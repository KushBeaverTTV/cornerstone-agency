package ai.nous.aurora.presentation.components;

import ai.nous.aurora.presentation.theme.ThemeTokens;
import ai.nous.aurora.presentation.theme.ThemeTokensKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.unit.Dp;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SourceBadge.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a/\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a+\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"sourceVisual", "Lai/nous/aurora/presentation/components/SourceVisual;", "key", "", "sourceBadgeTint", "Landroidx/compose/ui/graphics/Color;", "sourceKey", "declaredTint", "astral", "", "accent", "sourceBadgeTint-3bbok98", "(Ljava/lang/String;JZJ)J", "SourceBadge", "", "modifier", "Landroidx/compose/ui/Modifier;", "size", "Landroidx/compose/ui/unit/Dp;", "SourceBadge-6a0pyJM", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;FLandroidx/compose/runtime/Composer;II)V", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SourceBadgeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SourceBadge_6a0pyJM$lambda$3(String str, Modifier modifier, float f, int i, int i2, Composer composer, int i3) {
        m114SourceBadge6a0pyJM(str, modifier, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:49:0x0100  */
    /* JADX WARN: Code duplicated, block: B:50:0x0102  */
    /* JADX WARN: Code duplicated, block: B:52:0x0105  */
    /* JADX WARN: Code duplicated, block: B:53:0x012c  */
    /* JADX WARN: Code duplicated, block: B:56:0x0135  */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0039, code lost:
    
        if (r0.equals("desktop") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0042, code lost:
    
        if (r0.equals("tools") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004b, code lost:
    
        if (r0.equals("tool") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0065, code lost:
    
        if (r0.equals("web") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006e, code lost:
    
        if (r0.equals("tui") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0077, code lost:
    
        if (r0.equals("cli") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0091, code lost:
    
        if (r0.equals("api") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009a, code lost:
    
        if (r0.equals("dashboard") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b5, code lost:
    
        if (r0.equals("mobile") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00da, code lost:
    
        if (r0.equals("aurora") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        return new ai.nous.aurora.presentation.components.SourceVisual(com.cornerstone.agency.R.drawable.ic_source_generic, ai.nous.aurora.presentation.theme.AuroraColorSchemeKt.getSourceTool(), "Tool", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
    
        return new ai.nous.aurora.presentation.components.SourceVisual(com.cornerstone.agency.R.drawable.ic_source_cli, ai.nous.aurora.presentation.theme.AuroraColorSchemeKt.getSourceCli(), "CLI", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:?, code lost:
    
        return new ai.nous.aurora.presentation.components.SourceVisual(com.cornerstone.agency.R.drawable.ic_source_desktop, ai.nous.aurora.presentation.theme.AuroraColorSchemeKt.getSourceDesktop(), "Desktop", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:?, code lost:
    
        return new ai.nous.aurora.presentation.components.SourceVisual(com.cornerstone.agency.R.drawable.ic_source_aurora, ai.nous.aurora.presentation.theme.AuroraColorSchemeKt.getSourceAurora(), "Agency", null);
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final ai.nous.aurora.presentation.components.SourceVisual sourceVisual(java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.presentation.components.SourceBadgeKt.sourceVisual(java.lang.String):ai.nous.aurora.presentation.components.SourceVisual");
    }

    /* JADX INFO: renamed from: sourceBadgeTint-3bbok98, reason: not valid java name */
    public static final long m115sourceBadgeTint3bbok98(String sourceKey, long declaredTint, boolean astral, long accent) {
        Intrinsics.checkNotNullParameter(sourceKey, "sourceKey");
        Set of = SetsKt.setOf((Object[]) new String[]{"aurora", "api", "mobile"});
        String lowerCase = sourceKey.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        boolean auroraOwned = of.contains(lowerCase);
        return (auroraOwned && astral) ? accent : declaredTint;
    }

    /* JADX WARN: Code duplicated, block: B:66:0x0282  */
    /* JADX INFO: renamed from: SourceBadge-6a0pyJM, reason: not valid java name */
    public static final void m114SourceBadge6a0pyJM(final String sourceKey, Modifier modifier, float size, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        float size2;
        Function0<ComposeUiNode> function0;
        Modifier modifier3;
        boolean propagateMinConstraints$iv;
        Intrinsics.checkNotNullParameter(sourceKey, "sourceKey");
        Composer $composer2 = $composer.startRestartGroup(542514253);
        ComposerKt.sourceInformation($composer2, "C(SourceBadge)P(2!,1:c#ui.unit.Dp)63@2426L7,68@2720L439:SourceBadge.kt#9jzrut");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(sourceKey) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            f = size;
        } else if (($changed & 384) == 0) {
            f = size;
            $dirty |= $composer2.changed(f) ? 256 : 128;
        } else {
            f = size;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            size2 = f;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.Companion : modifier2;
            size2 = i3 != 0 ? ai.nous.aurora.compat.Compat.m7016constructorimpl(28) : f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(542514253, $dirty2, -1, "ai.nous.aurora.presentation.components.SourceBadge (SourceBadge.kt:61)");
            }
            SourceVisual v = sourceVisual(sourceKey);
            ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localThemeTokens);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ThemeTokens tokens = (ThemeTokens) objConsume;
            long tint = m115sourceBadgeTint3bbok98(sourceKey, v.m119getTintBg0d7_KjU(), tokens.getAstral(), tokens.m308getAccent0d7_KjU());
            float other$iv = ai.nous.aurora.compat.Compat.m7016constructorimpl(size2 * 0.32f);
            RoundedCornerShape shape = ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(other$iv);
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(modifier4, size2), shape), ai.nous.aurora.compat.Compat.m4532copywmQWz5c(tint, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(tint) : 0.26f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(tint) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(tint) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(tint) : 0.0f), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), ai.nous.aurora.compat.Compat.m4532copywmQWz5c(tint, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(tint) : 0.5f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(tint) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(tint) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(tint) : 0.0f), shape);
            Alignment contentAlignment$iv = Alignment.Companion.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
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
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                modifier3 = modifier4;
            } else {
                modifier3 = modifier4;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i4 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i5 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1546139632, "C77@3000L23,76@2972L181:SourceBadge.kt#9jzrut");
                ai.nous.aurora.compat.Compat.m2523Iconww6aTOc(PainterResources_androidKt.painterResource(v.getIcon(), $composer2, 0), v.getLabel(), ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(size2 * 0.55f)), tint, $composer2, 0, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                propagateMinConstraints$iv = ComposerKt.isTraceInProgress();
                if (propagateMinConstraints$iv) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i6 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i7 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1546139632, "C77@3000L23,76@2972L181:SourceBadge.kt#9jzrut");
            ai.nous.aurora.compat.Compat.m2523Iconww6aTOc(PainterResources_androidKt.painterResource(v.getIcon(), $composer2, 0), v.getLabel(), ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(size2 * 0.55f)), tint, $composer2, 0, 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            propagateMinConstraints$iv = ComposerKt.isTraceInProgress();
            if (propagateMinConstraints$iv) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final float f2 = size2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.SourceBadgeKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SourceBadgeKt.SourceBadge_6a0pyJM$lambda$3(sourceKey, modifier5, f2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
