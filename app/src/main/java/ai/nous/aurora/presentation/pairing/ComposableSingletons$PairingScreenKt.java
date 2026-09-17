package ai.nous.aurora.presentation.pairing;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PairingScreen.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$PairingScreenKt {
    public static final ComposableSingletons$PairingScreenKt INSTANCE = new ComposableSingletons$PairingScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3<ColumnScope, Composer, Integer, Unit> f0lambda1 = (Function3) ComposableLambdaKt.composableLambdaInstance(-1955148188, false, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.pairing.ComposableSingletons$PairingScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
            invoke(columnScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(ColumnScope AuroraCard, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(AuroraCard, "$this$AuroraCard");
            ComposerKt.sourceInformation($composer, "C169@7178L206,174@7401L20,175@7438L215,180@7670L20,181@7707L210:PairingScreen.kt#3dn6gp");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1955148188, $changed, -1, "ai.nous.aurora.presentation.pairing.ComposableSingletons$PairingScreenKt.lambda-1.<anonymous> (PairingScreen.kt:169)");
                }
                PairingScreenKt.PairingStep("01", "Paste the key once", "Minted on your gateway. It proves this phone belongs to you.", $composer, 438);
                PairingScreenKt.PairingStepDivider($composer, 0);
                PairingScreenKt.PairingStep("02", "Connect once", "Aurora seals the address and key to this device — nothing syncs to a cloud.", $composer, 438);
                PairingScreenKt.PairingStepDivider($composer, 0);
                PairingScreenKt.PairingStep("03", "Take the controls", "Chats, terminal and settings — the same Hermes, from your pocket.", $composer, 438);
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
    public final Function3<ColumnScope, Composer, Integer, Unit> m130getLambda1$app_debug() {
        return f0lambda1;
    }
}
