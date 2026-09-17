package ai.nous.aurora.presentation.settings;

import android.content.Context;
import android.content.Intent;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import com.termux.terminal.KeyHandler;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssistantSettingsPane.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$6 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ Context $context;

    AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$6(Context context) {
        this.$context = context;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(final LazyItemScope item, Composer $composer, int $changed) {
        Object value$iv;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C221@9194L645,218@9015L843:AssistantSettingsPane.kt#syfnu6");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(item) ? 4 : 2;
        }
        if (($dirty & 19) != 18 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(997071504, $dirty, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AssistantSettingsPane.kt:218)");
            }
            $composer.startReplaceGroup(-1518029266);
            ComposerKt.sourceInformation($composer, "CC(remember):AssistantSettingsPane.kt#9igjgp");
            boolean invalid$iv = (($dirty & 14) == 4) | $composer.changedInstance(this.$context);
            final Context context = this.$context;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$6$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$6.invoke$lambda$2$lambda$1(item, context);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceGroup();
            AssistantSettingsPaneKt.AssistActionRow("System Digital assistant settings", "Set Aurora as default assist app", (Function0) value$iv, $composer, 54);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$2$lambda$1(LazyItemScope $this_item, Context $context) {
        Object objM7388constructorimpl;
        List<Intent> intents = CollectionsKt.listOf(new Intent[]{new Intent("android.settings.VOICE_INPUT_SETTINGS"), new Intent("android.settings.MANAGE_DEFAULT_APPS_SETTINGS"), new Intent("android.settings.SETTINGS")});
        for (Intent intent : intents) {
            try {
                Result.Companion companion = Result.Companion;
                $context.startActivity(intent.addFlags(KeyHandler.KEYMOD_NUM_LOCK));
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
            }
            boolean ok = ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objM7388constructorimpl);
            if (ok) {
                break;
            }
        }
        return Unit.INSTANCE;
    }
}
