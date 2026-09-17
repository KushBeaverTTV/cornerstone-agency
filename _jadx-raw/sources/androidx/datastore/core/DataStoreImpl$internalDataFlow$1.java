package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: DataStoreImpl.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$internalDataFlow$1", f = "DataStoreImpl.kt", i = {0, 1, 1}, l = {123, 125, 132}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "startState"}, s = {"L$0", "L$0", "L$1"})
final class DataStoreImpl$internalDataFlow$1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$internalDataFlow$1(DataStoreImpl<T> dataStoreImpl, Continuation<? super DataStoreImpl$internalDataFlow$1> continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DataStoreImpl$internalDataFlow$1 dataStoreImpl$internalDataFlow$1 = new DataStoreImpl$internalDataFlow$1(this.this$0, continuation);
        dataStoreImpl$internalDataFlow$1.L$0 = obj;
        return dataStoreImpl$internalDataFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        return ((DataStoreImpl$internalDataFlow$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0056  */
    /* JADX WARN: Code duplicated, block: B:17:0x006d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:18:0x006e  */
    /* JADX WARN: Code duplicated, block: B:20:0x0077  */
    /* JADX WARN: Code duplicated, block: B:22:0x007b  */
    /* JADX WARN: Code duplicated, block: B:24:0x007f  */
    /* JADX WARN: Code duplicated, block: B:26:0x0083  */
    /* JADX WARN: Code duplicated, block: B:30:0x00c5 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:34:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:36:0x00d3  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        ?? r3;
        DataStoreImpl$internalDataFlow$1<T> dataStoreImpl$internalDataFlow$1;
        State state;
        State state2;
        DataStoreImpl$internalDataFlow$1<T> dataStoreImpl$internalDataFlow$2;
        ?? r4;
        final Flow flowDropWhile;
        ?? r5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                this.L$0 = flowCollector;
                this.label = 1;
                Object state3 = this.this$0.readState(false, this);
                if (state3 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = state3;
                r3 = flowCollector;
                dataStoreImpl$internalDataFlow$1 = this;
                state = (State) obj;
                if (state instanceof Data) {
                    dataStoreImpl$internalDataFlow$1.L$0 = r3;
                    dataStoreImpl$internalDataFlow$1.L$1 = state;
                    dataStoreImpl$internalDataFlow$1.label = 2;
                    if (r3.emit(((Data) state).getValue(), dataStoreImpl$internalDataFlow$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    DataStoreImpl$internalDataFlow$1<T> dataStoreImpl$internalDataFlow$3 = dataStoreImpl$internalDataFlow$1;
                    state2 = state;
                    obj = obj;
                    dataStoreImpl$internalDataFlow$2 = dataStoreImpl$internalDataFlow$3;
                    r5 = r3;
                    DataStoreImpl$internalDataFlow$1<T> dataStoreImpl$internalDataFlow$4 = dataStoreImpl$internalDataFlow$2;
                    obj = obj;
                    state = state2;
                    dataStoreImpl$internalDataFlow$1 = dataStoreImpl$internalDataFlow$4;
                    r4 = r5;
                } else {
                    if (!(state instanceof UnInitialized)) {
                        throw new IllegalStateException("This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542".toString());
                    }
                    if (!(state instanceof ReadException)) {
                        throw ((ReadException) state).getReadException();
                    }
                    if (state instanceof Final) {
                        r4 = r3;
                        return Unit.INSTANCE;
                    }
                }
                r4 = r3;
                flowDropWhile = FlowKt.dropWhile(FlowKt.takeWhile(((DataStoreImpl) dataStoreImpl$internalDataFlow$1.this$0).inMemoryCache.getFlow(), new AnonymousClass1(null)), new AnonymousClass2(state, null));
                dataStoreImpl$internalDataFlow$1.L$0 = null;
                dataStoreImpl$internalDataFlow$1.L$1 = null;
                dataStoreImpl$internalDataFlow$1.label = 3;
                if (FlowKt.emitAll((FlowCollector) r4, new Flow<T>() { // from class: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1

                    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1$2, reason: invalid class name */
                    /* JADX INFO: compiled from: Emitters.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    public static final class AnonymousClass2<T> implements FlowCollector {
                        final /* synthetic */ FlowCollector $this_unsafeFlow;

                        /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1$2$1, reason: invalid class name */
                        /* JADX INFO: compiled from: Emitters.kt */
                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                        @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1$2", f = "DataStoreImpl.kt", i = {}, l = {223}, m = "emit", n = {}, s = {})
                        public static final class AnonymousClass1 extends ContinuationImpl {
                            Object L$0;
                            int label;
                            /* synthetic */ Object result;

                            public AnonymousClass1(Continuation continuation) {
                                super(continuation);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                this.result = obj;
                                this.label |= Integer.MIN_VALUE;
                                return AnonymousClass2.this.emit(null, this);
                            }
                        }

                        public AnonymousClass2(FlowCollector flowCollector) {
                            this.$this_unsafeFlow = flowCollector;
                        }

                        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object value, Continuation continuation) throws Throwable {
                            AnonymousClass1 anonymousClass1;
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
                            switch (anonymousClass2.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    FlowCollector flowCollector = this.$this_unsafeFlow;
                                    State it = (State) value;
                                    if (it instanceof ReadException) {
                                        throw ((ReadException) it).getReadException();
                                    }
                                    if (!(it instanceof Data)) {
                                        if (it instanceof Final ? true : it instanceof UnInitialized) {
                                            throw new IllegalStateException("This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542".toString());
                                        }
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    Object value2 = ((Data) it).getValue();
                                    anonymousClass2.label = 1;
                                    if (flowCollector.emit(value2, anonymousClass2) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public Object collect(FlowCollector collector, Continuation $completion) {
                        Object objCollect = flowDropWhile.collect(new AnonymousClass2(collector), $completion);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, dataStoreImpl$internalDataFlow$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
                r3 = flowCollector2;
                dataStoreImpl$internalDataFlow$1 = this;
                state = (State) obj;
                if (state instanceof Data) {
                    dataStoreImpl$internalDataFlow$1.L$0 = r3;
                    dataStoreImpl$internalDataFlow$1.L$1 = state;
                    dataStoreImpl$internalDataFlow$1.label = 2;
                    if (r3.emit(((Data) state).getValue(), dataStoreImpl$internalDataFlow$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    DataStoreImpl$internalDataFlow$1<T> dataStoreImpl$internalDataFlow$5 = dataStoreImpl$internalDataFlow$1;
                    state2 = state;
                    obj = obj;
                    dataStoreImpl$internalDataFlow$2 = dataStoreImpl$internalDataFlow$5;
                    r5 = r3;
                    DataStoreImpl$internalDataFlow$1<T> dataStoreImpl$internalDataFlow$6 = dataStoreImpl$internalDataFlow$2;
                    obj = obj;
                    state = state2;
                    dataStoreImpl$internalDataFlow$1 = dataStoreImpl$internalDataFlow$6;
                    r4 = r5;
                } else {
                    if (!(state instanceof UnInitialized)) {
                        throw new IllegalStateException("This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542".toString());
                    }
                    if (!(state instanceof ReadException)) {
                        throw ((ReadException) state).getReadException();
                    }
                    if (state instanceof Final) {
                        r4 = r3;
                        return Unit.INSTANCE;
                    }
                }
                r4 = r3;
                flowDropWhile = FlowKt.dropWhile(FlowKt.takeWhile(((DataStoreImpl) dataStoreImpl$internalDataFlow$1.this$0).inMemoryCache.getFlow(), new AnonymousClass1(null)), new AnonymousClass2(state, null));
                dataStoreImpl$internalDataFlow$1.L$0 = null;
                dataStoreImpl$internalDataFlow$1.L$1 = null;
                dataStoreImpl$internalDataFlow$1.label = 3;
                if (FlowKt.emitAll((FlowCollector) r4, new Flow<T>() { // from class: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1

                    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1$2, reason: invalid class name */
                    /* JADX INFO: compiled from: Emitters.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    public static final class AnonymousClass2<T> implements FlowCollector {
                        final /* synthetic */ FlowCollector $this_unsafeFlow;

                        /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1$2$1, reason: invalid class name */
                        /* JADX INFO: compiled from: Emitters.kt */
                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                        @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1$2", f = "DataStoreImpl.kt", i = {}, l = {223}, m = "emit", n = {}, s = {})
                        public static final class AnonymousClass1 extends ContinuationImpl {
                            Object L$0;
                            int label;
                            /* synthetic */ Object result;

                            public AnonymousClass1(Continuation continuation) {
                                super(continuation);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                this.result = obj;
                                this.label |= Integer.MIN_VALUE;
                                return AnonymousClass2.this.emit(null, this);
                            }
                        }

                        public AnonymousClass2(FlowCollector flowCollector) {
                            this.$this_unsafeFlow = flowCollector;
                        }

                        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object value, Continuation continuation) throws Throwable {
                            AnonymousClass1 anonymousClass1;
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
                            switch (anonymousClass2.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    FlowCollector flowCollector = this.$this_unsafeFlow;
                                    State it = (State) value;
                                    if (it instanceof ReadException) {
                                        throw ((ReadException) it).getReadException();
                                    }
                                    if (!(it instanceof Data)) {
                                        if (it instanceof Final ? true : it instanceof UnInitialized) {
                                            throw new IllegalStateException("This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542".toString());
                                        }
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    Object value2 = ((Data) it).getValue();
                                    anonymousClass2.label = 1;
                                    if (flowCollector.emit(value2, anonymousClass2) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public Object collect(FlowCollector collector, Continuation $completion) {
                        Object objCollect = flowDropWhile.collect(new AnonymousClass2(collector), $completion);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, dataStoreImpl$internalDataFlow$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                dataStoreImpl$internalDataFlow$2 = this;
                state2 = (State) dataStoreImpl$internalDataFlow$2.L$1;
                FlowCollector flowCollector3 = (FlowCollector) dataStoreImpl$internalDataFlow$2.L$0;
                ResultKt.throwOnFailure(obj);
                r5 = flowCollector3;
                DataStoreImpl$internalDataFlow$1<T> dataStoreImpl$internalDataFlow$7 = dataStoreImpl$internalDataFlow$2;
                obj = obj;
                state = state2;
                dataStoreImpl$internalDataFlow$1 = dataStoreImpl$internalDataFlow$7;
                r4 = r5;
                r4 = r3;
                flowDropWhile = FlowKt.dropWhile(FlowKt.takeWhile(((DataStoreImpl) dataStoreImpl$internalDataFlow$1.this$0).inMemoryCache.getFlow(), new AnonymousClass1(null)), new AnonymousClass2(state, null));
                dataStoreImpl$internalDataFlow$1.L$0 = null;
                dataStoreImpl$internalDataFlow$1.L$1 = null;
                dataStoreImpl$internalDataFlow$1.label = 3;
                if (FlowKt.emitAll((FlowCollector) r4, new Flow<T>() { // from class: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1

                    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1$2, reason: invalid class name */
                    /* JADX INFO: compiled from: Emitters.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    public static final class AnonymousClass2<T> implements FlowCollector {
                        final /* synthetic */ FlowCollector $this_unsafeFlow;

                        /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1$2$1, reason: invalid class name */
                        /* JADX INFO: compiled from: Emitters.kt */
                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                        @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$internalDataFlow$1$invokeSuspend$$inlined$map$1$2", f = "DataStoreImpl.kt", i = {}, l = {223}, m = "emit", n = {}, s = {})
                        public static final class AnonymousClass1 extends ContinuationImpl {
                            Object L$0;
                            int label;
                            /* synthetic */ Object result;

                            public AnonymousClass1(Continuation continuation) {
                                super(continuation);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                this.result = obj;
                                this.label |= Integer.MIN_VALUE;
                                return AnonymousClass2.this.emit(null, this);
                            }
                        }

                        public AnonymousClass2(FlowCollector flowCollector) {
                            this.$this_unsafeFlow = flowCollector;
                        }

                        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object value, Continuation continuation) throws Throwable {
                            AnonymousClass1 anonymousClass1;
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
                            switch (anonymousClass2.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    FlowCollector flowCollector = this.$this_unsafeFlow;
                                    State it = (State) value;
                                    if (it instanceof ReadException) {
                                        throw ((ReadException) it).getReadException();
                                    }
                                    if (!(it instanceof Data)) {
                                        if (it instanceof Final ? true : it instanceof UnInitialized) {
                                            throw new IllegalStateException("This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542".toString());
                                        }
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    Object value2 = ((Data) it).getValue();
                                    anonymousClass2.label = 1;
                                    if (flowCollector.emit(value2, anonymousClass2) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public Object collect(FlowCollector collector, Continuation $completion) {
                        Object objCollect = flowDropWhile.collect(new AnonymousClass2(collector), $completion);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, dataStoreImpl$internalDataFlow$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 3:
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "T", "it", "Landroidx/datastore/core/State;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$internalDataFlow$1$1", f = "DataStoreImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<State<T>, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(State<T> state, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    State it = (State) this.L$0;
                    return Boxing.boxBoolean(!(it instanceof Final));
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$internalDataFlow$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "T", "it", "Landroidx/datastore/core/State;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$internalDataFlow$1$2", f = "DataStoreImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<State<T>, Continuation<? super Boolean>, Object> {
        final /* synthetic */ State<T> $startState;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(State<T> state, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$startState = state;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$startState, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(State<T> state, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    State it = (State) this.L$0;
                    return Boxing.boxBoolean((it instanceof Data) && it.getVersion() <= this.$startState.getVersion());
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
