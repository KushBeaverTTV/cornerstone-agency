package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.MoaPresetDto;
import ai.nous.aurora.presentation.components.ds.AuroraDsKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.DividerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$10 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ boolean $bridgeOnline;
    final /* synthetic */ MutableState<Boolean> $dirty$delegate;
    final /* synthetic */ MoaPresetDto $draft;
    final /* synthetic */ MutableState<Map<String, MoaPresetDto>> $drafts$delegate;
    final /* synthetic */ List<String> $fanoutOptions;
    final /* synthetic */ Function1<Boolean, Unit> $onDirtyChange;
    final /* synthetic */ boolean $saving;
    final /* synthetic */ MutableState<String> $selectedName$delegate;
    final /* synthetic */ List<String> $tokenOptions;

    /* JADX WARN: Multi-variable type inference failed */
    MoaEditorScreenKt$MoaEditorScreen$5$3$1$10(MoaPresetDto moaPresetDto, boolean z, boolean z2, List<String> list, MutableState<Map<String, MoaPresetDto>> mutableState, MutableState<String> mutableState2, Function1<? super Boolean, Unit> function1, List<String> list2, MutableState<Boolean> mutableState3) {
        this.$draft = moaPresetDto;
        this.$bridgeOnline = z;
        this.$saving = z2;
        this.$fanoutOptions = list;
        this.$drafts$delegate = mutableState;
        this.$selectedName$delegate = mutableState2;
        this.$onDirtyChange = function1;
        this.$tokenOptions = list2;
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
        Object value$iv3;
        Object value$iv4;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C565@25592L44,556@25048L611,567@25706L14,567@25680L100,573@26117L77,568@25801L416,575@26264L14,575@26238L100,581@26669L70,576@26359L403,583@26809L14,583@26783L100,589@27235L75,584@26904L429,591@27380L14,591@27354L100,597@27808L76,592@27475L432,599@27954L14,599@27928L100,605@28343L57,600@28049L374:MoaEditorScreen.kt#syfnu6");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1398160820, $changed, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:556)");
        }
        String fanout = this.$draft.getFanout();
        if (fanout == null) {
            fanout = "";
        }
        String str = fanout;
        String str2 = "per_iteration";
        if (StringsKt.isBlank(str)) {
            str = "per_iteration";
        }
        String str3 = str;
        String fanout2 = this.$draft.getFanout();
        if (fanout2 == null) {
            fanout2 = "";
        }
        String str4 = fanout2;
        if (!StringsKt.isBlank(str4)) {
            str2 = str4;
        }
        String str5 = Intrinsics.areEqual(str2, "user_turn") ? "Once per user turn" : "Every tool iteration";
        boolean z = this.$bridgeOnline && !this.$saving;
        List<String> list = this.$fanoutOptions;
        $composer.startReplaceGroup(482412420);
        ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange);
        final MutableState<Map<String, MoaPresetDto>> mutableState = this.$drafts$delegate;
        final MoaPresetDto moaPresetDto = this.$draft;
        final MutableState<String> mutableState2 = this.$selectedName$delegate;
        final Function1<Boolean, Unit> function1 = this.$onDirtyChange;
        final MutableState<Boolean> mutableState3 = this.$dirty$delegate;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$4$lambda$3(mutableState, moaPresetDto, mutableState2, function1, mutableState3, (String) obj);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        $composer.endReplaceGroup();
        AuroraDsKt.AuroraDropdownRow("Reference fan-out", str3, list, (Function1) value$iv, str5, z, false, null, null, $composer, 390, 448);
        DividerKt.m2446HorizontalDivider9IZ8Weo(PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null), 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer, 0), $composer, 6, 2);
        String strValueOf = String.valueOf(this.$draft.getMaxTokens());
        List listDistinct = CollectionsKt.distinct(CollectionsKt.plus((Collection<? extends String>) this.$tokenOptions, String.valueOf(this.$draft.getMaxTokens())));
        boolean z2 = this.$bridgeOnline && !this.$saving;
        $composer.startReplaceGroup(482429253);
        ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange);
        final MutableState<Map<String, MoaPresetDto>> mutableState4 = this.$drafts$delegate;
        final MoaPresetDto moaPresetDto2 = this.$draft;
        final MutableState<String> mutableState5 = this.$selectedName$delegate;
        final Function1<Boolean, Unit> function2 = this.$onDirtyChange;
        final MutableState<Boolean> mutableState6 = this.$dirty$delegate;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$7$lambda$6(mutableState4, moaPresetDto2, mutableState5, function2, mutableState6, (String) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        $composer.endReplaceGroup();
        AuroraDsKt.AuroraDropdownRow("Max output tokens", strValueOf, listDistinct, (Function1) value$iv2, null, z2, false, null, null, $composer, 6, 464);
        DividerKt.m2446HorizontalDivider9IZ8Weo(PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null), 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer, 0), $composer, 6, 2);
        Integer referenceMaxTokens = this.$draft.getReferenceMaxTokens();
        String string = referenceMaxTokens != null ? referenceMaxTokens.toString() : null;
        String str6 = string == null ? "" : string;
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(""), (Iterable) this.$tokenOptions);
        boolean z3 = this.$bridgeOnline && !this.$saving;
        $composer.startReplaceGroup(482446910);
        ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
        boolean invalid$iv3 = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange);
        final MutableState<Map<String, MoaPresetDto>> mutableState7 = this.$drafts$delegate;
        final MoaPresetDto moaPresetDto3 = this.$draft;
        final MutableState<String> mutableState8 = this.$selectedName$delegate;
        final Function1<Boolean, Unit> function3 = this.$onDirtyChange;
        final MutableState<Boolean> mutableState9 = this.$dirty$delegate;
        Object value$iv5 = $composer.rememberedValue();
        if (invalid$iv3 || value$iv5 == Composer.INSTANCE.getEmpty()) {
            value$iv5 = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$10$lambda$9(mutableState7, moaPresetDto3, mutableState8, function3, mutableState9, (String) obj);
                }
            };
            $composer.updateRememberedValue(value$iv5);
        }
        $composer.endReplaceGroup();
        AuroraDsKt.AuroraDropdownRow("Reference max tokens", str6, listPlus, (Function1) value$iv5, null, z3, false, null, null, $composer, 6, 464);
        DividerKt.m2446HorizontalDivider9IZ8Weo(PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null), 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer, 0), $composer, 6, 2);
        Double referenceTemperature = this.$draft.getReferenceTemperature();
        String string2 = referenceTemperature != null ? referenceTemperature.toString() : null;
        String str7 = string2 == null ? "" : string2;
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{"", "0", "0.2", "0.5", "0.7", "1.0"});
        boolean z4 = this.$bridgeOnline && !this.$saving;
        $composer.startReplaceGroup(482465027);
        ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
        boolean invalid$iv4 = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange);
        final MutableState<Map<String, MoaPresetDto>> mutableState10 = this.$drafts$delegate;
        final MoaPresetDto moaPresetDto4 = this.$draft;
        final MutableState<String> mutableState11 = this.$selectedName$delegate;
        final Function1<Boolean, Unit> function4 = this.$onDirtyChange;
        final MutableState<Boolean> mutableState12 = this.$dirty$delegate;
        Object value$iv6 = $composer.rememberedValue();
        if (invalid$iv4 || value$iv6 == Composer.INSTANCE.getEmpty()) {
            value$iv6 = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$13$lambda$12(mutableState10, moaPresetDto4, mutableState11, function4, mutableState12, (String) obj);
                }
            };
            $composer.updateRememberedValue(value$iv6);
        }
        $composer.endReplaceGroup();
        AuroraDsKt.AuroraDropdownRow("Reference temperature", str7, listListOf, (Function1) value$iv6, null, z4, false, null, null, $composer, 390, 464);
        DividerKt.m2446HorizontalDivider9IZ8Weo(PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null), 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer, 0), $composer, 6, 2);
        Double aggregatorTemperature = this.$draft.getAggregatorTemperature();
        String string3 = aggregatorTemperature != null ? aggregatorTemperature.toString() : null;
        String str8 = string3 == null ? "" : string3;
        List listListOf2 = CollectionsKt.listOf((Object[]) new String[]{"", "0", "0.2", "0.5", "0.7", "1.0"});
        boolean z5 = this.$bridgeOnline && !this.$saving;
        $composer.startReplaceGroup(482483364);
        ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
        boolean invalid$iv5 = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange);
        final MutableState<Map<String, MoaPresetDto>> mutableState13 = this.$drafts$delegate;
        final MoaPresetDto moaPresetDto5 = this.$draft;
        final MutableState<String> mutableState14 = this.$selectedName$delegate;
        final Function1<Boolean, Unit> function5 = this.$onDirtyChange;
        final MutableState<Boolean> mutableState15 = this.$dirty$delegate;
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv5 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv3 = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$16$lambda$15(mutableState13, moaPresetDto5, mutableState14, function5, mutableState15, (String) obj);
                }
            };
            $composer.updateRememberedValue(value$iv3);
        } else {
            value$iv3 = it$iv3;
        }
        $composer.endReplaceGroup();
        AuroraDsKt.AuroraDropdownRow("Aggregator temperature", str8, listListOf2, (Function1) value$iv3, null, z5, false, null, null, $composer, 390, 464);
        DividerKt.m2446HorizontalDivider9IZ8Weo(PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null), 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer, 0), $composer, 6, 2);
        boolean enabled = this.$draft.getEnabled();
        boolean z6 = this.$bridgeOnline && !this.$saving;
        $composer.startReplaceGroup(482500465);
        ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
        boolean invalid$iv6 = $composer.changed(this.$drafts$delegate) | $composer.changed(this.$selectedName$delegate) | $composer.changedInstance(this.$draft) | $composer.changed(this.$onDirtyChange);
        final MutableState<Map<String, MoaPresetDto>> mutableState16 = this.$drafts$delegate;
        final MoaPresetDto moaPresetDto6 = this.$draft;
        final MutableState<String> mutableState17 = this.$selectedName$delegate;
        final Function1<Boolean, Unit> function6 = this.$onDirtyChange;
        final MutableState<Boolean> mutableState18 = this.$dirty$delegate;
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv6 || it$iv4 == Composer.INSTANCE.getEmpty()) {
            value$iv4 = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$19$lambda$18(mutableState16, moaPresetDto6, mutableState17, function6, mutableState18, ((Boolean) obj).booleanValue());
                }
            };
            $composer.updateRememberedValue(value$iv4);
        } else {
            value$iv4 = it$iv4;
        }
        $composer.endReplaceGroup();
        AuroraDsKt.AuroraSwitchRow("Preset enabled", enabled, (Function1) value$iv4, "Available when this preset is selected", z6, $composer, 3078, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$4$lambda$3(MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, final String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$4$lambda$3$lambda$2(v, (MoaPresetDto) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$4$lambda$3$lambda$2(String $v, MoaPresetDto it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.copy((251 & 1) != 0 ? it.enabled : false, (251 & 2) != 0 ? it.referenceModels : null, (251 & 4) != 0 ? it.aggregator : null, (251 & 8) != 0 ? it.referenceTemperature : null, (251 & 16) != 0 ? it.aggregatorTemperature : null, (251 & 32) != 0 ? it.maxTokens : 0, (251 & 64) != 0 ? it.referenceMaxTokens : null, (251 & 128) != 0 ? it.fanout : $v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$7$lambda$6(MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, final String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$7$lambda$6$lambda$5(v, (MoaPresetDto) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$7$lambda$6$lambda$5(String $v, MoaPresetDto it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Integer intOrNull = StringsKt.toIntOrNull($v);
        return it.copy((251 & 1) != 0 ? it.enabled : false, (251 & 2) != 0 ? it.referenceModels : null, (251 & 4) != 0 ? it.aggregator : null, (251 & 8) != 0 ? it.referenceTemperature : null, (251 & 16) != 0 ? it.aggregatorTemperature : null, (251 & 32) != 0 ? it.maxTokens : intOrNull != null ? intOrNull.intValue() : it.getMaxTokens(), (251 & 64) != 0 ? it.referenceMaxTokens : null, (251 & 128) != 0 ? it.fanout : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$10$lambda$9(MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, final String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$10$lambda$9$lambda$8(v, (MoaPresetDto) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$10$lambda$9$lambda$8(String $v, MoaPresetDto it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.copy((251 & 1) != 0 ? it.enabled : false, (251 & 2) != 0 ? it.referenceModels : null, (251 & 4) != 0 ? it.aggregator : null, (251 & 8) != 0 ? it.referenceTemperature : null, (251 & 16) != 0 ? it.aggregatorTemperature : null, (251 & 32) != 0 ? it.maxTokens : 0, (251 & 64) != 0 ? it.referenceMaxTokens : StringsKt.toIntOrNull($v), (251 & 128) != 0 ? it.fanout : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$13$lambda$12(MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, final String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$13$lambda$12$lambda$11(v, (MoaPresetDto) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$13$lambda$12$lambda$11(String $v, MoaPresetDto it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.copy((251 & 1) != 0 ? it.enabled : false, (251 & 2) != 0 ? it.referenceModels : null, (251 & 4) != 0 ? it.aggregator : null, (251 & 8) != 0 ? it.referenceTemperature : StringsKt.toDoubleOrNull($v), (251 & 16) != 0 ? it.aggregatorTemperature : null, (251 & 32) != 0 ? it.maxTokens : 0, (251 & 64) != 0 ? it.referenceMaxTokens : null, (251 & 128) != 0 ? it.fanout : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$16$lambda$15(MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, final String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$16$lambda$15$lambda$14(v, (MoaPresetDto) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$16$lambda$15$lambda$14(String $v, MoaPresetDto it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.copy((251 & 1) != 0 ? it.enabled : false, (251 & 2) != 0 ? it.referenceModels : null, (251 & 4) != 0 ? it.aggregator : null, (251 & 8) != 0 ? it.referenceTemperature : null, (251 & 16) != 0 ? it.aggregatorTemperature : StringsKt.toDoubleOrNull($v), (251 & 32) != 0 ? it.maxTokens : 0, (251 & 64) != 0 ? it.referenceMaxTokens : null, (251 & 128) != 0 ? it.fanout : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$19$lambda$18(MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, final boolean checked) {
        MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$10$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoaEditorScreenKt$MoaEditorScreen$5$3$1$10.invoke$lambda$19$lambda$18$lambda$17(checked, (MoaPresetDto) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$19$lambda$18$lambda$17(boolean $checked, MoaPresetDto it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.copy((251 & 1) != 0 ? it.enabled : $checked, (251 & 2) != 0 ? it.referenceModels : null, (251 & 4) != 0 ? it.aggregator : null, (251 & 8) != 0 ? it.referenceTemperature : null, (251 & 16) != 0 ? it.aggregatorTemperature : null, (251 & 32) != 0 ? it.maxTokens : 0, (251 & 64) != 0 ? it.referenceMaxTokens : null, (251 & 128) != 0 ? it.fanout : null);
    }
}
