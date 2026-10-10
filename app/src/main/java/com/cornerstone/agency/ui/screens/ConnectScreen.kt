package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.PrimaryButton
import com.cornerstone.agency.ui.components.SectionKicker

private val PRESETS = listOf(
    "USB" to "http://127.0.0.1:9119",
    "LAN" to "http://192.168.0.223:9119",
    "Tailscale" to "http://100.84.54.112:9119",
)

@Composable
fun ConnectScreen(vm: AppViewModel) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space

    var url by remember { mutableStateOf(PRESETS[0].second) }
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = space.xl),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(Modifier.height(96.dp))
        Text(
            text = "Cornerstone",
            style = MaterialTheme.typography.displayLarge,
            color = colors.wordmark,
        )
        Text(
            text = "Agency",
            style = MaterialTheme.typography.displayLarge,
            color = colors.gold,
        )
        Spacer(Modifier.height(space.sm))
        Text(
            text = if (vm.hasKnownPc) "This phone knows ${ui.knownPcName.ifBlank { "your PC" }} — it connects by itself."
                else "Pair with your PC, or connect to a Hermes dashboard.",
            style = MaterialTheme.typography.bodyMedium,
            color = colors.secondary,
        )
        Spacer(Modifier.height(space.xl))

        // Auto-connect: a build that knows the PC needs no human step. Show what it is doing and
        // a retry; the manual paths below stay as the fallback.
        if (vm.hasKnownPc) {
            SectionKicker("Auto-connect")
            Spacer(Modifier.height(space.xs))
            Text(
                text = ui.autoStatus.ifBlank { "Idle" },
                style = MaterialTheme.typography.bodyMedium,
                color = if (ui.autoStatus.contains("not reachable") || ui.autoStatus.contains("refused")) colors.destructive else colors.foreground,
            )
            Spacer(Modifier.height(space.sm))
            PrimaryButton(
                label = "Connect now",
                loading = ui.connectBusy || ui.autoStatus.endsWith("…"),
                onClick = { vm.reconnectNow() },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(space.xl))
        }

        // Pairing (preferred): `agency-bridge pair` on the PC shows a QR; the phone camera opens
        // it straight into the app. Pasting the link works too.
        SectionKicker("Pair with PC")
        Spacer(Modifier.height(space.xs))
        Text(
            text = "On the PC run pair-agency-phone, then scan the QR with your camera.",
            style = MaterialTheme.typography.bodySmall,
            color = colors.secondary,
        )
        Spacer(Modifier.height(space.sm))
        var pairText by remember { mutableStateOf("") }
        val pairLink = remember(pairText) { com.cornerstone.agency.data.PairingLink.parse(pairText) }
        AgencyTextField(
            value = pairText,
            onValueChange = { pairText = it },
            placeholder = "or paste the cornerstone-agency:// link",
            keyboardType = KeyboardType.Uri,
        )
        if (pairText.isNotBlank()) {
            Spacer(Modifier.height(space.sm))
            PrimaryButton(
                label = if (pairLink == null) "Not a pairing link" else "Pair with ${pairLink.name.ifBlank { pairLink.bridge }}",
                loading = ui.connectBusy,
                enabled = pairLink != null,
                onClick = { pairLink?.let { vm.pair(it) } },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(Modifier.height(space.xl))

        SectionKicker("Dashboard")
        Spacer(Modifier.height(space.xs))
        AgencyTextField(
            value = url,
            onValueChange = { url = it },
            placeholder = "http://127.0.0.1:9119",
            keyboardType = KeyboardType.Uri,
        )
        Spacer(Modifier.height(space.md))
        Row(horizontalArrangement = Arrangement.spacedBy(space.sm)) {
            PRESETS.forEach { (label, preset) ->
                val selected = url == preset
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (selected) colors.primarySoft else colors.card,
                    modifier = Modifier.clickable { url = preset },
                ) {
                    Text(
                        text = label,
                        modifier = Modifier.padding(horizontal = space.md, vertical = space.xs),
                        style = MaterialTheme.typography.labelMedium,
                        color = if (selected) colors.foreground else colors.secondary,
                    )
                }
            }
        }

        if (ui.needsCredentials) {
            Spacer(Modifier.height(space.lg))
            SectionKicker("Sign in")
            Spacer(Modifier.height(space.xs))
            AgencyTextField(
                value = user,
                onValueChange = { user = it },
                placeholder = "Username",
            )
            Spacer(Modifier.height(space.sm))
            AgencyTextField(
                value = pass,
                onValueChange = { pass = it },
                placeholder = "Password",
                secret = true,
                keyboardType = KeyboardType.Password,
            )
        }

        if (ui.connectError.isNotEmpty()) {
            Spacer(Modifier.height(space.md))
            Text(
                text = ui.connectError,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.destructive,
            )
        }

        Spacer(Modifier.height(space.xl))
        PrimaryButton(
            label = if (ui.needsCredentials) "Sign in" else "Continue",
            loading = ui.connectBusy,
            enabled = url.isNotBlank() && (!ui.needsCredentials || (user.isNotBlank() && pass.isNotBlank())),
            onClick = {
                if (ui.needsCredentials) vm.login(user, pass) else vm.connect(url)
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
