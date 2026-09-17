package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: Combine.kt */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1", f = "Combine.kt", i = {0, 0}, l = {123}, m = "invokeSuspend", n = {"second", "collectJob"}, s = {"L$0", "L$1"})
final class CombineKt$zipImpl$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T1> $flow;
    final /* synthetic */ Flow<T2> $flow2;
    final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
    final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CombineKt$zipImpl$1$1(Flow<? extends T2> flow, Flow<? extends T1> flow2, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super CombineKt$zipImpl$1$1> continuation) {
        super(2, continuation);
        this.$flow2 = flow;
        this.$flow = flow2;
        this.$this_unsafeFlow = flowCollector;
        this.$transform = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$1 = new CombineKt$zipImpl$1$1(this.$flow2, this.$flow, this.$this_unsafeFlow, this.$transform, continuation);
        combineKt$zipImpl$1$1.L$0 = obj;
        return combineKt$zipImpl$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        ReceiveChannel second;
        CompletableJob collectJob;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    second = ProduceKt.produce$default($this$coroutineScope, null, 0, new CombineKt$zipImpl$1$1$second$1(this.$flow2, null), 3, null);
                    final CompletableJob collectJob2 = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
                    Intrinsics.checkNotNull(second, "null cannot be cast to non-null type kotlinx.coroutines.channels.SendChannel<*>");
                    ((SendChannel) second).invokeOnClose(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                            invoke2(th);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Throwable it) {
                            if (collectJob2.isActive()) {
                                collectJob2.cancel((CancellationException) new AbortFlowException(collectJob2));
                            }
                        }
                    });
                    try {
                        CoroutineContext scopeContext = $this$coroutineScope.getCoroutineContext();
                        Object cnt = ThreadContextKt.threadContextElements(scopeContext);
                        this.L$0 = second;
                        this.L$1 = collectJob2;
                        this.label = 1;
                        if (ChannelFlowKt.withContextUndispatched$default($this$coroutineScope.getCoroutineContext().plus(collectJob2), Unit.INSTANCE, null, new AnonymousClass2(this.$flow, scopeContext, cnt, second, this.$this_unsafeFlow, this.$transform, collectJob2, null), this, 4, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ReceiveChannel.DefaultImpls.cancel$default(second, (CancellationException) null, 1, (Object) null);
                        return Unit.INSTANCE;
                    } catch (AbortFlowException e) {
                        e = e;
                        collectJob = collectJob2;
                        FlowExceptions_commonKt.checkOwnership(e, collectJob);
                    }
                    break;
                case 1:
                    collectJob = (CompletableJob) this.L$1;
                    second = (ReceiveChannel) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        break;
                    } catch (AbortFlowException e2) {
                        e = e2;
                        FlowExceptions_commonKt.checkOwnership(e, collectJob);
                    }
                    ReceiveChannel.DefaultImpls.cancel$default(second, (CancellationException) null, 1, (Object) null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            ReceiveChannel.DefaultImpls.cancel$default(second, (CancellationException) null, 1, (Object) null);
            throw th;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: Combine.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2", f = "Combine.kt", i = {}, l = {124}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
        final /* synthetic */ Object $cnt;
        final /* synthetic */ CompletableJob $collectJob;
        final /* synthetic */ Flow<T1> $flow;
        final /* synthetic */ CoroutineContext $scopeContext;
        final /* synthetic */ ReceiveChannel<Object> $second;
        final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
        final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Flow<? extends T1> flow, CoroutineContext coroutineContext, Object obj, ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, CompletableJob completableJob, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$flow = flow;
            this.$scopeContext = coroutineContext;
            this.$cnt = obj;
            this.$second = receiveChannel;
            this.$this_unsafeFlow = flowCollector;
            this.$transform = function3;
            this.$collectJob = completableJob;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$flow, this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform, this.$collectJob, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: Combine.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ Object $cnt;
            final /* synthetic */ CompletableJob $collectJob;
            final /* synthetic */ CoroutineContext $scopeContext;
            final /* synthetic */ ReceiveChannel<Object> $second;
            final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
            final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(CoroutineContext coroutineContext, Object obj, ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, CompletableJob completableJob) {
                this.$scopeContext = coroutineContext;
                this.$cnt = obj;
                this.$second = receiveChannel;
                this.$this_unsafeFlow = flowCollector;
                this.$transform = function3;
                this.$collectJob = completableJob;
            }

            /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: Combine.kt */
            @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1", f = "Combine.kt", i = {}, l = {WebSocketProtocol.PAYLOAD_SHORT, 129, 129}, m = "invokeSuspend", n = {}, s = {})
            static final class C01751 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
                final /* synthetic */ CompletableJob $collectJob;
                final /* synthetic */ ReceiveChannel<Object> $second;
                final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
                final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
                final /* synthetic */ T1 $value;
                Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C01751(ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, T1 t1, CompletableJob completableJob, Continuation<? super C01751> continuation) {
                    super(2, continuation);
                    this.$second = receiveChannel;
                    this.$this_unsafeFlow = flowCollector;
                    this.$transform = function3;
                    this.$value = t1;
                    this.$collectJob = completableJob;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C01751(this.$second, this.$this_unsafeFlow, this.$transform, this.$value, this.$collectJob, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
                    return ((C01751) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:14:0x004a  */
                /* JADX WARN: Code duplicated, block: B:16:0x0051  */
                /* JADX WARN: Code duplicated, block: B:18:0x0059  */
                /* JADX WARN: Code duplicated, block: B:20:0x0066  */
                /* JADX WARN: Code duplicated, block: B:23:0x0073 A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:24:0x0074  */
                /* JADX WARN: Code duplicated, block: B:27:0x0086 A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:28:0x0087  */
                /* JADX WARN: Code duplicated, block: B:31:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r4v4, types: [kotlinx.coroutines.flow.FlowCollector] */
                /* JADX WARN: Type inference failed for: r4v5 */
                /* JADX WARN: Type inference failed for: r4v6 */
                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) throws Throwable {
                    C01751 c01751;
                    Object objMo8900receiveCatchingJP2dKIU;
                    CompletableJob completableJob;
                    Object obj2;
                    Object objInvoke;
                    C01751 c01752;
                    Throwable thM8912exceptionOrNullimpl;
                    ?? r4;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            c01751 = this;
                            c01751.label = 1;
                            objMo8900receiveCatchingJP2dKIU = c01751.$second.mo8900receiveCatchingJP2dKIU(c01751);
                            if (objMo8900receiveCatchingJP2dKIU == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            completableJob = c01751.$collectJob;
                            if (objMo8900receiveCatchingJP2dKIU instanceof ChannelResult.Failed) {
                                thM8912exceptionOrNullimpl = ChannelResult.m8912exceptionOrNullimpl(objMo8900receiveCatchingJP2dKIU);
                                if (thM8912exceptionOrNullimpl == null) {
                                    throw new AbortFlowException(completableJob);
                                }
                                throw thM8912exceptionOrNullimpl;
                            }
                            obj2 = c01751.$this_unsafeFlow;
                            Function3 function3 = c01751.$transform;
                            Object obj3 = c01751.$value;
                            if (objMo8900receiveCatchingJP2dKIU == NullSurrogateKt.NULL) {
                                objMo8900receiveCatchingJP2dKIU = null;
                            }
                            c01751.L$0 = obj2;
                            c01751.label = 2;
                            objInvoke = function3.invoke(obj3, objMo8900receiveCatchingJP2dKIU, c01751);
                            if (objInvoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = objInvoke;
                            c01752 = c01751;
                            r4 = obj2;
                            c01752.L$0 = null;
                            c01752.label = 3;
                            if (r4.emit(obj, c01752) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        case 1:
                            c01751 = this;
                            ResultKt.throwOnFailure(obj);
                            objMo8900receiveCatchingJP2dKIU = ((ChannelResult) obj).getHolder();
                            completableJob = c01751.$collectJob;
                            if (objMo8900receiveCatchingJP2dKIU instanceof ChannelResult.Failed) {
                                thM8912exceptionOrNullimpl = ChannelResult.m8912exceptionOrNullimpl(objMo8900receiveCatchingJP2dKIU);
                                if (thM8912exceptionOrNullimpl == null) {
                                    throw new AbortFlowException(completableJob);
                                }
                                throw thM8912exceptionOrNullimpl;
                            }
                            obj2 = c01751.$this_unsafeFlow;
                            Function3 function4 = c01751.$transform;
                            Object obj4 = c01751.$value;
                            if (objMo8900receiveCatchingJP2dKIU == NullSurrogateKt.NULL) {
                                objMo8900receiveCatchingJP2dKIU = null;
                            }
                            c01751.L$0 = obj2;
                            c01751.label = 2;
                            objInvoke = function4.invoke(obj4, objMo8900receiveCatchingJP2dKIU, c01751);
                            if (objInvoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = objInvoke;
                            c01752 = c01751;
                            r4 = obj2;
                            c01752.L$0 = null;
                            c01752.label = 3;
                            if (r4.emit(obj, c01752) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        case 2:
                            FlowCollector flowCollector = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            r4 = flowCollector;
                            c01752 = this;
                            c01752.L$0 = null;
                            c01752.label = 3;
                            if (r4.emit(obj, c01752) == coroutine_suspended) {
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
            }

            /* JADX WARN: Code duplicated, block: B:7:0x0018  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(T1 t1, Continuation<? super Unit> continuation) {
                CombineKt$zipImpl$1$1$2$1$emit$1 combineKt$zipImpl$1$1$2$1$emit$1;
                if (continuation instanceof CombineKt$zipImpl$1$1$2$1$emit$1) {
                    combineKt$zipImpl$1$1$2$1$emit$1 = (CombineKt$zipImpl$1$1$2$1$emit$1) continuation;
                    if ((combineKt$zipImpl$1$1$2$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        combineKt$zipImpl$1$1$2$1$emit$1.label -= Integer.MIN_VALUE;
                    } else {
                        combineKt$zipImpl$1$1$2$1$emit$1 = new CombineKt$zipImpl$1$1$2$1$emit$1(this, continuation);
                    }
                } else {
                    combineKt$zipImpl$1$1$2$1$emit$1 = new CombineKt$zipImpl$1$1$2$1$emit$1(this, continuation);
                }
                Object $result = combineKt$zipImpl$1$1$2$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (combineKt$zipImpl$1$1$2$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineContext coroutineContext = this.$scopeContext;
                        Unit unit = Unit.INSTANCE;
                        Object obj = this.$cnt;
                        C01751 c01751 = new C01751(this.$second, this.$this_unsafeFlow, this.$transform, t1, this.$collectJob, null);
                        combineKt$zipImpl$1$1$2$1$emit$1.label = 1;
                        if (ChannelFlowKt.withContextUndispatched(coroutineContext, unit, obj, c01751, combineKt$zipImpl$1$1$2$1$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
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

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    Flow<T1> flow = this.$flow;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform, this.$collectJob);
                    this.label = 1;
                    if (flow.collect(anonymousClass1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure(obj);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }
}
