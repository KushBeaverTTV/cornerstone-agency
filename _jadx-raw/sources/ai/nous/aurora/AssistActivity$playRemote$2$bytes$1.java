package ai.nous.aurora;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: AssistActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.AssistActivity$playRemote$2$bytes$1", f = "AssistActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class AssistActivity$playRemote$2$bytes$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
    final /* synthetic */ String $key;
    final /* synthetic */ String $url;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssistActivity$playRemote$2$bytes$1(String str, String str2, Continuation<? super AssistActivity$playRemote$2$bytes$1> continuation) {
        super(2, continuation);
        this.$url = str;
        this.$key = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssistActivity$playRemote$2$bytes$1(this.$url, this.$key, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super byte[]> continuation) {
        return ((AssistActivity$playRemote$2$bytes$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws IOException {
        ResponseBody responseBodyBody;
        byte[] bArrBytes;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Request req = new Request.Builder().url(this.$url).header("Authorization", "Bearer " + this.$key).get().build();
                OkHttpClient client = new OkHttpClient.Builder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).build();
                Response responseExecute = client.newCall(req).execute();
                try {
                    Response resp = responseExecute;
                    if (resp.isSuccessful() && (responseBodyBody = resp.body()) != null) {
                        bArrBytes = responseBodyBody.bytes();
                        break;
                    } else {
                        bArrBytes = null;
                    }
                    CloseableKt.closeFinally(responseExecute, null);
                    return bArrBytes;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(responseExecute, th);
                        throw th2;
                    }
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
