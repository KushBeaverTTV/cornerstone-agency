package com.cornerstone.agency.data

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SessionDeleteTest {
    private class Gw(val running: Boolean) : GatewayLink {
        override val state = MutableStateFlow(GatewayState.OPEN)
        override val events = MutableSharedFlow<GatewayEvent>()
        override val requests = MutableSharedFlow<GatewayRequest>()
        val calls = mutableListOf<String>()
        var loaded = true

        override suspend fun call(method: String, params: JsonObject): JsonElement {
            calls += method
            return when (method) {
                "session.delete" -> if (loaded) throw GatewayRpcException(4023, "cannot delete an active session")
                    else buildJsonObject { put("deleted", "s1") }
                "session.resume" -> buildJsonObject { put("session_id", "rt9"); put("running", running) }
                "session.close" -> { loaded = false; buildJsonObject { put("closed", true) } }
                else -> buildJsonObject { }
            }
        }

        override fun respond(requestId: JsonElement, result: JsonObject) = true
    }

    @Test
    fun aLoadedIdleSessionIsUnloadedThenDeleted() = runTest {
        val gw = Gw(running = false)
        assertTrue(removeStoredSession(gw, "s1", "default"))
        assertEquals(listOf("session.delete", "session.resume", "session.close", "session.delete"), gw.calls)
    }

    @Test
    fun aSessionWithARunningTurnIsOnlyHidden() = runTest {
        val gw = Gw(running = true)
        assertFalse(removeStoredSession(gw, "s1", "default"))
        assertTrue("session.close" !in gw.calls)
        assertEquals("session.set_hidden", gw.calls.last())
    }
}
