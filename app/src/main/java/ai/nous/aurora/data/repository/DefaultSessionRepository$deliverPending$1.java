package ai.nous.aurora.data.repository;

import ai.nous.aurora.domain.model.OutboxExactlyOnce;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: compiled from: DefaultSessionRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11}, l = {273, 286, 289, 298, StatusLine.HTTP_PERM_REDIRECT, 310, 319, 328, 332, 346, 349, 354}, m = "deliverPending-bMdYcbs", n = {"this", "sessionId", "pendingId", "content", "model", "owner", "clientMsgId", "mutex", "networkStarted", "this", "pendingId", "owner", "mutex", "this", "sessionId", "pendingId", "content", "owner", "clientMsgId", "mutex", "networkStarted", "this", "sessionId", "pendingId", "owner", "clientMsgId", "mutex", OutboxExactlyOnce.STATUS_SENT, "this", "pendingId", "owner", "clientMsgId", "mutex", OutboxExactlyOnce.STATUS_SENT, "this", "pendingId", "owner", "clientMsgId", "mutex", OutboxExactlyOnce.STATUS_SENT, "this", "sessionId", "pendingId", "content", "owner", "clientMsgId", "mutex", NotificationCompat.CATEGORY_ERROR, NotificationCompat.CATEGORY_STATUS, "this", "sessionId", "pendingId", "content", "owner", "clientMsgId", "mutex", NotificationCompat.CATEGORY_ERROR, "$this$deliverPending_bMdYcbs_u24lambda_u2443_u24lambda_u2442", "this", "sessionId", "pendingId", "content", "owner", "clientMsgId", "mutex", NotificationCompat.CATEGORY_ERROR, "$this$deliverPending_bMdYcbs_u24lambda_u2443_u24lambda_u2442", "next", "this", "pendingId", "content", "owner", "clientMsgId", "mutex", NotificationCompat.CATEGORY_ERROR, "this", "pendingId", "content", "owner", "clientMsgId", "mutex", NotificationCompat.CATEGORY_ERROR}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
final class DefaultSessionRepository$deliverPending$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultSessionRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSessionRepository$deliverPending$1(DefaultSessionRepository defaultSessionRepository, Continuation continuation) {
        super(continuation);
        this.this$0 = defaultSessionRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM51deliverPendingbMdYcbs = this.this$0.m51deliverPendingbMdYcbs(null, null, null, null, null, null, this);
        return objM51deliverPendingbMdYcbs == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM51deliverPendingbMdYcbs : ai.nous.aurora.compat.Compat.m7387boximpl(objM51deliverPendingbMdYcbs);
    }
}
