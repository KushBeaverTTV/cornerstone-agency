package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.HubSkill
import com.cornerstone.agency.data.Skill
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.components.PageHeader
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.SectionKicker
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** Installed skills with toggles + skills-hub search (install via POST). */
@Composable
fun SkillsScreen(vm: AppViewModel, onBack: () -> Unit) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val scope = rememberCoroutineScope()
    val snackbar = remember { SnackbarHostState() }

    var skills by remember { mutableStateOf<List<Skill>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf("") }

    var hubQuery by remember { mutableStateOf("") }
    var hubResults by remember { mutableStateOf<List<HubSkill>>(emptyList()) }
    var hubSearching by remember { mutableStateOf(false) }
    var hubJob by remember { mutableStateOf<Job?>(null) }
    var pendingInstall by remember { mutableStateOf<HubSkill?>(null) }
    var installBusy by remember { mutableStateOf(false) }

    fun load() {
        scope.launch {
            try {
                skills = vm.repo.skills()
                error = ""
            } catch (e: Exception) {
                error = e.message ?: "Failed to load skills"
            } finally {
                loading = false
            }
        }
    }
    LaunchedEffect(Unit) { load() }

    // Debounced hub search.
    LaunchedEffect(hubQuery) {
        hubJob?.cancel()
        val q = hubQuery.trim()
        if (q.length < 2) {
            hubResults = emptyList()
            return@LaunchedEffect
        }
        hubJob = scope.launch {
            delay(400)
            hubSearching = true
            hubResults = runCatching { vm.repo.skillHubSearch(q) }.getOrDefault(emptyList())
            hubSearching = false
        }
    }

    val filtered = skills.filter {
        filter.isBlank() || it.name.contains(filter, true) ||
            it.description?.contains(filter, true) == true
    }

    Box(Modifier.fillMaxSize().safeDrawingPadding()) {
        Column(Modifier.fillMaxSize()) {
            PageHeader("Skills", onBack = onBack)

            LazyColumn(Modifier.fillMaxSize().padding(horizontal = space.lg)) {
                item {
                    AgencyTextField(
                        value = filter,
                        onValueChange = { filter = it },
                        placeholder = "Filter installed skills",
                    )
                    Spacer(Modifier.height(space.md))
                }

                if (filtered.isEmpty() && !loading) {
                    item {
                        EmptyState(
                            if (error.isEmpty()) "No skills" else "Couldn't load skills",
                            if (error.isEmpty()) "Install from the hub below" else error,
                        )
                    }
                } else {
                    items(filtered, key = { it.name }) { skill ->
                        AgencyRow(
                            title = skill.name,
                            subtitle = skill.description ?: skill.category ?: "",
                            meta = if (skill.usage > 0) "×${skill.usage}" else "",
                            onClick = {},
                            trailing = {
                                Switch(
                                    checked = skill.enabled,
                                    onCheckedChange = { on ->
                                        // optimistic flip, revert on failure
                                        skills = skills.map {
                                            if (it.name == skill.name) it.copy(enabled = on) else it
                                        }
                                        scope.launch {
                                            try {
                                                vm.repo.toggleSkill(skill.name, on)
                                            } catch (e: Exception) {
                                                skills = skills.map {
                                                    if (it.name == skill.name) {
                                                        it.copy(enabled = !on)
                                                    } else it
                                                }
                                                snackbar.showSnackbar(
                                                    "Toggle failed: ${e.message}",
                                                )
                                            }
                                        }
                                    },
                                    colors = SwitchDefaults.colors(
                                        checkedTrackColor = colors.primary,
                                        uncheckedTrackColor = colors.elevated,
                                    ),
                                )
                            },
                        )
                    }
                }

                item {
                    Spacer(Modifier.height(space.lg))
                    SectionKicker("Hub")
                    Spacer(Modifier.height(space.sm))
                    AgencyTextField(
                        value = hubQuery,
                        onValueChange = { hubQuery = it },
                        placeholder = "Search the skills hub",
                    )
                    if (hubSearching) {
                        Spacer(Modifier.height(space.sm))
                        CircularProgressIndicator(
                            Modifier.size(20.dp),
                            color = colors.gold, strokeWidth = 2.dp,
                        )
                    }
                    Spacer(Modifier.height(space.sm))
                }

                items(hubResults, key = { it.identifier }) { hub ->
                    AgencyRow(
                        title = hub.name,
                        subtitle = hub.description ?: hub.source,
                        meta = hub.source,
                        onClick = {},
                        trailing = {
                            TextButton(
                                enabled = !installBusy,
                                onClick = { pendingInstall = hub },
                            ) { Text("Install", color = colors.gold) }
                        },
                    )
                }

                item { Spacer(Modifier.height(space.xl)) }
            }
        }
        SnackbarHost(snackbar, Modifier.align(Alignment.BottomCenter))
    }

    pendingInstall?.let { hub ->
        AlertDialog(
            onDismissRequest = { if (!installBusy) pendingInstall = null },
            title = { Text("Install ${hub.name}?") },
            text = {
                Text("Source: ${hub.source}\n${hub.identifier}")
            },
            confirmButton = {
                TextButton(
                    enabled = !installBusy,
                    onClick = {
                        installBusy = true
                        scope.launch {
                            try {
                                vm.repo.skillHubInstall(hub.identifier)
                                snackbar.showSnackbar("Install started")
                                pendingInstall = null
                                delay(1500)
                                load()
                            } catch (e: Exception) {
                                snackbar.showSnackbar("Install failed: ${e.message}")
                            } finally {
                                installBusy = false
                            }
                        }
                    },
                ) { Text("Install") }
            },
            dismissButton = {
                TextButton(
                    onClick = { pendingInstall = null },
                    enabled = !installBusy,
                ) { Text("Cancel") }
            },
        )
    }
}
