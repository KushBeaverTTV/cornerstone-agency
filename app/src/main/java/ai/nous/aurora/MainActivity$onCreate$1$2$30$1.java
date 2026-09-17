package ai.nous.aurora;

import ai.nous.aurora.data.remote.MoaConfigDto;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class MainActivity$onCreate$1$2$30$1 extends FunctionReferenceImpl implements kotlin.jvm.functions.Function1 {
    MainActivity$onCreate$1$2$30$1(Object obj) {
        super(1, obj, MainViewModel.class, "saveMoa", "saveMoa(Lai/nous/aurora/data/remote/MoaConfigDto;)V", 0);
    }

        public /* bridge */ /* synthetic */ Unit invoke(MoaConfigDto moaConfigDto) {
        invoke2(moaConfigDto);
        return Unit.INSTANCE;
    }
    @Override
    public Object invoke(Object p0) {
        return invoke((MoaConfigDto) p0);
    }


    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(MoaConfigDto p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((MainViewModel) this.receiver).saveMoa(p0);
    }
}
