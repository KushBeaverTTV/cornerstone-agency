package ai.nous.aurora.presentation.settings;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$3$1", f = "MoaEditorScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class MoaEditorScreenKt$MoaEditorScreen$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ MutableState<Boolean> $dirty$delegate;
    final /* synthetic */ String $error;
    final /* synthetic */ boolean $lastSaved;
    final /* synthetic */ Function1<? super Boolean, Unit> $onDirtyChange;
    final /* synthetic */ MutableState<Boolean> $saveInFlight$delegate;
    final /* synthetic */ boolean $saving;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    MoaEditorScreenKt$MoaEditorScreen$3$1(boolean z, boolean z2, String str, MutableState<Boolean> mutableState, Function1<? super Boolean, Unit> function1, MutableState<Boolean> mutableState2, Continuation continuation) {
        super(2, continuation);
        this.$saving = z;
        this.$lastSaved = z2;
        this.$error = str;
        this.$saveInFlight$delegate = mutableState;
        this.$onDirtyChange = function1;
        this.$dirty$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        return new MoaEditorScreenKt$MoaEditorScreen$3$1(this.$saving, this.$lastSaved, this.$error, this.$saveInFlight$delegate, this.$onDirtyChange, this.$dirty$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((MoaEditorScreenKt$MoaEditorScreen$3$1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                if (MoaEditorScreenKt.MoaEditorScreen$lambda$39(this.$saveInFlight$delegate) && !this.$saving) {
                    if (this.$lastSaved) {
                        String str = this.$error;
                        if (str == null || StringsKt.isBlank(str)) {
                            MoaEditorScreenKt.MoaEditorScreen$markDirty(this.$onDirtyChange, this.$dirty$delegate, false);
                        }
                    }
                    MoaEditorScreenKt.MoaEditorScreen$lambda$40(this.$saveInFlight$delegate, false);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
