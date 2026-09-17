package androidx.compose.foundation.lazy;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.ScrollingContainerKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
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
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyList.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0098\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0002\u0010\u001c\u001a\u009a\u0001\u0010\u001d\u001a\u0019\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001e¢\u0006\u0002\b\u001b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\tH\u0003¢\u0006\u0002\u0010*¨\u0006+"}, d2 = {"LazyList", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "beyondBoundsItemCount", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyListScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "rememberLazyListMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/LazyListItemProvider;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "stickyHeadersEnabled", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZZILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;ZLandroidx/compose/runtime/Composer;II)Lkotlin/jvm/functions/Function2;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListKt {
    public static final void LazyList(final Modifier modifier, final LazyListState state, final PaddingValues contentPadding, final boolean reverseLayout, final boolean isVertical, final FlingBehavior flingBehavior, final boolean userScrollEnabled, int beyondBoundsItemCount, Alignment.Horizontal horizontalAlignment, Arrangement.Vertical verticalArrangement, Alignment.Vertical verticalAlignment, Arrangement.Horizontal horizontalArrangement, final Function1<? super LazyListScope, Unit> function1, Composer $composer, final int $changed, final int $changed1, final int i) {
        Arrangement.Vertical verticalArrangement2;
        Alignment.Vertical verticalAlignment2;
        Arrangement.Horizontal horizontalArrangement2;
        int $dirty;
        Composer $composer2;
        Alignment.Horizontal horizontalAlignment2;
        int beyondBoundsItemCount2;
        Composer $composer3 = $composer.startRestartGroup(620764179);
        ComposerKt.sourceInformation($composer3, "C(LazyList)P(7,9,2,8,6,3,10!1,4,12,11,5)81@3859L50,83@3935L48,84@4009L24,85@4081L7,86@4150L7,88@4183L395,109@4816L278,117@5164L153,123@5480L7,116@5108L481,128@5650L317,105@4671L1429:LazyList.kt#428nma");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(state) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(reverseLayout) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changed(isVertical) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty2 |= $composer3.changed(flingBehavior) ? 131072 : 65536;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changed(userScrollEnabled) ? 1048576 : 524288;
        }
        int i2 = i & 128;
        if (i2 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(beyondBoundsItemCount) ? 8388608 : 4194304;
        }
        int i3 = i & 256;
        if (i3 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(horizontalAlignment) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i4 = i & 512;
        if (i4 != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changed(verticalArrangement) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i5 = i & 1024;
        if (i5 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(verticalAlignment) ? 4 : 2;
        }
        int i6 = i & 2048;
        if (i6 != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(horizontalArrangement) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        int $dirty3 = $dirty1;
        if ((306783379 & $dirty2) == 306783378 && ($dirty3 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            beyondBoundsItemCount2 = beyondBoundsItemCount;
            horizontalAlignment2 = horizontalAlignment;
            verticalArrangement2 = verticalArrangement;
            verticalAlignment2 = verticalAlignment;
            horizontalArrangement2 = horizontalArrangement;
            $dirty = $dirty2;
            $composer2 = $composer3;
        } else {
            int beyondBoundsItemCount3 = i2 != 0 ? 0 : beyondBoundsItemCount;
            Alignment.Horizontal horizontalAlignment3 = i3 != 0 ? null : horizontalAlignment;
            verticalArrangement2 = i4 != 0 ? null : verticalArrangement;
            verticalAlignment2 = i5 != 0 ? null : verticalAlignment;
            horizontalArrangement2 = i6 != 0 ? null : horizontalArrangement;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(620764179, $dirty2, $dirty3, "androidx.compose.foundation.lazy.LazyList (LazyList.kt:80)");
            }
            Function0<LazyListItemProvider> function0RememberLazyListItemProviderLambda = LazyListItemProviderKt.rememberLazyListItemProviderLambda(state, function1, $composer3, (($dirty2 >> 3) & 14) | (($dirty3 >> 3) & 112));
            LazyLayoutSemanticState semanticState = LazyListSemanticsKt.rememberLazyListSemanticState(state, isVertical, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 9) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
            Object value$iv$iv = $composer3.rememberedValue();
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
            CompositionLocal<Boolean> localScrollCaptureInProgress = CompositionLocalsKt.getLocalScrollCaptureInProgress();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localScrollCaptureInProgress);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean stickyHeadersEnabled = !((Boolean) objConsume2).booleanValue();
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyListMeasurePolicy = rememberLazyListMeasurePolicy(function0RememberLazyListItemProviderLambda, state, contentPadding, reverseLayout, isVertical, beyondBoundsItemCount3, horizontalAlignment3, verticalAlignment2, horizontalArrangement2, verticalArrangement2, coroutineScope, graphicsContext, stickyHeadersEnabled, $composer3, ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | ($dirty2 & 57344) | (($dirty2 >> 6) & 458752) | (($dirty2 >> 6) & 3670016) | (($dirty3 << 21) & 29360128) | (($dirty3 << 21) & 234881024) | (1879048192 & $dirty2), 0);
            Orientation orientation = isVertical ? Orientation.Vertical : Orientation.Horizontal;
            $dirty = $dirty2;
            Alignment.Horizontal horizontalAlignment4 = horizontalAlignment3;
            $composer2 = $composer3;
            Modifier modifierLazyLayoutSemantics = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyListItemProviderLambda, semanticState, orientation, userScrollEnabled, reverseLayout, $composer3, (($dirty2 >> 6) & 57344) | (($dirty2 << 6) & 458752));
            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberLazyListBeyondBoundsState = LazyListBeyondBoundsModifierKt.rememberLazyListBeyondBoundsState(state, beyondBoundsItemCount3, $composer2, (($dirty >> 3) & 14) | (($dirty >> 18) & 112));
            LazyLayoutBeyondBoundsInfo beyondBoundsInfo = state.getBeyondBoundsInfo();
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyLayoutKt.LazyLayout(function0RememberLazyListItemProviderLambda, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierLazyLayoutSemantics, lazyLayoutBeyondBoundsStateRememberLazyListBeyondBoundsState, beyondBoundsInfo, reverseLayout, (LayoutDirection) objConsume3, orientation, userScrollEnabled, $composer2, (MutableVector.$stable << 6) | ($dirty & 7168) | (3670016 & $dirty)).then(state.getItemAnimator$foundation_release().getModifier()), state, orientation, userScrollEnabled, reverseLayout, flingBehavior, state.getInternalInteractionSource(), null, $composer2, ($dirty & 112) | (($dirty >> 9) & 7168) | (($dirty << 3) & 57344) | ($dirty & 458752), 64), state.getPrefetchState(), function2RememberLazyListMeasurePolicy, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            horizontalAlignment2 = horizontalAlignment4;
            beyondBoundsItemCount2 = beyondBoundsItemCount3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i7 = beyondBoundsItemCount2;
            final Alignment.Horizontal horizontal = horizontalAlignment2;
            final Arrangement.Vertical vertical = verticalArrangement2;
            final Alignment.Vertical vertical2 = verticalAlignment2;
            final Arrangement.Horizontal horizontal2 = horizontalArrangement2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListKt.LazyList.1
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

                public final void invoke(Composer composer, int i8) {
                    LazyListKt.LazyList(modifier, state, contentPadding, reverseLayout, isVertical, flingBehavior, userScrollEnabled, i7, horizontal, vertical, vertical2, horizontal2, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0039  */
    /* JADX WARN: Code duplicated, block: B:23:0x0053  */
    /* JADX WARN: Code duplicated, block: B:33:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x008b  */
    /* JADX WARN: Code duplicated, block: B:53:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:63:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:73:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:83:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:93:0x011d  */
    private static final Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> rememberLazyListMeasurePolicy(final Function0<? extends LazyListItemProvider> function0, final LazyListState state, final PaddingValues contentPadding, final boolean reverseLayout, final boolean isVertical, final int beyondBoundsItemCount, final Alignment.Horizontal horizontalAlignment, final Alignment.Vertical verticalAlignment, final Arrangement.Horizontal horizontalArrangement, final Arrangement.Vertical verticalArrangement, final CoroutineScope coroutineScope, final GraphicsContext graphicsContext, final boolean stickyHeadersEnabled, Composer $composer, int $changed, int $changed1) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        ComposerKt.sourceInformationMarkerStart($composer, 1972347046, "C(rememberLazyListMeasurePolicy)P(7,9,1,8,6!1,4,11,5,12)170@7305L8413:LazyList.kt#428nma");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1972347046, $changed, $changed1, "androidx.compose.foundation.lazy.rememberLazyListMeasurePolicy (LazyList.kt:170)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 581121742, "CC(remember):LazyList.kt#9igjgp");
        if ((($changed & 112) ^ 48) > 32 && $composer.changed(state)) {
            z = true;
        } else if (($changed & 48) == 32) {
            z = true;
        } else {
            z = false;
        }
        if ((($changed & 896) ^ 384) > 256 && $composer.changed(contentPadding)) {
            z2 = true;
        } else if (($changed & 384) == 256) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z10 = z | z2;
        if ((($changed & 7168) ^ 3072) > 2048 && $composer.changed(reverseLayout)) {
            z3 = true;
        } else if (($changed & 3072) == 2048) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z11 = z10 | z3;
        if (((57344 & $changed) ^ 24576) > 16384 && $composer.changed(isVertical)) {
            z4 = true;
        } else if (($changed & 24576) == 16384) {
            z4 = true;
        } else {
            z4 = false;
        }
        boolean z12 = z11 | z4;
        if (((3670016 & $changed) ^ 1572864) > 1048576 && $composer.changed(horizontalAlignment)) {
            z5 = true;
        } else if (($changed & 1572864) == 1048576) {
            z5 = true;
        } else {
            z5 = false;
        }
        boolean z13 = z12 | z5;
        if (((29360128 & $changed) ^ 12582912) > 8388608 && $composer.changed(verticalAlignment)) {
            z6 = true;
        } else if (($changed & 12582912) == 8388608) {
            z6 = true;
        } else {
            z6 = false;
        }
        boolean z14 = z13 | z6;
        if (((234881024 & $changed) ^ 100663296) > 67108864 && $composer.changed(horizontalArrangement)) {
            z7 = true;
        } else if ((100663296 & $changed) == 67108864) {
            z7 = true;
        } else {
            z7 = false;
        }
        boolean z15 = z14 | z7;
        if (((1879048192 & $changed) ^ 805306368) > 536870912 && $composer.changed(verticalArrangement)) {
            z8 = true;
        } else if ((805306368 & $changed) == 536870912) {
            z8 = true;
        } else {
            z8 = false;
        }
        boolean zChanged = z15 | z8 | $composer.changed(graphicsContext);
        if ((($changed1 & 896) ^ 384) > 256 && $composer.changed(stickyHeadersEnabled)) {
            z9 = true;
        } else if (($changed1 & 384) == 256) {
            z9 = true;
        } else {
            z9 = false;
        }
        boolean invalid$iv = zChanged | z9;
        Object value$iv = $composer.rememberedValue();
        if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function2<LazyLayoutMeasureScope, Constraints, LazyListMeasureResult>() { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyListMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m1136invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyListMeasureResult m1136invoke0kLqBqw(final LazyLayoutMeasureScope $this$null, final long containerConstraints) throws Throwable {
                    int i;
                    int i2;
                    int i3;
                    float spacing;
                    int iM6969getMaxWidthimpl;
                    final long visualItemOffset;
                    float scrollToBeConsumed;
                    List<Integer> listEmptyList;
                    ObservableScopeInvalidator.m1208attachToScopeimpl(state.m1151getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                    boolean hasLookaheadPassOccurred = state.getHasLookaheadPassOccurred() || $this$null.isLookingAhead();
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
                    final long contentConstraints = ConstraintsKt.m6988offsetNN6EwU(containerConstraints, -totalHorizontalPadding, -totalVerticalPadding);
                    final LazyListItemProvider itemProvider = function0.invoke();
                    itemProvider.getItemScope().setMaxSize(Constraints.m6969getMaxWidthimpl(contentConstraints), Constraints.m6968getMaxHeightimpl(contentConstraints));
                    if (isVertical) {
                        Arrangement.Vertical vertical = verticalArrangement;
                        if (vertical == null) {
                            throw new IllegalArgumentException("null verticalArrangement when isVertical == true".toString());
                        }
                        spacing = vertical.getSpacing();
                    } else {
                        Arrangement.Horizontal horizontal = horizontalArrangement;
                        if (horizontal == null) {
                            throw new IllegalArgumentException("null horizontalAlignment when isVertical == false".toString());
                        }
                        spacing = horizontal.getSpacing();
                    }
                    float spaceBetweenItemsDp = spacing;
                    final int spaceBetweenItems = $this$null.mo720roundToPx0680j_4(spaceBetweenItemsDp);
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
                    final boolean z16 = isVertical;
                    final Alignment.Horizontal horizontal2 = horizontalAlignment;
                    final Alignment.Vertical vertical2 = verticalAlignment;
                    final boolean z17 = reverseLayout;
                    final LazyListState lazyListState = state;
                    LazyListMeasuredItemProvider lazyListMeasuredItemProvider = new LazyListMeasuredItemProvider(contentConstraints, z16, itemProvider, $this$null, itemsCount, spaceBetweenItems, horizontal2, vertical2, z17, beforeContentPadding, afterContentPadding, visualItemOffset, lazyListState) { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1
                        final /* synthetic */ int $afterContentPadding;
                        final /* synthetic */ int $beforeContentPadding;
                        final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
                        final /* synthetic */ boolean $isVertical;
                        final /* synthetic */ int $itemsCount;
                        final /* synthetic */ boolean $reverseLayout;
                        final /* synthetic */ int $spaceBetweenItems;
                        final /* synthetic */ LazyListState $state;
                        final /* synthetic */ LazyLayoutMeasureScope $this_null;
                        final /* synthetic */ Alignment.Vertical $verticalAlignment;
                        final /* synthetic */ long $visualItemOffset;

                        {
                            this.$isVertical = z16;
                            this.$this_null = $this$null;
                            this.$itemsCount = itemsCount;
                            this.$spaceBetweenItems = spaceBetweenItems;
                            this.$horizontalAlignment = horizontal2;
                            this.$verticalAlignment = vertical2;
                            this.$reverseLayout = z17;
                            this.$beforeContentPadding = beforeContentPadding;
                            this.$afterContentPadding = afterContentPadding;
                            this.$visualItemOffset = visualItemOffset;
                            this.$state = lazyListState;
                        }

                        @Override // androidx.compose.foundation.lazy.LazyListMeasuredItemProvider
                        /* JADX INFO: renamed from: createItem-X9ElhV4, reason: not valid java name */
                        public LazyListMeasuredItem mo1137createItemX9ElhV4(int index, Object key, Object contentType, List<? extends Placeable> placeables, long constraints) {
                            int spacing2 = index == this.$itemsCount + (-1) ? 0 : this.$spaceBetweenItems;
                            return new LazyListMeasuredItem(index, placeables, this.$isVertical, this.$horizontalAlignment, this.$verticalAlignment, this.$this_null.getLayoutDirection(), this.$reverseLayout, this.$beforeContentPadding, this.$afterContentPadding, spacing2, this.$visualItemOffset, key, contentType, this.$state.getItemAnimator$foundation_release(), constraints, null);
                        }
                    };
                    Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                    LazyListState lazyListState2 = state;
                    Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                    Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                    Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                    try {
                        try {
                            int firstVisibleItemIndex = lazyListState2.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(itemProvider, lazyListState2.getFirstVisibleItemIndex());
                            try {
                                int firstVisibleScrollOffset = lazyListState2.getFirstVisibleItemScrollOffset();
                                try {
                                    Unit unit = Unit.INSTANCE;
                                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, state.getPinnedItems(), state.getBeyondBoundsInfo());
                                    if ($this$null.isLookingAhead() || !hasLookaheadPassOccurred) {
                                        scrollToBeConsumed = state.getScrollToBeConsumed();
                                    } else {
                                        scrollToBeConsumed = state.getScrollDeltaBetweenPasses$foundation_release();
                                    }
                                    if (stickyHeadersEnabled) {
                                        listEmptyList = itemProvider.getHeaderIndexes();
                                    } else {
                                        listEmptyList = CollectionsKt.emptyList();
                                    }
                                    LazyListMeasureResult measureResult = LazyListMeasureKt.m1141measureLazyListx0Ok8Vo(itemsCount, lazyListMeasuredItemProvider, mainAxisAvailableSize, beforeContentPadding, afterContentPadding, spaceBetweenItems, firstVisibleItemIndex, firstVisibleScrollOffset, scrollToBeConsumed, contentConstraints, isVertical, listEmptyList, verticalArrangement, horizontalArrangement, reverseLayout, $this$null, state.getItemAnimator$foundation_release(), beyondBoundsItemCount, listCalculateLazyLayoutPinnedIndices, hasLookaheadPassOccurred, $this$null.isLookingAhead(), state.getPostLookaheadLayoutInfo(), coroutineScope, state.m1152getPlacementScopeInvalidatorzYiylxw$foundation_release(), graphicsContext, new Function3<Integer, Integer, Function1<? super Placeable.PlacementScope, ? extends Unit>, MeasureResult>() { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1$measureResult$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ MeasureResult invoke(Integer num, Integer num2, Function1<? super Placeable.PlacementScope, ? extends Unit> function1) {
                                            return invoke(num.intValue(), num2.intValue(), (Function1<? super Placeable.PlacementScope, Unit>) function1);
                                        }

                                        public final MeasureResult invoke(int width, int height, Function1<? super Placeable.PlacementScope, Unit> function1) {
                                            return $this$null.layout(ConstraintsKt.m6986constrainWidthK40F9xA(containerConstraints, totalHorizontalPadding + width), ConstraintsKt.m6985constrainHeightK40F9xA(containerConstraints, totalVerticalPadding + height), MapsKt.emptyMap(), function1);
                                        }
                                    });
                                    LazyListState.applyMeasureResult$foundation_release$default(state, measureResult, $this$null.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                } catch (Throwable th) {
                                    th = th;
                                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
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
