package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.Goal
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.PageHeader
import com.cornerstone.agency.ui.components.relativeTime
import com.cornerstone.agency.ui.isCompactHeight

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
    // Muse groups goals under coloured category headers; order follows the fixed category list.
    val byCategory = remember(open) {
        open.groupBy { it.category }.toList().sortedBy { (c, _) ->
            GOAL_CATEGORIES.indexOf(c).let { if (it < 0) Int.MAX_VALUE else it }
        }
    }

    Column(Modifier.fillMaxSize()) {
        PageHeader("Goals") {
            IconButton(onClick = {
                title = ""
                detail = ""
                category = "Personal"
                creating = true
            }) {
                Icon(Icons.Filled.Add, contentDescription = "New goal", tint = colors.foreground)
            }
        }
        if (state.goals.isEmpty()) {
            EmptyState(
                "No goals yet",
                "Create your first goal — something you're working toward.",
                modifier = Modifier.fillMaxWidth(),
            )
        } else {
            LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = space.xl)) {
                byCategory.forEach { (cat, goals) ->
                    item(key = "cat-$cat") {
                        CategoryHeader(cat, goals.size, categoryColor(cat))
                    }
                    items(goals, key = { it.id }) { goal ->
                        GoalRow(
                            goal = goal,
                            accent = categoryColor(goal.category),
                            onToggle = { vm.companion.toggleGoal(goal.id) },
                            onOpen = { viewing = goal },
                        )
                    }
                }
                if (done.isNotEmpty()) {
                    item(key = "done-head") {
                        CategoryHeader("Done", done.size, colors.tertiary)
                    }
                    items(done, key = { it.id }) { goal ->
                        GoalRow(
                            goal = goal,
                            accent = colors.tertiary,
                            onToggle = { vm.companion.toggleGoal(goal.id) },
                            onOpen = { viewing = goal },
                        )
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

/** Category tint: gold is Work (the agency's business); the rest stay quiet and distinct. */
@Composable
private fun categoryColor(category: String): Color {
    val colors = AgencyTokens.colors
    return when (category) {
        "Work" -> colors.gold
        "Health" -> colors.success
        "Finance" -> Color(0xFF7FB3FF)
        "Relationships" -> colors.warm
        else -> Color(0xFFF4F4F0)
    }
}

@Composable
private fun CategoryHeader(name: String, count: Int, accent: Color) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    Row(
        Modifier.padding(start = space.lg, end = space.lg, top = space.lg, bottom = space.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(8.dp).clip(CircleShape).background(accent))
        Spacer(Modifier.width(space.sm))
        Text(
            name.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = colors.secondary,
            modifier = Modifier.weight(1f),
        )
        Text(count.toString(), style = MaterialTheme.typography.labelSmall, color = colors.tertiary)
    }
}

@Composable
private fun GoalRow(goal: Goal, accent: Color, onToggle: () -> Unit, onOpen: () -> Unit) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = space.sm)
            .clip(RoundedCornerShape(AgencyTokens.shapes.md))
            .clickable(onClick = onOpen)
            .heightIn(min = 56.dp)
            .padding(start = space.xs, end = space.md, top = space.xs, bottom = space.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = goal.done,
            onCheckedChange = { onToggle() },
            colors = CheckboxDefaults.colors(
                checkedColor = accent,
                checkmarkColor = colors.onPrimary,
                uncheckedColor = colors.tertiary,
            ),
        )
        Spacer(Modifier.width(space.xs))
        Column(Modifier.weight(1f).padding(vertical = space.xs)) {
            Text(
                goal.title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (goal.done) colors.tertiary else colors.foreground,
                textDecoration = if (goal.done) TextDecoration.LineThrough else null,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            if (goal.detail.isNotBlank()) {
                Spacer(Modifier.height(2.dp))
                Text(
                    goal.detail,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.secondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Spacer(Modifier.width(space.sm))
        Text(
            relativeTime(goal.createdAt / 1000.0),
            style = MaterialTheme.typography.labelSmall,
            color = colors.tertiary,
        )
    }
}
