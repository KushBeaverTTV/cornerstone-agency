package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.core.view.MotionEventCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ContextMenuGestures.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0082@¢\u0006\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a&\u0010\b\u001a\u00020\t*\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\fH\u0081@¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"awaitFirstRightClickDown", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "contextMenuGestures", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "onRightClickDown", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDown", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContextMenuGestures_androidKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$awaitFirstRightClickDown$1, reason: invalid class name */
    /* JADX INFO: compiled from: ContextMenuGestures.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt", f = "ContextMenuGestures.android.kt", i = {0}, l = {66}, m = "awaitFirstRightClickDown", n = {"$this$awaitFirstRightClickDown"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ContextMenuGestures_androidKt.awaitFirstRightClickDown(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$contextMenuGestures$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ContextMenuGestures.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$contextMenuGestures$1", f = "ContextMenuGestures.android.kt", i = {}, l = {MotionEventCompat.AXIS_GENERIC_13}, m = "invokeSuspend", n = {}, s = {})
    static final class C03051 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ContextMenuState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03051(ContextMenuState contextMenuState, Continuation<? super C03051> continuation) {
            super(2, continuation);
            this.$state = contextMenuState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03051 c03051 = new C03051(this.$state, continuation);
            c03051.L$0 = obj;
            return c03051;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C03051) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    final ContextMenuState contextMenuState = this.$state;
                    this.label = 1;
                    if (ContextMenuGestures_androidKt.onRightClickDown($this$pointerInput, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt.contextMenuGestures.1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                            m697invokek4lQ0M(offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                        public final void m697invokek4lQ0M(long it) {
                            contextMenuState.setStatus(new ContextMenuState.Status.Open(it, null));
                        }
                    }, this) == coroutine_suspended) {
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

    public static final Modifier contextMenuGestures(Modifier $this$contextMenuGestures, ContextMenuState state) {
        return SuspendingPointerInputFilterKt.pointerInput($this$contextMenuGestures, ContextMenuKey.INSTANCE, new C03051(state, null));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$onRightClickDown$2, reason: invalid class name */
    /* JADX INFO: compiled from: ContextMenuGestures.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$onRightClickDown$2", f = "ContextMenuGestures.android.kt", i = {0}, l = {53, 56}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
    static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Offset, Unit> $onDown;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function1<? super Offset, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$onDown = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$onDown, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0061 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:15:0x0062  */
        /* JADX WARN: Code duplicated, block: B:18:0x0067  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AwaitPointerEventScope $this$awaitEachGesture;
            AnonymousClass2 anonymousClass2;
            PointerInputChange pointerInputChange;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = $this$awaitEachGesture2;
                    this.label = 1;
                    Object objAwaitFirstRightClickDown = ContextMenuGestures_androidKt.awaitFirstRightClickDown($this$awaitEachGesture2, this);
                    if (objAwaitFirstRightClickDown == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objAwaitFirstRightClickDown;
                    $this$awaitEachGesture = $this$awaitEachGesture2;
                    anonymousClass2 = this;
                    PointerInputChange down = (PointerInputChange) $result;
                    down.consume();
                    anonymousClass2.$onDown.invoke(Offset.m4282boximpl(down.getPosition()));
                    anonymousClass2.L$0 = null;
                    anonymousClass2.label = 2;
                    $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, anonymousClass2, 1, null);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pointerInputChange = (PointerInputChange) $result;
                    if (pointerInputChange != null) {
                        pointerInputChange.consume();
                    }
                    return Unit.INSTANCE;
                case 1:
                    AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    $this$awaitEachGesture = $this$awaitEachGesture3;
                    anonymousClass2 = this;
                    PointerInputChange down2 = (PointerInputChange) $result;
                    down2.consume();
                    anonymousClass2.$onDown.invoke(Offset.m4282boximpl(down2.getPosition()));
                    anonymousClass2.L$0 = null;
                    anonymousClass2.label = 2;
                    $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, anonymousClass2, 1, null);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pointerInputChange = (PointerInputChange) $result;
                    if (pointerInputChange != null) {
                        pointerInputChange.consume();
                    }
                    return Unit.INSTANCE;
                case 2:
                    ResultKt.throwOnFailure($result);
                    pointerInputChange = (PointerInputChange) $result;
                    if (pointerInputChange != null) {
                        pointerInputChange.consume();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final Object onRightClickDown(PointerInputScope $this$onRightClickDown, Function1<? super Offset, Unit> function1, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$onRightClickDown, new AnonymousClass2(function1, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x0072  */
    /* JADX WARN: Code duplicated, block: B:26:0x0085 A[LOOP:0: B:21:0x006f->B:26:0x0085, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:33:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004e -> B:18:0x0055). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitFirstRightClickDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt.AnonymousClass1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$awaitFirstRightClickDown$1 r1 = (androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$awaitFirstRightClickDown$1 r1 = new androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$awaitFirstRightClickDown$1
            r1.<init>(r0)
        L1b:
            r0 = r1
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            switch(r3) {
                case 0: goto L3c;
                case 1: goto L31;
                default: goto L28;
            }
        L28:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L31:
            java.lang.Object r3 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
            kotlin.ResultKt.throwOnFailure(r1)
            r5 = r3
            r3 = r2
            r2 = r1
            goto L55
        L3c:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r18
        L41:
            r0.L$0 = r3
            r0.label = r4
            r5 = 0
            java.lang.Object r5 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.awaitPointerEvent$default(r3, r5, r0, r4, r5)
            if (r5 != r2) goto L4e
            return r2
        L4e:
            r17 = r2
            r2 = r1
            r1 = r5
            r5 = r3
            r3 = r17
        L55:
            androidx.compose.ui.input.pointer.PointerEvent r1 = (androidx.compose.ui.input.pointer.PointerEvent) r1
            int r6 = r1.getButtons()
            boolean r6 = androidx.compose.ui.input.pointer.PointerEvent_androidKt.m5716isSecondaryPressedaHzCxE(r6)
            if (r6 == 0) goto L96
            java.util.List r6 = r1.getChanges()
            r7 = 0
            r8 = 0
            r9 = 0
            int r10 = r6.size()
        L6f:
            r11 = 0
            if (r9 >= r10) goto L89
            java.lang.Object r12 = r6.get(r9)
            r13 = r12
            r14 = 0
            r15 = r13
            androidx.compose.ui.input.pointer.PointerInputChange r15 = (androidx.compose.ui.input.pointer.PointerInputChange) r15
            r16 = 0
            boolean r15 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDown(r15)
            if (r15 != 0) goto L85
            r6 = r11
            goto L8b
        L85:
            int r9 = r9 + 1
            goto L6f
        L89:
            r6 = r4
        L8b:
            if (r6 == 0) goto L96
            java.util.List r3 = r1.getChanges()
            java.lang.Object r3 = r3.get(r11)
            return r3
        L96:
            r1 = r2
            r2 = r3
            r3 = r5
            goto L41
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt.awaitFirstRightClickDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
