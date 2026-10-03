package com.cornerstone.agency.voice

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.theme.AgencyTokens
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import com.cornerstone.agency.ui.components.MicIcon

/**
 * Agency-styled voice plate — ink/gold restyle of the Hermes VoicePlate:
 * no glow/chamfers, hairline accents, level-reactive mic orb kept.
 * Designed to live inside a ModalBottomSheet.
 */
@Composable
fun VoicePlate(
    session: VoiceSession,
    onClose: () -> Unit,
    botName: String = "Kaori",
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val s by session.uiState.collectAsState()
    val t by session.uiText.collectAsState()
    val d by session.detailLine.collectAsState()
    val lvl by session.micLevel.collectAsState()
    val turns by session.history.collectAsState()
    val muted by session.muted.collectAsState()

    val accent = when (s) {
        VoiceSession.State.CONNECTING, VoiceSession.State.RECONNECTING -> colors.secondary
        VoiceSession.State.LISTENING -> colors.gold
        VoiceSession.State.THINKING -> colors.warm
        VoiceSession.State.SPEAKING -> colors.primary
        VoiceSession.State.ERROR -> colors.destructive
    }
    val status = when {
        muted -> "Muted"
        s == VoiceSession.State.LISTENING -> "Listening"
        s == VoiceSession.State.THINKING -> "Thinking"
        s == VoiceSession.State.SPEAKING -> "Speaking"
        s == VoiceSession.State.ERROR -> "Can't connect"
        else -> "Connecting"
    }

    // Call card: avatar orb + live waveform, last words, and four round buttons.
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 420.dp)
            .padding(horizontal = space.lg, vertical = space.md),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(space.md))
        MicOrb(state = s, level = if (muted) 0f else lvl, accent = accent, orbSize = 112.dp)
        Spacer(Modifier.height(space.md))
        Text(botName, style = MaterialTheme.typography.displaySmall, color = colors.foreground)
        Text(
            status,
            color = accent,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite },
        )
        Spacer(Modifier.height(space.md))
        Waveform(level = if (muted) 0f else lvl, active = s == VoiceSession.State.LISTENING || s == VoiceSession.State.SPEAKING, accent = accent)
        Spacer(Modifier.height(space.md))
        if (!t.trimEnd('…', '.').equals(status, ignoreCase = true)) Text(
            t,
            color = colors.secondary,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
        )
        if (d.isNotBlank() && s == VoiceSession.State.ERROR) {
            Text(
                d,
                color = colors.tertiary,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = space.sm),
            )
        }
        if (turns.isNotEmpty()) {
            ConversationHistory(turns = turns, accent = accent, botName = botName)
        }
        Spacer(Modifier.height(space.xl))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CallButton(
                MicIcon,
                if (muted) "Unmute" else "Mute",
                container = if (muted) colors.foreground else colors.card,
                tint = if (muted) colors.chrome else colors.foreground,
            ) { session.setMuted(!muted) }
            CallButton(
                Icons.Filled.Close, "End call",
                container = colors.destructive, tint = Color.White, size = 72.dp,
                onClick = onClose,
            )
        }
        Spacer(Modifier.height(space.md))
    }
}

@Composable
private fun CallButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    container: Color,
    tint: Color,
    size: androidx.compose.ui.unit.Dp = 60.dp,
    onClick: () -> Unit,
) {
    Box(
        Modifier
            .size(maxOf(size, 48.dp))
            .clip(androidx.compose.foundation.shape.CircleShape)
            .background(container)
            .clickable(role = androidx.compose.ui.semantics.Role.Button, onClick = onClick)
            .semantics { contentDescription = label },
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(size * 0.4f))
    }
}

/** Live bar waveform driven by mic level; idle shimmer otherwise. */
@Composable
private fun Waveform(level: Float, active: Boolean, accent: Color) {
    val t by rememberInfiniteTransition(label = "wave").animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(900), RepeatMode.Restart), label = "waveT",
    )
    Canvas(Modifier.fillMaxWidth().height(40.dp)) {
        val bars = 28
        val gap = size.width / bars
        for (i in 0 until bars) {
            val phase = kotlin.math.sin((t * 2f * Math.PI + i * 0.55).toDouble()).toFloat()
            val amp = if (active) 0.18f + (0.25f + level * 0.57f) * (0.5f + 0.5f * phase) else 0.10f
            val h = size.height * amp.coerceIn(0.08f, 1f)
            val x = gap * i + gap / 2f
            drawLine(
                color = accent.copy(alpha = if (active) 0.9f else 0.35f),
                start = Offset(x, size.height / 2f - h / 2f),
                end = Offset(x, size.height / 2f + h / 2f),
                strokeWidth = 4.dp.toPx(),
                cap = androidx.compose.ui.graphics.StrokeCap.Round,
            )
        }
    }
}

/** Recent turns, newest pinned to the bottom of a sunken scroll strip. */
@Composable
private fun ConversationHistory(
    turns: List<VoiceSession.HistoryTurn>,
    accent: Color,
    botName: String,
) {
    val colors = AgencyTokens.colors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = AgencyTokens.space.sm)
            .clip(RoundedCornerShape(10.dp))
            .background(colors.sidebar),
    ) {
        LazyColumn(
            reverseLayout = true,
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 120.dp)
                .padding(horizontal = 10.dp, vertical = 6.dp),
        ) {
            items(
                items = turns.takeLast(MAX_VISIBLE_TURNS).asReversed(),
                key = { it.id },
            ) { turn ->
                HistoryRow(turn = turn, accent = accent, botName = botName)
            }
        }
    }
}

@Composable
private fun HistoryRow(turn: VoiceSession.HistoryTurn, accent: Color, botName: String) {
    val colors = AgencyTokens.colors
    val isUser = turn.speaker == VoiceSession.Speaker.USER
    Row(verticalAlignment = Alignment.Top) {
        Text(
            text = if (isUser) "you" else botName.lowercase(),
            color = if (isUser) colors.tertiary else accent,
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(60.dp),
        )
        Text(
            text = turn.text,
            color = colors.secondary,
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private const val MAX_VISIBLE_TURNS = 10

@Composable
private fun MicOrb(
    state: VoiceSession.State,
    level: Float,
    accent: Color,
    orbSize: androidx.compose.ui.unit.Dp = 44.dp,
) {
    val pulse by rememberInfiniteTransition(label = "pulse").animateFloat(
        initialValue = 0.6f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse),
        label = "pulseAnim",
    )
    // ring scale follows real mic level while listening; gentle pulse otherwise
    val energy = if (state == VoiceSession.State.LISTENING) {
        0.35f + level * 0.65f
    } else {
        pulse * 0.5f
    }

    Canvas(
        Modifier
            .size(orbSize)
            .semantics {
                contentDescription = "Assistant state: ${state.name.lowercase()}"
            },
    ) {
        val r = size.minDimension / 2f
        drawCircle(
            color = accent.copy(alpha = 0.25f + energy * 0.4f),
            radius = r * (0.72f + energy * 0.22f),
            style = Stroke(width = 2.dp.toPx()),
        )
        drawCircle(color = accent, radius = r * 0.38f)
        drawCircle(
            color = com.cornerstone.agency.theme.AgencyChrome,
            radius = r * 0.14f,
            center = Offset(r, r),
        )
    }
}
