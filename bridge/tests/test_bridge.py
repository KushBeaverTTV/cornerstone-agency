"""Bridge tests: auth, health, Prima lifecycle (fake worker), Laya (mocked)."""
import asyncio
import json
import os
import sys
import threading
import time

import pytest
from fastapi.testclient import TestClient

sys.path.insert(0, os.path.join(os.path.dirname(__file__), ".."))

import agency_bridge.app as appmod  # noqa: E402

FAKE_WORKER = os.path.join(os.path.dirname(__file__), "fake_worker.py")
AUTH = {"Authorization": f"Bearer {appmod.cfg.token}"}


@pytest.fixture(scope="module")
def client():
    """Point the Prima manager at the fake worker script before app startup."""
    appmod.prima.prima_cmd = f"{sys.executable} {FAKE_WORKER}"
    with TestClient(appmod.app) as c:
        yield c


class _MockAgent:
    device = type("D", (), {"type": "cpu"})()

    def system_one(self, state, questions):
        answers = {}
        for qid, q in questions.items():
            if q["type"] == "choice":
                keys = list(q["criteria"].keys())
                answers[qid] = {
                    "type": "choice", "choice": keys[0],
                    "probabilities": {k: round(1 / len(keys), 4) for k in keys},
                    "confidence": 0.5, "rl_agent": {},
                }
            elif q["type"] == "score":
                answers[qid] = {
                    "type": "score", "score": 1.0,
                    "legend": {str(i): c for i, c in enumerate(q["criteria"])},
                    "probabilities": {"0": 0.2, "1": 0.6, "2": 0.2},
                    "confidence": 0.6, "rl_agent": {},
                }
            else:
                answers[qid] = {"type": "noul", "noul": 0.9, "rl_agent": {}}
        return {"model": "rl-agent", "answers": answers, "usage": {}}


@pytest.fixture(autouse=True)
def _mock_laya(monkeypatch):
    appmod.laya._agent = _MockAgent()
    yield
    appmod.laya._agent = None


def test_health_public(client):
    r = client.get("/health")
    assert r.status_code == 200
    body = r.json()
    assert body["ok"] is True
    assert body["service"] == "cornerstone-agency-bridge"
    assert "prima" in body and "laya" in body


def test_auth_required(client):
    assert client.get("/prima/sessions").status_code == 401
    assert client.post("/laya/route", json={"text": "x", "bots": []}).status_code == 401
    bad = {"Authorization": "Bearer nope"}
    assert client.get("/prima/sessions", headers=bad).status_code == 401


def test_prima_session_lifecycle(client):
    r = client.post("/prima/sessions", headers=AUTH, json={"cwd": os.environ.get("TEMP", ".")})
    assert r.status_code == 200, r.text
    sid = r.json()["id"]

    r = client.get("/prima/sessions", headers=AUTH)
    assert any(s["id"] == sid for s in r.json()["sessions"])

    r = client.post(f"/prima/sessions/{sid}/prompt", headers=AUTH,
                    json={"message": "Reply with exactly: pong"})
    assert r.status_code == 200 and r.json()["accepted"] is True

    # let the fake worker's events flush
    deadline = time.time() + 5
    while time.time() < deadline:
        st = client.get(f"/prima/sessions/{sid}", headers=AUTH).json()
        if not st["bridge"]["is_streaming"]:
            break
        time.sleep(0.1)
    assert st["bridge"]["is_streaming"] is False

    r = client.get(f"/prima/sessions/{sid}/messages", headers=AUTH)
    msgs = r.json()["messages"]
    assert any(m.get("role") == "assistant" for m in msgs)

    r = client.post(f"/prima/sessions/{sid}/name", headers=AUTH, json={"name": "t"})
    assert r.status_code == 200

    r = client.delete(f"/prima/sessions/{sid}", headers=AUTH)
    assert r.status_code == 200
    assert client.get(f"/prima/sessions/{sid}", headers=AUTH).status_code == 404


def test_prima_409_when_streaming(client):
    r = client.post("/prima/sessions", headers=AUTH, json={})
    sid = r.json()["id"]
    appmod.prima.sessions[sid].is_streaming = True
    r = client.post(f"/prima/sessions/{sid}/prompt", headers=AUTH,
                    json={"message": "hi"})
    assert r.status_code == 409
    r = client.post(f"/prima/sessions/{sid}/prompt", headers=AUTH,
                    json={"message": "hi", "streaming_behavior": "followUp"})
    assert r.status_code == 200
    client.delete(f"/prima/sessions/{sid}", headers=AUTH)


def test_laya_route_mocked(client):
    bots = [
        {"name": "kaori", "description": "coordinator"},
        {"name": "kyoko", "description": "research"},
    ]
    r = client.post("/laya/route", headers=AUTH, json={"text": "check the gateway", "bots": bots})
    assert r.status_code == 200, r.text
    body = r.json()
    assert body["bot"] == "kaori"
    assert "probs" in body and "confidence" in body


def test_laya_triage_mocked(client):
    r = client.post("/laya/triage", headers=AUTH,
                    json={"title": "rm -rf /", "body": "approval needed"})
    assert r.status_code == 200, r.text
    body = r.json()
    assert body["urgency"]["level"] == 1
    assert body["urgency"]["label"] == "soon"
    assert body["needs_human"] == 0.9


def test_laya_status_mocked(client):
    r = client.get("/laya/status", headers=AUTH)
    assert r.status_code == 200
    assert r.json()["loaded"] is True
