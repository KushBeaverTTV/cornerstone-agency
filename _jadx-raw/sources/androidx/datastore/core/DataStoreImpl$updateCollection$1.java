package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: DataStoreImpl.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$updateCollection$1", f = "DataStoreImpl.kt", i = {}, l = {80, 81}, m = "invokeSuspend", n = {}, s = {})
final class DataStoreImpl$updateCollection$1 extends SuspendLambda implements Function2<FlowCollector<? super Unit>, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$updateCollection$1(DataStoreImpl<T> dataStoreImpl, Continuation<? super DataStoreImpl$updateCollection$1> continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DataStoreImpl$updateCollection$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super Unit> flowCollector, Continuation<? super Unit> continuation) {
        return ((DataStoreImpl$updateCollection$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0055 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:14:0x0056  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        DataStoreImpl$updateCollection$1 dataStoreImpl$updateCollection$1;
        Flow flowConflate;
        final DataStoreImpl<T> dataStoreImpl;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                dataStoreImpl$updateCollection$1 = this;
                dataStoreImpl$updateCollection$1.label = 1;
                if (((DataStoreImpl) dataStoreImpl$updateCollection$1.this$0).readAndInit.awaitComplete(dataStoreImpl$updateCollection$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowConflate = FlowKt.conflate(dataStoreImpl$updateCollection$1.this$0.getCoordinator().getUpdateNotifications());
                dataStoreImpl = dataStoreImpl$updateCollection$1.this$0;
                dataStoreImpl$updateCollection$1.label = 2;
                if (flowConflate.collect(new FlowCollector() { // from class: androidx.datastore.core.DataStoreImpl$updateCollection$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((Unit) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(Unit it, Continuation<? super Unit> continuation) {
                        Object dataAndUpdateCache;
                        return ((((DataStoreImpl) dataStoreImpl).inMemoryCache.getCurrentState() instanceof Final) || (dataAndUpdateCache = dataStoreImpl.readDataAndUpdateCache(true, continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : dataAndUpdateCache;
                    }
                }, dataStoreImpl$updateCollection$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                dataStoreImpl$updateCollection$1 = this;
                ResultKt.throwOnFailure($result);
                flowConflate = FlowKt.conflate(dataStoreImpl$updateCollection$1.this$0.getCoordinator().getUpdateNotifications());
                dataStoreImpl = dataStoreImpl$updateCollection$1.this$0;
                dataStoreImpl$updateCollection$1.label = 2;
                if (flowConflate.collect(new FlowCollector() { // from class: androidx.datastore.core.DataStoreImpl$updateCollection$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((Unit) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(Unit it, Continuation<? super Unit> continuation) {
                        Object dataAndUpdateCache;
                        return ((((DataStoreImpl) dataStoreImpl).inMemoryCache.getCurrentState() instanceof Final) || (dataAndUpdateCache = dataStoreImpl.readDataAndUpdateCache(true, continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : dataAndUpdateCache;
                    }
                }, dataStoreImpl$updateCollection$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
