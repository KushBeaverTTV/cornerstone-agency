package com.cornerstone.agency.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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

@Composable
fun AgencyScaffold(
    modifier: Modifier = Modifier,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (Modifier) -> Unit,
) {
    val colors = AgencyTokens.colors
    Scaffold(
        modifier = modifier,
        containerColor = colors.chrome,
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
        color = AgencyTokens.colors.tertiary,
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
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(colors.card)
            .padding(AgencyTokens.space.md),
        content = content,
    )
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

/** Material "visibility" eye glyph (icons-extended isn't a dependency). */
val EyeIcon: androidx.compose.ui.graphics.vector.ImageVector by lazy {
    androidx.compose.ui.graphics.vector.ImageVector.Builder(
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

private val AVATAR_COLORS = listOf(
    Color(0xFFFFD24A), // gold
    Color(0xFF8B5CF6), // violet
    Color(0xFF0053FD), // primary
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
            contentColor = Color.White,
            disabledContainerColor = colors.primarySoft,
            disabledContentColor = colors.tertiary,
        ),
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                color = Color.White,
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
                            Text(
                                model,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (selected) colors.gold else colors.foreground,
                                modifier = Modifier.weight(1f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
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
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(title, style = MaterialTheme.typography.displaySmall, color = colors.wordmark)
        Spacer(Modifier.height(AgencyTokens.space.sm))
        Text(hint, style = MaterialTheme.typography.bodyMedium, color = colors.tertiary)
    }
}
