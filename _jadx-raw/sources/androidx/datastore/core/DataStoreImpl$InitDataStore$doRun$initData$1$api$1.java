package androidx.datastore.core;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.sync.Mutex;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: DataStoreImpl.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001JA\u0010\u0002\u001a\u00028\u000021\u0010\u0003\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004H\u0096@¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"androidx/datastore/core/DataStoreImpl$InitDataStore$doRun$initData$1$api$1", "Landroidx/datastore/core/InitializerApi;", "updateData", "transform", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "t", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DataStoreImpl$InitDataStore$doRun$initData$1$api$1<T> implements InitializerApi<T> {
    final /* synthetic */ Ref.ObjectRef<T> $currentData;
    final /* synthetic */ Ref.BooleanRef $initializationComplete;
    final /* synthetic */ Mutex $updateLock;
    final /* synthetic */ DataStoreImpl<T> this$0;

    DataStoreImpl$InitDataStore$doRun$initData$1$api$1(Mutex $updateLock, Ref.BooleanRef $initializationComplete, Ref.ObjectRef<T> objectRef, DataStoreImpl<T> dataStoreImpl) {
        this.$updateLock = $updateLock;
        this.$initializationComplete = $initializationComplete;
        this.$currentData = objectRef;
        this.this$0 = dataStoreImpl;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00ab A[Catch: all -> 0x005c, TRY_LEAVE, TryCatch #1 {all -> 0x005c, blocks: (B:18:0x0051, B:28:0x00a7, B:30:0x00ab, B:49:0x00f8, B:50:0x0103), top: B:55:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00c1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:33:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:37:0x00d0 A[Catch: all -> 0x00f3, TRY_LEAVE, TryCatch #0 {all -> 0x00f3, blocks: (B:35:0x00c8, B:37:0x00d0), top: B:53:0x00c8 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00e0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:42:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:48:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x005d: MOVE (r4 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY] A[D('owner$iv' java.lang.Object)]), block:B:21:0x005d */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x005e: MOVE (r5 I:??[OBJECT, ARRAY] A[D('owner$iv' java.lang.Object)]) = (r6 I:??[OBJECT, ARRAY] A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)]), block:B:21:0x005d */
    @Override // androidx.datastore.core.InitializerApi
    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) throws Throwable {
        DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1 dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1;
        Object obj;
        Object obj2;
        Mutex mutex;
        Mutex mutex2;
        Function2<? super T, ? super Continuation<? super T>, ? extends Object> function3;
        Ref.BooleanRef booleanRef;
        Ref.ObjectRef<T> objectRef;
        Mutex mutex3;
        Object obj3;
        DataStoreImpl dataStoreImpl;
        Object objInvoke;
        Mutex mutex4;
        Object obj4;
        Object obj5;
        DataStoreImpl dataStoreImpl2;
        int i;
        int i2;
        T t;
        if (continuation instanceof DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1) {
            dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1 = (DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1) continuation;
            if ((dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1.label -= Integer.MIN_VALUE;
            } else {
                dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1 = new DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1(this, continuation);
            }
        } else {
            dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1 = new DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1(this, continuation);
        }
        DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1 dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2 = dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1;
        Object obj6 = dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.label) {
                case 0:
                    ResultKt.throwOnFailure(obj6);
                    function3 = function2;
                    Mutex mutex5 = this.$updateLock;
                    booleanRef = this.$initializationComplete;
                    objectRef = this.$currentData;
                    DataStoreImpl dataStoreImpl3 = this.this$0;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$0 = function3;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$1 = mutex5;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$2 = booleanRef;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$3 = objectRef;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$4 = dataStoreImpl3;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.label = 1;
                    if (mutex5.lock(null, dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutex3 = mutex5;
                    obj3 = null;
                    dataStoreImpl = dataStoreImpl3;
                    if (!booleanRef.element) {
                        throw new IllegalStateException("InitializerApi.updateData should not be called after initialization is complete.".toString());
                    }
                    T t2 = objectRef.element;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$0 = mutex3;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$1 = objectRef;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$2 = dataStoreImpl;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$3 = null;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$4 = null;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.label = 2;
                    objInvoke = function3.invoke(t2, dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutex4 = mutex3;
                    obj4 = obj3;
                    obj5 = objInvoke;
                    dataStoreImpl2 = dataStoreImpl;
                    i = 0;
                    try {
                        if (Intrinsics.areEqual(obj5, objectRef.element)) {
                            obj2 = obj4;
                            mutex2 = mutex4;
                        } else {
                            dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$0 = mutex4;
                            dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$1 = objectRef;
                            dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$2 = obj5;
                            dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.label = 3;
                            if (dataStoreImpl2.writeData$datastore_core_release(obj5, false, dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            i2 = i;
                            t = (T) obj5;
                            obj2 = obj4;
                            mutex2 = mutex4;
                            objectRef.element = t;
                        }
                        T t3 = objectRef.element;
                        mutex2.unlock(obj2);
                        return t3;
                    } catch (Throwable th) {
                        th = th;
                        obj2 = obj4;
                        mutex2 = mutex4;
                        mutex2.unlock(obj2);
                        throw th;
                    }
                case 1:
                    dataStoreImpl = (DataStoreImpl) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$4;
                    objectRef = (Ref.ObjectRef) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$3;
                    booleanRef = (Ref.BooleanRef) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$2;
                    obj3 = null;
                    mutex3 = (Mutex) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$1;
                    function3 = (Function2) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$0;
                    ResultKt.throwOnFailure(obj6);
                    if (!booleanRef.element) {
                        throw new IllegalStateException("InitializerApi.updateData should not be called after initialization is complete.".toString());
                    }
                    T t4 = objectRef.element;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$0 = mutex3;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$1 = objectRef;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$2 = dataStoreImpl;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$3 = null;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$4 = null;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.label = 2;
                    objInvoke = function3.invoke(t4, dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutex4 = mutex3;
                    obj4 = obj3;
                    obj5 = objInvoke;
                    dataStoreImpl2 = dataStoreImpl;
                    i = 0;
                    if (Intrinsics.areEqual(obj5, objectRef.element)) {
                        dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$0 = mutex4;
                        dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$1 = objectRef;
                        dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$2 = obj5;
                        dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.label = 3;
                        if (dataStoreImpl2.writeData$datastore_core_release(obj5, false, dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i2 = i;
                        t = (T) obj5;
                        obj2 = obj4;
                        mutex2 = mutex4;
                        objectRef.element = t;
                    } else {
                        obj2 = obj4;
                        mutex2 = mutex4;
                    }
                    T t5 = objectRef.element;
                    mutex2.unlock(obj2);
                    return t5;
                case 2:
                    i = 0;
                    DataStoreImpl dataStoreImpl4 = (DataStoreImpl) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$2;
                    Ref.ObjectRef<T> objectRef2 = (Ref.ObjectRef) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$1;
                    Mutex mutex6 = (Mutex) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$0;
                    ResultKt.throwOnFailure(obj6);
                    mutex4 = mutex6;
                    obj4 = null;
                    obj5 = obj6;
                    dataStoreImpl2 = dataStoreImpl4;
                    objectRef = objectRef2;
                    if (Intrinsics.areEqual(obj5, objectRef.element)) {
                        dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$0 = mutex4;
                        dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$1 = objectRef;
                        dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$2 = obj5;
                        dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.label = 3;
                        if (dataStoreImpl2.writeData$datastore_core_release(obj5, false, dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i2 = i;
                        t = (T) obj5;
                        obj2 = obj4;
                        mutex2 = mutex4;
                        objectRef.element = t;
                    } else {
                        obj2 = obj4;
                        mutex2 = mutex4;
                    }
                    T t6 = objectRef.element;
                    mutex2.unlock(obj2);
                    return t6;
                case 3:
                    i2 = 0;
                    t = (T) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$2;
                    objectRef = (Ref.ObjectRef) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$1;
                    obj2 = null;
                    mutex2 = (Mutex) dataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$2.L$0;
                    try {
                        ResultKt.throwOnFailure(obj6);
                        objectRef.element = t;
                        T t7 = objectRef.element;
                        mutex2.unlock(obj2);
                        return t7;
                    } catch (Throwable th2) {
                        th = th2;
                        mutex2.unlock(obj2);
                        throw th;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th3) {
            th = th3;
            obj2 = obj;
            mutex2 = mutex;
        }
    }
}
