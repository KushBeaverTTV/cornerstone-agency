package ai.nous.aurora.presentation.terminal;

import android.content.Context;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.termux.terminal.TerminalSession;
import com.termux.terminal.TextStyle;
import com.termux.view.TerminalView;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;

/* JADX INFO: compiled from: TerminalScreen.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.terminal.TerminalScreenKt$TerminalScreen$connect$1", f = "TerminalScreen.kt", i = {0, 1, 2, 2, 2, 2, 2}, l = {238, 239, TextStyle.NUM_INDEXED_COLORS, 263}, m = "invokeSuspend", n = {"store", "key", "key", "gateway", "cols", "rows", "attempt"}, s = {"L$0", "L$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
final class TerminalScreenKt$TerminalScreen$connect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AtomicReference<OkHttpClient> $clientRef;
    final /* synthetic */ MutableIntState $connectEpoch$delegate;
    final /* synthetic */ Context $context;
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
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TerminalScreenKt$TerminalScreen$connect$1(Context context, AtomicReference<TerminalView> atomicReference, AtomicReference<WebSocket> atomicReference2, AtomicReference<OkHttpClient> atomicReference3, MutableState<String> mutableState, MutableIntState mutableIntState, MutableState<Pair<Integer, Integer>> mutableState2, CoroutineScope coroutineScope, MutableState<Boolean> mutableState3, ArrayDeque<byte[]> arrayDeque, Object obj, TerminalOutputBuffer terminalOutputBuffer, boolean[] zArr, AtomicReference<TerminalSession> atomicReference4, Continuation<? super TerminalScreenKt$TerminalScreen$connect$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$viewRef = atomicReference;
        this.$socketRef = atomicReference2;
        this.$clientRef = atomicReference3;
        this.$status$delegate = mutableState;
        this.$connectEpoch$delegate = mutableIntState;
        this.$lastGeom$delegate = mutableState2;
        this.$scope = coroutineScope;
        this.$ptyReady$delegate = mutableState3;
        this.$pendingInput = arrayDeque;
        this.$outLock = obj;
        this.$outputBuffer = terminalOutputBuffer;
        this.$flushScheduled = zArr;
        this.$sessionRef = atomicReference4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TerminalScreenKt$TerminalScreen$connect$1(this.$context, this.$viewRef, this.$socketRef, this.$clientRef, this.$status$delegate, this.$connectEpoch$delegate, this.$lastGeom$delegate, this.$scope, this.$ptyReady$delegate, this.$pendingInput, this.$outLock, this.$outputBuffer, this.$flushScheduled, this.$sessionRef, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TerminalScreenKt$TerminalScreen$connect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:52:0x010a  */
    /* JADX WARN: Code duplicated, block: B:54:0x0114  */
    /* JADX WARN: Code duplicated, block: B:55:0x0117  */
    /* JADX WARN: Code duplicated, block: B:57:0x011a  */
    /* JADX WARN: Code duplicated, block: B:69:0x0151 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:68:0x014f -> B:70:0x0152). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r31) {
        /*
            Method dump skipped, instruction units count: 724
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.presentation.terminal.TerminalScreenKt$TerminalScreen$connect$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
