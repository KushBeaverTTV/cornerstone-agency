package androidx.compose.foundation.contextmenu;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.IntrinsicKt;
import androidx.compose.foundation.layout.IntrinsicSize;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.BasicTextKt;
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
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.compose.ui.window.PopupProperties;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextMenuUi.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a=\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000e2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\u0010¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u0013H\u0001¢\u0006\u0002\u0010\u0014\u001ai\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000e2*\b\u0002\u0010\u001a\u001a$\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0010¢\u0006\u0002\b\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0 H\u0001¢\u0006\u0002\u0010!\u001aN\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0 2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00012\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u000b0\u0010¢\u0006\u0002\b\u0013H\u0001¢\u0006\u0002\u0010(\u001aF\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0 2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u000b0\u0010¢\u0006\u0002\b\u0013H\u0001¢\u0006\u0002\u0010)\u001a!\u0010*\u001a\u00020\u00012\b\b\u0003\u0010+\u001a\u00020,2\b\b\u0003\u0010-\u001a\u00020,H\u0001¢\u0006\u0002\u0010.\u001a \u0010/\u001a\u00020\u001b*\u0004\u0018\u0001002\u0006\u00101\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a \u00104\u001a\u00020\u001b*\u0004\u0018\u0001002\u0006\u00101\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00103\u001a2\u00106\u001a\u00020\u001b*\u0002072\b\b\u0001\u00108\u001a\u00020,2\b\b\u0001\u00109\u001a\u00020,2\u0006\u00101\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0004\b:\u0010;\u001a\"\u0010<\u001a\u0004\u0018\u000100*\u0002072\b\b\u0001\u00108\u001a\u00020,2\b\b\u0001\u00109\u001a\u00020,H\u0002\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006="}, d2 = {"DefaultContextMenuColors", "Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "getDefaultContextMenuColors$annotations", "()V", "getDefaultContextMenuColors", "()Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "DefaultPopupProperties", "Landroidx/compose/ui/window/PopupProperties;", "DisabledAlpha", "", "ContextMenuColumn", "", "colors", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/contextmenu/ContextMenuColors;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ContextMenuItem", "label", "", "enabled", "", "leadingIcon", "Landroidx/compose/ui/graphics/Color;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "iconColor", "onClick", "Lkotlin/Function0;", "(Ljava/lang/String;ZLandroidx/compose/foundation/contextmenu/ContextMenuColors;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "ContextMenuPopup", "popupPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "onDismiss", "contextMenuBuilderBlock", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/contextmenu/ContextMenuColors;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "computeContextMenuColors", "backgroundStyleId", "", "foregroundStyleId", "(IILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "disabledColor", "Landroid/content/res/ColorStateList;", "defaultColor", "disabledColor-4WTKRHQ", "(Landroid/content/res/ColorStateList;J)J", "enabledColor", "enabledColor-4WTKRHQ", "resolveColor", "Landroid/content/Context;", "resId", "attrId", "resolveColor-g2O1Hgs", "(Landroid/content/Context;IIJ)J", "resolveColorStateList", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContextMenuUi_androidKt {
    private static final ContextMenuColors DefaultContextMenuColors;
    private static final PopupProperties DefaultPopupProperties = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);
    private static final float DisabledAlpha = 0.38f;

    public static /* synthetic */ void getDefaultContextMenuColors$annotations() {
    }

    static {
        long jM4571getWhite0d7_KjU = Color.INSTANCE.m4571getWhite0d7_KjU();
        long jM4560getBlack0d7_KjU = Color.INSTANCE.m4560getBlack0d7_KjU();
        long jM4560getBlack0d7_KjU2 = Color.INSTANCE.m4560getBlack0d7_KjU();
        long jM4560getBlack0d7_KjU3 = Color.INSTANCE.m4560getBlack0d7_KjU();
        long jM4532copywmQWz5c = Color.m4532copywmQWz5c(jM4560getBlack0d7_KjU3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM4560getBlack0d7_KjU3) : 0.38f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM4560getBlack0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM4560getBlack0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM4560getBlack0d7_KjU3) : 0.0f);
        long jM4560getBlack0d7_KjU4 = Color.INSTANCE.m4560getBlack0d7_KjU();
        DefaultContextMenuColors = new ContextMenuColors(jM4571getWhite0d7_KjU, jM4560getBlack0d7_KjU, jM4560getBlack0d7_KjU2, jM4532copywmQWz5c, Color.m4532copywmQWz5c(jM4560getBlack0d7_KjU4, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM4560getBlack0d7_KjU4) : 0.38f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM4560getBlack0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM4560getBlack0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM4560getBlack0d7_KjU4) : 0.0f), null);
    }

    public static final void ContextMenuPopup(final PopupPositionProvider popupPositionProvider, final Function0<Unit> function0, Modifier modifier, final Function1<? super ContextMenuScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(712057293);
        ComposerKt.sourceInformation($composer2, "C(ContextMenuPopup)P(3,2,1)108@4164L26,104@4014L241:ContextMenuUi.android.kt#3xeu6s");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(popupPositionProvider) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
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
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(712057293, $dirty, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup (ContextMenuUi.android.kt:103)");
            }
            ContextMenuPopup(popupPositionProvider, function0, modifier4, computeContextMenuColors(0, 0, $composer2, 0, 3), function1, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | (($dirty << 3) & 57344), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuPopup.1
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

                public final void invoke(Composer composer, int i3) {
                    ContextMenuUi_androidKt.ContextMenuPopup(popupPositionProvider, function0, modifier5, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void ContextMenuPopup(final PopupPositionProvider popupPositionProvider, final Function0<Unit> function0, Modifier modifier, final ContextMenuColors colors, final Function1<? super ContextMenuScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(1447189339);
        ComposerKt.sourceInformation($composer2, "C(ContextMenuPopup)P(4,3,2)126@4681L257,122@4530L408:ContextMenuUi.android.kt#3xeu6s");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(popupPositionProvider) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
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
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1447189339, $dirty2, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup (ContextMenuUi.android.kt:121)");
            }
            AndroidPopup_androidKt.Popup(popupPositionProvider, function0, DefaultPopupProperties, ComposableLambdaKt.rememberComposableLambda(795909757, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuPopup.2
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
                    ComposerKt.sourceInformation($composer3, "C127@4727L205,127@4691L241:ContextMenuUi.android.kt#3xeu6s");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(795909757, $changed2, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup.<anonymous> (ContextMenuUi.android.kt:127)");
                        }
                        ContextMenuColors contextMenuColors = colors;
                        Modifier modifier4 = modifier3;
                        final Function1<ContextMenuScope, Unit> function2 = function1;
                        final ContextMenuColors contextMenuColors2 = colors;
                        ContextMenuUi_androidKt.ContextMenuColumn(contextMenuColors, modifier4, ComposableLambdaKt.rememberComposableLambda(1156688164, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuPopup.2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                                invoke(columnScope, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(ColumnScope $this$ContextMenuColumn, Composer $composer4, int $changed3) {
                                Object value$iv;
                                ComposerKt.sourceInformation($composer4, "C128@4753L31,*132@4893L15:ContextMenuUi.android.kt#3xeu6s");
                                if (($changed3 & 17) == 16 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1156688164, $changed3, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup.<anonymous>.<anonymous> (ContextMenuUi.android.kt:128)");
                                }
                                ComposerKt.sourceInformationMarkerStart($composer4, 1358376149, "CC(remember):ContextMenuUi.android.kt#9igjgp");
                                Object it$iv = $composer4.rememberedValue();
                                if (it$iv == Composer.INSTANCE.getEmpty()) {
                                    value$iv = new ContextMenuScope();
                                    $composer4.updateRememberedValue(value$iv);
                                } else {
                                    value$iv = it$iv;
                                }
                                ContextMenuScope scope = (ContextMenuScope) value$iv;
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                Function1<ContextMenuScope, Unit> function3 = function2;
                                ContextMenuColors contextMenuColors3 = contextMenuColors2;
                                scope.clear$foundation_release();
                                function3.invoke(scope);
                                scope.Content$foundation_release(contextMenuColors3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer3, 54), $composer3, 384, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ($dirty2 & 14) | 3456 | ($dirty2 & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuPopup.3
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

                public final void invoke(Composer composer, int i3) {
                    ContextMenuUi_androidKt.ContextMenuPopup(popupPositionProvider, function0, modifier4, colors, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void ContextMenuColumn(final ContextMenuColors colors, Modifier modifier, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function0<ComposeUiNode> function0;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(-921259293);
        ComposerKt.sourceInformation($composer2, "C(ContextMenuColumn)P(!1,2)154@5499L21,145@5128L427:ContextMenuUi.android.kt#3xeu6s");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(colors) ? 4 : 2;
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
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-921259293, $dirty2, -1, "androidx.compose.foundation.contextmenu.ContextMenuColumn (ContextMenuUi.android.kt:144)");
            }
            Modifier modifier$iv = ScrollKt.verticalScroll$default(PaddingKt.m1036paddingVpY3zN4$default(IntrinsicKt.width(BackgroundKt.m589backgroundbw27NRU$default(ShadowKt.m4198shadows4CzXII$default(modifier4, ContextMenuSpec.INSTANCE.m709getMenuContainerElevationD9Ej5fM(), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(ContextMenuSpec.INSTANCE.m701getCornerRadiusD9Ej5fM()), false, 0L, 0L, 28, null), colors.getBackgroundColor(), null, 2, null), IntrinsicSize.Max), 0.0f, ContextMenuSpec.INSTANCE.m710getVerticalPaddingD9Ej5fM(), 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null);
            int $changed$iv = ($dirty2 << 3) & 7168;
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier modifier5 = modifier4;
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            function3.invoke(ColumnScopeInstance.INSTANCE, $composer2, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuColumn.1
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

                public final void invoke(Composer composer, int i4) {
                    ContextMenuUi_androidKt.ContextMenuColumn(colors, modifier6, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void ContextMenuItem(final String label, final boolean enabled, final ContextMenuColors colors, Modifier modifier, Function3<? super Color, ? super Composer, ? super Integer, Unit> function3, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function3<? super Color, ? super Composer, ? super Integer, Unit> function4;
        Function3<? super Color, ? super Composer, ? super Integer, Unit> function5;
        Object value$iv;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(791018367);
        ComposerKt.sourceInformation($composer2, "C(ContextMenuItem)P(2,1!1,4)189@6696L221,182@6391L1588:ContextMenuUi.android.kt#3xeu6s");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(label) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(enabled) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            function4 = function3;
        } else if (($changed & 24576) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 16384 : 8192;
        } else {
            function4 = function3;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            function5 = function4;
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            function5 = i3 != 0 ? null : function4;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(791018367, $dirty2, -1, "androidx.compose.foundation.contextmenu.ContextMenuItem (ContextMenuUi.android.kt:181)");
            }
            Alignment.Vertical labelVerticalTextAlignment = ContextMenuSpec.INSTANCE.getLabelVerticalTextAlignment();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM914spacedBy0680j_4 = Arrangement.INSTANCE.m914spacedBy0680j_4(ContextMenuSpec.INSTANCE.m703getHorizontalPaddingD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer2, -1814201532, "CC(remember):ContextMenuUi.android.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 112) == 32) | ((458752 & $dirty2) == 131072);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0<Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt$ContextMenuItem$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (enabled) {
                            function0.invoke();
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier5 = modifier4;
            Modifier modifierM1036paddingVpY3zN4$default = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.m1082sizeInqDBjuR0(SizeKt.fillMaxWidth$default(ClickableKt.m622clickableXHw0xAI$default(modifier4, enabled, label, null, (Function0) value$iv, 4, null), 0.0f, 1, null), ContextMenuSpec.INSTANCE.m700getContainerWidthMinD9Ej5fM(), ContextMenuSpec.INSTANCE.m708getListItemHeightD9Ej5fM(), ContextMenuSpec.INSTANCE.m699getContainerWidthMaxD9Ej5fM(), ContextMenuSpec.INSTANCE.m708getListItemHeightD9Ej5fM()), ContextMenuSpec.INSTANCE.m703getHorizontalPaddingD9Ej5fM(), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalOrVerticalM914spacedBy0680j_4, labelVerticalTextAlignment, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifierM1036paddingVpY3zN4$default);
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
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i5 = ((432 >> 6) & 112) | 6;
            RowScope $this$ContextMenuItem_u24lambda_u243 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 554565222, "C212@7698L275:ContextMenuUi.android.kt#3xeu6s");
            if (function5 == null) {
                $composer2.startReplaceGroup(554568909);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(554568910);
                ComposerKt.sourceInformation($composer2, "*204@7349L330");
                Function3<? super Color, ? super Composer, ? super Integer, Unit> function6 = function5;
                Modifier modifier$iv = SizeKt.m1075requiredSizeInqDBjuR0$default(Modifier.INSTANCE, ContextMenuSpec.INSTANCE.m704getIconSizeD9Ej5fM(), 0.0f, ContextMenuSpec.INSTANCE.m704getIconSizeD9Ej5fM(), ContextMenuSpec.INSTANCE.m704getIconSizeD9Ej5fM(), 2, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
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
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i7 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1218311042, "C210@7612L65:ContextMenuUi.android.kt#3xeu6s");
                function6.invoke(Color.m4524boximpl(enabled ? colors.getIconColor() : colors.getDisabledIconColor()), $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            BasicTextKt.m1330BasicTextVhcvRP8(label, $this$ContextMenuItem_u24lambda_u243.weight(Modifier.INSTANCE, 1.0f, true), ContextMenuSpec.INSTANCE.m711textStyle8_81llA(enabled ? colors.getTextColor() : colors.getDisabledTextColor()), (Function1<? super TextLayoutResult, Unit>) null, 0, false, 1, 0, (ColorProducer) null, $composer2, ($dirty2 & 14) | 1572864, 440);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            final Function3<? super Color, ? super Composer, ? super Integer, Unit> function7 = function5;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuItem.3
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
                    ContextMenuUi_androidKt.ContextMenuItem(label, enabled, colors, modifier6, function7, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final ContextMenuColors getDefaultContextMenuColors() {
        return DefaultContextMenuColors;
    }

    public static final ContextMenuColors computeContextMenuColors(int backgroundStyleId, int foregroundStyleId, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1689505294, "C(computeContextMenuColors)355@13081L7,356@13137L7,356@13100L846:ContextMenuUi.android.kt#3xeu6s");
        int backgroundStyleId2 = (i & 1) != 0 ? R.style.Widget.PopupMenu : backgroundStyleId;
        int foregroundStyleId2 = (i & 2) != 0 ? R.style.TextAppearance.Widget.PopupMenu.Large : foregroundStyleId;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1689505294, $changed, -1, "androidx.compose.foundation.contextmenu.computeContextMenuColors (ContextMenuUi.android.kt:354)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) objConsume;
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -753201361, "CC(remember):ContextMenuUi.android.kt#9igjgp");
        boolean invalid$iv = $composer.changed(context) | $composer.changed((Configuration) objConsume2);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            long backgroundColor = m715resolveColorg2O1Hgs(context, backgroundStyleId2, R.attr.colorBackground, DefaultContextMenuColors.getBackgroundColor());
            ColorStateList textColorStateList = resolveColorStateList(context, foregroundStyleId2, R.attr.textColorPrimary);
            long enabledColor = m714enabledColor4WTKRHQ(textColorStateList, DefaultContextMenuColors.getTextColor());
            long disabledColor = m713disabledColor4WTKRHQ(textColorStateList, DefaultContextMenuColors.getDisabledTextColor());
            value$iv = new ContextMenuColors(backgroundColor, enabledColor, enabledColor, disabledColor, disabledColor, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ContextMenuColors contextMenuColors = (ContextMenuColors) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return contextMenuColors;
    }

    /* JADX INFO: renamed from: resolveColor-g2O1Hgs, reason: not valid java name */
    private static final long m715resolveColorg2O1Hgs(Context $this$resolveColor_u2dg2O1Hgs, int resId, int attrId, long defaultColor) {
        TypedArray typedArray = $this$resolveColor_u2dg2O1Hgs.obtainStyledAttributes(resId, new int[]{attrId});
        int defaultColorAndroid = ColorKt.m4588toArgb8_81llA(defaultColor);
        int colorInt = typedArray.getColor(0, defaultColorAndroid);
        typedArray.recycle();
        return colorInt == defaultColorAndroid ? defaultColor : ColorKt.Color(colorInt);
    }

    private static final ColorStateList resolveColorStateList(Context $this$resolveColorStateList, int resId, int attrId) {
        TypedArray typedArray = $this$resolveColorStateList.obtainStyledAttributes(resId, new int[]{attrId});
        ColorStateList colorStateList = typedArray.getColorStateList(0);
        typedArray.recycle();
        return colorStateList;
    }

    /* JADX INFO: renamed from: enabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m714enabledColor4WTKRHQ(ColorStateList $this$enabledColor_u2d4WTKRHQ, long defaultColor) {
        int defaultColorArgb = ColorKt.m4588toArgb8_81llA(defaultColor);
        Integer color = $this$enabledColor_u2d4WTKRHQ != null ? Integer.valueOf($this$enabledColor_u2d4WTKRHQ.getColorForState(new int[]{R.attr.state_enabled}, defaultColorArgb)) : null;
        return (color == null || color.intValue() == defaultColorArgb) ? defaultColor : ColorKt.Color(color.intValue());
    }

    /* JADX INFO: renamed from: disabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m713disabledColor4WTKRHQ(ColorStateList $this$disabledColor_u2d4WTKRHQ, long defaultColor) {
        int defaultColorArgb = ColorKt.m4588toArgb8_81llA(defaultColor);
        Integer color = $this$disabledColor_u2d4WTKRHQ != null ? Integer.valueOf($this$disabledColor_u2d4WTKRHQ.getColorForState(new int[]{-16842910}, defaultColorArgb)) : null;
        return (color == null || color.intValue() == defaultColorArgb) ? defaultColor : ColorKt.Color(color.intValue());
    }
}
