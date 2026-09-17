package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AndroidAlertDialog.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aß\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0015\b\u0002\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"AlertDialog", "", "onDismissRequest", "Lkotlin/Function0;", "confirmButton", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "dismissButton", "icon", "title", "text", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "iconContentColor", "titleContentColor", "textContentColor", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "AlertDialog-Oix01E0", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JJJJFLandroidx/compose/ui/window/DialogProperties;Landroidx/compose/runtime/Composer;III)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidAlertDialog_androidKt {
    /* JADX INFO: renamed from: AlertDialog-Oix01E0, reason: not valid java name */
    public static final void m2135AlertDialogOix01E0(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Shape shape, long containerColor, long iconContentColor, long titleContentColor, long textContentColor, float tonalElevation, DialogProperties properties, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Shape shape2;
        Function2<? super Composer, ? super Integer, Unit> function9;
        long containerColor2;
        long iconContentColor2;
        long titleContentColor2;
        long textContentColor2;
        DialogProperties properties2;
        float tonalElevation2;
        int $dirty;
        int $dirty1;
        Function2<? super Composer, ? super Integer, Unit> function10;
        long containerColor3;
        long iconContentColor3;
        long titleContentColor3;
        long textContentColor3;
        Modifier modifier2;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function11;
        long containerColor4;
        Function2<? super Composer, ? super Integer, Unit> function12;
        long iconContentColor4;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Shape shape3;
        Composer $composer2 = $composer.startRestartGroup(-2081346864);
        ComposerKt.sourceInformation($composer2, "C(AlertDialog)P(6!1,5,2,3,11,9,8,1:c#ui.graphics.Color,4:c#ui.graphics.Color,12:c#ui.graphics.Color,10:c#ui.graphics.Color,13:c#ui.unit.Dp)62@4918L5,62@4973L14,62@5039L16,62@5108L17,62@5177L16,46@1648L513:AndroidAlertDialog.android.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty3 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changedInstance(function4) ? 16384 : 8192;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function7 = function5;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function7 = function5;
            $dirty2 |= $composer2.changedInstance(function7) ? 131072 : 65536;
        } else {
            function7 = function5;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            function8 = function6;
        } else if (($changed & 1572864) == 0) {
            function8 = function6;
            $dirty2 |= $composer2.changedInstance(function8) ? 1048576 : 524288;
        } else {
            function8 = function6;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                shape2 = shape;
                int i7 = $composer2.changed(shape2) ? 8388608 : 4194304;
                $dirty2 |= i7;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i7;
        } else {
            shape2 = shape;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer2.changed(containerColor)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & $changed) == 0) {
            $dirty2 |= ((i & 512) == 0 && $composer2.changed(iconContentColor)) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        if (($changed1 & 6) == 0) {
            $dirty3 |= ((i & 1024) == 0 && $composer2.changed(titleContentColor)) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty3 |= ((i & 2048) == 0 && $composer2.changed(textContentColor)) ? 32 : 16;
        }
        int i8 = i & 4096;
        if (i8 != 0) {
            $dirty3 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty3 |= $composer2.changed(tonalElevation) ? 256 : 128;
        }
        int i9 = i & 8192;
        if (i9 != 0) {
            $dirty3 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty3 |= $composer2.changed(properties) ? 2048 : 1024;
        }
        if (($dirty2 & 306783379) == 306783378 && ($dirty3 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            function12 = function3;
            function11 = function4;
            containerColor4 = containerColor;
            iconContentColor4 = iconContentColor;
            titleContentColor3 = titleContentColor;
            textContentColor3 = textContentColor;
            tonalElevation2 = tonalElevation;
            properties2 = properties;
            function13 = function7;
            function14 = function8;
            shape3 = shape2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                function9 = i3 != 0 ? null : function3;
                Function2<? super Composer, ? super Integer, Unit> function15 = i4 != 0 ? null : function4;
                if (i5 != 0) {
                    function7 = null;
                }
                if (i6 != 0) {
                    function8 = null;
                }
                Modifier modifier5 = modifier4;
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                    shape2 = AlertDialogDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 256) != 0) {
                    containerColor2 = AlertDialogDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty2 &= -234881025;
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 512) != 0) {
                    iconContentColor2 = AlertDialogDefaults.INSTANCE.getIconContentColor($composer2, 6);
                    $dirty2 &= -1879048193;
                } else {
                    iconContentColor2 = iconContentColor;
                }
                if ((i & 1024) != 0) {
                    titleContentColor2 = AlertDialogDefaults.INSTANCE.getTitleContentColor($composer2, 6);
                    $dirty3 &= -15;
                } else {
                    titleContentColor2 = titleContentColor;
                }
                if ((i & 2048) != 0) {
                    textContentColor2 = AlertDialogDefaults.INSTANCE.getTextContentColor($composer2, 6);
                    $dirty3 &= -113;
                } else {
                    textContentColor2 = textContentColor;
                }
                float tonalElevation3 = i8 != 0 ? AlertDialogDefaults.INSTANCE.m2129getTonalElevationD9Ej5fM() : tonalElevation;
                if (i9 != 0) {
                    tonalElevation2 = tonalElevation3;
                    $dirty = $dirty2;
                    $dirty1 = $dirty3;
                    properties2 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    function10 = function15;
                    containerColor3 = containerColor2;
                    iconContentColor3 = iconContentColor2;
                    titleContentColor3 = titleContentColor2;
                    textContentColor3 = textContentColor2;
                    modifier2 = modifier5;
                } else {
                    properties2 = properties;
                    tonalElevation2 = tonalElevation3;
                    $dirty = $dirty2;
                    $dirty1 = $dirty3;
                    function10 = function15;
                    containerColor3 = containerColor2;
                    iconContentColor3 = iconContentColor2;
                    titleContentColor3 = titleContentColor2;
                    textContentColor3 = textContentColor2;
                    modifier2 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty2 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty2 &= -1879048193;
                }
                if ((i & 1024) != 0) {
                    $dirty3 &= -15;
                }
                if ((i & 2048) != 0) {
                    int i10 = $dirty3 & (-113);
                    function9 = function3;
                    containerColor3 = containerColor;
                    iconContentColor3 = iconContentColor;
                    titleContentColor3 = titleContentColor;
                    textContentColor3 = textContentColor;
                    tonalElevation2 = tonalElevation;
                    properties2 = properties;
                    $dirty1 = i10;
                    $dirty = $dirty2;
                    modifier2 = modifier;
                    function10 = function4;
                } else {
                    modifier2 = modifier;
                    function9 = function3;
                    iconContentColor3 = iconContentColor;
                    titleContentColor3 = titleContentColor;
                    textContentColor3 = textContentColor;
                    tonalElevation2 = tonalElevation;
                    properties2 = properties;
                    $dirty = $dirty2;
                    $dirty1 = $dirty3;
                    function10 = function4;
                    containerColor3 = containerColor;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2081346864, $dirty, $dirty1, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
            }
            AlertDialogKt.m2132AlertDialogImplwrnwzgE(function0, function2, modifier2, function9, function10, function7, function8, shape2, containerColor3, iconContentColor3, titleContentColor3, textContentColor3, tonalElevation2, properties2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), ($dirty1 & 14) | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function11 = function10;
            containerColor4 = containerColor3;
            function12 = function9;
            iconContentColor4 = iconContentColor3;
            function13 = function7;
            function14 = function8;
            shape3 = shape2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function13;
            final Function2<? super Composer, ? super Integer, Unit> function19 = function14;
            final Shape shape4 = shape3;
            final long j = containerColor4;
            final long j2 = iconContentColor4;
            final long j3 = titleContentColor3;
            final long j4 = textContentColor3;
            final float f = tonalElevation2;
            final DialogProperties dialogProperties = properties2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidAlertDialog_androidKt$AlertDialog$1
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

                public final void invoke(Composer composer, int i11) {
                    AndroidAlertDialog_androidKt.m2135AlertDialogOix01E0(function0, function2, modifier6, function16, function17, function18, function19, shape4, j, j2, j3, j4, f, dialogProperties, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }
}
