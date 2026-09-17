package ai.nous.aurora.data.remote;

import ai.nous.aurora.domain.model.AuroraSession;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import retrofit2.Response;

/* JADX INFO: compiled from: GatewayClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lai/nous/aurora/domain/model/AuroraSession;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.remote.GatewayClient$getSessions$2", f = "GatewayClient.kt", i = {0, 0, 0, 0}, l = {168}, m = "invokeSuspend", n = {"svc", "out", "offset", "page"}, s = {"L$1", "L$2", "L$3", "I$0"})
final class GatewayClient$getSessions$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends List<? extends AuroraSession>>>, Object> {
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ GatewayClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GatewayClient$getSessions$2(GatewayClient gatewayClient, Continuation<? super GatewayClient$getSessions$2> continuation) {
        super(2, continuation);
        this.this$0 = gatewayClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        GatewayClient$getSessions$2 gatewayClient$getSessions$2 = new GatewayClient$getSessions$2(this.this$0, continuation);
        gatewayClient$getSessions$2.L$0 = obj;
        return gatewayClient$getSessions$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends List<? extends AuroraSession>>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<? extends List<AuroraSession>>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<? extends List<AuroraSession>>> continuation) {
        return ((GatewayClient$getSessions$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0078  */
    /* JADX WARN: Code duplicated, block: B:18:0x0091 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x0092  */
    /* JADX WARN: Code duplicated, block: B:22:0x00a8 A[Catch: all -> 0x0174, TryCatch #1 {all -> 0x0174, blocks: (B:20:0x00a0, B:22:0x00a8, B:24:0x00b0, B:25:0x00be, B:27:0x00c4), top: B:57:0x00a0 }] */
    /* JADX WARN: Code duplicated, block: B:24:0x00b0 A[Catch: all -> 0x0174, TryCatch #1 {all -> 0x0174, blocks: (B:20:0x00a0, B:22:0x00a8, B:24:0x00b0, B:25:0x00be, B:27:0x00c4), top: B:57:0x00a0 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x00c4 A[Catch: all -> 0x0174, TRY_LEAVE, TryCatch #1 {all -> 0x0174, blocks: (B:20:0x00a0, B:22:0x00a8, B:24:0x00b0, B:25:0x00be, B:27:0x00c4), top: B:57:0x00a0 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00ff A[Catch: all -> 0x016f, TryCatch #4 {all -> 0x016f, blocks: (B:29:0x00dd, B:30:0x00ec, B:32:0x00ff, B:35:0x010a, B:36:0x012d, B:37:0x013e, B:38:0x014b, B:39:0x014c, B:40:0x016e), top: B:63:0x00dd }] */
    /* JADX WARN: Code duplicated, block: B:37:0x013e A[Catch: all -> 0x016f, TryCatch #4 {all -> 0x016f, blocks: (B:29:0x00dd, B:30:0x00ec, B:32:0x00ff, B:35:0x010a, B:36:0x012d, B:37:0x013e, B:38:0x014b, B:39:0x014c, B:40:0x016e), top: B:63:0x00dd }] */
    /* JADX WARN: Code duplicated, block: B:39:0x014c A[Catch: all -> 0x016f, TryCatch #4 {all -> 0x016f, blocks: (B:29:0x00dd, B:30:0x00ec, B:32:0x00ff, B:35:0x010a, B:36:0x012d, B:37:0x013e, B:38:0x014b, B:39:0x014c, B:40:0x016e), top: B:63:0x00dd }] */
    /* JADX WARN: Code duplicated, block: B:45:0x017b A[Catch: all -> 0x018d, TRY_ENTER, TryCatch #2 {all -> 0x018d, blocks: (B:16:0x0079, B:45:0x017b, B:46:0x0188), top: B:59:0x0079 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0092 -> B:57:0x00a0). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List list;
        Object objM7388constructorimpl;
        Object $result;
        Object $result2;
        GatewayClient gatewayClient;
        GatewayService svc;
        LinkedHashMap out;
        Ref.IntRef offset;
        int page;
        int page2;
        int i;
        GatewayClient$getSessions$2 gatewayClient$getSessions$2;
        int i2;
        Response r;
        SessionListResponse body;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = 1;
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Object $result3 = obj;
                GatewayClient gatewayClient2 = this.this$0;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    int i4 = 0;
                    GatewayService svc2 = gatewayClient2.requireService();
                    LinkedHashMap out2 = new LinkedHashMap();
                    Ref.IntRef offset2 = new Ref.IntRef();
                    GatewayClient gatewayClient3 = gatewayClient2;
                    GatewayClient$getSessions$2 gatewayClient$getSessions$3 = this;
                    GatewayService svc3 = svc2;
                    int i5 = 0;
                    LinkedHashMap out3 = out2;
                    int i6 = 20;
                    Ref.IntRef offset3 = offset2;
                    int page3 = 100;
                    if (i5 >= i6) {
                        Collection collectionValues = out3.values();
                        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
                        list = CollectionsKt.toList(collectionValues);
                        objM7388constructorimpl = Result.m7388constructorimpl(list);
                        return Result.m7387boximpl(objM7388constructorimpl);
                    }
                    try {
                        int i7 = offset3.element;
                        gatewayClient$getSessions$3.L$0 = gatewayClient3;
                        gatewayClient$getSessions$3.L$1 = svc3;
                        gatewayClient$getSessions$3.L$2 = out3;
                        gatewayClient$getSessions$3.L$3 = offset3;
                        gatewayClient$getSessions$3.I$0 = page3;
                        gatewayClient$getSessions$3.I$1 = i6;
                        gatewayClient$getSessions$3.I$2 = i5;
                        gatewayClient$getSessions$3.label = i3;
                        Object objListSessions = svc3.listSessions(page3, i7, gatewayClient$getSessions$3);
                        if (objListSessions == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        try {
                            int i8 = i4;
                            $result = $result3;
                            $result2 = objListSessions;
                            gatewayClient = gatewayClient3;
                            svc = svc3;
                            out = out3;
                            offset = offset3;
                            page = page3;
                            page2 = i6;
                            i = i5;
                            gatewayClient$getSessions$2 = gatewayClient$getSessions$3;
                            i2 = i8;
                            r = (Response) $result2;
                            if (!r.isSuccessful()) {
                                throw new IllegalStateException(("Sessions HTTP " + r.code()).toString());
                            }
                            body = (SessionListResponse) r.body();
                            if (body == null) {
                                throw new IllegalStateException("Empty sessions body".toString());
                            }
                            Iterable $this$forEach$iv = body.getData();
                            for (Object element$iv : $this$forEach$iv) {
                                HermesSession s = (HermesSession) element$iv;
                                Object obj2 = coroutine_suspended;
                                Object $result4 = $result;
                                try {
                                    out.put(s.getId(), gatewayClient.toModel(s));
                                    $result = $result4;
                                    coroutine_suspended = obj2;
                                } catch (Throwable th) {
                                    th = th;
                                }
                            }
                            Object obj3 = coroutine_suspended;
                            Object $result5 = $result;
                            if (Intrinsics.areEqual(body.getHasMore(), Boxing.boxBoolean(true)) || body.getData().isEmpty()) {
                                Collection collectionValues2 = out.values();
                                Intrinsics.checkNotNullExpressionValue(collectionValues2, "<get-values>(...)");
                                list = CollectionsKt.toList(collectionValues2);
                            } else {
                                offset.element += body.getData().size();
                                int i9 = i + 1;
                                $result3 = $result5;
                                i3 = 1;
                                i4 = i2;
                                gatewayClient$getSessions$3 = gatewayClient$getSessions$2;
                                i6 = page2;
                                page3 = page;
                                offset3 = offset;
                                out3 = out;
                                svc3 = svc;
                                gatewayClient3 = gatewayClient;
                                coroutine_suspended = obj3;
                                i5 = i9;
                                if (i5 >= i6) {
                                    int i10 = offset3.element;
                                    gatewayClient$getSessions$3.L$0 = gatewayClient3;
                                    gatewayClient$getSessions$3.L$1 = svc3;
                                    gatewayClient$getSessions$3.L$2 = out3;
                                    gatewayClient$getSessions$3.L$3 = offset3;
                                    gatewayClient$getSessions$3.I$0 = page3;
                                    gatewayClient$getSessions$3.I$1 = i6;
                                    gatewayClient$getSessions$3.I$2 = i5;
                                    gatewayClient$getSessions$3.label = i3;
                                    Object objListSessions2 = svc3.listSessions(page3, i10, gatewayClient$getSessions$3);
                                    if (objListSessions2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    int i11 = i4;
                                    $result = $result3;
                                    $result2 = objListSessions2;
                                    gatewayClient = gatewayClient3;
                                    svc = svc3;
                                    out = out3;
                                    offset = offset3;
                                    page = page3;
                                    page2 = i6;
                                    i = i5;
                                    gatewayClient$getSessions$2 = gatewayClient$getSessions$3;
                                    i2 = i11;
                                    r = (Response) $result2;
                                    if (!r.isSuccessful()) {
                                        throw new IllegalStateException(("Sessions HTTP " + r.code()).toString());
                                    }
                                    body = (SessionListResponse) r.body();
                                    if (body == null) {
                                        throw new IllegalStateException("Empty sessions body".toString());
                                    }
                                    Iterable $this$forEach$iv2 = body.getData();
                                    while (r18.hasNext()) {
                                        HermesSession s2 = (HermesSession) element$iv;
                                        Object obj4 = coroutine_suspended;
                                        Object $result6 = $result;
                                        out.put(s2.getId(), gatewayClient.toModel(s2));
                                        $result = $result6;
                                        coroutine_suspended = obj4;
                                    }
                                    Object obj5 = coroutine_suspended;
                                    Object $result7 = $result;
                                    if (Intrinsics.areEqual(body.getHasMore(), Boxing.boxBoolean(true))) {
                                    }
                                    Collection collectionValues3 = out.values();
                                    Intrinsics.checkNotNullExpressionValue(collectionValues3, "<get-values>(...)");
                                    list = CollectionsKt.toList(collectionValues3);
                                } else {
                                    Collection collectionValues4 = out3.values();
                                    Intrinsics.checkNotNullExpressionValue(collectionValues4, "<get-values>(...)");
                                    list = CollectionsKt.toList(collectionValues4);
                                }
                            }
                            objM7388constructorimpl = Result.m7388constructorimpl(list);
                            return Result.m7387boximpl(objM7388constructorimpl);
                        } catch (Throwable th2) {
                            th = th2;
                        }
                        Result.Companion companion2 = Result.INSTANCE;
                        objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
                        return Result.m7387boximpl(objM7388constructorimpl);
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                break;
            case 1:
                $result2 = obj;
                int i12 = this.I$2;
                int i13 = this.I$1;
                int page4 = this.I$0;
                Ref.IntRef offset4 = (Ref.IntRef) this.L$3;
                LinkedHashMap out4 = (LinkedHashMap) this.L$2;
                GatewayService svc4 = (GatewayService) this.L$1;
                GatewayClient gatewayClient4 = (GatewayClient) this.L$0;
                try {
                    ResultKt.throwOnFailure($result2);
                    gatewayClient = gatewayClient4;
                    i2 = 0;
                    svc = svc4;
                    $result = $result2;
                    out = out4;
                    offset = offset4;
                    page = page4;
                    page2 = i13;
                    i = i12;
                    gatewayClient$getSessions$2 = this;
                    r = (Response) $result2;
                    if (!r.isSuccessful()) {
                        throw new IllegalStateException(("Sessions HTTP " + r.code()).toString());
                    }
                    body = (SessionListResponse) r.body();
                    if (body == null) {
                        throw new IllegalStateException("Empty sessions body".toString());
                    }
                    Iterable $this$forEach$iv3 = body.getData();
                    while (r18.hasNext()) {
                        HermesSession s3 = (HermesSession) element$iv;
                        Object obj6 = coroutine_suspended;
                        Object $result8 = $result;
                        out.put(s3.getId(), gatewayClient.toModel(s3));
                        $result = $result8;
                        coroutine_suspended = obj6;
                    }
                    Object obj7 = coroutine_suspended;
                    Object $result9 = $result;
                    if (Intrinsics.areEqual(body.getHasMore(), Boxing.boxBoolean(true))) {
                    }
                    Collection collectionValues5 = out.values();
                    Intrinsics.checkNotNullExpressionValue(collectionValues5, "<get-values>(...)");
                    list = CollectionsKt.toList(collectionValues5);
                    objM7388constructorimpl = Result.m7388constructorimpl(list);
                    return Result.m7387boximpl(objM7388constructorimpl);
                } catch (Throwable th5) {
                    th = th5;
                }
                Result.Companion companion3 = Result.INSTANCE;
                objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
                return Result.m7387boximpl(objM7388constructorimpl);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
