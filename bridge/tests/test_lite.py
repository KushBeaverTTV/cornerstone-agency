from agency_bridge import lite


def test_compact_rows_keep_only_what_the_phone_renders():
    rows = {"messages": [
        {"id": 1, "role": "user", "content": [{"type": "text", "text": "hi"}, {"type": "image_url", "image_url": "data:..."}],
         "api_content": "x" * 5000, "token_count": 3},
        {"id": 2, "role": "assistant", "content": "Checking.", "reasoning_details": ["r" * 9000],
         "tool_calls": [{"function": {"arguments": "a" * 9000}}]},
        {"id": 3, "role": "tool", "tool_name": "terminal", "content": "o" * 20000,
         "display_metadata": {"reactions": [{"emoji": "👍", "author": "user"}], "other": "x"}},
    ]}
    out = lite.compact_messages(rows)["messages"]
    assert out[0] == {"id": 1, "role": "user", "content": "hi"}
    assert out[1] == {"id": 2, "role": "assistant", "content": "Checking."}
    assert out[2] == {"id": 3, "role": "tool", "content": "", "tool_name": "terminal",
                      "display_metadata": {"reactions": [{"emoji": "👍", "author": "user"}]}}


def test_long_user_text_is_folded_but_assistant_text_is_kept_whole():
    rows = [{"id": 1, "role": "user", "content": "u" * 30000}, {"id": 2, "role": "assistant", "content": "a" * 30000}]
    out = lite.compact_messages(rows)["messages"]
    assert len(out[0]["content"]) < 21000 and out[0]["content"].endswith("…")
    assert len(out[1]["content"]) == 30000


def test_compact_sessions():
    out = lite.compact_sessions({"sessions": [{"id": "s", "title": "t", "last_active": 5.0, "cost": 9, "preview": "p" * 500}]})
    assert out == {"sessions": [{"id": "s", "title": "t", "preview": "p" * 160, "last_active": 5.0}]}
