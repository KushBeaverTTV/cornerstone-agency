package ai.nous.aurora;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class MainActivity$onCreate$1$2$36$1 extends FunctionReferenceImpl implements Function1<MainViewModel.ModelChoice, Unit> {
    MainActivity$onCreate$1$2$36$1(Object obj) {
        super(1, obj, MainViewModel.class, "selectModelChoice", "selectModelChoice(Lai/nous/aurora/MainViewModel$ModelChoice;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(MainViewModel.ModelChoice modelChoice) {
        invoke2(modelChoice);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(MainViewModel.ModelChoice p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((MainViewModel) this.receiver).selectModelChoice(p0);
    }
}
