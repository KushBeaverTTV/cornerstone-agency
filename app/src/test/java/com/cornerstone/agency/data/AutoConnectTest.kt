package com.cornerstone.agency.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AutoConnectTest {
    private val tok = "agb_abcdefghijklmnopqrstuvwxyz0123"
    private val oldTok = "agb_OLDOLDOLDOLDOLDOLDOLDOLDOLD00"

    @Test
    fun bakedConfigParsesAndDropsBadHosts() {
        val pc = KnownPc.fromBaked("KUSH-PC", "127.0.0.1:9121, 100.64.0.9:9121, garbage, evil.com/x:9121", tok, "8766", "agv_1")!!
        assertEquals(listOf("127.0.0.1:9121", "100.64.0.9:9121"), pc.hosts)
        assertEquals("KUSH-PC", pc.name)
        assertEquals(8766, pc.voicePort)
        assertNull(KnownPc.fromBaked("", "", "", "", "")) // no pairing file at build time
        assertNull(KnownPc.fromBaked("PC", "127.0.0.1:9121", "short", "8766", ""))
    }

    @Test
    fun linkForHostCarriesVoiceOnTheSameAddress() {
        val pc = KnownPc(name = "PC", hosts = listOf("100.64.0.9:9121"), token = tok, voicePort = 8766, voiceToken = "agv_1")
        val link = pc.linkFor("100.64.0.9:9121")
        assertEquals("100.64.0.9:9121", link.bridge)
        assertEquals("100.64.0.9:8766", link.voice)
        assertEquals("agv_1", link.voiceToken)
        assertEquals(tok, link.token)
    }

    @Test
    fun pairingResponseBecomesAddressBookAndRoundTripsJson() {
        val pc = KnownPc.fromPairingResponse(
            PairingResponse(name = "PC", hosts = listOf("127.0.0.1", "100.64.0.9"), bridge_port = 9121, token = tok, voice_token = "agv_1"),
        )!!
        assertEquals(listOf("127.0.0.1:9121", "100.64.0.9:9121"), pc.hosts)
        assertEquals(pc, KnownPc.fromJson(KnownPc.toJson(pc)))
    }

    @Test
    fun learnedBookWinsOverBakedWhenTokensMatchAndIsDemotedWhenStale() {
        val baked = KnownPc(name = "PC", hosts = listOf("127.0.0.1:9121"), token = tok)
        val learned = KnownPc(name = "PC", hosts = listOf("127.0.0.1:9121", "100.64.0.9:9121"), token = tok)
        assertEquals(
            listOf("127.0.0.1:9121", "100.64.0.9:9121"),
            AutoConnect.candidates(learned, baked).map { it.first },
        )
        // A fresh build with a rotated token: the stale learned entries go last, not first.
        val stale = learned.copy(token = oldTok, hosts = listOf("100.64.0.9:9121"))
        val c = AutoConnect.candidates(stale, baked)
        assertEquals(listOf("127.0.0.1:9121", "100.64.0.9:9121"), c.map { it.first })
        assertEquals(tok, c[0].second.token)
        assertEquals(oldTok, c[1].second.token)
        assertTrue(AutoConnect.candidates(null, null).isEmpty())
    }

    @Test
    fun pickReachablePrefersListOrderNotSpeed() = runTest {
        // The tailnet host answers first, but the USB host (ranked higher) also answers: USB wins.
        val picked = AutoConnect.pickReachable(listOf("127.0.0.1:9121", "100.64.0.9:9121"), timeoutMs = 4_000) { h ->
            if (h.startsWith("127")) { delay(300); true } else { delay(10); true }
        }
        assertEquals("127.0.0.1:9121", picked)
    }

    @Test
    fun pickReachableSkipsDeadAndSlowHosts() = runTest {
        val picked = AutoConnect.pickReachable(listOf("127.0.0.1:9121", "100.64.0.9:9121", "10.0.0.5:9121"), timeoutMs = 1_000) { h ->
            when {
                h.startsWith("127") -> throw java.net.ConnectException("refused") // cable unplugged
                h.startsWith("100") -> { delay(5_000); true } // asleep tailnet peer: slower than the timeout
                else -> true
            }
        }
        assertEquals("10.0.0.5:9121", picked)
        assertNull(AutoConnect.pickReachable(listOf("a", "b"), timeoutMs = 100) { false })
    }

    @Test
    fun pathLabel() {
        assertEquals("USB", AutoConnect.pathLabel("127.0.0.1:9121"))
        assertEquals("Tailscale", AutoConnect.pathLabel("100.64.0.9:9121"))
    }
}
