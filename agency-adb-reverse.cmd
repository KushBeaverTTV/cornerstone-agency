@echo off
REM Cornerstone Agency - phone-side USB reverse tunnels.
REM Keeps the phone able to reach the PC services over USB.
REM Usage: call C:\Cornerstone\projects\cornerstone-agency\agency-adb-reverse.cmd
set "ADB=C:\Cornerstone\tools\android-sdk\platform-tools\adb.exe"
%ADB% reverse tcp:9119 tcp:9119
%ADB% reverse tcp:9121 tcp:9121
%ADB% reverse tcp:8766 tcp:8766
%ADB% reverse --list
