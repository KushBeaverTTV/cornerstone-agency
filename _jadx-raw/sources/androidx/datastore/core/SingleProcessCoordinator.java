package androidx.datastore.core;

import androidx.core.view.MotionEventCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: SingleProcessCoordinator.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0010J2\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u001c\u0010\u0014\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0015H\u0096@¢\u0006\u0002\u0010\u0018J8\u0010\u0019\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\"\u0010\u0014\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u001aH\u0096@¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/datastore/core/SingleProcessCoordinator;", "Landroidx/datastore/core/InterProcessCoordinator;", "filePath", "", "(Ljava/lang/String;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "updateNotifications", "Lkotlinx/coroutines/flow/Flow;", "", "getUpdateNotifications", "()Lkotlinx/coroutines/flow/Flow;", "version", "Landroidx/datastore/core/AtomicInt;", "getVersion", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementAndGetVersion", "lock", "T", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryLock", "Lkotlin/Function2;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SingleProcessCoordinator implements InterProcessCoordinator {
    private final String filePath;
    private final Mutex mutex;
    private final Flow<Unit> updateNotifications;
    private final AtomicInt version;

    /* JADX INFO: renamed from: androidx.datastore.core.SingleProcessCoordinator$lock$1, reason: invalid class name */
    /* JADX INFO: compiled from: SingleProcessCoordinator.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.SingleProcessCoordinator", f = "SingleProcessCoordinator.kt", i = {0, 0, 1}, l = {66, MotionEventCompat.AXIS_GENERIC_10}, m = "lock", n = {"block", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0"})
    static final class AnonymousClass1<T> extends ContinuationImpl {
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
            return SingleProcessCoordinator.this.lock(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.SingleProcessCoordinator$tryLock$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SingleProcessCoordinator.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.SingleProcessCoordinator", f = "SingleProcessCoordinator.kt", i = {0, 0}, l = {50}, m = "tryLock", n = {"$this$withTryLock_u24default$iv", "locked$iv"}, s = {"L$0", "Z$0"})
    static final class C07481<T> extends ContinuationImpl {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C07481(Continuation<? super C07481> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SingleProcessCoordinator.this.tryLock(null, this);
        }
    }

    public SingleProcessCoordinator(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        this.filePath = filePath;
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        this.version = new AtomicInt(0);
        this.updateNotifications = FlowKt.flow(new SingleProcessCoordinator$updateNotifications$1(null));
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Flow<Unit> getUpdateNotifications() {
        return this.updateNotifications;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0077 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x0078  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.datastore.core.InterProcessCoordinator
    public <T> Object lock(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Function1<? super Continuation<? super T>, ? extends Object> function2;
        Mutex $this$withLock_u24default$iv;
        Object $this$withLock_u24default$iv2;
        Object objInvoke;
        Object owner$iv;
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
        Object owner$iv2 = null;
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                function2 = function1;
                Mutex $this$withLock_u24default$iv3 = this.mutex;
                anonymousClass2.L$0 = function2;
                anonymousClass2.L$1 = $this$withLock_u24default$iv3;
                anonymousClass2.label = 1;
                if ($this$withLock_u24default$iv3.lock(null, anonymousClass2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $this$withLock_u24default$iv = $this$withLock_u24default$iv3;
                $this$withLock_u24default$iv2 = null;
                try {
                    anonymousClass2.L$0 = $this$withLock_u24default$iv;
                    anonymousClass2.L$1 = null;
                    anonymousClass2.label = 2;
                    objInvoke = function2.invoke(anonymousClass2);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Object obj = $this$withLock_u24default$iv2;
                    owner$iv = objInvoke;
                    owner$iv2 = obj;
                    $this$withLock_u24default$iv.unlock(owner$iv2);
                    return owner$iv;
                } catch (Throwable th) {
                    th = th;
                    owner$iv2 = $this$withLock_u24default$iv2;
                    $this$withLock_u24default$iv.unlock(owner$iv2);
                    throw th;
                }
            case 1:
                Mutex $this$withLock_u24default$iv4 = (Mutex) anonymousClass2.L$1;
                function2 = (Function1) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                $this$withLock_u24default$iv2 = null;
                $this$withLock_u24default$iv = $this$withLock_u24default$iv4;
                anonymousClass2.L$0 = $this$withLock_u24default$iv;
                anonymousClass2.L$1 = null;
                anonymousClass2.label = 2;
                objInvoke = function2.invoke(anonymousClass2);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj2 = $this$withLock_u24default$iv2;
                owner$iv = objInvoke;
                owner$iv2 = obj2;
                $this$withLock_u24default$iv.unlock(owner$iv2);
                return owner$iv;
            case 2:
                $this$withLock_u24default$iv = (Mutex) anonymousClass2.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    owner$iv = $result;
                    $this$withLock_u24default$iv.unlock(owner$iv2);
                    return owner$iv;
                } catch (Throwable th2) {
                    th = th2;
                    $this$withLock_u24default$iv.unlock(owner$iv2);
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x006c  */
    /* JADX WARN: Code duplicated, block: B:33:0x0076  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.datastore.core.InterProcessCoordinator
    public <T> Object tryLock(Function2<? super Boolean, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) throws Throwable {
        C07481 c07481;
        Mutex $this$withTryLock_u24default$iv;
        Object owner$iv;
        boolean locked$iv;
        boolean locked$iv2;
        Object objInvoke;
        Object owner$iv2;
        if (continuation instanceof C07481) {
            c07481 = (C07481) continuation;
            if ((c07481.label & Integer.MIN_VALUE) != 0) {
                c07481.label -= Integer.MIN_VALUE;
            } else {
                c07481 = new C07481(continuation);
            }
        } else {
            c07481 = new C07481(continuation);
        }
        C07481 c07482 = c07481;
        Object $result = c07482.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07482.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                $this$withTryLock_u24default$iv = this.mutex;
                owner$iv = null;
                locked$iv = $this$withTryLock_u24default$iv.tryLock(null);
                boolean it = locked$iv;
                try {
                    Boolean boolBoxBoolean = Boxing.boxBoolean(it);
                    c07482.L$0 = $this$withTryLock_u24default$iv;
                    c07482.Z$0 = locked$iv;
                    c07482.label = 1;
                    objInvoke = function2.invoke(boolBoxBoolean, c07482);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    owner$iv2 = owner$iv;
                    if (locked$iv) {
                        $this$withTryLock_u24default$iv.unlock(owner$iv2);
                    }
                    return objInvoke;
                } catch (Throwable th) {
                    th = th;
                    locked$iv2 = locked$iv;
                    if (locked$iv2) {
                        $this$withTryLock_u24default$iv.unlock(owner$iv);
                    }
                    throw th;
                }
            case 1:
                locked$iv2 = c07482.Z$0;
                $this$withTryLock_u24default$iv = (Mutex) c07482.L$0;
                owner$iv = null;
                try {
                    ResultKt.throwOnFailure($result);
                    locked$iv = locked$iv2;
                    objInvoke = $result;
                    owner$iv2 = owner$iv;
                    if (locked$iv) {
                        $this$withTryLock_u24default$iv.unlock(owner$iv2);
                    }
                    return objInvoke;
                } catch (Throwable th2) {
                    th = th2;
                    if (locked$iv2) {
                        $this$withTryLock_u24default$iv.unlock(owner$iv);
                    }
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object getVersion(Continuation<? super Integer> continuation) {
        return Boxing.boxInt(this.version.get());
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object incrementAndGetVersion(Continuation<? super Integer> continuation) {
        return Boxing.boxInt(this.version.incrementAndGet());
    }
}
