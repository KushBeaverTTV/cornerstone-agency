package kotlinx.coroutines.sync;

import androidx.autofill.HintConstants;
import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelSegment$$ExternalSyntheticBackportWithForwarding0;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.selects.SelectInstance;

/* JADX INFO: compiled from: Semaphore.kt */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u0016H\u0082@¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001a\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00160\u001eH\u0005Jb\u0010\u001a\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u001f2\u0006\u0010\u001d\u001a\u0002H\u001f2!\u0010 \u001a\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00190!2!\u0010$\u001a\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00160!H\u0083\b¢\u0006\u0002\u0010%J\u001e\u0010&\u001a\u00020\u00162\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u0004J\b\u0010*\u001a\u00020\u0003H\u0002J\u0006\u0010+\u001a\u00020\u0016J\b\u0010,\u001a\u00020\u0016H\u0002J\u0010\u0010-\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020\u0019H\u0002J\f\u00100\u001a\u00020\u0019*\u00020\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004R\t\u0010\n\u001a\u00020\u000bX\u0082\u0004R\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004R\t\u0010\r\u001a\u00020\u000bX\u0082\u0004R\t\u0010\u000e\u001a\u00020\u000fX\u0082\u0004R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R&\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00160\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreAndMutexImpl;", "", "permits", "", "acquiredPermits", "<init>", "(II)V", "head", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "deqIdx", "Lkotlinx/atomicfu/AtomicLong;", "tail", "enqIdx", "_availablePermits", "Lkotlinx/atomicfu/AtomicInt;", "availablePermits", "getAvailablePermits", "()I", "onCancellationRelease", "Lkotlin/Function3;", "", "", "Lkotlin/coroutines/CoroutineContext;", "tryAcquire", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireSlowPath", "waiter", "Lkotlinx/coroutines/CancellableContinuation;", "W", "suspend", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "onAcquired", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onAcquireRegFunction", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "ignoredParam", "decPermits", "release", "coerceAvailablePermitsAtMaximum", "addAcquireToQueue", "Lkotlinx/coroutines/Waiter;", "tryResumeNextFromQueue", "tryResumeAcquire", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class SemaphoreAndMutexImpl {
    private volatile /* synthetic */ int _availablePermits$volatile;
    private volatile /* synthetic */ long deqIdx$volatile;
    private volatile /* synthetic */ long enqIdx$volatile;
    private volatile /* synthetic */ Object head$volatile;
    private final Function3<Throwable, Unit, CoroutineContext, Unit> onCancellationRelease;
    private final int permits;
    private volatile /* synthetic */ Object tail$volatile;
    private static final /* synthetic */ AtomicReferenceFieldUpdater head$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, Object.class, "head$volatile");
    private static final /* synthetic */ AtomicLongFieldUpdater deqIdx$volatile$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, "deqIdx$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater tail$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, Object.class, "tail$volatile");
    private static final /* synthetic */ AtomicLongFieldUpdater enqIdx$volatile$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, "enqIdx$volatile");
    private static final /* synthetic */ AtomicIntegerFieldUpdater _availablePermits$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, "_availablePermits$volatile");

    private final /* synthetic */ long getDeqIdx$volatile() {
        return this.deqIdx$volatile;
    }

    private final /* synthetic */ long getEnqIdx$volatile() {
        return this.enqIdx$volatile;
    }

    private final /* synthetic */ Object getHead$volatile() {
        return this.head$volatile;
    }

    private final /* synthetic */ Object getTail$volatile() {
        return this.tail$volatile;
    }

    private final /* synthetic */ int get_availablePermits$volatile() {
        return this._availablePermits$volatile;
    }

    private final /* synthetic */ void setDeqIdx$volatile(long j) {
        this.deqIdx$volatile = j;
    }

    private final /* synthetic */ void setEnqIdx$volatile(long j) {
        this.enqIdx$volatile = j;
    }

    private final /* synthetic */ void setHead$volatile(Object obj) {
        this.head$volatile = obj;
    }

    private final /* synthetic */ void setTail$volatile(Object obj) {
        this.tail$volatile = obj;
    }

    private final /* synthetic */ void set_availablePermits$volatile(int i) {
        this._availablePermits$volatile = i;
    }

    public SemaphoreAndMutexImpl(int permits, int acquiredPermits) {
        this.permits = permits;
        if (!(this.permits > 0)) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + this.permits).toString());
        }
        if (!(acquiredPermits >= 0 && acquiredPermits <= this.permits)) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + this.permits).toString());
        }
        SemaphoreSegment s = new SemaphoreSegment(0L, null, 2);
        this.head$volatile = s;
        this.tail$volatile = s;
        this._availablePermits$volatile = this.permits - acquiredPermits;
        this.onCancellationRelease = new Function3() { // from class: kotlinx.coroutines.sync.SemaphoreAndMutexImpl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SemaphoreAndMutexImpl.onCancellationRelease$lambda$2(this.f$0, (Throwable) obj, (Unit) obj2, (CoroutineContext) obj3);
            }
        };
    }

    public final int getAvailablePermits() {
        return Math.max(_availablePermits$volatile$FU.get(this), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCancellationRelease$lambda$2(SemaphoreAndMutexImpl this$0, Throwable th, Unit unit, CoroutineContext coroutineContext) {
        this$0.release();
        return Unit.INSTANCE;
    }

    public final boolean tryAcquire() {
        while (true) {
            int p = _availablePermits$volatile$FU.get(this);
            if (p > this.permits) {
                coerceAvailablePermitsAtMaximum();
            } else {
                if (p <= 0) {
                    return false;
                }
                if (_availablePermits$volatile$FU.compareAndSet(this, p, p - 1)) {
                    return true;
                }
            }
        }
    }

    public final Object acquire(Continuation<? super Unit> continuation) {
        Object objAcquireSlowPath;
        int p = decPermits();
        return (p <= 0 && (objAcquireSlowPath = acquireSlowPath(continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAcquireSlowPath : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object acquireSlowPath(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        try {
            if (!addAcquireToQueue(cancellable$iv)) {
                acquire((CancellableContinuation<? super Unit>) cancellable$iv);
            }
            Object result = cancellable$iv.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        } catch (Throwable e$iv) {
            cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw e$iv;
        }
    }

    protected final void acquire(CancellableContinuation<? super Unit> waiter) {
        while (p$iv <= 0) {
            Intrinsics.checkNotNull(waiter, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
            if (addAcquireToQueue((Waiter) waiter)) {
                return;
            }
        }
        waiter.resume(Unit.INSTANCE, this.onCancellationRelease);
    }

    private final <W> void acquire(W waiter, Function1<? super W, Boolean> suspend, Function1<? super W, Unit> onAcquired) {
        while (p <= 0) {
            if (suspend.invoke(waiter).booleanValue()) {
                return;
            }
        }
        onAcquired.invoke(waiter);
    }

    protected final void onAcquireRegFunction(SelectInstance<?> select, Object ignoredParam) {
        while (p$iv <= 0) {
            Intrinsics.checkNotNull(select, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
            if (addAcquireToQueue((Waiter) select)) {
                return;
            }
        }
        select.selectInRegistrationPhase(Unit.INSTANCE);
    }

    private final int decPermits() {
        int p;
        do {
            p = _availablePermits$volatile$FU.getAndDecrement(this);
        } while (p > this.permits);
        return p;
    }

    public final void release() {
        do {
            int p = _availablePermits$volatile$FU.getAndIncrement(this);
            if (p >= this.permits) {
                coerceAvailablePermitsAtMaximum();
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
            }
            if (p >= 0) {
                return;
            }
        } while (!tryResumeNextFromQueue());
    }

    private final void coerceAvailablePermitsAtMaximum() {
        int cur;
        do {
            cur = _availablePermits$volatile$FU.get(this);
            if (cur <= this.permits) {
                return;
            }
        } while (!_availablePermits$volatile$FU.compareAndSet(this, cur, this.permits));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean addAcquireToQueue(Waiter waiter) {
        Object s$iv;
        long id$iv;
        boolean z;
        SemaphoreSegment curTail = (SemaphoreSegment) tail$volatile$FU.get(this);
        long enqIdx = enqIdx$volatile$FU.getAndIncrement(this);
        KFunction createNewSegment = SemaphoreAndMutexImpl$addAcquireToQueue$createNewSegment$1.INSTANCE;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = tail$volatile$FU;
        long id$iv2 = enqIdx / ((long) SemaphoreKt.SEGMENT_SIZE);
        while (true) {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(curTail, id$iv2, (Function2) createNewSegment);
            if (SegmentOrClosed.m8967isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m8965getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) handler$atomicfu$iv.get(this);
                long j = cur$iv$iv.id;
                id$iv = id$iv2;
                long id$iv3 = to$iv$iv.id;
                if (j >= id$iv3) {
                    z = true;
                    break;
                }
                if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z = true;
                    break;
                }
                if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                    to$iv$iv.remove();
                }
                id$iv2 = id$iv;
            }
            if (z) {
                break;
            }
            id$iv2 = id$iv;
        }
        SemaphoreSegment segment = (SemaphoreSegment) SegmentOrClosed.m8965getSegmentimpl(s$iv);
        int i = (int) (enqIdx % ((long) SemaphoreKt.SEGMENT_SIZE));
        if (!ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(segment.getAcquirers(), i, null, waiter)) {
            Object expected$iv = SemaphoreKt.PERMIT;
            Object value$iv = SemaphoreKt.TAKEN;
            if (!ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(segment.getAcquirers(), i, expected$iv, value$iv)) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (segment.getAcquirers().get(i) == SemaphoreKt.BROKEN) {
                        return false;
                    }
                    throw new AssertionError();
                }
                return false;
            }
            if (waiter instanceof CancellableContinuation) {
                Intrinsics.checkNotNull(waiter, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
                ((CancellableContinuation) waiter).resume(Unit.INSTANCE, this.onCancellationRelease);
                return true;
            }
            if (waiter instanceof SelectInstance) {
                ((SelectInstance) waiter).selectInRegistrationPhase(Unit.INSTANCE);
                return true;
            }
            throw new IllegalStateException(("unexpected: " + waiter).toString());
        }
        waiter.invokeOnCancellation(segment, i);
        return true;
    }

    private final boolean tryResumeNextFromQueue() {
        Object s$iv;
        boolean z;
        SemaphoreSegment curHead = (SemaphoreSegment) head$volatile$FU.get(this);
        long deqIdx = deqIdx$volatile$FU.getAndIncrement(this);
        long id = deqIdx / ((long) SemaphoreKt.SEGMENT_SIZE);
        KFunction createNewSegment = SemaphoreAndMutexImpl$tryResumeNextFromQueue$createNewSegment$1.INSTANCE;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = head$volatile$FU;
        do {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(curHead, id, (Function2) createNewSegment);
            if (SegmentOrClosed.m8967isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m8965getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) handler$atomicfu$iv.get(this);
                if (cur$iv$iv.id >= to$iv$iv.id) {
                    z = true;
                    break;
                }
                if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z = true;
                    break;
                }
                if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                    to$iv$iv.remove();
                }
            }
        } while (!z);
        SemaphoreSegment segment = (SemaphoreSegment) SegmentOrClosed.m8965getSegmentimpl(s$iv);
        segment.cleanPrev();
        if (segment.id > id) {
            return false;
        }
        int i = (int) (deqIdx % ((long) SemaphoreKt.SEGMENT_SIZE));
        Object value$iv = segment.getAcquirers().getAndSet(i, SemaphoreKt.PERMIT);
        if (value$iv == null) {
            int i2 = SemaphoreKt.MAX_SPIN_CYCLES;
            int i3 = 0;
            while (i3 < i2) {
                SemaphoreSegment curHead2 = curHead;
                if (segment.getAcquirers().get(i) == SemaphoreKt.TAKEN) {
                    return true;
                }
                i3++;
                curHead = curHead2;
            }
            Object expected$iv = SemaphoreKt.PERMIT;
            return !ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(segment.getAcquirers(), i, expected$iv, SemaphoreKt.BROKEN);
        }
        if (value$iv == SemaphoreKt.CANCELLED) {
            return false;
        }
        return tryResumeAcquire(value$iv);
    }

    private final boolean tryResumeAcquire(Object $this$tryResumeAcquire) {
        if ($this$tryResumeAcquire instanceof CancellableContinuation) {
            Intrinsics.checkNotNull($this$tryResumeAcquire, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            Object token = ((CancellableContinuation) $this$tryResumeAcquire).tryResume(Unit.INSTANCE, null, this.onCancellationRelease);
            if (token == null) {
                return false;
            }
            ((CancellableContinuation) $this$tryResumeAcquire).completeResume(token);
            return true;
        }
        if ($this$tryResumeAcquire instanceof SelectInstance) {
            return ((SelectInstance) $this$tryResumeAcquire).trySelect(this, Unit.INSTANCE);
        }
        throw new IllegalStateException(("unexpected: " + $this$tryResumeAcquire).toString());
    }
}
