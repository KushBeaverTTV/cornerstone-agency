@echo off
setlocal
cd /d "%~dp0"
C:\Cornerstone\tools\uv\uv.exe run agency-bridge
exit /b %ERRORLEVEL%
