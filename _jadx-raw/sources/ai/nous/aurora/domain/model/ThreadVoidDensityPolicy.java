package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: ThreadVoidDensityPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J*\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000eJ*\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0013\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lai/nous/aurora/domain/model/ThreadVoidDensityPolicy;", "", "<init>", "()V", "SHORT_MAX", "", "SPACING_COMPACT_DP", "SPACING_COMFORTABLE_DP", "VOID_MARK_TOP_DP", "mode", "Lai/nous/aurora/domain/model/ThreadVoidDensityPolicy$Mode;", "messageCount", "bubbleSpacingDp", "showVoidBrandMark", "", "astral", OutboxExactlyOnce.STATUS_SENDING, "listening", "showEmberQuietGlow", "voidMarkTopDp", "Mode", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ThreadVoidDensityPolicy {
    public static final int $stable = 0;
    public static final ThreadVoidDensityPolicy INSTANCE = new ThreadVoidDensityPolicy();
    public static final int SHORT_MAX = 4;
    public static final int SPACING_COMFORTABLE_DP = 10;
    public static final int SPACING_COMPACT_DP = 8;
    public static final int VOID_MARK_TOP_DP = 96;

    /* JADX INFO: compiled from: ThreadVoidDensityPolicy.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Mode.values().length];
            try {
                iArr[Mode.Compact.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Mode.Comfortable.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ThreadVoidDensityPolicy() {
    }

    /* JADX INFO: compiled from: ThreadVoidDensityPolicy.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lai/nous/aurora/domain/model/ThreadVoidDensityPolicy$Mode;", "", "<init>", "(Ljava/lang/String;I)V", "Compact", "Comfortable", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Mode {
        Compact,
        Comfortable;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Mode> getEntries() {
            return $ENTRIES;
        }
    }

    public final Mode mode(int messageCount) {
        boolean z = false;
        if (1 <= messageCount && messageCount < 5) {
            z = true;
        }
        return z ? Mode.Compact : Mode.Comfortable;
    }

    public final int bubbleSpacingDp(int messageCount) {
        switch (WhenMappings.$EnumSwitchMapping$0[mode(messageCount).ordinal()]) {
            case 1:
                return 8;
            case 2:
                return 10;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ boolean showVoidBrandMark$default(ThreadVoidDensityPolicy threadVoidDensityPolicy, int i, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        if ((i2 & 8) != 0) {
            z3 = false;
        }
        return threadVoidDensityPolicy.showVoidBrandMark(i, z, z2, z3);
    }

    public final boolean showVoidBrandMark(int messageCount, boolean astral, boolean sending, boolean listening) {
        if (messageCount <= 0 || messageCount > 4 || !astral || sending || listening) {
            return false;
        }
        return true;
    }

    public static /* synthetic */ boolean showEmberQuietGlow$default(ThreadVoidDensityPolicy threadVoidDensityPolicy, int i, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        if ((i2 & 8) != 0) {
            z3 = false;
        }
        return threadVoidDensityPolicy.showEmberQuietGlow(i, z, z2, z3);
    }

    public final boolean showEmberQuietGlow(int messageCount, boolean astral, boolean sending, boolean listening) {
        if (astral) {
            return false;
        }
        return (!(1 <= messageCount && messageCount < 5) || sending || listening) ? false : true;
    }

    public final int voidMarkTopDp() {
        return 96;
    }
}
