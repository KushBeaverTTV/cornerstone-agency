package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ModelCatalogPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class ModelCatalogPolicy$choices$1$2 extends FunctionReferenceImpl implements Function1<String, Boolean> {
    public static final ModelCatalogPolicy$choices$1$2 INSTANCE = new ModelCatalogPolicy$choices$1$2();

    ModelCatalogPolicy$choices$1$2() {
        super(1, StringsKt.class, "isNotEmpty", "isNotEmpty(Ljava/lang/CharSequence;)Z", 1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Boolean.valueOf(p0.length() > 0);
    }
}
