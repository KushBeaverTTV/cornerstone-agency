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
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository", f = "SettingsRepository.kt", i = {}, l = {188}, m = "loadModelOptions-gIAlu-s", n = {}, s = {})
final class DefaultSettingsRepository$loadModelOptions$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultSettingsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSettingsRepository$loadModelOptions$1(DefaultSettingsRepository defaultSettingsRepository, Continuation continuation) {
        super(continuation);
        this.this$0 = defaultSettingsRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo58loadModelOptionsgIAlus = this.this$0.mo58loadModelOptionsgIAlus(false, this);
        return objMo58loadModelOptionsgIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo58loadModelOptionsgIAlus : ai.nous.aurora.compat.Compat.m7387boximpl(objMo58loadModelOptionsgIAlus);
    }
}
