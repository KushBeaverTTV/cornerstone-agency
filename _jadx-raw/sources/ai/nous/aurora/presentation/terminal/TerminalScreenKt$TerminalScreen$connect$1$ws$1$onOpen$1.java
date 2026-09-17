package ai.nous.aurora.presentation.terminal;

import androidx.compose.runtime.MutableState;
import com.termux.terminal.TerminalEmulator;
import com.termux.view.TerminalView;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.WebSocket;

/* JADX INFO: compiled from: TerminalScreen.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.terminal.TerminalScreenKt$TerminalScreen$connect$1$ws$1$onOpen$1", f = "TerminalScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class TerminalScreenKt$TerminalScreen$connect$1$ws$1$onOpen$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $cols;
    final /* synthetic */ ArrayDeque<byte[]> $pendingInput;
    final /* synthetic */ MutableState<Boolean> $ptyReady$delegate;
    final /* synthetic */ Ref.IntRef $rows;
    final /* synthetic */ AtomicReference<WebSocket> $socketRef;
    final /* synthetic */ MutableState<String> $status$delegate;
    final /* synthetic */ AtomicReference<TerminalView> $viewRef;
    final /* synthetic */ WebSocket $webSocket;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TerminalScreenKt$TerminalScreen$connect$1$ws$1$onOpen$1(Ref.IntRef intRef, Ref.IntRef intRef2, AtomicReference<TerminalView> atomicReference, WebSocket webSocket, MutableState<Boolean> mutableState, AtomicReference<WebSocket> atomicReference2, ArrayDeque<byte[]> arrayDeque, MutableState<String> mutableState2, Continuation<? super TerminalScreenKt$TerminalScreen$connect$1$ws$1$onOpen$1> continuation) {
        super(2, continuation);
        this.$cols = intRef;
        this.$rows = intRef2;
        this.$viewRef = atomicReference;
        this.$webSocket = webSocket;
        this.$ptyReady$delegate = mutableState;
        this.$socketRef = atomicReference2;
        this.$pendingInput = arrayDeque;
        this.$status$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TerminalScreenKt$TerminalScreen$connect$1$ws$1$onOpen$1(this.$cols, this.$rows, this.$viewRef, this.$webSocket, this.$ptyReady$delegate, this.$socketRef, this.$pendingInput, this.$status$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TerminalScreenKt$TerminalScreen$connect$1$ws$1$onOpen$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                TerminalScreenKt.TerminalScreen$lambda$5(this.$ptyReady$delegate, true);
                TerminalScreenKt.TerminalScreen$flushPendingInput(this.$socketRef, this.$pendingInput);
                this.$status$delegate.setValue("Online · hermes · " + this.$cols.element + "×" + this.$rows.element);
                TerminalView tv = this.$viewRef.get();
                TerminalEmulator emu = tv != null ? tv.mEmulator : null;
                if (emu != null) {
                    this.$webSocket.send("{\"type\":\"resize\",\"rows\":" + emu.mRows + ",\"cols\":" + emu.mColumns + "}");
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
