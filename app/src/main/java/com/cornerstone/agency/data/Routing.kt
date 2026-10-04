package com.cornerstone.agency.data

/**
 * Laya's verdict on who should take a message. The router is only trusted when it is both
 * reasonably sure ([top] ≥ 0.30) and clearly prefers one target over the runner-up (gap ≥ 0.05);
 * otherwise the user picks.
 */
object Routing {
    const val MIN_CONFIDENCE = 0.30
    const val MIN_MARGIN = 0.05

    /** The chosen target name when the router is confident enough, else null. */
    fun decide(pick: String, probs: Map<String, Double>): String? {
        if (pick.isBlank()) return null
        val top = probs[pick] ?: 0.0
        val second = probs.filterKeys { it != pick }.values.maxOrNull() ?: 0.0
        // Epsilon: 0.50 - 0.45 is 0.04999… in binary floating point.
        return if (top >= MIN_CONFIDENCE - EPS && top - second >= MIN_MARGIN - EPS) pick else null
    }

    private const val EPS = 1e-9
}
