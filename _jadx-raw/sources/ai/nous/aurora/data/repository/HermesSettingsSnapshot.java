package ai.nous.aurora.data.repository;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bk\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u0015\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010!\u001a\u00020\u000bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000fHÆ\u0003Jm\u0010%\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001J\u0013\u0010&\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006+"}, d2 = {"Lai/nous/aurora/data/repository/HermesSettingsSnapshot;", "", "fields", "", "Lai/nous/aurora/data/repository/HermesSettingField;", "categoryOrder", "", "values", "", "Lkotlinx/serialization/json/JsonElement;", "remoteAvailable", "", "error", "schemaSource", "moa", "Lai/nous/aurora/data/repository/MoaSnapshot;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/Map;ZLjava/lang/String;Ljava/lang/String;Lai/nous/aurora/data/repository/MoaSnapshot;)V", "getFields", "()Ljava/util/List;", "getCategoryOrder", "getValues", "()Ljava/util/Map;", "getRemoteAvailable", "()Z", "getError", "()Ljava/lang/String;", "getSchemaSource", "getMoa", "()Lai/nous/aurora/data/repository/MoaSnapshot;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class HermesSettingsSnapshot {
    public static final int $stable = 8;
    private final List<String> categoryOrder;
    private final String error;
    private final List<HermesSettingField> fields;
    private final MoaSnapshot moa;
    private final boolean remoteAvailable;
    private final String schemaSource;
    private final Map<String, JsonElement> values;

    public HermesSettingsSnapshot() {
        this(null, null, null, false, null, null, null, 127, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HermesSettingsSnapshot copy$default(HermesSettingsSnapshot hermesSettingsSnapshot, List list, List list2, Map map, boolean z, String str, String str2, MoaSnapshot moaSnapshot, int i, Object obj) {
        if ((i & 1) != 0) {
            list = hermesSettingsSnapshot.fields;
        }
        if ((i & 2) != 0) {
            list2 = hermesSettingsSnapshot.categoryOrder;
        }
        List list3 = list2;
        if ((i & 4) != 0) {
            map = hermesSettingsSnapshot.values;
        }
        Map map2 = map;
        if ((i & 8) != 0) {
            z = hermesSettingsSnapshot.remoteAvailable;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            str = hermesSettingsSnapshot.error;
        }
        String str3 = str;
        if ((i & 32) != 0) {
            str2 = hermesSettingsSnapshot.schemaSource;
        }
        String str4 = str2;
        if ((i & 64) != 0) {
            moaSnapshot = hermesSettingsSnapshot.moa;
        }
        return hermesSettingsSnapshot.copy(list, list3, map2, z2, str3, str4, moaSnapshot);
    }

    public final List<HermesSettingField> component1() {
        return this.fields;
    }

    public final List<String> component2() {
        return this.categoryOrder;
    }

    public final Map<String, JsonElement> component3() {
        return this.values;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getRemoteAvailable() {
        return this.remoteAvailable;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSchemaSource() {
        return this.schemaSource;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final MoaSnapshot getMoa() {
        return this.moa;
    }

    public final HermesSettingsSnapshot copy(List<HermesSettingField> fields, List<String> categoryOrder, Map<String, ? extends JsonElement> values, boolean remoteAvailable, String error, String schemaSource, MoaSnapshot moa) {
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(categoryOrder, "categoryOrder");
        Intrinsics.checkNotNullParameter(values, "values");
        return new HermesSettingsSnapshot(fields, categoryOrder, values, remoteAvailable, error, schemaSource, moa);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HermesSettingsSnapshot)) {
            return false;
        }
        HermesSettingsSnapshot hermesSettingsSnapshot = (HermesSettingsSnapshot) other;
        return Intrinsics.areEqual(this.fields, hermesSettingsSnapshot.fields) && Intrinsics.areEqual(this.categoryOrder, hermesSettingsSnapshot.categoryOrder) && Intrinsics.areEqual(this.values, hermesSettingsSnapshot.values) && this.remoteAvailable == hermesSettingsSnapshot.remoteAvailable && Intrinsics.areEqual(this.error, hermesSettingsSnapshot.error) && Intrinsics.areEqual(this.schemaSource, hermesSettingsSnapshot.schemaSource) && Intrinsics.areEqual(this.moa, hermesSettingsSnapshot.moa);
    }

    public int hashCode() {
        return (((((((((((this.fields.hashCode() * 31) + this.categoryOrder.hashCode()) * 31) + this.values.hashCode()) * 31) + Boolean.hashCode(this.remoteAvailable)) * 31) + (this.error == null ? 0 : this.error.hashCode())) * 31) + (this.schemaSource == null ? 0 : this.schemaSource.hashCode())) * 31) + (this.moa != null ? this.moa.hashCode() : 0);
    }

    public String toString() {
        return "HermesSettingsSnapshot(fields=" + this.fields + ", categoryOrder=" + this.categoryOrder + ", values=" + this.values + ", remoteAvailable=" + this.remoteAvailable + ", error=" + this.error + ", schemaSource=" + this.schemaSource + ", moa=" + this.moa + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HermesSettingsSnapshot(List<HermesSettingField> fields, List<String> categoryOrder, Map<String, ? extends JsonElement> values, boolean remoteAvailable, String error, String schemaSource, MoaSnapshot moa) {
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(categoryOrder, "categoryOrder");
        Intrinsics.checkNotNullParameter(values, "values");
        this.fields = fields;
        this.categoryOrder = categoryOrder;
        this.values = values;
        this.remoteAvailable = remoteAvailable;
        this.error = error;
        this.schemaSource = schemaSource;
        this.moa = moa;
    }

    public /* synthetic */ HermesSettingsSnapshot(List list, List list2, Map map, boolean z, String str, String str2, MoaSnapshot moaSnapshot, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? MapsKt.emptyMap() : map, (i & 8) != 0 ? false : z, (i & 16) != 0 ? null : str, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : moaSnapshot);
    }

    public final List<HermesSettingField> getFields() {
        return this.fields;
    }

    public final List<String> getCategoryOrder() {
        return this.categoryOrder;
    }

    public final Map<String, JsonElement> getValues() {
        return this.values;
    }

    public final boolean getRemoteAvailable() {
        return this.remoteAvailable;
    }

    public final String getError() {
        return this.error;
    }

    public final String getSchemaSource() {
        return this.schemaSource;
    }

    public final MoaSnapshot getMoa() {
        return this.moa;
    }
}
