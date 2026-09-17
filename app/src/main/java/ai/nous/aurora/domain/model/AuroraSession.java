package ai.nous.aurora.domain.model;

import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: AuroraSession.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0001-Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\t\u0010$\u001a\u00020\nHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003Je\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\bHÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0012¨\u0006."}, d2 = {"Lai/nous/aurora/domain/model/AuroraSession;", "", "id", "", "title", "source", "model", "messageCount", "", "lastActiveMs", "", "preview", "messages", "", "Lai/nous/aurora/domain/model/AuroraMessage;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getTitle", "getSource", "getModel", "getMessageCount", "()I", "getLastActiveMs", "()J", "getPreview", "getMessages", "()Ljava/util/List;", "sourceKey", "getSourceKey", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AuroraSession {
    private final String id;
    private final long lastActiveMs;
    private final int messageCount;
    private final List<AuroraMessage> messages;
    private final String model;
    private final String preview;
    private final String source;
    private final String title;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getMessageCount() {
        return this.messageCount;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getLastActiveMs() {
        return this.lastActiveMs;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPreview() {
        return this.preview;
    }

    public final List<AuroraMessage> component8() {
        return this.messages;
    }

    public final AuroraSession copy(String id, String title, String source, String model, int messageCount, long lastActiveMs, String preview, List<AuroraMessage> messages) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(messages, "messages");
        return new AuroraSession(id, title, source, model, messageCount, lastActiveMs, preview, messages);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuroraSession)) {
            return false;
        }
        AuroraSession auroraSession = (AuroraSession) other;
        return Intrinsics.areEqual(this.id, auroraSession.id) && Intrinsics.areEqual(this.title, auroraSession.title) && Intrinsics.areEqual(this.source, auroraSession.source) && Intrinsics.areEqual(this.model, auroraSession.model) && this.messageCount == auroraSession.messageCount && this.lastActiveMs == auroraSession.lastActiveMs && Intrinsics.areEqual(this.preview, auroraSession.preview) && Intrinsics.areEqual(this.messages, auroraSession.messages);
    }

    public int hashCode() {
        return (((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + (this.source == null ? 0 : this.source.hashCode())) * 31) + (this.model == null ? 0 : this.model.hashCode())) * 31) + Integer.hashCode(this.messageCount)) * 31) + Long.hashCode(this.lastActiveMs)) * 31) + (this.preview != null ? this.preview.hashCode() : 0)) * 31) + this.messages.hashCode();
    }

    public String toString() {
        return "AuroraSession(id=" + this.id + ", title=" + this.title + ", source=" + this.source + ", model=" + this.model + ", messageCount=" + this.messageCount + ", lastActiveMs=" + this.lastActiveMs + ", preview=" + this.preview + ", messages=" + this.messages + ")";
    }

    public AuroraSession(String id, String title, String source, String model, int messageCount, long lastActiveMs, String preview, List<AuroraMessage> messages) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(messages, "messages");
        this.id = id;
        this.title = title;
        this.source = source;
        this.model = model;
        this.messageCount = messageCount;
        this.lastActiveMs = lastActiveMs;
        this.preview = preview;
        this.messages = messages;
    }

    public /* synthetic */ AuroraSession(String str, String str2, String str3, String str4, int i, long j, String str5, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? 0 : i, (i2 & 32) != 0 ? 0L : j, (i2 & 64) == 0 ? str5 : null, (i2 & 128) != 0 ? CollectionsKt.emptyList() : list);
    }





    public final List<AuroraMessage> getMessages() {
        return this.messages;
    }

    public final String getSourceKey() {
        return INSTANCE.normalizeSource(this.source);
    }

    /* JADX INFO: compiled from: AuroraSession.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005¨\u0006\t"}, d2 = {"Lai/nous/aurora/domain/model/AuroraSession$Companion;", "", "<init>", "()V", "normalizeSource", "", "raw", "sourceLabel", "key", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String normalizeSource(String raw) {
            String s;
            String string;
            if (raw == null || (string = StringsKt.trim((CharSequence) raw).toString()) == null) {
                s = null;
            } else {
                s = string.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(s, "toLowerCase(...)");
            }
            if (s == null) {
                s = "";
            }
            switch (s.hashCode()) {
                case -1406381216:
                    if (!s.equals("aurora")) {
                        return s;
                    }
                    return "aurora";
                case -1360467711:
                    if (!s.equals("telegram")) {
                        return s;
                    }
                    return "telegram";
                case -1181248900:
                    if (!s.equals("terminal")) {
                        return s;
                    }
                    return "cli";
                case -1068855134:
                    if (!s.equals("mobile")) {
                        return s;
                    }
                    return "aurora";
                case -1047860588:
                    if (!s.equals("dashboard")) {
                        return s;
                    }
                    return "desktop";
                case 0:
                    if (!s.equals("")) {
                        return s;
                    }
                    return "other";
                case 3199:
                    return !s.equals("dc") ? s : "discord";
                case 3699:
                    if (!s.equals("tg")) {
                        return s;
                    }
                    return "telegram";
                case 96794:
                    if (!s.equals("api")) {
                        return s;
                    }
                    return "aurora";
                case 98592:
                    if (!s.equals("cli")) {
                        return s;
                    }
                    return "cli";
                case 102715:
                    if (!s.equals("gui")) {
                        return s;
                    }
                    return "desktop";
                case 115208:
                    if (!s.equals("tui")) {
                        return s;
                    }
                    return "desktop";
                case 3392903:
                    if (!s.equals(AbstractJsonLexerKt.NULL)) {
                        return s;
                    }
                    return "other";
                case 3565976:
                    if (!s.equals("tool")) {
                        return s;
                    }
                    return "tool";
                case 110545371:
                    if (!s.equals("tools")) {
                        return s;
                    }
                    return "tool";
                case 1557106716:
                    if (!s.equals("desktop")) {
                        return s;
                    }
                    return "desktop";
                case 1671380268:
                    return !s.equals("discord") ? s : "discord";
                case 1955725576:
                    if (!s.equals("api_server")) {
                        return s;
                    }
                    return "aurora";
                default:
                    return s;
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final String sourceLabel(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            switch (key.hashCode()) {
                case -1406381216:
                    if (key.equals("aurora")) {
                        return "Aurora";
                    }
                    break;
                case -1360467711:
                    if (key.equals("telegram")) {
                        return "Telegram";
                    }
                    break;
                case 96673:
                    if (key.equals("all")) {
                        return "All";
                    }
                    break;
                case 98592:
                    if (key.equals("cli")) {
                        return "CLI";
                    }
                    break;
                case 3565976:
                    if (key.equals("tool")) {
                        return "Tool";
                    }
                    break;
                case 1557106716:
                    if (key.equals("desktop")) {
                        return "Desktop";
                    }
                    break;
                case 1671380268:
                    if (key.equals("discord")) {
                        return "Discord";
                    }
                    break;
            }
            if (!(key.length() > 0)) {
                return key;
            }
            StringBuilder sb = new StringBuilder();
            char it = key.charAt(0);
            String strValueOf = String.valueOf(it);
            Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
            String upperCase = strValueOf.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            StringBuilder sbAppend = sb.append((Object) upperCase);
            String strSubstring = key.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return sbAppend.append(strSubstring).toString();
        }
    }
}
