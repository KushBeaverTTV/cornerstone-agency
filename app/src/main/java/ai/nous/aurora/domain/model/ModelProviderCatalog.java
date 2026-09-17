package ai.nous.aurora.domain.model;

import androidx.autofill.HintConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModelCatalogPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lai/nous/aurora/domain/model/ModelProviderCatalog;", "", "slug", "", HintConstants.AUTOFILL_HINT_NAME, "models", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getSlug", "()Ljava/lang/String;", "getName", "getModels", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ModelProviderCatalog {
    public static final int $stable = 8;
    private final List<String> models;
    private final String name;
    private final String slug;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ModelProviderCatalog copy$default(ModelProviderCatalog modelProviderCatalog, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = modelProviderCatalog.slug;
        }
        if ((i & 2) != 0) {
            str2 = modelProviderCatalog.name;
        }
        if ((i & 4) != 0) {
            list = modelProviderCatalog.models;
        }
        return modelProviderCatalog.copy(str, str2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSlug() {
        return this.slug;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component3() {
        return this.models;
    }

    public final ModelProviderCatalog copy(String slug, String name, List<String> models) {
        Intrinsics.checkNotNullParameter(slug, "slug");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(models, "models");
        return new ModelProviderCatalog(slug, name, models);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ModelProviderCatalog)) {
            return false;
        }
        ModelProviderCatalog modelProviderCatalog = (ModelProviderCatalog) other;
        return Intrinsics.areEqual(this.slug, modelProviderCatalog.slug) && Intrinsics.areEqual(this.name, modelProviderCatalog.name) && Intrinsics.areEqual(this.models, modelProviderCatalog.models);
    }

    public int hashCode() {
        return (((this.slug.hashCode() * 31) + this.name.hashCode()) * 31) + this.models.hashCode();
    }

    public String toString() {
        return "ModelProviderCatalog(slug=" + this.slug + ", name=" + this.name + ", models=" + this.models + ")";
    }

    public ModelProviderCatalog(String slug, String name, List<String> models) {
        Intrinsics.checkNotNullParameter(slug, "slug");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(models, "models");
        this.slug = slug;
        this.name = name;
        this.models = models;
    }





    public final List<String> getModels() {
        return this.models;
    }
}
