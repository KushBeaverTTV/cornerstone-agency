package ai.nous.aurora.domain.model;

import java.io.IOException;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: InboxActionLabelPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lai/nous/aurora/domain/model/InboxActionLabelPolicy;", "", "<init>", "()V", "screamCaseForbidden", "", "paintLabel", "", "raw", "isScreamCase", "painted", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class InboxActionLabelPolicy {
    public static final int $stable = 0;
    public static final InboxActionLabelPolicy INSTANCE = new InboxActionLabelPolicy();

    private InboxActionLabelPolicy() {
    }

    public final boolean screamCaseForbidden() {
        return true;
    }

    public final String paintLabel(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String t = StringsKt.trim((CharSequence) raw).toString();
        boolean z = true;
        if (t.length() == 0) {
            return t;
        }
        String $this$any$iv = t;
        int i = 0;
        while (true) {
            if (i < $this$any$iv.length()) {
                char element$iv = $this$any$iv.charAt(i);
                if (Character.isLowerCase(element$iv)) {
                    break;
                }
                i++;
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            return t;
        }
        String lowerCase = t.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return CollectionsKt.joinToString$default(new Regex("\\s+").split(lowerCase, 0), " ", null, null, 0, null, new Function1() { // from class: ai.nous.aurora.domain.model.InboxActionLabelPolicy$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return InboxActionLabelPolicy.paintLabel$lambda$2((String) obj);
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence paintLabel$lambda$2(String word) {
        String string;
        Intrinsics.checkNotNullParameter(word, "word");
        if (word.length() > 0) {
            StringBuilder sb = new StringBuilder();
            char ch = word.charAt(0);
            StringBuilder sbAppend = sb.append((Object) CharsKt.titlecase(ch));
            String strSubstring = word.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            string = sbAppend.append(strSubstring).toString();
        } else {
            string = word;
        }
        return string;
    }

    public final boolean isScreamCase(String painted) throws IOException {
        Intrinsics.checkNotNullParameter(painted, "painted");
        String $this$filterTo$iv$iv = painted;
        Appendable destination$iv$iv = new StringBuilder();
        int length = $this$filterTo$iv$iv.length();
        for (int index$iv$iv = 0; index$iv$iv < length; index$iv$iv++) {
            char element$iv$iv = $this$filterTo$iv$iv.charAt(index$iv$iv);
            if (Character.isLetter(element$iv$iv)) {
                destination$iv$iv.append(element$iv$iv);
            }
        }
        String letters = ((StringBuilder) destination$iv$iv).toString();
        Intrinsics.checkNotNullExpressionValue(letters, "toString(...)");
        if (letters.length() < 2) {
            return false;
        }
        String $this$all$iv = letters;
        for (int i = 0; i < $this$all$iv.length(); i++) {
            char element$iv = $this$all$iv.charAt(i);
            if (!Character.isUpperCase(element$iv)) {
                return false;
            }
        }
        return true;
    }
}
