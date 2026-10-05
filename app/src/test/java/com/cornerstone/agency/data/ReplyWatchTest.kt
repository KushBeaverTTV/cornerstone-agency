package com.cornerstone.agency.data

import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ReplyWatchTest {
    private fun row(id: Long, role: String, content: String, tool: String? = null) = buildJsonObject {
        put("id", id); put("role", role); put("content", content)
        if (tool != null) put("tool_name", tool)
    }

    @Test
    fun replyCountsOnlyWhenTheTurnHasFinished() {
        val prompt = row(10, "user", "check the build")
        // Still working: the newest row is a tool call.
        assertNull(ReplyWatch.landedReply(listOf(prompt, row(11, "assistant", "Looking."), row(12, "tool", "{}", "terminal")), 9))
        // Finished: the newest visible row is the answer.
        assertEquals(
            "Build is green.",
            ReplyWatch.landedReply(listOf(prompt, row(11, "assistant", "Looking."), row(12, "tool", "{}", "terminal"), row(13, "assistant", "Build is green.")), 10),
        )
    }

    @Test
    fun anOlderAnswerIsNotTheReply() {
        assertNull(ReplyWatch.landedReply(listOf(row(5, "assistant", "old answer"), row(6, "user", "new question")), 6))
        assertNull(ReplyWatch.landedReply(listOf(row(5, "assistant", "old answer")), 6))
    }
}
