package androidx.compose.material3;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.SecureFlagPolicy;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ModalBottomSheet.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a¹\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u001c\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001aJ\u0010\u001d\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0012H\u0001¢\u0006\u0002\u0010\"\u001a\f\u0010#\u001a\u00020$*\u00020%H\u0000\u001a\u0014\u0010&\u001a\u00020$*\u00020'2\u0006\u0010(\u001a\u00020$H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006)²\u0006\u0015\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0012X\u008a\u0084\u0002"}, d2 = {"ModalBottomSheet", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material3/SheetState;", "sheetMaxWidth", "Landroidx/compose/ui/unit/Dp;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "scrimColor", "dragHandle", "Landroidx/compose/runtime/Composable;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "properties", "Landroidx/compose/material3/ModalBottomSheetProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ModalBottomSheet-dYc4hso", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFJLkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/ModalBottomSheetProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ModalBottomSheetDialog", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/ModalBottomSheetProperties;Landroidx/compose/animation/core/Animatable;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "isFlagSecureEnabled", "", "Landroid/view/View;", "shouldApplySecureFlag", "Landroidx/compose/ui/window/SecureFlagPolicy;", "isSecureFlagSetOnParent", "material3_release", "currentContent"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheet_androidKt {

    /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SecureFlagPolicy.values().length];
            try {
                iArr[SecureFlagPolicy.SecureOff.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SecureFlagPolicy.SecureOn.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SecureFlagPolicy.Inherit.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use constructor with contentWindowInsets parameter.", replaceWith = @ReplaceWith(expression = "ModalBottomSheet(onDismissRequest,modifier,sheetState,sheetMaxWidth,shape,containerColor,contentColor,tonalElevation,scrimColor,dragHandle,{ windowInsets },properties,content,)", imports = {}))
    /* JADX INFO: renamed from: ModalBottomSheet-dYc4hso, reason: not valid java name */
    public static final /* synthetic */ void m2622ModalBottomSheetdYc4hso(final Function0 onDismissRequest, Modifier modifier, SheetState sheetState, float sheetMaxWidth, Shape shape, long containerColor, long contentColor, float tonalElevation, long scrimColor, Function2 dragHandle, WindowInsets windowInsets, ModalBottomSheetProperties properties, final Function3 content, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        SheetState sheetState2;
        int $dirty;
        SheetState sheetState3;
        float sheetMaxWidth2;
        Shape shape2;
        long containerColor2;
        long contentColor2;
        long scrimColor2;
        WindowInsets windowInsets2;
        ModalBottomSheetProperties properties2;
        Function2 dragHandle2;
        int $dirty2;
        int $dirty1;
        Shape shape3;
        long containerColor3;
        long scrimColor3;
        final WindowInsets windowInsets3;
        float tonalElevation2;
        long contentColor3;
        Shape shape4;
        long containerColor4;
        long contentColor4;
        float tonalElevation3;
        float sheetMaxWidth3;
        Function2 dragHandle3;
        Modifier modifier3;
        SheetState sheetState4;
        WindowInsets windowInsets4;
        int $dirty3;
        Composer $composer2 = $composer.startRestartGroup(944867294);
        ComposerKt.sourceInformation($composer2, "C(ModalBottomSheet)P(5,4,10,9:c#ui.unit.Dp,8,0:c#ui.graphics.Color,2:c#ui.graphics.Color,11:c#ui.unit.Dp,7:c#ui.graphics.Color,3,12,6)235@10240L31,237@10371L13,238@10434L14,239@10476L31,241@10584L10,243@10731L12,247@10884L485:ModalBottomSheet.android.kt#uh7d8r");
        int $dirty4 = $changed;
        int $dirty5 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer2.changedInstance(onDismissRequest) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sheetState2 = sheetState;
                int i3 = $composer2.changed(sheetState2) ? 256 : 128;
                $dirty4 |= i3;
            } else {
                sheetState2 = sheetState;
            }
            $dirty4 |= i3;
        } else {
            sheetState2 = sheetState;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty4 |= $composer2.changed(sheetMaxWidth) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty4 |= ((i & 16) == 0 && $composer2.changed(shape)) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty4 |= ((i & 32) == 0 && $composer2.changed(containerColor)) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                int i5 = $composer2.changed(contentColor) ? 1048576 : 524288;
                $dirty = $dirty3 | i5;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i5;
        } else {
            $dirty = $dirty4;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(tonalElevation) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= ((i & 256) == 0 && $composer2.changed(scrimColor)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty |= 805306368;
        } else if ((805306368 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(dragHandle) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int $dirty6 = $dirty;
        if (($changed1 & 6) == 0) {
            $dirty5 |= ((i & 1024) == 0 && $composer2.changed(windowInsets)) ? 4 : 2;
        }
        int i8 = i & 2048;
        if (i8 != 0) {
            $dirty5 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty5 |= $composer2.changed(properties) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty5 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty5 |= $composer2.changedInstance(content) ? 256 : 128;
        }
        if (($dirty6 & 306783379) == 306783378 && ($dirty5 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            sheetMaxWidth3 = sheetMaxWidth;
            shape4 = shape;
            containerColor4 = containerColor;
            contentColor4 = contentColor;
            tonalElevation3 = tonalElevation;
            scrimColor3 = scrimColor;
            dragHandle3 = dragHandle;
            windowInsets4 = windowInsets;
            properties2 = properties;
            modifier3 = modifier2;
            sheetState4 = sheetState2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    sheetState3 = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, $composer2, 0, 3);
                    $dirty6 &= -897;
                } else {
                    sheetState3 = sheetState2;
                }
                sheetMaxWidth2 = i4 != 0 ? BottomSheetDefaults.INSTANCE.m2167getSheetMaxWidthD9Ej5fM() : sheetMaxWidth;
                if ((i & 16) != 0) {
                    shape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                    $dirty6 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((i & 32) != 0) {
                    $dirty6 &= -458753;
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 64) != 0) {
                    contentColor2 = ColorSchemeKt.m2304contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 15) & 14);
                    $dirty6 &= -3670017;
                } else {
                    contentColor2 = contentColor;
                }
                float tonalElevation4 = i6 != 0 ? Dp.m7016constructorimpl(0) : tonalElevation;
                if ((i & 256) != 0) {
                    scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty6 &= -234881025;
                } else {
                    scrimColor2 = scrimColor;
                }
                Function2 dragHandle4 = i7 != 0 ? ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m2346getLambda1$material3_release() : dragHandle;
                if ((i & 1024) != 0) {
                    windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty5 &= -15;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i8 != 0) {
                    dragHandle2 = dragHandle4;
                    $dirty2 = $dirty6;
                    $dirty1 = $dirty5;
                    properties2 = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    scrimColor3 = scrimColor2;
                    modifier2 = modifier4;
                    sheetState2 = sheetState3;
                    windowInsets3 = windowInsets2;
                    tonalElevation2 = tonalElevation4;
                    contentColor3 = contentColor2;
                } else {
                    properties2 = properties;
                    dragHandle2 = dragHandle4;
                    $dirty2 = $dirty6;
                    $dirty1 = $dirty5;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    scrimColor3 = scrimColor2;
                    modifier2 = modifier4;
                    sheetState2 = sheetState3;
                    windowInsets3 = windowInsets2;
                    tonalElevation2 = tonalElevation4;
                    contentColor3 = contentColor2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty6 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty6 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty6 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty6 &= -3670017;
                }
                if ((i & 256) != 0) {
                    $dirty6 &= -234881025;
                }
                if ((i & 1024) != 0) {
                    sheetMaxWidth2 = sheetMaxWidth;
                    contentColor3 = contentColor;
                    tonalElevation2 = tonalElevation;
                    scrimColor3 = scrimColor;
                    dragHandle2 = dragHandle;
                    windowInsets3 = windowInsets;
                    properties2 = properties;
                    $dirty1 = $dirty5 & (-15);
                    $dirty2 = $dirty6;
                    shape3 = shape;
                    containerColor3 = containerColor;
                } else {
                    sheetMaxWidth2 = sheetMaxWidth;
                    shape3 = shape;
                    contentColor3 = contentColor;
                    tonalElevation2 = tonalElevation;
                    scrimColor3 = scrimColor;
                    dragHandle2 = dragHandle;
                    windowInsets3 = windowInsets;
                    properties2 = properties;
                    $dirty2 = $dirty6;
                    $dirty1 = $dirty5;
                    containerColor3 = containerColor;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(944867294, $dirty2, $dirty1, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
            }
            ModalBottomSheetKt.m2616ModalBottomSheetdYc4hso(onDismissRequest, modifier2, sheetState2, sheetMaxWidth2, shape3, containerColor3, contentColor3, tonalElevation2, scrimColor3, dragHandle2, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer, Integer num) {
                    return invoke(composer, num.intValue());
                }

                public final WindowInsets invoke(Composer $composer3, int $changed2) {
                    $composer3.startReplaceGroup(-2061903609);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2061903609, $changed2, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                    }
                    WindowInsets windowInsets5 = windowInsets3;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer3.endReplaceGroup();
                    return windowInsets5;
                }
            }, properties2, content, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2), ($dirty1 & 112) | ($dirty1 & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape4 = shape3;
            containerColor4 = containerColor3;
            contentColor4 = contentColor3;
            tonalElevation3 = tonalElevation2;
            sheetMaxWidth3 = sheetMaxWidth2;
            dragHandle3 = dragHandle2;
            modifier3 = modifier2;
            sheetState4 = sheetState2;
            windowInsets4 = windowInsets3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final SheetState sheetState5 = sheetState4;
            final float f = sheetMaxWidth3;
            final Shape shape5 = shape4;
            final long j = containerColor4;
            final long j2 = contentColor4;
            final float f2 = tonalElevation3;
            final long j3 = scrimColor3;
            final Function2 function2 = dragHandle3;
            final WindowInsets windowInsets5 = windowInsets4;
            final ModalBottomSheetProperties modalBottomSheetProperties = properties2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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
                    ModalBottomSheet_androidKt.m2622ModalBottomSheetdYc4hso(onDismissRequest, modifier5, sheetState5, f, shape5, j, j2, f2, j3, function2, windowInsets5, modalBottomSheetProperties, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public static final void ModalBottomSheetDialog(final Function0<Unit> function0, final ModalBottomSheetProperties modalBottomSheetProperties, final Animatable<Float, AnimationVector1D> animatable, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        Object compositionScopedCoroutineScopeCanceller;
        Object obj;
        Object obj2;
        Object obj3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1254951810);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ModalBottomSheetDialog)P(1,3,2)273@11822L7,274@11861L7,275@11916L7,276@11946L28,277@12001L29,278@12050L38,279@12105L24,280@12157L21,282@12204L697,305@12932L129,305@12907L154,314@13078L182,314@13067L193:ModalBottomSheet.android.kt#uh7d8r");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(modalBottomSheetProperties) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= (i & 512) == 0 ? composerStartRestartGroup.changed(animatable) : composerStartRestartGroup.changedInstance(animatable) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        int i3 = i2;
        if ((i3 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1254951810, i3, -1, "androidx.compose.material3.ModalBottomSheetDialog (ModalBottomSheet.android.kt:272)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localView);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            View view = (View) objConsume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume2;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final LayoutDirection layoutDirection = (LayoutDirection) objConsume3;
            CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 9) & 14);
            UUID uuid = (UUID) RememberSaveableKt.m4120rememberSaveable(new Object[0], (Saver) null, (String) null, (Function0) new Function0<UUID>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$dialogId$1
                @Override // kotlin.jvm.functions.Function0
                public final UUID invoke() {
                    return UUID.randomUUID();
                }
            }, composerStartRestartGroup, 3072, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954363344, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
            } else {
                compositionScopedCoroutineScopeCanceller = objRememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) compositionScopedCoroutineScopeCanceller).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            boolean zIsSystemInDarkTheme = DarkThemeKt.isSystemInDarkTheme(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1981517173, "CC(remember):ModalBottomSheet.android.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(view) | composerStartRestartGroup.changed(density);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper = new ModalBottomSheetDialogWrapper(function0, modalBottomSheetProperties, view, layoutDirection, density, uuid, animatable, coroutineScope, zIsSystemInDarkTheme);
                modalBottomSheetDialogWrapper.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-1560960657, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$dialog$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer, int $changed) {
                        Function0<ComposeUiNode> function1;
                        ComposerKt.sourceInformation($composer, "C296@12687L164:ModalBottomSheet.android.kt#uh7d8r");
                        if (($changed & 3) != 2 || !$composer.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1560960657, $changed, -1, "androidx.compose.material3.ModalBottomSheetDialog.<anonymous>.<anonymous>.<anonymous> (ModalBottomSheet.android.kt:296)");
                            }
                            Modifier modifier$iv = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$dialog$1$1$1.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                    SemanticsPropertiesKt.dialog($this$semantics);
                                }
                            }, 1, null);
                            State<Function2<Composer, Integer, Unit>> state = stateRememberUpdatedState;
                            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
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
                                function1 = constructor;
                                $composer.createNode(function1);
                            } else {
                                function1 = constructor;
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
                            int i4 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i5 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -490070203, "C299@12809L16:ModalBottomSheet.android.kt#uh7d8r");
                            ModalBottomSheet_androidKt.ModalBottomSheetDialog$lambda$0(state).invoke($composer, 0);
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
                }));
                composerStartRestartGroup.updateRememberedValue(modalBottomSheetDialogWrapper);
                obj = modalBottomSheetDialogWrapper;
            } else {
                obj = objRememberedValue2;
            }
            final ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper2 = (ModalBottomSheetDialogWrapper) obj;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1981494445, "CC(remember):ModalBottomSheet.android.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(modalBottomSheetDialogWrapper2);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                obj2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        modalBottomSheetDialogWrapper2.show();
                        final ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper3 = modalBottomSheetDialogWrapper2;
                        return new DisposableEffectResult() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                modalBottomSheetDialogWrapper3.dismiss();
                                modalBottomSheetDialogWrapper3.disposeComposition();
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj2);
            } else {
                obj2 = objRememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.DisposableEffect(modalBottomSheetDialogWrapper2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) obj2, composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1981489720, "CC(remember):ModalBottomSheet.android.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(modalBottomSheetDialogWrapper2) | ((i3 & 14) == 4) | ((i3 & 112) == 32) | composerStartRestartGroup.changed(layoutDirection);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                obj3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$2$1
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
                        modalBottomSheetDialogWrapper2.updateParameters(function0, modalBottomSheetProperties, layoutDirection);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj3);
            } else {
                obj3 = objRememberedValue4;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.SideEffect((Function0) obj3, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt.ModalBottomSheetDialog.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    ModalBottomSheet_androidKt.ModalBottomSheetDialog(function0, modalBottomSheetProperties, animatable, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<Composer, Integer, Unit> ModalBottomSheetDialog$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    public static final boolean isFlagSecureEnabled(View $this$isFlagSecureEnabled) {
        ViewGroup.LayoutParams layoutParams = $this$isFlagSecureEnabled.getRootView().getLayoutParams();
        WindowManager.LayoutParams windowParams = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (windowParams == null || (windowParams.flags & 8192) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldApplySecureFlag(SecureFlagPolicy $this$shouldApplySecureFlag, boolean isSecureFlagSetOnParent) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$shouldApplySecureFlag.ordinal()]) {
            case 1:
                return false;
            case 2:
                return true;
            case 3:
                return isSecureFlagSetOnParent;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
