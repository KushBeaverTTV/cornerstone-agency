package com.cornerstone.agency.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.cornerstone.agency.data.BotProfile
import com.cornerstone.agency.data.GatewayState
import com.cornerstone.agency.data.PendingRequests
import com.cornerstone.agency.data.PrimaSession
import com.cornerstone.agency.data.RequestKind
import com.cornerstone.agency.data.SessionSummary
import com.cornerstone.agency.data.listProfileSessions
import com.cornerstone.agency.service.NotificationCenter
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.BotMark
import com.cornerstone.agency.ui.components.KeystoneState
import com.cornerstone.agency.ui.components.MicIcon
import com.cornerstone.agency.ui.components.PageHeader
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.botAvatarColor
import com.cornerstone.agency.ui.components.cleanPreview
import com.cornerstone.agency.ui.components.relativeTime
import com.cornerstone.agency.ui.components.sessionLine
import com.cornerstone.agency.ui.isCompactHeight
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

/** Something you can talk to from the home dock. */
private sealed interface Target {
    val title: String
    val color: Color
    data class Bot(val profile: BotProfile, override val color: Color) : Target {
        override val title: String get() = profile.title
    }
    data class Desktop(override val color: Color) : Target {
        override val title: String get() = "Prima"
    }
}

/**
 * Home. One row per agent (Grok Bot / Muse): living keystone avatar, name, last line, time.
 * Requests that need you sit on top as answerable cards. A docked composer at the bottom
 * sends to the chosen bot — or lets Laya route it when the desktop bridge is up.
 * History lives behind each bot (chat menu → Sessions), not on the home screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen(
    vm: AppViewModel,
    onOpenSession: (profile: String, sessionId: String) -> Unit,
    onOpenChat: (profile: String) -> Unit,
    onOpenBot: (profile: String) -> Unit,
    onNewAgent: () -> Unit,
    onOpenPrima: () -> Unit,
    onOpenPrimaSession: (sessionId: String?) -> Unit,
) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val shapes = AgencyTokens.shapes
    val socketState by vm.gateway.state.collectAsState()
    val openSessions by PendingRequests.openSessions.collectAsState()
    val openProfiles by PendingRequests.openProfiles.collectAsState()
    val inbox by NotificationCenter.inbox.collectAsState()
    val bridgeHealth by vm.bridgeHealth.collectAsState()
    val scope = rememberCoroutineScope()
    val compact = isCompactHeight()
    val context = LocalContext.current

    val bridgeUp = bridgeHealth?.ok == true
    val primaUp = bridgeUp && bridgeHealth?.prima?.available == true

    // Sessions per bot (newest first), plus desktop Prima's latest.
    var sessions by remember { mutableStateOf<Map<String, List<SessionSummary>>>(emptyMap()) }
    val latest: Map<String, SessionSummary> = remember(sessions) {
        sessions.mapNotNull { (k, v) -> v.firstOrNull()?.let { k to it } }.toMap()
    }
    var primaLatest by remember { mutableStateOf<PrimaSession?>(null) }
    var loading by remember { mutableStateOf(true) }

    fun refresh() {
        val profiles = vm.ui.value.profiles
        if (profiles.isEmpty() || socketState != GatewayState.OPEN) return
        scope.launch {
            val out = HashMap<String, List<SessionSummary>>()
            for (p in profiles) {
                out[p.name] = runCatching { listProfileSessions(vm.gateway, p.name) }
                    .getOrDefault(emptyList())
                    .sortedByDescending { it.startedAt }
            }
            sessions = out
            loading = false
        }
    }

    // Recent: a short, quiet list of real conversations (no cron chatter), across every bot.
    val recent: List<Pair<BotProfile, SessionSummary>> = remember(sessions, ui.profiles) {
        ui.profiles.flatMap { p -> sessions[p.name].orEmpty().map { p to it } }
            .filterNot { (_, s) -> cleanPreview(s.preview) == "Scheduled job" }
            .sortedByDescending { it.second.startedAt }
            .take(6)
    }

    LaunchedEffect(socketState, ui.profiles, bridgeHealth) {
        if (socketState == GatewayState.OPEN && ui.profiles.isNotEmpty()) refresh()
        primaLatest = if (primaUp) {
            runCatching { vm.bridge.primaSessions() }.getOrDefault(emptyList())
                .maxByOrNull { it.last_activity }
        } else null
    }
    LaunchedEffect(Unit) {
        vm.gateway.events.collect { ev -> if (ev.type == "sessions.changed") refresh() }
    }
    LaunchedEffect(Unit) { vm.refreshBridge() }

    // ---- dock composer -------------------------------------------------------
    val defaultBot = ui.profiles.firstOrNull { it.isDefault || it.name == "default" }
        ?: ui.profiles.firstOrNull()
    val targets: List<Target> = remember(ui.profiles, primaUp) {
        ui.profiles.map { Target.Bot(it, botAvatarColor(it.name, it.isDefault)) } +
            if (primaUp) listOf(Target.Desktop(colors.defaultViolet)) else emptyList()
    }
    var chosen by remember { mutableStateOf<Target?>(null) }
    val target: Target? = chosen ?: defaultBot?.let { Target.Bot(it, botAvatarColor(it.name, it.isDefault)) }
    var draft by remember { mutableStateOf("") }
    var focused by remember { mutableStateOf(false) }
    var routing by remember { mutableStateOf(false) }
    var pickerOpen by remember { mutableStateOf(false) }
    var pendingSend by remember { mutableStateOf<String?>(null) }

    fun go(t: Target, text: String?) {
        if (text != null) vm.setDispatchText(text)
        when (t) {
            is Target.Desktop -> onOpenPrimaSession(primaLatest?.id)
            is Target.Bot -> {
                val sid = latest[t.profile.name]?.id
                if (sid != null) onOpenSession(t.profile.name, sid) else onOpenChat(t.profile.name)
            }
        }
    }

    fun send() {
        val text = draft.trim()
        val t = target ?: return
        if (text.isEmpty() || routing) return
        draft = ""
        // A hand-picked target is final. Otherwise let Laya route when the bridge is up.
        if (chosen != null || !bridgeUp || targets.size < 2) { go(t, text); return }
        routing = true
        scope.launch {
            try {
                val desc = targets.map { tt ->
                    tt.title to when (tt) {
                        is Target.Bot -> tt.profile.description
                        is Target.Desktop -> "Prime Agent — desktop coding agent on this PC, reached via the agency bridge"
                    }
                }
                val res = vm.bridge.layaRoute(text, desc)
                val pick = (res["bot"] as? JsonPrimitive)?.contentOrNull.orEmpty()
                val probs = res["probs"] as? JsonObject
                fun probOf(n: String) = (probs?.get(n) as? JsonPrimitive)?.contentOrNull?.toDoubleOrNull() ?: 0.0
                val top = probOf(pick)
                val second = probs?.entries?.filter { it.key != pick }
                    ?.mapNotNull { (it.value as? JsonPrimitive)?.contentOrNull?.toDoubleOrNull() }
                    ?.maxOrNull() ?: 0.0
                val picked = targets.firstOrNull { it.title.equals(pick, true) }
                if (picked != null && top >= 0.30 && (top - second) >= 0.05) {
                    go(picked, text)
                } else {
                    pendingSend = text
                    pickerOpen = true
                }
            } catch (e: Exception) {
                // Routing is a convenience; fall back to the default bot.
                go(t, text)
            } finally {
                routing = false
            }
        }
    }

    val micPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted -> if (granted) target?.let { vm.requestVoice(); go(it, null) } }

    fun talk() {
        val t = target ?: return
        val granted = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) ==
            PackageManager.PERMISSION_GRANTED
        if (granted) { vm.requestVoice(); go(t, null) } else micPermission.launch(Manifest.permission.RECORD_AUDIO)
    }

    // ---- layout ----------------------------------------------------------------
    Column(Modifier.fillMaxSize()) {
        PageHeader("Chats") {
            IconButton(onClick = onNewAgent) {
                Icon(Icons.Filled.Add, contentDescription = "New agent", tint = colors.foreground)
            }
        }

        LazyColumn(
            Modifier.weight(1f).fillMaxWidth(),
            contentPadding = PaddingValues(bottom = space.sm),
        ) {
            if (inbox.isNotEmpty()) {
                item(key = "inbox-head") {
                    SectionKicker(
                        if (inbox.size == 1) "Needs you" else "Needs you · ${inbox.size}",
                        modifier = Modifier.padding(horizontal = space.lg, vertical = space.sm),
                    )
                }
                items(inbox, key = { "inbox-${it.key}" }) { item ->
                    InboxCard(
                        item = item,
                        botTitle = ui.profiles.find { it.name == item.profile }?.title
                            ?: item.profile.replaceFirstChar { it.uppercase() },
                        avatarColor = botAvatarColor(item.profile, item.profile == "default"),
                        onOpen = { onOpenSession(item.profile, item.sessionKey) },
                    )
                }
                item(key = "inbox-gap") { Spacer(Modifier.height(space.md)) }
            }

            if (ui.profiles.isEmpty()) {
                item(key = "empty") {
                    Box(Modifier.fillMaxWidth().padding(top = 96.dp), contentAlignment = Alignment.Center) {
                        if (ui.profilesLoading) {
                            CircularProgressIndicator(color = colors.gold, strokeWidth = 2.dp, modifier = Modifier.size(28.dp))
                        } else {
                            Text(
                                ui.profilesError.ifBlank { "No agents yet" },
                                style = MaterialTheme.typography.bodyMedium,
                                color = colors.secondary,
                            )
                        }
                    }
                }
            }

            items(ui.profiles, key = { "bot-${it.name}" }) { p ->
                val s = latest[p.name]
                val waiting = p.name in openProfiles || (s != null && s.id in openSessions)
                BotRow(
                    title = p.title,
                    color = botAvatarColor(p.name, p.isDefault),
                    line = when {
                        waiting -> "Waiting for you"
                        s != null -> sessionLine(s.title, s.preview).ifBlank { p.subtitle }
                        loading -> ""
                        else -> p.subtitle.ifBlank { "Say hello" }
                    },
                    lineTone = if (waiting) colors.warm else colors.secondary,
                    time = s?.let { relativeTime(it.startedAt) }.orEmpty(),
                    state = if (waiting) KeystoneState.WAITING else KeystoneState.IDLE,
                    onClick = { if (s != null) onOpenSession(p.name, s.id) else onOpenChat(p.name) },
                    onLongClick = { onOpenBot(p.name) },
                )
            }

            if (primaUp) {
                item(key = "desktop-kicker") {
                    SectionKicker(
                        "Desktop",
                        modifier = Modifier.padding(start = space.lg, top = space.lg, bottom = space.xs),
                    )
                }
                item(key = "desktop-prima") {
                    val s = primaLatest
                    BotRow(
                        title = "Prima",
                        color = colors.defaultViolet,
                        line = s?.let { it.name.ifBlank { it.cwd } } ?: "Prime Agent on this PC",
                        lineTone = colors.secondary,
                        time = s?.let { relativeTime(it.last_activity) }.orEmpty(),
                        state = if (s?.is_streaming == true) KeystoneState.WORKING else KeystoneState.IDLE,
                        onClick = { if (s != null) onOpenPrimaSession(s.id) else onOpenPrima() },
                        onLongClick = { onOpenPrimaSession(null) },
                    )
                }
            }

            if (recent.size > 1) {
                item(key = "recent-kicker") {
                    SectionKicker(
                        "Recent",
                        modifier = Modifier.padding(start = space.lg, top = space.lg, bottom = space.xs),
                    )
                }
                items(recent, key = { (p, s) -> "recent-${p.name}-${s.id}" }) { (p, s) ->
                    RecentRow(
                        color = botAvatarColor(p.name, p.isDefault),
                        title = sessionLine(s.title, s.preview).ifBlank { s.label },
                        time = relativeTime(s.startedAt),
                        onClick = { onOpenSession(p.name, s.id) },
                    )
                }
            }
        }

        // ---- dock ---------------------------------------------------------------
        if (target != null) {
            val pill = RoundedCornerShape(28.dp)
            val hasText = draft.isNotBlank()
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (compact) space.sm else space.lg, vertical = space.sm)
                    .heightIn(min = 56.dp)
                    .clip(pill)
                    .border(1.dp, if (focused) colors.gold else colors.stroke, pill)
                    .background(colors.surface)
                    .padding(start = 6.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Target chip — who gets this message. Tap to change.
                Box(
                    Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .clickable(role = Role.Button, onClickLabel = "Choose who to message") { pickerOpen = true },
                    contentAlignment = Alignment.Center,
                ) {
                    BotMark(color = target.color, size = 36.dp, contentDescription = "To ${target.title}")
                }
                Spacer(Modifier.width(space.sm))
                Box(Modifier.weight(1f).padding(vertical = space.sm)) {
                    BasicTextField(
                        value = draft,
                        onValueChange = { draft = it },
                        modifier = Modifier.fillMaxWidth().onFocusChanged { focused = it.isFocused },
                        maxLines = 4,
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = colors.foreground),
                        cursorBrush = SolidColor(colors.gold),
                        decorationBox = { inner ->
                            Box {
                                if (draft.isEmpty()) {
                                    Text(
                                        if (chosen == null && bridgeUp && targets.size > 1) "Ask anyone…"
                                        else "Message ${target.title}",
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
                        .background(if (hasText || routing) colors.primary else Color.Transparent)
                        .clickable(role = Role.Button) { if (hasText) send() else talk() },
                    contentAlignment = Alignment.Center,
                ) {
                    if (routing) {
                        CircularProgressIndicator(Modifier.size(20.dp), color = colors.onPrimary, strokeWidth = 2.dp)
                    } else {
                        Icon(
                            if (hasText) Icons.AutoMirrored.Filled.Send else MicIcon,
                            contentDescription = if (hasText) "Send" else "Talk to ${target.title}",
                            tint = if (hasText) colors.onPrimary else colors.foreground,
                            modifier = Modifier.size(22.dp),
                        )
                    }
                }
            }
        }
    }

    if (pickerOpen) {
        ModalBottomSheet(
            onDismissRequest = { pickerOpen = false; pendingSend = null },
            containerColor = colors.elevated,
        ) {
            Column(Modifier.padding(horizontal = space.lg, vertical = space.sm)) {
                Text(
                    if (pendingSend != null) "Who should take this?" else "Message…",
                    style = MaterialTheme.typography.titleLarge, color = colors.foreground,
                )
                Spacer(Modifier.height(space.sm))
                targets.forEach { t ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(shapes.sm))
                            .clickable {
                                chosen = t
                                pickerOpen = false
                                val text = pendingSend
                                pendingSend = null
                                if (text != null) go(t, text)
                            }
                            .heightIn(min = 56.dp)
                            .padding(horizontal = space.sm),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        BotMark(color = t.color, size = 40.dp)
                        Spacer(Modifier.width(space.md))
                        Column(Modifier.weight(1f)) {
                            Text(t.title, style = MaterialTheme.typography.bodyLarge, color = colors.foreground)
                            val sub = when (t) {
                                is Target.Bot -> t.profile.subtitle
                                is Target.Desktop -> "Prime Agent on this PC"
                            }
                            if (sub.isNotBlank()) {
                                Text(
                                    sub, style = MaterialTheme.typography.labelMedium, color = colors.tertiary,
                                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                        if (t.title == target?.title) {
                            Box(Modifier.size(8.dp).clip(CircleShape).background(colors.gold))
                        }
                    }
                }
                Spacer(Modifier.height(space.lg))
            }
        }
    }
}

/** One agent on the home screen. */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BotRow(
    title: String,
    color: Color,
    line: String,
    lineTone: Color,
    time: String,
    state: KeystoneState,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = space.sm)
            .clip(RoundedCornerShape(AgencyTokens.shapes.md))
            .combinedClickable(onClick = onClick, onLongClick = onLongClick, onLongClickLabel = "Agent settings")
            .heightIn(min = 76.dp)
            .padding(horizontal = space.sm, vertical = space.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BotMark(color = color, size = 54.dp, state = state)
        Spacer(Modifier.width(space.lg))
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium, fontSize = 17.sp),
                    color = colors.foreground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false),
                )
                if (time.isNotEmpty()) {
                    Spacer(Modifier.width(space.sm))
                    Text(time, style = MaterialTheme.typography.labelMedium, color = colors.tertiary)
                }
            }
            if (line.isNotEmpty()) {
                Spacer(Modifier.height(3.dp))
                Text(
                    line,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = lineTone,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

/** A past conversation: bot-coloured tick, one line, time. Deliberately quieter than a bot row. */
@Composable
private fun RecentRow(color: Color, title: String, time: String, onClick: () -> Unit) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = space.sm)
            .clip(RoundedCornerShape(AgencyTokens.shapes.sm))
            .clickable(onClick = onClick)
            .heightIn(min = 48.dp)
            .padding(horizontal = space.sm + 4.dp, vertical = space.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(6.dp).clip(CircleShape).background(color.copy(alpha = 0.9f)))
        Spacer(Modifier.width(space.md + 2.dp))
        Text(
            title,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            color = colors.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
        if (time.isNotEmpty()) {
            Spacer(Modifier.width(space.sm))
            Text(time, style = MaterialTheme.typography.labelSmall, color = colors.tertiary)
        }
    }
}

/** An open request, answerable in place. Approvals get Deny / Allow; the rest open the chat. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun InboxCard(
    item: NotificationCenter.InboxItem,
    botTitle: String,
    avatarColor: Color,
    onOpen: () -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val req = item.req
    val haptics = LocalHapticFeedback.current
    val approval = req.kind == RequestKind.Approval
    val headline = when (req.kind) {
        RequestKind.Approval -> "$botTitle wants to run ${req.toolName.ifBlank { "a command" }}"
        RequestKind.Clarify -> "$botTitle has a question"
        else -> "$botTitle needs input"
    }
    val body = when (req.kind) {
        RequestKind.Approval -> req.command.ifBlank { req.description }
        RequestKind.Clarify -> req.questions.firstOrNull()?.question ?: req.prompt
        else -> req.prompt.ifBlank { req.title }
    }
    val shape = RoundedCornerShape(AgencyTokens.shapes.md)
    val tone = colors.gold
    val quick = if (req.kind == RequestKind.Clarify) {
        req.questions.singleOrNull()?.takeIf { !it.multiSelect && it.choices.isNotEmpty() }?.choices.orEmpty()
    } else emptyList()
    Column(
        Modifier
            .padding(horizontal = space.lg, vertical = space.xs)
            .fillMaxWidth()
            .clip(shape)
            .background(tone.copy(alpha = 0.06f).compositeOver(colors.card))
            .border(1.dp, tone.copy(alpha = 0.45f), shape)
            .clickable(onClick = onOpen)
            .padding(space.lg),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BotMark(color = avatarColor, size = 36.dp, state = KeystoneState.WAITING)
            Spacer(Modifier.width(space.md))
            Text(
                headline, style = MaterialTheme.typography.titleSmall, color = colors.foreground,
                maxLines = 2, overflow = TextOverflow.Ellipsis,
            )
        }
        if (body.isNotBlank()) {
            Spacer(Modifier.height(space.md))
            if (approval && Regex("""rm\s+-[a-z]*r|sudo |mkfs|dd if=|drop table|format |del /|--force|chmod -R|git reset --hard""", RegexOption.IGNORE_CASE).containsMatchIn(body)) {
                Text(
                    "Destructive command",
                    style = MaterialTheme.typography.labelLarge,
                    color = colors.destructive,
                    modifier = Modifier.padding(bottom = space.xs),
                )
            }
            if (approval) {
                Text(
                    body,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    color = colors.foreground,
                    maxLines = 4, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(AgencyTokens.shapes.sm))
                        .background(colors.chrome)
                        .padding(12.dp),
                )
            } else {
                Text(
                    body,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.foreground,
                    maxLines = 4, overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Spacer(Modifier.height(space.lg))
        if (approval) {
            Row(horizontalArrangement = Arrangement.spacedBy(space.sm)) {
                InboxButton("Deny", Color.Transparent, colors.foreground, Modifier.weight(1f), outlined = true) {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    NotificationCenter.answerApproval(item, "deny")
                }
                InboxButton("Allow once", colors.gold, colors.onPrimary, Modifier.weight(1f)) {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    NotificationCenter.answerApproval(item, "once")
                }
            }
        } else if (quick.isNotEmpty()) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(space.sm),
                verticalArrangement = Arrangement.spacedBy(space.sm),
            ) {
                quick.take(6).forEach { c ->
                    InboxButton(c, colors.gold.copy(alpha = 0.16f), colors.gold, Modifier, outlined = false) {
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        NotificationCenter.answerClarify(item, c)
                    }
                }
                InboxButton("Other…", Color.Transparent, colors.secondary, Modifier, outlined = true, onClick = onOpen)
            }
        } else {
            InboxButton("Open chat", colors.gold.copy(alpha = 0.16f), colors.gold, Modifier.fillMaxWidth(), onClick = onOpen)
        }
    }
}

@Composable
private fun InboxButton(
    label: String,
    container: Color,
    content: Color,
    modifier: Modifier,
    outlined: Boolean = false,
    onClick: () -> Unit,
) {
    val colors = AgencyTokens.colors
    Box(
        modifier
            .heightIn(min = 48.dp)
            .clip(CircleShape)
            .background(container)
            .then(if (outlined) Modifier.border(1.dp, colors.foreground.copy(alpha = 0.4f), CircleShape) else Modifier)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, style = MaterialTheme.typography.labelLarge, color = content)
    }
}
