package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.HermesConfigApi;
import ai.nous.aurora.data.remote.HermesCurrentModelDto;
import ai.nous.aurora.data.remote.HermesModelOptionsDto;
import ai.nous.aurora.data.remote.HermesModelProviderDto;
import ai.nous.aurora.domain.model.ModelProviderCatalog;
import ai.nous.aurora.domain.model.ModelSelection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.ResponseBody;
import retrofit2.Response;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lai/nous/aurora/data/repository/ModelOptionsSnapshot;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository$loadModelOptions$2", f = "SettingsRepository.kt", i = {}, l = {190, 190}, m = "invokeSuspend", n = {}, s = {})
final class DefaultSettingsRepository$loadModelOptions$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ boolean $refresh;
    private /* synthetic */ Object L$0;
    boolean Z$0;
    int label;
    final /* synthetic */ DefaultSettingsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultSettingsRepository$loadModelOptions$2(DefaultSettingsRepository defaultSettingsRepository, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = defaultSettingsRepository;
        this.$refresh = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation<?> continuation) {
        DefaultSettingsRepository$loadModelOptions$2 defaultSettingsRepository$loadModelOptions$2 = new DefaultSettingsRepository$loadModelOptions$2(this.this$0, this.$refresh, continuation);
        defaultSettingsRepository$loadModelOptions$2.L$0 = obj;
        return defaultSettingsRepository$loadModelOptions$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return invoke2(coroutineScope, (Continuation) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DefaultSettingsRepository$loadModelOptions$2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:102:0x00f2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:105:0x00d3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:108:0x017a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:110:0x0172 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:19:0x004b  */
    /* JADX WARN: Code duplicated, block: B:20:0x004d  */
    /* JADX WARN: Code duplicated, block: B:23:0x0057 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x0058  */
    /* JADX WARN: Code duplicated, block: B:27:0x0062 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x0068 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x0075  */
    /* JADX WARN: Code duplicated, block: B:35:0x007c A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x0094  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b7 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00bf A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00d9 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:48:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:54:0x0116 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:56:0x0130 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x0155 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x016b  */
    /* JADX WARN: Code duplicated, block: B:63:0x016e  */
    /* JADX WARN: Code duplicated, block: B:70:0x01a3 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x01b5 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:75:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:77:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:79:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:81:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:82:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:84:0x01d2 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:88:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:90:0x01f8 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:92:0x0204 A[Catch: all -> 0x0210, TryCatch #0 {all -> 0x0210, blocks: (B:25:0x0059, B:27:0x0062, B:29:0x0068, B:31:0x006e, B:33:0x0076, B:35:0x007c, B:38:0x0096, B:39:0x00b6, B:40:0x00b7, B:42:0x00bf, B:43:0x00d3, B:45:0x00d9, B:50:0x00f2, B:51:0x00f6, B:52:0x0110, B:54:0x0116, B:56:0x0130, B:57:0x0136, B:58:0x014f, B:60:0x0155, B:65:0x0172, B:67:0x017d, B:68:0x0193, B:70:0x01a3, B:72:0x01b5, B:84:0x01d2, B:85:0x01e1, B:89:0x01ef, B:90:0x01f8, B:91:0x0203, B:92:0x0204, B:93:0x020f, B:17:0x0047, B:21:0x004e), top: B:100:0x0047 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r6v0 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM7388constructorimpl;
        boolean z;
        int i;
        Object obj2;
        Object obj3;
        boolean z2;
        Object objModelOptions;
        Response response;
        HermesModelOptionsDto hermesModelOptionsDto;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        HermesCurrentModelDto current;
        boolean z3;
        boolean z4;
        String name;
        ArrayList arrayList4;
        boolean z5;
        boolean z6;
        ResponseBody responseBodyErrorBody;
        String strTake;
        String str;
        String strString;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = this.label;
        boolean z7 = true;
        try {
            switch (r2) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    DefaultSettingsRepository defaultSettingsRepository = this.this$0;
                    z = this.$refresh;
                    Result.Companion companion = Result.Companion;
                    i = 0;
                    this.Z$0 = z;
                    this.label = 1;
                    Object objService = defaultSettingsRepository.service(this);
                    if (objService == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj2 = obj;
                    obj3 = objService;
                    try {
                        HermesConfigApi hermesConfigApi = (HermesConfigApi) obj3;
                        if (z) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        this.label = 2;
                        objModelOptions = hermesConfigApi.modelOptions(z2, this);
                        if (objModelOptions == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        response = (Response) objModelOptions;
                        if (!response.isSuccessful()) {
                            responseBodyErrorBody = response.errorBody();
                            if (responseBodyErrorBody != null || (strString = responseBodyErrorBody.string()) == null) {
                                strTake = null;
                            } else {
                                strTake = StringsKt.take(strString, 180);
                            }
                            int iCode = response.code();
                            str = strTake != null ? ": " + strTake : null;
                            if (str == null) {
                                str = "";
                            }
                            throw new IllegalStateException(("Model inventory HTTP " + iCode + str).toString());
                        }
                        hermesModelOptionsDto = (HermesModelOptionsDto) response.body();
                        if (hermesModelOptionsDto == null) {
                            throw new IllegalStateException("The config bridge returned an empty model inventory".toString());
                        }
                        List<HermesModelProviderDto> providers = hermesModelOptionsDto.getProviders();
                        arrayList = new ArrayList();
                        for (Object obj4 : providers) {
                            if (StringsKt.isBlank(((HermesModelProviderDto) obj4).getSlug())) {
                                z6 = false;
                            } else {
                                z6 = true;
                            }
                            if (z6) {
                                arrayList.add(obj4);
                            }
                        }
                        ArrayList<HermesModelProviderDto> arrayList5 = arrayList;
                        arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
                        for (HermesModelProviderDto hermesModelProviderDto : arrayList5) {
                            String slug = hermesModelProviderDto.getSlug();
                            name = hermesModelProviderDto.getName();
                            if (StringsKt.isBlank(name)) {
                                name = hermesModelProviderDto.getSlug();
                            }
                            String str2 = name;
                            List<String> models = hermesModelProviderDto.getModels();
                            arrayList4 = new ArrayList();
                            for (Object obj5 : models) {
                                if (StringsKt.isBlank((String) obj5)) {
                                    z5 = false;
                                } else {
                                    z5 = true;
                                }
                                if (z5) {
                                    ArrayList arrayList6 = arrayList4;
                                    arrayList6.add(obj5);
                                    arrayList4 = arrayList6;
                                }
                            }
                            arrayList2.add(new ModelProviderCatalog(slug, str2, CollectionsKt.distinct(arrayList4)));
                        }
                        arrayList3 = arrayList2;
                        if (arrayList3.isEmpty()) {
                            throw new IllegalStateException("The configured provider inventory is empty".toString());
                        }
                        current = hermesModelOptionsDto.getCurrent();
                        if (StringsKt.isBlank(current.getProvider())) {
                            if (StringsKt.isBlank(current.getModel())) {
                                z4 = false;
                            } else {
                                z4 = true;
                            }
                            if (z4) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                        } else {
                            z3 = true;
                        }
                        if (!z3) {
                            current = null;
                        }
                        ModelSelection modelSelection = current != null ? new ModelSelection(current.getProvider(), current.getModel()) : null;
                        if (!hermesModelOptionsDto.getCurrent().getValid()) {
                            z7 = false;
                        }
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(new ModelOptionsSnapshot(arrayList3, modelSelection, z7));
                        return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.Companion;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                    }
                    break;
                case 1:
                    obj3 = obj;
                    i = 0;
                    boolean z8 = this.Z$0;
                    ResultKt.throwOnFailure(obj3);
                    z = z8;
                    obj2 = obj3;
                    HermesConfigApi hermesConfigApi2 = (HermesConfigApi) obj3;
                    if (z) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    this.label = 2;
                    objModelOptions = hermesConfigApi2.modelOptions(z2, this);
                    if (objModelOptions == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    response = (Response) objModelOptions;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        if (responseBodyErrorBody != null) {
                            strTake = null;
                        } else {
                            strTake = null;
                        }
                        int iCode2 = response.code();
                        if (strTake != null) {
                        }
                        str = strTake != null ? ": " + strTake : null;
                        if (str == null) {
                            str = "";
                        }
                        throw new IllegalStateException(("Model inventory HTTP " + iCode2 + str).toString());
                    }
                    hermesModelOptionsDto = (HermesModelOptionsDto) response.body();
                    if (hermesModelOptionsDto == null) {
                        throw new IllegalStateException("The config bridge returned an empty model inventory".toString());
                    }
                    List<HermesModelProviderDto> providers2 = hermesModelOptionsDto.getProviders();
                    arrayList = new ArrayList();
                    java.util.Iterator<HermesModelProviderDto> r11 = providers2.iterator();
                    while (r11.hasNext()) {
                        Object obj4 = r11.next();
                        if (StringsKt.isBlank(((HermesModelProviderDto) obj4).getSlug())) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (z6) {
                            arrayList.add(obj4);
                        }
                    }
                    ArrayList<HermesModelProviderDto> arrayList7 = arrayList;
                    arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList7, 10));
                    for (HermesModelProviderDto hermesModelProviderDto : arrayList7) {
                        String slug2 = hermesModelProviderDto.getSlug();
                        name = hermesModelProviderDto.getName();
                        if (StringsKt.isBlank(name)) {
                            name = hermesModelProviderDto.getSlug();
                        }
                        String str3 = name;
                        List<String> models2 = hermesModelProviderDto.getModels();
                        arrayList4 = new ArrayList();
                        java.util.Iterator<String> r17 = models2.iterator();
                        while (r17.hasNext()) {
                            Object obj5 = r17.next();
                            if (StringsKt.isBlank((String) obj5)) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            if (z5) {
                                ArrayList arrayList8 = arrayList4;
                                arrayList8.add(obj5);
                                arrayList4 = arrayList8;
                            }
                        }
                        arrayList2.add(new ModelProviderCatalog(slug2, str3, CollectionsKt.distinct(arrayList4)));
                    }
                    arrayList3 = arrayList2;
                    if (arrayList3.isEmpty()) {
                        throw new IllegalStateException("The configured provider inventory is empty".toString());
                    }
                    current = hermesModelOptionsDto.getCurrent();
                    if (StringsKt.isBlank(current.getProvider())) {
                        z3 = true;
                    } else {
                        if (StringsKt.isBlank(current.getModel())) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (z4) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                    }
                    if (!z3) {
                        current = null;
                    }
                    if (current != null) {
                    }
                    if (!hermesModelOptionsDto.getCurrent().getValid()) {
                        z7 = false;
                    }
                    ModelSelection modelSelection3 = current != null ? new ModelSelection(current.getProvider(), current.getModel()) : null;
                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(new ModelOptionsSnapshot(arrayList3, modelSelection3, z7));
                    return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                case 2:
                    objModelOptions = obj;
                    ResultKt.throwOnFailure(objModelOptions);
                    response = (Response) objModelOptions;
                    if (!response.isSuccessful()) {
                        responseBodyErrorBody = response.errorBody();
                        if (responseBodyErrorBody != null) {
                            strTake = null;
                        } else {
                            strTake = null;
                        }
                        int iCode3 = response.code();
                        if (strTake != null) {
                        }
                        str = strTake != null ? ": " + strTake : null;
                        if (str == null) {
                            str = "";
                        }
                        throw new IllegalStateException(("Model inventory HTTP " + iCode3 + str).toString());
                    }
                    hermesModelOptionsDto = (HermesModelOptionsDto) response.body();
                    if (hermesModelOptionsDto == null) {
                        throw new IllegalStateException("The config bridge returned an empty model inventory".toString());
                    }
                    List<HermesModelProviderDto> providers3 = hermesModelOptionsDto.getProviders();
                    arrayList = new ArrayList();
                    r11 = providers3.iterator();
                    while (r11.hasNext()) {
                        Object obj4 = r11.next();
                        if (StringsKt.isBlank(((HermesModelProviderDto) obj4).getSlug())) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (z6) {
                            arrayList.add(obj4);
                        }
                    }
                    ArrayList<HermesModelProviderDto> arrayList9 = arrayList;
                    arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList9, 10));
                    for (HermesModelProviderDto hermesModelProviderDto : arrayList9) {
                        String slug3 = hermesModelProviderDto.getSlug();
                        name = hermesModelProviderDto.getName();
                        if (StringsKt.isBlank(name)) {
                            name = hermesModelProviderDto.getSlug();
                        }
                        String str4 = name;
                        List<String> models3 = hermesModelProviderDto.getModels();
                        arrayList4 = new ArrayList();
                        for (Object obj5 : models3) {
                            if (StringsKt.isBlank((String) obj5)) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            if (z5) {
                                ArrayList arrayList10 = arrayList4;
                                arrayList10.add(obj5);
                                arrayList4 = arrayList10;
                            }
                        }
                        arrayList2.add(new ModelProviderCatalog(slug3, str4, CollectionsKt.distinct(arrayList4)));
                    }
                    arrayList3 = arrayList2;
                    if (arrayList3.isEmpty()) {
                        throw new IllegalStateException("The configured provider inventory is empty".toString());
                    }
                    current = hermesModelOptionsDto.getCurrent();
                    if (StringsKt.isBlank(current.getProvider())) {
                        z3 = true;
                    } else {
                        if (StringsKt.isBlank(current.getModel())) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (z4) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                    }
                    if (!z3) {
                        current = null;
                    }
                    if (current != null) {
                    }
                    if (!hermesModelOptionsDto.getCurrent().getValid()) {
                        z7 = false;
                    }
                    ModelSelection modelSelection = null;
                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(new ModelOptionsSnapshot(arrayList3, modelSelection, z7));
                    return ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th2) {
            Result.Companion companion99 = Result.Companion;
            return ai.nous.aurora.compat.Compat.m7387boximpl(ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2)));
        }
            return objM7388constructorimpl;
    }
}
