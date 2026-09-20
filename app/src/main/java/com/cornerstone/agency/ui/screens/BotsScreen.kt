package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.BotProfile
import com.cornerstone.agency.data.GatewayState
import com.cornerstone.agency.data.PendingRequests
import com.cornerstone.agency.data.SessionSummary
import com.cornerstone.agency.data.listProfileSessions
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.EyeIcon
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.botAvatarColor
import com.cornerstone.agency.ui.components.relativeTime
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

@Composable
fun BotsScreen(
    vm: AppViewModel,
    onOpenChat: (String) -> Unit,
    onOpenBot: (String) -> Unit = {},
    onNewAgent: () -> Unit = {},
    onOpenPrima: () -> Unit = {},
    onOpenPrimaSessions: () -> Unit = {},
    onOpenSession: (profile: String, sessionId: String?) -> Unit = { _, _ -> },
    onOpenPrimaSession: (sessionId: String?) -> Unit = {},
) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    var query by remember { mutableStateOf("") }
    val openProfiles by PendingRequests.openProfiles.collectAsState()
    val bridgeHealth by vm.bridgeHealth.collectAsState()
    val primaUp = bridgeHealth?.ok == true && bridgeHealth?.prima?.available == true
    val bridgeUp = bridgeHealth?.ok == true
    val scope = rememberCoroutineScope()

    // -- M6-P3: Laya dispatch composer --------------------------------------
    val desktopPrimaName = "Prima (desktop)"
    val routeBots = ui.profiles.map { it.title to it.description } +
        if (primaUp) listOf(
            desktopPrimaName to "Prime Agent — desktop coding agent on this PC, reached via the agency bridge",
        ) else emptyList()
    var dispatchText by remember { mutableStateOf("") }
    var routing by remember { mutableStateOf(false) }
    var routedBot by remember { mutableStateOf<String?>(null) }
    var routeConf by remember { mutableStateOf(0.0) }
    var pickerOpen by remember { mutableStateOf(false) }
    var routeError by remember { mutableStateOf("") }

    fun sendDispatch() {
        val bot = routedBot ?: return
        val text = dispatchText.trim()
        if (text.isEmpty()) return
        dispatchText = ""
        routedBot = null
        routeError = ""
        vm.setDispatchText(text)
        scope.launch {
            if (bot == desktopPrimaName) {
                val sid = runCatching {
                    vm.bridge.primaSessions().maxByOrNull { it.last_activity }?.id
                }.getOrNull()
                onOpenPrimaSession(sid)
            } else {
                val profile = ui.profiles.find {
                    it.title.equals(bot, true) || it.name.equals(bot, true)
                } ?: return@launch
                val sid = runCatching {
                    listProfileSessions(vm.gateway, profile.name).firstOrNull()?.id
                }.getOrNull()
                onOpenSession(profile.name, sid)
            }
        }
    }

    fun routeDispatch() {
        val text = dispatchText.trim()
        if (text.isEmpty() || routing) return
        routing = true
        routeError = ""
        scope.launch {
            try {
                val res = vm.bridge.layaRoute(text, routeBots)
                val pick = res["bot"]?.let { it as? kotlinx.serialization.json.JsonPrimitive }
                    ?.contentOrNull ?: ""
                val probs = res["probs"] as? kotlinx.serialization.json.JsonObject
                fun probOf(name: String): Double =
                    (probs?.get(name) as? kotlinx.serialization.json.JsonPrimitive)
                        ?.contentOrNull?.toDoubleOrNull() ?: 0.0
                // `confidence` is a margin score, not a probability — the chip
                // shows the pick's probability and the picker auto-opens only
                // when the route is genuinely ambiguous.
                val topProb = probOf(pick)
                val secondProb = probs?.entries
                    ?.filter { it.key != pick }
                    ?.mapNotNull {
                        (it.value as? kotlinx.serialization.json.JsonPrimitive)
                            ?.contentOrNull?.toDoubleOrNull()
                    }
                    ?.maxOrNull() ?: 0.0
                routedBot = pick.ifBlank { null }
                routeConf = topProb
                if (topProb < 0.30 || (topProb - secondProb) < 0.05) pickerOpen = true
            } catch (e: Exception) {
                routeError = e.message ?: "route failed"
            } finally {
                routing = false
            }
        }
    }

    LaunchedEffect(Unit) { vm.refreshBridge() }

    val filtered = ui.profiles.filter {
        query.isBlank() ||
            it.title.contains(query, ignoreCase = true) ||
            it.subtitle.contains(query, ignoreCase = true) ||
            it.name.contains(query, ignoreCase = true)
    }

    Column(Modifier.fillMaxSize()) {
    PullToRefreshBox(
        isRefreshing = ui.profilesLoading,
        onRefresh = { vm.refreshProfiles() },
        modifier = Modifier.weight(1f).fillMaxWidth(),
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                Column(Modifier.padding(horizontal = space.lg)) {
                    Spacer(Modifier.height(space.lg))
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Bots",
                            style = MaterialTheme.typography.displayLarge,
                            color = colors.wordmark,
                            modifier = Modifier.weight(1f),
                        )
                        IconButton(onClick = onNewAgent) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "New agent",
                                tint = colors.secondary,
                            )
                        }
                    }
                    Spacer(Modifier.height(space.md))
                    AgencyTextField(
                        value = query,
                        onValueChange = { query = it },
                        placeholder = "Search",
                    )
                    Spacer(Modifier.height(space.lg))
                    SectionKicker("Agents")
                    Spacer(Modifier.height(space.xs))
                }
            }

            if (ui.profilesError.isNotEmpty() && ui.profiles.isEmpty()) {
                item {
                    EmptyState("No roster", ui.profilesError)
                }
            } else if (filtered.isEmpty() && !ui.profilesLoading) {
                item {
                    EmptyState(
                        if (ui.profiles.isEmpty()) "No agents" else "No matches",
                        if (ui.profiles.isEmpty()) "Pull to refresh" else "Nothing matches “$query”",
                    )
                }
            } else {
                items(filtered, key = { it.name }) { profile ->
                    AgencyRow(
                        title = profile.title,
                        subtitle = profile.subtitle,
                        meta = if (profile.description.isNotBlank()) profile.model else "",
                        modifier = Modifier.padding(horizontal = space.sm),
                        onClick = { onOpenChat(profile.name) },
                        onLongClick = { onOpenBot(profile.name) },
                        leading = {
                            BotAvatar(
                                name = profile.title,
                                color = botAvatarColor(profile.name, profile.isDefault),
                            )
                        },
                        trailing = if (profile.name in openProfiles) {
                            { NeedsDot() }
                        } else null,
                    )
                }
            }

            if (primaUp && (query.isBlank() || "prima".contains(query, ignoreCase = true) ||
                    "prime agent".contains(query, ignoreCase = true))
            ) {
                item(key = "kicker-desktop") {
                    Column(Modifier.padding(horizontal = space.lg)) {
                        Spacer(Modifier.height(space.md))
                        SectionKicker("Desktop")
                        Spacer(Modifier.height(space.xs))
                    }
                }
                item(key = "desktop-prima") {
                    AgencyRow(
                        title = "Prima",
                        subtitle = "Prime Agent · ${bridgeHealth?.prima?.version ?: ""}".trimEnd('·', ' '),
                        meta = "",
                        modifier = Modifier.padding(horizontal = space.sm),
                        onClick = onOpenPrima,
                        onLongClick = onOpenPrimaSessions,
                        leading = {
                            BotAvatar(name = "Prima", color = colors.defaultViolet)
                        },
                    )
                }
            }
        }
        if (ui.profilesLoading && ui.profiles.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = colors.gold,
            )
        }
    }

    // -- M6-P3: Laya dispatch composer (bridge healthy only) -----------------
    if (bridgeUp) {
        if (routeError.isNotEmpty()) {
            Text(
                routeError,
                color = colors.destructive,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(horizontal = space.lg, vertical = space.xxs),
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = space.lg, vertical = space.sm),
            verticalAlignment = Alignment.Bottom,
        ) {
            routedBot?.let { bot ->
                // Routing chip — tap to change the target bot.
                Text(
                    "→ $bot · ${(routeConf * 100).toInt()}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.gold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.gold.copy(alpha = 0.15f))
                        .clickable { pickerOpen = true }
                        .padding(horizontal = space.sm, vertical = space.xs),
                )
                Spacer(Modifier.width(space.xs))
            }
            AgencyTextField(
                value = dispatchText,
                onValueChange = { dispatchText = it },
                modifier = Modifier.weight(1f),
                placeholder = "Ask anyone…",
                singleLine = true,
            )
            Spacer(Modifier.width(space.sm))
            if (routing) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp).padding(bottom = space.xs),
                    color = colors.gold,
                    strokeWidth = 2.dp,
                )
            } else {
                Icon(
                    Icons.AutoMirrored.Filled.Send,
                    contentDescription = if (routedBot == null) "Route" else "Send",
                    tint = if (dispatchText.isNotBlank() || routedBot != null) colors.primary
                        else colors.tertiary,
                    modifier = Modifier
                        .padding(bottom = space.xs)
                        .clickable {
                            if (routedBot != null) sendDispatch() else routeDispatch()
                        },
                )
            }
        }
    }
    }

    if (pickerOpen) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { pickerOpen = false },
            title = { Text("Send to…") },
            text = {
                Column {
                    routeBots.forEach { (name, desc) ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    routedBot = name
                                    pickerOpen = false
                                }
                                .padding(vertical = space.xs),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            BotAvatar(
                                name = name.removeSuffix(" (desktop)"),
                                color = if (name == desktopPrimaName) colors.defaultViolet
                                    else botAvatarColor(
                                        ui.profiles.find { it.title == name }?.name ?: name,
                                        ui.profiles.find { it.title == name }?.isDefault == true,
                                    ),
                                size = 30.dp,
                            )
                            Spacer(Modifier.width(space.sm))
                            Column {
                                Text(
                                    name,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colors.foreground,
                                )
                                if (desc.isNotBlank()) {
                                    Text(
                                        desc,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = colors.tertiary,
                                        maxLines = 1,
                                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                androidx.compose.material3.TextButton(onClick = { pickerOpen = false }) {
                    Text("Cancel")
                }
            },
        )
    }
}

@Composable
fun ChatsScreen(
    vm: AppViewModel,
    onOpenSession: (profile: String, sessionId: String) -> Unit,
    onOpenPrimaSession: (sessionId: String) -> Unit = {},
) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val socketState by vm.gateway.state.collectAsState()
    val hideCronChatter by vm.hideCronChatter.collectAsState()
    val openSessions by PendingRequests.openSessions.collectAsState()
    val bridgeHealth by vm.bridgeHealth.collectAsState()
    val scope = rememberCoroutineScope()
    var primaSessions by remember {
        mutableStateOf<List<com.cornerstone.agency.data.PrimaSession>>(emptyList())
    }

    // Sessions grouped by bot, in roster order.
    var groups by remember {
        mutableStateOf<List<Pair<BotProfile, List<SessionSummary>>>>(emptyList())
    }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }
    // Cron job names for the chatter filter (display name + raw [bot:x] name).
    var cronNames by remember { mutableStateOf<Set<String>>(emptySet()) }

    LaunchedEffect(Unit) {
        cronNames = runCatching { vm.repo.cronJobs() }
            .getOrDefault(emptyList())
            .flatMap { listOf(it.name, it.displayName) }
            .toSet()
    }

    fun refresh() {
        val profiles = vm.ui.value.profiles
        if (profiles.isEmpty() || socketState != GatewayState.OPEN) return
        scope.launch {
            try {
                val out = mutableListOf<Pair<BotProfile, List<SessionSummary>>>()
                for (p in profiles) {
                    val sessions = runCatching { listProfileSessions(vm.gateway, p.name) }
                        .getOrDefault(emptyList())
                    if (sessions.isNotEmpty()) out += p to sessions
                }
                groups = out
                error = ""
            } catch (e: Exception) {
                error = e.message ?: "Failed to load sessions"
            } finally {
                loading = false
            }
        }
    }

    LaunchedEffect(socketState, ui.profiles, bridgeHealth) {
        if (socketState == GatewayState.OPEN && ui.profiles.isNotEmpty()) refresh()
        if (bridgeHealth?.ok == true && bridgeHealth?.prima?.available == true) {
            primaSessions = runCatching { vm.bridge.primaSessions() }.getOrDefault(emptyList())
        } else {
            primaSessions = emptyList()
        }
    }
    // sessions.changed is a global push — refresh opportunistically.
    LaunchedEffect(Unit) {
        vm.gateway.events.collect { ev ->
            if (ev.type == "sessions.changed") refresh()
        }
    }

    val visibleGroups = if (!hideCronChatter) groups else groups.map { (p, sessions) ->
        p to sessions.filterNot { s ->
            val hay = "${s.title}\n${s.preview}"
            val title = s.title.trim()
            hay.contains("running as a scheduled cron job", ignoreCase = true) ||
                cronNames.any { title == it || title.startsWith("$it ·") }
        }
    }.filter { it.second.isNotEmpty() }

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ScreenTitle("Chats", Modifier.weight(1f))
            IconButton(onClick = { vm.setHideCronChatter(!hideCronChatter) }) {
                Icon(
                    EyeIcon,
                    contentDescription = "Hide cron chatter",
                    tint = if (hideCronChatter) colors.gold else colors.tertiary,
                    modifier = Modifier.padding(end = space.lg),
                )
            }
        }
        when {
            loading && visibleGroups.isEmpty() -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator(
                    color = colors.gold, strokeWidth = 2.dp,
                    modifier = Modifier.size(28.dp),
                )
            }

            visibleGroups.isEmpty() && primaSessions.isEmpty() -> EmptyState(
                if (error.isEmpty()) "No chats yet" else "Couldn't load chats",
                if (error.isEmpty()) {
                    if (hideCronChatter && groups.isNotEmpty()) "All visible sessions are cron chatter"
                    else "Open a bot to start a conversation"
                } else error,
            )

            else -> LazyColumn(Modifier.fillMaxSize()) {
                visibleGroups.forEach { (profile, sessions) ->
                    item(key = "kicker-${profile.name}") {
                        SectionKicker(
                            profile.title,
                            modifier = Modifier.padding(
                                horizontal = space.lg, vertical = space.xs,
                            ),
                        )
                    }
                    items(sessions, key = { "${profile.name}:${it.id}" }) { s ->
                        AgencyRow(
                            title = s.label,
                            subtitle = s.preview.lineSequence().firstOrNull() ?: "",
                            meta = "",
                            onClick = { onOpenSession(profile.name, s.id) },
                            modifier = Modifier.padding(horizontal = space.sm),
                            leading = {
                                BotAvatar(
                                    name = profile.title,
                                    color = botAvatarColor(profile.name, profile.isDefault),
                                )
                            },
                            trailing = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (s.id in openSessions) NeedsYouPill()
                                    val t = relativeTime(s.startedAt)
                                    if (t.isNotEmpty()) {
                                        Spacer(Modifier.width(space.xs))
                                        Text(
                                            text = t,
                                            style = MaterialTheme.typography.labelMedium,
                                            color = colors.tertiary,
                                        )
                                    }
                                }
                            },
                        )
                    }
                }
                if (primaSessions.isNotEmpty()) {
                    item(key = "kicker-desktop-prima") {
                        SectionKicker(
                            "PRIMA",
                            modifier = Modifier.padding(
                                horizontal = space.lg, vertical = space.xs,
                            ),
                        )
                    }
                    items(primaSessions, key = { "dprima:${it.id}" }) { s ->
                        AgencyRow(
                            title = s.label,
                            subtitle = s.cwd,
                            meta = if (s.is_streaming) "streaming" else "",
                            onClick = { onOpenPrimaSession(s.id) },
                            modifier = Modifier.padding(horizontal = space.sm),
                            leading = {
                                BotAvatar(name = "Prima", color = colors.defaultViolet)
                            },
                        )
                    }
                }
            }
        }
    }
}

/** Gold "needs you" pill for session rows with an unanswered request card. */
@Composable
private fun NeedsYouPill() {
    val colors = AgencyTokens.colors
    Text(
        text = "needs you",
        style = MaterialTheme.typography.labelSmall,
        color = colors.gold,
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(colors.gold.copy(alpha = 0.15f))
            .padding(horizontal = AgencyTokens.space.sm, vertical = 2.dp),
    )
}

/** Small gold dot on a bot row when one of its sessions needs input. */
@Composable
private fun NeedsDot() {
    Box(
        Modifier
            .size(8.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(AgencyTokens.colors.gold),
    )
}

@Composable
private fun ScreenTitle(title: String, modifier: Modifier = Modifier) {
    val colors = AgencyTokens.colors
    Text(
        text = title,
        style = MaterialTheme.typography.displayLarge,
        color = colors.wordmark,
        modifier = modifier.padding(
            horizontal = AgencyTokens.space.lg,
            vertical = AgencyTokens.space.lg,
        ),
    )
}
