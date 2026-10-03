package com.cornerstone.agency.voice

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Voice-session controller for the Agency voice plate (bottom sheet version of
 * HermesVoiceSession — no VoiceInteractionSession, service, or wake lock; it
 * lives and dies with the sheet).
 *
 * States: CONNECTING -> LISTENING -> THINKING -> SPEAKING -> (back to LISTENING).
 * The bridge embeds the STT transcript in a status frame
 * (`"<transcript>"\nThinking…`) — [onTranscript] fires with it so the caller
 * can post it into the open chat. TTS audio chunks stream to [VoicePlayer].
 */
class VoiceSession(
    context: Context,
    host: String,
    port: Int,
    private val token: String,
    private val onTranscript: (String) -> Unit = {},
) {

    enum class State { CONNECTING, RECONNECTING, LISTENING, THINKING, SPEAKING, ERROR }

    enum class Speaker { USER, AGENT }

    data class HistoryTurn(val id: Long, val speaker: Speaker, val text: String)

    private val client = VoiceClient(host, port, token)
    private val recorder = VoiceRecorder(context.applicationContext)
    private val player = VoicePlayer(context.applicationContext)
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private var linkWasOpen = false
    private var tearingDown = false
    private var turnSeq = 0L
    private var transcriptThisTurn = false
    private var audioThisTurn = false
    private var transcriptWatchdog: kotlinx.coroutines.Job? = null

    val uiState = MutableStateFlow(State.CONNECTING)
    val uiText = MutableStateFlow("Connecting…")
    val detailLine = MutableStateFlow("")
    val micLevel = MutableStateFlow(0f)
    val muted = MutableStateFlow(false)

    /** User mute: mic stays closed until unmuted, regardless of bridge record hints. */
    fun setMuted(m: Boolean) {
        muted.value = m
        if (m) recorder.pause() else recorder.resume()
    }
    val history = MutableStateFlow<List<HistoryTurn>>(emptyList())

    fun start() {
        if (token.isBlank()) {
            setState(State.ERROR, "Voice bridge token not set (More → Connection)")
            return
        }
        // mic first — if it fails the player never starts leaking focus/track
        if (!recorder.start()) {
            setState(State.ERROR, "Microphone unavailable")
            return
        }
        player.start()
        client.connect()

        scope.launch {
            recorder.audioFlow.collect { pcm ->
                if (client.link.value == VoiceClient.Link.OPEN && client.authed.value) {
                    client.sendAudio(pcm)
                }
            }
        }
        scope.launch { recorder.level.collect { micLevel.value = it } }
        scope.launch { client.incoming.collect { onBridgeMessage(it) } }
        scope.launch {
            client.control.collect { ctl ->
                if (ctl.interrupt) {
                    // barge-in: flush queued speech, reopen mic
                    player.flush()
                    if (!muted.value) recorder.resume()
                    setState(State.LISTENING, "Listening…")
                }
                ctl.record?.let { if (it && !muted.value) recorder.resume() else recorder.pause() }
            }
        }
        scope.launch { client.link.collect { onLinkChange(it) } }
        scope.launch {
            client.authed.collect { authed ->
                if (authed &&
                    (uiState.value == State.CONNECTING || uiState.value == State.RECONNECTING)
                ) {
                    setState(State.LISTENING, "Listening…")
                }
            }
        }
    }

    fun stop() {
        tearingDown = true
        recorder.stop()
        client.sendEndOfTurn()
        client.disconnect()
        player.stop()
        scope.cancel()
    }

    private fun setState(state: State, text: String? = null, detail: String? = null) {
        uiState.value = state
        if (text != null) uiText.value = text
        if (detail != null) detailLine.value = detail
    }

    private fun onLinkChange(link: VoiceClient.Link) {
        if (tearingDown) return
        when (link) {
            VoiceClient.Link.OPEN -> linkWasOpen = true
            VoiceClient.Link.CONNECTING ->
                if (linkWasOpen) setState(State.RECONNECTING, "Reconnecting…")
            VoiceClient.Link.CLOSED ->
                if (linkWasOpen) {
                    setState(State.RECONNECTING, "Reconnecting…")
                } else {
                    setState(State.ERROR, client.lastError.value ?: "Connection failed")
                }
            VoiceClient.Link.FAILED -> setState(
                State.ERROR,
                client.lastError.value?.ifBlank { null } ?: "Connection failed",
            )
            VoiceClient.Link.IDLE -> {}
        }
    }

    private fun onBridgeMessage(msg: VoiceClient.Incoming) {
        when (msg) {
            is VoiceClient.Incoming.Status -> onBridgeStatus(msg.text)
            is VoiceClient.Incoming.Audio -> {
                audioThisTurn = true
                setState(State.SPEAKING, "Speaking…")
                player.play(msg.bytes)
            }
            is VoiceClient.Incoming.Error ->
                setState(State.ERROR, msg.message.ifBlank { "Bridge error" })
        }
    }

    /** Map the bridge's status strings onto plate states, preserving the text. */
    private fun onBridgeStatus(text: String) {
        val t = text.trim()
        when {
            t.equals("connected", ignoreCase = true) ->
                setState(State.LISTENING, "Listening…")
            t.startsWith("Listening", ignoreCase = true) -> {
                transcriptThisTurn = false
                audioThisTurn = false
                setState(State.LISTENING, t)
            }
            t.startsWith("Transcribing", ignoreCase = true) ->
                setState(State.THINKING, t)
            t.contains("Thinking", ignoreCase = true) -> {
                // bridge sends '"transcript"\nThinking…' — surface the transcript
                val transcript = t.lineSequence()
                    .firstOrNull { it.trim().startsWith("\u201C") || it.trim().startsWith("\"") }
                transcript?.let { raw ->
                    val clean = raw.trim().trim('"', '\u201C', '\u201D').trim()
                    if (clean.isNotEmpty()) {
                        transcriptThisTurn = true
                        transcriptWatchdog?.cancel()
                        addTurn(Speaker.USER, clean)
                        onTranscript(clean)
                    }
                }
                // Some turns return audio with no STT transcript frame — flag it
                // after a grace period instead of leaving a blank detail line.
                if (!transcriptThisTurn) {
                    transcriptWatchdog?.cancel()
                    transcriptWatchdog = scope.launch {
                        kotlinx.coroutines.delay(10_000)
                        if (!transcriptThisTurn && audioThisTurn) {
                            detailLine.value = "(no transcript)"
                        }
                    }
                }
                setState(State.THINKING, "Thinking…", detail = transcript?.trim())
            }
            else -> {
                addTurn(Speaker.AGENT, t)
                setState(State.SPEAKING, t.ifBlank { "Speaking…" }, detail = t)
            }
        }
    }

    private fun addTurn(speaker: Speaker, text: String) {
        val clean = text.trim().trim('"').trim()
        if (clean.isEmpty()) return
        val last = history.value.lastOrNull()
        if (last != null && last.speaker == speaker && last.text == clean) return
        history.value = (history.value + HistoryTurn(++turnSeq, speaker, clean))
            .takeLast(MAX_HISTORY_TURNS)
    }

    companion object {
        private const val TAG = "VoiceSession"
        private const val MAX_HISTORY_TURNS = 50
    }
}
