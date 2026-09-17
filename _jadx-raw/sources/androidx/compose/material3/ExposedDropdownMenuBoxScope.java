package androidx.compose.material3;

import android.view.View;
import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ExposedDropdownMenu.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002JU\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u0016J\u0098\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J¢\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010#\u001a\u00020\n2\b\b\u0002\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\u0016\u0010&\u001a\u00020\u000e*\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\nH&J\f\u0010'\u001a\u00020\u000e*\u00020\u000eH\u0007J(\u0010'\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010(\u001a\u00020\u00042\b\b\u0002\u0010)\u001a\u00020\nH&ø\u0001\u0000¢\u0006\u0004\b*\u0010+R\u0018\u0010\u0003\u001a\u00020\u0004X \u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001,\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006-"}, d2 = {"Landroidx/compose/material3/ExposedDropdownMenuBoxScope;", "", "()V", "anchorType", "Landroidx/compose/material3/MenuAnchorType;", "getAnchorType-Mg6Rgbw$material3_release", "()Ljava/lang/String;", "ExposedDropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "matchTextFieldWidth", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "ExposedDropdownMenu-vNxi1II", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;ZLandroidx/compose/ui/graphics/Shape;JFFLandroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "focusable", "ExposedDropdownMenu-kbRbctU", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;ZZLandroidx/compose/ui/graphics/Shape;JFFLandroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "exposedDropdownSize", "menuAnchor", "type", "enabled", "menuAnchor-fsE2BvY", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Z)Landroidx/compose/ui/Modifier;", "Landroidx/compose/material3/ExposedDropdownMenuBoxScopeImpl;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ExposedDropdownMenuBoxScope {
    public static final int $stable = 0;

    public /* synthetic */ ExposedDropdownMenuBoxScope(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Modifier exposedDropdownSize(Modifier modifier, boolean z);

    /* JADX INFO: renamed from: getAnchorType-Mg6Rgbw$material3_release, reason: not valid java name */
    public abstract String mo2456getAnchorTypeMg6Rgbw$material3_release();

    /* JADX INFO: renamed from: menuAnchor-fsE2BvY, reason: not valid java name */
    public abstract Modifier mo2457menuAnchorfsE2BvY(Modifier modifier, String str, boolean z);

    private ExposedDropdownMenuBoxScope() {
    }

    /* JADX INFO: renamed from: menuAnchor-fsE2BvY$default, reason: not valid java name */
    public static /* synthetic */ Modifier m2453menuAnchorfsE2BvY$default(ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, Modifier modifier, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: menuAnchor-fsE2BvY");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return exposedDropdownMenuBoxScope.mo2457menuAnchorfsE2BvY(modifier, str, z);
    }

    public static /* synthetic */ Modifier exposedDropdownSize$default(ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, Modifier modifier, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: exposedDropdownSize");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        return exposedDropdownMenuBoxScope.exposedDropdownSize(modifier, z);
    }

    /* JADX INFO: renamed from: ExposedDropdownMenu-vNxi1II, reason: not valid java name */
    public final void m2455ExposedDropdownMenuvNxi1II(final boolean expanded, final Function0<Unit> function0, Modifier modifier, ScrollState scrollState, boolean matchTextFieldWidth, Shape shape, long containerColor, float tonalElevation, float shadowElevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        ScrollState scrollState2;
        Modifier.Companion modifier2;
        ScrollState scrollState3;
        boolean matchTextFieldWidth2;
        Shape shape2;
        long containerColor2;
        float tonalElevation2;
        BorderStroke border2;
        float shadowElevation2;
        int $dirty;
        long containerColor3;
        final float shadowElevation3;
        Object value$iv;
        Object value$iv2;
        BorderStroke border3;
        Modifier modifier3;
        ScrollState scrollState4;
        boolean matchTextFieldWidth3;
        Shape shape3;
        Composer $composer2 = $composer.startRestartGroup(720925481);
        ComposerKt.sourceInformation($composer2, "C(ExposedDropdownMenu)P(3,6,5,7,4,9,1:c#ui.graphics.Color,10:c#ui.unit.Dp,8:c#ui.unit.Dp)336@15550L21,338@15654L5,339@15706L14,347@16155L53,348@16238L7,349@16281L7,350@16332L10,357@16596L42,361@16795L51,363@16903L486,377@17583L27,378@17626L587,374@17403L810:ExposedDropdownMenu.android.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(expanded) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                scrollState2 = scrollState;
                int i3 = $composer2.changed(scrollState2) ? 2048 : 1024;
                $dirty2 |= i3;
            } else {
                scrollState2 = scrollState;
            }
            $dirty2 |= i3;
        } else {
            scrollState2 = scrollState;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(matchTextFieldWidth) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= ((i & 32) == 0 && $composer2.changed(shape)) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer2.changed(containerColor)) ? 1048576 : 524288;
        }
        int i5 = i & 128;
        if (i5 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changed(tonalElevation) ? 8388608 : 4194304;
        }
        int i6 = i & 256;
        if (i6 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer2.changed(shadowElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer2.changed(border) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changedInstance(function3) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changed(this) ? 32 : 16;
        }
        if (($dirty2 & 306783379) == 306783378 && ($dirty1 & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            matchTextFieldWidth3 = matchTextFieldWidth;
            shape3 = shape;
            containerColor3 = containerColor;
            tonalElevation2 = tonalElevation;
            shadowElevation3 = shadowElevation;
            border3 = border;
            scrollState4 = scrollState2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 8) != 0) {
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                    $dirty2 &= -7169;
                } else {
                    scrollState3 = scrollState2;
                }
                matchTextFieldWidth2 = i4 != 0 ? true : matchTextFieldWidth;
                if ((i & 32) != 0) {
                    shape2 = MenuDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -458753;
                } else {
                    shape2 = shape;
                }
                if ((i & 64) != 0) {
                    containerColor2 = MenuDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty2 &= -3670017;
                } else {
                    containerColor2 = containerColor;
                }
                tonalElevation2 = i5 != 0 ? MenuDefaults.INSTANCE.m2602getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i6 != 0 ? MenuDefaults.INSTANCE.m2601getShadowElevationD9Ej5fM() : shadowElevation;
                if (i7 != 0) {
                    $dirty = $dirty2;
                    border2 = null;
                    shadowElevation2 = shadowElevation4;
                    containerColor3 = containerColor2;
                } else {
                    border2 = border;
                    shadowElevation2 = shadowElevation4;
                    $dirty = $dirty2;
                    containerColor3 = containerColor2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    modifier2 = modifier;
                    shape2 = shape;
                    tonalElevation2 = tonalElevation;
                    shadowElevation2 = shadowElevation;
                    border2 = border;
                    $dirty = (-3670017) & $dirty2;
                    scrollState3 = scrollState2;
                    matchTextFieldWidth2 = matchTextFieldWidth;
                    containerColor3 = containerColor;
                } else {
                    modifier2 = modifier;
                    shape2 = shape;
                    tonalElevation2 = tonalElevation;
                    shadowElevation2 = shadowElevation;
                    border2 = border;
                    $dirty = $dirty2;
                    scrollState3 = scrollState2;
                    matchTextFieldWidth2 = matchTextFieldWidth;
                    containerColor3 = containerColor;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(720925481, $dirty, $dirty1, "androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu (ExposedDropdownMenu.android.kt:344)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 321492941, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
            Object value$iv3 = $composer2.rememberedValue();
            if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
                $composer2.updateRememberedValue(value$iv3);
            }
            final MutableState keyboardSignalState = (MutableState) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            int $dirty3 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            View view = (View) objConsume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            final BorderStroke border4 = border2;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density = (Density) objConsume2;
            int topWindowInsets = WindowInsets_androidKt.getStatusBars(WindowInsets.INSTANCE, $composer2, 6).getTop(density);
            $composer2.startReplaceGroup(321499814);
            ComposerKt.sourceInformation($composer2, "353@16432L36,353@16396L72");
            if (expanded) {
                ComposerKt.sourceInformationMarkerStart($composer2, 321501788, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
                Object value$iv4 = $composer2.rememberedValue();
                if (value$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$1$1
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
                            keyboardSignalState.setValue(Unit.INSTANCE);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv4);
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ExposedDropdownMenu_androidKt.SoftKeyboardListener(view, density, (Function0) value$iv4, $composer2, 384);
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerStart($composer2, 321507042, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
            Object value$iv5 = $composer2.rememberedValue();
            if (value$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = new MutableTransitionState(false);
                $composer2.updateRememberedValue(value$iv5);
            }
            final MutableTransitionState expandedState = (MutableTransitionState) value$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            expandedState.setTargetState$animation_core_release(Boolean.valueOf(expanded));
            if (((Boolean) expandedState.getCurrentState()).booleanValue() || ((Boolean) expandedState.getTargetState()).booleanValue()) {
                ComposerKt.sourceInformationMarkerStart($composer2, 321513419, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                shadowElevation3 = shadowElevation2;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m4912boximpl(TransformOrigin.INSTANCE.m4925getCenterSzJe1aQ()), null, 2, null);
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                final MutableState transformOriginState = (MutableState) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 321517310, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
                boolean invalid$iv = $composer2.changed(density) | $composer2.changed(topWindowInsets);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new ExposedDropdownMenuPositionProvider(density, topWindowInsets, keyboardSignalState, 0, new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$popupPositionProvider$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                            invoke2(intRect, intRect2);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IntRect anchorBounds, IntRect menuBounds) {
                            transformOriginState.setValue(TransformOrigin.m4912boximpl(MenuKt.calculateTransformOrigin(anchorBounds, menuBounds)));
                        }
                    }, 8, null);
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv2;
                }
                ExposedDropdownMenuPositionProvider popupPositionProvider = (ExposedDropdownMenuPositionProvider) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Modifier modifier4 = modifier2;
                final boolean z = matchTextFieldWidth2;
                final ScrollState scrollState5 = scrollState3;
                final Shape shape4 = shape2;
                final long j = containerColor3;
                final float f = tonalElevation2;
                AndroidPopup_androidKt.Popup(popupPositionProvider, function0, ExposedDropdownMenuDefaults.INSTANCE.m2461popupPropertiespR6Bxps$material3_release(mo2456getAnchorTypeMg6Rgbw$material3_release(), $composer2, 48), ComposableLambdaKt.rememberComposableLambda(-1082380263, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu.2
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
                        ComposerKt.sourceInformation($composer3, "C379@17644L555:ExposedDropdownMenu.android.kt#uh7d8r");
                        if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1082380263, $changed2, -1, "androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu.<anonymous> (ExposedDropdownMenu.android.kt:379)");
                            }
                            MenuKt.m2615DropdownMenuContentQj0Zi0g(ExposedDropdownMenuBoxScope.this.exposedDropdownSize(modifier4, z), expandedState, transformOriginState, scrollState5, shape4, j, f, shadowElevation3, border4, function3, $composer3, (MutableTransitionState.$stable << 3) | 384);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }, $composer2, 54), $composer2, ($dirty3 & 112) | 3072, 0);
            } else {
                containerColor3 = containerColor3;
                shadowElevation3 = shadowElevation2;
                tonalElevation2 = tonalElevation2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border3 = border4;
            modifier3 = modifier2;
            scrollState4 = scrollState3;
            matchTextFieldWidth3 = matchTextFieldWidth2;
            shape3 = shape2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final ScrollState scrollState6 = scrollState4;
            final boolean z2 = matchTextFieldWidth3;
            final Shape shape5 = shape3;
            final long j2 = containerColor3;
            final float f2 = tonalElevation2;
            final float f3 = shadowElevation3;
            final BorderStroke borderStroke = border3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu.3
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
                    ExposedDropdownMenuBoxScope.this.m2455ExposedDropdownMenuvNxi1II(expanded, function0, modifier5, scrollState6, z2, shape5, j2, f2, f3, borderStroke, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use overload that takes MenuAnchorType and enabled parameters", replaceWith = @ReplaceWith(expression = "menuAnchor(type, enabled)", imports = {}))
    public final Modifier menuAnchor(Modifier $this$menuAnchor) {
        return m2453menuAnchorfsE2BvY$default(this, $this$menuAnchor, MenuAnchorType.INSTANCE.m2599getPrimaryNotEditableMg6Rgbw(), false, 2, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "The `focusable` parameter is unused. Pass the proper MenuAnchorType to Modifier.menuAnchor instead, which will handle focusability automatically.")
    /* JADX INFO: renamed from: ExposedDropdownMenu-kbRbctU, reason: not valid java name */
    public final void m2454ExposedDropdownMenukbRbctU(final boolean expanded, final Function0<Unit> function0, Modifier modifier, ScrollState scrollState, boolean focusable, boolean matchTextFieldWidth, Shape shape, long containerColor, float tonalElevation, float shadowElevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        Shape shape2;
        long containerColor2;
        ScrollState scrollState2;
        Shape shape3;
        BorderStroke border2;
        float shadowElevation2;
        float tonalElevation2;
        boolean matchTextFieldWidth2;
        Modifier modifier2;
        boolean focusable2;
        int $dirty;
        ScrollState scrollState3;
        boolean focusable3;
        Modifier modifier3;
        ScrollState scrollState4;
        boolean matchTextFieldWidth3;
        float tonalElevation3;
        Shape shape4;
        float shadowElevation3;
        BorderStroke border3;
        long containerColor3;
        Composer $composer2 = $composer.startRestartGroup(366140493);
        ComposerKt.sourceInformation($composer2, "C(ExposedDropdownMenu)P(3,7,6,8,4,5,10,1:c#ui.graphics.Color,11:c#ui.unit.Dp,9:c#ui.unit.Dp)414@19050L21,417@19189L5,418@19241L14,424@19482L463:ExposedDropdownMenu.android.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(expanded) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= ((i & 8) == 0 && $composer2.changed(scrollState)) ? 2048 : 1024;
        }
        int i3 = i & 32;
        if (i3 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer2.changed(matchTextFieldWidth) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                shape2 = shape;
                int i4 = $composer2.changed(shape2) ? 1048576 : 524288;
                $dirty2 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                containerColor2 = containerColor;
                int i5 = $composer2.changed(containerColor2) ? 8388608 : 4194304;
                $dirty2 |= i5;
            } else {
                containerColor2 = containerColor;
            }
            $dirty2 |= i5;
        } else {
            containerColor2 = containerColor;
        }
        int i6 = i & 256;
        if (i6 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer2.changed(tonalElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer2.changed(shadowElevation) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i8 = i & 1024;
        if (i8 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changed(border) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty1 |= $composer2.changed(this) ? 256 : 128;
        }
        if (($dirty2 & 306775187) == 306775186 && ($dirty1 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            scrollState4 = scrollState;
            focusable3 = focusable;
            matchTextFieldWidth3 = matchTextFieldWidth;
            tonalElevation3 = tonalElevation;
            shadowElevation3 = shadowElevation;
            border3 = border;
            shape4 = shape2;
            containerColor3 = containerColor2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 8) != 0) {
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                    $dirty2 &= -7169;
                } else {
                    scrollState2 = scrollState;
                }
                boolean focusable4 = (i & 16) != 0 ? true : focusable;
                boolean matchTextFieldWidth4 = i3 != 0 ? true : matchTextFieldWidth;
                Modifier modifier5 = modifier4;
                if ((i & 64) != 0) {
                    shape3 = MenuDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -3670017;
                } else {
                    shape3 = shape2;
                }
                if ((i & 128) != 0) {
                    containerColor2 = MenuDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty2 &= -29360129;
                }
                float tonalElevation4 = i6 != 0 ? MenuDefaults.INSTANCE.m2602getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i7 != 0 ? MenuDefaults.INSTANCE.m2601getShadowElevationD9Ej5fM() : shadowElevation;
                if (i8 != 0) {
                    shadowElevation2 = shadowElevation4;
                    border2 = null;
                    modifier2 = modifier5;
                    tonalElevation2 = tonalElevation4;
                    focusable2 = focusable4;
                    matchTextFieldWidth2 = matchTextFieldWidth4;
                    $dirty = $dirty2;
                    scrollState3 = scrollState2;
                } else {
                    border2 = border;
                    shadowElevation2 = shadowElevation4;
                    tonalElevation2 = tonalElevation4;
                    matchTextFieldWidth2 = matchTextFieldWidth4;
                    modifier2 = modifier5;
                    focusable2 = focusable4;
                    $dirty = $dirty2;
                    scrollState3 = scrollState2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    int i9 = (-29360129) & $dirty2;
                    scrollState3 = scrollState;
                    matchTextFieldWidth2 = matchTextFieldWidth;
                    tonalElevation2 = tonalElevation;
                    border2 = border;
                    $dirty = i9;
                    shape3 = shape2;
                    modifier2 = modifier;
                    focusable2 = focusable;
                    shadowElevation2 = shadowElevation;
                } else {
                    modifier2 = modifier;
                    focusable2 = focusable;
                    matchTextFieldWidth2 = matchTextFieldWidth;
                    tonalElevation2 = tonalElevation;
                    border2 = border;
                    $dirty = $dirty2;
                    shape3 = shape2;
                    scrollState3 = scrollState;
                    shadowElevation2 = shadowElevation;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(366140493, $dirty, $dirty1, "androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu (ExposedDropdownMenu.android.kt:424)");
            }
            m2455ExposedDropdownMenuvNxi1II(expanded, function0, modifier2, scrollState3, matchTextFieldWidth2, shape3, containerColor2, tonalElevation2, shadowElevation2, border2, function3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty >> 3) & 234881024) | (($dirty1 << 27) & 1879048192), (($dirty1 >> 3) & 14) | (($dirty1 >> 3) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            focusable3 = focusable2;
            modifier3 = modifier2;
            scrollState4 = scrollState3;
            matchTextFieldWidth3 = matchTextFieldWidth2;
            tonalElevation3 = tonalElevation2;
            shape4 = shape3;
            shadowElevation3 = shadowElevation2;
            border3 = border2;
            containerColor3 = containerColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            final ScrollState scrollState5 = scrollState4;
            final boolean z = focusable3;
            final boolean z2 = matchTextFieldWidth3;
            final Shape shape5 = shape4;
            final long j = containerColor3;
            final float f = tonalElevation3;
            final float f2 = shadowElevation3;
            final BorderStroke borderStroke = border3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu.4
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

                public final void invoke(Composer composer, int i10) {
                    ExposedDropdownMenuBoxScope.this.m2454ExposedDropdownMenukbRbctU(expanded, function0, modifier6, scrollState5, z, z2, shape5, j, f, f2, borderStroke, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with customization options parameters.")
    public final /* synthetic */ void ExposedDropdownMenu(final boolean expanded, final Function0 onDismissRequest, Modifier modifier, ScrollState scrollState, final Function3 content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        ScrollState scrollState2;
        Modifier.Companion modifier3;
        Modifier modifier4;
        ScrollState scrollState3;
        Composer $composer2 = $composer.startRestartGroup(1729549851);
        ComposerKt.sourceInformation($composer2, "C(ExposedDropdownMenu)P(1,3,2,4)449@20337L21,458@20677L5,459@20726L14,452@20429L498:ExposedDropdownMenu.android.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(expanded) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(onDismissRequest) ? 32 : 16;
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
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                scrollState2 = scrollState;
                int i3 = $composer2.changed(scrollState2) ? 2048 : 1024;
                $dirty |= i3;
            } else {
                scrollState2 = scrollState;
            }
            $dirty |= i3;
        } else {
            scrollState2 = scrollState;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changed(this) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            scrollState3 = scrollState2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1729549851, $dirty, -1, "androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu (ExposedDropdownMenu.android.kt:452)");
            }
            m2455ExposedDropdownMenuvNxi1II(expanded, onDismissRequest, modifier3, scrollState2, true, MenuDefaults.INSTANCE.getShape($composer2, 6), MenuDefaults.INSTANCE.getContainerColor($composer2, 6), MenuDefaults.INSTANCE.m2602getTonalElevationD9Ej5fM(), MenuDefaults.INSTANCE.m2601getShadowElevationD9Ej5fM(), null, content, $composer2, ($dirty & 14) | 918577152 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), (($dirty >> 12) & 14) | (($dirty >> 12) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            scrollState3 = scrollState2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final ScrollState scrollState4 = scrollState3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu.5
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
                    ExposedDropdownMenuBoxScope.this.ExposedDropdownMenu(expanded, onDismissRequest, modifier5, scrollState4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
