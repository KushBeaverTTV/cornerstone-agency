package ai.nous.aurora.presentation.theme;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AuroraSky.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aU\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u001e\b\u0002\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0002\u0010\u000f\"\u0011\u0010\u0010\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"AuroraSky", "", "modifier", "Landroidx/compose/ui/Modifier;", "glow", "", "stars", "", "starSeed", "", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/Modifier;FZILkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AuroraGoldGradient", "Landroidx/compose/ui/graphics/Brush;", "getAuroraGoldGradient", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Brush;", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AuroraSkyKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraSky$lambda$3(Modifier modifier, float f, boolean z, int i, Function3 function3, int i2, int i3, Composer composer, int i4) {
        AuroraSky(modifier, f, z, i, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static final void AuroraSky(Modifier modifier, float glow, boolean stars, int starSeed, Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        boolean stars2;
        int starSeed2;
        Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3M247getLambda1$app_debug;
        float glow2;
        Modifier modifier3;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(187594452);
        ComposerKt.sourceInformation($composer2, "C(AuroraSky)P(2,1,4,3)35@1179L15,36@1211L10,37@1240L16,38@1274L16,39@1306L15,40@1337L18,41@1373L12,44@1427L7658:AuroraSky.kt#li0cm");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            f = glow;
        } else if (($changed & 48) == 0) {
            f = glow;
            $dirty |= $composer2.changed(f) ? 32 : 16;
        } else {
            f = glow;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            stars2 = stars;
        } else if (($changed & 384) == 0) {
            stars2 = stars;
            $dirty |= $composer2.changed(stars2) ? 256 : 128;
        } else {
            stars2 = stars;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            starSeed2 = starSeed;
        } else if (($changed & 3072) == 0) {
            starSeed2 = starSeed;
            $dirty |= $composer2.changed(starSeed2) ? 2048 : 1024;
        } else {
            starSeed2 = starSeed;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            function3M247getLambda1$app_debug = function3;
        } else if (($changed & 24576) == 0) {
            function3M247getLambda1$app_debug = function3;
            $dirty |= $composer2.changedInstance(function3M247getLambda1$app_debug) ? 16384 : 8192;
        } else {
            function3M247getLambda1$app_debug = function3;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            glow2 = f;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.Companion : modifier2;
            float glow3 = i3 != 0 ? 1.0f : f;
            if (i4 != 0) {
                stars2 = true;
            }
            if (i5 != 0) {
                starSeed2 = 19;
            }
            if (i6 != 0) {
                function3M247getLambda1$app_debug = ComposableSingletons$AuroraSkyKt.INSTANCE.m247getLambda1$app_debug();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(187594452, $dirty, -1, "ai.nous.aurora.presentation.theme.AuroraSky (AuroraSky.kt:34)");
            }
            long zenith = AuroraColorSchemeKt.getAuroraSkyZenith($composer2, 0);
            long voidC = AuroraColorSchemeKt.getAuroraVoid($composer2, 0);
            long horizon = AuroraColorSchemeKt.getAuroraSkyHorizon($composer2, 0);
            long nebula = AuroraColorSchemeKt.getAuroraDawnViolet($composer2, 0);
            Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function4 = function3M247getLambda1$app_debug;
            final long star = AuroraColorSchemeKt.getAuroraStarlight($composer2, 0);
            final int starSeed3 = starSeed2;
            final long cyan = AuroraColorSchemeKt.getAuroraAccentStrong($composer2, 0);
            long violet = AuroraColorSchemeKt.getAuroraAccent($composer2, 0);
            float g = RangesKt.coerceIn(glow3, 0.0f, 1.5f);
            glow2 = glow3;
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(modifier4, ai.nous.aurora.compat.Compat.m4492verticalGradient8A3gB4$default(Brush.Companion, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), ai.nous.aurora.compat.Compat.m4524boximpl(zenith)), TuplesKt.to(Float.valueOf(0.22f), ai.nous.aurora.compat.Compat.m4524boximpl(ColorKt.Color(4278847000L))), TuplesKt.to(Float.valueOf(0.48f), ai.nous.aurora.compat.Compat.m4524boximpl(voidC)), TuplesKt.to(Float.valueOf(0.72f), ai.nous.aurora.compat.Compat.m4524boximpl(horizon)), TuplesKt.to(Float.valueOf(1.0f), ai.nous.aurora.compat.Compat.m4524boximpl(ColorKt.Color(4278518296L)))}, 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
            modifier3 = modifier4;
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
                $composer2.createNode(constructor);
            } else {
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
            int $changed2 = ((0 >> 6) & 112) | 6;
            BoxScope $this$AuroraSky_u24lambda_u242 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 611220982, "C222@9070L9:AuroraSky.kt#li0cm");
            $composer2.startReplaceGroup(-811571099);
            ComposerKt.sourceInformation($composer2, "59@1901L586,75@2550L584,91@3180L511,106@3769L704,123@4537L620");
            if (g > 0.0f) {
                Modifier modifierFillMaxSize$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
                Brush.Companion companion = Brush.Companion;
                long jColor = ColorKt.Color(4283046496L);
                BoxKt.Box(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(modifierFillMaxSize$default, ai.nous.aurora.compat.Compat.m4497radialGradientP_VxKs(companion,(List<Color>) CollectionsKt.listOf(new Color[]{ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(nebula, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(nebula) : g * 0.28f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(nebula) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(nebula) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(nebula) : 0.0f)), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jColor, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jColor) : g * 0.14f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jColor) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jColor) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jColor) : 0.0f)), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion))}), (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4308getUnspecifiedF1C5BW0(Offset.Companion) : OffsetKt.Offset(0.15f, 0.18f), (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 900.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4907getClamp3opZhB0(TileMode.Companion) : 0), null, 0.0f, 6, null), $composer2, 0);
                Modifier modifierFillMaxSize$default2 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
                Brush.Companion companion2 = Brush.Companion;
                long jColor2 = ColorKt.Color(4279904896L);
                BoxKt.Box(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(modifierFillMaxSize$default2, ai.nous.aurora.compat.Compat.m4497radialGradientP_VxKs(companion2,(List<Color>) CollectionsKt.listOf(new Color[]{ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(cyan, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(cyan) : g * 0.16f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(cyan) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(cyan) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(cyan) : 0.0f)), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jColor2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jColor2) : g * 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jColor2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jColor2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jColor2) : 0.0f)), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion))}), (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4308getUnspecifiedF1C5BW0(Offset.Companion) : OffsetKt.Offset(0.88f, 0.42f), (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 750.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4907getClamp3opZhB0(TileMode.Companion) : 0), null, 0.0f, 6, null), $composer2, 0);
                BoxKt.Box(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m4497radialGradientP_VxKs(Brush.Companion,(List<Color>) CollectionsKt.listOf(new Color[]{ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(violet, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(violet) : g * 0.12f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(violet) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(violet) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(violet) : 0.0f)), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion))}), (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4308getUnspecifiedF1C5BW0(Offset.Companion) : OffsetKt.Offset(0.45f, 0.92f), (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 700.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4907getClamp3opZhB0(TileMode.Companion) : 0), null, 0.0f, 6, null), $composer2, 0);
                Modifier modifierFillMaxSize$default3 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
                Brush.Companion companion3 = Brush.Companion;
                Float fValueOf = Float.valueOf(0.42f);
                long jColor3 = ColorKt.Color(4282441888L);
                BoxKt.Box(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(modifierFillMaxSize$default3, ai.nous.aurora.compat.Compat.m4492verticalGradient8A3gB4$default(companion3, new Pair[]{TuplesKt.to(Float.valueOf(0.28f), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion))), TuplesKt.to(Float.valueOf(0.36f), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(cyan, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(cyan) : g * 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(cyan) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(cyan) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(cyan) : 0.0f))), TuplesKt.to(fValueOf, ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jColor3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jColor3) : g * 0.14f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jColor3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jColor3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jColor3) : 0.0f))), TuplesKt.to(Float.valueOf(0.48f), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(violet, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(violet) : g * 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(violet) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(violet) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(violet) : 0.0f))), TuplesKt.to(Float.valueOf(0.55f), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion)))}, 0.0f, 0.0f, ai.nous.aurora.compat.Compat.m4907getClamp3opZhB0(TileMode.Companion), 6, (Object) null), null, 0.0f, 6, null), $composer2, 0);
                BoxKt.Box(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m4492verticalGradient8A3gB4$default(Brush.Companion, new Pair[]{TuplesKt.to(Float.valueOf(0.2f), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion))), TuplesKt.to(Float.valueOf(0.26f), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(cyan, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(cyan) : g * 0.08f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(cyan) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(cyan) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(cyan) : 0.0f))), TuplesKt.to(Float.valueOf(0.3f), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4532copywmQWz5c(nebula, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(nebula) : g * 0.07f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(nebula) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(nebula) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(nebula) : 0.0f))), TuplesKt.to(Float.valueOf(0.36f), ai.nous.aurora.compat.Compat.m4524boximpl(ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion)))}, 0.0f, 0.0f, ai.nous.aurora.compat.Compat.m4907getClamp3opZhB0(TileMode.Companion), 6, (Object) null), null, 0.0f, 6, null), $composer2, 0);
            }
            $composer2.endReplaceGroup();
            $composer2.startReplaceGroup(-811462983);
            ComposerKt.sourceInformation($composer2, "140@5243L3808,140@5201L3850");
            if (stars2) {
                Modifier modifierFillMaxSize$default4 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxSize_4(Modifier.Companion, 0.0f, 1, null);
                $composer2.startReplaceGroup(-811460916);
                ComposerKt.sourceInformation($composer2, "CC(remember):AuroraSky.kt#9igjgp");
                boolean invalid$iv = (($dirty & 7168) == 2048) | $composer2.changed(star) | $composer2.changed(cyan);
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.Companion.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.theme.AuroraSkyKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AuroraSkyKt.AuroraSky$lambda$2$lambda$1$lambda$0(starSeed3, star, cyan, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer2.endReplaceGroup();
                CanvasKt.Canvas(modifierFillMaxSize$default4, (Function1) value$iv, $composer2, 6);
            }
            $composer2.endReplaceGroup();
            function4.invoke($this$AuroraSky_u24lambda_u242, $composer2, Integer.valueOf(($changed2 & 14) | (($dirty >> 9) & 112)));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            starSeed2 = starSeed3;
            function3M247getLambda1$app_debug = function4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final float f2 = glow2;
            final boolean z = stars2;
            final int i8 = starSeed2;
            final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function5 = function3M247getLambda1$app_debug;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.theme.AuroraSkyKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraSkyKt.AuroraSky$lambda$3(modifier5, f2, z, i8, function5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraSky$lambda$2$lambda$1$lambda$0(int $starSeed, long $star, long $cyan, DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float w = ai.nous.aurora.compat.Compat.m4362getWidthimpl(ai.nous.aurora.compat.Compat.mo5090getSizeNHjbRc(Canvas));
        float h = ai.nous.aurora.compat.Compat.m4359getHeightimpl(ai.nous.aurora.compat.Compat.mo5090getSizeNHjbRc(Canvas));
        if (w <= 0.0f || h <= 0.0f) {
            return Unit.INSTANCE;
        }
        int count = RangesKt.coerceIn((int) ((w * h) / 12000.0f), 80, 280);
        List bright = new ArrayList();
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            }
            float hx = Math.abs(((i * 7349) + ($starSeed * 911)) % 104729) / 104729.0f;
            float hy = Math.abs(((i * 15299) + ($starSeed * 3571)) % 130363) / 130363.0f;
            float falloff = 1.0f - (0.35f * hy);
            boolean big = i % 7 == 0;
            float alpha = (big ? 0.72f : 0.32f) * falloff;
            float radius = big ? 2.2f : 1.2f;
            long c = OffsetKt.Offset(hx * w, hy * h);
            int i2 = i;
            List bright2 = bright;
            ai.nous.aurora.compat.Compat.m5071drawCircleVaOC9Bg$default(Canvas, ai.nous.aurora.compat.Compat.m4532copywmQWz5c($star, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl($star) : alpha, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl($star) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl($star) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl($star) : 0.0f), radius, c, 0.0f, null, null, 0, 120, null);
            if (big) {
                bright2.add(ai.nous.aurora.compat.Compat.m4282boximpl(c));
                ai.nous.aurora.compat.Compat.m5076drawLineNGM6Ib0$default(Canvas, ai.nous.aurora.compat.Compat.m4532copywmQWz5c($star, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl($star) : alpha * 0.5f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl($star) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl($star) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl($star) : 0.0f), OffsetKt.Offset(ai.nous.aurora.compat.Compat.m4293getXimpl(c) - (radius * 4.0f), ai.nous.aurora.compat.Compat.m4294getYimpl(c)), OffsetKt.Offset(ai.nous.aurora.compat.Compat.m4293getXimpl(c) + (radius * 4.0f), ai.nous.aurora.compat.Compat.m4294getYimpl(c)), 0.9f, 0, null, 0.0f, null, 0, 496, null);
                ai.nous.aurora.compat.Compat.m5076drawLineNGM6Ib0$default(Canvas, ai.nous.aurora.compat.Compat.m4532copywmQWz5c($star, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl($star) : alpha * 0.5f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl($star) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl($star) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl($star) : 0.0f), OffsetKt.Offset(ai.nous.aurora.compat.Compat.m4293getXimpl(c), ai.nous.aurora.compat.Compat.m4294getYimpl(c) - (radius * 4.0f)), OffsetKt.Offset(ai.nous.aurora.compat.Compat.m4293getXimpl(c), ai.nous.aurora.compat.Compat.m4294getYimpl(c) + (4.0f * radius)), 0.9f, 0, null, 0.0f, null, 0, 496, null);
                ai.nous.aurora.compat.Compat.m5071drawCircleVaOC9Bg$default(Canvas, ai.nous.aurora.compat.Compat.m4532copywmQWz5c($cyan, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl($cyan) : falloff * 0.08f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl($cyan) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl($cyan) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl($cyan) : 0.0f), radius * 5.0f, c, 0.0f, null, null, 0, 120, null);
            }
            i = i2 + 1;
            bright = bright2;
        }
        List bright3 = bright;
        if (bright3.size() >= 4) {
            int i3 = 5;
            List<Pair> links = CollectionsKt.listOf(new Pair[]{TuplesKt.to(0, 1), TuplesKt.to(1, 2), TuplesKt.to(2, 3), TuplesKt.to(0, 3), TuplesKt.to(1, 3)});
            for (Pair pair : links) {
                int a = ((Number) pair.component1()).intValue();
                int b = ((Number) pair.component2()).intValue();
                if (a < bright3.size() && b < bright3.size()) {
                    ai.nous.aurora.compat.Compat.m5076drawLineNGM6Ib0$default(Canvas, ai.nous.aurora.compat.Compat.m4532copywmQWz5c($star, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl($star) : 0.12f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl($star) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl($star) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl($star) : 0.0f), ((Offset) bright3.get(a)).getPackedValue(), ((Offset) bright3.get(b)).getPackedValue(), 1.0f, 0, null, 0.0f, null, 0, 496, null);
                }
            }
            int i4 = i3;
            if (bright3.size() >= 8) {
                for (Pair pair2 : CollectionsKt.listOf(new Pair[]{TuplesKt.to(4, Integer.valueOf(i4)), TuplesKt.to(Integer.valueOf(i4), 6), TuplesKt.to(6, 7), TuplesKt.to(4, 6)})) {
                    int a2 = ((Number) pair2.component1()).intValue();
                    int b2 = ((Number) pair2.component2()).intValue();
                    if (a2 < bright3.size() && b2 < bright3.size()) {
                        List bright4 = bright3;
                        ai.nous.aurora.compat.Compat.m5076drawLineNGM6Ib0$default(Canvas, ai.nous.aurora.compat.Compat.m4532copywmQWz5c($cyan, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl($cyan) : 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl($cyan) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl($cyan) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl($cyan) : 0.0f), ((Offset) bright4.get(a2)).getPackedValue(), ((Offset) bright4.get(b2)).getPackedValue(), 0.9f, 0, null, 0.0f, null, 0, 496, null);
                        bright3 = bright4;
                    }
                }
            }
        }
        float dustY = h * 0.38f;
        for (int i5 = 0; i5 < 40; i5++) {
            float t = i5 / 40.0f;
            float x = w * t;
            float wobble = ((float) Math.sin((6.28f * t) + $starSeed)) * h * 0.03f;
            ai.nous.aurora.compat.Compat.m5071drawCircleVaOC9Bg$default(Canvas, ai.nous.aurora.compat.Compat.m4532copywmQWz5c($star, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl($star) : 0.04f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl($star) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl($star) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl($star) : 0.0f), 8.0f + (Math.abs((float) Math.cos(12.0f * t)) * 10.0f), OffsetKt.Offset(x, dustY + wobble), 0.0f, null, null, 0, 120, null);
        }
        return Unit.INSTANCE;
    }

    public static final Brush getAuroraGoldGradient(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1314083724);
        ComposerKt.sourceInformation($composer, "C(<get-AuroraGoldGradient>)229@9270L11,229@9283L18,229@9303L15:AuroraSky.kt#li0cm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1314083724, $changed, -1, "ai.nous.aurora.presentation.theme.<get-AuroraGoldGradient> (AuroraSky.kt:228)");
        }
        Brush brushM4483horizontalGradient8A3gB4$default = ai.nous.aurora.compat.Compat.m4483horizontalGradient8A3gB4$default(Brush.Companion, CollectionsKt.listOf(new Color[]{ai.nous.aurora.compat.Compat.m4524boximpl(AuroraColorSchemeKt.getAuroraAmber($composer, 0)), ai.nous.aurora.compat.Compat.m4524boximpl(AuroraColorSchemeKt.getAuroraAccentStrong($composer, 0)), ai.nous.aurora.compat.Compat.m4524boximpl(AuroraColorSchemeKt.getAuroraAmberDeep($composer, 0))}), 0.0f, 0.0f, 0, 14, (Object) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return brushM4483horizontalGradient8A3gB4$default;
    }
}
