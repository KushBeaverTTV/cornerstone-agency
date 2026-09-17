package ai.nous.aurora.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ComposerChipPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000b\u001a\u00020\u0005J\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005J\u0006\u0010\u000f\u001a\u00020\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u0015\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lai/nous/aurora/domain/model/ComposerChipPolicy;", "", "<init>", "()V", "META_FONT_SP", "", "MODEL_FONT_SP", "CHIP_MAX_WIDTH_DP", "CHIP_MIN_TOUCH_HEIGHT_DP", "DRAFT_MAX_LINES", "DRAFT_MAX_HEIGHT_DP", "metaFontSp", "chipMinTouchHeightDp", "chipMaxWidthDp", "screenWidthDp", "draftMaxLines", "metaLabel", "", "provider", "friendlyModel", "model", "friendlyProvider", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ComposerChipPolicy {
    public static final int $stable = 0;
    public static final int CHIP_MAX_WIDTH_DP = 118;
    public static final int CHIP_MIN_TOUCH_HEIGHT_DP = 48;
    public static final int DRAFT_MAX_HEIGHT_DP = 120;
    public static final int DRAFT_MAX_LINES = 5;
    public static final ComposerChipPolicy INSTANCE = new ComposerChipPolicy();
    public static final int META_FONT_SP = 11;
    public static final int MODEL_FONT_SP = 13;

    private ComposerChipPolicy() {
    }

    public final int metaFontSp() {
        return 11;
    }

    public final int chipMinTouchHeightDp() {
        return 48;
    }

    public final int chipMaxWidthDp(int screenWidthDp) {
        if (screenWidthDp <= 0 || screenWidthDp >= 340) {
            return CHIP_MAX_WIDTH_DP;
        }
        return 96;
    }

    public final int draftMaxLines() {
        return 5;
    }

    public final String metaLabel(String provider) {
        return friendlyProvider(provider);
    }

    public final String friendlyModel(String model) {
        String value = model != null ? StringsKt.trim((CharSequence) model).toString() : null;
        if (value == null) {
            value = "";
        }
        if (StringsKt.isBlank(value)) {
            return "Default model";
        }
        Iterable $this$filter$iv = StringsKt.split$default((CharSequence) value, new char[]{'-', '_'}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv, " ", null, null, 0, null, new Function1() { // from class: ai.nous.aurora.domain.model.ComposerChipPolicy$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ComposerChipPolicy.friendlyModel$lambda$2((String) obj);
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence friendlyModel$lambda$2(String part) {
        String string;
        Intrinsics.checkNotNullParameter(part, "part");
        if (StringsKt.equals(part, "gpt", true)) {
            return "GPT";
        }
        if (StringsKt.equals(part, "k3", true)) {
            return "K3";
        }
        if (StringsKt.equals(part, "sol", true)) {
            return "Sol";
        }
        if (StringsKt.equals(part, "grok", true)) {
            return "Grok";
        }
        Character chFirstOrNull = StringsKt.firstOrNull(part);
        if (chFirstOrNull != null && Character.isDigit(chFirstOrNull.charValue())) {
            return part;
        }
        if (part.length() > 0) {
            StringBuilder sb = new StringBuilder();
            char it = part.charAt(0);
            String strValueOf = String.valueOf(it);
            Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
            String upperCase = strValueOf.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            StringBuilder sbAppend = sb.append((Object) upperCase);
            String strSubstring = part.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            string = sbAppend.append(strSubstring).toString();
        } else {
            string = part;
        }
        return string;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:74:0x0134  */
    /* JADX WARN: Code duplicated, block: B:80:0x0146 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x012e A[SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
    
        if (r1.equals("kimi-coding") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
    
        if (r1.equals("nousresearch") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
    
        if (r1.equals("codex") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
    
        if (r1.equals("nous") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0064, code lost:
    
        if (r1.equals("kimi") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006d, code lost:
    
        if (r1.equals("grok") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0076, code lost:
    
        if (r1.equals("xai") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x007f, code lost:
    
        if (r1.equals("openai-codex") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0088, code lost:
    
        if (r1.equals("moonshot") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008b, code lost:
    
        return "Kimi";
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0095, code lost:
    
        if (r1.equals("xai-oauth") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0098, code lost:
    
        return "xAI";
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a2, code lost:
    
        if (r1.equals("openai") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a5, code lost:
    
        return "OpenAI";
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00af, code lost:
    
        if (r1.equals("google") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00b8, code lost:
    
        if (r1.equals("gemini") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00c2, code lost:
    
        if (r1.equals("google-gemini") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00c6, code lost:
    
        return "Google";
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d0, code lost:
    
        if (r1.equals("nous-research") == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00d4, code lost:
    
        return "Nous";
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String friendlyProvider(java.lang.String r18) {
        /*
            Method dump skipped, instruction units count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.domain.model.ComposerChipPolicy.friendlyProvider(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence friendlyProvider$lambda$4(String it) {
        String string;
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.length() > 0) {
            StringBuilder sb = new StringBuilder();
            char p0 = it.charAt(0);
            String strValueOf = String.valueOf(p0);
            Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
            String upperCase = strValueOf.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            StringBuilder sbAppend = sb.append((Object) upperCase);
            String strSubstring = it.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            string = sbAppend.append(strSubstring).toString();
        } else {
            string = it;
        }
        return string;
    }
}
