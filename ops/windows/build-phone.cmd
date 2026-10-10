@echo off
REM Cornerstone Agency - build the phone app with the PC baked in, and put it on the plugged-in phone.
REM   1. agency-bridge pair --write-env   -> Cornerstone_Secrets\agency-pairing.env (hosts + tokens, never committed)
REM   2. gradlew testDebugUnitTest assembleDebug
REM   3. adb install -r + reverse tunnels (skipped when no phone is attached)
REM Usage: ops\windows\build-phone.cmd [--no-install]
setlocal
set "ROOT=C:\Cornerstone\projects\cornerstone-agency"
set "ADB=C:\Cornerstone\tools\android-sdk\platform-tools\adb.exe"
set "UV=C:\Cornerstone\tools\uv\uv.exe"
if not exist "%UV%" set "UV=C:\Cornerstone\tools\uv.exe"
call "%ROOT%\agency-env.cmd"

echo [1/3] pairing env
pushd "%ROOT%\bridge"
"%UV%" run --frozen agency-bridge pair --write-env || (popd & exit /b 1)
popd

echo [2/3] build + unit tests
pushd "%ROOT%"
call gradlew.bat testDebugUnitTest assembleDebug --offline -q || (popd & exit /b 1)
popd

if "%~1"=="--no-install" goto :done
echo [3/3] install on the phone
"%ADB%" get-state >nul 2>&1 || (echo no phone attached - skipping install & goto :done)
"%ADB%" install -r "%ROOT%\app\build\outputs\apk\debug\app-debug.apk" || exit /b 1
for %%p in (9119 9121 8766) do "%ADB%" reverse tcp:%%p tcp:%%p >nul
"%ADB%" shell monkey -p com.cornerstone.agency -c android.intent.category.LAUNCHER 1 >nul 2>&1
echo installed and launched

:done
echo done
