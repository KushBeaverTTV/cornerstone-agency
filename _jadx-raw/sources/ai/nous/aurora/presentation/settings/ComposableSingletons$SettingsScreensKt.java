package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.presentation.components.ds.AuroraDsKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsScreens.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$SettingsScreensKt {
    public static final ComposableSingletons$SettingsScreensKt INSTANCE = new ComposableSingletons$SettingsScreensKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f10lambda1 = ComposableLambdaKt.composableLambdaInstance(-366211794, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C397@16824L36:SettingsScreens.kt#syfnu6");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-366211794, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt.lambda-1.<anonymous> (SettingsScreens.kt:397)");
            }
            AuroraDsKt.AuroraSectionLabel("Search results", null, $composer, 6, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f11lambda2 = ComposableLambdaKt.composableLambdaInstance(940116745, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C400@16946L152:SettingsScreens.kt#syfnu6");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(940116745, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt.lambda-2.<anonymous> (SettingsScreens.kt:400)");
                }
                AuroraDsKt.AuroraEmptyState("No matches", "Try moa, model, turns, telegram…", null, $composer, 54, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f12lambda3 = ComposableLambdaKt.composableLambdaInstance(1037429536, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C902@41846L11,898@41522L354:SettingsScreens.kt#syfnu6");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1037429536, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt.lambda-3.<anonymous> (SettingsScreens.kt:898)");
                }
                SettingsScreensKt.m188SettingsReadingPlaneBx497Mc("YOUR DEFAULT WAY OF WORKING", "Capable, steady, and accountable", "Start with the essentials. Open the deeper groups only when you want to tune how Hermes handles long or complex work.", AuroraColorSchemeKt.getAuroraAmber($composer, 0), $composer, 438);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f13lambda4 = ComposableLambdaKt.composableLambdaInstance(-627301455, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C1108@50543L11,1104@50251L322:SettingsScreens.kt#syfnu6");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-627301455, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt.lambda-4.<anonymous> (SettingsScreens.kt:1104)");
                }
                SettingsScreensKt.m188SettingsReadingPlaneBx497Mc("DEEP CONFIGURATION", "Tune one area at a time", "Your live choices stay visible in each row. Open a group when you need it; everything else stays quiet.", AuroraColorSchemeKt.getAuroraAmber($composer, 0), $composer, 438);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-5, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f14lambda5 = ComposableLambdaKt.composableLambdaInstance(-181694716, false, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1405@63627L10,1405@63591L47:SettingsScreens.kt#syfnu6");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-181694716, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt.lambda-5.<anonymous> (SettingsScreens.kt:1405)");
            }
            TextKt.m3067Text4IGK_g("Sign out of Aurora?", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-6, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f15lambda6 = ComposableLambdaKt.composableLambdaInstance(-1218811293, false, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1409@63865L15,1407@63679L220:SettingsScreens.kt#syfnu6");
            if (($changed & 3) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1218811293, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$SettingsScreensKt.lambda-6.<anonymous> (SettingsScreens.kt:1407)");
                }
                TextKt.m3067Text4IGK_g("This clears the private connection from this phone. You'll need to reconnect before Chats, Terminal or Control can reach Hermes.", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131066);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_debug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m144getLambda1$app_debug() {
        return f10lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_debug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m145getLambda2$app_debug() {
        return f11lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_debug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m146getLambda3$app_debug() {
        return f12lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$app_debug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m147getLambda4$app_debug() {
        return f13lambda4;
    }

    /* JADX INFO: renamed from: getLambda-5$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m148getLambda5$app_debug() {
        return f14lambda5;
    }

    /* JADX INFO: renamed from: getLambda-6$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m149getLambda6$app_debug() {
        return f15lambda6;
    }
}
