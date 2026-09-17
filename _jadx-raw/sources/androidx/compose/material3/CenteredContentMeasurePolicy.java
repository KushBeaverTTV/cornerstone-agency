package androidx.compose.material3;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ExpressiveNavigationBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/compose/material3/CenteredContentMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class CenteredContentMeasurePolicy implements MeasurePolicy {
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo391measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        int itemHeight;
        final List itemsPlaceables;
        int width = Constraints.m6969getMaxWidthimpl(constraints);
        int itemHeight2 = Constraints.m6970getMinHeightimpl(constraints);
        int itemsCount = list.size();
        if (itemsCount < 1) {
            return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, itemHeight2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.CenteredContentMeasurePolicy$measure$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope $this$layout) {
                }
            }, 4, null);
        }
        final Ref.IntRef barHorizontalPadding = new Ref.IntRef();
        List itemsPlaceables2 = null;
        if (Constraints.m6965getHasBoundedWidthimpl(constraints)) {
            int itemMaxWidth = width / itemsCount;
            barHorizontalPadding.element = ExpressiveNavigationBarKt.calculateCenteredContentHorizontalPadding(itemsCount, width);
            int itemMinWidth = (width - (barHorizontalPadding.element * 2)) / itemsCount;
            List<? extends Measurable> list2 = list;
            int index$iv = 0;
            int size = list2.size();
            while (index$iv < size) {
                Object item$iv = list2.get(index$iv);
                int measurableHeight = ((Measurable) item$iv).maxIntrinsicHeight(itemMinWidth);
                if (itemHeight2 < measurableHeight) {
                    itemHeight2 = RangesKt.coerceAtMost(measurableHeight, Constraints.m6968getMaxHeightimpl(constraints));
                }
                index$iv++;
                list2 = list2;
            }
            List<? extends Measurable> list3 = list;
            int $i$f$fastMap = 0;
            List target$iv = new ArrayList(list3.size());
            List<? extends Measurable> list4 = list3;
            int $i$f$fastForEach = 0;
            int index$iv$iv = 0;
            int size2 = list4.size();
            while (index$iv$iv < size2) {
                Object item$iv$iv = list4.get(index$iv$iv);
                List<? extends Measurable> list5 = list3;
                List list6 = target$iv;
                int $i$f$fastMap2 = $i$f$fastMap;
                Measurable it = (Measurable) item$iv$iv;
                List<? extends Measurable> list7 = list4;
                int measurableWidth = it.maxIntrinsicWidth(Constraints.m6970getMinHeightimpl(constraints));
                int $i$f$fastForEach2 = $i$f$fastForEach;
                int currentItemWidth = itemMinWidth;
                if (currentItemWidth < measurableWidth) {
                    int currentItemWidth2 = RangesKt.coerceAtMost(measurableWidth, itemMaxWidth);
                    barHorizontalPadding.element -= (currentItemWidth2 - itemMinWidth) / 2;
                    currentItemWidth = currentItemWidth2;
                }
                list6.add(it.mo5870measureBRTryo0(ConstraintsKt.m6984constrainN9IONVI(constraints, Constraints.INSTANCE.m6979fixedJhjzzOo(currentItemWidth, itemHeight2))));
                index$iv$iv++;
                list3 = list5;
                $i$f$fastMap = $i$f$fastMap2;
                itemMaxWidth = itemMaxWidth;
                list4 = list7;
                $i$f$fastForEach = $i$f$fastForEach2;
                itemMinWidth = itemMinWidth;
            }
            itemHeight = itemHeight2;
            itemsPlaceables = target$iv;
        } else {
            List<? extends Measurable> list8 = list;
            int $i$f$fastMap3 = 0;
            ArrayList target$iv2 = new ArrayList(list8.size());
            List<? extends Measurable> list9 = list8;
            int $i$f$fastForEach3 = 0;
            int index$iv$iv2 = 0;
            int size3 = list9.size();
            while (index$iv$iv2 < size3) {
                Object item$iv$iv2 = list9.get(index$iv$iv2);
                target$iv2.add(((Measurable) item$iv$iv2).mo5870measureBRTryo0(ConstraintsKt.m6984constrainN9IONVI(constraints, Constraints.INSTANCE.m6980fixedHeightOenEA2s(itemHeight2))));
                index$iv$iv2++;
                itemsPlaceables2 = itemsPlaceables2;
                list8 = list8;
                $i$f$fastMap3 = $i$f$fastMap3;
                list9 = list9;
                $i$f$fastForEach3 = $i$f$fastForEach3;
            }
            itemHeight = itemHeight2;
            itemsPlaceables = target$iv2;
        }
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, itemHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.CenteredContentMeasurePolicy$measure$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                int x = barHorizontalPadding.element;
                List<Placeable> list10 = itemsPlaceables;
                int size4 = list10.size();
                for (int index$iv2 = 0; index$iv2 < size4; index$iv2++) {
                    Object item$iv2 = list10.get(index$iv2);
                    Placeable item = (Placeable) item$iv2;
                    Placeable.PlacementScope.placeRelative$default($this$layout, item, x, 0, 0.0f, 4, null);
                    x += item.getWidth();
                }
            }
        }, 4, null);
    }
}
