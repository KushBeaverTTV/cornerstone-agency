package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: RowColumnMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\u001a\u0085\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "mainAxisMin", "", "crossAxisMin", "mainAxisMax", "crossAxisMax", "arrangementSpacingInt", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "startIndex", "endIndex", "crossAxisOffset", "", "currentLineIndex", "(Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;IIIIILandroidx/compose/ui/layout/MeasureScope;Ljava/util/List;[Landroidx/compose/ui/layout/Placeable;II[II)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RowColumnMeasurePolicyKt {
    public static /* synthetic */ MeasureResult measure$default(RowColumnMeasurePolicy rowColumnMeasurePolicy, int i, int i2, int i3, int i4, int i5, MeasureScope measureScope, List list, Placeable[] placeableArr, int i6, int i7, int[] iArr, int i8, int i9, Object obj) {
        int[] iArr2;
        int i10;
        if ((i9 & 1024) == 0) {
            iArr2 = iArr;
        } else {
            iArr2 = null;
        }
        if ((i9 & 2048) == 0) {
            i10 = i8;
        } else {
            i10 = 0;
        }
        return measure(rowColumnMeasurePolicy, i, i2, i3, i4, i5, measureScope, list, placeableArr, i6, i7, iArr2, i10);
    }

    public static final MeasureResult measure(RowColumnMeasurePolicy $this$measure, int mainAxisMin, int crossAxisMin, int mainAxisMax, int crossAxisMax, int arrangementSpacingInt, MeasureScope measureScope, List<? extends Measurable> list, Placeable[] placeables, int startIndex, int endIndex, int[] crossAxisOffset, int currentLineIndex) {
        int subSize;
        long arrangementSpacingPx;
        int[] childrenMainAxisSize;
        float totalWeight;
        long remainingToTarget;
        String str;
        String str2;
        String str3;
        long arrangementSpacingTotal;
        String str4;
        int weightChildrenCount;
        float totalWeight2;
        long arrangementSpacingPx2;
        RowColumnMeasurePolicy rowColumnMeasurePolicy;
        int weightedSpace;
        int fixedSpace;
        int crossAxisSpace;
        int fixedSpace2;
        int weightChildrenCount2;
        long arrangementSpacingPx3;
        long remainingToTarget2;
        String str5;
        String str6;
        long arrangementSpacingPx4;
        String str7;
        int i;
        int fixedSpace3;
        float totalWeight3;
        String str8;
        String str9;
        String str10;
        float weightUnitSpace;
        String str11;
        Integer numValueOf;
        FlowLayoutData it;
        int fixedSpace4;
        long arrangementSpacingTotal2;
        float totalWeight4;
        String str12;
        String str13;
        String str14;
        int fixedSpace5;
        int weightChildrenCount3;
        Integer crossAxisDesiredSize;
        String str15;
        float weightUnitSpace2;
        String str16;
        int iIntValue;
        int i2;
        int[] childrenMainAxisSize2;
        int i3;
        int iCoerceAtLeast;
        FlowLayoutData it2;
        List<? extends Measurable> list2 = list;
        int i4 = endIndex;
        long arrangementSpacingPx5 = arrangementSpacingInt;
        int crossAxisSpace2 = 0;
        int subSize2 = i4 - startIndex;
        int[] childrenMainAxisSize3 = new int[subSize2];
        int beforeCrossAxisAlignmentLine = 0;
        int afterCrossAxisAlignmentLine = 0;
        float totalWeight5 = 0.0f;
        int i5 = startIndex;
        int spaceAfterLastNoWeight = 0;
        int spaceAfterLastNoWeight2 = 0;
        int weightChildrenCount4 = 0;
        int weightChildrenCount5 = 0;
        while (true) {
            Integer numValueOf2 = null;
            subSize = subSize2;
            int i6 = 1;
            arrangementSpacingPx = arrangementSpacingPx5;
            if (i5 >= i4) {
                break;
            }
            Measurable child = list2.get(i5);
            RowColumnParentData parentData = RowColumnImplKt.getRowColumnParentData(child);
            float weight = RowColumnImplKt.getWeight(parentData);
            if (spaceAfterLastNoWeight2 == 0 && !RowColumnImplKt.isRelative(parentData)) {
                i6 = 0;
            }
            spaceAfterLastNoWeight2 = i6;
            if (weight > 0.0f) {
                totalWeight5 += weight;
                weightChildrenCount5++;
                childrenMainAxisSize2 = childrenMainAxisSize3;
                subSize = subSize;
                arrangementSpacingPx = arrangementSpacingPx;
            } else {
                if (crossAxisMax != 2147483647 && parentData != null && (it2 = parentData.getFlowLayoutData()) != null) {
                    float $this$fastRoundToInt$iv = it2.getFillCrossAxisFraction() * crossAxisMax;
                    int $i$f$fastRoundToInt = Math.round($this$fastRoundToInt$iv);
                    numValueOf2 = Integer.valueOf($i$f$fastRoundToInt);
                }
                Integer crossAxisDesiredSize2 = numValueOf2;
                int remaining = mainAxisMax - weightChildrenCount4;
                Placeable placeableMo5870measureBRTryo0 = placeables[i5];
                if (placeableMo5870measureBRTryo0 == null) {
                    int fixedSpace6 = crossAxisDesiredSize2 != null ? crossAxisDesiredSize2.intValue() : 0;
                    if (mainAxisMax == Integer.MAX_VALUE) {
                        iCoerceAtLeast = Integer.MAX_VALUE;
                        i3 = 0;
                    } else {
                        i3 = 0;
                        iCoerceAtLeast = RangesKt.coerceAtLeast(remaining, 0);
                    }
                    i2 = i3;
                    placeableMo5870measureBRTryo0 = child.mo5870measureBRTryo0(RowColumnMeasurePolicy.m1059createConstraintsxF2OJ5Q$default($this$measure, 0, fixedSpace6, iCoerceAtLeast, crossAxisDesiredSize2 != null ? crossAxisDesiredSize2.intValue() : crossAxisMax, false, 16, null));
                } else {
                    i2 = 0;
                }
                Placeable placeable = placeableMo5870measureBRTryo0;
                int placeableMainAxisSize = $this$measure.mainAxisSize(placeable);
                int placeableCrossAxisSize = $this$measure.crossAxisSize(placeable);
                childrenMainAxisSize2 = childrenMainAxisSize3;
                childrenMainAxisSize2[i5 - startIndex] = placeableMainAxisSize;
                int spaceAfterLastNoWeight3 = Math.min(arrangementSpacingInt, RangesKt.coerceAtLeast(remaining - placeableMainAxisSize, i2));
                int crossAxisSpace3 = Math.max(crossAxisSpace2, placeableCrossAxisSize);
                placeables[i5] = placeable;
                spaceAfterLastNoWeight = spaceAfterLastNoWeight3;
                weightChildrenCount4 = placeableMainAxisSize + spaceAfterLastNoWeight3 + weightChildrenCount4;
                crossAxisSpace2 = crossAxisSpace3;
                weightChildrenCount5 = weightChildrenCount5;
                totalWeight5 = totalWeight5;
            }
            i5++;
            childrenMainAxisSize3 = childrenMainAxisSize2;
            subSize2 = subSize;
            arrangementSpacingPx5 = arrangementSpacingPx;
        }
        int fixedSpace7 = weightChildrenCount4;
        float totalWeight6 = totalWeight5;
        int[] childrenMainAxisSize4 = childrenMainAxisSize3;
        int weightChildrenCount6 = weightChildrenCount5;
        if (weightChildrenCount6 != 0) {
            int targetSpace = mainAxisMax != Integer.MAX_VALUE ? mainAxisMax : mainAxisMin;
            long arrangementSpacingPx6 = arrangementSpacingPx;
            long arrangementSpacingTotal3 = ((long) (weightChildrenCount6 - 1)) * arrangementSpacingPx6;
            int crossAxisSpace4 = crossAxisSpace2;
            long remainingToTarget3 = RangesKt.coerceAtLeast(((long) (targetSpace - fixedSpace7)) - arrangementSpacingTotal3, 0L);
            float totalWeight7 = totalWeight6;
            float weightUnitSpace3 = remainingToTarget3 / totalWeight7;
            long remainder = remainingToTarget3;
            childrenMainAxisSize = childrenMainAxisSize4;
            int i7 = startIndex;
            while (true) {
                totalWeight = totalWeight7;
                remainingToTarget = remainingToTarget3;
                str = "fixedSpace ";
                str2 = "remainingToTarget ";
                str3 = "weightChildrenCount ";
                arrangementSpacingTotal = arrangementSpacingTotal3;
                str4 = "targetSpace ";
                if (i7 >= i4) {
                    break;
                }
                Measurable measurable = list2.get(i7);
                float itemWeight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(measurable));
                float weightedSize = weightUnitSpace3 * itemWeight;
                try {
                    remainder -= (long) Math.round(weightedSize);
                    i7++;
                    list2 = list;
                    i4 = endIndex;
                    remainingToTarget3 = remainingToTarget;
                    totalWeight7 = totalWeight;
                    arrangementSpacingTotal3 = arrangementSpacingTotal;
                } catch (IllegalArgumentException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/297974033 mainAxisMax ").append(mainAxisMax).append("mainAxisMin ").append(mainAxisMin).append("targetSpace ").append(targetSpace).append("arrangementSpacingPx ").append(arrangementSpacingPx6).append("weightChildrenCount ").append(weightChildrenCount6).append("fixedSpace ").append(fixedSpace7).append("arrangementSpacingTotal ").append(arrangementSpacingTotal).append(str2).append(remainingToTarget).append("totalWeight ").append(totalWeight).append("weightUnitSpace ").append(weightUnitSpace3).append("itemWeight ").append(itemWeight).append("weightedSize ");
                    sb.append(weightedSize);
                    IllegalArgumentException exception$iv = new IllegalArgumentException(sb.toString());
                    throw exception$iv.initCause(e);
                }
            }
            int fixedSpace8 = fixedSpace7;
            String str17 = "arrangementSpacingTotal ";
            String str18 = "mainAxisMin ";
            String str19 = "totalWeight ";
            String str20 = "weightedSize ";
            String str21 = "weightUnitSpace ";
            long remainingToTarget4 = remainingToTarget;
            float totalWeight8 = totalWeight;
            String str22 = "arrangementSpacingPx ";
            long remainder2 = remainder;
            int i8 = startIndex;
            int weightedSpace2 = 0;
            long arrangementSpacingTotal4 = arrangementSpacingTotal;
            int crossAxisSpace5 = crossAxisSpace4;
            while (true) {
                long arrangementSpacingPx7 = arrangementSpacingPx6;
                if (i8 >= endIndex) {
                    weightChildrenCount = weightChildrenCount6;
                    int fixedSpace9 = fixedSpace8;
                    totalWeight2 = totalWeight8;
                    arrangementSpacingPx2 = arrangementSpacingPx7;
                    rowColumnMeasurePolicy = $this$measure;
                    weightedSpace = RangesKt.coerceIn((int) (((long) weightedSpace2) + arrangementSpacingTotal4), 0, mainAxisMax - fixedSpace9);
                    fixedSpace = fixedSpace9;
                    crossAxisSpace = crossAxisSpace5;
                    break;
                }
                if (placeables[i8] == null) {
                    int i9 = i8;
                    Measurable child2 = list.get(i8);
                    RowColumnParentData parentData2 = RowColumnImplKt.getRowColumnParentData(child2);
                    float weight2 = RowColumnImplKt.getWeight(parentData2);
                    if (crossAxisMax == Integer.MAX_VALUE || parentData2 == null || (it = parentData2.getFlowLayoutData()) == null) {
                        numValueOf = null;
                    } else {
                        float $this$fastRoundToInt$iv2 = it.getFillCrossAxisFraction() * crossAxisMax;
                        int $i$f$fastRoundToInt2 = Math.round($this$fastRoundToInt$iv2);
                        numValueOf = Integer.valueOf($i$f$fastRoundToInt2);
                    }
                    Integer crossAxisDesiredSize3 = numValueOf;
                    if (!(weight2 > 0.0f)) {
                        throw new IllegalStateException("All weights <= 0 should have placeables".toString());
                    }
                    int remainderUnit = MathKt.getSign(remainder2);
                    String str23 = str3;
                    remainder2 -= (long) remainderUnit;
                    float weightedSize2 = weightUnitSpace3 * weight2;
                    int childMainAxisSize = Math.max(0, Math.round(weightedSize2) + remainderUnit);
                    try {
                        int i10 = (!RowColumnImplKt.getFill(parentData2) || childMainAxisSize == Integer.MAX_VALUE) ? 0 : childMainAxisSize;
                        if (crossAxisDesiredSize3 != null) {
                            try {
                                iIntValue = crossAxisDesiredSize3.intValue();
                            } catch (IllegalArgumentException e2) {
                                e = e2;
                                fixedSpace4 = fixedSpace8;
                                arrangementSpacingTotal2 = arrangementSpacingTotal4;
                                totalWeight4 = totalWeight8;
                                str12 = str23;
                                remainingToTarget2 = remainingToTarget4;
                                str13 = str2;
                                str14 = str19;
                                fixedSpace5 = targetSpace;
                                weightChildrenCount3 = weightChildrenCount6;
                                crossAxisDesiredSize = crossAxisDesiredSize3;
                                str15 = str21;
                                str6 = str22;
                                weightUnitSpace2 = weightUnitSpace3;
                                str11 = str18;
                                str16 = str;
                                StringBuilder sb2 = new StringBuilder();
                                long arrangementSpacingPx8 = arrangementSpacingTotal2;
                                sb2.append("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax ").append(mainAxisMax).append(str11).append(mainAxisMin).append(str4).append(fixedSpace5).append(str6).append(arrangementSpacingPx7).append(str12).append(weightChildrenCount3).append(str16).append(fixedSpace4).append(str17).append(arrangementSpacingPx8).append(str13).append(remainingToTarget2).append(str14).append(totalWeight4).append(str15).append(weightUnitSpace2).append("weight ").append(weight2).append(str20);
                                sb2.append(weightedSize2).append("crossAxisDesiredSize ").append(crossAxisDesiredSize).append("remainderUnit ").append(remainderUnit).append("childMainAxisSize ").append(childMainAxisSize);
                                IllegalArgumentException exception$iv2 = new IllegalArgumentException(sb2.toString());
                                throw exception$iv2.initCause(e);
                            }
                        } else {
                            iIntValue = 0;
                        }
                        int iIntValue2 = crossAxisDesiredSize3 != null ? crossAxisDesiredSize3.intValue() : crossAxisMax;
                        crossAxisDesiredSize = crossAxisDesiredSize3;
                        String str24 = str21;
                        weightUnitSpace2 = weightUnitSpace3;
                        String str25 = str2;
                        str15 = str24;
                        totalWeight4 = totalWeight8;
                        remainingToTarget2 = remainingToTarget4;
                        str14 = str19;
                        str13 = str25;
                        str11 = str18;
                        int i11 = i10;
                        fixedSpace4 = fixedSpace8;
                        fixedSpace5 = targetSpace;
                        str16 = str;
                        int weightChildrenCount7 = weightChildrenCount6;
                        int weightChildrenCount8 = iIntValue2;
                        arrangementSpacingTotal2 = arrangementSpacingTotal4;
                        i = i9;
                        weightChildrenCount3 = weightChildrenCount7;
                        str12 = str23;
                        str6 = str22;
                        try {
                            long childConstraints = $this$measure.mo949createConstraintsxF2OJ5Q(i11, iIntValue, childMainAxisSize, weightChildrenCount8, true);
                            Placeable placeable2 = child2.mo5870measureBRTryo0(childConstraints);
                            int placeableMainAxisSize2 = $this$measure.mainAxisSize(placeable2);
                            int placeableCrossAxisSize2 = $this$measure.crossAxisSize(placeable2);
                            childrenMainAxisSize[i - startIndex] = placeableMainAxisSize2;
                            weightedSpace2 += placeableMainAxisSize2;
                            crossAxisSpace5 = Math.max(crossAxisSpace5, placeableCrossAxisSize2);
                            placeables[i] = placeable2;
                            fixedSpace3 = fixedSpace4;
                            weightChildrenCount2 = weightChildrenCount3;
                            str5 = str13;
                            str8 = str14;
                            weightUnitSpace = weightUnitSpace2;
                            str9 = str15;
                            str7 = str16;
                            fixedSpace2 = fixedSpace5;
                            totalWeight3 = totalWeight4;
                            str10 = str12;
                            arrangementSpacingPx4 = arrangementSpacingPx7;
                            arrangementSpacingPx3 = arrangementSpacingTotal2;
                        } catch (IllegalArgumentException e3) {
                            e = e3;
                            StringBuilder sb3 = new StringBuilder();
                            long arrangementSpacingPx9 = arrangementSpacingTotal2;
                            sb3.append("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax ").append(mainAxisMax).append(str11).append(mainAxisMin).append(str4).append(fixedSpace5).append(str6).append(arrangementSpacingPx7).append(str12).append(weightChildrenCount3).append(str16).append(fixedSpace4).append(str17).append(arrangementSpacingPx9).append(str13).append(remainingToTarget2).append(str14).append(totalWeight4).append(str15).append(weightUnitSpace2).append("weight ").append(weight2).append(str20);
                            sb3.append(weightedSize2).append("crossAxisDesiredSize ").append(crossAxisDesiredSize).append("remainderUnit ").append(remainderUnit).append("childMainAxisSize ").append(childMainAxisSize);
                            IllegalArgumentException exception$iv3 = new IllegalArgumentException(sb3.toString());
                            throw exception$iv3.initCause(e);
                        }
                    } catch (IllegalArgumentException e4) {
                        e = e4;
                        fixedSpace4 = fixedSpace8;
                        arrangementSpacingTotal2 = arrangementSpacingTotal4;
                        totalWeight4 = totalWeight8;
                        str12 = str23;
                        remainingToTarget2 = remainingToTarget4;
                        str13 = str2;
                        str14 = str19;
                        fixedSpace5 = targetSpace;
                        weightChildrenCount3 = weightChildrenCount6;
                        crossAxisDesiredSize = crossAxisDesiredSize3;
                        str15 = str21;
                        str6 = str22;
                        weightUnitSpace2 = weightUnitSpace3;
                        str11 = str18;
                        str16 = str;
                    }
                } else {
                    fixedSpace2 = targetSpace;
                    weightChildrenCount2 = weightChildrenCount6;
                    arrangementSpacingPx3 = arrangementSpacingTotal4;
                    remainingToTarget2 = remainingToTarget4;
                    str5 = str2;
                    str6 = str22;
                    arrangementSpacingPx4 = arrangementSpacingPx7;
                    str7 = str;
                    i = i8;
                    fixedSpace3 = fixedSpace8;
                    totalWeight3 = totalWeight8;
                    str8 = str19;
                    str9 = str21;
                    str10 = str3;
                    weightUnitSpace = weightUnitSpace3;
                    str11 = str18;
                }
                i8 = i + 1;
                str22 = str6;
                str18 = str11;
                str2 = str5;
                str17 = str17;
                weightUnitSpace3 = weightUnitSpace;
                str3 = str10;
                str20 = str20;
                str4 = str4;
                str21 = str9;
                str19 = str8;
                totalWeight8 = totalWeight3;
                fixedSpace8 = fixedSpace3;
                str = str7;
                remainingToTarget4 = remainingToTarget2;
                long j = arrangementSpacingPx3;
                weightChildrenCount6 = weightChildrenCount2;
                targetSpace = fixedSpace2;
                arrangementSpacingPx6 = arrangementSpacingPx4;
                arrangementSpacingTotal4 = j;
            }
        } else {
            weightedSpace = 0;
            weightChildrenCount = weightChildrenCount6;
            childrenMainAxisSize = childrenMainAxisSize4;
            rowColumnMeasurePolicy = $this$measure;
            totalWeight2 = totalWeight6;
            arrangementSpacingPx2 = arrangementSpacingPx;
            crossAxisSpace = crossAxisSpace2;
            fixedSpace = fixedSpace7 - spaceAfterLastNoWeight;
        }
        if (spaceAfterLastNoWeight2 != 0) {
            int beforeCrossAxisAlignmentLine2 = 0;
            int afterCrossAxisAlignmentLine2 = 0;
            for (int i12 = startIndex; i12 < endIndex; i12++) {
                Placeable placeable3 = placeables[i12];
                Intrinsics.checkNotNull(placeable3);
                CrossAxisAlignment crossAxisAlignment = RowColumnImplKt.getCrossAxisAlignment(RowColumnImplKt.getRowColumnParentData(placeable3));
                Integer alignmentLinePosition = crossAxisAlignment != null ? crossAxisAlignment.calculateAlignmentLinePosition$foundation_layout_release(placeable3) : null;
                if (alignmentLinePosition != null) {
                    int it3 = alignmentLinePosition.intValue();
                    int placeableCrossAxisSize3 = rowColumnMeasurePolicy.crossAxisSize(placeable3);
                    beforeCrossAxisAlignmentLine2 = Math.max(beforeCrossAxisAlignmentLine2, it3 != Integer.MIN_VALUE ? alignmentLinePosition.intValue() : 0);
                    afterCrossAxisAlignmentLine2 = Math.max(afterCrossAxisAlignmentLine2, placeableCrossAxisSize3 - (it3 != Integer.MIN_VALUE ? it3 : placeableCrossAxisSize3));
                }
            }
            beforeCrossAxisAlignmentLine = beforeCrossAxisAlignmentLine2;
            afterCrossAxisAlignmentLine = afterCrossAxisAlignmentLine2;
        }
        int mainAxisLayoutSize = Math.max(RangesKt.coerceAtLeast(fixedSpace + weightedSpace, 0), mainAxisMin);
        int crossAxisLayoutSize = Math.max(crossAxisSpace, Math.max(crossAxisMin, beforeCrossAxisAlignmentLine + afterCrossAxisAlignmentLine));
        int[] mainAxisPositions = new int[subSize];
        for (int i13 = 0; i13 < subSize; i13++) {
            mainAxisPositions[i13] = 0;
        }
        rowColumnMeasurePolicy.populateMainAxisPositions(mainAxisLayoutSize, childrenMainAxisSize, mainAxisPositions, measureScope);
        return $this$measure.placeHelper(placeables, measureScope, beforeCrossAxisAlignmentLine, mainAxisPositions, mainAxisLayoutSize, crossAxisLayoutSize, crossAxisOffset, currentLineIndex, startIndex, endIndex);
    }
}
