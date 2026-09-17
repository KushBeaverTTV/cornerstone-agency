package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.repository.SettingsSearchIndex;
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
import androidx.compose.material3.DividerKt;
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
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsScreens.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class SettingsScreensKt$SettingsHome$3$1$2 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ Function1<? super String, Unit> $onOpenAdvancedCategory;
    final /* synthetic */ Function0<Unit> $onOpenAssistant;
    final /* synthetic */ Function1<? super String, Unit> $onOpenEssentials;
    final /* synthetic */ Function0<Unit> $onOpenMoa;
    final /* synthetic */ List<SettingsSearchIndex.Hit> $searchHits;

    /* JADX INFO: compiled from: SettingsScreens.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SettingsSearchIndex.Kind.values().length];
            try {
                iArr[SettingsSearchIndex.Kind.MoaEditor.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SettingsSearchIndex.Kind.Assistant.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SettingsSearchIndex.Kind.Category.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[SettingsSearchIndex.Kind.Field.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    SettingsScreensKt$SettingsHome$3$1$2(List<SettingsSearchIndex.Hit> list, Function0<Unit> function0, Function0<Unit> function1, Function1<? super String, Unit> function2, Function1<? super String, Unit> function3) {
        this.$searchHits = list;
        this.$onOpenMoa = function0;
        this.$onOpenAssistant = function1;
        this.$onOpenAdvancedCategory = function2;
        this.$onOpenEssentials = function3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        String str;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C411@17423L19,412@17486L14,407@17181L5192:SettingsScreens.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1355925970, $changed, -1, "ai.nous.aurora.presentation.settings.SettingsHome.<anonymous>.<anonymous>.<anonymous> (SettingsScreens.kt:407)");
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(Modifier.Companion, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(4)), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer, 0), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16)));
            List<SettingsSearchIndex.Hit> list = this.$searchHits;
            Function0<Unit> function4 = this.$onOpenMoa;
            Function0<Unit> function5 = this.$onOpenAssistant;
            Function1<? super String, Unit> function6 = this.$onOpenAdvancedCategory;
            Function1<? super String, Unit> function7 = this.$onOpenEssentials;
            String str2 = "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            String str3 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            String str4 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
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
            Composer $composer$iv = $composer;
            String str5 = "C88@4444L9:Column.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i2 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv, -404084252, "C:SettingsScreens.kt#syfnu6");
            $composer$iv.startReplaceGroup(-1121413292);
            ComposerKt.sourceInformation($composer$iv, "*419@17863L1803,415@17648L4357");
            Iterable<SettingsSearchIndex.Hit> $this$forEachIndexed$iv = list;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int index$iv2 = index$iv + 1;
                if (index$iv < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                final SettingsSearchIndex.Hit hit = (SettingsSearchIndex.Hit) item$iv;
                Modifier materialized$iv$iv2 = materialized$iv$iv;
                String str6 = str5;
                int index = index$iv;
                Arrangement.Vertical vertical = verticalArrangement$iv;
                String str7 = str2;
                Modifier modifierM1067heightInVpY3zN4$default = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
                $composer$iv.startReplaceGroup(1978374404);
                ComposerKt.sourceInformation($composer$iv, "CC(remember):SettingsScreens.kt#9igjgp");
                boolean invalid$iv = $composer$iv.changed(hit) | $composer$iv.changed(function4) | $composer$iv.changed(function5) | $composer$iv.changed(function6) | $composer$iv.changed(function7);
                Object value$iv = $composer$iv.rememberedValue();
                if (invalid$iv || value$iv == Composer.Companion.getEmpty()) {
                    str = str6;
                    final Function0<Unit> function8 = function4;
                    final Function0<Unit> function9 = function5;
                    final Function1<String, Unit> function10 = function7;
                    final Function1<String, Unit> function11 = function6;
                    value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.SettingsScreensKt$SettingsHome$3$1$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreensKt$SettingsHome$3$1$2.invoke$lambda$8$lambda$7$lambda$3$lambda$2(hit, function8, function9, function10, function11);
                        }
                    };
                    $composer$iv.updateRememberedValue(value$iv);
                } else {
                    str = str6;
                }
                $composer$iv.endReplaceGroup();
                Modifier modifier$iv2 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM1067heightInVpY3zN4$default, false, null, null, (Function0) value$iv, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer$iv, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv2 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str3);
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer$iv.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str4);
                if (!($composer$iv.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer$iv.startReusableNode();
                if ($composer$iv.getInserting()) {
                    function1 = constructor2;
                    $composer$iv.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer$iv.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i4 = ((384 >> 6) & 112) | 6;
                RowScope $this$invoke_u24lambda_u248_u24lambda_u247_u24lambda_u246 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer$iv, 274437739, "C449@20120L11,450@20207L11,445@19895L585,455@20513L29,456@20575L1104,478@21803L15,476@21712L263:SettingsScreens.kt#syfnu6");
                Modifier modifierClip = ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(28)), RoundedCornerShapeKt.getCircleShape());
                long auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer$iv, 0);
                Modifier modifierM589backgroundbw27NRU$default = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber) : 0.12f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber) : 0.0f), null, 2, null);
                float fM7016constructorimpl = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                long auroraAmber2 = AuroraColorSchemeKt.getAuroraAmber($composer$iv, 0);
                Modifier modifier$iv3 = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default, fM7016constructorimpl, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber2) : 0.4f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber2) : 0.0f), RoundedCornerShapeKt.getCircleShape());
                Alignment contentAlignment$iv = Alignment.Companion.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer$iv, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv3 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str3);
                int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer$iv.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv3);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str4);
                if (!($composer$iv.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer$iv.startReusableNode();
                if ($composer$iv.getInserting()) {
                    function2 = constructor3;
                    $composer$iv.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer$iv.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                Function1<String, Unit> function12 = function6;
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv4, ComposeUiNode.Companion.getSetModifier());
                int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i6 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -749445284, "C453@20416L11,453@20398L48:SettingsScreens.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("✦", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer$iv, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3078, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(12)), $composer$iv, 6);
                Modifier modifier$iv4 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u248_u24lambda_u247_u24lambda_u246, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer$iv, -483455358, str7);
                Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer$iv, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv4 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str3);
                int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer$iv.getCurrentCompositionLocalMap();
                String str8 = str3;
                Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv4);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str4);
                if (!($composer$iv.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer$iv.startReusableNode();
                if ($composer$iv.getInserting()) {
                    function3 = constructor4;
                    $composer$iv.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer$iv.useNode();
                }
                String str9 = str4;
                Composer $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv5, ComposeUiNode.Companion.getSetModifier());
                int i7 = ($changed$iv$iv$iv4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, str);
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i8 = ((0 >> 6) & 112) | 6;
                String str10 = str;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -749174592, "C459@20746L10,457@20641L414:SettingsScreens.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(hit.getLabel(), (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer$iv, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199680, 3120, 120786);
                $composer$iv.startReplaceGroup(1499867712);
                ComposerKt.sourceInformation($composer$iv, "466@21165L29,469@21351L15,467@21235L372");
                if (!StringsKt.isBlank(hit.getSubtitle())) {
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(2)), $composer$iv, 6);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(hit.getSubtitle(), (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3072, 3120, 120818);
                }
                $composer$iv.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("›", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199686, 0, 131026);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer$iv.startReplaceGroup(-1121275523);
                ComposerKt.sourceInformation($composer$iv, "485@22166L14,484@22103L192");
                if (index != CollectionsKt.getLastIndex(list)) {
                    ai.nous.aurora.compat.Compat.m2446HorizontalDivider9IZ8Weo(ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(54), 0.0f, 0.0f, 0.0f, 14, null), 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer$iv, 0), $composer$iv, 6, 2);
                }
                $composer$iv.endReplaceGroup();
                str2 = str7;
                localMap$iv$iv = localMap$iv$iv;
                index$iv = index$iv2;
                $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                materialized$iv$iv = materialized$iv$iv2;
                $composer$iv = $composer$iv;
                verticalArrangement$iv = vertical;
                function6 = function12;
                str3 = str8;
                str4 = str9;
                str5 = str10;
            }
            $composer$iv.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
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
    public static final Unit invoke$lambda$8$lambda$7$lambda$3$lambda$2(SettingsSearchIndex.Hit $hit, Function0 $onOpenMoa, Function0 $onOpenAssistant, Function1 $onOpenEssentials, Function1 $onOpenAdvancedCategory) {
        switch (WhenMappings.$EnumSwitchMapping$0[$hit.getKind().ordinal()]) {
            case 1:
                $onOpenMoa.invoke();
                break;
            case 2:
                $onOpenAssistant.invoke();
                break;
            case 3:
                if (StringsKt.equals($hit.getCategoryId(), "moa", true)) {
                    $onOpenMoa.invoke();
                } else if (!StringsKt.equals($hit.getCategoryId(), "voice", true)) {
                    String it = $hit.getCategoryId();
                    if (it != null) {
                        $onOpenAdvancedCategory.invoke(it);
                    }
                } else {
                    $onOpenAssistant.invoke();
                }
                break;
            case 4:
                String path = $hit.getPath();
                if (path == null) {
                    path = "";
                }
                String essentials = SettingsScreensKt.essentialsSectionForPath(path);
                if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(path, "moa.", false, 2, (Object) null) || StringsKt.equals($hit.getCategoryId(), "moa", true)) {
                    $onOpenMoa.invoke();
                } else if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(path, "voice.", false, 2, (Object) null) || StringsKt.equals($hit.getCategoryId(), "voice", true)) {
                    $onOpenAssistant.invoke();
                } else if (essentials == null) {
                    String it2 = $hit.getCategoryId();
                    if (it2 != null) {
                        $onOpenAdvancedCategory.invoke(it2);
                    }
                } else {
                    $onOpenEssentials.invoke(essentials);
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return Unit.INSTANCE;
    }
}
