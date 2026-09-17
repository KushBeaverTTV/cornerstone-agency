package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: DataStoreImpl.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u0005H¤@¢\u0006\u0002\u0010\tJ\u000e\u0010\u000b\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/datastore/core/RunOnce;", "", "()V", "didRun", "Lkotlinx/coroutines/CompletableDeferred;", "", "runMutex", "Lkotlinx/coroutines/sync/Mutex;", "awaitComplete", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doRun", "runIfNeeded", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class RunOnce {
    private final Mutex runMutex = MutexKt.Mutex$default(false, 1, null);
    private final CompletableDeferred<Unit> didRun = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);

    /* JADX INFO: renamed from: androidx.datastore.core.RunOnce$runIfNeeded$1, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.RunOnce", f = "DataStoreImpl.kt", i = {0, 0, 1, 1}, l = {552, 505}, m = "runIfNeeded", n = {"this", "$this$withLock_u24default$iv", "this", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RunOnce.this.runIfNeeded(this);
        }
    }

    protected abstract Object doRun(Continuation<? super Unit> continuation);

    public final Object awaitComplete(Continuation<? super Unit> continuation) {
        Object objAwait = this.didRun.await(continuation);
        return objAwait == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwait : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0080 A[Catch: all -> 0x00ac, TRY_LEAVE, TryCatch #1 {all -> 0x00ac, blocks: (B:27:0x0078, B:29:0x0080), top: B:47:0x0078 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0093 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x0094  */
    /* JADX WARN: Code duplicated, block: B:45:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object runIfNeeded(Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Mutex $this$withLock_u24default$iv;
        RunOnce runOnce;
        Mutex owner$iv;
        Mutex $this$withLock_u24default$iv2;
        Mutex $this$withLock_u24default$iv3;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.didRun.isCompleted()) {
                    return Unit.INSTANCE;
                }
                $this$withLock_u24default$iv = this.runMutex;
                anonymousClass2.L$0 = this;
                anonymousClass2.L$1 = $this$withLock_u24default$iv;
                anonymousClass2.label = 1;
                if ($this$withLock_u24default$iv.lock(null, anonymousClass2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                runOnce = this;
                owner$iv = null;
                try {
                    if (runOnce.didRun.isCompleted()) {
                        Unit unit = Unit.INSTANCE;
                        $this$withLock_u24default$iv.unlock(owner$iv);
                        return unit;
                    }
                    try {
                        anonymousClass2.L$0 = runOnce;
                        anonymousClass2.L$1 = $this$withLock_u24default$iv;
                        anonymousClass2.label = 2;
                        if (runOnce.doRun(anonymousClass2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Mutex mutex = owner$iv;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                        $this$withLock_u24default$iv3 = mutex;
                        runOnce.didRun.complete(Unit.INSTANCE);
                        $this$withLock_u24default$iv2.unlock($this$withLock_u24default$iv3);
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        Mutex mutex2 = owner$iv;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                        $this$withLock_u24default$iv3 = mutex2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    Mutex mutex3 = owner$iv;
                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                    $this$withLock_u24default$iv3 = mutex3;
                }
                $this$withLock_u24default$iv2.unlock($this$withLock_u24default$iv3);
                throw th;
            case 1:
                $this$withLock_u24default$iv = (Mutex) anonymousClass2.L$1;
                RunOnce runOnce2 = (RunOnce) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                runOnce = runOnce2;
                owner$iv = null;
                if (runOnce.didRun.isCompleted()) {
                    Unit unit2 = Unit.INSTANCE;
                    $this$withLock_u24default$iv.unlock(owner$iv);
                    return unit2;
                }
                anonymousClass2.L$0 = runOnce;
                anonymousClass2.L$1 = $this$withLock_u24default$iv;
                anonymousClass2.label = 2;
                if (runOnce.doRun(anonymousClass2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Mutex mutex4 = owner$iv;
                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                $this$withLock_u24default$iv3 = mutex4;
                runOnce.didRun.complete(Unit.INSTANCE);
                $this$withLock_u24default$iv2.unlock($this$withLock_u24default$iv3);
                return Unit.INSTANCE;
                $this$withLock_u24default$iv2.unlock($this$withLock_u24default$iv3);
                throw th;
            case 2:
                $this$withLock_u24default$iv3 = null;
                $this$withLock_u24default$iv2 = (Mutex) anonymousClass2.L$1;
                runOnce = (RunOnce) anonymousClass2.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    runOnce.didRun.complete(Unit.INSTANCE);
                    $this$withLock_u24default$iv2.unlock($this$withLock_u24default$iv3);
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
