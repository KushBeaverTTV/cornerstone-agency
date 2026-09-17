package ai.nous.aurora.domain.model;

import kotlin.Metadata;

/* JADX INFO: compiled from: OutboxRetryHitTarget.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lai/nous/aurora/domain/model/OutboxRetryHitTarget;", "", "<init>", "()V", "MIN_HEIGHT_DP", "", "HORIZONTAL_PAD_DP", "VERTICAL_PAD_DP", "usesPillChrome", "", "isPrimary", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OutboxRetryHitTarget {
    public static final int $stable = 0;
    public static final int HORIZONTAL_PAD_DP = 10;
    public static final OutboxRetryHitTarget INSTANCE = new OutboxRetryHitTarget();
    public static final int MIN_HEIGHT_DP = 48;
    public static final int VERTICAL_PAD_DP = 6;

    private OutboxRetryHitTarget() {
    }

    public final boolean usesPillChrome() {
        return true;
    }

    public final boolean isPrimary() {
        return true;
    }
}
