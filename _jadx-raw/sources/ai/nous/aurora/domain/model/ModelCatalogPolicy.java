package ai.nous.aurora.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ModelCatalogPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005J\u001c\u0010\t\u001a\u00020\n2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\u000b\u001a\u00020\fJ\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\u000f\u001a\u00020\u000e¨\u0006\u0010"}, d2 = {"Lai/nous/aurora/domain/model/ModelCatalogPolicy;", "", "<init>", "()V", "choices", "", "Lai/nous/aurora/domain/model/CatalogModelChoice;", "providers", "Lai/nous/aurora/domain/model/ModelProviderCatalog;", "isValid", "", "selection", "Lai/nous/aurora/domain/model/ModelSelection;", "modelsFor", "", "provider", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ModelCatalogPolicy {
    public static final int $stable = 0;
    public static final ModelCatalogPolicy INSTANCE = new ModelCatalogPolicy();

    private ModelCatalogPolicy() {
    }

    public final List<CatalogModelChoice> choices(List<ModelProviderCatalog> providers) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        List<ModelProviderCatalog> $this$flatMap$iv = providers;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            final ModelProviderCatalog provider = (ModelProviderCatalog) element$iv$iv;
            Iterable list$iv$iv = SequencesKt.toList(SequencesKt.map(SequencesKt.distinct(SequencesKt.filter(SequencesKt.map(CollectionsKt.asSequence(provider.getModels()), ModelCatalogPolicy$choices$1$1.INSTANCE), ModelCatalogPolicy$choices$1$2.INSTANCE)), new Function1() { // from class: ai.nous.aurora.domain.model.ModelCatalogPolicy$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ModelCatalogPolicy.choices$lambda$2$lambda$1(provider, (String) obj);
                }
            }));
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        Iterable $this$distinctBy$iv = (List) destination$iv$iv;
        HashSet set$iv = new HashSet();
        ArrayList list$iv = new ArrayList();
        for (Object e$iv : $this$distinctBy$iv) {
            CatalogModelChoice it = (CatalogModelChoice) e$iv;
            if (set$iv.add(it.getProvider() + "::" + it.getModel())) {
                list$iv.add(e$iv);
            }
        }
        return list$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CatalogModelChoice choices$lambda$2$lambda$1(ModelProviderCatalog $provider, String model) {
        Intrinsics.checkNotNullParameter(model, "model");
        String slug = $provider.getSlug();
        String name = $provider.getName();
        if (StringsKt.isBlank(name)) {
            name = $provider.getSlug();
        }
        return new CatalogModelChoice(slug, name, model);
    }

    public final boolean isValid(List<ModelProviderCatalog> providers, ModelSelection selection) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        Intrinsics.checkNotNullParameter(selection, "selection");
        return modelsFor(providers, selection.getProvider()).contains(selection.getModel());
    }

    public final List<String> modelsFor(List<ModelProviderCatalog> providers, String provider) {
        Object element$iv;
        ModelProviderCatalog it;
        Intrinsics.checkNotNullParameter(providers, "providers");
        Intrinsics.checkNotNullParameter(provider, "provider");
        List<ModelProviderCatalog> $this$firstOrNull$iv = providers;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            it = (ModelProviderCatalog) element$iv;
        } while (!Intrinsics.areEqual(it.getSlug(), provider));
        ModelProviderCatalog modelProviderCatalog = (ModelProviderCatalog) element$iv;
        List<String> models = modelProviderCatalog != null ? modelProviderCatalog.getModels() : null;
        return models == null ? CollectionsKt.emptyList() : models;
    }
}
