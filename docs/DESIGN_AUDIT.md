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


---
# Addendum (same day): verified research + what was built

## Verified research (second pass; sources in the agent reports, summarized)
Muse (Meta, "Muse from Meta", iOS+Android+WhatsApp):
- 5 bottom tabs: Chat, Feed, Ideas, Goals, Library. No drawer. One long persistent main chat + optional side chats; interrupt / queue tasks.
- Header = large centred animated avatar + name + one-line live status ("Checking price"); tap -> panel of what it's doing / did / will do / pending permissions (4-icon segmented control; Soul + Memory files reachable). Avatar can be described, regenerated, renamed.
- Chat: Muse text is plain (no bubble) on white; user = right-aligned light-grey rounded bubble; inline file cards; emoji reaction when it starts work; composer = rounded field with attach left, dictation right. No live voice orb at launch (voice = dictation / WhatsApp voice notes; live avatar calls announced).
- Approvals are deterministic cards, not chat: title, instruction, embedded preview, payment row, then Deny (grey) / Allow (blue) pills; options allow once / always / deny. Proactive messages held to a "high bar".
- Goals grouped under coloured headers with checkbox rows; Ideas = plain list of first-person headlines; Library = rounded cards with thumbnails; Feed = personal "newspaper" from a prompt.
- Look: light, large corner radii, one sans, blue primary only.
Grok (xAI) app: top bar (sidebar icon | Ask / Imagine toggle | new chat), NO bottom tabs; user = dark-grey right bubble, Grok = full-width plain text; composer = rounded field with mode pill (Auto/Fast) bottom-left, attach, and a light "Speak" pill with waveform bottom-right; black-to-#1E1E1E, almost no accent colour; Android widget (Chat / Imagine / Voice); Companions retired (Sept 2026).
**Grok Bot** (separate xAI agent app - the closest match to Cornerstone Agency): sidebar roster of Bots (avatar, name, last-message preview, timestamp); avatar motion = state (calm / acknowledging / working / waiting for help); inline cards instead of prose (e.g. email draft "Ready to send"); routines list ("Every day at 8:00 AM", "Paused"); side panel showing the Bot's computer; approvals come back to you. Voice = floating call card: pill with avatar + live waveform, bottom row of four round buttons (settings, transcript, mic, red end-call; red is the only saturated colour).
Unverifiable: Muse dark mode, Grok's consumer voice orb.

=> Direction confirmed: keep the roster as a first-class surface (Grok Bot), make chat plain-text + pill composer (both), put agent state in the header via an animated keystone avatar + status line (Muse/Grok Bot), approvals as Deny/Allow(Once/Always) cards (Muse), voice as a call card with live waveform + 4 buttons incl. red end (Grok Bot).

## Built on branch `design/chat-first` (debug APK installed on the phone)
- Theme: secondary/tertiary text contrast raised (tertiary 2.8:1 -> ~4.6:1), body 14->16sp, smallest label 11->12sp, row min height 56dp.
- Approval/clarify chips: 30dp -> 48dp+ hit areas.
- Chat: composer is one 56dp pill (focus ring), single 48dp trailing button = mic (empty) / send (text) / stop (streaming); assistant text full width, no bubble; user bubble clamps to 8 lines with Show more; thinking + tool rows collapse into one "Thought · N tools" chip per turn (pulsing yellow dot while live, tap to expand); empty state with avatar, name, tagline and 3 suggestion pills; New-chat button in the header; header targets always 48dp; long-press any message = copy; soft fade under the header; model chip 48dp tall and shows "Sonnet 5.5" instead of "claude-sonnet-5-5"; neutral hairline pill borders (gold on focus).
- Not yet: nav restructure (3 tabs / Activity merge), voice call card, avatar states, working strip, approvals inbox, widget.

## Passes 2-4 (built, installed, verified on device)
- Navigation: 3 tabs (Chats / Activity / Me). Chats = roster strip + one recency list (bots + desktop Prima), sessions waiting on an answer float to the top with a banner, cron/voice previews cleaned. Activity = Feed / Goals / Files / Jobs chips. Bots page reachable from the face icon in Chats.
- Voice: call card (orb, live waveform, state, last words, Mute + red End). Mute keeps the mic closed until unmuted.
- Chat header: avatar ring + one-line live status ("Using terminal", "Thinking...", "Replying...", "Waiting for you", "Reconnecting...") derived from the transcript.
- Home-screen widget (4x1): card opens Kaori's latest chat, mic opens straight into voice.
- Review pass (two sonnet reviewers) fixed: Bots overlay surviving tab switches, widget intent replay on recreate, stale voice flag (20s TTL), Jobs shortcut landing on the wrong pane, unmute while the bot speaks, empty chat overflow on the cover display, roster/routing chip hit targets, call buttons role.

## Pass 5 (verified on device)
- Needs-you inbox on Chats: approvals answerable in place (monospace command block, "Destructive command" flag, outlined Deny / gold Allow once); single-question clarifies get inline choice chips. Uses the same srq respond path as the notification action. Debug builds can fake items with `am start ... --ez debug_inbox true`.
- Animated keystone mark (arch of stones, lit keystone): breathes idle, ripples with your voice while listening, sweeps light while working, pulses speaking, tilts when waiting. Used in the voice card and empty chat.
- Voice card: transcript toggle, equal 64dp buttons with hairline borders.
- Chat: jump-to-latest button, message add animation, code fences with language label + Copy (monospace, scrolls sideways), table cells 150dp (scrolls), list indent.
- Verified at 1.4x font scale: no clipping on Chats or voice card.
- Second sonnet review fixed: inbox race (synchronized), debug items never hit the socket, a11y labels on jump/copy/keystone.
- Lesson: vision critiques are unreliable on fine detail (claimed code was not monospace; pixel widths proved it was). Verify with measurements.

## Pass 6 — bot-style home, five tabs, a real Feed (verified on device)
Brief: "the chat section looks like an email inbox", "the feed has nothing", "you got rid of the tabs", "Muse as inspiration".
- Navigation: back to five tabs (Chats / Feed / Goals / Files / Me) with a custom bar — hairline top, gold tint + short underline on the active tab, badge dots (Chats = needs-you, Feed = unread). Icons are hand-drawn `ImageVector`s in `AgencyIcons` (only `material-icons-core` is a dependency). Jobs moved under Me > Routines.
- Chats (`ChatsScreen.kt`, replaces `BotsScreen`): one row per bot with the keystone mark in a tinted disc (`BotMark`), name + last line + relative time; Desktop section for Prima; "Recent" is a quiet dot list capped at 6 and skips cron/voice noise. Machine-looking titles (`UI_test:_in_your…`) are cleaned (`sessionLine`). Docked composer pill ("Ask anyone…") routes via Laya when the bridge is up, otherwise a picker sheet; mic goes straight to voice.
- Chat: borderless neutral user bubble (`colors.bubble`), voice/cron preambles collapse to a centered chip, two-row Grok-style composer (model chip bottom-left, mic/send right), header avatar is the animated `BotMark` with WORKING / WAITING / ERROR states.
- Feed: actually written. `FeedWriter` opens a hidden throwaway session with Kaori, sends the brief, waits for a quiet turn (streaming dips between tool rounds, so "finished" = 2.5 s quiet + last row is assistant text), parses the newest assistant text that is a JSON array, dedupes by title into `CompanionRepository`, then `session.close` + `session.delete` (the gateway refuses to delete a live session). The session is marked `session.set_hidden` at creation so it never appears in Chats even if the app dies mid-run. Paper-style rows: topic kicker, Collapse headline, 14/20 body, source host, unread dot; grouped Today / Yesterday / date. Status strip while writing; menu = Edit brief / Mark all read / Clear.
- Goals grouped by category with a colour accent per group, Done folded at the bottom. Files is a plain list with a bordered type badge. Cards everywhere lost the yellow border (neutral `stroke`, 18dp radius).
- Me: identity row (initial in Collapse, host, Online pill), Connection card, Voice + Desktop bridge sections with Edit → Save/Cancel, More (Routines / Skills / System) with chevrons, Disconnect confirms. Dead "Appearance" row removed.
- Lessons: build from `C:\Cornerstone\...`, not the `D:` alias (Kotlin incremental compile trips on mismatched roots). `am start`/`input tap` on the phone can land on the notification shade — screenshot before tapping, `cmd statusbar collapse` to recover.

## Pass 6b — finish + polish (verified on device)
- Feed: Stop. The header spinner is a stop button, the status strip has "Stop", the empty state has "Stop"; cancel interrupts the turn and runs the same hidden-session cleanup (verified: no row left in `state.db`). Status shows elapsed minutes after the first (`Using web_search… · 2m`). "Clear feed" confirms first.
- Compact height (cover display): `PageHeader` shrinks to one tight line instead of disappearing, so +, Spark and the menu stay reachable; the compact top tab row gets `statusBarsPadding` (it was drawing under the clock).
- Routines: title matches the Me entry (was "Jobs"); `BotAvatar` now delegates to `BotMark`, so Routines, Bot detail, Prima sessions and the share target show the keystone instead of a letter in a circle.
- Chat: menu drops the duplicate "New session" (the header "+" does it) and only offers Rename once there is a conversation; empty state shows the bot's role as the tagline ("Social Media Manager") and specialists get their own three openers.
- Dead `routeError` state removed from Chats.
- Lesson: do not use `wm size` on the user's phone to simulate the cover display — it resizes the live screen. Ask them to close the phone instead.

## Known issues / not done
- Feed write duration depends on Kaori's search depth (1–5 min).
- The earlier "dark status-bar clock" was a stale-build artifact (the APK still held old dex); it renders correctly now. Always `gradlew clean assembleDebug` after structural edits.
- Inbox only shows requests from sessions you are not inside (leaving a chat interrupts its live turn, so only background/Telegram/cron sessions appear). Your config has approvals off, so real ones are rare; clarify and secret requests still show.
- Multi-question clarifies and secrets open the chat instead of answering inline.
- KeystoneMark allocates paths each frame (cheap, but could use drawWithCache); no reduce-motion handling yet.
- No dedicated mute-slash icon (button flips to white + "Muted" label).
- The floating "2" bubble in screenshots is `co.thefabulous.app`, not this app.
- Over USB-less use needs Tailscale ON on the phone.
