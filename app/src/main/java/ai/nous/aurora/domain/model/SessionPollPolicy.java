package ai.nous.aurora.domain.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SessionSyncPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fJ\u0016\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lai/nous/aurora/domain/model/SessionPollPolicy;", "", "<init>", "()V", "MIN_INTERVAL_MS", "", "MAX_INTERVAL_MS", "BACKOFF_FACTOR", "", "nextIntervalMs", "currentMs", "hadChanges", "", "hadError", "shouldPoll", "appInForeground", "connected", "fingerprint", "", "ids", "", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SessionPollPolicy {
    public static final int $stable = 0;
    public static final double BACKOFF_FACTOR = 2.0d;
    public static final SessionPollPolicy INSTANCE = new SessionPollPolicy();
    public static final long MAX_INTERVAL_MS = 60000;
    public static final long MIN_INTERVAL_MS = 4000;

    private SessionPollPolicy() {
    }

    public final long nextIntervalMs(long currentMs, boolean hadChanges, boolean hadError) {
        if (hadChanges && !hadError) {
            return MIN_INTERVAL_MS;
        }
        if (hadError) {
            long next = RangesKt.coerceAtLeast((long) (currentMs * 2.0d), MIN_INTERVAL_MS);
            return RangesKt.coerceAtMost(next, MAX_INTERVAL_MS);
        }
        long next2 = (long) (currentMs * 1.5d);
        return RangesKt.coerceIn(next2, MIN_INTERVAL_MS, MAX_INTERVAL_MS);
    }

    public final boolean shouldPoll(boolean appInForeground, boolean connected) {
        return appInForeground && connected;
    }

    public final String fingerprint(List<String> ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        return ai.nous.aurora.compat.Compat.d_CollectionsKt_joinToString_9(CollectionsKt.filterNotNull(ids), "|", null, null, 0, null, null, 62, null);
    }
}
