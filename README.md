# Cornerstone Agency

Android client for Kush's Hermes agents (Kaori and the other bots) plus Prima, the Windows
Prime Agent. Package `com.cornerstone.agency`, Kotlin + Jetpack Compose, min SDK 26, target 35.

Not part of Cornerstone HQ. Private repo: `KushBeaverTTV/cornerstone-agency`.

## Layout

| Path | What |
|---|---|
| `app/` | The Android app |
| `app/src/main/java/com/cornerstone/agency/data/` | Sockets, REST, chat state (`ChatTranscript` → `ChatRequestCards` → `ChatRepository`), Prima, cache, pairing |
| `app/src/main/java/com/cornerstone/agency/ui/screens/` | Compose screens; chat is split into `ChatScreen`, `ChatMessages`, `ChatMessageActions`, `ChatAttachments` |
| `app/src/main/java/com/cornerstone/agency/service/` | Foreground keep-alive, notifications, catch-up worker, boot receiver |
| `bridge/` | Desktop bridge (FastAPI, port 9121): Hermes relay, Prima, Laya, services board, pairing QR |
| `voice-bridge/` | Voice bridge (port 8766) |
| `ops/windows/` | Launchers, USB forwarder, watchdog + installer, pairing script |
| `docs/BRIDGE.md` | Bridge design and API (start here for anything PC-side) |
| `docs/DESIGN_AUDIT.md` | UI direction and what is done |

## How the phone reaches the PC

```
phone ──(Tailscale or USB+adb reverse)──► bridge :9121 ──/hermes──► Hermes desktop backend (live)
                                             │                    └─► hermes serve :9119 (fallback)
                                             ├─/prima──► prime-agent workers
                                             └─/services, /laya
phone ──► voice bridge :8766
```

Sign in by pairing: run `ops/windows/pair-agency-phone.ps1` on the PC and scan the QR with the
phone camera (or paste the `cornerstone-agency://pair?...` link on the Connect screen). The old
direct dashboard login (URL + password) still works but does not share live turns.

## Build

Toolchain lives in `C:\Cornerstone\tools` (JDK 21, Android SDK).

```bash
export JAVA_HOME=C:/Cornerstone/tools/jdk-21 ANDROID_HOME=C:/Cornerstone/tools/android-sdk
./gradlew testDebugUnitTest assembleDebug          # tests + debug APK
./gradlew assembleRelease                          # signed release (needs the env below)
```

Release signing reads `KBP_RELEASE_KEYSTORE_PATH`, `KBP_RELEASE_KEYSTORE_PASSWORD`,
`KBP_RELEASE_KEY_ALIAS`, `KBP_RELEASE_KEY_PASSWORD` from the environment
(`C:\Cornerstone\Cornerstone_Secrets\signing.env`). Never commit them. Release APKs go in
`dist/` (git-ignored). Debug and release share the application id but not the signature, so
switching between them needs an uninstall (pair again afterwards).

Bridge:

```bash
cd bridge && uv run --extra test pytest tests -q
```

CI (`.github/workflows/ci.yml`) runs the app unit tests + debug build and the bridge tests on
every push to `main`.

## Background delivery

- **Stay connected** keeps the socket in a foreground service. On Android 14+ its type is
  `remoteMessaging` (no daily cap; Android 15 caps `dataSync` at 6 h/day). `onTimeout` stops
  cleanly if the system ever ends it, and `BootReceiver` restores it after a reboot or update.
- **Catch-up worker** (`CatchUpWorker`, every ~15 min): for chats waiting on a reply to
  something sent from the phone (`ReplyWatch`), it checks the state DB over REST and notifies
  when the reply has landed, even if the socket was killed.

## PC services

`ops/windows/install-agency-watchdog.ps1` installs the `Cornerstone-Agency-Watchdog` task (every
5 min, restarts dashboard / bridge / voice bridge when they stop answering). The app's Me screen
shows the same board with Restart buttons.
