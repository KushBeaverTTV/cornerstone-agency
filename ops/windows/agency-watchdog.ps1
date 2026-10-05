# Cornerstone Agency watchdog: relaunches a PC-side Agency service that died.
# Runs every 5 minutes from the "Cornerstone-Agency-Watchdog" scheduled task (hidden, via
# agency-watchdog.vbs). Installed by ops/windows/install-agency-watchdog.ps1.
#
# A service is relaunched when its port is not listening, or when it has answered its health
# URL badly three runs in a row (a hung process). Log: %LOCALAPPDATA%\hermes\logs\agency-watchdog.log,
# capped at ~256 KB (trimmed to the newest 400 lines).
$ErrorActionPreference = 'SilentlyContinue'
$scripts = Join-Path $env:LOCALAPPDATA 'hermes\scripts'
$logDir = Join-Path $env:LOCALAPPDATA 'hermes\logs'
$log = Join-Path $logDir 'agency-watchdog.log'
$stateFile = Join-Path $logDir 'agency-watchdog.state.json'
New-Item -ItemType Directory -Force -Path $logDir | Out-Null

function Write-Log([string]$msg) {
    $line = "{0} {1}" -f (Get-Date -Format 'yyyy-MM-dd HH:mm:ss'), $msg
    Add-Content -Path $log -Value $line -Encoding utf8
    if ((Get-Item $log).Length -gt 256KB) {
        $keep = Get-Content $log -Tail 400
        Set-Content -Path $log -Value $keep -Encoding utf8
    }
}

# Give the login startup script time to bring everything up first.
$uptime = (Get-Date) - (Get-CimInstance Win32_OperatingSystem).LastBootUpTime
if ($uptime.TotalSeconds -lt 240) { exit 0 }

$ip = ''
try { $ip = (& tailscale ip -4 2>$null | Select-Object -First 1).Trim() } catch {}
if (-not $ip) { Write-Log 'skip: no tailscale ip'; exit 0 }

$services = @(
    @{ name = 'dashboard'; port = 9119; health = "http://${ip}:9119/api/health"; launch = 'launch-agency-dashboard.ps1' },
    @{ name = 'bridge';    port = 9121; health = "http://${ip}:9121/health";     launch = 'launch-agency-bridge.ps1' },
    @{ name = 'voice';     port = 8766; health = "http://${ip}:8766/health";     launch = 'launch-agency-voice.ps1' }
)

$state = @{}
if (Test-Path $stateFile) {
    try { (Get-Content $stateFile -Raw | ConvertFrom-Json).PSObject.Properties | ForEach-Object { $state[$_.Name] = [int]$_.Value } } catch {}
}

foreach ($s in $services) {
    $listening = Get-NetTCPConnection -LocalAddress $ip -LocalPort $s.port -State Listen -ErrorAction SilentlyContinue
    $healthy = $false
    if ($listening) {
        try {
            $r = Invoke-WebRequest -Uri $s.health -TimeoutSec 8 -UseBasicParsing
            $healthy = $r.StatusCode -lt 500
        } catch {
            # 401/404 still prove the process answers.
            if ($_.Exception.Response) { $healthy = [int]$_.Exception.Response.StatusCode -lt 500 }
        }
    }
    $script = Join-Path $scripts $s.launch
    if (-not $listening) {
        Write-Log ("{0}: port {1} not listening -> relaunch" -f $s.name, $s.port)
        & powershell -NoProfile -ExecutionPolicy Bypass -File $script
        $state[$s.name] = 0
        continue
    }
    if ($healthy) { $state[$s.name] = 0; continue }
    $state[$s.name] = 1 + [int]$state[$s.name]
    Write-Log ("{0}: unhealthy ({1}/3)" -f $s.name, $state[$s.name])
    if ($state[$s.name] -ge 3) {
        foreach ($c in $listening) { Stop-Process -Id $c.OwningProcess -Force -ErrorAction SilentlyContinue }
        Start-Sleep -Seconds 2
        Write-Log ("{0}: hung -> killed and relaunched" -f $s.name)
        & powershell -NoProfile -ExecutionPolicy Bypass -File $script
        $state[$s.name] = 0
    }
}
$state | ConvertTo-Json | Set-Content -Path $stateFile -Encoding utf8
