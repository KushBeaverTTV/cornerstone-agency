package ai.nous.aurora;

import ai.nous.aurora.data.remote.HostVoiceClient;
import ai.nous.aurora.data.storage.AuroraCredentialStore;
import ai.nous.aurora.device.AssistDeviceActions;
import ai.nous.aurora.domain.model.AssistLockPolicy;
import ai.nous.aurora.presentation.theme.AuroraMotion;
import ai.nous.aurora.presentation.theme.AuroraSkin;
import ai.nous.aurora.presentation.theme.AuroraThemeKt;
import ai.nous.aurora.presentation.voice.HostAudioRecorder;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.compose.ComponentActivityKt;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.compose.FlowExtKt;
import java.io.File;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: compiled from: AssistActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0014J\u0010\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020AH\u0014J\b\u0010B\u001a\u00020<H\u0014J\b\u0010C\u001a\u00020<H\u0014J\b\u0010D\u001a\u00020<H\u0014J\b\u0010E\u001a\u00020<H\u0014J\b\u0010F\u001a\u00020<H\u0002J\b\u0010G\u001a\u00020<H\u0002J\u0010\u0010H\u001a\u00020<2\u0006\u0010I\u001a\u00020\u0010H\u0002J\b\u0010J\u001a\u00020<H\u0002J\b\u0010K\u001a\u00020)H\u0002J\b\u0010L\u001a\u00020<H\u0002J\b\u0010M\u001a\u00020<H\u0002J\b\u0010N\u001a\u00020<H\u0002J\b\u0010O\u001a\u00020<H\u0002J\u0016\u0010P\u001a\u00020<2\u0006\u0010Q\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010RR\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR+\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R+\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0017\u001a\u0004\b\u0019\u0010\u0013\"\u0004\b\u001a\u0010\u0015R+\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0017\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R+\u0010 \u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u0017\u001a\u0004\b!\u0010\u0013\"\u0004\b\"\u0010\u0015R+\u0010$\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0017\u001a\u0004\b%\u0010\u0013\"\u0004\b&\u0010\u0015R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010*\u001a\u00020)2\u0006\u0010\u000f\u001a\u00020)8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010\u0017\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00109\u001a\b\u0012\u0004\u0012\u00020\u00100:X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006S²\u0006\n\u0010T\u001a\u00020\u0010X\u008a\u0084\u0002"}, d2 = {"Lai/nous/aurora/AssistActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "appModule", "Lai/nous/aurora/AppModule;", "getAppModule", "()Lai/nous/aurora/AppModule;", "appModule$delegate", "Lkotlin/Lazy;", "voiceClient", "Lai/nous/aurora/data/remote/HostVoiceClient;", "getVoiceClient", "()Lai/nous/aurora/data/remote/HostVoiceClient;", "voiceClient$delegate", "<set-?>", "", "phase", "getPhase", "()Ljava/lang/String;", "setPhase", "(Ljava/lang/String;)V", "phase$delegate", "Landroidx/compose/runtime/MutableState;", "statusLine", "getStatusLine", "setStatusLine", "statusLine$delegate", "transcript", "getTranscript", "setTranscript", "transcript$delegate", "reply", "getReply", "setReply", "reply$delegate", "orbStyle", "getOrbStyle", "setOrbStyle", "orbStyle$delegate", "autoListen", "", "speakReplies", "getSpeakReplies", "()Z", "setSpeakReplies", "(Z)V", "speakReplies$delegate", "recorder", "Lai/nous/aurora/presentation/voice/HostAudioRecorder;", "autoStopJob", "Lkotlinx/coroutines/Job;", "leaveCancelJob", "sendJob", "leaveArmed", "player", "Landroid/media/MediaPlayer;", "micPermission", "Landroidx/activity/result/ActivityResultLauncher;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "onUserLeaveHint", "onResume", "onStop", "onDestroy", "leaveAssist", "stopPlayback", "cancelListening", "reason", "ensureMicThenPower", "deviceLocked", "handlePowerGesture", "onOrbToggle", "startRecording", "stopAndSend", "playRemote", "url", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug", "skinWire"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AssistActivity extends ComponentActivity {
    public static final int $stable = 8;
    public Job autoStopJob;
    public boolean leaveArmed;
    public Job leaveCancelJob;
    public MediaPlayer player;
    public HostAudioRecorder recorder;
    public Job sendJob;

    /* JADX INFO: renamed from: appModule$delegate, reason: from kotlin metadata */
    private final Lazy appModule = LazyKt.lazy(new Function0() { // from class: ai.nous.aurora.AssistActivity$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Object f$0 = null;
            return AssistActivity.appModule_delegate$lambda$0(AssistActivity.this);
        }
    });

    /* JADX INFO: renamed from: voiceClient$delegate, reason: from kotlin metadata */
    private final Lazy voiceClient = LazyKt.lazy(new Function0() { // from class: ai.nous.aurora.AssistActivity$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AssistActivity.voiceClient_delegate$lambda$1(AssistActivity.this);
        }
    });

    /* JADX INFO: renamed from: phase$delegate, reason: from kotlin metadata */
    private final MutableState phase = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4("need_mic", null, 2, null);

    /* JADX INFO: renamed from: statusLine$delegate, reason: from kotlin metadata */
    private final MutableState statusLine = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4(AssistLockPolicy.STATUS_READY, null, 2, null);

    /* JADX INFO: renamed from: transcript$delegate, reason: from kotlin metadata */
    private final MutableState transcript = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4("", null, 2, null);

    /* JADX INFO: renamed from: reply$delegate, reason: from kotlin metadata */
    private final MutableState reply = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4("", null, 2, null);

    /* JADX INFO: renamed from: orbStyle$delegate, reason: from kotlin metadata */
    private final MutableState orbStyle = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4("amber", null, 2, null);
    private boolean autoListen = true;

    /* JADX INFO: renamed from: speakReplies$delegate, reason: from kotlin metadata */
    private final MutableState speakReplies = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4(true, null, 2, null);
    private final ActivityResultLauncher<String> micPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: ai.nous.aurora.AssistActivity$$ExternalSyntheticLambda2
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            AssistActivity.micPermission$lambda$2(AssistActivity.this, ((Boolean) obj).booleanValue());
        }
    });

    /* JADX INFO: compiled from: AssistActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssistLockPolicy.PowerGesture.values().length];
            try {
                iArr[AssistLockPolicy.PowerGesture.FinishSend.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AssistLockPolicy.PowerGesture.StartListen.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AssistLockPolicy.PowerGesture.Ignore.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.AssistActivity$playRemote$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AssistActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.AssistActivity", f = "AssistActivity.kt", i = {0, 0, 1, 2, 2}, l = {StatusLine.HTTP_MISDIRECTED_REQUEST, 425, 441, 442}, m = "playRemote", n = {"url", "$this$playRemote_u24lambda_u246", "$this$playRemote_u24lambda_u246", "$this$playRemote_u24lambda_u246", "tmp"}, s = {"L$0", "L$1", "L$0", "L$0", "L$1"})
    final class C01771 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01771(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AssistActivity.this.playRemote(null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AppModule appModule_delegate$lambda$0(AssistActivity this$0) {
        Application application = this$0.getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type ai.nous.aurora.AuroraApplication");
        return ((AuroraApplication) application).getAppModule();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AppModule getAppModule() {
        return (AppModule) this.appModule.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HostVoiceClient getVoiceClient() {
        return (HostVoiceClient) this.voiceClient.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HostVoiceClient voiceClient_delegate$lambda$1(AssistActivity this$0) {
        return new HostVoiceClient(this$0.getAppModule().getCredentialStore());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPhase() {
        State $this$getValue$iv = this.phase;
        return (String) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPhase(String str) {
        MutableState $this$setValue$iv = this.phase;
        $this$setValue$iv.setValue(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getStatusLine() {
        State $this$getValue$iv = this.statusLine;
        return (String) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStatusLine(String str) {
        MutableState $this$setValue$iv = this.statusLine;
        $this$setValue$iv.setValue(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTranscript() {
        State $this$getValue$iv = this.transcript;
        return (String) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTranscript(String str) {
        MutableState $this$setValue$iv = this.transcript;
        $this$setValue$iv.setValue(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getReply() {
        State $this$getValue$iv = this.reply;
        return (String) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setReply(String str) {
        MutableState $this$setValue$iv = this.reply;
        $this$setValue$iv.setValue(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getOrbStyle() {
        State $this$getValue$iv = this.orbStyle;
        return (String) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOrbStyle(String str) {
        MutableState $this$setValue$iv = this.orbStyle;
        $this$setValue$iv.setValue(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSpeakReplies() {
        State $this$getValue$iv = this.speakReplies;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSpeakReplies(boolean z) {
        MutableState $this$setValue$iv = this.speakReplies;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void micPermission$lambda$2(AssistActivity this$0, boolean granted) {
        if (granted) {
            this$0.setPhase("idle");
            this$0.setStatusLine(AssistLockPolicy.STATUS_READY);
            this$0.handlePowerGesture();
        } else {
            this$0.setPhase("error");
            this$0.setStatusLine(AssistLockPolicy.STATUS_NEED_MIC);
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
        } else {
            getWindow().addFlags(2621440);
        }
        getWindow().addFlags(128);
        androidx.activity.EdgeToEdge.enable(this);
        ai.nous.aurora.compat.Compat.d_ComponentActivityKt_setContent_5(this, null, ComposableLambdaKt.composableLambdaInstance(470567097, true, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.AssistActivity.onCreate.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            private static final String invoke$lambda$0(State<String> state) {
                Object thisObj$iv = state.getValue();
                return (String) thisObj$iv;
            }

            public final void invoke(Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C134@5472L29,136@5597L883,136@5572L908:AssistActivity.kt#l0z6fd");
                if (($changed & 3) != 2 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(470567097, $changed, -1, "ai.nous.aurora.AssistActivity.onCreate.<anonymous> (AssistActivity.kt:134)");
                    }
                    State skinWire$delegate = FlowExtKt.collectAsStateWithLifecycle(AssistActivity.this.getAppModule().getCredentialStore().getAppearanceSkinFlow(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer, 0, 7);
                    AuroraSkin skin = AuroraSkin.INSTANCE.fromWireValue(invoke$lambda$0(skinWire$delegate));
                    AuroraThemeKt.AuroraTheme(skin, (Function2) ComposableLambdaKt.rememberComposableLambda(-526643850, true, new C00001(AssistActivity.this), $composer, 54), $composer, 48, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }

            /* JADX INFO: renamed from: ai.nous.aurora.AssistActivity$onCreate$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: AssistActivity.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            static final class C00001 implements Function2<Composer, Integer, Unit> {
                final /* synthetic */ AssistActivity this$0;

                C00001(AssistActivity assistActivity) {
                    this.this$0 = assistActivity;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer, int $changed) {
                    Object value$iv;
                    Object value$iv2;
                    Object value$iv3;
                    ComposerKt.sourceInformation($composer, "C144@5911L349,153@6294L17,154@6343L55,155@6430L17,137@5615L851:AssistActivity.kt#l0z6fd");
                    if (($changed & 3) != 2 || !$composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-526643850, $changed, -1, "ai.nous.aurora.AssistActivity.onCreate.<anonymous>.<anonymous> (AssistActivity.kt:137)");
                        }
                        String phase = this.this$0.getPhase();
                        String statusLine = this.this$0.getStatusLine();
                        String transcript = this.this$0.getTranscript();
                        String reply = this.this$0.getReply();
                        String orbStyle = this.this$0.getOrbStyle();
                        boolean speakReplies = this.this$0.getSpeakReplies();
                        $composer.startReplaceGroup(-1609386169);
                        ComposerKt.sourceInformation($composer, "CC(remember):AssistActivity.kt#9igjgp");
                        boolean invalid$iv = $composer.changedInstance(this.this$0);
                        final AssistActivity assistActivity = this.this$0;
                        Object it$iv = $composer.rememberedValue();
                        if (invalid$iv || it$iv == Composer.Companion.getEmpty()) {
                            value$iv = new Function1() { // from class: ai.nous.aurora.AssistActivity$onCreate$1$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    Object AnonymousClass1 = null;
                                    return C00001.invoke$lambda$1$lambda$0(assistActivity, ((Boolean) obj).booleanValue());
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv;
                        }
                        Function1 function1 = (Function1) value$iv;
                        $composer.endReplaceGroup();
                        $composer.startReplaceGroup(-1609374245);
                        ComposerKt.sourceInformation($composer, "CC(remember):AssistActivity.kt#9igjgp");
                        boolean invalid$iv2 = $composer.changedInstance(this.this$0);
                        final AssistActivity assistActivity2 = this.this$0;
                        Object it$iv2 = $composer.rememberedValue();
                        if (invalid$iv2 || it$iv2 == Composer.Companion.getEmpty()) {
                            value$iv2 = new Function0() { // from class: ai.nous.aurora.AssistActivity$onCreate$1$1$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return C00001.invoke$lambda$3$lambda$2(assistActivity2);
                                }
                            };
                            $composer.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = it$iv2;
                        }
                        Function0 function0 = (Function0) value$iv2;
                        $composer.endReplaceGroup();
                        $composer.startReplaceGroup(-1609372639);
                        ComposerKt.sourceInformation($composer, "CC(remember):AssistActivity.kt#9igjgp");
                        Object it$iv3 = $composer.rememberedValue();
                        if (it$iv3 == Composer.Companion.getEmpty()) {
                            value$iv3 = new Function0() { // from class: ai.nous.aurora.AssistActivity$onCreate$1$1$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            $composer.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv3;
                        }
                        Function0 function2 = (Function0) value$iv3;
                        $composer.endReplaceGroup();
                        $composer.startReplaceGroup(-1609369893);
                        ComposerKt.sourceInformation($composer, "CC(remember):AssistActivity.kt#9igjgp");
                        boolean invalid$iv3 = $composer.changedInstance(this.this$0);
                        final AssistActivity assistActivity3 = this.this$0;
                        Object value$iv4 = $composer.rememberedValue();
                        if (invalid$iv3 || value$iv4 == Composer.Companion.getEmpty()) {
                            value$iv4 = new Function0() { // from class: ai.nous.aurora.AssistActivity$onCreate$1$1$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return C00001.invoke$lambda$7$lambda$6(assistActivity3);
                                }
                            };
                            $composer.updateRememberedValue(value$iv4);
                        }
                        $composer.endReplaceGroup();
                        AssistActivityKt.AssistUi(phase, statusLine, transcript, reply, orbStyle, speakReplies, function1, function0, function2, (Function0) value$iv4, $composer, 100663296);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer.skipToGroupEnd();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$1$lambda$0(AssistActivity this$0, boolean enabled) {
                    this$0.setSpeakReplies(enabled);
                    ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new AssistActivity$onCreate$1$1$1$1$1(this$0, enabled, null), 3, null);
                    if (!enabled) {
                        this$0.stopPlayback();
                    }
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$3$lambda$2(AssistActivity this$0) {
                    this$0.onOrbToggle();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$7$lambda$6(AssistActivity this$0) {
                    this$0.leaveAssist();
                    return Unit.INSTANCE;
                }
            }
        }), 1, null);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) { // from class: ai.nous.aurora.AssistActivity.onCreate.2
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                AssistActivity.this.leaveAssist();
            }
        });
        ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass3(null), 3, null);
    }

    /* JADX INFO: renamed from: ai.nous.aurora.AssistActivity$onCreate$3, reason: invalid class name */
    /* JADX INFO: compiled from: AssistActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.AssistActivity$onCreate$3", f = "AssistActivity.kt", i = {0, 1}, l = {169, AuroraMotion.CONTENT_MS, 171}, m = "invokeSuspend", n = {"store", "store"}, s = {"L$0", "L$0"})
    final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        Object L$0;
        Object L$1;
        int label;

        AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AssistActivity.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass3)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0078 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:16:0x0079  */
        /* JADX WARN: Code duplicated, block: B:19:0x0098 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:20:0x0099  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AssistActivity assistActivity;
            AuroraCredentialStore store;
            AssistActivity assistActivity2;
            Object assistAutoListen;
            AssistActivity assistActivity3;
            AssistActivity assistActivity4;
            Object assistOrbStyle;
            AssistActivity assistActivity5;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AuroraCredentialStore store2 = AssistActivity.this.getAppModule().getCredentialStore();
                    assistActivity = AssistActivity.this;
                    this.L$0 = store2;
                    this.L$1 = assistActivity;
                    this.label = 1;
                    Object assistSpeakReplies = store2.getAssistSpeakReplies(this);
                    if (assistSpeakReplies == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = assistSpeakReplies;
                    store = store2;
                    assistActivity.setSpeakReplies(((Boolean) $result).booleanValue());
                    assistActivity2 = AssistActivity.this;
                    this.L$0 = store;
                    this.L$1 = assistActivity2;
                    this.label = 2;
                    assistAutoListen = store.getAssistAutoListen(this);
                    if (assistAutoListen == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    assistActivity3 = assistActivity2;
                    $result = assistAutoListen;
                    assistActivity3.autoListen = ((Boolean) $result).booleanValue();
                    assistActivity4 = AssistActivity.this;
                    this.L$0 = assistActivity4;
                    this.L$1 = null;
                    this.label = 3;
                    assistOrbStyle = store.getAssistOrbStyle(this);
                    if (assistOrbStyle == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    assistActivity5 = assistActivity4;
                    $result = assistOrbStyle;
                    assistActivity5.setOrbStyle((String) $result);
                    AssistActivity.this.ensureMicThenPower();
                    return Unit.INSTANCE;
                case 1:
                    AssistActivity assistActivity6 = (AssistActivity) this.L$1;
                    AuroraCredentialStore store3 = (AuroraCredentialStore) this.L$0;
                    ResultKt.throwOnFailure($result);
                    store = store3;
                    assistActivity = assistActivity6;
                    assistActivity.setSpeakReplies(((Boolean) $result).booleanValue());
                    assistActivity2 = AssistActivity.this;
                    this.L$0 = store;
                    this.L$1 = assistActivity2;
                    this.label = 2;
                    assistAutoListen = store.getAssistAutoListen(this);
                    if (assistAutoListen == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    assistActivity3 = assistActivity2;
                    $result = assistAutoListen;
                    assistActivity3.autoListen = ((Boolean) $result).booleanValue();
                    assistActivity4 = AssistActivity.this;
                    this.L$0 = assistActivity4;
                    this.L$1 = null;
                    this.label = 3;
                    assistOrbStyle = store.getAssistOrbStyle(this);
                    if (assistOrbStyle == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    assistActivity5 = assistActivity4;
                    $result = assistOrbStyle;
                    assistActivity5.setOrbStyle((String) $result);
                    AssistActivity.this.ensureMicThenPower();
                    return Unit.INSTANCE;
                case 2:
                    AssistActivity assistActivity7 = (AssistActivity) this.L$1;
                    AuroraCredentialStore store4 = (AuroraCredentialStore) this.L$0;
                    ResultKt.throwOnFailure($result);
                    store = store4;
                    assistActivity3 = assistActivity7;
                    assistActivity3.autoListen = ((Boolean) $result).booleanValue();
                    assistActivity4 = AssistActivity.this;
                    this.L$0 = assistActivity4;
                    this.L$1 = null;
                    this.label = 3;
                    assistOrbStyle = store.getAssistOrbStyle(this);
                    if (assistOrbStyle == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    assistActivity5 = assistActivity4;
                    $result = assistOrbStyle;
                    assistActivity5.setOrbStyle((String) $result);
                    AssistActivity.this.ensureMicThenPower();
                    return Unit.INSTANCE;
                case 3:
                    assistActivity5 = (AssistActivity) this.L$0;
                    ResultKt.throwOnFailure($result);
                    assistActivity5.setOrbStyle((String) $result);
                    AssistActivity.this.ensureMicThenPower();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        this.leaveArmed = false;
        Job job = this.leaveCancelJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.leaveCancelJob = null;
        handlePowerGesture();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onUserLeaveHint() {
        this.leaveArmed = true;
        super.onUserLeaveHint();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.leaveArmed = false;
        Job job = this.leaveCancelJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        this.leaveCancelJob = null;
    }

    @Override // android.app.Activity
    protected void onStop() {
        if (isFinishing()) {
            if (AssistLockPolicy.INSTANCE.shouldCancelListenOnLeave(getPhase())) {
                cancelListening(AssistLockPolicy.STATUS_CANCELLED);
            }
        } else if (this.leaveArmed) {
            Job job = this.leaveCancelJob;
            if (job != null) {
                ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
            }
            this.leaveCancelJob = ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C01761(null), 3, null);
        }
        super.onStop();
    }

    /* JADX INFO: renamed from: ai.nous.aurora.AssistActivity$onStop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AssistActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.AssistActivity$onStop$1", f = "AssistActivity.kt", i = {}, l = {215}, m = "invokeSuspend", n = {}, s = {})
    final class C01761 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        C01761(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AssistActivity.this.new C01761(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01761)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(350L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (AssistActivity.this.leaveArmed && AssistLockPolicy.INSTANCE.shouldCancelListenOnLeave(AssistActivity.this.getPhase())) {
                AssistActivity.this.cancelListening(AssistLockPolicy.STATUS_CANCELLED);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        Job job = this.leaveCancelJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        Job job2 = this.autoStopJob;
        if (job2 != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job2, (CancellationException) null, 1, (Object) null);
        }
        Job job3 = this.sendJob;
        if (job3 != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job3, (CancellationException) null, 1, (Object) null);
        }
        HostAudioRecorder hostAudioRecorder = this.recorder;
        if (hostAudioRecorder != null) {
            hostAudioRecorder.cancel();
        }
        this.recorder = null;
        stopPlayback();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void leaveAssist() {
        this.leaveArmed = true;
        Job job = this.leaveCancelJob;
        if (job != null) {
            ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
        }
        cancelListening(AssistLockPolicy.STATUS_CANCELLED);
        stopPlayback();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopPlayback() {
        Unit unit;
        Unit unit2;
        try {
            Result.Companion companion = Result.Companion;
            AssistActivity $this$stopPlayback_u24lambda_u243 = this;
            MediaPlayer mediaPlayer = $this$stopPlayback_u24lambda_u243.player;
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                unit2 = Unit.INSTANCE;
            } else {
                unit2 = null;
            }
            ai.nous.aurora.compat.Compat.m7388constructorimpl(unit2);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.Companion;
            AssistActivity $this$stopPlayback_u24lambda_u244 = this;
            MediaPlayer mediaPlayer2 = $this$stopPlayback_u24lambda_u244.player;
            if (mediaPlayer2 != null) {
                mediaPlayer2.release();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            ai.nous.aurora.compat.Compat.m7388constructorimpl(unit);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.Companion;
            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
        }
        this.player = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelListening(String reason) {
        Unit unit;
        if (Intrinsics.areEqual(getPhase(), "recording") || this.recorder != null) {
            Job job = this.autoStopJob;
            if (job != null) {
                ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
            }
            this.autoStopJob = null;
            try {
                Result.Companion companion = Result.Companion;
                AssistActivity $this$cancelListening_u24lambda_u245 = this;
                HostAudioRecorder hostAudioRecorder = $this$cancelListening_u24lambda_u245.recorder;
                if (hostAudioRecorder != null) {
                    hostAudioRecorder.cancel();
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                ai.nous.aurora.compat.Compat.m7388constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
            }
            this.recorder = null;
            if (Intrinsics.areEqual(getPhase(), "recording")) {
                setPhase("idle");
                setStatusLine(reason);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensureMicThenPower() {
        boolean ok = ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0;
        if (ok) {
            setPhase("idle");
            handlePowerGesture();
        } else {
            setPhase("need_mic");
            this.micPermission.launch("android.permission.RECORD_AUDIO");
        }
    }

    private final boolean deviceLocked() {
        KeyguardManager kg = (KeyguardManager) getSystemService(KeyguardManager.class);
        if (kg != null && kg.isKeyguardLocked()) {
            return true;
        }
        return kg != null && kg.isDeviceLocked();
    }

    private final void handlePowerGesture() {
        switch (WhenMappings.$EnumSwitchMapping$0[AssistLockPolicy.INSTANCE.powerGestureAction(getPhase()).ordinal()]) {
            case 1:
                setStatusLine("Got it — processing…");
                stopAndSend();
                return;
            case 2:
                if (Intrinsics.areEqual(getPhase(), "need_mic")) {
                    ensureMicThenPower();
                    return;
                } else if (AssistLockPolicy.INSTANCE.shouldAutoStartListen(this.autoListen, getPhase())) {
                    startRecording();
                    return;
                } else {
                    setStatusLine(AssistLockPolicy.STATUS_READY);
                    return;
                }
            case 3:
                setStatusLine(AssistLockPolicy.STATUS_WORKING);
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void onOrbToggle() {
        String phase = getPhase();
        switch (phase.hashCode()) {
            case 3089282:
                if (!phase.equals("done")) {
                    return;
                }
                break;
            case 3227604:
                if (!phase.equals("idle")) {
                    return;
                }
                break;
            case 96784904:
                if (!phase.equals("error")) {
                    return;
                }
                break;
            case 993558001:
                if (phase.equals("recording")) {
                    stopAndSend();
                    return;
                }
                return;
            default:
                return;
        }
        startRecording();
    }

    private final void startRecording() {
        boolean locked = deviceLocked();
        if (AssistLockPolicy.INSTANCE.canBeginRecording(locked, getPhase())) {
            stopPlayback();
            try {
                File file = new File(getCacheDir(), "assist_capture.m4a");
                HostAudioRecorder r = new HostAudioRecorder(file);
                r.start(this);
                this.recorder = r;
                setPhase("recording");
                setStatusLine(AssistLockPolicy.STATUS_HOLD_POWER_AGAIN);
                setTranscript("");
                setReply("");
                Job job = this.autoStopJob;
                if (job != null) {
                    ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
                }
                this.autoStopJob = ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C01781(null), 3, null);
            } catch (Throwable t) {
                HostAudioRecorder hostAudioRecorder = this.recorder;
                if (hostAudioRecorder != null) {
                    hostAudioRecorder.cancel();
                }
                this.recorder = null;
                setPhase("error");
                String message = t.getMessage();
                if (message == null) {
                    message = "Could not start mic";
                }
                setStatusLine(message);
            }
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.AssistActivity$startRecording$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AssistActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.AssistActivity$startRecording$1", f = "AssistActivity.kt", i = {}, l = {328}, m = "invokeSuspend", n = {}, s = {})
    final class C01781 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        int label;

        C01781(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            return AssistActivity.this.new C01781(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01781)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(AssistLockPolicy.MAX_RECORDING_MILLIS, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (AssistLockPolicy.INSTANCE.canFinishRecording(AssistActivity.this.getPhase(), AssistActivity.this.recorder != null)) {
                AssistActivity.this.setStatusLine("Time cap · sending to Hermes…");
                AssistActivity.this.stopAndSend();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopAndSend() {
        HostAudioRecorder r;
        if (AssistLockPolicy.INSTANCE.canFinishRecording(getPhase(), this.recorder != null) && (r = this.recorder) != null) {
            this.recorder = null;
            Job job = this.autoStopJob;
            if (job != null) {
                ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job, (CancellationException) null, 1, (Object) null);
            }
            this.autoStopJob = null;
            setPhase("working");
            setStatusLine("Got it — transcribing…");
            Job job2 = this.sendJob;
            if (job2 != null) {
                ai.nous.aurora.compat.Compat.d_Job_DefaultImpls_cancel_4(job2, (CancellationException) null, 1, (Object) null);
            }
            this.sendJob = ai.nous.aurora.compat.Compat.d_BuildersKt_launch_6(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C01791(r, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.AssistActivity$stopAndSend$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AssistActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.AssistActivity$stopAndSend$1", f = "AssistActivity.kt", i = {0, 1, 1, 2, 2, 2, 3}, l = {354, 366, 383, 405}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "shouldSpeak", "$this$launch", "result", "shouldSpeak", "$this$launch"}, s = {"L$0", "L$0", "Z$0", "L$0", "L$1", "Z$0", "L$0"})
    final class C01791 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        final /* synthetic */ HostAudioRecorder $r;
        private /* synthetic */ Object L$0;
        Object L$1;
        boolean Z$0;
        int label;

        /* JADX INFO: renamed from: ai.nous.aurora.AssistActivity$stopAndSend$1$WhenMappings */
        /* JADX INFO: compiled from: AssistActivity.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[HostVoiceClient.Stage.values().length];
                try {
                    iArr[HostVoiceClient.Stage.UPLOADING.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[HostVoiceClient.Stage.PROCESSING.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01791(HostAudioRecorder hostAudioRecorder, Continuation continuation) {
            super(2, continuation);
            this.$r = hostAudioRecorder;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation<?> continuation) {
            C01791 c01791 = AssistActivity.this.new C01791(this.$r, continuation);
            c01791.L$0 = obj;
            return c01791;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01791)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:36:0x00ca A[Catch: all -> 0x01dd, TryCatch #5 {all -> 0x01dd, blocks: (B:54:0x0123, B:56:0x017e, B:58:0x0198, B:60:0x01a1, B:62:0x01ad, B:64:0x01c0, B:66:0x01c6, B:47:0x00fc, B:49:0x0105, B:22:0x005e, B:28:0x0086, B:34:0x00bb, B:36:0x00ca, B:40:0x00dc, B:44:0x00e4, B:33:0x00b2, B:25:0x006d, B:30:0x00a2), top: B:103:0x000f, inners: #0 }] */
        /* JADX WARN: Code duplicated, block: B:38:0x00d9  */
        /* JADX WARN: Code duplicated, block: B:39:0x00db  */
        /* JADX WARN: Code duplicated, block: B:42:0x00e1  */
        /* JADX WARN: Code duplicated, block: B:43:0x00e3  */
        /* JADX WARN: Code duplicated, block: B:46:0x00fb A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:49:0x0105 A[Catch: all -> 0x01dd, TryCatch #5 {all -> 0x01dd, blocks: (B:54:0x0123, B:56:0x017e, B:58:0x0198, B:60:0x01a1, B:62:0x01ad, B:64:0x01c0, B:66:0x01c6, B:47:0x00fc, B:49:0x0105, B:22:0x005e, B:28:0x0086, B:34:0x00bb, B:36:0x00ca, B:40:0x00dc, B:44:0x00e4, B:33:0x00b2, B:25:0x006d, B:30:0x00a2), top: B:103:0x000f, inners: #0 }] */
        /* JADX WARN: Code duplicated, block: B:51:0x011f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:52:0x0120  */
        /* JADX WARN: Code duplicated, block: B:54:0x0123 A[Catch: all -> 0x01dd, PHI: r3 r8 r9 r10
  0x0123: PHI (r3v22 ??) = (r3v32 ??), (r3v33 ??) binds: [B:48:0x0103, B:53:0x0122] A[DONT_GENERATE, DONT_INLINE]
  0x0123: PHI (r8v10 kotlinx.coroutines.CoroutineScope) = (r8v6 kotlinx.coroutines.CoroutineScope), (r8v11 kotlinx.coroutines.CoroutineScope) binds: [B:48:0x0103, B:53:0x0122] A[DONT_GENERATE, DONT_INLINE]
  0x0123: PHI (r9v15 boolean) = (r9v11 boolean), (r9v16 boolean) binds: [B:48:0x0103, B:53:0x0122] A[DONT_GENERATE, DONT_INLINE]
  0x0123: PHI (r10v9 ai.nous.aurora.data.remote.HostVoiceClient$DictateResult) = 
  (r10v8 ai.nous.aurora.data.remote.HostVoiceClient$DictateResult)
  (r10v10 ai.nous.aurora.data.remote.HostVoiceClient$DictateResult)
 binds: [B:48:0x0103, B:53:0x0122] A[DONT_GENERATE, DONT_INLINE], TryCatch #5 {all -> 0x01dd, blocks: (B:54:0x0123, B:56:0x017e, B:58:0x0198, B:60:0x01a1, B:62:0x01ad, B:64:0x01c0, B:66:0x01c6, B:47:0x00fc, B:49:0x0105, B:22:0x005e, B:28:0x0086, B:34:0x00bb, B:36:0x00ca, B:40:0x00dc, B:44:0x00e4, B:33:0x00b2, B:25:0x006d, B:30:0x00a2), top: B:103:0x000f, inners: #0 }] */
        /* JADX WARN: Code duplicated, block: B:56:0x017e A[Catch: all -> 0x01dd, TryCatch #5 {all -> 0x01dd, blocks: (B:54:0x0123, B:56:0x017e, B:58:0x0198, B:60:0x01a1, B:62:0x01ad, B:64:0x01c0, B:66:0x01c6, B:47:0x00fc, B:49:0x0105, B:22:0x005e, B:28:0x0086, B:34:0x00bb, B:36:0x00ca, B:40:0x00dc, B:44:0x00e4, B:33:0x00b2, B:25:0x006d, B:30:0x00a2), top: B:103:0x000f, inners: #0 }] */
        /* JADX WARN: Code duplicated, block: B:57:0x0196  */
        /* JADX WARN: Code duplicated, block: B:60:0x01a1 A[Catch: all -> 0x01dd, TryCatch #5 {all -> 0x01dd, blocks: (B:54:0x0123, B:56:0x017e, B:58:0x0198, B:60:0x01a1, B:62:0x01ad, B:64:0x01c0, B:66:0x01c6, B:47:0x00fc, B:49:0x0105, B:22:0x005e, B:28:0x0086, B:34:0x00bb, B:36:0x00ca, B:40:0x00dc, B:44:0x00e4, B:33:0x00b2, B:25:0x006d, B:30:0x00a2), top: B:103:0x000f, inners: #0 }] */
        /* JADX WARN: Code duplicated, block: B:64:0x01c0 A[Catch: all -> 0x01dd, TryCatch #5 {all -> 0x01dd, blocks: (B:54:0x0123, B:56:0x017e, B:58:0x0198, B:60:0x01a1, B:62:0x01ad, B:64:0x01c0, B:66:0x01c6, B:47:0x00fc, B:49:0x0105, B:22:0x005e, B:28:0x0086, B:34:0x00bb, B:36:0x00ca, B:40:0x00dc, B:44:0x00e4, B:33:0x00b2, B:25:0x006d, B:30:0x00a2), top: B:103:0x000f, inners: #0 }] */
        /* JADX WARN: Code duplicated, block: B:68:0x01d6 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:69:0x01d7  */
        /* JADX WARN: Code duplicated, block: B:76:0x01e6  */
        /* JADX WARN: Code duplicated, block: B:83:0x020b  */
        /* JADX WARN: Code duplicated, block: B:85:0x0213  */
        /* JADX WARN: Code duplicated, block: B:88:0x022b  */
        /* JADX WARN: Code duplicated, block: B:92:0x0242  */
        /* JADX WARN: Code duplicated, block: B:93:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v10, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v11 */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v6 */
        /* JADX WARN: Type inference failed for: r2v7 */
        /* JADX WARN: Type inference failed for: r2v8 */
        /* JADX WARN: Type inference failed for: r2v9 */
        /* JADX WARN: Type inference failed for: r3v0, types: [int] */
        /* JADX WARN: Type inference failed for: r3v1 */
        /* JADX WARN: Type inference failed for: r3v16 */
        /* JADX WARN: Type inference failed for: r3v17 */
        /* JADX WARN: Type inference failed for: r3v19 */
        /* JADX WARN: Type inference failed for: r3v22 */
        /* JADX WARN: Type inference failed for: r3v24 */
        /* JADX WARN: Type inference failed for: r3v27 */
        /* JADX WARN: Type inference failed for: r3v30 */
        /* JADX WARN: Type inference failed for: r3v31 */
        /* JADX WARN: Type inference failed for: r3v32 */
        /* JADX WARN: Type inference failed for: r3v33 */
        /* JADX WARN: Type inference failed for: r3v34 */
        /* JADX WARN: Type inference failed for: r3v35 */
        /* JADX WARN: Type inference failed for: r3v36 */
        /* JADX WARN: Type inference failed for: r3v9 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object r2;
            Throwable th = null;
            String message;
            String strTake;
            CoroutineScope coroutineScope;
            Object obj2;
            File file;
            String str;
            boolean speakReplies;
            boolean z;
            final boolean z2;
            Object objDictate;
            Object obj3;
            Object r3;
            HostVoiceClient.DictateResult dictateResult;
            String sessionId;
            AuroraCredentialStore credentialStore;
            CoroutineScope coroutineScope2;
            Object r4;
            AssistDeviceActions.ActionResult actionResultTryHandle;
            String str2;
            String audioUrl;
            AssistActivity assistActivity;
            Object r5;
            Object r6;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r7 = this.label;
            try {
                switch (r7) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        coroutineScope = (CoroutineScope) this.L$0;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C00011(this.$r, AssistActivity.this, null), this);
                        obj2 = objCoroutineScope;
                        if (objCoroutineScope == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Pair pair = (Pair) obj2;
                        file = (File) pair.component1();
                        str = (String) pair.component2();
                        if (!AssistLockPolicy.INSTANCE.isUsableCapture(file.length())) {
                            try {
                                Result.Companion companion = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxBoolean(file.delete()));
                                break;
                            } catch (Throwable th2) {
                                Result.Companion companion2 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                            }
                            AssistActivity.this.setPhase("error");
                            AssistActivity.this.setStatusLine(AssistLockPolicy.STATUS_TOO_SHORT);
                            return Unit.INSTANCE;
                        }
                        speakReplies = AssistActivity.this.getSpeakReplies();
                        HostVoiceClient voiceClient = AssistActivity.this.getVoiceClient();
                        if (speakReplies) {
                            z = true;
                        } else {
                            z = false;
                        }
                        final AssistActivity assistActivity2 = AssistActivity.this;
                        if (speakReplies) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        this.L$0 = coroutineScope;
                        this.Z$0 = speakReplies;
                        this.label = 2;
                        objDictate = voiceClient.dictate(file, z, str, new Function1() { // from class: ai.nous.aurora.AssistActivity$stopAndSend$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                return C01791.invokeSuspend$lambda$2(assistActivity2, z2, (HostVoiceClient.Stage) obj4);
                            }
                        }, this);
                        r3 = r7;
                        obj3 = objDictate;
                        if (objDictate == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dictateResult = (HostVoiceClient.DictateResult) obj3;
                        sessionId = dictateResult.getSessionId();
                        r4 = r3;
                        if (sessionId != null) {
                            credentialStore = AssistActivity.this.getAppModule().getCredentialStore();
                            this.L$0 = coroutineScope;
                            this.L$1 = dictateResult;
                            this.Z$0 = speakReplies;
                            this.label = 3;
                            if (credentialStore.setAssistSessionId(sessionId, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            coroutineScope2 = coroutineScope;
                            r6 = r3;
                            coroutineScope = coroutineScope2;
                            r4 = r6;
                            AssistActivity.this.setTranscript(dictateResult.getTranscript());
                            AssistActivity.this.setReply(StringsKt.trim((CharSequence) new Regex("(?im)^\\s*AURORA_(?:DEVICE_ACTIONS:|ACTION:).*$").replace(new Regex("\\[\\[audio_as_voice\\]\\]\\s*MEDIA:\\S+").replace(dictateResult.getReplyText(), ""), "")).toString());
                            actionResultTryHandle = AssistDeviceActions.INSTANCE.tryHandle(AssistActivity.this, dictateResult.getTranscript(), dictateResult.getReplyText(), dictateResult.getDeviceActionsJson());
                            AssistActivity.this.setPhase("done");
                            AssistActivity assistActivity3 = AssistActivity.this;
                            if (actionResultTryHandle.getHandled()) {
                                str2 = actionResultTryHandle.getMessage() + " · short-hold power to talk again";
                            } else {
                                str2 = "Done — short-hold power to talk again";
                            }
                            assistActivity3.setStatusLine(str2);
                            if (actionResultTryHandle.getHandled() && !StringsKt.isBlank(actionResultTryHandle.getMessage())) {
                                Toast.makeText(AssistActivity.this, actionResultTryHandle.getMessage(), 0).show();
                            }
                            if (speakReplies && (audioUrl = dictateResult.getAudioUrl()) != null) {
                                assistActivity = AssistActivity.this;
                                this.L$0 = coroutineScope;
                                this.L$1 = null;
                                this.label = 4;
                                if (assistActivity.playRemote(audioUrl, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                r5 = r4;
                            }
                        } else {
                            AssistActivity.this.setTranscript(dictateResult.getTranscript());
                            AssistActivity.this.setReply(StringsKt.trim((CharSequence) new Regex("(?im)^\\s*AURORA_(?:DEVICE_ACTIONS:|ACTION:).*$").replace(new Regex("\\[\\[audio_as_voice\\]\\]\\s*MEDIA:\\S+").replace(dictateResult.getReplyText(), ""), "")).toString());
                            actionResultTryHandle = AssistDeviceActions.INSTANCE.tryHandle(AssistActivity.this, dictateResult.getTranscript(), dictateResult.getReplyText(), dictateResult.getDeviceActionsJson());
                            AssistActivity.this.setPhase("done");
                            AssistActivity assistActivity4 = AssistActivity.this;
                            if (actionResultTryHandle.getHandled()) {
                                str2 = actionResultTryHandle.getMessage() + " · short-hold power to talk again";
                            } else {
                                str2 = "Done — short-hold power to talk again";
                            }
                            assistActivity4.setStatusLine(str2);
                            if (actionResultTryHandle.getHandled()) {
                                Toast.makeText(AssistActivity.this, actionResultTryHandle.getMessage(), 0).show();
                            }
                            if (speakReplies && (audioUrl = dictateResult.getAudioUrl()) != null) {
                                assistActivity = AssistActivity.this;
                                this.L$0 = coroutineScope;
                                this.L$1 = null;
                                this.label = 4;
                                if (assistActivity.playRemote(audioUrl, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                r5 = r4;
                            }
                        }
                        return Unit.INSTANCE;
                    case 1:
                        Object obj4 = obj;
                        coroutineScope = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj4);
                        obj2 = obj4;
                        Pair pair2 = (Pair) obj2;
                        file = (File) pair2.component1();
                        str = (String) pair2.component2();
                        if (!AssistLockPolicy.INSTANCE.isUsableCapture(file.length())) {
                            Result.Companion companion3 = Result.Companion;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxBoolean(file.delete()));
                            AssistActivity.this.setPhase("error");
                            AssistActivity.this.setStatusLine(AssistLockPolicy.STATUS_TOO_SHORT);
                            return Unit.INSTANCE;
                        }
                        speakReplies = AssistActivity.this.getSpeakReplies();
                        HostVoiceClient voiceClient2 = AssistActivity.this.getVoiceClient();
                        if (speakReplies) {
                            z = true;
                        } else {
                            z = false;
                        }
                        final AssistActivity assistActivity5 = AssistActivity.this;
                        if (speakReplies) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        this.L$0 = coroutineScope;
                        this.Z$0 = speakReplies;
                        this.label = 2;
                        objDictate = voiceClient2.dictate(file, z, str, new Function1() { // from class: ai.nous.aurora.AssistActivity$stopAndSend$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj5) {
                                return C01791.invokeSuspend$lambda$2(assistActivity5, z2, (HostVoiceClient.Stage) obj5);
                            }
                        }, this);
                        r3 = r7;
                        obj3 = objDictate;
                        if (objDictate == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dictateResult = (HostVoiceClient.DictateResult) obj3;
                        sessionId = dictateResult.getSessionId();
                        r4 = r3;
                        if (sessionId != null) {
                            credentialStore = AssistActivity.this.getAppModule().getCredentialStore();
                            this.L$0 = coroutineScope;
                            this.L$1 = dictateResult;
                            this.Z$0 = speakReplies;
                            this.label = 3;
                            if (credentialStore.setAssistSessionId(sessionId, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            coroutineScope2 = coroutineScope;
                            r6 = r3;
                            coroutineScope = coroutineScope2;
                            r4 = r6;
                            AssistActivity.this.setTranscript(dictateResult.getTranscript());
                            AssistActivity.this.setReply(StringsKt.trim((CharSequence) new Regex("(?im)^\\s*AURORA_(?:DEVICE_ACTIONS:|ACTION:).*$").replace(new Regex("\\[\\[audio_as_voice\\]\\]\\s*MEDIA:\\S+").replace(dictateResult.getReplyText(), ""), "")).toString());
                            actionResultTryHandle = AssistDeviceActions.INSTANCE.tryHandle(AssistActivity.this, dictateResult.getTranscript(), dictateResult.getReplyText(), dictateResult.getDeviceActionsJson());
                            AssistActivity.this.setPhase("done");
                            AssistActivity assistActivity6 = AssistActivity.this;
                            if (actionResultTryHandle.getHandled()) {
                                str2 = actionResultTryHandle.getMessage() + " · short-hold power to talk again";
                            } else {
                                str2 = "Done — short-hold power to talk again";
                            }
                            assistActivity6.setStatusLine(str2);
                            if (actionResultTryHandle.getHandled()) {
                                Toast.makeText(AssistActivity.this, actionResultTryHandle.getMessage(), 0).show();
                            }
                            if (speakReplies && (audioUrl = dictateResult.getAudioUrl()) != null) {
                                assistActivity = AssistActivity.this;
                                this.L$0 = coroutineScope;
                                this.L$1 = null;
                                this.label = 4;
                                if (assistActivity.playRemote(audioUrl, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                r5 = r4;
                            }
                        } else {
                            AssistActivity.this.setTranscript(dictateResult.getTranscript());
                            AssistActivity.this.setReply(StringsKt.trim((CharSequence) new Regex("(?im)^\\s*AURORA_(?:DEVICE_ACTIONS:|ACTION:).*$").replace(new Regex("\\[\\[audio_as_voice\\]\\]\\s*MEDIA:\\S+").replace(dictateResult.getReplyText(), ""), "")).toString());
                            actionResultTryHandle = AssistDeviceActions.INSTANCE.tryHandle(AssistActivity.this, dictateResult.getTranscript(), dictateResult.getReplyText(), dictateResult.getDeviceActionsJson());
                            AssistActivity.this.setPhase("done");
                            AssistActivity assistActivity7 = AssistActivity.this;
                            if (actionResultTryHandle.getHandled()) {
                                str2 = actionResultTryHandle.getMessage() + " · short-hold power to talk again";
                            } else {
                                str2 = "Done — short-hold power to talk again";
                            }
                            assistActivity7.setStatusLine(str2);
                            if (actionResultTryHandle.getHandled()) {
                                Toast.makeText(AssistActivity.this, actionResultTryHandle.getMessage(), 0).show();
                            }
                            if (speakReplies && (audioUrl = dictateResult.getAudioUrl()) != null) {
                                assistActivity = AssistActivity.this;
                                this.L$0 = coroutineScope;
                                this.L$1 = null;
                                this.label = 4;
                                if (assistActivity.playRemote(audioUrl, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                r5 = r4;
                            }
                        }
                        return Unit.INSTANCE;
                    case 2:
                        Object obj5 = obj;
                        boolean z3 = this.Z$0;
                        CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                        try {
                            ResultKt.throwOnFailure(obj5);
                            obj3 = obj5;
                            speakReplies = z3;
                            coroutineScope = coroutineScope3;
                            r3 = obj5;
                            dictateResult = (HostVoiceClient.DictateResult) obj3;
                            sessionId = dictateResult.getSessionId();
                            r4 = r3;
                            if (sessionId != null) {
                                credentialStore = AssistActivity.this.getAppModule().getCredentialStore();
                                this.L$0 = coroutineScope;
                                this.L$1 = dictateResult;
                                this.Z$0 = speakReplies;
                                this.label = 3;
                                if (credentialStore.setAssistSessionId(sessionId, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                coroutineScope2 = coroutineScope;
                                r6 = r3;
                                coroutineScope = coroutineScope2;
                                r4 = r6;
                                AssistActivity.this.setTranscript(dictateResult.getTranscript());
                                AssistActivity.this.setReply(StringsKt.trim((CharSequence) new Regex("(?im)^\\s*AURORA_(?:DEVICE_ACTIONS:|ACTION:).*$").replace(new Regex("\\[\\[audio_as_voice\\]\\]\\s*MEDIA:\\S+").replace(dictateResult.getReplyText(), ""), "")).toString());
                                actionResultTryHandle = AssistDeviceActions.INSTANCE.tryHandle(AssistActivity.this, dictateResult.getTranscript(), dictateResult.getReplyText(), dictateResult.getDeviceActionsJson());
                                AssistActivity.this.setPhase("done");
                                AssistActivity assistActivity8 = AssistActivity.this;
                                if (actionResultTryHandle.getHandled()) {
                                    str2 = actionResultTryHandle.getMessage() + " · short-hold power to talk again";
                                } else {
                                    str2 = "Done — short-hold power to talk again";
                                }
                                assistActivity8.setStatusLine(str2);
                                if (actionResultTryHandle.getHandled()) {
                                    Toast.makeText(AssistActivity.this, actionResultTryHandle.getMessage(), 0).show();
                                }
                                if (speakReplies && (audioUrl = dictateResult.getAudioUrl()) != null) {
                                    assistActivity = AssistActivity.this;
                                    this.L$0 = coroutineScope;
                                    this.L$1 = null;
                                    this.label = 4;
                                    if (assistActivity.playRemote(audioUrl, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    r5 = r4;
                                }
                            } else {
                                AssistActivity.this.setTranscript(dictateResult.getTranscript());
                                AssistActivity.this.setReply(StringsKt.trim((CharSequence) new Regex("(?im)^\\s*AURORA_(?:DEVICE_ACTIONS:|ACTION:).*$").replace(new Regex("\\[\\[audio_as_voice\\]\\]\\s*MEDIA:\\S+").replace(dictateResult.getReplyText(), ""), "")).toString());
                                actionResultTryHandle = AssistDeviceActions.INSTANCE.tryHandle(AssistActivity.this, dictateResult.getTranscript(), dictateResult.getReplyText(), dictateResult.getDeviceActionsJson());
                                AssistActivity.this.setPhase("done");
                                AssistActivity assistActivity9 = AssistActivity.this;
                                if (actionResultTryHandle.getHandled()) {
                                    str2 = actionResultTryHandle.getMessage() + " · short-hold power to talk again";
                                } else {
                                    str2 = "Done — short-hold power to talk again";
                                }
                                assistActivity9.setStatusLine(str2);
                                if (actionResultTryHandle.getHandled()) {
                                    Toast.makeText(AssistActivity.this, actionResultTryHandle.getMessage(), 0).show();
                                }
                                if (speakReplies && (audioUrl = dictateResult.getAudioUrl()) != null) {
                                    assistActivity = AssistActivity.this;
                                    this.L$0 = coroutineScope;
                                    this.L$1 = null;
                                    this.label = 4;
                                    if (assistActivity.playRemote(audioUrl, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    r5 = r4;
                                }
                            }
                        } catch (Throwable th3) {
                            r2 = obj5;
                            if (!(th3 instanceof CancellationException)) {
                                throw th3;
                            }
                            HostAudioRecorder hostAudioRecorder = this.$r;
                            try {
                                Result.Companion companion4 = Result.Companion;
                                hostAudioRecorder.cancel();
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                            } catch (Throwable th4) {
                                Result.Companion companion5 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th4));
                            }
                            AssistActivity.this.setPhase("error");
                            AssistActivity assistActivity10 = AssistActivity.this;
                            message = th3.getMessage();
                            if (message != null || (strTake = StringsKt.take(message, 160)) == null) {
                                strTake = "Voice failed";
                            }
                            assistActivity10.setStatusLine(strTake);
                            if (!StringsKt.contains((CharSequence) AssistActivity.this.getStatusLine(), (CharSequence) "cancel", true)) {
                                Toast.makeText(AssistActivity.this, AssistActivity.this.getStatusLine(), 1).show();
                            }
                            break;
                        }
                        return Unit.INSTANCE;
                    case 3:
                        Object obj6 = obj;
                        speakReplies = this.Z$0;
                        dictateResult = (HostVoiceClient.DictateResult) this.L$1;
                        coroutineScope2 = (CoroutineScope) this.L$0;
                        try {
                            ResultKt.throwOnFailure(obj6);
                            r6 = obj6;
                            coroutineScope = coroutineScope2;
                            r4 = r6;
                            AssistActivity.this.setTranscript(dictateResult.getTranscript());
                            AssistActivity.this.setReply(StringsKt.trim((CharSequence) new Regex("(?im)^\\s*AURORA_(?:DEVICE_ACTIONS:|ACTION:).*$").replace(new Regex("\\[\\[audio_as_voice\\]\\]\\s*MEDIA:\\S+").replace(dictateResult.getReplyText(), ""), "")).toString());
                            actionResultTryHandle = AssistDeviceActions.INSTANCE.tryHandle(AssistActivity.this, dictateResult.getTranscript(), dictateResult.getReplyText(), dictateResult.getDeviceActionsJson());
                            AssistActivity.this.setPhase("done");
                            AssistActivity assistActivity11 = AssistActivity.this;
                            if (actionResultTryHandle.getHandled()) {
                                str2 = actionResultTryHandle.getMessage() + " · short-hold power to talk again";
                            } else {
                                str2 = "Done — short-hold power to talk again";
                            }
                            assistActivity11.setStatusLine(str2);
                            if (actionResultTryHandle.getHandled()) {
                                Toast.makeText(AssistActivity.this, actionResultTryHandle.getMessage(), 0).show();
                            }
                            if (speakReplies && (audioUrl = dictateResult.getAudioUrl()) != null) {
                                assistActivity = AssistActivity.this;
                                this.L$0 = coroutineScope;
                                this.L$1 = null;
                                this.label = 4;
                                if (assistActivity.playRemote(audioUrl, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                r5 = r4;
                            }
                        } catch (Throwable th5) {
                            r2 = obj6;
                            if (!(th5 instanceof CancellationException)) {
                                throw th5;
                            }
                            HostAudioRecorder hostAudioRecorder2 = this.$r;
                            Result.Companion companion6 = Result.Companion;
                            hostAudioRecorder2.cancel();
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                            AssistActivity.this.setPhase("error");
                            AssistActivity assistActivity12 = AssistActivity.this;
                            message = th5.getMessage();
                            if (message != null) {
                                strTake = "Voice failed";
                            } else {
                                strTake = "Voice failed";
                            }
                            assistActivity12.setStatusLine(strTake);
                            if (!StringsKt.contains((CharSequence) AssistActivity.this.getStatusLine(), (CharSequence) "cancel", true)) {
                                Toast.makeText(AssistActivity.this, AssistActivity.this.getStatusLine(), 1).show();
                            }
                        }
                        return Unit.INSTANCE;
                    case 4:
                        r2 = obj;
                        try {
                            ResultKt.throwOnFailure(r2);
                            r5 = r2;
                        } catch (Throwable th6) {
                            if (!(th6 instanceof CancellationException)) {
                                throw th6;
                            }
                            HostAudioRecorder hostAudioRecorder3 = this.$r;
                            Result.Companion companion7 = Result.Companion;
                            hostAudioRecorder3.cancel();
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                            AssistActivity.this.setPhase("error");
                            AssistActivity assistActivity13 = AssistActivity.this;
                            message = th6.getMessage();
                            if (message != null) {
                                strTake = "Voice failed";
                            } else {
                                strTake = "Voice failed";
                            }
                            assistActivity13.setStatusLine(strTake);
                            if (!StringsKt.contains((CharSequence) AssistActivity.this.getStatusLine(), (CharSequence) "cancel", true)) {
                                Toast.makeText(AssistActivity.this, AssistActivity.this.getStatusLine(), 1).show();
                            }
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th7) {
                r2 = r7;
                if (!(th7 instanceof CancellationException)) {
                    throw th7;
                }
                HostAudioRecorder hostAudioRecorder4 = this.$r;
                Result.Companion companion8 = Result.Companion;
                hostAudioRecorder4.cancel();
                ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                AssistActivity.this.setPhase("error");
                AssistActivity assistActivity14 = AssistActivity.this;
                message = th7.getMessage();
                if (message != null) {
                    strTake = "Voice failed";
                } else {
                    strTake = "Voice failed";
                }
                assistActivity14.setStatusLine(strTake);
                if (!StringsKt.contains((CharSequence) AssistActivity.this.getStatusLine(), (CharSequence) "cancel", true)) {
                    Toast.makeText(AssistActivity.this, AssistActivity.this.getStatusLine(), 1).show();
                }
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: ai.nous.aurora.AssistActivity$stopAndSend$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: AssistActivity.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Ljava/io/File;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "ai.nous.aurora.AssistActivity$stopAndSend$1$1", f = "AssistActivity.kt", i = {0}, l = {357, 357}, m = "invokeSuspend", n = {"sessionDeferred"}, s = {"L$0"})
        static final class C00011 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
            final /* synthetic */ HostAudioRecorder $r;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ AssistActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00011(HostAudioRecorder hostAudioRecorder, AssistActivity assistActivity, Continuation continuation) {
                super(2, continuation);
                this.$r = hostAudioRecorder;
                this.this$0 = assistActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation<?> continuation) {
                C00011 c00011 = new C00011(this.$r, this.this$0, continuation);
                c00011.L$0 = obj;
                return c00011;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return invoke2(coroutineScope, (Continuation) continuation);
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00011)(Object) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:13:0x0071 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:14:0x0072  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Deferred sessionDeferred;
                Object objAwait;
                Object objAwait2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                        Deferred fileDeferred = ai.nous.aurora.compat.Compat.d_BuildersKt_async_6($this$coroutineScope, Dispatchers.getIO(), null, new AssistActivity$stopAndSend$1$1$fileDeferred$1(this.$r, null), 2, null);
                        sessionDeferred = ai.nous.aurora.compat.Compat.d_BuildersKt_async_6($this$coroutineScope, null, null, new AssistActivity$stopAndSend$1$1$sessionDeferred$1(this.this$0, null), 3, null);
                        this.L$0 = sessionDeferred;
                        this.label = 1;
                        objAwait = fileDeferred.await(this);
                        if (objAwait == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        this.L$0 = objAwait;
                        this.label = 2;
                        objAwait2 = sessionDeferred.await(this);
                        if (objAwait2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objAwait2;
                        return TuplesKt.to(objAwait, $result);
                    case 1:
                        sessionDeferred = (Deferred) this.L$0;
                        ResultKt.throwOnFailure($result);
                        objAwait = $result;
                        this.L$0 = objAwait;
                        this.label = 2;
                        objAwait2 = sessionDeferred.await(this);
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

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$2(final AssistActivity this$0, final boolean $shouldSpeak, final HostVoiceClient.Stage stage) {
            this$0.runOnUiThread(new Runnable() { // from class: ai.nous.aurora.AssistActivity$stopAndSend$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    C01791.invokeSuspend$lambda$2$lambda$1(this$0, stage, $shouldSpeak);
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$2$lambda$1(AssistActivity this$0, HostVoiceClient.Stage $stage, boolean $shouldSpeak) {
            String str;
            switch (WhenMappings.$EnumSwitchMapping$0[$stage.ordinal()]) {
                case 1:
                    str = "Uploading audio…";
                    break;
                case 2:
                    if ($shouldSpeak) {
                        str = "Host working · Whisper → Hermes → voice…";
                    } else {
                        str = "Host working · Whisper → Hermes…";
                    }
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            this$0.setStatusLine(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:30:0x0081  */
    /* JADX WARN: Code duplicated, block: B:41:0x00b4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:42:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ba A[Catch: all -> 0x0106, TryCatch #0 {all -> 0x0106, blocks: (B:13:0x002e, B:54:0x0100, B:16:0x003c, B:51:0x00e5, B:19:0x0046, B:43:0x00b6, B:45:0x00ba, B:47:0x00bd, B:22:0x0055, B:28:0x007d, B:31:0x0083, B:33:0x008c, B:35:0x008f, B:37:0x0097, B:39:0x009a, B:25:0x0060), top: B:60:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x00bd A[Catch: all -> 0x0106, TryCatch #0 {all -> 0x0106, blocks: (B:13:0x002e, B:54:0x0100, B:16:0x003c, B:51:0x00e5, B:19:0x0046, B:43:0x00b6, B:45:0x00ba, B:47:0x00bd, B:22:0x0055, B:28:0x007d, B:31:0x0083, B:33:0x008c, B:35:0x008f, B:37:0x0097, B:39:0x009a, B:25:0x0060), top: B:60:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00e3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:50:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:53:0x00ff A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object playRemote(String str, Continuation continuation) {
        C01771 c01771;
        String url;
        AssistActivity $this$playRemote_u24lambda_u246;
        Object apiKey;
        String key;
        Object objWithContext;
        AssistActivity $this$playRemote_u24lambda_u247;
        byte[] bytes;
        File tmp;
        CoroutineDispatcher io;
        AssistActivity$playRemote$2$1 assistActivity$playRemote$2$1;
        File tmp2;
        MainCoroutineDispatcher main;
        AssistActivity$playRemote$2$2 assistActivity$playRemote$2$2;
        if (continuation instanceof C01771) {
            c01771 = (C01771) continuation;
            if ((c01771.label & Integer.MIN_VALUE) != 0) {
                c01771.label -= Integer.MIN_VALUE;
            } else {
                c01771 = new C01771(continuation);
            }
        } else {
            c01771 = new C01771(continuation);
        }
        Object $result = c01771.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c01771.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    url = str;
                    Result.Companion companion = Result.Companion;
                    $this$playRemote_u24lambda_u246 = this;
                    AuroraCredentialStore credentialStore = $this$playRemote_u24lambda_u246.getAppModule().getCredentialStore();
                    c01771.L$0 = url;
                    c01771.L$1 = $this$playRemote_u24lambda_u246;
                    c01771.label = 1;
                    apiKey = credentialStore.getApiKey(c01771);
                    if (apiKey == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    key = (String) apiKey;
                    if (key == null) {
                        key = "";
                    }
                    if (StringsKt.isBlank(key) && AssistLockPolicy.INSTANCE.isTrustedBridgeAudioUrl(url)) {
                        CoroutineDispatcher io2 = Dispatchers.getIO();
                        AssistActivity$playRemote$2$bytes$1 assistActivity$playRemote$2$bytes$1 = new AssistActivity$playRemote$2$bytes$1(url, key, null);
                        c01771.L$0 = $this$playRemote_u24lambda_u246;
                        c01771.L$1 = null;
                        c01771.label = 2;
                        objWithContext = BuildersKt.withContext(io2, assistActivity$playRemote$2$bytes$1, c01771);
                        if (objWithContext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $this$playRemote_u24lambda_u247 = $this$playRemote_u24lambda_u246;
                        bytes = (byte[]) objWithContext;
                        if (bytes == null) {
                            return Unit.INSTANCE;
                        }
                        tmp = new File($this$playRemote_u24lambda_u247.getCacheDir(), "assist_reply.ogg");
                        io = Dispatchers.getIO();
                        assistActivity$playRemote$2$1 = new AssistActivity$playRemote$2$1(tmp, bytes, null);
                        c01771.L$0 = $this$playRemote_u24lambda_u247;
                        c01771.L$1 = tmp;
                        c01771.label = 3;
                        if (BuildersKt.withContext(io, assistActivity$playRemote$2$1, c01771) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        tmp2 = tmp;
                        main = Dispatchers.getMain();
                        assistActivity$playRemote$2$2 = new AssistActivity$playRemote$2$2($this$playRemote_u24lambda_u247, tmp2, null);
                        c01771.L$0 = null;
                        c01771.L$1 = null;
                        c01771.label = 4;
                        if (BuildersKt.withContext(main, assistActivity$playRemote$2$2, c01771) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                case 1:
                    $this$playRemote_u24lambda_u246 = (AssistActivity) c01771.L$1;
                    url = (String) c01771.L$0;
                    ResultKt.throwOnFailure($result);
                    apiKey = $result;
                    key = (String) apiKey;
                    if (key == null) {
                        key = "";
                    }
                    if (StringsKt.isBlank(key)) {
                        return Unit.INSTANCE;
                    }
                    CoroutineDispatcher io3 = Dispatchers.getIO();
                    AssistActivity$playRemote$2$bytes$1 assistActivity$playRemote$2$bytes$2 = new AssistActivity$playRemote$2$bytes$1(url, key, null);
                    c01771.L$0 = $this$playRemote_u24lambda_u246;
                    c01771.L$1 = null;
                    c01771.label = 2;
                    objWithContext = BuildersKt.withContext(io3, assistActivity$playRemote$2$bytes$2, c01771);
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$playRemote_u24lambda_u247 = $this$playRemote_u24lambda_u246;
                    bytes = (byte[]) objWithContext;
                    if (bytes == null) {
                        return Unit.INSTANCE;
                    }
                    tmp = new File($this$playRemote_u24lambda_u247.getCacheDir(), "assist_reply.ogg");
                    io = Dispatchers.getIO();
                    assistActivity$playRemote$2$1 = new AssistActivity$playRemote$2$1(tmp, bytes, null);
                    c01771.L$0 = $this$playRemote_u24lambda_u247;
                    c01771.L$1 = tmp;
                    c01771.label = 3;
                    if (BuildersKt.withContext(io, assistActivity$playRemote$2$1, c01771) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    tmp2 = tmp;
                    main = Dispatchers.getMain();
                    assistActivity$playRemote$2$2 = new AssistActivity$playRemote$2$2($this$playRemote_u24lambda_u247, tmp2, null);
                    c01771.L$0 = null;
                    c01771.L$1 = null;
                    c01771.label = 4;
                    if (BuildersKt.withContext(main, assistActivity$playRemote$2$2, c01771) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                    return Unit.INSTANCE;
                case 2:
                    AssistActivity $this$playRemote_u24lambda_u248 = (AssistActivity) c01771.L$0;
                    ResultKt.throwOnFailure($result);
                    objWithContext = $result;
                    $this$playRemote_u24lambda_u247 = $this$playRemote_u24lambda_u248;
                    bytes = (byte[]) objWithContext;
                    if (bytes == null) {
                        return Unit.INSTANCE;
                    }
                    tmp = new File($this$playRemote_u24lambda_u247.getCacheDir(), "assist_reply.ogg");
                    io = Dispatchers.getIO();
                    assistActivity$playRemote$2$1 = new AssistActivity$playRemote$2$1(tmp, bytes, null);
                    c01771.L$0 = $this$playRemote_u24lambda_u247;
                    c01771.L$1 = tmp;
                    c01771.label = 3;
                    if (BuildersKt.withContext(io, assistActivity$playRemote$2$1, c01771) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    tmp2 = tmp;
                    main = Dispatchers.getMain();
                    assistActivity$playRemote$2$2 = new AssistActivity$playRemote$2$2($this$playRemote_u24lambda_u247, tmp2, null);
                    c01771.L$0 = null;
                    c01771.L$1 = null;
                    c01771.label = 4;
                    if (BuildersKt.withContext(main, assistActivity$playRemote$2$2, c01771) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                    return Unit.INSTANCE;
                case 3:
                    tmp2 = (File) c01771.L$1;
                    $this$playRemote_u24lambda_u247 = (AssistActivity) c01771.L$0;
                    ResultKt.throwOnFailure($result);
                    main = Dispatchers.getMain();
                    assistActivity$playRemote$2$2 = new AssistActivity$playRemote$2$2($this$playRemote_u24lambda_u247, tmp2, null);
                    c01771.L$0 = null;
                    c01771.L$1 = null;
                    c01771.label = 4;
                    if (BuildersKt.withContext(main, assistActivity$playRemote$2$2, c01771) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                    return Unit.INSTANCE;
                case 4:
                    ResultKt.throwOnFailure($result);
                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            return ai.nous.aurora.compat.Compat.m7387boximpl(ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th)));
        }
    }
}
