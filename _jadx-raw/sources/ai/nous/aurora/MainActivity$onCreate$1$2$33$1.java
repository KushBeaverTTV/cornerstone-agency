package ai.nous.aurora;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class MainActivity$onCreate$1$2$33$1 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
    MainActivity$onCreate$1$2$33$1(Object obj) {
        super(1, obj, MainViewModel.class, "setAssistSpeakReplies", "setAssistSpeakReplies(Z)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
        invoke(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(boolean p0) {
        ((MainViewModel) this.receiver).setAssistSpeakReplies(p0);
    }
}
