package com.cornerstone.agency.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.data.ChatItem
import com.cornerstone.agency.data.ChatKind
import com.cornerstone.agency.data.ChatRepository
import com.cornerstone.agency.data.GatewayState
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.isCompactHeight
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.BotMark
import com.cornerstone.agency.ui.components.KeystoneState
import com.cornerstone.agency.ui.components.MicIcon
import com.cornerstone.agency.ui.components.ModelPickerSheet
import com.cornerstone.agency.ui.components.botAvatarColor
import com.cornerstone.agency.ui.components.shortModelLabel
import com.cornerstone.agency.voice.VoicePlate
import com.cornerstone.agency.voice.VoiceSession
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownColor
import com.mikepenz.markdown.m3.markdownTypography
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.derivedStateOf
import androidx.compose.foundation.horizontalScroll
import androidx.compose.ui.semantics.contentDescription

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ChatBubble(
    item: ChatItem,
    chat: com.cornerstone.agency.data.ChatSource,
    scope: kotlinx.coroutines.CoroutineScope,
    onLongPress: (ChatItem) -> Unit = {},
    onRetry: () -> Unit = {},
    onReact: (ChatItem, String) -> Unit = { _, _ -> },
    highlighted: Boolean = false,
) {
    val colors = AgencyTokens.colors
    val haptic = LocalHapticFeedback.current
    val press = Modifier.combinedClickable(
        onClick = {},
        onLongClickLabel = "Message actions",
        onLongClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            onLongPress(item)
        },
    )
    // Current search hit: a soft gold wash (an outline read like the reaction chips).
    val mark = if (highlighted) {
        Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(colors.gold.copy(alpha = 0.14f))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    } else Modifier
    when (item.kind) {
        ChatKind.User -> {
            // Harness preambles (voice / cron) are not something you said — show a quiet marker.
            val preamble = preambleLabel(item.text)
            if (preamble != null) {
                Row(
                    Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        preamble,
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.tertiary,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colors.sidebar)
                            .border(1.dp, colors.stroke, CircleShape)
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                    )
                }
                return
            }
            var exp by remember(item.id) { mutableStateOf(false) }
            var overflow by remember(item.id) { mutableStateOf(false) }
            Column(Modifier.fillMaxWidth().then(mark), horizontalAlignment = Alignment.End) {
                MessageAttachments(item.attachments, alignEnd = true)
                if (item.text.isNotBlank()) Bubble(
                    colors.bubble,
                    Modifier.widthIn(max = 340.dp).then(press),
                ) {
                    Column {
                        Text(
                            item.text,
                            style = MaterialTheme.typography.bodyLarge,
                            color = colors.foreground,
                            maxLines = if (exp) Int.MAX_VALUE else 8,
                            overflow = TextOverflow.Ellipsis,
                            onTextLayout = { if (!exp) overflow = it.hasVisualOverflow },
                        )
                        if (overflow || exp) {
                            Text(
                                if (exp) "Show less" else "Show more",
                                style = MaterialTheme.typography.labelLarge,
                                color = colors.gold,
                                modifier = Modifier
                                    .clickable { exp = !exp }
                                    .heightIn(min = 48.dp)
                                    .wrapContentHeight(Alignment.CenterVertically),
                            )
                        }
                    }
                }
                ReactionRow(item, alignEnd = true, onReact = onReact)
            }
        }

        // Assistant text is full width and borderless; only the user keeps a bubble.
        ChatKind.Assistant -> Column(
            Modifier.fillMaxWidth().then(mark).then(press).padding(vertical = 2.dp),
        ) {
            if (item.text.isEmpty() && item.streaming) {
                Text("▌", color = colors.gold, style = MaterialTheme.typography.bodyLarge)
            } else {
                Markdown(
                    content = com.cornerstone.agency.ui.components.keepLineBreaks(item.text) + if (item.streaming) " ▌" else "",
                    colors = markdownColor(
                        text = colors.foreground,
                        codeBackground = colors.elevated,
                        inlineCodeBackground = colors.elevated,
                        dividerColor = colors.stroke,
                        tableBackground = colors.card,
                    ),
                    typography = markdownTypography(
                        text = MaterialTheme.typography.bodyLarge,
                        paragraph = MaterialTheme.typography.bodyLarge,
                        ordered = MaterialTheme.typography.bodyLarge,
                        bullet = MaterialTheme.typography.bodyLarge,
                        list = MaterialTheme.typography.bodyLarge,
                        code = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Monospace, fontSize = 13.sp, lineHeight = 19.sp),
                        inlineCode = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Monospace, fontSize = 14.sp),
                        table = MaterialTheme.typography.bodyMedium,
                    ),
                    dimens = com.mikepenz.markdown.model.markdownDimens(tableCellWidth = 150.dp),
                    components = com.mikepenz.markdown.compose.components.markdownComponents(
                        codeFence = { m ->
                            val n = m.node
                            val parts = n.children.filter { it.type == org.intellij.markdown.MarkdownTokenTypes.CODE_FENCE_CONTENT }
                            val code = if (parts.isEmpty()) "" else m.content.substring(parts.first().startOffset, parts.last().endOffset)
                            val lang = n.children.firstOrNull { it.type == org.intellij.markdown.MarkdownTokenTypes.FENCE_LANG }
                                ?.let { m.content.substring(it.startOffset, it.endOffset) }.orEmpty()
                            CodeBlockCard(code.trimEnd('\n'), lang.trim())
                        },
                        codeBlock = { m ->
                            val parts = m.node.children.filter { it.type == org.intellij.markdown.MarkdownTokenTypes.CODE_LINE }
                            val code = if (parts.isEmpty()) "" else m.content.substring(parts.first().startOffset, parts.last().endOffset)
                            CodeBlockCard(code.trimEnd('\n'), "")
                        },
                    ),
                    padding = com.mikepenz.markdown.model.markdownPadding(
                        listItemTop = 4.dp, listItemBottom = 4.dp, listIndent = 12.dp,
                    ),
                    modifier = Modifier,
                )
            }
            MessageAttachments(item.attachments, alignEnd = false)
            ReactionRow(item, alignEnd = false, onReact = onReact)
        }

        ChatKind.Thinking -> ThinkingBubble(item)

        ChatKind.Tool -> Row(
            Modifier.fillMaxWidth().padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                item.text,
                color = colors.tertiary,
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        ChatKind.Status -> Row(Modifier.fillMaxWidth().then(press), horizontalArrangement = Arrangement.Center) {
            // Backend notices can be long (provider errors); two lines, full text via long-press.
            Text(
                item.text,
                style = MaterialTheme.typography.labelSmall,
                color = colors.tertiary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        }

        ChatKind.Error -> Row(
            Modifier.fillMaxWidth().then(press),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                item.text,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.destructive,
                modifier = Modifier.weight(1f, fill = false),
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "Retry",
                style = MaterialTheme.typography.labelLarge,
                color = colors.gold,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(role = androidx.compose.ui.semantics.Role.Button, onClickLabel = "Retry the last message") { onRetry() }
                    .heightIn(min = 48.dp)
                    .padding(horizontal = 12.dp)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
        }

        ChatKind.Request -> {
            val req = item.request
            if (req != null) {
                com.cornerstone.agency.ui.components.RequestCard(
                    req = req,
                    onApprove = { choice -> scope.launch { chat.approveRequest(item.id, choice) } },
                    onClarify = { answers -> scope.launch { chat.answerClarify(item.id, answers) } },
                    onValue = { v -> scope.launch { chat.submitRequestValue(item.id, v) } },
                    onMcp = { ok -> scope.launch { chat.respondMcp(item.id, ok) } },
                )
            } else {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    Bubble(colors.elevated, Modifier.widthIn(max = 300.dp)) {
                        Text(
                            "Request",
                            style = MaterialTheme.typography.labelSmall,
                            color = colors.gold,
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            item.text,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.secondary,
                        )
                    }
                }
            }
        }
    }
}

/** Voice / cron harness preambles → a short human label; null for real messages. */
internal fun preambleLabel(text: String): String? {
    val first = text.lineSequence().firstOrNull { it.isNotBlank() }?.trim() ?: return null
    return when {
        first.startsWith("You are being used through a hands-free voice", true) -> "Voice conversation"
        first.startsWith("[IMPORTANT: You are running as a scheduled cron", true) -> "Scheduled job"
        else -> null
    }
}

@Composable
internal fun Bubble(color: androidx.compose.ui.graphics.Color, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    // Flat, borderless (Muse): the tint alone separates you from the agent.
    Box(
        modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color)
            .padding(horizontal = 16.dp, vertical = 11.dp),
    ) {
        content()
    }
}

@Composable
internal fun ThinkingBubble(item: ChatItem) {
    val colors = AgencyTokens.colors
    var expanded by remember(item.id) { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colors.sidebar)
            .clickable { expanded = !expanded }
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            "thinking",
            style = MaterialTheme.typography.labelSmall,
            color = colors.tertiary,
        )
        Spacer(Modifier.height(2.dp))
        Text(
            item.text,
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic,
            color = colors.tertiary,
            maxLines = if (expanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}


// ---- grouped "work" rows --------------------------------------------------

internal sealed interface ChatRow {
    val key: String
    data class Single(val item: ChatItem) : ChatRow { override val key: String get() = item.id }
    data class Work(val items: List<ChatItem>, val live: Boolean) : ChatRow {
        override val key: String get() = "work-" + items.first().id
    }
}

/** Consecutive thinking + tool items collapse into one quiet chip per turn. */
internal fun buildChatRows(items: List<ChatItem>, streaming: Boolean): List<ChatRow> {
    val out = ArrayList<ChatRow>()
    var group = ArrayList<ChatItem>()
    fun flush(last: Boolean) {
        if (group.isEmpty()) return
        val live = (last && streaming) || group.any { it.kind == ChatKind.Tool && !it.done }
        out += ChatRow.Work(group, live)
        group = ArrayList()
    }
    items.forEachIndexed { i, it ->
        if (it.kind == ChatKind.Thinking || it.kind == ChatKind.Tool) {
            group.add(it)
        } else {
            flush(false)
            out += ChatRow.Single(it)
        }
    }
    flush(true)
    return out
}

@Composable
internal fun WorkGroup(g: ChatRow.Work) {
    val colors = AgencyTokens.colors
    var expanded by remember(g.key) { mutableStateOf(false) }
    val tools = g.items.filter { it.kind == ChatKind.Tool }
    val thoughts = g.items.filter { it.kind == ChatKind.Thinking }
    val label = buildString {
        if (thoughts.isNotEmpty()) append(if (g.live) "Thinking" else "Thought")
        if (tools.isNotEmpty()) {
            if (isNotEmpty()) append(" · ")
            append(tools.size).append(if (tools.size == 1) " tool" else " tools")
        }
    }.ifEmpty { "Working" }
    val pulse by rememberInfiniteTransition(label = "pulse").animateFloat(
        initialValue = 0.35f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse), label = "pulseA",
    )
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .heightIn(min = 48.dp)
                .clip(RoundedCornerShape(24.dp))
                .clickable { expanded = !expanded }
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier
                    .size(8.dp)
                    .alpha(if (g.live) pulse else 1f)
                    .clip(CircleShape)
                    .background(if (g.live) colors.gold else colors.tertiary),
            )
            Spacer(Modifier.width(8.dp))
            Text(label, style = MaterialTheme.typography.labelLarge, color = colors.secondary)
            Icon(
                Icons.Filled.KeyboardArrowDown, if (expanded) "Collapse" else "Expand",
                tint = colors.tertiary,
                modifier = Modifier.size(20.dp).rotate(if (expanded) 180f else 0f),
            )
        }
        if (expanded) {
            Column(Modifier.padding(start = 16.dp, bottom = 8.dp)) {
                g.items.forEach { it ->
                    when (it.kind) {
                        ChatKind.Thinking -> Text(
                            it.text,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = colors.tertiary,
                            modifier = Modifier.padding(vertical = 4.dp),
                        )
                        else -> Text(
                            it.text,
                            color = colors.secondary,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(vertical = 3.dp),
                        )
                    }
                }
            }
        }
    }
}

/** System clipboard without the deprecated Compose wrapper. */
internal fun copyToClipboard(ctx: android.content.Context, text: String) {
    ctx.getSystemService(android.content.ClipboardManager::class.java)
        ?.setPrimaryClip(android.content.ClipData.newPlainText("text", text))
}


/** Fenced code: language label + Copy, monospace, scrolls sideways instead of wrapping. */
@Composable
internal fun CodeBlockCard(code: String, lang: String) {
    val colors = AgencyTokens.colors
    val haptic = LocalHapticFeedback.current
    val ctx = LocalContext.current
    val shape = RoundedCornerShape(14.dp)
    Column(
        Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .clip(shape)
            .background(colors.card)
            .border(1.dp, colors.stroke, shape),
    ) {
        Row(
            Modifier.fillMaxWidth().padding(start = 14.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                lang.ifBlank { "code" },
                style = MaterialTheme.typography.labelMedium,
                color = colors.tertiary,
                modifier = Modifier.weight(1f),
            )
            Text(
                "Copy",
                style = MaterialTheme.typography.labelLarge,
                color = colors.gold,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .semantics { contentDescription = "Copy code" }
                    .clickable(role = androidx.compose.ui.semantics.Role.Button) {
                        copyToClipboard(ctx, code)
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        Toast.makeText(ctx, "Code copied", Toast.LENGTH_SHORT).show()
                    }
                    .heightIn(min = 48.dp)
                    .padding(horizontal = 12.dp)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
        }
        Text(
            code,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily.Monospace, fontSize = 13.sp, lineHeight = 19.sp,
            ),
            color = colors.foreground,
            softWrap = false,
            modifier = Modifier
                .horizontalScroll(androidx.compose.foundation.rememberScrollState())
                .padding(start = 14.dp, end = 14.dp, bottom = 14.dp),
        )
    }
}

/** Tapback chips under a message; tapping yours clears it. */
@Composable
private fun ReactionRow(item: ChatItem, alignEnd: Boolean, onReact: (ChatItem, String) -> Unit) {
    if (item.reactions.isEmpty()) return
    val colors = AgencyTokens.colors
    Row(
        Modifier.fillMaxWidth().padding(top = 4.dp),
        horizontalArrangement = if (alignEnd) Arrangement.End else Arrangement.Start,
    ) {
        item.reactions.groupBy { it.emoji }.forEach { (emoji, list) ->
            val mine = list.any { it.author == "user" }
            Row(
                Modifier
                    .padding(end = 6.dp)
                    .clip(CircleShape)
                    .background(if (mine) colors.elevated else colors.card)
                    .border(1.dp, if (mine) colors.gold else colors.stroke, CircleShape)
                    .clickable(enabled = mine, role = androidx.compose.ui.semantics.Role.Button, onClickLabel = "Remove reaction") {
                        onReact(item, emoji)
                    }
                    .heightIn(min = 32.dp)
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(emoji, style = MaterialTheme.typography.bodyMedium)
                if (list.size > 1) {
                    Spacer(Modifier.width(4.dp))
                    Text("${list.size}", style = MaterialTheme.typography.labelSmall, color = colors.secondary)
                }
            }
        }
    }
}
