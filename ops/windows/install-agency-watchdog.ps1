# Installs the Cornerstone Agency PC scripts and the watchdog scheduled task (per-user, no admin).
#   powershell -NoProfile -ExecutionPolicy Bypass -File ops\windows\install-agency-watchdog.ps1
$ErrorActionPreference = 'Stop'
$here = Split-Path -Parent $MyInvocation.MyCommand.Path
$scripts = Join-Path $env:LOCALAPPDATA 'hermes\scripts'
New-Item -ItemType Directory -Force -Path $scripts | Out-Null

# Launch/start scripts, the USB forwarder, the watchdog and the pairing helper.
Get-ChildItem $here -File | Where-Object { $_.Extension -in '.ps1', '.py' -and $_.Name -ne 'install-agency-watchdog.ps1' } |
    ForEach-Object { Copy-Item $_.FullName $scripts -Force }
$vbs = Join-Path $scripts 'agency-watchdog.vbs'
# wscript runs PowerShell with no console window flashing every five minutes.
@"
CreateObject("WScript.Shell").Run "powershell -NoProfile -ExecutionPolicy Bypass -File ""$scripts\agency-watchdog.ps1""", 0, False
"@ | Set-Content -Path $vbs -Encoding ascii

$action = New-ScheduledTaskAction -Execute 'wscript.exe' -Argument "`"$vbs`""
$trigger = New-ScheduledTaskTrigger -Once -At (Get-Date).AddMinutes(1) -RepetitionInterval (New-TimeSpan -Minutes 5)
$settings = New-ScheduledTaskSettingsSet -AllowStartIfOnBatteries -DontStopIfGoingOnBatteries -StartWhenAvailable `
    -ExecutionTimeLimit (New-TimeSpan -Minutes 4) -MultipleInstances IgnoreNew
Register-ScheduledTask -TaskName 'Cornerstone-Agency-Watchdog' -Action $action -Trigger $trigger -Settings $settings `
    -Description 'Relaunches Cornerstone Agency PC services (dashboard 9119, bridge 9121, voice 8766) if they die.' -Force | Out-Null
Write-Host "Installed Cornerstone-Agency-Watchdog (every 5 min) -> $scripts\agency-watchdog.ps1"
