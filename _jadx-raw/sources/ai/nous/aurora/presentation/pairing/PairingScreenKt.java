package ai.nous.aurora.presentation.pairing;

import ai.nous.aurora.BuildConfig;
import ai.nous.aurora.MainViewModel;
import ai.nous.aurora.presentation.components.AuroraComponentsKt;
import ai.nous.aurora.presentation.components.SourceBadgeKt;
import ai.nous.aurora.presentation.theme.AuroraBackdropKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import ai.nous.aurora.presentation.theme.AuroraTypographyKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ExpandLessKt;
import androidx.compose.material.icons.filled.ExpandMoreKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
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
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PairingScreen.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0012\u001ac\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\r\u001a\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002\u001a#\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010\u0014\u001a'\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0012H\u0003¢\u0006\u0002\u0010\u0019\u001a%\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0006H\u0003¢\u0006\u0002\u0010\u001e\u001a\r\u0010\u001f\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010 \u001a\u0017\u0010!\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0003¢\u0006\u0002\u0010\"¨\u0006#²\u0006\n\u0010$\u001a\u00020\u0012X\u008a\u008e\u0002"}, d2 = {"PairingScreen", "", "state", "Lai/nous/aurora/MainViewModel$UiState;", "onGatewayChange", "Lkotlin/Function1;", "", "onApiKeyChange", "onConnect", "Lkotlin/Function0;", "onRestorePreconfig", "modifier", "Landroidx/compose/ui/Modifier;", "(Lai/nous/aurora/MainViewModel$UiState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "credentialTail", "secret", "AdvancedDisclosure", "open", "", "onToggle", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "RowLabel", "left", "right", "rightLive", "(Ljava/lang/String;Ljava/lang/String;ZLandroidx/compose/runtime/Composer;II)V", "PairingStep", "number", "title", "body", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "PairingStepDivider", "(Landroidx/compose/runtime/Composer;I)V", "ConnectingSplash", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_debug", "advancedOpen"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PairingScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AdvancedDisclosure$lambda$6(boolean z, Function0 function0, int i, Composer composer, int i2) {
        AdvancedDisclosure(z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ConnectingSplash$lambda$15(Modifier modifier, int i, int i2, Composer composer, int i3) {
        ConnectingSplash(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PairingScreen$lambda$0(MainViewModel.UiState uiState, Function1 function1, Function1 function2, Function0 function0, Function0 function3, Modifier modifier, int i, int i2, Composer composer, int i3) {
        PairingScreen(uiState, function1, function2, function0, function3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PairingScreen$lambda$4(MainViewModel.UiState uiState, Function1 function1, Function1 function2, Function0 function0, Function0 function3, Modifier modifier, int i, int i2, Composer composer, int i3) {
        PairingScreen(uiState, function1, function2, function0, function3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PairingStep$lambda$12(String str, String str2, String str3, int i, Composer composer, int i2) {
        PairingStep(str, str2, str3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PairingStepDivider$lambda$13(int i, Composer composer, int i2) {
        PairingStepDivider(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RowLabel$lambda$8(String str, String str2, boolean z, int i, int i2, Composer composer, int i3) {
        RowLabel(str, str2, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void PairingScreen(final MainViewModel.UiState state, final Function1<? super String, Unit> onGatewayChange, final Function1<? super String, Unit> onApiKeyChange, final Function0<Unit> onConnect, final Function0<Unit> onRestorePreconfig, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Object value$iv;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onGatewayChange, "onGatewayChange");
        Intrinsics.checkNotNullParameter(onApiKeyChange, "onApiKeyChange");
        Intrinsics.checkNotNullParameter(onConnect, "onConnect");
        Intrinsics.checkNotNullParameter(onRestorePreconfig, "onRestorePreconfig");
        Composer $composer2 = $composer.startRestartGroup(-263217051);
        ComposerKt.sourceInformation($composer2, "C(PairingScreen)P(5,3,1,2,4)71@3100L34,76@3378L5091,76@3328L5141:PairingScreen.kt#3dn6gp");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(onGatewayChange) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(onApiKeyChange) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(onConnect) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(onRestorePreconfig) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if ((196608 & $changed) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-263217051, $dirty2, -1, "ai.nous.aurora.presentation.pairing.PairingScreen (PairingScreen.kt:64)");
            }
            $composer2.startReplaceGroup(-955839878);
            ComposerKt.sourceInformation($composer2, "66@2979L37");
            if (state.getConnectingSplash() || state.getConnectState().getBusy()) {
                final Modifier modifier5 = modifier4;
                ConnectingSplash(modifier5, $composer2, ($dirty2 >> 15) & 14, 0);
                $composer2.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.pairing.PairingScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return PairingScreenKt.PairingScreen$lambda$0(state, onGatewayChange, onApiKeyChange, onConnect, onRestorePreconfig, modifier5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            $composer2.endReplaceGroup();
            final MainViewModel.ConnectUiState connect = state.getConnectState();
            $composer2.startReplaceGroup(-955834015);
            ComposerKt.sourceInformation($composer2, "CC(remember):PairingScreen.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState advancedOpen$delegate = (MutableState) value$iv;
            $composer2.endReplaceGroup();
            final boolean keyEntered = !StringsKt.isBlank(connect.getApiKey());
            modifier3 = modifier4;
            AuroraBackdropKt.AuroraBackdrop(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null), 0, ComposableLambdaKt.rememberComposableLambda(-1442783071, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.pairing.PairingScreenKt.PairingScreen.2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer, Integer num) {
                    invoke(boxScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(BoxScope AuroraBackdrop, Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    Intrinsics.checkNotNullParameter(AuroraBackdrop, "$this$AuroraBackdrop");
                    ComposerKt.sourceInformation($composer3, "C80@3491L21,77@3388L5075:PairingScreen.kt#3dn6gp");
                    if (($changed2 & 17) != 16 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1442783071, $changed2, -1, "ai.nous.aurora.presentation.pairing.PairingScreen.<anonymous> (PairingScreen.kt:77)");
                        }
                        Modifier modifier$iv = PaddingKt.m1035paddingVpY3zN4(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null), Dp.m7016constructorimpl(22), Dp.m7016constructorimpl(14));
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(12));
                        MainViewModel.ConnectUiState connectUiState = connect;
                        Function1<String, Unit> function1 = onApiKeyChange;
                        Function0<Unit> function2 = onConnect;
                        Function1<String, Unit> function3 = onGatewayChange;
                        boolean z = keyEntered;
                        Function0<Unit> function4 = onRestorePreconfig;
                        MutableState<Boolean> mutableState = advancedOpen$delegate;
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                        int $changed$iv$iv = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer3);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i3 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        int i4 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1107752722, "C84@3665L40,85@3718L47,86@3778L38,87@3872L13,87@3829L57,90@4012L15,88@3899L143,93@4056L40,95@4145L2616,95@4110L2651,167@7056L57,168@7126L805,188@7945L52,193@8204L10,194@8240L15,191@8089L310,199@8413L40:PairingScreen.kt#3dn6gp");
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(2)), $composer3, 6);
                        SourceBadgeKt.m114SourceBadge6a0pyJM("aurora", null, Dp.m7016constructorimpl(64), $composer3, 390, 2);
                        AuroraComponentsKt.AuroraKicker("Aurora × Hermes", null, $composer3, 6, 2);
                        TextKt.m3067Text4IGK_g("Your agent,\non your phone", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraDisplay($composer3, 0), $composer3, 6, 0, 65534);
                        TextKt.m3067Text4IGK_g("Private control surface for hermes-hostinger.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBodyMuted($composer3, 0), $composer3, 0, 0, 65534);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(2)), $composer3, 6);
                        AuroraComponentsKt.m109AuroraCard942rkJo(null, null, Dp.m7016constructorimpl(14), ComposableLambdaKt.rememberComposableLambda(271765563, true, new PairingScreenKt$PairingScreen$2$1$1(connectUiState, function1, function2, function3, z, function4, mutableState), $composer3, 54), $composer3, 3456, 3);
                        String it = connectUiState.getError();
                        $composer3.startReplaceGroup(-1765286050);
                        ComposerKt.sourceInformation($composer3, "*162@6836L11,162@6857L15,162@6812L61");
                        if (it != null) {
                            TextKt.m3067Text4IGK_g(it, (Modifier) null, AuroraColorSchemeKt.getAuroraError($composer3, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBodyMuted($composer3, 0), $composer3, 0, 0, 65530);
                        }
                        $composer3.endReplaceGroup();
                        AuroraComponentsKt.AuroraKicker("First pairing takes about a minute", null, $composer3, 6, 2);
                        AuroraComponentsKt.m109AuroraCard942rkJo(null, null, Dp.m7016constructorimpl(0), ComposableSingletons$PairingScreenKt.INSTANCE.m130getLambda1$app_debug(), $composer3, 3456, 3);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m242getXsD9Ej5fM()), $composer3, 6);
                        TextKt.m3067Text4IGK_g("No account  ·  No cloud relay  ·  Keys stay on this device", SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMeta($composer3, 0), $composer3, 54, 0, 65016);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer3, 6);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            final Modifier modifier6 = modifier3;
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.pairing.PairingScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PairingScreenKt.PairingScreen$lambda$4(state, onGatewayChange, onApiKeyChange, onConnect, onRestorePreconfig, modifier6, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PairingScreen$lambda$2(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PairingScreen$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String credentialTail(String secret) {
        String tail = StringsKt.takeLast(secret, 4);
        if (tail.length() == 0) {
            return "";
        }
        if (tail.length() <= 4) {
            return "••••" + tail;
        }
        return "••" + StringsKt.takeLast(tail, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AdvancedDisclosure(final boolean open, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function1;
        Composer $composer2 = $composer.startRestartGroup(1928454354);
        ComposerKt.sourceInformation($composer2, "C(AdvancedDisclosure)P(1)219@8876L790:PairingScreen.kt#3dn6gp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(open) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1928454354, $dirty2, -1, "ai.nous.aurora.presentation.pairing.AdvancedDisclosure (PairingScreen.kt:218)");
            }
            Modifier modifier$iv = PaddingKt.m1036paddingVpY3zN4$default(ClickableKt.m622clickableXHw0xAI$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(10))), false, null, null, function0, 7, null), 0.0f, Dp.m7016constructorimpl(8), 1, null);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function1 = constructor;
                $composer2.createNode(function1);
            } else {
                function1 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 896498128, "C230@9342L10,231@9374L15,228@9228L172,236@9589L15,233@9409L251:PairingScreen.kt#3dn6gp");
            TextKt.m3067Text4IGK_g(open ? "Connection details" : "Advanced / connection details", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer2, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMeta($composer2, 0), $composer2, 0, 0, 65530);
            Icons.Filled filled = Icons.INSTANCE.getDefault();
            IconKt.m2524Iconww6aTOc(open ? ExpandLessKt.getExpandLess(filled) : ExpandMoreKt.getExpandMore(filled), open ? "Hide" : "Show", SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(18)), AuroraColorSchemeKt.getAuroraTextMuted($composer2, 0), $composer2, 384, 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.pairing.PairingScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PairingScreenKt.AdvancedDisclosure$lambda$6(open, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RowLabel(final String left, final String right, boolean rightLive, Composer $composer, final int $changed, final int i) {
        boolean z;
        long auroraAmber;
        boolean rightLive2;
        Composer $composer2 = $composer.startRestartGroup(-145860525);
        ComposerKt.sourceInformation($composer2, "C(RowLabel)244@9800L276:PairingScreen.kt#3dn6gp");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(left) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(right) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            z = rightLive;
        } else if (($changed & 384) == 0) {
            z = rightLive;
            $dirty |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = rightLive;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            boolean rightLive3 = i2 != 0 ? true : z;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-145860525, $dirty, -1, "ai.nous.aurora.presentation.pairing.RowLabel (PairingScreen.kt:243)");
            }
            Modifier modifier$iv = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            int $changed$iv$iv = (54 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i4 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1864269122, "C248@9942L10,248@9962L15,248@9923L55,249@10007L10,249@9987L83:PairingScreen.kt#3dn6gp");
            TextKt.m3067Text4IGK_g(left, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer2, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMeta($composer2, 0), $composer2, $dirty & 14, 0, 65530);
            TextStyle auroraMeta = AuroraTypographyKt.getAuroraMeta($composer2, 0);
            if (rightLive3) {
                $composer2.startReplaceGroup(-494047944);
                ComposerKt.sourceInformation($composer2, "249@10042L10");
                auroraAmber = AuroraColorSchemeKt.getAuroraLive($composer2, 0);
            } else {
                $composer2.startReplaceGroup(-494047431);
                ComposerKt.sourceInformation($composer2, "249@10058L11");
                auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer2, 0);
            }
            $composer2.endReplaceGroup();
            TextKt.m3067Text4IGK_g(right, (Modifier) null, auroraAmber, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, auroraMeta, $composer2, ($dirty >> 3) & 14, 0, 65530);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            rightLive2 = rightLive3;
        } else {
            $composer2.skipToGroupEnd();
            rightLive2 = z;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z2 = rightLive2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.pairing.PairingScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PairingScreenKt.RowLabel$lambda$8(left, right, z2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:63:0x03f5  */
    /* JADX WARN: Code duplicated, block: B:66:0x0401  */
    /* JADX WARN: Code duplicated, block: B:67:0x0405  */
    /* JADX WARN: Code duplicated, block: B:72:0x043c  */
    /* JADX WARN: Code duplicated, block: B:75:0x0555  */
    public static final void PairingStep(final String number, final String title, final String body, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        CompositionLocalMap localMap$iv$iv;
        Composer $composer2;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        Composer $composer3 = $composer.startRestartGroup(817365032);
        ComposerKt.sourceInformation($composer3, "C(PairingStep)P(1,2)256@10252L1184:PairingScreen.kt#3dn6gp");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(number) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(title) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(body) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(817365032, $dirty2, -1, "ai.nous.aurora.presentation.pairing.PairingStep (PairingScreen.kt:255)");
            }
            Modifier modifierM1036paddingVpY3zN4$default = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7016constructorimpl(6), 1, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM914spacedBy0680j_4 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(14));
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalOrVerticalM914spacedBy0680j_4, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv = (438 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifierM1036paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
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
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1092977460, "C267@10662L12,270@10767L12,263@10485L669,282@11163L267:PairingScreen.kt#3dn6gp");
            Modifier modifierClip = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(34)), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(10)));
            long auroraAccent = AuroraColorSchemeKt.getAuroraAccent($composer3, 0);
            Modifier modifierM589backgroundbw27NRU$default = BackgroundKt.m589backgroundbw27NRU$default(modifierClip, Color.m4532copywmQWz5c(auroraAccent, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAccent) : 0.14f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAccent) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAccent) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAccent) : 0.0f), null, 2, null);
            float fM7016constructorimpl = Dp.m7016constructorimpl(1);
            long auroraAccent2 = AuroraColorSchemeKt.getAuroraAccent($composer3, 0);
            Modifier modifier$iv = BorderKt.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default, fM7016constructorimpl, Color.m4532copywmQWz5c(auroraAccent2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAccent2) : 0.35f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAccent2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAccent2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAccent2) : 0.0f), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(10)));
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor3;
                $composer3.createNode(function0);
            } else {
                function0 = constructor3;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer3);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                localMap$iv$iv = localMap$iv$iv3;
            } else {
                localMap$iv$iv = localMap$iv$iv3;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -521959652, "C277@11035L10,278@11071L12,275@10974L170:PairingScreen.kt#3dn6gp");
                $composer2 = $composer3;
                TextKt.m3067Text4IGK_g(number, (Modifier) null, AuroraColorSchemeKt.getAuroraAccent($composer3, 0), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMeta($composer3, 0), $composer3, ($dirty2 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 65498);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifier$iv2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                int $changed$iv$iv3 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
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
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer3);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -521714690, "C283@11247L10,283@11267L10,283@11220L92,284@11325L40,285@11404L15,285@11378L42:PairingScreen.kt#3dn6gp");
                TextKt.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBody($composer3, 0), $composer3, (($dirty2 >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 65498);
                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(2)), $composer3, 6);
                TextKt.m3067Text4IGK_g(body, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBodyMuted($composer3, 0), $composer3, ($dirty2 >> 6) & 14, 0, 65534);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
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
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i8 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -521959652, "C277@11035L10,278@11071L12,275@10974L170:PairingScreen.kt#3dn6gp");
            $composer2 = $composer3;
            TextKt.m3067Text4IGK_g(number, (Modifier) null, AuroraColorSchemeKt.getAuroraAccent($composer3, 0), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMeta($composer3, 0), $composer3, ($dirty2 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 65498);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv4 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
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
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer3);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            } else {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i10 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -521714690, "C283@11247L10,283@11267L10,283@11220L92,284@11325L40,285@11404L15,285@11378L42:PairingScreen.kt#3dn6gp");
            TextKt.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBody($composer3, 0), $composer3, (($dirty2 >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 65498);
            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(2)), $composer3, 6);
            TextKt.m3067Text4IGK_g(body, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBodyMuted($composer3, 0), $composer3, ($dirty2 >> 6) & 14, 0, 65534);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.pairing.PairingScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PairingScreenKt.PairingStep$lambda$12(number, title, body, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PairingStepDivider(Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1026163258);
        ComposerKt.sourceInformation($composer2, "C(PairingStepDivider)297@11672L14,292@11491L203:PairingScreen.kt#3dn6gp");
        if ($changed != 0 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1026163258, $changed, -1, "ai.nous.aurora.presentation.pairing.PairingStepDivider (PairingScreen.kt:291)");
            }
            BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(PaddingKt.m1038paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(48), 0.0f, 0.0f, 0.0f, 14, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer2, 0), null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.pairing.PairingScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PairingScreenKt.PairingStepDivider$lambda$13($changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ConnectingSplash(Modifier modifier, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(-851455639);
        ComposerKt.sourceInformation($composer2, "C(ConnectingSplash)306@11863L10,303@11776L618:PairingScreen.kt#3dn6gp");
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
        if (($dirty & 3) != 2 || !$composer2.getSkipping()) {
            Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-851455639, $dirty, -1, "ai.nous.aurora.presentation.pairing.ConnectingSplash (PairingScreen.kt:302)");
            }
            Modifier modifier$iv = PaddingKt.m1034padding3ABfNKs(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), AuroraColorSchemeKt.getAuroraVoid($composer2, 0), null, 2, null), Dp.m7016constructorimpl(32));
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getCenter();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Modifier modifier4 = modifier3;
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
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i4 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1943779274, "C311@12064L12,311@12030L67,312@12106L41,313@12194L10,313@12214L10,313@12156L103,314@12314L10,314@12334L12,314@12268L120:PairingScreen.kt#3dn6gp");
            ProgressIndicatorKt.m2741CircularProgressIndicatorLxG7B9w(null, AuroraColorSchemeKt.getAuroraAccent($composer2, 0), Dp.m7016constructorimpl(2), 0L, 0, $composer2, 384, 25);
            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(18)), $composer2, 6);
            TextKt.m3067Text4IGK_g("Connecting to Hermes…", (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer2, 0), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBody($composer2, 0), $composer2, 196614, 0, 65498);
            TextKt.m3067Text4IGK_g(BuildConfig.HERMES_DISPLAY_NAME, PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7016constructorimpl(8), 0.0f, 0.0f, 13, null), AuroraColorSchemeKt.getAuroraAccent($composer2, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMeta($composer2, 0), $composer2, 54, 0, 65528);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.pairing.PairingScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PairingScreenKt.ConnectingSplash$lambda$15(modifier2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
