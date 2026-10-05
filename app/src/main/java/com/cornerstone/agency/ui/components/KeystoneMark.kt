package com.cornerstone.agency.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/** What the agent is doing; drives the keystone's motion. */
enum class KeystoneState { IDLE, LISTENING, WORKING, SPEAKING, WAITING, ERROR }

/**
 * The Cornerstone keystone as a living mark: breathes when idle, glows with your voice while
 * listening, sweeps light while working, pulses when speaking, tilts when it needs you.
 */
@Composable
fun KeystoneMark(
    state: KeystoneState,
    modifier: Modifier = Modifier,
    size: Dp = 96.dp,
    color: Color = Color(0xFFE6B800),
    level: Float = 0f,
) {
    val t = rememberInfiniteTransition(label = "keystone")
    val breatheS = t.animateFloat(
        0f, 1f, infiniteRepeatable(tween(3200, easing = FastOutSlowInEasing), RepeatMode.Reverse), label = "breathe",
    )
    val spinS = t.animateFloat(
        0f, 360f, infiniteRepeatable(tween(2600, easing = LinearEasing), RepeatMode.Restart), label = "spin",
    )
    val pulseS = t.animateFloat(
        0f, 1f, infiniteRepeatable(tween(520), RepeatMode.Reverse), label = "pulse",
    )
    val wobbleS = t.animateFloat(
        -1f, 1f, infiniteRepeatable(tween(900, easing = FastOutSlowInEasing), RepeatMode.Reverse), label = "wobble",
    )

    // Reduce motion: honour the system animator scale (0 = animations off).
    val ctx = androidx.compose.ui.platform.LocalContext.current
    val still = remember {
        android.provider.Settings.Global.getFloat(ctx.contentResolver, android.provider.Settings.Global.ANIMATOR_DURATION_SCALE, 1f) == 0f
    }
    // Geometry (5 stone paths, the crown, the halo brush) is built once per size in
    // drawWithCache; each frame only reads the animated values and issues draw calls, so a
    // screen full of breathing marks allocates nothing per frame.
    androidx.compose.foundation.layout.Spacer(
        modifier.size(size).drawWithCache {
            val w = this.size.width
            val h = this.size.height
            val cx = w / 2f
            val cy = h * 0.66f
            val rOut = w * 0.42f
            val rIn = w * 0.21f
            val stones = 5
            val gap = 4.5f
            val per = 180f / stones
            fun stone(i: Int, outer: Float, extra: Float = 0f): Path {
                val a0 = 180f + per * i + gap / 2f - extra / 2f
                val sw = per - gap + extra
                val oRect = androidx.compose.ui.geometry.Rect(cx - outer, cy - outer, cx + outer, cy + outer)
                val iRect = androidx.compose.ui.geometry.Rect(cx - rIn, cy - rIn, cx + rIn, cy + rIn)
                return Path().apply {
                    arcTo(oRect, a0, sw, true)
                    arcTo(iRect, a0 + sw, -sw, false)
                    close()
                }
            }
            val side = (0 until stones).map { stone(it, rOut) }
            val crown = stone(stones / 2, rOut + w * 0.09f, extra = 5f)
            val halo = Brush.radialGradient(
                listOf(color, Color.Transparent),
                center = Offset(w / 2, h * 0.5f), radius = w * 0.5f,
            )
            val post = androidx.compose.ui.geometry.Size(rOut - rIn, h * 0.20f)
            val pivot = Offset(cx, cy)
            onDrawBehind {
                val breathe = if (still) 0.5f else breatheS.value
                val spin = if (still) 0f else spinS.value
                val pulse = if (still) 0.5f else pulseS.value
                val wobble = if (still) 0f else wobbleS.value
                val dim = if (state == KeystoneState.ERROR) 0.35f else 1f
                val lvl = level.coerceIn(0f, 1f)

                // --- motion per state
                val scaleV = when (state) {
                    KeystoneState.IDLE -> 0.96f + 0.05f * breathe
                    KeystoneState.LISTENING -> 0.94f + 0.12f * lvl
                    KeystoneState.WORKING -> 0.97f + 0.02f * pulse
                    KeystoneState.SPEAKING -> 0.95f + 0.07f * pulse
                    KeystoneState.WAITING -> 1f
                    KeystoneState.ERROR -> 0.94f
                }
                val tilt = if (state == KeystoneState.WAITING) wobble * 6f else 0f
                val glow = when (state) {
                    KeystoneState.IDLE -> 0.10f + 0.10f * breathe
                    KeystoneState.LISTENING -> 0.18f + 0.55f * lvl
                    KeystoneState.WORKING -> 0.25f + 0.2f * pulse
                    KeystoneState.SPEAKING -> 0.25f + 0.35f * pulse
                    KeystoneState.WAITING -> 0.30f
                    KeystoneState.ERROR -> 0f
                }

                // soft halo (an arch of stones with the keystone lit at the crown)
                if (glow > 0f) drawCircle(halo, radius = w * 0.5f, center = Offset(w / 2, h * 0.5f), alpha = glow * dim)

                rotate(tilt, pivot = pivot) {
                    scale(scaleV, pivot = pivot) {
                        for (i in 0 until stones) {
                            if (i == stones / 2) continue
                            // side stones dim, with a ripple of light travelling over them while working
                            val ripple = when (state) {
                                KeystoneState.WORKING -> {
                                    val ph = ((spin / 360f) * stones) % stones
                                    val d = kotlin.math.abs(ph - i).coerceAtMost(stones - kotlin.math.abs(ph - i))
                                    (1f - d / 1.6f).coerceIn(0f, 1f)
                                }
                                KeystoneState.LISTENING -> lvl * kotlin.math.abs(sin((i + 1) * 1.7f + spin * 0.0175f)).coerceAtLeast(0.4f)
                                KeystoneState.SPEAKING -> pulse * (0.5f + 0.5f * kotlin.math.abs(sin((i + 1) * 1.3f)))
                                else -> 0f
                            }
                            drawPath(side[i], color = color, alpha = (0.38f + 0.5f * ripple) * dim)
                        }
                        // base posts
                        drawRect(color, Offset(cx - rOut, cy), post, alpha = 0.38f * dim)
                        drawRect(color, Offset(cx + rIn, cy), post, alpha = 0.38f * dim)
                        // the keystone
                        drawPath(crown, color = color, alpha = dim)
                    }
                }

                // orbiting dot while working
                if (state == KeystoneState.WORKING) {
                    val a = spin * (PI / 180f).toFloat()
                    val r = w * 0.46f
                    drawCircle(
                        color = Color.White,
                        alpha = 0.9f,
                        radius = w * 0.028f,
                        center = Offset(w / 2 + r * cos(a), h * 0.58f + r * sin(a)),
                    )
                }
            }
        },
    )
}
