"""Cornerstone Agency desktop bridge — FastAPI app.

Bearer auth on everything except /health. WS /prima/ws takes ?token=.
Mirrors services/hermes-mobile-gateway in layout and logging style.
"""
from __future__ import annotations

import asyncio
import json
import logging
import os
import sys
from typing import Optional

import uvicorn
from fastapi import Depends, FastAPI, HTTPException, Request, WebSocket, WebSocketDisconnect
from fastapi.security import HTTPAuthorizationCredentials, HTTPBearer
from pydantic import BaseModel

from .config import Config
from .laya import LayaEngine
from .prima import PrimaManager, WorkerDead

log = logging.getLogger("agency_bridge")

VERSION = "0.1.0"

cfg = Config()
prima = PrimaManager(cfg.prima_cmd, cfg.default_cwd, cfg.worker_idle_s)
laya = LayaEngine(cfg.laya_dir)

app = FastAPI(title="cornerstone-agency-bridge", version=VERSION)
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
    log.info(
        "agency-bridge %s on %s:%d (token=%s… masked, prima=%s, laya=%s)",
        VERSION, cfg.host, cfg.port, _masked(), cfg.prima_cmd, cfg.laya_dir,
    )


@app.on_event("shutdown")
async def _shutdown() -> None:
    await prima.stop()


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
    }


# -- prima ----------------------------------------------------------------

class NewSession(BaseModel):
    cwd: Optional[str] = None
    name: str = ""


class PromptIn(BaseModel):
    message: str
    streaming_behavior: Optional[str] = None


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
    try:
        w = await prima.create(cwd=cwd, name=body.name)
    except Exception as e:  # noqa: BLE001
        log.exception("worker spawn failed")
        raise HTTPException(502, f"prime-agent spawn failed: {e}") from e
    return {"id": w.id}


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
    try:
        resp = await w.prompt(body.message, body.streaming_behavior)
    except WorkerDead as e:
        raise HTTPException(410, str(e)) from e
    if not resp.get("success"):
        raise HTTPException(502, {"detail": "prompt rejected", "response": resp})
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
    uvicorn.run(app, host=cfg.host, port=cfg.port, log_level="info")


if __name__ == "__main__":
    run()
