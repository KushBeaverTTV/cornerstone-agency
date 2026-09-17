package ai.nous.aurora.data.remote;

import ai.nous.aurora.domain.model.ConnectionState;
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
import kotlinx.coroutines.flow.MutableStateFlow;
import retrofit2.Response;

/* JADX INFO: compiled from: GatewayClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.remote.GatewayClient$connect$2", f = "GatewayClient.kt", i = {}, l = {139}, m = "invokeSuspend", n = {}, s = {})
final class GatewayClient$connect$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ String $apiKey;
    final /* synthetic */ String $baseUrl;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ GatewayClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GatewayClient$connect$2(GatewayClient gatewayClient, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gatewayClient;
        this.$baseUrl = str;
        this.$apiKey = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        GatewayClient$connect$2 gatewayClient$connect$2 = new GatewayClient$connect$2(this.this$0, this.$baseUrl, this.$apiKey, continuation);
        gatewayClient$connect$2.L$0 = obj;
        return gatewayClient$connect$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return invoke2(coroutineScope, (Continuation) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation continuation) {
        return ((GatewayClient$connect$2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0058 A[Catch: all -> 0x009c, TRY_LEAVE, TryCatch #3 {all -> 0x009c, blocks: (B:17:0x0050, B:19:0x0058, B:27:0x0075, B:28:0x009b), top: B:53:0x0050 }] */
    /* JADX WARN: Code duplicated, block: B:22:0x0067 A[Catch: all -> 0x0073, TryCatch #2 {all -> 0x0073, blocks: (B:20:0x0061, B:22:0x0067, B:23:0x006a), top: B:51:0x0061 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x0075 A[Catch: all -> 0x009c, TRY_ENTER, TryCatch #3 {all -> 0x009c, blocks: (B:17:0x0050, B:19:0x0058, B:27:0x0075, B:28:0x009b), top: B:53:0x0050 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00ae A[Catch: all -> 0x00b2, TryCatch #0 {all -> 0x00b2, blocks: (B:33:0x00a8, B:35:0x00ae, B:36:0x00b1, B:12:0x002e), top: B:48:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:44:0x00d5  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Throwable th = null;
        Object objM7388constructorimpl;
        GatewayClient gatewayClient;
        Throwable err;
        String message;
        GatewayClient gatewayClient2;
        int i;
        Throwable th2 = null;
        GatewayClient gatewayClient3;
        Response r;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    gatewayClient2 = this.this$0;
                    String str = this.$baseUrl;
                    String str2 = this.$apiKey;
                    Result.Companion companion = Result.Companion;
                    i = 0;
                    gatewayClient2._connection.setValue(ConnectionState.Connecting.INSTANCE);
                    gatewayClient2.configure(str, str2);
                    try {
                        GatewayService gatewayServiceRequireService = gatewayClient2.requireService();
                        this.L$0 = gatewayClient2;
                        this.label = 1;
                        Object objHealth = gatewayServiceRequireService.health(this);
                        if (objHealth == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objHealth;
                        try {
                            r = (Response) $result;
                            if (r.isSuccessful()) {
                                throw new IllegalStateException(("Health check failed (HTTP " + r.code() + ")").toString());
                            }
                            gatewayClient2._connection.setValue(ConnectionState.Online.INSTANCE);
                            try {
                                if (gatewayClient2.healthChecksEnabled) {
                                    gatewayClient2.startHealthCheck(false);
                                }
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                                break;
                            } catch (Throwable th3) {
                                Result.Companion companion2 = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th3));
                            }
                            gatewayClient = this.this$0;
                            err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl);
                            if (err != null) {
                                MutableStateFlow mutableStateFlow = gatewayClient._connection;
                                message = err.getMessage();
                                if (message == null) {
                                    message = "Offline";
                                }
                                mutableStateFlow.setValue(new ConnectionState.Error(message));
                            }
                            return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                        } catch (Throwable th4) {
                            GatewayClient gatewayClient4 = gatewayClient2;
                            th2 = th4;
                            gatewayClient3 = gatewayClient4;
                            if (gatewayClient3.healthChecksEnabled) {
                                gatewayClient3.startHealthCheck(false);
                            }
                            throw th2;
                        }
                    } catch (Throwable th5) {
                        th2 = th5;
                        gatewayClient3 = gatewayClient2;
                        if (gatewayClient3.healthChecksEnabled) {
                            gatewayClient3.startHealthCheck(false);
                        }
                        throw th2;
                    }
                case 1:
                    gatewayClient3 = (GatewayClient) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        gatewayClient2 = gatewayClient3;
                        i = 0;
                        r = (Response) $result;
                        if (r.isSuccessful()) {
                            throw new IllegalStateException(("Health check failed (HTTP " + r.code() + ")").toString());
                        }
                        gatewayClient2._connection.setValue(ConnectionState.Online.INSTANCE);
                        if (gatewayClient2.healthChecksEnabled) {
                            gatewayClient2.startHealthCheck(false);
                        }
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                        gatewayClient = this.this$0;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl);
                        if (err != null) {
                            MutableStateFlow mutableStateFlow2 = gatewayClient._connection;
                            message = err.getMessage();
                            if (message == null) {
                                message = "Offline";
                            }
                            mutableStateFlow2.setValue(new ConnectionState.Error(message));
                        }
                        return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                    } catch (Throwable th6) {
                        th2 = th6;
                        if (gatewayClient3.healthChecksEnabled) {
                            gatewayClient3.startHealthCheck(false);
                        }
                        throw th2;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th7) {
        }
    }
}
