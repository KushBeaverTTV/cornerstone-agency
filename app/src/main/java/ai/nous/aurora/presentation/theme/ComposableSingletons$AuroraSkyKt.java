package ai.nous.aurora.presentation.theme;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuroraSky.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$AuroraSkyKt {
    public static final ComposableSingletons$AuroraSkyKt INSTANCE = new ComposableSingletons$AuroraSkyKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3<BoxScope, Composer, Integer, Unit> f21lambda1 = (Function3) ComposableLambdaKt.composableLambdaInstance(1053266840, false, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.theme.ComposableSingletons$AuroraSkyKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer, Integer num) {
            invoke(boxScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(BoxScope boxScope, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(boxScope, "<this>");
            ComposerKt.sourceInformation($composer, "C:AuroraSky.kt#li0cm");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1053266840, $changed, -1, "ai.nous.aurora.presentation.theme.ComposableSingletons$AuroraSkyKt.lambda-1.<anonymous> (AuroraSky.kt:33)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_debug, reason: not valid java name */
    public final Function3<BoxScope, Composer, Integer, Unit> m247getLambda1$app_debug() {
        return f21lambda1;
    }
}
