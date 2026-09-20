"""Prima workers — one `prime-agent --mode rpc` subprocess per bridge session.

Protocol (prime-agent docs/rpc.md): LF-delimited JSONL on stdin/stdout.
Commands carry an `id` for correlation; stdout lines with type=="response"
resolve the pending command, extension_ui_request lines become ui_request
frames for the phone, everything else is broadcast as an event frame.
"""
from __future__ import annotations

import asyncio
import itertools
import json
import logging
import os
import shlex
import time
import uuid
from typing import Any, Dict, List, Optional

log = logging.getLogger("agency_bridge.prima")


class WorkerDead(Exception):
    pass


class PrimaWorker:
    def __init__(self, session_id: str, cmd: str, cwd: str, name: str, broadcast):
        self.id = session_id
        self.cmd = cmd
        self.cwd = cwd
        self.name = name
        self.created_at = time.time()
        self.last_activity = self.created_at
        self.is_streaming = False
        self.message_count = 0
        self.alive = True
        self._proc: Optional[asyncio.subprocess.Process] = None
        self._pending: Dict[str, asyncio.Future] = {}
        self._seq = itertools.count(1)
        self._broadcast = broadcast
        self._reader_task: Optional[asyncio.Task] = None
        self._write_lock = asyncio.Lock()

    @staticmethod
    def _parts(cmd: str) -> List[str]:
        """Split a command spec. Single existing path wins verbatim; otherwise
        non-POSIX split keeps Windows backslashes (posix=False keeps quotes,
        so strip them)."""
        cmd = cmd.strip()
        if os.path.exists(cmd):
            return [cmd]
        return [p.strip('"').strip("'") for p in shlex.split(cmd, posix=False)]

    @classmethod
    def _argv(cls, cmd: str) -> List[str]:
        """Build the spawn argv; a multi-word cmd is split (tests use
        `python fake_worker.py`), a bare .cmd/.bat goes through cmd.exe."""
        parts = cls._parts(cmd)
        if len(parts) == 1 and parts[0].lower().endswith((".cmd", ".bat")):
            return ["cmd", "/c", parts[0], "--mode", "rpc"]
        return parts + ["--mode", "rpc"]

    async def start(self) -> None:
        argv = self._argv(self.cmd)
        env = dict(os.environ)
        # Force unbuffered UTF-8 so JSONL lines arrive whole and promptly.
        env["PYTHONUNBUFFERED"] = "1"
        self._proc = await asyncio.create_subprocess_exec(
            *argv,
            cwd=self.cwd or None,
            stdin=asyncio.subprocess.PIPE,
            stdout=asyncio.subprocess.PIPE,
            stderr=asyncio.subprocess.STDOUT,
            env=env,
        )
        self._reader_task = asyncio.create_task(self._reader())
        await self._broadcast(
            {"type": "worker", "session_id": self.id, "state": "started"}
        )

    # -- commands ---------------------------------------------------------

    async def command(self, command: str, timeout: float = 60.0, **fields) -> Dict[str, Any]:
        if not self.alive or self._proc is None or self._proc.returncode is not None:
            raise WorkerDead(f"worker {self.id} is dead")
        rid = f"br-{next(self._seq)}"
        fut: asyncio.Future = asyncio.get_running_loop().create_future()
        self._pending[rid] = fut
        frame = {"id": rid, "type": command, **fields}
        line = json.dumps(frame, ensure_ascii=False) + "\n"
        try:
            async with self._write_lock:
                self._proc.stdin.write(line.encode("utf-8"))
                await self._proc.stdin.drain()
            self.last_activity = time.time()
            return await asyncio.wait_for(fut, timeout=timeout)
        finally:
            self._pending.pop(rid, None)

    async def prompt(self, message: str, streaming_behavior: Optional[str] = None) -> Dict[str, Any]:
        fields: Dict[str, Any] = {"message": message}
        if streaming_behavior:
            fields["streamingBehavior"] = streaming_behavior
        return await self.command("prompt", **fields)

    async def get_state(self) -> Dict[str, Any]:
        return await self.command("get_state")

    async def get_messages(self) -> Dict[str, Any]:
        return await self.command("get_messages", timeout=120.0)

    async def abort(self) -> Dict[str, Any]:
        return await self.command("abort", timeout=15.0)

    async def set_session_name(self, name: str) -> Dict[str, Any]:
        self.name = name
        return await self.command("set_session_name", name=name)

    async def ui_response(self, request_id: str, **fields) -> None:
        """extension_ui_response — fire-and-forget, no response frame."""
        if not self.alive or self._proc is None or self._proc.returncode is not None:
            raise WorkerDead(f"worker {self.id} is dead")
        frame = {"type": "extension_ui_response", "id": request_id, **fields}
        async with self._write_lock:
            self._proc.stdin.write(
                (json.dumps(frame, ensure_ascii=False) + "\n").encode("utf-8")
            )
            await self._proc.stdin.drain()
        self.last_activity = time.time()

    # -- stdout reader ----------------------------------------------------

    async def _reader(self) -> None:
        assert self._proc and self._proc.stdout
        code = -1
        try:
            async for raw in self._proc.stdout:
                line = raw.decode("utf-8", errors="replace").rstrip("\r\n")
                if not line.strip():
                    continue
                try:
                    msg = json.loads(line)
                except json.JSONDecodeError:
                    log.info("[%s] non-JSON stdout: %s", self.id[:8], line[:200])
                    continue
                self.last_activity = time.time()
                mtype = msg.get("type")
                if mtype == "response":
                    fut = self._pending.get(msg.get("id", ""))
                    if fut is not None and not fut.done():
                        fut.set_result(msg)
                    else:
                        log.debug("[%s] orphan response: %s", self.id[:8], msg)
                elif mtype == "extension_ui_request":
                    await self._broadcast(
                        {"type": "ui_request", "session_id": self.id, "request": msg}
                    )
                else:
                    if mtype in ("agent_start", "turn_start"):
                        self.is_streaming = True
                    elif mtype == "agent_end":
                        self.is_streaming = False
                        self.message_count += len(msg.get("messages") or [])
                    await self._broadcast(
                        {"type": "event", "session_id": self.id, "event": msg}
                    )
        except asyncio.CancelledError:
            raise
        except Exception:
            log.exception("[%s] stdout reader failed", self.id[:8])
        finally:
            try:
                code = await asyncio.wait_for(self._proc.wait(), timeout=5)
            except asyncio.TimeoutError:
                pass
            self.alive = False
            self.is_streaming = False
            for fut in self._pending.values():
                if not fut.done():
                    fut.set_exception(WorkerDead(f"worker exited ({code})"))
            self._pending.clear()
            await self._broadcast(
                {"type": "worker", "session_id": self.id, "state": "exited", "code": code}
            )

    async def kill(self) -> None:
        self.alive = False
        if self._reader_task:
            self._reader_task.cancel()
        if self._proc and self._proc.returncode is None:
            try:
                self._proc.kill()
            except ProcessLookupError:
                pass
            try:
                await asyncio.wait_for(self._proc.wait(), timeout=5)
            except asyncio.TimeoutError:
                pass

    def info(self) -> Dict[str, Any]:
        return {
            "id": self.id,
            "name": self.name,
            "cwd": self.cwd,
            "is_streaming": self.is_streaming,
            "message_count": self.message_count,
            "created_at": self.created_at,
            "last_activity": self.last_activity,
            "alive": self.alive,
        }


class PrimaManager:
    def __init__(self, prima_cmd: str, default_cwd: str, idle_s: float = 6 * 3600):
        self.prima_cmd = prima_cmd
        self.default_cwd = default_cwd
        self.idle_s = idle_s
        self.sessions: Dict[str, PrimaWorker] = {}
        self._subscribers: List[asyncio.Queue] = []
        self._reaper_task: Optional[asyncio.Task] = None
        self._version: Optional[str] = None

    # -- fanout -----------------------------------------------------------

    def subscribe(self) -> asyncio.Queue:
        q: asyncio.Queue = asyncio.Queue(maxsize=256)
        self._subscribers.append(q)
        return q

    def unsubscribe(self, q: asyncio.Queue) -> None:
        try:
            self._subscribers.remove(q)
        except ValueError:
            pass

    async def broadcast(self, frame: Dict[str, Any]) -> None:
        for q in list(self._subscribers):
            try:
                q.put_nowait(frame)
            except asyncio.QueueFull:
                # slow consumer — drop rather than block the worker reader
                pass

    # -- lifecycle --------------------------------------------------------

    async def start(self) -> None:
        self._reaper_task = asyncio.create_task(self._reaper())

    async def stop(self) -> None:
        if self._reaper_task:
            self._reaper_task.cancel()
        for w in list(self.sessions.values()):
            await w.kill()

    async def create(self, cwd: Optional[str] = None, name: str = "") -> PrimaWorker:
        sid = uuid.uuid4().hex[:12]
        worker = PrimaWorker(
            session_id=sid,
            cmd=self.prima_cmd,
            cwd=cwd or self.default_cwd,
            name=name,
            broadcast=self.broadcast,
        )
        await worker.start()
        self.sessions[sid] = worker
        return worker

    def get(self, session_id: str) -> Optional[PrimaWorker]:
        w = self.sessions.get(session_id)
        return w if (w and w.alive) else None

    async def delete(self, session_id: str) -> bool:
        w = self.sessions.pop(session_id, None)
        if not w:
            return False
        await w.kill()
        return True

    async def probe_version(self) -> Optional[str]:
        """Best-effort `prime-agent --version` for /health."""
        if self._version:
            return self._version
        try:
            parts = PrimaWorker._parts(self.prima_cmd)
            if len(parts) == 1 and parts[0].lower().endswith((".cmd", ".bat")):
                argv = ["cmd", "/c", parts[0], "--version"]
            else:
                argv = parts + ["--version"]
            proc = await asyncio.create_subprocess_exec(
                *argv,
                stdout=asyncio.subprocess.PIPE,
                stderr=asyncio.subprocess.STDOUT,
            )
            out, _ = await asyncio.wait_for(proc.communicate(), timeout=10)
            text = out.decode("utf-8", errors="replace").strip().splitlines()
            self._version = text[-1].strip() if text else "unknown"
        except Exception as e:  # noqa: BLE001
            log.warning("prime-agent --version failed: %s", e)
            self._version = None
        return self._version

    def available(self) -> bool:
        parts = PrimaWorker._parts(self.prima_cmd)
        return bool(parts) and (
            os.path.exists(parts[0]) or bool(__import__("shutil").which(parts[0]))
        )

    async def _reaper(self) -> None:
        while True:
            await asyncio.sleep(60)
            now = time.time()
            for sid, w in list(self.sessions.items()):
                if w.alive and now - w.last_activity > self.idle_s:
                    log.info("reaping idle prima worker %s", sid)
                    await self.delete(sid)
                elif not w.alive:
                    self.sessions.pop(sid, None)
