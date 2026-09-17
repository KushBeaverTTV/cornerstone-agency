package ai.nous.aurora.domain.model;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoverShellPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tJ\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t¨\u0006\u000e"}, d2 = {"Lai/nous/aurora/domain/model/CoverShellPolicy;", "", "<init>", "()V", "latestSession", "Lai/nous/aurora/domain/model/AuroraSession;", "sessions", "", "canOpenLatest", "", "hasLatest", "canTalk", "online", "canStartChat", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CoverShellPolicy {
    public static final int $stable = 0;
    public static final CoverShellPolicy INSTANCE = new CoverShellPolicy();

    private CoverShellPolicy() {
    }

    public final AuroraSession latestSession(List<AuroraSession> sessions) {
        Object maxElem$iv;
        Intrinsics.checkNotNullParameter(sessions, "sessions");
        List<AuroraSession> $this$maxByOrNull$iv = sessions;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                AuroraSession it = (AuroraSession) maxElem$iv;
                long maxValue$iv = it.getLastActiveMs();
                do {
                    Object e$iv = iterator$iv.next();
                    AuroraSession it2 = (AuroraSession) e$iv;
                    long v$iv = it2.getLastActiveMs();
                    if (maxValue$iv < v$iv) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = null;
        }
        return (AuroraSession) maxElem$iv;
    }

    public final boolean canOpenLatest(boolean hasLatest) {
        return hasLatest;
    }

    public final boolean canTalk(boolean online) {
        return online;
    }

    public final boolean canStartChat(boolean online) {
        return online;
    }
}
