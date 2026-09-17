package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ModelCatalogPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class ModelCatalogPolicy$choices$1$1 extends FunctionReferenceImpl implements Function1<String, String> {
    public static final ModelCatalogPolicy$choices$1$1 INSTANCE = new ModelCatalogPolicy$choices$1$1();

    ModelCatalogPolicy$choices$1$1() {
        super(1, StringsKt.class, "trim", "trim(Ljava/lang/String;)Ljava/lang/String;", 1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final String invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return StringsKt.trim((CharSequence) p0).toString();
    }
}
