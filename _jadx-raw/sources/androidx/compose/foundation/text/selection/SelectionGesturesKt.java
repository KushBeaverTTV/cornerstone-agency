package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.core.location.LocationRequestCompat;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SelectionGestures.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u0012\u0010\f\u001a\u00020\u0004*\u00020\rH\u0082@¢\u0006\u0002\u0010\u000e\u001a*\u0010\u000f\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0016\u001a*\u0010\u0017\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0016\u001a\u001c\u0010\u0018\u001a\u00020\u0019*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0000\u001a\"\u0010\u001d\u001a\u00020\u0010*\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0080@¢\u0006\u0002\u0010\u001f\u001a\"\u0010 \u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a\"\u0010\"\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a\"\u0010$\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a \u0010%\u001a\u00020\u0019*\u00020\u00192\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100'H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005¨\u0006("}, d2 = {"STATIC_KEY", "", "isPrecisePointer", "", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "distanceIsTolerable", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "change1", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "change2", "awaitDown", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelection", "", "observer", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "clicksCounter", "Landroidx/compose/foundation/text/selection/ClicksCounter;", "down", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/selection/ClicksCounter;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelectionBtf2", "selectionGestureInput", "Landroidx/compose/ui/Modifier;", "mouseSelectionObserver", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "selectionGesturePointerInputBtf2", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelection", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelectionFirstPress", "downEvent", "touchSelectionSubsequentPress", "updateSelectionTouchMode", "updateTouchMode", "Lkotlin/Function1;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionGesturesKt {
    private static final int STATIC_KEY = 8675309;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0}, l = {425}, m = "awaitDown", n = {"$this$awaitDown"}, s = {"L$0"})
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
            return SelectionGesturesKt.awaitDown(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1}, l = {158, 181}, m = "mouseSelection", n = {"$this$mouseSelection", "observer", "$this$mouseSelection", "observer"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C04451 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C04451(Continuation<? super C04451> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelection(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelectionBtf2$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1}, l = {351, 377}, m = "mouseSelectionBtf2", n = {"$this$mouseSelectionBtf2", "observer", "$this$mouseSelectionBtf2", "observer"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C04461 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C04461(Continuation<? super C04461> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelectionBtf2(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 1, 1}, l = {124, 128}, m = "touchSelection", n = {"$this$touchSelection", "observer", "firstDown", "$this$touchSelection", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class C04481 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04481(Continuation<? super C04481> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelection(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionFirstPress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 1, 1}, l = {238, 241}, m = "touchSelectionFirstPress", n = {"$this$touchSelectionFirstPress", "observer", "firstDown", "$this$touchSelectionFirstPress", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class C04501 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04501(Continuation<? super C04501> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionFirstPress(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 0, 0, 1, 1}, l = {276, 315}, m = "touchSelectionSubsequentPress", n = {"$this$touchSelectionSubsequentPress", "observer", "firstDown", "overSlop", "pointerId", "$this$touchSelectionSubsequentPress", "observer"}, s = {"L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1"})
    static final class C04511 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C04511(Continuation<? super C04511> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionSubsequentPress(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1", f = "SelectionGestures.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
    static final class C04521 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04521(Function1<? super Boolean, Unit> function1, Continuation<? super C04521> continuation) {
            super(2, continuation);
            this.$updateTouchMode = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04521 c04521 = new C04521(this.$updateTouchMode, continuation);
            c04521.L$0 = obj;
            return c04521;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C04521) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SelectionGestures.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1", f = "SelectionGestures.kt", i = {0}, l = {93}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
        static final class C00671 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00671(Function1<? super Boolean, Unit> function1, Continuation<? super C00671> continuation) {
                super(2, continuation);
                this.$updateTouchMode = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00671 c00671 = new C00671(this.$updateTouchMode, continuation);
                c00671.L$0 = obj;
                return c00671;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00671) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:10:0x0038 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:11:0x0039  */
            /* JADX WARN: Code duplicated, block: B:14:0x0049  */
            /* JADX WARN: Code duplicated, block: B:15:0x004b  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0039 -> B:12:0x003f). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:15:0x004b
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                /*
                    r8 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    r2 = 1
                    switch(r1) {
                        case 0: goto L20;
                        case 1: goto L13;
                        default: goto La;
                    }
                La:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                L13:
                    r1 = r8
                    java.lang.Object r3 = r1.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
                    kotlin.ResultKt.throwOnFailure(r9)
                    r4 = r3
                    r3 = r1
                    r1 = r0
                    r0 = r9
                    goto L3f
                L20:
                    kotlin.ResultKt.throwOnFailure(r9)
                    r1 = r8
                    java.lang.Object r3 = r1.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
                L28:
                    androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r5 = r1
                    kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                    r1.L$0 = r3
                    r1.label = r2
                    java.lang.Object r4 = r3.awaitPointerEvent(r4, r5)
                    if (r4 != r0) goto L39
                    return r0
                L39:
                    r7 = r0
                    r0 = r9
                    r9 = r4
                    r4 = r3
                    r3 = r1
                    r1 = r7
                L3f:
                    androidx.compose.ui.input.pointer.PointerEvent r9 = (androidx.compose.ui.input.pointer.PointerEvent) r9
                    kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r5 = r3.$updateTouchMode
                    boolean r6 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r9)
                    if (r6 != 0) goto L4b
                    r9 = r2
                    goto L4c
                L4b:
                    r9 = 0
                L4c:
                    java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
                    r5.invoke(r9)
                    r9 = r0
                    r0 = r1
                    r1 = r3
                    r3 = r4
                    goto L28
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C04521.C00671.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    this.label = 1;
                    if ($this$pointerInput.awaitPointerEventScope(new C00671(this.$updateTouchMode, null), this) == coroutine_suspended) {
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

    public static final Modifier updateSelectionTouchMode(Modifier $this$updateSelectionTouchMode, Function1<? super Boolean, Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput($this$updateSelectionTouchMode, Integer.valueOf(STATIC_KEY), new C04521(function1, null));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1", f = "SelectionGestures.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER}, m = "invokeSuspend", n = {}, s = {})
    static final class C04471 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04471(MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super C04471> continuation) {
            super(2, continuation);
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04471 c04471 = new C04471(this.$mouseSelectionObserver, this.$textDragObserver, continuation);
            c04471.L$0 = obj;
            return c04471;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C04471) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    ClicksCounter clicksCounter = new ClicksCounter($this$pointerInput.getViewConfiguration());
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture($this$pointerInput, new C00661(this.$mouseSelectionObserver, clicksCounter, this.$textDragObserver, null), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SelectionGestures.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1", f = "SelectionGestures.kt", i = {0}, l = {105, 111, 113}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
        static final class C00661 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ClicksCounter $clicksCounter;
            final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
            final /* synthetic */ TextDragObserver $textDragObserver;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00661(MouseSelectionObserver mouseSelectionObserver, ClicksCounter clicksCounter, TextDragObserver textDragObserver, Continuation<? super C00661> continuation) {
                super(2, continuation);
                this.$mouseSelectionObserver = mouseSelectionObserver;
                this.$clicksCounter = clicksCounter;
                this.$textDragObserver = textDragObserver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00661 c00661 = new C00661(this.$mouseSelectionObserver, this.$clicksCounter, this.$textDragObserver, continuation);
                c00661.L$0 = obj;
                return c00661;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00661) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:18:0x0071  */
            /* JADX WARN: Code duplicated, block: B:20:0x0082  */
            /* JADX WARN: Code duplicated, block: B:21:0x0084  */
            /* JADX WARN: Code duplicated, block: B:24:0x008b A[LOOP:0: B:17:0x006f->B:24:0x008b, LOOP_END] */
            /* JADX WARN: Code duplicated, block: B:27:0x0093  */
            /* JADX WARN: Code duplicated, block: B:29:0x00a5 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:30:0x00a6  */
            /* JADX WARN: Code duplicated, block: B:34:0x00b1  */
            /* JADX WARN: Code duplicated, block: B:36:0x00c1 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:37:0x00c2  */
            /* JADX WARN: Code duplicated, block: B:42:0x0088 A[SYNTHETIC] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                C00661 c00661;
                Object $result;
                AwaitPointerEventScope $this$awaitEachGesture;
                Object objAwaitDown;
                PointerEvent down;
                C00661 c00662;
                Object $result2;
                List<PointerInputChange> changes;
                int index$iv$iv;
                int size;
                C00661 c00663;
                Object $result3;
                PointerInputChange it;
                boolean z;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                boolean z2 = true;
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        c00661 = this;
                        $result = obj;
                        $this$awaitEachGesture = (AwaitPointerEventScope) c00661.L$0;
                        c00661.L$0 = $this$awaitEachGesture;
                        c00661.label = 1;
                        objAwaitDown = SelectionGesturesKt.awaitDown($this$awaitEachGesture, c00661);
                        if (objAwaitDown == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        down = (PointerEvent) objAwaitDown;
                        if (SelectionGesturesKt.isPrecisePointer(down) && PointerEvent_androidKt.m5714isPrimaryPressedaHzCxE(down.getButtons())) {
                            changes = down.getChanges();
                            size = changes.size();
                            for (index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                                Object it$iv = changes.get(index$iv$iv);
                                it = (PointerInputChange) it$iv;
                                if (it.isConsumed()) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                if (!z) {
                                    z2 = false;
                                    if (z2) {
                                        c00661.L$0 = null;
                                        c00661.label = 2;
                                        if (SelectionGesturesKt.mouseSelection($this$awaitEachGesture, c00661.$mouseSelectionObserver, c00661.$clicksCounter, down, c00661) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        c00663 = c00661;
                                        $result3 = $result;
                                    }
                                    return Unit.INSTANCE;
                                }
                            }
                            if (z2) {
                                c00661.L$0 = null;
                                c00661.label = 2;
                                if (SelectionGesturesKt.mouseSelection($this$awaitEachGesture, c00661.$mouseSelectionObserver, c00661.$clicksCounter, down, c00661) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                c00663 = c00661;
                                $result3 = $result;
                            }
                            return Unit.INSTANCE;
                        }
                        if (!SelectionGesturesKt.isPrecisePointer(down)) {
                            c00661.L$0 = null;
                            c00661.label = 3;
                            if (SelectionGesturesKt.touchSelection($this$awaitEachGesture, c00661.$textDragObserver, down, c00661) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            c00662 = c00661;
                            $result2 = $result;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        c00661 = this;
                        $result = obj;
                        $this$awaitEachGesture = (AwaitPointerEventScope) c00661.L$0;
                        ResultKt.throwOnFailure($result);
                        objAwaitDown = $result;
                        down = (PointerEvent) objAwaitDown;
                        if (SelectionGesturesKt.isPrecisePointer(down)) {
                            changes = down.getChanges();
                            size = changes.size();
                            while (index$iv$iv < size) {
                                Object it$iv2 = changes.get(index$iv$iv);
                                it = (PointerInputChange) it$iv2;
                                if (it.isConsumed()) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (!z) {
                                    z2 = false;
                                    if (z2) {
                                        c00661.L$0 = null;
                                        c00661.label = 2;
                                        if (SelectionGesturesKt.mouseSelection($this$awaitEachGesture, c00661.$mouseSelectionObserver, c00661.$clicksCounter, down, c00661) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        c00663 = c00661;
                                        $result3 = $result;
                                    }
                                    return Unit.INSTANCE;
                                }
                            }
                            if (z2) {
                                c00661.L$0 = null;
                                c00661.label = 2;
                                if (SelectionGesturesKt.mouseSelection($this$awaitEachGesture, c00661.$mouseSelectionObserver, c00661.$clicksCounter, down, c00661) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                c00663 = c00661;
                                $result3 = $result;
                            }
                            return Unit.INSTANCE;
                        }
                        if (!SelectionGesturesKt.isPrecisePointer(down)) {
                            c00661.L$0 = null;
                            c00661.label = 3;
                            if (SelectionGesturesKt.touchSelection($this$awaitEachGesture, c00661.$textDragObserver, down, c00661) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            c00662 = c00661;
                            $result2 = $result;
                        }
                        return Unit.INSTANCE;
                    case 2:
                        c00663 = this;
                        $result3 = obj;
                        ResultKt.throwOnFailure($result3);
                        return Unit.INSTANCE;
                    case 3:
                        c00662 = this;
                        $result2 = obj;
                        ResultKt.throwOnFailure($result2);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public static final Modifier selectionGestureInput(Modifier $this$selectionGestureInput, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver) {
        return SuspendingPointerInputFilterKt.pointerInput($this$selectionGestureInput, mouseSelectionObserver, textDragObserver, new C04471(mouseSelectionObserver, textDragObserver, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:31:0x00a9 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:0x00b2 A[Catch: CancellationException -> 0x00e5, TryCatch #1 {CancellationException -> 0x00e5, blocks: (B:32:0x00aa, B:34:0x00b2, B:36:0x00c3, B:38:0x00d2, B:39:0x00d5, B:40:0x00da, B:41:0x00df, B:18:0x004d, B:25:0x0078, B:27:0x007c, B:29:0x0086, B:21:0x0056), top: B:50:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00c3 A[Catch: CancellationException -> 0x00e5, TryCatch #1 {CancellationException -> 0x00e5, blocks: (B:32:0x00aa, B:34:0x00b2, B:36:0x00c3, B:38:0x00d2, B:39:0x00d5, B:40:0x00da, B:41:0x00df, B:18:0x004d, B:25:0x0078, B:27:0x007c, B:29:0x0086, B:21:0x0056), top: B:50:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00d2 A[Catch: CancellationException -> 0x00e5, TryCatch #1 {CancellationException -> 0x00e5, blocks: (B:32:0x00aa, B:34:0x00b2, B:36:0x00c3, B:38:0x00d2, B:39:0x00d5, B:40:0x00da, B:41:0x00df, B:18:0x004d, B:25:0x0078, B:27:0x007c, B:29:0x0086, B:21:0x0056), top: B:50:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00df A[Catch: CancellationException -> 0x00e5, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x00e5, blocks: (B:32:0x00aa, B:34:0x00b2, B:36:0x00c3, B:38:0x00d2, B:39:0x00d5, B:40:0x00da, B:41:0x00df, B:18:0x004d, B:25:0x0078, B:27:0x007c, B:29:0x0086, B:21:0x0056), top: B:50:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00d5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object touchSelection(AwaitPointerEventScope $this$touchSelection, final TextDragObserver observer, PointerEvent down, Continuation<? super Unit> continuation) {
        C04481 c04481;
        CancellationException c;
        TextDragObserver observer2;
        Object objM767awaitLongPressOrCancellationrnUCldI;
        AwaitPointerEventScope $this$touchSelection2;
        PointerInputChange firstDown;
        PointerInputChange drag;
        Object objM775dragjO51t88;
        List<PointerInputChange> changes;
        int index$iv;
        int size;
        PointerInputChange it;
        if (continuation instanceof C04481) {
            c04481 = (C04481) continuation;
            if ((c04481.label & Integer.MIN_VALUE) != 0) {
                c04481.label -= Integer.MIN_VALUE;
            } else {
                c04481 = new C04481(continuation);
            }
        } else {
            c04481 = new C04481(continuation);
        }
        C04481 c04482 = c04481;
        Object $result = c04482.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c04482.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputChange firstDown2 = (PointerInputChange) CollectionsKt.first((List) down.getChanges());
                    long id = firstDown2.getId();
                    c04482.L$0 = $this$touchSelection;
                    c04482.L$1 = observer;
                    c04482.L$2 = firstDown2;
                    c04482.label = 1;
                    objM767awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m767awaitLongPressOrCancellationrnUCldI($this$touchSelection, id, c04482);
                    if (objM767awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$touchSelection2 = $this$touchSelection;
                    firstDown = firstDown2;
                    drag = (PointerInputChange) objM767awaitLongPressOrCancellationrnUCldI;
                    if (drag != null && distanceIsTolerable($this$touchSelection2.getViewConfiguration(), firstDown, drag)) {
                        observer.mo1413onStartk4lQ0M(drag.getPosition());
                        long id2 = drag.getId();
                        Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelection.2
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it2) {
                                observer.mo1412onDragk4lQ0M(PointerEventKt.positionChange(it2));
                                it2.consume();
                            }
                        };
                        c04482.L$0 = $this$touchSelection2;
                        c04482.L$1 = observer;
                        c04482.L$2 = null;
                        c04482.label = 2;
                        objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88($this$touchSelection2, id2, function1, c04482);
                        if (objM775dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) objM775dragjO51t88).booleanValue()) {
                            changes = $this$touchSelection2.getCurrentEvent().getChanges();
                            size = changes.size();
                            for (index$iv = 0; index$iv < size; index$iv++) {
                                Object item$iv = changes.get(index$iv);
                                it = (PointerInputChange) item$iv;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            observer.onStop();
                        } else {
                            observer.onCancel();
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    firstDown = (PointerInputChange) c04482.L$2;
                    observer = (TextDragObserver) c04482.L$1;
                    $this$touchSelection2 = (AwaitPointerEventScope) c04482.L$0;
                    ResultKt.throwOnFailure($result);
                    objM767awaitLongPressOrCancellationrnUCldI = $result;
                    drag = (PointerInputChange) objM767awaitLongPressOrCancellationrnUCldI;
                    if (drag != null) {
                        observer.mo1413onStartk4lQ0M(drag.getPosition());
                        long id3 = drag.getId();
                        Function1<PointerInputChange, Unit> function2 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelection.2
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it2) {
                                observer.mo1412onDragk4lQ0M(PointerEventKt.positionChange(it2));
                                it2.consume();
                            }
                        };
                        c04482.L$0 = $this$touchSelection2;
                        c04482.L$1 = observer;
                        c04482.L$2 = null;
                        c04482.label = 2;
                        objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88($this$touchSelection2, id3, function2, c04482);
                        if (objM775dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) objM775dragjO51t88).booleanValue()) {
                            changes = $this$touchSelection2.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (index$iv < size) {
                                Object item$iv2 = changes.get(index$iv);
                                it = (PointerInputChange) item$iv2;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            observer.onStop();
                        } else {
                            observer.onCancel();
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    observer2 = (TextDragObserver) c04482.L$1;
                    AwaitPointerEventScope $this$touchSelection3 = (AwaitPointerEventScope) c04482.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        $this$touchSelection2 = $this$touchSelection3;
                        observer = observer2;
                        objM775dragjO51t88 = $result;
                        if (((Boolean) objM775dragjO51t88).booleanValue()) {
                            changes = $this$touchSelection2.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (index$iv < size) {
                                Object item$iv3 = changes.get(index$iv);
                                it = (PointerInputChange) item$iv3;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            observer.onStop();
                        } else {
                            observer.onCancel();
                        }
                        return Unit.INSTANCE;
                    } catch (CancellationException e) {
                        c = e;
                        observer2.onCancel();
                        throw c;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (CancellationException e2) {
            c = e2;
            observer2 = observer;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:24:0x008e  */
    /* JADX WARN: Code duplicated, block: B:26:0x009f  */
    /* JADX WARN: Code duplicated, block: B:28:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:45:0x0104  */
    /* JADX WARN: Code duplicated, block: B:47:0x0115  */
    /* JADX WARN: Code duplicated, block: B:49:0x0122  */
    /* JADX WARN: Code duplicated, block: B:57:0x0125 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:0x00af A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object mouseSelection(AwaitPointerEventScope $this$mouseSelection, final MouseSelectionObserver observer, ClicksCounter clicksCounter, PointerEvent down, Continuation<? super Unit> continuation) {
        C04451 c04451;
        final SelectionAdjustment selectionAdjustment;
        Object objM775dragjO51t88;
        AwaitPointerEventScope $this$mouseSelection2;
        MouseSelectionObserver observer2;
        Object objM775dragjO51t89;
        AwaitPointerEventScope $this$mouseSelection3;
        MouseSelectionObserver observer3;
        boolean shouldConsumeUp;
        List<PointerInputChange> changes;
        int index$iv;
        int size;
        PointerInputChange it;
        boolean shouldConsumeUp2;
        List<PointerInputChange> changes2;
        int index$iv2;
        int size2;
        PointerInputChange it2;
        if (continuation instanceof C04451) {
            c04451 = (C04451) continuation;
            if ((c04451.label & Integer.MIN_VALUE) != 0) {
                c04451.label -= Integer.MIN_VALUE;
            } else {
                c04451 = new C04451(continuation);
            }
        } else {
            c04451 = new C04451(continuation);
        }
        C04451 c04452 = c04451;
        Object $result = c04452.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04452.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                clicksCounter.update(down);
                PointerInputChange downChange = down.getChanges().get(0);
                if (TextFieldSelectionManager_androidKt.isShiftPressed(down)) {
                    boolean started = observer.mo1626onExtendk4lQ0M(downChange.getPosition());
                    if (started) {
                        long id = downChange.getId();
                        Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$shouldConsumeUp$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it3) {
                                if (observer.mo1627onExtendDragk4lQ0M(it3.getPosition())) {
                                    it3.consume();
                                }
                            }
                        };
                        c04452.L$0 = $this$mouseSelection;
                        c04452.L$1 = observer;
                        c04452.label = 1;
                        objM775dragjO51t89 = DragGestureDetectorKt.m775dragjO51t88($this$mouseSelection, id, function1, c04452);
                        if (objM775dragjO51t89 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $this$mouseSelection3 = $this$mouseSelection;
                        observer3 = observer;
                        shouldConsumeUp = ((Boolean) objM775dragjO51t89).booleanValue();
                        if (shouldConsumeUp) {
                            changes = $this$mouseSelection3.getCurrentEvent().getChanges();
                            size = changes.size();
                            for (index$iv = 0; index$iv < size; index$iv++) {
                                Object item$iv = changes.get(index$iv);
                                it = (PointerInputChange) item$iv;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                        }
                        observer3.onDragDone();
                    }
                } else {
                    switch (clicksCounter.getClicks()) {
                        case 1:
                            selectionAdjustment = SelectionAdjustment.INSTANCE.getNone();
                            break;
                        case 2:
                            selectionAdjustment = SelectionAdjustment.INSTANCE.getWord();
                            break;
                        default:
                            selectionAdjustment = SelectionAdjustment.INSTANCE.getParagraph();
                            break;
                    }
                    boolean started2 = observer.mo1628onStart3MmeM6k(downChange.getPosition(), selectionAdjustment);
                    if (started2) {
                        long id2 = downChange.getId();
                        Function1<PointerInputChange, Unit> function2 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$shouldConsumeUp$2
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
                            public final void invoke2(PointerInputChange it3) {
                                if (observer.mo1625onDrag3MmeM6k(it3.getPosition(), selectionAdjustment)) {
                                    it3.consume();
                                }
                            }
                        };
                        c04452.L$0 = $this$mouseSelection;
                        c04452.L$1 = observer;
                        c04452.label = 2;
                        objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88($this$mouseSelection, id2, function2, c04452);
                        if (objM775dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $this$mouseSelection2 = $this$mouseSelection;
                        observer2 = observer;
                        shouldConsumeUp2 = ((Boolean) objM775dragjO51t88).booleanValue();
                        if (shouldConsumeUp2) {
                            changes2 = $this$mouseSelection2.getCurrentEvent().getChanges();
                            size2 = changes2.size();
                            for (index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                                Object item$iv2 = changes2.get(index$iv2);
                                it2 = (PointerInputChange) item$iv2;
                                if (PointerEventKt.changedToUp(it2)) {
                                    it2.consume();
                                }
                            }
                        }
                        observer2.onDragDone();
                    }
                }
                return Unit.INSTANCE;
            case 1:
                observer3 = (MouseSelectionObserver) c04452.L$1;
                $this$mouseSelection3 = (AwaitPointerEventScope) c04452.L$0;
                ResultKt.throwOnFailure($result);
                objM775dragjO51t89 = $result;
                shouldConsumeUp = ((Boolean) objM775dragjO51t89).booleanValue();
                if (shouldConsumeUp) {
                    changes = $this$mouseSelection3.getCurrentEvent().getChanges();
                    size = changes.size();
                    while (index$iv < size) {
                        Object item$iv3 = changes.get(index$iv);
                        it = (PointerInputChange) item$iv3;
                        if (PointerEventKt.changedToUp(it)) {
                            it.consume();
                        }
                    }
                }
                observer3.onDragDone();
                return Unit.INSTANCE;
            case 2:
                observer2 = (MouseSelectionObserver) c04452.L$1;
                $this$mouseSelection2 = (AwaitPointerEventScope) c04452.L$0;
                ResultKt.throwOnFailure($result);
                objM775dragjO51t88 = $result;
                shouldConsumeUp2 = ((Boolean) objM775dragjO51t88).booleanValue();
                if (shouldConsumeUp2) {
                    changes2 = $this$mouseSelection2.getCurrentEvent().getChanges();
                    size2 = changes2.size();
                    while (index$iv2 < size2) {
                        Object item$iv4 = changes2.get(index$iv2);
                        it2 = (PointerInputChange) item$iv4;
                        if (PointerEventKt.changedToUp(it2)) {
                            it2.consume();
                        }
                    }
                }
                observer2.onDragDone();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGesturePointerInputBtf2$2, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGesturePointerInputBtf2$2", f = "SelectionGestures.kt", i = {0}, l = {209, 217, 220, 221}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
    static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ClicksCounter $clicksCounter;
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ClicksCounter clicksCounter, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$clicksCounter = clicksCounter;
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$clicksCounter, this.$mouseSelectionObserver, this.$textDragObserver, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:19:0x007f  */
        /* JADX WARN: Code duplicated, block: B:21:0x0091  */
        /* JADX WARN: Code duplicated, block: B:22:0x0093  */
        /* JADX WARN: Code duplicated, block: B:25:0x0098 A[LOOP:0: B:18:0x007d->B:25:0x0098, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:28:0x00a1  */
        /* JADX WARN: Code duplicated, block: B:30:0x00b3 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:31:0x00b4  */
        /* JADX WARN: Code duplicated, block: B:34:0x00bb  */
        /* JADX WARN: Code duplicated, block: B:36:0x00c3  */
        /* JADX WARN: Code duplicated, block: B:38:0x00d3 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:39:0x00d4  */
        /* JADX WARN: Code duplicated, block: B:41:0x00d9  */
        /* JADX WARN: Code duplicated, block: B:43:0x00e9 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:44:0x00ea  */
        /* JADX WARN: Code duplicated, block: B:48:0x009c A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:49:0x009f A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AnonymousClass2 anonymousClass2;
            Object $result;
            AwaitPointerEventScope $this$awaitEachGesture;
            Object objAwaitDown;
            PointerEvent downEvent;
            boolean isPrecise;
            AnonymousClass2 anonymousClass3;
            Object $result2;
            AnonymousClass2 anonymousClass4;
            Object $result3;
            List<PointerInputChange> changes;
            int index$iv$iv;
            int size;
            boolean z;
            AnonymousClass2 anonymousClass5;
            Object $result4;
            PointerInputChange it;
            boolean z2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    anonymousClass2 = this;
                    $result = obj;
                    $this$awaitEachGesture = (AwaitPointerEventScope) anonymousClass2.L$0;
                    anonymousClass2.L$0 = $this$awaitEachGesture;
                    anonymousClass2.label = 1;
                    objAwaitDown = SelectionGesturesKt.awaitDown($this$awaitEachGesture, anonymousClass2);
                    if (objAwaitDown == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    downEvent = (PointerEvent) objAwaitDown;
                    anonymousClass2.$clicksCounter.update(downEvent);
                    isPrecise = SelectionGesturesKt.isPrecisePointer(downEvent);
                    if (isPrecise && PointerEvent_androidKt.m5714isPrimaryPressedaHzCxE(downEvent.getButtons())) {
                        changes = downEvent.getChanges();
                        index$iv$iv = 0;
                        size = changes.size();
                        while (true) {
                            if (index$iv$iv < size) {
                                Object it$iv = changes.get(index$iv$iv);
                                it = (PointerInputChange) it$iv;
                                z = false;
                                if (it.isConsumed()) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                if (!z2) {
                                    index$iv$iv++;
                                }
                            } else {
                                z = true;
                            }
                        }
                        if (z) {
                            anonymousClass2.L$0 = null;
                            anonymousClass2.label = 2;
                            if (SelectionGesturesKt.mouseSelectionBtf2($this$awaitEachGesture, anonymousClass2.$mouseSelectionObserver, anonymousClass2.$clicksCounter, downEvent, anonymousClass2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass5 = anonymousClass2;
                            $result4 = $result;
                        }
                        return Unit.INSTANCE;
                    }
                    if (!isPrecise) {
                        if (anonymousClass2.$clicksCounter.getClicks() == 1) {
                            anonymousClass2.L$0 = null;
                            anonymousClass2.label = 3;
                            if (SelectionGesturesKt.touchSelectionFirstPress($this$awaitEachGesture, anonymousClass2.$textDragObserver, downEvent, anonymousClass2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass4 = anonymousClass2;
                            $result3 = $result;
                        } else {
                            anonymousClass2.L$0 = null;
                            anonymousClass2.label = 4;
                            if (SelectionGesturesKt.touchSelectionSubsequentPress($this$awaitEachGesture, anonymousClass2.$textDragObserver, downEvent, anonymousClass2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass3 = anonymousClass2;
                            $result2 = $result;
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    anonymousClass2 = this;
                    $result = obj;
                    $this$awaitEachGesture = (AwaitPointerEventScope) anonymousClass2.L$0;
                    ResultKt.throwOnFailure($result);
                    objAwaitDown = $result;
                    downEvent = (PointerEvent) objAwaitDown;
                    anonymousClass2.$clicksCounter.update(downEvent);
                    isPrecise = SelectionGesturesKt.isPrecisePointer(downEvent);
                    if (isPrecise) {
                        changes = downEvent.getChanges();
                        index$iv$iv = 0;
                        size = changes.size();
                        while (true) {
                            if (index$iv$iv < size) {
                                Object it$iv2 = changes.get(index$iv$iv);
                                it = (PointerInputChange) it$iv2;
                                z = false;
                                if (it.isConsumed()) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (!z2) {
                                    index$iv$iv++;
                                }
                            } else {
                                z = true;
                            }
                        }
                        if (z) {
                            anonymousClass2.L$0 = null;
                            anonymousClass2.label = 2;
                            if (SelectionGesturesKt.mouseSelectionBtf2($this$awaitEachGesture, anonymousClass2.$mouseSelectionObserver, anonymousClass2.$clicksCounter, downEvent, anonymousClass2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass5 = anonymousClass2;
                            $result4 = $result;
                        }
                        return Unit.INSTANCE;
                    }
                    if (!isPrecise) {
                        if (anonymousClass2.$clicksCounter.getClicks() == 1) {
                            anonymousClass2.L$0 = null;
                            anonymousClass2.label = 3;
                            if (SelectionGesturesKt.touchSelectionFirstPress($this$awaitEachGesture, anonymousClass2.$textDragObserver, downEvent, anonymousClass2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass4 = anonymousClass2;
                            $result3 = $result;
                        } else {
                            anonymousClass2.L$0 = null;
                            anonymousClass2.label = 4;
                            if (SelectionGesturesKt.touchSelectionSubsequentPress($this$awaitEachGesture, anonymousClass2.$textDragObserver, downEvent, anonymousClass2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass3 = anonymousClass2;
                            $result2 = $result;
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    anonymousClass5 = this;
                    $result4 = obj;
                    ResultKt.throwOnFailure($result4);
                    return Unit.INSTANCE;
                case 3:
                    anonymousClass4 = this;
                    $result3 = obj;
                    ResultKt.throwOnFailure($result3);
                    return Unit.INSTANCE;
                case 4:
                    anonymousClass3 = this;
                    $result2 = obj;
                    ResultKt.throwOnFailure($result2);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final Object selectionGesturePointerInputBtf2(PointerInputScope $this$selectionGesturePointerInputBtf2, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        ClicksCounter clicksCounter = new ClicksCounter($this$selectionGesturePointerInputBtf2.getViewConfiguration());
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$selectionGesturePointerInputBtf2, new AnonymousClass2(clicksCounter, mouseSelectionObserver, textDragObserver, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:31:0x00a7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:0x00b0 A[Catch: CancellationException -> 0x00e3, TryCatch #1 {CancellationException -> 0x00e3, blocks: (B:32:0x00a8, B:34:0x00b0, B:36:0x00c1, B:38:0x00d0, B:39:0x00d3, B:40:0x00d8, B:41:0x00dd, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00c1 A[Catch: CancellationException -> 0x00e3, TryCatch #1 {CancellationException -> 0x00e3, blocks: (B:32:0x00a8, B:34:0x00b0, B:36:0x00c1, B:38:0x00d0, B:39:0x00d3, B:40:0x00d8, B:41:0x00dd, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00d0 A[Catch: CancellationException -> 0x00e3, TryCatch #1 {CancellationException -> 0x00e3, blocks: (B:32:0x00a8, B:34:0x00b0, B:36:0x00c1, B:38:0x00d0, B:39:0x00d3, B:40:0x00d8, B:41:0x00dd, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00dd A[Catch: CancellationException -> 0x00e3, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x00e3, blocks: (B:32:0x00a8, B:34:0x00b0, B:36:0x00c1, B:38:0x00d0, B:39:0x00d3, B:40:0x00d8, B:41:0x00dd, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00d3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object touchSelectionFirstPress(AwaitPointerEventScope $this$touchSelectionFirstPress, final TextDragObserver observer, PointerEvent downEvent, Continuation<? super Unit> continuation) {
        C04501 c04501;
        CancellationException c;
        TextDragObserver observer2;
        Object objM767awaitLongPressOrCancellationrnUCldI;
        AwaitPointerEventScope $this$touchSelectionFirstPress2;
        PointerInputChange firstDown;
        PointerInputChange longPress;
        Object objM775dragjO51t88;
        boolean dragCompletedWithUp;
        List<PointerInputChange> changes;
        int index$iv;
        int size;
        PointerInputChange it;
        if (continuation instanceof C04501) {
            c04501 = (C04501) continuation;
            if ((c04501.label & Integer.MIN_VALUE) != 0) {
                c04501.label -= Integer.MIN_VALUE;
            } else {
                c04501 = new C04501(continuation);
            }
        } else {
            c04501 = new C04501(continuation);
        }
        C04501 c04502 = c04501;
        Object $result = c04502.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c04502.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputChange firstDown2 = (PointerInputChange) CollectionsKt.first((List) downEvent.getChanges());
                    long id = firstDown2.getId();
                    c04502.L$0 = $this$touchSelectionFirstPress;
                    c04502.L$1 = observer;
                    c04502.L$2 = firstDown2;
                    c04502.label = 1;
                    objM767awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m767awaitLongPressOrCancellationrnUCldI($this$touchSelectionFirstPress, id, c04502);
                    if (objM767awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$touchSelectionFirstPress2 = $this$touchSelectionFirstPress;
                    firstDown = firstDown2;
                    longPress = (PointerInputChange) objM767awaitLongPressOrCancellationrnUCldI;
                    if (longPress != null && distanceIsTolerable($this$touchSelectionFirstPress2.getViewConfiguration(), firstDown, longPress)) {
                        observer.mo1413onStartk4lQ0M(longPress.getPosition());
                        long id2 = longPress.getId();
                        Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionFirstPress$dragCompletedWithUp$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it2) {
                                observer.mo1412onDragk4lQ0M(PointerEventKt.positionChange(it2));
                                it2.consume();
                            }
                        };
                        c04502.L$0 = $this$touchSelectionFirstPress2;
                        c04502.L$1 = observer;
                        c04502.L$2 = null;
                        c04502.label = 2;
                        objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88($this$touchSelectionFirstPress2, id2, function1, c04502);
                        if (objM775dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dragCompletedWithUp = ((Boolean) objM775dragjO51t88).booleanValue();
                        if (dragCompletedWithUp) {
                            changes = $this$touchSelectionFirstPress2.getCurrentEvent().getChanges();
                            size = changes.size();
                            for (index$iv = 0; index$iv < size; index$iv++) {
                                Object item$iv = changes.get(index$iv);
                                it = (PointerInputChange) item$iv;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            observer.onStop();
                        } else {
                            observer.onCancel();
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    firstDown = (PointerInputChange) c04502.L$2;
                    observer = (TextDragObserver) c04502.L$1;
                    $this$touchSelectionFirstPress2 = (AwaitPointerEventScope) c04502.L$0;
                    ResultKt.throwOnFailure($result);
                    objM767awaitLongPressOrCancellationrnUCldI = $result;
                    longPress = (PointerInputChange) objM767awaitLongPressOrCancellationrnUCldI;
                    if (longPress != null) {
                        observer.mo1413onStartk4lQ0M(longPress.getPosition());
                        long id3 = longPress.getId();
                        Function1<PointerInputChange, Unit> function2 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionFirstPress$dragCompletedWithUp$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it2) {
                                observer.mo1412onDragk4lQ0M(PointerEventKt.positionChange(it2));
                                it2.consume();
                            }
                        };
                        c04502.L$0 = $this$touchSelectionFirstPress2;
                        c04502.L$1 = observer;
                        c04502.L$2 = null;
                        c04502.label = 2;
                        objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88($this$touchSelectionFirstPress2, id3, function2, c04502);
                        if (objM775dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dragCompletedWithUp = ((Boolean) objM775dragjO51t88).booleanValue();
                        if (dragCompletedWithUp) {
                            changes = $this$touchSelectionFirstPress2.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (index$iv < size) {
                                Object item$iv2 = changes.get(index$iv);
                                it = (PointerInputChange) item$iv2;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            observer.onStop();
                        } else {
                            observer.onCancel();
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    observer2 = (TextDragObserver) c04502.L$1;
                    AwaitPointerEventScope $this$touchSelectionFirstPress3 = (AwaitPointerEventScope) c04502.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        $this$touchSelectionFirstPress2 = $this$touchSelectionFirstPress3;
                        observer = observer2;
                        objM775dragjO51t88 = $result;
                        dragCompletedWithUp = ((Boolean) objM775dragjO51t88).booleanValue();
                        if (dragCompletedWithUp) {
                            changes = $this$touchSelectionFirstPress2.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (index$iv < size) {
                                Object item$iv3 = changes.get(index$iv);
                                it = (PointerInputChange) item$iv3;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            observer.onStop();
                        } else {
                            observer.onCancel();
                        }
                        return Unit.INSTANCE;
                    } catch (CancellationException e) {
                        c = e;
                        observer2.onCancel();
                        throw c;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (CancellationException e2) {
            c = e2;
            observer2 = observer;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:29:0x00a8 A[Catch: CancellationException -> 0x003d, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00af A[Catch: CancellationException -> 0x003d, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x00b2 A[Catch: CancellationException -> 0x003d, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00bd A[Catch: CancellationException -> 0x003d, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00c3 A[Catch: CancellationException -> 0x003d, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00c7 A[Catch: CancellationException -> 0x003d, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00e4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:46:0x00ed A[Catch: CancellationException -> 0x003d, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x00ff A[Catch: CancellationException -> 0x003d, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x010e A[Catch: CancellationException -> 0x003d, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x011b A[Catch: CancellationException -> 0x003d, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x003d, blocks: (B:13:0x0037, B:44:0x00e5, B:46:0x00ed, B:48:0x00ff, B:50:0x010e, B:51:0x0111, B:52:0x0116, B:53:0x011b, B:27:0x00a4, B:29:0x00a8, B:30:0x00aa, B:32:0x00af, B:34:0x00b2, B:36:0x00bd, B:38:0x00c3, B:40:0x00c7, B:41:0x00cc), top: B:64:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0111 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v1, types: [androidx.compose.foundation.text.TextDragObserver] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r13v10, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope] */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v9 */
    public static final Object touchSelectionSubsequentPress(AwaitPointerEventScope awaitPointerEventScope, TextDragObserver textDragObserver, PointerEvent pointerEvent, Continuation<? super Unit> continuation) {
        C04511 c04511;
        PointerInputChange pointerInputChange;
        long id;
        Ref.LongRef longRef;
        Object objWithTimeoutOrNull;
        ?? r13;
        final TextDragObserver textDragObserver2;
        DownResolution downResolution;
        Object objM775dragjO51t88;
        ?? r14;
        List<PointerInputChange> changes;
        int i;
        int size;
        PointerInputChange pointerInputChange2;
        if (continuation instanceof C04511) {
            c04511 = (C04511) continuation;
            if ((c04511.label & Integer.MIN_VALUE) != 0) {
                c04511.label -= Integer.MIN_VALUE;
            } else {
                c04511 = new C04511(continuation);
            }
        } else {
            c04511 = new C04511(continuation);
        }
        C04511 c04512 = c04511;
        Object obj = c04512.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c04512.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    try {
                        pointerInputChange = (PointerInputChange) CollectionsKt.first((List) pointerEvent.getChanges());
                        id = pointerInputChange.getId();
                        longRef = new Ref.LongRef();
                        longRef.element = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
                        long longPressTimeoutMillis = awaitPointerEventScope.getViewConfiguration().getLongPressTimeoutMillis();
                        SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 = new SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1(id, longRef, null);
                        c04512.L$0 = awaitPointerEventScope;
                        c04512.L$1 = textDragObserver;
                        c04512.L$2 = pointerInputChange;
                        c04512.L$3 = longRef;
                        c04512.J$0 = id;
                        c04512.label = 1;
                        objWithTimeoutOrNull = awaitPointerEventScope.withTimeoutOrNull(longPressTimeoutMillis, selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1, c04512);
                        if (objWithTimeoutOrNull == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        r13 = awaitPointerEventScope;
                        textDragObserver2 = textDragObserver;
                        downResolution = (DownResolution) objWithTimeoutOrNull;
                        if (downResolution == null) {
                            downResolution = DownResolution.Timeout;
                        }
                        if (downResolution == DownResolution.Cancel) {
                            return Unit.INSTANCE;
                        }
                        textDragObserver2.mo1413onStartk4lQ0M(pointerInputChange.getPosition());
                        if (downResolution == DownResolution.Up) {
                            textDragObserver2.onStop();
                            return Unit.INSTANCE;
                        }
                        if (downResolution == DownResolution.Drag) {
                            textDragObserver2.mo1412onDragk4lQ0M(longRef.element);
                        }
                        Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$dragCompletedWithUp$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange3) {
                                invoke2(pointerInputChange3);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it) {
                                textDragObserver2.mo1412onDragk4lQ0M(PointerEventKt.positionChange(it));
                                it.consume();
                            }
                        };
                        c04512.L$0 = r13;
                        c04512.L$1 = textDragObserver2;
                        c04512.L$2 = null;
                        c04512.L$3 = null;
                        c04512.label = 2;
                        objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88(r13, id, function1, c04512);
                        r14 = r13;
                        if (objM775dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) objM775dragjO51t88).booleanValue()) {
                            changes = r14.getCurrentEvent().getChanges();
                            size = changes.size();
                            for (i = 0; i < size; i++) {
                                pointerInputChange2 = changes.get(i);
                                if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                    pointerInputChange2.consume();
                                }
                            }
                            textDragObserver2.onStop();
                        } else {
                            textDragObserver2.onCancel();
                        }
                        return Unit.INSTANCE;
                    } catch (CancellationException e) {
                        e = e;
                        awaitPointerEventScope = textDragObserver;
                        awaitPointerEventScope.onCancel();
                        throw e;
                    }
                case 1:
                    long j = c04512.J$0;
                    longRef = (Ref.LongRef) c04512.L$3;
                    pointerInputChange = (PointerInputChange) c04512.L$2;
                    TextDragObserver textDragObserver3 = (TextDragObserver) c04512.L$1;
                    AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) c04512.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        objWithTimeoutOrNull = obj;
                        textDragObserver2 = textDragObserver3;
                        r13 = awaitPointerEventScope2;
                        id = j;
                        downResolution = (DownResolution) objWithTimeoutOrNull;
                        if (downResolution == null) {
                            downResolution = DownResolution.Timeout;
                        }
                        if (downResolution == DownResolution.Cancel) {
                            return Unit.INSTANCE;
                        }
                        textDragObserver2.mo1413onStartk4lQ0M(pointerInputChange.getPosition());
                        if (downResolution == DownResolution.Up) {
                            textDragObserver2.onStop();
                            return Unit.INSTANCE;
                        }
                        if (downResolution == DownResolution.Drag) {
                            textDragObserver2.mo1412onDragk4lQ0M(longRef.element);
                        }
                        Function1<PointerInputChange, Unit> function2 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$dragCompletedWithUp$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange3) {
                                invoke2(pointerInputChange3);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it) {
                                textDragObserver2.mo1412onDragk4lQ0M(PointerEventKt.positionChange(it));
                                it.consume();
                            }
                        };
                        c04512.L$0 = r13;
                        c04512.L$1 = textDragObserver2;
                        c04512.L$2 = null;
                        c04512.L$3 = null;
                        c04512.label = 2;
                        objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88(r13, id, function2, c04512);
                        r14 = r13;
                        if (objM775dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) objM775dragjO51t88).booleanValue()) {
                            changes = r14.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (i < size) {
                                pointerInputChange2 = changes.get(i);
                                if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                    pointerInputChange2.consume();
                                }
                            }
                            textDragObserver2.onStop();
                        } else {
                            textDragObserver2.onCancel();
                        }
                        return Unit.INSTANCE;
                    } catch (CancellationException e2) {
                        e = e2;
                        awaitPointerEventScope = textDragObserver3;
                        awaitPointerEventScope.onCancel();
                        throw e;
                    }
                case 2:
                    textDragObserver2 = (TextDragObserver) c04512.L$1;
                    AwaitPointerEventScope awaitPointerEventScope3 = (AwaitPointerEventScope) c04512.L$0;
                    ResultKt.throwOnFailure(obj);
                    objM775dragjO51t88 = obj;
                    r14 = awaitPointerEventScope3;
                    if (((Boolean) objM775dragjO51t88).booleanValue()) {
                        changes = r14.getCurrentEvent().getChanges();
                        size = changes.size();
                        while (i < size) {
                            pointerInputChange2 = changes.get(i);
                            if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                pointerInputChange2.consume();
                            }
                        }
                        textDragObserver2.onStop();
                    } else {
                        textDragObserver2.onCancel();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (CancellationException e3) {
            e = e3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:32:0x0095 A[Catch: all -> 0x004c, TryCatch #2 {all -> 0x004c, blocks: (B:18:0x0047, B:30:0x008d, B:32:0x0095, B:34:0x00a7, B:36:0x00b6), top: B:75:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x00a7 A[Catch: all -> 0x004c, TryCatch #2 {all -> 0x004c, blocks: (B:18:0x0047, B:30:0x008d, B:32:0x0095, B:34:0x00a7, B:36:0x00b6), top: B:75:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00b6 A[Catch: all -> 0x004c, TRY_LEAVE, TryCatch #2 {all -> 0x004c, blocks: (B:18:0x0047, B:30:0x008d, B:32:0x0095, B:34:0x00a7, B:36:0x00b6), top: B:75:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x011a A[Catch: all -> 0x003c, TryCatch #3 {all -> 0x003c, blocks: (B:13:0x0036, B:55:0x0112, B:57:0x011a, B:59:0x012c, B:61:0x013b), top: B:77:0x0036 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x012c A[Catch: all -> 0x003c, TryCatch #3 {all -> 0x003c, blocks: (B:13:0x0036, B:55:0x0112, B:57:0x011a, B:59:0x012c, B:61:0x013b), top: B:77:0x0036 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x013b A[Catch: all -> 0x003c, TRY_LEAVE, TryCatch #3 {all -> 0x003c, blocks: (B:13:0x0036, B:55:0x0112, B:57:0x011a, B:59:0x012c, B:61:0x013b), top: B:77:0x0036 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code duplicated, block: B:81:0x013e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x00b9 A[SYNTHETIC] */
    public static final Object mouseSelectionBtf2(AwaitPointerEventScope $this$mouseSelectionBtf2, final MouseSelectionObserver observer, ClicksCounter clicksCounter, PointerEvent down, Continuation<? super Unit> continuation) throws Throwable {
        C04461 c04461;
        final SelectionAdjustment selectionAdjustment;
        Throwable th;
        MouseSelectionObserver observer2;
        Object objM775dragjO51t88;
        AwaitPointerEventScope $this$mouseSelectionBtf3;
        Throwable th2;
        MouseSelectionObserver observer3;
        Object objM775dragjO51t89;
        AwaitPointerEventScope $this$mouseSelectionBtf4;
        boolean shouldConsumeUp;
        List<PointerInputChange> changes;
        int index$iv;
        int size;
        PointerInputChange it;
        boolean shouldConsumeUp2;
        List<PointerInputChange> changes2;
        int index$iv2;
        int size2;
        PointerInputChange it2;
        if (continuation instanceof C04461) {
            c04461 = (C04461) continuation;
            if ((c04461.label & Integer.MIN_VALUE) != 0) {
                c04461.label -= Integer.MIN_VALUE;
            } else {
                c04461 = new C04461(continuation);
            }
        } else {
            c04461 = new C04461(continuation);
        }
        C04461 c04462 = c04461;
        Object $result = c04462.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04462.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputChange downChange = down.getChanges().get(0);
                if (TextFieldSelectionManager_androidKt.isShiftPressed(down)) {
                    boolean started = observer.mo1626onExtendk4lQ0M(downChange.getPosition());
                    if (started) {
                        try {
                            downChange.consume();
                            long id = downChange.getId();
                            Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelectionBtf2$shouldConsumeUp$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                    invoke2(pointerInputChange);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(PointerInputChange it3) {
                                    if (observer.mo1627onExtendDragk4lQ0M(it3.getPosition())) {
                                        it3.consume();
                                    }
                                }
                            };
                            c04462.L$0 = $this$mouseSelectionBtf2;
                            c04462.L$1 = observer;
                            c04462.label = 1;
                            objM775dragjO51t89 = DragGestureDetectorKt.m775dragjO51t88($this$mouseSelectionBtf2, id, function1, c04462);
                            if (objM775dragjO51t89 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$mouseSelectionBtf4 = $this$mouseSelectionBtf2;
                            observer3 = observer;
                            shouldConsumeUp = ((Boolean) objM775dragjO51t89).booleanValue();
                            if (shouldConsumeUp) {
                                changes = $this$mouseSelectionBtf4.getCurrentEvent().getChanges();
                                size = changes.size();
                                for (index$iv = 0; index$iv < size; index$iv++) {
                                    Object item$iv = changes.get(index$iv);
                                    it = (PointerInputChange) item$iv;
                                    if (PointerEventKt.changedToUp(it)) {
                                        it.consume();
                                    }
                                }
                            }
                            observer3.onDragDone();
                        } catch (Throwable th3) {
                            th2 = th3;
                            observer3 = observer;
                            observer3.onDragDone();
                            throw th2;
                        }
                    }
                } else {
                    switch (clicksCounter.getClicks()) {
                        case 1:
                            selectionAdjustment = SelectionAdjustment.INSTANCE.getNone();
                            break;
                        case 2:
                            selectionAdjustment = SelectionAdjustment.INSTANCE.getWord();
                            break;
                        default:
                            selectionAdjustment = SelectionAdjustment.INSTANCE.getParagraph();
                            break;
                    }
                    boolean started2 = observer.mo1628onStart3MmeM6k(downChange.getPosition(), selectionAdjustment);
                    if (started2) {
                        try {
                            downChange.consume();
                            long id2 = downChange.getId();
                            Function1<PointerInputChange, Unit> function2 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelectionBtf2$shouldConsumeUp$2
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
                                public final void invoke2(PointerInputChange it3) {
                                    if (observer.mo1625onDrag3MmeM6k(it3.getPosition(), selectionAdjustment)) {
                                        it3.consume();
                                    }
                                }
                            };
                            c04462.L$0 = $this$mouseSelectionBtf2;
                            c04462.L$1 = observer;
                            c04462.label = 2;
                            objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88($this$mouseSelectionBtf2, id2, function2, c04462);
                            if (objM775dragjO51t88 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$mouseSelectionBtf3 = $this$mouseSelectionBtf2;
                            observer2 = observer;
                            shouldConsumeUp2 = ((Boolean) objM775dragjO51t88).booleanValue();
                            if (shouldConsumeUp2) {
                                changes2 = $this$mouseSelectionBtf3.getCurrentEvent().getChanges();
                                size2 = changes2.size();
                                for (index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                                    Object item$iv2 = changes2.get(index$iv2);
                                    it2 = (PointerInputChange) item$iv2;
                                    if (PointerEventKt.changedToUp(it2)) {
                                        it2.consume();
                                    }
                                }
                            }
                            observer2.onDragDone();
                        } catch (Throwable th4) {
                            th = th4;
                            observer2 = observer;
                            observer2.onDragDone();
                            throw th;
                        }
                    }
                }
                return Unit.INSTANCE;
            case 1:
                observer3 = (MouseSelectionObserver) c04462.L$1;
                $this$mouseSelectionBtf4 = (AwaitPointerEventScope) c04462.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objM775dragjO51t89 = $result;
                    shouldConsumeUp = ((Boolean) objM775dragjO51t89).booleanValue();
                    if (shouldConsumeUp) {
                        changes = $this$mouseSelectionBtf4.getCurrentEvent().getChanges();
                        size = changes.size();
                        while (index$iv < size) {
                            Object item$iv3 = changes.get(index$iv);
                            it = (PointerInputChange) item$iv3;
                            if (PointerEventKt.changedToUp(it)) {
                                it.consume();
                            }
                        }
                    }
                    observer3.onDragDone();
                    return Unit.INSTANCE;
                } catch (Throwable th5) {
                    th2 = th5;
                    observer3.onDragDone();
                    throw th2;
                }
            case 2:
                observer2 = (MouseSelectionObserver) c04462.L$1;
                $this$mouseSelectionBtf3 = (AwaitPointerEventScope) c04462.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objM775dragjO51t88 = $result;
                    shouldConsumeUp2 = ((Boolean) objM775dragjO51t88).booleanValue();
                    if (shouldConsumeUp2) {
                        changes2 = $this$mouseSelectionBtf3.getCurrentEvent().getChanges();
                        size2 = changes2.size();
                        while (index$iv2 < size2) {
                            Object item$iv4 = changes2.get(index$iv2);
                            it2 = (PointerInputChange) item$iv4;
                            if (PointerEventKt.changedToUp(it2)) {
                                it2.consume();
                            }
                        }
                    }
                    observer2.onDragDone();
                    return Unit.INSTANCE;
                } catch (Throwable th6) {
                    th = th6;
                    observer2.onDragDone();
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x0068  */
    /* JADX WARN: Code duplicated, block: B:23:0x007a A[LOOP:0: B:19:0x0066->B:23:0x007a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:29:0x0078 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004f -> B:18:0x0056). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerEvent> r18) {
        /*
            r0 = r18
            boolean r1 = r0 instanceof androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r1 = (androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r1 = new androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1
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
            goto L56
        L3c:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r17
        L42:
            androidx.compose.ui.input.pointer.PointerEventPass r5 = androidx.compose.ui.input.pointer.PointerEventPass.Main
            r0.L$0 = r3
            r0.label = r4
            java.lang.Object r5 = r3.awaitPointerEvent(r5, r0)
            if (r5 != r2) goto L4f
            return r2
        L4f:
            r16 = r2
            r2 = r1
            r1 = r5
            r5 = r3
            r3 = r16
        L56:
            androidx.compose.ui.input.pointer.PointerEvent r1 = (androidx.compose.ui.input.pointer.PointerEvent) r1
            java.util.List r6 = r1.getChanges()
            r7 = 0
            r8 = 0
            r9 = 0
            int r10 = r6.size()
        L66:
            if (r9 >= r10) goto L7e
            java.lang.Object r11 = r6.get(r9)
            r12 = r11
            r13 = 0
            r14 = r12
            androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
            r15 = 0
            boolean r14 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDownIgnoreConsumed(r14)
            if (r14 != 0) goto L7a
            r10 = 0
            goto L80
        L7a:
            int r9 = r9 + 1
            goto L66
        L7e:
            r10 = r4
        L80:
            if (r10 == 0) goto L83
            return r1
        L83:
            r1 = r2
            r2 = r3
            r3 = r5
            goto L42
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean distanceIsTolerable(ViewConfiguration viewConfiguration, PointerInputChange change1, PointerInputChange change2) {
        float slop = DragGestureDetectorKt.m778pointerSlopE8SPZFQ(viewConfiguration, change1.getType());
        return Offset.m4291getDistanceimpl(Offset.m4297minusMKHz9U(change1.getPosition(), change2.getPosition())) < slop;
    }

    public static final boolean isPrecisePointer(PointerEvent $this$isPrecisePointer) {
        List<PointerInputChange> changes = $this$isPrecisePointer.getChanges();
        int size = changes.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = changes.get(index$iv$iv);
            PointerInputChange it = (PointerInputChange) item$iv$iv;
            if (!PointerType.m5813equalsimpl0(it.getType(), PointerType.INSTANCE.m5818getMouseT8wyACA())) {
                return false;
            }
        }
        return true;
    }
}
