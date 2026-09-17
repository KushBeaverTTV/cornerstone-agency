package androidx.compose.material3;

import ai.nous.aurora.domain.model.ComposerChipPolicy;
import ai.nous.aurora.presentation.theme.AuroraMotion;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.material3.internal.DraggableAnchorsConfig;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.NavigationDrawerTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NavigationDrawer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001at\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001al\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001aQ\u0010\u001f\u001a\u00020\t2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u00192\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020#2\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010$\u001at\u0010%\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001at\u0010*\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010\u001c\u001al\u0010*\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010\u001e\u001a`\u0010-\u001a\u00020\t2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u00192\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010.\u001a\u00020\u00112\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u008e\u0001\u00101\u001a\u00020\t2\u0011\u00102\u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u00192\u0006\u00103\u001a\u00020#2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\t0!2\b\b\u0002\u0010\f\u001a\u00020\r2\u0015\b\u0002\u00105\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010!¢\u0006\u0002\b\u00192\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010!¢\u0006\u0002\b\u00192\b\b\u0002\u00107\u001a\u00020\u000f2\b\b\u0002\u00108\u001a\u0002092\n\b\u0002\u0010:\u001a\u0004\u0018\u00010;H\u0007¢\u0006\u0002\u0010<\u001al\u0010=\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b>\u0010\u001e\u001a=\u0010?\u001a\u00020\t2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u00192\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010@\u001a>\u0010A\u001a\u00020\t2\u0006\u0010B\u001a\u00020#2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\t0!2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00020!2\u0006\u0010E\u001a\u00020\u0011H\u0003ø\u0001\u0000¢\u0006\u0004\bF\u0010G\u001a \u0010H\u001a\u00020\u00022\u0006\u0010I\u001a\u00020\u00022\u0006\u0010J\u001a\u00020\u00022\u0006\u0010K\u001a\u00020\u0002H\u0002\u001a+\u0010L\u001a\u00020\u000b2\u0006\u0010M\u001a\u00020N2\u0014\b\u0002\u0010O\u001a\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020#0\u0017H\u0007¢\u0006\u0002\u0010P\u001a\u0014\u0010Q\u001a\u00020\u0002*\u00020R2\u0006\u0010&\u001a\u00020'H\u0002\u001a\u0014\u0010S\u001a\u00020\u0002*\u00020R2\u0006\u0010&\u001a\u00020'H\u0002\u001a\u001c\u0010T\u001a\u00020\r*\u00020\r2\u0006\u0010&\u001a\u00020'2\u0006\u0010U\u001a\u00020#H\u0002\u001a\u001c\u0010V\u001a\u00020\r*\u00020\r2\u0006\u0010&\u001a\u00020'2\u0006\u0010U\u001a\u00020#H\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0002X\u0082D¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006W²\u0006\n\u0010X\u001a\u00020#X\u008a\u008e\u0002²\u0006\n\u0010Y\u001a\u00020\u0002X\u008a\u008e\u0002²\u0006\n\u0010X\u001a\u00020#X\u008a\u008e\u0002"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "DrawerPositionalThreshold", "DrawerVelocityThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "MinimumDrawerWidth", "DismissibleDrawerSheet", "", "drawerState", "Landroidx/compose/material3/DrawerState;", "modifier", "Landroidx/compose/ui/Modifier;", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerContainerColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "drawerTonalElevation", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DismissibleDrawerSheet-Snr_uVM", "(Landroidx/compose/material3/DrawerState;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DismissibleDrawerSheet-afqeVBk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DismissibleNavigationDrawer", "drawerContent", "Lkotlin/Function0;", "gesturesEnabled", "", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DrawerSheet", "drawerPredictiveBackState", "Landroidx/compose/material3/DrawerPredictiveBackState;", "DrawerSheet-7zSek6w", "(Landroidx/compose/material3/DrawerPredictiveBackState;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ModalDrawerSheet", "ModalDrawerSheet-Snr_uVM", "ModalDrawerSheet-afqeVBk", "ModalNavigationDrawer", "scrimColor", "ModalNavigationDrawer-FHprtrg", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "NavigationDrawerItem", "label", "selected", "onClick", "icon", "badge", "shape", "colors", "Landroidx/compose/material3/NavigationDrawerItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/NavigationDrawerItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "PermanentDrawerSheet", "PermanentDrawerSheet-afqeVBk", "PermanentNavigationDrawer", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Scrim", "open", "onClose", "fraction", "color", "Scrim-Bx497Mc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "calculateFraction", "a", "b", "pos", "rememberDrawerState", "initialValue", "Landroidx/compose/material3/DrawerValue;", "confirmStateChange", "(Landroidx/compose/material3/DrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DrawerState;", "calculatePredictiveBackScaleX", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "calculatePredictiveBackScaleY", "predictiveBackDrawerChild", "isRtl", "predictiveBackDrawerContainer", "material3_release", "anchorsInitialized", "minValue"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavigationDrawerKt {
    private static final float DrawerPositionalThreshold = 0.5f;
    private static final float DrawerVelocityThreshold = Dp.m7016constructorimpl(400);
    private static final float MinimumDrawerWidth = Dp.m7016constructorimpl(AuroraMotion.TAB_MS);
    private static final TweenSpec<Float> AnimationSpec = new TweenSpec<>(256, 0, null, 6, null);

    public static final DrawerState rememberDrawerState(final DrawerValue initialValue, final Function1<? super DrawerValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 2098699222, "C(rememberDrawerState)P(1)286@11488L61,286@11424L125:NavigationDrawer.kt#uh7d8r");
        if ((i & 2) != 0) {
            Function1 confirmStateChange = new Function1<DrawerValue, Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt.rememberDrawerState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DrawerValue it) {
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2098699222, $changed, -1, "androidx.compose.material3.rememberDrawerState (NavigationDrawer.kt:285)");
        }
        Object[] objArr = new Object[0];
        Saver<DrawerState, DrawerValue> Saver = DrawerState.INSTANCE.Saver(function1);
        ComposerKt.sourceInformationMarkerStart($composer, -666801427, "CC(remember):NavigationDrawer.kt#9igjgp");
        boolean invalid$iv = (((($changed & 112) ^ 48) > 32 && $composer.changed(function1)) || ($changed & 48) == 32) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue)) || ($changed & 6) == 4);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function0<DrawerState>() { // from class: androidx.compose.material3.NavigationDrawerKt$rememberDrawerState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final DrawerState invoke() {
                    return new DrawerState(initialValue, function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        DrawerState drawerState = (DrawerState) RememberSaveableKt.m4120rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return drawerState;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x015f  */
    /* JADX WARN: Code duplicated, block: B:102:0x017c  */
    /* JADX WARN: Code duplicated, block: B:105:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:106:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:109:0x020b  */
    /* JADX WARN: Code duplicated, block: B:113:0x0218 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:117:0x023e  */
    /* JADX WARN: Code duplicated, block: B:119:0x0244  */
    /* JADX WARN: Code duplicated, block: B:125:0x025f  */
    /* JADX WARN: Code duplicated, block: B:129:0x026d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:133:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:134:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:137:0x032c  */
    /* JADX WARN: Code duplicated, block: B:140:0x0338  */
    /* JADX WARN: Code duplicated, block: B:141:0x033c  */
    /* JADX WARN: Code duplicated, block: B:144:0x036b  */
    /* JADX WARN: Code duplicated, block: B:148:0x0381 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:152:0x0437  */
    /* JADX WARN: Code duplicated, block: B:155:0x0443  */
    /* JADX WARN: Code duplicated, block: B:156:0x0449  */
    /* JADX WARN: Code duplicated, block: B:159:0x047c  */
    /* JADX WARN: Code duplicated, block: B:163:0x0492 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:167:0x0515  */
    /* JADX WARN: Code duplicated, block: B:168:0x0517  */
    /* JADX WARN: Code duplicated, block: B:171:0x0520  */
    /* JADX WARN: Code duplicated, block: B:173:0x0526  */
    /* JADX WARN: Code duplicated, block: B:179:0x053e  */
    /* JADX WARN: Code duplicated, block: B:183:0x0549  */
    /* JADX WARN: Code duplicated, block: B:186:0x0573  */
    /* JADX WARN: Code duplicated, block: B:188:0x0579  */
    /* JADX WARN: Code duplicated, block: B:194:0x058c  */
    /* JADX WARN: Code duplicated, block: B:198:0x059d  */
    /* JADX WARN: Code duplicated, block: B:201:0x05d8  */
    /* JADX WARN: Code duplicated, block: B:203:0x05de  */
    /* JADX WARN: Code duplicated, block: B:209:0x05f0  */
    /* JADX WARN: Code duplicated, block: B:213:0x05fd A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:217:0x062d  */
    /* JADX WARN: Code duplicated, block: B:219:0x0633  */
    /* JADX WARN: Code duplicated, block: B:225:0x064c  */
    /* JADX WARN: Code duplicated, block: B:229:0x0659 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:233:0x0686  */
    /* JADX WARN: Code duplicated, block: B:235:0x068c  */
    /* JADX WARN: Code duplicated, block: B:241:0x06a2  */
    /* JADX WARN: Code duplicated, block: B:245:0x06b3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:249:0x070d  */
    /* JADX WARN: Code duplicated, block: B:252:0x0719  */
    /* JADX WARN: Code duplicated, block: B:253:0x071d  */
    /* JADX WARN: Code duplicated, block: B:256:0x074b  */
    /* JADX WARN: Code duplicated, block: B:259:0x075e  */
    /* JADX WARN: Code duplicated, block: B:260:0x0761  */
    /* JADX WARN: Code duplicated, block: B:264:0x07b8  */
    /* JADX WARN: Code duplicated, block: B:268:0x07c3  */
    /* JADX WARN: Code duplicated, block: B:270:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:74:0x00de  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:86:0x0100  */
    /* JADX WARN: Code duplicated, block: B:89:0x0105  */
    /* JADX WARN: Code duplicated, block: B:90:0x010e  */
    /* JADX WARN: Code duplicated, block: B:92:0x0111  */
    /* JADX WARN: Code duplicated, block: B:95:0x0117  */
    /* JADX WARN: Code duplicated, block: B:98:0x012b  */
    /* JADX INFO: renamed from: ModalNavigationDrawer-FHprtrg, reason: not valid java name */
    public static final void m2659ModalNavigationDrawerFHprtrg(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, DrawerState drawerState, boolean gesturesEnabled, long scrimColor, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        DrawerState drawerState2;
        final boolean gesturesEnabled2;
        long scrimColor2;
        int i2;
        Modifier.Companion modifier3;
        final DrawerState drawerState3;
        Object it$iv$iv;
        Object value$iv$iv;
        final CoroutineScope scope;
        Object value$iv;
        boolean invalid$iv;
        Object value$iv2;
        final MutableFloatState minValue$delegate;
        boolean invalid$iv2;
        Object value$iv3;
        Object objConsume;
        boolean isRtl;
        Modifier modifier4;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        boolean z;
        boolean invalid$iv3;
        Object value$iv4;
        boolean invalid$iv4;
        final float maxValue;
        Object value$iv5;
        boolean invalid$iv5;
        Object value$iv6;
        boolean invalid$iv6;
        Object value$iv7;
        boolean invalid$iv7;
        MeasurePolicy value$iv8;
        int compositeKeyHash$iv;
        Function0<ComposeUiNode> constructor3;
        Composer $this$Layout_u24lambda_u240$iv;
        DrawerState drawerState4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer2 = $composer.startRestartGroup(-1169303680);
        ComposerKt.sourceInformation($composer2, "C(ModalNavigationDrawer)P(1,4,2,3,5:c#ui.graphics.Color)316@12708L39,318@12825L10,321@12893L24,322@12943L33,323@13008L7,324@13046L34,325@13101L45,328@13185L33,328@13174L44,330@13257L7,331@13292L3409:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerState2 = drawerState;
                int i4 = $composer2.changed(drawerState2) ? 256 : 128;
                $dirty |= i4;
            } else {
                drawerState2 = drawerState;
            }
            $dirty |= i4;
        } else {
            drawerState2 = drawerState;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            gesturesEnabled2 = gesturesEnabled;
        } else if (($changed & 3072) == 0) {
            gesturesEnabled2 = gesturesEnabled;
            $dirty |= $composer2.changed(gesturesEnabled2) ? 2048 : 1024;
        } else {
            gesturesEnabled2 = gesturesEnabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                scrimColor2 = scrimColor;
                int i6 = $composer2.changed(scrimColor2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                scrimColor2 = scrimColor;
            }
            $dirty |= i6;
        } else {
            scrimColor2 = scrimColor;
        }
        if ((i & 32) == 0) {
            if ((196608 & $changed) == 0) {
                i2 = $composer2.changedInstance(function3) ? 131072 : 65536;
            }
            if ((74899 & $dirty) == 74898 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if ((i & 4) != 0) {
                        drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                        $dirty &= -897;
                    } else {
                        drawerState3 = drawerState2;
                    }
                    if (i5 != 0) {
                        gesturesEnabled2 = true;
                    }
                    if ((i & 16) != 0) {
                        $dirty &= -57345;
                        scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty &= -897;
                    }
                    if ((i & 16) != 0) {
                        $dirty &= -57345;
                    }
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1169303680, $dirty, -1, "androidx.compose.material3.ModalNavigationDrawer (NavigationDrawer.kt:320)");
                }
                ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer2, -954363344, "CC(remember):Effects.kt#9igjgp");
                it$iv$iv = $composer2.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                    $composer2.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                scope = wrapper$iv.getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Strings.Companion companion = Strings.INSTANCE;
                final String navigationMenu = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer2, 0);
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Density density = (Density) objConsume2;
                ComposerKt.sourceInformationMarkerStart($composer2, -2145781231, "CC(remember):NavigationDrawer.kt#9igjgp");
                value$iv = $composer2.rememberedValue();
                if (value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer2.updateRememberedValue(value$iv);
                }
                final MutableState anchorsInitialized$delegate = (MutableState) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -2145779460, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv = $composer2.changed(density);
                Object it$iv = $composer2.rememberedValue();
                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                minValue$delegate = (MutableFloatState) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -2145776784, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv2 = (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changed(density);
                value$iv3 = $composer2.rememberedValue();
                if (!invalid$iv2 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$1$1
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
                            drawerState3.setDensity$material3_release(density);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) value$iv3, $composer2, 0);
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                objConsume = $composer2.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (objConsume == LayoutDirection.Rtl) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                Modifier modifier$iv = AnchoredDraggableKt.anchoredDraggable(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), drawerState3.getAnchoredDraggableState$material3_release(), Orientation.Horizontal, (16 & 4) != 0 ? true : gesturesEnabled2, (16 & 8) != 0 ? false : isRtl, (16 & 16) != 0 ? null : null);
                modifier4 = modifier3;
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                long scrimColor3 = scrimColor2;
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i8 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1884906081, "C341@13601L17,344@13695L268,352@13988L70,342@13627L473,358@14209L601,370@14842L623,385@15477L1218,355@14109L2586:NavigationDrawer.kt#uh7d8r");
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Modifier modifier$iv2 = Modifier.INSTANCE;
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv2 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor2;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor2;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i10 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 2020094509, "C341@13607L9:NavigationDrawer.kt#uh7d8r");
                function3.invoke($composer2, Integer.valueOf(($dirty >> 15) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean zIsOpen = drawerState3.isOpen();
                ComposerKt.sourceInformationMarkerStart($composer2, -2139013222, "CC(remember):NavigationDrawer.kt#9igjgp");
                if (($dirty & 7168) == 2048) {
                    z = true;
                } else {
                    z = false;
                }
                invalid$iv3 = z | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
                Object it$iv2 = $composer2.rememberedValue();
                if (!invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1
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
                            if (gesturesEnabled2 && drawerState3.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(drawerState3, null), 3, null);
                            }
                        }

                        /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1, reason: invalid class name */
                        /* JADX INFO: compiled from: NavigationDrawer.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                        @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1", f = "NavigationDrawer.kt", i = {}, l = {350}, m = "invokeSuspend", n = {}, s = {})
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ DrawerState $drawerState;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(DrawerState drawerState, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$drawerState = drawerState;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$drawerState, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        this.label = 1;
                                        if (this.$drawerState.close(this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        break;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv2;
                }
                Function0 function1 = (Function0) value$iv4;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -2139004044, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256);
                Object it$iv3 = $composer2.rememberedValue();
                if (!invalid$iv4 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    maxValue = 0.0f;
                    value$iv5 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(minValue$delegate), maxValue, drawerState3.requireOffset$material3_release()));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv5);
                } else {
                    value$iv5 = it$iv3;
                    maxValue = 0.0f;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                m2661ScrimBx497Mc(zIsOpen, function1, (Function0) value$iv5, scrimColor3, $composer2, ($dirty >> 3) & 7168);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -2138996441, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv5 = ((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256;
                Object it$iv4 = $composer2.rememberedValue();
                if (!invalid$iv5 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv6 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$4$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ IntOffset invoke(Density density2) {
                            return IntOffset.m7135boximpl(m2663invokeBjo55l4(density2));
                        }

                        /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                        public final long m2663invokeBjo55l4(Density $this$offset) {
                            int offsetX;
                            float offset = drawerState3.getCurrentOffset();
                            DrawerState drawerState5 = drawerState3;
                            if (Float.isNaN(offset)) {
                                offsetX = drawerState5.isOpen() ? 0 : -$this$offset.mo720roundToPx0680j_4(DrawerDefaults.INSTANCE.m2449getMaximumDrawerWidthD9Ej5fM());
                            } else {
                                offsetX = MathKt.roundToInt(offset);
                            }
                            return IntOffsetKt.IntOffset(offsetX, 0);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv6);
                } else {
                    value$iv6 = it$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierOffset = OffsetKt.offset(companion2, (Function1) value$iv6);
                ComposerKt.sourceInformationMarkerStart($composer2, -2138976163, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv6 = $composer2.changed(navigationMenu) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
                Object it$iv5 = $composer2.rememberedValue();
                if (!invalid$iv6 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                    value$iv7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.setPaneTitle($this$semantics, navigationMenu);
                            if (drawerState3.isOpen()) {
                                final DrawerState drawerState5 = drawerState3;
                                final CoroutineScope coroutineScope = scope;
                                SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        if (drawerState5.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01141(drawerState5, null), 3, null);
                                        }
                                        return true;
                                    }

                                    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1$1, reason: invalid class name and collision with other inner class name */
                                    /* JADX INFO: compiled from: NavigationDrawer.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                    @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1$1", f = "NavigationDrawer.kt", i = {}, l = {380}, m = "invokeSuspend", n = {}, s = {})
                                    static final class C01141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ DrawerState $drawerState;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        C01141(DrawerState drawerState, Continuation<? super C01141> continuation) {
                                            super(2, continuation);
                                            this.$drawerState = drawerState;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new C01141(this.$drawerState, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((C01141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object $result) {
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure($result);
                                                    this.label = 1;
                                                    if (this.$drawerState.close(this) == coroutine_suspended) {
                                                        return coroutine_suspended;
                                                    }
                                                    break;
                                                case 1:
                                                    ResultKt.throwOnFailure($result);
                                                    break;
                                                default:
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            return Unit.INSTANCE;
                                        }
                                    }
                                }, 1, null);
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv7);
                } else {
                    value$iv7 = it$iv5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean z2 = true;
                Modifier modifier$iv3 = SemanticsModifierKt.semantics$default(modifierOffset, false, (Function1) value$iv7, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, -2138955248, "CC(remember):NavigationDrawer.kt#9igjgp");
                if (((($dirty & 896) ^ 384) > 256 || !$composer2.changed(drawerState3)) && ($dirty & 384) != 256) {
                }
                invalid$iv7 = $composer2.changed(minValue$delegate) | z2;
                Object it$iv6 = $composer2.rememberedValue();
                if (!invalid$iv7 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                    value$iv8 = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$6$1
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public final MeasureResult mo391measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
                            Integer numValueOf;
                            long looseConstraints = Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0);
                            List target$iv = new ArrayList(list.size());
                            int size = list.size();
                            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                                Object item$iv$iv = list.get(index$iv$iv);
                                Measurable it = (Measurable) item$iv$iv;
                                target$iv.add(it.mo5870measureBRTryo0(looseConstraints));
                            }
                            final List placeables = target$iv;
                            Comparable maxValue$iv = null;
                            if (!placeables.isEmpty()) {
                                Placeable it2 = (Placeable) placeables.get(0);
                                numValueOf = Integer.valueOf(it2.getWidth());
                                int i$iv = 1;
                                int lastIndex = CollectionsKt.getLastIndex(placeables);
                                if (1 <= lastIndex) {
                                    while (true) {
                                        Placeable it3 = (Placeable) placeables.get(i$iv);
                                        Integer numValueOf2 = Integer.valueOf(it3.getWidth());
                                        if (numValueOf2.compareTo(numValueOf) > 0) {
                                            numValueOf = numValueOf2;
                                        }
                                        if (i$iv == lastIndex) {
                                            break;
                                        }
                                        i$iv++;
                                    }
                                }
                            } else {
                                numValueOf = null;
                            }
                            Integer num = numValueOf;
                            final int width = num != null ? num.intValue() : 0;
                            if (!placeables.isEmpty()) {
                                Placeable it4 = (Placeable) placeables.get(0);
                                Integer numValueOf3 = Integer.valueOf(it4.getHeight());
                                int i$iv2 = 1;
                                int lastIndex2 = CollectionsKt.getLastIndex(placeables);
                                if (1 <= lastIndex2) {
                                    while (true) {
                                        Placeable it5 = (Placeable) placeables.get(i$iv2);
                                        Integer numValueOf4 = Integer.valueOf(it5.getHeight());
                                        if (numValueOf4.compareTo(numValueOf3) > 0) {
                                            numValueOf3 = numValueOf4;
                                        }
                                        if (i$iv2 == lastIndex2) {
                                            break;
                                        }
                                        i$iv2++;
                                    }
                                }
                                maxValue$iv = numValueOf3;
                            }
                            Integer num2 = (Integer) maxValue$iv;
                            int height = num2 != null ? num2.intValue() : 0;
                            final DrawerState drawerState5 = drawerState3;
                            final MutableState<Boolean> mutableState = anchorsInitialized$delegate;
                            final MutableFloatState mutableFloatState = minValue$delegate;
                            final float f = maxValue;
                            return MeasureScope.layout$default($this$Layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$6$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                    invoke2(placementScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX WARN: Code duplicated, block: B:11:0x0031  */
                                /* JADX WARN: Code duplicated, block: B:9:0x0029  */
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Placeable.PlacementScope $this$layout) {
                                    float currentClosedAnchor = drawerState5.getAnchoredDraggableState$material3_release().getAnchors().positionOf(DrawerValue.Closed);
                                    float calculatedClosedAnchor = -width;
                                    if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$2(mutableState)) {
                                        if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$2(mutableState)) {
                                            NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$3(mutableState, true);
                                        }
                                        mutableFloatState.setFloatValue(calculatedClosedAnchor);
                                        AnchoredDraggableState<DrawerValue> anchoredDraggableState$material3_release = drawerState5.getAnchoredDraggableState$material3_release();
                                        final float f2 = f;
                                        final MutableFloatState mutableFloatState2 = mutableFloatState;
                                        AnchoredDraggableState.updateAnchors$default(anchoredDraggableState$material3_release, AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<DrawerValue>, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.ModalNavigationDrawer.2.6.1.1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                                invoke2(draggableAnchorsConfig);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                                draggableAnchorsConfig.at(DrawerValue.Closed, NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(mutableFloatState2));
                                                draggableAnchorsConfig.at(DrawerValue.Open, f2);
                                            }
                                        }), null, 2, null);
                                    } else if (!(currentClosedAnchor == calculatedClosedAnchor)) {
                                        if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$2(mutableState)) {
                                            NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$3(mutableState, true);
                                        }
                                        mutableFloatState.setFloatValue(calculatedClosedAnchor);
                                        AnchoredDraggableState<DrawerValue> anchoredDraggableState$material3_release2 = drawerState5.getAnchoredDraggableState$material3_release();
                                        final float f3 = f;
                                        final MutableFloatState mutableFloatState3 = mutableFloatState;
                                        AnchoredDraggableState.updateAnchors$default(anchoredDraggableState$material3_release2, AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<DrawerValue>, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.ModalNavigationDrawer.2.6.1.1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                                invoke2(draggableAnchorsConfig);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                                draggableAnchorsConfig.at(DrawerValue.Closed, NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(mutableFloatState3));
                                                draggableAnchorsConfig.at(DrawerValue.Open, f3);
                                            }
                                        }), null, 2, null);
                                    }
                                    List<Placeable> list2 = placeables;
                                    int size2 = list2.size();
                                    for (int index$iv = 0; index$iv < size2; index$iv++) {
                                        Object item$iv = list2.get(index$iv);
                                        Placeable it6 = (Placeable) item$iv;
                                        Placeable.PlacementScope.placeRelative$default($this$layout, it6, 0, 0, 0.0f, 4, null);
                                    }
                                }
                            }, 4, null);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv8);
                } else {
                    value$iv8 = it$iv6;
                }
                MeasurePolicy measurePolicy$iv3 = (MeasurePolicy) value$iv8;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                int $changed$iv = $dirty & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv3 = (($changed$iv << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor3);
                } else {
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                    drawerState4 = drawerState3;
                } else {
                    drawerState4 = drawerState3;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    function2.invoke($composer2, Integer.valueOf(($changed$iv$iv3 >> 6) & 14));
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    scrimColor2 = scrimColor3;
                }
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash3);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                function2.invoke($composer2, Integer.valueOf(($changed$iv$iv3 >> 6) & 14));
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scrimColor2 = scrimColor3;
            } else {
                $composer2.skipToGroupEnd();
                modifier4 = modifier2;
                drawerState4 = drawerState2;
                gesturesEnabled2 = gesturesEnabled2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier5 = modifier4;
                final DrawerState drawerState5 = drawerState4;
                final boolean z3 = gesturesEnabled2;
                final long j = scrimColor2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$3
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
                        NavigationDrawerKt.m2659ModalNavigationDrawerFHprtrg(function2, modifier5, drawerState5, z3, j, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                    }
                });
            }
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        if ((74899 & $dirty) == 74898) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1169303680, $dirty, -1, "androidx.compose.material3.ModalNavigationDrawer (NavigationDrawer.kt:320)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, -954363344, "CC(remember):Effects.kt#9igjgp");
            it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv2 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            scope = wrapper$iv2.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Strings.Companion companion3 = Strings.INSTANCE;
            final String navigationMenu2 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer2, 0);
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density2 = (Density) objConsume3;
            ComposerKt.sourceInformationMarkerStart($composer2, -2145781231, "CC(remember):NavigationDrawer.kt#9igjgp");
            value$iv = $composer2.rememberedValue();
            if (value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            }
            final MutableState<Boolean> anchorsInitialized$delegate2 = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2145779460, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv = $composer2.changed(density2);
            Object it$iv7 = $composer2.rememberedValue();
            if (invalid$iv) {
            }
            value$iv2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
            $composer2.updateRememberedValue(value$iv2);
            minValue$delegate = (MutableFloatState) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2145776784, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv2 = (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changed(density2);
            value$iv3 = $composer2.rememberedValue();
            if (!invalid$iv2) {
            }
            value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$1$1
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
                    drawerState3.setDensity$material3_release(density2);
                }
            };
            $composer2.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) value$iv3, $composer2, 0);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            objConsume = $composer2.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (objConsume == LayoutDirection.Rtl) {
                isRtl = true;
            } else {
                isRtl = false;
            }
            Modifier modifier$iv4 = AnchoredDraggableKt.anchoredDraggable(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), drawerState3.getAnchoredDraggableState$material3_release(), Orientation.Horizontal, (16 & 4) != 0 ? true : gesturesEnabled2, (16 & 8) != 0 ? false : isRtl, (16 & 16) != 0 ? null : null);
            modifier4 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
            int $changed$iv$iv4 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
            long scrimColor4 = scrimColor2;
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
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
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i12 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1884906081, "C341@13601L17,344@13695L268,352@13988L70,342@13627L473,358@14209L601,370@14842L623,385@15477L1218,355@14109L2586:NavigationDrawer.kt#uh7d8r");
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier modifier$iv5 = Modifier.INSTANCE;
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
            int $changed$iv$iv5 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer2, modifier$iv5);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv5 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor2;
                $composer2.createNode(function0);
            } else {
                function0 = constructor2;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash5);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
            int i13 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i14 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 2020094509, "C341@13607L9:NavigationDrawer.kt#uh7d8r");
            function3.invoke($composer2, Integer.valueOf(($dirty >> 15) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean zIsOpen2 = drawerState3.isOpen();
            ComposerKt.sourceInformationMarkerStart($composer2, -2139013222, "CC(remember):NavigationDrawer.kt#9igjgp");
            if (($dirty & 7168) == 2048) {
                z = true;
            } else {
                z = false;
            }
            invalid$iv3 = z | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
            Object it$iv8 = $composer2.rememberedValue();
            if (invalid$iv3) {
                value$iv4 = new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1
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
                        if (gesturesEnabled2 && drawerState3.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(drawerState3, null), 3, null);
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: NavigationDrawer.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1", f = "NavigationDrawer.kt", i = {}, l = {350}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ DrawerState $drawerState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(DrawerState drawerState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$drawerState = drawerState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$drawerState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$drawerState.close(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1
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
                        if (gesturesEnabled2 && drawerState3.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(drawerState3, null), 3, null);
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: NavigationDrawer.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1", f = "NavigationDrawer.kt", i = {}, l = {350}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ DrawerState $drawerState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(DrawerState drawerState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$drawerState = drawerState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$drawerState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$drawerState.close(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
            }
            Function0 function4 = (Function0) value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2139004044, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256);
            Object it$iv9 = $composer2.rememberedValue();
            if (invalid$iv4) {
                maxValue = 0.0f;
                value$iv5 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(minValue$delegate), maxValue, drawerState3.requireOffset$material3_release()));
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
            } else {
                maxValue = 0.0f;
                value$iv5 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(minValue$delegate), maxValue, drawerState3.requireOffset$material3_release()));
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m2661ScrimBx497Mc(zIsOpen2, function4, (Function0) value$iv5, scrimColor4, $composer2, ($dirty >> 3) & 7168);
            Modifier.Companion companion4 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -2138996441, "CC(remember):NavigationDrawer.kt#9igjgp");
            if ((($dirty & 896) ^ 384) <= 256) {
            }
            Object it$iv10 = $composer2.rememberedValue();
            if (invalid$iv5) {
            }
            value$iv6 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$4$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntOffset invoke(Density density3) {
                    return IntOffset.m7135boximpl(m2663invokeBjo55l4(density3));
                }

                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                public final long m2663invokeBjo55l4(Density $this$offset) {
                    int offsetX;
                    float offset = drawerState3.getCurrentOffset();
                    DrawerState drawerState6 = drawerState3;
                    if (Float.isNaN(offset)) {
                        offsetX = drawerState6.isOpen() ? 0 : -$this$offset.mo720roundToPx0680j_4(DrawerDefaults.INSTANCE.m2449getMaximumDrawerWidthD9Ej5fM());
                    } else {
                        offsetX = MathKt.roundToInt(offset);
                    }
                    return IntOffsetKt.IntOffset(offsetX, 0);
                }
            };
            $composer2.updateRememberedValue(value$iv6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierOffset2 = OffsetKt.offset(companion4, (Function1) value$iv6);
            ComposerKt.sourceInformationMarkerStart($composer2, -2138976163, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv6 = $composer2.changed(navigationMenu2) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
            Object it$iv11 = $composer2.rememberedValue();
            if (invalid$iv6) {
            }
            value$iv7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setPaneTitle($this$semantics, navigationMenu2);
                    if (drawerState3.isOpen()) {
                        final DrawerState drawerState6 = drawerState3;
                        final CoroutineScope coroutineScope = scope;
                        SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                if (drawerState6.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01141(drawerState6, null), 3, null);
                                }
                                return true;
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1$1, reason: invalid class name and collision with other inner class name */
                            /* JADX INFO: compiled from: NavigationDrawer.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1$1", f = "NavigationDrawer.kt", i = {}, l = {380}, m = "invokeSuspend", n = {}, s = {})
                            static final class C01141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ DrawerState $drawerState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C01141(DrawerState drawerState, Continuation<? super C01141> continuation) {
                                    super(2, continuation);
                                    this.$drawerState = drawerState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C01141(this.$drawerState, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C01141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            this.label = 1;
                                            if (this.$drawerState.close(this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            break;
                                        case 1:
                                            ResultKt.throwOnFailure($result);
                                            break;
                                        default:
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    return Unit.INSTANCE;
                                }
                            }
                        }, 1, null);
                    }
                }
            };
            $composer2.updateRememberedValue(value$iv7);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean z4 = true;
            Modifier modifier$iv6 = SemanticsModifierKt.semantics$default(modifierOffset2, false, (Function1) value$iv7, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -2138955248, "CC(remember):NavigationDrawer.kt#9igjgp");
            z4 = (($dirty & 896) ^ 384) > 256 ? false : false;
            invalid$iv7 = $composer2.changed(minValue$delegate) | z4;
            Object it$iv12 = $composer2.rememberedValue();
            if (invalid$iv7) {
            }
            value$iv8 = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$6$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo391measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
                    Integer numValueOf;
                    long looseConstraints = Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0);
                    List target$iv = new ArrayList(list.size());
                    int size = list.size();
                    for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                        Object item$iv$iv = list.get(index$iv$iv);
                        Measurable it = (Measurable) item$iv$iv;
                        target$iv.add(it.mo5870measureBRTryo0(looseConstraints));
                    }
                    final List<? extends Placeable> placeables = target$iv;
                    Comparable maxValue$iv = null;
                    if (!placeables.isEmpty()) {
                        Placeable it2 = (Placeable) placeables.get(0);
                        numValueOf = Integer.valueOf(it2.getWidth());
                        int i$iv = 1;
                        int lastIndex = CollectionsKt.getLastIndex(placeables);
                        if (1 <= lastIndex) {
                            while (true) {
                                Placeable it3 = (Placeable) placeables.get(i$iv);
                                Integer numValueOf2 = Integer.valueOf(it3.getWidth());
                                if (numValueOf2.compareTo(numValueOf) > 0) {
                                    numValueOf = numValueOf2;
                                }
                                if (i$iv == lastIndex) {
                                    break;
                                }
                                i$iv++;
                            }
                        }
                    } else {
                        numValueOf = null;
                    }
                    Integer num = numValueOf;
                    final int width = num != null ? num.intValue() : 0;
                    if (!placeables.isEmpty()) {
                        Placeable it4 = (Placeable) placeables.get(0);
                        Integer numValueOf3 = Integer.valueOf(it4.getHeight());
                        int i$iv2 = 1;
                        int lastIndex2 = CollectionsKt.getLastIndex(placeables);
                        if (1 <= lastIndex2) {
                            while (true) {
                                Placeable it5 = (Placeable) placeables.get(i$iv2);
                                Integer numValueOf4 = Integer.valueOf(it5.getHeight());
                                if (numValueOf4.compareTo(numValueOf3) > 0) {
                                    numValueOf3 = numValueOf4;
                                }
                                if (i$iv2 == lastIndex2) {
                                    break;
                                }
                                i$iv2++;
                            }
                        }
                        maxValue$iv = numValueOf3;
                    }
                    Integer num2 = (Integer) maxValue$iv;
                    int height = num2 != null ? num2.intValue() : 0;
                    final DrawerState drawerState6 = drawerState3;
                    final MutableState<Boolean> mutableState = anchorsInitialized$delegate2;
                    final MutableFloatState mutableFloatState = minValue$delegate;
                    final float f = maxValue;
                    return MeasureScope.layout$default($this$Layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$6$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Code duplicated, block: B:11:0x0031  */
                        /* JADX WARN: Code duplicated, block: B:9:0x0029  */
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope $this$layout) {
                            float currentClosedAnchor = drawerState6.getAnchoredDraggableState$material3_release().getAnchors().positionOf(DrawerValue.Closed);
                            float calculatedClosedAnchor = -width;
                            if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$2(mutableState)) {
                                if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$2(mutableState)) {
                                    NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$3(mutableState, true);
                                }
                                mutableFloatState.setFloatValue(calculatedClosedAnchor);
                                AnchoredDraggableState<DrawerValue> anchoredDraggableState$material3_release2 = drawerState6.getAnchoredDraggableState$material3_release();
                                final float f3 = f;
                                final MutableFloatState mutableFloatState3 = mutableFloatState;
                                AnchoredDraggableState.updateAnchors$default(anchoredDraggableState$material3_release2, AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<DrawerValue>, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.ModalNavigationDrawer.2.6.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                        invoke2(draggableAnchorsConfig);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                        draggableAnchorsConfig.at(DrawerValue.Closed, NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(mutableFloatState3));
                                        draggableAnchorsConfig.at(DrawerValue.Open, f3);
                                    }
                                }), null, 2, null);
                            } else if (!(currentClosedAnchor == calculatedClosedAnchor)) {
                                if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$2(mutableState)) {
                                    NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$3(mutableState, true);
                                }
                                mutableFloatState.setFloatValue(calculatedClosedAnchor);
                                AnchoredDraggableState<DrawerValue> anchoredDraggableState$material3_release3 = drawerState6.getAnchoredDraggableState$material3_release();
                                final float f4 = f;
                                final MutableFloatState mutableFloatState4 = mutableFloatState;
                                AnchoredDraggableState.updateAnchors$default(anchoredDraggableState$material3_release3, AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<DrawerValue>, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.ModalNavigationDrawer.2.6.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                        invoke2(draggableAnchorsConfig);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                        draggableAnchorsConfig.at(DrawerValue.Closed, NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(mutableFloatState4));
                                        draggableAnchorsConfig.at(DrawerValue.Open, f4);
                                    }
                                }), null, 2, null);
                            }
                            List<Placeable> list2 = placeables;
                            int size2 = list2.size();
                            for (int index$iv = 0; index$iv < size2; index$iv++) {
                                Object item$iv = list2.get(index$iv);
                                Placeable it6 = (Placeable) item$iv;
                                Placeable.PlacementScope.placeRelative$default($this$layout, it6, 0, 0, 0.0f, 4, null);
                            }
                        }
                    }, 4, null);
                }
            };
            $composer2.updateRememberedValue(value$iv8);
            MeasurePolicy measurePolicy$iv6 = (MeasurePolicy) value$iv8;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv2 = $dirty & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv6);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv6 = (($changed$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor3);
            } else {
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                drawerState4 = drawerState3;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                function2.invoke($composer2, Integer.valueOf(($changed$iv$iv6 >> 6) & 14));
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scrimColor2 = scrimColor4;
            } else {
                drawerState4 = drawerState3;
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash6);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            function2.invoke($composer2, Integer.valueOf(($changed$iv$iv6 >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            scrimColor2 = scrimColor4;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1169303680, $dirty, -1, "androidx.compose.material3.ModalNavigationDrawer (NavigationDrawer.kt:320)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, -954363344, "CC(remember):Effects.kt#9igjgp");
            it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv3 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            scope = wrapper$iv3.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Strings.Companion companion5 = Strings.INSTANCE;
            final String navigationMenu3 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer2, 0);
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer2.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density3 = (Density) objConsume4;
            ComposerKt.sourceInformationMarkerStart($composer2, -2145781231, "CC(remember):NavigationDrawer.kt#9igjgp");
            value$iv = $composer2.rememberedValue();
            if (value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            }
            final MutableState<Boolean> anchorsInitialized$delegate3 = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2145779460, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv = $composer2.changed(density3);
            Object it$iv13 = $composer2.rememberedValue();
            if (invalid$iv) {
            }
            value$iv2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
            $composer2.updateRememberedValue(value$iv2);
            minValue$delegate = (MutableFloatState) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2145776784, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv2 = (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changed(density3);
            value$iv3 = $composer2.rememberedValue();
            if (!invalid$iv2) {
            }
            value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$1$1
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
                    drawerState3.setDensity$material3_release(density3);
                }
            };
            $composer2.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) value$iv3, $composer2, 0);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            objConsume = $composer2.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (objConsume == LayoutDirection.Rtl) {
                isRtl = true;
            } else {
                isRtl = false;
            }
            Modifier modifier$iv7 = AnchoredDraggableKt.anchoredDraggable(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), drawerState3.getAnchoredDraggableState$material3_release(), Orientation.Horizontal, (16 & 4) != 0 ? true : gesturesEnabled2, (16 & 8) != 0 ? false : isRtl, (16 & 16) != 0 ? null : null);
            modifier4 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv7 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
            int $changed$iv$iv7 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer2.getCurrentCompositionLocalMap();
            long scrimColor5 = scrimColor2;
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer2, modifier$iv7);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv5 = (($changed$iv$iv7 << 6) & 896) | 6;
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
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash7);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
            int i15 = ($changed$iv$iv$iv5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            int i16 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1884906081, "C341@13601L17,344@13695L268,352@13988L70,342@13627L473,358@14209L601,370@14842L623,385@15477L1218,355@14109L2586:NavigationDrawer.kt#uh7d8r");
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier modifier$iv8 = Modifier.INSTANCE;
            Alignment contentAlignment$iv6 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv8 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
            int $changed$iv$iv8 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv6 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer2, modifier$iv8);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv6 = (($changed$iv$iv8 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor2;
                $composer2.createNode(function0);
            } else {
                function0 = constructor2;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash8);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
            int i17 = ($changed$iv$iv$iv6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
            int i18 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 2020094509, "C341@13607L9:NavigationDrawer.kt#uh7d8r");
            function3.invoke($composer2, Integer.valueOf(($dirty >> 15) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean zIsOpen3 = drawerState3.isOpen();
            ComposerKt.sourceInformationMarkerStart($composer2, -2139013222, "CC(remember):NavigationDrawer.kt#9igjgp");
            if (($dirty & 7168) == 2048) {
                z = true;
            } else {
                z = false;
            }
            invalid$iv3 = z | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
            Object it$iv14 = $composer2.rememberedValue();
            if (invalid$iv3) {
                value$iv4 = new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1
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
                        if (gesturesEnabled2 && drawerState3.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(drawerState3, null), 3, null);
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: NavigationDrawer.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1", f = "NavigationDrawer.kt", i = {}, l = {350}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ DrawerState $drawerState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(DrawerState drawerState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$drawerState = drawerState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$drawerState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$drawerState.close(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1
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
                        if (gesturesEnabled2 && drawerState3.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(drawerState3, null), 3, null);
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: NavigationDrawer.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1", f = "NavigationDrawer.kt", i = {}, l = {350}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ DrawerState $drawerState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(DrawerState drawerState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$drawerState = drawerState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$drawerState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$drawerState.close(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
            }
            Function0 function5 = (Function0) value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2139004044, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256);
            Object it$iv15 = $composer2.rememberedValue();
            if (invalid$iv4) {
                maxValue = 0.0f;
                value$iv5 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(minValue$delegate), maxValue, drawerState3.requireOffset$material3_release()));
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
            } else {
                maxValue = 0.0f;
                value$iv5 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(minValue$delegate), maxValue, drawerState3.requireOffset$material3_release()));
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m2661ScrimBx497Mc(zIsOpen3, function5, (Function0) value$iv5, scrimColor5, $composer2, ($dirty >> 3) & 7168);
            Modifier.Companion companion6 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -2138996441, "CC(remember):NavigationDrawer.kt#9igjgp");
            if ((($dirty & 896) ^ 384) <= 256) {
            }
            Object it$iv16 = $composer2.rememberedValue();
            if (invalid$iv5) {
            }
            value$iv6 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$4$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntOffset invoke(Density density4) {
                    return IntOffset.m7135boximpl(m2663invokeBjo55l4(density4));
                }

                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                public final long m2663invokeBjo55l4(Density $this$offset) {
                    int offsetX;
                    float offset = drawerState3.getCurrentOffset();
                    DrawerState drawerState6 = drawerState3;
                    if (Float.isNaN(offset)) {
                        offsetX = drawerState6.isOpen() ? 0 : -$this$offset.mo720roundToPx0680j_4(DrawerDefaults.INSTANCE.m2449getMaximumDrawerWidthD9Ej5fM());
                    } else {
                        offsetX = MathKt.roundToInt(offset);
                    }
                    return IntOffsetKt.IntOffset(offsetX, 0);
                }
            };
            $composer2.updateRememberedValue(value$iv6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierOffset3 = OffsetKt.offset(companion6, (Function1) value$iv6);
            ComposerKt.sourceInformationMarkerStart($composer2, -2138976163, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv6 = $composer2.changed(navigationMenu3) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
            Object it$iv17 = $composer2.rememberedValue();
            if (invalid$iv6) {
            }
            value$iv7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setPaneTitle($this$semantics, navigationMenu3);
                    if (drawerState3.isOpen()) {
                        final DrawerState drawerState6 = drawerState3;
                        final CoroutineScope coroutineScope = scope;
                        SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                if (drawerState6.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01141(drawerState6, null), 3, null);
                                }
                                return true;
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1$1, reason: invalid class name and collision with other inner class name */
                            /* JADX INFO: compiled from: NavigationDrawer.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1$1", f = "NavigationDrawer.kt", i = {}, l = {380}, m = "invokeSuspend", n = {}, s = {})
                            static final class C01141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ DrawerState $drawerState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C01141(DrawerState drawerState, Continuation<? super C01141> continuation) {
                                    super(2, continuation);
                                    this.$drawerState = drawerState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C01141(this.$drawerState, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C01141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            this.label = 1;
                                            if (this.$drawerState.close(this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            break;
                                        case 1:
                                            ResultKt.throwOnFailure($result);
                                            break;
                                        default:
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    return Unit.INSTANCE;
                                }
                            }
                        }, 1, null);
                    }
                }
            };
            $composer2.updateRememberedValue(value$iv7);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean z5 = true;
            Modifier modifier$iv9 = SemanticsModifierKt.semantics$default(modifierOffset3, false, (Function1) value$iv7, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -2138955248, "CC(remember):NavigationDrawer.kt#9igjgp");
            if ((($dirty & 896) ^ 384) > 256) {
            }
            invalid$iv7 = $composer2.changed(minValue$delegate) | z5;
            Object it$iv18 = $composer2.rememberedValue();
            if (invalid$iv7) {
            }
            value$iv8 = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$6$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo391measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
                    Integer numValueOf;
                    long looseConstraints = Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0);
                    List target$iv = new ArrayList(list.size());
                    int size = list.size();
                    for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                        Object item$iv$iv = list.get(index$iv$iv);
                        Measurable it = (Measurable) item$iv$iv;
                        target$iv.add(it.mo5870measureBRTryo0(looseConstraints));
                    }
                    final List<? extends Placeable> placeables = target$iv;
                    Comparable maxValue$iv = null;
                    if (!placeables.isEmpty()) {
                        Placeable it2 = (Placeable) placeables.get(0);
                        numValueOf = Integer.valueOf(it2.getWidth());
                        int i$iv = 1;
                        int lastIndex = CollectionsKt.getLastIndex(placeables);
                        if (1 <= lastIndex) {
                            while (true) {
                                Placeable it3 = (Placeable) placeables.get(i$iv);
                                Integer numValueOf2 = Integer.valueOf(it3.getWidth());
                                if (numValueOf2.compareTo(numValueOf) > 0) {
                                    numValueOf = numValueOf2;
                                }
                                if (i$iv == lastIndex) {
                                    break;
                                }
                                i$iv++;
                            }
                        }
                    } else {
                        numValueOf = null;
                    }
                    Integer num = numValueOf;
                    final int width = num != null ? num.intValue() : 0;
                    if (!placeables.isEmpty()) {
                        Placeable it4 = (Placeable) placeables.get(0);
                        Integer numValueOf3 = Integer.valueOf(it4.getHeight());
                        int i$iv2 = 1;
                        int lastIndex2 = CollectionsKt.getLastIndex(placeables);
                        if (1 <= lastIndex2) {
                            while (true) {
                                Placeable it5 = (Placeable) placeables.get(i$iv2);
                                Integer numValueOf4 = Integer.valueOf(it5.getHeight());
                                if (numValueOf4.compareTo(numValueOf3) > 0) {
                                    numValueOf3 = numValueOf4;
                                }
                                if (i$iv2 == lastIndex2) {
                                    break;
                                }
                                i$iv2++;
                            }
                        }
                        maxValue$iv = numValueOf3;
                    }
                    Integer num2 = (Integer) maxValue$iv;
                    int height = num2 != null ? num2.intValue() : 0;
                    final DrawerState drawerState6 = drawerState3;
                    final MutableState<Boolean> mutableState = anchorsInitialized$delegate3;
                    final MutableFloatState mutableFloatState = minValue$delegate;
                    final float f = maxValue;
                    return MeasureScope.layout$default($this$Layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$6$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Code duplicated, block: B:11:0x0031  */
                        /* JADX WARN: Code duplicated, block: B:9:0x0029  */
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope $this$layout) {
                            float currentClosedAnchor = drawerState6.getAnchoredDraggableState$material3_release().getAnchors().positionOf(DrawerValue.Closed);
                            float calculatedClosedAnchor = -width;
                            if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$2(mutableState)) {
                                if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$2(mutableState)) {
                                    NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$3(mutableState, true);
                                }
                                mutableFloatState.setFloatValue(calculatedClosedAnchor);
                                AnchoredDraggableState<DrawerValue> anchoredDraggableState$material3_release3 = drawerState6.getAnchoredDraggableState$material3_release();
                                final float f4 = f;
                                final MutableFloatState mutableFloatState4 = mutableFloatState;
                                AnchoredDraggableState.updateAnchors$default(anchoredDraggableState$material3_release3, AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<DrawerValue>, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.ModalNavigationDrawer.2.6.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                        invoke2(draggableAnchorsConfig);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                        draggableAnchorsConfig.at(DrawerValue.Closed, NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(mutableFloatState4));
                                        draggableAnchorsConfig.at(DrawerValue.Open, f4);
                                    }
                                }), null, 2, null);
                            } else if (!(currentClosedAnchor == calculatedClosedAnchor)) {
                                if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$2(mutableState)) {
                                    NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$3(mutableState, true);
                                }
                                mutableFloatState.setFloatValue(calculatedClosedAnchor);
                                AnchoredDraggableState<DrawerValue> anchoredDraggableState$material3_release4 = drawerState6.getAnchoredDraggableState$material3_release();
                                final float f5 = f;
                                final MutableFloatState mutableFloatState5 = mutableFloatState;
                                AnchoredDraggableState.updateAnchors$default(anchoredDraggableState$material3_release4, AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<DrawerValue>, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.ModalNavigationDrawer.2.6.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                        invoke2(draggableAnchorsConfig);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                        draggableAnchorsConfig.at(DrawerValue.Closed, NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(mutableFloatState5));
                                        draggableAnchorsConfig.at(DrawerValue.Open, f5);
                                    }
                                }), null, 2, null);
                            }
                            List<Placeable> list2 = placeables;
                            int size2 = list2.size();
                            for (int index$iv = 0; index$iv < size2; index$iv++) {
                                Object item$iv = list2.get(index$iv);
                                Placeable it6 = (Placeable) item$iv;
                                Placeable.PlacementScope.placeRelative$default($this$layout, it6, 0, 0, 0.0f, 4, null);
                            }
                        }
                    }, 4, null);
                }
            };
            $composer2.updateRememberedValue(value$iv8);
            MeasurePolicy measurePolicy$iv9 = (MeasurePolicy) value$iv8;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv3 = $dirty & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv3 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv9);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv9 = (($changed$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor3);
            } else {
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                drawerState4 = drawerState3;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                function2.invoke($composer2, Integer.valueOf(($changed$iv$iv9 >> 6) & 14));
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scrimColor2 = scrimColor5;
            } else {
                drawerState4 = drawerState3;
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash9);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv3, ComposeUiNode.INSTANCE.getSetModifier());
            function2.invoke($composer2, Integer.valueOf(($changed$iv$iv9 >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            scrimColor2 = scrimColor5;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier4;
            final DrawerState drawerState6 = drawerState4;
            final boolean z6 = gesturesEnabled2;
            final long j2 = scrimColor2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$3
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

                public final void invoke(Composer composer, int i19) {
                    NavigationDrawerKt.m2659ModalNavigationDrawerFHprtrg(function2, modifier6, drawerState6, z6, j2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ModalNavigationDrawer_FHprtrg$lambda$2(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ModalNavigationDrawer_FHprtrg$lambda$3(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float ModalNavigationDrawer_FHprtrg$lambda$5(MutableFloatState $minValue$delegate) {
        MutableFloatState $this$getValue$iv = $minValue$delegate;
        return $this$getValue$iv.getFloatValue();
    }

    public static final void DismissibleNavigationDrawer(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, DrawerState drawerState, boolean gesturesEnabled, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        DrawerState drawerState2;
        boolean gesturesEnabled2;
        Modifier.Companion modifier3;
        final DrawerState drawerState3;
        Object value$iv;
        Object value$iv2;
        Modifier modifier4;
        Function0<ComposeUiNode> function0;
        MeasurePolicy value$iv3;
        Function0<ComposeUiNode> function1;
        Object value$iv4;
        DrawerState drawerState4;
        Function0<ComposeUiNode> function4;
        Function0<ComposeUiNode> function5;
        Composer $composer2 = $composer.startRestartGroup(398812198);
        ComposerKt.sourceInformation($composer2, "C(DismissibleNavigationDrawer)P(1,4,2,3)440@17931L39,444@18079L34,445@18145L7,446@18168L33,446@18157L44,448@18219L24,449@18269L33,451@18341L7,452@18376L2358:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
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
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerState2 = drawerState;
                int i3 = $composer2.changed(drawerState2) ? 256 : 128;
                $dirty |= i3;
            } else {
                drawerState2 = drawerState;
            }
            $dirty |= i3;
        } else {
            drawerState2 = drawerState;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            gesturesEnabled2 = gesturesEnabled;
        } else if (($changed & 3072) == 0) {
            gesturesEnabled2 = gesturesEnabled;
            $dirty |= $composer2.changed(gesturesEnabled2) ? 2048 : 1024;
        } else {
            gesturesEnabled2 = gesturesEnabled;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            drawerState4 = drawerState2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i4 != 0) {
                    gesturesEnabled2 = true;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
                drawerState3 = drawerState2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(398812198, $dirty, -1, "androidx.compose.material3.DismissibleNavigationDrawer (NavigationDrawer.kt:443)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1034582752, "CC(remember):NavigationDrawer.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState anchorsInitialized$delegate = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density = (Density) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer2, 1034585599, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv = (((($dirty & 896) ^ 384) > 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changed(density);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$1$1
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
                        drawerState3.setDensity$material3_release(density);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) value$iv2, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, -954363344, "CC(remember):Effects.kt#9igjgp");
            Object value$iv$iv = $composer2.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv);
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Strings.Companion companion = Strings.INSTANCE;
            final String navigationMenu = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer2, 0);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean isRtl = objConsume2 == LayoutDirection.Rtl;
            Modifier modifier$iv = AnchoredDraggableKt.anchoredDraggable(modifier3, drawerState3.getAnchoredDraggableState$material3_release(), Orientation.Horizontal, (16 & 4) != 0 ? true : gesturesEnabled2, (16 & 8) != 0 ? false : isRtl, (16 & 16) != 0 ? null : null);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            modifier4 = modifier3;
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            boolean gesturesEnabled3 = gesturesEnabled2;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
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
            int i5 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1217734065, "C483@19473L1255,460@18625L2103:NavigationDrawer.kt#uh7d8r");
            ComposerKt.sourceInformationMarkerStart($composer2, 99291876, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv2 = ((($dirty & 896) ^ 384) > 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256;
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo391measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
                        final Placeable sheetPlaceable = list.get(0).mo5870measureBRTryo0(constraints);
                        final Placeable contentPlaceable = list.get(1).mo5870measureBRTryo0(constraints);
                        int width = contentPlaceable.getWidth();
                        int height = contentPlaceable.getHeight();
                        final DrawerState drawerState5 = drawerState3;
                        final MutableState<Boolean> mutableState = anchorsInitialized$delegate;
                        return MeasureScope.layout$default($this$Layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Code duplicated, block: B:11:0x0035  */
                            /* JADX WARN: Code duplicated, block: B:9:0x002d  */
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope $this$layout) {
                                float currentClosedAnchor = drawerState5.getAnchoredDraggableState$material3_release().getAnchors().positionOf(DrawerValue.Closed);
                                final float calculatedClosedAnchor = -sheetPlaceable.getWidth();
                                if (NavigationDrawerKt.DismissibleNavigationDrawer$lambda$16(mutableState)) {
                                    if (!(currentClosedAnchor == calculatedClosedAnchor)) {
                                        if (!NavigationDrawerKt.DismissibleNavigationDrawer$lambda$16(mutableState)) {
                                            NavigationDrawerKt.DismissibleNavigationDrawer$lambda$17(mutableState, true);
                                        }
                                        AnchoredDraggableState.updateAnchors$default(drawerState5.getAnchoredDraggableState$material3_release(), AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<DrawerValue>, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.DismissibleNavigationDrawer.2.2.1.1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                                invoke2(draggableAnchorsConfig);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                                draggableAnchorsConfig.at(DrawerValue.Closed, calculatedClosedAnchor);
                                                draggableAnchorsConfig.at(DrawerValue.Open, 0.0f);
                                            }
                                        }), null, 2, null);
                                    }
                                } else {
                                    if (!NavigationDrawerKt.DismissibleNavigationDrawer$lambda$16(mutableState)) {
                                        NavigationDrawerKt.DismissibleNavigationDrawer$lambda$17(mutableState, true);
                                    }
                                    AnchoredDraggableState.updateAnchors$default(drawerState5.getAnchoredDraggableState$material3_release(), AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<DrawerValue>, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.DismissibleNavigationDrawer.2.2.1.1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                            invoke2(draggableAnchorsConfig);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
                                            draggableAnchorsConfig.at(DrawerValue.Closed, calculatedClosedAnchor);
                                            draggableAnchorsConfig.at(DrawerValue.Open, 0.0f);
                                        }
                                    }), null, 2, null);
                                }
                                Placeable.PlacementScope.placeRelative$default($this$layout, contentPlaceable, sheetPlaceable.getWidth() + MathKt.roundToInt(drawerState5.requireOffset$material3_release()), 0, 0.0f, 4, null);
                                Placeable.PlacementScope.placeRelative$default($this$layout, sheetPlaceable, MathKt.roundToInt(drawerState5.requireOffset$material3_release()), 0, 0.0f, 4, null);
                            }
                        }, 4, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            MeasurePolicy measurePolicy$iv2 = (MeasurePolicy) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            Modifier modifier$iv2 = Modifier.INSTANCE;
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv2 = ((0 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function1 = constructor2;
                $composer2.createNode(function1);
            } else {
                function1 = constructor2;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash2);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = ($changed$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 285828320, "C463@18717L623,462@18673L741,481@19431L17:NavigationDrawer.kt#uh7d8r");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -1376251797, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changed(navigationMenu) | (((($dirty & 896) ^ 384) > 256 && $composer2.changed(drawerState3)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setPaneTitle($this$semantics, navigationMenu);
                        if (drawerState3.isOpen()) {
                            final DrawerState drawerState5 = drawerState3;
                            final CoroutineScope coroutineScope = scope;
                            SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$1$1$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    if (drawerState5.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01121(drawerState5, null), 3, null);
                                    }
                                    return true;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                /* JADX INFO: compiled from: NavigationDrawer.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$1$1$1$1$1", f = "NavigationDrawer.kt", i = {}, l = {473}, m = "invokeSuspend", n = {}, s = {})
                                static final class C01121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ DrawerState $drawerState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C01121(DrawerState drawerState, Continuation<? super C01121> continuation) {
                                        super(2, continuation);
                                        this.$drawerState = drawerState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C01121(this.$drawerState, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C01121) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (this.$drawerState.close(this) == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                break;
                                            case 1:
                                                ResultKt.throwOnFailure($result);
                                                break;
                                            default:
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            }, 1, null);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv3 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv4, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            drawerState4 = drawerState3;
            MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv3 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function4 = constructor3;
                $composer2.createNode(function4);
            } else {
                function4 = constructor3;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i9 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 590751801, "C479@19381L15:NavigationDrawer.kt#uh7d8r");
            function2.invoke($composer2, Integer.valueOf($dirty & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier modifier$iv4 = Modifier.INSTANCE;
            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
            int $changed$iv$iv4 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
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
            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash4);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i11 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 590807167, "C481@19437L9:NavigationDrawer.kt#uh7d8r");
            function3.invoke($composer2, Integer.valueOf(($dirty >> 12) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            gesturesEnabled2 = gesturesEnabled3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final DrawerState drawerState5 = drawerState4;
            final boolean z = gesturesEnabled2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.DismissibleNavigationDrawer.3
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

                public final void invoke(Composer composer, int i12) {
                    NavigationDrawerKt.DismissibleNavigationDrawer(function2, modifier5, drawerState5, z, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DismissibleNavigationDrawer$lambda$16(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DismissibleNavigationDrawer$lambda$17(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final void PermanentNavigationDrawer(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer2 = $composer.startRestartGroup(-276843608);
        ComposerKt.sourceInformation($composer2, "C(PermanentNavigationDrawer)P(1,2)538@21771L85:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
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
                ComposerKt.traceEventStart(-276843608, $dirty2, -1, "androidx.compose.material3.PermanentNavigationDrawer (NavigationDrawer.kt:537)");
            }
            Modifier modifier$iv = SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            modifier3 = modifier4;
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
            ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -882563509, "C539@21809L15,540@21833L17:NavigationDrawer.kt#uh7d8r");
            function2.invoke($composer2, Integer.valueOf($dirty2 & 14));
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier modifier$iv2 = Modifier.INSTANCE;
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function1 = constructor2;
                $composer2.createNode(function1);
            } else {
                function1 = constructor2;
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
            int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1559366900, "C540@21839L9:NavigationDrawer.kt#uh7d8r");
            function3.invoke($composer2, Integer.valueOf(($dirty2 >> 6) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.PermanentNavigationDrawer.2
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

                public final void invoke(Composer composer, int i7) {
                    NavigationDrawerKt.PermanentNavigationDrawer(function2, modifier5, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: ModalDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m2658ModalDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        Modifier.Companion modifier3;
        WindowInsets windowInsets3;
        Modifier modifier4;
        WindowInsets windowInsets4;
        Shape drawerShape3;
        long drawerContainerColor3;
        long drawerContentColor3;
        float drawerTonalElevation3;
        Composer $composer2 = $composer.startRestartGroup(1001163336);
        ComposerKt.sourceInformation($composer2, "C(ModalDrawerSheet)P(5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,6)567@23196L5,568@23252L19,569@23305L37,571@23460L12,574@23530L225:NavigationDrawer.kt#uh7d8r");
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
                drawerShape2 = drawerShape;
                int i3 = $composer2.changed(drawerShape2) ? 32 : 16;
                $dirty |= i3;
            } else {
                drawerShape2 = drawerShape;
            }
            $dirty |= i3;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i4 = $composer2.changed(drawerContainerColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i5 = $composer2.changed(drawerContentColor2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty |= i5;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 24576) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer2.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer2.changed(windowInsets2) ? 131072 : 65536;
                $dirty |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 1048576 : 524288;
        }
        if ((599187 & $dirty) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
            windowInsets4 = windowInsets2;
            modifier4 = modifier2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    $dirty &= -113;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getModalContainerColor($composer2, 6);
                }
                if ((i & 8) != 0) {
                    long drawerContentColor4 = ColorSchemeKt.m2304contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                    drawerContentColor2 = drawerContentColor4;
                }
                if (i6 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2450getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty &= -458753;
                } else {
                    windowInsets3 = windowInsets2;
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
                if ((i & 32) != 0) {
                    $dirty = (-458753) & $dirty;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                } else {
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1001163336, $dirty, -1, "androidx.compose.material3.ModalDrawerSheet (NavigationDrawer.kt:573)");
            }
            m2656DrawerSheet7zSek6w(null, windowInsets3, modifier3, drawerShape2, drawerContainerColor2, drawerContentColor2, drawerTonalElevation2, function3, $composer2, (($dirty >> 12) & 112) | 6 | (($dirty << 6) & 896) | (($dirty << 6) & 7168) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            windowInsets4 = windowInsets3;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final Shape shape = drawerShape3;
            final long j = drawerContainerColor3;
            final long j2 = drawerContentColor3;
            final float f = drawerTonalElevation3;
            final WindowInsets windowInsets5 = windowInsets4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalDrawerSheet$1
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
                    NavigationDrawerKt.m2658ModalDrawerSheetafqeVBk(modifier5, shape, j, j2, f, windowInsets5, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: ModalDrawerSheet-Snr_uVM, reason: not valid java name */
    public static final void m2657ModalDrawerSheetSnr_uVM(final DrawerState drawerState, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        Modifier.Companion modifier2;
        Modifier modifier3;
        Shape drawerShape3;
        long drawerContainerColor3;
        long drawerContentColor3;
        float drawerTonalElevation3;
        WindowInsets windowInsets3;
        Composer $composer2 = $composer.startRestartGroup(1513027356);
        ComposerKt.sourceInformation($composer2, "C(ModalDrawerSheet)P(4,6,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.unit.Dp,7)611@25149L5,612@25205L19,613@25258L37,615@25413L12,618@25524L299,618@25483L340:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(drawerState) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerShape2 = drawerShape;
                int i3 = $composer2.changed(drawerShape2) ? 256 : 128;
                $dirty |= i3;
            } else {
                drawerShape2 = drawerShape;
            }
            $dirty |= i3;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i4 = $composer2.changed(drawerContainerColor2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i5 = $composer2.changed(drawerContentColor2) ? 16384 : 8192;
                $dirty |= i5;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty |= i5;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if ((196608 & $changed) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer2.changed(drawerTonalElevation2) ? 131072 : 65536;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer2.changed(windowInsets2) ? 1048576 : 524288;
                $dirty |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        if (($dirty & 4793491) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
            windowInsets3 = windowInsets2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getModalContainerColor($composer2, 6);
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2304contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i6 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2450getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    windowInsets2 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1513027356, $dirty, -1, "androidx.compose.material3.ModalDrawerSheet (NavigationDrawer.kt:617)");
            }
            final WindowInsets windowInsets4 = windowInsets2;
            final Modifier modifier4 = modifier2;
            final Shape shape = drawerShape2;
            final long j = drawerContainerColor2;
            final long j2 = drawerContentColor2;
            final float f = drawerTonalElevation2;
            Modifier modifier5 = modifier2;
            NavigationDrawer_androidKt.DrawerPredictiveBackHandler(drawerState, ComposableLambdaKt.rememberComposableLambda(1552342929, true, new Function3<DrawerPredictiveBackState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalDrawerSheet$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer composer, Integer num) {
                    invoke(drawerPredictiveBackState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C619@25563L254:NavigationDrawer.kt#uh7d8r");
                    int $dirty2 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty2 |= $composer3.changed(drawerPredictiveBackState) ? 4 : 2;
                    }
                    int $dirty3 = $dirty2;
                    if (($dirty3 & 19) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1552342929, $dirty3, -1, "androidx.compose.material3.ModalDrawerSheet.<anonymous> (NavigationDrawer.kt:619)");
                        }
                        NavigationDrawerKt.m2656DrawerSheet7zSek6w(drawerPredictiveBackState, windowInsets4, modifier4, shape, j, j2, f, function3, $composer3, $dirty3 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
            windowInsets3 = windowInsets2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            final Shape shape2 = drawerShape3;
            final long j3 = drawerContainerColor3;
            final long j4 = drawerContentColor3;
            final float f2 = drawerTonalElevation3;
            final WindowInsets windowInsets5 = windowInsets3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalDrawerSheet$3
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
                    NavigationDrawerKt.m2657ModalDrawerSheetSnr_uVM(drawerState, modifier6, shape2, j3, j4, f2, windowInsets5, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: DismissibleDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m2655DismissibleDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        Modifier.Companion modifier2;
        int $dirty;
        float drawerTonalElevation3;
        WindowInsets windowInsets3;
        Modifier modifier3;
        float drawerTonalElevation4;
        WindowInsets windowInsets4;
        Shape drawerShape3;
        long drawerContainerColor3;
        long drawerContentColor3;
        Composer $composer2 = $composer.startRestartGroup(-588600583);
        ComposerKt.sourceInformation($composer2, "C(DismissibleDrawerSheet)P(5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,6)656@27243L22,657@27299L37,659@27460L12,662@27530L225:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            drawerShape2 = drawerShape;
        } else if (($changed & 48) == 0) {
            drawerShape2 = drawerShape;
            $dirty2 |= $composer2.changed(drawerShape2) ? 32 : 16;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i4 = $composer2.changed(drawerContainerColor2) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty2 |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i5 = $composer2.changed(drawerContentColor2) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty2 |= i5;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 24576) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty2 |= $composer2.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer2.changed(windowInsets2) ? 131072 : 65536;
                $dirty2 |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 1048576 : 524288;
        }
        if (($dirty2 & 599187) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation4 = drawerTonalElevation2;
            windowInsets4 = windowInsets2;
            modifier3 = modifier;
            drawerShape3 = drawerShape2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer2, 6);
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2304contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i6 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2448getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty = $dirty2 & (-458753);
                    drawerTonalElevation3 = drawerTonalElevation2;
                } else {
                    $dirty = $dirty2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty = (-458753) & $dirty2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    $dirty = $dirty2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-588600583, $dirty, -1, "androidx.compose.material3.DismissibleDrawerSheet (NavigationDrawer.kt:661)");
            }
            m2656DrawerSheet7zSek6w(null, windowInsets3, modifier2, drawerShape2, drawerContainerColor2, drawerContentColor2, drawerTonalElevation3, function3, $composer2, 6 | (($dirty >> 12) & 112) | (($dirty << 6) & 896) | (($dirty << 6) & 7168) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            drawerTonalElevation4 = drawerTonalElevation3;
            windowInsets4 = windowInsets3;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final Shape shape = drawerShape3;
            final long j = drawerContainerColor3;
            final long j2 = drawerContentColor3;
            final float f = drawerTonalElevation4;
            final WindowInsets windowInsets5 = windowInsets4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleDrawerSheet$1
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
                    NavigationDrawerKt.m2655DismissibleDrawerSheetafqeVBk(modifier4, shape, j, j2, f, windowInsets5, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: DismissibleDrawerSheet-Snr_uVM, reason: not valid java name */
    public static final void m2654DismissibleDrawerSheetSnr_uVM(final DrawerState drawerState, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        Modifier.Companion modifier2;
        WindowInsets windowInsets2;
        Modifier modifier3;
        WindowInsets windowInsets3;
        Shape drawerShape3;
        long drawerContainerColor3;
        long drawerContentColor3;
        float drawerTonalElevation3;
        Composer $composer2 = $composer.startRestartGroup(1473549901);
        ComposerKt.sourceInformation($composer2, "C(DismissibleDrawerSheet)P(4,6,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.unit.Dp,7)700@29223L22,701@29279L37,703@29440L12,706@29551L299,706@29510L340:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(drawerState) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            drawerShape2 = drawerShape;
        } else if (($changed & 384) == 0) {
            drawerShape2 = drawerShape;
            $dirty |= $composer2.changed(drawerShape2) ? 256 : 128;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i4 = $composer2.changed(drawerContainerColor2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i5 = $composer2.changed(drawerContentColor2) ? 16384 : 8192;
                $dirty |= i5;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty |= i5;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if ((196608 & $changed) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer2.changed(drawerTonalElevation2) ? 131072 : 65536;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= ((i & 64) == 0 && $composer2.changed(windowInsets)) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        if (($dirty & 4793491) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            windowInsets3 = windowInsets;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer2, 6);
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2304contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i6 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2448getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 64) != 0) {
                    windowInsets2 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty &= -3670017;
                } else {
                    windowInsets2 = windowInsets;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 64) != 0) {
                    windowInsets2 = windowInsets;
                    $dirty &= -3670017;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1473549901, $dirty, -1, "androidx.compose.material3.DismissibleDrawerSheet (NavigationDrawer.kt:705)");
            }
            final WindowInsets windowInsets4 = windowInsets2;
            final Modifier modifier4 = modifier2;
            final Shape shape = drawerShape2;
            final long j = drawerContainerColor2;
            final long j2 = drawerContentColor2;
            final float f = drawerTonalElevation2;
            Modifier modifier5 = modifier2;
            NavigationDrawer_androidKt.DrawerPredictiveBackHandler(drawerState, ComposableLambdaKt.rememberComposableLambda(-807955710, true, new Function3<DrawerPredictiveBackState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleDrawerSheet$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer composer, Integer num) {
                    invoke(drawerPredictiveBackState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C707@29590L254:NavigationDrawer.kt#uh7d8r");
                    int $dirty2 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty2 |= $composer3.changed(drawerPredictiveBackState) ? 4 : 2;
                    }
                    int $dirty3 = $dirty2;
                    if (($dirty3 & 19) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-807955710, $dirty3, -1, "androidx.compose.material3.DismissibleDrawerSheet.<anonymous> (NavigationDrawer.kt:707)");
                        }
                        NavigationDrawerKt.m2656DrawerSheet7zSek6w(drawerPredictiveBackState, windowInsets4, modifier4, shape, j, j2, f, function3, $composer3, $dirty3 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            windowInsets3 = windowInsets2;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            final Shape shape2 = drawerShape3;
            final long j3 = drawerContainerColor3;
            final long j4 = drawerContentColor3;
            final float f2 = drawerTonalElevation3;
            final WindowInsets windowInsets5 = windowInsets3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleDrawerSheet$3
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

                public final void invoke(Composer composer, int i7) {
                    NavigationDrawerKt.m2654DismissibleDrawerSheetSnr_uVM(drawerState, modifier6, shape2, j3, j4, f2, windowInsets5, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: PermanentDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m2660PermanentDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        Modifier.Companion modifier2;
        int $dirty;
        float drawerTonalElevation3;
        WindowInsets windowInsets3;
        Object value$iv;
        Modifier modifier3;
        float drawerTonalElevation4;
        WindowInsets windowInsets4;
        Shape drawerShape3;
        long drawerContainerColor3;
        long drawerContentColor3;
        Composer $composer2 = $composer.startRestartGroup(-1733353241);
        ComposerKt.sourceInformation($composer2, "C(PermanentDrawerSheet)P(5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,6)740@31022L22,741@31078L37,743@31237L12,746@31328L33,750@31470L30,747@31366L266:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            drawerShape2 = drawerShape;
        } else if (($changed & 48) == 0) {
            drawerShape2 = drawerShape;
            $dirty2 |= $composer2.changed(drawerShape2) ? 32 : 16;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i4 = $composer2.changed(drawerContainerColor2) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty2 |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i5 = $composer2.changed(drawerContentColor2) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty2 |= i5;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 24576) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty2 |= $composer2.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer2.changed(windowInsets2) ? 131072 : 65536;
                $dirty2 |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 1048576 : 524288;
        }
        if (($dirty2 & 599187) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation4 = drawerTonalElevation2;
            windowInsets4 = windowInsets2;
            modifier3 = modifier;
            drawerShape3 = drawerShape2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer2, 6);
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2304contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i6 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2451getPermanentDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty = $dirty2 & (-458753);
                    drawerTonalElevation3 = drawerTonalElevation2;
                } else {
                    $dirty = $dirty2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty = (-458753) & $dirty2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    $dirty = $dirty2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1733353241, $dirty, -1, "androidx.compose.material3.PermanentDrawerSheet (NavigationDrawer.kt:745)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String navigationMenu = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 391093473, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(navigationMenu);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setPaneTitle($this$semantics, navigationMenu);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m2656DrawerSheet7zSek6w(null, windowInsets3, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) value$iv, 1, null), drawerShape2, drawerContainerColor2, drawerContentColor2, drawerTonalElevation3, function3, $composer2, (($dirty >> 12) & 112) | 6 | (($dirty << 6) & 7168) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            drawerTonalElevation4 = drawerTonalElevation3;
            windowInsets4 = windowInsets3;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final Shape shape = drawerShape3;
            final long j = drawerContainerColor3;
            final long j2 = drawerContentColor3;
            final float f = drawerTonalElevation4;
            final WindowInsets windowInsets5 = windowInsets4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$2
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
                    NavigationDrawerKt.m2660PermanentDrawerSheetafqeVBk(modifier4, shape, j, j2, f, windowInsets5, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: DrawerSheet-7zSek6w, reason: not valid java name */
    public static final void m2656DrawerSheet7zSek6w(final DrawerPredictiveBackState drawerPredictiveBackState, final WindowInsets windowInsets, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        Modifier.Companion modifier2;
        float drawerTonalElevation2;
        Modifier modifier3;
        float drawerTonalElevation3;
        Shape drawerShape3;
        long drawerContainerColor3;
        long drawerContentColor3;
        Composer $composer2 = $composer.startRestartGroup(-151557245);
        ComposerKt.sourceInformation($composer2, "C(DrawerSheet)P(3,7,6,4,1:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.unit.Dp)765@31890L22,766@31946L37,770@32146L7,785@32790L545,775@32383L952:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(drawerPredictiveBackState) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(windowInsets) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            drawerShape2 = drawerShape;
        } else if (($changed & 3072) == 0) {
            drawerShape2 = drawerShape;
            $dirty |= $composer2.changed(drawerShape2) ? 2048 : 1024;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i4 = $composer2.changed(drawerContainerColor2) ? 16384 : 8192;
                $dirty |= i4;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i5 = $composer2.changed(drawerContentColor2) ? 131072 : 65536;
                $dirty |= i5;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty |= i5;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(drawerTonalElevation) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            drawerTonalElevation3 = drawerTonalElevation;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 16) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer2, 6);
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2304contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 12) & 14);
                    $dirty &= -458753;
                }
                drawerTonalElevation2 = i6 != 0 ? DrawerDefaults.INSTANCE.m2451getPermanentDrawerElevationD9Ej5fM() : drawerTonalElevation;
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    drawerTonalElevation2 = drawerTonalElevation;
                    $dirty &= -458753;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    drawerTonalElevation2 = drawerTonalElevation;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-151557245, $dirty, -1, "androidx.compose.material3.DrawerSheet (NavigationDrawer.kt:769)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final boolean isRtl = objConsume == LayoutDirection.Rtl;
            Modifier.Companion predictiveBackDrawerContainerModifier = drawerPredictiveBackState != null ? predictiveBackDrawerContainer(Modifier.INSTANCE, drawerPredictiveBackState, isRtl) : Modifier.INSTANCE;
            Modifier modifier4 = modifier2;
            SurfaceKt.m2917SurfaceT9BRK9s(SizeKt.fillMaxHeight$default(SizeKt.m1083sizeInqDBjuR0$default(modifier4, MinimumDrawerWidth, 0.0f, DrawerDefaults.INSTANCE.m2449getMaximumDrawerWidthD9Ej5fM(), 0.0f, 10, null).then(predictiveBackDrawerContainerModifier), 0.0f, 1, null), drawerShape2, drawerContainerColor2, drawerContentColor2, drawerTonalElevation2, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(669057502, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$1
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
                    Modifier.Companion predictiveBackDrawerChildModifier;
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer3, "C790@33010L319:NavigationDrawer.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(669057502, $changed2, -1, "androidx.compose.material3.DrawerSheet.<anonymous> (NavigationDrawer.kt:786)");
                        }
                        if (drawerPredictiveBackState != null) {
                            predictiveBackDrawerChildModifier = NavigationDrawerKt.predictiveBackDrawerChild(Modifier.INSTANCE, drawerPredictiveBackState, isRtl);
                        } else {
                            predictiveBackDrawerChildModifier = Modifier.INSTANCE;
                        }
                        Modifier modifier$iv = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.m1083sizeInqDBjuR0$default(Modifier.INSTANCE, NavigationDrawerKt.MinimumDrawerWidth, 0.0f, DrawerDefaults.INSTANCE.m2449getMaximumDrawerWidthD9Ej5fM(), 0.0f, 10, null).then(predictiveBackDrawerChildModifier), windowInsets);
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
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
                        int i7 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf(((0 >> 6) & 112) | 6));
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
            }, $composer2, 54), $composer2, (($dirty >> 6) & 112) | 12582912 | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty >> 6) & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            drawerTonalElevation3 = drawerTonalElevation2;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final Shape shape = drawerShape3;
            final long j = drawerContainerColor3;
            final long j2 = drawerContentColor3;
            final float f = drawerTonalElevation3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$2
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

                public final void invoke(Composer composer, int i7) {
                    NavigationDrawerKt.m2656DrawerSheet7zSek6w(drawerPredictiveBackState, windowInsets, modifier5, shape, j, j2, f, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final Modifier predictiveBackDrawerContainer(Modifier $this$predictiveBackDrawerContainer, final DrawerPredictiveBackState drawerPredictiveBackState, final boolean isRtl) {
        return GraphicsLayerModifierKt.graphicsLayer($this$predictiveBackDrawerContainer, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.predictiveBackDrawerContainer.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                invoke2(graphicsLayerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                $this$graphicsLayer.setScaleX(NavigationDrawerKt.calculatePredictiveBackScaleX($this$graphicsLayer, drawerPredictiveBackState));
                $this$graphicsLayer.setScaleY(NavigationDrawerKt.calculatePredictiveBackScaleY($this$graphicsLayer, drawerPredictiveBackState));
                $this$graphicsLayer.mo4727setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(isRtl ? 1.0f : 0.0f, 0.5f));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier predictiveBackDrawerChild(Modifier $this$predictiveBackDrawerChild, final DrawerPredictiveBackState drawerPredictiveBackState, final boolean isRtl) {
        return GraphicsLayerModifierKt.graphicsLayer($this$predictiveBackDrawerChild, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.predictiveBackDrawerChild.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                invoke2(graphicsLayerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                float containerScaleX = NavigationDrawerKt.calculatePredictiveBackScaleX($this$graphicsLayer, drawerPredictiveBackState);
                float containerScaleY = NavigationDrawerKt.calculatePredictiveBackScaleY($this$graphicsLayer, drawerPredictiveBackState);
                $this$graphicsLayer.setScaleX(!((containerScaleX > 0.0f ? 1 : (containerScaleX == 0.0f ? 0 : -1)) == 0) ? containerScaleY / containerScaleX : 1.0f);
                $this$graphicsLayer.mo4727setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(isRtl ? 0.0f : 1.0f, 0.0f));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleX(GraphicsLayerScope $this$calculatePredictiveBackScaleX, DrawerPredictiveBackState drawerPredictiveBackState) {
        float width = Size.m4362getWidthimpl($this$calculatePredictiveBackScaleX.getSize());
        if (Float.isNaN(width)) {
            return 1.0f;
        }
        if (width == 0.0f) {
            return 1.0f;
        }
        int scaleXDirection = drawerPredictiveBackState.getSwipeEdgeMatchesDrawer() ? 1 : -1;
        return 1.0f + ((drawerPredictiveBackState.getScaleXDistance() * scaleXDirection) / width);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleY(GraphicsLayerScope $this$calculatePredictiveBackScaleY, DrawerPredictiveBackState drawerPredictiveBackState) {
        float height = Size.m4359getHeightimpl($this$calculatePredictiveBackScaleY.getSize());
        if (Float.isNaN(height)) {
            return 1.0f;
        }
        if (height == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (drawerPredictiveBackState.getScaleYDistance() / height);
    }

    public static final void NavigationDrawerItem(final Function2<? super Composer, ? super Integer, Unit> function2, final boolean selected, final Function0<Unit> function0, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Shape shape, NavigationDrawerItemColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Shape shape2;
        Shape shape3;
        NavigationDrawerItemColors colors2;
        MutableInteractionSource interactionSource2;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Shape shape4;
        int $dirty;
        NavigationDrawerItemColors colors3;
        Modifier modifier3;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1304626543);
        ComposerKt.sourceInformation($composer3, "C(NavigationDrawerItem)P(4,7,6,5,2!1,8)931@38697L5,932@38774L8,944@39157L24,946@39242L884,935@38848L1278:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(selected) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 2048 : 1024;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty2 |= 24576;
            function5 = function3;
        } else if (($changed & 24576) == 0) {
            function5 = function3;
            $dirty2 |= $composer3.changedInstance(function5) ? 16384 : 8192;
        } else {
            function5 = function3;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function6 = function4;
        } else if ((196608 & $changed) == 0) {
            function6 = function4;
            $dirty2 |= $composer3.changedInstance(function6) ? 131072 : 65536;
        } else {
            function6 = function4;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 1048576 : 524288;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer3.changed(colors)) ? 8388608 : 4194304;
        }
        int i6 = i & 256;
        if (i6 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty2 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            colors3 = colors;
            interactionSource2 = interactionSource;
            $composer2 = $composer3;
            function7 = function5;
            shape4 = shape2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                Function2<? super Composer, ? super Integer, Unit> function9 = i3 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function10 = i4 != 0 ? null : function6;
                if ((i & 64) != 0) {
                    shape3 = ShapesKt.getValue(NavigationDrawerTokens.INSTANCE.getActiveIndicatorShape(), $composer3, 6);
                    $dirty2 &= -3670017;
                } else {
                    shape3 = shape2;
                }
                if ((i & 128) != 0) {
                    colors2 = NavigationDrawerItemDefaults.INSTANCE.m2653colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 100663296, 255);
                    $dirty2 &= -29360129;
                } else {
                    colors2 = colors;
                }
                if (i6 != 0) {
                    interactionSource2 = null;
                    modifier2 = modifier4;
                    function7 = function9;
                    function8 = function10;
                    shape4 = shape3;
                    $dirty = $dirty2;
                } else {
                    interactionSource2 = interactionSource;
                    modifier2 = modifier4;
                    function7 = function9;
                    function8 = function10;
                    shape4 = shape3;
                    $dirty = $dirty2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    colors2 = colors;
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2 & (-29360129);
                    function7 = function5;
                    function8 = function6;
                    shape4 = shape2;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    colors2 = colors;
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2;
                    function7 = function5;
                    function8 = function6;
                    shape4 = shape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1304626543, $dirty, -1, "androidx.compose.material3.NavigationDrawerItem (NavigationDrawer.kt:934)");
            }
            final Function2<? super Composer, ? super Integer, Unit> function11 = function7;
            final NavigationDrawerItemColors navigationDrawerItemColors = colors2;
            final Function2<? super Composer, ? super Integer, Unit> function12 = function8;
            colors3 = colors2;
            modifier3 = modifier2;
            $composer2 = $composer3;
            SurfaceKt.m2918Surfaced85dljk(selected, function0, SizeKt.fillMaxWidth$default(SizeKt.m1067heightInVpY3zN4$default(SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.NavigationDrawerItem.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m6308setRolekuIjeqM($this$semantics, Role.INSTANCE.m6295getTabo7Vup1c());
                }
            }, 1, null), NavigationDrawerTokens.INSTANCE.m3697getActiveIndicatorHeightD9Ej5fM(), 0.0f, 2, null), 0.0f, 1, null), false, shape4, colors2.containerColor(selected, $composer3, (($dirty >> 3) & 14) | (($dirty >> 18) & 112)).getValue().m4544unboximpl(), 0L, 0.0f, 0.0f, (BorderStroke) null, interactionSource2, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(191488423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.NavigationDrawerItem.2
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

                public final void invoke(Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function1;
                    Function0<ComposeUiNode> function13;
                    ComposerKt.sourceInformation($composer4, "C947@39252L868:NavigationDrawer.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(191488423, $changed2, -1, "androidx.compose.material3.NavigationDrawerItem.<anonymous> (NavigationDrawer.kt:947)");
                        }
                        Modifier modifier$iv = PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7016constructorimpl(16), 0.0f, Dp.m7016constructorimpl(24), 0.0f, 10, null);
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function2<Composer, Integer, Unit> function14 = function11;
                        NavigationDrawerItemColors navigationDrawerItemColors2 = navigationDrawerItemColors;
                        boolean z = selected;
                        Function2<Composer, Integer, Unit> function15 = function12;
                        Function2<Composer, Integer, Unit> function16 = function2;
                        ComposerKt.sourceInformationMarkerStart($composer4, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer4, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function1 = constructor;
                            $composer4.createNode(function1);
                        } else {
                            function1 = constructor;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer4);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i7 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        int i8 = ((390 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u241 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1046361182, "C956@39650L203:NavigationDrawer.kt#uh7d8r");
                        $composer4.startReplaceGroup(-449396056);
                        ComposerKt.sourceInformation($composer4, "952@39457L19,953@39499L78,954@39594L29");
                        if (function14 != null) {
                            long iconColor = navigationDrawerItemColors2.iconColor(z, $composer4, 0).getValue().m4544unboximpl();
                            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(iconColor)), function14, $composer4, ProvidedValue.$stable);
                            SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(12)), $composer4, 6);
                        }
                        $composer4.endReplaceGroup();
                        Modifier modifier$iv2 = RowScope.weight$default($this$invoke_u24lambda_u241, Modifier.INSTANCE, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv2 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function13 = constructor2;
                            $composer4.createNode(function13);
                        } else {
                            function13 = constructor2;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer4);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i9 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 839121305, "C957@39717L19,958@39759L80:NavigationDrawer.kt#uh7d8r");
                        long labelColor = navigationDrawerItemColors2.textColor(z, $composer4, 0).getValue().m4544unboximpl();
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(labelColor)), function16, $composer4, ProvidedValue.$stable);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.startReplaceGroup(-449381075);
                        ComposerKt.sourceInformation($composer4, "961@39903L29,962@39973L20,963@40016L80");
                        if (function15 != null) {
                            SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(12)), $composer4, 6);
                            long badgeColor = navigationDrawerItemColors2.badgeColor(z, $composer4, 0).getValue().m4544unboximpl();
                            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(badgeColor)), function15, $composer4, ProvidedValue.$stable);
                        }
                        $composer4.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 6) & 57344), (($dirty >> 24) & 14) | 48, 968);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function8;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final Function2<? super Composer, ? super Integer, Unit> function13 = function7;
            final Function2<? super Composer, ? super Integer, Unit> function14 = function6;
            final Shape shape5 = shape4;
            final NavigationDrawerItemColors navigationDrawerItemColors2 = colors3;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.NavigationDrawerItem.3
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

                public final void invoke(Composer composer, int i7) {
                    NavigationDrawerKt.NavigationDrawerItem(function2, selected, function0, modifier5, function13, function14, shape5, navigationDrawerItemColors2, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateFraction(float a, float b, float pos) {
        return RangesKt.coerceIn((pos - a) / (b - a), 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-Bx497Mc, reason: not valid java name */
    public static final void m2661ScrimBx497Mc(final boolean open, final Function0<Unit> function0, final Function0<Float> function1, final long color, Composer $composer, final int $changed) {
        Modifier.Companion dismissDrawer;
        Object value$iv;
        NavigationDrawerKt$Scrim$dismissDrawer$1$1 value$iv2;
        Object value$iv3;
        Composer $composer2 = $composer.startRestartGroup(2106487387);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(3,2,1,0:c#ui.graphics.Color)1142@46950L30,1157@47448L39,1157@47397L90:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(open) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(color) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2106487387, $dirty2, -1, "androidx.compose.material3.Scrim (NavigationDrawer.kt:1141)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String closeDrawer = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(androidx.compose.ui.R.string.close_drawer), $composer2, 0);
            $composer2.startReplaceGroup(-1784743395);
            ComposerKt.sourceInformation($composer2, "1145@47068L35,1146@47156L187");
            if (open) {
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1784741978, "CC(remember):NavigationDrawer.kt#9igjgp");
                boolean invalid$iv = ($dirty2 & 112) == 32;
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new NavigationDrawerKt$Scrim$dismissDrawer$1$1(function0, null);
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion2, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2);
                ComposerKt.sourceInformationMarkerStart($composer2, -1784739010, "CC(remember):NavigationDrawer.kt#9igjgp");
                boolean invalid$iv2 = $composer2.changed(closeDrawer) | (($dirty2 & 112) == 32);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$dismissDrawer$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.setContentDescription($this$semantics, closeDrawer);
                            final Function0<Unit> function2 = function0;
                            SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$dismissDrawer$2$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    function2.invoke();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                dismissDrawer = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) value$iv3);
            } else {
                dismissDrawer = Modifier.INSTANCE;
            }
            $composer2.endReplaceGroup();
            Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissDrawer);
            ComposerKt.sourceInformationMarkerStart($composer2, -1784729814, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv3 = (($dirty2 & 7168) == 2048) | (($dirty2 & 896) == 256);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$Canvas) {
                        DrawScope.m5084drawRectnJ9OG0$default($this$Canvas, color, 0L, 0L, function1.invoke().floatValue(), null, null, 0, ComposerChipPolicy.CHIP_MAX_WIDTH_DP, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierThen, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    NavigationDrawerKt.m2661ScrimBx497Mc(open, function0, function1, color, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
