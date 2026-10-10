@echo off
REM Cornerstone Agency - Android build environment.
REM Usage: call C:\Cornerstone\projects\cornerstone-agency\agency-env.cmd
set "JAVA_HOME=C:\Cornerstone\tools\jdk17"
set "ANDROID_HOME=C:\Cornerstone\tools\android-sdk"
set "ANDROID_USER_HOME=C:\Cornerstone\tools\android-user-home"
set "GRADLE_USER_HOME=C:\Cornerstone\tools\gradle-home"
set "PATH=C:\Cornerstone\tools\jdk17\bin;C:\Cornerstone\tools\android-sdk\platform-tools;%PATH%"
