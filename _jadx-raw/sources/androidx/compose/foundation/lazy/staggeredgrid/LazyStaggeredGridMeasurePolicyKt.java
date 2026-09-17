package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGridMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u007f\u0010\u0000\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\u0002\b\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a)\u0010\u001c\u001a\u00020\u0012*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001f\u001a)\u0010 \u001a\u00020\u0012*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001f\u001a!\u0010!\u001a\u00020\u0012*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\"\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"rememberStaggeredGridMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "Lkotlin/ExtensionFunctionType;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacing", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "slots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "rememberStaggeredGridMeasurePolicy-qKj4JfE", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;FFLkotlinx/coroutines/CoroutineScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "afterPadding", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/ui/unit/LayoutDirection;)F", "beforePadding", "startPadding", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/unit/LayoutDirection;)F", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridMeasurePolicyKt {

    /* JADX INFO: compiled from: LazyStaggeredGridMeasurePolicy.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0040  */
    /* JADX WARN: Code duplicated, block: B:23:0x005b  */
    /* JADX WARN: Code duplicated, block: B:33:0x0076  */
    /* JADX WARN: Code duplicated, block: B:43:0x0093  */
    /* JADX WARN: Code duplicated, block: B:53:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:73:0x00ea  */
    /* JADX INFO: renamed from: rememberStaggeredGridMeasurePolicy-qKj4JfE, reason: not valid java name */
    public static final Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> m1246rememberStaggeredGridMeasurePolicyqKj4JfE(final LazyStaggeredGridState state, final Function0<? extends LazyStaggeredGridItemProvider> function0, final PaddingValues contentPadding, final boolean reverseLayout, final Orientation orientation, final float mainAxisSpacing, float crossAxisSpacing, final CoroutineScope coroutineScope, final LazyGridStaggeredGridSlotsProvider slots, final GraphicsContext graphicsContext, Composer $composer, int $changed) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        ComposerKt.sourceInformationMarkerStart($composer, 1630140849, "C(rememberStaggeredGridMeasurePolicy)P(9,4!1,7,6,5:c#ui.unit.Dp,2:c#ui.unit.Dp!1,8)50@2173L2923:LazyStaggeredGridMeasurePolicy.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1630140849, $changed, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberStaggeredGridMeasurePolicy (LazyStaggeredGridMeasurePolicy.kt:50)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1650675918, "CC(remember):LazyStaggeredGridMeasurePolicy.kt#9igjgp");
        boolean zChanged = $composer.changed(state);
        if ((($changed & 112) ^ 48) > 32 && $composer.changed(function0)) {
            z = true;
        } else if (($changed & 48) == 32) {
            z = true;
        } else {
            z = false;
        }
        boolean z8 = zChanged | z;
        if ((($changed & 896) ^ 384) > 256 && $composer.changed(contentPadding)) {
            z2 = true;
        } else if (($changed & 384) == 256) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z9 = z8 | z2;
        if ((($changed & 7168) ^ 3072) > 2048 && $composer.changed(reverseLayout)) {
            z3 = true;
        } else if (($changed & 3072) == 2048) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z10 = z9 | z3;
        if (((57344 & $changed) ^ 24576) > 16384 && $composer.changed(orientation)) {
            z4 = true;
        } else if (($changed & 24576) == 16384) {
            z4 = true;
        } else {
            z4 = false;
        }
        boolean z11 = z10 | z4;
        if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072 && $composer.changed(mainAxisSpacing)) {
            z5 = true;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
            z5 = true;
        } else {
            z5 = false;
        }
        boolean z12 = z11 | z5;
        if (((3670016 & $changed) ^ 1572864) > 1048576 && $composer.changed(crossAxisSpacing)) {
            z6 = true;
        } else if (($changed & 1572864) == 1048576) {
            z6 = true;
        } else {
            z6 = false;
        }
        boolean z13 = z12 | z6;
        if (((234881024 & $changed) ^ 100663296) <= 67108864 || !$composer.changed(slots)) {
            z7 = ($changed & 100663296) == 67108864;
        }
        boolean invalid$iv = z13 | z7 | $composer.changed(graphicsContext);
        Object value$iv = $composer.rememberedValue();
        if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyStaggeredGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m1247invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyStaggeredGridMeasureResult m1247invoke0kLqBqw(LazyLayoutMeasureScope $this$null, long constraints) {
                    ObservableScopeInvalidator.m1208attachToScopeimpl(state.m1252getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                    CheckScrollableContainerConstraintsKt.m617checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                    LazyStaggeredGridSlots resolvedSlots = slots.mo1228invoke0kLqBqw($this$null, constraints);
                    boolean isVertical = orientation == Orientation.Vertical;
                    LazyStaggeredGridItemProvider itemProvider = function0.invoke();
                    int beforeContentPadding = $this$null.mo720roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(contentPadding, orientation, reverseLayout, $this$null.getLayoutDirection()));
                    int afterContentPadding = $this$null.mo720roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(contentPadding, orientation, reverseLayout, $this$null.getLayoutDirection()));
                    int startContentPadding = $this$null.mo720roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(contentPadding, orientation, $this$null.getLayoutDirection()));
                    int maxMainAxisSize = isVertical ? Constraints.m6968getMaxHeightimpl(constraints) : Constraints.m6969getMaxWidthimpl(constraints);
                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                    long contentOffset = isVertical ? IntOffsetKt.IntOffset(startContentPadding, beforeContentPadding) : IntOffsetKt.IntOffset(beforeContentPadding, startContentPadding);
                    PaddingValues $this$invoke_0kLqBqw_u24lambda_u240 = contentPadding;
                    float arg0$iv = PaddingKt.calculateStartPadding($this$invoke_0kLqBqw_u24lambda_u240, $this$null.getLayoutDirection());
                    float other$iv = PaddingKt.calculateEndPadding($this$invoke_0kLqBqw_u24lambda_u240, $this$null.getLayoutDirection());
                    int horizontalPadding = $this$null.mo720roundToPx0680j_4(Dp.m7016constructorimpl(arg0$iv + other$iv));
                    PaddingValues $this$invoke_0kLqBqw_u24lambda_u241 = contentPadding;
                    float arg0$iv2 = $this$invoke_0kLqBqw_u24lambda_u241.getTop();
                    float other$iv2 = $this$invoke_0kLqBqw_u24lambda_u241.getBottom();
                    int verticalPadding = $this$null.mo720roundToPx0680j_4(Dp.m7016constructorimpl(arg0$iv2 + other$iv2));
                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1245measureStaggeredGridXtK8cYQ($this$null, state, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, state.getPinnedItems(), state.getBeyondBoundsInfo()), itemProvider, resolvedSlots, Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : ConstraintsKt.m6986constrainWidthK40F9xA(constraints, horizontalPadding), (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : ConstraintsKt.m6985constrainHeightK40F9xA(constraints, verticalPadding), (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0), isVertical, reverseLayout, contentOffset, mainAxisAvailableSize, $this$null.mo720roundToPx0680j_4(mainAxisSpacing), beforeContentPadding, afterContentPadding, coroutineScope, graphicsContext);
                    LazyStaggeredGridState.applyMeasureResult$foundation_release$default(state, measureResult, false, 2, null);
                    return measureResult;
                }
            };
            $composer.updateRememberedValue(value$iv);
        }
        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2 = (Function2) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return function2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float startPadding(PaddingValues $this$startPadding, Orientation orientation, LayoutDirection layoutDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                return PaddingKt.calculateStartPadding($this$startPadding, layoutDirection);
            case 2:
                return $this$startPadding.getTop();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float beforePadding(PaddingValues $this$beforePadding, Orientation orientation, boolean reverseLayout, LayoutDirection layoutDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                return reverseLayout ? $this$beforePadding.getBottom() : $this$beforePadding.getTop();
            case 2:
                if (reverseLayout) {
                    return PaddingKt.calculateEndPadding($this$beforePadding, layoutDirection);
                }
                return PaddingKt.calculateStartPadding($this$beforePadding, layoutDirection);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float afterPadding(PaddingValues $this$afterPadding, Orientation orientation, boolean reverseLayout, LayoutDirection layoutDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                return reverseLayout ? $this$afterPadding.getTop() : $this$afterPadding.getBottom();
            case 2:
                if (reverseLayout) {
                    return PaddingKt.calculateStartPadding($this$afterPadding, layoutDirection);
                }
                return PaddingKt.calculateEndPadding($this$afterPadding, layoutDirection);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
