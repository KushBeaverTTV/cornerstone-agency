package ai.nous.aurora.presentation.terminal;

import com.termux.terminal.TerminalSession;
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

/* JADX INFO: compiled from: TerminalScreen.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.terminal.TerminalScreenKt$TerminalScreen$enqueuePtyOutput$1", f = "TerminalScreen.kt", i = {}, l = {145}, m = "invokeSuspend", n = {}, s = {})
final class TerminalScreenKt$TerminalScreen$enqueuePtyOutput$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ boolean[] $flushScheduled;
    final /* synthetic */ Object $outLock;
    final /* synthetic */ TerminalOutputBuffer $outputBuffer;
    final /* synthetic */ AtomicReference<TerminalSession> $sessionRef;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TerminalScreenKt$TerminalScreen$enqueuePtyOutput$1(Object obj, AtomicReference<TerminalSession> atomicReference, TerminalOutputBuffer terminalOutputBuffer, boolean[] zArr, Continuation continuation) {
        super(2, continuation);
        this.$outLock = obj;
        this.$sessionRef = atomicReference;
        this.$outputBuffer = terminalOutputBuffer;
        this.$flushScheduled = zArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        return new TerminalScreenKt$TerminalScreen$enqueuePtyOutput$1(this.$outLock, this.$sessionRef, this.$outputBuffer, this.$flushScheduled, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((TerminalScreenKt$TerminalScreen$enqueuePtyOutput$1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0024  */
    /* JADX WARN: Code duplicated, block: B:20:0x0031  */
    /* JADX WARN: Code duplicated, block: B:25:0x003c  */
    /* JADX WARN: Code duplicated, block: B:41:0x005e  */
    /* JADX WARN: Code duplicated, block: B:44:0x0069 A[Catch: all -> 0x0081, TryCatch #0 {all -> 0x0081, blocks: (B:12:0x001f, B:13:0x0023, B:16:0x0029, B:42:0x005f, B:44:0x0069, B:45:0x006e, B:50:0x007f, B:51:0x0080, B:15:0x0025), top: B:75:0x001f, inners: #6 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x0087  */
    /* JADX WARN: Code duplicated, block: B:60:0x0092  */
    /* JADX WARN: Code duplicated, block: B:91:0x002c A[SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object obj;
        boolean[] zArr;
        Object obj2;
        TerminalOutputBuffer terminalOutputBuffer;
        byte[] tail;
        TerminalSession terminalSession = null;
        Object obj3;
        TerminalOutputBuffer terminalOutputBuffer2;
        byte[] chunk;
        Object obj4;
        boolean[] zArr2;
        Object obj5;
        TerminalOutputBuffer terminalOutputBuffer3;
        byte[] tail2;
        TerminalSession terminalSession2 = null;
        TerminalSession terminalSession3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                do {
                    try {
                        obj3 = this.$outLock;
                        terminalOutputBuffer2 = this.$outputBuffer;
                        synchronized (obj3) {
                            chunk = terminalOutputBuffer2.drain();
                        }
                        if (chunk == null) {
                            obj4 = this.$outLock;
                            zArr2 = this.$flushScheduled;
                            synchronized (obj4) {
                                zArr2[0] = false;
                                Unit unit = Unit.INSTANCE;
                            }
                            obj5 = this.$outLock;
                            terminalOutputBuffer3 = this.$outputBuffer;
                            synchronized (obj5) {
                                tail2 = terminalOutputBuffer3.drain();
                            }
                            if (tail2 != null && (terminalSession2 = this.$sessionRef.get()) != null) {
                                terminalSession2.writeToEmulator(tail2, tail2.length);
                            }
                            return Unit.INSTANCE;
                        }
                        terminalSession3 = this.$sessionRef.get();
                        if (terminalSession3 != null) {
                            terminalSession3.writeToEmulator(chunk, chunk.length);
                        }
                        this.label = 1;
                    } catch (Throwable th) {
                        obj = this.$outLock;
                        zArr = this.$flushScheduled;
                        synchronized (obj) {
                            zArr[0] = false;
                            Unit unit2 = Unit.INSTANCE;
                        }
                        obj2 = this.$outLock;
                        terminalOutputBuffer = this.$outputBuffer;
                        synchronized (obj2) {
                            tail = terminalOutputBuffer.drain();
                        }
                        if (tail != null && (terminalSession = this.$sessionRef.get()) != null) {
                            terminalSession.writeToEmulator(tail, tail.length);
                        }
                        throw th;
                    }
                } while (DelayKt.delay(8L, this) != coroutine_suspended);
                return coroutine_suspended;
            case 1:
                try {
                    ResultKt.throwOnFailure($result);
                    do {
                        obj3 = this.$outLock;
                        terminalOutputBuffer2 = this.$outputBuffer;
                        synchronized (obj3) {
                            chunk = terminalOutputBuffer2.drain();
                            if (chunk == null) {
                                obj4 = this.$outLock;
                                zArr2 = this.$flushScheduled;
                                synchronized (obj4) {
                                    zArr2[0] = false;
                                    Unit unit3 = Unit.INSTANCE;
                                    obj5 = this.$outLock;
                                    terminalOutputBuffer3 = this.$outputBuffer;
                                    synchronized (obj5) {
                                        tail2 = terminalOutputBuffer3.drain();
                                        if (tail2 != null && (terminalSession2 = this.$sessionRef.get()) != null) {
                                            terminalSession2.writeToEmulator(tail2, tail2.length);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            }
                            terminalSession3 = this.$sessionRef.get();
                            if (terminalSession3 != null) {
                                terminalSession3.writeToEmulator(chunk, chunk.length);
                            }
                            this.label = 1;
                        }
                    } while (DelayKt.delay(8L, this) != coroutine_suspended);
                    return coroutine_suspended;
                } catch (Throwable th2) {
                    obj = this.$outLock;
                    zArr = this.$flushScheduled;
                    synchronized (obj) {
                        zArr[0] = false;
                        Unit unit4 = Unit.INSTANCE;
                        obj2 = this.$outLock;
                        terminalOutputBuffer = this.$outputBuffer;
                        synchronized (obj2) {
                            tail = terminalOutputBuffer.drain();
                            if (tail != null && (terminalSession = this.$sessionRef.get()) != null) {
                                terminalSession.writeToEmulator(tail, tail.length);
                            }
                            throw th2;
                        }
                    }
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
