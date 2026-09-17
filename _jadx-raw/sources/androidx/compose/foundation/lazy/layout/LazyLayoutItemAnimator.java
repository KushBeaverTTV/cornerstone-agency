package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSetKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0005\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0003IJKB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010(\u001a\u0004\u0018\u00010\u00072\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u000bJ1\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00028\u00002\u0006\u0010&\u001a\u00020\u000b2\u0012\b\u0002\u0010.\u001a\f0\u0010R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002¢\u0006\u0002\u0010/J\u0082\u0001\u00100\u001a\u00020,2\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\u000b2\f\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\f\u001a\u00020\r2\f\u00105\u001a\b\u0012\u0004\u0012\u00028\u0000062\u0006\u00107\u001a\u00020#2\u0006\u00108\u001a\u00020#2\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020#2\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@J\u0010\u0010A\u001a\u00020,2\u0006\u0010)\u001a\u00020\u0003H\u0002J\u0006\u0010B\u001a\u00020,J\u001f\u0010C\u001a\u00020,2\u0006\u0010-\u001a\u00028\u00002\b\b\u0002\u0010D\u001a\u00020#H\u0002¢\u0006\u0002\u0010EJ\u0019\u0010F\u001a\u00020\u000b*\u00020G2\u0006\u0010-\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010HR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u000e\u0012\f0\u0010R\b\u0012\u0004\u0012\u00028\u00000\u00000\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\u00020\u00128Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001f\u001a\u00020\u000b*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0018\u0010\"\u001a\u00020#*\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0018\u0010&\u001a\u00020\u000b*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010!\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006L"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "T", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "", "()V", "disappearingItems", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "displayingNode", "Landroidx/compose/ui/node/DrawModifierNode;", "firstVisibleIndex", "", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "keyToItemInfoMap", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;", "minSizeToFitDisappearingItems", "Landroidx/compose/ui/unit/IntSize;", "getMinSizeToFitDisappearingItems-YbymL2g", "()J", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "movingAwayKeys", "Landroidx/collection/MutableScatterSet;", "movingAwayToEndBound", "movingAwayToStartBound", "movingInFromEndBound", "movingInFromStartBound", "crossAxisOffset", "getCrossAxisOffset", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "hasAnimations", "", "getHasAnimations", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)Z", "mainAxisOffset", "getMainAxisOffset", "getAnimation", "key", "placeableIndex", "initializeAnimation", "", "item", "itemInfo", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;ILandroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;)V", "onMeasured", "consumedScroll", "layoutWidth", "layoutHeight", "positionedItems", "itemProvider", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItemProvider;", "isVertical", "isLookingAhead", "laneCount", "hasLookaheadOccurred", "layoutMinOffset", "layoutMaxOffset", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "removeInfoForKey", "reset", "startPlacementAnimationsIfNeeded", "isMovingAway", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;Z)V", "updateAndReturnOffsetFor", "", "([ILandroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "DisplayingDisappearingItemsElement", "DisplayingDisappearingItemsNode", "ItemInfo", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutItemAnimator<T extends LazyLayoutMeasuredItem> {
    public static final int $stable = 8;
    private DrawModifierNode displayingNode;
    private int firstVisibleIndex;
    private LazyLayoutKeyIndexMap keyIndexMap;
    private final MutableScatterMap<Object, LazyLayoutItemAnimator<T>.ItemInfo> keyToItemInfoMap = ScatterMapKt.mutableScatterMapOf();
    private final MutableScatterSet<Object> movingAwayKeys = ScatterSetKt.mutableScatterSetOf();
    private final List<T> movingInFromStartBound = new ArrayList();
    private final List<T> movingInFromEndBound = new ArrayList();
    private final List<T> movingAwayToStartBound = new ArrayList();
    private final List<T> movingAwayToEndBound = new ArrayList();
    private final List<LazyLayoutItemAnimation> disappearingItems = new ArrayList();
    private final Modifier modifier = new DisplayingDisappearingItemsElement(this);

    /* JADX WARN: Code duplicated, block: B:195:0x0520 A[LOOP:13: B:185:0x0500->B:195:0x0520, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:206:0x053f  */
    /* JADX WARN: Code duplicated, block: B:208:0x055c  */
    /* JADX WARN: Code duplicated, block: B:209:0x0562  */
    /* JADX WARN: Code duplicated, block: B:299:0x051e A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<T> positionedItems, LazyLayoutKeyIndexMap keyIndexMap, LazyLayoutMeasuredItemProvider<T> itemProvider, boolean isVertical, boolean isLookingAhead, int laneCount, boolean hasLookaheadOccurred, int layoutMinOffset, int layoutMaxOffset, CoroutineScope coroutineScope, GraphicsContext graphicsContext) {
        boolean z;
        long jIntOffset;
        int[] iArr;
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap;
        int[] iArr2;
        int layoutMaxOffset2;
        int layoutMinOffset2;
        Object[] objArr;
        MutableScatterSet<Object> mutableScatterSet;
        int i;
        long[] jArr;
        Object[] objArr2;
        MutableScatterSet<Object> mutableScatterSet2;
        int i2;
        long[] jArr2;
        int[] iArr3;
        boolean z2;
        LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr;
        int i3;
        boolean z3;
        int i4;
        List<T> list;
        int i5;
        int i6;
        int i7;
        int i8;
        long[] jArr3;
        Object[] objArr3;
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap2 = this.keyIndexMap;
        this.keyIndexMap = keyIndexMap;
        int i9 = 0;
        int size = positionedItems.size();
        while (true) {
            if (i9 < size) {
                if (getHasAnimations(positionedItems.get(i9))) {
                    z = true;
                    break;
                }
                i9++;
            } else {
                z = false;
                break;
            }
        }
        boolean z4 = z;
        if (!z4 && this.keyToItemInfoMap.isEmpty()) {
            reset();
            return;
        }
        int i10 = this.firstVisibleIndex;
        LazyLayoutMeasuredItem lazyLayoutMeasuredItem = (LazyLayoutMeasuredItem) CollectionsKt.firstOrNull((List) positionedItems);
        this.firstVisibleIndex = lazyLayoutMeasuredItem != null ? lazyLayoutMeasuredItem.getIndex() : 0;
        if (isVertical) {
            jIntOffset = IntOffsetKt.IntOffset(0, consumedScroll);
        } else {
            jIntOffset = IntOffsetKt.IntOffset(consumedScroll, 0);
        }
        long j = jIntOffset;
        boolean z5 = isLookingAhead || !hasLookaheadOccurred;
        MutableScatterMap<Object, LazyLayoutItemAnimator<T>.ItemInfo> mutableScatterMap = this.keyToItemInfoMap;
        Object[] objArr4 = mutableScatterMap.keys;
        long[] jArr4 = mutableScatterMap.metadata;
        int length = jArr4.length - 2;
        int i11 = 0;
        if (0 <= length) {
            while (true) {
                long j2 = jArr4[i11];
                boolean z6 = z4;
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) == -9187201950435737472L) {
                    jArr3 = jArr4;
                    objArr3 = objArr4;
                } else {
                    int i12 = 8 - ((~(i11 - length)) >>> 31);
                    int i13 = 0;
                    while (i13 < i12) {
                        if (((j2 & 255) < 128 ? 1 : 0) != 0) {
                            this.movingAwayKeys.add(objArr4[(i11 << 3) + i13]);
                        }
                        j2 >>= 8;
                        i13++;
                        jArr4 = jArr4;
                        objArr4 = objArr4;
                    }
                    jArr3 = jArr4;
                    objArr3 = objArr4;
                    if (i12 != 8) {
                        break;
                    }
                }
                if (i11 == length) {
                    break;
                }
                i11++;
                jArr4 = jArr3;
                z4 = z6;
                objArr4 = objArr3;
            }
        }
        List<T> list2 = positionedItems;
        int i14 = 0;
        int i15 = 0;
        int size2 = list2.size();
        while (i15 < size2) {
            T t = list2.get(i15);
            this.movingAwayKeys.remove(t.getKey());
            if (getHasAnimations(t)) {
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo = this.keyToItemInfoMap.get(t.getKey());
                int index = lazyLayoutKeyIndexMap2 != null ? lazyLayoutKeyIndexMap2.getIndex(t.getKey()) : -1;
                list = list2;
                boolean z7 = index == -1 && lazyLayoutKeyIndexMap2 != null;
                if (itemInfo == null) {
                    i5 = i14;
                    LazyLayoutItemAnimator<T>.ItemInfo itemInfo2 = new ItemInfo();
                    ItemInfo.updateAnimation$default(itemInfo2, t, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, 0, 32, null);
                    i7 = size2;
                    this.keyToItemInfoMap.set(t.getKey(), itemInfo2);
                    if (t.getIndex() != index && index != -1) {
                        if (index < i10) {
                            this.movingInFromStartBound.add(t);
                            i6 = i10;
                        } else {
                            this.movingInFromEndBound.add(t);
                            i6 = i10;
                        }
                    } else {
                        long jMo1146getOffsetBjo55l4 = t.mo1146getOffsetBjo55l4(0);
                        initializeAnimation(t, t.getIsVertical() ? IntOffset.m7145getYimpl(jMo1146getOffsetBjo55l4) : IntOffset.m7144getXimpl(jMo1146getOffsetBjo55l4), itemInfo2);
                        if (z7) {
                            i6 = i10;
                            for (LazyLayoutItemAnimation lazyLayoutItemAnimation : itemInfo2.getAnimations()) {
                                if (lazyLayoutItemAnimation != null) {
                                    lazyLayoutItemAnimation.animateAppearance();
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        } else {
                            i6 = i10;
                        }
                    }
                } else {
                    i5 = i14;
                    i6 = i10;
                    i7 = size2;
                    if (z5) {
                        ItemInfo.updateAnimation$default(itemInfo, t, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, 0, 32, null);
                        LazyLayoutItemAnimation[] animations = itemInfo.getAnimations();
                        int length2 = animations.length;
                        int i16 = 0;
                        while (i16 < length2) {
                            LazyLayoutItemAnimation lazyLayoutItemAnimation2 = animations[i16];
                            LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr2 = animations;
                            if (lazyLayoutItemAnimation2 == null) {
                                i8 = index;
                            } else {
                                i8 = index;
                                if (!IntOffset.m7143equalsimpl0(lazyLayoutItemAnimation2.getRawOffset(), LazyLayoutItemAnimation.INSTANCE.m1198getNotInitializednOccac())) {
                                    lazyLayoutItemAnimation2.m1197setRawOffsetgyyYBs(IntOffset.m7148plusqkQi6aY(lazyLayoutItemAnimation2.getRawOffset(), j));
                                }
                            }
                            i16++;
                            index = i8;
                            animations = lazyLayoutItemAnimationArr2;
                        }
                        if (z7) {
                            for (LazyLayoutItemAnimation lazyLayoutItemAnimation3 : itemInfo.getAnimations()) {
                                if (lazyLayoutItemAnimation3 != null) {
                                    if (lazyLayoutItemAnimation3.isDisappearanceAnimationInProgress()) {
                                        this.disappearingItems.remove(lazyLayoutItemAnimation3);
                                        DrawModifierNode drawModifierNode = this.displayingNode;
                                        if (drawModifierNode != null) {
                                            DrawModifierNodeKt.invalidateDraw(drawModifierNode);
                                            Unit unit2 = Unit.INSTANCE;
                                        }
                                    }
                                    lazyLayoutItemAnimation3.animateAppearance();
                                }
                            }
                        }
                        startPlacementAnimationsIfNeeded$default(this, t, false, 2, null);
                    }
                }
            } else {
                list = list2;
                i5 = i14;
                i6 = i10;
                i7 = size2;
                removeInfoForKey(t.getKey());
            }
            i15++;
            list2 = list;
            i14 = i5;
            size2 = i7;
            i10 = i6;
        }
        int i17 = laneCount;
        int[] iArr4 = new int[i17];
        for (int i18 = 0; i18 < i17; i18++) {
            iArr4[i18] = 0;
        }
        int[] iArr5 = iArr4;
        if (z5 && lazyLayoutKeyIndexMap2 != null) {
            if (!this.movingInFromStartBound.isEmpty()) {
                List<T> list3 = this.movingInFromStartBound;
                if (list3.size() > 1) {
                    CollectionsKt.sortWith(list3, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$1
                        @Override // java.util.Comparator
                        public final int compare(T t2, T t3) {
                            LazyLayoutMeasuredItem it = (LazyLayoutMeasuredItem) t3;
                            LazyLayoutMeasuredItem it2 = (LazyLayoutMeasuredItem) t2;
                            return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap2.getIndex(it.getKey())), Integer.valueOf(lazyLayoutKeyIndexMap2.getIndex(it2.getKey())));
                        }
                    });
                }
                List<T> list4 = this.movingInFromStartBound;
                int size3 = list4.size();
                int i19 = 0;
                while (i19 < size3) {
                    T t2 = list4.get(i19);
                    initializeAnimation$default(this, t2, layoutMinOffset - updateAndReturnOffsetFor(iArr5, t2), null, 4, null);
                    startPlacementAnimationsIfNeeded$default(this, t2, false, 2, null);
                    i19++;
                    j = j;
                }
                ArraysKt.fill$default(iArr5, 0, 0, 0, 6, (Object) null);
            }
            if (!this.movingInFromEndBound.isEmpty()) {
                List<T> list5 = this.movingInFromEndBound;
                if (list5.size() > 1) {
                    CollectionsKt.sortWith(list5, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t3, T t4) {
                            LazyLayoutMeasuredItem it = (LazyLayoutMeasuredItem) t3;
                            LazyLayoutMeasuredItem it2 = (LazyLayoutMeasuredItem) t4;
                            return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap2.getIndex(it.getKey())), Integer.valueOf(lazyLayoutKeyIndexMap2.getIndex(it2.getKey())));
                        }
                    });
                }
                List<T> list6 = this.movingInFromEndBound;
                int size4 = list6.size();
                for (int i20 = 0; i20 < size4; i20++) {
                    T t3 = list6.get(i20);
                    initializeAnimation$default(this, t3, (layoutMaxOffset + updateAndReturnOffsetFor(iArr5, t3)) - t3.getMainAxisSizeWithSpacings(), null, 4, null);
                    startPlacementAnimationsIfNeeded$default(this, t3, false, 2, null);
                }
                ArraysKt.fill$default(iArr5, 0, 0, 0, 6, (Object) null);
            }
        }
        MutableScatterSet<Object> mutableScatterSet3 = this.movingAwayKeys;
        int i21 = 0;
        Object[] objArr5 = mutableScatterSet3.elements;
        MutableScatterSet<Object> mutableScatterSet4 = mutableScatterSet3;
        int i22 = 0;
        long[] jArr5 = mutableScatterSet4.metadata;
        int length3 = jArr5.length - 2;
        int i23 = 0;
        if (0 <= length3) {
            while (true) {
                long j3 = jArr5[i23];
                MutableScatterSet<Object> mutableScatterSet5 = mutableScatterSet3;
                int i24 = i21;
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) == -9187201950435737472L) {
                    objArr = objArr5;
                    mutableScatterSet = mutableScatterSet4;
                    i = i22;
                    jArr = jArr5;
                    iArr = iArr5;
                    lazyLayoutKeyIndexMap = keyIndexMap;
                } else {
                    int i25 = 8 - ((~(i23 - length3)) >>> 31);
                    int i26 = 0;
                    while (i26 < i25) {
                        if (((j3 & 255) < 128 ? 1 : 0) == 0) {
                            objArr2 = objArr5;
                            mutableScatterSet2 = mutableScatterSet4;
                            i2 = i22;
                            jArr2 = jArr5;
                            iArr3 = iArr5;
                        } else {
                            Object obj = objArr5[(i23 << 3) + i26];
                            objArr2 = objArr5;
                            LazyLayoutItemAnimator<T>.ItemInfo itemInfo3 = this.keyToItemInfoMap.get(obj);
                            Intrinsics.checkNotNull(itemInfo3);
                            LazyLayoutItemAnimator<T>.ItemInfo itemInfo4 = itemInfo3;
                            mutableScatterSet2 = mutableScatterSet4;
                            i2 = i22;
                            int index2 = keyIndexMap.getIndex(obj);
                            jArr2 = jArr5;
                            itemInfo4.setSpan(Math.min(i17, itemInfo4.getSpan()));
                            itemInfo4.setLane(Math.min(i17 - itemInfo4.getSpan(), itemInfo4.getLane()));
                            if (index2 == -1) {
                                LazyLayoutItemAnimation[] animations2 = itemInfo4.getAnimations();
                                int i27 = 0;
                                boolean z8 = false;
                                int length4 = animations2.length;
                                int i28 = 0;
                                while (i28 < length4) {
                                    LazyLayoutItemAnimation lazyLayoutItemAnimation4 = animations2[i28];
                                    int i29 = i27 + 1;
                                    LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr3 = animations2;
                                    if (lazyLayoutItemAnimation4 == null) {
                                        i4 = length4;
                                    } else if (lazyLayoutItemAnimation4.isDisappearanceAnimationInProgress()) {
                                        z8 = true;
                                        i4 = length4;
                                    } else if (lazyLayoutItemAnimation4.isDisappearanceAnimationFinished()) {
                                        lazyLayoutItemAnimation4.release();
                                        itemInfo4.getAnimations()[i27] = null;
                                        i4 = length4;
                                        this.disappearingItems.remove(lazyLayoutItemAnimation4);
                                        DrawModifierNode drawModifierNode2 = this.displayingNode;
                                        if (drawModifierNode2 != null) {
                                            DrawModifierNodeKt.invalidateDraw(drawModifierNode2);
                                            Unit unit3 = Unit.INSTANCE;
                                        }
                                    } else {
                                        i4 = length4;
                                        if (lazyLayoutItemAnimation4.getLayer() != null) {
                                            lazyLayoutItemAnimation4.animateDisappearance();
                                        }
                                        if (lazyLayoutItemAnimation4.isDisappearanceAnimationInProgress()) {
                                            this.disappearingItems.add(lazyLayoutItemAnimation4);
                                            DrawModifierNode drawModifierNode3 = this.displayingNode;
                                            if (drawModifierNode3 != null) {
                                                DrawModifierNodeKt.invalidateDraw(drawModifierNode3);
                                                Unit unit4 = Unit.INSTANCE;
                                            }
                                            z8 = true;
                                        } else {
                                            lazyLayoutItemAnimation4.release();
                                            itemInfo4.getAnimations()[i27] = null;
                                        }
                                    }
                                    i28++;
                                    i27 = i29;
                                    length4 = i4;
                                    animations2 = lazyLayoutItemAnimationArr3;
                                }
                                if (z8) {
                                    iArr3 = iArr5;
                                } else {
                                    removeInfoForKey(obj);
                                    iArr3 = iArr5;
                                }
                            } else {
                                Constraints constraints = itemInfo4.getConstraints();
                                Intrinsics.checkNotNull(constraints);
                                LazyLayoutMeasuredItem lazyLayoutMeasuredItemMo1148getAndMeasurehBUhpc = itemProvider.mo1148getAndMeasurehBUhpc(index2, itemInfo4.getLane(), itemInfo4.getSpan(), constraints.getValue());
                                lazyLayoutMeasuredItemMo1148getAndMeasurehBUhpc.setNonScrollableItem(true);
                                LazyLayoutItemAnimation[] animations3 = itemInfo4.getAnimations();
                                int length5 = animations3.length;
                                iArr3 = iArr5;
                                int i30 = 0;
                                while (true) {
                                    if (i30 >= length5) {
                                        z2 = false;
                                        break;
                                    }
                                    LazyLayoutItemAnimation lazyLayoutItemAnimation5 = animations3[i30];
                                    if (lazyLayoutItemAnimation5 != null) {
                                        lazyLayoutItemAnimationArr = animations3;
                                        i3 = length5;
                                        z3 = lazyLayoutItemAnimation5.isPlacementAnimationInProgress();
                                        if (z3) {
                                            z2 = true;
                                            break;
                                        } else {
                                            i30++;
                                            animations3 = lazyLayoutItemAnimationArr;
                                            length5 = i3;
                                        }
                                    } else {
                                        lazyLayoutItemAnimationArr = animations3;
                                        i3 = length5;
                                    }
                                    if (z3) {
                                        z2 = true;
                                        break;
                                    } else {
                                        i30++;
                                        animations3 = lazyLayoutItemAnimationArr;
                                        length5 = i3;
                                    }
                                }
                                if (z2) {
                                    itemInfo4.updateAnimation(lazyLayoutMeasuredItemMo1148getAndMeasurehBUhpc, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, itemInfo4.getCrossAxisOffset());
                                    if (index2 < this.firstVisibleIndex) {
                                        this.movingAwayToStartBound.add((T) lazyLayoutMeasuredItemMo1148getAndMeasurehBUhpc);
                                    } else {
                                        this.movingAwayToEndBound.add((T) lazyLayoutMeasuredItemMo1148getAndMeasurehBUhpc);
                                    }
                                } else if (lazyLayoutKeyIndexMap2 != null && index2 == lazyLayoutKeyIndexMap2.getIndex(obj)) {
                                    removeInfoForKey(obj);
                                } else {
                                    itemInfo4.updateAnimation(lazyLayoutMeasuredItemMo1148getAndMeasurehBUhpc, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, itemInfo4.getCrossAxisOffset());
                                    if (index2 < this.firstVisibleIndex) {
                                        this.movingAwayToStartBound.add((T) lazyLayoutMeasuredItemMo1148getAndMeasurehBUhpc);
                                    } else {
                                        this.movingAwayToEndBound.add((T) lazyLayoutMeasuredItemMo1148getAndMeasurehBUhpc);
                                    }
                                }
                            }
                        }
                        j3 >>= 8;
                        i26++;
                        i17 = laneCount;
                        objArr5 = objArr2;
                        mutableScatterSet4 = mutableScatterSet2;
                        i22 = i2;
                        jArr5 = jArr2;
                        iArr5 = iArr3;
                    }
                    objArr = objArr5;
                    mutableScatterSet = mutableScatterSet4;
                    i = i22;
                    jArr = jArr5;
                    iArr = iArr5;
                    lazyLayoutKeyIndexMap = keyIndexMap;
                    if (i25 != 8) {
                        break;
                    }
                }
                if (i23 == length3) {
                    break;
                }
                i23++;
                i17 = laneCount;
                mutableScatterSet3 = mutableScatterSet5;
                i21 = i24;
                objArr5 = objArr;
                mutableScatterSet4 = mutableScatterSet;
                i22 = i;
                jArr5 = jArr;
                iArr5 = iArr;
            }
        } else {
            iArr = iArr5;
            lazyLayoutKeyIndexMap = keyIndexMap;
        }
        if (this.movingAwayToStartBound.isEmpty()) {
            iArr2 = iArr;
        } else {
            List<T> list7 = this.movingAwayToStartBound;
            if (list7.size() > 1) {
                CollectionsKt.sortWith(list7, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$2
                    @Override // java.util.Comparator
                    public final int compare(T t4, T t5) {
                        LazyLayoutMeasuredItem it = (LazyLayoutMeasuredItem) t5;
                        LazyLayoutMeasuredItem it2 = (LazyLayoutMeasuredItem) t4;
                        return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(it.getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(it2.getKey())));
                    }
                });
            }
            List<T> list8 = this.movingAwayToStartBound;
            int i31 = 0;
            int size5 = list8.size();
            while (i31 < size5) {
                T t4 = list8.get(i31);
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo5 = this.keyToItemInfoMap.get(t4.getKey());
                Intrinsics.checkNotNull(itemInfo5);
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo6 = itemInfo5;
                int[] iArr6 = iArr;
                int iUpdateAndReturnOffsetFor = updateAndReturnOffsetFor(iArr6, t4);
                if (isLookingAhead) {
                    layoutMinOffset2 = getMainAxisOffset((LazyLayoutMeasuredItem) CollectionsKt.first((List) positionedItems));
                } else {
                    layoutMinOffset2 = itemInfo6.getLayoutMinOffset();
                }
                List<T> list9 = list8;
                t4.position(layoutMinOffset2 - iUpdateAndReturnOffsetFor, itemInfo6.getCrossAxisOffset(), layoutWidth, layoutHeight);
                if (z5) {
                    startPlacementAnimationsIfNeeded(t4, true);
                }
                i31++;
                iArr = iArr6;
                list8 = list9;
            }
            iArr2 = iArr;
            ArraysKt.fill$default(iArr2, 0, 0, 0, 6, (Object) null);
        }
        if (!this.movingAwayToEndBound.isEmpty()) {
            List<T> list10 = this.movingAwayToEndBound;
            if (list10.size() > 1) {
                CollectionsKt.sortWith(list10, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$2
                    @Override // java.util.Comparator
                    public final int compare(T t5, T t6) {
                        LazyLayoutMeasuredItem it = (LazyLayoutMeasuredItem) t5;
                        LazyLayoutMeasuredItem it2 = (LazyLayoutMeasuredItem) t6;
                        return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(it.getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(it2.getKey())));
                    }
                });
            }
            List<T> list11 = this.movingAwayToEndBound;
            int i32 = 0;
            int size6 = list11.size();
            while (i32 < size6) {
                T t5 = list11.get(i32);
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo7 = this.keyToItemInfoMap.get(t5.getKey());
                Intrinsics.checkNotNull(itemInfo7);
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo8 = itemInfo7;
                int iUpdateAndReturnOffsetFor2 = updateAndReturnOffsetFor(iArr2, t5);
                if (isLookingAhead) {
                    layoutMaxOffset2 = getMainAxisOffset((LazyLayoutMeasuredItem) CollectionsKt.last((List) positionedItems));
                } else {
                    layoutMaxOffset2 = itemInfo8.getLayoutMaxOffset() - t5.getMainAxisSizeWithSpacings();
                }
                List<T> list12 = list11;
                t5.position(layoutMaxOffset2 + iUpdateAndReturnOffsetFor2, itemInfo8.getCrossAxisOffset(), layoutWidth, layoutHeight);
                if (z5) {
                    startPlacementAnimationsIfNeeded(t5, true);
                }
                i32++;
                list11 = list12;
            }
        }
        List<T> list13 = this.movingAwayToStartBound;
        CollectionsKt.reverse(list13);
        Unit unit5 = Unit.INSTANCE;
        positionedItems.addAll(0, list13);
        positionedItems.addAll(this.movingAwayToEndBound);
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }

    private final void removeInfoForKey(Object key) {
        LazyLayoutItemAnimation[] animations;
        LazyLayoutItemAnimator<T>.ItemInfo itemInfoRemove = this.keyToItemInfoMap.remove(key);
        if (itemInfoRemove == null || (animations = itemInfoRemove.getAnimations()) == null) {
            return;
        }
        for (LazyLayoutItemAnimation lazyLayoutItemAnimation : animations) {
            if (lazyLayoutItemAnimation != null) {
                lazyLayoutItemAnimation.release();
            }
        }
    }

    public final void reset() {
        ScatterMap this_$iv;
        int $i$f$forEachValue;
        if (this.keyToItemInfoMap.isNotEmpty()) {
            ScatterMap this_$iv2 = this.keyToItemInfoMap;
            int $i$f$forEachValue2 = 0;
            Object[] v$iv = this_$iv2.values;
            long[] m$iv$iv = this_$iv2.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                        this_$iv = this_$iv2;
                        $i$f$forEachValue = $i$f$forEachValue2;
                    } else {
                        int i = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (value$iv$iv$iv < 128) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                ItemInfo it = (ItemInfo) v$iv[index$iv$iv];
                                for (LazyLayoutItemAnimation lazyLayoutItemAnimation : it.getAnimations()) {
                                    if (lazyLayoutItemAnimation != null) {
                                        lazyLayoutItemAnimation.release();
                                    }
                                }
                            }
                            slot$iv$iv >>= 8;
                            j$iv$iv++;
                            i = 8;
                            this_$iv2 = this_$iv2;
                            $i$f$forEachValue2 = $i$f$forEachValue2;
                        }
                        this_$iv = this_$iv2;
                        $i$f$forEachValue = $i$f$forEachValue2;
                        if (bitCount$iv$iv != i) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    this_$iv2 = this_$iv;
                    $i$f$forEachValue2 = $i$f$forEachValue;
                }
            }
            this.keyToItemInfoMap.clear();
        }
        this.keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
        this.firstVisibleIndex = -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void initializeAnimation$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, int i, ItemInfo itemInfo, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            LazyLayoutItemAnimator<T>.ItemInfo itemInfo2 = lazyLayoutItemAnimator.keyToItemInfoMap.get(lazyLayoutMeasuredItem.getKey());
            Intrinsics.checkNotNull(itemInfo2);
            itemInfo = itemInfo2;
        }
        lazyLayoutItemAnimator.initializeAnimation(lazyLayoutMeasuredItem, i, itemInfo);
    }

    private final void initializeAnimation(T item, int mainAxisOffset, LazyLayoutItemAnimator<T>.ItemInfo itemInfo) {
        long targetFirstPlaceableOffset;
        int i = 0;
        long firstPlaceableOffset = item.mo1146getOffsetBjo55l4(0);
        if (item.getIsVertical()) {
            targetFirstPlaceableOffset = IntOffset.m7140copyiSbpLlY$default(firstPlaceableOffset, 0, mainAxisOffset, 1, null);
        } else {
            targetFirstPlaceableOffset = IntOffset.m7140copyiSbpLlY$default(firstPlaceableOffset, mainAxisOffset, 0, 2, null);
        }
        LazyLayoutItemAnimation[] animations = itemInfo.getAnimations();
        int $i$f$forEachIndexed = 0;
        int index$iv = 0;
        int length = animations.length;
        while (i < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i];
            int index$iv2 = index$iv + 1;
            if (lazyLayoutItemAnimation != null) {
                long diffToFirstPlaceableOffset = IntOffset.m7147minusqkQi6aY(item.mo1146getOffsetBjo55l4(index$iv), firstPlaceableOffset);
                lazyLayoutItemAnimation.m1197setRawOffsetgyyYBs(IntOffset.m7148plusqkQi6aY(targetFirstPlaceableOffset, diffToFirstPlaceableOffset));
            }
            i++;
            index$iv = index$iv2;
            animations = animations;
            $i$f$forEachIndexed = $i$f$forEachIndexed;
        }
    }

    static /* synthetic */ void startPlacementAnimationsIfNeeded$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        lazyLayoutItemAnimator.startPlacementAnimationsIfNeeded(lazyLayoutMeasuredItem, z);
    }

    private final void startPlacementAnimationsIfNeeded(T item, boolean isMovingAway) {
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo = this.keyToItemInfoMap.get(item.getKey());
        Intrinsics.checkNotNull(itemInfo);
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo2 = itemInfo;
        LazyLayoutItemAnimation[] animations = itemInfo2.getAnimations();
        int index$iv = 0;
        int length = animations.length;
        int i = 0;
        while (i < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i];
            int index$iv2 = index$iv + 1;
            if (lazyLayoutItemAnimation != null) {
                long newTarget = item.mo1146getOffsetBjo55l4(index$iv);
                long currentTarget = lazyLayoutItemAnimation.getRawOffset();
                if (!IntOffset.m7143equalsimpl0(currentTarget, LazyLayoutItemAnimation.INSTANCE.m1198getNotInitializednOccac()) && !IntOffset.m7143equalsimpl0(currentTarget, newTarget)) {
                    lazyLayoutItemAnimation.m1190animatePlacementDeltaar5cAso(IntOffset.m7147minusqkQi6aY(newTarget, currentTarget), isMovingAway);
                }
                lazyLayoutItemAnimation.m1197setRawOffsetgyyYBs(newTarget);
            } else {
                animations = animations;
            }
            i++;
            index$iv = index$iv2;
            animations = animations;
            itemInfo2 = itemInfo2;
        }
    }

    public final LazyLayoutItemAnimation getAnimation(Object key, int placeableIndex) {
        LazyLayoutItemAnimation[] animations;
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo = this.keyToItemInfoMap.get(key);
        if (itemInfo == null || (animations = itemInfo.getAnimations()) == null) {
            return null;
        }
        return animations[placeableIndex];
    }

    private final int updateAndReturnOffsetFor(int[] $this$updateAndReturnOffsetFor, T t) {
        int lane = t.getLane();
        int span = t.getSpan();
        int maxOffset = 0;
        int i = lane + span;
        for (int i2 = lane; i2 < i; i2++) {
            $this$updateAndReturnOffsetFor[i2] = $this$updateAndReturnOffsetFor[i2] + t.getMainAxisSizeWithSpacings();
            maxOffset = Math.max(maxOffset, $this$updateAndReturnOffsetFor[i2]);
        }
        return maxOffset;
    }

    /* JADX INFO: renamed from: getMinSizeToFitDisappearingItems-YbymL2g, reason: not valid java name */
    public final long m1199getMinSizeToFitDisappearingItemsYbymL2g() {
        long size = IntSize.INSTANCE.m7191getZeroYbymL2g();
        List<LazyLayoutItemAnimation> list = this.disappearingItems;
        int size2 = list.size();
        for (int index$iv = 0; index$iv < size2; index$iv++) {
            Object item$iv = list.get(index$iv);
            LazyLayoutItemAnimation it = (LazyLayoutItemAnimation) item$iv;
            GraphicsLayer layer = it.getLayer();
            if (layer != null) {
                size = IntSizeKt.IntSize(Math.max(IntSize.m7186getWidthimpl(size), IntOffset.m7144getXimpl(it.getRawOffset()) + IntSize.m7186getWidthimpl(layer.getSize())), Math.max(IntSize.m7185getHeightimpl(size), IntOffset.m7145getYimpl(it.getRawOffset()) + IntSize.m7185getHeightimpl(layer.getSize())));
            }
        }
        return size;
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    private final boolean getHasAnimations(T t) {
        int placeablesCount = t.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            int index = i;
            if (LazyLayoutItemAnimatorKt.getSpecs(t.getParentData(index)) != null) {
                return true;
            }
        }
        return false;
    }

    private final int getMainAxisOffset(LazyLayoutMeasuredItem $this$mainAxisOffset) {
        long it = $this$mainAxisOffset.mo1146getOffsetBjo55l4(0);
        return $this$mainAxisOffset.getIsVertical() ? IntOffset.m7145getYimpl(it) : IntOffset.m7144getXimpl(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCrossAxisOffset(LazyLayoutMeasuredItem $this$crossAxisOffset) {
        long it = $this$crossAxisOffset.mo1146getOffsetBjo55l4(0);
        return !$this$crossAxisOffset.getIsVertical() ? IntOffset.m7145getYimpl(it) : IntOffset.m7144getXimpl(it);
    }

    /* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J=\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00028\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010*R0\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u001e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u001a\u0010 \u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0013\"\u0004\b\"\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006+"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;", "", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "<set-?>", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "animations", "getAnimations", "()[Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "getConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "setConstraints-_Sx5XlM", "(Landroidx/compose/ui/unit/Constraints;)V", "crossAxisOffset", "", "getCrossAxisOffset", "()I", "setCrossAxisOffset", "(I)V", "isRunningPlacement", "", "()Z", "lane", "getLane", "setLane", "layoutMaxOffset", "getLayoutMaxOffset", "layoutMinOffset", "getLayoutMinOffset", "span", "getSpan", "setSpan", "updateAnimation", "", "positionedItem", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;III)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class ItemInfo {
        private Constraints constraints;
        private int crossAxisOffset;
        private int lane;
        private int layoutMaxOffset;
        private int layoutMinOffset;
        private LazyLayoutItemAnimation[] animations = LazyLayoutItemAnimatorKt.EmptyArray;
        private int span = 1;

        public ItemInfo() {
        }

        public final LazyLayoutItemAnimation[] getAnimations() {
            return this.animations;
        }

        /* JADX INFO: renamed from: getConstraints-DWUhwKw, reason: not valid java name and from getter */
        public final Constraints getConstraints() {
            return this.constraints;
        }

        /* JADX INFO: renamed from: setConstraints-_Sx5XlM, reason: not valid java name */
        public final void m1201setConstraints_Sx5XlM(Constraints constraints) {
            this.constraints = constraints;
        }

        public final int getCrossAxisOffset() {
            return this.crossAxisOffset;
        }

        public final void setCrossAxisOffset(int i) {
            this.crossAxisOffset = i;
        }

        public final int getLane() {
            return this.lane;
        }

        public final void setLane(int i) {
            this.lane = i;
        }

        public final int getSpan() {
            return this.span;
        }

        public final void setSpan(int i) {
            this.span = i;
        }

        private final boolean isRunningPlacement() {
            LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr = this.animations;
            int length = lazyLayoutItemAnimationArr.length;
            for (int i = 0; i < length; i++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation = lazyLayoutItemAnimationArr[i];
                if (lazyLayoutItemAnimation != null && lazyLayoutItemAnimation.getIsRunningMovingAwayAnimation()) {
                    return true;
                }
            }
            return false;
        }

        public final int getLayoutMinOffset() {
            return this.layoutMinOffset;
        }

        public final int getLayoutMaxOffset() {
            return this.layoutMaxOffset;
        }

        public static /* synthetic */ void updateAnimation$default(ItemInfo itemInfo, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int i, int i2, int i3, int i4, Object obj) {
            int crossAxisOffset;
            if ((i4 & 32) != 0) {
                crossAxisOffset = LazyLayoutItemAnimator.this.getCrossAxisOffset(lazyLayoutMeasuredItem);
            } else {
                crossAxisOffset = i3;
            }
            itemInfo.updateAnimation(lazyLayoutMeasuredItem, coroutineScope, graphicsContext, i, i2, crossAxisOffset);
        }

        public final void updateAnimation(T positionedItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int layoutMinOffset, int layoutMaxOffset, int crossAxisOffset) {
            if (!isRunningPlacement()) {
                this.layoutMinOffset = layoutMinOffset;
                this.layoutMaxOffset = layoutMaxOffset;
            }
            int length = this.animations.length;
            for (int i = positionedItem.getPlaceablesCount(); i < length; i++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation = this.animations[i];
                if (lazyLayoutItemAnimation != null) {
                    lazyLayoutItemAnimation.release();
                }
            }
            if (this.animations.length != positionedItem.getPlaceablesCount()) {
                Object[] objArrCopyOf = Arrays.copyOf(this.animations, positionedItem.getPlaceablesCount());
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
                this.animations = (LazyLayoutItemAnimation[]) objArrCopyOf;
            }
            this.constraints = Constraints.m6957boximpl(positionedItem.getConstraints());
            this.crossAxisOffset = crossAxisOffset;
            this.lane = positionedItem.getLane();
            this.span = positionedItem.getSpan();
            int placeablesCount = positionedItem.getPlaceablesCount();
            final LazyLayoutItemAnimator<T> lazyLayoutItemAnimator = LazyLayoutItemAnimator.this;
            for (int i2 = 0; i2 < placeablesCount; i2++) {
                int index = i2;
                LazyLayoutAnimationSpecsNode specs = LazyLayoutItemAnimatorKt.getSpecs(positionedItem.getParentData(index));
                if (specs == null) {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation2 = this.animations[index];
                    if (lazyLayoutItemAnimation2 != null) {
                        lazyLayoutItemAnimation2.release();
                    }
                    this.animations[index] = null;
                } else {
                    LazyLayoutItemAnimation it = this.animations[index];
                    if (it == null) {
                        it = new LazyLayoutItemAnimation(coroutineScope, graphicsContext, new Function0<Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$ItemInfo$updateAnimation$1$animation$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                DrawModifierNode drawModifierNode = ((LazyLayoutItemAnimator) lazyLayoutItemAnimator).displayingNode;
                                if (drawModifierNode != null) {
                                    DrawModifierNodeKt.invalidateDraw(drawModifierNode);
                                }
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }
                        });
                        this.animations[index] = it;
                    }
                    LazyLayoutItemAnimation animation = it;
                    animation.setFadeInSpec(specs.getFadeInSpec());
                    animation.setPlacementSpec(specs.getPlacementSpec());
                    animation.setFadeOutSpec(specs.getFadeOutSpec());
                }
            }
        }
    }

    /* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u0007\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\b\u0010\b\u001a\u00020\u0002H\u0016J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0016J\f\u0010\u0014\u001a\u00020\u0012*\u00020\u0015H\u0016R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "component1", "copy", "create", "equals", "", "other", "", "hashCode", "", "toString", "", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final /* data */ class DisplayingDisappearingItemsElement extends ModifierNodeElement<DisplayingDisappearingItemsNode> {
        private final LazyLayoutItemAnimator<?> animator;

        private final LazyLayoutItemAnimator<?> component1() {
            return this.animator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DisplayingDisappearingItemsElement copy$default(DisplayingDisappearingItemsElement displayingDisappearingItemsElement, LazyLayoutItemAnimator lazyLayoutItemAnimator, int i, Object obj) {
            if ((i & 1) != 0) {
                lazyLayoutItemAnimator = displayingDisappearingItemsElement.animator;
            }
            return displayingDisappearingItemsElement.copy(lazyLayoutItemAnimator);
        }

        public final DisplayingDisappearingItemsElement copy(LazyLayoutItemAnimator<?> animator) {
            return new DisplayingDisappearingItemsElement(animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DisplayingDisappearingItemsElement) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsElement) other).animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return this.animator.hashCode();
        }

        public String toString() {
            return "DisplayingDisappearingItemsElement(animator=" + this.animator + ')';
        }

        public DisplayingDisappearingItemsElement(LazyLayoutItemAnimator<?> lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public DisplayingDisappearingItemsNode getNode() {
            return new DisplayingDisappearingItemsNode(this.animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(DisplayingDisappearingItemsNode node) {
            node.setAnimator(this.animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
            $this$inspectableProperties.setName("DisplayingDisappearingItemsElement");
        }
    }

    /* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u0007\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0012\u0010\u0011\u001a\u00020\u000f2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\f\u0010\u0014\u001a\u00020\u000f*\u00020\u0015H\u0016R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "component1", "copy", "equals", "", "other", "", "hashCode", "", "onAttach", "", "onDetach", "setAnimator", "toString", "", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final /* data */ class DisplayingDisappearingItemsNode extends Modifier.Node implements DrawModifierNode {
        private LazyLayoutItemAnimator<?> animator;

        private final LazyLayoutItemAnimator<?> component1() {
            return this.animator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DisplayingDisappearingItemsNode copy$default(DisplayingDisappearingItemsNode displayingDisappearingItemsNode, LazyLayoutItemAnimator lazyLayoutItemAnimator, int i, Object obj) {
            if ((i & 1) != 0) {
                lazyLayoutItemAnimator = displayingDisappearingItemsNode.animator;
            }
            return displayingDisappearingItemsNode.copy(lazyLayoutItemAnimator);
        }

        public final DisplayingDisappearingItemsNode copy(LazyLayoutItemAnimator<?> animator) {
            return new DisplayingDisappearingItemsNode(animator);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DisplayingDisappearingItemsNode) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsNode) other).animator);
        }

        public int hashCode() {
            return this.animator.hashCode();
        }

        public String toString() {
            return "DisplayingDisappearingItemsNode(animator=" + this.animator + ')';
        }

        public DisplayingDisappearingItemsNode(LazyLayoutItemAnimator<?> lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        @Override // androidx.compose.ui.node.DrawModifierNode
        public void draw(ContentDrawScope $this$draw) {
            List $this$fastForEach$iv = ((LazyLayoutItemAnimator) this.animator).disappearingItems;
            int size = $this$fastForEach$iv.size();
            int index$iv = 0;
            while (index$iv < size) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                LazyLayoutItemAnimation it = (LazyLayoutItemAnimation) item$iv;
                GraphicsLayer layer = it.getLayer();
                if (layer != null) {
                    float x = IntOffset.m7144getXimpl(it.getFinalOffset());
                    float y = IntOffset.m7145getYimpl(it.getFinalOffset());
                    ContentDrawScope $this$translate$iv = $this$draw;
                    float left$iv = x - IntOffset.m7144getXimpl(layer.getTopLeft());
                    float top$iv = y - IntOffset.m7145getYimpl(layer.getTopLeft());
                    $this$translate$iv.getDrawContext().getTransform().translate(left$iv, top$iv);
                    try {
                        GraphicsLayerKt.drawLayer($this$translate$iv, layer);
                        $this$translate$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                    } catch (Throwable th) {
                        $this$translate$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                        throw th;
                    }
                }
                index$iv++;
                $this$fastForEach$iv = $this$fastForEach$iv;
            }
            $this$draw.drawContent();
        }

        @Override // androidx.compose.ui.Modifier.Node
        public void onAttach() {
            ((LazyLayoutItemAnimator) this.animator).displayingNode = this;
        }

        @Override // androidx.compose.ui.Modifier.Node
        public void onDetach() {
            this.animator.reset();
        }

        public final void setAnimator(LazyLayoutItemAnimator<?> animator) {
            if (!Intrinsics.areEqual(this.animator, animator) && getNode().getIsAttached()) {
                this.animator.reset();
                ((LazyLayoutItemAnimator) animator).displayingNode = this;
                this.animator = animator;
            }
        }
    }
}
