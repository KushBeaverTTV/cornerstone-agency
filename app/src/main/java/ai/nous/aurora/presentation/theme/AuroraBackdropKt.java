package ai.nous.aurora.presentation.theme;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
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
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuroraBackdrop.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aA\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001e\b\u0002\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"AuroraBackdrop", "", "modifier", "Landroidx/compose/ui/Modifier;", "starSeed", "", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/Modifier;ILkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AuroraBackdropKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraBackdrop$lambda$0(Modifier modifier, int i, Function3 function3, int i2, int i3, Composer composer, int i4) {
        AuroraBackdrop(modifier, i, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraBackdrop$lambda$2(Modifier modifier, int i, Function3 function3, int i2, int i3, Composer composer, int i4) {
        AuroraBackdrop(modifier, i, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static final void AuroraBackdrop(Modifier modifier, int starSeed, Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        int i2;
        Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function4;
        Modifier modifier3;
        int starSeed2;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(1397984870);
        ComposerKt.sourceInformation($composer2, "C(AuroraBackdrop)P(1,2)25@857L7,48@1727L940:AuroraBackdrop.kt#li0cm");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            i2 = starSeed;
        } else if (($changed & 48) == 0) {
            i2 = starSeed;
            $dirty |= $composer2.changed(i2) ? 32 : 16;
        } else {
            i2 = starSeed;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            function4 = function3;
        } else if (($changed & 384) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function3;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            starSeed2 = i2;
        } else {
            modifier3 = i3 != 0 ? Modifier.Companion : modifier2;
            starSeed2 = i4 != 0 ? 19 : i2;
            final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3M246getLambda1$app_debug = i5 != 0 ? ComposableSingletons$AuroraBackdropKt.INSTANCE.m246getLambda1$app_debug() : function4;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1397984870, $dirty, -1, "ai.nous.aurora.presentation.theme.AuroraBackdrop (AuroraBackdrop.kt:24)");
            }
            ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localThemeTokens);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ThemeTokens tokens = (ThemeTokens) objConsume;
            $composer2.startReplaceGroup(1638796534);
            ComposerKt.sourceInformation($composer2, "27@950L710,27@898L762");
            if (tokens.getAstral()) {
                AuroraSkyKt.AuroraSky(modifier3, 0.0f, false, starSeed2, (Function3) ComposableLambdaKt.rememberComposableLambda(-1021559428, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.theme.AuroraBackdropKt.AuroraBackdrop.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer, Integer num) {
                        invoke(boxScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BoxScope AuroraSky, Composer $composer3, int $changed2) {
                        Intrinsics.checkNotNullParameter(AuroraSky, "$this$AuroraSky");
                        ComposerKt.sourceInformation($composer3, "C30@1105L523,43@1641L9:AuroraBackdrop.kt#li0cm");
                        int $dirty2 = $changed2;
                        if (($changed2 & 6) == 0) {
                            $dirty2 |= $composer3.changed(AuroraSky) ? 4 : 2;
                        }
                        if (($dirty2 & 19) == 18 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1021559428, $dirty2, -1, "ai.nous.aurora.presentation.theme.AuroraBackdrop.<anonymous> (AuroraBackdrop.kt:30)");
                        }
                        Modifier modifierFillMaxSize$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
                        Brush.Companion companion = Brush.Companion;
                        Float fValueOf = Float.valueOf(0.0f);
                        long jM4560getBlack0d7_KjU = ai.nous.aurora.compat.Compat.m4560getBlack0d7_KjU(Color.Companion);
                        Float fValueOf2 = Float.valueOf(0.45f);
                        long jM4560getBlack0d7_KjU2 = ai.nous.aurora.compat.Compat.m4560getBlack0d7_KjU(Color.Companion);
                        Float fValueOf3 = Float.valueOf(1.0f);
                        long jM4560getBlack0d7_KjU3 = ai.nous.aurora.compat.Compat.m4560getBlack0d7_KjU(Color.Companion);
                        BoxKt.Box(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(modifierFillMaxSize$default, ai.nous.aurora.compat.Compat.m4492verticalGradient8A3gB4$default(companion, new Pair[]{TuplesKt.to(fValueOf, ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM4560getBlack0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM4560getBlack0d7_KjU) : 0.06f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM4560getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM4560getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM4560getBlack0d7_KjU) : 0.0f))), TuplesKt.to(fValueOf2, ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM4560getBlack0d7_KjU2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM4560getBlack0d7_KjU2) : 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM4560getBlack0d7_KjU2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM4560getBlack0d7_KjU2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM4560getBlack0d7_KjU2) : 0.0f))), TuplesKt.to(fValueOf3, ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM4560getBlack0d7_KjU3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM4560getBlack0d7_KjU3) : 0.15f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM4560getBlack0d7_KjU3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM4560getBlack0d7_KjU3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM4560getBlack0d7_KjU3) : 0.0f)))}, 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), $composer3, 0);
                        function3M246getLambda1$app_debug.invoke(AuroraSky, $composer3, Integer.valueOf($dirty2 & 14));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer2, 54), $composer2, ($dirty & 14) | 24576 | (($dirty << 6) & 7168), 6);
                $composer2.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier4 = modifier3;
                    final int i6 = starSeed2;
                    final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function5 = function3M246getLambda1$app_debug;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.theme.AuroraBackdropKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return AuroraBackdropKt.AuroraBackdrop$lambda$0(modifier4, i6, function5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            int $dirty2 = $dirty;
            function4 = function3M246getLambda1$app_debug;
            $composer2.endReplaceGroup();
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(modifier3, ai.nous.aurora.compat.Compat.m4492verticalGradient8A3gB4$default(Brush.Companion, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m349getVoidColor0d7_KjU())), TuplesKt.to(Float.valueOf(0.45f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m315getBackground0d7_KjU())), TuplesKt.to(Float.valueOf(0.85f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m335getSkyHorizon0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m349getVoidColor0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.Companion.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i7 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScope $this$AuroraBackdrop_u24lambda_u241 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -371484890, "C60@2097L546,74@2652L9:AuroraBackdrop.kt#li0cm");
            Modifier modifierFillMaxSize$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
            Brush.Companion companion = Brush.Companion;
            Float fValueOf = Float.valueOf(0.0f);
            long jM308getAccent0d7_KjU = tokens.m308getAccent0d7_KjU();
            Float fValueOf2 = Float.valueOf(0.22f);
            long jM308getAccent0d7_KjU2 = tokens.m308getAccent0d7_KjU();
            BoxKt.Box(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(modifierFillMaxSize$default, ai.nous.aurora.compat.Compat.m4492verticalGradient8A3gB4$default(companion, new Pair[]{TuplesKt.to(fValueOf, ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM308getAccent0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM308getAccent0d7_KjU) : 0.05f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM308getAccent0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM308getAccent0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM308getAccent0d7_KjU) : 0.0f))), TuplesKt.to(fValueOf2, ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM308getAccent0d7_KjU2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM308getAccent0d7_KjU2) : 0.015f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM308getAccent0d7_KjU2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM308getAccent0d7_KjU2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM308getAccent0d7_KjU2) : 0.0f))), TuplesKt.to(Float.valueOf(0.4f), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion)))}, 0.0f, 0.0f, ai.nous.aurora.compat.Compat.m4907getClamp3opZhB0(TileMode.Companion), 6, (Object) null), null, 0.0f, 6, null), $composer2, 0);
            function4.invoke($this$AuroraBackdrop_u24lambda_u241, $composer2, Integer.valueOf(((((0 >> 6) & 112) | 6) & 14) | (($dirty2 >> 3) & 112)));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            final Modifier modifier5 = modifier3;
            final int i8 = starSeed2;
            final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function6 = function4;
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.theme.AuroraBackdropKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraBackdropKt.AuroraBackdrop$lambda$2(modifier5, i8, function6, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
