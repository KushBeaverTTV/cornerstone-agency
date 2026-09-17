package androidx.compose.ui.input.pointer;

import androidx.collection.LongObjectMap;
import androidx.collection.MutableLongObjectMap;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HitPathTracker.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u000eJ\u0018\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u0015J\u0006\u0010\u001d\u001a\u00020\u000eJ\u0006\u0010\u001e\u001a\u00020\u000eJ\u001e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"Landroidx/compose/ui/input/pointer/HitPathTracker;", "", "rootCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "hitPointerIdsAndNodes", "Landroidx/collection/MutableLongObjectMap;", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/input/pointer/Node;", "root", "Landroidx/compose/ui/input/pointer/NodeParent;", "getRoot$ui_release", "()Landroidx/compose/ui/input/pointer/NodeParent;", "addHitPath", "", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "pointerInputNodes", "", "Landroidx/compose/ui/Modifier$Node;", "prunePointerIdsAndChangesNotInNodesList", "", "addHitPath-QJqDSyo", "(JLjava/util/List;Z)V", "clearPreviouslyHitModifierNodeCache", "dispatchChanges", "internalPointerEvent", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "isInBounds", "processCancel", "removeDetachedPointerInputNodes", "removeInvalidPointerIdsAndChanges", "", "hitNodes", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HitPathTracker {
    public static final int $stable = 8;
    private final LayoutCoordinates rootCoordinates;
    private final NodeParent root = new NodeParent();
    private final MutableLongObjectMap<MutableObjectList<Node>> hitPointerIdsAndNodes = new MutableLongObjectMap<>(10);

    public HitPathTracker(LayoutCoordinates rootCoordinates) {
        this.rootCoordinates = rootCoordinates;
    }

    /* JADX INFO: renamed from: getRoot$ui_release, reason: from getter */
    public final NodeParent getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: addHitPath-QJqDSyo$default, reason: not valid java name */
    public static /* synthetic */ void m5668addHitPathQJqDSyo$default(HitPathTracker hitPathTracker, long j, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        hitPathTracker.m5669addHitPathQJqDSyo(j, list, z);
    }

    /* JADX INFO: renamed from: addHitPath-QJqDSyo, reason: not valid java name */
    public final void m5669addHitPathQJqDSyo(long pointerId, List<? extends Modifier.Node> pointerInputNodes, boolean prunePointerIdsAndChangesNotInNodesList) {
        LongObjectMap this_$iv;
        int $i$f$forEach;
        Object item$iv;
        Node parent = this.root;
        this.hitPointerIdsAndNodes.clear();
        boolean merging = true;
        int i = 0;
        for (int size = pointerInputNodes.size(); i < size; size = size) {
            Modifier.Node pointerInputNode = pointerInputNodes.get(i);
            if (merging) {
                MutableVector<Node> children = parent.getChildren();
                int size$iv = children.getSize();
                if (size$iv <= 0) {
                    item$iv = null;
                    break;
                }
                int i$iv = 0;
                Object[] content$iv = children.getContent();
                while (true) {
                    item$iv = content$iv[i$iv];
                    Node it = (Node) item$iv;
                    if (Intrinsics.areEqual(it.getPointerInputFilter(), pointerInputNode)) {
                        break;
                    }
                    i$iv++;
                    if (i$iv >= size$iv) {
                        item$iv = null;
                        break;
                    }
                }
                Node node = (Node) item$iv;
                if (node != null) {
                    node.markIsIn();
                    node.getPointerIds().m5834add0FcD4WY(pointerId);
                    MutableLongObjectMap<MutableObjectList<Node>> mutableLongObjectMap = this.hitPointerIdsAndNodes;
                    MutableObjectList<Node> mutableObjectList = mutableLongObjectMap.get(pointerId);
                    if (mutableObjectList == null) {
                        MutableObjectList<Node> mutableObjectList2 = new MutableObjectList<>(0, 1, null);
                        mutableLongObjectMap.set(pointerId, mutableObjectList2);
                        mutableObjectList = mutableObjectList2;
                    }
                    mutableObjectList.add(node);
                    parent = node;
                    merging = merging;
                } else {
                    merging = false;
                }
                i++;
            }
            Node node2 = new Node(pointerInputNode);
            node2.getPointerIds().m5834add0FcD4WY(pointerId);
            MutableLongObjectMap<MutableObjectList<Node>> mutableLongObjectMap2 = this.hitPointerIdsAndNodes;
            MutableObjectList<Node> mutableObjectList3 = mutableLongObjectMap2.get(pointerId);
            if (mutableObjectList3 == null) {
                MutableObjectList<Node> mutableObjectList4 = new MutableObjectList<>(0, 1, null);
                mutableLongObjectMap2.set(pointerId, mutableObjectList4);
                mutableObjectList3 = mutableObjectList4;
            }
            mutableObjectList3.add(node2);
            parent.getChildren().add(node2);
            parent = node2;
            i++;
        }
        if (!prunePointerIdsAndChangesNotInNodesList) {
            return;
        }
        LongObjectMap this_$iv2 = this.hitPointerIdsAndNodes;
        int $i$f$forEach2 = 0;
        long[] k$iv = this_$iv2.keys;
        Object[] v$iv = this_$iv2.values;
        long[] m$iv$iv = this_$iv2.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            NodeParent parent2 = parent;
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = slot$iv$iv & 255;
                    int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                    if ($i$f$isFull != 0) {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        long key = k$iv[index$iv$iv];
                        removeInvalidPointerIdsAndChanges(key, (MutableObjectList) v$iv[index$iv$iv]);
                    }
                    slot$iv$iv >>= 8;
                    j$iv$iv++;
                    i2 = 8;
                    $i$f$forEach2 = $i$f$forEach2;
                    this_$iv2 = this_$iv2;
                }
                this_$iv = this_$iv2;
                $i$f$forEach = $i$f$forEach2;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            } else {
                this_$iv = this_$iv2;
                $i$f$forEach = $i$f$forEach2;
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            parent = parent2;
            $i$f$forEach2 = $i$f$forEach;
            this_$iv2 = this_$iv;
        }
    }

    private final void removeInvalidPointerIdsAndChanges(long pointerId, MutableObjectList<Node> hitNodes) {
        this.root.removeInvalidPointerIdsAndChanges(pointerId, hitNodes);
    }

    public static /* synthetic */ boolean dispatchChanges$default(HitPathTracker hitPathTracker, InternalPointerEvent internalPointerEvent, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return hitPathTracker.dispatchChanges(internalPointerEvent, z);
    }

    public final boolean dispatchChanges(InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        boolean changed = this.root.buildCache(internalPointerEvent.getChanges(), this.rootCoordinates, internalPointerEvent, isInBounds);
        if (!changed) {
            return false;
        }
        boolean dispatchHit = this.root.dispatchMainEventPass(internalPointerEvent.getChanges(), this.rootCoordinates, internalPointerEvent, isInBounds);
        return this.root.dispatchFinalEventPass(internalPointerEvent) || dispatchHit;
    }

    public final void clearPreviouslyHitModifierNodeCache() {
        this.root.clear();
    }

    public final void processCancel() {
        this.root.dispatchCancel();
        clearPreviouslyHitModifierNodeCache();
    }

    public final void removeDetachedPointerInputNodes() {
        this.root.removeDetachedPointerInputModifierNodes();
    }
}
