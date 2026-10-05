package com.cornerstone.agency.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class PairingTest {
    private val token = "abcdefghijklmnopqrstuvwxyz012345"

    @Test
    fun parsesTheBridgePairingLink() {
        val p = PairingLink.parse(
            "cornerstone-agency://pair?v=1&bridge=100.84.54.112:9121&token=$token" +
                "&voice=100.84.54.112:8766&voice_token=vt-123&name=KBP%20HQ",
        )!!
        assertEquals("100.84.54.112:9121", p.bridge)
        assertEquals(token, p.token)
        assertEquals("100.84.54.112:8766", p.voice)
        assertEquals("vt-123", p.voiceToken)
        assertEquals("KBP HQ", p.name)
    }

    @Test
    fun rejectsOtherSchemesBadHostsAndShortTokens() {
        assertNull(PairingLink.parse("https://pair?bridge=1.2.3.4:9121&token=$token"))
        assertNull(PairingLink.parse("cornerstone-agency://pair?bridge=evil.com/x:9121&token=$token"))
        assertNull(PairingLink.parse("cornerstone-agency://pair?bridge=1.2.3.4:9121&token=short"))
        assertNull(PairingLink.parse("not a link"))
    }

    @Test
    fun dropsAMalformedVoiceAddressButKeepsThePairing() {
        val p = PairingLink.parse("cornerstone-agency://pair?bridge=kbp-hq:9121&token=$token&voice=bad")!!
        assertEquals("", p.voice)
    }
}
