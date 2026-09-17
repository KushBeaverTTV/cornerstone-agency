package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.MoaModelSlotDto;
import ai.nous.aurora.data.remote.MoaPresetDto;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u000f\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u0015\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J]\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010%\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u001aHÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006)"}, d2 = {"Lai/nous/aurora/data/repository/MoaSnapshot;", "", "defaultPreset", "", "activePreset", "saveTraces", "", "traceDir", "presets", "", "Lai/nous/aurora/data/remote/MoaPresetDto;", "remoteAvailable", "error", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/util/Map;ZLjava/lang/String;)V", "getDefaultPreset", "()Ljava/lang/String;", "getActivePreset", "getSaveTraces", "()Z", "getTraceDir", "getPresets", "()Ljava/util/Map;", "getRemoteAvailable", "getError", "defaultReferenceCount", "", "getDefaultReferenceCount", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class MoaSnapshot {
    public static final int $stable = 8;
    private final String activePreset;
    private final String defaultPreset;
    private final String error;
    private final Map<String, MoaPresetDto> presets;
    private final boolean remoteAvailable;
    private final boolean saveTraces;
    private final String traceDir;

    public MoaSnapshot() {
        this(null, null, false, null, null, false, null, 127, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MoaSnapshot copy$default(MoaSnapshot moaSnapshot, String str, String str2, boolean z, String str3, Map map, boolean z2, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = moaSnapshot.defaultPreset;
        }
        if ((i & 2) != 0) {
            str2 = moaSnapshot.activePreset;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            z = moaSnapshot.saveTraces;
        }
        boolean z3 = z;
        if ((i & 8) != 0) {
            str3 = moaSnapshot.traceDir;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            map = moaSnapshot.presets;
        }
        Map map2 = map;
        if ((i & 32) != 0) {
            z2 = moaSnapshot.remoteAvailable;
        }
        boolean z4 = z2;
        if ((i & 64) != 0) {
            str4 = moaSnapshot.error;
        }
        return moaSnapshot.copy(str, str5, z3, str6, map2, z4, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDefaultPreset() {
        return this.defaultPreset;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getActivePreset() {
        return this.activePreset;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getSaveTraces() {
        return this.saveTraces;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTraceDir() {
        return this.traceDir;
    }

    public final Map<String, MoaPresetDto> component5() {
        return this.presets;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getRemoteAvailable() {
        return this.remoteAvailable;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final MoaSnapshot copy(String defaultPreset, String activePreset, boolean saveTraces, String traceDir, Map<String, MoaPresetDto> presets, boolean remoteAvailable, String error) {
        Intrinsics.checkNotNullParameter(defaultPreset, "defaultPreset");
        Intrinsics.checkNotNullParameter(activePreset, "activePreset");
        Intrinsics.checkNotNullParameter(traceDir, "traceDir");
        Intrinsics.checkNotNullParameter(presets, "presets");
        return new MoaSnapshot(defaultPreset, activePreset, saveTraces, traceDir, presets, remoteAvailable, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoaSnapshot)) {
            return false;
        }
        MoaSnapshot moaSnapshot = (MoaSnapshot) other;
        return Intrinsics.areEqual(this.defaultPreset, moaSnapshot.defaultPreset) && Intrinsics.areEqual(this.activePreset, moaSnapshot.activePreset) && this.saveTraces == moaSnapshot.saveTraces && Intrinsics.areEqual(this.traceDir, moaSnapshot.traceDir) && Intrinsics.areEqual(this.presets, moaSnapshot.presets) && this.remoteAvailable == moaSnapshot.remoteAvailable && Intrinsics.areEqual(this.error, moaSnapshot.error);
    }

    public int hashCode() {
        return (((((((((((this.defaultPreset.hashCode() * 31) + this.activePreset.hashCode()) * 31) + Boolean.hashCode(this.saveTraces)) * 31) + this.traceDir.hashCode()) * 31) + this.presets.hashCode()) * 31) + Boolean.hashCode(this.remoteAvailable)) * 31) + (this.error == null ? 0 : this.error.hashCode());
    }

    public String toString() {
        return "MoaSnapshot(defaultPreset=" + this.defaultPreset + ", activePreset=" + this.activePreset + ", saveTraces=" + this.saveTraces + ", traceDir=" + this.traceDir + ", presets=" + this.presets + ", remoteAvailable=" + this.remoteAvailable + ", error=" + this.error + ")";
    }

    public MoaSnapshot(String defaultPreset, String activePreset, boolean saveTraces, String traceDir, Map<String, MoaPresetDto> presets, boolean remoteAvailable, String error) {
        Intrinsics.checkNotNullParameter(defaultPreset, "defaultPreset");
        Intrinsics.checkNotNullParameter(activePreset, "activePreset");
        Intrinsics.checkNotNullParameter(traceDir, "traceDir");
        Intrinsics.checkNotNullParameter(presets, "presets");
        this.defaultPreset = defaultPreset;
        this.activePreset = activePreset;
        this.saveTraces = saveTraces;
        this.traceDir = traceDir;
        this.presets = presets;
        this.remoteAvailable = remoteAvailable;
        this.error = error;
    }

    public /* synthetic */ MoaSnapshot(String str, String str2, boolean z, String str3, Map map, boolean z2, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "default" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? false : z, (i & 8) == 0 ? str3 : "", (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) == 0 ? z2 : false, (i & 64) != 0 ? null : str4);
    }



    public final Map<String, MoaPresetDto> getPresets() {
        return this.presets;
    }



    public final int getDefaultReferenceCount() {
        List<MoaModelSlotDto> referenceModels;
        List<MoaModelSlotDto> referenceModels2;
        MoaPresetDto moaPresetDto = this.presets.get(this.defaultPreset);
        if (moaPresetDto != null && (referenceModels2 = moaPresetDto.getReferenceModels()) != null) {
            return referenceModels2.size();
        }
        MoaPresetDto moaPresetDto2 = (MoaPresetDto) CollectionsKt.firstOrNull(this.presets.values());
        if (moaPresetDto2 == null || (referenceModels = moaPresetDto2.getReferenceModels()) == null) {
            return 0;
        }
        return referenceModels.size();
    }
}
