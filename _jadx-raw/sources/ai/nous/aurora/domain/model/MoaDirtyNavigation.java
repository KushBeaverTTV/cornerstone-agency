package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MoaDirtyNavigation.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002\u000e\u000fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u001e\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005¨\u0006\u0010"}, d2 = {"Lai/nous/aurora/domain/model/MoaDirtyNavigation;", "", "<init>", "()V", "allowLeaveSettingsTab", "", "moaDirty", "pane", "Lai/nous/aurora/domain/model/MoaDirtyNavigation$SettingsPane;", "allowResetSettingsToHome", "shouldConfirmDiscard", "confirmArmed", "canLeaveMoaEditor", "userConfirmedDiscard", "Tab", "SettingsPane", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MoaDirtyNavigation {
    public static final int $stable = 0;
    public static final MoaDirtyNavigation INSTANCE = new MoaDirtyNavigation();

    /* JADX INFO: compiled from: MoaDirtyNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/domain/model/MoaDirtyNavigation$Tab;", "", "<init>", "(Ljava/lang/String;I)V", "Chats", "Terminal", "Settings", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Tab {
        Chats,
        Terminal,
        Settings;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Tab> getEntries() {
            return $ENTRIES;
        }
    }

    private MoaDirtyNavigation() {
    }

    /* JADX INFO: compiled from: MoaDirtyNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lai/nous/aurora/domain/model/MoaDirtyNavigation$SettingsPane;", "", "<init>", "(Ljava/lang/String;I)V", "Home", "Essentials", "Advanced", "MoaEditor", "Account", "Assistant", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum SettingsPane {
        Home,
        Essentials,
        Advanced,
        MoaEditor,
        Account,
        Assistant;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<SettingsPane> getEntries() {
            return $ENTRIES;
        }
    }

    public final boolean allowLeaveSettingsTab(boolean moaDirty, SettingsPane pane) {
        Intrinsics.checkNotNullParameter(pane, "pane");
        return (moaDirty && pane == SettingsPane.MoaEditor) ? false : true;
    }

    public final boolean allowResetSettingsToHome(boolean moaDirty, SettingsPane pane) {
        Intrinsics.checkNotNullParameter(pane, "pane");
        return (moaDirty && pane == SettingsPane.MoaEditor) ? false : true;
    }

    public final boolean shouldConfirmDiscard(boolean moaDirty, boolean confirmArmed) {
        return moaDirty && !confirmArmed;
    }

    public final boolean canLeaveMoaEditor(boolean moaDirty, boolean confirmArmed, boolean userConfirmedDiscard) {
        if (!moaDirty || userConfirmedDiscard) {
            return true;
        }
        return false;
    }
}
