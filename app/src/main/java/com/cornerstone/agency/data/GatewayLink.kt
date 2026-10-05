package com.cornerstone.agency.data

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

/**
 * The JSON-RPC surface chat code needs from the Hermes socket. [GatewaySocket] is the real one;
 * tests drive [ChatRepository] with a fake.
 */
interface GatewayLink {
    val state: StateFlow<GatewayState>
    val events: SharedFlow<GatewayEvent>
    val requests: SharedFlow<GatewayRequest>
    suspend fun call(method: String, params: JsonObject = JsonObject(emptyMap())): JsonElement
    fun respond(requestId: JsonElement, result: JsonObject): Boolean
}

/** Read-only transcript rows straight from the state DB (`GET /api/sessions/{id}/messages`). */
fun interface HistorySource {
    /** Newest [limit] rows of [storedId], returned oldest-first. */
    suspend fun tail(storedId: String, profile: String, limit: Int): List<JsonObject>
}
