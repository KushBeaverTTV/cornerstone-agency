package androidx.datastore.core;

import ai.nous.aurora.domain.model.SessionPollPolicy;
import androidx.core.view.MotionEventCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: MultiProcessCoordinator.android.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000 42\u00020\u0001:\u00014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\bH\u0002J\u000e\u0010\"\u001a\u00020#H\u0096@¢\u0006\u0002\u0010$J\u000e\u0010%\u001a\u00020#H\u0096@¢\u0006\u0002\u0010$J2\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010'2\u001c\u0010(\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0*\u0012\u0006\u0012\u0004\u0018\u00010+0)H\u0096@¢\u0006\u0002\u0010,J8\u0010-\u001a\u0002H'\"\u0004\b\u0000\u0010'2\"\u0010(\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020/\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0*\u0012\u0006\u0012\u0004\u0018\u00010+0.H\u0096@¢\u0006\u0002\u00100J:\u00101\u001a\u0002H'\"\u0004\b\u0000\u0010'2$\b\u0004\u0010(\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0*\u0012\u0006\u0012\u0004\u0018\u00010+0.H\u0082H¢\u0006\u0002\u00100J\f\u00102\u001a\u00020\u001d*\u00020\u0005H\u0002J\f\u00103\u001a\u00020\u001d*\u00020\u0005H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\fR\u001b\u0010\u0016\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a*\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00065"}, d2 = {"Landroidx/datastore/core/MultiProcessCoordinator;", "Landroidx/datastore/core/InterProcessCoordinator;", "context", "Lkotlin/coroutines/CoroutineContext;", "file", "Ljava/io/File;", "(Lkotlin/coroutines/CoroutineContext;Ljava/io/File;)V", "LOCK_ERROR_MESSAGE", "", "LOCK_SUFFIX", "VERSION_SUFFIX", "getFile", "()Ljava/io/File;", "inMemoryMutex", "Lkotlinx/coroutines/sync/Mutex;", "lazySharedCounter", "Lkotlin/Lazy;", "Landroidx/datastore/core/SharedCounter;", "lockFile", "getLockFile", "lockFile$delegate", "Lkotlin/Lazy;", "sharedCounter", "getSharedCounter$delegate", "(Landroidx/datastore/core/MultiProcessCoordinator;)Ljava/lang/Object;", "getSharedCounter", "()Landroidx/datastore/core/SharedCounter;", "updateNotifications", "Lkotlinx/coroutines/flow/Flow;", "", "getUpdateNotifications", "()Lkotlinx/coroutines/flow/Flow;", "fileWithSuffix", "suffix", "getVersion", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementAndGetVersion", "lock", "T", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryLock", "Lkotlin/Function2;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withLazyCounter", "createIfNotExists", "createParentDirectories", "Companion", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MultiProcessCoordinator implements InterProcessCoordinator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DEADLOCK_ERROR_MESSAGE = "Resource deadlock would occur";
    private static final long INITIAL_WAIT_MILLIS = 10;
    private static final long MAX_WAIT_MILLIS = SessionPollPolicy.MAX_INTERVAL_MS;
    private final String LOCK_ERROR_MESSAGE;
    private final String LOCK_SUFFIX;
    private final String VERSION_SUFFIX;
    private final CoroutineContext context;
    private final File file;
    private final Mutex inMemoryMutex;
    private final Lazy<SharedCounter> lazySharedCounter;

    /* JADX INFO: renamed from: lockFile$delegate, reason: from kotlin metadata */
    private final Lazy lockFile;
    private final Flow<Unit> updateNotifications;

    /* JADX INFO: renamed from: androidx.datastore.core.MultiProcessCoordinator$lock$1, reason: invalid class name */
    /* JADX INFO: compiled from: MultiProcessCoordinator.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.MultiProcessCoordinator", f = "MultiProcessCoordinator.android.kt", i = {0, 0, 0, 1, 1, 2, 2}, l = {211, MotionEventCompat.AXIS_GENERIC_16, 48}, m = "lock", n = {"this", "block", "$this$withLock_u24default$iv", "block", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "lock"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$2"})
    static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultiProcessCoordinator.this.lock(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.MultiProcessCoordinator$tryLock$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MultiProcessCoordinator.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.MultiProcessCoordinator", f = "MultiProcessCoordinator.android.kt", i = {0, 0, 1, 1, 1}, l = {62, 87}, m = "tryLock", n = {"$this$withTryLock_u24default$iv", "locked$iv", "$this$withTryLock_u24default$iv", "lock", "locked$iv"}, s = {"L$0", "Z$0", "L$0", "L$2", "Z$0"})
    static final class C07471<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C07471(Continuation<? super C07471> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultiProcessCoordinator.this.tryLock(null, this);
        }
    }

    public MultiProcessCoordinator(CoroutineContext context, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        this.context = context;
        this.file = file;
        this.updateNotifications = MulticastFileObserver.INSTANCE.observe(this.file);
        this.LOCK_SUFFIX = ".lock";
        this.VERSION_SUFFIX = ".version";
        this.LOCK_ERROR_MESSAGE = "fcntl failed: EAGAIN";
        this.inMemoryMutex = MutexKt.Mutex$default(false, 1, null);
        this.lockFile = LazyKt.lazy(new Function0<File>() { // from class: androidx.datastore.core.MultiProcessCoordinator$lockFile$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() throws IOException {
                File lockFile = this.this$0.fileWithSuffix(this.this$0.LOCK_SUFFIX);
                this.this$0.createIfNotExists(lockFile);
                return lockFile;
            }
        });
        this.lazySharedCounter = LazyKt.lazy(new Function0<SharedCounter>() { // from class: androidx.datastore.core.MultiProcessCoordinator$lazySharedCounter$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SharedCounter invoke() {
                SharedCounter.INSTANCE.loadLib();
                SharedCounter.Companion companion = SharedCounter.INSTANCE;
                final MultiProcessCoordinator multiProcessCoordinator = this.this$0;
                return companion.create$datastore_core_release(new Function0<File>() { // from class: androidx.datastore.core.MultiProcessCoordinator$lazySharedCounter$1.1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final File invoke() throws IOException {
                        File versionFile = multiProcessCoordinator.fileWithSuffix(multiProcessCoordinator.VERSION_SUFFIX);
                        multiProcessCoordinator.createIfNotExists(versionFile);
                        return versionFile;
                    }
                });
            }
        });
    }

    protected final File getFile() {
        return this.file;
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Flow<Unit> getUpdateNotifications() {
        return this.updateNotifications;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00cd A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x010a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // androidx.datastore.core.InterProcessCoordinator
    public <T> Object lock(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        MultiProcessCoordinator multiProcessCoordinator;
        Function1<? super Continuation<? super T>, ? extends Object> function2;
        Mutex $this$withLock_u24default$iv;
        Object owner$iv;
        int i;
        FileOutputStream fileOutputStream;
        Throwable th;
        int i2;
        FileLock lock;
        int i3;
        java.io.Closeable closeable;
        FileLock lock2;
        Object exclusiveFileLockWithRetryIfDeadlock;
        Object objInvoke;
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
        Object $result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object owner$iv2 = null;
        switch (anonymousClass1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                multiProcessCoordinator = this;
                Mutex $this$withLock_u24default$iv2 = multiProcessCoordinator.inMemoryMutex;
                anonymousClass1.L$0 = multiProcessCoordinator;
                anonymousClass1.L$1 = function1;
                anonymousClass1.L$2 = $this$withLock_u24default$iv2;
                anonymousClass1.label = 1;
                if ($this$withLock_u24default$iv2.lock(null, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                function2 = function1;
                $this$withLock_u24default$iv = $this$withLock_u24default$iv2;
                owner$iv = null;
                i = 0;
                try {
                    fileOutputStream = new FileOutputStream(multiProcessCoordinator.getLockFile());
                    try {
                        FileOutputStream lockFileStream = fileOutputStream;
                        i2 = 0;
                        lock = null;
                        try {
                            Companion companion = INSTANCE;
                            anonymousClass1.L$0 = function2;
                            anonymousClass1.L$1 = $this$withLock_u24default$iv;
                            anonymousClass1.L$2 = fileOutputStream;
                            anonymousClass1.label = 2;
                            exclusiveFileLockWithRetryIfDeadlock = companion.getExclusiveFileLockWithRetryIfDeadlock(lockFileStream, anonymousClass1);
                            if (exclusiveFileLockWithRetryIfDeadlock == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            closeable = fileOutputStream;
                            try {
                                lock2 = (FileLock) exclusiveFileLockWithRetryIfDeadlock;
                                try {
                                    anonymousClass1.L$0 = $this$withLock_u24default$iv;
                                    anonymousClass1.L$1 = closeable;
                                    anonymousClass1.L$2 = lock2;
                                    anonymousClass1.label = 3;
                                    objInvoke = function2.invoke(anonymousClass1);
                                    if (objInvoke == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    if (lock2 != null) {
                                        try {
                                            lock2.release();
                                            break;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            fileOutputStream = closeable;
                                            try {
                                                throw th;
                                            } catch (Throwable th3) {
                                                kotlin.io.CloseableKt.closeFinally(fileOutputStream, th);
                                                throw th3;
                                            }
                                        }
                                    }
                                    try {
                                        kotlin.io.CloseableKt.closeFinally(closeable, null);
                                        $this$withLock_u24default$iv.unlock(owner$iv);
                                        return objInvoke;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        $this$withLock_u24default$iv.unlock(owner$iv);
                                        throw th;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    owner$iv2 = owner$iv;
                                    i3 = i;
                                    if (lock2 != null) {
                                        try {
                                            lock2.release();
                                        } catch (Throwable th6) {
                                            fileOutputStream = closeable;
                                            owner$iv = owner$iv2;
                                            th = th6;
                                            throw th;
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                lock2 = lock;
                                owner$iv2 = owner$iv;
                                i3 = i;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            owner$iv2 = owner$iv;
                            i3 = 0;
                            closeable = fileOutputStream;
                            lock2 = null;
                            if (lock2 != null) {
                                lock2.release();
                            }
                            throw th;
                        }
                    } catch (Throwable th9) {
                        th = th9;
                        throw th;
                    }
                } catch (Throwable th10) {
                    th = th10;
                    $this$withLock_u24default$iv.unlock(owner$iv);
                    throw th;
                }
                break;
            case 1:
                owner$iv = null;
                Mutex $this$withLock_u24default$iv3 = (Mutex) anonymousClass1.L$2;
                Function1<? super Continuation<? super T>, ? extends Object> function3 = (Function1) anonymousClass1.L$1;
                multiProcessCoordinator = (MultiProcessCoordinator) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                $this$withLock_u24default$iv = $this$withLock_u24default$iv3;
                function2 = function3;
                i = 0;
                fileOutputStream = new FileOutputStream(multiProcessCoordinator.getLockFile());
                FileOutputStream lockFileStream2 = fileOutputStream;
                i2 = 0;
                lock = null;
                Companion companion2 = INSTANCE;
                anonymousClass1.L$0 = function2;
                anonymousClass1.L$1 = $this$withLock_u24default$iv;
                anonymousClass1.L$2 = fileOutputStream;
                anonymousClass1.label = 2;
                exclusiveFileLockWithRetryIfDeadlock = companion2.getExclusiveFileLockWithRetryIfDeadlock(lockFileStream2, anonymousClass1);
                if (exclusiveFileLockWithRetryIfDeadlock == coroutine_suspended) {
                    return coroutine_suspended;
                }
                closeable = fileOutputStream;
                lock2 = (FileLock) exclusiveFileLockWithRetryIfDeadlock;
                anonymousClass1.L$0 = $this$withLock_u24default$iv;
                anonymousClass1.L$1 = closeable;
                anonymousClass1.L$2 = lock2;
                anonymousClass1.label = 3;
                objInvoke = function2.invoke(anonymousClass1);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (lock2 != null) {
                    lock2.release();
                    break;
                }
                kotlin.io.CloseableKt.closeFinally(closeable, null);
                $this$withLock_u24default$iv.unlock(owner$iv);
                return objInvoke;
            case 2:
                i3 = 0;
                closeable = (java.io.Closeable) anonymousClass1.L$2;
                $this$withLock_u24default$iv = (Mutex) anonymousClass1.L$1;
                function2 = (Function1) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    exclusiveFileLockWithRetryIfDeadlock = $result;
                    lock = null;
                    i2 = 0;
                    i = 0;
                    owner$iv = null;
                    lock2 = (FileLock) exclusiveFileLockWithRetryIfDeadlock;
                    anonymousClass1.L$0 = $this$withLock_u24default$iv;
                    anonymousClass1.L$1 = closeable;
                    anonymousClass1.L$2 = lock2;
                    anonymousClass1.label = 3;
                    objInvoke = function2.invoke(anonymousClass1);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (lock2 != null) {
                        lock2.release();
                        break;
                    }
                    kotlin.io.CloseableKt.closeFinally(closeable, null);
                    $this$withLock_u24default$iv.unlock(owner$iv);
                    return objInvoke;
                } catch (Throwable th11) {
                    th = th11;
                    lock2 = null;
                    owner$iv2 = null;
                    if (lock2 != null) {
                        lock2.release();
                    }
                    throw th;
                }
            case 3:
                i3 = 0;
                lock2 = (FileLock) anonymousClass1.L$2;
                closeable = (java.io.Closeable) anonymousClass1.L$1;
                $this$withLock_u24default$iv = (Mutex) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objInvoke = $result;
                    owner$iv = null;
                    if (lock2 != null) {
                        lock2.release();
                        break;
                    }
                    kotlin.io.CloseableKt.closeFinally(closeable, null);
                    $this$withLock_u24default$iv.unlock(owner$iv);
                    return objInvoke;
                } catch (Throwable th12) {
                    th = th12;
                    if (lock2 != null) {
                        lock2.release();
                    }
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:118:0x0185 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:122:0x014b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0096  */
    /* JADX WARN: Code duplicated, block: B:54:0x0107 A[Catch: all -> 0x00f6, TryCatch #10 {all -> 0x00f6, blocks: (B:46:0x00ed, B:54:0x0107, B:56:0x010d, B:64:0x0120), top: B:128:0x00ed }] */
    /* JADX WARN: Code duplicated, block: B:56:0x010d A[Catch: all -> 0x00f6, TryCatch #10 {all -> 0x00f6, blocks: (B:46:0x00ed, B:54:0x0107, B:56:0x010d, B:64:0x0120), top: B:128:0x00ed }] */
    /* JADX WARN: Code duplicated, block: B:58:0x0118  */
    /* JADX WARN: Code duplicated, block: B:59:0x011a  */
    /* JADX WARN: Code duplicated, block: B:62:0x011e  */
    /* JADX WARN: Code duplicated, block: B:64:0x0120 A[Catch: all -> 0x00f6, TRY_LEAVE, TryCatch #10 {all -> 0x00f6, blocks: (B:46:0x00ed, B:54:0x0107, B:56:0x010d, B:64:0x0120), top: B:128:0x00ed }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0121  */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Code duplicated, block: B:83:0x0162  */
    @Override // androidx.datastore.core.InterProcessCoordinator
    public <T> Object tryLock(Function2<? super Boolean, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) throws Throwable {
        C07471 c07471;
        Mutex $this$withTryLock_u24default$iv;
        boolean locked$iv;
        Mutex $this$withTryLock_u24default$iv2;
        Object objInvoke;
        Throwable th;
        int $i$f$withTryLock;
        boolean locked$iv2;
        int i;
        java.io.Closeable closeable;
        FileLock lock;
        boolean z;
        boolean z2;
        FileLock lock2;
        String message;
        boolean z3;
        Object objInvoke2;
        if (continuation instanceof C07471) {
            c07471 = (C07471) continuation;
            if ((c07471.label & Integer.MIN_VALUE) != 0) {
                c07471.label -= Integer.MIN_VALUE;
            } else {
                c07471 = new C07471(continuation);
            }
        } else {
            c07471 = new C07471(continuation);
        }
        Object $result = c07471.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object owner$iv = null;
        switch (c07471.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                $this$withTryLock_u24default$iv = this.inMemoryMutex;
                boolean locked$iv3 = $this$withTryLock_u24default$iv.tryLock(null);
                if (locked$iv3) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(getLockFile());
                        try {
                            FileInputStream lockFileStream = fileInputStream;
                            try {
                                FileLock lock3 = lockFileStream.getChannel().tryLock(0L, Long.MAX_VALUE, true);
                                lock2 = lock3;
                                z2 = true;
                            } catch (IOException ex) {
                                try {
                                    String message2 = ex.getMessage();
                                    if (message2 != null) {
                                        try {
                                            z = StringsKt.startsWith$default(message2, this.LOCK_ERROR_MESSAGE, false, 2, (Object) null);
                                            if (z) {
                                                z2 = true;
                                            } else {
                                                message = ex.getMessage();
                                                if (message != null) {
                                                    z2 = true;
                                                    z3 = StringsKt.startsWith$default(message, DEADLOCK_ERROR_MESSAGE, false, 2, (Object) null);
                                                    if (z3) {
                                                        throw ex;
                                                    }
                                                } else {
                                                    z2 = true;
                                                }
                                                if (z3) {
                                                    throw ex;
                                                }
                                            }
                                            lock2 = null;
                                        } catch (Throwable th2) {
                                            ex = th2;
                                            owner$iv = null;
                                            $i$f$withTryLock = 0;
                                            locked$iv2 = locked$iv3;
                                            i = 0;
                                            closeable = fileInputStream;
                                            lock = null;
                                            if (lock != null) {
                                                try {
                                                    lock.release();
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    locked$iv = locked$iv2;
                                                    fileInputStream = closeable;
                                                    $this$withTryLock_u24default$iv2 = $this$withTryLock_u24default$iv;
                                                    try {
                                                        throw th;
                                                    } catch (Throwable th4) {
                                                        try {
                                                            kotlin.io.CloseableKt.closeFinally(fileInputStream, th);
                                                            throw th4;
                                                        } catch (Throwable th5) {
                                                            th = th5;
                                                        }
                                                    }
                                                }
                                            }
                                            throw ex;
                                        }
                                        break;
                                    }
                                    if (z) {
                                        message = ex.getMessage();
                                        if (message != null) {
                                            z2 = true;
                                            if (StringsKt.startsWith$default(message, DEADLOCK_ERROR_MESSAGE, false, 2, (Object) null)) {
                                            }
                                            if (z3) {
                                                throw ex;
                                            }
                                        } else {
                                            z2 = true;
                                        }
                                        if (z3) {
                                            throw ex;
                                        }
                                    } else {
                                        z2 = true;
                                    }
                                    lock2 = null;
                                } catch (Throwable th6) {
                                    ex = th6;
                                    owner$iv = null;
                                    $i$f$withTryLock = 0;
                                    locked$iv2 = locked$iv3;
                                    i = 0;
                                    closeable = fileInputStream;
                                    lock = null;
                                }
                            } catch (Throwable th7) {
                                ex = th7;
                                owner$iv = null;
                                $i$f$withTryLock = 0;
                                locked$iv2 = locked$iv3;
                                i = 0;
                                closeable = fileInputStream;
                                lock = null;
                                if (lock != null) {
                                    lock.release();
                                }
                                throw ex;
                            }
                            try {
                                Boolean boolBoxBoolean = Boxing.boxBoolean(lock2 != null ? z2 : false);
                                c07471.L$0 = $this$withTryLock_u24default$iv;
                                c07471.L$1 = fileInputStream;
                                c07471.L$2 = lock2;
                                c07471.Z$0 = locked$iv3;
                                c07471.label = 2;
                                objInvoke2 = function2.invoke(boolBoxBoolean, c07471);
                                if (objInvoke2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                owner$iv = null;
                                $i$f$withTryLock = 0;
                                locked$iv2 = locked$iv3;
                                i = 0;
                                closeable = fileInputStream;
                                lock = lock2;
                                if (lock != null) {
                                    try {
                                        lock.release();
                                    } catch (Throwable th8) {
                                        $this$withTryLock_u24default$iv2 = $this$withTryLock_u24default$iv;
                                        fileInputStream = closeable;
                                        locked$iv = locked$iv2;
                                        owner$iv = owner$iv;
                                        th = th8;
                                        throw th;
                                    }
                                }
                                try {
                                    kotlin.io.CloseableKt.closeFinally(closeable, null);
                                    if (locked$iv2) {
                                        $this$withTryLock_u24default$iv.unlock(owner$iv);
                                    }
                                    return objInvoke2;
                                } catch (Throwable th9) {
                                    th = th9;
                                    $this$withTryLock_u24default$iv2 = $this$withTryLock_u24default$iv;
                                    locked$iv = locked$iv2;
                                }
                            } catch (Throwable th10) {
                                ex = th10;
                                owner$iv = null;
                                $i$f$withTryLock = 0;
                                locked$iv2 = locked$iv3;
                                i = 0;
                                closeable = fileInputStream;
                                lock = lock2;
                                if (lock != null) {
                                    lock.release();
                                }
                                throw ex;
                            }
                        } catch (Throwable th11) {
                            th = th11;
                            owner$iv = null;
                            locked$iv = locked$iv3;
                            $this$withTryLock_u24default$iv2 = $this$withTryLock_u24default$iv;
                            throw th;
                        }
                    } catch (Throwable th12) {
                        th = th12;
                        owner$iv = null;
                        locked$iv = locked$iv3;
                        $this$withTryLock_u24default$iv2 = $this$withTryLock_u24default$iv;
                    }
                    break;
                } else {
                    try {
                        Boolean boolBoxBoolean2 = Boxing.boxBoolean(false);
                        c07471.L$0 = $this$withTryLock_u24default$iv;
                        c07471.Z$0 = locked$iv3;
                        c07471.label = 1;
                        objInvoke = function2.invoke(boolBoxBoolean2, c07471);
                        if (objInvoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        owner$iv = null;
                        locked$iv = locked$iv3;
                        $this$withTryLock_u24default$iv2 = $this$withTryLock_u24default$iv;
                        if (locked$iv) {
                            $this$withTryLock_u24default$iv2.unlock(owner$iv);
                        }
                        return objInvoke;
                    } catch (Throwable th13) {
                        th = th13;
                        owner$iv = null;
                        locked$iv = locked$iv3;
                        $this$withTryLock_u24default$iv2 = $this$withTryLock_u24default$iv;
                    }
                }
                if (locked$iv) {
                    $this$withTryLock_u24default$iv2.unlock(owner$iv);
                }
                throw th;
            case 1:
                locked$iv = c07471.Z$0;
                $this$withTryLock_u24default$iv2 = (Mutex) c07471.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objInvoke = $result;
                    if (locked$iv) {
                        $this$withTryLock_u24default$iv2.unlock(owner$iv);
                    }
                    return objInvoke;
                } catch (Throwable th14) {
                    th = th14;
                }
                break;
            case 2:
                i = 0;
                $i$f$withTryLock = 0;
                locked$iv2 = c07471.Z$0;
                lock = (FileLock) c07471.L$2;
                closeable = (java.io.Closeable) c07471.L$1;
                $this$withTryLock_u24default$iv = (Mutex) c07471.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objInvoke2 = $result;
                    if (lock != null) {
                        lock.release();
                        break;
                    }
                    kotlin.io.CloseableKt.closeFinally(closeable, null);
                    if (locked$iv2) {
                        $this$withTryLock_u24default$iv.unlock(owner$iv);
                    }
                    return objInvoke2;
                } catch (Throwable th15) {
                    ex = th15;
                    if (lock != null) {
                        lock.release();
                    }
                    throw ex;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object getVersion(Continuation<? super Integer> continuation) {
        if (!this.lazySharedCounter.isInitialized()) {
            return BuildersKt.withContext(this.context, new MultiProcessCoordinator$getVersion$$inlined$withLazyCounter$1(this, null), continuation);
        }
        SharedCounter it = getSharedCounter();
        return Boxing.boxInt(it.getValue());
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object incrementAndGetVersion(Continuation<? super Integer> continuation) {
        if (!this.lazySharedCounter.isInitialized()) {
            return BuildersKt.withContext(this.context, new MultiProcessCoordinator$incrementAndGetVersion$$inlined$withLazyCounter$1(this, null), continuation);
        }
        SharedCounter it = getSharedCounter();
        return Boxing.boxInt(it.incrementAndGetValue());
    }

    private final File getLockFile() {
        return (File) this.lockFile.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedCounter getSharedCounter() {
        return this.lazySharedCounter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File fileWithSuffix(String suffix) {
        return new File(this.file.getAbsolutePath() + suffix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createIfNotExists(File $this$createIfNotExists) throws IOException {
        createParentDirectories($this$createIfNotExists);
        if (!$this$createIfNotExists.exists()) {
            $this$createIfNotExists.createNewFile();
        }
    }

    private final void createParentDirectories(File $this$createParentDirectories) throws IOException {
        File parent = $this$createParentDirectories.getCanonicalFile().getParentFile();
        if (parent != null) {
            parent.mkdirs();
            if (!parent.isDirectory()) {
                throw new IOException("Unable to create parent directories of " + $this$createParentDirectories);
            }
        }
    }

    private final <T> Object withLazyCounter(Function2<? super SharedCounter, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        if (this.lazySharedCounter.isInitialized()) {
            return function2.invoke(getSharedCounter(), continuation);
        }
        CoroutineContext coroutineContext = this.context;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(function2, this, null);
        InlineMarker.mark(0);
        Object objWithContext = BuildersKt.withContext(coroutineContext, anonymousClass2, continuation);
        InlineMarker.mark(1);
        return objWithContext;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.datastore.core.MultiProcessCoordinator$withLazyCounter$2, reason: invalid class name */
    /* JADX INFO: compiled from: MultiProcessCoordinator.android.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.MultiProcessCoordinator$withLazyCounter$2", f = "MultiProcessCoordinator.android.kt", i = {}, l = {163}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function2<SharedCounter, Continuation<? super T>, Object> $block;
        int label;
        final /* synthetic */ MultiProcessCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function2<? super SharedCounter, ? super Continuation<? super T>, ? extends Object> function2, MultiProcessCoordinator multiProcessCoordinator, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$block = function2;
            this.this$0 = multiProcessCoordinator;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$block, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Function2<SharedCounter, Continuation<? super T>, Object> function2 = this.$block;
                    SharedCounter sharedCounter = this.this$0.getSharedCounter();
                    this.label = 1;
                    Object objInvoke = function2.invoke(sharedCounter, this);
                    return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public final Object invokeSuspend$$forInline(Object $result) {
            return this.$block.invoke(this.this$0.getSharedCounter(), this);
        }
    }

    /* JADX INFO: compiled from: MultiProcessCoordinator.android.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/datastore/core/MultiProcessCoordinator$Companion;", "", "()V", "DEADLOCK_ERROR_MESSAGE", "", "INITIAL_WAIT_MILLIS", "", "MAX_WAIT_MILLIS", "getExclusiveFileLockWithRetryIfDeadlock", "Ljava/nio/channels/FileLock;", "lockFileStream", "Ljava/io/FileOutputStream;", "(Ljava/io/FileOutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't wrap try/catch for region: R(3:32|16|17) */
        /* JADX WARN: Code duplicated, block: B:30:0x008a  */
        /* JADX WARN: Code duplicated, block: B:32:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x005f, code lost:
        
            r6 = r2.getMessage();
            r8 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0065, code lost:
        
            if (r6 != null) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0076, code lost:
        
            r8 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0077, code lost:
        
            if (r8 != false) goto L25;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0079, code lost:
        
            r15.L$0 = r14;
            r15.J$0 = r4;
            r15.label = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0083, code lost:
        
            if (kotlinx.coroutines.DelayKt.delay(r4, r15) == r1) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0085, code lost:
        
            return r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0089, code lost:
        
            throw r2;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0083 -> B:28:0x0086). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object getExclusiveFileLockWithRetryIfDeadlock(java.io.FileOutputStream r14, kotlin.coroutines.Continuation<? super java.nio.channels.FileLock> r15) throws java.io.IOException {
            /*
                r13 = this;
                boolean r0 = r15 instanceof androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1
                if (r0 == 0) goto L14
                r0 = r15
                androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 r0 = (androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r15 = r0.label
                int r15 = r15 - r2
                r0.label = r15
                goto L19
            L14:
                androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 r0 = new androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1
                r0.<init>(r13, r15)
            L19:
                r15 = r0
                java.lang.Object r0 = r15.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r15.label
                r3 = 2
                switch(r2) {
                    case 0: goto L38;
                    case 1: goto L2e;
                    default: goto L26;
                }
            L26:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r15)
                throw r14
            L2e:
                long r4 = r15.J$0
                java.lang.Object r14 = r15.L$0
                java.io.FileOutputStream r14 = (java.io.FileOutputStream) r14
                kotlin.ResultKt.throwOnFailure(r0)
                goto L86
            L38:
                kotlin.ResultKt.throwOnFailure(r0)
                long r4 = androidx.datastore.core.MultiProcessCoordinator.access$getINITIAL_WAIT_MILLIS$cp()
            L3f:
                long r6 = androidx.datastore.core.MultiProcessCoordinator.access$getMAX_WAIT_MILLIS$cp()
                int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            */
            //  java.lang.String r6 = "lockFileStream.getChanne…LUE, /* shared= */ false)"
            /*
                if (r2 > 0) goto L8a
            L4a:
                java.nio.channels.FileChannel r7 = r14.getChannel()     // Catch: java.io.IOException -> L5e
                r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r12 = 0
                r8 = 0
                java.nio.channels.FileLock r2 = r7.lock(r8, r10, r12)     // Catch: java.io.IOException -> L5e
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)     // Catch: java.io.IOException -> L5e
                return r2
            L5e:
                r2 = move-exception
                java.lang.String r6 = r2.getMessage()
                r7 = 1
                r8 = 0
                if (r6 == 0) goto L77
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                java.lang.String r9 = androidx.datastore.core.MultiProcessCoordinator.access$getDEADLOCK_ERROR_MESSAGE$cp()
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9
                r10 = 0
                boolean r6 = kotlin.text.StringsKt.contains$default(r6, r9, r8, r3, r10)
                if (r6 != r7) goto L77
                r8 = r7
            L77:
                if (r8 == 0) goto L89
                r15.L$0 = r14
                r15.J$0 = r4
                r15.label = r7
                java.lang.Object r2 = kotlinx.coroutines.DelayKt.delay(r4, r15)
                if (r2 != r1) goto L86
                return r1
            L86:
                long r6 = (long) r3
                long r4 = r4 * r6
                goto L3f
            L89:
                throw r2
            L8a:
                java.nio.channels.FileChannel r7 = r14.getChannel()
                r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r12 = 0
                r8 = 0
                java.nio.channels.FileLock r1 = r7.lock(r8, r10, r12)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.MultiProcessCoordinator.Companion.getExclusiveFileLockWithRetryIfDeadlock(java.io.FileOutputStream, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
