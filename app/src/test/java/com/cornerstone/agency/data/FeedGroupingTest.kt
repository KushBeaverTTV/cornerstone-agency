package com.cornerstone.agency.data

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar
import java.util.TimeZone

class FeedGroupingTest {
    private val utc: TimeZone = TimeZone.getTimeZone("UTC")

    private fun at(year: Int, month: Int, day: Int, hour: Int = 12): Long =
        Calendar.getInstance(utc).apply { clear(); set(year, month - 1, day, hour, 0, 0) }.timeInMillis

    private fun post(title: String, ms: Long) = FeedPost(id = title, title = title, publishedAt = ms)

    @Test
    fun `buckets into today yesterday and dated groups newest first`() {
        val now = at(2026, 10, 3, 23)
        val posts = listOf(
            post("old", at(2026, 9, 28)),
            post("y1", at(2026, 10, 2, 8)),
            post("t1", at(2026, 10, 3, 7)),
            post("t2", at(2026, 10, 3, 9)),
        )
        val groups = groupPostsByDay(posts, now, utc)
        assertEquals(listOf("Today", "Yesterday", "Mon, Sep 28"), groups.map { it.first })
        assertEquals(listOf("t2", "t1"), groups[0].second.map { it.title })
        assertEquals(listOf("y1"), groups[1].second.map { it.title })
    }

    @Test
    fun `yesterday survives the year boundary`() {
        val now = at(2027, 1, 1, 9)
        val groups = groupPostsByDay(listOf(post("nye", at(2026, 12, 31, 20))), now, utc)
        assertEquals("Yesterday", groups.single().first)
    }

    @Test
    fun `a post one minute before local midnight is still yesterday not two days ago`() {
        val now = at(2026, 10, 3, 0) + 60_000 // 00:01 today
        val groups = groupPostsByDay(listOf(post("late", at(2026, 10, 3, 0) - 60_000)), now, utc)
        assertEquals("Yesterday", groups.single().first)
    }

    @Test
    fun `empty input gives no groups`() {
        assertEquals(emptyList<Pair<String, List<FeedPost>>>(), groupPostsByDay(emptyList(), at(2026, 10, 3), utc))
    }
}
