package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.presentation.assist.AssistOrbKt;
import ai.nous.aurora.presentation.assist.AssistOrbPhase;
import ai.nous.aurora.presentation.components.ds.AuroraDsKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import android.content.Context;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
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
import androidx.profileinstaller.ProfileVerifier;
import java.util.Iterator;
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

/* JADX INFO: compiled from: AssistantSettingsPane.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001ay\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\r2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\r2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0002\u0010\u0013\u001a+\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bH\u0003¢\u0006\u0002\u0010\u0018\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"ORB_CHOICES", "", "Lai/nous/aurora/presentation/settings/OrbChoice;", "AssistantSettingsPane", "", "assist", "Lai/nous/aurora/presentation/settings/AssistSettingsUi;", "sttProvider", "", "ttsProvider", "onBack", "Lkotlin/Function0;", "onSpeakReplies", "Lkotlin/Function1;", "", "onAutoListen", "onOrbStyle", "modifier", "Landroidx/compose/ui/Modifier;", "(Lai/nous/aurora/presentation/settings/AssistSettingsUi;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "AssistActionRow", "title", "subtitle", "onClick", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AssistantSettingsPaneKt {
    static final List<OrbChoice> ORB_CHOICES = CollectionsKt.listOf(new OrbChoice[]{new OrbChoice("amber", "Amber", ColorKt.Color(4293441645L), null), new OrbChoice("aurora", "Aurora", ColorKt.Color(4291077492L), null), new OrbChoice("cobalt", "Cobalt", ColorKt.Color(4285442303L), null), new OrbChoice("signal", "Signal", ColorKt.Color(4284336805L), null)});

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistActionRow$lambda$11(String str, String str2, Function0 function0, int i, Composer composer, int i2) {
        AssistActionRow(str, str2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistantSettingsPane$lambda$9(AssistSettingsUi assistSettingsUi, String str, String str2, Function0 function0, Function1 function1, Function1 function2, Function1 function3, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AssistantSettingsPane(assistSettingsUi, str, str2, function0, function1, function2, function3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x017f A[LOOP:0: B:96:0x015c->B:101:0x017f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:105:0x018b  */
    /* JADX WARN: Code duplicated, block: B:108:0x021d  */
    /* JADX WARN: Code duplicated, block: B:111:0x0229  */
    /* JADX WARN: Code duplicated, block: B:112:0x022f  */
    /* JADX WARN: Code duplicated, block: B:115:0x0262  */
    /* JADX WARN: Code duplicated, block: B:118:0x0275  */
    /* JADX WARN: Code duplicated, block: B:119:0x0278  */
    /* JADX WARN: Code duplicated, block: B:123:0x034a  */
    /* JADX WARN: Code duplicated, block: B:126:0x0356  */
    /* JADX WARN: Code duplicated, block: B:127:0x035a  */
    /* JADX WARN: Code duplicated, block: B:130:0x0389  */
    /* JADX WARN: Code duplicated, block: B:133:0x039c  */
    /* JADX WARN: Code duplicated, block: B:134:0x039f  */
    /* JADX WARN: Code duplicated, block: B:138:0x0478  */
    /* JADX WARN: Code duplicated, block: B:139:0x0485  */
    /* JADX WARN: Code duplicated, block: B:142:0x050e  */
    /* JADX WARN: Code duplicated, block: B:145:0x051a  */
    /* JADX WARN: Code duplicated, block: B:146:0x0520  */
    /* JADX WARN: Code duplicated, block: B:149:0x0551  */
    /* JADX WARN: Code duplicated, block: B:153:0x0567 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:157:0x0625  */
    /* JADX WARN: Code duplicated, block: B:160:0x0631  */
    /* JADX WARN: Code duplicated, block: B:161:0x0637  */
    /* JADX WARN: Code duplicated, block: B:164:0x0668  */
    /* JADX WARN: Code duplicated, block: B:168:0x067e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:172:0x07c6  */
    /* JADX WARN: Code duplicated, block: B:173:0x07c8  */
    /* JADX WARN: Code duplicated, block: B:176:0x07d1  */
    /* JADX WARN: Code duplicated, block: B:177:0x07d3  */
    /* JADX WARN: Code duplicated, block: B:180:0x07dc  */
    /* JADX WARN: Code duplicated, block: B:181:0x07de  */
    /* JADX WARN: Code duplicated, block: B:184:0x07e8  */
    /* JADX WARN: Code duplicated, block: B:185:0x07ea  */
    /* JADX WARN: Code duplicated, block: B:188:0x07f9  */
    /* JADX WARN: Code duplicated, block: B:189:0x07fb  */
    /* JADX WARN: Code duplicated, block: B:192:0x0803  */
    /* JADX WARN: Code duplicated, block: B:195:0x0812  */
    /* JADX WARN: Code duplicated, block: B:199:0x0825  */
    /* JADX WARN: Code duplicated, block: B:202:0x0886  */
    /* JADX WARN: Code duplicated, block: B:205:0x088f  */
    /* JADX WARN: Code duplicated, block: B:206:0x08b1  */
    /* JADX WARN: Code duplicated, block: B:208:0x0184 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:209:0x0187 A[EDGE_INSN: B:209:0x0187->B:103:0x0187 BREAK  A[LOOP:0: B:96:0x015c->B:101:0x017f], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:66:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:68:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:70:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:71:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:75:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:77:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:79:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:80:0x0100  */
    /* JADX WARN: Code duplicated, block: B:82:0x0105  */
    /* JADX WARN: Code duplicated, block: B:85:0x0111  */
    /* JADX WARN: Code duplicated, block: B:89:0x0122 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:90:0x0124  */
    /* JADX WARN: Code duplicated, block: B:91:0x012a  */
    /* JADX WARN: Code duplicated, block: B:94:0x0131  */
    /* JADX WARN: Code duplicated, block: B:98:0x0164  */
    public static final void AssistantSettingsPane(final AssistSettingsUi assist, final String sttProvider, final String ttsProvider, final Function0<Unit> onBack, final Function1<? super Boolean, Unit> onSpeakReplies, final Function1<? super Boolean, Unit> onAutoListen, final Function1<? super String, Unit> onOrbStyle, Modifier modifier, Composer $composer, final int $changed, final int i) {
        int i2;
        int i3;
        int i4;
        Modifier modifier2;
        int i5;
        Modifier modifier3;
        int $dirty;
        Iterable $this$firstOrNull$iv;
        int $i$f$firstOrNull;
        Iterator it;
        int $i$f$firstOrNull2;
        Object element$iv;
        OrbChoice activeOrb;
        MeasurePolicy measurePolicy$iv;
        int compositeKeyHash$iv$iv;
        CompositionLocalMap localMap$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        MeasurePolicy measurePolicy$iv2;
        CompositionLocalMap localMap$iv$iv2;
        MeasurePolicy measurePolicy$iv3;
        int compositeKeyHash$iv$iv2;
        CompositionLocalMap localMap$iv$iv3;
        Function0<ComposeUiNode> constructor2;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        MeasurePolicy measurePolicy$iv4;
        CompositionLocalMap localMap$iv$iv4;
        Modifier modifier4;
        Composer $composer2;
        Object it$iv;
        Object value$iv;
        int compositeKeyHash$iv$iv3;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        int compositeKeyHash$iv$iv4;
        Function0<ComposeUiNode> constructor4;
        Function0<ComposeUiNode> function2;
        Composer $this$Layout_u24lambda_u240$iv$iv4;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean invalid$iv;
        Object value$iv2;
        OrbChoice it2;
        Iterable $this$firstOrNull$iv2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(assist, "assist");
        Intrinsics.checkNotNullParameter(sttProvider, "sttProvider");
        Intrinsics.checkNotNullParameter(ttsProvider, "ttsProvider");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Intrinsics.checkNotNullParameter(onSpeakReplies, "onSpeakReplies");
        Intrinsics.checkNotNullParameter(onAutoListen, "onAutoListen");
        Intrinsics.checkNotNullParameter(onOrbStyle, "onOrbStyle");
        Composer $composer3 = $composer.startRestartGroup(1472870728);
        ComposerKt.sourceInformation($composer3, "C(AssistantSettingsPane)P(!1,6,7,3,5,2,4)79@3395L7,85@3591L16,82@3504L7371:AssistantSettingsPane.kt#syfnu6");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(assist) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(sttProvider) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(ttsProvider) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changedInstance(onBack) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(onSpeakReplies) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if ((196608 & $changed) == 0) {
                i2 = $composer3.changedInstance(onAutoListen) ? 131072 : 65536;
            }
            if ((i & 64) != 0) {
                if ((1572864 & $changed) == 0) {
                    if ($composer3.changedInstance(onOrbStyle)) {
                        i3 = 1048576;
                    } else {
                        i3 = 524288;
                    }
                }
                i4 = i & 128;
                if (i4 != 0) {
                    $dirty2 |= 12582912;
                    modifier2 = modifier;
                } else if ((12582912 & $changed) == 0) {
                    modifier2 = modifier;
                    if ($composer3.changed(modifier2)) {
                        i5 = 8388608;
                    } else {
                        i5 = 4194304;
                    }
                    $dirty2 |= i5;
                } else {
                    modifier2 = modifier;
                }
                if (($dirty2 & 4793491) == 4793490 || !$composer3.getSkipping()) {
                    if (i4 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1472870728, $dirty2, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane (AssistantSettingsPane.kt:78)");
                    }
                    ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                    $dirty = $dirty2;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localContext);
                    final Context contextC1 = (Context) objConsume;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $this$firstOrNull$iv = ORB_CHOICES;
                    $i$f$firstOrNull = 0;
                    it = $this$firstOrNull$iv.iterator();
                    while (true) {
                        $i$f$firstOrNull2 = $i$f$firstOrNull;
                        if (!it.hasNext()) {
                            element$iv = null;
                            break;
                        }
                        element$iv = it.next();
                        it2 = (OrbChoice) element$iv;
                        $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                        if (Intrinsics.areEqual(it2.getId(), assist.getOrbStyle())) {
                            break;
                        }
                        $i$f$firstOrNull = $i$f$firstOrNull2;
                        $this$firstOrNull$iv = $this$firstOrNull$iv2;
                    }
                    activeOrb = (OrbChoice) element$iv;
                    if (activeOrb == null) {
                        activeOrb = (OrbChoice) CollectionsKt.first((List) ORB_CHOICES);
                    }
                        final OrbChoice activeOrb2C1 = activeOrb;
                    Modifier modifier$iv = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(modifier3, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraBackground($composer3, 0), null, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
                    measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    Modifier modifier5 = modifier3;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    constructor = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function0 = constructor;
                        $composer3.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        measurePolicy$iv2 = measurePolicy$iv;
                        localMap$iv$iv2 = localMap$iv$iv;
                    } else {
                        measurePolicy$iv2 = measurePolicy$iv;
                        localMap$iv$iv2 = localMap$iv$iv;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                        int i6 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        int i7 = ((0 >> 6) & 112) | 6;
                        ColumnScope $this$AssistantSettingsPane_u24lambda_u248 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                        Modifier modifier$iv2 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                        Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv2 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                        constructor2 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            $composer3.createNode(constructor2);
                        } else {
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                            measurePolicy$iv4 = measurePolicy$iv3;
                            localMap$iv$iv4 = localMap$iv$iv3;
                        } else {
                            measurePolicy$iv4 = measurePolicy$iv3;
                            localMap$iv$iv4 = localMap$iv$iv3;
                            if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            }
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                            int i8 = ($changed$iv$iv$iv2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i9 = ((390 >> 6) & 112) | 6;
                            RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u245 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                            ImageVector arrowBack = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                            long auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                            modifier4 = modifier5;
                            $composer2 = $composer3;
                            Modifier modifierM1034padding3ABfNKs = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                            $composer3.startReplaceGroup(-218490998);
                            ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                            it$iv = $composer3.rememberedValue();
                            if (it$iv == Composer.Companion.getEmpty()) {
                                value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv);
                            } else {
                                value$iv = it$iv;
                            }
                            $composer3.endReplaceGroup();
                            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs, false, (Function1) value$iv, 1, null), auroraAmber, $composer3, 48, 0);
                            Modifier modifier$iv3 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u245, Modifier.Companion, 1.0f, false, 2, null);
                            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.Companion.getTopStart();
                            MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv3 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                            constructor3 = ComposeUiNode.Companion.getConstructor();
                            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                function1 = constructor3;
                                $composer3.createNode(function1);
                            } else {
                                function1 = constructor3;
                                $composer3.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv5, ComposeUiNode.Companion.getSetMeasurePolicy());
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                            }
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                            int i10 = ($changed$iv$iv$iv3 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i11 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Modifier modifier$iv4 = Modifier.Companion;
                            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
                            MeasurePolicy measurePolicy$iv6 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv4 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv6 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
                            constructor4 = ComposeUiNode.Companion.getConstructor();
                            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                function2 = constructor4;
                                $composer3.createNode(function2);
                            } else {
                                function2 = constructor4;
                                $composer3.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv6, ComposeUiNode.Companion.getSetMeasurePolicy());
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                            }
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.Companion.getSetModifier());
                            int i12 = ($changed$iv$iv$iv4 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            int i13 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Modifier modifierFillMaxWidth$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u248, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                            PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                            $composer3.startReplaceGroup(-1637032986);
                            ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                            boolean zChanged = $composer2.changed(activeOrb2C1);
                            if (($dirty & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            boolean z6 = zChanged | z;
                            if ((3670016 & $dirty) == 1048576) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            boolean z7 = z6 | z2;
                            if ((458752 & $dirty) == 131072) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            boolean z8 = z7 | z3;
                            if ((57344 & $dirty) == 16384) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            boolean zChangedInstance = z8 | z4 | $composer2.changedInstance(contextC1);
                            if (($dirty & 112) == 32) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            invalid$iv = (($dirty & 896) == 256) | zChangedInstance | z5;
                            value$iv2 = $composer3.rememberedValue();
                            if (invalid$iv || value$iv2 == Composer.Companion.getEmpty()) {
                                value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C1, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC1, sttProvider, ttsProvider, (LazyListScope) obj);
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv2);
                            }
                            $composer3.endReplaceGroup();
                            LazyDslKt.LazyColumn(modifierFillMaxWidth$default, null, paddingValuesM1031PaddingValuesa9UjIt4$default, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                        int i14 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i15 = ((390 >> 6) & 112) | 6;
                        RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u246 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                        ImageVector arrowBack2 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                        long auroraAmber2 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                        modifier4 = modifier5;
                        $composer2 = $composer3;
                        Modifier modifierM1034padding3ABfNKs2 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                        $composer3.startReplaceGroup(-218490998);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        it$iv = $composer3.rememberedValue();
                        if (it$iv == Composer.Companion.getEmpty()) {
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv;
                        }
                        $composer3.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack2, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs2, false, (Function1) value$iv, 1, null), auroraAmber2, $composer3, 48, 0);
                        Modifier modifier$iv5 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u246, Modifier.Companion, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv2 = Alignment.Companion.getTopStart();
                        MeasurePolicy measurePolicy$iv7 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                        int $changed$iv$iv5 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv7 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer3, modifier$iv5);
                        constructor3 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function1 = constructor3;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor3;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv7, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv7, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash5);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv5, ComposeUiNode.Companion.getSetModifier());
                        int i16 = ($changed$iv$iv$iv5 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        int i17 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv6 = Modifier.Companion;
                        Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv3 = Alignment.Companion.getStart();
                        MeasurePolicy measurePolicy$iv8 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv6 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv8 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer3, modifier$iv6);
                        constructor4 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function2 = constructor4;
                            $composer3.createNode(function2);
                        } else {
                            function2 = constructor4;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv8, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv8, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash6);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv6, ComposeUiNode.Companion.getSetModifier());
                        int i18 = ($changed$iv$iv$iv6 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                        int i19 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifierFillMaxWidth$default2 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u248, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                        PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default2 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                        $composer3.startReplaceGroup(-1637032986);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        boolean zChanged2 = $composer2.changed(activeOrb2C1);
                        if (($dirty & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean z9 = zChanged2 | z;
                        if ((3670016 & $dirty) == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        boolean z10 = z9 | z2;
                        if ((458752 & $dirty) == 131072) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z11 = z10 | z3;
                        if ((57344 & $dirty) == 16384) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean zChangedInstance2 = z11 | z4 | $composer2.changedInstance(contextC1);
                        if (($dirty & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        invalid$iv = (($dirty & 896) == 256) | zChangedInstance2 | z5;
                        value$iv2 = $composer3.rememberedValue();
                        if (invalid$iv) {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C1, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC1, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C1, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC1, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        }
                        $composer3.endReplaceGroup();
                        LazyDslKt.LazyColumn(modifierFillMaxWidth$default2, null, paddingValuesM1031PaddingValuesa9UjIt4$default2, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                    int i20 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    int i21 = ((0 >> 6) & 112) | 6;
                    ColumnScope $this$AssistantSettingsPane_u24lambda_u249 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                    Modifier modifier$iv7 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                    Alignment.Vertical verticalAlignment$iv2 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
                    measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv7 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer3, modifier$iv7);
                    constructor2 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv7 = (($changed$iv$iv7 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor2);
                    } else {
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv7, ComposeUiNode.Companion.getSetModifier());
                        int i110 = ($changed$iv$iv$iv7 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i111 = ((390 >> 6) & 112) | 6;
                        RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u247 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                        ImageVector arrowBack3 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                        long auroraAmber3 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                        modifier4 = modifier5;
                        $composer2 = $composer3;
                        Modifier modifierM1034padding3ABfNKs3 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                        $composer3.startReplaceGroup(-218490998);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        it$iv = $composer3.rememberedValue();
                        if (it$iv == Composer.Companion.getEmpty()) {
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv;
                        }
                        $composer3.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack3, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs3, false, (Function1) value$iv, 1, null), auroraAmber3, $composer3, 48, 0);
                        Modifier modifier$iv8 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u247, Modifier.Companion, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv3 = Alignment.Companion.getTopStart();
                        MeasurePolicy measurePolicy$iv9 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                        int $changed$iv$iv8 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv9 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer3, modifier$iv8);
                        constructor3 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function1 = constructor3;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor3;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv9, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv9, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash8);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv8, ComposeUiNode.Companion.getSetModifier());
                        int i112 = ($changed$iv$iv$iv8 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                        int i113 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv9 = Modifier.Companion;
                        Arrangement.Vertical verticalArrangement$iv4 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv4 = Alignment.Companion.getStart();
                        MeasurePolicy measurePolicy$iv10 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv4, horizontalAlignment$iv4, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv9 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv10 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv9 = ComposedModifierKt.materializeModifier($composer3, modifier$iv9);
                        constructor4 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv9 = (($changed$iv$iv9 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function2 = constructor4;
                            $composer3.createNode(function2);
                        } else {
                            function2 = constructor4;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv10, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv10, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash9);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv9, ComposeUiNode.Companion.getSetModifier());
                        int i114 = ($changed$iv$iv$iv9 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                        int i115 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifierFillMaxWidth$default3 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u249, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                        PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default3 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                        $composer3.startReplaceGroup(-1637032986);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        boolean zChanged3 = $composer2.changed(activeOrb2C1);
                        if (($dirty & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean z12 = zChanged3 | z;
                        if ((3670016 & $dirty) == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        boolean z13 = z12 | z2;
                        if ((458752 & $dirty) == 131072) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z14 = z13 | z3;
                        if ((57344 & $dirty) == 16384) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean zChangedInstance3 = z14 | z4 | $composer2.changedInstance(contextC1);
                        if (($dirty & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        invalid$iv = (($dirty & 896) == 256) | zChangedInstance3 | z5;
                        value$iv2 = $composer3.rememberedValue();
                        if (invalid$iv) {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C1, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC1, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C1, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC1, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        }
                        $composer3.endReplaceGroup();
                        LazyDslKt.LazyColumn(modifierFillMaxWidth$default3, null, paddingValuesM1031PaddingValuesa9UjIt4$default3, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                    }
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash7);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv7, ComposeUiNode.Companion.getSetModifier());
                    int i116 = ($changed$iv$iv$iv7 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i117 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u248 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack4 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber4 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier5;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs4 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack4, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs4, false, (Function1) value$iv, 1, null), auroraAmber4, $composer3, 48, 0);
                    Modifier modifier$iv10 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u248, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv4 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv11 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                    int $changed$iv$iv10 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv11 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv10 = ComposedModifierKt.materializeModifier($composer3, modifier$iv10);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv10 = (($changed$iv$iv10 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv11, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv11, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash10);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv10, ComposeUiNode.Companion.getSetModifier());
                    int i118 = ($changed$iv$iv$iv10 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                    int i119 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv11 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv5 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv5 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv12 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv5, horizontalAlignment$iv5, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv11 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv12 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv11 = (($changed$iv$iv11 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv12, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv12, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash11);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv11, ComposeUiNode.Companion.getSetModifier());
                    int i1110 = ($changed$iv$iv$iv11 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                    int i1111 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default4 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u249, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default4 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged4 = $composer2.changed(activeOrb2C1);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z15 = zChanged4 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z16 = z15 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z17 = z16 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance4 = z17 | z4 | $composer2.changedInstance(contextC1);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance4 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C1, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC1, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C1, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC1, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default4, null, paddingValuesM1031PaddingValuesa9UjIt4$default4, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    modifier4 = modifier2;
                    $composer2 = $composer3;
                }
                scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier6 = modifier4;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$9(assist, sttProvider, ttsProvider, onBack, onSpeakReplies, onAutoListen, onOrbStyle, modifier6, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 = 1572864;
            $dirty2 |= i3;
            i4 = i & 128;
            if (i4 != 0) {
                $dirty2 |= 12582912;
                modifier2 = modifier;
            } else if ((12582912 & $changed) == 0) {
                modifier2 = modifier;
                if ($composer3.changed(modifier2)) {
                    i5 = 8388608;
                } else {
                    i5 = 4194304;
                }
                $dirty2 |= i5;
            } else {
                modifier2 = modifier;
            }
            if (($dirty2 & 4793491) == 4793490) {
                if (i4 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1472870728, $dirty2, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane (AssistantSettingsPane.kt:78)");
                }
                ProvidableCompositionLocal<Context> localContext2 = AndroidCompositionLocals_androidKt.getLocalContext();
                $dirty = $dirty2;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localContext2);
                final Context contextC2 = (Context) objConsume2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $this$firstOrNull$iv = ORB_CHOICES;
                $i$f$firstOrNull = 0;
                it = $this$firstOrNull$iv.iterator();
                while (true) {
                    $i$f$firstOrNull2 = $i$f$firstOrNull;
                    if (!it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    it2 = (OrbChoice) element$iv;
                    $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                    if (Intrinsics.areEqual(it2.getId(), assist.getOrbStyle())) {
                        break;
                    } else {
                        $i$f$firstOrNull = $i$f$firstOrNull2;
                        $this$firstOrNull$iv = $this$firstOrNull$iv2;
                    }
                }
                activeOrb = (OrbChoice) element$iv;
                if (activeOrb == null) {
                    activeOrb = (OrbChoice) CollectionsKt.first((List) ORB_CHOICES);
                }
                    final OrbChoice activeOrb2C2 = activeOrb;
                Modifier modifier$iv12 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(modifier3, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraBackground($composer3, 0), null, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv6 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv6 = Alignment.Companion.getStart();
                measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv6, horizontalAlignment$iv6, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv12 = (0 << 3) & 112;
                Modifier modifier7 = modifier3;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv12 = ComposedModifierKt.materializeModifier($composer3, modifier$iv12);
                constructor = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv12 = (($changed$iv$iv12 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash12 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    measurePolicy$iv2 = measurePolicy$iv;
                    localMap$iv$iv2 = localMap$iv$iv;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv12, ComposeUiNode.Companion.getSetModifier());
                    int i22 = ($changed$iv$iv$iv12 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    int i23 = ((0 >> 6) & 112) | 6;
                    ColumnScope $this$AssistantSettingsPane_u24lambda_u2410 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                    Modifier modifier$iv13 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                    Alignment.Vertical verticalAlignment$iv3 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv3 = Arrangement.INSTANCE.getStart();
                    measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv3, verticalAlignment$iv3, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv13 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv13 = ComposedModifierKt.materializeModifier($composer3, modifier$iv13);
                    constructor2 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv13 = (($changed$iv$iv13 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor2);
                    } else {
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash13 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv13, ComposeUiNode.Companion.getSetModifier());
                        int i1112 = ($changed$iv$iv$iv13 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i1113 = ((390 >> 6) & 112) | 6;
                        RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u249 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                        ImageVector arrowBack5 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                        long auroraAmber5 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                        modifier4 = modifier7;
                        $composer2 = $composer3;
                        Modifier modifierM1034padding3ABfNKs5 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                        $composer3.startReplaceGroup(-218490998);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        it$iv = $composer3.rememberedValue();
                        if (it$iv == Composer.Companion.getEmpty()) {
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv;
                        }
                        $composer3.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack5, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs5, false, (Function1) value$iv, 1, null), auroraAmber5, $composer3, 48, 0);
                        Modifier modifier$iv14 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u249, Modifier.Companion, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv5 = Alignment.Companion.getTopStart();
                        MeasurePolicy measurePolicy$iv13 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
                        int $changed$iv$iv14 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv13 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv14 = ComposedModifierKt.materializeModifier($composer3, modifier$iv14);
                        constructor3 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv14 = (($changed$iv$iv14 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function1 = constructor3;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor3;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv13, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv13, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash14 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash14);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv14, ComposeUiNode.Companion.getSetModifier());
                        int i1114 = ($changed$iv$iv$iv14 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                        int i1115 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv15 = Modifier.Companion;
                        Arrangement.Vertical verticalArrangement$iv7 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv7 = Alignment.Companion.getStart();
                        MeasurePolicy measurePolicy$iv14 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv7, horizontalAlignment$iv7, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv15 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv14 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv15 = ComposedModifierKt.materializeModifier($composer3, modifier$iv15);
                        constructor4 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv15 = (($changed$iv$iv15 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function2 = constructor4;
                            $composer3.createNode(function2);
                        } else {
                            function2 = constructor4;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv14, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv14, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash15 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash15);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv15, ComposeUiNode.Companion.getSetModifier());
                        int i1116 = ($changed$iv$iv$iv15 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                        int i1117 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifierFillMaxWidth$default5 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2410, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                        PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default5 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                        $composer3.startReplaceGroup(-1637032986);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        boolean zChanged5 = $composer2.changed(activeOrb2C2);
                        if (($dirty & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean z18 = zChanged5 | z;
                        if ((3670016 & $dirty) == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        boolean z19 = z18 | z2;
                        if ((458752 & $dirty) == 131072) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z110 = z19 | z3;
                        if ((57344 & $dirty) == 16384) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean zChangedInstance5 = z110 | z4 | $composer2.changedInstance(contextC2);
                        if (($dirty & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        invalid$iv = (($dirty & 896) == 256) | zChangedInstance5 | z5;
                        value$iv2 = $composer3.rememberedValue();
                        if (invalid$iv) {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C2, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC2, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C2, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC2, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        }
                        $composer3.endReplaceGroup();
                        LazyDslKt.LazyColumn(modifierFillMaxWidth$default5, null, paddingValuesM1031PaddingValuesa9UjIt4$default5, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                    }
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash13);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv13, ComposeUiNode.Companion.getSetModifier());
                    int i1118 = ($changed$iv$iv$iv13 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i1119 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2410 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack6 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber6 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier7;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs6 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack6, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs6, false, (Function1) value$iv, 1, null), auroraAmber6, $composer3, 48, 0);
                    Modifier modifier$iv16 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2410, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv6 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv15 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
                    int $changed$iv$iv16 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv15 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv16 = ComposedModifierKt.materializeModifier($composer3, modifier$iv16);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv16 = (($changed$iv$iv16 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv15, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv15, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash16 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash16);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv16, ComposeUiNode.Companion.getSetModifier());
                    int i11110 = ($changed$iv$iv$iv16 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                    int i11111 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv17 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv8 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv8 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv16 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv8, horizontalAlignment$iv8, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv17 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv16 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv17 = ComposedModifierKt.materializeModifier($composer3, modifier$iv17);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv17 = (($changed$iv$iv17 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv16, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv16, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash17 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash17);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv17, ComposeUiNode.Companion.getSetModifier());
                    int i11112 = ($changed$iv$iv$iv17 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
                    int i11113 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default6 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2410, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default6 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged6 = $composer2.changed(activeOrb2C2);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z111 = zChanged6 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z112 = z111 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z113 = z112 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance6 = z113 | z4 | $composer2.changedInstance(contextC2);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance6 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C2, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC2, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C2, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC2, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default6, null, paddingValuesM1031PaddingValuesa9UjIt4$default6, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv2 = measurePolicy$iv;
                    localMap$iv$iv2 = localMap$iv$iv;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash12);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv12, ComposeUiNode.Companion.getSetModifier());
                int i24 = ($changed$iv$iv$iv12 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                int i25 = ((0 >> 6) & 112) | 6;
                ColumnScope $this$AssistantSettingsPane_u24lambda_u2411 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                Modifier modifier$iv18 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                Alignment.Vertical verticalAlignment$iv4 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv4 = Arrangement.INSTANCE.getStart();
                measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv4, verticalAlignment$iv4, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv18 = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv18 = ComposedModifierKt.materializeModifier($composer3, modifier$iv18);
                constructor2 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv18 = (($changed$iv$iv18 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash18 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv18, ComposeUiNode.Companion.getSetModifier());
                    int i11114 = ($changed$iv$iv$iv18 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i11115 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2411 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack7 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber7 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier7;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs7 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack7, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs7, false, (Function1) value$iv, 1, null), auroraAmber7, $composer3, 48, 0);
                    Modifier modifier$iv19 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2411, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv7 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv17 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv7, false);
                    int $changed$iv$iv19 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv17 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv19 = ComposedModifierKt.materializeModifier($composer3, modifier$iv19);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv19 = (($changed$iv$iv19 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv17, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv17, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash19 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash19);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv19, ComposeUiNode.Companion.getSetModifier());
                    int i11116 = ($changed$iv$iv$iv19 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                    int i11117 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv110 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv9 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv9 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv18 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv9, horizontalAlignment$iv9, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv110 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv18 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv110 = ComposedModifierKt.materializeModifier($composer3, modifier$iv110);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv110 = (($changed$iv$iv110 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv18, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv18, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash110 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash110);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv110, ComposeUiNode.Companion.getSetModifier());
                    int i11118 = ($changed$iv$iv$iv110 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance7 = ColumnScopeInstance.INSTANCE;
                    int i11119 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default7 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2411, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default7 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged7 = $composer2.changed(activeOrb2C2);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z114 = zChanged7 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z115 = z114 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z116 = z115 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance7 = z116 | z4 | $composer2.changedInstance(contextC2);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance7 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C2, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC2, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C2, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC2, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default7, null, paddingValuesM1031PaddingValuesa9UjIt4$default7, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash18);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv18, ComposeUiNode.Companion.getSetModifier());
                int i111110 = ($changed$iv$iv$iv18 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i111111 = ((390 >> 6) & 112) | 6;
                RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2412 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                ImageVector arrowBack8 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                long auroraAmber8 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                modifier4 = modifier7;
                $composer2 = $composer3;
                Modifier modifierM1034padding3ABfNKs8 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                $composer3.startReplaceGroup(-218490998);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack8, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs8, false, (Function1) value$iv, 1, null), auroraAmber8, $composer3, 48, 0);
                Modifier modifier$iv111 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2412, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv8 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv19 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv8, false);
                int $changed$iv$iv111 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv19 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv111 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv111 = (($changed$iv$iv111 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor3;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv19, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv19, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash111);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv111, ComposeUiNode.Companion.getSetModifier());
                int i111112 = ($changed$iv$iv$iv111 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                int i111113 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv112 = Modifier.Companion;
                Arrangement.Vertical verticalArrangement$iv10 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv10 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv110 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv10, horizontalAlignment$iv10, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv112 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv110 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv112 = ComposedModifierKt.materializeModifier($composer3, modifier$iv112);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv112 = (($changed$iv$iv112 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor4;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv110, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv110, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash112 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash112);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv112, ComposeUiNode.Companion.getSetModifier());
                int i111114 = ($changed$iv$iv$iv112 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance8 = ColumnScopeInstance.INSTANCE;
                int i111115 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxWidth$default8 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2411, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default8 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                $composer3.startReplaceGroup(-1637032986);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                boolean zChanged8 = $composer2.changed(activeOrb2C2);
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z117 = zChanged8 | z;
                if ((3670016 & $dirty) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z118 = z117 | z2;
                if ((458752 & $dirty) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z119 = z118 | z3;
                if ((57344 & $dirty) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance8 = z119 | z4 | $composer2.changedInstance(contextC2);
                if (($dirty & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = (($dirty & 896) == 256) | zChangedInstance8 | z5;
                value$iv2 = $composer3.rememberedValue();
                if (invalid$iv) {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C2, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC2, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C2, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC2, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                $composer3.endReplaceGroup();
                LazyDslKt.LazyColumn(modifierFillMaxWidth$default8, null, paddingValuesM1031PaddingValuesa9UjIt4$default8, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (i4 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1472870728, $dirty2, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane (AssistantSettingsPane.kt:78)");
                }
                ProvidableCompositionLocal<Context> localContext3 = AndroidCompositionLocals_androidKt.getLocalContext();
                $dirty = $dirty2;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer3.consume(localContext3);
                final Context contextC3 = (Context) objConsume3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $this$firstOrNull$iv = ORB_CHOICES;
                $i$f$firstOrNull = 0;
                it = $this$firstOrNull$iv.iterator();
                while (true) {
                    $i$f$firstOrNull2 = $i$f$firstOrNull;
                    if (!it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    it2 = (OrbChoice) element$iv;
                    $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                    if (Intrinsics.areEqual(it2.getId(), assist.getOrbStyle())) {
                        break;
                    } else {
                        $i$f$firstOrNull = $i$f$firstOrNull2;
                        $this$firstOrNull$iv = $this$firstOrNull$iv2;
                    }
                }
                activeOrb = (OrbChoice) element$iv;
                if (activeOrb == null) {
                    activeOrb = (OrbChoice) CollectionsKt.first((List) ORB_CHOICES);
                }
                    final OrbChoice activeOrb2C3 = activeOrb;
                Modifier modifier$iv113 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(modifier3, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraBackground($composer3, 0), null, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv11 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv11 = Alignment.Companion.getStart();
                measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv11, horizontalAlignment$iv11, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv113 = (0 << 3) & 112;
                Modifier modifier8 = modifier3;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv113 = ComposedModifierKt.materializeModifier($composer3, modifier$iv113);
                constructor = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv113 = (($changed$iv$iv113 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash113 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    measurePolicy$iv2 = measurePolicy$iv;
                    localMap$iv$iv2 = localMap$iv$iv;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv113, ComposeUiNode.Companion.getSetModifier());
                    int i26 = ($changed$iv$iv$iv113 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    int i27 = ((0 >> 6) & 112) | 6;
                    ColumnScope $this$AssistantSettingsPane_u24lambda_u2412 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                    Modifier modifier$iv114 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                    Alignment.Vertical verticalAlignment$iv5 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv5 = Arrangement.INSTANCE.getStart();
                    measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv5, verticalAlignment$iv5, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv114 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv114 = ComposedModifierKt.materializeModifier($composer3, modifier$iv114);
                    constructor2 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv114 = (($changed$iv$iv114 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor2);
                    } else {
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash114 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv114, ComposeUiNode.Companion.getSetModifier());
                        int i111116 = ($changed$iv$iv$iv114 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i111117 = ((390 >> 6) & 112) | 6;
                        RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2413 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                        ImageVector arrowBack9 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                        long auroraAmber9 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                        modifier4 = modifier8;
                        $composer2 = $composer3;
                        Modifier modifierM1034padding3ABfNKs9 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                        $composer3.startReplaceGroup(-218490998);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        it$iv = $composer3.rememberedValue();
                        if (it$iv == Composer.Companion.getEmpty()) {
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv;
                        }
                        $composer3.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack9, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs9, false, (Function1) value$iv, 1, null), auroraAmber9, $composer3, 48, 0);
                        Modifier modifier$iv115 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2413, Modifier.Companion, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv9 = Alignment.Companion.getTopStart();
                        MeasurePolicy measurePolicy$iv111 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv9, false);
                        int $changed$iv$iv115 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv111 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv115 = ComposedModifierKt.materializeModifier($composer3, modifier$iv115);
                        constructor3 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv115 = (($changed$iv$iv115 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function1 = constructor3;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor3;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv111, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv111, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash115 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash115);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv115, ComposeUiNode.Companion.getSetModifier());
                        int i111118 = ($changed$iv$iv$iv115 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
                        int i111119 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv116 = Modifier.Companion;
                        Arrangement.Vertical verticalArrangement$iv12 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv12 = Alignment.Companion.getStart();
                        MeasurePolicy measurePolicy$iv112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv12, horizontalAlignment$iv12, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv116 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv112 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv116 = ComposedModifierKt.materializeModifier($composer3, modifier$iv116);
                        constructor4 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv116 = (($changed$iv$iv116 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function2 = constructor4;
                            $composer3.createNode(function2);
                        } else {
                            function2 = constructor4;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv112, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv112, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash116 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash116);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv116, ComposeUiNode.Companion.getSetModifier());
                        int i1111110 = ($changed$iv$iv$iv116 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance9 = ColumnScopeInstance.INSTANCE;
                        int i1111111 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifierFillMaxWidth$default9 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2412, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                        PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default9 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                        $composer3.startReplaceGroup(-1637032986);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        boolean zChanged9 = $composer2.changed(activeOrb2C3);
                        if (($dirty & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean z1110 = zChanged9 | z;
                        if ((3670016 & $dirty) == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        boolean z1111 = z1110 | z2;
                        if ((458752 & $dirty) == 131072) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z1112 = z1111 | z3;
                        if ((57344 & $dirty) == 16384) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean zChangedInstance9 = z1112 | z4 | $composer2.changedInstance(contextC3);
                        if (($dirty & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        invalid$iv = (($dirty & 896) == 256) | zChangedInstance9 | z5;
                        value$iv2 = $composer3.rememberedValue();
                        if (invalid$iv) {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C3, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC3, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C3, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC3, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        }
                        $composer3.endReplaceGroup();
                        LazyDslKt.LazyColumn(modifierFillMaxWidth$default9, null, paddingValuesM1031PaddingValuesa9UjIt4$default9, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                    }
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash114);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv114, ComposeUiNode.Companion.getSetModifier());
                    int i1111112 = ($changed$iv$iv$iv114 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i1111113 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2414 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack10 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber10 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier8;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs10 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack10, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs10, false, (Function1) value$iv, 1, null), auroraAmber10, $composer3, 48, 0);
                    Modifier modifier$iv117 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2414, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv10 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv113 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv10, false);
                    int $changed$iv$iv117 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv113 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv117 = ComposedModifierKt.materializeModifier($composer3, modifier$iv117);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv117 = (($changed$iv$iv117 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv113, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv113, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash117 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash117);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv117, ComposeUiNode.Companion.getSetModifier());
                    int i1111114 = ($changed$iv$iv$iv117 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance10 = BoxScopeInstance.INSTANCE;
                    int i1111115 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv118 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv13 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv13 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv114 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv13, horizontalAlignment$iv13, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv118 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv114 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv118 = ComposedModifierKt.materializeModifier($composer3, modifier$iv118);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv118 = (($changed$iv$iv118 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv114, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv114, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash118 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash118);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv118, ComposeUiNode.Companion.getSetModifier());
                    int i1111116 = ($changed$iv$iv$iv118 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance10 = ColumnScopeInstance.INSTANCE;
                    int i1111117 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default10 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2412, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default10 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged10 = $composer2.changed(activeOrb2C3);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z1113 = zChanged10 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z1114 = z1113 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z1115 = z1114 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance10 = z1115 | z4 | $composer2.changedInstance(contextC3);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance10 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C3, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC3, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C3, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC3, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default10, null, paddingValuesM1031PaddingValuesa9UjIt4$default10, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv2 = measurePolicy$iv;
                    localMap$iv$iv2 = localMap$iv$iv;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash113);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv113, ComposeUiNode.Companion.getSetModifier());
                int i28 = ($changed$iv$iv$iv113 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                int i29 = ((0 >> 6) & 112) | 6;
                ColumnScope $this$AssistantSettingsPane_u24lambda_u2413 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                Modifier modifier$iv119 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                Alignment.Vertical verticalAlignment$iv6 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv6 = Arrangement.INSTANCE.getStart();
                measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv6, verticalAlignment$iv6, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv119 = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv119 = ComposedModifierKt.materializeModifier($composer3, modifier$iv119);
                constructor2 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv119 = (($changed$iv$iv119 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash119 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv119, ComposeUiNode.Companion.getSetModifier());
                    int i1111118 = ($changed$iv$iv$iv119 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i1111119 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2415 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack11 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber11 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier8;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs11 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack11, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs11, false, (Function1) value$iv, 1, null), auroraAmber11, $composer3, 48, 0);
                    Modifier modifier$iv1110 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2415, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv11 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv115 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv11, false);
                    int $changed$iv$iv1110 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv115 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1110 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1110);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv1110 = (($changed$iv$iv1110 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv115, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv115, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1110 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash1110);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv1110, ComposeUiNode.Companion.getSetModifier());
                    int i11111110 = ($changed$iv$iv$iv1110 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance11 = BoxScopeInstance.INSTANCE;
                    int i11111111 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv1111 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv14 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv14 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv116 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv14, horizontalAlignment$iv14, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv1111 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv116 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv1111 = (($changed$iv$iv1111 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv116, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv116, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash1111);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv1111, ComposeUiNode.Companion.getSetModifier());
                    int i11111112 = ($changed$iv$iv$iv1111 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance11 = ColumnScopeInstance.INSTANCE;
                    int i11111113 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default11 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2413, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default11 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged11 = $composer2.changed(activeOrb2C3);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z1116 = zChanged11 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z1117 = z1116 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z1118 = z1117 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance11 = z1118 | z4 | $composer2.changedInstance(contextC3);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance11 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C3, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC3, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C3, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC3, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default11, null, paddingValuesM1031PaddingValuesa9UjIt4$default11, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash119);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv119, ComposeUiNode.Companion.getSetModifier());
                int i11111114 = ($changed$iv$iv$iv119 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i11111115 = ((390 >> 6) & 112) | 6;
                RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2416 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                ImageVector arrowBack12 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                long auroraAmber12 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                modifier4 = modifier8;
                $composer2 = $composer3;
                Modifier modifierM1034padding3ABfNKs12 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                $composer3.startReplaceGroup(-218490998);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack12, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs12, false, (Function1) value$iv, 1, null), auroraAmber12, $composer3, 48, 0);
                Modifier modifier$iv1112 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2416, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv12 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv117 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv12, false);
                int $changed$iv$iv1112 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv117 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1112 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1112);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1112 = (($changed$iv$iv1112 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor3;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv117, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv117, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1112 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash1112);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv1112, ComposeUiNode.Companion.getSetModifier());
                int i11111116 = ($changed$iv$iv$iv1112 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance12 = BoxScopeInstance.INSTANCE;
                int i11111117 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv1113 = Modifier.Companion;
                Arrangement.Vertical verticalArrangement$iv15 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv15 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv118 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv15, horizontalAlignment$iv15, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv1113 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv118 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1113 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1113);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1113 = (($changed$iv$iv1113 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor4;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv118, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv118, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1113 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash1113);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv1113, ComposeUiNode.Companion.getSetModifier());
                int i11111118 = ($changed$iv$iv$iv1113 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance12 = ColumnScopeInstance.INSTANCE;
                int i11111119 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxWidth$default12 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2413, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default12 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                $composer3.startReplaceGroup(-1637032986);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                boolean zChanged12 = $composer2.changed(activeOrb2C3);
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z1119 = zChanged12 | z;
                if ((3670016 & $dirty) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z11110 = z1119 | z2;
                if ((458752 & $dirty) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z11111 = z11110 | z3;
                if ((57344 & $dirty) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance12 = z11111 | z4 | $composer2.changedInstance(contextC3);
                if (($dirty & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = (($dirty & 896) == 256) | zChangedInstance12 | z5;
                value$iv2 = $composer3.rememberedValue();
                if (invalid$iv) {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C3, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC3, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C3, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC3, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                $composer3.endReplaceGroup();
                LazyDslKt.LazyColumn(modifierFillMaxWidth$default12, null, paddingValuesM1031PaddingValuesa9UjIt4$default12, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier9 = modifier4;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$9(assist, sttProvider, ttsProvider, onBack, onSpeakReplies, onAutoListen, onOrbStyle, modifier9, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        if ((i & 64) != 0) {
            if ((1572864 & $changed) == 0) {
                if ($composer3.changedInstance(onOrbStyle)) {
                    i3 = 1048576;
                } else {
                    i3 = 524288;
                }
            }
            i4 = i & 128;
            if (i4 != 0) {
                $dirty2 |= 12582912;
                modifier2 = modifier;
            } else if ((12582912 & $changed) == 0) {
                modifier2 = modifier;
                if ($composer3.changed(modifier2)) {
                    i5 = 8388608;
                } else {
                    i5 = 4194304;
                }
                $dirty2 |= i5;
            } else {
                modifier2 = modifier;
            }
            if (($dirty2 & 4793491) == 4793490) {
                if (i4 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1472870728, $dirty2, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane (AssistantSettingsPane.kt:78)");
                }
                ProvidableCompositionLocal<Context> localContext4 = AndroidCompositionLocals_androidKt.getLocalContext();
                $dirty = $dirty2;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer3.consume(localContext4);
                final Context contextC4 = (Context) objConsume4;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $this$firstOrNull$iv = ORB_CHOICES;
                $i$f$firstOrNull = 0;
                it = $this$firstOrNull$iv.iterator();
                while (true) {
                    $i$f$firstOrNull2 = $i$f$firstOrNull;
                    if (!it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    it2 = (OrbChoice) element$iv;
                    $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                    if (Intrinsics.areEqual(it2.getId(), assist.getOrbStyle())) {
                        break;
                    } else {
                        $i$f$firstOrNull = $i$f$firstOrNull2;
                        $this$firstOrNull$iv = $this$firstOrNull$iv2;
                    }
                }
                activeOrb = (OrbChoice) element$iv;
                if (activeOrb == null) {
                    activeOrb = (OrbChoice) CollectionsKt.first((List) ORB_CHOICES);
                }
                    final OrbChoice activeOrb2C4 = activeOrb;
                Modifier modifier$iv1114 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(modifier3, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraBackground($composer3, 0), null, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv16 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv16 = Alignment.Companion.getStart();
                measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv16, horizontalAlignment$iv16, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv1114 = (0 << 3) & 112;
                Modifier modifier10 = modifier3;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1114 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1114);
                constructor = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1114 = (($changed$iv$iv1114 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1114 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    measurePolicy$iv2 = measurePolicy$iv;
                    localMap$iv$iv2 = localMap$iv$iv;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv1114, ComposeUiNode.Companion.getSetModifier());
                    int i210 = ($changed$iv$iv$iv1114 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    int i211 = ((0 >> 6) & 112) | 6;
                    ColumnScope $this$AssistantSettingsPane_u24lambda_u2414 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                    Modifier modifier$iv1115 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                    Alignment.Vertical verticalAlignment$iv7 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv7 = Arrangement.INSTANCE.getStart();
                    measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv7, verticalAlignment$iv7, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv1115 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1115 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1115);
                    constructor2 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv1115 = (($changed$iv$iv1115 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor2);
                    } else {
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1115 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv1115, ComposeUiNode.Companion.getSetModifier());
                        int i111111110 = ($changed$iv$iv$iv1115 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i111111111 = ((390 >> 6) & 112) | 6;
                        RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2417 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                        ImageVector arrowBack13 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                        long auroraAmber13 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                        modifier4 = modifier10;
                        $composer2 = $composer3;
                        Modifier modifierM1034padding3ABfNKs13 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                        $composer3.startReplaceGroup(-218490998);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        it$iv = $composer3.rememberedValue();
                        if (it$iv == Composer.Companion.getEmpty()) {
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv;
                        }
                        $composer3.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack13, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs13, false, (Function1) value$iv, 1, null), auroraAmber13, $composer3, 48, 0);
                        Modifier modifier$iv1116 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2417, Modifier.Companion, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv13 = Alignment.Companion.getTopStart();
                        MeasurePolicy measurePolicy$iv119 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv13, false);
                        int $changed$iv$iv1116 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv119 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1116 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1116);
                        constructor3 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv1116 = (($changed$iv$iv1116 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function1 = constructor3;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor3;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv119, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv119, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1116 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash1116);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv1116, ComposeUiNode.Companion.getSetModifier());
                        int i111111112 = ($changed$iv$iv$iv1116 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance13 = BoxScopeInstance.INSTANCE;
                        int i111111113 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv1117 = Modifier.Companion;
                        Arrangement.Vertical verticalArrangement$iv17 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv17 = Alignment.Companion.getStart();
                        MeasurePolicy measurePolicy$iv1110 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv17, horizontalAlignment$iv17, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv1117 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv1110 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1117 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1117);
                        constructor4 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv1117 = (($changed$iv$iv1117 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function2 = constructor4;
                            $composer3.createNode(function2);
                        } else {
                            function2 = constructor4;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv1110, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv1110, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1117 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash1117);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv1117, ComposeUiNode.Companion.getSetModifier());
                        int i111111114 = ($changed$iv$iv$iv1117 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance13 = ColumnScopeInstance.INSTANCE;
                        int i111111115 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifierFillMaxWidth$default13 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2414, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                        PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default13 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                        $composer3.startReplaceGroup(-1637032986);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        boolean zChanged13 = $composer2.changed(activeOrb2C4);
                        if (($dirty & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean z11112 = zChanged13 | z;
                        if ((3670016 & $dirty) == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        boolean z11113 = z11112 | z2;
                        if ((458752 & $dirty) == 131072) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z11114 = z11113 | z3;
                        if ((57344 & $dirty) == 16384) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean zChangedInstance13 = z11114 | z4 | $composer2.changedInstance(contextC4);
                        if (($dirty & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        invalid$iv = (($dirty & 896) == 256) | zChangedInstance13 | z5;
                        value$iv2 = $composer3.rememberedValue();
                        if (invalid$iv) {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C4, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC4, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C4, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC4, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        }
                        $composer3.endReplaceGroup();
                        LazyDslKt.LazyColumn(modifierFillMaxWidth$default13, null, paddingValuesM1031PaddingValuesa9UjIt4$default13, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                    }
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash1115);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv1115, ComposeUiNode.Companion.getSetModifier());
                    int i111111116 = ($changed$iv$iv$iv1115 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i111111117 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2418 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack14 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber14 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier10;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs14 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack14, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs14, false, (Function1) value$iv, 1, null), auroraAmber14, $composer3, 48, 0);
                    Modifier modifier$iv1118 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2418, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv14 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv1111 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv14, false);
                    int $changed$iv$iv1118 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv1111 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1118 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1118);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv1118 = (($changed$iv$iv1118 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv1111, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv1111, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1118 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash1118);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv1118, ComposeUiNode.Companion.getSetModifier());
                    int i111111118 = ($changed$iv$iv$iv1118 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance14 = BoxScopeInstance.INSTANCE;
                    int i111111119 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv1119 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv18 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv18 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv1112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv18, horizontalAlignment$iv18, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv1119 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv1112 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1119 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1119);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv1119 = (($changed$iv$iv1119 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv1112, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv1112, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1119 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash1119);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv1119, ComposeUiNode.Companion.getSetModifier());
                    int i1111111110 = ($changed$iv$iv$iv1119 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance14 = ColumnScopeInstance.INSTANCE;
                    int i1111111111 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default14 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2414, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default14 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged14 = $composer2.changed(activeOrb2C4);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z11115 = zChanged14 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z11116 = z11115 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z11117 = z11116 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance14 = z11117 | z4 | $composer2.changedInstance(contextC4);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance14 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C4, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC4, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C4, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC4, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default14, null, paddingValuesM1031PaddingValuesa9UjIt4$default14, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv2 = measurePolicy$iv;
                    localMap$iv$iv2 = localMap$iv$iv;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash1114);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv1114, ComposeUiNode.Companion.getSetModifier());
                int i212 = ($changed$iv$iv$iv1114 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                int i213 = ((0 >> 6) & 112) | 6;
                ColumnScope $this$AssistantSettingsPane_u24lambda_u2415 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                Modifier modifier$iv11110 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                Alignment.Vertical verticalAlignment$iv8 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv8 = Arrangement.INSTANCE.getStart();
                measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv8, verticalAlignment$iv8, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv11110 = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11110 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11110);
                constructor2 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv11110 = (($changed$iv$iv11110 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11110 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv11110, ComposeUiNode.Companion.getSetModifier());
                    int i1111111112 = ($changed$iv$iv$iv11110 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i1111111113 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2419 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack15 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber15 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier10;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs15 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack15, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs15, false, (Function1) value$iv, 1, null), auroraAmber15, $composer3, 48, 0);
                    Modifier modifier$iv11111 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u2419, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv15 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv1113 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv15, false);
                    int $changed$iv$iv11111 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv1113 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11111 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11111);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv11111 = (($changed$iv$iv11111 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv1113, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv1113, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash11111);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv11111, ComposeUiNode.Companion.getSetModifier());
                    int i1111111114 = ($changed$iv$iv$iv11111 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance15 = BoxScopeInstance.INSTANCE;
                    int i1111111115 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv11112 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv19 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv19 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv1114 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv19, horizontalAlignment$iv19, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv11112 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv1114 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11112 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11112);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv11112 = (($changed$iv$iv11112 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv1114, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv1114, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11112 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash11112);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv11112, ComposeUiNode.Companion.getSetModifier());
                    int i1111111116 = ($changed$iv$iv$iv11112 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance15 = ColumnScopeInstance.INSTANCE;
                    int i1111111117 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default15 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2415, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default15 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged15 = $composer2.changed(activeOrb2C4);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z11118 = zChanged15 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z11119 = z11118 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z111110 = z11119 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance15 = z111110 | z4 | $composer2.changedInstance(contextC4);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance15 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C4, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC4, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C4, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC4, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default15, null, paddingValuesM1031PaddingValuesa9UjIt4$default15, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash11110);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv11110, ComposeUiNode.Companion.getSetModifier());
                int i1111111118 = ($changed$iv$iv$iv11110 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i1111111119 = ((390 >> 6) & 112) | 6;
                RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24110 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                ImageVector arrowBack16 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                long auroraAmber16 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                modifier4 = modifier10;
                $composer2 = $composer3;
                Modifier modifierM1034padding3ABfNKs16 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                $composer3.startReplaceGroup(-218490998);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack16, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs16, false, (Function1) value$iv, 1, null), auroraAmber16, $composer3, 48, 0);
                Modifier modifier$iv11113 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24110, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv16 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv1115 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv16, false);
                int $changed$iv$iv11113 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv1115 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11113 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11113);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv11113 = (($changed$iv$iv11113 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor3;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv1115, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv1115, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11113 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash11113);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv11113, ComposeUiNode.Companion.getSetModifier());
                int i11111111110 = ($changed$iv$iv$iv11113 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance16 = BoxScopeInstance.INSTANCE;
                int i11111111111 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv11114 = Modifier.Companion;
                Arrangement.Vertical verticalArrangement$iv110 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv110 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv1116 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv110, horizontalAlignment$iv110, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv11114 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv1116 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11114 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11114);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv11114 = (($changed$iv$iv11114 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor4;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv1116, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv1116, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11114 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash11114);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv11114, ComposeUiNode.Companion.getSetModifier());
                int i11111111112 = ($changed$iv$iv$iv11114 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance16 = ColumnScopeInstance.INSTANCE;
                int i11111111113 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxWidth$default16 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2415, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default16 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                $composer3.startReplaceGroup(-1637032986);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                boolean zChanged16 = $composer2.changed(activeOrb2C4);
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z111111 = zChanged16 | z;
                if ((3670016 & $dirty) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z111112 = z111111 | z2;
                if ((458752 & $dirty) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z111113 = z111112 | z3;
                if ((57344 & $dirty) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance16 = z111113 | z4 | $composer2.changedInstance(contextC4);
                if (($dirty & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = (($dirty & 896) == 256) | zChangedInstance16 | z5;
                value$iv2 = $composer3.rememberedValue();
                if (invalid$iv) {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C4, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC4, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C4, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC4, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                $composer3.endReplaceGroup();
                LazyDslKt.LazyColumn(modifierFillMaxWidth$default16, null, paddingValuesM1031PaddingValuesa9UjIt4$default16, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (i4 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1472870728, $dirty2, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane (AssistantSettingsPane.kt:78)");
                }
                ProvidableCompositionLocal<Context> localContext5 = AndroidCompositionLocals_androidKt.getLocalContext();
                $dirty = $dirty2;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer3.consume(localContext5);
                final Context contextC5 = (Context) objConsume5;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $this$firstOrNull$iv = ORB_CHOICES;
                $i$f$firstOrNull = 0;
                it = $this$firstOrNull$iv.iterator();
                while (true) {
                    $i$f$firstOrNull2 = $i$f$firstOrNull;
                    if (!it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    it2 = (OrbChoice) element$iv;
                    $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                    if (Intrinsics.areEqual(it2.getId(), assist.getOrbStyle())) {
                        break;
                    } else {
                        $i$f$firstOrNull = $i$f$firstOrNull2;
                        $this$firstOrNull$iv = $this$firstOrNull$iv2;
                    }
                }
                activeOrb = (OrbChoice) element$iv;
                if (activeOrb == null) {
                    activeOrb = (OrbChoice) CollectionsKt.first((List) ORB_CHOICES);
                }
                    final OrbChoice activeOrb2C5 = activeOrb;
                Modifier modifier$iv11115 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(modifier3, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraBackground($composer3, 0), null, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv111 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv111 = Alignment.Companion.getStart();
                measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv111, horizontalAlignment$iv111, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv11115 = (0 << 3) & 112;
                Modifier modifier11 = modifier3;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11115 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11115);
                constructor = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv11115 = (($changed$iv$iv11115 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11115 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    measurePolicy$iv2 = measurePolicy$iv;
                    localMap$iv$iv2 = localMap$iv$iv;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv11115, ComposeUiNode.Companion.getSetModifier());
                    int i214 = ($changed$iv$iv$iv11115 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    int i215 = ((0 >> 6) & 112) | 6;
                    ColumnScope $this$AssistantSettingsPane_u24lambda_u2416 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                    Modifier modifier$iv11116 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                    Alignment.Vertical verticalAlignment$iv9 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv9 = Arrangement.INSTANCE.getStart();
                    measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv9, verticalAlignment$iv9, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv11116 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11116 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11116);
                    constructor2 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv11116 = (($changed$iv$iv11116 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor2);
                    } else {
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11116 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv11116, ComposeUiNode.Companion.getSetModifier());
                        int i11111111114 = ($changed$iv$iv$iv11116 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i11111111115 = ((390 >> 6) & 112) | 6;
                        RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24111 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                        ImageVector arrowBack17 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                        long auroraAmber17 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                        modifier4 = modifier11;
                        $composer2 = $composer3;
                        Modifier modifierM1034padding3ABfNKs17 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                        $composer3.startReplaceGroup(-218490998);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        it$iv = $composer3.rememberedValue();
                        if (it$iv == Composer.Companion.getEmpty()) {
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv;
                        }
                        $composer3.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack17, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs17, false, (Function1) value$iv, 1, null), auroraAmber17, $composer3, 48, 0);
                        Modifier modifier$iv11117 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24111, Modifier.Companion, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv17 = Alignment.Companion.getTopStart();
                        MeasurePolicy measurePolicy$iv1117 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv17, false);
                        int $changed$iv$iv11117 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv1117 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11117 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11117);
                        constructor3 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv11117 = (($changed$iv$iv11117 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function1 = constructor3;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor3;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv1117, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv1117, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11117 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash11117);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv11117, ComposeUiNode.Companion.getSetModifier());
                        int i11111111116 = ($changed$iv$iv$iv11117 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance17 = BoxScopeInstance.INSTANCE;
                        int i11111111117 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv11118 = Modifier.Companion;
                        Arrangement.Vertical verticalArrangement$iv112 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv112 = Alignment.Companion.getStart();
                        MeasurePolicy measurePolicy$iv1118 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv112, horizontalAlignment$iv112, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv11118 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv1118 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11118 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11118);
                        constructor4 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv11118 = (($changed$iv$iv11118 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function2 = constructor4;
                            $composer3.createNode(function2);
                        } else {
                            function2 = constructor4;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv1118, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv1118, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11118 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash11118);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv11118, ComposeUiNode.Companion.getSetModifier());
                        int i11111111118 = ($changed$iv$iv$iv11118 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance17 = ColumnScopeInstance.INSTANCE;
                        int i11111111119 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifierFillMaxWidth$default17 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2416, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                        PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default17 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                        $composer3.startReplaceGroup(-1637032986);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                        boolean zChanged17 = $composer2.changed(activeOrb2C5);
                        if (($dirty & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean z111114 = zChanged17 | z;
                        if ((3670016 & $dirty) == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        boolean z111115 = z111114 | z2;
                        if ((458752 & $dirty) == 131072) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z111116 = z111115 | z3;
                        if ((57344 & $dirty) == 16384) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean zChangedInstance17 = z111116 | z4 | $composer2.changedInstance(contextC5);
                        if (($dirty & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        invalid$iv = (($dirty & 896) == 256) | zChangedInstance17 | z5;
                        value$iv2 = $composer3.rememberedValue();
                        if (invalid$iv) {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C5, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC5, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C5, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC5, sttProvider, ttsProvider, (LazyListScope) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                        }
                        $composer3.endReplaceGroup();
                        LazyDslKt.LazyColumn(modifierFillMaxWidth$default17, null, paddingValuesM1031PaddingValuesa9UjIt4$default17, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        measurePolicy$iv4 = measurePolicy$iv3;
                        localMap$iv$iv4 = localMap$iv$iv3;
                    }
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash11116);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv11116, ComposeUiNode.Companion.getSetModifier());
                    int i111111111110 = ($changed$iv$iv$iv11116 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i111111111111 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24112 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack18 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber18 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier11;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs18 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack18, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs18, false, (Function1) value$iv, 1, null), auroraAmber18, $composer3, 48, 0);
                    Modifier modifier$iv11119 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24112, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv18 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv1119 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv18, false);
                    int $changed$iv$iv11119 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv1119 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11119 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11119);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv11119 = (($changed$iv$iv11119 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv1119, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv1119, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11119 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash11119);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv11119, ComposeUiNode.Companion.getSetModifier());
                    int i111111111112 = ($changed$iv$iv$iv11119 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance18 = BoxScopeInstance.INSTANCE;
                    int i111111111113 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv111110 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv113 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv113 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv11110 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv113, horizontalAlignment$iv113, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv111110 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv11110 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111110 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111110);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv111110 = (($changed$iv$iv111110 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv11110, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv11110, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111110 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash111110);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv111110, ComposeUiNode.Companion.getSetModifier());
                    int i111111111114 = ($changed$iv$iv$iv111110 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance18 = ColumnScopeInstance.INSTANCE;
                    int i111111111115 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default18 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2416, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default18 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged18 = $composer2.changed(activeOrb2C5);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z111117 = zChanged18 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z111118 = z111117 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z111119 = z111118 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance18 = z111119 | z4 | $composer2.changedInstance(contextC5);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance18 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C5, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC5, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C5, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC5, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default18, null, paddingValuesM1031PaddingValuesa9UjIt4$default18, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv2 = measurePolicy$iv;
                    localMap$iv$iv2 = localMap$iv$iv;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash11115);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv11115, ComposeUiNode.Companion.getSetModifier());
                int i216 = ($changed$iv$iv$iv11115 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                int i217 = ((0 >> 6) & 112) | 6;
                ColumnScope $this$AssistantSettingsPane_u24lambda_u2417 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                Modifier modifier$iv111111 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                Alignment.Vertical verticalAlignment$iv10 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv10 = Arrangement.INSTANCE.getStart();
                measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv10, verticalAlignment$iv10, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv111111 = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv111111 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111111);
                constructor2 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv111111 = (($changed$iv$iv111111 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv111111, ComposeUiNode.Companion.getSetModifier());
                    int i111111111116 = ($changed$iv$iv$iv111111 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i111111111117 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24113 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack19 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber19 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier11;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs19 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack19, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs19, false, (Function1) value$iv, 1, null), auroraAmber19, $composer3, 48, 0);
                    Modifier modifier$iv111112 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24113, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv19 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv11111 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv19, false);
                    int $changed$iv$iv111112 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv11111 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111112 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111112);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv111112 = (($changed$iv$iv111112 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv11111, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv11111, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111112 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash111112);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv111112, ComposeUiNode.Companion.getSetModifier());
                    int i111111111118 = ($changed$iv$iv$iv111112 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance19 = BoxScopeInstance.INSTANCE;
                    int i111111111119 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv111113 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv114 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv114 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv11112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv114, horizontalAlignment$iv114, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv111113 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv11112 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111113 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111113);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv111113 = (($changed$iv$iv111113 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv11112, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv11112, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111113 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash111113);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv111113, ComposeUiNode.Companion.getSetModifier());
                    int i1111111111110 = ($changed$iv$iv$iv111113 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance19 = ColumnScopeInstance.INSTANCE;
                    int i1111111111111 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default19 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2417, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default19 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged19 = $composer2.changed(activeOrb2C5);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z1111110 = zChanged19 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z1111111 = z1111110 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z1111112 = z1111111 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance19 = z1111112 | z4 | $composer2.changedInstance(contextC5);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance19 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C5, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC5, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C5, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC5, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default19, null, paddingValuesM1031PaddingValuesa9UjIt4$default19, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash111111);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv111111, ComposeUiNode.Companion.getSetModifier());
                int i1111111111112 = ($changed$iv$iv$iv111111 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i1111111111113 = ((390 >> 6) & 112) | 6;
                RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24114 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                ImageVector arrowBack110 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                long auroraAmber110 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                modifier4 = modifier11;
                $composer2 = $composer3;
                Modifier modifierM1034padding3ABfNKs110 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                $composer3.startReplaceGroup(-218490998);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack110, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs110, false, (Function1) value$iv, 1, null), auroraAmber110, $composer3, 48, 0);
                Modifier modifier$iv111114 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24114, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv110 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv11113 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv110, false);
                int $changed$iv$iv111114 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv11113 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv111114 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111114);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv111114 = (($changed$iv$iv111114 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor3;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv11113, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv11113, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111114 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash111114);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv111114, ComposeUiNode.Companion.getSetModifier());
                int i1111111111114 = ($changed$iv$iv$iv111114 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance110 = BoxScopeInstance.INSTANCE;
                int i1111111111115 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv111115 = Modifier.Companion;
                Arrangement.Vertical verticalArrangement$iv115 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv115 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv11114 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv115, horizontalAlignment$iv115, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv111115 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv11114 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv111115 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111115);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv111115 = (($changed$iv$iv111115 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor4;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv11114, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv11114, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111115 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash111115);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv111115, ComposeUiNode.Companion.getSetModifier());
                int i1111111111116 = ($changed$iv$iv$iv111115 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance110 = ColumnScopeInstance.INSTANCE;
                int i1111111111117 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxWidth$default110 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2417, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default110 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                $composer3.startReplaceGroup(-1637032986);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                boolean zChanged110 = $composer2.changed(activeOrb2C5);
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z1111113 = zChanged110 | z;
                if ((3670016 & $dirty) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z1111114 = z1111113 | z2;
                if ((458752 & $dirty) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z1111115 = z1111114 | z3;
                if ((57344 & $dirty) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance110 = z1111115 | z4 | $composer2.changedInstance(contextC5);
                if (($dirty & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = (($dirty & 896) == 256) | zChangedInstance110 | z5;
                value$iv2 = $composer3.rememberedValue();
                if (invalid$iv) {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C5, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC5, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C5, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC5, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                $composer3.endReplaceGroup();
                LazyDslKt.LazyColumn(modifierFillMaxWidth$default110, null, paddingValuesM1031PaddingValuesa9UjIt4$default110, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier12 = modifier4;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$9(assist, sttProvider, ttsProvider, onBack, onSpeakReplies, onAutoListen, onOrbStyle, modifier12, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 = 1572864;
        $dirty2 |= i3;
        i4 = i & 128;
        if (i4 != 0) {
            $dirty2 |= 12582912;
            modifier2 = modifier;
        } else if ((12582912 & $changed) == 0) {
            modifier2 = modifier;
            if ($composer3.changed(modifier2)) {
                i5 = 8388608;
            } else {
                i5 = 4194304;
            }
            $dirty2 |= i5;
        } else {
            modifier2 = modifier;
        }
        if (($dirty2 & 4793491) == 4793490) {
            if (i4 != 0) {
                modifier3 = Modifier.Companion;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1472870728, $dirty2, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane (AssistantSettingsPane.kt:78)");
            }
            ProvidableCompositionLocal<Context> localContext6 = AndroidCompositionLocals_androidKt.getLocalContext();
            $dirty = $dirty2;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localContext6);
            final Context contextC6 = (Context) objConsume6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $this$firstOrNull$iv = ORB_CHOICES;
            $i$f$firstOrNull = 0;
            it = $this$firstOrNull$iv.iterator();
            while (true) {
                $i$f$firstOrNull2 = $i$f$firstOrNull;
                if (!it.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it.next();
                it2 = (OrbChoice) element$iv;
                $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                if (Intrinsics.areEqual(it2.getId(), assist.getOrbStyle())) {
                    break;
                } else {
                    $i$f$firstOrNull = $i$f$firstOrNull2;
                    $this$firstOrNull$iv = $this$firstOrNull$iv2;
                }
            }
            activeOrb = (OrbChoice) element$iv;
            if (activeOrb == null) {
                activeOrb = (OrbChoice) CollectionsKt.first((List) ORB_CHOICES);
            }
                final OrbChoice activeOrb2C6 = activeOrb;
            Modifier modifier$iv111116 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(modifier3, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraBackground($composer3, 0), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv116 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv116 = Alignment.Companion.getStart();
            measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv116, horizontalAlignment$iv116, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv111116 = (0 << 3) & 112;
            Modifier modifier13 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv111116 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111116);
            constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv111116 = (($changed$iv$iv111116 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111116 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                measurePolicy$iv2 = measurePolicy$iv;
                localMap$iv$iv2 = localMap$iv$iv;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv111116, ComposeUiNode.Companion.getSetModifier());
                int i218 = ($changed$iv$iv$iv111116 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                int i219 = ((0 >> 6) & 112) | 6;
                ColumnScope $this$AssistantSettingsPane_u24lambda_u2418 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                Modifier modifier$iv111117 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                Alignment.Vertical verticalAlignment$iv11 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv11 = Arrangement.INSTANCE.getStart();
                measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv11, verticalAlignment$iv11, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv111117 = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv111117 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111117);
                constructor2 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv111117 = (($changed$iv$iv111117 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111117 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv111117, ComposeUiNode.Companion.getSetModifier());
                    int i1111111111118 = ($changed$iv$iv$iv111117 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i1111111111119 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24115 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack111 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber111 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier13;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs111 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack111, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs111, false, (Function1) value$iv, 1, null), auroraAmber111, $composer3, 48, 0);
                    Modifier modifier$iv111118 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24115, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv111 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv11115 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv111, false);
                    int $changed$iv$iv111118 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv11115 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111118 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111118);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv111118 = (($changed$iv$iv111118 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv11115, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv11115, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111118 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash111118);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv111118, ComposeUiNode.Companion.getSetModifier());
                    int i11111111111110 = ($changed$iv$iv$iv111118 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance111 = BoxScopeInstance.INSTANCE;
                    int i11111111111111 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv111119 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv117 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv117 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv11116 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv117, horizontalAlignment$iv117, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv111119 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv11116 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111119 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111119);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv111119 = (($changed$iv$iv111119 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv11116, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv11116, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111119 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash111119);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv111119, ComposeUiNode.Companion.getSetModifier());
                    int i11111111111112 = ($changed$iv$iv$iv111119 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance111 = ColumnScopeInstance.INSTANCE;
                    int i11111111111113 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default111 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2418, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default111 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged111 = $composer2.changed(activeOrb2C6);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z1111116 = zChanged111 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z1111117 = z1111116 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z1111118 = z1111117 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance111 = z1111118 | z4 | $composer2.changedInstance(contextC6);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance111 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C6, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC6, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C6, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC6, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default111, null, paddingValuesM1031PaddingValuesa9UjIt4$default111, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash111117);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv111117, ComposeUiNode.Companion.getSetModifier());
                int i11111111111114 = ($changed$iv$iv$iv111117 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i11111111111115 = ((390 >> 6) & 112) | 6;
                RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24116 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                ImageVector arrowBack112 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                long auroraAmber112 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                modifier4 = modifier13;
                $composer2 = $composer3;
                Modifier modifierM1034padding3ABfNKs112 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                $composer3.startReplaceGroup(-218490998);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack112, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs112, false, (Function1) value$iv, 1, null), auroraAmber112, $composer3, 48, 0);
                Modifier modifier$iv1111110 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24116, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv112 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv11117 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv112, false);
                int $changed$iv$iv1111110 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv11117 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1111110 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111110);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1111110 = (($changed$iv$iv1111110 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor3;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv11117, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv11117, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111110 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash1111110);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv1111110, ComposeUiNode.Companion.getSetModifier());
                int i11111111111116 = ($changed$iv$iv$iv1111110 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance112 = BoxScopeInstance.INSTANCE;
                int i11111111111117 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv1111111 = Modifier.Companion;
                Arrangement.Vertical verticalArrangement$iv118 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv118 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv11118 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv118, horizontalAlignment$iv118, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv1111111 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv11118 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1111111 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111111);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1111111 = (($changed$iv$iv1111111 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor4;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv11118, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv11118, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash1111111);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv1111111, ComposeUiNode.Companion.getSetModifier());
                int i11111111111118 = ($changed$iv$iv$iv1111111 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance112 = ColumnScopeInstance.INSTANCE;
                int i11111111111119 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxWidth$default112 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2418, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default112 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                $composer3.startReplaceGroup(-1637032986);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                boolean zChanged112 = $composer2.changed(activeOrb2C6);
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z1111119 = zChanged112 | z;
                if ((3670016 & $dirty) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z11111110 = z1111119 | z2;
                if ((458752 & $dirty) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z11111111 = z11111110 | z3;
                if ((57344 & $dirty) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance112 = z11111111 | z4 | $composer2.changedInstance(contextC6);
                if (($dirty & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = (($dirty & 896) == 256) | zChangedInstance112 | z5;
                value$iv2 = $composer3.rememberedValue();
                if (invalid$iv) {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C6, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC6, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C6, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC6, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                $composer3.endReplaceGroup();
                LazyDslKt.LazyColumn(modifierFillMaxWidth$default112, null, paddingValuesM1031PaddingValuesa9UjIt4$default112, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                measurePolicy$iv2 = measurePolicy$iv;
                localMap$iv$iv2 = localMap$iv$iv;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash111116);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv111116, ComposeUiNode.Companion.getSetModifier());
            int i2110 = ($changed$iv$iv$iv111116 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            int i2111 = ((0 >> 6) & 112) | 6;
            ColumnScope $this$AssistantSettingsPane_u24lambda_u2419 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
            Modifier modifier$iv1111112 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
            Alignment.Vertical verticalAlignment$iv12 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv12 = Arrangement.INSTANCE.getStart();
            measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv12, verticalAlignment$iv12, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            int $changed$iv$iv1111112 = (390 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv1111112 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111112);
            constructor2 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv1111112 = (($changed$iv$iv1111112 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111112 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                measurePolicy$iv4 = measurePolicy$iv3;
                localMap$iv$iv4 = localMap$iv$iv3;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv1111112, ComposeUiNode.Companion.getSetModifier());
                int i111111111111110 = ($changed$iv$iv$iv1111112 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i111111111111111 = ((390 >> 6) & 112) | 6;
                RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24117 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                ImageVector arrowBack113 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                long auroraAmber113 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                modifier4 = modifier13;
                $composer2 = $composer3;
                Modifier modifierM1034padding3ABfNKs113 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                $composer3.startReplaceGroup(-218490998);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack113, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs113, false, (Function1) value$iv, 1, null), auroraAmber113, $composer3, 48, 0);
                Modifier modifier$iv1111113 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24117, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv113 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv11119 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv113, false);
                int $changed$iv$iv1111113 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv11119 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1111113 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111113);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1111113 = (($changed$iv$iv1111113 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor3;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv11119, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv11119, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111113 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash1111113);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv1111113, ComposeUiNode.Companion.getSetModifier());
                int i111111111111112 = ($changed$iv$iv$iv1111113 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance113 = BoxScopeInstance.INSTANCE;
                int i111111111111113 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv1111114 = Modifier.Companion;
                Arrangement.Vertical verticalArrangement$iv119 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv119 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv111110 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv119, horizontalAlignment$iv119, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv1111114 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv111110 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1111114 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111114);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1111114 = (($changed$iv$iv1111114 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor4;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv111110, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv111110, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111114 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash1111114);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv1111114, ComposeUiNode.Companion.getSetModifier());
                int i111111111111114 = ($changed$iv$iv$iv1111114 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance113 = ColumnScopeInstance.INSTANCE;
                int i111111111111115 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxWidth$default113 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2419, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default113 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                $composer3.startReplaceGroup(-1637032986);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                boolean zChanged113 = $composer2.changed(activeOrb2C6);
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z11111112 = zChanged113 | z;
                if ((3670016 & $dirty) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z11111113 = z11111112 | z2;
                if ((458752 & $dirty) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z11111114 = z11111113 | z3;
                if ((57344 & $dirty) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance113 = z11111114 | z4 | $composer2.changedInstance(contextC6);
                if (($dirty & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = (($dirty & 896) == 256) | zChangedInstance113 | z5;
                value$iv2 = $composer3.rememberedValue();
                if (invalid$iv) {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C6, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC6, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C6, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC6, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                $composer3.endReplaceGroup();
                LazyDslKt.LazyColumn(modifierFillMaxWidth$default113, null, paddingValuesM1031PaddingValuesa9UjIt4$default113, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                measurePolicy$iv4 = measurePolicy$iv3;
                localMap$iv$iv4 = localMap$iv$iv3;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash1111112);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv1111112, ComposeUiNode.Companion.getSetModifier());
            int i111111111111116 = ($changed$iv$iv$iv1111112 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i111111111111117 = ((390 >> 6) & 112) | 6;
            RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24118 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
            ImageVector arrowBack114 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
            long auroraAmber114 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
            modifier4 = modifier13;
            $composer2 = $composer3;
            Modifier modifierM1034padding3ABfNKs114 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
            $composer3.startReplaceGroup(-218490998);
            ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
            it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer3.endReplaceGroup();
            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack114, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs114, false, (Function1) value$iv, 1, null), auroraAmber114, $composer3, 48, 0);
            Modifier modifier$iv1111115 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24118, Modifier.Companion, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv114 = Alignment.Companion.getTopStart();
            MeasurePolicy measurePolicy$iv111111 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv114, false);
            int $changed$iv$iv1111115 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv111111 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv1111115 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111115);
            constructor3 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv1111115 = (($changed$iv$iv1111115 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor3;
                $composer3.createNode(function1);
            } else {
                function1 = constructor3;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv111111, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv111111, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111115 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash1111115);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv1111115, ComposeUiNode.Companion.getSetModifier());
            int i111111111111118 = ($changed$iv$iv$iv1111115 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance114 = BoxScopeInstance.INSTANCE;
            int i111111111111119 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier modifier$iv1111116 = Modifier.Companion;
            Arrangement.Vertical verticalArrangement$iv1110 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv1110 = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv111112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1110, horizontalAlignment$iv1110, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv1111116 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv111112 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv1111116 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111116);
            constructor4 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv1111116 = (($changed$iv$iv1111116 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor4;
                $composer3.createNode(function2);
            } else {
                function2 = constructor4;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv111112, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv111112, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111116 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash1111116);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv1111116, ComposeUiNode.Companion.getSetModifier());
            int i1111111111111110 = ($changed$iv$iv$iv1111116 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance114 = ColumnScopeInstance.INSTANCE;
            int i1111111111111111 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierFillMaxWidth$default114 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u2419, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
            PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default114 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
            $composer3.startReplaceGroup(-1637032986);
            ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
            boolean zChanged114 = $composer2.changed(activeOrb2C6);
            if (($dirty & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            boolean z11111115 = zChanged114 | z;
            if ((3670016 & $dirty) == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z11111116 = z11111115 | z2;
            if ((458752 & $dirty) == 131072) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z11111117 = z11111116 | z3;
            if ((57344 & $dirty) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean zChangedInstance114 = z11111117 | z4 | $composer2.changedInstance(contextC6);
            if (($dirty & 112) == 32) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv = (($dirty & 896) == 256) | zChangedInstance114 | z5;
            value$iv2 = $composer3.rememberedValue();
            if (invalid$iv) {
                value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C6, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC6, sttProvider, ttsProvider, (LazyListScope) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C6, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC6, sttProvider, ttsProvider, (LazyListScope) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            }
            $composer3.endReplaceGroup();
            LazyDslKt.LazyColumn(modifierFillMaxWidth$default114, null, paddingValuesM1031PaddingValuesa9UjIt4$default114, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i4 != 0) {
                modifier3 = Modifier.Companion;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1472870728, $dirty2, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane (AssistantSettingsPane.kt:78)");
            }
            ProvidableCompositionLocal<Context> localContext7 = AndroidCompositionLocals_androidKt.getLocalContext();
            $dirty = $dirty2;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume7 = $composer3.consume(localContext7);
            final Context contextC7 = (Context) objConsume7;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $this$firstOrNull$iv = ORB_CHOICES;
            $i$f$firstOrNull = 0;
            it = $this$firstOrNull$iv.iterator();
            while (true) {
                $i$f$firstOrNull2 = $i$f$firstOrNull;
                if (!it.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it.next();
                it2 = (OrbChoice) element$iv;
                $this$firstOrNull$iv2 = $this$firstOrNull$iv;
                if (Intrinsics.areEqual(it2.getId(), assist.getOrbStyle())) {
                    break;
                } else {
                    $i$f$firstOrNull = $i$f$firstOrNull2;
                    $this$firstOrNull$iv = $this$firstOrNull$iv2;
                }
            }
            activeOrb = (OrbChoice) element$iv;
            if (activeOrb == null) {
                activeOrb = (OrbChoice) CollectionsKt.first((List) ORB_CHOICES);
            }
                final OrbChoice activeOrb2C7 = activeOrb;
            Modifier modifier$iv1111117 = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(modifier3, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraBackground($composer3, 0), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv1111 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv1111 = Alignment.Companion.getStart();
            measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1111, horizontalAlignment$iv1111, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv1111117 = (0 << 3) & 112;
            Modifier modifier14 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv1111117 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111117);
            constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv1111117 = (($changed$iv$iv1111117 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111117 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                measurePolicy$iv2 = measurePolicy$iv;
                localMap$iv$iv2 = localMap$iv$iv;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv1111117, ComposeUiNode.Companion.getSetModifier());
                int i2112 = ($changed$iv$iv$iv1111117 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                int i2113 = ((0 >> 6) & 112) | 6;
                ColumnScope $this$AssistantSettingsPane_u24lambda_u24110 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
                Modifier modifier$iv1111118 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                Alignment.Vertical verticalAlignment$iv13 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv13 = Arrangement.INSTANCE.getStart();
                measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv13, verticalAlignment$iv13, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv1111118 = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1111118 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111118);
                constructor2 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv1111118 = (($changed$iv$iv1111118 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111118 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv1111118, ComposeUiNode.Companion.getSetModifier());
                    int i1111111111111112 = ($changed$iv$iv$iv1111118 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i1111111111111113 = ((390 >> 6) & 112) | 6;
                    RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24119 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                    ImageVector arrowBack115 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                    long auroraAmber115 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    modifier4 = modifier14;
                    $composer2 = $composer3;
                    Modifier modifierM1034padding3ABfNKs115 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    $composer3.startReplaceGroup(-218490998);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack115, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs115, false, (Function1) value$iv, 1, null), auroraAmber115, $composer3, 48, 0);
                    Modifier modifier$iv1111119 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u24119, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv115 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv111113 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv115, false);
                    int $changed$iv$iv1111119 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv111113 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111119 = ComposedModifierKt.materializeModifier($composer3, modifier$iv1111119);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv1111119 = (($changed$iv$iv1111119 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor3;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv111113, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv111113, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111119 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash1111119);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv1111119, ComposeUiNode.Companion.getSetModifier());
                    int i1111111111111114 = ($changed$iv$iv$iv1111119 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance115 = BoxScopeInstance.INSTANCE;
                    int i1111111111111115 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv11111110 = Modifier.Companion;
                    Arrangement.Vertical verticalArrangement$iv1112 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv1112 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv111114 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1112, horizontalAlignment$iv1112, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv11111110 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv111114 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11111110 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11111110);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv11111110 = (($changed$iv$iv11111110 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor4;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv111114, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv111114, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111110 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash11111110);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv11111110, ComposeUiNode.Companion.getSetModifier());
                    int i1111111111111116 = ($changed$iv$iv$iv11111110 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance115 = ColumnScopeInstance.INSTANCE;
                    int i1111111111111117 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFillMaxWidth$default115 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u24110, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                    PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default115 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                    $composer3.startReplaceGroup(-1637032986);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                    boolean zChanged115 = $composer2.changed(activeOrb2C7);
                    if (($dirty & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z11111118 = zChanged115 | z;
                    if ((3670016 & $dirty) == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z11111119 = z11111118 | z2;
                    if ((458752 & $dirty) == 131072) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z111111110 = z11111119 | z3;
                    if ((57344 & $dirty) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean zChangedInstance115 = z111111110 | z4 | $composer2.changedInstance(contextC7);
                    if (($dirty & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    invalid$iv = (($dirty & 896) == 256) | zChangedInstance115 | z5;
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv) {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C7, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC7, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C7, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC7, sttProvider, ttsProvider, (LazyListScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    $composer3.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxWidth$default115, null, paddingValuesM1031PaddingValuesa9UjIt4$default115, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv4 = measurePolicy$iv3;
                    localMap$iv$iv4 = localMap$iv$iv3;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash1111118);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv1111118, ComposeUiNode.Companion.getSetModifier());
                int i1111111111111118 = ($changed$iv$iv$iv1111118 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i1111111111111119 = ((390 >> 6) & 112) | 6;
                RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u241110 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                ImageVector arrowBack116 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                long auroraAmber116 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                modifier4 = modifier14;
                $composer2 = $composer3;
                Modifier modifierM1034padding3ABfNKs116 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                $composer3.startReplaceGroup(-218490998);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack116, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs116, false, (Function1) value$iv, 1, null), auroraAmber116, $composer3, 48, 0);
                Modifier modifier$iv11111111 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u241110, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv116 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv111115 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv116, false);
                int $changed$iv$iv11111111 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv111115 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11111111 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11111111);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv11111111 = (($changed$iv$iv11111111 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor3;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv111115, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv111115, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111111 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash11111111);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv11111111, ComposeUiNode.Companion.getSetModifier());
                int i11111111111111110 = ($changed$iv$iv$iv11111111 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance116 = BoxScopeInstance.INSTANCE;
                int i11111111111111111 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv11111112 = Modifier.Companion;
                Arrangement.Vertical verticalArrangement$iv1113 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv1113 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv111116 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1113, horizontalAlignment$iv1113, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv11111112 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv111116 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11111112 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11111112);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv11111112 = (($changed$iv$iv11111112 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor4;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv111116, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv111116, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111112 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash11111112);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv11111112, ComposeUiNode.Companion.getSetModifier());
                int i11111111111111112 = ($changed$iv$iv$iv11111112 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance116 = ColumnScopeInstance.INSTANCE;
                int i11111111111111113 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxWidth$default116 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u24110, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default116 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                $composer3.startReplaceGroup(-1637032986);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                boolean zChanged116 = $composer2.changed(activeOrb2C7);
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z111111111 = zChanged116 | z;
                if ((3670016 & $dirty) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z111111112 = z111111111 | z2;
                if ((458752 & $dirty) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z111111113 = z111111112 | z3;
                if ((57344 & $dirty) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance116 = z111111113 | z4 | $composer2.changedInstance(contextC7);
                if (($dirty & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = (($dirty & 896) == 256) | zChangedInstance116 | z5;
                value$iv2 = $composer3.rememberedValue();
                if (invalid$iv) {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C7, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC7, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C7, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC7, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                $composer3.endReplaceGroup();
                LazyDslKt.LazyColumn(modifierFillMaxWidth$default116, null, paddingValuesM1031PaddingValuesa9UjIt4$default116, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                measurePolicy$iv2 = measurePolicy$iv;
                localMap$iv$iv2 = localMap$iv$iv;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash1111117);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv1111117, ComposeUiNode.Companion.getSetModifier());
            int i2114 = ($changed$iv$iv$iv1111117 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            int i2115 = ((0 >> 6) & 112) | 6;
            ColumnScope $this$AssistantSettingsPane_u24lambda_u24111 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 790184812, "C87@3626L1225,125@5083L5786,120@4861L6008:AssistantSettingsPane.kt#syfnu6");
            Modifier modifier$iv11111113 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
            Alignment.Vertical verticalAlignment$iv14 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv14 = Arrangement.INSTANCE.getStart();
            measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv14, verticalAlignment$iv14, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            int $changed$iv$iv11111113 = (390 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv11111113 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11111113);
            constructor2 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv11111113 = (($changed$iv$iv11111113 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111113 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                measurePolicy$iv4 = measurePolicy$iv3;
                localMap$iv$iv4 = localMap$iv$iv3;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv11111113, ComposeUiNode.Companion.getSetModifier());
                int i11111111111111114 = ($changed$iv$iv$iv11111113 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i11111111111111115 = ((390 >> 6) & 112) | 6;
                RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u241111 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
                ImageVector arrowBack117 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
                long auroraAmber117 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                modifier4 = modifier14;
                $composer2 = $composer3;
                Modifier modifierM1034padding3ABfNKs117 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                $composer3.startReplaceGroup(-218490998);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack117, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs117, false, (Function1) value$iv, 1, null), auroraAmber117, $composer3, 48, 0);
                Modifier modifier$iv11111114 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u241111, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv117 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv111117 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv117, false);
                int $changed$iv$iv11111114 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv111117 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11111114 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11111114);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv11111114 = (($changed$iv$iv11111114 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor3;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv111117, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv111117, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111114 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash11111114);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv11111114, ComposeUiNode.Companion.getSetModifier());
                int i11111111111111116 = ($changed$iv$iv$iv11111114 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance117 = BoxScopeInstance.INSTANCE;
                int i11111111111111117 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv11111115 = Modifier.Companion;
                Arrangement.Vertical verticalArrangement$iv1114 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv1114 = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv111118 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1114, horizontalAlignment$iv1114, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv11111115 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv111118 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11111115 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11111115);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv11111115 = (($changed$iv$iv11111115 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor4;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv111118, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv111118, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111115 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash11111115);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv11111115, ComposeUiNode.Companion.getSetModifier());
                int i11111111111111118 = ($changed$iv$iv$iv11111115 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance117 = ColumnScopeInstance.INSTANCE;
                int i11111111111111119 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxWidth$default117 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u24111, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
                PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default117 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
                $composer3.startReplaceGroup(-1637032986);
                ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
                boolean zChanged117 = $composer2.changed(activeOrb2C7);
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z111111114 = zChanged117 | z;
                if ((3670016 & $dirty) == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z111111115 = z111111114 | z2;
                if ((458752 & $dirty) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z111111116 = z111111115 | z3;
                if ((57344 & $dirty) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance117 = z111111116 | z4 | $composer2.changedInstance(contextC7);
                if (($dirty & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = (($dirty & 896) == 256) | zChangedInstance117 | z5;
                value$iv2 = $composer3.rememberedValue();
                if (invalid$iv) {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C7, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC7, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C7, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC7, sttProvider, ttsProvider, (LazyListScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                $composer3.endReplaceGroup();
                LazyDslKt.LazyColumn(modifierFillMaxWidth$default117, null, paddingValuesM1031PaddingValuesa9UjIt4$default117, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                measurePolicy$iv4 = measurePolicy$iv3;
                localMap$iv$iv4 = localMap$iv$iv3;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash11111113);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv11111113, ComposeUiNode.Companion.getSetModifier());
            int i111111111111111110 = ($changed$iv$iv$iv11111113 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i111111111111111111 = ((390 >> 6) & 112) | 6;
            RowScope $this$AssistantSettingsPane_u24lambda_u248_u24lambda_u241112 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1816406664, "C96@4006L11,101@4204L31,93@3865L385,103@4263L578:AssistantSettingsPane.kt#syfnu6");
            ImageVector arrowBack118 = ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE);
            long auroraAmber118 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
            modifier4 = modifier14;
            $composer2 = $composer3;
            Modifier modifierM1034padding3ABfNKs118 = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(44)), false, null, null, onBack, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
            $composer3.startReplaceGroup(-218490998);
            ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
            it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer3.endReplaceGroup();
            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(arrowBack118, "Back", ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM1034padding3ABfNKs118, false, (Function1) value$iv, 1, null), auroraAmber118, $composer3, 48, 0);
            Modifier modifier$iv11111116 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AssistantSettingsPane_u24lambda_u248_u24lambda_u241112, Modifier.Companion, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv118 = Alignment.Companion.getTopStart();
            MeasurePolicy measurePolicy$iv111119 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv118, false);
            int $changed$iv$iv11111116 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv111119 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv11111116 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11111116);
            constructor3 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv11111116 = (($changed$iv$iv11111116 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor3;
                $composer3.createNode(function1);
            } else {
                function1 = constructor3;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv111119, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv111119, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111116 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash11111116);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv11111116, ComposeUiNode.Companion.getSetModifier());
            int i111111111111111112 = ($changed$iv$iv$iv11111116 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance118 = BoxScopeInstance.INSTANCE;
            int i111111111111111113 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 439828475, "C104@4352L475:AssistantSettingsPane.kt#syfnu6");
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier modifier$iv11111117 = Modifier.Companion;
            Arrangement.Vertical verticalArrangement$iv1115 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv1115 = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv1111110 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1115, horizontalAlignment$iv1115, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv11111117 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv1111110 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv11111117 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11111117);
            constructor4 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv11111117 = (($changed$iv$iv11111117 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor4;
                $composer3.createNode(function2);
            } else {
                function2 = constructor4;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv1111110, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv1111110, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111117 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash11111117);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv11111117, ComposeUiNode.Companion.getSetModifier());
            int i111111111111111114 = ($changed$iv$iv$iv11111117 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance118 = ColumnScopeInstance.INSTANCE;
            int i111111111111111115 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 180951790, "C107@4463L10,105@4381L215,113@4729L15,111@4617L192:AssistantSettingsPane.kt#syfnu6");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Power-hold orb · Razr Digital assistant", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierFillMaxWidth$default118 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(ai.nous.aurora.compat.Compat.d_ColumnScope_weight_6($this$AssistantSettingsPane_u24lambda_u24111, Modifier.Companion, 1.0f, false, 2, null), 0.0f, 1, null);
            PaddingValues paddingValuesM1031PaddingValuesa9UjIt4$default118 = ai.nous.aurora.compat.Compat.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, AuroraSpacing.INSTANCE.m243getXxlD9Ej5fM(), 7, null);
            $composer3.startReplaceGroup(-1637032986);
            ComposerKt.sourceInformation($composer3, "CC(remember):AssistantSettingsPane.kt#9igjgp");
            boolean zChanged118 = $composer2.changed(activeOrb2C7);
            if (($dirty & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            boolean z111111117 = zChanged118 | z;
            if ((3670016 & $dirty) == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z111111118 = z111111117 | z2;
            if ((458752 & $dirty) == 131072) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z111111119 = z111111118 | z3;
            if ((57344 & $dirty) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean zChangedInstance118 = z111111119 | z4 | $composer2.changedInstance(contextC7);
            if (($dirty & 112) == 32) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv = (($dirty & 896) == 256) | zChangedInstance118 | z5;
            value$iv2 = $composer3.rememberedValue();
            if (invalid$iv) {
                value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C7, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC7, sttProvider, ttsProvider, (LazyListScope) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$8$lambda$7$lambda$6(activeOrb2C7, assist, onOrbStyle, onAutoListen, onSpeakReplies, contextC7, sttProvider, ttsProvider, (LazyListScope) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            }
            $composer3.endReplaceGroup();
            LazyDslKt.LazyColumn(modifierFillMaxWidth$default118, null, paddingValuesM1031PaddingValuesa9UjIt4$default118, false, null, null, null, false,null, (Function1) value$iv2, $composer3, 384, 250);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier15 = modifier4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AssistantSettingsPaneKt.AssistantSettingsPane$lambda$9(assist, sttProvider, ttsProvider, onBack, onSpeakReplies, onAutoListen, onOrbStyle, modifier15, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistantSettingsPane$lambda$8$lambda$5$lambda$2$lambda$1(SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        SemanticsPropertiesKt.setContentDescription(semantics, "Back");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistantSettingsPane$lambda$8$lambda$7$lambda$6(final OrbChoice $activeOrb, final AssistSettingsUi $assist, Function1 $onOrbStyle, final Function1 $onAutoListen, final Function1 $onSpeakReplies, Context $context, final String $sttProvider, final String $ttsProvider, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1394443858, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                invoke(lazyItemScope, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
                Function0<ComposeUiNode> function0;
                Intrinsics.checkNotNullParameter(item, "$this$item");
                ComposerKt.sourceInformation($composer, "C127@5120L794:AssistantSettingsPane.kt#syfnu6");
                if (($changed & 17) != 16 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1394443858, $changed, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AssistantSettingsPane.kt:127)");
                    }
                    Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1036paddingVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), 0.0f, AuroraSpacing.INSTANCE.m237getLgD9Ej5fM(), 1, null);
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getCenterHorizontally();
                    OrbChoice orbChoice = $activeOrb;
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv = (390 << 3) & 112;
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
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i2 = ((390 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 244314970, "C133@5383L249,139@5653L41,142@5816L15,140@5715L181:AssistantSettingsPane.kt#syfnu6");
                    AssistOrbKt.m85AssistOrbjIwJxvA(AssistOrbPhase.Idle, orbChoice.getId(), null, ai.nous.aurora.compat.Compat.m7016constructorimpl(112), null, "Orb preview " + orbChoice.getLabel(), $composer, 3078, 20);
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), $composer, 6);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Preview · " + orbChoice.getLabel(), (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 0, 131058);
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
        }), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableSingletons$AssistantSettingsPaneKt.INSTANCE.m135getLambda1$app_debug(), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1691622006, true, new AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$2($assist, $onOrbStyle)), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableSingletons$AssistantSettingsPaneKt.INSTANCE.m136getLambda2$app_debug(), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-795390836, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$3
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                invoke(lazyItemScope, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(item, "$this$item");
                ComposerKt.sourceInformation($composer, "C186@7792L295:AssistantSettingsPane.kt#syfnu6");
                if (($changed & 17) != 16 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-795390836, $changed, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AssistantSettingsPane.kt:186)");
                    }
                    AuroraDsKt.AuroraSwitchRow("Auto-listen on open", $assist.getAutoListen(), $onAutoListen, "Start mic when power-hold opens Assist", true, $composer, 27654, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }
        }), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-347275251, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$4
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                invoke(lazyItemScope, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(item, "$this$item");
                ComposerKt.sourceInformation($composer, "C195@8137L295:AssistantSettingsPane.kt#syfnu6");
                if (($changed & 17) != 16 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-347275251, $changed, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AssistantSettingsPane.kt:195)");
                    }
                    AuroraDsKt.AuroraSwitchRow("Speak replies", $assist.getSpeakReplies(), $onSpeakReplies, "Play Hermes answer aloud after each turn", true, $composer, 27654, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }
        }), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableSingletons$AssistantSettingsPaneKt.INSTANCE.m137getLambda3$app_debug(), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(548955919, true, new AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$5($context)), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(997071504, true, new AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$6($context)), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableSingletons$AssistantSettingsPaneKt.INSTANCE.m138getLambda4$app_debug(), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1632686517, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$7
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                invoke(lazyItemScope, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(item, "$this$item");
                ComposerKt.sourceInformation($composer, "C242@10199L15,239@9978L450:AssistantSettingsPane.kt#syfnu6");
                if (($changed & 17) == 16 && $composer.getSkipping()) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1632686517, $changed, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AssistantSettingsPane.kt:239)");
                }
                String str = $sttProvider;
                String str2 = "-";
                if (StringsKt.isBlank(str)) {
                    str = "-";
                }
                String str3 = $ttsProvider;
                if (!StringsKt.isBlank(str3)) {
                    str2 = str3;
                }
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("STT " + ((Object) str) + " · TTS " + ((Object) str2) + "\nChange under Advanced STT / TTS (Hermes desktop keys).", ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(Modifier.Companion, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3120, 0, 130992);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }), 3, null);
        ai.nous.aurora.compat.Compat.d_LazyListScope_item_6(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1184570932, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$AssistantSettingsPane$1$2$1$8
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                invoke(lazyItemScope, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(item, "$this$item");
                ComposerKt.sourceInformation($composer, "C252@10665L10,249@10478L367:AssistantSettingsPane.kt#syfnu6");
                if (($changed & 17) != 16 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1184570932, $changed, -1, "ai.nous.aurora.presentation.settings.AssistantSettingsPane.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AssistantSettingsPane.kt:249)");
                    }
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Path: mic -> host Whisper -> Hermes -> " + ($assist.getSpeakReplies() ? "spoken reply" : "text only"), ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(Modifier.Companion, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM()), AuroraColorSchemeKt.getAuroraLive($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3120, 0, 131056);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }
        }), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:49:0x0244  */
    public static final void AssistActionRow(final String title, final String subtitle, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Alignment.Horizontal horizontalAlignment$iv;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-2115209118);
        ComposerKt.sourceInformation($composer3, "C(AssistActionRow)P(2,1)263@10979L364:AssistantSettingsPane.kt#syfnu6");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(subtitle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2115209118, $dirty2, -1, "ai.nous.aurora.presentation.settings.AssistActionRow (AssistantSettingsPane.kt:262)");
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), false, null, null, function0, 7, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(14));
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv2, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                horizontalAlignment$iv = horizontalAlignment$iv2;
            } else {
                horizontalAlignment$iv = horizontalAlignment$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -154438236, "C269@11203L10,269@11176L88,270@11303L15,270@11273L64:AssistantSettingsPane.kt#syfnu6");
                $composer2 = $composer3;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 199680, 0, 131026);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 3072, 0, 131058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -154438236, "C269@11203L10,269@11176L88,270@11303L15,270@11273L64:AssistantSettingsPane.kt#syfnu6");
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 199680, 0, 131026);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 3072, 0, 131058);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.settings.AssistantSettingsPaneKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AssistantSettingsPaneKt.AssistActionRow$lambda$11(title, subtitle, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
