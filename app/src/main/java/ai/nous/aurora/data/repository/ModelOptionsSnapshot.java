package ai.nous.aurora.data.repository;

import ai.nous.aurora.domain.model.ModelProviderCatalog;
import ai.nous.aurora.domain.model.ModelSelection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J/\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lai/nous/aurora/data/repository/ModelOptionsSnapshot;", "", "providers", "", "Lai/nous/aurora/domain/model/ModelProviderCatalog;", "current", "Lai/nous/aurora/domain/model/ModelSelection;", "currentValid", "", "<init>", "(Ljava/util/List;Lai/nous/aurora/domain/model/ModelSelection;Z)V", "getProviders", "()Ljava/util/List;", "getCurrent", "()Lai/nous/aurora/domain/model/ModelSelection;", "getCurrentValid", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ModelOptionsSnapshot {
    public static final int $stable = 8;
    private final ModelSelection current;
    private final boolean currentValid;
    private final List<ModelProviderCatalog> providers;

    public ModelOptionsSnapshot() {
        this(null, null, false, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ModelOptionsSnapshot copy$default(ModelOptionsSnapshot modelOptionsSnapshot, List list, ModelSelection modelSelection, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            list = modelOptionsSnapshot.providers;
        }
        if ((i & 2) != 0) {
            modelSelection = modelOptionsSnapshot.current;
        }
        if ((i & 4) != 0) {
            z = modelOptionsSnapshot.currentValid;
        }
        return modelOptionsSnapshot.copy(list, modelSelection, z);
    }

    public final List<ModelProviderCatalog> component1() {
        return this.providers;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ModelSelection getCurrent() {
        return this.current;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getCurrentValid() {
        return this.currentValid;
    }

    public final ModelOptionsSnapshot copy(List<ModelProviderCatalog> providers, ModelSelection current, boolean currentValid) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        return new ModelOptionsSnapshot(providers, current, currentValid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ModelOptionsSnapshot)) {
            return false;
        }
        ModelOptionsSnapshot modelOptionsSnapshot = (ModelOptionsSnapshot) other;
        return Intrinsics.areEqual(this.providers, modelOptionsSnapshot.providers) && Intrinsics.areEqual(this.current, modelOptionsSnapshot.current) && this.currentValid == modelOptionsSnapshot.currentValid;
    }

    public int hashCode() {
        return (((this.providers.hashCode() * 31) + (this.current == null ? 0 : this.current.hashCode())) * 31) + Boolean.hashCode(this.currentValid);
    }

    public String toString() {
        return "ModelOptionsSnapshot(providers=" + this.providers + ", current=" + this.current + ", currentValid=" + this.currentValid + ")";
    }

    public ModelOptionsSnapshot(List<ModelProviderCatalog> providers, ModelSelection current, boolean currentValid) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        this.providers = providers;
        this.current = current;
        this.currentValid = currentValid;
    }

    public /* synthetic */ ModelOptionsSnapshot(List list, ModelSelection modelSelection, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? null : modelSelection, (i & 4) != 0 ? false : z);
    }

    public final List<ModelProviderCatalog> getProviders() {
        return this.providers;
    }




}
