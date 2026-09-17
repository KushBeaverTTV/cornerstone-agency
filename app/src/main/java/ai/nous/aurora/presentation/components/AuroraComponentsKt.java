package ai.nous.aurora.presentation.components;

import ai.nous.aurora.domain.model.AstralReadabilityPolicy;
import ai.nous.aurora.domain.model.OutboxExactlyOnce;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraShapeKt;
import ai.nous.aurora.presentation.theme.AuroraSkyKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import ai.nous.aurora.presentation.theme.AuroraTypographyKt;
import ai.nous.aurora.presentation.theme.ThemeTokens;
import ai.nous.aurora.presentation.theme.ThemeTokensKt;
import android.content.Context;
import android.provider.Settings;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
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
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.selection.SelectionContainerKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AuroraComponents.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\u001aA\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u000b\u001aS\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u008a\u0001\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00032\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00102\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u0015\b\u0002\u0010 \u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010!\u001a\u001f\u0010\"\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010#\u001a'\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010&\u001aC\u0010'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020\u00032\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010+\u001a\u001f\u0010,\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010#\u001a\u001f\u0010-\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010#¨\u0006/²\u0006\n\u00100\u001a\u000201X\u008a\u008e\u0002"}, d2 = {"AuroraButton", "", "text", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "primary", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/runtime/Composer;II)V", "AuroraCard", "contentSpacing", "Landroidx/compose/ui/unit/Dp;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/runtime/Composable;", "AuroraCard-942rkJo", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;FLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AuroraTextField", "value", "onValueChange", "label", "singleLine", "placeholder", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "trailingIcon", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Ljava/lang/String;ZZLjava/lang/String;Landroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "AuroraKicker", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "ConnectionChip", "online", "(ZLjava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "MessageBubble", "role", "deliveryStatus", "onRetry", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "LoadingRow", "ThinkingRow", "detail", "app_debug", "dots", ""}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AuroraComponentsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraButton$lambda$0(String str, Function0 function0, Modifier modifier, boolean z, boolean z2, int i, int i2, Composer composer, int i3) {
        AuroraButton(str, function0, modifier, z, z2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraCard_942rkJo$lambda$1(Modifier modifier, Function0 function0, float f, Function3 function3, int i, int i2, Composer composer, int i3) {
        m109AuroraCard942rkJo(modifier, function0, f, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraKicker$lambda$5(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AuroraKicker(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraTextField$lambda$4(String str, Function1 function1, Modifier modifier, String str2, boolean z, boolean z2, String str3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, Function2 function2, int i, int i2, Composer composer, int i3) {
        AuroraTextField(str, function1, modifier, str2, z, z2, str3, visualTransformation, keyboardOptions, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ConnectionChip$lambda$7(boolean z, String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        ConnectionChip(z, str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LoadingRow$lambda$23(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        LoadingRow(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MessageBubble$lambda$18(String str, String str2, Modifier modifier, String str3, Function0 function0, int i, int i2, Composer composer, int i3) {
        MessageBubble(str, str2, modifier, str3, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ThinkingRow$lambda$37(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        ThinkingRow(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void AuroraButton(final String text, final Function0<Unit> onClick, Modifier modifier, boolean enabled, boolean primary, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        boolean primary2;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer2 = $composer.startRestartGroup(-11714399);
        ComposerKt.sourceInformation($composer2, "C(AuroraButton)P(4,2,1):AuroraComponents.kt#9jzrut");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(text) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(onClick) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            primary2 = primary;
        } else if (($changed & 24576) == 0) {
            primary2 = primary;
            $dirty |= $composer2.changed(primary2) ? 16384 : 8192;
        } else {
            primary2 = primary;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.Companion : modifier2;
            final boolean enabled3 = i3 != 0 ? true : enabled2;
            boolean primary3 = i4 != 0 ? true : primary2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-11714399, $dirty, -1, "ai.nous.aurora.presentation.components.AuroraButton (AuroraComponents.kt:92)");
            }
            if (primary3) {
                $composer2.startReplaceGroup(1355021078);
                ComposerKt.sourceInformation($composer2, "101@4659L14,102@4716L14,103@4771L15,99@4562L239,106@4863L848,94@4366L1345");
                ButtonKt.Button(onClick, ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(modifier4, ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null), enabled3, AuroraShapeKt.getAuroraShapeMd(), ai.nous.aurora.compat.Compat.m2183buttonColorsro_MJ88(ButtonDefaults.INSTANCE, ai.nous.aurora.compat.Compat.m4569getTransparent0d7_KjU(Color.Companion), AuroraColorSchemeKt.getAuroraOnAccent($composer2, 0), AuroraColorSchemeKt.getAuroraHairline($composer2, 0), AuroraColorSchemeKt.getAuroraTextFaint($composer2, 0), $composer2, (ButtonDefaults.$stable << 12) | 6, 0), null, null, ai.nous.aurora.compat.Compat.m1027PaddingValues0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(0)), null, (Function3) ComposableLambdaKt.rememberComposableLambda(1654527404, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt.AuroraButton.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                        invoke(rowScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(RowScope Button, Composer $composer3, int $changed2) {
                        Brush brush;
                        Intrinsics.checkNotNullParameter(Button, "$this$Button");
                        ComposerKt.sourceInformation($composer3, "C107@4877L824:AuroraComponents.kt#9jzrut");
                        if (($changed2 & 17) != 16 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1654527404, $changed2, -1, "ai.nous.aurora.presentation.components.AuroraButton.<anonymous> (AuroraComponents.kt:107)");
                            }
                            Modifier modifierFillMaxWidth$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null);
                            if (enabled3) {
                                $composer3.startReplaceGroup(-1376277099);
                                ComposerKt.sourceInformation($composer3, "113@5127L12,113@5192L7");
                                Brush.Companion companion = Brush.Companion;
                                ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                Object objConsume = $composer3.consume(localThemeTokens);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Brush brushM4483horizontalGradient8A3gB4$default = ai.nous.aurora.compat.Compat.m4483horizontalGradient8A3gB4$default(companion, CollectionsKt.listOf(new Color[]{ai.nous.aurora.compat.Compat.m4524boximpl(AuroraColorSchemeKt.getAuroraAccent($composer3, 0)), ai.nous.aurora.compat.Compat.m4524boximpl(((ThemeTokens) objConsume).m316getButtonGradientEnd0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null);
                                $composer3.endReplaceGroup();
                                brush = brushM4483horizontalGradient8A3gB4$default;
                            } else {
                                $composer3.startReplaceGroup(-1376033904);
                                ComposerKt.sourceInformation($composer3, "116@5343L14,116@5359L14");
                                Brush brushM4483horizontalGradient8A3gB4$default2 = ai.nous.aurora.compat.Compat.m4483horizontalGradient8A3gB4$default(Brush.Companion, CollectionsKt.listOf(new Color[]{ai.nous.aurora.compat.Compat.m4524boximpl(AuroraColorSchemeKt.getAuroraHairline($composer3, 0)), ai.nous.aurora.compat.Compat.m4524boximpl(AuroraColorSchemeKt.getAuroraHairline($composer3, 0))}), 0.0f, 0.0f, 0, 14, (Object) null);
                                $composer3.endReplaceGroup();
                                brush = brushM4483horizontalGradient8A3gB4$default2;
                            }
                            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(modifierFillMaxWidth$default, brush, AuroraShapeKt.getAuroraShapeMd(), 0.0f, 4, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(18), ai.nous.aurora.compat.Compat.m7016constructorimpl(14));
                            Alignment contentAlignment$iv = Alignment.Companion.getCenter();
                            String str = text;
                            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv = (48 << 3) & 112;
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
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            }
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                            int i5 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i6 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -171768212, "C123@5625L62:AuroraComponents.kt#9jzrut");
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str, (Modifier) null, 0L, TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199680, 0, 131030);
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
                }, $composer2, 54), $composer2, (($dirty >> 3) & 14) | 817892352 | (($dirty >> 3) & 896), 352);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1356341399);
                ComposerKt.sourceInformation($composer2, "127@5803L122,127@5733L192");
                ButtonKt.TextButton(onClick, modifier4, enabled3, null, null, null, null, null, null, (Function3) ComposableLambdaKt.rememberComposableLambda(-1990548542, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt.AuroraButton.2
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                        invoke(rowScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(RowScope TextButton, Composer $composer3, int $changed2) {
                        long auroraTextFaint;
                        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
                        ComposerKt.sourceInformation($composer3, "C128@5817L98:AuroraComponents.kt#9jzrut");
                        if (($changed2 & 17) == 16 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1990548542, $changed2, -1, "ai.nous.aurora.presentation.components.AuroraButton.<anonymous> (AuroraComponents.kt:128)");
                        }
                        String str = text;
                        if (enabled3) {
                            $composer3.startReplaceGroup(509819206);
                            ComposerKt.sourceInformation($composer3, "128@5849L12");
                            auroraTextFaint = AuroraColorSchemeKt.getAuroraAccent($composer3, 0);
                        } else {
                            $composer3.startReplaceGroup(509819785);
                            ComposerKt.sourceInformation($composer3, "128@5867L15");
                            auroraTextFaint = AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0);
                        }
                        $composer3.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str, (Modifier) null, auroraTextFaint, 0L, (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 131034);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer2, 54), $composer2, (($dirty >> 3) & 14) | 805306368 | (($dirty >> 3) & 112) | (($dirty >> 3) & 896), 504);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled2 = enabled3;
            primary2 = primary3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final boolean z = enabled2;
            final boolean z2 = primary2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraComponentsKt.AuroraButton$lambda$0(text, onClick, modifier5, z, z2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: AuroraCard-942rkJo, reason: not valid java name */
    public static final void m109AuroraCard942rkJo(Modifier modifier, Function0<Unit> function0, float contentSpacing, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function0<Unit> function1;
        float contentSpacing2;
        Modifier modifier3;
        Function0<ComposeUiNode> function2;
        float contentSpacing3;
        Function0<Unit> function3;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1946491564);
        ComposerKt.sourceInformation($composer2, "C(AuroraCard)P(2,3,1:c#ui.unit.Dp)146@6333L12,150@6475L19,151@6518L14,153@6630L179:AuroraComponents.kt#9jzrut");
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
            function1 = function0;
        } else if (($changed & 48) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            contentSpacing2 = contentSpacing;
        } else if (($changed & 384) == 0) {
            contentSpacing2 = contentSpacing;
            $dirty |= $composer2.changed(contentSpacing2) ? 256 : 128;
        } else {
            contentSpacing2 = contentSpacing;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            contentSpacing3 = contentSpacing2;
            function3 = function1;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.Companion : modifier2;
            Function0<Unit> function4 = i3 != 0 ? null : function1;
            if (i4 != 0) {
                contentSpacing2 = AuroraSpacing.INSTANCE.m240getSmD9Ej5fM();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1946491564, $dirty2, -1, "ai.nous.aurora.presentation.components.AuroraCard (AuroraComponents.kt:139)");
            }
            RoundedCornerShape shape = AuroraShapeKt.getAuroraShapeLg();
            long auroraAccent = AuroraColorSchemeKt.getAuroraAccent($composer2, 0);
            long jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent) : 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent) : 0.0f);
            long jM4560getBlack0d7_KjU = ai.nous.aurora.compat.Compat.m4560getBlack0d7_KjU(Color.Companion);
            Modifier base = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m4198shadows4CzXII$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(7), shape, false, jM4532copywmQWz5c, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM4560getBlack0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM4560getBlack0d7_KjU) : 0.45f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM4560getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM4560getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM4560getBlack0d7_KjU) : 0.0f), 4, null), shape), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer2, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer2, 0), shape);
            Modifier clickable = function4 != null ? ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(base, false, null, null, function4, 7, null) : base;
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(modifier4.then(clickable), AuroraSpacing.INSTANCE.m237getLgD9Ej5fM());
            Arrangement.Vertical verticalArrangement$iv = ai.nous.aurora.compat.Compat.m914spacedBy0680j_4(Arrangement.INSTANCE, contentSpacing);
            int $changed$iv = $dirty2 & 7168;
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            modifier3 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Function0<Unit> function5 = function4;
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function2 = constructor;
                $composer2.createNode(function2);
            } else {
                function2 = constructor;
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
            int i5 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            content.invoke(ColumnScopeInstance.INSTANCE, $composer2, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentSpacing3 = contentSpacing2;
            function3 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final Function0<Unit> function6 = function3;
            final float f = contentSpacing3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraComponentsKt.AuroraCard_942rkJo$lambda$1(modifier5, function6, f, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AuroraTextField(final String value, final Function1<? super String, Unit> onValueChange, Modifier modifier, String label, boolean singleLine, boolean enabled, String placeholder, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        boolean z;
        boolean z2;
        String label2;
        boolean singleLine2;
        boolean enabled2;
        String placeholder2;
        VisualTransformation visualTransformation2;
        KeyboardOptions keyboardOptions2;
        Function2<? super Composer, ? super Integer, Unit> function3;
        ComposableLambda composableLambdaRememberComposableLambda;
        ComposableLambda composableLambdaRememberComposableLambda2;
        Composer $composer2;
        Modifier modifier2;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer $composer3 = $composer.startRestartGroup(1285243228);
        ComposerKt.sourceInformation($composer3, "C(AuroraTextField)P(8,4,3,2,6!1,5,9)188@7983L10,189@8028L10,190@8072L15,191@8122L12,192@8190L14,193@8232L12,194@8278L12,195@8326L15,196@8379L13,197@8432L13,198@8484L13,187@7944L564,175@7402L1113:AuroraComponents.kt#9jzrut");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(label) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            z = singleLine;
        } else if (($changed & 24576) == 0) {
            z = singleLine;
            $dirty |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = singleLine;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z2 = enabled;
        } else if ((196608 & $changed) == 0) {
            z2 = enabled;
            $dirty |= $composer3.changed(z2) ? 131072 : 65536;
        } else {
            z2 = enabled;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(placeholder) ? 1048576 : 524288;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(keyboardOptions) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        if (($dirty & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            label2 = label;
            placeholder2 = placeholder;
            visualTransformation2 = visualTransformation;
            keyboardOptions2 = keyboardOptions;
            function3 = function2;
            $composer2 = $composer3;
            singleLine2 = z;
            enabled2 = z2;
        } else {
            Modifier modifier3 = i2 != 0 ? Modifier.Companion : modifier;
            label2 = i3 != 0 ? null : label;
            singleLine2 = i4 != 0 ? true : z;
            enabled2 = i5 != 0 ? true : z2;
            placeholder2 = i6 != 0 ? null : placeholder;
            visualTransformation2 = i7 != 0 ? VisualTransformation.Companion.getNone() : visualTransformation;
            keyboardOptions2 = i8 != 0 ? KeyboardOptions.Companion.getDefault() : keyboardOptions;
            function3 = i9 != 0 ? null : function2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1285243228, $dirty, -1, "ai.nous.aurora.presentation.components.AuroraTextField (AuroraComponents.kt:174)");
            }
            Modifier modifierFillMaxWidth$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(modifier3, 0.0f, 1, null);
            $composer3.startReplaceGroup(1051709935);
            ComposerKt.sourceInformation($composer3, "*179@7556L35");
            if (label2 == null) {
                composableLambdaRememberComposableLambda = null;
            } else {
                final String it = label2;
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1920745719, true, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$AuroraTextField$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C179@7575L13,179@7558L31:AuroraComponents.kt#9jzrut");
                        if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1920745719, $changed2, -1, "ai.nous.aurora.presentation.components.AuroraTextField.<anonymous>.<anonymous> (AuroraComponents.kt:179)");
                        }
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(it, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraCaption($composer4, 0), $composer4, 0, 0, 65534);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54);
            }
            $composer3.endReplaceGroup();
            $composer3.startReplaceGroup(1051712497);
            ComposerKt.sourceInformation($composer3, "*180@7636L37");
            if (placeholder2 == null) {
                composableLambdaRememberComposableLambda2 = null;
            } else {
                final String it2 = placeholder2;
                composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-709442039, true, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$AuroraTextField$2$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C180@7655L15,180@7638L33:AuroraComponents.kt#9jzrut");
                        if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-709442039, $changed2, -1, "ai.nous.aurora.presentation.components.AuroraTextField.<anonymous>.<anonymous> (AuroraComponents.kt:180)");
                        }
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(it2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer4, 0), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131066);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54);
            }
            $composer3.endReplaceGroup();
            RoundedCornerShape auroraShapeMd = AuroraShapeKt.getAuroraShapeMd();
            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
            long auroraText = AuroraColorSchemeKt.getAuroraText($composer3, 0);
            long auroraText2 = AuroraColorSchemeKt.getAuroraText($composer3, 0);
            long auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
            long auroraAccent = AuroraColorSchemeKt.getAuroraAccent($composer3, 0);
            $composer2 = $composer3;
            modifier2 = modifier3;
            OutlinedTextFieldKt.OutlinedTextField(value, onValueChange, modifierFillMaxWidth$default, enabled2, false, (TextStyle) null, (Function2) composableLambdaRememberComposableLambda, (Function2) composableLambdaRememberComposableLambda2, (Function2<? super Composer, ? super Integer, Unit>) null, function3, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, visualTransformation2, keyboardOptions2, (KeyboardActions) null, singleLine2, 0, 0, (MutableInteractionSource) null, (Shape) auroraShapeMd, ai.nous.aurora.compat.Compat.m2717colors0hiis_0(outlinedTextFieldDefaults, auroraText, auroraText2, auroraTextMuted, 0L, AuroraColorSchemeKt.getAuroraSurface($composer3, 0), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), 0L, AuroraColorSchemeKt.getAuroraAccent($composer3, 0), 0L, null, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent) : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent) : 0.0f), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, AuroraColorSchemeKt.getAuroraAccent($composer3, 0), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, 2122311304, 4095), $composer2, ($dirty & 14) | ($dirty & 112) | (($dirty >> 6) & 7168) | (1879048192 & $dirty), (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (29360128 & ($dirty << 9)), 48, 1916208);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final String str = label2;
            final boolean z3 = singleLine2;
            final boolean z4 = enabled2;
            final String str2 = placeholder2;
            final VisualTransformation visualTransformation3 = visualTransformation2;
            final KeyboardOptions keyboardOptions3 = keyboardOptions2;
            final Function2<? super Composer, ? super Integer, Unit> function4 = function3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraComponentsKt.AuroraTextField$lambda$4(value, onValueChange, modifier4, str, z3, z4, str2, visualTransformation3, keyboardOptions3, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AuroraKicker(final String text, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(text, "text");
        Composer $composer3 = $composer.startRestartGroup(-1690144532);
        ComposerKt.sourceInformation($composer3, "C(AuroraKicker)P(1)205@8637L16,205@8599L76:AuroraComponents.kt#9jzrut");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) != 18 || !$composer3.getSkipping()) {
            Modifier modifier3 = i2 != 0 ? Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1690144532, $dirty2, -1, "ai.nous.aurora.presentation.components.AuroraKicker (AuroraComponents.kt:204)");
            }
            String upperCase = text.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(upperCase, modifier3, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMetaBright($composer3, 0), $composer2, $dirty2 & 112, 0, 65532);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraComponentsKt.AuroraKicker$lambda$5(text, modifier2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:74:0x02ea  */
    public static final void ConnectionChip(final boolean online, final String label, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long jM4532copywmQWz5c;
        long auroraError;
        long auroraError2;
        int i2;
        Object obj;
        float f;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(label, "label");
        Composer $composer2 = $composer.startRestartGroup(-989287911);
        ComposerKt.sourceInformation($composer2, "C(ConnectionChip)P(2)217@9019L556:AuroraComponents.kt#9jzrut");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(online) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(label) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i3 != 0 ? Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-989287911, $dirty, -1, "ai.nous.aurora.presentation.components.ConnectionChip (AuroraComponents.kt:213)");
            }
            if (online) {
                $composer2.startReplaceGroup(-359135263);
                ComposerKt.sourceInformation($composer2, "214@8815L14");
                jM4532copywmQWz5c = AuroraColorSchemeKt.getAuroraLiveSoft($composer2, 0);
            } else {
                $composer2.startReplaceGroup(-359134235);
                ComposerKt.sourceInformation($composer2, "214@8835L11");
                long auroraError3 = AuroraColorSchemeKt.getAuroraError($composer2, 0);
                jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError3) : 0.2f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError3) : 0.0f);
            }
            $composer2.endReplaceGroup();
            long bg = jM4532copywmQWz5c;
            if (online) {
                $composer2.startReplaceGroup(-359132835);
                ComposerKt.sourceInformation($composer2, "215@8891L10");
                auroraError = AuroraColorSchemeKt.getAuroraLive($composer2, 0);
            } else {
                $composer2.startReplaceGroup(-359132322);
                ComposerKt.sourceInformation($composer2, "215@8907L11");
                auroraError = AuroraColorSchemeKt.getAuroraError($composer2, 0);
            }
            $composer2.endReplaceGroup();
            long fg = auroraError;
            if (online) {
                $composer2.startReplaceGroup(-359130650);
                ComposerKt.sourceInformation($composer2, "216@8948L10");
                auroraError2 = AuroraColorSchemeKt.getAuroraLive($composer2, 0);
                i2 = 14;
                obj = null;
                f = 0.35f;
            } else {
                $composer2.startReplaceGroup(-359129467);
                ComposerKt.sourceInformation($composer2, "216@8984L11");
                auroraError2 = AuroraColorSchemeKt.getAuroraError($composer2, 0);
                i2 = 14;
                obj = null;
                f = 0.4f;
            }
            long border = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError2) : f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError2) : 0.0f);
            $composer2.endReplaceGroup();
            Modifier modifierM1035paddingVpY3zN4 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(modifier4, AuroraShapeKt.getAuroraShapePill()), bg, null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), border, AuroraShapeKt.getAuroraShapePill()), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), ai.nous.aurora.compat.Compat.m7016constructorimpl(5));
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM914spacedBy0680j_4 = ai.nous.aurora.compat.Compat.m914spacedBy0680j_4(Arrangement.INSTANCE, ai.nous.aurora.compat.Compat.m7016constructorimpl(6));
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalOrVerticalM914spacedBy0680j_4, centerVertically, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifierM1035paddingVpY3zN4);
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
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                modifier3 = modifier4;
            } else {
                modifier3 = modifier4;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i4 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i5 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 542376669, "C226@9357L142,232@9540L10,232@9508L61:AuroraComponents.kt#9jzrut");
                BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(6)), RoundedCornerShapeKt.getCircleShape()), fg, null, 2, null), $composer2, 0);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(label, (Modifier) null, fg, TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMeta($composer2, 0), $composer2, (($dirty >> 3) & 14) | 3072, 0, 65522);
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
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i6 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i7 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 542376669, "C226@9357L142,232@9540L10,232@9508L61:AuroraComponents.kt#9jzrut");
            BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(6)), RoundedCornerShapeKt.getCircleShape()), fg, null, 2, null), $composer2, 0);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(label, (Modifier) null, fg, TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraMeta($composer2, 0), $composer2, (($dirty >> 3) & 14) | 3072, 0, 65522);
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
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return AuroraComponentsKt.ConnectionChip$lambda$7(online, label, modifier5, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:224:0x0e2d  */
    /* JADX WARN: Code duplicated, block: B:230:0x0e6a  */
    /* JADX WARN: Code duplicated, block: B:234:0x0ea0  */
    public static final void MessageBubble(final String role, final String text, Modifier modifier, String deliveryStatus, Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        String str;
        Function0<Unit> function1;
        Modifier modifier3;
        String deliveryStatus2;
        long auroraText;
        long assistantBorder;
        Modifier modifierM600borderxT4_qwU;
        Function0<ComposeUiNode> function2;
        MeasurePolicy measurePolicy$iv;
        boolean invalid$iv;
        Object value$iv;
        long j;
        Function0<ComposeUiNode> function3;
        Object value$iv2;
        Function0<ComposeUiNode> function4;
        Function0<ComposeUiNode> function5;
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(text, "text");
        Composer $composer2 = $composer.startRestartGroup(1678902184);
        ComposerKt.sourceInformation($composer2, "C(MessageBubble)P(3,4,1)256@10380L7,257@10392L9116:AuroraComponents.kt#9jzrut");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(role) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(text) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            str = deliveryStatus;
        } else if (($changed & 3072) == 0) {
            str = deliveryStatus;
            $dirty |= $composer2.changed(str) ? 2048 : 1024;
        } else {
            str = deliveryStatus;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            function1 = function0;
        } else if (($changed & 24576) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        } else {
            function1 = function0;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            deliveryStatus2 = str;
            $composer2 = $composer2;
        } else {
            modifier3 = i2 != 0 ? Modifier.Companion : modifier2;
            deliveryStatus2 = i3 != 0 ? OutboxExactlyOnce.STATUS_SENT : str;
            if (i4 != 0) {
                function1 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1678902184, $dirty2, -1, "ai.nous.aurora.presentation.components.MessageBubble (AuroraComponents.kt:243)");
            }
            final boolean isUser = StringsKt.equals(role, "user", true);
            RoundedCornerShape shape = ai.nous.aurora.compat.Compat.m1318RoundedCornerShapea9UjIt4(ai.nous.aurora.compat.Compat.m7016constructorimpl(22), ai.nous.aurora.compat.Compat.m7016constructorimpl(22), isUser ? ai.nous.aurora.compat.Compat.m7016constructorimpl(6) : ai.nous.aurora.compat.Compat.m7016constructorimpl(22), isUser ? ai.nous.aurora.compat.Compat.m7016constructorimpl(22) : ai.nous.aurora.compat.Compat.m7016constructorimpl(6));
            boolean failed = Intrinsics.areEqual(deliveryStatus2, OutboxExactlyOnce.STATUS_FAILED);
            boolean pending = Intrinsics.areEqual(deliveryStatus2, OutboxExactlyOnce.STATUS_PENDING);
            if (isUser) {
                $composer2.startReplaceGroup(-172175720);
                ComposerKt.sourceInformation($composer2, "255@10281L14");
                auroraText = AuroraColorSchemeKt.getAuroraOnAccent($composer2, 0);
            } else {
                $composer2.startReplaceGroup(-172175084);
                ComposerKt.sourceInformation($composer2, "255@10301L10");
                auroraText = AuroraColorSchemeKt.getAuroraText($composer2, 0);
            }
            $composer2.endReplaceGroup();
            final long textColor = auroraText;
            ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localThemeTokens);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final ThemeTokens tokens = (ThemeTokens) objConsume;
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(modifier3, 0.0f, 1, null);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Horizontal horizontalArrangement$iv = isUser ? arrangement.getEnd() : arrangement.getStart();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getTop();
            MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
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
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i5 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i6 = ((0 >> 6) & 112) | 6;
            RowScope $this$MessageBubble_u24lambda_u2417 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 1286544441, "C288@11583L12,282@11342L7633:AuroraComponents.kt#9jzrut");
            $composer2.startReplaceGroup(595682593);
            ComposerKt.sourceInformation($composer2, "");
            if (!isUser) {
                if (tokens.getAstral()) {
                    $composer2.startReplaceGroup(1286321426);
                    ComposerKt.sourceInformation($composer2, "265@10671L11,263@10605L263");
                    long auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer2, 0);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g("✦", ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(8), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), 0.0f, 9, null), ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber) : 0.9f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber) : 0.0f), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 3126, 0, 131056);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(1286624358);
                    ComposerKt.sourceInformation($composer2, "278@11258L12,272@10976L333");
                    Modifier modifierClip = ClipKt.clip(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(6), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), 0.0f, 9, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(2)), ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(1)));
                    long auroraAccent = AuroraColorSchemeKt.getAuroraAccent($composer2, 0);
                    BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent) : 0.8f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent) : 0.0f), null, 2, null), $composer2, 0);
                    $composer2.endReplaceGroup();
                }
            }
            $composer2.endReplaceGroup();
            Modifier modifierM1086widthInVpY3zN4$default = ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(340), 1, null);
            float fM7016constructorimpl = tokens.getAstral() ? ai.nous.aurora.compat.Compat.m7016constructorimpl(9) : ai.nous.aurora.compat.Compat.m7016constructorimpl(5);
            RoundedCornerShape roundedCornerShape = shape;
            long auroraAccent2 = AuroraColorSchemeKt.getAuroraAccent($composer2, 0);
            long jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent2) : tokens.getAstral() ? 0.22f : 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent2) : 0.0f);
            long jM4560getBlack0d7_KjU = ai.nous.aurora.compat.Compat.m4560getBlack0d7_KjU(Color.Companion);
            Modifier modifierClip2 = ClipKt.clip(ai.nous.aurora.compat.Compat.m4198shadows4CzXII$default(modifierM1086widthInVpY3zN4$default, fM7016constructorimpl, roundedCornerShape, false, jM4532copywmQWz5c, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM4560getBlack0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM4560getBlack0d7_KjU) : 0.45f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM4560getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM4560getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM4560getBlack0d7_KjU) : 0.0f), 4, null), shape);
            if (isUser) {
                $composer2.startReplaceGroup(1287557210);
                ComposerKt.sourceInformation($composer2, "*301@12286L11");
                Modifier modifierBackground$default = ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(Modifier.Companion, ai.nous.aurora.compat.Compat.m4496linearGradientmHitzGk(Brush.Companion,(Pair<Float, Color>[]) new Pair[]{TuplesKt.to(Float.valueOf(0.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m348getUserBubbleTop0d7_KjU())), TuplesKt.to(Float.valueOf(0.45f), ai.nous.aurora.compat.Compat.m4524boximpl(AuroraColorSchemeKt.getAuroraAmber($composer2, 0))), TuplesKt.to(Float.valueOf(1.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m313getAmberDeep0d7_KjU()))}, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4309getZeroF1C5BW0(Offset.Companion) : 0L, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4307getInfiniteF1C5BW0(Offset.Companion) : 0L, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4907getClamp3opZhB0(TileMode.Companion) : 0), null, 0.0f, 6, null);
                float fM7016constructorimpl2 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                long jM347getUserBubbleEdge0d7_KjU = tokens.m347getUserBubbleEdge0d7_KjU();
                modifierM600borderxT4_qwU = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierBackground$default, fM7016constructorimpl2, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM347getUserBubbleEdge0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM347getUserBubbleEdge0d7_KjU) : 0.45f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM347getUserBubbleEdge0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM347getUserBubbleEdge0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM347getUserBubbleEdge0d7_KjU) : 0.0f), shape);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1288473787);
                ComposerKt.sourceInformation($composer2, "328@13610L11");
                Pair[] assistantStops = tokens.getAstral() ? new Pair[]{TuplesKt.to(Float.valueOf(0.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m322getGlassSurface0d7_KjU())), TuplesKt.to(Float.valueOf(0.55f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m339getSurface0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m339getSurface0d7_KjU()))} : new Pair[]{TuplesKt.to(Float.valueOf(0.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m322getGlassSurface0d7_KjU())), TuplesKt.to(Float.valueOf(0.65f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m340getSurfaceHighlight0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m340getSurfaceHighlight0d7_KjU()))};
                if (failed) {
                    assistantBorder = AuroraColorSchemeKt.getAuroraError($composer2, 0);
                } else if (tokens.getAstral()) {
                    long jM321getGlassHairline0d7_KjU = tokens.m321getGlassHairline0d7_KjU();
                    assistantBorder = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM321getGlassHairline0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM321getGlassHairline0d7_KjU) : 0.85f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM321getGlassHairline0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM321getGlassHairline0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM321getGlassHairline0d7_KjU) : 0.0f);
                } else {
                    long jM334getOutlineStrong0d7_KjU = tokens.m334getOutlineStrong0d7_KjU();
                    assistantBorder = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM334getOutlineStrong0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM334getOutlineStrong0d7_KjU) : 0.65f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM334getOutlineStrong0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM334getOutlineStrong0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM334getOutlineStrong0d7_KjU) : 0.0f);
                }
                modifierM600borderxT4_qwU = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(Modifier.Companion, ai.nous.aurora.compat.Compat.m4492verticalGradient8A3gB4$default(Brush.Companion, (Pair[]) Arrays.copyOf(assistantStops, assistantStops.length), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), assistantBorder, shape);
                $composer2.endReplaceGroup();
            }
            Modifier modifierThen = modifierClip2.then(modifierM600borderxT4_qwU);
            $composer2.startReplaceGroup(595805622);
            ComposerKt.sourceInformation($composer2, "348@14452L11");
            Modifier companionM600borderxT4_qwU = (isUser && failed) ? ai.nous.aurora.compat.Compat.m600borderxT4_qwU(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraError($composer2, 0), shape) : Modifier.Companion;
            $composer2.endReplaceGroup();
            Modifier modifier$iv2 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(modifierThen.then(companionM600borderxT4_qwU), AuroraSpacing.INSTANCE.m237getLgD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(11));
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv2 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function2 = constructor2;
                $composer2.createNode(function2);
            } else {
                function2 = constructor2;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                measurePolicy$iv = measurePolicy$iv3;
            } else {
                measurePolicy$iv = measurePolicy$iv3;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                int i7 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i8 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1566249650, "C352@14666L660,352@14647L679:AuroraComponents.kt#9jzrut");
                SelectionContainerKt.SelectionContainer(null, (Function2) ComposableLambdaKt.rememberComposableLambda(-1455326837, true, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$MessageBubble$1$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed2) {
                        AnnotatedString rendered;
                        ComposerKt.sourceInformation($composer3, "C365@15157L10,363@15086L226:AuroraComponents.kt#9jzrut");
                        if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1455326837, $changed2, -1, "ai.nous.aurora.presentation.components.MessageBubble.<anonymous>.<anonymous>.<anonymous> (AuroraComponents.kt:353)");
                            }
                            $composer3.startReplaceGroup(148602297);
                            ComposerKt.sourceInformation($composer3, "358@14916L10,359@14961L11");
                            if (isUser) {
                                rendered = new AnnotatedString(text, java.util.Collections.<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.SpanStyle>>emptyList(), java.util.Collections.<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.ParagraphStyle>>emptyList());
                            } else {
                                rendered = ChatMarkdownKt.m111parseChatMarkdownzSO0fhY(text, AuroraColorSchemeKt.getAuroraText($composer3, 0), AuroraColorSchemeKt.getAuroraAmber($composer3, 0), tokens.m317getCodeBackground0d7_KjU());
                            }
                            $composer3.endReplaceGroup();
                            TextStyle auroraBody = AuroraTypographyKt.getAuroraBody($composer3, 0);
                            ai.nous.aurora.compat.Compat.m3068TextIbK3jfQ(rendered, null, textColor, 0L, null, isUser ? FontWeight.Companion.getMedium() : FontWeight.Companion.getNormal(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, auroraBody, $composer3, 0, 0, 131034);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }, $composer2, 54), $composer2, 48, 1);
                $composer2.startReplaceGroup(-1196379022);
                ComposerKt.sourceInformation($composer2, "371@15392L40,372@15449L3502");
                if (!isUser && (failed || pending)) {
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer2, 6);
                    Modifier modifierClip3 = ClipKt.clip(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14)));
                    long jM315getBackground0d7_KjU = tokens.m315getBackground0d7_KjU();
                    Modifier modifierM589backgroundbw27NRU$default = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip3, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM315getBackground0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM315getBackground0d7_KjU) : 0.96f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM315getBackground0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM315getBackground0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM315getBackground0d7_KjU) : 0.0f), null, 2, null);
                    float fM7016constructorimpl3 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                    if (failed) {
                        $composer2.startReplaceGroup(-1196368261);
                        ComposerKt.sourceInformation($composer2, "379@15776L11");
                        long auroraError = AuroraColorSchemeKt.getAuroraError($composer2, 0);
                        long jM4532copywmQWz5c2 = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraError, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraError) : 0.45f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraError) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraError) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraError) : 0.0f);
                        $composer2.endReplaceGroup();
                        j = jM4532copywmQWz5c2;
                    } else {
                        $composer2.startReplaceGroup(-1196365926);
                        ComposerKt.sourceInformation($composer2, "380@15841L19");
                        long auroraGlassHairline = AuroraColorSchemeKt.getAuroraGlassHairline($composer2, 0);
                        long jM4532copywmQWz5c3 = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraGlassHairline, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraGlassHairline) : 0.5f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraGlassHairline) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraGlassHairline) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraGlassHairline) : 0.0f);
                        $composer2.endReplaceGroup();
                        j = jM4532copywmQWz5c3;
                    }
                    Modifier modifier$iv3 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierM589backgroundbw27NRU$default, fM7016constructorimpl3, j, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14))), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
                    MeasurePolicy measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv3 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        function3 = constructor3;
                        $composer2.createNode(function3);
                    } else {
                        function3 = constructor3;
                        $composer2.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                    int i9 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    int i10 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 313145073, "C:AuroraComponents.kt#9jzrut");
                    if (failed) {
                        $composer2.startReplaceGroup(313103377);
                        ComposerKt.sourceInformation($composer2, "386@16112L711,401@16848L29,404@17043L10,402@16902L292");
                        Alignment.Vertical verticalAlignment$iv2 = Alignment.Companion.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Modifier modifier$iv4 = Modifier.Companion;
                        Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv5 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv4 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
                        Function0<ComposeUiNode> constructor4 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            function5 = constructor4;
                            $composer2.createNode(function5);
                        } else {
                            function5 = constructor4;
                            $composer2.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv5, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.Companion.getSetModifier());
                        int i11 = ($changed$iv$iv$iv4 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i12 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1435515102, "C391@16402L11,387@16194L251,393@16474L28,396@16639L11,394@16531L266:AuroraComponents.kt#9jzrut");
                        BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(7)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraError($composer2, 0), null, 2, null), $composer2, 0);
                        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), $composer2, 6);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Message didn't send", (Modifier) null, AuroraColorSchemeKt.getAuroraError($composer2, 0), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 199686, 0, 131026);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(3)), $composer2, 6);
                        long auroraText2 = AuroraColorSchemeKt.getAuroraText($composer2, 0);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Hermes kept your words — retry sends the exact same message.", (Modifier) null, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraText2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraText2) : 0.78f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraText2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraText2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraText2) : 0.0f), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(16), 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 3078, 6, 130034);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(314204621);
                        ComposerKt.sourceInformation($composer2, "411@17337L10,409@17248L254");
                        long auroraText3 = AuroraColorSchemeKt.getAuroraText($composer2, 0);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Sending…", (Modifier) null, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraText3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraText3) : 0.78f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraText3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraText3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraText3) : 0.0f), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 199686, 0, 131026);
                        $composer2.endReplaceGroup();
                    }
                    $composer2.startReplaceGroup(148694387);
                    ComposerKt.sourceInformation($composer2, "417@17602L30,426@18170L18,428@18295L211,421@17871L1040");
                    if (failed && function1 != null) {
                        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), $composer2, 6);
                        Modifier modifierM622clickableXHw0xAI$default = ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(ClipKt.clip(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(48), 0.0f, 2, null), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(14))), AuroraSkyKt.getAuroraGoldGradient($composer2, 0), null, 0.0f, 6, null), false, null, null, function1, 7, null);
                        $composer2.startReplaceGroup(148717210);
                        ComposerKt.sourceInformation($composer2, "CC(remember):AuroraComponents.kt#9igjgp");
                        Object it$iv = $composer2.rememberedValue();
                        if (it$iv == Composer.Companion.getEmpty()) {
                            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda10
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AuroraComponentsKt.MessageBubble$lambda$17$lambda$13$lambda$12$lambda$10$lambda$9((SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = it$iv;
                        }
                        $composer2.endReplaceGroup();
                        Modifier modifier$iv5 = ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM622clickableXHw0xAI$default, false, (Function1) value$iv2, 1, null);
                        Alignment contentAlignment$iv = Alignment.Companion.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv6 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv5 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap localMap$iv$iv5 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer2, modifier$iv5);
                        Function0<ComposeUiNode> constructor5 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            function4 = constructor5;
                            $composer2.createNode(function4);
                        } else {
                            function4 = constructor5;
                            $composer2.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv5 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv6, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv5.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                            $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                            $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash5);
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv5, ComposeUiNode.Companion.getSetModifier());
                        int i13 = ($changed$iv$iv$iv5 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i14 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1433110339, "C436@18728L14,434@18629L256:AuroraComponents.kt#9jzrut");
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Retry send", (Modifier) null, AuroraColorSchemeKt.getAuroraOnAccent($composer2, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 199686, 0, 131026);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                    }
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                }
                $composer2.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.startReplaceGroup(595952495);
                ComposerKt.sourceInformation($composer2, "450@19147L345,446@19010L482");
                if (isUser) {
                    Modifier modifierAlign = $this$MessageBubble_u24lambda_u2417.align(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), Alignment.Companion.getBottom());
                    $composer2.startReplaceGroup(595957538);
                    ComposerKt.sourceInformation($composer2, "CC(remember):AuroraComponents.kt#9igjgp");
                    invalid$iv = $composer2.changed(tokens);
                    Object it$iv2 = $composer2.rememberedValue();
                    if (!invalid$iv || it$iv2 == Composer.Companion.getEmpty()) {
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AuroraComponentsKt.MessageBubble$lambda$17$lambda$16$lambda$15(tokens, (DrawScope) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv2;
                    }
                    $composer2.endReplaceGroup();
                    CanvasKt.Canvas(modifierAlign, (Function1) value$iv, $composer2, 0);
                }
                $composer2.endReplaceGroup();
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
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
            int i15 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
            int i16 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1566249650, "C352@14666L660,352@14647L679:AuroraComponents.kt#9jzrut");
            SelectionContainerKt.SelectionContainer(null, (Function2) ComposableLambdaKt.rememberComposableLambda(-1455326837, true, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$MessageBubble$1$1$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    AnnotatedString rendered;
                    ComposerKt.sourceInformation($composer3, "C365@15157L10,363@15086L226:AuroraComponents.kt#9jzrut");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1455326837, $changed2, -1, "ai.nous.aurora.presentation.components.MessageBubble.<anonymous>.<anonymous>.<anonymous> (AuroraComponents.kt:353)");
                        }
                        $composer3.startReplaceGroup(148602297);
                        ComposerKt.sourceInformation($composer3, "358@14916L10,359@14961L11");
                        if (isUser) {
                            rendered = new AnnotatedString(text, java.util.Collections.<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.SpanStyle>>emptyList(), java.util.Collections.<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.ParagraphStyle>>emptyList());
                        } else {
                            rendered = ChatMarkdownKt.m111parseChatMarkdownzSO0fhY(text, AuroraColorSchemeKt.getAuroraText($composer3, 0), AuroraColorSchemeKt.getAuroraAmber($composer3, 0), tokens.m317getCodeBackground0d7_KjU());
                        }
                        $composer3.endReplaceGroup();
                        TextStyle auroraBody = AuroraTypographyKt.getAuroraBody($composer3, 0);
                        ai.nous.aurora.compat.Compat.m3068TextIbK3jfQ(rendered, null, textColor, 0L, null, isUser ? FontWeight.Companion.getMedium() : FontWeight.Companion.getNormal(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, auroraBody, $composer3, 0, 0, 131034);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, 48, 1);
            $composer2.startReplaceGroup(-1196379022);
            ComposerKt.sourceInformation($composer2, "371@15392L40,372@15449L3502");
            if (!isUser) {
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.startReplaceGroup(595952495);
            ComposerKt.sourceInformation($composer2, "450@19147L345,446@19010L482");
            if (isUser) {
                Modifier modifierAlign2 = $this$MessageBubble_u24lambda_u2417.align(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), Alignment.Companion.getBottom());
                $composer2.startReplaceGroup(595957538);
                ComposerKt.sourceInformation($composer2, "CC(remember):AuroraComponents.kt#9igjgp");
                invalid$iv = $composer2.changed(tokens);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AuroraComponentsKt.MessageBubble$lambda$17$lambda$16$lambda$15(tokens, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AuroraComponentsKt.MessageBubble$lambda$17$lambda$16$lambda$15(tokens, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                }
                $composer2.endReplaceGroup();
                CanvasKt.Canvas(modifierAlign2, (Function1) value$iv, $composer2, 0);
            }
            $composer2.endReplaceGroup();
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
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final String str2 = deliveryStatus2;
            final Function0<Unit> function6 = function1;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraComponentsKt.MessageBubble$lambda$18(role, text, modifier4, str2, function6, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MessageBubble$lambda$17$lambda$13$lambda$12$lambda$10$lambda$9(SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        ai.nous.aurora.compat.Compat.m6308setRolekuIjeqM(semantics, ai.nous.aurora.compat.Compat.m6289getButtono7Vup1c(Role.Companion));
        SemanticsPropertiesKt.setContentDescription(semantics, "Retry sending this message");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MessageBubble$lambda$17$lambda$16$lambda$15(ThemeTokens $tokens, DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        Path $this$MessageBubble_u24lambda_u2417_u24lambda_u2416_u24lambda_u2415_u24lambda_u2414 = AndroidPath_androidKt.Path();
        $this$MessageBubble_u24lambda_u2417_u24lambda_u2416_u24lambda_u2415_u24lambda_u2414.moveTo(0.0f, 0.0f);
        $this$MessageBubble_u24lambda_u2417_u24lambda_u2416_u24lambda_u2415_u24lambda_u2414.lineTo(0.0f, ai.nous.aurora.compat.Compat.m4359getHeightimpl(ai.nous.aurora.compat.Compat.mo5090getSizeNHjbRc(Canvas)));
        $this$MessageBubble_u24lambda_u2417_u24lambda_u2416_u24lambda_u2415_u24lambda_u2414.lineTo(ai.nous.aurora.compat.Compat.m4362getWidthimpl(ai.nous.aurora.compat.Compat.mo5090getSizeNHjbRc(Canvas)), ai.nous.aurora.compat.Compat.m4359getHeightimpl(ai.nous.aurora.compat.Compat.mo5090getSizeNHjbRc(Canvas)));
        $this$MessageBubble_u24lambda_u2417_u24lambda_u2416_u24lambda_u2415_u24lambda_u2414.close();
        ai.nous.aurora.compat.Compat.m5080drawPathLG529CI$default(Canvas, $this$MessageBubble_u24lambda_u2417_u24lambda_u2416_u24lambda_u2415_u24lambda_u2414, $tokens.m313getAmberDeep0d7_KjU(), 0.0f, null, null, 0, 60, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:56:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:59:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:60:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:71:0x0466  */
    public static final void LoadingRow(final String text, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        Object value$iv;
        Function0<ComposeUiNode> function0;
        CompositionLocalMap localMap$iv$iv;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(text, "text");
        Composer $composer3 = $composer.startRestartGroup(1955886529);
        ComposerKt.sourceInformation($composer3, "C(LoadingRow)P(1)467@19620L7,476@20080L154,468@19632L1309:AuroraComponents.kt#9jzrut");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) != 18 || !$composer3.getSkipping()) {
            Modifier modifier3 = i2 != 0 ? Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1955886529, $dirty2, -1, "ai.nous.aurora.presentation.components.LoadingRow (AuroraComponents.kt:466)");
            }
            ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localThemeTokens);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ThemeTokens tokens = (ThemeTokens) objConsume;
            Modifier modifierM600borderxT4_qwU = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(modifier3, 0.0f, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(4), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), AstralReadabilityPolicy.INSTANCE.m75loadingRowBackgroundvNxB06k(tokens), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AstralReadabilityPolicy.INSTANCE.m76loadingRowBordervNxB06k(tokens), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10)));
            $composer3.startReplaceGroup(910509761);
            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraComponents.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function1() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AuroraComponentsKt.LoadingRow$lambda$20$lambda$19((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer3.endReplaceGroup();
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierM600borderxT4_qwU, false, (Function1) value$iv, 1, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(12), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
            Arrangement.Horizontal horizontalArrangement$iv = ai.nous.aurora.compat.Compat.m914spacedBy0680j_4(Arrangement.INSTANCE, ai.nous.aurora.compat.Compat.m7016constructorimpl(12));
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
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
            Composer $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                function0 = constructor2;
                localMap$iv$iv = localMap$iv$iv2;
            } else {
                function0 = constructor2;
                localMap$iv$iv = localMap$iv$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i3 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i4 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1184517886, "C486@20520L11,484@20428L146,489@20583L352:AuroraComponents.kt#9jzrut");
                ai.nous.aurora.compat.Compat.m2741CircularProgressIndicatorLxG7B9w(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), AuroraColorSchemeKt.getAuroraAmber($composer3, 0), ai.nous.aurora.compat.Compat.m7016constructorimpl(2), 0L, 0, $composer3, 390, 24);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv2 = Modifier.Companion;
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv2 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                constructor = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
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
                $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1165857955, "C492@20679L11,490@20604L185,498@20861L15,496@20802L123:AuroraComponents.kt#9jzrut");
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Hermes is thinking", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer3, 0), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
                Modifier modifier4 = modifier3;
                $composer2 = $composer3;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(text, (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBodyMuted($composer3, 0), $composer3, ($dirty2 & 14) | 3072, 0, 65526);
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
                modifier2 = modifier4;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i7 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i8 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1184517886, "C486@20520L11,484@20428L146,489@20583L352:AuroraComponents.kt#9jzrut");
            ai.nous.aurora.compat.Compat.m2741CircularProgressIndicatorLxG7B9w(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), AuroraColorSchemeKt.getAuroraAmber($composer3, 0), ai.nous.aurora.compat.Compat.m7016constructorimpl(2), 0L, 0, $composer3, 390, 24);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier modifier$iv3 = Modifier.Companion;
            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv3 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
            constructor = ComposeUiNode.Companion.getConstructor();
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
            $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
            int i9 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i10 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1165857955, "C492@20679L11,490@20604L185,498@20861L15,496@20802L123:AuroraComponents.kt#9jzrut");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Hermes is thinking", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer3, 0), TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
            Modifier modifier5 = modifier3;
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(text, (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBodyMuted($composer3, 0), $composer3, ($dirty2 & 14) | 3072, 0, 65526);
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
            modifier2 = modifier5;
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraComponentsKt.LoadingRow$lambda$23(text, modifier2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LoadingRow$lambda$20$lambda$19(SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        ai.nous.aurora.compat.Compat.m6307setLiveRegionhR3wRGc(semantics, ai.nous.aurora.compat.Compat.m6281getPolite0phEisY(LiveRegionMode.Companion));
        SemanticsPropertiesKt.setContentDescription(semantics, "Hermes is thinking");
        return Unit.INSTANCE;
    }

    public static final void ThinkingRow(final String detail, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Function0<ComposeUiNode> function0;
        long thinkingBorder;
        Composer $composer2;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Function0<ComposeUiNode> function4;
        Object value$iv4;
        Intrinsics.checkNotNullParameter(detail, "detail");
        Composer $composer3 = $composer.startRestartGroup(787198289);
        ComposerKt.sourceInformation($composer3, "C(ThinkingRow)513@21319L7,514@21387L7,515@21416L205,522@21638L56,534@22027L163,531@21943L3860:AuroraComponents.kt#9jzrut");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(detail) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($dirty & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            Modifier modifier3 = i2 != 0 ? Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(787198289, $dirty, -1, "ai.nous.aurora.presentation.components.ThinkingRow (AuroraComponents.kt:512)");
            }
            ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localThemeTokens);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ThemeTokens tokens = (ThemeTokens) objConsume;
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context = (Context) objConsume2;
            $composer3.startReplaceGroup(-1222081074);
            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraComponents.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv = Boolean.valueOf(Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f) > 0.0f);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            boolean animatorOn = ((Boolean) value$iv).booleanValue();
            $composer3.endReplaceGroup();
            $composer3.startReplaceGroup(-1222074119);
            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraComponents.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.Companion.getEmpty()) {
                value$iv2 = SnapshotIntStateKt.mutableIntStateOf(animatorOn ? 1 : 3);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            MutableIntState dots$delegate = (MutableIntState) value$iv2;
            $composer3.endReplaceGroup();
            $composer3.startReplaceGroup(-1222071984);
            ComposerKt.sourceInformation($composer3, "524@21746L186,524@21725L207");
            if (animatorOn) {
                Unit unit = Unit.INSTANCE;
                $composer3.startReplaceGroup(-1222070533);
                ComposerKt.sourceInformation($composer3, "CC(remember):AuroraComponents.kt#9igjgp");
                Object it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.Companion.getEmpty()) {
                    value$iv4 = new AuroraComponentsKt$ThinkingRow$1$1(dots$delegate, null);
                    $composer3.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv3;
                }
                $composer3.endReplaceGroup();
                EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation, ? extends Object>) value$iv4, $composer3, 6);
            }
            $composer3.endReplaceGroup();
            Modifier modifierFillMaxWidth$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(modifier3, 0.0f, 1, null);
            $composer3.startReplaceGroup(-1222061564);
            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraComponents.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv4 = $composer3.rememberedValue();
            if (invalid$iv || it$iv4 == Composer.Companion.getEmpty()) {
                value$iv3 = new Function1() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AuroraComponentsKt.ThinkingRow$lambda$30$lambda$29(detail, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv4;
            }
            $composer3.endReplaceGroup();
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.d_SemanticsModifierKt_semantics_5(modifierFillMaxWidth$default, false, (Function1) value$iv3, 1, null);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier modifier4 = modifier3;
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
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
            Composer $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i4 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1621840273, "C575@23463L2334:AuroraComponents.kt#9jzrut");
            if (tokens.getAstral()) {
                $composer3.startReplaceGroup(1621756944);
                ComposerKt.sourceInformation($composer3, "543@22350L11,541@22292L218");
                long auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g("✦", ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(8), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), 0.0f, 9, null), ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber) : 0.9f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber) : 0.0f), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3126, 0, 131056);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1622005657);
                ComposerKt.sourceInformation($composer3, "554@22798L12,548@22540L305");
                Modifier modifierClip = ClipKt.clip(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(6), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), 0.0f, 9, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(2)), ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(1)));
                long auroraAccent = AuroraColorSchemeKt.getAuroraAccent($composer3, 0);
                BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent) : 0.8f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent) : 0.0f), null, 2, null), $composer3, 0);
                $composer3.endReplaceGroup();
            }
            Pair[] thinkingStops = tokens.getAstral() ? new Pair[]{TuplesKt.to(Float.valueOf(0.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m322getGlassSurface0d7_KjU())), TuplesKt.to(Float.valueOf(0.55f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m339getSurface0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m339getSurface0d7_KjU()))} : new Pair[]{TuplesKt.to(Float.valueOf(0.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m322getGlassSurface0d7_KjU())), TuplesKt.to(Float.valueOf(0.65f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m340getSurfaceHighlight0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), ai.nous.aurora.compat.Compat.m4524boximpl(tokens.m340getSurfaceHighlight0d7_KjU()))};
            if (tokens.getAstral()) {
                long jM321getGlassHairline0d7_KjU = tokens.m321getGlassHairline0d7_KjU();
                thinkingBorder = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM321getGlassHairline0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM321getGlassHairline0d7_KjU) : 0.85f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM321getGlassHairline0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM321getGlassHairline0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM321getGlassHairline0d7_KjU) : 0.0f);
            } else {
                long jM334getOutlineStrong0d7_KjU = tokens.m334getOutlineStrong0d7_KjU();
                thinkingBorder = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM334getOutlineStrong0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM334getOutlineStrong0d7_KjU) : 0.65f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM334getOutlineStrong0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM334getOutlineStrong0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM334getOutlineStrong0d7_KjU) : 0.0f);
            }
            $composer2 = $composer3;
            Modifier modifierM1035paddingVpY3zN4 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(ClipKt.clip(ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(340), 1, null), ai.nous.aurora.compat.Compat.m1318RoundedCornerShapea9UjIt4(ai.nous.aurora.compat.Compat.m7016constructorimpl(6), ai.nous.aurora.compat.Compat.m7016constructorimpl(18), ai.nous.aurora.compat.Compat.m7016constructorimpl(18), ai.nous.aurora.compat.Compat.m7016constructorimpl(18))), ai.nous.aurora.compat.Compat.m4492verticalGradient8A3gB4$default(Brush.Companion, (Pair[]) Arrays.copyOf(thinkingStops, thinkingStops.length), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), thinkingBorder, ai.nous.aurora.compat.Compat.m1318RoundedCornerShapea9UjIt4(ai.nous.aurora.compat.Compat.m7016constructorimpl(6), ai.nous.aurora.compat.Compat.m7016constructorimpl(18), ai.nous.aurora.compat.Compat.m7016constructorimpl(18), ai.nous.aurora.compat.Compat.m7016constructorimpl(18))), AuroraSpacing.INSTANCE.m237getLgD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(11));
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM914spacedBy0680j_4 = ai.nous.aurora.compat.Compat.m914spacedBy0680j_4(Arrangement.INSTANCE, ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM914spacedBy0680j_4, centerVertically, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv2 = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifierM1035paddingVpY3zN4);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
            int $i$f$Row = $changed$iv$iv2 << 6;
            int $changed$iv$iv$iv2 = ($i$f$Row & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor2;
                $composer3.createNode(function1);
            } else {
                function1 = constructor2;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
            int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i6 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1596920819, "C593@24170L601,607@24784L1003:AuroraComponents.kt#9jzrut");
            Arrangement.HorizontalOrVertical horizontalOrVerticalM914spacedBy0680j_5 = ai.nous.aurora.compat.Compat.m914spacedBy0680j_4(Arrangement.INSTANCE, ai.nous.aurora.compat.Compat.m7016constructorimpl(4));
            Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
            Modifier modifierM1081sizeVpY3zN4 = ai.nous.aurora.compat.Compat.m1081sizeVpY3zN4(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(28), ai.nous.aurora.compat.Compat.m7016constructorimpl(14));
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalOrVerticalM914spacedBy0680j_5, centerVertically2, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv3 = (438 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifierM1081sizeVpY3zN4);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.Companion.getConstructor();
            int $i$f$Row2 = $changed$iv$iv3 << 6;
            int $changed$iv$iv$iv3 = ($i$f$Row2 & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor3;
                $composer3.createNode(function2);
            } else {
                function2 = constructor3;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
            int i7 = ($changed$iv$iv$iv3 >> 6) & 14;
            int i8 = 0;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i9 = ((438 >> 6) & 112) | 6;
            RowScope $this$ThinkingRow_u24lambda_u2436_u24lambda_u2435_u24lambda_u2432 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1477384204, "C:AuroraComponents.kt#9jzrut");
            $composer3.startReplaceGroup(186204887);
            ComposerKt.sourceInformation($composer3, "*603@24657L12,599@24453L286");
            int i10 = 0;
            while (i10 < 3) {
                int index = i10;
                Modifier companion = Modifier.Companion;
                int $changed$iv$iv$iv4 = $changed$iv$iv$iv3;
                int $changed$iv$iv$iv5 = ThinkingRow$lambda$26(dots$delegate);
                int i11 = i8;
                Modifier modifierClip2 = ClipKt.clip(ai.nous.aurora.compat.Compat.m1079size3ABfNKs(companion, index < $changed$iv$iv$iv5 ? ai.nous.aurora.compat.Compat.m7016constructorimpl(6) : ai.nous.aurora.compat.Compat.m7016constructorimpl(5)), RoundedCornerShapeKt.getCircleShape());
                long auroraAccent2 = AuroraColorSchemeKt.getAuroraAccent($composer3, 0);
                BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(modifierClip2, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent2) : index < ThinkingRow$lambda$26(dots$delegate) ? 1.0f : 0.28f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent2) : 0.0f), null, 2, null), $composer3, 0);
                i10++;
                $changed$iv$iv$iv3 = $changed$iv$iv$iv4;
                i8 = i11;
                $this$ThinkingRow_u24lambda_u2436_u24lambda_u2435_u24lambda_u2432 = $this$ThinkingRow_u24lambda_u2436_u24lambda_u2435_u24lambda_u2432;
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier modifier$iv2 = Modifier.Companion;
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv4 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv6 = (($changed$iv$iv4 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function3 = constructor4;
                $composer3.createNode(function3);
            } else {
                function3 = constructor4;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.Companion.getSetModifier());
            int i12 = ($changed$iv$iv$iv6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i13 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1477797217, "C610@24911L10,611@24951L10,608@24809L223,614@25049L724:AuroraComponents.kt#9jzrut");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Hermes is thinking" + StringsKt.repeat(".", ThinkingRow$lambda$26(dots$delegate)), (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), 0L, (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, AuroraTypographyKt.getAuroraBody($composer3, 0), $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 65498);
            Arrangement.Horizontal horizontalArrangement$iv2 = ai.nous.aurora.compat.Compat.m914spacedBy0680j_4(Arrangement.INSTANCE, ai.nous.aurora.compat.Compat.m7016constructorimpl(5));
            Alignment.Vertical verticalAlignment$iv2 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier modifier$iv3 = Modifier.Companion;
            MeasurePolicy measurePolicy$iv5 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv5 = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv7 = (($changed$iv$iv5 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function4 = constructor5;
                $composer3.createNode(function4);
            } else {
                function4 = constructor5;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv5 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv5, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv5.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash5);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv5, ComposeUiNode.Companion.getSetModifier());
            int i14 = ($changed$iv$iv$iv7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
            int i15 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 684722557, "C618@25259L15,618@25234L59,619@25332L15,619@25314L52,622@25460L12,620@25387L208,626@25634L15,626@25616L52,627@25721L15,627@25689L66:AuroraComponents.kt#9jzrut");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Received", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("→", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Working", (Modifier) null, AuroraColorSchemeKt.getAuroraAccent($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 131026);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("→", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("Tools if needed", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3078, 0, 131058);
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
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.AuroraComponentsKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraComponentsKt.ThinkingRow$lambda$37(detail, modifier2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ThinkingRow$lambda$26(MutableIntState $dots$delegate) {
        MutableIntState $this$getValue$iv = $dots$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ThinkingRow$lambda$30$lambda$29(String $detail, SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        ai.nous.aurora.compat.Compat.m6307setLiveRegionhR3wRGc(semantics, ai.nous.aurora.compat.Compat.m6281getPolite0phEisY(LiveRegionMode.Companion));
        SemanticsPropertiesKt.setContentDescription(semantics, "Hermes is thinking. " + $detail);
        return Unit.INSTANCE;
    }
}
