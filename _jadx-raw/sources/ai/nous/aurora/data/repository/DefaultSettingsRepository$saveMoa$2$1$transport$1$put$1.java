package ai.nous.aurora.data.repository;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository$saveMoa$2$1$transport$1", f = "SettingsRepository.kt", i = {}, l = {310}, m = "put", n = {}, s = {})
final class DefaultSettingsRepository$saveMoa$2$1$transport$1$put$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultSettingsRepository$saveMoa$2$1$transport$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSettingsRepository$saveMoa$2$1$transport$1$put$1(DefaultSettingsRepository$saveMoa$2$1$transport$1 defaultSettingsRepository$saveMoa$2$1$transport$1, Continuation<? super DefaultSettingsRepository$saveMoa$2$1$transport$1$put$1> continuation) {
        super(continuation);
        this.this$0 = defaultSettingsRepository$saveMoa$2$1$transport$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.put(null, this);
    }
}
