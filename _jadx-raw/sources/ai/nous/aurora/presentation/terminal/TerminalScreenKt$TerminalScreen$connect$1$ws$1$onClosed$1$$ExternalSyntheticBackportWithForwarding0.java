package ai.nous.aurora.presentation.terminal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes12.dex */
public final /* synthetic */ class TerminalScreenKt$TerminalScreen$connect$1$ws$1$onClosed$1$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ boolean m(AtomicReference atomicReference, Object obj, Object obj2) {
        while (!atomicReference.compareAndSet(obj, obj2)) {
            if (atomicReference.get() != obj) {
                return false;
            }
        }
        return true;
    }
}
