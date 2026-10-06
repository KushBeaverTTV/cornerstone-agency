package com.cornerstone.agency.data

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AppReleaseTest {
    @Test
    fun comparesVersionsNumerically() {
        assertTrue(AppRelease("1.3.1").newerThan("1.3.0"))
        assertTrue(AppRelease("1.10.0").newerThan("1.9.9"))
        assertFalse(AppRelease("1.3.0").newerThan("1.3.0"))
        assertFalse(AppRelease("1.2.9").newerThan("1.3.0"))
        assertTrue(AppRelease("2.0").newerThan("1.9.9"))
    }
}
