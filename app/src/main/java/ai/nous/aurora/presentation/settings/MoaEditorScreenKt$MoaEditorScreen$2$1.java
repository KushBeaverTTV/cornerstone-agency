package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.MoaModelSlotDto;
import ai.nous.aurora.data.remote.MoaPresetDto;
import ai.nous.aurora.data.repository.MoaSnapshot;
import androidx.compose.runtime.MutableState;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$2$1", f = "MoaEditorScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class MoaEditorScreenKt$MoaEditorScreen$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ MutableState<String> $activePreset$delegate;
    final /* synthetic */ MutableState<String> $defaultPreset$delegate;
    final /* synthetic */ MutableState<Boolean> $dirty$delegate;
    final /* synthetic */ MutableState<Map<String, MoaPresetDto>> $drafts$delegate;
    final /* synthetic */ MutableState<Boolean> $saveTraces$delegate;
    final /* synthetic */ MutableState<String> $selectedName$delegate;
    final /* synthetic */ MoaSnapshot $snapshot;
    final /* synthetic */ MutableState<String> $traceDir$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MoaEditorScreenKt$MoaEditorScreen$2$1(MoaSnapshot moaSnapshot, MutableState<Boolean> mutableState, MutableState<Map<String, MoaPresetDto>> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<Boolean> mutableState6, MutableState<String> mutableState7, Continuation continuation) {
        super(2, continuation);
        this.$snapshot = moaSnapshot;
        this.$dirty$delegate = mutableState;
        this.$drafts$delegate = mutableState2;
        this.$selectedName$delegate = mutableState3;
        this.$defaultPreset$delegate = mutableState4;
        this.$activePreset$delegate = mutableState5;
        this.$saveTraces$delegate = mutableState6;
        this.$traceDir$delegate = mutableState7;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        return new MoaEditorScreenKt$MoaEditorScreen$2$1(this.$snapshot, this.$dirty$delegate, this.$drafts$delegate, this.$selectedName$delegate, this.$defaultPreset$delegate, this.$activePreset$delegate, this.$saveTraces$delegate, this.$traceDir$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((MoaEditorScreenKt$MoaEditorScreen$2$1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                if (!MoaEditorScreenKt.MoaEditorScreen$lambda$27(this.$dirty$delegate)) {
                    MutableState<Map<String, MoaPresetDto>> mutableState = this.$drafts$delegate;
                    Map<String, MoaPresetDto> presets = this.$snapshot.getPresets();
                    if (presets.isEmpty()) {
                        presets = MapsKt.mapOf(TuplesKt.to("default", new MoaPresetDto(false, (List) null, (MoaModelSlotDto) null, (Double) null, (Double) null, 0, (Integer) null, (String) null, 255, (DefaultConstructorMarker) null)));
                    }
                    mutableState.setValue(presets);
                    MutableState<String> mutableState2 = this.$defaultPreset$delegate;
                    String defaultPreset = this.$snapshot.getDefaultPreset();
                    MutableState<String> mutableState3 = this.$selectedName$delegate;
                    if (StringsKt.isBlank(defaultPreset)) {
                        defaultPreset = MoaEditorScreenKt.MoaEditorScreen$lambda$7(mutableState3);
                    }
                    mutableState2.setValue(defaultPreset);
                    this.$activePreset$delegate.setValue(this.$snapshot.getActivePreset());
                    MoaEditorScreenKt.MoaEditorScreen$lambda$18(this.$saveTraces$delegate, this.$snapshot.getSaveTraces());
                    this.$traceDir$delegate.setValue(this.$snapshot.getTraceDir());
                    if (!MoaEditorScreenKt.MoaEditorScreen$lambda$24(this.$drafts$delegate).containsKey(MoaEditorScreenKt.MoaEditorScreen$lambda$7(this.$selectedName$delegate))) {
                        MutableState<String> mutableState4 = this.$selectedName$delegate;
                        String it = MoaEditorScreenKt.MoaEditorScreen$lambda$11(this.$defaultPreset$delegate);
                        if (!MoaEditorScreenKt.MoaEditorScreen$lambda$24(this.$drafts$delegate).containsKey(it)) {
                            it = null;
                        }
                        if (it == null) {
                            it = (String) CollectionsKt.first(MoaEditorScreenKt.MoaEditorScreen$lambda$24(this.$drafts$delegate).keySet());
                        }
                        mutableState4.setValue(it);
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
