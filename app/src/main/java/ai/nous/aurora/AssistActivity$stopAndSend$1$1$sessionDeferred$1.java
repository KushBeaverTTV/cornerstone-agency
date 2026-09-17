package ai.nous.aurora;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AssistActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.AssistActivity$stopAndSend$1$1$sessionDeferred$1", f = "AssistActivity.kt", i = {}, l = {356}, m = "invokeSuspend", n = {}, s = {})
final class AssistActivity$stopAndSend$1$1$sessionDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    int label;
    final /* synthetic */ AssistActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssistActivity$stopAndSend$1$1$sessionDeferred$1(AssistActivity assistActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = assistActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        return new AssistActivity$stopAndSend$1$1$sessionDeferred$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((AssistActivity$stopAndSend$1$1$sessionDeferred$1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object assistSessionId = this.this$0.getAppModule().getCredentialStore().getAssistSessionId(this);
                return assistSessionId == coroutine_suspended ? coroutine_suspended : assistSessionId;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
