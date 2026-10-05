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

/**
 * Transcript state shared by every Hermes chat: the item list and its flows, plus the streaming
 * assembly rules (one live assistant bubble per round, thinking/tool rows inserted above it).
 * [ChatRequestCards] adds interactive request cards; [ChatRepository] adds sessions, sync and
 * actions on top.
 */
abstract class ChatTranscript(
    protected val gateway: GatewayLink,
    val profile: String,
    dispatcher: CoroutineDispatcher,
) : ChatSource {
    protected val scope = CoroutineScope(SupervisorJob() + dispatcher)
    protected val localId = AtomicLong(0)

    override val linkState: StateFlow<GatewayState> = gateway.state

    protected val _items = MutableStateFlow<List<ChatItem>>(emptyList())
    override val items: StateFlow<List<ChatItem>> = _items.asStateFlow()

    protected val _title = MutableStateFlow("")
    override val title: StateFlow<String> = _title.asStateFlow()

    protected val _opening = MutableStateFlow(true)
    override val opening: StateFlow<Boolean> = _opening.asStateFlow()

    protected val _streaming = MutableStateFlow(false)
    override val streaming: StateFlow<Boolean> = _streaming.asStateFlow()

    /** Transient turn status line ("(◇) orchestrating…") — NOT a transcript row. */
    protected val _statusLine = MutableStateFlow("")
    override val statusLine: StateFlow<String> = _statusLine.asStateFlow()

    protected val _lastError = MutableStateFlow("")
    override val lastError: StateFlow<String> = _lastError.asStateFlow()

    /** True while the transcript on screen is the on-device copy (not yet refreshed from the PC). */
    protected val _fromCache = MutableStateFlow(false)
    val fromCache: StateFlow<Boolean> = _fromCache.asStateFlow()


    // Turn-scoped render state: one streaming assistant bubble (its list index is the insertion
    // point for thinking/tool rows so they sit ABOVE the answer), one thinking bubble, one tool.
    protected var assistantId: String? = null
    protected var turnAssistantIndex: Int? = null
    protected var thinkingId: String? = null
    protected var openToolId: String? = null


    var storedSessionId: String? = null
        protected set
    var runtimeSessionId: String? = null
        protected set

    // ---- item mutation helpers -------------------------------------------------

    protected fun nextId() = "i${localId.incrementAndGet()}"

    protected fun append(kind: ChatKind, text: String) {
        append(ChatItem(id = nextId(), kind = kind, text = text))
    }

    protected fun append(item: ChatItem) {
        _items.value = _items.value + item
    }

    /**
     * Insert a row just above the current turn's assistant bubble (keeping
     * thinking/tool lines visually before the answer) or append if the bubble
     * doesn't exist yet. Each insert bumps [turnAssistantIndex] so later rows
     * stay ordered.
     */
    protected fun insertAboveAssistant(item: ChatItem) {
        val idx = turnAssistantIndex
        if (idx != null && idx <= _items.value.size && assistantId != null) {
            _items.value = _items.value.toMutableList().apply { add(idx, item) }
            turnAssistantIndex = idx + 1
        } else {
            append(item)
        }
    }

    protected fun mutate(id: String, f: (ChatItem) -> ChatItem) {
        _items.value = _items.value.map { if (it.id == id) f(it) else it }
    }

    protected fun startAssistant() {
        thinkingId = null
        val id = nextId()
        assistantId = id
        turnAssistantIndex = _items.value.size
        append(ChatItem(id = id, kind = ChatKind.Assistant, text = "", streaming = true, done = false))
        _streaming.value = true
    }

    protected fun appendAssistantDelta(delta: String) {
        if (delta.isEmpty()) return
        val id = assistantId ?: run {
            startAssistant()
            assistantId!!
        }
        mutate(id) { it.copy(text = it.text + delta, streaming = true) }
    }

    protected fun setAssistantText(text: String) {
        val id = assistantId ?: run {
            val newId = nextId()
            assistantId = newId
            turnAssistantIndex = _items.value.size
            append(ChatItem(id = newId, kind = ChatKind.Assistant, text = text))
            newId
        }
        mutate(id) { it.copy(text = text, streaming = false, done = true) }
    }

    /** The bubble is finished but the turn is not (a tool round follows). */
    protected fun closeAssistantBubble() {
        assistantId?.let { id -> mutate(id) { it.copy(streaming = false, done = true) } }
        // An empty interim bubble (tool call with no preamble) is noise.
        _items.value = _items.value.filterNot { it.id == assistantId && it.text.isBlank() }
        assistantId = null
        turnAssistantIndex = null
        thinkingId = null
    }

    protected fun appendThinking(delta: String) {
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

    protected fun setThinking(text: String) {
        val id = thinkingId
        if (id == null) {
            val newId = nextId()
            thinkingId = newId
            insertAboveAssistant(ChatItem(id = newId, kind = ChatKind.Thinking, text = text))
        } else {
            mutate(id) { it.copy(text = text) }
        }
    }

    protected fun finalizeStreaming() {
        _streaming.value = false
        _statusLine.value = ""
        _items.value = _items.value
            .filterNot { it.kind == ChatKind.Assistant && it.text.isBlank() && it.attachments.isEmpty() }
            .map { if (it.streaming || !it.done) it.copy(streaming = false, done = true) else it }
        assistantId = null
        turnAssistantIndex = null
        thinkingId = null
        openToolId = null
    }

    protected fun friendly(e: Exception): String = when (e) {
        is GatewayRpcException -> e.message ?: "rpc error ${e.code}"
        is java.io.IOException -> "socket unavailable"
        else -> e.message ?: e.javaClass.simpleName
    }
}

private fun JsonElement.asObj(): JsonObject =
    this as? JsonObject ?: JsonObject(emptyMap())

private fun JsonObject.str(key: String): String? =
    (this[key] as? JsonPrimitive)?.contentOrNull

private fun JsonObject.long(key: String): Long? =
    (this[key] as? JsonPrimitive)?.let { it.longOrNull ?: it.contentOrNull?.toLongOrNull() }
