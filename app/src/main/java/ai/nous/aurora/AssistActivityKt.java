package ai.nous.aurora;

import ai.nous.aurora.presentation.theme.AuroraBackdropKt;
import ai.nous.aurora.presentation.theme.ThemeTokens;
import ai.nous.aurora.presentation.theme.ThemeTokensKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.SizeKt;
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
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.termux.terminal.KeyHandler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssistActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a{\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0001¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"AssistUi", "", "phase", "", "statusLine", "transcript", "reply", "orbStyle", "speakReplies", "", "onToggleSpeak", "Lkotlin/Function1;", "onOrbDown", "Lkotlin/Function0;", "onOrbUp", "onClose", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AssistActivityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistUi$lambda$1(String str, String str2, String str3, String str4, String str5, boolean z, Function1 function1, Function0 function0, Function0 function2, Function0 function3, int i, Composer composer, int i2) {
        AssistUi(str, str2, str3, str4, str5, z, function1, function0, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0282  */
    /* JADX WARN: Code duplicated, block: B:103:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:99:0x027d  */
    public static final void AssistUi(final String phase, final String statusLine, final String transcript, final String reply, final String orbStyle, final boolean speakReplies, final Function1<? super Boolean, Unit> onToggleSpeak, final Function0<Unit> onOrbDown, final Function0<Unit> onOrbUp, final Function0<Unit> onClose, Composer $composer, final int $changed) {
        CompositionLocalMap localMap$iv$iv;
        MeasurePolicy measurePolicy$iv;
        Color.Companion companion;
        long jM4560getBlack0d7_KjU;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(statusLine, "statusLine");
        Intrinsics.checkNotNullParameter(transcript, "transcript");
        Intrinsics.checkNotNullParameter(reply, "reply");
        Intrinsics.checkNotNullParameter(orbStyle, "orbStyle");
        Intrinsics.checkNotNullParameter(onToggleSpeak, "onToggleSpeak");
        Intrinsics.checkNotNullParameter(onOrbDown, "onOrbDown");
        Intrinsics.checkNotNullParameter(onOrbUp, "onOrbUp");
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        Composer $composer3 = $composer.startRestartGroup(-2092690954);
        ComposerKt.sourceInformation($composer3, "C(AssistUi)P(5,8,9,6,4,7,3,1,2)472@18310L7,473@18329L6339:AssistActivity.kt#l0z6fd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(phase) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(statusLine) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(transcript) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(reply) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(orbStyle) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(speakReplies) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(onToggleSpeak) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(onOrbDown) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(onOrbUp) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(onClose) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int $dirty2 = $dirty;
        if ((306783379 & $dirty2) != 306783378 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2092690954, $dirty2, -1, "ai.nous.aurora.AssistUi (AssistActivity.kt:465)");
            }
            boolean listening = Intrinsics.areEqual(phase, "recording");
            boolean working = Intrinsics.areEqual(phase, "working");
            ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localThemeTokens);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean astral = ((ThemeTokens) objConsume).getAstral();
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.Companion.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
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
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv = localMap$iv$iv2;
                measurePolicy$iv = measurePolicy$iv2;
            } else {
                localMap$iv$iv = localMap$iv$iv2;
                measurePolicy$iv = measurePolicy$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1845645778, "C474@18378L64,479@18621L6045,475@18451L6215:AssistActivity.kt#l0z6fd");
                AuroraBackdropKt.AuroraBackdrop(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null), 61, null, $composer3, 54, 4);
                Modifier modifierFillMaxSize$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
                companion = Color.Companion;
                if (astral) {
                    jM4560getBlack0d7_KjU = ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(companion);
                } else {
                    jM4560getBlack0d7_KjU = ai.nous.aurora.compat.Compat.m4560getBlack0d7_KjU(companion);
                }
                $composer2 = $composer3;
                BoxWithConstraintsKt.BoxWithConstraints(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierFillMaxSize$default, jM4560getBlack0d7_KjU, null, 2, null), null, false, (Function3) ComposableLambdaKt.rememberComposableLambda(-64316006, true, new AssistActivityKt$AssistUi$1$1(onClose, speakReplies, onToggleSpeak, phase, orbStyle, onOrbDown, statusLine, listening, working, transcript, reply), $composer3, 54), $composer3, 3072, 6);
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
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i4 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1845645778, "C474@18378L64,479@18621L6045,475@18451L6215:AssistActivity.kt#l0z6fd");
            AuroraBackdropKt.AuroraBackdrop(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null), 61, null, $composer3, 54, 4);
            Modifier modifierFillMaxSize$default2 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
            companion = Color.Companion;
            if (astral) {
                jM4560getBlack0d7_KjU = ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(companion);
            } else {
                jM4560getBlack0d7_KjU = ai.nous.aurora.compat.Compat.m4560getBlack0d7_KjU(companion);
            }
            $composer2 = $composer3;
            BoxWithConstraintsKt.BoxWithConstraints(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierFillMaxSize$default2, jM4560getBlack0d7_KjU, null, 2, null), null, false, (Function2) ComposableLambdaKt.rememberComposableLambda(-64316006, true, new AssistActivityKt$AssistUi$1$1(onClose, speakReplies, onToggleSpeak, phase, orbStyle, onOrbDown, statusLine, listening, working, transcript, reply), $composer3, 54), $composer3, 3072, 6);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.AssistActivityKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AssistActivityKt.AssistUi$lambda$1(phase, statusLine, transcript, reply, orbStyle, speakReplies, onToggleSpeak, onOrbDown, onOrbUp, onClose, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
