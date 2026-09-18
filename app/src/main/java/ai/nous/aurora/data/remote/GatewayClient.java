package ai.nous.aurora.data.remote;

import ai.nous.aurora.domain.model.AuroraMessage;
import ai.nous.aurora.domain.model.AuroraSession;
import ai.nous.aurora.domain.model.ConnectionState;
import androidx.core.app.NotificationCompat;
import com.jakewharton.retrofit2.converter.kotlinx.serialization.KotlinSerializationConverterFactory;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.internal.ws.WebSocketProtocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/* JADX INFO: compiled from: GatewayClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u0018H\u0002J\u0012\u0010 \u001a\u00020\u001c2\b\b\u0002\u0010!\u001a\u00020\u0003H\u0002J\u000e\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0003J\u0006\u0010$\u001a\u00020\u001cJ\u0006\u0010%\u001a\u00020\u0003J\u0006\u0010&\u001a\u00020\u001cJ\u000e\u0010'\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010(J&\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001c0*2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0086@¢\u0006\u0004\b+\u0010,J\u001c\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180.0*H\u0086@¢\u0006\u0004\b/\u0010(J\u001c\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002010.0*H\u0086@¢\u0006\u0004\b2\u0010(J\"\u00103\u001a\b\u0012\u0004\u0012\u0002010*2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0018H\u0086@¢\u0006\u0004\b5\u00106J$\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080.0*2\u0006\u00109\u001a\u00020\u0018H\u0086@¢\u0006\u0004\b:\u00106JJ\u0010;\u001a\b\u0012\u0004\u0012\u0002080*2\u0006\u00109\u001a\u00020\u00182\u0006\u0010<\u001a\u00020\u00182\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u0018H\u0086@¢\u0006\u0004\b@\u0010AJ\b\u0010B\u001a\u00020\u0016H\u0002J\f\u0010C\u001a\u000201*\u00020DH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lai/nous/aurora/data/remote/GatewayClient;", "", "isDebug", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "<init>", "(ZLkotlinx/coroutines/CoroutineDispatcher;)V", "json", "Lkotlinx/serialization/json/Json;", "_connection", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lai/nous/aurora/domain/model/ConnectionState;", "connection", "Lkotlinx/coroutines/flow/StateFlow;", "getConnection", "()Lkotlinx/coroutines/flow/StateFlow;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "healthJob", "Lkotlinx/coroutines/Job;", NotificationCompat.CATEGORY_SERVICE, "Lai/nous/aurora/data/remote/GatewayService;", "baseUrl", "", "apiKey", "healthChecksEnabled", "configure", "", "createService", "url", "key", "startHealthCheck", "checkImmediately", "setHealthChecksEnabled", "enabled", "disconnect", "isConfigured", "markConnecting", "checkConnection", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connect", "Lkotlin/Result;", "connect-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listModels", "", "listModels-IoAF18A", "getSessions", "Lai/nous/aurora/domain/model/AuroraSession;", "getSessions-IoAF18A", "createSession", "title", "createSession-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMessages", "Lai/nous/aurora/domain/model/AuroraMessage;", "sessionId", "getMessages-gIAlu-s", "sendMessage", "content", "model", "clientMsgId", "ownerKey", "sendMessage-hUnOzRk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requireService", "toModel", "Lai/nous/aurora/data/remote/HermesSession;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class GatewayClient {
    public static final int $stable = 8;
    public final MutableStateFlow<ConnectionState> _connection;
    public String apiKey;
    public String baseUrl;
    public final StateFlow<ConnectionState> connection;
    public final CoroutineDispatcher dispatcher;
    public volatile boolean healthChecksEnabled;
    public Job healthJob;
    public final boolean isDebug;
    public final Json json;
    public final CoroutineScope scope;
    public GatewayService service;

    /* JADX INFO: renamed from: ai.nous.aurora.data.remote.GatewayClient$checkConnection$1, reason: invalid class name */
    /* JADX INFO: compiled from: GatewayClient.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.remote.GatewayClient", f = "GatewayClient.kt", i = {0}, l = {WebSocketProtocol.PAYLOAD_SHORT}, m = "checkConnection", n = {"this"}, s = {"L$0"})
    final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GatewayClient.this.checkConnection(this);
        }
    }

    public GatewayClient(boolean isDebug, CoroutineDispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        this.isDebug = isDebug;
        this.dispatcher = dispatcher;
        this.json = ai.nous.aurora.compat.Compat.d_JsonKt_Json_4(null, new Function1() { // from class: ai.nous.aurora.data.remote.GatewayClient$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GatewayClient.json$lambda$0((JsonBuilder) obj);
            }
        }, 1, null);
        this._connection = StateFlowKt.MutableStateFlow(ConnectionState.Connecting.INSTANCE);
        this.connection = FlowKt.asStateFlow(this._connection);
        this.scope = CoroutineScopeKt.CoroutineScope(ai.nous.aurora.compat.Compat.d_SupervisorKt_SupervisorJob_3((Job) null, 1, (Object) null).plus(this.dispatcher));
        this.healthChecksEnabled = true;
    }

    public /* synthetic */ GatewayClient(boolean z, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? Dispatchers.getIO() : coroutineDispatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        Json.setLenient(true);
        Json.setExplicitNulls(false);
        return Unit.INSTANCE;
    }

    public final StateFlow<ConnectionState> getConnection() {
        return this.connection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void configure(String baseUrl, String apiKey) {
        this.baseUrl = StringsKt.trimEnd(baseUrl, '/') + "/";
        this.apiKey = StringsKt.trim((CharSequence) apiKey).toString();
        String str = this.baseUrl;
        Intrinsics.checkNotNull(str);
        String str2 = this.apiKey;
        Intrinsics.checkNotNull(str2);
        this.service = createService(str, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final GatewayService createService(String url, final String key) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        httpLoggingInterceptor.level(this.isDebug ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
        Object objCreate = new Retrofit.Builder().baseUrl(url).client(new OkHttpClient.Builder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(300L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).addInterceptor(new Interceptor() { // from class: ai.nous.aurora.data.remote.GatewayClient$$ExternalSyntheticLambda1
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) throws IOException {
                return GatewayClient.createService$lambda$2(key, chain);
            }
        }).addInterceptor(httpLoggingInterceptor).build()).addConverterFactory(KotlinSerializationConverterFactory.create(this.json, MediaType.Companion.get("application/json"))).build().create(GatewayService.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (GatewayService) objCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Response createService$lambda$2(String $key, Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(chain.request().newBuilder().header("Authorization", "Bearer " + $key).header("Content-Type", "application/json").build());
    }

    static /* synthetic */ void startHealthCheck$default(GatewayClient gatewayClient, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        gatewayClient.startHealthCheck(z);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.remote.GatewayClient$startHealthCheck$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GatewayClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.remote.GatewayClient$startHealthCheck$1", f = "GatewayClient.kt", i = {0, 1, 2}, l = {84, 86, 87}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$0"})
    static final class C02101 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ boolean $checkImmediately;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ GatewayClient this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02101(boolean z, GatewayClient gatewayClient, Continuation continuation) {
            super(2, continuation);
            this.$checkImmediately = z;
            this.this$0 = gatewayClient;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C02101 c02101 = new C02101(this.$checkImmediately, this.this$0, continuation);
            c02101.L$0 = obj;
            return c02101;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02101)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:18:0x0050  */
        /* JADX WARN: Code duplicated, block: B:20:0x0060 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:23:0x006f A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006d -> B:16:0x004a). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 20000(0x4e20, double:9.8813E-320)
                switch(r1) {
                    case 0: goto L2d;
                    case 1: goto L25;
                    case 2: goto L1c;
                    case 3: goto L13;
                    default: goto Lb;
                }
            Lb:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L13:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r9)
                r4 = r8
                goto L70
            L1c:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r9)
                r4 = r8
                goto L61
            L25:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r9)
                goto L47
            L2d:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                boolean r4 = r8.$checkImmediately
                if (r4 != 0) goto L49
                r4 = r8
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r8.L$0 = r1
                r5 = 1
                r8.label = r5
                java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r2, r4)
                if (r4 != r0) goto L47
                return r0
            L47:
                r4 = r8
                goto L4a
            L49:
                r4 = r8
            L4a:
                boolean r5 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)
                if (r5 == 0) goto L71
                ai.nous.aurora.data.remote.GatewayClient r5 = r4.this$0
                r6 = r4
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r4.L$0 = r1
                r7 = 2
                r4.label = r7
                java.lang.Object r5 = r5.checkConnection(r6)
                if (r5 != r0) goto L61
                return r0
            L61:
                r5 = r4
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r4.L$0 = r1
                r6 = 3
                r4.label = r6
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r2, r5)
                if (r5 != r0) goto L70
                return r0
            L70:
                goto L4a
            L71:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.data.remote.C02101.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startHealthCheck(boolean checkImmediately) {
        Job job = this.healthJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.healthJob = ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(this.scope, null, null, new C02101(checkImmediately, this, null), 3, null);
    }

    public final void setHealthChecksEnabled(boolean enabled) {
        if (this.healthChecksEnabled == enabled) {
            return;
        }
        this.healthChecksEnabled = enabled;
        if (enabled && this.service != null) {
            startHealthCheck$default(this, false, 1, null);
            return;
        }
        Job job = this.healthJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.healthJob = null;
    }

    public final void disconnect() {
        Job job = this.healthJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.healthJob = null;
        this.service = null;
        this.baseUrl = null;
        this.apiKey = null;
        this._connection.setValue(new ConnectionState.Error("Signed out"));
    }

    public final boolean isConfigured() {
        return this.service != null;
    }

    public final void markConnecting() {
        this._connection.setValue(ConnectionState.Connecting.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x006c A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x0030, B:26:0x0061, B:28:0x006c, B:30:0x008f, B:29:0x0071), top: B:42:0x0030 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x0071 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x0030, B:26:0x0061, B:28:0x006c, B:30:0x008f, B:29:0x0071), top: B:42:0x0030 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object checkConnection(Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Throwable t;
        GatewayClient gatewayClient;
        Object objHealth;
        String message;
        retrofit2.Response r;
        ConnectionState error;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object $result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                GatewayService svc = this.service;
                if (svc == null) {
                    GatewayClient $this$checkConnection_u24lambda_u243 = this;
                    $this$checkConnection_u24lambda_u243._connection.setValue(new ConnectionState.Error("Not configured"));
                    return Unit.INSTANCE;
                }
                try {
                    anonymousClass1.L$0 = this;
                    anonymousClass1.label = 1;
                    objHealth = svc.health(anonymousClass1);
                    if (objHealth == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    gatewayClient = this;
                    r = (retrofit2.Response) objHealth;
                    MutableStateFlow<ConnectionState> mutableStateFlow = gatewayClient._connection;
                    if (r.isSuccessful()) {
                        error = ConnectionState.Online.INSTANCE;
                    } else {
                        error = new ConnectionState.Error("Health HTTP " + r.code());
                    }
                    mutableStateFlow.setValue(error);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    t = th;
                    gatewayClient = this;
                    MutableStateFlow<ConnectionState> mutableStateFlow2 = gatewayClient._connection;
                    message = t.getMessage();
                    if (message == null) {
                        message = "Offline";
                    }
                    mutableStateFlow2.setValue(new ConnectionState.Error(message));
                }
                break;
            case 1:
                gatewayClient = (GatewayClient) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objHealth = $result;
                    r = (retrofit2.Response) objHealth;
                    MutableStateFlow<ConnectionState> mutableStateFlow3 = gatewayClient._connection;
                    if (r.isSuccessful()) {
                        error = ConnectionState.Online.INSTANCE;
                    } else {
                        error = new ConnectionState.Error("Health HTTP " + r.code());
                    }
                    mutableStateFlow3.setValue(error);
                    break;
                } catch (Throwable th2) {
                    t = th2;
                    MutableStateFlow<ConnectionState> mutableStateFlow4 = gatewayClient._connection;
                    message = t.getMessage();
                    if (message == null) {
                        message = "Offline";
                    }
                    mutableStateFlow4.setValue(new ConnectionState.Error(message));
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: connect-0E7RQCE, reason: not valid java name */
    public final Object m41connect0E7RQCE(String baseUrl, String apiKey, Continuation continuation) {
        GatewayClient$connect$1 gatewayClient$connect$1;
        Object objWithContext;
        if (continuation instanceof GatewayClient$connect$1) {
            gatewayClient$connect$1 = (GatewayClient$connect$1) continuation;
            if ((gatewayClient$connect$1.label & Integer.MIN_VALUE) != 0) {
                gatewayClient$connect$1.label -= Integer.MIN_VALUE;
            } else {
                gatewayClient$connect$1 = new GatewayClient$connect$1(this, continuation);
            }
        } else {
            gatewayClient$connect$1 = new GatewayClient$connect$1(this, continuation);
        }
        Object $result = gatewayClient$connect$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (gatewayClient$connect$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                GatewayClient$connect$2 gatewayClient$connect$2 = new GatewayClient$connect$2(this, baseUrl, apiKey, null);
                gatewayClient$connect$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, gatewayClient$connect$2, gatewayClient$connect$1);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ai.nous.aurora.compat.Compat.resultUnbox(objWithContext);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: listModels-IoAF18A, reason: not valid java name */
    public final Object m45listModelsIoAF18A(Continuation continuation) {
        GatewayClient$listModels$1 gatewayClient$listModels$1;
        Object objWithContext;
        if (continuation instanceof GatewayClient$listModels$1) {
            gatewayClient$listModels$1 = (GatewayClient$listModels$1) continuation;
            if ((gatewayClient$listModels$1.label & Integer.MIN_VALUE) != 0) {
                gatewayClient$listModels$1.label -= Integer.MIN_VALUE;
            } else {
                gatewayClient$listModels$1 = new GatewayClient$listModels$1(this, continuation);
            }
        } else {
            gatewayClient$listModels$1 = new GatewayClient$listModels$1(this, continuation);
        }
        Object $result = gatewayClient$listModels$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (gatewayClient$listModels$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                GatewayClient$listModels$2 gatewayClient$listModels$2 = new GatewayClient$listModels$2(this, null);
                gatewayClient$listModels$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, gatewayClient$listModels$2, gatewayClient$listModels$1);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ai.nous.aurora.compat.Compat.resultUnbox(objWithContext);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: getSessions-IoAF18A, reason: not valid java name */
    public final Object m44getSessionsIoAF18A(Continuation continuation) {
        GatewayClient$getSessions$1 gatewayClient$getSessions$1;
        Object objWithContext;
        if (continuation instanceof GatewayClient$getSessions$1) {
            gatewayClient$getSessions$1 = (GatewayClient$getSessions$1) continuation;
            if ((gatewayClient$getSessions$1.label & Integer.MIN_VALUE) != 0) {
                gatewayClient$getSessions$1.label -= Integer.MIN_VALUE;
            } else {
                gatewayClient$getSessions$1 = new GatewayClient$getSessions$1(this, continuation);
            }
        } else {
            gatewayClient$getSessions$1 = new GatewayClient$getSessions$1(this, continuation);
        }
        Object $result = gatewayClient$getSessions$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (gatewayClient$getSessions$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                GatewayClient$getSessions$2 gatewayClient$getSessions$2 = new GatewayClient$getSessions$2(this, null);
                gatewayClient$getSessions$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, gatewayClient$getSessions$2, gatewayClient$getSessions$1);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ai.nous.aurora.compat.Compat.resultUnbox(objWithContext);
    }

    /* JADX INFO: renamed from: createSession-gIAlu-s$default, reason: not valid java name */
    public static /* synthetic */ Object m39createSessiongIAlus$default(GatewayClient gatewayClient, String str, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return gatewayClient.m42createSessiongIAlus(str, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: createSession-gIAlu-s, reason: not valid java name */
    public final Object m42createSessiongIAlus(String title, Continuation continuation) {
        GatewayClient$createSession$1 gatewayClient$createSession$1;
        Object objWithContext;
        if (continuation instanceof GatewayClient$createSession$1) {
            gatewayClient$createSession$1 = (GatewayClient$createSession$1) continuation;
            if ((gatewayClient$createSession$1.label & Integer.MIN_VALUE) != 0) {
                gatewayClient$createSession$1.label -= Integer.MIN_VALUE;
            } else {
                gatewayClient$createSession$1 = new GatewayClient$createSession$1(this, continuation);
            }
        } else {
            gatewayClient$createSession$1 = new GatewayClient$createSession$1(this, continuation);
        }
        Object $result = gatewayClient$createSession$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (gatewayClient$createSession$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                GatewayClient$createSession$2 gatewayClient$createSession$2 = new GatewayClient$createSession$2(this, title, null);
                gatewayClient$createSession$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, gatewayClient$createSession$2, gatewayClient$createSession$1);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ai.nous.aurora.compat.Compat.resultUnbox(objWithContext);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: getMessages-gIAlu-s, reason: not valid java name */
    public final Object m43getMessagesgIAlus(String sessionId, Continuation continuation) {
        GatewayClient$getMessages$1 gatewayClient$getMessages$1;
        Object objWithContext;
        if (continuation instanceof GatewayClient$getMessages$1) {
            gatewayClient$getMessages$1 = (GatewayClient$getMessages$1) continuation;
            if ((gatewayClient$getMessages$1.label & Integer.MIN_VALUE) != 0) {
                gatewayClient$getMessages$1.label -= Integer.MIN_VALUE;
            } else {
                gatewayClient$getMessages$1 = new GatewayClient$getMessages$1(this, continuation);
            }
        } else {
            gatewayClient$getMessages$1 = new GatewayClient$getMessages$1(this, continuation);
        }
        Object $result = gatewayClient$getMessages$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (gatewayClient$getMessages$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                GatewayClient$getMessages$2 gatewayClient$getMessages$2 = new GatewayClient$getMessages$2(this, sessionId, null);
                gatewayClient$getMessages$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, gatewayClient$getMessages$2, gatewayClient$getMessages$1);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ai.nous.aurora.compat.Compat.resultUnbox(objWithContext);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX INFO: renamed from: sendMessage-hUnOzRk, reason: not valid java name */
    public final Object m46sendMessagehUnOzRk(String sessionId, String content, String model, String clientMsgId, String ownerKey, Continuation continuation) {
        GatewayClient$sendMessage$1 gatewayClient$sendMessage$1;
        Object objWithContext;
        if (continuation instanceof GatewayClient$sendMessage$1) {
            gatewayClient$sendMessage$1 = (GatewayClient$sendMessage$1) continuation;
            if ((gatewayClient$sendMessage$1.label & Integer.MIN_VALUE) != 0) {
                gatewayClient$sendMessage$1.label -= Integer.MIN_VALUE;
            } else {
                gatewayClient$sendMessage$1 = new GatewayClient$sendMessage$1(this, continuation);
            }
        } else {
            gatewayClient$sendMessage$1 = new GatewayClient$sendMessage$1(this, continuation);
        }
        Object $result = gatewayClient$sendMessage$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (gatewayClient$sendMessage$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                GatewayClient$sendMessage$2 gatewayClient$sendMessage$2 = new GatewayClient$sendMessage$2(clientMsgId, ownerKey, this, sessionId, content, model, null);
                gatewayClient$sendMessage$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, gatewayClient$sendMessage$2, gatewayClient$sendMessage$1);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ai.nous.aurora.compat.Compat.resultUnbox(objWithContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GatewayService requireService() {
        GatewayService gatewayService = this.service;
        if (gatewayService != null) {
            return gatewayService;
        }
        throw new IllegalStateException("Not connected".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:11:0x001e  */
    /* JADX WARN: Code duplicated, block: B:13:0x0024  */
    /* JADX WARN: Code duplicated, block: B:15:0x002f  */
    /* JADX WARN: Code duplicated, block: B:17:0x0032  */
    /* JADX WARN: Code duplicated, block: B:18:0x003a  */
    public final AuroraSession toModel(HermesSession $this$toModel) {
        String it;
        String strTake;
        String str;
        String id = $this$toModel.getId();
        String it2 = $this$toModel.getTitle();
        if (it2 != null) {
            if (StringsKt.isBlank(it2)) {
                it2 = null;
            }
            if (it2 == null) {
                it = $this$toModel.getPreview();
                if (it == null) {
                    strTake = "Chat " + StringsKt.takeLast($this$toModel.getId(), 6);
                } else {
                    if (StringsKt.isBlank(it)) {
                        str = null;
                    } else {
                        str = it;
                    }
                    if (str != null) {
                        strTake = StringsKt.take(str, 48);
                    } else {
                        strTake = "Chat " + StringsKt.takeLast($this$toModel.getId(), 6);
                    }
                }
            } else {
                strTake = it2;
            }
        } else {
            it = $this$toModel.getPreview();
            if (it == null) {
                strTake = "Chat " + StringsKt.takeLast($this$toModel.getId(), 6);
            } else {
                str = StringsKt.isBlank(it) ? null : it;
                if (str != null) {
                    strTake = StringsKt.take(str, 48);
                } else {
                    strTake = "Chat " + StringsKt.takeLast($this$toModel.getId(), 6);
                }
            }
        }
        String source = $this$toModel.getSource();
        String model = $this$toModel.getModel();
        Integer messageCount = $this$toModel.getMessageCount();
        int iIntValue = messageCount != null ? messageCount.intValue() : 0;
        Double lastActive = $this$toModel.getLastActive();
        return new AuroraSession(id, strTake, source, model, iIntValue, (long) ((lastActive != null ? lastActive.doubleValue() : 0.0d) * 1000.0d), $this$toModel.getPreview(), null, 128, null);
    }
}
