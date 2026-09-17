package ai.nous.aurora.data.repository;

import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DefaultSessionRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5}, l = {190, 191, 194, ComposerKt.reuseKey, 209, 217, 225}, m = "sendMessage-BWLJW6A", n = {"this", "sessionId", "content", "model", "this", "sessionId", "content", "model", "this", "sessionId", "content", "model", "owner", "clientId", "pendingId", "this", "sessionId", "content", "model", "owner", "clientId", "pendingId", "this", "sessionId", "content", "model", "owner", "clientId", "pendingId", "this", "sessionId", "content", "model", "owner", "clientId", "pendingId"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
final class DefaultSessionRepository$sendMessage$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultSessionRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSessionRepository$sendMessage$1(DefaultSessionRepository defaultSessionRepository, Continuation<? super DefaultSessionRepository$sendMessage$1> continuation) {
        super(continuation);
        this.this$0 = defaultSessionRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo56sendMessageBWLJW6A = this.this$0.mo56sendMessageBWLJW6A(null, null, null, this);
        return objMo56sendMessageBWLJW6A == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo56sendMessageBWLJW6A : Result.m7387boximpl(objMo56sendMessageBWLJW6A);
    }
}
