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
import com.cornerstone.agency.ui.components.BotAvatar
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

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
    var draftFocused by remember { mutableStateOf(false) }

    // Grok-style quick model switcher (hidden for Prima, which has no models).
    val profile = ui.profiles.find { it.name == profileName }
    var modelSheet by remember { mutableStateOf(false) }
    var providers by remember { mutableStateOf<List<com.cornerstone.agency.data.ModelProvider>>(emptyList()) }
    var providersLoading by remember { mutableStateOf(false) }
    var modelBusy by remember { mutableStateOf(false) }
    fun openModelSheet() {
        modelSheet = true
        if (providers.isEmpty() && !providersLoading) {
            providersLoading = true
            scope.launch {
                providers = runCatching { vm.repo.modelOptions() }.getOrDefault(emptyList())
                providersLoading = false
            }
        }
    }
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
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = colors.foreground)
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
            IconButton(onClick = { scope.launch { chat.newSession() } }) {
                Icon(Icons.Filled.Add, "New chat", tint = colors.foreground)
            }
            Box {
                IconButton(onClick = { menuOpen = true }) {
                    Icon(Icons.Filled.MoreVert, "Menu", tint = colors.foreground)
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
            if (!opening && items.isEmpty()) {
                EmptyChat(
                    botName = botName,
                    avatarColor = if (prima) colors.defaultViolet
                        else botAvatarColor(profileName, profileName == "default"),
                    onPick = { text -> scope.launch { chat.send(text) } },
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            val rows = remember(items, streaming) { buildChatRows(items, streaming) }
            LazyColumn(
                state = listState,
                reverseLayout = true,
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = space.md, vertical = space.sm,
                ),
            ) {
                items(rows.asReversed(), key = { it.key }) { row ->
                    when (row) {
                        is ChatRow.Single -> ChatBubble(row.item, chat, scope)
                        is ChatRow.Work -> WorkGroup(row)
                    }
                    Spacer(Modifier.height(space.sm))
                }
            }
            Box(
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(18.dp)
                    .background(Brush.verticalGradient(listOf(colors.chrome, Color.Transparent))),
            )
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

        // Model pill — Grok-style quick switcher above the composer.
        if (!prima) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (compact) space.sm else space.lg),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .border(1.dp, colors.stroke, RoundedCornerShape(999.dp))
                        .background(colors.card)
                        .clickable { openModelSheet() }
                        .heightIn(min = 48.dp)
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(colors.gold),
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        shortModelLabel(profile?.model ?: ""),
                        style = MaterialTheme.typography.labelMedium,
                        color = colors.foreground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(max = 200.dp),
                    )
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Change model",
                        tint = colors.tertiary,
                        modifier = Modifier.size(16.dp),
                    )
                }
            }
            Spacer(Modifier.height(space.xs))
        }

        // Composer - one tall pill; the trailing button is mic when empty, send when
        // there is text, stop while a turn streams (Grok-style).
        val hasText = draft.isNotBlank()
        val pill = RoundedCornerShape(28.dp)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = if (compact) space.sm else space.lg,
                    vertical = space.sm,
                )
                .heightIn(min = 56.dp)
                .clip(pill)
                .border(1.dp, if (draftFocused) colors.gold else colors.stroke, pill)
                .background(colors.card)
                .padding(start = space.lg, end = 4.dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(Modifier.weight(1f).padding(vertical = space.sm)) {
                BasicTextField(
                    value = draft,
                    onValueChange = { draft = it },
                    modifier = Modifier.fillMaxWidth().onFocusChanged { draftFocused = it.isFocused },
                    maxLines = 6,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = colors.foreground),
                    cursorBrush = SolidColor(colors.gold),
                    decorationBox = { inner ->
                        Box {
                            if (draft.isEmpty()) {
                                Text(
                                    "Message $botName",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = colors.tertiary,
                                )
                            }
                            inner()
                        }
                    },
                )
            }
            Box(
                Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (streaming) colors.destructive else colors.primary)
                    .clickable {
                        when {
                            streaming -> scope.launch { chat.stop() }
                            hasText -> {
                                val t = draft
                                draft = ""
                                scope.launch { chat.send(t) }
                            }
                            else -> {
                                val granted = ContextCompat.checkSelfPermission(
                                    context, Manifest.permission.RECORD_AUDIO,
                                ) == PackageManager.PERMISSION_GRANTED
                                if (granted) voiceOpen = true
                                else micPermission.launch(Manifest.permission.RECORD_AUDIO)
                            }
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    when {
                        streaming -> Icons.Filled.Close
                        hasText -> Icons.AutoMirrored.Filled.Send
                        else -> MicIcon
                    },
                    contentDescription = when {
                        streaming -> "Stop"
                        hasText -> "Send"
                        else -> "Voice"
                    },
                    tint = if (streaming) colors.foreground else colors.onPrimary,
                    modifier = Modifier.size(22.dp),
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
            VoicePlate(session = vs, onClose = { voiceOpen = false }, botName = botName)
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

    if (modelSheet) {
        ModelPickerSheet(
            providers = providers,
            currentProvider = profile?.provider ?: "",
            currentModel = profile?.model ?: "",
            loading = providersLoading || modelBusy,
            onPick = { slug, model ->
                modelBusy = true
                scope.launch {
                    try {
                        vm.repo.updateModel(profileName, slug, model)
                        vm.refreshProfiles()
                        modelSheet = false
                    } catch (e: Exception) {
                        // Keep the sheet open so the user can retry.
                    } finally {
                        modelBusy = false
                    }
                }
            },
            onDismiss = { modelSheet = false },
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
        ChatKind.User -> {
            var exp by remember(item.id) { mutableStateOf(false) }
            var overflow by remember(item.id) { mutableStateOf(false) }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Bubble(
                    colors.primarySoft,
                    Modifier.widthIn(max = 340.dp).copyOnLongPress(item.text),
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
            }
        }

        // Assistant text is full width and borderless; only the user keeps a bubble.
        ChatKind.Assistant -> Column(
            Modifier.fillMaxWidth().copyOnLongPress(item.text).padding(vertical = 2.dp),
        ) {
            if (item.text.isEmpty() && item.streaming) {
                Text("▌", color = colors.gold, style = MaterialTheme.typography.bodyLarge)
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
                        text = MaterialTheme.typography.bodyLarge,
                        paragraph = MaterialTheme.typography.bodyLarge,
                        ordered = MaterialTheme.typography.bodyLarge,
                        bullet = MaterialTheme.typography.bodyLarge,
                        list = MaterialTheme.typography.bodyLarge,
                    ),
                    modifier = Modifier,
                )
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
            .border(1.dp, AgencyTokens.colors.edge, RoundedCornerShape(18.dp))
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


// ---- grouped "work" rows --------------------------------------------------

private sealed interface ChatRow {
    val key: String
    data class Single(val item: ChatItem) : ChatRow { override val key: String get() = item.id }
    data class Work(val items: List<ChatItem>, val live: Boolean) : ChatRow {
        override val key: String get() = "work-" + items.first().id
    }
}

/** Consecutive thinking + tool items collapse into one quiet chip per turn. */
private fun buildChatRows(items: List<ChatItem>, streaming: Boolean): List<ChatRow> {
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
private fun WorkGroup(g: ChatRow.Work) {
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

@Composable
private fun EmptyChat(
    botName: String,
    avatarColor: androidx.compose.ui.graphics.Color,
    onPick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AgencyTokens.colors
    val suggestions = listOf(
        "What's on my plate today?",
        "Catch me up on what you've been working on",
        "What can you help me with right now?",
    )
    Column(
        modifier.padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BotAvatar(name = botName, color = avatarColor, size = 72.dp)
        Spacer(Modifier.height(16.dp))
        Text(botName, style = MaterialTheme.typography.displaySmall)
        Spacer(Modifier.height(4.dp))
        Text("What's on your mind?", style = MaterialTheme.typography.bodyLarge, color = colors.secondary)
        Spacer(Modifier.height(24.dp))
        suggestions.forEach { text ->
            Box(
                Modifier
                    .padding(vertical = 4.dp)
                    .widthIn(max = 360.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .border(1.dp, colors.stroke, RoundedCornerShape(24.dp))
                    .background(colors.card)
                    .clickable { onPick(text) }
                    .heightIn(min = 48.dp)
                    .padding(horizontal = 18.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text, style = MaterialTheme.typography.bodyMedium,
                    color = colors.foreground, textAlign = TextAlign.Center,
                )
            }
        }
    }
}

/** Long-press copies the message text. */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Modifier.copyOnLongPress(text: String): Modifier {
    val clip = LocalClipboardManager.current
    val haptic = LocalHapticFeedback.current
    val ctx = LocalContext.current
    return this.combinedClickable(onClick = {}, onLongClick = {
        clip.setText(AnnotatedString(text))
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        Toast.makeText(ctx, "Copied", Toast.LENGTH_SHORT).show()
    })
}
