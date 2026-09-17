package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.MoaModelSlotDto;
import ai.nous.aurora.data.remote.MoaPresetDto;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$6 implements Function4<LazyItemScope, Integer, Composer, Integer, Unit> {
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
    MoaEditorScreenKt$MoaEditorScreen$5$3$1$6(MoaPresetDto moaPresetDto, List<String> list, List<String> list2, List<String> list3, boolean z, boolean z2, MutableState<Map<String, MoaPresetDto>> mutableState, MutableState<String> mutableState2, Function1<? super Boolean, Unit> function1, MutableState<Boolean> mutableState3) {
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

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
        invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope items, final int index, Composer $composer, int $changed) {
        boolean z;
        Intrinsics.checkNotNullParameter(items, "$this$items");
        ComposerKt.sourceInformation($composer, "C489@22152L301,496@22490L217,481@21746L984:MoaEditorScreen.kt#syfnu6");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            $dirty |= $composer.changed(index) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 145) != 144 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-584877755, $dirty2, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:480)");
            }
            MoaModelSlotDto slot = this.$draft.getReferenceModels().get(index);
            String strValueOf = String.valueOf(index + 1);
            List<String> list = this.$providers;
            List<String> list2 = this.$models;
            List<String> list3 = this.$effortOptions;
            boolean z2 = this.$draft.getReferenceModels().size() > 1;
            boolean z3 = this.$bridgeOnline && !this.$saving;
            $composer.startReplaceGroup(482302597);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange) | (($dirty2 & 112) == 32);
            final MutableState<Map<String, MoaPresetDto>> mutableState = this.$drafts$delegate;
            final MoaPresetDto moaPresetDto = this.$draft;
            final MutableState<String> mutableState2 = this.$selectedName$delegate;
            final Function1<? super Boolean, Unit> function1 = this.$onDirtyChange;
            final MutableState<Boolean> mutableState3 = this.$dirty$delegate;
            Object value$iv = $composer.rememberedValue();
            if (invalid$iv || value$iv == Composer.Companion.getEmpty()) {
                z = true;
                value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$6$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$6.invoke$lambda$3$lambda$2(index, mutableState, moaPresetDto, mutableState2, function1, mutableState3, (MoaModelSlotDto) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                z = true;
            }
            Function1 function2 = (Function1) value$iv;
            $composer.endReplaceGroup();
            $composer.startReplaceGroup(482313329);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange) | (($dirty2 & 112) == 32 ? z : false);
            final MutableState<Map<String, MoaPresetDto>> mutableState4 = this.$drafts$delegate;
            final MoaPresetDto moaPresetDto2 = this.$draft;
            final MutableState<String> mutableState5 = this.$selectedName$delegate;
            final Function1<? super Boolean, Unit> function3 = this.$onDirtyChange;
            final MutableState<Boolean> mutableState6 = this.$dirty$delegate;
            Object value$iv2 = $composer.rememberedValue();
            if (invalid$iv2 || value$iv2 == Composer.Companion.getEmpty()) {
                value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$6$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$6.invoke$lambda$7$lambda$6(index, mutableState4, moaPresetDto2, mutableState5, function3, mutableState6);
                    }
                };
                $composer.updateRememberedValue(value$iv2);
            }
            $composer.endReplaceGroup();
            MoaEditorScreenKt.AgentCard(strValueOf, null, slot, list, list2, list3, z2, z3, function2, (Function0) value$iv2, $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$3$lambda$2(final int $index, MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, final MoaModelSlotDto next) {
        Intrinsics.checkNotNullParameter(next, "next");
        MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$6$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoaEditorScreenKt$MoaEditorScreen$5$3$1$6.invoke$lambda$3$lambda$2$lambda$1($index, next, (MoaPresetDto) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$3$lambda$2$lambda$1(int $index, MoaModelSlotDto $next, MoaPresetDto p) {
        Intrinsics.checkNotNullParameter(p, "p");
        List it = CollectionsKt.toMutableList((Collection) p.getReferenceModels());
        it.set($index, $next);
        Unit unit = Unit.INSTANCE;
        return p.copy((251 & 1) != 0 ? p.enabled : false, (251 & 2) != 0 ? p.referenceModels : it, (251 & 4) != 0 ? p.aggregator : null, (251 & 8) != 0 ? p.referenceTemperature : null, (251 & 16) != 0 ? p.aggregatorTemperature : null, (251 & 32) != 0 ? p.maxTokens : 0, (251 & 64) != 0 ? p.referenceMaxTokens : null, (251 & 128) != 0 ? p.fanout : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$7$lambda$6(final int $index, MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate) {
        MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$6$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoaEditorScreenKt$MoaEditorScreen$5$3$1$6.invoke$lambda$7$lambda$6$lambda$5($index, (MoaPresetDto) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$7$lambda$6$lambda$5(int $index, MoaPresetDto p) {
        Intrinsics.checkNotNullParameter(p, "p");
        Iterable $this$filterIndexed$iv = p.getReferenceModels();
        Collection destination$iv$iv = new ArrayList();
        int index$iv$iv = 0;
        for (Object item$iv$iv$iv : $this$filterIndexed$iv) {
            int index$iv$iv$iv = index$iv$iv + 1;
            if (index$iv$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int i = index$iv$iv;
            if (i != $index) {
                destination$iv$iv.add(item$iv$iv$iv);
            }
            index$iv$iv = index$iv$iv$iv;
        }
        return p.copy((251 & 1) != 0 ? p.enabled : false, (251 & 2) != 0 ? p.referenceModels : (List) destination$iv$iv, (251 & 4) != 0 ? p.aggregator : null, (251 & 8) != 0 ? p.referenceTemperature : null, (251 & 16) != 0 ? p.aggregatorTemperature : null, (251 & 32) != 0 ? p.maxTokens : 0, (251 & 64) != 0 ? p.referenceMaxTokens : null, (251 & 128) != 0 ? p.fanout : null);
    }
}
