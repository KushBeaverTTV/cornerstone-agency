package ai.nous.aurora.data.repository;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: DefaultSessionRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 1, 2, 2}, l = {123, 124, WebSocketProtocol.PAYLOAD_SHORT, 127}, m = "createSession-IoAF18A", n = {"this", "this", "this", "owner"}, s = {"L$0", "L$0", "L$0", "L$1"})
final class DefaultSessionRepository$createSession$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultSessionRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSessionRepository$createSession$1(DefaultSessionRepository defaultSessionRepository, Continuation continuation) {
        super(continuation);
        this.this$0 = defaultSessionRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo52createSessionIoAF18A = this.this$0.mo52createSessionIoAF18A(this);
        return objMo52createSessionIoAF18A == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo52createSessionIoAF18A : ai.nous.aurora.compat.Compat.m7387boximpl(objMo52createSessionIoAF18A);
    }
}
