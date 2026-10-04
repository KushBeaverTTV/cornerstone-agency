package com.cornerstone.agency.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.theme.AgencyFonts
import com.cornerstone.agency.theme.AgencyTokens
import java.util.Locale
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun AgencyScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (Modifier) -> Unit,
) {
    val colors = AgencyTokens.colors
    Scaffold(
        modifier = modifier.background(
            androidx.compose.ui.graphics.Brush.verticalGradient(
                listOf(colors.chrome, colors.chromeDeep),
            ),
        ),
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
        topBar = topBar,
        bottomBar = bottomBar,
    ) { padding ->
        content(Modifier.padding(padding))
    }
}

/** Uppercase 11sp section label — "AGENTS", "GROUPS". */
@Composable
fun SectionKicker(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.uppercase(Locale.US),
        modifier = modifier,
        color = AgencyTokens.colors.gold,
        fontSize = 11.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
        fontFamily = AgencyFonts.Sans,
        letterSpacing = 0.08.em,
    )
}

@Composable
fun AgencyCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = AgencyTokens.colors
    val shape = RoundedCornerShape(AgencyTokens.shapes.md)
    // Quiet by default: neutral hairline, no yellow edge — gold is for state, not chrome.
    Column(
        modifier = modifier
            .clip(shape)
            .border(1.dp, colors.stroke, shape)
            .background(colors.card)
            .padding(AgencyTokens.space.lg),
        content = content,
    )
}

/** Large page title in the display face, with optional trailing actions. */
@Composable
fun PageHeader(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable () -> Unit = {},
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = AgencyTokens.space.lg, end = AgencyTokens.space.xs, top = AgencyTokens.space.md, bottom = AgencyTokens.space.sm)
            .heightIn(min = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displayLarge,
            color = AgencyTokens.colors.wordmark,
            modifier = Modifier.weight(1f),
        )
        actions()
    }
}

@Composable
fun AgencyHairline(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(AgencyTokens.colors.hairline),
    )
}

/** Roster row: leading avatar slot, title + subtitle, trailing meta. */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgencyRow(
    title: String,
    subtitle: String,
    meta: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    dimmed: Boolean = false,
    onLongClick: (() -> Unit)? = null,
    leading: @Composable () -> Unit = {},
    trailing: (@Composable () -> Unit)? = null,
) {
    val colors = AgencyTokens.colors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) colors.rowActive else Color.Transparent)
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            .heightIn(min = 56.dp)
            .padding(horizontal = AgencyTokens.space.sm, vertical = AgencyTokens.space.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leading()
        Spacer(Modifier.width(AgencyTokens.space.md))
        Column(Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = if (dimmed) colors.secondary else colors.foreground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (subtitle.isNotEmpty()) {
                Spacer(Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (trailing != null) {
            Spacer(Modifier.width(AgencyTokens.space.sm))
            trailing()
        } else if (meta.isNotEmpty()) {
            Spacer(Modifier.width(AgencyTokens.space.sm))
            Text(
                text = meta,
                style = MaterialTheme.typography.labelMedium,
                color = colors.tertiary,
            )
        }
    }
}

/** Material "folder" glyph (icons-extended isn't a dependency). */
val FolderIcon: androidx.compose.ui.graphics.vector.ImageVector by lazy {
    androidx.compose.ui.graphics.vector.ImageVector.Builder(
        name = "Folder",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f,
    ).path(
        fill = androidx.compose.ui.graphics.SolidColor(Color.White),
    ) {
        moveTo(10f, 4f)
        lineTo(4f, 4f)
        curveTo(2.9f, 4f, 2f, 4.9f, 2f, 6f)
        verticalLineTo(18f)
        curveTo(2f, 19.1f, 2.9f, 20f, 4f, 20f)
        horizontalLineTo(20f)
        curveTo(21.1f, 20f, 22f, 19.1f, 22f, 18f)
        verticalLineTo(8f)
        curveTo(22f, 6.9f, 21.1f, 6f, 20f, 6f)
        horizontalLineTo(12f)
        lineTo(10f, 4f)
        close()
    }.build()
}

/** Material "visibility" eye glyph (icons-extended isn't a dependency). */
val EyeIcon: androidx.compose.ui.graphics.vector.ImageVector by lazy {    androidx.compose.ui.graphics.vector.ImageVector.Builder(
        name = "Eye",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f,
    ).path(
        fill = androidx.compose.ui.graphics.SolidColor(Color.White),
    ) {
        moveTo(12f, 4.5f)
        curveTo(7f, 4.5f, 2.73f, 7.61f, 1f, 12f)
        curveTo(2.73f, 16.39f, 7f, 19.5f, 12f, 19.5f)
        reflectiveCurveTo(21.27f, 16.39f, 23f, 12f)
        curveTo(21.27f, 7.61f, 17f, 4.5f, 12f, 4.5f)
        close()
        moveTo(12f, 17f)
        curveTo(9.24f, 17f, 7f, 14.76f, 7f, 12f)
        reflectiveCurveTo(9.24f, 7f, 12f, 7f)
        reflectiveCurveTo(17f, 9.24f, 17f, 12f)
        reflectiveCurveTo(14.76f, 17f, 12f, 17f)
        close()
        moveTo(12f, 9f)
        curveTo(10.34f, 9f, 9f, 10.34f, 9f, 12f)
        reflectiveCurveTo(10.34f, 15f, 12f, 15f)
        reflectiveCurveTo(15f, 13.66f, 15f, 12f)
        reflectiveCurveTo(13.66f, 9f, 12f, 9f)
        close()
    }.build()
}

/** Material "mic" glyph (icons-extended isn't a dependency). */
val MicIcon: androidx.compose.ui.graphics.vector.ImageVector by lazy {
    androidx.compose.ui.graphics.vector.ImageVector.Builder(
        name = "Mic",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f,
    ).path(
        fill = androidx.compose.ui.graphics.SolidColor(Color.White),
    ) {
        moveTo(12f, 14f)
        curveTo(13.66f, 14f, 14.99f, 12.66f, 14.99f, 11f)
        lineTo(15f, 5f)
        curveTo(15f, 3.34f, 13.66f, 2f, 12f, 2f)
        curveTo(10.34f, 2f, 9f, 3.34f, 9f, 5f)
        verticalLineTo(11f)
        curveTo(9f, 12.66f, 10.34f, 14f, 12f, 14f)
        close()
        moveTo(17.3f, 11f)
        curveTo(17.3f, 14f, 14.76f, 16.1f, 12f, 16.1f)
        curveTo(9.24f, 16.1f, 6.7f, 14f, 6.7f, 11f)
        lineTo(5f, 11f)
        curveTo(5f, 14.41f, 7.72f, 17.23f, 11f, 17.72f)
        lineTo(11f, 21f)
        horizontalLineToRelative(2f)
        lineTo(13f, 17.72f)
        curveTo(16.28f, 17.24f, 19f, 14.42f, 19f, 11f)
        lineTo(17.3f, 11f)
        close()
    }.build()
}

private val AVATAR_COLORS = listOf(
    Color(0xFFFCEE0A), // signal yellow
    Color(0xFF8B5CF6), // violet
    Color(0xFFF4F4F0), // bone white
    Color(0xFFCF806D), // warm
    Color(0xFF55A583), // success
    Color(0xFFE75E78), // destructive
)

/** Stable per-bot avatar color; the default profile (Kaori) always takes gold. */
fun botAvatarColor(name: String, isDefault: Boolean = false): Color {
    if (isDefault || name == "default") return AVATAR_COLORS[0]
    val idx = kotlin.math.abs(name.hashCode()) % (AVATAR_COLORS.size - 1) + 1
    return AVATAR_COLORS[idx]
}

/** "meta/muse-spark-1.3-contributor" -> "muse-spark-1.3-contributor". */
fun shortModelLabel(modelId: String): String {
    if (modelId.isBlank()) return "Select model"
    val id = modelId.substringAfterLast("/")
    // claude-sonnet-5-5 -> "Sonnet 5.5", claude-opus-4-8 -> "Opus 4.8"; anything else stays as-is.
    val m = Regex("^claude-([a-z]+)-(\\d+)(?:-(\\d{1,2}))?(?:-\\d{8})?$").matchEntire(id)
    if (m != null) {
        val family = m.groupValues[1].replaceFirstChar { it.uppercase() }
        val minor = m.groupValues[3]
        return "$family ${m.groupValues[2]}" + if (minor.isNotEmpty()) ".$minor" else ""
    }
    return id
}

/** System-prompt boilerplate makes useless previews; show something human instead. */
fun cleanPreview(raw: String): String {
    val first = raw.lineSequence().firstOrNull { it.isNotBlank() }?.trim() ?: return ""
    return when {
        first.startsWith("[IMPORTANT: You are running as a scheduled cron", true) -> "Scheduled job"
        first.startsWith("You are being used through a hands-free voice", true) -> "Voice conversation"
        else -> first
    }
}

/** True for machine-shaped titles like "UI_test:_in_your_terminal_run_exactly" — unreadable as a headline. */
fun looksMachineTitle(title: String): Boolean =
    title.length > 24 && !title.contains(' ') && title.count { it == '_' } >= 3

/** Human line for a session: the title unless it's machine-shaped, else the cleaned preview. */
fun sessionLine(title: String, preview: String): String {
    val t = title.trim()
    if (t.isNotBlank() && !looksMachineTitle(t)) return t
    val pv = cleanPreview(preview)
    if (pv.isNotBlank() && !looksMachineTitle(pv)) return pv
    return t.ifBlank { pv }.replace('_', ' ')
}

/** "just now" / "4m" / "2h" / "3d" / absolute date for old timestamps. */
fun relativeTime(epochSeconds: Double): String {
    if (epochSeconds <= 0.0) return ""
    val now = System.currentTimeMillis() / 1000.0
    val diff = (now - epochSeconds).toLong()
    return when {
        diff < 60 -> "just now"
        diff < 3600 -> "${diff / 60}m"
        diff < 86400 -> "${diff / 3600}h"
        diff < 86400 * 7 -> "${diff / 86400}d"
        else -> java.text.SimpleDateFormat("MMM d", Locale.US)
            .format(java.util.Date((epochSeconds * 1000).toLong()))
    }
}

/** Circle avatar with the bot's initial on a per-bot fill color. */
@Composable
fun BotAvatar(
    name: String,
    color: Color,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 34.dp,
) {
    val letter = name.trim().firstOrNull()?.uppercaseChar()?.toString() ?: "?"
    val onFill = if (color.luminance() < 0.43f) Color.White else Color(0xFF0D0D14)
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = letter,
            color = onFill,
            fontSize = (size.value * 0.44f).sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontFamily = AgencyFonts.Sans,
        )
    }
}

/**
 * The agent's face: the living keystone mark on a soft disc, tinted per bot.
 * Replaces the initial-in-a-circle wherever a bot is the subject (rows, headers).
 */
@Composable
fun BotMark(
    color: Color,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 48.dp,
    state: KeystoneState = KeystoneState.IDLE,
    contentDescription: String? = null,
) {
    Box(
        modifier
            .size(size)
            .clip(CircleShape)
            .background(color.copy(alpha = 0.10f))
            .then(
                if (contentDescription != null) {
                    Modifier.semantics { this.contentDescription = contentDescription }
                } else Modifier,
            ),
        contentAlignment = Alignment.Center,
    ) {
        // The arch's visual mass sits low in its box; nudge it up so it reads centred.
        KeystoneMark(
            state = state, size = size * 0.80f, color = color,
            modifier = Modifier.offset(y = -(size * 0.07f)),
        )
    }
}

@Composable
fun PrimaryButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
) {
    val colors = AgencyTokens.colors
    Button(
        onClick = onClick,
        modifier = modifier.height(AgencyTokens.space.touch),
        enabled = enabled && !loading,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.primary,
            contentColor = colors.onPrimary,
            disabledContainerColor = colors.primarySoft,
            disabledContentColor = colors.tertiary,
        ),
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                color = colors.onPrimary,
                strokeWidth = 2.dp,
            )
        } else {
            Text(label, style = MaterialTheme.typography.labelLarge)
        }
    }
}

/** Quiet text field: hairline underline instead of a filled box. */
@Composable
fun AgencyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    singleLine: Boolean = true,
    secret: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val colors = AgencyTokens.colors
    Column(modifier) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AgencyTokens.space.xs),
            singleLine = singleLine,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = colors.foreground),
            cursorBrush = SolidColor(colors.gold),
            visualTransformation = if (secret) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            decorationBox = { inner ->
                Box {
                    if (value.isEmpty()) {
                        Text(
                            placeholder,
                            style = MaterialTheme.typography.bodyLarge,
                            color = colors.tertiary,
                        )
                    }
                    inner()
                }
            },
        )
        AgencyHairline()
    }
}

/** Thin top line shown when the dashboard is unreachable. */
@Composable
fun StatusBanner(message: String, modifier: Modifier = Modifier) {
    val colors = AgencyTokens.colors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.sidebar)
            .padding(horizontal = AgencyTokens.space.lg, vertical = AgencyTokens.space.xs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.labelMedium,
            color = colors.warm,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Bottom sheet listing every provider's models (/api/model/options), grouped by
 * provider with a check on the current selection. [onPick] gets (providerSlug, modelId).
 */
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ModelPickerSheet(
    providers: List<com.cornerstone.agency.data.ModelProvider>,
    currentProvider: String,
    currentModel: String,
    loading: Boolean,
    onPick: (providerSlug: String, modelId: String) -> Unit,
    onDismiss: () -> Unit,
) {
    val colors = AgencyTokens.colors
    var filter by remember { mutableStateOf("") }
    androidx.compose.material3.ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = colors.elevated,
    ) {
        Column(Modifier.fillMaxWidth().padding(horizontal = AgencyTokens.space.lg)) {
            Text(
                "Model",
                style = MaterialTheme.typography.titleLarge,
                color = colors.foreground,
            )
            Spacer(Modifier.height(AgencyTokens.space.sm))
            AgencyTextField(
                value = filter,
                onValueChange = { filter = it },
                placeholder = "Search models",
            )
            Spacer(Modifier.height(AgencyTokens.space.sm))
        }
        if (loading) {
            Row(
                Modifier.fillMaxWidth().padding(AgencyTokens.space.lg),
                horizontalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = colors.gold, strokeWidth = 2.dp,
                )
            }
        } else {
            androidx.compose.foundation.lazy.LazyColumn(
                Modifier.fillMaxWidth().height(420.dp),
            ) {
                providers.forEach { provider ->
                    val models = provider.models.filter {
                        filter.isBlank() || it.contains(filter, ignoreCase = true)
                    }
                    if (models.isEmpty()) return@forEach
                    item(key = "prov-${provider.slug}") {
                        SectionKicker(
                            provider.name,
                            modifier = Modifier.padding(
                                horizontal = AgencyTokens.space.lg,
                                vertical = AgencyTokens.space.xs,
                            ),
                        )
                    }
                    items(models.size) { i ->
                        val model = models[i]
                        val selected =
                            provider.slug == currentProvider && model.equals(currentModel, true)
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable { onPick(provider.slug, model) }
                                .padding(
                                    horizontal = AgencyTokens.space.lg,
                                    vertical = AgencyTokens.space.sm,
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            androidx.compose.foundation.layout.Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(androidx.compose.foundation.shape.CircleShape)
                                    .background(if (selected) colors.gold else colors.tertiary),
                            )
                            Spacer(Modifier.width(AgencyTokens.space.sm))
                            Column(Modifier.weight(1f)) {
                                Text(
                                    shortModelLabel(model),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (selected) colors.gold else colors.foreground,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Text(
                                    provider.name,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = colors.tertiary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            if (selected) {
                                Text("✓", color = colors.gold)
                            }
                        }
                    }
                }
            }
        }
    }
}

/** Centered empty-state used by tabs that are not built yet. */
@Composable
fun EmptyState(title: String, hint: String, modifier: Modifier = Modifier) {
    val colors = AgencyTokens.colors
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(title, style = MaterialTheme.typography.displaySmall, color = colors.wordmark,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        Spacer(Modifier.height(AgencyTokens.space.sm))
        Text(hint, style = MaterialTheme.typography.bodyMedium, color = colors.secondary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}
