"""Prima's saved sessions on disk (``~/.prime/agent/sessions/*.jsonl``).

Each file is one prime-agent session: a ``session`` header line, then entries
(``message``, ``model_change``, ``session_state``…). The bridge lists the ones a person would
recognise — at least one user/assistant message, not archived — so the phone can reopen
conversations started on the desktop (a worker loads one with ``switch_session``).
"""
from __future__ import annotations

import json
from pathlib import Path
from typing import Any, Dict, List, Optional

DEFAULT_DIR = Path.home() / ".prime" / "agent" / "sessions"


def _text_of(content: Any) -> str:
    if isinstance(content, str):
        return content
    if isinstance(content, list):
        return " ".join(
            p.get("text", "") for p in content if isinstance(p, dict) and p.get("type") == "text"
        )
    return ""


def summarize(path: Path) -> Optional[Dict[str, Any]]:
    """One saved session -> list row, or None when it is empty or archived."""
    sid, cwd, name, first_user, last_text = "", "", "", "", ""
    messages, archived, model = 0, False, ""
    try:
        with path.open("r", encoding="utf-8", errors="replace") as fh:
            for raw in fh:
                raw = raw.strip()
                if not raw:
                    continue
                try:
                    e = json.loads(raw)
                except ValueError:
                    continue
                t = e.get("type")
                if t == "session":
                    sid = e.get("id") or ""
                    cwd = e.get("cwd") or ""
                elif t == "message":
                    m = e.get("message") or {}
                    role = m.get("role")
                    if role in ("user", "assistant"):
                        messages += 1
                        txt = _text_of(m.get("content")).strip()
                        if role == "user" and txt and not first_user and not txt.startswith("/"):
                            first_user = txt
                        if txt:
                            last_text = txt
                elif t == "session_state":
                    archived = (e.get("state") or {}).get("status") == "archived"
                elif t == "session_info":
                    name = e.get("name") or name
                elif t == "model_change":
                    model = e.get("modelId") or model
    except OSError:
        return None
    if messages == 0 or archived:
        return None
    title = name or (first_user.splitlines()[0][:80] if first_user else "")
    return {
        "path": str(path),
        "session_id": sid or path.stem,
        "title": title or f"Session {path.stem[:8]}",
        "preview": last_text[:160],
        "cwd": cwd,
        "message_count": messages,
        "model": model,
        "updated_at": path.stat().st_mtime,
    }


def list_saved(directory: Path = DEFAULT_DIR, limit: int = 50) -> List[Dict[str, Any]]:
    if not directory.is_dir():
        return []
    files = sorted(directory.glob("*.jsonl"), key=lambda p: p.stat().st_mtime, reverse=True)
    out: List[Dict[str, Any]] = []
    for p in files:
        row = summarize(p)
        if row:
            out.append(row)
            if len(out) >= limit:
                break
    return out


def is_saved_session(path: str, directory: Path = DEFAULT_DIR) -> bool:
    """Only files inside the Prima sessions dir may be opened (no arbitrary paths)."""
    try:
        p = Path(path).resolve()
        return p.suffix == ".jsonl" and p.parent == directory.resolve() and p.is_file()
    except OSError:
        return False


__all__ = ["list_saved", "summarize", "is_saved_session", "DEFAULT_DIR"]
