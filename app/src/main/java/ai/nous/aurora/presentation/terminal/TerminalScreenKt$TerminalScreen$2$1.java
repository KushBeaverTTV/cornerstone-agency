package ai.nous.aurora.presentation.terminal;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.lifecycle.CoroutineLiveDataKt;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import okhttp3.WebSocket;

/* JADX INFO: compiled from: TerminalScreen.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.terminal.TerminalScreenKt$TerminalScreen$2$1", f = "TerminalScreen.kt", i = {}, l = {348, 351}, m = "invokeSuspend", n = {}, s = {})
final class TerminalScreenKt$TerminalScreen$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ MutableIntState $connectEpoch$delegate;
    final /* synthetic */ boolean $demo;
    final /* synthetic */ MutableState<Boolean> $ptyReady$delegate;
    final /* synthetic */ AtomicReference<WebSocket> $socketRef;
    final /* synthetic */ MutableState<String> $status$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TerminalScreenKt$TerminalScreen$2$1(boolean z, AtomicReference<WebSocket> atomicReference, MutableState<String> mutableState, MutableState<Boolean> mutableState2, MutableIntState mutableIntState, Continuation continuation) {
        super(2, continuation);
        this.$demo = z;
        this.$socketRef = atomicReference;
        this.$status$delegate = mutableState;
        this.$ptyReady$delegate = mutableState2;
        this.$connectEpoch$delegate = mutableIntState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        return new TerminalScreenKt$TerminalScreen$2$1(this.$demo, this.$socketRef, this.$status$delegate, this.$ptyReady$delegate, this.$connectEpoch$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((TerminalScreenKt$TerminalScreen$2$1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x006b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x0074  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$demo) {
                    return Unit.INSTANCE;
                }
                if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(TerminalScreenKt.TerminalScreen$lambda$1(this.$status$delegate), "Connecting", false, 2, (Object) null)) {
                    this.label = 1;
                    if (DelayKt.delay(CoroutineLiveDataKt.DEFAULT_TIMEOUT, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (!TerminalScreenKt.TerminalScreen$lambda$4(this.$ptyReady$delegate) && ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(TerminalScreenKt.TerminalScreen$lambda$1(this.$status$delegate), "Connecting", false, 2, (Object) null)) {
                        this.$status$delegate.setValue("Idle · waiting for PTY");
                        this.label = 2;
                        if (DelayKt.delay(1600L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (!TerminalScreenKt.TerminalScreen$lambda$4(this.$ptyReady$delegate) && this.$socketRef.get() == null) {
                            this.$connectEpoch$delegate.setIntValue(TerminalScreenKt.TerminalScreen$lambda$13(this.$connectEpoch$delegate) + 1);
                        }
                    }
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                if (!TerminalScreenKt.TerminalScreen$lambda$4(this.$ptyReady$delegate)) {
                    this.$status$delegate.setValue("Idle · waiting for PTY");
                    this.label = 2;
                    if (DelayKt.delay(1600L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (!TerminalScreenKt.TerminalScreen$lambda$4(this.$ptyReady$delegate)) {
                        this.$connectEpoch$delegate.setIntValue(TerminalScreenKt.TerminalScreen$lambda$13(this.$connectEpoch$delegate) + 1);
                    }
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                if (!TerminalScreenKt.TerminalScreen$lambda$4(this.$ptyReady$delegate)) {
                    this.$connectEpoch$delegate.setIntValue(TerminalScreenKt.TerminalScreen$lambda$13(this.$connectEpoch$delegate) + 1);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
