"""Cornerstone Agency desktop bridge — FastAPI app.

Bearer auth on everything except /health. WS /prima/ws takes ?token=; the Hermes relay socket
(/hermes/api/ws) takes a one-time relay ticket or the token.

Surfaces: /prima (Prime Agent workers + saved sessions), /laya (routing model),
/hermes (relay to the Hermes backend the desktop app uses), /services (PC stack health).
"""
from __future__ import annotations

import asyncio
import json
import re
import secrets
import logging
import os
import sys
import time
from typing import Any, Optional
from urllib.parse import quote

import uvicorn
from fastapi import Depends, FastAPI, HTTPException, Request, WebSocket, WebSocketDisconnect
from fastapi.security import HTTPAuthorizationCredentials, HTTPBearer
from pydantic import BaseModel

from pathlib import Path

from fastapi.middleware.gzip import GZipMiddleware
from fastapi.responses import FileResponse, Response

from . import lite, prima_saved
from .config import Config
from .hermes_relay import HermesRelay, RelaySocket
from .laya import LayaEngine
from .prima import PrimaManager, WorkerDead
from .services import ServiceBoard

log = logging.getLogger("agency_bridge")

VERSION = "0.3.0"

cfg = Config()
prima = PrimaManager(cfg.prima_cmd, cfg.default_cwd, cfg.worker_idle_s)
laya = LayaEngine(cfg.laya_dir)
relay = HermesRelay(
    serve_url=os.environ.get("AGENCY_HERMES_SERVE_URL", f"http://{cfg.host}:9119"),
    creds_file=Path(os.environ.get(
        "AGENCY_DASHBOARD_ENV", r"C:\Cornerstone\Cornerstone_Secrets\agency-dashboard.env")),
)
board = ServiceBoard(cfg.host)

app = FastAPI(title="cornerstone-agency-bridge", version=VERSION)
# The phone is often on cellular: gzip every JSON body over 1 KB (OkHttp asks for it).
app.add_middleware(GZipMiddleware, minimum_size=1024)
_bearer = HTTPBearer(auto_error=False)


def _masked() -> str:
    return Config.mask(cfg.token)


async def require_auth(
    creds: Optional[HTTPAuthorizationCredentials] = Depends(_bearer),
) -> None:
    if creds is None or creds.credentials != cfg.token:
        raise HTTPException(status_code=401, detail="bad or missing bearer token")


@app.on_event("startup")
async def _startup() -> None:
    await prima.start()
    relay.start_watch()
    log.info(
        "agency-bridge %s on %s:%d (token=%s… masked, prima=%s, laya=%s)",
        VERSION, cfg.host, cfg.port, _masked(), cfg.prima_cmd, cfg.laya_dir,
    )


@app.on_event("shutdown")
async def _shutdown() -> None:
    await prima.stop()
    await relay.stop()


# -- health ---------------------------------------------------------------

@app.get("/health")
async def health() -> dict:
    return {
        "ok": True,
        "service": "cornerstone-agency-bridge",
        "version": VERSION,
        "prima": {
            "available": prima.available(),
            "version": await prima.probe_version(),
            "sessions": len(prima.sessions),
        },
        "laya": laya.status(),
        "hermes": {"backend": relay.target().kind},
    }


# -- prima ----------------------------------------------------------------

class NewSession(BaseModel):
    cwd: Optional[str] = None
    name: str = ""
    # Reopen a saved session (a path from GET /prima/saved).
    session_path: Optional[str] = None


class ImageIn(BaseModel):
    data: str  # base64, no data: prefix
    mime_type: str = "image/jpeg"


class FileIn(BaseModel):
    name: str
    data: str  # base64


class PromptIn(BaseModel):
    message: str
    streaming_behavior: Optional[str] = None
    images: list[ImageIn] = []
    files: list[FileIn] = []


# Phone uploads for Prima land here; Prima reads them with its own file tools.
UPLOAD_DIR = Path.home() / ".prime" / "agent" / "uploads"
MAX_UPLOAD_BYTES = 25 * 1024 * 1024


def _save_upload(f: FileIn) -> Path:
    import base64
    import re as _re

    raw = base64.b64decode(f.data, validate=False)
    if len(raw) > MAX_UPLOAD_BYTES:
        raise HTTPException(413, f"{f.name} is over 25 MB")
    safe = _re.sub(r"[^A-Za-z0-9._ -]", "_", Path(f.name).name).strip() or "upload"
    UPLOAD_DIR.mkdir(parents=True, exist_ok=True)
    dest = UPLOAD_DIR / f"{time.strftime('%Y%m%d-%H%M%S')}-{safe}"
    dest.write_bytes(raw)
    return dest


class NameIn(BaseModel):
    name: str


@app.get("/prima/sessions", dependencies=[Depends(require_auth)])
async def list_sessions() -> dict:
    return {"sessions": [w.info() for w in prima.sessions.values() if w.alive]}


@app.post("/prima/sessions", dependencies=[Depends(require_auth)])
async def create_session(body: NewSession) -> dict:
    if not prima.available():
        raise HTTPException(503, f"prime-agent not found at {cfg.prima_cmd}")
    cwd = body.cwd or cfg.default_cwd
    if not os.path.isdir(cwd):
        raise HTTPException(400, f"cwd does not exist: {cwd}")
    if body.session_path and not prima_saved.is_saved_session(body.session_path, prima_saved_dir()):
        raise HTTPException(400, "session_path is not a saved Prima session")
    try:
        w = await prima.create(cwd=cwd, name=body.name, session_path=body.session_path)
    except Exception as e:  # noqa: BLE001
        log.exception("worker spawn failed")
        raise HTTPException(502, f"prime-agent spawn failed: {e}") from e
    return {"id": w.id}


def prima_saved_dir() -> Path:
    return Path(os.environ.get("AGENCY_PRIMA_SESSIONS_DIR", str(prima_saved.DEFAULT_DIR)))


@app.get("/prima/saved", dependencies=[Depends(require_auth)])
async def saved_sessions(limit: int = 50) -> dict:
    """Prima conversations saved on this PC (desktop and phone alike), newest first."""
    rows = await asyncio.to_thread(prima_saved.list_saved, prima_saved_dir(), limit)
    open_by_file = {w.session_file: w.id for w in prima.sessions.values() if w.alive and w.session_file}
    for r in rows:
        r["open_id"] = open_by_file.get(r["path"])
    return {"sessions": rows}


@app.get("/prima/sessions/{sid}", dependencies=[Depends(require_auth)])
async def session_state(sid: str) -> dict:
    w = prima.get(sid)
    if not w:
        raise HTTPException(404, "session not found or worker dead")
    try:
        resp = await w.get_state()
    except WorkerDead as e:
        raise HTTPException(410, str(e)) from e
    return {"bridge": w.info(), "state": resp.get("data", resp)}


@app.get("/prima/sessions/{sid}/messages", dependencies=[Depends(require_auth)])
async def session_messages(sid: str) -> dict:
    w = prima.get(sid)
    if not w:
        raise HTTPException(404, "session not found or worker dead")
    try:
        resp = await w.get_messages()
    except WorkerDead as e:
        raise HTTPException(410, str(e)) from e
    return resp.get("data", resp)


@app.post("/prima/sessions/{sid}/prompt", dependencies=[Depends(require_auth)])
async def session_prompt(sid: str, body: PromptIn) -> dict:
    w = prima.get(sid)
    if not w:
        raise HTTPException(404, "session not found or worker dead")
    if w.is_streaming and not body.streaming_behavior:
        raise HTTPException(409, "agent is streaming; pass streaming_behavior")
    message = body.message
    if body.files:
        paths = [_save_upload(f) for f in body.files]
        message = (message + "\n\n" if message else "") + "\n".join(f"[Attached file: {p}]" for p in paths)
    images = [{"type": "image", "data": i.data, "mimeType": i.mime_type} for i in body.images]
    try:
        resp = await w.prompt(message, body.streaming_behavior, images or None)
    except WorkerDead as e:
        raise HTTPException(410, str(e)) from e
    if not resp.get("success"):
        # Prima explains why (e.g. "the selected model does not accept image input"); pass it on.
        raise HTTPException(422, str(resp.get("error") or "Prima rejected the prompt"))
    return {"accepted": True}


@app.post("/prima/sessions/{sid}/abort", dependencies=[Depends(require_auth)])
async def session_abort(sid: str) -> dict:
    w = prima.get(sid)
    if not w:
        raise HTTPException(404, "session not found or worker dead")
    try:
        await w.abort()
    except WorkerDead as e:
        raise HTTPException(410, str(e)) from e
    return {"ok": True}


@app.post("/prima/sessions/{sid}/name", dependencies=[Depends(require_auth)])
async def session_name(sid: str, body: NameIn) -> dict:
    w = prima.get(sid)
    if not w:
        raise HTTPException(404, "session not found or worker dead")
    try:
        await w.set_session_name(body.name)
    except WorkerDead as e:
        raise HTTPException(410, str(e)) from e
    return {"ok": True}


class ModelIn(BaseModel):
    provider: str
    model_id: str


@app.get("/prima/sessions/{sid}/models", dependencies=[Depends(require_auth)])
async def session_models(sid: str) -> dict:
    """Models this worker can switch to + the one it is on now (drives the phone's picker)."""
    w = prima.get(sid)
    if not w:
        raise HTTPException(404, "session not found or worker dead")
    try:
        avail = await w.get_available_models()
        state = await w.get_state()
    except WorkerDead as e:
        raise HTTPException(410, str(e)) from e
    models = (avail.get("data") or {}).get("models") or []
    cur = (state.get("data") or {}).get("model") or None
    return {
        # Picking a model only affects this conversation (the worker is respawned with --model).
        "scope": "session",
        "current": {"provider": cur.get("provider"), "id": cur.get("id")} if cur else None,
        "models": [
            {"provider": m.get("provider"), "id": m.get("id"), "name": m.get("name") or m.get("id")}
            for m in models
            if m.get("id")
        ],
    }


@app.post("/prima/sessions/{sid}/model", dependencies=[Depends(require_auth)])
async def session_set_model(sid: str, body: ModelIn) -> dict:
    if not prima.get(sid):
        raise HTTPException(404, "session not found or worker dead")
    try:
        w = await prima.restart_with_model(sid, body.provider, body.model_id)
    except WorkerDead as e:
        raise HTTPException(410, str(e)) from e
    except RuntimeError as e:
        raise HTTPException(409, str(e)) from e
    st = await w.get_state()
    cur = (st.get("data") or {}).get("model") or {}
    if cur.get("id") and cur.get("id") != body.model_id:
        raise HTTPException(502, f"Prima stayed on {cur.get('id')}")
    return {"ok": True, "scope": "session"}


@app.delete("/prima/sessions/{sid}", dependencies=[Depends(require_auth)])
async def session_delete(sid: str) -> dict:
    if not await prima.delete(sid):
        raise HTTPException(404, "session not found")
    return {"ok": True}


# -- prima ws --------------------------------------------------------------

@app.websocket("/prima/ws")
async def prima_ws(ws: WebSocket) -> None:
    token = ws.query_params.get("token", "")
    if token != cfg.token:
        await ws.close(code=4401)
        return
    await ws.accept()
    q = prima.subscribe()
    try:
        async def pump() -> None:
            while True:
                frame = await q.get()
                await ws.send_text(json.dumps(frame, ensure_ascii=False))

        pump_task = asyncio.create_task(pump())
        try:
            while True:
                raw = await ws.receive_text()
                try:
                    msg = json.loads(raw)
                except json.JSONDecodeError:
                    continue
                if msg.get("type") == "ping":
                    await ws.send_text('{"type":"pong"}')
                elif msg.get("type") == "ui_response":
                    sid = msg.get("session_id", "")
                    resp = msg.get("response") or {}
                    w = prima.get(sid)
                    if w:
                        try:
                            await w.ui_response(resp.get("id", ""), **{
                                k: v for k, v in resp.items() if k != "id"
                            })
                        except WorkerDead:
                            pass
        finally:
            pump_task.cancel()
    except WebSocketDisconnect:
        pass
    finally:
        prima.unsubscribe(q)


# -- hermes relay -----------------------------------------------------------

@app.get("/hermes/status", dependencies=[Depends(require_auth)])
async def hermes_status() -> dict:
    return relay.status()


@app.post("/hermes/api/auth/ws-ticket", dependencies=[Depends(require_auth)])
async def hermes_ws_ticket() -> dict:
    """One-time ticket for /hermes/api/ws (the phone's socket cannot carry a header everywhere)."""
    return {"ticket": relay.mint_ticket()}


@app.api_route(
    "/hermes/{path:path}", methods=["GET", "POST", "PUT", "PATCH", "DELETE"],
    dependencies=[Depends(require_auth)],
)
async def hermes_proxy(path: str, request: Request) -> Response:
    qs = request.url.query
    path_qs = "/" + path + (("?" + qs) if qs else "")
    try:
        r = await relay.request(request.method, path_qs, dict(request.headers), await request.body())
    except Exception as e:  # noqa: BLE001
        log.warning("relay %s %s failed: %s", request.method, path, e)
        raise HTTPException(502, f"Hermes backend unreachable: {e}") from e
    return Response(content=r.content, status_code=r.status_code, headers=relay.response_headers(r))


# -- phone-sized reads (see lite.py) ----------------------------------------------

async def _relay_json(path_qs: str) -> Any:
    try:
        r = await relay.request("GET", path_qs, {"accept": "application/json"}, b"")
    except Exception as e:  # noqa: BLE001
        raise HTTPException(502, f"Hermes backend unreachable: {e}") from e
    if r.status_code != 200:
        raise HTTPException(r.status_code, r.text[:300])
    return r.json()


@app.get("/lite/sessions/{sid}/messages", dependencies=[Depends(require_auth)])
async def lite_messages(sid: str, limit: int = 120, profile: Optional[str] = None) -> dict:
    """Newest `limit` transcript rows, oldest first, stripped to what the phone renders."""
    qs = f"?limit={max(1, min(limit, 500))}&order=latest&inline_images=false"
    if profile:
        qs += f"&profile={quote(profile)}"
    return lite.compact_messages(await _relay_json(f"/api/sessions/{quote(sid)}/messages{qs}"))


@app.get("/lite/sessions", dependencies=[Depends(require_auth)])
async def lite_sessions(profile: Optional[str] = None, limit: int = 30) -> dict:
    """A bot's chats by last activity (id, title, preview, times) for the Chats screen."""
    qs = f"?order=recent&limit={max(1, min(limit, 100))}"
    if profile:
        qs += f"&profile={quote(profile)}"
    return lite.compact_sessions(await _relay_json(f"/api/sessions{qs}"))


# -- app updates over Tailscale ---------------------------------------------------
# The newest signed release in <repo>/dist (cornerstone-agency-X.Y.Z.apk). The app checks
# /app/latest and downloads with its bearer token; a phone browser can use
# ?key=<AGENCY_DOWNLOAD_KEY from bridge.env> instead (download-only secret).

DIST = Path(__file__).resolve().parents[2] / "dist"
_SEMVER = re.compile(r"(\d+)\.(\d+)\.(\d+)")


def _latest_apk() -> Optional[Path]:
    def ver(p: Path) -> tuple:
        m = _SEMVER.search(p.name)
        return tuple(int(x) for x in m.groups()) if m else (0, 0, 0)

    apks = sorted(DIST.glob("cornerstone-agency-*.apk"), key=ver)
    return apks[-1] if apks else None


@app.get("/app/latest", dependencies=[Depends(require_auth)])
async def app_latest() -> dict:
    apk = _latest_apk()
    if apk is None:
        raise HTTPException(404, "no release in dist/")
    m = _SEMVER.search(apk.name)
    return {"version_name": ".".join(m.groups()) if m else "", "size": apk.stat().st_size, "file": apk.name}


@app.get("/app/latest.apk")
async def app_latest_apk(request: Request) -> FileResponse:
    key = request.query_params.get("key", "")
    auth = request.headers.get("authorization", "")
    dl_key = cfg.download_key
    ok = secrets.compare_digest(auth, f"Bearer {cfg.token}") or (dl_key and secrets.compare_digest(key, dl_key))
    if not ok:
        raise HTTPException(401, "unauthorized")
    apk = _latest_apk()
    if apk is None:
        raise HTTPException(404, "no release in dist/")
    return FileResponse(apk, media_type="application/vnd.android.package-archive", filename=apk.name)


@app.websocket("/hermes/api/ws")
async def hermes_ws(ws: WebSocket) -> None:
    import websockets

    ticket = ws.query_params.get("ticket", "")
    token = ws.query_params.get("token", "")
    auth = ws.headers.get("authorization", "")
    ok = relay.redeem_ticket(ticket) if ticket else (token == cfg.token or auth == f"Bearer {cfg.token}")
    if not ok:
        await ws.close(code=4401)
        return
    try:
        url, target = await relay.upstream_ws_url()
        upstream = await websockets.connect(url, max_size=None, ping_interval=20, open_timeout=15)
    except Exception as e:  # noqa: BLE001
        log.warning("relay socket: upstream connect failed: %s", e)
        await ws.close(code=1013)
        return
    await ws.accept()

    async def closer(code: int, reason: str) -> None:
        await upstream.close()
        await ws.close(code=code, reason=reason)

    rs = RelaySocket(target_key=target.key, closer=closer)
    relay.sockets.add(rs)

    async def phone_to_hermes() -> None:
        while True:
            msg = await ws.receive()
            if msg.get("type") == "websocket.disconnect":
                return
            rs.last_activity = time.monotonic()
            if msg.get("text") is not None:
                await upstream.send(msg["text"])
            elif msg.get("bytes") is not None:
                await upstream.send(msg["bytes"])

    async def hermes_to_phone() -> None:
        async for frame in upstream:
            rs.last_activity = time.monotonic()
            if isinstance(frame, bytes):
                await ws.send_bytes(frame)
            else:
                await ws.send_text(frame)

    tasks = [asyncio.create_task(phone_to_hermes()), asyncio.create_task(hermes_to_phone())]
    try:
        await asyncio.wait(tasks, return_when=asyncio.FIRST_COMPLETED)
    finally:
        for t in tasks:
            t.cancel()
        relay.sockets.discard(rs)
        try:
            await upstream.close()
        except Exception:  # noqa: BLE001
            pass
        try:
            await ws.close()
        except Exception:  # noqa: BLE001
            pass


# -- services ---------------------------------------------------------------

@app.get("/services", dependencies=[Depends(require_auth)])
async def services_status() -> dict:
    return {
        "services": await board.status(),
        "hermes": relay.status(),
        "watchdog": board.last_watchdog_lines(),
    }


@app.post("/services/{name}/restart", dependencies=[Depends(require_auth)])
async def services_restart(name: str) -> dict:
    def exit_soon() -> None:
        asyncio.get_running_loop().call_later(0.5, lambda: os._exit(0))

    try:
        return await board.restart(name, on_self_restart=exit_soon)
    except KeyError as e:
        raise HTTPException(404, f"unknown or non-restartable service: {name}") from e
    except FileNotFoundError as e:
        raise HTTPException(500, f"launch script missing: {e}") from e


# -- laya -------------------------------------------------------------------

class PredictIn(BaseModel):
    state: str
    questions: dict


class RouteIn(BaseModel):
    text: str
    bots: list[dict]


class TriageIn(BaseModel):
    title: str
    body: str = ""


@app.get("/laya/status", dependencies=[Depends(require_auth)])
async def laya_status() -> dict:
    return laya.status()


@app.post("/laya/predict", dependencies=[Depends(require_auth)])
async def laya_predict(body: PredictIn) -> dict:
    try:
        return await laya.predict(body.state, body.questions)
    except Exception as e:  # noqa: BLE001
        raise HTTPException(502, f"laya predict failed: {e}") from e


@app.post("/laya/route", dependencies=[Depends(require_auth)])
async def laya_route(body: RouteIn) -> dict:
    if not body.bots:
        raise HTTPException(400, "bots list is empty")
    try:
        return await laya.route(body.text, body.bots)
    except Exception as e:  # noqa: BLE001
        raise HTTPException(502, f"laya route failed: {e}") from e


@app.post("/laya/triage", dependencies=[Depends(require_auth)])
async def laya_triage(body: TriageIn) -> dict:
    try:
        return await laya.triage(body.title, body.body)
    except Exception as e:  # noqa: BLE001
        raise HTTPException(502, f"laya triage failed: {e}") from e


# -- entry ------------------------------------------------------------------

def run() -> None:
    logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s %(levelname)s %(name)s: %(message)s",
    )
    # httpx logs every relayed request at INFO (one line per phone poll); the log is not rotated.
    logging.getLogger("httpx").setLevel(logging.WARNING)
    if len(sys.argv) > 1 and sys.argv[1] == "pair":
        from .pair import main as pair_main

        sys.argv = [sys.argv[0], *sys.argv[2:]]
        pair_main()
        return
    # No per-request access log: the relay carries every phone REST call and the log file is
    # not rotated (Start-Process redirect). Warnings and errors still land there.
    uvicorn.run(app, host=cfg.host, port=cfg.port, log_level="info", access_log=False)


if __name__ == "__main__":
    run()
