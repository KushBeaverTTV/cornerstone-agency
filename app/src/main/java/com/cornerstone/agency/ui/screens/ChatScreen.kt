package com.cornerstone.agency.ui.screens

import androidx.compose.ui.focus.focusRequester
import androidx.compose.runtime.SideEffect
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
    /** Open another stored session of this bot (used by "Branch into new chat"). */
    onOpenSession: (String) -> Unit = {},
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
    val actionsApi = chat as? com.cornerstone.agency.data.MessageActions
    val hermesChat = chat as? ChatRepository

    // On-device copy first, so the chat shows instantly (and offline) before the socket opens.
    LaunchedEffect(chat) { hermesChat?.preload(sessionId) }

    val botName = botDisplayName(ui.profiles, profileName)
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var menuOpen by remember { mutableStateOf(false) }
    var renameOpen by remember { mutableStateOf(false) }
    var renameText by remember { mutableStateOf("") }
    var draft by remember { mutableStateOf("") }
    var draftFocused by remember { mutableStateOf(false) }

    // Composer attachments (photos / camera / files) — staged until Send.
    val pending = remember { androidx.compose.runtime.mutableStateListOf<PendingAttachment>() }
    var attachMenu by remember { mutableStateOf(false) }
    var cameraUri by androidx.compose.runtime.saveable.rememberSaveable { mutableStateOf<String?>(null) }

    // Message actions + in-chat search.
    var actionTarget by remember { mutableStateOf<ChatItem?>(null) }
    var selectText by remember { mutableStateOf<String?>(null) }
    var editTarget by remember { mutableStateOf<ChatItem?>(null) }
    var editText by remember { mutableStateOf("") }
    var searchOpen by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    var matchPos by remember { mutableStateOf(0) }
    var searchCount by remember { mutableStateOf(0) }

    // Grok-style quick model switcher. Hermes bots read/write the profile's model; Prima reads
    // and writes its own worker's model through the bridge. Same chip, same picker sheet.
    val profile = ui.profiles.find { it.name == profileName }
    val primaChat = chat as? com.cornerstone.agency.data.PrimaChatRepository
    val primaModel by (primaChat?.model
        ?: remember { kotlinx.coroutines.flow.MutableStateFlow<Pair<String, String>?>(null) })
        .collectAsState()
    val currentProvider = if (prima) primaModel?.first.orEmpty() else profile?.provider.orEmpty()
    val currentModel = if (prima) primaModel?.second.orEmpty() else profile?.model.orEmpty()
    var modelSheet by remember { mutableStateOf(false) }
    var providers by remember { mutableStateOf<List<com.cornerstone.agency.data.ModelProvider>>(emptyList()) }
    var providersLoading by remember { mutableStateOf(false) }
    var modelBusy by remember { mutableStateOf(false) }
    var modelError by remember { mutableStateOf("") }
    fun openModelSheet() {
        modelSheet = true
        if (providers.isEmpty() && !providersLoading) {
            providersLoading = true
            scope.launch {
                providers = runCatching {
                    primaChat?.modelOptions() ?: vm.repo.modelOptions()
                }.getOrDefault(emptyList())
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

    fun stage(uris: List<android.net.Uri>) {
        if (uris.isEmpty()) return
        scope.launch {
            for (u in uris.take(10 - pending.size)) {
                val (att, err) = readAttachment(context, u)
                if (att != null) pending += att
                if (err != null) Toast.makeText(context, err, Toast.LENGTH_SHORT).show()
            }
        }
    }
    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(10),
    ) { stage(it) }
    val pickFiles = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenMultipleDocuments(),
    ) { stage(it) }
    val takePicture = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture(),
    ) { ok -> cameraUri?.let { if (ok) stage(listOf(android.net.Uri.parse(it))) }; cameraUri = null }
    fun launchCamera() {
        val dir = java.io.File(context.cacheDir, "camera").apply { mkdirs() }
        // Old captures are only needed until sent; keep the folder from growing.
        dir.listFiles()?.filter { System.currentTimeMillis() - it.lastModified() > 86_400_000L }?.forEach { it.delete() }
        val file = java.io.File(dir, "photo-${System.currentTimeMillis()}.jpg")
        val uri = androidx.core.content.FileProvider.getUriForFile(context, "${context.packageName}.files", file)
        cameraUri = uri.toString()
        takePicture.launch(uri)
    }
    fun sendDraft() {
        val t = draft
        val atts = pending.map { it.upload }
        if (t.isBlank() && atts.isEmpty()) return
        draft = ""
        pending.clear()
        scope.launch { chat.send(t, atts) }
    }
    fun retryLast() {
        val last = items.lastOrNull { it.kind == ChatKind.User && it.text.isNotBlank() } ?: return
        scope.launch {
            val redone = if (actionsApi != null && last.rowId != null) actionsApi.editAndResend(last.id, last.text) else false
            if (!redone) chat.send(last.text)
        }
    }

    // Widget "talk" button: open straight into the voice card.
    LaunchedEffect(Unit) {
        if (vm.consumeVoiceRequest()) {
            val granted = ContextCompat.checkSelfPermission(
                context, Manifest.permission.RECORD_AUDIO,
            ) == PackageManager.PERMISSION_GRANTED
            if (granted) voiceOpen = true else micPermission.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

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
            val agent = remember(items, streaming, socketState) {
                agentActivity(items, streaming, socketState != GatewayState.OPEN)
            }
            // The keystone is the avatar; its motion is the status (Muse/Grok Bot).
            BotMark(
                color = if (prima) colors.defaultViolet
                    else botAvatarColor(profileName, profileName == "default"),
                size = if (compact) 32.dp else 40.dp,
                state = when (agent.state) {
                    AgentState.WORKING -> KeystoneState.WORKING
                    AgentState.WAITING -> KeystoneState.WAITING
                    AgentState.OFFLINE -> KeystoneState.ERROR
                    AgentState.IDLE -> KeystoneState.IDLE
                },
                contentDescription = botName,
            )
            Spacer(Modifier.width(space.md))
            Column(Modifier.weight(1f)) {
                Text(
                    botName,
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.foreground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (!compact) {
                    val line = agent.label
                        ?: title.let { if (com.cornerstone.agency.ui.components.looksMachineTitle(it)) it.replace('_', ' ') else it }
                            .ifBlank { "New session" }
                    Text(
                        line,
                        style = MaterialTheme.typography.labelMedium,
                        color = when (agent.state) {
                            AgentState.WORKING -> colors.gold
                            AgentState.WAITING -> colors.warm
                            AgentState.OFFLINE -> colors.destructive
                            AgentState.IDLE -> colors.tertiary
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite },
                    )
                }
            }
            IconButton(onClick = { searchOpen = !searchOpen; if (!searchOpen) query = "" }) {
                Icon(com.cornerstone.agency.ui.components.AgencyIcons.Search, "Search in chat", tint = colors.foreground)
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
                    // "+" in the header already starts a new session; Rename only makes sense once
                    // there is a conversation to name.
                    if (items.isNotEmpty()) DropdownMenuItem(
                        text = { Text("Rename") },
                        onClick = {
                            menuOpen = false
                            renameText = title
                            renameOpen = true
                        },
                    )
                    DropdownMenuItem(
                        text = { Text("Model · ${shortModelLabel(currentModel)}") },
                        onClick = { menuOpen = false; openModelSheet() },
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

        if (searchOpen) {
            ChatSearchBar(
                query = query,
                onQuery = { query = it; matchPos = 0 },
                count = if (query.isBlank()) 0 else searchCount,
                position = matchPos,
                onOlder = { if (matchPos + 1 < searchCount) matchPos++ },
                onNewer = { if (matchPos > 0) matchPos-- },
                onClose = { searchOpen = false; query = "" },
            )
        }

        val cached by (hermesChat?.fromCache
            ?: remember { kotlinx.coroutines.flow.MutableStateFlow(false) }).collectAsState()

        // Socket status pill
        if (socketState != GatewayState.OPEN || (cached && items.isNotEmpty())) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(colors.sidebar)
                    .padding(vertical = space.xxs),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    when (socketState) {
                        GatewayState.OPEN -> "Syncing with the PC…"
                        GatewayState.CONNECTING -> "Connecting…"
                        GatewayState.RECONNECTING -> "Reconnecting…"
                        else -> "Disconnected"
                    } + if (cached && items.isNotEmpty() && socketState != GatewayState.OPEN) " · showing saved copy" else "",
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
                    tagline = when {
                        prima -> "Prime Agent on this PC"
                        else -> profile?.subtitle?.substringBefore(" — ")?.trim().orEmpty()
                    },
                    isDefault = !prima && profileName == "default",
                    avatarColor = if (prima) colors.defaultViolet
                        else botAvatarColor(profileName, profileName == "default"),
                    onPick = { text -> scope.launch { chat.send(text) } },
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            val rows = remember(items, streaming) { buildChatRows(items, streaming) }
            val shown = rows.asReversed()
            val matches = remember(shown, query) {
                if (query.isBlank()) emptyList() else shown.mapIndexedNotNull { i, r ->
                    val hit = (r as? ChatRow.Single)?.item?.takeIf { it.text.contains(query.trim(), ignoreCase = true) }
                    if (hit != null) i to hit.id else null
                }
            }
            val currentHit = matches.getOrNull(matchPos)?.second
            LaunchedEffect(matches, matchPos) {
                matches.getOrNull(matchPos)?.let { listState.animateScrollToItem(it.first) }
            }
            if (searchOpen) {
                // Hand the numbers to the search bar (it lives in the header).
                SideEffect { searchCount = matches.size }
            }
            LazyColumn(
                state = listState,
                reverseLayout = true,
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = space.md, vertical = space.sm,
                ),
            ) {
                items(shown, key = { it.key }) { row ->
                    Column(Modifier.animateItem()) {
                        when (row) {
                            is ChatRow.Single -> ChatBubble(
                                row.item, chat, scope,
                                onLongPress = { actionTarget = it },
                                onRetry = { retryLast() },
                                onReact = { it2, e -> scope.launch { actionsApi?.react(it2.id, e) } },
                                highlighted = row.item.id == currentHit,
                            )
                            is ChatRow.Work -> WorkGroup(row)
                        }
                        Spacer(Modifier.height(space.sm))
                    }
                }
            }
            Box(
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(18.dp)
                    .background(Brush.verticalGradient(listOf(colors.chrome, Color.Transparent))),
            )
            val showJump by remember { derivedStateOf { listState.firstVisibleItemIndex > 2 } }
            androidx.compose.animation.AnimatedVisibility(
                visible = showJump,
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = space.md),
                enter = androidx.compose.animation.fadeIn() + androidx.compose.animation.scaleIn(),
                exit = androidx.compose.animation.fadeOut() + androidx.compose.animation.scaleOut(),
            ) {
                Box(
                    Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(colors.elevated)
                        .border(1.dp, colors.stroke, CircleShape)
                        .clickable(role = androidx.compose.ui.semantics.Role.Button, onClickLabel = "Jump to latest message") {
                            scope.launch { listState.animateScrollToItem(0) }
                        }
                        .semantics(mergeDescendants = true) { contentDescription = "Jump to latest" },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Filled.KeyboardArrowDown, "Jump to latest", tint = colors.foreground)
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

        // Composer (Grok): one rounded surface. Text on top; below it the model chip on the
        // left and a single action on the right — mic when empty, send with text, stop while
        // a turn streams. On the cover display it collapses to one row (model lives in the menu).
        val hasText = draft.isNotBlank() || pending.isNotEmpty()
        val shape = RoundedCornerShape(if (compact) 28.dp else 26.dp)
        val action: @Composable () -> Unit = {
            Box(
                Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            streaming -> colors.destructive
                            hasText -> colors.primary
                            else -> colors.elevated
                        },
                    )
                    .clickable(role = androidx.compose.ui.semantics.Role.Button) {
                        when {
                            streaming -> scope.launch { chat.stop() }
                            hasText -> sendDraft()
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
                    tint = when {
                        streaming -> colors.foreground
                        hasText -> colors.onPrimary
                        else -> colors.foreground
                    },
                    modifier = Modifier.size(22.dp),
                )
            }
        }
        val field: @Composable (Modifier) -> Unit = { m ->
            BasicTextField(
                value = draft,
                onValueChange = { draft = it },
                modifier = m.onFocusChanged { draftFocused = it.isFocused },
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
        val attach: @Composable () -> Unit = {
            if (chat.acceptsAttachments) Box {
                IconButton(onClick = { attachMenu = true }) {
                    Icon(com.cornerstone.agency.ui.components.AgencyIcons.Attach, "Attach", tint = colors.secondary)
                }
                DropdownMenu(expanded = attachMenu, onDismissRequest = { attachMenu = false }) {
                    DropdownMenuItem(
                        text = { Text("Photos") },
                        leadingIcon = { Icon(com.cornerstone.agency.ui.components.AgencyIcons.Photo, null) },
                        onClick = {
                            attachMenu = false
                            pickMedia.launch(androidx.activity.result.PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                    )
                    DropdownMenuItem(
                        text = { Text("Camera") },
                        leadingIcon = { Icon(com.cornerstone.agency.ui.components.AgencyIcons.Camera, null) },
                        onClick = { attachMenu = false; runCatching { launchCamera() } },
                    )
                    DropdownMenuItem(
                        text = { Text("Files") },
                        leadingIcon = { Icon(com.cornerstone.agency.ui.components.AgencyIcons.Files, null) },
                        onClick = { attachMenu = false; pickFiles.launch(arrayOf("*/*")) },
                    )
                }
            }
        }
        val frame = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (compact) space.sm else space.lg, vertical = space.sm)
            .clip(shape)
            .border(1.dp, if (draftFocused) colors.gold else colors.stroke, shape)
            .background(colors.surface)
        if (compact) {
            if (pending.isNotEmpty()) Box(Modifier.padding(horizontal = space.sm)) {
                PendingAttachmentStrip(pending) { pending.remove(it) }
            }
            Row(
                frame.heightIn(min = 56.dp).padding(start = space.lg, end = 4.dp, top = 4.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                attach()
                Box(Modifier.weight(1f).padding(vertical = space.sm)) { field(Modifier.fillMaxWidth()) }
                action()
            }
        } else {
            Column(frame.padding(start = space.lg, end = 6.dp, top = space.md, bottom = 6.dp)) {
                if (pending.isNotEmpty()) PendingAttachmentStrip(pending) { pending.remove(it) }
                field(Modifier.fillMaxWidth().padding(end = space.sm))
                Spacer(Modifier.height(space.sm))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    attach()
                    Row(
                        Modifier
                            .clip(CircleShape)
                            .clickable(role = androidx.compose.ui.semantics.Role.Button, onClickLabel = "Change model") { openModelSheet() }
                            .heightIn(min = 40.dp)
                            .padding(horizontal = space.sm),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(Modifier.size(6.dp).clip(CircleShape).background(colors.gold))
                        Spacer(Modifier.width(8.dp))
                        Text(
                            shortModelLabel(currentModel),
                            style = MaterialTheme.typography.labelMedium,
                            color = colors.secondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(max = 180.dp),
                        )
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            tint = colors.tertiary,
                            modifier = Modifier.size(16.dp),
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    action()
                }
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

    actionTarget?.let { target ->
        val busy = streaming
        MessageActionSheet(
            item = target,
            actions = actionsFor(target, actionsApi?.canAddress(target) == true, actionsApi != null, busy),
            onDismiss = { actionTarget = null },
            onReact = { e -> actionTarget = null; scope.launch { actionsApi?.react(target.id, e) } },
            onSelectText = { actionTarget = null; selectText = target.text },
            onQuote = {
                actionTarget = null
                val quoted = target.text.lines().take(6).joinToString("\n") { "> $it" }
                draft = quoted + "\n\n" + draft
            },
            onEdit = { actionTarget = null; editText = target.text; editTarget = target },
            onRegenerate = { actionTarget = null; scope.launch { actionsApi?.regenerate(target.id) } },
            onBranch = {
                actionTarget = null
                scope.launch {
                    val id = actionsApi?.branchFrom(target.id)
                    if (id != null) onOpenSession(id)
                    else Toast.makeText(context, "Couldn't branch this chat", Toast.LENGTH_SHORT).show()
                }
            },
            onResend = { actionTarget = null; scope.launch { chat.send(target.text) } },
        )
    }
    selectText?.let { SelectTextDialog(it) { selectText = null } }
    editTarget?.let { target ->
        AlertDialog(
            onDismissRequest = { editTarget = null },
            title = { Text("Edit & resend") },
            text = {
                Column {
                    Text(
                        "Replies after this message are replaced.",
                        style = MaterialTheme.typography.bodySmall, color = colors.secondary,
                    )
                    Spacer(Modifier.height(space.sm))
                    AgencyTextField(value = editText, onValueChange = { editText = it }, placeholder = "Message")
                }
            },
            confirmButton = {
                TextButton(enabled = editText.isNotBlank(), onClick = {
                    editTarget = null
                    scope.launch {
                        if (actionsApi?.editAndResend(target.id, editText) != true) {
                            Toast.makeText(context, "Can't edit while a reply is running", Toast.LENGTH_SHORT).show()
                        }
                    }
                }) { Text("Send") }
            },
            dismissButton = { TextButton(onClick = { editTarget = null }) { Text("Cancel") } },
        )
    }

    if (modelSheet) {
        ModelPickerSheet(
            providers = providers,
            currentProvider = currentProvider,
            currentModel = currentModel,
            loading = providersLoading || modelBusy,
            note = if (prima) "Switches this chat now and becomes Prima's default for new chats."
                else "Applies to $botName everywhere — desktop, Telegram and this phone.",
            error = modelError,
            onPick = { slug, model ->
                modelBusy = true
                modelError = ""
                scope.launch {
                    try {
                        if (primaChat != null) {
                            primaChat.setModel(slug, model)
                        } else {
                            vm.repo.updateModel(profileName, slug, model)
                            vm.refreshProfiles()
                        }
                        modelSheet = false
                    } catch (e: Exception) {
                        // Keep the sheet open so the user can retry.
                        modelError = "Couldn't switch: ${e.message ?: "error"}"
                    } finally {
                        modelBusy = false
                    }
                }
            },
            onDismiss = { modelSheet = false; modelError = "" },
        )
    }
}

@Composable
private fun EmptyChat(
    botName: String,
    tagline: String,
    isDefault: Boolean,
    avatarColor: androidx.compose.ui.graphics.Color,
    onPick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AgencyTokens.colors
    // The default bot runs your day; a specialist gets asked about its own lane.
    val suggestions = if (isDefault) listOf(
        "What's on my plate today?",
        "Catch me up on what you've been working on",
        "What can you help me with right now?",
    ) else listOf(
        "Where did we leave off?",
        "What should we tackle first?",
        "What could you take off my plate?",
    )
    val compact = isCompactHeight()
    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(androidx.compose.foundation.rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        com.cornerstone.agency.ui.components.KeystoneMark(com.cornerstone.agency.ui.components.KeystoneState.IDLE, size = if (compact) 72.dp else 112.dp, color = avatarColor, modifier = Modifier.semantics { contentDescription = "$botName" })
        Spacer(Modifier.height(16.dp))
        Text(botName, style = MaterialTheme.typography.displaySmall)
        Spacer(Modifier.height(4.dp))
        Text(
            tagline.ifBlank { "What's on your mind?" },
            style = MaterialTheme.typography.bodyLarge, color = colors.secondary,
            textAlign = TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis,
        )
        Spacer(Modifier.height(24.dp))
        suggestions.take(if (compact) 2 else 3).forEach { text ->
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


// ---- agent status (header) -------------------------------------------------

private enum class AgentState { IDLE, WORKING, WAITING, OFFLINE }

private data class AgentActivity(val state: AgentState, val label: String?)

/** One-line "what is it doing" derived from the transcript, Muse/Grok-Bot style. */
private fun agentActivity(items: List<ChatItem>, streaming: Boolean, offline: Boolean): AgentActivity {
    if (offline) return AgentActivity(AgentState.OFFLINE, "Reconnecting…")
    if (items.any { it.kind == ChatKind.Request && it.request?.state == com.cornerstone.agency.data.RequestState.Open }) {
        return AgentActivity(AgentState.WAITING, "Waiting for you")
    }
    if (!streaming) return AgentActivity(AgentState.IDLE, null)
    val tool = items.lastOrNull { it.kind == ChatKind.Tool && !it.done }
    if (tool != null) {
        val name = tool.text.removePrefix("▶").trim().take(28)
        return AgentActivity(AgentState.WORKING, "Using $name")
    }
    val last = items.lastOrNull()
    return AgentActivity(
        AgentState.WORKING,
        if (last?.kind == ChatKind.Thinking) "Thinking…" else "Replying…",
    )
}


/** In-chat find: matches run newest → oldest; arrows step through them. */
@Composable
private fun ChatSearchBar(
    query: String,
    onQuery: (String) -> Unit,
    count: Int,
    position: Int,
    onOlder: () -> Unit,
    onNewer: () -> Unit,
    onClose: () -> Unit,
) {
    val colors = AgencyTokens.colors
    val focus = remember { androidx.compose.ui.focus.FocusRequester() }
    LaunchedEffect(Unit) { runCatching { focus.requestFocus() } }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(colors.surface)
            .border(1.dp, colors.stroke, RoundedCornerShape(24.dp))
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQuery,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = colors.foreground),
            cursorBrush = SolidColor(colors.gold),
            modifier = Modifier.weight(1f).focusRequester(focus).semantics { contentDescription = "Search messages" },
            decorationBox = { inner ->
                Box {
                    if (query.isEmpty()) Text("Search this chat", style = MaterialTheme.typography.bodyLarge, color = colors.tertiary)
                    inner()
                }
            },
        )
        if (query.isNotBlank()) {
            Text(
                if (count == 0) "No matches" else "${position + 1}/$count",
                style = MaterialTheme.typography.labelMedium,
                color = colors.secondary,
                modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite },
            )
        }
        IconButton(onClick = onOlder, enabled = position + 1 < count) {
            Icon(com.cornerstone.agency.ui.components.AgencyIcons.ArrowUp, "Older match", tint = colors.foreground)
        }
        IconButton(onClick = onNewer, enabled = position > 0) {
            Icon(Icons.Filled.KeyboardArrowDown, "Newer match", tint = colors.foreground)
        }
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, "Close search", tint = colors.foreground)
        }
    }
}
