package ai.nous.aurora.data.storage;

import ai.nous.aurora.domain.model.PreconnectCredentialPolicy;
import ai.nous.aurora.domain.model.ThreadVoidDensityPolicy;
import ai.nous.aurora.presentation.theme.AuroraMotion;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.view.MotionEventCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: AuroraCredentialStore.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u0000 <2\u00020\u0001:\u0001<B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000f\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001e\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u001bJ\u0010\u0010 \u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u0018\u0010!\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0002\u0010\u0016J\u000e\u0010\"\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010\u0011J\u000e\u0010#\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010\u0011J\u0010\u0010$\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u0018\u0010%\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0002\u0010\u0016J\b\u0010-\u001a\u00020\u0014H\u0002J\u0018\u0010.\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0002\u0010\u0016J\u0018\u0010/\u001a\u0004\u0018\u00010\u00102\u0006\u00100\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010\u0016J \u00101\u001a\u00020\u00142\u0006\u00100\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0082@¢\u0006\u0002\u00102J \u00103\u001a\u00020\u00182\u0006\u00100\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0082@¢\u0006\u0002\u00102J\u000e\u00104\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u00105\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0016J\u000e\u00109\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u0010:\u001a\u00020\u00142\u0006\u0010;\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0016R\u0018\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020(0*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020\u00100'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u00100*¢\u0006\b\n\u0000\u001a\u0004\b8\u0010,¨\u0006="}, d2 = {"Lai/nous/aurora/data/storage/AuroraCredentialStore;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "prefs$delegate", "Lkotlin/Lazy;", "getGatewayAddress", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApiKey", "setApiKey", "", "value", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isAutoConnectOptOut", "", "setAutoConnectOptOut", "optOut", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAssistSpeakReplies", "setAssistSpeakReplies", "getAssistAutoListen", "setAssistAutoListen", "getDeviceId", "setDeviceId", "signOut", "restorePreconfig", "getAssistSessionId", "setAssistSessionId", "_ownerGenerationFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "ownerGenerationFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getOwnerGenerationFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "bumpOwnerGeneration", "setGatewayAddress", "get", "key", "set", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setIfChanged", "getAssistOrbStyle", "setAssistOrbStyle", "_appearanceSkinFlow", "appearanceSkinFlow", "getAppearanceSkinFlow", "getAppearanceSkin", "setAppearanceSkin", "wireValue", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AuroraCredentialStore {
    public static final String DEFAULT_GATEWAY_URL = "http://100.90.24.39:8642";
    private static final String KEY_API = "api_server_key";
    private static final String KEY_APPEARANCE_SKIN = "appearance_skin";
    private static final String KEY_ASSIST_AUTO_LISTEN = "assist_auto_listen";
    private static final String KEY_ASSIST_ORB_STYLE = "assist_orb_style";
    private static final String KEY_ASSIST_SESSION = "assist_session_id";
    private static final String KEY_ASSIST_SPEAK = "assist_speak_replies";
    private static final String KEY_DEVICE_ID = "device_id";
    private static final String KEY_GATEWAY = "gateway_address";
    private static final String KEY_OPT_OUT = "auto_connect_opt_out";
    private final MutableStateFlow<String> _appearanceSkinFlow;
    private final MutableStateFlow<Integer> _ownerGenerationFlow;
    private final Context appContext;
    private final StateFlow<String> appearanceSkinFlow;
    private final StateFlow<Integer> ownerGenerationFlow;

    /* JADX INFO: renamed from: prefs$delegate, reason: from kotlin metadata */
    private final Lazy prefs;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final List<String> ORB_STYLES = CollectionsKt.listOf(new String[]{"amber", "aurora", "cobalt", "signal"});

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$getApiKey$1, reason: invalid class name */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore", f = "AuroraCredentialStore.kt", i = {0}, l = {MotionEventCompat.AXIS_GENERIC_16, 48}, m = "getApiKey", n = {"this"}, s = {"L$0"})
    final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuroraCredentialStore.this.getApiKey(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$getGatewayAddress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore", f = "AuroraCredentialStore.kt", i = {}, l = {MotionEventCompat.AXIS_GENERIC_6}, m = "getGatewayAddress", n = {}, s = {})
    final class C02271 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02271(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuroraCredentialStore.this.getGatewayAddress(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$restorePreconfig$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore", f = "AuroraCredentialStore.kt", i = {0}, l = {95, ThreadVoidDensityPolicy.VOID_MARK_TOP_DP}, m = "restorePreconfig", n = {"this"}, s = {"L$0"})
    final class C02291 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02291(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuroraCredentialStore.this.restorePreconfig(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$setApiKey$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore", f = "AuroraCredentialStore.kt", i = {0}, l = {53}, m = "setApiKey", n = {"this"}, s = {"L$0"})
    final class C02311 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02311(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuroraCredentialStore.this.setApiKey(null, this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$setGatewayAddress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore", f = "AuroraCredentialStore.kt", i = {0}, l = {AuroraMotion.PRESS_MS}, m = "setGatewayAddress", n = {"this"}, s = {"L$0"})
    final class C02371 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02371(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuroraCredentialStore.this.setGatewayAddress(null, this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$signOut$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore", f = "AuroraCredentialStore.kt", i = {0, 1}, l = {88, 89, 90}, m = "signOut", n = {"this", "this"}, s = {"L$0", "L$0"})
    final class C02391 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02391(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuroraCredentialStore.this.signOut(this);
        }
    }

    public AuroraCredentialStore(Context context) {
        String string;
        String str = "constellation";
        Intrinsics.checkNotNullParameter(context, "context");
        this.appContext = context.getApplicationContext();
        this.prefs = LazyKt.lazy(new Function0() { // from class: ai.nous.aurora.data.storage.AuroraCredentialStore$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Object f$0 = null;
                return AuroraCredentialStore.prefs_delegate$lambda$0(AuroraCredentialStore.this);
            }
        });
        this._ownerGenerationFlow = StateFlowKt.MutableStateFlow(0);
        this.ownerGenerationFlow = FlowKt.asStateFlow(this._ownerGenerationFlow);
        this._appearanceSkinFlow = StateFlowKt.MutableStateFlow("ember");
        this.appearanceSkinFlow = FlowKt.asStateFlow(this._appearanceSkinFlow);
        try {
            String lowerCase = null;
            String v = getPrefs().getString(KEY_APPEARANCE_SKIN, null);
            MutableStateFlow<String> mutableStateFlow = this._appearanceSkinFlow;
            if (v != null && (string = StringsKt.trim((CharSequence) v).toString()) != null) {
                lowerCase = string.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            }
            if (!Intrinsics.areEqual(lowerCase, "constellation")) {
                str = "ember";
            }
            mutableStateFlow.setValue(str);
        } catch (Exception e) {
            this._appearanceSkinFlow.setValue("ember");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedPreferences getPrefs() {
        Object value = this.prefs.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (SharedPreferences) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SharedPreferences prefs_delegate$lambda$0(AuroraCredentialStore this$0) {
        try {
            MasterKey masterKey = new MasterKey.Builder(this$0.appContext).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
            Intrinsics.checkNotNullExpressionValue(masterKey, "build(...)");
            return EncryptedSharedPreferences.create(this$0.appContext, "aurora_secure_prefs", masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (Exception e) {
            return this$0.appContext.getSharedPreferences("aurora_fallback_prefs", 0);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getGatewayAddress(Continuation continuation) {
        C02271 c02271;
        Object obj;
        if (continuation instanceof C02271) {
            c02271 = (C02271) continuation;
            if ((c02271.label & Integer.MIN_VALUE) != 0) {
                c02271.label -= Integer.MIN_VALUE;
            } else {
                c02271 = new C02271(continuation);
            }
        } else {
            c02271 = new C02271(continuation);
        }
        Object $result = c02271.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02271.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                c02271.label = 1;
                obj = get(KEY_GATEWAY, c02271);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                obj = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String it = (String) obj;
        if (it != null) {
            return it;
        }
        String str = null;
        return StringsKt.isBlank(str) ? "http://100.90.24.39:8642" : str;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x006b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x006c  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getApiKey(Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        AuroraCredentialStore auroraCredentialStore;
        PreconnectCredentialPolicy preconnectCredentialPolicy;
        Object obj;
        String str;
        Object objIsAutoConnectOptOut;
        String str2;
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
                auroraCredentialStore = this;
                preconnectCredentialPolicy = PreconnectCredentialPolicy.INSTANCE;
                anonymousClass1.L$0 = auroraCredentialStore;
                anonymousClass1.L$1 = preconnectCredentialPolicy;
                anonymousClass1.label = 1;
                obj = auroraCredentialStore.get(KEY_API, anonymousClass1);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str = (String) obj;
                anonymousClass1.L$0 = preconnectCredentialPolicy;
                anonymousClass1.L$1 = str;
                anonymousClass1.label = 2;
                objIsAutoConnectOptOut = auroraCredentialStore.isAutoConnectOptOut(anonymousClass1);
                if (objIsAutoConnectOptOut == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str2 = str;
                return preconnectCredentialPolicy.effectiveKey(str2, ((Boolean) objIsAutoConnectOptOut).booleanValue());
            case 1:
                preconnectCredentialPolicy = (PreconnectCredentialPolicy) anonymousClass1.L$1;
                auroraCredentialStore = (AuroraCredentialStore) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                obj = $result;
                str = (String) obj;
                anonymousClass1.L$0 = preconnectCredentialPolicy;
                anonymousClass1.L$1 = str;
                anonymousClass1.label = 2;
                objIsAutoConnectOptOut = auroraCredentialStore.isAutoConnectOptOut(anonymousClass1);
                if (objIsAutoConnectOptOut == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str2 = str;
                return preconnectCredentialPolicy.effectiveKey(str2, ((Boolean) objIsAutoConnectOptOut).booleanValue());
            case 2:
                str2 = (String) anonymousClass1.L$1;
                preconnectCredentialPolicy = (PreconnectCredentialPolicy) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                objIsAutoConnectOptOut = $result;
                return preconnectCredentialPolicy.effectiveKey(str2, ((Boolean) objIsAutoConnectOptOut).booleanValue());
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object setApiKey(String value, Continuation continuation) {
        C02311 c02311;
        AuroraCredentialStore auroraCredentialStore;
        Object ifChanged;
        if (continuation instanceof C02311) {
            c02311 = (C02311) continuation;
            if ((c02311.label & Integer.MIN_VALUE) != 0) {
                c02311.label -= Integer.MIN_VALUE;
            } else {
                c02311 = new C02311(continuation);
            }
        } else {
            c02311 = new C02311(continuation);
        }
        Object $result = c02311.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02311.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                auroraCredentialStore = this;
                c02311.L$0 = auroraCredentialStore;
                c02311.label = 1;
                ifChanged = auroraCredentialStore.setIfChanged(KEY_API, value, c02311);
                if (ifChanged == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                AuroraCredentialStore auroraCredentialStore2 = (AuroraCredentialStore) c02311.L$0;
                ResultKt.throwOnFailure($result);
                auroraCredentialStore = auroraCredentialStore2;
                ifChanged = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) ifChanged).booleanValue()) {
            auroraCredentialStore.bumpOwnerGeneration();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$isAutoConnectOptOut$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$isAutoConnectOptOut$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02282 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        C02282(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02282(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02282)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxBoolean(AuroraCredentialStore.this.getPrefs().getBoolean(AuroraCredentialStore.KEY_OPT_OUT, false));
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object isAutoConnectOptOut(Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02282(null), continuation);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$setAutoConnectOptOut$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$setAutoConnectOptOut$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02362 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ boolean $optOut;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02362(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$optOut = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02362(this.$optOut, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02362)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    AuroraCredentialStore.this.getPrefs().edit().putBoolean(AuroraCredentialStore.KEY_OPT_OUT, this.$optOut).apply();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object setAutoConnectOptOut(boolean optOut, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C02362(optOut, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$getAssistSpeakReplies$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$getAssistSpeakReplies$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02262 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        C02262(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02262(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02262)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxBoolean(AuroraCredentialStore.this.getPrefs().getBoolean(AuroraCredentialStore.KEY_ASSIST_SPEAK, true));
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object getAssistSpeakReplies(Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02262(null), continuation);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$setAssistSpeakReplies$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$setAssistSpeakReplies$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02352 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ boolean $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02352(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$value = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02352(this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02352)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    AuroraCredentialStore.this.getPrefs().edit().putBoolean(AuroraCredentialStore.KEY_ASSIST_SPEAK, this.$value).apply();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object setAssistSpeakReplies(boolean value, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C02352(value, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$getAssistAutoListen$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$getAssistAutoListen$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02242 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        C02242(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02242(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02242)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxBoolean(AuroraCredentialStore.this.getPrefs().getBoolean(AuroraCredentialStore.KEY_ASSIST_AUTO_LISTEN, true));
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object getAssistAutoListen(Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02242(null), continuation);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$setAssistAutoListen$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$setAssistAutoListen$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02332 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ boolean $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02332(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$value = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02332(this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02332)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    AuroraCredentialStore.this.getPrefs().edit().putBoolean(AuroraCredentialStore.KEY_ASSIST_AUTO_LISTEN, this.$value).apply();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object setAssistAutoListen(boolean value, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C02332(value, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final Object getDeviceId(Continuation continuation) {
        return get(KEY_DEVICE_ID, continuation);
    }

    public final Object setDeviceId(String value, Continuation continuation) {
        Object obj = set(KEY_DEVICE_ID, value, continuation);
        return obj == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x005c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x0068 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object signOut(Continuation continuation) {
        C02391 c02391;
        AuroraCredentialStore auroraCredentialStore;
        if (continuation instanceof C02391) {
            c02391 = (C02391) continuation;
            if ((c02391.label & Integer.MIN_VALUE) != 0) {
                c02391.label -= Integer.MIN_VALUE;
            } else {
                c02391 = new C02391(continuation);
            }
        } else {
            c02391 = new C02391(continuation);
        }
        Object $result = c02391.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02391.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                auroraCredentialStore = this;
                c02391.L$0 = auroraCredentialStore;
                c02391.label = 1;
                if (auroraCredentialStore.setApiKey(null, c02391) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                c02391.L$0 = auroraCredentialStore;
                c02391.label = 2;
                if (auroraCredentialStore.setAutoConnectOptOut(true, c02391) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                c02391.L$0 = null;
                c02391.label = 3;
                if (auroraCredentialStore.setAssistSessionId(null, c02391) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                auroraCredentialStore = (AuroraCredentialStore) c02391.L$0;
                ResultKt.throwOnFailure($result);
                c02391.L$0 = auroraCredentialStore;
                c02391.label = 2;
                if (auroraCredentialStore.setAutoConnectOptOut(true, c02391) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                c02391.L$0 = null;
                c02391.label = 3;
                if (auroraCredentialStore.setAssistSessionId(null, c02391) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                auroraCredentialStore = (AuroraCredentialStore) c02391.L$0;
                ResultKt.throwOnFailure($result);
                c02391.L$0 = null;
                c02391.label = 3;
                if (auroraCredentialStore.setAssistSessionId(null, c02391) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 3:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0054  */
    /* JADX WARN: Code duplicated, block: B:20:0x0057  */
    /* JADX WARN: Code duplicated, block: B:23:0x0066 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object restorePreconfig(Continuation continuation) {
        C02291 c02291;
        AuroraCredentialStore auroraCredentialStore;
        String str;
        String str2;
        if (continuation instanceof C02291) {
            c02291 = (C02291) continuation;
            if ((c02291.label & Integer.MIN_VALUE) != 0) {
                c02291.label -= Integer.MIN_VALUE;
            } else {
                c02291 = new C02291(continuation);
            }
        } else {
            c02291 = new C02291(continuation);
        }
        Object $result = c02291.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02291.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                auroraCredentialStore = this;
                c02291.L$0 = auroraCredentialStore;
                c02291.label = 1;
                if (auroraCredentialStore.setAutoConnectOptOut(false, c02291) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str = "http://100.90.24.39:8642";
                str2 = getPrefs().getString(KEY_GATEWAY, null);
                if (!StringsKt.isBlank(str2)) {
                    str = str2;
                }
                c02291.L$0 = null;
                c02291.label = 2;
                if (auroraCredentialStore.setGatewayAddress(str, c02291) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                auroraCredentialStore = (AuroraCredentialStore) c02291.L$0;
                ResultKt.throwOnFailure($result);
                str = "http://100.90.24.39:8642";
                str2 = getPrefs().getString(KEY_GATEWAY, null);
                if (!StringsKt.isBlank(str2)) {
                    str = str2;
                }
                c02291.L$0 = null;
                c02291.label = 2;
                if (auroraCredentialStore.setGatewayAddress(str, c02291) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getAssistSessionId(Continuation continuation) {
        return get(KEY_ASSIST_SESSION, continuation);
    }

    public final Object setAssistSessionId(String value, Continuation continuation) {
        Object obj = set(KEY_ASSIST_SESSION, value, continuation);
        return obj == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj : Unit.INSTANCE;
    }

    public final StateFlow<Integer> getOwnerGenerationFlow() {
        return this.ownerGenerationFlow;
    }

    private final void bumpOwnerGeneration() {
        Integer value;
        int it;
        MutableStateFlow<Integer> mutableStateFlow = this._ownerGenerationFlow;
        do {
            value = mutableStateFlow.getValue();
            it = value.intValue();
        } while (!mutableStateFlow.compareAndSet(value, Integer.valueOf(it + 1)));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object setGatewayAddress(String value, Continuation continuation) {
        C02371 c02371;
        AuroraCredentialStore auroraCredentialStore;
        Object ifChanged;
        if (continuation instanceof C02371) {
            c02371 = (C02371) continuation;
            if ((c02371.label & Integer.MIN_VALUE) != 0) {
                c02371.label -= Integer.MIN_VALUE;
            } else {
                c02371 = new C02371(continuation);
            }
        } else {
            c02371 = new C02371(continuation);
        }
        Object $result = c02371.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02371.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                auroraCredentialStore = this;
                c02371.L$0 = auroraCredentialStore;
                c02371.label = 1;
                ifChanged = auroraCredentialStore.setIfChanged(KEY_GATEWAY, value, c02371);
                if (ifChanged == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                AuroraCredentialStore auroraCredentialStore2 = (AuroraCredentialStore) c02371.L$0;
                ResultKt.throwOnFailure($result);
                auroraCredentialStore = auroraCredentialStore2;
                ifChanged = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) ifChanged).booleanValue()) {
            auroraCredentialStore.bumpOwnerGeneration();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$get$2, reason: invalid class name */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\t\u0018\u00010\u0001¢\u0006\u0002\b\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$get$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $key;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation continuation) {
            super(2, continuation);
            this.$key = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new AnonymousClass2(this.$key, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return AuroraCredentialStore.this.getPrefs().getString(this.$key, null);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object get(String key, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(key, null), continuation);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$set$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$set$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02302 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $key;
        final /* synthetic */ String $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02302(String str, String str2, Continuation continuation) {
            super(2, continuation);
            this.$value = str;
            this.$key = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02302(this.$value, this.$key, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02302)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    SharedPreferences.Editor $this$invokeSuspend_u24lambda_u240 = AuroraCredentialStore.this.getPrefs().edit();
                    String str = this.$value;
                    String str2 = this.$key;
                    if (str == null) {
                        $this$invokeSuspend_u24lambda_u240.remove(str2);
                    } else {
                        $this$invokeSuspend_u24lambda_u240.putString(str2, str);
                    }
                    $this$invokeSuspend_u24lambda_u240.apply();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object set(String key, String value, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C02302(value, key, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$setIfChanged$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$setIfChanged$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02382 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $key;
        final /* synthetic */ String $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02382(String str, String str2, Continuation continuation) {
            super(2, continuation);
            this.$key = str;
            this.$value = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02382(this.$key, this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02382)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    if (Intrinsics.areEqual(AuroraCredentialStore.this.getPrefs().getString(this.$key, null), this.$value)) {
                        return Boxing.boxBoolean(false);
                    }
                    SharedPreferences.Editor $this$invokeSuspend_u24lambda_u240 = AuroraCredentialStore.this.getPrefs().edit();
                    String str = this.$value;
                    String str2 = this.$key;
                    if (str == null) {
                        $this$invokeSuspend_u24lambda_u240.remove(str2);
                    } else {
                        $this$invokeSuspend_u24lambda_u240.putString(str2, str);
                    }
                    $this$invokeSuspend_u24lambda_u240.apply();
                    return Boxing.boxBoolean(true);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object setIfChanged(String key, String value, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02382(key, value, null), continuation);
    }

    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lai/nous/aurora/data/storage/AuroraCredentialStore$Companion;", "", "<init>", "()V", "KEY_GATEWAY", "", "KEY_API", "KEY_DEVICE_ID", "KEY_OPT_OUT", "KEY_ASSIST_SPEAK", "KEY_ASSIST_AUTO_LISTEN", "KEY_ASSIST_ORB_STYLE", "KEY_ASSIST_SESSION", "KEY_APPEARANCE_SKIN", "DEFAULT_GATEWAY_URL", "ORB_STYLES", "", "getORB_STYLES", "()Ljava/util/List;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getORB_STYLES() {
            return AuroraCredentialStore.ORB_STYLES;
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$getAssistOrbStyle$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$getAssistOrbStyle$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02252 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        C02252(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02252(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02252)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    String it = AuroraCredentialStore.this.getPrefs().getString(AuroraCredentialStore.KEY_ASSIST_ORB_STYLE, "amber");
                    if (it == null) {
                        return "amber";
                    }
                    if (!AuroraCredentialStore.INSTANCE.getORB_STYLES().contains(it)) {
                        it = null;
                    }
                    return it == null ? "amber" : it;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object getAssistOrbStyle(Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02252(null), continuation);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$setAssistOrbStyle$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$setAssistOrbStyle$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02342 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $value;
        int label;
        final /* synthetic */ AuroraCredentialStore this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02342(String str, AuroraCredentialStore auroraCredentialStore, Continuation continuation) {
            super(2, continuation);
            this.$value = str;
            this.this$0 = auroraCredentialStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return new C02342(this.$value, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02342)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    String v = this.$value;
                    if (!AuroraCredentialStore.INSTANCE.getORB_STYLES().contains(v)) {
                        v = null;
                    }
                    if (v == null) {
                        v = "amber";
                    }
                    this.this$0.getPrefs().edit().putString(AuroraCredentialStore.KEY_ASSIST_ORB_STYLE, v).apply();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object setAssistOrbStyle(String value, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C02342(value, this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final StateFlow<String> getAppearanceSkinFlow() {
        return this.appearanceSkinFlow;
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$getAppearanceSkin$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$getAppearanceSkin$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    final class C02232 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        C02232(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AuroraCredentialStore.this.new C02232(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02232)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    String string = AuroraCredentialStore.this.getPrefs().getString(AuroraCredentialStore.KEY_APPEARANCE_SKIN, null);
                    if (string == null) {
                        string = "";
                    }
                    String str = string;
                    AuroraCredentialStore auroraCredentialStore = AuroraCredentialStore.this;
                    if (StringsKt.isBlank(str)) {
                        return (String) auroraCredentialStore._appearanceSkinFlow.getValue();
                    }
                    return str;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object getAppearanceSkin(Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02232(null), continuation);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.storage.AuroraCredentialStore$setAppearanceSkin$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuroraCredentialStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.storage.AuroraCredentialStore$setAppearanceSkin$2", f = "AuroraCredentialStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02322 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $wireValue;
        int label;
        final /* synthetic */ AuroraCredentialStore this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02322(String str, AuroraCredentialStore auroraCredentialStore, Continuation continuation) {
            super(2, continuation);
            this.$wireValue = str;
            this.this$0 = auroraCredentialStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return new C02322(this.$wireValue, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02322)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    String lowerCase = StringsKt.trim((CharSequence) this.$wireValue).toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    String str = "constellation";
                    if (!Intrinsics.areEqual(lowerCase, "constellation")) {
                        str = "ember";
                    }
                    String v = str;
                    this.this$0.getPrefs().edit().putString(AuroraCredentialStore.KEY_APPEARANCE_SKIN, v).apply();
                    this.this$0._appearanceSkinFlow.setValue(v);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object setAppearanceSkin(String wireValue, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C02322(wireValue, this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }
}
