package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MoaCommandBoundary.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lai/nous/aurora/domain/model/MoaCommandBoundary;", "", "<init>", "()V", "MOA_COMMAND", "Lkotlin/text/Regex;", "MOA_PREFIX", "isMoaCommand", "", "content", "", "stripCommandPrefix", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MoaCommandBoundary {
    public static final MoaCommandBoundary INSTANCE = new MoaCommandBoundary();
    private static final Regex MOA_COMMAND = new Regex("(?is)^/moa(\\s|$).*");
    private static final Regex MOA_PREFIX = new Regex("(?is)^/moa\\s*");
    public static final int $stable = 8;

    private MoaCommandBoundary() {
    }

    public final boolean isMoaCommand(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return MOA_COMMAND.matches(StringsKt.trimStart((CharSequence) content).toString());
    }

    public final String stripCommandPrefix(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        String trimmed = StringsKt.trimStart((CharSequence) content).toString();
        return !isMoaCommand(trimmed) ? content : StringsKt.trimStart((CharSequence) MOA_PREFIX.replaceFirst(trimmed, "")).toString();
    }
}
