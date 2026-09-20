package com.cornerstone.agency.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.MicIcon
import com.cornerstone.agency.ui.components.botAvatarColor
import com.cornerstone.agency.voice.VoicePlate
import com.cornerstone.agency.voice.VoiceSession
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownColor
import com.mikepenz.markdown.m3.markdownTypography
import kotlinx.coroutines.launch

fun botDisplayName(profiles: List<com.cornerstone.agency.data.BotProfile>, name: String): String =
    profiles.find { it.name == name }?.title
        ?: if (name == "default") "Kaori" else name.replaceFirstChar { it.uppercase() }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    vm: AppViewModel,
    profileName: String,
    sessionId: String?,
    onBack: () -> Unit,
    onOpenSessions: () -> Unit,
    onOpenBotSettings: () -> Unit,
    prima: Boolean = false,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val compact = isCompactHeight()
    val ui by vm.ui.collectAsState()

    val chat: com.cornerstone.agency.data.ChatSource = remember(profileName, sessionId, prima) {
        if (prima) com.cornerstone.agency.data.PrimaChatRepository(vm.bridge, vm.primaSocket)
        else ChatRepository(vm.gateway, profileName)
    }
    val socketState by chat.linkState.collectAsState()
    val items by chat.items.collectAsState()
    val title by chat.title.collectAsState()
    val opening by chat.opening.collectAsState()
    val streaming by chat.streaming.collectAsState()
    val statusLine by chat.statusLine.collectAsState()
    val lastError by chat.lastError.collectAsState()

    val botName = botDisplayName(ui.profiles, profileName)
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var menuOpen by remember { mutableStateOf(false) }
    var renameOpen by remember { mutableStateOf(false) }
    var renameText by remember { mutableStateOf("") }
    var draft by remember { mutableStateOf("") }
    // Share-intent handoff: ShareTargetScreen stashes a draft on the VM.
    LaunchedEffect(Unit) {
        vm.consumeShareDraft()?.let { if (it.isNotBlank()) draft = it }
    }

    // Voice plate — created when the sheet opens, torn down when it closes.
    val context = LocalContext.current
    val voiceBridge by vm.voiceBridge.collectAsState()
    val voiceToken by vm.voiceToken.collectAsState()
    var voiceOpen by remember { mutableStateOf(false) }
    var voiceSession by remember { mutableStateOf<VoiceSession?>(null) }
    val micPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted -> if (granted) voiceOpen = true }

    // Resolve the session once the socket is open; re-resume on every reconnect.
    var resolvedOnce by remember { mutableStateOf(false) }
    LaunchedEffect(socketState) {
        if (socketState == GatewayState.OPEN) {
            if (resolvedOnce) {
                chat.refreshAfterReconnect()
            } else {
                chat.open(sessionId)
                // Dispatch composer: auto-send the routed message once the
                // session is open (sessionId must be resolved first).
                vm.consumeDispatch()?.let { chat.send(it) }
            }
            resolvedOnce = true
        }
    }

    // Leaving while a turn is live interrupts it best-effort (Herald behavior).
    DisposableEffect(chat) {
        onDispose { chat.close() }
    }

    // Stick to the newest row while streaming / when new rows land.
    LaunchedEffect(items.size, items.lastOrNull()?.text?.length) {
        if (items.isNotEmpty() && listState.firstVisibleItemIndex <= 1) {
            listState.animateScrollToItem(0)
        }
    }

    Column(Modifier.fillMaxSize().safeDrawingPadding().imePadding()) {
        // Top bar
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = space.sm,
                    vertical = if (compact) 0.dp else space.xs,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack, modifier = if (compact) Modifier.size(36.dp) else Modifier) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack, "Back",
                    tint = colors.foreground,
                    modifier = if (compact) Modifier.size(18.dp) else Modifier,
                )
            }
            BotAvatar(
                name = botName,
                color = if (prima) colors.defaultViolet
                    else botAvatarColor(profileName, profileName == "default"),
                size = if (compact) 22.dp else 30.dp,
            )
            Spacer(Modifier.width(space.sm))
            Column(Modifier.weight(1f)) {
                Text(
                    botName,
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.foreground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (!compact) {
                    Text(
                        title.ifBlank { "New session" },
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.tertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Box {
                IconButton(onClick = { menuOpen = true }) {
                    Icon(Icons.Filled.MoreVert, "Menu", tint = colors.secondary)
                }
                DropdownMenu(
                    expanded = menuOpen,
                    onDismissRequest = { menuOpen = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("Sessions") },
                        onClick = { menuOpen = false; onOpenSessions() },
                    )
                    DropdownMenuItem(
                        text = { Text("New session") },
                        onClick = {
                            menuOpen = false
                            scope.launch { chat.newSession() }
                        },
                    )
                    DropdownMenuItem(
                        text = { Text("Rename") },
                        onClick = {
                            menuOpen = false
                            renameText = title
                            renameOpen = true
                        },
                    )
                    if (!prima) {
                        DropdownMenuItem(
                            text = { Text("Bot settings") },
                            onClick = { menuOpen = false; onOpenBotSettings() },
                        )
                    }
                }
            }
        }

        // Socket status pill
        if (socketState != GatewayState.OPEN) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(colors.sidebar)
                    .padding(vertical = space.xxs),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    when (socketState) {
                        GatewayState.CONNECTING -> "Connecting…"
                        GatewayState.RECONNECTING -> "Reconnecting…"
                        else -> "Disconnected"
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.warm,
                )
            }
        }

        // Messages — reverseLayout puts index 0 (newest) at the bottom.
        Box(Modifier.weight(1f).fillMaxWidth()) {
            if (opening && items.isEmpty()) {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center).size(28.dp),
                    color = colors.gold, strokeWidth = 2.dp,
                )
            }
            LazyColumn(
                state = listState,
                reverseLayout = true,
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = space.md, vertical = space.sm,
                ),
            ) {
                items(items.asReversed(), key = { it.id }) { item ->
                    ChatBubble(item, chat, scope)
                    Spacer(Modifier.height(space.xs))
                }
            }
        }

        // Transient turn status ("(◇) orchestrating…") — under the streaming
        // bubble, above the composer. Cleared on empty text / message.complete.
        if (statusLine.isNotEmpty()) {
            Text(
                statusLine,
                color = colors.tertiary,
                style = MaterialTheme.typography.labelSmall,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(horizontal = space.lg, vertical = space.xxs),
            )
        }

        if (lastError.isNotEmpty()) {
            Text(
                lastError,
                color = colors.destructive,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(horizontal = space.lg, vertical = space.xxs),
            )
        }

        // Composer
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = if (compact) space.sm else space.lg,
                    vertical = if (compact) space.xs else space.sm,
                ),
            verticalAlignment = Alignment.Bottom,
        ) {
            AgencyTextField(
                value = draft,
                onValueChange = { draft = it },
                modifier = Modifier.weight(1f),
                placeholder = "Message $botName",
                singleLine = false,
            )
            Spacer(Modifier.width(space.sm))
            IconButton(
                onClick = {
                    val granted = ContextCompat.checkSelfPermission(
                        context, Manifest.permission.RECORD_AUDIO,
                    ) == PackageManager.PERMISSION_GRANTED
                    if (granted) voiceOpen = true
                    else micPermission.launch(Manifest.permission.RECORD_AUDIO)
                },
            ) {
                Icon(MicIcon, "Voice", tint = colors.tertiary, modifier = Modifier.size(20.dp))
            }
            Box(
                Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(if (streaming) colors.destructive else colors.primary)
                    .clickable {
                        if (streaming) {
                            scope.launch { chat.stop() }
                        } else if (draft.isNotBlank()) {
                            val t = draft
                            draft = ""
                            scope.launch { chat.send(t) }
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    if (streaming) Icons.Filled.Close else Icons.AutoMirrored.Filled.Send,
                    contentDescription = if (streaming) "Stop" else "Send",
                    tint = colors.foreground,
                    modifier = Modifier.size(18.dp),
                )
            }
        }
    }

    if (voiceOpen) {
        // Fresh session per open; transcript flows back into this chat.
        val vs = remember(voiceBridge, voiceToken) {
            val (host, port) = voiceBridge.split(':', limit = 2)
                .let { it[0] to (it.getOrNull(1)?.toIntOrNull() ?: 8766) }
            VoiceSession(
                context = context,
                host = host,
                port = port,
                token = voiceToken,
                onTranscript = { text -> scope.launch { chat.send(text) } },
            ).also { it.start() }
        }
        voiceSession = vs
        ModalBottomSheet(
            onDismissRequest = { voiceOpen = false },
            containerColor = colors.elevated,
        ) {
            VoicePlate(session = vs, onClose = { voiceOpen = false })
        }
        DisposableEffect(vs) {
            onDispose {
                vs.stop()
                voiceSession = null
            }
        }
    }

    if (renameOpen) {
        AlertDialog(
            onDismissRequest = { renameOpen = false },
            title = { Text("Rename session") },
            text = {
                AgencyTextField(
                    value = renameText,
                    onValueChange = { renameText = it },
                    placeholder = "Session title",
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    renameOpen = false
                    scope.launch {
                        runCatching { chat.rename(renameText) }
                    }
                }) { Text("Save") }
            },
            dismissButton = {
                TextButton(onClick = { renameOpen = false }) { Text("Cancel") }
            },
        )
    }
}

@Composable
private fun ChatBubble(
    item: ChatItem,
    chat: com.cornerstone.agency.data.ChatSource,
    scope: kotlinx.coroutines.CoroutineScope,
) {
    val colors = AgencyTokens.colors
    when (item.kind) {
        ChatKind.User -> Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Bubble(colors.primarySoft, Modifier.widthIn(max = 300.dp)) {
                Text(
                    item.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.foreground,
                )
            }
        }

        ChatKind.Assistant -> Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Bubble(colors.card, Modifier.widthIn(max = 300.dp)) {
                if (item.text.isEmpty() && item.streaming) {
                    Text("▌", color = colors.tertiary, style = MaterialTheme.typography.bodyMedium)
                } else {
                    Markdown(
                        content = item.text + if (item.streaming) " ▌" else "",
                        colors = markdownColor(
                            text = colors.foreground,
                            codeBackground = colors.elevated,
                            inlineCodeBackground = colors.elevated,
                            dividerColor = colors.stroke,
                        ),
                        typography = markdownTypography(
                            text = MaterialTheme.typography.bodyMedium,
                            paragraph = MaterialTheme.typography.bodyMedium,
                            ordered = MaterialTheme.typography.bodyMedium,
                            bullet = MaterialTheme.typography.bodyMedium,
                            list = MaterialTheme.typography.bodyMedium,
                        ),
                        modifier = Modifier,
                    )
                }
            }
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

        ChatKind.Status -> Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                item.text,
                style = MaterialTheme.typography.labelSmall,
                color = colors.tertiary,
            )
        }

        ChatKind.Error -> Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Text(
                item.text,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.destructive,
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

@Composable
private fun Bubble(color: androidx.compose.ui.graphics.Color, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier
            .clip(RoundedCornerShape(18.dp))
            .background(color)
            .padding(horizontal = 14.dp, vertical = 10.dp),
    ) {
        content()
    }
}

@Composable
private fun ThinkingBubble(item: ChatItem) {
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
