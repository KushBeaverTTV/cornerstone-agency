package ai.nous.aurora.presentation.shell;

import ai.nous.aurora.MainViewModel;
import androidx.compose.foundation.layout.FlowRowScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InnerShell.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class InnerShellKt$InboxPane$2$2 implements Function3<FlowRowScope, Composer, Integer, Unit> {
    final /* synthetic */ boolean $astral;
    final /* synthetic */ List<SourceFilter> $filters;
    final /* synthetic */ Function1<String, Unit> $onSourceFilter;
    final /* synthetic */ MainViewModel.UiState $state;

    /* JADX WARN: Multi-variable type inference failed */
    InnerShellKt$InboxPane$2$2(List<SourceFilter> list, MainViewModel.UiState uiState, boolean z, Function1<? super String, Unit> function1) {
        this.$filters = list;
        this.$state = uiState;
        this.$astral = z;
        this.$onSourceFilter = function1;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(FlowRowScope flowRowScope, Composer composer, Integer num) {
        invoke(flowRowScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(FlowRowScope FlowRow, Composer $composer, int $changed) {
        Object value$iv;
        Intrinsics.checkNotNullParameter(FlowRow, "$this$FlowRow");
        ComposerKt.sourceInformation($composer, "C*606@28080L30,602@27890L243:InnerShell.kt#m1syn");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1296471067, $changed, -1, "ai.nous.aurora.presentation.shell.InboxPane.<anonymous>.<anonymous> (InnerShell.kt:600)");
            }
            Iterable $this$forEach$iv = this.$filters;
            MainViewModel.UiState uiState = this.$state;
            boolean z = this.$astral;
            final Function1<String, Unit> function1 = this.$onSourceFilter;
            for (Object element$iv : $this$forEach$iv) {
                final SourceFilter filter = (SourceFilter) element$iv;
                boolean selected = Intrinsics.areEqual(uiState.getSourceFilter(), filter.getKey());
                String label = filter.getLabel();
                $composer.startReplaceGroup(-236732868);
                ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                boolean invalid$iv = $composer.changed(function1) | $composer.changed(filter);
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$InboxPane$2$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return InnerShellKt$InboxPane$2$2.invoke$lambda$2$lambda$1$lambda$0(function1, filter);
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer.endReplaceGroup();
                InnerShellKt.SourceFilterChip(label, selected, z, (Function0) value$iv, $composer, 0);
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
    public static final Unit invoke$lambda$2$lambda$1$lambda$0(Function1 $onSourceFilter, SourceFilter $filter) {
        $onSourceFilter.invoke($filter.getKey());
        return Unit.INSTANCE;
    }
}
