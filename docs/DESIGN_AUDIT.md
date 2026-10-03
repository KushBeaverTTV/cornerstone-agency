# Cornerstone Agency - design audit and Muse/Grok direction (2026-10-02)

Method: four sonnet agents read the source in slices (design system, chat+voice, navigation/IA) and researched Muse and Grok from public sources; the branding board was reviewed directly. NOT done: live screenshots (phone was off USB), so rendering claims (clipping, crowding) are inferred from code. Muse's real screen layout and Grok's mobile voice orb could not be verified from any source found.

## What is already the personality (keep)
- Ink #0A0A0A + signal yellow #FCEE0A, strict two-colour discipline; white only for text/wordmark.
- Collapse display face for titles/wordmark/empty states only; sans for body.
- Yellow-tinted hairline edges, underline fields, tracked-caps yellow section kickers.
- RequestCard left accent bar by kind, collapsing to a muted summary line.
- Gold only for active state / notification dot; Kaori always gold; streaming ` ▌` cursor.
- Brand marks: keystone (icon), corner-cut, C-only, wordmark with clipped-corner triangle.

## Findings (verified in code unless marked)
1. Touch targets: `touch = 48.dp` token exists but only PrimaryButton uses it. Approve/Deny/Send chips ~30dp; model pill ~30dp; mic icon 20dp; send 44dp; compact-mode back button 36dp; password eye 24dp; long-press-only delete (Sessions, bot detail).
2. Contrast: tertiary text (alpha 0x8A) ~2.8:1 on card/ink - fails AA; used for placeholders, timestamps, unselected tab labels, disabled chips, 11sp labelSmall. Violet kicker ~4.0:1 at 11sp. Secondary 5.2:1 ok, gold 14.9:1 ok.
3. Navigation: six bottom tabs (Bots, Chats, Feed, Goals, Artifacts, More); first screen is the Bots roster, not a chat. Text chat = 2 taps, resume = 3+, voice = 3 taps (tab, bot, mic). Compact-height mode = six unlabeled 28dp icons. Jobs reached by a local flag (no real route/back handling - inferred).
4. Chat: assistant replies sit in bordered 300dp-max bubbles; composer is a hairline underline field + separate 20dp mic + 44dp send that stays yellow when empty; no attach button, no copy/regenerate, no empty state, no jump-to-latest; thinking, tool rows and statusLine are three separate "busy" surfaces; body text 14sp.
5. Voice: bottom sheet with a 44dp orb, 10-11sp detail text; same pulse in every non-listening state; no mute/stop button, barge-in exists in logic but no UI.
6. Settings sprawl: More holds inline editable voice-bridge/token and desktop-bridge host/token fields; "Appearance" row is dead and says "Hermes dark"; Disconnect has no confirm.
7. System: no shape/elevation/motion tokens (radii 6/8/10/12 hard-coded), no animations beyond haptics (tab swap, card collapse snap), duplicate TalkBack labels on nav icons, bare clickables without roles.

## What Muse and Grok do (sources in agent reports; VERIFIED vs INFERRED noted)
Muse: messaging-style, one long main chat + optional side chats, interrupt/queue tasks; surfaces for Feed, Ideas, Goals, Library/Artifacts, Activity log, Approvals; top "what it's doing" strip you can tap; approval cards with once/always scopes; animated avatar states; plain-language labels (no "crons/tools"); notifies only when something is new or needs input.
Grok: composer is a tall pill with "+" left, model chip + mic/voice right; send morphs from waveform to arrow; model picker uses outcome labels (Fast/Auto/Expert/Heavy); inline dictation; voice mode replaces the bar with Stop + persona; Android home-screen widget. Praise: input front and centre, fast. Complaints: promos clutter an empty page.

## Direction: "Cornerstone, chat-first" (borrow the structure, keep the skin)
Home and nav
- Home = recency-sorted chat list across all bots (bot avatar on each row) + avatar strip on top + a docked "Message..." pill with mic. Bots roster becomes the strip / "New chat" picker.
- Three tabs with labels: Chats (home) / Activity (Feed + Goals + Artifacts as segmented chips) / Me (connection, voice, desktop bridge, Jobs, Skills, System). Jobs becomes a real route.
- Persistent "working" strip under the header while any agent runs or needs approval (tap -> running tasks / approvals), yellow pulse dot.
Chat
- Assistant text full width, no border; keep a right-aligned yellow-tint user bubble; 16sp body.
- Composer = one 56dp+ pill: "+" (attach/skills), text, bot/model chip, mic morphing to send; yellow ring on focus; send hidden/dim when empty.
- One collapsible "Thought - 3 tools" chip per turn replacing thinking card + tool rows + status line; keep the ` ▌` cursor.
- Message actions (copy, regenerate), empty state with keystone mark + 3-4 large suggestion chips, jump-to-latest.
- Approval/clarify cards: big full-width Allow once / Always / Deny (48dp+), app-wide badge count.
Voice
- Full-screen on ink: 160-220dp keystone-glow orb with distinct motion per state (listening = mic-level ring, thinking = rotating arc, speaking = ripple), large live caption, 72dp end button + mute, same pill below. Consider a Grok-style home-screen widget (Chat / Voice).
Identity-preserving polish
- Animate the keystone mark as the agent's "avatar state" (idle / thinking / tool / waiting-for-approval); keep Collapse for titles only; yellow only for active/primary.
- Tokens: add AgencyShapes (8/12/pill), motion spec (150-220ms), raise tertiary alpha to ~0.70, 16sp chat / 12sp min label, rows min 56dp, 48dp minimum everywhere (never shrink in compact mode), swipe/overflow instead of long-press, Role semantics, remove duplicate nav content descriptions.
Settings
- Me screen = 4 big rows, each opening its own screen with a single Save + Test; real Appearance or remove it; confirm on Disconnect; saved connections instead of hard-coded IP presets.

## Suggested build order
1. Accessibility/tokens pass (touch 48dp, contrast, type floors, shapes, motion) - invisible, safe.
2. Composer pill + full-width assistant text + merged thinking/tool chip + empty state.
3. Voice full-screen mode.
4. Home = chat list + dock; 3-tab nav; Activity merge; Me restructure.
5. Working strip + approvals inbox + keystone avatar states + widget.
Note: ~10 files in this repo have uncommitted edits from other work; commit or branch before starting.
