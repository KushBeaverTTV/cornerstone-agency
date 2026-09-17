package ai.nous.aurora.presentation.settings;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsScreens.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JK\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lai/nous/aurora/presentation/settings/EssentialField;", "", "path", "", "label", "type", "options", "", "help", "allowCustom", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Z)V", "getPath", "()Ljava/lang/String;", "getLabel", "getType", "getOptions", "()Ljava/util/List;", "getHelp", "getAllowCustom", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class EssentialField {
    private final boolean allowCustom;
    private final String help;
    private final String label;
    private final List<String> options;
    private final String path;
    private final String type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EssentialField copy$default(EssentialField essentialField, String str, String str2, String str3, List list, String str4, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = essentialField.path;
        }
        if ((i & 2) != 0) {
            str2 = essentialField.label;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = essentialField.type;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            list = essentialField.options;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            str4 = essentialField.help;
        }
        String str7 = str4;
        if ((i & 32) != 0) {
            z = essentialField.allowCustom;
        }
        return essentialField.copy(str, str5, str6, list2, str7, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final List<String> component4() {
        return this.options;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getHelp() {
        return this.help;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getAllowCustom() {
        return this.allowCustom;
    }

    public final EssentialField copy(String path, String label, String type, List<String> options, String help, boolean allowCustom) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(help, "help");
        return new EssentialField(path, label, type, options, help, allowCustom);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EssentialField)) {
            return false;
        }
        EssentialField essentialField = (EssentialField) other;
        return Intrinsics.areEqual(this.path, essentialField.path) && Intrinsics.areEqual(this.label, essentialField.label) && Intrinsics.areEqual(this.type, essentialField.type) && Intrinsics.areEqual(this.options, essentialField.options) && Intrinsics.areEqual(this.help, essentialField.help) && this.allowCustom == essentialField.allowCustom;
    }

    public int hashCode() {
        return (((((((((this.path.hashCode() * 31) + this.label.hashCode()) * 31) + this.type.hashCode()) * 31) + this.options.hashCode()) * 31) + this.help.hashCode()) * 31) + Boolean.hashCode(this.allowCustom);
    }

    public String toString() {
        return "EssentialField(path=" + this.path + ", label=" + this.label + ", type=" + this.type + ", options=" + this.options + ", help=" + this.help + ", allowCustom=" + this.allowCustom + ")";
    }

    public EssentialField(String path, String label, String type, List<String> options, String help, boolean allowCustom) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(help, "help");
        this.path = path;
        this.label = label;
        this.type = type;
        this.options = options;
        this.help = help;
        this.allowCustom = allowCustom;
    }

    public /* synthetic */ EssentialField(String str, String str2, String str3, List list, String str4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? "" : str4, (i & 32) != 0 ? false : z);
    }

    public final String getPath() {
        return this.path;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getType() {
        return this.type;
    }

    public final List<String> getOptions() {
        return this.options;
    }

    public final String getHelp() {
        return this.help;
    }

    public final boolean getAllowCustom() {
        return this.allowCustom;
    }
}
