package com.cornerstone.agency.data

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar
import java.util.TimeZone

class FeedScheduleTest {
    private val utc: TimeZone = TimeZone.getTimeZone("UTC")

    private fun at(year: Int, month: Int, day: Int, hour: Int, minute: Int): Long =
        Calendar.getInstance(utc).apply {
            clear(); set(year, month - 1, day, hour, minute, 0)
        }.timeInMillis

    @Test
    fun `labels are 12-hour with am pm`() {
        assertEquals("7:00 AM", FeedSchedule(hour = 7, minute = 0).label())
        assertEquals("12:05 AM", FeedSchedule(hour = 0, minute = 5).label())
        assertEquals("12:30 PM", FeedSchedule(hour = 12, minute = 30).label())
        assertEquals("11:59 PM", FeedSchedule(hour = 23, minute = 59).label())
    }

    @Test
    fun `next run is later today when the time has not passed`() {
        val s = FeedSchedule(enabled = true, hour = 7, minute = 0)
        val now = at(2026, 10, 3, 6, 59)
        assertEquals(at(2026, 10, 3, 7, 0), s.nextRunAfter(now, utc))
    }

    @Test
    fun `next run rolls to tomorrow once the time has passed or is exactly now`() {
        val s = FeedSchedule(enabled = true, hour = 7, minute = 0)
        assertEquals(at(2026, 10, 4, 7, 0), s.nextRunAfter(at(2026, 10, 3, 7, 0), utc))
        assertEquals(at(2026, 10, 4, 7, 0), s.nextRunAfter(at(2026, 10, 3, 23, 8), utc))
    }

    @Test
    fun `rolls across month and year boundaries`() {
        val s = FeedSchedule(enabled = true, hour = 7, minute = 0)
        assertEquals(at(2027, 1, 1, 7, 0), s.nextRunAfter(at(2026, 12, 31, 8, 0), utc))
    }
}
