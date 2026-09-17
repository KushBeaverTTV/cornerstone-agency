package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.AssistActivity;
import android.content.Context;
import android.content.Intent;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import com.termux.terminal.KeyHandler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssistantSettingsPane.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$5 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ Context $context;

    AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$5(Context context) {
        this.$context = context;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Object value$iv;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C209@8704L242,206@8546L419:AssistantSettingsPane.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(548955919, $changed, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AssistantSettingsPane.kt:206)");
            }
            $composer.startReplaceGroup(-1518045349);
            ComposerKt.sourceInformation($composer, "CC(remember):AssistantSettingsPane.kt#9igjgp");
            boolean invalid$iv = $composer.changedInstance(this.$context);
            final Context context = this.$context;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$5$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$5.invoke$lambda$1$lambda$0(context);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceGroup();
            AssistantSettingsPaneKt.AssistActionRow("Open Assist now", "Test orb without power button", (Function0) value$iv, $composer, 54);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(Context $context) {
        $context.startActivity(new Intent($context, (Class<?>) AssistActivity.class).addFlags(KeyHandler.KEYMOD_NUM_LOCK));
        return Unit.INSTANCE;
    }
}
