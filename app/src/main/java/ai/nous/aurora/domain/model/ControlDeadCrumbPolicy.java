package ai.nous.aurora.domain.model;

import kotlin.Metadata;

/* JADX INFO: compiled from: ControlDeadCrumbPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u0007\u001a\u00020\u0005J\u0006\u0010\b\u001a\u00020\u0005¨\u0006\t"}, d2 = {"Lai/nous/aurora/domain/model/ControlDeadCrumbPolicy;", "", "<init>", "()V", "showSessionsRowOnControlHome", "", "showGatewayRowOnControlHome", "showAccountRowOnControlHome", "showAdvancedRowOnControlHome", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ControlDeadCrumbPolicy {
    public static final int $stable = 0;
    public static final ControlDeadCrumbPolicy INSTANCE = new ControlDeadCrumbPolicy();

    private ControlDeadCrumbPolicy() {
    }

    public final boolean showSessionsRowOnControlHome() {
        return false;
    }

    public final boolean showGatewayRowOnControlHome() {
        return true;
    }

    public final boolean showAccountRowOnControlHome() {
        return true;
    }

    public final boolean showAdvancedRowOnControlHome() {
        return true;
    }
}
