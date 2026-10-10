# Auto-connect — the phone is always attached to the PC, zero taps

## Goal
Open Kaori anywhere (USB, home wifi, cellular) and it is already connected to the PC.
No QR, no pairing code, no IP, no username/password, no clipboard. Ever.

## Non-goals
- A public login page. Only Kush's own devices ever reach the PC (Tailscale = private network).
- LAN-only discovery (mDNS). Tailscale already covers "same wifi" with a direct connection.

## Design (three pieces)

### 1. Network: Tailscale (free personal plan), one login per device, once
- PC: `winget install Tailscale.Tailscale`, sign in once. The PC gets a stable 100.x.y.z address.
- Phone: Tailscale app (already installed), sign in to the same account once.
- Services keep their current design: bind the tailnet IP when Tailscale is up, 127.0.0.1 otherwise;
  `adb reverse` still covers the USB cable.

### 2. Pairing baked into the APK (build-time, never committed)
- `ops/windows/write-pairing-env.py` writes `C:\Cornerstone\Cornerstone_Secrets\agency-pairing.env`
  from the bridge token (`%LOCALAPPDATA%\cornerstone-agency\bridge.env`), the voice token
  (`agency-voice.env`), and the current host list (tailnet IP, 127.0.0.1).
- `app/build.gradle.kts` reads that file into `BuildConfig.AGENCY_PC_HOSTS / AGENCY_BRIDGE_TOKEN /
  AGENCY_VOICE_TOKEN / AGENCY_PC_NAME` (all empty when the file is missing -> old behaviour).
- `ops/windows/build-phone.cmd`: write-pairing-env -> gradle assembleDebug -> adb install -> adb reverse.

### 3. The app keeps its own address book and reconnects itself
- `data/AutoConnect.kt`: candidate hosts = stored address book ∪ baked hosts. Probe every
  candidate's `/health` with the token in parallel (4 s), pick the first reachable by priority
  (USB loopback first, then tailnet). Pair with it through the existing `pair()` path.
- Runs: on cold start when nothing is stored or the stored connection fails to verify; on every
  foreground when the socket is not OPEN; when the gateway socket has been RECONNECTING > 20 s.
- After every successful connection the app fetches `GET /pairing` from the bridge (new,
  bearer-auth) and stores the fresh host list + voice token, so a tailnet IP learned over USB
  works later on cellular without a rebuild.
- Auto-pairing turns "Stay connected" on, so the foreground service keeps the socket alive.
- Me tab shows "Auto-connect: <PC name> via USB / Tailscale" with a "Reconnect now" button.

## Acceptance
1. Fresh install (`adb uninstall` + install): app opens straight into Chats, no pairing screen.
2. Pull the USB cable with Tailscale up on both: app reconnects by itself within 30 s.
3. Kill and restart the bridge: app reconnects by itself.
4. `./gradlew testDebugUnitTest` passes with new tests for candidate ordering + env parsing.
5. `pytest` in bridge/ passes with a test for `/pairing` (401 without token, hosts with token).

## Verification commands
- Android: `cmd /c ops\windows\build-phone.cmd` (runs tests + assemble + install)
- Bridge: `C:\Cornerstone\tools\uv.exe run --frozen --extra test pytest -q` (in bridge/)
- Phone: `adb logcat -d | grep -E "AutoConnect|FATAL"`; bridge `/hermes/status` shows `phones >= 1`

## Rejected
- Cloudflare tunnel / public URL: exposes a login page to the internet for no benefit.
- Committing tokens into git: no — they stay in Cornerstone_Secrets and are injected at build time.
- mDNS discovery: Tailscale already gives a stable name on every network incl. same wifi.
