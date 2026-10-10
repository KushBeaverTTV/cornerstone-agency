@echo off
rem Cornerstone Agency voice bridge: ws://<host>:8766/v1/audio  (phone mic -> whisper -> Hermes -> edge-tts -> phone)
setlocal
cd /d "%~dp0"
set "PYTHONIOENCODING=utf-8"
set "HERMES_VOICE_BRIDGE_PORT=8766"
set "FFMPEG_BINARY=%USERPROFILE%\.hermes\tools\ffmpeg-9.0.1-win32-x64\bin\ffmpeg.exe"
for /f "tokens=*" %%i in ('tailscale ip -4 2^>nul') do if not defined TSIP set "TSIP=%%i"
if defined TSIP set "AGENCY_VOICE_EXTRA_HOSTS=%TSIP%"
rem dashboard is bound to the tailnet IP only
if defined TSIP set "HERMES_AGENT_URL=http://%TSIP%:9119"
".venv\Scripts\python.exe" agency_voice_bridge.py %*
