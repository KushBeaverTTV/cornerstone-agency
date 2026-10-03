package com.cornerstone.agency.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.data.PendingRequest
import com.cornerstone.agency.data.RequestKind
import com.cornerstone.agency.data.RequestState
import com.cornerstone.agency.theme.AgencyTokens
import kotlinx.coroutines.delay

private val CHOICE_LABEL = mapOf(
    "once" to "Allow once",
    "always" to "Always",
    "session" to "This session",
    "deny" to "Deny",
)

/**
 * Answerable card for tui_gateway server→client requests (approval / clarify /
 * secret / sudo / mcp.setup / one-string prompts). Left accent bar is colored
 * per kind; open cards carry an "expires in Xm" countdown; resolved cards
 * collapse to a one-line muted summary.
 */
@Composable
fun RequestCard(
    req: PendingRequest,
    onApprove: (choice: String) -> Unit,
    onClarify: (answers: Map<String, String>) -> Unit,
    onValue: (String) -> Unit,
    onMcp: (approved: Boolean) -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val haptics = LocalHapticFeedback.current

    val accent = when (req.kind) {
        RequestKind.Approval -> colors.gold
        RequestKind.Clarify -> colors.primary
        RequestKind.Secret, RequestKind.Sudo -> colors.warm
        RequestKind.McpSetup -> colors.defaultViolet
        RequestKind.Value -> colors.tertiary
    }
    val kicker = when (req.kind) {
        RequestKind.Approval -> "APPROVAL · ${req.toolName.ifBlank { "command" }}"
        else -> req.kind.kicker
    }
    val done = req.state == RequestState.Resolved || req.state == RequestState.Expired

    // Re-read the clock once a minute so the countdown stays honest.
    var now by remember { mutableStateOf(System.currentTimeMillis()) }
    LaunchedEffect(req.state) {
        while (req.state == RequestState.Open) {
            delay(30_000)
            now = System.currentTimeMillis()
        }
    }
    val minutesLeft = ((req.expiresAt - now) / 60_000).coerceAtLeast(0)

    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(12.dp))
            .background(colors.elevated),
    ) {
        Box(Modifier.width(3.dp).fillMaxHeight().background(accent))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = space.md, vertical = space.sm),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    kicker,
                    style = MaterialTheme.typography.labelSmall,
                    color = accent,
                )
                Spacer(Modifier.weight(1f))
                when (req.state) {
                    RequestState.Open -> Text(
                        "expires in ${minutesLeft}m",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.tertiary,
                    )
                    RequestState.Sending -> Text(
                        "sending…",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.gold,
                    )
                    RequestState.Resolved -> Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            if (req.resolution.startsWith("Denied") || req.resolution == "cancelled") {
                                Icons.Filled.Close
                            } else {
                                Icons.Filled.Check
                            },
                            contentDescription = null,
                            tint = colors.tertiary,
                            modifier = Modifier.padding(end = 4.dp).width(14.dp),
                        )
                        Text(
                            req.resolution,
                            style = MaterialTheme.typography.labelSmall,
                            color = colors.tertiary,
                        )
                    }
                    RequestState.Expired -> Text(
                        "Expired",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.destructive,
                    )
                }
            }

            if (!done) {
                Spacer(Modifier.height(space.xs))
                RequestBody(req)
                Spacer(Modifier.height(space.sm))
                RequestActions(
                    req = req,
                    onApprove = {
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        onApprove(it)
                    },
                    onClarify = onClarify,
                    onValue = onValue,
                    onMcp = onMcp,
                )
                if (req.respondError.isNotEmpty()) {
                    Spacer(Modifier.height(space.xxs))
                    Text(
                        "Couldn't send: ${req.respondError}",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.destructive,
                    )
                }
            } else if (req.state == RequestState.Resolved &&
                (req.command.isNotEmpty() || req.questions.isNotEmpty() || req.prompt.isNotEmpty())
            ) {
                // Resolved cards collapse to the summary plus a muted hint of what was asked.
                Spacer(Modifier.height(space.xxs))
                Text(
                    req.command.ifBlank {
                        req.questions.firstOrNull()?.question ?: req.prompt
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.tertiary,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun RequestBody(req: PendingRequest) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    when (req.kind) {
        RequestKind.Approval -> {
            if (req.command.isNotEmpty()) {
                Text(
                    req.command,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    color = colors.foreground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(colors.sidebar)
                        .padding(horizontal = space.sm, vertical = space.xs),
                )
            }
            if (req.description.isNotEmpty()) {
                Spacer(Modifier.height(space.xxs))
                Text(
                    req.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.secondary,
                )
            }
        }
        RequestKind.Clarify -> {
            req.questions.forEach { q ->
                Text(
                    q.question,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.foreground,
                )
                if (q.choices.isNotEmpty()) {
                    // The chips live in RequestActions (they are the answer).
                }
            }
        }
        RequestKind.Secret -> {
            if (req.prompt.isNotEmpty()) {
                Text(req.prompt, style = MaterialTheme.typography.bodyMedium, color = colors.foreground)
            }
            if (req.envVar.isNotEmpty()) {
                Text(
                    req.envVar,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = colors.tertiary,
                )
            }
        }
        RequestKind.Sudo -> Text(
            "A command on your computer is waiting for sudo.",
            style = MaterialTheme.typography.bodySmall,
            color = colors.secondary,
        )
        RequestKind.McpSetup -> Text(
            req.description.ifBlank { "An MCP server wants to be configured." },
            style = MaterialTheme.typography.bodySmall,
            color = colors.secondary,
        )
        RequestKind.Value -> {
            if (req.description.isNotEmpty() || req.prompt.isNotEmpty()) {
                Text(
                    req.description.ifBlank { req.prompt },
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.foreground,
                )
            }
        }
    }
}

@Composable
private fun RequestActions(
    req: PendingRequest,
    onApprove: (String) -> Unit,
    onClarify: (Map<String, String>) -> Unit,
    onValue: (String) -> Unit,
    onMcp: (Boolean) -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val enabled = req.state == RequestState.Open
    when (req.kind) {
        RequestKind.Approval -> {
            val choices = req.choices.ifEmpty { listOf("once", "session", "always", "deny") }
            Row(horizontalArrangement = Arrangement.spacedBy(space.xs)) {
                choices.forEach { c ->
                    if (c == "always" && !req.allowPermanent) return@forEach
                    if (c == "session" && !req.allowSession) return@forEach
                    Chip(
                        label = CHOICE_LABEL[c] ?: c,
                        enabled = enabled,
                        filled = c == "once",
                        danger = c == "deny",
                        onClick = { onApprove(c) },
                    )
                }
            }
        }
        RequestKind.Clarify -> ClarifyControls(req, enabled, onClarify)
        RequestKind.Secret, RequestKind.Sudo, RequestKind.Value ->
            ValueControls(
                req = req,
                enabled = enabled,
                placeholder = when (req.kind) {
                    RequestKind.Sudo -> "sudo password"
                    RequestKind.Secret -> "Secret value"
                    else -> "Answer"
                },
                masked = req.kind != RequestKind.Value,
                onSend = onValue,
            )
        RequestKind.McpSetup -> Row(horizontalArrangement = Arrangement.spacedBy(space.xs)) {
            Chip(label = "Allow", enabled = enabled, filled = true) { onMcp(true) }
            Chip(label = "Deny", enabled = enabled, danger = true) { onMcp(false) }
        }
    }
}

@Composable
private fun ClarifyControls(
    req: PendingRequest,
    enabled: Boolean,
    onClarify: (Map<String, String>) -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val answers = remember { mutableStateMapOf<String, String>() }
    var freeText by remember { mutableStateOf("") }

    val needsText = req.questions.any { it.choices.isEmpty() } || req.questions.isEmpty()
    val allAnswered = req.questions.isNotEmpty() &&
        req.questions.all { q -> q.choices.isEmpty() || answers.containsKey(q.qid) }

    Column {
        req.questions.filter { it.choices.isNotEmpty() }.forEach { q ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(space.xs),
                modifier = Modifier.padding(bottom = space.xxs),
            ) {
                q.choices.forEach { c ->
                    Chip(
                        label = c,
                        enabled = enabled,
                        filled = answers[q.qid] == c,
                        onClick = {
                            answers[q.qid] = c
                            if (req.questions.size == 1) onClarify(mapOf(q.qid to c))
                        },
                    )
                }
            }
        }
        if (needsText) {
            Row(verticalAlignment = Alignment.Bottom) {
                AgencyTextField(
                    value = freeText,
                    onValueChange = { freeText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = "Type your answer",
                    singleLine = true,
                )
                Spacer(Modifier.width(space.sm))
                Chip(
                    label = "Send",
                    enabled = enabled && freeText.isNotBlank(),
                    filled = true,
                    onClick = {
                        val qid = req.questions.firstOrNull { it.choices.isEmpty() }?.qid ?: ""
                        onClarify(mapOf(qid to freeText.trim()))
                        freeText = ""
                    },
                )
            }
        } else if (req.questions.size > 1) {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Chip(
                    label = "Send",
                    enabled = enabled && allAnswered,
                    filled = true,
                    onClick = { onClarify(answers.toMap()) },
                )
            }
        }
        if (req.questions.size == 1 && req.questions[0].multiSelect) {
            Text(
                "multi-select — the server sees your last tap",
                style = MaterialTheme.typography.labelSmall,
                color = colors.tertiary,
            )
        }
    }
}

@Composable
private fun ValueControls(
    req: PendingRequest,
    enabled: Boolean,
    placeholder: String,
    masked: Boolean,
    onSend: (String) -> Unit,
) {
    val space = AgencyTokens.space
    var text by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.Bottom) {
        Box(Modifier.weight(1f)) {
            AgencyTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = placeholder,
                singleLine = true,
                secret = masked && !visible,
            )
        }
        if (masked) {
            Icon(
                EyeIcon,
                contentDescription = if (visible) "Hide" else "Show",
                tint = if (visible) AgencyTokens.colors.gold else AgencyTokens.colors.tertiary,
                modifier = Modifier
                    .padding(start = space.xs, bottom = space.xs)
                    .clickable { visible = !visible },
            )
        }
        Spacer(Modifier.width(space.sm))
        Chip(
            label = "Send",
            enabled = enabled && text.isNotEmpty(),
            filled = true,
            onClick = {
                val v = text
                text = "" // never keep a secret on screen after sending
                onSend(v)
            },
        )
    }
}

@Composable
private fun Chip(
    label: String,
    enabled: Boolean,
    filled: Boolean = false,
    danger: Boolean = false,
    onClick: () -> Unit,
) {
    val colors = AgencyTokens.colors
    val bg = when {
        !enabled -> colors.card
        danger -> colors.card
        filled -> colors.primary
        else -> colors.card
    }
    val fg = when {
        !enabled -> colors.tertiary
        danger -> colors.destructive
        filled -> colors.onPrimary
        else -> colors.secondary
    }
    Text(
        label,
        style = MaterialTheme.typography.labelMedium,
        color = fg,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bg)
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 12.dp, vertical = 7.dp),
    )
}
