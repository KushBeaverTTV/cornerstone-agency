"""PC-side service health + restart for the phone's Me screen.

The Agency stack is three long-lived processes started at login (dashboard ``hermes serve`` on
:9119, this bridge on :9121, the voice bridge on :8766). A Windows scheduled task
(``ops/windows/agency-watchdog.ps1``) relaunches any that die; this module lets the phone see
the same picture and restart one on demand.
"""
from __future__ import annotations

import asyncio
import logging
import os
import subprocess
from dataclasses import dataclass
from pathlib import Path
from typing import Any, Dict, List, Optional

import httpx

log = logging.getLogger("agency_bridge.services")

SCRIPTS = Path(os.environ.get("LOCALAPPDATA", str(Path.home() / "AppData" / "Local"))) / "hermes" / "scripts"
WATCHDOG_LOG = SCRIPTS.parent / "logs" / "agency-watchdog.log"


@dataclass
class Service:
    name: str
    label: str
    port: int
    health_path: str
    launch: Optional[str]  # launch script under SCRIPTS; None = not restartable from here


def default_services() -> List[Service]:
    return [
        Service("dashboard", "Hermes dashboard", 9119, "/api/health", "launch-agency-dashboard.ps1"),
        Service("voice", "Voice bridge", 8766, "/health", "launch-agency-voice.ps1"),
        Service("bridge", "Desktop bridge", 9121, "/health", "launch-agency-bridge.ps1"),
    ]


def _listening_pid(port: int, host: Optional[str] = None) -> Optional[int]:
    """PID listening on ``host:port``. The host matters: the USB forwarder also listens on
    127.0.0.1 with the same port numbers and must never be mistaken for the service."""
    try:
        import psutil

        for c in psutil.net_connections(kind="tcp"):
            if (c.laddr and c.laddr.port == port and c.status == psutil.CONN_LISTEN and c.pid
                    and (host is None or c.laddr.ip in (host, "0.0.0.0", "::"))):
                return c.pid
    except Exception:  # noqa: BLE001
        pass
    return None


def _run_hidden(script: Path, delay_s: float = 0.0) -> None:
    cmd = f"Start-Sleep -Seconds {delay_s}; & '{script}'" if delay_s else f"& '{script}'"
    flags = 0
    if os.name == "nt":
        flags = subprocess.CREATE_NO_WINDOW | subprocess.DETACHED_PROCESS | subprocess.CREATE_NEW_PROCESS_GROUP
    subprocess.Popen(
        ["powershell", "-NoProfile", "-ExecutionPolicy", "Bypass", "-Command", cmd],
        creationflags=flags, close_fds=True,
    )


class ServiceBoard:
    def __init__(self, host: str, services: Optional[List[Service]] = None) -> None:
        self.host = host
        self.services = {s.name: s for s in (services or default_services())}

    async def _probe(self, s: Service) -> Dict[str, Any]:
        url = f"http://{self.host}:{s.port}{s.health_path}"
        try:
            async with httpx.AsyncClient(timeout=3.0) as c:
                r = await c.get(url)
            ok = r.status_code < 500
        except Exception:  # noqa: BLE001
            ok = False
        return {
            "name": s.name, "label": s.label, "port": s.port, "ok": ok,
            "pid": _listening_pid(s.port, self.host), "restartable": s.launch is not None,
        }

    async def status(self) -> List[Dict[str, Any]]:
        return list(await asyncio.gather(*(self._probe(s) for s in self.services.values())))

    def last_watchdog_lines(self, n: int = 5) -> List[str]:
        try:
            # utf-8-sig: Windows PowerShell writes the log with a BOM.
            return WATCHDOG_LOG.read_text(encoding="utf-8-sig", errors="replace").splitlines()[-n:]
        except OSError:
            return []

    async def restart(self, name: str, on_self_restart=None) -> Dict[str, Any]:
        s = self.services.get(name)
        if s is None or s.launch is None:
            raise KeyError(name)
        script = SCRIPTS / s.launch
        if not script.exists():
            raise FileNotFoundError(str(script))
        if name == "bridge":
            # Relaunch after we exit; the new process needs the port.
            _run_hidden(script, delay_s=2)
            if on_self_restart:
                on_self_restart()
            return {"ok": True, "restarting": name}
        pid = _listening_pid(s.port, self.host)
        if pid:
            try:
                import psutil

                proc = psutil.Process(pid)
                kids = proc.children(recursive=True)
                # `hermes.exe serve` is a launcher whose python child holds the port.
                parent = proc.parent()
                extra = [parent] if parent and parent.name().lower() == "hermes.exe" else []
                for p in [proc, *kids, *extra]:
                    try:
                        p.kill()
                    except Exception:  # noqa: BLE001
                        pass
                await asyncio.sleep(1.0)
            except Exception:  # noqa: BLE001
                log.exception("kill %s pid %s failed", name, pid)
        _run_hidden(script)
        log.info("restart requested for %s (old pid %s)", name, pid)
        return {"ok": True, "restarting": name, "old_pid": pid}
