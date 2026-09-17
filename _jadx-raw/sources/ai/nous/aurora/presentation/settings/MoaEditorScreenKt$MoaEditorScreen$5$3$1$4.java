package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.MoaModelSlotDto;
import ai.nous.aurora.data.remote.MoaPresetDto;
import ai.nous.aurora.domain.model.ComposerChipPolicy;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.TextKt;
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
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
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
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$4 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ MoaPresetDto $draft;
    final /* synthetic */ MutableState<Boolean> $referencesExpanded$delegate;

    MoaEditorScreenKt$MoaEditorScreen$5$3$1$4(MutableState<Boolean> mutableState, MoaPresetDto moaPresetDto) {
        this.$referencesExpanded$delegate = mutableState;
        this.$draft = moaPresetDto;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Object value$iv;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Object value$iv2;
        Function0<ComposeUiNode> function2;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C418@18440L19,419@18503L14,420@18585L29,413@18154L3389:MoaEditorScreen.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(950045235, $changed, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:413)");
            }
            Modifier modifierM600borderxT4_qwU = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(PaddingKt.m1035paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), Dp.m7016constructorimpl(4)), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer, 0), null, 2, null), Dp.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer, 0), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16)));
            $composer.startReplaceGroup(482188181);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            final MutableState<Boolean> mutableState = this.$referencesExpanded$delegate;
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$4$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$4.invoke$lambda$1$lambda$0(mutableState);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceGroup();
            Modifier modifier$iv = PaddingKt.m1035paddingVpY3zN4(ClickableKt.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU, false, null, null, (Function0) value$iv, 7, null), Dp.m7016constructorimpl(16), Dp.m7016constructorimpl(14));
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(4));
            MoaPresetDto moaPresetDto = this.$draft;
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            String str = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -142421800, "C426@18933L10,424@18813L265,430@19103L2017,469@21221L132,472@21391L15,468@21145L376:MoaEditorScreen.kt#syfnu6");
            TextKt.m3067Text4IGK_g("Fan-out · " + moaPresetDto.getReferenceModels().size() + " agents", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 131026);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM914spacedBy0680j_4 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(5));
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM914spacedBy0680j_4, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv2 = (438 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $i$f$Layout = $changed$iv$iv2 << 6;
            int $changed$iv$iv$iv2 = ($i$f$Layout & 896) | 6;
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
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
            int i4 = 0;
            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int $changed2 = ((438 >> 6) & 112) | 6;
            RowScope $this$invoke_u24lambda_u248_u24lambda_u244 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -840796929, "C460@20780L27,463@20938L11,461@20836L258:MoaEditorScreen.kt#syfnu6");
            $composer.startReplaceGroup(1773993423);
            ComposerKt.sourceInformation($composer, "*448@20111L12,449@20199L12,444@19886L835");
            Iterable $this$forEachIndexed$iv = CollectionsKt.take(moaPresetDto.getReferenceModels(), 4);
            int $i$f$forEachIndexed = 0;
            int index = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int index$iv = index + 1;
                if (index < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                $composer.startReplaceGroup(1773994416);
                ComposerKt.sourceInformation($composer, "441@19765L14,437@19528L291");
                if (index > 0) {
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                }
                $composer.endReplaceGroup();
                Modifier modifierClip = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(24)), RoundedCornerShapeKt.getCircleShape());
                long auroraAccent = AuroraColorSchemeKt.getAuroraAccent($composer, 0);
                Modifier modifierM589backgroundbw27NRU$default = BackgroundKt.m589backgroundbw27NRU$default(modifierClip, Color.m4532copywmQWz5c(auroraAccent, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAccent) : 0.14f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAccent) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAccent) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAccent) : 0.0f), null, 2, null);
                float fM7016constructorimpl = Dp.m7016constructorimpl(1);
                long auroraAccent2 = AuroraColorSchemeKt.getAuroraAccent($composer, 0);
                int $changed3 = $changed2;
                MeasurePolicy measurePolicy$iv3 = measurePolicy$iv;
                Modifier modifier$iv2 = BorderKt.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default, fM7016constructorimpl, Color.m4532copywmQWz5c(auroraAccent2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAccent2) : 0.5f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAccent2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAccent2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAccent2) : 0.0f), RoundedCornerShapeKt.getCircleShape());
                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv3 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, str);
                int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                String str3 = str;
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer, modifier$iv2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, str2);
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                String str4 = str2;
                Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i6 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1614191655, "C454@20508L12,452@20391L296:MoaEditorScreen.kt#syfnu6");
                TextKt.m3067Text4IGK_g(String.valueOf(index + 1), (Modifier) null, AuroraColorSchemeKt.getAuroraAccent($composer, 0), TextUnitKt.getSp(10), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 131026);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                index = index$iv;
                $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                $i$f$forEachIndexed = $i$f$forEachIndexed;
                $changed2 = $changed3;
                i4 = i4;
                str = str3;
                str2 = str4;
                measurePolicy$iv = measurePolicy$iv3;
            }
            $composer.endReplaceGroup();
            SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u248_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
            TextKt.m3067Text4IGK_g("→  one answer", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            List<MoaModelSlotDto> referenceModels = moaPresetDto.getReferenceModels();
            $composer.startReplaceGroup(-1389993093);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$4$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$4.invoke$lambda$8$lambda$7$lambda$6((MoaModelSlotDto) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer.endReplaceGroup();
            TextKt.m3067Text4IGK_g(CollectionsKt.joinToString$default(referenceModels, str, null, null, 0, null, (Function1) value$iv2, 30, null), (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3072, 122866);
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
    public static final Unit invoke$lambda$1$lambda$0(MutableState $referencesExpanded$delegate) {
        MoaEditorScreenKt.MoaEditorScreen$lambda$46($referencesExpanded$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence invoke$lambda$8$lambda$7$lambda$6(MoaModelSlotDto it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String strFriendlyModel = ComposerChipPolicy.INSTANCE.friendlyModel(it.getModel());
        if (StringsKt.isBlank(strFriendlyModel)) {
            strFriendlyModel = "Unset model";
        }
        return strFriendlyModel;
    }
}
