package ai.nous.aurora.presentation.terminal;

import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TerminalOutputBuffer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006J\b\u0010\f\u001a\u0004\u0018\u00010\u0006J\u0006\u0010\r\u001a\u00020\nR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lai/nous/aurora/presentation/terminal/TerminalOutputBuffer;", "", "<init>", "()V", "chunks", "Lkotlin/collections/ArrayDeque;", "", "byteCount", "", "append", "", "bytes", "drain", "clear", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TerminalOutputBuffer {
    public static final int $stable = 8;
    private int byteCount;
    private final ArrayDeque<byte[]> chunks = new ArrayDeque<>();

    public final void append(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (bytes.length == 0) {
            return;
        }
        this.chunks.addLast(bytes);
        this.byteCount += bytes.length;
    }

    public final byte[] drain() {
        if (this.chunks.isEmpty()) {
            return null;
        }
        if (this.chunks.size() == 1) {
            this.byteCount = 0;
            return this.chunks.removeFirst();
        }
        byte[] combined = new byte[this.byteCount];
        int offset = 0;
        while (!this.chunks.isEmpty()) {
            byte[] chunk = this.chunks.removeFirst();
            ArraysKt.copyInto$default(chunk, combined, offset, 0, 0, 12, (Object) null);
            offset += chunk.length;
        }
        this.byteCount = 0;
        return combined;
    }

    public final void clear() {
        this.chunks.clear();
        this.byteCount = 0;
    }
}
