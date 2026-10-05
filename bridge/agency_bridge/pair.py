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
import socket
import subprocess
import sys
import tempfile
import urllib.parse
import webbrowser
from pathlib import Path
from typing import Dict, Optional

from .config import Config
from .hermes_relay import read_env_file

SECRETS = Path(os.environ.get("CORNERSTONE_SECRETS", r"C:\Cornerstone\Cornerstone_Secrets"))


def tailnet_ip() -> str:
    try:
        out = subprocess.run(["tailscale", "ip", "-4"], capture_output=True, text=True, timeout=10)
        ip = (out.stdout or "").strip().splitlines()[0].strip()
        if ip:
            return ip
    except Exception:  # noqa: BLE001
        pass
    return os.environ.get("AGENCY_BRIDGE_HOST", "127.0.0.1")


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
    uri = pair_uri()
    if "--print-uri" in sys.argv:
        print(uri)
        return
    path = write_page(uri)
    print(f"Pairing page: {path}")
    webbrowser.open(path.as_uri())


if __name__ == "__main__":
    main()
