package androidx.datastore.core.okio;

import androidx.datastore.core.WriteScope;
import java.io.Closeable;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.FileHandle;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

/* JADX INFO: compiled from: OkioStorage.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/datastore/core/okio/OkioWriteScope;", "T", "Landroidx/datastore/core/okio/OkioReadScope;", "Landroidx/datastore/core/WriteScope;", "fileSystem", "Lokio/FileSystem;", "path", "Lokio/Path;", "serializer", "Landroidx/datastore/core/okio/OkioSerializer;", "(Lokio/FileSystem;Lokio/Path;Landroidx/datastore/core/okio/OkioSerializer;)V", "writeData", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core-okio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OkioWriteScope<T> extends OkioReadScope<T> implements WriteScope<T> {

    /* JADX INFO: renamed from: androidx.datastore.core.okio.OkioWriteScope$writeData$1, reason: invalid class name */
    /* JADX INFO: compiled from: OkioStorage.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.okio.OkioWriteScope", f = "OkioStorage.kt", i = {0, 0, 0}, l = {211}, m = "writeData", n = {"$this$use$iv", "handle", "$this$use$iv"}, s = {"L$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ OkioWriteScope<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(OkioWriteScope<T> okioWriteScope, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = okioWriteScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.writeData(null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkioWriteScope(FileSystem fileSystem, Path path, OkioSerializer<T> serializer) {
        super(fileSystem, path, serializer);
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00db A[Catch: all -> 0x00d5, TRY_LEAVE, TryCatch #2 {all -> 0x00d5, blocks: (B:45:0x00db, B:53:0x00f0, B:40:0x00d1, B:36:0x00c9), top: B:70:0x0029, inners: #7 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00f0 A[Catch: all -> 0x00d5, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00d5, blocks: (B:45:0x00db, B:53:0x00f0, B:40:0x00d1, B:36:0x00c9), top: B:70:0x0029, inners: #7 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0106  */
    /* JADX WARN: Code duplicated, block: B:65:0x010c  */
    /* JADX WARN: Code duplicated, block: B:71:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x00b5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    @Override // androidx.datastore.core.WriteScope
    public Object writeData(T t, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        int $i$f$use;
        FileHandle $this$use$iv;
        Object result$iv;
        Throwable thrown$iv;
        Object result$iv2;
        FileHandle handle;
        Closeable $this$use$iv2;
        Object result$iv3;
        Throwable thrown$iv2;
        Object result$iv4;
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
        Object $result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int $i$f$use2 = anonymousClass1.label;
        try {
            switch ($i$f$use2) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    checkClose();
                    FileHandle fileHandle = getFileSystem().openReadWrite(getPath());
                    $this$use$iv = fileHandle;
                    $i$f$use = 0;
                    result$iv = null;
                    thrown$iv = null;
                    try {
                        handle = $this$use$iv;
                        $this$use$iv2 = Okio.buffer(FileHandle.sink$default(handle, 0L, 1, null));
                        try {
                            BufferedSink sink = (BufferedSink) $this$use$iv2;
                            OkioSerializer<T> serializer = getSerializer();
                            anonymousClass1.L$0 = $this$use$iv;
                            anonymousClass1.L$1 = handle;
                            anonymousClass1.L$2 = $this$use$iv2;
                            anonymousClass1.label = 1;
                            if (serializer.writeTo(t, sink, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $i$f$use2 = 0;
                            result$iv3 = null;
                            thrown$iv2 = null;
                            handle.flush();
                            result$iv4 = Unit.INSTANCE;
                            if ($this$use$iv2 != null) {
                                try {
                                    $this$use$iv2.close();
                                } catch (Throwable th) {
                                    thrown$iv2 = th;
                                }
                                break;
                            }
                            if (thrown$iv2 == null) {
                                throw thrown$iv2;
                            }
                            Intrinsics.checkNotNull(result$iv4);
                            result$iv2 = Unit.INSTANCE;
                            if ($this$use$iv != null) {
                                try {
                                    $this$use$iv.close();
                                } catch (Throwable t$iv) {
                                    thrown$iv = t$iv;
                                }
                                break;
                            }
                            if (thrown$iv == null) {
                                throw thrown$iv;
                            }
                            Intrinsics.checkNotNull(result$iv2);
                            return Unit.INSTANCE;
                        } catch (Throwable th2) {
                            t$iv = th2;
                            $i$f$use2 = 0;
                            result$iv3 = null;
                            thrown$iv2 = t$iv;
                            if ($this$use$iv2 != null) {
                                try {
                                    $this$use$iv2.close();
                                } catch (Throwable t$iv2) {
                                    ExceptionsKt.addSuppressed(thrown$iv2, t$iv2);
                                }
                            }
                            result$iv4 = result$iv3;
                        }
                    } catch (Throwable th3) {
                        t$iv = th3;
                        thrown$iv = t$iv;
                        if ($this$use$iv != null) {
                            try {
                                $this$use$iv.close();
                            } catch (Throwable t$iv3) {
                                ExceptionsKt.addSuppressed(thrown$iv, t$iv3);
                            }
                        }
                        result$iv2 = result$iv;
                    }
                    break;
                case 1:
                    $i$f$use2 = 0;
                    thrown$iv2 = null;
                    result$iv3 = null;
                    $this$use$iv2 = (Closeable) anonymousClass1.L$2;
                    handle = (FileHandle) anonymousClass1.L$1;
                    thrown$iv = null;
                    result$iv = null;
                    $this$use$iv = (Closeable) anonymousClass1.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        handle.flush();
                        result$iv4 = Unit.INSTANCE;
                        if ($this$use$iv2 != null) {
                            $this$use$iv2.close();
                        }
                        break;
                    } catch (Throwable th4) {
                        t$iv = th4;
                        thrown$iv2 = t$iv;
                        if ($this$use$iv2 != null) {
                            $this$use$iv2.close();
                        }
                        result$iv4 = result$iv3;
                        break;
                    }
                    if (thrown$iv2 == null) {
                        throw thrown$iv2;
                    }
                    Intrinsics.checkNotNull(result$iv4);
                    result$iv2 = Unit.INSTANCE;
                    if ($this$use$iv != null) {
                        $this$use$iv.close();
                        break;
                    }
                    if (thrown$iv == null) {
                        throw thrown$iv;
                    }
                    Intrinsics.checkNotNull(result$iv2);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th5) {
            t$iv = th5;
            $i$f$use = $i$f$use2;
        }
    }
}
