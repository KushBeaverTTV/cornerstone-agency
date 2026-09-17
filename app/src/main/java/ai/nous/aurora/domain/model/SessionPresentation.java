package ai.nous.aurora.domain.model;

import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SessionPresentation.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lai/nous/aurora/domain/model/SessionPresentation;", "", "<init>", "()V", "GENERIC_TITLES", "", "", "PREVIEW_MAX_LENGTH", "", "isGenericTitle", "", "raw", "displayTitle", "session", "Lai/nous/aurora/domain/model/AuroraSession;", "titleFromFirstMessage", "content", "previewLine", "subtitle", "metaLine", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SessionPresentation {
    private static final int PREVIEW_MAX_LENGTH = 120;
    public static final SessionPresentation INSTANCE = new SessionPresentation();
    private static final Set<String> GENERIC_TITLES = SetsKt.setOf(new String[]{"", "aurora chat", "new chat", "new session", "hermes", "untitled"});
    public static final int $stable = 8;

    private SessionPresentation() {
    }

    public final boolean isGenericTitle(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        Set<String> set = GENERIC_TITLES;
        String lowerCase = StringsKt.trim((CharSequence) raw).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return set.contains(lowerCase);
    }

    public final String displayTitle(AuroraSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        String raw = StringsKt.trim((CharSequence) session.getTitle()).toString();
        if ((raw.length() > 0) && !isGenericTitle(raw)) {
            return raw;
        }
        String src = AuroraSession.INSTANCE.sourceLabel(session.getSourceKey());
        return src + " · " + StringsKt.takeLast(session.getId(), 6);
    }

    public final String titleFromFirstMessage(String content) {
        String fromLine;
        String strStripCommandPrefix;
        Intrinsics.checkNotNullParameter(content, "content");
        String str = (String) SequencesKt.firstOrNull(StringsKt.lineSequence(content));
        String rawLine = str != null ? StringsKt.trim((CharSequence) str).toString() : null;
        if (rawLine == null) {
            rawLine = "";
        }
        if (MoaCommandBoundary.INSTANCE.isMoaCommand(rawLine)) {
            fromLine = MoaCommandBoundary.INSTANCE.stripCommandPrefix(rawLine);
        } else {
            fromLine = rawLine;
        }
        String str2 = fromLine;
        if (StringsKt.isBlank(str2)) {
            String full = StringsKt.trim((CharSequence) content).toString();
            if (MoaCommandBoundary.INSTANCE.isMoaCommand(full)) {
                strStripCommandPrefix = MoaCommandBoundary.INSTANCE.stripCommandPrefix(full);
            } else {
                strStripCommandPrefix = full;
            }
            str2 = strStripCommandPrefix;
        }
        String line = StringsKt.trim((CharSequence) str2).toString();
        if (line.length() == 0) {
            return "New chat";
        }
        if (line.length() <= 48) {
            return line;
        }
        return StringsKt.trimEnd((CharSequence) StringsKt.take(line, 45)).toString() + "…";
    }

    public final String previewLine(AuroraSession session) {
        Sequence<String> sequenceLineSequence;
        String str;
        Intrinsics.checkNotNullParameter(session, "session");
        String preview = session.getPreview();
        String preview2 = (preview == null || (sequenceLineSequence = StringsKt.lineSequence(preview)) == null || (str = (String) SequencesKt.firstOrNull(sequenceLineSequence)) == null) ? null : StringsKt.trim((CharSequence) str).toString();
        if (preview2 == null) {
            preview2 = "";
        }
        if (StringsKt.isBlank(preview2)) {
            return "";
        }
        String title = StringsKt.trim((CharSequence) displayTitle(session)).toString();
        if ((title.length() > 0) && StringsKt.equals(preview2, title, true)) {
            return "";
        }
        return preview2.length() <= 120 ? preview2 : StringsKt.trimEnd((CharSequence) StringsKt.take(preview2, 119)).toString() + "…";
    }

    public final String subtitle(AuroraSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        List parts = new ArrayList();
        parts.add(AuroraSession.INSTANCE.sourceLabel(session.getSourceKey()));
        if (session.getMessageCount() > 0) {
            parts.add(session.getMessageCount() + " " + (session.getMessageCount() == 1 ? NotificationCompat.CATEGORY_MESSAGE : "msgs"));
        }
        String it = session.getModel();
        if (it != null) {
            if (!((StringsKt.isBlank(it) || Intrinsics.areEqual(it, "hermes-agent")) ? false : true)) {
                it = null;
            }
            if (it != null) {
                parts.add(ai.nous.aurora.compat.Compat.d_StringsKt_substringAfterLast_5(it, '/', (String) null, 2, (Object) null));
            }
        }
        return ai.nous.aurora.compat.Compat.d_CollectionsKt_joinToString_9(parts, " · ", null, null, 0, null, null, 62, null);
    }

    public final String metaLine(AuroraSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        List parts = new ArrayList();
        parts.add(AuroraSession.INSTANCE.sourceLabel(session.getSourceKey()));
        String it = session.getModel();
        if (it != null) {
            if (!((StringsKt.isBlank(it) || Intrinsics.areEqual(it, "hermes-agent")) ? false : true)) {
                it = null;
            }
            if (it != null) {
                parts.add(ai.nous.aurora.compat.Compat.d_StringsKt_substringAfterLast_5(it, '/', (String) null, 2, (Object) null));
            }
        }
        return ai.nous.aurora.compat.Compat.d_CollectionsKt_joinToString_9(parts, " · ", null, null, 0, null, null, 62, null);
    }
}
