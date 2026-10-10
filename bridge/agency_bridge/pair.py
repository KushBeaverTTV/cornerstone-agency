"""Phone pairing: one QR code carries everything the app needs.

``agency-bridge pair`` (or ``python -m agency_bridge.pair``) writes a local HTML page with the
QR and opens it. The QR encodes::

    cornerstone-agency://pair?v=1&bridge=<ip>:9121&token=<bridge token>
        &voice=<ip>:8766&voice_token=<voice token>&name=<pc name>

The page is written under the user's temp dir and never served over the network; the tokens it
holds are the same ones already stored on this PC.
"""
from __future__ import annotations

import html
import os
import shutil
import socket
import subprocess
import sys
import tempfile
import urllib.parse
import webbrowser
from pathlib import Path
from typing import Dict, List, Optional

from .config import Config
from .hermes_relay import read_env_file

SECRETS = Path(os.environ.get("CORNERSTONE_SECRETS", r"C:\Cornerstone\Cornerstone_Secrets"))


def _tailscale_ip() -> str:
    """The PC's tailnet IPv4, or "" when Tailscale is absent / logged out."""
    exe = shutil.which("tailscale") or r"C:\Program Files\Tailscale\tailscale.exe"
    if not os.path.isfile(exe):
        return ""
    try:
        out = subprocess.run([exe, "ip", "-4"], capture_output=True, text=True, timeout=10)
        lines = (out.stdout or "").strip().splitlines()
        return lines[0].strip() if lines else ""
    except Exception:  # noqa: BLE001
        return ""


def tailnet_ip() -> str:
    return _tailscale_ip() or os.environ.get("AGENCY_BRIDGE_HOST", "127.0.0.1")


def candidate_hosts() -> List[str]:
    """Every address the phone may reach this PC on, best first.

    127.0.0.1 is the USB cable (``adb reverse``); the tailnet IP works on any network once both
    devices are signed in to Tailscale. Order matters: the phone tries them in this order and
    keeps the first that answers.
    """
    hosts = ["127.0.0.1"]
    ts = _tailscale_ip()
    if ts:
        hosts.append(ts)
    bound = os.environ.get("AGENCY_BRIDGE_HOST", "").strip()
    if bound and bound not in hosts and bound != "0.0.0.0":
        hosts.append(bound)
    return hosts


def pairing_info(cfg: Optional[Config] = None) -> Dict[str, object]:
    """What a phone needs to find and trust this PC — the body of GET /pairing and the source of
    the build-time ``agency-pairing.env`` (same data, so a baked-in app and a live one agree)."""
    cfg = cfg or Config()
    voice: Dict[str, str] = read_env_file(SECRETS / "agency-voice.env")
    return {
        "v": 1,
        "name": socket.gethostname(),
        "hosts": candidate_hosts(),
        "bridge_port": cfg.port,
        "token": cfg.token,
        "voice_port": 8766,
        "voice_token": voice.get("HERMES_VOICE_BRIDGE_TOKEN", ""),
    }


def write_pairing_env(path: Optional[Path] = None, cfg: Optional[Config] = None) -> Path:
    """Write the build-time pairing file Gradle bakes into BuildConfig (owner-only, never committed)."""
    info = pairing_info(cfg)
    path = path or (SECRETS / "agency-pairing.env")
    path.parent.mkdir(parents=True, exist_ok=True)
    body = (
        f"AGENCY_PC_NAME={info['name']}\n"
        f"AGENCY_PC_HOSTS={','.join(f'{h}:{info['bridge_port']}' for h in info['hosts'])}\n"
        f"AGENCY_BRIDGE_TOKEN={info['token']}\n"
        f"AGENCY_VOICE_PORT={info['voice_port']}\n"
        f"AGENCY_VOICE_TOKEN={info['voice_token']}\n"
    )
    path.write_text(body, encoding="utf-8")
    return path


def pair_uri(ip: Optional[str] = None, cfg: Optional[Config] = None) -> str:
    cfg = cfg or Config()
    ip = ip or tailnet_ip()
    voice: Dict[str, str] = read_env_file(SECRETS / "agency-voice.env")
    q = {
        "v": "1",
        "bridge": f"{ip}:{cfg.port}",
        "token": cfg.token,
        "voice": f"{ip}:8766",
        "voice_token": voice.get("HERMES_VOICE_BRIDGE_TOKEN", ""),
        "name": socket.gethostname(),
    }
    return "cornerstone-agency://pair?" + urllib.parse.urlencode(q)


def qr_svg(data: str) -> str:
    import qrcode
    import qrcode.image.svg

    img = qrcode.make(data, image_factory=qrcode.image.svg.SvgPathImage, box_size=12, border=2)
    return img.to_string(encoding="unicode")


def write_page(uri: str) -> Path:
    svg = qr_svg(uri)
    page = f"""<!doctype html><meta charset=utf-8><title>Pair Cornerstone Agency</title>
<style>body{{background:#0b0b0c;color:#f4f1e8;font:16px system-ui;display:grid;place-items:center;min-height:100vh;margin:0}}
.card{{text-align:center}} svg{{background:#fff;padding:16px;border-radius:16px;width:360px;height:360px}}
h1{{font-size:22px;color:#f5c400}} p{{color:#a7a29a;max-width:420px}}</style>
<div class=card><h1>Pair Cornerstone Agency</h1>{svg}
<p>On the phone: Me &rarr; Pair with PC &rarr; scan. This code contains access tokens;
close this page when you are done.</p><p style="font-size:12px">{html.escape(uri.split('?')[0])}</p></div>"""
    path = Path(tempfile.gettempdir()) / "cornerstone-agency-pair.html"
    path.write_text(page, encoding="utf-8")
    return path


def main() -> None:
    if "--write-env" in sys.argv:
        print(f"Pairing env: {write_pairing_env()}")
        return
    uri = pair_uri()
    if "--print-uri" in sys.argv:
        print(uri)
        return
    path = write_page(uri)
    print(f"Pairing page: {path}")
    webbrowser.open(path.as_uri())


if __name__ == "__main__":
    main()
