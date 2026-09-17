package ai.nous.aurora.data.repository;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: SettingsDataContracts.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lai/nous/aurora/data/repository/SettingsLastGoodPayload;", "", "schemaSource", "", "values", "", "Lkotlinx/serialization/json/JsonElement;", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getSchemaSource", "()Ljava/lang/String;", "getValues", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SettingsLastGoodPayload {
    public static final int $stable = 8;
    private final String schemaSource;
    private final Map<String, JsonElement> values;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SettingsLastGoodPayload copy$default(SettingsLastGoodPayload settingsLastGoodPayload, String str, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = settingsLastGoodPayload.schemaSource;
        }
        if ((i & 2) != 0) {
            map = settingsLastGoodPayload.values;
        }
        return settingsLastGoodPayload.copy(str, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSchemaSource() {
        return this.schemaSource;
    }

    public final Map<String, JsonElement> component2() {
        return this.values;
    }

    public final SettingsLastGoodPayload copy(String schemaSource, Map<String, ? extends JsonElement> values) {
        Intrinsics.checkNotNullParameter(schemaSource, "schemaSource");
        Intrinsics.checkNotNullParameter(values, "values");
        return new SettingsLastGoodPayload(schemaSource, values);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SettingsLastGoodPayload)) {
            return false;
        }
        SettingsLastGoodPayload settingsLastGoodPayload = (SettingsLastGoodPayload) other;
        return Intrinsics.areEqual(this.schemaSource, settingsLastGoodPayload.schemaSource) && Intrinsics.areEqual(this.values, settingsLastGoodPayload.values);
    }

    public int hashCode() {
        return (this.schemaSource.hashCode() * 31) + this.values.hashCode();
    }

    public String toString() {
        return "SettingsLastGoodPayload(schemaSource=" + this.schemaSource + ", values=" + this.values + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SettingsLastGoodPayload(String schemaSource, Map<String, ? extends JsonElement> values) {
        Intrinsics.checkNotNullParameter(schemaSource, "schemaSource");
        Intrinsics.checkNotNullParameter(values, "values");
        this.schemaSource = schemaSource;
        this.values = values;
    }

    public final String getSchemaSource() {
        return this.schemaSource;
    }

    public final Map<String, JsonElement> getValues() {
        return this.values;
    }
}
