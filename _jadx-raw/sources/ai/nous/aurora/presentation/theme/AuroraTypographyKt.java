package ai.nous.aurora.presentation.theme;

import androidx.compose.material3.Typography;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuroraTypography.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017\"\u0011\u0010\u0000\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0003\"\u0011\u0010\b\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\t\u0010\u0003\"\u0011\u0010\n\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0003\"\u0011\u0010\f\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\r\u0010\u0003\"\u0011\u0010\u000e\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0003\"\u0011\u0010\u0010\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0003\"\u0011\u0010\u0012\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0003¨\u0006\u0018"}, d2 = {"AuroraDisplay", "Landroidx/compose/ui/text/TextStyle;", "getAuroraDisplay", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/text/TextStyle;", "AuroraTitle", "getAuroraTitle", "AuroraTitleSm", "getAuroraTitleSm", "AuroraBody", "getAuroraBody", "AuroraBodyMuted", "getAuroraBodyMuted", "AuroraMeta", "getAuroraMeta", "AuroraMetaBright", "getAuroraMetaBright", "AuroraCaption", "getAuroraCaption", "AuroraCode", "getAuroraCode", "auroraTypography", "Landroidx/compose/material3/Typography;", "tokens", "Lai/nous/aurora/presentation/theme/ThemeTokens;", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AuroraTypographyKt {
    public static final TextStyle getAuroraDisplay(Composer $composer, int $changed) {
        $composer.startReplaceGroup(73427770);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraDisplay>)26@978L7:AuroraTypography.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(73427770, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraDisplay> (AuroraTypography.kt:20)");
        }
        GenericFontFamily sansSerif = FontFamily.INSTANCE.getSansSerif();
        FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
        long sp = TextUnitKt.getSp(24);
        long sp2 = TextUnitKt.getSp(30);
        long sp3 = TextUnitKt.getSp(-0.4d);
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextStyle textStyle = new TextStyle(((ThemeTokens) objConsume).m343getText0d7_KjU(), sp, semiBold, (FontStyle) null, (FontSynthesis) null, sansSerif, (String) null, sp3, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return textStyle;
    }

    public static final TextStyle getAuroraTitle(Composer $composer, int $changed) {
        $composer.startReplaceGroup(2086228430);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraTitle>)36@1268L7:AuroraTypography.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2086228430, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraTitle> (AuroraTypography.kt:30)");
        }
        GenericFontFamily sansSerif = FontFamily.INSTANCE.getSansSerif();
        FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
        long sp = TextUnitKt.getSp(20);
        long sp2 = TextUnitKt.getSp(26);
        long sp3 = TextUnitKt.getSp(-0.3d);
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextStyle textStyle = new TextStyle(((ThemeTokens) objConsume).m343getText0d7_KjU(), sp, semiBold, (FontStyle) null, (FontSynthesis) null, sansSerif, (String) null, sp3, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return textStyle;
    }

    public static final TextStyle getAuroraTitleSm(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1780109542);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraTitleSm>)47@1581L7:AuroraTypography.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1780109542, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraTitleSm> (AuroraTypography.kt:41)");
        }
        GenericFontFamily sansSerif = FontFamily.INSTANCE.getSansSerif();
        FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
        long sp = TextUnitKt.getSp(16);
        long sp2 = TextUnitKt.getSp(22);
        long sp3 = TextUnitKt.getSp(-0.2d);
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextStyle textStyle = new TextStyle(((ThemeTokens) objConsume).m343getText0d7_KjU(), sp, semiBold, (FontStyle) null, (FontSynthesis) null, sansSerif, (String) null, sp3, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return textStyle;
    }

    public static final TextStyle getAuroraBody(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-929865292);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraBody>)57@1863L7:AuroraTypography.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-929865292, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraBody> (AuroraTypography.kt:51)");
        }
        GenericFontFamily sansSerif = FontFamily.INSTANCE.getSansSerif();
        FontWeight normal = FontWeight.INSTANCE.getNormal();
        long sp = TextUnitKt.getSp(15);
        long sp2 = TextUnitKt.getSp(22);
        long sp3 = TextUnitKt.getSp(0);
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextStyle textStyle = new TextStyle(((ThemeTokens) objConsume).m343getText0d7_KjU(), sp, normal, (FontStyle) null, (FontSynthesis) null, sansSerif, (String) null, sp3, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return textStyle;
    }

    public static final TextStyle getAuroraBodyMuted(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-110091924);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraBodyMuted>)67@2143L7:AuroraTypography.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-110091924, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraBodyMuted> (AuroraTypography.kt:62)");
        }
        GenericFontFamily sansSerif = FontFamily.INSTANCE.getSansSerif();
        FontWeight normal = FontWeight.INSTANCE.getNormal();
        long sp = TextUnitKt.getSp(14);
        long sp2 = TextUnitKt.getSp(20);
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextStyle textStyle = new TextStyle(((ThemeTokens) objConsume).m346getTextMuted0d7_KjU(), sp, normal, (FontStyle) null, (FontSynthesis) null, sansSerif, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646104, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return textStyle;
    }

    public static final TextStyle getAuroraMeta(Composer $composer, int $changed) {
        $composer.startReplaceGroup(172898836);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraMeta>)78@2479L7:AuroraTypography.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(172898836, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraMeta> (AuroraTypography.kt:72)");
        }
        GenericFontFamily monospace = FontFamily.INSTANCE.getMonospace();
        FontWeight medium = FontWeight.INSTANCE.getMedium();
        long sp = TextUnitKt.getSp(12);
        long sp2 = TextUnitKt.getSp(16);
        long sp3 = TextUnitKt.getSp(0.6d);
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextStyle textStyle = new TextStyle(((ThemeTokens) objConsume).m346getTextMuted0d7_KjU(), sp, medium, (FontStyle) null, (FontSynthesis) null, monospace, (String) null, sp3, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return textStyle;
    }

    public static final TextStyle getAuroraMetaBright(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1470825900);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraMetaBright>)88@2776L7:AuroraTypography.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1470825900, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraMetaBright> (AuroraTypography.kt:82)");
        }
        GenericFontFamily monospace = FontFamily.INSTANCE.getMonospace();
        FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
        long sp = TextUnitKt.getSp(12);
        long sp2 = TextUnitKt.getSp(16);
        long sp3 = TextUnitKt.getSp(0.8d);
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextStyle textStyle = new TextStyle(((ThemeTokens) objConsume).m308getAccent0d7_KjU(), sp, semiBold, (FontStyle) null, (FontSynthesis) null, monospace, (String) null, sp3, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return textStyle;
    }

    public static final TextStyle getAuroraCaption(Composer $composer, int $changed) {
        $composer.startReplaceGroup(711370994);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraCaption>)97@3033L7:AuroraTypography.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(711370994, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraCaption> (AuroraTypography.kt:92)");
        }
        GenericFontFamily sansSerif = FontFamily.INSTANCE.getSansSerif();
        FontWeight medium = FontWeight.INSTANCE.getMedium();
        long sp = TextUnitKt.getSp(12);
        long sp2 = TextUnitKt.getSp(16);
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextStyle textStyle = new TextStyle(((ThemeTokens) objConsume).m346getTextMuted0d7_KjU(), sp, medium, (FontStyle) null, (FontSynthesis) null, sansSerif, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646104, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return textStyle;
    }

    public static final TextStyle getAuroraCode(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1718802196);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraCode>)107@3349L7:AuroraTypography.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1718802196, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraCode> (AuroraTypography.kt:102)");
        }
        GenericFontFamily monospace = FontFamily.INSTANCE.getMonospace();
        FontWeight normal = FontWeight.INSTANCE.getNormal();
        long sp = TextUnitKt.getSp(13);
        long sp2 = TextUnitKt.getSp(19);
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextStyle textStyle = new TextStyle(((ThemeTokens) objConsume).m343getText0d7_KjU(), sp, normal, (FontStyle) null, (FontSynthesis) null, monospace, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646104, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return textStyle;
    }

    public static final Typography auroraTypography(ThemeTokens tokens) {
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        GenericFontFamily sansSerif = FontFamily.INSTANCE.getSansSerif();
        FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
        long sp = TextUnitKt.getSp(24);
        long sp2 = TextUnitKt.getSp(30);
        TextStyle textStyle = new TextStyle(tokens.m343getText0d7_KjU(), sp, semiBold, (FontStyle) null, (FontSynthesis) null, sansSerif, (String) null, TextUnitKt.getSp(-0.4d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp2, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        GenericFontFamily sansSerif2 = FontFamily.INSTANCE.getSansSerif();
        FontWeight semiBold2 = FontWeight.INSTANCE.getSemiBold();
        long sp3 = TextUnitKt.getSp(20);
        long sp4 = TextUnitKt.getSp(26);
        TextStyle textStyle2 = new TextStyle(tokens.m343getText0d7_KjU(), sp3, semiBold2, (FontStyle) null, (FontSynthesis) null, sansSerif2, (String) null, TextUnitKt.getSp(-0.3d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp4, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        GenericFontFamily sansSerif3 = FontFamily.INSTANCE.getSansSerif();
        FontWeight semiBold3 = FontWeight.INSTANCE.getSemiBold();
        long sp5 = TextUnitKt.getSp(16);
        long sp6 = TextUnitKt.getSp(22);
        TextStyle textStyle3 = new TextStyle(tokens.m343getText0d7_KjU(), sp5, semiBold3, (FontStyle) null, (FontSynthesis) null, sansSerif3, (String) null, TextUnitKt.getSp(-0.2d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp6, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        GenericFontFamily sansSerif4 = FontFamily.INSTANCE.getSansSerif();
        FontWeight normal = FontWeight.INSTANCE.getNormal();
        TextStyle textStyle4 = new TextStyle(tokens.m343getText0d7_KjU(), TextUnitKt.getSp(15), normal, (FontStyle) null, (FontSynthesis) null, sansSerif4, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(22), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646104, (DefaultConstructorMarker) null);
        GenericFontFamily sansSerif5 = FontFamily.INSTANCE.getSansSerif();
        FontWeight normal2 = FontWeight.INSTANCE.getNormal();
        TextStyle textStyle5 = new TextStyle(tokens.m346getTextMuted0d7_KjU(), TextUnitKt.getSp(14), normal2, (FontStyle) null, (FontSynthesis) null, sansSerif5, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(20), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646104, (DefaultConstructorMarker) null);
        GenericFontFamily monospace = FontFamily.INSTANCE.getMonospace();
        FontWeight medium = FontWeight.INSTANCE.getMedium();
        long sp7 = TextUnitKt.getSp(12);
        long sp8 = TextUnitKt.getSp(16);
        TextStyle textStyle6 = new TextStyle(tokens.m346getTextMuted0d7_KjU(), sp7, medium, (FontStyle) null, (FontSynthesis) null, monospace, (String) null, TextUnitKt.getSp(0.6d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, sp8, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645976, (DefaultConstructorMarker) null);
        GenericFontFamily sansSerif6 = FontFamily.INSTANCE.getSansSerif();
        FontWeight medium2 = FontWeight.INSTANCE.getMedium();
        return new Typography(textStyle, null, null, null, textStyle2, null, null, textStyle3, null, textStyle4, textStyle5, null, null, new TextStyle(tokens.m346getTextMuted0d7_KjU(), TextUnitKt.getSp(12), medium2, (FontStyle) null, (FontSynthesis) null, sansSerif6, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(16), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646104, (DefaultConstructorMarker) null), textStyle6, 6510, null);
    }
}
