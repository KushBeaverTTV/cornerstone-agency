package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: ListItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JV\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\t\u001a\u00020\u00042,\u0010\n\u001a(\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\u0002\b\u000eH\u0002JV\u0010\u000f\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u0010\u001a\u00020\u00042,\u0010\n\u001a(\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\u0002\b\u000eH\u0002J(\u0010\u0011\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\t\u001a\u00020\u0004H\u0016J(\u0010\u0012\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J2\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00070\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ(\u0010\u001b\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\t\u001a\u00020\u0004H\u0016J(\u0010\u001c\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u0010\u001a\u00020\u0004H\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/ListItemMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "()V", "calculateIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "intrinsicMeasure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Lkotlin/ExtensionFunctionType;", "calculateIntrinsicWidth", "height", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ListItemMeasurePolicy implements MultiContentMeasurePolicy {
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo982measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends List<? extends Measurable>> list, long constraints) {
        long looseConstraints;
        Placeable leadingPlaceable;
        Placeable trailingPlaceable;
        Placeable headlinePlaceable;
        Placeable placeableMo5870measureBRTryo0;
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        List<? extends Measurable> list5 = list.get(3);
        List<? extends Measurable> list6 = list.get(4);
        long looseConstraints2 = Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0);
        float startPadding = ListItemKt.getListItemStartPadding();
        float endPadding = ListItemKt.getListItemEndPadding();
        int horizontalPadding = $this$measure_u2d3p2s80s.mo720roundToPx0680j_4(Dp.m7016constructorimpl(startPadding + endPadding));
        Measurable measurable = (Measurable) CollectionsKt.firstOrNull((List) list5);
        int intrinsicLeadingWidth = measurable != null ? measurable.minIntrinsicWidth(Constraints.m6968getMaxHeightimpl(constraints)) : 0;
        Measurable measurable2 = (Measurable) CollectionsKt.firstOrNull((List) list6);
        int intrinsicTrailingWidth = measurable2 != null ? measurable2.minIntrinsicWidth(Constraints.m6968getMaxHeightimpl(constraints)) : 0;
        int intrinsicSupportingWidthConstraint = ListItemKt.subtractConstraintSafely(Constraints.m6969getMaxWidthimpl(looseConstraints2), intrinsicLeadingWidth + intrinsicTrailingWidth + horizontalPadding);
        Measurable measurable3 = (Measurable) CollectionsKt.firstOrNull((List) list4);
        int intrinsicSupportingHeight = measurable3 != null ? measurable3.minIntrinsicHeight(intrinsicSupportingWidthConstraint) : 0;
        boolean intrinsicIsSupportingMultiline = ListItemKt.isSupportingMultilineHeuristic($this$measure_u2d3p2s80s, intrinsicSupportingHeight);
        int intrinsicListItemType = ListItemType.INSTANCE.m2590invokeZLSjz4$material3_release(CollectionsKt.firstOrNull((List) list3) != null, CollectionsKt.firstOrNull((List) list4) != null, intrinsicIsSupportingMultiline);
        float arg0$iv = ListItemKt.m2577verticalPaddingyh95HIg(intrinsicListItemType);
        int intrinsicVerticalPadding = $this$measure_u2d3p2s80s.mo720roundToPx0680j_4(Dp.m7016constructorimpl(2 * arg0$iv));
        long paddedLooseConstraints = ConstraintsKt.m6988offsetNN6EwU(looseConstraints2, -horizontalPadding, -intrinsicVerticalPadding);
        Measurable measurable4 = (Measurable) CollectionsKt.firstOrNull((List) list5);
        Placeable placeableMo5870measureBRTryo1 = null;
        if (measurable4 != null) {
            looseConstraints = paddedLooseConstraints;
            leadingPlaceable = measurable4.mo5870measureBRTryo0(looseConstraints);
        } else {
            looseConstraints = paddedLooseConstraints;
            leadingPlaceable = null;
        }
        int currentTotalWidth = 0 + TextFieldImplKt.widthOrZero(leadingPlaceable);
        Measurable measurable5 = (Measurable) CollectionsKt.firstOrNull((List) list6);
        if (measurable5 != null) {
            int intrinsicVerticalPadding2 = -currentTotalWidth;
            trailingPlaceable = measurable5.mo5870measureBRTryo0(ConstraintsKt.m6989offsetNN6EwU$default(looseConstraints, intrinsicVerticalPadding2, 0, 2, null));
        } else {
            trailingPlaceable = null;
        }
        int currentTotalWidth2 = currentTotalWidth + TextFieldImplKt.widthOrZero(trailingPlaceable);
        Measurable measurable6 = (Measurable) CollectionsKt.firstOrNull((List) list2);
        if (measurable6 != null) {
            int intrinsicSupportingHeight2 = -currentTotalWidth2;
            headlinePlaceable = measurable6.mo5870measureBRTryo0(ConstraintsKt.m6989offsetNN6EwU$default(looseConstraints, intrinsicSupportingHeight2, 0, 2, null));
        } else {
            headlinePlaceable = null;
        }
        int currentTotalHeight = 0 + TextFieldImplKt.heightOrZero(headlinePlaceable);
        Measurable measurable7 = (Measurable) CollectionsKt.firstOrNull((List) list4);
        if (measurable7 != null) {
            int intrinsicSupportingWidthConstraint2 = -currentTotalHeight;
            placeableMo5870measureBRTryo0 = measurable7.mo5870measureBRTryo0(ConstraintsKt.m6988offsetNN6EwU(looseConstraints, -currentTotalWidth2, intrinsicSupportingWidthConstraint2));
        } else {
            placeableMo5870measureBRTryo0 = null;
        }
        Placeable supportingPlaceable = placeableMo5870measureBRTryo0;
        int currentTotalHeight2 = currentTotalHeight + TextFieldImplKt.heightOrZero(supportingPlaceable);
        boolean isSupportingMultiline = (supportingPlaceable == null || supportingPlaceable.get(AlignmentLineKt.getFirstBaseline()) == supportingPlaceable.get(AlignmentLineKt.getLastBaseline())) ? false : true;
        Measurable measurable8 = (Measurable) CollectionsKt.firstOrNull((List) list3);
        if (measurable8 != null) {
            placeableMo5870measureBRTryo1 = measurable8.mo5870measureBRTryo0(ConstraintsKt.m6988offsetNN6EwU(looseConstraints, -currentTotalWidth2, -currentTotalHeight2));
        }
        Placeable overlinePlaceable = placeableMo5870measureBRTryo1;
        int listItemType = ListItemType.INSTANCE.m2590invokeZLSjz4$material3_release(overlinePlaceable != null, supportingPlaceable != null, isSupportingMultiline);
        float topPadding = ListItemKt.m2577verticalPaddingyh95HIg(listItemType);
        float fM7016constructorimpl = Dp.m7016constructorimpl(2 * topPadding);
        int width = ListItemKt.m2576calculateWidthyeHjK3Y($this$measure_u2d3p2s80s, TextFieldImplKt.widthOrZero(leadingPlaceable), TextFieldImplKt.widthOrZero(trailingPlaceable), TextFieldImplKt.widthOrZero(headlinePlaceable), TextFieldImplKt.widthOrZero(overlinePlaceable), TextFieldImplKt.widthOrZero(supportingPlaceable), horizontalPadding, constraints);
        int height = ListItemKt.m2575calculateHeightN4Jib3Y($this$measure_u2d3p2s80s, TextFieldImplKt.heightOrZero(leadingPlaceable), TextFieldImplKt.heightOrZero(trailingPlaceable), TextFieldImplKt.heightOrZero(headlinePlaceable), TextFieldImplKt.heightOrZero(overlinePlaceable), TextFieldImplKt.heightOrZero(supportingPlaceable), listItemType, $this$measure_u2d3p2s80s.mo720roundToPx0680j_4(fM7016constructorimpl), constraints);
        return ListItemKt.place($this$measure_u2d3p2s80s, width, height, leadingPlaceable, trailingPlaceable, headlinePlaceable, overlinePlaceable, supportingPlaceable, ListItemType.m2582equalsimpl0(listItemType, ListItemType.INSTANCE.m2588getThreeLineAlXitO8()), $this$measure_u2d3p2s80s.mo720roundToPx0680j_4(startPadding), $this$measure_u2d3p2s80s.mo720roundToPx0680j_4(endPadding), $this$measure_u2d3p2s80s.mo720roundToPx0680j_4(topPadding));
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$maxIntrinsicHeight$1, reason: invalid class name */
    /* JADX INFO: compiled from: ListItem.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(2, IntrinsicMeasurable.class, "maxIntrinsicHeight", "maxIntrinsicHeight(I)I", 0);
        }

        public final Integer invoke(IntrinsicMeasurable p0, int p1) {
            return Integer.valueOf(p0.maxIntrinsicHeight(p1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
            return invoke(intrinsicMeasurable, num.intValue());
        }
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
        return calculateIntrinsicHeight($this$maxIntrinsicHeight, list, width, AnonymousClass1.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$maxIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ListItem.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* synthetic */ class C05861 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final C05861 INSTANCE = new C05861();

        C05861() {
            super(2, IntrinsicMeasurable.class, "maxIntrinsicWidth", "maxIntrinsicWidth(I)I", 0);
        }

        public final Integer invoke(IntrinsicMeasurable p0, int p1) {
            return Integer.valueOf(p0.maxIntrinsicWidth(p1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
            return invoke(intrinsicMeasurable, num.intValue());
        }
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
        return calculateIntrinsicWidth($this$maxIntrinsicWidth, list, height, C05861.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$minIntrinsicHeight$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ListItem.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* synthetic */ class C05871 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final C05871 INSTANCE = new C05871();

        C05871() {
            super(2, IntrinsicMeasurable.class, "minIntrinsicHeight", "minIntrinsicHeight(I)I", 0);
        }

        public final Integer invoke(IntrinsicMeasurable p0, int p1) {
            return Integer.valueOf(p0.minIntrinsicHeight(p1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
            return invoke(intrinsicMeasurable, num.intValue());
        }
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
        return calculateIntrinsicHeight($this$minIntrinsicHeight, list, width, C05871.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$minIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ListItem.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* synthetic */ class C05881 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
        public static final C05881 INSTANCE = new C05881();

        C05881() {
            super(2, IntrinsicMeasurable.class, "minIntrinsicWidth", "minIntrinsicWidth(I)I", 0);
        }

        public final Integer invoke(IntrinsicMeasurable p0, int p1) {
            return Integer.valueOf(p0.minIntrinsicWidth(p1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
            return invoke(intrinsicMeasurable, num.intValue());
        }
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
        return calculateIntrinsicWidth($this$minIntrinsicWidth, list, height, C05881.INSTANCE);
    }

    private final int calculateIntrinsicWidth(IntrinsicMeasureScope $this$calculateIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        List<? extends IntrinsicMeasurable> list2 = list.get(0);
        List<? extends IntrinsicMeasurable> list3 = list.get(1);
        List<? extends IntrinsicMeasurable> list4 = list.get(2);
        List<? extends IntrinsicMeasurable> list5 = list.get(3);
        List<? extends IntrinsicMeasurable> list6 = list.get(4);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list5);
        int iIntValue = intrinsicMeasurable != null ? function2.invoke(intrinsicMeasurable, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list6);
        int iIntValue2 = intrinsicMeasurable2 != null ? function2.invoke(intrinsicMeasurable2, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list2);
        int iIntValue3 = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list3);
        int iIntValue4 = intrinsicMeasurable4 != null ? function2.invoke(intrinsicMeasurable4, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list4);
        int iIntValue5 = intrinsicMeasurable5 != null ? function2.invoke(intrinsicMeasurable5, Integer.valueOf(height)).intValue() : 0;
        float arg0$iv = ListItemKt.getListItemStartPadding();
        float other$iv = ListItemKt.getListItemEndPadding();
        return ListItemKt.m2576calculateWidthyeHjK3Y($this$calculateIntrinsicWidth, iIntValue, iIntValue2, iIntValue3, iIntValue4, iIntValue5, $this$calculateIntrinsicWidth.mo720roundToPx0680j_4(Dp.m7016constructorimpl(arg0$iv + other$iv)), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
    }

    private final int calculateIntrinsicHeight(IntrinsicMeasureScope $this$calculateIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        int remainingWidth;
        int remainingWidth2;
        int remainingWidth3;
        int remainingWidth4;
        List<? extends IntrinsicMeasurable> list2 = list.get(0);
        List<? extends IntrinsicMeasurable> list3 = list.get(1);
        List<? extends IntrinsicMeasurable> list4 = list.get(2);
        List<? extends IntrinsicMeasurable> list5 = list.get(3);
        List<? extends IntrinsicMeasurable> list6 = list.get(4);
        float arg0$iv = ListItemKt.getListItemStartPadding();
        float other$iv = ListItemKt.getListItemEndPadding();
        int remainingWidth5 = ListItemKt.subtractConstraintSafely(width, $this$calculateIntrinsicHeight.mo720roundToPx0680j_4(Dp.m7016constructorimpl(arg0$iv + other$iv)));
        IntrinsicMeasurable it = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list5);
        if (it != null) {
            int iIntValue = function2.invoke(it, Integer.valueOf(remainingWidth5)).intValue();
            remainingWidth = ListItemKt.subtractConstraintSafely(remainingWidth5, it.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth2 = iIntValue;
        } else {
            remainingWidth = remainingWidth5;
            remainingWidth2 = 0;
        }
        IntrinsicMeasurable it2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list6);
        if (it2 != null) {
            int iIntValue2 = function2.invoke(it2, Integer.valueOf(remainingWidth)).intValue();
            remainingWidth3 = ListItemKt.subtractConstraintSafely(remainingWidth, it2.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth4 = iIntValue2;
        } else {
            remainingWidth3 = remainingWidth;
            remainingWidth4 = 0;
        }
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list3);
        int overlineHeight = intrinsicMeasurable != null ? function2.invoke(intrinsicMeasurable, Integer.valueOf(remainingWidth3)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list4);
        int supportingHeight = intrinsicMeasurable2 != null ? function2.invoke(intrinsicMeasurable2, Integer.valueOf(remainingWidth3)).intValue() : 0;
        boolean isSupportingMultiline = ListItemKt.isSupportingMultilineHeuristic($this$calculateIntrinsicHeight, supportingHeight);
        int listItemType = ListItemType.INSTANCE.m2590invokeZLSjz4$material3_release(overlineHeight > 0, supportingHeight > 0, isSupportingMultiline);
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list2);
        int iIntValue3 = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(width)).intValue() : 0;
        float arg0$iv2 = ListItemKt.m2577verticalPaddingyh95HIg(listItemType);
        return ListItemKt.m2575calculateHeightN4Jib3Y($this$calculateIntrinsicHeight, remainingWidth2, remainingWidth4, iIntValue3, overlineHeight, supportingHeight, listItemType, $this$calculateIntrinsicHeight.mo720roundToPx0680j_4(Dp.m7016constructorimpl(2 * arg0$iv2)), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
    }
}
