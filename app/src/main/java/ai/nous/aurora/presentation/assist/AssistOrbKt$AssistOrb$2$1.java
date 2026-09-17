package ai.nous.aurora.presentation.assist;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AssistOrb.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.assist.AssistOrbKt$AssistOrb$2$1", f = "AssistOrb.kt", i = {}, l = {223, 224, 226}, m = "invokeSuspend", n = {}, s = {})
final class AssistOrbKt$AssistOrb$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $doneBloomAnim;
    final /* synthetic */ AssistOrbPhase $phase;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssistOrbKt$AssistOrb$2$1(AssistOrbPhase assistOrbPhase, Animatable<Float, AnimationVector1D> animatable, Continuation continuation) {
        super(2, continuation);
        this.$phase = assistOrbPhase;
        this.$doneBloomAnim = animatable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        return new AssistOrbKt$AssistOrb$2$1(this.$phase, this.$doneBloomAnim, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((AssistOrbKt$AssistOrb$2$1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0062 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Animatable<Float, AnimationVector1D> animatable;
        Float fBoxFloat;
        TweenSpec tweenSpecTween$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$phase != AssistOrbPhase.Done) {
                    this.label = 3;
                    if (this.$doneBloomAnim.snapTo(Boxing.boxFloat(0.0f), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Unit unit = Unit.INSTANCE;
                    return Unit.INSTANCE;
                }
                this.label = 1;
                if (this.$doneBloomAnim.snapTo(Boxing.boxFloat(1.0f), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                animatable = this.$doneBloomAnim;
                fBoxFloat = Boxing.boxFloat(0.0f);
                tweenSpecTween$default = ai.nous.aurora.compat.Compat.d_AnimationSpecKt_tween_5(1600, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
                this.label = 2;
                if (animatable.animateTo(fBoxFloat, tweenSpecTween$default, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                animatable = this.$doneBloomAnim;
                fBoxFloat = Boxing.boxFloat(0.0f);
                tweenSpecTween$default = ai.nous.aurora.compat.Compat.d_AnimationSpecKt_tween_5(1600, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
                this.label = 2;
                if (animatable.animateTo(fBoxFloat, tweenSpecTween$default, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 3:
                ResultKt.throwOnFailure($result);
                Unit unit2 = Unit.INSTANCE;
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
