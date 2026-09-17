package ai.nous.aurora.data.local.db;

import ai.nous.aurora.domain.model.OutboxExactlyOnce;
import ai.nous.aurora.domain.model.ThreadVoidDensityPolicy;
import androidx.core.app.NotificationCompat;
import androidx.core.location.LocationRequestCompat;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: MessageDao.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H'J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ \u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ(\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H§@¢\u0006\u0002\u0010\u0015J\u001e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\u000eJ6\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\u001fJ\u001e\u0010 \u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ\u0016\u0010!\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\"J\u001e\u0010$\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ(\u0010%\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0097@¢\u0006\u0002\u0010\u000eJ&\u0010&\u001a\u00020'2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0097@¢\u0006\u0002\u0010\u000eJ,\u0010(\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0097@¢\u0006\u0002\u0010*J\u0016\u0010+\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\"J\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H§@¢\u0006\u0002\u0010-J\u0016\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\"J\u000e\u00100\u001a\u00020\u0010H§@¢\u0006\u0002\u0010-¨\u00061"}, d2 = {"Lai/nous/aurora/data/local/db/MessageDao;", "", "observeMessages", "Lkotlinx/coroutines/flow/Flow;", "", "Lai/nous/aurora/data/local/db/MessageEntity;", "sessionId", "", "ownerKey", "listForSession", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "getByIdInSession", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "message", "(Lai/nous/aurora/data/local/db/MessageEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "messages", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteForSession", "failedForSession", "outboxForSession", "setDeliveryStatus", "", NotificationCompat.CATEGORY_STATUS, "updateStatusIf", "expectedStatus", "newStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "failAllPending", "failInterruptedForOwner", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "failAllPendingForOwner", "deleteById", "claimForRetry", "markSending", "", "replaceForSession", "rows", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteForOwner", "listOwnerless", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adoptOwnerless", "canonicalOwner", "deleteAll", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface MessageDao {

    /* JADX INFO: renamed from: ai.nous.aurora.data.local.db.MessageDao$claimForRetry$1, reason: invalid class name */
    /* JADX INFO: compiled from: MessageDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.local.db.MessageDao$DefaultImpls", f = "MessageDao.kt", i = {0, 0, 0, 0}, l = {ThreadVoidDensityPolicy.VOID_MARK_TOP_DP, LocationRequestCompat.QUALITY_LOW_POWER}, m = "claimForRetry", n = {"$this", "id", "sessionId", "ownerKey"}, s = {"L$0", "L$1", "L$2", "L$3"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.claimForRetry(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.local.db.MessageDao$markSending$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MessageDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.local.db.MessageDao$DefaultImpls", f = "MessageDao.kt", i = {0, 0, 0, 0}, l = {114, 117}, m = "markSending", n = {"$this", "id", "sessionId", "ownerKey"}, s = {"L$0", "L$1", "L$2", "L$3"})
    static final class C02081 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C02081(Continuation<? super C02081> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.markSending(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.local.db.MessageDao$replaceForSession$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MessageDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.local.db.MessageDao$DefaultImpls", f = "MessageDao.kt", i = {0, 0, 0, 0, 1, 1, 1, 2, 2, 2}, l = {128, 129, 130, 141}, m = "replaceForSession", n = {"$this", "sessionId", "ownerKey", "rows", "$this", "rows", "outbox", "$this", "rows", "outbox"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    static final class C02091 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C02091(Continuation<? super C02091> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.replaceForSession(null, null, null, null, this);
        }
    }

    Object adoptOwnerless(String str, Continuation<? super Integer> continuation);

    Object claimForRetry(String str, String str2, String str3, Continuation<? super MessageEntity> continuation);

    Object deleteAll(Continuation<? super Unit> continuation);

    Object deleteById(String str, String str2, Continuation<? super Unit> continuation);

    Object deleteForOwner(String str, Continuation<? super Unit> continuation);

    Object deleteForSession(String str, String str2, Continuation<? super Unit> continuation);

    Object failAllPending(String str, String str2, Continuation<? super Integer> continuation);

    @Deprecated(message = "Use failInterruptedForOwner", replaceWith = @ReplaceWith(expression = "failInterruptedForOwner(ownerKey)", imports = {}))
    Object failAllPendingForOwner(String str, Continuation<? super Integer> continuation);

    Object failInterruptedForOwner(String str, Continuation<? super Integer> continuation);

    Object failedForSession(String str, String str2, Continuation<? super List<MessageEntity>> continuation);

    Object getById(String str, String str2, Continuation<? super MessageEntity> continuation);

    Object getByIdInSession(String str, String str2, String str3, Continuation<? super MessageEntity> continuation);

    Object insert(MessageEntity messageEntity, Continuation<? super Unit> continuation);

    Object insertAll(List<MessageEntity> list, Continuation<? super Unit> continuation);

    Object listForSession(String str, String str2, Continuation<? super List<MessageEntity>> continuation);

    Object listOwnerless(Continuation<? super List<MessageEntity>> continuation);

    Object markSending(String str, String str2, String str3, Continuation<? super Boolean> continuation);

    Flow<List<MessageEntity>> observeMessages(String sessionId, String ownerKey);

    Object outboxForSession(String str, String str2, Continuation<? super List<MessageEntity>> continuation);

    Object replaceForSession(String str, String str2, List<MessageEntity> list, Continuation<? super Unit> continuation);

    Object setDeliveryStatus(String str, String str2, String str3, Continuation<? super Integer> continuation);

    Object updateStatusIf(String str, String str2, String str3, String str4, String str5, Continuation<? super Integer> continuation);

    /* JADX INFO: compiled from: MessageDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX WARN: Code duplicated, block: B:20:0x0086 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:21:0x0087  */
        /* JADX WARN: Code duplicated, block: B:23:0x0099 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:26:0x009f  */
        /* JADX WARN: Code duplicated, block: B:30:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:7:0x0016  */
        public static Object claimForRetry(MessageDao $this, String id, String str, String ownerKey, Continuation<? super MessageEntity> continuation) {
            AnonymousClass1 anonymousClass1;
            String sessionId;
            Object objUpdateStatusIf;
            MessageDao $this2;
            String id2;
            String sessionId2;
            int n;
            Object byIdInSession;
            MessageEntity it;
            if (continuation instanceof AnonymousClass1) {
                anonymousClass1 = (AnonymousClass1) continuation;
                if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                    anonymousClass1.label -= Integer.MIN_VALUE;
                } else {
                    anonymousClass1 = new AnonymousClass1(continuation);
                }
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
            Object $result = anonymousClass1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (anonymousClass1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    sessionId = str;
                    anonymousClass1.L$0 = $this;
                    anonymousClass1.L$1 = id;
                    anonymousClass1.L$2 = sessionId;
                    anonymousClass1.L$3 = ownerKey;
                    anonymousClass1.label = 1;
                    objUpdateStatusIf = $this.updateStatusIf(id, sessionId, ownerKey, OutboxExactlyOnce.STATUS_FAILED, OutboxExactlyOnce.STATUS_PENDING, anonymousClass1);
                    if (objUpdateStatusIf == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this2 = $this;
                    id2 = id;
                    sessionId2 = ownerKey;
                    n = ((Number) objUpdateStatusIf).intValue();
                    if (n != 1) {
                        return null;
                    }
                    anonymousClass1.L$0 = null;
                    anonymousClass1.L$1 = null;
                    anonymousClass1.L$2 = null;
                    anonymousClass1.L$3 = null;
                    anonymousClass1.label = 2;
                    byIdInSession = $this2.getByIdInSession(id2, sessionId, sessionId2, anonymousClass1);
                    if (byIdInSession == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    it = (MessageEntity) byIdInSession;
                    if (it == null && Intrinsics.areEqual(it.getDeliveryStatus(), OutboxExactlyOnce.STATUS_PENDING)) {
                        return it;
                    }
                    return null;
                case 1:
                    String ownerKey2 = (String) anonymousClass1.L$3;
                    String sessionId3 = (String) anonymousClass1.L$2;
                    id2 = (String) anonymousClass1.L$1;
                    $this2 = (MessageDao) anonymousClass1.L$0;
                    ResultKt.throwOnFailure($result);
                    sessionId = sessionId3;
                    sessionId2 = ownerKey2;
                    objUpdateStatusIf = $result;
                    n = ((Number) objUpdateStatusIf).intValue();
                    if (n != 1) {
                        return null;
                    }
                    anonymousClass1.L$0 = null;
                    anonymousClass1.L$1 = null;
                    anonymousClass1.L$2 = null;
                    anonymousClass1.L$3 = null;
                    anonymousClass1.label = 2;
                    byIdInSession = $this2.getByIdInSession(id2, sessionId, sessionId2, anonymousClass1);
                    if (byIdInSession == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    it = (MessageEntity) byIdInSession;
                    if (it == null) {
                        return null;
                    }
                    return null;
                case 2:
                    ResultKt.throwOnFailure($result);
                    byIdInSession = $result;
                    it = (MessageEntity) byIdInSession;
                    if (it == null) {
                        return null;
                    }
                    return null;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: Code duplicated, block: B:20:0x0080  */
        /* JADX WARN: Code duplicated, block: B:22:0x0085  */
        /* JADX WARN: Code duplicated, block: B:24:0x0096 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:27:0x009b  */
        /* JADX WARN: Code duplicated, block: B:7:0x0016  */
        public static Object markSending(MessageDao $this, String id, String str, String ownerKey, Continuation<? super Boolean> continuation) {
            C02081 c02081;
            String sessionId;
            Object objUpdateStatusIf;
            MessageDao $this2;
            String id2;
            String sessionId2;
            int fromPending;
            Object byIdInSession;
            if (continuation instanceof C02081) {
                c02081 = (C02081) continuation;
                if ((c02081.label & Integer.MIN_VALUE) != 0) {
                    c02081.label -= Integer.MIN_VALUE;
                } else {
                    c02081 = new C02081(continuation);
                }
            } else {
                c02081 = new C02081(continuation);
            }
            Object $result = c02081.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (c02081.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    sessionId = str;
                    c02081.L$0 = $this;
                    c02081.L$1 = id;
                    c02081.L$2 = sessionId;
                    c02081.L$3 = ownerKey;
                    c02081.label = 1;
                    objUpdateStatusIf = $this.updateStatusIf(id, sessionId, ownerKey, OutboxExactlyOnce.STATUS_PENDING, OutboxExactlyOnce.STATUS_SENDING, c02081);
                    if (objUpdateStatusIf == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this2 = $this;
                    id2 = id;
                    sessionId2 = ownerKey;
                    fromPending = ((Number) objUpdateStatusIf).intValue();
                    if (fromPending == 1) {
                        return Boxing.boxBoolean(true);
                    }
                    c02081.L$0 = null;
                    c02081.L$1 = null;
                    c02081.L$2 = null;
                    c02081.L$3 = null;
                    c02081.label = 2;
                    byIdInSession = $this2.getByIdInSession(id2, sessionId, sessionId2, c02081);
                    if (byIdInSession == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    MessageEntity already = (MessageEntity) byIdInSession;
                    return Boxing.boxBoolean(Intrinsics.areEqual(already != null ? already.getDeliveryStatus() : null, OutboxExactlyOnce.STATUS_SENDING));
                case 1:
                    String ownerKey2 = (String) c02081.L$3;
                    String sessionId3 = (String) c02081.L$2;
                    id2 = (String) c02081.L$1;
                    $this2 = (MessageDao) c02081.L$0;
                    ResultKt.throwOnFailure($result);
                    sessionId = sessionId3;
                    sessionId2 = ownerKey2;
                    objUpdateStatusIf = $result;
                    fromPending = ((Number) objUpdateStatusIf).intValue();
                    if (fromPending == 1) {
                        return Boxing.boxBoolean(true);
                    }
                    c02081.L$0 = null;
                    c02081.L$1 = null;
                    c02081.L$2 = null;
                    c02081.L$3 = null;
                    c02081.label = 2;
                    byIdInSession = $this2.getByIdInSession(id2, sessionId, sessionId2, c02081);
                    if (byIdInSession == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    MessageEntity already2 = (MessageEntity) byIdInSession;
                    return Boxing.boxBoolean(Intrinsics.areEqual(already2 != null ? already2.getDeliveryStatus() : null, OutboxExactlyOnce.STATUS_SENDING));
                case 2:
                    ResultKt.throwOnFailure($result);
                    byIdInSession = $result;
                    MessageEntity already3 = (MessageEntity) byIdInSession;
                    return Boxing.boxBoolean(Intrinsics.areEqual(already3 != null ? already3.getDeliveryStatus() : null, OutboxExactlyOnce.STATUS_SENDING));
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: Code duplicated, block: B:21:0x009a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:22:0x009b  */
        /* JADX WARN: Code duplicated, block: B:25:0x00a7  */
        /* JADX WARN: Code duplicated, block: B:27:0x00b6 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:29:0x00ba  */
        /* JADX WARN: Code duplicated, block: B:32:0x00c2  */
        /* JADX WARN: Code duplicated, block: B:34:0x00c5  */
        /* JADX WARN: Code duplicated, block: B:36:0x00ee  */
        /* JADX WARN: Code duplicated, block: B:38:0x00fd A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:7:0x0016  */
        public static Object replaceForSession(MessageDao messageDao, String str, String str2, List<MessageEntity> list, Continuation<? super Unit> continuation) {
            C02091 c02091;
            MessageDao $this;
            String ownerKey;
            String sessionId;
            List<MessageEntity> list2;
            Object objOutboxForSession;
            List outbox;
            List<MessageEntity> list3;
            MessageDao $this2;
            List outbox2;
            List outbox3;
            List<MessageEntity> list4;
            List<MessageEntity> listKeepOutboxAfterRemote;
            if (continuation instanceof C02091) {
                c02091 = (C02091) continuation;
                if ((c02091.label & Integer.MIN_VALUE) != 0) {
                    c02091.label -= Integer.MIN_VALUE;
                } else {
                    c02091 = new C02091(continuation);
                }
            } else {
                c02091 = new C02091(continuation);
            }
            Object $result = c02091.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (c02091.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this = messageDao;
                    ownerKey = str2;
                    sessionId = str;
                    list2 = list;
                    c02091.L$0 = $this;
                    c02091.L$1 = sessionId;
                    c02091.L$2 = ownerKey;
                    c02091.L$3 = list2;
                    c02091.label = 1;
                    objOutboxForSession = $this.outboxForSession(sessionId, ownerKey, c02091);
                    if (objOutboxForSession == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    outbox = (List) objOutboxForSession;
                    c02091.L$0 = $this;
                    c02091.L$1 = list2;
                    c02091.L$2 = outbox;
                    c02091.L$3 = null;
                    c02091.label = 2;
                    if ($this.deleteForSession(sessionId, ownerKey, c02091) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    list3 = list2;
                    $this2 = $this;
                    outbox2 = outbox;
                    if (list3.isEmpty()) {
                        outbox3 = outbox2;
                        list4 = list3;
                    } else {
                        c02091.L$0 = $this2;
                        c02091.L$1 = list3;
                        c02091.L$2 = outbox2;
                        c02091.label = 3;
                        if ($this2.insertAll(list3, c02091) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        outbox3 = outbox2;
                        list4 = list3;
                    }
                    if (outbox3.isEmpty()) {
                        return Unit.INSTANCE;
                    }
                    listKeepOutboxAfterRemote = OutboxExactlyOnce.INSTANCE.keepOutboxAfterRemote(list4, outbox3, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$1((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$2((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$3((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$4((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$5((MessageEntity) obj);
                        }
                    });
                    if (!listKeepOutboxAfterRemote.isEmpty()) {
                        c02091.L$0 = null;
                        c02091.L$1 = null;
                        c02091.L$2 = null;
                        c02091.label = 4;
                        if ($this2.insertAll(listKeepOutboxAfterRemote, c02091) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    list2 = (List) c02091.L$3;
                    ownerKey = (String) c02091.L$2;
                    sessionId = (String) c02091.L$1;
                    $this = (MessageDao) c02091.L$0;
                    ResultKt.throwOnFailure($result);
                    objOutboxForSession = $result;
                    outbox = (List) objOutboxForSession;
                    c02091.L$0 = $this;
                    c02091.L$1 = list2;
                    c02091.L$2 = outbox;
                    c02091.L$3 = null;
                    c02091.label = 2;
                    if ($this.deleteForSession(sessionId, ownerKey, c02091) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    list3 = list2;
                    $this2 = $this;
                    outbox2 = outbox;
                    if (list3.isEmpty()) {
                        c02091.L$0 = $this2;
                        c02091.L$1 = list3;
                        c02091.L$2 = outbox2;
                        c02091.label = 3;
                        if ($this2.insertAll(list3, c02091) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        outbox3 = outbox2;
                        list4 = list3;
                    } else {
                        outbox3 = outbox2;
                        list4 = list3;
                    }
                    if (outbox3.isEmpty()) {
                        return Unit.INSTANCE;
                    }
                    listKeepOutboxAfterRemote = OutboxExactlyOnce.INSTANCE.keepOutboxAfterRemote(list4, outbox3, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$1((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$2((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$3((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$4((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$5((MessageEntity) obj);
                        }
                    });
                    if (!listKeepOutboxAfterRemote.isEmpty()) {
                        c02091.L$0 = null;
                        c02091.L$1 = null;
                        c02091.L$2 = null;
                        c02091.label = 4;
                        if ($this2.insertAll(listKeepOutboxAfterRemote, c02091) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    outbox2 = (List) c02091.L$2;
                    list3 = (List) c02091.L$1;
                    $this2 = (MessageDao) c02091.L$0;
                    ResultKt.throwOnFailure($result);
                    if (list3.isEmpty()) {
                        c02091.L$0 = $this2;
                        c02091.L$1 = list3;
                        c02091.L$2 = outbox2;
                        c02091.label = 3;
                        if ($this2.insertAll(list3, c02091) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        outbox3 = outbox2;
                        list4 = list3;
                    } else {
                        outbox3 = outbox2;
                        list4 = list3;
                    }
                    if (outbox3.isEmpty()) {
                        return Unit.INSTANCE;
                    }
                    listKeepOutboxAfterRemote = OutboxExactlyOnce.INSTANCE.keepOutboxAfterRemote(list4, outbox3, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$1((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$2((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$3((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$4((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$5((MessageEntity) obj);
                        }
                    });
                    if (!listKeepOutboxAfterRemote.isEmpty()) {
                        c02091.L$0 = null;
                        c02091.L$1 = null;
                        c02091.L$2 = null;
                        c02091.label = 4;
                        if ($this2.insertAll(listKeepOutboxAfterRemote, c02091) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 3:
                    outbox2 = (List) c02091.L$2;
                    list3 = (List) c02091.L$1;
                    $this2 = (MessageDao) c02091.L$0;
                    ResultKt.throwOnFailure($result);
                    outbox3 = outbox2;
                    list4 = list3;
                    if (outbox3.isEmpty()) {
                        return Unit.INSTANCE;
                    }
                    listKeepOutboxAfterRemote = OutboxExactlyOnce.INSTANCE.keepOutboxAfterRemote(list4, outbox3, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$1((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$2((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$3((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$4((MessageEntity) obj);
                        }
                    }, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao$DefaultImpls$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MessageDao.DefaultImpls.replaceForSession$lambda$5((MessageEntity) obj);
                        }
                    });
                    if (!listKeepOutboxAfterRemote.isEmpty()) {
                        c02091.L$0 = null;
                        c02091.L$1 = null;
                        c02091.L$2 = null;
                        c02091.label = 4;
                        if ($this2.insertAll(listKeepOutboxAfterRemote, c02091) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 4:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String replaceForSession$lambda$1(MessageEntity it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String replaceForSession$lambda$2(MessageEntity it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getClientMsgId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String replaceForSession$lambda$3(MessageEntity it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getDeliveryStatus();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String replaceForSession$lambda$4(MessageEntity it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getContent();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String replaceForSession$lambda$5(MessageEntity it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getRole();
        }
    }
}
