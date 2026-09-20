package com.cornerstone.agency.ui.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cornerstone.agency.data.ModelProvider
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.ModelPickerSheet
import com.cornerstone.agency.ui.components.PrimaryButton
import com.cornerstone.agency.ui.components.SectionKicker
import kotlinx.coroutines.launch

private val SLUG = Regex("^[a-z0-9-]+$")

/**
 * Create-a-bot form. POST /api/profiles takes name/description/provider/model
 * (no soul field, verified by probe) — the soul template is applied in a second
 * PUT /api/profiles/{name}/soul {content} once the profile exists.
 * Display name has no server field; it seeds the soul template only.
 */
@Composable
fun NewAgentScreen(
    vm: AppViewModel,
    onBack: () -> Unit,
    onCreated: (String) -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val ui by vm.ui.collectAsState()
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var soul by remember { mutableStateOf("") }
    var soulTouched by remember { mutableStateOf(false) }

    // Default model selection = the fleet default profile's assignment.
    val defaultProfile = ui.profiles.find { it.isDefault || it.name == "default" }
    var provider by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    if (provider.isEmpty() && defaultProfile != null) {
        provider = defaultProfile.provider
        model = defaultProfile.model
    }

    var modelSheet by remember { mutableStateOf(false) }
    var providers by remember { mutableStateOf<List<ModelProvider>>(emptyList()) }
    var providersLoading by remember { mutableStateOf(false) }

    var creating by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }

    val nameError = when {
        name.isBlank() -> "Required"
        !SLUG.matches(name) -> "Lowercase letters, digits and dashes only"
        ui.profiles.any { it.name == name } -> "An agent with this name already exists"
        else -> ""
    }

    Column(Modifier.fillMaxSize().safeDrawingPadding().imePadding()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = space.sm, vertical = space.xs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = colors.foreground)
            }
            Text(
                "New agent",
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

            SectionKicker("Name")
            Spacer(Modifier.height(space.xs))
            AgencyTextField(
                value = name,
                onValueChange = {
                    name = it.lowercase().filter { c -> c.isLetterOrDigit() || c == '-' }
                },
                placeholder = "e.g. scout-2",
            )
            if (name.isNotEmpty() && nameError.isNotEmpty()) {
                Text(
                    nameError,
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.destructive,
                    modifier = Modifier.padding(top = space.xxs),
                )
            }
            Spacer(Modifier.height(space.md))

            SectionKicker("Display name")
            Spacer(Modifier.height(space.xs))
            AgencyTextField(
                value = displayName,
                onValueChange = { displayName = it },
                placeholder = "e.g. Scout",
            )
            Spacer(Modifier.height(space.md))

            SectionKicker("Description")
            Spacer(Modifier.height(space.xs))
            AgencyTextField(
                value = description,
                onValueChange = { description = it },
                placeholder = "What this agent does",
            )
            Spacer(Modifier.height(space.md))

            SectionKicker("Model")
            Spacer(Modifier.height(space.xs))
            AgencyRow(
                title = model.ifBlank { "Choose a model" },
                subtitle = provider,
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
            Spacer(Modifier.height(space.md))

            SectionKicker("Soul")
            Spacer(Modifier.height(space.xs))
            AgencyTextField(
                value = soul,
                onValueChange = {
                    soul = it
                    soulTouched = true
                },
                placeholder = "You are…",
                singleLine = false,
            )
            Spacer(Modifier.height(space.xl))

            if (error.isNotEmpty()) {
                Text(
                    error,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.destructive,
                )
                Spacer(Modifier.height(space.sm))
            }

            PrimaryButton(
                label = "Create agent",
                loading = creating,
                enabled = nameError.isEmpty() && name.isNotBlank(),
                onClick = {
                    creating = true
                    error = ""
                    val soulBody = if (soulTouched || soul.isNotBlank()) soul else {
                        val dn = displayName.ifBlank { name }
                        "You are $dn. You work for the Cornerstone agency. " +
                            "Be concise, capable, and warm."
                    }
                    scope.launch {
                        try {
                            val res = vm.repo.createProfile(name, description, provider, model)
                            // POST has no soul field — apply it via the soul endpoint.
                            if (soulBody.isNotBlank()) {
                                runCatching { vm.repo.updateSoul(name, soulBody) }
                            }
                            // If the POST couldn't set the model, try the dedicated PUT.
                            if (provider.isNotBlank() && model.isNotBlank() && !res.modelSet) {
                                runCatching { vm.repo.updateModel(name, provider, model) }
                            }
                            vm.refreshProfiles()
                            onCreated(name)
                        } catch (e: Exception) {
                            error = e.message ?: "Create failed"
                        } finally {
                            creating = false
                        }
                    }
                },
            )
            Spacer(Modifier.height(space.xl))
        }
    }

    if (modelSheet) {
        ModelPickerSheet(
            providers = providers,
            currentProvider = provider,
            currentModel = model,
            loading = providersLoading,
            onPick = { slug, m ->
                provider = slug
                model = m
                modelSheet = false
            },
            onDismiss = { modelSheet = false },
        )
    }
}
