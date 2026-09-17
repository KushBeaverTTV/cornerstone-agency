package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.MoaModelSlotDto;
import ai.nous.aurora.data.remote.MoaPresetDto;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3 implements Function3<ColumnScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<Boolean> $actionsOpen$delegate;
    final /* synthetic */ MutableState<String> $defaultPreset$delegate;
    final /* synthetic */ MutableState<Boolean> $dirty$delegate;
    final /* synthetic */ MoaPresetDto $draft;
    final /* synthetic */ MutableState<Map<String, MoaPresetDto>> $drafts$delegate;
    final /* synthetic */ List<String> $models;
    final /* synthetic */ Function1<Boolean, Unit> $onDirtyChange;
    final /* synthetic */ List<String> $providers;
    final /* synthetic */ MutableState<String> $selectedName$delegate;

    /* JADX WARN: Multi-variable type inference failed */
    MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3(MutableState<Map<String, MoaPresetDto>> mutableState, List<String> list, List<String> list2, MutableState<String> mutableState2, Function1<? super Boolean, Unit> function1, MutableState<String> mutableState3, MoaPresetDto moaPresetDto, MutableState<Boolean> mutableState4, MutableState<Boolean> mutableState5) {
        this.$drafts$delegate = mutableState;
        this.$providers = list;
        this.$models = list2;
        this.$selectedName$delegate = mutableState2;
        this.$onDirtyChange = function1;
        this.$defaultPreset$delegate = mutableState3;
        this.$draft = moaPresetDto;
        this.$dirty$delegate = mutableState4;
        this.$actionsOpen$delegate = mutableState5;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
        invoke(columnScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(ColumnScope DropdownMenu, Composer $composer, int $changed) {
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Intrinsics.checkNotNullParameter(DropdownMenu, "$this$DropdownMenu");
        ComposerKt.sourceInformation($composer, "C336@14337L1324,334@14214L1478,362@15846L208,360@15721L364,370@16243L581,368@16114L741,386@17106L394,384@16972L563:MoaEditorScreen.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1587555624, $changed, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:334)");
            }
            Function2<Composer, Integer, Unit> function2M140getLambda2$app_debug = ComposableSingletons$MoaEditorScreenKt.INSTANCE.m140getLambda2$app_debug();
            $composer.startReplaceGroup(920106017);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$drafts$delegate) | $composer.changedInstance(this.$providers) | $composer.changedInstance(this.$models) | $composer.changed(this.$selectedName$delegate) | $composer.changed(this.$onDirtyChange);
            final List<String> list = this.$providers;
            final List<String> list2 = this.$models;
            final MutableState<Map<String, MoaPresetDto>> mutableState = this.$drafts$delegate;
            final MutableState<String> mutableState2 = this.$selectedName$delegate;
            final Function1<Boolean, Unit> function1 = this.$onDirtyChange;
            final MutableState<Boolean> mutableState3 = this.$dirty$delegate;
            final MutableState<Boolean> mutableState4 = this.$actionsOpen$delegate;
            Object value$iv4 = $composer.rememberedValue();
            if (invalid$iv || value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3.invoke$lambda$1$lambda$0(list, list2, mutableState, mutableState2, function1, mutableState3, mutableState4);
                    }
                };
                $composer.updateRememberedValue(value$iv4);
            }
            $composer.endReplaceGroup();
            AndroidMenu_androidKt.DropdownMenuItem(function2M140getLambda2$app_debug, (Function0) value$iv4, null, null, null, false, null, null, null, $composer, 6, 508);
            Function2<Composer, Integer, Unit> function2M141getLambda3$app_debug = ComposableSingletons$MoaEditorScreenKt.INSTANCE.m141getLambda3$app_debug();
            $composer.startReplaceGroup(920153189);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed(this.$defaultPreset$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changed(this.$onDirtyChange);
            final MutableState<String> mutableState5 = this.$selectedName$delegate;
            final MutableState<String> mutableState6 = this.$defaultPreset$delegate;
            final Function1<Boolean, Unit> function2 = this.$onDirtyChange;
            final MutableState<Boolean> mutableState7 = this.$dirty$delegate;
            final MutableState<Boolean> mutableState8 = this.$actionsOpen$delegate;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3.invoke$lambda$3$lambda$2(mutableState5, mutableState6, function2, mutableState7, mutableState8);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceGroup();
            AndroidMenu_androidKt.DropdownMenuItem(function2M141getLambda3$app_debug, (Function0) value$iv, null, null, null, false, null, null, null, $composer, 6, 508);
            Function2<Composer, Integer, Unit> function2M142getLambda4$app_debug = ComposableSingletons$MoaEditorScreenKt.INSTANCE.m142getLambda4$app_debug();
            $composer.startReplaceGroup(920166266);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            boolean invalid$iv3 = $composer.changed(this.$selectedName$delegate) | $composer.changed(this.$drafts$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange);
            final MoaPresetDto moaPresetDto = this.$draft;
            final MutableState<String> mutableState9 = this.$selectedName$delegate;
            final MutableState<Map<String, MoaPresetDto>> mutableState10 = this.$drafts$delegate;
            final Function1<Boolean, Unit> function3 = this.$onDirtyChange;
            final MutableState<Boolean> mutableState11 = this.$dirty$delegate;
            final MutableState<Boolean> mutableState12 = this.$actionsOpen$delegate;
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3.invoke$lambda$5$lambda$4(moaPresetDto, mutableState9, mutableState10, function3, mutableState11, mutableState12);
                    }
                };
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer.endReplaceGroup();
            AndroidMenu_androidKt.DropdownMenuItem(function2M142getLambda4$app_debug, (Function0) value$iv2, null, null, null, false, null, null, null, $composer, 6, 508);
            if (MoaEditorScreenKt.MoaEditorScreen$lambda$24(this.$drafts$delegate).size() > 1 && !Intrinsics.areEqual(MoaEditorScreenKt.MoaEditorScreen$lambda$7(this.$selectedName$delegate), MoaEditorScreenKt.MoaEditorScreen$lambda$11(this.$defaultPreset$delegate))) {
                Function2<Composer, Integer, Unit> function2M143getLambda5$app_debug = ComposableSingletons$MoaEditorScreenKt.INSTANCE.m143getLambda5$app_debug();
                $composer.startReplaceGroup(920193695);
                ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
                boolean invalid$iv4 = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changed(this.$defaultPreset$delegate) | $composer.changed(this.$onDirtyChange);
                final MutableState<Map<String, MoaPresetDto>> mutableState13 = this.$drafts$delegate;
                final MutableState<String> mutableState14 = this.$selectedName$delegate;
                final MutableState<String> mutableState15 = this.$defaultPreset$delegate;
                final Function1<Boolean, Unit> function4 = this.$onDirtyChange;
                final MutableState<Boolean> mutableState16 = this.$dirty$delegate;
                final MutableState<Boolean> mutableState17 = this.$actionsOpen$delegate;
                Object it$iv3 = $composer.rememberedValue();
                if (invalid$iv4 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3.invoke$lambda$8$lambda$7(mutableState13, mutableState14, mutableState15, function4, mutableState16, mutableState17);
                        }
                    };
                    $composer.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv3;
                }
                $composer.endReplaceGroup();
                AndroidMenu_androidKt.DropdownMenuItem(function2M143getLambda5$app_debug, (Function0) value$iv3, null, null, null, false, null, null, null, $composer, 6, 508);
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
    public static final Unit invoke$lambda$1$lambda$0(List $providers, List $models, MutableState $drafts$delegate, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, MutableState $actionsOpen$delegate) {
        String n = "preset";
        int i = 1;
        while (true) {
            if (!MoaEditorScreenKt.MoaEditorScreen$lambda$24($drafts$delegate).containsKey(n) && (i != 1 || !MoaEditorScreenKt.MoaEditorScreen$lambda$24($drafts$delegate).containsKey("preset"))) {
                break;
            }
            n = "preset-" + i;
            i++;
        }
        Map mapMoaEditorScreen$lambda$24 = MoaEditorScreenKt.MoaEditorScreen$lambda$24($drafts$delegate);
        String str = (String) CollectionsKt.firstOrNull($providers);
        String str2 = str == null ? "" : str;
        String str3 = (String) CollectionsKt.firstOrNull($models);
        List listListOf = CollectionsKt.listOf(new MoaModelSlotDto(str2, str3 == null ? "" : str3, (String) null, 4, (DefaultConstructorMarker) null));
        String str4 = (String) CollectionsKt.firstOrNull($providers);
        String str5 = str4 == null ? "" : str4;
        String str6 = (String) CollectionsKt.firstOrNull($models);
        $drafts$delegate.setValue(MapsKt.plus(mapMoaEditorScreen$lambda$24, TuplesKt.to(n, new MoaPresetDto(false, listListOf, new MoaModelSlotDto(str5, str6 == null ? "" : str6, (String) null, 4, (DefaultConstructorMarker) null), (Double) null, (Double) null, 0, (Integer) null, (String) null, 249, (DefaultConstructorMarker) null))));
        $selectedName$delegate.setValue(n);
        MoaEditorScreenKt.MoaEditorScreen$markDirty($onDirtyChange, $dirty$delegate, true);
        MoaEditorScreenKt$MoaEditorScreen$5$3$1$2.invoke$lambda$12$lambda$6($actionsOpen$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$3$lambda$2(MutableState $selectedName$delegate, MutableState $defaultPreset$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, MutableState $actionsOpen$delegate) {
        $defaultPreset$delegate.setValue(MoaEditorScreenKt.MoaEditorScreen$lambda$7($selectedName$delegate));
        MoaEditorScreenKt.MoaEditorScreen$markDirty($onDirtyChange, $dirty$delegate, true);
        MoaEditorScreenKt$MoaEditorScreen$5$3$1$2.invoke$lambda$12$lambda$6($actionsOpen$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$5$lambda$4(MoaPresetDto $draft, MutableState $selectedName$delegate, MutableState $drafts$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, MutableState $actionsOpen$delegate) {
        String n = MoaEditorScreenKt.MoaEditorScreen$lambda$7($selectedName$delegate) + "-copy";
        int i = 2;
        while (MoaEditorScreenKt.MoaEditorScreen$lambda$24($drafts$delegate).containsKey(n)) {
            n = MoaEditorScreenKt.MoaEditorScreen$lambda$7($selectedName$delegate) + "-copy" + i;
            i++;
        }
        $drafts$delegate.setValue(MapsKt.plus(MoaEditorScreenKt.MoaEditorScreen$lambda$24($drafts$delegate), TuplesKt.to(n, $draft)));
        $selectedName$delegate.setValue(n);
        MoaEditorScreenKt.MoaEditorScreen$markDirty($onDirtyChange, $dirty$delegate, true);
        MoaEditorScreenKt$MoaEditorScreen$5$3$1$2.invoke$lambda$12$lambda$6($actionsOpen$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$8$lambda$7(MutableState $drafts$delegate, MutableState $selectedName$delegate, MutableState $defaultPreset$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, MutableState $actionsOpen$delegate) {
        Map next = MapsKt.minus((Map<? extends String, ? extends V>) MoaEditorScreenKt.MoaEditorScreen$lambda$24($drafts$delegate), MoaEditorScreenKt.MoaEditorScreen$lambda$7($selectedName$delegate));
        $drafts$delegate.setValue(next);
        String it = MoaEditorScreenKt.MoaEditorScreen$lambda$11($defaultPreset$delegate);
        if (!next.containsKey(it)) {
            it = null;
        }
        if (it == null) {
            it = (String) CollectionsKt.first(next.keySet());
        }
        $selectedName$delegate.setValue(it);
        MoaEditorScreenKt.MoaEditorScreen$markDirty($onDirtyChange, $dirty$delegate, true);
        MoaEditorScreenKt$MoaEditorScreen$5$3$1$2.invoke$lambda$12$lambda$6($actionsOpen$delegate, false);
        return Unit.INSTANCE;
    }
}
