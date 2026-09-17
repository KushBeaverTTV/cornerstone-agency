package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.ConfigCategory;
import ai.nous.aurora.data.remote.ConfigFieldMeta;
import ai.nous.aurora.data.repository.MoaSnapshot;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: SettingsScreens.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bñ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001d\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003¢\u0006\u0004\b\u001f\u0010 J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u0015\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u0015\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\u000bHÆ\u0003J\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0011HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0018HÆ\u0003J\t\u0010I\u001a\u00020\u0003HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\u001dHÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003Jó\u0001\u0010N\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u0003HÆ\u0001J\u0013\u0010O\u001a\u00020\u00032\b\u0010P\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010Q\u001a\u00020RHÖ\u0001J\t\u0010S\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\"R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\u000b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010$R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010$R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010$R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010$R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010$R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\"R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010$R\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\"R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u001e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\"¨\u0006T"}, d2 = {"Lai/nous/aurora/presentation/settings/SettingsUiModel;", "", "loading", "", "error", "", "bridgeOnline", "categories", "", "Lai/nous/aurora/data/remote/ConfigCategory;", "fields", "", "Lai/nous/aurora/data/remote/ConfigFieldMeta;", "config", "Lkotlinx/serialization/json/JsonElement;", "search", "pane", "Lai/nous/aurora/presentation/settings/SettingsPane;", "essentialsSection", "selectedCategory", "savingPath", "modelLabel", "lastSavedPath", "moa", "Lai/nous/aurora/data/repository/MoaSnapshot;", "moaSaving", "moaError", "moaLastSaved", "assist", "Lai/nous/aurora/presentation/settings/AssistSettingsUi;", "moaDirty", "<init>", "(ZLjava/lang/String;ZLjava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Lai/nous/aurora/presentation/settings/SettingsPane;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lai/nous/aurora/data/repository/MoaSnapshot;ZLjava/lang/String;ZLai/nous/aurora/presentation/settings/AssistSettingsUi;Z)V", "getLoading", "()Z", "getError", "()Ljava/lang/String;", "getBridgeOnline", "getCategories", "()Ljava/util/List;", "getFields", "()Ljava/util/Map;", "getConfig", "getSearch", "getPane", "()Lai/nous/aurora/presentation/settings/SettingsPane;", "getEssentialsSection", "getSelectedCategory", "getSavingPath", "getModelLabel", "getLastSavedPath", "getMoa", "()Lai/nous/aurora/data/repository/MoaSnapshot;", "getMoaSaving", "getMoaError", "getMoaLastSaved", "getAssist", "()Lai/nous/aurora/presentation/settings/AssistSettingsUi;", "getMoaDirty", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SettingsUiModel {
    public static final int $stable = 8;
    public final AssistSettingsUi assist;
    public final boolean bridgeOnline;
    public final List<ConfigCategory> categories;
    public final Map<String, JsonElement> config;
    public final String error;
    public final String essentialsSection;
    public final Map<String, ConfigFieldMeta> fields;
    public final String lastSavedPath;
    public final boolean loading;
    public final MoaSnapshot moa;
    public final boolean moaDirty;
    public final String moaError;
    public final boolean moaLastSaved;
    public final boolean moaSaving;
    public final String modelLabel;
    public final SettingsPane pane;
    public final String savingPath;
    public final String search;
    public final String selectedCategory;

    public SettingsUiModel() {
        this(false, null, false, null, null, null, null, null, null, null, null, null, null, null, false, null, false, null, false, 524287, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getLoading() {
        return this.loading;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getSelectedCategory() {
        return this.selectedCategory;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getSavingPath() {
        return this.savingPath;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getModelLabel() {
        return this.modelLabel;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getLastSavedPath() {
        return this.lastSavedPath;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final MoaSnapshot getMoa() {
        return this.moa;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getMoaSaving() {
        return this.moaSaving;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getMoaError() {
        return this.moaError;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getMoaLastSaved() {
        return this.moaLastSaved;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final AssistSettingsUi getAssist() {
        return this.assist;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final boolean getMoaDirty() {
        return this.moaDirty;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getBridgeOnline() {
        return this.bridgeOnline;
    }

    public final List<ConfigCategory> component4() {
        return this.categories;
    }

    public final Map<String, ConfigFieldMeta> component5() {
        return this.fields;
    }

    public final Map<String, JsonElement> component6() {
        return this.config;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getSearch() {
        return this.search;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final SettingsPane getPane() {
        return this.pane;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getEssentialsSection() {
        return this.essentialsSection;
    }

    public final SettingsUiModel copy(boolean loading, String error, boolean bridgeOnline, List<ConfigCategory> categories, Map<String, ConfigFieldMeta> fields, Map<String, JsonElement> config, String search, SettingsPane pane, String essentialsSection, String selectedCategory, String savingPath, String modelLabel, String lastSavedPath, MoaSnapshot moa, boolean moaSaving, String moaError, boolean moaLastSaved, AssistSettingsUi assist, boolean moaDirty) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(search, "search");
        Intrinsics.checkNotNullParameter(pane, "pane");
        Intrinsics.checkNotNullParameter(modelLabel, "modelLabel");
        Intrinsics.checkNotNullParameter(assist, "assist");
        return new SettingsUiModel(loading, error, bridgeOnline, categories, fields, config, search, pane, essentialsSection, selectedCategory, savingPath, modelLabel, lastSavedPath, moa, moaSaving, moaError, moaLastSaved, assist, moaDirty);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SettingsUiModel)) {
            return false;
        }
        SettingsUiModel settingsUiModel = (SettingsUiModel) other;
        return this.loading == settingsUiModel.loading && Intrinsics.areEqual(this.error, settingsUiModel.error) && this.bridgeOnline == settingsUiModel.bridgeOnline && Intrinsics.areEqual(this.categories, settingsUiModel.categories) && Intrinsics.areEqual(this.fields, settingsUiModel.fields) && Intrinsics.areEqual(this.config, settingsUiModel.config) && Intrinsics.areEqual(this.search, settingsUiModel.search) && this.pane == settingsUiModel.pane && Intrinsics.areEqual(this.essentialsSection, settingsUiModel.essentialsSection) && Intrinsics.areEqual(this.selectedCategory, settingsUiModel.selectedCategory) && Intrinsics.areEqual(this.savingPath, settingsUiModel.savingPath) && Intrinsics.areEqual(this.modelLabel, settingsUiModel.modelLabel) && Intrinsics.areEqual(this.lastSavedPath, settingsUiModel.lastSavedPath) && Intrinsics.areEqual(this.moa, settingsUiModel.moa) && this.moaSaving == settingsUiModel.moaSaving && Intrinsics.areEqual(this.moaError, settingsUiModel.moaError) && this.moaLastSaved == settingsUiModel.moaLastSaved && Intrinsics.areEqual(this.assist, settingsUiModel.assist) && this.moaDirty == settingsUiModel.moaDirty;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((Boolean.hashCode(this.loading) * 31) + (this.error == null ? 0 : this.error.hashCode())) * 31) + Boolean.hashCode(this.bridgeOnline)) * 31) + this.categories.hashCode()) * 31) + this.fields.hashCode()) * 31) + this.config.hashCode()) * 31) + this.search.hashCode()) * 31) + this.pane.hashCode()) * 31) + (this.essentialsSection == null ? 0 : this.essentialsSection.hashCode())) * 31) + (this.selectedCategory == null ? 0 : this.selectedCategory.hashCode())) * 31) + (this.savingPath == null ? 0 : this.savingPath.hashCode())) * 31) + this.modelLabel.hashCode()) * 31) + (this.lastSavedPath == null ? 0 : this.lastSavedPath.hashCode())) * 31) + (this.moa == null ? 0 : this.moa.hashCode())) * 31) + Boolean.hashCode(this.moaSaving)) * 31) + (this.moaError != null ? this.moaError.hashCode() : 0)) * 31) + Boolean.hashCode(this.moaLastSaved)) * 31) + this.assist.hashCode()) * 31) + Boolean.hashCode(this.moaDirty);
    }

    public String toString() {
        return "SettingsUiModel(loading=" + this.loading + ", error=" + this.error + ", bridgeOnline=" + this.bridgeOnline + ", categories=" + this.categories + ", fields=" + this.fields + ", config=" + this.config + ", search=" + this.search + ", pane=" + this.pane + ", essentialsSection=" + this.essentialsSection + ", selectedCategory=" + this.selectedCategory + ", savingPath=" + this.savingPath + ", modelLabel=" + this.modelLabel + ", lastSavedPath=" + this.lastSavedPath + ", moa=" + this.moa + ", moaSaving=" + this.moaSaving + ", moaError=" + this.moaError + ", moaLastSaved=" + this.moaLastSaved + ", assist=" + this.assist + ", moaDirty=" + this.moaDirty + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SettingsUiModel(boolean loading, String error, boolean bridgeOnline, List<ConfigCategory> categories, Map<String, ConfigFieldMeta> fields, Map<String, JsonElement> config, String search, SettingsPane pane, String essentialsSection, String selectedCategory, String savingPath, String modelLabel, String lastSavedPath, MoaSnapshot moa, boolean moaSaving, String moaError, boolean moaLastSaved, AssistSettingsUi assist, boolean moaDirty) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(search, "search");
        Intrinsics.checkNotNullParameter(pane, "pane");
        Intrinsics.checkNotNullParameter(modelLabel, "modelLabel");
        Intrinsics.checkNotNullParameter(assist, "assist");
        this.loading = loading;
        this.error = error;
        this.bridgeOnline = bridgeOnline;
        this.categories = categories;
        this.fields = fields;
        this.config = config;
        this.search = search;
        this.pane = pane;
        this.essentialsSection = essentialsSection;
        this.selectedCategory = selectedCategory;
        this.savingPath = savingPath;
        this.modelLabel = modelLabel;
        this.lastSavedPath = lastSavedPath;
        this.moa = moa;
        this.moaSaving = moaSaving;
        this.moaError = moaError;
        this.moaLastSaved = moaLastSaved;
        this.assist = assist;
        this.moaDirty = moaDirty;
    }

    public /* synthetic */ SettingsUiModel(boolean z, String str, boolean z2, List list, Map map, Map map2, String str2, SettingsPane settingsPane, String str3, String str4, String str5, String str6, String str7, MoaSnapshot moaSnapshot, boolean z3, String str8, boolean z4, AssistSettingsUi assistSettingsUi, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) != 0 ? MapsKt.emptyMap() : map2, (i & 64) != 0 ? "" : str2, (i & 128) != 0 ? SettingsPane.Home : settingsPane, (i & 256) != 0 ? null : str3, (i & 512) != 0 ? null : str4, (i & 1024) != 0 ? null : str5, (i & 2048) == 0 ? str6 : "", (i & 4096) != 0 ? null : str7, (i & 8192) != 0 ? null : moaSnapshot, (i & 16384) != 0 ? false : z3, (i & 32768) != 0 ? null : str8, (i & 65536) != 0 ? false : z4, (i & 131072) != 0 ? new AssistSettingsUi(false, false, null, 7, null) : assistSettingsUi, (i & 262144) != 0 ? false : z5);
    }





    public final List<ConfigCategory> getCategories() {
        return this.categories;
    }

    public final Map<String, ConfigFieldMeta> getFields() {
        return this.fields;
    }

    public final Map<String, JsonElement> getConfig() {
        return this.config;
    }




}
