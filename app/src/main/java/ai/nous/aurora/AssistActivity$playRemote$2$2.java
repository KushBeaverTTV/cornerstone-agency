package ai.nous.aurora;

import android.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.AssistActivity$playRemote$2$2", f = "AssistActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class AssistActivity$playRemote$2$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ AssistActivity $this_runCatching;
    final /* synthetic */ File $tmp;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssistActivity$playRemote$2$2(AssistActivity assistActivity, File file, Continuation continuation) {
        super(2, continuation);
        this.$this_runCatching = assistActivity;
        this.$tmp = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        return new AssistActivity$playRemote$2$2(this.$this_runCatching, this.$tmp, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((AssistActivity$playRemote$2$2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                this.$this_runCatching.stopPlayback();
                AssistActivity assistActivity = this.$this_runCatching;
                MediaPlayer $this$invokeSuspend_u24lambda_u240 = new MediaPlayer();
                try {
                    $this$invokeSuspend_u24lambda_u240.setDataSource(this.$tmp.getAbsolutePath());
                    $this$invokeSuspend_u24lambda_u240.prepare();
                } catch (java.io.IOException e) {
                    throw ai.nous.aurora.compat.Compat.sneakyThrow(e);
                }
                $this$invokeSuspend_u24lambda_u240.start();
                assistActivity.player = $this$invokeSuspend_u24lambda_u240;
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
