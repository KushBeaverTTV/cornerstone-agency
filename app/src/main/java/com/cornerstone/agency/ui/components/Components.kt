package com.cornerstone.agency.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.luminance
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
@Composable
fun AgencyRow(
    title: String,
    subtitle: String,
    meta: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    dimmed: Boolean = false,
    leading: @Composable () -> Unit = {},
) {
    val colors = AgencyTokens.colors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) colors.rowActive else Color.Transparent)
            .clickable(onClick = onClick)
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
        if (meta.isNotEmpty()) {
            Spacer(Modifier.width(AgencyTokens.space.sm))
            Text(
                text = meta,
                style = MaterialTheme.typography.labelMedium,
                color = colors.tertiary,
            )
        }
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
