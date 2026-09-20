package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.data.ModelProvider
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyCard
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.ModelPickerSheet
import com.cornerstone.agency.ui.components.PrimaryButton
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.botAvatarColor
import kotlinx.coroutines.launch

/**
 * Bot settings: SOUL.md view/edit, model picker, description, sessions link,
 * delete. Mutations go through the verified REST surface:
 * PUT soul {content}, PUT description {description}, PUT model {provider,model}.
 */
@Composable
fun BotDetailScreen(
    vm: AppViewModel,
    profileName: String,
    onBack: () -> Unit,
    onOpenSessions: () -> Unit,
    onDeleted: () -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val ui by vm.ui.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbar = remember { SnackbarHostState() }

    val profile = ui.profiles.find { it.name == profileName }
    val botName = botDisplayName(ui.profiles, profileName)
    val isDefault = profile?.isDefault == true || profileName == "default"

    var soulText by remember { mutableStateOf<String?>(null) }
    var editingSoul by remember { mutableStateOf(false) }
    var soulDraft by remember { mutableStateOf("") }
    var soulBusy by remember { mutableStateOf(false) }

    var descDraft by remember { mutableStateOf("") }
    var descDirty by remember { mutableStateOf(false) }
    var descBusy by remember { mutableStateOf(false) }

    var modelSheet by remember { mutableStateOf(false) }
    var providers by remember { mutableStateOf<List<ModelProvider>>(emptyList()) }
    var providersLoading by remember { mutableStateOf(false) }
    var modelBusy by remember { mutableStateOf(false) }

    var confirmDelete by remember { mutableStateOf(false) }
    var deleteBusy by remember { mutableStateOf(false) }

    LaunchedEffect(profileName) {
        soulText = runCatching { vm.repo.soul(profileName).content }
            .getOrElse { "" }
    }
    // Keep the field in sync with the loaded profile until the user edits it.
    LaunchedEffect(profile?.description) {
        if (!descDirty) descDraft = profile?.description ?: ""
    }

    Box(Modifier.fillMaxSize().safeDrawingPadding()) {
        if (editingSoul) {
            // Full-screen SOUL.md editor.
            Column(Modifier.fillMaxSize().imePadding().padding(horizontal = space.lg)) {
                Row(
                    Modifier.fillMaxWidth().padding(vertical = space.xs),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextButton(
                        onClick = { editingSoul = false },
                        enabled = !soulBusy,
                    ) { Text("Cancel", color = colors.secondary) }
                    Spacer(Modifier.weight(1f))
                    Text(
                        "SOUL.md",
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.foreground,
                    )
                    Spacer(Modifier.weight(1f))
                    TextButton(
                        enabled = !soulBusy,
                        onClick = {
                            soulBusy = true
                            scope.launch {
                                try {
                                    vm.repo.updateSoul(profileName, soulDraft)
                                    soulText = soulDraft
                                    editingSoul = false
                                    snackbar.showSnackbar("Soul saved")
                                } catch (e: Exception) {
                                    snackbar.showSnackbar("Save failed: ${e.message}")
                                } finally {
                                    soulBusy = false
                                }
                            }
                        },
                    ) { Text("Save", color = colors.gold) }
                }
                AgencyTextField(
                    value = soulDraft,
                    onValueChange = { soulDraft = it },
                    placeholder = "You are…",
                    singleLine = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
            }
        } else {
            Column(Modifier.fillMaxSize()) {
                // Top bar
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = space.sm, vertical = space.xs),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, "Back",
                            tint = colors.foreground,
                        )
                    }
                    Text(
                        "Bot settings",
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.foreground,
                    )
                }

                Column(
                    Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = space.lg),
                ) {
                    Spacer(Modifier.height(space.md))

                    // Header: avatar + name + model pill + description
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        BotAvatar(
                            name = botName,
                            color = botAvatarColor(profileName, isDefault),
                            size = 56.dp,
                        )
                        Spacer(Modifier.width(space.md))
                        Column {
                            Text(
                                botName,
                                style = MaterialTheme.typography.displaySmall,
                                color = colors.wordmark,
                            )
                            Spacer(Modifier.height(space.xs))
                            val modelLabel =
                                profile?.model?.ifBlank { null } ?: "No model"
                            Box(
                                Modifier
                                    .clip(RoundedCornerShape(999.dp))
                                    .background(colors.primarySoft)
                                    .padding(horizontal = 10.dp, vertical = 3.dp),
                            ) {
                                Text(
                                    modelLabel,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = colors.foreground,
                                )
                            }
                        }
                    }
                    if (!profile?.description.isNullOrBlank()) {
                        Spacer(Modifier.height(space.sm))
                        Text(
                            profile!!.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.secondary,
                        )
                    }
                    Spacer(Modifier.height(space.lg))

                    // Soul
                    SectionKicker("Soul")
                    Spacer(Modifier.height(space.xs))
                    AgencyCard(Modifier.fillMaxWidth()) {
                        when {
                            soulText == null -> CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = colors.gold, strokeWidth = 2.dp,
                            )

                            soulText!!.isBlank() -> Text(
                                "No SOUL.md yet",
                                style = MaterialTheme.typography.bodyMedium,
                                color = colors.tertiary,
                            )

                            else -> Text(
                                soulText!!,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 13.sp,
                                color = colors.secondary,
                                maxLines = 12,
                            )
                        }
                        Spacer(Modifier.height(space.sm))
                        TextButton(
                            onClick = {
                                soulDraft = soulText ?: ""
                                editingSoul = true
                            },
                        ) { Text("Edit", color = colors.gold) }
                    }
                    Spacer(Modifier.height(space.lg))

                    // Model
                    SectionKicker("Model")
                    Spacer(Modifier.height(space.xs))
                    AgencyRow(
                        title = profile?.model?.ifBlank { "Default model" } ?: "…",
                        subtitle = profile?.provider ?: "",
                        meta = "Change",
                        onClick = {
                            modelSheet = true
                            if (providers.isEmpty() && !providersLoading) {
                                providersLoading = true
                                scope.launch {
                                    providers = runCatching { vm.repo.modelOptions() }
                                        .getOrDefault(emptyList())
                                    providersLoading = false
                                }
                            }
                        },
                    )
                    Spacer(Modifier.height(space.lg))

                    // Description
                    SectionKicker("Description")
                    Spacer(Modifier.height(space.xs))
                    AgencyTextField(
                        value = descDraft,
                        onValueChange = {
                            descDraft = it
                            descDirty = true
                        },
                        placeholder = "What this agent does",
                    )
                    Spacer(Modifier.height(space.sm))
                    val dirty = descDraft != (profile?.description ?: "")
                    PrimaryButton(
                        label = "Save description",
                        enabled = dirty && !descBusy,
                        loading = descBusy,
                        onClick = {
                            descBusy = true
                            scope.launch {
                                try {
                                    vm.repo.updateDescription(profileName, descDraft)
                                    descDirty = false
                                    vm.refreshProfiles()
                                    snackbar.showSnackbar("Description saved")
                                } catch (e: Exception) {
                                    snackbar.showSnackbar("Save failed: ${e.message}")
                                } finally {
                                    descBusy = false
                                }
                            }
                        },
                    )
                    Spacer(Modifier.height(space.lg))

                    // Sessions
                    SectionKicker("Sessions")
                    Spacer(Modifier.height(space.xs))
                    AgencyRow(
                        title = "View sessions",
                        subtitle = "Past conversations with $botName",
                        meta = "",
                        onClick = onOpenSessions,
                    )
                    Spacer(Modifier.height(space.lg))

                    // Danger
                    SectionKicker("Danger")
                    Spacer(Modifier.height(space.xs))
                    AgencyRow(
                        title = "Delete agent",
                        subtitle = if (isDefault) {
                            "The default agent can't be deleted"
                        } else {
                            "Remove $botName and its files"
                        },
                        meta = "",
                        dimmed = isDefault,
                        onClick = { if (!isDefault) confirmDelete = true },
                    )
                    Spacer(Modifier.height(space.xl))
                }
            }
        }

        SnackbarHost(
            hostState = snackbar,
            modifier = Modifier.align(Alignment.BottomCenter),
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
                        snackbar.showSnackbar("Model set to $model")
                    } catch (e: Exception) {
                        snackbar.showSnackbar("Model change failed: ${e.message}")
                    } finally {
                        modelBusy = false
                    }
                }
            },
            onDismiss = { modelSheet = false },
        )
    }

    if (confirmDelete) {
        AlertDialog(
            onDismissRequest = { if (!deleteBusy) confirmDelete = false },
            title = { Text("Delete $botName?") },
            text = {
                Text("This removes the agent's profile directory. Sessions may remain.")
            },
            confirmButton = {
                TextButton(
                    enabled = !deleteBusy,
                    onClick = {
                        deleteBusy = true
                        scope.launch {
                            try {
                                vm.repo.deleteProfile(profileName)
                                vm.refreshProfiles()
                                confirmDelete = false
                                onDeleted()
                            } catch (e: Exception) {
                                snackbar.showSnackbar("Delete failed: ${e.message}")
                            } finally {
                                deleteBusy = false
                            }
                        }
                    },
                ) { Text("Delete", color = colors.destructive) }
            },
            dismissButton = {
                TextButton(
                    onClick = { confirmDelete = false },
                    enabled = !deleteBusy,
                ) { Text("Cancel") }
            },
        )
    }
}
