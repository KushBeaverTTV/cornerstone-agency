@echo off
powershell -NoProfile -ExecutionPolicy Bypass -File "%LOCALAPPDATA%\hermes\scripts\launch-agency-dashboard.ps1"
powershell -NoProfile -ExecutionPolicy Bypass -File "%LOCALAPPDATA%\hermes\scripts\launch-agency-bridge.ps1"
powershell -NoProfile -ExecutionPolicy Bypass -File "%LOCALAPPDATA%\hermes\scripts\launch-agency-usb-forward.ps1"
powershell -NoProfile -ExecutionPolicy Bypass -File "%LOCALAPPDATA%\hermes\scripts\launch-agency-voice.ps1"
powershell -NoProfile -ExecutionPolicy Bypass -File "%LOCALAPPDATA%\hermes\scripts\launch-agency-adb-reverse.ps1"
