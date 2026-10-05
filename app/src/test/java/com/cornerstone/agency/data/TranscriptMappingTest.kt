package com.cornerstone.agency.data

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TranscriptMappingTest {
    private var n = 0
    private val id = { "t${++n}" }

    @Test
    fun imageRefsBecomeAttachmentsNotText() {
        val (text, atts) = TranscriptMapping.splitAttachments(
            "Look at this\n\n@image:C:\\Users\\me\\upload_1.png\n[User attached image: upload_1.png]",
        )
        assertEquals("Look at this", text)
        assertEquals(1, atts.size)
        assertEquals("C:\\Users\\me\\upload_1.png", atts[0].remotePath)
        assertEquals("image", atts[0].kind)
    }

    @Test
    fun fileRefsAreListedAndStrippedFromText() {
        val (text, atts) = TranscriptMapping.splitAttachments("Summarize this\n@file:`uploads/report.pdf`")
        assertEquals("Summarize this", text)
        assertEquals(listOf("report.pdf"), atts.map { it.name })
        assertEquals("file", atts[0].kind)
    }

    @Test
    fun resumeSkipsHiddenAndEmptyAssistantRowsAndReadsReactions() {
        val msgs = buildJsonArray {
            add(buildJsonObject { put("role", "user"); put("text", "hi"); put("row_id", 1) })
            add(buildJsonObject { put("role", "assistant"); put("text", ""); put("row_id", 2) })
            add(buildJsonObject { put("role", "tool"); put("name", "terminal"); put("context", "ls") })
            add(buildJsonObject {
                put("role", "assistant"); put("text", "done"); put("row_id", 4)
                put("display_metadata", buildJsonObject {
                    put("reactions", buildJsonArray { add(buildJsonObject { put("emoji", "👍"); put("author", "user") }) })
                })
            })
            add(buildJsonObject { put("role", "user"); put("text", "x"); put("display_kind", "hidden") })
        }
        val items = TranscriptMapping.fromResume(msgs, id)
        assertEquals(listOf(ChatKind.User, ChatKind.Tool, ChatKind.Assistant), items.map { it.kind })
        assertEquals(listOf(Reaction("👍", "user")), items.last().reactions)
    }

    @Test
    fun dbRowsMatchResumeRendering() {
        val rows = listOf(
            buildJsonObject { put("id", 5); put("role", "user"); put("content", "hello") },
            buildJsonObject { put("id", 6); put("role", "tool"); put("tool_name", "read_file"); put("content", "{}") },
            buildJsonObject { put("id", 7); put("role", "user"); put("content", "[System: scaffold]") },
            buildJsonObject {
                put("id", 8); put("role", "assistant")
                put("content", buildJsonArray { add(buildJsonObject { put("type", "text"); put("text", "hi there") }) })
            },
        )
        val items = TranscriptMapping.fromDbRows(rows, id)
        assertEquals(listOf("hello", "✓ read_file", "hi there"), items.map { it.text })
        assertEquals(listOf(5L, 6L, 8L), items.map { it.rowId })
    }

    @Test
    fun reconcileReusesIdsKeepsLocalPreviewsAndRequests() {
        val local = ChatAttachment("p.jpg", localUri = "content://x")
        val overlay = listOf(
            ChatItem("a", ChatKind.User, "pic?", attachments = listOf(local)),
            ChatItem("b", ChatKind.Tool, "▶ vision"),
            ChatItem("c", ChatKind.Assistant, "A cat."),
            ChatItem("r", ChatKind.Request, "approve?"),
        )
        val persisted = listOf(
            ChatItem("n1", ChatKind.User, "pic?", rowId = 1,
                attachments = listOf(ChatAttachment("p.jpg", remotePath = "C:/p.jpg"))),
            ChatItem("n2", ChatKind.Tool, "✓ vision", rowId = 2),
            ChatItem("n3", ChatKind.Assistant, "A cat.", rowId = 3),
        )
        val out = TranscriptMapping.reconcile(overlay, persisted)
        assertEquals(listOf("a", "b", "c", "r"), out.map { it.id })
        assertEquals(listOf(1L, 2L, 3L, null), out.map { it.rowId })
        assertEquals("content://x", out[0].attachments.single().localUri)
    }

    @Test
    fun branchCountCountsVisibleTurnsThroughTheItem() {
        val items = listOf(
            ChatItem("1", ChatKind.User, "a"), ChatItem("2", ChatKind.Tool, "t"),
            ChatItem("3", ChatKind.Assistant, "b"), ChatItem("4", ChatKind.User, "c"),
            ChatItem("5", ChatKind.Assistant, "d"),
        )
        assertEquals(2, TranscriptMapping.branchCount(items, "3"))
        assertEquals(4, TranscriptMapping.branchCount(items, "5"))
        assertEquals("4", TranscriptMapping.promptFor(items, "5")?.id)
        assertTrue(TranscriptMapping.fromResume(JsonArray(emptyList()), id).isEmpty())
    }
}
