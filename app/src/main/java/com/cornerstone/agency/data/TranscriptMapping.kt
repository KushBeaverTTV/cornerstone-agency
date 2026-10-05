package com.cornerstone.agency.data

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.longOrNull

/**
 * Pure mapping between Hermes transcript shapes and [ChatItem]s.
 *
 * Two server shapes reach the phone:
 *  - `session.resume` messages (the gateway's display projection: role/text/name/context/row_id), and
 *  - raw state-DB rows from `GET /api/sessions/{id}/messages` (role/content/tool_name/id/…), used to
 *    catch up on rows another process wrote and to give live-streamed rows their durable ids.
 * Both map to the same items so a transcript can be rebuilt from either.
 */
object TranscriptMapping {

    private val IMAGE_REF = Regex("""^@image:(`[^`\n]+`|"[^"\n]+"|'[^'\n]+'|\S+)\s*$""")
    private val IMAGE_HINT = Regex("""^\[Image attached at: (.+)]\s*$""")
    private val USER_ATTACHED = Regex("""^\[User attached image: (.+)]\s*$""")
    private val MEDIA_LINE = Regex("""^MEDIA:(\S.*)$""")
    private val FILE_REF = Regex("""@file:(`[^`\n]+`|"[^"\n]+"|'[^'\n]+'|\S+)""")
    private val IMAGE_EXT = setOf("png", "jpg", "jpeg", "gif", "webp", "bmp", "heic")

    /** Display-only timeline rows that are not something anyone typed. */
    private val MARKER_KINDS = setOf("model_switch", "notice", "timeline", "delegation", "compaction", "interrupt")

    private fun unquote(v: String): String =
        if (v.length >= 2 && v.first() == v.last() && v.first() in "`\"'") v.substring(1, v.length - 1) else v

    private fun isImagePath(p: String) = p.substringAfterLast('.', "").lowercase() in IMAGE_EXT

    /**
     * Pull attachment references out of message text. Returns the text a person should read and
     * the attachments it referenced (`@image:` refs, `[Image attached at: …]` hints, `MEDIA:` lines
     * for images; `@file:` refs stay inline but are also listed).
     */
    fun splitAttachments(text: String): Pair<String, List<ChatAttachment>> {
        if (text.isEmpty()) return text to emptyList()
        val out = ArrayList<ChatAttachment>()
        val kept = ArrayList<String>()
        for (line in text.lines()) {
            val t = line.trim()
            val img = IMAGE_REF.matchEntire(t)?.groupValues?.get(1)?.let(::unquote)
                ?: IMAGE_HINT.matchEntire(t)?.groupValues?.get(1)
                ?: MEDIA_LINE.matchEntire(t)?.groupValues?.get(1)?.trim()?.takeIf(::isImagePath)
            if (img != null) {
                out += ChatAttachment(name = img.substringAfterLast('/').substringAfterLast('\\'), kind = "image", remotePath = img)
                continue
            }
            if (USER_ATTACHED.matches(t)) continue // queued-image echo; the image itself is listed
            kept += line
        }
        FILE_REF.findAll(text).forEach { m ->
            val p = unquote(m.groupValues[1])
            out += ChatAttachment(name = p.substringAfterLast('/').substringAfterLast('\\'), kind = "file", remotePath = p)
        }
        // The file chips stand in for the refs; the prose keeps the rest of the line.
        val prose = FILE_REF.replace(kept.joinToString("\n"), "").lines().joinToString("\n") { it.trimEnd() }
        return prose.trim() to out
    }

    fun reactionsOf(meta: JsonElement?): List<Reaction> {
        val list = (meta as? JsonObject)?.get("reactions") as? JsonArray ?: return emptyList()
        return list.mapNotNull { el ->
            val o = el as? JsonObject ?: return@mapNotNull null
            val emoji = o.str("emoji") ?: return@mapNotNull null
            Reaction(emoji, o.str("author") ?: "user")
        }
    }

    /** `session.resume` / `session.branch` display projection → items. */
    fun fromResume(msgs: JsonArray?, newId: () -> String): List<ChatItem> {
        val list = ArrayList<ChatItem>()
        msgs?.forEach { el ->
            val m = el as? JsonObject ?: return@forEach
            val kind = m.str("display_kind")
            if (kind == "hidden") return@forEach
            val rowId = m.long("row_id")
            val ts = m.double("timestamp")
            val reactions = reactionsOf(m["display_metadata"])
            when (m.str("role")) {
                "user" -> {
                    val raw = m.str("text").orEmpty()
                    if (kind != null && kind in MARKER_KINDS) {
                        if (raw.isNotBlank()) list += ChatItem(newId(), ChatKind.Status, raw, rowId = rowId, timestamp = ts)
                        return@forEach
                    }
                    val (text, atts) = splitAttachments(raw)
                    list += ChatItem(newId(), ChatKind.User, text, rowId = rowId, attachments = atts,
                        reactions = reactions, timestamp = ts)
                }
                "assistant" -> {
                    val raw = m.str("text").orEmpty()
                    // reasoning can carry the final ANSWER verbatim — never a duplicate thinking row.
                    m.str("reasoning")?.takeIf { it.isNotBlank() && it.trim() != raw.trim() }?.let {
                        list += ChatItem(newId(), ChatKind.Thinking, it, timestamp = ts)
                    }
                    if (raw.isNotBlank()) {
                        val (text, atts) = splitAttachments(raw)
                        list += ChatItem(newId(), ChatKind.Assistant, text, rowId = rowId, attachments = atts,
                            reactions = reactions, timestamp = ts)
                    }
                }
                "tool" -> list += ChatItem(
                    newId(), ChatKind.Tool, "✓ ${m.str("name") ?: "tool"}",
                    meta = m.str("context").orEmpty(), rowId = rowId, timestamp = ts,
                )
            }
        }
        return list
    }

    private fun contentText(el: JsonElement?): String = when (el) {
        null -> ""
        is JsonArray -> el.mapNotNull { (it as? JsonObject)?.let { p -> p.str("text") } }.joinToString("\n")
        is JsonPrimitive -> el.contentOrNull.orEmpty()
        else -> ""
    }

    /** Raw state-DB rows (REST) → items, matching [fromResume]'s rendering. */
    fun fromDbRows(rows: List<JsonObject>, newId: () -> String): List<ChatItem> {
        val list = ArrayList<ChatItem>()
        for (m in rows) {
            val kind = m.str("display_kind")
            if (kind == "hidden") continue
            val raw = contentText(m["display_content"]).ifBlank { contentText(m["content"]) }
            val rowId = m.long("id")
            val ts = m.double("timestamp")
            val reactions = reactionsOf(parseMeta(m["display_metadata"]))
            when (m.str("role")) {
                "user" -> {
                    if (raw.isBlank() || raw.startsWith("[System:")) continue
                    if (kind != null && kind in MARKER_KINDS) {
                        list += ChatItem(newId(), ChatKind.Status, raw, rowId = rowId, timestamp = ts)
                        continue
                    }
                    val (text, atts) = splitAttachments(raw)
                    list += ChatItem(newId(), ChatKind.User, text, rowId = rowId, attachments = atts,
                        reactions = reactions, timestamp = ts)
                }
                "assistant" -> {
                    m.str("reasoning")?.takeIf { it.isNotBlank() && it.trim() != raw.trim() }?.let {
                        list += ChatItem(newId(), ChatKind.Thinking, it, timestamp = ts)
                    }
                    if (raw.isNotBlank()) {
                        val (text, atts) = splitAttachments(raw)
                        list += ChatItem(newId(), ChatKind.Assistant, text, rowId = rowId, attachments = atts,
                            reactions = reactions, timestamp = ts)
                    }
                }
                "tool" -> list += ChatItem(newId(), ChatKind.Tool, "✓ ${m.str("tool_name") ?: "tool"}",
                    rowId = rowId, timestamp = ts)
            }
        }
        return list
    }

    /** display_metadata arrives as an object (resume) or a JSON string (raw DB column). */
    private fun parseMeta(el: JsonElement?): JsonElement? = when (el) {
        is JsonPrimitive -> el.contentOrNull?.let {
            runCatching { kotlinx.serialization.json.Json.parseToJsonElement(it) }.getOrNull()
        }
        else -> el
    }

    /**
     * Replace a live-streamed tail ([overlay]) with the same turn as persisted ([persisted]):
     * the persisted rows win (they carry row ids and are what every other client sees), but ids of
     * matching overlay rows are reused so the list does not re-animate, and local attachment
     * previews survive. Request/Error rows (never persisted) are kept.
     */
    fun reconcile(overlay: List<ChatItem>, persisted: List<ChatItem>): List<ChatItem> {
        if (persisted.isEmpty()) return overlay
        val pool = overlay.filter { it.kind != ChatKind.Request && it.kind != ChatKind.Error }.toMutableList()
        val out = persisted.map { p ->
            val idx = pool.indexOfFirst { o ->
                o.kind == p.kind && (o.kind == ChatKind.Tool || o.kind == ChatKind.Thinking || o.text.trim() == p.text.trim())
            }
            if (idx < 0) p else {
                val o = pool.removeAt(idx)
                val atts = if (o.attachments.any { it.localUri.isNotEmpty() }) o.attachments else p.attachments
                p.copy(id = o.id, attachments = atts)
            }
        }
        return out + overlay.filter { it.kind == ChatKind.Request || it.kind == ChatKind.Error }
    }

    /** `session.branch {count}` copies the first N visible user/assistant rows. */
    fun branchCount(items: List<ChatItem>, throughId: String): Int {
        var n = 0
        for (it in items) {
            if ((it.kind == ChatKind.User || it.kind == ChatKind.Assistant) &&
                (it.text.isNotBlank() || it.attachments.isNotEmpty())
            ) n++
            if (it.id == throughId) return n
        }
        return n
    }

    /** The user turn an assistant reply answers (for Regenerate). */
    fun promptFor(items: List<ChatItem>, assistantId: String): ChatItem? {
        val idx = items.indexOfFirst { it.id == assistantId }
        if (idx < 0) return null
        for (i in idx - 1 downTo 0) if (items[i].kind == ChatKind.User) return items[i]
        return null
    }

    fun maxRowId(items: List<ChatItem>): Long? = items.mapNotNull { it.rowId }.maxOrNull()

    private fun JsonObject.str(key: String): String? = (this[key] as? JsonPrimitive)?.contentOrNull
    private fun JsonObject.long(key: String): Long? =
        (this[key] as? JsonPrimitive)?.let { it.longOrNull ?: it.contentOrNull?.toLongOrNull() }
    private fun JsonObject.double(key: String): Double =
        (this[key] as? JsonPrimitive)?.let { it.doubleOrNull ?: it.contentOrNull?.toDoubleOrNull() } ?: 0.0
}
