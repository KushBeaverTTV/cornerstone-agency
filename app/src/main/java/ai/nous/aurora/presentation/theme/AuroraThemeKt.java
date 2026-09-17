package ai.nous.aurora.presentation.theme;

import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.material3.Shapes;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuroraTheme.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00050\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\f"}, d2 = {"AuroraShapes", "Landroidx/compose/material3/Shapes;", "getAuroraShapes", "()Landroidx/compose/material3/Shapes;", "AuroraTheme", "", "skin", "Lai/nous/aurora/presentation/theme/AuroraSkin;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Lai/nous/aurora/presentation/theme/AuroraSkin;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AuroraThemeKt {
    private static final Shapes AuroraShapes = new Shapes(AuroraShapeKt.getAuroraShapeSm(), AuroraShapeKt.getAuroraShapeMd(), AuroraShapeKt.getAuroraShapeLg(), AuroraShapeKt.getAuroraShapeXl(), AuroraShapeKt.getAuroraShapePill());

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraTheme$lambda$0(AuroraSkin auroraSkin, Function2 function2, int i, int i2, Composer composer, int i3) {
        AuroraTheme(auroraSkin, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final Shapes getAuroraShapes() {
        return AuroraShapes;
    }

    public static final void AuroraTheme(final AuroraSkin skin, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1634037950);
        ComposerKt.sourceInformation($composer2, "C(AuroraTheme)P(1)34@1088L171,30@940L319:AuroraTheme.kt#li0cm");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ((i & 1) == 0 && $composer2.changed(skin)) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "26@848L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty &= -15;
                }
            } else if ((i & 1) != 0) {
                ProvidableCompositionLocal<AuroraSkin> localAuroraSkin = ThemeTokensKt.getLocalAuroraSkin();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localAuroraSkin);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                skin = (AuroraSkin) objConsume;
                $dirty &= -15;
            }
            int $dirty2 = $dirty;
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1634037950, $dirty2, -1, "ai.nous.aurora.presentation.theme.AuroraTheme (AuroraTheme.kt:28)");
            }
            final ThemeTokens tokens = ThemeTokensKt.themeTokensFor(skin);
            MaterialThemeKt.MaterialTheme(AuroraColorSchemeKt.auroraColorScheme(tokens), AuroraShapes, AuroraTypographyKt.auroraTypography(tokens), (Function2) ComposableLambdaKt.rememberComposableLambda(-1582685294, true, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.theme.AuroraThemeKt.AuroraTheme.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C35@1098L155:AuroraTheme.kt#li0cm");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1582685294, $changed2, -1, "ai.nous.aurora.presentation.theme.AuroraTheme.<anonymous> (AuroraTheme.kt:35)");
                        }
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ThemeTokensKt.getLocalThemeTokens().provides(tokens), ThemeTokensKt.getLocalAuroraSkin().provides(skin)}, content, $composer3, ProvidedValue.$stable);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, 3120, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.theme.AuroraThemeKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraThemeKt.AuroraTheme$lambda$0(skin, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
