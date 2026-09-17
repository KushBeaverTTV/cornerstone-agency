package ai.nous.aurora.data.remote;

import ai.nous.aurora.domain.model.AuroraMessage;
import ai.nous.aurora.domain.model.OutboxExactlyOnce;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.ResponseBody;
import retrofit2.Response;

/* JADX INFO: compiled from: GatewayClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lai/nous/aurora/domain/model/AuroraMessage;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.remote.GatewayClient$sendMessage$2", f = "GatewayClient.kt", i = {}, l = {224}, m = "invokeSuspend", n = {}, s = {})
final class GatewayClient$sendMessage$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends AuroraMessage>>, Object> {
    final /* synthetic */ String $clientMsgId;
    final /* synthetic */ String $content;
    final /* synthetic */ String $model;
    final /* synthetic */ String $ownerKey;
    final /* synthetic */ String $sessionId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ GatewayClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GatewayClient$sendMessage$2(String str, String str2, GatewayClient gatewayClient, String str3, String str4, String str5, Continuation<? super GatewayClient$sendMessage$2> continuation) {
        super(2, continuation);
        this.$clientMsgId = str;
        this.$ownerKey = str2;
        this.this$0 = gatewayClient;
        this.$sessionId = str3;
        this.$content = str4;
        this.$model = str5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        GatewayClient$sendMessage$2 gatewayClient$sendMessage$2 = new GatewayClient$sendMessage$2(this.$clientMsgId, this.$ownerKey, this.this$0, this.$sessionId, this.$content, this.$model, continuation);
        gatewayClient$sendMessage$2.L$0 = obj;
        return gatewayClient$sendMessage$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends AuroraMessage>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<AuroraMessage>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<AuroraMessage>> continuation) {
        return ((GatewayClient$sendMessage$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0066 A[Catch: all -> 0x013d, TryCatch #1 {all -> 0x013d, blocks: (B:7:0x001b, B:10:0x0037, B:12:0x0042, B:18:0x004e, B:20:0x0053, B:26:0x005f, B:35:0x0077, B:27:0x0066, B:29:0x006b), top: B:89:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0071  */
    /* JADX WARN: Code duplicated, block: B:33:0x0074  */
    /* JADX WARN: Code duplicated, block: B:34:0x0076  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r4v0 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objM7388constructorimpl;
        String str;
        String str2;
        String strIdempotencyKey;
        Object $result2;
        Object $result3;
        String role;
        String clientMsgId;
        String strString;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r2 = this.label;
        String err = null;
        try {
            switch (r2) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    str = this.$clientMsgId;
                    String str3 = this.$ownerKey;
                    GatewayClient gatewayClient = this.this$0;
                    String str4 = this.$sessionId;
                    String str5 = this.$content;
                    String str6 = this.$model;
                    Result.Companion companion = Result.INSTANCE;
                    String str7 = str;
                    if (str7 == null || StringsKt.isBlank(str7)) {
                        str2 = str;
                        if (str2 != null || StringsKt.isBlank(str2)) {
                            strIdempotencyKey = null;
                        } else {
                            strIdempotencyKey = str;
                        }
                    } else {
                        String str8 = str3;
                        if (!(str8 == null || StringsKt.isBlank(str8))) {
                            strIdempotencyKey = OutboxExactlyOnce.INSTANCE.idempotencyKey(str3, str);
                        } else {
                            str2 = str;
                            if (str2 != null || StringsKt.isBlank(str2)) {
                                strIdempotencyKey = str;
                            } else {
                                strIdempotencyKey = null;
                            }
                        }
                    }
                    GatewayService gatewayServiceRequireService = gatewayClient.requireService();
                    SessionChatRequest sessionChatRequest = new SessionChatRequest(str5, str6, str);
                    this.L$0 = str;
                    this.label = 1;
                    Object objSessionChat = gatewayServiceRequireService.sessionChat(str4, sessionChatRequest, strIdempotencyKey, this);
                    if (objSessionChat == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result3 = objSessionChat;
                    break;
                case 1:
                    $result3 = $result;
                    String str9 = (String) this.L$0;
                    ResultKt.throwOnFailure($result3);
                    str = str9;
                    $result2 = $result3;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                Response r = (Response) $result3;
                String str10 = "";
                if (!r.isSuccessful()) {
                    ResponseBody responseBodyErrorBody = r.errorBody();
                    if (responseBodyErrorBody != null && (strString = responseBodyErrorBody.string()) != null) {
                        err = StringsKt.take(strString, 400);
                    }
                    int iCode = r.code();
                    if (err != null) {
                        String it = err;
                        String str11 = ": " + it;
                        if (str11 != null) {
                            str10 = str11;
                        }
                    }
                    throw new IllegalStateException(("Chat HTTP " + iCode + str10).toString());
                }
                SessionChatResponse sessionChatResponse = (SessionChatResponse) r.body();
                HermesMessage msg = sessionChatResponse != null ? sessionChatResponse.getMessage() : null;
                String id = msg != null ? msg.getId() : null;
                if (msg == null || (role = msg.getRole()) == null) {
                    role = "assistant";
                }
                String str12 = role;
                err = msg != null ? msg.getContent() : null;
                objM7388constructorimpl = Result.m7388constructorimpl(new AuroraMessage(id, str12, err == null ? "" : err, null, (msg == null || (clientMsgId = msg.getClientMsgId()) == null) ? str : clientMsgId, 8, null));
                return Result.m7387boximpl(objM7388constructorimpl);
            } catch (Throwable th) {
                th = th;
                r2 = $result2;
                Result.Companion companion2 = Result.INSTANCE;
                objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
