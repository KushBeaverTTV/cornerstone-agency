package ai.nous.aurora.data.remote;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HostVoiceClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.remote.HostVoiceClient$dictate$3$1$keyDeferred$1", f = "HostVoiceClient.kt", i = {}, l = {100}, m = "invokeSuspend", n = {}, s = {})
final class HostVoiceClient$dictate$3$1$keyDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    int label;
    final /* synthetic */ HostVoiceClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HostVoiceClient$dictate$3$1$keyDeferred$1(HostVoiceClient hostVoiceClient, Continuation<? super HostVoiceClient$dictate$3$1$keyDeferred$1> continuation) {
        super(2, continuation);
        this.this$0 = hostVoiceClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HostVoiceClient$dictate$3$1$keyDeferred$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((HostVoiceClient$dictate$3$1$keyDeferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object objApiKey = this.this$0.apiKey(this);
                return objApiKey == coroutine_suspended ? coroutine_suspended : objApiKey;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
