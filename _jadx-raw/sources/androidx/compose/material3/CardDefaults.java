package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.ElevatedCardTokens;
import androidx.compose.material3.tokens.FilledCardTokens;
import androidx.compose.material3.tokens.OutlinedCardTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0014\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\u0015J:\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJN\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020 2\b\b\u0002\u0010$\u001a\u00020 2\b\b\u0002\u0010%\u001a\u00020 H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\r\u0010(\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\u0015J:\u0010(\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010\u001cJN\u0010*\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020 2\b\b\u0002\u0010$\u001a\u00020 2\b\b\u0002\u0010%\u001a\u00020 H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010'J\u0017\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020/H\u0007¢\u0006\u0002\u00100J\r\u00101\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\u0015J:\u00101\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u0010\u001cJN\u00103\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020 2\b\b\u0002\u0010$\u001a\u00020 2\b\b\u0002\u0010%\u001a\u00020 H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u0010'R\u0011\u0010\u0003\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0018\u0010\u000b\u001a\u00020\f*\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\f*\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0018\u0010\u0012\u001a\u00020\f*\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00065"}, d2 = {"Landroidx/compose/material3/CardDefaults;", "", "()V", "elevatedShape", "Landroidx/compose/ui/graphics/Shape;", "getElevatedShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "outlinedShape", "getOutlinedShape", "shape", "getShape", "defaultCardColors", "Landroidx/compose/material3/CardColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultCardColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/CardColors;", "defaultElevatedCardColors", "getDefaultElevatedCardColors$material3_release", "defaultOutlinedCardColors", "getDefaultOutlinedCardColors$material3_release", "cardColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/CardColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "cardColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CardColors;", "cardElevation", "Landroidx/compose/material3/CardElevation;", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "cardElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CardElevation;", "elevatedCardColors", "elevatedCardColors-ro_MJ88", "elevatedCardElevation", "elevatedCardElevation-aqJV_2Y", "outlinedCardBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "(ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "outlinedCardColors", "outlinedCardColors-ro_MJ88", "outlinedCardElevation", "outlinedCardElevation-aqJV_2Y", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CardDefaults {
    public static final int $stable = 0;
    public static final CardDefaults INSTANCE = new CardDefaults();

    private CardDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1266660211, "C376@16530L5:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1266660211, $changed, -1, "androidx.compose.material3.CardDefaults.<get-shape> (Card.kt:376)");
        }
        Shape value = ShapesKt.getValue(FilledCardTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getElevatedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -133496185, "C380@16675L5:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-133496185, $changed, -1, "androidx.compose.material3.CardDefaults.<get-elevatedShape> (Card.kt:380)");
        }
        Shape value = ShapesKt.getValue(ElevatedCardTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1095404023, "C384@16820L5:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1095404023, $changed, -1, "androidx.compose.material3.CardDefaults.<get-outlinedShape> (Card.kt:384)");
        }
        Shape value = ShapesKt.getValue(OutlinedCardTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    /* JADX INFO: renamed from: cardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m2204cardElevationaqJV_2Y(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -574898487, "C(cardElevation)P(0:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp):Card.kt#uh7d8r");
        float defaultElevation2 = (i & 1) != 0 ? FilledCardTokens.INSTANCE.m3621getContainerElevationD9Ej5fM() : defaultElevation;
        float pressedElevation2 = (i & 2) != 0 ? FilledCardTokens.INSTANCE.m3627getPressedContainerElevationD9Ej5fM() : pressedElevation;
        float focusedElevation2 = (i & 4) != 0 ? FilledCardTokens.INSTANCE.m3624getFocusContainerElevationD9Ej5fM() : focusedElevation;
        float hoveredElevation2 = (i & 8) != 0 ? FilledCardTokens.INSTANCE.m3625getHoverContainerElevationD9Ej5fM() : hoveredElevation;
        float draggedElevation2 = (i & 16) != 0 ? FilledCardTokens.INSTANCE.m3623getDraggedContainerElevationD9Ej5fM() : draggedElevation;
        float disabledElevation2 = (i & 32) != 0 ? FilledCardTokens.INSTANCE.m3622getDisabledContainerElevationD9Ej5fM() : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-574898487, $changed, -1, "androidx.compose.material3.CardDefaults.cardElevation (Card.kt:405)");
        }
        CardElevation cardElevation = new CardElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardElevation;
    }

    /* JADX INFO: renamed from: elevatedCardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m2206elevatedCardElevationaqJV_2Y(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1154241939, "C(elevatedCardElevation)P(0:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp):Card.kt#uh7d8r");
        float defaultElevation2 = (i & 1) != 0 ? ElevatedCardTokens.INSTANCE.m3540getContainerElevationD9Ej5fM() : defaultElevation;
        float pressedElevation2 = (i & 2) != 0 ? ElevatedCardTokens.INSTANCE.m3546getPressedContainerElevationD9Ej5fM() : pressedElevation;
        float focusedElevation2 = (i & 4) != 0 ? ElevatedCardTokens.INSTANCE.m3543getFocusContainerElevationD9Ej5fM() : focusedElevation;
        float hoveredElevation2 = (i & 8) != 0 ? ElevatedCardTokens.INSTANCE.m3544getHoverContainerElevationD9Ej5fM() : hoveredElevation;
        float draggedElevation2 = (i & 16) != 0 ? ElevatedCardTokens.INSTANCE.m3542getDraggedContainerElevationD9Ej5fM() : draggedElevation;
        float disabledElevation2 = (i & 32) != 0 ? ElevatedCardTokens.INSTANCE.m3541getDisabledContainerElevationD9Ej5fM() : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1154241939, $changed, -1, "androidx.compose.material3.CardDefaults.elevatedCardElevation (Card.kt:434)");
        }
        CardElevation cardElevation = new CardElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardElevation;
    }

    /* JADX INFO: renamed from: outlinedCardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m2208outlinedCardElevationaqJV_2Y(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -97678773, "C(outlinedCardElevation)P(0:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp):Card.kt#uh7d8r");
        float defaultElevation2 = (i & 1) != 0 ? OutlinedCardTokens.INSTANCE.m3720getContainerElevationD9Ej5fM() : defaultElevation;
        float pressedElevation2 = (i & 2) != 0 ? defaultElevation2 : pressedElevation;
        float focusedElevation2 = (i & 4) != 0 ? defaultElevation2 : focusedElevation;
        float hoveredElevation2 = (i & 8) != 0 ? defaultElevation2 : hoveredElevation;
        float draggedElevation2 = (i & 16) != 0 ? OutlinedCardTokens.INSTANCE.m3722getDraggedContainerElevationD9Ej5fM() : draggedElevation;
        float disabledElevation2 = (i & 32) != 0 ? OutlinedCardTokens.INSTANCE.m3721getDisabledContainerElevationD9Ej5fM() : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-97678773, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardElevation (Card.kt:463)");
        }
        CardElevation cardElevation = new CardElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardElevation;
    }

    public final CardColors cardColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1876034303, "C(cardColors)476@21292L11:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1876034303, $changed, -1, "androidx.compose.material3.CardDefaults.cardColors (Card.kt:476)");
        }
        CardColors defaultCardColors$material3_release = getDefaultCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultCardColors$material3_release;
    }

    /* JADX INFO: renamed from: cardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m2203cardColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long disabledContentColor2;
        ComposerKt.sourceInformationMarkerStart($composer, -1589582123, "C(cardColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)490@21903L31,494@22109L11:Card.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m2304contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            long j = contentColor2;
            disabledContentColor2 = Color.m4532copywmQWz5c(j, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j) : 0.38f, (14 & 2) != 0 ? Color.m4540getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1589582123, $changed, -1, "androidx.compose.material3.CardDefaults.cardColors (Card.kt:494)");
        }
        CardColors cardColorsM2198copyjRlVdoo = getDefaultCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2198copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardColorsM2198copyjRlVdoo;
    }

    public final CardColors getDefaultCardColors$material3_release(ColorScheme $this$defaultCardColors) {
        CardColors defaultCardColorsCached = $this$defaultCardColors.getDefaultCardColorsCached();
        if (defaultCardColorsCached == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor());
            long jM2303contentColorFor4WTKRHQ = ColorSchemeKt.m2303contentColorFor4WTKRHQ($this$defaultCardColors, ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor()));
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getDisabledContainerColor());
            long jM4579compositeOverOWjLjI = ColorKt.m4579compositeOverOWjLjI(Color.m4532copywmQWz5c(jFromToken2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken2) : FilledCardTokens.INSTANCE.getDisabledContainerOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken2) : 0.0f), ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor()));
            long jM2303contentColorFor4WTKRHQ2 = ColorSchemeKt.m2303contentColorFor4WTKRHQ($this$defaultCardColors, ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor()));
            CardColors it = new CardColors(jFromToken, jM2303contentColorFor4WTKRHQ, jM4579compositeOverOWjLjI, Color.m4532copywmQWz5c(jM2303contentColorFor4WTKRHQ2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM2303contentColorFor4WTKRHQ2) : 0.38f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM2303contentColorFor4WTKRHQ2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM2303contentColorFor4WTKRHQ2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM2303contentColorFor4WTKRHQ2) : 0.0f), null);
            $this$defaultCardColors.setDefaultCardColorsCached$material3_release(it);
            return it;
        }
        return defaultCardColorsCached;
    }

    public final CardColors elevatedCardColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1610137975, "C(elevatedCardColors)522@23477L11:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1610137975, $changed, -1, "androidx.compose.material3.CardDefaults.elevatedCardColors (Card.kt:522)");
        }
        CardColors defaultElevatedCardColors$material3_release = getDefaultElevatedCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultElevatedCardColors$material3_release;
    }

    /* JADX INFO: renamed from: elevatedCardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m2205elevatedCardColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long disabledContentColor2;
        ComposerKt.sourceInformationMarkerStart($composer, 139558303, "C(elevatedCardColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)536@24145L31,540@24351L11:Card.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m2304contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            long j = contentColor2;
            disabledContentColor2 = Color.m4532copywmQWz5c(j, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j) : 0.38f, (14 & 2) != 0 ? Color.m4540getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(139558303, $changed, -1, "androidx.compose.material3.CardDefaults.elevatedCardColors (Card.kt:540)");
        }
        CardColors cardColorsM2198copyjRlVdoo = getDefaultElevatedCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2198copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardColorsM2198copyjRlVdoo;
    }

    public final CardColors getDefaultElevatedCardColors$material3_release(ColorScheme $this$defaultElevatedCardColors) {
        CardColors defaultElevatedCardColorsCached = $this$defaultElevatedCardColors.getDefaultElevatedCardColorsCached();
        if (defaultElevatedCardColorsCached == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getContainerColor());
            long jM2303contentColorFor4WTKRHQ = ColorSchemeKt.m2303contentColorFor4WTKRHQ($this$defaultElevatedCardColors, ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getContainerColor()));
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getDisabledContainerColor());
            long jM4579compositeOverOWjLjI = ColorKt.m4579compositeOverOWjLjI(Color.m4532copywmQWz5c(jFromToken2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken2) : ElevatedCardTokens.INSTANCE.getDisabledContainerOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken2) : 0.0f), ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getDisabledContainerColor()));
            long jM2303contentColorFor4WTKRHQ2 = ColorSchemeKt.m2303contentColorFor4WTKRHQ($this$defaultElevatedCardColors, ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getContainerColor()));
            CardColors it = new CardColors(jFromToken, jM2303contentColorFor4WTKRHQ, jM4579compositeOverOWjLjI, Color.m4532copywmQWz5c(jM2303contentColorFor4WTKRHQ2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM2303contentColorFor4WTKRHQ2) : 0.38f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM2303contentColorFor4WTKRHQ2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM2303contentColorFor4WTKRHQ2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM2303contentColorFor4WTKRHQ2) : 0.0f), null);
            $this$defaultElevatedCardColors.setDefaultElevatedCardColorsCached$material3_release(it);
            return it;
        }
        return defaultElevatedCardColorsCached;
    }

    public final CardColors outlinedCardColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1204388929, "C(outlinedCardColors)571@25869L11:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1204388929, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardColors (Card.kt:571)");
        }
        CardColors defaultOutlinedCardColors$material3_release = getDefaultOutlinedCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultOutlinedCardColors$material3_release;
    }

    /* JADX INFO: renamed from: outlinedCardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m2207outlinedCardColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long disabledContentColor2;
        ComposerKt.sourceInformationMarkerStart($composer, -1112362409, "C(outlinedCardColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)585@26537L31,587@26667L31,589@26762L11:Card.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m2304contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            long jM2304contentColorForek8zF_U = ColorSchemeKt.m2304contentColorForek8zF_U(containerColor2, $composer, $changed & 14);
            disabledContentColor2 = Color.m4532copywmQWz5c(jM2304contentColorForek8zF_U, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM2304contentColorForek8zF_U) : 0.38f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM2304contentColorForek8zF_U) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM2304contentColorForek8zF_U) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM2304contentColorForek8zF_U) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1112362409, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardColors (Card.kt:589)");
        }
        CardColors cardColorsM2198copyjRlVdoo = getDefaultOutlinedCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2198copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cardColorsM2198copyjRlVdoo;
    }

    public final CardColors getDefaultOutlinedCardColors$material3_release(ColorScheme $this$defaultOutlinedCardColors) {
        CardColors defaultOutlinedCardColorsCached = $this$defaultOutlinedCardColors.getDefaultOutlinedCardColorsCached();
        if (defaultOutlinedCardColorsCached == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor());
            long jM2303contentColorFor4WTKRHQ = ColorSchemeKt.m2303contentColorFor4WTKRHQ($this$defaultOutlinedCardColors, ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor()));
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor());
            long jM2303contentColorFor4WTKRHQ2 = ColorSchemeKt.m2303contentColorFor4WTKRHQ($this$defaultOutlinedCardColors, ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor()));
            CardColors it = new CardColors(jFromToken, jM2303contentColorFor4WTKRHQ, jFromToken2, Color.m4532copywmQWz5c(jM2303contentColorFor4WTKRHQ2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM2303contentColorFor4WTKRHQ2) : 0.38f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM2303contentColorFor4WTKRHQ2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM2303contentColorFor4WTKRHQ2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM2303contentColorFor4WTKRHQ2) : 0.0f), null);
            $this$defaultOutlinedCardColors.setDefaultOutlinedCardColorsCached$material3_release(it);
            return it;
        }
        return defaultOutlinedCardColorsCached;
    }

    public final BorderStroke outlinedCardBorder(boolean enabled, Composer $composer, int $changed, int i) {
        long color;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -392936593, "C(outlinedCardBorder)626@28402L72:Card.kt#uh7d8r");
        if ((i & 1) != 0) {
            enabled = true;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-392936593, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardBorder (Card.kt:617)");
        }
        if (enabled) {
            $composer.startReplaceGroup(-134409770);
            ComposerKt.sourceInformation($composer, "620@28131L5");
            color = ColorSchemeKt.getValue(OutlinedCardTokens.INSTANCE.getOutlineColor(), $composer, 6);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-134330379);
            ComposerKt.sourceInformation($composer, "622@28214L5,624@28366L5");
            long value = ColorSchemeKt.getValue(OutlinedCardTokens.INSTANCE.getDisabledOutlineColor(), $composer, 6);
            color = ColorKt.m4579compositeOverOWjLjI(Color.m4532copywmQWz5c(value, (14 & 1) != 0 ? Color.m4536getAlphaimpl(value) : 0.12f, (14 & 2) != 0 ? Color.m4540getRedimpl(value) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(value) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(value) : 0.0f), ColorSchemeKt.getValue(ElevatedCardTokens.INSTANCE.getContainerColor(), $composer, 6));
            $composer.endReplaceGroup();
        }
        ComposerKt.sourceInformationMarkerStart($composer, -974156849, "CC(remember):Card.kt#9igjgp");
        boolean invalid$iv = $composer.changed(color);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = BorderStrokeKt.m616BorderStrokecXLIe8U(OutlinedCardTokens.INSTANCE.m3726getOutlineWidthD9Ej5fM(), color);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        BorderStroke borderStroke = (BorderStroke) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return borderStroke;
    }
}
