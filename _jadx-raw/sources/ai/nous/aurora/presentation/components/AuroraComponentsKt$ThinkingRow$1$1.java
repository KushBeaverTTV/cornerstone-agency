package ai.nous.aurora.presentation.components;

import androidx.compose.runtime.MutableIntState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AuroraComponents.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.presentation.components.AuroraComponentsKt$ThinkingRow$1$1", f = "AuroraComponents.kt", i = {}, l = {527}, m = "invokeSuspend", n = {}, s = {})
final class AuroraComponentsKt$ThinkingRow$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableIntState $dots$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AuroraComponentsKt$ThinkingRow$1$1(MutableIntState mutableIntState, Continuation<? super AuroraComponentsKt$ThinkingRow$1$1> continuation) {
        super(2, continuation);
        this.$dots$delegate = mutableIntState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuroraComponentsKt$ThinkingRow$1$1(this.$dots$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuroraComponentsKt$ThinkingRow$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0029 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x0027 -> B:11:0x002a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:10:0x0029
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            switch(r1) {
                case 0: goto L17;
                case 1: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L12:
            kotlin.ResultKt.throwOnFailure(r7)
            r1 = r6
            goto L2a
        L17:
            kotlin.ResultKt.throwOnFailure(r7)
            r1 = r6
        L1b:
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r1.label = r2
            r4 = 420(0x1a4, double:2.075E-321)
            java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r4, r3)
            if (r3 != r0) goto L2a
            return r0
        L2a:
            androidx.compose.runtime.MutableIntState r3 = r1.$dots$delegate
            androidx.compose.runtime.MutableIntState r4 = r1.$dots$delegate
            int r4 = ai.nous.aurora.presentation.components.AuroraComponentsKt.access$ThinkingRow$lambda$26(r4)
            int r4 = r4 % 3
            int r4 = r4 + r2
            ai.nous.aurora.presentation.components.AuroraComponentsKt.access$ThinkingRow$lambda$27(r3, r4)
            goto L1b
        */
        throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.presentation.components.AuroraComponentsKt$ThinkingRow$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
