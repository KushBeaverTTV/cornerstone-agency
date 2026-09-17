package ai.nous.aurora.data.remote;

import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/* JADX INFO: compiled from: HermesConfigService.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J%\u0010\u0002\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0005\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\u00060\u00040\u0003H§@¢\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H§@¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0003\u0010\f\u001a\u00020\rH§@¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0003H§@¢\u0006\u0002\u0010\u0007J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H§@¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0003H§@¢\u0006\u0002\u0010\u0007¨\u0006\u0018"}, d2 = {"Lai/nous/aurora/data/remote/HermesConfigService;", "", "health", "Lretrofit2/Response;", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "schema", "Lai/nous/aurora/data/remote/ConfigSchemaResponse;", "config", "Lai/nous/aurora/data/remote/ConfigResponse;", "redact", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "categories", "Lai/nous/aurora/data/remote/CategoriesResponse;", "set", "Lai/nous/aurora/data/remote/ConfigSetResponse;", "body", "Lai/nous/aurora/data/remote/ConfigSetRequest;", "(Lai/nous/aurora/data/remote/ConfigSetRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "model", "Lai/nous/aurora/data/remote/ModelInfoResponse;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface HermesConfigService {
    @GET("/v1/hermes/categories")
    Object categories(Continuation continuation);

    @GET("/v1/hermes/config")
    Object config(@Query("redact") boolean z, Continuation continuation);

    @GET("/health")
    Object health(Continuation continuation);

    @GET("/v1/hermes/model")
    Object model(Continuation continuation);

    @GET("/v1/hermes/schema")
    Object schema(Continuation continuation);

    @PUT("/v1/hermes/config/set")
    Object set(@Body ConfigSetRequest configSetRequest, Continuation continuation);

    /* JADX INFO: compiled from: HermesConfigService.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object config$default(HermesConfigService hermesConfigService, boolean z, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: config");
            }
            if ((i & 1) != 0) {
                z = true;
            }
            return hermesConfigService.config(z, continuation);
        }
    }
}
