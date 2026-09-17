package ai.nous.aurora;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class MainActivity$onCreate$1$2$28$1 extends FunctionReferenceImpl implements Function2<String, Boolean, Unit> {
    MainActivity$onCreate$1$2$28$1(Object obj) {
        super(2, obj, MainViewModel.class, "setConfigBoolean", "setConfigBoolean(Ljava/lang/String;Z)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
        invoke(str, bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(String p0, boolean p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((MainViewModel) this.receiver).setConfigBoolean(p0, p1);
    }
}
