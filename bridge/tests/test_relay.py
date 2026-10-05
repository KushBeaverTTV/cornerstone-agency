"""Hermes relay: backend choice, auth, HTTP + socket forwarding (fake upstream backends)."""
import asyncio
import json
import os
import socket
import sys
import threading
import time

import httpx
import pytest
import uvicorn
from fastapi import FastAPI, Request, WebSocket, WebSocketDisconnect
from fastapi.responses import JSONResponse
from fastapi.testclient import TestClient

sys.path.insert(0, os.path.join(os.path.dirname(__file__), ".."))

import agency_bridge.app as appmod  # noqa: E402
from agency_bridge.hermes_relay import HermesRelay  # noqa: E402

AUTH = {"Authorization": f"Bearer {appmod.cfg.token}"}


def _free_port() -> int:
    s = socket.socket()
    s.bind(("127.0.0.1", 0))
    port = s.getsockname()[1]
    s.close()
    return port


def fake_hermes(token: str) -> FastAPI:
    """Minimal Hermes backend: token-gated REST + JSON-RPC echo socket that also pushes an event."""
    app = FastAPI()

    @app.get("/api/profiles")
    async def profiles(request: Request):
        if request.headers.get("x-hermes-session-token") != token:
            return JSONResponse({"error": "unauthorized"}, status_code=401)
        return {"profiles": [{"name": "default"}], "who": "desktop"}

    @app.websocket("/api/ws")
    async def ws(sock: WebSocket):
        if sock.query_params.get("token") != token:
            await sock.close(code=4401)
            return
        await sock.accept()
        while True:
            try:
                msg = json.loads(await sock.receive_text())
            except WebSocketDisconnect:
                return
            await sock.send_text(json.dumps({"jsonrpc": "2.0", "id": msg["id"], "result": {"echo": msg["method"]}}))
            await sock.send_text(json.dumps({"jsonrpc": "2.0", "method": "event", "params": {
                "type": "message.delta", "session_id": "rt1", "payload": {"text": "hi"}}}))

    return app


@pytest.fixture(scope="module")
def desktop_backend(tmp_path_factory):
    token = "desk_tok"
    port = _free_port()
    server = uvicorn.Server(uvicorn.Config(fake_hermes(token), host="127.0.0.1", port=port, log_level="warning"))
    th = threading.Thread(target=server.run, daemon=True)
    th.start()
    deadline = time.time() + 10
    while not server.started and time.time() < deadline:
        time.sleep(0.05)
    lock = tmp_path_factory.mktemp("locks")
    (lock / "host-desktop-serve.json").write_text(json.dumps(
        {"role": "desktop-serve", "pid": os.getpid(), "host": "127.0.0.1", "port": port}), encoding="utf-8")
    (lock / "host-desktop-serve.token").write_text(token, encoding="utf-8")
    yield lock, port
    server.should_exit = True


def test_target_prefers_live_desktop_backend(tmp_path):
    (tmp_path / "host-desktop-serve.json").write_text(json.dumps({"pid": 42, "port": 5555}), encoding="utf-8")
    (tmp_path / "host-desktop-serve.token").write_text("t", encoding="utf-8")
    alive = HermesRelay("http://serve:9119", tmp_path / "creds.env", lock_dir=tmp_path, pid_alive=lambda p, c: True)
    assert alive.target().kind == "desktop" and alive.target().base == "http://127.0.0.1:5555"
    dead = HermesRelay("http://serve:9119", tmp_path / "creds.env", lock_dir=tmp_path, pid_alive=lambda p, c: False)
    assert dead.target().kind == "serve" and dead.target().base == "http://serve:9119"


def test_relay_tickets_are_single_use(tmp_path):
    r = HermesRelay("http://serve:9119", tmp_path / "c.env", lock_dir=tmp_path)
    tk = r.mint_ticket()
    assert r.redeem_ticket(tk) is True
    assert r.redeem_ticket(tk) is False
    assert r.redeem_ticket("rlt_forged") is False


def test_serve_fallback_logs_in_with_dashboard_credentials(tmp_path):
    creds = tmp_path / "dash.env"
    creds.write_text("HERMES_DASHBOARD_BASIC_AUTH_USERNAME=kush\nHERMES_DASHBOARD_BASIC_AUTH_PASSWORD=pw\n", encoding="utf-8")
    seen = []

    def handler(req: httpx.Request) -> httpx.Response:
        seen.append((req.method, req.url.path, req.headers.get("cookie")))
        if req.url.path == "/auth/password-login":
            assert json.loads(req.content) == {"provider": "basic", "username": "kush", "password": "pw"}
            return httpx.Response(200, json={"ok": True}, headers={"set-cookie": "hermes_session=abc; Path=/"})
        if req.headers.get("cookie") != "hermes_session=abc":
            return httpx.Response(401)
        return httpx.Response(200, json={"profiles": []})

    relay = HermesRelay("http://serve:9119", creds, lock_dir=tmp_path, transport=httpx.MockTransport(handler),
                        pid_alive=lambda p, c: False)
    r = asyncio.run(relay.request("GET", "/api/profiles", {"authorization": "Bearer phone"}, b""))
    assert r.status_code == 200
    assert seen[0][1] == "/auth/password-login" and seen[-1] == ("GET", "/api/profiles", "hermes_session=abc")


def test_http_and_socket_relay_to_desktop_backend(desktop_backend):
    lock, port = desktop_backend
    saved = appmod.relay
    appmod.relay = HermesRelay("http://127.0.0.1:1", lock / "none.env", lock_dir=lock)
    try:
        with TestClient(appmod.app) as c:
            assert c.get("/hermes/api/profiles").status_code == 401  # bearer required
            r = c.get("/hermes/api/profiles", headers=AUTH)
            assert r.status_code == 200 and r.json()["who"] == "desktop"
            assert c.get("/hermes/status", headers=AUTH).json()["backend"] == "desktop"

            ticket = c.post("/hermes/api/auth/ws-ticket", headers=AUTH).json()["ticket"]
            with c.websocket_connect(f"/hermes/api/ws?ticket={ticket}") as ws:
                ws.send_text(json.dumps({"jsonrpc": "2.0", "id": 7, "method": "session.list", "params": {}}))
                first = json.loads(ws.receive_text())
                second = json.loads(ws.receive_text())
                assert first == {"jsonrpc": "2.0", "id": 7, "result": {"echo": "session.list"}}
                assert second["params"]["type"] == "message.delta"
            # The ticket was consumed.
            with pytest.raises(Exception):
                with c.websocket_connect(f"/hermes/api/ws?ticket={ticket}") as ws:
                    ws.receive_text()
    finally:
        appmod.relay = saved
