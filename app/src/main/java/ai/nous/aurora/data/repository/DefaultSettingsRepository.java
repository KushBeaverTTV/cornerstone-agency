package ai.nous.aurora.data.repository;

import com.cornerstone.agency.BuildConfig;
import ai.nous.aurora.data.remote.HermesConfigApi;
import ai.nous.aurora.data.remote.HermesConfigFieldDto;
import ai.nous.aurora.data.remote.HermesConfigResponseDto;
import ai.nous.aurora.data.remote.HermesConfigSchemaDto;
import ai.nous.aurora.data.remote.MoaConfigDto;
import ai.nous.aurora.data.storage.AuroraCredentialStore;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.jakewharton.retrofit2.converter.kotlinx.serialization.KotlinSerializationConverterFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.json.JsonObject;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0016\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0096@¢\u0006\u0002\u0010\u001fJ\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010#\u001a\u00020\u001eH\u0096@¢\u0006\u0004\b$\u0010\u001fJ&\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00100!2\u0006\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020(H\u0096@¢\u0006\u0004\b)\u0010*J0\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00100!2\u0018\u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020(0.0-H\u0096@¢\u0006\u0004\b/\u00100J\u0016\u00101\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH\u0096@¢\u0006\u0002\u0010\u001fJ\u001e\u00102\u001a\b\u0012\u0004\u0012\u00020\u00120!2\u0006\u00103\u001a\u000204H\u0096@¢\u0006\u0004\b5\u00106J(\u00107\u001a\b\u0012\u0004\u0012\u00020\u00140!2\u0006\u00108\u001a\u00020\u00142\b\u00109\u001a\u0004\u0018\u00010\u0014H\u0096@¢\u0006\u0004\b:\u0010;J\n\u0010<\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0010H\u0002J\n\u0010@\u001a\u0004\u0018\u00010\u0010H\u0002J\u001e\u0010A\u001a\b\u0012\u0004\u0012\u00020B0-2\u0006\u0010C\u001a\u00020\u00142\u0006\u0010D\u001a\u00020\u0010H\u0016J\u0016\u0010E\u001a\u00020\u00122\u0006\u0010F\u001a\u00020\u0018H\u0082@¢\u0006\u0002\u0010GJ\u0014\u0010H\u001a\u00020\u0012*\u0002042\u0006\u0010I\u001a\u00020\u001eH\u0002J\u000e\u0010F\u001a\u00020\u0018H\u0082@¢\u0006\u0002\u0010JJ\u0010\u0010K\u001a\u00020\u00142\u0006\u0010L\u001a\u00020\u0014H\u0002J\b\u0010M\u001a\u00020NH\u0002JJ\u0010H\u001a\u00020\u0010*\u00020N2\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020(0P2\u0006\u0010I\u001a\u00020\u001e2\b\u0010Q\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u0012H\u0002J\u0014\u0010T\u001a\u00020U*\u00020V2\u0006\u0010&\u001a\u00020\u0014H\u0002J&\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020(0P2\u0006\u0010'\u001a\u00020(2\b\b\u0002\u0010X\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"Lai/nous/aurora/data/repository/DefaultSettingsRepository;", "Lai/nous/aurora/data/repository/SettingsRepository;", "context", "Landroid/content/Context;", "credentialStore", "Lai/nous/aurora/data/storage/AuroraCredentialStore;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "<init>", "(Landroid/content/Context;Lai/nous/aurora/data/storage/AuroraCredentialStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "json", "Lkotlinx/serialization/json/Json;", "cached", "Lai/nous/aurora/data/repository/HermesSettingsSnapshot;", "moaCached", "Lai/nous/aurora/data/repository/MoaSnapshot;", "lastGoodHostTag", "", "lastGoodFile", "Ljava/io/File;", "api", "Lai/nous/aurora/data/remote/HermesConfigApi;", "apiIdentity", "loadMutex", "Lkotlinx/coroutines/sync/Mutex;", "load", "forceRefresh", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadModelOptions", "Lkotlin/Result;", "Lai/nous/aurora/data/repository/ModelOptionsSnapshot;", "refresh", "loadModelOptions-gIAlu-s", "setValue", "path", "value", "Lkotlinx/serialization/json/JsonElement;", "setValue-0E7RQCE", "(Ljava/lang/String;Lkotlinx/serialization/json/JsonElement;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setValues", "pairs", "", "Lkotlin/Pair;", "setValues-gIAlu-s", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadMoa", "saveMoa", "body", "Lai/nous/aurora/data/remote/MoaConfigDto;", "saveMoa-gIAlu-s", "(Lai/nous/aurora/data/remote/MoaConfigDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encodeMoaPrompt", "prompt", "preset", "encodeMoaPrompt-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "peekCached", "persistLastGood", "", "snap", "readLastGood", "search", "Lai/nous/aurora/data/repository/SettingsSearchIndex$Hit;", "query", "snapshot", "fetchMoa", NotificationCompat.CATEGORY_SERVICE, "(Lai/nous/aurora/data/remote/HermesConfigApi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toSnapshot", "remoteAvailable", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "configBridgeUrl", "gateway", "loadBundledSchema", "Lai/nous/aurora/data/remote/HermesConfigSchemaDto;", "values", "", "error", "schemaSource", "moa", "toModel", "Lai/nous/aurora/data/repository/HermesSettingField;", "Lai/nous/aurora/data/remote/HermesConfigFieldDto;", "flatten", "prefix", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultSettingsRepository implements SettingsRepository {
    public static final int $stable = 8;
    public volatile HermesConfigApi api;
    public volatile String apiIdentity;
    public final Context appContext;
    public volatile HermesSettingsSnapshot cached;
    public final AuroraCredentialStore credentialStore;
    public final CoroutineDispatcher dispatcher;
    public final Json json;
    public volatile String lastGoodHostTag;
    public final Mutex loadMutex;
    public volatile MoaSnapshot moaCached;

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSettingsRepository$fetchMoa$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository", f = "SettingsRepository.kt", i = {0}, l = {388}, m = "fetchMoa", n = {"this"}, s = {"L$0"})
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
            return DefaultSettingsRepository.this.fetchMoa(null, this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSettingsRepository$service$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository", f = "SettingsRepository.kt", i = {0, 1, 1}, l = {404, 406}, m = NotificationCompat.CATEGORY_SERVICE, n = {"this", "this", "key"}, s = {"L$0", "L$0", "L$1"})
    final class C02221 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C02221(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultSettingsRepository.this.service(this);
        }
    }

    public DefaultSettingsRepository(Context context, AuroraCredentialStore credentialStore, CoroutineDispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(credentialStore, "credentialStore");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        this.credentialStore = credentialStore;
        this.dispatcher = dispatcher;
        this.appContext = context.getApplicationContext();
        this.json = ai.nous.aurora.compat.Compat.d_JsonKt_Json_4(null, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSettingsRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DefaultSettingsRepository.json$lambda$0((JsonBuilder) obj);
            }
        }, 1, null);
        this.lastGoodHostTag = "default";
        this.loadMutex = ai.nous.aurora.compat.Compat.d_MutexKt_Mutex_3(false, 1, null);
    }

    public /* synthetic */ DefaultSettingsRepository(Context context, AuroraCredentialStore auroraCredentialStore, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, auroraCredentialStore, (i & 4) != 0 ? Dispatchers.getIO() : coroutineDispatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        Json.setLenient(true);
        Json.setExplicitNulls(false);
        return Unit.INSTANCE;
    }

    private final File lastGoodFile() {
        return new File(this.appContext.getFilesDir(), "settings-last-good-" + this.lastGoodHostTag + ".json");
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSettingsRepository$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lai/nous/aurora/data/repository/HermesSettingsSnapshot;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository$load$2", f = "SettingsRepository.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4}, l = {503, 122, 123, 127, 135}, m = "invokeSuspend", n = {"$this$withContext", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "bundled", "$this$invokeSuspend_u24lambda_u2412_u24lambda_u244", "$this$withLock_u24default$iv", "bundled", "$this$invokeSuspend_u24lambda_u2412_u24lambda_u244", NotificationCompat.CATEGORY_SERVICE, "$this$withLock_u24default$iv", "bundled", "$this$invokeSuspend_u24lambda_u2412_u24lambda_u244", NotificationCompat.CATEGORY_SERVICE, "schema", "$this$withLock_u24default$iv", "bundled", "schema", "values"}, s = {"L$0", "L$1", "L$0", "L$2", "L$3", "L$0", "L$2", "L$3", "L$4", "L$0", "L$2", "L$3", "L$4", "L$5", "L$0", "L$2", "L$3", "L$4"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ boolean $forceRefresh;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        final /* synthetic */ DefaultSettingsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(boolean z, DefaultSettingsRepository defaultSettingsRepository, Continuation continuation) {
            super(2, continuation);
            this.$forceRefresh = z;
            this.this$0 = defaultSettingsRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$forceRefresh, this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:104:0x0265  */
        /* JADX WARN: Code duplicated, block: B:108:0x0281 A[Catch: all -> 0x02e4, TryCatch #3 {all -> 0x02e4, blocks: (B:102:0x025f, B:105:0x0266, B:106:0x027b, B:108:0x0281, B:110:0x0296, B:115:0x02a3, B:116:0x02af), top: B:228:0x025f }] */
        /* JADX WARN: Code duplicated, block: B:110:0x0296 A[Catch: all -> 0x02e4, TryCatch #3 {all -> 0x02e4, blocks: (B:102:0x025f, B:105:0x0266, B:106:0x027b, B:108:0x0281, B:110:0x0296, B:115:0x02a3, B:116:0x02af), top: B:228:0x025f }] */
        /* JADX WARN: Code duplicated, block: B:113:0x02a0  */
        /* JADX WARN: Code duplicated, block: B:122:0x02f7 A[Catch: all -> 0x0334, TRY_ENTER, TryCatch #9 {all -> 0x0334, blocks: (B:80:0x01e1, B:82:0x01e9, B:84:0x01ef, B:87:0x01f7, B:88:0x0200, B:122:0x02f7, B:123:0x0302, B:79:0x01dd, B:124:0x0303, B:125:0x0310, B:126:0x0311, B:127:0x0333), top: B:240:0x01ae }] */
        /* JADX WARN: Code duplicated, block: B:124:0x0303 A[Catch: all -> 0x0334, TryCatch #9 {all -> 0x0334, blocks: (B:80:0x01e1, B:82:0x01e9, B:84:0x01ef, B:87:0x01f7, B:88:0x0200, B:122:0x02f7, B:123:0x0302, B:79:0x01dd, B:124:0x0303, B:125:0x0310, B:126:0x0311, B:127:0x0333), top: B:240:0x01ae }] */
        /* JADX WARN: Code duplicated, block: B:126:0x0311 A[Catch: all -> 0x0334, TryCatch #9 {all -> 0x0334, blocks: (B:80:0x01e1, B:82:0x01e9, B:84:0x01ef, B:87:0x01f7, B:88:0x0200, B:122:0x02f7, B:123:0x0302, B:79:0x01dd, B:124:0x0303, B:125:0x0310, B:126:0x0311, B:127:0x0333), top: B:240:0x01ae }] */
        /* JADX WARN: Code duplicated, block: B:132:0x0344 A[Catch: all -> 0x037d, TRY_ENTER, TryCatch #15 {all -> 0x037d, blocks: (B:57:0x0169, B:59:0x0171, B:61:0x0179, B:63:0x0185, B:132:0x0344, B:133:0x034f, B:134:0x0350, B:135:0x035b, B:136:0x035c, B:137:0x037c, B:53:0x014d), top: B:251:0x014d }] */
        /* JADX WARN: Code duplicated, block: B:134:0x0350 A[Catch: all -> 0x037d, TryCatch #15 {all -> 0x037d, blocks: (B:57:0x0169, B:59:0x0171, B:61:0x0179, B:63:0x0185, B:132:0x0344, B:133:0x034f, B:134:0x0350, B:135:0x035b, B:136:0x035c, B:137:0x037c, B:53:0x014d), top: B:251:0x014d }] */
        /* JADX WARN: Code duplicated, block: B:136:0x035c A[Catch: all -> 0x037d, TryCatch #15 {all -> 0x037d, blocks: (B:57:0x0169, B:59:0x0171, B:61:0x0179, B:63:0x0185, B:132:0x0344, B:133:0x034f, B:134:0x0350, B:135:0x035b, B:136:0x035c, B:137:0x037c, B:53:0x014d), top: B:251:0x014d }] */
        /* JADX WARN: Code duplicated, block: B:146:0x039f  */
        /* JADX WARN: Code duplicated, block: B:147:0x03a1 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:149:0x03aa A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:150:0x03af  */
        /* JADX WARN: Code duplicated, block: B:152:0x03b2 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:155:0x03c5 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:156:0x03ca  */
        /* JADX WARN: Code duplicated, block: B:158:0x03cd A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:162:0x03d7  */
        /* JADX WARN: Code duplicated, block: B:164:0x03db A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:165:0x03e1 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:168:0x03f1  */
        /* JADX WARN: Code duplicated, block: B:171:0x040e A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:173:0x0414  */
        /* JADX WARN: Code duplicated, block: B:176:0x0419 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:177:0x041f  */
        /* JADX WARN: Code duplicated, block: B:181:0x0432 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:182:0x0439 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:184:0x043f A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:190:0x0451 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:192:0x0459  */
        /* JADX WARN: Code duplicated, block: B:193:0x045c  */
        /* JADX WARN: Code duplicated, block: B:195:0x0460 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:198:0x0471 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:200:0x0477 A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:203:0x047e A[Catch: all -> 0x04b0, TryCatch #7 {all -> 0x04b0, blocks: (B:144:0x0399, B:179:0x0427, B:181:0x0432, B:188:0x044b, B:190:0x0451, B:195:0x0460, B:196:0x0466, B:198:0x0471, B:200:0x0477, B:203:0x047e, B:205:0x048a, B:209:0x0495, B:182:0x0439, B:184:0x043f, B:186:0x0445, B:147:0x03a1, B:149:0x03aa, B:152:0x03b2, B:153:0x03b6, B:155:0x03c5, B:158:0x03cd, B:164:0x03db, B:166:0x03e6, B:169:0x03f3, B:171:0x040e, B:176:0x0419, B:178:0x0420, B:165:0x03e1), top: B:236:0x0399 }] */
        /* JADX WARN: Code duplicated, block: B:207:0x0490  */
        /* JADX WARN: Code duplicated, block: B:208:0x0494  */
        /* JADX WARN: Code duplicated, block: B:226:0x0112 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:230:0x01c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:252:0x0202 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:254:0x02a3 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:257:0x027b A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:41:0x0118  */
        /* JADX WARN: Code duplicated, block: B:43:0x011f  */
        /* JADX WARN: Code duplicated, block: B:46:0x0128  */
        /* JADX WARN: Code duplicated, block: B:51:0x0145 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:52:0x0146  */
        /* JADX WARN: Code duplicated, block: B:55:0x0162 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:56:0x0163  */
        /* JADX WARN: Code duplicated, block: B:59:0x0171 A[Catch: all -> 0x037d, TryCatch #15 {all -> 0x037d, blocks: (B:57:0x0169, B:59:0x0171, B:61:0x0179, B:63:0x0185, B:132:0x0344, B:133:0x034f, B:134:0x0350, B:135:0x035b, B:136:0x035c, B:137:0x037c, B:53:0x014d), top: B:251:0x014d }] */
        /* JADX WARN: Code duplicated, block: B:61:0x0179 A[Catch: all -> 0x037d, TryCatch #15 {all -> 0x037d, blocks: (B:57:0x0169, B:59:0x0171, B:61:0x0179, B:63:0x0185, B:132:0x0344, B:133:0x034f, B:134:0x0350, B:135:0x035b, B:136:0x035c, B:137:0x037c, B:53:0x014d), top: B:251:0x014d }] */
        /* JADX WARN: Code duplicated, block: B:63:0x0185 A[Catch: all -> 0x037d, TRY_LEAVE, TryCatch #15 {all -> 0x037d, blocks: (B:57:0x0169, B:59:0x0171, B:61:0x0179, B:63:0x0185, B:132:0x0344, B:133:0x034f, B:134:0x0350, B:135:0x035b, B:136:0x035c, B:137:0x037c, B:53:0x014d), top: B:251:0x014d }] */
        /* JADX WARN: Code duplicated, block: B:65:0x019a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:66:0x019b  */
        /* JADX WARN: Code duplicated, block: B:69:0x01b0 A[Catch: all -> 0x033b, TryCatch #13 {all -> 0x033b, blocks: (B:67:0x01a8, B:69:0x01b0, B:71:0x01b8, B:77:0x01d2), top: B:247:0x01a8 }] */
        /* JADX WARN: Code duplicated, block: B:71:0x01b8 A[Catch: all -> 0x033b, TRY_LEAVE, TryCatch #13 {all -> 0x033b, blocks: (B:67:0x01a8, B:69:0x01b0, B:71:0x01b8, B:77:0x01d2), top: B:247:0x01a8 }] */
        /* JADX WARN: Code duplicated, block: B:77:0x01d2 A[Catch: all -> 0x033b, TRY_ENTER, TRY_LEAVE, TryCatch #13 {all -> 0x033b, blocks: (B:67:0x01a8, B:69:0x01b0, B:71:0x01b8, B:77:0x01d2), top: B:247:0x01a8 }] */
        /* JADX WARN: Code duplicated, block: B:82:0x01e9 A[Catch: all -> 0x0334, TryCatch #9 {all -> 0x0334, blocks: (B:80:0x01e1, B:82:0x01e9, B:84:0x01ef, B:87:0x01f7, B:88:0x0200, B:122:0x02f7, B:123:0x0302, B:79:0x01dd, B:124:0x0303, B:125:0x0310, B:126:0x0311, B:127:0x0333), top: B:240:0x01ae }] */
        /* JADX WARN: Code duplicated, block: B:84:0x01ef A[Catch: all -> 0x0334, TryCatch #9 {all -> 0x0334, blocks: (B:80:0x01e1, B:82:0x01e9, B:84:0x01ef, B:87:0x01f7, B:88:0x0200, B:122:0x02f7, B:123:0x0302, B:79:0x01dd, B:124:0x0303, B:125:0x0310, B:126:0x0311, B:127:0x0333), top: B:240:0x01ae }] */
        /* JADX WARN: Code duplicated, block: B:86:0x01f5  */
        /* JADX WARN: Code duplicated, block: B:92:0x021a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:93:0x021b  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Throwable th = new IllegalStateException("unreachable");
            Object $result;
            CoroutineScope $this$withContext;
            Mutex $this$withLock_u24default$iv;
            boolean z;
            HermesConfigSchemaDto owner$iv;
            DefaultSettingsRepository defaultSettingsRepository;
            int $i$f$withLock;
            HermesSettingsSnapshot it;
            int i;
            HermesConfigSchemaDto owner$iv2;
            Mutex $this$withLock_u24default$iv2;
            HermesSettingsSnapshot hermesSettingsSnapshot;
            HermesConfigSchemaDto owner$iv3;
            HermesConfigSchemaDto bundled;
            int $i$f$withLock2;
            Mutex $this$withLock_u24default$iv3;
            CoroutineScope $this$invokeSuspend_u24lambda_u2412_u24lambda_u244;
            int i2;
            Object objService;
            Object $result2;
            Object $result3;
            CoroutineScope $this$invokeSuspend_u24lambda_u2412_u24lambda_u245;
            int $i$f$withLock3;
            HermesConfigApi service;
            Object objSchema;
            HermesConfigApi service2;
            Object $result4;
            CoroutineScope $this$invokeSuspend_u24lambda_u2412_u24lambda_u246;
            Response schemaResponse;
            HermesConfigSchemaDto schema;
            Object objConfig;
            HermesConfigSchemaDto schema2;
            Object $result5;
            int i3;
            HermesConfigApi service3;
            int $i$f$withLock4;
            Mutex $this$withLock_u24default$iv4;
            Response configResponse;
            HermesConfigResponseDto config;
            Map<String, JsonElement> flat;
            HermesConfigSchemaDto bundled2;
            DefaultSettingsRepository defaultSettingsRepository2;
            HermesConfigSchemaDto bundled3;
            Mutex $this$withLock_u24default$iv5;
            Map<String, JsonElement> map;
            Object objFetchMoa;
            Object $result6;
            Object $result7;
            String resolutionError;
            Object objM7388constructorimpl;
            Map<String, JsonElement> map2;
            HermesConfigSchemaDto schema3;
            HermesConfigSchemaDto bundled4;
            DefaultSettingsRepository defaultSettingsRepository3;
            HermesConfigSchemaDto owner$iv4;
            int $i$f$withLock5;
            Object objM7388constructorimpl2;
            HermesConfigSchemaDto bundled5;
            DefaultSettingsRepository defaultSettingsRepository4;
            Throwable failure;
            HermesSettingsSnapshot prior;
            Map<String, JsonElement> mapEmptyMap;
            StringBuilder $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245;
            Map<String, JsonElement> values;
            boolean z2;
            String message;
            String schemaSource;
            MoaSnapshot moa;
            HermesSettingsSnapshot snap;
            HermesSettingsSnapshot disk;
            MoaSnapshot it2;
            HermesSettingsSnapshot snap2;
            HermesSettingsSnapshot lastGood;
            HermesSettingsSnapshot disk2;
            String error;
            String str;
            MoaSnapshot it3;
            LinkedHashMap result$iv;
            String it4;
            boolean z3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    $result = obj;
                    $this$withContext = (CoroutineScope) this.L$0;
                    if (!this.$forceRefresh && (it = this.this$0.cached) != null) {
                        return it;
                    }
                    $this$withLock_u24default$iv = this.this$0.loadMutex;
                    z = this.$forceRefresh;
                    DefaultSettingsRepository defaultSettingsRepository5 = this.this$0;
                    owner$iv = null;
                    this.L$0 = $this$withContext;
                    this.L$1 = $this$withLock_u24default$iv;
                    this.L$2 = defaultSettingsRepository5;
                    this.Z$0 = z;
                    this.label = 1;
                    if ($this$withLock_u24default$iv.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    defaultSettingsRepository = defaultSettingsRepository5;
                    $i$f$withLock = 0;
                    i = 0;
                    if (z) {
                        owner$iv3 = owner$iv;
                    } else {
                        try {
                            hermesSettingsSnapshot = defaultSettingsRepository.cached;
                            if (hermesSettingsSnapshot != null) {
                                $this$withLock_u24default$iv.unlock(owner$iv);
                                return hermesSettingsSnapshot;
                            }
                            owner$iv3 = owner$iv;
                        } catch (Throwable thCatch) {
                            owner$iv2 = owner$iv;
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                            $this$withLock_u24default$iv2.unlock(owner$iv2);
                            throw thCatch;
                        }
                    }
                    try {
                        bundled = defaultSettingsRepository.loadBundledSchema();
                        try {
                            Result.Companion companion = Result.Companion;
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u244 = $this$withContext;
                            i2 = 0;
                            this.L$0 = $this$withLock_u24default$iv;
                            this.L$1 = defaultSettingsRepository;
                            this.L$2 = bundled;
                            this.L$3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u244;
                            this.label = 2;
                            objService = defaultSettingsRepository.service(this);
                            if (objService == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            int i4 = $i$f$withLock;
                            $result2 = $result;
                            $result3 = objService;
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u245 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u244;
                            $i$f$withLock3 = i4;
                            try {
                                service = (HermesConfigApi) $result3;
                                this.L$0 = $this$withLock_u24default$iv;
                                this.L$1 = defaultSettingsRepository;
                                this.L$2 = bundled;
                                this.L$3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u245;
                                this.L$4 = service;
                                this.label = 3;
                                objSchema = service.schema(this);
                                if (objSchema == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                CoroutineScope coroutineScope = $this$invokeSuspend_u24lambda_u2412_u24lambda_u245;
                                service2 = service;
                                $result4 = objSchema;
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246 = coroutineScope;
                                schemaResponse = (Response) $result4;
                                if (schemaResponse.isSuccessful()) {
                                    throw new IllegalStateException(("Schema HTTP " + schemaResponse.code()).toString());
                                }
                                schema = (HermesConfigSchemaDto) schemaResponse.body();
                                if (schema != null) {
                                    throw new IllegalStateException("The config bridge returned an empty schema".toString());
                                }
                                if (!schema.getFields().isEmpty()) {
                                    throw new IllegalStateException("Schema has zero fields".toString());
                                }
                                this.L$0 = $this$withLock_u24default$iv;
                                this.L$1 = defaultSettingsRepository;
                                this.L$2 = bundled;
                                this.L$3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246;
                                this.L$4 = service2;
                                this.L$5 = schema;
                                this.label = 4;
                                objConfig = service2.config(true, true, this);
                                if (objConfig == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                HermesConfigApi hermesConfigApi = service2;
                                schema2 = schema;
                                $result5 = objConfig;
                                i3 = i;
                                service3 = hermesConfigApi;
                                $i$f$withLock4 = $i$f$withLock3;
                                $this$withLock_u24default$iv4 = $this$withLock_u24default$iv;
                                try {
                                    try {
                                        try {
                                            configResponse = (Response) $result5;
                                            try {
                                                if (!configResponse.isSuccessful()) {
                                                    throw new IllegalStateException(("Config HTTP " + configResponse.code()).toString());
                                                }
                                                config = (HermesConfigResponseDto) configResponse.body();
                                                if (config == null) {
                                                    throw new IllegalStateException("The config bridge returned an empty config".toString());
                                                }
                                                if (!config.getFlat().isEmpty()) {
                                                    try {
                                                    } catch (Throwable th2) {
                                                        $i$f$withLock2 = $i$f$withLock4;
                                                        $this$withLock_u24default$iv3 = $this$withLock_u24default$iv4;
                                                    }
                                                }
                                                flat = !config.getFlat().isEmpty() ? config.getFlat() : DefaultSettingsRepository.flatten$default(defaultSettingsRepository, config.getConfig(), null, 2, null);
                                                if (flat.isEmpty()) {
                                                    throw new IllegalStateException("Config values empty".toString());
                                                }
                                                if (!config.getResolved()) {
                                                    resolutionError = config.getResolutionError();
                                                    if (resolutionError == null) {
                                                        resolutionError = "Bridge did not return resolved config";
                                                    }
                                                    throw new IllegalStateException(resolutionError.toString());
                                                }
                                                try {
                                                    Result.Companion companion2 = Result.Companion;
                                                    this.L$0 = $this$withLock_u24default$iv4;
                                                    this.L$1 = defaultSettingsRepository;
                                                    this.L$2 = bundled;
                                                    this.L$3 = schema2;
                                                    this.L$4 = flat;
                                                    this.L$5 = null;
                                                    this.label = 5;
                                                    objFetchMoa = defaultSettingsRepository.fetchMoa(service3, this);
                                                    if (objFetchMoa == coroutine_suspended) {
                                                        return coroutine_suspended;
                                                    }
                                                    $result6 = objFetchMoa;
                                                    bundled2 = bundled;
                                                    defaultSettingsRepository2 = defaultSettingsRepository;
                                                    bundled3 = owner$iv3;
                                                    $this$withLock_u24default$iv5 = $this$withLock_u24default$iv4;
                                                    map = flat;
                                                    $result7 = $result2;
                                                    try {
                                                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((MoaSnapshot) $result6);
                                                        map2 = map;
                                                        schema3 = schema2;
                                                        bundled4 = bundled2;
                                                        defaultSettingsRepository3 = defaultSettingsRepository2;
                                                        owner$iv4 = bundled3;
                                                        $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                                                        $i$f$withLock5 = $i$f$withLock4;
                                                    } catch (Throwable th3) {
                                                        try {
                                                            Result.Companion companion3 = Result.Companion;
                                                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th3));
                                                            map2 = map;
                                                            schema3 = schema2;
                                                            bundled4 = bundled2;
                                                            defaultSettingsRepository3 = defaultSettingsRepository2;
                                                            owner$iv4 = bundled3;
                                                            $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                                                            $i$f$withLock5 = $i$f$withLock4;
                                                        } catch (Throwable th4) {
                                                            $i$f$withLock2 = $i$f$withLock4;
                                                            owner$iv3 = bundled3;
                                                            $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                                                            bundled = bundled2;
                                                            defaultSettingsRepository = defaultSettingsRepository2;
                                                        }
                                                    }
                                                    try {
                                                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                                                            objM7388constructorimpl = null;
                                                        }
                                                        MoaSnapshot moa2 = (MoaSnapshot) objM7388constructorimpl;
                                                        Map<String, HermesConfigFieldDto> fields = schema3.getFields();
                                                        result$iv = new LinkedHashMap();
                                                        for (Map.Entry<String, HermesConfigFieldDto> entry : fields.entrySet()) {
                                                            it4 = entry.getKey();
                                                            if (!Intrinsics.areEqual(it4, "model") || Intrinsics.areEqual(it4, "model_context_length")) {
                                                                z3 = false;
                                                            } else {
                                                                z3 = true;
                                                            }
                                                            if (z3) {
                                                                result$iv.put(entry.getKey(), entry.getValue());
                                                            }
                                                        }
                                                        HermesConfigSchemaDto safeSchema = HermesConfigSchemaDto.copy$default(schema3, result$iv, null, null, null, 14, null);
                                                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(defaultSettingsRepository3.toSnapshot(safeSchema, map2, true, null, schema3.getSource(), moa2));
                                                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                                        bundled5 = bundled4;
                                                        defaultSettingsRepository4 = defaultSettingsRepository3;
                                                        owner$iv2 = owner$iv4;
                                                    } catch (Throwable th5) {
                                                        $i$f$withLock2 = $i$f$withLock5;
                                                        bundled = bundled4;
                                                        defaultSettingsRepository = defaultSettingsRepository3;
                                                        owner$iv3 = owner$iv4;
                                                        Result.Companion companion4 = Result.Companion;
                                                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th5));
                                                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                                        bundled5 = bundled;
                                                        defaultSettingsRepository4 = defaultSettingsRepository;
                                                        owner$iv2 = owner$iv3;
                                                    }
                                                    failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                                                    if (failure != null) {
                                                        prior = defaultSettingsRepository4.cached;
                                                        if (prior != null) {
                                                            mapEmptyMap = prior.getValues();
                                                        } else {
                                                            mapEmptyMap = null;
                                                        }
                                                        if (mapEmptyMap == null) {
                                                            mapEmptyMap = MapsKt.emptyMap();
                                                        }
                                                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                                                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                                                        if (prior != null) {
                                                            values = prior.getValues();
                                                        } else {
                                                            values = null;
                                                        }
                                                        if (values != null || values.isEmpty()) {
                                                            z2 = true;
                                                        } else {
                                                            z2 = false;
                                                        }
                                                        if (z2) {
                                                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                                                        } else {
                                                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                                                        }
                                                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                                                        message = failure.getMessage();
                                                        if (message == null) {
                                                            message = "";
                                                        }
                                                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                                                        Unit unit = Unit.INSTANCE;
                                                        String string = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                                                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                                                        String string2 = StringsKt.trim((CharSequence) string).toString();
                                                        if (prior != null || (schemaSource = prior.getSchemaSource()) == null) {
                                                            schemaSource = "bundled";
                                                        }
                                                        String str2 = schemaSource;
                                                        if (prior != null) {
                                                            moa = prior.getMoa();
                                                        } else {
                                                            moa = null;
                                                        }
                                                        objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string2, str2, moa);
                                                    }
                                                    snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                                                    if (snap.getRemoteAvailable()) {
                                                        defaultSettingsRepository4.cached = snap;
                                                        defaultSettingsRepository4.persistLastGood(snap);
                                                    } else if (defaultSettingsRepository4.cached == null && (disk = defaultSettingsRepository4.readLastGood()) != null) {
                                                        defaultSettingsRepository4.cached = disk;
                                                    }
                                                    it2 = snap.getMoa();
                                                    if (it2 != null) {
                                                        if (it2.getRemoteAvailable()) {
                                                            it3 = it2;
                                                        } else {
                                                            it3 = null;
                                                        }
                                                        if (it3 != null) {
                                                            defaultSettingsRepository4.moaCached = it3;
                                                        }
                                                    }
                                                    snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                                                    if (!snap2.getRemoteAvailable()) {
                                                        lastGood = defaultSettingsRepository4.cached;
                                                        if (lastGood == null) {
                                                            lastGood = defaultSettingsRepository4.readLastGood();
                                                        }
                                                        disk2 = lastGood;
                                                        if (disk2 != null && !disk2.getValues().isEmpty()) {
                                                            error = snap2.getError();
                                                            if (error == null) {
                                                                str = "Live settings unavailable. Showing last-good snapshot.";
                                                            } else {
                                                                str = error;
                                                            }
                                                            snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                                                        }
                                                        break;
                                                    }
                                                    $this$withLock_u24default$iv2.unlock(owner$iv2);
                                                    return snap2;
                                                } catch (Throwable th6) {
                                                    bundled2 = bundled;
                                                    defaultSettingsRepository2 = defaultSettingsRepository;
                                                    bundled3 = owner$iv3;
                                                    $this$withLock_u24default$iv5 = $this$withLock_u24default$iv4;
                                                    map = flat;
                                                    Result.Companion companion5 = Result.Companion;
                                                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th6));
                                                    map2 = map;
                                                    schema3 = schema2;
                                                    bundled4 = bundled2;
                                                    defaultSettingsRepository3 = defaultSettingsRepository2;
                                                    owner$iv4 = bundled3;
                                                    $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                                                    $i$f$withLock5 = $i$f$withLock4;
                                                    if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                                                        objM7388constructorimpl = null;
                                                    }
                                                    MoaSnapshot moa3 = (MoaSnapshot) objM7388constructorimpl;
                                                    Map<String, HermesConfigFieldDto> fields2 = schema3.getFields();
                                                    result$iv = new LinkedHashMap();
                                                    java.util.Iterator r11 = fields2.entrySet().iterator();
                                                    while (r11.hasNext()) {
                                                        java.util.Map.Entry<String, HermesConfigFieldDto> entry = (java.util.Map.Entry<String, HermesConfigFieldDto>) r11.next();
                                                        it4 = entry.getKey();
                                                        if (Intrinsics.areEqual(it4, "model")) {
                                                            z3 = false;
                                                        } else {
                                                            z3 = false;
                                                        }
                                                        if (z3) {
                                                            entry = (java.util.Map.Entry) r11.next();
                                                            result$iv.put(entry.getKey(), entry.getValue());
                                                        }
                                                    }
                                                    HermesConfigSchemaDto safeSchema2 = HermesConfigSchemaDto.copy$default(schema3, result$iv, null, null, null, 14, null);
                                                    objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(defaultSettingsRepository3.toSnapshot(safeSchema2, map2, true, null, schema3.getSource(), moa3));
                                                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                                    bundled5 = bundled4;
                                                    defaultSettingsRepository4 = defaultSettingsRepository3;
                                                    owner$iv2 = owner$iv4;
                                                    failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                                                    if (failure != null) {
                                                        prior = defaultSettingsRepository4.cached;
                                                        if (prior != null) {
                                                            mapEmptyMap = prior.getValues();
                                                        } else {
                                                            mapEmptyMap = null;
                                                        }
                                                        if (mapEmptyMap == null) {
                                                            mapEmptyMap = MapsKt.emptyMap();
                                                        }
                                                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                                                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                                                        if (prior != null) {
                                                            values = prior.getValues();
                                                        } else {
                                                            values = null;
                                                        }
                                                        if (values != null) {
                                                            z2 = true;
                                                        } else {
                                                            z2 = true;
                                                        }
                                                        if (z2) {
                                                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                                                        } else {
                                                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                                                        }
                                                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                                                        message = failure.getMessage();
                                                        if (message == null) {
                                                            message = "";
                                                        }
                                                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                                                        Unit unit2 = Unit.INSTANCE;
                                                        String string3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                                                        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                                                        String string4 = StringsKt.trim((CharSequence) string3).toString();
                                                        if (prior != null) {
                                                            schemaSource = "bundled";
                                                        } else {
                                                            schemaSource = "bundled";
                                                        }
                                                        String str3 = schemaSource;
                                                        if (prior != null) {
                                                            moa = prior.getMoa();
                                                        } else {
                                                            moa = null;
                                                        }
                                                        objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string4, str3, moa);
                                                    }
                                                    snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                                                    if (snap.getRemoteAvailable()) {
                                                        defaultSettingsRepository4.cached = snap;
                                                        defaultSettingsRepository4.persistLastGood(snap);
                                                    } else if (defaultSettingsRepository4.cached == null) {
                                                        defaultSettingsRepository4.cached = disk;
                                                    }
                                                    it2 = snap.getMoa();
                                                    if (it2 != null) {
                                                        if (it2.getRemoteAvailable()) {
                                                            it3 = it2;
                                                        } else {
                                                            it3 = null;
                                                        }
                                                        if (it3 != null) {
                                                            defaultSettingsRepository4.moaCached = it3;
                                                        }
                                                    }
                                                    snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                                                    if (!snap2.getRemoteAvailable()) {
                                                        lastGood = defaultSettingsRepository4.cached;
                                                        if (lastGood == null) {
                                                            lastGood = defaultSettingsRepository4.readLastGood();
                                                        }
                                                        disk2 = lastGood;
                                                        if (disk2 != null) {
                                                            error = snap2.getError();
                                                            if (error == null) {
                                                                str = "Live settings unavailable. Showing last-good snapshot.";
                                                            } else {
                                                                str = error;
                                                            }
                                                            snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                                                        }
                                                        break;
                                                    }
                                                    $this$withLock_u24default$iv2.unlock(owner$iv2);
                                                    return snap2;
                                                }
                                            } catch (Throwable th7) {
                                                $i$f$withLock2 = $i$f$withLock4;
                                                $this$withLock_u24default$iv3 = $this$withLock_u24default$iv4;
                                            }
                                        } catch (Throwable th8) {
                                            $i$f$withLock2 = $i$f$withLock4;
                                            $this$withLock_u24default$iv3 = $this$withLock_u24default$iv4;
                                        }
                                        failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                                        if (failure != null) {
                                            prior = defaultSettingsRepository4.cached;
                                            if (prior != null) {
                                                mapEmptyMap = prior.getValues();
                                            } else {
                                                mapEmptyMap = null;
                                            }
                                            if (mapEmptyMap == null) {
                                                mapEmptyMap = MapsKt.emptyMap();
                                            }
                                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                                            if (prior != null) {
                                                values = prior.getValues();
                                            } else {
                                                values = null;
                                            }
                                            if (values != null) {
                                                z2 = true;
                                            } else {
                                                z2 = true;
                                            }
                                            if (z2) {
                                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                                            } else {
                                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                                            }
                                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                                            message = failure.getMessage();
                                            if (message == null) {
                                                message = "";
                                            }
                                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                                            Unit unit3 = Unit.INSTANCE;
                                            String string5 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                                            Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
                                            String string6 = StringsKt.trim((CharSequence) string5).toString();
                                            if (prior != null) {
                                                schemaSource = "bundled";
                                            } else {
                                                schemaSource = "bundled";
                                            }
                                            String str4 = schemaSource;
                                            if (prior != null) {
                                                moa = prior.getMoa();
                                            } else {
                                                moa = null;
                                            }
                                            objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string6, str4, moa);
                                        }
                                        snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                                        if (snap.getRemoteAvailable()) {
                                            defaultSettingsRepository4.cached = snap;
                                            defaultSettingsRepository4.persistLastGood(snap);
                                        } else if (defaultSettingsRepository4.cached == null) {
                                            defaultSettingsRepository4.cached = disk;
                                        }
                                        it2 = snap.getMoa();
                                        if (it2 != null) {
                                            if (it2.getRemoteAvailable()) {
                                                it3 = it2;
                                            } else {
                                                it3 = null;
                                            }
                                            if (it3 != null) {
                                                defaultSettingsRepository4.moaCached = it3;
                                            }
                                        }
                                        snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                                        if (!snap2.getRemoteAvailable()) {
                                            lastGood = defaultSettingsRepository4.cached;
                                            if (lastGood == null) {
                                                lastGood = defaultSettingsRepository4.readLastGood();
                                            }
                                            disk2 = lastGood;
                                            if (disk2 != null) {
                                                error = snap2.getError();
                                                if (error == null) {
                                                    str = "Live settings unavailable. Showing last-good snapshot.";
                                                } else {
                                                    str = error;
                                                }
                                                snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                                            }
                                            break;
                                        }
                                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                                        return snap2;
                                    } catch (Throwable th9) {
                                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                                        Result.Companion companion6 = Result.Companion;
                                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th9));
                                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                        bundled5 = bundled;
                                        defaultSettingsRepository4 = defaultSettingsRepository;
                                        owner$iv2 = owner$iv3;
                                        throw th9;
                                    }
                                } catch (Throwable th10) {
                                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                    owner$iv2 = owner$iv3;
                                    $this$withLock_u24default$iv2.unlock(owner$iv2);
                                    throw th10;
                                }
                            } catch (Throwable th11) {
                                $this$withLock_u24default$iv3 = $this$withLock_u24default$iv;
                                $i$f$withLock2 = $i$f$withLock3;
                            }
                        } catch (Throwable th12) {
                            $i$f$withLock2 = $i$f$withLock;
                            $this$withLock_u24default$iv3 = $this$withLock_u24default$iv;
                        }
                    } catch (Throwable th13) {
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                        owner$iv2 = owner$iv3;
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        throw th13;
                    }
                    break;
                case 1:
                    $result = obj;
                    $i$f$withLock = 0;
                    z = this.Z$0;
                    DefaultSettingsRepository defaultSettingsRepository6 = (DefaultSettingsRepository) this.L$2;
                    $this$withLock_u24default$iv = (Mutex) this.L$1;
                    $this$withContext = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    defaultSettingsRepository = defaultSettingsRepository6;
                    owner$iv = null;
                    i = 0;
                    if (z) {
                        hermesSettingsSnapshot = defaultSettingsRepository.cached;
                        if (hermesSettingsSnapshot != null) {
                            $this$withLock_u24default$iv.unlock(owner$iv);
                            return hermesSettingsSnapshot;
                        }
                        owner$iv3 = owner$iv;
                    } else {
                        owner$iv3 = owner$iv;
                    }
                    bundled = defaultSettingsRepository.loadBundledSchema();
                    Result.Companion companion7 = Result.Companion;
                    $this$invokeSuspend_u24lambda_u2412_u24lambda_u244 = $this$withContext;
                    i2 = 0;
                    this.L$0 = $this$withLock_u24default$iv;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = bundled;
                    this.L$3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u244;
                    this.label = 2;
                    objService = defaultSettingsRepository.service(this);
                    if (objService == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int i5 = $i$f$withLock;
                    $result2 = $result;
                    $result3 = objService;
                    $this$invokeSuspend_u24lambda_u2412_u24lambda_u245 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u244;
                    $i$f$withLock3 = i5;
                    service = (HermesConfigApi) $result3;
                    this.L$0 = $this$withLock_u24default$iv;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = bundled;
                    this.L$3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u245;
                    this.L$4 = service;
                    this.label = 3;
                    objSchema = service.schema(this);
                    if (objSchema == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    CoroutineScope coroutineScope2 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u245;
                    service2 = service;
                    $result4 = objSchema;
                    $this$invokeSuspend_u24lambda_u2412_u24lambda_u246 = coroutineScope2;
                    schemaResponse = (Response) $result4;
                    if (schemaResponse.isSuccessful()) {
                        throw new IllegalStateException(("Schema HTTP " + schemaResponse.code()).toString());
                    }
                    schema = (HermesConfigSchemaDto) schemaResponse.body();
                    if (schema != null) {
                        throw new IllegalStateException("The config bridge returned an empty schema".toString());
                    }
                    if (!schema.getFields().isEmpty()) {
                        throw new IllegalStateException("Schema has zero fields".toString());
                    }
                    this.L$0 = $this$withLock_u24default$iv;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = bundled;
                    this.L$3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246;
                    this.L$4 = service2;
                    this.L$5 = schema;
                    this.label = 4;
                    objConfig = service2.config(true, true, this);
                    if (objConfig == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    HermesConfigApi hermesConfigApi2 = service2;
                    schema2 = schema;
                    $result5 = objConfig;
                    i3 = i;
                    service3 = hermesConfigApi2;
                    $i$f$withLock4 = $i$f$withLock3;
                    $this$withLock_u24default$iv4 = $this$withLock_u24default$iv;
                    configResponse = (Response) $result5;
                    if (!configResponse.isSuccessful()) {
                        throw new IllegalStateException(("Config HTTP " + configResponse.code()).toString());
                    }
                    config = (HermesConfigResponseDto) configResponse.body();
                    if (config == null) {
                        throw new IllegalStateException("The config bridge returned an empty config".toString());
                    }
                    flat = !config.getFlat().isEmpty() ? config.getFlat() : DefaultSettingsRepository.flatten$default(defaultSettingsRepository, config.getConfig(), null, 2, null);
                    if (flat.isEmpty()) {
                        throw new IllegalStateException("Config values empty".toString());
                    }
                    if (!config.getResolved()) {
                        resolutionError = config.getResolutionError();
                        if (resolutionError == null) {
                            resolutionError = "Bridge did not return resolved config";
                        }
                        throw new IllegalStateException(resolutionError.toString());
                    }
                    Result.Companion companion8 = Result.Companion;
                    this.L$0 = $this$withLock_u24default$iv4;
                    this.L$1 = defaultSettingsRepository;
                    this.L$2 = bundled;
                    this.L$3 = schema2;
                    this.L$4 = flat;
                    this.L$5 = null;
                    this.label = 5;
                    objFetchMoa = defaultSettingsRepository.fetchMoa(service3, this);
                    if (objFetchMoa == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result6 = objFetchMoa;
                    bundled2 = bundled;
                    defaultSettingsRepository2 = defaultSettingsRepository;
                    bundled3 = owner$iv3;
                    $this$withLock_u24default$iv5 = $this$withLock_u24default$iv4;
                    map = flat;
                    $result7 = $result2;
                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((MoaSnapshot) $result6);
                    map2 = map;
                    schema3 = schema2;
                    bundled4 = bundled2;
                    defaultSettingsRepository3 = defaultSettingsRepository2;
                    owner$iv4 = bundled3;
                    $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                    $i$f$withLock5 = $i$f$withLock4;
                    if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                        objM7388constructorimpl = null;
                    }
                    MoaSnapshot moa4 = (MoaSnapshot) objM7388constructorimpl;
                    Map<String, HermesConfigFieldDto> fields3 = schema3.getFields();
                    result$iv = new LinkedHashMap();
                    java.util.Iterator r11 = fields3.entrySet().iterator();
                    while (r11.hasNext()) {
                        java.util.Map.Entry<String, HermesConfigFieldDto> entry = (java.util.Map.Entry<String, HermesConfigFieldDto>) r11.next();
                        it4 = entry.getKey();
                        if (Intrinsics.areEqual(it4, "model")) {
                            z3 = false;
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            entry = (java.util.Map.Entry) r11.next();
                            result$iv.put(entry.getKey(), entry.getValue());
                        }
                    }
                    HermesConfigSchemaDto safeSchema3 = HermesConfigSchemaDto.copy$default(schema3, result$iv, null, null, null, 14, null);
                    objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(defaultSettingsRepository3.toSnapshot(safeSchema3, map2, true, null, schema3.getSource(), moa4));
                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                    bundled5 = bundled4;
                    defaultSettingsRepository4 = defaultSettingsRepository3;
                    owner$iv2 = owner$iv4;
                    failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                    if (failure != null) {
                        prior = defaultSettingsRepository4.cached;
                        if (prior != null) {
                            mapEmptyMap = prior.getValues();
                        } else {
                            mapEmptyMap = null;
                        }
                        if (mapEmptyMap == null) {
                            mapEmptyMap = MapsKt.emptyMap();
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                        if (prior != null) {
                            values = prior.getValues();
                        } else {
                            values = null;
                        }
                        if (values != null) {
                            z2 = true;
                        } else {
                            z2 = true;
                        }
                        if (z2) {
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                        } else {
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                        message = failure.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                        Unit unit4 = Unit.INSTANCE;
                        String string7 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                        Intrinsics.checkNotNullExpressionValue(string7, "toString(...)");
                        String string8 = StringsKt.trim((CharSequence) string7).toString();
                        if (prior != null) {
                            schemaSource = "bundled";
                        } else {
                            schemaSource = "bundled";
                        }
                        String str5 = schemaSource;
                        if (prior != null) {
                            moa = prior.getMoa();
                        } else {
                            moa = null;
                        }
                        objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string8, str5, moa);
                    }
                    snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                    if (snap.getRemoteAvailable()) {
                        defaultSettingsRepository4.cached = snap;
                        defaultSettingsRepository4.persistLastGood(snap);
                    } else if (defaultSettingsRepository4.cached == null) {
                        defaultSettingsRepository4.cached = disk;
                    }
                    it2 = snap.getMoa();
                    if (it2 != null) {
                        if (it2.getRemoteAvailable()) {
                            it3 = it2;
                        } else {
                            it3 = null;
                        }
                        if (it3 != null) {
                            defaultSettingsRepository4.moaCached = it3;
                        }
                    }
                    snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                    if (!snap2.getRemoteAvailable()) {
                        lastGood = defaultSettingsRepository4.cached;
                        if (lastGood == null) {
                            lastGood = defaultSettingsRepository4.readLastGood();
                        }
                        disk2 = lastGood;
                        if (disk2 != null) {
                            error = snap2.getError();
                            if (error == null) {
                                str = "Live settings unavailable. Showing last-good snapshot.";
                            } else {
                                str = error;
                            }
                            snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                        }
                        break;
                    }
                    $this$withLock_u24default$iv2.unlock(owner$iv2);
                    return snap2;
                    Result.Companion companion9 = Result.Companion;
                    objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                    bundled5 = bundled;
                    defaultSettingsRepository4 = defaultSettingsRepository;
                    owner$iv2 = owner$iv3;
                    failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                    if (failure != null) {
                        prior = defaultSettingsRepository4.cached;
                        if (prior != null) {
                            mapEmptyMap = prior.getValues();
                        } else {
                            mapEmptyMap = null;
                        }
                        if (mapEmptyMap == null) {
                            mapEmptyMap = MapsKt.emptyMap();
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                        if (prior != null) {
                            values = prior.getValues();
                        } else {
                            values = null;
                        }
                        if (values != null) {
                            z2 = true;
                        } else {
                            z2 = true;
                        }
                        if (z2) {
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                        } else {
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                        message = failure.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                        Unit unit5 = Unit.INSTANCE;
                        String string9 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                        Intrinsics.checkNotNullExpressionValue(string9, "toString(...)");
                        String string10 = StringsKt.trim((CharSequence) string9).toString();
                        if (prior != null) {
                            schemaSource = "bundled";
                        } else {
                            schemaSource = "bundled";
                        }
                        String str6 = schemaSource;
                        if (prior != null) {
                            moa = prior.getMoa();
                        } else {
                            moa = null;
                        }
                        objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string10, str6, moa);
                    }
                    snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                    if (snap.getRemoteAvailable()) {
                        defaultSettingsRepository4.cached = snap;
                        defaultSettingsRepository4.persistLastGood(snap);
                    } else if (defaultSettingsRepository4.cached == null) {
                        defaultSettingsRepository4.cached = disk;
                    }
                    it2 = snap.getMoa();
                    if (it2 != null) {
                        if (it2.getRemoteAvailable()) {
                            it3 = it2;
                        } else {
                            it3 = null;
                        }
                        if (it3 != null) {
                            defaultSettingsRepository4.moaCached = it3;
                        }
                    }
                    snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                    if (!snap2.getRemoteAvailable()) {
                        lastGood = defaultSettingsRepository4.cached;
                        if (lastGood == null) {
                            lastGood = defaultSettingsRepository4.readLastGood();
                        }
                        disk2 = lastGood;
                        if (disk2 != null) {
                            error = snap2.getError();
                            if (error == null) {
                                str = "Live settings unavailable. Showing last-good snapshot.";
                            } else {
                                str = error;
                            }
                            snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                        }
                        break;
                    }
                    $this$withLock_u24default$iv2.unlock(owner$iv2);
                    return snap2;
                case 2:
                    $result3 = obj;
                    $i$f$withLock2 = 0;
                    CoroutineScope $this$invokeSuspend_u24lambda_u2412_u24lambda_u247 = (CoroutineScope) this.L$3;
                    bundled = (HermesConfigSchemaDto) this.L$2;
                    defaultSettingsRepository = (DefaultSettingsRepository) this.L$1;
                    owner$iv3 = null;
                    Mutex $this$withLock_u24default$iv6 = (Mutex) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result3);
                        i = 0;
                        i2 = 0;
                        $result2 = $result3;
                        $i$f$withLock3 = 0;
                        $this$withLock_u24default$iv = $this$withLock_u24default$iv6;
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u245 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u247;
                        service = (HermesConfigApi) $result3;
                        this.L$0 = $this$withLock_u24default$iv;
                        this.L$1 = defaultSettingsRepository;
                        this.L$2 = bundled;
                        this.L$3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u245;
                        this.L$4 = service;
                        this.label = 3;
                        objSchema = service.schema(this);
                        if (objSchema == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        CoroutineScope coroutineScope3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u245;
                        service2 = service;
                        $result4 = objSchema;
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246 = coroutineScope3;
                        schemaResponse = (Response) $result4;
                        if (schemaResponse.isSuccessful()) {
                            throw new IllegalStateException(("Schema HTTP " + schemaResponse.code()).toString());
                        }
                        schema = (HermesConfigSchemaDto) schemaResponse.body();
                        if (schema != null) {
                            throw new IllegalStateException("The config bridge returned an empty schema".toString());
                        }
                        if (!schema.getFields().isEmpty()) {
                            throw new IllegalStateException("Schema has zero fields".toString());
                        }
                        this.L$0 = $this$withLock_u24default$iv;
                        this.L$1 = defaultSettingsRepository;
                        this.L$2 = bundled;
                        this.L$3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246;
                        this.L$4 = service2;
                        this.L$5 = schema;
                        this.label = 4;
                        objConfig = service2.config(true, true, this);
                        if (objConfig == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        HermesConfigApi hermesConfigApi3 = service2;
                        schema2 = schema;
                        $result5 = objConfig;
                        i3 = i;
                        service3 = hermesConfigApi3;
                        $i$f$withLock4 = $i$f$withLock3;
                        $this$withLock_u24default$iv4 = $this$withLock_u24default$iv;
                        configResponse = (Response) $result5;
                        if (!configResponse.isSuccessful()) {
                            throw new IllegalStateException(("Config HTTP " + configResponse.code()).toString());
                        }
                        config = (HermesConfigResponseDto) configResponse.body();
                        if (config == null) {
                            throw new IllegalStateException("The config bridge returned an empty config".toString());
                        }
                        flat = !config.getFlat().isEmpty() ? config.getFlat() : DefaultSettingsRepository.flatten$default(defaultSettingsRepository, config.getConfig(), null, 2, null);
                        if (flat.isEmpty()) {
                            throw new IllegalStateException("Config values empty".toString());
                        }
                        if (!config.getResolved()) {
                            resolutionError = config.getResolutionError();
                            if (resolutionError == null) {
                                resolutionError = "Bridge did not return resolved config";
                            }
                            throw new IllegalStateException(resolutionError.toString());
                        }
                        Result.Companion companion10 = Result.Companion;
                        this.L$0 = $this$withLock_u24default$iv4;
                        this.L$1 = defaultSettingsRepository;
                        this.L$2 = bundled;
                        this.L$3 = schema2;
                        this.L$4 = flat;
                        this.L$5 = null;
                        this.label = 5;
                        objFetchMoa = defaultSettingsRepository.fetchMoa(service3, this);
                        if (objFetchMoa == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result6 = objFetchMoa;
                        bundled2 = bundled;
                        defaultSettingsRepository2 = defaultSettingsRepository;
                        bundled3 = owner$iv3;
                        $this$withLock_u24default$iv5 = $this$withLock_u24default$iv4;
                        map = flat;
                        $result7 = $result2;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((MoaSnapshot) $result6);
                        map2 = map;
                        schema3 = schema2;
                        bundled4 = bundled2;
                        defaultSettingsRepository3 = defaultSettingsRepository2;
                        owner$iv4 = bundled3;
                        $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                        $i$f$withLock5 = $i$f$withLock4;
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = null;
                        }
                        MoaSnapshot moa5 = (MoaSnapshot) objM7388constructorimpl;
                        Map<String, HermesConfigFieldDto> fields4 = schema3.getFields();
                        result$iv = new LinkedHashMap();
                        java.util.Iterator r11U16 = fields4.entrySet().iterator();
                        while (r11U16.hasNext()) {
    java.util.Map.Entry<String, HermesConfigFieldDto> entry = (java.util.Map.Entry<String, HermesConfigFieldDto>) r11U16.next();

                            it4 = entry.getKey();
                            if (Intrinsics.areEqual(it4, "model")) {
                                z3 = false;
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                result$iv.put(entry.getKey(), entry.getValue());
                            }
                        }
                        HermesConfigSchemaDto safeSchema4 = HermesConfigSchemaDto.copy$default(schema3, result$iv, null, null, null, 14, null);
                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(defaultSettingsRepository3.toSnapshot(safeSchema4, map2, true, null, schema3.getSource(), moa5));
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        bundled5 = bundled4;
                        defaultSettingsRepository4 = defaultSettingsRepository3;
                        owner$iv2 = owner$iv4;
                        failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                        if (failure != null) {
                            prior = defaultSettingsRepository4.cached;
                            if (prior != null) {
                                mapEmptyMap = prior.getValues();
                            } else {
                                mapEmptyMap = null;
                            }
                            if (mapEmptyMap == null) {
                                mapEmptyMap = MapsKt.emptyMap();
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                            if (prior != null) {
                                values = prior.getValues();
                            } else {
                                values = null;
                            }
                            if (values != null) {
                                z2 = true;
                            } else {
                                z2 = true;
                            }
                            if (z2) {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                            } else {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                            message = failure.getMessage();
                            if (message == null) {
                                message = "";
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                            Unit unit6 = Unit.INSTANCE;
                            String string11 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                            Intrinsics.checkNotNullExpressionValue(string11, "toString(...)");
                            String string12 = StringsKt.trim((CharSequence) string11).toString();
                            if (prior != null) {
                                schemaSource = "bundled";
                            } else {
                                schemaSource = "bundled";
                            }
                            String str7 = schemaSource;
                            if (prior != null) {
                                moa = prior.getMoa();
                            } else {
                                moa = null;
                            }
                            objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string12, str7, moa);
                        }
                        snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (snap.getRemoteAvailable()) {
                            defaultSettingsRepository4.cached = snap;
                            defaultSettingsRepository4.persistLastGood(snap);
                        } else if (defaultSettingsRepository4.cached == null) {
                            defaultSettingsRepository4.cached = disk;
                        }
                        it2 = snap.getMoa();
                        if (it2 != null) {
                            if (it2.getRemoteAvailable()) {
                                it3 = it2;
                            } else {
                                it3 = null;
                            }
                            if (it3 != null) {
                                defaultSettingsRepository4.moaCached = it3;
                            }
                        }
                        snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (!snap2.getRemoteAvailable()) {
                            lastGood = defaultSettingsRepository4.cached;
                            if (lastGood == null) {
                                lastGood = defaultSettingsRepository4.readLastGood();
                            }
                            disk2 = lastGood;
                            if (disk2 != null) {
                                error = snap2.getError();
                                if (error == null) {
                                    str = "Live settings unavailable. Showing last-good snapshot.";
                                } else {
                                    str = error;
                                }
                                snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                            }
                            break;
                        }
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return snap2;
                        Result.Companion companion11 = Result.Companion;
                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        bundled5 = bundled;
                        defaultSettingsRepository4 = defaultSettingsRepository;
                        owner$iv2 = owner$iv3;
                        failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                        if (failure != null) {
                            prior = defaultSettingsRepository4.cached;
                            if (prior != null) {
                                mapEmptyMap = prior.getValues();
                            } else {
                                mapEmptyMap = null;
                            }
                            if (mapEmptyMap == null) {
                                mapEmptyMap = MapsKt.emptyMap();
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                            if (prior != null) {
                                values = prior.getValues();
                            } else {
                                values = null;
                            }
                            if (values != null) {
                                z2 = true;
                            } else {
                                z2 = true;
                            }
                            if (z2) {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                            } else {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                            message = failure.getMessage();
                            if (message == null) {
                                message = "";
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                            Unit unit7 = Unit.INSTANCE;
                            String string13 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                            Intrinsics.checkNotNullExpressionValue(string13, "toString(...)");
                            String string14 = StringsKt.trim((CharSequence) string13).toString();
                            if (prior != null) {
                                schemaSource = "bundled";
                            } else {
                                schemaSource = "bundled";
                            }
                            String str8 = schemaSource;
                            if (prior != null) {
                                moa = prior.getMoa();
                            } else {
                                moa = null;
                            }
                            objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string14, str8, moa);
                        }
                        snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (snap.getRemoteAvailable()) {
                            defaultSettingsRepository4.cached = snap;
                            defaultSettingsRepository4.persistLastGood(snap);
                        } else if (defaultSettingsRepository4.cached == null) {
                            defaultSettingsRepository4.cached = disk;
                        }
                        it2 = snap.getMoa();
                        if (it2 != null) {
                            if (it2.getRemoteAvailable()) {
                                it3 = it2;
                            } else {
                                it3 = null;
                            }
                            if (it3 != null) {
                                defaultSettingsRepository4.moaCached = it3;
                            }
                        }
                        snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (!snap2.getRemoteAvailable()) {
                            lastGood = defaultSettingsRepository4.cached;
                            if (lastGood == null) {
                                lastGood = defaultSettingsRepository4.readLastGood();
                            }
                            disk2 = lastGood;
                            if (disk2 != null) {
                                error = snap2.getError();
                                if (error == null) {
                                    str = "Live settings unavailable. Showing last-good snapshot.";
                                } else {
                                    str = error;
                                }
                                snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                            }
                            break;
                        }
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return snap2;
                    } catch (Throwable th14) {
                        $this$withLock_u24default$iv3 = $this$withLock_u24default$iv6;
                    }
                    break;
                case 3:
                    $result4 = obj;
                    $i$f$withLock2 = 0;
                    HermesConfigApi service4 = (HermesConfigApi) this.L$4;
                    CoroutineScope $this$invokeSuspend_u24lambda_u2412_u24lambda_u248 = (CoroutineScope) this.L$3;
                    bundled = (HermesConfigSchemaDto) this.L$2;
                    defaultSettingsRepository = (DefaultSettingsRepository) this.L$1;
                    owner$iv3 = null;
                    Mutex $this$withLock_u24default$iv7 = (Mutex) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result4);
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u248;
                        service2 = service4;
                        $i$f$withLock3 = 0;
                        $this$withLock_u24default$iv = $this$withLock_u24default$iv7;
                        i = 0;
                        i2 = 0;
                        $result2 = $result4;
                        schemaResponse = (Response) $result4;
                        if (schemaResponse.isSuccessful()) {
                            throw new IllegalStateException(("Schema HTTP " + schemaResponse.code()).toString());
                        }
                        schema = (HermesConfigSchemaDto) schemaResponse.body();
                        if (schema != null) {
                            throw new IllegalStateException("The config bridge returned an empty schema".toString());
                        }
                        if (!schema.getFields().isEmpty()) {
                            throw new IllegalStateException("Schema has zero fields".toString());
                        }
                        this.L$0 = $this$withLock_u24default$iv;
                        this.L$1 = defaultSettingsRepository;
                        this.L$2 = bundled;
                        this.L$3 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246;
                        this.L$4 = service2;
                        this.L$5 = schema;
                        this.label = 4;
                        objConfig = service2.config(true, true, this);
                        if (objConfig == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        HermesConfigApi hermesConfigApi4 = service2;
                        schema2 = schema;
                        $result5 = objConfig;
                        i3 = i;
                        service3 = hermesConfigApi4;
                        $i$f$withLock4 = $i$f$withLock3;
                        $this$withLock_u24default$iv4 = $this$withLock_u24default$iv;
                        configResponse = (Response) $result5;
                        if (!configResponse.isSuccessful()) {
                            throw new IllegalStateException(("Config HTTP " + configResponse.code()).toString());
                        }
                        config = (HermesConfigResponseDto) configResponse.body();
                        if (config == null) {
                            throw new IllegalStateException("The config bridge returned an empty config".toString());
                        }
                        flat = !config.getFlat().isEmpty() ? config.getFlat() : DefaultSettingsRepository.flatten$default(defaultSettingsRepository, config.getConfig(), null, 2, null);
                        if (flat.isEmpty()) {
                            throw new IllegalStateException("Config values empty".toString());
                        }
                        if (!config.getResolved()) {
                            resolutionError = config.getResolutionError();
                            if (resolutionError == null) {
                                resolutionError = "Bridge did not return resolved config";
                            }
                            throw new IllegalStateException(resolutionError.toString());
                        }
                        Result.Companion companion12 = Result.Companion;
                        this.L$0 = $this$withLock_u24default$iv4;
                        this.L$1 = defaultSettingsRepository;
                        this.L$2 = bundled;
                        this.L$3 = schema2;
                        this.L$4 = flat;
                        this.L$5 = null;
                        this.label = 5;
                        objFetchMoa = defaultSettingsRepository.fetchMoa(service3, this);
                        if (objFetchMoa == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result6 = objFetchMoa;
                        bundled2 = bundled;
                        defaultSettingsRepository2 = defaultSettingsRepository;
                        bundled3 = owner$iv3;
                        $this$withLock_u24default$iv5 = $this$withLock_u24default$iv4;
                        map = flat;
                        $result7 = $result2;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((MoaSnapshot) $result6);
                        map2 = map;
                        schema3 = schema2;
                        bundled4 = bundled2;
                        defaultSettingsRepository3 = defaultSettingsRepository2;
                        owner$iv4 = bundled3;
                        $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                        $i$f$withLock5 = $i$f$withLock4;
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = null;
                        }
                        MoaSnapshot moa6 = (MoaSnapshot) objM7388constructorimpl;
                        Map<String, HermesConfigFieldDto> fields5 = schema3.getFields();
                        result$iv = new LinkedHashMap();
                        java.util.Iterator r11U17 = fields5.entrySet().iterator();
                        while (r11U17.hasNext()) {
    java.util.Map.Entry<String, HermesConfigFieldDto> entry = (java.util.Map.Entry<String, HermesConfigFieldDto>) r11U17.next();

                            it4 = entry.getKey();
                            if (Intrinsics.areEqual(it4, "model")) {
                                z3 = false;
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                result$iv.put(entry.getKey(), entry.getValue());
                            }
                        }
                        HermesConfigSchemaDto safeSchema5 = HermesConfigSchemaDto.copy$default(schema3, result$iv, null, null, null, 14, null);
                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(defaultSettingsRepository3.toSnapshot(safeSchema5, map2, true, null, schema3.getSource(), moa6));
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        bundled5 = bundled4;
                        defaultSettingsRepository4 = defaultSettingsRepository3;
                        owner$iv2 = owner$iv4;
                        failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                        if (failure != null) {
                            prior = defaultSettingsRepository4.cached;
                            if (prior != null) {
                                mapEmptyMap = prior.getValues();
                            } else {
                                mapEmptyMap = null;
                            }
                            if (mapEmptyMap == null) {
                                mapEmptyMap = MapsKt.emptyMap();
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                            if (prior != null) {
                                values = prior.getValues();
                            } else {
                                values = null;
                            }
                            if (values != null) {
                                z2 = true;
                            } else {
                                z2 = true;
                            }
                            if (z2) {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                            } else {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                            message = failure.getMessage();
                            if (message == null) {
                                message = "";
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                            Unit unit8 = Unit.INSTANCE;
                            String string15 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                            Intrinsics.checkNotNullExpressionValue(string15, "toString(...)");
                            String string16 = StringsKt.trim((CharSequence) string15).toString();
                            if (prior != null) {
                                schemaSource = "bundled";
                            } else {
                                schemaSource = "bundled";
                            }
                            String str9 = schemaSource;
                            if (prior != null) {
                                moa = prior.getMoa();
                            } else {
                                moa = null;
                            }
                            objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string16, str9, moa);
                        }
                        snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (snap.getRemoteAvailable()) {
                            defaultSettingsRepository4.cached = snap;
                            defaultSettingsRepository4.persistLastGood(snap);
                        } else if (defaultSettingsRepository4.cached == null) {
                            defaultSettingsRepository4.cached = disk;
                        }
                        it2 = snap.getMoa();
                        if (it2 != null) {
                            if (it2.getRemoteAvailable()) {
                                it3 = it2;
                            } else {
                                it3 = null;
                            }
                            if (it3 != null) {
                                defaultSettingsRepository4.moaCached = it3;
                            }
                        }
                        snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (!snap2.getRemoteAvailable()) {
                            lastGood = defaultSettingsRepository4.cached;
                            if (lastGood == null) {
                                lastGood = defaultSettingsRepository4.readLastGood();
                            }
                            disk2 = lastGood;
                            if (disk2 != null) {
                                error = snap2.getError();
                                if (error == null) {
                                    str = "Live settings unavailable. Showing last-good snapshot.";
                                } else {
                                    str = error;
                                }
                                snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                            }
                            break;
                        }
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return snap2;
                        Result.Companion companion13 = Result.Companion;
                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        bundled5 = bundled;
                        defaultSettingsRepository4 = defaultSettingsRepository;
                        owner$iv2 = owner$iv3;
                        failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                        if (failure != null) {
                            prior = defaultSettingsRepository4.cached;
                            if (prior != null) {
                                mapEmptyMap = prior.getValues();
                            } else {
                                mapEmptyMap = null;
                            }
                            if (mapEmptyMap == null) {
                                mapEmptyMap = MapsKt.emptyMap();
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                            if (prior != null) {
                                values = prior.getValues();
                            } else {
                                values = null;
                            }
                            if (values != null) {
                                z2 = true;
                            } else {
                                z2 = true;
                            }
                            if (z2) {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                            } else {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                            message = failure.getMessage();
                            if (message == null) {
                                message = "";
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                            Unit unit9 = Unit.INSTANCE;
                            String string17 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                            Intrinsics.checkNotNullExpressionValue(string17, "toString(...)");
                            String string18 = StringsKt.trim((CharSequence) string17).toString();
                            if (prior != null) {
                                schemaSource = "bundled";
                            } else {
                                schemaSource = "bundled";
                            }
                            String str10 = schemaSource;
                            if (prior != null) {
                                moa = prior.getMoa();
                            } else {
                                moa = null;
                            }
                            objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string18, str10, moa);
                        }
                        snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (snap.getRemoteAvailable()) {
                            defaultSettingsRepository4.cached = snap;
                            defaultSettingsRepository4.persistLastGood(snap);
                        } else if (defaultSettingsRepository4.cached == null) {
                            defaultSettingsRepository4.cached = disk;
                        }
                        it2 = snap.getMoa();
                        if (it2 != null) {
                            if (it2.getRemoteAvailable()) {
                                it3 = it2;
                            } else {
                                it3 = null;
                            }
                            if (it3 != null) {
                                defaultSettingsRepository4.moaCached = it3;
                            }
                        }
                        snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (!snap2.getRemoteAvailable()) {
                            lastGood = defaultSettingsRepository4.cached;
                            if (lastGood == null) {
                                lastGood = defaultSettingsRepository4.readLastGood();
                            }
                            disk2 = lastGood;
                            if (disk2 != null) {
                                error = snap2.getError();
                                if (error == null) {
                                    str = "Live settings unavailable. Showing last-good snapshot.";
                                } else {
                                    str = error;
                                }
                                snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                            }
                            break;
                        }
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return snap2;
                    } catch (Throwable th15) {
                        $this$withLock_u24default$iv3 = $this$withLock_u24default$iv7;
                    }
                    break;
                case 4:
                    $result5 = obj;
                    $i$f$withLock2 = 0;
                    schema2 = (HermesConfigSchemaDto) this.L$5;
                    service3 = (HermesConfigApi) this.L$4;
                    bundled = (HermesConfigSchemaDto) this.L$2;
                    defaultSettingsRepository = (DefaultSettingsRepository) this.L$1;
                    owner$iv3 = null;
                    $this$withLock_u24default$iv3 = (Mutex) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result5);
                        i3 = 0;
                        $i$f$withLock4 = 0;
                        $this$withLock_u24default$iv4 = $this$withLock_u24default$iv3;
                        $result2 = $result5;
                        configResponse = (Response) $result5;
                        if (!configResponse.isSuccessful()) {
                            throw new IllegalStateException(("Config HTTP " + configResponse.code()).toString());
                        }
                        config = (HermesConfigResponseDto) configResponse.body();
                        if (config == null) {
                            throw new IllegalStateException("The config bridge returned an empty config".toString());
                        }
                        flat = !config.getFlat().isEmpty() ? config.getFlat() : DefaultSettingsRepository.flatten$default(defaultSettingsRepository, config.getConfig(), null, 2, null);
                        if (flat.isEmpty()) {
                            throw new IllegalStateException("Config values empty".toString());
                        }
                        if (!config.getResolved()) {
                            resolutionError = config.getResolutionError();
                            if (resolutionError == null) {
                                resolutionError = "Bridge did not return resolved config";
                            }
                            throw new IllegalStateException(resolutionError.toString());
                        }
                        Result.Companion companion14 = Result.Companion;
                        this.L$0 = $this$withLock_u24default$iv4;
                        this.L$1 = defaultSettingsRepository;
                        this.L$2 = bundled;
                        this.L$3 = schema2;
                        this.L$4 = flat;
                        this.L$5 = null;
                        this.label = 5;
                        objFetchMoa = defaultSettingsRepository.fetchMoa(service3, this);
                        if (objFetchMoa == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result6 = objFetchMoa;
                        bundled2 = bundled;
                        defaultSettingsRepository2 = defaultSettingsRepository;
                        bundled3 = owner$iv3;
                        $this$withLock_u24default$iv5 = $this$withLock_u24default$iv4;
                        map = flat;
                        $result7 = $result2;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((MoaSnapshot) $result6);
                        map2 = map;
                        schema3 = schema2;
                        bundled4 = bundled2;
                        defaultSettingsRepository3 = defaultSettingsRepository2;
                        owner$iv4 = bundled3;
                        $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                        $i$f$withLock5 = $i$f$withLock4;
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = null;
                        }
                        MoaSnapshot moa7 = (MoaSnapshot) objM7388constructorimpl;
                        Map<String, HermesConfigFieldDto> fields6 = schema3.getFields();
                        result$iv = new LinkedHashMap();
                        java.util.Iterator r11U18 = fields6.entrySet().iterator();
                        while (r11U18.hasNext()) {
    java.util.Map.Entry<String, HermesConfigFieldDto> entry = (java.util.Map.Entry<String, HermesConfigFieldDto>) r11U18.next();

                            it4 = entry.getKey();
                            if (Intrinsics.areEqual(it4, "model")) {
                                z3 = false;
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                result$iv.put(entry.getKey(), entry.getValue());
                            }
                        }
                        HermesConfigSchemaDto safeSchema6 = HermesConfigSchemaDto.copy$default(schema3, result$iv, null, null, null, 14, null);
                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(defaultSettingsRepository3.toSnapshot(safeSchema6, map2, true, null, schema3.getSource(), moa7));
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        bundled5 = bundled4;
                        defaultSettingsRepository4 = defaultSettingsRepository3;
                        owner$iv2 = owner$iv4;
                        failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                        if (failure != null) {
                            prior = defaultSettingsRepository4.cached;
                            if (prior != null) {
                                mapEmptyMap = prior.getValues();
                            } else {
                                mapEmptyMap = null;
                            }
                            if (mapEmptyMap == null) {
                                mapEmptyMap = MapsKt.emptyMap();
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                            if (prior != null) {
                                values = prior.getValues();
                            } else {
                                values = null;
                            }
                            if (values != null) {
                                z2 = true;
                            } else {
                                z2 = true;
                            }
                            if (z2) {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                            } else {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                            message = failure.getMessage();
                            if (message == null) {
                                message = "";
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                            Unit unit10 = Unit.INSTANCE;
                            String string19 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                            Intrinsics.checkNotNullExpressionValue(string19, "toString(...)");
                            String string110 = StringsKt.trim((CharSequence) string19).toString();
                            if (prior != null) {
                                schemaSource = "bundled";
                            } else {
                                schemaSource = "bundled";
                            }
                            String str11 = schemaSource;
                            if (prior != null) {
                                moa = prior.getMoa();
                            } else {
                                moa = null;
                            }
                            objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string110, str11, moa);
                        }
                        snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (snap.getRemoteAvailable()) {
                            defaultSettingsRepository4.cached = snap;
                            defaultSettingsRepository4.persistLastGood(snap);
                        } else if (defaultSettingsRepository4.cached == null) {
                            defaultSettingsRepository4.cached = disk;
                        }
                        it2 = snap.getMoa();
                        if (it2 != null) {
                            if (it2.getRemoteAvailable()) {
                                it3 = it2;
                            } else {
                                it3 = null;
                            }
                            if (it3 != null) {
                                defaultSettingsRepository4.moaCached = it3;
                            }
                        }
                        snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (!snap2.getRemoteAvailable()) {
                            lastGood = defaultSettingsRepository4.cached;
                            if (lastGood == null) {
                                lastGood = defaultSettingsRepository4.readLastGood();
                            }
                            disk2 = lastGood;
                            if (disk2 != null) {
                                error = snap2.getError();
                                if (error == null) {
                                    str = "Live settings unavailable. Showing last-good snapshot.";
                                } else {
                                    str = error;
                                }
                                snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                            }
                            break;
                        }
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return snap2;
                    } catch (Throwable th16) {
                    }
                    Result.Companion companion15 = Result.Companion;
                    objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                    bundled5 = bundled;
                    defaultSettingsRepository4 = defaultSettingsRepository;
                    owner$iv2 = owner$iv3;
                    failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                    if (failure != null) {
                        prior = defaultSettingsRepository4.cached;
                        if (prior != null) {
                            mapEmptyMap = prior.getValues();
                        } else {
                            mapEmptyMap = null;
                        }
                        if (mapEmptyMap == null) {
                            mapEmptyMap = MapsKt.emptyMap();
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                        if (prior != null) {
                            values = prior.getValues();
                        } else {
                            values = null;
                        }
                        if (values != null) {
                            z2 = true;
                        } else {
                            z2 = true;
                        }
                        if (z2) {
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                        } else {
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                        message = failure.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                        Unit unit11 = Unit.INSTANCE;
                        String string111 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                        Intrinsics.checkNotNullExpressionValue(string111, "toString(...)");
                        String string112 = StringsKt.trim((CharSequence) string111).toString();
                        if (prior != null) {
                            schemaSource = "bundled";
                        } else {
                            schemaSource = "bundled";
                        }
                        String str12 = schemaSource;
                        if (prior != null) {
                            moa = prior.getMoa();
                        } else {
                            moa = null;
                        }
                        objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string112, str12, moa);
                    }
                    snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                    if (snap.getRemoteAvailable()) {
                        defaultSettingsRepository4.cached = snap;
                        defaultSettingsRepository4.persistLastGood(snap);
                    } else if (defaultSettingsRepository4.cached == null) {
                        defaultSettingsRepository4.cached = disk;
                    }
                    it2 = snap.getMoa();
                    if (it2 != null) {
                        if (it2.getRemoteAvailable()) {
                            it3 = it2;
                        } else {
                            it3 = null;
                        }
                        if (it3 != null) {
                            defaultSettingsRepository4.moaCached = it3;
                        }
                    }
                    snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                    if (!snap2.getRemoteAvailable()) {
                        lastGood = defaultSettingsRepository4.cached;
                        if (lastGood == null) {
                            lastGood = defaultSettingsRepository4.readLastGood();
                        }
                        disk2 = lastGood;
                        if (disk2 != null) {
                            error = snap2.getError();
                            if (error == null) {
                                str = "Live settings unavailable. Showing last-good snapshot.";
                            } else {
                                str = error;
                            }
                            snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                        }
                        break;
                    }
                    $this$withLock_u24default$iv2.unlock(owner$iv2);
                    return snap2;
                case 5:
                    $result6 = obj;
                    i3 = 0;
                    $i$f$withLock4 = 0;
                    map = (Map) this.L$4;
                    schema2 = (HermesConfigSchemaDto) this.L$3;
                    bundled2 = (HermesConfigSchemaDto) this.L$2;
                    defaultSettingsRepository2 = (DefaultSettingsRepository) this.L$1;
                    bundled3 = null;
                    $this$withLock_u24default$iv5 = (Mutex) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result6);
                        $result7 = $result6;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((MoaSnapshot) $result6);
                        map2 = map;
                        schema3 = schema2;
                        bundled4 = bundled2;
                        defaultSettingsRepository3 = defaultSettingsRepository2;
                        owner$iv4 = bundled3;
                        $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                        $i$f$withLock5 = $i$f$withLock4;
                    } catch (Throwable th17) {
                        Result.Companion companion16 = Result.Companion;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th17));
                        map2 = map;
                        schema3 = schema2;
                        bundled4 = bundled2;
                        defaultSettingsRepository3 = defaultSettingsRepository2;
                        owner$iv4 = bundled3;
                        $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                        $i$f$withLock5 = $i$f$withLock4;
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = null;
                        }
                        MoaSnapshot moa8 = (MoaSnapshot) objM7388constructorimpl;
                        Map<String, HermesConfigFieldDto> fields7 = schema3.getFields();
                        result$iv = new LinkedHashMap();
                        java.util.Iterator r11U19 = fields7.entrySet().iterator();
                        while (r11U19.hasNext()) {
    java.util.Map.Entry<String, HermesConfigFieldDto> entry = (java.util.Map.Entry<String, HermesConfigFieldDto>) r11U19.next();

                            it4 = entry.getKey();
                            if (Intrinsics.areEqual(it4, "model")) {
                                z3 = false;
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                result$iv.put(entry.getKey(), entry.getValue());
                            }
                        }
                        HermesConfigSchemaDto safeSchema7 = HermesConfigSchemaDto.copy$default(schema3, result$iv, null, null, null, 14, null);
                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(defaultSettingsRepository3.toSnapshot(safeSchema7, map2, true, null, schema3.getSource(), moa8));
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        bundled5 = bundled4;
                        defaultSettingsRepository4 = defaultSettingsRepository3;
                        owner$iv2 = owner$iv4;
                        failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                        if (failure != null) {
                            prior = defaultSettingsRepository4.cached;
                            if (prior != null) {
                                mapEmptyMap = prior.getValues();
                            } else {
                                mapEmptyMap = null;
                            }
                            if (mapEmptyMap == null) {
                                mapEmptyMap = MapsKt.emptyMap();
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                            if (prior != null) {
                                values = prior.getValues();
                            } else {
                                values = null;
                            }
                            if (values != null) {
                                z2 = true;
                            } else {
                                z2 = true;
                            }
                            if (z2) {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                            } else {
                                $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                            message = failure.getMessage();
                            if (message == null) {
                                message = "";
                            }
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                            Unit unit12 = Unit.INSTANCE;
                            String string113 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                            Intrinsics.checkNotNullExpressionValue(string113, "toString(...)");
                            String string114 = StringsKt.trim((CharSequence) string113).toString();
                            if (prior != null) {
                                schemaSource = "bundled";
                            } else {
                                schemaSource = "bundled";
                            }
                            String str13 = schemaSource;
                            if (prior != null) {
                                moa = prior.getMoa();
                            } else {
                                moa = null;
                            }
                            objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string114, str13, moa);
                        }
                        snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (snap.getRemoteAvailable()) {
                            defaultSettingsRepository4.cached = snap;
                            defaultSettingsRepository4.persistLastGood(snap);
                        } else if (defaultSettingsRepository4.cached == null) {
                            defaultSettingsRepository4.cached = disk;
                        }
                        it2 = snap.getMoa();
                        if (it2 != null) {
                            if (it2.getRemoteAvailable()) {
                                it3 = it2;
                            } else {
                                it3 = null;
                            }
                            if (it3 != null) {
                                defaultSettingsRepository4.moaCached = it3;
                            }
                        }
                        snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                        if (!snap2.getRemoteAvailable()) {
                            lastGood = defaultSettingsRepository4.cached;
                            if (lastGood == null) {
                                lastGood = defaultSettingsRepository4.readLastGood();
                            }
                            disk2 = lastGood;
                            if (disk2 != null) {
                                error = snap2.getError();
                                if (error == null) {
                                    str = "Live settings unavailable. Showing last-good snapshot.";
                                } else {
                                    str = error;
                                }
                                snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                            }
                            break;
                        }
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return snap2;
                    }
                    if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                        objM7388constructorimpl = null;
                    }
                    MoaSnapshot moa9 = (MoaSnapshot) objM7388constructorimpl;
                    Map<String, HermesConfigFieldDto> fields8 = schema3.getFields();
                    result$iv = new LinkedHashMap();
                    java.util.Iterator r11U20 = fields8.entrySet().iterator();
                    while (r11U20.hasNext()) {
    java.util.Map.Entry<String, HermesConfigFieldDto> entry = (java.util.Map.Entry<String, HermesConfigFieldDto>) r11U20.next();

                        it4 = entry.getKey();
                        if (Intrinsics.areEqual(it4, "model")) {
                            z3 = false;
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            result$iv.put(entry.getKey(), entry.getValue());
                        }
                    }
                    HermesConfigSchemaDto safeSchema8 = HermesConfigSchemaDto.copy$default(schema3, result$iv, null, null, null, 14, null);
                    objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(defaultSettingsRepository3.toSnapshot(safeSchema8, map2, true, null, schema3.getSource(), moa9));
                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                    bundled5 = bundled4;
                    defaultSettingsRepository4 = defaultSettingsRepository3;
                    owner$iv2 = owner$iv4;
                    failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl2);
                    if (failure != null) {
                        prior = defaultSettingsRepository4.cached;
                        if (prior != null) {
                            mapEmptyMap = prior.getValues();
                        } else {
                            mapEmptyMap = null;
                        }
                        if (mapEmptyMap == null) {
                            mapEmptyMap = MapsKt.emptyMap();
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245 = new StringBuilder();
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Live settings unavailable. ");
                        if (prior != null) {
                            values = prior.getValues();
                        } else {
                            values = null;
                        }
                        if (values != null) {
                            z2 = true;
                        } else {
                            z2 = true;
                        }
                        if (z2) {
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("No last-good snapshot.");
                        } else {
                            $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append("Showing last-good snapshot.");
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(" ");
                        message = failure.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.append(message);
                        Unit unit13 = Unit.INSTANCE;
                        String string115 = $this$invokeSuspend_u24lambda_u2412_u24lambda_u246_u24lambda_u245.toString();
                        Intrinsics.checkNotNullExpressionValue(string115, "toString(...)");
                        String string116 = StringsKt.trim((CharSequence) string115).toString();
                        if (prior != null) {
                            schemaSource = "bundled";
                        } else {
                            schemaSource = "bundled";
                        }
                        String str14 = schemaSource;
                        if (prior != null) {
                            moa = prior.getMoa();
                        } else {
                            moa = null;
                        }
                        objM7388constructorimpl2 = defaultSettingsRepository4.toSnapshot(bundled5, mapEmptyMap, false, string116, str14, moa);
                    }
                    snap = (HermesSettingsSnapshot) objM7388constructorimpl2;
                    if (snap.getRemoteAvailable()) {
                        defaultSettingsRepository4.cached = snap;
                        defaultSettingsRepository4.persistLastGood(snap);
                    } else if (defaultSettingsRepository4.cached == null) {
                        defaultSettingsRepository4.cached = disk;
                    }
                    it2 = snap.getMoa();
                    if (it2 != null) {
                        if (it2.getRemoteAvailable()) {
                            it3 = it2;
                        } else {
                            it3 = null;
                        }
                        if (it3 != null) {
                            defaultSettingsRepository4.moaCached = it3;
                        }
                    }
                    snap2 = (HermesSettingsSnapshot) objM7388constructorimpl2;
                    if (!snap2.getRemoteAvailable()) {
                        lastGood = defaultSettingsRepository4.cached;
                        if (lastGood == null) {
                            lastGood = defaultSettingsRepository4.readLastGood();
                        }
                        disk2 = lastGood;
                        if (disk2 != null) {
                            error = snap2.getError();
                            if (error == null) {
                                str = "Live settings unavailable. Showing last-good snapshot.";
                            } else {
                                str = error;
                            }
                            snap2 = HermesSettingsSnapshot.copy$default(disk2, null, null, null, false, str, null, null, 103, null);
                        }
                        break;
                    }
                    $this$withLock_u24default$iv2.unlock(owner$iv2);
                    return snap2;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // ai.nous.aurora.data.repository.SettingsRepository
    public Object load(boolean forceRefresh, Continuation continuation) {
        return BuildersKt.withContext(this.dispatcher, new AnonymousClass2(forceRefresh, this, null), continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // ai.nous.aurora.data.repository.SettingsRepository
    /* JADX INFO: renamed from: loadModelOptions-gIAlu-s, reason: not valid java name */
    public Object mo58loadModelOptionsgIAlus(boolean refresh, Continuation continuation) {
        DefaultSettingsRepository$loadModelOptions$1 defaultSettingsRepository$loadModelOptions$1;
        Object objWithContext;
        if (continuation instanceof DefaultSettingsRepository$loadModelOptions$1) {
            defaultSettingsRepository$loadModelOptions$1 = (DefaultSettingsRepository$loadModelOptions$1) continuation;
            if ((defaultSettingsRepository$loadModelOptions$1.label & Integer.MIN_VALUE) != 0) {
                defaultSettingsRepository$loadModelOptions$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSettingsRepository$loadModelOptions$1 = new DefaultSettingsRepository$loadModelOptions$1(this, continuation);
            }
        } else {
            defaultSettingsRepository$loadModelOptions$1 = new DefaultSettingsRepository$loadModelOptions$1(this, continuation);
        }
        Object $result = defaultSettingsRepository$loadModelOptions$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSettingsRepository$loadModelOptions$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                boolean refresh2 = refresh;
                DefaultSettingsRepository$loadModelOptions$2 defaultSettingsRepository$loadModelOptions$2 = new DefaultSettingsRepository$loadModelOptions$2(this, refresh2, null);
                defaultSettingsRepository$loadModelOptions$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, defaultSettingsRepository$loadModelOptions$2, defaultSettingsRepository$loadModelOptions$1);
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
    @Override // ai.nous.aurora.data.repository.SettingsRepository
    /* JADX INFO: renamed from: setValue-0E7RQCE, reason: not valid java name */
    public Object mo60setValue0E7RQCE(String path, JsonElement value, Continuation continuation) {
        DefaultSettingsRepository$setValue$1 defaultSettingsRepository$setValue$1;
        Object objWithContext;
        if (continuation instanceof DefaultSettingsRepository$setValue$1) {
            defaultSettingsRepository$setValue$1 = (DefaultSettingsRepository$setValue$1) continuation;
            if ((defaultSettingsRepository$setValue$1.label & Integer.MIN_VALUE) != 0) {
                defaultSettingsRepository$setValue$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSettingsRepository$setValue$1 = new DefaultSettingsRepository$setValue$1(this, continuation);
            }
        } else {
            defaultSettingsRepository$setValue$1 = new DefaultSettingsRepository$setValue$1(this, continuation);
        }
        Object $result = defaultSettingsRepository$setValue$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSettingsRepository$setValue$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                DefaultSettingsRepository$setValue$2 defaultSettingsRepository$setValue$2 = new DefaultSettingsRepository$setValue$2(path, this, value, null);
                defaultSettingsRepository$setValue$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, defaultSettingsRepository$setValue$2, defaultSettingsRepository$setValue$1);
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
    @Override // ai.nous.aurora.data.repository.SettingsRepository
    /* JADX INFO: renamed from: setValues-gIAlu-s, reason: not valid java name */
    public Object mo61setValuesgIAlus(List<? extends Pair<String, ? extends JsonElement>> list, Continuation continuation) {
        DefaultSettingsRepository$setValues$1 defaultSettingsRepository$setValues$1;
        Object objWithContext;
        if (continuation instanceof DefaultSettingsRepository$setValues$1) {
            defaultSettingsRepository$setValues$1 = (DefaultSettingsRepository$setValues$1) continuation;
            if ((defaultSettingsRepository$setValues$1.label & Integer.MIN_VALUE) != 0) {
                defaultSettingsRepository$setValues$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSettingsRepository$setValues$1 = new DefaultSettingsRepository$setValues$1(this, continuation);
            }
        } else {
            defaultSettingsRepository$setValues$1 = new DefaultSettingsRepository$setValues$1(this, continuation);
        }
        Object $result = defaultSettingsRepository$setValues$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSettingsRepository$setValues$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                DefaultSettingsRepository$setValues$2 defaultSettingsRepository$setValues$2 = new DefaultSettingsRepository$setValues$2(list, this, null);
                defaultSettingsRepository$setValues$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, defaultSettingsRepository$setValues$2, defaultSettingsRepository$setValues$1);
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

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSettingsRepository$loadMoa$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lai/nous/aurora/data/repository/MoaSnapshot;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSettingsRepository$loadMoa$2", f = "SettingsRepository.kt", i = {}, l = {282, 282}, m = "invokeSuspend", n = {}, s = {})
    static final class C02212 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ boolean $forceRefresh;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ DefaultSettingsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02212(boolean z, DefaultSettingsRepository defaultSettingsRepository, Continuation continuation) {
            super(2, continuation);
            this.$forceRefresh = z;
            this.this$0 = defaultSettingsRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C02212 c02212 = new C02212(this.$forceRefresh, this.this$0, continuation);
            c02212.L$0 = obj;
            return c02212;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02212)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:32:0x0079 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:33:0x007a  */
        /* JADX WARN: Code duplicated, block: B:42:0x00a0  */
        /* JADX WARN: Code duplicated, block: B:44:0x00a8  */
        /* JADX WARN: Code duplicated, block: B:49:0x00d3 A[ORIG_RETURN, RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object $result2;
            DefaultSettingsRepository defaultSettingsRepository;
            Throwable err;
            MoaSnapshot moaSnapshotCopy$default;
            DefaultSettingsRepository defaultSettingsRepository2;
            int i;
            DefaultSettingsRepository defaultSettingsRepository3;
            MoaSnapshot it;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        if (!this.$forceRefresh && (it = this.this$0.moaCached) != null) {
                            if (!it.getRemoteAvailable()) {
                                it = null;
                            }
                            if (it != null) {
                                return it;
                            }
                        }
                        DefaultSettingsRepository defaultSettingsRepository4 = this.this$0;
                        Result.Companion companion = Result.Companion;
                        this.L$0 = defaultSettingsRepository4;
                        this.L$1 = defaultSettingsRepository4;
                        this.label = 1;
                        Object objService = defaultSettingsRepository4.service(this);
                        if (objService == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultSettingsRepository2 = defaultSettingsRepository4;
                        i = 0;
                        $result = objService;
                        defaultSettingsRepository3 = defaultSettingsRepository2;
                        try {
                            this.L$0 = defaultSettingsRepository3;
                            this.L$1 = null;
                            this.label = 2;
                            $result = defaultSettingsRepository2.fetchMoa((HermesConfigApi) $result, this);
                            if ($result == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            defaultSettingsRepository3.moaCached = (MoaSnapshot) $result;
                            $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl((MoaSnapshot) $result);
                            defaultSettingsRepository = this.this$0;
                            err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result2);
                            if (err == null) {
                                return $result2;
                            }
                            MoaSnapshot moaSnapshot = defaultSettingsRepository.moaCached;
                            return (moaSnapshot != null || (moaSnapshotCopy$default = MoaSnapshot.copy$default(moaSnapshot, null, null, false, null, null, false, err.getMessage(), 31, null)) == null) ? new MoaSnapshot(null, null, false, null, null, false, err.getMessage(), 31, null) : moaSnapshotCopy$default;
                        } catch (Throwable th) {
                            $result = $result;
                            Object $result3 = Result.Companion;
                            $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                        }
                        break;
                    case 1:
                        DefaultSettingsRepository defaultSettingsRepository5 = (DefaultSettingsRepository) this.L$1;
                        defaultSettingsRepository3 = (DefaultSettingsRepository) this.L$0;
                        ResultKt.throwOnFailure($result);
                        defaultSettingsRepository2 = defaultSettingsRepository5;
                        i = 0;
                        this.L$0 = defaultSettingsRepository3;
                        this.L$1 = null;
                        this.label = 2;
                        $result = defaultSettingsRepository2.fetchMoa((HermesConfigApi) $result, this);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultSettingsRepository3.moaCached = (MoaSnapshot) $result;
                        $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl((MoaSnapshot) $result);
                        defaultSettingsRepository = this.this$0;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result2);
                        if (err == null) {
                            return $result2;
                        }
                        MoaSnapshot moaSnapshot2 = defaultSettingsRepository.moaCached;
                        if (moaSnapshot2 != null) {
                        }
                        break;
                    case 2:
                        DefaultSettingsRepository defaultSettingsRepository6 = (DefaultSettingsRepository) this.L$0;
                        ResultKt.throwOnFailure($result);
                        defaultSettingsRepository3 = defaultSettingsRepository6;
                        $result = $result;
                        defaultSettingsRepository3.moaCached = (MoaSnapshot) $result;
                        $result2 = ai.nous.aurora.compat.Compat.m7388constructorimpl((MoaSnapshot) $result);
                        defaultSettingsRepository = this.this$0;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result2);
                        if (err == null) {
                            return $result2;
                        }
                        MoaSnapshot moaSnapshot3 = defaultSettingsRepository.moaCached;
                        if (moaSnapshot3 != null) {
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th2) {
            }
        }
    }

    @Override // ai.nous.aurora.data.repository.SettingsRepository
    public Object loadMoa(boolean forceRefresh, Continuation continuation) {
        return BuildersKt.withContext(this.dispatcher, new C02212(forceRefresh, this, null), continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // ai.nous.aurora.data.repository.SettingsRepository
    /* JADX INFO: renamed from: saveMoa-gIAlu-s, reason: not valid java name */
    public Object mo59saveMoagIAlus(MoaConfigDto body, Continuation continuation) {
        DefaultSettingsRepository$saveMoa$1 defaultSettingsRepository$saveMoa$1;
        Object objWithContext;
        if (continuation instanceof DefaultSettingsRepository$saveMoa$1) {
            defaultSettingsRepository$saveMoa$1 = (DefaultSettingsRepository$saveMoa$1) continuation;
            if ((defaultSettingsRepository$saveMoa$1.label & Integer.MIN_VALUE) != 0) {
                defaultSettingsRepository$saveMoa$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSettingsRepository$saveMoa$1 = new DefaultSettingsRepository$saveMoa$1(this, continuation);
            }
        } else {
            defaultSettingsRepository$saveMoa$1 = new DefaultSettingsRepository$saveMoa$1(this, continuation);
        }
        Object $result = defaultSettingsRepository$saveMoa$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSettingsRepository$saveMoa$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                DefaultSettingsRepository$saveMoa$2 defaultSettingsRepository$saveMoa$2 = new DefaultSettingsRepository$saveMoa$2(this, body, null);
                defaultSettingsRepository$saveMoa$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, defaultSettingsRepository$saveMoa$2, defaultSettingsRepository$saveMoa$1);
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
    @Override // ai.nous.aurora.data.repository.SettingsRepository
    /* JADX INFO: renamed from: encodeMoaPrompt-0E7RQCE, reason: not valid java name */
    public Object mo57encodeMoaPrompt0E7RQCE(String prompt, String preset, Continuation continuation) {
        DefaultSettingsRepository$encodeMoaPrompt$1 defaultSettingsRepository$encodeMoaPrompt$1;
        Object objWithContext;
        if (continuation instanceof DefaultSettingsRepository$encodeMoaPrompt$1) {
            defaultSettingsRepository$encodeMoaPrompt$1 = (DefaultSettingsRepository$encodeMoaPrompt$1) continuation;
            if ((defaultSettingsRepository$encodeMoaPrompt$1.label & Integer.MIN_VALUE) != 0) {
                defaultSettingsRepository$encodeMoaPrompt$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSettingsRepository$encodeMoaPrompt$1 = new DefaultSettingsRepository$encodeMoaPrompt$1(this, continuation);
            }
        } else {
            defaultSettingsRepository$encodeMoaPrompt$1 = new DefaultSettingsRepository$encodeMoaPrompt$1(this, continuation);
        }
        Object $result = defaultSettingsRepository$encodeMoaPrompt$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSettingsRepository$encodeMoaPrompt$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher coroutineDispatcher = this.dispatcher;
                DefaultSettingsRepository$encodeMoaPrompt$2 defaultSettingsRepository$encodeMoaPrompt$2 = new DefaultSettingsRepository$encodeMoaPrompt$2(this, prompt, preset, null);
                defaultSettingsRepository$encodeMoaPrompt$1.label = 1;
                objWithContext = BuildersKt.withContext(coroutineDispatcher, defaultSettingsRepository$encodeMoaPrompt$2, defaultSettingsRepository$encodeMoaPrompt$1);
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

    @Override // ai.nous.aurora.data.repository.SettingsRepository
    public HermesSettingsSnapshot peekCached() {
        HermesSettingsSnapshot hermesSettingsSnapshot = this.cached;
        return hermesSettingsSnapshot == null ? readLastGood() : hermesSettingsSnapshot;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void persistLastGood(HermesSettingsSnapshot snap) {
        try {
            Result.Companion companion = Result.Companion;
            DefaultSettingsRepository $this$persistLastGood_u24lambda_u241 = this;
            SettingsLastGoodCodec settingsLastGoodCodec = SettingsLastGoodCodec.INSTANCE;
            String schemaSource = snap.getSchemaSource();
            if (schemaSource == null) {
                schemaSource = "live";
            }
            String payload = settingsLastGoodCodec.encode(schemaSource, snap.getValues());
            ai.nous.aurora.compat.Compat.d_FilesKt_writeText_5($this$persistLastGood_u24lambda_u241.lastGoodFile(), payload, null, 2, null);
            ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HermesSettingsSnapshot readLastGood() {
        Object objM7388constructorimpl;
        SettingsLastGoodPayload payload;
        try {
            Result.Companion companion = Result.Companion;
            DefaultSettingsRepository $this$readLastGood_u24lambda_u242 = this;
            if (!$this$readLastGood_u24lambda_u242.lastGoodFile().isFile() || (payload = SettingsLastGoodCodec.INSTANCE.decode(ai.nous.aurora.compat.Compat.d_FilesKt_readText_4($this$readLastGood_u24lambda_u242.lastGoodFile(), null, 1, null))) == null) {
                return null;
            }
            HermesConfigSchemaDto bundled = $this$readLastGood_u24lambda_u242.loadBundledSchema();
            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl($this$readLastGood_u24lambda_u242.toSnapshot(bundled, payload.getValues(), false, null, payload.getSchemaSource(), null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
        }
        return (HermesSettingsSnapshot) (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl) ? null : objM7388constructorimpl);
    }

    @Override // ai.nous.aurora.data.repository.SettingsRepository
    public List<SettingsSearchIndex.Hit> search(String query, HermesSettingsSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Map<String, JsonElement> values = snapshot.getValues();
        Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity(values.size()));
        Iterable $this$associateByTo$iv$iv$iv = values.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Object key = it$iv$iv.getKey();
            JsonElement el = (JsonElement) ((Map.Entry) element$iv$iv$iv).getValue();
            destination$iv$iv.put(key, StringsKt.trim(el.toString(), '\"'));
        }
        SettingsSearchIndex settingsSearchIndex = SettingsSearchIndex.INSTANCE;
        List<HermesSettingField> fields = snapshot.getFields();
        List<String> categoryOrder = snapshot.getCategoryOrder();
        MoaSnapshot moa = snapshot.getMoa();
        String defaultPreset = moa != null ? moa.getDefaultPreset() : null;
        MoaSnapshot moa2 = snapshot.getMoa();
        return settingsSearchIndex.search(query, fields, destination$iv$iv, categoryOrder, defaultPreset, moa2 != null ? Integer.valueOf(moa2.getDefaultReferenceCount()) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object fetchMoa(HermesConfigApi service, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        DefaultSettingsRepository defaultSettingsRepository;
        Object objMoa;
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
                defaultSettingsRepository = this;
                anonymousClass1.L$0 = defaultSettingsRepository;
                anonymousClass1.label = 1;
                objMoa = service.moa(anonymousClass1);
                if (objMoa == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                DefaultSettingsRepository defaultSettingsRepository2 = (DefaultSettingsRepository) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                defaultSettingsRepository = defaultSettingsRepository2;
                objMoa = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Response response = (Response) objMoa;
        if (!response.isSuccessful()) {
            throw new IllegalStateException(("MoA HTTP " + response.code()).toString());
        }
        MoaConfigDto body = (MoaConfigDto) response.body();
        if (body == null) {
            throw new IllegalStateException("MoA body empty".toString());
        }
        return defaultSettingsRepository.toSnapshot(body, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MoaSnapshot toSnapshot(MoaConfigDto $this$toSnapshot, boolean remoteAvailable) {
        return new MoaSnapshot($this$toSnapshot.getDefaultPreset(), $this$toSnapshot.getActivePreset(), $this$toSnapshot.getSaveTraces(), $this$toSnapshot.getTraceDir(), $this$toSnapshot.getPresets(), remoteAvailable, null, 64, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x005a  */
    /* JADX WARN: Code duplicated, block: B:22:0x0065  */
    /* JADX WARN: Code duplicated, block: B:24:0x0074 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x0075  */
    /* JADX WARN: Code duplicated, block: B:28:0x009d  */
    /* JADX WARN: Code duplicated, block: B:31:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:33:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:37:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:43:0x0185  */
    /* JADX WARN: Code duplicated, block: B:51:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:54:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object service(Continuation continuation) {
        C02221 c02221;
        DefaultSettingsRepository defaultSettingsRepository;
        Object apiKey;
        String key;
        Object gatewayAddress;
        final String key2;
        String identity;
        HermesConfigApi hermesConfigApi;
        Object objM7388constructorimpl;
        String strReplace;
        if (continuation instanceof C02221) {
            c02221 = (C02221) continuation;
            if ((c02221.label & Integer.MIN_VALUE) != 0) {
                c02221.label -= Integer.MIN_VALUE;
            } else {
                c02221 = new C02221(continuation);
            }
        } else {
            c02221 = new C02221(continuation);
        }
        Object $result = c02221.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02221.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                defaultSettingsRepository = this;
                AuroraCredentialStore auroraCredentialStore = defaultSettingsRepository.credentialStore;
                c02221.L$0 = defaultSettingsRepository;
                c02221.label = 1;
                apiKey = auroraCredentialStore.getApiKey(c02221);
                if (apiKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                key = (String) apiKey;
                if (key == null) {
                    key = "";
                }
                if (!StringsKt.isBlank(key)) {
                    throw new IllegalStateException("No Hermes API key is available".toString());
                }
                AuroraCredentialStore auroraCredentialStore2 = defaultSettingsRepository.credentialStore;
                c02221.L$0 = defaultSettingsRepository;
                c02221.L$1 = key;
                c02221.label = 2;
                gatewayAddress = auroraCredentialStore2.getGatewayAddress(c02221);
                if (gatewayAddress == coroutine_suspended) {
                    return coroutine_suspended;
                }
                key2 = key;
                String gateway = (String) gatewayAddress;
                String url = defaultSettingsRepository.configBridgeUrl(gateway);
                identity = url + ":" + key2.hashCode();
                hermesConfigApi = defaultSettingsRepository.api;
                if (hermesConfigApi != null) {
                    if (!Intrinsics.areEqual(defaultSettingsRepository.apiIdentity, identity)) {
                        hermesConfigApi = null;
                    }
                    if (hermesConfigApi != null) {
                        HermesConfigApi it = hermesConfigApi;
                        return it;
                    }
                }
                if (defaultSettingsRepository.apiIdentity != null && !Intrinsics.areEqual(defaultSettingsRepository.apiIdentity, identity)) {
                    defaultSettingsRepository.cached = null;
                    defaultSettingsRepository.moaCached = null;
                }
                Interceptor auth = new Interceptor() { // from class: ai.nous.aurora.data.repository.DefaultSettingsRepository$$ExternalSyntheticLambda1
                    @Override // okhttp3.Interceptor
                    public final okhttp3.Response intercept(Interceptor.Chain chain) {
                        return DefaultSettingsRepository.service$lambda$6(key2, chain);
                    }
                };
                OkHttpClient client = new OkHttpClient.Builder().connectTimeout(8L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).addInterceptor(auth).build();
                Object objCreate = new Retrofit.Builder().baseUrl(StringsKt.trimEnd(url, '/') + "/").client(client).addConverterFactory(KotlinSerializationConverterFactory.create(defaultSettingsRepository.json, MediaType.Companion.get("application/json"))).build().create(HermesConfigApi.class);
                HermesConfigApi it2 = (HermesConfigApi) objCreate;
                defaultSettingsRepository.api = it2;
                defaultSettingsRepository.apiIdentity = identity;
                try {
                    Result.Companion companion = Result.Companion;
                    String lowerCase = StringsKt.trim((CharSequence) gateway).toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    strReplace = new Regex("[^a-z0-9._-]").replace(StringsKt.trimEnd(StringsKt.removePrefix(StringsKt.removePrefix(lowerCase, (CharSequence) "https://"), (CharSequence) "http://"), '/'), "_");
                    if (StringsKt.isBlank(strReplace)) {
                        strReplace = "default";
                    }
                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(strReplace);
                    break;
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                }
                defaultSettingsRepository.lastGoodHostTag = (String) (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl) ? "default" : objM7388constructorimpl);
                Intrinsics.checkNotNullExpressionValue(objCreate, "also(...)");
                return objCreate;
            case 1:
                defaultSettingsRepository = (DefaultSettingsRepository) c02221.L$0;
                ResultKt.throwOnFailure($result);
                apiKey = $result;
                key = (String) apiKey;
                if (key == null) {
                    key = "";
                }
                if (!StringsKt.isBlank(key)) {
                    throw new IllegalStateException("No Hermes API key is available".toString());
                }
                AuroraCredentialStore auroraCredentialStore3 = defaultSettingsRepository.credentialStore;
                c02221.L$0 = defaultSettingsRepository;
                c02221.L$1 = key;
                c02221.label = 2;
                gatewayAddress = auroraCredentialStore3.getGatewayAddress(c02221);
                if (gatewayAddress == coroutine_suspended) {
                    return coroutine_suspended;
                }
                key2 = key;
                String gateway2 = (String) gatewayAddress;
                String url2 = defaultSettingsRepository.configBridgeUrl(gateway2);
                identity = url2 + ":" + key2.hashCode();
                hermesConfigApi = defaultSettingsRepository.api;
                if (hermesConfigApi != null) {
                    if (!Intrinsics.areEqual(defaultSettingsRepository.apiIdentity, identity)) {
                        hermesConfigApi = null;
                    }
                    if (hermesConfigApi != null) {
                        HermesConfigApi it3 = hermesConfigApi;
                        return it3;
                    }
                }
                if (defaultSettingsRepository.apiIdentity != null) {
                    defaultSettingsRepository.cached = null;
                    defaultSettingsRepository.moaCached = null;
                }
                Interceptor auth2 = new Interceptor() { // from class: ai.nous.aurora.data.repository.DefaultSettingsRepository$$ExternalSyntheticLambda1
                    @Override // okhttp3.Interceptor
                    public final okhttp3.Response intercept(Interceptor.Chain chain) {
                        return DefaultSettingsRepository.service$lambda$6(key2, chain);
                    }
                };
                OkHttpClient client2 = new OkHttpClient.Builder().connectTimeout(8L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).addInterceptor(auth2).build();
                Object objCreate2 = new Retrofit.Builder().baseUrl(StringsKt.trimEnd(url2, '/') + "/").client(client2).addConverterFactory(KotlinSerializationConverterFactory.create(defaultSettingsRepository.json, MediaType.Companion.get("application/json"))).build().create(HermesConfigApi.class);
                HermesConfigApi it4 = (HermesConfigApi) objCreate2;
                defaultSettingsRepository.api = it4;
                defaultSettingsRepository.apiIdentity = identity;
                Result.Companion companion3 = Result.Companion;
                String lowerCase2 = StringsKt.trim((CharSequence) gateway2).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                strReplace = new Regex("[^a-z0-9._-]").replace(StringsKt.trimEnd(StringsKt.removePrefix(StringsKt.removePrefix(lowerCase2, (CharSequence) "https://"), (CharSequence) "http://"), '/'), "_");
                if (StringsKt.isBlank(strReplace)) {
                    strReplace = "default";
                }
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(strReplace);
                defaultSettingsRepository.lastGoodHostTag = (String) (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl) ? "default" : objM7388constructorimpl);
                Intrinsics.checkNotNullExpressionValue(objCreate2, "also(...)");
                return objCreate2;
            case 2:
                key2 = (String) c02221.L$1;
                defaultSettingsRepository = (DefaultSettingsRepository) c02221.L$0;
                ResultKt.throwOnFailure($result);
                gatewayAddress = $result;
                String gateway3 = (String) gatewayAddress;
                String url3 = defaultSettingsRepository.configBridgeUrl(gateway3);
                identity = url3 + ":" + key2.hashCode();
                hermesConfigApi = defaultSettingsRepository.api;
                if (hermesConfigApi != null) {
                    if (!Intrinsics.areEqual(defaultSettingsRepository.apiIdentity, identity)) {
                        hermesConfigApi = null;
                    }
                    if (hermesConfigApi != null) {
                        HermesConfigApi it5 = hermesConfigApi;
                        return it5;
                    }
                }
                if (defaultSettingsRepository.apiIdentity != null) {
                    defaultSettingsRepository.cached = null;
                    defaultSettingsRepository.moaCached = null;
                }
                Interceptor auth3 = new Interceptor() { // from class: ai.nous.aurora.data.repository.DefaultSettingsRepository$$ExternalSyntheticLambda1
                    @Override // okhttp3.Interceptor
                    public final okhttp3.Response intercept(Interceptor.Chain chain) {
                        return DefaultSettingsRepository.service$lambda$6(key2, chain);
                    }
                };
                OkHttpClient client3 = new OkHttpClient.Builder().connectTimeout(8L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).addInterceptor(auth3).build();
                Object objCreate3 = new Retrofit.Builder().baseUrl(StringsKt.trimEnd(url3, '/') + "/").client(client3).addConverterFactory(KotlinSerializationConverterFactory.create(defaultSettingsRepository.json, MediaType.Companion.get("application/json"))).build().create(HermesConfigApi.class);
                HermesConfigApi it6 = (HermesConfigApi) objCreate3;
                defaultSettingsRepository.api = it6;
                defaultSettingsRepository.apiIdentity = identity;
                Result.Companion companion4 = Result.Companion;
                String lowerCase3 = StringsKt.trim((CharSequence) gateway3).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                strReplace = new Regex("[^a-z0-9._-]").replace(StringsKt.trimEnd(StringsKt.removePrefix(StringsKt.removePrefix(lowerCase3, (CharSequence) "https://"), (CharSequence) "http://"), '/'), "_");
                if (StringsKt.isBlank(strReplace)) {
                    strReplace = "default";
                }
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(strReplace);
                defaultSettingsRepository.lastGoodHostTag = (String) (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl) ? "default" : objM7388constructorimpl);
                Intrinsics.checkNotNullExpressionValue(objCreate3, "also(...)");
                return objCreate3;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final okhttp3.Response service$lambda$6(String $key, Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(chain.request().newBuilder().header("Authorization", "Bearer " + $key).header("Content-Type", "application/json").build());
    }

    private final String configBridgeUrl(String gateway) {
        Object objM7388constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            DefaultSettingsRepository defaultSettingsRepository = this;
            URI uri = new URI(gateway);
            String host = uri.getHost();
            if (!(host == null || StringsKt.isBlank(host))) {
                String scheme = uri.getScheme();
                if (scheme == null) {
                    scheme = "http";
                }
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(new URI(scheme, null, uri.getHost(), 8765, null, null, null).toString());
                if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                    objM7388constructorimpl = BuildConfig.HERMES_CONFIG_URL;
                }
                return (String) objM7388constructorimpl;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HermesConfigSchemaDto loadBundledSchema() throws IOException {
        InputStream inputStreamOpen = this.appContext.getAssets().open("hermes_schema.json");
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "open(...)");
        Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            BufferedReader it = bufferedReader;
            Json this_$iv = this.json;
            String string$iv = TextStreamsKt.readText(it);
            this_$iv.getSerializersModule();
            HermesConfigSchemaDto hermesConfigSchemaDto = (HermesConfigSchemaDto) this_$iv.decodeFromString(HermesConfigSchemaDto.INSTANCE.serializer(), string$iv);
            CloseableKt.closeFinally(bufferedReader, null);
            return hermesConfigSchemaDto;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(bufferedReader, th);
                throw th2;
            }
        }
    }

    static /* synthetic */ HermesSettingsSnapshot toSnapshot$default(DefaultSettingsRepository defaultSettingsRepository, HermesConfigSchemaDto hermesConfigSchemaDto, Map map, boolean z, String str, String str2, MoaSnapshot moaSnapshot, int i, Object obj) {
        String str3;
        MoaSnapshot moaSnapshot2;
        if ((i & 8) == 0) {
            str3 = str2;
        } else {
            str3 = null;
        }
        if ((i & 16) == 0) {
            moaSnapshot2 = moaSnapshot;
        } else {
            moaSnapshot2 = null;
        }
        return defaultSettingsRepository.toSnapshot(hermesConfigSchemaDto, map, z, str, str3, moaSnapshot2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HermesSettingsSnapshot toSnapshot(HermesConfigSchemaDto $this$toSnapshot, Map<String, ? extends JsonElement> map, boolean remoteAvailable, String error, String schemaSource, MoaSnapshot moa) {
        Map<String, HermesConfigFieldDto> fields = $this$toSnapshot.getFields();
        Collection destination$iv$iv = new ArrayList(fields.size());
        for (Map.Entry<String, HermesConfigFieldDto> entry : fields.entrySet()) {
            String path = entry.getKey();
            HermesConfigFieldDto field = entry.getValue();
            destination$iv$iv.add(toModel(field, path));
        }
        return new HermesSettingsSnapshot((List) destination$iv$iv, $this$toSnapshot.getCategoryOrder(), map, remoteAvailable, error, schemaSource, moa);
    }

    private final HermesSettingField toModel(HermesConfigFieldDto $this$toModel, String path) {
        String lowerCase = $this$toModel.getType().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String description = $this$toModel.getDescription();
        String category = $this$toModel.getCategory();
        if (StringsKt.isBlank(category)) {
            category = "general";
        }
        return new HermesSettingField(path, lowerCase, description, category, $this$toModel.getOptions(), $this$toModel.getSecret());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Map<String, JsonElement> flatten(JsonElement value, String prefix) {
        if (!(value instanceof JsonObject)) {
            return value instanceof JsonArray ? MapsKt.mapOf(TuplesKt.to(prefix, value)) : MapsKt.mapOf(TuplesKt.to(prefix, value));
        }
        Map $this$flatten_u24lambda_u2416 = MapsKt.createMapBuilder();
        Map $this$forEach$iv = (Map) value;
        for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
            String key = (String) element$iv.getKey();
            JsonElement child = (JsonElement) element$iv.getValue();
            String path = StringsKt.isBlank(prefix) ? key : prefix + "." + key;
            $this$flatten_u24lambda_u2416.putAll(flatten(child, path));
        }
        return MapsKt.build($this$flatten_u24lambda_u2416);
    }

    static /* synthetic */ Map flatten$default(DefaultSettingsRepository defaultSettingsRepository, JsonElement jsonElement, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return defaultSettingsRepository.flatten(jsonElement, str);
    }
}
