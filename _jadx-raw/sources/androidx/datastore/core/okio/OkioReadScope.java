package androidx.datastore.core.okio;

import androidx.datastore.core.ReadScope;
import java.io.Closeable;
import java.io.FileNotFoundException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

/* JADX INFO: compiled from: OkioStorage.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0013H\u0004J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u000e\u0010\u0015\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Landroidx/datastore/core/okio/OkioReadScope;", "T", "Landroidx/datastore/core/ReadScope;", "fileSystem", "Lokio/FileSystem;", "path", "Lokio/Path;", "serializer", "Landroidx/datastore/core/okio/OkioSerializer;", "(Lokio/FileSystem;Lokio/Path;Landroidx/datastore/core/okio/OkioSerializer;)V", "closed", "Landroidx/datastore/core/okio/AtomicBoolean;", "getFileSystem", "()Lokio/FileSystem;", "getPath", "()Lokio/Path;", "getSerializer", "()Landroidx/datastore/core/okio/OkioSerializer;", "checkClose", "", "close", "readData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core-okio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class OkioReadScope<T> implements ReadScope<T> {
    private final AtomicBoolean closed;
    private final FileSystem fileSystem;
    private final Path path;
    private final OkioSerializer<T> serializer;

    /* JADX INFO: renamed from: androidx.datastore.core.okio.OkioReadScope$readData$1, reason: invalid class name */
    /* JADX INFO: compiled from: OkioStorage.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.okio.OkioReadScope", f = "OkioStorage.kt", i = {0, 0}, l = {180}, m = "readData$suspendImpl", n = {"$this", "$this$use$iv$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ OkioReadScope<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(OkioReadScope<T> okioReadScope, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = okioReadScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OkioReadScope.readData$suspendImpl(this.this$0, this);
        }
    }

    @Override // androidx.datastore.core.ReadScope
    public Object readData(Continuation<? super T> continuation) {
        return readData$suspendImpl(this, continuation);
    }

    public OkioReadScope(FileSystem fileSystem, Path path, OkioSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.fileSystem = fileSystem;
        this.path = path;
        this.serializer = serializer;
        this.closed = new AtomicBoolean(false);
    }

    protected final FileSystem getFileSystem() {
        return this.fileSystem;
    }

    protected final Path getPath() {
        return this.path;
    }

    protected final OkioSerializer<T> getSerializer() {
        return this.serializer;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x00a1 A[Catch: FileNotFoundException -> 0x00a7, TryCatch #3 {FileNotFoundException -> 0x00a7, blocks: (B:41:0x00a1, B:42:0x00a6, B:38:0x009a, B:18:0x004b, B:35:0x0094), top: B:55:0x0022, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00a6 A[Catch: FileNotFoundException -> 0x00a7, TRY_LEAVE, TryCatch #3 {FileNotFoundException -> 0x00a7, blocks: (B:41:0x00a1, B:42:0x00a6, B:38:0x009a, B:18:0x004b, B:35:0x0094), top: B:55:0x0022, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:53:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    static /* synthetic */ <T> Object readData$suspendImpl(OkioReadScope<T> okioReadScope, Continuation<? super T> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        OkioReadScope<T> okioReadScope2;
        BufferedSource $this$use$iv$iv;
        Object result$iv$iv;
        Object from;
        Object obj;
        Throwable th;
        Throwable thrown$iv$iv;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(okioReadScope, continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(okioReadScope, continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (anonymousClass2.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    okioReadScope2 = okioReadScope;
                    okioReadScope2.checkClose();
                    FileSystem this_$iv = ((OkioReadScope) okioReadScope2).fileSystem;
                    Path file$iv = ((OkioReadScope) okioReadScope2).path;
                    $this$use$iv$iv = Okio.buffer(this_$iv.source(file$iv));
                    try {
                        BufferedSource $this$readData_u24lambda_u240 = $this$use$iv$iv;
                        OkioSerializer<T> okioSerializer = ((OkioReadScope) okioReadScope2).serializer;
                        anonymousClass2.L$0 = okioReadScope2;
                        anonymousClass2.L$1 = $this$use$iv$iv;
                        anonymousClass2.label = 1;
                        from = okioSerializer.readFrom($this$readData_u24lambda_u240, anonymousClass2);
                        if (from == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = null;
                        th = null;
                        thrown$iv$iv = th;
                        result$iv$iv = from;
                        if ($this$use$iv$iv != null) {
                            try {
                                $this$use$iv$iv.close();
                            } catch (Throwable th2) {
                                thrown$iv$iv = th2;
                            }
                            break;
                        }
                        if (thrown$iv$iv == null) {
                            throw thrown$iv$iv;
                        }
                        Intrinsics.checkNotNull(result$iv$iv);
                        return result$iv$iv;
                    } catch (Throwable th3) {
                        t$iv$iv = th3;
                        result$iv$iv = null;
                        Throwable thrown$iv$iv2 = t$iv$iv;
                        if ($this$use$iv$iv != null) {
                            try {
                                $this$use$iv$iv.close();
                            } catch (Throwable t$iv$iv) {
                                ExceptionsKt.addSuppressed(thrown$iv$iv2, t$iv$iv);
                            }
                        }
                        thrown$iv$iv = thrown$iv$iv2;
                    }
                    break;
                case 1:
                    $this$use$iv$iv = (Closeable) anonymousClass2.L$1;
                    okioReadScope2 = (OkioReadScope) anonymousClass2.L$0;
                    th = null;
                    try {
                        ResultKt.throwOnFailure($result);
                        from = $result;
                        obj = null;
                        thrown$iv$iv = th;
                        result$iv$iv = from;
                        if ($this$use$iv$iv != null) {
                            $this$use$iv$iv.close();
                        }
                        break;
                    } catch (Throwable th4) {
                        t$iv$iv = th4;
                        result$iv$iv = null;
                        Throwable thrown$iv$iv3 = t$iv$iv;
                        if ($this$use$iv$iv != null) {
                            $this$use$iv$iv.close();
                        }
                        thrown$iv$iv = thrown$iv$iv3;
                        break;
                    }
                    if (thrown$iv$iv == null) {
                        throw thrown$iv$iv;
                    }
                    Intrinsics.checkNotNull(result$iv$iv);
                    return result$iv$iv;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (FileNotFoundException ex) {
            if (((OkioReadScope) okioReadScope2).fileSystem.exists(((OkioReadScope) okioReadScope2).path)) {
                throw ex;
            }
            return ((OkioReadScope) okioReadScope2).serializer.getDefaultValue();
        }
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
    }

    protected final void checkClose() {
        if (this.closed.get()) {
            throw new IllegalStateException("This scope has already been closed.".toString());
        }
    }
}
