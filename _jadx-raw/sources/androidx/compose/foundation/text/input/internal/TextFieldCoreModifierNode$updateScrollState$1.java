package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextFieldCoreModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$updateScrollState$1", f = "TextFieldCoreModifier.kt", i = {}, l = {448, 451}, m = "invokeSuspend", n = {}, s = {})
final class TextFieldCoreModifierNode$updateScrollState$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ float $offsetDifference;
    final /* synthetic */ Rect $rawCursorRect;
    int label;
    final /* synthetic */ TextFieldCoreModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldCoreModifierNode$updateScrollState$1(TextFieldCoreModifierNode textFieldCoreModifierNode, float f, Rect rect, Continuation<? super TextFieldCoreModifierNode$updateScrollState$1> continuation) {
        super(2, continuation);
        this.this$0 = textFieldCoreModifierNode;
        this.$offsetDifference = f;
        this.$rawCursorRect = rect;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextFieldCoreModifierNode$updateScrollState$1(this.this$0, this.$offsetDifference, this.$rawCursorRect, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextFieldCoreModifierNode$updateScrollState$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0053 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:14:0x0054  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        TextFieldCoreModifierNode$updateScrollState$1 textFieldCoreModifierNode$updateScrollState$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                textFieldCoreModifierNode$updateScrollState$1 = this;
                textFieldCoreModifierNode$updateScrollState$1.label = 1;
                if (ScrollExtensionsKt.scrollBy(textFieldCoreModifierNode$updateScrollState$1.this$0.scrollState, TextFieldCoreModifierKt.roundToNext(textFieldCoreModifierNode$updateScrollState$1.$offsetDifference), textFieldCoreModifierNode$updateScrollState$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                textFieldCoreModifierNode$updateScrollState$1.label = 2;
                if (textFieldCoreModifierNode$updateScrollState$1.this$0.textLayoutState.getBringIntoViewRequester().bringIntoView(textFieldCoreModifierNode$updateScrollState$1.$rawCursorRect, textFieldCoreModifierNode$updateScrollState$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                textFieldCoreModifierNode$updateScrollState$1 = this;
                ResultKt.throwOnFailure($result);
                textFieldCoreModifierNode$updateScrollState$1.label = 2;
                if (textFieldCoreModifierNode$updateScrollState$1.this$0.textLayoutState.getBringIntoViewRequester().bringIntoView(textFieldCoreModifierNode$updateScrollState$1.$rawCursorRect, textFieldCoreModifierNode$updateScrollState$1) == coroutine_suspended) {
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
