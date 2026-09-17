package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PairingKeyStatusPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0016\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005¨\u0006\u0011"}, d2 = {"Lai/nous/aurora/domain/model/PairingKeyStatusPolicy;", "", "<init>", "()V", "keyStatusLabel", "", "savedKey", "enteredKey", "connectEnabled", "", "busy", "connectionDetailsLabel", "address", "savedCredentialLabel", "hasCredential", "credentialTail", "key", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PairingKeyStatusPolicy {
    public static final int $stable = 0;
    public static final PairingKeyStatusPolicy INSTANCE = new PairingKeyStatusPolicy();

    private PairingKeyStatusPolicy() {
    }

    public final String keyStatusLabel(String savedKey, String enteredKey) {
        Intrinsics.checkNotNullParameter(savedKey, "savedKey");
        Intrinsics.checkNotNullParameter(enteredKey, "enteredKey");
        return hasCredential(savedKey, enteredKey) ? "Ready" : "Required";
    }

    public final boolean connectEnabled(boolean busy, String savedKey, String enteredKey) {
        Intrinsics.checkNotNullParameter(savedKey, "savedKey");
        Intrinsics.checkNotNullParameter(enteredKey, "enteredKey");
        return !busy && hasCredential(savedKey, enteredKey);
    }

    public final String connectionDetailsLabel(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        return "Connection details";
    }

    public final String savedCredentialLabel(String savedKey) {
        Intrinsics.checkNotNullParameter(savedKey, "savedKey");
        return !StringsKt.isBlank(savedKey) ? "Saved securely on this device" : "No saved key on this device";
    }

    public final boolean hasCredential(String savedKey, String enteredKey) {
        Intrinsics.checkNotNullParameter(savedKey, "savedKey");
        Intrinsics.checkNotNullParameter(enteredKey, "enteredKey");
        return (StringsKt.isBlank(savedKey) && StringsKt.isBlank(enteredKey)) ? false : true;
    }

    public final String credentialTail(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        String normalized = StringsKt.trim((CharSequence) key).toString();
        return normalized.length() == 0 ? "" : StringsKt.takeLast(normalized, 6);
    }
}
