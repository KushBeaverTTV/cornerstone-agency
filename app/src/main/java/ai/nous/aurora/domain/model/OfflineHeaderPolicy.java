package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: OfflineHeaderPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0005J \u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\bJ\u0006\u0010\f\u001a\u00020\b¨\u0006\r"}, d2 = {"Lai/nous/aurora/domain/model/OfflineHeaderPolicy;", "", "<init>", "()V", "showConnectionChip", "", "online", "connectionChipLabel", "", "hostSubtitle", "host", "statusLine", "bannerTitle", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OfflineHeaderPolicy {
    public static final int $stable = 0;
    public static final OfflineHeaderPolicy INSTANCE = new OfflineHeaderPolicy();

    private OfflineHeaderPolicy() {
    }

    public final boolean showConnectionChip(boolean online) {
        return false;
    }

    public final String connectionChipLabel(boolean online) {
        return online ? "Live" : "Offline";
    }

    public final String hostSubtitle(String host, boolean online, String statusLine) {
        Intrinsics.checkNotNullParameter(host, "host");
        String custom = null;
        if (statusLine != null && !StringsKt.isBlank(statusLine)) {
            custom = statusLine;
        }
        return custom != null ? custom : host;
    }

    public final String bannerTitle() {
        return "Offline";
    }
}
