# Cornerstone Agency Desktop Bridge — design

Status: design (M6). Owner: Agency. Lives in `bridge/` of this repo; runs on the HQ desktop
next to `hermes serve` (:9119) and the Hermes voice bridge (:8766).

## Why

Two things the phone wants that have no network surface today:

1. **Prima** — the Windows Prime Agent (`prime-agent` 0.9.5, installed at
   `%LOCALAPPDATA%\Programs\PrimeAgentWindows\prime-agent.cmd`). It only speaks JSONL over
   stdin/stdout (`--mode rpc`). The bridge wraps it so Prima appears in the Agency roster like
   any Hermes bot.
2. **Laya** — the System-1 typed-decision model at `C:\Users\antho\laya-model`
   (`rl_agent_api.RLAgent.predict(state, questions)`). The bridge hosts it so the phone can
   ask fast routing/triage questions (bot dispatch, request urgency, chatter filtering).

## Runtime

- Python 3.11+, FastAPI + uvicorn (same stack as `services/hermes-mobile-gateway`), managed
  with `C:\Cornerstone\tools\uv`. Torch + transformers + safetensors for Laya (CPU is fine —
  measured ~0.8 s/prediction on this box; Laya is lazy-loaded on first call).
- Bind `127.0.0.1:9121` by default. Phone reaches it over `adb reverse tcp:9121 tcp:9121`
  (USB), or LAN/Tailscale when started with `AGENCY_BRIDGE_HOST=0.0.0.0`.
- Start script: `bridge/start.cmd` → `uv run agency-bridge`. Should be added to the stack
  launcher later; not part of M6.

## Auth

Single static bearer token. On first start the bridge generates `agb_<token_urlsafe(24)>`
into `%LOCALAPPDATA%\cornerstone-agency\bridge.env` (owner-only) and prints it once. Phone
stores it in `SecureStore`; every request carries `Authorization: Bearer agb_…`; WS passes
`?token=`. `GET /health` is public and never includes the token. No pairing dance in M6.

## HTTP API

```
GET  /health                    → {ok, service:"cornerstone-agency-bridge", version,
                                    prima:{available, version}, laya:{loaded, checkpoint}}
```

### Prima (`/prima`)

One `prime-agent --mode rpc` subprocess per session ("worker"). Workers survive phone
disconnects; the bridge kills idle workers after 6 h.

```
GET    /prima/sessions                  → {sessions:[{id, name, cwd, is_streaming,
                                            message_count, created_at, last_activity}]}
POST   /prima/sessions {cwd?, name?}    → {id}          (spawns worker; default cwd = C:\Cornerstone)
GET    /prima/sessions/{id}             → get_state passthrough + bridge fields
GET    /prima/sessions/{id}/messages    → get_messages passthrough (UserMessage /
                                           AssistantMessage / ToolResultMessage list)
POST   /prima/sessions/{id}/prompt {message, streaming_behavior?: "steer"|"followUp"}
                                        → {accepted:true} | 409 if streaming and no behavior
POST   /prima/sessions/{id}/abort       → {ok}
POST   /prima/sessions/{id}/name {name} → set_session_name
DELETE /prima/sessions/{id}             → kills worker (sessions stay on disk; prime-agent
                                           persists them itself)
```

### Prima events — `WS /prima/ws?token=`

One socket, all sessions. Frames from the bridge:

```
{"type":"event","session_id":"…","event":{…raw prime-agent RPC event…}}
{"type":"ui_request","session_id":"…","request":{…extension_ui_request…}}
{"type":"worker","session_id":"…","state":"started"|"exited","code":n}
```

Frames from the phone:

```
{"type":"ui_response","session_id":"…","response":{…extension_ui_response…}}
{"type":"ping"}  → {"type":"pong"}
```

Raw prime-agent events the phone must map to the existing `ChatItem` stream
(`ChatKind` from `ChatRepository`):

| prime-agent event                         | ChatItem                                           |
|-------------------------------------------|----------------------------------------------------|
| `agent_start` / `turn_start`              | streaming=true, statusLine "working…"              |
| `message_start` (assistant)               | new Assistant bubble                               |
| `message_update` → `assistantMessageEvent.type` `text_delta` | append to Assistant text                  |
| `message_update` → `assistantMessageEvent.type` `thinking_delta` | Thinking bubble (before the Assistant bubble) |
| `tool_execution_start`                    | Tool row "▶ <tool>" before the Assistant bubble    |
| `tool_execution_end`                      | flip to "✓ <tool>" (or "✗" on error)               |
| `message_end`                             | finalize Assistant text                            |
| `turn_end` / `agent_end`                  | streaming=false, clear statusLine                  |

Observed on the wire (prime-agent 0.9.5): `message_update` also emits
`assistantMessageEvent` subtypes `text_start`/`text_end`, `thinking_start`/`thinking_end`
— safely ignorable; deltas carry `delta` and `contentIndex` fields.
| `extension_ui_request` `confirm`/`select`/`input` | Request card (Approval-style for confirm, Clarify-style for select/input) → `ui_response` |

### Laya (`/laya`)

```
GET  /laya/status                       → {loaded, checkpoint, device, warm_ms}
POST /laya/predict {state, questions}   → raw RLAgent.predict output (generic escape hatch)
POST /laya/route {text, bots:[{name, description}]}
     → {bot, probs:{name:p}, confidence}
       (builds one `choice` question: instructions "Which agent should handle this
        message?", criteria = {name: description}; adds "none" only if bots < 2)
POST /laya/triage {title, body}
     → {urgency:{level:0|1|2, label:"later"|"soon"|"now", probs}, needs_human:p}
       (one `score` question over ["later","soon","now"], one `noul`
        "Does this require a human to act right now?")
```

Laya inputs are the caller's text only — the bridge never fetches from Hermes itself, so it
has no Hermes credentials.

## Phone integration (M6 scope on the Android side)

- More → **Bridge** card: host:port (default `127.0.0.1:9121`), token field, "Test" button
  (`/health`). Stored in `SecureStore`.
- When the bridge is healthy and `prima.available`, the Bots roster shows **Prima** (violet
  `defaultViolet` avatar "P", subtitle "Prime Agent · <version>") under a `DESKTOP` kicker.
  Tap → `PrimaChatScreen` (reuses `ChatScreen` UI with a `PrimaChatRepository` producing the
  same `ChatItem` flow). Long-press → sessions list (`/prima/sessions`).
- **Dispatch** composer on the Bots tab (only when Laya is `loaded` or loadable): one text
  field "Ask anyone…". On submit: `/laya/route` with the current roster → show a chip
  "→ Kyoko (82%)" with a tap-to-change picker → send into that bot's most recent session.
  If confidence < 0.55 always show the picker first.
- Request notifications (M7): before posting, call `/laya/triage` when the bridge is
  reachable; `level==2 || needs_human>0.7` → heads-up high priority, else silent channel.
  Failure/unreachable → fall back to today's behaviour.

## Non-goals (M6)

No pairing codes, no TLS, no multi-user, no Prima ACP mode, no image attachments, no
Laya fine-tuning. The bridge is a thin, honest adapter.
