package ai.nous.aurora.presentation.terminal;

import android.content.Context;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.termux.terminal.TerminalSession;
import com.termux.view.TerminalView;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;

/* JADX INFO: compiled from: TerminalScreen.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.terminal.TerminalScreenKt$TerminalScreen$1$1", f = "TerminalScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class TerminalScreenKt$TerminalScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ AtomicReference<OkHttpClient> $clientRef;
    final /* synthetic */ MutableIntState $connectEpoch$delegate;
    final /* synthetic */ Context $context;
    final /* synthetic */ boolean $demo;
    final /* synthetic */ boolean[] $flushScheduled;
    final /* synthetic */ MutableState<Pair<Integer, Integer>> $lastGeom$delegate;
    final /* synthetic */ Object $outLock;
    final /* synthetic */ TerminalOutputBuffer $outputBuffer;
    final /* synthetic */ ArrayDeque<byte[]> $pendingInput;
    final /* synthetic */ MutableState<Boolean> $ptyReady$delegate;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ AtomicReference<TerminalSession> $sessionRef;
    final /* synthetic */ AtomicReference<WebSocket> $socketRef;
    final /* synthetic */ MutableState<String> $status$delegate;
    final /* synthetic */ AtomicReference<TerminalView> $viewRef;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TerminalScreenKt$TerminalScreen$1$1(boolean z, AtomicReference<WebSocket> atomicReference, CoroutineScope coroutineScope, MutableState<String> mutableState, MutableState<Boolean> mutableState2, Object obj, AtomicReference<TerminalSession> atomicReference2, AtomicReference<TerminalView> atomicReference3, TerminalOutputBuffer terminalOutputBuffer, boolean[] zArr, Context context, AtomicReference<OkHttpClient> atomicReference4, MutableIntState mutableIntState, MutableState<Pair<Integer, Integer>> mutableState3, ArrayDeque<byte[]> arrayDeque, Continuation continuation) {
        super(2, continuation);
        this.$demo = z;
        this.$socketRef = atomicReference;
        this.$scope = coroutineScope;
        this.$status$delegate = mutableState;
        this.$ptyReady$delegate = mutableState2;
        this.$outLock = obj;
        this.$sessionRef = atomicReference2;
        this.$viewRef = atomicReference3;
        this.$outputBuffer = terminalOutputBuffer;
        this.$flushScheduled = zArr;
        this.$context = context;
        this.$clientRef = atomicReference4;
        this.$connectEpoch$delegate = mutableIntState;
        this.$lastGeom$delegate = mutableState3;
        this.$pendingInput = arrayDeque;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        return new TerminalScreenKt$TerminalScreen$1$1(this.$demo, this.$socketRef, this.$scope, this.$status$delegate, this.$ptyReady$delegate, this.$outLock, this.$sessionRef, this.$viewRef, this.$outputBuffer, this.$flushScheduled, this.$context, this.$clientRef, this.$connectEpoch$delegate, this.$lastGeom$delegate, this.$pendingInput, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((TerminalScreenKt$TerminalScreen$1$1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                TerminalScreenKt.TerminalScreen$connect(this.$demo, this.$socketRef, this.$scope, this.$status$delegate, this.$ptyReady$delegate, this.$outLock, this.$sessionRef, this.$viewRef, this.$outputBuffer, this.$flushScheduled, this.$context, this.$clientRef, this.$connectEpoch$delegate, this.$lastGeom$delegate, this.$pendingInput);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
