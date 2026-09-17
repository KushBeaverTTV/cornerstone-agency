package ai.nous.aurora.presentation.assist;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: AssistOrb.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lai/nous/aurora/presentation/assist/AssistOrbPhase;", "", "<init>", "(Ljava/lang/String;I)V", "Idle", "Listening", "Working", "Done", "Error", "NeedMic", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public enum AssistOrbPhase {
    Idle,
    Listening,
    Working,
    Done,
    Error,
    NeedMic;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<AssistOrbPhase> getEntries() {
        return $ENTRIES;
    }
}
