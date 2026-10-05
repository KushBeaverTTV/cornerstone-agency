# Starts `hermes serve` (dashboard backend the Cornerstone Agency phone app talks to) on the tailnet IP.
$ErrorActionPreference = 'Stop'
Get-Content 'C:\Cornerstone\Cornerstone_Secrets\agency-dashboard.env' | ForEach-Object {
  if ($_ -match '^([A-Z_]+)=(.*)$') { Set-Item -Path "Env:$($Matches[1])" -Value $Matches[2] }
}
$ip = ''
for ($i=0; $i -lt 60 -and -not $ip; $i++) { try { $ip = (& tailscale ip -4 2>$null | Select-Object -First 1).Trim() } catch {}; if (-not $ip) { Start-Sleep 5 } }
if (-not $ip) { throw 'no tailscale ip' }
$env:HERMES_DASHBOARD_PUBLIC_URL = "http://${ip}:9119"
& "$env:LOCALAPPDATA\hermes\bin\hermes.exe" serve --host $ip --port 9119 --skip-build
