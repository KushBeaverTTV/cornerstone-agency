"""Fake prime-agent worker for tests: JSONL stdin/stdout.

Responds to commands with type:"response" frames; on `prompt` emits a canned
agent_start → message_start → text_delta("pong") → message_end → agent_end
sequence; on `extension_ui_response` echoes nothing (just accepts).
Also answers `--version` so probe_version works.
"""
import json
import sys

if "--version" in sys.argv:
    print("prime-agent 0.0.0-fake")
    sys.exit(0)

state = {
    "model": {"provider": "fake", "modelId": "fake-1"},
    "isStreaming": False,
    "sessionId": "fake-session",
    "messageCount": 0,
}
messages = []
seq = 0

for raw in sys.stdin:
    raw = raw.strip()
    if not raw:
        continue
    try:
        msg = json.loads(raw)
    except json.JSONDecodeError:
        continue
    t = msg.get("type")
    rid = msg.get("id")

    def respond(ok=True, data=None):
        out = {"type": "response", "command": t, "success": ok}
        if rid is not None:
            out["id"] = rid
        if data is not None:
            out["data"] = data
        print(json.dumps(out), flush=True)

    if t == "prompt":
        text = msg.get("message", "")
        respond(True)
        state["isStreaming"] = True
        print(json.dumps({"type": "agent_start"}), flush=True)
        print(json.dumps({"type": "turn_start"}), flush=True)
        amsg = {"role": "assistant", "content": [{"type": "text", "text": ""}]}
        print(json.dumps({"type": "message_start", "message": amsg}), flush=True)
        print(json.dumps({
            "type": "message_update",
            "message": amsg,
            "assistantMessageEvent": {
                "type": "text_delta", "contentIndex": 0, "delta": "pong",
                "partial": amsg,
            },
        }), flush=True)
        done = {"role": "assistant", "content": [{"type": "text", "text": "pong"}]}
        print(json.dumps({"type": "message_end", "message": done}), flush=True)
        print(json.dumps({"type": "agent_end", "messages": [done]}), flush=True)
        state["isStreaming"] = False
        state["messageCount"] += 2
        messages.append({"role": "user", "content": [{"type": "text", "text": text}]})
        messages.append(done)
    elif t == "get_state":
        respond(True, dict(state))
    elif t == "get_messages":
        respond(True, {"messages": list(messages)})
    elif t == "get_available_models":
        respond(True, {"models": [
            {"provider": "anthropic", "id": "claude-sonnet-5-5", "name": "Claude Sonnet 5.5"},
            {"provider": "anthropic", "id": "claude-opus-4-8", "name": "Claude Opus 4.8"},
        ]})
    elif t == "set_model":
        state["model"] = {"provider": msg.get("provider"), "id": msg.get("modelId")}
        respond(True, state["model"])
    elif t == "abort":
        respond(True)
    elif t == "set_session_name":
        state["sessionName"] = msg.get("name", "")
        respond(True)
    elif t == "new_session":
        respond(True, {"cancelled": False})
    elif t == "extension_ui_response":
        pass  # acknowledged silently
    else:
        respond(True)
