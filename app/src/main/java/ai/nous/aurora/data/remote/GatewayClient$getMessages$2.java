package ai.nous.aurora.data.remote;

import ai.nous.aurora.domain.model.AuroraMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import retrofit2.Response;

/* JADX INFO: compiled from: GatewayClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lai/nous/aurora/domain/model/AuroraMessage;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.remote.GatewayClient$getMessages$2", f = "GatewayClient.kt", i = {}, l = {189}, m = "invokeSuspend", n = {}, s = {})
final class GatewayClient$getMessages$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ String $sessionId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ GatewayClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GatewayClient$getMessages$2(GatewayClient gatewayClient, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gatewayClient;
        this.$sessionId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        GatewayClient$getMessages$2 gatewayClient$getMessages$2 = new GatewayClient$getMessages$2(this.this$0, this.$sessionId, continuation);
        gatewayClient$getMessages$2.L$0 = obj;
        return gatewayClient$getMessages$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return invoke2(coroutineScope, (Continuation) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation continuation) {
        return ((GatewayClient$getMessages$2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [retrofit2.Response] */
    /* JADX WARN: Type inference failed for: r3v1 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objM7388constructorimpl;
        Object $result2;
        Object $result3;
        AuroraMessage auroraMessage;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = this.label;
        try {
            switch (r2) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    GatewayClient gatewayClient = this.this$0;
                    String str = this.$sessionId;
                    Result.Companion companion = Result.Companion;
                    GatewayService gatewayServiceRequireService = gatewayClient.requireService();
                    this.label = 1;
                    Object objSessionMessages = gatewayServiceRequireService.sessionMessages(str, this);
                    if (objSessionMessages == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result3 = objSessionMessages;
                    break;
                    break;
                case 1:
                    $result3 = $result;
                    ResultKt.throwOnFailure($result3);
                    $result2 = $result3;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                Response responseObj = (Response) $result3;
                if (!responseObj.isSuccessful()) {
                    throw new IllegalStateException(("Messages HTTP " + responseObj.code()).toString());
                }
                MessageListResponse messageListResponse = (MessageListResponse) responseObj.body();
                Iterable data = messageListResponse != null ? messageListResponse.getData() : null;
                if (data == null) {
                    data = CollectionsKt.emptyList();
                }
                Iterable $this$forEach$iv$iv$iv = data;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$forEach$iv$iv$iv) {
                    HermesMessage m = (HermesMessage) element$iv$iv;
                    String role = m.getRole();
                    if (role == null) {
                        auroraMessage = null;
                    } else if (Intrinsics.areEqual(role, "user") || Intrinsics.areEqual(role, "assistant") || Intrinsics.areEqual(role, "system")) {
                        String id = m.getId();
                        String content = m.getContent();
                        if (content == null) {
                            content = "";
                        }
                        auroraMessage = new AuroraMessage(id, role, content, null, m.getClientMsgId(), 8, null);
                    } else {
                        auroraMessage = null;
                    }
                    if (auroraMessage != null) {
                        destination$iv$iv.add(auroraMessage);
                    }
                }
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv);
                return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
            Throwable th = null;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable th2) {
        }
    }
}
