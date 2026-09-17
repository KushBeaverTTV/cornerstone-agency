package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.ScrollingContainerKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsState;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyGrid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a~\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017H\u0001¢\u0006\u0002\u0010\u0018\u001a~\u0010\u0019\u001a\u0019\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001a¢\u0006\u0002\b\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0003¢\u0006\u0002\u0010%¨\u0006&"}, d2 = {"LazyGrid", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "slots", "Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "rememberLazyGridMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridKt {
    /* JADX WARN: Code duplicated, block: B:100:0x0128  */
    /* JADX WARN: Code duplicated, block: B:103:0x012e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0134  */
    /* JADX WARN: Code duplicated, block: B:106:0x0139  */
    /* JADX WARN: Code duplicated, block: B:108:0x0141  */
    /* JADX WARN: Code duplicated, block: B:109:0x0144  */
    /* JADX WARN: Code duplicated, block: B:111:0x0149  */
    /* JADX WARN: Code duplicated, block: B:114:0x014f  */
    /* JADX WARN: Code duplicated, block: B:115:0x0152  */
    /* JADX WARN: Code duplicated, block: B:117:0x0156  */
    /* JADX WARN: Code duplicated, block: B:119:0x015c  */
    /* JADX WARN: Code duplicated, block: B:120:0x015e  */
    /* JADX WARN: Code duplicated, block: B:130:0x0187  */
    /* JADX WARN: Code duplicated, block: B:132:0x018f  */
    /* JADX WARN: Code duplicated, block: B:139:0x01aa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:140:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:142:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:144:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:145:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:148:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:149:0x01db  */
    /* JADX WARN: Code duplicated, block: B:152:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:155:0x023a  */
    /* JADX WARN: Code duplicated, block: B:156:0x0257  */
    /* JADX WARN: Code duplicated, block: B:159:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:160:0x02da  */
    /* JADX WARN: Code duplicated, block: B:163:0x03a8  */
    /* JADX WARN: Code duplicated, block: B:167:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:168:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:70:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:78:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:81:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:82:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:87:0x0102  */
    /* JADX WARN: Code duplicated, block: B:89:0x0107  */
    /* JADX WARN: Code duplicated, block: B:92:0x010d  */
    /* JADX WARN: Code duplicated, block: B:93:0x0113  */
    /* JADX WARN: Code duplicated, block: B:95:0x0118  */
    /* JADX WARN: Code duplicated, block: B:97:0x0120  */
    /* JADX WARN: Code duplicated, block: B:98:0x0123  */
    public static final void LazyGrid(Modifier modifier, final LazyGridState state, final LazyGridSlotsProvider slots, PaddingValues contentPadding, boolean reverseLayout, final boolean isVertical, FlingBehavior flingBehavior, final boolean userScrollEnabled, final Arrangement.Vertical verticalArrangement, final Arrangement.Horizontal horizontalArrangement, final Function1<? super LazyGridScope, Unit> function1, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        PaddingValues contentPadding2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean reverseLayout2;
        FlingBehavior flingBehavior2;
        int $dirty;
        Modifier modifier3;
        PaddingValues contentPadding3;
        boolean reverseLayout3;
        Object value$iv$iv;
        boolean reverseLayout4;
        Orientation orientation;
        Composer $composer2;
        Modifier modifier4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer3 = $composer.startRestartGroup(-649686062);
        ComposerKt.sourceInformation($composer3, "C(LazyGrid)P(5,8,7,1,6,4,2,9,10,3)68@3233L15,78@3657L50,80@3733L51,82@3811L24,83@3883L7,84@3915L269,102@4422L278,110@4770L48,113@4981L7,109@4714L376,118@5151L317,98@4277L1324:LazyGrid.kt#7791vq");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        int i7 = i & 1;
        if (i7 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(state) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= ($changed & 512) == 0 ? $composer3.changed(slots) : $composer3.changedInstance(slots) ? 256 : 128;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty2 |= 3072;
            contentPadding2 = contentPadding;
        } else if (($changed & 3072) == 0) {
            contentPadding2 = contentPadding;
            $dirty2 |= $composer3.changed(contentPadding2) ? 2048 : 1024;
        } else {
            contentPadding2 = contentPadding;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changed(reverseLayout) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                i2 = $composer3.changed(isVertical) ? 131072 : 65536;
            }
            if (($changed & 1572864) != 0) {
                $dirty2 |= ((i & 64) == 0 || !$composer3.changed(flingBehavior)) ? 524288 : 1048576;
            }
            if ((i & 128) != 0) {
                $dirty2 |= 12582912;
            } else if ((12582912 & $changed) != 0) {
                if ($composer3.changed(userScrollEnabled)) {
                    i3 = 8388608;
                } else {
                    i3 = 4194304;
                }
                $dirty2 |= i3;
            }
            if ((i & 256) != 0) {
                $dirty2 |= 100663296;
            } else if ((100663296 & $changed) != 0) {
                if ($composer3.changed(verticalArrangement)) {
                    i4 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i4 = 33554432;
                }
                $dirty2 |= i4;
            }
            if ((i & 512) != 0) {
                $dirty2 |= 805306368;
            } else if ((805306368 & $changed) != 0) {
                if ($composer3.changed(horizontalArrangement)) {
                    i5 = KeyHandler.KEYMOD_SHIFT;
                } else {
                    i5 = KeyHandler.KEYMOD_NUM_LOCK;
                }
                $dirty2 |= i5;
            }
            if ((i & 1024) != 0) {
                $dirty1 |= 6;
            } else if (($changed1 & 6) == 0) {
                if ($composer3.changedInstance(function1)) {
                    i6 = 4;
                } else {
                    i6 = 2;
                }
                $dirty1 |= i6;
            }
            if ((306783379 & $dirty2) != 306783378 && ($dirty1 & 3) == 2 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                reverseLayout4 = reverseLayout;
                flingBehavior2 = flingBehavior;
                modifier4 = modifier2;
                contentPadding3 = contentPadding2;
                $composer2 = $composer3;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        modifier3 = modifier2;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        $dirty = $dirty2;
                        modifier3 = modifier2;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 64) != 0) {
                        $dirty2 &= -3670017;
                    }
                    flingBehavior2 = flingBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-649686062, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:77)");
                }
                Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, function1, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                LazyLayoutSemanticState semanticState = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
                value$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                    $composer3.updateRememberedValue(value$iv$iv);
                }
                CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext = CompositionLocalsKt.getLocalGraphicsContext();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localGraphicsContext);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                GraphicsContext graphicsContext = (GraphicsContext) objConsume;
                int $dirty3 = $dirty;
                reverseLayout4 = reverseLayout3;
                Modifier modifier5 = modifier3;
                Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, coroutineScope, graphicsContext, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128));
                if (isVertical) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                Orientation orientation2 = orientation;
                Modifier modifierLazyLayoutSemantics = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier5.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda, semanticState, orientation2, userScrollEnabled, reverseLayout4, $composer3, (($dirty3 >> 9) & 57344) | (($dirty3 << 3) & 458752));
                $composer2 = $composer3;
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyGridBeyondBoundsState = LazyGridBeyondBoundsModifierKt.rememberLazyGridBeyondBoundsState(state, $composer2, ($dirty3 >> 3) & 14);
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo$foundation_release = state.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics, lazyLayoutBeyondBoundsStateRememberLazyGridBeyondBoundsState, beyondBoundsInfo$foundation_release, reverseLayout4, (LayoutDirection) objConsume2, orientation2, userScrollEnabled, $composer2, (MutableVector.$stable << 6) | (($dirty3 >> 3) & 7168) | (($dirty3 >> 3) & 3670016)).then(state.getItemAnimator$foundation_release().getModifier()), state, orientation2, userScrollEnabled, reverseLayout4, flingBehavior2, state.getInternalInteractionSource(), null, $composer2, ($dirty3 & 112) | (($dirty3 >> 12) & 7168) | ($dirty3 & 57344) | (($dirty3 >> 3) & 458752), 64), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier5;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier4;
                final PaddingValues paddingValues = contentPadding3;
                final boolean z = reverseLayout4;
                final FlingBehavior flingBehavior3 = flingBehavior2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                        LazyGridKt.LazyGrid(modifier6, state, slots, paddingValues, z, isVertical, flingBehavior3, userScrollEnabled, verticalArrangement, horizontalArrangement, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                    }
                });
            }
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        if (($changed & 1572864) != 0) {
            $dirty2 |= ((i & 64) == 0 || !$composer3.changed(flingBehavior)) ? 524288 : 1048576;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if ((12582912 & $changed) != 0) {
            if ($composer3.changed(userScrollEnabled)) {
                i3 = 8388608;
            } else {
                i3 = 4194304;
            }
            $dirty2 |= i3;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if ((100663296 & $changed) != 0) {
            if ($composer3.changed(verticalArrangement)) {
                i4 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i4 = 33554432;
            }
            $dirty2 |= i4;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if ((805306368 & $changed) != 0) {
            if ($composer3.changed(horizontalArrangement)) {
                i5 = KeyHandler.KEYMOD_SHIFT;
            } else {
                i5 = KeyHandler.KEYMOD_NUM_LOCK;
            }
            $dirty2 |= i5;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            if ($composer3.changedInstance(function1)) {
                i6 = 4;
            } else {
                i6 = 2;
            }
            $dirty1 |= i6;
        }
        if ((306783379 & $dirty2) != 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    contentPadding2 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = reverseLayout;
                }
                if ((i & 64) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                }
            } else {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    contentPadding2 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = reverseLayout;
                }
                if ((i & 64) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-649686062, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:77)");
            }
            Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda2 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, function1, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
            LazyLayoutSemanticState semanticState2 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
            value$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv);
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv2 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CoroutineScope coroutineScope2 = wrapper$iv2.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext2 = CompositionLocalsKt.getLocalGraphicsContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localGraphicsContext2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            GraphicsContext graphicsContext2 = (GraphicsContext) objConsume3;
            int $dirty4 = $dirty;
            reverseLayout4 = reverseLayout3;
            Modifier modifier7 = modifier3;
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy2 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda2, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, coroutineScope2, graphicsContext2, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128));
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            Orientation orientation3 = orientation;
            Modifier modifierLazyLayoutSemantics2 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier7.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda2, semanticState2, orientation3, userScrollEnabled, reverseLayout4, $composer3, (($dirty4 >> 9) & 57344) | (($dirty4 << 3) & 458752));
            $composer2 = $composer3;
            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyGridBeyondBoundsState2 = LazyGridBeyondBoundsModifierKt.rememberLazyGridBeyondBoundsState(state, $composer2, ($dirty4 >> 3) & 14);
            LazyLayoutBeyondBoundsInfo beyondBoundsInfo$foundation_release2 = state.getBeyondBoundsInfo();
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer2.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda2, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics2, lazyLayoutBeyondBoundsStateRememberLazyGridBeyondBoundsState2, beyondBoundsInfo$foundation_release2, reverseLayout4, (LayoutDirection) objConsume4, orientation3, userScrollEnabled, $composer2, (MutableVector.$stable << 6) | (($dirty4 >> 3) & 7168) | (($dirty4 >> 3) & 3670016)).then(state.getItemAnimator$foundation_release().getModifier()), state, orientation3, userScrollEnabled, reverseLayout4, flingBehavior2, state.getInternalInteractionSource(), null, $composer2, ($dirty4 & 112) | (($dirty4 >> 12) & 7168) | ($dirty4 & 57344) | (($dirty4 >> 3) & 458752), 64), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy2, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier7;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    contentPadding2 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = reverseLayout;
                }
                if ((i & 64) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                }
            } else {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    contentPadding2 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = reverseLayout;
                }
                if ((i & 64) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-649686062, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:77)");
            }
            Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda3 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, function1, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
            LazyLayoutSemanticState semanticState3 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
            value$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv);
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv3 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CoroutineScope coroutineScope3 = wrapper$iv3.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext3 = CompositionLocalsKt.getLocalGraphicsContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer3.consume(localGraphicsContext3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            GraphicsContext graphicsContext3 = (GraphicsContext) objConsume5;
            int $dirty5 = $dirty;
            reverseLayout4 = reverseLayout3;
            Modifier modifier8 = modifier3;
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy3 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda3, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, coroutineScope3, graphicsContext3, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128));
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            Orientation orientation4 = orientation;
            Modifier modifierLazyLayoutSemantics3 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier8.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda3, semanticState3, orientation4, userScrollEnabled, reverseLayout4, $composer3, (($dirty5 >> 9) & 57344) | (($dirty5 << 3) & 458752));
            $composer2 = $composer3;
            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyGridBeyondBoundsState3 = LazyGridBeyondBoundsModifierKt.rememberLazyGridBeyondBoundsState(state, $composer2, ($dirty5 >> 3) & 14);
            LazyLayoutBeyondBoundsInfo beyondBoundsInfo$foundation_release3 = state.getBeyondBoundsInfo();
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer2.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda3, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics3, lazyLayoutBeyondBoundsStateRememberLazyGridBeyondBoundsState3, beyondBoundsInfo$foundation_release3, reverseLayout4, (LayoutDirection) objConsume6, orientation4, userScrollEnabled, $composer2, (MutableVector.$stable << 6) | (($dirty5 >> 3) & 7168) | (($dirty5 >> 3) & 3670016)).then(state.getItemAnimator$foundation_release().getModifier()), state, orientation4, userScrollEnabled, reverseLayout4, flingBehavior2, state.getInternalInteractionSource(), null, $composer2, ($dirty5 & 112) | (($dirty5 >> 12) & 7168) | ($dirty5 & 57344) | (($dirty5 >> 3) & 458752), 64), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy3, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier8;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier9 = modifier4;
            final PaddingValues paddingValues2 = contentPadding3;
            final boolean z2 = reverseLayout4;
            final FlingBehavior flingBehavior4 = flingBehavior2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                    LazyGridKt.LazyGrid(modifier9, state, slots, paddingValues2, z2, isVertical, flingBehavior4, userScrollEnabled, verticalArrangement, horizontalArrangement, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x003a  */
    /* JADX WARN: Code duplicated, block: B:23:0x0054  */
    /* JADX WARN: Code duplicated, block: B:33:0x006f  */
    /* JADX WARN: Code duplicated, block: B:43:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:63:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:73:0x00e3  */
    private static final Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> rememberLazyGridMeasurePolicy(final Function0<? extends LazyGridItemProvider> function0, final LazyGridState state, final LazyGridSlotsProvider slots, final PaddingValues contentPadding, final boolean reverseLayout, final boolean isVertical, final Arrangement.Horizontal horizontalArrangement, final Arrangement.Vertical verticalArrangement, final CoroutineScope coroutineScope, final GraphicsContext graphicsContext, Composer $composer, int $changed) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        ComposerKt.sourceInformationMarkerStart($composer, -1585069765, "C(rememberLazyGridMeasurePolicy)P(5,8,7!1,6,4,3,9)161@6721L9334:LazyGrid.kt#7791vq");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1585069765, $changed, -1, "androidx.compose.foundation.lazy.grid.rememberLazyGridMeasurePolicy (LazyGrid.kt:161)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1026581015, "CC(remember):LazyGrid.kt#9igjgp");
        if ((($changed & 112) ^ 48) > 32 && $composer.changed(state)) {
            z = true;
        } else if (($changed & 48) == 32) {
            z = true;
        } else {
            z = false;
        }
        if ((($changed & 896) ^ 384) > 256 && $composer.changed(slots)) {
            z2 = true;
        } else if (($changed & 384) == 256) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z8 = z | z2;
        if ((($changed & 7168) ^ 3072) > 2048 && $composer.changed(contentPadding)) {
            z3 = true;
        } else if (($changed & 3072) == 2048) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z9 = z8 | z3;
        if (((57344 & $changed) ^ 24576) > 16384 && $composer.changed(reverseLayout)) {
            z4 = true;
        } else if (($changed & 24576) == 16384) {
            z4 = true;
        } else {
            z4 = false;
        }
        boolean z10 = z9 | z4;
        if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072 && $composer.changed(isVertical)) {
            z5 = true;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
            z5 = true;
        } else {
            z5 = false;
        }
        boolean z11 = z10 | z5;
        if (((3670016 & $changed) ^ 1572864) > 1048576 && $composer.changed(horizontalArrangement)) {
            z6 = true;
        } else if (($changed & 1572864) == 1048576) {
            z6 = true;
        } else {
            z6 = false;
        }
        boolean z12 = z11 | z6;
        if (((29360128 & $changed) ^ 12582912) <= 8388608 || !$composer.changed(verticalArrangement)) {
            z7 = ($changed & 12582912) == 8388608;
        }
        boolean invalid$iv = z12 | z7 | $composer.changed(graphicsContext);
        Object value$iv = $composer.rememberedValue();
        if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function2<LazyLayoutMeasureScope, Constraints, LazyGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m1172invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* JADX WARN: Type inference failed for: r0v47, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1] */
                /* JADX WARN: Type inference failed for: r0v48, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1] */
                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyGridMeasureResult m1172invoke0kLqBqw(final LazyLayoutMeasureScope $this$null, final long containerConstraints) throws Throwable {
                    int i;
                    int i2;
                    int i3;
                    float spacing;
                    int iM6969getMaxWidthimpl;
                    final long visualItemOffset;
                    int firstVisibleLineIndex;
                    int firstVisibleLineScrollOffset;
                    ObservableScopeInvalidator.m1208attachToScopeimpl(state.m1180getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                    CheckScrollableContainerConstraintsKt.m617checkScrollableContainerConstraintsK40F9xA(containerConstraints, isVertical ? Orientation.Vertical : Orientation.Horizontal);
                    if (isVertical) {
                        i = $this$null.mo720roundToPx0680j_4(contentPadding.mo985calculateLeftPaddingu2uoSUM($this$null.getLayoutDirection()));
                    } else {
                        i = $this$null.mo720roundToPx0680j_4(PaddingKt.calculateStartPadding(contentPadding, $this$null.getLayoutDirection()));
                    }
                    int startPadding = i;
                    if (isVertical) {
                        i2 = $this$null.mo720roundToPx0680j_4(contentPadding.mo986calculateRightPaddingu2uoSUM($this$null.getLayoutDirection()));
                    } else {
                        i2 = $this$null.mo720roundToPx0680j_4(PaddingKt.calculateEndPadding(contentPadding, $this$null.getLayoutDirection()));
                    }
                    int endPadding = i2;
                    int topPadding = $this$null.mo720roundToPx0680j_4(contentPadding.getTop());
                    int bottomPadding = $this$null.mo720roundToPx0680j_4(contentPadding.getBottom());
                    final int totalVerticalPadding = topPadding + bottomPadding;
                    final int totalHorizontalPadding = startPadding + endPadding;
                    int totalMainAxisPadding = isVertical ? totalVerticalPadding : totalHorizontalPadding;
                    if (isVertical && !reverseLayout) {
                        i3 = topPadding;
                    } else if (isVertical && reverseLayout) {
                        i3 = bottomPadding;
                    } else {
                        i3 = (isVertical || reverseLayout) ? endPadding : startPadding;
                    }
                    final int beforeContentPadding = i3;
                    final int afterContentPadding = totalMainAxisPadding - beforeContentPadding;
                    long contentConstraints = ConstraintsKt.m6988offsetNN6EwU(containerConstraints, -totalHorizontalPadding, -totalVerticalPadding);
                    final LazyGridItemProvider itemProvider = function0.invoke();
                    final LazyGridSpanLayoutProvider spanLayoutProvider = itemProvider.getSpanLayoutProvider();
                    final LazyGridSlots resolvedSlots = slots.mo1161invoke0kLqBqw($this$null, containerConstraints);
                    int slotsPerLine = resolvedSlots.getSizes().length;
                    spanLayoutProvider.setSlotsPerLine(slotsPerLine);
                    if (isVertical) {
                        Arrangement.Vertical vertical = verticalArrangement;
                        if (vertical == null) {
                            throw new IllegalArgumentException("null verticalArrangement when isVertical == true".toString());
                        }
                        spacing = vertical.getSpacing();
                    } else {
                        Arrangement.Horizontal horizontal = horizontalArrangement;
                        if (horizontal == null) {
                            throw new IllegalArgumentException("null horizontalArrangement when isVertical == false".toString());
                        }
                        spacing = horizontal.getSpacing();
                    }
                    float spaceBetweenLinesDp = spacing;
                    final int spaceBetweenLines = $this$null.mo720roundToPx0680j_4(spaceBetweenLinesDp);
                    final int itemsCount = itemProvider.getItemCount();
                    if (isVertical) {
                        iM6969getMaxWidthimpl = Constraints.m6968getMaxHeightimpl(containerConstraints) - totalVerticalPadding;
                    } else {
                        iM6969getMaxWidthimpl = Constraints.m6969getMaxWidthimpl(containerConstraints) - totalHorizontalPadding;
                    }
                    int mainAxisAvailableSize = iM6969getMaxWidthimpl;
                    if (!reverseLayout || mainAxisAvailableSize > 0) {
                        visualItemOffset = IntOffsetKt.IntOffset(startPadding, topPadding);
                    } else {
                        visualItemOffset = IntOffsetKt.IntOffset(isVertical ? startPadding : startPadding + mainAxisAvailableSize, isVertical ? topPadding + mainAxisAvailableSize : topPadding);
                    }
                    final LazyGridState lazyGridState = state;
                    final boolean z13 = isVertical;
                    final boolean z14 = reverseLayout;
                    final ?? r0 = new LazyGridMeasuredItemProvider(itemProvider, $this$null, spaceBetweenLines, lazyGridState, z13, z14, beforeContentPadding, afterContentPadding, visualItemOffset) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1
                        final /* synthetic */ int $afterContentPadding;
                        final /* synthetic */ int $beforeContentPadding;
                        final /* synthetic */ boolean $isVertical;
                        final /* synthetic */ boolean $reverseLayout;
                        final /* synthetic */ LazyGridState $state;
                        final /* synthetic */ LazyLayoutMeasureScope $this_null;
                        final /* synthetic */ long $visualItemOffset;

                        {
                            this.$this_null = $this$null;
                            this.$state = lazyGridState;
                            this.$isVertical = z13;
                            this.$reverseLayout = z14;
                            this.$beforeContentPadding = beforeContentPadding;
                            this.$afterContentPadding = afterContentPadding;
                            this.$visualItemOffset = visualItemOffset;
                        }

                        @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider
                        /* JADX INFO: renamed from: createItem-O3s9Psw, reason: not valid java name */
                        public LazyGridMeasuredItem mo1173createItemO3s9Psw(int index, Object key, Object contentType, int crossAxisSize, int mainAxisSpacing, List<? extends Placeable> placeables, long constraints, int lane, int span) {
                            return new LazyGridMeasuredItem(index, key, this.$isVertical, crossAxisSize, mainAxisSpacing, this.$reverseLayout, this.$this_null.getLayoutDirection(), this.$beforeContentPadding, this.$afterContentPadding, placeables, this.$visualItemOffset, contentType, this.$state.getItemAnimator$foundation_release(), constraints, lane, span, null);
                        }
                    };
                    final boolean z15 = isVertical;
                    final ?? r1 = new LazyGridMeasuredLineProvider(z15, resolvedSlots, itemsCount, spaceBetweenLines, r0, spanLayoutProvider) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1
                        final /* synthetic */ boolean $isVertical;
                        final /* synthetic */ LazyGridSlots $resolvedSlots;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(z15, resolvedSlots, itemsCount, spaceBetweenLines, r0, spanLayoutProvider);
                            this.$isVertical = z15;
                            this.$resolvedSlots = resolvedSlots;
                        }

                        @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider
                        public LazyGridMeasuredLine createLine(int index, LazyGridMeasuredItem[] items, List<GridItemSpan> spans, int mainAxisSpacing) {
                            return new LazyGridMeasuredLine(index, items, this.$resolvedSlots, spans, this.$isVertical, mainAxisSpacing);
                        }
                    };
                    Function1<Integer, ArrayList<Pair<? extends Integer, ? extends Constraints>>> function1 = new Function1<Integer, ArrayList<Pair<? extends Integer, ? extends Constraints>>>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$prefetchInfoRetriever$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ ArrayList<Pair<? extends Integer, ? extends Constraints>> invoke(Integer num) {
                            return invoke(num.intValue());
                        }

                        public final ArrayList<Pair<Integer, Constraints>> invoke(int line) {
                            LazyGridSpanLayoutProvider.LineConfiguration lineConfiguration = spanLayoutProvider.getLineConfiguration(line);
                            int index = lineConfiguration.getFirstItemIndex();
                            int slot = 0;
                            ArrayList<Pair<Integer, Constraints>> arrayList = new ArrayList<>(lineConfiguration.getSpans().size());
                            List<GridItemSpan> spans = lineConfiguration.getSpans();
                            LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 = r1;
                            int index$iv = 0;
                            int size = spans.size();
                            while (index$iv < size) {
                                Object item$iv = spans.get(index$iv);
                                long it = ((GridItemSpan) item$iv).getPackedValue();
                                int span = GridItemSpan.m1157getCurrentLineSpanimpl(it);
                                arrayList.add(TuplesKt.to(Integer.valueOf(index), Constraints.m6957boximpl(lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1.m1179childConstraintsJhjzzOo$foundation_release(slot, span))));
                                index++;
                                slot += span;
                                index$iv++;
                                lineConfiguration = lineConfiguration;
                            }
                            return arrayList;
                        }
                    };
                    Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                    LazyGridState lazyGridState2 = state;
                    Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                    Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                    Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                    try {
                        int firstVisibleLineIndex2 = lazyGridState2.getFirstVisibleItemIndex();
                        int index = lazyGridState2.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(itemProvider, firstVisibleLineIndex2);
                        if (index < itemsCount || itemsCount <= 0) {
                            try {
                                firstVisibleLineIndex = spanLayoutProvider.getLineIndexOfItem(index);
                                try {
                                    firstVisibleLineScrollOffset = lazyGridState2.getFirstVisibleItemScrollOffset();
                                    try {
                                        Unit unit = Unit.INSTANCE;
                                        this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                        LazyGridMeasureResult measureResult = LazyGridMeasureKt.m1175measureLazyGridOZKpZRA(itemsCount, (LazyGridMeasuredLineProvider) r1, (LazyGridMeasuredItemProvider) r0, mainAxisAvailableSize, beforeContentPadding, afterContentPadding, spaceBetweenLines, firstVisibleLineIndex, firstVisibleLineScrollOffset, state.getScrollToBeConsumed(), contentConstraints, isVertical, verticalArrangement, horizontalArrangement, reverseLayout, $this$null, state.getItemAnimator$foundation_release(), slotsPerLine, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, state.getPinnedItems(), state.getBeyondBoundsInfo()), coroutineScope, state.m1181getPlacementScopeInvalidatorzYiylxw$foundation_release(), graphicsContext, function1, new Function3<Integer, Integer, Function1<? super Placeable.PlacementScope, ? extends Unit>, MeasureResult>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measureResult$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ MeasureResult invoke(Integer num, Integer num2, Function1<? super Placeable.PlacementScope, ? extends Unit> function2) {
                                                return invoke(num.intValue(), num2.intValue(), (Function1<? super Placeable.PlacementScope, Unit>) function2);
                                            }

                                            public final MeasureResult invoke(int width, int height, Function1<? super Placeable.PlacementScope, Unit> function2) {
                                                return $this$null.layout(ConstraintsKt.m6986constrainWidthK40F9xA(containerConstraints, totalHorizontalPadding + width), ConstraintsKt.m6985constrainHeightK40F9xA(containerConstraints, totalVerticalPadding + height), MapsKt.emptyMap(), function2);
                                            }
                                        });
                                        LazyGridState.applyMeasureResult$foundation_release$default(state, measureResult, false, 2, null);
                                        return measureResult;
                                    } catch (Throwable th) {
                                        th = th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } else {
                            try {
                                int firstVisibleLineIndex3 = spanLayoutProvider.getLineIndexOfItem(itemsCount - 1);
                                firstVisibleLineIndex = firstVisibleLineIndex3;
                                firstVisibleLineScrollOffset = 0;
                                Unit unit2 = Unit.INSTANCE;
                                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                LazyGridMeasureResult measureResult2 = LazyGridMeasureKt.m1175measureLazyGridOZKpZRA(itemsCount, (LazyGridMeasuredLineProvider) r1, (LazyGridMeasuredItemProvider) r0, mainAxisAvailableSize, beforeContentPadding, afterContentPadding, spaceBetweenLines, firstVisibleLineIndex, firstVisibleLineScrollOffset, state.getScrollToBeConsumed(), contentConstraints, isVertical, verticalArrangement, horizontalArrangement, reverseLayout, $this$null, state.getItemAnimator$foundation_release(), slotsPerLine, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, state.getPinnedItems(), state.getBeyondBoundsInfo()), coroutineScope, state.m1181getPlacementScopeInvalidatorzYiylxw$foundation_release(), graphicsContext, function1, new Function3<Integer, Integer, Function1<? super Placeable.PlacementScope, ? extends Unit>, MeasureResult>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measureResult$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(Integer num, Integer num2, Function1<? super Placeable.PlacementScope, ? extends Unit> function2) {
                                        return invoke(num.intValue(), num2.intValue(), (Function1<? super Placeable.PlacementScope, Unit>) function2);
                                    }

                                    public final MeasureResult invoke(int width, int height, Function1<? super Placeable.PlacementScope, Unit> function2) {
                                        return $this$null.layout(ConstraintsKt.m6986constrainWidthK40F9xA(containerConstraints, totalHorizontalPadding + width), ConstraintsKt.m6985constrainHeightK40F9xA(containerConstraints, totalVerticalPadding + height), MapsKt.emptyMap(), function2);
                                    }
                                });
                                LazyGridState.applyMeasureResult$foundation_release$default(state, measureResult2, false, 2, null);
                                return measureResult2;
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                    throw th;
                }
            };
            $composer.updateRememberedValue(value$iv);
        }
        Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2 = (Function2) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return function2;
    }
}
