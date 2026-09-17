package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PreconnectCredentialPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lai/nous/aurora/domain/model/PreconnectCredentialPolicy;", "", "<init>", "()V", "effectiveKey", "", "storedKey", "optedOut", "", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PreconnectCredentialPolicy {
    public static final int $stable = 0;
    public static final PreconnectCredentialPolicy INSTANCE = new PreconnectCredentialPolicy();

    private PreconnectCredentialPolicy() {
    }

    public final String effectiveKey(String storedKey, boolean optedOut) {
        if (optedOut || storedKey == null || StringsKt.isBlank(storedKey)) {
            return null;
        }
        return storedKey;
    }
}
