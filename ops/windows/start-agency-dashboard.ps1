# Starts `hermes serve` (dashboard backend the Cornerstone Agency phone app talks to).
# Binds the tailnet IP when Tailscale is up; otherwise 127.0.0.1 (phone reaches it over `adb reverse`).
$ErrorActionPreference = 'Stop'
Get-Content 'C:\Cornerstone\Cornerstone_Secrets\agency-dashboard.env' | ForEach-Object {
  if ($_ -match '^([A-Z_]+)=(.*)$') { Set-Item -Path "Env:$($Matches[1])" -Value $Matches[2] }
}
$ip = ''
$ts = (Get-Command tailscale -ErrorAction SilentlyContinue).Source; if (-not $ts -and (Test-Path 'C:\Program Files\Tailscale\tailscale.exe')) { $ts = 'C:\Program Files\Tailscale\tailscale.exe' }
if ($ts) {
  for ($i=0; $i -lt 60 -and -not $ip; $i++) { try { $ip = (& $ts ip -4 2>$null | Select-Object -First 1).Trim() } catch {}; if (-not $ip) { Start-Sleep 5 } }
}
if (-not $ip) { $ip = '127.0.0.1' }
$env:HERMES_DASHBOARD_PUBLIC_URL = "http://${ip}:9119"
$hermes = "$env:USERPROFILE\.hermes\bin\hermes.exe"
if (-not (Test-Path $hermes)) { $hermes = "$env:LOCALAPPDATA\hermes\bin\hermes.exe" }
& $hermes serve --host $ip --port 9119 --skip-build
