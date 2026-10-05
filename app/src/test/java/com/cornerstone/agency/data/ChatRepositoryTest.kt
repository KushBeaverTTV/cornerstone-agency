package com.cornerstone.agency.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Drives ChatRepository with a fake socket + fake state DB (no Android, virtual time). */
@OptIn(ExperimentalCoroutinesApi::class)
class ChatRepositoryTest {

    private class FakeGateway : GatewayLink {
        override val state = MutableStateFlow(GatewayState.OPEN)
        override val events = MutableSharedFlow<GatewayEvent>(extraBufferCapacity = 256)
        override val requests = MutableSharedFlow<GatewayRequest>(extraBufferCapacity = 16)
        val calls = mutableListOf<Pair<String, JsonObject>>()
        var resumeMessages: JsonArray = JsonArray(emptyList())
        var resumeExtra: JsonObject = JsonObject(emptyMap())

        override suspend fun call(method: String, params: JsonObject): JsonElement {
            calls += method to params
            return when (method) {
                "session.resume" -> JsonObject(
                    mapOf(
                        "session_id" to JsonPrimitive("rt1"),
                        "session_key" to JsonPrimitive("stored1"),
                        "messages" to resumeMessages,
                    ) + resumeExtra,
                )
                "prompt.submit" -> buildJsonObject { put("status", "streaming"); put("user_row_id", 105) }
                "session.list" -> buildJsonObject { put("sessions", buildJsonArray { }) }
                else -> JsonObject(emptyMap())
            }
        }

        override fun respond(requestId: JsonElement, result: JsonObject) = true

        fun emit(type: String, payload: JsonObject = JsonObject(emptyMap()), sid: String = "rt1") {
            check(events.tryEmit(GatewayEvent(type, sid, payload)))
        }
    }

    /** Rows in the fake state DB (what GET /api/sessions/{id}/messages would return). */
    private class FakeDb : HistorySource {
        val rows = mutableListOf<JsonObject>()
        override suspend fun tail(storedId: String, profile: String, limit: Int) = rows.takeLast(limit)
        fun add(id: Long, role: String, content: String, tool: String? = null) {
            rows += buildJsonObject {
                put("id", id); put("role", role); put("content", content)
                if (tool != null) put("tool_name", tool)
            }
        }
    }

    private fun msg(role: String, text: String, row: Long) = buildJsonObject {
        put("role", role); put("text", text); put("row_id", row)
    }

    private fun text(t: String) = buildJsonObject { put("text", t) }

    private fun TestScope.repo(gw: FakeGateway, db: FakeDb) =
        ChatRepository(gw, "default", persistent = true, history = { db },
            dispatcher = StandardTestDispatcher(testScheduler), cacheEnabled = false)

    @Test
    fun resumeHydratesWithDurableRowIds() = runTest {
        val gw = FakeGateway()
        val db = FakeDb().apply { add(10, "user", "hi"); add(11, "assistant", "hello") }
        gw.resumeMessages = JsonArray(listOf(msg("user", "hi", 10), msg("assistant", "hello", 11)))
        val chat = repo(gw, db)
        chat.open("stored1")
        runCurrent()
        assertEquals(listOf(10L, 11L), chat.items.value.map { it.rowId })
        assertEquals("stored1", chat.storedSessionId)
        chat.close()
    }

    @Test
    fun localTurnStreamsThenSwapsToPersistedRows() = runTest {
        val gw = FakeGateway()
        val db = FakeDb().apply { add(100, "user", "earlier") }
        gw.resumeMessages = JsonArray(listOf(msg("user", "earlier", 100)))
        val chat = repo(gw, db)
        chat.open("stored1"); runCurrent()

        chat.send("what's up"); runCurrent()
        assertTrue(chat.streaming.value)
        val submit = gw.calls.last { it.first == "prompt.submit" }.second
        assertEquals("rt1", (submit["session_id"] as JsonPrimitive).content)

        gw.emit("message.start"); gw.emit("message.delta", text("Check")); runCurrent()
        gw.emit("message.interim", text("Checking.")); gw.emit("tool.start", buildJsonObject { put("name", "terminal") })
        gw.emit("tool.complete", buildJsonObject { put("name", "terminal") }); runCurrent()
        // interim is NOT the end of the turn
        assertTrue(chat.streaming.value)
        gw.emit("message.start"); gw.emit("message.delta", text("All good")); runCurrent()
        val liveIds = chat.items.value.map { it.id }

        db.add(105, "user", "what's up"); db.add(106, "assistant", "Checking.")
        db.add(107, "tool", "{}", tool = "terminal"); db.add(108, "assistant", "All good")
        gw.emit("message.complete", text("All good")); runCurrent()
        assertFalse(chat.streaming.value)
        advanceTimeBy(800); runCurrent()

        val items = chat.items.value
        assertEquals(listOf("earlier", "what's up", "Checking.", "✓ terminal", "All good"), items.map { it.text })
        assertEquals(listOf(100L, 105L, 106L, 107L, 108L), items.map { it.rowId })
        // Same keys as the live rows: no re-animation when the swap lands.
        assertTrue(liveIds.containsAll(items.drop(1).map { it.id }))
        chat.close()
    }

    @Test
    fun peerTurnFromDesktopShowsPromptAndStreams() = runTest {
        val gw = FakeGateway()
        val db = FakeDb().apply { add(50, "assistant", "hey") }
        gw.resumeMessages = JsonArray(listOf(msg("assistant", "hey", 50)))
        val chat = repo(gw, db)
        chat.open("stored1"); runCurrent()

        // The desktop sends a prompt: its row lands, then the reply streams to every viewer.
        db.add(51, "user", "typed on the desktop")
        gw.emit("message.start"); gw.emit("message.delta", text("Sure")); runCurrent()
        assertTrue(chat.streaming.value)
        assertEquals(listOf("hey", "typed on the desktop", "Sure"), chat.items.value.map { it.text })

        db.add(52, "assistant", "Sure thing")
        gw.emit("message.complete", text("Sure thing")); runCurrent()
        advanceTimeBy(800); runCurrent()
        assertEquals(listOf(50L, 51L, 52L), chat.items.value.map { it.rowId })
        assertTrue(gw.calls.none { it.first == "prompt.submit" })
        chat.close()
    }

    @Test
    fun pollAppendsRowsFromOtherProcessesAndRebuildsOnUndo() = runTest {
        val gw = FakeGateway()
        val db = FakeDb().apply { add(1, "user", "a"); add(2, "assistant", "b") }
        gw.resumeMessages = JsonArray(listOf(msg("user", "a", 1), msg("assistant", "b", 2)))
        val chat = repo(gw, db)
        chat.open("stored1"); runCurrent()

        db.add(3, "user", "from telegram"); db.add(4, "assistant", "reply")
        advanceTimeBy(3_100); runCurrent()
        assertEquals(listOf("a", "b", "from telegram", "reply"), chat.items.value.map { it.text })

        // Undo elsewhere: rows 3-4 gone -> rebuild from the server.
        db.rows.removeAll { (it["id"] as JsonPrimitive).content.toLong() > 2 }
        val resumes = gw.calls.count { it.first == "session.resume" }
        advanceTimeBy(3_100); runCurrent()
        assertEquals(resumes + 1, gw.calls.count { it.first == "session.resume" })
        assertEquals(listOf("a", "b"), chat.items.value.map { it.text })
        chat.close()
    }

    @Test
    fun leavingNeverInterruptsASharedTurn() = runTest {
        val gw = FakeGateway()
        val chat = repo(gw, FakeDb())
        chat.open("stored1"); runCurrent()
        gw.emit("message.start"); gw.emit("message.delta", text("working")); runCurrent()
        assertTrue(chat.streaming.value)
        chat.close(); runCurrent()
        assertTrue(gw.calls.none { it.first == "session.interrupt" })
    }

    @Test
    fun editResendTruncatesAtTheRowAndRegenerateReusesThePrompt() = runTest {
        val gw = FakeGateway()
        val db = FakeDb().apply { add(7, "user", "first"); add(8, "assistant", "one") }
        gw.resumeMessages = JsonArray(listOf(msg("user", "first", 7), msg("assistant", "one", 8)))
        val chat = repo(gw, db)
        chat.open("stored1"); runCurrent()

        val answer = chat.items.value.last()
        assertTrue(chat.regenerate(answer.id)); runCurrent()
        val p = gw.calls.last { it.first == "prompt.submit" }.second
        assertEquals("first", (p["text"] as JsonPrimitive).content)
        assertEquals("7", (p["truncate_before_row_id"] as JsonPrimitive).content)
        assertEquals("true", (p["confirm_truncate"] as JsonPrimitive).content)
        // Everything from the edited prompt on is replaced locally too.
        assertEquals(listOf("first"), chat.items.value.map { it.text })
        chat.close()
    }

    @Test
    fun joiningMidTurnShowsInflightReply() = runTest {
        val gw = FakeGateway()
        gw.resumeMessages = JsonArray(listOf(msg("user", "go", 20)))
        gw.resumeExtra = buildJsonObject {
            put("running", true)
            put("inflight", buildJsonObject { put("user", "go"); put("assistant", "Half way") })
        }
        val chat = repo(gw, FakeDb())
        chat.open("stored1"); runCurrent()
        assertTrue(chat.streaming.value)
        assertEquals(listOf("go", "Half way"), chat.items.value.map { it.text })
        gw.emit("message.delta", text(" there")); runCurrent()
        assertEquals("Half way there", chat.items.value.last().text)
        assertNotNull(chat.items.value.last().streaming.takeIf { it })
        chat.close()
    }
}
