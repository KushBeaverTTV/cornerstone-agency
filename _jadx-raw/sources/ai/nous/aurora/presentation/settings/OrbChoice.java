package ai.nous.aurora.presentation.settings;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssistantSettingsPane.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0012\u0010\rJ.\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u00020\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u001c"}, d2 = {"Lai/nous/aurora/presentation/settings/OrbChoice;", "", "id", "", "label", "color", "Landroidx/compose/ui/graphics/Color;", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getLabel", "getColor-0d7_KjU", "()J", "J", "component1", "component2", "component3", "component3-0d7_KjU", "copy", "copy-mxwnekA", "(Ljava/lang/String;Ljava/lang/String;J)Lai/nous/aurora/presentation/settings/OrbChoice;", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class OrbChoice {
    private final long color;
    private final String id;
    private final String label;

    public /* synthetic */ OrbChoice(String str, String str2, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, j);
    }

    /* JADX INFO: renamed from: copy-mxwnekA$default, reason: not valid java name */
    public static /* synthetic */ OrbChoice m163copymxwnekA$default(OrbChoice orbChoice, String str, String str2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = orbChoice.id;
        }
        if ((i & 2) != 0) {
            str2 = orbChoice.label;
        }
        if ((i & 4) != 0) {
            j = orbChoice.color;
        }
        return orbChoice.m165copymxwnekA(str, str2, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3-0d7_KjU, reason: not valid java name and from getter */
    public final long getColor() {
        return this.color;
    }

    /* JADX INFO: renamed from: copy-mxwnekA, reason: not valid java name */
    public final OrbChoice m165copymxwnekA(String id, String label, long color) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(label, "label");
        return new OrbChoice(id, label, color, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrbChoice)) {
            return false;
        }
        OrbChoice orbChoice = (OrbChoice) other;
        return Intrinsics.areEqual(this.id, orbChoice.id) && Intrinsics.areEqual(this.label, orbChoice.label) && Color.m4535equalsimpl0(this.color, orbChoice.color);
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.label.hashCode()) * 31) + Color.m4541hashCodeimpl(this.color);
    }

    public String toString() {
        return "OrbChoice(id=" + this.id + ", label=" + this.label + ", color=" + Color.m4542toStringimpl(this.color) + ")";
    }

    private OrbChoice(String id, String label, long color) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(label, "label");
        this.id = id;
        this.label = label;
        this.color = color;
    }

    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name */
    public final long m166getColor0d7_KjU() {
        return this.color;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }
}
