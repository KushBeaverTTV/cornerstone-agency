package ai.nous.aurora.data.repository;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository", f = "SettingsRepository.kt", i = {}, l = {218}, m = "setValue-0E7RQCE", n = {}, s = {})
final class DefaultSettingsRepository$setValue$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultSettingsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSettingsRepository$setValue$1(DefaultSettingsRepository defaultSettingsRepository, Continuation<? super DefaultSettingsRepository$setValue$1> continuation) {
        super(continuation);
        this.this$0 = defaultSettingsRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo60setValue0E7RQCE = this.this$0.mo60setValue0E7RQCE(null, null, this);
        return objMo60setValue0E7RQCE == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo60setValue0E7RQCE : Result.m7387boximpl(objMo60setValue0E7RQCE);
    }
}
