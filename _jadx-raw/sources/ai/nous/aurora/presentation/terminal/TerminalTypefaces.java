package ai.nous.aurora.presentation.terminal;

import android.content.Context;
import android.graphics.Typeface;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TerminalTypefaces.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lai/nous/aurora/presentation/terminal/TerminalTypefaces;", "", "<init>", "()V", "regular", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroid/graphics/Typeface;", "mono", "context", "Landroid/content/Context;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TerminalTypefaces {
    public static final TerminalTypefaces INSTANCE = new TerminalTypefaces();
    private static final AtomicReference<Typeface> regular = new AtomicReference<>(null);
    public static final int $stable = 8;

    private TerminalTypefaces() {
    }

    public final Typeface mono(Context context) {
        Object objM7388constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Typeface it = regular.get();
        if (it != null) {
            return it;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            TerminalTypefaces terminalTypefaces = this;
            objM7388constructorimpl = Result.m7388constructorimpl(Typeface.createFromAsset(context.getAssets(), "fonts/LiberationMono-Regular.ttf"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
            objM7388constructorimpl = Typeface.MONOSPACE;
        }
        Typeface loaded = (Typeface) objM7388constructorimpl;
        TerminalScreenKt$TerminalScreen$connect$1$ws$1$onClosed$1$$ExternalSyntheticBackportWithForwarding0.m(regular, null, loaded);
        Typeface typeface = regular.get();
        if (typeface != null) {
            return typeface;
        }
        Typeface MONOSPACE = Typeface.MONOSPACE;
        Intrinsics.checkNotNullExpressionValue(MONOSPACE, "MONOSPACE");
        return MONOSPACE;
    }
}
