# Keeps the phone's USB port forwards (Agency app -> PC) alive; adb restarts and replugs drop them.
$adb = 'C:\Cornerstone\tools\android-sdk\platform-tools\adb.exe'
while ($true) {
  try {
    $dev = (& $adb devices 2>$null | Select-String '\tdevice$')
    if ($dev) {
      $have = (& $adb reverse --list 2>$null) -join ' '
      foreach ($p in 9119,9121,8766) { if ($have -notmatch "tcp:$p ") { & $adb reverse "tcp:$p" "tcp:$p" | Out-Null } }
    }
  } catch {}
  Start-Sleep 10
}
