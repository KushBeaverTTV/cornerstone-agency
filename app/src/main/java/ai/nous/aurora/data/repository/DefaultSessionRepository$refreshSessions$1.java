package ai.nous.aurora.data.repository;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DefaultSessionRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 6, 6}, l = {97, 98, 431, 103, 105, 108, 111}, m = "refreshSessions-IoAF18A", n = {"this", "this", "this", "owner", "$this$withLock_u24default$iv", "gen", "this", "$this$withLock_u24default$iv", "this", "owner", "$this$withLock_u24default$iv", "gen", "this", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "remote"}, s = {"L$0", "L$0", "L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$0", "L$1"})
final class DefaultSessionRepository$refreshSessions$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultSessionRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSessionRepository$refreshSessions$1(DefaultSessionRepository defaultSessionRepository, Continuation continuation) {
        super(continuation);
        this.this$0 = defaultSessionRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo53refreshSessionsIoAF18A = this.this$0.mo53refreshSessionsIoAF18A(this);
        return objMo53refreshSessionsIoAF18A == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo53refreshSessionsIoAF18A : ai.nous.aurora.compat.Compat.m7387boximpl(objMo53refreshSessionsIoAF18A);
    }
}
