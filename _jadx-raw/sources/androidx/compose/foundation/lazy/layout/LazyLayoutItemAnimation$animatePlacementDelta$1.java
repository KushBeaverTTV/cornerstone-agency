package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.ui.unit.IntOffset;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1", f = "LazyLayoutItemAnimation.kt", i = {0}, l = {151, 158}, m = "invokeSuspend", n = {"finalSpec"}, s = {"L$0"})
final class LazyLayoutItemAnimation$animatePlacementDelta$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FiniteAnimationSpec<IntOffset> $spec;
    final /* synthetic */ long $totalDelta;
    Object L$0;
    int label;
    final /* synthetic */ LazyLayoutItemAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyLayoutItemAnimation$animatePlacementDelta$1(LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec<IntOffset> finiteAnimationSpec, long j, Continuation<? super LazyLayoutItemAnimation$animatePlacementDelta$1> continuation) {
        super(2, continuation);
        this.this$0 = lazyLayoutItemAnimation;
        this.$spec = finiteAnimationSpec;
        this.$totalDelta = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LazyLayoutItemAnimation$animatePlacementDelta$1(this.this$0, this.$spec, this.$totalDelta, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LazyLayoutItemAnimation$animatePlacementDelta$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00b9 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:32:0x00ba  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LazyLayoutItemAnimation$animatePlacementDelta$1 lazyLayoutItemAnimation$animatePlacementDelta$1;
        SpringSpec springSpec;
        final long jM7147minusqkQi6aY;
        Animatable animatable;
        Function1<Animatable<IntOffset, AnimationVector2D>, Unit> function1;
        Continuation continuation;
        ?? r0;
        LazyLayoutItemAnimation$animatePlacementDelta$1 lazyLayoutItemAnimation$animatePlacementDelta$2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
            switch (r1) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    lazyLayoutItemAnimation$animatePlacementDelta$1 = this;
                    if (lazyLayoutItemAnimation$animatePlacementDelta$1.this$0.placementDeltaAnimation.isRunning()) {
                        springSpec = lazyLayoutItemAnimation$animatePlacementDelta$1.$spec instanceof SpringSpec ? (SpringSpec) lazyLayoutItemAnimation$animatePlacementDelta$1.$spec : LazyLayoutItemAnimationKt.InterruptionSpec;
                    } else {
                        springSpec = lazyLayoutItemAnimation$animatePlacementDelta$1.$spec;
                    }
                    boolean zIsRunning = lazyLayoutItemAnimation$animatePlacementDelta$1.this$0.placementDeltaAnimation.isRunning();
                    r1 = lazyLayoutItemAnimation$animatePlacementDelta$1;
                    if (!zIsRunning) {
                        lazyLayoutItemAnimation$animatePlacementDelta$1.L$0 = springSpec;
                        lazyLayoutItemAnimation$animatePlacementDelta$1.label = 1;
                        if (lazyLayoutItemAnimation$animatePlacementDelta$1.this$0.placementDeltaAnimation.snapTo(IntOffset.m7135boximpl(lazyLayoutItemAnimation$animatePlacementDelta$1.$totalDelta), lazyLayoutItemAnimation$animatePlacementDelta$1) == coroutine_suspended) {
                            lazyLayoutItemAnimation$animatePlacementDelta$2 = lazyLayoutItemAnimation$animatePlacementDelta$1;
                            return coroutine_suspended;
                        }
                        lazyLayoutItemAnimation$animatePlacementDelta$2 = lazyLayoutItemAnimation$animatePlacementDelta$1;
                        lazyLayoutItemAnimation$animatePlacementDelta$2.this$0.onLayerPropertyChanged.invoke();
                        r1 = lazyLayoutItemAnimation$animatePlacementDelta$2;
                    }
                    jM7147minusqkQi6aY = IntOffset.m7147minusqkQi6aY(((IntOffset) r1.this$0.placementDeltaAnimation.getValue()).getPackedValue(), r1.$totalDelta);
                    animatable = r1.this$0.placementDeltaAnimation;
                    final LazyLayoutItemAnimation lazyLayoutItemAnimation = r1.this$0;
                    function1 = new Function1<Animatable<IntOffset, AnimationVector2D>, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Animatable<IntOffset, AnimationVector2D> animatable2) {
                            invoke2(animatable2);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Animatable<IntOffset, AnimationVector2D> animatable2) {
                            lazyLayoutItemAnimation.m1189setPlacementDeltagyyYBs(IntOffset.m7147minusqkQi6aY(animatable2.getValue().getPackedValue(), jM7147minusqkQi6aY));
                            lazyLayoutItemAnimation.onLayerPropertyChanged.invoke();
                        }
                    };
                    continuation = (Continuation) r1;
                    r1.L$0 = null;
                    r1.label = 2;
                    if (animatable.animateTo(IntOffset.m7135boximpl(jM7147minusqkQi6aY), (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpec, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : function1, continuation) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r0 = r1;
                    r0.this$0.setPlacementAnimationInProgress(false);
                    r0.this$0.isRunningMovingAwayAnimation = false;
                    return Unit.INSTANCE;
                case 1:
                    LazyLayoutItemAnimation$animatePlacementDelta$1 lazyLayoutItemAnimation$animatePlacementDelta$3 = this;
                    springSpec = (FiniteAnimationSpec) lazyLayoutItemAnimation$animatePlacementDelta$3.L$0;
                    ResultKt.throwOnFailure(obj);
                    lazyLayoutItemAnimation$animatePlacementDelta$2 = lazyLayoutItemAnimation$animatePlacementDelta$3;
                    lazyLayoutItemAnimation$animatePlacementDelta$2 = lazyLayoutItemAnimation$animatePlacementDelta$1;
                    lazyLayoutItemAnimation$animatePlacementDelta$2.this$0.onLayerPropertyChanged.invoke();
                    r1 = lazyLayoutItemAnimation$animatePlacementDelta$2;
                    jM7147minusqkQi6aY = IntOffset.m7147minusqkQi6aY(((IntOffset) r1.this$0.placementDeltaAnimation.getValue()).getPackedValue(), r1.$totalDelta);
                    animatable = r1.this$0.placementDeltaAnimation;
                    final LazyLayoutItemAnimation lazyLayoutItemAnimation2 = r1.this$0;
                    function1 = new Function1<Animatable<IntOffset, AnimationVector2D>, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Animatable<IntOffset, AnimationVector2D> animatable2) {
                            invoke2(animatable2);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Animatable<IntOffset, AnimationVector2D> animatable2) {
                            lazyLayoutItemAnimation2.m1189setPlacementDeltagyyYBs(IntOffset.m7147minusqkQi6aY(animatable2.getValue().getPackedValue(), jM7147minusqkQi6aY));
                            lazyLayoutItemAnimation2.onLayerPropertyChanged.invoke();
                        }
                    };
                    continuation = (Continuation) r1;
                    r1.L$0 = null;
                    r1.label = 2;
                    if (animatable.animateTo(IntOffset.m7135boximpl(jM7147minusqkQi6aY), (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpec, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : function1, continuation) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r0 = r1;
                    r0.this$0.setPlacementAnimationInProgress(false);
                    r0.this$0.isRunningMovingAwayAnimation = false;
                    return Unit.INSTANCE;
                case 2:
                    r0 = this;
                    try {
                        ResultKt.throwOnFailure(obj);
                        r0.this$0.setPlacementAnimationInProgress(false);
                        r0.this$0.isRunningMovingAwayAnimation = false;
                        break;
                    } catch (CancellationException e) {
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (CancellationException e2) {
        }
    }
}
