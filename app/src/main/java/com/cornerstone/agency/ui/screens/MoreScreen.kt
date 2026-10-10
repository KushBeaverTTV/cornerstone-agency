package com.cornerstone.agency.ui.screens

import kotlinx.coroutines.launch
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.BuildConfig
import com.cornerstone.agency.data.GatewayState
import com.cornerstone.agency.theme.AgencyFonts
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyCard
import com.cornerstone.agency.ui.components.AgencyIcons
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.PageHeader
import com.cornerstone.agency.ui.components.SectionKicker

/**
 * Me: who you are and what you're connected to. Secrets and hosts stay folded behind
 * "Edit" so the page reads as status, not as a config file. Routines/Skills/System are rows.
 */
@Composable
fun MoreScreen(
    vm: AppViewModel,
    onOpenSystem: () -> Unit = {},
    onOpenSkills: () -> Unit = {},
    onOpenJobs: () -> Unit = {},
) {
    val ui by vm.ui.collectAsState()
    val stayConnected by vm.stayConnected.collectAsState()
    val voiceBridge by vm.voiceBridge.collectAsState()
    val voiceToken by vm.voiceToken.collectAsState()
    val bridgeHealth by vm.bridgeHealth.collectAsState()
    val primaSocketState by vm.primaSocket.state.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space

    var editVoice by remember { mutableStateOf(false) }
    var voiceBridgeDraft by remember { mutableStateOf<String?>(null) }
    var voiceTokenDraft by remember { mutableStateOf<String?>(null) }
    var editBridge by remember { mutableStateOf(false) }
    var bridgeHostDraft by remember { mutableStateOf<String?>(null) }
    var bridgeTokenDraft by remember { mutableStateOf<String?>(null) }
    var confirmDisconnect by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { vm.refreshStatus(); vm.refreshBridge() }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        PageHeader("Me")

        // ---- identity ------------------------------------------------------------
        Row(
            Modifier.padding(horizontal = space.lg, vertical = space.sm),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val initial = ui.sessionUser.trim().firstOrNull()?.uppercaseChar()?.toString() ?: "·"
            Box(
                Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(colors.card)
                    .semantics { contentDescription = "Signed in as ${ui.sessionUser.ifBlank { "unknown" }}" },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    initial, color = colors.foreground, fontSize = 22.sp,
                    fontWeight = FontWeight.Bold, fontFamily = AgencyFonts.Collapse,
                )
            }
            Spacer(Modifier.width(space.lg))
            Column(Modifier.weight(1f)) {
                Text(
                    ui.sessionUser.ifBlank { "Signed in" },
                    style = MaterialTheme.typography.headlineSmall,
                    color = colors.foreground,
                )
                Text(
                    if (vm.repo.viaRelay) "Paired · ${ui.baseUrl.removePrefix("http://").substringBefore('/')}"
                        else ui.baseUrl.removePrefix("http://").removePrefix("https://"),
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.tertiary,
                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                )
            }
            val running = ui.status?.gatewayRunning == true
            Pill(
                text = if (ui.status == null) "…" else if (running) "Online" else "Stopped",
                tone = if (ui.status == null) colors.tertiary else if (running) colors.success else colors.destructive,
            )
        }

        Spacer(Modifier.height(space.md))

        // ---- connection ----------------------------------------------------------
        Section("Connection") {
            AgencyCard(Modifier.fillMaxWidth()) {
                ui.status?.version?.takeIf { it.isNotBlank() }?.let { v ->
                    InfoRow("Hermes", "v$v")
                    Spacer(Modifier.height(space.md))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("Stay connected in background", style = MaterialTheme.typography.bodyMedium, color = colors.foreground)
                        Text("Keeps the socket alive so requests reach you", style = MaterialTheme.typography.labelSmall, color = colors.tertiary)
                    }
                    Switch(
                        checked = stayConnected,
                        onCheckedChange = { vm.setStayConnected(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = colors.onPrimary,
                            checkedTrackColor = colors.gold,
                            uncheckedThumbColor = colors.secondary,
                            uncheckedTrackColor = colors.elevated,
                            uncheckedBorderColor = colors.stroke,
                        ),
                    )
                }
            }
        }

        // ---- voice -----------------------------------------------------------------
        Section("Voice", action = if (editVoice) null else "Edit", onAction = { editVoice = true }) {
            AgencyCard(Modifier.fillMaxWidth()) {
                if (!editVoice) {
                    InfoRow("Bridge", voiceBridge.ifBlank { "not set" })
                    Spacer(Modifier.height(space.sm))
                    InfoRow("Token", if (voiceToken.isBlank()) "not set" else "••••••••")
                } else {
                    FieldLabel("Bridge host:port")
                    AgencyTextField(
                        value = voiceBridgeDraft ?: voiceBridge,
                        onValueChange = { voiceBridgeDraft = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "127.0.0.1:8766",
                    )
                    Spacer(Modifier.height(space.sm))
                    FieldLabel("Token")
                    AgencyTextField(
                        value = voiceTokenDraft ?: voiceToken,
                        onValueChange = { voiceTokenDraft = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "HERMES_VOICE_BRIDGE_TOKEN",
                        secret = true,
                    )
                    SaveRow(
                        onCancel = { editVoice = false; voiceBridgeDraft = null; voiceTokenDraft = null },
                        onSave = {
                            voiceBridgeDraft?.let { vm.setVoiceBridge(it.trim()) }
                            voiceTokenDraft?.let { vm.setVoiceToken(it.trim()) }
                            voiceBridgeDraft = null; voiceTokenDraft = null; editVoice = false
                        },
                    )
                }
            }
        }

        // ---- auto-connect ------------------------------------------------------------
        if (vm.hasKnownPc) {
            Section("Auto-connect") {
                AgencyCard(Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(ui.knownPcName.ifBlank { "Your PC" }, style = MaterialTheme.typography.bodyMedium, color = colors.foreground)
                            Text(
                                if (ui.autoStatus.isNotBlank()) ui.autoStatus
                                else if (vm.repo.viaRelay) "Connected over ${com.cornerstone.agency.data.AutoConnect.pathLabel(vm.bridge.host)} · ${vm.bridge.host}"
                                else "Not connected through the PC",
                                style = MaterialTheme.typography.labelSmall, color = colors.tertiary,
                                maxLines = 2, overflow = TextOverflow.Ellipsis,
                            )
                        }
                        Pill(
                            text = if (vm.repo.viaRelay && ui.reachable) "Linked" else "Searching",
                            tone = if (vm.repo.viaRelay && ui.reachable) colors.success else colors.tertiary,
                        )
                        TextButton(onClick = { vm.reconnectNow() }) { Text("Reconnect", color = colors.gold) }
                    }
                    Spacer(Modifier.height(space.sm))
                    Text(
                        "The phone finds the PC by itself over USB or Tailscale and keeps the link alive. " +
                            "Nothing to type, scan or paste.",
                        style = MaterialTheme.typography.labelSmall, color = colors.tertiary,
                    )
                }
            }
        }

        // ---- desktop bridge ----------------------------------------------------------
        Section("Desktop bridge", action = if (editBridge) null else "Edit", onAction = { editBridge = true }) {
            AgencyCard(Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("Prima + Laya on this PC", style = MaterialTheme.typography.bodyMedium, color = colors.foreground)
                        Text(
                            vm.bridge.host.ifBlank { "not set" },
                            style = MaterialTheme.typography.labelSmall, color = colors.tertiary,
                            maxLines = 1, overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Pill(
                        text = if (bridgeHealth?.ok == true) "Healthy" else "Unreachable",
                        tone = if (bridgeHealth?.ok == true) colors.success else colors.destructive,
                    )
                    TextButton(onClick = { vm.refreshBridge() }) { Text("Test", color = colors.gold) }
                }
                bridgeHealth?.let { h ->
                    Spacer(Modifier.height(space.sm))
                    InfoRow("Prima", h.prima.version?.let { "v$it" } ?: "not found")
                    Spacer(Modifier.height(space.xs))
                    InfoRow("Laya", if (h.laya.loaded) "loaded" else if (h.laya.present) "loads on first call" else "not found")
                    Spacer(Modifier.height(space.xs))
                    InfoRow(
                        "Socket",
                        when (primaSocketState) {
                            GatewayState.OPEN -> "open"
                            GatewayState.CONNECTING -> "connecting"
                            GatewayState.RECONNECTING -> "reconnecting"
                            else -> "closed"
                        },
                    )
                }
                if (editBridge) {
                    Spacer(Modifier.height(space.md))
                    FieldLabel("Host:port")
                    AgencyTextField(
                        value = bridgeHostDraft ?: vm.bridge.host,
                        onValueChange = { bridgeHostDraft = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "127.0.0.1:9121",
                    )
                    Spacer(Modifier.height(space.sm))
                    FieldLabel("Token")
                    AgencyTextField(
                        value = bridgeTokenDraft ?: vm.bridge.token,
                        onValueChange = { bridgeTokenDraft = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "agb_…",
                        secret = true,
                    )
                    SaveRow(
                        onCancel = { editBridge = false; bridgeHostDraft = null; bridgeTokenDraft = null },
                        onSave = {
                            bridgeHostDraft?.let { vm.setBridgeHost(it) }
                            bridgeTokenDraft?.let { vm.setBridgeToken(it) }
                            bridgeHostDraft = null; bridgeTokenDraft = null; editBridge = false
                        },
                    )
                }
            }
        }

        // ---- PC services (health + restart; the watchdog task restarts them too) ------
        if (vm.bridge.configured) {
            var board by remember { mutableStateOf<com.cornerstone.agency.data.PcServices?>(null) }
            var boardError by remember { mutableStateOf("") }
            var restarting by remember { mutableStateOf<String?>(null) }
            val boardScope = androidx.compose.runtime.rememberCoroutineScope()
            fun loadBoard() {
                boardScope.launch {
                    board = runCatching { vm.bridge.services() }
                        .onFailure { boardError = "Couldn't reach the PC" }
                        .onSuccess { boardError = "" }
                        .getOrNull() ?: board
                }
            }
            LaunchedEffect(Unit) { loadBoard() }
            Section("PC services", action = "Refresh", onAction = { loadBoard() }) {
                AgencyCard(Modifier.fillMaxWidth()) {
                    val b = board
                    if (b == null) {
                        Text(boardError.ifBlank { "Checking…" }, style = MaterialTheme.typography.bodyMedium, color = colors.tertiary)
                    } else {
                        InfoRow(
                            "Live chats",
                            when (b.hermes.backend) {
                                "desktop" -> "shared with the desktop app"
                                "serve" -> "dashboard (desktop app closed)"
                                else -> "unavailable"
                            },
                        )
                        b.services.forEach { s ->
                            Spacer(Modifier.height(space.sm))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Column(Modifier.weight(1f)) {
                                    Text(s.label, style = MaterialTheme.typography.bodyMedium, color = colors.foreground)
                                    Text(":${s.port}", style = MaterialTheme.typography.labelSmall, color = colors.tertiary)
                                }
                                Pill(
                                    text = if (restarting == s.name) "Restarting" else if (s.ok) "Up" else "Down",
                                    tone = if (restarting == s.name) colors.warm else if (s.ok) colors.success else colors.destructive,
                                )
                                if (s.restartable) TextButton(
                                    enabled = restarting == null,
                                    onClick = {
                                        restarting = s.name
                                        boardScope.launch {
                                            runCatching { vm.bridge.restartService(s.name) }
                                            // Give it time to come back (the bridge restarting itself drops this call).
                                            kotlinx.coroutines.delay(6_000)
                                            restarting = null
                                            loadBoard()
                                            if (s.name == "bridge") vm.refreshBridge()
                                        }
                                    },
                                ) { Text("Restart", color = colors.gold) }
                            }
                        }
                        b.watchdog.lastOrNull()?.let { line ->
                            Spacer(Modifier.height(space.sm))
                            Text(
                                "Watchdog: ${line.trim().trimStart('\uFEFF')}",
                                style = MaterialTheme.typography.labelSmall, color = colors.tertiary,
                                maxLines = 2, overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
        }

        // ---- more -------------------------------------------------------------------
        Section("More") {
            NavRow(AgencyIcons.Jobs, "Routines", "Scheduled work your agents run on their own", onOpenJobs)
            NavRow(AgencyIcons.Spark, "Skills", "What's installed, and the skills hub", onOpenSkills)
            NavRow(AgencyIcons.Feed, "System", "Host metrics, platforms, usage, plugins", onOpenSystem)
        }

        // ---- app update (release builds from the PC's dist/, over Tailscale) -----------------
        if (vm.bridge.configured) {
            val ctx = androidx.compose.ui.platform.LocalContext.current
            val upScope = androidx.compose.runtime.rememberCoroutineScope()
            var release by remember { mutableStateOf<com.cornerstone.agency.data.AppRelease?>(null) }
            var progress by remember { mutableStateOf<Float?>(null) }
            var updateError by remember { mutableStateOf("") }
            LaunchedEffect(Unit) { release = vm.bridge.appLatest() }
            val rel = release
            if (rel != null && rel.newerThan(BuildConfig.VERSION_NAME)) {
                Section("Update") {
                    AgencyCard(Modifier.fillMaxWidth()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(1f)) {
                                Text("Version ${rel.version_name} is ready", style = MaterialTheme.typography.bodyMedium, color = colors.foreground)
                                Text(
                                    progress?.let { "Downloading… ${(it * 100).toInt()}%" }
                                        ?: updateError.ifBlank { "From your PC · ${rel.size / 1_048_576} MB · keeps your sign-in" },
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (updateError.isNotBlank()) colors.destructive else colors.tertiary,
                                )
                            }
                            TextButton(enabled = progress == null, onClick = {
                                updateError = ""
                                progress = 0f
                                upScope.launch {
                                    try {
                                        val f = updateApkFile(ctx)
                                        vm.bridge.downloadApk(f) { p -> progress = p }
                                        progress = null
                                        if (!installApk(ctx, f)) updateError = "Allow installs from Agency, then tap Update again"
                                    } catch (e: Exception) {
                                        progress = null
                                        updateError = "Download failed: ${e.message ?: "error"}"
                                    }
                                }
                            }) { Text("Update", color = colors.gold) }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(space.xl))
        Text(
            "Cornerstone Agency ${BuildConfig.VERSION_NAME} · build ${BuildConfig.VERSION_CODE}" +
                if (BuildConfig.DEBUG) " · debug" else "",
            style = MaterialTheme.typography.labelSmall,
            color = colors.tertiary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(space.sm))
        TextButton(
            onClick = { confirmDisconnect = true },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Disconnect", color = colors.destructive, style = MaterialTheme.typography.labelLarge)
        }
        Spacer(Modifier.height(space.xl))
    }

    if (confirmDisconnect) {
        AlertDialog(
            onDismissRequest = { confirmDisconnect = false },
            title = { Text("Disconnect?") },
            text = { Text("You'll need the dashboard address and your login to reconnect.") },
            confirmButton = {
                TextButton(onClick = { confirmDisconnect = false; vm.disconnect() }) {
                    Text("Disconnect", color = colors.destructive)
                }
            },
            dismissButton = {
                TextButton(onClick = { confirmDisconnect = false }) { Text("Cancel") }
            },
        )
    }
}

@Composable
private fun Section(
    title: String,
    action: String? = null,
    onAction: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val space = AgencyTokens.space
    val colors = AgencyTokens.colors
    Column(Modifier.padding(horizontal = space.lg, vertical = space.sm)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.heightIn(min = 32.dp)) {
            SectionKicker(title, Modifier.weight(1f))
            if (action != null) {
                Text(
                    action,
                    style = MaterialTheme.typography.labelLarge,
                    color = colors.gold,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(role = Role.Button, onClick = onAction)
                        .padding(horizontal = space.sm, vertical = space.xs),
                )
            }
        }
        Spacer(Modifier.height(space.xs))
        content()
    }
}

@Composable
private fun NavRow(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AgencyTokens.shapes.md))
            .clickable(onClick = onClick)
            .heightIn(min = 60.dp)
            .padding(horizontal = space.sm, vertical = space.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, contentDescription = null, tint = colors.secondary, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(space.lg))
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge, color = colors.foreground)
            Text(
                subtitle, style = MaterialTheme.typography.labelMedium, color = colors.tertiary,
                maxLines = 1, overflow = TextOverflow.Ellipsis,
            )
        }
        Icon(AgencyIcons.Chevron, contentDescription = null, tint = colors.tertiary, modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun Pill(text: String, tone: Color) {
    Box(
        Modifier
            .clip(CircleShape)
            .background(tone.copy(alpha = 0.14f))
            .padding(horizontal = AgencyTokens.space.md, vertical = AgencyTokens.space.xs),
    ) {
        Text(text, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Medium, color = tone)
    }
}

@Composable
private fun FieldLabel(text: String) {
    Text(text, style = MaterialTheme.typography.labelSmall, color = AgencyTokens.colors.tertiary)
}

@Composable
private fun SaveRow(onCancel: () -> Unit, onSave: () -> Unit) {
    val colors = AgencyTokens.colors
    Row(
        Modifier.fillMaxWidth().padding(top = AgencyTokens.space.sm),
        horizontalArrangement = Arrangement.End,
    ) {
        TextButton(onClick = onCancel) { Text("Cancel", color = colors.secondary) }
        TextButton(onClick = onSave) { Text("Save", color = colors.gold) }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    val colors = AgencyTokens.colors
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = colors.tertiary)
        Text(
            value, style = MaterialTheme.typography.bodyMedium, color = colors.foreground,
            maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(start = 16.dp),
        )
    }
}
