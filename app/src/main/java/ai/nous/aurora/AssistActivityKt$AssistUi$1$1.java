package ai.nous.aurora;

import ai.nous.aurora.presentation.assist.AssistPresenceOrbKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraMotion;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
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
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
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
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AssistActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class AssistActivityKt$AssistUi$1$1 implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
    final /* synthetic */ boolean $listening;
    final /* synthetic */ Function0<Unit> $onClose;
    final /* synthetic */ Function0<Unit> $onOrbDown;
    final /* synthetic */ Function1<? super Boolean, Unit> $onToggleSpeak;
    final /* synthetic */ String $orbStyle;
    final /* synthetic */ String $phase;
    final /* synthetic */ String $reply;
    final /* synthetic */ boolean $speakReplies;
    final /* synthetic */ String $statusLine;
    final /* synthetic */ String $transcript;
    final /* synthetic */ boolean $working;

    /* JADX WARN: Multi-variable type inference failed */
    AssistActivityKt$AssistUi$1$1(Function0<Unit> function0, boolean z, Function1<? super Boolean, Unit> function1, String str, String str2, Function0<Unit> function2, String str3, boolean z2, boolean z3, String str4, String str5) {
        this.$onClose = function0;
        this.$speakReplies = z;
        this.$onToggleSpeak = function1;
        this.$phase = str;
        this.$orbStyle = str2;
        this.$onOrbDown = function2;
        this.$statusLine = str3;
        this.$listening = z2;
        this.$working = z3;
        this.$transcript = str4;
        this.$reply = str5;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
        invoke(boxWithConstraintsScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:104:0x0572  */
    /* JADX WARN: Code duplicated, block: B:105:0x0575  */
    /* JADX WARN: Code duplicated, block: B:108:0x057b  */
    /* JADX WARN: Code duplicated, block: B:109:0x058c  */
    /* JADX WARN: Code duplicated, block: B:112:0x05c8  */
    /* JADX WARN: Code duplicated, block: B:113:0x05df  */
    /* JADX WARN: Code duplicated, block: B:125:0x0718  */
    /* JADX WARN: Code duplicated, block: B:128:0x0724  */
    /* JADX WARN: Code duplicated, block: B:129:0x072a  */
    /* JADX WARN: Code duplicated, block: B:140:0x07f2  */
    /* JADX WARN: Code duplicated, block: B:141:0x07f9  */
    /* JADX WARN: Code duplicated, block: B:144:0x0814  */
    /* JADX WARN: Code duplicated, block: B:145:0x0819  */
    /* JADX WARN: Code duplicated, block: B:148:0x0885  */
    /* JADX WARN: Code duplicated, block: B:150:0x08a6  */
    /* JADX WARN: Code duplicated, block: B:151:0x08ab  */
    /* JADX WARN: Code duplicated, block: B:154:0x08b7  */
    /* JADX WARN: Code duplicated, block: B:155:0x08c0  */
    /* JADX WARN: Code duplicated, block: B:159:0x0906  */
    /* JADX WARN: Code duplicated, block: B:161:0x092d  */
    /* JADX WARN: Code duplicated, block: B:162:0x0936  */
    /* JADX WARN: Code duplicated, block: B:166:0x0a03  */
    /* JADX WARN: Code duplicated, block: B:169:0x0a0f  */
    /* JADX WARN: Code duplicated, block: B:170:0x0a15  */
    /* JADX WARN: Code duplicated, block: B:181:0x0abf  */
    /* JADX WARN: Code duplicated, block: B:183:0x0afb  */
    /* JADX WARN: Code duplicated, block: B:184:0x0afe  */
    /* JADX WARN: Code duplicated, block: B:186:0x0b53  */
    /* JADX WARN: Code duplicated, block: B:189:0x0b6d  */
    /* JADX WARN: Code duplicated, block: B:191:0x0ba9  */
    /* JADX WARN: Code duplicated, block: B:192:0x0bac  */
    /* JADX WARN: Code duplicated, block: B:213:0x0cb7  */
    /* JADX WARN: Code duplicated, block: B:216:0x0cf0  */
    /* JADX WARN: Code duplicated, block: B:219:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:63:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:64:0x02cd  */
    /* JADX WARN: Code duplicated, block: B:75:0x036d  */
    /* JADX WARN: Code duplicated, block: B:76:0x0372  */
    /* JADX WARN: Code duplicated, block: B:79:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:80:0x03c0  */
    /* JADX WARN: Code duplicated, block: B:83:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:84:0x03ea  */
    /* JADX WARN: Code duplicated, block: B:87:0x043e  */
    /* JADX WARN: Code duplicated, block: B:89:0x04c3  */
    /* JADX WARN: Code duplicated, block: B:92:0x04cf  */
    /* JADX WARN: Code duplicated, block: B:93:0x04d5  */
    public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer $composer, int $changed) {
        float orbSize;
        Function0<ComposeUiNode> function0;
        CompositionLocalMap localMap$iv$iv;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        long sp;
        Object it$iv;
        Object value$iv;
        AuroraSpacing auroraSpacing;
        float fM238getMdD9Ej5fM;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function2;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        float fM7016constructorimpl;
        long sp2;
        int compositeKeyHash$iv$iv3;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function3;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        String str;
        String str2;
        Object value$iv2;
        int i;
        int i2;
        long sp3;
        String str3;
        long sp4;
        int compositeKeyHash$iv$iv4;
        Function0<ComposeUiNode> constructor4;
        Function0<ComposeUiNode> function4;
        Composer $this$Layout_u24lambda_u240$iv$iv4;
        String str4;
        long auroraTextMuted;
        long auroraPillIdleBg;
        boolean invalid$iv;
        Object value$iv3;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation($composer, "C493@19190L5470:AssistActivity.kt#l0z6fd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(BoxWithConstraints) ? 4 : 2;
        }
        if (($dirty & 19) == 18 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-64316006, $dirty, -1, "ai.nous.aurora.AssistUi.<anonymous>.<anonymous> (AssistActivity.kt:481)");
        }
        boolean coverLike = ai.nous.aurora.compat.Compat.m7015compareTo0680j_4(ai.nous.aurora.compat.Compat.mo942getMaxHeightD9Ej5fM(BoxWithConstraints), ai.nous.aurora.compat.Compat.m7016constructorimpl((float) AuroraMotion.ELLIPSIS_STEP_MS)) < 0 || ai.nous.aurora.compat.Compat.m7015compareTo0680j_4(ai.nous.aurora.compat.Compat.mo943getMaxWidthD9Ej5fM(BoxWithConstraints), ai.nous.aurora.compat.Compat.m7016constructorimpl((float) 320)) < 0;
        if (coverLike) {
            float side = Math.min(ai.nous.aurora.compat.Compat.mo943getMaxWidthD9Ej5fM(BoxWithConstraints), ai.nous.aurora.compat.Compat.mo942getMaxHeightD9Ej5fM(BoxWithConstraints));
            float $this$dp$iv = 0.42f * side;
            orbSize = ai.nous.aurora.compat.Compat.m7030unboximpl((Dp) RangesKt.coerceIn(ai.nous.aurora.compat.Compat.m7014boximpl(ai.nous.aurora.compat.Compat.m7016constructorimpl($this$dp$iv)), ai.nous.aurora.compat.Compat.m7014boximpl(ai.nous.aurora.compat.Compat.m7016constructorimpl(96)), ai.nous.aurora.compat.Compat.m7014boximpl(ai.nous.aurora.compat.Compat.m7016constructorimpl(140))));
        } else {
            orbSize = ai.nous.aurora.compat.Compat.m7015compareTo0680j_4(ai.nous.aurora.compat.Compat.mo942getMaxHeightD9Ej5fM(BoxWithConstraints), ai.nous.aurora.compat.Compat.m7016constructorimpl((float) 640)) < 0 ? ai.nous.aurora.compat.Compat.m7016constructorimpl(176) : ai.nous.aurora.compat.Compat.m7016constructorimpl(220);
        }
        AuroraSpacing auroraSpacing2 = AuroraSpacing.INSTANCE;
        float hPad = coverLike ? auroraSpacing2.m237getLgD9Ej5fM() : auroraSpacing2.m241getXlD9Ej5fM();
        float transcriptCap = coverLike ? ai.nous.aurora.compat.Compat.m7016constructorimpl(96) : ai.nous.aurora.compat.Compat.m7016constructorimpl(180);
        Modifier modifierFillMaxSize$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
        AuroraSpacing auroraSpacing3 = AuroraSpacing.INSTANCE;
        Modifier modifierM1035paddingVpY3zN4 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(modifierFillMaxSize$default, hPad, coverLike ? auroraSpacing3.m238getMdD9Ej5fM() : auroraSpacing3.m241getXlD9Ej5fM());
        Alignment.Horizontal centerHorizontally = Alignment.Companion.getCenterHorizontally();
        Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
        Function0<Unit> function5 = this.$onClose;
        final boolean z = this.$speakReplies;
        final Function1<? super Boolean, Unit> function6 = this.$onToggleSpeak;
        String str5 = this.$phase;
        String str6 = this.$orbStyle;
        Function0<Unit> function7 = this.$onOrbDown;
        String str7 = this.$statusLine;
        boolean z2 = this.$listening;
        float transcriptCap2 = transcriptCap;
        boolean z3 = this.$working;
        String str8 = this.$transcript;
        String str9 = this.$reply;
        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(spaceBetween, centerHorizontally, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
        int $changed$iv$iv = (432 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifierM1035paddingVpY3zN4);
        Function0<ComposeUiNode> constructor5 = ComposeUiNode.Companion.getConstructor();
        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
        float orbSize2 = orbSize;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            function0 = constructor5;
            $composer.createNode(function0);
        } else {
            function0 = constructor5;
            $composer.useNode();
        }
        Composer $this$Layout_u24lambda_u240$iv$iv5 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
            localMap$iv$iv = localMap$iv$iv2;
            if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i4 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 964367386, "C500@19520L2050,542@21584L1645,585@23410L21,581@23243L1407:AssistActivity.kt#l0z6fd");
            Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier modifier$iv = Modifier.Companion;
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv2 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function1 = constructor;
                $composer.createNode(function1);
            } else {
                function1 = constructor;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
            int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i6 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -879922466, "C503@19661L15,509@19953L39,501@19597L612:AssistActivity.kt#l0z6fd");
            long auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
            if (coverLike) {
                sp = TextUnitKt.getSp(12);
            } else {
                sp = TextUnitKt.getSp(14);
            }
            long j = sp;
            FontWeight semiBold = FontWeight.Companion.getSemiBold();
            long sp5 = TextUnitKt.getSp(2);
            Modifier modifierFillMaxWidth$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null);
            $composer.startReplaceGroup(802908869);
            ComposerKt.sourceInformation($composer, "CC(remember):AssistActivity.kt#9igjgp");
            it$iv = $composer.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function1() { // from class: ai.nous.aurora.AssistActivityKt$AssistUi$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistActivityKt$AssistUi$1$1.invoke$lambda$10$lambda$5$lambda$1$lambda$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceGroup();
            Modifier modifierM622clickableXHw0xAI$default = ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierFillMaxWidth$default, false, (Function1) value$iv, 1, null), false, null, null, function5, 7, null);
            auroraSpacing = AuroraSpacing.INSTANCE;
            if (coverLike) {
                fM238getMdD9Ej5fM = auroraSpacing.m242getXsD9Ej5fM();
            } else {
                fM238getMdD9Ej5fM = auroraSpacing.m238getMdD9Ej5fM();
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("AURORA", ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(modifierM622clickableXHw0xAI$default, 0.0f, fM238getMdD9Ej5fM, 0.0f, 0.0f, 13, null), auroraTextMuted2, j, (FontStyle) null, semiBold, (FontFamily) null, sp5, (TextDecoration) null, ai.nous.aurora.compat.Compat.m6876boximpl(ai.nous.aurora.compat.Compat.m6883getCentere0LSkKk(TextAlign.Companion)), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12779526, 0, 130384);
            $composer.startReplaceGroup(802918896);
            ComposerKt.sourceInformation($composer, "515@20264L41,516@20326L1212");
            if (!coverLike) {
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, AuroraSpacing.INSTANCE.m240getSmD9Ej5fM()), $composer, 6);
                Modifier modifier$iv2 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null);
                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
                Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                int $changed$iv$iv3 = (438 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer, modifier$iv2);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function4 = constructor4;
                    $composer.createNode(function4);
                } else {
                    function4 = constructor4;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash3);
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                int i7 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i8 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1390804797, "C535@21367L32,521@20579L937:AssistActivity.kt#l0z6fd");
                if (z) {
                    str4 = "Voice replies ON";
                } else {
                    str4 = "Voice replies OFF";
                }
                String str10 = str4;
                if (z) {
                    $composer.startReplaceGroup(-786415192);
                    ComposerKt.sourceInformation($composer, "523@20737L11");
                    auroraTextMuted = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                } else {
                    $composer.startReplaceGroup(-786414644);
                    ComposerKt.sourceInformation($composer, "523@20754L15");
                    auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                }
                $composer.endReplaceGroup();
                long j2 = auroraTextMuted;
                long sp6 = TextUnitKt.getSp(13);
                FontWeight medium = FontWeight.Companion.getMedium();
                Modifier modifierClip = ClipKt.clip(Modifier.Companion, RoundedCornerShapeKt.getCircleShape());
                $composer.startReplaceGroup(-786404732);
                ComposerKt.sourceInformation($composer, "530@21117L11");
                if (z) {
                    long auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    auroraPillIdleBg = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber) : 0.18f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber) : 0.0f);
                } else {
                    auroraPillIdleBg = AuroraColorSchemeKt.getAuroraPillIdleBg();
                }
                $composer.endReplaceGroup();
                Modifier modifierM589backgroundbw27NRU$default = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip, auroraPillIdleBg, null, 2, null);
                $composer.startReplaceGroup(-786395011);
                ComposerKt.sourceInformation($composer, "CC(remember):AssistActivity.kt#9igjgp");
                invalid$iv = $composer.changed(function6) | $composer.changed(z);
                Object it$iv2 = $composer.rememberedValue();
                if (!invalid$iv || it$iv2 == Composer.Companion.getEmpty()) {
                    value$iv3 = new Function0() { // from class: ai.nous.aurora.AssistActivityKt$AssistUi$1$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AssistActivityKt$AssistUi$1$1.invoke$lambda$10$lambda$5$lambda$4$lambda$3$lambda$2(function6, z);
                        }
                    };
                    $composer.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv2;
                }
                $composer.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str10, ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM589backgroundbw27NRU$default, false, null, null, (Function0) value$iv3, 7, null), AuroraSpacing.INSTANCE.m237getLgD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), j2, sp6, (FontStyle) null, medium, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 131024);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier modifier$iv3 = Modifier.Companion;
            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv4 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer, modifier$iv3);
            constructor2 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function2 = constructor2;
                $composer.createNode(function2);
            } else {
                function2 = constructor2;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash4);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv4, ComposeUiNode.Companion.getSetModifier());
            int i9 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
            int i10 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -877887533, "C543@21661L186,549@21864L67,552@22014L10,550@21948L341:AssistActivity.kt#l0z6fd");
            AssistPresenceOrbKt.m102AssistPresenceOrbFJfuzF0(str5, str6, function7, null, orbSize2, $composer, 0, 8);
            Modifier companion = Modifier.Companion;
            if (coverLike) {
                fM7016constructorimpl = AuroraSpacing.INSTANCE.m238getMdD9Ej5fM();
            } else {
                fM7016constructorimpl = ai.nous.aurora.compat.Compat.m7016constructorimpl(20);
            }
            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(companion, fM7016constructorimpl), $composer, 0);
            long auroraText = AuroraColorSchemeKt.getAuroraText($composer, 0);
            if (coverLike) {
                sp2 = TextUnitKt.getSp(15);
            } else {
                sp2 = TextUnitKt.getSp(18);
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str7, ai.nous.aurora.compat.Compat.m1036paddingVpY3zN4$default(Modifier.Companion, AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), 0.0f, 2, null), auroraText, sp2, (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, ai.nous.aurora.compat.Compat.m6876boximpl(ai.nous.aurora.compat.Compat.m6883getCentere0LSkKk(TextAlign.Companion)), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196656, 0, 130512);
            $composer.startReplaceGroup(802984693);
            ComposerKt.sourceInformation($composer, "559@22343L61,566@22699L11,560@22425L430");
            if (z2) {
                Modifier companion2 = Modifier.Companion;
                float arg0$iv = AuroraSpacing.INSTANCE.m240getSmD9Ej5fM();
                float other$iv = AuroraSpacing.INSTANCE.m244getXxsD9Ej5fM();
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(companion2, ai.nous.aurora.compat.Compat.m7016constructorimpl(arg0$iv - other$iv)), $composer, 6);
                if (coverLike) {
                    str3 = "Talk free — power again to send";
                } else {
                    str3 = "Talk free — short-hold power again to send";
                }
                long auroraAmber2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                if (coverLike) {
                    sp4 = TextUnitKt.getSp(12);
                } else {
                    sp4 = TextUnitKt.getSp(14);
                }
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str3, (Modifier) null, auroraAmber2, sp4, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, ai.nous.aurora.compat.Compat.m6876boximpl(ai.nous.aurora.compat.Compat.m6883getCentere0LSkKk(TextAlign.Companion)), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 0, 0, 130546);
            }
            $composer.endReplaceGroup();
            $composer.startReplaceGroup(803003139);
            ComposerKt.sourceInformation($composer, "572@22925L61,575@23091L15,573@23007L190");
            if (z3) {
                Modifier companion3 = Modifier.Companion;
                float arg0$iv2 = AuroraSpacing.INSTANCE.m240getSmD9Ej5fM();
                float other$iv2 = AuroraSpacing.INSTANCE.m244getXxsD9Ej5fM();
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(companion3, ai.nous.aurora.compat.Compat.m7016constructorimpl(arg0$iv2 - other$iv2)), $composer, 6);
                long auroraTextMuted3 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                if (coverLike) {
                    sp3 = TextUnitKt.getSp(12);
                } else {
                    sp3 = TextUnitKt.getSp(14);
                }
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Hermes is working…", (Modifier) null, auroraTextMuted3, sp3, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131058);
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv4 = ai.nous.aurora.compat.Compat.d_ScrollKt_verticalScroll_7(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), 0.0f, transcriptCap2, 1, null), ScrollKt.rememberScrollState(0, $composer, 0, 1), false, null, false, 14, null);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv3 = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv5 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv5 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv6 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer, modifier$iv4);
            constructor3 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function3 = constructor3;
                $composer.createNode(function3);
            } else {
                function3 = constructor3;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv5, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash5);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv5, ComposeUiNode.Companion.getSetModifier());
            int i11 = ($changed$iv$iv$iv5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            int i12 = ((0 >> 6) & 112) | 6;
            ColumnScope $this$invoke_u24lambda_u2410_u24lambda_u249 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -876108877, "C:AssistActivity.kt#l0z6fd");
            $composer.startReplaceGroup(803021552);
            ComposerKt.sourceInformation($composer, "588@23537L15,588@23517L54,589@23617L10,589@23592L80,590@23693L61");
            str = str8;
            if (!StringsKt.isBlank(str)) {
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("You", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131058);
                long auroraText2 = AuroraColorSchemeKt.getAuroraText($composer, 0);
                if (coverLike) {
                    i2 = 13;
                } else {
                    i2 = 15;
                }
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str8, (Modifier) null, auroraText2, TextUnitKt.getSp(i2), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 0, 0, 131058);
                Modifier companion4 = Modifier.Companion;
                float arg0$iv3 = AuroraSpacing.INSTANCE.m238getMdD9Ej5fM();
                float other$iv3 = AuroraSpacing.INSTANCE.m244getXxsD9Ej5fM();
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(companion4, ai.nous.aurora.compat.Compat.m7016constructorimpl(arg0$iv3 - other$iv3)), $composer, 6);
            }
            $composer.endReplaceGroup();
            $composer.startReplaceGroup(803031795);
            ComposerKt.sourceInformation($composer, "593@23858L11,593@23835L53,594@23929L10,594@23909L75");
            str2 = str9;
            if (!StringsKt.isBlank(str2)) {
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Agency", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131058);
                long auroraText3 = AuroraColorSchemeKt.getAuroraText($composer, 0);
                if (coverLike) {
                    i = 13;
                } else {
                    i = 15;
                }
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str9, (Modifier) null, auroraText3, TextUnitKt.getSp(i), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 0, 0, 131058);
            }
            $composer.endReplaceGroup();
            $composer.startReplaceGroup(803039559);
            ComposerKt.sourceInformation($composer, "599@24235L15,604@24489L32,597@24099L519");
            if (!coverLike && StringsKt.isBlank(str) && StringsKt.isBlank(str2)) {
                String str11 = z ? "Voice ON · tap orb" : "Voice OFF · tap orb";
                long auroraTextMuted4 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                long sp7 = TextUnitKt.getSp(12);
                Modifier modifierClip2 = ClipKt.clip($this$invoke_u24lambda_u2410_u24lambda_u249.align(Modifier.Companion, Alignment.Companion.getCenterHorizontally()), RoundedCornerShapeKt.getCircleShape());
                $composer.startReplaceGroup(803054014);
                ComposerKt.sourceInformation($composer, "CC(remember):AssistActivity.kt#9igjgp");
                boolean invalid$iv2 = $composer.changed(function6) | $composer.changed(z);
                Object it$iv3 = $composer.rememberedValue();
                if (invalid$iv2 || it$iv3 == Composer.Companion.getEmpty()) {
                    value$iv2 = new Function0() { // from class: ai.nous.aurora.AssistActivityKt$AssistUi$1$1$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AssistActivityKt$AssistUi$1$1.invoke$lambda$10$lambda$9$lambda$8$lambda$7(function6, z);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv3;
                }
                $composer.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str11, ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierClip2, false, null, null, (Function0) value$iv2, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), auroraTextMuted4, sp7, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 0, 131056);
            }
            $composer.endReplaceGroup();
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
            }
        }
        localMap$iv$iv = localMap$iv$iv2;
        $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
        $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
        int i13 = ($changed$iv$iv$iv >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
        ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
        int i14 = ((432 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, 964367386, "C500@19520L2050,542@21584L1645,585@23410L21,581@23243L1407:AssistActivity.kt#l0z6fd");
        Alignment.Horizontal horizontalAlignment$iv4 = Alignment.Companion.getCenterHorizontally();
        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
        Modifier modifier$iv5 = Modifier.Companion;
        Arrangement.Vertical verticalArrangement$iv4 = Arrangement.INSTANCE.getTop();
        MeasurePolicy measurePolicy$iv6 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv4, horizontalAlignment$iv4, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
        int $changed$iv$iv6 = (384 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv7 = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer, modifier$iv5);
        constructor = ComposeUiNode.Companion.getConstructor();
        int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            function1 = constructor;
            $composer.createNode(function1);
        } else {
            function1 = constructor;
            $composer.useNode();
        }
        $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv6, ComposeUiNode.Companion.getSetMeasurePolicy());
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv7, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.Companion.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
        }
        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash6);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv6, ComposeUiNode.Companion.getSetModifier());
        int i15 = ($changed$iv$iv$iv6 >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
        ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
        int i16 = ((384 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -879922466, "C503@19661L15,509@19953L39,501@19597L612:AssistActivity.kt#l0z6fd");
        long auroraTextMuted5 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
        if (coverLike) {
            sp = TextUnitKt.getSp(12);
        } else {
            sp = TextUnitKt.getSp(14);
        }
        long j3 = sp;
        FontWeight semiBold2 = FontWeight.Companion.getSemiBold();
        long sp8 = TextUnitKt.getSp(2);
        Modifier modifierFillMaxWidth$default2 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null);
        $composer.startReplaceGroup(802908869);
        ComposerKt.sourceInformation($composer, "CC(remember):AssistActivity.kt#9igjgp");
        it$iv = $composer.rememberedValue();
        if (it$iv == Composer.Companion.getEmpty()) {
            value$iv = new Function1() { // from class: ai.nous.aurora.AssistActivityKt$AssistUi$1$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AssistActivityKt$AssistUi$1$1.invoke$lambda$10$lambda$5$lambda$1$lambda$0((SemanticsPropertyReceiver) obj);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        $composer.endReplaceGroup();
        Modifier modifierM622clickableXHw0xAI$default2 = ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierFillMaxWidth$default2, false, (Function1) value$iv, 1, null), false, null, null, function5, 7, null);
        auroraSpacing = AuroraSpacing.INSTANCE;
        if (coverLike) {
            fM238getMdD9Ej5fM = auroraSpacing.m242getXsD9Ej5fM();
        } else {
            fM238getMdD9Ej5fM = auroraSpacing.m238getMdD9Ej5fM();
        }
        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("AURORA", ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(modifierM622clickableXHw0xAI$default2, 0.0f, fM238getMdD9Ej5fM, 0.0f, 0.0f, 13, null), auroraTextMuted5, j3, (FontStyle) null, semiBold2, (FontFamily) null, sp8, (TextDecoration) null, ai.nous.aurora.compat.Compat.m6876boximpl(ai.nous.aurora.compat.Compat.m6883getCentere0LSkKk(TextAlign.Companion)), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12779526, 0, 130384);
        $composer.startReplaceGroup(802918896);
        ComposerKt.sourceInformation($composer, "515@20264L41,516@20326L1212");
        if (!coverLike) {
            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, AuroraSpacing.INSTANCE.m240getSmD9Ej5fM()), $composer, 6);
            Modifier modifier$iv6 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null);
            Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getCenter();
            Alignment.Vertical verticalAlignment$iv2 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv7 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv7 = (438 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv8 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer, modifier$iv6);
            constructor4 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv7 = (($changed$iv$iv7 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function4 = constructor4;
                $composer.createNode(function4);
            } else {
                function4 = constructor4;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv7, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv8, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash7);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv7, ComposeUiNode.Companion.getSetModifier());
            int i17 = ($changed$iv$iv$iv7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i18 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1390804797, "C535@21367L32,521@20579L937:AssistActivity.kt#l0z6fd");
            if (z) {
                str4 = "Voice replies ON";
            } else {
                str4 = "Voice replies OFF";
            }
            String str12 = str4;
            if (z) {
                $composer.startReplaceGroup(-786415192);
                ComposerKt.sourceInformation($composer, "523@20737L11");
                auroraTextMuted = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
            } else {
                $composer.startReplaceGroup(-786414644);
                ComposerKt.sourceInformation($composer, "523@20754L15");
                auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
            }
            $composer.endReplaceGroup();
            long j4 = auroraTextMuted;
            long sp9 = TextUnitKt.getSp(13);
            FontWeight medium2 = FontWeight.Companion.getMedium();
            Modifier modifierClip3 = ClipKt.clip(Modifier.Companion, RoundedCornerShapeKt.getCircleShape());
            $composer.startReplaceGroup(-786404732);
            ComposerKt.sourceInformation($composer, "530@21117L11");
            if (z) {
                long auroraAmber3 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                auroraPillIdleBg = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber3) : 0.18f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber3) : 0.0f);
            } else {
                auroraPillIdleBg = AuroraColorSchemeKt.getAuroraPillIdleBg();
            }
            $composer.endReplaceGroup();
            Modifier modifierM589backgroundbw27NRU$default2 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip3, auroraPillIdleBg, null, 2, null);
            $composer.startReplaceGroup(-786395011);
            ComposerKt.sourceInformation($composer, "CC(remember):AssistActivity.kt#9igjgp");
            invalid$iv = $composer.changed(function6) | $composer.changed(z);
            Object it$iv4 = $composer.rememberedValue();
            if (invalid$iv) {
            }
            value$iv3 = new Function0() { // from class: ai.nous.aurora.AssistActivityKt$AssistUi$1$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AssistActivityKt$AssistUi$1$1.invoke$lambda$10$lambda$5$lambda$4$lambda$3$lambda$2(function6, z);
                }
            };
            $composer.updateRememberedValue(value$iv3);
            $composer.endReplaceGroup();
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str12, ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM589backgroundbw27NRU$default2, false, null, null, (Function0) value$iv3, 7, null), AuroraSpacing.INSTANCE.m237getLgD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), j4, sp9, (FontStyle) null, medium2, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 131024);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        $composer.endReplaceGroup();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Alignment.Horizontal horizontalAlignment$iv5 = Alignment.Companion.getCenterHorizontally();
        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
        Modifier modifier$iv7 = Modifier.Companion;
        Arrangement.Vertical verticalArrangement$iv5 = Arrangement.INSTANCE.getTop();
        MeasurePolicy measurePolicy$iv8 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv5, horizontalAlignment$iv5, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
        int $changed$iv$iv8 = (384 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv9 = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer, modifier$iv7);
        constructor2 = ComposeUiNode.Companion.getConstructor();
        int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            function2 = constructor2;
            $composer.createNode(function2);
        } else {
            function2 = constructor2;
            $composer.useNode();
        }
        $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv8, ComposeUiNode.Companion.getSetMeasurePolicy());
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv9, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.Companion.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
        }
        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash8);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv8, ComposeUiNode.Companion.getSetModifier());
        int i19 = ($changed$iv$iv$iv8 >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
        ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
        int i110 = ((384 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -877887533, "C543@21661L186,549@21864L67,552@22014L10,550@21948L341:AssistActivity.kt#l0z6fd");
        AssistPresenceOrbKt.m102AssistPresenceOrbFJfuzF0(str5, str6, function7, null, orbSize2, $composer, 0, 8);
        Modifier companion5 = Modifier.Companion;
        if (coverLike) {
            fM7016constructorimpl = AuroraSpacing.INSTANCE.m238getMdD9Ej5fM();
        } else {
            fM7016constructorimpl = ai.nous.aurora.compat.Compat.m7016constructorimpl(20);
        }
        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(companion5, fM7016constructorimpl), $composer, 0);
        long auroraText4 = AuroraColorSchemeKt.getAuroraText($composer, 0);
        if (coverLike) {
            sp2 = TextUnitKt.getSp(15);
        } else {
            sp2 = TextUnitKt.getSp(18);
        }
        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str7, ai.nous.aurora.compat.Compat.m1036paddingVpY3zN4$default(Modifier.Companion, AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), 0.0f, 2, null), auroraText4, sp2, (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, ai.nous.aurora.compat.Compat.m6876boximpl(ai.nous.aurora.compat.Compat.m6883getCentere0LSkKk(TextAlign.Companion)), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196656, 0, 130512);
        $composer.startReplaceGroup(802984693);
        ComposerKt.sourceInformation($composer, "559@22343L61,566@22699L11,560@22425L430");
        if (z2) {
            Modifier companion6 = Modifier.Companion;
            float arg0$iv4 = AuroraSpacing.INSTANCE.m240getSmD9Ej5fM();
            float other$iv4 = AuroraSpacing.INSTANCE.m244getXxsD9Ej5fM();
            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(companion6, ai.nous.aurora.compat.Compat.m7016constructorimpl(arg0$iv4 - other$iv4)), $composer, 6);
            if (coverLike) {
                str3 = "Talk free — power again to send";
            } else {
                str3 = "Talk free — short-hold power again to send";
            }
            long auroraAmber4 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
            if (coverLike) {
                sp4 = TextUnitKt.getSp(12);
            } else {
                sp4 = TextUnitKt.getSp(14);
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str3, (Modifier) null, auroraAmber4, sp4, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, ai.nous.aurora.compat.Compat.m6876boximpl(ai.nous.aurora.compat.Compat.m6883getCentere0LSkKk(TextAlign.Companion)), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 0, 0, 130546);
        }
        $composer.endReplaceGroup();
        $composer.startReplaceGroup(803003139);
        ComposerKt.sourceInformation($composer, "572@22925L61,575@23091L15,573@23007L190");
        if (z3) {
            Modifier companion7 = Modifier.Companion;
            float arg0$iv5 = AuroraSpacing.INSTANCE.m240getSmD9Ej5fM();
            float other$iv5 = AuroraSpacing.INSTANCE.m244getXxsD9Ej5fM();
            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(companion7, ai.nous.aurora.compat.Compat.m7016constructorimpl(arg0$iv5 - other$iv5)), $composer, 6);
            long auroraTextMuted6 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
            if (coverLike) {
                sp3 = TextUnitKt.getSp(12);
            } else {
                sp3 = TextUnitKt.getSp(14);
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Hermes is working…", (Modifier) null, auroraTextMuted6, sp3, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131058);
        }
        $composer.endReplaceGroup();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifier$iv8 = ai.nous.aurora.compat.Compat.d_ScrollKt_verticalScroll_7(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), 0.0f, transcriptCap2, 1, null), ScrollKt.rememberScrollState(0, $composer, 0, 1), false, null, false, 14, null);
        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
        Arrangement.Vertical verticalArrangement$iv6 = Arrangement.INSTANCE.getTop();
        Alignment.Horizontal horizontalAlignment$iv6 = Alignment.Companion.getStart();
        MeasurePolicy measurePolicy$iv9 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv6, horizontalAlignment$iv6, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
        int $changed$iv$iv9 = (0 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv10 = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv9 = ComposedModifierKt.materializeModifier($composer, modifier$iv8);
        constructor3 = ComposeUiNode.Companion.getConstructor();
        int $changed$iv$iv$iv9 = (($changed$iv$iv9 << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            function3 = constructor3;
            $composer.createNode(function3);
        } else {
            function3 = constructor3;
            $composer.useNode();
        }
        $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv9, ComposeUiNode.Companion.getSetMeasurePolicy());
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv10, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.Companion.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
        }
        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash9);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv9, ComposeUiNode.Companion.getSetModifier());
        int i111 = ($changed$iv$iv$iv9 >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
        int i112 = ((0 >> 6) & 112) | 6;
        ColumnScope $this$invoke_u24lambda_u2410_u24lambda_u2410 = ColumnScopeInstance.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, -876108877, "C:AssistActivity.kt#l0z6fd");
        $composer.startReplaceGroup(803021552);
        ComposerKt.sourceInformation($composer, "588@23537L15,588@23517L54,589@23617L10,589@23592L80,590@23693L61");
        str = str8;
        if (!StringsKt.isBlank(str)) {
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("You", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131058);
            long auroraText5 = AuroraColorSchemeKt.getAuroraText($composer, 0);
            if (coverLike) {
                i2 = 13;
            } else {
                i2 = 15;
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str8, (Modifier) null, auroraText5, TextUnitKt.getSp(i2), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 0, 0, 131058);
            Modifier companion8 = Modifier.Companion;
            float arg0$iv6 = AuroraSpacing.INSTANCE.m238getMdD9Ej5fM();
            float other$iv6 = AuroraSpacing.INSTANCE.m244getXxsD9Ej5fM();
            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(companion8, ai.nous.aurora.compat.Compat.m7016constructorimpl(arg0$iv6 - other$iv6)), $composer, 6);
        }
        $composer.endReplaceGroup();
        $composer.startReplaceGroup(803031795);
        ComposerKt.sourceInformation($composer, "593@23858L11,593@23835L53,594@23929L10,594@23909L75");
        str2 = str9;
        if (!StringsKt.isBlank(str2)) {
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Agency", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131058);
            long auroraText6 = AuroraColorSchemeKt.getAuroraText($composer, 0);
            if (coverLike) {
                i = 13;
            } else {
                i = 15;
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str9, (Modifier) null, auroraText6, TextUnitKt.getSp(i), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 0, 0, 131058);
        }
        $composer.endReplaceGroup();
        $composer.startReplaceGroup(803039559);
        ComposerKt.sourceInformation($composer, "599@24235L15,604@24489L32,597@24099L519");
        if (!coverLike) {
        }
        $composer.endReplaceGroup();
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
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$10$lambda$5$lambda$1$lambda$0(SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        SemanticsPropertiesKt.setContentDescription(semantics, "Close Assist");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$10$lambda$5$lambda$4$lambda$3$lambda$2(Function1 $onToggleSpeak, boolean $speakReplies) {
        $onToggleSpeak.invoke(Boolean.valueOf(!$speakReplies));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$10$lambda$9$lambda$8$lambda$7(Function1 $onToggleSpeak, boolean $speakReplies) {
        $onToggleSpeak.invoke(Boolean.valueOf(!$speakReplies));
        return Unit.INSTANCE;
    }
}
