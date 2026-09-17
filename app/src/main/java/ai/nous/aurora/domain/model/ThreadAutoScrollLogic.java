package ai.nous.aurora.domain.model;

import kotlin.Metadata;

/* JADX INFO: compiled from: ThreadAutoScrollLogic.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J\u0016\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0005¨\u0006\u000e"}, d2 = {"Lai/nous/aurora/domain/model/ThreadAutoScrollLogic;", "", "<init>", "()V", "shouldScrollToLatest", "", "followBottom", "messageCount", "", "previousCount", OutboxExactlyOnce.STATUS_SENDING, "followBottomAfterSettle", "canScrollForward", "targetIndex", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ThreadAutoScrollLogic {
    public static final int $stable = 0;
    public static final ThreadAutoScrollLogic INSTANCE = new ThreadAutoScrollLogic();

    private ThreadAutoScrollLogic() {
    }

    public static /* synthetic */ boolean shouldScrollToLatest$default(ThreadAutoScrollLogic threadAutoScrollLogic, boolean z, int i, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            z2 = false;
        }
        return threadAutoScrollLogic.shouldScrollToLatest(z, i, i2, z2);
    }

    public final boolean shouldScrollToLatest(boolean followBottom, int messageCount, int previousCount, boolean sending) {
        if ((messageCount <= 0 && !sending) || !followBottom) {
            return false;
        }
        if ((previousCount == 0 && (messageCount > 0 || sending)) || messageCount > previousCount) {
            return true;
        }
        if (!sending || messageCount < previousCount) {
            return false;
        }
        return true;
    }

    public final boolean followBottomAfterSettle(boolean canScrollForward) {
        return !canScrollForward;
    }

    public final int targetIndex(int messageCount, boolean sending) {
        if (messageCount <= 0) {
            return 0;
        }
        return (messageCount - 1) + (sending ? 1 : 0);
    }
}
