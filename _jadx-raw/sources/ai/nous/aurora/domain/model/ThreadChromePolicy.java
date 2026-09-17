package ai.nous.aurora.domain.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ThreadChromePolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u001e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u0016\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005J&\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011J\u001e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0016J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cJ\u0018\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\u001cR\u000e\u0010\u0015\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lai/nous/aurora/domain/model/ThreadChromePolicy;", "", "<init>", "()V", "showShellBrandHeader", "", "inThread", "activeTabIsTerminal", "showHostUnderTitle", OutboxExactlyOnce.STATUS_SENDING, "listening", "offline", "autoScrollEnabled", "enabled", "overrideDisabled", "canAutoScroll", "firstVisible", "", "lastVisible", "total", "threshold", "TYPING_GRACE_MS", "", "showTypingIndicator", "composerHasFocus", "lastEditMs", "nowMs", "formatMeta", "", "source", "model", "showModelLine", "chipModel", "lineModel", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ThreadChromePolicy {
    public static final int $stable = 0;
    public static final ThreadChromePolicy INSTANCE = new ThreadChromePolicy();
    private static final long TYPING_GRACE_MS = 5000;

    private ThreadChromePolicy() {
    }

    public final boolean showShellBrandHeader(boolean inThread, boolean activeTabIsTerminal) {
        if (inThread || activeTabIsTerminal) {
            return false;
        }
        return true;
    }

    public final boolean showHostUnderTitle(boolean sending, boolean listening, boolean offline) {
        return (sending || listening || offline) ? false : true;
    }

    public final boolean autoScrollEnabled(boolean enabled, boolean overrideDisabled) {
        return enabled && !overrideDisabled;
    }

    public final boolean canAutoScroll(int firstVisible, int lastVisible, int total, int threshold) {
        if (total <= 0) {
            return false;
        }
        int remaining = (total - lastVisible) - 1;
        return remaining <= threshold;
    }

    public final boolean showTypingIndicator(boolean composerHasFocus, long lastEditMs, long nowMs) {
        if (!composerHasFocus) {
            return false;
        }
        long elapsed = nowMs - lastEditMs;
        return 0 <= elapsed && elapsed < 5001;
    }

    public final String formatMeta(String source, String model) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(model, "model");
        List $this$formatMeta_u24lambda_u240 = CollectionsKt.createListBuilder();
        if (!StringsKt.isBlank(source)) {
            $this$formatMeta_u24lambda_u240.add(source);
        }
        if (!StringsKt.isBlank(model)) {
            $this$formatMeta_u24lambda_u240.add(model);
        }
        List parts = CollectionsKt.build($this$formatMeta_u24lambda_u240);
        return CollectionsKt.joinToString$default(parts, " · ", null, null, 0, null, null, 62, null);
    }

    public final boolean showModelLine(String chipModel, String lineModel) {
        Intrinsics.checkNotNullParameter(chipModel, "chipModel");
        String str = lineModel;
        if (str == null || StringsKt.isBlank(str)) {
            return true;
        }
        return !Intrinsics.areEqual(lineModel, chipModel);
    }
}
