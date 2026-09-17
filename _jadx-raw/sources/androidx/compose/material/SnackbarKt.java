package androidx.compose.material;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Snackbar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a3\u0010\u000b\u001a\u00020\f2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000fH\u0003¢\u0006\u0002\u0010\u0011\u001a3\u0010\u0012\u001a\u00020\f2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000fH\u0003¢\u0006\u0002\u0010\u0011\u001a`\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001ax\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e¢\u0006\u0002\b\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u00012\u0011\u0010#\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a \u0010&\u001a\u00020\f2\u0011\u0010#\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000fH\u0003¢\u0006\u0002\u0010'\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006("}, d2 = {"HeightToFirstLine", "Landroidx/compose/ui/unit/Dp;", "F", "HorizontalSpacing", "HorizontalSpacingButtonSide", "LongButtonVerticalOffset", "SeparateButtonExtraY", "SnackbarMinHeightOneLine", "SnackbarMinHeightTwoLines", "SnackbarVerticalPadding", "TextEndExtraSpacing", "NewLineButtonSnackbar", "", "text", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "action", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "OneRowSnackbar", "Snackbar", "snackbarData", "Landroidx/compose/material/SnackbarData;", "modifier", "Landroidx/compose/ui/Modifier;", "actionOnNewLine", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "actionColor", "elevation", "Snackbar-sPrSdHI", "(Landroidx/compose/material/SnackbarData;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJJFLandroidx/compose/runtime/Composer;II)V", "content", "Snackbar-7zSek6w", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/graphics/Shape;JJFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TextOnlySnackbar", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnackbarKt {
    private static final float HeightToFirstLine = Dp.m7016constructorimpl(30);
    private static final float HorizontalSpacing = Dp.m7016constructorimpl(16);
    private static final float HorizontalSpacingButtonSide = Dp.m7016constructorimpl(8);
    private static final float SeparateButtonExtraY = Dp.m7016constructorimpl(2);
    private static final float SnackbarVerticalPadding = Dp.m7016constructorimpl(6);
    private static final float TextEndExtraSpacing = Dp.m7016constructorimpl(8);
    private static final float LongButtonVerticalOffset = Dp.m7016constructorimpl(12);
    private static final float SnackbarMinHeightOneLine = Dp.m7016constructorimpl(48);
    private static final float SnackbarMinHeightTwoLines = Dp.m7016constructorimpl(68);

    /* JADX INFO: renamed from: Snackbar-7zSek6w, reason: not valid java name */
    public static final void m2011Snackbar7zSek6w(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean actionOnNewLine, Shape shape, long backgroundColor, long contentColor, float elevation, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        final Function2<? super Composer, ? super Integer, Unit> function4;
        final boolean actionOnNewLine2;
        Shape shape2;
        long backgroundColor2;
        int $dirty;
        float f;
        Modifier.Companion modifier2;
        long contentColor2;
        int $dirty2;
        float elevation2;
        long contentColor3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        float elevation3;
        Modifier modifier3;
        long contentColor4;
        boolean actionOnNewLine3;
        Shape shape3;
        long backgroundColor3;
        int $dirty3;
        Composer $composer2 = $composer.startRestartGroup(-558258760);
        ComposerKt.sourceInformation($composer2, "C(Snackbar)P(6!2,7,2:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.unit.Dp)87@4085L6,88@4145L15,89@4202L6,99@4455L464,93@4288L631:Snackbar.kt#jmzs0o");
        int $dirty4 = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer2.changed(modifier) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty4 |= 48;
            function4 = function2;
        } else if (($changed & 48) == 0) {
            function4 = function2;
            $dirty4 |= $composer2.changedInstance(function4) ? 32 : 16;
        } else {
            function4 = function2;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty4 |= 384;
            actionOnNewLine2 = actionOnNewLine;
        } else if (($changed & 384) == 0) {
            actionOnNewLine2 = actionOnNewLine;
            $dirty4 |= $composer2.changed(actionOnNewLine2) ? 256 : 128;
        } else {
            actionOnNewLine2 = actionOnNewLine;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i5 = $composer2.changed(shape2) ? 2048 : 1024;
                $dirty4 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                backgroundColor2 = backgroundColor;
                int i6 = $composer2.changed(backgroundColor2) ? 16384 : 8192;
                $dirty4 |= i6;
            } else {
                backgroundColor2 = backgroundColor;
            }
            $dirty4 |= i6;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                $dirty3 = $dirty4;
                int i7 = $composer2.changed(contentColor) ? 131072 : 65536;
                $dirty = $dirty3 | i7;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i7;
        } else {
            $dirty = $dirty4;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            f = elevation;
        } else if (($changed & 1572864) == 0) {
            f = elevation;
            $dirty |= $composer2.changed(f) ? 1048576 : 524288;
        } else {
            f = elevation;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        int $dirty5 = $dirty;
        if ((4793491 & $dirty5) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            contentColor4 = contentColor;
            function5 = function4;
            actionOnNewLine3 = actionOnNewLine2;
            shape3 = shape2;
            backgroundColor3 = backgroundColor2;
            elevation3 = f;
            modifier3 = modifier;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    function4 = null;
                }
                if (i4 != 0) {
                    actionOnNewLine2 = false;
                }
                if ((i & 8) != 0) {
                    $dirty5 &= -7169;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getSmall();
                }
                if ((i & 16) != 0) {
                    backgroundColor2 = SnackbarDefaults.INSTANCE.getBackgroundColor($composer2, 6);
                    $dirty5 &= -57345;
                }
                if ((i & 32) != 0) {
                    contentColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1850getSurface0d7_KjU();
                    $dirty5 &= -458753;
                } else {
                    contentColor2 = contentColor;
                }
                if (i8 != 0) {
                    $dirty2 = $dirty5;
                    elevation2 = Dp.m7016constructorimpl(6);
                    contentColor3 = contentColor2;
                } else {
                    $dirty2 = $dirty5;
                    elevation2 = f;
                    contentColor3 = contentColor2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty5 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty5 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                modifier2 = modifier;
                contentColor3 = contentColor;
                $dirty2 = $dirty5;
                elevation2 = f;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-558258760, $dirty2, -1, "androidx.compose.material.Snackbar (Snackbar.kt:92)");
            }
            Function2<? super Composer, ? super Integer, Unit> function6 = function4;
            SurfaceKt.m2028SurfaceFjzlyU(modifier2, shape2, backgroundColor2, contentColor3, null, elevation2, ComposableLambdaKt.rememberComposableLambda(-2084221700, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$1
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C100@4530L4,100@4536L377,100@4465L448:Snackbar.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2084221700, $changed2, -1, "androidx.compose.material.Snackbar.<anonymous> (Snackbar.kt:100)");
                        }
                        ProvidedValue<Float> providedValueProvides = ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh($composer3, 6)));
                        final Function2<Composer, Integer, Unit> function7 = function4;
                        final Function2<Composer, Integer, Unit> function8 = function3;
                        final boolean z = actionOnNewLine2;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(1939362236, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$1.1
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

                            public final void invoke(Composer $composer4, int $changed3) {
                                ComposerKt.sourceInformation($composer4, "C101@4580L10,102@4645L258,102@4609L294:Snackbar.kt#jmzs0o");
                                if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1939362236, $changed3, -1, "androidx.compose.material.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:101)");
                                    }
                                    TextStyle textStyle = MaterialTheme.INSTANCE.getTypography($composer4, 6).getBody2();
                                    final Function2<Composer, Integer, Unit> function9 = function7;
                                    final Function2<Composer, Integer, Unit> function10 = function8;
                                    final boolean z2 = z;
                                    TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.rememberComposableLambda(225114541, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt.Snackbar.1.1.1
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

                                        public final void invoke(Composer $composer5, int $changed4) {
                                            ComposerKt.sourceInformation($composer5, "C:Snackbar.kt#jmzs0o");
                                            if (($changed4 & 3) != 2 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(225114541, $changed4, -1, "androidx.compose.material.Snackbar.<anonymous>.<anonymous>.<anonymous> (Snackbar.kt:103)");
                                                }
                                                if (function9 == null) {
                                                    $composer5.startReplaceGroup(1850967489);
                                                    ComposerKt.sourceInformation($composer5, "104@4708L25");
                                                    SnackbarKt.TextOnlySnackbar(function10, $composer5, 0);
                                                    $composer5.endReplaceGroup();
                                                } else if (z2) {
                                                    $composer5.startReplaceGroup(1850969582);
                                                    ComposerKt.sourceInformation($composer5, "105@4773L38");
                                                    SnackbarKt.NewLineButtonSnackbar(function10, function9, $composer5, 0);
                                                    $composer5.endReplaceGroup();
                                                } else {
                                                    $composer5.startReplaceGroup(1850971719);
                                                    ComposerKt.sourceInformation($composer5, "106@4840L31");
                                                    SnackbarKt.OneRowSnackbar(function10, function9, $composer5, 0);
                                                    $composer5.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer5.skipToGroupEnd();
                                        }
                                    }, $composer4, 54), $composer4, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        }, $composer3, 54), $composer3, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ($dirty2 & 14) | 1572864 | (($dirty2 >> 6) & 112) | (($dirty2 >> 6) & 896) | (($dirty2 >> 6) & 7168) | (($dirty2 >> 3) & 458752), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function5 = function6;
            elevation3 = elevation2;
            modifier3 = modifier2;
            contentColor4 = contentColor3;
            actionOnNewLine3 = actionOnNewLine2;
            shape3 = shape2;
            backgroundColor3 = backgroundColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final Function2<? super Composer, ? super Integer, Unit> function7 = function5;
            final boolean z = actionOnNewLine3;
            final Shape shape4 = shape3;
            final long j = backgroundColor3;
            final long j2 = contentColor4;
            final float f2 = elevation3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$2
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

                public final void invoke(Composer composer, int i9) {
                    SnackbarKt.m2011Snackbar7zSek6w(modifier4, function7, z, shape4, j, j2, f2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: Snackbar-sPrSdHI, reason: not valid java name */
    public static final void m2012SnackbarsPrSdHI(final SnackbarData snackbarData, Modifier modifier, boolean actionOnNewLine, Shape shape, long backgroundColor, long contentColor, long actionColor, float elevation, Composer $composer, final int $changed, final int i) {
        boolean z;
        Shape shape2;
        long j;
        long j2;
        int $dirty;
        Modifier.Companion modifier2;
        boolean actionOnNewLine2;
        CornerBasedShape shape3;
        long backgroundColor2;
        long contentColor2;
        long actionColor2;
        float elevation2;
        final long actionColor3;
        Function2 actionComposable;
        long actionColor4;
        Modifier modifier3;
        boolean actionOnNewLine3;
        float elevation3;
        Shape shape4;
        long backgroundColor3;
        long contentColor3;
        int $dirty2;
        Composer $composer2 = $composer.startRestartGroup(258660814);
        ComposerKt.sourceInformation($composer2, "C(Snackbar)P(7,5,1,6,2:c#ui.graphics.Color,3:c#ui.graphics.Color,0:c#ui.graphics.Color,4:c#ui.unit.Dp)158@7262L6,159@7322L15,160@7379L6,161@7437L18,178@7986L30,176@7914L320:Snackbar.kt#jmzs0o");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= ($changed & 8) == 0 ? $composer2.changed(snackbarData) : $composer2.changedInstance(snackbarData) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            z = actionOnNewLine;
        } else if (($changed & 384) == 0) {
            z = actionOnNewLine;
            $dirty3 |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = actionOnNewLine;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i4 = $composer2.changed(shape2) ? 2048 : 1024;
                $dirty3 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                j = backgroundColor;
                int i5 = $composer2.changed(j) ? 16384 : 8192;
                $dirty3 |= i5;
            } else {
                j = backgroundColor;
            }
            $dirty3 |= i5;
        } else {
            j = backgroundColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                j2 = contentColor;
                int i6 = $composer2.changed(j2) ? 131072 : 65536;
                $dirty3 |= i6;
            } else {
                j2 = contentColor;
            }
            $dirty3 |= i6;
        } else {
            j2 = contentColor;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                $dirty2 = $dirty3;
                int i7 = $composer2.changed(actionColor) ? 1048576 : 524288;
                $dirty = $dirty2 | i7;
            } else {
                $dirty2 = $dirty3;
            }
            $dirty = $dirty2 | i7;
        } else {
            $dirty = $dirty3;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(elevation) ? 8388608 : 4194304;
        }
        int $dirty4 = $dirty;
        if (($dirty4 & 4793491) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            actionColor4 = actionColor;
            elevation3 = elevation;
            actionOnNewLine3 = z;
            shape4 = shape2;
            backgroundColor3 = j;
            contentColor3 = j2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                actionOnNewLine2 = i3 != 0 ? false : z;
                if ((i & 8) != 0) {
                    shape3 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getSmall();
                    $dirty4 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    backgroundColor2 = SnackbarDefaults.INSTANCE.getBackgroundColor($composer2, 6);
                    $dirty4 &= -57345;
                } else {
                    backgroundColor2 = j;
                }
                if ((i & 32) != 0) {
                    contentColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1850getSurface0d7_KjU();
                    $dirty4 &= -458753;
                } else {
                    contentColor2 = j2;
                }
                if ((i & 64) != 0) {
                    actionColor2 = SnackbarDefaults.INSTANCE.getPrimaryActionColor($composer2, 6);
                    $dirty4 &= -3670017;
                } else {
                    actionColor2 = actionColor;
                }
                if (i8 != 0) {
                    elevation2 = Dp.m7016constructorimpl(6);
                    actionColor3 = actionColor2;
                } else {
                    elevation2 = elevation;
                    actionColor3 = actionColor2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty4 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty4 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty4 &= -458753;
                }
                if ((i & 64) != 0) {
                    elevation2 = elevation;
                    $dirty4 &= -3670017;
                    actionOnNewLine2 = z;
                    shape3 = shape2;
                    backgroundColor2 = j;
                    contentColor2 = j2;
                    modifier2 = modifier;
                    actionColor3 = actionColor;
                } else {
                    modifier2 = modifier;
                    elevation2 = elevation;
                    actionOnNewLine2 = z;
                    shape3 = shape2;
                    backgroundColor2 = j;
                    contentColor2 = j2;
                    actionColor3 = actionColor;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(258660814, $dirty4, -1, "androidx.compose.material.Snackbar (Snackbar.kt:163)");
            }
            final String actionLabel = snackbarData.getActionLabel();
            if (actionLabel != null) {
                $composer2.startReplaceGroup(1609178760);
                ComposerKt.sourceInformation($composer2, "166@7634L243");
                Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1843479216, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$actionComposable$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed2) {
                        Object value$iv;
                        ComposerKt.sourceInformation($composer3, "C168@7700L44,169@7772L32,170@7832L21,167@7648L219:Snackbar.kt#jmzs0o");
                        if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1843479216, $changed2, -1, "androidx.compose.material.Snackbar.<anonymous> (Snackbar.kt:167)");
                            }
                            ButtonColors buttonColorsM1821textButtonColorsRGew2ao = ButtonDefaults.INSTANCE.m1821textButtonColorsRGew2ao(0L, actionColor3, 0L, $composer3, 3072, 5);
                            ComposerKt.sourceInformationMarkerStart($composer3, 750806346, "CC(remember):Snackbar.kt#9igjgp");
                            boolean invalid$iv = $composer3.changedInstance(snackbarData);
                            final SnackbarData snackbarData2 = snackbarData;
                            Object it$iv = $composer3.rememberedValue();
                            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                value$iv = new Function0<Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$actionComposable$1$1$1
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        snackbarData2.performAction();
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv);
                            } else {
                                value$iv = it$iv;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            final String str = actionLabel;
                            ButtonKt.TextButton((Function0) value$iv, null, false, null, null, null, null, buttonColorsM1821textButtonColorsRGew2ao, null, ComposableLambdaKt.rememberComposableLambda(-929149933, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$actionComposable$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                                    invoke(rowScope, composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(RowScope $this$TextButton, Composer $composer4, int $changed3) {
                                    ComposerKt.sourceInformation($composer4, "C170@7834L17:Snackbar.kt#jmzs0o");
                                    if (($changed3 & 17) == 16 && $composer4.getSkipping()) {
                                        $composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-929149933, $changed3, -1, "androidx.compose.material.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:170)");
                                    }
                                    TextKt.m2089Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131070);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, $composer3, 54), $composer3, 805306368, 382);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }, $composer2, 54);
                $composer2.endReplaceGroup();
                actionComposable = function2RememberComposableLambda;
            } else {
                $composer2.startReplaceGroup(1609445763);
                $composer2.endReplaceGroup();
                actionComposable = null;
            }
            m2011Snackbar7zSek6w(PaddingKt.m1034padding3ABfNKs(modifier2, Dp.m7016constructorimpl(12)), actionComposable, actionOnNewLine2, shape3, backgroundColor2, contentColor2, elevation2, ComposableLambdaKt.rememberComposableLambda(-261845785, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$3
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C178@7988L26:Snackbar.kt#jmzs0o");
                    if (($changed2 & 3) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-261845785, $changed2, -1, "androidx.compose.material.Snackbar.<anonymous> (Snackbar.kt:178)");
                    }
                    TextKt.m2089Text4IGK_g(snackbarData.getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ($dirty4 & 896) | 12582912 | ($dirty4 & 7168) | (57344 & $dirty4) | (458752 & $dirty4) | (($dirty4 >> 3) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            actionColor4 = actionColor3;
            modifier3 = modifier2;
            actionOnNewLine3 = actionOnNewLine2;
            elevation3 = elevation2;
            shape4 = shape3;
            backgroundColor3 = backgroundColor2;
            contentColor3 = contentColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final boolean z2 = actionOnNewLine3;
            final Shape shape5 = shape4;
            final long j3 = backgroundColor3;
            final long j4 = contentColor3;
            final long j5 = actionColor4;
            final float f = elevation3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i9) {
                    SnackbarKt.m2012SnackbarsPrSdHI(snackbarData, modifier4, z2, shape5, j3, j4, j5, f, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TextOnlySnackbar(final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(917397959);
        ComposerKt.sourceInformation($composer2, "C(TextOnlySnackbar)239@10057L1816:Snackbar.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($dirty & 3) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(917397959, $dirty, -1, "androidx.compose.material.TextOnlySnackbar (Snackbar.kt:238)");
            }
            MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.material.SnackbarKt.TextOnlySnackbar.2
                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo391measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
                    final ArrayList textPlaceables = new ArrayList(list.size());
                    int firstBaseline = Integer.MIN_VALUE;
                    int lastBaseline = Integer.MIN_VALUE;
                    int height = 0;
                    int size = list.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = list.get(index$iv);
                        Measurable it = (Measurable) item$iv;
                        Placeable placeable = it.mo5870measureBRTryo0(constraints);
                        textPlaceables.add(placeable);
                        if (placeable.get(AlignmentLineKt.getFirstBaseline()) != Integer.MIN_VALUE && (firstBaseline == Integer.MIN_VALUE || placeable.get(AlignmentLineKt.getFirstBaseline()) < firstBaseline)) {
                            firstBaseline = placeable.get(AlignmentLineKt.getFirstBaseline());
                        }
                        if (placeable.get(AlignmentLineKt.getLastBaseline()) != Integer.MIN_VALUE && (lastBaseline == Integer.MIN_VALUE || placeable.get(AlignmentLineKt.getLastBaseline()) > lastBaseline)) {
                            lastBaseline = placeable.get(AlignmentLineKt.getLastBaseline());
                        }
                        height = Math.max(height, placeable.getHeight());
                    }
                    boolean hasText = (firstBaseline == Integer.MIN_VALUE || lastBaseline == Integer.MIN_VALUE) ? false : true;
                    float minHeight = (firstBaseline == lastBaseline || !hasText) ? SnackbarKt.SnackbarMinHeightOneLine : SnackbarKt.SnackbarMinHeightTwoLines;
                    final int containerHeight = Math.max($this$Layout.mo720roundToPx0680j_4(minHeight), height);
                    return MeasureScope.layout$default($this$Layout, Constraints.m6969getMaxWidthimpl(constraints), containerHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.SnackbarKt.TextOnlySnackbar.2.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope $this$layout) {
                            List $this$fastForEach$iv = textPlaceables;
                            int i = containerHeight;
                            int size2 = $this$fastForEach$iv.size();
                            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                                Object item$iv2 = $this$fastForEach$iv.get(index$iv2);
                                Placeable it2 = (Placeable) item$iv2;
                                int textPlaceY = (i - it2.getHeight()) / 2;
                                Placeable.PlacementScope.placeRelative$default($this$layout, it2, 0, textPlaceY, 0.0f, 4, null);
                            }
                        }
                    }, 4, null);
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            Modifier modifier$iv = Modifier.INSTANCE;
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = ((384 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1169284118, "C240@10074L202:Snackbar.kt#jmzs0o");
            Modifier modifier$iv2 = PaddingKt.m1035paddingVpY3zN4(Modifier.INSTANCE, HorizontalSpacing, SnackbarVerticalPadding);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor2;
                $composer2.createNode(function0);
            } else {
                function0 = constructor2;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 2052531256, "C246@10257L9:Snackbar.kt#jmzs0o");
            function2.invoke($composer2, Integer.valueOf($dirty & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt.TextOnlySnackbar.3
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

                public final void invoke(Composer composer, int i4) {
                    SnackbarKt.TextOnlySnackbar(function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NewLineButtonSnackbar(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer2;
        Function0<ComposeUiNode> function4;
        Composer $composer3 = $composer.startRestartGroup(-1229075900);
        ComposerKt.sourceInformation($composer3, "C(NewLineButtonSnackbar)P(1)294@12001L476:Snackbar.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1229075900, $dirty, -1, "androidx.compose.material.NewLineButtonSnackbar (Snackbar.kt:293)");
            }
            Modifier modifier$iv = PaddingKt.m1038paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), HorizontalSpacing, 0.0f, HorizontalSpacingButtonSide, SeparateButtonExtraY, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer3);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            int i2 = ((6 >> 6) & 112) | 6;
            ColumnScope $this$NewLineButtonSnackbar_u24lambda_u244 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -322444484, "C302@12244L171,306@12424L47:Snackbar.kt#jmzs0o");
            Modifier modifier$iv2 = PaddingKt.m1038paddingqDBjuR0$default(androidx.compose.foundation.layout.AlignmentLineKt.m888paddingFromBaselineVpY3zN4(Modifier.INSTANCE, HeightToFirstLine, LongButtonVerticalOffset), 0.0f, 0.0f, HorizontalSpacingButtonSide, 0.0f, 11, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor2;
                $composer3.createNode(function1);
            } else {
                function1 = constructor2;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
            $composer2 = $composer3;
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i4 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -559692806, "C305@12407L6:Snackbar.kt#jmzs0o");
            function2.invoke($composer3, Integer.valueOf($dirty & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv3 = $this$NewLineButtonSnackbar_u24lambda_u244.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd());
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv3 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function4 = constructor3;
                $composer3.createNode(function4);
            } else {
                function4 = constructor3;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer3);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -559639176, "C306@12461L8:Snackbar.kt#jmzs0o");
            function3.invoke($composer3, Integer.valueOf(($dirty >> 3) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt.NewLineButtonSnackbar.2
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

                public final void invoke(Composer composer, int i7) {
                    SnackbarKt.NewLineButtonSnackbar(function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void OneRowSnackbar(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Object value$iv;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function4;
        Composer $composer2 = $composer.startRestartGroup(-534813202);
        ComposerKt.sourceInformation($composer2, "C(OneRowSnackbar)P(1)326@12977L2198,317@12652L2523:Snackbar.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-534813202, $dirty, -1, "androidx.compose.material.OneRowSnackbar (Snackbar.kt:314)");
            }
            final String textTag = "text";
            final String actionTag = "action";
            Modifier modifier$iv = PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, HorizontalSpacing, 0.0f, HorizontalSpacingButtonSide, 0.0f, 10, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -1075355883, "CC(remember):Snackbar.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MeasurePolicy() { // from class: androidx.compose.material.SnackbarKt$OneRowSnackbar$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo391measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
                        int containerHeight;
                        int contentHeight;
                        int textPlaceY;
                        SnackbarKt$OneRowSnackbar$2$1 snackbarKt$OneRowSnackbar$2$1 = this;
                        String str = actionTag;
                        int index$iv$iv = 0;
                        int size = list.size();
                        while (index$iv$iv < size) {
                            Object item$iv$iv = list.get(index$iv$iv);
                            Measurable it = (Measurable) item$iv$iv;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), str)) {
                                final Placeable buttonPlaceable = ((Measurable) item$iv$iv).mo5870measureBRTryo0(constraints);
                                int textMaxWidth = RangesKt.coerceAtLeast((Constraints.m6969getMaxWidthimpl(constraints) - buttonPlaceable.getWidth()) - $this$Layout.mo720roundToPx0680j_4(SnackbarKt.TextEndExtraSpacing), Constraints.m6971getMinWidthimpl(constraints));
                                String str2 = textTag;
                                int size2 = list.size();
                                for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                                    Object item$iv$iv2 = list.get(index$iv$iv2);
                                    Measurable it2 = (Measurable) item$iv$iv2;
                                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), str2)) {
                                        final Placeable textPlaceable = ((Measurable) item$iv$iv2).mo5870measureBRTryo0(Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : textMaxWidth, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0));
                                        int firstTextBaseline = textPlaceable.get(AlignmentLineKt.getFirstBaseline());
                                        int lastTextBaseline = textPlaceable.get(AlignmentLineKt.getLastBaseline());
                                        boolean z = true;
                                        boolean hasText = (firstTextBaseline == Integer.MIN_VALUE || lastTextBaseline == Integer.MIN_VALUE) ? false : true;
                                        if (firstTextBaseline != lastTextBaseline && hasText) {
                                            z = false;
                                        }
                                        boolean isOneLine = z;
                                        final int buttonPlaceX = Constraints.m6969getMaxWidthimpl(constraints) - buttonPlaceable.getWidth();
                                        if (isOneLine) {
                                            int minContainerHeight = $this$Layout.mo720roundToPx0680j_4(SnackbarKt.SnackbarMinHeightOneLine);
                                            int contentHeight2 = buttonPlaceable.getHeight();
                                            int containerHeight2 = Math.max(minContainerHeight, contentHeight2);
                                            int textPlaceY2 = (containerHeight2 - textPlaceable.getHeight()) / 2;
                                            int buttonBaseline = buttonPlaceable.get(AlignmentLineKt.getFirstBaseline());
                                            int it3 = buttonBaseline != Integer.MIN_VALUE ? (textPlaceY2 + firstTextBaseline) - buttonBaseline : 0;
                                            contentHeight = it3;
                                            containerHeight = containerHeight2;
                                            textPlaceY = textPlaceY2;
                                        } else {
                                            int baselineOffset = $this$Layout.mo720roundToPx0680j_4(SnackbarKt.HeightToFirstLine);
                                            int textPlaceY3 = baselineOffset - firstTextBaseline;
                                            int minContainerHeight2 = $this$Layout.mo720roundToPx0680j_4(SnackbarKt.SnackbarMinHeightTwoLines);
                                            int contentHeight3 = textPlaceY3 + textPlaceable.getHeight();
                                            int containerHeight3 = Math.max(minContainerHeight2, contentHeight3);
                                            int buttonPlaceY = (containerHeight3 - buttonPlaceable.getHeight()) / 2;
                                            containerHeight = containerHeight3;
                                            contentHeight = buttonPlaceY;
                                            textPlaceY = textPlaceY3;
                                        }
                                        int minContainerHeight3 = Constraints.m6969getMaxWidthimpl(constraints);
                                        final int i = textPlaceY;
                                        final int i2 = contentHeight;
                                        return MeasureScope.layout$default($this$Layout, minContainerHeight3, containerHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.SnackbarKt$OneRowSnackbar$2$1.2
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope $this$layout) {
                                                Placeable.PlacementScope.placeRelative$default($this$layout, textPlaceable, 0, i, 0.0f, 4, null);
                                                Placeable.PlacementScope.placeRelative$default($this$layout, buttonPlaceable, buttonPlaceX, i2, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                            index$iv$iv++;
                            snackbarKt$OneRowSnackbar$2$1 = this;
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MeasurePolicy measurePolicy$iv = (MeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = ((432 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -16000972, "C319@12682L86,320@12781L46:Snackbar.kt#jmzs0o");
            Modifier modifier$iv2 = PaddingKt.m1036paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "text"), 0.0f, SnackbarVerticalPadding, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function1 = constructor2;
                $composer2.createNode(function1);
            } else {
                function1 = constructor2;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1078382464, "C319@12760L6:Snackbar.kt#jmzs0o");
            function2.invoke($composer2, Integer.valueOf($dirty & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv3 = LayoutIdKt.layoutId(Modifier.INSTANCE, "action");
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv3 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function4 = constructor3;
                $composer2.createNode(function4);
            } else {
                function4 = constructor3;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i5 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1078325858, "C320@12817L8:Snackbar.kt#jmzs0o");
            function3.invoke($composer2, Integer.valueOf(($dirty >> 3) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt.OneRowSnackbar.3
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

                public final void invoke(Composer composer, int i6) {
                    SnackbarKt.OneRowSnackbar(function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
