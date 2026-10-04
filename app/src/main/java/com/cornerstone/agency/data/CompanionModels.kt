package com.cornerstone.agency.data

import kotlinx.serialization.Serializable

const val DEFAULT_FEED_INSTRUCTIONS =
    "Write me a feed about my interests: AI models and research, coding assistants, " +
        "developer tools, AI agents, PC gaming, and tech news. Keep the tone clear and direct. " +
        "Make it quick to skim. No clickbait."

/** One editorial post in the Feed tab. Written by the default bot via [FeedWriter]. */
@Serializable
data class FeedPost(
    val id: String = "",
    val title: String = "",
    val body: String = "",
    val link: String = "",
    /** Short section label ("AI models", "Dev tools"). */
    val topic: String = "",
    /** Epoch millis. */
    val publishedAt: Long = 0L,
    val read: Boolean = false,
)

/** One tracked goal in the Goals tab. */
@Serializable
data class Goal(
    val id: String = "",
    val title: String = "",
    val detail: String = "",
    val category: String = "Personal",
    /** Epoch millis. */
    val createdAt: Long = 0L,
    val done: Boolean = false,
    /** Epoch millis. */
    val doneAt: Long = 0L,
)

/** One saved file in the Artifacts tab. The bytes live under companion/artifacts/. */
@Serializable
data class ArtifactEntry(
    val id: String = "",
    val name: String = "",
    val mimeType: String = "",
    val size: Long = 0L,
    /** Epoch millis. */
    val addedAt: Long = 0L,
)

/** William's current tracked goals, seeded on first run so the tab isn't empty. */
fun defaultGoals(): List<Goal> {
    val now = System.currentTimeMillis()
    fun goal(title: String, detail: String, category: String) = Goal(
        id = title.lowercase().replace(Regex("[^a-z0-9]+"), "-").trim('-'),
        title = title,
        detail = detail,
        category = category,
        createdAt = now,
    )
    return listOf(
        goal(
            "Decide on the air fryer",
            "Ninja AF100 4-Qt at \$79 (down from \$89) on Walmart — in stock, shipping only. " +
                "Buy now or wait for the Oct 1 reminder?",
            "Personal",
        ),
        goal(
            "Monthly grocery shop",
            "Protein-heavy ~\$200 list via DoorDash (Weis / Martin's / Save a Lot). " +
                "Rebuild the full list fresh when ready — never patch fragments.",
            "Health",
        ),
        goal(
            "Finish Cornerstone HQ code scan fixes",
            "Remaining high/medium fixes, regression tests, and full verification " +
                "in C:\\Cornerstone-Dev. No commit or deploy without the word.",
            "Work",
        ),
        goal(
            "Migrate AI memory snapshots into Muse",
            "Move the portable memory snapshots over into Muse.",
            "Personal",
        ),
        goal(
            "Discord server setup and verification",
            "Kush Beaver Productionz HQ — still pending: server rename, " +
                "@antsailab handle, AI channel scoping.",
            "Work",
        ),
        goal(
            "NBA 2K27 jump-shot meta watch",
            "Keep the takeover reenactment jumper info current — verify patch-currency, " +
                "no stale data, never bypass the \$8 paywall.",
            "Personal",
        ),
    )
}

/** When the daily issue is written. Off by default; the user turns it on from the Feed menu. */
@Serializable
data class FeedSchedule(
    val enabled: Boolean = false,
    val hour: Int = 7,
    val minute: Int = 0,
) {
    /** "7:00 AM" style label. */
    fun label(): String {
        val h12 = when (hour % 12) { 0 -> 12; else -> hour % 12 }
        val ampm = if (hour < 12) "AM" else "PM"
        return "%d:%02d %s".format(h12, minute, ampm)
    }

    /** Epoch millis of the next occurrence strictly after [nowMs] in the default zone. */
    fun nextRunAfter(nowMs: Long, zone: java.util.TimeZone = java.util.TimeZone.getDefault()): Long {
        val cal = java.util.Calendar.getInstance(zone).apply {
            timeInMillis = nowMs
            set(java.util.Calendar.HOUR_OF_DAY, hour)
            set(java.util.Calendar.MINUTE, minute)
            set(java.util.Calendar.SECOND, 0)
            set(java.util.Calendar.MILLISECOND, 0)
        }
        if (cal.timeInMillis <= nowMs) cal.add(java.util.Calendar.DAY_OF_YEAR, 1)
        return cal.timeInMillis
    }
}

/** Whole companion store, persisted as one JSON file. */
@Serializable
data class CompanionState(
    val feedInstructions: String = DEFAULT_FEED_INSTRUCTIONS,
    val posts: List<FeedPost> = emptyList(),
    val goals: List<Goal> = defaultGoals(),
    val artifacts: List<ArtifactEntry> = emptyList(),
    val feedSchedule: FeedSchedule = FeedSchedule(),
    /** Profile name the writer uses — the default bot as last resolved by the app. */
    val feedProfile: String = "default",
    /** Display name of that bot, for notifications written while the app is closed. */
    val feedBotTitle: String = "Kaori",
    /** Epoch millis of the last successful issue (scheduled or manual). */
    val lastFeedRunAt: Long = 0L,
)
