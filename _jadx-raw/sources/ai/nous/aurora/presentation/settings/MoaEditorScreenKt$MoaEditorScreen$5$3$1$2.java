package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.MoaPresetDto;
import ai.nous.aurora.presentation.components.ds.AuroraDsKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$2 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<String> $activePreset$delegate;
    final /* synthetic */ boolean $bridgeOnline;
    final /* synthetic */ MutableState<String> $defaultPreset$delegate;
    final /* synthetic */ MutableState<Boolean> $dirty$delegate;
    final /* synthetic */ MoaPresetDto $draft;
    final /* synthetic */ MutableState<Map<String, MoaPresetDto>> $drafts$delegate;
    final /* synthetic */ List<String> $models;
    final /* synthetic */ Function1<Boolean, Unit> $onDirtyChange;
    final /* synthetic */ List<String> $providers;
    final /* synthetic */ boolean $saving;
    final /* synthetic */ MutableState<String> $selectedName$delegate;

    /* JADX WARN: Multi-variable type inference failed */
    MoaEditorScreenKt$MoaEditorScreen$5$3$1$2(boolean z, boolean z2, MutableState<String> mutableState, MutableState<String> mutableState2, Function1<? super Boolean, Unit> function1, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, MutableState<Map<String, MoaPresetDto>> mutableState5, List<String> list, List<String> list2, MoaPresetDto moaPresetDto) {
        this.$bridgeOnline = z;
        this.$saving = z2;
        this.$defaultPreset$delegate = mutableState;
        this.$selectedName$delegate = mutableState2;
        this.$onDirtyChange = function1;
        this.$dirty$delegate = mutableState3;
        this.$activePreset$delegate = mutableState4;
        this.$drafts$delegate = mutableState5;
        this.$providers = list;
        this.$models = list2;
        this.$draft = moaPresetDto;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:86:0x0441  */
    /* JADX WARN: Code duplicated, block: B:89:0x0471  */
    /* JADX WARN: Code duplicated, block: B:90:0x047f  */
    /* JADX WARN: Code duplicated, block: B:93:0x0509  */
    /* JADX WARN: Code duplicated, block: B:96:? A[RETURN, SYNTHETIC] */
    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Object value$iv;
        Object value$iv2;
        Function0<ComposeUiNode> function1;
        Function1<Boolean, Unit> function2;
        boolean invalid$iv;
        Object value$iv3;
        float f;
        Object it$iv;
        Object value$iv4;
        Object value$iv5;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C305@12546L5085:MoaEditorScreen.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(981054617, $changed, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:305)");
            }
            Modifier modifier$iv = PaddingKt.m1035paddingVpY3zN4(SizeKt.m1067heightInVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(44), 0.0f, 2, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), Dp.m7016constructorimpl(2));
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.m914spacedBy0680j_4(AuroraSpacing.INSTANCE.m240getSmD9Ej5fM());
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            final boolean z = this.$bridgeOnline;
            final boolean z2 = this.$saving;
            final MutableState<String> mutableState = this.$defaultPreset$delegate;
            final MutableState<String> mutableState2 = this.$selectedName$delegate;
            final Function1<Boolean, Unit> function3 = this.$onDirtyChange;
            final MutableState<Boolean> mutableState3 = this.$dirty$delegate;
            MutableState<String> mutableState4 = this.$activePreset$delegate;
            MutableState<Map<String, MoaPresetDto>> mutableState5 = this.$drafts$delegate;
            List<String> list = this.$providers;
            List<String> list2 = this.$models;
            MoaPresetDto moaPresetDto = this.$draft;
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv = (438 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i2 = ((438 >> 6) & 112) | 6;
            RowScope $this$invoke_u24lambda_u2412 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -148171525, "C317@13263L128,313@12956L458,325@13648L27,326@13715L34,327@13770L3843:MoaEditorScreen.kt#syfnu6");
            String str = Intrinsics.areEqual(MoaEditorScreenKt.MoaEditorScreen$lambda$7(mutableState2), MoaEditorScreenKt.MoaEditorScreen$lambda$11(mutableState)) ? "Default preset" : "Make default";
            boolean zAreEqual = Intrinsics.areEqual(MoaEditorScreenKt.MoaEditorScreen$lambda$7(mutableState2), MoaEditorScreenKt.MoaEditorScreen$lambda$11(mutableState));
            boolean z3 = (!z || z2 || Intrinsics.areEqual(MoaEditorScreenKt.MoaEditorScreen$lambda$7(mutableState2), MoaEditorScreenKt.MoaEditorScreen$lambda$11(mutableState))) ? false : true;
            $composer.startReplaceGroup(-1390247753);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed(mutableState) | $composer.changed(mutableState2) | $composer.changed(function3);
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$2.invoke$lambda$12$lambda$1$lambda$0(mutableState2, mutableState, function3, mutableState3);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv2;
            }
            $composer.endReplaceGroup();
            MoaEditorScreenKt.StatusChip(str, zAreEqual, z3, (Function0) value$iv, $composer, 0);
            $composer.startReplaceGroup(-1390242185);
            ComposerKt.sourceInformation($composer, "323@13602L2,323@13524L81");
            if (Intrinsics.areEqual(MoaEditorScreenKt.MoaEditorScreen$lambda$7(mutableState2), MoaEditorScreenKt.MoaEditorScreen$lambda$14(mutableState4)) && !StringsKt.isBlank(MoaEditorScreenKt.MoaEditorScreen$lambda$14(mutableState4))) {
                $composer.startReplaceGroup(-1390237031);
                ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
                Object it$iv3 = $composer.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer.updateRememberedValue(value$iv5);
                } else {
                    value$iv5 = it$iv3;
                }
                $composer.endReplaceGroup();
                MoaEditorScreenKt.StatusChip("Active preset", true, false, (Function0) value$iv5, $composer, 3510);
            }
            $composer.endReplaceGroup();
            SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2412, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
            $composer.startReplaceGroup(-1390233383);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            Object it$iv4 = $composer.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv4;
            }
            final MutableState actionsOpen$delegate = (MutableState) value$iv2;
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier modifier$iv2 = Modifier.INSTANCE;
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function1 = constructor2;
                $composer.createNode(function1);
            } else {
                function1 = constructor2;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                function2 = function3;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -846271746, "C330@13902L51,328@13800L278,333@14159L23,333@14184L3407,333@14103L3488:MoaEditorScreen.kt#syfnu6");
                $composer.startReplaceGroup(1773815753);
                ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
                invalid$iv = $composer.changed(z) | $composer.changed(z2);
                Object it$iv5 = $composer.rememberedValue();
                if (!invalid$iv || it$iv5 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MoaEditorScreenKt$MoaEditorScreen$5$3$1$2.invoke$lambda$12$lambda$11$lambda$8$lambda$7(z, z2, actionsOpen$delegate);
                        }
                    };
                    $composer.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv5;
                }
                Function0 function4 = (Function0) value$iv3;
                $composer.endReplaceGroup();
                Modifier.Companion companion = Modifier.INSTANCE;
                if (z || z2) {
                    f = 0.5f;
                } else {
                    f = 1.0f;
                }
                AuroraDsKt.AuroraPillAction("Manage", function4, AlphaKt.alpha(companion, f), false, $composer, 6, 8);
                boolean zInvoke$lambda$12$lambda$5 = invoke$lambda$12$lambda$5(actionsOpen$delegate);
                $composer.startReplaceGroup(1773823949);
                ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
                it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MoaEditorScreenKt$MoaEditorScreen$5$3$1$2.invoke$lambda$12$lambda$11$lambda$10$lambda$9(actionsOpen$delegate);
                        }
                    };
                    $composer.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv;
                }
                $composer.endReplaceGroup();
                AndroidMenu_androidKt.m2138DropdownMenuIlH_yew(zInvoke$lambda$12$lambda$5, (Function0) value$iv4, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1587555624, true, new MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3(mutableState5, list, list2, mutableState2, function2, mutableState, moaPresetDto, mutableState3, actionsOpen$delegate), $composer, 54), $composer, 48, 48, 2044);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
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
            function2 = function3;
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -846271746, "C330@13902L51,328@13800L278,333@14159L23,333@14184L3407,333@14103L3488:MoaEditorScreen.kt#syfnu6");
            $composer.startReplaceGroup(1773815753);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            invalid$iv = $composer.changed(z) | $composer.changed(z2);
            Object it$iv6 = $composer.rememberedValue();
            if (!invalid$iv) {
            }
            value$iv3 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$2.invoke$lambda$12$lambda$11$lambda$8$lambda$7(z, z2, actionsOpen$delegate);
                }
            };
            $composer.updateRememberedValue(value$iv3);
            Function0 function5 = (Function0) value$iv3;
            $composer.endReplaceGroup();
            Modifier.Companion companion2 = Modifier.INSTANCE;
            if (z) {
                f = 0.5f;
            } else {
                f = 0.5f;
            }
            AuroraDsKt.AuroraPillAction("Manage", function5, AlphaKt.alpha(companion2, f), false, $composer, 6, 8);
            boolean zInvoke$lambda$12$lambda$6 = invoke$lambda$12$lambda$5(actionsOpen$delegate);
            $composer.startReplaceGroup(1773823949);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$2.invoke$lambda$12$lambda$11$lambda$10$lambda$9(actionsOpen$delegate);
                    }
                };
                $composer.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv;
            }
            $composer.endReplaceGroup();
            AndroidMenu_androidKt.m2138DropdownMenuIlH_yew(zInvoke$lambda$12$lambda$6, (Function0) value$iv4, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1587555624, true, new MoaEditorScreenKt$MoaEditorScreen$5$3$1$2$1$3$3(mutableState5, list, list2, mutableState2, function2, mutableState, moaPresetDto, mutableState3, actionsOpen$delegate), $composer, 54), $composer, 48, 48, 2044);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
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
    public static final Unit invoke$lambda$12$lambda$1$lambda$0(MutableState $selectedName$delegate, MutableState $defaultPreset$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate) {
        $defaultPreset$delegate.setValue(MoaEditorScreenKt.MoaEditorScreen$lambda$7($selectedName$delegate));
        MoaEditorScreenKt.MoaEditorScreen$markDirty($onDirtyChange, $dirty$delegate, true);
        return Unit.INSTANCE;
    }

    private static final boolean invoke$lambda$12$lambda$5(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$12$lambda$6(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$12$lambda$11$lambda$8$lambda$7(boolean $bridgeOnline, boolean $saving, MutableState $actionsOpen$delegate) {
        if ($bridgeOnline && !$saving) {
            invoke$lambda$12$lambda$6($actionsOpen$delegate, true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$12$lambda$11$lambda$10$lambda$9(MutableState $actionsOpen$delegate) {
        invoke$lambda$12$lambda$6($actionsOpen$delegate, false);
        return Unit.INSTANCE;
    }
}
