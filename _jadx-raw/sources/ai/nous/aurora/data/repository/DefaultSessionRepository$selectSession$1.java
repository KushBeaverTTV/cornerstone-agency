package ai.nous.aurora.data.repository;

import ai.nous.aurora.BuildConfig;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DefaultSessionRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 8}, l = {BuildConfig.VERSION_CODE, 132, 133, 136, 137, 145, 154, 159, 161}, m = "selectSession-gIAlu-s", n = {"this", "sessionId", "this", "sessionId", "this", "sessionId", "owner", "this", "sessionId", "owner", "remoteMsgs", "this", "sessionId", "owner", "remoteMsgs", "this", "sessionId", "owner", "remoteMsgs", "this", "sessionId", "owner", "this", "sessionId", "sessionId"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4", "L$0", "L$1", "L$2", "L$4", "L$0", "L$1", "L$2", "L$4", "L$0", "L$1", "L$2", "L$0", "L$1", "L$0"})
final class DefaultSessionRepository$selectSession$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultSessionRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSessionRepository$selectSession$1(DefaultSessionRepository defaultSessionRepository, Continuation<? super DefaultSessionRepository$selectSession$1> continuation) {
        super(continuation);
        this.this$0 = defaultSessionRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo55selectSessiongIAlus = this.this$0.mo55selectSessiongIAlus(null, this);
        return objMo55selectSessiongIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo55selectSessiongIAlus : Result.m7387boximpl(objMo55selectSessiongIAlus);
    }
}
