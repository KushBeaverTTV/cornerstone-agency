"""Bridge configuration + bearer-token bootstrap.

Token lives at %LOCALAPPDATA%\\cornerstone-agency\\bridge.env as
AGENCY_BRIDGE_TOKEN=agb_… — generated on first start, owner-only file.
"""
from __future__ import annotations

import logging
import os
import secrets
from pathlib import Path

log = logging.getLogger("agency_bridge")

LOCALAPPDATA = Path(os.environ.get("LOCALAPPDATA", Path.home() / "AppData" / "Local"))
TOKEN_FILE = LOCALAPPDATA / "cornerstone-agency" / "bridge.env"
TOKEN_KEY = "AGENCY_BRIDGE_TOKEN"
# Download-only secret for fetching the release APK in a phone browser (GET /app/latest.apk?key=).
DOWNLOAD_KEY = "AGENCY_DOWNLOAD_KEY"


class Config:
    def __init__(self) -> None:
        self.host = os.environ.get("AGENCY_BRIDGE_HOST", "127.0.0.1").strip() or "127.0.0.1"
        self.port = int(os.environ.get("AGENCY_BRIDGE_PORT", "9121") or 9121)
        self.prima_cmd = os.environ.get(
            "AGENCY_PRIMA_CMD",
            str(LOCALAPPDATA / "Programs" / "PrimeAgentWindows" / "prime-agent.cmd"),
        ).strip()
        self.laya_dir = os.environ.get(
            "AGENCY_LAYA_DIR", r"C:\Users\antho\laya-model"
        ).strip()
        cwd = os.environ.get("AGENCY_PRIMA_CWD", r"C:\Cornerstone").strip()
        # A missing default folder would make every new Prima session fail to spawn.
        self.default_cwd = cwd if os.path.isdir(cwd) else str(Path.home())
        self.worker_idle_s = float(os.environ.get("AGENCY_PRIMA_IDLE_S", str(6 * 3600)))
        self.token = os.environ.get(TOKEN_KEY, "").strip() or self._bootstrap_token()
        self.download_key = os.environ.get(DOWNLOAD_KEY, "").strip() or self._env_value(DOWNLOAD_KEY, "dl_")

    def _env_value(self, key: str, prefix: str) -> str:
        """Read `key` from bridge.env, appending a fresh random value when it is missing."""
        try:
            for line in TOKEN_FILE.read_text(encoding="utf-8").splitlines():
                if line.startswith(key + "="):
                    val = line.split("=", 1)[1].strip()
                    if val:
                        return val
        except OSError:
            pass
        val = prefix + secrets.token_urlsafe(18)
        try:
            with TOKEN_FILE.open("a", encoding="utf-8") as f:
                f.write(f"{key}={val}\n")
        except OSError:
            log.warning("could not persist %s to %s", key, TOKEN_FILE)
        return val

    def _bootstrap_token(self) -> str:
        """Load or create the static bearer token."""
        try:
            if TOKEN_FILE.exists():
                for line in TOKEN_FILE.read_text(encoding="utf-8").splitlines():
                    if line.startswith(TOKEN_KEY + "="):
                        tok = line.split("=", 1)[1].strip()
                        if tok:
                            return tok
        except OSError:
            pass
        tok = "agb_" + secrets.token_urlsafe(24)
        try:
            TOKEN_FILE.parent.mkdir(parents=True, exist_ok=True)
            TOKEN_FILE.write_text(f"{TOKEN_KEY}={tok}\n", encoding="utf-8")
        except OSError:
            log.warning("could not persist bridge token to %s", TOKEN_FILE)
        # Printed once at startup — later logs mask it.
        print(f"[agency-bridge] generated bridge token: {tok}  (saved to {TOKEN_FILE})")
        return tok

    @staticmethod
    def mask(tok: str) -> str:
        return tok[:7] + "…" if len(tok) > 7 else "…"
