package androidx.compose.material3;

import android.content.res.Configuration;
import androidx.compose.foundation.layout.AlignmentLineKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.PlainTooltipTokens;
import androidx.compose.material3.tokens.RichTooltipTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Tooltip.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ao\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0093\u0001\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001aH\u0010\u001c\u001a\u00020\u001d*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010&H\u0003ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006)"}, d2 = {"PlainTooltip", "", "Landroidx/compose/material3/TooltipScope;", "modifier", "Landroidx/compose/ui/Modifier;", "caretSize", "Landroidx/compose/ui/unit/DpSize;", "shape", "Landroidx/compose/ui/graphics/Shape;", "contentColor", "Landroidx/compose/ui/graphics/Color;", "containerColor", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "PlainTooltip-7QI4Sbk", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "RichTooltip", "title", "action", "colors", "Landroidx/compose/material3/RichTooltipColors;", "text", "RichTooltip-yDvdmqw", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/RichTooltipColors;FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "drawCaretWithPath", "Landroidx/compose/ui/draw/DrawResult;", "Landroidx/compose/ui/draw/CacheDrawScope;", "caretType", "Landroidx/compose/material3/CaretType;", "density", "Landroidx/compose/ui/unit/Density;", "configuration", "Landroid/content/res/Configuration;", "anchorLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "drawCaretWithPath-JKu-mZY", "(Landroidx/compose/ui/draw/CacheDrawScope;Landroidx/compose/material3/CaretType;Landroidx/compose/ui/unit/Density;Landroid/content/res/Configuration;JJLandroidx/compose/ui/layout/LayoutCoordinates;)Landroidx/compose/ui/draw/DrawResult;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Tooltip_androidKt {
    /* JADX INFO: renamed from: PlainTooltip-7QI4Sbk, reason: not valid java name */
    public static final void m3218PlainTooltip7QI4Sbk(final TooltipScope $this$PlainTooltip_u2d7QI4Sbk, Modifier modifier, long caretSize, Shape shape, long contentColor, long containerColor, float tonalElevation, float shadowElevation, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        long contentColor2;
        long containerColor2;
        float tonalElevation2;
        float shadowElevation2;
        long caretSize2;
        Shape shape3;
        long containerColor3;
        Modifier modifier2;
        float f;
        int $dirty;
        long caretSize3;
        int $dirty2;
        long contentColor3;
        Modifier drawCaretModifier;
        float shadowElevation3;
        float tonalElevation3;
        Modifier modifier3;
        long caretSize4;
        long containerColor4;
        long contentColor4;
        boolean invalid$iv;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(1407069716);
        ComposerKt.sourceInformation($composer2, "C(PlainTooltip)P(4,0:c#ui.unit.DpSize,6,3:c#ui.graphics.Color,1:c#ui.graphics.Color,7:c#ui.unit.Dp,5:c#ui.unit.Dp)208@8412L26,209@8482L24,211@8552L26,96@3876L606,90@3685L797:Tooltip.android.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= ($changed & 8) == 0 ? $composer2.changed($this$PlainTooltip_u2d7QI4Sbk) : $composer2.changedInstance($this$PlainTooltip_u2d7QI4Sbk) ? 4 : 2;
        }
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty3 |= ((i & 2) == 0 && $composer2.changed(caretSize)) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i3 = $composer2.changed(shape2) ? 2048 : 1024;
                $dirty3 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                int i4 = $composer2.changed(contentColor2) ? 16384 : 8192;
                $dirty3 |= i4;
            } else {
                contentColor2 = contentColor;
            }
            $dirty3 |= i4;
        } else {
            contentColor2 = contentColor;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 16) == 0) {
                containerColor2 = containerColor;
                int i5 = $composer2.changed(containerColor2) ? 131072 : 65536;
                $dirty3 |= i5;
            } else {
                containerColor2 = containerColor;
            }
            $dirty3 |= i5;
        } else {
            containerColor2 = containerColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= 1572864;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 1572864) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty3 |= $composer2.changed(tonalElevation2) ? 1048576 : 524288;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 12582912;
            shadowElevation2 = shadowElevation;
        } else if (($changed & 12582912) == 0) {
            shadowElevation2 = shadowElevation;
            $dirty3 |= $composer2.changed(shadowElevation2) ? 8388608 : 4194304;
        } else {
            shadowElevation2 = shadowElevation;
        }
        if ((i & 128) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer2.changedInstance(function2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & $dirty3) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            caretSize4 = caretSize;
            shape2 = shape2;
            containerColor4 = containerColor2;
            tonalElevation3 = tonalElevation2;
            contentColor4 = contentColor2;
            shadowElevation3 = shadowElevation2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 2) != 0) {
                    caretSize2 = DpSize.INSTANCE.m7123getUnspecifiedMYxV2XQ();
                    $dirty3 &= -897;
                } else {
                    caretSize2 = caretSize;
                }
                if ((i & 4) != 0) {
                    shape3 = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape($composer2, 6);
                    $dirty3 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    contentColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContentColor($composer2, 6);
                    $dirty3 &= -57345;
                }
                if ((i & 16) != 0) {
                    containerColor3 = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor($composer2, 6);
                    $dirty3 &= -458753;
                } else {
                    containerColor3 = containerColor2;
                }
                if (i6 != 0) {
                    tonalElevation2 = Dp.m7016constructorimpl(0);
                }
                if (i7 != 0) {
                    shadowElevation2 = Dp.m7016constructorimpl(0);
                    containerColor2 = containerColor3;
                    f = tonalElevation2;
                    modifier2 = modifier4;
                    $dirty = $dirty3;
                    shape2 = shape3;
                    caretSize3 = caretSize2;
                } else {
                    modifier2 = modifier4;
                    containerColor2 = containerColor3;
                    f = tonalElevation2;
                    $dirty = $dirty3;
                    shape2 = shape3;
                    caretSize3 = caretSize2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty3 &= -897;
                }
                if ((i & 4) != 0) {
                    $dirty3 &= -7169;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 16) != 0) {
                    int i8 = $dirty3 & (-458753);
                    caretSize3 = caretSize;
                    f = tonalElevation2;
                    $dirty = i8;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    f = tonalElevation2;
                    $dirty = $dirty3;
                    caretSize3 = caretSize;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1407069716, $dirty, -1, "androidx.compose.material3.PlainTooltip (Tooltip.android.kt:73)");
            }
            $composer2.startReplaceGroup(-333850415);
            ComposerKt.sourceInformation($composer2, "76@3183L7,77@3242L7,78@3281L343");
            long $this$isSpecified$iv = caretSize3;
            if ($this$isSpecified$iv != InlineClassHelperKt.UnspecifiedPackedFloats) {
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Density density = (Density) objConsume;
                ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localConfiguration);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Configuration configuration = (Configuration) objConsume2;
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -333845325, "CC(remember):Tooltip.android.kt#9igjgp");
                boolean zChanged = $composer2.changed(density) | $composer2.changedInstance(configuration);
                int $dirty4 = $dirty;
                if (((458752 & $dirty) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                    $dirty2 = $dirty4;
                    if ($composer2.changed(containerColor2)) {
                        contentColor3 = contentColor2;
                    }
                    invalid$iv = zChanged | z | (((($dirty2 & 896) ^ 384) <= 256 && $composer2.changed(caretSize3)) || ($dirty2 & 384) == 256);
                    Object it$iv = $composer2.rememberedValue();
                    if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        final long j = containerColor2;
                        final long j2 = caretSize3;
                        value$iv = new Function2<CacheDrawScope, LayoutCoordinates, DrawResult>() { // from class: androidx.compose.material3.Tooltip_androidKt$PlainTooltip$drawCaretModifier$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final DrawResult invoke(CacheDrawScope $this$drawCaret, LayoutCoordinates anchorLayoutCoordinates) {
                                return Tooltip_androidKt.m3221drawCaretWithPathJKumZY($this$drawCaret, CaretType.Plain, density, configuration, j, j2, anchorLayoutCoordinates);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    drawCaretModifier = $this$PlainTooltip_u2d7QI4Sbk.drawCaret(companion, (Function2) value$iv).then(modifier2);
                } else {
                    $dirty2 = $dirty4;
                }
                contentColor3 = contentColor2;
                boolean z = ($dirty2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072;
                invalid$iv = zChanged | z | (((($dirty2 & 896) ^ 384) <= 256 && $composer2.changed(caretSize3)) || ($dirty2 & 384) == 256);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv) {
                }
                final long j3 = containerColor2;
                final long j4 = caretSize3;
                value$iv = new Function2<CacheDrawScope, LayoutCoordinates, DrawResult>() { // from class: androidx.compose.material3.Tooltip_androidKt$PlainTooltip$drawCaretModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final DrawResult invoke(CacheDrawScope $this$drawCaret, LayoutCoordinates anchorLayoutCoordinates) {
                        return Tooltip_androidKt.m3221drawCaretWithPathJKumZY($this$drawCaret, CaretType.Plain, density, configuration, j3, j4, anchorLayoutCoordinates);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                drawCaretModifier = $this$PlainTooltip_u2d7QI4Sbk.drawCaret(companion, (Function2) value$iv).then(modifier2);
            } else {
                $dirty2 = $dirty;
                contentColor3 = contentColor2;
                drawCaretModifier = modifier2;
            }
            $composer2.endReplaceGroup();
            final long contentColor5 = contentColor3;
            SurfaceKt.m2917SurfaceT9BRK9s(drawCaretModifier, shape2, containerColor2, 0L, f, shadowElevation2, null, ComposableLambdaKt.rememberComposableLambda(1430116975, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.Tooltip_androidKt$PlainTooltip$1
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
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer3, "C97@3886L590:Tooltip.android.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1430116975, $changed2, -1, "androidx.compose.material3.PlainTooltip.<anonymous> (Tooltip.android.kt:97)");
                        }
                        Modifier modifier$iv = PaddingKt.padding(SizeKt.m1083sizeInqDBjuR0$default(Modifier.INSTANCE, TooltipKt.getTooltipMinWidth(), TooltipKt.getTooltipMinHeight(), TooltipKt.getPlainTooltipMaxWidth(), 0.0f, 8, null), TooltipKt.getPlainTooltipContentPadding());
                        long j5 = contentColor5;
                        Function2<Composer, Integer, Unit> function3 = function2;
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
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
                        int i9 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1903647947, "C106@4266L5,108@4285L181:Tooltip.android.kt#uh7d8r");
                        TextStyle textStyle = TypographyKt.getValue(PlainTooltipTokens.INSTANCE.getSupportingTextFont(), $composer3, 6);
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(j5)), TextKt.getLocalTextStyle().provides(textStyle)}, function3, $composer3, ProvidedValue.$stable);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, (($dirty2 >> 6) & 112) | 12582912 | (($dirty2 >> 9) & 896) | (($dirty2 >> 6) & 57344) | (($dirty2 >> 6) & 458752), 72);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shadowElevation3 = shadowElevation2;
            tonalElevation3 = f;
            modifier3 = modifier2;
            caretSize4 = caretSize3;
            containerColor4 = containerColor2;
            contentColor4 = contentColor5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final long j5 = caretSize4;
            final Shape shape4 = shape2;
            final long j6 = contentColor4;
            final long j7 = containerColor4;
            final float f2 = tonalElevation3;
            final float f3 = shadowElevation3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.Tooltip_androidKt$PlainTooltip$2
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
                    Tooltip_androidKt.m3218PlainTooltip7QI4Sbk($this$PlainTooltip_u2d7QI4Sbk, modifier5, j5, shape4, j6, j7, f2, f3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: RichTooltip-yDvdmqw, reason: not valid java name */
    public static final void m3219RichTooltipyDvdmqw(final TooltipScope $this$RichTooltip_u2dyDvdmqw, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, long caretSize, Shape shape, RichTooltipColors colors, float tonalElevation, float shadowElevation, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        long j;
        Shape shape2;
        Modifier.Companion modifier2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        long caretSize2;
        final RichTooltipColors colors2;
        float tonalElevation2;
        float shadowElevation2;
        Function2<? super Composer, ? super Integer, Unit> function8;
        final long elevatedColor;
        Modifier drawCaretModifier;
        Modifier modifier3;
        Shape shape3;
        float shadowElevation3;
        float tonalElevation3;
        Function2<? super Composer, ? super Integer, Unit> function9;
        long caretSize3;
        Function2<? super Composer, ? super Integer, Unit> function10;
        RichTooltipColors colors3;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(1867454921);
        ComposerKt.sourceInformation($composer2, "C(RichTooltip)P(3,7!1,1:c#ui.unit.DpSize,5!1,8:c#ui.unit.Dp,4:c#ui.unit.Dp)255@9959L25,257@10034L19,*148@5873L7,150@5944L11,150@5956L61,178@6991L1595,167@6622L1964:Tooltip.android.kt#uh7d8r");
        int $dirty = $changed;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed($this$RichTooltip_u2dyDvdmqw) : $composer2.changedInstance($this$RichTooltip_u2dyDvdmqw) ? 4 : 2;
        }
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 3072;
            function5 = function3;
        } else if (($changed & 3072) == 0) {
            function5 = function3;
            $dirty |= $composer2.changedInstance(function5) ? 2048 : 1024;
        } else {
            function5 = function3;
        }
        if (($changed & 24576) == 0) {
            if ((i & 8) == 0) {
                j = caretSize;
                int i5 = $composer2.changed(j) ? 16384 : 8192;
                $dirty |= i5;
            } else {
                j = caretSize;
            }
            $dirty |= i5;
        } else {
            j = caretSize;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i6 = $composer2.changed(shape2) ? 131072 : 65536;
                $dirty |= i6;
            } else {
                shape2 = shape;
            }
            $dirty |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= ((i & 32) == 0 && $composer2.changed(colors)) ? 1048576 : 524288;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(tonalElevation) ? 8388608 : 4194304;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(shadowElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 256) != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        if ((306783379 & $dirty) == 306783378 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            function9 = function2;
            colors3 = colors;
            tonalElevation3 = tonalElevation;
            shadowElevation3 = shadowElevation;
            function10 = function5;
            shape3 = shape2;
            caretSize3 = j;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                function6 = i3 != 0 ? null : function2;
                function7 = i4 != 0 ? null : function5;
                if ((i & 8) != 0) {
                    caretSize2 = DpSize.INSTANCE.m7123getUnspecifiedMYxV2XQ();
                    $dirty &= -57345;
                } else {
                    caretSize2 = j;
                }
                if ((i & 16) != 0) {
                    shape2 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer2, 6);
                    $dirty &= -458753;
                }
                if ((i & 32) != 0) {
                    colors2 = TooltipDefaults.INSTANCE.richTooltipColors($composer2, 6);
                    $dirty &= -3670017;
                } else {
                    colors2 = colors;
                }
                tonalElevation2 = i7 != 0 ? ElevationTokens.INSTANCE.m3547getLevel0D9Ej5fM() : tonalElevation;
                shadowElevation2 = i8 != 0 ? RichTooltipTokens.INSTANCE.m3845getContainerElevationD9Ej5fM() : shadowElevation;
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 16) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 32) != 0) {
                    modifier2 = modifier;
                    function6 = function2;
                    $dirty = (-3670017) & $dirty;
                    function7 = function5;
                    caretSize2 = j;
                    colors2 = colors;
                    tonalElevation2 = tonalElevation;
                    shadowElevation2 = shadowElevation;
                } else {
                    modifier2 = modifier;
                    function6 = function2;
                    shadowElevation2 = shadowElevation;
                    function7 = function5;
                    caretSize2 = j;
                    colors2 = colors;
                    tonalElevation2 = tonalElevation;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1867454921, $dirty, -1, "androidx.compose.material3.RichTooltip (Tooltip.android.kt:147)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteTonalElevation = SurfaceKt.getLocalAbsoluteTonalElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localAbsoluteTonalElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv = ((Dp) objConsume).m7030unboximpl();
            long elevatedColor2 = ColorSchemeKt.m2302applyTonalElevationRFCenO8(MaterialTheme.INSTANCE.getColorScheme($composer2, 6), colors2.getContainerColor(), Dp.m7016constructorimpl(arg0$iv + tonalElevation2), $composer2, 0);
            $composer2.startReplaceGroup(1472746423);
            ComposerKt.sourceInformation($composer2, "153@6122L7,154@6181L7,155@6220L341");
            long $this$isSpecified$iv = caretSize2;
            if ($this$isSpecified$iv != InlineClassHelperKt.UnspecifiedPackedFloats) {
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Density density = (Density) objConsume2;
                ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer2.consume(localConfiguration);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Configuration configuration = (Configuration) objConsume3;
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 1472751513, "CC(remember):Tooltip.android.kt#9igjgp");
                Function2<? super Composer, ? super Integer, Unit> function11 = function6;
                elevatedColor = elevatedColor2;
                function8 = function11;
                boolean invalid$iv = (((($dirty & 57344) ^ 24576) > 16384 && $composer2.changed(caretSize2)) || ($dirty & 24576) == 16384) | $composer2.changed(density) | $composer2.changedInstance(configuration) | $composer2.changed(elevatedColor);
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    final long j2 = caretSize2;
                    value$iv = new Function2<CacheDrawScope, LayoutCoordinates, DrawResult>() { // from class: androidx.compose.material3.Tooltip_androidKt$RichTooltip$drawCaretModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final DrawResult invoke(CacheDrawScope $this$drawCaret, LayoutCoordinates anchorLayoutCoordinates) {
                            return Tooltip_androidKt.m3221drawCaretWithPathJKumZY($this$drawCaret, CaretType.Rich, density, configuration, elevatedColor, j2, anchorLayoutCoordinates);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                drawCaretModifier = $this$RichTooltip_u2dyDvdmqw.drawCaret(companion, (Function2) value$iv).then(modifier2);
            } else {
                function8 = function6;
                elevatedColor = elevatedColor2;
                drawCaretModifier = modifier2;
            }
            $composer2.endReplaceGroup();
            final Function2<? super Composer, ? super Integer, Unit> function12 = function8;
            final Function2<? super Composer, ? super Integer, Unit> function13 = function7;
            modifier3 = modifier2;
            SurfaceKt.m2917SurfaceT9BRK9s(SizeKt.m1083sizeInqDBjuR0$default(drawCaretModifier, TooltipKt.getTooltipMinWidth(), TooltipKt.getTooltipMinHeight(), TooltipKt.getRichTooltipMaxWidth(), 0.0f, 8, null), shape2, colors2.getContainerColor(), 0L, tonalElevation2, shadowElevation2, null, ComposableLambdaKt.rememberComposableLambda(317290958, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.Tooltip_androidKt$RichTooltip$1
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
                    Function0<ComposeUiNode> function0;
                    Function0<ComposeUiNode> function1;
                    Function0<ComposeUiNode> function14;
                    Function0<ComposeUiNode> function15;
                    ComposerKt.sourceInformation($composer3, "C179@7066L5,180@7133L5,181@7210L5,183@7225L1355:Tooltip.android.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(317290958, $changed2, -1, "androidx.compose.material3.RichTooltip.<anonymous> (Tooltip.android.kt:179)");
                        }
                        TextStyle actionLabelTextStyle = TypographyKt.getValue(RichTooltipTokens.INSTANCE.getActionLabelTextFont(), $composer3, 6);
                        TextStyle subheadTextStyle = TypographyKt.getValue(RichTooltipTokens.INSTANCE.getSubheadFont(), $composer3, 6);
                        TextStyle supportingTextStyle = TypographyKt.getValue(RichTooltipTokens.INSTANCE.getSupportingTextFont(), $composer3, 6);
                        Modifier modifier$iv = PaddingKt.m1036paddingVpY3zN4$default(Modifier.INSTANCE, TooltipKt.getRichTooltipHorizontalPadding(), 0.0f, 2, null);
                        Function2<Composer, Integer, Unit> function16 = function12;
                        Function2<Composer, Integer, Unit> function17 = function13;
                        RichTooltipColors richTooltipColors = colors2;
                        Function2<Composer, Integer, Unit> function18 = function4;
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                        int $changed$iv$iv = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
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
                        int i9 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        int i10 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -459254051, "C193@7718L319:Tooltip.android.kt#uh7d8r");
                        $composer3.startReplaceGroup(955016030);
                        ComposerKt.sourceInformation($composer3, "*185@7347L344");
                        if (function16 != null) {
                            Modifier modifier$iv2 = AlignmentLineKt.m889paddingFromBaselineVpY3zN4$default(Modifier.INSTANCE, TooltipKt.getHeightToSubheadFirstLine(), 0.0f, 2, null);
                            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv2 = (6 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
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
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                            int i11 = ($changed$iv$iv$iv2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i12 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1468424960, "C186@7446L227:Tooltip.android.kt#uh7d8r");
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(richTooltipColors.getTitleContentColor())), TextKt.getLocalTextStyle().provides(subheadTextStyle)}, function16, $composer3, ProvidedValue.$stable);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Unit unit = Unit.INSTANCE;
                            Unit unit2 = Unit.INSTANCE;
                        }
                        $composer3.endReplaceGroup();
                        Modifier modifier$iv3 = TooltipKt.textVerticalPadding(Modifier.INSTANCE, function16 != null, function17 != null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                        int $changed$iv$iv3 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
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
                            function14 = constructor3;
                            $composer3.createNode(function14);
                        } else {
                            function14 = constructor3;
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
                        int i13 = ($changed$iv$iv$iv3 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        int i14 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1959181329, "C194@7812L211:Tooltip.android.kt#uh7d8r");
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(richTooltipColors.getContentColor())), TextKt.getLocalTextStyle().provides(supportingTextStyle)}, function18, $composer3, ProvidedValue.$stable);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.startReplaceGroup(955039618);
                        ComposerKt.sourceInformation($composer3, "*201@8080L476");
                        if (function17 != null) {
                            Modifier modifier$iv4 = PaddingKt.m1038paddingqDBjuR0$default(SizeKt.m1070requiredHeightInVpY3zN4$default(Modifier.INSTANCE, TooltipKt.getActionLabelMinHeight(), 0.0f, 2, null), 0.0f, 0.0f, 0.0f, TooltipKt.getActionLabelBottomPadding(), 7, null);
                            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                            int $changed$iv$iv4 = (6 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
                            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                function15 = constructor4;
                                $composer3.createNode(function15);
                            } else {
                                function15 = constructor4;
                                $composer3.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer3);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                            int i15 = ($changed$iv$iv$iv4 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                            int i16 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1469278235, "C206@8306L232:Tooltip.android.kt#uh7d8r");
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(richTooltipColors.getActionContentColor())), TextKt.getLocalTextStyle().provides(actionLabelTextStyle)}, function17, $composer3, ProvidedValue.$stable);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Unit unit3 = Unit.INSTANCE;
                            Unit unit4 = Unit.INSTANCE;
                        }
                        $composer3.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, (($dirty >> 12) & 112) | 12582912 | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752), 72);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape2;
            shadowElevation3 = shadowElevation2;
            tonalElevation3 = tonalElevation2;
            function9 = function12;
            caretSize3 = caretSize2;
            function10 = function13;
            colors3 = colors2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final Function2<? super Composer, ? super Integer, Unit> function14 = function9;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function10;
            final long j3 = caretSize3;
            final Shape shape4 = shape3;
            final RichTooltipColors richTooltipColors = colors3;
            final float f = tonalElevation3;
            final float f2 = shadowElevation3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.Tooltip_androidKt$RichTooltip$2
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
                    Tooltip_androidKt.m3219RichTooltipyDvdmqw($this$RichTooltip_u2dyDvdmqw, modifier4, function14, function15, j3, shape4, richTooltipColors, f, f2, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCaretWithPath-JKu-mZY, reason: not valid java name */
    public static final DrawResult m3221drawCaretWithPathJKumZY(CacheDrawScope $this$drawCaretWithPath_u2dJKu_u2dmZY, CaretType caretType, Density density, Configuration configuration, final long containerColor, long caretSize, final LayoutCoordinates anchorLayoutCoordinates) {
        float f;
        float anchorMidFromRightScreenEdge;
        long position;
        final Path path = AndroidPath_androidKt.Path();
        if (anchorLayoutCoordinates != null) {
            int caretHeightPx = density.mo720roundToPx0680j_4(DpSize.m7112getHeightD9Ej5fM(caretSize));
            int caretWidthPx = density.mo720roundToPx0680j_4(DpSize.m7114getWidthD9Ej5fM(caretSize));
            int $this$dp$iv = configuration.screenWidthDp;
            int screenWidthPx = density.mo720roundToPx0680j_4(Dp.m7016constructorimpl($this$dp$iv));
            int tooltipAnchorSpacing = density.mo720roundToPx0680j_4(TooltipKt.getSpacingBetweenTooltipAndAnchor());
            Rect anchorBounds = LayoutCoordinatesKt.boundsInWindow(anchorLayoutCoordinates);
            float anchorLeft = anchorBounds.getLeft();
            float anchorRight = anchorBounds.getRight();
            float anchorTop = anchorBounds.getTop();
            float f2 = 2;
            float anchorMid = (anchorRight + anchorLeft) / f2;
            float anchorWidth = anchorRight - anchorLeft;
            float tooltipWidth = Size.m4362getWidthimpl($this$drawCaretWithPath_u2dJKu_u2dmZY.m4183getSizeNHjbRc());
            float tooltipHeight = Size.m4359getHeightimpl($this$drawCaretWithPath_u2dJKu_u2dmZY.m4183getSizeNHjbRc());
            boolean isCaretTop = (anchorTop - tooltipHeight) - ((float) tooltipAnchorSpacing) < 0.0f;
            if (isCaretTop) {
                f = 0.0f;
            } else {
                f = tooltipHeight;
            }
            float caretY = f;
            if (caretType != CaretType.Plain) {
                anchorMidFromRightScreenEdge = caretY;
                long preferredPosition = OffsetKt.Offset(anchorMid - anchorLeft, anchorMidFromRightScreenEdge);
                if (anchorLeft + tooltipWidth > screenWidthPx) {
                    preferredPosition = OffsetKt.Offset(anchorMid - (anchorRight - tooltipWidth), anchorMidFromRightScreenEdge);
                    if (anchorRight - tooltipWidth < 0.0f) {
                        if ((anchorLeft - (tooltipWidth / f2)) + (anchorWidth / f2) <= 0.0f) {
                            preferredPosition = OffsetKt.Offset(anchorMid, anchorMidFromRightScreenEdge);
                        } else if (((tooltipWidth / f2) + anchorRight) - (anchorWidth / f2) >= screenWidthPx) {
                            float caretX = tooltipWidth - (screenWidthPx - anchorMid);
                            preferredPosition = OffsetKt.Offset(caretX, anchorMidFromRightScreenEdge);
                        } else {
                            preferredPosition = OffsetKt.Offset(tooltipWidth / f2, anchorMidFromRightScreenEdge);
                        }
                    }
                }
                position = preferredPosition;
            } else if ((tooltipWidth / f2) + anchorMid > screenWidthPx) {
                float caretX2 = tooltipWidth - (screenWidthPx - anchorMid);
                position = OffsetKt.Offset(caretX2, caretY);
                anchorMidFromRightScreenEdge = caretY;
            } else {
                anchorMidFromRightScreenEdge = caretY;
                float tooltipLeft = anchorLeft - ((Size.m4362getWidthimpl($this$drawCaretWithPath_u2dJKu_u2dmZY.m4183getSizeNHjbRc()) / f2) - (anchorWidth / f2));
                float caretX3 = anchorMid - Math.max(tooltipLeft, 0.0f);
                position = OffsetKt.Offset(caretX3, anchorMidFromRightScreenEdge);
            }
            if (isCaretTop) {
                path.moveTo(Offset.m4293getXimpl(position), Offset.m4294getYimpl(position));
                path.lineTo(Offset.m4293getXimpl(position) + (caretWidthPx / 2), Offset.m4294getYimpl(position));
                path.lineTo(Offset.m4293getXimpl(position), Offset.m4294getYimpl(position) - caretHeightPx);
                path.lineTo(Offset.m4293getXimpl(position) - (caretWidthPx / 2), Offset.m4294getYimpl(position));
                path.close();
            } else {
                path.moveTo(Offset.m4293getXimpl(position), Offset.m4294getYimpl(position));
                path.lineTo(Offset.m4293getXimpl(position) + (caretWidthPx / 2), Offset.m4294getYimpl(position));
                path.lineTo(Offset.m4293getXimpl(position), Offset.m4294getYimpl(position) + caretHeightPx);
                path.lineTo(Offset.m4293getXimpl(position) - (caretWidthPx / 2), Offset.m4294getYimpl(position));
                path.close();
            }
        }
        return $this$drawCaretWithPath_u2dJKu_u2dmZY.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.Tooltip_androidKt$drawCaretWithPath$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope $this$onDrawWithContent) {
                if (anchorLayoutCoordinates != null) {
                    $this$onDrawWithContent.drawContent();
                    DrawScope.m5080drawPathLG529CI$default($this$onDrawWithContent, path, containerColor, 0.0f, null, null, 0, 60, null);
                }
            }
        });
    }
}
