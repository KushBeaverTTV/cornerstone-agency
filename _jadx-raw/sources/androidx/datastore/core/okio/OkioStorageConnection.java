package androidx.datastore.core.okio;

import androidx.autofill.HintConstants;
import androidx.datastore.core.Closeable;
import androidx.datastore.core.InterProcessCoordinator;
import androidx.datastore.core.ReadScope;
import androidx.datastore.core.StorageConnection;
import androidx.datastore.core.WriteScope;
import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.FileSystem;
import okio.Path;

/* JADX INFO: compiled from: OkioStorage.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B9\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\b\u0010\u0015\u001a\u00020\rH\u0002J\b\u0010\u0016\u001a\u00020\rH\u0016JX\u0010\u0017\u001a\u0002H\u0018\"\u0004\b\u0001\u0010\u00182B\u0010\u0019\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180 \u0012\u0006\u0012\u0004\u0018\u00010!0\u001a¢\u0006\u0002\b\"H\u0096@¢\u0006\u0002\u0010#J=\u0010$\u001a\u00020\r2-\u0010\u0019\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0 \u0012\u0006\u0012\u0004\u0018\u00010!0%¢\u0006\u0002\b\"H\u0096@¢\u0006\u0002\u0010'R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Landroidx/datastore/core/okio/OkioStorageConnection;", "T", "Landroidx/datastore/core/StorageConnection;", "fileSystem", "Lokio/FileSystem;", "path", "Lokio/Path;", "serializer", "Landroidx/datastore/core/okio/OkioSerializer;", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "onClose", "Lkotlin/Function0;", "", "(Lokio/FileSystem;Lokio/Path;Landroidx/datastore/core/okio/OkioSerializer;Landroidx/datastore/core/InterProcessCoordinator;Lkotlin/jvm/functions/Function0;)V", "closed", "Landroidx/datastore/core/okio/AtomicBoolean;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "transactionMutex", "Lkotlinx/coroutines/sync/Mutex;", "checkNotClosed", "close", "readScope", "R", "block", "Lkotlin/Function3;", "Landroidx/datastore/core/ReadScope;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "locked", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeScope", "Lkotlin/Function2;", "Landroidx/datastore/core/WriteScope;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core-okio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OkioStorageConnection<T> implements StorageConnection<T> {
    private final AtomicBoolean closed;
    private final InterProcessCoordinator coordinator;
    private final FileSystem fileSystem;
    private final Function0<Unit> onClose;
    private final Path path;
    private final OkioSerializer<T> serializer;
    private final Mutex transactionMutex;

    /* JADX INFO: renamed from: androidx.datastore.core.okio.OkioStorageConnection$readScope$1, reason: invalid class name */
    /* JADX INFO: compiled from: OkioStorage.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.okio.OkioStorageConnection", f = "OkioStorage.kt", i = {0, 0, 0}, l = {113}, m = "readScope", n = {"this", "$this$use$iv", "lock"}, s = {"L$0", "L$1", "Z$0"})
    static final class AnonymousClass1<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ OkioStorageConnection<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(OkioStorageConnection<T> okioStorageConnection, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = okioStorageConnection;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readScope(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.okio.OkioStorageConnection$writeScope$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OkioStorage.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.okio.OkioStorageConnection", f = "OkioStorage.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {231, 137}, m = "writeScope", n = {"this", "block", "parentDir", "$this$withLock_u24default$iv", "this", "$this$withLock_u24default$iv", "scratchPath", "$this$use$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    static final class C07501 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ OkioStorageConnection<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07501(OkioStorageConnection<T> okioStorageConnection, Continuation<? super C07501> continuation) {
            super(continuation);
            this.this$0 = okioStorageConnection;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.writeScope(null, this);
        }
    }

    public OkioStorageConnection(FileSystem fileSystem, Path path, OkioSerializer<T> serializer, InterProcessCoordinator coordinator, Function0<Unit> onClose) {
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(coordinator, "coordinator");
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        this.fileSystem = fileSystem;
        this.path = path;
        this.serializer = serializer;
        this.coordinator = coordinator;
        this.onClose = onClose;
        this.closed = new AtomicBoolean(false);
        this.transactionMutex = MutexKt.Mutex$default(false, 1, null);
    }

    @Override // androidx.datastore.core.StorageConnection
    public InterProcessCoordinator getCoordinator() {
        return this.coordinator;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0089 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:33:0x008b  */
    /* JADX WARN: Code duplicated, block: B:36:0x0092 A[Catch: all -> 0x00a4, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00a4, blocks: (B:36:0x0092, B:45:0x00a3, B:43:0x009e, B:18:0x0052, B:40:0x0098), top: B:54:0x0052, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Undo finally extract visitor
    java.lang.NullPointerException: Cannot invoke "Object.hashCode()" because "this.second" is null
    	at jadx.core.utils.Pair.hashCode(Pair.java:35)
    	at java.base/java.util.HashMap.hash(HashMap.java:338)
    	at java.base/java.util.HashMap.getNode(HashMap.java:576)
    	at java.base/java.util.HashMap.containsKey(HashMap.java:602)
    	at jadx.core.dex.visitors.finaly.traverser.state.TraverserGlobalCommonState.hasBlocksBeenCached(TraverserGlobalCommonState.java:35)
    	at jadx.core.dex.visitors.finaly.traverser.handlers.MergePathActivePathTraverserHandler.handle(MergePathActivePathTraverserHandler.java:174)
    	at jadx.core.dex.visitors.finaly.traverser.handlers.AbstractActivePathTraverserHandler.process(AbstractActivePathTraverserHandler.java:19)
    	at jadx.core.dex.visitors.finaly.traverser.TraverserController.processHandlerImplementations(TraverserController.java:43)
    	at jadx.core.dex.visitors.finaly.traverser.TraverserController.advance(TraverserController.java:156)
    	at jadx.core.dex.visitors.finaly.traverser.TraverserController.process(TraverserController.java:79)
    	at jadx.core.dex.visitors.finaly.MarkFinallyVisitor.findCommonInsns(MarkFinallyVisitor.java:404)
    	at jadx.core.dex.visitors.finaly.MarkFinallyVisitor.extractFinally(MarkFinallyVisitor.java:284)
    	at jadx.core.dex.visitors.finaly.MarkFinallyVisitor.processTryBlock(MarkFinallyVisitor.java:202)
    	at jadx.core.dex.visitors.finaly.MarkFinallyVisitor.visit(MarkFinallyVisitor.java:135)
     */
    @Override // androidx.datastore.core.StorageConnection
    public <R> Object readScope(Function3<? super ReadScope<T>, ? super Boolean, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super R> continuation) {
        AnonymousClass1 anonymousClass1;
        OkioStorageConnection<T> okioStorageConnection;
        boolean lock;
        OkioReadScope $this$use$iv;
        Throwable th;
        Object objInvoke;
        Throwable thrown$iv;
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
                okioStorageConnection = this;
                okioStorageConnection.checkNotClosed();
                lock = Mutex.DefaultImpls.tryLock$default(okioStorageConnection.transactionMutex, null, 1, null);
                try {
                    $this$use$iv = new OkioReadScope(okioStorageConnection.fileSystem, okioStorageConnection.path, okioStorageConnection.serializer);
                    th = null;
                    try {
                        OkioReadScope okioReadScope = $this$use$iv;
                        Boolean boolBoxBoolean = Boxing.boxBoolean(lock);
                        anonymousClass2.L$0 = okioStorageConnection;
                        anonymousClass2.L$1 = $this$use$iv;
                        anonymousClass2.Z$0 = lock;
                        anonymousClass2.label = 1;
                        objInvoke = function3.invoke(okioReadScope, boolBoxBoolean, anonymousClass2);
                        if (objInvoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        try {
                            $this$use$iv.close();
                            break;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                        if (th == null) {
                            throw th;
                        }
                        if (lock) {
                            Mutex.DefaultImpls.unlock$default(okioStorageConnection.transactionMutex, null, 1, null);
                        }
                        return objInvoke;
                    } catch (Throwable th3) {
                        t$iv = th3;
                        thrown$iv = t$iv;
                        try {
                            $this$use$iv.close();
                            throw thrown$iv;
                        } catch (Throwable t$iv) {
                            ExceptionsKt.addSuppressed(thrown$iv, t$iv);
                            throw thrown$iv;
                        }
                    }
                } catch (Throwable th4) {
                    if (lock) {
                        Mutex.DefaultImpls.unlock$default(okioStorageConnection.transactionMutex, null, 1, null);
                    }
                    throw th4;
                }
            case 1:
                lock = anonymousClass2.Z$0;
                $this$use$iv = (Closeable) anonymousClass2.L$1;
                okioStorageConnection = (OkioStorageConnection) anonymousClass2.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objInvoke = $result;
                    th = null;
                    $this$use$iv.close();
                    if (th == null) {
                        throw th;
                    }
                    if (lock) {
                        Mutex.DefaultImpls.unlock$default(okioStorageConnection.transactionMutex, null, 1, null);
                    }
                    return objInvoke;
                } catch (Throwable th5) {
                    t$iv = th5;
                    thrown$iv = t$iv;
                    $this$use$iv.close();
                    throw thrown$iv;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00ec A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:39:0x0107 A[Catch: IOException -> 0x013b, all -> 0x0153, TRY_ENTER, TryCatch #3 {all -> 0x0153, blocks: (B:39:0x0107, B:41:0x010f, B:43:0x0117, B:47:0x0122, B:62:0x0141, B:64:0x014a, B:68:0x0152, B:57:0x013a, B:55:0x0135, B:25:0x00a3, B:26:0x00c1), top: B:80:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:41:0x010f A[Catch: IOException -> 0x013b, all -> 0x0153, TRY_LEAVE, TryCatch #3 {all -> 0x0153, blocks: (B:39:0x0107, B:41:0x010f, B:43:0x0117, B:47:0x0122, B:62:0x0141, B:64:0x014a, B:68:0x0152, B:57:0x013a, B:55:0x0135, B:25:0x00a3, B:26:0x00c1), top: B:80:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:47:0x0122 A[Catch: IOException -> 0x013b, all -> 0x0153, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0153, blocks: (B:39:0x0107, B:41:0x010f, B:43:0x0117, B:47:0x0122, B:62:0x0141, B:64:0x014a, B:68:0x0152, B:57:0x013a, B:55:0x0135, B:25:0x00a3, B:26:0x00c1), top: B:80:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0154: INVOKE 
  (r11v0 ?? I:kotlinx.coroutines.sync.Mutex A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)])
  (r10 I:java.lang.Object A[D('owner$iv' java.lang.Object)])
 INTERFACE call: kotlinx.coroutines.sync.Mutex.unlock(java.lang.Object):void A[MD:(java.lang.Object):void (m)], block:B:70:0x0154 */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0154: INVOKE 
  (r11 I:kotlinx.coroutines.sync.Mutex A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)])
  (r10 I:java.lang.Object A[D('owner$iv' java.lang.Object)])
 INTERFACE call: kotlinx.coroutines.sync.Mutex.unlock(java.lang.Object):void A[MD:(java.lang.Object):void (m)], block:B:70:0x0154 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x013d: MOVE (r7 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY] A[D('scratchPath' okio.Path)]), block:B:59:0x013c */
    @Override // androidx.datastore.core.StorageConnection
    public Object writeScope(Function2<? super WriteScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) throws IOException {
        C07501 c07501;
        Path scratchPath;
        Path scratchPath2;
        Mutex $this$withLock_u24default$iv;
        Object owner$iv;
        Function2<? super WriteScope<T>, ? super Continuation<? super Unit>, ? extends Object> function3;
        Path parentDir;
        OkioStorageConnection<T> okioStorageConnection;
        Mutex $this$withLock_u24default$iv2;
        Object owner$iv2;
        Closeable $this$use$iv;
        OkioWriteScope it;
        Path scratchPath3;
        Throwable thrown$iv;
        Throwable thrown$iv2;
        if (continuation instanceof C07501) {
            c07501 = (C07501) continuation;
            if ((c07501.label & Integer.MIN_VALUE) != 0) {
                c07501.label -= Integer.MIN_VALUE;
            } else {
                c07501 = new C07501(this, continuation);
            }
        } else {
            c07501 = new C07501(this, continuation);
        }
        Object $result = c07501.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            try {
                switch (c07501.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        function3 = function2;
                        checkNotClosed();
                        Path pathParent = this.path.parent();
                        if (pathParent == null) {
                            throw new IllegalStateException("must have a parent path".toString());
                        }
                        parentDir = pathParent;
                        this.fileSystem.createDirectories(parentDir, false);
                        Mutex $this$withLock_u24default$iv3 = this.transactionMutex;
                        c07501.L$0 = this;
                        c07501.L$1 = function3;
                        c07501.L$2 = parentDir;
                        c07501.L$3 = $this$withLock_u24default$iv3;
                        c07501.label = 1;
                        if ($this$withLock_u24default$iv3.lock(null, c07501) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        okioStorageConnection = this;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        owner$iv2 = null;
                        scratchPath2 = parentDir.resolve(okioStorageConnection.path.name() + ".tmp");
                        try {
                            okioStorageConnection.fileSystem.delete(scratchPath2, false);
                            $this$use$iv = new OkioWriteScope(okioStorageConnection.fileSystem, scratchPath2, okioStorageConnection.serializer);
                            try {
                                it = (OkioWriteScope) $this$use$iv;
                                c07501.L$0 = okioStorageConnection;
                                c07501.L$1 = $this$withLock_u24default$iv2;
                                c07501.L$2 = scratchPath2;
                                c07501.L$3 = $this$use$iv;
                                c07501.label = 2;
                                if (function3.invoke(it, c07501) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                scratchPath3 = scratchPath2;
                                thrown$iv = null;
                                Unit unit = Unit.INSTANCE;
                                try {
                                    $this$use$iv.close();
                                    break;
                                } catch (Throwable t$iv) {
                                    thrown$iv = t$iv;
                                }
                                if (thrown$iv != null) {
                                    throw thrown$iv;
                                }
                                if (okioStorageConnection.fileSystem.exists(scratchPath3)) {
                                    okioStorageConnection.fileSystem.atomicMove(scratchPath3, okioStorageConnection.path);
                                }
                                Unit unit2 = Unit.INSTANCE;
                                $this$withLock_u24default$iv2.unlock(owner$iv2);
                                return Unit.INSTANCE;
                            } catch (Throwable th) {
                                t$iv = th;
                                thrown$iv2 = t$iv;
                                try {
                                    $this$use$iv.close();
                                    throw thrown$iv2;
                                } catch (Throwable t$iv2) {
                                    ExceptionsKt.addSuppressed(thrown$iv2, t$iv2);
                                    throw thrown$iv2;
                                }
                            }
                        } catch (IOException e) {
                            e = e;
                            IOException ex = e;
                            if (!okioStorageConnection.fileSystem.exists(scratchPath2)) {
                                throw ex;
                            }
                            try {
                                okioStorageConnection.fileSystem.delete(scratchPath2);
                                throw ex;
                            } catch (IOException e2) {
                                throw ex;
                            }
                        }
                    case 1:
                        Mutex $this$withLock_u24default$iv4 = (Mutex) c07501.L$3;
                        parentDir = (Path) c07501.L$2;
                        function3 = (Function2) c07501.L$1;
                        OkioStorageConnection<T> okioStorageConnection2 = (OkioStorageConnection) c07501.L$0;
                        ResultKt.throwOnFailure($result);
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv4;
                        okioStorageConnection = okioStorageConnection2;
                        owner$iv2 = null;
                        scratchPath2 = parentDir.resolve(okioStorageConnection.path.name() + ".tmp");
                        okioStorageConnection.fileSystem.delete(scratchPath2, false);
                        $this$use$iv = new OkioWriteScope(okioStorageConnection.fileSystem, scratchPath2, okioStorageConnection.serializer);
                        it = (OkioWriteScope) $this$use$iv;
                        c07501.L$0 = okioStorageConnection;
                        c07501.L$1 = $this$withLock_u24default$iv2;
                        c07501.L$2 = scratchPath2;
                        c07501.L$3 = $this$use$iv;
                        c07501.label = 2;
                        if (function3.invoke(it, c07501) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        scratchPath3 = scratchPath2;
                        thrown$iv = null;
                        Unit unit3 = Unit.INSTANCE;
                        $this$use$iv.close();
                        if (thrown$iv != null) {
                            throw thrown$iv;
                        }
                        if (okioStorageConnection.fileSystem.exists(scratchPath3)) {
                            okioStorageConnection.fileSystem.atomicMove(scratchPath3, okioStorageConnection.path);
                        }
                        Unit unit4 = Unit.INSTANCE;
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return Unit.INSTANCE;
                    case 2:
                        thrown$iv = null;
                        $this$use$iv = (Closeable) c07501.L$3;
                        scratchPath3 = (Path) c07501.L$2;
                        owner$iv2 = null;
                        $this$withLock_u24default$iv2 = (Mutex) c07501.L$1;
                        okioStorageConnection = (OkioStorageConnection) c07501.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            Unit unit5 = Unit.INSTANCE;
                            $this$use$iv.close();
                            if (thrown$iv != null) {
                                throw thrown$iv;
                            }
                            if (okioStorageConnection.fileSystem.exists(scratchPath3)) {
                                okioStorageConnection.fileSystem.atomicMove(scratchPath3, okioStorageConnection.path);
                            }
                            Unit unit6 = Unit.INSTANCE;
                            $this$withLock_u24default$iv2.unlock(owner$iv2);
                            return Unit.INSTANCE;
                        } catch (Throwable th2) {
                            t$iv = th2;
                            thrown$iv2 = t$iv;
                            $this$use$iv.close();
                            throw thrown$iv2;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th3) {
                $this$withLock_u24default$iv.unlock(owner$iv);
                throw th3;
            }
        } catch (IOException e3) {
            e = e3;
            scratchPath2 = scratchPath;
        }
    }

    private final void checkNotClosed() {
        if (this.closed.get()) {
            throw new IllegalStateException("StorageConnection has already been disposed.".toString());
        }
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
        this.onClose.invoke();
    }
}
