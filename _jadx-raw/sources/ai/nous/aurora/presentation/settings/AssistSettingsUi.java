package ai.nous.aurora.presentation.settings;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssistantSettingsPane.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lai/nous/aurora/presentation/settings/AssistSettingsUi;", "", "speakReplies", "", "autoListen", "orbStyle", "", "<init>", "(ZZLjava/lang/String;)V", "getSpeakReplies", "()Z", "getAutoListen", "getOrbStyle", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AssistSettingsUi {
    public static final int $stable = 0;
    private final boolean autoListen;
    private final String orbStyle;
    private final boolean speakReplies;

    public AssistSettingsUi() {
        this(false, false, null, 7, null);
    }

    public static /* synthetic */ AssistSettingsUi copy$default(AssistSettingsUi assistSettingsUi, boolean z, boolean z2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = assistSettingsUi.speakReplies;
        }
        if ((i & 2) != 0) {
            z2 = assistSettingsUi.autoListen;
        }
        if ((i & 4) != 0) {
            str = assistSettingsUi.orbStyle;
        }
        return assistSettingsUi.copy(z, z2, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getSpeakReplies() {
        return this.speakReplies;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getAutoListen() {
        return this.autoListen;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOrbStyle() {
        return this.orbStyle;
    }

    public final AssistSettingsUi copy(boolean speakReplies, boolean autoListen, String orbStyle) {
        Intrinsics.checkNotNullParameter(orbStyle, "orbStyle");
        return new AssistSettingsUi(speakReplies, autoListen, orbStyle);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssistSettingsUi)) {
            return false;
        }
        AssistSettingsUi assistSettingsUi = (AssistSettingsUi) other;
        return this.speakReplies == assistSettingsUi.speakReplies && this.autoListen == assistSettingsUi.autoListen && Intrinsics.areEqual(this.orbStyle, assistSettingsUi.orbStyle);
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.speakReplies) * 31) + Boolean.hashCode(this.autoListen)) * 31) + this.orbStyle.hashCode();
    }

    public String toString() {
        return "AssistSettingsUi(speakReplies=" + this.speakReplies + ", autoListen=" + this.autoListen + ", orbStyle=" + this.orbStyle + ")";
    }

    public AssistSettingsUi(boolean speakReplies, boolean autoListen, String orbStyle) {
        Intrinsics.checkNotNullParameter(orbStyle, "orbStyle");
        this.speakReplies = speakReplies;
        this.autoListen = autoListen;
        this.orbStyle = orbStyle;
    }

    public /* synthetic */ AssistSettingsUi(boolean z, boolean z2, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? "amber" : str);
    }

    public final boolean getSpeakReplies() {
        return this.speakReplies;
    }

    public final boolean getAutoListen() {
        return this.autoListen;
    }

    public final String getOrbStyle() {
        return this.orbStyle;
    }
}
