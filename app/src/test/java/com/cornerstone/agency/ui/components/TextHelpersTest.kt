package com.cornerstone.agency.ui.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TextHelpersTest {
    @Test
    fun `cleanPreview replaces system boilerplate and takes the first non-blank line`() {
        assertEquals("Scheduled job", cleanPreview("[IMPORTANT: You are running as a scheduled cron job]\nmore"))
        assertEquals("Voice conversation", cleanPreview("You are being used through a hands-free voice channel."))
        assertEquals("Hello there", cleanPreview("\n\n  Hello there  \nsecond line"))
        assertEquals("", cleanPreview("   \n  "))
    }

    @Test
    fun `looksMachineTitle catches underscore slugs but not prose`() {
        assertTrue(looksMachineTitle("UI_test:_in_your_terminal_run_exactly"))
        assertFalse(looksMachineTitle("Shout out to all"))
        assertFalse(looksMachineTitle("short_slug_x")) // too short to matter
        assertFalse(looksMachineTitle("long-hyphenated-slug-with-two_under_scores-only"))
    }

    @Test
    fun `sessionLine prefers a human title, then preview, then de-slugs`() {
        assertEquals("Take out requested items", sessionLine("Take out requested items", "whatever"))
        assertEquals("mkdir -p test", sessionLine("UI_test:_in_your_terminal_run_exactly", "mkdir -p test"))
        assertEquals("UI test: in your terminal run exactly", sessionLine("UI_test:_in_your_terminal_run_exactly", ""))
        assertEquals("Scheduled job", sessionLine("", "[IMPORTANT: You are running as a scheduled cron job]"))
        assertEquals("", sessionLine("  ", "\n"))
    }

    @Test
    fun `relativeTime buckets by age`() {
        val now = 1_699_963_200.0 // 2023-11-14T12:00Z, so "now - 7d" is noon UTC and safe in any zone
        assertEquals("", relativeTime(0.0, now))
        assertEquals("just now", relativeTime(now - 30, now))
        assertEquals("4m", relativeTime(now - 4 * 60, now))
        assertEquals("2h", relativeTime(now - 2 * 3600, now))
        assertEquals("3d", relativeTime(now - 3 * 86400, now))
        assertEquals("Nov 7", relativeTime(now - 7 * 86400, now))
    }
}
