package ai.nous.aurora.presentation.terminal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TerminalScreen.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JA\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lai/nous/aurora/presentation/terminal/KeySpec;", "", "label", "", "contentDescription", "selected", "", "danger", "onClick", "Lkotlin/Function0;", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZLkotlin/jvm/functions/Function0;)V", "getLabel", "()Ljava/lang/String;", "getContentDescription", "getSelected", "()Z", "getDanger", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class KeySpec {
    private final String contentDescription;
    private final boolean danger;
    private final String label;
    private final Function0<Unit> onClick;
    private final boolean selected;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KeySpec copy$default(KeySpec keySpec, String str, String str2, boolean z, boolean z2, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = keySpec.label;
        }
        if ((i & 2) != 0) {
            str2 = keySpec.contentDescription;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            z = keySpec.selected;
        }
        boolean z3 = z;
        if ((i & 8) != 0) {
            z2 = keySpec.danger;
        }
        boolean z4 = z2;
        if ((i & 16) != 0) {
            function0 = keySpec.onClick;
        }
        return keySpec.copy(str, str3, z3, z4, function0);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getContentDescription() {
        return this.contentDescription;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getSelected() {
        return this.selected;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getDanger() {
        return this.danger;
    }

    public final Function0<Unit> component5() {
        return this.onClick;
    }

    public final KeySpec copy(String label, String contentDescription, boolean selected, boolean danger, Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        return new KeySpec(label, contentDescription, selected, danger, onClick);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KeySpec)) {
            return false;
        }
        KeySpec keySpec = (KeySpec) other;
        return Intrinsics.areEqual(this.label, keySpec.label) && Intrinsics.areEqual(this.contentDescription, keySpec.contentDescription) && this.selected == keySpec.selected && this.danger == keySpec.danger && Intrinsics.areEqual(this.onClick, keySpec.onClick);
    }

    public int hashCode() {
        return (((((((this.label.hashCode() * 31) + this.contentDescription.hashCode()) * 31) + Boolean.hashCode(this.selected)) * 31) + Boolean.hashCode(this.danger)) * 31) + this.onClick.hashCode();
    }

    public String toString() {
        return "KeySpec(label=" + this.label + ", contentDescription=" + this.contentDescription + ", selected=" + this.selected + ", danger=" + this.danger + ", onClick=" + this.onClick + ")";
    }

    public KeySpec(String label, String contentDescription, boolean selected, boolean danger, Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        this.label = label;
        this.contentDescription = contentDescription;
        this.selected = selected;
        this.danger = danger;
        this.onClick = onClick;
    }

    public /* synthetic */ KeySpec(String str, String str2, boolean z, boolean z2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? str : str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, function0);
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getContentDescription() {
        return this.contentDescription;
    }

    public final boolean getSelected() {
        return this.selected;
    }

    public final boolean getDanger() {
        return this.danger;
    }

    public final Function0<Unit> getOnClick() {
        return this.onClick;
    }
}
