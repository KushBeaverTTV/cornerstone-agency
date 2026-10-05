"""Hermes relay — one authenticated door from the phone to the Hermes backend the desktop uses.

Why: the Hermes desktop app runs its own `hermes serve` child on a random loopback port
(role ``desktop-serve``). Live turn streams only exist inside that process, so a phone that
talks to a *different* backend can only ever catch up from the database after the fact. When
the phone attaches to the SAME backend, `session.resume` on a stored id joins the live session
as an extra viewer and every `message.delta` / `tool.*` event fans out to both clients.

Target choice (re-checked every few seconds):
  1. ``desktop-serve`` — the backend the Hermes desktop app spawned (live record + token in the
     host rendezvous dir). Auth: ``X-Hermes-Session-Token`` header / ``?token=`` on the socket.
  2. ``serve`` fallback — the always-on agency dashboard (``hermes serve`` on :9119). Auth:
     basic login with the dashboard credentials, then cookies / one-time WS tickets.

The phone authenticates to the relay with the bridge bearer token (header) or a one-time relay
ticket (socket query), so it never holds a Hermes credential itself.
"""
from __future__ import annotations

import asyncio
import json
import logging
import os
import secrets
import time
from dataclasses import dataclass, field
from pathlib import Path
from typing import Any, Callable, Dict, Optional, Set

import httpx

log = logging.getLogger("agency_bridge.relay")

# Hop-by-hop / auth headers never forwarded in either direction.
_DROP_REQ = {
    "host", "content-length", "connection", "keep-alive", "authorization", "cookie",
    "upgrade", "te", "trailer", "transfer-encoding", "proxy-authorization", "accept-encoding",
}
_DROP_RESP = {
    "content-length", "connection", "keep-alive", "transfer-encoding", "set-cookie",
    "content-encoding", "upgrade",
}


def default_lock_dir() -> Path:
    """Mirror of hermes `gateway.status._get_lock_dir` (OS-user scoped rendezvous dir)."""
    override = os.environ.get("HERMES_GATEWAY_LOCK_DIR")
    if override:
        return Path(override)
    xdg = os.environ.get("XDG_STATE_HOME") or ""
    base = Path(xdg) if os.path.isabs(xdg) else Path.home() / ".local" / "state"
    return base / "hermes" / "gateway-locks"


def read_env_file(path: Path) -> Dict[str, str]:
    out: Dict[str, str] = {}
    try:
        for line in path.read_text(encoding="utf-8").splitlines():
            line = line.strip()
            if line and not line.startswith("#") and "=" in line:
                k, v = line.split("=", 1)
                out[k.strip()] = v.strip()
    except OSError:
        pass
    return out


@dataclass
class Target:
    kind: str  # "desktop" | "serve" | "none"
    base: str = ""  # http://host:port
    token: str = ""  # desktop session token
    pid: int = 0

    @property
    def key(self) -> str:
        return f"{self.kind}:{self.base}:{self.pid}"


def _pid_alive(pid: int, create_time: Optional[float]) -> bool:
    try:
        import psutil

        p = psutil.Process(pid)
        if create_time and abs(p.create_time() - float(create_time)) > 2.0:
            return False  # recycled PID
        return p.is_running()
    except Exception:
        return False


class HermesRelay:
    def __init__(
        self,
        serve_url: str,
        creds_file: Path,
        lock_dir: Optional[Path] = None,
        transport: Optional[httpx.AsyncBaseTransport] = None,
        pid_alive: Callable[[int, Optional[float]], bool] = _pid_alive,
    ) -> None:
        self.serve_url = serve_url.rstrip("/")
        self.creds_file = creds_file
        self.lock_dir = lock_dir or default_lock_dir()
        self._pid_alive = pid_alive
        self._transport = transport
        self._target: Optional[Target] = None
        self._target_at = 0.0
        self._serve_client: Optional[httpx.AsyncClient] = None
        self._serve_login_lock = asyncio.Lock()
        self._tickets: Dict[str, float] = {}
        self.sockets: Set["RelaySocket"] = set()
        self._watch_task: Optional[asyncio.Task] = None

    # -- target discovery ---------------------------------------------------

    def _desktop_target(self) -> Optional[Target]:
        rec_path = self.lock_dir / "host-desktop-serve.json"
        tok_path = self.lock_dir / "host-desktop-serve.token"
        try:
            rec = json.loads(rec_path.read_text(encoding="utf-8"))
            token = tok_path.read_text(encoding="utf-8").strip()
        except (OSError, ValueError):
            return None
        pid, port = int(rec.get("pid") or 0), int(rec.get("port") or 0)
        if not pid or not port or not token:
            return None
        if not self._pid_alive(pid, rec.get("createTime")):
            return None
        host = rec.get("host") or "127.0.0.1"
        if host in ("", "0.0.0.0", "::", "localhost"):
            host = "127.0.0.1"
        return Target("desktop", f"http://{host}:{port}", token, pid)

    def target(self, max_age: float = 3.0) -> Target:
        now = time.monotonic()
        if self._target is None or now - self._target_at > max_age:
            self._target = self._desktop_target() or (
                Target("serve", self.serve_url) if self.serve_url else Target("none")
            )
            self._target_at = now
        return self._target

    # -- upstream HTTP ------------------------------------------------------

    def _client(self, **kw) -> httpx.AsyncClient:
        return httpx.AsyncClient(transport=self._transport, timeout=httpx.Timeout(120, connect=8), **kw)

    async def _serve(self, relogin: bool = False) -> httpx.AsyncClient:
        """Cookie-holding client logged in to the agency dashboard."""
        async with self._serve_login_lock:
            if self._serve_client is not None and not relogin:
                return self._serve_client
            if self._serve_client is not None:
                await self._serve_client.aclose()
            env = read_env_file(self.creds_file)
            client = self._client(base_url=self.serve_url)
            r = await client.post("/auth/password-login", json={
                "provider": "basic",
                "username": env.get("HERMES_DASHBOARD_BASIC_AUTH_USERNAME", ""),
                "password": env.get("HERMES_DASHBOARD_BASIC_AUTH_PASSWORD", ""),
            })
            if r.status_code >= 400:
                await client.aclose()
                raise RuntimeError(f"dashboard login failed (HTTP {r.status_code})")
            self._serve_client = client
            return client

    async def request(self, method: str, path_qs: str, headers: Dict[str, str], body: bytes) -> httpx.Response:
        t = self.target()
        fwd = {k: v for k, v in headers.items() if k.lower() not in _DROP_REQ}
        if t.kind == "desktop":
            fwd["X-Hermes-Session-Token"] = t.token
            async with self._client() as c:
                return await c.request(method, t.base + path_qs, headers=fwd, content=body)
        if t.kind == "serve":
            client = await self._serve()
            r = await client.request(method, path_qs, headers=fwd, content=body)
            if r.status_code == 401:
                client = await self._serve(relogin=True)
                r = await client.request(method, path_qs, headers=fwd, content=body)
            return r
        raise RuntimeError("no Hermes backend is running")

    @staticmethod
    def response_headers(r: httpx.Response) -> Dict[str, str]:
        return {k: v for k, v in r.headers.items() if k.lower() not in _DROP_RESP}

    # -- upstream socket ----------------------------------------------------

    async def upstream_ws_url(self) -> tuple[str, Target]:
        t = self.target(max_age=0)
        ws_base = t.base.replace("http://", "ws://").replace("https://", "wss://")
        if t.kind == "desktop":
            return f"{ws_base}/api/ws?token={t.token}", t
        if t.kind == "serve":
            client = await self._serve()
            r = await client.post("/api/auth/ws-ticket", json={})
            if r.status_code == 401:
                client = await self._serve(relogin=True)
                r = await client.post("/api/auth/ws-ticket", json={})
            r.raise_for_status()
            try:
                el: Any = r.json()
            except ValueError:
                el = r.text.strip().strip('"')
            ticket = el if isinstance(el, str) else (el.get("ticket") or el.get("token"))
            return f"{ws_base}/api/ws?ticket={ticket}", t
        raise RuntimeError("no Hermes backend is running")

    # -- relay tickets (phone -> relay socket) ------------------------------

    def mint_ticket(self, ttl: float = 60.0) -> str:
        now = time.monotonic()
        self._tickets = {k: v for k, v in self._tickets.items() if v > now}
        tk = "rlt_" + secrets.token_urlsafe(18)
        self._tickets[tk] = now + ttl
        return tk

    def redeem_ticket(self, tk: str) -> bool:
        exp = self._tickets.pop(tk, None)
        return exp is not None and exp > time.monotonic()

    # -- switching backends -------------------------------------------------

    def start_watch(self) -> None:
        if self._watch_task is None:
            self._watch_task = asyncio.create_task(self._watch())

    async def stop(self) -> None:
        if self._watch_task:
            self._watch_task.cancel()
        if self._serve_client:
            await self._serve_client.aclose()

    async def _watch(self) -> None:
        """When the desktop app starts or quits, move idle phones to the new backend: closing
        their relay socket makes the app reconnect and re-resume on the right process."""
        while True:
            await asyncio.sleep(3)
            try:
                key = self.target(max_age=0).key
                now = time.monotonic()
                for s in list(self.sockets):
                    if s.target_key != key and now - s.last_activity > 20:
                        log.info("relay: backend moved (%s -> %s); recycling phone socket", s.target_key, key)
                        await s.close(1012, "backend switched")
            except asyncio.CancelledError:
                raise
            except Exception:  # noqa: BLE001
                log.exception("relay watch failed")

    def status(self) -> Dict[str, Any]:
        t = self.target()
        return {"backend": t.kind, "base": t.base, "pid": t.pid, "phones": len(self.sockets)}


@dataclass(eq=False)
class RelaySocket:
    target_key: str
    closer: Callable[[int, str], Any]
    last_activity: float = field(default_factory=time.monotonic)

    async def close(self, code: int, reason: str) -> None:
        try:
            await self.closer(code, reason)
        except Exception:  # noqa: BLE001
            pass
