package ai.nous.aurora.data.repository;

import androidx.autofill.HintConstants;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003JK\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0015¨\u0006#"}, d2 = {"Lai/nous/aurora/data/repository/HermesSettingField;", "", "path", "", "type", "description", "category", "options", "", "schemaSecret", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Z)V", "getPath", "()Ljava/lang/String;", "getType", "getDescription", "getCategory", "getOptions", "()Ljava/util/List;", "getSchemaSecret", "()Z", "isSecret", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class HermesSettingField {
    public static final int $stable = 8;
    private final String category;
    private final String description;
    private final List<String> options;
    private final String path;
    private final boolean schemaSecret;
    private final String type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HermesSettingField copy$default(HermesSettingField hermesSettingField, String str, String str2, String str3, String str4, List list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hermesSettingField.path;
        }
        if ((i & 2) != 0) {
            str2 = hermesSettingField.type;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = hermesSettingField.description;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = hermesSettingField.category;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            list = hermesSettingField.options;
        }
        List list2 = list;
        if ((i & 32) != 0) {
            z = hermesSettingField.schemaSecret;
        }
        return hermesSettingField.copy(str, str5, str6, str7, list2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCategory() {
        return this.category;
    }

    public final List<String> component5() {
        return this.options;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getSchemaSecret() {
        return this.schemaSecret;
    }

    public final HermesSettingField copy(String path, String type, String description, String category, List<String> options, boolean schemaSecret) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(options, "options");
        return new HermesSettingField(path, type, description, category, options, schemaSecret);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HermesSettingField)) {
            return false;
        }
        HermesSettingField hermesSettingField = (HermesSettingField) other;
        return Intrinsics.areEqual(this.path, hermesSettingField.path) && Intrinsics.areEqual(this.type, hermesSettingField.type) && Intrinsics.areEqual(this.description, hermesSettingField.description) && Intrinsics.areEqual(this.category, hermesSettingField.category) && Intrinsics.areEqual(this.options, hermesSettingField.options) && this.schemaSecret == hermesSettingField.schemaSecret;
    }

    public int hashCode() {
        return (((((((((this.path.hashCode() * 31) + this.type.hashCode()) * 31) + this.description.hashCode()) * 31) + this.category.hashCode()) * 31) + this.options.hashCode()) * 31) + Boolean.hashCode(this.schemaSecret);
    }

    public String toString() {
        return "HermesSettingField(path=" + this.path + ", type=" + this.type + ", description=" + this.description + ", category=" + this.category + ", options=" + this.options + ", schemaSecret=" + this.schemaSecret + ")";
    }

    public HermesSettingField(String path, String type, String description, String category, List<String> options, boolean schemaSecret) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(options, "options");
        this.path = path;
        this.type = type;
        this.description = description;
        this.category = category;
        this.options = options;
        this.schemaSecret = schemaSecret;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ HermesSettingField(String str, String str2, String str3, String str4, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, list, (i & 32) == 0 ? z : false);
    }









    public final List<String> getOptions() {
        return this.options;
    }



    public final boolean isSecret() {
        boolean z;
        if (this.schemaSecret) {
            return true;
        }
        String value = this.path.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(value, "toLowerCase(...)");
        Iterable $this$any$iv = CollectionsKt.listOf(new String[]{"api_key", "apikey", "token", HintConstants.AUTOFILL_HINT_PASSWORD, "secret", "credential", "private_key", "identity"});
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            z = false;
        } else {
            for (Object element$iv : $this$any$iv) {
                CharSequence p0 = (CharSequence) element$iv;
                if (ai.nous.aurora.compat.Compat.d_StringsKt_contains_5((CharSequence) value, p0, false, 2, (Object) null)) {
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }
}
