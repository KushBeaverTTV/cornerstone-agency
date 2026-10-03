package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.Goal
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyCard
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.relativeTime

private val GOAL_CATEGORIES = listOf("Health", "Relationships", "Finance", "Work", "Personal")

@Composable
fun GoalsScreen(vm: AppViewModel) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val state by vm.companion.state.collectAsState()

    var creating by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Personal") }
    var categoryMenu by remember { mutableStateOf(false) }
    var viewing by remember { mutableStateOf<Goal?>(null) }

    val open = state.goals.filter { !it.done }
    val done = state.goals.filter { it.done }

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.padding(horizontal = space.lg)) {
            Spacer(Modifier.height(space.lg))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Goals",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.weight(1f),
                )
                IconButton(onClick = {
                    title = ""
                    detail = ""
                    category = "Personal"
                    creating = true
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "New goal", tint = colors.gold)
                }
            }
            Spacer(Modifier.height(space.md))
        }
        if (state.goals.isEmpty()) {
            EmptyState(
                "No goals yet",
                "Create your first goal — something you're working toward.",
                modifier = Modifier.fillMaxWidth(),
            )
        } else {
            LazyColumn(Modifier.fillMaxSize()) {
                if (open.isNotEmpty()) {
                    item {
                        SectionKicker(
                            "Tracking",
                            modifier = Modifier.padding(horizontal = space.lg),
                        )
                        Spacer(Modifier.height(space.xs))
                    }
                    items(open, key = { it.id }) { goal ->
                        Column(Modifier.padding(horizontal = space.lg)) {
                            GoalRow(
                                goal = goal,
                                onToggle = { vm.companion.toggleGoal(goal.id) },
                                onOpen = { viewing = goal },
                            )
                            Spacer(Modifier.height(space.sm))
                        }
                    }
                    item { Spacer(Modifier.height(space.md)) }
                }
                if (done.isNotEmpty()) {
                    item {
                        SectionKicker(
                            "Done",
                            modifier = Modifier.padding(horizontal = space.lg),
                        )
                        Spacer(Modifier.height(space.xs))
                    }
                    items(done, key = { it.id }) { goal ->
                        Column(Modifier.padding(horizontal = space.lg)) {
                            GoalRow(
                                goal = goal,
                                onToggle = { vm.companion.toggleGoal(goal.id) },
                                onOpen = { viewing = goal },
                            )
                            Spacer(Modifier.height(space.sm))
                        }
                    }
                }
            }
        }
    }

    if (creating) {
        AlertDialog(
            onDismissRequest = { creating = false },
            title = { Text("New goal") },
            text = {
                Column {
                    AgencyTextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = "Goal title",
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(Modifier.height(space.sm))
                    AgencyTextField(
                        value = detail,
                        onValueChange = { detail = it },
                        placeholder = "Details (optional)",
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth().height(110.dp),
                    )
                    Spacer(Modifier.height(space.sm))
                    Box {
                        TextButton(onClick = { categoryMenu = true }) {
                            Text(category, color = colors.gold)
                        }
                        DropdownMenu(
                            expanded = categoryMenu,
                            onDismissRequest = { categoryMenu = false },
                        ) {
                            GOAL_CATEGORIES.forEach { c ->
                                DropdownMenuItem(
                                    text = { Text(c) },
                                    onClick = {
                                        category = c
                                        categoryMenu = false
                                    },
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    enabled = title.isNotBlank(),
                    onClick = {
                        vm.companion.addGoal(title, detail, category)
                        creating = false
                    },
                ) { Text("Create", color = colors.gold) }
            },
            dismissButton = {
                TextButton(onClick = { creating = false }) { Text("Cancel") }
            },
        )
    }

    viewing?.let { goal ->
        AlertDialog(
            onDismissRequest = { viewing = null },
            title = { Text(goal.title) },
            text = {
                Column {
                    if (goal.detail.isNotBlank()) {
                        Text(
                            goal.detail,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.secondary,
                        )
                        Spacer(Modifier.height(space.sm))
                    }
                    Text(
                        "${goal.category} · started ${relativeTime(goal.createdAt / 1000.0)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.tertiary,
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { viewing = null }) { Text("Close") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        vm.companion.deleteGoal(goal.id)
                        viewing = null
                    },
                ) { Text("Delete", color = colors.destructive) }
            },
        )
    }
}

@Composable
private fun GoalRow(goal: Goal, onToggle: () -> Unit, onOpen: () -> Unit) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    AgencyCard(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = goal.done,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(
                    checkedColor = colors.gold,
                    checkmarkColor = colors.onPrimary,
                    uncheckedColor = colors.tertiary,
                ),
            )
            Spacer(Modifier.width(space.sm))
            Column(
                Modifier
                    .weight(1f)
                    .clickable { onOpen() }
                    .padding(vertical = space.xs),
            ) {
                Text(
                    goal.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (goal.done) colors.tertiary else colors.foreground,
                    textDecoration = if (goal.done) TextDecoration.LineThrough else null,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(space.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        goal.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.gold,
                    )
                    Text(
                        relativeTime(goal.createdAt / 1000.0),
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.tertiary,
                    )
                }
                if (goal.detail.isNotBlank()) {
                    Text(
                        goal.detail,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.secondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
