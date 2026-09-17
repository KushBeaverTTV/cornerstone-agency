package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.input.pointer.util.VelocityTrackerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* JADX INFO: compiled from: Draggable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b \u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B5\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u0006\u0010\u001e\u001a\u00020\u001fJM\u0010 \u001a\u00020\u001f2=\u0010!\u001a9\b\u0001\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110#¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u001f0\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0'\u0012\u0006\u0012\u0004\u0018\u00010(0\"H¦@¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020\u001dH\u0002J\b\u0010+\u001a\u00020\u001fH\u0016J\b\u0010,\u001a\u00020\u001fH\u0016J\u001a\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H&ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u001a\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u000204H&ø\u0001\u0000¢\u0006\u0004\b5\u00101J*\u00106\u001a\u00020\u001f2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0016ø\u0001\u0000¢\u0006\u0004\b=\u0010>J\u000e\u0010?\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010@J\u0016\u0010A\u001a\u00020\u001f2\u0006\u0010B\u001a\u00020CH\u0082@¢\u0006\u0002\u0010DJ\u0016\u0010E\u001a\u00020\u001f2\u0006\u0010B\u001a\u00020FH\u0082@¢\u0006\u0002\u0010GJ\b\u0010H\u001a\u00020\u0007H&J\b\u0010I\u001a\u00020\u001fH\u0002JH\u0010J\u001a\u00020\u001f2\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010K\u001a\u00020\u0007R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\n@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006L"}, d2 = {"Landroidx/compose/foundation/gestures/DragGestureNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "canDrag", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "orientationLock", "Landroidx/compose/foundation/gestures/Orientation;", "(Lkotlin/jvm/functions/Function1;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/gestures/Orientation;)V", "_canDrag", "<set-?>", "getCanDrag", "()Lkotlin/jvm/functions/Function1;", "channel", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/compose/foundation/gestures/DragEvent;", "dragInteraction", "Landroidx/compose/foundation/interaction/DragInteraction$Start;", "getEnabled", "()Z", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "isListeningForEvents", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "disposeInteractionSource", "", "drag", "forEachDelta", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dragDelta", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initializePointerInputNode", "onCancelPointerInput", "onDetach", "onDragStarted", "startedPosition", "Landroidx/compose/ui/geometry/Offset;", "onDragStarted-k-4lQ0M", "(J)V", "onDragStopped", "velocity", "Landroidx/compose/ui/unit/Velocity;", "onDragStopped-TH1AsA0", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "processDragCancel", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processDragStart", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/foundation/gestures/DragEvent$DragStarted;", "(Landroidx/compose/foundation/gestures/DragEvent$DragStarted;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processDragStop", "Landroidx/compose/foundation/gestures/DragEvent$DragStopped;", "(Landroidx/compose/foundation/gestures/DragEvent$DragStopped;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startDragImmediately", "startListeningForEvents", "update", "shouldResetPointerInputHandling", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class DragGestureNode extends DelegatingNode implements PointerInputModifierNode, CompositionLocalConsumerModifierNode {
    public static final int $stable = 8;
    private final Function1<PointerInputChange, Boolean> _canDrag = new Function1<PointerInputChange, Boolean>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$_canDrag$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(PointerInputChange it) {
            return this.this$0.getCanDrag().invoke(it);
        }
    };
    private Function1<? super PointerInputChange, Boolean> canDrag;
    private Channel<DragEvent> channel;
    private DragInteraction.Start dragInteraction;
    private boolean enabled;
    private MutableInteractionSource interactionSource;
    private boolean isListeningForEvents;
    private Orientation orientationLock;
    private SuspendingPointerInputModifierNode pointerInputNode;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {0}, l = {566}, m = "processDragCancel", n = {"this"}, s = {"L$0"})
    static final class C03251 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C03251(Continuation<? super C03251> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragCancel(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {0, 0, 1, 1, 1}, l = {548, 551}, m = "processDragStart", n = {"this", NotificationCompat.CATEGORY_EVENT, "this", NotificationCompat.CATEGORY_EVENT, "interaction"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    static final class C03261 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C03261(Continuation<? super C03261> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragStart(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {0, 0}, l = {558}, m = "processDragStop", n = {"this", NotificationCompat.CATEGORY_EVENT}, s = {"L$0", "L$1"})
    static final class C03271 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03271(Continuation<? super C03271> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragStop(null, this);
        }
    }

    public abstract Object drag(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation);

    /* JADX INFO: renamed from: onDragStarted-k-4lQ0M */
    public abstract void mo743onDragStartedk4lQ0M(long startedPosition);

    /* JADX INFO: renamed from: onDragStopped-TH1AsA0 */
    public abstract void mo744onDragStoppedTH1AsA0(long velocity);

    /* JADX INFO: renamed from: startDragImmediately */
    public abstract boolean getStartDragImmediately();

    public DragGestureNode(Function1<? super PointerInputChange, Boolean> function1, boolean enabled, MutableInteractionSource interactionSource, Orientation orientationLock) {
        this.orientationLock = orientationLock;
        this.canDrag = function1;
        this.enabled = enabled;
        this.interactionSource = interactionSource;
    }

    protected final Function1<PointerInputChange, Boolean> getCanDrag() {
        return this.canDrag;
    }

    protected final boolean getEnabled() {
        return this.enabled;
    }

    protected final MutableInteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startListeningForEvents() {
        this.isListeningForEvents = true;
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C03281(null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1", f = "Draggable.kt", i = {0, 0, 1, 1, 2, 2, 3, 4, 5}, l = {431, 433, 435, 442, 444, 447}, m = "invokeSuspend", n = {"$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$0", "L$0"})
    static final class C03281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        C03281(Continuation<? super C03281> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03281 c03281 = DragGestureNode.this.new C03281(continuation);
            c03281.L$0 = obj;
            return c03281;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x007e  */
        /* JADX WARN: Code duplicated, block: B:27:0x008b  */
        /* JADX WARN: Code duplicated, block: B:29:0x009d A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:30:0x009e  */
        /* JADX WARN: Code duplicated, block: B:32:0x00a8  */
        /* JADX WARN: Code duplicated, block: B:35:0x00b7  */
        /* JADX WARN: Code duplicated, block: B:37:0x00cf A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:38:0x00d0  */
        /* JADX WARN: Code duplicated, block: B:42:0x00f1 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:43:0x00f2  */
        /* JADX WARN: Code duplicated, block: B:46:0x00f9 A[Catch: CancellationException -> 0x0133, TryCatch #0 {CancellationException -> 0x0133, blocks: (B:44:0x00f3, B:46:0x00f9, B:50:0x0118, B:52:0x011e), top: B:66:0x00f3 }] */
        /* JADX WARN: Code duplicated, block: B:48:0x0115 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:49:0x0116  */
        /* JADX WARN: Code duplicated, block: B:50:0x0118 A[Catch: CancellationException -> 0x0133, TryCatch #0 {CancellationException -> 0x0133, blocks: (B:44:0x00f3, B:46:0x00f9, B:50:0x0118, B:52:0x011e), top: B:66:0x00f3 }] */
        /* JADX WARN: Code duplicated, block: B:52:0x011e A[Catch: CancellationException -> 0x0133, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x0133, blocks: (B:44:0x00f3, B:46:0x00f9, B:50:0x0118, B:52:0x011e), top: B:66:0x00f3 }] */
        /* JADX WARN: Code duplicated, block: B:54:0x0130 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:63:0x014b  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v15 */
        /* JADX WARN: Type inference failed for: r10v18 */
        /* JADX WARN: Type inference failed for: r10v5, types: [T] */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v12, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r1v16, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r1v17, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r1v18, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r1v19, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r1v2, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r1v20 */
        /* JADX WARN: Type inference failed for: r1v21 */
        /* JADX WARN: Type inference failed for: r1v22 */
        /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r1v6 */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r1v9, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r3v13, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
        /* JADX WARN: Type inference failed for: r3v15 */
        /* JADX WARN: Type inference failed for: r3v18 */
        /* JADX WARN: Type inference failed for: r3v19 */
        /* JADX WARN: Type inference failed for: r3v24 */
        /* JADX WARN: Type inference failed for: r3v35 */
        /* JADX WARN: Type inference failed for: r3v9 */
        /* JADX WARN: Type inference failed for: r4v8 */
        /* JADX WARN: Type inference failed for: r5v0 */
        /* JADX WARN: Type inference failed for: r6v9 */
        /* JADX WARN: Type inference failed for: r7v2 */
        /* JADX WARN: Type inference failed for: r7v4 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x011c -> B:23:0x0078). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x012e -> B:23:0x0078). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x0148 -> B:23:0x0078). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:63:0x014b -> B:23:0x0078). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:38:0x00d0
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 358
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.C03281.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Draggable.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00010\u0003H\u008a@"}, d2 = {"<anonymous>", "", "processDelta", "Lkotlin/Function1;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dragDelta"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1$1", f = "Draggable.kt", i = {0}, l = {438}, m = "invokeSuspend", n = {"processDelta"}, s = {"L$0"})
        static final class C00261 extends SuspendLambda implements Function2<Function1<? super DragEvent.DragDelta, ? extends Unit>, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.ObjectRef<DragEvent> $event;
            /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DragGestureNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00261(Ref.ObjectRef<DragEvent> objectRef, DragGestureNode dragGestureNode, Continuation<? super C00261> continuation) {
                super(2, continuation);
                this.$event = objectRef;
                this.this$0 = dragGestureNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00261 c00261 = new C00261(this.$event, this.this$0, continuation);
                c00261.L$0 = obj;
                return c00261;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Function1<? super DragEvent.DragDelta, ? extends Unit> function1, Continuation<? super Unit> continuation) {
                return invoke2((Function1<? super DragEvent.DragDelta, Unit>) function1, continuation);
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(Function1<? super DragEvent.DragDelta, Unit> function1, Continuation<? super Unit> continuation) {
                return ((C00261) create(function1, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:10:0x0035  */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0057 -> B:25:0x0079). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x006a -> B:24:0x0071). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                C00261 c00261;
                Function1 function1;
                T t;
                Ref.ObjectRef<DragEvent> objectRef;
                Object obj2;
                Function1 function2;
                Ref.ObjectRef<DragEvent> objectRef2;
                C00261 c00262;
                Object obj3;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        c00261 = this;
                        function1 = (Function1) c00261.L$0;
                        if ((c00261.$event.element instanceof DragEvent.DragStopped) && !(c00261.$event.element instanceof DragEvent.DragCancelled)) {
                            DragEvent dragEvent = c00261.$event.element;
                            t = 0;
                            DragEvent.DragDelta dragDelta = dragEvent instanceof DragEvent.DragDelta ? (DragEvent.DragDelta) dragEvent : null;
                            if (dragDelta != null) {
                                function1.invoke(dragDelta);
                            }
                            objectRef = c00261.$event;
                            Channel channel = c00261.this$0.channel;
                            if (channel != null) {
                                c00261.L$0 = function1;
                                c00261.L$1 = objectRef;
                                c00261.label = 1;
                                Object objReceive = channel.receive(c00261);
                                if (objReceive == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                Object obj4 = coroutine_suspended;
                                obj2 = obj;
                                obj = objReceive;
                                function2 = function1;
                                objectRef2 = objectRef;
                                c00262 = c00261;
                                obj3 = obj4;
                                DragEvent dragEvent2 = (DragEvent) obj;
                                obj = obj2;
                                coroutine_suspended = obj3;
                                c00261 = c00262;
                                objectRef = objectRef2;
                                function1 = function2;
                                t = dragEvent2;
                            }
                            objectRef.element = t;
                            if (c00261.$event.element instanceof DragEvent.DragStopped) {
                            }
                            return Unit.INSTANCE;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        Ref.ObjectRef<DragEvent> objectRef3 = (Ref.ObjectRef) this.L$1;
                        Function1 function3 = (Function1) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        function2 = function3;
                        objectRef2 = objectRef3;
                        c00262 = this;
                        obj3 = coroutine_suspended;
                        obj2 = obj;
                        DragEvent dragEvent3 = (DragEvent) obj;
                        obj = obj2;
                        coroutine_suspended = obj3;
                        c00261 = c00262;
                        objectRef = objectRef2;
                        function1 = function2;
                        t = dragEvent3;
                        objectRef.element = t;
                        if (c00261.$event.element instanceof DragEvent.DragStopped) {
                            break;
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.isListeningForEvents = false;
        disposeInteractionSource();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo562onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        if (this.enabled && this.pointerInputNode == null) {
            this.pointerInputNode = (SuspendingPointerInputModifierNode) delegate(initializePointerInputNode());
        }
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.mo562onPointerEventH0pRuoY(pointerEvent, pass, bounds);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1, reason: invalid class name */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1", f = "Draggable.kt", i = {}, l = {524}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = DragGestureNode.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$SuspendingPointerInputModifierNode = (PointerInputScope) this.L$0;
                    final VelocityTracker velocityTracker = new VelocityTracker();
                    final DragGestureNode dragGestureNode = DragGestureNode.this;
                    Function3<PointerInputChange, PointerInputChange, Offset, Unit> function3 = new Function3<PointerInputChange, PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragStart$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2, Offset offset) {
                            m786invoke0AR0LA0(pointerInputChange, pointerInputChange2, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-0AR0LA0, reason: not valid java name */
                        public final void m786invoke0AR0LA0(PointerInputChange down, PointerInputChange slopTriggerChange, long postSlopOffset) {
                            if (dragGestureNode.getCanDrag().invoke(down).booleanValue()) {
                                if (!dragGestureNode.isListeningForEvents) {
                                    if (dragGestureNode.channel == null) {
                                        dragGestureNode.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
                                    }
                                    dragGestureNode.startListeningForEvents();
                                }
                                VelocityTrackerKt.addPointerInputChange(velocityTracker, down);
                                long dragStartedOffset = Offset.m4297minusMKHz9U(slopTriggerChange.getPosition(), postSlopOffset);
                                Channel channel = dragGestureNode.channel;
                                if (channel != null) {
                                    ChannelResult.m8908boximpl(channel.mo8893trySendJP2dKIU(new DragEvent.DragStarted(dragStartedOffset, null)));
                                }
                            }
                        }
                    };
                    final DragGestureNode dragGestureNode2 = DragGestureNode.this;
                    Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragEnd$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                            invoke2(pointerInputChange);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(PointerInputChange upEvent) {
                            VelocityTrackerKt.addPointerInputChange(velocityTracker, upEvent);
                            float maximumVelocity = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(dragGestureNode2, CompositionLocalsKt.getLocalViewConfiguration())).getMaximumFlingVelocity();
                            long velocity = velocityTracker.m5841calculateVelocityAH228Gc(VelocityKt.Velocity(maximumVelocity, maximumVelocity));
                            velocityTracker.resetTracking();
                            Channel channel = dragGestureNode2.channel;
                            if (channel != null) {
                                ChannelResult.m8908boximpl(channel.mo8893trySendJP2dKIU(new DragEvent.DragStopped(DraggableKt.m801toValidVelocityTH1AsA0(velocity), null)));
                            }
                        }
                    };
                    final DragGestureNode dragGestureNode3 = DragGestureNode.this;
                    Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragCancel$1
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
                            Channel channel = dragGestureNode3.channel;
                            if (channel != null) {
                                ChannelResult.m8908boximpl(channel.mo8893trySendJP2dKIU(DragEvent.DragCancelled.INSTANCE));
                            }
                        }
                    };
                    final DragGestureNode dragGestureNode4 = DragGestureNode.this;
                    Function0<Boolean> function2 = new Function0<Boolean>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$shouldAwaitTouchSlop$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return Boolean.valueOf(!dragGestureNode4.getStartDragImmediately());
                        }
                    };
                    final DragGestureNode dragGestureNode5 = DragGestureNode.this;
                    Function2<PointerInputChange, Offset, Unit> function4 = new Function2<PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDrag$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                            m785invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m785invokeUv8p0NA(PointerInputChange change, long delta) {
                            VelocityTrackerKt.addPointerInputChange(velocityTracker, change);
                            Channel channel = dragGestureNode5.channel;
                            if (channel != null) {
                                ChannelResult.m8908boximpl(channel.mo8893trySendJP2dKIU(new DragEvent.DragDelta(delta, null)));
                            }
                        }
                    };
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(new C00251(DragGestureNode.this, $this$SuspendingPointerInputModifierNode, function3, function1, function0, function2, function4, null), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Draggable.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$1", f = "Draggable.kt", i = {0}, l = {526}, m = "invokeSuspend", n = {"$this$coroutineScope"}, s = {"L$0"})
        static final class C00251 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $$this$SuspendingPointerInputModifierNode;
            final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
            final /* synthetic */ Function0<Unit> $onDragCancel;
            final /* synthetic */ Function1<PointerInputChange, Unit> $onDragEnd;
            final /* synthetic */ Function3<PointerInputChange, PointerInputChange, Offset, Unit> $onDragStart;
            final /* synthetic */ Function0<Boolean> $shouldAwaitTouchSlop;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ DragGestureNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00251(DragGestureNode dragGestureNode, PointerInputScope pointerInputScope, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function1<? super PointerInputChange, Unit> function1, Function0<Unit> function0, Function0<Boolean> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function4, Continuation<? super C00251> continuation) {
                super(2, continuation);
                this.this$0 = dragGestureNode;
                this.$$this$SuspendingPointerInputModifierNode = pointerInputScope;
                this.$onDragStart = function3;
                this.$onDragEnd = function1;
                this.$onDragCancel = function0;
                this.$shouldAwaitTouchSlop = function2;
                this.$onDrag = function4;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00251 c00251 = new C00251(this.this$0, this.$$this$SuspendingPointerInputModifierNode, this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$shouldAwaitTouchSlop, this.$onDrag, continuation);
                c00251.L$0 = obj;
                return c00251;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00251) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:21:0x0058  */
            /* JADX WARN: Code duplicated, block: B:26:0x006a  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                CancellationException cancellation;
                C00251 c00251;
                CoroutineScope $this$coroutineScope;
                Channel channel;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$coroutineScope2 = (CoroutineScope) this.L$0;
                        try {
                            Orientation orientation = this.this$0.orientationLock;
                            this.L$0 = $this$coroutineScope2;
                            this.label = 1;
                            if (DragGestureDetectorKt.detectDragGestures(this.$$this$SuspendingPointerInputModifierNode, this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$shouldAwaitTouchSlop, orientation, this.$onDrag, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        } catch (CancellationException e) {
                            cancellation = e;
                            c00251 = this;
                            $this$coroutineScope = $this$coroutineScope2;
                            channel = c00251.this$0.channel;
                            if (channel != null) {
                                ChannelResult.m8908boximpl(channel.mo8893trySendJP2dKIU(DragEvent.DragCancelled.INSTANCE));
                            }
                            if (!CoroutineScopeKt.isActive($this$coroutineScope)) {
                                throw cancellation;
                            }
                        }
                    case 1:
                        c00251 = this;
                        $this$coroutineScope = (CoroutineScope) c00251.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            break;
                        } catch (CancellationException e2) {
                            cancellation = e2;
                            channel = c00251.this$0.channel;
                            if (channel != null) {
                                ChannelResult.m8908boximpl(channel.mo8893trySendJP2dKIU(DragEvent.DragCancelled.INSTANCE));
                            }
                            if (!CoroutineScopeKt.isActive($this$coroutineScope)) {
                                throw cancellation;
                            }
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    private final SuspendingPointerInputModifierNode initializePointerInputNode() {
        return SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AnonymousClass1(null));
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.onCancelPointerInput();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:25:0x007a  */
    /* JADX WARN: Code duplicated, block: B:27:0x008c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x008d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object processDragStart(DragEvent.DragStarted dragStarted, Continuation<? super Unit> continuation) {
        C03261 c03261;
        DragGestureNode dragGestureNode;
        DragEvent.DragStarted event;
        MutableInteractionSource mutableInteractionSource;
        DragInteraction.Start interaction;
        MutableInteractionSource mutableInteractionSource2;
        DragEvent.DragStarted event2;
        DragGestureNode dragGestureNode2;
        if (continuation instanceof C03261) {
            c03261 = (C03261) continuation;
            if ((c03261.label & Integer.MIN_VALUE) != 0) {
                c03261.label -= Integer.MIN_VALUE;
            } else {
                c03261 = new C03261(continuation);
            }
        } else {
            c03261 = new C03261(continuation);
        }
        C03261 c03262 = c03261;
        Object $result = c03262.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c03262.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                dragGestureNode = this;
                event = dragStarted;
                DragInteraction.Start oldInteraction = dragGestureNode.dragInteraction;
                if (oldInteraction != null && (mutableInteractionSource = dragGestureNode.interactionSource) != null) {
                    DragInteraction.Cancel cancel = new DragInteraction.Cancel(oldInteraction);
                    c03262.L$0 = dragGestureNode;
                    c03262.L$1 = event;
                    c03262.label = 1;
                    if (mutableInteractionSource.emit(cancel, c03262) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                interaction = new DragInteraction.Start();
                mutableInteractionSource2 = dragGestureNode.interactionSource;
                if (mutableInteractionSource2 != null) {
                    c03262.L$0 = dragGestureNode;
                    c03262.L$1 = event;
                    c03262.L$2 = interaction;
                    c03262.label = 2;
                    if (mutableInteractionSource2.emit(interaction, c03262) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    event2 = event;
                    dragGestureNode2 = dragGestureNode;
                    dragGestureNode = dragGestureNode2;
                    event = event2;
                }
                dragGestureNode.dragInteraction = interaction;
                dragGestureNode.mo743onDragStartedk4lQ0M(event.getStartPoint());
                return Unit.INSTANCE;
            case 1:
                event = (DragEvent.DragStarted) c03262.L$1;
                dragGestureNode = (DragGestureNode) c03262.L$0;
                ResultKt.throwOnFailure($result);
                interaction = new DragInteraction.Start();
                mutableInteractionSource2 = dragGestureNode.interactionSource;
                if (mutableInteractionSource2 != null) {
                    c03262.L$0 = dragGestureNode;
                    c03262.L$1 = event;
                    c03262.L$2 = interaction;
                    c03262.label = 2;
                    if (mutableInteractionSource2.emit(interaction, c03262) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    event2 = event;
                    dragGestureNode2 = dragGestureNode;
                    dragGestureNode = dragGestureNode2;
                    event = event2;
                }
                dragGestureNode.dragInteraction = interaction;
                dragGestureNode.mo743onDragStartedk4lQ0M(event.getStartPoint());
                return Unit.INSTANCE;
            case 2:
                interaction = (DragInteraction.Start) c03262.L$2;
                event2 = (DragEvent.DragStarted) c03262.L$1;
                dragGestureNode2 = (DragGestureNode) c03262.L$0;
                ResultKt.throwOnFailure($result);
                dragGestureNode = dragGestureNode2;
                event = event2;
                dragGestureNode.dragInteraction = interaction;
                dragGestureNode.mo743onDragStartedk4lQ0M(event.getStartPoint());
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object processDragStop(DragEvent.DragStopped event, Continuation<? super Unit> continuation) {
        C03271 c03271;
        DragGestureNode dragGestureNode;
        DragEvent.DragStopped event2;
        DragEvent.DragStopped event3;
        if (continuation instanceof C03271) {
            c03271 = (C03271) continuation;
            if ((c03271.label & Integer.MIN_VALUE) != 0) {
                c03271.label -= Integer.MIN_VALUE;
            } else {
                c03271 = new C03271(continuation);
            }
        } else {
            c03271 = new C03271(continuation);
        }
        C03271 c03272 = c03271;
        Object $result = c03272.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c03272.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                dragGestureNode = this;
                DragInteraction.Start interaction = dragGestureNode.dragInteraction;
                if (interaction != null) {
                    MutableInteractionSource mutableInteractionSource = dragGestureNode.interactionSource;
                    if (mutableInteractionSource != null) {
                        DragInteraction.Stop stop = new DragInteraction.Stop(interaction);
                        c03272.L$0 = dragGestureNode;
                        c03272.L$1 = event;
                        c03272.label = 1;
                        if (mutableInteractionSource.emit(stop, c03272) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        event2 = event;
                        event3 = null;
                        event = event2;
                    }
                    dragGestureNode.dragInteraction = null;
                }
                dragGestureNode.mo744onDragStoppedTH1AsA0(event.getVelocity());
                return Unit.INSTANCE;
            case 1:
                event3 = null;
                event2 = (DragEvent.DragStopped) c03272.L$1;
                dragGestureNode = (DragGestureNode) c03272.L$0;
                ResultKt.throwOnFailure($result);
                event = event2;
                dragGestureNode.dragInteraction = null;
                dragGestureNode.mo744onDragStoppedTH1AsA0(event.getVelocity());
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object processDragCancel(Continuation<? super Unit> continuation) {
        C03251 c03251;
        DragGestureNode dragGestureNode;
        int i;
        if (continuation instanceof C03251) {
            c03251 = (C03251) continuation;
            if ((c03251.label & Integer.MIN_VALUE) != 0) {
                c03251.label -= Integer.MIN_VALUE;
            } else {
                c03251 = new C03251(continuation);
            }
        } else {
            c03251 = new C03251(continuation);
        }
        C03251 c03252 = c03251;
        Object $result = c03252.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c03252.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                dragGestureNode = this;
                DragInteraction.Start interaction = dragGestureNode.dragInteraction;
                if (interaction != null) {
                    MutableInteractionSource mutableInteractionSource = dragGestureNode.interactionSource;
                    if (mutableInteractionSource != null) {
                        DragInteraction.Cancel cancel = new DragInteraction.Cancel(interaction);
                        c03252.L$0 = dragGestureNode;
                        c03252.label = 1;
                        if (mutableInteractionSource.emit(cancel, c03252) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i = 0;
                    }
                    dragGestureNode.dragInteraction = null;
                }
                dragGestureNode.mo744onDragStoppedTH1AsA0(Velocity.INSTANCE.m7264getZero9UxMQ8M());
                return Unit.INSTANCE;
            case 1:
                i = 0;
                dragGestureNode = (DragGestureNode) c03252.L$0;
                ResultKt.throwOnFailure($result);
                dragGestureNode.dragInteraction = null;
                dragGestureNode.mo744onDragStoppedTH1AsA0(Velocity.INSTANCE.m7264getZero9UxMQ8M());
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void disposeInteractionSource() {
        DragInteraction.Start interaction = this.dragInteraction;
        if (interaction != null) {
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                mutableInteractionSource.tryEmit(new DragInteraction.Cancel(interaction));
            }
            this.dragInteraction = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void update$default(DragGestureNode dragGestureNode, Function1 function1, boolean z, MutableInteractionSource mutableInteractionSource, Orientation orientation, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: update");
        }
        if ((i & 1) != 0) {
            function1 = dragGestureNode.canDrag;
        }
        dragGestureNode.update(function1, (i & 2) != 0 ? dragGestureNode.enabled : z, (i & 4) != 0 ? dragGestureNode.interactionSource : mutableInteractionSource, (i & 8) != 0 ? dragGestureNode.orientationLock : orientation, (i & 16) != 0 ? false : z2);
    }

    public final void update(Function1<? super PointerInputChange, Boolean> canDrag, boolean enabled, MutableInteractionSource interactionSource, Orientation orientationLock, boolean shouldResetPointerInputHandling) {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode;
        boolean resetPointerInputHandling = shouldResetPointerInputHandling;
        this.canDrag = canDrag;
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (!enabled) {
                disposeInteractionSource();
                SuspendingPointerInputModifierNode it = this.pointerInputNode;
                if (it != null) {
                    undelegate(it);
                }
                this.pointerInputNode = null;
            }
            resetPointerInputHandling = true;
        }
        if (!Intrinsics.areEqual(this.interactionSource, interactionSource)) {
            disposeInteractionSource();
            this.interactionSource = interactionSource;
        }
        if (this.orientationLock != orientationLock) {
            this.orientationLock = orientationLock;
            resetPointerInputHandling = true;
        }
        if (!resetPointerInputHandling || (suspendingPointerInputModifierNode = this.pointerInputNode) == null) {
            return;
        }
        suspendingPointerInputModifierNode.resetPointerInputHandler();
    }
}
