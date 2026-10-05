package com.cornerstone.agency.ui.components

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Plain Kotlin, no Compose: everything here is unit-tested in app/src/test.

/** System-prompt boilerplate makes useless previews; show something human instead. */
fun cleanPreview(raw: String): String {
    val first = raw.lineSequence().firstOrNull { it.isNotBlank() }?.trim() ?: return ""
    return when {
        first.startsWith("[IMPORTANT: You are running as a scheduled cron", true) -> "Scheduled job"
        first.startsWith("You are being used through a hands-free voice", true) -> "Voice conversation"
        else -> first
    }
}

/** True for machine-shaped titles like "UI_test:_in_your_terminal_run_exactly" — unreadable as a headline. */
fun looksMachineTitle(title: String): Boolean =
    title.length > 24 && !title.contains(' ') && title.count { it == '_' } >= 3

/** Human line for a session: the title unless it's machine-shaped, else the cleaned preview. */
fun sessionLine(title: String, preview: String): String {
    val t = title.trim()
    if (t.isNotBlank() && !looksMachineTitle(t)) return t
    val pv = cleanPreview(preview)
    if (pv.isNotBlank() && !looksMachineTitle(pv)) return pv
    return t.ifBlank { pv }.replace('_', ' ')
}

/** "just now" / "4m" / "2h" / "3d" / absolute date for old timestamps. */
fun relativeTime(epochSeconds: Double, nowSeconds: Double = System.currentTimeMillis() / 1000.0): String {
    if (epochSeconds <= 0.0) return ""
    val diff = (nowSeconds - epochSeconds).toLong()
    return when {
        diff < 60 -> "just now"
        diff < 3600 -> "${diff / 60}m"
        diff < 86400 -> "${diff / 3600}h"
        diff < 86400 * 7 -> "${diff / 86400}d"
        else -> SimpleDateFormat("MMM d", Locale.US).format(Date((epochSeconds * 1000).toLong()))
    }
}

/**
 * Chat replies use single newlines as line breaks (lists of numbers, addresses, poems), but
 * Markdown folds them into spaces. Turn them into hard breaks outside code fences and tables;
 * blank lines, headings and list items keep their normal Markdown meaning.
 */
fun keepLineBreaks(md: String): String {
    if ('\n' !in md) return md
    val lines = md.split('\n')
    val out = StringBuilder(md.length + 32)
    var fence = false
    for ((i, line) in lines.withIndex()) {
        val trimmed = line.trimStart()
        if (trimmed.startsWith("```") || trimmed.startsWith("~~~")) fence = !fence
        out.append(line)
        if (i < lines.lastIndex) {
            val next = lines[i + 1]
            val joinable = !fence && line.isNotBlank() && next.isNotBlank() &&
                !trimmed.startsWith("|") && !next.trimStart().startsWith("|") &&
                !trimmed.startsWith("```") && !line.endsWith("  ") && !line.endsWith("\\")
            if (joinable) out.append("  ")
            out.append('\n')
        }
    }
    return out.toString()
}
