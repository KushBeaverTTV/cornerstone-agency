package ai.nous.aurora.presentation.terminal;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import okhttp3.WebSocket;

/* JADX INFO: compiled from: TerminalScreen.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.terminal.TerminalScreenKt$TerminalScreen$connect$1$ws$1$onFailure$1", f = "TerminalScreen.kt", i = {}, l = {331}, m = "invokeSuspend", n = {}, s = {})
final class TerminalScreenKt$TerminalScreen$connect$1$ws$1$onFailure$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableIntState $connectEpoch$delegate;
    final /* synthetic */ MutableState<Boolean> $ptyReady$delegate;
    final /* synthetic */ AtomicReference<WebSocket> $socketRef;
    final /* synthetic */ MutableState<String> $status$delegate;
    final /* synthetic */ WebSocket $webSocket;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TerminalScreenKt$TerminalScreen$connect$1$ws$1$onFailure$1(AtomicReference<WebSocket> atomicReference, WebSocket webSocket, MutableState<Boolean> mutableState, MutableState<String> mutableState2, MutableIntState mutableIntState, Continuation<? super TerminalScreenKt$TerminalScreen$connect$1$ws$1$onFailure$1> continuation) {
        super(2, continuation);
        this.$socketRef = atomicReference;
        this.$webSocket = webSocket;
        this.$ptyReady$delegate = mutableState;
        this.$status$delegate = mutableState2;
        this.$connectEpoch$delegate = mutableIntState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TerminalScreenKt$TerminalScreen$connect$1$ws$1$onFailure$1(this.$socketRef, this.$webSocket, this.$ptyReady$delegate, this.$status$delegate, this.$connectEpoch$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TerminalScreenKt$TerminalScreen$connect$1$ws$1$onFailure$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0056  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$socketRef.get() == this.$webSocket || this.$socketRef.get() == null) {
                    TerminalScreenKt.TerminalScreen$lambda$5(this.$ptyReady$delegate, false);
                    this.$status$delegate.setValue("Offline · retrying");
                    TerminalScreenKt$TerminalScreen$connect$1$ws$1$onClosed$1$$ExternalSyntheticBackportWithForwarding0.m(this.$socketRef, this.$webSocket, null);
                    this.label = 1;
                    if (DelayKt.delay(1600L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (this.$socketRef.get() == null) {
                        this.$connectEpoch$delegate.setIntValue(TerminalScreenKt.TerminalScreen$lambda$13(this.$connectEpoch$delegate) + 1);
                    }
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                if (this.$socketRef.get() == null) {
                    this.$connectEpoch$delegate.setIntValue(TerminalScreenKt.TerminalScreen$lambda$13(this.$connectEpoch$delegate) + 1);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
