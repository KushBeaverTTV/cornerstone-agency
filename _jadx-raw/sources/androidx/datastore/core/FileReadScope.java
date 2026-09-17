package androidx.datastore.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FileStorage.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0004J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u000e\u0010\u0011\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Landroidx/datastore/core/FileReadScope;", "T", "Landroidx/datastore/core/ReadScope;", "file", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "(Ljava/io/File;Landroidx/datastore/core/Serializer;)V", "closed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getFile", "()Ljava/io/File;", "getSerializer", "()Landroidx/datastore/core/Serializer;", "checkNotClosed", "", "close", "readData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class FileReadScope<T> implements ReadScope<T> {
    private final java.util.concurrent.atomic.AtomicBoolean closed;
    private final File file;
    private final Serializer<T> serializer;

    /* JADX INFO: renamed from: androidx.datastore.core.FileReadScope$readData$1, reason: invalid class name */
    /* JADX INFO: compiled from: FileStorage.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.FileReadScope", f = "FileStorage.kt", i = {0}, l = {169, 178}, m = "readData$suspendImpl", n = {"$this"}, s = {"L$0"})
    static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FileReadScope<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(FileReadScope<T> fileReadScope, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = fileReadScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FileReadScope.readData$suspendImpl(this.this$0, this);
        }
    }

    @Override // androidx.datastore.core.ReadScope
    public Object readData(Continuation<? super T> continuation) {
        return readData$suspendImpl(this, continuation);
    }

    public FileReadScope(File file, Serializer<T> serializer) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.file = file;
        this.serializer = serializer;
        this.closed = new java.util.concurrent.atomic.AtomicBoolean(false);
    }

    protected final File getFile() {
        return this.file;
    }

    protected final Serializer<T> getSerializer() {
        return this.serializer;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.Closeable] */
    static /* synthetic */ <T> Object readData$suspendImpl(FileReadScope<T> fileReadScope, Continuation<? super T> continuation) {
        AnonymousClass1 anonymousClass1;
        java.io.Closeable closeable;
        Throwable th;
        Object from;
        FileReadScope<T> fileReadScope2;
        Object from2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(fileReadScope, continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(fileReadScope, continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r2 = anonymousClass2.label;
        try {
            try {
                switch (r2) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        fileReadScope2 = fileReadScope;
                        fileReadScope2.checkNotClosed();
                        FileInputStream fileInputStream = new FileInputStream(((FileReadScope) fileReadScope2).file);
                        anonymousClass2.L$0 = fileReadScope2;
                        anonymousClass2.L$1 = fileInputStream;
                        anonymousClass2.label = 1;
                        from2 = ((FileReadScope) fileReadScope2).serializer.readFrom(fileInputStream, anonymousClass2);
                        if (from2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        r2 = fileInputStream;
                        kotlin.io.CloseableKt.closeFinally(r2, null);
                        return from2;
                    case 1:
                        java.io.Closeable closeable2 = (java.io.Closeable) anonymousClass2.L$1;
                        fileReadScope2 = (FileReadScope) anonymousClass2.L$0;
                        ResultKt.throwOnFailure(obj);
                        from2 = obj;
                        r2 = closeable2;
                        kotlin.io.CloseableKt.closeFinally(r2, null);
                        return from2;
                    case 2:
                        closeable = (java.io.Closeable) anonymousClass2.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            from = obj;
                            kotlin.io.CloseableKt.closeFinally(closeable, null);
                            return from;
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                throw th;
                            } catch (Throwable th3) {
                                kotlin.io.CloseableKt.closeFinally(closeable, th);
                                throw th3;
                            }
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (FileNotFoundException e) {
                if (!((FileReadScope) fileReadScope2).file.exists()) {
                    return ((FileReadScope) fileReadScope2).serializer.getDefaultValue();
                }
                FileInputStream fileInputStream2 = new FileInputStream(((FileReadScope) fileReadScope2).file);
                try {
                    anonymousClass2.L$0 = fileInputStream2;
                    anonymousClass2.L$1 = null;
                    anonymousClass2.label = 2;
                    from = ((FileReadScope) fileReadScope2).serializer.readFrom(fileInputStream2, anonymousClass2);
                    if (from == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    closeable = fileInputStream2;
                } catch (Throwable th4) {
                    closeable = fileInputStream2;
                    th = th4;
                    throw th;
                }
            }
        } catch (Throwable th5) {
            try {
                throw th5;
            } catch (Throwable th6) {
                kotlin.io.CloseableKt.closeFinally(r2, th5);
                throw th6;
            }
        }
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
    }

    protected final void checkNotClosed() {
        if (this.closed.get()) {
            throw new IllegalStateException("This scope has already been closed.".toString());
        }
    }
}
