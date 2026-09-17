package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModelCatalogPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lai/nous/aurora/domain/model/ModelSelection;", "", "provider", "", "model", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getProvider", "()Ljava/lang/String;", "getModel", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ModelSelection {
    public static final int $stable = 0;
    private final String model;
    private final String provider;

    public static /* synthetic */ ModelSelection copy$default(ModelSelection modelSelection, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = modelSelection.provider;
        }
        if ((i & 2) != 0) {
            str2 = modelSelection.model;
        }
        return modelSelection.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    public final ModelSelection copy(String provider, String model) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(model, "model");
        return new ModelSelection(provider, model);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ModelSelection)) {
            return false;
        }
        ModelSelection modelSelection = (ModelSelection) other;
        return Intrinsics.areEqual(this.provider, modelSelection.provider) && Intrinsics.areEqual(this.model, modelSelection.model);
    }

    public int hashCode() {
        return (this.provider.hashCode() * 31) + this.model.hashCode();
    }

    public String toString() {
        return "ModelSelection(provider=" + this.provider + ", model=" + this.model + ")";
    }

    public ModelSelection(String provider, String model) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(model, "model");
        this.provider = provider;
        this.model = model;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getModel() {
        return this.model;
    }
}
