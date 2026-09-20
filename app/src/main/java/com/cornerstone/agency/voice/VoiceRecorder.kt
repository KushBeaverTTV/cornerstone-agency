package com.cornerstone.agency.voice

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/** Records 16 kHz mono PCM16 and emits every read, plus a normalized mic level. */
class VoiceRecorder(private val context: Context) {

    private val _audioFlow = MutableSharedFlow<ByteArray>(extraBufferCapacity = 32)
    val audioFlow: SharedFlow<ByteArray> = _audioFlow

    private val _level = MutableStateFlow(0f)
    val level: StateFlow<Float> = _level

    private val bufferSize: Int =
        maxOf(AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT), 3200)

    private var audioRecord: AudioRecord? = null
    private var job: Job? = null
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    var activeSource: Int = -1
        private set

    @Volatile private var paused = false

    fun pause() {
        paused = true
        _level.value = 0f
    }

    fun resume() {
        paused = false
    }

    @SuppressLint("MissingPermission")
    fun start(): Boolean {
        if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            Log.w(TAG, "RECORD_AUDIO not granted")
            return false
        }
        val record = openRecord() ?: return false
        paused = false
        audioRecord = record
        record.startRecording()
        job = scope.launch {
            val buf = ByteArray(bufferSize)
            while (isActive) {
                // keep reading while paused so the hardware buffer never fills
                val read = record.read(buf, 0, buf.size)
                if (read > 0 && !paused) {
                    val boosted = applyGain(buf.copyOf(read), MIC_GAIN)
                    _audioFlow.emit(boosted)
                    _level.value = rmsLevel(boosted)
                }
            }
        }
        return true
    }

    /** VOICE_RECOGNITION isn't available on every device — fall back gracefully. */
    @SuppressLint("MissingPermission")
    private fun openRecord(): AudioRecord? {
        for (source in AUDIO_SOURCES) {
            val candidate = runCatching {
                AudioRecord(source, SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT, bufferSize)
            }.getOrNull()
            if (candidate != null && candidate.state == AudioRecord.STATE_INITIALIZED) {
                activeSource = source
                return candidate
            }
            candidate?.release()
        }
        Log.w(TAG, "no usable audio source")
        return null
    }

    fun stop() {
        job?.cancel()
        job = null
        paused = false
        audioRecord?.let {
            runCatching { it.stop() }
            it.release()
        }
        audioRecord = null
        activeSource = -1
        _level.value = 0f
    }

    private fun applyGain(buf: ByteArray, gain: Float): ByteArray {
        var i = 0
        while (i + 1 < buf.size) {
            val s = (buf[i].toInt() and 0xFF) or (buf[i + 1].toInt() shl 8)
            val v = (s.toShort().toInt() * gain).toInt()
                .coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt())
            buf[i] = (v and 0xFF).toByte()
            buf[i + 1] = ((v shr 8) and 0xFF).toByte()
            i += 2
        }
        return buf
    }

    private fun rmsLevel(buf: ByteArray): Float {
        var sum = 0.0
        var i = 0
        while (i + 1 < buf.size) {
            val s = (buf[i].toInt() and 0xFF) or (buf[i + 1].toInt() shl 8)
            sum += s.toShort() * s.toShort().toDouble()
            i += 2
        }
        val n = buf.size / 2
        if (n == 0) return 0f
        val rms = kotlin.math.sqrt(sum / n)
        return (rms / 6000.0).coerceIn(0.0, 1.0).toFloat()
    }

    companion object {
        private const val TAG = "VoiceRecorder"
        private const val MIC_GAIN = 4.0f
        private val AUDIO_SOURCES = intArrayOf(
            MediaRecorder.AudioSource.VOICE_RECOGNITION,
            MediaRecorder.AudioSource.MIC,
            MediaRecorder.AudioSource.CAMCORDER,
        )
        const val SAMPLE_RATE = 16000
        const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
        const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
    }
}
