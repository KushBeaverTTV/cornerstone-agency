package androidx.compose.animation;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SharedTransitionScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002:\u0001uB\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\u0015\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202H\u0000¢\u0006\u0002\b3J\u0015\u00104\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001eH\u0000¢\u0006\u0002\b6J\u0015\u00107\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001eH\u0000¢\u0006\u0002\b8J\u0015\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b<J\u0015\u0010=\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020$H\u0017¢\u0006\u0002\u0010BJM\u0010C\u001a\u00020;2\u0006\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020@2\u0006\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\nH\u0003¢\u0006\u0002\u0010OJ\u0010\u0010P\u001a\u00020%2\u0006\u0010A\u001a\u00020$H\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J3\u0010Q\u001a\u00020R*\u00020\u00122\u0006\u0010S\u001a\u00020\u00122\b\b\u0002\u0010T\u001a\u00020R2\b\b\u0002\u0010U\u001a\u00020\nH\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bV\u0010WJ>\u0010X\u001a\u00020Y*\u00020Y2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\n0\u001a2\u0006\u0010L\u001a\u00020M2\u001a\u0010K\u001a\u0016\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020]\u0012\u0006\u0012\u0004\u0018\u00010^0[H\u0016J\\\u0010_\u001a\u00020Y*\u00020Y2\u0006\u0010J\u001a\u00020@2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020i2\u0006\u0010G\u001a\u00020H2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0016Jp\u0010j\u001a\u00020Y\"\u0004\b\u0000\u0010k*\u00020Y2\u0006\u0010J\u001a\u00020@2\u000e\u0010l\u001a\n\u0012\u0004\u0012\u0002Hk\u0018\u00010m2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u0002Hk\u0012\u0004\u0012\u00020\n0'2\u0006\u0010f\u001a\u00020g2\b\b\u0002\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0002JS\u0010o\u001a\u00020Y*\u00020Y2\u0006\u0010J\u001a\u00020@2\u0006\u0010n\u001a\u00020\n2\b\b\u0002\u0010f\u001a\u00020g2\b\b\u0002\u0010G\u001a\u00020H2\b\b\u0002\u0010N\u001a\u00020\n2\b\b\u0002\u0010L\u001a\u00020M2\b\b\u0002\u0010K\u001a\u00020-H\u0000¢\u0006\u0002\bpJD\u0010D\u001a\u00020Y*\u00020Y2\u0006\u0010q\u001a\u00020@2\u0006\u0010`\u001a\u00020a2\u0006\u0010f\u001a\u00020g2\u0006\u0010G\u001a\u00020H2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0016JD\u0010r\u001a\u00020Y*\u00020Y2\u0006\u0010J\u001a\u00020@2\u0006\u0010n\u001a\u00020\n2\u0006\u0010f\u001a\u00020g2\u0006\u0010G\u001a\u00020H2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0016J\f\u0010s\u001a\u00020Y*\u00020YH\u0016J\r\u0010t\u001a\u00020\u0012*\u00020\u0012H\u0096\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0014\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020\u0012X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0018R\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001b0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010(\u001a\u00020\u0012*\u00020)X\u0096\u0005¢\u0006\u0006\u001a\u0004\b*\u0010+\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006v"}, d2 = {"Landroidx/compose/animation/SharedTransitionScopeImpl;", "Landroidx/compose/animation/SharedTransitionScope;", "Landroidx/compose/ui/layout/LookaheadScope;", "lookaheadScope", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/compose/ui/layout/LookaheadScope;Lkotlinx/coroutines/CoroutineScope;)V", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "<set-?>", "", "isTransitionActive", "()Z", "setTransitionActive", "(Z)V", "isTransitionActive$delegate", "Landroidx/compose/runtime/MutableState;", "lookaheadRoot", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getLookaheadRoot$animation_release", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "nullableLookaheadRoot", "getNullableLookaheadRoot$animation_release", "setNullableLookaheadRoot$animation_release", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "observeAnimatingBlock", "Lkotlin/Function0;", "", "renderers", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Landroidx/compose/animation/LayerRenderer;", "root", "getRoot$animation_release", "setRoot$animation_release", "sharedElements", "Landroidx/collection/MutableScatterMap;", "", "Landroidx/compose/animation/SharedElement;", "updateTransitionActiveness", "Lkotlin/Function1;", "lookaheadScopeCoordinates", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getLookaheadScopeCoordinates", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)Landroidx/compose/ui/layout/LayoutCoordinates;", "OverlayClip", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "drawInOverlay", "scope", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawInOverlay$animation_release", "onLayerRendererCreated", "renderer", "onLayerRendererCreated$animation_release", "onLayerRendererRemoved", "onLayerRendererRemoved$animation_release", "onStateAdded", "sharedElementState", "Landroidx/compose/animation/SharedElementInternalState;", "onStateAdded$animation_release", "onStateRemoved", "onStateRemoved$animation_release", "rememberSharedContentState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "key", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "rememberSharedElementState", "sharedElement", "boundsAnimation", "Landroidx/compose/animation/BoundsAnimation;", "placeHolderSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;", "renderOnlyWhenVisible", "sharedContentState", "clipInOverlayDuringTransition", "zIndexInOverlay", "", "renderInOverlayDuringTransition", "(Landroidx/compose/animation/SharedElement;Landroidx/compose/animation/BoundsAnimation;Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;ZLandroidx/compose/animation/SharedTransitionScope$SharedContentState;Landroidx/compose/animation/SharedTransitionScope$OverlayClip;FZLandroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedElementInternalState;", "sharedElementsFor", "localLookaheadPositionOf", "Landroidx/compose/ui/geometry/Offset;", "sourceCoordinates", "relativeToSource", "includeMotionFrameOfReference", "localLookaheadPositionOf-au-aQtc", "(Landroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "renderInSharedTransitionScopeOverlay", "Landroidx/compose/ui/Modifier;", "renderInOverlay", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/LayoutDirection;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/graphics/Path;", "sharedBounds", "animatedVisibilityScope", "Landroidx/compose/animation/AnimatedVisibilityScope;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "boundsTransform", "Landroidx/compose/animation/BoundsTransform;", "resizeMode", "Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "sharedBoundsImpl", "T", "parentTransition", "Landroidx/compose/animation/core/Transition;", "visible", "sharedBoundsWithCallerManagedVisibility", "sharedBoundsWithCallerManagedVisibility$animation_release", "state", "sharedElementWithCallerManagedVisibility", "skipToLookaheadSize", "toLookaheadCoordinates", "ShapeBasedClip", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SharedTransitionScopeImpl implements SharedTransitionScope, LookaheadScope {
    public static final int $stable = 0;
    private final /* synthetic */ LookaheadScope $$delegate_0;
    private final CoroutineScope coroutineScope;
    private LayoutCoordinates nullableLookaheadRoot;
    public LayoutCoordinates root;

    /* JADX INFO: renamed from: isTransitionActive$delegate, reason: from kotlin metadata */
    private final MutableState isTransitionActive = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    private final Function0<Unit> observeAnimatingBlock = new Function0<Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$observeAnimatingBlock$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ScatterMap this_$iv = this.this$0.sharedElements;
            Object[] k$iv$iv = this_$iv.keys;
            Object[] v$iv$iv = this_$iv.values;
            long[] m$iv$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            if (0 > lastIndex$iv$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                ScatterMap this_$iv2 = this_$iv;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    for (int j$iv$iv$iv = 0; j$iv$iv$iv < bitCount$iv$iv$iv; j$iv$iv$iv++) {
                        long value$iv$iv$iv$iv = 255 & slot$iv$iv$iv;
                        if (value$iv$iv$iv$iv < 128) {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                            Object obj = k$iv$iv[index$iv$iv$iv];
                            Object value$iv = v$iv$iv[index$iv$iv$iv];
                            SharedElement element = (SharedElement) value$iv;
                            if (element.isAnimating()) {
                                return;
                            }
                        }
                        slot$iv$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv$iv != 8) {
                        return;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    return;
                }
                i$iv$iv$iv++;
                this_$iv = this_$iv2;
            }
        }
    };
    private final Function1<SharedTransitionScope, Unit> updateTransitionActiveness = new Function1<SharedTransitionScope, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.updateTransitionActiveness.1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SharedTransitionScope sharedTransitionScope) {
            invoke2(sharedTransitionScope);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(SharedTransitionScope it) {
            SharedTransitionScopeImpl.this.updateTransitionActiveness();
        }
    };
    private final SnapshotStateList<LayerRenderer> renderers = SnapshotStateKt.mutableStateListOf();
    private final MutableScatterMap<Object, SharedElement> sharedElements = new MutableScatterMap<>(0, 1, null);

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates getLookaheadScopeCoordinates(Placeable.PlacementScope placementScope) {
        return this.$$delegate_0.getLookaheadScopeCoordinates(placementScope);
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    /* JADX INFO: renamed from: localLookaheadPositionOf-au-aQtc, reason: not valid java name */
    public long mo462localLookaheadPositionOfauaQtc(LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2, long j, boolean z) {
        return this.$$delegate_0.mo462localLookaheadPositionOfauaQtc(layoutCoordinates, layoutCoordinates2, j, z);
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates toLookaheadCoordinates(LayoutCoordinates layoutCoordinates) {
        return this.$$delegate_0.toLookaheadCoordinates(layoutCoordinates);
    }

    public SharedTransitionScopeImpl(LookaheadScope lookaheadScope, CoroutineScope coroutineScope) {
        this.coroutineScope = coroutineScope;
        this.$$delegate_0 = lookaheadScope;
    }

    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    private void setTransitionActive(boolean z) {
        MutableState $this$setValue$iv = this.isTransitionActive;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public boolean isTransitionActive() {
        State $this$getValue$iv = this.isTransitionActive;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier skipToLookaheadSize(Modifier modifier) {
        return modifier.then(new SkipToLookaheadElement(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0));
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier renderInSharedTransitionScopeOverlay(Modifier $this$renderInSharedTransitionScopeOverlay, Function0<Boolean> function0, float zIndexInOverlay, Function2<? super LayoutDirection, ? super Density, ? extends Path> function2) {
        return $this$renderInSharedTransitionScopeOverlay.then(new RenderInTransitionOverlayNodeElement(this, function0, zIndexInOverlay, function2));
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier sharedElement(Modifier $this$sharedElement, SharedTransitionScope.SharedContentState state, AnimatedVisibilityScope animatedVisibilityScope, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderInOverlayDuringTransition, float zIndexInOverlay, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return sharedBoundsImpl($this$sharedElement, state, animatedVisibilityScope.getTransition(), new Function1<EnterExitState, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedElement.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(EnterExitState it) {
                return Boolean.valueOf(it == EnterExitState.Visible);
            }
        }, boundsTransform, placeHolderSize, true, renderInOverlayDuringTransition, zIndexInOverlay, clipInOverlayDuringTransition);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier sharedBounds(Modifier $this$sharedBounds, final SharedTransitionScope.SharedContentState sharedContentState, final AnimatedVisibilityScope animatedVisibilityScope, final EnterTransition enter, final ExitTransition exit, BoundsTransform boundsTransform, final SharedTransitionScope.ResizeMode resizeMode, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderInOverlayDuringTransition, float zIndexInOverlay, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return ComposedModifierKt.composed$default(sharedBoundsImpl($this$sharedBounds, sharedContentState, animatedVisibilityScope.getTransition(), new Function1<EnterExitState, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedBounds.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(EnterExitState it) {
                return Boolean.valueOf(it == EnterExitState.Visible);
            }
        }, boundsTransform, placeHolderSize, false, renderInOverlayDuringTransition, zIndexInOverlay, clipInOverlayDuringTransition), null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedBounds.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Object value$iv;
                Modifier.Companion companionCreateContentScaleModifier;
                Object value$iv2;
                $composer.startReplaceGroup(-419341573);
                ComposerKt.sourceInformation($composer, "C779@41927L35,771@41404L656:SharedTransitionScope.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-419341573, $changed, -1, "androidx.compose.animation.SharedTransitionScopeImpl.sharedBounds.<anonymous> (SharedTransitionScope.kt:770)");
                }
                Transition<EnterExitState> transition = animatedVisibilityScope.getTransition();
                EnterTransition enterTransition = enter;
                ExitTransition exitTransition = exit;
                ComposerKt.sourceInformationMarkerStart($composer, -1411456741, "CC(remember):SharedTransitionScope.kt#9igjgp");
                boolean invalid$iv = $composer.changedInstance(sharedContentState);
                final SharedTransitionScope.SharedContentState sharedContentState2 = sharedContentState;
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBounds$2$1$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return Boolean.valueOf(sharedContentState2.isMatchFound());
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifierCreateModifier = EnterExitTransitionKt.createModifier(transition, enterTransition, exitTransition, (Function0) value$iv, "enter/exit for " + sharedContentState.getKey(), $composer, 0, 0);
                if (resizeMode instanceof ScaleToBoundsImpl) {
                    $composer.startReplaceGroup(-805247216);
                    ComposerKt.sourceInformation($composer, "784@42227L530");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ScaleToBoundsImpl scaleToBoundsImpl = (ScaleToBoundsImpl) resizeMode;
                    ComposerKt.sourceInformationMarkerStart($composer, -1411446646, "CC(remember):SharedTransitionScope.kt#9igjgp");
                    boolean invalid$iv2 = $composer.changedInstance(sharedContentState);
                    final SharedTransitionScope.SharedContentState sharedContentState3 = sharedContentState;
                    Object it$iv2 = $composer.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBounds$2$2$1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                return Boolean.valueOf(sharedContentState3.isMatchFound());
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    companionCreateContentScaleModifier = SharedTransitionScopeKt.createContentScaleModifier(companion, scaleToBoundsImpl, (Function0) value$iv2);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(-804630006);
                    $composer.endReplaceGroup();
                    companionCreateContentScaleModifier = Modifier.INSTANCE;
                }
                Modifier modifierThen = modifierCreateModifier.then(companionCreateContentScaleModifier);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return modifierThen;
            }
        }, 1, null);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier sharedElementWithCallerManagedVisibility(Modifier $this$sharedElementWithCallerManagedVisibility, SharedTransitionScope.SharedContentState sharedContentState, final boolean visible, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderInOverlayDuringTransition, float zIndexInOverlay, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return sharedBoundsImpl($this$sharedElementWithCallerManagedVisibility, sharedContentState, null, new Function1<Unit, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedElementWithCallerManagedVisibility.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Unit it) {
                return Boolean.valueOf(visible);
            }
        }, boundsTransform, placeHolderSize, true, renderInOverlayDuringTransition, zIndexInOverlay, clipInOverlayDuringTransition);
    }

    public final Modifier sharedBoundsWithCallerManagedVisibility$animation_release(Modifier $this$sharedBoundsWithCallerManagedVisibility, SharedTransitionScope.SharedContentState sharedContentState, final boolean visible, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderInOverlayDuringTransition, float zIndexInOverlay, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return sharedBoundsImpl($this$sharedBoundsWithCallerManagedVisibility, sharedContentState, null, new Function1<Unit, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBoundsWithCallerManagedVisibility$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Unit it) {
                return Boolean.valueOf(visible);
            }
        }, boundsTransform, placeHolderSize, false, renderInOverlayDuringTransition, zIndexInOverlay, clipInOverlayDuringTransition);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public SharedTransitionScope.OverlayClip OverlayClip(Shape clipShape) {
        return new ShapeBasedClip(clipShape);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public SharedTransitionScope.SharedContentState rememberSharedContentState(Object key, Composer $composer, int $changed) {
        Object value$iv;
        $composer.startReplaceGroup(799702514);
        ComposerKt.sourceInformation($composer, "C(rememberSharedContentState)912@49465L53:SharedTransitionScope.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(799702514, $changed, -1, "androidx.compose.animation.SharedTransitionScopeImpl.rememberSharedContentState (SharedTransitionScope.kt:912)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 935469886, "CC(remember):SharedTransitionScope.kt#9igjgp");
        boolean invalid$iv = $composer.changed(key);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new SharedTransitionScope.SharedContentState(key);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        SharedTransitionScope.SharedContentState sharedContentState = (SharedTransitionScope.SharedContentState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return sharedContentState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x0076 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:20:0x0078 A[LOOP:0: B:4:0x0021->B:20:0x0078, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:26:0x008e  */
    /* JADX WARN: Code duplicated, block: B:28:0x0093  */
    /* JADX WARN: Code duplicated, block: B:30:0x00a6 A[LOOP:2: B:30:0x00a6->B:44:0x00f9, LOOP_START, PHI: r1 r2 r10
  0x00a6: PHI (r1v20 'isActive' boolean) = (r1v3 'isActive' boolean), (r1v26 'isActive' boolean) binds: [B:29:0x00a4, B:44:0x00f9] A[DONT_GENERATE, DONT_INLINE]
  0x00a6: PHI (r2v18 'this_$iv' androidx.collection.ScatterMap) = (r2v17 'this_$iv' androidx.collection.ScatterMap), (r2v19 'this_$iv' androidx.collection.ScatterMap) binds: [B:29:0x00a4, B:44:0x00f9] A[DONT_GENERATE, DONT_INLINE]
  0x00a6: PHI (r10v6 'i$iv$iv' int) = (r10v5 'i$iv$iv' int), (r10v7 'i$iv$iv' int) binds: [B:29:0x00a4, B:44:0x00f9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:32:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:34:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:36:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:37:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:39:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:43:0x00f7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:44:0x00f9 A[LOOP:2: B:30:0x00a6->B:44:0x00f9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:45:0x0101  */
    /* JADX WARN: Code duplicated, block: B:48:0x0106  */
    /* JADX WARN: Code duplicated, block: B:49:0x0108  */
    /* JADX WARN: Code duplicated, block: B:52:0x011c A[LOOP:4: B:52:0x011c->B:68:0x0177, LOOP_START, PHI: r1 r2 r9
  0x011c: PHI (r1v7 'this_$iv' androidx.collection.ScatterMap) = (r1v5 'this_$iv' androidx.collection.ScatterMap), (r1v13 'this_$iv' androidx.collection.ScatterMap) binds: [B:51:0x011a, B:68:0x0177] A[DONT_GENERATE, DONT_INLINE]
  0x011c: PHI (r2v4 '$i$f$forEach' int) = (r2v2 '$i$f$forEach' int), (r2v6 '$i$f$forEach' int) binds: [B:51:0x011a, B:68:0x0177] A[DONT_GENERATE, DONT_INLINE]
  0x011c: PHI (r9v2 'i$iv$iv' int) = (r9v1 'i$iv$iv' int), (r9v3 'i$iv$iv' int) binds: [B:51:0x011a, B:68:0x0177] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:54:0x012e  */
    /* JADX WARN: Code duplicated, block: B:56:0x013a  */
    /* JADX WARN: Code duplicated, block: B:58:0x0145  */
    /* JADX WARN: Code duplicated, block: B:59:0x0147  */
    /* JADX WARN: Code duplicated, block: B:61:0x014b  */
    /* JADX WARN: Code duplicated, block: B:66:0x016f  */
    /* JADX WARN: Code duplicated, block: B:68:0x0177 A[LOOP:4: B:52:0x011c->B:68:0x0177, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:69:0x017e  */
    /* JADX WARN: Code duplicated, block: B:75:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x0109 A[EDGE_INSN: B:79:0x0109->B:50:0x0109 BREAK  A[LOOP:2: B:30:0x00a6->B:44:0x00f9], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x0109 A[EDGE_INSN: B:80:0x0109->B:50:0x0109 BREAK  A[LOOP:2: B:30:0x00a6->B:44:0x00f9], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x00ec A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0183 A[EDGE_INSN: B:84:0x0183->B:71:0x0183 BREAK  A[LOOP:4: B:52:0x011c->B:68:0x0177], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x0183 A[EDGE_INSN: B:85:0x0183->B:71:0x0183 BREAK  A[LOOP:4: B:52:0x011c->B:68:0x0177], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x0160 A[SYNTHETIC] */
    public final void updateTransitionActiveness() {
        boolean isActive;
        ScatterMap this_$iv;
        int $i$f$forEach;
        Object[] k$iv;
        Object[] v$iv;
        long[] m$iv$iv;
        int lastIndex$iv$iv;
        int i$iv$iv;
        long slot$iv$iv;
        ScatterMap this_$iv2;
        int $i$f$forEach2;
        int bitCount$iv$iv;
        int j$iv$iv;
        long value$iv$iv$iv;
        int $i$f$isFull;
        ScatterMap this_$iv3;
        Object[] k$iv2;
        Object[] v$iv2;
        long[] m$iv$iv2;
        int lastIndex$iv$iv2;
        int i$iv$iv2;
        long slot$iv$iv2;
        long slot$iv$iv3;
        boolean isActive2;
        ScatterMap this_$iv4;
        int bitCount$iv$iv2;
        int j$iv$iv2;
        long value$iv$iv$iv2;
        int $i$f$isFull2;
        ScatterMap this_$iv5 = this.sharedElements;
        int $i$f$any = 0;
        Object[] k$iv$iv = this_$iv5.keys;
        Object[] v$iv$iv = this_$iv5.values;
        long[] m$iv$iv$iv = this_$iv5.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 <= lastIndex$iv$iv$iv) {
            loop0: while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                ScatterMap this_$iv6 = this_$iv5;
                int $i$f$any2 = $i$f$any;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                        i$iv$iv$iv++;
                        this_$iv5 = this_$iv6;
                        $i$f$any = $i$f$any2;
                    }
                } else {
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    for (int j$iv$iv$iv = 0; j$iv$iv$iv < bitCount$iv$iv$iv; j$iv$iv$iv++) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        int $i$f$isFull3 = value$iv$iv$iv$iv < 128 ? 1 : 0;
                        if ($i$f$isFull3 != 0) {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                            Object obj = k$iv$iv[index$iv$iv$iv];
                            Object value$iv = v$iv$iv[index$iv$iv$iv];
                            SharedElement element = (SharedElement) value$iv;
                            if (element.isAnimating()) {
                                isActive = true;
                                break loop0;
                            }
                        }
                        slot$iv$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv$iv == 8) {
                        if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                            i$iv$iv$iv++;
                            this_$iv5 = this_$iv6;
                            $i$f$any = $i$f$any2;
                        }
                    }
                }
            }
            if (isActive != isTransitionActive()) {
                setTransitionActive(isActive);
                if (!isActive) {
                    this_$iv3 = this.sharedElements;
                    k$iv2 = this_$iv3.keys;
                    v$iv2 = this_$iv3.values;
                    m$iv$iv2 = this_$iv3.metadata;
                    lastIndex$iv$iv2 = m$iv$iv2.length - 2;
                    i$iv$iv2 = 0;
                    if (0 <= lastIndex$iv$iv2) {
                        while (true) {
                            slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                            slot$iv$iv3 = slot$iv$iv2;
                            isActive2 = isActive;
                            this_$iv4 = this_$iv3;
                            if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) != -9187201950435737472L) {
                                if (i$iv$iv2 != lastIndex$iv$iv2) {
                                    break;
                                    break;
                                } else {
                                    i$iv$iv2++;
                                    isActive = isActive2;
                                    this_$iv3 = this_$iv4;
                                }
                            } else {
                                bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                                for (j$iv$iv2 = 0; j$iv$iv2 < bitCount$iv$iv2; j$iv$iv2++) {
                                    value$iv$iv$iv2 = slot$iv$iv3 & 255;
                                    if (value$iv$iv$iv2 < 128) {
                                        $i$f$isFull2 = 1;
                                    } else {
                                        $i$f$isFull2 = 0;
                                    }
                                    if ($i$f$isFull2 != 0) {
                                        int index$iv$iv = (i$iv$iv2 << 3) + j$iv$iv2;
                                        Object obj2 = k$iv2[index$iv$iv];
                                        SharedElement element2 = (SharedElement) v$iv2[index$iv$iv];
                                        element2.onSharedTransitionFinished();
                                    }
                                    slot$iv$iv3 >>= 8;
                                }
                                if (bitCount$iv$iv2 == 8) {
                                    break;
                                }
                                if (i$iv$iv2 != lastIndex$iv$iv2) {
                                    break;
                                }
                                i$iv$iv2++;
                                isActive = isActive2;
                                this_$iv3 = this_$iv4;
                            }
                        }
                    }
                }
            }
            this_$iv = this.sharedElements;
            $i$f$forEach = 0;
            k$iv = this_$iv.keys;
            v$iv = this_$iv.values;
            m$iv$iv = this_$iv.metadata;
            lastIndex$iv$iv = m$iv$iv.length - 2;
            i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    slot$iv$iv = m$iv$iv[i$iv$iv];
                    this_$iv2 = this_$iv;
                    $i$f$forEach2 = $i$f$forEach;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                        bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        for (j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                            value$iv$iv$iv = slot$iv$iv & 255;
                            if (value$iv$iv$iv < 128) {
                                $i$f$isFull = 1;
                            } else {
                                $i$f$isFull = 0;
                            }
                            if ($i$f$isFull != 0) {
                                int index$iv$iv2 = (i$iv$iv << 3) + j$iv$iv;
                                Object obj3 = k$iv[index$iv$iv2];
                                SharedElement element3 = (SharedElement) v$iv[index$iv$iv2];
                                element3.updateMatch();
                            }
                            slot$iv$iv >>= 8;
                        }
                        if (bitCount$iv$iv == 8) {
                            break;
                        }
                    }
                    if (i$iv$iv != lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    this_$iv = this_$iv2;
                    $i$f$forEach = $i$f$forEach2;
                }
            }
            SharedTransitionScopeKt.getSharedTransitionObserver().observeReads(this, this.updateTransitionActiveness, this.observeAnimatingBlock);
        }
        isActive = false;
        if (isActive != isTransitionActive()) {
            setTransitionActive(isActive);
            if (!isActive) {
                this_$iv3 = this.sharedElements;
                k$iv2 = this_$iv3.keys;
                v$iv2 = this_$iv3.values;
                m$iv$iv2 = this_$iv3.metadata;
                lastIndex$iv$iv2 = m$iv$iv2.length - 2;
                i$iv$iv2 = 0;
                if (0 <= lastIndex$iv$iv2) {
                    while (true) {
                        slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                        slot$iv$iv3 = slot$iv$iv2;
                        isActive2 = isActive;
                        this_$iv4 = this_$iv3;
                        if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) != -9187201950435737472L) {
                            if (i$iv$iv2 != lastIndex$iv$iv2) {
                                break;
                                break;
                            } else {
                                i$iv$iv2++;
                                isActive = isActive2;
                                this_$iv3 = this_$iv4;
                            }
                        } else {
                            bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                            while (j$iv$iv2 < bitCount$iv$iv2) {
                                value$iv$iv$iv2 = slot$iv$iv3 & 255;
                                if (value$iv$iv$iv2 < 128) {
                                    $i$f$isFull2 = 1;
                                } else {
                                    $i$f$isFull2 = 0;
                                }
                                if ($i$f$isFull2 != 0) {
                                    int index$iv$iv3 = (i$iv$iv2 << 3) + j$iv$iv2;
                                    Object obj4 = k$iv2[index$iv$iv3];
                                    SharedElement element4 = (SharedElement) v$iv2[index$iv$iv3];
                                    element4.onSharedTransitionFinished();
                                }
                                slot$iv$iv3 >>= 8;
                            }
                            if (bitCount$iv$iv2 == 8) {
                                break;
                                break;
                            } else if (i$iv$iv2 != lastIndex$iv$iv2) {
                                break;
                                break;
                            } else {
                                i$iv$iv2++;
                                isActive = isActive2;
                                this_$iv3 = this_$iv4;
                            }
                        }
                    }
                }
            }
        }
        this_$iv = this.sharedElements;
        $i$f$forEach = 0;
        k$iv = this_$iv.keys;
        v$iv = this_$iv.values;
        m$iv$iv = this_$iv.metadata;
        lastIndex$iv$iv = m$iv$iv.length - 2;
        i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                slot$iv$iv = m$iv$iv[i$iv$iv];
                this_$iv2 = this_$iv;
                $i$f$forEach2 = $i$f$forEach;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    while (j$iv$iv < bitCount$iv$iv) {
                        value$iv$iv$iv = slot$iv$iv & 255;
                        if (value$iv$iv$iv < 128) {
                            $i$f$isFull = 1;
                        } else {
                            $i$f$isFull = 0;
                        }
                        if ($i$f$isFull != 0) {
                            int index$iv$iv4 = (i$iv$iv << 3) + j$iv$iv;
                            Object obj5 = k$iv[index$iv$iv4];
                            SharedElement element5 = (SharedElement) v$iv[index$iv$iv4];
                            element5.updateMatch();
                        }
                        slot$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv == 8) {
                        break;
                        break;
                    }
                }
                if (i$iv$iv != lastIndex$iv$iv) {
                    break;
                    break;
                } else {
                    i$iv$iv++;
                    this_$iv = this_$iv2;
                    $i$f$forEach = $i$f$forEach2;
                }
            }
        }
        SharedTransitionScopeKt.getSharedTransitionObserver().observeReads(this, this.updateTransitionActiveness, this.observeAnimatingBlock);
    }

    static /* synthetic */ Modifier sharedBoundsImpl$default(SharedTransitionScopeImpl sharedTransitionScopeImpl, Modifier modifier, SharedTransitionScope.SharedContentState sharedContentState, Transition transition, Function1 function1, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean z, boolean z2, float f, SharedTransitionScope.OverlayClip overlayClip, int i, Object obj) {
        SharedTransitionScope.PlaceHolderSize contentSize;
        if ((i & 16) == 0) {
            contentSize = placeHolderSize;
        } else {
            contentSize = SharedTransitionScope.PlaceHolderSize.INSTANCE.getContentSize();
        }
        return sharedTransitionScopeImpl.sharedBoundsImpl(modifier, sharedContentState, transition, function1, boundsTransform, contentSize, z, z2, f, overlayClip);
    }

    private final <T> Modifier sharedBoundsImpl(Modifier $this$sharedBoundsImpl, final SharedTransitionScope.SharedContentState sharedContentState, final Transition<T> transition, final Function1<? super T, Boolean> function1, final BoundsTransform boundsTransform, final SharedTransitionScope.PlaceHolderSize placeHolderSize, final boolean renderOnlyWhenVisible, final boolean renderInOverlayDuringTransition, final float zIndexInOverlay, final SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return ComposedModifierKt.composed$default($this$sharedBoundsImpl, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedBoundsImpl.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            /* JADX WARN: Type inference incomplete: some casts might be missing */
            public final Modifier invoke(Modifier modifier, Composer composer, int i) {
                Object objSharedElementsFor;
                Object mutableTransitionState;
                Transition transitionRememberTransition;
                boolean z;
                Object boundsAnimation;
                Object currentState;
                composer.startReplaceGroup(-1843478929);
                ComposerKt.sourceInformation(composer, "C:SharedTransitionScope.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1843478929, i, -1, "androidx.compose.animation.SharedTransitionScopeImpl.sharedBoundsImpl.<anonymous> (SharedTransitionScope.kt:968)");
                }
                Object key = sharedContentState.getKey();
                composer.startMovableGroup(-359675295, key);
                ComposerKt.sourceInformation(composer, "970@51556L35,1006@53390L522");
                ComposerKt.sourceInformationMarkerStart(composer, -359676293, "CC(remember):SharedTransitionScope.kt#9igjgp");
                SharedTransitionScopeImpl sharedTransitionScopeImpl = this;
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objSharedElementsFor = sharedTransitionScopeImpl.sharedElementsFor(key);
                    composer.updateRememberedValue(objSharedElementsFor);
                } else {
                    objSharedElementsFor = objRememberedValue;
                }
                SharedElement sharedElement = (SharedElement) objSharedElementsFor;
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.startMovableGroup(-359672306, transition);
                ComposerKt.sourceInformation(composer, "*997@53029L278");
                if (transition != 0) {
                    composer.startReplaceGroup(1735101820);
                    ComposerKt.sourceInformation(composer, "974@51759L53");
                    Transition<T> transition2 = transition;
                    String string = key.toString();
                    Function1<T, Boolean> function2 = function1;
                    ComposerKt.sourceInformationMarkerStart(composer, 1215497572, "CC(createChildTransition)1813@74138L36,1814@74198L74,1815@74295L39,1816@74346L63:Transition.kt#pdpnli");
                    ComposerKt.sourceInformationMarkerStart(composer, 272027253, "CC(remember):Transition.kt#9igjgp");
                    boolean z2 = (((0 & 14) ^ 6) > 4 && composer.changed(transition2)) || (0 & 6) == 4;
                    Object objRememberedValue2 = composer.rememberedValue();
                    if (z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        currentState = transition2.getCurrentState();
                        composer.updateRememberedValue(currentState);
                    } else {
                        currentState = objRememberedValue2;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    Object currentState2 = transition2.isSeeking() ? transition2.getCurrentState() : currentState;
                    int i2 = (0 >> 3) & 112;
                    composer.startReplaceGroup(1329676753);
                    ComposerKt.sourceInformation(composer, "C:SharedTransitionScope.kt#xbi5r1");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1329676753, i2, -1, "androidx.compose.animation.SharedTransitionScopeImpl.sharedBoundsImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SharedTransitionScope.kt:974)");
                    }
                    boolean zBooleanValue = function2.invoke((T) currentState2).booleanValue();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    Boolean boolValueOf = Boolean.valueOf(zBooleanValue);
                    Object targetState = transition2.getTargetState();
                    int i3 = (0 >> 3) & 112;
                    composer.startReplaceGroup(1329676753);
                    ComposerKt.sourceInformation(composer, "C:SharedTransitionScope.kt#xbi5r1");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1329676753, i3, -1, "androidx.compose.animation.SharedTransitionScopeImpl.sharedBoundsImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SharedTransitionScope.kt:974)");
                    }
                    boolean zBooleanValue2 = function2.invoke((T) targetState).booleanValue();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    transitionRememberTransition = androidx.compose.animation.core.TransitionKt.createChildTransitionInternal(transition2, boolValueOf, Boolean.valueOf(zBooleanValue2), string, composer, (0 & 14) | ((0 << 6) & 7168));
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(1735245009);
                    ComposerKt.sourceInformation(composer, "*979@52034L707,992@52800L35");
                    Function1<T, Boolean> function3 = function1;
                    Intrinsics.checkNotNull(function3, "null cannot be cast to non-null type kotlin.Function1<kotlin.Unit, kotlin.Boolean>");
                    boolean zBooleanValue3 = ((Boolean) ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function3, 1)).invoke(Unit.INSTANCE)).booleanValue();
                    ComposerKt.sourceInformationMarkerStart(composer, -359660325, "CC(remember):SharedTransitionScope.kt#9igjgp");
                    Object objRememberedValue3 = composer.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        if (sharedElement.getCurrentBounds() != null) {
                            z = !zBooleanValue3;
                        } else {
                            z = zBooleanValue3;
                        }
                        mutableTransitionState = new MutableTransitionState(Boolean.valueOf(z));
                        composer.updateRememberedValue(mutableTransitionState);
                    } else {
                        mutableTransitionState = objRememberedValue3;
                    }
                    MutableTransitionState mutableTransitionState2 = (MutableTransitionState) mutableTransitionState;
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    mutableTransitionState2.setTargetState$animation_core_release(Boolean.valueOf(zBooleanValue3));
                    transitionRememberTransition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState2, null, composer, MutableTransitionState.$stable, 2);
                    composer.endReplaceGroup();
                }
                Transition transition3 = transitionRememberTransition;
                composer.startMovableGroup(-359633642, Boolean.valueOf(this.isTransitionActive()));
                ComposerKt.sourceInformation(composer, "995@52949L45");
                Transition<Boolean>.DeferredAnimation<Rect, AnimationVector4D> deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition3, VectorConvertersKt.getVectorConverter(Rect.INSTANCE), null, composer, 0, 2);
                composer.endMovableGroup();
                ComposerKt.sourceInformationMarkerStart(composer, -359628914, "CC(remember):SharedTransitionScope.kt#9igjgp");
                boolean zChanged = composer.changed(transition3);
                SharedTransitionScopeImpl sharedTransitionScopeImpl2 = this;
                BoundsTransform boundsTransform2 = boundsTransform;
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    boundsAnimation = new BoundsAnimation(sharedTransitionScopeImpl2, transition3, deferredAnimationCreateDeferredAnimation, boundsTransform2);
                    composer.updateRememberedValue(boundsAnimation);
                } else {
                    boundsAnimation = objRememberedValue4;
                }
                BoundsAnimation boundsAnimation2 = (BoundsAnimation) boundsAnimation;
                ComposerKt.sourceInformationMarkerEnd(composer);
                boundsAnimation2.updateAnimation(deferredAnimationCreateDeferredAnimation, boundsTransform);
                composer.endMovableGroup();
                SharedElementInternalState sharedElementInternalStateRememberSharedElementState = this.rememberSharedElementState(sharedElement, boundsAnimation2, placeHolderSize, renderOnlyWhenVisible, sharedContentState, clipInOverlayDuringTransition, zIndexInOverlay, renderInOverlayDuringTransition, composer, 0);
                composer.endMovableGroup();
                Modifier modifierThen = modifier.then(new SharedBoundsNodeElement(sharedElementInternalStateRememberSharedElementState));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                return modifierThen;
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedElementInternalState rememberSharedElementState(SharedElement sharedElement, BoundsAnimation boundsAnimation, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderOnlyWhenVisible, SharedTransitionScope.SharedContentState sharedContentState, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition, float zIndexInOverlay, boolean renderInOverlayDuringTransition, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 2066772852, "C(rememberSharedElementState)P(6!1,2,4,5!1,7)*1032@54440L467:SharedTransitionScope.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2066772852, $changed, -1, "androidx.compose.animation.SharedTransitionScopeImpl.rememberSharedElementState (SharedTransitionScope.kt:1032)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -663265575, "CC(remember):SharedTransitionScope.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new SharedElementInternalState(sharedElement, boundsAnimation, placeHolderSize, renderOnlyWhenVisible, clipInOverlayDuringTransition, renderInOverlayDuringTransition, sharedContentState, zIndexInOverlay);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        SharedElementInternalState it = (SharedElementInternalState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        sharedContentState.setInternalState$animation_release(it);
        it.setSharedElement(sharedElement);
        it.setRenderOnlyWhenVisible(renderOnlyWhenVisible);
        it.setBoundsAnimation(boundsAnimation);
        it.setPlaceHolderSize(placeHolderSize);
        it.setOverlayClip(clipInOverlayDuringTransition);
        it.setZIndex(zIndexInOverlay);
        it.setRenderInOverlayDuringTransition(renderInOverlayDuringTransition);
        it.setUserState(sharedContentState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return it;
    }

    public final LayoutCoordinates getRoot$animation_release() {
        LayoutCoordinates layoutCoordinates = this.root;
        if (layoutCoordinates != null) {
            return layoutCoordinates;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    public final void setRoot$animation_release(LayoutCoordinates layoutCoordinates) {
        this.root = layoutCoordinates;
    }

    public final LayoutCoordinates getLookaheadRoot$animation_release() {
        LayoutCoordinates layoutCoordinates = this.nullableLookaheadRoot;
        if (layoutCoordinates == null) {
            throw new IllegalArgumentException("Error: Uninitialized LayoutCoordinates. Please make sure when using the SharedTransitionScope composable function, the modifier passed to the child content is being used, or use SharedTransitionLayout instead.".toString());
        }
        return layoutCoordinates;
    }

    /* JADX INFO: renamed from: getNullableLookaheadRoot$animation_release, reason: from getter */
    public final LayoutCoordinates getNullableLookaheadRoot() {
        return this.nullableLookaheadRoot;
    }

    public final void setNullableLookaheadRoot$animation_release(LayoutCoordinates layoutCoordinates) {
        this.nullableLookaheadRoot = layoutCoordinates;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedElement sharedElementsFor(Object key) {
        SharedElement sharedElement = this.sharedElements.get(key);
        if (sharedElement != null) {
            return sharedElement;
        }
        SharedElement it = new SharedElement(key, this);
        this.sharedElements.set(key, it);
        return it;
    }

    public final void drawInOverlay$animation_release(ContentDrawScope scope) {
        List $this$sortBy$iv = this.renderers;
        if ($this$sortBy$iv.size() > 1) {
            CollectionsKt.sortWith($this$sortBy$iv, new Comparator() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$drawInOverlay$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    LayerRenderer it = (LayerRenderer) t;
                    Float fValueOf = Float.valueOf((((it.getZIndex() > 0.0f ? 1 : (it.getZIndex() == 0.0f ? 0 : -1)) == 0) && (it instanceof SharedElementInternalState) && ((SharedElementInternalState) it).getParentState() == null) ? -1.0f : it.getZIndex());
                    LayerRenderer it2 = (LayerRenderer) t2;
                    return ComparisonsKt.compareValues(fValueOf, Float.valueOf(((it2.getZIndex() == 0.0f) && (it2 instanceof SharedElementInternalState) && ((SharedElementInternalState) it2).getParentState() == null) ? -1.0f : it2.getZIndex()));
                }
            });
        }
        List $this$fastForEach$iv = this.renderers;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            LayerRenderer it = (LayerRenderer) item$iv;
            it.drawInOverlay(scope);
        }
    }

    public final void onStateRemoved$animation_release(SharedElementInternalState sharedElementState) {
        SharedElement $this$onStateRemoved_u24lambda_u2410 = sharedElementState.getSharedElement();
        $this$onStateRemoved_u24lambda_u2410.removeState(sharedElementState);
        this.updateTransitionActiveness.invoke(this);
        SharedTransitionScopeKt.getSharedTransitionObserver().observeReads($this$onStateRemoved_u24lambda_u2410.getScope(), this.updateTransitionActiveness, this.observeAnimatingBlock);
        this.renderers.remove(sharedElementState);
        if ($this$onStateRemoved_u24lambda_u2410.getStates().isEmpty()) {
            BuildersKt__Builders_commonKt.launch$default($this$onStateRemoved_u24lambda_u2410.getScope().coroutineScope, null, null, new SharedTransitionScopeImpl$onStateRemoved$1$1($this$onStateRemoved_u24lambda_u2410, null), 3, null);
        }
    }

    public final void onStateAdded$animation_release(SharedElementInternalState sharedElementState) {
        SharedElement $this$onStateAdded_u24lambda_u2412 = sharedElementState.getSharedElement();
        $this$onStateAdded_u24lambda_u2412.addState(sharedElementState);
        this.updateTransitionActiveness.invoke(this);
        SharedTransitionScopeKt.getSharedTransitionObserver().observeReads($this$onStateAdded_u24lambda_u2412.getScope(), this.updateTransitionActiveness, this.observeAnimatingBlock);
        List $this$indexOfFirst$iv = this.renderers;
        int index$iv = 0;
        Iterator<LayerRenderer> it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                LayerRenderer it2 = (LayerRenderer) item$iv;
                SharedElementInternalState sharedElementInternalState = it2 instanceof SharedElementInternalState ? (SharedElementInternalState) it2 : null;
                if (Intrinsics.areEqual(sharedElementInternalState != null ? sharedElementInternalState.getSharedElement() : null, sharedElementState.getSharedElement())) {
                    break;
                } else {
                    index$iv++;
                }
            } else {
                index$iv = -1;
                break;
            }
        }
        int id = index$iv;
        if (id == this.renderers.size() - 1 || id == -1) {
            this.renderers.add(sharedElementState);
        } else {
            this.renderers.add(id + 1, sharedElementState);
        }
    }

    public final void onLayerRendererCreated$animation_release(LayerRenderer renderer) throws Throwable {
        this.renderers.add(renderer);
    }

    public final void onLayerRendererRemoved$animation_release(LayerRenderer renderer) {
        this.renderers.remove(renderer);
    }

    /* JADX INFO: compiled from: SharedTransitionScope.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/animation/SharedTransitionScopeImpl$ShapeBasedClip;", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "(Landroidx/compose/ui/graphics/Shape;)V", "getClipShape", "()Landroidx/compose/ui/graphics/Shape;", "path", "Landroidx/compose/ui/graphics/Path;", "getClipPath", "state", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class ShapeBasedClip implements SharedTransitionScope.OverlayClip {
        private final Shape clipShape;
        private final Path path = AndroidPath_androidKt.Path();

        public ShapeBasedClip(Shape clipShape) {
            this.clipShape = clipShape;
        }

        public final Shape getClipShape() {
            return this.clipShape;
        }

        @Override // androidx.compose.animation.SharedTransitionScope.OverlayClip
        public Path getClipPath(SharedTransitionScope.SharedContentState state, Rect bounds, LayoutDirection layoutDirection, Density density) {
            this.path.reset();
            OutlineKt.addOutline(this.path, this.clipShape.mo637createOutlinePq9zytI(bounds.m4326getSizeNHjbRc(), layoutDirection, density));
            this.path.mo4429translatek4lQ0M(bounds.m4328getTopLeftF1C5BW0());
            return this.path;
        }
    }
}
