package ai.nous.aurora;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class MainActivity$onCreate$1$2$38$1 extends FunctionReferenceImpl implements kotlin.jvm.functions.Function1 {
    MainActivity$onCreate$1$2$38$1(Object obj) {
        super(1, obj, MainViewModel.class, "setListening", "setListening(Z)V", 0);
    }

        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
        invoke(bool.booleanValue());
        return Unit.INSTANCE;
    }
    @Override
    public Object invoke(Object p0) {
        return invoke((Boolean) p0);
    }


    public final void invoke(boolean p0) {
        ((MainViewModel) this.receiver).setListening(p0);
    }
}
