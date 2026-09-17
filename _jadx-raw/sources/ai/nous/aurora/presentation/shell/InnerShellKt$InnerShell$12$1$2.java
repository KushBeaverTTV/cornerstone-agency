package ai.nous.aurora.presentation.shell;

import ai.nous.aurora.MainViewModel;
import ai.nous.aurora.domain.model.ConnectionState;
import ai.nous.aurora.domain.model.OfflineHeaderPolicy;
import ai.nous.aurora.presentation.components.ds.AuroraDsKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
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
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: InnerShell.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class InnerShellKt$InnerShell$12$1$2 implements Function3<AnimatedVisibilityScope, Composer, Integer, Unit> {
    final /* synthetic */ Function0<Unit> $onRetryConnection;
    final /* synthetic */ MainViewModel.UiState $state;

    InnerShellKt$InnerShell$12$1$2(MainViewModel.UiState uiState, Function0<Unit> function0) {
        this.$state = uiState;
        this.$onRetryConnection = function0;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
        invoke(animatedVisibilityScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        String strTake;
        Object value$iv;
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C342@15841L3089:InnerShell.kt#m1syn");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-367788450, $changed, -1, "ai.nous.aurora.presentation.shell.InnerShell.<anonymous>.<anonymous>.<anonymous> (InnerShell.kt:342)");
        }
        MainViewModel.UiState uiState = this.$state;
        final Function0<Unit> function3 = this.$onRetryConnection;
        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
        Modifier modifier$iv = Modifier.INSTANCE;
        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
        int $changed$iv$iv = (0 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
        int i2 = ((0 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, 1819569973, "C348@16202L19,345@16045L2558,391@18839L14,387@18632L272:InnerShell.kt#m1syn");
        Modifier modifier$iv2 = PaddingKt.m1035paddingVpY3zN4(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer, 0), null, 2, null), AuroraSpacing.INSTANCE.m237getLgD9Ej5fM(), Dp.m7016constructorimpl(10));
        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
        MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
        int $changed$iv$iv2 = (384 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer, modifier$iv2);
        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
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
        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
        int i4 = ((384 >> 6) & 112) | 6;
        RowScope $this$invoke_u24lambda_u244_u24lambda_u243 = RowScopeInstance.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 1836277474, "C356@16686L13,352@16462L273,358@16768L40,359@16841L1241,379@18115L29,383@18442L42,381@18275L298:InnerShell.kt#m1syn");
        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(7)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraWarning($composer, 0), null, 2, null), $composer, 0);
        SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m238getMdD9Ej5fM()), $composer, 6);
        Modifier modifier$iv3 = RowScope.weight$default($this$invoke_u24lambda_u244_u24lambda_u243, Modifier.INSTANCE, 1.0f, false, 2, null);
        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
        Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
        Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
        int $i$f$Column = ((0 >> 3) & 14) | ((0 >> 3) & 112);
        MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer, $i$f$Column);
        int $changed$iv$iv3 = (0 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer, modifier$iv3);
        Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
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
        Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer);
        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
        }
        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
        int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
        ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
        int i6 = ((0 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, 242302968, "C362@17047L10,360@16918L310,373@17808L15,366@17265L783:InnerShell.kt#m1syn");
        TextKt.m3067Text4IGK_g(OfflineHeaderPolicy.INSTANCE.bannerTitle(), (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 131026);
        ConnectionState connection = uiState.getConnection();
        if (connection instanceof ConnectionState.Error) {
            ConnectionState connection2 = uiState.getConnection();
            Intrinsics.checkNotNull(connection2, "null cannot be cast to non-null type ai.nous.aurora.domain.model.ConnectionState.Error");
            strTake = StringsKt.take(((ConnectionState.Error) connection2).getMessage(), 90);
        } else {
            strTake = Intrinsics.areEqual(connection, ConnectionState.Connecting.INSTANCE) ? "Reconnecting to Hermes…" : "Showing cached threads when available";
        }
        TextKt.m3067Text4IGK_g(strTake, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
        final boolean reconnecting = uiState.getConnection() instanceof ConnectionState.Connecting;
        String str = reconnecting ? "Reconnecting…" : "Retry connection";
        $composer.startReplaceGroup(-79251284);
        ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
        boolean invalid$iv = $composer.changed(reconnecting) | $composer.changed(function3);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$InnerShell$12$1$2$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return InnerShellKt$InnerShell$12$1$2.invoke$lambda$4$lambda$3$lambda$2$lambda$1(reconnecting, function3);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        $composer.endReplaceGroup();
        AuroraDsKt.AuroraPillAction(str, (Function0) value$iv, null, true, $composer, 3072, 4);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierM1065height3ABfNKs = SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1));
        long auroraHairline = AuroraColorSchemeKt.getAuroraHairline($composer, 0);
        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierM1065height3ABfNKs, Color.m4532copywmQWz5c(auroraHairline, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraHairline) : 0.7f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraHairline) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraHairline) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraHairline) : 0.0f), null, 2, null), $composer, 0);
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
    public static final Unit invoke$lambda$4$lambda$3$lambda$2$lambda$1(boolean $reconnecting, Function0 $onRetryConnection) {
        if (!$reconnecting) {
            $onRetryConnection.invoke();
        }
        return Unit.INSTANCE;
    }
}
