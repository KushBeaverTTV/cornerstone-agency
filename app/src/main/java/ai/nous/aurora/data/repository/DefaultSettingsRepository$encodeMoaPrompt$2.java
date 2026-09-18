package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.HermesConfigApi;
import ai.nous.aurora.data.remote.MoaEncodeRequest;
import ai.nous.aurora.data.remote.MoaEncodeResponse;
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

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository$encodeMoaPrompt$2", f = "SettingsRepository.kt", i = {}, l = {333, 333}, m = "invokeSuspend", n = {}, s = {})
final class DefaultSettingsRepository$encodeMoaPrompt$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ String $preset;
    final /* synthetic */ String $prompt;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DefaultSettingsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSettingsRepository$encodeMoaPrompt$2(DefaultSettingsRepository defaultSettingsRepository, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = defaultSettingsRepository;
        this.$prompt = str;
        this.$preset = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        DefaultSettingsRepository$encodeMoaPrompt$2 defaultSettingsRepository$encodeMoaPrompt$2 = new DefaultSettingsRepository$encodeMoaPrompt$2(this.this$0, this.$prompt, this.$preset, continuation);
        defaultSettingsRepository$encodeMoaPrompt$2.L$0 = obj;
        return defaultSettingsRepository$encodeMoaPrompt$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return invoke2(coroutineScope, (Continuation) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DefaultSettingsRepository$encodeMoaPrompt$2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x005e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x005f  */
    /* JADX WARN: Code duplicated, block: B:23:0x0068 A[Catch: all -> 0x00f9, TryCatch #0 {all -> 0x00f9, blocks: (B:21:0x0060, B:23:0x0068, B:25:0x006e, B:27:0x0074, B:29:0x007c, B:31:0x0082, B:34:0x009c, B:35:0x00bc, B:36:0x00bd, B:38:0x00c5, B:45:0x00dc, B:46:0x00e1, B:47:0x00ec, B:48:0x00ed, B:49:0x00f8, B:17:0x004a), top: B:56:0x004a }] */
    /* JADX WARN: Code duplicated, block: B:25:0x006e A[Catch: all -> 0x00f9, TryCatch #0 {all -> 0x00f9, blocks: (B:21:0x0060, B:23:0x0068, B:25:0x006e, B:27:0x0074, B:29:0x007c, B:31:0x0082, B:34:0x009c, B:35:0x00bc, B:36:0x00bd, B:38:0x00c5, B:45:0x00dc, B:46:0x00e1, B:47:0x00ec, B:48:0x00ed, B:49:0x00f8, B:17:0x004a), top: B:56:0x004a }] */
    /* JADX WARN: Code duplicated, block: B:28:0x007b  */
    /* JADX WARN: Code duplicated, block: B:31:0x0082 A[Catch: all -> 0x00f9, TryCatch #0 {all -> 0x00f9, blocks: (B:21:0x0060, B:23:0x0068, B:25:0x006e, B:27:0x0074, B:29:0x007c, B:31:0x0082, B:34:0x009c, B:35:0x00bc, B:36:0x00bd, B:38:0x00c5, B:45:0x00dc, B:46:0x00e1, B:47:0x00ec, B:48:0x00ed, B:49:0x00f8, B:17:0x004a), top: B:56:0x004a }] */
    /* JADX WARN: Code duplicated, block: B:33:0x009a  */
    /* JADX WARN: Code duplicated, block: B:36:0x00bd A[Catch: all -> 0x00f9, TryCatch #0 {all -> 0x00f9, blocks: (B:21:0x0060, B:23:0x0068, B:25:0x006e, B:27:0x0074, B:29:0x007c, B:31:0x0082, B:34:0x009c, B:35:0x00bc, B:36:0x00bd, B:38:0x00c5, B:45:0x00dc, B:46:0x00e1, B:47:0x00ec, B:48:0x00ed, B:49:0x00f8, B:17:0x004a), top: B:56:0x004a }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00c5 A[Catch: all -> 0x00f9, TryCatch #0 {all -> 0x00f9, blocks: (B:21:0x0060, B:23:0x0068, B:25:0x006e, B:27:0x0074, B:29:0x007c, B:31:0x0082, B:34:0x009c, B:35:0x00bc, B:36:0x00bd, B:38:0x00c5, B:45:0x00dc, B:46:0x00e1, B:47:0x00ec, B:48:0x00ed, B:49:0x00f8, B:17:0x004a), top: B:56:0x004a }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:41:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:43:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:45:0x00dc A[Catch: all -> 0x00f9, TryCatch #0 {all -> 0x00f9, blocks: (B:21:0x0060, B:23:0x0068, B:25:0x006e, B:27:0x0074, B:29:0x007c, B:31:0x0082, B:34:0x009c, B:35:0x00bc, B:36:0x00bd, B:38:0x00c5, B:45:0x00dc, B:46:0x00e1, B:47:0x00ec, B:48:0x00ed, B:49:0x00f8, B:17:0x004a), top: B:56:0x004a }] */
    /* JADX WARN: Code duplicated, block: B:46:0x00e1 A[Catch: all -> 0x00f9, TryCatch #0 {all -> 0x00f9, blocks: (B:21:0x0060, B:23:0x0068, B:25:0x006e, B:27:0x0074, B:29:0x007c, B:31:0x0082, B:34:0x009c, B:35:0x00bc, B:36:0x00bd, B:38:0x00c5, B:45:0x00dc, B:46:0x00e1, B:47:0x00ec, B:48:0x00ed, B:49:0x00f8, B:17:0x004a), top: B:56:0x004a }] */
    /* JADX WARN: Code duplicated, block: B:48:0x00ed A[Catch: all -> 0x00f9, TryCatch #0 {all -> 0x00f9, blocks: (B:21:0x0060, B:23:0x0068, B:25:0x006e, B:27:0x0074, B:29:0x007c, B:31:0x0082, B:34:0x009c, B:35:0x00bc, B:36:0x00bd, B:38:0x00c5, B:45:0x00dc, B:46:0x00e1, B:47:0x00ec, B:48:0x00ed, B:49:0x00f8, B:17:0x004a), top: B:56:0x004a }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object $result2;
        String str;
        String str2;
        int i;
        Response response;
        MoaEncodeResponse body;
        String it;
        String str3;
        ResponseBody responseBodyErrorBody;
        String detail;
        String strString;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z = true;
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    DefaultSettingsRepository defaultSettingsRepository = this.this$0;
                    str = this.$prompt;
                    str2 = this.$preset;
                    Result.Companion companion = Result.Companion;
                    this.L$0 = str;
                    this.L$1 = str2;
                    this.label = 1;
                    Object objService = defaultSettingsRepository.service(this);
                    if (objService == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objService;
                    i = 0;
                    try {
                        MoaEncodeRequest moaEncodeRequest = new MoaEncodeRequest(str, str2);
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 2;
                        $result = ((HermesConfigApi) $result).encodeMoa(moaEncodeRequest, this);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        response = (Response) $result;
                        if (!response.isSuccessful()) {
                            responseBodyErrorBody = response.errorBody();
                            if (responseBodyErrorBody != null || (strString = responseBodyErrorBody.string()) == null) {
                                detail = null;
                            } else {
                                detail = StringsKt.take(strString, 180);
                            }
                            int iCode = response.code();
                            str3 = detail != null ? ": " + detail : null;
                            if (str3 == null) {
                                str3 = "";
                            }
                            throw new IllegalStateException(("MoA encode HTTP " + iCode + str3).toString());
                        }
                        body = (MoaEncodeResponse) response.body();
                        if (body == null) {
                            throw new IllegalStateException("empty encode body".toString());
                        }
                        it = body.getMessage();
                        if (StringsKt.isBlank(it)) {
                            z = false;
                        }
                        str3 = z ? it : null;
                        if (str3 == null) {
                            throw new IllegalStateException("encode returned blank message".toString());
                        }
                        $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(str3);
                        return ai.nous.aurora.compat.Compat.m7387boximpl($result2);
                    } catch (Throwable th) {
                        $result = $result;
                        Result.Companion companion2 = Result.Companion;
                        $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                    }
                    break;
                case 1:
                    String str4 = (String) this.L$1;
                    str = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    str2 = str4;
                    i = 0;
                    MoaEncodeRequest moaEncodeRequest2 = new MoaEncodeRequest(str, str2);
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 2;
                    $result = ((HermesConfigApi) $result).encodeMoa(moaEncodeRequest2, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    response = (Response) $result;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        if (responseBodyErrorBody != null) {
                            detail = null;
                        } else {
                            detail = null;
                        }
                        int iCode2 = response.code();
                        if (detail != null) {
                        }
                        str3 = null;
                        if (str3 == null) {
                            str3 = "";
                        }
                        throw new IllegalStateException(("MoA encode HTTP " + iCode2 + str3).toString());
                    }
                    body = (MoaEncodeResponse) response.body();
                    if (body == null) {
                        throw new IllegalStateException("empty encode body".toString());
                    }
                    it = body.getMessage();
                    if (StringsKt.isBlank(it)) {
                        z = false;
                    }
                    if (z) {
                    }
                    str3 = z ? it : null;
                    if (str3 == null) {
                        throw new IllegalStateException("encode returned blank message".toString());
                    }
                    $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(str3);
                    return ai.nous.aurora.compat.Compat.m7387boximpl($result2);
                case 2:
                    ResultKt.throwOnFailure($result);
                    response = (Response) $result;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        if (responseBodyErrorBody != null) {
                            detail = null;
                        } else {
                            detail = null;
                        }
                        int iCode3 = response.code();
                        if (detail != null) {
                        }
                        str3 = null;
                        if (str3 == null) {
                            str3 = "";
                        }
                        throw new IllegalStateException(("MoA encode HTTP " + iCode3 + str3).toString());
                    }
                    body = (MoaEncodeResponse) response.body();
                    if (body == null) {
                        throw new IllegalStateException("empty encode body".toString());
                    }
                    it = body.getMessage();
                    if (StringsKt.isBlank(it)) {
                        z = false;
                    }
                    if (z) {
                    }
                    str3 = z ? it : null;
                    if (str3 == null) {
                        throw new IllegalStateException("encode returned blank message".toString());
                    }
                    $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(str3);
                    return ai.nous.aurora.compat.Compat.m7387boximpl($result2);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th2) {
            Result.Companion companion99 = Result.Companion;
            return ai.nous.aurora.compat.Compat.m7387boximpl(ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2)));
        }
            return $result2;
    }
}
