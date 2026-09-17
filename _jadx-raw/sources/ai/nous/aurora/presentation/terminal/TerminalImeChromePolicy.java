package ai.nous.aurora.presentation.terminal;

import kotlin.Metadata;

/* JADX INFO: compiled from: TerminalImeChromePolicy.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005¨\u0006\u000b"}, d2 = {"Lai/nous/aurora/presentation/terminal/TerminalImeChromePolicy;", "", "<init>", "()V", "showKeyDeck", "", "imeOpen", "showImeDismissStrip", "preferredKeyDeckHeightDp", "", "expandedMoreKeys", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TerminalImeChromePolicy {
    public static final int $stable = 0;
    public static final TerminalImeChromePolicy INSTANCE = new TerminalImeChromePolicy();

    private TerminalImeChromePolicy() {
    }

    public final boolean showKeyDeck(boolean imeOpen) {
        return !imeOpen;
    }

    public final boolean showImeDismissStrip(boolean imeOpen) {
        return imeOpen;
    }

    public final int preferredKeyDeckHeightDp(boolean imeOpen, boolean expandedMoreKeys) {
        if (imeOpen) {
            return 0;
        }
        return expandedMoreKeys ? 100 : 56;
    }
}
