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
    suspend fun stop()
    suspend fun newSession()
    suspend fun rename(newTitle: String)
    suspend fun approveRequest(itemId: String, choice: String)
    suspend fun answerClarify(itemId: String, answers: Map<String, String>)
    suspend fun submitRequestValue(itemId: String, value: String)
    suspend fun respondMcp(itemId: String, approved: Boolean)
    fun close()
}
