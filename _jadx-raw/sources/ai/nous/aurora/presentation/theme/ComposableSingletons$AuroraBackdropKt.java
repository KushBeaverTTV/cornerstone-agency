package ai.nous.aurora.presentation.theme;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuroraBackdrop.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$AuroraBackdropKt {
    public static final ComposableSingletons$AuroraBackdropKt INSTANCE = new ComposableSingletons$AuroraBackdropKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3<BoxScope, Composer, Integer, Unit> f20lambda1 = ComposableLambdaKt.composableLambdaInstance(207727914, false, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.theme.ComposableSingletons$AuroraBackdropKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer, Integer num) {
            invoke(boxScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(BoxScope boxScope, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(boxScope, "<this>");
            ComposerKt.sourceInformation($composer, "C:AuroraBackdrop.kt#li0cm");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(207727914, $changed, -1, "ai.nous.aurora.presentation.theme.ComposableSingletons$AuroraBackdropKt.lambda-1.<anonymous> (AuroraBackdrop.kt:23)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_debug, reason: not valid java name */
    public final Function3<BoxScope, Composer, Integer, Unit> m246getLambda1$app_debug() {
        return f20lambda1;
    }
}
