package androidx.datastore.core;

import ai.nous.aurora.domain.model.ComposerChipPolicy;
import androidx.autofill.HintConstants;
import java.io.File;
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

/* JADX INFO: compiled from: FileStorage.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\b\u0010\u0013\u001a\u00020\u000bH\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0016JX\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0001\u0010\u00162B\u0010\u0017\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0018¢\u0006\u0002\b H\u0096@¢\u0006\u0002\u0010!J=\u0010\"\u001a\u00020\u000b2-\u0010\u0017\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0#¢\u0006\u0002\b H\u0096@¢\u0006\u0002\u0010%J\f\u0010&\u001a\u00020\u000b*\u00020\u0004H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/datastore/core/FileStorageConnection;", "T", "Landroidx/datastore/core/StorageConnection;", "file", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "onClose", "Lkotlin/Function0;", "", "(Ljava/io/File;Landroidx/datastore/core/Serializer;Landroidx/datastore/core/InterProcessCoordinator;Lkotlin/jvm/functions/Function0;)V", "closed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "transactionMutex", "Lkotlinx/coroutines/sync/Mutex;", "checkNotClosed", "close", "readScope", "R", "block", "Lkotlin/Function3;", "Landroidx/datastore/core/ReadScope;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "locked", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeScope", "Lkotlin/Function2;", "Landroidx/datastore/core/WriteScope;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createParentDirectories", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FileStorageConnection<T> implements StorageConnection<T> {
    private final java.util.concurrent.atomic.AtomicBoolean closed;
    private final InterProcessCoordinator coordinator;
    private final File file;
    private final Function0<Unit> onClose;
    private final Serializer<T> serializer;
    private final Mutex transactionMutex;

    /* JADX INFO: renamed from: androidx.datastore.core.FileStorageConnection$readScope$1, reason: invalid class name */
    /* JADX INFO: compiled from: FileStorage.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.FileStorageConnection", f = "FileStorage.kt", i = {0, 0, 0}, l = {101}, m = "readScope", n = {"this", "$this$use$iv", "lock"}, s = {"L$0", "L$1", "Z$0"})
    static final class AnonymousClass1<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FileStorageConnection<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(FileStorageConnection<T> fileStorageConnection, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = fileStorageConnection;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readScope(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.FileStorageConnection$writeScope$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FileStorage.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.FileStorageConnection", f = "FileStorage.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {213, ComposerChipPolicy.CHIP_MAX_WIDTH_DP}, m = "writeScope", n = {"this", "block", "$this$withLock_u24default$iv", "this", "$this$withLock_u24default$iv", "scratchFile", "$this$use$iv"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    static final class C07461 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FileStorageConnection<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07461(FileStorageConnection<T> fileStorageConnection, Continuation<? super C07461> continuation) {
            super(continuation);
            this.this$0 = fileStorageConnection;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.writeScope(null, this);
        }
    }

    public FileStorageConnection(File file, Serializer<T> serializer, InterProcessCoordinator coordinator, Function0<Unit> onClose) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(coordinator, "coordinator");
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        this.file = file;
        this.serializer = serializer;
        this.coordinator = coordinator;
        this.onClose = onClose;
        this.closed = new java.util.concurrent.atomic.AtomicBoolean(false);
        this.transactionMutex = MutexKt.Mutex$default(false, 1, null);
    }

    @Override // androidx.datastore.core.StorageConnection
    public InterProcessCoordinator getCoordinator() {
        return this.coordinator;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x008d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:35:0x008f  */
    /* JADX WARN: Code duplicated, block: B:38:0x0096 A[Catch: all -> 0x00a8, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x00a8, blocks: (B:38:0x0096, B:47:0x00a7, B:45:0x00a2, B:18:0x0053, B:42:0x009c), top: B:60:0x0053, inners: #0 }] */
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
        FileStorageConnection<T> fileStorageConnection;
        boolean lock;
        FileReadScope $this$use$iv;
        Throwable thrown$iv;
        Object objInvoke;
        Throwable thrown$iv2;
        Throwable thrown$iv3;
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
                fileStorageConnection = this;
                fileStorageConnection.checkNotClosed();
                lock = Mutex.DefaultImpls.tryLock$default(fileStorageConnection.transactionMutex, null, 1, null);
                try {
                    $this$use$iv = new FileReadScope(fileStorageConnection.file, fileStorageConnection.serializer);
                    thrown$iv = null;
                    try {
                        FileReadScope fileReadScope = $this$use$iv;
                        Boolean boolBoxBoolean = Boxing.boxBoolean(lock);
                        anonymousClass2.L$0 = fileStorageConnection;
                        anonymousClass2.L$1 = $this$use$iv;
                        anonymousClass2.Z$0 = lock;
                        anonymousClass2.label = 1;
                        objInvoke = function3.invoke(fileReadScope, boolBoxBoolean, anonymousClass2);
                        if (objInvoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        thrown$iv3 = thrown$iv;
                        try {
                            $this$use$iv.close();
                            break;
                        } catch (Throwable th) {
                            thrown$iv3 = th;
                        }
                        if (thrown$iv3 == null) {
                            throw thrown$iv3;
                        }
                        if (lock) {
                            Mutex.DefaultImpls.unlock$default(fileStorageConnection.transactionMutex, null, 1, null);
                        }
                        return objInvoke;
                    } catch (Throwable th2) {
                        t$iv = th2;
                        thrown$iv2 = t$iv;
                        try {
                            $this$use$iv.close();
                            throw thrown$iv2;
                        } catch (Throwable t$iv) {
                            ExceptionsKt.addSuppressed(thrown$iv2, t$iv);
                            throw thrown$iv2;
                        }
                    }
                } catch (Throwable th3) {
                    if (lock) {
                        Mutex.DefaultImpls.unlock$default(fileStorageConnection.transactionMutex, null, 1, null);
                    }
                    throw th3;
                }
            case 1:
                lock = anonymousClass2.Z$0;
                $this$use$iv = (Closeable) anonymousClass2.L$1;
                fileStorageConnection = (FileStorageConnection) anonymousClass2.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objInvoke = $result;
                    thrown$iv = null;
                    thrown$iv3 = thrown$iv;
                    $this$use$iv.close();
                    if (thrown$iv3 == null) {
                        throw thrown$iv3;
                    }
                    if (lock) {
                        Mutex.DefaultImpls.unlock$default(fileStorageConnection.transactionMutex, null, 1, null);
                    }
                    return objInvoke;
                } catch (Throwable th4) {
                    t$iv = th4;
                    thrown$iv2 = t$iv;
                    $this$use$iv.close();
                    throw thrown$iv2;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00d3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:30:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:38:0x00e8 A[Catch: IOException -> 0x0141, all -> 0x014d, TRY_ENTER, TryCatch #2 {IOException -> 0x0141, blocks: (B:38:0x00e8, B:40:0x00ee, B:43:0x00f7, B:44:0x0121, B:50:0x012e, B:60:0x0140, B:58:0x013b, B:25:0x00b1), top: B:74:0x0029, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ee A[Catch: IOException -> 0x0141, all -> 0x014d, TryCatch #2 {IOException -> 0x0141, blocks: (B:38:0x00e8, B:40:0x00ee, B:43:0x00f7, B:44:0x0121, B:50:0x012e, B:60:0x0140, B:58:0x013b, B:25:0x00b1), top: B:74:0x0029, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x012e A[Catch: IOException -> 0x0141, all -> 0x014d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {IOException -> 0x0141, blocks: (B:38:0x00e8, B:40:0x00ee, B:43:0x00f7, B:44:0x0121, B:50:0x012e, B:60:0x0140, B:58:0x013b, B:25:0x00b1), top: B:74:0x0029, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x014e: INVOKE 
  (r11v0 ?? I:kotlinx.coroutines.sync.Mutex A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)])
  (r10 I:java.lang.Object A[D('owner$iv' java.lang.Object)])
 INTERFACE call: kotlinx.coroutines.sync.Mutex.unlock(java.lang.Object):void A[MD:(java.lang.Object):void (m)], block:B:68:0x014e */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x014e: INVOKE 
  (r11 I:kotlinx.coroutines.sync.Mutex A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)])
  (r10 I:java.lang.Object A[D('owner$iv' java.lang.Object)])
 INTERFACE call: kotlinx.coroutines.sync.Mutex.unlock(java.lang.Object):void A[MD:(java.lang.Object):void (m)], block:B:68:0x014e */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0142: INVOKE (r2 I:boolean) = (r9 I:java.io.File A[D('scratchFile' java.io.File)]) VIRTUAL call: java.io.File.exists():boolean A[Catch: all -> 0x014d, MD:():boolean (c), TRY_ENTER] (LINE:129), block:B:62:0x0142 */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.io.File] */
    @Override // androidx.datastore.core.StorageConnection
    public Object writeScope(Function2<? super WriteScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) throws IOException {
        C07461 c07461;
        Mutex $this$withLock_u24default$iv;
        Object owner$iv;
        ?? Exists;
        Function2<? super WriteScope<T>, ? super Continuation<? super Unit>, ? extends Object> function3;
        FileStorageConnection<T> fileStorageConnection;
        Mutex $this$withLock_u24default$iv2;
        Object owner$iv2;
        File scratchFile;
        Closeable $this$use$iv;
        FileWriteScope it;
        Throwable thrown$iv;
        Throwable thrown$iv2;
        if (continuation instanceof C07461) {
            c07461 = (C07461) continuation;
            if ((c07461.label & Integer.MIN_VALUE) != 0) {
                c07461.label -= Integer.MIN_VALUE;
            } else {
                c07461 = new C07461(this, continuation);
            }
        } else {
            c07461 = new C07461(this, continuation);
        }
        Object $result = c07461.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            try {
                switch (c07461.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        function3 = function2;
                        checkNotClosed();
                        createParentDirectories(this.file);
                        Mutex $this$withLock_u24default$iv3 = this.transactionMutex;
                        c07461.L$0 = this;
                        c07461.L$1 = function3;
                        c07461.L$2 = $this$withLock_u24default$iv3;
                        c07461.label = 1;
                        if ($this$withLock_u24default$iv3.lock(null, c07461) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        fileStorageConnection = this;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        owner$iv2 = null;
                        scratchFile = new File(fileStorageConnection.file.getAbsolutePath() + ".tmp");
                        $this$use$iv = new FileWriteScope(scratchFile, fileStorageConnection.serializer);
                        try {
                            it = (FileWriteScope) $this$use$iv;
                            c07461.L$0 = fileStorageConnection;
                            c07461.L$1 = $this$withLock_u24default$iv2;
                            c07461.L$2 = scratchFile;
                            c07461.L$3 = $this$use$iv;
                            c07461.label = 2;
                            if (function3.invoke(it, c07461) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
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
                            if (scratchFile.exists() && !FileMoves_androidKt.atomicMoveTo(scratchFile, fileStorageConnection.file)) {
                                throw new IOException("Unable to rename " + scratchFile + " to " + fileStorageConnection.file + ". This likely means that there are multiple instances of DataStore for this file. Ensure that you are only creating a single instance of datastore for this file.");
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
                    case 1:
                        Mutex $this$withLock_u24default$iv4 = (Mutex) c07461.L$2;
                        function3 = (Function2) c07461.L$1;
                        FileStorageConnection<T> fileStorageConnection2 = (FileStorageConnection) c07461.L$0;
                        ResultKt.throwOnFailure($result);
                        owner$iv2 = null;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv4;
                        fileStorageConnection = fileStorageConnection2;
                        scratchFile = new File(fileStorageConnection.file.getAbsolutePath() + ".tmp");
                        $this$use$iv = new FileWriteScope(scratchFile, fileStorageConnection.serializer);
                        it = (FileWriteScope) $this$use$iv;
                        c07461.L$0 = fileStorageConnection;
                        c07461.L$1 = $this$withLock_u24default$iv2;
                        c07461.L$2 = scratchFile;
                        c07461.L$3 = $this$use$iv;
                        c07461.label = 2;
                        if (function3.invoke(it, c07461) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        thrown$iv = null;
                        Unit unit3 = Unit.INSTANCE;
                        $this$use$iv.close();
                        if (thrown$iv != null) {
                            throw thrown$iv;
                        }
                        if (scratchFile.exists()) {
                            throw new IOException("Unable to rename " + scratchFile + " to " + fileStorageConnection.file + ". This likely means that there are multiple instances of DataStore for this file. Ensure that you are only creating a single instance of datastore for this file.");
                        }
                        Unit unit4 = Unit.INSTANCE;
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return Unit.INSTANCE;
                    case 2:
                        thrown$iv = null;
                        $this$use$iv = (Closeable) c07461.L$3;
                        scratchFile = (File) c07461.L$2;
                        owner$iv2 = null;
                        $this$withLock_u24default$iv2 = (Mutex) c07461.L$1;
                        fileStorageConnection = (FileStorageConnection) c07461.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            Unit unit5 = Unit.INSTANCE;
                            $this$use$iv.close();
                            if (thrown$iv != null) {
                                throw thrown$iv;
                            }
                            if (scratchFile.exists()) {
                                throw new IOException("Unable to rename " + scratchFile + " to " + fileStorageConnection.file + ". This likely means that there are multiple instances of DataStore for this file. Ensure that you are only creating a single instance of datastore for this file.");
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
            } catch (IOException ex) {
                if (Exists.exists()) {
                    Exists.delete();
                }
                throw ex;
            }
        } catch (Throwable ex2) {
            $this$withLock_u24default$iv.unlock(owner$iv);
            throw ex2;
        }
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
        this.onClose.invoke();
    }

    private final void checkNotClosed() {
        if (this.closed.get()) {
            throw new IllegalStateException("StorageConnection has already been disposed.".toString());
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
}
