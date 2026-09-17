package ai.nous.aurora.data.repository;

import ai.nous.aurora.presentation.theme.AuroraMotion;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DefaultSessionRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {229, AuroraMotion.SHEET_MS, 232, AuroraMotion.TAB_MS, 245}, m = "retryMessage-BWLJW6A", n = {"this", "sessionId", "localMessageId", "model", "this", "sessionId", "localMessageId", "model", "this", "sessionId", "localMessageId", "model", "owner"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"})
final class DefaultSessionRepository$retryMessage$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultSessionRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSessionRepository$retryMessage$1(DefaultSessionRepository defaultSessionRepository, Continuation<? super DefaultSessionRepository$retryMessage$1> continuation) {
        super(continuation);
        this.this$0 = defaultSessionRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo54retryMessageBWLJW6A = this.this$0.mo54retryMessageBWLJW6A(null, null, null, this);
        return objMo54retryMessageBWLJW6A == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo54retryMessageBWLJW6A : Result.m7387boximpl(objMo54retryMessageBWLJW6A);
    }
}
