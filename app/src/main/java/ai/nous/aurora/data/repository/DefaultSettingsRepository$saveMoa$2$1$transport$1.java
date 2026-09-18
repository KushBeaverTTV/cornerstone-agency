package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.HermesConfigApi;
import ai.nous.aurora.data.remote.MoaConfigDto;
import ai.nous.aurora.presentation.theme.AuroraMotion;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import retrofit2.Response;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0096@¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"ai/nous/aurora/data/repository/DefaultSettingsRepository$saveMoa$2$1$transport$1", "Lai/nous/aurora/data/repository/MoaWriteTransport;", "get", "Lai/nous/aurora/data/repository/MoaTransportResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "put", "body", "Lai/nous/aurora/data/remote/MoaConfigDto;", "(Lai/nous/aurora/data/remote/MoaConfigDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultSettingsRepository$saveMoa$2$1$transport$1 implements MoaWriteTransport {
    final /* synthetic */ HermesConfigApi $currentService;

    DefaultSettingsRepository$saveMoa$2$1$transport$1(HermesConfigApi $currentService) {
        this.$currentService = $currentService;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // ai.nous.aurora.data.repository.MoaWriteTransport
    public Object get(Continuation continuation) {
        DefaultSettingsRepository$saveMoa$2$1$transport$1$get$1 defaultSettingsRepository$saveMoa$2$1$transport$1$get$1;
        Object objMoa;
        ResponseBody responseBodyErrorBody;
        String strString;
        if (continuation instanceof DefaultSettingsRepository$saveMoa$2$1$transport$1$get$1) {
            defaultSettingsRepository$saveMoa$2$1$transport$1$get$1 = (DefaultSettingsRepository$saveMoa$2$1$transport$1$get$1) continuation;
            if ((defaultSettingsRepository$saveMoa$2$1$transport$1$get$1.label & Integer.MIN_VALUE) != 0) {
                defaultSettingsRepository$saveMoa$2$1$transport$1$get$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSettingsRepository$saveMoa$2$1$transport$1$get$1 = new DefaultSettingsRepository$saveMoa$2$1$transport$1$get$1(this, continuation);
            }
        } else {
            defaultSettingsRepository$saveMoa$2$1$transport$1$get$1 = new DefaultSettingsRepository$saveMoa$2$1$transport$1$get$1(this, continuation);
        }
        Object $result = defaultSettingsRepository$saveMoa$2$1$transport$1$get$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSettingsRepository$saveMoa$2$1$transport$1$get$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                HermesConfigApi hermesConfigApi = this.$currentService;
                defaultSettingsRepository$saveMoa$2$1$transport$1$get$1.label = 1;
                objMoa = hermesConfigApi.moa(defaultSettingsRepository$saveMoa$2$1$transport$1$get$1);
                if (objMoa == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objMoa = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Response response = (Response) objMoa;
        boolean zIsSuccessful = response.isSuccessful();
        int iCode = response.code();
        MoaConfigDto moaConfigDto = (MoaConfigDto) response.body();
        String strTake = null;
        if (!response.isSuccessful() && (responseBodyErrorBody = response.errorBody()) != null) {
            try {
                strString = responseBodyErrorBody.string();
            } catch (java.io.IOException e) {
                throw ai.nous.aurora.compat.Compat.sneakyThrow(e);
            }
            if (strString != null) {
                strTake = StringsKt.take(strString, AuroraMotion.TAB_MS);
            }
        }
        return new MoaTransportResponse(zIsSuccessful, iCode, moaConfigDto, strTake);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // ai.nous.aurora.data.repository.MoaWriteTransport
    public Object put(MoaConfigDto body, Continuation continuation) {
        DefaultSettingsRepository$saveMoa$2$1$transport$1$put$1 defaultSettingsRepository$saveMoa$2$1$transport$1$put$1;
        Object objPutMoa;
        ResponseBody responseBodyErrorBody;
        String strString;
        if (continuation instanceof DefaultSettingsRepository$saveMoa$2$1$transport$1$put$1) {
            defaultSettingsRepository$saveMoa$2$1$transport$1$put$1 = (DefaultSettingsRepository$saveMoa$2$1$transport$1$put$1) continuation;
            if ((defaultSettingsRepository$saveMoa$2$1$transport$1$put$1.label & Integer.MIN_VALUE) != 0) {
                defaultSettingsRepository$saveMoa$2$1$transport$1$put$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSettingsRepository$saveMoa$2$1$transport$1$put$1 = new DefaultSettingsRepository$saveMoa$2$1$transport$1$put$1(this, continuation);
            }
        } else {
            defaultSettingsRepository$saveMoa$2$1$transport$1$put$1 = new DefaultSettingsRepository$saveMoa$2$1$transport$1$put$1(this, continuation);
        }
        Object $result = defaultSettingsRepository$saveMoa$2$1$transport$1$put$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSettingsRepository$saveMoa$2$1$transport$1$put$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                HermesConfigApi hermesConfigApi = this.$currentService;
                defaultSettingsRepository$saveMoa$2$1$transport$1$put$1.label = 1;
                objPutMoa = hermesConfigApi.putMoa(body, defaultSettingsRepository$saveMoa$2$1$transport$1$put$1);
                if (objPutMoa == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objPutMoa = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Response response = (Response) objPutMoa;
        boolean zIsSuccessful = response.isSuccessful();
        int iCode = response.code();
        MoaConfigDto moaConfigDto = (MoaConfigDto) response.body();
        String strTake = null;
        if (!response.isSuccessful() && (responseBodyErrorBody = response.errorBody()) != null) {
            try {
                strString = responseBodyErrorBody.string();
            } catch (java.io.IOException e) {
                throw ai.nous.aurora.compat.Compat.sneakyThrow(e);
            }
            if (strString != null) {
                strTake = StringsKt.take(strString, AuroraMotion.TAB_MS);
            }
        }
        return new MoaTransportResponse(zIsSuccessful, iCode, moaConfigDto, strTake);
    }
}
