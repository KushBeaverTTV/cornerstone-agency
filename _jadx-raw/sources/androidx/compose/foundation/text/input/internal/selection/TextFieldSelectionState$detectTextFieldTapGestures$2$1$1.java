package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.core.app.FrameMetricsAggregator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextFieldSelectionState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1", f = "TextFieldSelectionState.kt", i = {}, l = {504, FrameMetricsAggregator.EVERY_DURATION}, m = "invokeSuspend", n = {}, s = {})
final class TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PressGestureScope $$this$detectTapAndPress;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ long $offset;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TextFieldSelectionState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionState$detectTextFieldTapGestures$2$1$1(PressGestureScope pressGestureScope, TextFieldSelectionState textFieldSelectionState, long j, MutableInteractionSource mutableInteractionSource, Continuation<? super TextFieldSelectionState$detectTextFieldTapGestures$2$1$1> continuation) {
        super(2, continuation);
        this.$$this$detectTapAndPress = pressGestureScope;
        this.this$0 = textFieldSelectionState;
        this.$offset = j;
        this.$interactionSource = mutableInteractionSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = new TextFieldSelectionState$detectTextFieldTapGestures$2$1$1(this.$$this$detectTapAndPress, this.this$0, this.$offset, this.$interactionSource, continuation);
        textFieldSelectionState$detectTextFieldTapGestures$2$1$1.L$0 = obj;
        return textFieldSelectionState$detectTextFieldTapGestures$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextFieldSelectionState$detectTextFieldTapGestures$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1", f = "TextFieldSelectionState.kt", i = {1}, l = {496, 501}, m = "invokeSuspend", n = {"press"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ long $offset;
        Object L$0;
        int label;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TextFieldSelectionState textFieldSelectionState, long j, MutableInteractionSource mutableInteractionSource, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = textFieldSelectionState;
            this.$offset = j;
            this.$interactionSource = mutableInteractionSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$offset, this.$interactionSource, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x006e A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x006f  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass1 anonymousClass1;
            TextFieldSelectionState textFieldSelectionState;
            PressInteraction.Press press;
            AnonymousClass1 anonymousClass2;
            PressInteraction.Press press2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    anonymousClass1 = this;
                    PressInteraction.Press oldValue = anonymousClass1.this$0.pressInteraction;
                    if (oldValue != null) {
                        MutableInteractionSource mutableInteractionSource = anonymousClass1.$interactionSource;
                        TextFieldSelectionState textFieldSelectionState2 = anonymousClass1.this$0;
                        PressInteraction.Cancel interaction = new PressInteraction.Cancel(oldValue);
                        anonymousClass1.L$0 = textFieldSelectionState2;
                        anonymousClass1.label = 1;
                        if (mutableInteractionSource.emit(interaction, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        textFieldSelectionState = textFieldSelectionState2;
                        textFieldSelectionState.pressInteraction = null;
                    }
                    press = new PressInteraction.Press(anonymousClass1.$offset, null);
                    anonymousClass1.L$0 = press;
                    anonymousClass1.label = 2;
                    if (anonymousClass1.$interactionSource.emit(press, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass2 = anonymousClass1;
                    press2 = press;
                    anonymousClass2.this$0.pressInteraction = press2;
                    return Unit.INSTANCE;
                case 1:
                    anonymousClass1 = this;
                    textFieldSelectionState = (TextFieldSelectionState) anonymousClass1.L$0;
                    ResultKt.throwOnFailure($result);
                    textFieldSelectionState.pressInteraction = null;
                    press = new PressInteraction.Press(anonymousClass1.$offset, null);
                    anonymousClass1.L$0 = press;
                    anonymousClass1.label = 2;
                    if (anonymousClass1.$interactionSource.emit(press, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass2 = anonymousClass1;
                    press2 = press;
                    anonymousClass2.this$0.pressInteraction = press2;
                    return Unit.INSTANCE;
                case 2:
                    anonymousClass2 = this;
                    press2 = (PressInteraction.Press) anonymousClass2.L$0;
                    ResultKt.throwOnFailure($result);
                    anonymousClass2.this$0.pressInteraction = press2;
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0060  */
    /* JADX WARN: Code duplicated, block: B:16:0x0066  */
    /* JADX WARN: Code duplicated, block: B:17:0x006e  */
    /* JADX WARN: Code duplicated, block: B:20:0x0082 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:21:0x0083  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 textFieldSelectionState$detectTextFieldTapGestures$2$1$1;
        boolean success;
        PressInteraction.Press pressInteraction;
        MutableInteractionSource mutableInteractionSource;
        PressInteraction endInteraction;
        TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 textFieldSelectionState$detectTextFieldTapGestures$2$1$2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new AnonymousClass1(this.this$0, this.$offset, this.$interactionSource, null), 3, null);
                this.label = 1;
                Object objTryAwaitRelease = this.$$this$detectTapAndPress.tryAwaitRelease(this);
                if (objTryAwaitRelease == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objTryAwaitRelease;
                textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = this;
                success = ((Boolean) $result).booleanValue();
                pressInteraction = textFieldSelectionState$detectTextFieldTapGestures$2$1$1.this$0.pressInteraction;
                if (pressInteraction != null) {
                    mutableInteractionSource = textFieldSelectionState$detectTextFieldTapGestures$2$1$1.$interactionSource;
                    if (success) {
                        endInteraction = new PressInteraction.Release(pressInteraction);
                    } else {
                        endInteraction = new PressInteraction.Cancel(pressInteraction);
                    }
                    textFieldSelectionState$detectTextFieldTapGestures$2$1$1.label = 2;
                    if (mutableInteractionSource.emit(endInteraction, textFieldSelectionState$detectTextFieldTapGestures$2$1$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = $result;
                    textFieldSelectionState$detectTextFieldTapGestures$2$1$2 = textFieldSelectionState$detectTextFieldTapGestures$2$1$1;
                    textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = textFieldSelectionState$detectTextFieldTapGestures$2$1$2;
                }
                textFieldSelectionState$detectTextFieldTapGestures$2$1$1.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = this;
                success = ((Boolean) $result).booleanValue();
                pressInteraction = textFieldSelectionState$detectTextFieldTapGestures$2$1$1.this$0.pressInteraction;
                if (pressInteraction != null) {
                    mutableInteractionSource = textFieldSelectionState$detectTextFieldTapGestures$2$1$1.$interactionSource;
                    if (success) {
                        endInteraction = new PressInteraction.Release(pressInteraction);
                    } else {
                        endInteraction = new PressInteraction.Cancel(pressInteraction);
                    }
                    textFieldSelectionState$detectTextFieldTapGestures$2$1$1.label = 2;
                    if (mutableInteractionSource.emit(endInteraction, textFieldSelectionState$detectTextFieldTapGestures$2$1$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = $result;
                    textFieldSelectionState$detectTextFieldTapGestures$2$1$2 = textFieldSelectionState$detectTextFieldTapGestures$2$1$1;
                    textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = textFieldSelectionState$detectTextFieldTapGestures$2$1$2;
                }
                textFieldSelectionState$detectTextFieldTapGestures$2$1$1.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            case 2:
                textFieldSelectionState$detectTextFieldTapGestures$2$1$2 = this;
                ResultKt.throwOnFailure($result);
                textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = textFieldSelectionState$detectTextFieldTapGestures$2$1$2;
                textFieldSelectionState$detectTextFieldTapGestures$2$1$1.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
