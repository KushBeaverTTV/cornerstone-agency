package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SessionSyncPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0018\u0010\f\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000b¨\u0006\u000e"}, d2 = {"Lai/nous/aurora/domain/model/SessionSyncPolicy;", "", "<init>", "()V", "shouldApplyRefresh", "", "completedGeneration", "", "latestGeneration", "isOwnerless", "ownerKey", "", "shouldAdopt", "canonicalOwner", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SessionSyncPolicy {
    public static final int $stable = 0;
    public static final SessionSyncPolicy INSTANCE = new SessionSyncPolicy();

    private SessionSyncPolicy() {
    }

    public final boolean shouldApplyRefresh(long completedGeneration, long latestGeneration) {
        return completedGeneration == latestGeneration && completedGeneration > 0;
    }

    public final boolean isOwnerless(String ownerKey) {
        String str = ownerKey;
        return str == null || str.length() == 0;
    }

    public final boolean shouldAdopt(String ownerKey, String canonicalOwner) {
        Intrinsics.checkNotNullParameter(canonicalOwner, "canonicalOwner");
        return (!isOwnerless(ownerKey) || StringsKt.isBlank(canonicalOwner) || Intrinsics.areEqual(canonicalOwner, "")) ? false : true;
    }
}
