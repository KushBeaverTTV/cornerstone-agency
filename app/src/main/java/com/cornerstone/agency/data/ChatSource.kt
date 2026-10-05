package com.cornerstone.agency.data

import kotlinx.coroutines.flow.StateFlow

/**
 * Transcript surface shared by ChatRepository (Hermes gateway sessions) and
 * PrimaChatRepository (bridge prime-agent sessions). ChatScreen renders only
 * this interface.
 */
interface ChatSource {
    val items: StateFlow<List<ChatItem>>
    val title: StateFlow<String>
    val opening: StateFlow<Boolean>
    val streaming: StateFlow<Boolean>
    val statusLine: StateFlow<String>
    val lastError: StateFlow<String>

    /** Socket/link state driving the "connecting…" pill and re-resume. */
    val linkState: StateFlow<GatewayState>

    suspend fun open(requestedStoredId: String? = null)
    suspend fun refreshAfterReconnect()
    suspend fun send(text: String)

    /** Send with images / files. Sources without attachment support send the text only. */
    suspend fun send(text: String, attachments: List<OutgoingAttachment>) = send(text)

    /** Whether the composer should offer the attach button. */
    val acceptsAttachments: Boolean get() = false
    suspend fun stop()
    suspend fun newSession()
    suspend fun rename(newTitle: String)
    suspend fun approveRequest(itemId: String, choice: String)
    suspend fun answerClarify(itemId: String, answers: Map<String, String>)
    suspend fun submitRequestValue(itemId: String, value: String)
    suspend fun respondMcp(itemId: String, approved: Boolean)
    fun close()
}

/**
 * Per-message actions that need durable row ids on the server (Hermes sessions). Prima chats do
 * not implement this; the UI then offers only the client-side actions (copy, share, read aloud).
 */
interface MessageActions {
    /** Re-run from a user message with new text; drops it and everything after it. */
    suspend fun editAndResend(userItemId: String, newText: String): Boolean
    /** Re-run the prompt that produced an assistant message. */
    suspend fun regenerate(assistantItemId: String): Boolean
    /** Copy the conversation through this message into a new chat; returns its stored id. */
    suspend fun branchFrom(itemId: String): String?
    /** Tapback; the same emoji again clears it. */
    suspend fun react(itemId: String, emoji: String)
    /** True when the server can address this row (it has been persisted). */
    fun canAddress(item: ChatItem): Boolean = item.rowId != null
}
