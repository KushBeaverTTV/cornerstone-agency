package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.HermesConfigApi;
import ai.nous.aurora.data.remote.HermesConfigSetRequest;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
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
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository$setValue$2", f = "SettingsRepository.kt", i = {1, 2, 3}, l = {223, 226, 226, 232}, m = "invokeSuspend", n = {"prior", "prior", "prior"}, s = {"L$3", "L$3", "L$3"})
final class DefaultSettingsRepository$setValue$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ String $path;
    final /* synthetic */ JsonElement $value;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ DefaultSettingsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSettingsRepository$setValue$2(String str, DefaultSettingsRepository defaultSettingsRepository, JsonElement jsonElement, Continuation continuation) {
        super(2, continuation);
        this.$path = str;
        this.this$0 = defaultSettingsRepository;
        this.$value = jsonElement;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        DefaultSettingsRepository$setValue$2 defaultSettingsRepository$setValue$2 = new DefaultSettingsRepository$setValue$2(this.$path, this.this$0, this.$value, continuation);
        defaultSettingsRepository$setValue$2.L$0 = obj;
        return defaultSettingsRepository$setValue$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return invoke2(coroutineScope, (Continuation) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DefaultSettingsRepository$setValue$2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00c9 A[Catch: all -> 0x0204, TRY_LEAVE, TryCatch #0 {all -> 0x0204, blocks: (B:7:0x0023, B:10:0x003b, B:13:0x0055, B:16:0x006b, B:35:0x00c2, B:37:0x00c9, B:78:0x01d5, B:79:0x01e0, B:19:0x0081, B:21:0x008c, B:23:0x0094, B:25:0x009a, B:80:0x01e1, B:81:0x0203), top: B:86:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00da A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:0x00db  */
    /* JADX WARN: Code duplicated, block: B:43:0x00f8 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:46:0x0101 A[Catch: all -> 0x00b9, TryCatch #1 {all -> 0x00b9, blocks: (B:63:0x016d, B:65:0x0175, B:68:0x0180, B:70:0x0195, B:71:0x019a, B:72:0x01bf, B:73:0x01c0, B:76:0x01cb, B:77:0x01d4, B:44:0x00f9, B:46:0x0101, B:48:0x0108, B:50:0x010e, B:52:0x0116, B:54:0x011c, B:57:0x0136, B:58:0x0156, B:59:0x0157, B:41:0x00e0, B:29:0x00b1), top: B:87:0x00b1 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x0108 A[Catch: all -> 0x00b9, TryCatch #1 {all -> 0x00b9, blocks: (B:63:0x016d, B:65:0x0175, B:68:0x0180, B:70:0x0195, B:71:0x019a, B:72:0x01bf, B:73:0x01c0, B:76:0x01cb, B:77:0x01d4, B:44:0x00f9, B:46:0x0101, B:48:0x0108, B:50:0x010e, B:52:0x0116, B:54:0x011c, B:57:0x0136, B:58:0x0156, B:59:0x0157, B:41:0x00e0, B:29:0x00b1), top: B:87:0x00b1 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0115  */
    /* JADX WARN: Code duplicated, block: B:54:0x011c A[Catch: all -> 0x00b9, TryCatch #1 {all -> 0x00b9, blocks: (B:63:0x016d, B:65:0x0175, B:68:0x0180, B:70:0x0195, B:71:0x019a, B:72:0x01bf, B:73:0x01c0, B:76:0x01cb, B:77:0x01d4, B:44:0x00f9, B:46:0x0101, B:48:0x0108, B:50:0x010e, B:52:0x0116, B:54:0x011c, B:57:0x0136, B:58:0x0156, B:59:0x0157, B:41:0x00e0, B:29:0x00b1), top: B:87:0x00b1 }] */
    /* JADX WARN: Code duplicated, block: B:56:0x0134  */
    /* JADX WARN: Code duplicated, block: B:59:0x0157 A[Catch: all -> 0x00b9, TryCatch #1 {all -> 0x00b9, blocks: (B:63:0x016d, B:65:0x0175, B:68:0x0180, B:70:0x0195, B:71:0x019a, B:72:0x01bf, B:73:0x01c0, B:76:0x01cb, B:77:0x01d4, B:44:0x00f9, B:46:0x0101, B:48:0x0108, B:50:0x010e, B:52:0x0116, B:54:0x011c, B:57:0x0136, B:58:0x0156, B:59:0x0157, B:41:0x00e0, B:29:0x00b1), top: B:87:0x00b1 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x0168 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:62:0x0169  */
    /* JADX WARN: Code duplicated, block: B:65:0x0175 A[Catch: all -> 0x00b9, TryCatch #1 {all -> 0x00b9, blocks: (B:63:0x016d, B:65:0x0175, B:68:0x0180, B:70:0x0195, B:71:0x019a, B:72:0x01bf, B:73:0x01c0, B:76:0x01cb, B:77:0x01d4, B:44:0x00f9, B:46:0x0101, B:48:0x0108, B:50:0x010e, B:52:0x0116, B:54:0x011c, B:57:0x0136, B:58:0x0156, B:59:0x0157, B:41:0x00e0, B:29:0x00b1), top: B:87:0x00b1 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x0195 A[Catch: all -> 0x00b9, TryCatch #1 {all -> 0x00b9, blocks: (B:63:0x016d, B:65:0x0175, B:68:0x0180, B:70:0x0195, B:71:0x019a, B:72:0x01bf, B:73:0x01c0, B:76:0x01cb, B:77:0x01d4, B:44:0x00f9, B:46:0x0101, B:48:0x0108, B:50:0x010e, B:52:0x0116, B:54:0x011c, B:57:0x0136, B:58:0x0156, B:59:0x0157, B:41:0x00e0, B:29:0x00b1), top: B:87:0x00b1 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x019a A[Catch: all -> 0x00b9, TryCatch #1 {all -> 0x00b9, blocks: (B:63:0x016d, B:65:0x0175, B:68:0x0180, B:70:0x0195, B:71:0x019a, B:72:0x01bf, B:73:0x01c0, B:76:0x01cb, B:77:0x01d4, B:44:0x00f9, B:46:0x0101, B:48:0x0108, B:50:0x010e, B:52:0x0116, B:54:0x011c, B:57:0x0136, B:58:0x0156, B:59:0x0157, B:41:0x00e0, B:29:0x00b1), top: B:87:0x00b1 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:78:0x01d5 A[Catch: all -> 0x0204, TRY_ENTER, TryCatch #0 {all -> 0x0204, blocks: (B:7:0x0023, B:10:0x003b, B:13:0x0055, B:16:0x006b, B:35:0x00c2, B:37:0x00c9, B:78:0x01d5, B:79:0x01e0, B:19:0x0081, B:21:0x008c, B:23:0x0094, B:25:0x009a, B:80:0x01e1, B:81:0x0203), top: B:86:0x0007 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object $result2;
        int i;
        HermesSettingsSnapshot prior;
        JsonElement jsonElement;
        String str = null;
        DefaultSettingsRepository defaultSettingsRepository;
        String str2;
        int i2;
        Object objService;
        String str3;
        int i3;
        Response response;
        JsonElement jsonElement2;
        DefaultSettingsRepository defaultSettingsRepository2;
        String str4;
        ResponseBody responseBodyErrorBody;
        String str5;
        String detail;
        String strString;
        HermesSettingsSnapshot reloaded;
        String error;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    String str6 = this.$path;
                    DefaultSettingsRepository defaultSettingsRepository3 = this.this$0;
                    JsonElement jsonElement3 = this.$value;
                    Result.Companion companion = Result.Companion;
                    i = 0;
                    if (Intrinsics.areEqual(str6, "model") || Intrinsics.areEqual(str6, "model_context_length")) {
                        throw new IllegalStateException(("Refusing to write bare path '" + str6 + "' (would corrupt nested model config)").toString());
                    }
                    prior = defaultSettingsRepository3.cached;
                    if (prior == null) {
                        this.L$0 = str6;
                        this.L$1 = defaultSettingsRepository3;
                        this.L$2 = jsonElement3;
                        this.label = 1;
                        Object objLoad = defaultSettingsRepository3.load(false, this);
                        if (objLoad == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objLoad;
                        str2 = str6;
                        defaultSettingsRepository = defaultSettingsRepository3;
                        jsonElement = jsonElement3;
                        i2 = 0;
                        try {
                            String str7 = str2;
                            prior = (HermesSettingsSnapshot) $result;
                            $result = $result;
                            i = i2;
                            str = str7;
                        } catch (Throwable th) {
                            $result = $result;
                            Result.Companion companion2 = Result.Companion;
                            $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                        }
                    } else {
                        jsonElement = jsonElement3;
                        str = str6;
                        defaultSettingsRepository = defaultSettingsRepository3;
                    }
                    if (prior.getRemoteAvailable()) {
                        throw new IllegalStateException("The config bridge is offline. Reconnect before changing settings.".toString());
                    }
                    this.L$0 = str;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = jsonElement;
                    this.L$3 = prior;
                    this.label = 2;
                    objService = defaultSettingsRepository.service(this);
                    if (objService == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objService;
                    str3 = str;
                    i3 = i;
                    HermesConfigSetRequest hermesConfigSetRequest = new HermesConfigSetRequest(str3, jsonElement);
                    this.L$0 = str3;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = jsonElement;
                    this.L$3 = prior;
                    this.label = 3;
                    $result = ((HermesConfigApi) $result).setValue(hermesConfigSetRequest, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    response = (Response) $result;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        str5 = null;
                        if (responseBodyErrorBody != null || (strString = responseBodyErrorBody.string()) == null) {
                            detail = null;
                        } else {
                            detail = StringsKt.take(strString, 180);
                        }
                        int iCode = response.code();
                        if (detail != null) {
                            String it = detail;
                            str5 = ": " + it;
                        }
                        if (str5 == null) {
                            str5 = "";
                        }
                        throw new IllegalStateException(("Save HTTP " + iCode + str5).toString());
                    }
                    this.L$0 = str3;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = jsonElement;
                    this.L$3 = prior;
                    this.label = 4;
                    $result = defaultSettingsRepository.load(true, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    jsonElement2 = jsonElement;
                    defaultSettingsRepository2 = defaultSettingsRepository;
                    str4 = str3;
                    reloaded = (HermesSettingsSnapshot) $result;
                    if (reloaded.getRemoteAvailable() && !reloaded.getValues().isEmpty()) {
                        if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), CollectionsKt.listOf(TuplesKt.to(str4, jsonElement2)))) {
                            $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(reloaded);
                            return ai.nous.aurora.compat.Compat.m7387boximpl($result2);
                        }
                        defaultSettingsRepository2.cached = prior;
                        throw new IllegalStateException(("Save readback did not match '" + str4 + "' — last-good restored").toString());
                    }
                    defaultSettingsRepository2.cached = prior;
                    error = reloaded.getError();
                    if (error == null) {
                        error = "Save wrote but reload failed — last-good restored";
                    }
                    throw new IllegalStateException(error.toString());
                case 1:
                    JsonElement jsonElement4 = (JsonElement) this.L$2;
                    DefaultSettingsRepository defaultSettingsRepository4 = (DefaultSettingsRepository) this.L$1;
                    String str8 = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    str2 = str8;
                    defaultSettingsRepository = defaultSettingsRepository4;
                    jsonElement = jsonElement4;
                    i2 = 0;
                    String str9 = str2;
                    prior = (HermesSettingsSnapshot) $result;
                    $result = $result;
                    i = i2;
                    str = str9;
                    if (prior.getRemoteAvailable()) {
                        throw new IllegalStateException("The config bridge is offline. Reconnect before changing settings.".toString());
                    }
                    this.L$0 = str;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = jsonElement;
                    this.L$3 = prior;
                    this.label = 2;
                    objService = defaultSettingsRepository.service(this);
                    if (objService == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objService;
                    str3 = str;
                    i3 = i;
                    HermesConfigSetRequest hermesConfigSetRequest2 = new HermesConfigSetRequest(str3, jsonElement);
                    this.L$0 = str3;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = jsonElement;
                    this.L$3 = prior;
                    this.label = 3;
                    $result = ((HermesConfigApi) $result).setValue(hermesConfigSetRequest2, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    response = (Response) $result;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        str5 = null;
                        if (responseBodyErrorBody != null) {
                            detail = null;
                        } else {
                            detail = null;
                        }
                        int iCode2 = response.code();
                        if (detail != null) {
                            String it2 = detail;
                            str5 = ": " + it2;
                        }
                        if (str5 == null) {
                            str5 = "";
                        }
                        throw new IllegalStateException(("Save HTTP " + iCode2 + str5).toString());
                    }
                    this.L$0 = str3;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = jsonElement;
                    this.L$3 = prior;
                    this.label = 4;
                    $result = defaultSettingsRepository.load(true, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    jsonElement2 = jsonElement;
                    defaultSettingsRepository2 = defaultSettingsRepository;
                    str4 = str3;
                    reloaded = (HermesSettingsSnapshot) $result;
                    if (reloaded.getRemoteAvailable()) {
                        if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), CollectionsKt.listOf(TuplesKt.to(str4, jsonElement2)))) {
                            $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(reloaded);
                            return ai.nous.aurora.compat.Compat.m7387boximpl($result2);
                        }
                        defaultSettingsRepository2.cached = prior;
                        throw new IllegalStateException(("Save readback did not match '" + str4 + "' — last-good restored").toString());
                    }
                    defaultSettingsRepository2.cached = prior;
                    error = reloaded.getError();
                    if (error == null) {
                        error = "Save wrote but reload failed — last-good restored";
                    }
                    throw new IllegalStateException(error.toString());
                case 2:
                    HermesSettingsSnapshot prior2 = (HermesSettingsSnapshot) this.L$3;
                    jsonElement = (JsonElement) this.L$2;
                    defaultSettingsRepository = (DefaultSettingsRepository) this.L$1;
                    String str10 = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    str3 = str10;
                    prior = prior2;
                    i3 = 0;
                    HermesConfigSetRequest hermesConfigSetRequest3 = new HermesConfigSetRequest(str3, jsonElement);
                    this.L$0 = str3;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = jsonElement;
                    this.L$3 = prior;
                    this.label = 3;
                    $result = ((HermesConfigApi) $result).setValue(hermesConfigSetRequest3, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    response = (Response) $result;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        str5 = null;
                        if (responseBodyErrorBody != null) {
                            detail = null;
                        } else {
                            detail = null;
                        }
                        int iCode3 = response.code();
                        if (detail != null) {
                            String it3 = detail;
                            str5 = ": " + it3;
                        }
                        if (str5 == null) {
                            str5 = "";
                        }
                        throw new IllegalStateException(("Save HTTP " + iCode3 + str5).toString());
                    }
                    this.L$0 = str3;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = jsonElement;
                    this.L$3 = prior;
                    this.label = 4;
                    $result = defaultSettingsRepository.load(true, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    jsonElement2 = jsonElement;
                    defaultSettingsRepository2 = defaultSettingsRepository;
                    str4 = str3;
                    reloaded = (HermesSettingsSnapshot) $result;
                    if (reloaded.getRemoteAvailable()) {
                        if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), CollectionsKt.listOf(TuplesKt.to(str4, jsonElement2)))) {
                            $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(reloaded);
                            return ai.nous.aurora.compat.Compat.m7387boximpl($result2);
                        }
                        defaultSettingsRepository2.cached = prior;
                        throw new IllegalStateException(("Save readback did not match '" + str4 + "' — last-good restored").toString());
                    }
                    defaultSettingsRepository2.cached = prior;
                    error = reloaded.getError();
                    if (error == null) {
                        error = "Save wrote but reload failed — last-good restored";
                    }
                    throw new IllegalStateException(error.toString());
                case 3:
                    HermesSettingsSnapshot prior3 = (HermesSettingsSnapshot) this.L$3;
                    jsonElement = (JsonElement) this.L$2;
                    defaultSettingsRepository = (DefaultSettingsRepository) this.L$1;
                    String str11 = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    str3 = str11;
                    prior = prior3;
                    i3 = 0;
                    response = (Response) $result;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        str5 = null;
                        if (responseBodyErrorBody != null) {
                            detail = null;
                        } else {
                            detail = null;
                        }
                        int iCode4 = response.code();
                        if (detail != null) {
                            String it4 = detail;
                            str5 = ": " + it4;
                        }
                        if (str5 == null) {
                            str5 = "";
                        }
                        throw new IllegalStateException(("Save HTTP " + iCode4 + str5).toString());
                    }
                    this.L$0 = str3;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = jsonElement;
                    this.L$3 = prior;
                    this.label = 4;
                    $result = defaultSettingsRepository.load(true, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    jsonElement2 = jsonElement;
                    defaultSettingsRepository2 = defaultSettingsRepository;
                    str4 = str3;
                    reloaded = (HermesSettingsSnapshot) $result;
                    if (reloaded.getRemoteAvailable()) {
                        if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), CollectionsKt.listOf(TuplesKt.to(str4, jsonElement2)))) {
                            $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(reloaded);
                            return ai.nous.aurora.compat.Compat.m7387boximpl($result2);
                        }
                        defaultSettingsRepository2.cached = prior;
                        throw new IllegalStateException(("Save readback did not match '" + str4 + "' — last-good restored").toString());
                    }
                    defaultSettingsRepository2.cached = prior;
                    error = reloaded.getError();
                    if (error == null) {
                        error = "Save wrote but reload failed — last-good restored";
                    }
                    throw new IllegalStateException(error.toString());
                case 4:
                    HermesSettingsSnapshot prior4 = (HermesSettingsSnapshot) this.L$3;
                    jsonElement2 = (JsonElement) this.L$2;
                    defaultSettingsRepository2 = (DefaultSettingsRepository) this.L$1;
                    str4 = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    prior = prior4;
                    reloaded = (HermesSettingsSnapshot) $result;
                    if (reloaded.getRemoteAvailable()) {
                        if (SettingsWriteContract.INSTANCE.matches(reloaded.getValues(), CollectionsKt.listOf(TuplesKt.to(str4, jsonElement2)))) {
                            $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(reloaded);
                            return ai.nous.aurora.compat.Compat.m7387boximpl($result2);
                        }
                        defaultSettingsRepository2.cached = prior;
                        throw new IllegalStateException(("Save readback did not match '" + str4 + "' — last-good restored").toString());
                    }
                    defaultSettingsRepository2.cached = prior;
                    error = reloaded.getError();
                    if (error == null) {
                        error = "Save wrote but reload failed — last-good restored";
                    }
                    throw new IllegalStateException(error.toString());
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th2) {
        }
            return null;
    }
}
