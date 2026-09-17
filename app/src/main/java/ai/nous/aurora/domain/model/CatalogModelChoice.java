package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModelCatalogPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lai/nous/aurora/domain/model/CatalogModelChoice;", "", "provider", "", "providerName", "model", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getProvider", "()Ljava/lang/String;", "getProviderName", "getModel", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class CatalogModelChoice {
    public static final int $stable = 0;
    private final String model;
    private final String provider;
    private final String providerName;

    public static /* synthetic */ CatalogModelChoice copy$default(CatalogModelChoice catalogModelChoice, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = catalogModelChoice.provider;
        }
        if ((i & 2) != 0) {
            str2 = catalogModelChoice.providerName;
        }
        if ((i & 4) != 0) {
            str3 = catalogModelChoice.model;
        }
        return catalogModelChoice.copy(str, str2, str3);
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

    public final CatalogModelChoice copy(String provider, String providerName, String model) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(providerName, "providerName");
        Intrinsics.checkNotNullParameter(model, "model");
        return new CatalogModelChoice(provider, providerName, model);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogModelChoice)) {
            return false;
        }
        CatalogModelChoice catalogModelChoice = (CatalogModelChoice) other;
        return Intrinsics.areEqual(this.provider, catalogModelChoice.provider) && Intrinsics.areEqual(this.providerName, catalogModelChoice.providerName) && Intrinsics.areEqual(this.model, catalogModelChoice.model);
    }

    public int hashCode() {
        return (((this.provider.hashCode() * 31) + this.providerName.hashCode()) * 31) + this.model.hashCode();
    }

    public String toString() {
        return "CatalogModelChoice(provider=" + this.provider + ", providerName=" + this.providerName + ", model=" + this.model + ")";
    }

    public CatalogModelChoice(String provider, String providerName, String model) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(providerName, "providerName");
        Intrinsics.checkNotNullParameter(model, "model");
        this.provider = provider;
        this.providerName = providerName;
        this.model = model;
    }




}
