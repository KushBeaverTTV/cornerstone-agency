package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.presentation.assist.AssistOrbKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraShapeKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssistantSettingsPane.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$2 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ AssistSettingsUi $assist;
    final /* synthetic */ Function1<? super String, Unit> $onOrbStyle;

    /* JADX WARN: Multi-variable type inference failed */
    AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$2(AssistSettingsUi assistSettingsUi, Function1<? super String, Unit> function1) {
        this.$assist = assistSettingsUi;
        this.$onOrbStyle = function1;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        long auroraHairline;
        Object value$iv;
        Function0<ComposeUiNode> function1;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C150@6026L1655:AssistantSettingsPane.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1691622006, $changed, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AssistantSettingsPane.kt:150)");
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM());
            Arrangement.Horizontal horizontalArrangement$iv = ai.nous.aurora.compat.Compat.m914spacedBy0680j_4(Arrangement.INSTANCE, AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
            AssistSettingsUi assistSettingsUi = this.$assist;
            Function1<? super String, Unit> function2 = this.$onOrbStyle;
            int $changed$iv = 54;
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            int $changed$iv$iv = (54 << 3) & 112;
            String str = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            String str2 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
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
            int $changed2 = ((54 >> 6) & 112) | 6;
            RowScope $this$invoke_u24lambda_u244 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 245282697, "C:AssistantSettingsPane.kt#syfnu6");
            $composer.startReplaceGroup(1116291374);
            ComposerKt.sourceInformation($composer, "*168@7046L13,169@7104L25,158@6457L1184");
            Iterable $this$forEach$iv = AssistantSettingsPaneKt.ORB_CHOICES;
            int $i$f$forEach = 0;
            for (Object element$iv : $this$forEach$iv) {
                Iterable $this$forEach$iv2 = $this$forEach$iv;
                final OrbChoice choice = (OrbChoice) element$iv;
                int $i$f$forEach2 = $i$f$forEach;
                Modifier materialized$iv$iv2 = materialized$iv$iv;
                boolean selected = Intrinsics.areEqual(choice.getId(), assistSettingsUi.getOrbStyle());
                Alignment.Horizontal centerHorizontally = Alignment.Companion.getCenterHorizontally();
                int $changed3 = $changed2;
                Arrangement.Horizontal horizontalArrangement$iv2 = horizontalArrangement$iv;
                Modifier modifierClip = ClipKt.clip(ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u244, Modifier.Companion, 1.0f, false, 2, null), AuroraShapeKt.getAuroraShapeMd());
                float fM244getXxsD9Ej5fM = selected ? AuroraSpacing.INSTANCE.m244getXxsD9Ej5fM() : ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                if (selected) {
                    $composer.startReplaceGroup(-1660050318);
                    ComposerKt.sourceInformation($composer, "165@6876L11");
                    auroraHairline = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                } else {
                    $composer.startReplaceGroup(-1660049771);
                    ComposerKt.sourceInformation($composer, "165@6893L14");
                    auroraHairline = AuroraColorSchemeKt.getAuroraHairline($composer, 0);
                }
                $composer.endReplaceGroup();
                final Function1<? super String, Unit> function3 = function2;
                int $changed$iv2 = $changed$iv;
                Modifier modifierM589backgroundbw27NRU$default = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierClip, fM244getXxsD9Ej5fM, auroraHairline, AuroraShapeKt.getAuroraShapeMd()), AuroraColorSchemeKt.getAuroraSurface($composer, 0), null, 2, null);
                $composer.startReplaceGroup(-1660043008);
                ComposerKt.sourceInformation($composer, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                boolean invalid$iv = $composer.changed(function3) | $composer.changed(choice);
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$2.invoke$lambda$4$lambda$3$lambda$1$lambda$0(function3, choice);
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer.endReplaceGroup();
                Modifier modifierM1036paddingVpY3zN4$default = ai.nous.aurora.compat.Compat.m1036paddingVpY3zN4$default(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM589backgroundbw27NRU$default, false, null, null, (Function0) value$iv, 7, null), 0.0f, AuroraSpacing.INSTANCE.m238getMdD9Ej5fM(), 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, centerHorizontally, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv2 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, str);
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer, modifierM1036paddingVpY3zN4$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
                String str3 = str;
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, str2);
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
                String str4 = str2;
                Composer $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                int i2 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -356996969, "C172@7257L197,177@7483L40,178@7586L10,178@7552L63:AssistantSettingsPane.kt#syfnu6");
                AssistOrbKt.m86AssistOrbPreviewhGBTI10(choice.getId(), selected, null, ai.nous.aurora.compat.Compat.m7016constructorimpl(40), null, $composer, 3072, 20);
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(6)), $composer, 6);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(choice.getLabel(), (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                function2 = function3;
                horizontalArrangement$iv = horizontalArrangement$iv2;
                $changed$iv = $changed$iv2;
                $this$forEach$iv = $this$forEach$iv2;
                $i$f$forEach = $i$f$forEach2;
                materialized$iv$iv = materialized$iv$iv2;
                $changed2 = $changed3;
                str = str3;
                str2 = str4;
            }
            $composer.endReplaceGroup();
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
    public static final Unit invoke$lambda$4$lambda$3$lambda$1$lambda$0(Function1 $onOrbStyle, OrbChoice $choice) {
        $onOrbStyle.invoke($choice.getId());
        return Unit.INSTANCE;
    }
}
