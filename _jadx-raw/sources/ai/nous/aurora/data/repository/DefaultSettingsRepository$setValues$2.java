package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.HermesConfigApi;
import ai.nous.aurora.data.remote.HermesConfigSetBatchItem;
import ai.nous.aurora.data.remote.HermesConfigSetBatchRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.json.JsonElement;
import okhttp3.ResponseBody;
import retrofit2.Response;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lai/nous/aurora/data/repository/HermesSettingsSnapshot;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository$setValues$2", f = "SettingsRepository.kt", i = {1, 2, 3}, l = {254, 257, 257, 266}, m = "invokeSuspend", n = {"prior", "prior", "prior"}, s = {"L$2", "L$2", "L$2"})
final class DefaultSettingsRepository$setValues$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends HermesSettingsSnapshot>>, Object> {
    final /* synthetic */ List<Pair<String, JsonElement>> $pairs;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ DefaultSettingsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DefaultSettingsRepository$setValues$2(List<? extends Pair<String, ? extends JsonElement>> list, DefaultSettingsRepository defaultSettingsRepository, Continuation<? super DefaultSettingsRepository$setValues$2> continuation) {
        super(2, continuation);
        this.$pairs = list;
        this.this$0 = defaultSettingsRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DefaultSettingsRepository$setValues$2 defaultSettingsRepository$setValues$2 = new DefaultSettingsRepository$setValues$2(this.$pairs, this.this$0, continuation);
        defaultSettingsRepository$setValues$2.L$0 = obj;
        return defaultSettingsRepository$setValues$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends HermesSettingsSnapshot>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<HermesSettingsSnapshot>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<HermesSettingsSnapshot>> continuation) {
        return ((DefaultSettingsRepository$setValues$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:45:0x010d A[Catch: all -> 0x0262, TRY_LEAVE, TryCatch #1 {all -> 0x0262, blocks: (B:7:0x0023, B:10:0x0039, B:13:0x0051, B:16:0x0065, B:43:0x0106, B:45:0x010d, B:97:0x024a, B:98:0x0255, B:19:0x007b, B:21:0x0084, B:22:0x008c, B:24:0x0092, B:26:0x00a8, B:29:0x00b2, B:30:0x00d4, B:31:0x00d5, B:33:0x00dc, B:99:0x0256, B:100:0x0261), top: B:106:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x011c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:48:0x011d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0143 A[Catch: all -> 0x0244, TRY_LEAVE, TryCatch #2 {all -> 0x0244, blocks: (B:49:0x0124, B:50:0x013d, B:52:0x0143), top: B:107:0x0124 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x0183 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:58:0x0184  */
    /* JADX WARN: Code duplicated, block: B:61:0x018f A[Catch: all -> 0x0240, TryCatch #0 {all -> 0x0240, blocks: (B:78:0x01f7, B:80:0x01ff, B:83:0x020a, B:85:0x0217, B:86:0x021c, B:87:0x022a, B:88:0x022b, B:91:0x0236, B:92:0x023f, B:59:0x0187, B:61:0x018f, B:63:0x0196, B:65:0x019c, B:67:0x01a4, B:69:0x01aa, B:72:0x01c3, B:73:0x01e3, B:74:0x01e4, B:54:0x015f, B:55:0x0169), top: B:105:0x015f }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0196 A[Catch: all -> 0x0240, TryCatch #0 {all -> 0x0240, blocks: (B:78:0x01f7, B:80:0x01ff, B:83:0x020a, B:85:0x0217, B:86:0x021c, B:87:0x022a, B:88:0x022b, B:91:0x0236, B:92:0x023f, B:59:0x0187, B:61:0x018f, B:63:0x0196, B:65:0x019c, B:67:0x01a4, B:69:0x01aa, B:72:0x01c3, B:73:0x01e3, B:74:0x01e4, B:54:0x015f, B:55:0x0169), top: B:105:0x015f }] */
    /* JADX WARN: Code duplicated, block: B:66:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:69:0x01aa A[Catch: all -> 0x0240, TryCatch #0 {all -> 0x0240, blocks: (B:78:0x01f7, B:80:0x01ff, B:83:0x020a, B:85:0x0217, B:86:0x021c, B:87:0x022a, B:88:0x022b, B:91:0x0236, B:92:0x023f, B:59:0x0187, B:61:0x018f, B:63:0x0196, B:65:0x019c, B:67:0x01a4, B:69:0x01aa, B:72:0x01c3, B:73:0x01e3, B:74:0x01e4, B:54:0x015f, B:55:0x0169), top: B:105:0x015f }] */
    /* JADX WARN: Code duplicated, block: B:71:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:74:0x01e4 A[Catch: all -> 0x0240, TryCatch #0 {all -> 0x0240, blocks: (B:78:0x01f7, B:80:0x01ff, B:83:0x020a, B:85:0x0217, B:86:0x021c, B:87:0x022a, B:88:0x022b, B:91:0x0236, B:92:0x023f, B:59:0x0187, B:61:0x018f, B:63:0x0196, B:65:0x019c, B:67:0x01a4, B:69:0x01aa, B:72:0x01c3, B:73:0x01e3, B:74:0x01e4, B:54:0x015f, B:55:0x0169), top: B:105:0x015f }] */
    /* JADX WARN: Code duplicated, block: B:76:0x01f4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:77:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:80:0x01ff A[Catch: all -> 0x0240, TryCatch #0 {all -> 0x0240, blocks: (B:78:0x01f7, B:80:0x01ff, B:83:0x020a, B:85:0x0217, B:86:0x021c, B:87:0x022a, B:88:0x022b, B:91:0x0236, B:92:0x023f, B:59:0x0187, B:61:0x018f, B:63:0x0196, B:65:0x019c, B:67:0x01a4, B:69:0x01aa, B:72:0x01c3, B:73:0x01e3, B:74:0x01e4, B:54:0x015f, B:55:0x0169), top: B:105:0x015f }] */
    /* JADX WARN: Code duplicated, block: B:85:0x0217 A[Catch: all -> 0x0240, TryCatch #0 {all -> 0x0240, blocks: (B:78:0x01f7, B:80:0x01ff, B:83:0x020a, B:85:0x0217, B:86:0x021c, B:87:0x022a, B:88:0x022b, B:91:0x0236, B:92:0x023f, B:59:0x0187, B:61:0x018f, B:63:0x0196, B:65:0x019c, B:67:0x01a4, B:69:0x01aa, B:72:0x01c3, B:73:0x01e3, B:74:0x01e4, B:54:0x015f, B:55:0x0169), top: B:105:0x015f }] */
    /* JADX WARN: Code duplicated, block: B:86:0x021c A[Catch: all -> 0x0240, TryCatch #0 {all -> 0x0240, blocks: (B:78:0x01f7, B:80:0x01ff, B:83:0x020a, B:85:0x0217, B:86:0x021c, B:87:0x022a, B:88:0x022b, B:91:0x0236, B:92:0x023f, B:59:0x0187, B:61:0x018f, B:63:0x0196, B:65:0x019c, B:67:0x01a4, B:69:0x01aa, B:72:0x01c3, B:73:0x01e3, B:74:0x01e4, B:54:0x015f, B:55:0x0169), top: B:105:0x015f }] */
    /* JADX WARN: Code duplicated, block: B:90:0x0234  */
    /* JADX WARN: Code duplicated, block: B:97:0x024a A[Catch: all -> 0x0262, TRY_ENTER, TryCatch #1 {all -> 0x0262, blocks: (B:7:0x0023, B:10:0x0039, B:13:0x0051, B:16:0x0065, B:43:0x0106, B:45:0x010d, B:97:0x024a, B:98:0x0255, B:19:0x007b, B:21:0x0084, B:22:0x008c, B:24:0x0092, B:26:0x00a8, B:29:0x00b2, B:30:0x00d4, B:31:0x00d5, B:33:0x00dc, B:99:0x0256, B:100:0x0261), top: B:106:0x0009 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:61:0x018f, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM7388constructorimpl;
        Object $result;
        int i;
        HermesSettingsSnapshot prior;
        DefaultSettingsRepository defaultSettingsRepository;
        List<Pair<String, JsonElement>> list;
        Object $result2;
        Object $result3;
        List<Pair<String, JsonElement>> list2;
        int i2;
        Object objService;
        Object $result4;
        Object $result5;
        List<Pair<String, JsonElement>> list3;
        int i3;
        Collection destination$iv$iv;
        Object $result6;
        int i4;
        HermesSettingsSnapshot prior2;
        List<Pair<String, JsonElement>> list4;
        Object $result7;
        Response response;
        Object $result8;
        DefaultSettingsRepository defaultSettingsRepository2;
        ResponseBody responseBodyErrorBody;
        String detail;
        String str;
        String strString;
        HermesSettingsSnapshot reloaded;
        String error;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r2 = this.label;
        try {
            switch (r2) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    $result = obj;
                    List<Pair<String, JsonElement>> list5 = this.$pairs;
                    DefaultSettingsRepository defaultSettingsRepository3 = this.this$0;
                    Result.Companion companion = Result.INSTANCE;
                    i = 0;
                    if (list5.isEmpty()) {
                        throw new IllegalStateException("empty batch".toString());
                    }
                    List<Pair<String, JsonElement>> $this$forEach$iv = list5;
                    for (Object element$iv : $this$forEach$iv) {
                        String path = (String) ((Pair) element$iv).component1();
                        if (Intrinsics.areEqual(path, "model") || Intrinsics.areEqual(path, "model_context_length")) {
                            throw new IllegalStateException(("Refusing to write bare path '" + path + "' (would corrupt nested model config)").toString());
                        }
                    }
                    prior = defaultSettingsRepository3.cached;
                    if (prior == null) {
                        this.L$0 = list5;
                        this.L$1 = defaultSettingsRepository3;
                        this.label = 1;
                        Object objLoad = defaultSettingsRepository3.load(false, this);
                        if (objLoad == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                        $result3 = objLoad;
                        list2 = list5;
                        defaultSettingsRepository = defaultSettingsRepository3;
                        i2 = 0;
                        try {
                            List<Pair<String, JsonElement>> list6 = list2;
                            prior = (HermesSettingsSnapshot) $result3;
                            $result = $result2;
                            i = i2;
                            list = list6;
                        } catch (Throwable th) {
                            th = th;
                            r2 = $result2;
                            Result.Companion companion2 = Result.INSTANCE;
                            objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
                            return Result.m7387boximpl(objM7388constructorimpl);
                        }
                    } else {
                        defaultSettingsRepository = defaultSettingsRepository3;
                        list = list5;
                    }
                    if (prior.getRemoteAvailable()) {
                        throw new IllegalStateException("The config bridge is offline. Reconnect before changing settings.".toString());
                    }
                    this.L$0 = list;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = prior;
                    this.label = 2;
                    objService = defaultSettingsRepository.service(this);
                    if (objService == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int i5 = i;
                    $result4 = $result;
                    $result5 = objService;
                    list3 = list;
                    i3 = i5;
                    try {
                        HermesConfigApi hermesConfigApi = (HermesConfigApi) $result5;
                        Iterable $this$map$iv = list3;
                        destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        for (Object item$iv$iv : $this$map$iv) {
                            Pair pair = (Pair) item$iv$iv;
                            String path2 = (String) pair.component1();
                            JsonElement value = (JsonElement) pair.component2();
                            $result7 = $result4;
                            try {
                                destination$iv$iv.add(new HermesConfigSetBatchItem(path2, value));
                                $result4 = $result7;
                            } catch (Throwable th2) {
                                th = th2;
                                r2 = $result7;
                                Result.Companion companion3 = Result.INSTANCE;
                                objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
                                return Result.m7387boximpl(objM7388constructorimpl);
                            }
                        }
                        HermesConfigSetBatchRequest hermesConfigSetBatchRequest = new HermesConfigSetBatchRequest((List) destination$iv$iv);
                        this.L$0 = list3;
                        this.L$1 = defaultSettingsRepository;
                        this.L$2 = prior;
                        this.label = 3;
                        $result6 = hermesConfigApi.setValues(hermesConfigSetBatchRequest, this);
                        if ($result6 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i4 = i3;
                        prior2 = prior;
                        list4 = list3;
                        response = (Response) $result6;
                        if (!response.isSuccessful()) {
                            responseBodyErrorBody = response.errorBody();
                            if (responseBodyErrorBody != null || (strString = responseBodyErrorBody.string()) == null) {
                                detail = null;
                            } else {
                                detail = StringsKt.take(strString, 180);
                            }
                            int iCode = response.code();
                            str = detail != null ? ": " + detail : null;
                            if (str == null) {
                                str = "";
                            }
                            throw new IllegalStateException(("Batch save HTTP " + iCode + str).toString());
                        }
                        this.L$0 = list4;
                        this.L$1 = defaultSettingsRepository;
                        this.L$2 = prior2;
                        this.label = 4;
                        $result8 = defaultSettingsRepository.load(true, this);
                        if ($result8 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultSettingsRepository2 = defaultSettingsRepository;
                        reloaded = (HermesSettingsSnapshot) $result8;
                        if (reloaded.getRemoteAvailable() && !reloaded.getValues().isEmpty()) {
                            if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), list4)) {
                                objM7388constructorimpl = Result.m7388constructorimpl(reloaded);
                                return Result.m7387boximpl(objM7388constructorimpl);
                            }
                            defaultSettingsRepository2.cached = prior2;
                            throw new IllegalStateException("Batch save readback mismatch — last-good restored".toString());
                        }
                        defaultSettingsRepository2.cached = prior2;
                        error = reloaded.getError();
                        if (error == null) {
                            error = "Batch save wrote but reload failed — last-good restored";
                        }
                        throw new IllegalStateException(error.toString());
                    } catch (Throwable th3) {
                        th = th3;
                        r2 = $result4;
                    }
                    break;
                case 1:
                    $result3 = obj;
                    DefaultSettingsRepository defaultSettingsRepository4 = (DefaultSettingsRepository) this.L$1;
                    List<Pair<String, JsonElement>> list7 = (List) this.L$0;
                    ResultKt.throwOnFailure($result3);
                    list2 = list7;
                    defaultSettingsRepository = defaultSettingsRepository4;
                    i2 = 0;
                    $result2 = $result3;
                    List<Pair<String, JsonElement>> list8 = list2;
                    prior = (HermesSettingsSnapshot) $result3;
                    $result = $result2;
                    i = i2;
                    list = list8;
                    if (prior.getRemoteAvailable()) {
                        throw new IllegalStateException("The config bridge is offline. Reconnect before changing settings.".toString());
                    }
                    this.L$0 = list;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = prior;
                    this.label = 2;
                    objService = defaultSettingsRepository.service(this);
                    if (objService == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int i6 = i;
                    $result4 = $result;
                    $result5 = objService;
                    list3 = list;
                    i3 = i6;
                    HermesConfigApi hermesConfigApi2 = (HermesConfigApi) $result5;
                    Iterable $this$map$iv2 = list3;
                    destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                    while (r13.hasNext()) {
                        Pair pair2 = (Pair) item$iv$iv;
                        String path3 = (String) pair2.component1();
                        JsonElement value2 = (JsonElement) pair2.component2();
                        $result7 = $result4;
                        destination$iv$iv.add(new HermesConfigSetBatchItem(path3, value2));
                        $result4 = $result7;
                    }
                    HermesConfigSetBatchRequest hermesConfigSetBatchRequest2 = new HermesConfigSetBatchRequest((List) destination$iv$iv);
                    this.L$0 = list3;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = prior;
                    this.label = 3;
                    $result6 = hermesConfigApi2.setValues(hermesConfigSetBatchRequest2, this);
                    if ($result6 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i4 = i3;
                    prior2 = prior;
                    list4 = list3;
                    response = (Response) $result6;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        if (responseBodyErrorBody != null) {
                            detail = null;
                        } else {
                            detail = null;
                        }
                        int iCode2 = response.code();
                        if (detail != null) {
                        }
                        if (str == null) {
                            str = "";
                        }
                        throw new IllegalStateException(("Batch save HTTP " + iCode2 + str).toString());
                    }
                    this.L$0 = list4;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = prior2;
                    this.label = 4;
                    $result8 = defaultSettingsRepository.load(true, this);
                    if ($result8 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    defaultSettingsRepository2 = defaultSettingsRepository;
                    reloaded = (HermesSettingsSnapshot) $result8;
                    if (reloaded.getRemoteAvailable()) {
                        if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), list4)) {
                            objM7388constructorimpl = Result.m7388constructorimpl(reloaded);
                            return Result.m7387boximpl(objM7388constructorimpl);
                        }
                        defaultSettingsRepository2.cached = prior2;
                        throw new IllegalStateException("Batch save readback mismatch — last-good restored".toString());
                    }
                    defaultSettingsRepository2.cached = prior2;
                    error = reloaded.getError();
                    if (error == null) {
                        error = "Batch save wrote but reload failed — last-good restored";
                    }
                    throw new IllegalStateException(error.toString());
                case 2:
                    $result5 = obj;
                    HermesSettingsSnapshot prior3 = (HermesSettingsSnapshot) this.L$2;
                    defaultSettingsRepository = (DefaultSettingsRepository) this.L$1;
                    List<Pair<String, JsonElement>> list9 = (List) this.L$0;
                    ResultKt.throwOnFailure($result5);
                    list3 = list9;
                    prior = prior3;
                    i3 = 0;
                    $result4 = $result5;
                    HermesConfigApi hermesConfigApi3 = (HermesConfigApi) $result5;
                    Iterable $this$map$iv3 = list3;
                    destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
                    while (r13.hasNext()) {
                        Pair pair3 = (Pair) item$iv$iv;
                        String path4 = (String) pair3.component1();
                        JsonElement value3 = (JsonElement) pair3.component2();
                        $result7 = $result4;
                        destination$iv$iv.add(new HermesConfigSetBatchItem(path4, value3));
                        $result4 = $result7;
                    }
                    HermesConfigSetBatchRequest hermesConfigSetBatchRequest3 = new HermesConfigSetBatchRequest((List) destination$iv$iv);
                    this.L$0 = list3;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = prior;
                    this.label = 3;
                    $result6 = hermesConfigApi3.setValues(hermesConfigSetBatchRequest3, this);
                    if ($result6 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i4 = i3;
                    prior2 = prior;
                    list4 = list3;
                    response = (Response) $result6;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        if (responseBodyErrorBody != null) {
                            detail = null;
                        } else {
                            detail = null;
                        }
                        int iCode3 = response.code();
                        if (detail != null) {
                        }
                        if (str == null) {
                            str = "";
                        }
                        throw new IllegalStateException(("Batch save HTTP " + iCode3 + str).toString());
                    }
                    this.L$0 = list4;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = prior2;
                    this.label = 4;
                    $result8 = defaultSettingsRepository.load(true, this);
                    if ($result8 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    defaultSettingsRepository2 = defaultSettingsRepository;
                    reloaded = (HermesSettingsSnapshot) $result8;
                    if (reloaded.getRemoteAvailable()) {
                        if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), list4)) {
                            objM7388constructorimpl = Result.m7388constructorimpl(reloaded);
                            return Result.m7387boximpl(objM7388constructorimpl);
                        }
                        defaultSettingsRepository2.cached = prior2;
                        throw new IllegalStateException("Batch save readback mismatch — last-good restored".toString());
                    }
                    defaultSettingsRepository2.cached = prior2;
                    error = reloaded.getError();
                    if (error == null) {
                        error = "Batch save wrote but reload failed — last-good restored";
                    }
                    throw new IllegalStateException(error.toString());
                case 3:
                    $result6 = obj;
                    i4 = 0;
                    HermesSettingsSnapshot prior4 = (HermesSettingsSnapshot) this.L$2;
                    defaultSettingsRepository = (DefaultSettingsRepository) this.L$1;
                    List<Pair<String, JsonElement>> list10 = (List) this.L$0;
                    ResultKt.throwOnFailure($result6);
                    prior2 = prior4;
                    list4 = list10;
                    response = (Response) $result6;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        if (responseBodyErrorBody != null) {
                            detail = null;
                        } else {
                            detail = null;
                        }
                        int iCode4 = response.code();
                        if (detail != null) {
                        }
                        if (str == null) {
                            str = "";
                        }
                        throw new IllegalStateException(("Batch save HTTP " + iCode4 + str).toString());
                    }
                    this.L$0 = list4;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = prior2;
                    this.label = 4;
                    $result8 = defaultSettingsRepository.load(true, this);
                    if ($result8 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    defaultSettingsRepository2 = defaultSettingsRepository;
                    reloaded = (HermesSettingsSnapshot) $result8;
                    if (reloaded.getRemoteAvailable()) {
                        if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), list4)) {
                            objM7388constructorimpl = Result.m7388constructorimpl(reloaded);
                            return Result.m7387boximpl(objM7388constructorimpl);
                        }
                        defaultSettingsRepository2.cached = prior2;
                        throw new IllegalStateException("Batch save readback mismatch — last-good restored".toString());
                    }
                    defaultSettingsRepository2.cached = prior2;
                    error = reloaded.getError();
                    if (error == null) {
                        error = "Batch save wrote but reload failed — last-good restored";
                    }
                    throw new IllegalStateException(error.toString());
                case 4:
                    $result8 = obj;
                    prior2 = (HermesSettingsSnapshot) this.L$2;
                    defaultSettingsRepository2 = (DefaultSettingsRepository) this.L$1;
                    list4 = (List) this.L$0;
                    ResultKt.throwOnFailure($result8);
                    reloaded = (HermesSettingsSnapshot) $result8;
                    if (reloaded.getRemoteAvailable()) {
                        if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), list4)) {
                            objM7388constructorimpl = Result.m7388constructorimpl(reloaded);
                            return Result.m7387boximpl(objM7388constructorimpl);
                        }
                        defaultSettingsRepository2.cached = prior2;
                        throw new IllegalStateException("Batch save readback mismatch — last-good restored".toString());
                    }
                    defaultSettingsRepository2.cached = prior2;
                    error = reloaded.getError();
                    if (error == null) {
                        error = "Batch save wrote but reload failed — last-good restored";
                    }
                    throw new IllegalStateException(error.toString());
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }
}
