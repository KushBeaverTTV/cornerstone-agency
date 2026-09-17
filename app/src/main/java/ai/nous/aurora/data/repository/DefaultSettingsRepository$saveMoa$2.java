package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.HermesConfigApi;
import ai.nous.aurora.data.remote.MoaConfigDto;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lai/nous/aurora/data/repository/MoaSnapshot;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository$saveMoa$2", f = "SettingsRepository.kt", i = {0, 0, 1, 1, 2}, l = {293, 321, 325}, m = "invokeSuspend", n = {"priorCache", "$this$invokeSuspend_u24lambda_u242", "priorCache", "$this$invokeSuspend_u24lambda_u242", "priorCache"}, s = {"L$0", "L$3", "L$0", "L$2", "L$0"})
final class DefaultSettingsRepository$saveMoa$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ MoaConfigDto $body;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ DefaultSettingsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSettingsRepository$saveMoa$2(DefaultSettingsRepository defaultSettingsRepository, MoaConfigDto moaConfigDto, Continuation continuation) {
        super(2, continuation);
        this.this$0 = defaultSettingsRepository;
        this.$body = moaConfigDto;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        DefaultSettingsRepository$saveMoa$2 defaultSettingsRepository$saveMoa$2 = new DefaultSettingsRepository$saveMoa$2(this.this$0, this.$body, continuation);
        defaultSettingsRepository$saveMoa$2.L$0 = obj;
        return defaultSettingsRepository$saveMoa$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return invoke2(coroutineScope, (Continuation) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DefaultSettingsRepository$saveMoa$2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00a1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:27:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:31:0x00c3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:32:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:54:0x010c  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MoaSnapshot priorCache;
        Object objM7388constructorimpl;
        DefaultSettingsRepository defaultSettingsRepository;
        MoaConfigDto moaConfigDto;
        int i;
        MoaSnapshot priorCache2;
        DefaultSettingsRepository defaultSettingsRepository2;
        CoroutineScope $this$invokeSuspend_u24lambda_u242;
        Object objSaveAndVerify;
        DefaultSettingsRepository defaultSettingsRepository3;
        MoaSnapshot it;
        int i2;
        MoaSnapshot priorCache3;
        MoaSnapshot moaSnapshot;
        int i3;
        Object objLoad;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$invokeSuspend_u24lambda_u243 = (CoroutineScope) this.L$0;
                    MoaSnapshot priorCache4 = this.this$0.moaCached;
                    DefaultSettingsRepository defaultSettingsRepository4 = this.this$0;
                    moaConfigDto = this.$body;
                    Result.Companion companion = Result.Companion;
                    i = 0;
                    this.L$0 = priorCache4;
                    this.L$1 = defaultSettingsRepository4;
                    this.L$2 = moaConfigDto;
                    this.L$3 = $this$invokeSuspend_u24lambda_u243;
                    this.label = 1;
                    Object objService = defaultSettingsRepository4.service(this);
                    if (objService == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objService;
                    priorCache2 = priorCache4;
                    defaultSettingsRepository2 = defaultSettingsRepository4;
                    $this$invokeSuspend_u24lambda_u242 = $this$invokeSuspend_u24lambda_u243;
                    try {
                        HermesConfigApi currentService = (HermesConfigApi) $result;
                        DefaultSettingsRepository$saveMoa$2$1$transport$1 transport = new DefaultSettingsRepository$saveMoa$2$1$transport$1(currentService);
                        this.L$0 = priorCache2;
                        this.L$1 = defaultSettingsRepository2;
                        this.L$2 = $this$invokeSuspend_u24lambda_u242;
                        this.L$3 = null;
                        this.label = 2;
                        objSaveAndVerify = CompensatingMoaWriter.INSTANCE.saveAndVerify(transport, moaConfigDto, this);
                        if (objSaveAndVerify == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objSaveAndVerify;
                        defaultSettingsRepository3 = defaultSettingsRepository2;
                        priorCache = priorCache2;
                        try {
                            MoaConfigDto verifiedDto = (MoaConfigDto) $result;
                            it = defaultSettingsRepository3.toSnapshot(verifiedDto, true);
                            i2 = 0;
                            defaultSettingsRepository3.moaCached = it;
                            try {
                                Result.Companion companion2 = Result.Companion;
                                this.L$0 = priorCache;
                                this.L$1 = it;
                                this.L$2 = null;
                                this.label = 3;
                                objLoad = defaultSettingsRepository3.load(true, this);
                                if (objLoad == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                priorCache3 = priorCache;
                                moaSnapshot = it;
                                $result = objLoad;
                                try {
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl((HermesSettingsSnapshot) $result);
                                    priorCache = priorCache3;
                                Throwable th = null;
                                } catch (Throwable th) {
                                    i3 = i2;
                                    try {
                                        Result.Companion companion3 = Result.Companion;
                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                                        priorCache = priorCache3;
                                    } catch (Throwable th2) {
                                        priorCache = priorCache3;
                                        Result.Companion companion4 = Result.Companion;
                                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                    }
                                }
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(moaSnapshot);
                                defaultSettingsRepository = this.this$0;
                                if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                                    defaultSettingsRepository.moaCached = priorCache;
                                }
                                return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                            } catch (Throwable th3) {
                                priorCache3 = priorCache;
                                moaSnapshot = it;
                                i3 = 0;
                                Result.Companion companion5 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th3));
                                priorCache = priorCache3;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(moaSnapshot);
                                defaultSettingsRepository = this.this$0;
                                if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                                    defaultSettingsRepository.moaCached = priorCache;
                                }
                                return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                            }
                        } catch (Throwable th4) {
                            Result.Companion companion6 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th4));
                        }
                    } catch (Throwable th5) {
                        priorCache = priorCache2;
                        Result.Companion companion7 = Result.Companion;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th5));
                        defaultSettingsRepository = this.this$0;
                        if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                            defaultSettingsRepository.moaCached = priorCache;
                        }
                        return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                    }
                    break;
                case 1:
                    $this$invokeSuspend_u24lambda_u242 = (CoroutineScope) this.L$3;
                    moaConfigDto = (MoaConfigDto) this.L$2;
                    defaultSettingsRepository2 = (DefaultSettingsRepository) this.L$1;
                    MoaSnapshot priorCache5 = (MoaSnapshot) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        priorCache2 = priorCache5;
                        i = 0;
                        HermesConfigApi currentService2 = (HermesConfigApi) $result;
                        DefaultSettingsRepository$saveMoa$2$1$transport$1 transport2 = new DefaultSettingsRepository$saveMoa$2$1$transport$1(currentService2);
                        this.L$0 = priorCache2;
                        this.L$1 = defaultSettingsRepository2;
                        this.L$2 = $this$invokeSuspend_u24lambda_u242;
                        this.L$3 = null;
                        this.label = 2;
                        objSaveAndVerify = CompensatingMoaWriter.INSTANCE.saveAndVerify(transport2, moaConfigDto, this);
                        if (objSaveAndVerify == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objSaveAndVerify;
                        defaultSettingsRepository3 = defaultSettingsRepository2;
                        priorCache = priorCache2;
                        MoaConfigDto verifiedDto2 = (MoaConfigDto) $result;
                        it = defaultSettingsRepository3.toSnapshot(verifiedDto2, true);
                        i2 = 0;
                        defaultSettingsRepository3.moaCached = it;
                        Result.Companion companion8 = Result.Companion;
                        this.L$0 = priorCache;
                        this.L$1 = it;
                        this.L$2 = null;
                        this.label = 3;
                        objLoad = defaultSettingsRepository3.load(true, this);
                        if (objLoad == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        priorCache3 = priorCache;
                        moaSnapshot = it;
                        $result = objLoad;
                        ai.nous.aurora.compat.Compat.m7388constructorimpl((HermesSettingsSnapshot) $result);
                        priorCache = priorCache3;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(moaSnapshot);
                        defaultSettingsRepository = this.this$0;
                        if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                            defaultSettingsRepository.moaCached = priorCache;
                        }
                        return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                    } catch (Throwable th6) {
                        priorCache = priorCache5;
                        Result.Companion companion9 = Result.Companion;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th6));
                        defaultSettingsRepository = this.this$0;
                        if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                            defaultSettingsRepository.moaCached = priorCache;
                        }
                        return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                    }
                case 2:
                    defaultSettingsRepository3 = (DefaultSettingsRepository) this.L$1;
                    priorCache = (MoaSnapshot) this.L$0;
                    ResultKt.throwOnFailure($result);
                    i = 0;
                    $result = $result;
                    MoaConfigDto verifiedDto3 = (MoaConfigDto) $result;
                    it = defaultSettingsRepository3.toSnapshot(verifiedDto3, true);
                    i2 = 0;
                    defaultSettingsRepository3.moaCached = it;
                    Result.Companion companion10 = Result.Companion;
                    this.L$0 = priorCache;
                    this.L$1 = it;
                    this.L$2 = null;
                    this.label = 3;
                    objLoad = defaultSettingsRepository3.load(true, this);
                    if (objLoad == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    priorCache3 = priorCache;
                    moaSnapshot = it;
                    $result = objLoad;
                    ai.nous.aurora.compat.Compat.m7388constructorimpl((HermesSettingsSnapshot) $result);
                    priorCache = priorCache3;
                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(moaSnapshot);
                    defaultSettingsRepository = this.this$0;
                    if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                        defaultSettingsRepository.moaCached = priorCache;
                    }
                    return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                case 3:
                    i3 = 0;
                    moaSnapshot = (MoaSnapshot) this.L$1;
                    priorCache3 = (MoaSnapshot) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        i2 = 0;
                        $result = $result;
                        ai.nous.aurora.compat.Compat.m7388constructorimpl((HermesSettingsSnapshot) $result);
                        priorCache = priorCache3;
                    } catch (Throwable th7) {
                        Result.Companion companion11 = Result.Companion;
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th7));
                        priorCache = priorCache3;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(moaSnapshot);
                        defaultSettingsRepository = this.this$0;
                        if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                            defaultSettingsRepository.moaCached = priorCache;
                        }
                        return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                    }
                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(moaSnapshot);
                    defaultSettingsRepository = this.this$0;
                    if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                        defaultSettingsRepository.moaCached = priorCache;
                    }
                    return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th8) {
        }
    }
}
