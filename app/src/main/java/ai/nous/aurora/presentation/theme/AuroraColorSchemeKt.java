package ai.nous.aurora.presentation.theme;

import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuroraColorScheme.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b_\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020c\"\u0013\u0010\u0000\u001a\u00020\u0001¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0013\u0010\u0005\u001a\u00020\u0001¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0006\u0010\u0003\"\u0013\u0010\u0007\u001a\u00020\u0001¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\b\u0010\u0003\"\u0013\u0010\t\u001a\u00020\u0001¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\n\u0010\u0003\"\u0013\u0010\u000b\u001a\u00020\u0001¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\f\u0010\u0003\"\u0013\u0010\r\u001a\u00020\u0001¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u000e\u0010\u0003\"\u0013\u0010\u000f\u001a\u00020\u0001¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0010\u0010\u0003\"\u0011\u0010\u0011\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u0011\u0010\u0014\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013\"\u0011\u0010\u0016\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013\"\u0011\u0010\u0018\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013\"\u0011\u0010\u001a\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013\"\u0011\u0010\u001c\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013\"\u0011\u0010\u001e\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013\"\u0011\u0010 \u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b!\u0010\u0013\"\u0011\u0010\"\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b#\u0010\u0013\"\u0011\u0010$\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b%\u0010\u0013\"\u0011\u0010&\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b'\u0010\u0013\"\u0011\u0010(\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b)\u0010\u0013\"\u0011\u0010*\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b+\u0010\u0013\"\u0011\u0010,\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b-\u0010\u0013\"\u0011\u0010.\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b/\u0010\u0013\"\u0011\u00100\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b1\u0010\u0013\"\u0011\u00102\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b3\u0010\u0013\"\u0011\u00104\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b5\u0010\u0013\"\u0011\u00106\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b7\u0010\u0013\"\u0011\u00108\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b9\u0010\u0013\"\u0011\u0010:\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b;\u0010\u0013\"\u0011\u0010<\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b=\u0010\u0013\"\u0011\u0010>\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b?\u0010\u0013\"\u0011\u0010@\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bA\u0010\u0013\"\u0011\u0010B\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bC\u0010\u0013\"\u0011\u0010D\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bE\u0010\u0013\"\u0011\u0010F\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bG\u0010\u0013\"\u0011\u0010H\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bI\u0010\u0013\"\u0011\u0010J\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bK\u0010\u0013\"\u0011\u0010L\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bM\u0010\u0013\"\u0011\u0010N\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bO\u0010\u0013\"\u0011\u0010P\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bQ\u0010\u0013\"\u0011\u0010R\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bS\u0010\u0013\"\u0011\u0010T\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bU\u0010\u0013\"\u0011\u0010V\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bW\u0010\u0013\"\u0011\u0010X\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\bY\u0010\u0013\"\u0011\u0010Z\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b[\u0010\u0013\"\u0011\u0010\\\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b]\u0010\u0013\"\u0011\u0010^\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b_\u0010\u0013¨\u0006d"}, d2 = {"SourceTelegram", "Landroidx/compose/ui/graphics/Color;", "getSourceTelegram", "()J", "J", "SourceDiscord", "getSourceDiscord", "SourceCli", "getSourceCli", "SourceDesktop", "getSourceDesktop", "SourceAurora", "getSourceAurora", "SourceTool", "getSourceTool", "AuroraPillIdleBg", "getAuroraPillIdleBg", "AuroraVoid", "getAuroraVoid", "(Landroidx/compose/runtime/Composer;I)J", "AuroraBackground", "getAuroraBackground", "AuroraNavigation", "getAuroraNavigation", "AuroraComposer", "getAuroraComposer", "AuroraSurface", "getAuroraSurface", "AuroraSurfaceRaised", "getAuroraSurfaceRaised", "AuroraSurfaceHighlight", "getAuroraSurfaceHighlight", "AuroraSurfaceSelected", "getAuroraSurfaceSelected", "AuroraHairline", "getAuroraHairline", "AuroraText", "getAuroraText", "AuroraTextMuted", "getAuroraTextMuted", "AuroraTextFaint", "getAuroraTextFaint", "AuroraTextDisabled", "getAuroraTextDisabled", "AuroraMocha", "getAuroraMocha", "AuroraMochaDeep", "getAuroraMochaDeep", "AuroraAmber", "getAuroraAmber", "AuroraAmberSoft", "getAuroraAmberSoft", "AuroraAmberDeep", "getAuroraAmberDeep", "AuroraLightBrown", "getAuroraLightBrown", "AuroraAccent", "getAuroraAccent", "AuroraAccentSoft", "getAuroraAccentSoft", "AuroraAccentDim", "getAuroraAccentDim", "AuroraAccentStrong", "getAuroraAccentStrong", "AuroraOnAccent", "getAuroraOnAccent", "AuroraLive", "getAuroraLive", "AuroraLiveDim", "getAuroraLiveDim", "AuroraLiveSoft", "getAuroraLiveSoft", "AuroraOutline", "getAuroraOutline", "AuroraOutlineStrong", "getAuroraOutlineStrong", "AuroraError", "getAuroraError", "AuroraWarning", "getAuroraWarning", "AuroraSuccess", "getAuroraSuccess", "AuroraInfo", "getAuroraInfo", "AuroraSkyZenith", "getAuroraSkyZenith", "AuroraSkyHorizon", "getAuroraSkyHorizon", "AuroraDawnViolet", "getAuroraDawnViolet", "AuroraStarlight", "getAuroraStarlight", "AuroraGlassSurface", "getAuroraGlassSurface", "AuroraGlassHairline", "getAuroraGlassHairline", "auroraColorScheme", "Landroidx/compose/material3/ColorScheme;", "tokens", "Lai/nous/aurora/presentation/theme/ThemeTokens;", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AuroraColorSchemeKt {
    private static final long SourceTelegram = ColorKt.Color(4284195785L);
    private static final long SourceDiscord = ColorKt.Color(4286284500L);
    private static final long SourceCli = ColorKt.Color(4287609999L);
    private static final long SourceDesktop = ColorKt.Color(4291077252L);
    private static final long SourceAurora = ColorKt.Color(4288968804L);
    private static final long SourceTool = ColorKt.Color(4289765520L);
    private static final long AuroraPillIdleBg = ColorKt.Color(587202559);

    public static final long getSourceTelegram() {
        return SourceTelegram;
    }

    public static final long getSourceDiscord() {
        return SourceDiscord;
    }

    public static final long getSourceCli() {
        return SourceCli;
    }

    public static final long getSourceDesktop() {
        return SourceDesktop;
    }

    public static final long getSourceAurora() {
        return SourceAurora;
    }

    public static final long getSourceTool() {
        return SourceTool;
    }

    public static final long getAuroraPillIdleBg() {
        return AuroraPillIdleBg;
    }

    public static final long getAuroraVoid(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1199709436);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraVoid>)28@1131L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1199709436, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraVoid> (AuroraColorScheme.kt:28)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM349getVoidColor0d7_KjU = ((ThemeTokens) objConsume).m349getVoidColor0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM349getVoidColor0d7_KjU;
    }

    public static final long getAuroraBackground(Composer $composer, int $changed) {
        $composer.startReplaceGroup(360132676);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraBackground>)29@1214L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(360132676, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraBackground> (AuroraColorScheme.kt:29)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM315getBackground0d7_KjU = ((ThemeTokens) objConsume).m315getBackground0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM315getBackground0d7_KjU;
    }

    public static final long getAuroraNavigation(Composer $composer, int $changed) {
        $composer.startReplaceGroup(125953284);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraNavigation>)30@1298L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(125953284, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraNavigation> (AuroraColorScheme.kt:30)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM331getNavigation0d7_KjU = ((ThemeTokens) objConsume).m331getNavigation0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM331getNavigation0d7_KjU;
    }

    public static final long getAuroraComposer(Composer $composer, int $changed) {
        $composer.startReplaceGroup(885874052);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraComposer>)31@1380L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(885874052, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraComposer> (AuroraColorScheme.kt:31)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM318getComposer0d7_KjU = ((ThemeTokens) objConsume).m318getComposer0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM318getComposer0d7_KjU;
    }

    public static final long getAuroraSurface(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-2030575630);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraSurface>)32@1459L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2030575630, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraSurface> (AuroraColorScheme.kt:32)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM339getSurface0d7_KjU = ((ThemeTokens) objConsume).m339getSurface0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM339getSurface0d7_KjU;
    }

    public static final long getAuroraSurfaceRaised(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1584585442);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraSurfaceRaised>)33@1543L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1584585442, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraSurfaceRaised> (AuroraColorScheme.kt:33)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM341getSurfaceRaised0d7_KjU = ((ThemeTokens) objConsume).m341getSurfaceRaised0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM341getSurfaceRaised0d7_KjU;
    }

    public static final long getAuroraSurfaceHighlight(Composer $composer, int $changed) {
        $composer.startReplaceGroup(903760804);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraSurfaceHighlight>)34@1636L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(903760804, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraSurfaceHighlight> (AuroraColorScheme.kt:34)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM340getSurfaceHighlight0d7_KjU = ((ThemeTokens) objConsume).m340getSurfaceHighlight0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM340getSurfaceHighlight0d7_KjU;
    }

    public static final long getAuroraSurfaceSelected(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1228295640);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraSurfaceSelected>)35@1731L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1228295640, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraSurfaceSelected> (AuroraColorScheme.kt:35)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM342getSurfaceSelected0d7_KjU = ((ThemeTokens) objConsume).m342getSurfaceSelected0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM342getSurfaceSelected0d7_KjU;
    }

    public static final long getAuroraHairline(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1018373828);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraHairline>)36@1818L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1018373828, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraHairline> (AuroraColorScheme.kt:36)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM323getHairline0d7_KjU = ((ThemeTokens) objConsume).m323getHairline0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM323getHairline0d7_KjU;
    }

    public static final long getAuroraText(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-130520220);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraText>)39@1973L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-130520220, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraText> (AuroraColorScheme.kt:39)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM343getText0d7_KjU = ((ThemeTokens) objConsume).m343getText0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM343getText0d7_KjU;
    }

    public static final long getAuroraTextMuted(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1964410836);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraTextMuted>)40@2050L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1964410836, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraTextMuted> (AuroraColorScheme.kt:40)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM346getTextMuted0d7_KjU = ((ThemeTokens) objConsume).m346getTextMuted0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM346getTextMuted0d7_KjU;
    }

    public static final long getAuroraTextFaint(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1721414746);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraTextFaint>)41@2132L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1721414746, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraTextFaint> (AuroraColorScheme.kt:41)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM345getTextFaint0d7_KjU = ((ThemeTokens) objConsume).m345getTextFaint0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM345getTextFaint0d7_KjU;
    }

    public static final long getAuroraTextDisabled(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1387936028);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraTextDisabled>)42@2217L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1387936028, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraTextDisabled> (AuroraColorScheme.kt:42)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM344getTextDisabled0d7_KjU = ((ThemeTokens) objConsume).m344getTextDisabled0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM344getTextDisabled0d7_KjU;
    }

    public static final long getAuroraMocha(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-722125876);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraMocha>)45@2377L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-722125876, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraMocha> (AuroraColorScheme.kt:45)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM329getMocha0d7_KjU = ((ThemeTokens) objConsume).m329getMocha0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM329getMocha0d7_KjU;
    }

    public static final long getAuroraMochaDeep(Composer $composer, int $changed) {
        $composer.startReplaceGroup(648806116);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraMochaDeep>)46@2455L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(648806116, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraMochaDeep> (AuroraColorScheme.kt:46)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM330getMochaDeep0d7_KjU = ((ThemeTokens) objConsume).m330getMochaDeep0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM330getMochaDeep0d7_KjU;
    }

    public static final long getAuroraAmber(Composer $composer, int $changed) {
        $composer.startReplaceGroup(116242078);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraAmber>)47@2533L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(116242078, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraAmber> (AuroraColorScheme.kt:47)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM312getAmber0d7_KjU = ((ThemeTokens) objConsume).m312getAmber0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM312getAmber0d7_KjU;
    }

    public static final long getAuroraAmberSoft(Composer $composer, int $changed) {
        $composer.startReplaceGroup(16358066);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraAmberSoft>)48@2611L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(16358066, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraAmberSoft> (AuroraColorScheme.kt:48)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM314getAmberSoft0d7_KjU = ((ThemeTokens) objConsume).m314getAmberSoft0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM314getAmberSoft0d7_KjU;
    }

    public static final long getAuroraAmberDeep(Composer $composer, int $changed) {
        $composer.startReplaceGroup(372900918);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraAmberDeep>)49@2693L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(372900918, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraAmberDeep> (AuroraColorScheme.kt:49)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM313getAmberDeep0d7_KjU = ((ThemeTokens) objConsume).m313getAmberDeep0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM313getAmberDeep0d7_KjU;
    }

    public static final long getAuroraLightBrown(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1146385788);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraLightBrown>)50@2776L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1146385788, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraLightBrown> (AuroraColorScheme.kt:50)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM325getLightBrown0d7_KjU = ((ThemeTokens) objConsume).m325getLightBrown0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM325getLightBrown0d7_KjU;
    }

    public static final long getAuroraAccent(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-67257148);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraAccent>)51@2856L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-67257148, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraAccent> (AuroraColorScheme.kt:51)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM308getAccent0d7_KjU = ((ThemeTokens) objConsume).m308getAccent0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM308getAccent0d7_KjU;
    }

    public static final long getAuroraAccentSoft(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1714267396);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraAccentSoft>)52@2936L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1714267396, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraAccentSoft> (AuroraColorScheme.kt:52)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM310getAccentSoft0d7_KjU = ((ThemeTokens) objConsume).m310getAccentSoft0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM310getAccentSoft0d7_KjU;
    }

    public static final long getAuroraAccentDim(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1351319060);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraAccentDim>)53@3019L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1351319060, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraAccentDim> (AuroraColorScheme.kt:53)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM309getAccentDim0d7_KjU = ((ThemeTokens) objConsume).m309getAccentDim0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM309getAccentDim0d7_KjU;
    }

    public static final long getAuroraAccentStrong(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1330936804);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraAccentStrong>)54@3104L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1330936804, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraAccentStrong> (AuroraColorScheme.kt:54)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM311getAccentStrong0d7_KjU = ((ThemeTokens) objConsume).m311getAccentStrong0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM311getAccentStrong0d7_KjU;
    }

    public static final long getAuroraOnAccent(Composer $composer, int $changed) {
        $composer.startReplaceGroup(794251364);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraOnAccent>)55@3188L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(794251364, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraOnAccent> (AuroraColorScheme.kt:55)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM332getOnAccent0d7_KjU = ((ThemeTokens) objConsume).m332getOnAccent0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM332getOnAccent0d7_KjU;
    }

    public static final long getAuroraLive(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1600982020);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraLive>)58@3343L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1600982020, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraLive> (AuroraColorScheme.kt:58)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM326getLive0d7_KjU = ((ThemeTokens) objConsume).m326getLive0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM326getLive0d7_KjU;
    }

    public static final long getAuroraLiveDim(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1869288880);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraLiveDim>)59@3418L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1869288880, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraLiveDim> (AuroraColorScheme.kt:59)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM327getLiveDim0d7_KjU = ((ThemeTokens) objConsume).m327getLiveDim0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM327getLiveDim0d7_KjU;
    }

    public static final long getAuroraLiveSoft(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-372729148);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraLiveSoft>)60@3497L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-372729148, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraLiveSoft> (AuroraColorScheme.kt:60)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM328getLiveSoft0d7_KjU = ((ThemeTokens) objConsume).m328getLiveSoft0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM328getLiveSoft0d7_KjU;
    }

    public static final long getAuroraOutline(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1656286116);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraOutline>)61@3576L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1656286116, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraOutline> (AuroraColorScheme.kt:61)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM333getOutline0d7_KjU = ((ThemeTokens) objConsume).m333getOutline0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM333getOutline0d7_KjU;
    }

    public static final long getAuroraOutlineStrong(Composer $composer, int $changed) {
        $composer.startReplaceGroup(214786506);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraOutlineStrong>)62@3660L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(214786506, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraOutlineStrong> (AuroraColorScheme.kt:62)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM334getOutlineStrong0d7_KjU = ((ThemeTokens) objConsume).m334getOutlineStrong0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM334getOutlineStrong0d7_KjU;
    }

    public static final long getAuroraError(Composer $composer, int $changed) {
        $composer.startReplaceGroup(2140712808);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraError>)63@3742L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2140712808, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraError> (AuroraColorScheme.kt:63)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM320getError0d7_KjU = ((ThemeTokens) objConsume).m320getError0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM320getError0d7_KjU;
    }

    public static final long getAuroraWarning(Composer $composer, int $changed) {
        $composer.startReplaceGroup(900131216);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraWarning>)64@3818L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(900131216, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraWarning> (AuroraColorScheme.kt:64)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM350getWarning0d7_KjU = ((ThemeTokens) objConsume).m350getWarning0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM350getWarning0d7_KjU;
    }

    public static final long getAuroraSuccess(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1195955170);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraSuccess>)65@3896L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1195955170, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraSuccess> (AuroraColorScheme.kt:65)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM338getSuccess0d7_KjU = ((ThemeTokens) objConsume).m338getSuccess0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM338getSuccess0d7_KjU;
    }

    public static final long getAuroraInfo(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1848033988);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraInfo>)66@3971L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1848033988, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraInfo> (AuroraColorScheme.kt:66)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM324getInfo0d7_KjU = ((ThemeTokens) objConsume).m324getInfo0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM324getInfo0d7_KjU;
    }

    public static final long getAuroraSkyZenith(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1943696014);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraSkyZenith>)69@4127L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1943696014, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraSkyZenith> (AuroraColorScheme.kt:69)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM336getSkyZenith0d7_KjU = ((ThemeTokens) objConsume).m336getSkyZenith0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM336getSkyZenith0d7_KjU;
    }

    public static final long getAuroraSkyHorizon(Composer $composer, int $changed) {
        $composer.startReplaceGroup(1335845316);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraSkyHorizon>)70@4210L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1335845316, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraSkyHorizon> (AuroraColorScheme.kt:70)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM335getSkyHorizon0d7_KjU = ((ThemeTokens) objConsume).m335getSkyHorizon0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM335getSkyHorizon0d7_KjU;
    }

    public static final long getAuroraDawnViolet(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-454388508);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraDawnViolet>)71@4294L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-454388508, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraDawnViolet> (AuroraColorScheme.kt:71)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM319getDawnViolet0d7_KjU = ((ThemeTokens) objConsume).m319getDawnViolet0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM319getDawnViolet0d7_KjU;
    }

    public static final long getAuroraStarlight(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-116239520);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraStarlight>)72@4377L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-116239520, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraStarlight> (AuroraColorScheme.kt:72)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM337getStarlight0d7_KjU = ((ThemeTokens) objConsume).m337getStarlight0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM337getStarlight0d7_KjU;
    }

    public static final long getAuroraGlassSurface(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-799239708);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraGlassSurface>)73@4462L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-799239708, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraGlassSurface> (AuroraColorScheme.kt:73)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM322getGlassSurface0d7_KjU = ((ThemeTokens) objConsume).m322getGlassSurface0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM322getGlassSurface0d7_KjU;
    }

    public static final long getAuroraGlassHairline(Composer $composer, int $changed) {
        $composer.startReplaceGroup(19391292);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraGlassHairline>)74@4551L7:AuroraColorScheme.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(19391292, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraGlassHairline> (AuroraColorScheme.kt:74)");
        }
        ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localThemeTokens);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long jM321getGlassHairline0d7_KjU = ((ThemeTokens) objConsume).m321getGlassHairline0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return jM321getGlassHairline0d7_KjU;
    }

    public static final ColorScheme auroraColorScheme(ThemeTokens tokens) {
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        long jM308getAccent0d7_KjU = tokens.m308getAccent0d7_KjU();
        long jM332getOnAccent0d7_KjU = tokens.m332getOnAccent0d7_KjU();
        long jM309getAccentDim0d7_KjU = tokens.m309getAccentDim0d7_KjU();
        long jM343getText0d7_KjU = tokens.m343getText0d7_KjU();
        long jM312getAmber0d7_KjU = tokens.m312getAmber0d7_KjU();
        long jM332getOnAccent0d7_KjU2 = tokens.m332getOnAccent0d7_KjU();
        long jM340getSurfaceHighlight0d7_KjU = tokens.m340getSurfaceHighlight0d7_KjU();
        long jM343getText0d7_KjU2 = tokens.m343getText0d7_KjU();
        long jM325getLightBrown0d7_KjU = tokens.m325getLightBrown0d7_KjU();
        long jM332getOnAccent0d7_KjU3 = tokens.m332getOnAccent0d7_KjU();
        long jM315getBackground0d7_KjU = tokens.m315getBackground0d7_KjU();
        long jM343getText0d7_KjU3 = tokens.m343getText0d7_KjU();
        long jM339getSurface0d7_KjU = tokens.m339getSurface0d7_KjU();
        long jM343getText0d7_KjU4 = tokens.m343getText0d7_KjU();
        long jM340getSurfaceHighlight0d7_KjU2 = tokens.m340getSurfaceHighlight0d7_KjU();
        long jM346getTextMuted0d7_KjU = tokens.m346getTextMuted0d7_KjU();
        long jM4569getTransparent0d7_KjU = ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion);
        long jM343getText0d7_KjU5 = tokens.m343getText0d7_KjU();
        long jM349getVoidColor0d7_KjU = tokens.m349getVoidColor0d7_KjU();
        long jM309getAccentDim0d7_KjU2 = tokens.m309getAccentDim0d7_KjU();
        long jM333getOutline0d7_KjU = tokens.m333getOutline0d7_KjU();
        long jM323getHairline0d7_KjU = tokens.m323getHairline0d7_KjU();
        long jM320getError0d7_KjU = tokens.m320getError0d7_KjU();
        long jM332getOnAccent0d7_KjU4 = tokens.m332getOnAccent0d7_KjU();
        long jM320getError0d7_KjU2 = tokens.m320getError0d7_KjU();
        return ai.nous.aurora.compat.Compat.m2306darkColorSchemeCXl9yA$default(jM308getAccent0d7_KjU, jM332getOnAccent0d7_KjU, jM309getAccentDim0d7_KjU, jM343getText0d7_KjU, jM309getAccentDim0d7_KjU2, jM312getAmber0d7_KjU, jM332getOnAccent0d7_KjU2, jM340getSurfaceHighlight0d7_KjU, jM343getText0d7_KjU2, jM325getLightBrown0d7_KjU, jM332getOnAccent0d7_KjU3, 0L, 0L, jM315getBackground0d7_KjU, jM343getText0d7_KjU3, jM339getSurface0d7_KjU, jM343getText0d7_KjU4, jM340getSurfaceHighlight0d7_KjU2, jM346getTextMuted0d7_KjU, jM4569getTransparent0d7_KjU, jM343getText0d7_KjU5, jM349getVoidColor0d7_KjU, jM320getError0d7_KjU, jM332getOnAccent0d7_KjU4, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM320getError0d7_KjU2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM320getError0d7_KjU2) : 0.24f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM320getError0d7_KjU2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM320getError0d7_KjU2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM320getError0d7_KjU2) : 0.0f), tokens.m343getText0d7_KjU(), jM333getOutline0d7_KjU, jM323getHairline0d7_KjU, ColorKt.Color(3003121664L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, -536864768, 15, null);
    }
}
