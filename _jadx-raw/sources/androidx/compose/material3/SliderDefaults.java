package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JB\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ3\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u0010!J\u0096\u0001\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u0017\u001a\u00020\r2!\b\u0002\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0012\u0018\u00010#¢\u0006\u0002\b&2%\b\u0002\u0010'\u001a\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0002\b&2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-J3\u0010\u001e\u001a\u00020\u00122\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u00100J3\u0010\u001e\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u00103J\u0096\u0001\u0010\u001e\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u0017\u001a\u00020\r2!\b\u0002\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0012\u0018\u00010#¢\u0006\u0002\b&2%\b\u0002\u0010'\u001a\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0002\b&2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u00104J\r\u0010\u0017\u001a\u00020\rH\u0007¢\u0006\u0002\u00105Jv\u0010\u0017\u001a\u00020\r2\b\b\u0002\u00106\u001a\u00020)2\b\b\u0002\u00107\u001a\u00020)2\b\b\u0002\u00108\u001a\u00020)2\b\b\u0002\u00109\u001a\u00020)2\b\b\u0002\u0010:\u001a\u00020)2\b\b\u0002\u0010;\u001a\u00020)2\b\b\u0002\u0010<\u001a\u00020)2\b\b\u0002\u0010=\u001a\u00020)2\b\b\u0002\u0010>\u001a\u00020)2\b\b\u0002\u0010?\u001a\u00020)H\u0007ø\u0001\u0000¢\u0006\u0004\b@\u0010AJ2\u0010\"\u001a\u00020\u00122\u0006\u0010B\u001a\u00020$2\u0006\u0010C\u001a\u00020%2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020)H\u0002ø\u0001\u0000¢\u0006\u0004\bF\u0010GJÄ\u0001\u0010H\u001a\u00020\u0012*\u00020$2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020L2\u0006\u00109\u001a\u00020)2\u0006\u00107\u001a\u00020)2\u0006\u0010:\u001a\u00020)2\u0006\u00108\u001a\u00020)2\u0006\u0010N\u001a\u00020\u00042\u0006\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u001f\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0012\u0018\u00010#¢\u0006\u0002\b&2#\u0010'\u001a\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0002\b&2\u0006\u0010Q\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ>\u0010T\u001a\u00020\u0012*\u00020$2\u0006\u0010C\u001a\u00020%2\u0006\u0010D\u001a\u00020U2\u0006\u0010E\u001a\u00020)2\u0006\u0010V\u001a\u00020L2\u0006\u0010W\u001a\u00020LH\u0002ø\u0001\u0000¢\u0006\u0004\bX\u0010YR\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\u00020\r*\u00020\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Z"}, d2 = {"Landroidx/compose/material3/SliderDefaults;", "", "()V", "TickSize", "Landroidx/compose/ui/unit/Dp;", "getTickSize-D9Ej5fM", "()F", "F", "TrackStopIndicatorSize", "getTrackStopIndicatorSize-D9Ej5fM", "trackPath", "Landroidx/compose/ui/graphics/Path;", "defaultSliderColors", "Landroidx/compose/material3/SliderColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultSliderColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SliderColors;", "Thumb", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "enabled", "", "thumbSize", "Landroidx/compose/ui/unit/DpSize;", "Thumb-9LiSoMs", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZJLandroidx/compose/runtime/Composer;II)V", "Track", "rangeSliderState", "Landroidx/compose/material3/RangeSliderState;", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "drawStopIndicator", "Lkotlin/Function2;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ExtensionFunctionType;", "drawTick", "Lkotlin/Function3;", "Landroidx/compose/ui/graphics/Color;", "thumbTrackGapSize", "trackInsideCornerSize", "Track-4EFweAY", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "sliderPositions", "Landroidx/compose/material3/SliderPositions;", "(Landroidx/compose/material3/SliderPositions;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "sliderState", "Landroidx/compose/material3/SliderState;", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SliderColors;", "thumbColor", "activeTrackColor", "activeTickColor", "inactiveTrackColor", "inactiveTickColor", "disabledThumbColor", "disabledActiveTrackColor", "disabledActiveTickColor", "disabledInactiveTrackColor", "disabledInactiveTickColor", "colors-q0g_0yA", "(JJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SliderColors;", "drawScope", "offset", "size", "color", "drawStopIndicator-x3O1jOs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFJ)V", "drawTrack", "tickFractions", "", "activeRangeStart", "", "activeRangeEnd", "height", "startThumbWidth", "endThumbWidth", "isRangeSlider", "drawTrack-ngJ0SCU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;[FFFJJJJFFFFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Z)V", "drawTrackPath", "Landroidx/compose/ui/geometry/Size;", "startCornerRadius", "endCornerRadius", "drawTrackPath-Cx2C_VA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJJFF)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SliderDefaults {
    public static final int $stable = 0;
    public static final SliderDefaults INSTANCE = new SliderDefaults();
    private static final float TrackStopIndicatorSize = SliderTokens.INSTANCE.m3873getStopIndicatorSizeD9Ej5fM();
    private static final float TickSize = SliderTokens.INSTANCE.m3873getStopIndicatorSizeD9Ej5fM();
    private static final Path trackPath = AndroidPath_androidKt.Path();

    private SliderDefaults() {
    }

    public final SliderColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1376295968, "C(colors)845@36907L11:Slider.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1376295968, $changed, -1, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:845)");
        }
        SliderColors defaultSliderColors$material3_release = getDefaultSliderColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSliderColors$material3_release;
    }

    /* JADX INFO: renamed from: colors-q0g_0yA, reason: not valid java name */
    public final SliderColors m2869colorsq0g_0yA(long thumbColor, long activeTrackColor, long activeTickColor, long inactiveTrackColor, long inactiveTickColor, long disabledThumbColor, long disabledActiveTrackColor, long disabledActiveTickColor, long disabledInactiveTrackColor, long disabledInactiveTickColor, Composer $composer, int $changed, int $changed1, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 885588574, "C(colors)P(9:c#ui.graphics.Color,1:c#ui.graphics.Color,0:c#ui.graphics.Color,8:c#ui.graphics.Color,7:c#ui.graphics.Color,6:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.graphics.Color,4:c#ui.graphics.Color)887@39230L11:Slider.kt#uh7d8r");
        long thumbColor2 = (i & 1) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : thumbColor;
        long activeTrackColor2 = (i & 2) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : activeTrackColor;
        long activeTickColor2 = (i & 4) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : activeTickColor;
        long inactiveTrackColor2 = (i & 8) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : inactiveTrackColor;
        long inactiveTickColor2 = (i & 16) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : inactiveTickColor;
        long disabledThumbColor2 = (i & 32) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledThumbColor;
        long disabledActiveTrackColor2 = (i & 64) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledActiveTrackColor;
        long disabledActiveTickColor2 = (i & 128) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledActiveTickColor;
        long disabledInactiveTrackColor2 = (i & 256) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledInactiveTrackColor;
        long disabledInactiveTickColor2 = (i & 512) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledInactiveTickColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(885588574, $changed, $changed1, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:887)");
        }
        SliderColors sliderColorsM2847copyK518z4 = getDefaultSliderColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2847copyK518z4(thumbColor2, activeTrackColor2, activeTickColor2, inactiveTrackColor2, inactiveTickColor2, disabledThumbColor2, disabledActiveTrackColor2, disabledActiveTickColor2, disabledInactiveTrackColor2, disabledInactiveTickColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return sliderColorsM2847copyK518z4;
    }

    public final SliderColors getDefaultSliderColors$material3_release(ColorScheme $this$defaultSliderColors) {
        SliderColors it = $this$defaultSliderColors.getDefaultSliderColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getHandleColor());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getActiveTrackColor());
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getInactiveTrackColor());
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getInactiveTrackColor());
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getActiveTrackColor());
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledHandleColor());
            long jM4579compositeOverOWjLjI = ColorKt.m4579compositeOverOWjLjI(Color.m4532copywmQWz5c(jFromToken6, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken6) : SliderTokens.INSTANCE.getDisabledHandleOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken6) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken6) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken6) : 0.0f), $this$defaultSliderColors.getSurface());
            long jFromToken7 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledActiveTrackColor());
            long jM4532copywmQWz5c = Color.m4532copywmQWz5c(jFromToken7, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken7) : SliderTokens.INSTANCE.getDisabledActiveTrackOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken7) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken7) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken7) : 0.0f);
            long jFromToken8 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledInactiveTrackColor());
            long jM4532copywmQWz5c2 = Color.m4532copywmQWz5c(jFromToken8, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken8) : SliderTokens.INSTANCE.getDisabledInactiveTrackOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken8) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken8) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken8) : 0.0f);
            long jFromToken9 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledInactiveTrackColor());
            long jM4532copywmQWz5c3 = Color.m4532copywmQWz5c(jFromToken9, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken9) : SliderTokens.INSTANCE.getDisabledInactiveTrackOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken9) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken9) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken9) : 0.0f);
            long jFromToken10 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledActiveTrackColor());
            SliderColors it2 = new SliderColors(jFromToken, jFromToken2, jFromToken3, jFromToken4, jFromToken5, jM4579compositeOverOWjLjI, jM4532copywmQWz5c, jM4532copywmQWz5c2, jM4532copywmQWz5c3, Color.m4532copywmQWz5c(jFromToken10, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken10) : SliderTokens.INSTANCE.getDisabledActiveTrackOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken10) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken10) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken10) : 0.0f), null);
            $this$defaultSliderColors.setDefaultSliderColorsCached$material3_release(it2);
            return it2;
        }
        return it;
    }

    /* JADX INFO: renamed from: Thumb-9LiSoMs, reason: not valid java name */
    public final void m2866Thumb9LiSoMs(final MutableInteractionSource interactionSource, Modifier modifier, SliderColors colors, boolean enabled, long thumbSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors colors2;
        boolean enabled2;
        long thumbSize2;
        Modifier.Companion modifier3;
        Object value$iv;
        SliderDefaults$Thumb$1$1 value$iv2;
        long size;
        Modifier modifier4;
        long thumbSize3;
        SliderColors colors3;
        boolean enabled3;
        Composer $composer2 = $composer.startRestartGroup(-290277409);
        ComposerKt.sourceInformation($composer2, "C(Thumb)P(2,3!,4:c#ui.unit.DpSize)947@42562L8,951@42678L46,952@42767L658,952@42733L692,975@43824L5,971@43620L220:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                colors2 = colors;
                int i3 = $composer2.changed(colors2) ? 256 : 128;
                $dirty |= i3;
            } else {
                colors2 = colors;
            }
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            thumbSize2 = thumbSize;
        } else if (($changed & 24576) == 0) {
            thumbSize2 = thumbSize;
            $dirty |= $composer2.changed(thumbSize2) ? 16384 : 8192;
        } else {
            thumbSize2 = thumbSize;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changed(this) ? 131072 : 65536;
        }
        if (($dirty & 74899) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            thumbSize3 = thumbSize2;
            colors3 = colors2;
            enabled3 = enabled2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    SliderColors colors4 = colors($composer2, ($dirty >> 15) & 14);
                    $dirty &= -897;
                    colors2 = colors4;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if (i5 != 0) {
                    thumbSize2 = SliderKt.ThumbSize;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-290277409, $dirty, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:950)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1068636116, "CC(remember):Slider.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt.mutableStateListOf();
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            SnapshotStateList interactions = (SnapshotStateList) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1068632656, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new SliderDefaults$Thumb$1$1(interactionSource, interactions, null);
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer2, $dirty & 14);
            if (interactions.isEmpty()) {
                size = thumbSize2;
            } else {
                float arg0$iv = DpSize.m7114getWidthD9Ej5fM(thumbSize2);
                size = DpSize.m7107copyDwJknco$default(thumbSize2, Dp.m7016constructorimpl(arg0$iv / 2), 0.0f, 2, null);
            }
            SpacerKt.Spacer(BackgroundKt.m588backgroundbw27NRU(HoverableKt.hoverable$default(SizeKt.m1080size6HolHcs(modifier3, size), interactionSource, false, 2, null), colors2.m2858thumbColorvNxB06k$material3_release(enabled2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), $composer2, 6)), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            thumbSize3 = thumbSize2;
            colors3 = colors2;
            enabled3 = enabled2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final SliderColors sliderColors = colors3;
            final boolean z = enabled3;
            final long j = thumbSize3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Thumb$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    this.$tmp2_rcvr.m2866Thumb9LiSoMs(interactionSource, modifier5, sliderColors, z, j, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(message = "Use version that supports slider state")
    public final void Track(final SliderPositions sliderPositions, Modifier modifier, SliderColors colors, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors sliderColors;
        boolean enabled2;
        Modifier.Companion modifier3;
        SliderColors colors2;
        boolean enabled3;
        Modifier modifier4;
        SliderColors colors3;
        Composer $composer2 = $composer.startRestartGroup(-1546713545);
        ComposerKt.sourceInformation($composer2, "C(Track)P(3,2)997@44755L8,1004@45160L1834,1004@45108L1886:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(sliderPositions) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sliderColors = colors;
                int i3 = $composer2.changed(sliderColors) ? 256 : 128;
                $dirty |= i3;
            } else {
                sliderColors = colors;
            }
            $dirty |= i3;
        } else {
            sliderColors = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(this) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = sliderColors;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    colors2 = colors($composer2, ($dirty >> 12) & 14);
                    $dirty &= -897;
                } else {
                    colors2 = sliderColors;
                }
                enabled3 = i4 != 0 ? true : enabled2;
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
                colors2 = sliderColors;
                enabled3 = enabled2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1546713545, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:999)");
            }
            final long inactiveTrackColor = colors2.m2860trackColorWaAFU9c$material3_release(enabled3, false);
            final long activeTrackColor = colors2.m2860trackColorWaAFU9c$material3_release(enabled3, true);
            final long inactiveTickColor = colors2.m2859tickColorWaAFU9c$material3_release(enabled3, false);
            final long activeTickColor = colors2.m2859tickColorWaAFU9c$material3_release(enabled3, true);
            Modifier modifierM1065height3ABfNKs = SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), SliderKt.getTrackHeight());
            ComposerKt.sourceInformationMarkerStart($composer2, -801023075, "CC(remember):Slider.kt#9igjgp");
            modifier4 = modifier3;
            boolean invalid$iv = (($dirty & 14) == 4) | $composer2.changed(inactiveTrackColor) | $composer2.changed(activeTrackColor) | $composer2.changed(inactiveTickColor) | $composer2.changed(activeTickColor);
            Object value$iv = $composer2.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$Canvas) {
                        Object answer$iv$iv$iv;
                        boolean isRtl = $this$Canvas.getLayoutDirection() == LayoutDirection.Rtl;
                        long sliderLeft = OffsetKt.Offset(0.0f, Offset.m4294getYimpl($this$Canvas.mo5089getCenterF1C5BW0()));
                        long sliderRight = OffsetKt.Offset(Size.m4362getWidthimpl($this$Canvas.mo5090getSizeNHjbRc()), Offset.m4294getYimpl($this$Canvas.mo5089getCenterF1C5BW0()));
                        long sliderStart = isRtl ? sliderRight : sliderLeft;
                        long sliderEnd = isRtl ? sliderLeft : sliderRight;
                        float tickSize = $this$Canvas.mo726toPx0680j_4(SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM());
                        float trackStrokeWidth = $this$Canvas.mo726toPx0680j_4(SliderKt.getTrackHeight());
                        long sliderValueStart = sliderEnd;
                        long sliderStart2 = sliderStart;
                        DrawScope.m5076drawLineNGM6Ib0$default($this$Canvas, inactiveTrackColor, sliderStart, sliderEnd, trackStrokeWidth, StrokeCap.INSTANCE.m4888getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                        long sliderValueEnd = OffsetKt.Offset(Offset.m4293getXimpl(sliderStart2) + ((Offset.m4293getXimpl(sliderValueStart) - Offset.m4293getXimpl(sliderStart2)) * sliderPositions.getActiveRange().getEndInclusive().floatValue()), Offset.m4294getYimpl($this$Canvas.mo5089getCenterF1C5BW0()));
                        long sliderEnd2 = OffsetKt.Offset(Offset.m4293getXimpl(sliderStart2) + ((Offset.m4293getXimpl(sliderValueStart) - Offset.m4293getXimpl(sliderStart2)) * sliderPositions.getActiveRange().getStart().floatValue()), Offset.m4294getYimpl($this$Canvas.mo5089getCenterF1C5BW0()));
                        DrawScope.m5076drawLineNGM6Ib0$default($this$Canvas, activeTrackColor, sliderEnd2, sliderValueEnd, trackStrokeWidth, StrokeCap.INSTANCE.m4888getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                        float[] $this$groupBy$iv = sliderPositions.getTickFractions();
                        SliderPositions sliderPositions2 = sliderPositions;
                        Map destination$iv$iv = new LinkedHashMap();
                        int length = $this$groupBy$iv.length;
                        int i5 = 0;
                        while (i5 < length) {
                            float element$iv$iv = $this$groupBy$iv[i5];
                            Boolean boolValueOf = Boolean.valueOf(element$iv$iv > sliderPositions2.getActiveRange().getEndInclusive().floatValue() || element$iv$iv < sliderPositions2.getActiveRange().getStart().floatValue());
                            Object value$iv$iv$iv = destination$iv$iv.get(boolValueOf);
                            if (value$iv$iv$iv == null) {
                                answer$iv$iv$iv = new ArrayList();
                                destination$iv$iv.put(boolValueOf, answer$iv$iv$iv);
                            } else {
                                answer$iv$iv$iv = value$iv$iv$iv;
                            }
                            List list$iv$iv = (List) answer$iv$iv$iv;
                            list$iv$iv.add(Float.valueOf(element$iv$iv));
                            i5++;
                            $this$groupBy$iv = $this$groupBy$iv;
                        }
                        long j = inactiveTickColor;
                        long j2 = activeTickColor;
                        Map $this$forEach$iv = destination$iv$iv;
                        for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                            boolean outsideFraction = ((Boolean) element$iv.getKey()).booleanValue();
                            List list = (List) element$iv.getValue();
                            ArrayList target$iv = new ArrayList(list.size());
                            int index$iv$iv = 0;
                            int size = list.size();
                            while (index$iv$iv < size) {
                                Object item$iv$iv = list.get(index$iv$iv);
                                long j3 = j;
                                float it = ((Number) item$iv$iv).floatValue();
                                long sliderStart3 = sliderStart2;
                                long sliderValueStart2 = sliderEnd2;
                                long sliderValueStart3 = sliderValueStart;
                                target$iv.add(Offset.m4282boximpl(OffsetKt.Offset(Offset.m4293getXimpl(OffsetKt.m4316lerpWko1d7g(sliderStart3, sliderValueStart3, it)), Offset.m4294getYimpl($this$Canvas.mo5089getCenterF1C5BW0()))));
                                index$iv$iv++;
                                size = size;
                                j = j3;
                                $this$forEach$iv = $this$forEach$iv;
                                sliderStart2 = sliderStart3;
                                sliderEnd2 = sliderValueStart2;
                                sliderValueStart = sliderValueStart3;
                            }
                            long j4 = j;
                            Map $this$forEach$iv2 = $this$forEach$iv;
                            long sliderStart4 = sliderStart2;
                            long sliderValueStart4 = sliderEnd2;
                            long sliderValueStart5 = sliderValueStart;
                            DrawScope.m5081drawPointsF8ZwMP8$default($this$Canvas, target$iv, PointMode.INSTANCE.m4840getPointsr_lszbg(), outsideFraction ? j4 : j2, tickSize, StrokeCap.INSTANCE.m4888getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                            j2 = j2;
                            j = j4;
                            $this$forEach$iv = $this$forEach$iv2;
                            sliderStart2 = sliderStart4;
                            sliderEnd2 = sliderValueStart4;
                            sliderValueStart = sliderValueStart5;
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM1065height3ABfNKs, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors3 = colors2;
            enabled2 = enabled3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final SliderColors sliderColors2 = colors3;
            final boolean z = enabled2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    SliderDefaults.this.Track(sliderPositions, modifier5, sliderColors2, z, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacySliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(sliderState, modifier, enabled, colors, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    public final /* synthetic */ void Track(final SliderState sliderState, Modifier modifier, SliderColors colors, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors sliderColors;
        boolean z;
        SliderColors colors2;
        int $dirty;
        Modifier modifier3;
        SliderColors colors3;
        boolean enabled2;
        Composer $composer2 = $composer.startRestartGroup(593554206);
        ComposerKt.sourceInformation($composer2, "C(Track)P(3,2)1079@48295L8,1082@48353L213:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(sliderState) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sliderColors = colors;
                int i3 = $composer2.changed(sliderColors) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                sliderColors = colors;
            }
            $dirty2 |= i3;
        } else {
            sliderColors = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(this) ? 16384 : 8192;
        }
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors3 = sliderColors;
            enabled2 = z;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    colors2 = colors($composer2, ($dirty2 >> 12) & 14);
                    $dirty2 &= -897;
                } else {
                    colors2 = sliderColors;
                }
                if (i4 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    colors3 = colors2;
                    enabled2 = true;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    colors3 = colors2;
                    enabled2 = z;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                colors3 = sliderColors;
                enabled2 = z;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(593554206, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1081)");
            }
            m2868Track4EFweAY(sliderState, modifier3, enabled2, colors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, $composer2, ($dirty & 14) | 14155776 | ($dirty & 112) | (($dirty >> 3) & 896) | (($dirty << 3) & 7168) | (234881024 & ($dirty << 12)), 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final SliderColors sliderColors2 = colors3;
            final boolean z2 = enabled2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    SliderDefaults.this.Track(sliderState, modifier5, sliderColors2, z2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:105:0x013f  */
    /* JADX WARN: Code duplicated, block: B:107:0x0146  */
    /* JADX WARN: Code duplicated, block: B:117:0x0172 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:118:0x0174  */
    /* JADX WARN: Code duplicated, block: B:119:0x0179  */
    /* JADX WARN: Code duplicated, block: B:121:0x017d  */
    /* JADX WARN: Code duplicated, block: B:124:0x0183  */
    /* JADX WARN: Code duplicated, block: B:127:0x0192  */
    /* JADX WARN: Code duplicated, block: B:129:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:131:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:137:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:138:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:141:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:145:0x01d5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:148:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:150:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:152:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:153:0x0203  */
    /* JADX WARN: Code duplicated, block: B:155:0x0207  */
    /* JADX WARN: Code duplicated, block: B:156:0x0213  */
    /* JADX WARN: Code duplicated, block: B:159:0x0223  */
    /* JADX WARN: Code duplicated, block: B:162:0x027b  */
    /* JADX WARN: Code duplicated, block: B:163:0x027e  */
    /* JADX WARN: Code duplicated, block: B:166:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:167:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:170:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:171:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:174:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:178:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:180:0x02d8 A[PHI: r7
  0x02d8: PHI (r7v29 '$dirty' int) = (r7v27 '$dirty' int), (r7v31 '$dirty' int) binds: [B:179:0x02d6, B:177:0x02cd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:181:0x02da  */
    /* JADX WARN: Code duplicated, block: B:184:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:185:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:188:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:192:0x0305 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:196:0x033e  */
    /* JADX WARN: Code duplicated, block: B:200:0x0357  */
    /* JADX WARN: Code duplicated, block: B:201:0x037c  */
    /* JADX INFO: renamed from: Track-4EFweAY, reason: not valid java name */
    public final void m2868Track4EFweAY(final SliderState sliderState, Modifier modifier, boolean enabled, SliderColors colors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float thumbTrackGapSize, float trackInsideCornerSize, Composer $composer, final int $changed, final int i) {
        final boolean enabled2;
        final SliderColors colors2;
        Function2<? super DrawScope, ? super Offset, Unit> function4;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function5;
        float f;
        int i2;
        Modifier.Companion modifier2;
        float thumbTrackGapSize2;
        float thumbTrackGapSize3;
        int $dirty;
        float trackInsideCornerSize2;
        Function2<? super DrawScope, ? super Offset, Unit> function6;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function7;
        Modifier modifier3;
        boolean z;
        boolean invalid$iv;
        Object value$iv;
        int $dirty2;
        Object objConsume;
        float f2;
        boolean z2;
        boolean z3;
        int $dirty3;
        boolean z4;
        boolean z5;
        boolean invalid$iv2;
        Object value$iv2;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function8;
        float thumbTrackGapSize4;
        float trackInsideCornerSize3;
        boolean enabled3;
        SliderColors colors3;
        Modifier modifier4;
        Function2<? super DrawScope, ? super Offset, Unit> function9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer2 = $composer.startRestartGroup(49984771);
        ComposerKt.sourceInformation($composer2, "C(Track)P(5,4,3!3,6:c#ui.unit.Dp,7:c#ui.unit.Dp)1114@49756L8,1115@49825L232,1137@50806L7,1138@50862L595,1133@50659L798:Slider.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer2.changedInstance(sliderState) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty4 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty4 |= $composer2.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i5 = $composer2.changed(colors2) ? 2048 : 1024;
                $dirty4 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i5;
        } else {
            colors2 = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                function4 = function2;
                int i6 = $composer2.changedInstance(function4) ? 16384 : 8192;
                $dirty4 |= i6;
            } else {
                function4 = function2;
            }
            $dirty4 |= i6;
        } else {
            function4 = function2;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function5 = function3;
            $dirty4 |= $composer2.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function3;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty4 |= $composer2.changed(thumbTrackGapSize) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty4 |= 12582912;
            f = trackInsideCornerSize;
        } else if (($changed & 12582912) == 0) {
            f = trackInsideCornerSize;
            $dirty4 |= $composer2.changed(f) ? 8388608 : 4194304;
        } else {
            f = trackInsideCornerSize;
        }
        if ((i & 256) == 0) {
            if ((100663296 & $changed) == 0) {
                i2 = $composer2.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            if ((38347923 & $dirty4) == 38347922 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    }
                    if ((i & 8) != 0) {
                        SliderColors colors4 = colors($composer2, ($dirty4 >> 24) & 14);
                        $dirty4 &= -7169;
                        colors2 = colors4;
                    }
                    if ((i & 16) != 0) {
                        ComposerKt.sourceInformationMarkerStart($composer2, -800875397, "CC(remember):Slider.kt#9igjgp");
                        boolean z6 = ((($dirty4 & 7168) ^ 3072) <= 2048 && $composer2.changed(colors2)) || ($dirty4 & 3072) == 2048;
                        if (($dirty4 & 896) == 256) {
                            z = true;
                        } else {
                            z = false;
                        }
                        invalid$iv = z6 | z;
                        value$iv = $composer2.rememberedValue();
                        if (!invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                                    m2873invokeUv8p0NA(drawScope, offset.getPackedValue());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                                public final void m2873invokeUv8p0NA(DrawScope $this$null, long it) {
                                    SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                                }
                            };
                            $composer2.updateRememberedValue(value$iv);
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $dirty4 &= -57345;
                        function4 = (Function2) value$iv;
                    }
                    if (i7 != 0) {
                        function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.5
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m2874invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m2874invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                                SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                            }
                        };
                    }
                    if (i8 != 0) {
                        thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                    } else {
                        thumbTrackGapSize2 = thumbTrackGapSize;
                    }
                    if (i9 != 0) {
                        thumbTrackGapSize3 = thumbTrackGapSize2;
                        $dirty = $dirty4;
                        trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                        function6 = function4;
                        function7 = function5;
                        modifier3 = modifier2;
                    } else {
                        thumbTrackGapSize3 = thumbTrackGapSize2;
                        $dirty = $dirty4;
                        trackInsideCornerSize2 = f;
                        function6 = function4;
                        function7 = function5;
                        modifier3 = modifier2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty4 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        thumbTrackGapSize3 = thumbTrackGapSize;
                        $dirty = (-57345) & $dirty4;
                        trackInsideCornerSize2 = f;
                        function6 = function4;
                        function7 = function5;
                        modifier3 = modifier;
                    } else {
                        modifier3 = modifier;
                        thumbTrackGapSize3 = thumbTrackGapSize;
                        $dirty = $dirty4;
                        trackInsideCornerSize2 = f;
                        function6 = function4;
                        function7 = function5;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(49984771, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1128)");
                }
                final long inactiveTrackColor = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, false);
                final long activeTrackColor = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true);
                final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function10 = function7;
                final float thumbTrackGapSize5 = thumbTrackGapSize3;
                final long inactiveTickColor = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, false);
                final float trackInsideCornerSize4 = trackInsideCornerSize2;
                $dirty2 = $dirty;
                final long activeTickColor = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, true);
                boolean enabled4 = enabled2;
                SliderColors colors5 = colors2;
                Modifier modifierM1065height3ABfNKs = SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), SliderKt.getTrackHeight());
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                Modifier modifier5 = modifier3;
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                objConsume = $composer2.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (objConsume == LayoutDirection.Rtl) {
                    f2 = 180.0f;
                } else {
                    f2 = 0.0f;
                }
                Modifier modifierRotate = RotateKt.rotate(modifierM1065height3ABfNKs, f2);
                ComposerKt.sourceInformationMarkerStart($composer2, -800841850, "CC(remember):Slider.kt#9igjgp");
                boolean zChangedInstance = $composer2.changedInstance(sliderState) | $composer2.changed(inactiveTrackColor) | $composer2.changed(activeTrackColor) | $composer2.changed(inactiveTickColor) | $composer2.changed(activeTickColor);
                if (($dirty2 & 3670016) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z7 = zChangedInstance | z2;
                if (($dirty2 & 29360128) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z8 = z7 | z3;
                if ((($dirty2 & 57344) ^ 24576) > 16384 || !$composer2.changed(function6)) {
                    $dirty3 = $dirty2;
                    if (($dirty3 & 24576) != 16384) {
                        z4 = false;
                    }
                    boolean z9 = z8 | z4;
                    if ((458752 & $dirty3) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv2 = z9 | z5;
                    value$iv2 = $composer2.rememberedValue();
                    if (!invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                        final Function2<? super DrawScope, ? super Offset, Unit> function11 = function6;
                        value$iv2 = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$6$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                                invoke2(drawScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DrawScope $this$Canvas) {
                                SliderDefaults.INSTANCE.m2864drawTrackngJ0SCU($this$Canvas, sliderState.getTickFractions(), 0.0f, sliderState.getCoercedValueAsFraction$material3_release(), inactiveTrackColor, activeTrackColor, inactiveTickColor, activeTickColor, $this$Canvas.mo722toDpu2uoSUM(sliderState.getTrackHeight$material3_release()), $this$Canvas.mo723toDpu2uoSUM(0), $this$Canvas.mo722toDpu2uoSUM(sliderState.getThumbWidth$material3_release()), thumbTrackGapSize5, trackInsideCornerSize4, function11, function10, false);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv2);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    CanvasKt.Canvas(modifierRotate, (Function1) value$iv2, $composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    thumbTrackGapSize4 = thumbTrackGapSize5;
                    trackInsideCornerSize3 = trackInsideCornerSize4;
                    enabled3 = enabled4;
                    colors3 = colors5;
                    modifier4 = modifier5;
                    function9 = function6;
                } else {
                    $dirty3 = $dirty2;
                }
                z4 = true;
                boolean z10 = z8 | z4;
                if ((458752 & $dirty3) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv2 = z10 | z5;
                value$iv2 = $composer2.rememberedValue();
                if (!invalid$iv2) {
                }
                final Function2<? super DrawScope, ? super Offset, Unit> function12 = function6;
                value$iv2 = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$6$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$Canvas) {
                        SliderDefaults.INSTANCE.m2864drawTrackngJ0SCU($this$Canvas, sliderState.getTickFractions(), 0.0f, sliderState.getCoercedValueAsFraction$material3_release(), inactiveTrackColor, activeTrackColor, inactiveTickColor, activeTickColor, $this$Canvas.mo722toDpu2uoSUM(sliderState.getTrackHeight$material3_release()), $this$Canvas.mo723toDpu2uoSUM(0), $this$Canvas.mo722toDpu2uoSUM(sliderState.getThumbWidth$material3_release()), thumbTrackGapSize5, trackInsideCornerSize4, function12, function10, false);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierRotate, (Function1) value$iv2, $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function10;
                thumbTrackGapSize4 = thumbTrackGapSize5;
                trackInsideCornerSize3 = trackInsideCornerSize4;
                enabled3 = enabled4;
                colors3 = colors5;
                modifier4 = modifier5;
                function9 = function6;
            } else {
                $composer2.skipToGroupEnd();
                thumbTrackGapSize4 = thumbTrackGapSize;
                trackInsideCornerSize3 = f;
                function9 = function4;
                function8 = function5;
                modifier4 = modifier;
                enabled3 = enabled2;
                colors3 = colors2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier4;
                final boolean z11 = enabled3;
                final SliderColors sliderColors = colors3;
                final Function2<? super DrawScope, ? super Offset, Unit> function13 = function9;
                final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function14 = function8;
                final float f3 = thumbTrackGapSize4;
                final float f4 = trackInsideCornerSize3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.7
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer, int i10) {
                        SliderDefaults.this.m2868Track4EFweAY(sliderState, modifier6, z11, sliderColors, function13, function14, f3, f4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                    }
                });
            }
        }
        i2 = 100663296;
        $dirty4 |= i2;
        if ((38347923 & $dirty4) == 38347922) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors6 = colors($composer2, ($dirty4 >> 24) & 14);
                    $dirty4 &= -7169;
                    colors2 = colors6;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -800875397, "CC(remember):Slider.kt#9igjgp");
                    if ((($dirty4 & 7168) ^ 3072) <= 2048) {
                    }
                    if (($dirty4 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    invalid$iv = z6 | z;
                    value$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                            m2873invokeUv8p0NA(drawScope, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m2873invokeUv8p0NA(DrawScope $this$null, long it) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty4 &= -57345;
                    function4 = (Function2) value$iv;
                }
                if (i7 != 0) {
                    function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.5
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                            m2874invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                        public final void m2874invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                        }
                    };
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                } else {
                    thumbTrackGapSize2 = thumbTrackGapSize;
                }
                if (i9 != 0) {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                } else {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = f;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors7 = colors($composer2, ($dirty4 >> 24) & 14);
                    $dirty4 &= -7169;
                    colors2 = colors7;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -800875397, "CC(remember):Slider.kt#9igjgp");
                    if ((($dirty4 & 7168) ^ 3072) <= 2048) {
                    }
                    if (($dirty4 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    invalid$iv = z6 | z;
                    value$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                            m2873invokeUv8p0NA(drawScope, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m2873invokeUv8p0NA(DrawScope $this$null, long it) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty4 &= -57345;
                    function4 = (Function2) value$iv;
                }
                if (i7 != 0) {
                    function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.5
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                            m2874invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                        public final void m2874invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                        }
                    };
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                } else {
                    thumbTrackGapSize2 = thumbTrackGapSize;
                }
                if (i9 != 0) {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                } else {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = f;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(49984771, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1128)");
            }
            final long inactiveTrackColor2 = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, false);
            final long activeTrackColor2 = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true);
            final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function15 = function7;
            final float thumbTrackGapSize6 = thumbTrackGapSize3;
            final long inactiveTickColor2 = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, false);
            final float trackInsideCornerSize5 = trackInsideCornerSize2;
            $dirty2 = $dirty;
            final long activeTickColor2 = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, true);
            boolean enabled5 = enabled2;
            SliderColors colors8 = colors2;
            Modifier modifierM1065height3ABfNKs2 = SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), SliderKt.getTrackHeight());
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            Modifier modifier7 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            objConsume = $composer2.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (objConsume == LayoutDirection.Rtl) {
                f2 = 180.0f;
            } else {
                f2 = 0.0f;
            }
            Modifier modifierRotate2 = RotateKt.rotate(modifierM1065height3ABfNKs2, f2);
            ComposerKt.sourceInformationMarkerStart($composer2, -800841850, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance2 = $composer2.changedInstance(sliderState) | $composer2.changed(inactiveTrackColor2) | $composer2.changed(activeTrackColor2) | $composer2.changed(inactiveTickColor2) | $composer2.changed(activeTickColor2);
            if (($dirty2 & 3670016) == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z12 = zChangedInstance2 | z2;
            if (($dirty2 & 29360128) == 8388608) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z13 = z12 | z3;
            if ((($dirty2 & 57344) ^ 24576) > 16384) {
                $dirty3 = $dirty2;
                if (($dirty3 & 24576) != 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            } else {
                $dirty3 = $dirty2;
                if (($dirty3 & 24576) != 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            }
            boolean z14 = z13 | z4;
            if ((458752 & $dirty3) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv2 = z14 | z5;
            value$iv2 = $composer2.rememberedValue();
            if (!invalid$iv2) {
            }
            final Function2<? super DrawScope, ? super Offset, Unit> function16 = function6;
            value$iv2 = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$6$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope $this$Canvas) {
                    SliderDefaults.INSTANCE.m2864drawTrackngJ0SCU($this$Canvas, sliderState.getTickFractions(), 0.0f, sliderState.getCoercedValueAsFraction$material3_release(), inactiveTrackColor2, activeTrackColor2, inactiveTickColor2, activeTickColor2, $this$Canvas.mo722toDpu2uoSUM(sliderState.getTrackHeight$material3_release()), $this$Canvas.mo723toDpu2uoSUM(0), $this$Canvas.mo722toDpu2uoSUM(sliderState.getThumbWidth$material3_release()), thumbTrackGapSize6, trackInsideCornerSize5, function16, function15, false);
                }
            };
            $composer2.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierRotate2, (Function1) value$iv2, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function8 = function15;
            thumbTrackGapSize4 = thumbTrackGapSize6;
            trackInsideCornerSize3 = trackInsideCornerSize5;
            enabled3 = enabled5;
            colors3 = colors8;
            modifier4 = modifier7;
            function9 = function6;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors9 = colors($composer2, ($dirty4 >> 24) & 14);
                    $dirty4 &= -7169;
                    colors2 = colors9;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -800875397, "CC(remember):Slider.kt#9igjgp");
                    if ((($dirty4 & 7168) ^ 3072) <= 2048) {
                    }
                    if (($dirty4 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    invalid$iv = z6 | z;
                    value$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                            m2873invokeUv8p0NA(drawScope, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m2873invokeUv8p0NA(DrawScope $this$null, long it) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty4 &= -57345;
                    function4 = (Function2) value$iv;
                }
                if (i7 != 0) {
                    function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.5
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                            m2874invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                        public final void m2874invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                        }
                    };
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                } else {
                    thumbTrackGapSize2 = thumbTrackGapSize;
                }
                if (i9 != 0) {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                } else {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = f;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors10 = colors($composer2, ($dirty4 >> 24) & 14);
                    $dirty4 &= -7169;
                    colors2 = colors10;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -800875397, "CC(remember):Slider.kt#9igjgp");
                    if ((($dirty4 & 7168) ^ 3072) <= 2048) {
                    }
                    if (($dirty4 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    invalid$iv = z6 | z;
                    value$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                            m2873invokeUv8p0NA(drawScope, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m2873invokeUv8p0NA(DrawScope $this$null, long it) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty4 &= -57345;
                    function4 = (Function2) value$iv;
                }
                if (i7 != 0) {
                    function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.5
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                            m2874invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                        public final void m2874invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                        }
                    };
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                } else {
                    thumbTrackGapSize2 = thumbTrackGapSize;
                }
                if (i9 != 0) {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                } else {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = f;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(49984771, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1128)");
            }
            final long inactiveTrackColor3 = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, false);
            final long activeTrackColor3 = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true);
            final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function17 = function7;
            final float thumbTrackGapSize7 = thumbTrackGapSize3;
            final long inactiveTickColor3 = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, false);
            final float trackInsideCornerSize6 = trackInsideCornerSize2;
            $dirty2 = $dirty;
            final long activeTickColor3 = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, true);
            boolean enabled6 = enabled2;
            SliderColors colors11 = colors2;
            Modifier modifierM1065height3ABfNKs3 = SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), SliderKt.getTrackHeight());
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            Modifier modifier8 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            objConsume = $composer2.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (objConsume == LayoutDirection.Rtl) {
                f2 = 180.0f;
            } else {
                f2 = 0.0f;
            }
            Modifier modifierRotate3 = RotateKt.rotate(modifierM1065height3ABfNKs3, f2);
            ComposerKt.sourceInformationMarkerStart($composer2, -800841850, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance3 = $composer2.changedInstance(sliderState) | $composer2.changed(inactiveTrackColor3) | $composer2.changed(activeTrackColor3) | $composer2.changed(inactiveTickColor3) | $composer2.changed(activeTickColor3);
            if (($dirty2 & 3670016) == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z15 = zChangedInstance3 | z2;
            if (($dirty2 & 29360128) == 8388608) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z16 = z15 | z3;
            if ((($dirty2 & 57344) ^ 24576) > 16384) {
                $dirty3 = $dirty2;
                if (($dirty3 & 24576) != 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            } else {
                $dirty3 = $dirty2;
                if (($dirty3 & 24576) != 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            }
            boolean z17 = z16 | z4;
            if ((458752 & $dirty3) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv2 = z17 | z5;
            value$iv2 = $composer2.rememberedValue();
            if (!invalid$iv2) {
            }
            final Function2<? super DrawScope, ? super Offset, Unit> function18 = function6;
            value$iv2 = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$6$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope $this$Canvas) {
                    SliderDefaults.INSTANCE.m2864drawTrackngJ0SCU($this$Canvas, sliderState.getTickFractions(), 0.0f, sliderState.getCoercedValueAsFraction$material3_release(), inactiveTrackColor3, activeTrackColor3, inactiveTickColor3, activeTickColor3, $this$Canvas.mo722toDpu2uoSUM(sliderState.getTrackHeight$material3_release()), $this$Canvas.mo723toDpu2uoSUM(0), $this$Canvas.mo722toDpu2uoSUM(sliderState.getThumbWidth$material3_release()), thumbTrackGapSize7, trackInsideCornerSize6, function18, function17, false);
                }
            };
            $composer2.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierRotate3, (Function1) value$iv2, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function8 = function17;
            thumbTrackGapSize4 = thumbTrackGapSize7;
            trackInsideCornerSize3 = trackInsideCornerSize6;
            enabled3 = enabled6;
            colors3 = colors11;
            modifier4 = modifier8;
            function9 = function6;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier9 = modifier4;
            final boolean z18 = enabled3;
            final SliderColors sliderColors2 = colors3;
            final Function2<? super DrawScope, ? super Offset, Unit> function19 = function9;
            final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function110 = function8;
            final float f5 = thumbTrackGapSize4;
            final float f6 = trackInsideCornerSize3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.7
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i10) {
                    SliderDefaults.this.m2868Track4EFweAY(sliderState, modifier9, z18, sliderColors2, function19, function110, f5, f6, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacyRangeSliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(rangeSliderState, modifier, colors, enabled, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    public final /* synthetic */ void Track(final RangeSliderState rangeSliderState, Modifier modifier, SliderColors colors, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors sliderColors;
        boolean z;
        SliderColors colors2;
        int $dirty;
        Modifier modifier3;
        SliderColors colors3;
        boolean enabled2;
        Composer $composer2 = $composer.startRestartGroup(-1617869097);
        ComposerKt.sourceInformation($composer2, "C(Track)P(3,2)1187@52807L8,1190@52865L218:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(rangeSliderState) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sliderColors = colors;
                int i3 = $composer2.changed(sliderColors) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                sliderColors = colors;
            }
            $dirty2 |= i3;
        } else {
            sliderColors = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(this) ? 16384 : 8192;
        }
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors3 = sliderColors;
            enabled2 = z;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    colors2 = colors($composer2, ($dirty2 >> 12) & 14);
                    $dirty2 &= -897;
                } else {
                    colors2 = sliderColors;
                }
                if (i4 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    colors3 = colors2;
                    enabled2 = true;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    colors3 = colors2;
                    enabled2 = z;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                colors3 = sliderColors;
                enabled2 = z;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1617869097, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1189)");
            }
            m2867Track4EFweAY(rangeSliderState, modifier3, enabled2, colors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, $composer2, ($dirty & 14) | 14155776 | ($dirty & 112) | (($dirty >> 3) & 896) | (($dirty << 3) & 7168) | (234881024 & ($dirty << 12)), 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final SliderColors sliderColors2 = colors3;
            final boolean z2 = enabled2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.8
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    SliderDefaults.this.Track(rangeSliderState, modifier5, sliderColors2, z2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:105:0x013f  */
    /* JADX WARN: Code duplicated, block: B:107:0x0146  */
    /* JADX WARN: Code duplicated, block: B:117:0x0172 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:118:0x0174  */
    /* JADX WARN: Code duplicated, block: B:119:0x0179  */
    /* JADX WARN: Code duplicated, block: B:121:0x017d  */
    /* JADX WARN: Code duplicated, block: B:124:0x0183  */
    /* JADX WARN: Code duplicated, block: B:127:0x0192  */
    /* JADX WARN: Code duplicated, block: B:129:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:131:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:137:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:138:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:141:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:145:0x01d5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:148:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:150:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:152:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:153:0x0203  */
    /* JADX WARN: Code duplicated, block: B:155:0x0207  */
    /* JADX WARN: Code duplicated, block: B:156:0x0213  */
    /* JADX WARN: Code duplicated, block: B:159:0x0223  */
    /* JADX WARN: Code duplicated, block: B:162:0x027b  */
    /* JADX WARN: Code duplicated, block: B:163:0x027e  */
    /* JADX WARN: Code duplicated, block: B:166:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:167:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:170:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:171:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:174:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:178:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:180:0x02d8 A[PHI: r7
  0x02d8: PHI (r7v29 '$dirty' int) = (r7v27 '$dirty' int), (r7v31 '$dirty' int) binds: [B:179:0x02d6, B:177:0x02cd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:181:0x02da  */
    /* JADX WARN: Code duplicated, block: B:184:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:185:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:188:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:192:0x0305 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:196:0x033e  */
    /* JADX WARN: Code duplicated, block: B:200:0x0357  */
    /* JADX WARN: Code duplicated, block: B:201:0x037c  */
    /* JADX INFO: renamed from: Track-4EFweAY, reason: not valid java name */
    public final void m2867Track4EFweAY(final RangeSliderState rangeSliderState, Modifier modifier, boolean enabled, SliderColors colors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float thumbTrackGapSize, float trackInsideCornerSize, Composer $composer, final int $changed, final int i) {
        final boolean enabled2;
        final SliderColors colors2;
        Function2<? super DrawScope, ? super Offset, Unit> function4;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function5;
        float f;
        int i2;
        Modifier.Companion modifier2;
        float thumbTrackGapSize2;
        float thumbTrackGapSize3;
        int $dirty;
        float trackInsideCornerSize2;
        Function2<? super DrawScope, ? super Offset, Unit> function6;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function7;
        Modifier modifier3;
        boolean z;
        boolean invalid$iv;
        Object value$iv;
        int $dirty2;
        Object objConsume;
        float f2;
        boolean z2;
        boolean z3;
        int $dirty3;
        boolean z4;
        boolean z5;
        boolean invalid$iv2;
        Object value$iv2;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function8;
        float thumbTrackGapSize4;
        float trackInsideCornerSize3;
        boolean enabled3;
        SliderColors colors3;
        Modifier modifier4;
        Function2<? super DrawScope, ? super Offset, Unit> function9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer2 = $composer.startRestartGroup(-541824132);
        ComposerKt.sourceInformation($composer2, "C(Track)P(5,4,3!3,6:c#ui.unit.Dp,7:c#ui.unit.Dp)1222@54320L8,1223@54389L232,1245@55370L7,1246@55426L706,1241@55223L909:Slider.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer2.changedInstance(rangeSliderState) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty4 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty4 |= $composer2.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i5 = $composer2.changed(colors2) ? 2048 : 1024;
                $dirty4 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i5;
        } else {
            colors2 = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                function4 = function2;
                int i6 = $composer2.changedInstance(function4) ? 16384 : 8192;
                $dirty4 |= i6;
            } else {
                function4 = function2;
            }
            $dirty4 |= i6;
        } else {
            function4 = function2;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function5 = function3;
            $dirty4 |= $composer2.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function3;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty4 |= $composer2.changed(thumbTrackGapSize) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty4 |= 12582912;
            f = trackInsideCornerSize;
        } else if (($changed & 12582912) == 0) {
            f = trackInsideCornerSize;
            $dirty4 |= $composer2.changed(f) ? 8388608 : 4194304;
        } else {
            f = trackInsideCornerSize;
        }
        if ((i & 256) == 0) {
            if ((100663296 & $changed) == 0) {
                i2 = $composer2.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            if ((38347923 & $dirty4) == 38347922 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    }
                    if ((i & 8) != 0) {
                        SliderColors colors4 = colors($composer2, ($dirty4 >> 24) & 14);
                        $dirty4 &= -7169;
                        colors2 = colors4;
                    }
                    if ((i & 16) != 0) {
                        ComposerKt.sourceInformationMarkerStart($composer2, -800729349, "CC(remember):Slider.kt#9igjgp");
                        boolean z6 = ((($dirty4 & 7168) ^ 3072) <= 2048 && $composer2.changed(colors2)) || ($dirty4 & 3072) == 2048;
                        if (($dirty4 & 896) == 256) {
                            z = true;
                        } else {
                            z = false;
                        }
                        invalid$iv = z6 | z;
                        value$iv = $composer2.rememberedValue();
                        if (!invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$9$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                                    m2875invokeUv8p0NA(drawScope, offset.getPackedValue());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                                public final void m2875invokeUv8p0NA(DrawScope $this$null, long it) {
                                    SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                                }
                            };
                            $composer2.updateRememberedValue(value$iv);
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $dirty4 &= -57345;
                        function4 = (Function2) value$iv;
                    }
                    if (i7 != 0) {
                        function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.10
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m2872invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m2872invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                                SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                            }
                        };
                    }
                    if (i8 != 0) {
                        thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                    } else {
                        thumbTrackGapSize2 = thumbTrackGapSize;
                    }
                    if (i9 != 0) {
                        thumbTrackGapSize3 = thumbTrackGapSize2;
                        $dirty = $dirty4;
                        trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                        function6 = function4;
                        function7 = function5;
                        modifier3 = modifier2;
                    } else {
                        thumbTrackGapSize3 = thumbTrackGapSize2;
                        $dirty = $dirty4;
                        trackInsideCornerSize2 = f;
                        function6 = function4;
                        function7 = function5;
                        modifier3 = modifier2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty4 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        thumbTrackGapSize3 = thumbTrackGapSize;
                        $dirty = (-57345) & $dirty4;
                        trackInsideCornerSize2 = f;
                        function6 = function4;
                        function7 = function5;
                        modifier3 = modifier;
                    } else {
                        modifier3 = modifier;
                        thumbTrackGapSize3 = thumbTrackGapSize;
                        $dirty = $dirty4;
                        trackInsideCornerSize2 = f;
                        function6 = function4;
                        function7 = function5;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-541824132, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1236)");
                }
                final long inactiveTrackColor = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, false);
                final long activeTrackColor = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true);
                final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function10 = function7;
                final float thumbTrackGapSize5 = thumbTrackGapSize3;
                final long inactiveTickColor = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, false);
                final float trackInsideCornerSize4 = trackInsideCornerSize2;
                $dirty2 = $dirty;
                final long activeTickColor = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, true);
                boolean enabled4 = enabled2;
                SliderColors colors5 = colors2;
                Modifier modifierM1065height3ABfNKs = SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), SliderKt.getTrackHeight());
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                Modifier modifier5 = modifier3;
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                objConsume = $composer2.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (objConsume == LayoutDirection.Rtl) {
                    f2 = 180.0f;
                } else {
                    f2 = 0.0f;
                }
                Modifier modifierRotate = RotateKt.rotate(modifierM1065height3ABfNKs, f2);
                ComposerKt.sourceInformationMarkerStart($composer2, -800695691, "CC(remember):Slider.kt#9igjgp");
                boolean zChangedInstance = $composer2.changedInstance(rangeSliderState) | $composer2.changed(inactiveTrackColor) | $composer2.changed(activeTrackColor) | $composer2.changed(inactiveTickColor) | $composer2.changed(activeTickColor);
                if (($dirty2 & 3670016) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z7 = zChangedInstance | z2;
                if (($dirty2 & 29360128) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z8 = z7 | z3;
                if ((($dirty2 & 57344) ^ 24576) > 16384 || !$composer2.changed(function6)) {
                    $dirty3 = $dirty2;
                    if (($dirty3 & 24576) != 16384) {
                        z4 = false;
                    }
                    boolean z9 = z8 | z4;
                    if ((458752 & $dirty3) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv2 = z9 | z5;
                    value$iv2 = $composer2.rememberedValue();
                    if (!invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                        final Function2<? super DrawScope, ? super Offset, Unit> function11 = function6;
                        value$iv2 = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$11$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                                invoke2(drawScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DrawScope $this$Canvas) {
                                SliderDefaults.INSTANCE.m2864drawTrackngJ0SCU($this$Canvas, rangeSliderState.getTickFractions(), rangeSliderState.getCoercedActiveRangeStartAsFraction$material3_release(), rangeSliderState.getCoercedActiveRangeEndAsFraction$material3_release(), inactiveTrackColor, activeTrackColor, inactiveTickColor, activeTickColor, $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getTrackHeight$material3_release()), $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getStartThumbWidth$material3_release()), $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getEndThumbWidth$material3_release()), thumbTrackGapSize5, trackInsideCornerSize4, function11, function10, true);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv2);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    CanvasKt.Canvas(modifierRotate, (Function1) value$iv2, $composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    thumbTrackGapSize4 = thumbTrackGapSize5;
                    trackInsideCornerSize3 = trackInsideCornerSize4;
                    enabled3 = enabled4;
                    colors3 = colors5;
                    modifier4 = modifier5;
                    function9 = function6;
                } else {
                    $dirty3 = $dirty2;
                }
                z4 = true;
                boolean z10 = z8 | z4;
                if ((458752 & $dirty3) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv2 = z10 | z5;
                value$iv2 = $composer2.rememberedValue();
                if (!invalid$iv2) {
                }
                final Function2<? super DrawScope, ? super Offset, Unit> function12 = function6;
                value$iv2 = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$11$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$Canvas) {
                        SliderDefaults.INSTANCE.m2864drawTrackngJ0SCU($this$Canvas, rangeSliderState.getTickFractions(), rangeSliderState.getCoercedActiveRangeStartAsFraction$material3_release(), rangeSliderState.getCoercedActiveRangeEndAsFraction$material3_release(), inactiveTrackColor, activeTrackColor, inactiveTickColor, activeTickColor, $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getTrackHeight$material3_release()), $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getStartThumbWidth$material3_release()), $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getEndThumbWidth$material3_release()), thumbTrackGapSize5, trackInsideCornerSize4, function12, function10, true);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierRotate, (Function1) value$iv2, $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function10;
                thumbTrackGapSize4 = thumbTrackGapSize5;
                trackInsideCornerSize3 = trackInsideCornerSize4;
                enabled3 = enabled4;
                colors3 = colors5;
                modifier4 = modifier5;
                function9 = function6;
            } else {
                $composer2.skipToGroupEnd();
                thumbTrackGapSize4 = thumbTrackGapSize;
                trackInsideCornerSize3 = f;
                function9 = function4;
                function8 = function5;
                modifier4 = modifier;
                enabled3 = enabled2;
                colors3 = colors2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier4;
                final boolean z11 = enabled3;
                final SliderColors sliderColors = colors3;
                final Function2<? super DrawScope, ? super Offset, Unit> function13 = function9;
                final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function14 = function8;
                final float f3 = thumbTrackGapSize4;
                final float f4 = trackInsideCornerSize3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.12
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer, int i10) {
                        SliderDefaults.this.m2867Track4EFweAY(rangeSliderState, modifier6, z11, sliderColors, function13, function14, f3, f4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                    }
                });
            }
        }
        i2 = 100663296;
        $dirty4 |= i2;
        if ((38347923 & $dirty4) == 38347922) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors6 = colors($composer2, ($dirty4 >> 24) & 14);
                    $dirty4 &= -7169;
                    colors2 = colors6;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -800729349, "CC(remember):Slider.kt#9igjgp");
                    if ((($dirty4 & 7168) ^ 3072) <= 2048) {
                    }
                    if (($dirty4 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    invalid$iv = z6 | z;
                    value$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                            m2875invokeUv8p0NA(drawScope, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m2875invokeUv8p0NA(DrawScope $this$null, long it) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty4 &= -57345;
                    function4 = (Function2) value$iv;
                }
                if (i7 != 0) {
                    function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.10
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                            m2872invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                        public final void m2872invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                        }
                    };
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                } else {
                    thumbTrackGapSize2 = thumbTrackGapSize;
                }
                if (i9 != 0) {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                } else {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = f;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors7 = colors($composer2, ($dirty4 >> 24) & 14);
                    $dirty4 &= -7169;
                    colors2 = colors7;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -800729349, "CC(remember):Slider.kt#9igjgp");
                    if ((($dirty4 & 7168) ^ 3072) <= 2048) {
                    }
                    if (($dirty4 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    invalid$iv = z6 | z;
                    value$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                            m2875invokeUv8p0NA(drawScope, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m2875invokeUv8p0NA(DrawScope $this$null, long it) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty4 &= -57345;
                    function4 = (Function2) value$iv;
                }
                if (i7 != 0) {
                    function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.10
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                            m2872invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                        public final void m2872invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                        }
                    };
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                } else {
                    thumbTrackGapSize2 = thumbTrackGapSize;
                }
                if (i9 != 0) {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                } else {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = f;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-541824132, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1236)");
            }
            final long inactiveTrackColor2 = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, false);
            final long activeTrackColor2 = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true);
            final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function15 = function7;
            final float thumbTrackGapSize6 = thumbTrackGapSize3;
            final long inactiveTickColor2 = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, false);
            final float trackInsideCornerSize5 = trackInsideCornerSize2;
            $dirty2 = $dirty;
            final long activeTickColor2 = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, true);
            boolean enabled5 = enabled2;
            SliderColors colors8 = colors2;
            Modifier modifierM1065height3ABfNKs2 = SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), SliderKt.getTrackHeight());
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            Modifier modifier7 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            objConsume = $composer2.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (objConsume == LayoutDirection.Rtl) {
                f2 = 180.0f;
            } else {
                f2 = 0.0f;
            }
            Modifier modifierRotate2 = RotateKt.rotate(modifierM1065height3ABfNKs2, f2);
            ComposerKt.sourceInformationMarkerStart($composer2, -800695691, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance2 = $composer2.changedInstance(rangeSliderState) | $composer2.changed(inactiveTrackColor2) | $composer2.changed(activeTrackColor2) | $composer2.changed(inactiveTickColor2) | $composer2.changed(activeTickColor2);
            if (($dirty2 & 3670016) == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z12 = zChangedInstance2 | z2;
            if (($dirty2 & 29360128) == 8388608) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z13 = z12 | z3;
            if ((($dirty2 & 57344) ^ 24576) > 16384) {
                $dirty3 = $dirty2;
                if (($dirty3 & 24576) != 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            } else {
                $dirty3 = $dirty2;
                if (($dirty3 & 24576) != 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            }
            boolean z14 = z13 | z4;
            if ((458752 & $dirty3) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv2 = z14 | z5;
            value$iv2 = $composer2.rememberedValue();
            if (!invalid$iv2) {
            }
            final Function2<? super DrawScope, ? super Offset, Unit> function16 = function6;
            value$iv2 = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$11$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope $this$Canvas) {
                    SliderDefaults.INSTANCE.m2864drawTrackngJ0SCU($this$Canvas, rangeSliderState.getTickFractions(), rangeSliderState.getCoercedActiveRangeStartAsFraction$material3_release(), rangeSliderState.getCoercedActiveRangeEndAsFraction$material3_release(), inactiveTrackColor2, activeTrackColor2, inactiveTickColor2, activeTickColor2, $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getTrackHeight$material3_release()), $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getStartThumbWidth$material3_release()), $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getEndThumbWidth$material3_release()), thumbTrackGapSize6, trackInsideCornerSize5, function16, function15, true);
                }
            };
            $composer2.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierRotate2, (Function1) value$iv2, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function8 = function15;
            thumbTrackGapSize4 = thumbTrackGapSize6;
            trackInsideCornerSize3 = trackInsideCornerSize5;
            enabled3 = enabled5;
            colors3 = colors8;
            modifier4 = modifier7;
            function9 = function6;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors9 = colors($composer2, ($dirty4 >> 24) & 14);
                    $dirty4 &= -7169;
                    colors2 = colors9;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -800729349, "CC(remember):Slider.kt#9igjgp");
                    if ((($dirty4 & 7168) ^ 3072) <= 2048) {
                    }
                    if (($dirty4 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    invalid$iv = z6 | z;
                    value$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                            m2875invokeUv8p0NA(drawScope, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m2875invokeUv8p0NA(DrawScope $this$null, long it) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty4 &= -57345;
                    function4 = (Function2) value$iv;
                }
                if (i7 != 0) {
                    function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.10
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                            m2872invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                        public final void m2872invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                        }
                    };
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                } else {
                    thumbTrackGapSize2 = thumbTrackGapSize;
                }
                if (i9 != 0) {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                } else {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = f;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors10 = colors($composer2, ($dirty4 >> 24) & 14);
                    $dirty4 &= -7169;
                    colors2 = colors10;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -800729349, "CC(remember):Slider.kt#9igjgp");
                    if ((($dirty4 & 7168) ^ 3072) <= 2048) {
                    }
                    if (($dirty4 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    invalid$iv = z6 | z;
                    value$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                            m2875invokeUv8p0NA(drawScope, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m2875invokeUv8p0NA(DrawScope $this$null, long it) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, it, SliderDefaults.INSTANCE.m2871getTrackStopIndicatorSizeD9Ej5fM(), colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty4 &= -57345;
                    function4 = (Function2) value$iv;
                }
                if (i7 != 0) {
                    function5 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.10
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                            m2872invokewPWG1Vc(drawScope, offset.getPackedValue(), color.m4544unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                        public final void m2872invokewPWG1Vc(DrawScope $this$null, long offset, long color) {
                            SliderDefaults.INSTANCE.m2863drawStopIndicatorx3O1jOs($this$null, offset, SliderDefaults.INSTANCE.m2870getTickSizeD9Ej5fM(), color);
                        }
                    };
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                } else {
                    thumbTrackGapSize2 = thumbTrackGapSize;
                }
                if (i9 != 0) {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                } else {
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    $dirty = $dirty4;
                    trackInsideCornerSize2 = f;
                    function6 = function4;
                    function7 = function5;
                    modifier3 = modifier2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-541824132, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1236)");
            }
            final long inactiveTrackColor3 = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, false);
            final long activeTrackColor3 = colors2.m2860trackColorWaAFU9c$material3_release(enabled2, true);
            final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function17 = function7;
            final float thumbTrackGapSize7 = thumbTrackGapSize3;
            final long inactiveTickColor3 = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, false);
            final float trackInsideCornerSize6 = trackInsideCornerSize2;
            $dirty2 = $dirty;
            final long activeTickColor3 = colors2.m2859tickColorWaAFU9c$material3_release(enabled2, true);
            boolean enabled6 = enabled2;
            SliderColors colors11 = colors2;
            Modifier modifierM1065height3ABfNKs3 = SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), SliderKt.getTrackHeight());
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            Modifier modifier8 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            objConsume = $composer2.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (objConsume == LayoutDirection.Rtl) {
                f2 = 180.0f;
            } else {
                f2 = 0.0f;
            }
            Modifier modifierRotate3 = RotateKt.rotate(modifierM1065height3ABfNKs3, f2);
            ComposerKt.sourceInformationMarkerStart($composer2, -800695691, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance3 = $composer2.changedInstance(rangeSliderState) | $composer2.changed(inactiveTrackColor3) | $composer2.changed(activeTrackColor3) | $composer2.changed(inactiveTickColor3) | $composer2.changed(activeTickColor3);
            if (($dirty2 & 3670016) == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z15 = zChangedInstance3 | z2;
            if (($dirty2 & 29360128) == 8388608) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z16 = z15 | z3;
            if ((($dirty2 & 57344) ^ 24576) > 16384) {
                $dirty3 = $dirty2;
                if (($dirty3 & 24576) != 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            } else {
                $dirty3 = $dirty2;
                if (($dirty3 & 24576) != 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            }
            boolean z17 = z16 | z4;
            if ((458752 & $dirty3) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv2 = z17 | z5;
            value$iv2 = $composer2.rememberedValue();
            if (!invalid$iv2) {
            }
            final Function2<? super DrawScope, ? super Offset, Unit> function18 = function6;
            value$iv2 = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$11$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope $this$Canvas) {
                    SliderDefaults.INSTANCE.m2864drawTrackngJ0SCU($this$Canvas, rangeSliderState.getTickFractions(), rangeSliderState.getCoercedActiveRangeStartAsFraction$material3_release(), rangeSliderState.getCoercedActiveRangeEndAsFraction$material3_release(), inactiveTrackColor3, activeTrackColor3, inactiveTickColor3, activeTickColor3, $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getTrackHeight$material3_release()), $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getStartThumbWidth$material3_release()), $this$Canvas.mo722toDpu2uoSUM(rangeSliderState.getEndThumbWidth$material3_release()), thumbTrackGapSize7, trackInsideCornerSize6, function18, function17, true);
                }
            };
            $composer2.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierRotate3, (Function1) value$iv2, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function8 = function17;
            thumbTrackGapSize4 = thumbTrackGapSize7;
            trackInsideCornerSize3 = trackInsideCornerSize6;
            enabled3 = enabled6;
            colors3 = colors11;
            modifier4 = modifier8;
            function9 = function6;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier9 = modifier4;
            final boolean z18 = enabled3;
            final SliderColors sliderColors2 = colors3;
            final Function2<? super DrawScope, ? super Offset, Unit> function19 = function9;
            final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function110 = function8;
            final float f5 = thumbTrackGapSize4;
            final float f6 = trackInsideCornerSize3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults.Track.12
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i10) {
                    SliderDefaults.this.m2867Track4EFweAY(rangeSliderState, modifier9, z18, sliderColors2, function19, function110, f5, f6, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:56:0x0261 A[PHI: r38
  0x0261: PHI (r38v4 'end' long) = (r38v3 'end' long), (r38v5 'end' long) binds: [B:55:0x025f, B:51:0x024c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX INFO: renamed from: drawTrack-ngJ0SCU, reason: not valid java name */
    public final void m2864drawTrackngJ0SCU(DrawScope $this$drawTrack_u2dngJ0SCU, float[] tickFractions, float activeRangeStart, float activeRangeEnd, long inactiveTrackColor, long activeTrackColor, long inactiveTickColor, long activeTickColor, float height, float startThumbWidth, float endThumbWidth, float thumbTrackGapSize, float trackInsideCornerSize, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, boolean isRangeSlider) {
        float startGap;
        float endGap;
        float trackStrokeWidth;
        float trackStrokeWidth2;
        float[] $this$forEachIndexed$iv;
        int i;
        long start;
        long end;
        long sliderStart = OffsetKt.Offset(0.0f, Offset.m4294getYimpl($this$drawTrack_u2dngJ0SCU.mo5089getCenterF1C5BW0()));
        long sliderEnd = OffsetKt.Offset(Size.m4362getWidthimpl($this$drawTrack_u2dngJ0SCU.mo5090getSizeNHjbRc()), Offset.m4294getYimpl($this$drawTrack_u2dngJ0SCU.mo5089getCenterF1C5BW0()));
        float trackStrokeWidth3 = $this$drawTrack_u2dngJ0SCU.mo726toPx0680j_4(height);
        long sliderValueEnd = OffsetKt.Offset(Offset.m4293getXimpl(sliderStart) + ((Offset.m4293getXimpl(sliderEnd) - Offset.m4293getXimpl(sliderStart)) * activeRangeEnd), Offset.m4294getYimpl($this$drawTrack_u2dngJ0SCU.mo5089getCenterF1C5BW0()));
        long sliderValueStart = OffsetKt.Offset(Offset.m4293getXimpl(sliderStart) + ((Offset.m4293getXimpl(sliderEnd) - Offset.m4293getXimpl(sliderStart)) * activeRangeStart), Offset.m4294getYimpl($this$drawTrack_u2dngJ0SCU.mo5089getCenterF1C5BW0()));
        float f = 2;
        float cornerSize = trackStrokeWidth3 / f;
        float insideCornerSize = $this$drawTrack_u2dngJ0SCU.mo726toPx0680j_4(trackInsideCornerSize);
        int $this$dp$iv = Dp.m7015compareTo0680j_4(thumbTrackGapSize, Dp.m7016constructorimpl(0));
        if ($this$dp$iv > 0) {
            float startGap2 = ($this$drawTrack_u2dngJ0SCU.mo726toPx0680j_4(startThumbWidth) / f) + $this$drawTrack_u2dngJ0SCU.mo726toPx0680j_4(thumbTrackGapSize);
            float endGap2 = ($this$drawTrack_u2dngJ0SCU.mo726toPx0680j_4(endThumbWidth) / f) + $this$drawTrack_u2dngJ0SCU.mo726toPx0680j_4(thumbTrackGapSize);
            startGap = startGap2;
            endGap = endGap2;
        } else {
            startGap = 0.0f;
            endGap = 0.0f;
        }
        if (!isRangeSlider || Offset.m4293getXimpl(sliderValueStart) <= Offset.m4293getXimpl(sliderStart) + startGap + cornerSize) {
            trackStrokeWidth = trackStrokeWidth3;
        } else {
            float start2 = Offset.m4293getXimpl(sliderStart);
            float end2 = Offset.m4293getXimpl(sliderValueStart) - startGap;
            trackStrokeWidth = trackStrokeWidth3;
            m2865drawTrackPathCx2C_VA($this$drawTrack_u2dngJ0SCU, Offset.INSTANCE.m4309getZeroF1C5BW0(), androidx.compose.ui.geometry.SizeKt.Size(end2 - start2, trackStrokeWidth3), inactiveTrackColor, cornerSize, insideCornerSize);
            if (function2 != null) {
                function2.invoke($this$drawTrack_u2dngJ0SCU, Offset.m4282boximpl(OffsetKt.Offset(start2 + cornerSize, Offset.m4294getYimpl($this$drawTrack_u2dngJ0SCU.mo5089getCenterF1C5BW0()))));
            }
        }
        if (Offset.m4293getXimpl(sliderValueEnd) >= (Offset.m4293getXimpl(sliderEnd) - endGap) - cornerSize) {
            trackStrokeWidth2 = trackStrokeWidth;
        } else {
            float start3 = Offset.m4293getXimpl(sliderValueEnd) + endGap;
            float end3 = Offset.m4293getXimpl(sliderEnd);
            float trackStrokeWidth4 = trackStrokeWidth;
            trackStrokeWidth2 = trackStrokeWidth4;
            m2865drawTrackPathCx2C_VA($this$drawTrack_u2dngJ0SCU, OffsetKt.Offset(start3, 0.0f), androidx.compose.ui.geometry.SizeKt.Size(end3 - start3, trackStrokeWidth4), inactiveTrackColor, insideCornerSize, cornerSize);
            if (function2 != null) {
                function2.invoke($this$drawTrack_u2dngJ0SCU, Offset.m4282boximpl(OffsetKt.Offset(end3 - cornerSize, Offset.m4294getYimpl($this$drawTrack_u2dngJ0SCU.mo5089getCenterF1C5BW0()))));
            }
        }
        float activeTrackStart = isRangeSlider ? Offset.m4293getXimpl(sliderValueStart) + startGap : 0.0f;
        float activeTrackEnd = Offset.m4293getXimpl(sliderValueEnd) - endGap;
        float startCornerRadius = isRangeSlider ? insideCornerSize : cornerSize;
        if (activeTrackEnd - activeTrackStart > startCornerRadius) {
            m2865drawTrackPathCx2C_VA($this$drawTrack_u2dngJ0SCU, OffsetKt.Offset(activeTrackStart, 0.0f), androidx.compose.ui.geometry.SizeKt.Size(activeTrackEnd - activeTrackStart, trackStrokeWidth2), activeTrackColor, startCornerRadius, insideCornerSize);
        }
        long start4 = OffsetKt.Offset(Offset.m4293getXimpl(sliderStart) + cornerSize, Offset.m4294getYimpl(sliderStart));
        long end4 = OffsetKt.Offset(Offset.m4293getXimpl(sliderEnd) - cornerSize, Offset.m4294getYimpl(sliderEnd));
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo = RangesKt.rangeTo(Offset.m4293getXimpl(sliderValueStart) - startGap, Offset.m4293getXimpl(sliderValueStart) + startGap);
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo2 = RangesKt.rangeTo(Offset.m4293getXimpl(sliderValueEnd) - endGap, Offset.m4293getXimpl(sliderValueEnd) + endGap);
        float[] $this$forEachIndexed$iv2 = tickFractions;
        int index$iv = 0;
        int length = $this$forEachIndexed$iv2.length;
        int $i$f$forEachIndexed = 0;
        while ($i$f$forEachIndexed < length) {
            float item$iv = $this$forEachIndexed$iv2[$i$f$forEachIndexed];
            int index$iv2 = index$iv + 1;
            boolean z = true;
            if (function2 == null) {
                $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                i = length;
            } else {
                if (isRangeSlider && index$iv == 0) {
                    $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                    i = length;
                } else {
                    $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                    i = length;
                    if (index$iv == tickFractions.length - 1) {
                    }
                    $i$f$forEachIndexed++;
                    index$iv = index$iv2;
                    $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                    length = i;
                    end4 = end;
                    start4 = start;
                }
                start = start4;
                end = end4;
                $i$f$forEachIndexed++;
                index$iv = index$iv2;
                $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                length = i;
                end4 = end;
                start4 = start;
            }
            if (item$iv <= activeRangeEnd && item$iv >= activeRangeStart) {
                z = false;
            }
            boolean outsideFraction = z;
            start = start4;
            long center = OffsetKt.Offset(Offset.m4293getXimpl(OffsetKt.m4316lerpWko1d7g(start4, end4, item$iv)), Offset.m4294getYimpl($this$drawTrack_u2dngJ0SCU.mo5089getCenterF1C5BW0()));
            if (isRangeSlider) {
                end = end4;
                if (!closedFloatingPointRangeRangeTo.contains(Float.valueOf(Offset.m4293getXimpl(center)))) {
                }
                $i$f$forEachIndexed++;
                index$iv = index$iv2;
                $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                length = i;
                end4 = end;
                start4 = start;
            } else {
                end = end4;
            }
            if (!closedFloatingPointRangeRangeTo2.contains(Float.valueOf(Offset.m4293getXimpl(center)))) {
                function3.invoke($this$drawTrack_u2dngJ0SCU, Offset.m4282boximpl(center), Color.m4524boximpl(outsideFraction ? inactiveTickColor : activeTickColor));
            }
            $i$f$forEachIndexed++;
            index$iv = index$iv2;
            $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
            length = i;
            end4 = end;
            start4 = start;
        }
    }

    /* JADX INFO: renamed from: drawTrackPath-Cx2C_VA, reason: not valid java name */
    private final void m2865drawTrackPathCx2C_VA(DrawScope $this$drawTrackPath_u2dCx2C_VA, long offset, long size, long color, float startCornerRadius, float endCornerRadius) {
        long startCorner = CornerRadiusKt.CornerRadius(startCornerRadius, startCornerRadius);
        long endCorner = CornerRadiusKt.CornerRadius(endCornerRadius, endCornerRadius);
        RoundRect track = RoundRectKt.m4345RoundRectZAM2FJo(RectKt.m4333Recttz77jQw(OffsetKt.Offset(Offset.m4293getXimpl(offset), 0.0f), androidx.compose.ui.geometry.SizeKt.Size(Size.m4362getWidthimpl(size), Size.m4359getHeightimpl(size))), startCorner, endCorner, endCorner, startCorner);
        Path.addRoundRect$default(trackPath, track, null, 2, null);
        DrawScope.m5080drawPathLG529CI$default($this$drawTrackPath_u2dCx2C_VA, trackPath, color, 0.0f, null, null, 0, 60, null);
        trackPath.rewind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawStopIndicator-x3O1jOs, reason: not valid java name */
    public final void m2863drawStopIndicatorx3O1jOs(DrawScope drawScope, long offset, float size, long color) {
        DrawScope.m5071drawCircleVaOC9Bg$default(drawScope, color, drawScope.mo726toPx0680j_4(size) / 2.0f, offset, 0.0f, null, null, 0, 120, null);
    }

    /* JADX INFO: renamed from: getTrackStopIndicatorSize-D9Ej5fM, reason: not valid java name */
    public final float m2871getTrackStopIndicatorSizeD9Ej5fM() {
        return TrackStopIndicatorSize;
    }

    /* JADX INFO: renamed from: getTickSize-D9Ej5fM, reason: not valid java name */
    public final float m2870getTickSizeD9Ej5fM() {
        return TickSize;
    }
}
