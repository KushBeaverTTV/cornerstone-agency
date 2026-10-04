package com.cornerstone.agency.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class RoutingTest {
    @Test
    fun `confident and clear pick is accepted`() {
        assertEquals("Kaori", Routing.decide("Kaori", mapOf("Kaori" to 0.7, "Aurora" to 0.2, "Prima" to 0.1)))
    }

    @Test
    fun `low confidence falls back to the user`() {
        assertNull(Routing.decide("Kaori", mapOf("Kaori" to 0.29, "Aurora" to 0.1)))
    }

    @Test
    fun `a near tie falls back to the user`() {
        assertNull(Routing.decide("Kaori", mapOf("Kaori" to 0.45, "Aurora" to 0.42)))
    }

    @Test
    fun `edges of the thresholds are inclusive`() {
        assertEquals("A", Routing.decide("A", mapOf("A" to 0.30, "B" to 0.25)))
        assertEquals("A", Routing.decide("A", mapOf("A" to 0.50, "B" to 0.45)))
    }

    @Test
    fun `a single candidate only needs confidence`() {
        assertEquals("A", Routing.decide("A", mapOf("A" to 0.31)))
    }

    @Test
    fun `blank or unknown picks are rejected`() {
        assertNull(Routing.decide("", mapOf("A" to 0.9)))
        assertNull(Routing.decide("Z", mapOf("A" to 0.9)))
    }
}
