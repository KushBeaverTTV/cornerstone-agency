package com.cornerstone.agency.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import java.util.concurrent.atomic.AtomicLong

private val JSON = Json { ignoreUnknownKeys = true }

private val REQUEST_EVENTS = setOf(
    "approval.request", "clarify.request", "secret.request",
    "sudo.request", "mcp.setup.request",
)

/**
 * One bot's chat: resolves a live session for [profile], hydrates the transcript
 * via session.resume (stored id → runtime snapshot with messages[]), then renders
 * server-pushed events as ChatItems.
 *
 * ID discipline (tui_gateway contract): session.list/delete take STORED ids;
 * session.resume takes a stored id and returns a runtime `session_id`; prompt,
 * interrupt and event session_ids are the runtime id.
 */
class ChatRepository(
    private val gateway: GatewaySocket,
    val profile: String,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val localId = AtomicLong(0)

    private val _items = MutableStateFlow<List<ChatItem>>(emptyList())
    val items: StateFlow<List<ChatItem>> = _items.asStateFlow()

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _opening = MutableStateFlow(true)
    val opening: StateFlow<Boolean> = _opening.asStateFlow()

    private val _streaming = MutableStateFlow(false)
    val streaming: StateFlow<Boolean> = _streaming.asStateFlow()

    /** Transient turn status line ("(◇) orchestrating…") — NOT a transcript row. */
    private val _statusLine = MutableStateFlow("")
    val statusLine: StateFlow<String> = _statusLine.asStateFlow()

    private val _lastError = MutableStateFlow("")
    val lastError: StateFlow<String> = _lastError.asStateFlow()

    var storedSessionId: String? = null
        private set
    var runtimeSessionId: String? = null
        private set

    // Turn-scoped render state: one streaming assistant bubble (its list index is
    // the insertion point for thinking/tool rows so they always sit ABOVE the
    // answer), one coalesced thinking bubble, one open tool line.
    private var assistantId: String? = null
    private var turnAssistantIndex: Int? = null
    private var thinkingId: String? = null
    private var openToolId: String? = null

    init {
        scope.launch { gateway.events.collect(::onEvent) }
    }

    // ---- session resolution ---------------------------------------------

    /**
     * Herald's order: explicit stored id → resume; else session.list{profile}
     * most-recent → resume; else session.create{profile}.
     */
    suspend fun open(requestedStoredId: String? = null) {
        _opening.value = true
        try {
            when {
                requestedStoredId != null -> resume(requestedStoredId)
                else -> {
                    val sessions = listSessions()
                    val recent = sessions.firstOrNull()
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
    suspend fun refreshAfterReconnect() {
        val stored = storedSessionId ?: return
        try {
            resume(stored)
        } catch (_: Exception) {
        }
    }

    private suspend fun resume(storedId: String) {
        val res = gateway.call(
            "session.resume",
            buildJsonObject { put("session_id", storedId) },
        ).asObj()
        runtimeSessionId = res.str("session_id") ?: runtimeSessionId
        storedSessionId = res.str("stored_session_id")
            ?: res.str("session_key") ?: res.str("resumed") ?: storedId
        hydrate(res)
        fetchTitle()
    }

    private suspend fun create() {
        val res = gateway.call(
            "session.create",
            buildJsonObject { put("profile", profile) },
        ).asObj()
        runtimeSessionId = res.str("session_id")
        storedSessionId = res.str("stored_session_id") ?: storedSessionId
        hydrate(res)
    }

    suspend fun listSessions(): List<SessionSummary> =
        listProfileSessions(gateway, profile)

    // ---- actions ----------------------------------------------------------

    suspend fun send(text: String) {
        val t = text.trim()
        if (t.isEmpty()) return
        if (runtimeSessionId == null) {
            try {
                create()
            } catch (e: Exception) {
                append(ChatKind.Error, "Could not start a session: ${friendly(e)}")
                return
            }
        }
        append(ChatKind.User, t)
        _streaming.value = true
        _statusLine.value = ""
        try {
            gateway.call(
                "prompt.submit",
                buildJsonObject {
                    put("session_id", runtimeSessionId)
                    put("text", t)
                },
            )
        } catch (e: Exception) {
            _streaming.value = false
            append(ChatKind.Error, "Send failed: ${friendly(e)}")
        }
    }

    suspend fun stop() {
        val rt = runtimeSessionId ?: return
        try {
            gateway.call("session.interrupt", buildJsonObject { put("session_id", rt) })
        } catch (_: Exception) {
        }
        finalizeStreaming()
    }

    suspend fun newSession() {
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

    suspend fun deleteSession(storedId: String) {
        gateway.call("session.delete", buildJsonObject { put("session_id", storedId) })
    }

    suspend fun rename(newTitle: String) {
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
    }

    private suspend fun fetchTitle() {
        val id = runtimeSessionId ?: storedSessionId ?: return
        try {
            val res = gateway.call(
                "session.title",
                buildJsonObject { put("session_id", id) },
            ).asObj()
            res.str("title")?.takeIf { it.isNotBlank() }?.let { _title.value = it }
        } catch (_: Exception) {
        }
    }

    /** Leaving the chat: best-effort interrupt of a live turn, then tear down. */
    @OptIn(DelicateCoroutinesApi::class)
    fun close() {
        if (_streaming.value) {
            val rt = runtimeSessionId
            if (rt != null) {
                // Outlives this repo's scope so the interrupt still reaches the wire.
                GlobalScope.launch(Dispatchers.IO) {
                    runCatching {
                        gateway.call(
                            "session.interrupt",
                            buildJsonObject { put("session_id", rt) },
                        )
                    }
                }
            }
        }
        scope.cancel()
    }

    // ---- transcript hydration ----------------------------------------------

    private fun hydrate(res: JsonObject) {
        val msgs = res["messages"] as? JsonArray
        val list = mutableListOf<ChatItem>()
        msgs?.forEach { el ->
            val m = el as? JsonObject ?: return@forEach
            if (m.str("display_kind") == "hidden") return@forEach
            when (m.str("role")) {
                "user" -> list += ChatItem(
                    id = nextId(), kind = ChatKind.User, text = m.str("text") ?: "",
                )

                "assistant" -> {
                    val text = m.str("text") ?: ""
                    // reasoning.available can carry the final ANSWER verbatim —
                    // don't render it as a duplicate thinking bubble.
                    m.str("reasoning")
                        ?.takeIf { it.isNotBlank() && it.trim() != text.trim() }
                        ?.let {
                            list += ChatItem(id = nextId(), kind = ChatKind.Thinking, text = it)
                        }
                    list += ChatItem(
                        id = nextId(), kind = ChatKind.Assistant, text = text,
                    )
                }

                "tool" -> list += ChatItem(
                    id = nextId(), kind = ChatKind.Tool,
                    text = "✓ ${m.str("name") ?: "tool"}",
                    meta = m.str("context") ?: "",
                )
            }
        }
        // Requests still open on the server come back as placeholders too.
        (res["open_requests"] as? JsonArray)?.forEach { el ->
            val r = el as? JsonObject ?: return@forEach
            val payload = r["payload"] as? JsonObject ?: r
            list += ChatItem(
                id = nextId(), kind = ChatKind.Request,
                text = payload.str("title") ?: payload.str("prompt")
                    ?: r.str("type") ?: "Request pending",
            )
        }
        _items.value = list
        assistantId = null
        turnAssistantIndex = null
        thinkingId = null
        openToolId = null
        _statusLine.value = ""
        _streaming.value = res["running"]?.jsonPrimitive?.booleanOrNull == true
    }

    // ---- live events ---------------------------------------------------------

    private fun onEvent(ev: GatewayEvent) {
        val sid = ev.sessionId
        if (sid != null && sid != runtimeSessionId && sid != storedSessionId) return
        val p = ev.payload
        when (ev.type) {
            "message.start" -> startAssistant()

            "message.delta" ->
                appendAssistantDelta(p?.str("delta") ?: p?.str("text") ?: "")

            "message.interim", "message.complete" -> {
                val text = p?.str("text")
                if (!text.isNullOrEmpty()) setAssistantText(text)
                finalizeStreaming()
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
                } ?: ""
                if (t.isNotBlank() && t.trim() != answer.trim()) setThinking(t)
            }

            "tool.start" -> {
                openToolId = nextId()
                insertAboveAssistant(
                    ChatItem(
                        id = openToolId!!, kind = ChatKind.Tool, done = false,
                        text = "▶ ${p?.str("name") ?: "tool"}",
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

            "status.update" -> append(ChatKind.Status, p?.str("text") ?: "")

            "error" -> {
                append(ChatKind.Error, p?.str("message") ?: p?.str("text") ?: "Unknown error")
                finalizeStreaming()
            }

            in REQUEST_EVENTS -> append(
                ChatKind.Request,
                p?.str("title") ?: p?.str("prompt") ?: ev.type,
            )

            "session.info" -> {
                val info = p?.get("info") as? JsonObject ?: p
                info?.str("title")?.takeIf { it.isNotBlank() }?.let { _title.value = it }
            }

            // sessions.changed / everything else: no transcript row.
            else -> Unit
        }
    }

    // ---- item mutation helpers -------------------------------------------------

    private fun nextId() = "i${localId.incrementAndGet()}"

    private fun append(kind: ChatKind, text: String) {
        append(ChatItem(id = nextId(), kind = kind, text = text))
    }

    private fun append(item: ChatItem) {
        _items.value = _items.value + item
    }

    /**
     * Insert a row just above the current turn's assistant bubble (keeping
     * thinking/tool lines visually before the answer) or append if the bubble
     * doesn't exist yet. Each insert bumps [turnAssistantIndex] so later rows
     * stay ordered.
     */
    private fun insertAboveAssistant(item: ChatItem) {
        val idx = turnAssistantIndex
        if (idx != null && idx <= _items.value.size) {
            _items.value = _items.value.toMutableList().apply { add(idx, item) }
            turnAssistantIndex = idx + 1
        } else {
            append(item)
        }
    }

    private fun mutate(id: String, f: (ChatItem) -> ChatItem) {
        _items.value = _items.value.map { if (it.id == id) f(it) else it }
    }

    private fun startAssistant() {
        thinkingId = null
        val id = nextId()
        assistantId = id
        turnAssistantIndex = _items.value.size
        append(ChatItem(id = id, kind = ChatKind.Assistant, text = "", streaming = true))
        _streaming.value = true
    }

    private fun appendAssistantDelta(delta: String) {
        if (delta.isEmpty()) return
        val id = assistantId ?: run {
            startAssistant()
            assistantId!!
        }
        mutate(id) { it.copy(text = it.text + delta, streaming = true) }
    }

    private fun setAssistantText(text: String) {
        val id = assistantId ?: run {
            val newId = nextId()
            assistantId = newId
            turnAssistantIndex = _items.value.size
            append(ChatItem(id = newId, kind = ChatKind.Assistant, text = text))
            newId
        }
        mutate(id) { it.copy(text = text, streaming = false, done = true) }
    }

    private fun appendThinking(delta: String) {
        if (delta.isEmpty()) return
        val id = thinkingId
        if (id == null) {
            val newId = nextId()
            thinkingId = newId
            insertAboveAssistant(ChatItem(id = newId, kind = ChatKind.Thinking, text = delta))
        } else {
            mutate(id) { it.copy(text = it.text + delta) }
        }
    }

    private fun setThinking(text: String) {
        val id = thinkingId
        if (id == null) {
            val newId = nextId()
            thinkingId = newId
            insertAboveAssistant(ChatItem(id = newId, kind = ChatKind.Thinking, text = text))
        } else {
            mutate(id) { it.copy(text = text) }
        }
    }

    private fun finalizeStreaming() {
        _streaming.value = false
        _statusLine.value = ""
        _items.value = _items.value.map {
            if (it.streaming || !it.done) it.copy(streaming = false, done = true) else it
        }
        assistantId = null
        turnAssistantIndex = null
        thinkingId = null
        openToolId = null
    }

    private fun friendly(e: Exception): String = when (e) {
        is GatewayRpcException -> e.message ?: "rpc error ${e.code}"
        is java.io.IOException -> "socket unavailable"
        else -> e.message ?: e.javaClass.simpleName
    }
}

private fun kotlinx.serialization.json.JsonElement.asObj(): JsonObject =
    this as? JsonObject ?: JsonObject(emptyMap())

private fun JsonObject.str(key: String): String? =
    (this[key] as? kotlinx.serialization.json.JsonPrimitive)?.contentOrNull

/** session.list for one profile — the server scopes the listing per profile DB. */
internal suspend fun listProfileSessions(
    gateway: GatewaySocket,
    profile: String,
    limit: Int = 200,
): List<SessionSummary> {
    val res = gateway.call(
        "session.list",
        buildJsonObject {
            put("profile", profile)
            put("limit", limit)
        },
    )
    return runCatching {
        JSON.decodeFromJsonElement(SessionListResult.serializer(), res)
    }.getOrNull()?.sessions
        ?.sortedByDescending { it.startedAt }
        ?: emptyList()
}

internal suspend fun deleteStoredSession(gateway: GatewaySocket, storedId: String) {
    gateway.call("session.delete", buildJsonObject { put("session_id", storedId) })
}
