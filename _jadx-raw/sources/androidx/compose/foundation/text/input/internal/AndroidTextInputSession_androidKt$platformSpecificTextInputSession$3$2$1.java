package androidx.compose.foundation.text.input.internal;

import androidx.compose.runtime.MonotonicFrameClockKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1", f = "AndroidTextInputSession.android.kt", i = {}, l = {115, 116}, m = "invokeSuspend", n = {}, s = {})
final class AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ComposeInputMethodManager $composeImm;
    final /* synthetic */ MutableSharedFlow<Unit> $it;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1(MutableSharedFlow<Unit> mutableSharedFlow, ComposeInputMethodManager composeInputMethodManager, Continuation<? super AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1> continuation) {
        super(2, continuation);
        this.$it = mutableSharedFlow;
        this.$composeImm = composeInputMethodManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1(this.$it, this.$composeImm, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0048 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:14:0x0049  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1 androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1;
        MutableSharedFlow<Unit> mutableSharedFlow;
        final ComposeInputMethodManager composeInputMethodManager;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1 = this;
                androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.label = 1;
                if (MonotonicFrameClockKt.withFrameMillis(new Function1<Long, Unit>() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                        invoke(l.longValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(long it) {
                    }
                }, androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableSharedFlow = androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.$it;
                composeInputMethodManager = androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.$composeImm;
                androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.label = 2;
                if (mutableSharedFlow.collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((Unit) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(Unit it, Continuation<? super Unit> continuation) {
                        composeInputMethodManager.startStylusHandwriting();
                        return Unit.INSTANCE;
                    }
                }, androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw new KotlinNothingValueException();
            case 1:
                androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1 = this;
                ResultKt.throwOnFailure($result);
                mutableSharedFlow = androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.$it;
                composeInputMethodManager = androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.$composeImm;
                androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.label = 2;
                if (mutableSharedFlow.collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((Unit) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(Unit it, Continuation<? super Unit> continuation) {
                        composeInputMethodManager.startStylusHandwriting();
                        return Unit.INSTANCE;
                    }
                }, androidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw new KotlinNothingValueException();
            case 2:
                ResultKt.throwOnFailure($result);
                throw new KotlinNothingValueException();
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
