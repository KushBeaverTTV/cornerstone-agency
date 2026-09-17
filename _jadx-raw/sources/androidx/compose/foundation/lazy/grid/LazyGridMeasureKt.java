package androidx.compose.foundation.lazy.grid;

import androidx.autofill.HintConstants;
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
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\u001aA\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\nH\u0083\b\u001a\u008c\u0001\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001a¸\u0002\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00020-2\u0006\u0010.\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020423\u00105\u001a/\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020+090\u00010\n2/\u0010:\u001a+\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020=0\n¢\u0006\u0002\b>\u0012\u0004\u0012\u00020?0;H\u0000ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a+\u0010B\u001a\u00020=\"\u0004\b\u0000\u0010C*\b\u0012\u0004\u0012\u0002HC0\r2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002HC0EH\u0002¢\u0006\u0002\u0010F\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006G"}, d2 = {"calculateExtraItems", "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "pinnedItems", "", "measuredItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "filter", "Lkotlin/Function1;", "", "calculateItemsOffsets", "", "lines", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "isVertical", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "measureLazyGrid", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "itemsCount", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "firstVisibleLineScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "slotsPerLine", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "prefetchInfoRetriever", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "line", "Lkotlin/Pair;", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyGrid-OZKpZRA", "(ILandroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "addAllFromArray", "T", "arr", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridMeasureKt {
    /* JADX WARN: Code duplicated, block: B:184:0x0536  */
    /* JADX WARN: Code duplicated, block: B:186:0x0557  */
    /* JADX WARN: Code duplicated, block: B:188:0x0575 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:190:0x0579  */
    /* JADX WARN: Code duplicated, block: B:192:0x057d  */
    /* JADX WARN: Code duplicated, block: B:197:0x059f  */
    /* JADX WARN: Code duplicated, block: B:198:0x05a2  */
    /* JADX WARN: Code duplicated, block: B:231:0x0583 A[SYNTHETIC] */
    /* JADX INFO: renamed from: measureLazyGrid-OZKpZRA, reason: not valid java name */
    public static final LazyGridMeasureResult m1175measureLazyGridOZKpZRA(int itemsCount, LazyGridMeasuredLineProvider measuredLineProvider, LazyGridMeasuredItemProvider measuredItemProvider, int mainAxisAvailableSize, int beforeContentPadding, int afterContentPadding, int spaceBetweenLines, int firstVisibleLineIndex, int firstVisibleLineScrollOffset, float scrollToBeConsumed, long constraints, boolean isVertical, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density, LazyLayoutItemAnimator<LazyGridMeasuredItem> lazyLayoutItemAnimator, int slotsPerLine, List<Integer> list, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, Function1<? super Integer, ? extends List<Pair<Integer, Constraints>>> function1, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int currentFirstLineIndex;
        int currentFirstLineScrollOffset;
        int scrollDelta;
        int currentMainAxisOffset;
        int currentFirstLineScrollOffset2;
        LazyGridMeasuredLine firstLine;
        int layoutWidth;
        int layoutHeight;
        int layoutWidth2;
        int layoutHeight2;
        int maxOffset;
        boolean z;
        ArrayList target$iv;
        List<LazyGridMeasuredItem> list2;
        int size;
        int index$iv$iv;
        int currentMainAxisOffset2;
        List<LazyGridMeasuredItem> list3;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        int index;
        int currentMainAxisOffset3;
        boolean z2;
        Orientation orientation;
        int newMainAxisSize;
        List list4;
        int firstItemIndex;
        int index2;
        ArrayList arrayList;
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem2;
        int currentMainAxisOffset4;
        int maxMainAxis;
        if (!(beforeContentPadding >= 0)) {
            throw new IllegalArgumentException("negative beforeContentPadding".toString());
        }
        if (!(afterContentPadding >= 0)) {
            throw new IllegalArgumentException("negative afterContentPadding".toString());
        }
        if (itemsCount <= 0) {
            int layoutWidth3 = Constraints.m6971getMinWidthimpl(constraints);
            int layoutHeight3 = Constraints.m6970getMinHeightimpl(constraints);
            lazyLayoutItemAnimator.onMeasured(0, layoutWidth3, layoutHeight3, new ArrayList(), measuredItemProvider.getKeyIndexMap(), measuredItemProvider, isVertical, false, slotsPerLine, false, 0, 0, coroutineScope, graphicsContext);
            long disappearingItemsSize = lazyLayoutItemAnimator.m1199getMinSizeToFitDisappearingItemsYbymL2g();
            if (!IntSize.m7184equalsimpl0(disappearingItemsSize, IntSize.INSTANCE.m7191getZeroYbymL2g())) {
                layoutWidth3 = ConstraintsKt.m6986constrainWidthK40F9xA(constraints, IntSize.m7186getWidthimpl(disappearingItemsSize));
                layoutHeight3 = ConstraintsKt.m6985constrainHeightK40F9xA(constraints, IntSize.m7185getHeightimpl(disappearingItemsSize));
            }
            return new LazyGridMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(layoutWidth3), Integer.valueOf(layoutHeight3), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope $this$invoke) {
                }
            }), false, coroutineScope, density, slotsPerLine, function1, CollectionsKt.emptyList(), -beforeContentPadding, mainAxisAvailableSize + afterContentPadding, 0, reverseLayout, isVertical ? Orientation.Vertical : Orientation.Horizontal, afterContentPadding, spaceBetweenLines);
        }
        int currentFirstLineIndex2 = firstVisibleLineIndex;
        int scrollDelta2 = Math.round(scrollToBeConsumed);
        int currentFirstLineScrollOffset3 = firstVisibleLineScrollOffset - scrollDelta2;
        if (currentFirstLineIndex2 == 0 && currentFirstLineScrollOffset3 < 0) {
            scrollDelta2 += currentFirstLineScrollOffset3;
            currentFirstLineScrollOffset3 = 0;
        }
        ArrayDeque visibleLines = new ArrayDeque();
        int currentMainAxisOffset5 = (-beforeContentPadding) + (spaceBetweenLines < 0 ? spaceBetweenLines : 0);
        int maxOffset2 = mainAxisAvailableSize;
        int currentFirstLineScrollOffset4 = currentFirstLineScrollOffset3 + currentMainAxisOffset5;
        while (currentFirstLineScrollOffset4 < 0 && currentFirstLineIndex2 > 0) {
            int previous = currentFirstLineIndex2 - 1;
            LazyGridMeasuredLine measuredLine = measuredLineProvider.getAndMeasure(previous);
            visibleLines.add(0, measuredLine);
            currentFirstLineScrollOffset4 += measuredLine.getMainAxisSizeWithSpacings();
            currentFirstLineIndex2 = previous;
        }
        if (currentFirstLineScrollOffset4 < currentMainAxisOffset5) {
            scrollDelta2 += currentFirstLineScrollOffset4;
            currentFirstLineScrollOffset4 = currentMainAxisOffset5;
        }
        int currentFirstLineScrollOffset5 = currentFirstLineScrollOffset4 - currentMainAxisOffset5;
        int index3 = currentFirstLineIndex2;
        int maxMainAxis2 = RangesKt.coerceAtLeast(maxOffset2 + afterContentPadding, 0);
        int currentMainAxisOffset6 = -currentFirstLineScrollOffset5;
        boolean remeasureNeeded = false;
        int indexInVisibleLines = 0;
        while (true) {
            currentFirstLineIndex2 = currentFirstLineIndex2;
            if (indexInVisibleLines >= visibleLines.size()) {
                break;
            }
            if (currentMainAxisOffset6 >= maxMainAxis2) {
                visibleLines.remove(indexInVisibleLines);
                remeasureNeeded = true;
            } else {
                index3++;
                currentMainAxisOffset6 += ((LazyGridMeasuredLine) visibleLines.get(indexInVisibleLines)).getMainAxisSizeWithSpacings();
                indexInVisibleLines++;
            }
        }
        boolean remeasureNeeded2 = remeasureNeeded;
        int currentFirstLineIndex3 = currentFirstLineIndex2;
        int i = currentMainAxisOffset6;
        int currentMainAxisOffset7 = index3;
        int index4 = i;
        while (true) {
            if (currentMainAxisOffset7 >= itemsCount) {
                currentFirstLineIndex = currentFirstLineIndex3;
                break;
            }
            if (index4 >= maxMainAxis2 && index4 > 0 && !visibleLines.isEmpty()) {
                currentFirstLineIndex = currentFirstLineIndex3;
                break;
            }
            currentFirstLineIndex = currentFirstLineIndex3;
            LazyGridMeasuredLine measuredLine2 = measuredLineProvider.getAndMeasure(currentMainAxisOffset7);
            if (measuredLine2.isEmpty()) {
                break;
            }
            int currentMainAxisOffset8 = index4 + measuredLine2.getMainAxisSizeWithSpacings();
            if (currentMainAxisOffset8 <= currentMainAxisOffset5) {
                currentMainAxisOffset4 = currentMainAxisOffset8;
                maxMainAxis = maxMainAxis2;
                if (((LazyGridMeasuredItem) ArraysKt.last(measuredLine2.getItems())).getIndex() != itemsCount - 1) {
                    currentFirstLineScrollOffset5 -= measuredLine2.getMainAxisSizeWithSpacings();
                    currentFirstLineIndex = currentMainAxisOffset7 + 1;
                    remeasureNeeded2 = true;
                }
                currentMainAxisOffset7++;
                currentFirstLineIndex3 = currentFirstLineIndex;
                maxMainAxis2 = maxMainAxis;
                index4 = currentMainAxisOffset4;
            } else {
                currentMainAxisOffset4 = currentMainAxisOffset8;
                maxMainAxis = maxMainAxis2;
            }
            visibleLines.add(measuredLine2);
            currentMainAxisOffset7++;
            currentFirstLineIndex3 = currentFirstLineIndex;
            maxMainAxis2 = maxMainAxis;
            index4 = currentMainAxisOffset4;
        }
        if (index4 >= maxOffset2) {
            maxOffset2 = maxOffset2;
            indexInVisibleLines = indexInVisibleLines;
            currentFirstLineScrollOffset = currentFirstLineScrollOffset5;
            scrollDelta = scrollDelta2;
            currentMainAxisOffset = index4;
        } else {
            int toScrollBack = maxOffset2 - index4;
            int currentFirstLineScrollOffset6 = currentFirstLineScrollOffset5 - toScrollBack;
            int currentMainAxisOffset9 = index4 + toScrollBack;
            while (currentFirstLineScrollOffset6 < beforeContentPadding && currentFirstLineIndex > 0) {
                int previousIndex = currentFirstLineIndex - 1;
                int maxOffset3 = maxOffset2;
                LazyGridMeasuredLine measuredLine3 = measuredLineProvider.getAndMeasure(previousIndex);
                visibleLines.add(0, measuredLine3);
                currentFirstLineScrollOffset6 += measuredLine3.getMainAxisSizeWithSpacings();
                currentFirstLineIndex = previousIndex;
                maxOffset2 = maxOffset3;
                indexInVisibleLines = indexInVisibleLines;
            }
            int scrollDelta3 = scrollDelta2 + toScrollBack;
            if (currentFirstLineScrollOffset6 >= 0) {
                currentFirstLineScrollOffset = currentFirstLineScrollOffset6;
                scrollDelta = scrollDelta3;
                currentMainAxisOffset = currentMainAxisOffset9;
            } else {
                currentFirstLineScrollOffset = 0;
                scrollDelta = scrollDelta3 + currentFirstLineScrollOffset6;
                currentMainAxisOffset = currentMainAxisOffset9 + currentFirstLineScrollOffset6;
            }
        }
        float consumedScroll = (MathKt.getSign(Math.round(scrollToBeConsumed)) == MathKt.getSign(scrollDelta) && Math.abs(Math.round(scrollToBeConsumed)) >= Math.abs(scrollDelta)) ? scrollDelta : scrollToBeConsumed;
        if (!(currentFirstLineScrollOffset >= 0)) {
            throw new IllegalArgumentException("negative initial offset".toString());
        }
        int visibleLinesScrollOffset = -currentFirstLineScrollOffset;
        LazyGridMeasuredLine firstLine2 = (LazyGridMeasuredLine) visibleLines.first();
        LazyGridMeasuredItem lazyGridMeasuredItem3 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(firstLine2.getItems());
        int firstItemIndex2 = lazyGridMeasuredItem3 != null ? lazyGridMeasuredItem3.getIndex() : 0;
        LazyGridMeasuredLine lazyGridMeasuredLine = (LazyGridMeasuredLine) visibleLines.lastOrNull();
        int lastItemIndex = (lazyGridMeasuredLine == null || (items = lazyGridMeasuredLine.getItems()) == null || (lazyGridMeasuredItem2 = (LazyGridMeasuredItem) ArraysKt.lastOrNull(items)) == null) ? 0 : lazyGridMeasuredItem2.getIndex();
        ArrayList arrayList2 = null;
        List<Integer> list5 = list;
        int visibleLinesScrollOffset2 = visibleLinesScrollOffset;
        int visibleLinesScrollOffset3 = list5.size();
        int index$iv$iv2 = currentFirstLineScrollOffset;
        int currentFirstLineScrollOffset7 = 0;
        while (currentFirstLineScrollOffset7 < visibleLinesScrollOffset3) {
            int i2 = visibleLinesScrollOffset3;
            List<Integer> list6 = list5;
            Object item$iv$iv = list6.get(currentFirstLineScrollOffset7);
            int index$iv = ((Number) item$iv$iv).intValue();
            int lastItemIndex2 = lastItemIndex;
            int it = (index$iv < 0 || index$iv >= firstItemIndex2) ? 0 : 1;
            if (it == 0) {
                firstItemIndex = firstItemIndex2;
                index2 = lastItemIndex2;
            } else {
                int span$iv = measuredLineProvider.spanOf(index$iv);
                long constraints$iv = measuredLineProvider.m1179childConstraintsJhjzzOo$foundation_release(0, span$iv);
                index2 = lastItemIndex2;
                firstItemIndex = firstItemIndex2;
                LazyGridMeasuredItem measuredItem$iv = measuredItemProvider.mo1148getAndMeasurehBUhpc(index$iv, 0, span$iv, constraints$iv);
                if (arrayList2 != null) {
                    arrayList = arrayList2;
                } else {
                    Object items$iv = new ArrayList();
                    arrayList = (List) items$iv;
                }
                arrayList.add(measuredItem$iv);
                arrayList2 = arrayList;
            }
            currentFirstLineScrollOffset7++;
            lastItemIndex = index2;
            currentMainAxisOffset = currentMainAxisOffset;
            list5 = list6;
            visibleLinesScrollOffset3 = i2;
            currentMainAxisOffset5 = currentMainAxisOffset5;
            currentMainAxisOffset7 = currentMainAxisOffset7;
            visibleLinesScrollOffset2 = visibleLinesScrollOffset2;
            consumedScroll = consumedScroll;
            maxOffset2 = maxOffset2;
            maxMainAxis2 = maxMainAxis2;
            scrollDelta = scrollDelta;
            firstItemIndex2 = firstItemIndex;
        }
        int firstItemIndex3 = firstItemIndex2;
        float consumedScroll2 = consumedScroll;
        int maxOffset4 = maxOffset2;
        int visibleLinesScrollOffset4 = visibleLinesScrollOffset2;
        int index5 = lastItemIndex;
        int firstItemIndex4 = currentMainAxisOffset;
        List extraItemsBefore = arrayList2 == null ? CollectionsKt.emptyList() : arrayList2;
        List<Integer> list7 = list;
        List extraItemsAfter = null;
        int index$iv$iv3 = 0;
        for (int size2 = list7.size(); index$iv$iv3 < size2; size2 = size2) {
            Object item$iv$iv2 = list7.get(index$iv$iv3);
            int index$iv2 = ((Number) item$iv$iv2).intValue();
            int it2 = (index5 + 1 > index$iv2 || index$iv2 >= itemsCount) ? 0 : 1;
            if (it2 != 0) {
                int span$iv2 = measuredLineProvider.spanOf(index$iv2);
                long constraints$iv2 = measuredLineProvider.m1179childConstraintsJhjzzOo$foundation_release(0, span$iv2);
                LazyGridMeasuredItem measuredItem$iv2 = measuredItemProvider.mo1148getAndMeasurehBUhpc(index$iv2, 0, span$iv2, constraints$iv2);
                if (extraItemsAfter != null) {
                    list4 = extraItemsAfter;
                } else {
                    Object items$iv2 = new ArrayList();
                    list4 = (List) items$iv2;
                }
                list4.add(measuredItem$iv2);
                extraItemsAfter = list4;
            }
            index$iv$iv3++;
            list7 = list7;
        }
        if (extraItemsAfter == null) {
            extraItemsAfter = CollectionsKt.emptyList();
        }
        if (beforeContentPadding > 0 || spaceBetweenLines < 0) {
            int size3 = visibleLines.size();
            int currentFirstLineScrollOffset8 = index$iv$iv2;
            for (int i3 = 0; i3 < size3; i3++) {
                int size4 = ((LazyGridMeasuredLine) visibleLines.get(i3)).getMainAxisSizeWithSpacings();
                if (currentFirstLineScrollOffset8 == 0 || size4 > currentFirstLineScrollOffset8 || i3 == CollectionsKt.getLastIndex(visibleLines)) {
                    break;
                }
                currentFirstLineScrollOffset8 -= size4;
                firstLine2 = (LazyGridMeasuredLine) visibleLines.get(i3 + 1);
            }
            currentFirstLineScrollOffset2 = currentFirstLineScrollOffset8;
            firstLine = firstLine2;
        } else {
            firstLine = firstLine2;
            currentFirstLineScrollOffset2 = index$iv$iv2;
        }
        if (isVertical) {
            layoutWidth = Constraints.m6969getMaxWidthimpl(constraints);
        } else {
            layoutWidth = ConstraintsKt.m6986constrainWidthK40F9xA(constraints, firstItemIndex4);
        }
        if (isVertical) {
            layoutHeight = ConstraintsKt.m6985constrainHeightK40F9xA(constraints, firstItemIndex4);
        } else {
            layoutHeight = Constraints.m6968getMaxHeightimpl(constraints);
        }
        final List<LazyGridMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(visibleLines, extraItemsBefore, extraItemsAfter, layoutWidth, layoutHeight, firstItemIndex4, maxOffset4, visibleLinesScrollOffset4, isVertical, verticalArrangement, horizontalArrangement, reverseLayout, density);
        lazyLayoutItemAnimator.onMeasured((int) consumedScroll2, layoutWidth, layoutHeight, listCalculateItemsOffsets, measuredItemProvider.getKeyIndexMap(), measuredItemProvider, isVertical, false, slotsPerLine, false, currentFirstLineScrollOffset2, firstItemIndex4, coroutineScope, graphicsContext);
        long disappearingItemsSize2 = lazyLayoutItemAnimator.m1199getMinSizeToFitDisappearingItemsYbymL2g();
        if (IntSize.m7184equalsimpl0(disappearingItemsSize2, IntSize.INSTANCE.m7191getZeroYbymL2g())) {
            layoutWidth2 = layoutWidth;
            layoutHeight2 = layoutHeight;
        } else {
            int oldMainAxisSize = isVertical ? layoutHeight : layoutWidth;
            int layoutWidth4 = ConstraintsKt.m6986constrainWidthK40F9xA(constraints, Math.max(layoutWidth, IntSize.m7186getWidthimpl(disappearingItemsSize2)));
            int layoutHeight4 = ConstraintsKt.m6985constrainHeightK40F9xA(constraints, Math.max(layoutHeight, IntSize.m7185getHeightimpl(disappearingItemsSize2)));
            if (isVertical) {
            }
            if (newMainAxisSize != oldMainAxisSize) {
                newMainAxisSize = layoutWidth4;
                layoutWidth2 = layoutWidth4;
                int layoutWidth5 = listCalculateItemsOffsets.size();
                layoutHeight2 = layoutHeight4;
                int layoutHeight5 = 0;
                while (layoutHeight5 < layoutWidth5) {
                    newMainAxisSize = layoutHeight4;
                    Object item$iv = listCalculateItemsOffsets.get(layoutHeight5);
                    int i4 = layoutWidth5;
                    LazyGridMeasuredItem it3 = (LazyGridMeasuredItem) item$iv;
                    it3.updateMainAxisLayoutSize(newMainAxisSize);
                    layoutHeight5++;
                    layoutWidth5 = i4;
                }
                newMainAxisSize = layoutHeight4;
            } else {
                newMainAxisSize = layoutWidth4;
                newMainAxisSize = layoutHeight4;
                layoutWidth2 = layoutWidth4;
                layoutHeight2 = layoutHeight4;
            }
        }
        if (index5 == itemsCount - 1) {
            maxOffset = maxOffset4;
            if (firstItemIndex4 <= maxOffset) {
                z = false;
            }
            MeasureResult measureResultInvoke = function3.invoke(Integer.valueOf(layoutWidth2), Integer.valueOf(layoutHeight2), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$6
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
                    List<LazyGridMeasuredItem> list8 = listCalculateItemsOffsets;
                    int size5 = list8.size();
                    for (int index$iv3 = 0; index$iv3 < size5; index$iv3++) {
                        Object item$iv2 = list8.get(index$iv3);
                        LazyGridMeasuredItem it4 = (LazyGridMeasuredItem) item$iv2;
                        it4.place($this$invoke);
                    }
                    ObservableScopeInvalidator.m1208attachToScopeimpl(mutableState);
                }
            });
            int i5 = -beforeContentPadding;
            int i6 = mainAxisAvailableSize + afterContentPadding;
            if (extraItemsBefore.isEmpty() || !extraItemsAfter.isEmpty()) {
                target$iv = new ArrayList(listCalculateItemsOffsets.size());
                list2 = listCalculateItemsOffsets;
                size = list2.size();
                index$iv$iv = 0;
                while (index$iv$iv < size) {
                    lazyGridMeasuredItem = list2.get(index$iv$iv);
                    int i7 = size;
                    LazyGridMeasuredItem it4 = lazyGridMeasuredItem;
                    List<LazyGridMeasuredItem> list8 = list2;
                    index = it4.getIndex();
                    int currentMainAxisOffset10 = firstItemIndex4;
                    currentMainAxisOffset3 = firstItemIndex3;
                    if (currentMainAxisOffset3 <= index || index > index5) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    if (z2) {
                        target$iv.add(lazyGridMeasuredItem);
                    }
                    index$iv$iv++;
                    firstItemIndex3 = currentMainAxisOffset3;
                    size = i7;
                    list2 = list8;
                    firstItemIndex4 = currentMainAxisOffset10;
                }
                currentMainAxisOffset2 = firstItemIndex3;
                list3 = target$iv;
            } else {
                list3 = listCalculateItemsOffsets;
                currentMainAxisOffset2 = firstItemIndex3;
            }
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            return new LazyGridMeasureResult(firstLine, currentFirstLineScrollOffset2, z, consumedScroll2, measureResultInvoke, remeasureNeeded2, coroutineScope, density, slotsPerLine, function1, list3, i5, i6, itemsCount, reverseLayout, orientation, afterContentPadding, spaceBetweenLines);
        }
        maxOffset = maxOffset4;
        z = true;
        MeasureResult measureResultInvoke2 = function3.invoke(Integer.valueOf(layoutWidth2), Integer.valueOf(layoutHeight2), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$6
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
                List<LazyGridMeasuredItem> list9 = listCalculateItemsOffsets;
                int size5 = list9.size();
                for (int index$iv3 = 0; index$iv3 < size5; index$iv3++) {
                    Object item$iv2 = list9.get(index$iv3);
                    LazyGridMeasuredItem it5 = (LazyGridMeasuredItem) item$iv2;
                    it5.place($this$invoke);
                }
                ObservableScopeInvalidator.m1208attachToScopeimpl(mutableState);
            }
        });
        int i8 = -beforeContentPadding;
        int i9 = mainAxisAvailableSize + afterContentPadding;
        if (extraItemsBefore.isEmpty()) {
            target$iv = new ArrayList(listCalculateItemsOffsets.size());
            list2 = listCalculateItemsOffsets;
            size = list2.size();
            index$iv$iv = 0;
            while (index$iv$iv < size) {
                lazyGridMeasuredItem = list2.get(index$iv$iv);
                int i10 = size;
                LazyGridMeasuredItem it5 = lazyGridMeasuredItem;
                List<LazyGridMeasuredItem> list9 = list2;
                index = it5.getIndex();
                int currentMainAxisOffset11 = firstItemIndex4;
                currentMainAxisOffset3 = firstItemIndex3;
                if (currentMainAxisOffset3 <= index) {
                    z2 = false;
                } else {
                    z2 = false;
                }
                if (z2) {
                    target$iv.add(lazyGridMeasuredItem);
                }
                index$iv$iv++;
                firstItemIndex3 = currentMainAxisOffset3;
                size = i10;
                list2 = list9;
                firstItemIndex4 = currentMainAxisOffset11;
            }
            currentMainAxisOffset2 = firstItemIndex3;
            list3 = target$iv;
        } else {
            target$iv = new ArrayList(listCalculateItemsOffsets.size());
            list2 = listCalculateItemsOffsets;
            size = list2.size();
            index$iv$iv = 0;
            while (index$iv$iv < size) {
                lazyGridMeasuredItem = list2.get(index$iv$iv);
                int i11 = size;
                LazyGridMeasuredItem it6 = lazyGridMeasuredItem;
                List<LazyGridMeasuredItem> list10 = list2;
                index = it6.getIndex();
                int currentMainAxisOffset12 = firstItemIndex4;
                currentMainAxisOffset3 = firstItemIndex3;
                if (currentMainAxisOffset3 <= index) {
                    z2 = false;
                } else {
                    z2 = false;
                }
                if (z2) {
                    target$iv.add(lazyGridMeasuredItem);
                }
                index$iv$iv++;
                firstItemIndex3 = currentMainAxisOffset3;
                size = i11;
                list2 = list10;
                firstItemIndex4 = currentMainAxisOffset12;
            }
            currentMainAxisOffset2 = firstItemIndex3;
            list3 = target$iv;
        }
        if (isVertical) {
            orientation = Orientation.Vertical;
        } else {
            orientation = Orientation.Horizontal;
        }
        return new LazyGridMeasureResult(firstLine, currentFirstLineScrollOffset2, z, consumedScroll2, measureResultInvoke2, remeasureNeeded2, coroutineScope, density, slotsPerLine, function1, list3, i8, i9, itemsCount, reverseLayout, orientation, afterContentPadding, spaceBetweenLines);
    }

    private static final List<LazyGridMeasuredItem> calculateExtraItems(List<Integer> list, LazyGridMeasuredItemProvider measuredItemProvider, LazyGridMeasuredLineProvider measuredLineProvider, Function1<? super Integer, Boolean> function1) {
        ArrayList arrayList = null;
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            int index = ((Number) item$iv).intValue();
            if (function1.invoke(Integer.valueOf(index)).booleanValue()) {
                int span = measuredLineProvider.spanOf(index);
                long constraints = measuredLineProvider.m1179childConstraintsJhjzzOo$foundation_release(0, span);
                LazyGridMeasuredItem measuredItem = measuredItemProvider.mo1148getAndMeasurehBUhpc(index, 0, span, constraints);
                if (arrayList == null) {
                    Object items = new ArrayList();
                    arrayList = (List) items;
                }
                arrayList.add(measuredItem);
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyGridMeasuredItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int layoutWidth, int layoutHeight, int finalMainAxisOffset, int maxOffset, int firstLineScrollOffset, boolean isVertical, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density) {
        int[] offsets;
        List<LazyGridMeasuredLine> list4 = list;
        boolean z = reverseLayout;
        int mainAxisLayoutSize = isVertical ? layoutHeight : layoutWidth;
        boolean hasSpareSpace = finalMainAxisOffset < Math.min(mainAxisLayoutSize, maxOffset);
        if (hasSpareSpace) {
            if (!(firstLineScrollOffset == 0)) {
                throw new IllegalStateException("non-zero firstLineScrollOffset".toString());
            }
        }
        int sum$iv = 0;
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            sum$iv += ((LazyGridMeasuredLine) item$iv$iv).getItems().length;
        }
        ArrayList positionedItems = new ArrayList(sum$iv);
        if (hasSpareSpace) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                throw new IllegalArgumentException("no items".toString());
            }
            int linesCount = list.size();
            int[] sizes = new int[linesCount];
            for (int i = 0; i < linesCount; i++) {
                sizes[i] = list4.get(calculateItemsOffsets$reverseAware(i, z, linesCount)).getMainAxisSize();
            }
            int[] offsets2 = new int[linesCount];
            for (int i2 = 0; i2 < linesCount; i2++) {
                offsets2[i2] = 0;
            }
            if (isVertical) {
                if (verticalArrangement == null) {
                    throw new IllegalArgumentException("null verticalArrangement".toString());
                }
                verticalArrangement.arrange(density, mainAxisLayoutSize, sizes, offsets2);
                offsets = offsets2;
            } else {
                if (horizontalArrangement == null) {
                    throw new IllegalArgumentException("null horizontalArrangement".toString());
                }
                offsets = offsets2;
                horizontalArrangement.arrange(density, mainAxisLayoutSize, sizes, LayoutDirection.Ltr, offsets);
            }
            IntRange reverseAwareOffsetIndices = ArraysKt.getIndices(offsets);
            if (z) {
                reverseAwareOffsetIndices = RangesKt.reversed(reverseAwareOffsetIndices);
            }
            int index = reverseAwareOffsetIndices.getFirst();
            int last = reverseAwareOffsetIndices.getLast();
            int step = reverseAwareOffsetIndices.getStep();
            if ((step > 0 && index <= last) || (step < 0 && last <= index)) {
                while (true) {
                    int absoluteOffset = offsets[index];
                    LazyGridMeasuredLine line = list4.get(calculateItemsOffsets$reverseAware(index, z, linesCount));
                    int relativeOffset = z ? (mainAxisLayoutSize - absoluteOffset) - line.getMainAxisSize() : absoluteOffset;
                    int mainAxisLayoutSize2 = mainAxisLayoutSize;
                    addAllFromArray(positionedItems, line.position(relativeOffset, layoutWidth, layoutHeight));
                    if (index == last) {
                        break;
                    }
                    index += step;
                    list4 = list;
                    z = reverseLayout;
                    mainAxisLayoutSize = mainAxisLayoutSize2;
                }
            }
        } else {
            int currentMainAxis = firstLineScrollOffset;
            int size2 = list2.size() - 1;
            if (size2 >= 0) {
                do {
                    int index$iv = size2;
                    size2--;
                    Object item$iv = list2.get(index$iv);
                    LazyGridMeasuredItem it = (LazyGridMeasuredItem) item$iv;
                    currentMainAxis -= it.getMainAxisSizeWithSpacings();
                    it.position(currentMainAxis, 0, layoutWidth, layoutHeight);
                    positionedItems.add(it);
                } while (size2 >= 0);
            }
            int currentMainAxis2 = firstLineScrollOffset;
            int size3 = list.size();
            for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                Object item$iv2 = list.get(index$iv2);
                LazyGridMeasuredLine it2 = (LazyGridMeasuredLine) item$iv2;
                addAllFromArray(positionedItems, it2.position(currentMainAxis2, layoutWidth, layoutHeight));
                currentMainAxis2 += it2.getMainAxisSizeWithSpacings();
            }
            int size4 = list3.size();
            for (int index$iv3 = 0; index$iv3 < size4; index$iv3++) {
                Object item$iv3 = list3.get(index$iv3);
                LazyGridMeasuredItem it3 = (LazyGridMeasuredItem) item$iv3;
                it3.position(currentMainAxis2, 0, layoutWidth, layoutHeight);
                positionedItems.add(it3);
                currentMainAxis2 += it3.getMainAxisSizeWithSpacings();
            }
        }
        return positionedItems;
    }

    private static final int calculateItemsOffsets$reverseAware(int $this$calculateItemsOffsets_u24reverseAware, boolean $reverseLayout, int linesCount) {
        return !$reverseLayout ? $this$calculateItemsOffsets_u24reverseAware : (linesCount - $this$calculateItemsOffsets_u24reverseAware) - 1;
    }

    private static final <T> void addAllFromArray(List<T> list, T[] tArr) {
        for (T t : tArr) {
            list.add(t);
        }
    }
}
