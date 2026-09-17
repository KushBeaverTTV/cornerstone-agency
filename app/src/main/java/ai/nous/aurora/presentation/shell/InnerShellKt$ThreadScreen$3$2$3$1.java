package ai.nous.aurora.presentation.shell;

import ai.nous.aurora.MainViewModel;
import ai.nous.aurora.domain.model.ThreadAutoScrollLogic;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: InnerShell.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.shell.InnerShellKt$ThreadScreen$3$2$3$1", f = "InnerShell.kt", i = {0}, l = {1184}, m = "invokeSuspend", n = {"count"}, s = {"I$0"})
final class InnerShellKt$ThreadScreen$3$2$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ MutableState<Boolean> $followBottom$delegate;
    final /* synthetic */ LazyListState $listState;
    final /* synthetic */ MutableIntState $previousCount$delegate;
    final /* synthetic */ MainViewModel.UiState $state;
    int I$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    InnerShellKt$ThreadScreen$3$2$3$1(MainViewModel.UiState uiState, LazyListState lazyListState, MutableState<Boolean> mutableState, MutableIntState mutableIntState, Continuation continuation) {
        super(2, continuation);
        this.$state = uiState;
        this.$listState = lazyListState;
        this.$followBottom$delegate = mutableState;
        this.$previousCount$delegate = mutableIntState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        return new InnerShellKt$ThreadScreen$3$2$3$1(this.$state, this.$listState, this.$followBottom$delegate, this.$previousCount$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((InnerShellKt$ThreadScreen$3$2$3$1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int count;
        int count2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                count = this.$state.getMessages().size();
                if (ThreadAutoScrollLogic.INSTANCE.shouldScrollToLatest(InnerShellKt.AnonymousClass3.invoke$lambda$34$lambda$7(this.$followBottom$delegate), count, InnerShellKt.AnonymousClass3.invoke$lambda$34$lambda$10(this.$previousCount$delegate), this.$state.getSending())) {
                    int target = ThreadAutoScrollLogic.INSTANCE.targetIndex(count, this.$state.getSending());
                    this.I$0 = count;
                    this.label = 1;
                    if (ai.nous.aurora.compat.Compat.d_LazyListState_scrollToItem_6(this.$listState, RangesKt.coerceAtLeast(target, 0), 0, this, 2, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    count2 = count;
                    count = count2;
                }
                this.$previousCount$delegate.setIntValue(count);
                return Unit.INSTANCE;
            case 1:
                count2 = this.I$0;
                ResultKt.throwOnFailure($result);
                count = count2;
                this.$previousCount$delegate.setIntValue(count);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
