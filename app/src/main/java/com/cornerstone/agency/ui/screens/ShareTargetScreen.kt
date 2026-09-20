package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyCard
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.botAvatarColor

/**
 * ACTION_SEND target — pick a bot, land in its chat with the composer
 * prefilled. Images arrive as [image: name] markers (the dashboard socket has
 * no artifact upload path).
 */
@Composable
fun ShareTargetScreen(
    vm: AppViewModel,
    onPick: (profile: String) -> Unit,
    onCancel: () -> Unit,
) {
    val ui by vm.ui.collectAsState()
    val share by vm.pendingShare.collectAsState()
    val lastProfile by vm.lastShareProfile.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space

    val profiles = ui.profiles
    val sorted = profiles.sortedWith(
        compareByDescending<com.cornerstone.agency.data.BotProfile> { it.name == lastProfile }
            .thenByDescending { it.isDefault || it.name == "default" },
    )

    Column(
        Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = space.lg),
    ) {
        Spacer(Modifier.height(space.lg))
        Text(
            "Send to…",
            style = MaterialTheme.typography.displayLarge,
            color = colors.wordmark,
        )
        share?.let { s ->
            Spacer(Modifier.height(space.sm))
            val preview = buildString {
                if (s.text.isNotBlank()) append(s.text)
                s.imageNames.forEach { append("\n[image: $it]") }
            }.trim()
            if (preview.isNotEmpty()) {
                Spacer(Modifier.height(space.sm))
                AgencyCard(Modifier.fillMaxWidth()) {
                    Text(
                        preview,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.secondary,
                        maxLines = 3,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    )
                }
            }
        }
        Spacer(Modifier.height(space.lg))

        SectionKicker("Agents")
        Spacer(Modifier.height(space.sm))
        sorted.forEach { p ->
            AgencyRow(
                title = p.title,
                subtitle = p.subtitle,
                meta = if (p.name == lastProfile) "recent" else "",
                leading = {
                    BotAvatar(
                        name = p.title,
                        color = botAvatarColor(p.name, p.isDefault || p.name == "default"),
                    )
                },
                onClick = { onPick(p.name) },
            )
        }
        Spacer(Modifier.height(space.lg))
        androidx.compose.material3.TextButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Cancel", color = colors.tertiary)
        }
        Spacer(Modifier.height(space.xl))
    }
}
