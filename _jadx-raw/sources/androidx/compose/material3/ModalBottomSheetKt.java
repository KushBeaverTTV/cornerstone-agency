package androidx.compose.material3;

import ai.nous.aurora.domain.model.ComposerChipPolicy;
import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.material3.internal.DraggableAnchors;
import androidx.compose.material3.internal.DraggableAnchorsConfig;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: ModalBottomSheet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÄ\u0001\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00132\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\n¢\u0006\u0002\b\u00182\u0013\b\u0002\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u001a0\n¢\u0006\u0002\b\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\b0\u001e¢\u0006\u0002\b\u0018¢\u0006\u0002\b H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a0\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u00132\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010%\u001a\u00020&H\u0003ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a-\u0010)\u001a\u00020\u000e2\b\b\u0002\u0010*\u001a\u00020&2\u0014\b\u0002\u0010+\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020&0\u001eH\u0007¢\u0006\u0002\u0010-\u001aó\u0001\u0010.\u001a\u00020\b*\u00020/2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u000203012\u0006\u00104\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\b0\n2!\u00107\u001a\u001d\u0012\u0013\u0012\u001102¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b0\u001e2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00042\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\n¢\u0006\u0002\b\u00182\u0013\b\u0002\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u001a0\n¢\u0006\u0002\b\u00182\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\b0\u001e¢\u0006\u0002\b\u0018¢\u0006\u0002\b H\u0001ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a\u0014\u0010=\u001a\u000202*\u00020>2\u0006\u0010?\u001a\u000202H\u0002\u001a\u0014\u0010@\u001a\u000202*\u00020>2\u0006\u0010?\u001a\u000202H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006A²\u0006\n\u0010B\u001a\u000202X\u008a\u0084\u0002"}, d2 = {"PredictiveBackChildTransformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "J", "PredictiveBackMaxScaleXDistance", "Landroidx/compose/ui/unit/Dp;", "F", "PredictiveBackMaxScaleYDistance", "ModalBottomSheet", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material3/SheetState;", "sheetMaxWidth", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "scrimColor", "dragHandle", "Landroidx/compose/runtime/Composable;", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "properties", "Landroidx/compose/material3/ModalBottomSheetProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ModalBottomSheet-dYc4hso", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFJLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/ModalBottomSheetProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "Scrim", "color", "visible", "", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "rememberModalBottomSheetState", "skipPartiallyExpanded", "confirmValueChange", "Landroidx/compose/material3/SheetValue;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "ModalBottomSheetContent", "Landroidx/compose/foundation/layout/BoxScope;", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "animateToDismiss", "settleToDismiss", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "velocity", "ModalBottomSheetContent-IQkwcL4", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/animation/core/Animatable;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "calculatePredictiveBackScaleX", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", NotificationCompat.CATEGORY_PROGRESS, "calculatePredictiveBackScaleY", "material3_release", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheetKt {
    private static final float PredictiveBackMaxScaleXDistance = Dp.m7016constructorimpl(48);
    private static final float PredictiveBackMaxScaleYDistance = Dp.m7016constructorimpl(24);
    private static final long PredictiveBackChildTransformOrigin = TransformOriginKt.TransformOrigin(0.5f, 0.0f);

    /* JADX INFO: renamed from: ModalBottomSheet-dYc4hso, reason: not valid java name */
    public static final void m2616ModalBottomSheetdYc4hso(final Function0<Unit> function0, Modifier modifier, SheetState sheetState, float sheetMaxWidth, Shape shape, long containerColor, long contentColor, float tonalElevation, long scrimColor, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, ? extends WindowInsets> function3, ModalBottomSheetProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        final SheetState sheetState2;
        Shape shape2;
        Modifier.Companion modifier3;
        SheetState sheetState3;
        float sheetMaxWidth2;
        Shape shape3;
        long containerColor2;
        long contentColor2;
        long scrimColor2;
        ModalBottomSheetKt$ModalBottomSheet$1 modalBottomSheetKt$ModalBottomSheet$1;
        ModalBottomSheetProperties properties2;
        int $dirty;
        int $dirty1;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function2<? super Composer, ? super Integer, ? extends WindowInsets> function6;
        long scrimColor3;
        float tonalElevation2;
        long contentColor3;
        Object value$iv$iv;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Function2<? super Composer, ? super Integer, Unit> function7;
        float tonalElevation3;
        long contentColor4;
        long containerColor3;
        long scrimColor4;
        SheetState sheetState4;
        Modifier modifier4;
        Shape shape4;
        float sheetMaxWidth3;
        ModalBottomSheetKt$ModalBottomSheet$4$1 value$iv4;
        Composer $composer2 = $composer.startRestartGroup(2132719801);
        ComposerKt.sourceInformation($composer2, "C(ModalBottomSheet)P(6,5,11,10:c#ui.unit.Dp,9,0:c#ui.graphics.Color,2:c#ui.graphics.Color,12:c#ui.unit.Dp,8:c#ui.graphics.Color,4,3,7)121@6012L31,123@6143L13,124@6206L14,125@6248L31,127@6356L10,133@6697L24,134@6761L327,145@7142L149,151@7326L42,155@7458L708,167@8231L771,153@7374L1628,193@9077L21,193@9050L48:ModalBottomSheet.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty3 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 4 : 2;
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
                sheetState2 = sheetState;
                int i3 = $composer2.changed(sheetState2) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                sheetState2 = sheetState;
            }
            $dirty2 |= i3;
        } else {
            sheetState2 = sheetState;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changed(sheetMaxWidth) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i5 = $composer2.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= ((i & 32) == 0 && $composer2.changed(containerColor)) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer2.changed(contentColor)) ? 1048576 : 524288;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changed(tonalElevation) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer2.changed(scrimColor)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty2 |= 805306368;
        } else if ((805306368 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        if (($changed1 & 6) == 0) {
            $dirty3 |= ((i & 1024) == 0 && $composer2.changedInstance(function3)) ? 4 : 2;
        }
        int i8 = i & 2048;
        if (i8 != 0) {
            $dirty3 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty3 |= $composer2.changed(properties) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty3 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty3 |= $composer2.changedInstance(function4) ? 256 : 128;
        }
        if (($dirty2 & 306783379) == 306783378 && ($dirty3 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            sheetMaxWidth3 = sheetMaxWidth;
            containerColor3 = containerColor;
            contentColor4 = contentColor;
            tonalElevation3 = tonalElevation;
            scrimColor4 = scrimColor;
            function7 = function2;
            function6 = function3;
            properties2 = properties;
            modifier4 = modifier2;
            sheetState4 = sheetState2;
            shape4 = shape2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    sheetState3 = rememberModalBottomSheetState(false, null, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    sheetState3 = sheetState2;
                }
                sheetMaxWidth2 = i4 != 0 ? BottomSheetDefaults.INSTANCE.m2167getSheetMaxWidthD9Ej5fM() : sheetMaxWidth;
                if ((i & 16) != 0) {
                    shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 64) != 0) {
                    contentColor2 = ColorSchemeKt.m2304contentColorForek8zF_U(containerColor2, $composer2, ($dirty2 >> 15) & 14);
                    $dirty2 &= -3670017;
                } else {
                    contentColor2 = contentColor;
                }
                float tonalElevation4 = i6 != 0 ? Dp.m7016constructorimpl(0) : tonalElevation;
                if ((i & 256) != 0) {
                    scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty2 &= -234881025;
                } else {
                    scrimColor2 = scrimColor;
                }
                Function2<? super Composer, ? super Integer, Unit> function2M2344getLambda1$material3_release = i7 != 0 ? ComposableSingletons$ModalBottomSheetKt.INSTANCE.m2344getLambda1$material3_release() : function2;
                if ((i & 1024) != 0) {
                    modalBottomSheetKt$ModalBottomSheet$1 = new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$1
                        public final WindowInsets invoke(Composer $composer3, int $changed2) {
                            $composer3.startReplaceGroup(58488196);
                            ComposerKt.sourceInformation($composer3, "C129@6530L12:ModalBottomSheet.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(58488196, $changed2, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.kt:129)");
                            }
                            WindowInsets windowInsets = BottomSheetDefaults.INSTANCE.getWindowInsets($composer3, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            $composer3.endReplaceGroup();
                            return windowInsets;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer, Integer num) {
                            return invoke(composer, num.intValue());
                        }
                    };
                    $dirty3 &= -15;
                } else {
                    modalBottomSheetKt$ModalBottomSheet$1 = function3;
                }
                if (i8 != 0) {
                    $dirty = $dirty2;
                    $dirty1 = $dirty3;
                    function6 = modalBottomSheetKt$ModalBottomSheet$1;
                    properties2 = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    shape2 = shape3;
                    sheetState2 = sheetState3;
                    tonalElevation2 = tonalElevation4;
                    function5 = function2M2344getLambda1$material3_release;
                    contentColor3 = contentColor2;
                    scrimColor3 = scrimColor2;
                } else {
                    properties2 = properties;
                    $dirty = $dirty2;
                    $dirty1 = $dirty3;
                    function5 = function2M2344getLambda1$material3_release;
                    function6 = modalBottomSheetKt$ModalBottomSheet$1;
                    shape2 = shape3;
                    scrimColor3 = scrimColor2;
                    sheetState2 = sheetState3;
                    tonalElevation2 = tonalElevation4;
                    contentColor3 = contentColor2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 256) != 0) {
                    $dirty2 &= -234881025;
                }
                if ((i & 1024) != 0) {
                    $dirty3 &= -15;
                }
                containerColor2 = containerColor;
                scrimColor3 = scrimColor;
                function5 = function2;
                function6 = function3;
                properties2 = properties;
                $dirty = $dirty2;
                $dirty1 = $dirty3;
                modifier3 = modifier2;
                sheetMaxWidth2 = sheetMaxWidth;
                contentColor3 = contentColor;
                tonalElevation2 = tonalElevation;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2132719801, $dirty, $dirty1, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.kt:132)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            int $dirty4 = $dirty1;
            ComposerKt.sourceInformationMarkerStart($composer2, -954363344, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            final float tonalElevation5 = tonalElevation2;
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2011393839, "CC(remember):ModalBottomSheet.kt#9igjgp");
            final long contentColor5 = contentColor3;
            boolean invalid$iv = (($dirty & 14) == 4) | (((($dirty & 896) ^ 384) > 256 && $composer2.changed(sheetState2)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        if (sheetState2.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Hidden).booleanValue()) {
                            Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(sheetState2, null), 3, null);
                            final SheetState sheetState5 = sheetState2;
                            final Function0<Unit> function1 = function0;
                            jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                    invoke2(th);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Throwable it) {
                                    if (!sheetState5.isVisible()) {
                                        function1.invoke();
                                    }
                                }
                            });
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$sheetState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$sheetState.hide(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final Function0 animateToDismiss = (Function0) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2011381825, "CC(remember):ModalBottomSheet.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(scope) | (((($dirty & 896) ^ 384) > 256 && $composer2.changed(sheetState2)) || ($dirty & 384) == 256) | (($dirty & 14) == 4);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                        invoke(f.floatValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ float $it;
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, float f, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                            this.$it = f;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$sheetState, this.$it, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$sheetState.settle$material3_release(this.$it, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    public final void invoke(float it) {
                        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(sheetState2, it, null), 3, null);
                        final SheetState sheetState5 = sheetState2;
                        final Function0<Unit> function1 = function0;
                        jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable it2) {
                                if (sheetState5.isVisible()) {
                                    return;
                                }
                                function1.invoke();
                            }
                        });
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final Function1 settleToDismiss = (Function1) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2011376044, "CC(remember):ModalBottomSheet.kt#9igjgp");
            Object value$iv5 = $composer2.rememberedValue();
            final long containerColor4 = containerColor2;
            if (value$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
                $composer2.updateRememberedValue(value$iv5);
            }
            final Animatable predictiveBackProgress = (Animatable) value$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2011371154, "CC(remember):ModalBottomSheet.kt#9igjgp");
            boolean invalid$iv3 = (((($dirty & 896) ^ 384) > 256 && $composer2.changed(sheetState2)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope) | $composer2.changedInstance(predictiveBackProgress) | (($dirty & 14) == 4);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        if (sheetState2.getCurrentValue() != SheetValue.Expanded || !sheetState2.getHasPartiallyExpandedState()) {
                            Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass3(sheetState2, null), 3, null);
                            final Function0<Unit> function1 = function0;
                            jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                    invoke2(th);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Throwable it) {
                                    function1.invoke();
                                }
                            });
                        } else {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(predictiveBackProgress, null), 3, null);
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass2(sheetState2, null), 3, null);
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1", f = "ModalBottomSheet.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$predictiveBackProgress = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$predictiveBackProgress, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    Animatable<Float, AnimationVector1D> animatable = this.$predictiveBackProgress;
                                    this.label = 1;
                                    if (animatable.animateTo(Boxing.boxFloat(0.0f), (4 & 2) != 0 ? animatable.defaultSpringSpec : null, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2, reason: invalid class name */
                    /* JADX INFO: compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2", f = "ModalBottomSheet.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass2(SheetState sheetState, Continuation<? super AnonymousClass2> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass2(this.$sheetState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$sheetState.partialExpand(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3, reason: invalid class name */
                    /* JADX INFO: compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3", f = "ModalBottomSheet.kt", i = {}, l = {164}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass3(SheetState sheetState, Continuation<? super AnonymousClass3> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass3(this.$sheetState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$sheetState.hide(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final long j = scrimColor3;
            final SheetState sheetState5 = sheetState2;
            final Modifier modifier5 = modifier3;
            final float f = sheetMaxWidth2;
            final Shape shape5 = shape2;
            final Function2 function8 = function5;
            final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function9 = function6;
            ModalBottomSheet_androidKt.ModalBottomSheetDialog((Function0) value$iv3, properties2, predictiveBackProgress, ComposableLambdaKt.rememberComposableLambda(-314673510, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3
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
                    Function0<ComposeUiNode> function1;
                    ComposerKt.sourceInformation($composer3, "C168@8241L755:ModalBottomSheet.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-314673510, $changed2, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.kt:168)");
                        }
                        Modifier modifier$iv = SemanticsModifierKt.semantics$default(WindowInsetsPadding_androidKt.imePadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null)), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
                            }
                        }, 1, null);
                        long j2 = j;
                        Function0<Unit> function10 = animateToDismiss;
                        SheetState sheetState6 = sheetState5;
                        Animatable<Float, AnimationVector1D> animatable = predictiveBackProgress;
                        CoroutineScope coroutineScope = scope;
                        Function1<Float, Unit> function11 = settleToDismiss;
                        Modifier modifier6 = modifier5;
                        float f2 = f;
                        Shape shape6 = shape5;
                        long j3 = containerColor4;
                        long j4 = contentColor5;
                        float f3 = tonalElevation5;
                        Function2<Composer, Integer, Unit> function12 = function8;
                        Function2<Composer, Integer, WindowInsets> function13 = function9;
                        Function3<ColumnScope, Composer, Integer, Unit> function14 = function4;
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
                            function1 = constructor;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor;
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
                        int $changed3 = ((0 >> 6) & 112) | 6;
                        BoxScope $this$invoke_u24lambda_u240 = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1107507610, "C169@8345L169,174@8527L459:ModalBottomSheet.kt#uh7d8r");
                        ModalBottomSheetKt.m2618Scrim3JVO9M(j2, function10, sheetState6.getTargetValue() != SheetValue.Hidden, $composer3, 0);
                        ModalBottomSheetKt.m2617ModalBottomSheetContentIQkwcL4($this$invoke_u24lambda_u240, animatable, coroutineScope, function10, function11, modifier6, sheetState6, f2, shape6, j3, j4, f3, function12, function13, function14, $composer3, ($changed3 & 14) | (Animatable.$stable << 3), 0, 0);
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
            }, $composer2, 54), $composer2, ($dirty4 & 112) | 3072 | (Animatable.$stable << 6));
            if (sheetState2.getHasExpandedState()) {
                ComposerKt.sourceInformationMarkerStart($composer2, -2011320033, "CC(remember):ModalBottomSheet.kt#9igjgp");
                boolean invalid$iv4 = ((($dirty & 896) ^ 384) > 256 && $composer2.changed(sheetState2)) || ($dirty & 384) == 256;
                Object it$iv4 = $composer2.rememberedValue();
                if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = new ModalBottomSheetKt$ModalBottomSheet$4$1(sheetState2, null);
                    $composer2.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(sheetState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer2, ($dirty >> 6) & 14);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function7 = function5;
            tonalElevation3 = tonalElevation5;
            contentColor4 = contentColor5;
            containerColor3 = containerColor4;
            scrimColor4 = scrimColor3;
            sheetState4 = sheetState2;
            modifier4 = modifier3;
            shape4 = shape2;
            sheetMaxWidth3 = sheetMaxWidth2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier4;
            final SheetState sheetState6 = sheetState4;
            final float f2 = sheetMaxWidth3;
            final Shape shape6 = shape4;
            final long j2 = containerColor3;
            final long j3 = contentColor4;
            final float f3 = tonalElevation3;
            final long j4 = scrimColor4;
            final Function2<? super Composer, ? super Integer, Unit> function10 = function7;
            final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function11 = function6;
            final ModalBottomSheetProperties modalBottomSheetProperties = properties2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$5
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
                    ModalBottomSheetKt.m2616ModalBottomSheetdYc4hso(function0, modifier6, sheetState6, f2, shape6, j2, j3, f3, j4, function10, function11, modalBottomSheetProperties, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:265:0x0409  */
    /* JADX WARN: Code duplicated, block: B:266:0x040b  */
    /* JADX WARN: Code duplicated, block: B:273:0x0423  */
    /* JADX WARN: Code duplicated, block: B:280:0x046a  */
    /* JADX WARN: Code duplicated, block: B:297:0x04b8  */
    /* JADX WARN: Code duplicated, block: B:304:0x04ce  */
    /* JADX WARN: Code duplicated, block: B:307:0x053f  */
    /* JADX INFO: renamed from: ModalBottomSheetContent-IQkwcL4, reason: not valid java name */
    public static final void m2617ModalBottomSheetContentIQkwcL4(final BoxScope $this$ModalBottomSheetContent_u2dIQkwcL4, final Animatable<Float, AnimationVector1D> animatable, final CoroutineScope scope, final Function0<Unit> function0, final Function1<? super Float, Unit> function1, Modifier modifier, SheetState sheetState, float sheetMaxWidth, Shape shape, long containerColor, long contentColor, float tonalElevation, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, ? extends WindowInsets> function3, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        Shape shape2;
        final SheetState sheetState2;
        Shape shape3;
        long containerColor2;
        long contentColor2;
        Function2<? super Composer, ? super Integer, ? extends WindowInsets> function5;
        int $dirty;
        int $dirty1;
        Function2<? super Composer, ? super Integer, Unit> function6;
        float sheetMaxWidth2;
        float f;
        long contentColor3;
        long contentColor4;
        float sheetMaxWidth3;
        Object value$iv;
        boolean invalid$iv;
        long containerColor3;
        Object value$iv2;
        boolean z;
        boolean invalid$iv2;
        Object value$iv3;
        boolean invalid$iv3;
        Object value$iv4;
        boolean z2;
        boolean invalid$iv4;
        Object value$iv5;
        float tonalElevation2;
        Modifier modifier3;
        Shape shape4;
        SheetState sheetState3;
        Composer $composer2 = $composer.startRestartGroup(-1676960531);
        ComposerKt.sourceInformation($composer2, "C(ModalBottomSheetContent)P(7,8!1,9,6,12,11:c#ui.unit.Dp,10,1:c#ui.graphics.Color,3:c#ui.graphics.Color,13:c#ui.unit.Dp,5,4)205@9432L31,207@9563L13,208@9626L14,209@9668L31,215@10018L48,224@10294L324,232@10728L1476,267@12552L23,269@12621L112,273@12765L612,288@13518L2623,217@10072L6069:ModalBottomSheet.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty3 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed($this$ModalBottomSheetContent_u2dIQkwcL4) ? 4 : 2;
        }
        if ((i & 1) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= ($changed & 64) == 0 ? $composer2.changed(animatable) : $composer2.changedInstance(animatable) ? 32 : 16;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changedInstance(scope) ? 256 : 128;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 2048 : 1024;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 16384 : 8192;
        }
        int i2 = i & 16;
        if (i2 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= ((i & 32) == 0 && $composer2.changed(sheetState)) ? 1048576 : 524288;
        }
        int i3 = i & 64;
        if (i3 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changed(sheetMaxWidth) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 128) == 0) {
                shape2 = shape;
                int i4 = $composer2.changed(shape2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty2 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer2.changed(containerColor)) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        if (($changed1 & 6) == 0) {
            $dirty3 |= ((i & 512) == 0 && $composer2.changed(contentColor)) ? 4 : 2;
        }
        int i5 = i & 1024;
        if (i5 != 0) {
            $dirty3 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty3 |= $composer2.changed(tonalElevation) ? 32 : 16;
        }
        int i6 = i & 2048;
        if (i6 != 0) {
            $dirty3 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty3 |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty3 |= ((i & 4096) == 0 && $composer2.changedInstance(function3)) ? 2048 : 1024;
        }
        if ((i & 8192) != 0) {
            $dirty3 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty3 |= $composer2.changedInstance(function4) ? 16384 : 8192;
        }
        if (($dirty2 & 306783379) == 306783378 && ($dirty3 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            sheetState3 = sheetState;
            sheetMaxWidth3 = sheetMaxWidth;
            containerColor3 = containerColor;
            contentColor4 = contentColor;
            tonalElevation2 = tonalElevation;
            function6 = function2;
            function5 = function3;
            shape4 = shape2;
            modifier3 = modifier2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 32) != 0) {
                    sheetState2 = rememberModalBottomSheetState(false, null, $composer2, 0, 3);
                    $dirty2 &= -3670017;
                } else {
                    sheetState2 = sheetState;
                }
                float sheetMaxWidth4 = i3 != 0 ? BottomSheetDefaults.INSTANCE.m2167getSheetMaxWidthD9Ej5fM() : sheetMaxWidth;
                Modifier modifier5 = modifier4;
                if ((i & 128) != 0) {
                    shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                    $dirty2 &= -234881025;
                } else {
                    shape3 = shape2;
                }
                if ((i & 256) != 0) {
                    $dirty2 &= -1879048193;
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 512) != 0) {
                    contentColor2 = ColorSchemeKt.m2304contentColorForek8zF_U(containerColor2, $composer2, ($dirty2 >> 27) & 14);
                    $dirty3 &= -15;
                } else {
                    contentColor2 = contentColor;
                }
                float tonalElevation3 = i5 != 0 ? BottomSheetDefaults.INSTANCE.m2166getElevationD9Ej5fM() : tonalElevation;
                Function2<? super Composer, ? super Integer, Unit> function2M2345getLambda2$material3_release = i6 != 0 ? ComposableSingletons$ModalBottomSheetKt.INSTANCE.m2345getLambda2$material3_release() : function2;
                if ((i & 4096) != 0) {
                    $dirty = $dirty2;
                    $dirty1 = $dirty3 & (-7169);
                    function6 = function2M2345getLambda2$material3_release;
                    function5 = new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$1
                        public final WindowInsets invoke(Composer $composer3, int $changed2) {
                            $composer3.startReplaceGroup(-11444670);
                            ComposerKt.sourceInformation($composer3, "C212@9919L12:ModalBottomSheet.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-11444670, $changed2, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:212)");
                            }
                            WindowInsets windowInsets = BottomSheetDefaults.INSTANCE.getWindowInsets($composer3, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            $composer3.endReplaceGroup();
                            return windowInsets;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer, Integer num) {
                            return invoke(composer, num.intValue());
                        }
                    };
                    sheetMaxWidth2 = sheetMaxWidth4;
                    modifier2 = modifier5;
                    shape2 = shape3;
                    f = tonalElevation3;
                    contentColor3 = contentColor2;
                } else {
                    shape2 = shape3;
                    function5 = function3;
                    $dirty = $dirty2;
                    $dirty1 = $dirty3;
                    function6 = function2M2345getLambda2$material3_release;
                    sheetMaxWidth2 = sheetMaxWidth4;
                    modifier2 = modifier5;
                    f = tonalElevation3;
                    contentColor3 = contentColor2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty2 &= -234881025;
                }
                if ((i & 256) != 0) {
                    $dirty2 &= -1879048193;
                }
                if ((i & 512) != 0) {
                    $dirty3 &= -15;
                }
                if ((i & 4096) != 0) {
                    $dirty3 &= -7169;
                }
                sheetState2 = sheetState;
                containerColor2 = containerColor;
                f = tonalElevation;
                function6 = function2;
                function5 = function3;
                $dirty = $dirty2;
                $dirty1 = $dirty3;
                sheetMaxWidth2 = sheetMaxWidth;
                contentColor3 = contentColor;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1676960531, $dirty, $dirty1, "androidx.compose.material3.ModalBottomSheetContent (ModalBottomSheet.kt:214)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String bottomSheetPaneTitle = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_pane_title), $composer2, 0);
            Modifier modifier6 = modifier2;
            contentColor4 = contentColor3;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SizeKt.m1086widthInVpY3zN4$default($this$ModalBottomSheetContent_u2dIQkwcL4.align(modifier2, Alignment.INSTANCE.getTopCenter()), 0.0f, sheetMaxWidth2, 1, null), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1237857395, "CC(remember):ModalBottomSheet.kt#9igjgp");
            boolean invalid$iv5 = (((3670016 & $dirty) ^ 1572864) > 1048576 && $composer2.changed(sheetState2)) || ($dirty & 1572864) == 1048576;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv5) {
                sheetMaxWidth3 = sheetMaxWidth2;
            } else {
                sheetMaxWidth3 = sheetMaxWidth2;
                if (it$iv != Composer.INSTANCE.getEmpty()) {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(modifierFillMaxWidth$default, (NestedScrollConnection) value$iv, null, 2, null);
                AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release = sheetState2.getAnchoredDraggableState$material3_release();
                Orientation orientation = Orientation.Vertical;
                ComposerKt.sourceInformationMarkerStart($composer2, 1237872435, "CC(remember):ModalBottomSheet.kt#9igjgp");
                invalid$iv = (((3670016 & $dirty) ^ 1572864) <= 1048576 && $composer2.changed(sheetState2)) || ($dirty & 1572864) == 1048576;
                containerColor3 = containerColor2;
                value$iv2 = $composer2.rememberedValue();
                if (!invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1

                        /* JADX INFO: compiled from: ModalBottomSheet.kt */
                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                        public /* synthetic */ class WhenMappings {
                            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                            static {
                                int[] iArr = new int[SheetValue.values().length];
                                try {
                                    iArr[SheetValue.Hidden.ordinal()] = 1;
                                } catch (NoSuchFieldError e) {
                                }
                                try {
                                    iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                                } catch (NoSuchFieldError e2) {
                                }
                                try {
                                    iArr[SheetValue.Expanded.ordinal()] = 3;
                                } catch (NoSuchFieldError e3) {
                                }
                                $EnumSwitchMapping$0 = iArr;
                            }
                        }

                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                            return m2620invokeGpV2Q24(intSize.getPackedValue(), constraints.getValue());
                        }

                        /* JADX INFO: renamed from: invoke-GpV2Q24, reason: not valid java name */
                        public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2620invokeGpV2Q24(final long sheetSize, long constraints) {
                            SheetValue sheetValue;
                            final float fullHeight = Constraints.m6968getMaxHeightimpl(constraints);
                            final SheetState sheetState4 = sheetState2;
                            DraggableAnchors newAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1$newAnchors$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                    invoke2(draggableAnchorsConfig);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                    draggableAnchorsConfig.at(SheetValue.Hidden, fullHeight);
                                    if (IntSize.m7185getHeightimpl(sheetSize) > fullHeight / 2 && !sheetState4.getSkipPartiallyExpanded()) {
                                        draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, fullHeight / 2.0f);
                                    }
                                    if (IntSize.m7185getHeightimpl(sheetSize) != 0) {
                                        draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, fullHeight - IntSize.m7185getHeightimpl(sheetSize)));
                                    }
                                }
                            });
                            switch (WhenMappings.$EnumSwitchMapping$0[sheetState2.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()]) {
                                case 1:
                                    sheetValue = SheetValue.Hidden;
                                    break;
                                case 2:
                                case 3:
                                    boolean hasPartiallyExpandedState = newAnchors.hasAnchorFor(SheetValue.PartiallyExpanded);
                                    if (!hasPartiallyExpandedState) {
                                        sheetValue = !newAnchors.hasAnchorFor(SheetValue.Expanded) ? SheetValue.Hidden : SheetValue.Expanded;
                                    } else {
                                        sheetValue = SheetValue.PartiallyExpanded;
                                    }
                                    break;
                                default:
                                    throw new NoWhenBranchMatchedException();
                            }
                            SheetValue newTarget = sheetValue;
                            return TuplesKt.to(newAnchors, newTarget);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierDraggableAnchors = AnchoredDraggableKt.draggableAnchors(modifierNestedScroll$default, anchoredDraggableState$material3_release, orientation, (Function2) value$iv2);
                DraggableState draggableState = sheetState2.getAnchoredDraggableState$material3_release().getDraggableState();
                Orientation orientation2 = Orientation.Vertical;
                boolean zIsVisible = sheetState2.isVisible();
                boolean zIsAnimationRunning = sheetState2.getAnchoredDraggableState$material3_release().isAnimationRunning();
                ComposerKt.sourceInformationMarkerStart($composer2, 1237929350, "CC(remember):ModalBottomSheet.kt#9igjgp");
                if ((57344 & $dirty) == 16384) {
                    z = true;
                } else {
                    z = false;
                }
                invalid$iv2 = z;
                Object it$iv2 = $composer2.rememberedValue();
                if (!invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierDraggable = DraggableKt.draggable(modifierDraggableAnchors, draggableState, orientation2, (188 & 4) != 0 ? true : zIsVisible, (188 & 8) != 0 ? null : null, (188 & 16) != 0 ? false : zIsAnimationRunning, (188 & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (188 & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) value$iv3, (188 & 128) != 0 ? false : false);
                ComposerKt.sourceInformationMarkerStart($composer2, 1237931647, "CC(remember):ModalBottomSheet.kt#9igjgp");
                invalid$iv3 = $composer2.changed(bottomSheetPaneTitle);
                Object it$iv3 = $composer2.rememberedValue();
                if (!invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.setPaneTitle($this$semantics, bottomSheetPaneTitle);
                            SemanticsPropertiesKt.setTraversalIndex($this$semantics, 0.0f);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierDraggable, false, (Function1) value$iv4, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 1237936755, "CC(remember):ModalBottomSheet.kt#9igjgp");
                boolean z3 = (((3670016 & $dirty) ^ 1572864) <= 1048576 && $composer2.changed(sheetState2)) || ($dirty & 1572864) == 1048576;
                if (($dirty & 112) != 32 || (($dirty & 64) != 0 && $composer2.changedInstance(animatable))) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                invalid$iv4 = z3 | z2;
                Object it$iv4 = $composer2.rememberedValue();
                if (!invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv5 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                            invoke2(graphicsLayerScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                            float sheetOffset = sheetState2.getAnchoredDraggableState$material3_release().getOffset();
                            float sheetHeight = Size.m4359getHeightimpl($this$graphicsLayer.getSize());
                            if (Float.isNaN(sheetOffset) || Float.isNaN(sheetHeight)) {
                                return;
                            }
                            if (!(sheetHeight == 0.0f)) {
                                float progress = animatable.getValue().floatValue();
                                $this$graphicsLayer.setScaleX(ModalBottomSheetKt.calculatePredictiveBackScaleX($this$graphicsLayer, progress));
                                $this$graphicsLayer.setScaleY(ModalBottomSheetKt.calculatePredictiveBackScaleY($this$graphicsLayer, progress));
                                $this$graphicsLayer.mo4727setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (sheetOffset + sheetHeight) / sheetHeight));
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv5);
                } else {
                    value$iv5 = it$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function7 = function5;
                final Function2<? super Composer, ? super Integer, Unit> function8 = function6;
                final SheetState sheetState4 = sheetState2;
                SurfaceKt.m2917SurfaceT9BRK9s(GraphicsLayerModifierKt.graphicsLayer(modifierSemantics$default, (Function1) value$iv5), shape2, containerColor3, contentColor4, f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-692668920, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7
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

                    /* JADX WARN: Code duplicated, block: B:35:0x01a4  */
                    /* JADX WARN: Code duplicated, block: B:45:0x02ae  */
                    /* JADX WARN: Code duplicated, block: B:48:0x02ba  */
                    /* JADX WARN: Code duplicated, block: B:49:0x02c0  */
                    /* JADX WARN: Code duplicated, block: B:59:0x0376  */
                    /* JADX WARN: Code duplicated, block: B:62:0x03aa  */
                    /* JADX WARN: Code duplicated, block: B:65:? A[RETURN, SYNTHETIC] */
                    public final void invoke(Composer composer, int i7) {
                        Object obj;
                        Function0<ComposeUiNode> function9;
                        Function0<ComposeUiNode> function10;
                        ColumnScopeInstance columnScopeInstance;
                        Composer composer2;
                        boolean zChanged;
                        Object obj2;
                        int currentCompositeKeyHash;
                        Function0<ComposeUiNode> constructor;
                        Function0<ComposeUiNode> function11;
                        Composer composerM4027constructorimpl;
                        ComposerKt.sourceInformation(composer, "C290@13592L21,290@13629L550,289@13528L2607:ModalBottomSheet.kt#uh7d8r");
                        if ((i7 & 3) != 2 || !composer.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-692668920, i7, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:289)");
                            }
                            Modifier modifierWindowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), function7.invoke(composer, 0));
                            ComposerKt.sourceInformationMarkerStart(composer, 320924084, "CC(remember):ModalBottomSheet.kt#9igjgp");
                            boolean zChangedInstance = composer.changedInstance(animatable);
                            final Animatable<Float, AnimationVector1D> animatable2 = animatable;
                            Object objRememberedValue = composer.rememberedValue();
                            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                obj = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                        invoke2(graphicsLayerScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                                        float progress = animatable2.getValue().floatValue();
                                        float predictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX($this$graphicsLayer, progress);
                                        float predictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY($this$graphicsLayer, progress);
                                        $this$graphicsLayer.setScaleY(!((predictiveBackScaleY > 0.0f ? 1 : (predictiveBackScaleY == 0.0f ? 0 : -1)) == 0) ? predictiveBackScaleX / predictiveBackScaleY : 1.0f);
                                        $this$graphicsLayer.mo4727setTransformOrigin__ExYCQ(ModalBottomSheetKt.PredictiveBackChildTransformOrigin);
                                    }
                                };
                                composer.updateRememberedValue(obj);
                            } else {
                                obj = objRememberedValue;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierWindowInsetsPadding, (Function1) obj);
                            Function2<Composer, Integer, Unit> function12 = function8;
                            final SheetState sheetState5 = sheetState4;
                            final Function0<Unit> function13 = function0;
                            final CoroutineScope coroutineScope = scope;
                            Function3<ColumnScope, Composer, Integer, Unit> function14 = function4;
                            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierGraphicsLayer);
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int i8 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer.startReusableNode();
                            if (composer.getInserting()) {
                                function9 = constructor2;
                                composer.createNode(function9);
                            } else {
                                function9 = constructor2;
                                composer.useNode();
                            }
                            Composer composerM4027constructorimpl2 = Updater.m4027constructorimpl(composer);
                            Updater.m4034setimpl(composerM4027constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl(composerM4027constructorimpl2, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!composerM4027constructorimpl2.getInserting()) {
                                function10 = function9;
                                if (!Intrinsics.areEqual(composerM4027constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                }
                                Updater.m4034setimpl(composerM4027constructorimpl2, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                int i9 = (i8 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart(composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                                int i10 = ((0 >> 6) & 112) | 6;
                                columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                                composer.startReplaceGroup(-1636564008);
                                ComposerKt.sourceInformation(composer, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                                if (function12 != null) {
                                    Strings.Companion companion2 = Strings.INSTANCE;
                                    final String strM3349getString2EP1pXo = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer, 0);
                                    Strings.Companion companion3 = Strings.INSTANCE;
                                    final String strM3349getString2EP1pXo2 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer, 0);
                                    Strings.Companion companion4 = Strings.INSTANCE;
                                    final String strM3349getString2EP1pXo3 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer, 0);
                                    Modifier modifierAlign = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                                    ComposerKt.sourceInformationMarkerStart(composer, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                    zChanged = composer.changed(sheetState5) | composer.changed(strM3349getString2EP1pXo2) | composer.changed(function13) | composer.changed(strM3349getString2EP1pXo3) | composer.changedInstance(coroutineScope) | composer.changed(strM3349getString2EP1pXo);
                                    Object objRememberedValue2 = composer.rememberedValue();
                                    if (!zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        obj2 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                                final SheetState $this$invoke_u24lambda_u240 = sheetState5;
                                                String str = strM3349getString2EP1pXo2;
                                                String str2 = strM3349getString2EP1pXo3;
                                                String str3 = strM3349getString2EP1pXo;
                                                final Function0<Unit> function15 = function13;
                                                final CoroutineScope coroutineScope2 = coroutineScope;
                                                final SheetState sheetState6 = sheetState5;
                                                SemanticsPropertiesKt.dismiss($this$semantics, str, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        function15.invoke();
                                                        return true;
                                                    }
                                                });
                                                if ($this$invoke_u24lambda_u240.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                    SemanticsPropertiesKt.expand($this$semantics, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(0);
                                                        }

                                                        /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                                        /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                        @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                            final /* synthetic */ SheetState $sheetState;
                                                            int label;

                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                super(2, continuation);
                                                                this.$sheetState = sheetState;
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                return new AnonymousClass1(this.$sheetState, continuation);
                                                            }

                                                            @Override // kotlin.jvm.functions.Function2
                                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Object invokeSuspend(Object $result) {
                                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                switch (this.label) {
                                                                    case 0:
                                                                        ResultKt.throwOnFailure($result);
                                                                        this.label = 1;
                                                                        if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                            return coroutine_suspended;
                                                                        }
                                                                        break;
                                                                    case 1:
                                                                        ResultKt.throwOnFailure($result);
                                                                        break;
                                                                    default:
                                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                }
                                                                return Unit.INSTANCE;
                                                            }
                                                        }

                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                        @Override // kotlin.jvm.functions.Function0
                                                        public final Boolean invoke() {
                                                            if ($this$invoke_u24lambda_u240.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(sheetState6, null), 3, null);
                                                            }
                                                            return true;
                                                        }
                                                    });
                                                } else if ($this$invoke_u24lambda_u240.getHasPartiallyExpandedState()) {
                                                    SemanticsPropertiesKt.collapse($this$semantics, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(0);
                                                        }

                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                        @Override // kotlin.jvm.functions.Function0
                                                        public final Boolean invoke() {
                                                            if ($this$invoke_u24lambda_u240.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                            }
                                                            return true;
                                                        }

                                                        /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                                        /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                        @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                            final /* synthetic */ SheetState $this_with;
                                                            int label;

                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                super(2, continuation);
                                                                this.$this_with = sheetState;
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                return new AnonymousClass1(this.$this_with, continuation);
                                                            }

                                                            @Override // kotlin.jvm.functions.Function2
                                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Object invokeSuspend(Object $result) {
                                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                switch (this.label) {
                                                                    case 0:
                                                                        ResultKt.throwOnFailure($result);
                                                                        this.label = 1;
                                                                        if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                            return coroutine_suspended;
                                                                        }
                                                                        break;
                                                                    case 1:
                                                                        ResultKt.throwOnFailure($result);
                                                                        break;
                                                                    default:
                                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                }
                                                                return Unit.INSTANCE;
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        };
                                        composer.updateRememberedValue(obj2);
                                    } else {
                                        obj2 = objRememberedValue2;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(r8);
                                    Modifier modifierSemantics = SemanticsModifierKt.semantics(modifierAlign, true, (Function1) obj2);
                                    composer2 = composer;
                                    ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, modifierSemantics);
                                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    int i11 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer2.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer2.startReusableNode();
                                    if (composer2.getInserting()) {
                                        function11 = constructor;
                                        composer2.createNode(function11);
                                    } else {
                                        function11 = constructor;
                                        composer2.useNode();
                                    }
                                    composerM4027constructorimpl = Updater.m4027constructorimpl(composer2);
                                    Updater.m4034setimpl(composerM4027constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4034setimpl(composerM4027constructorimpl, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (!composerM4027constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4027constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM4027constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM4027constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash2);
                                    }
                                    Updater.m4034setimpl(composerM4027constructorimpl, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i12 = (i11 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i13 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer2, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                                    function12.invoke(composer2, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                } else {
                                    composer2 = composer;
                                }
                                composer2.endReplaceGroup();
                                function14.invoke(columnScopeInstance, composer2, Integer.valueOf(i10 & 14));
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            function10 = function9;
                            composerM4027constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            composerM4027constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash);
                            Updater.m4034setimpl(composerM4027constructorimpl2, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            int i14 = (i8 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            int i15 = ((0 >> 6) & 112) | 6;
                            columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                            composer.startReplaceGroup(-1636564008);
                            ComposerKt.sourceInformation(composer, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                            if (function12 != null) {
                                Strings.Companion companion5 = Strings.INSTANCE;
                                final String strM3349getString2EP1pXo4 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer, 0);
                                Strings.Companion companion6 = Strings.INSTANCE;
                                final String strM3349getString2EP1pXo5 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer, 0);
                                Strings.Companion companion7 = Strings.INSTANCE;
                                final String strM3349getString2EP1pXo6 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer, 0);
                                Modifier modifierAlign2 = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                                ComposerKt.sourceInformationMarkerStart(composer, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                zChanged = composer.changed(sheetState5) | composer.changed(strM3349getString2EP1pXo5) | composer.changed(function13) | composer.changed(strM3349getString2EP1pXo6) | composer.changedInstance(coroutineScope) | composer.changed(strM3349getString2EP1pXo4);
                                Object objRememberedValue3 = composer.rememberedValue();
                                if (!zChanged) {
                                }
                                obj2 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                        final SheetState $this$invoke_u24lambda_u240 = sheetState5;
                                        String str = strM3349getString2EP1pXo5;
                                        String str2 = strM3349getString2EP1pXo6;
                                        String str3 = strM3349getString2EP1pXo4;
                                        final Function0<Unit> function15 = function13;
                                        final CoroutineScope coroutineScope2 = coroutineScope;
                                        final SheetState sheetState6 = sheetState5;
                                        SemanticsPropertiesKt.dismiss($this$semantics, str, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                function15.invoke();
                                                return true;
                                            }
                                        });
                                        if ($this$invoke_u24lambda_u240.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                            SemanticsPropertiesKt.expand($this$semantics, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                                /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ SheetState $sheetState;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$sheetState = sheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$sheetState, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object $result) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        switch (this.label) {
                                                            case 0:
                                                                ResultKt.throwOnFailure($result);
                                                                this.label = 1;
                                                                if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                    return coroutine_suspended;
                                                                }
                                                                break;
                                                            case 1:
                                                                ResultKt.throwOnFailure($result);
                                                                break;
                                                            default:
                                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                        }
                                                        return Unit.INSTANCE;
                                                    }
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    if ($this$invoke_u24lambda_u240.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(sheetState6, null), 3, null);
                                                    }
                                                    return true;
                                                }
                                            });
                                        } else if ($this$invoke_u24lambda_u240.getHasPartiallyExpandedState()) {
                                            SemanticsPropertiesKt.collapse($this$semantics, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    if ($this$invoke_u24lambda_u240.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                    }
                                                    return true;
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                                /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ SheetState $this_with;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$this_with = sheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$this_with, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object $result) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        switch (this.label) {
                                                            case 0:
                                                                ResultKt.throwOnFailure($result);
                                                                this.label = 1;
                                                                if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                    return coroutine_suspended;
                                                                }
                                                                break;
                                                            case 1:
                                                                ResultKt.throwOnFailure($result);
                                                                break;
                                                            default:
                                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                        }
                                                        return Unit.INSTANCE;
                                                    }
                                                }
                                            });
                                        }
                                    }
                                };
                                composer.updateRememberedValue(obj2);
                                ComposerKt.sourceInformationMarkerEnd(r8);
                                Modifier modifierSemantics2 = SemanticsModifierKt.semantics(modifierAlign2, true, (Function1) obj2);
                                composer2 = composer;
                                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifierSemantics2);
                                constructor = ComposeUiNode.INSTANCE.getConstructor();
                                int i16 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    function11 = constructor;
                                    composer2.createNode(function11);
                                } else {
                                    function11 = constructor;
                                    composer2.useNode();
                                }
                                composerM4027constructorimpl = Updater.m4027constructorimpl(composer2);
                                Updater.m4034setimpl(composerM4027constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4034setimpl(composerM4027constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!composerM4027constructorimpl.getInserting()) {
                                }
                                composerM4027constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM4027constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash3);
                                Updater.m4034setimpl(composerM4027constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                                int i17 = (i16 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                int i18 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer2, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                                function12.invoke(composer2, 0);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                            } else {
                                composer2 = composer;
                            }
                            composer2.endReplaceGroup();
                            function14.invoke(columnScopeInstance, composer2, Integer.valueOf(i15 & 14));
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer.skipToGroupEnd();
                    }
                }, $composer2, 54), $composer2, (($dirty >> 21) & 112) | 12582912 | (($dirty >> 21) & 896) | (($dirty1 << 9) & 7168) | (($dirty1 << 9) & 57344), 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                tonalElevation2 = f;
                modifier3 = modifier6;
                shape4 = shape2;
                sheetState3 = sheetState2;
            }
            value$iv = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState2, Orientation.Vertical, function1);
            $composer2.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierNestedScroll$default2 = NestedScrollModifierKt.nestedScroll$default(modifierFillMaxWidth$default, (NestedScrollConnection) value$iv, null, 2, null);
            AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release2 = sheetState2.getAnchoredDraggableState$material3_release();
            Orientation orientation3 = Orientation.Vertical;
            ComposerKt.sourceInformationMarkerStart($composer2, 1237872435, "CC(remember):ModalBottomSheet.kt#9igjgp");
            if (((3670016 & $dirty) ^ 1572864) <= 1048576) {
            }
            containerColor3 = containerColor2;
            value$iv2 = $composer2.rememberedValue();
            if (!invalid$iv) {
            }
            value$iv2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1

                /* JADX INFO: compiled from: ModalBottomSheet.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[SheetValue.values().length];
                        try {
                            iArr[SheetValue.Hidden.ordinal()] = 1;
                        } catch (NoSuchFieldError e) {
                        }
                        try {
                            iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                        } catch (NoSuchFieldError e2) {
                        }
                        try {
                            iArr[SheetValue.Expanded.ordinal()] = 3;
                        } catch (NoSuchFieldError e3) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                    return m2620invokeGpV2Q24(intSize.getPackedValue(), constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-GpV2Q24, reason: not valid java name */
                public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2620invokeGpV2Q24(final long sheetSize, long constraints) {
                    SheetValue sheetValue;
                    final float fullHeight = Constraints.m6968getMaxHeightimpl(constraints);
                    final SheetState sheetState5 = sheetState2;
                    DraggableAnchors newAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1$newAnchors$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                            invoke2(draggableAnchorsConfig);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                            draggableAnchorsConfig.at(SheetValue.Hidden, fullHeight);
                            if (IntSize.m7185getHeightimpl(sheetSize) > fullHeight / 2 && !sheetState5.getSkipPartiallyExpanded()) {
                                draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, fullHeight / 2.0f);
                            }
                            if (IntSize.m7185getHeightimpl(sheetSize) != 0) {
                                draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, fullHeight - IntSize.m7185getHeightimpl(sheetSize)));
                            }
                        }
                    });
                    switch (WhenMappings.$EnumSwitchMapping$0[sheetState2.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()]) {
                        case 1:
                            sheetValue = SheetValue.Hidden;
                            break;
                        case 2:
                        case 3:
                            boolean hasPartiallyExpandedState = newAnchors.hasAnchorFor(SheetValue.PartiallyExpanded);
                            if (!hasPartiallyExpandedState) {
                                sheetValue = !newAnchors.hasAnchorFor(SheetValue.Expanded) ? SheetValue.Hidden : SheetValue.Expanded;
                            } else {
                                sheetValue = SheetValue.PartiallyExpanded;
                            }
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    SheetValue newTarget = sheetValue;
                    return TuplesKt.to(newAnchors, newTarget);
                }
            };
            $composer2.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierDraggableAnchors2 = AnchoredDraggableKt.draggableAnchors(modifierNestedScroll$default2, anchoredDraggableState$material3_release2, orientation3, (Function2) value$iv2);
            DraggableState draggableState2 = sheetState2.getAnchoredDraggableState$material3_release().getDraggableState();
            Orientation orientation4 = Orientation.Vertical;
            boolean zIsVisible2 = sheetState2.isVisible();
            boolean zIsAnimationRunning2 = sheetState2.getAnchoredDraggableState$material3_release().isAnimationRunning();
            ComposerKt.sourceInformationMarkerStart($composer2, 1237929350, "CC(remember):ModalBottomSheet.kt#9igjgp");
            if ((57344 & $dirty) == 16384) {
                z = true;
            } else {
                z = false;
            }
            invalid$iv2 = z;
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv2) {
                value$iv3 = new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
                $composer2.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
                $composer2.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierDraggable2 = DraggableKt.draggable(modifierDraggableAnchors2, draggableState2, orientation4, (188 & 4) != 0 ? true : zIsVisible2, (188 & 8) != 0 ? null : null, (188 & 16) != 0 ? false : zIsAnimationRunning2, (188 & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (188 & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) value$iv3, (188 & 128) != 0 ? false : false);
            ComposerKt.sourceInformationMarkerStart($composer2, 1237931647, "CC(remember):ModalBottomSheet.kt#9igjgp");
            invalid$iv3 = $composer2.changed(bottomSheetPaneTitle);
            Object it$iv6 = $composer2.rememberedValue();
            if (invalid$iv3) {
                value$iv4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setPaneTitle($this$semantics, bottomSheetPaneTitle);
                        SemanticsPropertiesKt.setTraversalIndex($this$semantics, 0.0f);
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setPaneTitle($this$semantics, bottomSheetPaneTitle);
                        SemanticsPropertiesKt.setTraversalIndex($this$semantics, 0.0f);
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifierDraggable2, false, (Function1) value$iv4, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1237936755, "CC(remember):ModalBottomSheet.kt#9igjgp");
            if (((3670016 & $dirty) ^ 1572864) <= 1048576) {
            }
            if (($dirty & 112) != 32) {
                z2 = true;
            } else {
                z2 = true;
            }
            invalid$iv4 = z3 | z2;
            Object it$iv7 = $composer2.rememberedValue();
            if (invalid$iv4) {
                value$iv5 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                        invoke2(graphicsLayerScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                        float sheetOffset = sheetState2.getAnchoredDraggableState$material3_release().getOffset();
                        float sheetHeight = Size.m4359getHeightimpl($this$graphicsLayer.getSize());
                        if (Float.isNaN(sheetOffset) || Float.isNaN(sheetHeight)) {
                            return;
                        }
                        if (!(sheetHeight == 0.0f)) {
                            float progress = animatable.getValue().floatValue();
                            $this$graphicsLayer.setScaleX(ModalBottomSheetKt.calculatePredictiveBackScaleX($this$graphicsLayer, progress));
                            $this$graphicsLayer.setScaleY(ModalBottomSheetKt.calculatePredictiveBackScaleY($this$graphicsLayer, progress));
                            $this$graphicsLayer.mo4727setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (sheetOffset + sheetHeight) / sheetHeight));
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                        invoke2(graphicsLayerScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                        float sheetOffset = sheetState2.getAnchoredDraggableState$material3_release().getOffset();
                        float sheetHeight = Size.m4359getHeightimpl($this$graphicsLayer.getSize());
                        if (Float.isNaN(sheetOffset) || Float.isNaN(sheetHeight)) {
                            return;
                        }
                        if (!(sheetHeight == 0.0f)) {
                            float progress = animatable.getValue().floatValue();
                            $this$graphicsLayer.setScaleX(ModalBottomSheetKt.calculatePredictiveBackScaleX($this$graphicsLayer, progress));
                            $this$graphicsLayer.setScaleY(ModalBottomSheetKt.calculatePredictiveBackScaleY($this$graphicsLayer, progress));
                            $this$graphicsLayer.mo4727setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (sheetOffset + sheetHeight) / sheetHeight));
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function9 = function5;
            final Function2<? super Composer, ? super Integer, Unit> function10 = function6;
            final SheetState sheetState5 = sheetState2;
            SurfaceKt.m2917SurfaceT9BRK9s(GraphicsLayerModifierKt.graphicsLayer(modifierSemantics$default2, (Function1) value$iv5), shape2, containerColor3, contentColor4, f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-692668920, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7
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

                /* JADX WARN: Code duplicated, block: B:35:0x01a4  */
                /* JADX WARN: Code duplicated, block: B:45:0x02ae  */
                /* JADX WARN: Code duplicated, block: B:48:0x02ba  */
                /* JADX WARN: Code duplicated, block: B:49:0x02c0  */
                /* JADX WARN: Code duplicated, block: B:59:0x0376  */
                /* JADX WARN: Code duplicated, block: B:62:0x03aa  */
                /* JADX WARN: Code duplicated, block: B:65:? A[RETURN, SYNTHETIC] */
                public final void invoke(Composer composer, int i7) {
                    Object obj;
                    Function0<ComposeUiNode> function11;
                    Function0<ComposeUiNode> function12;
                    ColumnScopeInstance columnScopeInstance;
                    Composer composer2;
                    boolean zChanged;
                    Object obj2;
                    int currentCompositeKeyHash;
                    Function0<ComposeUiNode> constructor;
                    Function0<ComposeUiNode> function13;
                    Composer composerM4027constructorimpl;
                    ComposerKt.sourceInformation(composer, "C290@13592L21,290@13629L550,289@13528L2607:ModalBottomSheet.kt#uh7d8r");
                    if ((i7 & 3) != 2 || !composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-692668920, i7, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:289)");
                        }
                        Modifier modifierWindowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), function9.invoke(composer, 0));
                        ComposerKt.sourceInformationMarkerStart(composer, 320924084, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        boolean zChangedInstance = composer.changedInstance(animatable);
                        final Animatable<Float, AnimationVector1D> animatable2 = animatable;
                        Object objRememberedValue = composer.rememberedValue();
                        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            obj = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                    invoke2(graphicsLayerScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                                    float progress = animatable2.getValue().floatValue();
                                    float predictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX($this$graphicsLayer, progress);
                                    float predictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY($this$graphicsLayer, progress);
                                    $this$graphicsLayer.setScaleY(!((predictiveBackScaleY > 0.0f ? 1 : (predictiveBackScaleY == 0.0f ? 0 : -1)) == 0) ? predictiveBackScaleX / predictiveBackScaleY : 1.0f);
                                    $this$graphicsLayer.mo4727setTransformOrigin__ExYCQ(ModalBottomSheetKt.PredictiveBackChildTransformOrigin);
                                }
                            };
                            composer.updateRememberedValue(obj);
                        } else {
                            obj = objRememberedValue;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierWindowInsetsPadding, (Function1) obj);
                        Function2<Composer, Integer, Unit> function14 = function10;
                        final SheetState sheetState6 = sheetState5;
                        final Function0<Unit> function15 = function0;
                        final CoroutineScope coroutineScope = scope;
                        Function3<ColumnScope, Composer, Integer, Unit> function16 = function4;
                        ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierGraphicsLayer);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int i8 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer.startReusableNode();
                        if (composer.getInserting()) {
                            function11 = constructor2;
                            composer.createNode(function11);
                        } else {
                            function11 = constructor2;
                            composer.useNode();
                        }
                        Composer composerM4027constructorimpl2 = Updater.m4027constructorimpl(composer);
                        Updater.m4034setimpl(composerM4027constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl(composerM4027constructorimpl2, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM4027constructorimpl2.getInserting()) {
                            function12 = function11;
                            if (!Intrinsics.areEqual(composerM4027constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            }
                            Updater.m4034setimpl(composerM4027constructorimpl2, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            int i14 = (i8 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            int i15 = ((0 >> 6) & 112) | 6;
                            columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                            composer.startReplaceGroup(-1636564008);
                            ComposerKt.sourceInformation(composer, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                            if (function14 != null) {
                                Strings.Companion companion5 = Strings.INSTANCE;
                                final String strM3349getString2EP1pXo4 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer, 0);
                                Strings.Companion companion6 = Strings.INSTANCE;
                                final String strM3349getString2EP1pXo5 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer, 0);
                                Strings.Companion companion7 = Strings.INSTANCE;
                                final String strM3349getString2EP1pXo6 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer, 0);
                                Modifier modifierAlign2 = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                                ComposerKt.sourceInformationMarkerStart(composer, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                zChanged = composer.changed(sheetState6) | composer.changed(strM3349getString2EP1pXo5) | composer.changed(function15) | composer.changed(strM3349getString2EP1pXo6) | composer.changedInstance(coroutineScope) | composer.changed(strM3349getString2EP1pXo4);
                                Object objRememberedValue3 = composer.rememberedValue();
                                if (!zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    obj2 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                            final SheetState $this$invoke_u24lambda_u240 = sheetState6;
                                            String str = strM3349getString2EP1pXo5;
                                            String str2 = strM3349getString2EP1pXo6;
                                            String str3 = strM3349getString2EP1pXo4;
                                            final Function0<Unit> function17 = function15;
                                            final CoroutineScope coroutineScope2 = coroutineScope;
                                            final SheetState sheetState7 = sheetState6;
                                            SemanticsPropertiesKt.dismiss($this$semantics, str, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    function17.invoke();
                                                    return true;
                                                }
                                            });
                                            if ($this$invoke_u24lambda_u240.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                SemanticsPropertiesKt.expand($this$semantics, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ SheetState $sheetState;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$sheetState = sheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$sheetState, continuation);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Object invokeSuspend(Object $result) {
                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                            switch (this.label) {
                                                                case 0:
                                                                    ResultKt.throwOnFailure($result);
                                                                    this.label = 1;
                                                                    if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                        return coroutine_suspended;
                                                                    }
                                                                    break;
                                                                case 1:
                                                                    ResultKt.throwOnFailure($result);
                                                                    break;
                                                                default:
                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                            }
                                                            return Unit.INSTANCE;
                                                        }
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        if ($this$invoke_u24lambda_u240.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(sheetState7, null), 3, null);
                                                        }
                                                        return true;
                                                    }
                                                });
                                            } else if ($this$invoke_u24lambda_u240.getHasPartiallyExpandedState()) {
                                                SemanticsPropertiesKt.collapse($this$semantics, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        if ($this$invoke_u24lambda_u240.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                        }
                                                        return true;
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ SheetState $this_with;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$this_with = sheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$this_with, continuation);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Object invokeSuspend(Object $result) {
                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                            switch (this.label) {
                                                                case 0:
                                                                    ResultKt.throwOnFailure($result);
                                                                    this.label = 1;
                                                                    if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                        return coroutine_suspended;
                                                                    }
                                                                    break;
                                                                case 1:
                                                                    ResultKt.throwOnFailure($result);
                                                                    break;
                                                                default:
                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                            }
                                                            return Unit.INSTANCE;
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    };
                                    composer.updateRememberedValue(obj2);
                                } else {
                                    obj2 = objRememberedValue3;
                                }
                                ComposerKt.sourceInformationMarkerEnd(r8);
                                Modifier modifierSemantics2 = SemanticsModifierKt.semantics(modifierAlign2, true, (Function1) obj2);
                                composer2 = composer;
                                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifierSemantics2);
                                constructor = ComposeUiNode.INSTANCE.getConstructor();
                                int i16 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    function13 = constructor;
                                    composer2.createNode(function13);
                                } else {
                                    function13 = constructor;
                                    composer2.useNode();
                                }
                                composerM4027constructorimpl = Updater.m4027constructorimpl(composer2);
                                Updater.m4034setimpl(composerM4027constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4034setimpl(composerM4027constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!composerM4027constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4027constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM4027constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM4027constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash3);
                                }
                                Updater.m4034setimpl(composerM4027constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                                int i17 = (i16 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                int i18 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer2, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                                function14.invoke(composer2, 0);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                            } else {
                                composer2 = composer;
                            }
                            composer2.endReplaceGroup();
                            function16.invoke(columnScopeInstance, composer2, Integer.valueOf(i15 & 14));
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        function12 = function11;
                        composerM4027constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM4027constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash);
                        Updater.m4034setimpl(composerM4027constructorimpl2, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        int i19 = (i8 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        int i110 = ((0 >> 6) & 112) | 6;
                        columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                        composer.startReplaceGroup(-1636564008);
                        ComposerKt.sourceInformation(composer, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                        if (function14 != null) {
                            Strings.Companion companion8 = Strings.INSTANCE;
                            final String strM3349getString2EP1pXo7 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer, 0);
                            Strings.Companion companion9 = Strings.INSTANCE;
                            final String strM3349getString2EP1pXo8 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer, 0);
                            Strings.Companion companion10 = Strings.INSTANCE;
                            final String strM3349getString2EP1pXo9 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer, 0);
                            Modifier modifierAlign3 = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                            ComposerKt.sourceInformationMarkerStart(composer, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                            zChanged = composer.changed(sheetState6) | composer.changed(strM3349getString2EP1pXo8) | composer.changed(function15) | composer.changed(strM3349getString2EP1pXo9) | composer.changedInstance(coroutineScope) | composer.changed(strM3349getString2EP1pXo7);
                            Object objRememberedValue4 = composer.rememberedValue();
                            if (!zChanged) {
                            }
                            obj2 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                    final SheetState $this$invoke_u24lambda_u240 = sheetState6;
                                    String str = strM3349getString2EP1pXo8;
                                    String str2 = strM3349getString2EP1pXo9;
                                    String str3 = strM3349getString2EP1pXo7;
                                    final Function0<Unit> function17 = function15;
                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                    final SheetState sheetState7 = sheetState6;
                                    SemanticsPropertiesKt.dismiss($this$semantics, str, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            function17.invoke();
                                            return true;
                                        }
                                    });
                                    if ($this$invoke_u24lambda_u240.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                        SemanticsPropertiesKt.expand($this$semantics, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                            /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ SheetState $sheetState;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                    super(2, continuation);
                                                    this.$sheetState = sheetState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new AnonymousClass1(this.$sheetState, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object $result) {
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    switch (this.label) {
                                                        case 0:
                                                            ResultKt.throwOnFailure($result);
                                                            this.label = 1;
                                                            if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                return coroutine_suspended;
                                                            }
                                                            break;
                                                        case 1:
                                                            ResultKt.throwOnFailure($result);
                                                            break;
                                                        default:
                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                    }
                                                    return Unit.INSTANCE;
                                                }
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                if ($this$invoke_u24lambda_u240.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(sheetState7, null), 3, null);
                                                }
                                                return true;
                                            }
                                        });
                                    } else if ($this$invoke_u24lambda_u240.getHasPartiallyExpandedState()) {
                                        SemanticsPropertiesKt.collapse($this$semantics, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                if ($this$invoke_u24lambda_u240.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                }
                                                return true;
                                            }

                                            /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                            /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ SheetState $this_with;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                    super(2, continuation);
                                                    this.$this_with = sheetState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new AnonymousClass1(this.$this_with, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object $result) {
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    switch (this.label) {
                                                        case 0:
                                                            ResultKt.throwOnFailure($result);
                                                            this.label = 1;
                                                            if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                return coroutine_suspended;
                                                            }
                                                            break;
                                                        case 1:
                                                            ResultKt.throwOnFailure($result);
                                                            break;
                                                        default:
                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                    }
                                                    return Unit.INSTANCE;
                                                }
                                            }
                                        });
                                    }
                                }
                            };
                            composer.updateRememberedValue(obj2);
                            ComposerKt.sourceInformationMarkerEnd(r8);
                            Modifier modifierSemantics3 = SemanticsModifierKt.semantics(modifierAlign3, true, (Function1) obj2);
                            composer2 = composer;
                            ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer2, modifierSemantics3);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int i111 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                function13 = constructor;
                                composer2.createNode(function13);
                            } else {
                                function13 = constructor;
                                composer2.useNode();
                            }
                            composerM4027constructorimpl = Updater.m4027constructorimpl(composer2);
                            Updater.m4034setimpl(composerM4027constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl(composerM4027constructorimpl, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!composerM4027constructorimpl.getInserting()) {
                            }
                            composerM4027constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM4027constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash4);
                            Updater.m4034setimpl(composerM4027constructorimpl, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                            int i112 = (i111 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                            int i113 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer2, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                            function14.invoke(composer2, 0);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                        } else {
                            composer2 = composer;
                        }
                        composer2.endReplaceGroup();
                        function16.invoke(columnScopeInstance, composer2, Integer.valueOf(i110 & 14));
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, (($dirty >> 21) & 112) | 12582912 | (($dirty >> 21) & 896) | (($dirty1 << 9) & 7168) | (($dirty1 << 9) & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            tonalElevation2 = f;
            modifier3 = modifier6;
            shape4 = shape2;
            sheetState3 = sheetState2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier7 = modifier3;
            final SheetState sheetState6 = sheetState3;
            final float f2 = sheetMaxWidth3;
            final Shape shape5 = shape4;
            final long j = containerColor3;
            final long j2 = contentColor4;
            final float f3 = tonalElevation2;
            final Function2<? super Composer, ? super Integer, Unit> function11 = function6;
            final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function12 = function5;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$8
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
                    ModalBottomSheetKt.m2617ModalBottomSheetContentIQkwcL4($this$ModalBottomSheetContent_u2dIQkwcL4, animatable, scope, function0, function1, modifier7, sheetState6, f2, shape5, j, j2, f3, function11, function12, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleX(GraphicsLayerScope $this$calculatePredictiveBackScaleX, float progress) {
        float width = Size.m4362getWidthimpl($this$calculatePredictiveBackScaleX.getSize());
        if (Float.isNaN(width)) {
            return 1.0f;
        }
        if (width == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min($this$calculatePredictiveBackScaleX.mo726toPx0680j_4(PredictiveBackMaxScaleXDistance), width), progress) / width);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleY(GraphicsLayerScope $this$calculatePredictiveBackScaleY, float progress) {
        float height = Size.m4359getHeightimpl($this$calculatePredictiveBackScaleY.getSize());
        if (Float.isNaN(height)) {
            return 1.0f;
        }
        if (height == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min($this$calculatePredictiveBackScaleY.mo726toPx0680j_4(PredictiveBackMaxScaleYDistance), height), progress) / height);
    }

    public static final SheetState rememberModalBottomSheetState(boolean skipPartiallyExpanded, Function1<? super SheetValue, Boolean> function1, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -778250030, "C(rememberModalBottomSheetState)P(1)400@18058L160:ModalBottomSheet.kt#uh7d8r");
        if ((i & 1) != 0) {
            skipPartiallyExpanded = false;
        }
        if ((i & 2) != 0) {
            Function1 confirmValueChange = new Function1<SheetValue, Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt.rememberModalBottomSheetState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(SheetValue it) {
                    return true;
                }
            };
            function1 = confirmValueChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-778250030, $changed, -1, "androidx.compose.material3.rememberModalBottomSheetState (ModalBottomSheet.kt:400)");
        }
        SheetState sheetStateRememberSheetState = SheetDefaultsKt.rememberSheetState(skipPartiallyExpanded, function1, SheetValue.Hidden, false, $composer, ($changed & 14) | 384 | ($changed & 112), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return sheetStateRememberSheetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m2618Scrim3JVO9M(final long color, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissSheet;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Composer $composer2 = $composer.startRestartGroup(951870469);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(0:c#ui.graphics.Color)410@18376L87,411@18489L29,426@19112L79,426@19062L129:ModalBottomSheet.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(color) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(visible) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(951870469, $dirty, -1, "androidx.compose.material3.Scrim (ModalBottomSheet.kt:407)");
            }
            if ((color != 16 ? 1 : 0) != 0) {
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(visible ? 1.0f : 0.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                Strings.Companion companion = Strings.INSTANCE;
                final String closeSheet = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(androidx.compose.ui.R.string.close_sheet), $composer2, 0);
                $composer2.startReplaceGroup(-1785653838);
                ComposerKt.sourceInformation($composer2, "414@18629L44,415@18730L263");
                if (visible) {
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1785652017, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean invalid$iv = ($dirty & 112) == 32;
                    Object it$iv = $composer2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new ModalBottomSheetKt$Scrim$dismissSheet$1$1(function0, null);
                        $composer2.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion2, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1785648566, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean invalid$iv2 = $composer2.changed(closeSheet) | (($dirty & 112) == 32);
                    Object it$iv2 = $composer2.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$dismissSheet$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                SemanticsPropertiesKt.setTraversalIndex($this$semantics, 1.0f);
                                SemanticsPropertiesKt.setContentDescription($this$semantics, closeSheet);
                                final Function0<Unit> function1 = function0;
                                SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$dismissSheet$2$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        function1.invoke();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    dismissSheet = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) value$iv3);
                } else {
                    dismissSheet = Modifier.INSTANCE;
                }
                $composer2.endReplaceGroup();
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissSheet);
                ComposerKt.sourceInformationMarkerStart($composer2, -1785636526, "CC(remember):ModalBottomSheet.kt#9igjgp");
                boolean invalid$iv3 = $composer2.changed(stateAnimateFloatAsState) | (($dirty & 14) == 4);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$1$1
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
                            DrawScope.m5084drawRectnJ9OG0$default($this$Canvas, color, 0L, 0L, RangesKt.coerceIn(ModalBottomSheetKt.Scrim_3J_VO9M$lambda$10(stateAnimateFloatAsState), 0.0f, 1.0f), null, null, 0, ComposerChipPolicy.CHIP_MAX_WIDTH_DP, null);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierThen, (Function1) value$iv, $composer2, 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ModalBottomSheetKt.m2618Scrim3JVO9M(color, function0, visible, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Scrim_3J_VO9M$lambda$10(State<Float> state) {
        return state.getValue().floatValue();
    }
}
