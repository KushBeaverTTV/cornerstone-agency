package androidx.compose.animation.core;

import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0007\u0018\u0000 X*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002XYB\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u000e\u0010<\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010=J*\u0010>\u001a\u00020\b2\b\b\u0002\u00101\u001a\u00028\u00002\u0010\b\u0002\u0010?\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010@H\u0086@¢\u0006\u0002\u0010AJ\u000e\u0010B\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010=J\b\u0010C\u001a\u00020\bH\u0002J\b\u0010D\u001a\u00020\bH\u0002J\r\u0010E\u001a\u00020\bH\u0000¢\u0006\u0002\bFJ\r\u0010G\u001a\u00020\bH\u0000¢\u0006\u0002\bHJ\u0018\u0010I\u001a\u00020\b2\u0006\u0010J\u001a\u00020\u00192\u0006\u0010K\u001a\u00020\u0007H\u0002J\u000e\u0010L\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010=J\"\u0010M\u001a\u00020\b2\b\b\u0001\u0010#\u001a\u00020!2\b\b\u0002\u00101\u001a\u00028\u0000H\u0086@¢\u0006\u0002\u0010NJ\b\u0010O\u001a\u00020\bH\u0002J\u0016\u0010P\u001a\u00020\b2\u0006\u00101\u001a\u00028\u0000H\u0086@¢\u0006\u0002\u0010QJ\u001b\u0010R\u001a\u00020\b2\f\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;H\u0010¢\u0006\u0002\bSJ\r\u0010T\u001a\u00020\bH\u0010¢\u0006\u0002\bUJ\u000e\u0010V\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010=J\u000e\u0010W\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010=R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u00028\u0000X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0004R\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u000b\"\u0004\b\u001d\u0010\u0004R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010#\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020!8G@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00190+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020\b00X\u0082\u0004¢\u0006\u0002\n\u0000R+\u00101\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b4\u0010\u001f\u001a\u0004\b2\u0010\u000b\"\u0004\b3\u0010\u0004R\u001a\u00105\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0016\u0010:\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState;", "S", "Landroidx/compose/animation/core/TransitionState;", "initialState", "(Ljava/lang/Object;)V", "animateOneFrameLambda", "Lkotlin/Function1;", "", "", "composedTargetState", "getComposedTargetState$animation_core_release", "()Ljava/lang/Object;", "setComposedTargetState$animation_core_release", "Ljava/lang/Object;", "compositionContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "getCompositionContinuation$animation_core_release", "()Lkotlinx/coroutines/CancellableContinuation;", "setCompositionContinuation$animation_core_release", "(Lkotlinx/coroutines/CancellableContinuation;)V", "compositionContinuationMutex", "Lkotlinx/coroutines/sync/Mutex;", "getCompositionContinuationMutex$animation_core_release", "()Lkotlinx/coroutines/sync/Mutex;", "currentAnimation", "Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "<set-?>", "currentState", "getCurrentState", "setCurrentState$animation_core_release", "currentState$delegate", "Landroidx/compose/runtime/MutableState;", "durationScale", "", "firstFrameLambda", "fraction", "getFraction", "()F", "setFraction", "(F)V", "fraction$delegate", "Landroidx/compose/runtime/MutableFloatState;", "initialValueAnimations", "Landroidx/collection/MutableObjectList;", "lastFrameTimeNanos", "mutatorMutex", "Landroidx/compose/animation/core/MutatorMutex;", "recalculateTotalDurationNanos", "Lkotlin/Function0;", "targetState", "getTargetState", "setTargetState$animation_core_release", "targetState$delegate", "totalDurationNanos", "getTotalDurationNanos$animation_core_release", "()J", "setTotalDurationNanos$animation_core_release", "(J)V", "transition", "Landroidx/compose/animation/core/Transition;", "animateOneFrame", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateTo", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "(Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doOneFrame", "endAllAnimations", "moveAnimationToInitialState", "observeTotalDuration", "observeTotalDuration$animation_core_release", "onTotalDurationChanged", "onTotalDurationChanged$animation_core_release", "recalculateAnimationValue", "animation", "deltaPlayTimeNanos", "runAnimations", "seekTo", "(FLjava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seekToFraction", "snapTo", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transitionConfigured", "transitionConfigured$animation_core_release", "transitionRemoved", "transitionRemoved$animation_core_release", "waitForComposition", "waitForCompositionAfterTargetStateChange", "Companion", "SeekingAnimationState", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SeekableTransitionState<S> extends TransitionState<S> {
    private final Function1<Long, Unit> animateOneFrameLambda;
    private S composedTargetState;
    private CancellableContinuation<? super S> compositionContinuation;
    private final Mutex compositionContinuationMutex;
    private SeekingAnimationState currentAnimation;

    /* JADX INFO: renamed from: currentState$delegate, reason: from kotlin metadata */
    private final MutableState currentState;
    private float durationScale;
    private final Function1<Long, Unit> firstFrameLambda;

    /* JADX INFO: renamed from: fraction$delegate, reason: from kotlin metadata */
    private final MutableFloatState fraction;
    private final MutableObjectList<SeekingAnimationState> initialValueAnimations;
    private long lastFrameTimeNanos;
    private final MutatorMutex mutatorMutex;
    private final Function0<Unit> recalculateTotalDurationNanos;

    /* JADX INFO: renamed from: targetState$delegate, reason: from kotlin metadata */
    private final MutableState targetState;
    private long totalDurationNanos;
    private Transition<S> transition;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final AnimationVector1D ZeroVelocity = new AnimationVector1D(0.0f);
    private static final AnimationVector1D Target1 = new AnimationVector1D(1.0f);

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$runAnimations$1, reason: invalid class name */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {0, 1}, l = {370, 373}, m = "runAnimations", n = {"this", "this"}, s = {"L$0", "L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SeekableTransitionState<S> seekableTransitionState, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = seekableTransitionState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.runAnimations(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$waitForComposition$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {0, 0, 1, 1}, l = {566, 2186}, m = "waitForComposition", n = {"this", "expectedState", "this", "expectedState"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C02811 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02811(SeekableTransitionState<S> seekableTransitionState, Continuation<? super C02811> continuation) {
            super(continuation);
            this.this$0 = seekableTransitionState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.waitForComposition(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$waitForCompositionAfterTargetStateChange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {0, 0, 1, 1}, l = {542, 2186}, m = "waitForCompositionAfterTargetStateChange", n = {"this", "expectedState", "this", "expectedState"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C02821 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02821(SeekableTransitionState<S> seekableTransitionState, Continuation<? super C02821> continuation) {
            super(continuation);
            this.this$0 = seekableTransitionState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.waitForCompositionAfterTargetStateChange(this);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SeekableTransitionState(S s) {
        DefaultConstructorMarker defaultConstructorMarker = null;
        super(defaultConstructorMarker);
        this.targetState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(s, null, 2, null);
        this.currentState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(s, null, 2, null);
        this.composedTargetState = s;
        this.recalculateTotalDurationNanos = new Function0<Unit>(this) { // from class: androidx.compose.animation.core.SeekableTransitionState$recalculateTotalDurationNanos$1
            final /* synthetic */ SeekableTransitionState<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                SeekableTransitionState<S> seekableTransitionState = this.this$0;
                Transition transition = ((SeekableTransitionState) this.this$0).transition;
                seekableTransitionState.setTotalDurationNanos$animation_core_release(transition != null ? transition.getTotalDurationNanos() : 0L);
            }
        };
        this.fraction = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.compositionContinuationMutex = MutexKt.Mutex$default(false, 1, null);
        this.mutatorMutex = new MutatorMutex();
        this.lastFrameTimeNanos = Long.MIN_VALUE;
        this.initialValueAnimations = new MutableObjectList<>(0, 1, defaultConstructorMarker);
        this.firstFrameLambda = new Function1<Long, Unit>(this) { // from class: androidx.compose.animation.core.SeekableTransitionState$firstFrameLambda$1
            final /* synthetic */ SeekableTransitionState<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long frameTimeNanos) {
                ((SeekableTransitionState) this.this$0).lastFrameTimeNanos = frameTimeNanos;
            }
        };
        this.animateOneFrameLambda = new Function1<Long, Unit>(this) { // from class: androidx.compose.animation.core.SeekableTransitionState$animateOneFrameLambda$1
            final /* synthetic */ SeekableTransitionState<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long frameTimeNanos) {
                long delta = frameTimeNanos - ((SeekableTransitionState) this.this$0).lastFrameTimeNanos;
                ((SeekableTransitionState) this.this$0).lastFrameTimeNanos = frameTimeNanos;
                long deltaPlayTimeNanos = MathKt.roundToLong(delta / ((double) ((SeekableTransitionState) this.this$0).durationScale));
                if (((SeekableTransitionState) this.this$0).initialValueAnimations.isNotEmpty()) {
                    ObjectList this_$iv = ((SeekableTransitionState) this.this$0).initialValueAnimations;
                    SeekableTransitionState<S> seekableTransitionState = this.this$0;
                    Object[] content$iv = this_$iv.content;
                    int i = this_$iv._size;
                    for (int i$iv = 0; i$iv < i; i$iv++) {
                        SeekableTransitionState.SeekingAnimationState animation = (SeekableTransitionState.SeekingAnimationState) content$iv[i$iv];
                        seekableTransitionState.recalculateAnimationValue(animation, deltaPlayTimeNanos);
                        animation.setComplete(true);
                    }
                    Transition transition = ((SeekableTransitionState) this.this$0).transition;
                    if (transition != null) {
                        transition.updateInitialValues$animation_core_release();
                    }
                    MutableObjectList this_$iv2 = ((SeekableTransitionState) this.this$0).initialValueAnimations;
                    int gap$iv = 0;
                    int size$iv = this_$iv2._size;
                    Object[] content$iv2 = this_$iv2.content;
                    MutableObjectList this_$iv$iv = this_$iv2;
                    IntRange intRangeUntil = RangesKt.until(0, this_$iv$iv._size);
                    int i$iv2 = intRangeUntil.getFirst();
                    int last = intRangeUntil.getLast();
                    if (i$iv2 <= last) {
                        while (true) {
                            content$iv2[i$iv2 - gap$iv] = content$iv2[i$iv2];
                            SeekableTransitionState.SeekingAnimationState it = (SeekableTransitionState.SeekingAnimationState) content$iv2[i$iv2];
                            if (it.getIsComplete()) {
                                gap$iv++;
                            }
                            if (i$iv2 == last) {
                                break;
                            } else {
                                i$iv2++;
                            }
                        }
                    }
                    ArraysKt.fill(content$iv2, (Object) null, size$iv - gap$iv, size$iv);
                    this_$iv2._size -= gap$iv;
                }
                SeekableTransitionState.SeekingAnimationState currentAnimation = ((SeekableTransitionState) this.this$0).currentAnimation;
                if (currentAnimation != null) {
                    currentAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                    this.this$0.recalculateAnimationValue(currentAnimation, deltaPlayTimeNanos);
                    this.this$0.setFraction(currentAnimation.getValue());
                    if (currentAnimation.getValue() == 1.0f) {
                        ((SeekableTransitionState) this.this$0).currentAnimation = null;
                    }
                    this.this$0.seekToFraction();
                }
            }
        };
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getTargetState() {
        return (S) this.targetState.getValue();
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void setTargetState$animation_core_release(S s) {
        MutableState $this$setValue$iv = this.targetState;
        $this$setValue$iv.setValue(s);
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getCurrentState() {
        return (S) this.currentState.getValue();
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void setCurrentState$animation_core_release(S s) {
        MutableState $this$setValue$iv = this.currentState;
        $this$setValue$iv.setValue(s);
    }

    public final S getComposedTargetState$animation_core_release() {
        return this.composedTargetState;
    }

    public final void setComposedTargetState$animation_core_release(S s) {
        this.composedTargetState = s;
    }

    /* JADX INFO: renamed from: getTotalDurationNanos$animation_core_release, reason: from getter */
    public final long getTotalDurationNanos() {
        return this.totalDurationNanos;
    }

    public final void setTotalDurationNanos$animation_core_release(long j) {
        this.totalDurationNanos = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFraction(float f) {
        MutableFloatState $this$setValue$iv = this.fraction;
        $this$setValue$iv.setFloatValue(f);
    }

    public final float getFraction() {
        FloatState $this$getValue$iv = this.fraction;
        return $this$getValue$iv.getFloatValue();
    }

    public final CancellableContinuation<S> getCompositionContinuation$animation_core_release() {
        return this.compositionContinuation;
    }

    public final void setCompositionContinuation$animation_core_release(CancellableContinuation<? super S> cancellableContinuation) {
        this.compositionContinuation = cancellableContinuation;
    }

    /* JADX INFO: renamed from: getCompositionContinuationMutex$animation_core_release, reason: from getter */
    public final Mutex getCompositionContinuationMutex() {
        return this.compositionContinuationMutex;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endAllAnimations() {
        Transition<S> transition = this.transition;
        if (transition != null) {
            transition.clearInitialAnimations$animation_core_release();
        }
        this.initialValueAnimations.clear();
        SeekingAnimationState current = this.currentAnimation;
        if (current != null) {
            this.currentAnimation = null;
            setFraction(1.0f);
            seekToFraction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object runAnimations(Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        SeekableTransitionState seekableTransitionState;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(this, continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(this, continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                seekableTransitionState = this;
                if (seekableTransitionState.initialValueAnimations.isEmpty() && seekableTransitionState.currentAnimation == null) {
                    return Unit.INSTANCE;
                }
                if (SuspendAnimationKt.getDurationScale(anonymousClass2.get$context()) == 0.0f) {
                    seekableTransitionState.endAllAnimations();
                    seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                    return Unit.INSTANCE;
                }
                if (seekableTransitionState.lastFrameTimeNanos == Long.MIN_VALUE) {
                    Function1<Long, Unit> function1 = seekableTransitionState.firstFrameLambda;
                    anonymousClass2.L$0 = seekableTransitionState;
                    anonymousClass2.label = 1;
                    if (MonotonicFrameClockKt.withFrameNanos(function1, anonymousClass2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                break;
            case 1:
                seekableTransitionState = (SeekableTransitionState) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                break;
            case 2:
                seekableTransitionState = (SeekableTransitionState) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        do {
            if (seekableTransitionState.initialValueAnimations.isNotEmpty() || seekableTransitionState.currentAnimation != null) {
                anonymousClass2.L$0 = seekableTransitionState;
                anonymousClass2.label = 2;
            } else {
                seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                return Unit.INSTANCE;
            }
        } while (seekableTransitionState.animateOneFrame(anonymousClass2) != coroutine_suspended);
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object doOneFrame(Continuation<? super Unit> continuation) {
        if (this.lastFrameTimeNanos == Long.MIN_VALUE) {
            Object objWithFrameNanos = MonotonicFrameClockKt.withFrameNanos(this.firstFrameLambda, continuation);
            return objWithFrameNanos == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithFrameNanos : Unit.INSTANCE;
        }
        Object objAnimateOneFrame = animateOneFrame(continuation);
        return objAnimateOneFrame == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateOneFrame : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateOneFrame(Continuation<? super Unit> continuation) {
        float durationScale = SuspendAnimationKt.getDurationScale(continuation.get$context());
        if (durationScale <= 0.0f) {
            endAllAnimations();
            return Unit.INSTANCE;
        }
        this.durationScale = durationScale;
        Object objWithFrameNanos = MonotonicFrameClockKt.withFrameNanos(this.animateOneFrameLambda, continuation);
        return objWithFrameNanos == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithFrameNanos : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recalculateAnimationValue(SeekingAnimationState animation, long deltaPlayTimeNanos) {
        long playTimeNanos = animation.getProgressNanos() + deltaPlayTimeNanos;
        animation.setProgressNanos(playTimeNanos);
        long durationNanos = animation.getAnimationSpecDuration();
        if (playTimeNanos >= durationNanos) {
            animation.setValue(1.0f);
            return;
        }
        VectorizedAnimationSpec<AnimationVector1D> animationSpec = animation.getAnimationSpec();
        if (animationSpec == null) {
            animation.setValue(VectorConvertersKt.lerp(animation.getStart().get$animation_core_release(0), 1.0f, playTimeNanos / durationNanos));
            return;
        }
        AnimationVector1D start = animation.getStart();
        AnimationVector1D animationVector1D = Target1;
        AnimationVector1D initialVelocity = animation.getInitialVelocity();
        if (initialVelocity == null) {
            initialVelocity = ZeroVelocity;
        }
        animation.setValue(RangesKt.coerceIn(((AnimationVector1D) animationSpec.getValueFromNanos(playTimeNanos, start, animationVector1D, initialVelocity)).get$animation_core_release(0), 0.0f, 1.0f));
    }

    public final Object snapTo(S s, Continuation<? super Unit> continuation) {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        if (Intrinsics.areEqual(getCurrentState(), s) && Intrinsics.areEqual(getTargetState(), s)) {
            return Unit.INSTANCE;
        }
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new C02802(this, s, transition, null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$snapTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "S"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$snapTo$2", f = "Transition.kt", i = {}, l = {477}, m = "invokeSuspend", n = {}, s = {})
    static final class C02802 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02802(SeekableTransitionState<S> seekableTransitionState, S s, Transition<S> transition, Continuation<? super C02802> continuation) {
            super(1, continuation);
            this.this$0 = seekableTransitionState;
            this.$targetState = s;
            this.$transition = transition;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C02802(this.this$0, this.$targetState, this.$transition, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C02802) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            C02802 c02802;
            float fraction;
            C02802 c02803;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    c02802 = this;
                    c02802.this$0.endAllAnimations();
                    ((SeekableTransitionState) c02802.this$0).lastFrameTimeNanos = Long.MIN_VALUE;
                    c02802.this$0.setFraction(0.0f);
                    S s = c02802.$targetState;
                    if (Intrinsics.areEqual(s, c02802.this$0.getCurrentState())) {
                        fraction = -4.0f;
                    } else {
                        fraction = Intrinsics.areEqual(s, c02802.this$0.getTargetState()) ? -5.0f : -3.0f;
                    }
                    c02802.$transition.updateTarget$animation_core_release(c02802.$targetState);
                    c02802.$transition.setPlayTimeNanos(0L);
                    c02802.this$0.setTargetState$animation_core_release(c02802.$targetState);
                    c02802.this$0.setFraction(0.0f);
                    c02802.this$0.setCurrentState$animation_core_release(c02802.$targetState);
                    c02802.$transition.resetAnimationFraction$animation_core_release(fraction);
                    if (fraction == -3.0f) {
                        c02802.label = 1;
                        if (c02802.this$0.waitForCompositionAfterTargetStateChange(c02802) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c02803 = c02802;
                        c02802 = c02803;
                    }
                    c02802.$transition.onTransitionEnd$animation_core_release();
                    return Unit.INSTANCE;
                case 1:
                    c02803 = this;
                    ResultKt.throwOnFailure($result);
                    c02802 = c02803;
                    c02802.$transition.onTransitionEnd$animation_core_release();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object seekTo$default(SeekableTransitionState seekableTransitionState, float f, Object obj, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = seekableTransitionState.getTargetState();
        }
        return seekableTransitionState.seekTo(f, obj, continuation);
    }

    public final Object seekTo(float fraction, S s, Continuation<? super Unit> continuation) {
        boolean z = false;
        if (0.0f <= fraction && fraction <= 1.0f) {
            z = true;
        }
        boolean value$iv = z;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Expecting fraction between 0 and 1. Got " + fraction);
        }
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        Object oldTargetState = getTargetState();
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new AnonymousClass3(s, oldTargetState, this, transition, fraction, null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekTo$3, reason: invalid class name */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "S"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$seekTo$3", f = "Transition.kt", i = {}, l = {509}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ float $fraction;
        final /* synthetic */ S $oldTargetState;
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(S s, S s2, SeekableTransitionState<S> seekableTransitionState, Transition<S> transition, float f, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$targetState = s;
            this.$oldTargetState = s2;
            this.this$0 = seekableTransitionState;
            this.$transition = transition;
            this.$fraction = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$targetState, this.$oldTargetState, this.this$0, this.$transition, this.$fraction, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: Transition.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "S", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1", f = "Transition.kt", i = {}, l = {531}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ float $fraction;
            final /* synthetic */ S $oldTargetState;
            final /* synthetic */ S $targetState;
            final /* synthetic */ Transition<S> $transition;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SeekableTransitionState<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(S s, S s2, SeekableTransitionState<S> seekableTransitionState, Transition<S> transition, float f, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$targetState = s;
                this.$oldTargetState = s2;
                this.this$0 = seekableTransitionState;
                this.$transition = transition;
                this.$fraction = f;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$targetState, this.$oldTargetState, this.this$0, this.$transition, this.$fraction, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                AnonymousClass1 anonymousClass1;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                        if (!Intrinsics.areEqual(this.$targetState, this.$oldTargetState)) {
                            this.this$0.moveAnimationToInitialState();
                        } else {
                            ((SeekableTransitionState) this.this$0).currentAnimation = null;
                            if (Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                return Unit.INSTANCE;
                            }
                        }
                        if (!Intrinsics.areEqual(this.$targetState, this.$oldTargetState)) {
                            this.$transition.updateTarget$animation_core_release(this.$targetState);
                            this.$transition.setPlayTimeNanos(0L);
                            this.this$0.setTargetState$animation_core_release(this.$targetState);
                            this.$transition.resetAnimationFraction$animation_core_release(this.$fraction);
                        }
                        this.this$0.setFraction(this.$fraction);
                        if (((SeekableTransitionState) this.this$0).initialValueAnimations.isNotEmpty()) {
                            BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new C00061(this.this$0, null), 3, null);
                        } else {
                            ((SeekableTransitionState) this.this$0).lastFrameTimeNanos = Long.MIN_VALUE;
                        }
                        this.label = 1;
                        if (this.this$0.waitForCompositionAfterTargetStateChange(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        anonymousClass1 = this;
                        break;
                        break;
                    case 1:
                        anonymousClass1 = this;
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                anonymousClass1.this$0.seekToFraction();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: Transition.kt */
            @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "S", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1$1", f = "Transition.kt", i = {}, l = {527}, m = "invokeSuspend", n = {}, s = {})
            static final class C00061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ SeekableTransitionState<S> this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00061(SeekableTransitionState<S> seekableTransitionState, Continuation<? super C00061> continuation) {
                    super(2, continuation);
                    this.this$0 = seekableTransitionState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00061(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00061) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (this.this$0.runAnimations(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return Unit.INSTANCE;
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.$targetState, this.$oldTargetState, this.this$0, this.$transition, this.$fraction, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x006a  */
    /* JADX WARN: Code duplicated, block: B:21:0x0070  */
    /* JADX WARN: Code duplicated, block: B:23:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:25:0x00aa A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:31:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object waitForCompositionAfterTargetStateChange(Continuation<? super Unit> continuation) {
        C02821 c02821;
        SeekableTransitionState<S> seekableTransitionState;
        Object expectedState;
        Object result;
        Object state;
        if (continuation instanceof C02821) {
            c02821 = (C02821) continuation;
            if ((c02821.label & Integer.MIN_VALUE) != 0) {
                c02821.label -= Integer.MIN_VALUE;
            } else {
                c02821 = new C02821(this, continuation);
            }
        } else {
            c02821 = new C02821(this, continuation);
        }
        C02821 c02822 = c02821;
        Object $result = c02822.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02822.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Object expectedState2 = getTargetState();
                Mutex mutex = this.compositionContinuationMutex;
                c02822.L$0 = this;
                c02822.L$1 = expectedState2;
                c02822.label = 1;
                if (Mutex.DefaultImpls.lock$default(mutex, null, c02822, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                seekableTransitionState = this;
                expectedState = expectedState2;
                if (Intrinsics.areEqual(expectedState, seekableTransitionState.composedTargetState)) {
                    Mutex.DefaultImpls.unlock$default(seekableTransitionState.compositionContinuationMutex, null, 1, null);
                } else {
                    c02822.L$0 = seekableTransitionState;
                    c02822.L$1 = expectedState;
                    c02822.label = 2;
                    C02821 uCont$iv = c02822;
                    CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv), 1);
                    cancellable$iv.initCancellability();
                    CancellableContinuationImpl continuation2 = cancellable$iv;
                    seekableTransitionState.setCompositionContinuation$animation_core_release(continuation2);
                    Mutex.DefaultImpls.unlock$default(seekableTransitionState.getCompositionContinuationMutex(), null, 1, null);
                    result = cancellable$iv.getResult();
                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(c02822);
                    }
                    if (result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    state = result;
                    if (!Intrinsics.areEqual(state, expectedState)) {
                        seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                        throw new CancellationException("snapTo() was canceled because state was changed to " + state + " instead of " + expectedState);
                    }
                }
                return Unit.INSTANCE;
            case 1:
                expectedState = c02822.L$1;
                seekableTransitionState = (SeekableTransitionState) c02822.L$0;
                ResultKt.throwOnFailure($result);
                if (Intrinsics.areEqual(expectedState, seekableTransitionState.composedTargetState)) {
                    Mutex.DefaultImpls.unlock$default(seekableTransitionState.compositionContinuationMutex, null, 1, null);
                } else {
                    c02822.L$0 = seekableTransitionState;
                    c02822.L$1 = expectedState;
                    c02822.label = 2;
                    C02821 uCont$iv2 = c02822;
                    CancellableContinuationImpl cancellable$iv2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv2), 1);
                    cancellable$iv2.initCancellability();
                    CancellableContinuationImpl continuation3 = cancellable$iv2;
                    seekableTransitionState.setCompositionContinuation$animation_core_release(continuation3);
                    Mutex.DefaultImpls.unlock$default(seekableTransitionState.getCompositionContinuationMutex(), null, 1, null);
                    result = cancellable$iv2.getResult();
                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(c02822);
                    }
                    if (result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    state = result;
                    if (!Intrinsics.areEqual(state, expectedState)) {
                        seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                        throw new CancellationException("snapTo() was canceled because state was changed to " + state + " instead of " + expectedState);
                    }
                }
                return Unit.INSTANCE;
            case 2:
                expectedState = c02822.L$1;
                SeekableTransitionState<S> seekableTransitionState2 = (SeekableTransitionState) c02822.L$0;
                ResultKt.throwOnFailure($result);
                seekableTransitionState = seekableTransitionState2;
                result = $result;
                state = result;
                if (!Intrinsics.areEqual(state, expectedState)) {
                    seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                    throw new CancellationException("snapTo() was canceled because state was changed to " + state + " instead of " + expectedState);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x0093  */
    /* JADX WARN: Code duplicated, block: B:22:0x009b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x009c  */
    /* JADX WARN: Code duplicated, block: B:26:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object waitForComposition(Continuation<? super Unit> continuation) {
        C02811 c02811;
        SeekableTransitionState<S> seekableTransitionState;
        Object expectedState;
        Object result;
        Object state;
        if (continuation instanceof C02811) {
            c02811 = (C02811) continuation;
            if ((c02811.label & Integer.MIN_VALUE) != 0) {
                c02811.label -= Integer.MIN_VALUE;
            } else {
                c02811 = new C02811(this, continuation);
            }
        } else {
            c02811 = new C02811(this, continuation);
        }
        C02811 c02812 = c02811;
        Object $result = c02812.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02812.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Object expectedState2 = getTargetState();
                Mutex mutex = this.compositionContinuationMutex;
                c02812.L$0 = this;
                c02812.L$1 = expectedState2;
                c02812.label = 1;
                if (Mutex.DefaultImpls.lock$default(mutex, null, c02812, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                seekableTransitionState = this;
                expectedState = expectedState2;
                c02812.L$0 = seekableTransitionState;
                c02812.L$1 = expectedState;
                c02812.label = 2;
                C02811 uCont$iv = c02812;
                CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv), 1);
                cancellable$iv.initCancellability();
                CancellableContinuationImpl continuation2 = cancellable$iv;
                seekableTransitionState.setCompositionContinuation$animation_core_release(continuation2);
                Mutex.DefaultImpls.unlock$default(seekableTransitionState.getCompositionContinuationMutex(), null, 1, null);
                result = cancellable$iv.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(c02812);
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
                state = result;
                if (!Intrinsics.areEqual(state, expectedState)) {
                    seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                    throw new CancellationException("targetState while waiting for composition");
                }
                return Unit.INSTANCE;
            case 1:
                expectedState = c02812.L$1;
                seekableTransitionState = (SeekableTransitionState) c02812.L$0;
                ResultKt.throwOnFailure($result);
                c02812.L$0 = seekableTransitionState;
                c02812.L$1 = expectedState;
                c02812.label = 2;
                C02811 uCont$iv2 = c02812;
                CancellableContinuationImpl cancellable$iv2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv2), 1);
                cancellable$iv2.initCancellability();
                CancellableContinuationImpl continuation3 = cancellable$iv2;
                seekableTransitionState.setCompositionContinuation$animation_core_release(continuation3);
                Mutex.DefaultImpls.unlock$default(seekableTransitionState.getCompositionContinuationMutex(), null, 1, null);
                result = cancellable$iv2.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(c02812);
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
                state = result;
                if (!Intrinsics.areEqual(state, expectedState)) {
                    seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                    throw new CancellationException("targetState while waiting for composition");
                }
                return Unit.INSTANCE;
            case 2:
                expectedState = c02812.L$1;
                SeekableTransitionState<S> seekableTransitionState2 = (SeekableTransitionState) c02812.L$0;
                ResultKt.throwOnFailure($result);
                seekableTransitionState = seekableTransitionState2;
                result = $result;
                state = result;
                if (!Intrinsics.areEqual(state, expectedState)) {
                    seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                    throw new CancellationException("targetState while waiting for composition");
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:18:0x0062  */
    public final void moveAnimationToInitialState() {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return;
        }
        SeekingAnimationState it = this.currentAnimation;
        if (it == null) {
            if (this.totalDurationNanos > 0) {
                if ((getFraction() == 1.0f) || Intrinsics.areEqual(getCurrentState(), getTargetState())) {
                    it = null;
                } else {
                    it = new SeekingAnimationState();
                    it.setValue(getFraction());
                    long totalDurationNanos = this.totalDurationNanos;
                    it.setDurationNanos(totalDurationNanos);
                    it.setAnimationSpecDuration(MathKt.roundToLong(totalDurationNanos * (1.0d - ((double) getFraction()))));
                    it.getStart().set$animation_core_release(0, getFraction());
                }
            } else {
                it = null;
            }
        }
        if (it != null) {
            it.setDurationNanos(this.totalDurationNanos);
            this.initialValueAnimations.add(it);
            transition.setInitialAnimations$animation_core_release(it);
        }
        this.currentAnimation = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateTo$default(SeekableTransitionState seekableTransitionState, Object obj, FiniteAnimationSpec finiteAnimationSpec, Continuation continuation, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = seekableTransitionState.getTargetState();
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = null;
        }
        return seekableTransitionState.animateTo(obj, finiteAnimationSpec, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$animateTo$2, reason: invalid class name */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "S"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2", f = "Transition.kt", i = {}, l = {623}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Transition<S> transition, SeekableTransitionState<S> seekableTransitionState, S s, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$transition = transition;
            this.this$0 = seekableTransitionState;
            this.$targetState = s;
            this.$animationSpec = finiteAnimationSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.$transition, this.this$0, this.$targetState, this.$animationSpec, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: Transition.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "S", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1", f = "Transition.kt", i = {0}, l = {2191, 636, 638, 690, 692}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv"}, s = {"L$0"})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
            final /* synthetic */ S $targetState;
            final /* synthetic */ Transition<S> $transition;
            Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ SeekableTransitionState<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(SeekableTransitionState<S> seekableTransitionState, S s, Transition<S> transition, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = seekableTransitionState;
                this.$targetState = s;
                this.$transition = transition;
                this.$animationSpec = finiteAnimationSpec;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:21:0x00b6  */
            /* JADX WARN: Code duplicated, block: B:23:0x00c8 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:26:0x00d7 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:28:0x00d9 A[PHI: r2 r7
  0x00d9: PHI (r2v6 androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1) = 
  (r2v2 androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1)
  (r2v4 androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1)
  (r2v7 androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1)
 binds: [B:20:0x00b4, B:25:0x00d5, B:8:0x002a] A[DONT_GENERATE, DONT_INLINE]
  0x00d9: PHI (r7v5 '$result' java.lang.Object) = (r7v1 '$result' java.lang.Object), (r7v3 '$result' java.lang.Object), (r7v6 '$result' java.lang.Object) binds: [B:20:0x00b4, B:25:0x00d5, B:8:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:30:0x00e7  */
            /* JADX WARN: Code duplicated, block: B:32:0x00f3  */
            /* JADX WARN: Code duplicated, block: B:34:0x00fd  */
            /* JADX WARN: Code duplicated, block: B:35:0x0108  */
            /* JADX WARN: Code duplicated, block: B:37:0x010b  */
            /* JADX WARN: Code duplicated, block: B:39:0x0115 A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:40:0x0117  */
            /* JADX WARN: Code duplicated, block: B:41:0x011c  */
            /* JADX WARN: Code duplicated, block: B:44:0x0122  */
            /* JADX WARN: Code duplicated, block: B:46:0x013f  */
            /* JADX WARN: Code duplicated, block: B:48:0x0152  */
            /* JADX WARN: Code duplicated, block: B:49:0x0154  */
            /* JADX WARN: Code duplicated, block: B:60:0x018b  */
            /* JADX WARN: Code duplicated, block: B:62:0x0195  */
            /* JADX WARN: Code duplicated, block: B:65:0x01cb  */
            /* JADX WARN: Code duplicated, block: B:66:0x01e2  */
            /* JADX WARN: Code duplicated, block: B:70:0x0212 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:71:0x0213  */
            /* JADX WARN: Code duplicated, block: B:74:0x0229 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:75:0x022a  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                AnonymousClass1 anonymousClass1;
                Object $result;
                Mutex $this$withLock_u24default$iv;
                SeekableTransitionState<S> seekableTransitionState;
                Object owner$iv;
                Object composedTargetState;
                Object $result2;
                SeekingAnimationState newAnimation;
                FiniteAnimationSpec<Float> finiteAnimationSpec;
                VectorizedFiniteAnimationSpec newSpec;
                VectorizedAnimationSpec<AnimationVector1D> animationSpec;
                VectorizedAnimationSpec<AnimationVector1D> vectorizedAnimationSpec;
                AnimationVector1D oldVelocity;
                long jRoundToLong;
                AnimationVector1D initialVelocity;
                AnonymousClass1 anonymousClass2;
                Object $result3;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        anonymousClass1 = this;
                        $result = obj;
                        S targetState = anonymousClass1.this$0.getTargetState();
                        if (!Intrinsics.areEqual(anonymousClass1.$targetState, targetState)) {
                            anonymousClass1.this$0.moveAnimationToInitialState();
                            anonymousClass1.this$0.setFraction(0.0f);
                            anonymousClass1.$transition.updateTarget$animation_core_release(anonymousClass1.$targetState);
                            anonymousClass1.$transition.setPlayTimeNanos(0L);
                            anonymousClass1.this$0.setCurrentState$animation_core_release(targetState);
                            anonymousClass1.this$0.setTargetState$animation_core_release(anonymousClass1.$targetState);
                        }
                        $this$withLock_u24default$iv = anonymousClass1.this$0.getCompositionContinuationMutex();
                        seekableTransitionState = anonymousClass1.this$0;
                        owner$iv = null;
                        anonymousClass1.L$0 = $this$withLock_u24default$iv;
                        anonymousClass1.L$1 = seekableTransitionState;
                        anonymousClass1.label = 1;
                        if ($this$withLock_u24default$iv.lock(null, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        try {
                            composedTargetState = seekableTransitionState.getComposedTargetState$animation_core_release();
                            $this$withLock_u24default$iv.unlock(owner$iv);
                            if (!Intrinsics.areEqual(anonymousClass1.$targetState, composedTargetState)) {
                                anonymousClass1.L$0 = null;
                                anonymousClass1.L$1 = null;
                                anonymousClass1.label = 2;
                                if (anonymousClass1.this$0.doOneFrame(anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                anonymousClass1.label = 3;
                                if (anonymousClass1.this$0.waitForCompositionAfterTargetStateChange(anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                if (!Intrinsics.areEqual(anonymousClass1.this$0.getCurrentState(), anonymousClass1.$targetState)) {
                                    if (anonymousClass1.this$0.getFraction() < 1.0f) {
                                        newAnimation = ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation;
                                        finiteAnimationSpec = anonymousClass1.$animationSpec;
                                        if (finiteAnimationSpec != null) {
                                            newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                        } else {
                                            newSpec = null;
                                        }
                                        if (newAnimation != null || !Intrinsics.areEqual(newSpec, newAnimation.getAnimationSpec())) {
                                            if (newAnimation != null) {
                                                animationSpec = newAnimation.getAnimationSpec();
                                            } else {
                                                animationSpec = null;
                                            }
                                            vectorizedAnimationSpec = animationSpec;
                                            if (vectorizedAnimationSpec != null) {
                                                long progressNanos = newAnimation.getProgressNanos();
                                                AnimationVector1D start = newAnimation.getStart();
                                                AnimationVector1D target1 = SeekableTransitionState.Companion.getTarget1();
                                                initialVelocity = newAnimation.getInitialVelocity();
                                                if (initialVelocity == null) {
                                                    initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                                }
                                                oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos, start, target1, initialVelocity);
                                            } else if (newAnimation != null || newAnimation.getProgressNanos() == 0) {
                                                oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            } else {
                                                long oldDurationNanos = newAnimation.getDurationNanos();
                                                if (oldDurationNanos == Long.MIN_VALUE) {
                                                    oldDurationNanos = anonymousClass1.this$0.getTotalDurationNanos();
                                                }
                                                float oldDuration = oldDurationNanos / 1.0E9f;
                                                oldVelocity = oldDuration <= 0.0f ? SeekableTransitionState.Companion.getZeroVelocity() : new AnimationVector1D(1.0f / oldDuration);
                                            }
                                            if (newAnimation == null) {
                                                newAnimation = new SeekingAnimationState();
                                            }
                                            newAnimation.setAnimationSpec(newSpec);
                                            newAnimation.setComplete(false);
                                            newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                            newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                            newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                            newAnimation.setProgressNanos(0L);
                                            newAnimation.setInitialVelocity(oldVelocity);
                                            if (newSpec != null) {
                                                jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                            } else {
                                                jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                            }
                                            newAnimation.setAnimationSpecDuration(jRoundToLong);
                                            ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                        }
                                    }
                                    anonymousClass1.L$0 = null;
                                    anonymousClass1.L$1 = null;
                                    anonymousClass1.label = 4;
                                    if (anonymousClass1.this$0.runAnimations(anonymousClass1) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    $result2 = $result;
                                    anonymousClass1.this$0.setCurrentState$animation_core_release(anonymousClass1.$targetState);
                                    anonymousClass1.label = 5;
                                    if (anonymousClass1.this$0.waitForComposition(anonymousClass1) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    anonymousClass2 = anonymousClass1;
                                    $result3 = $result2;
                                    anonymousClass2.this$0.setFraction(0.0f);
                                }
                            } else if (!Intrinsics.areEqual(anonymousClass1.this$0.getCurrentState(), anonymousClass1.$targetState)) {
                                if (anonymousClass1.this$0.getFraction() < 1.0f) {
                                    newAnimation = ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation;
                                    finiteAnimationSpec = anonymousClass1.$animationSpec;
                                    if (finiteAnimationSpec != null) {
                                        newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                    } else {
                                        newSpec = null;
                                    }
                                    if (newAnimation != null) {
                                        if (newAnimation != null) {
                                            animationSpec = newAnimation.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        vectorizedAnimationSpec = animationSpec;
                                        if (vectorizedAnimationSpec != null) {
                                            long progressNanos2 = newAnimation.getProgressNanos();
                                            AnimationVector1D start2 = newAnimation.getStart();
                                            AnimationVector1D target2 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = newAnimation.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos2, start2, target2, initialVelocity);
                                        } else if (newAnimation != null) {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (newAnimation == null) {
                                            newAnimation = new SeekingAnimationState();
                                        }
                                        newAnimation.setAnimationSpec(newSpec);
                                        newAnimation.setComplete(false);
                                        newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                        newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                        newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                        newAnimation.setProgressNanos(0L);
                                        newAnimation.setInitialVelocity(oldVelocity);
                                        if (newSpec != null) {
                                            jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                        }
                                        newAnimation.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                    } else {
                                        if (newAnimation != null) {
                                            animationSpec = newAnimation.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        vectorizedAnimationSpec = animationSpec;
                                        if (vectorizedAnimationSpec != null) {
                                            long progressNanos3 = newAnimation.getProgressNanos();
                                            AnimationVector1D start3 = newAnimation.getStart();
                                            AnimationVector1D target3 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = newAnimation.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos3, start3, target3, initialVelocity);
                                        } else if (newAnimation != null) {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (newAnimation == null) {
                                            newAnimation = new SeekingAnimationState();
                                        }
                                        newAnimation.setAnimationSpec(newSpec);
                                        newAnimation.setComplete(false);
                                        newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                        newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                        newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                        newAnimation.setProgressNanos(0L);
                                        newAnimation.setInitialVelocity(oldVelocity);
                                        if (newSpec != null) {
                                            jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                        }
                                        newAnimation.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                    }
                                }
                                anonymousClass1.L$0 = null;
                                anonymousClass1.L$1 = null;
                                anonymousClass1.label = 4;
                                if (anonymousClass1.this$0.runAnimations(anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $result2 = $result;
                                anonymousClass1.this$0.setCurrentState$animation_core_release(anonymousClass1.$targetState);
                                anonymousClass1.label = 5;
                                if (anonymousClass1.this$0.waitForComposition(anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                anonymousClass2 = anonymousClass1;
                                $result3 = $result2;
                                anonymousClass2.this$0.setFraction(0.0f);
                            }
                            return Unit.INSTANCE;
                        } catch (Throwable th) {
                            $this$withLock_u24default$iv.unlock(owner$iv);
                            throw th;
                        }
                    case 1:
                        anonymousClass1 = this;
                        $result = obj;
                        seekableTransitionState = (SeekableTransitionState) anonymousClass1.L$1;
                        owner$iv = null;
                        $this$withLock_u24default$iv = (Mutex) anonymousClass1.L$0;
                        ResultKt.throwOnFailure($result);
                        composedTargetState = seekableTransitionState.getComposedTargetState$animation_core_release();
                        $this$withLock_u24default$iv.unlock(owner$iv);
                        if (!Intrinsics.areEqual(anonymousClass1.$targetState, composedTargetState)) {
                            anonymousClass1.L$0 = null;
                            anonymousClass1.L$1 = null;
                            anonymousClass1.label = 2;
                            if (anonymousClass1.this$0.doOneFrame(anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass1.label = 3;
                            if (anonymousClass1.this$0.waitForCompositionAfterTargetStateChange(anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (!Intrinsics.areEqual(anonymousClass1.this$0.getCurrentState(), anonymousClass1.$targetState)) {
                                if (anonymousClass1.this$0.getFraction() < 1.0f) {
                                    newAnimation = ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation;
                                    finiteAnimationSpec = anonymousClass1.$animationSpec;
                                    if (finiteAnimationSpec != null) {
                                        newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                    } else {
                                        newSpec = null;
                                    }
                                    if (newAnimation != null) {
                                        if (newAnimation != null) {
                                            animationSpec = newAnimation.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        vectorizedAnimationSpec = animationSpec;
                                        if (vectorizedAnimationSpec != null) {
                                            long progressNanos4 = newAnimation.getProgressNanos();
                                            AnimationVector1D start4 = newAnimation.getStart();
                                            AnimationVector1D target4 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = newAnimation.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos4, start4, target4, initialVelocity);
                                        } else if (newAnimation != null) {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (newAnimation == null) {
                                            newAnimation = new SeekingAnimationState();
                                        }
                                        newAnimation.setAnimationSpec(newSpec);
                                        newAnimation.setComplete(false);
                                        newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                        newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                        newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                        newAnimation.setProgressNanos(0L);
                                        newAnimation.setInitialVelocity(oldVelocity);
                                        if (newSpec != null) {
                                            jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                        }
                                        newAnimation.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                    } else {
                                        if (newAnimation != null) {
                                            animationSpec = newAnimation.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        vectorizedAnimationSpec = animationSpec;
                                        if (vectorizedAnimationSpec != null) {
                                            long progressNanos5 = newAnimation.getProgressNanos();
                                            AnimationVector1D start5 = newAnimation.getStart();
                                            AnimationVector1D target5 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = newAnimation.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos5, start5, target5, initialVelocity);
                                        } else if (newAnimation != null) {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (newAnimation == null) {
                                            newAnimation = new SeekingAnimationState();
                                        }
                                        newAnimation.setAnimationSpec(newSpec);
                                        newAnimation.setComplete(false);
                                        newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                        newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                        newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                        newAnimation.setProgressNanos(0L);
                                        newAnimation.setInitialVelocity(oldVelocity);
                                        if (newSpec != null) {
                                            jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                        }
                                        newAnimation.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                    }
                                }
                                anonymousClass1.L$0 = null;
                                anonymousClass1.L$1 = null;
                                anonymousClass1.label = 4;
                                if (anonymousClass1.this$0.runAnimations(anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $result2 = $result;
                                anonymousClass1.this$0.setCurrentState$animation_core_release(anonymousClass1.$targetState);
                                anonymousClass1.label = 5;
                                if (anonymousClass1.this$0.waitForComposition(anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                anonymousClass2 = anonymousClass1;
                                $result3 = $result2;
                                anonymousClass2.this$0.setFraction(0.0f);
                            }
                        } else if (!Intrinsics.areEqual(anonymousClass1.this$0.getCurrentState(), anonymousClass1.$targetState)) {
                            if (anonymousClass1.this$0.getFraction() < 1.0f) {
                                newAnimation = ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation;
                                finiteAnimationSpec = anonymousClass1.$animationSpec;
                                if (finiteAnimationSpec != null) {
                                    newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                } else {
                                    newSpec = null;
                                }
                                if (newAnimation != null) {
                                    if (newAnimation != null) {
                                        animationSpec = newAnimation.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    vectorizedAnimationSpec = animationSpec;
                                    if (vectorizedAnimationSpec != null) {
                                        long progressNanos6 = newAnimation.getProgressNanos();
                                        AnimationVector1D start6 = newAnimation.getStart();
                                        AnimationVector1D target6 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = newAnimation.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos6, start6, target6, initialVelocity);
                                    } else if (newAnimation != null) {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (newAnimation == null) {
                                        newAnimation = new SeekingAnimationState();
                                    }
                                    newAnimation.setAnimationSpec(newSpec);
                                    newAnimation.setComplete(false);
                                    newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                    newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                    newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                    newAnimation.setProgressNanos(0L);
                                    newAnimation.setInitialVelocity(oldVelocity);
                                    if (newSpec != null) {
                                        jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                    }
                                    newAnimation.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                } else {
                                    if (newAnimation != null) {
                                        animationSpec = newAnimation.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    vectorizedAnimationSpec = animationSpec;
                                    if (vectorizedAnimationSpec != null) {
                                        long progressNanos7 = newAnimation.getProgressNanos();
                                        AnimationVector1D start7 = newAnimation.getStart();
                                        AnimationVector1D target7 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = newAnimation.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos7, start7, target7, initialVelocity);
                                    } else if (newAnimation != null) {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (newAnimation == null) {
                                        newAnimation = new SeekingAnimationState();
                                    }
                                    newAnimation.setAnimationSpec(newSpec);
                                    newAnimation.setComplete(false);
                                    newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                    newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                    newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                    newAnimation.setProgressNanos(0L);
                                    newAnimation.setInitialVelocity(oldVelocity);
                                    if (newSpec != null) {
                                        jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                    }
                                    newAnimation.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                }
                            }
                            anonymousClass1.L$0 = null;
                            anonymousClass1.L$1 = null;
                            anonymousClass1.label = 4;
                            if (anonymousClass1.this$0.runAnimations(anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result2 = $result;
                            anonymousClass1.this$0.setCurrentState$animation_core_release(anonymousClass1.$targetState);
                            anonymousClass1.label = 5;
                            if (anonymousClass1.this$0.waitForComposition(anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass2 = anonymousClass1;
                            $result3 = $result2;
                            anonymousClass2.this$0.setFraction(0.0f);
                        }
                        return Unit.INSTANCE;
                    case 2:
                        anonymousClass1 = this;
                        $result = obj;
                        ResultKt.throwOnFailure($result);
                        anonymousClass1.label = 3;
                        if (anonymousClass1.this$0.waitForCompositionAfterTargetStateChange(anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (!Intrinsics.areEqual(anonymousClass1.this$0.getCurrentState(), anonymousClass1.$targetState)) {
                            if (anonymousClass1.this$0.getFraction() < 1.0f) {
                                newAnimation = ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation;
                                finiteAnimationSpec = anonymousClass1.$animationSpec;
                                if (finiteAnimationSpec != null) {
                                    newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                } else {
                                    newSpec = null;
                                }
                                if (newAnimation != null) {
                                    if (newAnimation != null) {
                                        animationSpec = newAnimation.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    vectorizedAnimationSpec = animationSpec;
                                    if (vectorizedAnimationSpec != null) {
                                        long progressNanos8 = newAnimation.getProgressNanos();
                                        AnimationVector1D start8 = newAnimation.getStart();
                                        AnimationVector1D target8 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = newAnimation.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos8, start8, target8, initialVelocity);
                                    } else if (newAnimation != null) {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (newAnimation == null) {
                                        newAnimation = new SeekingAnimationState();
                                    }
                                    newAnimation.setAnimationSpec(newSpec);
                                    newAnimation.setComplete(false);
                                    newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                    newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                    newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                    newAnimation.setProgressNanos(0L);
                                    newAnimation.setInitialVelocity(oldVelocity);
                                    if (newSpec != null) {
                                        jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                    }
                                    newAnimation.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                } else {
                                    if (newAnimation != null) {
                                        animationSpec = newAnimation.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    vectorizedAnimationSpec = animationSpec;
                                    if (vectorizedAnimationSpec != null) {
                                        long progressNanos9 = newAnimation.getProgressNanos();
                                        AnimationVector1D start9 = newAnimation.getStart();
                                        AnimationVector1D target9 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = newAnimation.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos9, start9, target9, initialVelocity);
                                    } else if (newAnimation != null) {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (newAnimation == null) {
                                        newAnimation = new SeekingAnimationState();
                                    }
                                    newAnimation.setAnimationSpec(newSpec);
                                    newAnimation.setComplete(false);
                                    newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                    newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                    newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                    newAnimation.setProgressNanos(0L);
                                    newAnimation.setInitialVelocity(oldVelocity);
                                    if (newSpec != null) {
                                        jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                    }
                                    newAnimation.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                }
                            }
                            anonymousClass1.L$0 = null;
                            anonymousClass1.L$1 = null;
                            anonymousClass1.label = 4;
                            if (anonymousClass1.this$0.runAnimations(anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result2 = $result;
                            anonymousClass1.this$0.setCurrentState$animation_core_release(anonymousClass1.$targetState);
                            anonymousClass1.label = 5;
                            if (anonymousClass1.this$0.waitForComposition(anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass2 = anonymousClass1;
                            $result3 = $result2;
                            anonymousClass2.this$0.setFraction(0.0f);
                        }
                        return Unit.INSTANCE;
                    case 3:
                        anonymousClass1 = this;
                        $result = obj;
                        ResultKt.throwOnFailure($result);
                        if (!Intrinsics.areEqual(anonymousClass1.this$0.getCurrentState(), anonymousClass1.$targetState)) {
                            if (anonymousClass1.this$0.getFraction() < 1.0f) {
                                newAnimation = ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation;
                                finiteAnimationSpec = anonymousClass1.$animationSpec;
                                if (finiteAnimationSpec != null) {
                                    newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                } else {
                                    newSpec = null;
                                }
                                if (newAnimation != null) {
                                    if (newAnimation != null) {
                                        animationSpec = newAnimation.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    vectorizedAnimationSpec = animationSpec;
                                    if (vectorizedAnimationSpec != null) {
                                        long progressNanos10 = newAnimation.getProgressNanos();
                                        AnimationVector1D start10 = newAnimation.getStart();
                                        AnimationVector1D target10 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = newAnimation.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos10, start10, target10, initialVelocity);
                                    } else if (newAnimation != null) {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (newAnimation == null) {
                                        newAnimation = new SeekingAnimationState();
                                    }
                                    newAnimation.setAnimationSpec(newSpec);
                                    newAnimation.setComplete(false);
                                    newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                    newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                    newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                    newAnimation.setProgressNanos(0L);
                                    newAnimation.setInitialVelocity(oldVelocity);
                                    if (newSpec != null) {
                                        jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                    }
                                    newAnimation.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                } else {
                                    if (newAnimation != null) {
                                        animationSpec = newAnimation.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    vectorizedAnimationSpec = animationSpec;
                                    if (vectorizedAnimationSpec != null) {
                                        long progressNanos11 = newAnimation.getProgressNanos();
                                        AnimationVector1D start11 = newAnimation.getStart();
                                        AnimationVector1D target11 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = newAnimation.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos11, start11, target11, initialVelocity);
                                    } else if (newAnimation != null) {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (newAnimation == null) {
                                        newAnimation = new SeekingAnimationState();
                                    }
                                    newAnimation.setAnimationSpec(newSpec);
                                    newAnimation.setComplete(false);
                                    newAnimation.setValue(anonymousClass1.this$0.getFraction());
                                    newAnimation.getStart().set$animation_core_release(0, anonymousClass1.this$0.getFraction());
                                    newAnimation.setDurationNanos(anonymousClass1.this$0.getTotalDurationNanos());
                                    newAnimation.setProgressNanos(0L);
                                    newAnimation.setInitialVelocity(oldVelocity);
                                    if (newSpec != null) {
                                        jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(anonymousClass1.this$0.getTotalDurationNanos() * (1.0d - ((double) anonymousClass1.this$0.getFraction())));
                                    }
                                    newAnimation.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) anonymousClass1.this$0).currentAnimation = newAnimation;
                                }
                            }
                            anonymousClass1.L$0 = null;
                            anonymousClass1.L$1 = null;
                            anonymousClass1.label = 4;
                            if (anonymousClass1.this$0.runAnimations(anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result2 = $result;
                            anonymousClass1.this$0.setCurrentState$animation_core_release(anonymousClass1.$targetState);
                            anonymousClass1.label = 5;
                            if (anonymousClass1.this$0.waitForComposition(anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass2 = anonymousClass1;
                            $result3 = $result2;
                            anonymousClass2.this$0.setFraction(0.0f);
                        }
                        return Unit.INSTANCE;
                    case 4:
                        anonymousClass1 = this;
                        $result2 = obj;
                        ResultKt.throwOnFailure($result2);
                        anonymousClass1.this$0.setCurrentState$animation_core_release(anonymousClass1.$targetState);
                        anonymousClass1.label = 5;
                        if (anonymousClass1.this$0.waitForComposition(anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        anonymousClass2 = anonymousClass1;
                        $result3 = $result2;
                        anonymousClass2.this$0.setFraction(0.0f);
                        return Unit.INSTANCE;
                    case 5:
                        anonymousClass2 = this;
                        $result3 = obj;
                        ResultKt.throwOnFailure($result3);
                        anonymousClass2.this$0.setFraction(0.0f);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass2 anonymousClass2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass2 = this;
                    break;
                    break;
                case 1:
                    anonymousClass2 = this;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            anonymousClass2.$transition.onTransitionEnd$animation_core_release();
            return Unit.INSTANCE;
        }
    }

    public final Object animateTo(S s, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super Unit> continuation) {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new AnonymousClass2(transition, this, s, finiteAnimationSpec, null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void transitionConfigured$animation_core_release(Transition<S> transition) {
        boolean value$iv = this.transition == null || Intrinsics.areEqual(transition, this.transition);
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("An instance of SeekableTransitionState has been used in different Transitions. Previous instance: " + this.transition + ", new instance: " + transition);
        }
        this.transition = transition;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void transitionRemoved$animation_core_release() {
        this.transition = null;
        TransitionKt.getSeekableStateObserver().clear(this);
    }

    public final void observeTotalDuration$animation_core_release() {
        TransitionKt.getSeekableStateObserver().observeReads(this, TransitionKt.SeekableTransitionStateTotalDurationChanged, this.recalculateTotalDurationNanos);
    }

    public final void onTotalDurationChanged$animation_core_release() {
        long previousTotalDurationNanos = this.totalDurationNanos;
        observeTotalDuration$animation_core_release();
        if (previousTotalDurationNanos != this.totalDurationNanos) {
            SeekingAnimationState animation = this.currentAnimation;
            if (animation == null) {
                if (this.totalDurationNanos != 0) {
                    seekToFraction();
                }
            } else {
                animation.setDurationNanos(this.totalDurationNanos);
                if (animation.getAnimationSpec() == null) {
                    animation.setAnimationSpecDuration(MathKt.roundToLong((1.0d - ((double) animation.getStart().get$animation_core_release(0))) * this.totalDurationNanos));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void seekToFraction() {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return;
        }
        long playTimeNanos = MathKt.roundToLong(((double) getFraction()) * transition.getTotalDurationNanos());
        transition.seekAnimations$animation_core_release(playTimeNanos);
    }

    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010)\u001a\u00020*H\u0016R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\r\"\u0004\b\u001f\u0010\u000fR\u001a\u0010 \u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0015\"\u0004\b\"\u0010\u0017R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006+"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "", "()V", "animationSpec", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "Landroidx/compose/animation/core/AnimationVector1D;", "getAnimationSpec", "()Landroidx/compose/animation/core/VectorizedAnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/VectorizedAnimationSpec;)V", "animationSpecDuration", "", "getAnimationSpecDuration", "()J", "setAnimationSpecDuration", "(J)V", "durationNanos", "getDurationNanos", "setDurationNanos", "initialVelocity", "getInitialVelocity", "()Landroidx/compose/animation/core/AnimationVector1D;", "setInitialVelocity", "(Landroidx/compose/animation/core/AnimationVector1D;)V", "isComplete", "", "()Z", "setComplete", "(Z)V", "progressNanos", "getProgressNanos", "setProgressNanos", "start", "getStart", "setStart", "value", "", "getValue", "()F", "setValue", "(F)V", "toString", "", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SeekingAnimationState {
        public static final int $stable = 8;
        private VectorizedAnimationSpec<AnimationVector1D> animationSpec;
        private long animationSpecDuration;
        private long durationNanos;
        private AnimationVector1D initialVelocity;
        private boolean isComplete;
        private long progressNanos;
        private AnimationVector1D start = new AnimationVector1D(0.0f);
        private float value;

        public final long getProgressNanos() {
            return this.progressNanos;
        }

        public final void setProgressNanos(long j) {
            this.progressNanos = j;
        }

        public final VectorizedAnimationSpec<AnimationVector1D> getAnimationSpec() {
            return this.animationSpec;
        }

        public final void setAnimationSpec(VectorizedAnimationSpec<AnimationVector1D> vectorizedAnimationSpec) {
            this.animationSpec = vectorizedAnimationSpec;
        }

        /* JADX INFO: renamed from: isComplete, reason: from getter */
        public final boolean getIsComplete() {
            return this.isComplete;
        }

        public final void setComplete(boolean z) {
            this.isComplete = z;
        }

        public final float getValue() {
            return this.value;
        }

        public final void setValue(float f) {
            this.value = f;
        }

        public final AnimationVector1D getStart() {
            return this.start;
        }

        public final void setStart(AnimationVector1D animationVector1D) {
            this.start = animationVector1D;
        }

        public final AnimationVector1D getInitialVelocity() {
            return this.initialVelocity;
        }

        public final void setInitialVelocity(AnimationVector1D animationVector1D) {
            this.initialVelocity = animationVector1D;
        }

        public final long getDurationNanos() {
            return this.durationNanos;
        }

        public final void setDurationNanos(long j) {
            this.durationNanos = j;
        }

        public final long getAnimationSpecDuration() {
            return this.animationSpecDuration;
        }

        public final void setAnimationSpecDuration(long j) {
            this.animationSpecDuration = j;
        }

        public String toString() {
            return "progress nanos: " + this.progressNanos + ", animationSpec: " + this.animationSpec + ", isComplete: " + this.isComplete + ", value: " + this.value + ", start: " + this.start + ", initialVelocity: " + this.initialVelocity + ", durationNanos: " + this.durationNanos + ", animationSpecDuration: " + this.animationSpecDuration;
        }
    }

    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState$Companion;", "", "()V", "Target1", "Landroidx/compose/animation/core/AnimationVector1D;", "getTarget1", "()Landroidx/compose/animation/core/AnimationVector1D;", "ZeroVelocity", "getZeroVelocity", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AnimationVector1D getZeroVelocity() {
            return SeekableTransitionState.ZeroVelocity;
        }

        public final AnimationVector1D getTarget1() {
            return SeekableTransitionState.Target1;
        }
    }
}
