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

/**
 * Agency-styled voice plate — ink/gold restyle of the Hermes VoicePlate:
 * no glow/chamfers, hairline accents, level-reactive mic orb kept.
 * Designed to live inside a ModalBottomSheet.
 */
@Composable
fun VoicePlate(
    session: VoiceSession,
    onClose: () -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val s by session.uiState.collectAsState()
    val t by session.uiText.collectAsState()
    val d by session.detailLine.collectAsState()
    val lvl by session.micLevel.collectAsState()
    val turns by session.history.collectAsState()

    val accent = when (s) {
        VoiceSession.State.CONNECTING, VoiceSession.State.RECONNECTING -> colors.secondary
        VoiceSession.State.LISTENING -> colors.gold
        VoiceSession.State.THINKING -> colors.warm
        VoiceSession.State.SPEAKING -> colors.primary
        VoiceSession.State.ERROR -> colors.destructive
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = space.lg, vertical = space.md),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            MicOrb(state = s, level = lvl, accent = accent)
            Spacer(Modifier.width(space.md))
            Column(Modifier.weight(1f)) {
                Text(
                    "VOICE",
                    color = accent,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                )
                Text(
                    t,
                    color = colors.foreground,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.semantics {
                        liveRegion = LiveRegionMode.Polite
                    },
                )
            }
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, "Close", tint = colors.tertiary)
            }
        }
        if (d.isNotBlank()) {
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
            ConversationHistory(turns = turns, accent = accent)
        }
        Spacer(Modifier.height(space.md))
    }
}

/** Recent turns, newest pinned to the bottom of a sunken scroll strip. */
@Composable
private fun ConversationHistory(
    turns: List<VoiceSession.HistoryTurn>,
    accent: Color,
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
                .heightIn(max = 96.dp)
                .padding(horizontal = 10.dp, vertical = 6.dp),
        ) {
            items(
                items = turns.takeLast(MAX_VISIBLE_TURNS).asReversed(),
                key = { it.id },
            ) { turn ->
                HistoryRow(turn = turn, accent = accent)
            }
        }
    }
}

@Composable
private fun HistoryRow(turn: VoiceSession.HistoryTurn, accent: Color) {
    val colors = AgencyTokens.colors
    val isUser = turn.speaker == VoiceSession.Speaker.USER
    Row(verticalAlignment = Alignment.Top) {
        Text(
            text = if (isUser) "you" else "hermes",
            color = if (isUser) colors.tertiary else accent,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(52.dp),
        )
        Text(
            text = turn.text,
            color = colors.secondary,
            fontSize = 11.sp,
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
            .size(44.dp)
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
