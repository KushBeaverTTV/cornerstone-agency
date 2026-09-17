package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.domain.model.MoaEditorPresentationPolicy;
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

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$MoaEditorScreenKt {
    public static final ComposableSingletons$MoaEditorScreenKt INSTANCE = new ComposableSingletons$MoaEditorScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f5lambda1 = (Function3) ComposableLambdaKt.composableLambdaInstance(-227846815, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C285@11787L183:MoaEditorScreen.kt#syfnu6");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-227846815, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt.lambda-1.<anonymous> (MoaEditorScreen.kt:285)");
                }
                MoaEditorScreenKt.SectionHeader(MoaEditorPresentationPolicy.INSTANCE.sectionLabel(MoaEditorPresentationPolicy.Section.Preset), null, null, null, false, $composer, 48, 28);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f6lambda2 = (Function2) ComposableLambdaKt.composableLambdaInstance(547038040, false, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C335@14273L18:MoaEditorScreen.kt#syfnu6");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(547038040, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt.lambda-2.<anonymous> (MoaEditorScreen.kt:335)");
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("New preset", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f7lambda3 = (Function2) ComposableLambdaKt.composableLambdaInstance(-1922156351, false, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C361@15780L20:MoaEditorScreen.kt#syfnu6");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1922156351, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt.lambda-3.<anonymous> (MoaEditorScreen.kt:361)");
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Make default", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f8lambda4 = (Function2) ComposableLambdaKt.composableLambdaInstance(1070896864, false, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C369@16173L24:MoaEditorScreen.kt#syfnu6");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1070896864, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt.lambda-4.<anonymous> (MoaEditorScreen.kt:369)");
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Duplicate preset", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-5, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f9lambda5 = (Function2) ComposableLambdaKt.composableLambdaInstance(392071709, false, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C385@17035L21:MoaEditorScreen.kt#syfnu6");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(392071709, $changed, -1, "ai.nous.aurora.presentation.settings.ComposableSingletons$MoaEditorScreenKt.lambda-5.<anonymous> (MoaEditorScreen.kt:385)");
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Delete preset", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_debug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m139getLambda1$app_debug() {
        return f5lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m140getLambda2$app_debug() {
        return f6lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m141getLambda3$app_debug() {
        return f7lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m142getLambda4$app_debug() {
        return f8lambda4;
    }

    /* JADX INFO: renamed from: getLambda-5$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m143getLambda5$app_debug() {
        return f9lambda5;
    }
}
