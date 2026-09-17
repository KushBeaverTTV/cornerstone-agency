package ai.nous.aurora.domain.model;

import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: OutboxExactlyOnce.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b+\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005J\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0005J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0018J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0018J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u0005J)\u0010\u001e\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010\"J\u000e\u0010#\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0005J\u008b\u0001\u0010$\u001a\u0004\u0018\u0001H%\"\u0004\b\u0000\u0010%2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H%0'2\u0006\u0010(\u001a\u00020\u00052\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00050*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00050*2\u0014\b\u0002\u0010,\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\f0*2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010-\u001a\u0010\u0012\u0004\u0012\u0002H%\u0012\u0006\u0012\u0004\u0018\u00010\u00050*¢\u0006\u0002\u0010.J\u0018\u0010/\u001a\u0004\u0018\u00010\u00052\u0006\u00100\u001a\u00020\f2\u0006\u00101\u001a\u00020\fJ\u0010\u00102\u001a\u0004\u0018\u00010\u00052\u0006\u00103\u001a\u00020\u0005J\u0098\u0001\u00104\u001a\b\u0012\u0004\u0012\u0002H%0'\"\u0004\b\u0000\u0010%2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H%0'2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H%0'2\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00050*2\u0014\u0010-\u001a\u0010\u0012\u0004\u0012\u0002H%\u0012\u0006\u0012\u0004\u0018\u00010\u00050*2\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00050*2\u0014\b\u0002\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00050*2\u0014\b\u0002\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00050*J`\u00108\u001a\b\u0012\u0004\u0012\u0002H%0'\"\u0004\b\u0000\u0010%2\f\u00109\u001a\b\u0012\u0004\u0012\u0002H%0'2\u0014\u00106\u001a\u0010\u0012\u0004\u0012\u0002H%\u0012\u0006\u0012\u0004\u0018\u00010\u00050*2\u0014\u0010-\u001a\u0010\u0012\u0004\u0012\u0002H%\u0012\u0006\u0012\u0004\u0018\u00010\u00050*2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\f0*J7\u0010:\u001a\u0002H%\"\u0004\b\u0000\u0010%2\u0006\u0010;\u001a\u0002H%2\u0006\u0010<\u001a\u0002H%2\u0014\u00106\u001a\u0010\u0012\u0004\u0012\u0002H%\u0012\u0006\u0012\u0004\u0018\u00010\u00050*¢\u0006\u0002\u0010=J\u0016\u0010>\u001a\u00020\f2\u0006\u0010?\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0005J\u0016\u0010A\u001a\u00020\u00052\u0006\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u0005J\u0016\u0010D\u001a\u00020\u00052\u0006\u0010B\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005J\u0006\u0010E\u001a\u00020\u0005J\u0016\u0010F\u001a\u00020\f2\u0006\u0010G\u001a\u00020!2\u0006\u0010H\u001a\u00020\fJ\u000e\u0010I\u001a\u00020\f2\u0006\u0010J\u001a\u00020!J\u0010\u0010K\u001a\u0004\u0018\u00010\u00052\u0006\u0010L\u001a\u00020\u0005J:\u0010M\u001a\u0004\u0018\u00010\u00052\u0006\u0010N\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00052\u0006\u0010R\u001a\u00020\u00052\b\b\u0002\u0010S\u001a\u00020\u0005J\u0006\u0010T\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Lai/nous/aurora/domain/model/OutboxExactlyOnce;", "", "<init>", "()V", "STATUS_PENDING", "", "STATUS_SENDING", "STATUS_FAILED", "STATUS_SENT", "STATUS_AMBIGUOUS", "IDEMPOTENCY_HEADER", "SERVER_SESSION_CHAT_IDEMPOTENT", "", "SERVER_API_SERVER_INSTALLED_PATH", "CODE_IDEMPOTENCY_PENDING", "CODE_IDEMPOTENCY_CONFLICT", "localIdForClient", "clientMsgId", "isOutboxStatus", NotificationCompat.CATEGORY_STATUS, "isRetryable", "requiresReconcileBeforeRetry", "canClaimForDelivery", "interruptedStatuses", "", "cancelFailStatuses", "classifyIdempotencyErrorCode", "code", "extractServerErrorCode", "errorMessage", "classifyTransportFailure", "networkStarted", "httpStatus", "", "(Ljava/lang/String;ZLjava/lang/Integer;)Ljava/lang/String;", "retryActionAllowed", "findRemoteContentMatch", "T", "remote", "", "content", "roleOf", "Lkotlin/Function1;", "contentOf", "isUser", "clientMsgIdOf", "(Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "reconcileAmbiguousAgainstRemote", "remoteLoaded", "remoteHasMatchingUserContent", "statusAfterProcessDeathInterrupt", "previous", "keepOutboxAfterRemote", "outbox", "idOf", "statusOf", "dedupeByClientMsgId", "rows", "preferRemoteBubble", "a", "b", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "retryKeepsSameBubble", "originalLocalId", "retryLocalId", "deliveryKey", "ownerKey", "localMessageId", "idempotencyKey", "statusAfterNetworkAck", "reflectsEmptyThread", "messageCount", "isLoading", "reflectsEmptySessionList", "sessionCount", "claimRetryTransition", "currentStatus", "claimScoped", "rowSessionId", "rowOwner", "rowStatus", "expectedSessionId", "expectedOwner", "expectedStatus", "statusAfterPreNetworkRejection", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OutboxExactlyOnce {
    public static final int $stable = 0;
    public static final String CODE_IDEMPOTENCY_CONFLICT = "idempotency_conflict";
    public static final String CODE_IDEMPOTENCY_PENDING = "idempotency_pending";
    public static final String IDEMPOTENCY_HEADER = "Idempotency-Key";
    public static final OutboxExactlyOnce INSTANCE = new OutboxExactlyOnce();
    public static final String SERVER_API_SERVER_INSTALLED_PATH = "/opt/hermes/gateway/platforms/api_server.py";
    public static final boolean SERVER_SESSION_CHAT_IDEMPOTENT = true;
    public static final String STATUS_AMBIGUOUS = "ambiguous";
    public static final String STATUS_FAILED = "failed";
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_SENDING = "sending";
    public static final String STATUS_SENT = "sent";

    private OutboxExactlyOnce() {
    }

    public final String localIdForClient(String clientMsgId) {
        Intrinsics.checkNotNullParameter(clientMsgId, "clientMsgId");
        return "local-" + clientMsgId;
    }

    public final boolean isOutboxStatus(String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return Intrinsics.areEqual(status, STATUS_PENDING) || Intrinsics.areEqual(status, STATUS_FAILED) || Intrinsics.areEqual(status, STATUS_SENDING) || Intrinsics.areEqual(status, STATUS_AMBIGUOUS);
    }

    public final boolean isRetryable(String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return Intrinsics.areEqual(status, STATUS_FAILED);
    }

    public final boolean requiresReconcileBeforeRetry(String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return Intrinsics.areEqual(status, STATUS_AMBIGUOUS);
    }

    public final boolean canClaimForDelivery(String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return Intrinsics.areEqual(status, STATUS_FAILED) || Intrinsics.areEqual(status, STATUS_PENDING);
    }

    public final Set<String> interruptedStatuses() {
        return SetsKt.setOf((Object[]) new String[]{STATUS_PENDING, STATUS_SENDING});
    }

    public final Set<String> cancelFailStatuses() {
        return SetsKt.setOf(STATUS_PENDING);
    }

    public final String classifyIdempotencyErrorCode(String code) {
        String lowerCase;
        String string;
        if (code == null || (string = StringsKt.trim((CharSequence) code).toString()) == null) {
            lowerCase = null;
        } else {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        if (!Intrinsics.areEqual(lowerCase, CODE_IDEMPOTENCY_PENDING) && !Intrinsics.areEqual(lowerCase, CODE_IDEMPOTENCY_CONFLICT)) {
            return null;
        }
        return STATUS_AMBIGUOUS;
    }

    public final String extractServerErrorCode(String errorMessage) {
        List<String> groupValues;
        String it;
        String msg = errorMessage == null ? "" : errorMessage;
        MatchResult matchResultFind$default = Regex.find$default(new Regex("\"code\"\\s*:\\s*\"([^\"]+)\""), msg, 0, 2, null);
        if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (it = (String) CollectionsKt.getOrNull(groupValues, 1)) != null) {
            return it;
        }
        Iterable $this$forEach$iv = CollectionsKt.listOf((Object[]) new String[]{CODE_IDEMPOTENCY_PENDING, CODE_IDEMPOTENCY_CONFLICT});
        for (Object element$iv : $this$forEach$iv) {
            String token = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) msg, (CharSequence) token, false, 2, (Object) null)) {
                return token;
            }
        }
        return null;
    }

    public static /* synthetic */ String classifyTransportFailure$default(OutboxExactlyOnce outboxExactlyOnce, String str, boolean z, Integer num, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        return outboxExactlyOnce.classifyTransportFailure(str, z, num);
    }

    public final String classifyTransportFailure(String errorMessage, boolean networkStarted, Integer httpStatus) {
        Integer parsedHttp;
        boolean looksTimeout;
        List<String> groupValues;
        String str;
        if (!networkStarted) {
            return STATUS_FAILED;
        }
        String msg = errorMessage == null ? "" : errorMessage;
        if (httpStatus == null) {
            MatchResult matchResultFind$default = Regex.find$default(new Regex("\\bHTTP\\s+(\\d{3})\\b", RegexOption.IGNORE_CASE), msg, 0, 2, null);
            parsedHttp = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = (String) CollectionsKt.getOrNull(groupValues, 1)) == null) ? null : StringsKt.toIntOrNull(str);
        } else {
            parsedHttp = httpStatus;
        }
        String code = extractServerErrorCode(msg);
        String it = classifyIdempotencyErrorCode(code);
        if (it != null) {
            return it;
        }
        if (parsedHttp != null && parsedHttp.intValue() == 409) {
            return STATUS_AMBIGUOUS;
        }
        if (parsedHttp != null && new IntRange(400, 499).contains(parsedHttp.intValue()) && parsedHttp.intValue() != 408 && parsedHttp.intValue() != 429) {
            return STATUS_FAILED;
        }
        String lower = msg.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        Iterable $this$any$iv = CollectionsKt.listOf((Object[]) new String[]{"timeout", "timed out", "socket closed", "connection reset", "unexpected end", "canceled", "cancelled", "stream was reset", "broken pipe", "failed to connect", "unable to resolve"});
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it2 = $this$any$iv.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    looksTimeout = false;
                    break;
                }
                Object element$iv = it2.next();
                if (StringsKt.contains$default((CharSequence) lower, (CharSequence) element$iv, false, 2, (Object) null)) {
                    looksTimeout = true;
                    break;
                }
            }
        } else {
            looksTimeout = false;
        }
        return (looksTimeout || parsedHttp == null || parsedHttp.intValue() == 408 || parsedHttp.intValue() >= 500) ? STATUS_AMBIGUOUS : STATUS_FAILED;
    }

    public final boolean retryActionAllowed(String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return isRetryable(status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean findRemoteContentMatch$lambda$4(Function1 $roleOf, Object it) {
        return StringsKt.equals((String) $roleOf.invoke(it), "user", true);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0086  */
    /* JADX WARN: Type inference failed for: r13v1, types: [T, java.lang.Object] */
    public final <T> T findRemoteContentMatch(List<? extends T> remote, String content, Function1<? super T, String> roleOf, Function1<? super T, String> contentOf, Function1<? super T, Boolean> isUser, String clientMsgId, Function1<? super T, String> clientMsgIdOf) {
        Object objPrevious;
        boolean z;
        Intrinsics.checkNotNullParameter(remote, "remote");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(roleOf, "roleOf");
        Intrinsics.checkNotNullParameter(contentOf, "contentOf");
        Intrinsics.checkNotNullParameter(isUser, "isUser");
        Intrinsics.checkNotNullParameter(clientMsgIdOf, "clientMsgIdOf");
        String str = (clientMsgId == null || StringsKt.isBlank(clientMsgId)) ? null : clientMsgId;
        if (str != null) {
            ListIterator<? extends T> listIterator = remote.listIterator(remote.size());
            do {
                if (!listIterator.hasPrevious()) {
                    objPrevious = null;
                    break;
                }
                objPrevious = listIterator.previous();
                if (isUser.invoke(objPrevious).booleanValue()) {
                    String strInvoke = clientMsgIdOf.invoke(objPrevious);
                    if (Intrinsics.areEqual((strInvoke == null || StringsKt.isBlank(strInvoke)) ? null : strInvoke, str)) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
            } while (!z);
            if (objPrevious != null) {
                return (T) objPrevious;
            }
        }
        String string = StringsKt.trim((CharSequence) content).toString();
        if (string.length() == 0) {
            return null;
        }
        ListIterator<? extends T> listIterator2 = remote.listIterator(remote.size());
        while (listIterator2.hasPrevious()) {
            T tPrevious = listIterator2.previous();
            if (isUser.invoke(tPrevious).booleanValue() && Intrinsics.areEqual(StringsKt.trim((CharSequence) contentOf.invoke(tPrevious)).toString(), string)) {
                return tPrevious;
            }
        }
        return null;
    }

    public final String reconcileAmbiguousAgainstRemote(boolean remoteLoaded, boolean remoteHasMatchingUserContent) {
        if (remoteLoaded) {
            return remoteHasMatchingUserContent ? STATUS_SENT : STATUS_FAILED;
        }
        return null;
    }

    public final String statusAfterProcessDeathInterrupt(String previous) {
        Intrinsics.checkNotNullParameter(previous, "previous");
        if (interruptedStatuses().contains(previous)) {
            return STATUS_FAILED;
        }
        return null;
    }

    public static /* synthetic */ List keepOutboxAfterRemote$default(OutboxExactlyOnce outboxExactlyOnce, List list, List list2, Function1 function1, Function1 function2, Function1 function3, Function1 function4, Function1 function5, int i, Object obj) {
        Function1 function6;
        Function1 function7;
        if ((i & 32) == 0) {
            function6 = function4;
        } else {
            function6 = new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return OutboxExactlyOnce.keepOutboxAfterRemote$lambda$10(obj2);
                }
            };
        }
        if ((i & 64) == 0) {
            function7 = function5;
        } else {
            function7 = new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return OutboxExactlyOnce.keepOutboxAfterRemote$lambda$11(obj2);
                }
            };
        }
        return outboxExactlyOnce.keepOutboxAfterRemote(list, list2, function1, function2, function3, function6, function7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String keepOutboxAfterRemote$lambda$10(Object it) {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String keepOutboxAfterRemote$lambda$11(Object it) {
        return "user";
    }

    public final <T> List<T> keepOutboxAfterRemote(List<? extends T> remote, List<? extends T> outbox, Function1<? super T, String> idOf, Function1<? super T, String> clientMsgIdOf, Function1<? super T, String> statusOf, Function1<? super T, String> contentOf, Function1<? super T, String> roleOf) {
        boolean z;
        String it;
        Function1<? super T, String> statusOf2 = statusOf;
        Function1<? super T, String> roleOf2 = roleOf;
        Intrinsics.checkNotNullParameter(remote, "remote");
        Intrinsics.checkNotNullParameter(outbox, "outbox");
        Intrinsics.checkNotNullParameter(idOf, "idOf");
        Intrinsics.checkNotNullParameter(clientMsgIdOf, "clientMsgIdOf");
        Intrinsics.checkNotNullParameter(statusOf2, "statusOf");
        Intrinsics.checkNotNullParameter(contentOf, "contentOf");
        Intrinsics.checkNotNullParameter(roleOf2, "roleOf");
        List<? extends T> $this$mapTo$iv = remote;
        Collection destination$iv = new HashSet();
        for (Object item$iv : $this$mapTo$iv) {
            destination$iv.add(idOf.invoke(item$iv));
        }
        HashSet remoteIds = (HashSet) destination$iv;
        Collection destination$iv2 = new HashSet();
        Iterator<T> it2 = remote.iterator();
        while (it2.hasNext()) {
            String c = (String) clientMsgIdOf.invoke(it2.next());
            String str = (c == null || StringsKt.isBlank(c)) ? null : c;
            if (str != null) {
                destination$iv2.add(str);
            }
        }
        HashSet remoteClientIds = (HashSet) destination$iv2;
        List<? extends T> $this$mapNotNullTo$iv = remote;
        Collection destination$iv3 = new HashSet();
        int $i$f$mapNotNullTo = 0;
        for (Object element$iv$iv : $this$mapNotNullTo$iv) {
            Object row = roleOf2.invoke(element$iv$iv);
            String str2 = (String) row;
            Iterable $this$mapNotNullTo$iv2 = $this$mapNotNullTo$iv;
            int $i$f$mapNotNullTo2 = $i$f$mapNotNullTo;
            if (StringsKt.equals(str2, "user", true)) {
                it = StringsKt.trim((CharSequence) contentOf.invoke(element$iv$iv)).toString();
                int $i$f$mapNotNullTo3 = it.length() <= 0 ? 0 : 1;
                if ($i$f$mapNotNullTo3 == 0) {
                    it = null;
                }
            } else {
                it = null;
            }
            if (it != null) {
                destination$iv3.add(it);
            }
            roleOf2 = roleOf;
            $this$mapNotNullTo$iv = $this$mapNotNullTo$iv2;
            $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
        }
        HashSet remoteUserContents = (HashSet) destination$iv3;
        List<? extends T> $this$filter$iv = outbox;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv) {
            OutboxExactlyOnce outboxExactlyOnce = INSTANCE;
            Object local = statusOf2.invoke(element$iv$iv2);
            if (outboxExactlyOnce.isOutboxStatus((String) local)) {
                String id = idOf.invoke(element$iv$iv2);
                String client = clientMsgIdOf.invoke(element$iv$iv2);
                if (remoteIds.contains(id)) {
                    z = false;
                } else {
                    String str3 = client;
                    if ((str3 == null || StringsKt.isBlank(str3)) || !remoteClientIds.contains(client)) {
                        String content = StringsKt.trim((CharSequence) contentOf.invoke(element$iv$iv2)).toString();
                        z = ((content.length() > 0) && remoteUserContents.contains(content)) ? false : true;
                    } else {
                        z = false;
                    }
                }
            } else {
                z = false;
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv2);
            }
            statusOf2 = statusOf;
        }
        return (List) destination$iv$iv;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> List<T> dedupeByClientMsgId(List<? extends T> rows, Function1<? super T, String> idOf, Function1<? super T, String> clientMsgIdOf, Function1<? super T, Boolean> isUser) {
        String it;
        Intrinsics.checkNotNullParameter(rows, "rows");
        Intrinsics.checkNotNullParameter(idOf, "idOf");
        Intrinsics.checkNotNullParameter(clientMsgIdOf, "clientMsgIdOf");
        Intrinsics.checkNotNullParameter(isUser, "isUser");
        if (rows.size() <= 1) {
            return rows;
        }
        LinkedHashMap bestByClient = new LinkedHashMap();
        List<? extends T> $this$forEach$iv = rows;
        Iterator<T> it2 = $this$forEach$iv.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object element$iv = it2.next();
            if (isUser.invoke(element$iv).booleanValue() && (it = clientMsgIdOf.invoke(element$iv)) != null) {
                String client = StringsKt.isBlank(it) ? null : it;
                if (client != null) {
                    Object existing = bestByClient.get(client);
                    if (existing == null) {
                        bestByClient.put(client, element$iv);
                    } else {
                        bestByClient.put(client, INSTANCE.preferRemoteBubble(existing, element$iv, idOf));
                    }
                }
            }
        }
        HashSet emittedClients = new HashSet();
        ArrayList out = new ArrayList(rows.size());
        List<? extends T> $this$forEach$iv2 = rows;
        for (Object element$iv2 : $this$forEach$iv2) {
            if (!isUser.invoke(element$iv2).booleanValue()) {
                out.add(element$iv2);
            } else {
                String client2 = clientMsgIdOf.invoke(element$iv2);
                if (client2 == null || StringsKt.isBlank(client2)) {
                    client2 = null;
                }
                if (client2 == null) {
                    out.add(element$iv2);
                } else if (!emittedClients.contains(client2)) {
                    emittedClients.add(client2);
                    out.add(MapsKt.getValue(bestByClient, client2));
                }
            }
        }
        return out;
    }

    public final <T> T preferRemoteBubble(T a, T b, Function1<? super T, String> idOf) {
        Intrinsics.checkNotNullParameter(idOf, "idOf");
        String strInvoke = idOf.invoke(a);
        boolean aLocal = strInvoke != null && StringsKt.startsWith$default(strInvoke, "local-", false, 2, (Object) null);
        String strInvoke2 = idOf.invoke(b);
        boolean bLocal = strInvoke2 != null && StringsKt.startsWith$default(strInvoke2, "local-", false, 2, (Object) null);
        if (aLocal && !bLocal) {
            return b;
        }
        if (!bLocal || !aLocal) {
        }
        return a;
    }

    public final boolean retryKeepsSameBubble(String originalLocalId, String retryLocalId) {
        Intrinsics.checkNotNullParameter(originalLocalId, "originalLocalId");
        Intrinsics.checkNotNullParameter(retryLocalId, "retryLocalId");
        return Intrinsics.areEqual(originalLocalId, retryLocalId) && StringsKt.startsWith$default(originalLocalId, "local-", false, 2, (Object) null);
    }

    public final String deliveryKey(String ownerKey, String localMessageId) {
        Intrinsics.checkNotNullParameter(ownerKey, "ownerKey");
        Intrinsics.checkNotNullParameter(localMessageId, "localMessageId");
        return ownerKey + "::" + localMessageId;
    }

    public final String idempotencyKey(String ownerKey, String clientMsgId) {
        Intrinsics.checkNotNullParameter(ownerKey, "ownerKey");
        Intrinsics.checkNotNullParameter(clientMsgId, "clientMsgId");
        return "aurora-chat:" + ownerKey + ":" + clientMsgId;
    }

    public final String statusAfterNetworkAck() {
        return STATUS_SENT;
    }

    public final boolean reflectsEmptyThread(int messageCount, boolean isLoading) {
        return !isLoading && messageCount == 0;
    }

    public final boolean reflectsEmptySessionList(int sessionCount) {
        return sessionCount == 0;
    }

    public final String claimRetryTransition(String currentStatus) {
        Intrinsics.checkNotNullParameter(currentStatus, "currentStatus");
        if (Intrinsics.areEqual(currentStatus, STATUS_FAILED)) {
            return STATUS_PENDING;
        }
        return null;
    }

    public static /* synthetic */ String claimScoped$default(OutboxExactlyOnce outboxExactlyOnce, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        String str7;
        if ((i & 32) == 0) {
            str7 = str6;
        } else {
            str7 = STATUS_FAILED;
        }
        return outboxExactlyOnce.claimScoped(str, str2, str3, str4, str5, str7);
    }

    public final String claimScoped(String rowSessionId, String rowOwner, String rowStatus, String expectedSessionId, String expectedOwner, String expectedStatus) {
        Intrinsics.checkNotNullParameter(rowSessionId, "rowSessionId");
        Intrinsics.checkNotNullParameter(rowOwner, "rowOwner");
        Intrinsics.checkNotNullParameter(rowStatus, "rowStatus");
        Intrinsics.checkNotNullParameter(expectedSessionId, "expectedSessionId");
        Intrinsics.checkNotNullParameter(expectedOwner, "expectedOwner");
        Intrinsics.checkNotNullParameter(expectedStatus, "expectedStatus");
        if (Intrinsics.areEqual(rowSessionId, expectedSessionId) && Intrinsics.areEqual(rowOwner, expectedOwner) && Intrinsics.areEqual(rowStatus, expectedStatus)) {
            return STATUS_PENDING;
        }
        return null;
    }

    public final String statusAfterPreNetworkRejection() {
        return STATUS_FAILED;
    }
}
