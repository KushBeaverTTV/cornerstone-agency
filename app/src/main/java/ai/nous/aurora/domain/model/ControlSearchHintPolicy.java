package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ControlSearchHintPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lai/nous/aurora/domain/model/ControlSearchHintPolicy;", "", "<init>", "()V", "MIN_QUERY_LEN", "", "hintText", "", "shouldShowShortQueryHint", "", "raw", "shouldShowHomeDirectory", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ControlSearchHintPolicy {
    public static final int $stable = 0;
    public static final ControlSearchHintPolicy INSTANCE = new ControlSearchHintPolicy();
    public static final int MIN_QUERY_LEN = 2;

    private ControlSearchHintPolicy() {
    }

    public final String hintText() {
        return "Type 2+ characters to search";
    }

    public final boolean shouldShowShortQueryHint(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String q = StringsKt.trim((CharSequence) raw).toString();
        return (q.length() > 0) && q.length() < 2;
    }

    public final boolean shouldShowHomeDirectory(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String q = StringsKt.trim((CharSequence) raw).toString();
        return q.length() < 2;
    }
}
