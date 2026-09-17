package androidx.datastore.core;

import androidx.autofill.HintConstants;
import androidx.datastore.core.handlers.NoOpCorruptionHandler;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.time.Duration;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.SharingStartedKt;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: compiled from: DataStoreImpl.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u0000 R*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002RSBn\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012?\b\u0002\u0010\u0005\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00070\u0006\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013JG\u00100\u001a\u0002H1\"\u0004\b\u0001\u001012\u0006\u00102\u001a\u0002032\u001c\u00104\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H10\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e05H\u0082@\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u00106J\u001c\u00107\u001a\u00020\r2\f\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000/H\u0082@¢\u0006\u0002\u00109J\u000e\u0010:\u001a\u00020\rH\u0082@¢\u0006\u0002\u0010;J\u001c\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00000=2\u0006\u0010>\u001a\u000203H\u0082@¢\u0006\u0002\u0010?J\u000e\u0010@\u001a\u00028\u0000H\u0082@¢\u0006\u0002\u0010;J\u001c\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000B2\u0006\u00102\u001a\u000203H\u0082@¢\u0006\u0002\u0010?J\u001c\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000=2\u0006\u0010>\u001a\u000203H\u0082@¢\u0006\u0002\u0010?JI\u0010D\u001a\u00028\u000021\u0010E\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(F\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00072\u0006\u0010G\u001a\u00020HH\u0082@¢\u0006\u0002\u0010IJA\u0010J\u001a\u00028\u000021\u0010E\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(F\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007H\u0096@¢\u0006\u0002\u0010KJ \u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00028\u00002\u0006\u0010O\u001a\u000203H\u0080@¢\u0006\u0004\bP\u0010QR\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\f0\"R\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$8@X\u0080\u0084\u0002¢\u0006\f\u001a\u0004\b'\u0010(*\u0004\b%\u0010&R\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\r0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000/0.X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Landroidx/datastore/core/DataStoreImpl;", "T", "Landroidx/datastore/core/DataStore;", "storage", "Landroidx/datastore/core/Storage;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "api", "Lkotlin/coroutines/Continuation;", "", "", "corruptionHandler", "Landroidx/datastore/core/CorruptionHandler;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/datastore/core/Storage;Ljava/util/List;Landroidx/datastore/core/CorruptionHandler;Lkotlinx/coroutines/CoroutineScope;)V", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "coordinator$delegate", "Lkotlin/Lazy;", "data", "Lkotlinx/coroutines/flow/Flow;", "getData", "()Lkotlinx/coroutines/flow/Flow;", "inMemoryCache", "Landroidx/datastore/core/DataStoreInMemoryCache;", "internalDataFlow", "readAndInit", "Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "storageConnection", "Landroidx/datastore/core/StorageConnection;", "getStorageConnection$datastore_core_release$delegate", "(Landroidx/datastore/core/DataStoreImpl;)Ljava/lang/Object;", "getStorageConnection$datastore_core_release", "()Landroidx/datastore/core/StorageConnection;", "storageConnectionDelegate", "Lkotlin/Lazy;", "updateCollection", "Lkotlinx/coroutines/flow/SharedFlow;", "writeActor", "Landroidx/datastore/core/SimpleActor;", "Landroidx/datastore/core/Message$Update;", "doWithWriteFileLock", "R", "hasWriteFileLock", "", "block", "Lkotlin/Function1;", "(ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleUpdate", "update", "(Landroidx/datastore/core/Message$Update;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAndInitOrPropagateAndThrowFailure", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readDataAndUpdateCache", "Landroidx/datastore/core/State;", "requireLock", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readDataFromFileOrDefault", "readDataOrHandleCorruption", "Landroidx/datastore/core/Data;", "readState", "transformAndWrite", "transform", "t", "callerContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateData", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeData", "", "newData", "updateCache", "writeData$datastore_core_release", "(Ljava/lang/Object;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "InitDataStore", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DataStoreImpl<T> implements DataStore<T> {
    private static final String BUG_MESSAGE = "This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542";

    /* JADX INFO: renamed from: coordinator$delegate, reason: from kotlin metadata */
    private final Lazy coordinator;
    private final CorruptionHandler<T> corruptionHandler;
    private final Flow<T> data;
    private final DataStoreInMemoryCache<T> inMemoryCache;
    private final Flow<T> internalDataFlow;
    private final DataStoreImpl<T>.InitDataStore readAndInit;
    private final CoroutineScope scope;
    private final Storage<T> storage;
    private final Lazy<StorageConnection<T>> storageConnectionDelegate;
    private final SharedFlow<Unit> updateCollection;
    private final SimpleActor<Message.Update<T>> writeActor;

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$handleUpdate$1, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {1, 1}, l = {245, 251, 254}, m = "handleUpdate", n = {"update", "$this$handleUpdate_u24lambda_u240"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DataStoreImpl<T> dataStoreImpl, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.handleUpdate(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 1, 1}, l = {272, 274}, m = "readAndInitOrPropagateAndThrowFailure", n = {"this", "this", "preReadVersion"}, s = {"L$0", "L$0", "I$0"})
    static final class C07381 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07381(DataStoreImpl<T> dataStoreImpl, Continuation<? super C07381> continuation) {
            super(continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readAndInitOrPropagateAndThrowFailure(this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 0, 0, 1, 2}, l = {295, 304, 312}, m = "readDataAndUpdateCache", n = {"this", "currentState", "requireLock", "this", "this"}, s = {"L$0", "L$1", "Z$0", "L$0", "L$0"})
    static final class C07391 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07391(DataStoreImpl<T> dataStoreImpl, Continuation<? super C07391> continuation) {
            super(continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readDataAndUpdateCache(false, this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 5}, l = {373, 374, 376, 377, 388, 392}, m = "readDataOrHandleCorruption", n = {"this", "hasWriteFileLock", "this", "hasWriteFileLock", "this", "hasWriteFileLock", "this", "hasWriteFileLock", "this", "ex", "newData", "hasWriteFileLock", "ex", "newData", "version"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2"})
    static final class C07411 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07411(DataStoreImpl<T> dataStoreImpl, Continuation<? super C07411> continuation) {
            super(continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readDataOrHandleCorruption(false, this);
        }
    }

    public DataStoreImpl(Storage<T> storage, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasksList, CorruptionHandler<T> corruptionHandler, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(initTasksList, "initTasksList");
        Intrinsics.checkNotNullParameter(corruptionHandler, "corruptionHandler");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.storage = storage;
        this.corruptionHandler = corruptionHandler;
        this.scope = scope;
        this.updateCollection = FlowKt.shareIn(FlowKt.flow(new DataStoreImpl$updateCollection$1(this, null)), this.scope, SharingStartedKt.m8944WhileSubscribed5qebJ5I(SharingStarted.INSTANCE, Duration.INSTANCE.m8817getZEROUwyO8pc(), Duration.INSTANCE.m8817getZEROUwyO8pc()), 0);
        this.internalDataFlow = FlowKt.flow(new DataStoreImpl$internalDataFlow$1(this, null));
        this.data = FlowKt.channelFlow(new DataStoreImpl$data$1(this, null));
        this.inMemoryCache = new DataStoreInMemoryCache<>();
        this.readAndInit = new InitDataStore(this, initTasksList);
        this.storageConnectionDelegate = LazyKt.lazy(new Function0<StorageConnection<T>>(this) { // from class: androidx.datastore.core.DataStoreImpl$storageConnectionDelegate$1
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final StorageConnection<T> invoke() {
                return ((DataStoreImpl) this.this$0).storage.createConnection();
            }
        });
        this.coordinator = LazyKt.lazy(new Function0<InterProcessCoordinator>(this) { // from class: androidx.datastore.core.DataStoreImpl$coordinator$2
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final InterProcessCoordinator invoke() {
                return this.this$0.getStorageConnection$datastore_core_release().getCoordinator();
            }
        });
        this.writeActor = new SimpleActor<>(this.scope, new Function1<Throwable, Unit>(this) { // from class: androidx.datastore.core.DataStoreImpl$writeActor$1
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                if (it != null) {
                    ((DataStoreImpl) this.this$0).inMemoryCache.tryUpdate(new Final(it));
                }
                if (((DataStoreImpl) this.this$0).storageConnectionDelegate.isInitialized()) {
                    this.this$0.getStorageConnection$datastore_core_release().close();
                }
            }
        }, new Function2<Message.Update<T>, Throwable, Unit>() { // from class: androidx.datastore.core.DataStoreImpl$writeActor$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object p1, Throwable th) {
                invoke((Message.Update) p1, th);
                return Unit.INSTANCE;
            }

            public final void invoke(Message.Update<T> msg, Throwable ex) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                msg.getAck().completeExceptionally(ex == null ? new CancellationException("DataStore scope was cancelled before updateData could complete") : ex);
            }
        }, new DataStoreImpl$writeActor$3(this, null));
    }

    public /* synthetic */ DataStoreImpl(Storage storage, List list, NoOpCorruptionHandler noOpCorruptionHandler, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(storage, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? new NoOpCorruptionHandler() : noOpCorruptionHandler, (i & 8) != 0 ? CoroutineScopeKt.CoroutineScope(Actual_jvmKt.ioDispatcher().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null))) : coroutineScope);
    }

    @Override // androidx.datastore.core.DataStore
    public Flow<T> getData() {
        return this.data;
    }

    @Override // androidx.datastore.core.DataStore
    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        UpdatingDataContextElement parentContextElement = (UpdatingDataContextElement) continuation.getContext().get(UpdatingDataContextElement.Companion.Key.INSTANCE);
        if (parentContextElement != null) {
            parentContextElement.checkNotUpdating(this);
        }
        UpdatingDataContextElement childContextElement = new UpdatingDataContextElement(parentContextElement, this);
        return BuildersKt.withContext(childContextElement, new C07452(this, function2, null), continuation);
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$updateData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$updateData$2", f = "DataStoreImpl.kt", i = {}, l = {177}, m = "invokeSuspend", n = {}, s = {})
    static final class C07452 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function2<T, Continuation<? super T>, Object> $transform;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C07452(DataStoreImpl<T> dataStoreImpl, Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super C07452> continuation) {
            super(2, continuation);
            this.this$0 = dataStoreImpl;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07452 c07452 = new C07452(this.this$0, this.$transform, continuation);
            c07452.L$0 = obj;
            return c07452;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((C07452) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$withContext = (CoroutineScope) this.L$0;
                    CompletableDeferred ack = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                    Message.Update updateMsg = new Message.Update(this.$transform, ack, ((DataStoreImpl) this.this$0).inMemoryCache.getCurrentState(), $this$withContext.getCoroutineContext());
                    ((DataStoreImpl) this.this$0).writeActor.offer(updateMsg);
                    this.label = 1;
                    Object objAwait = ack.await(this);
                    return objAwait == coroutine_suspended ? coroutine_suspended : objAwait;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final StorageConnection<T> getStorageConnection$datastore_core_release() {
        return this.storageConnectionDelegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InterProcessCoordinator getCoordinator() {
        return (InterProcessCoordinator) this.coordinator.getValue();
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readState$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/State;", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readState$2", f = "DataStoreImpl.kt", i = {}, l = {226, 234}, m = "invokeSuspend", n = {}, s = {})
    static final class C07432 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super State<T>>, Object> {
        final /* synthetic */ boolean $requireLock;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07432(DataStoreImpl<T> dataStoreImpl, boolean z, Continuation<? super C07432> continuation) {
            super(2, continuation);
            this.this$0 = dataStoreImpl;
            this.$requireLock = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07432(this.this$0, this.$requireLock, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super State<T>> continuation) {
            return ((C07432) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:21:0x005d A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:22:0x005e  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            C07432 c07432;
            Object dataAndUpdateCache;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        c07432 = this;
                        if (((DataStoreImpl) c07432.this$0).inMemoryCache.getCurrentState() instanceof Final) {
                            return ((DataStoreImpl) c07432.this$0).inMemoryCache.getCurrentState();
                        }
                        c07432.label = 1;
                        if (c07432.this$0.readAndInitOrPropagateAndThrowFailure(c07432) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c07432.label = 2;
                        dataAndUpdateCache = c07432.this$0.readDataAndUpdateCache(c07432.$requireLock, c07432);
                        if (dataAndUpdateCache == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = dataAndUpdateCache;
                        return (State) $result;
                    case 1:
                        c07432 = this;
                        ResultKt.throwOnFailure($result);
                        c07432.label = 2;
                        dataAndUpdateCache = c07432.this$0.readDataAndUpdateCache(c07432.$requireLock, c07432);
                        if (dataAndUpdateCache == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = dataAndUpdateCache;
                        return (State) $result;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        return (State) $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable throwable) {
                return new ReadException(throwable, -1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readState(boolean requireLock, Continuation<? super State<T>> continuation) {
        return BuildersKt.withContext(this.scope.getCoroutineContext(), new C07432(this, requireLock, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:46:0x00c8 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:47:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [kotlinx.coroutines.CompletableDeferred] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    public final Object handleUpdate(Message.Update<T> update, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object objM7388constructorimpl;
        ?? r1;
        Message.Update<T> update2;
        DataStoreImpl dataStoreImpl;
        CompletableDeferred<T> completableDeferred;
        Object objTransformAndWrite;
        CompletableDeferred<T> completableDeferred2;
        Object obj;
        Object objTransformAndWrite2;
        CompletableDeferred<T> completableDeferred3;
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
        Object obj2 = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (anonymousClass2.label) {
                case 0:
                    ResultKt.throwOnFailure(obj2);
                    update2 = update;
                    CompletableDeferred<T> ack = update2.getAck();
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        dataStoreImpl = this;
                        State<T> currentState = dataStoreImpl.inMemoryCache.getCurrentState();
                        boolean z = true;
                        if (currentState instanceof Data) {
                            Function2<T, Continuation<? super T>, Object> transform = update2.getTransform();
                            CoroutineContext callerContext = update2.getCallerContext();
                            anonymousClass2.L$0 = ack;
                            anonymousClass2.label = 1;
                            objTransformAndWrite = dataStoreImpl.transformAndWrite(transform, callerContext, anonymousClass2);
                            if (objTransformAndWrite == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            completableDeferred2 = ack;
                            obj = objTransformAndWrite;
                            coroutine_suspended = completableDeferred2;
                            objM7388constructorimpl = Result.m7388constructorimpl(obj);
                            r1 = coroutine_suspended;
                            CompletableDeferredKt.completeWith(r1, objM7388constructorimpl);
                            return Unit.INSTANCE;
                        }
                        if (!(currentState instanceof ReadException)) {
                            z = currentState instanceof UnInitialized;
                        }
                        if (!z) {
                            if (currentState instanceof Final) {
                                throw ((Final) currentState).getFinalException();
                            }
                            throw new NoWhenBranchMatchedException();
                        }
                        if (currentState != update2.getLastState()) {
                            Intrinsics.checkNotNull(currentState, "null cannot be cast to non-null type androidx.datastore.core.ReadException<T of androidx.datastore.core.DataStoreImpl.handleUpdate$lambda$0>");
                            throw ((ReadException) currentState).getReadException();
                        }
                        anonymousClass2.L$0 = update2;
                        anonymousClass2.L$1 = dataStoreImpl;
                        anonymousClass2.L$2 = ack;
                        anonymousClass2.label = 2;
                        if (dataStoreImpl.readAndInitOrPropagateAndThrowFailure(anonymousClass2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        completableDeferred = ack;
                        Function2<T, Continuation<? super T>, Object> transform2 = update2.getTransform();
                        CoroutineContext callerContext2 = update2.getCallerContext();
                        anonymousClass2.L$0 = completableDeferred;
                        anonymousClass2.L$1 = null;
                        anonymousClass2.L$2 = null;
                        anonymousClass2.label = 3;
                        objTransformAndWrite2 = dataStoreImpl.transformAndWrite(transform2, callerContext2, anonymousClass2);
                        if (objTransformAndWrite2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        completableDeferred3 = completableDeferred;
                        obj = objTransformAndWrite2;
                        coroutine_suspended = completableDeferred3;
                        objM7388constructorimpl = Result.m7388constructorimpl(obj);
                        r1 = coroutine_suspended;
                        CompletableDeferredKt.completeWith(r1, objM7388constructorimpl);
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        coroutine_suspended = ack;
                        th = th;
                        Result.Companion companion2 = Result.INSTANCE;
                        objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
                        r1 = coroutine_suspended;
                    }
                    break;
                case 1:
                    CompletableDeferred<T> completableDeferred4 = (CompletableDeferred) anonymousClass2.L$0;
                    ResultKt.throwOnFailure(obj2);
                    objTransformAndWrite = obj2;
                    completableDeferred2 = completableDeferred4;
                    obj = objTransformAndWrite;
                    coroutine_suspended = completableDeferred2;
                    objM7388constructorimpl = Result.m7388constructorimpl(obj);
                    r1 = coroutine_suspended;
                    CompletableDeferredKt.completeWith(r1, objM7388constructorimpl);
                    return Unit.INSTANCE;
                case 2:
                    completableDeferred = (CompletableDeferred) anonymousClass2.L$2;
                    dataStoreImpl = (DataStoreImpl) anonymousClass2.L$1;
                    update2 = (Message.Update) anonymousClass2.L$0;
                    try {
                        ResultKt.throwOnFailure(obj2);
                        Function2<T, Continuation<? super T>, Object> transform3 = update2.getTransform();
                        CoroutineContext callerContext3 = update2.getCallerContext();
                        anonymousClass2.L$0 = completableDeferred;
                        anonymousClass2.L$1 = null;
                        anonymousClass2.L$2 = null;
                        anonymousClass2.label = 3;
                        objTransformAndWrite2 = dataStoreImpl.transformAndWrite(transform3, callerContext3, anonymousClass2);
                        if (objTransformAndWrite2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        completableDeferred3 = completableDeferred;
                        obj = objTransformAndWrite2;
                        coroutine_suspended = completableDeferred3;
                        objM7388constructorimpl = Result.m7388constructorimpl(obj);
                        r1 = coroutine_suspended;
                        CompletableDeferredKt.completeWith(r1, objM7388constructorimpl);
                        return Unit.INSTANCE;
                    } catch (Throwable th2) {
                        th = th2;
                        coroutine_suspended = completableDeferred;
                        Result.Companion companion3 = Result.INSTANCE;
                        objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
                        r1 = coroutine_suspended;
                    }
                    break;
                case 3:
                    CompletableDeferred<T> completableDeferred5 = (CompletableDeferred) anonymousClass2.L$0;
                    ResultKt.throwOnFailure(obj2);
                    objTransformAndWrite2 = obj2;
                    completableDeferred3 = completableDeferred5;
                    obj = objTransformAndWrite2;
                    coroutine_suspended = completableDeferred3;
                    objM7388constructorimpl = Result.m7388constructorimpl(obj);
                    r1 = coroutine_suspended;
                    CompletableDeferredKt.completeWith(r1, objM7388constructorimpl);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:24:0x006c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x006d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object readAndInitOrPropagateAndThrowFailure(Continuation<? super Unit> continuation) throws Throwable {
        C07381 c07381;
        DataStoreImpl<T> dataStoreImpl;
        Object version;
        int preReadVersion;
        Throwable throwable;
        int preReadVersion2;
        DataStoreImpl<T>.InitDataStore initDataStore;
        if (continuation instanceof C07381) {
            c07381 = (C07381) continuation;
            if ((c07381.label & Integer.MIN_VALUE) != 0) {
                c07381.label -= Integer.MIN_VALUE;
            } else {
                c07381 = new C07381(this, continuation);
            }
        } else {
            c07381 = new C07381(this, continuation);
        }
        C07381 c07382 = c07381;
        Object $result = c07382.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07382.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                dataStoreImpl = this;
                InterProcessCoordinator coordinator = dataStoreImpl.getCoordinator();
                c07382.L$0 = dataStoreImpl;
                c07382.label = 1;
                version = coordinator.getVersion(c07382);
                if (version == coroutine_suspended) {
                    return coroutine_suspended;
                }
                preReadVersion = ((Number) version).intValue();
                try {
                    initDataStore = dataStoreImpl.readAndInit;
                    c07382.L$0 = dataStoreImpl;
                    c07382.I$0 = preReadVersion;
                    c07382.label = 2;
                    if (initDataStore.runIfNeeded(c07382) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    throwable = th;
                    preReadVersion2 = preReadVersion;
                    dataStoreImpl.inMemoryCache.tryUpdate(new ReadException(throwable, preReadVersion2));
                    throw throwable;
                }
            case 1:
                dataStoreImpl = (DataStoreImpl) c07382.L$0;
                ResultKt.throwOnFailure($result);
                version = $result;
                preReadVersion = ((Number) version).intValue();
                initDataStore = dataStoreImpl.readAndInit;
                c07382.L$0 = dataStoreImpl;
                c07382.I$0 = preReadVersion;
                c07382.label = 2;
                if (initDataStore.runIfNeeded(c07382) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                preReadVersion2 = c07382.I$0;
                dataStoreImpl = (DataStoreImpl) c07382.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    throwable = th2;
                    dataStoreImpl.inMemoryCache.tryUpdate(new ReadException(throwable, preReadVersion2));
                    throw throwable;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x0083  */
    /* JADX WARN: Code duplicated, block: B:24:0x0088  */
    /* JADX WARN: Code duplicated, block: B:31:0x0093  */
    /* JADX WARN: Code duplicated, block: B:33:0x00ab A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x00af  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:41:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object readDataAndUpdateCache(boolean requireLock, Continuation<? super State<T>> continuation) {
        C07391 c07391;
        State<T> currentState;
        Object version;
        boolean requireLock2;
        DataStoreImpl<T> dataStoreImpl;
        int cachedVersion;
        Object objTryLock;
        Object objLock;
        Pair pair;
        State<T> state;
        boolean acquiredLock;
        if (continuation instanceof C07391) {
            c07391 = (C07391) continuation;
            if ((c07391.label & Integer.MIN_VALUE) != 0) {
                c07391.label -= Integer.MIN_VALUE;
            } else {
                c07391 = new C07391(this, continuation);
            }
        } else {
            c07391 = new C07391(this, continuation);
        }
        C07391 c07392 = c07391;
        Object $result = c07392.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07392.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                currentState = this.inMemoryCache.getCurrentState();
                if (currentState instanceof UnInitialized) {
                    throw new IllegalStateException(BUG_MESSAGE.toString());
                }
                InterProcessCoordinator coordinator = getCoordinator();
                c07392.L$0 = this;
                c07392.L$1 = currentState;
                c07392.Z$0 = requireLock;
                c07392.label = 1;
                version = coordinator.getVersion(c07392);
                if (version == coroutine_suspended) {
                    return coroutine_suspended;
                }
                requireLock2 = requireLock;
                dataStoreImpl = this;
                int latestVersion = ((Number) version).intValue();
                if (currentState instanceof Data) {
                    cachedVersion = currentState.getVersion();
                } else {
                    cachedVersion = -1;
                }
                if (!(currentState instanceof Data) && latestVersion == cachedVersion) {
                    return currentState;
                }
                if (requireLock2) {
                    InterProcessCoordinator coordinator2 = dataStoreImpl.getCoordinator();
                    C07403 c07403 = new C07403(dataStoreImpl, null);
                    c07392.L$0 = dataStoreImpl;
                    c07392.L$1 = null;
                    c07392.label = 2;
                    objLock = coordinator2.lock(c07403, c07392);
                    if (objLock == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pair = (Pair) objLock;
                    state = (State) pair.component1();
                    acquiredLock = ((Boolean) pair.component2()).booleanValue();
                    if (acquiredLock) {
                        dataStoreImpl.inMemoryCache.tryUpdate(state);
                    }
                    return state;
                }
                InterProcessCoordinator coordinator3 = dataStoreImpl.getCoordinator();
                AnonymousClass4 anonymousClass4 = new AnonymousClass4(dataStoreImpl, cachedVersion, null);
                c07392.L$0 = dataStoreImpl;
                c07392.L$1 = null;
                c07392.label = 3;
                objTryLock = coordinator3.tryLock(anonymousClass4, c07392);
                if (objTryLock == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pair = (Pair) objTryLock;
                state = (State) pair.component1();
                acquiredLock = ((Boolean) pair.component2()).booleanValue();
                if (acquiredLock) {
                    dataStoreImpl.inMemoryCache.tryUpdate(state);
                }
                return state;
            case 1:
                boolean requireLock3 = c07392.Z$0;
                State<T> state2 = (State) c07392.L$1;
                DataStoreImpl<T> dataStoreImpl2 = (DataStoreImpl) c07392.L$0;
                ResultKt.throwOnFailure($result);
                version = $result;
                requireLock2 = requireLock3;
                dataStoreImpl = dataStoreImpl2;
                currentState = state2;
                int latestVersion2 = ((Number) version).intValue();
                if (currentState instanceof Data) {
                    cachedVersion = currentState.getVersion();
                } else {
                    cachedVersion = -1;
                }
                if (!(currentState instanceof Data)) {
                }
                if (requireLock2) {
                    InterProcessCoordinator coordinator4 = dataStoreImpl.getCoordinator();
                    C07403 c07404 = new C07403(dataStoreImpl, null);
                    c07392.L$0 = dataStoreImpl;
                    c07392.L$1 = null;
                    c07392.label = 2;
                    objLock = coordinator4.lock(c07404, c07392);
                    if (objLock == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pair = (Pair) objLock;
                    state = (State) pair.component1();
                    acquiredLock = ((Boolean) pair.component2()).booleanValue();
                    if (acquiredLock) {
                        dataStoreImpl.inMemoryCache.tryUpdate(state);
                    }
                    return state;
                }
                InterProcessCoordinator coordinator5 = dataStoreImpl.getCoordinator();
                AnonymousClass4 anonymousClass5 = new AnonymousClass4(dataStoreImpl, cachedVersion, null);
                c07392.L$0 = dataStoreImpl;
                c07392.L$1 = null;
                c07392.label = 3;
                objTryLock = coordinator5.tryLock(anonymousClass5, c07392);
                if (objTryLock == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pair = (Pair) objTryLock;
                state = (State) pair.component1();
                acquiredLock = ((Boolean) pair.component2()).booleanValue();
                if (acquiredLock) {
                    dataStoreImpl.inMemoryCache.tryUpdate(state);
                }
                return state;
            case 2:
                dataStoreImpl = (DataStoreImpl) c07392.L$0;
                ResultKt.throwOnFailure($result);
                objLock = $result;
                pair = (Pair) objLock;
                state = (State) pair.component1();
                acquiredLock = ((Boolean) pair.component2()).booleanValue();
                if (acquiredLock) {
                    dataStoreImpl.inMemoryCache.tryUpdate(state);
                }
                return state;
            case 3:
                dataStoreImpl = (DataStoreImpl) c07392.L$0;
                ResultKt.throwOnFailure($result);
                objTryLock = $result;
                pair = (Pair) objTryLock;
                state = (State) pair.component1();
                acquiredLock = ((Boolean) pair.component2()).booleanValue();
                if (acquiredLock) {
                    dataStoreImpl.inMemoryCache.tryUpdate(state);
                }
                return state;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Landroidx/datastore/core/State;", "T", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$3", f = "DataStoreImpl.kt", i = {}, l = {306, StatusLine.HTTP_PERM_REDIRECT}, m = "invokeSuspend", n = {}, s = {})
    static final class C07403 extends SuspendLambda implements Function1<Continuation<? super Pair<? extends State<T>, ? extends Boolean>>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07403(DataStoreImpl<T> dataStoreImpl, Continuation<? super C07403> continuation) {
            super(1, continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C07403(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Pair<? extends State<T>, Boolean>> continuation) {
            return ((C07403) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v13 */
        /* JADX WARN: Type inference failed for: r1v14 */
        /* JADX WARN: Type inference failed for: r1v2, types: [androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$3] */
        /* JADX WARN: Type inference failed for: r1v3 */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v8 */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r5v0 */
        /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r9v1 */
        /* JADX WARN: Type inference failed for: r9v11 */
        /* JADX WARN: Type inference failed for: r9v12 */
        /* JADX WARN: Type inference failed for: r9v14 */
        /* JADX WARN: Type inference failed for: r9v15, types: [androidx.datastore.core.State] */
        /* JADX WARN: Type inference failed for: r9v17 */
        /* JADX WARN: Type inference failed for: r9v18 */
        /* JADX WARN: Type inference failed for: r9v19 */
        /* JADX WARN: Type inference failed for: r9v2 */
        /* JADX WARN: Type inference failed for: r9v20 */
        /* JADX WARN: Type inference failed for: r9v3 */
        /* JADX WARN: Type inference failed for: r9v4, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r9v6 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ?? r0;
            ?? r9;
            ?? readException;
            ?? r1;
            ?? r10;
            C07403 c07403;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            obj = this.label;
            try {
                switch (obj) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        Object dataOrHandleCorruption = this.this$0.readDataOrHandleCorruption(true, this);
                        if (dataOrHandleCorruption == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        r10 = dataOrHandleCorruption;
                        c07403 = this;
                        try {
                            obj = (State) r10;
                            readException = obj;
                            break;
                        } catch (Throwable th) {
                            C07403 c07404 = c07403;
                            th = th;
                            obj = obj;
                            obj = c07404;
                            obj.L$0 = th;
                            obj.label = 2;
                            Object version = obj.this$0.getCoordinator().getVersion((Continuation) obj);
                            if (version == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            r0 = obj;
                            r9 = version;
                            r1 = obj;
                            readException = new ReadException(th, ((Number) r9).intValue());
                        }
                        return TuplesKt.to(readException, Boxing.boxBoolean(true));
                    case 1:
                        ResultKt.throwOnFailure(obj);
                        c07403 = this;
                        r10 = obj;
                        obj = (State) r10;
                        readException = obj;
                        return TuplesKt.to(readException, Boxing.boxBoolean(true));
                    case 2:
                        Throwable th2 = (Throwable) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        th = th2;
                        r1 = this;
                        r0 = obj;
                        r9 = obj;
                        readException = new ReadException(th, ((Number) r9).intValue());
                        return TuplesKt.to(readException, Boxing.boxBoolean(true));
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0005\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Landroidx/datastore/core/State;", "T", "", "locked"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4", f = "DataStoreImpl.kt", i = {0, 1}, l = {314, 317}, m = "invokeSuspend", n = {"locked", "locked"}, s = {"Z$0", "Z$0"})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<Boolean, Continuation<? super Pair<? extends State<T>, ? extends Boolean>>, Object> {
        final /* synthetic */ int $cachedVersion;
        Object L$0;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(DataStoreImpl<T> dataStoreImpl, int i, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.this$0 = dataStoreImpl;
            this.$cachedVersion = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, this.$cachedVersion, continuation);
            anonymousClass4.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Object obj) {
            return invoke(bool.booleanValue(), (Continuation) obj);
        }

        public final Object invoke(boolean z, Continuation<? super Pair<? extends State<T>, Boolean>> continuation) {
            return ((AnonymousClass4) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:34:0x0092  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v9 */
        /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r11v1 */
        /* JADX WARN: Type inference failed for: r11v10 */
        /* JADX WARN: Type inference failed for: r11v13 */
        /* JADX WARN: Type inference failed for: r11v14 */
        /* JADX WARN: Type inference failed for: r11v16 */
        /* JADX WARN: Type inference failed for: r11v17, types: [androidx.datastore.core.State] */
        /* JADX WARN: Type inference failed for: r11v19 */
        /* JADX WARN: Type inference failed for: r11v2 */
        /* JADX WARN: Type inference failed for: r11v20 */
        /* JADX WARN: Type inference failed for: r11v21 */
        /* JADX WARN: Type inference failed for: r11v22 */
        /* JADX WARN: Type inference failed for: r11v4 */
        /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v13 */
        /* JADX WARN: Type inference failed for: r1v14 */
        /* JADX WARN: Type inference failed for: r1v2, types: [androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4] */
        /* JADX WARN: Type inference failed for: r1v3 */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v5 */
        /* JADX WARN: Type inference failed for: r1v9 */
        /* JADX WARN: Type inference failed for: r4v1, types: [boolean] */
        /* JADX WARN: Type inference failed for: r4v10 */
        /* JADX WARN: Type inference failed for: r4v13 */
        /* JADX WARN: Type inference failed for: r4v15 */
        /* JADX WARN: Type inference failed for: r4v3 */
        /* JADX WARN: Type inference failed for: r4v6 */
        /* JADX WARN: Type inference failed for: r4v7 */
        /* JADX WARN: Type inference failed for: r4v8 */
        /* JADX WARN: Type inference failed for: r5v14 */
        /* JADX WARN: Type inference failed for: r5v2 */
        /* JADX WARN: Type inference failed for: r5v4 */
        /* JADX WARN: Type inference failed for: r7v0 */
        /* JADX WARN: Type inference failed for: r9v0 */
        /* JADX WARN: Type inference failed for: r9v1 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ?? r4;
            int iIntValue;
            ?? r0;
            ?? r1;
            ?? r11;
            ?? r12;
            ?? r5;
            ?? r6;
            ?? r2;
            ?? r13;
            boolean z;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            obj = this.label;
            try {
                switch (obj) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        boolean z2 = this.Z$0;
                        DataStoreImpl<T> dataStoreImpl = this.this$0;
                        boolean z3 = z2;
                        this.Z$0 = z2;
                        this.label = 1;
                        Object dataOrHandleCorruption = dataStoreImpl.readDataOrHandleCorruption(z3, this);
                        if (dataOrHandleCorruption == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        r13 = dataOrHandleCorruption;
                        z = z2;
                        r4 = this;
                        try {
                            obj = (State) r13;
                            r5 = z;
                            r12 = obj;
                            break;
                        } catch (Throwable th) {
                            boolean z4 = z ? 1 : 0;
                            th = th;
                            obj = obj;
                            obj = r4;
                            r4 = z4 ? 1 : 0;
                            if (r4 != 0) {
                                obj.L$0 = th;
                                obj.Z$0 = r4;
                                obj.label = 2;
                                Object version = obj.this$0.getCoordinator().getVersion((Continuation) obj);
                                if (version == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                r1 = obj;
                                r11 = version;
                                r2 = obj;
                                r6 = r4;
                                iIntValue = ((Number) r11).intValue();
                                r0 = r6;
                            } else {
                                iIntValue = obj.$cachedVersion;
                                r0 = r4 == true ? 1 : 0;
                            }
                            ReadException readException = new ReadException(th, iIntValue);
                            r5 = r0;
                            r12 = readException;
                        }
                        return TuplesKt.to(r12, Boxing.boxBoolean(r5 != 0));
                    case 1:
                        boolean z5 = this.Z$0;
                        ResultKt.throwOnFailure(obj);
                        z = z5;
                        r4 = this;
                        r13 = obj;
                        obj = (State) r13;
                        r5 = z;
                        r12 = obj;
                        return TuplesKt.to(r12, Boxing.boxBoolean(r5 != 0));
                    case 2:
                        boolean z6 = this.Z$0;
                        Throwable th2 = (Throwable) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        th = th2;
                        r6 = z6;
                        r2 = this;
                        r1 = obj;
                        r11 = obj;
                        iIntValue = ((Number) r11).intValue();
                        r0 = r6;
                        ReadException readException2 = new ReadException(th, iIntValue);
                        r5 = r0;
                        r12 = readException2;
                        return TuplesKt.to(r12, Boxing.boxBoolean(r5 != 0));
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readDataFromFileOrDefault(Continuation<? super T> continuation) {
        return StorageConnectionKt.readData(getStorageConnection$datastore_core_release(), continuation);
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$transformAndWrite$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\u008a@"}, d2 = {"<anonymous>", "T"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$transformAndWrite$2", f = "DataStoreImpl.kt", i = {1, 2}, l = {338, 339, 345}, m = "invokeSuspend", n = {"curData", "newData"}, s = {"L$0", "L$0"})
    static final class C07442 extends SuspendLambda implements Function1<Continuation<? super T>, Object> {
        final /* synthetic */ CoroutineContext $callerContext;
        final /* synthetic */ Function2<T, Continuation<? super T>, Object> $transform;
        Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C07442(DataStoreImpl<T> dataStoreImpl, CoroutineContext coroutineContext, Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super C07442> continuation) {
            super(1, continuation);
            this.this$0 = dataStoreImpl;
            this.$callerContext = coroutineContext;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C07442(this.this$0, this.$callerContext, this.$transform, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super T> continuation) {
            return ((C07442) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x005f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:16:0x0060  */
        /* JADX WARN: Code duplicated, block: B:19:0x0071  */
        /* JADX WARN: Code duplicated, block: B:21:0x0081 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:22:0x0082  */
        /* JADX WARN: Code duplicated, block: B:25:? A[RETURN, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws CorruptionException {
            C07442 c07442;
            Data data;
            Object objWithContext;
            Data data2;
            DataStoreImpl<T> dataStoreImpl;
            C07442 c07443;
            Object obj2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    Object dataOrHandleCorruption = this.this$0.readDataOrHandleCorruption(true, this);
                    if (dataOrHandleCorruption == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj = dataOrHandleCorruption;
                    c07442 = this;
                    data = (Data) obj;
                    c07442.L$0 = data;
                    c07442.label = 2;
                    objWithContext = BuildersKt.withContext(c07442.$callerContext, new DataStoreImpl$transformAndWrite$2$newData$1(c07442.$transform, data, null), c07442);
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    data2 = data;
                    obj = objWithContext;
                    data2.checkHashCode();
                    if (Intrinsics.areEqual(data2.getValue(), obj)) {
                        return obj;
                    }
                    dataStoreImpl = c07442.this$0;
                    c07442.L$0 = obj;
                    c07442.label = 3;
                    if (dataStoreImpl.writeData$datastore_core_release((T) obj, true, c07442) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c07443 = c07442;
                    Object obj3 = obj;
                    obj2 = obj;
                    obj = obj3;
                    return obj2;
                case 1:
                    ResultKt.throwOnFailure(obj);
                    c07442 = this;
                    data = (Data) obj;
                    c07442.L$0 = data;
                    c07442.label = 2;
                    objWithContext = BuildersKt.withContext(c07442.$callerContext, new DataStoreImpl$transformAndWrite$2$newData$1(c07442.$transform, data, null), c07442);
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    data2 = data;
                    obj = objWithContext;
                    data2.checkHashCode();
                    if (Intrinsics.areEqual(data2.getValue(), obj)) {
                        return obj;
                    }
                    dataStoreImpl = c07442.this$0;
                    c07442.L$0 = obj;
                    c07442.label = 3;
                    if (dataStoreImpl.writeData$datastore_core_release((T) obj, true, c07442) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c07443 = c07442;
                    Object obj4 = obj;
                    obj2 = obj;
                    obj = obj4;
                    return obj2;
                case 2:
                    Data data3 = (Data) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    data2 = data3;
                    c07442 = this;
                    obj = obj;
                    data2.checkHashCode();
                    if (Intrinsics.areEqual(data2.getValue(), obj)) {
                        return obj;
                    }
                    dataStoreImpl = c07442.this$0;
                    c07442.L$0 = obj;
                    c07442.label = 3;
                    if (dataStoreImpl.writeData$datastore_core_release((T) obj, true, c07442) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c07443 = c07442;
                    Object obj5 = obj;
                    obj2 = obj;
                    obj = obj5;
                    return obj2;
                case 3:
                    c07443 = this;
                    obj2 = c07443.L$0;
                    ResultKt.throwOnFailure(obj);
                    return obj2;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object transformAndWrite(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, CoroutineContext callerContext, Continuation<? super T> continuation) {
        return getCoordinator().lock(new C07442(this, callerContext, function2, null), continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object writeData$datastore_core_release(T t, boolean updateCache, Continuation<? super Integer> continuation) {
        DataStoreImpl$writeData$1 dataStoreImpl$writeData$1;
        Ref.IntRef newVersion;
        if (continuation instanceof DataStoreImpl$writeData$1) {
            dataStoreImpl$writeData$1 = (DataStoreImpl$writeData$1) continuation;
            if ((dataStoreImpl$writeData$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$writeData$1.label -= Integer.MIN_VALUE;
            } else {
                dataStoreImpl$writeData$1 = new DataStoreImpl$writeData$1(this, continuation);
            }
        } else {
            dataStoreImpl$writeData$1 = new DataStoreImpl$writeData$1(this, continuation);
        }
        DataStoreImpl$writeData$1 dataStoreImpl$writeData$2 = dataStoreImpl$writeData$1;
        Object $result = dataStoreImpl$writeData$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dataStoreImpl$writeData$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                newVersion = new Ref.IntRef();
                StorageConnection<T> storageConnection$datastore_core_release = getStorageConnection$datastore_core_release();
                DataStoreImpl$writeData$2 dataStoreImpl$writeData$3 = new DataStoreImpl$writeData$2(newVersion, this, t, updateCache, null);
                dataStoreImpl$writeData$2.L$0 = newVersion;
                dataStoreImpl$writeData$2.label = 1;
                if (storageConnection$datastore_core_release.writeScope(dataStoreImpl$writeData$3, dataStoreImpl$writeData$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                newVersion = (Ref.IntRef) dataStoreImpl$writeData$2.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Boxing.boxInt(newVersion.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:42:0x00b3 A[Catch: CorruptionException -> 0x0084, TRY_ENTER, TryCatch #2 {CorruptionException -> 0x0084, blocks: (B:25:0x007f, B:47:0x00cf, B:42:0x00b3, B:44:0x00b9), top: B:81:0x0025 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:46:0x00ce A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:54:0x010b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:60:0x012d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:61:0x012e  */
    /* JADX WARN: Code duplicated, block: B:72:0x0163  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v27 */
    /* JADX WARN: Type inference failed for: r11v28 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v33 */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [androidx.datastore.core.DataStoreImpl, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [androidx.datastore.core.DataStoreImpl] */
    /* JADX WARN: Type inference failed for: r9v8 */
    public final Object readDataOrHandleCorruption(boolean z, Continuation<? super Data<T>> continuation) throws CorruptionException {
        C07411 c07411;
        ?? r11;
        ?? r8;
        CorruptionException corruptionException;
        Ref.ObjectRef objectRef;
        Object objHandleCorruption;
        ?? r9;
        T t;
        CorruptionException corruptionException2;
        Ref.ObjectRef objectRef2;
        ?? r12;
        CorruptionException corruptionException3;
        Ref.IntRef intRef;
        Ref.ObjectRef objectRef3;
        DataStoreImpl dataStoreImpl;
        Object version;
        Object dataFromFileOrDefault;
        DataStoreImpl dataStoreImpl2;
        boolean z2;
        int iHashCode;
        Object version2;
        boolean z3;
        Object objTryLock;
        boolean z4;
        if (continuation instanceof C07411) {
            c07411 = (C07411) continuation;
            if ((c07411.label & Integer.MIN_VALUE) != 0) {
                c07411.label -= Integer.MIN_VALUE;
            } else {
                c07411 = new C07411(this, continuation);
            }
        } else {
            c07411 = new C07411(this, continuation);
        }
        C07411 c07412 = c07411;
        Object obj = c07412.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r2 = c07412.label;
        try {
            try {
                switch (r2) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        dataStoreImpl = this;
                        if (!z) {
                            InterProcessCoordinator coordinator = dataStoreImpl.getCoordinator();
                            c07412.L$0 = dataStoreImpl;
                            c07412.Z$0 = z;
                            c07412.label = 3;
                            version = coordinator.getVersion(c07412);
                            if (version == coroutine_suspended) {
                                z3 = z;
                                return coroutine_suspended;
                            }
                            z3 = z;
                            int iIntValue = ((Number) version).intValue();
                            InterProcessCoordinator coordinator2 = dataStoreImpl.getCoordinator();
                            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dataStoreImpl, iIntValue, null);
                            c07412.L$0 = dataStoreImpl;
                            c07412.Z$0 = z3;
                            c07412.label = 4;
                            objTryLock = coordinator2.tryLock(anonymousClass2, c07412);
                            z4 = z3;
                            if (objTryLock == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return (Data) objTryLock;
                        }
                        c07412.L$0 = dataStoreImpl;
                        c07412.Z$0 = z;
                        c07412.label = 1;
                        dataFromFileOrDefault = dataStoreImpl.readDataFromFileOrDefault(c07412);
                        if (dataFromFileOrDefault == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dataStoreImpl2 = dataStoreImpl;
                        z2 = z ? 1 : 0;
                        if (dataFromFileOrDefault != null) {
                            iHashCode = dataFromFileOrDefault.hashCode();
                        } else {
                            iHashCode = 0;
                        }
                        InterProcessCoordinator coordinator3 = dataStoreImpl2.getCoordinator();
                        c07412.L$0 = dataStoreImpl2;
                        c07412.L$1 = dataFromFileOrDefault;
                        c07412.Z$0 = z2;
                        c07412.I$0 = iHashCode;
                        c07412.label = 2;
                        version2 = coordinator3.getVersion(c07412);
                        r2 = z2;
                        if (version2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return new Data(dataFromFileOrDefault, iHashCode, ((Number) version2).intValue());
                    case 1:
                        boolean z5 = c07412.Z$0;
                        DataStoreImpl dataStoreImpl3 = (DataStoreImpl) c07412.L$0;
                        ResultKt.throwOnFailure(obj);
                        dataFromFileOrDefault = obj;
                        dataStoreImpl2 = dataStoreImpl3;
                        z2 = z5;
                        if (dataFromFileOrDefault != null) {
                            iHashCode = dataFromFileOrDefault.hashCode();
                        } else {
                            iHashCode = 0;
                        }
                        InterProcessCoordinator coordinator4 = dataStoreImpl2.getCoordinator();
                        c07412.L$0 = dataStoreImpl2;
                        c07412.L$1 = dataFromFileOrDefault;
                        c07412.Z$0 = z2;
                        c07412.I$0 = iHashCode;
                        c07412.label = 2;
                        version2 = coordinator4.getVersion(c07412);
                        r2 = z2;
                        if (version2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return new Data(dataFromFileOrDefault, iHashCode, ((Number) version2).intValue());
                    case 2:
                        iHashCode = c07412.I$0;
                        boolean z6 = c07412.Z$0;
                        dataFromFileOrDefault = c07412.L$1;
                        dataStoreImpl2 = (DataStoreImpl) c07412.L$0;
                        ResultKt.throwOnFailure(obj);
                        version2 = obj;
                        r2 = z6;
                        return new Data(dataFromFileOrDefault, iHashCode, ((Number) version2).intValue());
                    case 3:
                        boolean z7 = c07412.Z$0;
                        dataStoreImpl = (DataStoreImpl) c07412.L$0;
                        ResultKt.throwOnFailure(obj);
                        version = obj;
                        z3 = z7;
                        z3 = z;
                        int iIntValue2 = ((Number) version).intValue();
                        InterProcessCoordinator coordinator5 = dataStoreImpl.getCoordinator();
                        AnonymousClass2 anonymousClass3 = new AnonymousClass2(dataStoreImpl, iIntValue2, null);
                        c07412.L$0 = dataStoreImpl;
                        c07412.Z$0 = z3;
                        c07412.label = 4;
                        objTryLock = coordinator5.tryLock(anonymousClass3, c07412);
                        z4 = z3;
                        if (objTryLock == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return (Data) objTryLock;
                    case 4:
                        boolean z8 = c07412.Z$0;
                        dataStoreImpl = (DataStoreImpl) c07412.L$0;
                        ResultKt.throwOnFailure(obj);
                        objTryLock = obj;
                        z4 = z8;
                        return (Data) objTryLock;
                    case 5:
                        boolean z9 = c07412.Z$0;
                        objectRef2 = (Ref.ObjectRef) c07412.L$3;
                        objectRef = (Ref.ObjectRef) c07412.L$2;
                        corruptionException2 = (CorruptionException) c07412.L$1;
                        DataStoreImpl dataStoreImpl4 = (DataStoreImpl) c07412.L$0;
                        ResultKt.throwOnFailure(obj);
                        r9 = dataStoreImpl4;
                        t = (T) obj;
                        r12 = z9;
                        objectRef2.element = t;
                        Ref.IntRef intRef2 = new Ref.IntRef();
                        boolean z10 = r12 != 0;
                        try {
                            C07423 c07423 = new C07423(objectRef, r9, intRef2, null);
                            c07412.L$0 = corruptionException2;
                            c07412.L$1 = objectRef;
                            c07412.L$2 = intRef2;
                            c07412.L$3 = null;
                            c07412.label = 6;
                            if (r9.doWithWriteFileLock(z10, c07423, c07412) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            intRef = intRef2;
                            objectRef3 = objectRef;
                            T t2 = objectRef3.element;
                            T t3 = objectRef3.element;
                            return new Data(t2, t3 != null ? t3.hashCode() : 0, intRef.element);
                        } catch (Throwable th) {
                            th = th;
                            corruptionException3 = corruptionException2;
                            ExceptionsKt.addSuppressed(corruptionException3, th);
                            throw corruptionException3;
                        }
                    case 6:
                        intRef = (Ref.IntRef) c07412.L$2;
                        objectRef3 = (Ref.ObjectRef) c07412.L$1;
                        corruptionException3 = (CorruptionException) c07412.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            T t4 = objectRef3.element;
                            T t5 = objectRef3.element;
                            return new Data(t4, t5 != null ? t5.hashCode() : 0, intRef.element);
                        } catch (Throwable th2) {
                            th = th2;
                            ExceptionsKt.addSuppressed(corruptionException3, th);
                            throw corruptionException3;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (CorruptionException e) {
                e = e;
                r8 = r2;
                r11 = z;
                corruptionException = e;
                objectRef = new Ref.ObjectRef();
                CorruptionHandler<T> corruptionHandler = r8.corruptionHandler;
                c07412.L$0 = r8;
                c07412.L$1 = corruptionException;
                c07412.L$2 = objectRef;
                c07412.L$3 = objectRef;
                c07412.Z$0 = r11;
                c07412.label = 5;
                objHandleCorruption = corruptionHandler.handleCorruption(corruptionException, c07412);
                if (objHandleCorruption == coroutine_suspended) {
                    return coroutine_suspended;
                }
                r9 = r8;
                t = (T) objHandleCorruption;
                corruptionException2 = corruptionException;
                objectRef2 = objectRef;
                r12 = r11;
            }
        } catch (CorruptionException e2) {
            e = e2;
            r11 = r2;
            r8 = dataStoreImpl2;
            corruptionException = e;
            objectRef = new Ref.ObjectRef();
            CorruptionHandler<T> corruptionHandler2 = r8.corruptionHandler;
            c07412.L$0 = r8;
            c07412.L$1 = corruptionException;
            c07412.L$2 = objectRef;
            c07412.L$3 = objectRef;
            c07412.Z$0 = r11;
            c07412.label = 5;
            objHandleCorruption = corruptionHandler2.handleCorruption(corruptionException, c07412);
            if (objHandleCorruption == coroutine_suspended) {
                return coroutine_suspended;
            }
            r9 = r8;
            t = (T) objHandleCorruption;
            corruptionException2 = corruptionException;
            objectRef2 = objectRef;
            r12 = r11;
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/Data;", "T", "locked", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2", f = "DataStoreImpl.kt", i = {0, 1}, l = {378, 379}, m = "invokeSuspend", n = {"locked", "data"}, s = {"Z$0", "L$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Boolean, Continuation<? super Data<T>>, Object> {
        final /* synthetic */ int $preLockVersion;
        Object L$0;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DataStoreImpl<T> dataStoreImpl, int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = dataStoreImpl;
            this.$preLockVersion = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$preLockVersion, continuation);
            anonymousClass2.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Object obj) {
            return invoke(bool.booleanValue(), (Continuation) obj);
        }

        public final Object invoke(boolean z, Continuation<? super Data<T>> continuation) {
            return ((AnonymousClass2) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0043  */
        /* JADX WARN: Code duplicated, block: B:16:0x0057 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:17:0x0058  */
        /* JADX WARN: Code duplicated, block: B:19:0x0062  */
        /* JADX WARN: Code duplicated, block: B:22:0x006d  */
        /* JADX WARN: Code duplicated, block: B:23:0x0072  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object data) {
            boolean locked;
            AnonymousClass2 anonymousClass2;
            Object data2;
            int version;
            Object version2;
            int iHashCode;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(data);
                    boolean locked2 = this.Z$0;
                    this.Z$0 = locked2;
                    this.label = 1;
                    Object dataFromFileOrDefault = this.this$0.readDataFromFileOrDefault(this);
                    if (dataFromFileOrDefault == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    data = dataFromFileOrDefault;
                    locked = locked2;
                    anonymousClass2 = this;
                    if (locked) {
                        anonymousClass2.L$0 = data;
                        anonymousClass2.label = 2;
                        version2 = anonymousClass2.this$0.getCoordinator().getVersion(anonymousClass2);
                        if (version2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        data2 = data;
                        data = version2;
                        version = ((Number) data).intValue();
                    } else {
                        int i = anonymousClass2.$preLockVersion;
                        data2 = data;
                        version = i;
                    }
                    if (data2 != null) {
                        iHashCode = data2.hashCode();
                    } else {
                        iHashCode = 0;
                    }
                    return new Data(data2, iHashCode, version);
                case 1:
                    boolean locked3 = this.Z$0;
                    ResultKt.throwOnFailure(data);
                    locked = locked3;
                    anonymousClass2 = this;
                    if (locked) {
                        anonymousClass2.L$0 = data;
                        anonymousClass2.label = 2;
                        version2 = anonymousClass2.this$0.getCoordinator().getVersion(anonymousClass2);
                        if (version2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        data2 = data;
                        data = version2;
                        version = ((Number) data).intValue();
                    } else {
                        int i2 = anonymousClass2.$preLockVersion;
                        data2 = data;
                        version = i2;
                    }
                    if (data2 != null) {
                        iHashCode = data2.hashCode();
                    } else {
                        iHashCode = 0;
                    }
                    return new Data(data2, iHashCode, version);
                case 2:
                    Object data3 = this.L$0;
                    ResultKt.throwOnFailure(data);
                    data2 = data3;
                    version = ((Number) data).intValue();
                    if (data2 != null) {
                        iHashCode = data2.hashCode();
                    } else {
                        iHashCode = 0;
                    }
                    return new Data(data2, iHashCode, version);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "T"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3", f = "DataStoreImpl.kt", i = {}, l = {395, 396, 398}, m = "invokeSuspend", n = {}, s = {})
    static final class C07423 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<T> $newData;
        final /* synthetic */ Ref.IntRef $version;
        Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07423(Ref.ObjectRef<T> objectRef, DataStoreImpl<T> dataStoreImpl, Ref.IntRef intRef, Continuation<? super C07423> continuation) {
            super(1, continuation);
            this.$newData = objectRef;
            this.this$0 = dataStoreImpl;
            this.$version = intRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C07423(this.$newData, this.this$0, this.$version, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C07423) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:20:0x006a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:21:0x006b  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            C07423 c07423;
            Ref.IntRef intRef;
            Object obj2;
            Ref.ObjectRef<T> objectRef;
            C07423 c07424;
            Ref.IntRef intRef2;
            Object version;
            Ref.IntRef intRef3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
                switch (r1) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Ref.ObjectRef<T> objectRef2 = this.$newData;
                        this.L$0 = objectRef2;
                        this.label = 1;
                        Object dataFromFileOrDefault = this.this$0.readDataFromFileOrDefault(this);
                        if (dataFromFileOrDefault == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = (T) dataFromFileOrDefault;
                        objectRef = objectRef2;
                        c07424 = this;
                        try {
                            objectRef.element = (T) obj;
                            intRef2 = c07424.$version;
                            c07424.L$0 = intRef2;
                            c07424.label = 2;
                            version = c07424.this$0.getCoordinator().getVersion(c07424);
                            if (version == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            intRef3 = intRef2;
                            obj = (T) version;
                            c07424 = c07424;
                            intRef3.element = ((Number) obj).intValue();
                            return Unit.INSTANCE;
                        } catch (CorruptionException e) {
                            obj = (T) obj;
                            c07423 = c07424;
                            intRef = c07423.$version;
                            c07423.L$0 = intRef;
                            c07423.label = 3;
                            Object objWriteData$datastore_core_release = c07423.this$0.writeData$datastore_core_release(c07423.$newData.element, true, c07423);
                            if (objWriteData$datastore_core_release == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj2 = obj;
                            obj = (T) objWriteData$datastore_core_release;
                            intRef.element = ((Number) obj).intValue();
                        }
                        break;
                    case 1:
                        Ref.ObjectRef<T> objectRef3 = (Ref.ObjectRef) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objectRef = objectRef3;
                        c07424 = this;
                        objectRef.element = (T) obj;
                        intRef2 = c07424.$version;
                        c07424.L$0 = intRef2;
                        c07424.label = 2;
                        version = c07424.this$0.getCoordinator().getVersion(c07424);
                        if (version == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        intRef3 = intRef2;
                        obj = (T) version;
                        c07424 = c07424;
                        intRef3.element = ((Number) obj).intValue();
                        return Unit.INSTANCE;
                    case 2:
                        Ref.IntRef intRef4 = (Ref.IntRef) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        intRef3 = intRef4;
                        c07424 = this;
                        obj = (T) obj;
                        intRef3.element = ((Number) obj).intValue();
                        return Unit.INSTANCE;
                    case 3:
                        intRef = (Ref.IntRef) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        obj2 = obj;
                        intRef.element = ((Number) obj).intValue();
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (CorruptionException e2) {
                c07423 = r1;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> Object doWithWriteFileLock(boolean hasWriteFileLock, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        if (hasWriteFileLock) {
            return function1.invoke(continuation);
        }
        return getCoordinator().lock(new AnonymousClass3(function1, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$doWithWriteFileLock$3, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "R", "T"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$doWithWriteFileLock$3", f = "DataStoreImpl.kt", i = {}, l = {424}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3<R> extends SuspendLambda implements Function1<Continuation<? super R>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super R> continuation) {
            return ((AnonymousClass3) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Function1<Continuation<? super R>, Object> function1 = this.$block;
                    this.label = 1;
                    Object objInvoke = function1.invoke(this);
                    return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001BD\u0012=\u0010\u0002\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00040\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u000e\u001a\u00020\nH\u0094@¢\u0006\u0002\u0010\u000fRG\u0010\r\u001a;\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "Landroidx/datastore/core/RunOnce;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "api", "Lkotlin/coroutines/Continuation;", "", "", "(Landroidx/datastore/core/DataStoreImpl;Ljava/util/List;)V", "initTasks", "doRun", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    final class InitDataStore extends RunOnce {
        private List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasks;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public InitDataStore(DataStoreImpl this$0, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasksList) {
            Intrinsics.checkNotNullParameter(initTasksList, "initTasksList");
            this.this$0 = this$0;
            this.initTasks = CollectionsKt.toList(initTasksList);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // androidx.datastore.core.RunOnce
        protected Object doRun(Continuation<? super Unit> continuation) throws CorruptionException {
            DataStoreImpl$InitDataStore$doRun$1 dataStoreImpl$InitDataStore$doRun$1;
            Object dataOrHandleCorruption;
            InitDataStore initDataStore;
            Object objLock;
            Data data;
            if (continuation instanceof DataStoreImpl$InitDataStore$doRun$1) {
                dataStoreImpl$InitDataStore$doRun$1 = (DataStoreImpl$InitDataStore$doRun$1) continuation;
                if ((dataStoreImpl$InitDataStore$doRun$1.label & Integer.MIN_VALUE) != 0) {
                    dataStoreImpl$InitDataStore$doRun$1.label -= Integer.MIN_VALUE;
                } else {
                    dataStoreImpl$InitDataStore$doRun$1 = new DataStoreImpl$InitDataStore$doRun$1(this, continuation);
                }
            } else {
                dataStoreImpl$InitDataStore$doRun$1 = new DataStoreImpl$InitDataStore$doRun$1(this, continuation);
            }
            DataStoreImpl$InitDataStore$doRun$1 dataStoreImpl$InitDataStore$doRun$2 = dataStoreImpl$InitDataStore$doRun$1;
            Object $result = dataStoreImpl$InitDataStore$doRun$2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (dataStoreImpl$InitDataStore$doRun$2.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    if (this.initTasks != null) {
                        List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> list = this.initTasks;
                        Intrinsics.checkNotNull(list);
                        if (!list.isEmpty()) {
                            InterProcessCoordinator coordinator = this.this$0.getCoordinator();
                            DataStoreImpl$InitDataStore$doRun$initData$1 dataStoreImpl$InitDataStore$doRun$initData$1 = new DataStoreImpl$InitDataStore$doRun$initData$1(this.this$0, this, null);
                            dataStoreImpl$InitDataStore$doRun$2.L$0 = this;
                            dataStoreImpl$InitDataStore$doRun$2.label = 2;
                            objLock = coordinator.lock(dataStoreImpl$InitDataStore$doRun$initData$1, dataStoreImpl$InitDataStore$doRun$2);
                            if (objLock == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            initDataStore = this;
                            data = (Data) objLock;
                            Data initData = data;
                            ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(initData);
                            return Unit.INSTANCE;
                        }
                    }
                    DataStoreImpl<T> dataStoreImpl = this.this$0;
                    dataStoreImpl$InitDataStore$doRun$2.L$0 = this;
                    dataStoreImpl$InitDataStore$doRun$2.label = 1;
                    dataOrHandleCorruption = dataStoreImpl.readDataOrHandleCorruption(false, dataStoreImpl$InitDataStore$doRun$2);
                    if (dataOrHandleCorruption == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    initDataStore = this;
                    data = (Data) dataOrHandleCorruption;
                    Data initData2 = data;
                    ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(initData2);
                    return Unit.INSTANCE;
                case 1:
                    initDataStore = (InitDataStore) dataStoreImpl$InitDataStore$doRun$2.L$0;
                    ResultKt.throwOnFailure($result);
                    dataOrHandleCorruption = $result;
                    data = (Data) dataOrHandleCorruption;
                    Data initData3 = data;
                    ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(initData3);
                    return Unit.INSTANCE;
                case 2:
                    initDataStore = (InitDataStore) dataStoreImpl$InitDataStore$doRun$2.L$0;
                    ResultKt.throwOnFailure($result);
                    objLock = $result;
                    data = (Data) objLock;
                    Data initData4 = data;
                    ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(initData4);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
