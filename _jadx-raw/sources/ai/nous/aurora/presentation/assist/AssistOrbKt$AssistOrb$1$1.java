package ai.nous.aurora.presentation.assist;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.SpringSpec;
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
@DebugMetadata(c = "ai.nous.aurora.presentation.assist.AssistOrbKt$AssistOrb$1$1", f = "AssistOrb.kt", i = {}, l = {212, 213}, m = "invokeSuspend", n = {}, s = {})
final class AssistOrbKt$AssistOrb$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $settle;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssistOrbKt$AssistOrb$1$1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AssistOrbKt$AssistOrb$1$1> continuation) {
        super(2, continuation);
        this.$settle = animatable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssistOrbKt$AssistOrb$1$1(this.$settle, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AssistOrbKt$AssistOrb$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0058 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Animatable<Float, AnimationVector1D> animatable;
        Float fBoxFloat;
        SpringSpec springSpecSpring$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (this.$settle.snapTo(Boxing.boxFloat(0.94f), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                animatable = this.$settle;
                fBoxFloat = Boxing.boxFloat(1.0f);
                springSpecSpring$default = AnimationSpecKt.spring$default(0.72f, 280.0f, null, 4, null);
                this.label = 2;
                if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring$default, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                animatable = this.$settle;
                fBoxFloat = Boxing.boxFloat(1.0f);
                springSpecSpring$default = AnimationSpecKt.spring$default(0.72f, 280.0f, null, 4, null);
                this.label = 2;
                if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring$default, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
