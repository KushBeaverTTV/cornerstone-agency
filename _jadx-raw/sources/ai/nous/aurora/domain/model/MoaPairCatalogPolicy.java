package ai.nous.aurora.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MoaPairCatalogPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001:\u0003\u0017\u0018\u0019B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005J\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\r\u001a\u00020\fJ$\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fJ(\u0010\u0011\u001a\u00020\u00122\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\fJ:\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\r\u001a\u00020\f2\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u0015J$\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f¨\u0006\u001a"}, d2 = {"Lai/nous/aurora/domain/model/MoaPairCatalogPolicy;", "", "<init>", "()V", "livePairs", "", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$LivePair;", "providers", "Lai/nous/aurora/domain/model/ModelProviderCatalog;", "providersWithModels", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$ProviderModels;", "modelsForProvider", "", "provider", "isPairAvailable", "", "model", "resolvePersistedSlot", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution;", "selectableModelsForProvider", "includePersistedIfMissing", "Lkotlin/Pair;", "canPersistSlot", "ProviderModels", "LivePair", "SlotResolution", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MoaPairCatalogPolicy {
    public static final int $stable = 0;
    public static final MoaPairCatalogPolicy INSTANCE = new MoaPairCatalogPolicy();

    private MoaPairCatalogPolicy() {
    }

    /* JADX INFO: compiled from: MoaPairCatalogPolicy.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$ProviderModels;", "", "provider", "", "providerName", "models", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getProvider", "()Ljava/lang/String;", "getProviderName", "getModels", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class ProviderModels {
        public static final int $stable = 8;
        private final List<String> models;
        private final String provider;
        private final String providerName;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ProviderModels copy$default(ProviderModels providerModels, String str, String str2, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = providerModels.provider;
            }
            if ((i & 2) != 0) {
                str2 = providerModels.providerName;
            }
            if ((i & 4) != 0) {
                list = providerModels.models;
            }
            return providerModels.copy(str, str2, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProvider() {
            return this.provider;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getProviderName() {
            return this.providerName;
        }

        public final List<String> component3() {
            return this.models;
        }

        public final ProviderModels copy(String provider, String providerName, List<String> models) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(providerName, "providerName");
            Intrinsics.checkNotNullParameter(models, "models");
            return new ProviderModels(provider, providerName, models);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProviderModels)) {
                return false;
            }
            ProviderModels providerModels = (ProviderModels) other;
            return Intrinsics.areEqual(this.provider, providerModels.provider) && Intrinsics.areEqual(this.providerName, providerModels.providerName) && Intrinsics.areEqual(this.models, providerModels.models);
        }

        public int hashCode() {
            return (((this.provider.hashCode() * 31) + this.providerName.hashCode()) * 31) + this.models.hashCode();
        }

        public String toString() {
            return "ProviderModels(provider=" + this.provider + ", providerName=" + this.providerName + ", models=" + this.models + ")";
        }

        public ProviderModels(String provider, String providerName, List<String> models) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(providerName, "providerName");
            Intrinsics.checkNotNullParameter(models, "models");
            this.provider = provider;
            this.providerName = providerName;
            this.models = models;
        }

        public final String getProvider() {
            return this.provider;
        }

        public final String getProviderName() {
            return this.providerName;
        }

        public final List<String> getModels() {
            return this.models;
        }
    }

    /* JADX INFO: compiled from: MoaPairCatalogPolicy.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\t¨\u0006\u0018"}, d2 = {"Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$LivePair;", "", "provider", "", "providerName", "model", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getProvider", "()Ljava/lang/String;", "getProviderName", "getModel", "key", "getKey", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class LivePair {
        public static final int $stable = 0;
        private final String model;
        private final String provider;
        private final String providerName;

        public static /* synthetic */ LivePair copy$default(LivePair livePair, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = livePair.provider;
            }
            if ((i & 2) != 0) {
                str2 = livePair.providerName;
            }
            if ((i & 4) != 0) {
                str3 = livePair.model;
            }
            return livePair.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProvider() {
            return this.provider;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getProviderName() {
            return this.providerName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getModel() {
            return this.model;
        }

        public final LivePair copy(String provider, String providerName, String model) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(providerName, "providerName");
            Intrinsics.checkNotNullParameter(model, "model");
            return new LivePair(provider, providerName, model);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LivePair)) {
                return false;
            }
            LivePair livePair = (LivePair) other;
            return Intrinsics.areEqual(this.provider, livePair.provider) && Intrinsics.areEqual(this.providerName, livePair.providerName) && Intrinsics.areEqual(this.model, livePair.model);
        }

        public int hashCode() {
            return (((this.provider.hashCode() * 31) + this.providerName.hashCode()) * 31) + this.model.hashCode();
        }

        public String toString() {
            return "LivePair(provider=" + this.provider + ", providerName=" + this.providerName + ", model=" + this.model + ")";
        }

        public LivePair(String provider, String providerName, String model) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(providerName, "providerName");
            Intrinsics.checkNotNullParameter(model, "model");
            this.provider = provider;
            this.providerName = providerName;
            this.model = model;
        }

        public final String getProvider() {
            return this.provider;
        }

        public final String getProviderName() {
            return this.providerName;
        }

        public final String getModel() {
            return this.model;
        }

        public final String getKey() {
            return this.provider + "::" + this.model;
        }
    }

    /* JADX INFO: compiled from: MoaPairCatalogPolicy.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution;", "", "<init>", "()V", "Available", "UnavailablePersisted", "Empty", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution$Available;", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution$Empty;", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution$UnavailablePersisted;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static abstract class SlotResolution {
        public static final int $stable = 0;

        public /* synthetic */ SlotResolution(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: MoaPairCatalogPolicy.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution$Available;", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution;", "pair", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$LivePair;", "<init>", "(Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$LivePair;)V", "getPair", "()Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$LivePair;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Available extends SlotResolution {
            public static final int $stable = 0;
            private final LivePair pair;

            public static /* synthetic */ Available copy$default(Available available, LivePair livePair, int i, Object obj) {
                if ((i & 1) != 0) {
                    livePair = available.pair;
                }
                return available.copy(livePair);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final LivePair getPair() {
                return this.pair;
            }

            public final Available copy(LivePair pair) {
                Intrinsics.checkNotNullParameter(pair, "pair");
                return new Available(pair);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Available) && Intrinsics.areEqual(this.pair, ((Available) other).pair);
            }

            public int hashCode() {
                return this.pair.hashCode();
            }

            public String toString() {
                return "Available(pair=" + this.pair + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Available(LivePair pair) {
                super(null);
                Intrinsics.checkNotNullParameter(pair, "pair");
                this.pair = pair;
            }

            public final LivePair getPair() {
                return this.pair;
            }
        }

        private SlotResolution() {
        }

        /* JADX INFO: compiled from: MoaPairCatalogPolicy.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\t¨\u0006\u0019"}, d2 = {"Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution$UnavailablePersisted;", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution;", "provider", "", "model", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getProvider", "()Ljava/lang/String;", "getModel", "getReason", "key", "getKey", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class UnavailablePersisted extends SlotResolution {
            public static final int $stable = 0;
            private final String model;
            private final String provider;
            private final String reason;

            public static /* synthetic */ UnavailablePersisted copy$default(UnavailablePersisted unavailablePersisted, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = unavailablePersisted.provider;
                }
                if ((i & 2) != 0) {
                    str2 = unavailablePersisted.model;
                }
                if ((i & 4) != 0) {
                    str3 = unavailablePersisted.reason;
                }
                return unavailablePersisted.copy(str, str2, str3);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getProvider() {
                return this.provider;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getModel() {
                return this.model;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getReason() {
                return this.reason;
            }

            public final UnavailablePersisted copy(String provider, String model, String reason) {
                Intrinsics.checkNotNullParameter(provider, "provider");
                Intrinsics.checkNotNullParameter(model, "model");
                Intrinsics.checkNotNullParameter(reason, "reason");
                return new UnavailablePersisted(provider, model, reason);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof UnavailablePersisted)) {
                    return false;
                }
                UnavailablePersisted unavailablePersisted = (UnavailablePersisted) other;
                return Intrinsics.areEqual(this.provider, unavailablePersisted.provider) && Intrinsics.areEqual(this.model, unavailablePersisted.model) && Intrinsics.areEqual(this.reason, unavailablePersisted.reason);
            }

            public int hashCode() {
                return (((this.provider.hashCode() * 31) + this.model.hashCode()) * 31) + this.reason.hashCode();
            }

            public String toString() {
                return "UnavailablePersisted(provider=" + this.provider + ", model=" + this.model + ", reason=" + this.reason + ")";
            }

            public /* synthetic */ UnavailablePersisted(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, (i & 4) != 0 ? "Saved provider/model pair is not in the live catalog" : str3);
            }

            public final String getProvider() {
                return this.provider;
            }

            public final String getModel() {
                return this.model;
            }

            public final String getReason() {
                return this.reason;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public UnavailablePersisted(String provider, String model, String reason) {
                super(null);
                Intrinsics.checkNotNullParameter(provider, "provider");
                Intrinsics.checkNotNullParameter(model, "model");
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.provider = provider;
                this.model = model;
                this.reason = reason;
            }

            public final String getKey() {
                return this.provider + "::" + this.model;
            }
        }

        /* JADX INFO: compiled from: MoaPairCatalogPolicy.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution$Empty;", "Lai/nous/aurora/domain/model/MoaPairCatalogPolicy$SlotResolution;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Empty extends SlotResolution {
            public static final int $stable = 0;
            public static final Empty INSTANCE = new Empty();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Empty)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return -358233637;
            }

            public String toString() {
                return "Empty";
            }

            private Empty() {
                super(null);
            }
        }
    }

    public final List<LivePair> livePairs(List<ModelProviderCatalog> providers) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        Iterable $this$map$iv = ModelCatalogPolicy.INSTANCE.choices(providers);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            CatalogModelChoice it = (CatalogModelChoice) item$iv$iv;
            destination$iv$iv.add(new LivePair(it.getProvider(), it.getProviderName(), it.getModel()));
        }
        return (List) destination$iv$iv;
    }

    public final List<ProviderModels> providersWithModels(List<ModelProviderCatalog> providers) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        List<ModelProviderCatalog> $this$filter$iv = providers;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ModelProviderCatalog it = (ModelProviderCatalog) element$iv$iv;
            if (!StringsKt.isBlank(it.getSlug())) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        int i = 10;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ModelProviderCatalog p = (ModelProviderCatalog) item$iv$iv;
            String slug = p.getSlug();
            String name = p.getName();
            if (StringsKt.isBlank(name)) {
                name = p.getSlug();
            }
            String str = name;
            Iterable $this$map$iv2 = p.getModels();
            Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, i));
            for (Object item$iv$iv2 : $this$map$iv2) {
                String p0 = (String) item$iv$iv2;
                destination$iv$iv3.add(StringsKt.trim((CharSequence) p0).toString());
            }
            Iterable $this$filter$iv2 = (List) destination$iv$iv3;
            Collection destination$iv$iv4 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                Iterable $this$filter$iv3 = $this$filter$iv2;
                String p1 = (String) element$iv$iv2;
                if (p1.length() > 0) {
                    destination$iv$iv4.add(element$iv$iv2);
                }
                $this$filter$iv2 = $this$filter$iv3;
            }
            destination$iv$iv2.add(new ProviderModels(slug, str, CollectionsKt.distinct((List) destination$iv$iv4)));
            i = 10;
        }
        Iterable $this$filter$iv4 = (List) destination$iv$iv2;
        Collection destination$iv$iv5 = new ArrayList();
        for (Object element$iv$iv3 : $this$filter$iv4) {
            ProviderModels it2 = (ProviderModels) element$iv$iv3;
            if (!it2.getModels().isEmpty()) {
                destination$iv$iv5.add(element$iv$iv3);
            }
        }
        return (List) destination$iv$iv5;
    }

    public final List<String> modelsForProvider(List<ModelProviderCatalog> providers, String provider) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        Intrinsics.checkNotNullParameter(provider, "provider");
        return ModelCatalogPolicy.INSTANCE.modelsFor(providers, provider);
    }

    public final boolean isPairAvailable(List<ModelProviderCatalog> providers, String provider, String model) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(model, "model");
        if (StringsKt.isBlank(provider) || StringsKt.isBlank(model)) {
            return false;
        }
        return ModelCatalogPolicy.INSTANCE.isValid(providers, new ModelSelection(provider, model));
    }

    /* JADX WARN: Code duplicated, block: B:58:0x00bb  */
    public final SlotResolution resolvePersistedSlot(List<ModelProviderCatalog> providers, String provider, String model) {
        String name;
        String name2;
        Intrinsics.checkNotNullParameter(providers, "providers");
        Object obj = null;
        String p = provider != null ? StringsKt.trim((CharSequence) provider).toString() : null;
        if (p == null) {
            p = "";
        }
        String string = model != null ? StringsKt.trim((CharSequence) model).toString() : null;
        String m = string != null ? string : "";
        if (p.length() == 0) {
            if (m.length() == 0) {
                return SlotResolution.Empty.INSTANCE;
            }
        }
        if (!(p.length() == 0)) {
            if (!(m.length() == 0)) {
                if (!isPairAvailable(providers, p, m)) {
                    return new SlotResolution.UnavailablePersisted(p, m, null, 4, null);
                }
                List<ModelProviderCatalog> $this$firstOrNull$iv = providers;
                for (Object element$iv : $this$firstOrNull$iv) {
                    ModelProviderCatalog it = (ModelProviderCatalog) element$iv;
                    if (Intrinsics.areEqual(it.getSlug(), p)) {
                        obj = element$iv;
                        break;
                    }
                }
                ModelProviderCatalog modelProviderCatalog = (ModelProviderCatalog) obj;
                if (modelProviderCatalog == null || (name2 = modelProviderCatalog.getName()) == null) {
                    name = p;
                } else {
                    String str = name2;
                    if (StringsKt.isBlank(str)) {
                        str = p;
                    }
                    name = str;
                    if (name == null) {
                        name = p;
                    }
                }
                return new SlotResolution.Available(new LivePair(p, name, m));
            }
        }
        return new SlotResolution.UnavailablePersisted(p, m, "Incomplete provider/model pair");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List selectableModelsForProvider$default(MoaPairCatalogPolicy moaPairCatalogPolicy, List list, String str, Pair pair, int i, Object obj) {
        if ((i & 4) != 0) {
            pair = null;
        }
        return moaPairCatalogPolicy.selectableModelsForProvider(list, str, pair);
    }

    public final List<String> selectableModelsForProvider(List<ModelProviderCatalog> providers, String provider, Pair<String, String> includePersistedIfMissing) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        Intrinsics.checkNotNullParameter(provider, "provider");
        List<String> mutableList = CollectionsKt.toMutableList((Collection) modelsForProvider(providers, provider));
        if (includePersistedIfMissing != null && Intrinsics.areEqual(includePersistedIfMissing.getFirst(), provider) && !StringsKt.isBlank(includePersistedIfMissing.getSecond()) && !mutableList.contains(includePersistedIfMissing.getSecond())) {
            mutableList.add(0, includePersistedIfMissing.getSecond());
        }
        return mutableList;
    }

    public final boolean canPersistSlot(List<ModelProviderCatalog> providers, String provider, String model) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(model, "model");
        return isPairAvailable(providers, provider, model);
    }
}
