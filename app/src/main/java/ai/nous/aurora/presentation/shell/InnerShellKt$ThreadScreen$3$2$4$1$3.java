package ai.nous.aurora.presentation.shell;

import ai.nous.aurora.MainViewModel;
import ai.nous.aurora.domain.model.AuroraMessage;
import ai.nous.aurora.presentation.components.AuroraComponentsKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InnerShell.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class InnerShellKt$ThreadScreen$3$2$4$1$3 implements Function4<LazyItemScope, Integer, Composer, Integer, Unit> {
    final /* synthetic */ Function1<? super String, Unit> $onRetryMessage;
    final /* synthetic */ MainViewModel.UiState $state;

    /* JADX WARN: Multi-variable type inference failed */
    InnerShellKt$ThreadScreen$3$2$4$1$3(MainViewModel.UiState uiState, Function1<? super String, Unit> function1) {
        this.$state = uiState;
        this.$onRetryMessage = function1;
    }

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
        invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope items, int idx, Composer $composer, int $changed) {
        Object value$iv;
        Intrinsics.checkNotNullParameter(items, "$this$items");
        ComposerKt.sourceInformation($composer, "C1215@54499L311:InnerShell.kt#m1syn");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            $dirty |= $composer.changed(idx) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 145) != 144 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1371521645, $dirty2, -1, "ai.nous.aurora.presentation.shell.ThreadScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (InnerShell.kt:1214)");
            }
            AuroraMessage msg = this.$state.getMessages().get(idx);
            String role = msg.getRole();
            String content = msg.getContent();
            String deliveryStatus = msg.getDeliveryStatus();
            final String id = msg.getId();
            Function0 function0 = null;
            if (id == null || !msg.isFailed()) {
                id = null;
            }
            $composer.startReplaceGroup(621923278);
            ComposerKt.sourceInformation($composer, "*1220@54747L22");
            if (id != null) {
                final Function1<? super String, Unit> function1 = this.$onRetryMessage;
                $composer.startReplaceGroup(1104876819);
                ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                boolean invalid$iv = $composer.changed(function1) | $composer.changed(id);
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ThreadScreen$3$2$4$1$3$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return InnerShellKt$ThreadScreen$3$2$4$1$3.invoke$lambda$3$lambda$2$lambda$1(function1, id);
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer.endReplaceGroup();
                function0 = (Function0) value$iv;
            }
            $composer.endReplaceGroup();
            AuroraComponentsKt.MessageBubble(role, content, null, deliveryStatus, function0, $composer, 0, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$3$lambda$2$lambda$1(Function1 $onRetryMessage, String $id) {
        $onRetryMessage.invoke($id);
        return Unit.INSTANCE;
    }
}
