package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.util.PointerIdArray;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.PointerInputModifierNodeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HitPathTracker.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u0016\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u0010\u0010 \u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0017\u0010!\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001d0#H\u0082\bJ.\u0010$\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bH\u0016J\u001a\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\r2\u0006\u0010'\u001a\u00020\rH\u0002J\u0006\u0010(\u001a\u00020\u001dJ\u001e\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00000-H\u0016J\b\u0010.\u001a\u00020/H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Landroidx/compose/ui/input/pointer/Node;", "Landroidx/compose/ui/input/pointer/NodeParent;", "modifierNode", "Landroidx/compose/ui/Modifier$Node;", "(Landroidx/compose/ui/Modifier$Node;)V", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "hasExited", "", "isIn", "getModifierNode", "()Landroidx/compose/ui/Modifier$Node;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerIds", "Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "getPointerIds", "()Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "relevantChanges", "Landroidx/collection/LongSparseArray;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "wasIn", "buildCache", "changes", "parentCoordinates", "internalPointerEvent", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "isInBounds", "cleanUpHits", "", "clearCache", "dispatchCancel", "dispatchFinalEventPass", "dispatchIfNeeded", "block", "Lkotlin/Function0;", "dispatchMainEventPass", "hasPositionChanged", "oldEvent", "newEvent", "markIsIn", "removeInvalidPointerIdsAndChanges", "pointerIdValue", "", "hitNodes", "Landroidx/collection/MutableObjectList;", "toString", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Node extends NodeParent {
    public static final int $stable = 8;
    private LayoutCoordinates coordinates;

    /* JADX INFO: renamed from: modifierNode, reason: from kotlin metadata and from toString */
    private final Modifier.Node pointerInputFilter;
    private PointerEvent pointerEvent;
    private boolean wasIn;
    private final PointerIdArray pointerIds = new PointerIdArray();
    private final LongSparseArray<PointerInputChange> relevantChanges = new LongSparseArray<>(2);
    private boolean isIn = true;
    private boolean hasExited = true;

    public Node(Modifier.Node modifierNode) {
        this.pointerInputFilter = modifierNode;
    }

    /* JADX INFO: renamed from: getModifierNode, reason: from getter */
    public final Modifier.Node getPointerInputFilter() {
        return this.pointerInputFilter;
    }

    public final PointerIdArray getPointerIds() {
        return this.pointerIds;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void removeInvalidPointerIdsAndChanges(long pointerIdValue, MutableObjectList<Node> hitNodes) {
        if (this.pointerIds.contains(pointerIdValue) && !hitNodes.contains(this)) {
            this.pointerIds.remove(pointerIdValue);
            this.relevantChanges.remove(pointerIdValue);
        }
        MutableVector<Node> children = getChildren();
        int size$iv = children.getSize();
        if (size$iv <= 0) {
            return;
        }
        int i$iv = 0;
        Object[] content$iv = children.getContent();
        do {
            Node it = (Node) content$iv[i$iv];
            it.removeInvalidPointerIdsAndChanges(pointerIdValue, hitNodes);
            i$iv++;
        } while (i$iv < size$iv);
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchMainEventPass(LongSparseArray<PointerInputChange> changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        int kind$iv;
        MutableVector<Node> children;
        int size$iv;
        Node this_$iv;
        int $i$f$dispatchIfNeeded;
        Node this_$iv2 = this;
        int $i$f$dispatchIfNeeded2 = 0;
        if (!this_$iv2.relevantChanges.isEmpty() && this_$iv2.pointerInputFilter.getIsAttached()) {
            PointerEvent event = this.pointerEvent;
            Intrinsics.checkNotNull(event);
            LayoutCoordinates layoutCoordinates = this.coordinates;
            Intrinsics.checkNotNull(layoutCoordinates);
            long size = layoutCoordinates.mo5878getSizeYbymL2g();
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2 = this.pointerInputFilter;
            int iM6105constructorimpl = NodeKind.m6105constructorimpl(16);
            MutableVector mutableVector = null;
            Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv2;
            while (true) {
                int i = 1;
                if (nodePop == null) {
                    break;
                }
                if (nodePop instanceof PointerInputModifierNode) {
                    PointerInputModifierNode it = (PointerInputModifierNode) nodePop;
                    it.mo562onPointerEventH0pRuoY(event, PointerEventPass.Initial, size);
                    this_$iv = this_$iv2;
                    $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded2;
                } else {
                    Modifier.Node this_$iv$iv = nodePop;
                    if (!((this_$iv$iv.getKindSet() & iM6105constructorimpl) != 0) || !(nodePop instanceof DelegatingNode)) {
                        this_$iv = this_$iv2;
                        $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded2;
                    } else {
                        int count$iv = 0;
                        DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                        while (node$iv$iv != null) {
                            Modifier.Node next$iv = node$iv$iv;
                            if (((next$iv.getKindSet() & iM6105constructorimpl) != 0 ? i : 0) == 0) {
                                this_$iv2 = this_$iv2;
                                $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded2;
                            } else {
                                count$iv++;
                                if (count$iv == i) {
                                    nodePop = next$iv;
                                    this_$iv2 = this_$iv2;
                                    $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded2;
                                } else {
                                    mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                    Modifier.Node theNode$iv = nodePop;
                                    if (theNode$iv != null) {
                                        if (mutableVector != null) {
                                            mutableVector.add(theNode$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(next$iv);
                                    }
                                }
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            this_$iv2 = this_$iv2;
                            $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded2;
                            i = 1;
                        }
                        this_$iv = this_$iv2;
                        $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded2;
                        if (count$iv == 1) {
                            this_$iv2 = this_$iv;
                            $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded;
                        }
                    }
                }
                nodePop = DelegatableNodeKt.pop(mutableVector);
                this_$iv2 = this_$iv;
                $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded;
            }
            if (this.pointerInputFilter.getIsAttached() && (size$iv = (children = getChildren()).getSize()) > 0) {
                int i$iv = 0;
                Object[] content$iv = children.getContent();
                do {
                    Node it2 = (Node) content$iv[i$iv];
                    LongSparseArray<PointerInputChange> longSparseArray = this.relevantChanges;
                    LayoutCoordinates layoutCoordinates2 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates2);
                    it2.dispatchMainEventPass(longSparseArray, layoutCoordinates2, internalPointerEvent, isInBounds);
                    i$iv++;
                } while (i$iv < size$iv);
            }
            if (!this.pointerInputFilter.getIsAttached()) {
                return true;
            }
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = this.pointerInputFilter;
            int kind$iv2 = NodeKind.m6105constructorimpl(16);
            MutableVector mutableVector2 = null;
            Modifier.Node nodePop2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
            while (nodePop2 != null) {
                if (nodePop2 instanceof PointerInputModifierNode) {
                    PointerInputModifierNode it3 = (PointerInputModifierNode) nodePop2;
                    it3.mo562onPointerEventH0pRuoY(event, PointerEventPass.Main, size);
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    kind$iv = kind$iv2;
                } else {
                    Modifier.Node this_$iv$iv3 = nodePop2;
                    if (!((this_$iv$iv3.getKindSet() & kind$iv2) != 0) || !(nodePop2 instanceof DelegatingNode)) {
                        $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        kind$iv = kind$iv2;
                    } else {
                        int count$iv2 = 0;
                        DelegatingNode this_$iv$iv4 = (DelegatingNode) nodePop2;
                        Modifier.Node node$iv$iv2 = this_$iv$iv4.getDelegate();
                        while (node$iv$iv2 != null) {
                            Modifier.Node next$iv2 = node$iv$iv2;
                            if (!((next$iv2.getKindSet() & kind$iv2) != 0)) {
                                $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                kind$iv2 = kind$iv2;
                            } else {
                                count$iv2++;
                                if (count$iv2 == 1) {
                                    nodePop2 = next$iv2;
                                    $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    kind$iv2 = kind$iv2;
                                } else {
                                    mutableVector2 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                    Modifier.Node theNode$iv2 = nodePop2;
                                    if (theNode$iv2 != null) {
                                        if (mutableVector2 != null) {
                                            mutableVector2.add(theNode$iv2);
                                        }
                                        nodePop2 = null;
                                    }
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(next$iv2);
                                    }
                                }
                            }
                            node$iv$iv2 = node$iv$iv2.getChild();
                            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                            kind$iv2 = kind$iv2;
                        }
                        $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        kind$iv = kind$iv2;
                        if (count$iv2 == 1) {
                            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                            kind$iv2 = kind$iv;
                        }
                    }
                }
                nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                kind$iv2 = kind$iv;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        boolean z;
        MutableVector<Node> children;
        int size$iv;
        Node this_$iv;
        int $i$f$dispatchIfNeeded;
        Node this_$iv2 = this;
        int $i$f$dispatchIfNeeded2 = 0;
        boolean z2 = false;
        if (!this_$iv2.relevantChanges.isEmpty() && this_$iv2.pointerInputFilter.getIsAttached()) {
            PointerEvent event = this.pointerEvent;
            Intrinsics.checkNotNull(event);
            LayoutCoordinates layoutCoordinates = this.coordinates;
            Intrinsics.checkNotNull(layoutCoordinates);
            long size = layoutCoordinates.mo5878getSizeYbymL2g();
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv = this.pointerInputFilter;
            int iM6105constructorimpl = NodeKind.m6105constructorimpl(16);
            MutableVector mutableVector = null;
            Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv;
            while (true) {
                int i = 1;
                if (nodePop == null) {
                    break;
                }
                if (nodePop instanceof PointerInputModifierNode) {
                    PointerInputModifierNode it = (PointerInputModifierNode) nodePop;
                    it.mo562onPointerEventH0pRuoY(event, PointerEventPass.Final, size);
                    this_$iv = this_$iv2;
                    $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded2;
                } else {
                    Modifier.Node this_$iv$iv = nodePop;
                    if (!((this_$iv$iv.getKindSet() & iM6105constructorimpl) != 0) || !(nodePop instanceof DelegatingNode)) {
                        this_$iv = this_$iv2;
                        $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded2;
                    } else {
                        int count$iv = 0;
                        DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                        while (node$iv$iv != null) {
                            Modifier.Node next$iv = node$iv$iv;
                            if (((next$iv.getKindSet() & iM6105constructorimpl) != 0 ? i : 0) == 0) {
                                this_$iv2 = this_$iv2;
                                $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded2;
                            } else {
                                count$iv++;
                                if (count$iv == i) {
                                    nodePop = next$iv;
                                    this_$iv2 = this_$iv2;
                                    $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded2;
                                } else {
                                    mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                    Modifier.Node theNode$iv = nodePop;
                                    if (theNode$iv != null) {
                                        if (mutableVector != null) {
                                            mutableVector.add(theNode$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(next$iv);
                                    }
                                }
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            this_$iv2 = this_$iv2;
                            $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded2;
                            i = 1;
                        }
                        this_$iv = this_$iv2;
                        $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded2;
                        if (count$iv == 1) {
                            this_$iv2 = this_$iv;
                            $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded;
                        }
                    }
                }
                nodePop = DelegatableNodeKt.pop(mutableVector);
                this_$iv2 = this_$iv;
                $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded;
            }
            if (!this.pointerInputFilter.getIsAttached() || (size$iv = (children = getChildren()).getSize()) <= 0) {
                z = true;
            } else {
                int i$iv = 0;
                Object[] content$iv = children.getContent();
                do {
                    Node it2 = (Node) content$iv[i$iv];
                    it2.dispatchFinalEventPass(internalPointerEvent);
                    z = true;
                    i$iv++;
                } while (i$iv < size$iv);
            }
            z2 = z;
        }
        boolean result = z2;
        cleanUpHits(internalPointerEvent);
        clearCache();
        return result;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean buildCache(LongSparseArray<PointerInputChange> changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        Object it$iv;
        boolean changed;
        boolean z;
        int j;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        int kind$iv;
        boolean childChanged = super.buildCache(changes, parentCoordinates, internalPointerEvent, isInBounds);
        int i = 1;
        if (!this.pointerInputFilter.getIsAttached()) {
            return true;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2 = this.pointerInputFilter;
        int kind$iv2 = NodeKind.m6105constructorimpl(16);
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv2;
        while (nodePop != null) {
            if (nodePop instanceof PointerInputModifierNode) {
                this.coordinates = PointerInputModifierNodeKt.getLayoutCoordinates((PointerInputModifierNode) nodePop);
                $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
                kind$iv = kind$iv2;
            } else {
                Modifier.Node this_$iv$iv = nodePop;
                if (((this_$iv$iv.getKindSet() & kind$iv2) != 0 ? i : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
                    kind$iv = kind$iv2;
                } else {
                    int count$iv = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if (((next$iv.getKindSet() & kind$iv2) != 0 ? i : 0) != 0) {
                            count$iv++;
                            if (count$iv == i) {
                                nodePop = next$iv;
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                                kind$iv2 = kind$iv2;
                            } else {
                                mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                Modifier.Node theNode$iv = nodePop;
                                if (theNode$iv != null) {
                                    if (mutableVector != null) {
                                        mutableVector.add(theNode$iv);
                                    }
                                    nodePop = null;
                                }
                                if (mutableVector != null) {
                                    mutableVector.add(next$iv);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                            kind$iv2 = kind$iv2;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        kind$iv2 = kind$iv2;
                        i = 1;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
                    kind$iv = kind$iv2;
                    if (count$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        kind$iv2 = kind$iv;
                        i = 1;
                    }
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv;
            kind$iv2 = kind$iv;
            i = 1;
        }
        int j2 = 0;
        int size = changes.size();
        while (j2 < size) {
            long keyValue = changes.keyAt(j2);
            PointerInputChange change = changes.valueAt(j2);
            if (this.pointerIds.contains(keyValue)) {
                long prevPosition = change.getPreviousPosition();
                long currentPosition = change.getPosition();
                if (Offset.m4296isValidimpl(prevPosition) && Offset.m4296isValidimpl(currentPosition)) {
                    ArrayList historical = new ArrayList(change.getHistorical().size());
                    List<HistoricalChange> historical2 = change.getHistorical();
                    int size2 = historical2.size();
                    int index$iv = 0;
                    while (index$iv < size2) {
                        Object item$iv = historical2.get(index$iv);
                        HistoricalChange it = (HistoricalChange) item$iv;
                        int j3 = j2;
                        int i2 = size2;
                        long historicalPosition = it.getPosition();
                        if (Offset.m4296isValidimpl(historicalPosition)) {
                            long uptimeMillis = it.getUptimeMillis();
                            LayoutCoordinates layoutCoordinates = this.coordinates;
                            Intrinsics.checkNotNull(layoutCoordinates);
                            historical.add(new HistoricalChange(uptimeMillis, layoutCoordinates.mo5879localPositionOfR5De75A(parentCoordinates, historicalPosition), it.getOriginalEventPosition(), null));
                        }
                        index$iv++;
                        size2 = i2;
                        historical2 = historical2;
                        j2 = j3;
                    }
                    j = j2;
                    LongSparseArray<PointerInputChange> longSparseArray = this.relevantChanges;
                    LayoutCoordinates layoutCoordinates2 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates2);
                    long jMo5879localPositionOfR5De75A = layoutCoordinates2.mo5879localPositionOfR5De75A(parentCoordinates, prevPosition);
                    LayoutCoordinates layoutCoordinates3 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates3);
                    longSparseArray.put(keyValue, change.m5736copyOHpmEuE((731 & 1) != 0 ? change.id : 0L, (731 & 2) != 0 ? change.uptimeMillis : 0L, (731 & 4) != 0 ? change.position : layoutCoordinates3.mo5879localPositionOfR5De75A(parentCoordinates, currentPosition), (731 & 8) != 0 ? change.pressed : false, (731 & 16) != 0 ? change.previousUptimeMillis : 0L, (731 & 32) != 0 ? change.previousPosition : jMo5879localPositionOfR5De75A, (731 & 64) != 0 ? change.previousPressed : false, (731 & 128) != 0 ? change.type : 0, historical, (731 & 512) != 0 ? change.scrollDelta : 0L));
                } else {
                    j = j2;
                }
            } else {
                j = j2;
            }
            j2 = j + 1;
            size = size;
            childChanged = childChanged;
        }
        boolean childChanged2 = childChanged;
        if (this.relevantChanges.isEmpty()) {
            this.pointerIds.clear();
            getChildren().clear();
            return true;
        }
        PointerIdArray this_$iv = this.pointerIds;
        for (int i3 = this_$iv.getSize() - 1; -1 < i3; i3--) {
            long pointerId = this.pointerIds.m5836get_I2yYro(i3);
            if (!changes.containsKey(pointerId)) {
                this.pointerIds.removeAt(i3);
            }
        }
        ArrayList changesList = new ArrayList(this.relevantChanges.size());
        int size3 = this.relevantChanges.size();
        for (int i4 = 0; i4 < size3; i4++) {
            changesList.add(this.relevantChanges.valueAt(i4));
        }
        PointerEvent event = new PointerEvent(changesList, internalPointerEvent);
        List<PointerInputChange> changes2 = event.getChanges();
        int index$iv$iv = 0;
        int size4 = changes2.size();
        while (true) {
            if (index$iv$iv >= size4) {
                it$iv = null;
                break;
            }
            Object item$iv$iv = changes2.get(index$iv$iv);
            it$iv = item$iv$iv;
            if (internalPointerEvent.m5670activeHoverEvent0FcD4WY(((PointerInputChange) it$iv).getId())) {
                break;
            }
            index$iv$iv++;
        }
        PointerInputChange activeHoverChange = (PointerInputChange) it$iv;
        if (activeHoverChange != null) {
            if (isInBounds) {
                changed = false;
                if (this.isIn) {
                    z = true;
                } else if (activeHoverChange.getPressed() || activeHoverChange.getPreviousPressed()) {
                    LayoutCoordinates layoutCoordinates4 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates4);
                    long size5 = layoutCoordinates4.mo5878getSizeYbymL2g();
                    z = true;
                    this.isIn = !PointerEventKt.m5685isOutOfBoundsO0kMr_c(activeHoverChange, size5);
                } else {
                    z = true;
                }
            } else {
                changed = false;
                this.isIn = false;
                z = true;
            }
            if (this.isIn != this.wasIn && (PointerEventType.m5690equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5696getMove7fucELk()) || PointerEventType.m5690equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5694getEnter7fucELk()) || PointerEventType.m5690equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5695getExit7fucELk()))) {
                event.m5684setTypeEhbLWgg$ui_release(this.isIn ? PointerEventType.INSTANCE.m5694getEnter7fucELk() : PointerEventType.INSTANCE.m5695getExit7fucELk());
            } else if (PointerEventType.m5690equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5694getEnter7fucELk()) && this.wasIn && !this.hasExited) {
                event.m5684setTypeEhbLWgg$ui_release(PointerEventType.INSTANCE.m5696getMove7fucELk());
            } else if (PointerEventType.m5690equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5695getExit7fucELk()) && this.isIn && activeHoverChange.getPressed()) {
                event.m5684setTypeEhbLWgg$ui_release(PointerEventType.INSTANCE.m5696getMove7fucELk());
            }
        } else {
            changed = false;
            z = true;
        }
        if (childChanged2 || !PointerEventType.m5690equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5696getMove7fucELk()) || hasPositionChanged(this.pointerEvent, event)) {
            changed = z;
        }
        this.pointerEvent = event;
        return changed;
    }

    private final boolean hasPositionChanged(PointerEvent oldEvent, PointerEvent newEvent) {
        if (oldEvent == null || oldEvent.getChanges().size() != newEvent.getChanges().size()) {
            return true;
        }
        int size = newEvent.getChanges().size();
        for (int i = 0; i < size; i++) {
            PointerInputChange old = oldEvent.getChanges().get(i);
            PointerInputChange current = newEvent.getChanges().get(i);
            if (!Offset.m4290equalsimpl0(old.getPosition(), current.getPosition())) {
                return true;
            }
        }
        return false;
    }

    private final void clearCache() {
        this.relevantChanges.clear();
        this.coordinates = null;
    }

    private final boolean dispatchIfNeeded(Function0<Unit> block) {
        if (this.relevantChanges.isEmpty() || !this.pointerInputFilter.getIsAttached()) {
            return false;
        }
        block.invoke();
        return true;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void dispatchCancel() {
        int i;
        MutableVector mutableVector;
        MutableVector<Node> children = getChildren();
        int size$iv = children.getSize();
        int i2 = 1;
        if (size$iv > 0) {
            int i$iv = 0;
            Object[] content$iv = children.getContent();
            do {
                Node it = (Node) content$iv[i$iv];
                it.dispatchCancel();
                i$iv++;
            } while (i$iv < size$iv);
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv = this.pointerInputFilter;
        int iM6105constructorimpl = NodeKind.m6105constructorimpl(16);
        MutableVector mutableVector2 = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv;
        while (nodePop != null) {
            if (nodePop instanceof PointerInputModifierNode) {
                PointerInputModifierNode it2 = (PointerInputModifierNode) nodePop;
                it2.onCancelPointerInput();
                i = i2;
            } else {
                Modifier.Node this_$iv$iv = nodePop;
                if (((this_$iv$iv.getKindSet() & iM6105constructorimpl) != 0 ? i2 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                    i = i2;
                } else {
                    int count$iv = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if (((next$iv.getKindSet() & iM6105constructorimpl) != 0 ? i2 : 0) != 0) {
                            count$iv++;
                            if (count$iv == i2) {
                                nodePop = next$iv;
                            } else {
                                if (mutableVector2 != null) {
                                    mutableVector = mutableVector2;
                                } else {
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                mutableVector2 = mutableVector;
                                Modifier.Node theNode$iv = nodePop;
                                if (theNode$iv != null) {
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode$iv);
                                    }
                                    nodePop = null;
                                }
                                if (mutableVector2 != null) {
                                    mutableVector2.add(next$iv);
                                }
                            }
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        i2 = 1;
                    }
                    i = 1;
                    if (count$iv == 1) {
                        i2 = 1;
                    }
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector2);
            i2 = i;
        }
    }

    public final void markIsIn() {
        this.isIn = true;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        super.cleanUpHits(internalPointerEvent);
        PointerEvent event = this.pointerEvent;
        if (event == null) {
            return;
        }
        this.wasIn = this.isIn;
        List<PointerInputChange> changes = event.getChanges();
        int $i$f$fastForEach = 0;
        int index$iv = 0;
        int size = changes.size();
        while (true) {
            boolean removePointerId = false;
            if (index$iv >= size) {
                this.isIn = false;
                this.hasExited = PointerEventType.m5690equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5695getExit7fucELk());
                return;
            }
            Object item$iv = changes.get(index$iv);
            PointerInputChange change = (PointerInputChange) item$iv;
            boolean released = !change.getPressed();
            boolean nonHoverEventStream = !internalPointerEvent.m5670activeHoverEvent0FcD4WY(change.getId());
            boolean outsideArea = !this.isIn;
            if ((released && nonHoverEventStream) || (released && outsideArea)) {
                removePointerId = true;
            }
            if (removePointerId) {
                this.pointerIds.m5837remove0FcD4WY(change.getId());
            }
            index$iv++;
            changes = changes;
            $i$f$fastForEach = $i$f$fastForEach;
        }
    }

    public String toString() {
        return "Node(pointerInputFilter=" + this.pointerInputFilter + ", children=" + getChildren() + ", pointerIds=" + this.pointerIds + ')';
    }
}
