package com.cornerstone.agency.data

import org.junit.Assert.assertEquals
import org.junit.Test

class ActivityScoresTest {
    private fun s(id: String, started: Double) = SessionSummary(id = id, startedAt = started)

    @Test
    fun olderStartedButMoreRecentlyActiveRowKeepsServerOrder() {
        // Server order = last activity: "old" (started long ago) was active after "new" started.
        val scored = listOf(s("old", 100.0), s("new", 500.0), s("older", 50.0)).activityScores()
        assertEquals(listOf("old", "new", "older"), scored.map { it.first.id })
        // A row is at least as recent as anything listed after it.
        assertEquals(listOf(500.0, 500.0, 50.0), scored.map { it.second })
    }

    @Test
    fun emptyListIsEmpty() {
        assertEquals(0, emptyList<SessionSummary>().activityScores().size)
    }
}
