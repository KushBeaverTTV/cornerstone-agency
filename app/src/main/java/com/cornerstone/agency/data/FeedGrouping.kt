package com.cornerstone.agency.data

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Posts newest-first, bucketed by local calendar day: "Today", "Yesterday", then "Sat, Oct 3".
 * Works across the year boundary (Dec 31 → Jan 1 is still "Yesterday").
 */
fun groupPostsByDay(
    posts: List<FeedPost>,
    now: Long = System.currentTimeMillis(),
    zone: TimeZone = TimeZone.getDefault(),
): List<Pair<String, List<FeedPost>>> {
    val fmt = SimpleDateFormat("EEE, MMM d", Locale.US).apply { timeZone = zone }
    val todayDay = localDayNumber(now, zone)
    fun dayKey(ms: Long): String = when (todayDay - localDayNumber(ms, zone)) {
        0L -> "Today"
        1L -> "Yesterday"
        else -> fmt.format(Date(ms))
    }
    return posts.sortedByDescending { it.publishedAt }
        .groupBy { dayKey(it.publishedAt) }
        .map { it.key to it.value }
}

/** Whole days since the epoch for the local midnight containing [ms]. */
private fun localDayNumber(ms: Long, zone: TimeZone): Long {
    val c = Calendar.getInstance(zone).apply {
        timeInMillis = ms
        set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
    }
    return Math.floorDiv(c.timeInMillis + zone.getOffset(c.timeInMillis), 86_400_000L)
}
