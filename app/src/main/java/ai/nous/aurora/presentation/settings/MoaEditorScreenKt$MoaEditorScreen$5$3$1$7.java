package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.MoaModelSlotDto;
import ai.nous.aurora.data.remote.MoaPresetDto;
import ai.nous.aurora.presentation.components.ds.AuroraDsKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$7 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ boolean $bridgeOnline;
    final /* synthetic */ MutableState<Boolean> $dirty$delegate;
    final /* synthetic */ MoaPresetDto $draft;
    final /* synthetic */ MutableState<Map<String, MoaPresetDto>> $drafts$delegate;
    final /* synthetic */ List<String> $models;
    final /* synthetic */ Function1<? super Boolean, Unit> $onDirtyChange;
    final /* synthetic */ List<String> $providers;
    final /* synthetic */ boolean $saving;
    final /* synthetic */ MutableState<String> $selectedName$delegate;

    /* JADX WARN: Multi-variable type inference failed */
    MoaEditorScreenKt$MoaEditorScreen$5$3$1$7(boolean z, boolean z2, MutableState<Map<String, MoaPresetDto>> mutableState, MutableState<String> mutableState2, MoaPresetDto moaPresetDto, Function1<? super Boolean, Unit> function1, List<String> list, List<String> list2, MutableState<Boolean> mutableState3) {
        this.$bridgeOnline = z;
        this.$saving = z2;
        this.$drafts$delegate = mutableState;
        this.$selectedName$delegate = mutableState2;
        this.$draft = moaPresetDto;
        this.$onDirtyChange = function1;
        this.$providers = list;
        this.$models = list2;
        this.$dirty$delegate = mutableState3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Object value$iv;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C504@22792L1019:MoaEditorScreen.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1884351676, $changed, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:504)");
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
            final boolean z = this.$bridgeOnline;
            final boolean z2 = this.$saving;
            final MutableState<Map<String, MoaPresetDto>> mutableState = this.$drafts$delegate;
            final MutableState<String> mutableState2 = this.$selectedName$delegate;
            final MoaPresetDto moaPresetDto = this.$draft;
            final Function1<? super Boolean, Unit> function1 = this.$onDirtyChange;
            final List<String> list = this.$providers;
            final List<String> list2 = this.$models;
            final MutableState<Boolean> mutableState3 = this.$dirty$delegate;
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -138316253, "C511@23123L639,509@23012L777:MoaEditorScreen.kt#syfnu6");
            $composer.startReplaceGroup(-1389931722);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            boolean invalid$iv = $composer.changed(z) | $composer.changed(z2) | $composer.changed(mutableState) | $composer.changed(mutableState2) | $composer.changedInstance(moaPresetDto) | $composer.changed(function1) | $composer.changedInstance(list) | $composer.changedInstance(list2);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$7$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$7.invoke$lambda$3$lambda$2$lambda$1(z, z2, list, list2, mutableState, moaPresetDto, mutableState2, function1, mutableState3);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            Object r5 = null;
            $composer.endReplaceGroup();
            AuroraDsKt.AuroraPillAction("+ Add reference", (Function0) value$iv, null, false, r5, 6, 12);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$3$lambda$2$lambda$1(boolean $bridgeOnline, boolean $saving, final List $providers, final List $models, MutableState $drafts$delegate, MoaPresetDto $draft, MutableState $selectedName$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate) {
        if ($bridgeOnline && !$saving) {
            MoaEditorScreenKt.MoaEditorScreen$updateDraft($drafts$delegate, $draft, $selectedName$delegate, $onDirtyChange, $dirty$delegate, new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$7$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$7.invoke$lambda$3$lambda$2$lambda$1$lambda$0($providers, $models, (MoaPresetDto) obj);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MoaPresetDto invoke$lambda$3$lambda$2$lambda$1$lambda$0(List $providers, List $models, MoaPresetDto p) {
        Intrinsics.checkNotNullParameter(p, "p");
        List<MoaModelSlotDto> referenceModels = p.getReferenceModels();
        String str = (String) CollectionsKt.firstOrNull($providers);
        String str2 = str == null ? "" : str;
        String str3 = (String) CollectionsKt.firstOrNull($models);
        return p.copy((251 & 1) != 0 ? p.enabled : false, (251 & 2) != 0 ? p.referenceModels : CollectionsKt.plus((Collection<? extends MoaModelSlotDto>) referenceModels, new MoaModelSlotDto(str2, str3 == null ? "" : str3, (String) null, 4, (DefaultConstructorMarker) null)), (251 & 4) != 0 ? p.aggregator : null, (251 & 8) != 0 ? p.referenceTemperature : null, (251 & 16) != 0 ? p.aggregatorTemperature : null, (251 & 32) != 0 ? p.maxTokens : 0, (251 & 64) != 0 ? p.referenceMaxTokens : null, (251 & 128) != 0 ? p.fanout : null);
    }
}
