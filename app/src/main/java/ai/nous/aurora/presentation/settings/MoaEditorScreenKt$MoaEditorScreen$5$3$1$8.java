package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.MoaModelSlotDto;
import ai.nous.aurora.data.remote.MoaPresetDto;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$8 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ boolean $bridgeOnline;
    final /* synthetic */ MutableState<Boolean> $dirty$delegate;
    final /* synthetic */ MoaPresetDto $draft;
    final /* synthetic */ MutableState<Map<String, MoaPresetDto>> $drafts$delegate;
    final /* synthetic */ List<String> $effortOptions;
    final /* synthetic */ List<String> $models;
    final /* synthetic */ Function1<? super Boolean, Unit> $onDirtyChange;
    final /* synthetic */ List<String> $providers;
    final /* synthetic */ boolean $saving;
    final /* synthetic */ MutableState<String> $selectedName$delegate;

    /* JADX WARN: Multi-variable type inference failed */
    MoaEditorScreenKt$MoaEditorScreen$5$3$1$8(MoaPresetDto moaPresetDto, List<String> list, List<String> list2, List<String> list3, boolean z, boolean z2, MutableState<Map<String, MoaPresetDto>> mutableState, MutableState<String> mutableState2, Function1<? super Boolean, Unit> function1, MutableState<Boolean> mutableState3) {
        this.$draft = moaPresetDto;
        this.$providers = list;
        this.$models = list2;
        this.$effortOptions = list3;
        this.$bridgeOnline = z;
        this.$saving = z2;
        this.$drafts$delegate = mutableState;
        this.$selectedName$delegate = mutableState2;
        this.$onDirtyChange = function1;
        this.$dirty$delegate = mutableState3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Object value$iv;
        Object value$iv2;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C539@24308L54,540@24395L2,530@23912L504:MoaEditorScreen.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1429170202, $changed, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:530)");
            }
            MoaModelSlotDto aggregator = this.$draft.getAggregator();
            List<String> list = this.$providers;
            List<String> list2 = this.$models;
            List<String> list3 = this.$effortOptions;
            boolean z = this.$bridgeOnline && !this.$saving;
            $composer.startReplaceGroup(482371342);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange);
            final MutableState<Map<String, MoaPresetDto>> mutableState = this.$drafts$delegate;
            final MoaPresetDto moaPresetDto = this.$draft;
            final MutableState<String> mutableState2 = this.$selectedName$delegate;
            final Function1<? super Boolean, Unit> function1 = this.$onDirtyChange;
            final MutableState<Boolean> mutableState3 = this.$dirty$delegate;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$8$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$8.invoke$lambda$2$lambda$1(mutableState, moaPresetDto, mutableState2, function1, mutableState3, (MoaModelSlotDto) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            Function1 function2 = (Function1) value$iv;
            $composer.endReplaceGroup();
            $composer.startReplaceGroup(482374074);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.Companion.getEmpty()) {
                value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$8$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer.endReplaceGroup();
            MoaEditorScreenKt.AgentCard("A", "Aggregator", aggregator, list, list2, list3, false, z, function2, (Function0) value$iv2, $composer, 807075894, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$2$lambda$1(MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, final MoaModelSlotDto next) {
        Intrinsics.checkNotNullParameter(next, "next");
        MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$8$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoaEditorScreenKt$MoaEditorScreen$5$3$1$8.invoke$lambda$2$lambda$1$lambda$0(next, (MoaPresetDto) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$2$lambda$1$lambda$0(MoaModelSlotDto $next, MoaPresetDto it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.copy((251 & 1) != 0 ? it.enabled : false, (251 & 2) != 0 ? it.referenceModels : null, (251 & 4) != 0 ? it.aggregator : $next, (251 & 8) != 0 ? it.referenceTemperature : null, (251 & 16) != 0 ? it.aggregatorTemperature : null, (251 & 32) != 0 ? it.maxTokens : 0, (251 & 64) != 0 ? it.referenceMaxTokens : null, (251 & 128) != 0 ? it.fanout : null);
    }
}
