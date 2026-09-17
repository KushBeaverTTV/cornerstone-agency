package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationConstants;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
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
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.app.NotificationCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PullToRefresh.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0002\u001a(\u0010\u0017\u001a\u00020\u00182\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0003ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u007f\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\u00192\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020'2\u001e\b\u0002\u0010(\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00180)¢\u0006\u0002\b+¢\u0006\u0002\b,2\u001c\u0010-\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00180)¢\u0006\u0002\b+¢\u0006\u0002\b,H\u0007¢\u0006\u0002\u0010.\u001a\b\u0010/\u001a\u00020%H\u0007\u001a\r\u00100\u001a\u00020%H\u0007¢\u0006\u0002\u00101\u001aF\u00102\u001a\u00020\u0018*\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a>\u0010=\u001a\u00020\u0018*\u0002032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u00152\u0006\u0010>\u001a\u0002072\u0006\u0010:\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010@\u001aH\u0010A\u001a\u00020#*\u00020#2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010B\u001a\u00020 2\b\b\u0002\u0010C\u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\bD\u0010E\u001aN\u0010F\u001a\u00020#*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010C\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020H2\b\b\u0002\u0010I\u001a\u00020\u001b2\b\b\u0002\u0010J\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\bK\u0010L\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u000e\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000f\u0010\u0010\"\u0016\u0010\u0011\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0012\u0010\u0010\"\u0010\u0010\u0013\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006M²\u0006\n\u0010N\u001a\u00020\u0002X\u008a\u0084\u0002"}, d2 = {"AlphaTween", "Landroidx/compose/animation/core/TweenSpec;", "", "ArcRadius", "Landroidx/compose/ui/unit/Dp;", "F", "ArrowHeight", "ArrowWidth", "CrossfadeDurationMs", "", "DragMultiplier", "MaxAlpha", "MaxProgressArc", "MinAlpha", "SpinnerContainerSize", "getSpinnerContainerSize", "()F", "SpinnerSize", "getSpinnerSize", "StrokeWidth", "ArrowValues", "Landroidx/compose/material3/pulltorefresh/ArrowValues;", NotificationCompat.CATEGORY_PROGRESS, "CircularArrowProgressIndicator", "", "Lkotlin/Function0;", "color", "Landroidx/compose/ui/graphics/Color;", "CircularArrowProgressIndicator-RPmYEkk", "(Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "PullToRefreshBox", "isRefreshing", "", "onRefresh", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "indicator", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "content", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/pulltorefresh/PullToRefreshState;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "PullToRefreshState", "rememberPullToRefreshState", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "drawArrow", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "arrow", "Landroidx/compose/ui/graphics/Path;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "alpha", "values", "strokeWidth", "drawArrow-uDrxG_w", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/geometry/Rect;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;F)V", "drawCircularIndicator", "arcBounds", "drawCircularIndicator-KzyDr3Q", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;Landroidx/compose/ui/geometry/Rect;F)V", "pullToRefresh", "enabled", "threshold", "pullToRefresh-Z4HSEVQ", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/pulltorefresh/PullToRefreshState;ZFLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/Modifier;", "pullToRefreshIndicator", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "elevation", "pullToRefreshIndicator-wUdLESc", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/pulltorefresh/PullToRefreshState;ZFLandroidx/compose/ui/graphics/Shape;JF)Landroidx/compose/ui/Modifier;", "material3_release", "targetAlpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PullToRefreshKt {
    private static final int CrossfadeDurationMs = 100;
    private static final float DragMultiplier = 0.5f;
    private static final float MaxAlpha = 1.0f;
    private static final float MaxProgressArc = 0.8f;
    private static final float MinAlpha = 0.3f;
    private static final float StrokeWidth = Dp.m7016constructorimpl((float) 2.5d);
    private static final float ArcRadius = Dp.m7016constructorimpl((float) 5.5d);
    private static final float SpinnerSize = Dp.m7016constructorimpl(16);
    private static final float SpinnerContainerSize = Dp.m7016constructorimpl(40);
    private static final float ArrowWidth = Dp.m7016constructorimpl(10);
    private static final float ArrowHeight = Dp.m7016constructorimpl(5);
    private static final TweenSpec<Float> AlphaTween = AnimationSpecKt.tween$default(AnimationConstants.DefaultDurationMillis, 0, EasingKt.getLinearEasing(), 2, null);

    public static final void PullToRefreshBox(final boolean isRefreshing, final Function0<Unit> function0, Modifier modifier, PullToRefreshState state, Alignment contentAlignment, Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        PullToRefreshState pullToRefreshState;
        Alignment contentAlignment2;
        Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function5;
        final PullToRefreshState state2;
        int $dirty;
        PullToRefreshState state3;
        Alignment contentAlignment3;
        Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda;
        Modifier modifier3;
        Function0<ComposeUiNode> function1;
        PullToRefreshState state4;
        Alignment contentAlignment4;
        Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function6;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(1902956467);
        ComposerKt.sourceInformation($composer2, "C(PullToRefreshBox)P(3,5,4,6,1,2)124@5580L28,126@5713L163,135@5931L199:PullToRefresh.kt#djiw08");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(isRefreshing) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                pullToRefreshState = state;
                int i3 = $composer2.changed(pullToRefreshState) ? 2048 : 1024;
                $dirty2 |= i3;
            } else {
                pullToRefreshState = state;
            }
            $dirty2 |= i3;
        } else {
            pullToRefreshState = state;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            contentAlignment2 = contentAlignment;
        } else if (($changed & 24576) == 0) {
            contentAlignment2 = contentAlignment;
            $dirty2 |= $composer2.changed(contentAlignment2) ? 16384 : 8192;
        } else {
            contentAlignment2 = contentAlignment;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
        } else if ((196608 & $changed) == 0) {
            function5 = function3;
            $dirty2 |= $composer2.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function3;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changedInstance(function4) ? 1048576 : 524288;
        }
        if ((599187 & $dirty2) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            state4 = pullToRefreshState;
            contentAlignment4 = contentAlignment2;
            function6 = function5;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    state2 = rememberPullToRefreshState($composer2, 0);
                    $dirty2 &= -7169;
                } else {
                    state2 = pullToRefreshState;
                }
                if (i4 != 0) {
                    contentAlignment2 = Alignment.INSTANCE.getTopStart();
                }
                if (i5 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier5;
                    state3 = state2;
                    contentAlignment3 = contentAlignment2;
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1989171225, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer, Integer num) {
                            invoke(boxScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(BoxScope $this$null, Composer $composer3, int $changed2) {
                            ComposerKt.sourceInformation($composer3, "C127@5723L147:PullToRefresh.kt#djiw08");
                            int $dirty3 = $changed2;
                            if (($changed2 & 6) == 0) {
                                $dirty3 |= $composer3.changed($this$null) ? 4 : 2;
                            }
                            int $dirty4 = $dirty3;
                            if (($dirty4 & 19) == 18 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1989171225, $dirty4, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:127)");
                            }
                            PullToRefreshDefaults.INSTANCE.m3358Indicator2poqoh4(state2, isRefreshing, $this$null.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, $composer3, 1572864, 56);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54);
                } else {
                    $dirty = $dirty2;
                    state3 = state2;
                    contentAlignment3 = contentAlignment2;
                    function3RememberComposableLambda = function5;
                    modifier3 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                $dirty = $dirty2;
                state3 = pullToRefreshState;
                contentAlignment3 = contentAlignment2;
                function3RememberComposableLambda = function5;
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1902956467, $dirty, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:134)");
            }
            int $dirty3 = $dirty;
            Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function7 = function3RememberComposableLambda;
            Alignment contentAlignment5 = contentAlignment3;
            Modifier modifier$iv = m3372pullToRefreshZ4HSEVQ$default(modifier3, isRefreshing, state3, false, 0.0f, function0, 12, null);
            int $changed$iv = ($dirty3 >> 9) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment5, false);
            int $changed$iv$iv = ($changed$iv << 3) & 112;
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
            int i6 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            int $changed2 = (($changed$iv >> 6) & 112) | 6;
            BoxScope $this$PullToRefreshBox_u24lambda_u240 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 1101976897, "C139@6095L9,140@6113L11:PullToRefresh.kt#djiw08");
            function4.invoke($this$PullToRefreshBox_u24lambda_u240, $composer2, Integer.valueOf(($changed2 & 14) | (($dirty3 >> 15) & 112)));
            function7.invoke($this$PullToRefreshBox_u24lambda_u240, $composer2, Integer.valueOf(($changed2 & 14) | (($dirty3 >> 12) & 112)));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            state4 = state3;
            contentAlignment4 = contentAlignment5;
            function6 = function7;
            modifier4 = modifier3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier4;
            final PullToRefreshState pullToRefreshState2 = state4;
            final Alignment alignment = contentAlignment4;
            final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function8 = function6;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.3
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
                    PullToRefreshKt.PullToRefreshBox(isRefreshing, function0, modifier6, pullToRefreshState2, alignment, function8, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: pullToRefreshIndicator-wUdLESc, reason: not valid java name */
    public static final Modifier m3373pullToRefreshIndicatorwUdLESc(Modifier $this$pullToRefreshIndicator_u2dwUdLESc, final PullToRefreshState state, final boolean isRefreshing, final float threshold, final Shape shape, long containerColor, final float elevation) {
        return BackgroundKt.m588backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(DrawModifierKt.drawWithContent(SizeKt.m1079size3ABfNKs($this$pullToRefreshIndicator_u2dwUdLESc, SpinnerContainerSize), new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$pullToRefreshIndicator$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope $this$drawWithContent) {
                ContentDrawScope $this$clipRect_u2drOu3jXo_u24default$iv = $this$drawWithContent;
                int clipOp$iv = ClipOp.INSTANCE.m4523getIntersectrtfAjoo();
                DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext();
                long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo5011getSizeNHjbRc();
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
                try {
                    DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                    $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo5014clipRectN_I0leg(-3.4028235E38f, 0.0f, Float.MAX_VALUE, Float.MAX_VALUE, clipOp$iv);
                    $this$drawWithContent.drawContent();
                } finally {
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u246$iv$iv.mo5012setSizeuvyYCjk(previousSize$iv$iv);
                }
            }
        }), new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$pullToRefreshIndicator$2
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
                boolean showElevation = state.getDistanceFraction() > 0.0f || isRefreshing;
                $this$graphicsLayer.setTranslationY((state.getDistanceFraction() * $this$graphicsLayer.mo720roundToPx0680j_4(threshold)) - Size.m4359getHeightimpl($this$graphicsLayer.getSize()));
                $this$graphicsLayer.setShadowElevation(showElevation ? $this$graphicsLayer.mo726toPx0680j_4(elevation) : 0.0f);
                $this$graphicsLayer.setShape(shape);
                $this$graphicsLayer.setClip(true);
            }
        }), containerColor, shape);
    }

    /* JADX INFO: renamed from: pullToRefresh-Z4HSEVQ$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3372pullToRefreshZ4HSEVQ$default(Modifier modifier, boolean z, PullToRefreshState pullToRefreshState, boolean z2, float f, Function0 function0, int i, Object obj) {
        boolean z3;
        float fM3360getPositionalThresholdD9Ej5fM;
        if ((i & 4) == 0) {
            z3 = z2;
        } else {
            z3 = true;
        }
        if ((i & 8) == 0) {
            fM3360getPositionalThresholdD9Ej5fM = f;
        } else {
            fM3360getPositionalThresholdD9Ej5fM = PullToRefreshDefaults.INSTANCE.m3360getPositionalThresholdD9Ej5fM();
        }
        return m3371pullToRefreshZ4HSEVQ(modifier, z, pullToRefreshState, z3, fM3360getPositionalThresholdD9Ej5fM, function0);
    }

    /* JADX INFO: renamed from: pullToRefresh-Z4HSEVQ, reason: not valid java name */
    public static final Modifier m3371pullToRefreshZ4HSEVQ(Modifier $this$pullToRefresh_u2dZ4HSEVQ, boolean isRefreshing, PullToRefreshState state, boolean enabled, float threshold, Function0<Unit> function0) {
        return $this$pullToRefresh_u2dZ4HSEVQ.then(new PullToRefreshElement(isRefreshing, function0, enabled, state, threshold, null));
    }

    public static final PullToRefreshState rememberPullToRefreshState(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 318623070, "C(rememberPullToRefreshState)513@19156L83:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(318623070, $changed, -1, "androidx.compose.material3.pulltorefresh.rememberPullToRefreshState (PullToRefresh.kt:512)");
        }
        PullToRefreshStateImpl pullToRefreshStateImpl = (PullToRefreshStateImpl) RememberSaveableKt.m4120rememberSaveable(new Object[0], (Saver) PullToRefreshStateImpl.INSTANCE.getSaver(), (String) null, (Function0) new Function0<PullToRefreshStateImpl>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.rememberPullToRefreshState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PullToRefreshStateImpl invoke() {
                return new PullToRefreshStateImpl();
            }
        }, $composer, 3072, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return pullToRefreshStateImpl;
    }

    public static final PullToRefreshState PullToRefreshState() {
        return new PullToRefreshStateImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CircularArrowProgressIndicator-RPmYEkk, reason: not valid java name */
    public static final void m3365CircularArrowProgressIndicatorRPmYEkk(final Function0<Float> function0, final long color, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-569718810);
        ComposerKt.sourceInformation($composer3, "C(CircularArrowProgressIndicator)P(1,0:c#ui.graphics.Color)562@20583L61,564@20745L76,565@20843L74,567@20982L98,571@21118L443,566@20922L639:PullToRefresh.kt#djiw08");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(color) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-569718810, $dirty2, -1, "androidx.compose.material3.pulltorefresh.CircularArrowProgressIndicator (PullToRefresh.kt:561)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1136642763, "CC(remember):PullToRefresh.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Path $this$CircularArrowProgressIndicator_RPmYEkk_u24lambda_u242_u24lambda_u241 = AndroidPath_androidKt.Path();
                $this$CircularArrowProgressIndicator_RPmYEkk_u24lambda_u242_u24lambda_u241.mo4427setFillTypeoQ8Xj4U(PathFillType.INSTANCE.m4816getEvenOddRgk1Os());
                value$iv = $this$CircularArrowProgressIndicator_RPmYEkk_u24lambda_u242_u24lambda_u241;
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final Path path = (Path) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1136647962, "CC(remember):PullToRefresh.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt.derivedStateOf(new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$targetAlpha$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(function0.invoke().floatValue() < 1.0f ? 0.3f : 1.0f);
                    }
                });
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            State targetAlpha$delegate = (State) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CircularArrowProgressIndicator_RPmYEkk$lambda$4(targetAlpha$delegate), AlphaTween, 0.0f, null, null, $composer3, 48, 28);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1136655568, "CC(remember):PullToRefresh.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 14) == 4;
            Object it$iv3 = $composer3.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$1$1
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
                        SemanticsPropertiesKt.setProgressBarRangeInfo($this$semantics, new ProgressBarRangeInfo(function0.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0));
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1079size3ABfNKs = SizeKt.m1079size3ABfNKs(SemanticsModifierKt.semantics(companion, true, (Function1) value$iv3), SpinnerSize);
            ComposerKt.sourceInformationMarkerStart($composer3, 1136660265, "CC(remember):PullToRefresh.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 14) == 4) | $composer3.changed(stateAnimateFloatAsState) | (($dirty2 & 112) == 32) | $composer3.changedInstance(path);
            Object it$iv4 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) throws Throwable {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$Canvas) throws Throwable {
                        long previousSize$iv$iv;
                        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
                        ArrowValues values = PullToRefreshKt.ArrowValues(function0.invoke().floatValue());
                        float alpha = stateAnimateFloatAsState.getValue().floatValue();
                        float degrees$iv = values.getRotation();
                        long j = color;
                        Path path2 = path;
                        long pivot$iv = $this$Canvas.mo5089getCenterF1C5BW0();
                        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$Canvas.getDrawContext();
                        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo5011getSizeNHjbRc();
                        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
                        try {
                            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
                            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5017rotateUv8p0NA(degrees$iv, pivot$iv);
                            float arcRadius = $this$Canvas.mo726toPx0680j_4(PullToRefreshKt.ArcRadius) + ($this$Canvas.mo726toPx0680j_4(PullToRefreshKt.StrokeWidth) / 2.0f);
                            Rect arcBounds = RectKt.m4332Rect3MmeM6k(androidx.compose.ui.geometry.SizeKt.m4372getCenteruvyYCjk($this$Canvas.mo5090getSizeNHjbRc()), arcRadius);
                            try {
                                PullToRefreshKt.m3370drawCircularIndicatorKzyDr3Q($this$Canvas, j, alpha, values, arcBounds, PullToRefreshKt.StrokeWidth);
                                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                try {
                                    PullToRefreshKt.m3369drawArrowuDrxG_w($this$Canvas, path2, arcBounds, j, alpha, values, PullToRefreshKt.StrokeWidth);
                                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                                    $this$withTransform_u24lambda_u246$iv$iv.mo5012setSizeuvyYCjk(previousSize$iv$iv2);
                                } catch (Throwable th) {
                                    th = th;
                                    previousSize$iv$iv = previousSize$iv$iv2;
                                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                                    $this$withTransform_u24lambda_u246$iv$iv.mo5012setSizeuvyYCjk(previousSize$iv$iv);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                previousSize$iv$iv = previousSize$iv$iv2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            previousSize$iv$iv = previousSize$iv$iv2;
                            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            CanvasKt.Canvas(modifierM1079size3ABfNKs, (Function1) value$iv4, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$3
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
                    PullToRefreshKt.m3365CircularArrowProgressIndicatorRPmYEkk(function0, color, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final float CircularArrowProgressIndicator_RPmYEkk$lambda$4(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCircularIndicator-KzyDr3Q, reason: not valid java name */
    public static final void m3370drawCircularIndicatorKzyDr3Q(DrawScope $this$drawCircularIndicator_u2dKzyDr3Q, long color, float alpha, ArrowValues values, Rect arcBounds, float strokeWidth) {
        DrawScope.m5069drawArcyD3GUKo$default($this$drawCircularIndicator_u2dKzyDr3Q, color, values.getStartAngle(), values.getEndAngle() - values.getStartAngle(), false, arcBounds.m4328getTopLeftF1C5BW0(), arcBounds.m4326getSizeNHjbRc(), alpha, new Stroke($this$drawCircularIndicator_u2dKzyDr3Q.mo726toPx0680j_4(strokeWidth), 0.0f, StrokeCap.INSTANCE.m4887getButtKaPHkGw(), 0, null, 26, null), null, 0, 768, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrowValues ArrowValues(float progress) {
        float adjustedPercent = (Math.max(Math.min(1.0f, progress) - 0.4f, 0.0f) * 5) / 3;
        float overshootPercent = Math.abs(progress) - 1.0f;
        float linearTension = RangesKt.coerceIn(overshootPercent, 0.0f, 2.0f);
        float tensionPercent = linearTension - (((float) Math.pow(linearTension, 2)) / 4);
        float endTrim = 0.8f * adjustedPercent;
        float rotation = (((0.4f * adjustedPercent) - 0.25f) + tensionPercent) * 0.5f;
        float f = 360;
        float startAngle = rotation * f;
        float endAngle = (rotation + endTrim) * f;
        float scale = Math.min(1.0f, adjustedPercent);
        return new ArrowValues(rotation, startAngle, endAngle, scale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawArrow-uDrxG_w, reason: not valid java name */
    public static final void m3369drawArrowuDrxG_w(DrawScope $this$drawArrow_u2duDrxG_w, Path arrow, Rect bounds, long color, float alpha, ArrowValues values, float strokeWidth) throws Throwable {
        long previousSize$iv$iv;
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        arrow.reset();
        arrow.moveTo(0.0f, 0.0f);
        arrow.lineTo(($this$drawArrow_u2duDrxG_w.mo726toPx0680j_4(ArrowWidth) * values.getScale()) / 2, $this$drawArrow_u2duDrxG_w.mo726toPx0680j_4(ArrowHeight) * values.getScale());
        arrow.lineTo($this$drawArrow_u2duDrxG_w.mo726toPx0680j_4(ArrowWidth) * values.getScale(), 0.0f);
        float radius = Math.min(bounds.getWidth(), bounds.getHeight()) / 2.0f;
        float inset = ($this$drawArrow_u2duDrxG_w.mo726toPx0680j_4(ArrowWidth) * values.getScale()) / 2.0f;
        arrow.mo4429translatek4lQ0M(OffsetKt.Offset((Offset.m4293getXimpl(bounds.m4323getCenterF1C5BW0()) + radius) - inset, Offset.m4294getYimpl(bounds.m4323getCenterF1C5BW0()) - $this$drawArrow_u2duDrxG_w.mo726toPx0680j_4(strokeWidth)));
        float degrees$iv = values.getEndAngle() - $this$drawArrow_u2duDrxG_w.mo726toPx0680j_4(strokeWidth);
        long pivot$iv = $this$drawArrow_u2duDrxG_w.mo5089getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$drawArrow_u2duDrxG_w.getDrawContext();
        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo5011getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5017rotateUv8p0NA(degrees$iv, pivot$iv);
            try {
                DrawScope.m5080drawPathLG529CI$default($this$drawArrow_u2duDrxG_w, arrow, color, alpha, new Stroke($this$drawArrow_u2duDrxG_w.mo726toPx0680j_4(strokeWidth), 0.0f, 0, 0, null, 30, null), null, 0, 48, null);
                $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv2.mo5012setSizeuvyYCjk(previousSize$iv$iv2);
            } catch (Throwable th) {
                th = th;
                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                previousSize$iv$iv = previousSize$iv$iv2;
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5012setSizeuvyYCjk(previousSize$iv$iv);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            previousSize$iv$iv = previousSize$iv$iv2;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
        }
    }

    public static final float getSpinnerSize() {
        return SpinnerSize;
    }

    public static final float getSpinnerContainerSize() {
        return SpinnerContainerSize;
    }
}
