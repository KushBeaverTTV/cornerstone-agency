package ai.nous.aurora.presentation.terminal;

import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TerminalFontMetrics.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u000b"}, d2 = {"Lai/nous/aurora/presentation/terminal/TerminalFontMetrics;", "", "<init>", "()V", "spToPx", "", "fontSp", "scaledDensity", "", "wouldSmashIfRawSp", "", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TerminalFontMetrics {
    public static final int $stable = 0;
    public static final TerminalFontMetrics INSTANCE = new TerminalFontMetrics();

    private TerminalFontMetrics() {
    }

    public final int spToPx(int fontSp, float scaledDensity) {
        int sp = RangesKt.coerceIn(fontSp, 8, 48);
        float density = (!(!Float.isInfinite(scaledDensity) && !Float.isNaN(scaledDensity)) || scaledDensity <= 0.0f) ? 1.0f : scaledDensity;
        return RangesKt.coerceIn((int) (sp * density), 10, 64);
    }

    public final boolean wouldSmashIfRawSp(int fontSp, float scaledDensity) {
        int correct = spToPx(fontSp, scaledDensity);
        return fontSp != correct && scaledDensity > 1.1f;
    }
}
