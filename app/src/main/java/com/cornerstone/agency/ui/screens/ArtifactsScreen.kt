package com.cornerstone.agency.ui.screens

import android.content.ContentResolver
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.ArtifactEntry
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.PageHeader
import com.cornerstone.agency.ui.components.relativeTime
import com.cornerstone.agency.ui.isCompactHeight
import androidx.compose.foundation.border
import kotlinx.coroutines.launch

private fun displayNameOf(resolver: ContentResolver, uri: Uri): String? {
    var cursor: Cursor? = null
    return try {
        cursor = resolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
        if (cursor != null && cursor.moveToFirst()) cursor.getString(0) else null
    } catch (e: Exception) {
        null
    } finally {
        cursor?.close()
    }
}

private fun formatSize(bytes: Long): String = when {
    bytes >= 1_000_000 -> "%.1f MB".format(bytes / 1_000_000.0)
    bytes >= 1_000 -> "%.0f KB".format(bytes / 1_000.0)
    else -> "$bytes B"
}

@Composable
fun ArtifactsScreen(vm: AppViewModel) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state by vm.companion.state.collectAsState()

    var busy by remember { mutableStateOf(false) }
    var viewer by remember { mutableStateOf<ArtifactEntry?>(null) }
    var confirmDelete by remember { mutableStateOf<ArtifactEntry?>(null) }

    val picker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult
        busy = true
        scope.launch {
            try {
                val bytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
                if (bytes != null) {
                    val name = displayNameOf(context.contentResolver, uri) ?: "artifact"
                    val mime = context.contentResolver.getType(uri) ?: ""
                    vm.companion.importArtifact(name, mime, bytes)
                }
            } finally {
                busy = false
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        if (!isCompactHeight()) {
            PageHeader("Files") {
                if (busy) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(horizontal = 12.dp).size(20.dp),
                        color = colors.gold, strokeWidth = 2.dp,
                    )
                } else {
                    IconButton(onClick = { picker.launch("*/*") }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add file", tint = colors.foreground)
                    }
                }
            }
        }
        if (state.artifacts.isEmpty()) {
            EmptyState(
                "Nothing saved yet",
                "Keep files with your agent — documents, lists, images, anything. Tap + to add one.",
                modifier = Modifier.fillMaxWidth(),
            )
        } else {
            LazyColumn(Modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = space.xl)) {
                items(state.artifacts, key = { it.id }) { entry ->
                    ArtifactRow(entry, onOpen = { viewer = entry })
                }
            }
        }
    }

    viewer?.let { entry ->
        AlertDialog(
            onDismissRequest = { viewer = null },
            title = {
                Text(
                    entry.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            text = { ArtifactPreview(vm, entry) },
            confirmButton = {
                TextButton(onClick = { viewer = null }) { Text("Close") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        confirmDelete = entry
                        viewer = null
                    },
                ) { Text("Delete", color = colors.destructive) }
            },
        )
    }

    confirmDelete?.let { entry ->
        AlertDialog(
            onDismissRequest = { confirmDelete = null },
            title = { Text("Delete artifact?") },
            text = { Text("\"${entry.name}\" will be removed from this device.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        vm.companion.deleteArtifact(entry.id)
                        confirmDelete = null
                    },
                ) { Text("Delete", color = colors.destructive) }
            },
            dismissButton = {
                TextButton(onClick = { confirmDelete = null }) { Text("Cancel") }
            },
        )
    }
}

@Composable
private fun ArtifactRow(entry: ArtifactEntry, onOpen: () -> Unit) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val badge = entry.name.substringAfterLast('.', "")
        .uppercase().take(4).ifBlank { "FILE" }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = space.sm)
            .clip(RoundedCornerShape(AgencyTokens.shapes.md))
            .clickable { onOpen() }
            .heightIn(min = 64.dp)
            .padding(horizontal = space.sm, vertical = space.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(AgencyTokens.shapes.sm))
                .background(colors.card)
                .border(1.dp, colors.stroke, RoundedCornerShape(AgencyTokens.shapes.sm)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                badge,
                style = MaterialTheme.typography.labelSmall,
                color = colors.gold,
                maxLines = 1,
            )
        }
        Spacer(Modifier.width(space.md))
        Column(Modifier.weight(1f)) {
            Text(
                entry.name,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.foreground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                "${formatSize(entry.size)} · ${relativeTime(entry.addedAt / 1000.0)}",
                style = MaterialTheme.typography.labelMedium,
                color = colors.tertiary,
            )
        }
    }
}

@Composable
private fun ArtifactPreview(vm: AppViewModel, entry: ArtifactEntry) {
    val colors = AgencyTokens.colors
    val isImage = entry.mimeType.startsWith("image/")
    val isText = entry.mimeType.startsWith("text/") ||
        entry.name.substringAfterLast('.', "").lowercase() in
        setOf("md", "txt", "json", "kt", "py", "js", "ts", "yaml", "yml", "csv", "log")

    when {
        isImage -> {
            val bitmap = remember(entry.id) {
                vm.companion.artifactFile(entry)?.absolutePath?.let { path ->
                    val opts = BitmapFactory.Options().apply { inSampleSize = 2 }
                    runCatching { BitmapFactory.decodeFile(path, opts)?.asImageBitmap() }
                        .getOrNull()
                }
            }
            if (bitmap != null) {
                Image(
                    bitmap,
                    contentDescription = entry.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 380.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )
            } else {
                Text(
                    "Couldn't load this image.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.secondary,
                )
            }
        }

        isText -> {
            val text = remember(entry.id) {
                vm.companion.artifactFile(entry)
                    ?.takeIf { it.length() < 300_000 }
                    ?.let { runCatching { it.readText() }.getOrNull() }
            }
            if (text != null) {
                Text(
                    text,
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 380.dp)
                        .verticalScroll(rememberScrollState()),
                )
            } else {
                Text(
                    "File is too large to preview.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.secondary,
                )
            }
        }

        else -> {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "No preview for this file type.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.secondary,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "${formatSize(entry.size)} · saved on this device",
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.tertiary,
                )
            }
        }
    }
}
