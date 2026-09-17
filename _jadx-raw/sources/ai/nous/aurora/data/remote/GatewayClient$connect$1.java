package ai.nous.aurora.data.remote;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: GatewayClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.remote.GatewayClient", f = "GatewayClient.kt", i = {}, l = {134}, m = "connect-0E7RQCE", n = {}, s = {})
final class GatewayClient$connect$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GatewayClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GatewayClient$connect$1(GatewayClient gatewayClient, Continuation<? super GatewayClient$connect$1> continuation) {
        super(continuation);
        this.this$0 = gatewayClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM41connect0E7RQCE = this.this$0.m41connect0E7RQCE(null, null, this);
        return objM41connect0E7RQCE == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM41connect0E7RQCE : Result.m7387boximpl(objM41connect0E7RQCE);
    }
}
