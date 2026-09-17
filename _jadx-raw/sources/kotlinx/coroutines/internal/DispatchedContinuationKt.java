package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;

/* JADX INFO: compiled from: DispatchedContinuation.kt */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\bH\u0007¢\u0006\u0002\u0010\t\u001a\u0012\u0010\n\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00040\fH\u0000\u001a;\u0010\r\u001a\u00020\u000b*\u0006\u0012\u0002\b\u00030\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014H\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"UNDEFINED", "Lkotlinx/coroutines/internal/Symbol;", "REUSABLE_CLAIMED", "resumeCancellableWith", "", "T", "Lkotlin/coroutines/Continuation;", "result", "Lkotlin/Result;", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "yieldUndispatched", "", "Lkotlinx/coroutines/internal/DispatchedContinuation;", "executeUnconfined", "contState", "", "mode", "", "doYield", "block", "Lkotlin/Function0;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DispatchedContinuationKt {
    private static final Symbol UNDEFINED = new Symbol("UNDEFINED");
    public static final Symbol REUSABLE_CLAIMED = new Symbol("REUSABLE_CLAIMED");

    /* JADX WARN: Code duplicated, block: B:22:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:24:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:34:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:42:0x00f8 A[Catch: all -> 0x0127, TryCatch #2 {all -> 0x0127, blocks: (B:40:0x00f2, B:58:0x011f, B:42:0x00f8, B:49:0x0106, B:53:0x0110, B:51:0x010c), top: B:78:0x00b0 }] */
    /* JADX WARN: Code duplicated, block: B:56:0x0117  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:? A[LOOP:0: B:58:0x011f->B:91:?, LOOP_END, SYNTHETIC] */
    public static final <T> void resumeCancellableWith(Continuation<? super T> continuation, Object result) {
        boolean z;
        Continuation<T> continuation2;
        CoroutineContext context$iv$iv$iv;
        Object oldValue$iv$iv$iv;
        UndispatchedCoroutine<?> undispatchedCoroutineUpdateUndispatchedCompletion;
        UndispatchedCoroutine<?> undispatchedCoroutine;
        if (!(continuation instanceof DispatchedContinuation)) {
            continuation.resumeWith(result);
            return;
        }
        DispatchedContinuation this_$iv = (DispatchedContinuation) continuation;
        Object state$iv = CompletionStateKt.toState(result);
        if (this_$iv.dispatcher.isDispatchNeeded(this_$iv.getContext())) {
            this_$iv._state = state$iv;
            this_$iv.resumeMode = 1;
            this_$iv.dispatcher.mo8959dispatch(this_$iv.getContext(), this_$iv);
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
        }
        EventLoop eventLoop$iv$iv = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$iv$iv.isUnconfinedLoopActive()) {
            this_$iv._state = state$iv;
            this_$iv.resumeMode = 1;
            eventLoop$iv$iv.dispatchUnconfined(this_$iv);
            return;
        }
        DispatchedContinuation $this$runUnconfinedEventLoop$iv$iv$iv = this_$iv;
        eventLoop$iv$iv.incrementUseCount(true);
        Job job$iv$iv = (Job) this_$iv.getContext().get(Job.INSTANCE);
        if (job$iv$iv != null) {
            try {
                if (job$iv$iv.isActive()) {
                    z = false;
                } else {
                    CancellationException cause$iv$iv = job$iv$iv.getCancellationException();
                    this_$iv.cancelCompletedResult$kotlinx_coroutines_core(state$iv, cause$iv$iv);
                    Result.Companion companion = Result.INSTANCE;
                    this_$iv.resumeWith(Result.m7388constructorimpl(ResultKt.createFailure(cause$iv$iv)));
                    z = true;
                }
                try {
                    if (!z) {
                        continuation2 = this_$iv.continuation;
                        Object countOrElement$iv$iv$iv = this_$iv.countOrElement;
                        context$iv$iv$iv = continuation2.getContext();
                        try {
                            oldValue$iv$iv$iv = ThreadContextKt.updateThreadContext(context$iv$iv$iv, countOrElement$iv$iv$iv);
                            if (oldValue$iv$iv$iv != ThreadContextKt.NO_THREAD_ELEMENTS) {
                                try {
                                    undispatchedCoroutineUpdateUndispatchedCompletion = CoroutineContextKt.updateUndispatchedCompletion(continuation2, context$iv$iv$iv, oldValue$iv$iv$iv);
                                } catch (Throwable th) {
                                    e$iv$iv$iv = th;
                                    try {
                                        $this$runUnconfinedEventLoop$iv$iv$iv.handleFatalException$kotlinx_coroutines_core(e$iv$iv$iv);
                                    } finally {
                                        eventLoop$iv$iv.decrementUseCount(true);
                                    }
                                }
                            } else {
                                undispatchedCoroutineUpdateUndispatchedCompletion = null;
                            }
                            undispatchedCoroutine = undispatchedCoroutineUpdateUndispatchedCompletion;
                            try {
                                try {
                                    this_$iv.continuation.resumeWith(result);
                                    Unit unit = Unit.INSTANCE;
                                    if (undispatchedCoroutine != null || undispatchedCoroutine.clearThreadContext()) {
                                        ThreadContextKt.restoreThreadContext(context$iv$iv$iv, oldValue$iv$iv$iv);
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (undispatchedCoroutine == null || undispatchedCoroutine.clearThreadContext()) {
                                        ThreadContextKt.restoreThreadContext(context$iv$iv$iv, oldValue$iv$iv$iv);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (Throwable th4) {
                            e$iv$iv$iv = th4;
                        }
                    }
                    while (eventLoop$iv$iv.processUnconfinedEvent()) {
                    }
                } catch (Throwable th5) {
                    e$iv$iv$iv = th5;
                }
            } catch (Throwable th6) {
                e$iv$iv$iv = th6;
                $this$runUnconfinedEventLoop$iv$iv$iv.handleFatalException$kotlinx_coroutines_core(e$iv$iv$iv);
            }
        } else {
            z = false;
            if (!z) {
                continuation2 = this_$iv.continuation;
                Object countOrElement$iv$iv$iv2 = this_$iv.countOrElement;
                context$iv$iv$iv = continuation2.getContext();
                oldValue$iv$iv$iv = ThreadContextKt.updateThreadContext(context$iv$iv$iv, countOrElement$iv$iv$iv2);
                if (oldValue$iv$iv$iv != ThreadContextKt.NO_THREAD_ELEMENTS) {
                    undispatchedCoroutineUpdateUndispatchedCompletion = CoroutineContextKt.updateUndispatchedCompletion(continuation2, context$iv$iv$iv, oldValue$iv$iv$iv);
                } else {
                    undispatchedCoroutineUpdateUndispatchedCompletion = null;
                }
                undispatchedCoroutine = undispatchedCoroutineUpdateUndispatchedCompletion;
                this_$iv.continuation.resumeWith(result);
                Unit unit2 = Unit.INSTANCE;
                if (undispatchedCoroutine != null) {
                    ThreadContextKt.restoreThreadContext(context$iv$iv$iv, oldValue$iv$iv$iv);
                } else {
                    ThreadContextKt.restoreThreadContext(context$iv$iv$iv, oldValue$iv$iv$iv);
                }
            }
            while (eventLoop$iv$iv.processUnconfinedEvent()) {
            }
        }
    }

    public static final boolean yieldUndispatched(DispatchedContinuation<? super Unit> dispatchedContinuation) {
        Object contState$iv = Unit.INSTANCE;
        if (DebugKt.getASSERTIONS_ENABLED()) {
        }
        EventLoop eventLoop$iv = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$iv.isUnconfinedQueueEmpty()) {
            return false;
        }
        if (eventLoop$iv.isUnconfinedLoopActive()) {
            dispatchedContinuation._state = contState$iv;
            dispatchedContinuation.resumeMode = 1;
            eventLoop$iv.dispatchUnconfined(dispatchedContinuation);
            return true;
        }
        DispatchedContinuation<? super Unit> $this$runUnconfinedEventLoop$iv$iv = dispatchedContinuation;
        eventLoop$iv.incrementUseCount(true);
        try {
            dispatchedContinuation.run();
            do {
            } while (eventLoop$iv.processUnconfinedEvent());
        } catch (Throwable e$iv$iv) {
            try {
                $this$runUnconfinedEventLoop$iv$iv.handleFatalException$kotlinx_coroutines_core(e$iv$iv);
            } finally {
                eventLoop$iv.decrementUseCount(true);
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ boolean executeUnconfined$default(DispatchedContinuation dispatchedContinuation, Object obj, int i, boolean z, Function0 function0, int i2, Object obj2) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((i != -1 ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (z && eventLoop$kotlinx_coroutines_core.isUnconfinedQueueEmpty()) {
            return false;
        }
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            dispatchedContinuation._state = obj;
            dispatchedContinuation.resumeMode = i;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedContinuation);
            return true;
        }
        DispatchedContinuation dispatchedContinuation2 = dispatchedContinuation;
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            function0.invoke();
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
            InlineMarker.finallyStart(1);
        } catch (Throwable th) {
            try {
                dispatchedContinuation2.handleFatalException$kotlinx_coroutines_core(th);
                InlineMarker.finallyStart(1);
            } finally {
                InlineMarker.finallyStart(1);
                eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean executeUnconfined(DispatchedContinuation<?> dispatchedContinuation, Object obj, int i, boolean z, Function0<Unit> function0) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((i != -1 ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (z && eventLoop$kotlinx_coroutines_core.isUnconfinedQueueEmpty()) {
            return false;
        }
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            dispatchedContinuation._state = obj;
            dispatchedContinuation.resumeMode = i;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedContinuation);
            return true;
        }
        DispatchedContinuation<?> dispatchedContinuation2 = dispatchedContinuation;
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            function0.invoke();
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
            InlineMarker.finallyStart(1);
        } catch (Throwable th) {
            try {
                dispatchedContinuation2.handleFatalException$kotlinx_coroutines_core(th);
                InlineMarker.finallyStart(1);
            } finally {
                InlineMarker.finallyStart(1);
                eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            }
        }
        return false;
    }
}
