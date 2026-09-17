package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.ShapeKeyTokens;
import androidx.compose.material3.tokens.TypographyKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ExpressiveNavigationBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u001aW\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020!0,¢\u0006\u0002\b-H\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001a\u009d\u0001\u00100\u001a\u00020!2\u0006\u00101\u001a\u0002022\f\u00103\u001a\b\u0012\u0004\u0012\u00020!0,2\u0011\u00104\u001a\r\u0012\u0004\u0012\u00020!0,¢\u0006\u0002\b-2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u00105\u001a\u0002022\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020!\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020!\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u00108\u001a\u0002092\b\b\u0002\u0010:\u001a\u00020;2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010=H\u0001ø\u0001\u0000¢\u0006\u0004\b>\u0010?\u001a\u0018\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020AH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\r\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0016\u0010\u000e\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000f\u0010\u0010\"\u0016\u0010\u0011\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0010\"\u0010\u0010\u0013\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0016\u0010\u0014\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0015\u0010\u0010\"\u0016\u0010\u0016\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0017\u0010\u0010\"\u0016\u0010\u0018\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0019\u0010\u0010\"\u0016\u0010\u001a\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001b\u0010\u0010\"\u0010\u0010\u001c\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0010\u0010\u001d\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0016\u0010\u001e\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001f\u0010\u0010\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006D"}, d2 = {"ActiveIconColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ActiveIndicatorColor", "ActiveIndicatorShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "ActiveLabelTextColor", "IconSize", "Landroidx/compose/ui/unit/Dp;", "F", "InactiveIconColor", "InactiveLabelTextColor", "LabelTextFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "NavigationBarHeight", "StartIconIndicatorHorizontalPadding", "getStartIconIndicatorHorizontalPadding", "()F", "StartIconIndicatorVerticalPadding", "getStartIconIndicatorVerticalPadding", "StartIconItemActiveIndicatorHeight", "StartIconToLabelPadding", "getStartIconToLabelPadding", "TopIconIndicatorHorizontalPadding", "getTopIconIndicatorHorizontalPadding", "TopIconIndicatorToLabelPadding", "getTopIconIndicatorToLabelPadding", "TopIconIndicatorVerticalPadding", "getTopIconIndicatorVerticalPadding", "TopIconItemActiveIndicatorHeight", "TopIconItemActiveIndicatorWidth", "TopIconItemVerticalPadding", "getTopIconItemVerticalPadding", "ExpressiveNavigationBar", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "arrangement", "Landroidx/compose/material3/NavigationBarArrangement;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "ExpressiveNavigationBar-NiJtXQ4", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/foundation/layout/WindowInsets;ILkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ExpressiveNavigationBarItem", "selected", "", "onClick", "icon", "enabled", "label", "badge", "iconPosition", "Landroidx/compose/material3/NavigationItemIconPosition;", "colors", "Landroidx/compose/material3/NavigationItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "ExpressiveNavigationBarItem-pli-t6k", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ILandroidx/compose/material3/NavigationItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "calculateCenteredContentHorizontalPadding", "", "itemsCount", "barWidth", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExpressiveNavigationBarKt {
    private static final float StartIconIndicatorHorizontalPadding;
    private static final float StartIconIndicatorVerticalPadding;
    private static final float StartIconToLabelPadding;
    private static final float TopIconIndicatorHorizontalPadding;
    private static final float TopIconIndicatorToLabelPadding;
    private static final float TopIconIndicatorVerticalPadding;
    private static final float IconSize = Dp.m7016constructorimpl((float) 24.0d);
    private static final float TopIconItemActiveIndicatorWidth = Dp.m7016constructorimpl(56);
    private static final float TopIconItemActiveIndicatorHeight = Dp.m7016constructorimpl(32);
    private static final float StartIconItemActiveIndicatorHeight = Dp.m7016constructorimpl(40);
    private static final TypographyKeyTokens LabelTextFont = TypographyKeyTokens.LabelMedium;
    private static final ShapeKeyTokens ActiveIndicatorShape = ShapeKeyTokens.CornerFull;
    private static final ColorSchemeKeyTokens ActiveIconColor = ColorSchemeKeyTokens.Secondary;
    private static final ColorSchemeKeyTokens ActiveLabelTextColor = ColorSchemeKeyTokens.Secondary;
    private static final ColorSchemeKeyTokens ActiveIndicatorColor = ColorSchemeKeyTokens.SecondaryContainer;
    private static final ColorSchemeKeyTokens InactiveIconColor = ColorSchemeKeyTokens.OnSurfaceVariant;
    private static final ColorSchemeKeyTokens InactiveLabelTextColor = ColorSchemeKeyTokens.OnSurfaceVariant;
    private static final float NavigationBarHeight = Dp.m7016constructorimpl(64);
    private static final float TopIconItemVerticalPadding = Dp.m7016constructorimpl(6);

    /* JADX INFO: renamed from: ExpressiveNavigationBar-NiJtXQ4, reason: not valid java name */
    public static final void m2469ExpressiveNavigationBarNiJtXQ4(Modifier modifier, long containerColor, long contentColor, WindowInsets windowInsets, int arrangement, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long j2;
        WindowInsets windowInsets2;
        final int arrangement2;
        final Modifier.Companion modifier3;
        long containerColor2;
        long contentColor2;
        final WindowInsets windowInsets3;
        Modifier modifier4;
        long containerColor3;
        long contentColor3;
        WindowInsets windowInsets4;
        int arrangement3;
        Composer $composer2 = $composer.startRestartGroup(-1171105467);
        ComposerKt.sourceInformation($composer2, "C(ExpressiveNavigationBar)P(4,1:c#ui.graphics.Color,3:c#ui.graphics.Color,5,0:c#material3.NavigationBarArrangement)83@3944L14,84@4018L12,85@4097L12,92@4328L779,89@4244L863:ExpressiveNavigationBar.kt#uh7d8r");
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
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j = containerColor;
                int i3 = $composer2.changed(j) ? 32 : 16;
                $dirty |= i3;
            } else {
                j = containerColor;
            }
            $dirty |= i3;
        } else {
            j = containerColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j2 = contentColor;
                int i4 = $composer2.changed(j2) ? 256 : 128;
                $dirty |= i4;
            } else {
                j2 = contentColor;
            }
            $dirty |= i4;
        } else {
            j2 = contentColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                windowInsets2 = windowInsets;
                int i5 = $composer2.changed(windowInsets2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i5;
        } else {
            windowInsets2 = windowInsets;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                arrangement2 = arrangement;
                int i6 = $composer2.changed(arrangement2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                arrangement2 = arrangement;
            }
            $dirty |= i6;
        } else {
            arrangement2 = arrangement;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            containerColor3 = j;
            contentColor3 = j2;
            windowInsets4 = windowInsets2;
            arrangement3 = arrangement2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    containerColor2 = ExpressiveNavigationBarDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty &= -113;
                } else {
                    containerColor2 = j;
                }
                if ((i & 4) != 0) {
                    contentColor2 = ExpressiveNavigationBarDefaults.INSTANCE.getContentColor($composer2, 6);
                    $dirty &= -897;
                } else {
                    contentColor2 = j2;
                }
                if ((i & 8) != 0) {
                    windowInsets3 = ExpressiveNavigationBarDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty &= -7169;
                } else {
                    windowInsets3 = windowInsets2;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    arrangement2 = ExpressiveNavigationBarDefaults.INSTANCE.m2468getArrangementEbr7WPU();
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                modifier3 = modifier2;
                containerColor2 = j;
                contentColor2 = j2;
                windowInsets3 = windowInsets2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1171105467, $dirty, -1, "androidx.compose.material3.ExpressiveNavigationBar (ExpressiveNavigationBar.kt:88)");
            }
            SurfaceKt.m2917SurfaceT9BRK9s(null, null, containerColor2, contentColor2, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1573697866, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExpressiveNavigationBarKt$ExpressiveNavigationBar$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    CenteredContentMeasurePolicy centeredContentMeasurePolicy;
                    ComposerKt.sourceInformation($composer3, "C93@4338L763:ExpressiveNavigationBar.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1573697866, $changed2, -1, "androidx.compose.material3.ExpressiveNavigationBar.<anonymous> (ExpressiveNavigationBar.kt:93)");
                        }
                        Modifier modifierSelectableGroup = SelectableGroupKt.selectableGroup(SizeKt.m1064defaultMinSizeVpY3zN4$default(WindowInsetsPaddingKt.windowInsetsPadding(modifier3, windowInsets3), 0.0f, ExpressiveNavigationBarKt.NavigationBarHeight, 1, null));
                        int i7 = arrangement2;
                        if (NavigationBarArrangement.m2626equalsimpl0(i7, NavigationBarArrangement.INSTANCE.m2631getEqualWeightEbr7WPU())) {
                            centeredContentMeasurePolicy = new EqualWeightContentMeasurePolicy();
                        } else if (NavigationBarArrangement.m2626equalsimpl0(i7, NavigationBarArrangement.INSTANCE.m2630getCenteredEbr7WPU())) {
                            centeredContentMeasurePolicy = new CenteredContentMeasurePolicy();
                        } else {
                            throw new IllegalArgumentException("Invalid ItemsArrangement value.");
                        }
                        Function2<Composer, Integer, Unit> function3 = function2;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifierSelectableGroup);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv = ((0 << 6) & 896) | 6;
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
                        Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer3);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, centeredContentMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        function3.invoke($composer3, Integer.valueOf(($changed$iv$iv >> 6) & 14));
                        $composer3.endNode();
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
            }, $composer2, 54), $composer2, (($dirty << 3) & 896) | 12582912 | (($dirty << 3) & 7168), 115);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            windowInsets4 = windowInsets3;
            arrangement3 = arrangement2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final long j3 = containerColor3;
            final long j4 = contentColor3;
            final WindowInsets windowInsets5 = windowInsets4;
            final int i7 = arrangement3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExpressiveNavigationBarKt$ExpressiveNavigationBar$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) {
                    ExpressiveNavigationBarKt.m2469ExpressiveNavigationBarNiJtXQ4(modifier5, j3, j4, windowInsets5, i7, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: ExpressiveNavigationBarItem-pli-t6k, reason: not valid java name */
    public static final void m2470ExpressiveNavigationBarItemplit6k(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, int iconPosition, NavigationItemColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier.Companion modifier2;
        boolean enabled2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int iconPosition2;
        NavigationItemColors colors2;
        MutableInteractionSource interactionSource2;
        int $dirty;
        NavigationItemColors colors3;
        MutableInteractionSource interactionSource3;
        MutableInteractionSource interactionSource4;
        MutableInteractionSource interactionSource5;
        int iconPosition3;
        NavigationItemColors colors4;
        Modifier modifier3;
        boolean enabled3;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(1250474866);
        ComposerKt.sourceInformation($composer2, "C(ExpressiveNavigationBarItem)P(9,8,3,7,2,6!1,4:c#material3.NavigationItemIconPosition)195@8610L8,219@9391L5,220@9444L5,215@9259L800:ExpressiveNavigationBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 2048 : 1024;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(enabled) ? 16384 : 8192;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty2 |= 1572864;
            function5 = function4;
        } else if ((1572864 & $changed) == 0) {
            function5 = function4;
            $dirty2 |= $composer2.changedInstance(function5) ? 1048576 : 524288;
        } else {
            function5 = function4;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changed(iconPosition) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer2.changed(colors)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        if (($dirty2 & 306783379) == 306783378 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            function8 = function3;
            iconPosition3 = iconPosition;
            colors4 = colors;
            interactionSource5 = interactionSource;
            function9 = function5;
            enabled3 = enabled;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                enabled2 = i3 != 0 ? true : enabled;
                function6 = i4 != 0 ? null : function3;
                function7 = i5 != 0 ? null : function5;
                iconPosition2 = i6 != 0 ? NavigationItemIconPosition.INSTANCE.m2684getTopxw1Ddg() : iconPosition;
                if ((i & 256) != 0) {
                    colors2 = ExpressiveNavigationBarItemDefaults.INSTANCE.colors($composer2, 6);
                    $dirty2 &= -234881025;
                } else {
                    colors2 = colors;
                }
                if (i7 != 0) {
                    interactionSource2 = null;
                    $dirty = $dirty2;
                    colors3 = colors2;
                } else {
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2;
                    colors3 = colors2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 256) != 0) {
                    $dirty2 &= -234881025;
                }
                modifier2 = modifier;
                enabled2 = enabled;
                function6 = function3;
                iconPosition2 = iconPosition;
                interactionSource2 = interactionSource;
                function7 = function5;
                $dirty = $dirty2;
                colors3 = colors;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1250474866, $dirty, -1, "androidx.compose.material3.ExpressiveNavigationBarItem (ExpressiveNavigationBar.kt:197)");
            }
            $composer2.startReplaceGroup(-986536477);
            ComposerKt.sourceInformation($composer2, "199@8762L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer2, -986535826, "CC(remember):ExpressiveNavigationBar.kt#9igjgp");
                interactionSource3 = interactionSource2;
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                interactionSource4 = (MutableInteractionSource) value$iv;
            } else {
                interactionSource3 = interactionSource2;
                interactionSource4 = interactionSource3;
            }
            $composer2.endReplaceGroup();
            boolean isIconPositionTop = NavigationItemIconPosition.m2679equalsimpl0(iconPosition2, NavigationItemIconPosition.INSTANCE.m2684getTopxw1Ddg());
            float indicatorHorizontalPadding = isIconPositionTop ? TopIconIndicatorHorizontalPadding : StartIconIndicatorHorizontalPadding;
            float indicatorVerticalPadding = isIconPositionTop ? TopIconIndicatorVerticalPadding : StartIconIndicatorVerticalPadding;
            NavigationItemKt.m2685NavigationItemSHbi2eg(selected, function0, function2, TypographyKt.getValue(LabelTextFont, $composer2, 6), ShapesKt.getValue(ActiveIndicatorShape, $composer2, 6), TopIconItemActiveIndicatorWidth, indicatorHorizontalPadding, indicatorVerticalPadding, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, colors3, modifier2, enabled2, function6, function7, iconPosition2, interactionSource4, $composer2, ($dirty & 14) | 906166272 | ($dirty & 112) | ($dirty & 896), (($dirty >> 21) & 112) | 6 | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752) | (3670016 & ($dirty >> 3)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource5 = interactionSource3;
            iconPosition3 = iconPosition2;
            colors4 = colors3;
            modifier3 = modifier2;
            enabled3 = enabled2;
            function8 = function6;
            function9 = function7;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final boolean z = enabled3;
            final Function2<? super Composer, ? super Integer, Unit> function10 = function8;
            final Function2<? super Composer, ? super Integer, Unit> function11 = function9;
            final int i8 = iconPosition3;
            final NavigationItemColors navigationItemColors = colors4;
            final MutableInteractionSource mutableInteractionSource = interactionSource5;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExpressiveNavigationBarKt$ExpressiveNavigationBarItem$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i9) {
                    ExpressiveNavigationBarKt.m2470ExpressiveNavigationBarItemplit6k(selected, function0, function2, modifier4, z, function10, function11, i8, navigationItemColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int calculateCenteredContentHorizontalPadding(int itemsCount, int barWidth) {
        if (itemsCount > 6) {
            return 0;
        }
        float paddingPercentage = ((100 - ((itemsCount + 3) * 10)) / 2.0f) / 100;
        return MathKt.roundToInt(barWidth * paddingPercentage);
    }

    static {
        float arg0$iv = TopIconItemActiveIndicatorHeight;
        float other$iv = IconSize;
        TopIconIndicatorVerticalPadding = Dp.m7016constructorimpl(Dp.m7016constructorimpl(arg0$iv - other$iv) / 2);
        float arg0$iv2 = TopIconItemActiveIndicatorWidth;
        float other$iv2 = IconSize;
        TopIconIndicatorHorizontalPadding = Dp.m7016constructorimpl(Dp.m7016constructorimpl(arg0$iv2 - other$iv2) / 2);
        float arg0$iv3 = StartIconItemActiveIndicatorHeight;
        float other$iv3 = IconSize;
        StartIconIndicatorVerticalPadding = Dp.m7016constructorimpl(Dp.m7016constructorimpl(arg0$iv3 - other$iv3) / 2);
        TopIconIndicatorToLabelPadding = Dp.m7016constructorimpl(4);
        StartIconIndicatorHorizontalPadding = Dp.m7016constructorimpl(16);
        StartIconToLabelPadding = Dp.m7016constructorimpl(4);
    }

    public static final float getTopIconItemVerticalPadding() {
        return TopIconItemVerticalPadding;
    }

    public static final float getTopIconIndicatorVerticalPadding() {
        return TopIconIndicatorVerticalPadding;
    }

    public static final float getTopIconIndicatorHorizontalPadding() {
        return TopIconIndicatorHorizontalPadding;
    }

    public static final float getStartIconIndicatorVerticalPadding() {
        return StartIconIndicatorVerticalPadding;
    }

    public static final float getTopIconIndicatorToLabelPadding() {
        return TopIconIndicatorToLabelPadding;
    }

    public static final float getStartIconIndicatorHorizontalPadding() {
        return StartIconIndicatorHorizontalPadding;
    }

    public static final float getStartIconToLabelPadding() {
        return StartIconToLabelPadding;
    }
}
