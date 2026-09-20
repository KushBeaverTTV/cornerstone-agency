package com.cornerstone.agency.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.R

// Palette decoded from the Hermes v1.0.5 prototype (ai.nous.hermes ui/theme).
val AgencyChrome = Color(0xFF0D0D0E)
val AgencySidebar = Color(0xFF0A0A0B)
val AgencyCard = Color(0xFF161618)
val AgencyElevated = Color(0xFF1C1C1F)
val AgencyPrimary = Color(0xFF0053FD)
val AgencyPrimarySoft = Color(0xFF1A2A55)
val AgencyRowActive = Color(0x140053FD)
val AgencyGold = Color(0xFFFFD24A)
val AgencyForeground = Color(0xF0F4F4F5)
val AgencySecondaryText = Color(0xBDB1B3B8)
val AgencyTertiaryText = Color(0x8A9A9B9E)
val AgencyStroke = Color(0x1FFFFFFF)
val AgencyDestructive = Color(0xFFE75E78)
val AgencySuccess = Color(0xFF55A583)
val AgencyWarm = Color(0xFFCF806D)
val AgencyDefaultViolet = Color(0xFF8B5CF6)
val AgencyWordmark = AgencyForeground.copy(alpha = 0.9f)
val AgencyHairline = AgencyStroke
val AgencySelection = AgencyGold.copy(alpha = 0.38f)

data class AgencyColors(
    val chrome: Color = AgencyChrome,
    val sidebar: Color = AgencySidebar,
    val card: Color = AgencyCard,
    val elevated: Color = AgencyElevated,
    val primary: Color = AgencyPrimary,
    val primarySoft: Color = AgencyPrimarySoft,
    val rowActive: Color = AgencyRowActive,
    val gold: Color = AgencyGold,
    val foreground: Color = AgencyForeground,
    val secondary: Color = AgencySecondaryText,
    val tertiary: Color = AgencyTertiaryText,
    val stroke: Color = AgencyStroke,
    val destructive: Color = AgencyDestructive,
    val success: Color = AgencySuccess,
    val warm: Color = AgencyWarm,
    val defaultViolet: Color = AgencyDefaultViolet,
    val wordmark: Color = AgencyWordmark,
    val hairline: Color = AgencyHairline,
    val selection: Color = AgencySelection,
)

data class AgencySpacing(
    val xxs: androidx.compose.ui.unit.Dp = 2.dp,
    val xs: androidx.compose.ui.unit.Dp = 4.dp,
    val sm: androidx.compose.ui.unit.Dp = 8.dp,
    val md: androidx.compose.ui.unit.Dp = 12.dp,
    val lg: androidx.compose.ui.unit.Dp = 16.dp,
    val xl: androidx.compose.ui.unit.Dp = 24.dp,
    val touch: androidx.compose.ui.unit.Dp = 48.dp,
)

object AgencyFonts {
    val Collapse = FontFamily(
        Font(R.font.collapse_regular, FontWeight.Normal),
        Font(R.font.collapse_bold, FontWeight.Bold),
    )
    val Sans = FontFamily.SansSerif
}

private val AgencyTypography = Typography(
    displayLarge = TextStyle(
        color = AgencyForeground, fontSize = 28.sp, fontWeight = FontWeight.Bold,
        fontFamily = AgencyFonts.Collapse, letterSpacing = 0.08.em, lineHeight = 26.sp,
    ),
    displayMedium = TextStyle(
        color = AgencyForeground, fontSize = 22.sp, fontWeight = FontWeight.Bold,
        fontFamily = AgencyFonts.Collapse, letterSpacing = 0.06.em, lineHeight = 22.sp,
    ),
    displaySmall = TextStyle(
        color = AgencyForeground, fontSize = 20.sp, fontWeight = FontWeight.Bold,
        fontFamily = AgencyFonts.Collapse, letterSpacing = 0.04.em, lineHeight = 22.sp,
    ),
    headlineSmall = TextStyle(
        color = AgencyForeground, fontSize = 18.sp, fontWeight = FontWeight.Bold,
        fontFamily = AgencyFonts.Collapse, letterSpacing = (-0.02).em, lineHeight = 22.sp,
    ),
    titleLarge = TextStyle(
        color = AgencyForeground, fontSize = 18.sp, fontWeight = FontWeight.Bold,
        fontFamily = AgencyFonts.Collapse, letterSpacing = (-0.02).em, lineHeight = 22.sp,
    ),
    titleMedium = TextStyle(
        color = AgencyForeground, fontSize = 14.sp, fontWeight = FontWeight.Medium,
        fontFamily = AgencyFonts.Sans, letterSpacing = (-0.01).em, lineHeight = 18.sp,
    ),
    titleSmall = TextStyle(
        color = AgencyForeground, fontSize = 13.sp, fontWeight = FontWeight.Medium,
        fontFamily = AgencyFonts.Sans, lineHeight = 17.sp,
    ),
    bodyLarge = TextStyle(
        color = AgencyForeground, fontSize = 14.sp, fontWeight = FontWeight.Normal,
        fontFamily = AgencyFonts.Sans, lineHeight = 20.sp,
    ),
    bodyMedium = TextStyle(
        color = AgencyForeground, fontSize = 13.sp, fontWeight = FontWeight.Normal,
        fontFamily = AgencyFonts.Sans, lineHeight = 18.sp,
    ),
    bodySmall = TextStyle(
        color = AgencySecondaryText, fontSize = 12.sp, fontWeight = FontWeight.Normal,
        fontFamily = AgencyFonts.Sans, lineHeight = 16.sp,
    ),
    labelLarge = TextStyle(
        color = AgencyForeground, fontSize = 13.sp, fontWeight = FontWeight.Medium,
        fontFamily = AgencyFonts.Sans, lineHeight = 16.sp,
    ),
    labelMedium = TextStyle(
        color = AgencySecondaryText, fontSize = 12.sp, fontWeight = FontWeight.Medium,
        fontFamily = AgencyFonts.Sans, lineHeight = 16.sp,
    ),
    labelSmall = TextStyle(
        color = AgencyTertiaryText, fontSize = 11.sp, fontWeight = FontWeight.Medium,
        fontFamily = AgencyFonts.Sans, letterSpacing = 0.06.em, lineHeight = 14.sp,
    ),
)

val LocalAgencyColors = staticCompositionLocalOf { AgencyColors() }
val LocalAgencySpacing = staticCompositionLocalOf { AgencySpacing() }

object AgencyTokens {
    val colors: AgencyColors
        @Composable get() = LocalAgencyColors.current
    val space: AgencySpacing
        @Composable get() = LocalAgencySpacing.current
}

@Composable
fun AgencyTheme(content: @Composable () -> Unit) {
    val colors = AgencyColors()
    CompositionLocalProvider(
        LocalAgencyColors provides colors,
        LocalAgencySpacing provides AgencySpacing(),
    ) {
        MaterialTheme(
            colorScheme = darkColorScheme(
                primary = colors.primary,
                onPrimary = Color.White,
                primaryContainer = colors.primarySoft,
                onPrimaryContainer = colors.foreground,
                secondary = colors.secondary,
                onSecondary = colors.chrome,
                background = colors.chrome,
                onBackground = colors.foreground,
                surface = colors.chrome,
                onSurface = colors.foreground,
                surfaceVariant = colors.card,
                onSurfaceVariant = colors.secondary,
                outline = colors.stroke,
                error = colors.destructive,
                onError = Color.White,
            ),
            typography = AgencyTypography,
            content = content,
        )
    }
}
