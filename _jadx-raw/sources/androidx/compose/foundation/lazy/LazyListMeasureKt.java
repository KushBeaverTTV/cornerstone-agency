package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyListMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u008c\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a\\\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002\u001a4\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010#\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0004H\u0002\u001a£\u0002\u0010$\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u0002012\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u00102\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u00103\u001a\u0004\u0018\u00010!2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092/\u0010:\u001a+\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020>0<¢\u0006\u0002\b?\u0012\u0004\u0012\u00020@0;H\u0000ø\u0001\u0000¢\u0006\u0004\bA\u0010B\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"calculateItemsOffsets", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "items", "", "extraItemsBefore", "extraItemsAfter", "layoutWidth", "", "layoutHeight", "finalMainAxisOffset", "maxOffset", "itemsScrollOffset", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "createItemsAfterList", "visibleItems", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "itemsCount", "beyondBoundsItemCount", "pinnedItems", "consumedScroll", "", "isLookingAhead", "lastPostLookaheadLayoutInfo", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "createItemsBeforeList", "currentFirstItemIndex", "measureLazyList", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenItems", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "scrollToBeConsumed", "constraints", "Landroidx/compose/ui/unit/Constraints;", "headerIndexes", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "hasLookaheadPassOccurred", "postLookaheadLayoutInfo", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "layout", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyList-x0Ok8Vo", "(ILandroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;IIIIIIFJZLjava/util/List;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;ZZLandroidx/compose/foundation/lazy/LazyListLayoutInfo;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListMeasureKt {
    /* JADX WARN: Code duplicated, block: B:157:0x0511  */
    /* JADX WARN: Code duplicated, block: B:158:0x0522  */
    /* JADX WARN: Code duplicated, block: B:161:0x052b  */
    /* JADX WARN: Code duplicated, block: B:164:0x0532  */
    /* JADX WARN: Code duplicated, block: B:165:0x0535  */
    /* JADX WARN: Code duplicated, block: B:169:0x0558  */
    /* JADX WARN: Code duplicated, block: B:170:0x0560  */
    /* JADX WARN: Code duplicated, block: B:172:0x0580  */
    /* JADX WARN: Code duplicated, block: B:174:0x05a5  */
    /* JADX WARN: Code duplicated, block: B:176:0x05b5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:181:0x05bf  */
    /* JADX WARN: Code duplicated, block: B:186:0x05e5  */
    /* JADX WARN: Code duplicated, block: B:187:0x05e8  */
    /* JADX WARN: Code duplicated, block: B:215:0x05c5 A[SYNTHETIC] */
    /* JADX INFO: renamed from: measureLazyList-x0Ok8Vo, reason: not valid java name */
    public static final LazyListMeasureResult m1141measureLazyListx0Ok8Vo(int itemsCount, LazyListMeasuredItemProvider measuredItemProvider, int mainAxisAvailableSize, int beforeContentPadding, int afterContentPadding, int spaceBetweenItems, int firstVisibleItemIndex, int firstVisibleItemScrollOffset, float scrollToBeConsumed, long constraints, boolean isVertical, List<Integer> list, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density, LazyLayoutItemAnimator<LazyListMeasuredItem> lazyLayoutItemAnimator, int beyondBoundsItemCount, List<Integer> list2, boolean hasLookaheadPassOccurred, final boolean isLookingAhead, LazyListLayoutInfo postLookaheadLayoutInfo, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int scrollDelta;
        int currentFirstItemScrollOffset;
        int scrollDelta2;
        int currentFirstItemScrollOffset2;
        int scrollDelta3;
        int currentMainAxisOffset;
        int maxCrossAxis;
        float f;
        int scrollDelta4;
        float scrollBackAmount;
        ArrayDeque visibleItems;
        int currentFirstItemScrollOffset3;
        LazyListMeasuredItem firstItem;
        int oldMainAxisSize;
        int layoutHeight;
        final LazyListMeasuredItem headerItem;
        int maxOffset;
        int currentMainAxisOffset2;
        boolean z;
        ArrayList target$iv;
        List<LazyListMeasuredItem> list3;
        int size;
        int layoutHeight2;
        LazyListMeasuredItem firstItem2;
        List list4;
        LazyListMeasuredItem lazyListMeasuredItem;
        LazyListMeasuredItem it;
        Orientation orientation;
        int newMainAxisSize;
        int currentMainAxisOffset3;
        if (!(beforeContentPadding >= 0)) {
            throw new IllegalArgumentException("invalid beforeContentPadding".toString());
        }
        if (!(afterContentPadding >= 0)) {
            throw new IllegalArgumentException("invalid afterContentPadding".toString());
        }
        if (itemsCount <= 0) {
            int layoutWidth = Constraints.m6971getMinWidthimpl(constraints);
            int layoutHeight3 = Constraints.m6970getMinHeightimpl(constraints);
            lazyLayoutItemAnimator.onMeasured(0, layoutWidth, layoutHeight3, new ArrayList(), measuredItemProvider.getKeyIndexMap(), measuredItemProvider, isVertical, isLookingAhead, 1, hasLookaheadPassOccurred, 0, 0, coroutineScope, graphicsContext);
            if (!isLookingAhead) {
                long disappearingItemsSize = lazyLayoutItemAnimator.m1199getMinSizeToFitDisappearingItemsYbymL2g();
                if (!IntSize.m7184equalsimpl0(disappearingItemsSize, IntSize.INSTANCE.m7191getZeroYbymL2g())) {
                    layoutWidth = ConstraintsKt.m6986constrainWidthK40F9xA(constraints, IntSize.m7186getWidthimpl(disappearingItemsSize));
                    layoutHeight3 = ConstraintsKt.m6985constrainHeightK40F9xA(constraints, IntSize.m7185getHeightimpl(disappearingItemsSize));
                }
            }
            return new LazyListMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(layoutWidth), Integer.valueOf(layoutHeight3), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope $this$invoke) {
                }
            }), 0.0f, false, coroutineScope, density, measuredItemProvider.getChildConstraints(), CollectionsKt.emptyList(), -beforeContentPadding, mainAxisAvailableSize + afterContentPadding, 0, reverseLayout, isVertical ? Orientation.Vertical : Orientation.Horizontal, afterContentPadding, spaceBetweenItems, null);
        }
        int currentFirstItemIndex = firstVisibleItemIndex;
        int currentFirstItemScrollOffset4 = firstVisibleItemScrollOffset;
        if (currentFirstItemIndex >= itemsCount) {
            currentFirstItemIndex = itemsCount - 1;
            currentFirstItemScrollOffset4 = 0;
        }
        int scrollDelta5 = Math.round(scrollToBeConsumed);
        int currentFirstItemScrollOffset5 = currentFirstItemScrollOffset4 - scrollDelta5;
        if (currentFirstItemIndex == 0 && currentFirstItemScrollOffset5 < 0) {
            int scrollDelta6 = scrollDelta5 + currentFirstItemScrollOffset5;
            currentFirstItemScrollOffset5 = 0;
            scrollDelta = scrollDelta6;
        } else {
            scrollDelta = scrollDelta5;
        }
        ArrayDeque visibleItems2 = new ArrayDeque();
        int minOffset = (-beforeContentPadding) + (spaceBetweenItems < 0 ? spaceBetweenItems : 0);
        int maxOffset2 = mainAxisAvailableSize;
        boolean remeasureNeeded = false;
        int currentFirstItemIndex2 = currentFirstItemIndex;
        int maxCrossAxis2 = 0;
        int maxCrossAxis3 = currentFirstItemScrollOffset5 + minOffset;
        while (maxCrossAxis3 < 0 && currentFirstItemIndex2 > 0) {
            int previous = currentFirstItemIndex2 - 1;
            int currentFirstItemScrollOffset6 = maxCrossAxis3;
            LazyListMeasuredItem measuredItem = LazyListMeasuredItemProvider.m1147getAndMeasure0kLqBqw$default(measuredItemProvider, previous, 0L, 2, null);
            visibleItems2.add(0, measuredItem);
            maxCrossAxis2 = Math.max(maxCrossAxis2, measuredItem.getCrossAxisSize());
            maxCrossAxis3 = currentFirstItemScrollOffset6 + measuredItem.getMainAxisSizeWithSpacings();
            currentFirstItemIndex2 = previous;
            maxOffset2 = maxOffset2;
            minOffset = minOffset;
        }
        int maxOffset3 = maxOffset2;
        int minOffset2 = minOffset;
        int maxOffset4 = maxCrossAxis3;
        int currentFirstItemIndex3 = maxCrossAxis2;
        if (maxOffset4 >= minOffset2) {
            currentFirstItemScrollOffset = maxOffset4;
        } else {
            scrollDelta += maxOffset4;
            currentFirstItemScrollOffset = minOffset2;
        }
        int currentFirstItemScrollOffset7 = currentFirstItemScrollOffset - minOffset2;
        int index = currentFirstItemIndex2;
        int maxOffset5 = maxOffset3;
        int maxMainAxis = RangesKt.coerceAtLeast(maxOffset5 + afterContentPadding, 0);
        int currentMainAxisOffset4 = -currentFirstItemScrollOffset7;
        int minOffset3 = minOffset2;
        int minOffset4 = 0;
        while (true) {
            currentFirstItemScrollOffset7 = currentFirstItemScrollOffset7;
            int currentFirstItemScrollOffset8 = visibleItems2.size();
            if (minOffset4 >= currentFirstItemScrollOffset8) {
                break;
            }
            if (currentMainAxisOffset4 >= maxMainAxis) {
                visibleItems2.remove(minOffset4);
                remeasureNeeded = true;
            } else {
                index++;
                currentMainAxisOffset4 += ((LazyListMeasuredItem) visibleItems2.get(minOffset4)).getMainAxisSizeWithSpacings();
                minOffset4++;
            }
        }
        boolean remeasureNeeded2 = remeasureNeeded;
        int i = currentMainAxisOffset4;
        int currentMainAxisOffset5 = index;
        int currentMainAxisOffset6 = i;
        while (currentMainAxisOffset5 < itemsCount && (currentMainAxisOffset6 < maxMainAxis || currentMainAxisOffset6 <= 0 || visibleItems2.isEmpty())) {
            int maxCrossAxis4 = currentFirstItemIndex3;
            int maxMainAxis2 = maxMainAxis;
            int maxOffset6 = maxOffset5;
            int indexInVisibleItems = minOffset4;
            int minOffset5 = minOffset3;
            LazyListMeasuredItem measuredItem2 = LazyListMeasuredItemProvider.m1147getAndMeasure0kLqBqw$default(measuredItemProvider, currentMainAxisOffset5, 0L, 2, null);
            currentMainAxisOffset6 = measuredItem2.getMainAxisSizeWithSpacings() + currentMainAxisOffset6;
            if (currentMainAxisOffset6 <= minOffset5 && currentMainAxisOffset5 != itemsCount - 1) {
                currentFirstItemScrollOffset7 -= measuredItem2.getMainAxisSizeWithSpacings();
                currentFirstItemIndex2 = currentMainAxisOffset5 + 1;
                remeasureNeeded2 = true;
                currentFirstItemIndex3 = maxCrossAxis4;
            } else {
                currentFirstItemIndex3 = Math.max(maxCrossAxis4, measuredItem2.getCrossAxisSize());
                visibleItems2.add(measuredItem2);
            }
            currentMainAxisOffset5++;
            minOffset3 = minOffset5;
            maxMainAxis = maxMainAxis2;
            minOffset4 = indexInVisibleItems;
            maxOffset5 = maxOffset6;
        }
        int preScrollBackScrollDelta = scrollDelta;
        int maxOffset7 = maxOffset5;
        if (currentMainAxisOffset6 >= maxOffset7) {
            maxOffset7 = maxOffset7;
            preScrollBackScrollDelta = preScrollBackScrollDelta;
            scrollDelta2 = scrollDelta;
            currentFirstItemScrollOffset2 = currentFirstItemScrollOffset7;
            scrollDelta3 = currentMainAxisOffset6;
            currentMainAxisOffset = currentFirstItemIndex3;
            maxCrossAxis = currentFirstItemIndex2;
        } else {
            int toScrollBack = maxOffset7 - currentMainAxisOffset6;
            int currentMainAxisOffset7 = currentMainAxisOffset6 + toScrollBack;
            int maxCrossAxis5 = currentFirstItemScrollOffset7 - toScrollBack;
            while (true) {
                if (maxCrossAxis5 >= beforeContentPadding) {
                    currentMainAxisOffset3 = currentFirstItemIndex3;
                    break;
                }
                if (currentFirstItemIndex2 <= 0) {
                    break;
                }
                int previousIndex = currentFirstItemIndex2 - 1;
                int maxCrossAxis6 = currentMainAxisOffset3;
                int currentFirstItemScrollOffset9 = maxCrossAxis5;
                LazyListMeasuredItem measuredItem3 = LazyListMeasuredItemProvider.m1147getAndMeasure0kLqBqw$default(measuredItemProvider, previousIndex, 0L, 2, null);
                visibleItems2.add(0, measuredItem3);
                currentMainAxisOffset3 = Math.max(maxCrossAxis6, measuredItem3.getCrossAxisSize());
                maxCrossAxis5 = currentFirstItemScrollOffset9 + measuredItem3.getMainAxisSizeWithSpacings();
                currentFirstItemIndex2 = previousIndex;
                preScrollBackScrollDelta = preScrollBackScrollDelta;
                maxOffset7 = maxOffset7;
                minOffset3 = minOffset3;
            }
            int scrollDelta7 = scrollDelta + toScrollBack;
            if (maxCrossAxis5 >= 0) {
                currentMainAxisOffset = currentMainAxisOffset3;
                scrollDelta2 = scrollDelta7;
                maxCrossAxis = currentFirstItemIndex2;
                scrollDelta3 = currentMainAxisOffset7;
                currentFirstItemScrollOffset2 = maxCrossAxis5;
            } else {
                int currentMainAxisOffset8 = currentMainAxisOffset7 + maxCrossAxis5;
                currentMainAxisOffset = currentMainAxisOffset3;
                scrollDelta2 = scrollDelta7 + maxCrossAxis5;
                maxCrossAxis = currentFirstItemIndex2;
                currentFirstItemScrollOffset2 = 0;
                scrollDelta3 = currentMainAxisOffset8;
            }
        }
        if (MathKt.getSign(Math.round(scrollToBeConsumed)) == MathKt.getSign(scrollDelta2) && Math.abs(Math.round(scrollToBeConsumed)) >= Math.abs(scrollDelta2)) {
            f = scrollDelta2;
        } else {
            f = scrollToBeConsumed;
        }
        float consumedScroll = f;
        float unconsumedScroll = scrollToBeConsumed - consumedScroll;
        float f2 = 0.0f;
        if (isLookingAhead && scrollDelta2 > preScrollBackScrollDelta && unconsumedScroll <= 0.0f) {
            f2 = (scrollDelta2 - preScrollBackScrollDelta) + unconsumedScroll;
        }
        ArrayDeque visibleItems3 = visibleItems2;
        float scrollBackAmount2 = f2;
        if (!(currentFirstItemScrollOffset2 >= 0)) {
            throw new IllegalArgumentException("negative currentFirstItemScrollOffset".toString());
        }
        int visibleItemsScrollOffset = -currentFirstItemScrollOffset2;
        LazyListMeasuredItem firstItem3 = (LazyListMeasuredItem) visibleItems3.first();
        if (beforeContentPadding > 0 || spaceBetweenItems < 0) {
            int currentFirstItemScrollOffset10 = currentFirstItemScrollOffset2;
            int currentFirstItemScrollOffset11 = visibleItems3.size();
            int visibleItemsScrollOffset2 = currentFirstItemScrollOffset10;
            int maxCrossAxis7 = 0;
            while (true) {
                if (maxCrossAxis7 >= currentFirstItemScrollOffset11) {
                    scrollDelta4 = scrollDelta2;
                    scrollBackAmount = scrollBackAmount2;
                    visibleItems = visibleItems3;
                    break;
                }
                scrollBackAmount = scrollBackAmount2;
                visibleItems = visibleItems3;
                int i2 = currentFirstItemScrollOffset11;
                int size2 = ((LazyListMeasuredItem) visibleItems.get(maxCrossAxis7)).getMainAxisSizeWithSpacings();
                if (visibleItemsScrollOffset2 == 0 || size2 > visibleItemsScrollOffset2) {
                    scrollDelta4 = scrollDelta2;
                    break;
                }
                scrollDelta4 = scrollDelta2;
                if (maxCrossAxis7 == CollectionsKt.getLastIndex(visibleItems)) {
                    break;
                }
                visibleItemsScrollOffset2 -= size2;
                firstItem3 = (LazyListMeasuredItem) visibleItems.get(maxCrossAxis7 + 1);
                maxCrossAxis7++;
                visibleItems3 = visibleItems;
                currentFirstItemScrollOffset11 = i2;
                scrollDelta2 = scrollDelta4;
                scrollBackAmount2 = scrollBackAmount;
            }
            currentFirstItemScrollOffset3 = visibleItemsScrollOffset2;
            firstItem = firstItem3;
        } else {
            scrollDelta4 = scrollDelta2;
            scrollBackAmount = scrollBackAmount2;
            firstItem = firstItem3;
            visibleItems = visibleItems3;
            currentFirstItemScrollOffset3 = currentFirstItemScrollOffset2;
        }
        List<LazyListMeasuredItem> listCreateItemsBeforeList = createItemsBeforeList(maxCrossAxis, measuredItemProvider, beyondBoundsItemCount, list2);
        List<LazyListMeasuredItem> list5 = listCreateItemsBeforeList;
        int size3 = list5.size();
        int maxCrossAxis8 = currentMainAxisOffset;
        int index$iv = 0;
        while (index$iv < size3) {
            Object item$iv = list5.get(index$iv);
            maxCrossAxis8 = Math.max(maxCrossAxis8, ((LazyListMeasuredItem) item$iv).getCrossAxisSize());
            index$iv++;
            list5 = list5;
        }
        int maxCrossAxis9 = maxCrossAxis8;
        LazyListMeasuredItem firstItem4 = firstItem;
        int currentMainAxisOffset9 = scrollDelta3;
        int index2 = currentMainAxisOffset5;
        List<LazyListMeasuredItem> listCreateItemsAfterList = createItemsAfterList(visibleItems, measuredItemProvider, itemsCount, beyondBoundsItemCount, list2, consumedScroll, isLookingAhead, postLookaheadLayoutInfo);
        List<LazyListMeasuredItem> list6 = listCreateItemsAfterList;
        int index$iv2 = 0;
        int size4 = list6.size();
        int maxCrossAxis10 = maxCrossAxis9;
        while (index$iv2 < size4) {
            Object item$iv2 = list6.get(index$iv2);
            maxCrossAxis10 = Math.max(maxCrossAxis10, ((LazyListMeasuredItem) item$iv2).getCrossAxisSize());
            index$iv2++;
            list6 = list6;
        }
        boolean noExtraItems = Intrinsics.areEqual(firstItem4, visibleItems.first()) && listCreateItemsBeforeList.isEmpty() && listCreateItemsAfterList.isEmpty();
        int layoutWidth2 = ConstraintsKt.m6986constrainWidthK40F9xA(constraints, isVertical ? maxCrossAxis10 : currentMainAxisOffset9);
        int layoutHeight4 = ConstraintsKt.m6985constrainHeightK40F9xA(constraints, isVertical ? currentMainAxisOffset9 : maxCrossAxis10);
        final List<LazyListMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(visibleItems, listCreateItemsBeforeList, listCreateItemsAfterList, layoutWidth2, layoutHeight4, currentMainAxisOffset9, maxOffset7, visibleItemsScrollOffset, isVertical, verticalArrangement, horizontalArrangement, reverseLayout, density);
        lazyLayoutItemAnimator.onMeasured((int) consumedScroll, layoutWidth2, layoutHeight4, listCalculateItemsOffsets, measuredItemProvider.getKeyIndexMap(), measuredItemProvider, isVertical, isLookingAhead, 1, hasLookaheadPassOccurred, currentFirstItemScrollOffset3, currentMainAxisOffset9, coroutineScope, graphicsContext);
        if (!isLookingAhead) {
            long disappearingItemsSize2 = lazyLayoutItemAnimator.m1199getMinSizeToFitDisappearingItemsYbymL2g();
            if (!IntSize.m7184equalsimpl0(disappearingItemsSize2, IntSize.INSTANCE.m7191getZeroYbymL2g())) {
                int oldMainAxisSize2 = isVertical ? layoutHeight4 : layoutWidth2;
                int layoutWidth3 = ConstraintsKt.m6986constrainWidthK40F9xA(constraints, Math.max(layoutWidth2, IntSize.m7186getWidthimpl(disappearingItemsSize2)));
                int layoutHeight5 = ConstraintsKt.m6985constrainHeightK40F9xA(constraints, Math.max(layoutHeight4, IntSize.m7185getHeightimpl(disappearingItemsSize2)));
                if (isVertical) {
                }
                if (newMainAxisSize != oldMainAxisSize2) {
                    newMainAxisSize = layoutWidth3;
                    List<LazyListMeasuredItem> list7 = listCalculateItemsOffsets;
                    int layoutWidth4 = list7.size();
                    int layoutHeight6 = 0;
                    while (layoutHeight6 < layoutWidth4) {
                        newMainAxisSize = layoutHeight5;
                        int i3 = layoutWidth4;
                        List<LazyListMeasuredItem> list8 = list7;
                        Object item$iv3 = list8.get(layoutHeight6);
                        ((LazyListMeasuredItem) item$iv3).updateMainAxisLayoutSize(newMainAxisSize);
                        layoutHeight6++;
                        layoutWidth4 = i3;
                        list7 = list8;
                    }
                    newMainAxisSize = layoutHeight5;
                } else {
                    newMainAxisSize = layoutWidth3;
                    newMainAxisSize = layoutHeight5;
                }
                oldMainAxisSize = layoutWidth3;
                layoutHeight = layoutHeight5;
            }
            if (list.isEmpty()) {
                headerItem = null;
            } else {
                headerItem = LazyListHeadersKt.findOrComposeLazyListHeader(listCalculateItemsOffsets, measuredItemProvider, list, beforeContentPadding, oldMainAxisSize, layoutHeight);
            }
            if (index2 >= itemsCount) {
                maxOffset = maxOffset7;
                currentMainAxisOffset2 = currentMainAxisOffset9;
                if (currentMainAxisOffset2 > maxOffset) {
                    z = false;
                }
                MeasureResult measureResultInvoke = function3.invoke(Integer.valueOf(oldMainAxisSize), Integer.valueOf(layoutHeight), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$8
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
                    public final void invoke2(Placeable.PlacementScope $this$invoke) {
                        List<LazyListMeasuredItem> list9 = listCalculateItemsOffsets;
                        LazyListMeasuredItem lazyListMeasuredItem2 = headerItem;
                        boolean z2 = isLookingAhead;
                        int size5 = list9.size();
                        for (int index$iv3 = 0; index$iv3 < size5; index$iv3++) {
                            Object item$iv4 = list9.get(index$iv3);
                            LazyListMeasuredItem it2 = (LazyListMeasuredItem) item$iv4;
                            if (it2 != lazyListMeasuredItem2) {
                                it2.place($this$invoke, z2);
                            }
                        }
                        LazyListMeasuredItem lazyListMeasuredItem3 = headerItem;
                        if (lazyListMeasuredItem3 != null) {
                            lazyListMeasuredItem3.place($this$invoke, isLookingAhead);
                        }
                        ObservableScopeInvalidator.m1208attachToScopeimpl(mutableState);
                    }
                });
                if (noExtraItems) {
                    firstItem2 = firstItem4;
                    list4 = listCalculateItemsOffsets;
                } else {
                    target$iv = new ArrayList(listCalculateItemsOffsets.size());
                    list3 = listCalculateItemsOffsets;
                    size = list3.size();
                    layoutHeight2 = 0;
                    while (layoutHeight2 < size) {
                        lazyListMeasuredItem = list3.get(layoutHeight2);
                        int i4 = size;
                        List<LazyListMeasuredItem> list9 = list3;
                        it = lazyListMeasuredItem;
                        LazyListMeasuredItem firstItem5 = firstItem4;
                        if ((it.getIndex() < ((LazyListMeasuredItem) visibleItems.first()).getIndex() && it.getIndex() <= ((LazyListMeasuredItem) visibleItems.last()).getIndex()) || it == headerItem) {
                            target$iv.add(lazyListMeasuredItem);
                        }
                        layoutHeight2++;
                        list3 = list9;
                        size = i4;
                        firstItem4 = firstItem5;
                    }
                    firstItem2 = firstItem4;
                    list4 = target$iv;
                }
                int i5 = -beforeContentPadding;
                int i6 = maxOffset + afterContentPadding;
                if (isVertical) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                return new LazyListMeasureResult(firstItem2, currentFirstItemScrollOffset3, z, consumedScroll, measureResultInvoke, scrollBackAmount, remeasureNeeded2, coroutineScope, density, measuredItemProvider.getChildConstraints(), list4, i5, i6, itemsCount, reverseLayout, orientation, afterContentPadding, spaceBetweenItems, null);
            }
            maxOffset = maxOffset7;
            currentMainAxisOffset2 = currentMainAxisOffset9;
            z = true;
            MeasureResult measureResultInvoke2 = function3.invoke(Integer.valueOf(oldMainAxisSize), Integer.valueOf(layoutHeight), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$8
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
                public final void invoke2(Placeable.PlacementScope $this$invoke) {
                    List<LazyListMeasuredItem> list10 = listCalculateItemsOffsets;
                    LazyListMeasuredItem lazyListMeasuredItem2 = headerItem;
                    boolean z2 = isLookingAhead;
                    int size5 = list10.size();
                    for (int index$iv3 = 0; index$iv3 < size5; index$iv3++) {
                        Object item$iv4 = list10.get(index$iv3);
                        LazyListMeasuredItem it2 = (LazyListMeasuredItem) item$iv4;
                        if (it2 != lazyListMeasuredItem2) {
                            it2.place($this$invoke, z2);
                        }
                    }
                    LazyListMeasuredItem lazyListMeasuredItem3 = headerItem;
                    if (lazyListMeasuredItem3 != null) {
                        lazyListMeasuredItem3.place($this$invoke, isLookingAhead);
                    }
                    ObservableScopeInvalidator.m1208attachToScopeimpl(mutableState);
                }
            });
            if (noExtraItems) {
                firstItem2 = firstItem4;
                list4 = listCalculateItemsOffsets;
            } else {
                target$iv = new ArrayList(listCalculateItemsOffsets.size());
                list3 = listCalculateItemsOffsets;
                size = list3.size();
                layoutHeight2 = 0;
                while (layoutHeight2 < size) {
                    lazyListMeasuredItem = list3.get(layoutHeight2);
                    int i7 = size;
                    List<LazyListMeasuredItem> list10 = list3;
                    it = lazyListMeasuredItem;
                    LazyListMeasuredItem firstItem6 = firstItem4;
                    if ((it.getIndex() < ((LazyListMeasuredItem) visibleItems.first()).getIndex() && it.getIndex() <= ((LazyListMeasuredItem) visibleItems.last()).getIndex()) || it == headerItem) {
                        target$iv.add(lazyListMeasuredItem);
                    }
                    layoutHeight2++;
                    list3 = list10;
                    size = i7;
                    firstItem4 = firstItem6;
                }
                firstItem2 = firstItem4;
                list4 = target$iv;
            }
            int i8 = -beforeContentPadding;
            int i9 = maxOffset + afterContentPadding;
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            return new LazyListMeasureResult(firstItem2, currentFirstItemScrollOffset3, z, consumedScroll, measureResultInvoke2, scrollBackAmount, remeasureNeeded2, coroutineScope, density, measuredItemProvider.getChildConstraints(), list4, i8, i9, itemsCount, reverseLayout, orientation, afterContentPadding, spaceBetweenItems, null);
        }
        oldMainAxisSize = layoutWidth2;
        layoutHeight = layoutHeight4;
        if (list.isEmpty()) {
            headerItem = LazyListHeadersKt.findOrComposeLazyListHeader(listCalculateItemsOffsets, measuredItemProvider, list, beforeContentPadding, oldMainAxisSize, layoutHeight);
        } else {
            headerItem = null;
        }
        if (index2 >= itemsCount) {
            maxOffset = maxOffset7;
            currentMainAxisOffset2 = currentMainAxisOffset9;
            if (currentMainAxisOffset2 > maxOffset) {
                z = false;
            }
            MeasureResult measureResultInvoke3 = function3.invoke(Integer.valueOf(oldMainAxisSize), Integer.valueOf(layoutHeight), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$8
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
                public final void invoke2(Placeable.PlacementScope $this$invoke) {
                    List<LazyListMeasuredItem> list11 = listCalculateItemsOffsets;
                    LazyListMeasuredItem lazyListMeasuredItem2 = headerItem;
                    boolean z2 = isLookingAhead;
                    int size5 = list11.size();
                    for (int index$iv3 = 0; index$iv3 < size5; index$iv3++) {
                        Object item$iv4 = list11.get(index$iv3);
                        LazyListMeasuredItem it2 = (LazyListMeasuredItem) item$iv4;
                        if (it2 != lazyListMeasuredItem2) {
                            it2.place($this$invoke, z2);
                        }
                    }
                    LazyListMeasuredItem lazyListMeasuredItem3 = headerItem;
                    if (lazyListMeasuredItem3 != null) {
                        lazyListMeasuredItem3.place($this$invoke, isLookingAhead);
                    }
                    ObservableScopeInvalidator.m1208attachToScopeimpl(mutableState);
                }
            });
            if (noExtraItems) {
                firstItem2 = firstItem4;
                list4 = listCalculateItemsOffsets;
            } else {
                target$iv = new ArrayList(listCalculateItemsOffsets.size());
                list3 = listCalculateItemsOffsets;
                size = list3.size();
                layoutHeight2 = 0;
                while (layoutHeight2 < size) {
                    lazyListMeasuredItem = list3.get(layoutHeight2);
                    int i10 = size;
                    List<LazyListMeasuredItem> list11 = list3;
                    it = lazyListMeasuredItem;
                    LazyListMeasuredItem firstItem7 = firstItem4;
                    if ((it.getIndex() < ((LazyListMeasuredItem) visibleItems.first()).getIndex() && it.getIndex() <= ((LazyListMeasuredItem) visibleItems.last()).getIndex()) || it == headerItem) {
                        target$iv.add(lazyListMeasuredItem);
                    }
                    layoutHeight2++;
                    list3 = list11;
                    size = i10;
                    firstItem4 = firstItem7;
                }
                firstItem2 = firstItem4;
                list4 = target$iv;
            }
            int i11 = -beforeContentPadding;
            int i12 = maxOffset + afterContentPadding;
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            return new LazyListMeasureResult(firstItem2, currentFirstItemScrollOffset3, z, consumedScroll, measureResultInvoke3, scrollBackAmount, remeasureNeeded2, coroutineScope, density, measuredItemProvider.getChildConstraints(), list4, i11, i12, itemsCount, reverseLayout, orientation, afterContentPadding, spaceBetweenItems, null);
        }
        maxOffset = maxOffset7;
        currentMainAxisOffset2 = currentMainAxisOffset9;
        z = true;
        MeasureResult measureResultInvoke4 = function3.invoke(Integer.valueOf(oldMainAxisSize), Integer.valueOf(layoutHeight), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$8
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
            public final void invoke2(Placeable.PlacementScope $this$invoke) {
                List<LazyListMeasuredItem> list12 = listCalculateItemsOffsets;
                LazyListMeasuredItem lazyListMeasuredItem2 = headerItem;
                boolean z2 = isLookingAhead;
                int size5 = list12.size();
                for (int index$iv3 = 0; index$iv3 < size5; index$iv3++) {
                    Object item$iv4 = list12.get(index$iv3);
                    LazyListMeasuredItem it2 = (LazyListMeasuredItem) item$iv4;
                    if (it2 != lazyListMeasuredItem2) {
                        it2.place($this$invoke, z2);
                    }
                }
                LazyListMeasuredItem lazyListMeasuredItem3 = headerItem;
                if (lazyListMeasuredItem3 != null) {
                    lazyListMeasuredItem3.place($this$invoke, isLookingAhead);
                }
                ObservableScopeInvalidator.m1208attachToScopeimpl(mutableState);
            }
        });
        if (noExtraItems) {
            firstItem2 = firstItem4;
            list4 = listCalculateItemsOffsets;
        } else {
            target$iv = new ArrayList(listCalculateItemsOffsets.size());
            list3 = listCalculateItemsOffsets;
            size = list3.size();
            layoutHeight2 = 0;
            while (layoutHeight2 < size) {
                lazyListMeasuredItem = list3.get(layoutHeight2);
                int i13 = size;
                List<LazyListMeasuredItem> list12 = list3;
                it = lazyListMeasuredItem;
                LazyListMeasuredItem firstItem8 = firstItem4;
                if ((it.getIndex() < ((LazyListMeasuredItem) visibleItems.first()).getIndex() && it.getIndex() <= ((LazyListMeasuredItem) visibleItems.last()).getIndex()) || it == headerItem) {
                    target$iv.add(lazyListMeasuredItem);
                }
                layoutHeight2++;
                list3 = list12;
                size = i13;
                firstItem4 = firstItem8;
            }
            firstItem2 = firstItem4;
            list4 = target$iv;
        }
        int i14 = -beforeContentPadding;
        int i15 = maxOffset + afterContentPadding;
        if (isVertical) {
            orientation = Orientation.Vertical;
        } else {
            orientation = Orientation.Horizontal;
        }
        return new LazyListMeasureResult(firstItem2, currentFirstItemScrollOffset3, z, consumedScroll, measureResultInvoke4, scrollBackAmount, remeasureNeeded2, coroutineScope, density, measuredItemProvider.getChildConstraints(), list4, i14, i15, itemsCount, reverseLayout, orientation, afterContentPadding, spaceBetweenItems, null);
    }

    private static final List<LazyListMeasuredItem> createItemsAfterList(List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider measuredItemProvider, int itemsCount, int beyondBoundsItemCount, List<Integer> list2, float consumedScroll, boolean isLookingAhead, LazyListLayoutInfo lastPostLookaheadLayoutInfo) {
        LazyListMeasuredItem lazyListMeasuredItem;
        Object it$iv;
        Object it$iv2;
        int i;
        int iMin;
        LazyListMeasuredItem lazyListMeasuredItem2;
        Object it$iv3;
        List list3 = null;
        int end = Math.min(((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + beyondBoundsItemCount, itemsCount - 1);
        int i2 = ((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + 1;
        if (i2 <= end) {
            while (true) {
                if (list3 == null) {
                    Object list4 = new ArrayList();
                    list3 = (List) list4;
                }
                list3.add(LazyListMeasuredItemProvider.m1147getAndMeasure0kLqBqw$default(measuredItemProvider, i2, 0L, 2, null));
                if (i2 == end) {
                    break;
                }
                i2++;
            }
        }
        if (isLookingAhead && lastPostLookaheadLayoutInfo != null && !lastPostLookaheadLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            List<LazyListItemInfo> visibleItemsInfo = lastPostLookaheadLayoutInfo.getVisibleItemsInfo();
            LazyListItemInfo found = null;
            for (int i3 = visibleItemsInfo.size() - 1; -1 < i3; i3--) {
                if (visibleItemsInfo.get(i3).getIndex() > end && (i3 == 0 || visibleItemsInfo.get(i3 - 1).getIndex() <= end)) {
                    LazyListItemInfo found2 = visibleItemsInfo.get(i3);
                    found = found2;
                    break;
                }
            }
            LazyListItemInfo firstItem = found;
            LazyListItemInfo lastVisibleItem = (LazyListItemInfo) CollectionsKt.last((List) lastPostLookaheadLayoutInfo.getVisibleItemsInfo());
            if (firstItem != null && (i = firstItem.getIndex()) <= (iMin = Math.min(lastVisibleItem.getIndex(), itemsCount - 1))) {
                while (true) {
                    if (list3 != null) {
                        List $this$fastFirstOrNull$iv = list3;
                        int index$iv$iv = 0;
                        int size = $this$fastFirstOrNull$iv.size();
                        while (true) {
                            if (index$iv$iv < size) {
                                Object item$iv$iv = $this$fastFirstOrNull$iv.get(index$iv$iv);
                                it$iv3 = item$iv$iv;
                                if (((LazyListMeasuredItem) it$iv3).getIndex() == i) {
                                    break;
                                }
                                index$iv$iv++;
                            } else {
                                it$iv3 = null;
                                break;
                            }
                        }
                        lazyListMeasuredItem2 = (LazyListMeasuredItem) it$iv3;
                    } else {
                        lazyListMeasuredItem2 = null;
                    }
                    if (lazyListMeasuredItem2 == null) {
                        if (list3 == null) {
                            Object list5 = new ArrayList();
                            list3 = (List) list5;
                        }
                        list3.add(LazyListMeasuredItemProvider.m1147getAndMeasure0kLqBqw$default(measuredItemProvider, i, 0L, 2, null));
                    }
                    if (i == iMin) {
                        break;
                    }
                    i++;
                }
            }
            float additionalOffset = ((lastPostLookaheadLayoutInfo.getViewportEndOffset() - lastVisibleItem.getOffset()) - lastVisibleItem.getSize()) - consumedScroll;
            if (additionalOffset > 0.0f) {
                int totalOffset = 0;
                int index = lastVisibleItem.getIndex() + 1;
                while (index < itemsCount && totalOffset < additionalOffset) {
                    if (index > end) {
                        if (list3 == null) {
                            lazyListMeasuredItem = null;
                        } else {
                            List $this$fastFirstOrNull$iv2 = list3;
                            int index$iv$iv2 = 0;
                            int size2 = $this$fastFirstOrNull$iv2.size();
                            while (true) {
                                if (index$iv$iv2 < size2) {
                                    Object item$iv$iv2 = $this$fastFirstOrNull$iv2.get(index$iv$iv2);
                                    it$iv = item$iv$iv2;
                                    if (((LazyListMeasuredItem) it$iv).getIndex() == index) {
                                        break;
                                    }
                                    index$iv$iv2++;
                                } else {
                                    it$iv = null;
                                    break;
                                }
                            }
                            lazyListMeasuredItem = (LazyListMeasuredItem) it$iv;
                        }
                    } else {
                        int index$iv$iv3 = 0;
                        int size3 = list.size();
                        while (true) {
                            if (index$iv$iv3 < size3) {
                                Object item$iv$iv3 = list.get(index$iv$iv3);
                                it$iv2 = item$iv$iv3;
                                if (((LazyListMeasuredItem) it$iv2).getIndex() == index) {
                                    break;
                                }
                                index$iv$iv3++;
                            } else {
                                it$iv2 = null;
                                break;
                            }
                        }
                        lazyListMeasuredItem = (LazyListMeasuredItem) it$iv2;
                    }
                    LazyListMeasuredItem item = lazyListMeasuredItem;
                    if (item != null) {
                        index++;
                        totalOffset += item.getMainAxisSizeWithSpacings();
                    } else {
                        if (list3 == null) {
                            Object list6 = new ArrayList();
                            list3 = (List) list6;
                        }
                        list3.add(LazyListMeasuredItemProvider.m1147getAndMeasure0kLqBqw$default(measuredItemProvider, index, 0L, 2, null));
                        index++;
                        totalOffset += ((LazyListMeasuredItem) CollectionsKt.last(list3)).getMainAxisSizeWithSpacings();
                    }
                }
            }
        }
        if (list3 != null) {
            List it = list3;
            if (((LazyListMeasuredItem) CollectionsKt.last(it)).getIndex() > end) {
                end = ((LazyListMeasuredItem) CollectionsKt.last(it)).getIndex();
            }
        }
        int size4 = list2.size();
        for (int index$iv = 0; index$iv < size4; index$iv++) {
            Object item$iv = list2.get(index$iv);
            int index2 = ((Number) item$iv).intValue();
            if (index2 > end) {
                if (list3 == null) {
                    List list7 = new ArrayList();
                    list3 = list7;
                }
                list3.add(LazyListMeasuredItemProvider.m1147getAndMeasure0kLqBqw$default(measuredItemProvider, index2, 0L, 2, null));
            }
        }
        return list3 == null ? CollectionsKt.emptyList() : list3;
    }

    private static final List<LazyListMeasuredItem> createItemsBeforeList(int currentFirstItemIndex, LazyListMeasuredItemProvider measuredItemProvider, int beyondBoundsItemCount, List<Integer> list) {
        ArrayList arrayList = null;
        int start = Math.max(0, currentFirstItemIndex - beyondBoundsItemCount);
        int i = currentFirstItemIndex - 1;
        if (start <= i) {
            int i2 = i;
            while (true) {
                if (arrayList == null) {
                    Object list2 = new ArrayList();
                    arrayList = (List) list2;
                }
                arrayList.add(LazyListMeasuredItemProvider.m1147getAndMeasure0kLqBqw$default(measuredItemProvider, i2, 0L, 2, null));
                if (i2 == start) {
                    break;
                }
                i2--;
            }
        }
        int size = list.size() - 1;
        if (size >= 0) {
            do {
                int index$iv = size;
                size--;
                Object item$iv = list.get(index$iv);
                int index = ((Number) item$iv).intValue();
                if (index < start) {
                    if (arrayList == null) {
                        Object list3 = new ArrayList();
                        arrayList = (List) list3;
                    }
                    arrayList.add(LazyListMeasuredItemProvider.m1147getAndMeasure0kLqBqw$default(measuredItemProvider, index, 0L, 2, null));
                }
            } while (size >= 0);
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> calculateItemsOffsets(List<LazyListMeasuredItem> list, List<LazyListMeasuredItem> list2, List<LazyListMeasuredItem> list3, int layoutWidth, int layoutHeight, int finalMainAxisOffset, int maxOffset, int itemsScrollOffset, boolean isVertical, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density) {
        int[] offsets;
        int size;
        List<LazyListMeasuredItem> list4 = list;
        int mainAxisLayoutSize = isVertical ? layoutHeight : layoutWidth;
        boolean hasSpareSpace = finalMainAxisOffset < Math.min(mainAxisLayoutSize, maxOffset);
        if (hasSpareSpace) {
            if (!(itemsScrollOffset == 0)) {
                throw new IllegalStateException("non-zero itemsScrollOffset".toString());
            }
        }
        ArrayList positionedItems = new ArrayList(list.size() + list2.size() + list3.size());
        if (hasSpareSpace) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                throw new IllegalArgumentException("no extra items".toString());
            }
            int itemsCount = list.size();
            int[] sizes = new int[itemsCount];
            for (int i = 0; i < itemsCount; i++) {
                sizes[i] = list4.get(calculateItemsOffsets$reverseAware(i, reverseLayout, itemsCount)).getSize();
            }
            int[] offsets2 = new int[itemsCount];
            for (int i2 = 0; i2 < itemsCount; i2++) {
                offsets2[i2] = 0;
            }
            if (isVertical) {
                if (verticalArrangement != null) {
                    verticalArrangement.arrange(density, mainAxisLayoutSize, sizes, offsets2);
                    offsets = offsets2;
                } else {
                    throw new IllegalArgumentException("null verticalArrangement when isVertical == true".toString());
                }
            } else if (horizontalArrangement != null) {
                offsets = offsets2;
                horizontalArrangement.arrange(density, mainAxisLayoutSize, sizes, LayoutDirection.Ltr, offsets);
            } else {
                throw new IllegalArgumentException("null horizontalArrangement when isVertical == false".toString());
            }
            IntRange reverseAwareOffsetIndices = ArraysKt.getIndices(offsets);
            if (reverseLayout) {
                reverseAwareOffsetIndices = RangesKt.reversed(reverseAwareOffsetIndices);
            }
            int index = reverseAwareOffsetIndices.getFirst();
            int last = reverseAwareOffsetIndices.getLast();
            int step = reverseAwareOffsetIndices.getStep();
            if ((step > 0 && index <= last) || (step < 0 && last <= index)) {
                while (true) {
                    int absoluteOffset = offsets[index];
                    LazyListMeasuredItem item = list4.get(calculateItemsOffsets$reverseAware(index, reverseLayout, itemsCount));
                    if (reverseLayout) {
                        size = (mainAxisLayoutSize - absoluteOffset) - item.getSize();
                    } else {
                        size = absoluteOffset;
                    }
                    int relativeOffset = size;
                    item.position(relativeOffset, layoutWidth, layoutHeight);
                    positionedItems.add(item);
                    if (index == last) {
                        break;
                    }
                    index += step;
                    list4 = list;
                }
            }
        } else {
            int currentMainAxis = itemsScrollOffset;
            int size2 = list2.size();
            for (int index$iv = 0; index$iv < size2; index$iv++) {
                Object item$iv = list2.get(index$iv);
                LazyListMeasuredItem it = (LazyListMeasuredItem) item$iv;
                currentMainAxis -= it.getMainAxisSizeWithSpacings();
                it.position(currentMainAxis, layoutWidth, layoutHeight);
                positionedItems.add(it);
            }
            int currentMainAxis2 = itemsScrollOffset;
            int size3 = list.size();
            for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                Object item$iv2 = list.get(index$iv2);
                LazyListMeasuredItem it2 = (LazyListMeasuredItem) item$iv2;
                it2.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedItems.add(it2);
                currentMainAxis2 += it2.getMainAxisSizeWithSpacings();
            }
            int size4 = list3.size();
            for (int index$iv3 = 0; index$iv3 < size4; index$iv3++) {
                Object item$iv3 = list3.get(index$iv3);
                LazyListMeasuredItem it3 = (LazyListMeasuredItem) item$iv3;
                it3.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedItems.add(it3);
                currentMainAxis2 += it3.getMainAxisSizeWithSpacings();
            }
        }
        return positionedItems;
    }

    private static final int calculateItemsOffsets$reverseAware(int $this$calculateItemsOffsets_u24reverseAware, boolean $reverseLayout, int itemsCount) {
        return !$reverseLayout ? $this$calculateItemsOffsets_u24reverseAware : (itemsCount - $this$calculateItemsOffsets_u24reverseAware) - 1;
    }
}
