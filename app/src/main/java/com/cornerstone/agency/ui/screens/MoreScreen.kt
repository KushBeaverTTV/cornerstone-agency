package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.BuildConfig
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyCard
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.SectionKicker

/** More tab — settings hub: connection, gateway pill, rows to sub-screens. */
@Composable
fun MoreScreen(
    vm: AppViewModel,
    onOpenSystem: () -> Unit = {},
    onOpenSkills: () -> Unit = {},
) {
    val ui by vm.ui.collectAsState()
    val stayConnected by vm.stayConnected.collectAsState()
    val voiceBridge by vm.voiceBridge.collectAsState()
    val voiceToken by vm.voiceToken.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    var voiceBridgeDraft by androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf<String?>(null)
    }
    var voiceTokenDraft by androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf<String?>(null)
    }

    LaunchedEffect(Unit) { vm.refreshStatus() }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = space.lg),
    ) {
        Spacer(Modifier.height(space.lg))
        Text(
            text = "More",
            style = MaterialTheme.typography.displayLarge,
            color = colors.wordmark,
        )
        Spacer(Modifier.height(space.lg))

        SectionKicker("Connection")
        Spacer(Modifier.height(space.sm))
        AgencyCard(Modifier.fillMaxWidth()) {
            InfoRow("Dashboard", ui.baseUrl)
            Spacer(Modifier.height(space.sm))
            InfoRow("Signed in as", ui.sessionUser.ifBlank { "—" })
            ui.status?.version?.takeIf { it.isNotBlank() }?.let { v ->
                Spacer(Modifier.height(space.sm))
                InfoRow("Hermes", "v$v")
            }
            Spacer(Modifier.height(space.sm))
            // Compact gateway pill — details live on the System screen.
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Gateway",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.tertiary,
                    modifier = Modifier.weight(1f),
                )
                val running = ui.status?.gatewayRunning == true
                Box(
                    Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(
                            if (running) colors.success.copy(alpha = 0.16f)
                            else colors.destructive.copy(alpha = 0.16f),
                        )
                        .padding(horizontal = space.md, vertical = space.xs),
                ) {
                    Text(
                        text = if (ui.status == null) "…" else if (running) "Running" else "Stopped",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        color = if (running) colors.success else colors.destructive,
                    )
                }
            }
            Spacer(Modifier.height(space.sm))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(
                        "Stay connected in background",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.foreground,
                    )
                    Text(
                        "Foreground service keeps the socket alive",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.tertiary,
                    )
                }
                androidx.compose.material3.Switch(
                    checked = stayConnected,
                    onCheckedChange = { vm.setStayConnected(it) },
                )
            }
            Spacer(Modifier.height(space.sm))
            Text(
                "Voice bridge",
                style = MaterialTheme.typography.labelSmall,
                color = colors.tertiary,
            )
            com.cornerstone.agency.ui.components.AgencyTextField(
                value = voiceBridgeDraft ?: voiceBridge,
                onValueChange = { voiceBridgeDraft = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = "127.0.0.1:8766",
                singleLine = true,
            )
            Spacer(Modifier.height(space.xs))
            Text(
                "Voice token",
                style = MaterialTheme.typography.labelSmall,
                color = colors.tertiary,
            )
            com.cornerstone.agency.ui.components.AgencyTextField(
                value = voiceTokenDraft ?: voiceToken,
                onValueChange = { voiceTokenDraft = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = "HERMES_VOICE_BRIDGE_TOKEN",
                singleLine = true,
            )
            if (voiceBridgeDraft != null || voiceTokenDraft != null) {
                Spacer(Modifier.height(space.xs))
                TextButton(
                    onClick = {
                        voiceBridgeDraft?.let { vm.setVoiceBridge(it.trim()) }
                        voiceTokenDraft?.let { vm.setVoiceToken(it.trim()) }
                        voiceBridgeDraft = null
                        voiceTokenDraft = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Save voice settings", color = colors.gold)
                }
            }
        }

        Spacer(Modifier.height(space.lg))
        SectionKicker("Hub")
        Spacer(Modifier.height(space.sm))
        AgencyRow(
            title = "System",
            subtitle = "Host metrics, gateway platforms, usage, plugins",
            meta = "",
            onClick = onOpenSystem,
        )
        AgencyRow(
            title = "Skills",
            subtitle = "Installed skills and the skills hub",
            meta = "",
            onClick = onOpenSkills,
        )
        AgencyRow(
            title = "Appearance",
            subtitle = "Theme: Hermes dark",
            meta = "",
            onClick = {},
        )

        Spacer(Modifier.height(space.lg))
        SectionKicker("About")
        Spacer(Modifier.height(space.sm))
        AgencyCard(Modifier.fillMaxWidth()) {
            InfoRow("Cornerstone Agency", "v${BuildConfig.VERSION_NAME}")
            Spacer(Modifier.height(space.sm))
            InfoRow("Build", "${BuildConfig.VERSION_CODE} · debug")
        }

        Spacer(Modifier.height(space.xl))
        TextButton(
            onClick = { vm.disconnect() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Disconnect", color = colors.destructive, style = MaterialTheme.typography.labelLarge)
        }
        Spacer(Modifier.height(space.xl))
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    val colors = AgencyTokens.colors
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = colors.tertiary)
        Text(value, style = MaterialTheme.typography.bodyMedium, color = colors.foreground)
    }
}
