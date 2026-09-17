package ai.nous.aurora;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class MainActivity$onCreate$1$2$27$1 extends FunctionReferenceImpl implements Function0<Unit> {
    MainActivity$onCreate$1$2$27$1(Object obj) {
        super(0, obj, MainViewModel.class, "backSettings", "backSettings()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((MainViewModel) this.receiver).backSettings();
    }
}
