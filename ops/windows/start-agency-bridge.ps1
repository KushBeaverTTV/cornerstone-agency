# Starts the Cornerstone Agency desktop bridge (Prima + Laya) on port 9121.
# Binds the tailnet IP when Tailscale is up; otherwise 127.0.0.1 (phone reaches it over `adb reverse`).
$ip = ''
$ts = (Get-Command tailscale -ErrorAction SilentlyContinue).Source; if (-not $ts -and (Test-Path 'C:\Program Files\Tailscale\tailscale.exe')) { $ts = 'C:\Program Files\Tailscale\tailscale.exe' }
if ($ts) {
  for ($i=0; $i -lt 60 -and -not $ip; $i++) { try { $ip = (& $ts ip -4 2>$null | Select-Object -First 1).Trim() } catch {}; if (-not $ip) { Start-Sleep 5 } }
}
if (-not $ip) { $ip = '127.0.0.1' }
$env:AGENCY_BRIDGE_HOST = $ip
Set-Location 'C:\Cornerstone\projects\cornerstone-agency\bridge'
$uv = 'C:\Cornerstone\tools\uv\uv.exe'
if (-not (Test-Path $uv)) { $uv = 'C:\Cornerstone\tools\uv.exe' }
& $uv run --frozen agency-bridge
