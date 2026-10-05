# Cornerstone Agency Desktop Bridge — design

Status: shipped, bridge 0.2.0 (2026-10-05). Owner: Agency. Lives in `bridge/` of this repo; runs
on the HQ desktop next to `hermes serve` (:9119) and the Hermes voice bridge (:8766).

What changed since M6 (details in the sections below):

- **Hermes relay** (`/hermes/*`): the phone talks to Hermes *through* the bridge, which forwards
  to the backend the Hermes desktop app itself spawned. Live turns are shared, so a reply typed
  on the desktop streams token-by-token onto the phone and vice versa.
- **QR pairing** (`agency-bridge pair` / `ops/windows/pair-agency-phone.ps1`): one scan signs the
  phone into the relay, the bridge and the voice bridge. No password on the phone.
- **Service board** (`/services`) + `Cornerstone-Agency-Watchdog` scheduled task.
- **Prima**: reopen saved conversations (`/prima/saved`), per-session model, image/file prompts.

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
- Live install binds the PC's tailnet IP (`100.84.54.112:9121`) via
  `ops/windows/launch-agency-bridge.ps1`. Over USB the loopback forwarder
  (`agency-usb-forward.py`) + `adb reverse tcp:9121 tcp:9121` gives the phone `127.0.0.1:9121`.
- All three services start at login from `Cornerstone-Agency-Backend.cmd`. Copies of every
  launcher live in `ops/windows/`; `install-agency-watchdog.ps1` copies them into
  `%LOCALAPPDATA%\hermes\scripts` and registers the watchdog task.

### Watchdog

`Cornerstone-Agency-Watchdog` runs `agency-watchdog.ps1` every 5 minutes (per-user task, hidden
window). A service is relaunched when its port is not listening or after 3 consecutive failed
health checks. Log: `%LOCALAPPDATA%\hermes\logs\agency-watchdog.log`, capped at 256 KB
(trimmed to the newest 400 lines).

## Auth

Single static bearer token. On first start the bridge generates `agb_<token_urlsafe(24)>`
into `%LOCALAPPDATA%\cornerstone-agency\bridge.env` (owner-only). Phone stores it in
`SecureStore`; every request carries an `Authorization: Bearer` header with it; sockets pass
`?token=` or a one-time `?ticket=` (60 s). `GET /health` is public and never includes the token.

### Pairing

`agency-bridge pair` (or `ops/windows/pair-agency-phone.ps1`) writes a local HTML page with a QR
for `cornerstone-agency://pair?v=1&bridge=<ip>:9121&token=<t>&voice=<ip>:8766&voice_token=<t>&name=<pc>`
and opens it. The page is a temp file and is never served. The phone camera opens the link in
the app (`MainActivity.handlePairing` → `AppViewModel.pair`), which verifies the relay before
saving anything. The link can also be pasted on the Connect screen.

### Hermes relay (`/hermes/*`)

The phone never holds a Hermes credential. The relay picks its upstream on every request:

1. `desktop-serve` — the backend the Hermes desktop app spawned (port + session token from
   `~/.local/state/hermes/gateway-locks/host-desktop-serve.{json,token}`, header
   `X-Hermes-Session-Token`). Live turn streams only exist in this process.
2. `serve` fallback — the always-on dashboard on :9119 (basic login → cookies / WS tickets),
   used when the desktop app is closed. Turns made there are caught up from the state DB.

```
GET  /hermes/status              → {backend:"desktop"|"serve", base, pid, phones}
POST /hermes/api/auth/ws-ticket  → {ticket}  (one-time, for /hermes/api/ws)
WS   /hermes/api/ws?ticket=…     → transparent JSON-RPC pipe to the upstream socket
ANY  /hermes/{path}              → forwarded REST (/api/profiles, /api/sessions, /api/media …)
```

`/api/auth/me` does not exist on the desktop backend, so the app proves a relay sign-in with
`/api/profiles` (`ConnectionRepository.verify`).

### How the phone shares a live chat

`session.resume` through the relay joins the desktop's live session as another viewer. If a
turn is running, `inflight` carries every delta so far; `TranscriptMapping.joinLiveTurn`
subtracts the interim text already persisted and continues the current bubble. Turns the
phone did not start ("peer turns") fetch their prompt from the state DB and stream live.
Each finished turn is swapped for its persisted rows (durable row ids, needed by edit /
regenerate / react / branch); a 3 s DB poll adds rows written elsewhere (Telegram, cron) and
rebuilds when rows disappear (undo / rewind on the desktop).

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
POST   /prima/sessions {cwd?, name?, session_path?}
                                        → {id}          (spawns worker; default cwd = C:\Cornerstone;
                                                         session_path reopens a saved conversation)
GET    /prima/saved?limit=              → {sessions:[{path, session_id, title, preview, cwd,
                                            message_count, model, updated_at, open_id}]}
                                          (~/.prime/agent/sessions/*.jsonl, newest first; desktop
                                           and phone conversations alike)
GET    /prima/sessions/{id}/models      → get_available_models
POST   /prima/sessions/{id}/model {provider, model_id}
                                        → set_model (switches this session AND becomes Prima's
                                           defaultModel for new sessions — Prima persists it)
GET    /prima/sessions/{id}             → get_state passthrough + bridge fields
GET    /prima/sessions/{id}/messages    → get_messages passthrough (UserMessage /
                                           AssistantMessage / ToolResultMessage list)
POST   /prima/sessions/{id}/prompt {message, streaming_behavior?: "steer"|"followUp",
                                        images?: [{data(base64), mime_type}],
                                        files?:  [{name, data(base64)}]}
                                        → {accepted:true} | 409 if streaming and no behavior
                                          (images ride as ImageContent; files are saved to
                                           ~/.prime/agent/uploads/ and referenced in the message)
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

### Services (`/services`)

```
GET  /services                  → {services:[{name, label, port, ok, pid, restartable}],
                                   hermes:{backend, phones}, watchdog:[last log lines]}
POST /services/{name}/restart   → relaunch via its ops/windows launcher
                                  (restarting "bridge" exits after replying; the launcher
                                   brings it back)
```

Listener detection filters by host: the USB forwarder binds the same ports on 127.0.0.1.

## Phone integration (M6 scope on the Android side)

- More → **Bridge** card: host:port (default `127.0.0.1:9121`), token field, "Test" button
  (`/health`). Stored in `SecureStore`.
- When the bridge is healthy and `prima.available`, the Chats home shows **Prima** (violet
  `defaultViolet` keystone, subtitle "Prime Agent on this PC") under a `DESKTOP` kicker.
  Tap → `PrimaChatScreen` (reuses `ChatScreen` UI with a `PrimaChatRepository` producing the
  same `ChatItem` flow). Long-press → sessions list (`/prima/sessions`).
- **Dock composer** on Chats ("Ask anyone…", `ChatsScreen.kt`): when the bridge is up and
  there is more than one target, submit calls `/laya/route` with the roster descriptions.
  `Routing.decide` (`data/Routing.kt`) accepts the pick only when `probs[pick] ≥ 0.30` and it
  beats the runner-up by `≥ 0.05`; otherwise a picker sheet asks "Who should take this?".
  A hand-picked target skips routing. Routing errors fall back to the default bot.
- Request notifications (M7): before posting, call `/laya/triage` when the bridge is
  reachable; `level==2 || needs_human>0.7` → heads-up high priority, else silent channel.
  Failure/unreachable → fall back to today's behaviour.

Since 0.2.0 the Me screen also shows the **PC services** board (status + restart), and the
Prima sessions screen lists saved conversations from `/prima/saved` for reopening.

## Non-goals

No TLS (Tailscale carries the traffic), no multi-user, no Prima ACP mode, no Laya
fine-tuning. The bridge is a thin, honest adapter.
