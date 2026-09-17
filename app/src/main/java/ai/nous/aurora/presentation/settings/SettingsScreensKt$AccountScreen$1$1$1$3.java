package ai.nous.aurora.presentation.settings;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsScreens.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class SettingsScreensKt$AccountScreen$1$1$1$3 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<Boolean> $confirmSignOut$delegate;

    SettingsScreensKt$AccountScreen$1$1$1$3(MutableState<Boolean> mutableState) {
        this.$confirmSignOut$delegate = mutableState;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(MutableState $confirmSignOut$delegate) {
        SettingsScreensKt.AccountScreen$lambda$141($confirmSignOut$delegate, true);
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Object value$iv;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C1398@63379L25,1398@63351L54:SettingsScreens.kt#syfnu6");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-844781577, $changed, -1, "ai.nous.aurora.presentation.settings.AccountScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingsScreens.kt:1398)");
        }
        $composer.startReplaceGroup(77727154);
        ComposerKt.sourceInformation($composer, "CC(remember):SettingsScreens.kt#9igjgp");
        final MutableState<Boolean> mutableState = this.$confirmSignOut$delegate;
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.Companion.getEmpty()) {
            value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.SettingsScreensKt$AccountScreen$1$1$1$3$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SettingsScreensKt$AccountScreen$1$1$1$3.invoke$lambda$1$lambda$0(mutableState);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        $composer.endReplaceGroup();
        SettingsScreensKt.DangerSignOutCard((Function0) value$iv, $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
