package ai.nous.aurora.presentation.shell;

import ai.nous.aurora.MainViewModel;
import ai.nous.aurora.domain.model.ComposerChipPolicy;
import ai.nous.aurora.presentation.components.ds.AuroraDsKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.ExpandMoreKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
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
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: InnerShell.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class InnerShellKt$ModelPickerSheet$1$2 implements Function3<AnimatedVisibilityScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<String> $activeProvider$delegate;
    final /* synthetic */ List<MainViewModel.ModelChoice> $deduped;
    final /* synthetic */ Function0<Unit> $onDismiss;
    final /* synthetic */ Function1<? super MainViewModel.ModelChoice, Unit> $onSelect;
    final /* synthetic */ MutableState<Boolean> $providerMenuOpen$delegate;
    final /* synthetic */ List<String> $providerOrder;
    final /* synthetic */ String $selectedModel;
    final /* synthetic */ String $selectedProvider;
    final /* synthetic */ MutableInteractionSource $sheetInteractionSource;
    final /* synthetic */ String $warning;

    /* JADX WARN: Multi-variable type inference failed */
    InnerShellKt$ModelPickerSheet$1$2(MutableInteractionSource mutableInteractionSource, String str, List<MainViewModel.ModelChoice> list, Function0<Unit> function0, MutableState<String> mutableState, MutableState<Boolean> mutableState2, List<String> list2, Function1<? super MainViewModel.ModelChoice, Unit> function1, String str2, String str3) {
        this.$sheetInteractionSource = mutableInteractionSource;
        this.$warning = str;
        this.$deduped = list;
        this.$onDismiss = function0;
        this.$activeProvider$delegate = mutableState;
        this.$providerMenuOpen$delegate = mutableState2;
        this.$providerOrder = list2;
        this.$onSelect = function1;
        this.$selectedModel = str2;
        this.$selectedProvider = str3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
        invoke(animatedVisibilityScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x09b7  */
    /* JADX WARN: Code duplicated, block: B:104:0x09d4 A[LOOP:0: B:99:0x09b1->B:104:0x09d4, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:108:0x09e4  */
    /* JADX WARN: Code duplicated, block: B:111:0x09eb  */
    /* JADX WARN: Code duplicated, block: B:114:0x0a7e  */
    /* JADX WARN: Code duplicated, block: B:115:0x0a8c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0abc  */
    /* JADX WARN: Code duplicated, block: B:122:0x0ac9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:126:0x0b60  */
    /* JADX WARN: Code duplicated, block: B:129:0x0b6c  */
    /* JADX WARN: Code duplicated, block: B:130:0x0b72  */
    /* JADX WARN: Code duplicated, block: B:133:0x0ba5  */
    /* JADX WARN: Code duplicated, block: B:137:0x0bbb A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:141:0x0ccd  */
    /* JADX WARN: Code duplicated, block: B:144:0x0cd9  */
    /* JADX WARN: Code duplicated, block: B:145:0x0cdf  */
    /* JADX WARN: Code duplicated, block: B:148:0x0d12  */
    /* JADX WARN: Code duplicated, block: B:152:0x0d28 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:156:0x0e84  */
    /* JADX WARN: Code duplicated, block: B:157:0x0e90  */
    /* JADX WARN: Code duplicated, block: B:160:0x0f85  */
    /* JADX WARN: Code duplicated, block: B:163:0x0f91  */
    /* JADX WARN: Code duplicated, block: B:164:0x0f97  */
    /* JADX WARN: Code duplicated, block: B:167:0x0fca  */
    /* JADX WARN: Code duplicated, block: B:171:0x0fe0 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:176:0x104b  */
    /* JADX WARN: Code duplicated, block: B:178:0x1069  */
    /* JADX WARN: Code duplicated, block: B:183:0x10a8  */
    /* JADX WARN: Code duplicated, block: B:185:0x10b0  */
    /* JADX WARN: Code duplicated, block: B:188:0x111b  */
    /* JADX WARN: Code duplicated, block: B:189:0x1131  */
    /* JADX WARN: Code duplicated, block: B:191:0x1138  */
    /* JADX WARN: Code duplicated, block: B:192:0x115e  */
    /* JADX WARN: Code duplicated, block: B:195:0x1187  */
    /* JADX WARN: Code duplicated, block: B:196:0x11ac  */
    /* JADX WARN: Code duplicated, block: B:198:0x11b2  */
    /* JADX WARN: Code duplicated, block: B:199:0x11d7  */
    /* JADX WARN: Code duplicated, block: B:202:0x1233  */
    /* JADX WARN: Code duplicated, block: B:205:0x123e  */
    /* JADX WARN: Code duplicated, block: B:206:0x1240  */
    /* JADX WARN: Code duplicated, block: B:210:0x12ef  */
    /* JADX WARN: Code duplicated, block: B:213:0x12fb  */
    /* JADX WARN: Code duplicated, block: B:214:0x1301  */
    /* JADX WARN: Code duplicated, block: B:217:0x1334  */
    /* JADX WARN: Code duplicated, block: B:221:0x134a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:225:0x1416  */
    /* JADX WARN: Code duplicated, block: B:228:0x1422  */
    /* JADX WARN: Code duplicated, block: B:229:0x1428  */
    /* JADX WARN: Code duplicated, block: B:232:0x145b  */
    /* JADX WARN: Code duplicated, block: B:236:0x1471 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:240:0x14c9  */
    /* JADX WARN: Code duplicated, block: B:241:0x14da  */
    /* JADX WARN: Code duplicated, block: B:244:0x1537  */
    /* JADX WARN: Code duplicated, block: B:245:0x153b  */
    /* JADX WARN: Code duplicated, block: B:247:0x1543  */
    /* JADX WARN: Code duplicated, block: B:248:0x1547  */
    /* JADX WARN: Code duplicated, block: B:249:0x156a  */
    /* JADX WARN: Code duplicated, block: B:252:0x1575  */
    /* JADX WARN: Code duplicated, block: B:253:0x157a  */
    /* JADX WARN: Code duplicated, block: B:256:0x1585  */
    /* JADX WARN: Code duplicated, block: B:257:0x15a5  */
    /* JADX WARN: Code duplicated, block: B:260:0x15b0  */
    /* JADX WARN: Code duplicated, block: B:261:0x15d0  */
    /* JADX WARN: Code duplicated, block: B:264:0x15e3  */
    /* JADX WARN: Code duplicated, block: B:265:0x15f4  */
    /* JADX WARN: Code duplicated, block: B:268:0x1659  */
    /* JADX WARN: Code duplicated, block: B:269:0x1697  */
    /* JADX WARN: Code duplicated, block: B:271:0x169d  */
    /* JADX WARN: Code duplicated, block: B:272:0x174a  */
    /* JADX WARN: Code duplicated, block: B:275:0x1770  */
    /* JADX WARN: Code duplicated, block: B:27:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:280:0x1804  */
    /* JADX WARN: Code duplicated, block: B:282:0x09d9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:283:0x09df A[EDGE_INSN: B:283:0x09df->B:106:0x09df BREAK  A[LOOP:0: B:99:0x09b1->B:104:0x09d4], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:286:0x106c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:289:0x1784 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:290:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:31:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:42:0x043a  */
    /* JADX WARN: Code duplicated, block: B:45:0x0446  */
    /* JADX WARN: Code duplicated, block: B:46:0x044c  */
    /* JADX WARN: Code duplicated, block: B:49:0x047f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0492  */
    /* JADX WARN: Code duplicated, block: B:53:0x0495  */
    /* JADX WARN: Code duplicated, block: B:57:0x05b6  */
    /* JADX WARN: Code duplicated, block: B:61:0x05bf  */
    /* JADX WARN: Code duplicated, block: B:63:0x05c2  */
    /* JADX WARN: Code duplicated, block: B:65:0x06cb  */
    /* JADX WARN: Code duplicated, block: B:68:0x06d7  */
    /* JADX WARN: Code duplicated, block: B:69:0x06dd  */
    /* JADX WARN: Code duplicated, block: B:72:0x0710  */
    /* JADX WARN: Code duplicated, block: B:76:0x0726 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:79:0x0818  */
    /* JADX WARN: Code duplicated, block: B:82:0x0825  */
    /* JADX WARN: Code duplicated, block: B:83:0x0879  */
    /* JADX WARN: Code duplicated, block: B:85:0x08fa  */
    /* JADX WARN: Code duplicated, block: B:88:0x0906  */
    /* JADX WARN: Code duplicated, block: B:89:0x090c  */
    /* JADX WARN: Code duplicated, block: B:92:0x093f  */
    /* JADX WARN: Code duplicated, block: B:96:0x0955 A[ADDED_TO_REGION] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        Object value$iv;
        Function0<ComposeUiNode> function0;
        Function1<MainViewModel.ModelChoice, Unit> function1;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function2;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        MeasurePolicy measurePolicy$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function3;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        MeasurePolicy measurePolicy$iv2;
        Function1<MainViewModel.ModelChoice, Unit> function4;
        String str;
        String str2;
        boolean z;
        String str3;
        int compositeKeyHash$iv$iv3;
        Modifier materialized$iv$iv;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function5;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        Iterable $this$firstOrNull$iv;
        Iterator it;
        Object element$iv;
        MainViewModel.ModelChoice modelChoice;
        final String activeProviderLabel;
        Object it$iv;
        Object value$iv2;
        boolean invalid$iv;
        Object value$iv3;
        int compositeKeyHash$iv$iv4;
        Function0<ComposeUiNode> constructor4;
        Function0<ComposeUiNode> function6;
        Composer $this$Layout_u24lambda_u240$iv$iv4;
        String str4;
        String str5;
        int compositeKeyHash$iv$iv5;
        Function0<ComposeUiNode> constructor5;
        Function0<ComposeUiNode> function7;
        Composer $this$Layout_u24lambda_u240$iv$iv5;
        Object it$iv2;
        Object value$iv4;
        Composer $composer2;
        String str6;
        int compositeKeyHash$iv$iv6;
        CompositionLocalMap localMap$iv$iv;
        Function0<ComposeUiNode> constructor6;
        String str7;
        Function0<ComposeUiNode> function8;
        Composer $this$Layout_u24lambda_u240$iv$iv6;
        int $changed$iv;
        Composer $composer$iv;
        int i;
        ColumnScope $this$invoke_u24lambda_u2423_u24lambda_u2422;
        Iterable $this$filter$iv;
        Collection destination$iv$iv;
        List activeChoices;
        Iterable $this$forEachIndexed$iv;
        int $i$f$forEachIndexed;
        int index$iv;
        Iterator it2;
        Composer $composer3;
        final MainViewModel.ModelChoice choice;
        boolean selected;
        long j;
        long jM4532copywmQWz5c;
        boolean invalid$iv2;
        Object it$iv3;
        String str8;
        Object value$iv5;
        int compositeKeyHash$iv$iv7;
        Function0<ComposeUiNode> constructor7;
        Function0<ComposeUiNode> function9;
        Composer $this$Layout_u24lambda_u240$iv$iv7;
        int compositeKeyHash$iv$iv8;
        Function0<ComposeUiNode> constructor8;
        Function0<ComposeUiNode> function10;
        Composer $this$Layout_u24lambda_u240$iv$iv8;
        long auroraTextMuted;
        String role;
        String strFriendlyProvider;
        long auroraError;
        MainViewModel.ModelChoice it3;
        MainViewModel.ModelChoice it4;
        Iterable $this$firstOrNull$iv2;
        Modifier materialized$iv$iv2;
        int compositeKeyHash$iv$iv9;
        Function0<ComposeUiNode> constructor9;
        Function0<ComposeUiNode> function11;
        Composer $this$Layout_u24lambda_u240$iv$iv9;
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C1513@68281L18,1516@68384L19,1522@68702L2,1508@68048L13817:InnerShell.kt#m1syn");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1849447941, $changed, -1, "ai.nous.aurora.presentation.shell.ModelPickerSheet.<anonymous>.<anonymous> (InnerShell.kt:1508)");
        }
        Modifier modifierM589backgroundbw27NRU$default = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.d_SizeKt_wrapContentHeight_5(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), null, false, 3, null), ai.nous.aurora.compat.Compat.m1319RoundedCornerShapea9UjIt4$default(ai.nous.aurora.compat.Compat.m7016constructorimpl(22), ai.nous.aurora.compat.Compat.m7016constructorimpl(22), 0.0f, 0.0f, 12, null)), AuroraColorSchemeKt.getAuroraGlassSurface($composer, 0), null, 2, null);
        float fM7016constructorimpl = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
        long auroraGlassHairline = AuroraColorSchemeKt.getAuroraGlassHairline($composer, 0);
        Modifier modifierM600borderxT4_qwU = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default, fM7016constructorimpl, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraGlassHairline, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraGlassHairline) : 0.85f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraGlassHairline) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraGlassHairline) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraGlassHairline) : 0.0f), ai.nous.aurora.compat.Compat.m1319RoundedCornerShapea9UjIt4$default(ai.nous.aurora.compat.Compat.m7016constructorimpl(22), ai.nous.aurora.compat.Compat.m7016constructorimpl(22), 0.0f, 0.0f, 12, null));
        MutableInteractionSource mutableInteractionSource = this.$sheetInteractionSource;
        $composer.startReplaceGroup(-1901617963);
        String str9 = "CC(remember):InnerShell.kt#9igjgp";
        ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
        Object it$iv4 = $composer.rememberedValue();
        if (it$iv4 == Composer.Companion.getEmpty()) {
            value$iv = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv4;
        }
        $composer.endReplaceGroup();
        Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(ai.nous.aurora.compat.Compat.m619clickableO2vRcR0(modifierM600borderxT4_qwU, mutableInteractionSource, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) value$iv), 0.0f, 0.0f, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(12), 7, null);
        String str10 = this.$warning;
        List<MainViewModel.ModelChoice> list = this.$deduped;
        Function0<Unit> function12 = this.$onDismiss;
        MutableState<String> mutableState = this.$activeProvider$delegate;
        final MutableState<Boolean> mutableState2 = this.$providerMenuOpen$delegate;
        List<String> list2 = this.$providerOrder;
        Function1<? super MainViewModel.ModelChoice, Unit> function13 = this.$onSelect;
        String str11 = this.$selectedModel;
        String str12 = this.$selectedProvider;
        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
        Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
        MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
        int $changed$iv$iv = (0 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer, modifier$iv);
        Function0<ComposeUiNode> constructor10 = ComposeUiNode.Companion.getConstructor();
        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            function0 = constructor10;
            $composer.createNode(function0);
        } else {
            function0 = constructor10;
            $composer.useNode();
        }
        Composer $this$Layout_u24lambda_u240$iv$iv10 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
            function1 = function13;
            if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv10.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv10))) {
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1804908505, "C1527@68881L529,1541@69427L788:InnerShell.kt#m1syn");
            Modifier modifier$iv2 = ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(10), 0.0f, 0.0f, 13, null);
            Alignment contentAlignment$iv = Alignment.Companion.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (54 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer, modifier$iv2);
            constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function2 = constructor;
                $composer.createNode(function2);
            } else {
                function2 = constructor;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.Companion.getSetModifier());
            int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i5 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 705275896, "C1538@69330L19,1533@69110L282:InnerShell.kt#m1syn");
            Modifier modifierClip = ClipKt.clip(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(36)), ai.nous.aurora.compat.Compat.m7016constructorimpl(4)), RoundedCornerShapeKt.getCircleShape());
            long auroraOutlineStrong = AuroraColorSchemeKt.getAuroraOutlineStrong($composer, 0);
            BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraOutlineStrong, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraOutlineStrong) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraOutlineStrong) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraOutlineStrong) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraOutlineStrong) : 0.0f), null, 2, null), $composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv3 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(16), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            int $changed$iv$iv3 = (390 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer, modifier$iv3);
            constructor2 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function3 = constructor2;
                $composer.createNode(function3);
            } else {
                function3 = constructor2;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                measurePolicy$iv2 = measurePolicy$iv;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.Companion.getSetModifier());
                int i6 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i7 = ((390 >> 6) & 112) | 6;
                RowScope $this$invoke_u24lambda_u2423_u24lambda_u243 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 705859192, "C1549@69763L10,1547@69691L261,1554@69973L224:InnerShell.kt#m1syn");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Models", ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u243, Modifier.Companion, 1.0f, false, 2, null), AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131024);
                function4 = function1;
                str = str11;
                AuroraDsKt.AuroraPillAction("Done", function12, ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(84)), true, $composer, 3462, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.startReplaceGroup(-1028022376);
                ComposerKt.sourceInformation($composer, "1568@70651L13,1569@70728L13,1563@70383L1373");
                str2 = str10;
                if (str2 != null || StringsKt.isBlank(str2)) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    str3 = "C101@5126L9:Row.kt#2w3rfo";
                } else {
                    Modifier modifierClip2 = ClipKt.clip(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(16), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(12)));
                    long auroraWarning = AuroraColorSchemeKt.getAuroraWarning($composer, 0);
                    Modifier modifierM589backgroundbw27NRU$default2 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip2, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraWarning, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraWarning) : 0.08f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraWarning) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraWarning) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraWarning) : 0.0f), null, 2, null);
                    float fM7016constructorimpl2 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                    long auroraWarning2 = AuroraColorSchemeKt.getAuroraWarning($composer, 0);
                    Modifier modifier$iv4 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default2, fM7016constructorimpl2, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraWarning2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraWarning2) : 0.28f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraWarning2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraWarning2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraWarning2) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(12))), ai.nous.aurora.compat.Compat.m7016constructorimpl(48), 0.0f, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                    Alignment.Vertical verticalAlignment$iv2 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv5 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv4 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv5 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer, modifier$iv4);
                    constructor9 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function11 = constructor9;
                        $composer.createNode(function11);
                    } else {
                        function11 = constructor9;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv9 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv5, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv9.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv9))) {
                        $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                        $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash4);
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv6, ComposeUiNode.Companion.getSetModifier());
                    int i8 = ($changed$iv$iv$iv4 >> 6) & 14;
                    str3 = "C101@5126L9:Row.kt#2w3rfo";
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, str3);
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i9 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 707199384, "C1578@71228L13,1574@71036L233,1580@71294L39,1583@71485L10,1581@71358L376:InnerShell.kt#m1syn");
                    BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraWarning($composer, 0), null, 2, null), $composer, 0);
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer, 6);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Saved model unavailable — choose a replacement", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 3120, 120786);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                }
                $composer.endReplaceGroup();
                if (list.isEmpty()) {
                    $composer.startReplaceGroup(-1802393228);
                    ComposerKt.sourceInformation($composer, "1594@71950L15,1592@71836L212");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("No models are available from the connected host.", ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 54, 0, 131064);
                    $composer.endReplaceGroup();
                    $composer3 = $composer;
                } else {
                    $composer.startReplaceGroup(-1801841955);
                    ComposerKt.sourceInformation($composer, "1598@72094L3748,1674@76055L21,1670@75863L5970");
                    Modifier modifier$iv5 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(6));
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv2 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv6 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv5 = (6 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv6 = $composer.getCurrentCompositionLocalMap();
                    materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv5);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function5 = constructor3;
                        $composer.createNode(function5);
                    } else {
                        function5 = constructor3;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv6, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash5);
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                    int i10 = ($changed$iv$iv$iv5 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i11 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 708550364, "C1612@72797L19,1613@72864L12,1614@72968L27,1615@73039L56,1607@72532L2370,1653@75045L28,1654@75101L719,1651@74927L893:InnerShell.kt#m1syn");
                    List<MainViewModel.ModelChoice> list3 = list;
                    $this$firstOrNull$iv = list3;
                    it = $this$firstOrNull$iv.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = null;
                            break;
                        }
                        element$iv = it.next();
                        it4 = (MainViewModel.ModelChoice) element$iv;
                        $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                        materialized$iv$iv2 = materialized$iv$iv;
                        if (Intrinsics.areEqual(it4.getProvider(), InnerShellKt.ModelPickerSheet$lambda$84(mutableState))) {
                            break;
                        }
                        $this$firstOrNull$iv = $this$firstOrNull$iv2;
                        materialized$iv$iv = materialized$iv$iv2;
                    }
                    modelChoice = (MainViewModel.ModelChoice) element$iv;
                    if (modelChoice != null || (activeProviderLabel = modelChoice.getProviderName()) == null) {
                        activeProviderLabel = InnerShellKt.ModelPickerSheet$lambda$84(mutableState);
                    }
                    Modifier modifierM589backgroundbw27NRU$default3 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer, 0), null, 2, null);
                    float fM7016constructorimpl3 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                    long auroraAccent = AuroraColorSchemeKt.getAuroraAccent($composer, 0);
                    Modifier modifierM600borderxT4_qwU2 = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default3, fM7016constructorimpl3, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent) : 0.42f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16)));
                    $composer.startReplaceGroup(-254220628);
                    ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                    it$iv = $composer.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$7$lambda$6(mutableState2);
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    $composer.endReplaceGroup();
                    Modifier modifierM622clickableXHw0xAI$default = ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU2, false, null, null, (Function0) value$iv2, 7, null);
                    $composer.startReplaceGroup(-254218327);
                    ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                    invalid$iv = $composer.changed(activeProviderLabel);
                    Object it$iv5 = $composer.rememberedValue();
                    if (!invalid$iv || it$iv5 == Composer.Companion.getEmpty()) {
                        value$iv3 = new Function1() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$9$lambda$8(activeProviderLabel, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv5;
                    }
                    $composer.endReplaceGroup();
                    Modifier modifier$iv6 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM622clickableXHw0xAI$default, false, (Function1) value$iv3, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                    Alignment.Vertical verticalAlignment$iv3 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv3 = Arrangement.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv7 = RowKt.rowMeasurePolicy(horizontalArrangement$iv3, verticalAlignment$iv3, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv6 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv7 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer, modifier$iv6);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function6 = constructor4;
                        $composer.createNode(function6);
                    } else {
                        function6 = constructor4;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv7, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv7, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash6);
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv7, ComposeUiNode.Companion.getSetModifier());
                    int i12 = ($changed$iv$iv$iv6 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, str3);
                    int i13 = ((384 >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u2423_u24lambda_u2414_u24lambda_u2411 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -1359517038, "C1623@73515L12,1619@73307L252,1625@73588L29,1626@73646L931,1647@74768L12,1644@74606L270:InnerShell.kt#m1syn");
                    BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(9)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraAccent($composer, 0), null, 2, null), $composer, 0);
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), $composer, 6);
                    Modifier modifier$iv7 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2414_u24lambda_u2411, Modifier.Companion, 1.0f, false, 2, null);
                    str4 = "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo";
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, str4);
                    Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
                    str5 = str3;
                    MeasurePolicy measurePolicy$iv8 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv7 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv8 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer, modifier$iv7);
                    constructor5 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv7 = (($changed$iv$iv7 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function7 = constructor5;
                        $composer.createNode(function7);
                    } else {
                        function7 = constructor5;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv5 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv8, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv8, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                        $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                        $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash7);
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv8, ComposeUiNode.Companion.getSetModifier());
                    int i14 = ($changed$iv$iv$iv7 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    int i15 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1490674488, "C1629@73817L15,1627@73719L399,1637@74258L10,1635@74151L396:InnerShell.kt#m1syn");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("PROVIDER", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(10), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) FontFamily.Companion.getMonospace(), TextUnitKt.getSp(0.8d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(activeProviderLabel, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ExpandMoreKt.getExpandMore(Icons.INSTANCE.getDefault()), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraAccent($composer, 0), $composer, 432, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    boolean zModelPickerSheet$lambda$87 = InnerShellKt.ModelPickerSheet$lambda$87(mutableState2);
                    $composer.startReplaceGroup(-254154163);
                    ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                    it$iv2 = $composer.rememberedValue();
                    if (it$iv2 == Composer.Companion.getEmpty()) {
                        value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$13$lambda$12(mutableState2);
                            }
                        };
                        $composer.updateRememberedValue(value$iv4);
                    } else {
                        value$iv4 = it$iv2;
                    }
                    $composer.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zModelPickerSheet$lambda$87, (Function0) value$iv4, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1146712311, true, new InnerShellKt$ModelPickerSheet$1$2$2$4$5(list2, list, mutableState, mutableState2), $composer, 54), $composer, 48, 48, 2044);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer2 = $composer;
                    Modifier modifier$iv8 = ai.nous.aurora.compat.Compat.m1036paddingVpY3zN4$default(ai.nous.aurora.compat.Compat.d_ScrollKt_verticalScroll_7(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(400), 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), 0.0f, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, -483455358, str4);
                    Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv3 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv9 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv8 = (0 << 3) & 112;
                    str6 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str6);
                    compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv9 = ComposedModifierKt.materializeModifier($composer2, modifier$iv8);
                    constructor6 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 6) & 896) | 6;
                    str7 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, str7);
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        function8 = constructor6;
                        $composer2.createNode(function8);
                    } else {
                        function8 = constructor6;
                        $composer2.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv6 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv9, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv6.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv6))) {
                        $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                        $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash8);
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv9, ComposeUiNode.Companion.getSetModifier());
                    $changed$iv = ($changed$iv$iv$iv8 >> 6) & 14;
                    $composer$iv = $composer2;
                    i = 0;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    int i16 = ((0 >> 6) & 112) | 6;
                    $this$invoke_u24lambda_u2423_u24lambda_u2422 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, 712458999, "C1763@81771L40:InnerShell.kt#m1syn");
                    $this$filter$iv = list3;
                    destination$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$filter$iv) {
                        Iterable $this$filter$iv2 = $this$filter$iv;
                        it3 = (MainViewModel.ModelChoice) element$iv$iv;
                        int $changed$iv2 = $changed$iv;
                        int i17 = i;
                        if (Intrinsics.areEqual(it3.getProvider(), InnerShellKt.ModelPickerSheet$lambda$84(mutableState))) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                        $this$filter$iv = $this$filter$iv2;
                        $changed$iv = $changed$iv2;
                        i = i17;
                    }
                    Iterable $this$sortedBy$iv = (List) destination$iv$iv;
                    activeChoices = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$invoke$lambda$23$lambda$22$$inlined$sortedBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(Object t, Object t2) {
                            return ComparisonsKt.compareValues(Integer.valueOf(((MainViewModel.ModelChoice) t).getPersistable() ? 1 : 0), Integer.valueOf(((MainViewModel.ModelChoice) t2).getPersistable() ? 1 : 0));
                        }
                    });
                    $composer$iv.startReplaceGroup(-254092806);
                    ComposerKt.sourceInformation($composer$iv, "");
                    $this$forEachIndexed$iv = activeChoices;
                    $i$f$forEachIndexed = 0;
                    index$iv = 0;
                    it2 = $this$forEachIndexed$iv.iterator();
                    while (it2.hasNext()) {
                        Object item$iv = it2.next();
                        int index$iv2 = index$iv + 1;
                        if (index$iv < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                        choice = (MainViewModel.ModelChoice) item$iv;
                        int $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                        Iterator it5 = it2;
                        ColumnScope $this$invoke_u24lambda_u2423_u24lambda_u2423 = $this$invoke_u24lambda_u2423_u24lambda_u2422;
                        $composer$iv.startMovableGroup(1480282593, choice.getRole() + ":" + choice.getKey());
                        ComposerKt.sourceInformation($composer$iv, "1709@78328L20,1689@77007L4501,1759@81616L40");
                        String str13 = str;
                        selected = invoke$lambda$23$lambda$22$isSelected(str13, str12, choice);
                        CompositionLocalMap localMap$iv$iv9 = localMap$iv$iv;
                        int compositeKeyHash$iv$iv11 = compositeKeyHash$iv$iv6;
                        Modifier modifierClip3 = ClipKt.clip(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14)));
                        if (selected) {
                            $composer$iv.startReplaceGroup(1480293445);
                            ComposerKt.sourceInformation($composer$iv, "1695@77361L21");
                            long auroraSurfaceSelected = AuroraColorSchemeKt.getAuroraSurfaceSelected($composer$iv, 0);
                            $composer$iv.endReplaceGroup();
                            j = auroraSurfaceSelected;
                        } else if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(1480299235);
                            ComposerKt.sourceInformation($composer$iv, "1697@77542L19");
                            long auroraSurfaceRaised = AuroraColorSchemeKt.getAuroraSurfaceRaised($composer$iv, 0);
                            $composer$iv.endReplaceGroup();
                            j = auroraSurfaceRaised;
                        } else {
                            $composer$iv.startReplaceGroup(1480296803);
                            ComposerKt.sourceInformation($composer$iv, "1696@77454L11");
                            long auroraError2 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                            long jM4532copywmQWz5c2 = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError2) : 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError2) : 0.0f);
                            $composer$iv.endReplaceGroup();
                            j = jM4532copywmQWz5c2;
                        }
                        Modifier modifierM589backgroundbw27NRU$default4 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip3, j, null, 2, null);
                        float fM7016constructorimpl4 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                        if (selected) {
                            $composer$iv.startReplaceGroup(1480309858);
                            ComposerKt.sourceInformation($composer$iv, "1703@77861L12");
                            long auroraAccent2 = AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0);
                            jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent2) : 0.6f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent2) : 0.0f);
                            $composer$iv.endReplaceGroup();
                        } else if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(1480316035);
                            ComposerKt.sourceInformation($composer$iv, "1705@78052L14");
                            long auroraHairline = AuroraColorSchemeKt.getAuroraHairline($composer$iv, 0);
                            jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraHairline, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraHairline) : 0.55f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraHairline) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraHairline) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraHairline) : 0.0f);
                            $composer$iv.endReplaceGroup();
                        } else {
                            $composer$iv.startReplaceGroup(1480313123);
                            ComposerKt.sourceInformation($composer$iv, "1704@77964L11");
                            long auroraError3 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                            jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError3) : 0.55f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError3) : 0.0f);
                            $composer$iv.endReplaceGroup();
                        }
                        Composer $composer4 = $composer2;
                        Composer $composer$iv2 = $composer$iv;
                        Modifier modifierM600borderxT4_qwU3 = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default4, fM7016constructorimpl4, jM4532copywmQWz5c, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14)));
                        boolean persistable = choice.getPersistable();
                        $composer$iv.startReplaceGroup(1480324388);
                        ComposerKt.sourceInformation($composer$iv, str9);
                        final Function1<MainViewModel.ModelChoice, Unit> function14 = function4;
                        invalid$iv2 = $composer$iv.changed(function14) | $composer$iv.changed(choice);
                        it$iv3 = $composer$iv.rememberedValue();
                        if (invalid$iv2) {
                            str8 = str9;
                        } else {
                            str8 = str9;
                            if (it$iv3 == Composer.Companion.getEmpty()) {
                                value$iv5 = it$iv3;
                            }
                            $composer$iv.endReplaceGroup();
                            Modifier modifier$iv9 = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU3, persistable, null, null, (Function0) value$iv5, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
                            Alignment.Vertical verticalAlignment$iv4 = Alignment.Companion.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Arrangement.Horizontal horizontalArrangement$iv4 = Arrangement.INSTANCE.getStart();
                            function4 = function14;
                            MeasurePolicy measurePolicy$iv10 = RowKt.rowMeasurePolicy(horizontalArrangement$iv4, verticalAlignment$iv4, $composer$iv, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            int $changed$iv$iv9 = (384 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                            compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                            CompositionLocalMap localMap$iv$iv10 = $composer$iv.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv10 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv9);
                            constructor7 = ComposeUiNode.Companion.getConstructor();
                            int $changed$iv$iv$iv9 = (($changed$iv$iv9 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                            if (!($composer$iv.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer$iv.startReusableNode();
                            if ($composer$iv.getInserting()) {
                                function9 = constructor7;
                                $composer$iv.createNode(function9);
                            } else {
                                function9 = constructor7;
                                $composer$iv.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv7 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv10, ComposeUiNode.Companion.getSetMeasurePolicy());
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv10, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv7.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv7))) {
                                $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                                $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash9);
                            }
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv10, ComposeUiNode.Companion.getSetModifier());
                            int i18 = ($changed$iv$iv$iv9 >> 6) & 14;
                            String str14 = str5;
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -407840262, str14);
                            int i19 = ((384 >> 6) & 112) | 6;
                            RowScope $this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2420 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -51160205, "C1714@78656L1494:InnerShell.kt#m1syn");
                            Modifier modifier$iv10 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2420, Modifier.Companion, 1.0f, false, 2, null);
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -483455358, str4);
                            Arrangement.Vertical verticalArrangement$iv4 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv4 = Alignment.Companion.getStart();
                            String str15 = str4;
                            MeasurePolicy measurePolicy$iv11 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv4, horizontalAlignment$iv4, $composer$iv, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv10 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                            compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                            CompositionLocalMap localMap$iv$iv11 = $composer$iv.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv11 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv10);
                            constructor8 = ComposeUiNode.Companion.getConstructor();
                            int $changed$iv$iv$iv10 = (($changed$iv$iv10 << 6) & 896) | 6;
                            String str16 = str6;
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                            if (!($composer$iv.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer$iv.startReusableNode();
                            if ($composer$iv.getInserting()) {
                                function10 = constructor8;
                                $composer$iv.createNode(function10);
                            } else {
                                function10 = constructor8;
                                $composer$iv.useNode();
                            }
                            String str17 = str7;
                            $this$Layout_u24lambda_u240$iv$iv8 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv11, ComposeUiNode.Companion.getSetMeasurePolicy());
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv11, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv8.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv8))) {
                                $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                                $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash10);
                            }
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv11, ComposeUiNode.Companion.getSetModifier());
                            int i20 = ($changed$iv$iv$iv10 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                            int i21 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer$iv, 504703351, "C1715@78737L524,1723@79302L810:InnerShell.kt#m1syn");
                            String strFriendlyModel = ComposerChipPolicy.INSTANCE.friendlyModel(choice.getModel());
                            if (choice.getPersistable()) {
                                $composer$iv.startReplaceGroup(-1230641033);
                                ComposerKt.sourceInformation($composer$iv, "1717@78911L10");
                                auroraTextMuted = AuroraColorSchemeKt.getAuroraText($composer$iv, 0);
                            } else {
                                $composer$iv.startReplaceGroup(-1230640516);
                                ComposerKt.sourceInformation($composer$iv, "1717@78927L15");
                                auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                            }
                            $composer$iv.endReplaceGroup();
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyModel, (Modifier) null, auroraTextMuted, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199680, 3120, 120786);
                            role = choice.getRole();
                            switch (role.hashCode()) {
                                case -911832760:
                                    if (role.equals("Delegation")) {
                                        strFriendlyProvider = "Delegation · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                    } else {
                                        strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                    }
                                    break;
                                case 825887490:
                                    if (role.equals("Fallback")) {
                                        strFriendlyProvider = "Fallback · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                    } else {
                                        strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                    }
                                    break;
                                case 1002405936:
                                    if (role.equals("Unavailable")) {
                                        strFriendlyProvider = "Unavailable saved pair";
                                    } else {
                                        strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                    }
                                    break;
                                case 1349887458:
                                    if (role.equals("Primary")) {
                                        strFriendlyProvider = "Default · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                    } else {
                                        strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                    }
                                    break;
                                default:
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                    break;
                            }
                            if (choice.getPersistable()) {
                                $composer$iv.startReplaceGroup(-1230611332);
                                ComposerKt.sourceInformation($composer$iv, "1731@79839L15");
                                auroraError = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                            } else {
                                $composer$iv.startReplaceGroup(-1230610664);
                                ComposerKt.sourceInformation($composer$iv, "1731@79860L11");
                                auroraError = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                            }
                            $composer$iv.endReplaceGroup();
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyProvider, (Modifier) null, auroraError, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3072, 3120, 120818);
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            $composer$iv.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            if (selected) {
                                $composer$iv.startReplaceGroup(-49702183);
                                ComposerKt.sourceInformation($composer$iv, "1741@80442L12,1738@80243L331");
                                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Selected", ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(22)), AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0), $composer$iv, 432, 0);
                                $composer$iv.endReplaceGroup();
                            } else if (choice.getPersistable()) {
                                $composer$iv.startReplaceGroup(-48452108);
                                $composer$iv.endReplaceGroup();
                            } else {
                                $composer$iv.startReplaceGroup(-49250699);
                                ComposerKt.sourceInformation($composer$iv, "1747@80809L11,1753@81243L11,1745@80685L751");
                                long auroraError4 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                                GenericFontFamily monospace = FontFamily.Companion.getMonospace();
                                long sp = TextUnitKt.getSp(10);
                                FontWeight bold = FontWeight.Companion.getBold();
                                Modifier modifierClip4 = ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8)));
                                float fM7016constructorimpl5 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                                long auroraError5 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("UNAVAILABLE", ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierClip4, fM7016constructorimpl5, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError5, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError5) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError5) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError5) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError5) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8))), ai.nous.aurora.compat.Compat.m7016constructorimpl(8), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), auroraError4, sp, (FontStyle) null, bold, (FontFamily) monospace, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199686, 0, 130960);
                                $composer$iv.endReplaceGroup();
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            $composer$iv.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            if (index$iv < CollectionsKt.getLastIndex(activeChoices)) {
                                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                            }
                            $composer$iv.endMovableGroup();
                            str5 = str14;
                            localMap$iv$iv = localMap$iv$iv9;
                            compositeKeyHash$iv$iv6 = compositeKeyHash$iv$iv11;
                            index$iv = index$iv2;
                            $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                            $i$f$forEachIndexed = $i$f$forEachIndexed2;
                            it2 = it5;
                            $this$invoke_u24lambda_u2423_u24lambda_u2422 = $this$invoke_u24lambda_u2423_u24lambda_u2423;
                            str = str13;
                            $composer2 = $composer4;
                            $composer$iv = $composer$iv2;
                            str9 = str8;
                            str4 = str15;
                            str6 = str16;
                            str7 = str17;
                        }
                        value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$22$lambda$21$lambda$18$lambda$17(function14, choice);
                            }
                        };
                        $composer$iv.updateRememberedValue(value$iv5);
                        $composer$iv.endReplaceGroup();
                        Modifier modifier$iv11 = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU3, persistable, null, null, (Function0) value$iv5, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
                        Alignment.Vertical verticalAlignment$iv5 = Alignment.Companion.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv5 = Arrangement.INSTANCE.getStart();
                        function4 = function14;
                        MeasurePolicy measurePolicy$iv12 = RowKt.rowMeasurePolicy(horizontalArrangement$iv5, verticalAlignment$iv5, $composer$iv, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv11 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                        compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                        CompositionLocalMap localMap$iv$iv12 = $composer$iv.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv12 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv11);
                        constructor7 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv11 = (($changed$iv$iv11 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                        if (!($composer$iv.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer$iv.startReusableNode();
                        if ($composer$iv.getInserting()) {
                            function9 = constructor7;
                            $composer$iv.createNode(function9);
                        } else {
                            function9 = constructor7;
                            $composer$iv.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv7 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv12, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv12, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash11);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv12, ComposeUiNode.Companion.getSetModifier());
                        int i110 = ($changed$iv$iv$iv11 >> 6) & 14;
                        String str18 = str5;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -407840262, str18);
                        int i111 = ((384 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2421 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -51160205, "C1714@78656L1494:InnerShell.kt#m1syn");
                        Modifier modifier$iv12 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2421, Modifier.Companion, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -483455358, str4);
                        Arrangement.Vertical verticalArrangement$iv5 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv5 = Alignment.Companion.getStart();
                        String str19 = str4;
                        MeasurePolicy measurePolicy$iv13 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv5, horizontalAlignment$iv5, $composer$iv, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv12 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                        compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                        CompositionLocalMap localMap$iv$iv13 = $composer$iv.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv13 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv12);
                        constructor8 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv12 = (($changed$iv$iv12 << 6) & 896) | 6;
                        String str110 = str6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                        if (!($composer$iv.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer$iv.startReusableNode();
                        if ($composer$iv.getInserting()) {
                            function10 = constructor8;
                            $composer$iv.createNode(function10);
                        } else {
                            function10 = constructor8;
                            $composer$iv.useNode();
                        }
                        String str111 = str7;
                        $this$Layout_u24lambda_u240$iv$iv8 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv13, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv13, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash12 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                        $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash12);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv13, ComposeUiNode.Companion.getSetModifier());
                        int i22 = ($changed$iv$iv$iv12 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                        int i23 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, 504703351, "C1715@78737L524,1723@79302L810:InnerShell.kt#m1syn");
                        String strFriendlyModel2 = ComposerChipPolicy.INSTANCE.friendlyModel(choice.getModel());
                        if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(-1230641033);
                            ComposerKt.sourceInformation($composer$iv, "1717@78911L10");
                            auroraTextMuted = AuroraColorSchemeKt.getAuroraText($composer$iv, 0);
                        } else {
                            $composer$iv.startReplaceGroup(-1230640516);
                            ComposerKt.sourceInformation($composer$iv, "1717@78927L15");
                            auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                        }
                        $composer$iv.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyModel2, (Modifier) null, auroraTextMuted, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199680, 3120, 120786);
                        role = choice.getRole();
                        switch (role.hashCode()) {
                            case -911832760:
                                if (role.equals("Delegation")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Delegation · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                }
                                break;
                            case 825887490:
                                if (role.equals("Fallback")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Fallback · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                }
                                break;
                            case 1002405936:
                                if (role.equals("Unavailable")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Unavailable saved pair";
                                }
                                break;
                            case 1349887458:
                                if (role.equals("Primary")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Default · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                }
                                break;
                            default:
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                break;
                        }
                        if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(-1230611332);
                            ComposerKt.sourceInformation($composer$iv, "1731@79839L15");
                            auroraError = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                        } else {
                            $composer$iv.startReplaceGroup(-1230610664);
                            ComposerKt.sourceInformation($composer$iv, "1731@79860L11");
                            auroraError = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        }
                        $composer$iv.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyProvider, (Modifier) null, auroraError, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3072, 3120, 120818);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        $composer$iv.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        if (selected) {
                            $composer$iv.startReplaceGroup(-49702183);
                            ComposerKt.sourceInformation($composer$iv, "1741@80442L12,1738@80243L331");
                            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Selected", ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(22)), AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0), $composer$iv, 432, 0);
                            $composer$iv.endReplaceGroup();
                        } else if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(-49250699);
                            ComposerKt.sourceInformation($composer$iv, "1747@80809L11,1753@81243L11,1745@80685L751");
                            long auroraError6 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                            GenericFontFamily monospace2 = FontFamily.Companion.getMonospace();
                            long sp2 = TextUnitKt.getSp(10);
                            FontWeight bold2 = FontWeight.Companion.getBold();
                            Modifier modifierClip5 = ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8)));
                            float fM7016constructorimpl6 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                            long auroraError7 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("UNAVAILABLE", ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierClip5, fM7016constructorimpl6, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError7, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError7) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError7) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError7) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError7) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8))), ai.nous.aurora.compat.Compat.m7016constructorimpl(8), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), auroraError6, sp2, (FontStyle) null, bold2, (FontFamily) monospace2, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199686, 0, 130960);
                            $composer$iv.endReplaceGroup();
                        } else {
                            $composer$iv.startReplaceGroup(-48452108);
                            $composer$iv.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        $composer$iv.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        if (index$iv < CollectionsKt.getLastIndex(activeChoices)) {
                            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                        }
                        $composer$iv.endMovableGroup();
                        str5 = str18;
                        localMap$iv$iv = localMap$iv$iv9;
                        compositeKeyHash$iv$iv6 = compositeKeyHash$iv$iv11;
                        index$iv = index$iv2;
                        $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                        $i$f$forEachIndexed = $i$f$forEachIndexed2;
                        it2 = it5;
                        $this$invoke_u24lambda_u2423_u24lambda_u2422 = $this$invoke_u24lambda_u2423_u24lambda_u2423;
                        str = str13;
                        $composer2 = $composer4;
                        $composer$iv = $composer$iv2;
                        str9 = str8;
                        str4 = str19;
                        str6 = str110;
                        str7 = str111;
                    }
                    $composer3 = $composer2;
                    $composer$iv.endReplaceGroup();
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            measurePolicy$iv2 = measurePolicy$iv;
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.Companion.getSetModifier());
            int i24 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i25 = ((390 >> 6) & 112) | 6;
            RowScope $this$invoke_u24lambda_u2423_u24lambda_u244 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 705859192, "C1549@69763L10,1547@69691L261,1554@69973L224:InnerShell.kt#m1syn");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Models", ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u244, Modifier.Companion, 1.0f, false, 2, null), AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131024);
            function4 = function1;
            str = str11;
            AuroraDsKt.AuroraPillAction("Done", function12, ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(84)), true, $composer, 3462, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.startReplaceGroup(-1028022376);
            ComposerKt.sourceInformation($composer, "1568@70651L13,1569@70728L13,1563@70383L1373");
            str2 = str10;
            if (str2 != null) {
                z = true;
            } else {
                z = true;
            }
            if (z) {
                Modifier modifierClip6 = ClipKt.clip(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(16), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(12)));
                long auroraWarning3 = AuroraColorSchemeKt.getAuroraWarning($composer, 0);
                Modifier modifierM589backgroundbw27NRU$default5 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip6, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraWarning3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraWarning3) : 0.08f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraWarning3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraWarning3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraWarning3) : 0.0f), null, 2, null);
                float fM7016constructorimpl7 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                long auroraWarning4 = AuroraColorSchemeKt.getAuroraWarning($composer, 0);
                Modifier modifier$iv13 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default5, fM7016constructorimpl7, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraWarning4, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraWarning4) : 0.28f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraWarning4) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraWarning4) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraWarning4) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(12))), ai.nous.aurora.compat.Compat.m7016constructorimpl(48), 0.0f, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                Alignment.Vertical verticalAlignment$iv6 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv6 = Arrangement.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv14 = RowKt.rowMeasurePolicy(horizontalArrangement$iv6, verticalAlignment$iv6, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv13 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv14 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv14 = ComposedModifierKt.materializeModifier($composer, modifier$iv13);
                constructor9 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv13 = (($changed$iv$iv13 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function11 = constructor9;
                    $composer.createNode(function11);
                } else {
                    function11 = constructor9;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv9 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv14, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv14, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash13 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash13);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv14, ComposeUiNode.Companion.getSetModifier());
                int i26 = ($changed$iv$iv$iv13 >> 6) & 14;
                str3 = "C101@5126L9:Row.kt#2w3rfo";
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, str3);
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i27 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 707199384, "C1578@71228L13,1574@71036L233,1580@71294L39,1583@71485L10,1581@71358L376:InnerShell.kt#m1syn");
                BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraWarning($composer, 0), null, 2, null), $composer, 0);
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer, 6);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Saved model unavailable — choose a replacement", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 3120, 120786);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
            } else {
                str3 = "C101@5126L9:Row.kt#2w3rfo";
            }
            $composer.endReplaceGroup();
            if (list.isEmpty()) {
                $composer.startReplaceGroup(-1802393228);
                ComposerKt.sourceInformation($composer, "1594@71950L15,1592@71836L212");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("No models are available from the connected host.", ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 54, 0, 131064);
                $composer.endReplaceGroup();
                $composer3 = $composer;
            } else {
                $composer.startReplaceGroup(-1801841955);
                ComposerKt.sourceInformation($composer, "1598@72094L3748,1674@76055L21,1670@75863L5970");
                Modifier modifier$iv14 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(6));
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv15 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv14 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv15 = $composer.getCurrentCompositionLocalMap();
                materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv14);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv14 = (($changed$iv$iv14 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor3;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor3;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv15, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv15, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash14 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash14);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i112 = ($changed$iv$iv$iv14 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i113 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 708550364, "C1612@72797L19,1613@72864L12,1614@72968L27,1615@73039L56,1607@72532L2370,1653@75045L28,1654@75101L719,1651@74927L893:InnerShell.kt#m1syn");
                List<MainViewModel.ModelChoice> list4 = list;
                $this$firstOrNull$iv = list4;
                it = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    it4 = (MainViewModel.ModelChoice) element$iv;
                    $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                    materialized$iv$iv2 = materialized$iv$iv;
                    if (Intrinsics.areEqual(it4.getProvider(), InnerShellKt.ModelPickerSheet$lambda$84(mutableState))) {
                        break;
                        break;
                    } else {
                        $this$firstOrNull$iv = $this$firstOrNull$iv2;
                        materialized$iv$iv = materialized$iv$iv2;
                    }
                }
                modelChoice = (MainViewModel.ModelChoice) element$iv;
                if (modelChoice != null) {
                    activeProviderLabel = InnerShellKt.ModelPickerSheet$lambda$84(mutableState);
                } else {
                    activeProviderLabel = InnerShellKt.ModelPickerSheet$lambda$84(mutableState);
                }
                Modifier modifierM589backgroundbw27NRU$default6 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer, 0), null, 2, null);
                float fM7016constructorimpl8 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                long auroraAccent3 = AuroraColorSchemeKt.getAuroraAccent($composer, 0);
                Modifier modifierM600borderxT4_qwU4 = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default6, fM7016constructorimpl8, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent3) : 0.42f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent3) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16)));
                $composer.startReplaceGroup(-254220628);
                ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                it$iv = $composer.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$7$lambda$6(mutableState2);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                $composer.endReplaceGroup();
                Modifier modifierM622clickableXHw0xAI$default2 = ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU4, false, null, null, (Function0) value$iv2, 7, null);
                $composer.startReplaceGroup(-254218327);
                ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                invalid$iv = $composer.changed(activeProviderLabel);
                Object it$iv6 = $composer.rememberedValue();
                if (invalid$iv) {
                }
                value$iv3 = new Function1() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$9$lambda$8(activeProviderLabel, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv3);
                $composer.endReplaceGroup();
                Modifier modifier$iv15 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM622clickableXHw0xAI$default2, false, (Function1) value$iv3, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                Alignment.Vertical verticalAlignment$iv7 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv7 = Arrangement.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv16 = RowKt.rowMeasurePolicy(horizontalArrangement$iv7, verticalAlignment$iv7, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv15 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv16 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv15 = ComposedModifierKt.materializeModifier($composer, modifier$iv15);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv15 = (($changed$iv$iv15 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function6 = constructor4;
                    $composer.createNode(function6);
                } else {
                    function6 = constructor4;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv16, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv16, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash15 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash15);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv15, ComposeUiNode.Companion.getSetModifier());
                int i114 = ($changed$iv$iv$iv15 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, str3);
                int i115 = ((384 >> 6) & 112) | 6;
                RowScope $this$invoke_u24lambda_u2423_u24lambda_u2414_u24lambda_u2412 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -1359517038, "C1623@73515L12,1619@73307L252,1625@73588L29,1626@73646L931,1647@74768L12,1644@74606L270:InnerShell.kt#m1syn");
                BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(9)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraAccent($composer, 0), null, 2, null), $composer, 0);
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), $composer, 6);
                Modifier modifier$iv16 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2414_u24lambda_u2412, Modifier.Companion, 1.0f, false, 2, null);
                str4 = "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo";
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, str4);
                Arrangement.Vertical verticalArrangement$iv6 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv6 = Alignment.Companion.getStart();
                str5 = str3;
                MeasurePolicy measurePolicy$iv17 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv6, horizontalAlignment$iv6, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv16 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv17 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv16 = ComposedModifierKt.materializeModifier($composer, modifier$iv16);
                constructor5 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv16 = (($changed$iv$iv16 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function7 = constructor5;
                    $composer.createNode(function7);
                } else {
                    function7 = constructor5;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv5 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv17, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv17, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash16 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash16);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv16, ComposeUiNode.Companion.getSetModifier());
                int i116 = ($changed$iv$iv$iv16 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                int i117 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1490674488, "C1629@73817L15,1627@73719L399,1637@74258L10,1635@74151L396:InnerShell.kt#m1syn");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("PROVIDER", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(10), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) FontFamily.Companion.getMonospace(), TextUnitKt.getSp(0.8d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(activeProviderLabel, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ExpandMoreKt.getExpandMore(Icons.INSTANCE.getDefault()), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraAccent($composer, 0), $composer, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                boolean zModelPickerSheet$lambda$88 = InnerShellKt.ModelPickerSheet$lambda$87(mutableState2);
                $composer.startReplaceGroup(-254154163);
                ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                it$iv2 = $composer.rememberedValue();
                if (it$iv2 == Composer.Companion.getEmpty()) {
                    value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$13$lambda$12(mutableState2);
                        }
                    };
                    $composer.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv2;
                }
                $composer.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zModelPickerSheet$lambda$88, (Function0) value$iv4, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1146712311, true, new InnerShellKt$ModelPickerSheet$1$2$2$4$5(list2, list, mutableState, mutableState2), $composer, 54), $composer, 48, 48, 2044);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer2 = $composer;
                Modifier modifier$iv17 = ai.nous.aurora.compat.Compat.m1036paddingVpY3zN4$default(ai.nous.aurora.compat.Compat.d_ScrollKt_verticalScroll_7(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(400), 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, str4);
                Arrangement.Vertical verticalArrangement$iv7 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv7 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv18 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv7, horizontalAlignment$iv7, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv17 = (0 << 3) & 112;
                str6 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str6);
                compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv17 = ComposedModifierKt.materializeModifier($composer2, modifier$iv17);
                constructor6 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv17 = (($changed$iv$iv17 << 6) & 896) | 6;
                str7 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, str7);
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function8 = constructor6;
                    $composer2.createNode(function8);
                } else {
                    function8 = constructor6;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv6 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv18, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash17 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash17);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv17, ComposeUiNode.Companion.getSetModifier());
                $changed$iv = ($changed$iv$iv$iv17 >> 6) & 14;
                $composer$iv = $composer2;
                i = 0;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                int i118 = ((0 >> 6) & 112) | 6;
                $this$invoke_u24lambda_u2423_u24lambda_u2422 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer$iv, 712458999, "C1763@81771L40:InnerShell.kt#m1syn");
                $this$filter$iv = list4;
                destination$iv$iv = new ArrayList();
                java.util.Iterator<Object> r56 = list4.iterator();
                while (r56.hasNext()) {
                    Iterable $this$filter$iv3 = $this$filter$iv;
                    Object element$iv$iv = r56.next();
                    it3 = (MainViewModel.ModelChoice) element$iv$iv;
                    int $changed$iv3 = $changed$iv;
                    int i119 = i;
                    if (Intrinsics.areEqual(it3.getProvider(), InnerShellKt.ModelPickerSheet$lambda$84(mutableState))) {
                        Object element$iv$iv = r56.next();
                        destination$iv$iv.add(element$iv$iv);
                    }
                    $this$filter$iv = $this$filter$iv3;
                    $changed$iv = $changed$iv3;
                    i = i119;
                }
                Iterable $this$sortedBy$iv2 = (List) destination$iv$iv;
                activeChoices = CollectionsKt.sortedWith($this$sortedBy$iv2, new Comparator() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$invoke$lambda$23$lambda$22$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(Object t, Object t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(((MainViewModel.ModelChoice) t).getPersistable() ? 1 : 0), Integer.valueOf(((MainViewModel.ModelChoice) t2).getPersistable() ? 1 : 0));
                    }
                });
                $composer$iv.startReplaceGroup(-254092806);
                ComposerKt.sourceInformation($composer$iv, "");
                $this$forEachIndexed$iv = activeChoices;
                $i$f$forEachIndexed = 0;
                index$iv = 0;
                it2 = $this$forEachIndexed$iv.iterator();
                while (it2.hasNext()) {
                    Object item$iv2 = it2.next();
                    int index$iv3 = index$iv + 1;
                    if (index$iv < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Iterable $this$forEachIndexed$iv3 = $this$forEachIndexed$iv;
                    choice = (MainViewModel.ModelChoice) item$iv2;
                    int $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                    Iterator it6 = it2;
                    ColumnScope $this$invoke_u24lambda_u2423_u24lambda_u2424 = $this$invoke_u24lambda_u2423_u24lambda_u2422;
                    $composer$iv.startMovableGroup(1480282593, choice.getRole() + ":" + choice.getKey());
                    ComposerKt.sourceInformation($composer$iv, "1709@78328L20,1689@77007L4501,1759@81616L40");
                    String str112 = str;
                    selected = invoke$lambda$23$lambda$22$isSelected(str112, str12, choice);
                    CompositionLocalMap localMap$iv$iv18 = localMap$iv$iv;
                    int compositeKeyHash$iv$iv12 = compositeKeyHash$iv$iv6;
                    Modifier modifierClip7 = ClipKt.clip(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14)));
                    if (selected) {
                        $composer$iv.startReplaceGroup(1480293445);
                        ComposerKt.sourceInformation($composer$iv, "1695@77361L21");
                        long auroraSurfaceSelected2 = AuroraColorSchemeKt.getAuroraSurfaceSelected($composer$iv, 0);
                        $composer$iv.endReplaceGroup();
                        j = auroraSurfaceSelected2;
                    } else if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(1480296803);
                        ComposerKt.sourceInformation($composer$iv, "1696@77454L11");
                        long auroraError8 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        long jM4532copywmQWz5c3 = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError8, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError8) : 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError8) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError8) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError8) : 0.0f);
                        $composer$iv.endReplaceGroup();
                        j = jM4532copywmQWz5c3;
                    } else {
                        $composer$iv.startReplaceGroup(1480299235);
                        ComposerKt.sourceInformation($composer$iv, "1697@77542L19");
                        long auroraSurfaceRaised2 = AuroraColorSchemeKt.getAuroraSurfaceRaised($composer$iv, 0);
                        $composer$iv.endReplaceGroup();
                        j = auroraSurfaceRaised2;
                    }
                    Modifier modifierM589backgroundbw27NRU$default7 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip7, j, null, 2, null);
                    float fM7016constructorimpl9 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                    if (selected) {
                        $composer$iv.startReplaceGroup(1480309858);
                        ComposerKt.sourceInformation($composer$iv, "1703@77861L12");
                        long auroraAccent4 = AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0);
                        jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent4, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent4) : 0.6f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent4) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent4) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent4) : 0.0f);
                        $composer$iv.endReplaceGroup();
                    } else if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(1480313123);
                        ComposerKt.sourceInformation($composer$iv, "1704@77964L11");
                        long auroraError9 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError9, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError9) : 0.55f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError9) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError9) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError9) : 0.0f);
                        $composer$iv.endReplaceGroup();
                    } else {
                        $composer$iv.startReplaceGroup(1480316035);
                        ComposerKt.sourceInformation($composer$iv, "1705@78052L14");
                        long auroraHairline2 = AuroraColorSchemeKt.getAuroraHairline($composer$iv, 0);
                        jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraHairline2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraHairline2) : 0.55f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraHairline2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraHairline2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraHairline2) : 0.0f);
                        $composer$iv.endReplaceGroup();
                    }
                    Composer $composer5 = $composer2;
                    Composer $composer$iv3 = $composer$iv;
                    Modifier modifierM600borderxT4_qwU5 = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default7, fM7016constructorimpl9, jM4532copywmQWz5c, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14)));
                    boolean persistable2 = choice.getPersistable();
                    $composer$iv.startReplaceGroup(1480324388);
                    ComposerKt.sourceInformation($composer$iv, str9);
                    final Function1 function15 = function4;
                    invalid$iv2 = $composer$iv.changed(function15) | $composer$iv.changed(choice);
                    it$iv3 = $composer$iv.rememberedValue();
                    if (invalid$iv2) {
                        str8 = str9;
                        if (it$iv3 == Composer.Companion.getEmpty()) {
                            value$iv5 = it$iv3;
                        }
                        $composer$iv.endReplaceGroup();
                        Modifier modifier$iv18 = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU5, persistable2, null, null, (Function0) value$iv5, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
                        Alignment.Vertical verticalAlignment$iv8 = Alignment.Companion.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv8 = Arrangement.INSTANCE.getStart();
                        function4 = function15;
                        MeasurePolicy measurePolicy$iv19 = RowKt.rowMeasurePolicy(horizontalArrangement$iv8, verticalAlignment$iv8, $composer$iv, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv18 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                        compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                        CompositionLocalMap localMap$iv$iv19 = $composer$iv.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv18 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv18);
                        constructor7 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv18 = (($changed$iv$iv18 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                        if (!($composer$iv.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer$iv.startReusableNode();
                        if ($composer$iv.getInserting()) {
                            function9 = constructor7;
                            $composer$iv.createNode(function9);
                        } else {
                            function9 = constructor7;
                            $composer$iv.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv7 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv19, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv19, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash18 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash18);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv18, ComposeUiNode.Companion.getSetModifier());
                        int i1110 = ($changed$iv$iv$iv18 >> 6) & 14;
                        String str113 = str5;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -407840262, str113);
                        int i1111 = ((384 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2422 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -51160205, "C1714@78656L1494:InnerShell.kt#m1syn");
                        Modifier modifier$iv19 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2422, Modifier.Companion, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -483455358, str4);
                        Arrangement.Vertical verticalArrangement$iv8 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv8 = Alignment.Companion.getStart();
                        String str114 = str4;
                        MeasurePolicy measurePolicy$iv110 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv8, horizontalAlignment$iv8, $composer$iv, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv19 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                        compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                        CompositionLocalMap localMap$iv$iv110 = $composer$iv.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv19 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv19);
                        constructor8 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv19 = (($changed$iv$iv19 << 6) & 896) | 6;
                        String str115 = str6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                        if (!($composer$iv.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer$iv.startReusableNode();
                        if ($composer$iv.getInserting()) {
                            function10 = constructor8;
                            $composer$iv.createNode(function10);
                        } else {
                            function10 = constructor8;
                            $composer$iv.useNode();
                        }
                        String str116 = str7;
                        $this$Layout_u24lambda_u240$iv$iv8 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv110, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv110, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash19 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                        $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash19);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv19, ComposeUiNode.Companion.getSetModifier());
                        int i28 = ($changed$iv$iv$iv19 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
                        int i29 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, 504703351, "C1715@78737L524,1723@79302L810:InnerShell.kt#m1syn");
                        String strFriendlyModel3 = ComposerChipPolicy.INSTANCE.friendlyModel(choice.getModel());
                        if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(-1230641033);
                            ComposerKt.sourceInformation($composer$iv, "1717@78911L10");
                            auroraTextMuted = AuroraColorSchemeKt.getAuroraText($composer$iv, 0);
                        } else {
                            $composer$iv.startReplaceGroup(-1230640516);
                            ComposerKt.sourceInformation($composer$iv, "1717@78927L15");
                            auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                        }
                        $composer$iv.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyModel3, (Modifier) null, auroraTextMuted, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199680, 3120, 120786);
                        role = choice.getRole();
                        switch (role.hashCode()) {
                            case -911832760:
                                if (role.equals("Delegation")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Delegation · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                }
                                break;
                            case 825887490:
                                if (role.equals("Fallback")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Fallback · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                }
                                break;
                            case 1002405936:
                                if (role.equals("Unavailable")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Unavailable saved pair";
                                }
                                break;
                            case 1349887458:
                                if (role.equals("Primary")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Default · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                }
                                break;
                            default:
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                break;
                        }
                        if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(-1230611332);
                            ComposerKt.sourceInformation($composer$iv, "1731@79839L15");
                            auroraError = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                        } else {
                            $composer$iv.startReplaceGroup(-1230610664);
                            ComposerKt.sourceInformation($composer$iv, "1731@79860L11");
                            auroraError = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        }
                        $composer$iv.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyProvider, (Modifier) null, auroraError, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3072, 3120, 120818);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        $composer$iv.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        if (selected) {
                            $composer$iv.startReplaceGroup(-49702183);
                            ComposerKt.sourceInformation($composer$iv, "1741@80442L12,1738@80243L331");
                            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Selected", ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(22)), AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0), $composer$iv, 432, 0);
                            $composer$iv.endReplaceGroup();
                        } else if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(-49250699);
                            ComposerKt.sourceInformation($composer$iv, "1747@80809L11,1753@81243L11,1745@80685L751");
                            long auroraError10 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                            GenericFontFamily monospace3 = FontFamily.Companion.getMonospace();
                            long sp3 = TextUnitKt.getSp(10);
                            FontWeight bold3 = FontWeight.Companion.getBold();
                            Modifier modifierClip8 = ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8)));
                            float fM7016constructorimpl10 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                            long auroraError11 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("UNAVAILABLE", ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierClip8, fM7016constructorimpl10, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError11, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError11) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError11) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError11) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError11) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8))), ai.nous.aurora.compat.Compat.m7016constructorimpl(8), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), auroraError10, sp3, (FontStyle) null, bold3, (FontFamily) monospace3, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199686, 0, 130960);
                            $composer$iv.endReplaceGroup();
                        } else {
                            $composer$iv.startReplaceGroup(-48452108);
                            $composer$iv.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        $composer$iv.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        if (index$iv < CollectionsKt.getLastIndex(activeChoices)) {
                            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                        }
                        $composer$iv.endMovableGroup();
                        str5 = str113;
                        localMap$iv$iv = localMap$iv$iv18;
                        compositeKeyHash$iv$iv6 = compositeKeyHash$iv$iv12;
                        index$iv = index$iv3;
                        $this$forEachIndexed$iv = $this$forEachIndexed$iv3;
                        $i$f$forEachIndexed = $i$f$forEachIndexed3;
                        it2 = it6;
                        $this$invoke_u24lambda_u2423_u24lambda_u2422 = $this$invoke_u24lambda_u2423_u24lambda_u2424;
                        str = str112;
                        $composer2 = $composer5;
                        $composer$iv = $composer$iv3;
                        str9 = str8;
                        str4 = str114;
                        str6 = str115;
                        str7 = str116;
                    } else {
                        str8 = str9;
                    }
                    value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$22$lambda$21$lambda$18$lambda$17(function15, choice);
                        }
                    };
                    $composer$iv.updateRememberedValue(value$iv5);
                    $composer$iv.endReplaceGroup();
                    Modifier modifier$iv110 = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU5, persistable2, null, null, (Function0) value$iv5, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
                    Alignment.Vertical verticalAlignment$iv9 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv9 = Arrangement.INSTANCE.getStart();
                    function4 = function15;
                    MeasurePolicy measurePolicy$iv111 = RowKt.rowMeasurePolicy(horizontalArrangement$iv9, verticalAlignment$iv9, $composer$iv, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv110 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                    compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                    CompositionLocalMap localMap$iv$iv111 = $composer$iv.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv110 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv110);
                    constructor7 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv110 = (($changed$iv$iv110 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                    if (!($composer$iv.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer$iv.startReusableNode();
                    if ($composer$iv.getInserting()) {
                        function9 = constructor7;
                        $composer$iv.createNode(function9);
                    } else {
                        function9 = constructor7;
                        $composer$iv.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv7 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv111, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv111, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash110 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                    $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash110);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv110, ComposeUiNode.Companion.getSetModifier());
                    int i1112 = ($changed$iv$iv$iv110 >> 6) & 14;
                    String str117 = str5;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -407840262, str117);
                    int i1113 = ((384 >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2423 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -51160205, "C1714@78656L1494:InnerShell.kt#m1syn");
                    Modifier modifier$iv111 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2423, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -483455358, str4);
                    Arrangement.Vertical verticalArrangement$iv9 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv9 = Alignment.Companion.getStart();
                    String str118 = str4;
                    MeasurePolicy measurePolicy$iv112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv9, horizontalAlignment$iv9, $composer$iv, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv111 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                    compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                    CompositionLocalMap localMap$iv$iv112 = $composer$iv.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv111);
                    constructor8 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv111 = (($changed$iv$iv111 << 6) & 896) | 6;
                    String str119 = str6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                    if (!($composer$iv.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer$iv.startReusableNode();
                    if ($composer$iv.getInserting()) {
                        function10 = constructor8;
                        $composer$iv.createNode(function10);
                    } else {
                        function10 = constructor8;
                        $composer$iv.useNode();
                    }
                    String str1110 = str7;
                    $this$Layout_u24lambda_u240$iv$iv8 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv112, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv112, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                    $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash111);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv111, ComposeUiNode.Companion.getSetModifier());
                    int i210 = ($changed$iv$iv$iv111 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance7 = ColumnScopeInstance.INSTANCE;
                    int i211 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, 504703351, "C1715@78737L524,1723@79302L810:InnerShell.kt#m1syn");
                    String strFriendlyModel4 = ComposerChipPolicy.INSTANCE.friendlyModel(choice.getModel());
                    if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(-1230641033);
                        ComposerKt.sourceInformation($composer$iv, "1717@78911L10");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraText($composer$iv, 0);
                    } else {
                        $composer$iv.startReplaceGroup(-1230640516);
                        ComposerKt.sourceInformation($composer$iv, "1717@78927L15");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                    }
                    $composer$iv.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyModel4, (Modifier) null, auroraTextMuted, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199680, 3120, 120786);
                    role = choice.getRole();
                    switch (role.hashCode()) {
                        case -911832760:
                            if (role.equals("Delegation")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Delegation · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            }
                            break;
                        case 825887490:
                            if (role.equals("Fallback")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Fallback · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            }
                            break;
                        case 1002405936:
                            if (role.equals("Unavailable")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Unavailable saved pair";
                            }
                            break;
                        case 1349887458:
                            if (role.equals("Primary")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Default · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            }
                            break;
                        default:
                            strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            break;
                    }
                    if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(-1230611332);
                        ComposerKt.sourceInformation($composer$iv, "1731@79839L15");
                        auroraError = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                    } else {
                        $composer$iv.startReplaceGroup(-1230610664);
                        ComposerKt.sourceInformation($composer$iv, "1731@79860L11");
                        auroraError = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                    }
                    $composer$iv.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyProvider, (Modifier) null, auroraError, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3072, 3120, 120818);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    if (selected) {
                        $composer$iv.startReplaceGroup(-49702183);
                        ComposerKt.sourceInformation($composer$iv, "1741@80442L12,1738@80243L331");
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Selected", ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(22)), AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0), $composer$iv, 432, 0);
                        $composer$iv.endReplaceGroup();
                    } else if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(-49250699);
                        ComposerKt.sourceInformation($composer$iv, "1747@80809L11,1753@81243L11,1745@80685L751");
                        long auroraError12 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        GenericFontFamily monospace4 = FontFamily.Companion.getMonospace();
                        long sp4 = TextUnitKt.getSp(10);
                        FontWeight bold4 = FontWeight.Companion.getBold();
                        Modifier modifierClip9 = ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8)));
                        float fM7016constructorimpl11 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                        long auroraError13 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("UNAVAILABLE", ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierClip9, fM7016constructorimpl11, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError13, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError13) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError13) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError13) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError13) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8))), ai.nous.aurora.compat.Compat.m7016constructorimpl(8), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), auroraError12, sp4, (FontStyle) null, bold4, (FontFamily) monospace4, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199686, 0, 130960);
                        $composer$iv.endReplaceGroup();
                    } else {
                        $composer$iv.startReplaceGroup(-48452108);
                        $composer$iv.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    if (index$iv < CollectionsKt.getLastIndex(activeChoices)) {
                        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                    }
                    $composer$iv.endMovableGroup();
                    str5 = str117;
                    localMap$iv$iv = localMap$iv$iv18;
                    compositeKeyHash$iv$iv6 = compositeKeyHash$iv$iv12;
                    index$iv = index$iv3;
                    $this$forEachIndexed$iv = $this$forEachIndexed$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    it2 = it6;
                    $this$invoke_u24lambda_u2423_u24lambda_u2422 = $this$invoke_u24lambda_u2423_u24lambda_u2424;
                    str = str112;
                    $composer2 = $composer5;
                    $composer$iv = $composer$iv3;
                    str9 = str8;
                    str4 = str118;
                    str6 = str119;
                    str7 = str1110;
                }
                $composer3 = $composer2;
                $composer$iv.endReplaceGroup();
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        function1 = function13;
        $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
        $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
        int i30 = ($changed$iv$iv$iv >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
        ColumnScopeInstance columnScopeInstance8 = ColumnScopeInstance.INSTANCE;
        int i31 = ((0 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -1804908505, "C1527@68881L529,1541@69427L788:InnerShell.kt#m1syn");
        Modifier modifier$iv20 = ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(10), 0.0f, 0.0f, 13, null);
        Alignment contentAlignment$iv4 = Alignment.Companion.getCenter();
        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
        MeasurePolicy measurePolicy$iv20 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
        int $changed$iv$iv20 = (54 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv20 = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv20 = ComposedModifierKt.materializeModifier($composer, modifier$iv20);
        constructor = ComposeUiNode.Companion.getConstructor();
        int $changed$iv$iv$iv20 = (($changed$iv$iv20 << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            function2 = constructor;
            $composer.createNode(function2);
        } else {
            function2 = constructor;
            $composer.useNode();
        }
        $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv20, ComposeUiNode.Companion.getSetMeasurePolicy());
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv20, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash20 = ComposeUiNode.Companion.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
        }
        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash20);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv20, ComposeUiNode.Companion.getSetModifier());
        int i32 = ($changed$iv$iv$iv20 >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
        BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
        int i33 = ((54 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, 705275896, "C1538@69330L19,1533@69110L282:InnerShell.kt#m1syn");
        Modifier modifierClip10 = ClipKt.clip(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(36)), ai.nous.aurora.compat.Compat.m7016constructorimpl(4)), RoundedCornerShapeKt.getCircleShape());
        long auroraOutlineStrong2 = AuroraColorSchemeKt.getAuroraOutlineStrong($composer, 0);
        BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip10, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraOutlineStrong2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraOutlineStrong2) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraOutlineStrong2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraOutlineStrong2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraOutlineStrong2) : 0.0f), null, 2, null), $composer, 0);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifier$iv21 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(16), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
        Alignment.Vertical verticalAlignment$iv10 = Alignment.Companion.getCenterVertically();
        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
        Arrangement.Horizontal horizontalArrangement$iv10 = Arrangement.INSTANCE.getStart();
        measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv10, verticalAlignment$iv10, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
        int $changed$iv$iv21 = (390 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv21 = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv21 = ComposedModifierKt.materializeModifier($composer, modifier$iv21);
        constructor2 = ComposeUiNode.Companion.getConstructor();
        int $changed$iv$iv$iv21 = (($changed$iv$iv21 << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            function3 = constructor2;
            $composer.createNode(function3);
        } else {
            function3 = constructor2;
            $composer.useNode();
        }
        $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv21, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash21 = ComposeUiNode.Companion.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
            measurePolicy$iv2 = measurePolicy$iv;
            if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv21, ComposeUiNode.Companion.getSetModifier());
            int i212 = ($changed$iv$iv$iv21 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i213 = ((390 >> 6) & 112) | 6;
            RowScope $this$invoke_u24lambda_u2423_u24lambda_u245 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 705859192, "C1549@69763L10,1547@69691L261,1554@69973L224:InnerShell.kt#m1syn");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Models", ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u245, Modifier.Companion, 1.0f, false, 2, null), AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131024);
            function4 = function1;
            str = str11;
            AuroraDsKt.AuroraPillAction("Done", function12, ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(84)), true, $composer, 3462, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.startReplaceGroup(-1028022376);
            ComposerKt.sourceInformation($composer, "1568@70651L13,1569@70728L13,1563@70383L1373");
            str2 = str10;
            if (str2 != null) {
                z = true;
            } else {
                z = true;
            }
            if (z) {
                Modifier modifierClip11 = ClipKt.clip(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(16), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(12)));
                long auroraWarning5 = AuroraColorSchemeKt.getAuroraWarning($composer, 0);
                Modifier modifierM589backgroundbw27NRU$default8 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip11, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraWarning5, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraWarning5) : 0.08f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraWarning5) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraWarning5) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraWarning5) : 0.0f), null, 2, null);
                float fM7016constructorimpl12 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                long auroraWarning6 = AuroraColorSchemeKt.getAuroraWarning($composer, 0);
                Modifier modifier$iv112 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default8, fM7016constructorimpl12, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraWarning6, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraWarning6) : 0.28f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraWarning6) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraWarning6) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraWarning6) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(12))), ai.nous.aurora.compat.Compat.m7016constructorimpl(48), 0.0f, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                Alignment.Vertical verticalAlignment$iv11 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv11 = Arrangement.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv113 = RowKt.rowMeasurePolicy(horizontalArrangement$iv11, verticalAlignment$iv11, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv112 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv113 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv112 = ComposedModifierKt.materializeModifier($composer, modifier$iv112);
                constructor9 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv112 = (($changed$iv$iv112 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function11 = constructor9;
                    $composer.createNode(function11);
                } else {
                    function11 = constructor9;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv9 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv113, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv113, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash112 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash112);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv112, ComposeUiNode.Companion.getSetModifier());
                int i214 = ($changed$iv$iv$iv112 >> 6) & 14;
                str3 = "C101@5126L9:Row.kt#2w3rfo";
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, str3);
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                int i215 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 707199384, "C1578@71228L13,1574@71036L233,1580@71294L39,1583@71485L10,1581@71358L376:InnerShell.kt#m1syn");
                BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraWarning($composer, 0), null, 2, null), $composer, 0);
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer, 6);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Saved model unavailable — choose a replacement", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 3120, 120786);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
            } else {
                str3 = "C101@5126L9:Row.kt#2w3rfo";
            }
            $composer.endReplaceGroup();
            if (list.isEmpty()) {
                $composer.startReplaceGroup(-1802393228);
                ComposerKt.sourceInformation($composer, "1594@71950L15,1592@71836L212");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("No models are available from the connected host.", ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 54, 0, 131064);
                $composer.endReplaceGroup();
                $composer3 = $composer;
            } else {
                $composer.startReplaceGroup(-1801841955);
                ComposerKt.sourceInformation($composer, "1598@72094L3748,1674@76055L21,1670@75863L5970");
                Modifier modifier$iv113 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(6));
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv5 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv114 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
                int $changed$iv$iv113 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv114 = $composer.getCurrentCompositionLocalMap();
                materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv113);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv113 = (($changed$iv$iv113 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor3;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor3;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv114, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv114, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash113 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash113);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i1114 = ($changed$iv$iv$iv113 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                int i1115 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 708550364, "C1612@72797L19,1613@72864L12,1614@72968L27,1615@73039L56,1607@72532L2370,1653@75045L28,1654@75101L719,1651@74927L893:InnerShell.kt#m1syn");
                List<MainViewModel.ModelChoice> list5 = list;
                $this$firstOrNull$iv = list5;
                it = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    it4 = (MainViewModel.ModelChoice) element$iv;
                    $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                    materialized$iv$iv2 = materialized$iv$iv;
                    if (Intrinsics.areEqual(it4.getProvider(), InnerShellKt.ModelPickerSheet$lambda$84(mutableState))) {
                        break;
                        break;
                    } else {
                        $this$firstOrNull$iv = $this$firstOrNull$iv2;
                        materialized$iv$iv = materialized$iv$iv2;
                    }
                }
                modelChoice = (MainViewModel.ModelChoice) element$iv;
                if (modelChoice != null) {
                    activeProviderLabel = InnerShellKt.ModelPickerSheet$lambda$84(mutableState);
                } else {
                    activeProviderLabel = InnerShellKt.ModelPickerSheet$lambda$84(mutableState);
                }
                Modifier modifierM589backgroundbw27NRU$default9 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer, 0), null, 2, null);
                float fM7016constructorimpl13 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                long auroraAccent5 = AuroraColorSchemeKt.getAuroraAccent($composer, 0);
                Modifier modifierM600borderxT4_qwU6 = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default9, fM7016constructorimpl13, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent5, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent5) : 0.42f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent5) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent5) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent5) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16)));
                $composer.startReplaceGroup(-254220628);
                ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                it$iv = $composer.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$7$lambda$6(mutableState2);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                $composer.endReplaceGroup();
                Modifier modifierM622clickableXHw0xAI$default3 = ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU6, false, null, null, (Function0) value$iv2, 7, null);
                $composer.startReplaceGroup(-254218327);
                ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                invalid$iv = $composer.changed(activeProviderLabel);
                Object it$iv7 = $composer.rememberedValue();
                if (invalid$iv) {
                }
                value$iv3 = new Function1() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$9$lambda$8(activeProviderLabel, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv3);
                $composer.endReplaceGroup();
                Modifier modifier$iv114 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM622clickableXHw0xAI$default3, false, (Function1) value$iv3, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                Alignment.Vertical verticalAlignment$iv12 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv12 = Arrangement.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv115 = RowKt.rowMeasurePolicy(horizontalArrangement$iv12, verticalAlignment$iv12, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv114 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv115 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv113 = ComposedModifierKt.materializeModifier($composer, modifier$iv114);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv114 = (($changed$iv$iv114 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function6 = constructor4;
                    $composer.createNode(function6);
                } else {
                    function6 = constructor4;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv115, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv115, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash114 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash114);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv113, ComposeUiNode.Companion.getSetModifier());
                int i1116 = ($changed$iv$iv$iv114 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, str3);
                int i1117 = ((384 >> 6) & 112) | 6;
                RowScope $this$invoke_u24lambda_u2423_u24lambda_u2414_u24lambda_u2413 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -1359517038, "C1623@73515L12,1619@73307L252,1625@73588L29,1626@73646L931,1647@74768L12,1644@74606L270:InnerShell.kt#m1syn");
                BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(9)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraAccent($composer, 0), null, 2, null), $composer, 0);
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), $composer, 6);
                Modifier modifier$iv115 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2414_u24lambda_u2413, Modifier.Companion, 1.0f, false, 2, null);
                str4 = "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo";
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, str4);
                Arrangement.Vertical verticalArrangement$iv10 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv10 = Alignment.Companion.getStart();
                str5 = str3;
                MeasurePolicy measurePolicy$iv116 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv10, horizontalAlignment$iv10, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv115 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv116 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv114 = ComposedModifierKt.materializeModifier($composer, modifier$iv115);
                constructor5 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv115 = (($changed$iv$iv115 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function7 = constructor5;
                    $composer.createNode(function7);
                } else {
                    function7 = constructor5;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv5 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv116, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv116, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash115 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash115);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv114, ComposeUiNode.Companion.getSetModifier());
                int i1118 = ($changed$iv$iv$iv115 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance9 = ColumnScopeInstance.INSTANCE;
                int i1119 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1490674488, "C1629@73817L15,1627@73719L399,1637@74258L10,1635@74151L396:InnerShell.kt#m1syn");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("PROVIDER", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(10), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) FontFamily.Companion.getMonospace(), TextUnitKt.getSp(0.8d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(activeProviderLabel, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ExpandMoreKt.getExpandMore(Icons.INSTANCE.getDefault()), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraAccent($composer, 0), $composer, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                boolean zModelPickerSheet$lambda$89 = InnerShellKt.ModelPickerSheet$lambda$87(mutableState2);
                $composer.startReplaceGroup(-254154163);
                ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
                it$iv2 = $composer.rememberedValue();
                if (it$iv2 == Composer.Companion.getEmpty()) {
                    value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$13$lambda$12(mutableState2);
                        }
                    };
                    $composer.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv2;
                }
                $composer.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zModelPickerSheet$lambda$89, (Function0) value$iv4, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1146712311, true, new InnerShellKt$ModelPickerSheet$1$2$2$4$5(list2, list, mutableState, mutableState2), $composer, 54), $composer, 48, 48, 2044);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer2 = $composer;
                Modifier modifier$iv116 = ai.nous.aurora.compat.Compat.m1036paddingVpY3zN4$default(ai.nous.aurora.compat.Compat.d_ScrollKt_verticalScroll_7(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(400), 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, str4);
                Arrangement.Vertical verticalArrangement$iv11 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv11 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv117 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv11, horizontalAlignment$iv11, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv116 = (0 << 3) & 112;
                str6 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str6);
                compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv115 = ComposedModifierKt.materializeModifier($composer2, modifier$iv116);
                constructor6 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv116 = (($changed$iv$iv116 << 6) & 896) | 6;
                str7 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, str7);
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function8 = constructor6;
                    $composer2.createNode(function8);
                } else {
                    function8 = constructor6;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv6 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv117, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash116 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash116);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv115, ComposeUiNode.Companion.getSetModifier());
                $changed$iv = ($changed$iv$iv$iv116 >> 6) & 14;
                $composer$iv = $composer2;
                i = 0;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                int i1120 = ((0 >> 6) & 112) | 6;
                $this$invoke_u24lambda_u2423_u24lambda_u2422 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer$iv, 712458999, "C1763@81771L40:InnerShell.kt#m1syn");
                $this$filter$iv = list5;
                destination$iv$iv = new ArrayList();
                java.util.Iterator<Object> r56 = java.util.Collections.emptyList().iterator();
                while (r56.hasNext()) {
                    Iterable $this$filter$iv4 = $this$filter$iv;
                    Object element$iv$iv = r56.next();
                    it3 = (MainViewModel.ModelChoice) element$iv$iv;
                    int $changed$iv4 = $changed$iv;
                    int i1121 = i;
                    if (Intrinsics.areEqual(it3.getProvider(), InnerShellKt.ModelPickerSheet$lambda$84(mutableState))) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                    $this$filter$iv = $this$filter$iv4;
                    $changed$iv = $changed$iv4;
                    i = i1121;
                }
                Iterable $this$sortedBy$iv3 = (List) destination$iv$iv;
                activeChoices = CollectionsKt.sortedWith($this$sortedBy$iv3, new Comparator() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$invoke$lambda$23$lambda$22$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(Object t, Object t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(((MainViewModel.ModelChoice) t).getPersistable() ? 1 : 0), Integer.valueOf(((MainViewModel.ModelChoice) t2).getPersistable() ? 1 : 0));
                    }
                });
                $composer$iv.startReplaceGroup(-254092806);
                ComposerKt.sourceInformation($composer$iv, "");
                $this$forEachIndexed$iv = activeChoices;
                $i$f$forEachIndexed = 0;
                index$iv = 0;
                it2 = $this$forEachIndexed$iv.iterator();
                while (it2.hasNext()) {
                    Object item$iv3 = it2.next();
                    int index$iv4 = index$iv + 1;
                    if (index$iv < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Iterable $this$forEachIndexed$iv4 = $this$forEachIndexed$iv;
                    choice = (MainViewModel.ModelChoice) item$iv3;
                    int $i$f$forEachIndexed4 = $i$f$forEachIndexed;
                    Iterator it7 = it2;
                    ColumnScope $this$invoke_u24lambda_u2423_u24lambda_u2425 = $this$invoke_u24lambda_u2423_u24lambda_u2422;
                    $composer$iv.startMovableGroup(1480282593, choice.getRole() + ":" + choice.getKey());
                    ComposerKt.sourceInformation($composer$iv, "1709@78328L20,1689@77007L4501,1759@81616L40");
                    String str1111 = str;
                    selected = invoke$lambda$23$lambda$22$isSelected(str1111, str12, choice);
                    CompositionLocalMap localMap$iv$iv117 = localMap$iv$iv;
                    int compositeKeyHash$iv$iv13 = compositeKeyHash$iv$iv6;
                    Modifier modifierClip12 = ClipKt.clip(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14)));
                    if (selected) {
                        $composer$iv.startReplaceGroup(1480293445);
                        ComposerKt.sourceInformation($composer$iv, "1695@77361L21");
                        long auroraSurfaceSelected3 = AuroraColorSchemeKt.getAuroraSurfaceSelected($composer$iv, 0);
                        $composer$iv.endReplaceGroup();
                        j = auroraSurfaceSelected3;
                    } else if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(1480296803);
                        ComposerKt.sourceInformation($composer$iv, "1696@77454L11");
                        long auroraError14 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        long jM4532copywmQWz5c4 = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError14, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError14) : 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError14) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError14) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError14) : 0.0f);
                        $composer$iv.endReplaceGroup();
                        j = jM4532copywmQWz5c4;
                    } else {
                        $composer$iv.startReplaceGroup(1480299235);
                        ComposerKt.sourceInformation($composer$iv, "1697@77542L19");
                        long auroraSurfaceRaised3 = AuroraColorSchemeKt.getAuroraSurfaceRaised($composer$iv, 0);
                        $composer$iv.endReplaceGroup();
                        j = auroraSurfaceRaised3;
                    }
                    Modifier modifierM589backgroundbw27NRU$default10 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip12, j, null, 2, null);
                    float fM7016constructorimpl14 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                    if (selected) {
                        $composer$iv.startReplaceGroup(1480309858);
                        ComposerKt.sourceInformation($composer$iv, "1703@77861L12");
                        long auroraAccent6 = AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0);
                        jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent6, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent6) : 0.6f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent6) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent6) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent6) : 0.0f);
                        $composer$iv.endReplaceGroup();
                    } else if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(1480313123);
                        ComposerKt.sourceInformation($composer$iv, "1704@77964L11");
                        long auroraError15 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError15, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError15) : 0.55f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError15) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError15) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError15) : 0.0f);
                        $composer$iv.endReplaceGroup();
                    } else {
                        $composer$iv.startReplaceGroup(1480316035);
                        ComposerKt.sourceInformation($composer$iv, "1705@78052L14");
                        long auroraHairline3 = AuroraColorSchemeKt.getAuroraHairline($composer$iv, 0);
                        jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraHairline3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraHairline3) : 0.55f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraHairline3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraHairline3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraHairline3) : 0.0f);
                        $composer$iv.endReplaceGroup();
                    }
                    Composer $composer6 = $composer2;
                    Composer $composer$iv4 = $composer$iv;
                    Modifier modifierM600borderxT4_qwU7 = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default10, fM7016constructorimpl14, jM4532copywmQWz5c, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14)));
                    boolean persistable3 = choice.getPersistable();
                    $composer$iv.startReplaceGroup(1480324388);
                    ComposerKt.sourceInformation($composer$iv, str9);
                    final Function1 function16 = function4;
                    invalid$iv2 = $composer$iv.changed(function16) | $composer$iv.changed(choice);
                    it$iv3 = $composer$iv.rememberedValue();
                    if (invalid$iv2) {
                        str8 = str9;
                        if (it$iv3 == Composer.Companion.getEmpty()) {
                            value$iv5 = it$iv3;
                        }
                        $composer$iv.endReplaceGroup();
                        Modifier modifier$iv117 = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU7, persistable3, null, null, (Function0) value$iv5, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
                        Alignment.Vertical verticalAlignment$iv13 = Alignment.Companion.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv13 = Arrangement.INSTANCE.getStart();
                        function4 = function16;
                        MeasurePolicy measurePolicy$iv118 = RowKt.rowMeasurePolicy(horizontalArrangement$iv13, verticalAlignment$iv13, $composer$iv, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv117 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                        compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                        CompositionLocalMap localMap$iv$iv118 = $composer$iv.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv116 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv117);
                        constructor7 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv117 = (($changed$iv$iv117 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                        if (!($composer$iv.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer$iv.startReusableNode();
                        if ($composer$iv.getInserting()) {
                            function9 = constructor7;
                            $composer$iv.createNode(function9);
                        } else {
                            function9 = constructor7;
                            $composer$iv.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv7 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv118, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv118, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash117 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash117);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv116, ComposeUiNode.Companion.getSetModifier());
                        int i11110 = ($changed$iv$iv$iv117 >> 6) & 14;
                        String str1112 = str5;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -407840262, str1112);
                        int i11111 = ((384 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2424 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -51160205, "C1714@78656L1494:InnerShell.kt#m1syn");
                        Modifier modifier$iv118 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2424, Modifier.Companion, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -483455358, str4);
                        Arrangement.Vertical verticalArrangement$iv12 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv12 = Alignment.Companion.getStart();
                        String str1113 = str4;
                        MeasurePolicy measurePolicy$iv119 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv12, horizontalAlignment$iv12, $composer$iv, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv118 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                        compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                        CompositionLocalMap localMap$iv$iv119 = $composer$iv.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv117 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv118);
                        constructor8 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv118 = (($changed$iv$iv118 << 6) & 896) | 6;
                        String str1114 = str6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                        if (!($composer$iv.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer$iv.startReusableNode();
                        if ($composer$iv.getInserting()) {
                            function10 = constructor8;
                            $composer$iv.createNode(function10);
                        } else {
                            function10 = constructor8;
                            $composer$iv.useNode();
                        }
                        String str1115 = str7;
                        $this$Layout_u24lambda_u240$iv$iv8 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv119, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv119, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash118 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                        $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash118);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv117, ComposeUiNode.Companion.getSetModifier());
                        int i216 = ($changed$iv$iv$iv118 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance10 = ColumnScopeInstance.INSTANCE;
                        int i217 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, 504703351, "C1715@78737L524,1723@79302L810:InnerShell.kt#m1syn");
                        String strFriendlyModel5 = ComposerChipPolicy.INSTANCE.friendlyModel(choice.getModel());
                        if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(-1230641033);
                            ComposerKt.sourceInformation($composer$iv, "1717@78911L10");
                            auroraTextMuted = AuroraColorSchemeKt.getAuroraText($composer$iv, 0);
                        } else {
                            $composer$iv.startReplaceGroup(-1230640516);
                            ComposerKt.sourceInformation($composer$iv, "1717@78927L15");
                            auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                        }
                        $composer$iv.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyModel5, (Modifier) null, auroraTextMuted, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199680, 3120, 120786);
                        role = choice.getRole();
                        switch (role.hashCode()) {
                            case -911832760:
                                if (role.equals("Delegation")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Delegation · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                }
                                break;
                            case 825887490:
                                if (role.equals("Fallback")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Fallback · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                }
                                break;
                            case 1002405936:
                                if (role.equals("Unavailable")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Unavailable saved pair";
                                }
                                break;
                            case 1349887458:
                                if (role.equals("Primary")) {
                                    strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                } else {
                                    strFriendlyProvider = "Default · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                }
                                break;
                            default:
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                                break;
                        }
                        if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(-1230611332);
                            ComposerKt.sourceInformation($composer$iv, "1731@79839L15");
                            auroraError = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                        } else {
                            $composer$iv.startReplaceGroup(-1230610664);
                            ComposerKt.sourceInformation($composer$iv, "1731@79860L11");
                            auroraError = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        }
                        $composer$iv.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyProvider, (Modifier) null, auroraError, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3072, 3120, 120818);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        $composer$iv.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        if (selected) {
                            $composer$iv.startReplaceGroup(-49702183);
                            ComposerKt.sourceInformation($composer$iv, "1741@80442L12,1738@80243L331");
                            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Selected", ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(22)), AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0), $composer$iv, 432, 0);
                            $composer$iv.endReplaceGroup();
                        } else if (choice.getPersistable()) {
                            $composer$iv.startReplaceGroup(-49250699);
                            ComposerKt.sourceInformation($composer$iv, "1747@80809L11,1753@81243L11,1745@80685L751");
                            long auroraError16 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                            GenericFontFamily monospace5 = FontFamily.Companion.getMonospace();
                            long sp5 = TextUnitKt.getSp(10);
                            FontWeight bold5 = FontWeight.Companion.getBold();
                            Modifier modifierClip13 = ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8)));
                            float fM7016constructorimpl15 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                            long auroraError17 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("UNAVAILABLE", ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierClip13, fM7016constructorimpl15, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError17, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError17) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError17) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError17) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError17) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8))), ai.nous.aurora.compat.Compat.m7016constructorimpl(8), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), auroraError16, sp5, (FontStyle) null, bold5, (FontFamily) monospace5, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199686, 0, 130960);
                            $composer$iv.endReplaceGroup();
                        } else {
                            $composer$iv.startReplaceGroup(-48452108);
                            $composer$iv.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        $composer$iv.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        if (index$iv < CollectionsKt.getLastIndex(activeChoices)) {
                            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                        }
                        $composer$iv.endMovableGroup();
                        str5 = str1112;
                        localMap$iv$iv = localMap$iv$iv117;
                        compositeKeyHash$iv$iv6 = compositeKeyHash$iv$iv13;
                        index$iv = index$iv4;
                        $this$forEachIndexed$iv = $this$forEachIndexed$iv4;
                        $i$f$forEachIndexed = $i$f$forEachIndexed4;
                        it2 = it7;
                        $this$invoke_u24lambda_u2423_u24lambda_u2422 = $this$invoke_u24lambda_u2423_u24lambda_u2425;
                        str = str1111;
                        $composer2 = $composer6;
                        $composer$iv = $composer$iv4;
                        str9 = str8;
                        str4 = str1113;
                        str6 = str1114;
                        str7 = str1115;
                    } else {
                        str8 = str9;
                    }
                    value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$22$lambda$21$lambda$18$lambda$17(function16, choice);
                        }
                    };
                    $composer$iv.updateRememberedValue(value$iv5);
                    $composer$iv.endReplaceGroup();
                    Modifier modifier$iv119 = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU7, persistable3, null, null, (Function0) value$iv5, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
                    Alignment.Vertical verticalAlignment$iv14 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv14 = Arrangement.INSTANCE.getStart();
                    function4 = function16;
                    MeasurePolicy measurePolicy$iv1110 = RowKt.rowMeasurePolicy(horizontalArrangement$iv14, verticalAlignment$iv14, $composer$iv, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv119 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                    compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                    CompositionLocalMap localMap$iv$iv1110 = $composer$iv.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv118 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv119);
                    constructor7 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv119 = (($changed$iv$iv119 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                    if (!($composer$iv.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer$iv.startReusableNode();
                    if ($composer$iv.getInserting()) {
                        function9 = constructor7;
                        $composer$iv.createNode(function9);
                    } else {
                        function9 = constructor7;
                        $composer$iv.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv7 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv1110, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv1110, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash119 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                    $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash119);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv118, ComposeUiNode.Companion.getSetModifier());
                    int i11112 = ($changed$iv$iv$iv119 >> 6) & 14;
                    String str1116 = str5;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -407840262, str1116);
                    int i11113 = ((384 >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2425 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -51160205, "C1714@78656L1494:InnerShell.kt#m1syn");
                    Modifier modifier$iv1110 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2425, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -483455358, str4);
                    Arrangement.Vertical verticalArrangement$iv13 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv13 = Alignment.Companion.getStart();
                    String str1117 = str4;
                    MeasurePolicy measurePolicy$iv1111 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv13, horizontalAlignment$iv13, $composer$iv, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv1110 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                    compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                    CompositionLocalMap localMap$iv$iv1111 = $composer$iv.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv119 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv1110);
                    constructor8 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv1110 = (($changed$iv$iv1110 << 6) & 896) | 6;
                    String str1118 = str6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                    if (!($composer$iv.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer$iv.startReusableNode();
                    if ($composer$iv.getInserting()) {
                        function10 = constructor8;
                        $composer$iv.createNode(function10);
                    } else {
                        function10 = constructor8;
                        $composer$iv.useNode();
                    }
                    String str1119 = str7;
                    $this$Layout_u24lambda_u240$iv$iv8 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv1111, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv1111, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1110 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                    $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash1110);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv119, ComposeUiNode.Companion.getSetModifier());
                    int i218 = ($changed$iv$iv$iv1110 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance11 = ColumnScopeInstance.INSTANCE;
                    int i219 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, 504703351, "C1715@78737L524,1723@79302L810:InnerShell.kt#m1syn");
                    String strFriendlyModel6 = ComposerChipPolicy.INSTANCE.friendlyModel(choice.getModel());
                    if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(-1230641033);
                        ComposerKt.sourceInformation($composer$iv, "1717@78911L10");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraText($composer$iv, 0);
                    } else {
                        $composer$iv.startReplaceGroup(-1230640516);
                        ComposerKt.sourceInformation($composer$iv, "1717@78927L15");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                    }
                    $composer$iv.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyModel6, (Modifier) null, auroraTextMuted, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199680, 3120, 120786);
                    role = choice.getRole();
                    switch (role.hashCode()) {
                        case -911832760:
                            if (role.equals("Delegation")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Delegation · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            }
                            break;
                        case 825887490:
                            if (role.equals("Fallback")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Fallback · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            }
                            break;
                        case 1002405936:
                            if (role.equals("Unavailable")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Unavailable saved pair";
                            }
                            break;
                        case 1349887458:
                            if (role.equals("Primary")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Default · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            }
                            break;
                        default:
                            strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            break;
                    }
                    if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(-1230611332);
                        ComposerKt.sourceInformation($composer$iv, "1731@79839L15");
                        auroraError = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                    } else {
                        $composer$iv.startReplaceGroup(-1230610664);
                        ComposerKt.sourceInformation($composer$iv, "1731@79860L11");
                        auroraError = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                    }
                    $composer$iv.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyProvider, (Modifier) null, auroraError, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3072, 3120, 120818);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    if (selected) {
                        $composer$iv.startReplaceGroup(-49702183);
                        ComposerKt.sourceInformation($composer$iv, "1741@80442L12,1738@80243L331");
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Selected", ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(22)), AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0), $composer$iv, 432, 0);
                        $composer$iv.endReplaceGroup();
                    } else if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(-49250699);
                        ComposerKt.sourceInformation($composer$iv, "1747@80809L11,1753@81243L11,1745@80685L751");
                        long auroraError18 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        GenericFontFamily monospace6 = FontFamily.Companion.getMonospace();
                        long sp6 = TextUnitKt.getSp(10);
                        FontWeight bold6 = FontWeight.Companion.getBold();
                        Modifier modifierClip14 = ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8)));
                        float fM7016constructorimpl16 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                        long auroraError19 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("UNAVAILABLE", ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierClip14, fM7016constructorimpl16, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError19, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError19) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError19) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError19) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError19) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8))), ai.nous.aurora.compat.Compat.m7016constructorimpl(8), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), auroraError18, sp6, (FontStyle) null, bold6, (FontFamily) monospace6, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199686, 0, 130960);
                        $composer$iv.endReplaceGroup();
                    } else {
                        $composer$iv.startReplaceGroup(-48452108);
                        $composer$iv.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    if (index$iv < CollectionsKt.getLastIndex(activeChoices)) {
                        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                    }
                    $composer$iv.endMovableGroup();
                    str5 = str1116;
                    localMap$iv$iv = localMap$iv$iv117;
                    compositeKeyHash$iv$iv6 = compositeKeyHash$iv$iv13;
                    index$iv = index$iv4;
                    $this$forEachIndexed$iv = $this$forEachIndexed$iv4;
                    $i$f$forEachIndexed = $i$f$forEachIndexed4;
                    it2 = it7;
                    $this$invoke_u24lambda_u2423_u24lambda_u2422 = $this$invoke_u24lambda_u2423_u24lambda_u2425;
                    str = str1111;
                    $composer2 = $composer6;
                    $composer$iv = $composer$iv4;
                    str9 = str8;
                    str4 = str1117;
                    str6 = str1118;
                    str7 = str1119;
                }
                $composer3 = $composer2;
                $composer$iv.endReplaceGroup();
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        measurePolicy$iv2 = measurePolicy$iv;
        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash21);
        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv21, ComposeUiNode.Companion.getSetModifier());
        int i2110 = ($changed$iv$iv$iv21 >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
        int i2111 = ((390 >> 6) & 112) | 6;
        RowScope $this$invoke_u24lambda_u2423_u24lambda_u246 = RowScopeInstance.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 705859192, "C1549@69763L10,1547@69691L261,1554@69973L224:InnerShell.kt#m1syn");
        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Models", ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u246, Modifier.Companion, 1.0f, false, 2, null), AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131024);
        function4 = function1;
        str = str11;
        AuroraDsKt.AuroraPillAction("Done", function12, ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(84)), true, $composer, 3462, 0);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.startReplaceGroup(-1028022376);
        ComposerKt.sourceInformation($composer, "1568@70651L13,1569@70728L13,1563@70383L1373");
        str2 = str10;
        if (str2 != null) {
            z = true;
        } else {
            z = true;
        }
        if (z) {
            Modifier modifierClip15 = ClipKt.clip(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(16), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(12)));
            long auroraWarning7 = AuroraColorSchemeKt.getAuroraWarning($composer, 0);
            Modifier modifierM589backgroundbw27NRU$default11 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip15, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraWarning7, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraWarning7) : 0.08f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraWarning7) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraWarning7) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraWarning7) : 0.0f), null, 2, null);
            float fM7016constructorimpl17 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
            long auroraWarning8 = AuroraColorSchemeKt.getAuroraWarning($composer, 0);
            Modifier modifier$iv1111 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default11, fM7016constructorimpl17, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraWarning8, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraWarning8) : 0.28f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraWarning8) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraWarning8) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraWarning8) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(12))), ai.nous.aurora.compat.Compat.m7016constructorimpl(48), 0.0f, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
            Alignment.Vertical verticalAlignment$iv15 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv15 = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv1112 = RowKt.rowMeasurePolicy(horizontalArrangement$iv15, verticalAlignment$iv15, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv1111 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv1112 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv1110 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111);
            constructor9 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv1111 = (($changed$iv$iv1111 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function11 = constructor9;
                $composer.createNode(function11);
            } else {
                function11 = constructor9;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv9 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv1112, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv1112, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
            $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash1111);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv1110, ComposeUiNode.Companion.getSetModifier());
            int i2112 = ($changed$iv$iv$iv1111 >> 6) & 14;
            str3 = "C101@5126L9:Row.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart($composer, -407840262, str3);
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            int i2113 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 707199384, "C1578@71228L13,1574@71036L233,1580@71294L39,1583@71485L10,1581@71358L376:InnerShell.kt#m1syn");
            BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraWarning($composer, 0), null, 2, null), $composer, 0);
            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer, 6);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Saved model unavailable — choose a replacement", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 3120, 120786);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
        } else {
            str3 = "C101@5126L9:Row.kt#2w3rfo";
        }
        $composer.endReplaceGroup();
        if (list.isEmpty()) {
            $composer.startReplaceGroup(-1802393228);
            ComposerKt.sourceInformation($composer, "1594@71950L15,1592@71836L212");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("No models are available from the connected host.", ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 54, 0, 131064);
            $composer.endReplaceGroup();
            $composer3 = $composer;
        } else {
            $composer.startReplaceGroup(-1801841955);
            ComposerKt.sourceInformation($composer, "1598@72094L3748,1674@76055L21,1670@75863L5970");
            Modifier modifier$iv1112 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(6));
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv6 = Alignment.Companion.getTopStart();
            MeasurePolicy measurePolicy$iv1113 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
            int $changed$iv$iv1112 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv1113 = $composer.getCurrentCompositionLocalMap();
            materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv1112);
            constructor3 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv1112 = (($changed$iv$iv1112 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function5 = constructor3;
                $composer.createNode(function5);
            } else {
                function5 = constructor3;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv1113, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv1113, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1112 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash1112);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i11114 = ($changed$iv$iv$iv1112 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
            int i11115 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 708550364, "C1612@72797L19,1613@72864L12,1614@72968L27,1615@73039L56,1607@72532L2370,1653@75045L28,1654@75101L719,1651@74927L893:InnerShell.kt#m1syn");
            List<MainViewModel.ModelChoice> list6 = list;
            $this$firstOrNull$iv = list6;
            it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it.next();
                it4 = (MainViewModel.ModelChoice) element$iv;
                $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                materialized$iv$iv2 = materialized$iv$iv;
                if (Intrinsics.areEqual(it4.getProvider(), InnerShellKt.ModelPickerSheet$lambda$84(mutableState))) {
                    break;
                    break;
                } else {
                    $this$firstOrNull$iv = $this$firstOrNull$iv2;
                    materialized$iv$iv = materialized$iv$iv2;
                }
            }
            modelChoice = (MainViewModel.ModelChoice) element$iv;
            if (modelChoice != null) {
                activeProviderLabel = InnerShellKt.ModelPickerSheet$lambda$84(mutableState);
            } else {
                activeProviderLabel = InnerShellKt.ModelPickerSheet$lambda$84(mutableState);
            }
            Modifier modifierM589backgroundbw27NRU$default12 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer, 0), null, 2, null);
            float fM7016constructorimpl18 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
            long auroraAccent7 = AuroraColorSchemeKt.getAuroraAccent($composer, 0);
            Modifier modifierM600borderxT4_qwU8 = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default12, fM7016constructorimpl18, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent7, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent7) : 0.42f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent7) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent7) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent7) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(16)));
            $composer.startReplaceGroup(-254220628);
            ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
            it$iv = $composer.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$7$lambda$6(mutableState2);
                    }
                };
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv;
            }
            $composer.endReplaceGroup();
            Modifier modifierM622clickableXHw0xAI$default4 = ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU8, false, null, null, (Function0) value$iv2, 7, null);
            $composer.startReplaceGroup(-254218327);
            ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
            invalid$iv = $composer.changed(activeProviderLabel);
            Object it$iv8 = $composer.rememberedValue();
            if (invalid$iv) {
            }
            value$iv3 = new Function1() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$9$lambda$8(activeProviderLabel, (SemanticsPropertyReceiver) obj);
                }
            };
            $composer.updateRememberedValue(value$iv3);
            $composer.endReplaceGroup();
            Modifier modifier$iv1113 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM622clickableXHw0xAI$default4, false, (Function1) value$iv3, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
            Alignment.Vertical verticalAlignment$iv16 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv16 = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv1114 = RowKt.rowMeasurePolicy(horizontalArrangement$iv16, verticalAlignment$iv16, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv1113 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv1114 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv1111 = ComposedModifierKt.materializeModifier($composer, modifier$iv1113);
            constructor4 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv1113 = (($changed$iv$iv1113 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function6 = constructor4;
                $composer.createNode(function6);
            } else {
                function6 = constructor4;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv1114, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv1114, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1113 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash1113);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv1111, ComposeUiNode.Companion.getSetModifier());
            int i11116 = ($changed$iv$iv$iv1113 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407840262, str3);
            int i11117 = ((384 >> 6) & 112) | 6;
            RowScope $this$invoke_u24lambda_u2423_u24lambda_u2414_u24lambda_u2414 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1359517038, "C1623@73515L12,1619@73307L252,1625@73588L29,1626@73646L931,1647@74768L12,1644@74606L270:InnerShell.kt#m1syn");
            BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(9)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraAccent($composer, 0), null, 2, null), $composer, 0);
            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), $composer, 6);
            Modifier modifier$iv1114 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2414_u24lambda_u2414, Modifier.Companion, 1.0f, false, 2, null);
            str4 = "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, str4);
            Arrangement.Vertical verticalArrangement$iv14 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv14 = Alignment.Companion.getStart();
            str5 = str3;
            MeasurePolicy measurePolicy$iv1115 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv14, horizontalAlignment$iv14, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv1114 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv1115 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv1112 = ComposedModifierKt.materializeModifier($composer, modifier$iv1114);
            constructor5 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv1114 = (($changed$iv$iv1114 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function7 = constructor5;
                $composer.createNode(function7);
            } else {
                function7 = constructor5;
                $composer.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv5 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv1115, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv1115, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1114 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
            $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash1114);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv1112, ComposeUiNode.Companion.getSetModifier());
            int i11118 = ($changed$iv$iv$iv1114 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance12 = ColumnScopeInstance.INSTANCE;
            int i11119 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1490674488, "C1629@73817L15,1627@73719L399,1637@74258L10,1635@74151L396:InnerShell.kt#m1syn");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("PROVIDER", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(10), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) FontFamily.Companion.getMonospace(), TextUnitKt.getSp(0.8d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(activeProviderLabel, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ExpandMoreKt.getExpandMore(Icons.INSTANCE.getDefault()), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraAccent($composer, 0), $composer, 432, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            boolean zModelPickerSheet$lambda$810 = InnerShellKt.ModelPickerSheet$lambda$87(mutableState2);
            $composer.startReplaceGroup(-254154163);
            ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
            it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.Companion.getEmpty()) {
                value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$14$lambda$13$lambda$12(mutableState2);
                    }
                };
                $composer.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv2;
            }
            $composer.endReplaceGroup();
            ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zModelPickerSheet$lambda$810, (Function0) value$iv4, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1146712311, true, new InnerShellKt$ModelPickerSheet$1$2$2$4$5(list2, list, mutableState, mutableState2), $composer, 54), $composer, 48, 48, 2044);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer2 = $composer;
            Modifier modifier$iv1115 = ai.nous.aurora.compat.Compat.m1036paddingVpY3zN4$default(ai.nous.aurora.compat.Compat.d_ScrollKt_verticalScroll_7(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(400), 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, str4);
            Arrangement.Vertical verticalArrangement$iv15 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv15 = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv1116 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv15, horizontalAlignment$iv15, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv1115 = (0 << 3) & 112;
            str6 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str6);
            compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv1113 = ComposedModifierKt.materializeModifier($composer2, modifier$iv1115);
            constructor6 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv1115 = (($changed$iv$iv1115 << 6) & 896) | 6;
            str7 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, str7);
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function8 = constructor6;
                $composer2.createNode(function8);
            } else {
                function8 = constructor6;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv6 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv1116, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1115 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
            $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash1115);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv1113, ComposeUiNode.Companion.getSetModifier());
            $changed$iv = ($changed$iv$iv$iv1115 >> 6) & 14;
            $composer$iv = $composer2;
            i = 0;
            ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            int i1122 = ((0 >> 6) & 112) | 6;
            $this$invoke_u24lambda_u2423_u24lambda_u2422 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 712458999, "C1763@81771L40:InnerShell.kt#m1syn");
            $this$filter$iv = list6;
            destination$iv$iv = new ArrayList();
            while (r56.hasNext()) {
                Iterable $this$filter$iv5 = $this$filter$iv;
                Object element$iv$iv = r56.next();
                it3 = (MainViewModel.ModelChoice) element$iv$iv;
                int $changed$iv5 = $changed$iv;
                int i1123 = i;
                if (Intrinsics.areEqual(it3.getProvider(), InnerShellKt.ModelPickerSheet$lambda$84(mutableState))) {
                    destination$iv$iv.add(element$iv$iv);
                }
                $this$filter$iv = $this$filter$iv5;
                $changed$iv = $changed$iv5;
                i = i1123;
            }
            Iterable $this$sortedBy$iv4 = (List) destination$iv$iv;
            activeChoices = CollectionsKt.sortedWith($this$sortedBy$iv4, new Comparator() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$invoke$lambda$23$lambda$22$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(Object t, Object t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((MainViewModel.ModelChoice) t).getPersistable() ? 1 : 0), Integer.valueOf(((MainViewModel.ModelChoice) t2).getPersistable() ? 1 : 0));
                }
            });
            $composer$iv.startReplaceGroup(-254092806);
            ComposerKt.sourceInformation($composer$iv, "");
            $this$forEachIndexed$iv = activeChoices;
            $i$f$forEachIndexed = 0;
            index$iv = 0;
            it2 = $this$forEachIndexed$iv.iterator();
            while (it2.hasNext()) {
                Object item$iv4 = it2.next();
                int index$iv5 = index$iv + 1;
                if (index$iv < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Iterable $this$forEachIndexed$iv5 = $this$forEachIndexed$iv;
                choice = (MainViewModel.ModelChoice) item$iv4;
                int $i$f$forEachIndexed5 = $i$f$forEachIndexed;
                Iterator it8 = it2;
                ColumnScope $this$invoke_u24lambda_u2423_u24lambda_u2426 = $this$invoke_u24lambda_u2423_u24lambda_u2422;
                $composer$iv.startMovableGroup(1480282593, choice.getRole() + ":" + choice.getKey());
                ComposerKt.sourceInformation($composer$iv, "1709@78328L20,1689@77007L4501,1759@81616L40");
                String str11110 = str;
                selected = invoke$lambda$23$lambda$22$isSelected(str11110, str12, choice);
                CompositionLocalMap localMap$iv$iv1116 = localMap$iv$iv;
                int compositeKeyHash$iv$iv14 = compositeKeyHash$iv$iv6;
                Modifier modifierClip16 = ClipKt.clip(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14)));
                if (selected) {
                    $composer$iv.startReplaceGroup(1480293445);
                    ComposerKt.sourceInformation($composer$iv, "1695@77361L21");
                    long auroraSurfaceSelected4 = AuroraColorSchemeKt.getAuroraSurfaceSelected($composer$iv, 0);
                    $composer$iv.endReplaceGroup();
                    j = auroraSurfaceSelected4;
                } else if (choice.getPersistable()) {
                    $composer$iv.startReplaceGroup(1480296803);
                    ComposerKt.sourceInformation($composer$iv, "1696@77454L11");
                    long auroraError110 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                    long jM4532copywmQWz5c5 = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError110, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError110) : 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError110) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError110) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError110) : 0.0f);
                    $composer$iv.endReplaceGroup();
                    j = jM4532copywmQWz5c5;
                } else {
                    $composer$iv.startReplaceGroup(1480299235);
                    ComposerKt.sourceInformation($composer$iv, "1697@77542L19");
                    long auroraSurfaceRaised4 = AuroraColorSchemeKt.getAuroraSurfaceRaised($composer$iv, 0);
                    $composer$iv.endReplaceGroup();
                    j = auroraSurfaceRaised4;
                }
                Modifier modifierM589backgroundbw27NRU$default13 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip16, j, null, 2, null);
                float fM7016constructorimpl19 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                if (selected) {
                    $composer$iv.startReplaceGroup(1480309858);
                    ComposerKt.sourceInformation($composer$iv, "1703@77861L12");
                    long auroraAccent8 = AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0);
                    jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent8, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent8) : 0.6f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent8) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent8) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent8) : 0.0f);
                    $composer$iv.endReplaceGroup();
                } else if (choice.getPersistable()) {
                    $composer$iv.startReplaceGroup(1480313123);
                    ComposerKt.sourceInformation($composer$iv, "1704@77964L11");
                    long auroraError111 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                    jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError111, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError111) : 0.55f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError111) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError111) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError111) : 0.0f);
                    $composer$iv.endReplaceGroup();
                } else {
                    $composer$iv.startReplaceGroup(1480316035);
                    ComposerKt.sourceInformation($composer$iv, "1705@78052L14");
                    long auroraHairline4 = AuroraColorSchemeKt.getAuroraHairline($composer$iv, 0);
                    jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraHairline4, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraHairline4) : 0.55f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraHairline4) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraHairline4) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraHairline4) : 0.0f);
                    $composer$iv.endReplaceGroup();
                }
                Composer $composer7 = $composer2;
                Composer $composer$iv5 = $composer$iv;
                Modifier modifierM600borderxT4_qwU9 = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default13, fM7016constructorimpl19, jM4532copywmQWz5c, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14)));
                boolean persistable4 = choice.getPersistable();
                $composer$iv.startReplaceGroup(1480324388);
                ComposerKt.sourceInformation($composer$iv, str9);
                final Function1 function17 = function4;
                invalid$iv2 = $composer$iv.changed(function17) | $composer$iv.changed(choice);
                it$iv3 = $composer$iv.rememberedValue();
                if (invalid$iv2) {
                    str8 = str9;
                    if (it$iv3 == Composer.Companion.getEmpty()) {
                        value$iv5 = it$iv3;
                    }
                    $composer$iv.endReplaceGroup();
                    Modifier modifier$iv1116 = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU9, persistable4, null, null, (Function0) value$iv5, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
                    Alignment.Vertical verticalAlignment$iv17 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv17 = Arrangement.INSTANCE.getStart();
                    function4 = function17;
                    MeasurePolicy measurePolicy$iv1117 = RowKt.rowMeasurePolicy(horizontalArrangement$iv17, verticalAlignment$iv17, $composer$iv, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv1116 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                    compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                    CompositionLocalMap localMap$iv$iv1117 = $composer$iv.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1114 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv1116);
                    constructor7 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv1116 = (($changed$iv$iv1116 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                    if (!($composer$iv.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer$iv.startReusableNode();
                    if ($composer$iv.getInserting()) {
                        function9 = constructor7;
                        $composer$iv.createNode(function9);
                    } else {
                        function9 = constructor7;
                        $composer$iv.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv7 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv1117, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv1117, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1116 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                    $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash1116);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv1114, ComposeUiNode.Companion.getSetModifier());
                    int i111110 = ($changed$iv$iv$iv1116 >> 6) & 14;
                    String str11111 = str5;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -407840262, str11111);
                    int i111111 = ((384 >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2426 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -51160205, "C1714@78656L1494:InnerShell.kt#m1syn");
                    Modifier modifier$iv1117 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2426, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -483455358, str4);
                    Arrangement.Vertical verticalArrangement$iv16 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv16 = Alignment.Companion.getStart();
                    String str11112 = str4;
                    MeasurePolicy measurePolicy$iv1118 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv16, horizontalAlignment$iv16, $composer$iv, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv1117 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                    compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                    CompositionLocalMap localMap$iv$iv1118 = $composer$iv.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1115 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv1117);
                    constructor8 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv1117 = (($changed$iv$iv1117 << 6) & 896) | 6;
                    String str11113 = str6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                    if (!($composer$iv.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer$iv.startReusableNode();
                    if ($composer$iv.getInserting()) {
                        function10 = constructor8;
                        $composer$iv.createNode(function10);
                    } else {
                        function10 = constructor8;
                        $composer$iv.useNode();
                    }
                    String str11114 = str7;
                    $this$Layout_u24lambda_u240$iv$iv8 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv1118, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv1118, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1117 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                    $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash1117);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv1115, ComposeUiNode.Companion.getSetModifier());
                    int i2114 = ($changed$iv$iv$iv1117 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance13 = ColumnScopeInstance.INSTANCE;
                    int i2115 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, 504703351, "C1715@78737L524,1723@79302L810:InnerShell.kt#m1syn");
                    String strFriendlyModel7 = ComposerChipPolicy.INSTANCE.friendlyModel(choice.getModel());
                    if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(-1230641033);
                        ComposerKt.sourceInformation($composer$iv, "1717@78911L10");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraText($composer$iv, 0);
                    } else {
                        $composer$iv.startReplaceGroup(-1230640516);
                        ComposerKt.sourceInformation($composer$iv, "1717@78927L15");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                    }
                    $composer$iv.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyModel7, (Modifier) null, auroraTextMuted, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199680, 3120, 120786);
                    role = choice.getRole();
                    switch (role.hashCode()) {
                        case -911832760:
                            if (role.equals("Delegation")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Delegation · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            }
                            break;
                        case 825887490:
                            if (role.equals("Fallback")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Fallback · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            }
                            break;
                        case 1002405936:
                            if (role.equals("Unavailable")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Unavailable saved pair";
                            }
                            break;
                        case 1349887458:
                            if (role.equals("Primary")) {
                                strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            } else {
                                strFriendlyProvider = "Default · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            }
                            break;
                        default:
                            strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                            break;
                    }
                    if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(-1230611332);
                        ComposerKt.sourceInformation($composer$iv, "1731@79839L15");
                        auroraError = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                    } else {
                        $composer$iv.startReplaceGroup(-1230610664);
                        ComposerKt.sourceInformation($composer$iv, "1731@79860L11");
                        auroraError = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                    }
                    $composer$iv.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyProvider, (Modifier) null, auroraError, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3072, 3120, 120818);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    if (selected) {
                        $composer$iv.startReplaceGroup(-49702183);
                        ComposerKt.sourceInformation($composer$iv, "1741@80442L12,1738@80243L331");
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Selected", ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(22)), AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0), $composer$iv, 432, 0);
                        $composer$iv.endReplaceGroup();
                    } else if (choice.getPersistable()) {
                        $composer$iv.startReplaceGroup(-49250699);
                        ComposerKt.sourceInformation($composer$iv, "1747@80809L11,1753@81243L11,1745@80685L751");
                        long auroraError112 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        GenericFontFamily monospace7 = FontFamily.Companion.getMonospace();
                        long sp7 = TextUnitKt.getSp(10);
                        FontWeight bold7 = FontWeight.Companion.getBold();
                        Modifier modifierClip17 = ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8)));
                        float fM7016constructorimpl110 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                        long auroraError113 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("UNAVAILABLE", ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierClip17, fM7016constructorimpl110, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError113, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError113) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError113) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError113) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError113) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8))), ai.nous.aurora.compat.Compat.m7016constructorimpl(8), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), auroraError112, sp7, (FontStyle) null, bold7, (FontFamily) monospace7, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199686, 0, 130960);
                        $composer$iv.endReplaceGroup();
                    } else {
                        $composer$iv.startReplaceGroup(-48452108);
                        $composer$iv.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    if (index$iv < CollectionsKt.getLastIndex(activeChoices)) {
                        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                    }
                    $composer$iv.endMovableGroup();
                    str5 = str11111;
                    localMap$iv$iv = localMap$iv$iv1116;
                    compositeKeyHash$iv$iv6 = compositeKeyHash$iv$iv14;
                    index$iv = index$iv5;
                    $this$forEachIndexed$iv = $this$forEachIndexed$iv5;
                    $i$f$forEachIndexed = $i$f$forEachIndexed5;
                    it2 = it8;
                    $this$invoke_u24lambda_u2423_u24lambda_u2422 = $this$invoke_u24lambda_u2423_u24lambda_u2426;
                    str = str11110;
                    $composer2 = $composer7;
                    $composer$iv = $composer$iv5;
                    str9 = str8;
                    str4 = str11112;
                    str6 = str11113;
                    str7 = str11114;
                } else {
                    str8 = str9;
                }
                value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return InnerShellKt$ModelPickerSheet$1$2.invoke$lambda$23$lambda$22$lambda$21$lambda$18$lambda$17(function17, choice);
                    }
                };
                $composer$iv.updateRememberedValue(value$iv5);
                $composer$iv.endReplaceGroup();
                Modifier modifier$iv1118 = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierM600borderxT4_qwU9, persistable4, null, null, (Function0) value$iv5, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
                Alignment.Vertical verticalAlignment$iv18 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv18 = Arrangement.INSTANCE.getStart();
                function4 = function17;
                MeasurePolicy measurePolicy$iv1119 = RowKt.rowMeasurePolicy(horizontalArrangement$iv18, verticalAlignment$iv18, $composer$iv, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv1118 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                CompositionLocalMap localMap$iv$iv1119 = $composer$iv.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1116 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv1118);
                constructor7 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1118 = (($changed$iv$iv1118 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                if (!($composer$iv.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer$iv.startReusableNode();
                if ($composer$iv.getInserting()) {
                    function9 = constructor7;
                    $composer$iv.createNode(function9);
                } else {
                    function9 = constructor7;
                    $composer$iv.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv7 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv1119, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv1119, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1118 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash1118);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv1116, ComposeUiNode.Companion.getSetModifier());
                int i111112 = ($changed$iv$iv$iv1118 >> 6) & 14;
                String str11115 = str5;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -407840262, str11115);
                int i111113 = ((384 >> 6) & 112) | 6;
                RowScope $this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2427 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -51160205, "C1714@78656L1494:InnerShell.kt#m1syn");
                Modifier modifier$iv1119 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$invoke_u24lambda_u2423_u24lambda_u2422_u24lambda_u2421_u24lambda_u2427, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer$iv, -483455358, str4);
                Arrangement.Vertical verticalArrangement$iv17 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv17 = Alignment.Companion.getStart();
                String str11116 = str4;
                MeasurePolicy measurePolicy$iv11110 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv17, horizontalAlignment$iv17, $composer$iv, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv1119 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str6);
                compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                CompositionLocalMap localMap$iv$iv11110 = $composer$iv.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1117 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv1119);
                constructor8 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1119 = (($changed$iv$iv1119 << 6) & 896) | 6;
                String str11117 = str6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str7);
                if (!($composer$iv.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer$iv.startReusableNode();
                if ($composer$iv.getInserting()) {
                    function10 = constructor8;
                    $composer$iv.createNode(function10);
                } else {
                    function10 = constructor8;
                    $composer$iv.useNode();
                }
                String str11118 = str7;
                $this$Layout_u24lambda_u240$iv$iv8 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer$iv);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv11110, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv11110, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1119 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash1119);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv1117, ComposeUiNode.Companion.getSetModifier());
                int i2116 = ($changed$iv$iv$iv1119 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance14 = ColumnScopeInstance.INSTANCE;
                int i2117 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, 504703351, "C1715@78737L524,1723@79302L810:InnerShell.kt#m1syn");
                String strFriendlyModel8 = ComposerChipPolicy.INSTANCE.friendlyModel(choice.getModel());
                if (choice.getPersistable()) {
                    $composer$iv.startReplaceGroup(-1230641033);
                    ComposerKt.sourceInformation($composer$iv, "1717@78911L10");
                    auroraTextMuted = AuroraColorSchemeKt.getAuroraText($composer$iv, 0);
                } else {
                    $composer$iv.startReplaceGroup(-1230640516);
                    ComposerKt.sourceInformation($composer$iv, "1717@78927L15");
                    auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                }
                $composer$iv.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyModel8, (Modifier) null, auroraTextMuted, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199680, 3120, 120786);
                role = choice.getRole();
                switch (role.hashCode()) {
                    case -911832760:
                        if (role.equals("Delegation")) {
                            strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                        } else {
                            strFriendlyProvider = "Delegation · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                        }
                        break;
                    case 825887490:
                        if (role.equals("Fallback")) {
                            strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                        } else {
                            strFriendlyProvider = "Fallback · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                        }
                        break;
                    case 1002405936:
                        if (role.equals("Unavailable")) {
                            strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                        } else {
                            strFriendlyProvider = "Unavailable saved pair";
                        }
                        break;
                    case 1349887458:
                        if (role.equals("Primary")) {
                            strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                        } else {
                            strFriendlyProvider = "Default · " + ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                        }
                        break;
                    default:
                        strFriendlyProvider = ComposerChipPolicy.INSTANCE.friendlyProvider(choice.getProvider());
                        break;
                }
                if (choice.getPersistable()) {
                    $composer$iv.startReplaceGroup(-1230611332);
                    ComposerKt.sourceInformation($composer$iv, "1731@79839L15");
                    auroraError = AuroraColorSchemeKt.getAuroraTextMuted($composer$iv, 0);
                } else {
                    $composer$iv.startReplaceGroup(-1230610664);
                    ComposerKt.sourceInformation($composer$iv, "1731@79860L11");
                    auroraError = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                }
                $composer$iv.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(strFriendlyProvider, (Modifier) null, auroraError, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 3072, 3120, 120818);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (selected) {
                    $composer$iv.startReplaceGroup(-49702183);
                    ComposerKt.sourceInformation($composer$iv, "1741@80442L12,1738@80243L331");
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Selected", ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(22)), AuroraColorSchemeKt.getAuroraAccent($composer$iv, 0), $composer$iv, 432, 0);
                    $composer$iv.endReplaceGroup();
                } else if (choice.getPersistable()) {
                    $composer$iv.startReplaceGroup(-49250699);
                    ComposerKt.sourceInformation($composer$iv, "1747@80809L11,1753@81243L11,1745@80685L751");
                    long auroraError114 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                    GenericFontFamily monospace8 = FontFamily.Companion.getMonospace();
                    long sp8 = TextUnitKt.getSp(10);
                    FontWeight bold8 = FontWeight.Companion.getBold();
                    Modifier modifierClip18 = ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8)));
                    float fM7016constructorimpl111 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                    long auroraError115 = AuroraColorSchemeKt.getAuroraError($composer$iv, 0);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("UNAVAILABLE", ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierClip18, fM7016constructorimpl111, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError115, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError115) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError115) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError115) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError115) : 0.0f), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(8))), ai.nous.aurora.compat.Compat.m7016constructorimpl(8), ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), auroraError114, sp8, (FontStyle) null, bold8, (FontFamily) monospace8, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer$iv, 199686, 0, 130960);
                    $composer$iv.endReplaceGroup();
                } else {
                    $composer$iv.startReplaceGroup(-48452108);
                    $composer$iv.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (index$iv < CollectionsKt.getLastIndex(activeChoices)) {
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
                }
                $composer$iv.endMovableGroup();
                str5 = str11115;
                localMap$iv$iv = localMap$iv$iv1116;
                compositeKeyHash$iv$iv6 = compositeKeyHash$iv$iv14;
                index$iv = index$iv5;
                $this$forEachIndexed$iv = $this$forEachIndexed$iv5;
                $i$f$forEachIndexed = $i$f$forEachIndexed5;
                it2 = it8;
                $this$invoke_u24lambda_u2423_u24lambda_u2422 = $this$invoke_u24lambda_u2423_u24lambda_u2426;
                str = str11110;
                $composer2 = $composer7;
                $composer$iv = $composer$iv5;
                str9 = str8;
                str4 = str11116;
                str6 = str11117;
                str7 = str11118;
            }
            $composer3 = $composer2;
            $composer$iv.endReplaceGroup();
            SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer$iv, 6);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceGroup();
        }
        ComposerKt.sourceInformationMarkerEnd($composer3);
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
    public static final Unit invoke$lambda$23$lambda$14$lambda$7$lambda$6(MutableState $providerMenuOpen$delegate) {
        InnerShellKt.ModelPickerSheet$lambda$88($providerMenuOpen$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$23$lambda$14$lambda$9$lambda$8(String $activeProviderLabel, SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        SemanticsPropertiesKt.setContentDescription(semantics, "Provider " + $activeProviderLabel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$23$lambda$14$lambda$13$lambda$12(MutableState $providerMenuOpen$delegate) {
        InnerShellKt.ModelPickerSheet$lambda$88($providerMenuOpen$delegate, false);
        return Unit.INSTANCE;
    }

    private static final boolean invoke$lambda$23$lambda$22$isSelected(String $selectedModel, String $selectedProvider, MainViewModel.ModelChoice choice) {
        return choice.getPersistable() && Intrinsics.areEqual(choice.getModel(), $selectedModel) && Intrinsics.areEqual(choice.getProvider(), $selectedProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$23$lambda$22$lambda$21$lambda$18$lambda$17(Function1 $onSelect, MainViewModel.ModelChoice $choice) {
        $onSelect.invoke($choice);
        return Unit.INSTANCE;
    }
}
