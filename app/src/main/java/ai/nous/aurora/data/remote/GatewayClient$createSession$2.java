package ai.nous.aurora.data.remote;

import ai.nous.aurora.domain.model.AuroraSession;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import retrofit2.Response;

/* JADX INFO: compiled from: GatewayClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lai/nous/aurora/domain/model/AuroraSession;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.remote.GatewayClient$createSession$2", f = "GatewayClient.kt", i = {}, l = {181}, m = "invokeSuspend", n = {}, s = {})
final class GatewayClient$createSession$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ String $title;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ GatewayClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GatewayClient$createSession$2(GatewayClient gatewayClient, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gatewayClient;
        this.$title = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        GatewayClient$createSession$2 gatewayClient$createSession$2 = new GatewayClient$createSession$2(this.this$0, this.$title, continuation);
        gatewayClient$createSession$2.L$0 = obj;
        return gatewayClient$createSession$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return invoke2(coroutineScope, (Continuation) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation continuation) {
        return ((GatewayClient$createSession$2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10, types: [retrofit2.Response] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v9 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM7388constructorimpl = null;
        GatewayClient gatewayClient;
        Object r10;
        HermesSession session;
        AuroraSession model;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    gatewayClient = this.this$0;
                    String str = this.$title;
                    Result.Companion companion = Result.Companion;
                    GatewayService gatewayServiceRequireService = gatewayClient.requireService();
                    CreateSessionRequest createSessionRequest = new CreateSessionRequest(str, (String) null, 2, (DefaultConstructorMarker) null);
                    this.L$0 = gatewayClient;
                    this.label = 1;
                    Object objCreateSession = gatewayServiceRequireService.createSession(createSessionRequest, this);
                    if (objCreateSession == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r10 = objCreateSession;
                    break;
                case 1:
                    GatewayClient gatewayClient2 = (GatewayClient) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    gatewayClient = gatewayClient2;
                    r10 = obj;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                Response responseObj = (Response) r10;
                if (!responseObj.isSuccessful()) {
                    throw new IllegalStateException(("Create session HTTP " + responseObj.code()).toString());
                }
                SessionEnvelope sessionEnvelope = (SessionEnvelope) responseObj.body();
                if (sessionEnvelope != null && (session = sessionEnvelope.getSession()) != null && (model = gatewayClient.toModel(session)) != null) {
                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(model);
                    return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                }
                throw new IllegalStateException("Empty session".toString());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.Companion;
            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
        }
        return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
    }
}
