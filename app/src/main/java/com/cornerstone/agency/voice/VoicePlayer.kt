package com.cornerstone.agency.voice

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/** Streams PCM16 reply chunks to an AudioTrack with assistant attributes. */
class VoicePlayer(context: Context) {

    private val audioManager =
        context.applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private val audioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_ASSISTANT)
        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
        .build()

    private val focusRequest = AudioFocusRequest.Builder(
        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK,
    )
        .setAudioAttributes(audioAttributes)
        .setOnAudioFocusChangeListener { }
        .build()

    private val queue = Channel<ByteArray>(Channel.BUFFERED)
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var job: Job? = null

    private var audioTrack: AudioTrack? = null

    private fun newTrack(): AudioTrack {
        val minBuf = AudioTrack.getMinBufferSize(SAMPLE_RATE, CHANNEL_OUT, FORMAT)
        return AudioTrack.Builder()
            .setAudioAttributes(audioAttributes)
            .setAudioFormat(
                AudioFormat.Builder()
                    .setSampleRate(SAMPLE_RATE)
                    .setEncoding(FORMAT)
                    .setChannelMask(CHANNEL_OUT)
                    .build(),
            )
            .setBufferSizeInBytes(minBuf * 2)
            .setTransferMode(AudioTrack.MODE_STREAM)
            .build()
    }

    fun start() {
        val granted = audioManager.requestAudioFocus(focusRequest)
        if (granted != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            Log.w(TAG, "audio focus denied ($granted); playing anyway")
        }
        if (job?.isActive == true) return
        val track = audioTrack ?: newTrack().also { audioTrack = it }
        track.play()
        job = scope.launch {
            for (chunk in queue) {
                if (!isActive) break
                track.write(chunk, 0, chunk.size)
            }
        }
    }

    fun play(chunk: ByteArray) {
        queue.trySend(chunk)
    }

    /** Drop queued-but-unwritten chunks (barge-in) without tearing down. */
    fun flush() {
        while (queue.tryReceive().isSuccess) { /* drain stale chunks */ }
        audioTrack?.let {
            runCatching {
                it.pause()
                it.flush()
                it.play()
            }
        }
    }

    fun stop() {
        job?.cancel()
        job = null
        while (queue.tryReceive().isSuccess) { /* drain stale chunks */ }
        audioTrack?.let {
            runCatching { it.stop() }
            it.release()
        }
        audioTrack = null
        audioManager.abandonAudioFocusRequest(focusRequest)
    }

    companion object {
        private const val TAG = "VoicePlayer"
        const val SAMPLE_RATE = 16000
        const val CHANNEL_OUT = AudioFormat.CHANNEL_OUT_MONO
        const val FORMAT = AudioFormat.ENCODING_PCM_16BIT
    }
}
