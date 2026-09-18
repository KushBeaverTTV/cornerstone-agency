package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.local.db.MessageEntity;
import ai.nous.aurora.domain.model.AuroraMessage;
import ai.nous.aurora.domain.model.OutboxExactlyOnce;
import androidx.autofill.HintConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.text.StringsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: Merge.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0015\u0010\u0005\u001a\u0011H\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\bH\n¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "value", "kotlinx/coroutines/flow/FlowKt__MergeKt$flatMapLatest$1"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository$observeMessages$$inlined$flatMapLatest$1", f = "DefaultSessionRepository.kt", i = {}, l = {190, 189}, m = "invokeSuspend", n = {}, s = {})
public final class DefaultSessionRepository$observeMessages$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3<FlowCollector<? super List<? extends AuroraMessage>>, Integer, Continuation, Object> {
    final /* synthetic */ String $sessionId$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ DefaultSessionRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSessionRepository$observeMessages$$inlined$flatMapLatest$1(Continuation continuation, DefaultSessionRepository defaultSessionRepository, String str) {
        super(3, continuation);
        this.this$0 = defaultSessionRepository;
        this.$sessionId$inlined = str;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super List<? extends AuroraMessage>> flowCollector, Integer num, Continuation continuation) {
        DefaultSessionRepository$observeMessages$$inlined$flatMapLatest$1 defaultSessionRepository$observeMessages$$inlined$flatMapLatest$1 = new DefaultSessionRepository$observeMessages$$inlined$flatMapLatest$1(continuation, this.this$0, this.$sessionId$inlined);
        defaultSessionRepository$observeMessages$$inlined$flatMapLatest$1.L$0 = flowCollector;
        defaultSessionRepository$observeMessages$$inlined$flatMapLatest$1.L$1 = num;
        return defaultSessionRepository$observeMessages$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0075 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:0x0076  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        FlowCollector flowCollector;
        final Flow<List<MessageEntity>> flowObserveMessages;
        final DefaultSessionRepository defaultSessionRepository;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                Object it = this.L$1;
                ((Number) it).intValue();
                DefaultSessionRepository defaultSessionRepository2 = this.this$0;
                this.L$0 = flowCollector2;
                this.label = 1;
                Object objOwnerKey = defaultSessionRepository2.ownerKey(this);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objOwnerKey;
                flowCollector = flowCollector2;
                String owner = (String) $result;
                flowObserveMessages = this.this$0.messages.observeMessages(this.$sessionId$inlined, owner);
                defaultSessionRepository = this.this$0;
                this.L$0 = null;
                this.label = 2;
                if (FlowKt.emitAll(flowCollector, new Flow<List<? extends AuroraMessage>>() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$observeMessages$lambda$9$$inlined$map$1

                    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$observeMessages$lambda$9$$inlined$map$1$2, reason: invalid class name */
                    /* JADX INFO: compiled from: Emitters.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                    public static final class AnonymousClass2 implements FlowCollector {
                        final /* synthetic */ FlowCollector $this_unsafeFlow;
                        final /* synthetic */ DefaultSessionRepository this$0;

                        /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$observeMessages$lambda$9$$inlined$map$1$2$1, reason: invalid class name */
                        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                        @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository$observeMessages$lambda$9$$inlined$map$1$2", f = "DefaultSessionRepository.kt", i = {}, l = {50}, m = "emit", n = {}, s = {})
                        public final class AnonymousClass1 extends ContinuationImpl {
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

                        public AnonymousClass2(FlowCollector flowCollector, DefaultSessionRepository defaultSessionRepository) {
                            this.$this_unsafeFlow = flowCollector;
                            this.this$0 = defaultSessionRepository;
                        }

                        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object value, Continuation $completion) {
                            AnonymousClass1 anonymousClass1;
                            if ($completion instanceof AnonymousClass1) {
                                anonymousClass1 = (AnonymousClass1) $completion;
                                if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                                    anonymousClass1.label -= Integer.MIN_VALUE;
                                } else {
                                    anonymousClass1 = new AnonymousClass1($completion);
                                }
                            } else {
                                anonymousClass1 = new AnonymousClass1($completion);
                            }
                            Object $result = anonymousClass1.result;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (anonymousClass1.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    FlowCollector flowCollector = this.$this_unsafeFlow;
                                    Iterable list = (List) value;
                                    Iterable $this$map$iv = list;
                                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                    for (Object item$iv$iv : $this$map$iv) {
                                        MessageEntity it = (MessageEntity) item$iv$iv;
                                        destination$iv$iv.add(this.this$0.toModel(it));
                                    }
                                    List<AuroraMessage> listDedupeByClientMsgId = OutboxExactlyOnce.INSTANCE.<AuroraMessage>dedupeByClientMsgId((List) destination$iv$iv, new Function1<AuroraMessage, String>() {
                                        @Override public String invoke(AuroraMessage m) { return m.getId(); }
                                    }, new Function1<AuroraMessage, String>() {
                                        @Override public String invoke(AuroraMessage m) { return m.getClientMsgId(); }
                                    }, new Function1<AuroraMessage, Boolean>() {
                                        @Override public Boolean invoke(AuroraMessage m) { return StringsKt.equals(m.getRole(), "user", true); }
                                    });
                                    anonymousClass1.label = 1;
                                    if (flowCollector.emit(listDedupeByClientMsgId, anonymousClass1) == coroutine_suspended) {
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

                    @Override // kotlinx.coroutines.flow.Flow
                    public Object collect(FlowCollector flowCollector3, Continuation $completion) {
                        Object objCollect = flowObserveMessages.collect(new AnonymousClass2(flowCollector3, defaultSessionRepository), $completion);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                FlowCollector flowCollector3 = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure($result);
                flowCollector = flowCollector3;
                String owner2 = (String) $result;
                flowObserveMessages = this.this$0.messages.observeMessages(this.$sessionId$inlined, owner2);
                defaultSessionRepository = this.this$0;
                this.L$0 = null;
                this.label = 2;
                if (FlowKt.emitAll(flowCollector, new Flow<List<? extends AuroraMessage>>() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$observeMessages$lambda$9$$inlined$map$1

                    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$observeMessages$lambda$9$$inlined$map$1$2, reason: invalid class name */
                    /* JADX INFO: compiled from: Emitters.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                    public static final class AnonymousClass2 implements FlowCollector {
                        final /* synthetic */ FlowCollector $this_unsafeFlow;
                        final /* synthetic */ DefaultSessionRepository this$0;

                        /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$observeMessages$lambda$9$$inlined$map$1$2$1, reason: invalid class name */
                        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                        @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository$observeMessages$lambda$9$$inlined$map$1$2", f = "DefaultSessionRepository.kt", i = {}, l = {50}, m = "emit", n = {}, s = {})
                        public final class AnonymousClass1 extends ContinuationImpl {
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

                        public AnonymousClass2(FlowCollector flowCollector, DefaultSessionRepository defaultSessionRepository) {
                            this.$this_unsafeFlow = flowCollector;
                            this.this$0 = defaultSessionRepository;
                        }

                        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object value, Continuation $completion) {
                            AnonymousClass1 anonymousClass1;
                            if ($completion instanceof AnonymousClass1) {
                                anonymousClass1 = (AnonymousClass1) $completion;
                                if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                                    anonymousClass1.label -= Integer.MIN_VALUE;
                                } else {
                                    anonymousClass1 = new AnonymousClass1($completion);
                                }
                            } else {
                                anonymousClass1 = new AnonymousClass1($completion);
                            }
                            Object $result = anonymousClass1.result;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (anonymousClass1.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    FlowCollector flowCollector = this.$this_unsafeFlow;
                                    Iterable list = (List) value;
                                    Iterable $this$map$iv = list;
                                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                    for (Object item$iv$iv : $this$map$iv) {
                                        MessageEntity it = (MessageEntity) item$iv$iv;
                                        destination$iv$iv.add(this.this$0.toModel(it));
                                    }
                                    List<AuroraMessage> listDedupeByClientMsgId = OutboxExactlyOnce.INSTANCE.<AuroraMessage>dedupeByClientMsgId((List) destination$iv$iv, new Function1<AuroraMessage, String>() {
                                        @Override public String invoke(AuroraMessage m) { return m.getId(); }
                                    }, new Function1<AuroraMessage, String>() {
                                        @Override public String invoke(AuroraMessage m) { return m.getClientMsgId(); }
                                    }, new Function1<AuroraMessage, Boolean>() {
                                        @Override public Boolean invoke(AuroraMessage m) { return StringsKt.equals(m.getRole(), "user", true); }
                                    });
                                    anonymousClass1.label = 1;
                                    if (flowCollector.emit(listDedupeByClientMsgId, anonymousClass1) == coroutine_suspended) {
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

                    @Override // kotlinx.coroutines.flow.Flow
                    public Object collect(FlowCollector flowCollector4, Continuation $completion) {
                        Object objCollect = flowObserveMessages.collect(new AnonymousClass2(flowCollector4, defaultSessionRepository), $completion);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
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
