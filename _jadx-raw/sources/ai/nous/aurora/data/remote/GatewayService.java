package ai.nous.aurora.data.remote;

import ai.nous.aurora.domain.model.OutboxExactlyOnce;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* JADX INFO: compiled from: GatewayService.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H§@¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H§@¢\u0006\u0002\u0010\u0005J(\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0003\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\f\u001a\u00020\u000bH§@¢\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0003\u0010\u0010\u001a\u00020\u0011H§@¢\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u0016H§@¢\u0006\u0002\u0010\u0017J4\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0010\u001a\u00020\u001a2\n\b\u0003\u0010\u001b\u001a\u0004\u0018\u00010\u0016H§@¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, d2 = {"Lai/nous/aurora/data/remote/GatewayService;", "", "health", "Lretrofit2/Response;", "Lai/nous/aurora/data/remote/HealthResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "models", "Lai/nous/aurora/data/remote/ModelsResponse;", "listSessions", "Lai/nous/aurora/data/remote/SessionListResponse;", "limit", "", "offset", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSession", "Lai/nous/aurora/data/remote/SessionEnvelope;", "body", "Lai/nous/aurora/data/remote/CreateSessionRequest;", "(Lai/nous/aurora/data/remote/CreateSessionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sessionMessages", "Lai/nous/aurora/data/remote/MessageListResponse;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sessionChat", "Lai/nous/aurora/data/remote/SessionChatResponse;", "Lai/nous/aurora/data/remote/SessionChatRequest;", "idempotencyKey", "(Ljava/lang/String;Lai/nous/aurora/data/remote/SessionChatRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface GatewayService {
    @POST("/api/sessions")
    Object createSession(@Body CreateSessionRequest createSessionRequest, Continuation<? super Response<SessionEnvelope>> continuation);

    @GET("/health")
    Object health(Continuation<? super Response<HealthResponse>> continuation);

    @GET("/api/sessions")
    Object listSessions(@Query("limit") int i, @Query("offset") int i2, Continuation<? super Response<SessionListResponse>> continuation);

    @GET("/v1/models")
    Object models(Continuation<? super Response<ModelsResponse>> continuation);

    @POST("/api/sessions/{id}/chat")
    Object sessionChat(@Path("id") String str, @Body SessionChatRequest sessionChatRequest, @Header(OutboxExactlyOnce.IDEMPOTENCY_HEADER) String str2, Continuation<? super Response<SessionChatResponse>> continuation);

    @GET("/api/sessions/{id}/messages")
    Object sessionMessages(@Path("id") String str, Continuation<? super Response<MessageListResponse>> continuation);

    /* JADX INFO: compiled from: GatewayService.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object listSessions$default(GatewayService gatewayService, int i, int i2, Continuation continuation, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listSessions");
            }
            if ((i3 & 1) != 0) {
                i = 50;
            }
            if ((i3 & 2) != 0) {
                i2 = 0;
            }
            return gatewayService.listSessions(i, i2, continuation);
        }

        public static /* synthetic */ Object createSession$default(GatewayService gatewayService, CreateSessionRequest createSessionRequest, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createSession");
            }
            if ((i & 1) != 0) {
                createSessionRequest = new CreateSessionRequest((String) null, (String) null, 3, (DefaultConstructorMarker) null);
            }
            return gatewayService.createSession(createSessionRequest, continuation);
        }

        public static /* synthetic */ Object sessionChat$default(GatewayService gatewayService, String str, SessionChatRequest sessionChatRequest, String str2, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sessionChat");
            }
            if ((i & 4) != 0) {
                str2 = null;
            }
            return gatewayService.sessionChat(str, sessionChatRequest, str2, continuation);
        }
    }
}
