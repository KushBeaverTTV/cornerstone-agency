package com.cornerstone.agency.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlinx.serialization.json.put
import java.util.concurrent.atomic.AtomicLong

/** Cadence of the history poll (a `sessions.changed` event short-circuits the wait). */
private const val SYNC_POLL_MS = 3_000L
private const val SYNC_FETCH = 200

/** A turn that has produced no event for this long is re-checked against the server. */
private const val STALL_MS = 90_000L

private val REQUEST_EVENTS = setOf(
    "approval.request", "clarify.request", "secret.request",
    "sudo.request", "mcp.setup.request",
)

/** *.resolved/.expired/.answered/.cancelled/.timeout events close the card they name. */
private val RESOLVE_SUFFIX = Regex("\\.(resolved|expired|answered|cancelled|canceled|timeout)$")

/** Events that mean "a turn is producing output" — a turn we did not start began elsewhere. */
private val TURN_EVENTS = setOf(
    "message.start", "message.delta", "reasoning.delta", "reasoning.available",
    "tool.start", "tool.generating", "tool.complete", "message.interim",
)

/** Bytes of one picked attachment, read by the UI (it owns the ContentResolver). */
class OutgoingAttachment(
    val name: String,
    val mime: String,
    val bytes: ByteArray,
    val localUri: String,
) {
    val isImage: Boolean get() = mime.startsWith("image/")
}

/**
 * One bot's chat over the Hermes socket.
 *
 * Sharing a session with the desktop: when the socket reaches the same backend the Hermes desktop
 * app uses (the bridge relay), `session.resume` joins the LIVE session as another viewer, so every
 * turn — typed here or on the desktop — streams into both. A turn this client did not start is a
 * "peer turn": its prompt is fetched from the state DB and the reply streams in live.
 *
 * Every finished turn is then reconciled against the state DB: the persisted rows (with durable
 * row ids, which edit / regenerate / react / branch need) replace the live overlay. A slower poll
 * appends rows written by other processes (Telegram, cron, a backend the phone is not attached
 * to) and rebuilds the transcript when rows disappear (undo / rewind elsewhere).
 *
 * ID discipline (tui_gateway contract): session.list/delete take STORED ids; session.resume takes
 * a stored id and returns a runtime `session_id`; prompt, interrupt and event ids are runtime ids.
 */
class ChatRepository(
    gateway: GatewayLink,
    profile: String,
    /** Live chats sync + cache; a headless writer (Feed) sets this false. */
    private val persistent: Boolean = true,
    private val history: () -> HistorySource? = { GatewayHub.repo },
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    private val cacheEnabled: Boolean = persistent && ChatCache.ready,
) : ChatRequestCards(gateway, profile, dispatcher) {
    // The current turn: where its rows start on screen, and the DB row id they will follow.
    private var turnStartIndex: Int? = null
    private var turnBaseRow: Long = 0
    private var lastEventAt = 0L

    // Every runtime id this repo has owned (re-resume mints new ones) — all are
    // "this chat is open" for notification suppression until close().
    private val registeredSids = HashSet<String>()

    // Highest state-DB row id reflected on screen (every row <= cursor is shown or hidden on purpose).
    private var syncCursor = 0L
    private var pendingReconcile = false
    private var syncJob: Job? = null
    private val syncKick = Channel<Unit>(Channel.CONFLATED)

    init {
        scope.launch { gateway.events.collect { if (it.type == "sessions.changed") syncKick.trySend(Unit) } }
        scope.launch { gateway.events.collect(::onEvent) }
        scope.launch { gateway.requests.collect(::onRequestFrame) }
        // Resolutions that happened outside this transcript (notification
        // action, TTL) collapse the matching card — the server stays silent.
        scope.launch {
            com.cornerstone.agency.service.NotificationCenter.resolvedKeys
                .collect { key -> resolveRequest(key, "handled") }
        }
    }

    // ---- session resolution ---------------------------------------------

    /**
     * Show the on-device copy right away (before the socket is even open). [storedId] null means
     * "whatever this bot had open last".
     */
    suspend fun preload(storedId: String?) {
        if (!cacheEnabled || _items.value.isNotEmpty()) return
        val id = storedId ?: ChatCache.lastSession(profile) ?: return
        val cached = ChatCache.loadTranscript(profile, id, ::nextId) ?: return
        if (_items.value.isNotEmpty()) return
        _title.value = cached.first
        _items.value = cached.second
        _fromCache.value = true
    }

    /**
     * Explicit stored id → resume; else this bot's most recently ACTIVE session (session.list is
     * ordered by last activity) → resume; else session.create.
     */
    override suspend fun open(requestedStoredId: String?) {
        _opening.value = true
        try {
            when {
                requestedStoredId != null -> resume(requestedStoredId)
                else -> {
                    val recent = listSessions().firstOrNull()
                    if (recent != null) {
                        _title.value = recent.title
                        resume(recent.id)
                    } else {
                        create()
                    }
                }
            }
            _lastError.value = ""
        } catch (e: Exception) {
            _lastError.value = friendly(e)
        } finally {
            _opening.value = false
        }
    }

    /** Re-resolve after a socket reconnect: resume the stored session for a fresh runtime id. */
    override suspend fun refreshAfterReconnect() {
        val stored = storedSessionId ?: return
        try {
            resume(stored)
        } catch (_: Exception) {
        }
    }

    private suspend fun resume(storedId: String) {
        val res = gateway.call(
            "session.resume",
            buildJsonObject {
                put("session_id", storedId)
                put("profile", profile)
            },
        ).asObj()
        bindRuntime(res, storedId)
        hydrate(res)
        _fromCache.value = false
        syncCursor = maxOf(TranscriptMapping.maxRowId(_items.value) ?: 0L, dbHead() ?: 0L)
        if (_streaming.value) {
            // Joined mid-turn: the turn's rows start at its prompt and land after that row.
            turnBaseRow = (_items.value.getOrNull(turnStartIndex ?: -1)?.rowId?.minus(1)) ?: syncCursor
            syncCursor = turnBaseRow
        }
        startSync()
        seedPendingFromResume(res)
        fetchTitle()
        seedPendingApprovals()
        persist()
    }

    private fun bindRuntime(res: JsonObject, fallbackStored: String?) {
        runtimeSessionId = res.str("session_id") ?: runtimeSessionId
        storedSessionId = res.str("stored_session_id") ?: res.str("session_key")
            ?: res.str("resumed") ?: fallbackStored ?: storedSessionId
        GatewayHub.registerSession(runtimeSessionId, storedSessionId, profile)
        runtimeSessionId?.let {
            registeredSids += it
            GatewayHub.openChats += it
        }
        if (persistent) storedSessionId?.let { ChatCache.setLastSession(profile, it) }
    }

    private suspend fun create() {
        val res = gateway.call(
            "session.create",
            buildJsonObject { put("profile", profile) },
        ).asObj()
        bindRuntime(res, null)
        hydrate(res)
        _fromCache.value = false
        syncCursor = 0
        startSync()
        seedPendingFromResume(res)
        seedPendingApprovals()
    }

    suspend fun listSessions(): List<SessionSummary> =
        listProfileSessions(gateway, profile).also { if (persistent) ChatCache.saveSessions(profile, it) }

    // ---- sending ----------------------------------------------------------

    override suspend fun send(text: String) = send(text, emptyList())

    /**
     * Send a message, optionally with attachments. Images are queued on the session
     * (`image.attach_bytes`, the model sees them natively); other files are staged into the
     * session workspace (`file.attach`) and referenced in the text as `@file:` refs.
     */
    suspend fun send(text: String, attachments: List<OutgoingAttachment>) {
        val t = text.trim()
        if (t.isEmpty() && attachments.isEmpty()) return
        if (!ensureSession()) return
        val rt = runtimeSessionId ?: return
        val shown = attachments.map {
            ChatAttachment(it.name, if (it.isImage) "image" else "file", it.mime, it.localUri, sizeBytes = it.bytes.size.toLong())
        }
        beginLocalTurn(ChatItem(nextId(), ChatKind.User, t, attachments = shown))
        val refs = ArrayList<String>()
        try {
            for (a in attachments) {
                if (a.isImage) {
                    gateway.call("image.attach_bytes", buildJsonObject {
                        put("session_id", rt)
                        put("content_base64", android.util.Base64.encodeToString(a.bytes, android.util.Base64.NO_WRAP))
                        put("filename", a.name)
                    })
                } else {
                    val res = gateway.call("file.attach", buildJsonObject {
                        put("session_id", rt)
                        put("name", a.name)
                        put("data_url", "data:${a.mime.ifBlank { "application/octet-stream" }};base64," +
                            android.util.Base64.encodeToString(a.bytes, android.util.Base64.NO_WRAP))
                    }).asObj()
                    res.str("ref_text")?.let { refs += it }
                }
            }
        } catch (e: Exception) {
            failTurn("Attachment upload failed: ${friendly(e)}")
            return
        }
        val body = (listOf(t) + refs).filter { it.isNotBlank() }.joinToString("\n")
            .ifBlank { "What do you see?" }
        submit(body, truncateBeforeRow = null)
    }

    private suspend fun ensureSession(): Boolean {
        if (runtimeSessionId != null) return true
        return try {
            create(); true
        } catch (e: Exception) {
            append(ChatKind.Error, "Could not start a session: ${friendly(e)}")
            false
        }
    }

    private fun beginLocalTurn(user: ChatItem) {
        if (!_streaming.value || turnStartIndex == null) {
            turnStartIndex = _items.value.size
            turnBaseRow = syncCursor
        }
        append(user)
        _streaming.value = true
        _statusLine.value = ""
        lastEventAt = System.currentTimeMillis()
    }

    private fun failTurn(message: String) {
        append(ChatKind.Error, message)
        finalizeStreaming()
        turnStartIndex = null
    }

    private suspend fun submit(text: String, truncateBeforeRow: Long?) {
        val rt = runtimeSessionId ?: return
        val userItemId = _items.value.lastOrNull { it.kind == ChatKind.User }?.id
        try {
            val res = gateway.call(
                "prompt.submit",
                buildJsonObject {
                    put("session_id", rt)
                    put("text", text)
                    put("surface", "mobile")
                    if (truncateBeforeRow != null) {
                        put("confirm_truncate", true)
                        put("truncate_before_row_id", truncateBeforeRow)
                    }
                },
            ).asObj()
            res.long("user_row_id")?.let { row -> userItemId?.let { id -> mutate(id) { it.copy(rowId = row) } } }
            when (res.str("status")) {
                "queued" -> append(ChatKind.Status, "Queued — runs after the current reply")
                "steered" -> append(ChatKind.Status, "Added to the reply in progress")
            }
        } catch (e: Exception) {
            failTurn("Send failed: ${friendly(e)}")
        }
    }

    override suspend fun stop() {
        val rt = runtimeSessionId ?: return
        try {
            gateway.call("session.interrupt", buildJsonObject { put("session_id", rt) })
        } catch (_: Exception) {
        }
        endTurn()
    }

    // ---- message actions (Hermes desktop parity) ----------------------------

    /** Re-run the conversation from a user message with new text (drops it and everything after). */
    suspend fun editAndResend(userItemId: String, newText: String): Boolean {
        val all = _items.value
        val idx = all.indexOfFirst { it.id == userItemId }
        val item = all.getOrNull(idx) ?: return false
        val row = item.rowId ?: return false
        if (_streaming.value) return false
        val t = newText.trim()
        if (t.isEmpty()) return false
        _items.value = all.take(idx)
        turnStartIndex = idx
        turnBaseRow = syncCursor
        append(ChatItem(nextId(), ChatKind.User, t))
        _streaming.value = true
        lastEventAt = System.currentTimeMillis()
        submit(t, truncateBeforeRow = row)
        return true
    }

    /** Ask again: re-run the prompt that produced [assistantItemId]. */
    suspend fun regenerate(assistantItemId: String): Boolean {
        val prompt = TranscriptMapping.promptFor(_items.value, assistantItemId) ?: return false
        return editAndResend(prompt.id, prompt.text.ifBlank { "Try again." })
    }

    /** Copy the conversation up to and including [itemId] into a new chat; returns its stored id. */
    suspend fun branchFrom(itemId: String): String? {
        val rt = runtimeSessionId ?: return null
        val count = TranscriptMapping.branchCount(_items.value, itemId)
        if (count <= 0) return null
        return try {
            val res = gateway.call("session.branch", buildJsonObject {
                put("session_id", rt)
                put("count", count)
            }).asObj()
            res.str("stored_session_id")
        } catch (e: Exception) {
            _lastError.value = "Branch failed: ${friendly(e)}"
            null
        }
    }

    /** Tapback: same emoji again clears it. */
    suspend fun react(itemId: String, emoji: String) {
        val rt = runtimeSessionId ?: return
        val item = _items.value.firstOrNull { it.id == itemId } ?: return
        val row = item.rowId ?: return
        val mine = item.reactions.firstOrNull { it.author == "user" }?.emoji
        val next = if (mine == emoji) null else emoji
        // Optimistic.
        mutate(itemId) { it.copy(reactions = it.reactions.filter { r -> r.author != "user" } + listOfNotNull(next?.let { e -> Reaction(e) })) }
        try {
            val res = gateway.call("message.react", buildJsonObject {
                put("session_id", rt)
                put("row_id", row)
                if (next == null) put("emoji", JsonNull) else put("emoji", next)
            }).asObj()
            val list = (res["reactions"] as? JsonArray)?.let { TranscriptMapping.reactionsOf(buildJsonObject { put("reactions", it) }) }
            if (list != null) mutate(itemId) { it.copy(reactions = list) }
            persist()
        } catch (e: Exception) {
            mutate(itemId) { it.copy(reactions = item.reactions) }
            _lastError.value = "Reaction failed: ${friendly(e)}"
        }
    }

    override suspend fun newSession() {
        _opening.value = true
        try {
            create()
            _title.value = ""
            _lastError.value = ""
        } catch (e: Exception) {
            _lastError.value = friendly(e)
        } finally {
            _opening.value = false
        }
    }

    /** Delete, or hide if the gateway still considers it live. True when actually deleted. */
    suspend fun deleteSession(storedId: String): Boolean =
        removeStoredSession(gateway, storedId, profile).also { ChatCache.forget(profile, storedId) }

    override suspend fun rename(newTitle: String) {
        val id = runtimeSessionId ?: storedSessionId ?: return
        val t = newTitle.trim()
        if (t.isEmpty()) return
        gateway.call(
            "session.title",
            buildJsonObject {
                put("session_id", id)
                put("title", t)
            },
        )
        _title.value = t
        persist()
    }

    private suspend fun fetchTitle() {
        val id = runtimeSessionId ?: storedSessionId ?: return
        try {
            val res = gateway.call(
                "session.title",
                buildJsonObject { put("session_id", id) },
            ).asObj()
            res.str("title")?.takeIf { it.isNotBlank() }?.let {
                _title.value = it
                GatewayHub.sessions[runtimeSessionId]?.title = it
            }
        } catch (_: Exception) {
        }
    }

    /**
     * Leaving the chat. The session is SHARED (the desktop may be watching the same turn), so
     * leaving never interrupts it — Stop does that explicitly.
     */
    override fun close() {
        persist()
        reqTimers.values.forEach { it.cancel() }
        reqTimers.clear()
        reqIndex.clear()
        GatewayHub.openChats -= registeredSids
        registeredSids.clear()
        PendingRequests.clear(storedSessionId ?: runtimeSessionId ?: "", profile)
        scope.cancel()
    }

    private fun persist() {
        if (!cacheEnabled) return
        val stored = storedSessionId ?: return
        ChatCache.saveTranscript(profile, stored, _title.value, _items.value)
    }

    // ---- transcript hydration ----------------------------------------------

    private fun hydrate(res: JsonObject) {
        val list = TranscriptMapping.fromResume(res["messages"] as? JsonArray, ::nextId).toMutableList()
        assistantId = null
        turnAssistantIndex = null
        thinkingId = null
        openToolId = null
        turnStartIndex = null
        // Hydration replaces the transcript — request cards must be re-seeded
        // from open_requests, so drop the stale dedupe index and timers.
        reqTimers.values.forEach { it.cancel() }
        reqTimers.clear()
        reqIndex.clear()
        _statusLine.value = ""
        val running = res["running"]?.jsonPrimitive?.booleanOrNull == true
        if (running) {
            // Joined a live turn: show its prompt (if the snapshot does not have it yet) and what
            // the reply has said so far; the deltas that follow continue this bubble.
            val inflight = res["inflight"] as? JsonObject
            val prompt = inflight?.str("user").orEmpty()
            var start = list.indexOfLast { it.kind == ChatKind.User }
            if (prompt.isNotBlank() && list.lastOrNull { it.kind == ChatKind.User }?.text?.trim() != prompt.trim()) {
                list += ChatItem(nextId(), ChatKind.User, TranscriptMapping.splitAttachments(prompt).first)
                start = list.size - 1
            }
            turnStartIndex = if (start >= 0) start else list.size
            val partial = inflight?.str("assistant").orEmpty()
            val id = nextId()
            assistantId = id
            turnAssistantIndex = list.size
            list += ChatItem(id, ChatKind.Assistant, partial, streaming = true, done = false)
            lastEventAt = System.currentTimeMillis()
        }
        _items.value = list
        _streaming.value = running
        reportOpenRequests()
    }

    // ---- live events ---------------------------------------------------------

    private fun onEvent(ev: GatewayEvent) {
        val sid = ev.sessionId
        if (sid != null && sid != runtimeSessionId && sid != storedSessionId) return
        val p = ev.payload
        // Session-less frames (sessions.changed, legacy request events) never start a turn.
        if (sid != null && ev.type in TURN_EVENTS || (ev.type == "thinking.delta" && !p?.str("text").isNullOrEmpty())) {
            lastEventAt = System.currentTimeMillis()
            if (!_streaming.value) beginPeerTurn()
        }
        when (ev.type) {
            "message.start" -> startAssistant()

            "message.delta" ->
                appendAssistantDelta(p?.str("delta") ?: p?.str("text") ?: "")

            // interim = the text before a tool round; the turn goes on.
            "message.interim" -> {
                val text = p?.str("text")
                if (!text.isNullOrEmpty()) setAssistantText(text)
                closeAssistantBubble()
            }

            "message.complete" -> {
                val text = p?.str("text")
                if (!text.isNullOrEmpty()) setAssistantText(text)
                endTurn()
            }

            // thinking.delta carries the turn status line ("(◇) orchestrating…")
            // followed by {"text":""} when done — a transient caption, NOT a
            // transcript row. reasoning.delta is the thinking bubble's only source.
            "thinking.delta" -> _statusLine.value = p?.str("text") ?: ""

            "reasoning.delta" -> appendThinking(p?.str("text") ?: "")

            "reasoning.available" -> {
                // Sometimes carries the final answer text instead of reasoning —
                // ignore blanks and answer-echoes, else (re)write the thinking row.
                val t = p?.str("text") ?: ""
                val answer = assistantId?.let { id ->
                    _items.value.firstOrNull { it.id == id }?.text
                } ?: _items.value.lastOrNull { it.kind == ChatKind.Assistant }?.text.orEmpty()
                if (t.isNotBlank() && t.trim() != answer.trim()) setThinking(t)
            }

            "tool.start" -> {
                openToolId = nextId()
                insertAboveAssistant(
                    ChatItem(
                        id = openToolId!!, kind = ChatKind.Tool, done = false,
                        text = "▶ ${p?.str("name") ?: "tool"}",
                        meta = p?.str("context").orEmpty(),
                    )
                )
            }

            "tool.complete" -> {
                val id = openToolId
                if (id != null) {
                    mutate(id) {
                        it.copy(text = "✓ ${p?.str("name") ?: "tool"}", done = true)
                    }
                    openToolId = null
                } else {
                    insertAboveAssistant(
                        ChatItem(
                            id = nextId(), kind = ChatKind.Tool, done = true,
                            text = "✓ ${p?.str("name") ?: "tool"}",
                        )
                    )
                }
            }

            "status.update" -> p?.str("text")?.takeIf { it.isNotBlank() }?.let { append(ChatKind.Status, it) }

            "error" -> {
                append(ChatKind.Error, p?.str("message") ?: p?.str("text") ?: "Unknown error")
                endTurn()
            }

            // Legacy event-style requests (some builds emit *.request events
            // instead of srq frames) become the same cards; they answer via
            // the *.respond RPCs since they carry no frame id.
            in REQUEST_EVENTS ->
                pushRequest(ev.type.removeSuffix(".request"), p ?: JsonObject(emptyMap()), srqId = null)

            // The backend withdrew an open request: {id, method, reason}.
            "request.cancel" -> {
                val rid = p?.str("id")
                resolveRequest(rid, p?.str("reason") ?: "cancelled")
            }

            "session.info" -> {
                val info = p?.get("info") as? JsonObject ?: p
                info?.str("title")?.takeIf { it.isNotBlank() }?.let { _title.value = it }
            }

            "session.title" -> p?.str("title")?.takeIf { it.isNotBlank() }?.let { _title.value = it }

            // sessions.changed / everything else: no transcript row.
            else -> {
                // *.resolved / *.expired / *.answered / *.cancelled / *.timeout
                // carry the request_id they closed.
                if (RESOLVE_SUFFIX.containsMatchIn(ev.type)) {
                    resolveRequest(p?.str("request_id") ?: p?.str("requestId"), "handled elsewhere")
                }
            }
        }
    }

    /** A turn started by another client (the desktop). Show its prompt, then stream its reply. */
    private fun beginPeerTurn() {
        turnStartIndex = _items.value.size
        turnBaseRow = syncCursor
        _streaming.value = true
        val stored = storedSessionId ?: return
        val src = history() ?: return
        val base = turnBaseRow
        scope.launch {
            val rows = runCatching { src.tail(stored, profile, 6) }.getOrNull() ?: return@launch
            val prompts = rows.filter { (it.long("id") ?: 0) > base && it.str("role") == "user" }
            val items = TranscriptMapping.fromDbRows(prompts, ::nextId).filter { it.kind == ChatKind.User }
            val at = turnStartIndex ?: return@launch
            if (items.isEmpty() || at > _items.value.size) return@launch
            _items.value = _items.value.toMutableList().apply { addAll(at, items) }
            turnAssistantIndex = turnAssistantIndex?.plus(items.size)
        }
    }

    /** The turn finished: settle the UI, then swap the live rows for the persisted ones. */
    private fun endTurn() {
        finalizeStreaming()
        if (turnStartIndex != null && persistent) {
            pendingReconcile = true
            scope.launch {
                delay(700)
                reconcileTurn()
            }
        } else {
            turnStartIndex = null
        }
        persist()
    }

    /**
     * Replace the finished turn's live rows with its persisted rows. The last assistant text must
     * match before the swap (the final row can land a moment after `message.complete`); until it
     * does, the poll retries and appends nothing, so nothing is ever shown twice.
     */
    private suspend fun reconcileTurn() {
        if (!pendingReconcile || _streaming.value) return
        val start = turnStartIndex ?: run { pendingReconcile = false; return }
        val stored = storedSessionId ?: return
        val src = history() ?: return
        val base = turnBaseRow
        val rows = runCatching { src.tail(stored, profile, SYNC_FETCH) }.getOrNull() ?: return
        if (_streaming.value || turnStartIndex != start) return
        val fresh = rows.filter { (it.long("id") ?: 0) > base }
        val persisted = TranscriptMapping.fromDbRows(fresh, ::nextId)
        val all = _items.value
        if (start > all.size) { pendingReconcile = false; turnStartIndex = null; return }
        val overlay = all.subList(start, all.size)
        val liveLast = overlay.lastOrNull { it.kind == ChatKind.Assistant }?.text?.trim()
        val dbLast = persisted.lastOrNull { it.kind == ChatKind.Assistant }?.text?.trim()
        if (liveLast != null && liveLast != dbLast) return // final row not written yet
        _items.value = all.subList(0, start) + TranscriptMapping.reconcile(overlay, persisted)
        syncCursor = maxOf(syncCursor, fresh.mapNotNull { it.long("id") }.maxOrNull() ?: base)
        pendingReconcile = false
        turnStartIndex = null
        persist()
    }

    // ---- history poll ---------------------------------------------------------

    private suspend fun dbHead(): Long? {
        val stored = storedSessionId ?: return null
        return runCatching { history()?.tail(stored, profile, 1)?.lastOrNull()?.long("id") }.getOrNull()
    }

    private fun startSync() {
        if (!persistent || syncJob?.isActive == true) return
        syncJob = scope.launch {
            var reconcileTries = 0
            while (true) {
                withTimeoutOrNull(SYNC_POLL_MS) { syncKick.receive() }
                if (!GatewayHub.foregrounded || _opening.value) continue
                if (_streaming.value) {
                    // A turn that went quiet (missed message.complete across a reconnect): ask again.
                    if (System.currentTimeMillis() - lastEventAt > STALL_MS && gateway.state.value == GatewayState.OPEN) {
                        lastEventAt = System.currentTimeMillis()
                        runCatching { refreshAfterReconnect() }
                    }
                    continue
                }
                if (pendingReconcile) {
                    reconcileTurn()
                    if (pendingReconcile && ++reconcileTries >= 5) {
                        // Give up on the swap; adopt the DB head so the poll never double-adds.
                        pendingReconcile = false
                        turnStartIndex = null
                        syncCursor = dbHead() ?: syncCursor
                    }
                    continue
                }
                reconcileTries = 0
                val stored = storedSessionId ?: continue
                val src = history() ?: continue
                val head = runCatching { src.tail(stored, profile, 1) }.getOrNull()
                    ?.lastOrNull()?.long("id") ?: continue
                when {
                    head > syncCursor -> {
                        val rows = runCatching { src.tail(stored, profile, SYNC_FETCH) }.getOrNull() ?: continue
                        if (_streaming.value || pendingReconcile) continue
                        val fresh = rows.filter { (it.long("id") ?: 0) > syncCursor }
                        syncCursor = maxOf(syncCursor, rows.mapNotNull { it.long("id") }.maxOrNull() ?: syncCursor)
                        val add = TranscriptMapping.fromDbRows(fresh, ::nextId)
                        if (add.isNotEmpty()) {
                            _items.value = _items.value + add
                            persist()
                        }
                    }
                    // Rows vanished: undone / rewound / edited elsewhere. Rebuild from the server.
                    head < syncCursor && gateway.state.value == GatewayState.OPEN ->
                        runCatching { resume(stored) }
                }
            }
        }
    }
}

private fun JsonElement.asObj(): JsonObject =
    this as? JsonObject ?: JsonObject(emptyMap())

private fun JsonObject.str(key: String): String? =
    (this[key] as? JsonPrimitive)?.contentOrNull

private fun JsonObject.long(key: String): Long? =
    (this[key] as? JsonPrimitive)?.let { it.longOrNull ?: it.contentOrNull?.toLongOrNull() }
