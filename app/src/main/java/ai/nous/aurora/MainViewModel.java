package ai.nous.aurora;

import ai.nous.aurora.data.remote.ConfigCategory;
import ai.nous.aurora.data.remote.ConfigFieldMeta;
import ai.nous.aurora.data.remote.GatewayClient;
import ai.nous.aurora.data.remote.MoaConfigDto;
import ai.nous.aurora.data.repository.HermesSettingField;
import ai.nous.aurora.data.repository.HermesSettingsSnapshot;
import ai.nous.aurora.data.repository.MoaSnapshot;
import ai.nous.aurora.data.repository.ModelOptionsSnapshot;
import ai.nous.aurora.data.repository.SessionRepository;
import ai.nous.aurora.data.repository.SettingsRepository;
import ai.nous.aurora.data.storage.AuroraCredentialStore;
import ai.nous.aurora.domain.model.AuroraDisplayMode;
import ai.nous.aurora.domain.model.AuroraMessage;
import ai.nous.aurora.domain.model.AuroraSession;
import ai.nous.aurora.domain.model.AuroraTab;
import ai.nous.aurora.domain.model.CatalogModelChoice;
import ai.nous.aurora.domain.model.ConnectionState;
import ai.nous.aurora.domain.model.CoverOpenPolicy;
import ai.nous.aurora.domain.model.CoverShellPolicy;
import ai.nous.aurora.domain.model.MoaCommandBoundary;
import ai.nous.aurora.domain.model.MoaDirtyNavigation;
import ai.nous.aurora.domain.model.ModelCatalogPolicy;
import ai.nous.aurora.domain.model.ModelProviderCatalog;
import ai.nous.aurora.domain.model.ModelSelection;
import ai.nous.aurora.domain.model.OutboxExactlyOnce;
import ai.nous.aurora.domain.model.SessionPollPolicy;
import ai.nous.aurora.presentation.settings.AssistSettingsUi;
import ai.nous.aurora.presentation.settings.SettingsPane;
import ai.nous.aurora.presentation.settings.SettingsUiModel;
import ai.nous.aurora.presentation.theme.AuroraMotion;
import ai.nous.aurora.service.AuroraNotificationManager;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.window.embedding.SplitRule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.GroupingKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: MainViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001:\f\u0088\u0001\u0089\u0001\u008a\u0001\u008b\u0001\u008c\u0001\u008d\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ&\u0010,\u001a\b\u0012\u0004\u0012\u00020%0-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\b\u00101\u001a\u0004\u0018\u00010%H\u0002J\u000e\u00102\u001a\u000203H\u0082@¢\u0006\u0002\u00104J\u000e\u00105\u001a\u0002032\u0006\u00106\u001a\u00020%J\u000e\u00107\u001a\u0002032\u0006\u00106\u001a\u00020%J\u000e\u00108\u001a\u0002032\u0006\u00106\u001a\u00020%J\u000e\u00109\u001a\u0002032\u0006\u0010:\u001a\u00020#J\u000e\u0010;\u001a\u0002032\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u0002032\u0006\u0010?\u001a\u00020#J\u000e\u0010@\u001a\u0002032\u0006\u0010A\u001a\u00020%J\u0006\u0010B\u001a\u000203J\u000e\u0010C\u001a\u0002032\u0006\u0010D\u001a\u00020%J\u000e\u0010E\u001a\u0002032\u0006\u0010F\u001a\u00020%J\u0006\u0010G\u001a\u000203J\u000e\u0010J\u001a\u0002032\u0006\u0010K\u001a\u00020LJ\u0006\u0010M\u001a\u000203J\u0006\u0010N\u001a\u000203J\u0006\u0010O\u001a\u000203J\u000e\u0010P\u001a\u0002032\u0006\u0010Q\u001a\u00020%J\u000e\u0010R\u001a\u0002032\u0006\u0010S\u001a\u00020#J\u0006\u0010T\u001a\u000203J\b\u0010U\u001a\u000203H\u0002J\u0006\u0010V\u001a\u000203J\u0006\u0010W\u001a\u000203J\u0006\u0010X\u001a\u000203J\u0006\u0010Y\u001a\u000203J\u000e\u0010Z\u001a\u0002032\u0006\u0010[\u001a\u00020%J\u0018\u0010\\\u001a\u0002032\u0006\u0010Q\u001a\u00020%2\u0006\u0010[\u001a\u00020%H\u0002J\u0006\u0010]\u001a\u000203J\u0006\u0010^\u001a\u000203J\u000e\u0010_\u001a\u0002032\u0006\u0010`\u001a\u00020%J\u000e\u0010a\u001a\u0002032\u0006\u0010b\u001a\u00020cJ\u000e\u0010d\u001a\u0002032\u0006\u0010e\u001a\u00020#J\u000e\u0010f\u001a\u0002032\u0006\u0010F\u001a\u00020%J\u000e\u0010g\u001a\u0002032\u0006\u0010h\u001a\u00020%J\u0006\u0010i\u001a\u000203J\u000e\u0010j\u001a\u0002032\u0006\u0010k\u001a\u00020%J\u0006\u0010l\u001a\u000203J\u0006\u0010m\u001a\u000203J\u0006\u0010n\u001a\u000203J\u000e\u0010o\u001a\u0002032\u0006\u00106\u001a\u00020#J\u000e\u0010p\u001a\u0002032\u0006\u00106\u001a\u00020#J\u000e\u0010q\u001a\u0002032\u0006\u00106\u001a\u00020%J\u000e\u0010r\u001a\u00020sH\u0082@¢\u0006\u0002\u00104J\u000e\u0010t\u001a\u0002032\u0006\u0010u\u001a\u00020vJ\u0006\u0010w\u001a\u000203J\u001a\u0010x\u001a\u0002032\b\b\u0002\u0010y\u001a\u00020#2\b\b\u0002\u0010z\u001a\u00020#J\u0018\u0010{\u001a\u0002032\u0006\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020#H\u0002J\u0017\u0010\u007f\u001a\u0002032\u0007\u0010\u0080\u0001\u001a\u00020%2\u0006\u00106\u001a\u00020#J#\u0010\u0081\u0001\u001a\u0002032\u0007\u0010\u0080\u0001\u001a\u00020%2\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010%2\u0006\u00106\u001a\u00020%J\u001e\u0010\u0083\u0001\u001a\u00030\u0084\u00012\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010%2\u0007\u0010\u0085\u0001\u001a\u00020%H\u0002J\u0012\u0010\u0086\u0001\u001a\u00020%2\u0007\u00106\u001a\u00030\u0084\u0001H\u0002J\u0011\u0010\u0087\u0001\u001a\u00020%2\u0006\u0010k\u001a\u00020%H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)0+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010I\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u008e\u0001"}, d2 = {"Lai/nous/aurora/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "credentialStore", "Lai/nous/aurora/data/storage/AuroraCredentialStore;", "gatewayClient", "Lai/nous/aurora/data/remote/GatewayClient;", "sessionRepository", "Lai/nous/aurora/data/repository/SessionRepository;", "settingsRepository", "Lai/nous/aurora/data/repository/SettingsRepository;", "notifications", "Lai/nous/aurora/service/AuroraNotificationManager;", "<init>", "(Lai/nous/aurora/data/storage/AuroraCredentialStore;Lai/nous/aurora/data/remote/GatewayClient;Lai/nous/aurora/data/repository/SessionRepository;Lai/nous/aurora/data/repository/SettingsRepository;Lai/nous/aurora/service/AuroraNotificationManager;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lai/nous/aurora/MainViewModel$UiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "_toasts", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lai/nous/aurora/MainViewModel$ToastEvent;", "toasts", "Lkotlinx/coroutines/flow/SharedFlow;", "getToasts", "()Lkotlinx/coroutines/flow/SharedFlow;", "messagesJob", "Lkotlinx/coroutines/Job;", "sendJob", "settingsLoadJob", "pollJob", "reconnectJob", "appInForeground", "", "lastNotifiedAssistantId", "", "lastSessionFingerprint", "lastThreadFingerprint", "pollIntervalMs", "", "sessionReadMarkers", "", "computeUnread", "", "sessions", "", "Lai/nous/aurora/domain/model/AuroraSession;", "selectedId", "bootstrap", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onGatewayAddressChange", "value", "onApiKeyChange", "onDraftChange", "setModelPickerOpen", "open", "selectModelChoice", "choice", "Lai/nous/aurora/MainViewModel$ModelChoice;", "setListening", "listening", "appendDictation", "text", "loadModels", "setSourceFilter", "source", "setSearchQuery", "q", "restorePreconfig", "wantInnerAfterUnfold", "pendingSessionAfterUnfold", "onDisplayMode", "mode", "Lai/nous/aurora/domain/model/AuroraDisplayMode;", "requestInnerFromCover", "openLatestSession", "connectToHermes", "openSession", "sessionId", "setAppInForeground", "foreground", "retryConnection", "startSessionPolling", "backToSessionList", "createSession", "send", "cancelSend", "retryFailedMessage", "messageId", "doRetry", "refreshSessions", "disconnect", "flashStatus", NotificationCompat.CATEGORY_MESSAGE, "onTabSelected", "tab", "Lai/nous/aurora/domain/model/AuroraTab;", "setMoaDirty", "dirty", "setSettingsSearch", "openEssentials", "section", "openAdvancedIndex", "openAdvancedCategory", "id", "openAccount", "openMoaEditor", "openAssistantSettings", "setAssistSpeakReplies", "setAssistAutoListen", "setAssistOrbStyle", "loadAssistSettings", "Lai/nous/aurora/presentation/settings/AssistSettingsUi;", "saveMoa", "body", "Lai/nous/aurora/data/remote/MoaConfigDto;", "backSettings", "refreshSettings", "forceNetwork", "paintCacheFirst", "applySettingsSnapshot", "snapshot", "Lai/nous/aurora/data/repository/HermesSettingsSnapshot;", "loading", "setConfigBoolean", "path", "setConfigString", "type", "configJsonValue", "Lkotlinx/serialization/json/JsonElement;", "raw", "displayJsonValue", "settingsCategoryLabel", "ConnectUiState", "UiMode", "ChatPane", "ModelChoice", "UiState", "ToastEvent", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MainViewModel extends ViewModel {
    public static final int $stable = 8;
    public final MutableSharedFlow<ToastEvent> _toasts;
    public final MutableStateFlow<UiState> _uiState;
    public volatile boolean appInForeground;
    public final AuroraCredentialStore credentialStore;
    public final GatewayClient gatewayClient;
    public String lastNotifiedAssistantId;
    public String lastSessionFingerprint;
    public String lastThreadFingerprint;
    public Job messagesJob;
    public final AuroraNotificationManager notifications;
    public volatile String pendingSessionAfterUnfold;
    public long pollIntervalMs;
    public Job pollJob;
    public Job reconnectJob;
    public Job sendJob;
    public final Map<String, Long> sessionReadMarkers;
    public final SessionRepository sessionRepository;
    public Job settingsLoadJob;
    public final SettingsRepository settingsRepository;
    public final SharedFlow<ToastEvent> toasts;
    public final StateFlow<UiState> uiState;
    public volatile boolean wantInnerAfterUnfold;

    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[AuroraDisplayMode.values().length];
            try {
                iArr[AuroraDisplayMode.RAZR_COVER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[SettingsPane.values().length];
            try {
                iArr2[SettingsPane.AdvancedCategory.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr2[SettingsPane.AdvancedIndex.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr2[SettingsPane.Essentials.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[SettingsPane.MoaEditor.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[SettingsPane.Account.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[SettingsPane.Assistant.ordinal()] = 6;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[SettingsPane.Home.ordinal()] = 7;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$bootstrap$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel", f = "MainViewModel.kt", i = {0, 1, 2, 3, 3, 4, 4, 4, 5, 5, 5, 6, 7, 8, 9}, l = {186, 187, 189, 191, 193, 222, 224, 228, 229, AuroraMotion.SHEET_MS}, m = "bootstrap", n = {"this", "this", "this", "this", "gateway", "this", "gateway", "apiKey", "this", "gateway", "apiKey", "this", "this", "this", "this"}, s = {"L$0", "L$0", "L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$0", "L$0", "L$0"})
    final class C01811 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C01811(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainViewModel.this.bootstrap(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$loadAssistSettings$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel", f = "MainViewModel.kt", i = {0, 1}, l = {1191, 1192, 1193}, m = "loadAssistSettings", n = {"this", "this"}, s = {"L$0", "L$0"})
    final class C01881 extends ContinuationImpl {
        Object L$0;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        C01881(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainViewModel.this.loadAssistSettings(this);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MainViewModel(AuroraCredentialStore auroraCredentialStore, GatewayClient gatewayClient, SessionRepository sessionRepository, SettingsRepository settingsRepository, AuroraNotificationManager auroraNotificationManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(auroraCredentialStore, gatewayClient, sessionRepository, settingsRepository, (i & 16) == 0 ? auroraNotificationManager : null);
    }

    public MainViewModel(AuroraCredentialStore credentialStore, GatewayClient gatewayClient, SessionRepository sessionRepository, SettingsRepository settingsRepository, AuroraNotificationManager notifications) {
        Intrinsics.checkNotNullParameter(credentialStore, "credentialStore");
        Intrinsics.checkNotNullParameter(gatewayClient, "gatewayClient");
        Intrinsics.checkNotNullParameter(sessionRepository, "sessionRepository");
        Intrinsics.checkNotNullParameter(settingsRepository, "settingsRepository");
        this.credentialStore = credentialStore;
        this.gatewayClient = gatewayClient;
        this.sessionRepository = sessionRepository;
        this.settingsRepository = settingsRepository;
        this.notifications = notifications;
        this._uiState = StateFlowKt.MutableStateFlow(new UiState(null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 67108863, null));
        this.uiState = FlowKt.asStateFlow(this._uiState);
        this._toasts = ai.nous.aurora.compat.Compat.d_SharedFlowKt_MutableSharedFlow_5(0, 4, null, 5, null);
        this.toasts = FlowKt.asSharedFlow(this._toasts);
        this.appInForeground = true;
        this.lastSessionFingerprint = "";
        this.lastThreadFingerprint = "";
        this.pollIntervalMs = SessionPollPolicy.MIN_INTERVAL_MS;
        this.sessionReadMarkers = new LinkedHashMap();
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass2(null), 3, null);
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass3(null), 3, null);
    }

    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001a"}, d2 = {"Lai/nous/aurora/MainViewModel$ConnectUiState;", "", "gatewayAddress", "", "apiKey", "busy", "", "error", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getGatewayAddress", "()Ljava/lang/String;", "getApiKey", "getBusy", "()Z", "getError", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class ConnectUiState {
        public static final int $stable = 0;
        public final String apiKey;
        public final boolean busy;
        public final String error;
        public final String gatewayAddress;

        public ConnectUiState() {
            this(null, null, false, null, 15, null);
        }

        public static /* synthetic */ ConnectUiState copy$default(ConnectUiState connectUiState, String str, String str2, boolean z, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = connectUiState.gatewayAddress;
            }
            if ((i & 2) != 0) {
                str2 = connectUiState.apiKey;
            }
            if ((i & 4) != 0) {
                z = connectUiState.busy;
            }
            if ((i & 8) != 0) {
                str3 = connectUiState.error;
            }
            return connectUiState.copy(str, str2, z, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getGatewayAddress() {
            return this.gatewayAddress;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getApiKey() {
            return this.apiKey;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getBusy() {
            return this.busy;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getError() {
            return this.error;
        }

        public final ConnectUiState copy(String gatewayAddress, String apiKey, boolean busy, String error) {
            Intrinsics.checkNotNullParameter(gatewayAddress, "gatewayAddress");
            Intrinsics.checkNotNullParameter(apiKey, "apiKey");
            return new ConnectUiState(gatewayAddress, apiKey, busy, error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ConnectUiState)) {
                return false;
            }
            ConnectUiState connectUiState = (ConnectUiState) other;
            return Intrinsics.areEqual(this.gatewayAddress, connectUiState.gatewayAddress) && Intrinsics.areEqual(this.apiKey, connectUiState.apiKey) && this.busy == connectUiState.busy && Intrinsics.areEqual(this.error, connectUiState.error);
        }

        public int hashCode() {
            return (((((this.gatewayAddress.hashCode() * 31) + this.apiKey.hashCode()) * 31) + Boolean.hashCode(this.busy)) * 31) + (this.error == null ? 0 : this.error.hashCode());
        }

        public String toString() {
            return "ConnectUiState(gatewayAddress=" + this.gatewayAddress + ", apiKey=" + this.apiKey + ", busy=" + this.busy + ", error=" + this.error + ")";
        }

        public ConnectUiState(String gatewayAddress, String apiKey, boolean busy, String error) {
            Intrinsics.checkNotNullParameter(gatewayAddress, "gatewayAddress");
            Intrinsics.checkNotNullParameter(apiKey, "apiKey");
            this.gatewayAddress = gatewayAddress;
            this.apiKey = apiKey;
            this.busy = busy;
            this.error = error;
        }

        public /* synthetic */ ConnectUiState(String str, String str2, boolean z, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "http://100.90.24.39:8642" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : str3);
        }




    }

    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lai/nous/aurora/MainViewModel$UiMode;", "", "<init>", "(Ljava/lang/String;I)V", "Connect", "Main", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum UiMode {
        Connect,
        Main;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<UiMode> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lai/nous/aurora/MainViewModel$ChatPane;", "", "<init>", "(Ljava/lang/String;I)V", "List", "Thread", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum ChatPane {
        List,
        Thread;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ChatPane> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\fR\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\f¨\u0006!"}, d2 = {"Lai/nous/aurora/MainViewModel$ModelChoice;", "", "model", "", "provider", "role", "providerName", "persistable", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getModel", "()Ljava/lang/String;", "getProvider", "getRole", "getProviderName", "getPersistable", "()Z", "label", "getLabel", "key", "getKey", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class ModelChoice {
        public static final int $stable = 0;
        public final String model;
        public final boolean persistable;
        public final String provider;
        public final String providerName;
        public final String role;

        public static /* synthetic */ ModelChoice copy$default(ModelChoice modelChoice, String str, String str2, String str3, String str4, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = modelChoice.model;
            }
            if ((i & 2) != 0) {
                str2 = modelChoice.provider;
            }
            String str5 = str2;
            if ((i & 4) != 0) {
                str3 = modelChoice.role;
            }
            String str6 = str3;
            if ((i & 8) != 0) {
                str4 = modelChoice.providerName;
            }
            String str7 = str4;
            if ((i & 16) != 0) {
                z = modelChoice.persistable;
            }
            return modelChoice.copy(str, str5, str6, str7, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getModel() {
            return this.model;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getProvider() {
            return this.provider;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getRole() {
            return this.role;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getProviderName() {
            return this.providerName;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getPersistable() {
            return this.persistable;
        }

        public final ModelChoice copy(String model, String provider, String role, String providerName, boolean persistable) {
            Intrinsics.checkNotNullParameter(model, "model");
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(providerName, "providerName");
            return new ModelChoice(model, provider, role, providerName, persistable);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ModelChoice)) {
                return false;
            }
            ModelChoice modelChoice = (ModelChoice) other;
            return Intrinsics.areEqual(this.model, modelChoice.model) && Intrinsics.areEqual(this.provider, modelChoice.provider) && Intrinsics.areEqual(this.role, modelChoice.role) && Intrinsics.areEqual(this.providerName, modelChoice.providerName) && this.persistable == modelChoice.persistable;
        }

        public int hashCode() {
            return (((((((this.model.hashCode() * 31) + this.provider.hashCode()) * 31) + this.role.hashCode()) * 31) + this.providerName.hashCode()) * 31) + Boolean.hashCode(this.persistable);
        }

        public String toString() {
            return "ModelChoice(model=" + this.model + ", provider=" + this.provider + ", role=" + this.role + ", providerName=" + this.providerName + ", persistable=" + this.persistable + ")";
        }

        public ModelChoice(String model, String provider, String role, String providerName, boolean persistable) {
            Intrinsics.checkNotNullParameter(model, "model");
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(providerName, "providerName");
            this.model = model;
            this.provider = provider;
            this.role = role;
            this.providerName = providerName;
            this.persistable = persistable;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ ModelChoice(String str, String str2, String str3, String str4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i & 8) == 0 ? str4 : str2, (i & 16) == 0 ? z : true);
        }



        public final String getLabel() {
            if (StringsKt.isBlank(this.provider)) {
                return this.model;
            }
            return this.provider + " · " + this.model;
        }

        public final String getKey() {
            return this.provider + "::" + this.model;
        }
    }

    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\bE\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B³\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0010\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\r\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0010\u0012\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\r\u0012\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\r\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0013\u0012\b\b\u0002\u0010 \u001a\u00020\u0013\u0012\b\b\u0002\u0010!\u001a\u00020\u0013\u0012\b\b\u0002\u0010\"\u001a\u00020\u0013\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010$\u001a\u00020%\u0012\b\b\u0002\u0010&\u001a\u00020'\u0012\u000e\b\u0002\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100)¢\u0006\u0004\b*\u0010+J\t\u0010Q\u001a\u00020\u0003HÆ\u0003J\t\u0010R\u001a\u00020\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0007HÆ\u0003J\t\u0010T\u001a\u00020\tHÆ\u0003J\t\u0010U\u001a\u00020\u000bHÆ\u0003J\u000f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\t\u0010W\u001a\u00020\u0010HÆ\u0003J\t\u0010X\u001a\u00020\u0010HÆ\u0003J\t\u0010Y\u001a\u00020\u0013HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\t\u0010[\u001a\u00020\u0010HÆ\u0003J\u000f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00170\rHÆ\u0003J\t\u0010]\u001a\u00020\u0010HÆ\u0003J\u000f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u001a0\rHÆ\u0003J\u000f\u0010_\u001a\b\u0012\u0004\u0012\u00020\u00100\rHÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\t\u0010c\u001a\u00020\u0013HÆ\u0003J\t\u0010d\u001a\u00020\u0013HÆ\u0003J\t\u0010e\u001a\u00020\u0013HÆ\u0003J\t\u0010f\u001a\u00020\u0013HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\t\u0010h\u001a\u00020%HÆ\u0003J\t\u0010i\u001a\u00020'HÆ\u0003J\u000f\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00100)HÆ\u0003Jµ\u0002\u0010k\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u00102\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00102\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\r2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u001f\u001a\u00020\u00132\b\b\u0002\u0010 \u001a\u00020\u00132\b\b\u0002\u0010!\u001a\u00020\u00132\b\b\u0002\u0010\"\u001a\u00020\u00132\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020'2\u000e\b\u0002\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100)HÆ\u0001J\u0013\u0010l\u001a\u00020\u00132\b\u0010m\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010n\u001a\u00020oHÖ\u0001J\t\u0010p\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u0011\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b:\u00109R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b=\u00109R\u0011\u0010\u0015\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b>\u00109R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\r¢\u0006\b\n\u0000\u001a\u0004\b?\u00107R\u0011\u0010\u0018\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b@\u00109R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\r¢\u0006\b\n\u0000\u001a\u0004\bA\u00107R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\r¢\u0006\b\n\u0000\u001a\u0004\bB\u00107R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\bC\u00109R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\bD\u00109R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\bE\u00109R\u0011\u0010\u001f\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bF\u0010<R\u0011\u0010 \u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bG\u0010<R\u0011\u0010!\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bH\u0010<R\u0011\u0010\"\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bI\u0010<R\u0013\u0010#\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\bJ\u00109R\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u0011\u0010&\u001a\u00020'¢\u0006\b\n\u0000\u001a\u0004\bM\u0010NR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100)¢\u0006\b\n\u0000\u001a\u0004\bO\u0010P¨\u0006q"}, d2 = {"Lai/nous/aurora/MainViewModel$UiState;", "", "mode", "Lai/nous/aurora/MainViewModel$UiMode;", "activeTab", "Lai/nous/aurora/domain/model/AuroraTab;", "chatPane", "Lai/nous/aurora/MainViewModel$ChatPane;", "displayMode", "Lai/nous/aurora/domain/model/AuroraDisplayMode;", "connection", "Lai/nous/aurora/domain/model/ConnectionState;", "sessions", "", "Lai/nous/aurora/domain/model/AuroraSession;", "sourceFilter", "", "searchQuery", "connectingSplash", "", "selectedSessionId", "selectedTitle", "messages", "Lai/nous/aurora/domain/model/AuroraMessage;", "draft", "modelChoices", "Lai/nous/aurora/MainViewModel$ModelChoice;", "availableModels", "selectedModel", "selectedProvider", "modelCatalogWarning", "modelPickerOpen", "listening", "busy", OutboxExactlyOnce.STATUS_SENDING, "statusLine", "connectState", "Lai/nous/aurora/MainViewModel$ConnectUiState;", "settings", "Lai/nous/aurora/presentation/settings/SettingsUiModel;", "unreadSessionIds", "", "<init>", "(Lai/nous/aurora/MainViewModel$UiMode;Lai/nous/aurora/domain/model/AuroraTab;Lai/nous/aurora/MainViewModel$ChatPane;Lai/nous/aurora/domain/model/AuroraDisplayMode;Lai/nous/aurora/domain/model/ConnectionState;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZLjava/lang/String;Lai/nous/aurora/MainViewModel$ConnectUiState;Lai/nous/aurora/presentation/settings/SettingsUiModel;Ljava/util/Set;)V", "getMode", "()Lai/nous/aurora/MainViewModel$UiMode;", "getActiveTab", "()Lai/nous/aurora/domain/model/AuroraTab;", "getChatPane", "()Lai/nous/aurora/MainViewModel$ChatPane;", "getDisplayMode", "()Lai/nous/aurora/domain/model/AuroraDisplayMode;", "getConnection", "()Lai/nous/aurora/domain/model/ConnectionState;", "getSessions", "()Ljava/util/List;", "getSourceFilter", "()Ljava/lang/String;", "getSearchQuery", "getConnectingSplash", "()Z", "getSelectedSessionId", "getSelectedTitle", "getMessages", "getDraft", "getModelChoices", "getAvailableModels", "getSelectedModel", "getSelectedProvider", "getModelCatalogWarning", "getModelPickerOpen", "getListening", "getBusy", "getSending", "getStatusLine", "getConnectState", "()Lai/nous/aurora/MainViewModel$ConnectUiState;", "getSettings", "()Lai/nous/aurora/presentation/settings/SettingsUiModel;", "getUnreadSessionIds", "()Ljava/util/Set;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class UiState {
        public static final int $stable = 8;
        public final AuroraTab activeTab;
        public final List<String> availableModels;
        public final boolean busy;
        public final ChatPane chatPane;
        public final ConnectUiState connectState;
        public final boolean connectingSplash;
        public final ConnectionState connection;
        public final AuroraDisplayMode displayMode;
        public final String draft;
        public final boolean listening;
        public final List<AuroraMessage> messages;
        public final UiMode mode;
        public final String modelCatalogWarning;
        public final List<ModelChoice> modelChoices;
        public final boolean modelPickerOpen;
        public final String searchQuery;
        public final String selectedModel;
        public final String selectedProvider;
        public final String selectedSessionId;
        public final String selectedTitle;
        public final boolean sending;
        public final List<AuroraSession> sessions;
        public final SettingsUiModel settings;
        public final String sourceFilter;
        public final String statusLine;
        public final Set<String> unreadSessionIds;

        public UiState() {
            this(null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 67108863, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ UiState copy$default(UiState uiState, UiMode uiMode, AuroraTab auroraTab, ChatPane chatPane, AuroraDisplayMode auroraDisplayMode, ConnectionState connectionState, List list, String str, String str2, boolean z, String str3, String str4, List list2, String str5, List list3, List list4, String str6, String str7, String str8, boolean z2, boolean z3, boolean z4, boolean z5, String str9, ConnectUiState connectUiState, SettingsUiModel settingsUiModel, Set set, int i, Object obj) {
            return uiState.copy((i & 1) != 0 ? uiState.mode : uiMode, (i & 2) != 0 ? uiState.activeTab : auroraTab, (i & 4) != 0 ? uiState.chatPane : chatPane, (i & 8) != 0 ? uiState.displayMode : auroraDisplayMode, (i & 16) != 0 ? uiState.connection : connectionState, (i & 32) != 0 ? uiState.sessions : list, (i & 64) != 0 ? uiState.sourceFilter : str, (i & 128) != 0 ? uiState.searchQuery : str2, (i & 256) != 0 ? uiState.connectingSplash : z, (i & 512) != 0 ? uiState.selectedSessionId : str3, (i & 1024) != 0 ? uiState.selectedTitle : str4, (i & 2048) != 0 ? uiState.messages : list2, (i & 4096) != 0 ? uiState.draft : str5, (i & 8192) != 0 ? uiState.modelChoices : list3, (i & 16384) != 0 ? uiState.availableModels : list4, (i & 32768) != 0 ? uiState.selectedModel : str6, (i & 65536) != 0 ? uiState.selectedProvider : str7, (i & 131072) != 0 ? uiState.modelCatalogWarning : str8, (i & 262144) != 0 ? uiState.modelPickerOpen : z2, (i & 524288) != 0 ? uiState.listening : z3, (i & 1048576) != 0 ? uiState.busy : z4, (i & 2097152) != 0 ? uiState.sending : z5, (i & 4194304) != 0 ? uiState.statusLine : str9, (i & 8388608) != 0 ? uiState.connectState : connectUiState, (i & 16777216) != 0 ? uiState.settings : settingsUiModel, (i & 33554432) != 0 ? uiState.unreadSessionIds : set);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UiMode getMode() {
            return this.mode;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getSelectedSessionId() {
            return this.selectedSessionId;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getSelectedTitle() {
            return this.selectedTitle;
        }

        public final List<AuroraMessage> component12() {
            return this.messages;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getDraft() {
            return this.draft;
        }

        public final List<ModelChoice> component14() {
            return this.modelChoices;
        }

        public final List<String> component15() {
            return this.availableModels;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final String getSelectedModel() {
            return this.selectedModel;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getSelectedProvider() {
            return this.selectedProvider;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getModelCatalogWarning() {
            return this.modelCatalogWarning;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final boolean getModelPickerOpen() {
            return this.modelPickerOpen;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final AuroraTab getActiveTab() {
            return this.activeTab;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final boolean getListening() {
            return this.listening;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final boolean getBusy() {
            return this.busy;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final boolean getSending() {
            return this.sending;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final String getStatusLine() {
            return this.statusLine;
        }

        /* JADX INFO: renamed from: component24, reason: from getter */
        public final ConnectUiState getConnectState() {
            return this.connectState;
        }

        /* JADX INFO: renamed from: component25, reason: from getter */
        public final SettingsUiModel getSettings() {
            return this.settings;
        }

        public final Set<String> component26() {
            return this.unreadSessionIds;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final ChatPane getChatPane() {
            return this.chatPane;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final AuroraDisplayMode getDisplayMode() {
            return this.displayMode;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final ConnectionState getConnection() {
            return this.connection;
        }

        public final List<AuroraSession> component6() {
            return this.sessions;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getSourceFilter() {
            return this.sourceFilter;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getSearchQuery() {
            return this.searchQuery;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final boolean getConnectingSplash() {
            return this.connectingSplash;
        }

        public final UiState copy(UiMode mode, AuroraTab activeTab, ChatPane chatPane, AuroraDisplayMode displayMode, ConnectionState connection, List<AuroraSession> sessions, String sourceFilter, String searchQuery, boolean connectingSplash, String selectedSessionId, String selectedTitle, List<AuroraMessage> messages, String draft, List<ModelChoice> modelChoices, List<String> availableModels, String selectedModel, String selectedProvider, String modelCatalogWarning, boolean modelPickerOpen, boolean listening, boolean busy, boolean sending, String statusLine, ConnectUiState connectState, SettingsUiModel settings, Set<String> unreadSessionIds) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(activeTab, "activeTab");
            Intrinsics.checkNotNullParameter(chatPane, "chatPane");
            Intrinsics.checkNotNullParameter(displayMode, "displayMode");
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(sessions, "sessions");
            Intrinsics.checkNotNullParameter(sourceFilter, "sourceFilter");
            Intrinsics.checkNotNullParameter(searchQuery, "searchQuery");
            Intrinsics.checkNotNullParameter(selectedTitle, "selectedTitle");
            Intrinsics.checkNotNullParameter(messages, "messages");
            Intrinsics.checkNotNullParameter(draft, "draft");
            Intrinsics.checkNotNullParameter(modelChoices, "modelChoices");
            Intrinsics.checkNotNullParameter(availableModels, "availableModels");
            Intrinsics.checkNotNullParameter(connectState, "connectState");
            Intrinsics.checkNotNullParameter(settings, "settings");
            Intrinsics.checkNotNullParameter(unreadSessionIds, "unreadSessionIds");
            return new UiState(mode, activeTab, chatPane, displayMode, connection, sessions, sourceFilter, searchQuery, connectingSplash, selectedSessionId, selectedTitle, messages, draft, modelChoices, availableModels, selectedModel, selectedProvider, modelCatalogWarning, modelPickerOpen, listening, busy, sending, statusLine, connectState, settings, unreadSessionIds);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UiState)) {
                return false;
            }
            UiState uiState = (UiState) other;
            return this.mode == uiState.mode && this.activeTab == uiState.activeTab && this.chatPane == uiState.chatPane && this.displayMode == uiState.displayMode && Intrinsics.areEqual(this.connection, uiState.connection) && Intrinsics.areEqual(this.sessions, uiState.sessions) && Intrinsics.areEqual(this.sourceFilter, uiState.sourceFilter) && Intrinsics.areEqual(this.searchQuery, uiState.searchQuery) && this.connectingSplash == uiState.connectingSplash && Intrinsics.areEqual(this.selectedSessionId, uiState.selectedSessionId) && Intrinsics.areEqual(this.selectedTitle, uiState.selectedTitle) && Intrinsics.areEqual(this.messages, uiState.messages) && Intrinsics.areEqual(this.draft, uiState.draft) && Intrinsics.areEqual(this.modelChoices, uiState.modelChoices) && Intrinsics.areEqual(this.availableModels, uiState.availableModels) && Intrinsics.areEqual(this.selectedModel, uiState.selectedModel) && Intrinsics.areEqual(this.selectedProvider, uiState.selectedProvider) && Intrinsics.areEqual(this.modelCatalogWarning, uiState.modelCatalogWarning) && this.modelPickerOpen == uiState.modelPickerOpen && this.listening == uiState.listening && this.busy == uiState.busy && this.sending == uiState.sending && Intrinsics.areEqual(this.statusLine, uiState.statusLine) && Intrinsics.areEqual(this.connectState, uiState.connectState) && Intrinsics.areEqual(this.settings, uiState.settings) && Intrinsics.areEqual(this.unreadSessionIds, uiState.unreadSessionIds);
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((((((((((((((((this.mode.hashCode() * 31) + this.activeTab.hashCode()) * 31) + this.chatPane.hashCode()) * 31) + this.displayMode.hashCode()) * 31) + this.connection.hashCode()) * 31) + this.sessions.hashCode()) * 31) + this.sourceFilter.hashCode()) * 31) + this.searchQuery.hashCode()) * 31) + Boolean.hashCode(this.connectingSplash)) * 31) + (this.selectedSessionId == null ? 0 : this.selectedSessionId.hashCode())) * 31) + this.selectedTitle.hashCode()) * 31) + this.messages.hashCode()) * 31) + this.draft.hashCode()) * 31) + this.modelChoices.hashCode()) * 31) + this.availableModels.hashCode()) * 31) + (this.selectedModel == null ? 0 : this.selectedModel.hashCode())) * 31) + (this.selectedProvider == null ? 0 : this.selectedProvider.hashCode())) * 31) + (this.modelCatalogWarning == null ? 0 : this.modelCatalogWarning.hashCode())) * 31) + Boolean.hashCode(this.modelPickerOpen)) * 31) + Boolean.hashCode(this.listening)) * 31) + Boolean.hashCode(this.busy)) * 31) + Boolean.hashCode(this.sending)) * 31) + (this.statusLine != null ? this.statusLine.hashCode() : 0)) * 31) + this.connectState.hashCode()) * 31) + this.settings.hashCode()) * 31) + this.unreadSessionIds.hashCode();
        }

        public String toString() {
            return "UiState(mode=" + this.mode + ", activeTab=" + this.activeTab + ", chatPane=" + this.chatPane + ", displayMode=" + this.displayMode + ", connection=" + this.connection + ", sessions=" + this.sessions + ", sourceFilter=" + this.sourceFilter + ", searchQuery=" + this.searchQuery + ", connectingSplash=" + this.connectingSplash + ", selectedSessionId=" + this.selectedSessionId + ", selectedTitle=" + this.selectedTitle + ", messages=" + this.messages + ", draft=" + this.draft + ", modelChoices=" + this.modelChoices + ", availableModels=" + this.availableModels + ", selectedModel=" + this.selectedModel + ", selectedProvider=" + this.selectedProvider + ", modelCatalogWarning=" + this.modelCatalogWarning + ", modelPickerOpen=" + this.modelPickerOpen + ", listening=" + this.listening + ", busy=" + this.busy + ", sending=" + this.sending + ", statusLine=" + this.statusLine + ", connectState=" + this.connectState + ", settings=" + this.settings + ", unreadSessionIds=" + this.unreadSessionIds + ")";
        }

        public UiState(UiMode mode, AuroraTab activeTab, ChatPane chatPane, AuroraDisplayMode displayMode, ConnectionState connection, List<AuroraSession> sessions, String sourceFilter, String searchQuery, boolean connectingSplash, String selectedSessionId, String selectedTitle, List<AuroraMessage> messages, String draft, List<ModelChoice> modelChoices, List<String> availableModels, String selectedModel, String selectedProvider, String modelCatalogWarning, boolean modelPickerOpen, boolean listening, boolean busy, boolean sending, String statusLine, ConnectUiState connectState, SettingsUiModel settings, Set<String> unreadSessionIds) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(activeTab, "activeTab");
            Intrinsics.checkNotNullParameter(chatPane, "chatPane");
            Intrinsics.checkNotNullParameter(displayMode, "displayMode");
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(sessions, "sessions");
            Intrinsics.checkNotNullParameter(sourceFilter, "sourceFilter");
            Intrinsics.checkNotNullParameter(searchQuery, "searchQuery");
            Intrinsics.checkNotNullParameter(selectedTitle, "selectedTitle");
            Intrinsics.checkNotNullParameter(messages, "messages");
            Intrinsics.checkNotNullParameter(draft, "draft");
            Intrinsics.checkNotNullParameter(modelChoices, "modelChoices");
            Intrinsics.checkNotNullParameter(availableModels, "availableModels");
            Intrinsics.checkNotNullParameter(connectState, "connectState");
            Intrinsics.checkNotNullParameter(settings, "settings");
            Intrinsics.checkNotNullParameter(unreadSessionIds, "unreadSessionIds");
            this.mode = mode;
            this.activeTab = activeTab;
            this.chatPane = chatPane;
            this.displayMode = displayMode;
            this.connection = connection;
            this.sessions = sessions;
            this.sourceFilter = sourceFilter;
            this.searchQuery = searchQuery;
            this.connectingSplash = connectingSplash;
            this.selectedSessionId = selectedSessionId;
            this.selectedTitle = selectedTitle;
            this.messages = messages;
            this.draft = draft;
            this.modelChoices = modelChoices;
            this.availableModels = availableModels;
            this.selectedModel = selectedModel;
            this.selectedProvider = selectedProvider;
            this.modelCatalogWarning = modelCatalogWarning;
            this.modelPickerOpen = modelPickerOpen;
            this.listening = listening;
            this.busy = busy;
            this.sending = sending;
            this.statusLine = statusLine;
            this.connectState = connectState;
            this.settings = settings;
            this.unreadSessionIds = unreadSessionIds;
        }

        public /* synthetic */ UiState(UiMode uiMode, AuroraTab auroraTab, ChatPane chatPane, AuroraDisplayMode auroraDisplayMode, ConnectionState connectionState, List list, String str, String str2, boolean z, String str3, String str4, List list2, String str5, List list3, List list4, String str6, String str7, String str8, boolean z2, boolean z3, boolean z4, boolean z5, String str9, ConnectUiState connectUiState, SettingsUiModel settingsUiModel, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? UiMode.Connect : uiMode, (i & 2) != 0 ? AuroraTab.Chats : auroraTab, (i & 4) != 0 ? ChatPane.List : chatPane, (i & 8) != 0 ? AuroraDisplayMode.FULL : auroraDisplayMode, (i & 16) != 0 ? ConnectionState.Connecting.INSTANCE : connectionState, (i & 32) != 0 ? CollectionsKt.emptyList() : list, (i & 64) != 0 ? "all" : str, (i & 128) != 0 ? "" : str2, (i & 256) != 0 ? false : z, (i & 512) != 0 ? null : str3, (i & 1024) != 0 ? "" : str4, (i & 2048) != 0 ? CollectionsKt.emptyList() : list2, (i & 4096) == 0 ? str5 : "", (i & 8192) != 0 ? CollectionsKt.emptyList() : list3, (i & 16384) != 0 ? CollectionsKt.emptyList() : list4, (i & 32768) != 0 ? null : str6, (i & 65536) != 0 ? null : str7, (i & 131072) != 0 ? null : str8, (i & 262144) != 0 ? false : z2, (i & 524288) != 0 ? false : z3, (i & 1048576) != 0 ? false : z4, (i & 2097152) != 0 ? false : z5, (i & 4194304) != 0 ? null : str9, (i & 8388608) != 0 ? new ConnectUiState(null, null, false, null, 15, null) : connectUiState, (i & 16777216) != 0 ? new SettingsUiModel(false, null, false, null, null, null, null, null, null, null, null, null, null, null, false, null, false, null, false, 524287, null) : settingsUiModel, (i & 33554432) != 0 ? SetsKt.emptySet() : set);
        }





        public final List<AuroraSession> getSessions() {
            return this.sessions;
        }







        public final List<AuroraMessage> getMessages() {
            return this.messages;
        }



        public final List<ModelChoice> getModelChoices() {
            return this.modelChoices;
        }

        public final List<String> getAvailableModels() {
            return this.availableModels;
        }










        public final Set<String> getUnreadSessionIds() {
            return this.unreadSessionIds;
        }
    }

    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lai/nous/aurora/MainViewModel$ToastEvent;", "", "Error", "Info", "Lai/nous/aurora/MainViewModel$ToastEvent$Error;", "Lai/nous/aurora/MainViewModel$ToastEvent$Info;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface ToastEvent {

        /* JADX INFO: compiled from: MainViewModel.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lai/nous/aurora/MainViewModel$ToastEvent$Error;", "Lai/nous/aurora/MainViewModel$ToastEvent;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Error implements ToastEvent {
            public static final int $stable = 0;
            public final String message;

            public static /* synthetic */ Error copy$default(Error error, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = error.message;
                }
                return error.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final Error copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new Error(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.message, ((Error) other).message);
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            public String toString() {
                return "Error(message=" + this.message + ")";
            }

            public Error(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }


        }

        /* JADX INFO: compiled from: MainViewModel.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lai/nous/aurora/MainViewModel$ToastEvent$Info;", "Lai/nous/aurora/MainViewModel$ToastEvent;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Info implements ToastEvent {
            public static final int $stable = 0;
            public final String message;

            public static /* synthetic */ Info copy$default(Info info, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = info.message;
                }
                return info.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final Info copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new Info(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Info) && Intrinsics.areEqual(this.message, ((Info) other).message);
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            public String toString() {
                return "Info(message=" + this.message + ")";
            }

            public Info(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }

        }
    }

    public final StateFlow<UiState> getUiState() {
        return this.uiState;
    }

    public final SharedFlow<ToastEvent> getToasts() {
        return this.toasts;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> computeUnread(List<AuroraSession> sessions, String selectedId) {
        Set unread = new LinkedHashSet();
        List<AuroraSession> $this$forEach$iv = sessions;
        for (Object element$iv : $this$forEach$iv) {
            AuroraSession s = (AuroraSession) element$iv;
            if (Intrinsics.areEqual(s.getId(), selectedId)) {
                Map<String, Long> map = this.sessionReadMarkers;
                String id = s.getId();
                Long l = this.sessionReadMarkers.get(s.getId());
                map.put(id, Long.valueOf(Math.max(l != null ? l.longValue() : 0L, s.getLastActiveMs())));
            } else {
                Long marker = this.sessionReadMarkers.get(s.getId());
                if (marker == null) {
                    this.sessionReadMarkers.put(s.getId(), Long.valueOf(s.getLastActiveMs()));
                } else if (s.getLastActiveMs() > marker.longValue() && s.getMessageCount() > 0) {
                    unread.add(s.getId());
                }
            }
        }
        return unread;
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$1", f = "MainViewModel.kt", i = {}, l = {164}, m = "invokeSuspend", n = {}, s = {})
    final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<ConnectionState> connection = MainViewModel.this.gatewayClient.getConnection();
                    final MainViewModel mainViewModel = MainViewModel.this;
                    this.label = 1;
                    if (connection.collect(new FlowCollector() { // from class: ai.nous.aurora.MainViewModel.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((ConnectionState) value, (Continuation) $completion);
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        public final Object emit(ConnectionState conn, Continuation continuation) {
                            Object prevValue$iv;
                            UiState it;
                            MutableStateFlow mutableStateFlow = mainViewModel._uiState;
                            do {
                                prevValue$iv = mutableStateFlow.getValue();
                                it = (UiState) prevValue$iv;
                            } while (!mutableStateFlow.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, conn, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 67108847, null)));
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$2", f = "MainViewModel.kt", i = {}, l = {169}, m = "invokeSuspend", n = {}, s = {})
    final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Flow<List<AuroraSession>> flowObserveSessions = MainViewModel.this.sessionRepository.observeSessions();
                    final MainViewModel mainViewModel = MainViewModel.this;
                    this.label = 1;
                    if (flowObserveSessions.collect(new FlowCollector() { // from class: ai.nous.aurora.MainViewModel.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((List<AuroraSession>) value, (Continuation) $completion);
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        public final Object emit(List<AuroraSession> list, Continuation continuation) {
                            Object next;
                            String title;
                            MutableStateFlow mutableStateFlow = mainViewModel._uiState;
                            MainViewModel mainViewModel2 = mainViewModel;
                            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
                            while (true) {
                                Object value = mutableStateFlow2.getValue();
                                UiState uiState = (UiState) value;
                                String selectedSessionId = uiState.getSelectedSessionId();
                                Iterator<AuroraSession> it = list.iterator();
                                do {
                                    if (!it.hasNext()) {
                                        next = null;
                                        break;
                                    }
                                    next = it.next();
                                } while (!Intrinsics.areEqual(((AuroraSession) next).getId(), selectedSessionId));
                                AuroraSession auroraSession = (AuroraSession) next;
                                MainViewModel mainViewModel3 = mainViewModel2;
                                MutableStateFlow mutableStateFlow3 = mutableStateFlow2;
                                if (mutableStateFlow3.compareAndSet(value, UiState.copy$default(uiState, null, null, null, null, null, list, null, null, false, null, (auroraSession == null || (title = auroraSession.getTitle()) == null) ? uiState.getSelectedTitle() : title, null, null, null, null, null, null, null, false, false, false, false, null, null, null, mainViewModel2.computeUnread(list, selectedSessionId), 33553375, null))) {
                                    return Unit.INSTANCE;
                                }
                                mutableStateFlow2 = mutableStateFlow3;
                                mainViewModel2 = mainViewModel3;
                            }
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$3, reason: invalid class name */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$3", f = "MainViewModel.kt", i = {}, l = {182}, m = "invokeSuspend", n = {}, s = {})
    final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass3)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.bootstrap(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:103:0x0363  */
    /* JADX WARN: Code duplicated, block: B:108:0x03dd A[LOOP:2: B:54:0x014f->B:108:0x03dd, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:113:0x01c2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:28:0x00e7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:32:0x00f6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:38:0x010d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:41:0x0112  */
    /* JADX WARN: Code duplicated, block: B:44:0x011d  */
    /* JADX WARN: Code duplicated, block: B:46:0x012e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:47:0x012f  */
    /* JADX WARN: Code duplicated, block: B:50:0x013c  */
    /* JADX WARN: Code duplicated, block: B:51:0x0140  */
    /* JADX WARN: Code duplicated, block: B:52:0x0145 A[PHI: r5 r8 r9
  0x0145: PHI (r5v16 ai.nous.aurora.MainViewModel) = (r5v11 ai.nous.aurora.MainViewModel), (r5v24 ai.nous.aurora.MainViewModel) binds: [B:43:0x011b, B:51:0x0140] A[DONT_GENERATE, DONT_INLINE]
  0x0145: PHI (r8v17 'gateway' java.lang.String) = (r8v13 'gateway' java.lang.String), (r8v26 'gateway' java.lang.String) binds: [B:43:0x011b, B:51:0x0140] A[DONT_GENERATE, DONT_INLINE]
  0x0145: PHI (r9v11 'apiKey' java.lang.String) = (r9v7 'apiKey' java.lang.String), (r9v19 'apiKey' java.lang.String) binds: [B:43:0x011b, B:51:0x0140] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:56:0x015d  */
    /* JADX WARN: Code duplicated, block: B:57:0x0160  */
    /* JADX WARN: Code duplicated, block: B:60:0x016b  */
    /* JADX WARN: Code duplicated, block: B:61:0x016d  */
    /* JADX WARN: Code duplicated, block: B:66:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:71:0x022b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x022d  */
    /* JADX WARN: Code duplicated, block: B:77:0x0284  */
    /* JADX WARN: Code duplicated, block: B:79:0x0295 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:80:0x0296  */
    /* JADX WARN: Code duplicated, block: B:83:0x02aa A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:84:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:87:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:89:0x02c7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:92:0x02d8 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:95:0x02e9 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:96:0x02ea  */
    public final Object bootstrap(Continuation continuation) {
        C01811 c01811;
        MainViewModel mainViewModel;
        Object deviceId;
        AuroraCredentialStore auroraCredentialStore;
        String string;
        Object gatewayAddress;
        String gateway;
        Object apiKey;
        String apiKey2;
        Object objIsAutoConnectOptOut;
        MainViewModel mainViewModel2;
        String apiKey3;
        MainViewModel mainViewModel3;
        String gateway2;
        boolean z;
        boolean auto;
        MutableStateFlow<UiState> mutableStateFlow;
        UiState value;
        UiState it;
        boolean z2;
        ConnectUiState connectState;
        boolean z3;
        MutableStateFlow<UiState> mutableStateFlow2;
        MainViewModel mainViewModel4;
        Object prevValue$iv;
        AuroraCredentialStore auroraCredentialStore2;
        MainViewModel mainViewModel5;
        String gateway3;
        MutableStateFlow<UiState> mutableStateFlow3;
        UiState value2;
        UiState it2;
        MutableStateFlow<UiState> mutableStateFlow4;
        UiState value3;
        UiState it3;
        Object objM41connect0E7RQCE;
        Object value4;
        int i;
        SessionRepository sessionRepository;
        SessionRepository sessionRepository2;
        SessionRepository sessionRepository3;
        Object obj;
        MainViewModel mainViewModel6;
        Throwable err;
        MutableStateFlow<UiState> mutableStateFlow5;
        UiState value5;
        UiState it4;
        MutableStateFlow<UiState> mutableStateFlow6;
        UiState value6;
        UiState it5;
        if (continuation instanceof C01811) {
            c01811 = (C01811) continuation;
            if ((c01811.label & Integer.MIN_VALUE) != 0) {
                c01811.label -= Integer.MIN_VALUE;
            } else {
                c01811 = new C01811(continuation);
            }
        } else {
            c01811 = new C01811(continuation);
        }
        Object $result = c01811.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z4 = true;
        switch (c01811.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mainViewModel = this;
                AuroraCredentialStore auroraCredentialStore3 = mainViewModel.credentialStore;
                c01811.L$0 = mainViewModel;
                c01811.label = 1;
                deviceId = auroraCredentialStore3.getDeviceId(c01811);
                if (deviceId == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (deviceId == null) {
                    auroraCredentialStore = mainViewModel.credentialStore;
                    string = UUID.randomUUID().toString();
                    c01811.L$0 = mainViewModel;
                    c01811.label = 2;
                    if (auroraCredentialStore.setDeviceId(string, c01811) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                AuroraCredentialStore auroraCredentialStore4 = mainViewModel.credentialStore;
                c01811.L$0 = mainViewModel;
                c01811.label = 3;
                gatewayAddress = auroraCredentialStore4.getGatewayAddress(c01811);
                if (gatewayAddress == coroutine_suspended) {
                    return coroutine_suspended;
                }
                gateway = (String) gatewayAddress;
                if (gateway == null) {
                    gateway = "http://100.90.24.39:8642";
                }
                AuroraCredentialStore auroraCredentialStore5 = mainViewModel.credentialStore;
                c01811.L$0 = mainViewModel;
                c01811.L$1 = gateway;
                c01811.label = 4;
                apiKey = auroraCredentialStore5.getApiKey(c01811);
                if (apiKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                apiKey2 = (String) apiKey;
                if (apiKey2 == null) {
                    apiKey2 = "";
                }
                if (StringsKt.isBlank(apiKey2)) {
                    mainViewModel3 = mainViewModel;
                    gateway2 = gateway;
                    apiKey3 = apiKey2;
                    z = false;
                } else {
                    AuroraCredentialStore auroraCredentialStore6 = mainViewModel.credentialStore;
                    c01811.L$0 = mainViewModel;
                    c01811.L$1 = gateway;
                    c01811.L$2 = apiKey2;
                    c01811.label = 5;
                    objIsAutoConnectOptOut = auroraCredentialStore6.isAutoConnectOptOut(c01811);
                    if (objIsAutoConnectOptOut == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    String str = apiKey2;
                    mainViewModel2 = mainViewModel;
                    apiKey3 = str;
                    if (((Boolean) objIsAutoConnectOptOut).booleanValue()) {
                        MainViewModel mainViewModel7 = mainViewModel2;
                        apiKey2 = apiKey3;
                        mainViewModel = mainViewModel7;
                        mainViewModel3 = mainViewModel;
                        gateway2 = gateway;
                        apiKey3 = apiKey2;
                        z = false;
                    } else {
                        gateway2 = gateway;
                        mainViewModel3 = mainViewModel2;
                        z = true;
                    }
                }
                auto = z;
                mutableStateFlow = mainViewModel3._uiState;
                while (true) {
                    value = mutableStateFlow.getValue();
                    it = value;
                    if (auto) {
                        z2 = z4;
                    } else {
                        z2 = false;
                    }
                    connectState = it.getConnectState();
                    if (auto) {
                        z3 = z4;
                    } else {
                        z3 = false;
                    }
                    mutableStateFlow2 = mutableStateFlow;
                    mainViewModel4 = mainViewModel3;
                    if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, z2, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, gateway2, "", z3, null, 8, null), null, null, 58719999, null))) {
                        prevValue$iv = apiKey3;
                        if (StringsKt.isBlank((CharSequence) prevValue$iv)) {
                            mutableStateFlow4 = mainViewModel4._uiState;
                            do {
                                value3 = mutableStateFlow4.getValue();
                                it3 = value3;
                            } while (!mutableStateFlow4.compareAndSet(value3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, "Paste API key once (not stored in the APK). Gateway is pre-filled.", 3, null), null, null, 58719998, null)));
                            Object prevValue$iv2 = Unit.INSTANCE;
                            return prevValue$iv2;
                        }
                        if (!auto) {
                            mutableStateFlow3 = mainViewModel4._uiState;
                            do {
                                value2 = mutableStateFlow3.getValue();
                                it2 = value2;
                            } while (!mutableStateFlow3.compareAndSet(value2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 11, null), null, null, 58719999, null)));
                            Object prevValue$iv3 = Unit.INSTANCE;
                            return prevValue$iv3;
                        }
                        auroraCredentialStore2 = mainViewModel4.credentialStore;
                        c01811.L$0 = mainViewModel4;
                        c01811.L$1 = gateway2;
                        c01811.L$2 = apiKey3;
                        c01811.label = 6;
                        if (auroraCredentialStore2.setGatewayAddress(gateway2, c01811) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mainViewModel5 = mainViewModel4;
                        gateway3 = gateway2;
                        GatewayClient gatewayClient = mainViewModel5.gatewayClient;
                        c01811.L$0 = mainViewModel5;
                        c01811.L$1 = null;
                        c01811.L$2 = null;
                        c01811.label = 7;
                        objM41connect0E7RQCE = gatewayClient.m41connect0E7RQCE(gateway3, apiKey3, c01811);
                        if (objM41connect0E7RQCE == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        value4 = objM41connect0E7RQCE;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value4)) {
                            i = 0;
                            sessionRepository = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 8;
                            if (sessionRepository.adoptLegacyOwnerlessRows(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository2 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 9;
                            if (sessionRepository2.recoverInterruptedMessages(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository3 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 10;
                            if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = value4;
                            mainViewModel6 = mainViewModel5;
                            mainViewModel6.loadModels();
                            mainViewModel6.refreshSettings(true, true);
                            mainViewModel6.startSessionPolling();
                            mutableStateFlow6 = mainViewModel6._uiState;
                            do {
                                value6 = mutableStateFlow6.getValue();
                                it5 = value6;
                            } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                            mainViewModel5 = mainViewModel6;
                            value4 = obj;
                        }
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                        if (err != null) {
                            mutableStateFlow5 = mainViewModel5._uiState;
                            do {
                                value5 = mutableStateFlow5.getValue();
                                it4 = value5;
                            } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                        }
                        return Unit.INSTANCE;
                    }
                    mainViewModel3 = mainViewModel4;
                    mutableStateFlow = mutableStateFlow2;
                    z4 = true;
                }
                break;
            case 1:
                mainViewModel = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                deviceId = $result;
                if (deviceId == null) {
                    auroraCredentialStore = mainViewModel.credentialStore;
                    string = UUID.randomUUID().toString();
                    c01811.L$0 = mainViewModel;
                    c01811.label = 2;
                    if (auroraCredentialStore.setDeviceId(string, c01811) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                AuroraCredentialStore auroraCredentialStore7 = mainViewModel.credentialStore;
                c01811.L$0 = mainViewModel;
                c01811.label = 3;
                gatewayAddress = auroraCredentialStore7.getGatewayAddress(c01811);
                if (gatewayAddress == coroutine_suspended) {
                    return coroutine_suspended;
                }
                gateway = (String) gatewayAddress;
                if (gateway == null) {
                    gateway = "http://100.90.24.39:8642";
                }
                AuroraCredentialStore auroraCredentialStore8 = mainViewModel.credentialStore;
                c01811.L$0 = mainViewModel;
                c01811.L$1 = gateway;
                c01811.label = 4;
                apiKey = auroraCredentialStore8.getApiKey(c01811);
                if (apiKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                apiKey2 = (String) apiKey;
                if (apiKey2 == null) {
                    apiKey2 = "";
                }
                if (StringsKt.isBlank(apiKey2)) {
                    AuroraCredentialStore auroraCredentialStore9 = mainViewModel.credentialStore;
                    c01811.L$0 = mainViewModel;
                    c01811.L$1 = gateway;
                    c01811.L$2 = apiKey2;
                    c01811.label = 5;
                    objIsAutoConnectOptOut = auroraCredentialStore9.isAutoConnectOptOut(c01811);
                    if (objIsAutoConnectOptOut == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    String str2 = apiKey2;
                    mainViewModel2 = mainViewModel;
                    apiKey3 = str2;
                    if (((Boolean) objIsAutoConnectOptOut).booleanValue()) {
                        gateway2 = gateway;
                        mainViewModel3 = mainViewModel2;
                        z = true;
                    } else {
                        MainViewModel mainViewModel8 = mainViewModel2;
                        apiKey2 = apiKey3;
                        mainViewModel = mainViewModel8;
                        mainViewModel3 = mainViewModel;
                        gateway2 = gateway;
                        apiKey3 = apiKey2;
                        z = false;
                    }
                } else {
                    mainViewModel3 = mainViewModel;
                    gateway2 = gateway;
                    apiKey3 = apiKey2;
                    z = false;
                }
                auto = z;
                mutableStateFlow = mainViewModel3._uiState;
                while (true) {
                    value = mutableStateFlow.getValue();
                    it = value;
                    if (auto) {
                        z2 = z4;
                    } else {
                        z2 = false;
                    }
                    connectState = it.getConnectState();
                    if (auto) {
                        z3 = z4;
                    } else {
                        z3 = false;
                    }
                    mutableStateFlow2 = mutableStateFlow;
                    mainViewModel4 = mainViewModel3;
                    if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, z2, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, gateway2, "", z3, null, 8, null), null, null, 58719999, null))) {
                        prevValue$iv = apiKey3;
                        if (StringsKt.isBlank((CharSequence) prevValue$iv)) {
                            mutableStateFlow4 = mainViewModel4._uiState;
                            do {
                                value3 = mutableStateFlow4.getValue();
                                it3 = value3;
                            } while (!mutableStateFlow4.compareAndSet(value3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, "Paste API key once (not stored in the APK). Gateway is pre-filled.", 3, null), null, null, 58719998, null)));
                            Object prevValue$iv4 = Unit.INSTANCE;
                            return prevValue$iv4;
                        }
                        if (!auto) {
                            mutableStateFlow3 = mainViewModel4._uiState;
                            do {
                                value2 = mutableStateFlow3.getValue();
                                it2 = value2;
                            } while (!mutableStateFlow3.compareAndSet(value2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 11, null), null, null, 58719999, null)));
                            Object prevValue$iv5 = Unit.INSTANCE;
                            return prevValue$iv5;
                        }
                        auroraCredentialStore2 = mainViewModel4.credentialStore;
                        c01811.L$0 = mainViewModel4;
                        c01811.L$1 = gateway2;
                        c01811.L$2 = apiKey3;
                        c01811.label = 6;
                        if (auroraCredentialStore2.setGatewayAddress(gateway2, c01811) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mainViewModel5 = mainViewModel4;
                        gateway3 = gateway2;
                        GatewayClient gatewayClient2 = mainViewModel5.gatewayClient;
                        c01811.L$0 = mainViewModel5;
                        c01811.L$1 = null;
                        c01811.L$2 = null;
                        c01811.label = 7;
                        objM41connect0E7RQCE = gatewayClient2.m41connect0E7RQCE(gateway3, apiKey3, c01811);
                        if (objM41connect0E7RQCE == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        value4 = objM41connect0E7RQCE;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value4)) {
                            i = 0;
                            sessionRepository = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 8;
                            if (sessionRepository.adoptLegacyOwnerlessRows(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository2 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 9;
                            if (sessionRepository2.recoverInterruptedMessages(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository3 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 10;
                            if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = value4;
                            mainViewModel6 = mainViewModel5;
                            mainViewModel6.loadModels();
                            mainViewModel6.refreshSettings(true, true);
                            mainViewModel6.startSessionPolling();
                            mutableStateFlow6 = mainViewModel6._uiState;
                            do {
                                value6 = mutableStateFlow6.getValue();
                                it5 = value6;
                            } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                            mainViewModel5 = mainViewModel6;
                            value4 = obj;
                        }
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                        if (err != null) {
                            mutableStateFlow5 = mainViewModel5._uiState;
                            do {
                                value5 = mutableStateFlow5.getValue();
                                it4 = value5;
                            } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                        }
                        return Unit.INSTANCE;
                    }
                    mainViewModel3 = mainViewModel4;
                    mutableStateFlow = mutableStateFlow2;
                    z4 = true;
                }
                break;
            case 2:
                mainViewModel = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                AuroraCredentialStore auroraCredentialStore10 = mainViewModel.credentialStore;
                c01811.L$0 = mainViewModel;
                c01811.label = 3;
                gatewayAddress = auroraCredentialStore10.getGatewayAddress(c01811);
                if (gatewayAddress == coroutine_suspended) {
                    return coroutine_suspended;
                }
                gateway = (String) gatewayAddress;
                if (gateway == null) {
                    gateway = "http://100.90.24.39:8642";
                }
                AuroraCredentialStore auroraCredentialStore11 = mainViewModel.credentialStore;
                c01811.L$0 = mainViewModel;
                c01811.L$1 = gateway;
                c01811.label = 4;
                apiKey = auroraCredentialStore11.getApiKey(c01811);
                if (apiKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                apiKey2 = (String) apiKey;
                if (apiKey2 == null) {
                    apiKey2 = "";
                }
                if (StringsKt.isBlank(apiKey2)) {
                    AuroraCredentialStore auroraCredentialStore12 = mainViewModel.credentialStore;
                    c01811.L$0 = mainViewModel;
                    c01811.L$1 = gateway;
                    c01811.L$2 = apiKey2;
                    c01811.label = 5;
                    objIsAutoConnectOptOut = auroraCredentialStore12.isAutoConnectOptOut(c01811);
                    if (objIsAutoConnectOptOut == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    String str3 = apiKey2;
                    mainViewModel2 = mainViewModel;
                    apiKey3 = str3;
                    if (((Boolean) objIsAutoConnectOptOut).booleanValue()) {
                        gateway2 = gateway;
                        mainViewModel3 = mainViewModel2;
                        z = true;
                    } else {
                        MainViewModel mainViewModel9 = mainViewModel2;
                        apiKey2 = apiKey3;
                        mainViewModel = mainViewModel9;
                        mainViewModel3 = mainViewModel;
                        gateway2 = gateway;
                        apiKey3 = apiKey2;
                        z = false;
                    }
                } else {
                    mainViewModel3 = mainViewModel;
                    gateway2 = gateway;
                    apiKey3 = apiKey2;
                    z = false;
                }
                auto = z;
                mutableStateFlow = mainViewModel3._uiState;
                while (true) {
                    value = mutableStateFlow.getValue();
                    it = value;
                    if (auto) {
                        z2 = z4;
                    } else {
                        z2 = false;
                    }
                    connectState = it.getConnectState();
                    if (auto) {
                        z3 = z4;
                    } else {
                        z3 = false;
                    }
                    mutableStateFlow2 = mutableStateFlow;
                    mainViewModel4 = mainViewModel3;
                    if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, z2, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, gateway2, "", z3, null, 8, null), null, null, 58719999, null))) {
                        prevValue$iv = apiKey3;
                        if (StringsKt.isBlank((CharSequence) prevValue$iv)) {
                            mutableStateFlow4 = mainViewModel4._uiState;
                            do {
                                value3 = mutableStateFlow4.getValue();
                                it3 = value3;
                            } while (!mutableStateFlow4.compareAndSet(value3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, "Paste API key once (not stored in the APK). Gateway is pre-filled.", 3, null), null, null, 58719998, null)));
                            Object prevValue$iv6 = Unit.INSTANCE;
                            return prevValue$iv6;
                        }
                        if (!auto) {
                            mutableStateFlow3 = mainViewModel4._uiState;
                            do {
                                value2 = mutableStateFlow3.getValue();
                                it2 = value2;
                            } while (!mutableStateFlow3.compareAndSet(value2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 11, null), null, null, 58719999, null)));
                            Object prevValue$iv7 = Unit.INSTANCE;
                            return prevValue$iv7;
                        }
                        auroraCredentialStore2 = mainViewModel4.credentialStore;
                        c01811.L$0 = mainViewModel4;
                        c01811.L$1 = gateway2;
                        c01811.L$2 = apiKey3;
                        c01811.label = 6;
                        if (auroraCredentialStore2.setGatewayAddress(gateway2, c01811) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mainViewModel5 = mainViewModel4;
                        gateway3 = gateway2;
                        GatewayClient gatewayClient3 = mainViewModel5.gatewayClient;
                        c01811.L$0 = mainViewModel5;
                        c01811.L$1 = null;
                        c01811.L$2 = null;
                        c01811.label = 7;
                        objM41connect0E7RQCE = gatewayClient3.m41connect0E7RQCE(gateway3, apiKey3, c01811);
                        if (objM41connect0E7RQCE == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        value4 = objM41connect0E7RQCE;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value4)) {
                            i = 0;
                            sessionRepository = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 8;
                            if (sessionRepository.adoptLegacyOwnerlessRows(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository2 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 9;
                            if (sessionRepository2.recoverInterruptedMessages(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository3 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 10;
                            if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = value4;
                            mainViewModel6 = mainViewModel5;
                            mainViewModel6.loadModels();
                            mainViewModel6.refreshSettings(true, true);
                            mainViewModel6.startSessionPolling();
                            mutableStateFlow6 = mainViewModel6._uiState;
                            do {
                                value6 = mutableStateFlow6.getValue();
                                it5 = value6;
                            } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                            mainViewModel5 = mainViewModel6;
                            value4 = obj;
                        }
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                        if (err != null) {
                            mutableStateFlow5 = mainViewModel5._uiState;
                            do {
                                value5 = mutableStateFlow5.getValue();
                                it4 = value5;
                            } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                        }
                        return Unit.INSTANCE;
                    }
                    mainViewModel3 = mainViewModel4;
                    mutableStateFlow = mutableStateFlow2;
                    z4 = true;
                }
                break;
            case 3:
                mainViewModel = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                gatewayAddress = $result;
                gateway = (String) gatewayAddress;
                if (gateway == null) {
                    gateway = "http://100.90.24.39:8642";
                }
                AuroraCredentialStore auroraCredentialStore13 = mainViewModel.credentialStore;
                c01811.L$0 = mainViewModel;
                c01811.L$1 = gateway;
                c01811.label = 4;
                apiKey = auroraCredentialStore13.getApiKey(c01811);
                if (apiKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                apiKey2 = (String) apiKey;
                if (apiKey2 == null) {
                    apiKey2 = "";
                }
                if (StringsKt.isBlank(apiKey2)) {
                    AuroraCredentialStore auroraCredentialStore14 = mainViewModel.credentialStore;
                    c01811.L$0 = mainViewModel;
                    c01811.L$1 = gateway;
                    c01811.L$2 = apiKey2;
                    c01811.label = 5;
                    objIsAutoConnectOptOut = auroraCredentialStore14.isAutoConnectOptOut(c01811);
                    if (objIsAutoConnectOptOut == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    String str4 = apiKey2;
                    mainViewModel2 = mainViewModel;
                    apiKey3 = str4;
                    if (((Boolean) objIsAutoConnectOptOut).booleanValue()) {
                        gateway2 = gateway;
                        mainViewModel3 = mainViewModel2;
                        z = true;
                    } else {
                        MainViewModel mainViewModel10 = mainViewModel2;
                        apiKey2 = apiKey3;
                        mainViewModel = mainViewModel10;
                        mainViewModel3 = mainViewModel;
                        gateway2 = gateway;
                        apiKey3 = apiKey2;
                        z = false;
                    }
                } else {
                    mainViewModel3 = mainViewModel;
                    gateway2 = gateway;
                    apiKey3 = apiKey2;
                    z = false;
                }
                auto = z;
                mutableStateFlow = mainViewModel3._uiState;
                while (true) {
                    value = mutableStateFlow.getValue();
                    it = value;
                    if (auto) {
                        z2 = z4;
                    } else {
                        z2 = false;
                    }
                    connectState = it.getConnectState();
                    if (auto) {
                        z3 = z4;
                    } else {
                        z3 = false;
                    }
                    mutableStateFlow2 = mutableStateFlow;
                    mainViewModel4 = mainViewModel3;
                    if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, z2, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, gateway2, "", z3, null, 8, null), null, null, 58719999, null))) {
                        prevValue$iv = apiKey3;
                        if (StringsKt.isBlank((CharSequence) prevValue$iv)) {
                            mutableStateFlow4 = mainViewModel4._uiState;
                            do {
                                value3 = mutableStateFlow4.getValue();
                                it3 = value3;
                            } while (!mutableStateFlow4.compareAndSet(value3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, "Paste API key once (not stored in the APK). Gateway is pre-filled.", 3, null), null, null, 58719998, null)));
                            Object prevValue$iv8 = Unit.INSTANCE;
                            return prevValue$iv8;
                        }
                        if (!auto) {
                            mutableStateFlow3 = mainViewModel4._uiState;
                            do {
                                value2 = mutableStateFlow3.getValue();
                                it2 = value2;
                            } while (!mutableStateFlow3.compareAndSet(value2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 11, null), null, null, 58719999, null)));
                            Object prevValue$iv9 = Unit.INSTANCE;
                            return prevValue$iv9;
                        }
                        auroraCredentialStore2 = mainViewModel4.credentialStore;
                        c01811.L$0 = mainViewModel4;
                        c01811.L$1 = gateway2;
                        c01811.L$2 = apiKey3;
                        c01811.label = 6;
                        if (auroraCredentialStore2.setGatewayAddress(gateway2, c01811) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mainViewModel5 = mainViewModel4;
                        gateway3 = gateway2;
                        GatewayClient gatewayClient4 = mainViewModel5.gatewayClient;
                        c01811.L$0 = mainViewModel5;
                        c01811.L$1 = null;
                        c01811.L$2 = null;
                        c01811.label = 7;
                        objM41connect0E7RQCE = gatewayClient4.m41connect0E7RQCE(gateway3, apiKey3, c01811);
                        if (objM41connect0E7RQCE == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        value4 = objM41connect0E7RQCE;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value4)) {
                            i = 0;
                            sessionRepository = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 8;
                            if (sessionRepository.adoptLegacyOwnerlessRows(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository2 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 9;
                            if (sessionRepository2.recoverInterruptedMessages(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository3 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 10;
                            if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = value4;
                            mainViewModel6 = mainViewModel5;
                            mainViewModel6.loadModels();
                            mainViewModel6.refreshSettings(true, true);
                            mainViewModel6.startSessionPolling();
                            mutableStateFlow6 = mainViewModel6._uiState;
                            do {
                                value6 = mutableStateFlow6.getValue();
                                it5 = value6;
                            } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                            mainViewModel5 = mainViewModel6;
                            value4 = obj;
                        }
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                        if (err != null) {
                            mutableStateFlow5 = mainViewModel5._uiState;
                            do {
                                value5 = mutableStateFlow5.getValue();
                                it4 = value5;
                            } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                        }
                        return Unit.INSTANCE;
                    }
                    mainViewModel3 = mainViewModel4;
                    mutableStateFlow = mutableStateFlow2;
                    z4 = true;
                }
                break;
            case 4:
                String gateway4 = (String) c01811.L$1;
                MainViewModel mainViewModel11 = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                apiKey = $result;
                gateway = gateway4;
                mainViewModel = mainViewModel11;
                apiKey2 = (String) apiKey;
                if (apiKey2 == null) {
                    apiKey2 = "";
                }
                if (StringsKt.isBlank(apiKey2)) {
                    AuroraCredentialStore auroraCredentialStore15 = mainViewModel.credentialStore;
                    c01811.L$0 = mainViewModel;
                    c01811.L$1 = gateway;
                    c01811.L$2 = apiKey2;
                    c01811.label = 5;
                    objIsAutoConnectOptOut = auroraCredentialStore15.isAutoConnectOptOut(c01811);
                    if (objIsAutoConnectOptOut == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    String str5 = apiKey2;
                    mainViewModel2 = mainViewModel;
                    apiKey3 = str5;
                    if (((Boolean) objIsAutoConnectOptOut).booleanValue()) {
                        gateway2 = gateway;
                        mainViewModel3 = mainViewModel2;
                        z = true;
                    } else {
                        MainViewModel mainViewModel12 = mainViewModel2;
                        apiKey2 = apiKey3;
                        mainViewModel = mainViewModel12;
                        mainViewModel3 = mainViewModel;
                        gateway2 = gateway;
                        apiKey3 = apiKey2;
                        z = false;
                    }
                } else {
                    mainViewModel3 = mainViewModel;
                    gateway2 = gateway;
                    apiKey3 = apiKey2;
                    z = false;
                }
                auto = z;
                mutableStateFlow = mainViewModel3._uiState;
                while (true) {
                    value = mutableStateFlow.getValue();
                    it = value;
                    if (auto) {
                        z2 = z4;
                    } else {
                        z2 = false;
                    }
                    connectState = it.getConnectState();
                    if (auto) {
                        z3 = z4;
                    } else {
                        z3 = false;
                    }
                    mutableStateFlow2 = mutableStateFlow;
                    mainViewModel4 = mainViewModel3;
                    if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, z2, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, gateway2, "", z3, null, 8, null), null, null, 58719999, null))) {
                        prevValue$iv = apiKey3;
                        if (StringsKt.isBlank((CharSequence) prevValue$iv)) {
                            mutableStateFlow4 = mainViewModel4._uiState;
                            do {
                                value3 = mutableStateFlow4.getValue();
                                it3 = value3;
                            } while (!mutableStateFlow4.compareAndSet(value3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, "Paste API key once (not stored in the APK). Gateway is pre-filled.", 3, null), null, null, 58719998, null)));
                            Object prevValue$iv10 = Unit.INSTANCE;
                            return prevValue$iv10;
                        }
                        if (!auto) {
                            mutableStateFlow3 = mainViewModel4._uiState;
                            do {
                                value2 = mutableStateFlow3.getValue();
                                it2 = value2;
                            } while (!mutableStateFlow3.compareAndSet(value2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 11, null), null, null, 58719999, null)));
                            Object prevValue$iv11 = Unit.INSTANCE;
                            return prevValue$iv11;
                        }
                        auroraCredentialStore2 = mainViewModel4.credentialStore;
                        c01811.L$0 = mainViewModel4;
                        c01811.L$1 = gateway2;
                        c01811.L$2 = apiKey3;
                        c01811.label = 6;
                        if (auroraCredentialStore2.setGatewayAddress(gateway2, c01811) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mainViewModel5 = mainViewModel4;
                        gateway3 = gateway2;
                        GatewayClient gatewayClient5 = mainViewModel5.gatewayClient;
                        c01811.L$0 = mainViewModel5;
                        c01811.L$1 = null;
                        c01811.L$2 = null;
                        c01811.label = 7;
                        objM41connect0E7RQCE = gatewayClient5.m41connect0E7RQCE(gateway3, apiKey3, c01811);
                        if (objM41connect0E7RQCE == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        value4 = objM41connect0E7RQCE;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value4)) {
                            i = 0;
                            sessionRepository = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 8;
                            if (sessionRepository.adoptLegacyOwnerlessRows(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository2 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 9;
                            if (sessionRepository2.recoverInterruptedMessages(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository3 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 10;
                            if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = value4;
                            mainViewModel6 = mainViewModel5;
                            mainViewModel6.loadModels();
                            mainViewModel6.refreshSettings(true, true);
                            mainViewModel6.startSessionPolling();
                            mutableStateFlow6 = mainViewModel6._uiState;
                            do {
                                value6 = mutableStateFlow6.getValue();
                                it5 = value6;
                            } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                            mainViewModel5 = mainViewModel6;
                            value4 = obj;
                        }
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                        if (err != null) {
                            mutableStateFlow5 = mainViewModel5._uiState;
                            do {
                                value5 = mutableStateFlow5.getValue();
                                it4 = value5;
                            } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                        }
                        return Unit.INSTANCE;
                    }
                    mainViewModel3 = mainViewModel4;
                    mutableStateFlow = mutableStateFlow2;
                    z4 = true;
                }
                break;
            case 5:
                apiKey3 = (String) c01811.L$2;
                gateway = (String) c01811.L$1;
                mainViewModel2 = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                objIsAutoConnectOptOut = $result;
                if (((Boolean) objIsAutoConnectOptOut).booleanValue()) {
                    gateway2 = gateway;
                    mainViewModel3 = mainViewModel2;
                    z = true;
                } else {
                    MainViewModel mainViewModel13 = mainViewModel2;
                    apiKey2 = apiKey3;
                    mainViewModel = mainViewModel13;
                    mainViewModel3 = mainViewModel;
                    gateway2 = gateway;
                    apiKey3 = apiKey2;
                    z = false;
                }
                auto = z;
                mutableStateFlow = mainViewModel3._uiState;
                while (true) {
                    value = mutableStateFlow.getValue();
                    it = value;
                    if (auto) {
                        z2 = z4;
                    } else {
                        z2 = false;
                    }
                    connectState = it.getConnectState();
                    if (auto) {
                        z3 = z4;
                    } else {
                        z3 = false;
                    }
                    mutableStateFlow2 = mutableStateFlow;
                    mainViewModel4 = mainViewModel3;
                    if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, z2, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, gateway2, "", z3, null, 8, null), null, null, 58719999, null))) {
                        prevValue$iv = apiKey3;
                        if (StringsKt.isBlank((CharSequence) prevValue$iv)) {
                            mutableStateFlow4 = mainViewModel4._uiState;
                            do {
                                value3 = mutableStateFlow4.getValue();
                                it3 = value3;
                            } while (!mutableStateFlow4.compareAndSet(value3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, "Paste API key once (not stored in the APK). Gateway is pre-filled.", 3, null), null, null, 58719998, null)));
                            Object prevValue$iv12 = Unit.INSTANCE;
                            return prevValue$iv12;
                        }
                        if (!auto) {
                            mutableStateFlow3 = mainViewModel4._uiState;
                            do {
                                value2 = mutableStateFlow3.getValue();
                                it2 = value2;
                            } while (!mutableStateFlow3.compareAndSet(value2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 11, null), null, null, 58719999, null)));
                            Object prevValue$iv13 = Unit.INSTANCE;
                            return prevValue$iv13;
                        }
                        auroraCredentialStore2 = mainViewModel4.credentialStore;
                        c01811.L$0 = mainViewModel4;
                        c01811.L$1 = gateway2;
                        c01811.L$2 = apiKey3;
                        c01811.label = 6;
                        if (auroraCredentialStore2.setGatewayAddress(gateway2, c01811) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mainViewModel5 = mainViewModel4;
                        gateway3 = gateway2;
                        GatewayClient gatewayClient6 = mainViewModel5.gatewayClient;
                        c01811.L$0 = mainViewModel5;
                        c01811.L$1 = null;
                        c01811.L$2 = null;
                        c01811.label = 7;
                        objM41connect0E7RQCE = gatewayClient6.m41connect0E7RQCE(gateway3, apiKey3, c01811);
                        if (objM41connect0E7RQCE == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        value4 = objM41connect0E7RQCE;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value4)) {
                            i = 0;
                            sessionRepository = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 8;
                            if (sessionRepository.adoptLegacyOwnerlessRows(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository2 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 9;
                            if (sessionRepository2.recoverInterruptedMessages(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            sessionRepository3 = mainViewModel5.sessionRepository;
                            c01811.L$0 = mainViewModel5;
                            c01811.L$1 = value4;
                            c01811.label = 10;
                            if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = value4;
                            mainViewModel6 = mainViewModel5;
                            mainViewModel6.loadModels();
                            mainViewModel6.refreshSettings(true, true);
                            mainViewModel6.startSessionPolling();
                            mutableStateFlow6 = mainViewModel6._uiState;
                            do {
                                value6 = mutableStateFlow6.getValue();
                                it5 = value6;
                            } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                            mainViewModel5 = mainViewModel6;
                            value4 = obj;
                        }
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                        if (err != null) {
                            mutableStateFlow5 = mainViewModel5._uiState;
                            do {
                                value5 = mutableStateFlow5.getValue();
                                it4 = value5;
                            } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                        }
                        return Unit.INSTANCE;
                    }
                    mainViewModel3 = mainViewModel4;
                    mutableStateFlow = mutableStateFlow2;
                    z4 = true;
                }
                break;
            case 6:
                apiKey3 = (String) c01811.L$2;
                gateway3 = (String) c01811.L$1;
                mainViewModel5 = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                GatewayClient gatewayClient7 = mainViewModel5.gatewayClient;
                c01811.L$0 = mainViewModel5;
                c01811.L$1 = null;
                c01811.L$2 = null;
                c01811.label = 7;
                objM41connect0E7RQCE = gatewayClient7.m41connect0E7RQCE(gateway3, apiKey3, c01811);
                if (objM41connect0E7RQCE == coroutine_suspended) {
                    return coroutine_suspended;
                }
                value4 = objM41connect0E7RQCE;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value4)) {
                    i = 0;
                    sessionRepository = mainViewModel5.sessionRepository;
                    c01811.L$0 = mainViewModel5;
                    c01811.L$1 = value4;
                    c01811.label = 8;
                    if (sessionRepository.adoptLegacyOwnerlessRows(c01811) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionRepository2 = mainViewModel5.sessionRepository;
                    c01811.L$0 = mainViewModel5;
                    c01811.L$1 = value4;
                    c01811.label = 9;
                    if (sessionRepository2.recoverInterruptedMessages(c01811) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionRepository3 = mainViewModel5.sessionRepository;
                    c01811.L$0 = mainViewModel5;
                    c01811.L$1 = value4;
                    c01811.label = 10;
                    if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj = value4;
                    mainViewModel6 = mainViewModel5;
                    mainViewModel6.loadModels();
                    mainViewModel6.refreshSettings(true, true);
                    mainViewModel6.startSessionPolling();
                    mutableStateFlow6 = mainViewModel6._uiState;
                    do {
                        value6 = mutableStateFlow6.getValue();
                        it5 = value6;
                    } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                    mainViewModel5 = mainViewModel6;
                    value4 = obj;
                }
                err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                if (err != null) {
                    mutableStateFlow5 = mainViewModel5._uiState;
                    do {
                        value5 = mutableStateFlow5.getValue();
                        it4 = value5;
                    } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                }
                return Unit.INSTANCE;
            case 7:
                MainViewModel mainViewModel14 = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                value4 = ai.nous.aurora.compat.Compat.resultUnbox($result);
                mainViewModel5 = mainViewModel14;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value4)) {
                    i = 0;
                    sessionRepository = mainViewModel5.sessionRepository;
                    c01811.L$0 = mainViewModel5;
                    c01811.L$1 = value4;
                    c01811.label = 8;
                    if (sessionRepository.adoptLegacyOwnerlessRows(c01811) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionRepository2 = mainViewModel5.sessionRepository;
                    c01811.L$0 = mainViewModel5;
                    c01811.L$1 = value4;
                    c01811.label = 9;
                    if (sessionRepository2.recoverInterruptedMessages(c01811) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionRepository3 = mainViewModel5.sessionRepository;
                    c01811.L$0 = mainViewModel5;
                    c01811.L$1 = value4;
                    c01811.label = 10;
                    if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj = value4;
                    mainViewModel6 = mainViewModel5;
                    mainViewModel6.loadModels();
                    mainViewModel6.refreshSettings(true, true);
                    mainViewModel6.startSessionPolling();
                    mutableStateFlow6 = mainViewModel6._uiState;
                    do {
                        value6 = mutableStateFlow6.getValue();
                        it5 = value6;
                    } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                    mainViewModel5 = mainViewModel6;
                    value4 = obj;
                }
                err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                if (err != null) {
                    mutableStateFlow5 = mainViewModel5._uiState;
                    do {
                        value5 = mutableStateFlow5.getValue();
                        it4 = value5;
                    } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                }
                return Unit.INSTANCE;
            case 8:
                i = 0;
                value4 = c01811.L$1;
                mainViewModel5 = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                sessionRepository2 = mainViewModel5.sessionRepository;
                c01811.L$0 = mainViewModel5;
                c01811.L$1 = value4;
                c01811.label = 9;
                if (sessionRepository2.recoverInterruptedMessages(c01811) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionRepository3 = mainViewModel5.sessionRepository;
                c01811.L$0 = mainViewModel5;
                c01811.L$1 = value4;
                c01811.label = 10;
                if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = value4;
                mainViewModel6 = mainViewModel5;
                mainViewModel6.loadModels();
                mainViewModel6.refreshSettings(true, true);
                mainViewModel6.startSessionPolling();
                mutableStateFlow6 = mainViewModel6._uiState;
                do {
                    value6 = mutableStateFlow6.getValue();
                    it5 = value6;
                } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                mainViewModel5 = mainViewModel6;
                value4 = obj;
                err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                if (err != null) {
                    mutableStateFlow5 = mainViewModel5._uiState;
                    do {
                        value5 = mutableStateFlow5.getValue();
                        it4 = value5;
                    } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                }
                return Unit.INSTANCE;
            case 9:
                i = 0;
                value4 = c01811.L$1;
                mainViewModel5 = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                sessionRepository3 = mainViewModel5.sessionRepository;
                c01811.L$0 = mainViewModel5;
                c01811.L$1 = value4;
                c01811.label = 10;
                if (sessionRepository3.mo53refreshSessionsIoAF18A(c01811) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = value4;
                mainViewModel6 = mainViewModel5;
                mainViewModel6.loadModels();
                mainViewModel6.refreshSettings(true, true);
                mainViewModel6.startSessionPolling();
                mutableStateFlow6 = mainViewModel6._uiState;
                do {
                    value6 = mutableStateFlow6.getValue();
                    it5 = value6;
                } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                mainViewModel5 = mainViewModel6;
                value4 = obj;
                err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                if (err != null) {
                    mutableStateFlow5 = mainViewModel5._uiState;
                    do {
                        value5 = mutableStateFlow5.getValue();
                        it4 = value5;
                    } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                }
                return Unit.INSTANCE;
            case 10:
                obj = c01811.L$1;
                MainViewModel mainViewModel15 = (MainViewModel) c01811.L$0;
                ResultKt.throwOnFailure($result);
                ai.nous.aurora.compat.Compat.resultUnbox($result);
                mainViewModel6 = mainViewModel15;
                mainViewModel6.loadModels();
                mainViewModel6.refreshSettings(true, true);
                mainViewModel6.startSessionPolling();
                mutableStateFlow6 = mainViewModel6._uiState;
                do {
                    value6 = mutableStateFlow6.getValue();
                    it5 = value6;
                } while (!mutableStateFlow6.compareAndSet(value6, UiState.copy$default(it5, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected to hermes-hostinger", ConnectUiState.copy$default(it5.getConnectState(), null, "", false, null, 1, null), null, null, 54525694, null)));
                mainViewModel5 = mainViewModel6;
                value4 = obj;
                err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value4);
                if (err != null) {
                    mutableStateFlow5 = mainViewModel5._uiState;
                    do {
                        value5 = mutableStateFlow5.getValue();
                        it4 = value5;
                    } while (!mutableStateFlow5.compareAndSet(value5, UiState.copy$default(it4, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it4.getConnectState(), null, null, false, "Could not reach hermes-hostinger: " + err.getMessage(), 3, null), null, null, 58719998, null)));
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void onGatewayAddressChange(String value) {
        UiState value2;
        UiState it;
        Intrinsics.checkNotNullParameter(value, "value");
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value2 = mutableStateFlow.getValue();
            it = value2;
        } while (!mutableStateFlow.compareAndSet(value2, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it.getConnectState(), value, null, false, null, 6, null), null, null, 58720255, null)));
    }

    public final void onApiKeyChange(String value) {
        UiState value2;
        UiState it;
        Intrinsics.checkNotNullParameter(value, "value");
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value2 = mutableStateFlow.getValue();
            it = value2;
        } while (!mutableStateFlow.compareAndSet(value2, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it.getConnectState(), null, value, false, null, 5, null), null, null, 58720255, null)));
    }

    public final void onDraftChange(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        while (true) {
            UiState value2 = mutableStateFlow.getValue();
            UiState it = value2;
            MutableStateFlow<UiState> mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value2, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, value, null, null, null, null, null, false, false, false, false, null, null, null, null, 67104767, null))) {
                return;
            } else {
                mutableStateFlow = mutableStateFlow2;
            }
        }
    }

    public final void setModelPickerOpen(boolean open) {
        UiState value;
        UiState it;
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            it = value;
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, open, false, false, false, null, null, null, null, 66846719, null)));
        if (open) {
            loadModels();
        }
    }

    public final void selectModelChoice(ModelChoice choice) {
        UiState value;
        UiState it;
        Intrinsics.checkNotNullParameter(choice, "choice");
        if (!choice.getPersistable()) {
            this._toasts.tryEmit(new ToastEvent.Error("That saved provider/model pair is unavailable. Choose a valid model below."));
            return;
        }
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            it = value;
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, choice.getModel(), choice.getProvider(), null, false, false, false, false, null, null, null, null, 66748415, null)));
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01992(choice, this, null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$selectModelChoice$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$selectModelChoice$2", f = "MainViewModel.kt", i = {}, l = {309, 311, 316}, m = "invokeSuspend", n = {}, s = {})
    static final class C01992 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ ModelChoice $choice;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ MainViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01992(ModelChoice modelChoice, MainViewModel mainViewModel, Continuation continuation) {
            super(2, continuation);
            this.$choice = modelChoice;
            this.this$0 = mainViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return new C01992(this.$choice, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01992)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:28:0x0123  */
        /* JADX WARN: Code duplicated, block: B:30:0x013f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:32:0x0149 A[PHI: r4
  0x0149: PHI (r4v32 java.lang.Object) = (r4v30 java.lang.Object), (r4v33 java.lang.Object) binds: [B:27:0x0121, B:31:0x0140] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:34:0x0152  */
        /* JADX WARN: Code duplicated, block: B:36:0x015f  */
        /* JADX WARN: Code duplicated, block: B:39:0x0170 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:40:0x0171  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object value;
            MainViewModel mainViewModel;
            MutableSharedFlow mutableSharedFlow;
            ToastEvent.Info info;
            MainViewModel mainViewModel2;
            Throwable t;
            MutableSharedFlow mutableSharedFlow2;
            ToastEvent.Error error;
            String message;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    List pairs = new ArrayList();
                    String role = this.$choice.getRole();
                    if (Intrinsics.areEqual(role, "Fallback")) {
                        pairs.add(TuplesKt.to("fallback_model.model", JsonElementKt.JsonPrimitive(this.$choice.getModel())));
                        if (!StringsKt.isBlank(this.$choice.getProvider())) {
                            pairs.add(TuplesKt.to("fallback_model.provider", JsonElementKt.JsonPrimitive(this.$choice.getProvider())));
                        }
                    } else if (Intrinsics.areEqual(role, "Delegation")) {
                        pairs.add(TuplesKt.to("delegation.model", JsonElementKt.JsonPrimitive(this.$choice.getModel())));
                        if (!StringsKt.isBlank(this.$choice.getProvider())) {
                            pairs.add(TuplesKt.to("delegation.provider", JsonElementKt.JsonPrimitive(this.$choice.getProvider())));
                        }
                    } else {
                        pairs.add(TuplesKt.to("model.default", JsonElementKt.JsonPrimitive(this.$choice.getModel())));
                        if (!StringsKt.isBlank(this.$choice.getProvider())) {
                            pairs.add(TuplesKt.to("model.provider", JsonElementKt.JsonPrimitive(this.$choice.getProvider())));
                        }
                    }
                    this.label = 1;
                    Object objMo61setValuesgIAlus = this.this$0.settingsRepository.mo61setValuesgIAlus(pairs, this);
                    if (objMo61setValuesgIAlus == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    value = objMo61setValuesgIAlus;
                    mainViewModel = this.this$0;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        mutableSharedFlow = mainViewModel._toasts;
                        info = new ToastEvent.Info("Model saved on host");
                        this.L$0 = value;
                        this.L$1 = mainViewModel;
                        this.label = 2;
                        if (mutableSharedFlow.emit(info, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        MainViewModel.refreshSettings$default(mainViewModel, false, false, 3, null);
                        mainViewModel.loadModels();
                        mainViewModel2 = this.this$0;
                        t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                        if (t != null) {
                            mutableSharedFlow2 = mainViewModel2._toasts;
                            message = t.getMessage();
                            if (message == null) {
                                message = "Model save failed";
                            }
                            error = new ToastEvent.Error(message);
                            this.L$0 = value;
                            this.L$1 = mainViewModel2;
                            this.label = 3;
                            if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            mainViewModel2.loadModels();
                        }
                    } else {
                        mainViewModel2 = this.this$0;
                        t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                        if (t != null) {
                            mutableSharedFlow2 = mainViewModel2._toasts;
                            message = t.getMessage();
                            if (message == null) {
                                message = "Model save failed";
                            }
                            error = new ToastEvent.Error(message);
                            this.L$0 = value;
                            this.L$1 = mainViewModel2;
                            this.label = 3;
                            if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            mainViewModel2.loadModels();
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    value = ai.nous.aurora.compat.Compat.resultUnbox($result);
                    mainViewModel = this.this$0;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        mutableSharedFlow = mainViewModel._toasts;
                        info = new ToastEvent.Info("Model saved on host");
                        this.L$0 = value;
                        this.L$1 = mainViewModel;
                        this.label = 2;
                        if (mutableSharedFlow.emit(info, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        MainViewModel.refreshSettings$default(mainViewModel, false, false, 3, null);
                        mainViewModel.loadModels();
                        mainViewModel2 = this.this$0;
                        t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                        if (t != null) {
                            mutableSharedFlow2 = mainViewModel2._toasts;
                            message = t.getMessage();
                            if (message == null) {
                                message = "Model save failed";
                            }
                            error = new ToastEvent.Error(message);
                            this.L$0 = value;
                            this.L$1 = mainViewModel2;
                            this.label = 3;
                            if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            mainViewModel2.loadModels();
                        }
                    } else {
                        mainViewModel2 = this.this$0;
                        t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                        if (t != null) {
                            mutableSharedFlow2 = mainViewModel2._toasts;
                            message = t.getMessage();
                            if (message == null) {
                                message = "Model save failed";
                            }
                            error = new ToastEvent.Error(message);
                            this.L$0 = value;
                            this.L$1 = mainViewModel2;
                            this.label = 3;
                            if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            mainViewModel2.loadModels();
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    mainViewModel = (MainViewModel) this.L$1;
                    value = this.L$0;
                    ResultKt.throwOnFailure($result);
                    MainViewModel.refreshSettings$default(mainViewModel, false, false, 3, null);
                    mainViewModel.loadModels();
                    mainViewModel2 = this.this$0;
                    t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (t != null) {
                        mutableSharedFlow2 = mainViewModel2._toasts;
                        message = t.getMessage();
                        if (message == null) {
                            message = "Model save failed";
                        }
                        error = new ToastEvent.Error(message);
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 3;
                        if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mainViewModel2.loadModels();
                    }
                    return Unit.INSTANCE;
                case 3:
                    mainViewModel2 = (MainViewModel) this.L$1;
                    ResultKt.throwOnFailure($result);
                    mainViewModel2.loadModels();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void setListening(boolean listening) {
        UiState value;
        UiState it;
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            it = value;
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, listening, false, false, null, null, null, null, 66584575, null)));
    }

    public final void appendDictation(String text) {
        UiState value;
        UiState st;
        String next;
        Intrinsics.checkNotNullParameter(text, "text");
        String piece = StringsKt.trim((CharSequence) text).toString();
        if (piece.length() == 0) {
            return;
        }
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            st = value;
            String base = StringsKt.trimEnd((CharSequence) st.getDraft()).toString();
            next = base.length() == 0 ? piece : base + " " + piece;
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(st, null, null, null, null, null, null, null, null, false, null, null, null, next, null, null, null, null, null, false, false, false, false, null, null, null, null, 67104767, null)));
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$loadModels$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$loadModels$1", f = "MainViewModel.kt", i = {1}, l = {338, 339}, m = "invokeSuspend", n = {"config"}, s = {"L$0"})
    final class C01891 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C01891(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C01891 c01891 = MainViewModel.this.new C01891(continuation);
            c01891.L$0 = obj;
            return c01891;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01891)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:103:0x0287  */
        /* JADX WARN: Code duplicated, block: B:105:0x029f  */
        /* JADX WARN: Code duplicated, block: B:108:0x02af  */
        /* JADX WARN: Code duplicated, block: B:111:0x02b3 A[LOOP:3: B:101:0x0281->B:111:0x02b3, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:115:0x02be A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:116:0x02c0  */
        /* JADX WARN: Code duplicated, block: B:119:0x02ce  */
        /* JADX WARN: Code duplicated, block: B:125:0x02e7  */
        /* JADX WARN: Code duplicated, block: B:126:0x02f4  */
        /* JADX WARN: Code duplicated, block: B:130:0x0314 A[LOOP:5: B:128:0x030e->B:130:0x0314, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:136:0x037d A[LOOP:1: B:85:0x01e8->B:136:0x037d, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:141:0x037a A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:143:0x0276 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:147:0x02b6 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:148:0x02b9 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:149:0x02e1 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:23:0x006a  */
        /* JADX WARN: Code duplicated, block: B:26:0x0081 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:27:0x0082  */
        /* JADX WARN: Code duplicated, block: B:30:0x008c  */
        /* JADX WARN: Code duplicated, block: B:31:0x008e  */
        /* JADX WARN: Code duplicated, block: B:34:0x0093  */
        /* JADX WARN: Code duplicated, block: B:35:0x0098  */
        /* JADX WARN: Code duplicated, block: B:37:0x009b  */
        /* JADX WARN: Code duplicated, block: B:40:0x00a4  */
        /* JADX WARN: Code duplicated, block: B:42:0x00aa  */
        /* JADX WARN: Code duplicated, block: B:44:0x00b4  */
        /* JADX WARN: Code duplicated, block: B:47:0x00bd  */
        /* JADX WARN: Code duplicated, block: B:53:0x00d4  */
        /* JADX WARN: Code duplicated, block: B:56:0x00dd  */
        /* JADX WARN: Code duplicated, block: B:59:0x00ec  */
        /* JADX WARN: Code duplicated, block: B:63:0x00f6  */
        /* JADX WARN: Code duplicated, block: B:66:0x0103  */
        /* JADX WARN: Code duplicated, block: B:69:0x011f A[LOOP:0: B:67:0x0119->B:69:0x011f, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:71:0x0153  */
        /* JADX WARN: Code duplicated, block: B:74:0x0183  */
        /* JADX WARN: Code duplicated, block: B:76:0x0189  */
        /* JADX WARN: Code duplicated, block: B:78:0x018f  */
        /* JADX WARN: Code duplicated, block: B:80:0x01a7  */
        /* JADX WARN: Code duplicated, block: B:82:0x01b0  */
        /* JADX WARN: Code duplicated, block: B:83:0x01de  */
        /* JADX WARN: Code duplicated, block: B:87:0x01f9  */
        /* JADX WARN: Code duplicated, block: B:88:0x023e  */
        /* JADX WARN: Code duplicated, block: B:91:0x024c  */
        /* JADX WARN: Code duplicated, block: B:93:0x0262  */
        /* JADX WARN: Code duplicated, block: B:96:0x0272  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object $result2;
            Object objM7388constructorimpl;
            HermesSettingsSnapshot config;
            Object objMo58loadModelOptionsgIAlus;
            HermesSettingsSnapshot config2;
            Object $result3;
            Object $result4;
            Object obj;
            ModelOptionsSnapshot catalog;
            Map<String, JsonElement> mapEmptyMap;
            Map<String, JsonElement> map;
            ModelSelection modelSelection;
            String strInvokeSuspend$str;
            String strInvokeSuspend$str2;
            ModelSelection primary;
            String strInvokeSuspend$str3;
            String strInvokeSuspend$str4;
            ModelSelection fallback;
            String strInvokeSuspend$str5;
            LinkedHashMap choices;
            LinkedHashMap choices2;
            ModelSelection fallback2;
            ModelSelection primary2;
            List invalidRoles;
            List list;
            String warning;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv;
            UiState state;
            Iterator it;
            Object element$iv;
            ModelChoice currentChoice;
            Iterator it2;
            Object $result5;
            Object element$iv2;
            ModelChoice retained;
            Collection destination$iv$iv;
            UiState uiStateCopy$default;
            Iterator it3;
            Object element$iv3;
            ModelChoice it4;
            ModelChoice choice;
            boolean z;
            ModelChoice it5;
            boolean z2;
            Throwable thM7391exceptionOrNullimpl;
            String message;
            Object $result6;
            Object $result7;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        MainViewModel mainViewModel = MainViewModel.this;
                        Result.Companion companion = Result.Companion;
                        SettingsRepository settingsRepository = mainViewModel.settingsRepository;
                        this.label = 1;
                        Object objLoad = settingsRepository.load(true, this);
                        if (objLoad == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result6 = $result;
                        $result7 = objLoad;
                        try {
                            $result2 = (HermesSettingsSnapshot) $result7;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl($result2);
                            $result2 = $result6;
                        Throwable th = null;
                        } catch (Throwable th) {
                            $result2 = $result6;
                            Result.Companion companion2 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                        }
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = null;
                        }
                        config = (HermesSettingsSnapshot) objM7388constructorimpl;
                        this.L$0 = config;
                        this.label = 2;
                        objMo58loadModelOptionsgIAlus = MainViewModel.this.settingsRepository.mo58loadModelOptionsgIAlus(false, this);
                        if (objMo58loadModelOptionsgIAlus == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        config2 = config;
                        $result3 = $result2;
                        $result4 = objMo58loadModelOptionsgIAlus;
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl($result4)) {
                            obj = null;
                        } else {
                            obj = $result4;
                        }
                        catalog = (ModelOptionsSnapshot) obj;
                        if (config2 != null) {
                            mapEmptyMap = config2.getValues();
                        } else {
                            mapEmptyMap = null;
                        }
                        if (mapEmptyMap == null) {
                            mapEmptyMap = MapsKt.emptyMap();
                        }
                        map = mapEmptyMap;
                        if (catalog != null || (modelSelection = catalog.getCurrent()) == null) {
                            strInvokeSuspend$str = invokeSuspend$str(map, "model.provider");
                            if (strInvokeSuspend$str == null) {
                                strInvokeSuspend$str = "";
                            }
                            strInvokeSuspend$str2 = invokeSuspend$str(map, "model.default");
                            if (strInvokeSuspend$str2 == null && (strInvokeSuspend$str2 = invokeSuspend$str(map, "model")) == null) {
                                strInvokeSuspend$str2 = "";
                            }
                            modelSelection = new ModelSelection(strInvokeSuspend$str, strInvokeSuspend$str2);
                        }
                        primary = modelSelection;
                        strInvokeSuspend$str3 = invokeSuspend$str(map, "fallback_model.provider");
                        if (strInvokeSuspend$str3 == null) {
                            strInvokeSuspend$str3 = "";
                        }
                        strInvokeSuspend$str4 = invokeSuspend$str(map, "fallback_model.model");
                        if (strInvokeSuspend$str4 == null) {
                            strInvokeSuspend$str4 = "";
                        }
                        fallback = new ModelSelection(strInvokeSuspend$str3, strInvokeSuspend$str4);
                        strInvokeSuspend$str5 = invokeSuspend$str(map, "delegation.provider");
                        if (strInvokeSuspend$str5 == null) {
                            strInvokeSuspend$str5 = "";
                        }
                        String strInvokeSuspend$str6 = invokeSuspend$str(map, "delegation.model");
                        ModelSelection delegation = new ModelSelection(strInvokeSuspend$str5, strInvokeSuspend$str6 != null ? strInvokeSuspend$str6 : "");
                        choices = new LinkedHashMap();
                        if (catalog == null) {
                            choices2 = choices;
                            fallback2 = fallback;
                            primary2 = primary;
                        } else {
                            Iterable $this$forEach$iv = ModelCatalogPolicy.INSTANCE.choices(catalog.getProviders());
                            Object element$iv4 = null;
                            for (Object element$iv4 : $this$forEach$iv) {
                                CatalogModelChoice choice2 = (CatalogModelChoice) element$iv4;
                                invokeSuspend$add$default(choices, catalog, choice2.getModel(), choice2.getProvider(), "Available", false, 32, null);
                            }
                            choices2 = choices;
                            fallback2 = fallback;
                            primary2 = primary;
                        }
                        invalidRoles = new ArrayList();
                        invokeSuspend$addConfigured(catalog, invalidRoles, choices2, primary2, "Primary");
                        invokeSuspend$addConfigured(catalog, invalidRoles, choices2, fallback2, "Fallback");
                        invokeSuspend$addConfigured(catalog, invalidRoles, choices2, delegation, "Delegation");
                        Collection collectionValues = choices2.values();
                        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
                        list = CollectionsKt.toList(collectionValues);
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl($result4)) {
                            thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result4);
                            if (thM7391exceptionOrNullimpl != null || (message = thM7391exceptionOrNullimpl.getMessage()) == null) {
                                message = "bridge error";
                            }
                            warning = "Provider catalog unavailable: " + message;
                        } else if (invalidRoles.isEmpty()) {
                            warning = null;
                        } else {
                            warning = "Invalid saved provider/model pair: " + ai.nous.aurora.compat.Compat.d_CollectionsKt_joinToString_9(invalidRoles, null, null, null, 0, null, null, 63, null) + ". Choose a model from its real provider.";
                        }
                        $this$update$iv = MainViewModel.this._uiState;
                        while (true) {
                            prevValue$iv = $this$update$iv.getValue();
                            state = (UiState) prevValue$iv;
                            if (list.isEmpty()) {
                                uiStateCopy$default = UiState.copy$default(state, null, null, null, null, null, null, null, null, false, null, null, null, null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), null, null, warning, false, false, false, false, null, null, null, null, 66953215, null);
                                $result5 = $result3;
                            } else {
                                List $this$firstOrNull$iv = list;
                                it = $this$firstOrNull$iv.iterator();
                                do {
                                    if (it.hasNext()) {
                                        element$iv = it.next();
                                        it5 = (ModelChoice) element$iv;
                                        if (Intrinsics.areEqual(it5.getModel(), primary2.getModel()) || !Intrinsics.areEqual(it5.getProvider(), primary2.getProvider())) {
                                            z2 = false;
                                        } else {
                                            z2 = true;
                                        }
                                    } else {
                                        element$iv = null;
                                    }
                                    currentChoice = (ModelChoice) element$iv;
                                    List $this$firstOrNull$iv2 = list;
                                    it2 = $this$firstOrNull$iv2.iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            element$iv2 = it2.next();
                                            choice = (ModelChoice) element$iv2;
                                            $result5 = $result3;
                                            if (Intrinsics.areEqual(choice.getModel(), state.getSelectedModel()) || !Intrinsics.areEqual(choice.getProvider(), state.getSelectedProvider())) {
                                                z = false;
                                            } else {
                                                z = true;
                                            }
                                            if (z) {
                                                $result3 = $result5;
                                            }
                                        } else {
                                            $result5 = $result3;
                                            element$iv2 = null;
                                        }
                                    }
                                    retained = (ModelChoice) element$iv2;
                                    if (currentChoice == null) {
                                        if (retained == null) {
                                            currentChoice = retained;
                                        } else {
                                            List $this$firstOrNull$iv3 = list;
                                            it3 = $this$firstOrNull$iv3.iterator();
                                            do {
                                                if (it3.hasNext()) {
                                                    element$iv3 = it3.next();
                                                    it4 = (ModelChoice) element$iv3;
                                                } else {
                                                    element$iv3 = null;
                                                }
                                                currentChoice = (ModelChoice) element$iv3;
                                                if (currentChoice == null) {
                                                    Object objFirst = CollectionsKt.first((List<? extends Object>) list);
                                                    Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
                                                    currentChoice = (ModelChoice) objFirst;
                                                }
                                            } while (!Intrinsics.areEqual(it4.getRole(), "Primary"));
                                            currentChoice = (ModelChoice) element$iv3;
                                            if (currentChoice == null) {
                                                Object objFirst2 = CollectionsKt.first((List<? extends Object>) list);
                                                Intrinsics.checkNotNullExpressionValue(objFirst2, "first(...)");
                                                currentChoice = (ModelChoice) objFirst2;
                                            }
                                        }
                                    }
                                    ModelChoice retained2 = currentChoice;
                                    List $this$map$iv = list;
                                    destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                    for (Object item$iv$iv : $this$map$iv) {
                                        ModelChoice it6 = (ModelChoice) item$iv$iv;
                                        destination$iv$iv.add(it6.getModel());
                                    }
                                    uiStateCopy$default = UiState.copy$default(state, null, null, null, null, null, null, null, null, false, null, null, null, null, list, CollectionsKt.distinct((List) destination$iv$iv), retained2.getModel(), retained2.getProvider(), warning, false, false, false, false, null, null, null, null, 66854911, null);
                                } while (!z2);
                                currentChoice = (ModelChoice) element$iv;
                                List $this$firstOrNull$iv4 = list;
                                it2 = $this$firstOrNull$iv4.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        element$iv2 = it2.next();
                                        choice = (ModelChoice) element$iv2;
                                        $result5 = $result3;
                                        if (Intrinsics.areEqual(choice.getModel(), state.getSelectedModel())) {
                                            z = false;
                                        } else {
                                            z = false;
                                        }
                                        if (z) {
                                            $result3 = $result5;
                                        }
                                    } else {
                                        $result5 = $result3;
                                        element$iv2 = null;
                                    }
                                }
                                retained = (ModelChoice) element$iv2;
                                if (currentChoice == null) {
                                    if (retained == null) {
                                        currentChoice = retained;
                                    } else {
                                        List $this$firstOrNull$iv5 = list;
                                        it3 = $this$firstOrNull$iv5.iterator();
                                        do {
                                            if (it3.hasNext()) {
                                                element$iv3 = it3.next();
                                                it4 = (ModelChoice) element$iv3;
                                            } else {
                                                element$iv3 = null;
                                            }
                                            currentChoice = (ModelChoice) element$iv3;
                                            if (currentChoice == null) {
                                                Object objFirst3 = CollectionsKt.first((List<? extends Object>) list);
                                                Intrinsics.checkNotNullExpressionValue(objFirst3, "first(...)");
                                                currentChoice = (ModelChoice) objFirst3;
                                            }
                                        } while (!Intrinsics.areEqual(it4.getRole(), "Primary"));
                                        currentChoice = (ModelChoice) element$iv3;
                                        if (currentChoice == null) {
                                            Object objFirst4 = CollectionsKt.first((List<? extends Object>) list);
                                            Intrinsics.checkNotNullExpressionValue(objFirst4, "first(...)");
                                            currentChoice = (ModelChoice) objFirst4;
                                        }
                                    }
                                }
                                ModelChoice retained3 = currentChoice;
                                List $this$map$iv2 = list;
                                destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                                java.util.Iterator<Object> r13 = collectionValues.iterator();
                                java.util.Iterator<Object> r13U21 = collectionValues.iterator();
                                while (r13U21.hasNext()) {
    Object item$iv$iv = r13U21.next();

                                    ModelChoice it7 = (ModelChoice) item$iv$iv;
                                    destination$iv$iv.add(it7.getModel());
                                }
                                uiStateCopy$default = UiState.copy$default(state, null, null, null, null, null, null, null, null, false, null, null, null, null, list, CollectionsKt.distinct((List) destination$iv$iv), retained3.getModel(), retained3.getProvider(), warning, false, false, false, false, null, null, null, null, 66854911, null);
                            }
                            if ($this$update$iv.compareAndSet(prevValue$iv, uiStateCopy$default)) {
                                Object nextValue$iv = Unit.INSTANCE;
                                return nextValue$iv;
                            }
                            $result3 = $result5;
                        }
                        break;
                    case 1:
                        $result7 = $result;
                        ResultKt.throwOnFailure($result7);
                        $result6 = $result7;
                        $result2 = (HermesSettingsSnapshot) $result7;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl($result2);
                        $result2 = $result6;
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = null;
                        }
                        config = (HermesSettingsSnapshot) objM7388constructorimpl;
                        this.L$0 = config;
                        this.label = 2;
                        objMo58loadModelOptionsgIAlus = MainViewModel.this.settingsRepository.mo58loadModelOptionsgIAlus(false, this);
                        if (objMo58loadModelOptionsgIAlus == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        config2 = config;
                        $result3 = $result2;
                        $result4 = objMo58loadModelOptionsgIAlus;
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl($result4)) {
                            obj = null;
                        } else {
                            obj = $result4;
                        }
                        catalog = (ModelOptionsSnapshot) obj;
                        if (config2 != null) {
                            mapEmptyMap = config2.getValues();
                        } else {
                            mapEmptyMap = null;
                        }
                        if (mapEmptyMap == null) {
                            mapEmptyMap = MapsKt.emptyMap();
                        }
                        map = mapEmptyMap;
                        if (catalog != null) {
                            strInvokeSuspend$str = invokeSuspend$str(map, "model.provider");
                            if (strInvokeSuspend$str == null) {
                                strInvokeSuspend$str = "";
                            }
                            strInvokeSuspend$str2 = invokeSuspend$str(map, "model.default");
                            if (strInvokeSuspend$str2 == null) {
                                strInvokeSuspend$str2 = "";
                            }
                            modelSelection = new ModelSelection(strInvokeSuspend$str, strInvokeSuspend$str2);
                        } else {
                            strInvokeSuspend$str = invokeSuspend$str(map, "model.provider");
                            if (strInvokeSuspend$str == null) {
                                strInvokeSuspend$str = "";
                            }
                            strInvokeSuspend$str2 = invokeSuspend$str(map, "model.default");
                            if (strInvokeSuspend$str2 == null) {
                                strInvokeSuspend$str2 = "";
                            }
                            modelSelection = new ModelSelection(strInvokeSuspend$str, strInvokeSuspend$str2);
                        }
                        primary = modelSelection;
                        strInvokeSuspend$str3 = invokeSuspend$str(map, "fallback_model.provider");
                        if (strInvokeSuspend$str3 == null) {
                            strInvokeSuspend$str3 = "";
                        }
                        strInvokeSuspend$str4 = invokeSuspend$str(map, "fallback_model.model");
                        if (strInvokeSuspend$str4 == null) {
                            strInvokeSuspend$str4 = "";
                        }
                        fallback = new ModelSelection(strInvokeSuspend$str3, strInvokeSuspend$str4);
                        strInvokeSuspend$str5 = invokeSuspend$str(map, "delegation.provider");
                        if (strInvokeSuspend$str5 == null) {
                            strInvokeSuspend$str5 = "";
                        }
                        String strInvokeSuspend$str7 = invokeSuspend$str(map, "delegation.model");
                        ModelSelection delegation2 = new ModelSelection(strInvokeSuspend$str5, strInvokeSuspend$str7 != null ? strInvokeSuspend$str7 : "");
                        choices = new LinkedHashMap();
                        if (catalog == null) {
                            choices2 = choices;
                            fallback2 = fallback;
                            primary2 = primary;
                        } else {
                            Iterable $this$forEach$iv2 = ModelCatalogPolicy.INSTANCE.choices(catalog.getProviders());
                            java.util.Iterator<Object> r18 = collectionValues.iterator();
                            java.util.Iterator<Object> r18U22 = collectionValues.iterator();
                            while (r18U22.hasNext()) {
    Object element$iv4 = r18U22.next();

                                CatalogModelChoice choice3 = (CatalogModelChoice) element$iv4;
                                invokeSuspend$add$default(choices, catalog, choice3.getModel(), choice3.getProvider(), "Available", false, 32, null);
                            }
                            choices2 = choices;
                            fallback2 = fallback;
                            primary2 = primary;
                        }
                        invalidRoles = new ArrayList();
                        invokeSuspend$addConfigured(catalog, invalidRoles, choices2, primary2, "Primary");
                        invokeSuspend$addConfigured(catalog, invalidRoles, choices2, fallback2, "Fallback");
                        invokeSuspend$addConfigured(catalog, invalidRoles, choices2, delegation2, "Delegation");
                        Collection collectionValues2 = choices2.values();
                        Intrinsics.checkNotNullExpressionValue(collectionValues2, "<get-values>(...)");
                        list = CollectionsKt.toList(collectionValues2);
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl($result4)) {
                            thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result4);
                            if (thM7391exceptionOrNullimpl != null) {
                                message = "bridge error";
                            } else {
                                message = "bridge error";
                            }
                            warning = "Provider catalog unavailable: " + message;
                        } else if (invalidRoles.isEmpty()) {
                            warning = "Invalid saved provider/model pair: " + ai.nous.aurora.compat.Compat.d_CollectionsKt_joinToString_9(invalidRoles, null, null, null, 0, null, null, 63, null) + ". Choose a model from its real provider.";
                        } else {
                            warning = null;
                        }
                        $this$update$iv = MainViewModel.this._uiState;
                        while (true) {
                            prevValue$iv = $this$update$iv.getValue();
                            state = (UiState) prevValue$iv;
                            if (list.isEmpty()) {
                                uiStateCopy$default = UiState.copy$default(state, null, null, null, null, null, null, null, null, false, null, null, null, null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), null, null, warning, false, false, false, false, null, null, null, null, 66953215, null);
                                $result5 = $result3;
                            } else {
                                List $this$firstOrNull$iv6 = list;
                                it = $this$firstOrNull$iv6.iterator();
                                do {
                                    if (it.hasNext()) {
                                        element$iv = it.next();
                                        it5 = (ModelChoice) element$iv;
                                        if (Intrinsics.areEqual(it5.getModel(), primary2.getModel())) {
                                            z2 = false;
                                        } else {
                                            z2 = false;
                                        }
                                    } else {
                                        element$iv = null;
                                    }
                                    currentChoice = (ModelChoice) element$iv;
                                    List $this$firstOrNull$iv7 = list;
                                    it2 = $this$firstOrNull$iv7.iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            element$iv2 = it2.next();
                                            choice = (ModelChoice) element$iv2;
                                            $result5 = $result3;
                                            if (Intrinsics.areEqual(choice.getModel(), state.getSelectedModel())) {
                                                z = false;
                                            } else {
                                                z = false;
                                            }
                                            if (z) {
                                                $result3 = $result5;
                                            }
                                        } else {
                                            $result5 = $result3;
                                            element$iv2 = null;
                                        }
                                    }
                                    retained = (ModelChoice) element$iv2;
                                    if (currentChoice == null) {
                                        if (retained == null) {
                                            currentChoice = retained;
                                        } else {
                                            List $this$firstOrNull$iv8 = list;
                                            it3 = $this$firstOrNull$iv8.iterator();
                                            do {
                                                if (it3.hasNext()) {
                                                    element$iv3 = it3.next();
                                                    it4 = (ModelChoice) element$iv3;
                                                } else {
                                                    element$iv3 = null;
                                                }
                                                currentChoice = (ModelChoice) element$iv3;
                                                if (currentChoice == null) {
                                                    Object objFirst5 = CollectionsKt.first((List<? extends Object>) list);
                                                    Intrinsics.checkNotNullExpressionValue(objFirst5, "first(...)");
                                                    currentChoice = (ModelChoice) objFirst5;
                                                }
                                            } while (!Intrinsics.areEqual(it4.getRole(), "Primary"));
                                            currentChoice = (ModelChoice) element$iv3;
                                            if (currentChoice == null) {
                                                Object objFirst6 = CollectionsKt.first((List<? extends Object>) list);
                                                Intrinsics.checkNotNullExpressionValue(objFirst6, "first(...)");
                                                currentChoice = (ModelChoice) objFirst6;
                                            }
                                        }
                                    }
                                    ModelChoice retained4 = currentChoice;
                                    List $this$map$iv3 = list;
                                    destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
                                    java.util.Iterator<Object> r13U23 = $this$map$iv3.iterator();
                                    while (r13U23.hasNext()) {
    Object item$iv$iv = r13U23.next();

                                        ModelChoice it8 = (ModelChoice) item$iv$iv;
                                        destination$iv$iv.add(it8.getModel());
                                    }
                                    uiStateCopy$default = UiState.copy$default(state, null, null, null, null, null, null, null, null, false, null, null, null, null, list, CollectionsKt.distinct((List) destination$iv$iv), retained4.getModel(), retained4.getProvider(), warning, false, false, false, false, null, null, null, null, 66854911, null);
                                } while (!z2);
                                currentChoice = (ModelChoice) element$iv;
                                List $this$firstOrNull$iv9 = list;
                                it2 = $this$firstOrNull$iv9.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        element$iv2 = it2.next();
                                        choice = (ModelChoice) element$iv2;
                                        $result5 = $result3;
                                        if (Intrinsics.areEqual(choice.getModel(), state.getSelectedModel())) {
                                            z = false;
                                        } else {
                                            z = false;
                                        }
                                        if (z) {
                                            $result3 = $result5;
                                        }
                                    } else {
                                        $result5 = $result3;
                                        element$iv2 = null;
                                    }
                                }
                                retained = (ModelChoice) element$iv2;
                                if (currentChoice == null) {
                                    if (retained == null) {
                                        currentChoice = retained;
                                    } else {
                                        List $this$firstOrNull$iv10 = list;
                                        it3 = $this$firstOrNull$iv10.iterator();
                                        do {
                                            if (it3.hasNext()) {
                                                element$iv3 = it3.next();
                                                it4 = (ModelChoice) element$iv3;
                                            } else {
                                                element$iv3 = null;
                                            }
                                            currentChoice = (ModelChoice) element$iv3;
                                            if (currentChoice == null) {
                                                Object objFirst7 = CollectionsKt.first((List<? extends Object>) list);
                                                Intrinsics.checkNotNullExpressionValue(objFirst7, "first(...)");
                                                currentChoice = (ModelChoice) objFirst7;
                                            }
                                        } while (!Intrinsics.areEqual(it4.getRole(), "Primary"));
                                        currentChoice = (ModelChoice) element$iv3;
                                        if (currentChoice == null) {
                                            Object objFirst8 = CollectionsKt.first((List<? extends Object>) list);
                                            Intrinsics.checkNotNullExpressionValue(objFirst8, "first(...)");
                                            currentChoice = (ModelChoice) objFirst8;
                                        }
                                    }
                                }
                                ModelChoice retained5 = currentChoice;
                                List $this$map$iv4 = list;
                                destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv4, 10));
                                java.util.Iterator<Object> r13U24 = $this$map$iv4.iterator();
                                while (r13U24.hasNext()) {
    Object item$iv$iv = r13U24.next();

                                    ModelChoice it9 = (ModelChoice) item$iv$iv;
                                    destination$iv$iv.add(it9.getModel());
                                }
                                uiStateCopy$default = UiState.copy$default(state, null, null, null, null, null, null, null, null, false, null, null, null, null, list, CollectionsKt.distinct((List) destination$iv$iv), retained5.getModel(), retained5.getProvider(), warning, false, false, false, false, null, null, null, null, 66854911, null);
                            }
                            if ($this$update$iv.compareAndSet(prevValue$iv, uiStateCopy$default)) {
                                Object nextValue$iv2 = Unit.INSTANCE;
                                return nextValue$iv2;
                            }
                            $result3 = $result5;
                        }
                        break;
                    case 2:
                        $result3 = $result;
                        config2 = (HermesSettingsSnapshot) this.L$0;
                        ResultKt.throwOnFailure($result3);
                        $result4 = ai.nous.aurora.compat.Compat.resultUnbox($result3);
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl($result4)) {
                            obj = null;
                        } else {
                            obj = $result4;
                        }
                        catalog = (ModelOptionsSnapshot) obj;
                        if (config2 != null) {
                            mapEmptyMap = config2.getValues();
                        } else {
                            mapEmptyMap = null;
                        }
                        if (mapEmptyMap == null) {
                            mapEmptyMap = MapsKt.emptyMap();
                        }
                        map = mapEmptyMap;
                        if (catalog != null) {
                            strInvokeSuspend$str = invokeSuspend$str(map, "model.provider");
                            if (strInvokeSuspend$str == null) {
                                strInvokeSuspend$str = "";
                            }
                            strInvokeSuspend$str2 = invokeSuspend$str(map, "model.default");
                            if (strInvokeSuspend$str2 == null) {
                                strInvokeSuspend$str2 = "";
                            }
                            modelSelection = new ModelSelection(strInvokeSuspend$str, strInvokeSuspend$str2);
                        } else {
                            strInvokeSuspend$str = invokeSuspend$str(map, "model.provider");
                            if (strInvokeSuspend$str == null) {
                                strInvokeSuspend$str = "";
                            }
                            strInvokeSuspend$str2 = invokeSuspend$str(map, "model.default");
                            if (strInvokeSuspend$str2 == null) {
                                strInvokeSuspend$str2 = "";
                            }
                            modelSelection = new ModelSelection(strInvokeSuspend$str, strInvokeSuspend$str2);
                        }
                        primary = modelSelection;
                        strInvokeSuspend$str3 = invokeSuspend$str(map, "fallback_model.provider");
                        if (strInvokeSuspend$str3 == null) {
                            strInvokeSuspend$str3 = "";
                        }
                        strInvokeSuspend$str4 = invokeSuspend$str(map, "fallback_model.model");
                        if (strInvokeSuspend$str4 == null) {
                            strInvokeSuspend$str4 = "";
                        }
                        fallback = new ModelSelection(strInvokeSuspend$str3, strInvokeSuspend$str4);
                        strInvokeSuspend$str5 = invokeSuspend$str(map, "delegation.provider");
                        if (strInvokeSuspend$str5 == null) {
                            strInvokeSuspend$str5 = "";
                        }
                        String strInvokeSuspend$str8 = invokeSuspend$str(map, "delegation.model");
                        ModelSelection delegation3 = new ModelSelection(strInvokeSuspend$str5, strInvokeSuspend$str8 != null ? strInvokeSuspend$str8 : "");
                        choices = new LinkedHashMap();
                        if (catalog == null) {
                            choices2 = choices;
                            fallback2 = fallback;
                            primary2 = primary;
                        } else {
                            Iterable $this$forEach$iv3 = ModelCatalogPolicy.INSTANCE.choices(catalog.getProviders());
                            java.util.Iterator<Object> r18U25 = $this$forEach$iv3.iterator();
                            while (r18U25.hasNext()) {
    Object element$iv4 = r18U25.next();

                                CatalogModelChoice choice4 = (CatalogModelChoice) element$iv4;
                                invokeSuspend$add$default(choices, catalog, choice4.getModel(), choice4.getProvider(), "Available", false, 32, null);
                            }
                            choices2 = choices;
                            fallback2 = fallback;
                            primary2 = primary;
                        }
                        invalidRoles = new ArrayList();
                        invokeSuspend$addConfigured(catalog, invalidRoles, choices2, primary2, "Primary");
                        invokeSuspend$addConfigured(catalog, invalidRoles, choices2, fallback2, "Fallback");
                        invokeSuspend$addConfigured(catalog, invalidRoles, choices2, delegation3, "Delegation");
                        Collection collectionValues3 = choices2.values();
                        Intrinsics.checkNotNullExpressionValue(collectionValues3, "<get-values>(...)");
                        list = CollectionsKt.toList(collectionValues3);
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl($result4)) {
                            thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result4);
                            if (thM7391exceptionOrNullimpl != null) {
                                message = "bridge error";
                            } else {
                                message = "bridge error";
                            }
                            warning = "Provider catalog unavailable: " + message;
                        } else if (invalidRoles.isEmpty()) {
                            warning = "Invalid saved provider/model pair: " + ai.nous.aurora.compat.Compat.d_CollectionsKt_joinToString_9(invalidRoles, null, null, null, 0, null, null, 63, null) + ". Choose a model from its real provider.";
                        } else {
                            warning = null;
                        }
                        $this$update$iv = MainViewModel.this._uiState;
                        while (true) {
                            prevValue$iv = $this$update$iv.getValue();
                            state = (UiState) prevValue$iv;
                            if (list.isEmpty()) {
                                uiStateCopy$default = UiState.copy$default(state, null, null, null, null, null, null, null, null, false, null, null, null, null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), null, null, warning, false, false, false, false, null, null, null, null, 66953215, null);
                                $result5 = $result3;
                            } else {
                                List $this$firstOrNull$iv11 = list;
                                it = $this$firstOrNull$iv11.iterator();
                                do {
                                    if (it.hasNext()) {
                                        element$iv = it.next();
                                        it5 = (ModelChoice) element$iv;
                                        if (Intrinsics.areEqual(it5.getModel(), primary2.getModel())) {
                                            z2 = false;
                                        } else {
                                            z2 = false;
                                        }
                                    } else {
                                        element$iv = null;
                                    }
                                    currentChoice = (ModelChoice) element$iv;
                                    List $this$firstOrNull$iv12 = list;
                                    it2 = $this$firstOrNull$iv12.iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            element$iv2 = it2.next();
                                            choice = (ModelChoice) element$iv2;
                                            $result5 = $result3;
                                            if (Intrinsics.areEqual(choice.getModel(), state.getSelectedModel())) {
                                                z = false;
                                            } else {
                                                z = false;
                                            }
                                            if (z) {
                                                $result3 = $result5;
                                            }
                                        } else {
                                            $result5 = $result3;
                                            element$iv2 = null;
                                        }
                                    }
                                    retained = (ModelChoice) element$iv2;
                                    if (currentChoice == null) {
                                        if (retained == null) {
                                            currentChoice = retained;
                                        } else {
                                            List $this$firstOrNull$iv13 = list;
                                            it3 = $this$firstOrNull$iv13.iterator();
                                            do {
                                                if (it3.hasNext()) {
                                                    element$iv3 = it3.next();
                                                    it4 = (ModelChoice) element$iv3;
                                                } else {
                                                    element$iv3 = null;
                                                }
                                                currentChoice = (ModelChoice) element$iv3;
                                                if (currentChoice == null) {
                                                    Object objFirst9 = CollectionsKt.first((List<? extends Object>) list);
                                                    Intrinsics.checkNotNullExpressionValue(objFirst9, "first(...)");
                                                    currentChoice = (ModelChoice) objFirst9;
                                                }
                                            } while (!Intrinsics.areEqual(it4.getRole(), "Primary"));
                                            currentChoice = (ModelChoice) element$iv3;
                                            if (currentChoice == null) {
                                                Object objFirst10 = CollectionsKt.first((List<? extends Object>) list);
                                                Intrinsics.checkNotNullExpressionValue(objFirst10, "first(...)");
                                                currentChoice = (ModelChoice) objFirst10;
                                            }
                                        }
                                    }
                                    ModelChoice retained6 = currentChoice;
                                    List $this$map$iv5 = list;
                                    destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv5, 10));
                                    java.util.Iterator<Object> r13U26 = $this$map$iv5.iterator();
                                    while (r13U26.hasNext()) {
    Object item$iv$iv = r13U26.next();

                                        ModelChoice it10 = (ModelChoice) item$iv$iv;
                                        destination$iv$iv.add(it10.getModel());
                                    }
                                    uiStateCopy$default = UiState.copy$default(state, null, null, null, null, null, null, null, null, false, null, null, null, null, list, CollectionsKt.distinct((List) destination$iv$iv), retained6.getModel(), retained6.getProvider(), warning, false, false, false, false, null, null, null, null, 66854911, null);
                                } while (!z2);
                                currentChoice = (ModelChoice) element$iv;
                                List $this$firstOrNull$iv14 = list;
                                it2 = $this$firstOrNull$iv14.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        element$iv2 = it2.next();
                                        choice = (ModelChoice) element$iv2;
                                        $result5 = $result3;
                                        if (Intrinsics.areEqual(choice.getModel(), state.getSelectedModel())) {
                                            z = false;
                                        } else {
                                            z = false;
                                        }
                                        if (z) {
                                            $result3 = $result5;
                                        }
                                    } else {
                                        $result5 = $result3;
                                        element$iv2 = null;
                                    }
                                }
                                retained = (ModelChoice) element$iv2;
                                if (currentChoice == null) {
                                    if (retained == null) {
                                        currentChoice = retained;
                                    } else {
                                        List $this$firstOrNull$iv15 = list;
                                        it3 = $this$firstOrNull$iv15.iterator();
                                        do {
                                            if (it3.hasNext()) {
                                                element$iv3 = it3.next();
                                                it4 = (ModelChoice) element$iv3;
                                            } else {
                                                element$iv3 = null;
                                            }
                                            currentChoice = (ModelChoice) element$iv3;
                                            if (currentChoice == null) {
                                                Object objFirst11 = CollectionsKt.first((List<? extends Object>) list);
                                                Intrinsics.checkNotNullExpressionValue(objFirst11, "first(...)");
                                                currentChoice = (ModelChoice) objFirst11;
                                            }
                                        } while (!Intrinsics.areEqual(it4.getRole(), "Primary"));
                                        currentChoice = (ModelChoice) element$iv3;
                                        if (currentChoice == null) {
                                            Object objFirst12 = CollectionsKt.first((List<? extends Object>) list);
                                            Intrinsics.checkNotNullExpressionValue(objFirst12, "first(...)");
                                            currentChoice = (ModelChoice) objFirst12;
                                        }
                                    }
                                }
                                ModelChoice retained7 = currentChoice;
                                List $this$map$iv6 = list;
                                destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv6, 10));
                                java.util.Iterator<Object> r13U27 = $this$map$iv6.iterator();
                                while (r13U27.hasNext()) {
    Object item$iv$iv = r13U27.next();

                                    ModelChoice it11 = (ModelChoice) item$iv$iv;
                                    destination$iv$iv.add(it11.getModel());
                                }
                                uiStateCopy$default = UiState.copy$default(state, null, null, null, null, null, null, null, null, false, null, null, null, null, list, CollectionsKt.distinct((List) destination$iv$iv), retained7.getModel(), retained7.getProvider(), warning, false, false, false, false, null, null, null, null, 66854911, null);
                            }
                            if ($this$update$iv.compareAndSet(prevValue$iv, uiStateCopy$default)) {
                                Object nextValue$iv3 = Unit.INSTANCE;
                                return nextValue$iv3;
                            }
                            $result3 = $result5;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th2) {
            }
        }

        private static final String invokeSuspend$str(Map<String, ? extends JsonElement> map, String path) {
            JsonElement el = map.get(path);
            if (el == null) {
                return null;
            }
            boolean z = false;
            String content = el instanceof JsonPrimitive ? ((JsonPrimitive) el).getContent() : StringsKt.trim(el.toString(), '\"');
            String it = content;
            if (!StringsKt.isBlank(it) && !Intrinsics.areEqual(it, AbstractJsonLexerKt.NULL)) {
                z = true;
            }
            if (z) {
                return content;
            }
            return null;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:21:0x0034 A[ORIG_RETURN, RETURN] */
        private static final int invokeSuspend$rolePriority(String role) {
            switch (role) {
                case "Delegation":
                    return 2;
                case "Fallback":
                    return 1;
                case "Available":
                    return 3;
                case "Primary":
                    return 0;
                default:
                    return 4;
            }
        }

        private static final String invokeSuspend$providerName(ModelOptionsSnapshot catalog, String provider) {
            Iterable providers;
            Object element$iv;
            String name;
            ModelProviderCatalog it;
            if (catalog != null && (providers = catalog.getProviders()) != null) {
                Iterable $this$firstOrNull$iv = providers;
                Iterator it2 = $this$firstOrNull$iv.iterator();
                do {
                    if (!it2.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it2.next();
                    it = (ModelProviderCatalog) element$iv;
                } while (!Intrinsics.areEqual(it.getSlug(), provider));
                ModelProviderCatalog modelProviderCatalog = (ModelProviderCatalog) element$iv;
                if (modelProviderCatalog != null && (name = modelProviderCatalog.getName()) != null) {
                    return name;
                }
            }
            return provider;
        }

        static /* synthetic */ void invokeSuspend$add$default(LinkedHashMap linkedHashMap, ModelOptionsSnapshot modelOptionsSnapshot, String str, String str2, String str3, boolean z, int i, Object obj) {
            boolean z2;
            if ((i & 32) == 0) {
                z2 = z;
            } else {
                z2 = true;
            }
            invokeSuspend$add(linkedHashMap, modelOptionsSnapshot, str, str2, str3, z2);
        }

        private static final void invokeSuspend$add(LinkedHashMap<String, ModelChoice> linkedHashMap, ModelOptionsSnapshot catalog, String model, String provider, String role, boolean persistable) {
            String modelId = model != null ? StringsKt.trim((CharSequence) model).toString() : null;
            if (modelId == null) {
                modelId = "";
            }
            String string = provider != null ? StringsKt.trim((CharSequence) provider).toString() : null;
            String providerId = string != null ? string : "";
            if (modelId.length() == 0) {
                return;
            }
            if (providerId.length() == 0) {
                return;
            }
            String key = providerId + "::" + modelId;
            ModelChoice existing = linkedHashMap.get(key);
            if (existing == null || invokeSuspend$rolePriority(role) < invokeSuspend$rolePriority(existing.getRole())) {
                linkedHashMap.put(key, new ModelChoice(modelId, providerId, role, invokeSuspend$providerName(catalog, providerId), persistable));
            }
        }

        private static final void invokeSuspend$addConfigured(ModelOptionsSnapshot catalog, List<String> list, LinkedHashMap<String, ModelChoice> linkedHashMap, ModelSelection selection, String role) {
            boolean valid;
            if (StringsKt.isBlank(selection.getModel()) || StringsKt.isBlank(selection.getProvider())) {
                return;
            }
            if (catalog != null) {
                valid = ModelCatalogPolicy.INSTANCE.isValid(catalog.getProviders(), selection);
            } else {
                valid = true;
            }
            if (!valid) {
                list.add(role);
            }
            invokeSuspend$add(linkedHashMap, catalog, selection.getModel(), selection.getProvider(), valid ? role : "Unavailable", valid);
        }
    }

    public final void loadModels() {
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01891(null), 3, null);
    }

    public final void setSourceFilter(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        while (true) {
            UiState value = mutableStateFlow.getValue();
            UiState it = value;
            MutableStateFlow<UiState> mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, source, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 67108799, null))) {
                return;
            } else {
                mutableStateFlow = mutableStateFlow2;
            }
        }
    }

    public final void setSearchQuery(String q) {
        Intrinsics.checkNotNullParameter(q, "q");
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        while (true) {
            UiState value = mutableStateFlow.getValue();
            UiState it = value;
            MutableStateFlow<UiState> mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, q, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 67108735, null))) {
                return;
            } else {
                mutableStateFlow = mutableStateFlow2;
            }
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$restorePreconfig$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$restorePreconfig$1", f = "MainViewModel.kt", i = {2}, l = {466, 467, 468, 492, 494}, m = "invokeSuspend", n = {"gateway"}, s = {"L$0"})
    final class C01951 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        Object L$0;
        Object L$1;
        int label;

        C01951(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01951(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01951)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:16:0x007b A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:17:0x007c  */
        /* JADX WARN: Code duplicated, block: B:20:0x0097 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:21:0x0098  */
        /* JADX WARN: Code duplicated, block: B:24:0x00a3  */
        /* JADX WARN: Code duplicated, block: B:30:0x0113  */
        /* JADX WARN: Code duplicated, block: B:35:0x0175  */
        /* JADX WARN: Code duplicated, block: B:37:0x0189 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:38:0x018a  */
        /* JADX WARN: Code duplicated, block: B:41:0x0198  */
        /* JADX WARN: Code duplicated, block: B:43:0x01ad A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:44:0x01ae  */
        /* JADX WARN: Code duplicated, block: B:51:0x021c  */
        /* JADX WARN: Code duplicated, block: B:56:0x0284 A[LOOP:2: B:26:0x00ab->B:56:0x0284, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:61:0x010a A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object $result;
            Object gatewayAddress;
            Object $result2;
            Object $result3;
            String gateway;
            Object apiKey;
            String gateway2;
            Object $result4;
            String apiKey2;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv;
            UiState it;
            Object objM41connect0E7RQCE;
            Object $result5;
            Object $result6;
            MutableStateFlow $this$update$iv2;
            Object prevValue$iv2;
            UiState it2;
            MainViewModel mainViewModel;
            SessionRepository sessionRepository;
            Object $result7;
            Object obj2;
            MainViewModel mainViewModel2;
            Throwable err;
            MutableStateFlow $this$update$iv3;
            Object prevValue$iv3;
            UiState it3;
            MutableStateFlow $this$update$iv4;
            Object prevValue$iv4;
            UiState it4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            boolean z = true;
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    $result = obj;
                    this.label = 1;
                    if (MainViewModel.this.credentialStore.restorePreconfig(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    this.label = 2;
                    gatewayAddress = MainViewModel.this.credentialStore.getGatewayAddress(this);
                    if (gatewayAddress == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result3 = gatewayAddress;
                    gateway = (String) $result3;
                    this.L$0 = gateway;
                    this.label = 3;
                    apiKey = MainViewModel.this.credentialStore.getApiKey(this);
                    if (apiKey == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    gateway2 = gateway;
                    $result4 = apiKey;
                    apiKey2 = (String) $result4;
                    if (apiKey2 == null) {
                        apiKey2 = "";
                    }
                    $this$update$iv = MainViewModel.this._uiState;
                    while (true) {
                        prevValue$iv = $this$update$iv.getValue();
                        it = (UiState) prevValue$iv;
                        if ($this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, true, null, null, null, null, null, null, null, null, null, false, false, false, false, null, it.getConnectState().copy(gateway2, "", z, null), null, null, 58719999, null))) {
                            if (StringsKt.isBlank(apiKey2)) {
                                $this$update$iv2 = MainViewModel.this._uiState;
                                do {
                                    prevValue$iv2 = $this$update$iv2.getValue();
                                    it2 = (UiState) prevValue$iv2;
                                } while (!$this$update$iv2.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, "No stored API key. Paste it once; it is never stored in the APK.", 3, null), null, null, 58719999, null)));
                                return Unit.INSTANCE;
                            }
                            this.L$0 = null;
                            this.label = 4;
                            objM41connect0E7RQCE = MainViewModel.this.gatewayClient.m41connect0E7RQCE(gateway2, apiKey2, this);
                            if (objM41connect0E7RQCE == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Object obj3 = $result2;
                            $result5 = objM41connect0E7RQCE;
                            $result6 = obj3;
                            mainViewModel = MainViewModel.this;
                            if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl($result5)) {
                                sessionRepository = mainViewModel.sessionRepository;
                                this.L$0 = $result5;
                                this.L$1 = mainViewModel;
                                this.label = 5;
                                if (sessionRepository.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $result7 = $result6;
                                obj2 = $result5;
                                $this$update$iv4 = mainViewModel._uiState;
                                do {
                                    prevValue$iv4 = $this$update$iv4.getValue();
                                    it4 = (UiState) prevValue$iv4;
                                } while (!$this$update$iv4.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Restored hermes-hostinger", ConnectUiState.copy$default(it4.getConnectState(), null, null, false, null, 11, null), null, null, 54525694, null)));
                                $result5 = obj2;
                            }
                            mainViewModel2 = MainViewModel.this;
                            err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result5);
                            if (err != null) {
                                $this$update$iv3 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv3 = $this$update$iv3.getValue();
                                    it3 = (UiState) prevValue$iv3;
                                } while (!$this$update$iv3.compareAndSet(prevValue$iv3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, err.getMessage(), 3, null), null, null, 58719998, null)));
                            }
                            return Unit.INSTANCE;
                        }
                        z = true;
                    }
                    break;
                case 1:
                    $result = obj;
                    ResultKt.throwOnFailure($result);
                    this.label = 2;
                    gatewayAddress = MainViewModel.this.credentialStore.getGatewayAddress(this);
                    if (gatewayAddress == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result3 = gatewayAddress;
                    gateway = (String) $result3;
                    this.L$0 = gateway;
                    this.label = 3;
                    apiKey = MainViewModel.this.credentialStore.getApiKey(this);
                    if (apiKey == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    gateway2 = gateway;
                    $result4 = apiKey;
                    apiKey2 = (String) $result4;
                    if (apiKey2 == null) {
                        apiKey2 = "";
                    }
                    $this$update$iv = MainViewModel.this._uiState;
                    while (true) {
                        prevValue$iv = $this$update$iv.getValue();
                        it = (UiState) prevValue$iv;
                        if ($this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, true, null, null, null, null, null, null, null, null, null, false, false, false, false, null, it.getConnectState().copy(gateway2, "", z, null), null, null, 58719999, null))) {
                            if (StringsKt.isBlank(apiKey2)) {
                                $this$update$iv2 = MainViewModel.this._uiState;
                                do {
                                    prevValue$iv2 = $this$update$iv2.getValue();
                                    it2 = (UiState) prevValue$iv2;
                                } while (!$this$update$iv2.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, "No stored API key. Paste it once; it is never stored in the APK.", 3, null), null, null, 58719999, null)));
                                return Unit.INSTANCE;
                            }
                            this.L$0 = null;
                            this.label = 4;
                            objM41connect0E7RQCE = MainViewModel.this.gatewayClient.m41connect0E7RQCE(gateway2, apiKey2, this);
                            if (objM41connect0E7RQCE == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Object obj4 = $result2;
                            $result5 = objM41connect0E7RQCE;
                            $result6 = obj4;
                            mainViewModel = MainViewModel.this;
                            if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl($result5)) {
                                sessionRepository = mainViewModel.sessionRepository;
                                this.L$0 = $result5;
                                this.L$1 = mainViewModel;
                                this.label = 5;
                                if (sessionRepository.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $result7 = $result6;
                                obj2 = $result5;
                                $this$update$iv4 = mainViewModel._uiState;
                                do {
                                    prevValue$iv4 = $this$update$iv4.getValue();
                                    it4 = (UiState) prevValue$iv4;
                                } while (!$this$update$iv4.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Restored hermes-hostinger", ConnectUiState.copy$default(it4.getConnectState(), null, null, false, null, 11, null), null, null, 54525694, null)));
                                $result5 = obj2;
                            }
                            mainViewModel2 = MainViewModel.this;
                            err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result5);
                            if (err != null) {
                                $this$update$iv3 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv3 = $this$update$iv3.getValue();
                                    it3 = (UiState) prevValue$iv3;
                                } while (!$this$update$iv3.compareAndSet(prevValue$iv3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, err.getMessage(), 3, null), null, null, 58719998, null)));
                            }
                            return Unit.INSTANCE;
                        }
                        z = true;
                    }
                    break;
                case 2:
                    $result3 = obj;
                    ResultKt.throwOnFailure($result3);
                    $result2 = $result3;
                    gateway = (String) $result3;
                    this.L$0 = gateway;
                    this.label = 3;
                    apiKey = MainViewModel.this.credentialStore.getApiKey(this);
                    if (apiKey == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    gateway2 = gateway;
                    $result4 = apiKey;
                    apiKey2 = (String) $result4;
                    if (apiKey2 == null) {
                        apiKey2 = "";
                    }
                    $this$update$iv = MainViewModel.this._uiState;
                    while (true) {
                        prevValue$iv = $this$update$iv.getValue();
                        it = (UiState) prevValue$iv;
                        if ($this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, true, null, null, null, null, null, null, null, null, null, false, false, false, false, null, it.getConnectState().copy(gateway2, "", z, null), null, null, 58719999, null))) {
                            if (StringsKt.isBlank(apiKey2)) {
                                $this$update$iv2 = MainViewModel.this._uiState;
                                do {
                                    prevValue$iv2 = $this$update$iv2.getValue();
                                    it2 = (UiState) prevValue$iv2;
                                } while (!$this$update$iv2.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, "No stored API key. Paste it once; it is never stored in the APK.", 3, null), null, null, 58719999, null)));
                                return Unit.INSTANCE;
                            }
                            this.L$0 = null;
                            this.label = 4;
                            objM41connect0E7RQCE = MainViewModel.this.gatewayClient.m41connect0E7RQCE(gateway2, apiKey2, this);
                            if (objM41connect0E7RQCE == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Object obj5 = $result2;
                            $result5 = objM41connect0E7RQCE;
                            $result6 = obj5;
                            mainViewModel = MainViewModel.this;
                            if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl($result5)) {
                                sessionRepository = mainViewModel.sessionRepository;
                                this.L$0 = $result5;
                                this.L$1 = mainViewModel;
                                this.label = 5;
                                if (sessionRepository.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $result7 = $result6;
                                obj2 = $result5;
                                $this$update$iv4 = mainViewModel._uiState;
                                do {
                                    prevValue$iv4 = $this$update$iv4.getValue();
                                    it4 = (UiState) prevValue$iv4;
                                } while (!$this$update$iv4.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Restored hermes-hostinger", ConnectUiState.copy$default(it4.getConnectState(), null, null, false, null, 11, null), null, null, 54525694, null)));
                                $result5 = obj2;
                            }
                            mainViewModel2 = MainViewModel.this;
                            err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result5);
                            if (err != null) {
                                $this$update$iv3 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv3 = $this$update$iv3.getValue();
                                    it3 = (UiState) prevValue$iv3;
                                } while (!$this$update$iv3.compareAndSet(prevValue$iv3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, err.getMessage(), 3, null), null, null, 58719998, null)));
                            }
                            return Unit.INSTANCE;
                        }
                        z = true;
                    }
                    break;
                case 3:
                    $result4 = obj;
                    String gateway3 = (String) this.L$0;
                    ResultKt.throwOnFailure($result4);
                    gateway2 = gateway3;
                    $result2 = $result4;
                    apiKey2 = (String) $result4;
                    if (apiKey2 == null) {
                        apiKey2 = "";
                    }
                    $this$update$iv = MainViewModel.this._uiState;
                    while (true) {
                        prevValue$iv = $this$update$iv.getValue();
                        it = (UiState) prevValue$iv;
                        if ($this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, true, null, null, null, null, null, null, null, null, null, false, false, false, false, null, it.getConnectState().copy(gateway2, "", z, null), null, null, 58719999, null))) {
                            if (StringsKt.isBlank(apiKey2)) {
                                $this$update$iv2 = MainViewModel.this._uiState;
                                do {
                                    prevValue$iv2 = $this$update$iv2.getValue();
                                    it2 = (UiState) prevValue$iv2;
                                } while (!$this$update$iv2.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, false, "No stored API key. Paste it once; it is never stored in the APK.", 3, null), null, null, 58719999, null)));
                                return Unit.INSTANCE;
                            }
                            this.L$0 = null;
                            this.label = 4;
                            objM41connect0E7RQCE = MainViewModel.this.gatewayClient.m41connect0E7RQCE(gateway2, apiKey2, this);
                            if (objM41connect0E7RQCE == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Object obj6 = $result2;
                            $result5 = objM41connect0E7RQCE;
                            $result6 = obj6;
                            mainViewModel = MainViewModel.this;
                            if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl($result5)) {
                                sessionRepository = mainViewModel.sessionRepository;
                                this.L$0 = $result5;
                                this.L$1 = mainViewModel;
                                this.label = 5;
                                if (sessionRepository.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $result7 = $result6;
                                obj2 = $result5;
                                $this$update$iv4 = mainViewModel._uiState;
                                do {
                                    prevValue$iv4 = $this$update$iv4.getValue();
                                    it4 = (UiState) prevValue$iv4;
                                } while (!$this$update$iv4.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Restored hermes-hostinger", ConnectUiState.copy$default(it4.getConnectState(), null, null, false, null, 11, null), null, null, 54525694, null)));
                                $result5 = obj2;
                            }
                            mainViewModel2 = MainViewModel.this;
                            err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result5);
                            if (err != null) {
                                $this$update$iv3 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv3 = $this$update$iv3.getValue();
                                    it3 = (UiState) prevValue$iv3;
                                } while (!$this$update$iv3.compareAndSet(prevValue$iv3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, err.getMessage(), 3, null), null, null, 58719998, null)));
                            }
                            return Unit.INSTANCE;
                        }
                        z = true;
                    }
                    break;
                case 4:
                    $result6 = obj;
                    ResultKt.throwOnFailure($result6);
                    $result5 = ai.nous.aurora.compat.Compat.resultUnbox($result6);
                    mainViewModel = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl($result5)) {
                        sessionRepository = mainViewModel.sessionRepository;
                        this.L$0 = $result5;
                        this.L$1 = mainViewModel;
                        this.label = 5;
                        if (sessionRepository.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result7 = $result6;
                        obj2 = $result5;
                        $this$update$iv4 = mainViewModel._uiState;
                        do {
                            prevValue$iv4 = $this$update$iv4.getValue();
                            it4 = (UiState) prevValue$iv4;
                        } while (!$this$update$iv4.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Restored hermes-hostinger", ConnectUiState.copy$default(it4.getConnectState(), null, null, false, null, 11, null), null, null, 54525694, null)));
                        $result5 = obj2;
                    }
                    mainViewModel2 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result5);
                    if (err != null) {
                        $this$update$iv3 = mainViewModel2._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv3.getValue();
                            it3 = (UiState) prevValue$iv3;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, err.getMessage(), 3, null), null, null, 58719998, null)));
                    }
                    return Unit.INSTANCE;
                case 5:
                    $result7 = obj;
                    mainViewModel = (MainViewModel) this.L$1;
                    Object obj7 = this.L$0;
                    ResultKt.throwOnFailure($result7);
                    ai.nous.aurora.compat.Compat.resultUnbox($result7);
                    obj2 = obj7;
                    $this$update$iv4 = mainViewModel._uiState;
                    do {
                        prevValue$iv4 = $this$update$iv4.getValue();
                        it4 = (UiState) prevValue$iv4;
                    } while (!$this$update$iv4.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Restored hermes-hostinger", ConnectUiState.copy$default(it4.getConnectState(), null, null, false, null, 11, null), null, null, 54525694, null)));
                    $result5 = obj2;
                    mainViewModel2 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl($result5);
                    if (err != null) {
                        $this$update$iv3 = mainViewModel2._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv3.getValue();
                            it3 = (UiState) prevValue$iv3;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv3, UiState.copy$default(it3, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it3.getConnectState(), null, null, false, err.getMessage(), 3, null), null, null, 58719998, null)));
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void restorePreconfig() {
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01951(null), 3, null);
    }

    public final void onDisplayMode(AuroraDisplayMode mode) {
        UiState uiState;
        UiState next;
        Intrinsics.checkNotNullParameter(mode, "mode");
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            UiState value = mutableStateFlow.getValue();
            UiState st = value;
            if (WhenMappings.$EnumSwitchMapping$0[mode.ordinal()] == 1) {
                next = UiState.copy$default(st, null, null, null, AuroraDisplayMode.RAZR_COVER, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 67108855, null);
                uiState = value;
            } else {
                this.wantInnerAfterUnfold = false;
                String pending = this.pendingSessionAfterUnfold;
                this.pendingSessionAfterUnfold = null;
                uiState = value;
                next = UiState.copy$default(st, null, null, null, mode, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 67108855, null);
                if (pending != null) {
                    ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$onDisplayMode$1$1(this, pending, null), 3, null);
                }
            }
        } while (!mutableStateFlow.compareAndSet(uiState, next));
    }

    public final void requestInnerFromCover() {
        this.wantInnerAfterUnfold = true;
        flashStatus("Unfold for full Aurora");
    }

    public final void openLatestSession() {
        AuroraSession latest = CoverShellPolicy.INSTANCE.latestSession(this._uiState.getValue().getSessions());
        if (latest == null) {
            return;
        }
        if (this._uiState.getValue().getDisplayMode() != AuroraDisplayMode.RAZR_COVER) {
            if (!CoverOpenPolicy.INSTANCE.shouldDeferFullChromeUntilUnfold(WhenMappings.$EnumSwitchMapping$0[this._uiState.getValue().getDisplayMode().ordinal()] == 1 ? CoverOpenPolicy.DisplayMode.Cover : CoverOpenPolicy.DisplayMode.Full)) {
                openSession(latest.getId());
                return;
            }
        }
        this.pendingSessionAfterUnfold = latest.getId();
        this.wantInnerAfterUnfold = true;
        flashStatus("Unfold for full chat");
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$connectToHermes$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$connectToHermes$1", f = "MainViewModel.kt", i = {0, 0, 1, 1, 2, 3, 3}, l = {583, 585, 586, 599, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT, 602, 603, 604}, m = "invokeSuspend", n = {"gateway", "typed", "gateway", "typed", "gateway", "gateway", "key"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$0", "L$1"})
    final class C01831 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        Object L$0;
        Object L$1;
        int label;

        C01831(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01831(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01831)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:24:0x0118 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:25:0x0119  */
        /* JADX WARN: Code duplicated, block: B:28:0x0126  */
        /* JADX WARN: Code duplicated, block: B:30:0x0138 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:31:0x0139  */
        /* JADX WARN: Code duplicated, block: B:34:0x0144  */
        /* JADX WARN: Code duplicated, block: B:38:0x0155  */
        /* JADX WARN: Code duplicated, block: B:49:0x01ec A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:50:0x01ed  */
        /* JADX WARN: Code duplicated, block: B:53:0x01f7  */
        /* JADX WARN: Code duplicated, block: B:55:0x020c A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:58:0x021e A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:61:0x0231 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:62:0x0232  */
        /* JADX WARN: Code duplicated, block: B:69:0x02a8  */
        /* JADX WARN: Code duplicated, block: B:72:0x02c6  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object $result;
            String gateway;
            String gateway2;
            String typed;
            String gateway3;
            String gateway4;
            MainViewModel mainViewModel;
            Object apiKey;
            Object $result2;
            Object $result3;
            String gateway5;
            String key;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv;
            UiState it;
            Object prevValue$iv2;
            UiState it2;
            String str;
            Object objM41connect0E7RQCE;
            Object value;
            MainViewModel mainViewModel2;
            SessionRepository sessionRepository;
            SessionRepository sessionRepository2;
            SessionRepository sessionRepository3;
            Object $result4;
            Object obj2;
            MainViewModel mainViewModel3;
            Throwable err;
            MutableStateFlow $this$update$iv2;
            Object prevValue$iv3;
            UiState it3;
            ConnectUiState connectState;
            String message;
            MutableStateFlow $this$update$iv3;
            Object prevValue$iv4;
            UiState it4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    $result = obj;
                    String gateway6 = StringsKt.trimEnd(StringsKt.trim((CharSequence) ((UiState) MainViewModel.this._uiState.getValue()).getConnectState().getGatewayAddress()).toString(), '/');
                    String typed2 = StringsKt.trim((CharSequence) ((UiState) MainViewModel.this._uiState.getValue()).getConnectState().getApiKey()).toString();
                    if (StringsKt.isBlank(typed2)) {
                        gateway = gateway6;
                        gateway2 = typed2;
                    } else {
                        this.L$0 = gateway6;
                        this.L$1 = typed2;
                        this.label = 1;
                        if (MainViewModel.this.credentialStore.setApiKey(typed2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        gateway = gateway6;
                        gateway2 = typed2;
                    }
                    this.L$0 = gateway;
                    this.L$1 = gateway2;
                    this.label = 2;
                    if (MainViewModel.this.credentialStore.setAutoConnectOptOut(false, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    typed = gateway2;
                    gateway3 = gateway;
                    gateway4 = typed;
                    mainViewModel = MainViewModel.this;
                    if (StringsKt.isBlank(gateway4)) {
                        AuroraCredentialStore auroraCredentialStore = mainViewModel.credentialStore;
                        this.L$0 = gateway3;
                        this.L$1 = null;
                        this.label = 3;
                        apiKey = auroraCredentialStore.getApiKey(this);
                        if (apiKey == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                        $result3 = apiKey;
                        gateway5 = gateway3;
                        str = (String) $result3;
                        if (str == null) {
                            str = "";
                        }
                        gateway3 = gateway5;
                        gateway4 = str;
                        $result = $result2;
                    }
                    key = gateway4;
                    if (!StringsKt.isBlank(gateway3) || StringsKt.isBlank(key)) {
                        $this$update$iv = MainViewModel.this._uiState;
                        do {
                            prevValue$iv = $this$update$iv.getValue();
                            it = (UiState) prevValue$iv;
                        } while (!$this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it.getConnectState(), null, null, false, "Hermes address and API key required", 7, null), null, null, 58720255, null)));
                        return Unit.INSTANCE;
                    }
                    MutableStateFlow $this$update$iv4 = MainViewModel.this._uiState;
                    do {
                        prevValue$iv2 = $this$update$iv4.getValue();
                        it2 = (UiState) prevValue$iv2;
                    } while (!$this$update$iv4.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, true, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it2.getConnectState(), null, null, true, null, 3, null), null, null, 58719999, null)));
                    this.L$0 = gateway3;
                    this.L$1 = key;
                    this.label = 4;
                    if (MainViewModel.this.credentialStore.setGatewayAddress(gateway3, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 5;
                    objM41connect0E7RQCE = MainViewModel.this.gatewayClient.m41connect0E7RQCE(gateway3, key, this);
                    if (objM41connect0E7RQCE == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    value = objM41connect0E7RQCE;
                    mainViewModel2 = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        sessionRepository = mainViewModel2.sessionRepository;
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 6;
                        if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository2 = mainViewModel2.sessionRepository;
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 7;
                        if (sessionRepository2.recoverInterruptedMessages(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository3 = mainViewModel2.sessionRepository;
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 8;
                        if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result4 = $result;
                        obj2 = value;
                        mainViewModel2.loadModels();
                        mainViewModel2.startSessionPolling();
                        $this$update$iv3 = mainViewModel2._uiState;
                        do {
                            prevValue$iv4 = $this$update$iv3.getValue();
                            it4 = (UiState) prevValue$iv4;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected", ConnectUiState.copy$default(it4.getConnectState(), null, "", false, null, 9, null), null, null, 54525694, null)));
                        value = obj2;
                    }
                    mainViewModel3 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel3._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                            connectState = it3.getConnectState();
                            message = err.getMessage();
                            if (message == null) {
                                message = "Connect failed";
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, null, null, false, message, 3, null), null, null, 58719999, null)));
                    }
                    return Unit.INSTANCE;
                case 1:
                    $result = obj;
                    gateway2 = (String) this.L$1;
                    gateway = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    this.L$0 = gateway;
                    this.L$1 = gateway2;
                    this.label = 2;
                    if (MainViewModel.this.credentialStore.setAutoConnectOptOut(false, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    typed = gateway2;
                    gateway3 = gateway;
                    gateway4 = typed;
                    mainViewModel = MainViewModel.this;
                    if (StringsKt.isBlank(gateway4)) {
                        AuroraCredentialStore auroraCredentialStore2 = mainViewModel.credentialStore;
                        this.L$0 = gateway3;
                        this.L$1 = null;
                        this.label = 3;
                        apiKey = auroraCredentialStore2.getApiKey(this);
                        if (apiKey == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                        $result3 = apiKey;
                        gateway5 = gateway3;
                        str = (String) $result3;
                        if (str == null) {
                            str = "";
                        }
                        gateway3 = gateway5;
                        gateway4 = str;
                        $result = $result2;
                    }
                    key = gateway4;
                    if (StringsKt.isBlank(gateway3)) {
                        break;
                    }
                    $this$update$iv = MainViewModel.this._uiState;
                    do {
                        prevValue$iv = $this$update$iv.getValue();
                        it = (UiState) prevValue$iv;
                    } while (!$this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it.getConnectState(), null, null, false, "Hermes address and API key required", 7, null), null, null, 58720255, null)));
                    return Unit.INSTANCE;
                case 2:
                    $result = obj;
                    typed = (String) this.L$1;
                    gateway3 = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    gateway4 = typed;
                    mainViewModel = MainViewModel.this;
                    if (StringsKt.isBlank(gateway4)) {
                        AuroraCredentialStore auroraCredentialStore3 = mainViewModel.credentialStore;
                        this.L$0 = gateway3;
                        this.L$1 = null;
                        this.label = 3;
                        apiKey = auroraCredentialStore3.getApiKey(this);
                        if (apiKey == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                        $result3 = apiKey;
                        gateway5 = gateway3;
                        str = (String) $result3;
                        if (str == null) {
                            str = "";
                        }
                        gateway3 = gateway5;
                        gateway4 = str;
                        $result = $result2;
                    }
                    key = gateway4;
                    if (StringsKt.isBlank(gateway3)) {
                        break;
                    }
                    $this$update$iv = MainViewModel.this._uiState;
                    do {
                        prevValue$iv = $this$update$iv.getValue();
                        it = (UiState) prevValue$iv;
                    } while (!$this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it.getConnectState(), null, null, false, "Hermes address and API key required", 7, null), null, null, 58720255, null)));
                    return Unit.INSTANCE;
                case 3:
                    $result3 = obj;
                    String gateway7 = (String) this.L$0;
                    ResultKt.throwOnFailure($result3);
                    gateway5 = gateway7;
                    $result2 = $result3;
                    str = (String) $result3;
                    if (str == null) {
                        str = "";
                    }
                    gateway3 = gateway5;
                    gateway4 = str;
                    $result = $result2;
                    key = gateway4;
                    if (StringsKt.isBlank(gateway3)) {
                        break;
                    }
                    $this$update$iv = MainViewModel.this._uiState;
                    do {
                        prevValue$iv = $this$update$iv.getValue();
                        it = (UiState) prevValue$iv;
                    } while (!$this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it.getConnectState(), null, null, false, "Hermes address and API key required", 7, null), null, null, 58720255, null)));
                    return Unit.INSTANCE;
                case 4:
                    $result = obj;
                    key = (String) this.L$1;
                    gateway3 = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 5;
                    objM41connect0E7RQCE = MainViewModel.this.gatewayClient.m41connect0E7RQCE(gateway3, key, this);
                    if (objM41connect0E7RQCE == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    value = objM41connect0E7RQCE;
                    mainViewModel2 = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        sessionRepository = mainViewModel2.sessionRepository;
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 6;
                        if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository2 = mainViewModel2.sessionRepository;
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 7;
                        if (sessionRepository2.recoverInterruptedMessages(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository3 = mainViewModel2.sessionRepository;
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 8;
                        if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result4 = $result;
                        obj2 = value;
                        mainViewModel2.loadModels();
                        mainViewModel2.startSessionPolling();
                        $this$update$iv3 = mainViewModel2._uiState;
                        do {
                            prevValue$iv4 = $this$update$iv3.getValue();
                            it4 = (UiState) prevValue$iv4;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected", ConnectUiState.copy$default(it4.getConnectState(), null, "", false, null, 9, null), null, null, 54525694, null)));
                        value = obj2;
                    }
                    mainViewModel3 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel3._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                            connectState = it3.getConnectState();
                            message = err.getMessage();
                            if (message == null) {
                                message = "Connect failed";
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, null, null, false, message, 3, null), null, null, 58719999, null)));
                    }
                    return Unit.INSTANCE;
                case 5:
                    $result = obj;
                    ResultKt.throwOnFailure($result);
                    value = ai.nous.aurora.compat.Compat.resultUnbox($result);
                    mainViewModel2 = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        sessionRepository = mainViewModel2.sessionRepository;
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 6;
                        if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository2 = mainViewModel2.sessionRepository;
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 7;
                        if (sessionRepository2.recoverInterruptedMessages(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository3 = mainViewModel2.sessionRepository;
                        this.L$0 = value;
                        this.L$1 = mainViewModel2;
                        this.label = 8;
                        if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result4 = $result;
                        obj2 = value;
                        mainViewModel2.loadModels();
                        mainViewModel2.startSessionPolling();
                        $this$update$iv3 = mainViewModel2._uiState;
                        do {
                            prevValue$iv4 = $this$update$iv3.getValue();
                            it4 = (UiState) prevValue$iv4;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected", ConnectUiState.copy$default(it4.getConnectState(), null, "", false, null, 9, null), null, null, 54525694, null)));
                        value = obj2;
                    }
                    mainViewModel3 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel3._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                            connectState = it3.getConnectState();
                            message = err.getMessage();
                            if (message == null) {
                                message = "Connect failed";
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, null, null, false, message, 3, null), null, null, 58719999, null)));
                    }
                    return Unit.INSTANCE;
                case 6:
                    $result = obj;
                    mainViewModel2 = (MainViewModel) this.L$1;
                    value = this.L$0;
                    ResultKt.throwOnFailure($result);
                    sessionRepository2 = mainViewModel2.sessionRepository;
                    this.L$0 = value;
                    this.L$1 = mainViewModel2;
                    this.label = 7;
                    if (sessionRepository2.recoverInterruptedMessages(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionRepository3 = mainViewModel2.sessionRepository;
                    this.L$0 = value;
                    this.L$1 = mainViewModel2;
                    this.label = 8;
                    if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result4 = $result;
                    obj2 = value;
                    mainViewModel2.loadModels();
                    mainViewModel2.startSessionPolling();
                    $this$update$iv3 = mainViewModel2._uiState;
                    do {
                        prevValue$iv4 = $this$update$iv3.getValue();
                        it4 = (UiState) prevValue$iv4;
                    } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected", ConnectUiState.copy$default(it4.getConnectState(), null, "", false, null, 9, null), null, null, 54525694, null)));
                    value = obj2;
                    mainViewModel3 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel3._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                            connectState = it3.getConnectState();
                            message = err.getMessage();
                            if (message == null) {
                                message = "Connect failed";
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, null, null, false, message, 3, null), null, null, 58719999, null)));
                    }
                    return Unit.INSTANCE;
                case 7:
                    $result = obj;
                    mainViewModel2 = (MainViewModel) this.L$1;
                    value = this.L$0;
                    ResultKt.throwOnFailure($result);
                    sessionRepository3 = mainViewModel2.sessionRepository;
                    this.L$0 = value;
                    this.L$1 = mainViewModel2;
                    this.label = 8;
                    if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result4 = $result;
                    obj2 = value;
                    mainViewModel2.loadModels();
                    mainViewModel2.startSessionPolling();
                    $this$update$iv3 = mainViewModel2._uiState;
                    do {
                        prevValue$iv4 = $this$update$iv3.getValue();
                        it4 = (UiState) prevValue$iv4;
                    } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected", ConnectUiState.copy$default(it4.getConnectState(), null, "", false, null, 9, null), null, null, 54525694, null)));
                    value = obj2;
                    mainViewModel3 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel3._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                            connectState = it3.getConnectState();
                            message = err.getMessage();
                            if (message == null) {
                                message = "Connect failed";
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, null, null, false, message, 3, null), null, null, 58719999, null)));
                    }
                    return Unit.INSTANCE;
                case 8:
                    $result4 = obj;
                    MainViewModel mainViewModel4 = (MainViewModel) this.L$1;
                    Object obj3 = this.L$0;
                    ResultKt.throwOnFailure($result4);
                    ai.nous.aurora.compat.Compat.resultUnbox($result4);
                    obj2 = obj3;
                    mainViewModel2 = mainViewModel4;
                    mainViewModel2.loadModels();
                    mainViewModel2.startSessionPolling();
                    $this$update$iv3 = mainViewModel2._uiState;
                    do {
                        prevValue$iv4 = $this$update$iv3.getValue();
                        it4 = (UiState) prevValue$iv4;
                    } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it4, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Connected", ConnectUiState.copy$default(it4.getConnectState(), null, "", false, null, 9, null), null, null, 54525694, null)));
                    value = obj2;
                    mainViewModel3 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel3._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                            connectState = it3.getConnectState();
                            message = err.getMessage();
                            if (message == null) {
                                message = "Connect failed";
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(connectState, null, null, false, message, 3, null), null, null, 58719999, null)));
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void connectToHermes() {
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01831(null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$openSession$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$openSession$1", f = "MainViewModel.kt", i = {}, l = {668, 674}, m = "invokeSuspend", n = {}, s = {})
    final class C01921 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $sessionId;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01921(String str, Continuation continuation) {
            super(2, continuation);
            this.$sessionId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01921(this.$sessionId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01921)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0066  */
        /* JADX WARN: Code duplicated, block: B:18:0x006b  */
        /* JADX WARN: Code duplicated, block: B:20:0x006e  */
        /* JADX WARN: Code duplicated, block: B:23:0x008a  */
        /* JADX WARN: Code duplicated, block: B:24:0x008f  */
        /* JADX WARN: Code duplicated, block: B:28:0x00b0  */
        /* JADX WARN: Code duplicated, block: B:34:0x00c8  */
        /* JADX WARN: Code duplicated, block: B:35:0x00cd  */
        /* JADX WARN: Code duplicated, block: B:41:0x014b  */
        /* JADX WARN: Code duplicated, block: B:44:0x0182 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:47:0x018c  */
        /* JADX WARN: Code duplicated, block: B:53:0x01ea  */
        /* JADX WARN: Code duplicated, block: B:58:0x024b  */
        /* JADX WARN: Code duplicated, block: B:61:0x025b A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:62:0x025c  */
        /* JADX WARN: Code duplicated, block: B:73:0x00c3 A[SYNTHETIC] */
        /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r3v25 'element$iv'  java.lang.Object, still in use, count: 2, list:
              (r3v25 'element$iv' java.lang.Object) from 0x0062: PHI (r3 I:??) = (r3v7 'element$iv' java.lang.Object), (r3v25 'element$iv' java.lang.Object) binds: [B:14:0x0061, B:71:0x0062] A[DONT_GENERATE, DONT_INLINE]
              (r3v25 'element$iv' java.lang.Object A[D('element$iv' java.lang.Object)]) from 0x0053: CHECK_CAST (ai.nous.aurora.domain.model.AuroraSession) (r3v25 'element$iv' java.lang.Object A[D('element$iv' java.lang.Object)])
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
            	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
            	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:132)
            	at jadx.core.dex.visitors.regions.TernaryMod.processRegion(TernaryMod.java:67)
            	at jadx.core.dex.visitors.regions.TernaryMod.enterRegion(TernaryMod.java:50)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:96)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
            	at jadx.core.dex.visitors.regions.TernaryMod.process(TernaryMod.java:36)
            	at jadx.core.dex.visitors.regions.IfRegionVisitor.process(IfRegionVisitor.java:44)
            	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:30)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r42) {
            /*
                Method dump skipped, instruction units count: 622
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.C01921.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$openSession$1$3, reason: invalid class name */
        /* JADX INFO: compiled from: MainViewModel.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "ai.nous.aurora.MainViewModel$openSession$1$3", f = "MainViewModel.kt", i = {}, l = {651}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
            final /* synthetic */ String $sessionId;
            int label;
            final /* synthetic */ MainViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(MainViewModel mainViewModel, String str, Continuation continuation) {
                super(2, continuation);
                this.this$0 = mainViewModel;
                this.$sessionId = str;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.this$0, this.$sessionId, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((AnonymousClass3)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        Flow<List<AuroraMessage>> flowObserveMessages = this.this$0.sessionRepository.observeMessages(this.$sessionId);
                        final MainViewModel mainViewModel = this.this$0;
                        this.label = 1;
                        if (flowObserveMessages.collect(new FlowCollector() { // from class: ai.nous.aurora.MainViewModel.openSession.1.3.1
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                                return emit((List<AuroraMessage>) value, (Continuation) $completion);
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            public final Object emit(List<AuroraMessage> list, Continuation continuation) {
                                Object prevValue$iv;
                                UiState it;
                                boolean z;
                                Object element$iv;
                                AuroraMessage it2;
                                Iterable messages = ((UiState) mainViewModel._uiState.getValue()).getMessages();
                                MutableStateFlow mutableStateFlow = mainViewModel._uiState;
                                do {
                                    prevValue$iv = mutableStateFlow.getValue();
                                    it = (UiState) prevValue$iv;
                                } while (!mutableStateFlow.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, list, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 67106815, null)));
                                ListIterator<AuroraMessage> listIterator = list.listIterator(list.size());
                                do {
                                    z = true;
                                    if (listIterator.hasPrevious()) {
                                        element$iv = listIterator.previous();
                                        it2 = (AuroraMessage) element$iv;
                                    } else {
                                        element$iv = null;
                                        break;
                                    }
                                } while (!StringsKt.equals(it2.getRole(), "assistant", true));
                                AuroraMessage newest = (AuroraMessage) element$iv;
                                if (newest != null && newest.getId() != null && !Intrinsics.areEqual(newest.getId(), mainViewModel.lastNotifiedAssistantId)) {
                                    Iterable $this$none$iv = messages;
                                    if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
                                        for (Object element$iv2 : $this$none$iv) {
                                            AuroraMessage it3 = (AuroraMessage) element$iv2;
                                            if (Intrinsics.areEqual(it3.getId(), newest.getId())) {
                                                z = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (z && !mainViewModel.appInForeground) {
                                        mainViewModel.lastNotifiedAssistantId = newest.getId();
                                        String selectedTitle = ((UiState) mainViewModel._uiState.getValue()).getSelectedTitle();
                                        if (StringsKt.isBlank(selectedTitle)) {
                                            selectedTitle = "Hermes";
                                        }
                                        String title = selectedTitle;
                                        AuroraNotificationManager auroraNotificationManager = mainViewModel.notifications;
                                        if (auroraNotificationManager != null) {
                                            AuroraNotificationManager.notifyMessage$default(auroraNotificationManager, title, newest.getContent(), 0, 4, null);
                                        }
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        }, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void openSession(String sessionId) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01921(sessionId, null), 3, null);
    }

    public final void setAppInForeground(boolean foreground) {
        this.appInForeground = foreground;
        this.gatewayClient.setHealthChecksEnabled(foreground);
        if (foreground) {
            ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C02011(null), 3, null);
            return;
        }
        Job job = this.pollJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.pollJob = null;
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$setAppInForeground$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$setAppInForeground$1", f = "MainViewModel.kt", i = {0, 1, 3, 4, 5}, l = {686, 688, 690, 696, 699, 700, 703}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$0", "L$0", "L$0"})
    final class C02011 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        C02011(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C02011 c02011 = MainViewModel.this.new C02011(continuation);
            c02011.L$0 = obj;
            return c02011;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02011)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:41:0x00c3  */
        /* JADX WARN: Code duplicated, block: B:44:0x00d7 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:45:0x00d8  */
        /* JADX WARN: Code duplicated, block: B:51:0x00ff  */
        /* JADX WARN: Code duplicated, block: B:54:0x0115 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:55:0x0116  */
        /* JADX WARN: Code duplicated, block: B:64:0x0139  */
        /* JADX WARN: Code duplicated, block: B:66:0x0145  */
        /* JADX WARN: Code duplicated, block: B:68:0x0162 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:71:0x0175  */
        /* JADX WARN: Code duplicated, block: B:74:0x018b A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:75:0x018c  */
        /* JADX WARN: Code duplicated, block: B:78:0x01a1 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:79:0x01a2  */
        /* JADX WARN: Code duplicated, block: B:86:0x01c9  */
        /* JADX WARN: Code duplicated, block: B:89:0x01e1 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:90:0x01e2  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            String sid;
            Object objMo55selectSessiongIAlus;
            Object objM7388constructorimpl;
            String sid2;
            Object objMo55selectSessiongIAlus2;
            Object objM7388constructorimpl2;
            CoroutineScope $this$launch;
            Object objMo53refreshSessionsIoAF18A;
            Object value;
            MainViewModel mainViewModel;
            SessionRepository sessionRepository;
            MainViewModel mainViewModel2;
            CoroutineScope $this$launch2;
            Object objMo53refreshSessionsIoAF18A2;
            Object value2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                try {
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            CoroutineScope $this$launch3 = (CoroutineScope) this.L$0;
                            if (((UiState) MainViewModel.this._uiState.getValue()).getMode() == UiMode.Main) {
                                this.L$0 = $this$launch3;
                                this.label = 1;
                                if (MainViewModel.this.gatewayClient.checkConnection(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$launch = $this$launch3;
                                if (MainViewModel.this.gatewayClient.getConnection().getValue() instanceof ConnectionState.Online) {
                                    MainViewModel mainViewModel3 = MainViewModel.this;
                                    Result.Companion companion = Result.Companion;
                                    SessionRepository sessionRepository2 = mainViewModel3.sessionRepository;
                                    this.L$0 = $this$launch;
                                    this.label = 2;
                                    objMo53refreshSessionsIoAF18A = sessionRepository2.mo53refreshSessionsIoAF18A(this);
                                    if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    value = objMo53refreshSessionsIoAF18A;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                                    sid = ((UiState) MainViewModel.this._uiState.getValue()).getSelectedSessionId();
                                    if (sid != null) {
                                        MainViewModel mainViewModel4 = MainViewModel.this;
                                        try {
                                            Result.Companion companion2 = Result.Companion;
                                            SessionRepository sessionRepository3 = mainViewModel4.sessionRepository;
                                            this.L$0 = null;
                                            this.label = 3;
                                            objMo55selectSessiongIAlus = sessionRepository3.mo55selectSessiongIAlus(sid, this);
                                            if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus));
                                            ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                                        } catch (Throwable th) {
                                            Result.Companion companion3 = Result.Companion;
                                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                                        }
                                    }
                                    MainViewModel.this.startSessionPolling();
                                } else if (MainViewModel.this.gatewayClient.isConfigured()) {
                                    MainViewModel.this.gatewayClient.markConnecting();
                                    this.L$0 = $this$launch;
                                    this.label = 4;
                                    if (MainViewModel.this.gatewayClient.checkConnection(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    if (MainViewModel.this.gatewayClient.getConnection().getValue() instanceof ConnectionState.Online) {
                                        mainViewModel = MainViewModel.this;
                                        Result.Companion companion4 = Result.Companion;
                                        sessionRepository = mainViewModel.sessionRepository;
                                        this.L$0 = $this$launch;
                                        this.L$1 = mainViewModel;
                                        this.label = 5;
                                        if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        CoroutineScope coroutineScope = $this$launch;
                                        mainViewModel2 = mainViewModel;
                                        $this$launch2 = coroutineScope;
                                        SessionRepository sessionRepository4 = mainViewModel2.sessionRepository;
                                        this.L$0 = $this$launch2;
                                        this.L$1 = null;
                                        this.label = 6;
                                        objMo53refreshSessionsIoAF18A2 = sessionRepository4.mo53refreshSessionsIoAF18A(this);
                                        if (objMo53refreshSessionsIoAF18A2 == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        value2 = objMo53refreshSessionsIoAF18A2;
                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value2));
                                        sid2 = ((UiState) MainViewModel.this._uiState.getValue()).getSelectedSessionId();
                                        if (sid2 != null) {
                                            MainViewModel mainViewModel5 = MainViewModel.this;
                                            try {
                                                Result.Companion companion5 = Result.Companion;
                                                SessionRepository sessionRepository5 = mainViewModel5.sessionRepository;
                                                this.L$0 = null;
                                                this.L$1 = null;
                                                this.label = 7;
                                                objMo55selectSessiongIAlus2 = sessionRepository5.mo55selectSessiongIAlus(sid2, this);
                                                if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                                ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl2);
                                            } catch (Throwable th2) {
                                                Result.Companion companion6 = Result.Companion;
                                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                            }
                                        }
                                        MainViewModel.this.startSessionPolling();
                                    }
                                }
                            }
                            return Unit.INSTANCE;
                        case 1:
                            CoroutineScope $this$launch4 = (CoroutineScope) this.L$0;
                            ResultKt.throwOnFailure($result);
                            $this$launch = $this$launch4;
                            if (MainViewModel.this.gatewayClient.getConnection().getValue() instanceof ConnectionState.Online) {
                                MainViewModel mainViewModel6 = MainViewModel.this;
                                Result.Companion companion7 = Result.Companion;
                                SessionRepository sessionRepository6 = mainViewModel6.sessionRepository;
                                this.L$0 = $this$launch;
                                this.label = 2;
                                objMo53refreshSessionsIoAF18A = sessionRepository6.mo53refreshSessionsIoAF18A(this);
                                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                value = objMo53refreshSessionsIoAF18A;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                                sid = ((UiState) MainViewModel.this._uiState.getValue()).getSelectedSessionId();
                                if (sid != null) {
                                    MainViewModel mainViewModel7 = MainViewModel.this;
                                    Result.Companion companion8 = Result.Companion;
                                    SessionRepository sessionRepository7 = mainViewModel7.sessionRepository;
                                    this.L$0 = null;
                                    this.label = 3;
                                    objMo55selectSessiongIAlus = sessionRepository7.mo55selectSessiongIAlus(sid, this);
                                    if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus));
                                    ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                                }
                                MainViewModel.this.startSessionPolling();
                            } else if (MainViewModel.this.gatewayClient.isConfigured()) {
                                MainViewModel.this.gatewayClient.markConnecting();
                                this.L$0 = $this$launch;
                                this.label = 4;
                                if (MainViewModel.this.gatewayClient.checkConnection(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                if (MainViewModel.this.gatewayClient.getConnection().getValue() instanceof ConnectionState.Online) {
                                    mainViewModel = MainViewModel.this;
                                    Result.Companion companion9 = Result.Companion;
                                    sessionRepository = mainViewModel.sessionRepository;
                                    this.L$0 = $this$launch;
                                    this.L$1 = mainViewModel;
                                    this.label = 5;
                                    if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    CoroutineScope coroutineScope2 = $this$launch;
                                    mainViewModel2 = mainViewModel;
                                    $this$launch2 = coroutineScope2;
                                    SessionRepository sessionRepository8 = mainViewModel2.sessionRepository;
                                    this.L$0 = $this$launch2;
                                    this.L$1 = null;
                                    this.label = 6;
                                    objMo53refreshSessionsIoAF18A2 = sessionRepository8.mo53refreshSessionsIoAF18A(this);
                                    if (objMo53refreshSessionsIoAF18A2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    value2 = objMo53refreshSessionsIoAF18A2;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value2));
                                    sid2 = ((UiState) MainViewModel.this._uiState.getValue()).getSelectedSessionId();
                                    if (sid2 != null) {
                                        MainViewModel mainViewModel8 = MainViewModel.this;
                                        Result.Companion companion10 = Result.Companion;
                                        SessionRepository sessionRepository9 = mainViewModel8.sessionRepository;
                                        this.L$0 = null;
                                        this.L$1 = null;
                                        this.label = 7;
                                        objMo55selectSessiongIAlus2 = sessionRepository9.mo55selectSessiongIAlus(sid2, this);
                                        if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                        ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl2);
                                    }
                                    MainViewModel.this.startSessionPolling();
                                }
                            }
                            return Unit.INSTANCE;
                        case 2:
                            ResultKt.throwOnFailure($result);
                            value = ai.nous.aurora.compat.Compat.resultUnbox($result);
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                            sid = ((UiState) MainViewModel.this._uiState.getValue()).getSelectedSessionId();
                            if (sid != null) {
                                MainViewModel mainViewModel9 = MainViewModel.this;
                                Result.Companion companion11 = Result.Companion;
                                SessionRepository sessionRepository10 = mainViewModel9.sessionRepository;
                                this.L$0 = null;
                                this.label = 3;
                                objMo55selectSessiongIAlus = sessionRepository10.mo55selectSessiongIAlus(sid, this);
                                if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus));
                                ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                            }
                            MainViewModel.this.startSessionPolling();
                            return Unit.INSTANCE;
                        case 3:
                            try {
                                ResultKt.throwOnFailure($result);
                                objMo55selectSessiongIAlus = ai.nous.aurora.compat.Compat.resultUnbox($result);
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus));
                                break;
                            } catch (Throwable th3) {
                                Result.Companion companion12 = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th3));
                            }
                            ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl);
                            MainViewModel.this.startSessionPolling();
                            return Unit.INSTANCE;
                        case 4:
                            CoroutineScope $this$launch5 = (CoroutineScope) this.L$0;
                            ResultKt.throwOnFailure($result);
                            $this$launch = $this$launch5;
                            if (MainViewModel.this.gatewayClient.getConnection().getValue() instanceof ConnectionState.Online) {
                                mainViewModel = MainViewModel.this;
                                Result.Companion companion13 = Result.Companion;
                                sessionRepository = mainViewModel.sessionRepository;
                                this.L$0 = $this$launch;
                                this.L$1 = mainViewModel;
                                this.label = 5;
                                if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                CoroutineScope coroutineScope3 = $this$launch;
                                mainViewModel2 = mainViewModel;
                                $this$launch2 = coroutineScope3;
                                SessionRepository sessionRepository11 = mainViewModel2.sessionRepository;
                                this.L$0 = $this$launch2;
                                this.L$1 = null;
                                this.label = 6;
                                objMo53refreshSessionsIoAF18A2 = sessionRepository11.mo53refreshSessionsIoAF18A(this);
                                if (objMo53refreshSessionsIoAF18A2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                value2 = objMo53refreshSessionsIoAF18A2;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value2));
                                sid2 = ((UiState) MainViewModel.this._uiState.getValue()).getSelectedSessionId();
                                if (sid2 != null) {
                                    MainViewModel mainViewModel10 = MainViewModel.this;
                                    Result.Companion companion14 = Result.Companion;
                                    SessionRepository sessionRepository12 = mainViewModel10.sessionRepository;
                                    this.L$0 = null;
                                    this.L$1 = null;
                                    this.label = 7;
                                    objMo55selectSessiongIAlus2 = sessionRepository12.mo55selectSessiongIAlus(sid2, this);
                                    if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                    ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl2);
                                }
                                MainViewModel.this.startSessionPolling();
                            }
                            return Unit.INSTANCE;
                        case 5:
                            mainViewModel2 = (MainViewModel) this.L$1;
                            $this$launch2 = (CoroutineScope) this.L$0;
                            try {
                                ResultKt.throwOnFailure($result);
                                SessionRepository sessionRepository13 = mainViewModel2.sessionRepository;
                                this.L$0 = $this$launch2;
                                this.L$1 = null;
                                this.label = 6;
                                objMo53refreshSessionsIoAF18A2 = sessionRepository13.mo53refreshSessionsIoAF18A(this);
                                if (objMo53refreshSessionsIoAF18A2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                value2 = objMo53refreshSessionsIoAF18A2;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value2));
                                sid2 = ((UiState) MainViewModel.this._uiState.getValue()).getSelectedSessionId();
                                if (sid2 != null) {
                                    MainViewModel mainViewModel11 = MainViewModel.this;
                                    Result.Companion companion15 = Result.Companion;
                                    SessionRepository sessionRepository14 = mainViewModel11.sessionRepository;
                                    this.L$0 = null;
                                    this.L$1 = null;
                                    this.label = 7;
                                    objMo55selectSessiongIAlus2 = sessionRepository14.mo55selectSessiongIAlus(sid2, this);
                                    if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                    ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl2);
                                }
                                MainViewModel.this.startSessionPolling();
                                return Unit.INSTANCE;
                            } catch (Throwable th4) {
                                Result.Companion companion16 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th4));
                            }
                            break;
                        case 6:
                            ResultKt.throwOnFailure($result);
                            value2 = ai.nous.aurora.compat.Compat.resultUnbox($result);
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value2));
                            sid2 = ((UiState) MainViewModel.this._uiState.getValue()).getSelectedSessionId();
                            if (sid2 != null) {
                                MainViewModel mainViewModel12 = MainViewModel.this;
                                Result.Companion companion17 = Result.Companion;
                                SessionRepository sessionRepository15 = mainViewModel12.sessionRepository;
                                this.L$0 = null;
                                this.L$1 = null;
                                this.label = 7;
                                objMo55selectSessiongIAlus2 = sessionRepository15.mo55selectSessiongIAlus(sid2, this);
                                if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl2);
                            }
                            MainViewModel.this.startSessionPolling();
                            return Unit.INSTANCE;
                        case 7:
                            try {
                                ResultKt.throwOnFailure($result);
                                objMo55selectSessiongIAlus2 = ai.nous.aurora.compat.Compat.resultUnbox($result);
                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                break;
                            } catch (Throwable th5) {
                                Result.Companion companion18 = Result.Companion;
                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th5));
                            }
                            ai.nous.aurora.compat.Compat.m7387boximpl(objM7388constructorimpl2);
                            MainViewModel.this.startSessionPolling();
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                } catch (Throwable th6) {
                    Result.Companion companion19 = Result.Companion;
                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th6));
                }
            } catch (Throwable th7) {
            }
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$retryConnection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$retryConnection$1", f = "MainViewModel.kt", i = {0, 1, 1, 2, 3, 4, 5}, l = {730, 732, 745, 747, 748, 749, 752, 769}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "gateway", "$this$launch", "$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$1", "L$0", "L$0", "L$0", "L$0"})
    final class C01961 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        C01961(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C01961 c01961 = MainViewModel.this.new C01961(continuation);
            c01961.L$0 = obj;
            return c01961;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01961)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:103:0x0262 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:27:0x013b  */
        /* JADX WARN: Code duplicated, block: B:30:0x0158 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:31:0x0159  */
        /* JADX WARN: Code duplicated, block: B:34:0x0163  */
        /* JADX WARN: Code duplicated, block: B:37:0x016e  */
        /* JADX WARN: Code duplicated, block: B:42:0x01d3  */
        /* JADX WARN: Code duplicated, block: B:44:0x01e9 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:45:0x01ea  */
        /* JADX WARN: Code duplicated, block: B:48:0x01f5  */
        /* JADX WARN: Code duplicated, block: B:50:0x020c A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:51:0x020d  */
        /* JADX WARN: Code duplicated, block: B:54:0x0226 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:57:0x023a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:58:0x023b  */
        /* JADX WARN: Code duplicated, block: B:61:0x0257  */
        /* JADX WARN: Code duplicated, block: B:68:0x0278 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:69:0x0279  */
        /* JADX WARN: Code duplicated, block: B:77:0x02a0  */
        /* JADX WARN: Code duplicated, block: B:82:0x030f A[PHI: r6
  0x030f: PHI (r6v36 java.lang.Object) = (r6v16 java.lang.Object), (r6v40 java.lang.Object) binds: [B:47:0x01f3, B:81:0x0308] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:84:0x0318  */
        /* JADX WARN: Code duplicated, block: B:87:0x0330  */
        /* JADX WARN: Code duplicated, block: B:88:0x0333  */
        /* JADX WARN: Code duplicated, block: B:93:0x0380  */
        /* JADX WARN: Code duplicated, block: B:96:0x0395 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:97:0x0396  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object prevValue$iv;
            Object $result2;
            Object $result3;
            CoroutineScope $this$launch;
            CharSequence charSequence;
            String gateway;
            Object apiKey;
            String gateway2;
            Object $result4;
            CoroutineScope $this$launch2;
            String key;
            Object objM41connect0E7RQCE;
            Object $result5;
            CoroutineScope $this$launch3;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv2;
            UiState it;
            MainViewModel mainViewModel;
            SessionRepository sessionRepository;
            MainViewModel mainViewModel2;
            int i;
            Object obj;
            SessionRepository sessionRepository2;
            SessionRepository sessionRepository3;
            Object $result6;
            Object obj2;
            MainViewModel mainViewModel3;
            int i2;
            String selected;
            String str;
            Object $result7;
            int i3;
            MainViewModel mainViewModel4;
            Object obj3;
            Object objMo55selectSessiongIAlus;
            Object value;
            Object obj4;
            MainViewModel mainViewModel5;
            Throwable err;
            MutableStateFlow $this$update$iv2;
            Object prevValue$iv3;
            UiState uiState;
            String message;
            String str2;
            MutableSharedFlow mutableSharedFlow;
            ToastEvent.Error error;
            String message2;
            MutableStateFlow $this$update$iv3;
            Object prevValue$iv4;
            UiState it2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            boolean z = true;
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$launch4 = (CoroutineScope) this.L$0;
                    MutableStateFlow $this$update$iv4 = MainViewModel.this._uiState;
                    do {
                        prevValue$iv = $this$update$iv4.getValue();
                    } while (!$this$update$iv4.compareAndSet(prevValue$iv, UiState.copy$default((UiState) prevValue$iv, null, null, null, null, ConnectionState.Connecting.INSTANCE, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Reconnecting to Hermes…", null, null, null, 62914543, null)));
                    MainViewModel.this.gatewayClient.markConnecting();
                    this.L$0 = $this$launch4;
                    this.label = 1;
                    Object gatewayAddress = MainViewModel.this.credentialStore.getGatewayAddress(this);
                    if (gatewayAddress == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result3 = gatewayAddress;
                    $this$launch = $this$launch4;
                    charSequence = (CharSequence) $result3;
                    if (StringsKt.isBlank(charSequence)) {
                        charSequence = "http://100.90.24.39:8642";
                    }
                    gateway = (String) charSequence;
                    this.L$0 = $this$launch;
                    this.L$1 = gateway;
                    this.label = 2;
                    apiKey = MainViewModel.this.credentialStore.getApiKey(this);
                    if (apiKey == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    CoroutineScope coroutineScope = $this$launch;
                    gateway2 = gateway;
                    $result4 = apiKey;
                    $this$launch2 = coroutineScope;
                    key = (String) $result4;
                    if (key == null) {
                        key = "";
                    }
                    if (StringsKt.isBlank(key)) {
                        $this$update$iv = MainViewModel.this._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv.getValue();
                            it = (UiState) prevValue$iv2;
                        } while (!$this$update$iv.compareAndSet(prevValue$iv2, UiState.copy$default(it, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it.getConnectState(), null, null, false, "No stored API key. Paste it once to reconnect.", 7, null), null, null, 54525950, null)));
                        return Unit.INSTANCE;
                    }
                    this.L$0 = $this$launch2;
                    this.L$1 = null;
                    this.label = 3;
                    objM41connect0E7RQCE = MainViewModel.this.gatewayClient.m41connect0E7RQCE(gateway2, key, this);
                    if (objM41connect0E7RQCE == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result5 = $result2;
                    $this$launch3 = $this$launch2;
                    mainViewModel = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objM41connect0E7RQCE)) {
                        sessionRepository = mainViewModel.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = objM41connect0E7RQCE;
                        this.L$2 = mainViewModel;
                        this.label = 4;
                        if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj5 = objM41connect0E7RQCE;
                        mainViewModel2 = mainViewModel;
                        i = 0;
                        obj = obj5;
                        sessionRepository2 = mainViewModel2.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = obj;
                        this.L$2 = mainViewModel2;
                        this.label = 5;
                        if (sessionRepository2.recoverInterruptedMessages(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository3 = mainViewModel2.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = obj;
                        this.L$2 = mainViewModel2;
                        this.label = 6;
                        if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        int i4 = i;
                        $result6 = $result5;
                        obj2 = obj;
                        mainViewModel3 = mainViewModel2;
                        i2 = i4;
                        selected = ((UiState) mainViewModel3._uiState.getValue()).getSelectedSessionId();
                        str = selected;
                        if (str != null && !StringsKt.isBlank(str)) {
                            z = false;
                        }
                        if (z) {
                            obj4 = obj2;
                        } else {
                            try {
                                Result.Companion companion = Result.Companion;
                                SessionRepository sessionRepository4 = mainViewModel3.sessionRepository;
                                this.L$0 = obj2;
                                this.L$1 = mainViewModel3;
                                this.L$2 = null;
                                this.label = 7;
                                objMo55selectSessiongIAlus = sessionRepository4.mo55selectSessiongIAlus(selected, this);
                                if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                Object obj6 = obj2;
                                value = objMo55selectSessiongIAlus;
                                $result7 = $result6;
                                i3 = i2;
                                mainViewModel4 = mainViewModel3;
                                obj3 = obj6;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                                obj4 = obj3;
                                mainViewModel3 = mainViewModel4;
                                $result6 = $result7;
                            } catch (Throwable th) {
                                $result7 = $result6;
                                i3 = i2;
                                mainViewModel4 = mainViewModel3;
                                obj3 = obj2;
                                Result.Companion companion2 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                            }
                        }
                        mainViewModel3.loadModels();
                        mainViewModel3.startSessionPolling();
                        $this$update$iv3 = mainViewModel3._uiState;
                        do {
                            prevValue$iv4 = $this$update$iv3.getValue();
                            it2 = (UiState) prevValue$iv4;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it2, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Reconnected", ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 3, null), null, null, 54525950, null)));
                        mainViewModel3.flashStatus("Reconnected");
                        objM41connect0E7RQCE = obj4;
                        mainViewModel5 = MainViewModel.this;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                        if (err != null) {
                            $this$update$iv2 = mainViewModel5._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv2.getValue();
                                uiState = (UiState) prevValue$iv3;
                                message = err.getMessage();
                                if (message == null) {
                                    str2 = "Reconnect failed";
                                } else {
                                    str2 = message;
                                }
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                            mutableSharedFlow = mainViewModel5._toasts;
                            message2 = err.getMessage();
                            if (message2 == null) {
                                message2 = "Reconnect failed";
                            }
                            error = new ToastEvent.Error(message2);
                            this.L$0 = objM41connect0E7RQCE;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 8;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        mainViewModel5 = MainViewModel.this;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                        if (err != null) {
                            $this$update$iv2 = mainViewModel5._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv2.getValue();
                                uiState = (UiState) prevValue$iv3;
                                message = err.getMessage();
                                if (message == null) {
                                    str2 = "Reconnect failed";
                                } else {
                                    str2 = message;
                                }
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                            mutableSharedFlow = mainViewModel5._toasts;
                            message2 = err.getMessage();
                            if (message2 == null) {
                                message2 = "Reconnect failed";
                            }
                            error = new ToastEvent.Error(message2);
                            this.L$0 = objM41connect0E7RQCE;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 8;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    $result3 = $result;
                    CoroutineScope $this$launch5 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result3);
                    $this$launch = $this$launch5;
                    $result2 = $result3;
                    charSequence = (CharSequence) $result3;
                    if (StringsKt.isBlank(charSequence)) {
                        charSequence = "http://100.90.24.39:8642";
                    }
                    gateway = (String) charSequence;
                    this.L$0 = $this$launch;
                    this.L$1 = gateway;
                    this.label = 2;
                    apiKey = MainViewModel.this.credentialStore.getApiKey(this);
                    if (apiKey == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    CoroutineScope coroutineScope2 = $this$launch;
                    gateway2 = gateway;
                    $result4 = apiKey;
                    $this$launch2 = coroutineScope2;
                    key = (String) $result4;
                    if (key == null) {
                        key = "";
                    }
                    if (StringsKt.isBlank(key)) {
                        $this$update$iv = MainViewModel.this._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv.getValue();
                            it = (UiState) prevValue$iv2;
                        } while (!$this$update$iv.compareAndSet(prevValue$iv2, UiState.copy$default(it, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it.getConnectState(), null, null, false, "No stored API key. Paste it once to reconnect.", 7, null), null, null, 54525950, null)));
                        return Unit.INSTANCE;
                    }
                    this.L$0 = $this$launch2;
                    this.L$1 = null;
                    this.label = 3;
                    objM41connect0E7RQCE = MainViewModel.this.gatewayClient.m41connect0E7RQCE(gateway2, key, this);
                    if (objM41connect0E7RQCE == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result5 = $result2;
                    $this$launch3 = $this$launch2;
                    mainViewModel = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objM41connect0E7RQCE)) {
                        sessionRepository = mainViewModel.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = objM41connect0E7RQCE;
                        this.L$2 = mainViewModel;
                        this.label = 4;
                        if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj7 = objM41connect0E7RQCE;
                        mainViewModel2 = mainViewModel;
                        i = 0;
                        obj = obj7;
                        sessionRepository2 = mainViewModel2.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = obj;
                        this.L$2 = mainViewModel2;
                        this.label = 5;
                        if (sessionRepository2.recoverInterruptedMessages(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository3 = mainViewModel2.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = obj;
                        this.L$2 = mainViewModel2;
                        this.label = 6;
                        if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        int i5 = i;
                        $result6 = $result5;
                        obj2 = obj;
                        mainViewModel3 = mainViewModel2;
                        i2 = i5;
                        selected = ((UiState) mainViewModel3._uiState.getValue()).getSelectedSessionId();
                        str = selected;
                        if (str != null) {
                            z = false;
                        }
                        if (z) {
                            Result.Companion companion3 = Result.Companion;
                            SessionRepository sessionRepository5 = mainViewModel3.sessionRepository;
                            this.L$0 = obj2;
                            this.L$1 = mainViewModel3;
                            this.L$2 = null;
                            this.label = 7;
                            objMo55selectSessiongIAlus = sessionRepository5.mo55selectSessiongIAlus(selected, this);
                            if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Object obj8 = obj2;
                            value = objMo55selectSessiongIAlus;
                            $result7 = $result6;
                            i3 = i2;
                            mainViewModel4 = mainViewModel3;
                            obj3 = obj8;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                            obj4 = obj3;
                            mainViewModel3 = mainViewModel4;
                            $result6 = $result7;
                        } else {
                            obj4 = obj2;
                        }
                        mainViewModel3.loadModels();
                        mainViewModel3.startSessionPolling();
                        $this$update$iv3 = mainViewModel3._uiState;
                        do {
                            prevValue$iv4 = $this$update$iv3.getValue();
                            it2 = (UiState) prevValue$iv4;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it2, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Reconnected", ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 3, null), null, null, 54525950, null)));
                        mainViewModel3.flashStatus("Reconnected");
                        objM41connect0E7RQCE = obj4;
                        mainViewModel5 = MainViewModel.this;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                        if (err != null) {
                            $this$update$iv2 = mainViewModel5._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv2.getValue();
                                uiState = (UiState) prevValue$iv3;
                                message = err.getMessage();
                                if (message == null) {
                                    str2 = "Reconnect failed";
                                } else {
                                    str2 = message;
                                }
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                            mutableSharedFlow = mainViewModel5._toasts;
                            message2 = err.getMessage();
                            if (message2 == null) {
                                message2 = "Reconnect failed";
                            }
                            error = new ToastEvent.Error(message2);
                            this.L$0 = objM41connect0E7RQCE;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 8;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        mainViewModel5 = MainViewModel.this;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                        if (err != null) {
                            $this$update$iv2 = mainViewModel5._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv2.getValue();
                                uiState = (UiState) prevValue$iv3;
                                message = err.getMessage();
                                if (message == null) {
                                    str2 = "Reconnect failed";
                                } else {
                                    str2 = message;
                                }
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                            mutableSharedFlow = mainViewModel5._toasts;
                            message2 = err.getMessage();
                            if (message2 == null) {
                                message2 = "Reconnect failed";
                            }
                            error = new ToastEvent.Error(message2);
                            this.L$0 = objM41connect0E7RQCE;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 8;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    $result4 = $result;
                    String gateway3 = (String) this.L$1;
                    CoroutineScope $this$launch6 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result4);
                    $this$launch2 = $this$launch6;
                    gateway2 = gateway3;
                    $result2 = $result4;
                    key = (String) $result4;
                    if (key == null) {
                        key = "";
                    }
                    if (StringsKt.isBlank(key)) {
                        $this$update$iv = MainViewModel.this._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv.getValue();
                            it = (UiState) prevValue$iv2;
                        } while (!$this$update$iv.compareAndSet(prevValue$iv2, UiState.copy$default(it, UiMode.Connect, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, ConnectUiState.copy$default(it.getConnectState(), null, null, false, "No stored API key. Paste it once to reconnect.", 7, null), null, null, 54525950, null)));
                        return Unit.INSTANCE;
                    }
                    this.L$0 = $this$launch2;
                    this.L$1 = null;
                    this.label = 3;
                    objM41connect0E7RQCE = MainViewModel.this.gatewayClient.m41connect0E7RQCE(gateway2, key, this);
                    if (objM41connect0E7RQCE == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result5 = $result2;
                    $this$launch3 = $this$launch2;
                    mainViewModel = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objM41connect0E7RQCE)) {
                        sessionRepository = mainViewModel.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = objM41connect0E7RQCE;
                        this.L$2 = mainViewModel;
                        this.label = 4;
                        if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj9 = objM41connect0E7RQCE;
                        mainViewModel2 = mainViewModel;
                        i = 0;
                        obj = obj9;
                        sessionRepository2 = mainViewModel2.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = obj;
                        this.L$2 = mainViewModel2;
                        this.label = 5;
                        if (sessionRepository2.recoverInterruptedMessages(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository3 = mainViewModel2.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = obj;
                        this.L$2 = mainViewModel2;
                        this.label = 6;
                        if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        int i6 = i;
                        $result6 = $result5;
                        obj2 = obj;
                        mainViewModel3 = mainViewModel2;
                        i2 = i6;
                        selected = ((UiState) mainViewModel3._uiState.getValue()).getSelectedSessionId();
                        str = selected;
                        if (str != null) {
                            z = false;
                        }
                        if (z) {
                            Result.Companion companion4 = Result.Companion;
                            SessionRepository sessionRepository6 = mainViewModel3.sessionRepository;
                            this.L$0 = obj2;
                            this.L$1 = mainViewModel3;
                            this.L$2 = null;
                            this.label = 7;
                            objMo55selectSessiongIAlus = sessionRepository6.mo55selectSessiongIAlus(selected, this);
                            if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Object obj10 = obj2;
                            value = objMo55selectSessiongIAlus;
                            $result7 = $result6;
                            i3 = i2;
                            mainViewModel4 = mainViewModel3;
                            obj3 = obj10;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                            obj4 = obj3;
                            mainViewModel3 = mainViewModel4;
                            $result6 = $result7;
                        } else {
                            obj4 = obj2;
                        }
                        mainViewModel3.loadModels();
                        mainViewModel3.startSessionPolling();
                        $this$update$iv3 = mainViewModel3._uiState;
                        do {
                            prevValue$iv4 = $this$update$iv3.getValue();
                            it2 = (UiState) prevValue$iv4;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it2, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Reconnected", ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 3, null), null, null, 54525950, null)));
                        mainViewModel3.flashStatus("Reconnected");
                        objM41connect0E7RQCE = obj4;
                        mainViewModel5 = MainViewModel.this;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                        if (err != null) {
                            $this$update$iv2 = mainViewModel5._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv2.getValue();
                                uiState = (UiState) prevValue$iv3;
                                message = err.getMessage();
                                if (message == null) {
                                    str2 = "Reconnect failed";
                                } else {
                                    str2 = message;
                                }
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                            mutableSharedFlow = mainViewModel5._toasts;
                            message2 = err.getMessage();
                            if (message2 == null) {
                                message2 = "Reconnect failed";
                            }
                            error = new ToastEvent.Error(message2);
                            this.L$0 = objM41connect0E7RQCE;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 8;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        mainViewModel5 = MainViewModel.this;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                        if (err != null) {
                            $this$update$iv2 = mainViewModel5._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv2.getValue();
                                uiState = (UiState) prevValue$iv3;
                                message = err.getMessage();
                                if (message == null) {
                                    str2 = "Reconnect failed";
                                } else {
                                    str2 = message;
                                }
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                            mutableSharedFlow = mainViewModel5._toasts;
                            message2 = err.getMessage();
                            if (message2 == null) {
                                message2 = "Reconnect failed";
                            }
                            error = new ToastEvent.Error(message2);
                            this.L$0 = objM41connect0E7RQCE;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 8;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                case 3:
                    $result5 = $result;
                    CoroutineScope $this$launch7 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result5);
                    objM41connect0E7RQCE = ai.nous.aurora.compat.Compat.resultUnbox($result5);
                    $this$launch3 = $this$launch7;
                    mainViewModel = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objM41connect0E7RQCE)) {
                        sessionRepository = mainViewModel.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = objM41connect0E7RQCE;
                        this.L$2 = mainViewModel;
                        this.label = 4;
                        if (sessionRepository.adoptLegacyOwnerlessRows(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj11 = objM41connect0E7RQCE;
                        mainViewModel2 = mainViewModel;
                        i = 0;
                        obj = obj11;
                        sessionRepository2 = mainViewModel2.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = obj;
                        this.L$2 = mainViewModel2;
                        this.label = 5;
                        if (sessionRepository2.recoverInterruptedMessages(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionRepository3 = mainViewModel2.sessionRepository;
                        this.L$0 = $this$launch3;
                        this.L$1 = obj;
                        this.L$2 = mainViewModel2;
                        this.label = 6;
                        if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        int i7 = i;
                        $result6 = $result5;
                        obj2 = obj;
                        mainViewModel3 = mainViewModel2;
                        i2 = i7;
                        selected = ((UiState) mainViewModel3._uiState.getValue()).getSelectedSessionId();
                        str = selected;
                        if (str != null) {
                            z = false;
                        }
                        if (z) {
                            Result.Companion companion5 = Result.Companion;
                            SessionRepository sessionRepository7 = mainViewModel3.sessionRepository;
                            this.L$0 = obj2;
                            this.L$1 = mainViewModel3;
                            this.L$2 = null;
                            this.label = 7;
                            objMo55selectSessiongIAlus = sessionRepository7.mo55selectSessiongIAlus(selected, this);
                            if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Object obj12 = obj2;
                            value = objMo55selectSessiongIAlus;
                            $result7 = $result6;
                            i3 = i2;
                            mainViewModel4 = mainViewModel3;
                            obj3 = obj12;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                            obj4 = obj3;
                            mainViewModel3 = mainViewModel4;
                            $result6 = $result7;
                        } else {
                            obj4 = obj2;
                        }
                        mainViewModel3.loadModels();
                        mainViewModel3.startSessionPolling();
                        $this$update$iv3 = mainViewModel3._uiState;
                        do {
                            prevValue$iv4 = $this$update$iv3.getValue();
                            it2 = (UiState) prevValue$iv4;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it2, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Reconnected", ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 3, null), null, null, 54525950, null)));
                        mainViewModel3.flashStatus("Reconnected");
                        objM41connect0E7RQCE = obj4;
                        mainViewModel5 = MainViewModel.this;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                        if (err != null) {
                            $this$update$iv2 = mainViewModel5._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv2.getValue();
                                uiState = (UiState) prevValue$iv3;
                                message = err.getMessage();
                                if (message == null) {
                                    str2 = "Reconnect failed";
                                } else {
                                    str2 = message;
                                }
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                            mutableSharedFlow = mainViewModel5._toasts;
                            message2 = err.getMessage();
                            if (message2 == null) {
                                message2 = "Reconnect failed";
                            }
                            error = new ToastEvent.Error(message2);
                            this.L$0 = objM41connect0E7RQCE;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 8;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        mainViewModel5 = MainViewModel.this;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                        if (err != null) {
                            $this$update$iv2 = mainViewModel5._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv2.getValue();
                                uiState = (UiState) prevValue$iv3;
                                message = err.getMessage();
                                if (message == null) {
                                    str2 = "Reconnect failed";
                                } else {
                                    str2 = message;
                                }
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                            mutableSharedFlow = mainViewModel5._toasts;
                            message2 = err.getMessage();
                            if (message2 == null) {
                                message2 = "Reconnect failed";
                            }
                            error = new ToastEvent.Error(message2);
                            this.L$0 = objM41connect0E7RQCE;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 8;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                case 4:
                    $result5 = $result;
                    i = 0;
                    mainViewModel2 = (MainViewModel) this.L$2;
                    obj = this.L$1;
                    $this$launch3 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result5);
                    sessionRepository2 = mainViewModel2.sessionRepository;
                    this.L$0 = $this$launch3;
                    this.L$1 = obj;
                    this.L$2 = mainViewModel2;
                    this.label = 5;
                    if (sessionRepository2.recoverInterruptedMessages(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionRepository3 = mainViewModel2.sessionRepository;
                    this.L$0 = $this$launch3;
                    this.L$1 = obj;
                    this.L$2 = mainViewModel2;
                    this.label = 6;
                    if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int i8 = i;
                    $result6 = $result5;
                    obj2 = obj;
                    mainViewModel3 = mainViewModel2;
                    i2 = i8;
                    selected = ((UiState) mainViewModel3._uiState.getValue()).getSelectedSessionId();
                    str = selected;
                    if (str != null) {
                        z = false;
                    }
                    if (z) {
                        Result.Companion companion6 = Result.Companion;
                        SessionRepository sessionRepository8 = mainViewModel3.sessionRepository;
                        this.L$0 = obj2;
                        this.L$1 = mainViewModel3;
                        this.L$2 = null;
                        this.label = 7;
                        objMo55selectSessiongIAlus = sessionRepository8.mo55selectSessiongIAlus(selected, this);
                        if (objMo55selectSessiongIAlus == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj13 = obj2;
                        value = objMo55selectSessiongIAlus;
                        $result7 = $result6;
                        i3 = i2;
                        mainViewModel4 = mainViewModel3;
                        obj3 = obj13;
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                        obj4 = obj3;
                        mainViewModel3 = mainViewModel4;
                        $result6 = $result7;
                    } else {
                        obj4 = obj2;
                    }
                    mainViewModel3.loadModels();
                    mainViewModel3.startSessionPolling();
                    $this$update$iv3 = mainViewModel3._uiState;
                    do {
                        prevValue$iv4 = $this$update$iv3.getValue();
                        it2 = (UiState) prevValue$iv4;
                    } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it2, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Reconnected", ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 3, null), null, null, 54525950, null)));
                    mainViewModel3.flashStatus("Reconnected");
                    objM41connect0E7RQCE = obj4;
                    mainViewModel5 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel5._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            uiState = (UiState) prevValue$iv3;
                            message = err.getMessage();
                            if (message == null) {
                                str2 = "Reconnect failed";
                            } else {
                                str2 = message;
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                        mutableSharedFlow = mainViewModel5._toasts;
                        message2 = err.getMessage();
                        if (message2 == null) {
                            message2 = "Reconnect failed";
                        }
                        error = new ToastEvent.Error(message2);
                        this.L$0 = objM41connect0E7RQCE;
                        this.L$1 = null;
                        this.L$2 = null;
                        this.label = 8;
                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 5:
                    $result5 = $result;
                    i = 0;
                    mainViewModel2 = (MainViewModel) this.L$2;
                    obj = this.L$1;
                    $this$launch3 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result5);
                    sessionRepository3 = mainViewModel2.sessionRepository;
                    this.L$0 = $this$launch3;
                    this.L$1 = obj;
                    this.L$2 = mainViewModel2;
                    this.label = 6;
                    if (sessionRepository3.mo53refreshSessionsIoAF18A(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int i9 = i;
                    $result6 = $result5;
                    obj2 = obj;
                    mainViewModel3 = mainViewModel2;
                    i2 = i9;
                    selected = ((UiState) mainViewModel3._uiState.getValue()).getSelectedSessionId();
                    str = selected;
                    if (str != null) {
                        z = false;
                    }
                    if (z) {
                        Result.Companion companion7 = Result.Companion;
                        SessionRepository sessionRepository9 = mainViewModel3.sessionRepository;
                        this.L$0 = obj2;
                        this.L$1 = mainViewModel3;
                        this.L$2 = null;
                        this.label = 7;
                        objMo55selectSessiongIAlus = sessionRepository9.mo55selectSessiongIAlus(selected, this);
                        if (objMo55selectSessiongIAlus == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj14 = obj2;
                        value = objMo55selectSessiongIAlus;
                        $result7 = $result6;
                        i3 = i2;
                        mainViewModel4 = mainViewModel3;
                        obj3 = obj14;
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                        obj4 = obj3;
                        mainViewModel3 = mainViewModel4;
                        $result6 = $result7;
                    } else {
                        obj4 = obj2;
                    }
                    mainViewModel3.loadModels();
                    mainViewModel3.startSessionPolling();
                    $this$update$iv3 = mainViewModel3._uiState;
                    do {
                        prevValue$iv4 = $this$update$iv3.getValue();
                        it2 = (UiState) prevValue$iv4;
                    } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it2, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Reconnected", ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 3, null), null, null, 54525950, null)));
                    mainViewModel3.flashStatus("Reconnected");
                    objM41connect0E7RQCE = obj4;
                    mainViewModel5 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel5._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            uiState = (UiState) prevValue$iv3;
                            message = err.getMessage();
                            if (message == null) {
                                str2 = "Reconnect failed";
                            } else {
                                str2 = message;
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                        mutableSharedFlow = mainViewModel5._toasts;
                        message2 = err.getMessage();
                        if (message2 == null) {
                            message2 = "Reconnect failed";
                        }
                        error = new ToastEvent.Error(message2);
                        this.L$0 = objM41connect0E7RQCE;
                        this.L$1 = null;
                        this.L$2 = null;
                        this.label = 8;
                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 6:
                    MainViewModel mainViewModel6 = (MainViewModel) this.L$2;
                    Object obj15 = this.L$1;
                    ResultKt.throwOnFailure($result);
                    ai.nous.aurora.compat.Compat.resultUnbox($result);
                    $result6 = $result;
                    obj2 = obj15;
                    mainViewModel3 = mainViewModel6;
                    i2 = 0;
                    selected = ((UiState) mainViewModel3._uiState.getValue()).getSelectedSessionId();
                    str = selected;
                    if (str != null) {
                        z = false;
                    }
                    if (z) {
                        Result.Companion companion8 = Result.Companion;
                        SessionRepository sessionRepository10 = mainViewModel3.sessionRepository;
                        this.L$0 = obj2;
                        this.L$1 = mainViewModel3;
                        this.L$2 = null;
                        this.label = 7;
                        objMo55selectSessiongIAlus = sessionRepository10.mo55selectSessiongIAlus(selected, this);
                        if (objMo55selectSessiongIAlus == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj16 = obj2;
                        value = objMo55selectSessiongIAlus;
                        $result7 = $result6;
                        i3 = i2;
                        mainViewModel4 = mainViewModel3;
                        obj3 = obj16;
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                        obj4 = obj3;
                        mainViewModel3 = mainViewModel4;
                        $result6 = $result7;
                    } else {
                        obj4 = obj2;
                    }
                    mainViewModel3.loadModels();
                    mainViewModel3.startSessionPolling();
                    $this$update$iv3 = mainViewModel3._uiState;
                    do {
                        prevValue$iv4 = $this$update$iv3.getValue();
                        it2 = (UiState) prevValue$iv4;
                    } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it2, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Reconnected", ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 3, null), null, null, 54525950, null)));
                    mainViewModel3.flashStatus("Reconnected");
                    objM41connect0E7RQCE = obj4;
                    mainViewModel5 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel5._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            uiState = (UiState) prevValue$iv3;
                            message = err.getMessage();
                            if (message == null) {
                                str2 = "Reconnect failed";
                            } else {
                                str2 = message;
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                        mutableSharedFlow = mainViewModel5._toasts;
                        message2 = err.getMessage();
                        if (message2 == null) {
                            message2 = "Reconnect failed";
                        }
                        error = new ToastEvent.Error(message2);
                        this.L$0 = objM41connect0E7RQCE;
                        this.L$1 = null;
                        this.L$2 = null;
                        this.label = 8;
                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 7:
                    $result7 = $result;
                    i3 = 0;
                    mainViewModel4 = (MainViewModel) this.L$1;
                    obj3 = this.L$0;
                    try {
                        ResultKt.throwOnFailure($result7);
                        value = ai.nous.aurora.compat.Compat.resultUnbox($result7);
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value));
                        break;
                    } catch (Throwable th2) {
                        Result.Companion companion9 = Result.Companion;
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                    }
                    obj4 = obj3;
                    mainViewModel3 = mainViewModel4;
                    $result6 = $result7;
                    mainViewModel3.loadModels();
                    mainViewModel3.startSessionPolling();
                    $this$update$iv3 = mainViewModel3._uiState;
                    do {
                        prevValue$iv4 = $this$update$iv3.getValue();
                        it2 = (UiState) prevValue$iv4;
                    } while (!$this$update$iv3.compareAndSet(prevValue$iv4, UiState.copy$default(it2, UiMode.Main, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Reconnected", ConnectUiState.copy$default(it2.getConnectState(), null, null, false, null, 3, null), null, null, 54525950, null)));
                    mainViewModel3.flashStatus("Reconnected");
                    objM41connect0E7RQCE = obj4;
                    mainViewModel5 = MainViewModel.this;
                    err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM41connect0E7RQCE);
                    if (err != null) {
                        $this$update$iv2 = mainViewModel5._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            uiState = (UiState) prevValue$iv3;
                            message = err.getMessage();
                            if (message == null) {
                                str2 = "Reconnect failed";
                            } else {
                                str2 = message;
                            }
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null)));
                        mutableSharedFlow = mainViewModel5._toasts;
                        message2 = err.getMessage();
                        if (message2 == null) {
                            message2 = "Reconnect failed";
                        }
                        error = new ToastEvent.Error(message2);
                        this.L$0 = objM41connect0E7RQCE;
                        this.L$1 = null;
                        this.L$2 = null;
                        this.label = 8;
                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 8:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void retryConnection() {
        Job job = this.reconnectJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (z) {
            return;
        }
        this.reconnectJob = ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01961(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSessionPolling() {
        Job job = this.pollJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.pollIntervalMs = SessionPollPolicy.MIN_INTERVAL_MS;
        this.pollJob = ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C02071(null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$startSessionPolling$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$startSessionPolling$1", f = "MainViewModel.kt", i = {0, 1, 1, 1, 2, 2, 2, 3, 4}, l = {785, 791, 802, 813, 814}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "hadChanges", "hadError", "$this$launch", "hadChanges", "hadError", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$0"})
    final class C02071 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        C02071(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C02071 c02071 = MainViewModel.this.new C02071(continuation);
            c02071.L$0 = obj;
            return c02071;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02071)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:126:0x0331 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:127:0x0332  */
        /* JADX WARN: Code duplicated, block: B:130:0x0342 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:131:0x0343  */
        /* JADX WARN: Code duplicated, block: B:152:0x00d7 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:153:0x00d5 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:155:? A[LOOP:0: B:20:0x0099->B:155:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:22:0x00a3  */
        /* JADX WARN: Code duplicated, block: B:24:0x00bd  */
        /* JADX WARN: Code duplicated, block: B:25:0x00bf  */
        /* JADX WARN: Code duplicated, block: B:28:0x00c6  */
        /* JADX WARN: Code duplicated, block: B:35:0x00fd A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:36:0x00fe  */
        /* JADX WARN: Code duplicated, block: B:39:0x010c A[Catch: all -> 0x01d7, TryCatch #3 {all -> 0x01d7, blocks: (B:37:0x0106, B:39:0x010c, B:40:0x012e, B:42:0x0134), top: B:141:0x0106 }] */
        /* JADX WARN: Code duplicated, block: B:42:0x0134 A[Catch: all -> 0x01d7, TRY_LEAVE, TryCatch #3 {all -> 0x01d7, blocks: (B:37:0x0106, B:39:0x010c, B:40:0x012e, B:42:0x0134), top: B:141:0x0106 }] */
        /* JADX WARN: Code duplicated, block: B:51:0x0198 A[Catch: all -> 0x01ce, TryCatch #7 {all -> 0x01ce, blocks: (B:46:0x0150, B:49:0x0183, B:51:0x0198, B:55:0x01a7, B:58:0x01b3, B:60:0x01ba, B:61:0x01be), top: B:149:0x0150 }] */
        /* JADX WARN: Code duplicated, block: B:53:0x01a4  */
        /* JADX WARN: Code duplicated, block: B:54:0x01a6  */
        /* JADX WARN: Code duplicated, block: B:57:0x01ad  */
        /* JADX WARN: Code duplicated, block: B:60:0x01ba A[Catch: all -> 0x01ce, TryCatch #7 {all -> 0x01ce, blocks: (B:46:0x0150, B:49:0x0183, B:51:0x0198, B:55:0x01a7, B:58:0x01b3, B:60:0x01ba, B:61:0x01be), top: B:149:0x0150 }] */
        /* JADX WARN: Code duplicated, block: B:71:0x01f1  */
        /* JADX WARN: Code duplicated, block: B:74:0x020a  */
        /* JADX WARN: Code duplicated, block: B:78:0x0213  */
        /* JADX WARN: Code duplicated, block: B:80:0x0216  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:131:0x0343 -> B:132:0x0345). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:36:0x00fe
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r25) {
            /*
                Method dump skipped, instruction units count: 864
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.C02071.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void backToSessionList() {
        UiState value;
        UiState it;
        Job job = this.messagesJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            it = value;
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(it, null, null, ChatPane.List, null, null, null, null, null, false, null, null, null, "", null, null, null, null, null, false, false, false, false, null, null, null, null, 62910459, null)));
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$createSession$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$createSession$1", f = "MainViewModel.kt", i = {}, l = {829, 836}, m = "invokeSuspend", n = {}, s = {})
    final class C01841 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        Object L$0;
        int label;

        C01841(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01841(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01841)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0099  */
        /* JADX WARN: Code duplicated, block: B:22:0x0100  */
        /* JADX WARN: Code duplicated, block: B:27:0x015f  */
        /* JADX WARN: Code duplicated, block: B:30:0x016f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:31:0x0170  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object $result;
            Object prevValue$iv;
            UiState it;
            Object objMo52createSessionIoAF18A;
            MainViewModel mainViewModel;
            MainViewModel mainViewModel2;
            Throwable t;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv2;
            UiState it2;
            MutableSharedFlow mutableSharedFlow;
            ToastEvent.Error error;
            String message;
            Object $result2;
            MutableStateFlow $this$update$iv2;
            Object prevValue$iv3;
            UiState it3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    $result = obj;
                    MutableStateFlow $this$update$iv3 = MainViewModel.this._uiState;
                    do {
                        prevValue$iv = $this$update$iv3.getValue();
                        it = (UiState) prevValue$iv;
                    } while (!$this$update$iv3.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, true, false, "Creating chat…", null, null, null, 61865983, null)));
                    this.label = 1;
                    objMo52createSessionIoAF18A = MainViewModel.this.sessionRepository.mo52createSessionIoAF18A(this);
                    if (objMo52createSessionIoAF18A == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mainViewModel = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objMo52createSessionIoAF18A)) {
                        AuroraSession created = (AuroraSession) objMo52createSessionIoAF18A;
                        $this$update$iv2 = mainViewModel._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 61865983, null)));
                        mainViewModel.openSession(created.getId());
                    }
                    mainViewModel2 = MainViewModel.this;
                    t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo52createSessionIoAF18A);
                    if (t != null) {
                        $this$update$iv = mainViewModel2._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv.getValue();
                            it2 = (UiState) prevValue$iv2;
                        } while (!$this$update$iv.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 61865983, null)));
                        mutableSharedFlow = mainViewModel2._toasts;
                        message = t.getMessage();
                        if (message == null) {
                            message = "Could not create chat";
                        }
                        error = new ToastEvent.Error(message);
                        this.L$0 = objMo52createSessionIoAF18A;
                        this.label = 2;
                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                    }
                    Object $result3 = Unit.INSTANCE;
                    return $result3;
                case 1:
                    $result = obj;
                    ResultKt.throwOnFailure($result);
                    objMo52createSessionIoAF18A = ai.nous.aurora.compat.Compat.resultUnbox($result);
                    mainViewModel = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objMo52createSessionIoAF18A)) {
                        AuroraSession created2 = (AuroraSession) objMo52createSessionIoAF18A;
                        $this$update$iv2 = mainViewModel._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 61865983, null)));
                        mainViewModel.openSession(created2.getId());
                    }
                    mainViewModel2 = MainViewModel.this;
                    t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo52createSessionIoAF18A);
                    if (t != null) {
                        $this$update$iv = mainViewModel2._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv.getValue();
                            it2 = (UiState) prevValue$iv2;
                        } while (!$this$update$iv.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 61865983, null)));
                        mutableSharedFlow = mainViewModel2._toasts;
                        message = t.getMessage();
                        if (message == null) {
                            message = "Could not create chat";
                        }
                        error = new ToastEvent.Error(message);
                        this.L$0 = objMo52createSessionIoAF18A;
                        this.label = 2;
                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                    }
                    Object $result4 = Unit.INSTANCE;
                    return $result4;
                case 2:
                    $result2 = obj;
                    ResultKt.throwOnFailure($result2);
                    Object $result5 = Unit.INSTANCE;
                    return $result5;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void createSession() {
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01841(null), 3, null);
    }

    public final void send() {
        if (this._uiState.getValue().getSending()) {
            return;
        }
        String sessionId = this._uiState.getValue().getSelectedSessionId();
        String content = StringsKt.trim((CharSequence) this._uiState.getValue().getDraft()).toString();
        String str = sessionId;
        if ((str == null || StringsKt.isBlank(str)) || StringsKt.isBlank(content)) {
            return;
        }
        Job job = this.sendJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.sendJob = ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C02001(content, sessionId, null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$send$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$send$1", f = "MainViewModel.kt", i = {0, 1, 2, 3, 4}, l = {852, 859, 864, 881, 885}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch", "$this$launch", "c"}, s = {"L$0", "L$0", "L$0", "L$0", "L$0"})
    final class C02001 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $content;
        final /* synthetic */ String $sendSessionId;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02001(String str, String str2, Continuation continuation) {
            super(2, continuation);
            this.$content = str;
            this.$sendSessionId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C02001 c02001 = MainViewModel.this.new C02001(this.$content, this.$sendSessionId, continuation);
            c02001.L$0 = obj;
            return c02001;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02001)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:127:0x0382 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:128:0x01a6 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:35:0x00f0 A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:36:0x00f4 A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:39:0x0114 A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:40:0x0155 A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:45:0x01b2  */
        /* JADX WARN: Code duplicated, block: B:48:0x01c2 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:49:0x01c3  */
        /* JADX WARN: Code duplicated, block: B:52:0x01c8 A[LOOP:4: B:37:0x00ff->B:52:0x01c8, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:56:0x01f6 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:57:0x01f7  */
        /* JADX WARN: Code duplicated, block: B:60:0x0203 A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:62:0x021b A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:66:0x026a A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:73:0x02c6 A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:75:0x02cd A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:78:0x02ea A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:79:0x032e A[Catch: CancellationException -> 0x0060, TryCatch #0 {CancellationException -> 0x0060, blocks: (B:12:0x0029, B:15:0x0035, B:58:0x01f8, B:60:0x0203, B:62:0x021b, B:63:0x0220, B:66:0x026a, B:67:0x026f, B:71:0x02ba, B:73:0x02c6, B:75:0x02cd, B:76:0x02d5, B:78:0x02ea, B:80:0x0375, B:82:0x0382, B:85:0x0390, B:79:0x032e, B:93:0x03ae, B:18:0x0048, B:50:0x01c5, B:21:0x0054, B:33:0x00e3, B:35:0x00f0, B:54:0x01cf, B:36:0x00f4, B:37:0x00ff, B:39:0x0114, B:41:0x019a, B:43:0x01a6, B:46:0x01b4, B:40:0x0155, B:28:0x00bf, B:30:0x00c9, B:53:0x01cd), top: B:116:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:84:0x038e  */
        /* JADX WARN: Code duplicated, block: B:87:0x03a0 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:88:0x03a1  */
        /* JADX WARN: Code duplicated, block: B:91:0x03a5 A[LOOP:3: B:76:0x02d5->B:91:0x03a5, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:92:0x03ac  */
        /* JADX WARN: Not initialized variable reg: 3, insn: 0x03cd: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY] A[D('$result' java.lang.Object)]) (LINE:885), block:B:100:0x03cd */
        /* JADX WARN: Not initialized variable reg: 3, insn: 0x03d7: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY] A[D('$result' java.lang.Object)]), block:B:104:0x03d7 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            CancellationException c;
            Object prevValue$iv;
            UiState it;
            CoroutineScope $this$launch;
            Object prevValue$iv2;
            String outbound;
            Object objM65encodeMoaPrompt0E7RQCE$default;
            Object objMo56sendMessageBWLJW6A;
            Object value;
            MainViewModel mainViewModel;
            String str;
            String str2;
            Throwable err;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv3;
            UiState it2;
            String str3;
            UiState uiStateCopy$default;
            UiState it3;
            MutableStateFlow $this$update$iv2;
            MutableSharedFlow mutableSharedFlow;
            ToastEvent.Error error;
            String message;
            MainViewModel mainViewModel2;
            String str4;
            MainViewModel mainViewModel3;
            String str5;
            String str6;
            Throwable thM7391exceptionOrNullimpl;
            Throwable t;
            MutableStateFlow $this$update$iv3;
            Object prevValue$iv4;
            UiState it4;
            String str7;
            UiState uiStateCopy$default2;
            UiState uiState;
            MutableStateFlow $this$update$iv4;
            MutableSharedFlow mutableSharedFlow2;
            ToastEvent.Error error2;
            String message2;
            MutableStateFlow $this$update$iv5;
            Object prevValue$iv5;
            MutableStateFlow $this$update$iv6;
            Object prevValue$iv6;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this$launch = (CoroutineScope) this.L$0;
                        MutableStateFlow $this$update$iv7 = MainViewModel.this._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv7.getValue();
                        } while (!$this$update$iv7.compareAndSet(prevValue$iv2, UiState.copy$default((UiState) prevValue$iv2, null, null, null, null, null, null, null, null, false, null, null, null, "", null, null, null, null, null, false, false, false, true, "Hermes is thinking…", null, null, null, 60813311, null)));
                        if (MoaCommandBoundary.INSTANCE.isMoaCommand(this.$content)) {
                            this.L$0 = $this$launch;
                            this.label = 1;
                            objM65encodeMoaPrompt0E7RQCE$default = SettingsRepository.DefaultImpls.m65encodeMoaPrompt0E7RQCE$default(MainViewModel.this.settingsRepository, this.$content, null, this, 2, null);
                            if (objM65encodeMoaPrompt0E7RQCE$default == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            mainViewModel = MainViewModel.this;
                            str = this.$sendSessionId;
                            str2 = this.$content;
                            err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM65encodeMoaPrompt0E7RQCE$default);
                            if (err == null) {
                                outbound = (String) objM65encodeMoaPrompt0E7RQCE$default;
                            } else {
                                $this$update$iv = mainViewModel._uiState;
                                while (true) {
                                    prevValue$iv3 = $this$update$iv.getValue();
                                    it2 = (UiState) prevValue$iv3;
                                    if (Intrinsics.areEqual(it2.getSelectedSessionId(), str)) {
                                        str3 = str2;
                                        uiStateCopy$default = UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, str3, null, null, null, null, null, false, false, false, false, null, null, null, null, 60813311, null);
                                    } else {
                                        str3 = str2;
                                        uiStateCopy$default = UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null);
                                    }
                                    it3 = uiStateCopy$default;
                                    $this$update$iv2 = $this$update$iv;
                                    if ($this$update$iv2.compareAndSet(prevValue$iv3, it3)) {
                                        mutableSharedFlow = mainViewModel._toasts;
                                        message = err.getMessage();
                                        if (message == null) {
                                            message = "Could not encode /moa";
                                        }
                                        error = new ToastEvent.Error(message);
                                        this.L$0 = $this$launch;
                                        this.label = 2;
                                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        return Unit.INSTANCE;
                                    }
                                    $this$update$iv = $this$update$iv2;
                                    str2 = str3;
                                }
                            }
                        } else {
                            outbound = this.$content;
                        }
                        this.L$0 = $this$launch;
                        this.label = 3;
                        objMo56sendMessageBWLJW6A = MainViewModel.this.sessionRepository.mo56sendMessageBWLJW6A(this.$sendSessionId, outbound, ((UiState) MainViewModel.this._uiState.getValue()).getSelectedModel(), this);
                        if (objMo56sendMessageBWLJW6A == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        value = objMo56sendMessageBWLJW6A;
                        mainViewModel2 = MainViewModel.this;
                        str4 = this.$sendSessionId;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                            if (Intrinsics.areEqual(((UiState) mainViewModel2._uiState.getValue()).getSelectedSessionId(), str4)) {
                                $this$update$iv6 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv6 = $this$update$iv6.getValue();
                                } while (!$this$update$iv6.compareAndSet(prevValue$iv6, UiState.copy$default((UiState) prevValue$iv6, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 60817407, null)));
                            } else {
                                $this$update$iv5 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv5 = $this$update$iv5.getValue();
                                } while (!$this$update$iv5.compareAndSet(prevValue$iv5, UiState.copy$default((UiState) prevValue$iv5, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null)));
                            }
                        }
                        mainViewModel3 = MainViewModel.this;
                        str5 = this.$sendSessionId;
                        str6 = this.$content;
                        thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                        if (thM7391exceptionOrNullimpl != null) {
                            t = thM7391exceptionOrNullimpl;
                            if (t instanceof CancellationException) {
                                throw t;
                            }
                            $this$update$iv3 = mainViewModel3._uiState;
                            while (true) {
                                prevValue$iv4 = $this$update$iv3.getValue();
                                it4 = (UiState) prevValue$iv4;
                                if (Intrinsics.areEqual(it4.getSelectedSessionId(), str5)) {
                                    str7 = str6;
                                    uiStateCopy$default2 = UiState.copy$default(it4, null, null, null, null, null, null, null, null, false, null, null, null, str7, null, null, null, null, null, false, false, false, false, null, null, null, null, 60813311, null);
                                } else {
                                    str7 = str6;
                                    uiStateCopy$default2 = UiState.copy$default(it4, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null);
                                }
                                uiState = uiStateCopy$default2;
                                $this$update$iv4 = $this$update$iv3;
                                if ($this$update$iv4.compareAndSet(prevValue$iv4, uiState)) {
                                    mutableSharedFlow2 = mainViewModel3._toasts;
                                    message2 = t.getMessage();
                                    if (message2 == null) {
                                        message2 = "Send failed";
                                    }
                                    error2 = new ToastEvent.Error(message2);
                                    this.L$0 = $this$launch;
                                    this.L$1 = value;
                                    this.label = 4;
                                    if (mutableSharedFlow2.emit(error2, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } else {
                                    $this$update$iv3 = $this$update$iv4;
                                    str6 = str7;
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    case 1:
                        $this$launch = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        objM65encodeMoaPrompt0E7RQCE$default = ai.nous.aurora.compat.Compat.resultUnbox($result);
                        mainViewModel = MainViewModel.this;
                        str = this.$sendSessionId;
                        str2 = this.$content;
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM65encodeMoaPrompt0E7RQCE$default);
                        if (err == null) {
                            outbound = (String) objM65encodeMoaPrompt0E7RQCE$default;
                            this.L$0 = $this$launch;
                            this.label = 3;
                            objMo56sendMessageBWLJW6A = MainViewModel.this.sessionRepository.mo56sendMessageBWLJW6A(this.$sendSessionId, outbound, ((UiState) MainViewModel.this._uiState.getValue()).getSelectedModel(), this);
                            if (objMo56sendMessageBWLJW6A == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            value = objMo56sendMessageBWLJW6A;
                            mainViewModel2 = MainViewModel.this;
                            str4 = this.$sendSessionId;
                            if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                                if (Intrinsics.areEqual(((UiState) mainViewModel2._uiState.getValue()).getSelectedSessionId(), str4)) {
                                    $this$update$iv6 = mainViewModel2._uiState;
                                    do {
                                        prevValue$iv6 = $this$update$iv6.getValue();
                                    } while (!$this$update$iv6.compareAndSet(prevValue$iv6, UiState.copy$default((UiState) prevValue$iv6, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 60817407, null)));
                                } else {
                                    $this$update$iv5 = mainViewModel2._uiState;
                                    do {
                                        prevValue$iv5 = $this$update$iv5.getValue();
                                    } while (!$this$update$iv5.compareAndSet(prevValue$iv5, UiState.copy$default((UiState) prevValue$iv5, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null)));
                                }
                            }
                            mainViewModel3 = MainViewModel.this;
                            str5 = this.$sendSessionId;
                            str6 = this.$content;
                            thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                            if (thM7391exceptionOrNullimpl != null) {
                                t = thM7391exceptionOrNullimpl;
                                if (t instanceof CancellationException) {
                                    throw t;
                                }
                                $this$update$iv3 = mainViewModel3._uiState;
                                while (true) {
                                    prevValue$iv4 = $this$update$iv3.getValue();
                                    it4 = (UiState) prevValue$iv4;
                                    if (Intrinsics.areEqual(it4.getSelectedSessionId(), str5)) {
                                        str7 = str6;
                                        uiStateCopy$default2 = UiState.copy$default(it4, null, null, null, null, null, null, null, null, false, null, null, null, str7, null, null, null, null, null, false, false, false, false, null, null, null, null, 60813311, null);
                                    } else {
                                        str7 = str6;
                                        uiStateCopy$default2 = UiState.copy$default(it4, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null);
                                    }
                                    uiState = uiStateCopy$default2;
                                    $this$update$iv4 = $this$update$iv3;
                                    if ($this$update$iv4.compareAndSet(prevValue$iv4, uiState)) {
                                        mutableSharedFlow2 = mainViewModel3._toasts;
                                        message2 = t.getMessage();
                                        if (message2 == null) {
                                            message2 = "Send failed";
                                        }
                                        error2 = new ToastEvent.Error(message2);
                                        this.L$0 = $this$launch;
                                        this.L$1 = value;
                                        this.label = 4;
                                        if (mutableSharedFlow2.emit(error2, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    } else {
                                        $this$update$iv3 = $this$update$iv4;
                                        str6 = str7;
                                    }
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        $this$update$iv = mainViewModel._uiState;
                        while (true) {
                            prevValue$iv3 = $this$update$iv.getValue();
                            it2 = (UiState) prevValue$iv3;
                            if (Intrinsics.areEqual(it2.getSelectedSessionId(), str)) {
                                str3 = str2;
                                uiStateCopy$default = UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, str3, null, null, null, null, null, false, false, false, false, null, null, null, null, 60813311, null);
                            } else {
                                str3 = str2;
                                uiStateCopy$default = UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null);
                            }
                            it3 = uiStateCopy$default;
                            $this$update$iv2 = $this$update$iv;
                            if ($this$update$iv2.compareAndSet(prevValue$iv3, it3)) {
                                mutableSharedFlow = mainViewModel._toasts;
                                message = err.getMessage();
                                if (message == null) {
                                    message = "Could not encode /moa";
                                }
                                error = new ToastEvent.Error(message);
                                this.L$0 = $this$launch;
                                this.label = 2;
                                if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                            $this$update$iv = $this$update$iv2;
                            str2 = str3;
                        }
                        break;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    case 3:
                        $this$launch = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        value = ai.nous.aurora.compat.Compat.resultUnbox($result);
                        mainViewModel2 = MainViewModel.this;
                        str4 = this.$sendSessionId;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                            if (Intrinsics.areEqual(((UiState) mainViewModel2._uiState.getValue()).getSelectedSessionId(), str4)) {
                                $this$update$iv6 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv6 = $this$update$iv6.getValue();
                                } while (!$this$update$iv6.compareAndSet(prevValue$iv6, UiState.copy$default((UiState) prevValue$iv6, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 60817407, null)));
                            } else {
                                $this$update$iv5 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv5 = $this$update$iv5.getValue();
                                } while (!$this$update$iv5.compareAndSet(prevValue$iv5, UiState.copy$default((UiState) prevValue$iv5, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null)));
                            }
                        }
                        mainViewModel3 = MainViewModel.this;
                        str5 = this.$sendSessionId;
                        str6 = this.$content;
                        thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                        if (thM7391exceptionOrNullimpl != null) {
                            t = thM7391exceptionOrNullimpl;
                            if (t instanceof CancellationException) {
                                throw t;
                            }
                            $this$update$iv3 = mainViewModel3._uiState;
                            while (true) {
                                prevValue$iv4 = $this$update$iv3.getValue();
                                it4 = (UiState) prevValue$iv4;
                                if (Intrinsics.areEqual(it4.getSelectedSessionId(), str5)) {
                                    str7 = str6;
                                    uiStateCopy$default2 = UiState.copy$default(it4, null, null, null, null, null, null, null, null, false, null, null, null, str7, null, null, null, null, null, false, false, false, false, null, null, null, null, 60813311, null);
                                } else {
                                    str7 = str6;
                                    uiStateCopy$default2 = UiState.copy$default(it4, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null);
                                }
                                uiState = uiStateCopy$default2;
                                $this$update$iv4 = $this$update$iv3;
                                if ($this$update$iv4.compareAndSet(prevValue$iv4, uiState)) {
                                    mutableSharedFlow2 = mainViewModel3._toasts;
                                    message2 = t.getMessage();
                                    if (message2 == null) {
                                        message2 = "Send failed";
                                    }
                                    error2 = new ToastEvent.Error(message2);
                                    this.L$0 = $this$launch;
                                    this.L$1 = value;
                                    this.label = 4;
                                    if (mutableSharedFlow2.emit(error2, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } else {
                                    $this$update$iv3 = $this$update$iv4;
                                    str6 = str7;
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    case 4:
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    case 5:
                        c = (CancellationException) this.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                            break;
                        } catch (Throwable th) {
                            Result.Companion companion = Result.Companion;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                        }
                        MutableStateFlow $this$update$iv8 = MainViewModel.this._uiState;
                        String str8 = this.$sendSessionId;
                        do {
                            prevValue$iv = $this$update$iv8.getValue();
                            it = (UiState) prevValue$iv;
                        } while (!$this$update$iv8.compareAndSet(prevValue$iv, Intrinsics.areEqual(it.getSelectedSessionId(), str8) ? UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Send cancelled", null, null, null, 60817407, null) : UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null)));
                        throw c;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (CancellationException c2) {
                MainViewModel mainViewModel4 = MainViewModel.this;
                String str9 = this.$sendSessionId;
                try {
                    Result.Companion companion2 = Result.Companion;
                    SessionRepository sessionRepository = mainViewModel4.sessionRepository;
                    this.L$0 = c2;
                    this.L$1 = null;
                    this.label = 5;
                    if (sessionRepository.failPendingMessages(str9, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c = c2;
                } catch (Throwable th2) {
                    c = c2;
                    Result.Companion companion3 = Result.Companion;
                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                }
            }
        }
    }

    public final void cancelSend() {
        UiState value;
        UiState it;
        String sessionId = this._uiState.getValue().getSelectedSessionId();
        Job job = this.sendJob;
        boolean z = true;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.sendJob = null;
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            it = value;
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Send cancelled", null, null, null, 60817407, null)));
        String str = sessionId;
        if (str != null && !StringsKt.isBlank(str)) {
            z = false;
        }
        if (!z) {
            ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01822(sessionId, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$cancelSend$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$cancelSend$2", f = "MainViewModel.kt", i = {}, l = {904}, m = "invokeSuspend", n = {}, s = {})
    final class C01822 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $sessionId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01822(String str, Continuation continuation) {
            super(2, continuation);
            this.$sessionId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01822(this.$sessionId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01822)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.sessionRepository.failPendingMessages(this.$sessionId, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void retryFailedMessage(String messageId) {
        Object element$iv;
        AuroraMessage it;
        Intrinsics.checkNotNullParameter(messageId, "messageId");
        String sessionId = this._uiState.getValue().getSelectedSessionId();
        if (sessionId == null || this._uiState.getValue().getSending()) {
            return;
        }
        Iterable $this$firstOrNull$iv = this._uiState.getValue().getMessages();
        Iterator it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            } else {
                element$iv = it2.next();
                it = (AuroraMessage) element$iv;
            }
        } while (!Intrinsics.areEqual(it.getId(), messageId));
        AuroraMessage target = (AuroraMessage) element$iv;
        if (target != null && !target.getCanRetry()) {
            ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01971(sessionId, messageId, null), 3, null);
        } else {
            doRetry(sessionId, messageId);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$retryFailedMessage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$retryFailedMessage$1", f = "MainViewModel.kt", i = {}, l = {916, 919}, m = "invokeSuspend", n = {}, s = {})
    final class C01971 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $messageId;
        final /* synthetic */ String $sessionId;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01971(String str, String str2, Continuation continuation) {
            super(2, continuation);
            this.$sessionId = str;
            this.$messageId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C01971 c01971 = MainViewModel.this.new C01971(this.$sessionId, this.$messageId, continuation);
            c01971.L$0 = obj;
            return c01971;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01971)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:21:0x0070  */
        /* JADX WARN: Code duplicated, block: B:27:0x0088  */
        /* JADX WARN: Code duplicated, block: B:36:0x00af  */
        /* JADX WARN: Code duplicated, block: B:39:0x00b6  */
        /* JADX WARN: Code duplicated, block: B:41:0x00b9  */
        /* JADX WARN: Code duplicated, block: B:46:0x0083 A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            String str;
            Iterator it;
            Object element$iv;
            AuroraMessage after;
            AuroraMessage it2;
            Object objMo55selectSessiongIAlus;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        MainViewModel mainViewModel = MainViewModel.this;
                        String str2 = this.$sessionId;
                        Result.Companion companion = Result.Companion;
                        SessionRepository sessionRepository = mainViewModel.sessionRepository;
                        this.label = 1;
                        objMo55selectSessiongIAlus = sessionRepository.mo55selectSessiongIAlus(str2, this);
                        if (objMo55selectSessiongIAlus == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus));
                        Iterable $this$firstOrNull$iv = ((UiState) MainViewModel.this._uiState.getValue()).getMessages();
                        str = this.$messageId;
                        it = $this$firstOrNull$iv.iterator();
                        do {
                            if (it.hasNext()) {
                                element$iv = it.next();
                                it2 = (AuroraMessage) element$iv;
                            } else {
                                element$iv = null;
                            }
                            after = (AuroraMessage) element$iv;
                            if (after == null && !after.getCanRetry()) {
                                this.label = 2;
                                if (MainViewModel.this._toasts.emit(new ToastEvent.Info("Delivery is uncertain — refresh completed. Retry unlocks only if the message is still missing on Hermes."), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                            if (after == null && after.getCanRetry()) {
                                MainViewModel.this.doRetry(this.$sessionId, this.$messageId);
                            }
                            return Unit.INSTANCE;
                        } while (!Intrinsics.areEqual(it2.getId(), str));
                        after = (AuroraMessage) element$iv;
                        if (after == null) {
                        }
                        if (after == null && after.getCanRetry()) {
                            MainViewModel.this.doRetry(this.$sessionId, this.$messageId);
                        }
                        return Unit.INSTANCE;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        objMo55selectSessiongIAlus = ai.nous.aurora.compat.Compat.resultUnbox($result);
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus));
                        Iterable $this$firstOrNull$iv2 = ((UiState) MainViewModel.this._uiState.getValue()).getMessages();
                        str = this.$messageId;
                        it = $this$firstOrNull$iv2.iterator();
                        do {
                            if (it.hasNext()) {
                                element$iv = it.next();
                                it2 = (AuroraMessage) element$iv;
                            } else {
                                element$iv = null;
                            }
                            after = (AuroraMessage) element$iv;
                            if (after == null) {
                                break;
                            }
                            if (after == null && after.getCanRetry()) {
                                MainViewModel.this.doRetry(this.$sessionId, this.$messageId);
                            }
                            return Unit.INSTANCE;
                        } while (!Intrinsics.areEqual(it2.getId(), str));
                        after = (AuroraMessage) element$iv;
                        if (after == null) {
                            break;
                        }
                        if (after == null && after.getCanRetry()) {
                            MainViewModel.this.doRetry(this.$sessionId, this.$messageId);
                        }
                        return Unit.INSTANCE;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$doRetry$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$doRetry$1", f = "MainViewModel.kt", i = {2}, l = {940, 947, 950}, m = "invokeSuspend", n = {"c"}, s = {"L$0"})
    final class C01861 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $messageId;
        final /* synthetic */ String $sessionId;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01861(String str, String str2, Continuation continuation) {
            super(2, continuation);
            this.$sessionId = str;
            this.$messageId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01861(this.$sessionId, this.$messageId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01861)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:21:0x00c2 A[Catch: CancellationException -> 0x0034, TryCatch #0 {CancellationException -> 0x0034, blocks: (B:8:0x0021, B:9:0x0026, B:19:0x00b9, B:21:0x00c2, B:22:0x00cb, B:25:0x0115, B:27:0x011d, B:29:0x0122, B:30:0x0127, B:32:0x0171, B:35:0x017f, B:41:0x0191, B:16:0x0092), top: B:54:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:27:0x011d A[Catch: CancellationException -> 0x0034, TryCatch #0 {CancellationException -> 0x0034, blocks: (B:8:0x0021, B:9:0x0026, B:19:0x00b9, B:21:0x00c2, B:22:0x00cb, B:25:0x0115, B:27:0x011d, B:29:0x0122, B:30:0x0127, B:32:0x0171, B:35:0x017f, B:41:0x0191, B:16:0x0092), top: B:54:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:29:0x0122 A[Catch: CancellationException -> 0x0034, TryCatch #0 {CancellationException -> 0x0034, blocks: (B:8:0x0021, B:9:0x0026, B:19:0x00b9, B:21:0x00c2, B:22:0x00cb, B:25:0x0115, B:27:0x011d, B:29:0x0122, B:30:0x0127, B:32:0x0171, B:35:0x017f, B:41:0x0191, B:16:0x0092), top: B:54:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:34:0x017d  */
        /* JADX WARN: Code duplicated, block: B:37:0x018d A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:38:0x018e  */
        /* JADX WARN: Code duplicated, block: B:41:0x0191 A[Catch: CancellationException -> 0x0034, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x0034, blocks: (B:8:0x0021, B:9:0x0026, B:19:0x00b9, B:21:0x00c2, B:22:0x00cb, B:25:0x0115, B:27:0x011d, B:29:0x0122, B:30:0x0127, B:32:0x0171, B:35:0x017f, B:41:0x0191, B:16:0x0092), top: B:54:0x0008 }] */
        /* JADX WARN: Not initialized variable reg: 3, insn: 0x0035: MOVE (r40 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY] A[D('$result' java.lang.Object)]), block:B:12:0x0035 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            CancellationException c;
            Object prevValue$iv;
            UiState it;
            Object prevValue$iv2;
            UiState it2;
            Object objMo54retryMessageBWLJW6A;
            MainViewModel mainViewModel;
            MainViewModel mainViewModel2;
            Throwable t;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv3;
            UiState it3;
            MutableSharedFlow mutableSharedFlow;
            ToastEvent.Error error;
            String message;
            MutableStateFlow $this$update$iv2;
            Object prevValue$iv4;
            UiState it4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        MutableStateFlow $this$update$iv3 = MainViewModel.this._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv3.getValue();
                            it2 = (UiState) prevValue$iv2;
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, true, "Hermes is thinking…", null, null, null, 60817407, null)));
                        this.label = 1;
                        objMo54retryMessageBWLJW6A = MainViewModel.this.sessionRepository.mo54retryMessageBWLJW6A(this.$sessionId, this.$messageId, ((UiState) MainViewModel.this._uiState.getValue()).getSelectedModel(), this);
                        if (objMo54retryMessageBWLJW6A == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mainViewModel = MainViewModel.this;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objMo54retryMessageBWLJW6A)) {
                            $this$update$iv2 = mainViewModel._uiState;
                            do {
                                prevValue$iv4 = $this$update$iv2.getValue();
                                it4 = (UiState) prevValue$iv4;
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv4, UiState.copy$default(it4, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 60817407, null)));
                        }
                        mainViewModel2 = MainViewModel.this;
                        t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo54retryMessageBWLJW6A);
                        if (t != null) {
                            if (!(t instanceof CancellationException)) {
                                throw t;
                            }
                            $this$update$iv = mainViewModel2._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv.getValue();
                                it3 = (UiState) prevValue$iv3;
                            } while (!$this$update$iv.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null)));
                            mutableSharedFlow = mainViewModel2._toasts;
                            message = t.getMessage();
                            if (message == null) {
                                message = "Retry failed";
                            }
                            error = new ToastEvent.Error(message);
                            this.L$0 = objMo54retryMessageBWLJW6A;
                            this.label = 2;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        return Unit.INSTANCE;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        objMo54retryMessageBWLJW6A = ai.nous.aurora.compat.Compat.resultUnbox($result);
                        mainViewModel = MainViewModel.this;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objMo54retryMessageBWLJW6A)) {
                            $this$update$iv2 = mainViewModel._uiState;
                            do {
                                prevValue$iv4 = $this$update$iv2.getValue();
                                it4 = (UiState) prevValue$iv4;
                            } while (!$this$update$iv2.compareAndSet(prevValue$iv4, UiState.copy$default(it4, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 60817407, null)));
                        }
                        mainViewModel2 = MainViewModel.this;
                        t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo54retryMessageBWLJW6A);
                        if (t != null) {
                            if (!(t instanceof CancellationException)) {
                                throw t;
                            }
                            $this$update$iv = mainViewModel2._uiState;
                            do {
                                prevValue$iv3 = $this$update$iv.getValue();
                                it3 = (UiState) prevValue$iv3;
                            } while (!$this$update$iv.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 65011711, null)));
                            mutableSharedFlow = mainViewModel2._toasts;
                            message = t.getMessage();
                            if (message == null) {
                                message = "Retry failed";
                            }
                            error = new ToastEvent.Error(message);
                            this.L$0 = objMo54retryMessageBWLJW6A;
                            this.label = 2;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        return Unit.INSTANCE;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    case 3:
                        c = (CancellationException) this.L$0;
                        ResultKt.throwOnFailure($result);
                        MutableStateFlow $this$update$iv4 = MainViewModel.this._uiState;
                        do {
                            prevValue$iv = $this$update$iv4.getValue();
                            it = (UiState) prevValue$iv;
                        } while (!$this$update$iv4.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, "Send cancelled", null, null, null, 60817407, null)));
                        throw c;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (CancellationException c2) {
                this.L$0 = c2;
                this.label = 3;
                if (MainViewModel.this.sessionRepository.failPendingMessages(this.$sessionId, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                c = c2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doRetry(String sessionId, String messageId) {
        Job job = this.sendJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.sendJob = ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01861(sessionId, messageId, null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$refreshSessions$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$refreshSessions$1", f = "MainViewModel.kt", i = {}, l = {960, 967}, m = "invokeSuspend", n = {}, s = {})
    final class C01931 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        Object L$0;
        int label;

        C01931(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01931(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01931)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0099  */
        /* JADX WARN: Code duplicated, block: B:22:0x00fd  */
        /* JADX WARN: Code duplicated, block: B:27:0x015c  */
        /* JADX WARN: Code duplicated, block: B:30:0x016c A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:31:0x016d  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object $result;
            Object prevValue$iv;
            UiState it;
            Object objMo53refreshSessionsIoAF18A;
            MainViewModel mainViewModel;
            MainViewModel mainViewModel2;
            Throwable t;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv2;
            UiState it2;
            MutableSharedFlow mutableSharedFlow;
            ToastEvent.Error error;
            String message;
            Object $result2;
            MutableStateFlow $this$update$iv2;
            Object prevValue$iv3;
            UiState it3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    $result = obj;
                    MutableStateFlow $this$update$iv3 = MainViewModel.this._uiState;
                    do {
                        prevValue$iv = $this$update$iv3.getValue();
                        it = (UiState) prevValue$iv;
                    } while (!$this$update$iv3.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, true, false, null, null, null, null, 66060287, null)));
                    this.label = 1;
                    objMo53refreshSessionsIoAF18A = MainViewModel.this.sessionRepository.mo53refreshSessionsIoAF18A(this);
                    if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mainViewModel = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objMo53refreshSessionsIoAF18A)) {
                        $this$update$iv2 = mainViewModel._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 66060287, null)));
                        mainViewModel.flashStatus("Sessions refreshed");
                    }
                    mainViewModel2 = MainViewModel.this;
                    t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo53refreshSessionsIoAF18A);
                    if (t != null) {
                        $this$update$iv = mainViewModel2._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv.getValue();
                            it2 = (UiState) prevValue$iv2;
                        } while (!$this$update$iv.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 66060287, null)));
                        mutableSharedFlow = mainViewModel2._toasts;
                        message = t.getMessage();
                        if (message == null) {
                            message = "Refresh failed";
                        }
                        error = new ToastEvent.Error(message);
                        this.L$0 = objMo53refreshSessionsIoAF18A;
                        this.label = 2;
                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                    }
                    Object $result3 = Unit.INSTANCE;
                    return $result3;
                case 1:
                    $result = obj;
                    ResultKt.throwOnFailure($result);
                    objMo53refreshSessionsIoAF18A = ai.nous.aurora.compat.Compat.resultUnbox($result);
                    mainViewModel = MainViewModel.this;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objMo53refreshSessionsIoAF18A)) {
                        $this$update$iv2 = mainViewModel._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv2.getValue();
                            it3 = (UiState) prevValue$iv3;
                        } while (!$this$update$iv2.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 66060287, null)));
                        mainViewModel.flashStatus("Sessions refreshed");
                    }
                    mainViewModel2 = MainViewModel.this;
                    t = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo53refreshSessionsIoAF18A);
                    if (t != null) {
                        $this$update$iv = mainViewModel2._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv.getValue();
                            it2 = (UiState) prevValue$iv2;
                        } while (!$this$update$iv.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 66060287, null)));
                        mutableSharedFlow = mainViewModel2._toasts;
                        message = t.getMessage();
                        if (message == null) {
                            message = "Refresh failed";
                        }
                        error = new ToastEvent.Error(message);
                        this.L$0 = objMo53refreshSessionsIoAF18A;
                        this.label = 2;
                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                    }
                    Object $result4 = Unit.INSTANCE;
                    return $result4;
                case 2:
                    $result2 = obj;
                    ResultKt.throwOnFailure($result2);
                    Object $result5 = Unit.INSTANCE;
                    return $result5;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void refreshSessions() {
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01931(null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$disconnect$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$disconnect$1", f = "MainViewModel.kt", i = {0}, l = {982, 984}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    final class C01851 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C01851(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C01851 c01851 = MainViewModel.this.new C01851(continuation);
            c01851.L$0 = obj;
            return c01851;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01851)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:33:0x00aa A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:34:0x00ab  */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow mutableStateFlow;
            Object value;
            String str;
            boolean z;
            String str2;
            boolean z2;
            String str3;
            String str4;
            List list;
            String str5;
            List list2;
            List list3;
            String str6;
            String str7;
            String str8;
            boolean z3;
            boolean z4;
            boolean z5;
            boolean z6;
            String str9;
            SettingsUiModel settingsUiModel;
            Set set;
            SessionRepository sessionRepository;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        Job job = MainViewModel.this.sendJob;
                        if (job != null) {
                            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
                        }
                        MainViewModel.this.sendJob = null;
                        Job job2 = MainViewModel.this.messagesJob;
                        if (job2 != null) {
                            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job2, (CancellationException) null, 1, (Object) null);
                        }
                        Job job3 = MainViewModel.this.settingsLoadJob;
                        if (job3 != null) {
                            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job3, (CancellationException) null, 1, (Object) null);
                        }
                        Job job4 = MainViewModel.this.pollJob;
                        if (job4 != null) {
                            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job4, (CancellationException) null, 1, (Object) null);
                        }
                        MainViewModel.this.pollJob = null;
                        Job job5 = MainViewModel.this.reconnectJob;
                        if (job5 != null) {
                            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job5, (CancellationException) null, 1, (Object) null);
                        }
                        MainViewModel.this.reconnectJob = null;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        if (MainViewModel.this.credentialStore.signOut(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        MainViewModel.this.gatewayClient.disconnect();
                        MainViewModel mainViewModel = MainViewModel.this;
                        Result.Companion companion = Result.Companion;
                        sessionRepository = mainViewModel.sessionRepository;
                        this.L$0 = null;
                        this.label = 2;
                        if (sessionRepository.clearLocalCache(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                        mutableStateFlow = MainViewModel.this._uiState;
                        do {
                            value = mutableStateFlow.getValue();
                            str = "";
                            z = false;
                            str2 = "Signed out. Paste your API key once to reconnect; it is never stored in the APK.";
                            z2 = false;
                            str3 = null;
                            str4 = null;
                            list = null;
                            str5 = null;
                            list2 = null;
                            list3 = null;
                            str6 = null;
                            str7 = null;
                            str8 = null;
                            z3 = false;
                            z4 = false;
                            z5 = false;
                            z6 = false;
                            str9 = null;
                            settingsUiModel = null;
                            set = null;
                        } while (!mutableStateFlow.compareAndSet(value, new UiState(UiMode.Connect, null, null, null, null, null, null, null, z2, str3, str4, list, str5, list2, list3, str6, str7, str8, z3, z4, z5, z6, str9, new ConnectUiState(((UiState) value).getConnectState().getGatewayAddress(), str, z, str2, 4, null), settingsUiModel, set, 58720254, null)));
                        return Unit.INSTANCE;
                    case 1:
                        ResultKt.throwOnFailure(obj);
                        MainViewModel.this.gatewayClient.disconnect();
                        MainViewModel mainViewModel2 = MainViewModel.this;
                        Result.Companion companion2 = Result.Companion;
                        sessionRepository = mainViewModel2.sessionRepository;
                        this.L$0 = null;
                        this.label = 2;
                        if (sessionRepository.clearLocalCache(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                        mutableStateFlow = MainViewModel.this._uiState;
                        do {
                            value = mutableStateFlow.getValue();
                            str = "";
                            z = false;
                            str2 = "Signed out. Paste your API key once to reconnect; it is never stored in the APK.";
                            z2 = false;
                            str3 = null;
                            str4 = null;
                            list = null;
                            str5 = null;
                            list2 = null;
                            list3 = null;
                            str6 = null;
                            str7 = null;
                            str8 = null;
                            z3 = false;
                            z4 = false;
                            z5 = false;
                            z6 = false;
                            str9 = null;
                            settingsUiModel = null;
                            set = null;
                        } while (!mutableStateFlow.compareAndSet(value, new UiState(UiMode.Connect, null, null, null, null, null, null, null, z2, str3, str4, list, str5, list2, list3, str6, str7, str8, z3, z4, z5, z6, str9, new ConnectUiState(((UiState) value).getConnectState().getGatewayAddress(), str, z, str2, 4, null), settingsUiModel, set, 58720254, null)));
                        return Unit.INSTANCE;
                    case 2:
                        ResultKt.throwOnFailure(obj);
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                        mutableStateFlow = MainViewModel.this._uiState;
                        do {
                            value = mutableStateFlow.getValue();
                            str = "";
                            z = false;
                            str2 = "Signed out. Paste your API key once to reconnect; it is never stored in the APK.";
                            z2 = false;
                            str3 = null;
                            str4 = null;
                            list = null;
                            str5 = null;
                            list2 = null;
                            list3 = null;
                            str6 = null;
                            str7 = null;
                            str8 = null;
                            z3 = false;
                            z4 = false;
                            z5 = false;
                            z6 = false;
                            str9 = null;
                            settingsUiModel = null;
                            set = null;
                        } while (!mutableStateFlow.compareAndSet(value, new UiState(UiMode.Connect, null, null, null, null, null, null, null, z2, str3, str4, list, str5, list2, list3, str6, str7, str8, z3, z4, z5, z6, str9, new ConnectUiState(((UiState) value).getConnectState().getGatewayAddress(), str, z, str2, 4, null), settingsUiModel, set, 58720254, null)));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th) {
                Result.Companion companion3 = Result.Companion;
                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    public final void disconnect() {
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01851(null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$flashStatus$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$flashStatus$1", f = "MainViewModel.kt", i = {}, l = {1001}, m = "invokeSuspend", n = {}, s = {})
    final class C01871 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $msg;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01871(String str, Continuation continuation) {
            super(2, continuation);
            this.$msg = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01871(this.$msg, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01871)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object prevValue$iv;
            UiState s;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    MutableStateFlow $this$update$iv = MainViewModel.this._uiState;
                    String str = this.$msg;
                    while (true) {
                        Object prevValue$iv2 = $this$update$iv.getValue();
                        UiState it = (UiState) prevValue$iv2;
                        String str2 = str;
                        if ($this$update$iv.compareAndSet(prevValue$iv2, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, str2, null, null, null, 62914559, null))) {
                            this.label = 1;
                            if (DelayKt.delay(1800L, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            str = str2;
                        }
                        break;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            MutableStateFlow $this$update$iv2 = MainViewModel.this._uiState;
            String str3 = this.$msg;
            do {
                prevValue$iv = $this$update$iv2.getValue();
                s = (UiState) prevValue$iv;
                if (Intrinsics.areEqual(s.getStatusLine(), str3)) {
                    s = UiState.copy$default(s, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 62914559, null);
                }
            } while (!$this$update$iv2.compareAndSet(prevValue$iv, s));
            return Unit.INSTANCE;
        }
    }

    public final void flashStatus(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01871(msg, null), 3, null);
    }

    public final void onTabSelected(AuroraTab tab) {
        Object obj;
        UiState value;
        UiState it;
        SettingsUiModel settings;
        Object tab2 = tab;
        Intrinsics.checkNotNullParameter(tab2, "tab");
        UiState cur = this._uiState.getValue();
        boolean moaDirty = cur.getSettings().getMoaDirty();
        boolean moaPane = cur.getSettings().getPane() == SettingsPane.MoaEditor;
        if (cur.getActiveTab() == AuroraTab.Settings && tab2 != AuroraTab.Settings) {
            if (!MoaDirtyNavigation.INSTANCE.allowLeaveSettingsTab(moaDirty, moaPane ? MoaDirtyNavigation.SettingsPane.MoaEditor : MoaDirtyNavigation.SettingsPane.Home)) {
                flashStatus("Save or discard MoA changes first");
                return;
            }
        }
        if (tab2 == AuroraTab.Settings && cur.getActiveTab() == AuroraTab.Settings) {
            if (!MoaDirtyNavigation.INSTANCE.allowResetSettingsToHome(moaDirty, moaPane ? MoaDirtyNavigation.SettingsPane.MoaEditor : MoaDirtyNavigation.SettingsPane.Home)) {
                return;
            } else {
                return;
            }
        }
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        while (true) {
            UiState value2 = mutableStateFlow.getValue();
            UiState it2 = value2;
            MutableStateFlow<UiState> mutableStateFlow2 = mutableStateFlow;
            boolean moaDirty2 = moaDirty;
            obj = tab2;
            if (mutableStateFlow2.compareAndSet(value2, UiState.copy$default(it2, null, tab, tab2 == AuroraTab.Chats ? it2.getChatPane() : ChatPane.List, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, null, null, 62914553, null))) {
                break;
            }
            tab2 = tab;
            mutableStateFlow = mutableStateFlow2;
            moaDirty = moaDirty2;
        }
        Object nextValue$iv = AuroraTab.Settings;
        if (obj == nextValue$iv && cur.getActiveTab() != AuroraTab.Settings) {
            MutableStateFlow<UiState> mutableStateFlow3 = this._uiState;
            do {
                value = mutableStateFlow3.getValue();
                it = value;
                settings = it.getSettings();
            } while (!mutableStateFlow3.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : SettingsPane.Home, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
            refreshSettings(true, true);
        }
    }

    public final void setMoaDirty(boolean dirty) {
        UiState value;
        UiState it;
        SettingsUiModel settings;
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            it = value;
            settings = it.getSettings();
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : dirty), null, 50331647, null)));
    }

    public final void setSettingsSearch(String q) {
        Intrinsics.checkNotNullParameter(q, "q");
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        while (true) {
            UiState value = mutableStateFlow.getValue();
            UiState it = value;
            SettingsUiModel settings = it.getSettings();
            MutableStateFlow<UiState> mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : q, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null))) {
                return;
            } else {
                mutableStateFlow = mutableStateFlow2;
            }
        }
    }

    public final void openEssentials(String section) {
        Intrinsics.checkNotNullParameter(section, "section");
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        while (true) {
            UiState value = mutableStateFlow.getValue();
            UiState it = value;
            SettingsUiModel settings = it.getSettings();
            MutableStateFlow<UiState> mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : SettingsPane.Essentials, (392319 & 256) != 0 ? settings.essentialsSection : section, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null))) {
                return;
            } else {
                mutableStateFlow = mutableStateFlow2;
            }
        }
    }

    public final void openAdvancedIndex() {
        UiState value;
        UiState it;
        SettingsUiModel settings;
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            it = value;
            settings = it.getSettings();
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : SettingsPane.AdvancedIndex, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
    }

    public final void openAdvancedCategory(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        if (StringsKt.equals(id, "moa", true)) {
            openMoaEditor();
            return;
        }
        if (StringsKt.equals(id, "voice", true)) {
            openAssistantSettings();
            return;
        }
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        while (true) {
            UiState value = mutableStateFlow.getValue();
            UiState it = value;
            SettingsUiModel settings = it.getSettings();
            MutableStateFlow<UiState> mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : SettingsPane.AdvancedCategory, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : id, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null))) {
                return;
            } else {
                mutableStateFlow = mutableStateFlow2;
            }
        }
    }

    public final void openAccount() {
        UiState value;
        UiState it;
        SettingsUiModel settings;
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            it = value;
            settings = it.getSettings();
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : SettingsPane.Account, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
    }

    public final void openMoaEditor() {
        UiState value;
        UiState it;
        SettingsUiModel settings;
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            it = value;
            settings = it.getSettings();
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : SettingsPane.MoaEditor, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01912(null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$openMoaEditor$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$openMoaEditor$2", f = "MainViewModel.kt", i = {}, l = {1139}, m = "invokeSuspend", n = {}, s = {})
    final class C01912 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        C01912(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01912(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01912)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object $result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    Object objLoadMoa = MainViewModel.this.settingsRepository.loadMoa(true, this);
                    if (objLoadMoa == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objLoadMoa;
                    break;
                    break;
                case 1:
                    $result = obj;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            MoaSnapshot moa = (MoaSnapshot) $result;
            MutableStateFlow $this$update$iv = MainViewModel.this._uiState;
            while (true) {
                Object prevValue$iv = $this$update$iv.getValue();
                UiState st = (UiState) prevValue$iv;
                SettingsUiModel settings = st.getSettings();
                MutableStateFlow $this$update$iv2 = $this$update$iv;
                if ($this$update$iv2.compareAndSet(prevValue$iv, UiState.copy$default(st, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : moa, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : moa.getError(), (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null))) {
                    Object nextValue$iv = Unit.INSTANCE;
                    return nextValue$iv;
                }
                $this$update$iv = $this$update$iv2;
            }
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$openAssistantSettings$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$openAssistantSettings$1", f = "MainViewModel.kt", i = {}, l = {1148}, m = "invokeSuspend", n = {}, s = {})
    final class C01901 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        C01901(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C01901(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01901)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object $result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    Object objLoadAssistSettings = MainViewModel.this.loadAssistSettings(this);
                    if (objLoadAssistSettings == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objLoadAssistSettings;
                    break;
                case 1:
                    $result = obj;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AssistSettingsUi assist = (AssistSettingsUi) $result;
            MutableStateFlow $this$update$iv = MainViewModel.this._uiState;
            while (true) {
                Object prevValue$iv = $this$update$iv.getValue();
                UiState it = (UiState) prevValue$iv;
                SettingsUiModel settings = it.getSettings();
                MutableStateFlow $this$update$iv2 = $this$update$iv;
                if ($this$update$iv2.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : SettingsPane.Assistant, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : assist, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null))) {
                    Object nextValue$iv = Unit.INSTANCE;
                    return nextValue$iv;
                }
                $this$update$iv = $this$update$iv2;
            }
        }
    }

    public final void openAssistantSettings() {
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01901(null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$setAssistSpeakReplies$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$setAssistSpeakReplies$1", f = "MainViewModel.kt", i = {}, l = {1164}, m = "invokeSuspend", n = {}, s = {})
    final class C02041 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ boolean $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02041(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$value = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C02041(this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02041)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object prevValue$iv;
            UiState it;
            SettingsUiModel settings;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.credentialStore.setAssistSpeakReplies(this.$value, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            MutableStateFlow $this$update$iv = MainViewModel.this._uiState;
            boolean z = this.$value;
            do {
                prevValue$iv = $this$update$iv.getValue();
                it = (UiState) prevValue$iv;
                settings = it.getSettings();
            } while (!$this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : AssistSettingsUi.copy$default(it.getSettings().getAssist(), z, false, null, 6, null), (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
            return Unit.INSTANCE;
        }
    }

    public final void setAssistSpeakReplies(boolean value) {
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C02041(value, null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$setAssistAutoListen$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$setAssistAutoListen$1", f = "MainViewModel.kt", i = {}, l = {1173}, m = "invokeSuspend", n = {}, s = {})
    final class C02021 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ boolean $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02021(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$value = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C02021(this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02021)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object prevValue$iv;
            UiState it;
            SettingsUiModel settings;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.credentialStore.setAssistAutoListen(this.$value, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            MutableStateFlow $this$update$iv = MainViewModel.this._uiState;
            boolean z = this.$value;
            do {
                prevValue$iv = $this$update$iv.getValue();
                it = (UiState) prevValue$iv;
                settings = it.getSettings();
            } while (!$this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : AssistSettingsUi.copy$default(it.getSettings().getAssist(), false, z, null, 5, null), (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
            return Unit.INSTANCE;
        }
    }

    public final void setAssistAutoListen(boolean value) {
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C02021(value, null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$setAssistOrbStyle$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$setAssistOrbStyle$1", f = "MainViewModel.kt", i = {}, l = {1183}, m = "invokeSuspend", n = {}, s = {})
    final class C02031 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02031(String str, Continuation continuation) {
            super(2, continuation);
            this.$value = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C02031(this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02031)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object prevValue$iv;
            UiState it;
            SettingsUiModel settings;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.credentialStore.setAssistOrbStyle(this.$value, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            MutableStateFlow $this$update$iv = MainViewModel.this._uiState;
            String str = this.$value;
            do {
                prevValue$iv = $this$update$iv.getValue();
                it = (UiState) prevValue$iv;
                settings = it.getSettings();
            } while (!$this$update$iv.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : AssistSettingsUi.copy$default(it.getSettings().getAssist(), false, false, str, 3, null), (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
            return Unit.INSTANCE;
        }
    }

    public final void setAssistOrbStyle(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C02031(value, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x0070 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:21:0x0071  */
    /* JADX WARN: Code duplicated, block: B:24:0x008c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x008d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object loadAssistSettings(Continuation continuation) {
        C01881 c01881;
        MainViewModel mainViewModel;
        Object assistSpeakReplies;
        boolean zBooleanValue;
        Object assistAutoListen;
        MainViewModel mainViewModel2;
        boolean z;
        boolean zBooleanValue2;
        Object assistOrbStyle;
        boolean z2;
        if (continuation instanceof C01881) {
            c01881 = (C01881) continuation;
            if ((c01881.label & Integer.MIN_VALUE) != 0) {
                c01881.label -= Integer.MIN_VALUE;
            } else {
                c01881 = new C01881(continuation);
            }
        } else {
            c01881 = new C01881(continuation);
        }
        Object $result = c01881.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c01881.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mainViewModel = this;
                AuroraCredentialStore auroraCredentialStore = mainViewModel.credentialStore;
                c01881.L$0 = mainViewModel;
                c01881.label = 1;
                assistSpeakReplies = auroraCredentialStore.getAssistSpeakReplies(c01881);
                if (assistSpeakReplies == coroutine_suspended) {
                    return coroutine_suspended;
                }
                zBooleanValue = ((Boolean) assistSpeakReplies).booleanValue();
                AuroraCredentialStore auroraCredentialStore2 = mainViewModel.credentialStore;
                c01881.L$0 = mainViewModel;
                c01881.Z$0 = zBooleanValue;
                c01881.label = 2;
                assistAutoListen = auroraCredentialStore2.getAssistAutoListen(c01881);
                if (assistAutoListen == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mainViewModel2 = mainViewModel;
                z = zBooleanValue;
                zBooleanValue2 = ((Boolean) assistAutoListen).booleanValue();
                AuroraCredentialStore auroraCredentialStore3 = mainViewModel2.credentialStore;
                c01881.L$0 = null;
                c01881.Z$0 = z;
                c01881.Z$1 = zBooleanValue2;
                c01881.label = 3;
                assistOrbStyle = auroraCredentialStore3.getAssistOrbStyle(c01881);
                if (assistOrbStyle == coroutine_suspended) {
                    return coroutine_suspended;
                }
                z2 = zBooleanValue2;
                return new AssistSettingsUi(z, z2, (String) assistOrbStyle);
            case 1:
                mainViewModel = (MainViewModel) c01881.L$0;
                ResultKt.throwOnFailure($result);
                assistSpeakReplies = $result;
                zBooleanValue = ((Boolean) assistSpeakReplies).booleanValue();
                AuroraCredentialStore auroraCredentialStore4 = mainViewModel.credentialStore;
                c01881.L$0 = mainViewModel;
                c01881.Z$0 = zBooleanValue;
                c01881.label = 2;
                assistAutoListen = auroraCredentialStore4.getAssistAutoListen(c01881);
                if (assistAutoListen == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mainViewModel2 = mainViewModel;
                z = zBooleanValue;
                zBooleanValue2 = ((Boolean) assistAutoListen).booleanValue();
                AuroraCredentialStore auroraCredentialStore5 = mainViewModel2.credentialStore;
                c01881.L$0 = null;
                c01881.Z$0 = z;
                c01881.Z$1 = zBooleanValue2;
                c01881.label = 3;
                assistOrbStyle = auroraCredentialStore5.getAssistOrbStyle(c01881);
                if (assistOrbStyle == coroutine_suspended) {
                    return coroutine_suspended;
                }
                z2 = zBooleanValue2;
                return new AssistSettingsUi(z, z2, (String) assistOrbStyle);
            case 2:
                z = c01881.Z$0;
                mainViewModel2 = (MainViewModel) c01881.L$0;
                ResultKt.throwOnFailure($result);
                assistAutoListen = $result;
                zBooleanValue2 = ((Boolean) assistAutoListen).booleanValue();
                AuroraCredentialStore auroraCredentialStore6 = mainViewModel2.credentialStore;
                c01881.L$0 = null;
                c01881.Z$0 = z;
                c01881.Z$1 = zBooleanValue2;
                c01881.label = 3;
                assistOrbStyle = auroraCredentialStore6.getAssistOrbStyle(c01881);
                if (assistOrbStyle == coroutine_suspended) {
                    return coroutine_suspended;
                }
                z2 = zBooleanValue2;
                return new AssistSettingsUi(z, z2, (String) assistOrbStyle);
            case 3:
                z2 = c01881.Z$1;
                z = c01881.Z$0;
                ResultKt.throwOnFailure($result);
                assistOrbStyle = $result;
                return new AssistSettingsUi(z, z2, (String) assistOrbStyle);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$saveMoa$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$saveMoa$1", f = "MainViewModel.kt", i = {0, 1}, l = {1201, 1214, 1216, 1230}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"})
    final class C01981 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ MoaConfigDto $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01981(MoaConfigDto moaConfigDto, Continuation continuation) {
            super(2, continuation);
            this.$body = moaConfigDto;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C01981 c01981 = MainViewModel.this.new C01981(this.$body, continuation);
            c01981.L$0 = obj;
            return c01981;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01981)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:23:0x00e6  */
        /* JADX WARN: Code duplicated, block: B:28:0x0193 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:29:0x0194  */
        /* JADX WARN: Code duplicated, block: B:33:0x01b3 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:34:0x01b4  */
        /* JADX WARN: Code duplicated, block: B:42:0x01d5  */
        /* JADX WARN: Code duplicated, block: B:45:0x01db  */
        /* JADX WARN: Code duplicated, block: B:47:0x01e4 A[LOOP:1: B:24:0x00f1->B:47:0x01e4, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:48:0x01e9 A[PHI: r6
  0x01e9: PHI (r6v2 java.lang.Object) = (r6v4 java.lang.Object), (r6v10 java.lang.Object) binds: [B:46:0x01e1, B:22:0x00e4] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:50:0x01f2  */
        /* JADX WARN: Code duplicated, block: B:55:0x026e  */
        /* JADX WARN: Code duplicated, block: B:58:0x0283 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:59:0x0284  */
        /* JADX WARN: Code duplicated, block: B:69:0x0179 A[SYNTHETIC] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v1 */
        /* JADX WARN: Type inference failed for: r3v16 */
        /* JADX WARN: Type inference failed for: r3v17, types: [ai.nous.aurora.data.repository.HermesSettingsSnapshot, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v2 */
        /* JADX WARN: Type inference failed for: r4v1 */
        /* JADX WARN: Type inference failed for: r4v15 */
        /* JADX WARN: Type inference failed for: r4v7 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM7388constructorimpl;
            Object r4;
            Object objMo59saveMoagIAlus;
            MainViewModel mainViewModel;
            Throwable thM7391exceptionOrNullimpl;
            MutableStateFlow mutableStateFlow;
            Object value;
            UiState uiState;
            SettingsUiModel settings;
            MutableSharedFlow mutableSharedFlow;
            ToastEvent.Error error;
            String message;
            HermesSettingsSnapshot hermesSettingsSnapshot;
            Object obj2;
            Object value2;
            UiState uiState2;
            SettingsUiModel settings2;
            CoroutineScope coroutineScope;
            MainViewModel mainViewModel2;
            MoaSnapshot moaSnapshot;
            MutableStateFlow mutableStateFlow2;
            Object value3;
            UiState uiState3;
            SettingsUiModel settings3;
            MutableStateFlow mutableStateFlow3;
            MutableSharedFlow mutableSharedFlow2;
            ToastEvent.Info info;
            Object obj3;
            Object obj4;
            MainViewModel mainViewModel3;
            Object objLoad;
            Object obj5;
            Object obj6;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            Object r3 = null;
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        obj2 = obj;
                        CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                        MutableStateFlow mutableStateFlow4 = MainViewModel.this._uiState;
                        do {
                            value2 = mutableStateFlow4.getValue();
                            uiState2 = (UiState) value2;
                            settings2 = uiState2.getSettings();
                        } while (!mutableStateFlow4.compareAndSet(value2, UiState.copy$default(uiState2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings2.copy((392319 & 1) != 0 ? settings2.loading : false, (392319 & 2) != 0 ? settings2.error : null, (392319 & 4) != 0 ? settings2.bridgeOnline : false, (392319 & 8) != 0 ? settings2.categories : null, (392319 & 16) != 0 ? settings2.fields : null, (392319 & 32) != 0 ? settings2.config : null, (392319 & 64) != 0 ? settings2.search : null, (392319 & 128) != 0 ? settings2.pane : null, (392319 & 256) != 0 ? settings2.essentialsSection : null, (392319 & 512) != 0 ? settings2.selectedCategory : null, (392319 & 1024) != 0 ? settings2.savingPath : null, (392319 & 2048) != 0 ? settings2.modelLabel : null, (392319 & 4096) != 0 ? settings2.lastSavedPath : null, (392319 & 8192) != 0 ? settings2.moa : null, (392319 & 16384) != 0 ? settings2.moaSaving : true, (392319 & 32768) != 0 ? settings2.moaError : null, (392319 & 65536) != 0 ? settings2.moaLastSaved : false, (392319 & 131072) != 0 ? settings2.assist : null, (392319 & 262144) != 0 ? settings2.moaDirty : false), null, 50331647, null)));
                        this.L$0 = coroutineScope2;
                        this.label = 1;
                        objMo59saveMoagIAlus = MainViewModel.this.settingsRepository.mo59saveMoagIAlus(this.$body, this);
                        if (objMo59saveMoagIAlus == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        coroutineScope = coroutineScope2;
                        mainViewModel2 = MainViewModel.this;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objMo59saveMoagIAlus)) {
                            moaSnapshot = (MoaSnapshot) objMo59saveMoagIAlus;
                            mutableStateFlow2 = mainViewModel2._uiState;
                            while (true) {
                                value3 = mutableStateFlow2.getValue();
                                uiState3 = (UiState) value3;
                                settings3 = uiState3.getSettings();
                                mutableStateFlow3 = mutableStateFlow2;
                                if (mutableStateFlow3.compareAndSet(value3, UiState.copy$default(uiState3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings3.copy((392319 & 1) != 0 ? settings3.loading : false, (392319 & 2) != 0 ? settings3.error : null, (392319 & 4) != 0 ? settings3.bridgeOnline : false, (392319 & 8) != 0 ? settings3.categories : null, (392319 & 16) != 0 ? settings3.fields : null, (392319 & 32) != 0 ? settings3.config : null, (392319 & 64) != 0 ? settings3.search : null, (392319 & 128) != 0 ? settings3.pane : null, (392319 & 256) != 0 ? settings3.essentialsSection : null, (392319 & 512) != 0 ? settings3.selectedCategory : null, (392319 & 1024) != 0 ? settings3.savingPath : null, (392319 & 2048) != 0 ? settings3.modelLabel : null, (392319 & 4096) != 0 ? settings3.lastSavedPath : "moa", (392319 & 8192) != 0 ? settings3.moa : moaSnapshot, (392319 & 16384) != 0 ? settings3.moaSaving : false, (392319 & 32768) != 0 ? settings3.moaError : null, (392319 & 65536) != 0 ? settings3.moaLastSaved : true, (392319 & 131072) != 0 ? settings3.assist : null, (392319 & 262144) != 0 ? settings3.moaDirty : false), null, 50331647, null))) {
                                    mutableSharedFlow2 = mainViewModel2._toasts;
                                    info = new ToastEvent.Info("Saved · Mixture of Agents");
                                    this.L$0 = coroutineScope;
                                    this.L$1 = objMo59saveMoagIAlus;
                                    this.L$2 = mainViewModel2;
                                    this.label = 2;
                                    if (mutableSharedFlow2.emit(info, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    obj3 = obj2;
                                    obj4 = objMo59saveMoagIAlus;
                                    mainViewModel3 = mainViewModel2;
                                    Result.Companion companion = Result.Companion;
                                    SettingsRepository settingsRepository = mainViewModel3.settingsRepository;
                                    this.L$0 = obj4;
                                    this.L$1 = mainViewModel3;
                                    this.L$2 = null;
                                    this.label = 3;
                                    objLoad = settingsRepository.load(true, this);
                                    if (objLoad == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    obj5 = obj3;
                                    obj6 = objLoad;
                                    try {
                                        r3 = (HermesSettingsSnapshot) obj6;
                                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(r3);
                                        r4 = obj5;
                                    } catch (Throwable th) {
                                        r3 = obj5;
                                        Result.Companion companion2 = Result.Companion;
                                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                                        r4 = r3;
                                    }
                                    if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                                        objM7388constructorimpl = null;
                                    }
                                    hermesSettingsSnapshot = (HermesSettingsSnapshot) objM7388constructorimpl;
                                    if (hermesSettingsSnapshot != null) {
                                        mainViewModel3.applySettingsSnapshot(hermesSettingsSnapshot, false);
                                    }
                                    objMo59saveMoagIAlus = obj4;
                                    mainViewModel = MainViewModel.this;
                                    thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo59saveMoagIAlus);
                                    if (thM7391exceptionOrNullimpl != null) {
                                        mutableStateFlow = mainViewModel._uiState;
                                        do {
                                            value = mutableStateFlow.getValue();
                                            uiState = (UiState) value;
                                            settings = uiState.getSettings();
                                        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : thM7391exceptionOrNullimpl.getMessage(), (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
                                        mutableSharedFlow = mainViewModel._toasts;
                                        message = thM7391exceptionOrNullimpl.getMessage();
                                        if (message == null) {
                                            message = "MoA save failed";
                                        }
                                        error = new ToastEvent.Error(message);
                                        this.L$0 = objMo59saveMoagIAlus;
                                        this.L$1 = null;
                                        this.L$2 = null;
                                        this.label = 4;
                                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                } else {
                                    mutableStateFlow2 = mutableStateFlow3;
                                }
                            }
                        } else {
                            mainViewModel = MainViewModel.this;
                            thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo59saveMoagIAlus);
                            if (thM7391exceptionOrNullimpl != null) {
                                mutableStateFlow = mainViewModel._uiState;
                                do {
                                    value = mutableStateFlow.getValue();
                                    uiState = (UiState) value;
                                    settings = uiState.getSettings();
                                } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : thM7391exceptionOrNullimpl.getMessage(), (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
                                mutableSharedFlow = mainViewModel._toasts;
                                message = thM7391exceptionOrNullimpl.getMessage();
                                if (message == null) {
                                    message = "MoA save failed";
                                }
                                error = new ToastEvent.Error(message);
                                this.L$0 = objMo59saveMoagIAlus;
                                this.L$1 = null;
                                this.L$2 = null;
                                this.label = 4;
                                if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    case 1:
                        obj2 = obj;
                        CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj2);
                        objMo59saveMoagIAlus = ai.nous.aurora.compat.Compat.resultUnbox(obj2);
                        coroutineScope = coroutineScope3;
                        mainViewModel2 = MainViewModel.this;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(objMo59saveMoagIAlus)) {
                            moaSnapshot = (MoaSnapshot) objMo59saveMoagIAlus;
                            mutableStateFlow2 = mainViewModel2._uiState;
                            while (true) {
                                value3 = mutableStateFlow2.getValue();
                                uiState3 = (UiState) value3;
                                settings3 = uiState3.getSettings();
                                mutableStateFlow3 = mutableStateFlow2;
                                if (mutableStateFlow3.compareAndSet(value3, UiState.copy$default(uiState3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings3.copy((392319 & 1) != 0 ? settings3.loading : false, (392319 & 2) != 0 ? settings3.error : null, (392319 & 4) != 0 ? settings3.bridgeOnline : false, (392319 & 8) != 0 ? settings3.categories : null, (392319 & 16) != 0 ? settings3.fields : null, (392319 & 32) != 0 ? settings3.config : null, (392319 & 64) != 0 ? settings3.search : null, (392319 & 128) != 0 ? settings3.pane : null, (392319 & 256) != 0 ? settings3.essentialsSection : null, (392319 & 512) != 0 ? settings3.selectedCategory : null, (392319 & 1024) != 0 ? settings3.savingPath : null, (392319 & 2048) != 0 ? settings3.modelLabel : null, (392319 & 4096) != 0 ? settings3.lastSavedPath : "moa", (392319 & 8192) != 0 ? settings3.moa : moaSnapshot, (392319 & 16384) != 0 ? settings3.moaSaving : false, (392319 & 32768) != 0 ? settings3.moaError : null, (392319 & 65536) != 0 ? settings3.moaLastSaved : true, (392319 & 131072) != 0 ? settings3.assist : null, (392319 & 262144) != 0 ? settings3.moaDirty : false), null, 50331647, null))) {
                                    mutableSharedFlow2 = mainViewModel2._toasts;
                                    info = new ToastEvent.Info("Saved · Mixture of Agents");
                                    this.L$0 = coroutineScope;
                                    this.L$1 = objMo59saveMoagIAlus;
                                    this.L$2 = mainViewModel2;
                                    this.label = 2;
                                    if (mutableSharedFlow2.emit(info, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    obj3 = obj2;
                                    obj4 = objMo59saveMoagIAlus;
                                    mainViewModel3 = mainViewModel2;
                                    Result.Companion companion3 = Result.Companion;
                                    SettingsRepository settingsRepository2 = mainViewModel3.settingsRepository;
                                    this.L$0 = obj4;
                                    this.L$1 = mainViewModel3;
                                    this.L$2 = null;
                                    this.label = 3;
                                    objLoad = settingsRepository2.load(true, this);
                                    if (objLoad == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    obj5 = obj3;
                                    obj6 = objLoad;
                                    r3 = (HermesSettingsSnapshot) obj6;
                                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(r3);
                                    r4 = obj5;
                                    if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                                        objM7388constructorimpl = null;
                                    }
                                    hermesSettingsSnapshot = (HermesSettingsSnapshot) objM7388constructorimpl;
                                    if (hermesSettingsSnapshot != null) {
                                        mainViewModel3.applySettingsSnapshot(hermesSettingsSnapshot, false);
                                    }
                                    objMo59saveMoagIAlus = obj4;
                                    mainViewModel = MainViewModel.this;
                                    thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo59saveMoagIAlus);
                                    if (thM7391exceptionOrNullimpl != null) {
                                        mutableStateFlow = mainViewModel._uiState;
                                        do {
                                            value = mutableStateFlow.getValue();
                                            uiState = (UiState) value;
                                            settings = uiState.getSettings();
                                        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : thM7391exceptionOrNullimpl.getMessage(), (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
                                        mutableSharedFlow = mainViewModel._toasts;
                                        message = thM7391exceptionOrNullimpl.getMessage();
                                        if (message == null) {
                                            message = "MoA save failed";
                                        }
                                        error = new ToastEvent.Error(message);
                                        this.L$0 = objMo59saveMoagIAlus;
                                        this.L$1 = null;
                                        this.L$2 = null;
                                        this.label = 4;
                                        if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                } else {
                                    mutableStateFlow2 = mutableStateFlow3;
                                }
                            }
                        } else {
                            mainViewModel = MainViewModel.this;
                            thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo59saveMoagIAlus);
                            if (thM7391exceptionOrNullimpl != null) {
                                mutableStateFlow = mainViewModel._uiState;
                                do {
                                    value = mutableStateFlow.getValue();
                                    uiState = (UiState) value;
                                    settings = uiState.getSettings();
                                } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : thM7391exceptionOrNullimpl.getMessage(), (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
                                mutableSharedFlow = mainViewModel._toasts;
                                message = thM7391exceptionOrNullimpl.getMessage();
                                if (message == null) {
                                    message = "MoA save failed";
                                }
                                error = new ToastEvent.Error(message);
                                this.L$0 = objMo59saveMoagIAlus;
                                this.L$1 = null;
                                this.L$2 = null;
                                this.label = 4;
                                if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    case 2:
                        mainViewModel3 = (MainViewModel) this.L$2;
                        obj4 = this.L$1;
                        ResultKt.throwOnFailure(obj);
                        obj3 = obj;
                        Result.Companion companion4 = Result.Companion;
                        SettingsRepository settingsRepository3 = mainViewModel3.settingsRepository;
                        this.L$0 = obj4;
                        this.L$1 = mainViewModel3;
                        this.L$2 = null;
                        this.label = 3;
                        objLoad = settingsRepository3.load(true, this);
                        if (objLoad == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj5 = obj3;
                        obj6 = objLoad;
                        r3 = (HermesSettingsSnapshot) obj6;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(r3);
                        r4 = obj5;
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = null;
                        }
                        hermesSettingsSnapshot = (HermesSettingsSnapshot) objM7388constructorimpl;
                        if (hermesSettingsSnapshot != null) {
                            mainViewModel3.applySettingsSnapshot(hermesSettingsSnapshot, false);
                        }
                        objMo59saveMoagIAlus = obj4;
                        mainViewModel = MainViewModel.this;
                        thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo59saveMoagIAlus);
                        if (thM7391exceptionOrNullimpl != null) {
                            mutableStateFlow = mainViewModel._uiState;
                            do {
                                value = mutableStateFlow.getValue();
                                uiState = (UiState) value;
                                settings = uiState.getSettings();
                            } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : thM7391exceptionOrNullimpl.getMessage(), (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
                            mutableSharedFlow = mainViewModel._toasts;
                            message = thM7391exceptionOrNullimpl.getMessage();
                            if (message == null) {
                                message = "MoA save failed";
                            }
                            error = new ToastEvent.Error(message);
                            this.L$0 = objMo59saveMoagIAlus;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 4;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        return Unit.INSTANCE;
                    case 3:
                        obj6 = obj;
                        mainViewModel3 = (MainViewModel) this.L$1;
                        obj4 = this.L$0;
                        ResultKt.throwOnFailure(obj6);
                        obj5 = obj6;
                        r3 = (HermesSettingsSnapshot) obj6;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(r3);
                        r4 = obj5;
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = null;
                        }
                        hermesSettingsSnapshot = (HermesSettingsSnapshot) objM7388constructorimpl;
                        if (hermesSettingsSnapshot != null) {
                            mainViewModel3.applySettingsSnapshot(hermesSettingsSnapshot, false);
                        }
                        objMo59saveMoagIAlus = obj4;
                        mainViewModel = MainViewModel.this;
                        thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objMo59saveMoagIAlus);
                        if (thM7391exceptionOrNullimpl != null) {
                            mutableStateFlow = mainViewModel._uiState;
                            do {
                                value = mutableStateFlow.getValue();
                                uiState = (UiState) value;
                                settings = uiState.getSettings();
                            } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(uiState, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : thM7391exceptionOrNullimpl.getMessage(), (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
                            mutableSharedFlow = mainViewModel._toasts;
                            message = thM7391exceptionOrNullimpl.getMessage();
                            if (message == null) {
                                message = "MoA save failed";
                            }
                            error = new ToastEvent.Error(message);
                            this.L$0 = objMo59saveMoagIAlus;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 4;
                            if (mutableSharedFlow.emit(error, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        return Unit.INSTANCE;
                    case 4:
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th2) {
            }
        }
    }

    public final void saveMoa(MoaConfigDto body) {
        Intrinsics.checkNotNullParameter(body, "body");
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01981(body, null), 3, null);
    }

    public final void backSettings() {
        UiState value;
        UiState st;
        SettingsUiModel next;
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        do {
            value = mutableStateFlow.getValue();
            st = value;
            SettingsUiModel s = st.getSettings();
            switch (WhenMappings.$EnumSwitchMapping$1[s.getPane().ordinal()]) {
                case 1:
                    next = s.copy((392319 & 1) != 0 ? s.loading : false, (392319 & 2) != 0 ? s.error : null, (392319 & 4) != 0 ? s.bridgeOnline : false, (392319 & 8) != 0 ? s.categories : null, (392319 & 16) != 0 ? s.fields : null, (392319 & 32) != 0 ? s.config : null, (392319 & 64) != 0 ? s.search : null, (392319 & 128) != 0 ? s.pane : SettingsPane.AdvancedIndex, (392319 & 256) != 0 ? s.essentialsSection : null, (392319 & 512) != 0 ? s.selectedCategory : null, (392319 & 1024) != 0 ? s.savingPath : null, (392319 & 2048) != 0 ? s.modelLabel : null, (392319 & 4096) != 0 ? s.lastSavedPath : null, (392319 & 8192) != 0 ? s.moa : null, (392319 & 16384) != 0 ? s.moaSaving : false, (392319 & 32768) != 0 ? s.moaError : null, (392319 & 65536) != 0 ? s.moaLastSaved : false, (392319 & 131072) != 0 ? s.assist : null, (392319 & 262144) != 0 ? s.moaDirty : false);
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    next = s.copy((392319 & 1) != 0 ? s.loading : false, (392319 & 2) != 0 ? s.error : null, (392319 & 4) != 0 ? s.bridgeOnline : false, (392319 & 8) != 0 ? s.categories : null, (392319 & 16) != 0 ? s.fields : null, (392319 & 32) != 0 ? s.config : null, (392319 & 64) != 0 ? s.search : "", (392319 & 128) != 0 ? s.pane : SettingsPane.Home, (392319 & 256) != 0 ? s.essentialsSection : null, (392319 & 512) != 0 ? s.selectedCategory : null, (392319 & 1024) != 0 ? s.savingPath : null, (392319 & 2048) != 0 ? s.modelLabel : null, (392319 & 4096) != 0 ? s.lastSavedPath : null, (392319 & 8192) != 0 ? s.moa : null, (392319 & 16384) != 0 ? s.moaSaving : false, (392319 & 32768) != 0 ? s.moaError : null, (392319 & 65536) != 0 ? s.moaLastSaved : false, (392319 & 131072) != 0 ? s.assist : null, (392319 & 262144) != 0 ? s.moaDirty : false);
                    break;
                case 7:
                    next = s;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } while (!mutableStateFlow.compareAndSet(value, UiState.copy$default(st, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, next, null, 50331647, null)));
    }

    public static /* synthetic */ void refreshSettings$default(MainViewModel mainViewModel, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        mainViewModel.refreshSettings(z, z2);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$refreshSettings$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$refreshSettings$1", f = "MainViewModel.kt", i = {}, l = {1265, 1279}, m = "invokeSuspend", n = {}, s = {})
    final class C01941 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ boolean $forceNetwork;
        final /* synthetic */ boolean $paintCacheFirst;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01941(boolean z, boolean z2, Continuation continuation) {
            super(2, continuation);
            this.$paintCacheFirst = z;
            this.$forceNetwork = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C01941 c01941 = MainViewModel.this.new C01941(this.$paintCacheFirst, this.$forceNetwork, continuation);
            c01941.L$0 = obj;
            return c01941;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01941)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:23:0x0067  */
        /* JADX WARN: Code duplicated, block: B:29:0x00f6  */
        /* JADX WARN: Code duplicated, block: B:36:0x011b  */
        /* JADX WARN: Code duplicated, block: B:38:0x0135  */
        /* JADX WARN: Code duplicated, block: B:42:0x019a  */
        /* JADX WARN: Code duplicated, block: B:44:0x019e  */
        /* JADX WARN: Code duplicated, block: B:49:0x0206  */
        /* JADX WARN: Code duplicated, block: B:51:0x0219 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:55:0x0225 A[LOOP:0: B:25:0x0072->B:55:0x0225, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:59:0x00f2 A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objM7388constructorimpl;
            AssistSettingsUi assistSettingsUi;
            AssistSettingsUi assist;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv;
            UiState it;
            SettingsUiModel settings;
            MutableStateFlow $this$update$iv2;
            MutableStateFlow $this$update$iv3;
            Object prevValue$iv2;
            UiState it2;
            SettingsUiModel settings2;
            Object $result2;
            HermesSettingsSnapshot cached;
            MutableStateFlow $this$update$iv4;
            Object prevValue$iv3;
            UiState it3;
            SettingsUiModel settings3;
            Object $result3;
            Object $result4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        MainViewModel mainViewModel = MainViewModel.this;
                        Result.Companion companion = Result.Companion;
                        this.label = 1;
                        Object objLoadAssistSettings = mainViewModel.loadAssistSettings(this);
                        if (objLoadAssistSettings == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result3 = $result;
                        $result4 = objLoadAssistSettings;
                        try {
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((AssistSettingsUi) $result4);
                            while (true) {
                                prevValue$iv = $this$update$iv.getValue();
                                it = (UiState) prevValue$iv;
                                settings = it.getSettings();
                                $this$update$iv2 = $this$update$iv;
                                if ($this$update$iv2.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : assist, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null))) {
                                    if (this.$paintCacheFirst) {
                                        cached = MainViewModel.this.settingsRepository.peekCached();
                                        if (cached != null || (cached.getValues().isEmpty() && !cached.getRemoteAvailable())) {
                                            if (((UiState) MainViewModel.this._uiState.getValue()).getSettings().getConfig().isEmpty()) {
                                                $this$update$iv4 = MainViewModel.this._uiState;
                                                do {
                                                    prevValue$iv3 = $this$update$iv4.getValue();
                                                    it3 = (UiState) prevValue$iv3;
                                                    settings3 = it3.getSettings();
                                                } while (!$this$update$iv4.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings3.copy((392319 & 1) != 0 ? settings3.loading : true, (392319 & 2) != 0 ? settings3.error : null, (392319 & 4) != 0 ? settings3.bridgeOnline : false, (392319 & 8) != 0 ? settings3.categories : null, (392319 & 16) != 0 ? settings3.fields : null, (392319 & 32) != 0 ? settings3.config : null, (392319 & 64) != 0 ? settings3.search : null, (392319 & 128) != 0 ? settings3.pane : null, (392319 & 256) != 0 ? settings3.essentialsSection : null, (392319 & 512) != 0 ? settings3.selectedCategory : null, (392319 & 1024) != 0 ? settings3.savingPath : null, (392319 & 2048) != 0 ? settings3.modelLabel : null, (392319 & 4096) != 0 ? settings3.lastSavedPath : null, (392319 & 8192) != 0 ? settings3.moa : null, (392319 & 16384) != 0 ? settings3.moaSaving : false, (392319 & 32768) != 0 ? settings3.moaError : null, (392319 & 65536) != 0 ? settings3.moaLastSaved : false, (392319 & 131072) != 0 ? settings3.assist : null, (392319 & 262144) != 0 ? settings3.moaDirty : false), null, 50331647, null)));
                                            }
                                        } else {
                                            MainViewModel.this.applySettingsSnapshot(cached, this.$forceNetwork);
                                        }
                                    } else if (this.$forceNetwork) {
                                        $this$update$iv3 = MainViewModel.this._uiState;
                                        do {
                                            prevValue$iv2 = $this$update$iv3.getValue();
                                            it2 = (UiState) prevValue$iv2;
                                            settings2 = it2.getSettings();
                                        } while (!$this$update$iv3.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings2.copy((392319 & 1) != 0 ? settings2.loading : true, (392319 & 2) != 0 ? settings2.error : null, (392319 & 4) != 0 ? settings2.bridgeOnline : false, (392319 & 8) != 0 ? settings2.categories : null, (392319 & 16) != 0 ? settings2.fields : null, (392319 & 32) != 0 ? settings2.config : null, (392319 & 64) != 0 ? settings2.search : null, (392319 & 128) != 0 ? settings2.pane : null, (392319 & 256) != 0 ? settings2.essentialsSection : null, (392319 & 512) != 0 ? settings2.selectedCategory : null, (392319 & 1024) != 0 ? settings2.savingPath : null, (392319 & 2048) != 0 ? settings2.modelLabel : null, (392319 & 4096) != 0 ? settings2.lastSavedPath : null, (392319 & 8192) != 0 ? settings2.moa : null, (392319 & 16384) != 0 ? settings2.moaSaving : false, (392319 & 32768) != 0 ? settings2.moaError : null, (392319 & 65536) != 0 ? settings2.moaLastSaved : false, (392319 & 131072) != 0 ? settings2.assist : null, (392319 & 262144) != 0 ? settings2.moaDirty : false), null, 50331647, null)));
                                    }
                                    if (this.$forceNetwork) {
                                        this.label = 2;
                                        $result2 = MainViewModel.this.settingsRepository.load(true, this);
                                        if ($result2 == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        HermesSettingsSnapshot snapshot = (HermesSettingsSnapshot) $result2;
                                        MainViewModel.this.applySettingsSnapshot(snapshot, false);
                                    }
                                    return Unit.INSTANCE;
                                }
                                $this$update$iv = $this$update$iv2;
                            }
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                        }
                        assistSettingsUi = new AssistSettingsUi(false, false, null, 7, null);
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = assistSettingsUi;
                        }
                        assist = (AssistSettingsUi) objM7388constructorimpl;
                        $this$update$iv = MainViewModel.this._uiState;
                        break;
                    case 1:
                        $result4 = $result;
                        ResultKt.throwOnFailure($result4);
                        $result3 = $result4;
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((AssistSettingsUi) $result4);
                        assistSettingsUi = new AssistSettingsUi(false, false, null, 7, null);
                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl)) {
                            objM7388constructorimpl = assistSettingsUi;
                        }
                        assist = (AssistSettingsUi) objM7388constructorimpl;
                        $this$update$iv = MainViewModel.this._uiState;
                        while (true) {
                            prevValue$iv = $this$update$iv.getValue();
                            it = (UiState) prevValue$iv;
                            settings = it.getSettings();
                            $this$update$iv2 = $this$update$iv;
                            if ($this$update$iv2.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : assist, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null))) {
                                if (this.$paintCacheFirst) {
                                    cached = MainViewModel.this.settingsRepository.peekCached();
                                    if (cached != null) {
                                        if (((UiState) MainViewModel.this._uiState.getValue()).getSettings().getConfig().isEmpty()) {
                                            $this$update$iv4 = MainViewModel.this._uiState;
                                            do {
                                                prevValue$iv3 = $this$update$iv4.getValue();
                                                it3 = (UiState) prevValue$iv3;
                                                settings3 = it3.getSettings();
                                            } while (!$this$update$iv4.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings3.copy((392319 & 1) != 0 ? settings3.loading : true, (392319 & 2) != 0 ? settings3.error : null, (392319 & 4) != 0 ? settings3.bridgeOnline : false, (392319 & 8) != 0 ? settings3.categories : null, (392319 & 16) != 0 ? settings3.fields : null, (392319 & 32) != 0 ? settings3.config : null, (392319 & 64) != 0 ? settings3.search : null, (392319 & 128) != 0 ? settings3.pane : null, (392319 & 256) != 0 ? settings3.essentialsSection : null, (392319 & 512) != 0 ? settings3.selectedCategory : null, (392319 & 1024) != 0 ? settings3.savingPath : null, (392319 & 2048) != 0 ? settings3.modelLabel : null, (392319 & 4096) != 0 ? settings3.lastSavedPath : null, (392319 & 8192) != 0 ? settings3.moa : null, (392319 & 16384) != 0 ? settings3.moaSaving : false, (392319 & 32768) != 0 ? settings3.moaError : null, (392319 & 65536) != 0 ? settings3.moaLastSaved : false, (392319 & 131072) != 0 ? settings3.assist : null, (392319 & 262144) != 0 ? settings3.moaDirty : false), null, 50331647, null)));
                                        }
                                    } else if (((UiState) MainViewModel.this._uiState.getValue()).getSettings().getConfig().isEmpty()) {
                                        $this$update$iv4 = MainViewModel.this._uiState;
                                        do {
                                            prevValue$iv3 = $this$update$iv4.getValue();
                                            it3 = (UiState) prevValue$iv3;
                                            settings3 = it3.getSettings();
                                        } while (!$this$update$iv4.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings3.copy((392319 & 1) != 0 ? settings3.loading : true, (392319 & 2) != 0 ? settings3.error : null, (392319 & 4) != 0 ? settings3.bridgeOnline : false, (392319 & 8) != 0 ? settings3.categories : null, (392319 & 16) != 0 ? settings3.fields : null, (392319 & 32) != 0 ? settings3.config : null, (392319 & 64) != 0 ? settings3.search : null, (392319 & 128) != 0 ? settings3.pane : null, (392319 & 256) != 0 ? settings3.essentialsSection : null, (392319 & 512) != 0 ? settings3.selectedCategory : null, (392319 & 1024) != 0 ? settings3.savingPath : null, (392319 & 2048) != 0 ? settings3.modelLabel : null, (392319 & 4096) != 0 ? settings3.lastSavedPath : null, (392319 & 8192) != 0 ? settings3.moa : null, (392319 & 16384) != 0 ? settings3.moaSaving : false, (392319 & 32768) != 0 ? settings3.moaError : null, (392319 & 65536) != 0 ? settings3.moaLastSaved : false, (392319 & 131072) != 0 ? settings3.assist : null, (392319 & 262144) != 0 ? settings3.moaDirty : false), null, 50331647, null)));
                                    }
                                } else if (this.$forceNetwork) {
                                    $this$update$iv3 = MainViewModel.this._uiState;
                                    do {
                                        prevValue$iv2 = $this$update$iv3.getValue();
                                        it2 = (UiState) prevValue$iv2;
                                        settings2 = it2.getSettings();
                                    } while (!$this$update$iv3.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings2.copy((392319 & 1) != 0 ? settings2.loading : true, (392319 & 2) != 0 ? settings2.error : null, (392319 & 4) != 0 ? settings2.bridgeOnline : false, (392319 & 8) != 0 ? settings2.categories : null, (392319 & 16) != 0 ? settings2.fields : null, (392319 & 32) != 0 ? settings2.config : null, (392319 & 64) != 0 ? settings2.search : null, (392319 & 128) != 0 ? settings2.pane : null, (392319 & 256) != 0 ? settings2.essentialsSection : null, (392319 & 512) != 0 ? settings2.selectedCategory : null, (392319 & 1024) != 0 ? settings2.savingPath : null, (392319 & 2048) != 0 ? settings2.modelLabel : null, (392319 & 4096) != 0 ? settings2.lastSavedPath : null, (392319 & 8192) != 0 ? settings2.moa : null, (392319 & 16384) != 0 ? settings2.moaSaving : false, (392319 & 32768) != 0 ? settings2.moaError : null, (392319 & 65536) != 0 ? settings2.moaLastSaved : false, (392319 & 131072) != 0 ? settings2.assist : null, (392319 & 262144) != 0 ? settings2.moaDirty : false), null, 50331647, null)));
                                }
                                if (this.$forceNetwork) {
                                    this.label = 2;
                                    $result2 = MainViewModel.this.settingsRepository.load(true, this);
                                    if ($result2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    HermesSettingsSnapshot snapshot2 = (HermesSettingsSnapshot) $result2;
                                    MainViewModel.this.applySettingsSnapshot(snapshot2, false);
                                }
                                return Unit.INSTANCE;
                            }
                            $this$update$iv = $this$update$iv2;
                        }
                        break;
                    case 2:
                        $result2 = $result;
                        ResultKt.throwOnFailure($result2);
                        HermesSettingsSnapshot snapshot3 = (HermesSettingsSnapshot) $result2;
                        MainViewModel.this.applySettingsSnapshot(snapshot3, false);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th2) {
            }
        }
    }

    public final void refreshSettings(boolean forceNetwork, boolean paintCacheFirst) {
        if (forceNetwork) {
            Job job = this.settingsLoadJob;
            boolean z = false;
            if (job != null && job.isActive()) {
                z = true;
            }
            if (z) {
                return;
            }
        }
        this.settingsLoadJob = ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C01941(paintCacheFirst, forceNetwork, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applySettingsSnapshot(HermesSettingsSnapshot snapshot, boolean loading) {
        List<ConfigCategory> list;
        Iterable $this$associate$iv = snapshot.getFields();
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(capacity$iv);
        Iterator it = $this$associate$iv.iterator();
        while (true) {
            String category = "cron";
            if (!it.hasNext()) {
                break;
            }
            HermesSettingField field = (HermesSettingField) it.next();
            if (!ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(field.getPath(), "cron.", false, 2, (Object) null)) {
                category = field.getCategory();
            }
            String category2 = category;
            Pair pair = TuplesKt.to(field.getPath(), new ConfigFieldMeta(field.getType(), field.getDescription(), category2, field.getOptions(), Boolean.valueOf(field.isSecret())));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        final Iterable $this$groupingBy$iv = linkedHashMap2.values();
        Map grouped = GroupingKt.eachCount(new Grouping<ConfigFieldMeta, String>() { // from class: ai.nous.aurora.MainViewModel$applySettingsSnapshot$$inlined$groupingBy$1
            @Override // kotlin.collections.Grouping
            public Iterator<ConfigFieldMeta> sourceIterator() {
                return $this$groupingBy$iv.iterator();
            }

            @Override // kotlin.collections.Grouping
            public String keyOf(ConfigFieldMeta element) {
                ConfigFieldMeta it2 = element;
                String category3 = it2.getCategory();
                return category3 == null ? "general" : category3;
            }
        });
        List priority = CollectionsKt.listOf(new String[]{"general", "agent", "display", "gateway", "memory", "security", "terminal", "tts", "stt", "voice", "discord", "delegation", "compression", "sessions", "tools", "browser", "cron", "kanban", "secrets"});
        Iterable $this$filter$iv = CollectionsKt.distinct(CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) priority, (Iterable) snapshot.getCategoryOrder()), (Iterable) CollectionsKt.sorted(grouped.keySet())));
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (grouped.containsKey(it2)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable categoryIds = (List) destination$iv$iv;
        Iterable $this$map$iv = categoryIds;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String id = (String) item$iv$iv;
            String str = settingsCategoryLabel(id);
            Integer num = (Integer) grouped.get(id);
            destination$iv$iv2.add(new ConfigCategory(id, str, num != null ? num.intValue() : 0));
        }
        List<ConfigCategory> listPlus = CollectionsKt.plus((Collection<? extends ConfigCategory>) destination$iv$iv2, new ConfigCategory("about", "About", 1));
        JsonElement p0 = snapshot.getValues().get("model.provider");
        String provider = p0 != null ? displayJsonValue(p0) : null;
        JsonElement p1 = snapshot.getValues().get("model.default");
        String activeModel = p1 != null ? displayJsonValue(p1) : null;
        boolean keepPrior = snapshot.getValues().isEmpty() && !this._uiState.getValue().getSettings().getConfig().isEmpty();
        MutableStateFlow<UiState> mutableStateFlow = this._uiState;
        while (true) {
            UiState value = mutableStateFlow.getValue();
            UiState st = value;
            SettingsUiModel prior = st.getSettings();
            String error = snapshot.getError();
            boolean bridgeOnline = keepPrior ? prior.getBridgeOnline() : snapshot.getRemoteAvailable();
            if (listPlus.size() > 1) {
                list = listPlus;
            } else {
                List<ConfigCategory> categories = prior.getCategories();
                if (categories.isEmpty()) {
                    categories = listPlus;
                }
                list = categories;
            }
            Map<String, ConfigFieldMeta> fields = !linkedHashMap2.isEmpty() ? linkedHashMap2 : prior.getFields();
            Map<String, JsonElement> values = !keepPrior ? snapshot.getValues() : prior.getConfig();
            Iterable $this$filter$iv2 = CollectionsKt.listOfNotNull((Object[]) new String[]{provider, activeModel});
            Collection destination$iv$iv3 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                if (!StringsKt.isBlank((String) element$iv$iv2)) {
                    destination$iv$iv3.add(element$iv$iv2);
                }
            }
            LinkedHashMap linkedHashMap3 = linkedHashMap2;
            String strJoinToString$default = ai.nous.aurora.compat.Compat.d_CollectionsKt_joinToString_9((List) destination$iv$iv3, " · ", null, null, 0, null, null, 62, null);
            if (StringsKt.isBlank(strJoinToString$default)) {
                strJoinToString$default = prior.getModelLabel();
            }
            String str2 = strJoinToString$default;
            MoaSnapshot moa = snapshot.getMoa();
            if (moa == null) {
                moa = prior.getMoa();
            }
            if (mutableStateFlow.compareAndSet(value, UiState.copy$default(st, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, prior.copy((392319 & 1) != 0 ? prior.loading : loading, (392319 & 2) != 0 ? prior.error : error, (392319 & 4) != 0 ? prior.bridgeOnline : bridgeOnline, (392319 & 8) != 0 ? prior.categories : list, (392319 & 16) != 0 ? prior.fields : fields, (392319 & 32) != 0 ? prior.config : values, (392319 & 64) != 0 ? prior.search : null, (392319 & 128) != 0 ? prior.pane : null, (392319 & 256) != 0 ? prior.essentialsSection : null, (392319 & 512) != 0 ? prior.selectedCategory : null, (392319 & 1024) != 0 ? prior.savingPath : null, (392319 & 2048) != 0 ? prior.modelLabel : str2, (392319 & 4096) != 0 ? prior.lastSavedPath : null, (392319 & 8192) != 0 ? prior.moa : moa, (392319 & 16384) != 0 ? prior.moaSaving : false, (392319 & 32768) != 0 ? prior.moaError : null, (392319 & 65536) != 0 ? prior.moaLastSaved : false, (392319 & 131072) != 0 ? prior.assist : null, (392319 & 262144) != 0 ? prior.moaDirty : false), null, 50331647, null))) {
                return;
            } else {
                linkedHashMap2 = linkedHashMap3;
            }
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$setConfigBoolean$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$setConfigBoolean$1", f = "MainViewModel.kt", i = {}, l = {1334, 1346, 1350}, m = "invokeSuspend", n = {}, s = {})
    final class C02051 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $path;
        final /* synthetic */ boolean $value;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02051(String str, boolean z, Continuation continuation) {
            super(2, continuation);
            this.$path = str;
            this.$value = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C02051(this.$path, this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02051)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:18:0x00ee  */
        /* JADX WARN: Code duplicated, block: B:23:0x01a3 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:24:0x01a4  */
        /* JADX WARN: Code duplicated, block: B:26:0x01a7 A[LOOP:1: B:19:0x00fa->B:26:0x01a7, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:29:0x01b5  */
        /* JADX WARN: Code duplicated, block: B:34:0x022d  */
        /* JADX WARN: Code duplicated, block: B:37:0x023d A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:38:0x023e  */
        /* JADX WARN: Code duplicated, block: B:45:0x018d A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object $result;
            Object value;
            MainViewModel mainViewModel;
            String str;
            HermesSettingsSnapshot snapshot;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv;
            UiState it;
            SettingsUiModel settings;
            MutableStateFlow $this$update$iv2;
            String str2;
            MutableSharedFlow mutableSharedFlow;
            ToastEvent.Info info;
            MainViewModel mainViewModel2;
            Throwable e;
            MutableStateFlow $this$update$iv3;
            Object prevValue$iv2;
            UiState it2;
            SettingsUiModel settings2;
            MutableSharedFlow mutableSharedFlow2;
            ToastEvent.Error error;
            String message;
            Object $result2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    $result = obj;
                    MutableStateFlow $this$update$iv4 = MainViewModel.this._uiState;
                    String str3 = this.$path;
                    while (true) {
                        Object prevValue$iv3 = $this$update$iv4.getValue();
                        UiState it3 = (UiState) prevValue$iv3;
                        SettingsUiModel settings3 = it3.getSettings();
                        String str4 = str3;
                        if ($this$update$iv4.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings3.copy((392319 & 1) != 0 ? settings3.loading : false, (392319 & 2) != 0 ? settings3.error : null, (392319 & 4) != 0 ? settings3.bridgeOnline : false, (392319 & 8) != 0 ? settings3.categories : null, (392319 & 16) != 0 ? settings3.fields : null, (392319 & 32) != 0 ? settings3.config : null, (392319 & 64) != 0 ? settings3.search : null, (392319 & 128) != 0 ? settings3.pane : null, (392319 & 256) != 0 ? settings3.essentialsSection : null, (392319 & 512) != 0 ? settings3.selectedCategory : null, (392319 & 1024) != 0 ? settings3.savingPath : str3, (392319 & 2048) != 0 ? settings3.modelLabel : null, (392319 & 4096) != 0 ? settings3.lastSavedPath : null, (392319 & 8192) != 0 ? settings3.moa : null, (392319 & 16384) != 0 ? settings3.moaSaving : false, (392319 & 32768) != 0 ? settings3.moaError : null, (392319 & 65536) != 0 ? settings3.moaLastSaved : false, (392319 & 131072) != 0 ? settings3.assist : null, (392319 & 262144) != 0 ? settings3.moaDirty : false), null, 50331647, null))) {
                            this.label = 1;
                            Object objMo60setValue0E7RQCE = MainViewModel.this.settingsRepository.mo60setValue0E7RQCE(this.$path, JsonElementKt.JsonPrimitive(Boxing.boxBoolean(this.$value)), this);
                            if (objMo60setValue0E7RQCE == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            value = objMo60setValue0E7RQCE;
                            mainViewModel = MainViewModel.this;
                            str = this.$path;
                            if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                                snapshot = (HermesSettingsSnapshot) value;
                                $this$update$iv = mainViewModel._uiState;
                                while (true) {
                                    prevValue$iv = $this$update$iv.getValue();
                                    it = (UiState) prevValue$iv;
                                    settings = it.getSettings();
                                    $this$update$iv2 = $this$update$iv;
                                    str2 = str;
                                    if ($this$update$iv2.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : snapshot.getValues(), (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : str2, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null))) {
                                        mutableSharedFlow = mainViewModel._toasts;
                                        info = new ToastEvent.Info("Saved · hermes-hostinger");
                                        this.L$0 = value;
                                        this.label = 2;
                                        if (mutableSharedFlow.emit(info, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    } else {
                                        $this$update$iv = $this$update$iv2;
                                        str = str2;
                                    }
                                }
                            }
                            mainViewModel2 = MainViewModel.this;
                            e = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                            if (e != null) {
                                $this$update$iv3 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv2 = $this$update$iv3.getValue();
                                    it2 = (UiState) prevValue$iv2;
                                    settings2 = it2.getSettings();
                                } while (!$this$update$iv3.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings2.copy((392319 & 1) != 0 ? settings2.loading : false, (392319 & 2) != 0 ? settings2.error : e.getMessage(), (392319 & 4) != 0 ? settings2.bridgeOnline : false, (392319 & 8) != 0 ? settings2.categories : null, (392319 & 16) != 0 ? settings2.fields : null, (392319 & 32) != 0 ? settings2.config : null, (392319 & 64) != 0 ? settings2.search : null, (392319 & 128) != 0 ? settings2.pane : null, (392319 & 256) != 0 ? settings2.essentialsSection : null, (392319 & 512) != 0 ? settings2.selectedCategory : null, (392319 & 1024) != 0 ? settings2.savingPath : null, (392319 & 2048) != 0 ? settings2.modelLabel : null, (392319 & 4096) != 0 ? settings2.lastSavedPath : null, (392319 & 8192) != 0 ? settings2.moa : null, (392319 & 16384) != 0 ? settings2.moaSaving : false, (392319 & 32768) != 0 ? settings2.moaError : null, (392319 & 65536) != 0 ? settings2.moaLastSaved : false, (392319 & 131072) != 0 ? settings2.assist : null, (392319 & 262144) != 0 ? settings2.moaDirty : false), null, 50331647, null)));
                                mutableSharedFlow2 = mainViewModel2._toasts;
                                message = e.getMessage();
                                if (message == null) {
                                    message = "Save failed";
                                }
                                error = new ToastEvent.Error(message);
                                this.L$0 = value;
                                this.label = 3;
                                if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $result2 = $result;
                            }
                            Object $result3 = Unit.INSTANCE;
                            return $result3;
                        }
                        str3 = str4;
                    }
                    break;
                case 1:
                    $result = obj;
                    ResultKt.throwOnFailure($result);
                    value = ai.nous.aurora.compat.Compat.resultUnbox($result);
                    mainViewModel = MainViewModel.this;
                    str = this.$path;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        snapshot = (HermesSettingsSnapshot) value;
                        $this$update$iv = mainViewModel._uiState;
                        while (true) {
                            prevValue$iv = $this$update$iv.getValue();
                            it = (UiState) prevValue$iv;
                            settings = it.getSettings();
                            $this$update$iv2 = $this$update$iv;
                            str2 = str;
                            if ($this$update$iv2.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : null, (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : snapshot.getValues(), (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : str2, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null))) {
                                mutableSharedFlow = mainViewModel._toasts;
                                info = new ToastEvent.Info("Saved · hermes-hostinger");
                                this.L$0 = value;
                                this.label = 2;
                                if (mutableSharedFlow.emit(info, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                $this$update$iv = $this$update$iv2;
                                str = str2;
                            }
                        }
                    }
                    mainViewModel2 = MainViewModel.this;
                    e = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (e != null) {
                        $this$update$iv3 = mainViewModel2._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv3.getValue();
                            it2 = (UiState) prevValue$iv2;
                            settings2 = it2.getSettings();
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings2.copy((392319 & 1) != 0 ? settings2.loading : false, (392319 & 2) != 0 ? settings2.error : e.getMessage(), (392319 & 4) != 0 ? settings2.bridgeOnline : false, (392319 & 8) != 0 ? settings2.categories : null, (392319 & 16) != 0 ? settings2.fields : null, (392319 & 32) != 0 ? settings2.config : null, (392319 & 64) != 0 ? settings2.search : null, (392319 & 128) != 0 ? settings2.pane : null, (392319 & 256) != 0 ? settings2.essentialsSection : null, (392319 & 512) != 0 ? settings2.selectedCategory : null, (392319 & 1024) != 0 ? settings2.savingPath : null, (392319 & 2048) != 0 ? settings2.modelLabel : null, (392319 & 4096) != 0 ? settings2.lastSavedPath : null, (392319 & 8192) != 0 ? settings2.moa : null, (392319 & 16384) != 0 ? settings2.moaSaving : false, (392319 & 32768) != 0 ? settings2.moaError : null, (392319 & 65536) != 0 ? settings2.moaLastSaved : false, (392319 & 131072) != 0 ? settings2.assist : null, (392319 & 262144) != 0 ? settings2.moaDirty : false), null, 50331647, null)));
                        mutableSharedFlow2 = mainViewModel2._toasts;
                        message = e.getMessage();
                        if (message == null) {
                            message = "Save failed";
                        }
                        error = new ToastEvent.Error(message);
                        this.L$0 = value;
                        this.label = 3;
                        if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                    }
                    Object $result4 = Unit.INSTANCE;
                    return $result4;
                case 2:
                    $result = obj;
                    value = this.L$0;
                    ResultKt.throwOnFailure($result);
                    mainViewModel2 = MainViewModel.this;
                    e = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (e != null) {
                        $this$update$iv3 = mainViewModel2._uiState;
                        do {
                            prevValue$iv2 = $this$update$iv3.getValue();
                            it2 = (UiState) prevValue$iv2;
                            settings2 = it2.getSettings();
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings2.copy((392319 & 1) != 0 ? settings2.loading : false, (392319 & 2) != 0 ? settings2.error : e.getMessage(), (392319 & 4) != 0 ? settings2.bridgeOnline : false, (392319 & 8) != 0 ? settings2.categories : null, (392319 & 16) != 0 ? settings2.fields : null, (392319 & 32) != 0 ? settings2.config : null, (392319 & 64) != 0 ? settings2.search : null, (392319 & 128) != 0 ? settings2.pane : null, (392319 & 256) != 0 ? settings2.essentialsSection : null, (392319 & 512) != 0 ? settings2.selectedCategory : null, (392319 & 1024) != 0 ? settings2.savingPath : null, (392319 & 2048) != 0 ? settings2.modelLabel : null, (392319 & 4096) != 0 ? settings2.lastSavedPath : null, (392319 & 8192) != 0 ? settings2.moa : null, (392319 & 16384) != 0 ? settings2.moaSaving : false, (392319 & 32768) != 0 ? settings2.moaError : null, (392319 & 65536) != 0 ? settings2.moaLastSaved : false, (392319 & 131072) != 0 ? settings2.assist : null, (392319 & 262144) != 0 ? settings2.moaDirty : false), null, 50331647, null)));
                        mutableSharedFlow2 = mainViewModel2._toasts;
                        message = e.getMessage();
                        if (message == null) {
                            message = "Save failed";
                        }
                        error = new ToastEvent.Error(message);
                        this.L$0 = value;
                        this.label = 3;
                        if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                    }
                    Object $result5 = Unit.INSTANCE;
                    return $result5;
                case 3:
                    $result2 = obj;
                    ResultKt.throwOnFailure($result2);
                    Object $result6 = Unit.INSTANCE;
                    return $result6;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void setConfigBoolean(String path, boolean value) {
        Intrinsics.checkNotNullParameter(path, "path");
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C02051(path, value, null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainViewModel$setConfigString$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainViewModel$setConfigString$1", f = "MainViewModel.kt", i = {}, l = {1360, 1363, 1375, 1379}, m = "invokeSuspend", n = {}, s = {})
    final class C02061 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ String $path;
        final /* synthetic */ String $type;
        final /* synthetic */ String $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02061(String str, String str2, String str3, Continuation continuation) {
            super(2, continuation);
            this.$path = str;
            this.$type = str2;
            this.$value = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C02061 c02061 = MainViewModel.this.new C02061(this.$path, this.$type, this.$value, continuation);
            c02061.L$0 = obj;
            return c02061;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02061)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:26:0x011b  */
        /* JADX WARN: Code duplicated, block: B:31:0x01d0 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:32:0x01d1  */
        /* JADX WARN: Code duplicated, block: B:34:0x01d4 A[LOOP:1: B:27:0x0127->B:34:0x01d4, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:37:0x01e2  */
        /* JADX WARN: Code duplicated, block: B:42:0x025a  */
        /* JADX WARN: Code duplicated, block: B:45:0x026a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:46:0x026b  */
        /* JADX WARN: Code duplicated, block: B:67:0x01ba A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objM7388constructorimpl;
            Object prevValue$iv;
            UiState it;
            SettingsUiModel settings;
            Object value;
            MainViewModel mainViewModel;
            String str;
            HermesSettingsSnapshot snapshot;
            MutableStateFlow $this$update$iv;
            Object prevValue$iv2;
            UiState it2;
            SettingsUiModel settings2;
            MutableStateFlow $this$update$iv2;
            String str2;
            MutableSharedFlow mutableSharedFlow;
            ToastEvent.Info info;
            MainViewModel mainViewModel2;
            Throwable e;
            MutableStateFlow $this$update$iv3;
            Object prevValue$iv3;
            UiState it3;
            SettingsUiModel settings3;
            MutableSharedFlow mutableSharedFlow2;
            ToastEvent.Error error;
            String message;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    MutableStateFlow $this$update$iv4 = MainViewModel.this._uiState;
                    String str3 = this.$path;
                    while (true) {
                        Object prevValue$iv4 = $this$update$iv4.getValue();
                        UiState it4 = (UiState) prevValue$iv4;
                        SettingsUiModel settings4 = it4.getSettings();
                        String str4 = str3;
                        if ($this$update$iv4.compareAndSet(prevValue$iv4, UiState.copy$default(it4, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings4.copy((392319 & 1) != 0 ? settings4.loading : false, (392319 & 2) != 0 ? settings4.error : null, (392319 & 4) != 0 ? settings4.bridgeOnline : false, (392319 & 8) != 0 ? settings4.categories : null, (392319 & 16) != 0 ? settings4.fields : null, (392319 & 32) != 0 ? settings4.config : null, (392319 & 64) != 0 ? settings4.search : null, (392319 & 128) != 0 ? settings4.pane : null, (392319 & 256) != 0 ? settings4.essentialsSection : null, (392319 & 512) != 0 ? settings4.selectedCategory : null, (392319 & 1024) != 0 ? settings4.savingPath : str4, (392319 & 2048) != 0 ? settings4.modelLabel : null, (392319 & 4096) != 0 ? settings4.lastSavedPath : null, (392319 & 8192) != 0 ? settings4.moa : null, (392319 & 16384) != 0 ? settings4.moaSaving : false, (392319 & 32768) != 0 ? settings4.moaError : null, (392319 & 65536) != 0 ? settings4.moaLastSaved : false, (392319 & 131072) != 0 ? settings4.assist : null, (392319 & 262144) != 0 ? settings4.moaDirty : false), null, 50331647, null))) {
                            MainViewModel mainViewModel3 = MainViewModel.this;
                            String str5 = this.$type;
                            String str6 = this.$value;
                            try {
                                Result.Companion companion = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(mainViewModel3.configJsonValue(str5, str6));
                                break;
                            } catch (Throwable th) {
                                Result.Companion companion2 = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                            }
                            MainViewModel mainViewModel4 = MainViewModel.this;
                            Throwable failure = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM7388constructorimpl);
                            if (failure != null) {
                                MutableStateFlow $this$update$iv5 = mainViewModel4._uiState;
                                do {
                                    prevValue$iv = $this$update$iv5.getValue();
                                    it = (UiState) prevValue$iv;
                                    settings = it.getSettings();
                                } while (!$this$update$iv5.compareAndSet(prevValue$iv, UiState.copy$default(it, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings.copy((392319 & 1) != 0 ? settings.loading : false, (392319 & 2) != 0 ? settings.error : failure.getMessage(), (392319 & 4) != 0 ? settings.bridgeOnline : false, (392319 & 8) != 0 ? settings.categories : null, (392319 & 16) != 0 ? settings.fields : null, (392319 & 32) != 0 ? settings.config : null, (392319 & 64) != 0 ? settings.search : null, (392319 & 128) != 0 ? settings.pane : null, (392319 & 256) != 0 ? settings.essentialsSection : null, (392319 & 512) != 0 ? settings.selectedCategory : null, (392319 & 1024) != 0 ? settings.savingPath : null, (392319 & 2048) != 0 ? settings.modelLabel : null, (392319 & 4096) != 0 ? settings.lastSavedPath : null, (392319 & 8192) != 0 ? settings.moa : null, (392319 & 16384) != 0 ? settings.moaSaving : false, (392319 & 32768) != 0 ? settings.moaError : null, (392319 & 65536) != 0 ? settings.moaLastSaved : false, (392319 & 131072) != 0 ? settings.assist : null, (392319 & 262144) != 0 ? settings.moaDirty : false), null, 50331647, null)));
                                MutableSharedFlow mutableSharedFlow3 = mainViewModel4._toasts;
                                String message2 = failure.getMessage();
                                if (message2 == null) {
                                    message2 = "Invalid value";
                                }
                                ToastEvent.Error error2 = new ToastEvent.Error(message2);
                                this.label = 1;
                                return mutableSharedFlow3.emit(error2, this) == coroutine_suspended ? coroutine_suspended : Unit.INSTANCE;
                            }
                            JsonElement element = (JsonElement) objM7388constructorimpl;
                            this.label = 2;
                            Object objMo60setValue0E7RQCE = MainViewModel.this.settingsRepository.mo60setValue0E7RQCE(this.$path, element, this);
                            if (objMo60setValue0E7RQCE == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            value = objMo60setValue0E7RQCE;
                            mainViewModel = MainViewModel.this;
                            str = this.$path;
                            if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                                snapshot = (HermesSettingsSnapshot) value;
                                $this$update$iv = mainViewModel._uiState;
                                while (true) {
                                    prevValue$iv2 = $this$update$iv.getValue();
                                    it2 = (UiState) prevValue$iv2;
                                    settings2 = it2.getSettings();
                                    $this$update$iv2 = $this$update$iv;
                                    str2 = str;
                                    if ($this$update$iv2.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings2.copy((392319 & 1) != 0 ? settings2.loading : false, (392319 & 2) != 0 ? settings2.error : null, (392319 & 4) != 0 ? settings2.bridgeOnline : false, (392319 & 8) != 0 ? settings2.categories : null, (392319 & 16) != 0 ? settings2.fields : null, (392319 & 32) != 0 ? settings2.config : snapshot.getValues(), (392319 & 64) != 0 ? settings2.search : null, (392319 & 128) != 0 ? settings2.pane : null, (392319 & 256) != 0 ? settings2.essentialsSection : null, (392319 & 512) != 0 ? settings2.selectedCategory : null, (392319 & 1024) != 0 ? settings2.savingPath : null, (392319 & 2048) != 0 ? settings2.modelLabel : null, (392319 & 4096) != 0 ? settings2.lastSavedPath : str2, (392319 & 8192) != 0 ? settings2.moa : null, (392319 & 16384) != 0 ? settings2.moaSaving : false, (392319 & 32768) != 0 ? settings2.moaError : null, (392319 & 65536) != 0 ? settings2.moaLastSaved : false, (392319 & 131072) != 0 ? settings2.assist : null, (392319 & 262144) != 0 ? settings2.moaDirty : false), null, 50331647, null))) {
                                        mutableSharedFlow = mainViewModel._toasts;
                                        info = new ToastEvent.Info("Saved · hermes-hostinger");
                                        this.L$0 = value;
                                        this.label = 3;
                                        if (mutableSharedFlow.emit(info, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    } else {
                                        $this$update$iv = $this$update$iv2;
                                        str = str2;
                                    }
                                }
                            }
                            mainViewModel2 = MainViewModel.this;
                            e = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                            if (e != null) {
                                $this$update$iv3 = mainViewModel2._uiState;
                                do {
                                    prevValue$iv3 = $this$update$iv3.getValue();
                                    it3 = (UiState) prevValue$iv3;
                                    settings3 = it3.getSettings();
                                } while (!$this$update$iv3.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings3.copy((392319 & 1) != 0 ? settings3.loading : false, (392319 & 2) != 0 ? settings3.error : e.getMessage(), (392319 & 4) != 0 ? settings3.bridgeOnline : false, (392319 & 8) != 0 ? settings3.categories : null, (392319 & 16) != 0 ? settings3.fields : null, (392319 & 32) != 0 ? settings3.config : null, (392319 & 64) != 0 ? settings3.search : null, (392319 & 128) != 0 ? settings3.pane : null, (392319 & 256) != 0 ? settings3.essentialsSection : null, (392319 & 512) != 0 ? settings3.selectedCategory : null, (392319 & 1024) != 0 ? settings3.savingPath : null, (392319 & 2048) != 0 ? settings3.modelLabel : null, (392319 & 4096) != 0 ? settings3.lastSavedPath : null, (392319 & 8192) != 0 ? settings3.moa : null, (392319 & 16384) != 0 ? settings3.moaSaving : false, (392319 & 32768) != 0 ? settings3.moaError : null, (392319 & 65536) != 0 ? settings3.moaLastSaved : false, (392319 & 131072) != 0 ? settings3.assist : null, (392319 & 262144) != 0 ? settings3.moaDirty : false), null, 50331647, null)));
                                mutableSharedFlow2 = mainViewModel2._toasts;
                                message = e.getMessage();
                                if (message == null) {
                                    message = "Save failed";
                                }
                                error = new ToastEvent.Error(message);
                                this.L$0 = value;
                                this.label = 4;
                                if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        str3 = str4;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                case 2:
                    ResultKt.throwOnFailure($result);
                    value = ai.nous.aurora.compat.Compat.resultUnbox($result);
                    mainViewModel = MainViewModel.this;
                    str = this.$path;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        snapshot = (HermesSettingsSnapshot) value;
                        $this$update$iv = mainViewModel._uiState;
                        while (true) {
                            prevValue$iv2 = $this$update$iv.getValue();
                            it2 = (UiState) prevValue$iv2;
                            settings2 = it2.getSettings();
                            $this$update$iv2 = $this$update$iv;
                            str2 = str;
                            if ($this$update$iv2.compareAndSet(prevValue$iv2, UiState.copy$default(it2, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings2.copy((392319 & 1) != 0 ? settings2.loading : false, (392319 & 2) != 0 ? settings2.error : null, (392319 & 4) != 0 ? settings2.bridgeOnline : false, (392319 & 8) != 0 ? settings2.categories : null, (392319 & 16) != 0 ? settings2.fields : null, (392319 & 32) != 0 ? settings2.config : snapshot.getValues(), (392319 & 64) != 0 ? settings2.search : null, (392319 & 128) != 0 ? settings2.pane : null, (392319 & 256) != 0 ? settings2.essentialsSection : null, (392319 & 512) != 0 ? settings2.selectedCategory : null, (392319 & 1024) != 0 ? settings2.savingPath : null, (392319 & 2048) != 0 ? settings2.modelLabel : null, (392319 & 4096) != 0 ? settings2.lastSavedPath : str2, (392319 & 8192) != 0 ? settings2.moa : null, (392319 & 16384) != 0 ? settings2.moaSaving : false, (392319 & 32768) != 0 ? settings2.moaError : null, (392319 & 65536) != 0 ? settings2.moaLastSaved : false, (392319 & 131072) != 0 ? settings2.assist : null, (392319 & 262144) != 0 ? settings2.moaDirty : false), null, 50331647, null))) {
                                mutableSharedFlow = mainViewModel._toasts;
                                info = new ToastEvent.Info("Saved · hermes-hostinger");
                                this.L$0 = value;
                                this.label = 3;
                                if (mutableSharedFlow.emit(info, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                $this$update$iv = $this$update$iv2;
                                str = str2;
                            }
                        }
                    }
                    mainViewModel2 = MainViewModel.this;
                    e = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (e != null) {
                        $this$update$iv3 = mainViewModel2._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv3.getValue();
                            it3 = (UiState) prevValue$iv3;
                            settings3 = it3.getSettings();
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings3.copy((392319 & 1) != 0 ? settings3.loading : false, (392319 & 2) != 0 ? settings3.error : e.getMessage(), (392319 & 4) != 0 ? settings3.bridgeOnline : false, (392319 & 8) != 0 ? settings3.categories : null, (392319 & 16) != 0 ? settings3.fields : null, (392319 & 32) != 0 ? settings3.config : null, (392319 & 64) != 0 ? settings3.search : null, (392319 & 128) != 0 ? settings3.pane : null, (392319 & 256) != 0 ? settings3.essentialsSection : null, (392319 & 512) != 0 ? settings3.selectedCategory : null, (392319 & 1024) != 0 ? settings3.savingPath : null, (392319 & 2048) != 0 ? settings3.modelLabel : null, (392319 & 4096) != 0 ? settings3.lastSavedPath : null, (392319 & 8192) != 0 ? settings3.moa : null, (392319 & 16384) != 0 ? settings3.moaSaving : false, (392319 & 32768) != 0 ? settings3.moaError : null, (392319 & 65536) != 0 ? settings3.moaLastSaved : false, (392319 & 131072) != 0 ? settings3.assist : null, (392319 & 262144) != 0 ? settings3.moaDirty : false), null, 50331647, null)));
                        mutableSharedFlow2 = mainViewModel2._toasts;
                        message = e.getMessage();
                        if (message == null) {
                            message = "Save failed";
                        }
                        error = new ToastEvent.Error(message);
                        this.L$0 = value;
                        this.label = 4;
                        if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 3:
                    value = this.L$0;
                    ResultKt.throwOnFailure($result);
                    mainViewModel2 = MainViewModel.this;
                    e = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                    if (e != null) {
                        $this$update$iv3 = mainViewModel2._uiState;
                        do {
                            prevValue$iv3 = $this$update$iv3.getValue();
                            it3 = (UiState) prevValue$iv3;
                            settings3 = it3.getSettings();
                        } while (!$this$update$iv3.compareAndSet(prevValue$iv3, UiState.copy$default(it3, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, false, false, false, false, null, null, settings3.copy((392319 & 1) != 0 ? settings3.loading : false, (392319 & 2) != 0 ? settings3.error : e.getMessage(), (392319 & 4) != 0 ? settings3.bridgeOnline : false, (392319 & 8) != 0 ? settings3.categories : null, (392319 & 16) != 0 ? settings3.fields : null, (392319 & 32) != 0 ? settings3.config : null, (392319 & 64) != 0 ? settings3.search : null, (392319 & 128) != 0 ? settings3.pane : null, (392319 & 256) != 0 ? settings3.essentialsSection : null, (392319 & 512) != 0 ? settings3.selectedCategory : null, (392319 & 1024) != 0 ? settings3.savingPath : null, (392319 & 2048) != 0 ? settings3.modelLabel : null, (392319 & 4096) != 0 ? settings3.lastSavedPath : null, (392319 & 8192) != 0 ? settings3.moa : null, (392319 & 16384) != 0 ? settings3.moaSaving : false, (392319 & 32768) != 0 ? settings3.moaError : null, (392319 & 65536) != 0 ? settings3.moaLastSaved : false, (392319 & 131072) != 0 ? settings3.assist : null, (392319 & 262144) != 0 ? settings3.moaDirty : false), null, 50331647, null)));
                        mutableSharedFlow2 = mainViewModel2._toasts;
                        message = e.getMessage();
                        if (message == null) {
                            message = "Save failed";
                        }
                        error = new ToastEvent.Error(message);
                        this.L$0 = value;
                        this.label = 4;
                        if (mutableSharedFlow2.emit(error, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 4:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void setConfigString(String path, String type, String value) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(value, "value");
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(ViewModelKt.getViewModelScope(this), null, null, new C02061(path, type, value, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:54:0x014b  */
    /* JADX WARN: Code duplicated, block: B:56:0x0151  */
    /* JADX WARN: Code duplicated, block: B:58:0x015d  */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0023, code lost:
    
        if (r2.equals("float") == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0122, code lost:
    
        if (r2.equals("int") != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x012b, code lost:
    
        if (r2.equals("number") == false) goto L60;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlinx.serialization.json.JsonElement configJsonValue(java.lang.String r16, java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.MainViewModel.configJsonValue(java.lang.String, java.lang.String):kotlinx.serialization.json.JsonElement");
    }

    private final String displayJsonValue(JsonElement value) {
        JsonPrimitive jsonPrimitive = value instanceof JsonPrimitive ? (JsonPrimitive) value : null;
        String content = jsonPrimitive != null ? jsonPrimitive.getContent() : null;
        return content == null ? "" : content;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final String settingsCategoryLabel(String id) {
        switch (id.hashCode()) {
            case 107465:
                if (id.equals("lsp")) {
                    return "Language Servers";
                }
                break;
            case 108287:
                if (id.equals("moa")) {
                    return "Mixture of Agents";
                }
                break;
            case 114227:
                if (id.equals("stt")) {
                    return "Speech to Text";
                }
                break;
            case 115187:
                if (id.equals("tts")) {
                    return "Text to Speech";
                }
                break;
            case 92750597:
                if (id.equals("agent")) {
                    return "Model & Agent";
                }
                break;
            case 485976911:
                if (id.equals("x_search")) {
                    return "X Search";
                }
                break;
            case 1671380268:
                if (id.equals("discord")) {
                    return "Discord & Telegram";
                }
                break;
        }
        String strReplace$default = ai.nous.aurora.compat.Compat.d_StringsKt_replace_6(id, '_', ' ', false, 4, (Object) null);
        if (!(strReplace$default.length() > 0)) {
            return strReplace$default;
        }
        StringBuilder sb = new StringBuilder();
        char it = strReplace$default.charAt(0);
        StringBuilder sbAppend = sb.append((Object) CharsKt.titlecase(it));
        String strSubstring = strReplace$default.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return sbAppend.append(strSubstring).toString();
    }
}
