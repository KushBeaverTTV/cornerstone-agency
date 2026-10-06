"""Phone-sized views of Hermes data (the relay's raw rows are built for the desktop).

A state-DB message row carries reasoning blobs, tool-call arguments, tool output, provider
replay fields and (by default) inlined images. The phone renders only role, visible text, the
tool's name and reactions, so a 200-row page shrinks from ~640 KB to a few dozen KB — the
difference between "instant" and "stuck on the old copy" over cellular.
"""
from __future__ import annotations

from typing import Any, Dict, List, Optional

# Visible text longer than this is cut (the phone shows a "Show more" fold anyway). Assistant
# text is never cut: the phone matches it against the live stream to swap in persisted rows.
MAX_USER_TEXT = 20_000


def _text_of(content: Any) -> str:
    """content may be a string or a list of OpenAI/Anthropic-style parts."""
    if content is None:
        return ""
    if isinstance(content, str):
        return content
    if isinstance(content, list):
        out: List[str] = []
        for part in content:
            if isinstance(part, str):
                out.append(part)
            elif isinstance(part, dict):
                t = part.get("text")
                if isinstance(t, str):
                    out.append(t)
        return "\n".join(out)
    if isinstance(content, dict) and isinstance(content.get("text"), str):
        return content["text"]
    return ""


def _reactions(meta: Any) -> Optional[Dict[str, Any]]:
    if isinstance(meta, dict) and isinstance(meta.get("reactions"), list):
        return {"reactions": meta["reactions"]}
    return None


def compact_row(row: Dict[str, Any]) -> Dict[str, Any]:
    role = row.get("role")
    out: Dict[str, Any] = {"id": row.get("id"), "role": role}
    if role == "tool":
        out["content"] = ""  # the phone shows "✓ <tool_name>"; output stays on the PC
    else:
        text = _text_of(row.get("content"))
        if role == "user" and len(text) > MAX_USER_TEXT:
            text = text[:MAX_USER_TEXT] + "\n…"
        out["content"] = text
    for key in ("tool_name", "display_kind", "timestamp"):
        if row.get(key) is not None:
            out[key] = row[key]
    meta = _reactions(row.get("display_metadata"))
    if meta:
        out["display_metadata"] = meta
    return out


def compact_messages(payload: Any) -> Dict[str, Any]:
    rows = payload.get("messages") if isinstance(payload, dict) else payload
    if not isinstance(rows, list):
        rows = []
    return {"messages": [compact_row(r) for r in rows if isinstance(r, dict)]}


def compact_sessions(payload: Any) -> Dict[str, Any]:
    rows = payload.get("sessions") if isinstance(payload, dict) else payload
    keep = ("id", "title", "preview", "started_at", "last_active", "message_count", "source", "profile")
    out = []
    for r in rows or []:
        if isinstance(r, dict):
            item = {k: r.get(k) for k in keep if r.get(k) is not None}
            preview = item.get("preview")
            if isinstance(preview, str):
                item["preview"] = preview[:160]
            out.append(item)
    return {"sessions": out}
