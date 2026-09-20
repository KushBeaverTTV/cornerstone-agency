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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyCard
import com.cornerstone.agency.ui.components.SectionKicker

@Composable
fun MoreScreen(vm: AppViewModel) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space

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
        }

        Spacer(Modifier.height(space.lg))
        SectionKicker("Gateway")
        Spacer(Modifier.height(space.sm))
        AgencyCard(Modifier.fillMaxWidth()) {
            val status = ui.status
            val running = status?.gatewayRunning == true
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                        text = if (status == null) "Checking…" else if (running) "Running" else "Stopped",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        color = if (running) colors.success else colors.destructive,
                    )
                }
            }
            if (status != null && status.gatewayPlatforms.isNotEmpty()) {
                Spacer(Modifier.height(space.md))
                status.gatewayPlatforms.forEach { (name, p) ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.secondary,
                        )
                        Text(
                            p.state,
                            style = MaterialTheme.typography.labelMedium,
                            color = if (p.state == "connected") colors.success else colors.tertiary,
                        )
                    }
                }
            }
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
