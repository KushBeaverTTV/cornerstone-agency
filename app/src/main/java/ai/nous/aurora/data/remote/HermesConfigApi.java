package ai.nous.aurora.data.remote;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.serialization.json.JsonObject;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H§@¢\u0006\u0002\u0010\u0005J(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0003\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\tH§@¢\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0003\u0010\u000e\u001a\u00020\tH§@¢\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0001\u0010\u0012\u001a\u00020\u0013H§@¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0001\u0010\u0012\u001a\u00020\u0016H§@¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0003H§@¢\u0006\u0002\u0010\u0005J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\b\b\u0001\u0010\u001b\u001a\u00020\u0019H§@¢\u0006\u0002\u0010\u001cJ\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\b\b\u0001\u0010\u001b\u001a\u00020\u001fH§@¢\u0006\u0002\u0010 ¨\u0006!"}, d2 = {"Lai/nous/aurora/data/remote/HermesConfigApi;", "", "schema", "Lretrofit2/Response;", "Lai/nous/aurora/data/remote/HermesConfigSchemaDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "config", "Lai/nous/aurora/data/remote/HermesConfigResponseDto;", "redact", "", "resolved", "(ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "modelOptions", "Lai/nous/aurora/data/remote/HermesModelOptionsDto;", "refresh", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setValue", "Lkotlinx/serialization/json/JsonObject;", "request", "Lai/nous/aurora/data/remote/HermesConfigSetRequest;", "(Lai/nous/aurora/data/remote/HermesConfigSetRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setValues", "Lai/nous/aurora/data/remote/HermesConfigSetBatchRequest;", "(Lai/nous/aurora/data/remote/HermesConfigSetBatchRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moa", "Lai/nous/aurora/data/remote/MoaConfigDto;", "putMoa", "body", "(Lai/nous/aurora/data/remote/MoaConfigDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encodeMoa", "Lai/nous/aurora/data/remote/MoaEncodeResponse;", "Lai/nous/aurora/data/remote/MoaEncodeRequest;", "(Lai/nous/aurora/data/remote/MoaEncodeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface HermesConfigApi {
    @GET("/v1/hermes/config")
    Object config(@Query("redact") boolean z, @Query("resolved") boolean z2, Continuation continuation);

    @POST("/v1/hermes/moa/encode")
    Object encodeMoa(@Body MoaEncodeRequest moaEncodeRequest, Continuation continuation);

    @GET("/v1/hermes/moa")
    Object moa(Continuation continuation);

    @GET("/v1/hermes/model-options")
    Object modelOptions(@Query("refresh") boolean z, Continuation continuation);

    @PUT("/v1/hermes/moa")
    Object putMoa(@Body MoaConfigDto moaConfigDto, Continuation continuation);

    @GET("/v1/hermes/schema")
    Object schema(Continuation continuation);

    @PUT("/v1/hermes/config/set")
    Object setValue(@Body HermesConfigSetRequest hermesConfigSetRequest, Continuation continuation);

    @PUT("/v1/hermes/config/set-batch")
    Object setValues(@Body HermesConfigSetBatchRequest hermesConfigSetBatchRequest, Continuation continuation);

    /* JADX INFO: compiled from: HermesConfigApi.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object config$default(HermesConfigApi hermesConfigApi, boolean z, boolean z2, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: config");
            }
            if ((i & 1) != 0) {
                z = true;
            }
            if ((i & 2) != 0) {
                z2 = true;
            }
            return hermesConfigApi.config(z, z2, continuation);
        }

        public static /* synthetic */ Object modelOptions$default(HermesConfigApi hermesConfigApi, boolean z, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: modelOptions");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return hermesConfigApi.modelOptions(z, continuation);
        }
    }
}
