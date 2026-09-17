package ai.nous.aurora.data.remote;

import com.cornerstone.agency.BuildConfig;
import ai.nous.aurora.data.storage.AuroraCredentialStore;
import androidx.core.location.LocationRequestCompat;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
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
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: HostVoiceClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\tH\u0082@¢\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\tH\u0082@¢\u0006\u0002\u0010\nJ\u000e\u0010\f\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\nJB\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\r2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015H\u0086@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lai/nous/aurora/data/remote/HostVoiceClient;", "", "credentialStore", "Lai/nous/aurora/data/storage/AuroraCredentialStore;", "<init>", "(Lai/nous/aurora/data/storage/AuroraCredentialStore;)V", "client", "Lokhttp3/OkHttpClient;", "bridgeBase", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apiKey", "statusOk", "", "dictate", "Lai/nous/aurora/data/remote/HostVoiceClient$DictateResult;", "file", "Ljava/io/File;", "speak", "sessionId", "onStage", "Lkotlin/Function1;", "Lai/nous/aurora/data/remote/HostVoiceClient$Stage;", "", "(Ljava/io/File;ZLjava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transcribe", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Stage", "DictateResult", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class HostVoiceClient {
    public static final int $stable = 8;
    private final OkHttpClient client;
    private final AuroraCredentialStore credentialStore;

    /* JADX INFO: renamed from: ai.nous.aurora.data.remote.HostVoiceClient$apiKey$1, reason: invalid class name */
    /* JADX INFO: compiled from: HostVoiceClient.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.remote.HostVoiceClient", f = "HostVoiceClient.kt", i = {}, l = {78}, m = "apiKey", n = {}, s = {})
    final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HostVoiceClient.this.apiKey(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.remote.HostVoiceClient$bridgeBase$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HostVoiceClient.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.remote.HostVoiceClient", f = "HostVoiceClient.kt", i = {0}, l = {68}, m = "bridgeBase", n = {"this"}, s = {"L$0"})
    final class C02111 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02111(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HostVoiceClient.this.bridgeBase(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.remote.HostVoiceClient$transcribe$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HostVoiceClient.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.remote.HostVoiceClient", f = "HostVoiceClient.kt", i = {}, l = {140}, m = "transcribe", n = {}, s = {})
    final class C02121 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02121(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HostVoiceClient.this.transcribe(null, this);
        }
    }

    public HostVoiceClient(AuroraCredentialStore credentialStore) {
        Intrinsics.checkNotNullParameter(credentialStore, "credentialStore");
        this.credentialStore = credentialStore;
        this.client = new OkHttpClient.Builder().connectTimeout(5L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).callTimeout(185L, TimeUnit.SECONDS).build();
    }

    /* JADX INFO: compiled from: HostVoiceClient.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lai/nous/aurora/data/remote/HostVoiceClient$Stage;", "", "<init>", "(Ljava/lang/String;I)V", "UPLOADING", "PROCESSING", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Stage {
        UPLOADING,
        PROCESSING;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Stage> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: HostVoiceClient.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003JA\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lai/nous/aurora/data/remote/HostVoiceClient$DictateResult;", "", "transcript", "", "replyText", "sessionId", "audioUrl", "deviceActionsJson", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTranscript", "()Ljava/lang/String;", "getReplyText", "getSessionId", "getAudioUrl", "getDeviceActionsJson", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class DictateResult {
        public static final int $stable = 0;
        private final String audioUrl;
        private final String deviceActionsJson;
        private final String replyText;
        private final String sessionId;
        private final String transcript;

        public static /* synthetic */ DictateResult copy$default(DictateResult dictateResult, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = dictateResult.transcript;
            }
            if ((i & 2) != 0) {
                str2 = dictateResult.replyText;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = dictateResult.sessionId;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = dictateResult.audioUrl;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = dictateResult.deviceActionsJson;
            }
            return dictateResult.copy(str, str6, str7, str8, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTranscript() {
            return this.transcript;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getReplyText() {
            return this.replyText;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSessionId() {
            return this.sessionId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getAudioUrl() {
            return this.audioUrl;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getDeviceActionsJson() {
            return this.deviceActionsJson;
        }

        public final DictateResult copy(String transcript, String replyText, String sessionId, String audioUrl, String deviceActionsJson) {
            Intrinsics.checkNotNullParameter(transcript, "transcript");
            Intrinsics.checkNotNullParameter(replyText, "replyText");
            return new DictateResult(transcript, replyText, sessionId, audioUrl, deviceActionsJson);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DictateResult)) {
                return false;
            }
            DictateResult dictateResult = (DictateResult) other;
            return Intrinsics.areEqual(this.transcript, dictateResult.transcript) && Intrinsics.areEqual(this.replyText, dictateResult.replyText) && Intrinsics.areEqual(this.sessionId, dictateResult.sessionId) && Intrinsics.areEqual(this.audioUrl, dictateResult.audioUrl) && Intrinsics.areEqual(this.deviceActionsJson, dictateResult.deviceActionsJson);
        }

        public int hashCode() {
            return (((((((this.transcript.hashCode() * 31) + this.replyText.hashCode()) * 31) + (this.sessionId == null ? 0 : this.sessionId.hashCode())) * 31) + (this.audioUrl == null ? 0 : this.audioUrl.hashCode())) * 31) + (this.deviceActionsJson != null ? this.deviceActionsJson.hashCode() : 0);
        }

        public String toString() {
            return "DictateResult(transcript=" + this.transcript + ", replyText=" + this.replyText + ", sessionId=" + this.sessionId + ", audioUrl=" + this.audioUrl + ", deviceActionsJson=" + this.deviceActionsJson + ")";
        }

        public DictateResult(String transcript, String replyText, String sessionId, String audioUrl, String deviceActionsJson) {
            Intrinsics.checkNotNullParameter(transcript, "transcript");
            Intrinsics.checkNotNullParameter(replyText, "replyText");
            this.transcript = transcript;
            this.replyText = replyText;
            this.sessionId = sessionId;
            this.audioUrl = audioUrl;
            this.deviceActionsJson = deviceActionsJson;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ DictateResult(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, (i & 16) == 0 ? str5 : null);
        }




    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object bridgeBase(Continuation continuation) {
        C02111 c02111;
        Object gatewayAddress;
        HostVoiceClient hostVoiceClient;
        Object objM7388constructorimpl;
        if (continuation instanceof C02111) {
            c02111 = (C02111) continuation;
            if ((c02111.label & Integer.MIN_VALUE) != 0) {
                c02111.label -= Integer.MIN_VALUE;
            } else {
                c02111 = new C02111(continuation);
            }
        } else {
            c02111 = new C02111(continuation);
        }
        C02111 c02112 = c02111;
        Object $result = c02112.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02112.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                AuroraCredentialStore auroraCredentialStore = this.credentialStore;
                c02112.L$0 = this;
                c02112.label = 1;
                gatewayAddress = auroraCredentialStore.getGatewayAddress(c02112);
                if (gatewayAddress == coroutine_suspended) {
                    return coroutine_suspended;
                }
                hostVoiceClient = this;
                break;
            case 1:
                hostVoiceClient = (HostVoiceClient) c02112.L$0;
                ResultKt.throwOnFailure($result);
                gatewayAddress = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String gateway = (String) gatewayAddress;
        try {
            Result.Companion companion = Result.Companion;
            URI uri = new URI(gateway);
            String host = uri.getHost();
            if (host == null || StringsKt.isBlank(host)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String scheme = uri.getScheme();
            if (scheme == null) {
                scheme = "http";
            }
            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(new URI(scheme, null, uri.getHost(), 8765, null, null, null).toString());
            if (ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                objM7388constructorimpl = BuildConfig.HERMES_CONFIG_URL;
            }
            Intrinsics.checkNotNullExpressionValue(objM7388constructorimpl, "getOrElse(...)");
            return StringsKt.trimEnd((String) objM7388constructorimpl, '/');
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object apiKey(Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Object apiKey;
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
                AuroraCredentialStore auroraCredentialStore = this.credentialStore;
                anonymousClass1.label = 1;
                apiKey = auroraCredentialStore.getApiKey(anonymousClass1);
                if (apiKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                apiKey = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String str = (String) apiKey;
        return str == null ? "" : str;
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.remote.HostVoiceClient$statusOk$2, reason: invalid class name */
    /* JADX INFO: compiled from: HostVoiceClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.remote.HostVoiceClient$statusOk$2", f = "HostVoiceClient.kt", i = {1}, l = {82, 85}, m = "invokeSuspend", n = {"key"}, s = {"L$1"})
    final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = HostVoiceClient.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:19:0x0059  */
        /* JADX WARN: Code duplicated, block: B:20:0x005c A[Catch: all -> 0x00d8, TryCatch #2 {all -> 0x00d8, blocks: (B:24:0x0074, B:27:0x00c4, B:34:0x00d4, B:35:0x00d7, B:17:0x004e, B:20:0x005c, B:25:0x00bb, B:32:0x00d2), top: B:48:0x004e, inners: #0, #1 }] */
        /* JADX WARN: Code duplicated, block: B:22:0x0070 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:23:0x0071  */
        /* JADX WARN: Code duplicated, block: B:42:0x00f0  */
        /* JADX WARN: Code duplicated, block: B:51:? A[RETURN, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objM7388constructorimpl;
            Boolean boolBoxBoolean;
            HostVoiceClient hostVoiceClient;
            int i;
            String key;
            Request.Builder builder;
            Object objBridgeBase;
            String key2;
            boolean zIsSuccessful;
            Response responseExecute;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        HostVoiceClient hostVoiceClient2 = HostVoiceClient.this;
                        Result.Companion companion = Result.Companion;
                        this.L$0 = hostVoiceClient2;
                        this.label = 1;
                        Object objApiKey = hostVoiceClient2.apiKey(this);
                        if (objApiKey == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objApiKey;
                        hostVoiceClient = hostVoiceClient2;
                        i = 0;
                        try {
                            key = (String) $result;
                            if (StringsKt.isBlank(key)) {
                                zIsSuccessful = false;
                            } else {
                                builder = new Request.Builder();
                                this.L$0 = hostVoiceClient;
                                this.L$1 = key;
                                this.L$2 = builder;
                                this.label = 2;
                                objBridgeBase = hostVoiceClient.bridgeBase(this);
                                if (objBridgeBase == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                key2 = key;
                                $result = objBridgeBase;
                                Request req = builder.url($result + "/v1/voice/status").header("Authorization", "Bearer " + key2).get().build();
                                responseExecute = hostVoiceClient.client.newCall(req).execute();
                                try {
                                    Response it = responseExecute;
                                    zIsSuccessful = it.isSuccessful();
                                    CloseableKt.closeFinally(responseExecute, null);
                                } catch (Throwable th) {
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        CloseableKt.closeFinally(responseExecute, th);
                                        throw th2;
                                    }
                                }
                            }
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxBoolean(zIsSuccessful));
                            break;
                        } catch (Throwable th3) {
                            Result.Companion companion2 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th3));
                        }
                        boolBoxBoolean = Boxing.boxBoolean(false);
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            return boolBoxBoolean;
                        }
                        return objM7388constructorimpl;
                    case 1:
                        HostVoiceClient hostVoiceClient3 = (HostVoiceClient) this.L$0;
                        ResultKt.throwOnFailure($result);
                        hostVoiceClient = hostVoiceClient3;
                        i = 0;
                        key = (String) $result;
                        if (StringsKt.isBlank(key)) {
                            zIsSuccessful = false;
                        } else {
                            builder = new Request.Builder();
                            this.L$0 = hostVoiceClient;
                            this.L$1 = key;
                            this.L$2 = builder;
                            this.label = 2;
                            objBridgeBase = hostVoiceClient.bridgeBase(this);
                            if (objBridgeBase == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            key2 = key;
                            $result = objBridgeBase;
                            Request req2 = builder.url($result + "/v1/voice/status").header("Authorization", "Bearer " + key2).get().build();
                            responseExecute = hostVoiceClient.client.newCall(req2).execute();
                            Response it2 = responseExecute;
                            zIsSuccessful = it2.isSuccessful();
                            CloseableKt.closeFinally(responseExecute, null);
                        }
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxBoolean(zIsSuccessful));
                        boolBoxBoolean = Boxing.boxBoolean(false);
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            return boolBoxBoolean;
                        }
                        return objM7388constructorimpl;
                    case 2:
                        Request.Builder builder2 = (Request.Builder) this.L$2;
                        key2 = (String) this.L$1;
                        hostVoiceClient = (HostVoiceClient) this.L$0;
                        ResultKt.throwOnFailure($result);
                        builder = builder2;
                        $result = $result;
                        Request req3 = builder.url($result + "/v1/voice/status").header("Authorization", "Bearer " + key2).get().build();
                        responseExecute = hostVoiceClient.client.newCall(req3).execute();
                        Response it3 = responseExecute;
                        zIsSuccessful = it3.isSuccessful();
                        CloseableKt.closeFinally(responseExecute, null);
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxBoolean(zIsSuccessful));
                        boolBoxBoolean = Boxing.boxBoolean(false);
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            return boolBoxBoolean;
                        }
                        return objM7388constructorimpl;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th4) {
            }
        }
    }

    public final Object statusOk(Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(null), continuation);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.remote.HostVoiceClient$dictate$3, reason: invalid class name */
    /* JADX INFO: compiled from: HostVoiceClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lai/nous/aurora/data/remote/HostVoiceClient$DictateResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.remote.HostVoiceClient$dictate$3", f = "HostVoiceClient.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ File $file;
        final /* synthetic */ Function1<? super Stage, Unit> $onStage;
        final /* synthetic */ String $sessionId;
        final /* synthetic */ boolean $speak;
        int label;
        final /* synthetic */ HostVoiceClient this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(File file, boolean z, String str, Function1<? super Stage, Unit> function1, HostVoiceClient hostVoiceClient, Continuation continuation) {
            super(2, continuation);
            this.$file = file;
            this.$speak = z;
            this.$sessionId = str;
            this.$onStage = function1;
            this.this$0 = hostVoiceClient;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.$file, this.$speak, this.$sessionId, this.$onStage, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass3)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: ai.nous.aurora.data.remote.HostVoiceClient$dictate$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: HostVoiceClient.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "ai.nous.aurora.data.remote.HostVoiceClient$dictate$3$1", f = "HostVoiceClient.kt", i = {0}, l = {LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY, LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY}, m = "invokeSuspend", n = {"baseDeferred"}, s = {"L$0"})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ HostVoiceClient this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(HostVoiceClient hostVoiceClient, Continuation continuation) {
                super(2, continuation);
                this.this$0 = hostVoiceClient;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return invoke2(coroutineScope, (Continuation) continuation);
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, Continuation continuation) {
                return ((AnonymousClass1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:13:0x006a A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:14:0x006b  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Deferred baseDeferred;
                Object objAwait;
                Object objAwait2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                        Deferred keyDeferred = ai.nous.aurora.compat.Compat.d_BuildersKt_async_6($this$coroutineScope, null, null, new HostVoiceClient$dictate$3$1$keyDeferred$1(this.this$0, null), 3, null);
                        baseDeferred = ai.nous.aurora.compat.Compat.d_BuildersKt_async_6($this$coroutineScope, null, null, new HostVoiceClient$dictate$3$1$baseDeferred$1(this.this$0, null), 3, null);
                        this.L$0 = baseDeferred;
                        this.label = 1;
                        objAwait = keyDeferred.await(this);
                        if (objAwait == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        this.L$0 = objAwait;
                        this.label = 2;
                        objAwait2 = baseDeferred.await(this);
                        if (objAwait2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objAwait2;
                        return TuplesKt.to(objAwait, $result);
                    case 1:
                        baseDeferred = (Deferred) this.L$0;
                        ResultKt.throwOnFailure($result);
                        objAwait = $result;
                        this.L$0 = objAwait;
                        this.label = 2;
                        objAwait2 = baseDeferred.await(this);
                        if (objAwait2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objAwait2;
                        return TuplesKt.to(objAwait, $result);
                    case 2:
                        Object obj = this.L$0;
                        ResultKt.throwOnFailure($result);
                        objAwait = obj;
                        return TuplesKt.to(objAwait, $result);
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:45:0x0162  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object $result;
            String audioUrl;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            boolean z = true;
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.this$0, null), this);
                    if (objCoroutineScope == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objCoroutineScope;
                    break;
                case 1:
                    $result = obj;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Pair pair = (Pair) $result;
            String key = (String) pair.component1();
            String base = (String) pair.component2();
            if (StringsKt.isBlank(key)) {
                throw new IllegalStateException("No API key — connect Aurora to Hermes first".toString());
            }
            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", this.$file.getName(), RequestBody.Companion.create(this.$file, MediaType.Companion.get("audio/*"))).addFormDataPart("speak", String.valueOf(this.$speak));
            String str = this.$sessionId;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
            }
            if (!z) {
                bodyBuilder.addFormDataPart("session_id", this.$sessionId);
            }
            this.$onStage.invoke(Stage.UPLOADING);
            StageReportingRequestBody body = new StageReportingRequestBody(bodyBuilder.build(), this.$onStage);
            Request req = new Request.Builder().url(base + "/v1/voice/dictate").header("Authorization", "Bearer " + key).post(body).build();
            Response responseExecute = this.this$0.client.newCall(req).execute();
            try {
                Response resp = responseExecute;
                ResponseBody responseBodyBody = resp.body();
                String text = responseBodyBody != null ? responseBodyBody.string() : null;
                if (text == null) {
                    text = "";
                }
                if (!resp.isSuccessful()) {
                    throw new IllegalStateException(("Voice HTTP " + resp.code() + ": " + StringsKt.take(text, 200)).toString());
                }
                JSONObject json = new JSONObject(text);
                if (!json.optBoolean("ok", false)) {
                    String strOptString = json.optString("error", "dictate failed");
                    Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                    throw new IllegalStateException(strOptString.toString());
                }
                String strOptString2 = json.optString("audio_url");
                if (StringsKt.isBlank(strOptString2)) {
                    strOptString2 = null;
                }
                String audioPath = strOptString2;
                if (audioPath == null) {
                    audioUrl = null;
                } else {
                    if (!ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(audioPath, "/", false, 2, (Object) null)) {
                        audioPath = null;
                    }
                    if (audioPath != null) {
                        String it = audioPath;
                        audioUrl = base + it;
                    } else {
                        audioUrl = null;
                    }
                }
                String strOptString3 = json.optString("transcript");
                Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                String strOptString4 = json.optString("reply_text");
                Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(...)");
                String strOptString5 = json.optString("session_id");
                if (StringsKt.isBlank(strOptString5)) {
                    strOptString5 = null;
                }
                String str2 = strOptString5;
                JSONArray jSONArrayOptJSONArray = json.optJSONArray("device_actions");
                DictateResult dictateResult = new DictateResult(strOptString3, strOptString4, str2, audioUrl, jSONArrayOptJSONArray != null ? jSONArrayOptJSONArray.toString() : null);
                CloseableKt.closeFinally(responseExecute, null);
                return dictateResult;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(responseExecute, th);
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit dictate$lambda$2(Stage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public final Object dictate(File file, boolean speak, String sessionId, Function1<? super Stage, Unit> function1, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass3(file, speak, sessionId, function1, this, null), continuation);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.remote.HostVoiceClient$transcribe$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HostVoiceClient.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.remote.HostVoiceClient$transcribe$2", f = "HostVoiceClient.kt", i = {1, 1}, l = {141, 152}, m = "invokeSuspend", n = {"key", "body"}, s = {"L$0", "L$1"})
    final class C02132 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ File $file;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02132(File file, Continuation continuation) {
            super(2, continuation);
            this.$file = file;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return HostVoiceClient.this.new C02132(this.$file, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02132)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x004b  */
        /* JADX WARN: Code duplicated, block: B:16:0x0091 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:17:0x0092  */
        /* JADX WARN: Code duplicated, block: B:21:0x00eb A[Catch: all -> 0x0152, TryCatch #0 {all -> 0x0152, blocks: (B:19:0x00e1, B:21:0x00eb, B:25:0x00f5, B:27:0x00fb, B:29:0x010e, B:33:0x0115, B:34:0x0120, B:35:0x0121, B:36:0x0151), top: B:44:0x00e1 }] */
        /* JADX WARN: Code duplicated, block: B:22:0x00f0  */
        /* JADX WARN: Code duplicated, block: B:24:0x00f3  */
        /* JADX WARN: Code duplicated, block: B:27:0x00fb A[Catch: all -> 0x0152, TryCatch #0 {all -> 0x0152, blocks: (B:19:0x00e1, B:21:0x00eb, B:25:0x00f5, B:27:0x00fb, B:29:0x010e, B:33:0x0115, B:34:0x0120, B:35:0x0121, B:36:0x0151), top: B:44:0x00e1 }] */
        /* JADX WARN: Code duplicated, block: B:29:0x010e A[Catch: all -> 0x0152, TRY_LEAVE, TryCatch #0 {all -> 0x0152, blocks: (B:19:0x00e1, B:21:0x00eb, B:25:0x00f5, B:27:0x00fb, B:29:0x010e, B:33:0x0115, B:34:0x0120, B:35:0x0121, B:36:0x0151), top: B:44:0x00e1 }] */
        /* JADX WARN: Code duplicated, block: B:32:0x0114  */
        /* JADX WARN: Code duplicated, block: B:35:0x0121 A[Catch: all -> 0x0152, TryCatch #0 {all -> 0x0152, blocks: (B:19:0x00e1, B:21:0x00eb, B:25:0x00f5, B:27:0x00fb, B:29:0x010e, B:33:0x0115, B:34:0x0120, B:35:0x0121, B:36:0x0151), top: B:44:0x00e1 }] */
        /* JADX WARN: Code duplicated, block: B:42:0x0159  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            String key;
            MultipartBody body;
            Request.Builder builder;
            Object objBridgeBase;
            Request.Builder builder2;
            String key2;
            Response responseExecute;
            Response resp;
            ResponseBody responseBodyBody;
            String text;
            String strOptString;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objApiKey = HostVoiceClient.this.apiKey(this);
                    if (objApiKey == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objApiKey;
                    key = (String) $result;
                    if (!StringsKt.isBlank(key)) {
                        throw new IllegalStateException("No API key".toString());
                    }
                    body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", this.$file.getName(), RequestBody.Companion.create(this.$file, MediaType.Companion.get("audio/*"))).build();
                    builder = new Request.Builder();
                    this.L$0 = key;
                    this.L$1 = body;
                    this.L$2 = builder;
                    this.label = 2;
                    objBridgeBase = HostVoiceClient.this.bridgeBase(this);
                    if (objBridgeBase == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    builder2 = builder;
                    key2 = key;
                    $result = objBridgeBase;
                    Request req = builder2.url($result + "/v1/voice/transcribe").header("Authorization", "Bearer " + key2).post(body).build();
                    responseExecute = HostVoiceClient.this.client.newCall(req).execute();
                    try {
                        resp = responseExecute;
                        responseBodyBody = resp.body();
                        if (responseBodyBody != null) {
                            text = responseBodyBody.string();
                        } else {
                            text = null;
                        }
                        if (text == null) {
                            text = "";
                        }
                        if (!resp.isSuccessful()) {
                            throw new IllegalStateException(("STT HTTP " + resp.code() + ": " + StringsKt.take(text, 180)).toString());
                        }
                        JSONObject json = new JSONObject(text);
                        strOptString = json.optString("text");
                        if (StringsKt.isBlank(strOptString)) {
                            throw new IllegalStateException("empty transcript".toString());
                        }
                        String str = strOptString;
                        CloseableKt.closeFinally(responseExecute, null);
                        return str;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(responseExecute, th);
                            throw th2;
                        }
                    }
                case 1:
                    ResultKt.throwOnFailure($result);
                    key = (String) $result;
                    if (!StringsKt.isBlank(key)) {
                        throw new IllegalStateException("No API key".toString());
                    }
                    body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", this.$file.getName(), RequestBody.Companion.create(this.$file, MediaType.Companion.get("audio/*"))).build();
                    builder = new Request.Builder();
                    this.L$0 = key;
                    this.L$1 = body;
                    this.L$2 = builder;
                    this.label = 2;
                    objBridgeBase = HostVoiceClient.this.bridgeBase(this);
                    if (objBridgeBase == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    builder2 = builder;
                    key2 = key;
                    $result = objBridgeBase;
                    Request req2 = builder2.url($result + "/v1/voice/transcribe").header("Authorization", "Bearer " + key2).post(body).build();
                    responseExecute = HostVoiceClient.this.client.newCall(req2).execute();
                    resp = responseExecute;
                    responseBodyBody = resp.body();
                    if (responseBodyBody != null) {
                        text = responseBodyBody.string();
                    } else {
                        text = null;
                    }
                    if (text == null) {
                        text = "";
                    }
                    if (!resp.isSuccessful()) {
                        throw new IllegalStateException(("STT HTTP " + resp.code() + ": " + StringsKt.take(text, 180)).toString());
                    }
                    JSONObject json2 = new JSONObject(text);
                    strOptString = json2.optString("text");
                    if (StringsKt.isBlank(strOptString)) {
                        throw new IllegalStateException("empty transcript".toString());
                    }
                    String str2 = strOptString;
                    CloseableKt.closeFinally(responseExecute, null);
                    return str2;
                case 2:
                    builder2 = (Request.Builder) this.L$2;
                    MultipartBody body2 = (MultipartBody) this.L$1;
                    String key3 = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    key2 = key3;
                    body = body2;
                    Request req3 = builder2.url($result + "/v1/voice/transcribe").header("Authorization", "Bearer " + key2).post(body).build();
                    responseExecute = HostVoiceClient.this.client.newCall(req3).execute();
                    resp = responseExecute;
                    responseBodyBody = resp.body();
                    if (responseBodyBody != null) {
                        text = responseBodyBody.string();
                    } else {
                        text = null;
                    }
                    if (text == null) {
                        text = "";
                    }
                    if (!resp.isSuccessful()) {
                        throw new IllegalStateException(("STT HTTP " + resp.code() + ": " + StringsKt.take(text, 180)).toString());
                    }
                    JSONObject json3 = new JSONObject(text);
                    strOptString = json3.optString("text");
                    if (StringsKt.isBlank(strOptString)) {
                        throw new IllegalStateException("empty transcript".toString());
                    }
                    String str3 = strOptString;
                    CloseableKt.closeFinally(responseExecute, null);
                    return str3;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object transcribe(File file, Continuation continuation) {
        C02121 c02121;
        Object objWithContext;
        if (continuation instanceof C02121) {
            c02121 = (C02121) continuation;
            if ((c02121.label & Integer.MIN_VALUE) != 0) {
                c02121.label -= Integer.MIN_VALUE;
            } else {
                c02121 = new C02121(continuation);
            }
        } else {
            c02121 = new C02121(continuation);
        }
        Object $result = c02121.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02121.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineDispatcher io = Dispatchers.getIO();
                C02132 c02132 = new C02132(file, null);
                c02121.label = 1;
                objWithContext = BuildersKt.withContext(io, c02132, c02121);
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
        Intrinsics.checkNotNullExpressionValue(objWithContext, "withContext(...)");
        return objWithContext;
    }
}
