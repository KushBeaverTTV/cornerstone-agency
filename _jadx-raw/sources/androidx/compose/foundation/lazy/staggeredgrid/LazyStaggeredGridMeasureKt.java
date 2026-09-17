package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082\b\u001a5\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\n0\u000e¢\u0006\u0002\b\u000fH\u0083\b¢\u0006\u0002\u0010\u0010\u001aR\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u000e2!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u000e2\u0006\u0010\u001a\u001a\u00020\u0001H\u0083\b\u001a;\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00142\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0003H\u0002¢\u0006\u0002\u0010\"\u001a\u001d\u0010#\u001a\u00020\b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001e0\u001dH\u0002¢\u0006\u0002\u0010$\u001a\u001c\u0010%\u001a\u00020\u0005*\u00020\u00142\u0006\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020\u0003H\u0002\u001a7\u0010(\u001a\u00020\u0005\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00122\b\b\u0002\u0010)\u001a\u00020\u00012\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00050\u000eH\u0082\b\u001a\u001c\u0010+\u001a\u00020\u0003*\u00020\u00142\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0002\u001a+\u0010.\u001a\u00020\u0005*\u00020/2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u000eH\u0082\bø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\f\u00102\u001a\u00020\u0003*\u00020 H\u0002\u001a2\u00103\u001a\u00020\u0003\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u001d2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00030\u000eH\u0082\b¢\u0006\u0002\u00104\u001a\u0016\u00105\u001a\u00020\u0003*\u00020 2\b\b\u0002\u00106\u001a\u00020\u0003H\u0000\u001a\u001e\u00107\u001a\u00020\u0003*\u00020 2\u0006\u00108\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a,\u0010;\u001a\u00020<*\u00020\u00142\u0006\u0010=\u001a\u00020\u00032\u0006\u0010>\u001a\u00020 2\u0006\u0010?\u001a\u00020 2\u0006\u0010@\u001a\u00020\u0001H\u0003\u001a\u008c\u0001\u0010A\u001a\u00020<*\u00020\f2\u0006\u0010B\u001a\u00020C2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u00012\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u00032\u0006\u0010P\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\u00032\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0001ø\u0001\u0000¢\u0006\u0004\bW\u0010X\u001a\u0014\u0010Y\u001a\u00020\u0005*\u00020 2\u0006\u0010Z\u001a\u00020\u0003H\u0002\u001a!\u0010[\u001a\u00020 *\u00020 2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eH\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\\"}, d2 = {"DebugLoggingEnabled", "", "Unset", "", "debugLog", "", "message", "Lkotlin/Function0;", "", "withDebugLogging", "T", "scope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "calculateExtraItems", "", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", "position", "filter", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "itemIndex", "beforeVisibleBounds", "calculateVisibleItems", "measuredItems", "", "Lkotlin/collections/ArrayDeque;", "itemScrollOffsets", "", "mainAxisLayoutSize", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;[Lkotlin/collections/ArrayDeque;[II)Ljava/util/List;", "debugRender", "([Lkotlin/collections/ArrayDeque;)Ljava/lang/String;", "ensureIndicesInRange", "indices", "itemCount", "fastForEach", "reverse", "action", "findPreviousItemIndex", "item", "lane", "forEach", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "forEach-nIS5qE8", "(JLkotlin/jvm/functions/Function1;)V", "indexOfMaxValue", "indexOfMinBy", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "indexOfMinValue", "minBound", "maxInRange", "indexRange", "maxInRange-jy6DScQ", "([IJ)I", "measure", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "initialScrollDelta", "initialItemIndices", "initialItemOffsets", "canRestartMeasure", "measureStaggeredGrid", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "pinnedItems", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "resolvedSlots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "reverseLayout", "contentOffset", "Landroidx/compose/ui/unit/IntOffset;", "mainAxisAvailableSize", "mainAxisSpacing", "beforeContentPadding", "afterContentPadding", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "measureStaggeredGrid-XtK8cYQ", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Ljava/util/List;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;JZZJIIIILkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "offsetBy", "delta", "transform", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridMeasureKt {
    private static final boolean DebugLoggingEnabled = false;
    private static final int Unset = Integer.MIN_VALUE;

    private static final <T> T withDebugLogging(LazyLayoutMeasureScope scope, Function1<? super LazyLayoutMeasureScope, ? extends T> function1) {
        return function1.invoke(scope);
    }

    private static final String debugRender(ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr) {
        return "";
    }

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX INFO: renamed from: measureStaggeredGrid-XtK8cYQ, reason: not valid java name */
    public static final LazyStaggeredGridMeasureResult m1245measureStaggeredGridXtK8cYQ(LazyLayoutMeasureScope $this$measureStaggeredGrid_u2dXtK8cYQ, LazyStaggeredGridState state, List<Integer> list, LazyStaggeredGridItemProvider itemProvider, LazyStaggeredGridSlots resolvedSlots, long constraints, boolean isVertical, boolean reverseLayout, long contentOffset, int mainAxisAvailableSize, int mainAxisSpacing, int beforeContentPadding, int afterContentPadding, CoroutineScope coroutineScope, GraphicsContext graphicsContext) {
        int[] $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u242;
        int iM1244maxInRangejy6DScQ;
        int[] $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u243;
        int i;
        LazyStaggeredGridMeasureContext context = new LazyStaggeredGridMeasureContext(state, list, itemProvider, resolvedSlots, constraints, isVertical, $this$measureStaggeredGrid_u2dXtK8cYQ, mainAxisAvailableSize, contentOffset, beforeContentPadding, afterContentPadding, reverseLayout, mainAxisSpacing, coroutineScope, graphicsContext, null);
        int[] firstVisibleIndices = state.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(itemProvider, state.getScrollPosition().getIndices());
        int[] firstVisibleOffsets = state.getScrollPosition().getScrollOffsets();
        if (firstVisibleIndices.length == context.getLaneCount()) {
            $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u242 = firstVisibleIndices;
        } else {
            context.getLaneInfo().reset();
            $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u242 = new int[context.getLaneCount()];
            int length = $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u242.length;
            for (int lane = 0; lane < length; lane++) {
                if (lane < firstVisibleIndices.length && firstVisibleIndices[lane] != -1) {
                    iM1244maxInRangejy6DScQ = firstVisibleIndices[lane];
                } else if (lane == 0) {
                    iM1244maxInRangejy6DScQ = 0;
                } else {
                    iM1244maxInRangejy6DScQ = m1244maxInRangejy6DScQ($this$measureStaggeredGrid_XtK8cYQ_u24lambda_u242, SpanRange.m1255constructorimpl(0, lane)) + 1;
                }
                $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u242[lane] = iM1244maxInRangejy6DScQ;
                context.getLaneInfo().setLane($this$measureStaggeredGrid_XtK8cYQ_u24lambda_u242[lane], lane);
            }
        }
        int[] initialItemIndices = $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u242;
        if (firstVisibleOffsets.length == context.getLaneCount()) {
            $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u243 = firstVisibleOffsets;
        } else {
            $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u243 = new int[context.getLaneCount()];
            int lane2 = 0;
            int length2 = $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u243.length;
            while (lane2 < length2) {
                if (lane2 < firstVisibleOffsets.length) {
                    i = firstVisibleOffsets[lane2];
                } else {
                    i = lane2 == 0 ? 0 : $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u243[lane2 - 1];
                }
                $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u243[lane2] = i;
                lane2++;
            }
        }
        int[] initialItemOffsets = $this$measureStaggeredGrid_XtK8cYQ_u24lambda_u243;
        float $this$fastRoundToInt$iv = state.getScrollToBeConsumed();
        return measure(context, Math.round($this$fastRoundToInt$iv), initialItemIndices, initialItemOffsets, true);
    }

    /* JADX WARN: Code duplicated, block: B:128:0x03b8  */
    /* JADX WARN: Code duplicated, block: B:256:0x06e5  */
    /* JADX WARN: Code duplicated, block: B:409:0x0b62  */
    /* JADX WARN: Code duplicated, block: B:412:0x0bd7  */
    /* JADX WARN: Code duplicated, block: B:414:0x0c2d  */
    /* JADX WARN: Code duplicated, block: B:415:0x0c2f  */
    /* JADX WARN: Code duplicated, block: B:417:0x0c32  */
    /* JADX WARN: Code duplicated, block: B:418:0x0c36  */
    /* JADX WARN: Code duplicated, block: B:421:0x0c76  */
    /* JADX WARN: Code duplicated, block: B:422:0x0c78  */
    /* JADX WARN: Code duplicated, block: B:424:0x0c7b  */
    /* JADX WARN: Code duplicated, block: B:426:0x0c85  */
    /* JADX WARN: Code duplicated, block: B:427:0x0c8c  */
    /* JADX WARN: Code duplicated, block: B:430:0x0cab  */
    /* JADX WARN: Code duplicated, block: B:432:0x0caf  */
    /* JADX WARN: Code duplicated, block: B:473:0x03c8 A[EDGE_INSN: B:473:0x03c8->B:130:0x03c8 BREAK  A[LOOP:9: B:106:0x0382->B:478:0x0382], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:527:0x0cb5 A[SYNTHETIC] */
    private static final LazyStaggeredGridMeasureResult measure(final LazyStaggeredGridMeasureContext $this$measure, int initialScrollDelta, int[] initialItemIndices, int[] initialItemOffsets, boolean canRestartMeasure) {
        int layoutHeight;
        boolean z;
        int currentLaneIndex;
        int itemIndex;
        long spanRange;
        boolean z2;
        int i;
        LazyStaggeredGridMeasuredItem measuredItem;
        int offset;
        long $this$isFullSpan$iv;
        boolean z3;
        int[] gaps;
        int[] gaps2;
        int i$iv;
        int i2;
        int lane;
        boolean z4;
        boolean z5;
        LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext;
        int[] currentItemOffsets;
        int[] firstItemIndices;
        int[] firstItemOffsets;
        int offsetValue;
        float consumedScroll;
        int[] firstItemIndices2;
        int layoutWidth;
        int layoutHeight2;
        int maxOffset;
        int layoutHeight3;
        int oldMainAxisSize;
        boolean z6;
        boolean canScrollForward;
        boolean z7;
        int newMainAxisSize;
        ArrayDeque[] measuredItems;
        int itemCount;
        int contentPadding;
        boolean z8;
        int firstIndex;
        List list;
        int scrollDelta;
        int maxOffsetLane;
        boolean gapDetected;
        int maxOffsetLane2;
        boolean gapDetected2;
        boolean gapDetected3;
        boolean z9;
        boolean remeasureNeeded;
        $this$measure = $this$measure;
        LazyLayoutMeasureScope scope$iv = $this$measure.getMeasureScope();
        int itemCount2 = $this$measure.getItemProvider().getItemCount();
        if (itemCount2 <= 0 || $this$measure.getLaneCount() == 0) {
            int layoutWidth2 = Constraints.m6971getMinWidthimpl($this$measure.getConstraints());
            int layoutHeight4 = Constraints.m6970getMinHeightimpl($this$measure.getConstraints());
            $this$measure.getState().getItemAnimator$foundation_release().onMeasured(0, layoutWidth2, layoutHeight4, new ArrayList(), $this$measure.getMeasuredItemProvider().getKeyIndexMap(), $this$measure.getMeasuredItemProvider(), $this$measure.getIsVertical(), false, $this$measure.getLaneCount(), false, 0, 0, $this$measure.getCoroutineScope(), $this$measure.getGraphicsContext());
            long disappearingItemsSize = $this$measure.getState().getItemAnimator$foundation_release().m1199getMinSizeToFitDisappearingItemsYbymL2g();
            if (IntSize.m7184equalsimpl0(disappearingItemsSize, IntSize.INSTANCE.m7191getZeroYbymL2g())) {
                layoutHeight = layoutHeight4;
            } else {
                layoutWidth2 = ConstraintsKt.m6986constrainWidthK40F9xA($this$measure.getConstraints(), IntSize.m7186getWidthimpl(disappearingItemsSize));
                layoutHeight = ConstraintsKt.m6985constrainHeightK40F9xA($this$measure.getConstraints(), IntSize.m7185getHeightimpl(disappearingItemsSize));
            }
            return new LazyStaggeredGridMeasureResult(initialItemIndices, initialItemOffsets, 0.0f, MeasureScope.layout$default(scope$iv, layoutWidth2, layoutHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$measure$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope $this$layout) {
                }
            }, 4, null), false, $this$measure.getIsVertical(), false, $this$measure.getResolvedSlots(), $this$measure.getItemProvider().getSpanProvider(), scope$iv, itemCount2, CollectionsKt.emptyList(), IntSizeKt.IntSize(Constraints.m6971getMinWidthimpl($this$measure.getConstraints()), Constraints.m6970getMinHeightimpl($this$measure.getConstraints())), -$this$measure.getBeforeContentPadding(), $this$measure.getMainAxisAvailableSize() + $this$measure.getAfterContentPadding(), $this$measure.getBeforeContentPadding(), $this$measure.getAfterContentPadding(), $this$measure.getMainAxisSpacing(), $this$measure.getCoroutineScope(), null);
        }
        int scrollDelta2 = initialScrollDelta;
        int[] firstItemIndices3 = Arrays.copyOf(initialItemIndices, initialItemIndices.length);
        String str = "copyOf(this, size)";
        Intrinsics.checkNotNullExpressionValue(firstItemIndices3, "copyOf(this, size)");
        int[] firstItemOffsets2 = Arrays.copyOf(initialItemOffsets, initialItemOffsets.length);
        Intrinsics.checkNotNullExpressionValue(firstItemOffsets2, "copyOf(this, size)");
        boolean remeasureNeeded2 = false;
        ensureIndicesInRange($this$measure, firstItemIndices3, itemCount2);
        offsetBy(firstItemOffsets2, -scrollDelta2);
        int laneCount = $this$measure.getLaneCount();
        ArrayDeque[] arrayDequeArr = new ArrayDeque[laneCount];
        for (int i3 = 0; i3 < laneCount; i3++) {
            arrayDequeArr[i3] = new ArrayDeque(16);
        }
        ArrayDeque[] measuredItems2 = arrayDequeArr;
        offsetBy(firstItemOffsets2, -$this$measure.getBeforeContentPadding());
        int laneToCheckForGaps = -1;
        while (measure$lambda$41$hasSpaceBeforeFirst(firstItemIndices3, firstItemOffsets2, $this$measure)) {
            int laneIndex = indexOfMaxValue(firstItemIndices3);
            int itemIndex2 = firstItemIndices3[laneIndex];
            int length = firstItemOffsets2.length;
            for (int i4 = 0; i4 < length; i4++) {
                if (firstItemIndices3[i4] != firstItemIndices3[laneIndex] && firstItemOffsets2[i4] < firstItemOffsets2[laneIndex]) {
                    firstItemOffsets2[i4] = firstItemOffsets2[laneIndex];
                }
            }
            int previousItemIndex = findPreviousItemIndex($this$measure, itemIndex2, laneIndex);
            if (previousItemIndex < 0) {
                laneToCheckForGaps = laneIndex;
                break;
            }
            int laneToCheckForGaps2 = laneToCheckForGaps;
            long spanRange2 = $this$measure.m1240getSpanRangelOCCd4c($this$measure.getItemProvider(), previousItemIndex, laneIndex);
            $this$measure.getLaneInfo().setLane(previousItemIndex, ((int) (spanRange2 & 4294967295L)) - ((int) (spanRange2 >> 32)) != 1 ? -2 : (int) (spanRange2 >> 32));
            LazyStaggeredGridMeasuredItem measuredItem2 = $this$measure.getMeasuredItemProvider().m1249getAndMeasurejy6DScQ(previousItemIndex, spanRange2);
            int offset2 = m1244maxInRangejy6DScQ(firstItemOffsets2, spanRange2);
            long $this$isFullSpan$iv2 = spanRange2 >> 32;
            int[] gaps3 = ((int) (spanRange2 & 4294967295L)) - ((int) $this$isFullSpan$iv2) != 1 ? $this$measure.getLaneInfo().getGaps(previousItemIndex) : null;
            int i$iv2 = (int) (spanRange2 >> 32);
            int i5 = (int) (spanRange2 & 4294967295L);
            for (int i$iv3 = i$iv2; i$iv3 < i5; i$iv3++) {
                int lane2 = i$iv3;
                firstItemIndices3[lane2] = previousItemIndex;
                int gap = gaps3 == null ? 0 : gaps3[lane2];
                int newOffset = offset2 + measuredItem2.getMainAxisSizeWithSpacings() + gap;
                firstItemOffsets2[lane2] = newOffset;
                if ($this$measure.getMainAxisAvailableSize() + newOffset <= 0) {
                    remeasureNeeded2 = true;
                }
            }
            remeasureNeeded2 = remeasureNeeded2;
            laneToCheckForGaps = laneToCheckForGaps2;
            measuredItems2 = measuredItems2;
        }
        int $i$f$debugLog = $this$measure.getBeforeContentPadding();
        int minOffset = -$i$f$debugLog;
        if (firstItemOffsets2[0] < minOffset) {
            scrollDelta2 += firstItemOffsets2[0];
            offsetBy(firstItemOffsets2, minOffset - firstItemOffsets2[0]);
        }
        int $i$f$debugLog2 = $this$measure.getBeforeContentPadding();
        offsetBy(firstItemOffsets2, $i$f$debugLog2);
        int laneToCheckForGaps3 = laneToCheckForGaps == -1 ? ArraysKt.indexOf(firstItemIndices3, 0) : laneToCheckForGaps;
        if (laneToCheckForGaps3 != -1 && measure$lambda$41$misalignedStart(firstItemIndices3, $this$measure, firstItemOffsets2, laneToCheckForGaps3) && canRestartMeasure) {
            $this$measure.getLaneInfo().reset();
            int length2 = firstItemIndices3.length;
            int[] iArr = new int[length2];
            for (int i6 = 0; i6 < length2; i6++) {
                iArr[i6] = -1;
            }
            int length3 = firstItemOffsets2.length;
            int[] iArr2 = new int[length3];
            for (int i7 = 0; i7 < length3; i7++) {
                iArr2[i7] = firstItemOffsets2[laneToCheckForGaps3];
            }
            return measure($this$measure, scrollDelta2, iArr, iArr2, false);
        }
        int[] currentItemIndices = Arrays.copyOf(firstItemIndices3, firstItemIndices3.length);
        Intrinsics.checkNotNullExpressionValue(currentItemIndices, "copyOf(this, size)");
        int length4 = firstItemOffsets2.length;
        int[] iArr3 = new int[length4];
        for (int i8 = 0; i8 < length4; i8++) {
            iArr3[i8] = -firstItemOffsets2[i8];
        }
        int[] currentItemOffsets2 = iArr3;
        int minVisibleOffset = $this$measure.getMainAxisSpacing() + minOffset;
        int maxOffset2 = RangesKt.coerceAtLeast($this$measure.getMainAxisAvailableSize() + $this$measure.getAfterContentPadding(), 0);
        int initialItemsMeasured = 0;
        int initialLaneToMeasure = indexOfMinValue$default(currentItemIndices, 0, 1, null);
        boolean remeasureNeeded3 = remeasureNeeded2;
        while (initialLaneToMeasure != -1 && initialItemsMeasured < $this$measure.getLaneCount()) {
            int itemIndex3 = currentItemIndices[initialLaneToMeasure];
            int laneIndex2 = initialLaneToMeasure;
            initialLaneToMeasure = indexOfMinValue(currentItemIndices, itemIndex3);
            initialItemsMeasured++;
            if (itemIndex3 >= 0) {
                int laneToCheckForGaps4 = laneToCheckForGaps3;
                boolean remeasureNeeded4 = remeasureNeeded3;
                long spanRange3 = $this$measure.m1240getSpanRangelOCCd4c($this$measure.getItemProvider(), itemIndex3, laneIndex2);
                LazyStaggeredGridMeasuredItem measuredItem3 = $this$measure.getMeasuredItemProvider().m1249getAndMeasurejy6DScQ(itemIndex3, spanRange3);
                int scrollDelta3 = scrollDelta2;
                int minOffset2 = minOffset;
                int[] firstItemIndices4 = firstItemIndices3;
                $this$measure.getLaneInfo().setLane(itemIndex3, ((int) (spanRange3 & 4294967295L)) - ((int) (spanRange3 >> 32)) != 1 ? -2 : (int) (spanRange3 >> 32));
                int offset3 = m1244maxInRangejy6DScQ(currentItemOffsets2, spanRange3);
                String str2 = str;
                int[] firstItemOffsets3 = firstItemOffsets2;
                long $this$forEach_u2dnIS5qE8$iv = spanRange3 & 4294967295L;
                int i9 = (int) $this$forEach_u2dnIS5qE8$iv;
                for (int i$iv4 = (int) (spanRange3 >> 32); i$iv4 < i9; i$iv4++) {
                    int lane3 = i$iv4;
                    currentItemOffsets2[lane3] = measuredItem3.getMainAxisSizeWithSpacings() + offset3;
                    currentItemIndices[lane3] = itemIndex3;
                    measuredItems2[lane3].addLast(measuredItem3);
                }
                if (offset3 >= minVisibleOffset || currentItemOffsets2[(int) (spanRange3 >> 32)] > minVisibleOffset) {
                    remeasureNeeded = remeasureNeeded4;
                } else {
                    measuredItem3.setVisible(false);
                    remeasureNeeded = true;
                }
                boolean remeasureNeeded5 = remeasureNeeded;
                long $this$isFullSpan$iv3 = spanRange3 >> 32;
                if (((int) (spanRange3 & 4294967295L)) - ((int) $this$isFullSpan$iv3) != 1) {
                    initialItemsMeasured = $this$measure.getLaneCount();
                    initialLaneToMeasure = initialLaneToMeasure;
                    str = str2;
                    remeasureNeeded3 = remeasureNeeded5;
                    scrollDelta2 = scrollDelta3;
                    laneToCheckForGaps3 = laneToCheckForGaps4;
                    firstItemIndices3 = firstItemIndices4;
                    minOffset = minOffset2;
                    firstItemOffsets2 = firstItemOffsets3;
                } else {
                    initialLaneToMeasure = initialLaneToMeasure;
                    initialItemsMeasured = initialItemsMeasured;
                    str = str2;
                    remeasureNeeded3 = remeasureNeeded5;
                    scrollDelta2 = scrollDelta3;
                    laneToCheckForGaps3 = laneToCheckForGaps4;
                    firstItemIndices3 = firstItemIndices4;
                    minOffset = minOffset2;
                    firstItemOffsets2 = firstItemOffsets3;
                }
            }
        }
        int scrollDelta4 = scrollDelta2;
        int minOffset3 = minOffset;
        int[] firstItemIndices5 = firstItemIndices3;
        String str3 = str;
        int[] firstItemOffsets4 = firstItemOffsets2;
        boolean remeasureNeeded6 = remeasureNeeded3;
        while (true) {
            int[] $this$any$iv = currentItemOffsets2;
            int length5 = $this$any$iv.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length5) {
                    z = false;
                    break;
                }
                int element$iv = $this$any$iv[i10];
                if (element$iv < maxOffset2 || element$iv <= 0) {
                    z = true;
                    break;
                }
                i10++;
            }
            if (!z) {
                ArrayDeque[] arrayDequeArr2 = measuredItems2;
                int length6 = arrayDequeArr2.length;
                int i11 = 0;
                while (true) {
                    if (i11 >= length6) {
                        z9 = true;
                        break;
                    }
                    if (!arrayDequeArr2[i11].isEmpty()) {
                        z9 = false;
                        break;
                    }
                    i11++;
                }
                if (!z9) {
                    break;
                }
                currentLaneIndex = indexOfMinValue$default(currentItemOffsets2, 0, 1, null);
                itemIndex = ArraysKt.maxOrThrow(currentItemIndices) + 1;
                if (itemIndex >= itemCount2) {
                    break;
                }
                itemCount2 = itemCount2;
                currentItemIndices = currentItemIndices;
                maxOffset2 = maxOffset2;
                str3 = str3;
                measuredItems2 = measuredItems2;
                firstItemIndices5 = firstItemIndices5;
                int[] currentItemIndices2 = firstItemOffsets4;
                int minVisibleOffset2 = minVisibleOffset;
                initialLaneToMeasure = initialLaneToMeasure;
                initialItemsMeasured = initialItemsMeasured;
                scrollDelta4 = scrollDelta4;
                int[] currentItemOffsets3 = currentItemOffsets2;
                spanRange = $this$measure.m1240getSpanRangelOCCd4c($this$measure.getItemProvider(), itemIndex, currentLaneIndex);
                LazyStaggeredGridLaneInfo laneInfo = $this$measure.getLaneInfo();
                if (((int) (spanRange & 4294967295L)) - ((int) (spanRange >> 32)) != 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    i = -2;
                } else {
                    i = (int) (spanRange >> 32);
                }
                laneInfo.setLane(itemIndex, i);
                measuredItem = $this$measure.getMeasuredItemProvider().m1249getAndMeasurejy6DScQ(itemIndex, spanRange);
                currentItemOffsets2 = currentItemOffsets3;
                offset = m1244maxInRangejy6DScQ(currentItemOffsets2, spanRange);
                firstItemOffsets4 = currentItemIndices2;
                $this$isFullSpan$iv = spanRange >> 32;
                if (((int) (spanRange & 4294967295L)) - ((int) $this$isFullSpan$iv) != 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    gaps = $this$measure.getLaneInfo().getGaps(itemIndex);
                    if (gaps == null) {
                        gaps = new int[$this$measure.getLaneCount()];
                    }
                } else {
                    gaps = null;
                }
                gaps2 = gaps;
                long $this$forEach_u2dnIS5qE8$iv2 = spanRange & 4294967295L;
                i2 = (int) $this$forEach_u2dnIS5qE8$iv2;
                for (i$iv = (int) (spanRange >> 32); i$iv < i2; i$iv++) {
                    lane = i$iv;
                    if (gaps2 != null) {
                        gaps2[lane] = offset - currentItemOffsets2[lane];
                    }
                    currentItemIndices[lane] = itemIndex;
                    currentItemOffsets2[lane] = measuredItem.getMainAxisSizeWithSpacings() + offset;
                    measuredItems2[lane].addLast(measuredItem);
                }
                $this$measure.getLaneInfo().setGaps(itemIndex, gaps2);
                minVisibleOffset = minVisibleOffset2;
                if (offset >= minVisibleOffset && currentItemOffsets2[(int) (spanRange >> 32)] <= minVisibleOffset) {
                    measuredItem.setVisible(false);
                }
            } else {
                currentLaneIndex = indexOfMinValue$default(currentItemOffsets2, 0, 1, null);
                itemIndex = ArraysKt.maxOrThrow(currentItemIndices) + 1;
                if (itemIndex >= itemCount2) {
                    break;
                    break;
                }
                itemCount2 = itemCount2;
                currentItemIndices = currentItemIndices;
                maxOffset2 = maxOffset2;
                str3 = str3;
                measuredItems2 = measuredItems2;
                firstItemIndices5 = firstItemIndices5;
                int[] currentItemIndices3 = firstItemOffsets4;
                int minVisibleOffset3 = minVisibleOffset;
                initialLaneToMeasure = initialLaneToMeasure;
                initialItemsMeasured = initialItemsMeasured;
                scrollDelta4 = scrollDelta4;
                int[] currentItemOffsets4 = currentItemOffsets2;
                spanRange = $this$measure.m1240getSpanRangelOCCd4c($this$measure.getItemProvider(), itemIndex, currentLaneIndex);
                LazyStaggeredGridLaneInfo laneInfo2 = $this$measure.getLaneInfo();
                if (((int) (spanRange & 4294967295L)) - ((int) (spanRange >> 32)) != 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    i = -2;
                } else {
                    i = (int) (spanRange >> 32);
                }
                laneInfo2.setLane(itemIndex, i);
                measuredItem = $this$measure.getMeasuredItemProvider().m1249getAndMeasurejy6DScQ(itemIndex, spanRange);
                currentItemOffsets2 = currentItemOffsets4;
                offset = m1244maxInRangejy6DScQ(currentItemOffsets2, spanRange);
                firstItemOffsets4 = currentItemIndices3;
                $this$isFullSpan$iv = spanRange >> 32;
                if (((int) (spanRange & 4294967295L)) - ((int) $this$isFullSpan$iv) != 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    gaps = $this$measure.getLaneInfo().getGaps(itemIndex);
                    if (gaps == null) {
                        gaps = new int[$this$measure.getLaneCount()];
                    }
                } else {
                    gaps = null;
                }
                gaps2 = gaps;
                long $this$forEach_u2dnIS5qE8$iv3 = spanRange & 4294967295L;
                i2 = (int) $this$forEach_u2dnIS5qE8$iv3;
                while (i$iv < i2) {
                    lane = i$iv;
                    if (gaps2 != null) {
                        gaps2[lane] = offset - currentItemOffsets2[lane];
                    }
                    currentItemIndices[lane] = itemIndex;
                    currentItemOffsets2[lane] = measuredItem.getMainAxisSizeWithSpacings() + offset;
                    measuredItems2[lane].addLast(measuredItem);
                }
                $this$measure.getLaneInfo().setGaps(itemIndex, gaps2);
                minVisibleOffset = minVisibleOffset3;
                if (offset >= minVisibleOffset) {
                }
            }
        }
        ArrayDeque[] measuredItems3 = measuredItems2;
        int length7 = measuredItems3.length;
        for (int laneIndex3 = 0; laneIndex3 < length7; laneIndex3++) {
            ArrayDeque laneItems = measuredItems3[laneIndex3];
            while (laneItems.size() > 1 && !((LazyStaggeredGridMeasuredItem) laneItems.first()).getIsVisible()) {
                LazyStaggeredGridMeasuredItem item = (LazyStaggeredGridMeasuredItem) laneItems.removeFirst();
                int[] gaps4 = item.getSpan() != 1 ? $this$measure.getLaneInfo().getGaps(item.getIndex()) : null;
                firstItemOffsets4[laneIndex3] = firstItemOffsets4[laneIndex3] - (item.getMainAxisSizeWithSpacings() + (gaps4 == null ? 0 : gaps4[laneIndex3]));
            }
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem = (LazyStaggeredGridMeasuredItem) laneItems.firstOrNull();
            firstItemIndices5[laneIndex3] = lazyStaggeredGridMeasuredItem != null ? lazyStaggeredGridMeasuredItem.getIndex() : -1;
        }
        int[] $this$any$iv2 = currentItemIndices;
        int length8 = $this$any$iv2.length;
        int i12 = 0;
        while (true) {
            if (i12 >= length8) {
                z4 = false;
                break;
            }
            int element$iv2 = $this$any$iv2[i12];
            int it = element$iv2 == itemCount2 + (-1) ? 1 : 0;
            if (it != 0) {
                z4 = true;
                break;
            }
            i12++;
        }
        if (z4) {
            offsetBy(currentItemOffsets2, -$this$measure.getMainAxisSpacing());
        }
        int[] $this$all$iv = currentItemOffsets2;
        int length9 = $this$all$iv.length;
        int i13 = 0;
        while (true) {
            if (i13 >= length9) {
                z5 = true;
                break;
            }
            int element$iv3 = $this$all$iv[i13];
            int it2 = element$iv3 < $this$measure.getMainAxisAvailableSize() ? 1 : 0;
            if (it2 == 0) {
                z5 = false;
                break;
            }
            i13++;
        }
        if (z5) {
            int maxOffsetLane3 = indexOfMaxValue(currentItemOffsets2);
            int toScrollBack = $this$measure.getMainAxisAvailableSize() - currentItemOffsets2[maxOffsetLane3];
            firstItemOffsets = firstItemOffsets4;
            offsetBy(firstItemOffsets, -toScrollBack);
            offsetBy(currentItemOffsets2, toScrollBack);
            boolean gapDetected4 = false;
            while (true) {
                int length10 = firstItemOffsets.length;
                int i14 = 0;
                while (true) {
                    if (i14 >= length10) {
                        maxOffsetLane = maxOffsetLane3;
                        gapDetected = gapDetected4;
                        maxOffsetLane2 = 0;
                        break;
                    }
                    int element$iv4 = firstItemOffsets[i14];
                    maxOffsetLane = maxOffsetLane3;
                    gapDetected = gapDetected4;
                    if (element$iv4 < $this$measure.getBeforeContentPadding()) {
                        maxOffsetLane2 = 1;
                        break;
                    }
                    i14++;
                    maxOffsetLane3 = maxOffsetLane;
                    gapDetected4 = gapDetected;
                }
                if (maxOffsetLane2 == 0) {
                    lazyStaggeredGridMeasureContext = $this$measure;
                    firstItemIndices = firstItemIndices5;
                    scrollDelta4 = scrollDelta4;
                    gapDetected2 = gapDetected;
                    break;
                }
                int laneIndex4 = indexOfMinValue$default(firstItemOffsets, 0, 1, null);
                int nextLaneIndex = indexOfMaxValue(firstItemIndices5);
                if (laneIndex4 == nextLaneIndex) {
                    gapDetected3 = gapDetected;
                } else if (firstItemOffsets[laneIndex4] == firstItemOffsets[nextLaneIndex]) {
                    laneIndex4 = nextLaneIndex;
                    gapDetected3 = gapDetected;
                } else {
                    gapDetected3 = true;
                }
                int currentIndex = firstItemIndices5[laneIndex4] == -1 ? itemCount2 : firstItemIndices5[laneIndex4];
                lazyStaggeredGridMeasureContext = $this$measure;
                int previousIndex = findPreviousItemIndex(lazyStaggeredGridMeasureContext, currentIndex, laneIndex4);
                if (previousIndex < 0) {
                    if (!gapDetected3) {
                        firstItemIndices = firstItemIndices5;
                        if (measure$lambda$41$misalignedStart(firstItemIndices, lazyStaggeredGridMeasureContext, firstItemOffsets, laneIndex4)) {
                        }
                        gapDetected2 = gapDetected3;
                        break;
                    }
                    firstItemIndices = firstItemIndices5;
                    if (!canRestartMeasure) {
                        gapDetected2 = gapDetected3;
                        break;
                    }
                    $this$measure.getLaneInfo().reset();
                    int length11 = firstItemIndices.length;
                    int[] iArr4 = new int[length11];
                    for (int initialLaneToMeasure2 = 0; initialLaneToMeasure2 < length11; initialLaneToMeasure2++) {
                        iArr4[initialLaneToMeasure2] = -1;
                    }
                    int length12 = firstItemOffsets.length;
                    int[] iArr5 = new int[length12];
                    for (int initialItemsMeasured2 = 0; initialItemsMeasured2 < length12; initialItemsMeasured2++) {
                        iArr5[initialItemsMeasured2] = firstItemOffsets[laneIndex4];
                    }
                    return measure(lazyStaggeredGridMeasureContext, scrollDelta4, iArr4, iArr5, false);
                }
                boolean gapDetected5 = gapDetected3;
                int initialLaneToMeasure3 = initialLaneToMeasure;
                int[] firstItemIndices6 = firstItemIndices5;
                int initialItemsMeasured3 = initialItemsMeasured;
                int initialItemsMeasured4 = scrollDelta4;
                int minVisibleOffset4 = minVisibleOffset;
                int maxOffset3 = maxOffset2;
                long spanRange4 = lazyStaggeredGridMeasureContext.m1240getSpanRangelOCCd4c($this$measure.getItemProvider(), previousIndex, laneIndex4);
                int[] currentItemIndices4 = currentItemIndices;
                int[] currentItemOffsets5 = currentItemOffsets2;
                $this$measure.getLaneInfo().setLane(previousIndex, ((int) (spanRange4 & 4294967295L)) - ((int) (spanRange4 >> 32)) != 1 ? -2 : (int) (spanRange4 >> 32));
                LazyStaggeredGridMeasuredItem measuredItem4 = $this$measure.getMeasuredItemProvider().m1249getAndMeasurejy6DScQ(previousIndex, spanRange4);
                int offset4 = m1244maxInRangejy6DScQ(firstItemOffsets, spanRange4);
                int toScrollBack2 = toScrollBack;
                int itemCount3 = itemCount2;
                long $this$isFullSpan$iv4 = spanRange4 >> 32;
                int[] gaps5 = ((int) (spanRange4 & 4294967295L)) - ((int) $this$isFullSpan$iv4) != 1 ? $this$measure.getLaneInfo().getGaps(previousIndex) : null;
                long $this$forEach_u2dnIS5qE8$iv4 = spanRange4 & 4294967295L;
                int i15 = (int) $this$forEach_u2dnIS5qE8$iv4;
                for (int i$iv5 = (int) (spanRange4 >> 32); i$iv5 < i15; i$iv5++) {
                    int lane4 = i$iv5;
                    if (firstItemOffsets[lane4] != offset4) {
                        gapDetected5 = true;
                    }
                    measuredItems3[lane4].addFirst(measuredItem4);
                    firstItemIndices6[lane4] = previousIndex;
                    int gap2 = gaps5 == null ? 0 : gaps5[lane4];
                    firstItemOffsets[lane4] = offset4 + measuredItem4.getMainAxisSizeWithSpacings() + gap2;
                }
                maxOffset2 = maxOffset3;
                scrollDelta4 = initialItemsMeasured4;
                gapDetected4 = gapDetected5;
                toScrollBack = toScrollBack2;
                initialLaneToMeasure = initialLaneToMeasure3;
                initialItemsMeasured = initialItemsMeasured3;
                minVisibleOffset = minVisibleOffset4;
                currentItemIndices = currentItemIndices4;
                currentItemOffsets2 = currentItemOffsets5;
                itemCount2 = itemCount3;
                firstItemIndices5 = firstItemIndices6;
                maxOffsetLane3 = maxOffsetLane;
            }
            if (gapDetected2 && canRestartMeasure) {
                $this$measure.getLaneInfo().reset();
                return measure(lazyStaggeredGridMeasureContext, scrollDelta4, firstItemIndices, firstItemOffsets, false);
            }
            int scrollDelta5 = scrollDelta4 + toScrollBack;
            int minOffsetLane = indexOfMinValue$default(firstItemOffsets, 0, 1, null);
            if (firstItemOffsets[minOffsetLane] < 0) {
                int offsetValue2 = firstItemOffsets[minOffsetLane];
                currentItemOffsets = currentItemOffsets2;
                offsetBy(currentItemOffsets, offsetValue2);
                offsetBy(firstItemOffsets, -offsetValue2);
                offsetValue = scrollDelta5 + offsetValue2;
            } else {
                currentItemOffsets = currentItemOffsets2;
                offsetValue = scrollDelta5;
            }
        } else {
            lazyStaggeredGridMeasureContext = $this$measure;
            itemCount2 = itemCount2;
            currentItemIndices = currentItemIndices;
            currentItemOffsets = currentItemOffsets2;
            firstItemIndices = firstItemIndices5;
            firstItemOffsets = firstItemOffsets4;
            maxOffset2 = maxOffset2;
            int initialItemsMeasured5 = scrollDelta4;
            offsetValue = initialItemsMeasured5;
        }
        float $this$fastRoundToInt$iv = $this$measure.getState().getScrollToBeConsumed();
        if (MathKt.getSign(Math.round($this$fastRoundToInt$iv)) == MathKt.getSign(offsetValue)) {
            float $this$fastRoundToInt$iv2 = $this$measure.getState().getScrollToBeConsumed();
            if (Math.abs(Math.round($this$fastRoundToInt$iv2)) >= Math.abs(offsetValue)) {
                consumedScroll = offsetValue;
            } else {
                consumedScroll = $this$measure.getState().getScrollToBeConsumed();
            }
        } else {
            consumedScroll = $this$measure.getState().getScrollToBeConsumed();
        }
        int[] $this$transform$iv = Arrays.copyOf(firstItemOffsets, firstItemOffsets.length);
        Intrinsics.checkNotNullExpressionValue($this$transform$iv, str3);
        int length13 = $this$transform$iv.length;
        for (int i$iv6 = 0; i$iv6 < length13; i$iv6++) {
            int it3 = $this$transform$iv[i$iv6];
            $this$transform$iv[i$iv6] = -it3;
        }
        int $i$f$debugLog3 = $this$measure.getBeforeContentPadding();
        if ($i$f$debugLog3 > $this$measure.getMainAxisSpacing()) {
            int laneIndex5 = 0;
            int length14 = measuredItems3.length;
            while (laneIndex5 < length14) {
                ArrayDeque laneItems2 = measuredItems3[laneIndex5];
                int i16 = 0;
                int size = laneItems2.size();
                while (true) {
                    if (i16 >= size) {
                        scrollDelta = offsetValue;
                        break;
                    }
                    LazyStaggeredGridMeasuredItem item2 = (LazyStaggeredGridMeasuredItem) laneItems2.get(i16);
                    scrollDelta = offsetValue;
                    int[] gaps6 = $this$measure.getLaneInfo().getGaps(item2.getIndex());
                    int size2 = item2.getMainAxisSizeWithSpacings() + (gaps6 == null ? 0 : gaps6[laneIndex5]);
                    if (i16 == CollectionsKt.getLastIndex(laneItems2) || firstItemOffsets[laneIndex5] == 0 || firstItemOffsets[laneIndex5] < size2) {
                        break;
                    }
                    firstItemOffsets[laneIndex5] = firstItemOffsets[laneIndex5] - size2;
                    firstItemIndices[laneIndex5] = ((LazyStaggeredGridMeasuredItem) laneItems2.get(i16 + 1)).getIndex();
                    i16++;
                    offsetValue = scrollDelta;
                }
                laneIndex5++;
                offsetValue = scrollDelta;
            }
        }
        int $i$f$debugLog4 = $this$measure.getBeforeContentPadding();
        int contentPadding2 = $i$f$debugLog4 + $this$measure.getAfterContentPadding();
        int layoutWidth3 = $this$measure.getIsVertical() ? Constraints.m6969getMaxWidthimpl($this$measure.getConstraints()) : ConstraintsKt.m6986constrainWidthK40F9xA($this$measure.getConstraints(), ArraysKt.maxOrThrow(currentItemOffsets) + contentPadding2);
        int contentPadding3 = $this$measure.getIsVertical() ? ConstraintsKt.m6985constrainHeightK40F9xA($this$measure.getConstraints(), ArraysKt.maxOrThrow(currentItemOffsets) + contentPadding2) : Constraints.m6968getMaxHeightimpl($this$measure.getConstraints());
        int it4 = Math.min($this$measure.getIsVertical() ? contentPadding3 : layoutWidth3, $this$measure.getMainAxisAvailableSize());
        int mainAxisLayoutSize = (it4 - $this$measure.getBeforeContentPadding()) + $this$measure.getAfterContentPadding();
        int extraItemOffset = $this$transform$iv[0];
        boolean beforeVisibleBounds$iv = true;
        int $i$f$calculateExtraItems = 0;
        List listEmptyList = null;
        List<Integer> pinnedItems = $this$measure.getPinnedItems();
        int size3 = pinnedItems.size() - 1;
        if (size3 >= 0) {
            while (true) {
                int index$iv$iv$iv = size3;
                size3--;
                int $i$f$calculateExtraItems2 = $i$f$calculateExtraItems;
                List<Integer> list2 = pinnedItems;
                boolean beforeVisibleBounds$iv2 = beforeVisibleBounds$iv;
                Object item$iv$iv$iv = list2.get(index$iv$iv$iv);
                int index$iv = ((Number) item$iv$iv$iv).intValue();
                maxOffset = maxOffset2;
                int lane5 = $this$measure.getLaneInfo().getLane(index$iv);
                switch (lane5) {
                    case -2:
                    case -1:
                        firstItemIndices2 = firstItemIndices;
                        layoutHeight2 = contentPadding3;
                        ArrayDeque[] arrayDequeArr3 = measuredItems3;
                        int layoutHeight5 = arrayDequeArr3.length;
                        layoutWidth = layoutWidth3;
                        int layoutWidth4 = 0;
                        while (true) {
                            if (layoutWidth4 >= layoutHeight5) {
                                firstIndex = 1;
                            } else {
                                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem2 = (LazyStaggeredGridMeasuredItem) arrayDequeArr3[layoutWidth4].firstOrNull();
                                int firstIndex2 = lazyStaggeredGridMeasuredItem2 != null ? lazyStaggeredGridMeasuredItem2.getIndex() : -1;
                                ArrayDeque[] arrayDequeArr4 = arrayDequeArr3;
                                int firstIndex3 = firstIndex2 > index$iv ? 1 : 0;
                                if (firstIndex3 == 0) {
                                    firstIndex = 0;
                                } else {
                                    layoutWidth4++;
                                    arrayDequeArr3 = arrayDequeArr4;
                                }
                            }
                            break;
                        }
                        break;
                    default:
                        firstItemIndices2 = firstItemIndices;
                        layoutWidth = layoutWidth3;
                        layoutHeight2 = contentPadding3;
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem3 = (LazyStaggeredGridMeasuredItem) measuredItems3[lane5].firstOrNull();
                        int firstIndex4 = lazyStaggeredGridMeasuredItem3 != null ? lazyStaggeredGridMeasuredItem3.getIndex() : -1;
                        firstIndex = firstIndex4 > index$iv ? 1 : 0;
                        break;
                }
                if (firstIndex != 0) {
                    long spanRange$iv = $this$measure.m1240getSpanRangelOCCd4c($this$measure.getItemProvider(), index$iv, 0);
                    if (listEmptyList == null) {
                        Object result$iv = new ArrayList();
                        list = (List) result$iv;
                    } else {
                        list = listEmptyList;
                    }
                    LazyStaggeredGridMeasuredItem measuredItem$iv = $this$measure.getMeasuredItemProvider().m1249getAndMeasurejy6DScQ(index$iv, spanRange$iv);
                    extraItemOffset -= measuredItem$iv.getMainAxisSizeWithSpacings();
                    measuredItem$iv.position(extraItemOffset, 0, mainAxisLayoutSize);
                    list.add(measuredItem$iv);
                    listEmptyList = list;
                }
                if (size3 >= 0) {
                    contentPadding2 = contentPadding2;
                    beforeVisibleBounds$iv = beforeVisibleBounds$iv2;
                    $i$f$calculateExtraItems = $i$f$calculateExtraItems2;
                    pinnedItems = list2;
                    contentPadding3 = layoutHeight2;
                    layoutWidth3 = layoutWidth;
                    maxOffset2 = maxOffset;
                    firstItemIndices = firstItemIndices2;
                }
            }
        } else {
            firstItemIndices2 = firstItemIndices;
            layoutWidth = layoutWidth3;
            layoutHeight2 = contentPadding3;
            maxOffset = maxOffset2;
            contentPadding2 = contentPadding2;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List extraItemsBefore = listEmptyList;
        List<LazyStaggeredGridMeasuredItem> listCalculateVisibleItems = calculateVisibleItems(lazyStaggeredGridMeasureContext, measuredItems3, $this$transform$iv, mainAxisLayoutSize);
        int extraItemOffset2 = $this$transform$iv[0];
        int $i$f$calculateExtraItems3 = 0;
        List listEmptyList2 = null;
        List<Integer> pinnedItems2 = $this$measure.getPinnedItems();
        List<Integer> list3 = pinnedItems2;
        int size4 = list3.size();
        int index$iv$iv$iv2 = 0;
        while (index$iv$iv$iv2 < size4) {
            int i17 = size4;
            List<Integer> list4 = list3;
            Object item$iv$iv$iv2 = list4.get(index$iv$iv$iv2);
            int index$iv2 = ((Number) item$iv$iv$iv2).intValue();
            List<Integer> list5 = pinnedItems2;
            int itemCount4 = itemCount2;
            int itemIndex4 = $i$f$calculateExtraItems3;
            if (index$iv2 < itemCount4) {
                measuredItems = measuredItems3;
                int lane6 = $this$measure.getLaneInfo().getLane(index$iv2);
                switch (lane6) {
                    case -2:
                    case -1:
                        itemCount = itemCount4;
                        int[] $this$all$iv2 = currentItemIndices;
                        int itemCount5 = $this$all$iv2.length;
                        contentPadding = contentPadding2;
                        int contentPadding4 = 0;
                        while (true) {
                            if (contentPadding4 >= itemCount5) {
                                z8 = true;
                            } else {
                                int element$iv5 = $this$all$iv2[contentPadding4];
                                int[] $this$all$iv3 = $this$all$iv2;
                                int it5 = element$iv5 < index$iv2 ? 1 : 0;
                                if (it5 == 0) {
                                    z8 = false;
                                } else {
                                    contentPadding4++;
                                    $this$all$iv2 = $this$all$iv3;
                                }
                            }
                            break;
                        }
                        break;
                    default:
                        contentPadding = contentPadding2;
                        itemCount = itemCount4;
                        z8 = currentItemIndices[lane6] < index$iv2;
                        break;
                }
            } else {
                measuredItems = measuredItems3;
                contentPadding = contentPadding2;
                itemCount = itemCount4;
                z8 = false;
            }
            if (z8) {
                long spanRange$iv2 = $this$measure.m1240getSpanRangelOCCd4c($this$measure.getItemProvider(), index$iv2, 0);
                if (listEmptyList2 == null) {
                    Object result$iv2 = new ArrayList();
                    listEmptyList2 = (List) result$iv2;
                }
                LazyStaggeredGridMeasuredItem measuredItem$iv2 = $this$measure.getMeasuredItemProvider().m1249getAndMeasurejy6DScQ(index$iv2, spanRange$iv2);
                measuredItem$iv2.position(extraItemOffset2, 0, mainAxisLayoutSize);
                extraItemOffset2 += measuredItem$iv2.getMainAxisSizeWithSpacings();
                listEmptyList2.add(measuredItem$iv2);
            }
            index$iv$iv$iv2++;
            firstItemOffsets = firstItemOffsets;
            currentItemOffsets = currentItemOffsets;
            size4 = i17;
            list3 = list4;
            $i$f$calculateExtraItems3 = itemIndex4;
            pinnedItems2 = list5;
            measuredItems3 = measuredItems;
            itemCount2 = itemCount;
            contentPadding2 = contentPadding;
        }
        int contentPadding5 = contentPadding2;
        int itemCount6 = itemCount2;
        int[] firstItemOffsets5 = firstItemOffsets;
        int[] currentItemOffsets6 = currentItemOffsets;
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        List extraItemsAfter = listEmptyList2;
        final List positionedItems = new ArrayList();
        positionedItems.addAll(extraItemsBefore);
        positionedItems.addAll(listCalculateVisibleItems);
        positionedItems.addAll(extraItemsAfter);
        $this$measure.getState().getItemAnimator$foundation_release().onMeasured((int) consumedScroll, layoutWidth, layoutHeight2, positionedItems, $this$measure.getMeasuredItemProvider().getKeyIndexMap(), $this$measure.getMeasuredItemProvider(), $this$measure.getIsVertical(), false, $this$measure.getLaneCount(), false, ArraysKt.minOrThrow(firstItemOffsets5), ArraysKt.maxOrThrow(currentItemOffsets6) + contentPadding5, $this$measure.getCoroutineScope(), $this$measure.getGraphicsContext());
        long disappearingItemsSize2 = $this$measure.getState().getItemAnimator$foundation_release().m1199getMinSizeToFitDisappearingItemsYbymL2g();
        if (IntSize.m7184equalsimpl0(disappearingItemsSize2, IntSize.INSTANCE.m7191getZeroYbymL2g())) {
            layoutHeight3 = layoutHeight2;
            oldMainAxisSize = layoutWidth;
        } else {
            int oldMainAxisSize2 = $this$measure.getIsVertical() ? layoutHeight2 : layoutWidth;
            int layoutWidth5 = ConstraintsKt.m6986constrainWidthK40F9xA($this$measure.getConstraints(), Math.max(layoutWidth, IntSize.m7186getWidthimpl(disappearingItemsSize2)));
            int layoutHeight6 = ConstraintsKt.m6985constrainHeightK40F9xA($this$measure.getConstraints(), Math.max(layoutHeight2, IntSize.m7185getHeightimpl(disappearingItemsSize2)));
            if (!$this$measure.getIsVertical()) {
                newMainAxisSize = layoutWidth5;
            }
            if (newMainAxisSize != oldMainAxisSize2) {
                int layoutHeight7 = positionedItems.size();
                int index$iv3 = 0;
                while (index$iv3 < layoutHeight7) {
                    newMainAxisSize = layoutHeight6;
                    Object item$iv = positionedItems.get(index$iv3);
                    int i18 = layoutHeight7;
                    LazyStaggeredGridMeasuredItem it6 = (LazyStaggeredGridMeasuredItem) item$iv;
                    it6.updateMainAxisLayoutSize(newMainAxisSize);
                    index$iv3++;
                    layoutHeight7 = i18;
                }
                newMainAxisSize = layoutHeight6;
            } else {
                newMainAxisSize = layoutHeight6;
            }
            oldMainAxisSize = layoutWidth5;
            layoutHeight3 = layoutHeight6;
        }
        int[] $this$any$iv3 = currentItemOffsets6;
        int length15 = $this$any$iv3.length;
        int i19 = 0;
        while (true) {
            if (i19 < length15) {
                int element$iv6 = $this$any$iv3[i19];
                int[] $this$any$iv4 = $this$any$iv3;
                long disappearingItemsSize3 = disappearingItemsSize2;
                if (element$iv6 > $this$measure.getMainAxisAvailableSize()) {
                    z6 = true;
                } else {
                    i19++;
                    $this$any$iv3 = $this$any$iv4;
                    disappearingItemsSize2 = disappearingItemsSize3;
                }
            } else {
                z6 = false;
            }
        }
        if (z6) {
            canScrollForward = true;
        } else {
            int[] $this$all$iv4 = currentItemIndices;
            int length16 = $this$all$iv4.length;
            int i20 = 0;
            while (true) {
                if (i20 < length16) {
                    int element$iv7 = $this$all$iv4[i20];
                    int[] $this$all$iv5 = $this$all$iv4;
                    if (element$iv7 < itemCount6 + (-1)) {
                        i20++;
                        $this$all$iv4 = $this$all$iv5;
                    } else {
                        z7 = false;
                    }
                } else {
                    z7 = true;
                }
            }
            if (z7) {
                canScrollForward = true;
            } else {
                canScrollForward = false;
            }
        }
        return new LazyStaggeredGridMeasureResult(firstItemIndices2, firstItemOffsets5, consumedScroll, MeasureScope.layout$default(scope$iv, oldMainAxisSize, layoutHeight3, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$measure$1$33
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
                List<LazyStaggeredGridMeasuredItem> list6 = positionedItems;
                LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext2 = $this$measure;
                int size5 = list6.size();
                for (int index$iv4 = 0; index$iv4 < size5; index$iv4++) {
                    Object item$iv2 = list6.get(index$iv4);
                    LazyStaggeredGridMeasuredItem item3 = (LazyStaggeredGridMeasuredItem) item$iv2;
                    item3.place($this$layout, lazyStaggeredGridMeasureContext2);
                }
                ObservableScopeInvalidator.m1208attachToScopeimpl($this$measure.getState().m1253getPlacementScopeInvalidatorzYiylxw$foundation_release());
            }
        }, 4, null), canScrollForward, $this$measure.getIsVertical(), remeasureNeeded6, $this$measure.getResolvedSlots(), $this$measure.getItemProvider().getSpanProvider(), scope$iv, itemCount6, listCalculateVisibleItems, IntSizeKt.IntSize(oldMainAxisSize, layoutHeight3), minOffset3, maxOffset, $this$measure.getBeforeContentPadding(), $this$measure.getAfterContentPadding(), $this$measure.getMainAxisSpacing(), $this$measure.getCoroutineScope(), null);
    }

    private static final boolean measure$lambda$41$hasSpaceBeforeFirst(int[] firstItemIndices, int[] firstItemOffsets, LazyStaggeredGridMeasureContext $this_measure) {
        int length = firstItemIndices.length;
        for (int lane = 0; lane < length; lane++) {
            int itemIndex = firstItemIndices[lane];
            int itemOffset = firstItemOffsets[lane];
            if (itemOffset < Math.max(-$this_measure.getMainAxisSpacing(), 0) && itemIndex > 0) {
                return true;
            }
        }
        return false;
    }

    private static final boolean measure$lambda$41$misalignedStart(int[] firstItemIndices, LazyStaggeredGridMeasureContext $this_measure, int[] firstItemOffsets, int referenceLane) {
        int lane = 0;
        int length = firstItemIndices.length;
        while (true) {
            boolean z = false;
            if (lane < length) {
                if (findPreviousItemIndex($this_measure, firstItemIndices[lane], lane) == -1 && firstItemOffsets[lane] != firstItemOffsets[referenceLane]) {
                    z = true;
                }
                boolean misalignedOffsets = z;
                if (misalignedOffsets) {
                    return true;
                }
                lane++;
            } else {
                int length2 = firstItemIndices.length;
                for (int lane2 = 0; lane2 < length2; lane2++) {
                    boolean moreItemsInOtherLanes = findPreviousItemIndex($this_measure, firstItemIndices[lane2], lane2) != -1 && firstItemOffsets[lane2] >= firstItemOffsets[referenceLane];
                    if (moreItemsInOtherLanes) {
                        return true;
                    }
                }
                int firstItemLane = $this_measure.getLaneInfo().getLane(0);
                return (firstItemLane == 0 || firstItemLane == -1 || firstItemLane == -2) ? false : true;
            }
        }
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateVisibleItems(LazyStaggeredGridMeasureContext $this$calculateVisibleItems, ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr, int[] itemScrollOffsets, int mainAxisLayoutSize) {
        boolean z;
        int size = 0;
        for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque : arrayDequeArr) {
            size += arrayDeque.size();
        }
        ArrayList positionedItems = new ArrayList(size);
        while (true) {
            int length = arrayDequeArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                if (!arrayDequeArr[i].isEmpty()) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                return positionedItems;
            }
            int result$iv = -1;
            int min$iv = Integer.MAX_VALUE;
            int length2 = arrayDequeArr.length;
            for (int i$iv = 0; i$iv < length2; i$iv++) {
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemFirstOrNull = arrayDequeArr[i$iv].firstOrNull();
                int value$iv = lazyStaggeredGridMeasuredItemFirstOrNull != null ? lazyStaggeredGridMeasuredItemFirstOrNull.getIndex() : Integer.MAX_VALUE;
                if (min$iv > value$iv) {
                    min$iv = value$iv;
                    result$iv = i$iv;
                }
            }
            int laneIndex = result$iv;
            LazyStaggeredGridMeasuredItem item = arrayDequeArr[laneIndex].removeFirst();
            if (item.getLane() == laneIndex) {
                long spanRange = SpanRange.m1255constructorimpl(item.getLane(), item.getSpan());
                int mainAxisOffset = m1244maxInRangejy6DScQ(itemScrollOffsets, spanRange);
                int crossAxisOffset = $this$calculateVisibleItems.getResolvedSlots().getPositions()[laneIndex];
                item.position(mainAxisOffset, crossAxisOffset, mainAxisLayoutSize);
                positionedItems.add(item);
                int i$iv2 = (int) (spanRange >> 32);
                int i2 = (int) (spanRange & 4294967295L);
                for (int i$iv3 = i$iv2; i$iv3 < i2; i$iv3++) {
                    int lane = i$iv3;
                    itemScrollOffsets[lane] = mainAxisOffset + item.getMainAxisSizeWithSpacings();
                }
            }
        }
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateExtraItems(LazyStaggeredGridMeasureContext $this$calculateExtraItems, Function1<? super LazyStaggeredGridMeasuredItem, Unit> function1, Function1<? super Integer, Boolean> function2, boolean beforeVisibleBounds) {
        Function1<? super Integer, Boolean> function3 = function2;
        int $i$f$calculateExtraItems = 0;
        ArrayList arrayList = null;
        List<Integer> pinnedItems = $this$calculateExtraItems.getPinnedItems();
        if (beforeVisibleBounds) {
            int $i$f$fastForEachReversed = 0;
            int size = pinnedItems.size() - 1;
            if (size >= 0) {
                while (true) {
                    int index$iv$iv = size;
                    size--;
                    Object item$iv$iv = pinnedItems.get(index$iv$iv);
                    int index = ((Number) item$iv$iv).intValue();
                    if (function3.invoke(Integer.valueOf(index)).booleanValue()) {
                        long spanRange = $this$calculateExtraItems.m1240getSpanRangelOCCd4c($this$calculateExtraItems.getItemProvider(), index, 0);
                        if (arrayList == null) {
                            Object result = new ArrayList();
                            arrayList = (List) result;
                        }
                        LazyStaggeredGridMeasuredItem measuredItem = $this$calculateExtraItems.getMeasuredItemProvider().m1249getAndMeasurejy6DScQ(index, spanRange);
                        function1.invoke(measuredItem);
                        arrayList.add(measuredItem);
                    }
                    if (size < 0) {
                        break;
                    }
                    pinnedItems = pinnedItems;
                    $i$f$fastForEachReversed = $i$f$fastForEachReversed;
                }
            }
        } else {
            int index$iv$iv2 = 0;
            int size2 = pinnedItems.size();
            while (index$iv$iv2 < size2) {
                Object item$iv$iv2 = pinnedItems.get(index$iv$iv2);
                int index2 = ((Number) item$iv$iv2).intValue();
                if (function3.invoke(Integer.valueOf(index2)).booleanValue()) {
                    long spanRange2 = $this$calculateExtraItems.m1240getSpanRangelOCCd4c($this$calculateExtraItems.getItemProvider(), index2, 0);
                    if (arrayList == null) {
                        Object result2 = new ArrayList();
                        arrayList = (List) result2;
                    }
                    LazyStaggeredGridMeasuredItem measuredItem2 = $this$calculateExtraItems.getMeasuredItemProvider().m1249getAndMeasurejy6DScQ(index2, spanRange2);
                    function1.invoke(measuredItem2);
                    arrayList.add(measuredItem2);
                }
                index$iv$iv2++;
                function3 = function2;
                $i$f$calculateExtraItems = $i$f$calculateExtraItems;
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    static /* synthetic */ void fastForEach$default(List $this$fastForEach_u24default, boolean reverse, Function1 action, int i, Object obj) {
        if ((i & 1) != 0) {
            reverse = false;
        }
        if (reverse) {
            int size = $this$fastForEach_u24default.size() - 1;
            if (size < 0) {
                return;
            }
            do {
                int index$iv = size;
                size--;
                Object item$iv = $this$fastForEach_u24default.get(index$iv);
                action.invoke(item$iv);
            } while (size >= 0);
            return;
        }
        int size2 = $this$fastForEach_u24default.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $this$fastForEach_u24default.get(index$iv2);
            action.invoke(item$iv2);
        }
    }

    private static final <T> void fastForEach(List<? extends T> list, boolean reverse, Function1<? super T, Unit> function1) {
        if (reverse) {
            int size = list.size() - 1;
            if (size < 0) {
                return;
            }
            do {
                int index$iv = size;
                size--;
                Object item$iv = list.get(index$iv);
                function1.invoke(item$iv);
            } while (size >= 0);
            return;
        }
        int size2 = list.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = list.get(index$iv2);
            function1.invoke(item$iv2);
        }
    }

    /* JADX INFO: renamed from: forEach-nIS5qE8, reason: not valid java name */
    private static final void m1243forEachnIS5qE8(long $this$forEach_u2dnIS5qE8, Function1<? super Integer, Unit> function1) {
        int i = (int) (4294967295L & $this$forEach_u2dnIS5qE8);
        for (int i2 = (int) ($this$forEach_u2dnIS5qE8 >> 32); i2 < i; i2++) {
            function1.invoke(Integer.valueOf(i2));
        }
    }

    private static final void offsetBy(int[] $this$offsetBy, int delta) {
        int length = $this$offsetBy.length;
        for (int i = 0; i < length; i++) {
            $this$offsetBy[i] = $this$offsetBy[i] + delta;
        }
    }

    /* JADX INFO: renamed from: maxInRange-jy6DScQ, reason: not valid java name */
    private static final int m1244maxInRangejy6DScQ(int[] $this$maxInRange_u2djy6DScQ, long indexRange) {
        int max = Integer.MIN_VALUE;
        int i = (int) (4294967295L & indexRange);
        for (int i$iv = (int) (indexRange >> 32); i$iv < i; i$iv++) {
            int it = i$iv;
            max = Math.max(max, $this$maxInRange_u2djy6DScQ[it]);
        }
        return max;
    }

    public static /* synthetic */ int indexOfMinValue$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MIN_VALUE;
        }
        return indexOfMinValue(iArr, i);
    }

    public static final int indexOfMinValue(int[] $this$indexOfMinValue, int minBound) {
        int result = -1;
        int min = Integer.MAX_VALUE;
        int length = $this$indexOfMinValue.length;
        for (int i = 0; i < length; i++) {
            int i2 = minBound + 1;
            int i3 = $this$indexOfMinValue[i];
            boolean z = false;
            if (i2 <= i3 && i3 < min) {
                z = true;
            }
            if (z) {
                min = $this$indexOfMinValue[i];
                result = i;
            }
        }
        return result;
    }

    private static final <T> int indexOfMinBy(T[] tArr, Function1<? super T, Integer> function1) {
        int result = -1;
        int min = Integer.MAX_VALUE;
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            int value = function1.invoke(tArr[i]).intValue();
            if (min > value) {
                min = value;
                result = i;
            }
        }
        return result;
    }

    private static final int indexOfMaxValue(int[] $this$indexOfMaxValue) {
        int result = -1;
        int max = Integer.MIN_VALUE;
        int length = $this$indexOfMaxValue.length;
        for (int i = 0; i < length; i++) {
            if (max < $this$indexOfMaxValue[i]) {
                max = $this$indexOfMaxValue[i];
                result = i;
            }
        }
        return result;
    }

    private static final int[] transform(int[] $this$transform, Function1<? super Integer, Integer> function1) {
        int length = $this$transform.length;
        for (int i = 0; i < length; i++) {
            $this$transform[i] = function1.invoke(Integer.valueOf($this$transform[i])).intValue();
        }
        return $this$transform;
    }

    private static final void ensureIndicesInRange(LazyStaggeredGridMeasureContext $this$ensureIndicesInRange, int[] indices, int itemCount) {
        int length = indices.length - 1;
        if (length >= 0) {
            do {
                int i = length;
                length--;
                while (true) {
                    if (indices[i] < itemCount && $this$ensureIndicesInRange.getLaneInfo().assignedToLane(indices[i], i)) {
                        break;
                    } else {
                        indices[i] = findPreviousItemIndex($this$ensureIndicesInRange, indices[i], i);
                    }
                }
                if (indices[i] >= 0 && !$this$ensureIndicesInRange.isFullSpan($this$ensureIndicesInRange.getItemProvider(), indices[i])) {
                    $this$ensureIndicesInRange.getLaneInfo().setLane(indices[i], i);
                }
            } while (length >= 0);
        }
    }

    private static final int findPreviousItemIndex(LazyStaggeredGridMeasureContext $this$findPreviousItemIndex, int item, int lane) {
        return $this$findPreviousItemIndex.getLaneInfo().findPreviousItemIndex(item, lane);
    }
}
