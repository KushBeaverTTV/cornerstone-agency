package androidx.compose.foundation.pager;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PagerMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a¡\u0001\u0010\u0000\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\u0002\b\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"rememberPagerMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "beyondViewportPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "pageCount", "rememberPagerMeasurePolicy-8u0NR3k", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;IFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Lkotlin/jvm/functions/Function2;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PagerMeasurePolicyKt {
    /* JADX WARN: Code duplicated, block: B:103:0x0130  */
    /* JADX WARN: Code duplicated, block: B:113:0x0150  */
    /* JADX WARN: Code duplicated, block: B:13:0x0039  */
    /* JADX WARN: Code duplicated, block: B:23:0x0053  */
    /* JADX WARN: Code duplicated, block: B:33:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x008b  */
    /* JADX WARN: Code duplicated, block: B:53:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:63:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:73:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:83:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:93:0x0117  */
    /* JADX INFO: renamed from: rememberPagerMeasurePolicy-8u0NR3k, reason: not valid java name */
    public static final Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> m1277rememberPagerMeasurePolicy8u0NR3k(final Function0<PagerLazyLayoutItemProvider> function0, final PagerState state, final PaddingValues contentPadding, final boolean reverseLayout, final Orientation orientation, final int beyondViewportPageCount, final float pageSpacing, final PageSize pageSize, final Alignment.Horizontal horizontalAlignment, final Alignment.Vertical verticalAlignment, final SnapPosition snapPosition, final CoroutineScope coroutineScope, final Function0<Integer> function1, Composer $composer, int $changed, int $changed1) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1391419623, "C(rememberPagerMeasurePolicy)P(4,11,1,9,5!1,8:c#ui.unit.Dp,7,3,12,10)56@2301L6278:PagerMeasurePolicy.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1391419623, $changed, $changed1, "androidx.compose.foundation.pager.rememberPagerMeasurePolicy (PagerMeasurePolicy.kt:56)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 35232261, "CC(remember):PagerMeasurePolicy.kt#9igjgp");
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
        boolean z12 = z | z2;
        if ((($changed & 7168) ^ 3072) > 2048 && $composer.changed(reverseLayout)) {
            z3 = true;
        } else if (($changed & 3072) == 2048) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z13 = z12 | z3;
        if (((57344 & $changed) ^ 24576) > 16384 && $composer.changed(orientation)) {
            z4 = true;
        } else if (($changed & 24576) == 16384) {
            z4 = true;
        } else {
            z4 = false;
        }
        boolean z14 = z13 | z4;
        if (((234881024 & $changed) ^ 100663296) > 67108864 && $composer.changed(horizontalAlignment)) {
            z5 = true;
        } else if (($changed & 100663296) == 67108864) {
            z5 = true;
        } else {
            z5 = false;
        }
        boolean z15 = z14 | z5;
        if (((1879048192 & $changed) ^ 805306368) > 536870912 && $composer.changed(verticalAlignment)) {
            z6 = true;
        } else if (($changed & 805306368) == 536870912) {
            z6 = true;
        } else {
            z6 = false;
        }
        boolean z16 = z15 | z6;
        if (((3670016 & $changed) ^ 1572864) > 1048576 && $composer.changed(pageSpacing)) {
            z7 = true;
        } else if ((1572864 & $changed) == 1048576) {
            z7 = true;
        } else {
            z7 = false;
        }
        boolean z17 = z16 | z7;
        if (((29360128 & $changed) ^ 12582912) > 8388608 && $composer.changed(pageSize)) {
            z8 = true;
        } else if ((12582912 & $changed) == 8388608) {
            z8 = true;
        } else {
            z8 = false;
        }
        boolean z18 = z17 | z8;
        if ((($changed1 & 14) ^ 6) > 4 && $composer.changed(snapPosition)) {
            z9 = true;
        } else if (($changed1 & 6) == 4) {
            z9 = true;
        } else {
            z9 = false;
        }
        boolean z19 = z18 | z9;
        if ((($changed1 & 896) ^ 384) > 256 && $composer.changed(function1)) {
            z10 = true;
        } else if (($changed1 & 384) == 256) {
            z10 = true;
        } else {
            z10 = false;
        }
        boolean z20 = z19 | z10;
        if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072 && $composer.changed(beyondViewportPageCount)) {
            z11 = true;
        } else if ((196608 & $changed) == 131072) {
            z11 = true;
        } else {
            z11 = false;
        }
        boolean invalid$iv = z20 | z11 | $composer.changed(coroutineScope);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function2<LazyLayoutMeasureScope, Constraints, PagerMeasureResult>() { // from class: androidx.compose.foundation.pager.PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ PagerMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m1278invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final PagerMeasureResult m1278invoke0kLqBqw(final LazyLayoutMeasureScope $this$null, final long containerConstraints) throws Throwable {
                    int i;
                    int i2;
                    int i3;
                    int iM6969getMaxWidthimpl;
                    long visualItemOffset;
                    int iM6969getMaxWidthimpl2;
                    int iM6968getMaxHeightimpl;
                    Snapshot.Companion this_$iv;
                    ObservableScopeInvalidator.m1208attachToScopeimpl(state.m1279getMeasurementScopeInvalidatorzYiylxw$foundation_release());
                    boolean isVertical = orientation == Orientation.Vertical;
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
                    int beforeContentPadding = i3;
                    int afterContentPadding = totalMainAxisPadding - beforeContentPadding;
                    long contentConstraints = ConstraintsKt.m6988offsetNN6EwU(containerConstraints, -totalHorizontalPadding, -totalVerticalPadding);
                    state.setDensity$foundation_release($this$null);
                    int spaceBetweenPages = $this$null.mo720roundToPx0680j_4(pageSpacing);
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
                    PageSize $this$invoke_0kLqBqw_u24lambda_u240 = pageSize;
                    int pageAvailableSize = RangesKt.coerceAtLeast($this$invoke_0kLqBqw_u24lambda_u240.calculateMainAxisPageSize($this$null, mainAxisAvailableSize, spaceBetweenPages), 0);
                    PagerState pagerState = state;
                    if (orientation == Orientation.Vertical) {
                        iM6969getMaxWidthimpl2 = Constraints.m6969getMaxWidthimpl(contentConstraints);
                    } else {
                        iM6969getMaxWidthimpl2 = pageAvailableSize;
                    }
                    if (orientation != Orientation.Vertical) {
                        iM6968getMaxHeightimpl = Constraints.m6968getMaxHeightimpl(contentConstraints);
                    } else {
                        iM6968getMaxHeightimpl = pageAvailableSize;
                    }
                    pagerState.m1283setPremeasureConstraintsBRTryo0$foundation_release(ConstraintsKt.Constraints$default(0, iM6969getMaxWidthimpl2, 0, iM6968getMaxHeightimpl, 5, null));
                    PagerLazyLayoutItemProvider itemProvider = function0.invoke();
                    Snapshot.Companion this_$iv2 = Snapshot.INSTANCE;
                    PagerState pagerState2 = state;
                    SnapPosition snapPosition2 = snapPosition;
                    Snapshot previousSnapshot$iv = this_$iv2.getCurrentThreadSnapshot();
                    Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                    Snapshot newSnapshot$iv = this_$iv2.makeCurrentNonObservable(previousSnapshot$iv);
                    try {
                        int currentPage = pagerState2.matchScrollPositionWithKey$foundation_release(itemProvider, pagerState2.getCurrentPage());
                        this_$iv = this_$iv2;
                        try {
                            int currentPageOffset = PagerKt.currentPageOffset(snapPosition2, mainAxisAvailableSize, pageAvailableSize, spaceBetweenPages, beforeContentPadding, afterContentPadding, pagerState2.getCurrentPage(), pagerState2.getCurrentPageOffsetFraction(), pagerState2.getPageCount());
                            try {
                                Unit unit = Unit.INSTANCE;
                                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                PagerMeasureResult measureResult = PagerMeasureKt.m1276measurePagerbmk8ZPk($this$null, function1.invoke().intValue(), itemProvider, mainAxisAvailableSize, beforeContentPadding, afterContentPadding, spaceBetweenPages, currentPage, currentPageOffset, contentConstraints, orientation, verticalAlignment, horizontalAlignment, reverseLayout, visualItemOffset, pageAvailableSize, beyondViewportPageCount, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, state.getPinnedPages(), state.getBeyondBoundsInfo()), snapPosition, state.m1280getPlacementScopeInvalidatorzYiylxw$foundation_release(), coroutineScope, new Function3<Integer, Integer, Function1<? super Placeable.PlacementScope, ? extends Unit>, MeasureResult>() { // from class: androidx.compose.foundation.pager.PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1$measureResult$1
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
                                PagerState.applyMeasureResult$foundation_release$default(state, measureResult, false, 2, null);
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
                        this_$iv = this_$iv2;
                    }
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
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
