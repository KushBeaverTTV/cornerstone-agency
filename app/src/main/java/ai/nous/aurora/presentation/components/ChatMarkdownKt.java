package ai.nous.aurora.presentation.components;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnitKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: ChatMarkdown.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a3\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"parseChatMarkdown", "Landroidx/compose/ui/text/AnnotatedString;", "raw", "", "bodyColor", "Landroidx/compose/ui/graphics/Color;", "accent", "codeBg", "parseChatMarkdown-zSO0fhY", "(Ljava/lang/String;JJJ)Landroidx/compose/ui/text/AnnotatedString;", "appendInlineMarkdown", "", "Landroidx/compose/ui/text/AnnotatedString$Builder;", "text", "appendInlineMarkdown-FLEW7EY", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Ljava/lang/String;JJJ)V", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ChatMarkdownKt {
    /* JADX INFO: renamed from: parseChatMarkdown-zSO0fhY$default, reason: not valid java name */
    public static /* synthetic */ AnnotatedString m112parseChatMarkdownzSO0fhY$default(String str, long j, long j2, long j3, int i, Object obj) {
        long jColor;
        if ((i & 8) == 0) {
            jColor = j3;
        } else {
            jColor = ColorKt.Color(587202559);
        }
        return m111parseChatMarkdownzSO0fhY(str, j, j2, jColor);
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 5 */
    /* JADX INFO: renamed from: parseChatMarkdown-zSO0fhY, reason: not valid java name */
    public static final AnnotatedString m111parseChatMarkdownzSO0fhY(String raw, long bodyColor, long accent, long codeBg) {
        boolean z;
        boolean z2;
        List lines;
        AnnotatedString.Builder $this$parseChatMarkdown_zSO0fhY_u24lambda_u246;
        long sp;
        boolean inFence;
        Intrinsics.checkNotNullParameter(raw, "raw");
        if (StringsKt.isBlank(raw)) {
            return new AnnotatedString(raw, java.util.Collections.<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.SpanStyle>>emptyList(), java.util.Collections.<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.ParagraphStyle>>emptyList());
        }
        String $this$any$iv = raw;
        boolean z3 = false;
        int i = 0;
        while (true) {
            z = true;
            if (i >= $this$any$iv.length()) {
                z2 = false;
                break;
            }
            char element$iv = $this$any$iv.charAt(i);
            char it = (element$iv == '`' || element$iv == '*' || element$iv == '#' || element$iv == '[') ? (char) 1 : (char) 0;
            if (it != 0) {
                z2 = true;
                break;
            }
            i++;
        }
        if (!z2) {
            return new AnnotatedString(raw, java.util.Collections.<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.SpanStyle>>emptyList(), java.util.Collections.<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.ParagraphStyle>>emptyList());
        }
        Object obj = null;
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0);
        AnnotatedString.Builder $this$parseChatMarkdown_zSO0fhY_u24lambda_u247 = builder;
        char c = '\n';
        List lines2 = ai.nous.aurora.compat.Compat.d_StringsKt_split_6((CharSequence) ai.nous.aurora.compat.Compat.d_StringsKt_replace_6(raw, "\r\n", "\n", false, 4, (Object) null), new char[]{'\n'}, false, 0, 6, (Object) null);
        boolean inFence2 = false;
        List $this$forEachIndexed$iv = lines2;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv2 = index$iv + 1;
            if (index$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String line = (String) item$iv;
            if (index$iv > 0) {
                $this$parseChatMarkdown_zSO0fhY_u24lambda_u247.append(c);
            }
            String trimmed = StringsKt.trimStart((CharSequence) line).toString();
            if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(trimmed, "```", z3, 2, obj)) {
                inFence = !inFence2 ? z : z3;
                SpanStyle style$iv = ai.nous.aurora.compat.Compat.newSpanStyle(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(accent, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(accent) : 0.85f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(accent) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(accent) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(accent) : 0.0f), TextUnitKt.getSp(12), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, FontFamily.Companion.getMonospace(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65500);
                AnnotatedString.Builder $this$withStyle$iv = $this$parseChatMarkdown_zSO0fhY_u24lambda_u247;
                int index$iv3 = $this$withStyle$iv.pushStyle(style$iv);
                try {
                    $this$withStyle$iv.append(inFence ? "┌ code" : "└");
                    Unit unit = Unit.INSTANCE;
                    $this$withStyle$iv.pop(index$iv3);
                    lines = lines2;
                    $this$parseChatMarkdown_zSO0fhY_u24lambda_u246 = $this$parseChatMarkdown_zSO0fhY_u24lambda_u247;
                } catch (Throwable th) {
                    $this$withStyle$iv.pop(index$iv3);
                    throw th;
                }
            } else {
                if (inFence2) {
                    SpanStyle style$iv2 = ai.nous.aurora.compat.Compat.newSpanStyle(bodyColor, TextUnitKt.getSp(13), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, FontFamily.Companion.getMonospace(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, codeBg, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 63452);
                    AnnotatedString.Builder $this$withStyle$iv2 = $this$parseChatMarkdown_zSO0fhY_u24lambda_u247;
                    int index$iv4 = $this$withStyle$iv2.pushStyle(style$iv2);
                    try {
                        $this$withStyle$iv2.append(line);
                        Unit unit2 = Unit.INSTANCE;
                        $this$withStyle$iv2.pop(index$iv4);
                        lines = lines2;
                        $this$parseChatMarkdown_zSO0fhY_u24lambda_u246 = $this$parseChatMarkdown_zSO0fhY_u24lambda_u247;
                    } catch (Throwable th2) {
                        $this$withStyle$iv2.pop(index$iv4);
                        throw th2;
                    }
                } else {
                    MatchResult headingMatch = new Regex("^(#{1,3})\\s+(.*)$").matchEntire(trimmed);
                    if (headingMatch != null) {
                        int level = headingMatch.getGroupValues().get(1).length();
                        String title = headingMatch.getGroupValues().get(2);
                        FontWeight semiBold = FontWeight.Companion.getSemiBold();
                        switch (level) {
                            case 1:
                                sp = TextUnitKt.getSp(18);
                                break;
                            case 2:
                                sp = TextUnitKt.getSp(16);
                                break;
                            default:
                                sp = TextUnitKt.getSp(15);
                                break;
                        }
                        SpanStyle style$iv3 = ai.nous.aurora.compat.Compat.newSpanStyle(accent, sp, semiBold, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65528);
                        AnnotatedString.Builder $this$withStyle$iv3 = $this$parseChatMarkdown_zSO0fhY_u24lambda_u247;
                        lines = lines2;
                        int index$iv5 = $this$withStyle$iv3.pushStyle(style$iv3);
                        try {
                            m110appendInlineMarkdownFLEW7EY($this$withStyle$iv3, title, bodyColor, accent, codeBg);
                            Unit unit3 = Unit.INSTANCE;
                            $this$withStyle$iv3.pop(index$iv5);
                            $this$parseChatMarkdown_zSO0fhY_u24lambda_u246 = $this$parseChatMarkdown_zSO0fhY_u24lambda_u247;
                        } catch (Throwable th3) {
                            $this$withStyle$iv3.pop(index$iv5);
                            throw th3;
                        }
                    } else {
                        lines = lines2;
                        MatchResult listMatch = new Regex("^(\\s*)([-*•])\\s+(.*)$").matchEntire(line);
                        if (listMatch != null) {
                            String indent = listMatch.getGroupValues().get(1);
                            String body = listMatch.getGroupValues().get(3);
                            $this$parseChatMarkdown_zSO0fhY_u24lambda_u247.append(indent);
                            SpanStyle style$iv4 = ai.nous.aurora.compat.Compat.newSpanStyle(accent, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65534);
                            AnnotatedString.Builder $this$withStyle$iv4 = $this$parseChatMarkdown_zSO0fhY_u24lambda_u247;
                            int index$iv6 = $this$withStyle$iv4.pushStyle(style$iv4);
                            try {
                                $this$withStyle$iv4.append("• ");
                                Unit unit4 = Unit.INSTANCE;
                                $this$withStyle$iv4.pop(index$iv6);
                                $this$parseChatMarkdown_zSO0fhY_u24lambda_u246 = $this$parseChatMarkdown_zSO0fhY_u24lambda_u247;
                                m110appendInlineMarkdownFLEW7EY($this$parseChatMarkdown_zSO0fhY_u24lambda_u247, body, bodyColor, accent, codeBg);
                            } catch (Throwable th4) {
                                $this$withStyle$iv4.pop(index$iv6);
                                throw th4;
                            }
                        } else {
                            $this$parseChatMarkdown_zSO0fhY_u24lambda_u246 = $this$parseChatMarkdown_zSO0fhY_u24lambda_u247;
                            m110appendInlineMarkdownFLEW7EY($this$parseChatMarkdown_zSO0fhY_u24lambda_u246, line, bodyColor, accent, codeBg);
                        }
                    }
                }
                inFence = inFence2;
            }
            $this$parseChatMarkdown_zSO0fhY_u24lambda_u247 = $this$parseChatMarkdown_zSO0fhY_u24lambda_u246;
            index$iv = index$iv2;
            lines2 = lines;
            z3 = false;
            z = true;
            obj = null;
            inFence2 = inFence;
            c = '\n';
        }
        return builder.toAnnotatedString();
    }

    /* JADX INFO: renamed from: appendInlineMarkdown-FLEW7EY, reason: not valid java name */
    private static final void m110appendInlineMarkdownFLEW7EY(AnnotatedString.Builder $this$appendInlineMarkdown_u2dFLEW7EY, String text, long bodyColor, long accent, long codeBg) {
        int i = 0;
        while (i < text.length()) {
            if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_6(text, "**", i, false, 4, (Object) null)) {
                int end = ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) text, "**", i + 2, false, 4, (Object) null);
                if (end > i + 2) {
                    SpanStyle style$iv = ai.nous.aurora.compat.Compat.newSpanStyle(bodyColor, 0L, FontWeight.Companion.getBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65530);
                    int index$iv = $this$appendInlineMarkdown_u2dFLEW7EY.pushStyle(style$iv);
                    try {
                        String strSubstring = text.substring(i + 2, end);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                        $this$appendInlineMarkdown_u2dFLEW7EY.append(strSubstring);
                        Unit unit = Unit.INSTANCE;
                        $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv);
                        i = end + 2;
                    } catch (Throwable th) {
                        $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv);
                        throw th;
                    }
                } else {
                    $this$appendInlineMarkdown_u2dFLEW7EY.append(text.charAt(i));
                    i++;
                }
            } else if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_6(text, "`", i, false, 4, (Object) null) && !ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_6(text, "```", i, false, 4, (Object) null)) {
                int end2 = ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) text, '`', i + 1, false, 4, (Object) null);
                if (end2 > i + 1) {
                    SpanStyle style$iv2 = ai.nous.aurora.compat.Compat.newSpanStyle(accent, TextUnitKt.getSp(13), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, FontFamily.Companion.getMonospace(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, codeBg, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 63452);
                    int index$iv2 = $this$appendInlineMarkdown_u2dFLEW7EY.pushStyle(style$iv2);
                    try {
                        String strSubstring2 = text.substring(i + 1, end2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                        $this$appendInlineMarkdown_u2dFLEW7EY.append(strSubstring2);
                        Unit unit2 = Unit.INSTANCE;
                        $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv2);
                        i = end2 + 1;
                    } catch (Throwable th2) {
                        $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv2);
                        throw th2;
                    }
                } else {
                    $this$appendInlineMarkdown_u2dFLEW7EY.append(text.charAt(i));
                    i++;
                }
            } else if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_6(text, "*", i, false, 4, (Object) null) && !ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_6(text, "**", i, false, 4, (Object) null)) {
                int end3 = ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) text, '*', i + 1, false, 4, (Object) null);
                if (end3 > i + 1) {
                    SpanStyle style$iv3 = ai.nous.aurora.compat.Compat.newSpanStyle(bodyColor, 0L, (FontWeight) null, ai.nous.aurora.compat.Compat.m6586boximpl(ai.nous.aurora.compat.Compat.m6595getItalic_LCdwA(FontStyle.Companion)), (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65526);
                    int index$iv3 = $this$appendInlineMarkdown_u2dFLEW7EY.pushStyle(style$iv3);
                    try {
                        String strSubstring3 = text.substring(i + 1, end3);
                        Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                        $this$appendInlineMarkdown_u2dFLEW7EY.append(strSubstring3);
                        Unit unit3 = Unit.INSTANCE;
                        $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv3);
                        i = end3 + 1;
                    } catch (Throwable th3) {
                        $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv3);
                        throw th3;
                    }
                } else {
                    $this$appendInlineMarkdown_u2dFLEW7EY.append(text.charAt(i));
                    i++;
                }
            } else if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_6(text, "[", i, false, 4, (Object) null)) {
                int closeLabel = ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) text, AbstractJsonLexerKt.END_LIST, i + 1, false, 4, (Object) null);
                int openUrl = (closeLabel <= i + 1 || closeLabel + 1 >= text.length() || text.charAt(closeLabel + 1) != '(') ? -1 : closeLabel + 1;
                int closeUrl = openUrl > 0 ? ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) text, ')', openUrl + 1, false, 4, (Object) null) : -1;
                if (openUrl <= 0 || closeUrl <= openUrl + 1) {
                    $this$appendInlineMarkdown_u2dFLEW7EY.append(text.charAt(i));
                    i++;
                } else {
                    SpanStyle style$iv4 = ai.nous.aurora.compat.Compat.newSpanStyle(accent, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, TextDecoration.Companion.getUnderline(), (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 61438);
                    int index$iv4 = $this$appendInlineMarkdown_u2dFLEW7EY.pushStyle(style$iv4);
                    try {
                        String strSubstring4 = text.substring(i + 1, closeLabel);
                        Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
                        $this$appendInlineMarkdown_u2dFLEW7EY.append(strSubstring4);
                        Unit unit4 = Unit.INSTANCE;
                        $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv4);
                        i = closeUrl + 1;
                    } catch (Throwable th4) {
                        $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv4);
                        throw th4;
                    }
                }
            } else {
                Integer[] numArr = new Integer[4];
                Integer numValueOf = Integer.valueOf(ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) text, "**", i, false, 4, (Object) null));
                int it = numValueOf.intValue();
                int it2 = it >= 0 ? 1 : 0;
                if (it2 == 0) {
                    numValueOf = null;
                }
                numArr[0] = numValueOf;
                Integer numValueOf2 = Integer.valueOf(ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) text, '`', i, false, 4, (Object) null));
                int it3 = numValueOf2.intValue();
                int it4 = it3 >= 0 ? 1 : 0;
                if (it4 == 0) {
                    numValueOf2 = null;
                }
                numArr[1] = numValueOf2;
                Integer numValueOf3 = Integer.valueOf(ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) text, '*', i, false, 4, (Object) null));
                int it5 = numValueOf3.intValue();
                int it6 = it5 >= 0 ? 1 : 0;
                if (it6 == 0) {
                    numValueOf3 = null;
                }
                numArr[2] = numValueOf3;
                Integer numValueOf4 = Integer.valueOf(ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) text, AbstractJsonLexerKt.BEGIN_LIST, i, false, 4, (Object) null));
                int it7 = numValueOf4.intValue();
                numArr[3] = it7 >= 0 ? numValueOf4 : null;
                Integer num = CollectionsKt.minOrNull(CollectionsKt.filterNotNull(CollectionsKt.listOf(numArr)));
                int next = num != null ? num.intValue() : text.length();
                SpanStyle style$iv5 = ai.nous.aurora.compat.Compat.newSpanStyle(bodyColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65534);
                int index$iv5 = $this$appendInlineMarkdown_u2dFLEW7EY.pushStyle(style$iv5);
                try {
                    String strSubstring5 = text.substring(i, next);
                    Intrinsics.checkNotNullExpressionValue(strSubstring5, "substring(...)");
                    $this$appendInlineMarkdown_u2dFLEW7EY.append(strSubstring5);
                    Unit unit5 = Unit.INSTANCE;
                    $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv5);
                    i = next;
                } catch (Throwable th5) {
                    $this$appendInlineMarkdown_u2dFLEW7EY.pop(index$iv5);
                    throw th5;
                }
            }
        }
    }
}
