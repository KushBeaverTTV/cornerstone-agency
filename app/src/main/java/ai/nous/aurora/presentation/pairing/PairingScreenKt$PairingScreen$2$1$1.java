package ai.nous.aurora.presentation.pairing;

import ai.nous.aurora.MainViewModel;
import ai.nous.aurora.domain.model.PairingKeyStatusPolicy;
import ai.nous.aurora.presentation.components.AuroraComponentsKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraTypographyKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PairingScreen.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class PairingScreenKt$PairingScreen$2$1$1 implements Function3<ColumnScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<Boolean> $advancedOpen$delegate;
    final /* synthetic */ MainViewModel.ConnectUiState $connect;
    final /* synthetic */ boolean $keyEntered;
    final /* synthetic */ Function1<? super String, Unit> $onApiKeyChange;
    final /* synthetic */ Function0<Unit> $onConnect;
    final /* synthetic */ Function1<? super String, Unit> $onGatewayChange;
    final /* synthetic */ Function0<Unit> $onRestorePreconfig;

    /* JADX WARN: Multi-variable type inference failed */
    PairingScreenKt$PairingScreen$2$1$1(MainViewModel.ConnectUiState connectUiState, Function1<? super String, Unit> function1, Function0<Unit> function0, Function1<? super String, Unit> function2, boolean z, Function0<Unit> function3, MutableState<Boolean> mutableState) {
        this.$connect = connectUiState;
        this.$onApiKeyChange = function1;
        this.$onConnect = function0;
        this.$onGatewayChange = function2;
        this.$keyEntered = z;
        this.$onRestorePreconfig = function3;
        this.$advancedOpen$delegate = mutableState;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
        invoke(columnScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(ColumnScope AuroraCard, Composer $composer, int $changed) {
        Object value$iv;
        Intrinsics.checkNotNullParameter(AuroraCard, "$this$AuroraCard");
        ComposerKt.sourceInformation($composer, "C97@4242L371,114@4980L161,120@5159L408,134@5768L32,132@5676L143,138@5877L250,151@6435L294:PairingScreen.kt#3dn6gp");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(271765563, $changed, -1, "ai.nous.aurora.presentation.pairing.PairingScreen.<anonymous>.<anonymous>.<anonymous> (PairingScreen.kt:97)");
            }
            AuroraComponentsKt.AuroraTextField(this.$connect.getApiKey(), this.$onApiKeyChange, null, "Paste your key once", true, !this.$connect.getBusy(), null, new PasswordVisualTransformation('•'), null, null, $composer, 27648, 836);
            String keyStatus = PairingKeyStatusPolicy.INSTANCE.keyStatusLabel("", this.$connect.getApiKey());
            boolean keyStatusLive = PairingKeyStatusPolicy.INSTANCE.hasCredential("", this.$connect.getApiKey());
            PairingScreenKt.RowLabel("SECURE CONNECTION", keyStatus, keyStatusLive, $composer, 6, 0);
            AuroraComponentsKt.AuroraButton("Connect to Hermes  →", this.$onConnect, ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), PairingKeyStatusPolicy.INSTANCE.connectEnabled(this.$connect.getBusy(), "", this.$connect.getApiKey()), false, $composer, 390, 16);
            boolean zPairingScreen$lambda$2 = PairingScreenKt.PairingScreen$lambda$2(this.$advancedOpen$delegate);
            $composer.startReplaceGroup(2038705628);
            ComposerKt.sourceInformation($composer, "CC(remember):PairingScreen.kt#9igjgp");
            final MutableState<Boolean> mutableState = this.$advancedOpen$delegate;
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.pairing.PairingScreenKt$PairingScreen$2$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PairingScreenKt$PairingScreen$2$1$1.invoke$lambda$1$lambda$0(mutableState);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceGroup();
            PairingScreenKt.AdvancedDisclosure(zPairingScreen$lambda$2, (Function0) value$iv, $composer, 48);
            if (PairingScreenKt.PairingScreen$lambda$2(this.$advancedOpen$delegate)) {
                AuroraComponentsKt.AuroraTextField(this.$connect.getGatewayAddress(), this.$onGatewayChange, null, "Hermes address", false, !this.$connect.getBusy(), null, null, null, null, $composer, 3072, 980);
                $composer.startReplaceGroup(2038718022);
                ComposerKt.sourceInformation($composer, "147@6302L10,148@6350L15,145@6190L202");
                if (this.$keyEntered) {
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Using the key you pasted above.", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMeta($composer, 0), $composer, 6, 0, 65530);
                }
                $composer.endReplaceGroup();
                AuroraComponentsKt.AuroraButton("Use saved connection", this.$onRestorePreconfig, ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), !this.$connect.getBusy(), false, $composer, 24966, 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(MutableState $advancedOpen$delegate) {
        PairingScreenKt.PairingScreen$lambda$3($advancedOpen$delegate, !PairingScreenKt.PairingScreen$lambda$2($advancedOpen$delegate));
        return Unit.INSTANCE;
    }
}
