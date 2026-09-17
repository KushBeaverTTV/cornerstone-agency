package androidx.compose.animation;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnterExitTransition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001aQ\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122#\b\u0002\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00150\u0014H\u0007\u001aQ\u0010\u0019\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u001a2\b\b\u0002\u0010\u0011\u001a\u00020\u00122#\b\u0002\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00060\u0014H\u0007\u001aQ\u0010\u001d\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u001e2\b\b\u0002\u0010\u0011\u001a\u00020\u00122#\b\u0002\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00150\u0014H\u0007\u001a\"\u0010!\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\b\b\u0002\u0010\"\u001a\u00020\u0002H\u0007\u001a\"\u0010#\u001a\u00020$2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\b\b\u0002\u0010%\u001a\u00020\u0002H\u0007\u001a6\u0010&\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\b\b\u0002\u0010'\u001a\u00020\u00022\b\b\u0002\u0010(\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a6\u0010+\u001a\u00020$2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\b\b\u0002\u0010,\u001a\u00020\u00022\b\b\u0002\u0010(\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001aQ\u0010/\u001a\u00020$2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\b\b\u0002\u00100\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122#\b\u0002\u00101\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00150\u0014H\u0007\u001aQ\u00102\u001a\u00020$2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\b\b\u0002\u00100\u001a\u00020\u001a2\b\b\u0002\u0010\u0011\u001a\u00020\u00122#\b\u0002\u00103\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00060\u0014H\u0007\u001aQ\u00104\u001a\u00020$2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\b\b\u0002\u00100\u001a\u00020\u001e2\b\b\u0002\u0010\u0011\u001a\u00020\u00122#\b\u0002\u00105\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00150\u0014H\u0007\u001a;\u00106\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00040\u0014H\u0007\u001a=\u00108\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2#\b\u0002\u00109\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00150\u0014H\u0007\u001a=\u0010:\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2#\b\u0002\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00150\u0014H\u0007\u001a;\u0010<\u001a\u00020$2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2!\u0010=\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00040\u0014H\u0007\u001a=\u0010>\u001a\u00020$2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2#\b\u0002\u0010?\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00150\u0014H\u0007\u001a=\u0010@\u001a\u00020$2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2#\b\u0002\u0010A\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00150\u0014H\u0007\u001a/\u0010B\u001a\u00020C*\b\u0012\u0004\u0012\u00020E0D2\u0006\u0010F\u001a\u00020\f2\u0006\u0010G\u001a\u00020$2\u0006\u0010H\u001a\u00020IH\u0003¢\u0006\u0002\u0010J\u001a?\u0010K\u001a\u00020L*\b\u0012\u0004\u0012\u00020E0D2\u0006\u0010F\u001a\u00020\f2\u0006\u0010G\u001a\u00020$2\u000e\b\u0002\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00120N2\u0006\u0010H\u001a\u00020IH\u0001¢\u0006\u0002\u0010O\u001a,\u0010P\u001a\u0004\u0018\u0001HQ\"\b\b\u0000\u0010Q*\u00020R*\u00020\f2\f\u0010S\u001a\b\u0012\u0004\u0012\u0002HQ0TH\u0080\u0002¢\u0006\u0002\u0010U\u001a,\u0010P\u001a\u0004\u0018\u0001HQ\"\b\b\u0000\u0010Q*\u00020R*\u00020$2\f\u0010S\u001a\b\u0012\u0004\u0012\u0002HQ0TH\u0080\u0002¢\u0006\u0002\u0010V\u001a\f\u0010W\u001a\u00020\u001a*\u00020\u0010H\u0002\u001a\f\u0010W\u001a\u00020\u001a*\u00020\u001eH\u0002\u001a\u001f\u0010X\u001a\u00020\f*\b\u0012\u0004\u0012\u00020E0D2\u0006\u0010F\u001a\u00020\fH\u0001¢\u0006\u0002\u0010Y\u001a\u001f\u0010Z\u001a\u00020$*\b\u0012\u0004\u0012\u00020E0D2\u0006\u0010G\u001a\u00020$H\u0001¢\u0006\u0002\u0010[\u001a\u0015\u0010\\\u001a\u00020\f*\u00020\f2\u0006\u0010]\u001a\u00020RH\u0080\u0004\u001a\u0015\u0010\\\u001a\u00020$*\u00020$2\u0006\u0010]\u001a\u00020RH\u0080\u0004\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006^²\u0006\n\u0010_\u001a\u00020\fX\u008a\u008e\u0002²\u0006\n\u0010`\u001a\u00020$X\u008a\u008e\u0002"}, d2 = {"DefaultAlphaAndScaleSpring", "Landroidx/compose/animation/core/SpringSpec;", "", "DefaultOffsetAnimationSpec", "Landroidx/compose/ui/unit/IntOffset;", "DefaultSizeAnimationSpec", "Landroidx/compose/ui/unit/IntSize;", "TransformOriginVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/graphics/TransformOrigin;", "Landroidx/compose/animation/core/AnimationVector2D;", "expandHorizontally", "Landroidx/compose/animation/EnterTransition;", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "expandFrom", "Landroidx/compose/ui/Alignment$Horizontal;", "clip", "", "initialWidth", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "fullWidth", "expandIn", "Landroidx/compose/ui/Alignment;", "initialSize", "fullSize", "expandVertically", "Landroidx/compose/ui/Alignment$Vertical;", "initialHeight", "fullHeight", "fadeIn", "initialAlpha", "fadeOut", "Landroidx/compose/animation/ExitTransition;", "targetAlpha", "scaleIn", "initialScale", "transformOrigin", "scaleIn-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/EnterTransition;", "scaleOut", "targetScale", "scaleOut-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/ExitTransition;", "shrinkHorizontally", "shrinkTowards", "targetWidth", "shrinkOut", "targetSize", "shrinkVertically", "targetHeight", "slideIn", "initialOffset", "slideInHorizontally", "initialOffsetX", "slideInVertically", "initialOffsetY", "slideOut", "targetOffset", "slideOutHorizontally", "targetOffsetX", "slideOutVertically", "targetOffsetY", "createGraphicsLayerBlock", "Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "enter", "exit", "label", "", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "createModifier", "Landroidx/compose/ui/Modifier;", "isEnabled", "Lkotlin/Function0;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/Modifier;", "get", "T", "Landroidx/compose/animation/TransitionEffect;", "key", "Landroidx/compose/animation/TransitionEffectKey;", "(Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/TransitionEffectKey;)Landroidx/compose/animation/TransitionEffect;", "(Landroidx/compose/animation/ExitTransition;Landroidx/compose/animation/TransitionEffectKey;)Landroidx/compose/animation/TransitionEffect;", "toAlignment", "trackActiveEnter", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterTransition;", "trackActiveExit", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/ExitTransition;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/ExitTransition;", "withEffect", "effect", "animation_release", "activeEnter", "activeExit"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EnterExitTransitionKt {
    private static final TwoWayConverter<TransformOrigin, AnimationVector2D> TransformOriginVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1<TransformOrigin, AnimationVector2D>() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ AnimationVector2D invoke(TransformOrigin transformOrigin) {
            return m430invoke__ExYCQ(transformOrigin.getPackedValue());
        }

        /* JADX INFO: renamed from: invoke-__ExYCQ, reason: not valid java name */
        public final AnimationVector2D m430invoke__ExYCQ(long it) {
            return new AnimationVector2D(TransformOrigin.m4920getPivotFractionXimpl(it), TransformOrigin.m4921getPivotFractionYimpl(it));
        }
    }, new Function1<AnimationVector2D, TransformOrigin>() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$2
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ TransformOrigin invoke(AnimationVector2D animationVector2D) {
            return TransformOrigin.m4912boximpl(m431invokeLIALnN8(animationVector2D));
        }

        /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
        public final long m431invokeLIALnN8(AnimationVector2D it) {
            return TransformOriginKt.TransformOrigin(it.getV1(), it.getV2());
        }
    });
    private static final SpringSpec<Float> DefaultAlphaAndScaleSpring = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
    private static final SpringSpec<IntOffset> DefaultOffsetAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m7135boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
    private static final SpringSpec<IntSize> DefaultSizeAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m7178boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);

    public static final EnterTransition withEffect(EnterTransition $this$withEffect, TransitionEffect effect) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, null, false, MapsKt.mapOf(TuplesKt.to(effect.getKey$animation_release(), effect)), 31, null));
    }

    public static final ExitTransition withEffect(ExitTransition $this$withEffect, TransitionEffect effect) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, null, false, MapsKt.mapOf(TuplesKt.to(effect.getKey$animation_release(), effect)), 31, null));
    }

    public static /* synthetic */ EnterTransition fadeIn$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeIn(finiteAnimationSpec, f);
    }

    public static final EnterTransition fadeIn(FiniteAnimationSpec<Float> finiteAnimationSpec, float initialAlpha) {
        return new EnterTransitionImpl(new TransitionData(new Fade(initialAlpha, finiteAnimationSpec), null, null, null, false, null, 62, null));
    }

    public static /* synthetic */ ExitTransition fadeOut$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeOut(finiteAnimationSpec, f);
    }

    public static final ExitTransition fadeOut(FiniteAnimationSpec<Float> finiteAnimationSpec, float targetAlpha) {
        return new ExitTransitionImpl(new TransitionData(new Fade(targetAlpha, finiteAnimationSpec), null, null, null, false, null, 62, null));
    }

    public static /* synthetic */ EnterTransition slideIn$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m7135boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        return slideIn(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideIn(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, Function1<? super IntSize, IntOffset> function1) {
        return new EnterTransitionImpl(new TransitionData(null, new Slide(function1, finiteAnimationSpec), null, null, false, null, 61, null));
    }

    public static /* synthetic */ ExitTransition slideOut$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m7135boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        return slideOut(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOut(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, Function1<? super IntSize, IntOffset> function1) {
        return new ExitTransitionImpl(new TransitionData(null, new Slide(function1, finiteAnimationSpec), null, null, false, null, 61, null));
    }

    /* JADX INFO: renamed from: scaleIn-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ EnterTransition m427scaleInL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            j = TransformOrigin.INSTANCE.m4925getCenterSzJe1aQ();
        }
        return m426scaleInL8ZKhE(finiteAnimationSpec, f, j);
    }

    /* JADX INFO: renamed from: scaleIn-L8ZKh-E, reason: not valid java name */
    public static final EnterTransition m426scaleInL8ZKhE(FiniteAnimationSpec<Float> finiteAnimationSpec, float initialScale, long transformOrigin) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, new Scale(initialScale, transformOrigin, finiteAnimationSpec, null), false, null, 55, null));
    }

    /* JADX INFO: renamed from: scaleOut-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ ExitTransition m429scaleOutL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            j = TransformOrigin.INSTANCE.m4925getCenterSzJe1aQ();
        }
        return m428scaleOutL8ZKhE(finiteAnimationSpec, f, j);
    }

    /* JADX INFO: renamed from: scaleOut-L8ZKh-E, reason: not valid java name */
    public static final ExitTransition m428scaleOutL8ZKhE(FiniteAnimationSpec<Float> finiteAnimationSpec, float targetScale, long transformOrigin) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, new Scale(targetScale, transformOrigin, finiteAnimationSpec, null), false, null, 55, null));
    }

    public static /* synthetic */ EnterTransition expandIn$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m7178boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandIn.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                    return IntSize.m7178boximpl(m434invokemzRDjE0(intSize.getPackedValue()));
                }

                /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
                public final long m434invokemzRDjE0(long it) {
                    return IntSizeKt.IntSize(0, 0);
                }
            };
        }
        return expandIn(finiteAnimationSpec, alignment, z, function1);
    }

    public static final EnterTransition expandIn(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment expandFrom, boolean clip, Function1<? super IntSize, IntSize> function1) {
        return new EnterTransitionImpl(new TransitionData(null, null, new ChangeSize(expandFrom, function1, finiteAnimationSpec, clip), null, false, null, 59, null));
    }

    public static /* synthetic */ ExitTransition shrinkOut$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m7178boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkOut.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                    return IntSize.m7178boximpl(m437invokemzRDjE0(intSize.getPackedValue()));
                }

                /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
                public final long m437invokemzRDjE0(long it) {
                    return IntSizeKt.IntSize(0, 0);
                }
            };
        }
        return shrinkOut(finiteAnimationSpec, alignment, z, function1);
    }

    public static final ExitTransition shrinkOut(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment shrinkTowards, boolean clip, Function1<? super IntSize, IntSize> function1) {
        return new ExitTransitionImpl(new TransitionData(null, null, new ChangeSize(shrinkTowards, function1, finiteAnimationSpec, clip), null, false, null, 59, null));
    }

    public static /* synthetic */ EnterTransition expandHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m7178boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return expandHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final EnterTransition expandHorizontally(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Horizontal expandFrom, boolean clip, final Function1<? super Integer, Integer> function1) {
        return expandIn(finiteAnimationSpec, toAlignment(expandFrom), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m7178boximpl(m433invokemzRDjE0(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m433invokemzRDjE0(long it) {
                return IntSizeKt.IntSize(function1.invoke(Integer.valueOf(IntSize.m7186getWidthimpl(it))).intValue(), IntSize.m7185getHeightimpl(it));
            }
        });
    }

    public static /* synthetic */ EnterTransition expandVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m7178boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return expandVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final EnterTransition expandVertically(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Vertical expandFrom, boolean clip, final Function1<? super Integer, Integer> function1) {
        return expandIn(finiteAnimationSpec, toAlignment(expandFrom), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m7178boximpl(m435invokemzRDjE0(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m435invokemzRDjE0(long it) {
                return IntSizeKt.IntSize(IntSize.m7186getWidthimpl(it), function1.invoke(Integer.valueOf(IntSize.m7185getHeightimpl(it))).intValue());
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m7178boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return shrinkHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final ExitTransition shrinkHorizontally(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Horizontal shrinkTowards, boolean clip, final Function1<? super Integer, Integer> function1) {
        return shrinkOut(finiteAnimationSpec, toAlignment(shrinkTowards), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m7178boximpl(m436invokemzRDjE0(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m436invokemzRDjE0(long it) {
                return IntSizeKt.IntSize(function1.invoke(Integer.valueOf(IntSize.m7186getWidthimpl(it))).intValue(), IntSize.m7185getHeightimpl(it));
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m7178boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return shrinkVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final ExitTransition shrinkVertically(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Vertical shrinkTowards, boolean clip, final Function1<? super Integer, Integer> function1) {
        return shrinkOut(finiteAnimationSpec, toAlignment(shrinkTowards), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m7178boximpl(m438invokemzRDjE0(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m438invokemzRDjE0(long it) {
                return IntSizeKt.IntSize(IntSize.m7186getWidthimpl(it), function1.invoke(Integer.valueOf(IntSize.m7185getHeightimpl(it))).intValue());
            }
        });
    }

    public static /* synthetic */ EnterTransition slideInHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m7135boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInHorizontally.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideInHorizontally(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideInHorizontally(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideIn(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m7135boximpl(m439invokemHKZG7I(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m439invokemHKZG7I(long it) {
                return IntOffsetKt.IntOffset(function1.invoke(Integer.valueOf(IntSize.m7186getWidthimpl(it))).intValue(), 0);
            }
        });
    }

    public static /* synthetic */ EnterTransition slideInVertically$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m7135boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInVertically.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideInVertically(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideInVertically(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideIn(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m7135boximpl(m440invokemHKZG7I(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m440invokemHKZG7I(long it) {
                return IntOffsetKt.IntOffset(0, function1.invoke(Integer.valueOf(IntSize.m7185getHeightimpl(it))).intValue());
            }
        });
    }

    public static /* synthetic */ ExitTransition slideOutHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m7135boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutHorizontally.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideOutHorizontally(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOutHorizontally(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideOut(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m7135boximpl(m441invokemHKZG7I(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m441invokemHKZG7I(long it) {
                return IntOffsetKt.IntOffset(function1.invoke(Integer.valueOf(IntSize.m7186getWidthimpl(it))).intValue(), 0);
            }
        });
    }

    public static /* synthetic */ ExitTransition slideOutVertically$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m7135boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutVertically.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideOutVertically(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOutVertically(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideOut(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m7135boximpl(m442invokemHKZG7I(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m442invokemHKZG7I(long it) {
                return IntOffsetKt.IntOffset(0, function1.invoke(Integer.valueOf(IntSize.m7185getHeightimpl(it))).intValue());
            }
        });
    }

    private static final Alignment toAlignment(Alignment.Horizontal $this$toAlignment) {
        if (Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getStart())) {
            return Alignment.INSTANCE.getCenterStart();
        }
        return Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getEnd()) ? Alignment.INSTANCE.getCenterEnd() : Alignment.INSTANCE.getCenter();
    }

    private static final Alignment toAlignment(Alignment.Vertical $this$toAlignment) {
        if (Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getTop())) {
            return Alignment.INSTANCE.getTopCenter();
        }
        return Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getBottom()) ? Alignment.INSTANCE.getBottomCenter() : Alignment.INSTANCE.getCenter();
    }

    public static final <T extends TransitionEffect> T get(EnterTransition $this$get, TransitionEffectKey<T> transitionEffectKey) {
        TransitionEffect transitionEffect = $this$get.getData().getEffectsMap().get(transitionEffectKey);
        if (transitionEffect instanceof TransitionEffect) {
            return (T) transitionEffect;
        }
        return null;
    }

    public static final <T extends TransitionEffect> T get(ExitTransition $this$get, TransitionEffectKey<T> transitionEffectKey) {
        TransitionEffect transitionEffect = $this$get.getData().getEffectsMap().get(transitionEffectKey);
        if (transitionEffect instanceof TransitionEffect) {
            return (T) transitionEffect;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:64:0x0206  */
    public static final Modifier createModifier(Transition<EnterExitState> transition, EnterTransition enter, ExitTransition exit, Function0<Boolean> function0, String label, Composer $composer, int $changed, int i) {
        Transition.DeferredAnimation slideAnimation;
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation;
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation2;
        boolean z;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        ComposerKt.sourceInformationMarkerStart($composer, 28261782, "C(createModifier)870@38224L31,871@38277L28,896@39249L56,898@39349L58:EnterExitTransition.kt#xbi5r1");
        final Function0<Boolean> function1 = (i & 4) != 0 ? new Function0<Boolean>() { // from class: androidx.compose.animation.EnterExitTransitionKt.createModifier.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return true;
            }
        } : function0;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(28261782, $changed, -1, "androidx.compose.animation.createModifier (EnterExitTransition.kt:869)");
        }
        EnterTransition activeEnter = trackActiveEnter(transition, enter, $composer, ($changed & 14) | ($changed & 112));
        ExitTransition activeExit = trackActiveExit(transition, exit, $composer, ($changed & 14) | (($changed >> 3) & 112));
        boolean z2 = true;
        boolean shouldAnimateSlide = (activeEnter.getData().getSlide() == null && activeExit.getData().getSlide() == null) ? false : true;
        boolean shouldAnimateSizeChange = (activeEnter.getData().getChangeSize() == null && activeExit.getData().getChangeSize() == null) ? false : true;
        if (shouldAnimateSlide) {
            $composer.startReplaceGroup(-821375963);
            ComposerKt.sourceInformation($composer, "878@38627L27,878@38576L79");
            TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, -165041447, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv4 = label + " slide";
                $composer.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation3 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) value$iv4, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
            slideAnimation = deferredAnimationCreateDeferredAnimation3;
        } else {
            $composer.startReplaceGroup(-821278096);
            $composer.endReplaceGroup();
            slideAnimation = null;
        }
        if (shouldAnimateSizeChange) {
            $composer.startReplaceGroup(-821202177);
            ComposerKt.sourceInformation($composer, "883@38800L35,883@38751L85");
            TwoWayConverter<IntSize, AnimationVector2D> vectorConverter2 = VectorConvertersKt.getVectorConverter(IntSize.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, -165035903, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = label + " shrink/expand";
                $composer.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter2, (String) value$iv3, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-821099041);
            $composer.endReplaceGroup();
            deferredAnimationCreateDeferredAnimation = null;
        }
        Transition.DeferredAnimation sizeAnimation = deferredAnimationCreateDeferredAnimation;
        if (shouldAnimateSizeChange) {
            $composer.startReplaceGroup(-821034002);
            ComposerKt.sourceInformation($composer, "889@38995L48,887@38919L134");
            TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter3 = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, -165029650, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv3 = $composer.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = label + " InterruptionHandlingOffset";
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            deferredAnimationCreateDeferredAnimation2 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter3, (String) value$iv2, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-820883777);
            $composer.endReplaceGroup();
            deferredAnimationCreateDeferredAnimation2 = null;
        }
        Transition.DeferredAnimation offsetAnimation = deferredAnimationCreateDeferredAnimation2;
        ChangeSize changeSize = activeEnter.getData().getChangeSize();
        if ((changeSize == null || changeSize.getClip()) ? false : true) {
            z = true;
        } else {
            ChangeSize changeSize2 = activeExit.getData().getChangeSize();
            if (((changeSize2 == null || changeSize2.getClip()) ? false : true) || !shouldAnimateSizeChange) {
                z = true;
            } else {
                z = false;
            }
        }
        final boolean disableClip = z;
        GraphicsLayerBlockForEnterExit graphicsLayerBlock = createGraphicsLayerBlock(transition, activeEnter, activeExit, label, $composer, ($changed & 14) | (($changed >> 3) & 7168));
        Modifier.Companion companion = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, -165018312, "CC(remember):EnterExitTransition.kt#9igjgp");
        boolean zChanged = $composer.changed(disableClip);
        if (((($changed & 7168) ^ 3072) <= 2048 || !$composer.changed(function1)) && ($changed & 3072) != 2048) {
            z2 = false;
        }
        boolean invalid$iv = zChanged | z2;
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv || it$iv4 == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$2$1
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
                    $this$graphicsLayer.setClip(!disableClip && function1.invoke().booleanValue());
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv4;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierThen = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) value$iv).then(new EnterExitTransitionElement(transition, sizeAnimation, offsetAnimation, slideAnimation, activeEnter, activeExit, function1, graphicsLayerBlock));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return modifierThen;
    }

    public static final EnterTransition trackActiveEnter(Transition<EnterExitState> transition, EnterTransition enter, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 21614502, "C(trackActiveEnter)915@40149L40:EnterExitTransition.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(21614502, $changed, -1, "androidx.compose.animation.trackActiveEnter (EnterExitTransition.kt:910)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1583278438, "CC(remember):EnterExitTransition.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(enter, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableState activeEnter$delegate = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                activeEnter$delegate.setValue(enter);
            } else {
                activeEnter$delegate.setValue(EnterTransition.INSTANCE.getNone());
            }
        } else if (transition.getTargetState() == EnterExitState.Visible) {
            activeEnter$delegate.setValue(trackActiveEnter$lambda$5(activeEnter$delegate).plus(enter));
        }
        EnterTransition enterTransitionTrackActiveEnter$lambda$5 = trackActiveEnter$lambda$5(activeEnter$delegate);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return enterTransitionTrackActiveEnter$lambda$5;
    }

    private static final EnterTransition trackActiveEnter$lambda$5(MutableState<EnterTransition> mutableState) {
        MutableState<EnterTransition> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final ExitTransition trackActiveExit(Transition<EnterExitState> transition, ExitTransition exit, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1363864804, "C(trackActiveExit)935@41130L39:EnterExitTransition.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1363864804, $changed, -1, "androidx.compose.animation.trackActiveExit (EnterExitTransition.kt:930)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1483140299, "CC(remember):EnterExitTransition.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(exit, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableState activeExit$delegate = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                activeExit$delegate.setValue(exit);
            } else {
                activeExit$delegate.setValue(ExitTransition.INSTANCE.getNone());
            }
        } else if (transition.getTargetState() != EnterExitState.Visible) {
            activeExit$delegate.setValue(trackActiveExit$lambda$8(activeExit$delegate).plus(exit));
        }
        ExitTransition exitTransitionTrackActiveExit$lambda$8 = trackActiveExit$lambda$8(activeExit$delegate);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return exitTransitionTrackActiveExit$lambda$8;
    }

    private static final ExitTransition trackActiveExit$lambda$8(MutableState<ExitTransition> mutableState) {
        MutableState<ExitTransition> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0171  */
    /* JADX WARN: Code duplicated, block: B:57:0x018c  */
    /* JADX WARN: Code duplicated, block: B:67:0x01ab  */
    private static final GraphicsLayerBlockForEnterExit createGraphicsLayerBlock(final Transition<EnterExitState> transition, final EnterTransition enter, final ExitTransition exit, String label, Composer $composer, int $changed) {
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation;
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation2;
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation3;
        boolean z;
        boolean z2;
        boolean z3;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        ComposerKt.sourceInformationMarkerStart($composer, 642253525, "C(createGraphicsLayerBlock)985@42946L2853:EnterExitTransition.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(642253525, $changed, -1, "androidx.compose.animation.createGraphicsLayerBlock (EnterExitTransition.kt:958)");
        }
        boolean shouldAnimateAlpha = (enter.getData().getFade() == null && exit.getData().getFade() == null) ? false : true;
        boolean shouldAnimateScale = (enter.getData().getScale() == null && exit.getData().getScale() == null) ? false : true;
        if (shouldAnimateAlpha) {
            $composer.startReplaceGroup(-675389204);
            ComposerKt.sourceInformation($composer, "968@42460L27,967@42377L120");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, -1545804535, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv3 = label + " alpha";
                $composer.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) value$iv3, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-675252433);
            $composer.endReplaceGroup();
            deferredAnimationCreateDeferredAnimation = null;
        }
        final Transition.DeferredAnimation alphaAnimation = deferredAnimationCreateDeferredAnimation;
        if (shouldAnimateScale) {
            $composer.startReplaceGroup(-675193780);
            ComposerKt.sourceInformation($composer, "974@42657L27,973@42574L120");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, -1545798231, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = label + " scale";
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            deferredAnimationCreateDeferredAnimation2 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter2, (String) value$iv2, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-675057009);
            $composer.endReplaceGroup();
            deferredAnimationCreateDeferredAnimation2 = null;
        }
        final Transition.DeferredAnimation scaleAnimation = deferredAnimationCreateDeferredAnimation2;
        if (shouldAnimateScale) {
            $composer.startReplaceGroup(-674987940);
            ComposerKt.sourceInformation($composer, "979@42781L136");
            deferredAnimationCreateDeferredAnimation3 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, TransformOriginVectorConverter, "TransformOriginInterruptionHandling", $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-674835793);
            $composer.endReplaceGroup();
            deferredAnimationCreateDeferredAnimation3 = null;
        }
        final Transition.DeferredAnimation transformOriginAnimation = deferredAnimationCreateDeferredAnimation3;
        ComposerKt.sourceInformationMarkerStart($composer, -1545786157, "CC(remember):EnterExitTransition.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance(alphaAnimation);
        if ((($changed & 112) ^ 48) > 32 && $composer.changed(enter)) {
            z = true;
        } else if (($changed & 48) == 32) {
            z = true;
        } else {
            z = false;
        }
        boolean z4 = zChangedInstance | z;
        if ((($changed & 896) ^ 384) > 256 && $composer.changed(exit)) {
            z2 = true;
        } else if (($changed & 384) == 256) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean zChangedInstance2 = z4 | z2 | $composer.changedInstance(scaleAnimation);
        if ((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) {
            z3 = true;
        } else if (($changed & 6) == 4) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean invalid$iv = zChangedInstance2 | z3 | $composer.changedInstance(transformOriginAnimation);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                public final Function1 init() {
                    return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$13$lambda$12(alphaAnimation, scaleAnimation, transition, enter, exit, transformOriginAnimation);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv3;
        }
        GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit = (GraphicsLayerBlockForEnterExit) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return graphicsLayerBlockForEnterExit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1 createGraphicsLayerBlock$lambda$13$lambda$12(Transition.DeferredAnimation $alphaAnimation, Transition.DeferredAnimation $scaleAnimation, Transition $this_createGraphicsLayerBlock, final EnterTransition $enter, final ExitTransition $exit, Transition.DeferredAnimation $transformOriginAnimation) {
        final TransformOrigin transformOriginWhenVisible;
        final State alpha = $alphaAnimation != null ? $alphaAnimation.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> segment) {
                FiniteAnimationSpec<Float> animationSpec;
                FiniteAnimationSpec<Float> animationSpec2;
                if (segment.isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
                    Fade fade = $enter.getData().getFade();
                    if (fade == null || (animationSpec2 = fade.getAnimationSpec()) == null) {
                        return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                    }
                    return animationSpec2;
                }
                if (!segment.isTransitioningTo(EnterExitState.Visible, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                Fade fade2 = $exit.getData().getFade();
                if (fade2 == null || (animationSpec = fade2.getAnimationSpec()) == null) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                return animationSpec;
            }
        }, new Function1<EnterExitState, Float>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$2

            /* JADX INFO: compiled from: EnterExitTransition.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Float invoke(EnterExitState it) {
                float alpha2 = 1.0f;
                switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                    case 1:
                        break;
                    case 2:
                        Fade fade = $enter.getData().getFade();
                        if (fade != null) {
                            alpha2 = fade.getAlpha();
                        }
                        break;
                    case 3:
                        Fade fade2 = $exit.getData().getFade();
                        if (fade2 != null) {
                            alpha2 = fade2.getAlpha();
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                return Float.valueOf(alpha2);
            }
        }) : null;
        final State scale = $scaleAnimation != null ? $scaleAnimation.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> segment) {
                FiniteAnimationSpec<Float> animationSpec;
                FiniteAnimationSpec<Float> animationSpec2;
                if (segment.isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
                    Scale scale2 = $enter.getData().getScale();
                    if (scale2 == null || (animationSpec2 = scale2.getAnimationSpec()) == null) {
                        return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                    }
                    return animationSpec2;
                }
                if (!segment.isTransitioningTo(EnterExitState.Visible, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                Scale scale3 = $exit.getData().getScale();
                if (scale3 == null || (animationSpec = scale3.getAnimationSpec()) == null) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                return animationSpec;
            }
        }, new Function1<EnterExitState, Float>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$2

            /* JADX INFO: compiled from: EnterExitTransition.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Float invoke(EnterExitState it) {
                float scale2 = 1.0f;
                switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                    case 1:
                        break;
                    case 2:
                        Scale scale3 = $enter.getData().getScale();
                        if (scale3 != null) {
                            scale2 = scale3.getScale();
                        }
                        break;
                    case 3:
                        Scale scale4 = $exit.getData().getScale();
                        if (scale4 != null) {
                            scale2 = scale4.getScale();
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                return Float.valueOf(scale2);
            }
        }) : null;
        if ($this_createGraphicsLayerBlock.getCurrentState() == EnterExitState.PreEnter) {
            Scale scale2 = $enter.getData().getScale();
            transformOriginWhenVisible = (scale2 == null && (scale2 = $exit.getData().getScale()) == null) ? null : TransformOrigin.m4912boximpl(scale2.m455getTransformOriginSzJe1aQ());
        } else {
            Scale scale3 = $exit.getData().getScale();
            transformOriginWhenVisible = (scale3 == null && (scale3 = $enter.getData().getScale()) == null) ? null : TransformOrigin.m4912boximpl(scale3.m455getTransformOriginSzJe1aQ());
        }
        final State transformOrigin = $transformOriginAnimation != null ? $transformOriginAnimation.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<TransformOrigin>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$1
            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec<TransformOrigin> invoke(Transition.Segment<EnterExitState> segment) {
                return AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
            }
        }, new Function1<EnterExitState, TransformOrigin>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$2

            /* JADX INFO: compiled from: EnterExitTransition.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ TransformOrigin invoke(EnterExitState enterExitState) {
                return TransformOrigin.m4912boximpl(m432invokeLIALnN8(enterExitState));
            }

            /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
            public final long m432invokeLIALnN8(EnterExitState it) {
                TransformOrigin transformOriginM4912boximpl = null;
                switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                    case 1:
                        transformOriginM4912boximpl = transformOriginWhenVisible;
                        break;
                    case 2:
                        Scale scale4 = $enter.getData().getScale();
                        if (scale4 != null || (scale4 = $exit.getData().getScale()) != null) {
                            transformOriginM4912boximpl = TransformOrigin.m4912boximpl(scale4.m455getTransformOriginSzJe1aQ());
                        }
                        break;
                    case 3:
                        Scale scale5 = $exit.getData().getScale();
                        if (scale5 != null || (scale5 = $enter.getData().getScale()) != null) {
                            transformOriginM4912boximpl = TransformOrigin.m4912boximpl(scale5.m455getTransformOriginSzJe1aQ());
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                return transformOriginM4912boximpl != null ? transformOriginM4912boximpl.getPackedValue() : TransformOrigin.INSTANCE.m4925getCenterSzJe1aQ();
            }
        }) : null;
        return new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$block$1
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
            public final void invoke2(GraphicsLayerScope $this$null) {
                State<Float> state = alpha;
                $this$null.setAlpha(state != null ? state.getValue().floatValue() : 1.0f);
                State<Float> state2 = scale;
                $this$null.setScaleX(state2 != null ? state2.getValue().floatValue() : 1.0f);
                State<Float> state3 = scale;
                $this$null.setScaleY(state3 != null ? state3.getValue().floatValue() : 1.0f);
                State<TransformOrigin> state4 = transformOrigin;
                $this$null.mo4727setTransformOrigin__ExYCQ(state4 != null ? state4.getValue().getPackedValue() : TransformOrigin.INSTANCE.m4925getCenterSzJe1aQ());
            }
        };
    }
}
