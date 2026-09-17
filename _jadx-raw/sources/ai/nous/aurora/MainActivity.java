package ai.nous.aurora;

import ai.nous.aurora.domain.model.AuroraDisplayMode;
import ai.nous.aurora.domain.model.RazrLayoutClassifier;
import ai.nous.aurora.presentation.pairing.PairingScreenKt;
import ai.nous.aurora.presentation.shell.InnerShellKt;
import ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt;
import ai.nous.aurora.presentation.theme.AuroraSkin;
import ai.nous.aurora.presentation.theme.AuroraThemeKt;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.activity.compose.ComponentActivityKt;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.profileinstaller.ProfileVerifier;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0014J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0014J\b\u0010\u001e\u001a\u00020\u0013H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\t\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001f²\u0006\n\u0010 \u001a\u00020!X\u008a\u0084\u0002²\u0006\n\u0010\"\u001a\u00020\fX\u008a\u0084\u0002"}, d2 = {"Lai/nous/aurora/MainActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "appModule", "Lai/nous/aurora/AppModule;", "getAppModule", "()Lai/nous/aurora/AppModule;", "appModule$delegate", "Lkotlin/Lazy;", "notifPermission", "Landroidx/activity/result/ActivityResultLauncher;", "", "viewModel", "Lai/nous/aurora/MainViewModel;", "getViewModel", "()Lai/nous/aurora/MainViewModel;", "viewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onNewIntent", "intent", "Landroid/content/Intent;", "maybeLaunchAssist", "", "launchAssist", "onStop", "observeWindowState", "app_debug", "state", "Lai/nous/aurora/MainViewModel$UiState;", "skinWire"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MainActivity extends ComponentActivity {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: appModule$delegate, reason: from kotlin metadata */
    private final Lazy appModule = LazyKt.lazy(new Function0() { // from class: ai.nous.aurora.MainActivity$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MainActivity.appModule_delegate$lambda$0(this.f$0);
        }
    });
    private final ActivityResultLauncher<String> notifPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: ai.nous.aurora.MainActivity$$ExternalSyntheticLambda1
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            MainActivity.notifPermission$lambda$1(((Boolean) obj).booleanValue());
        }
    });

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public MainActivity() {
        final MainActivity $this$viewModels_u24default$iv = this;
        Function0 factoryProducer$iv = new Function0() { // from class: ai.nous.aurora.MainActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.viewModel_delegate$lambda$2(this.f$0);
            }
        };
        final Function0 extrasProducer$iv = null;
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: ai.nous.aurora.MainActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return $this$viewModels_u24default$iv.getViewModelStore();
            }
        }, factoryProducer$iv, new Function0<CreationExtras>() { // from class: ai.nous.aurora.MainActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function0 = extrasProducer$iv;
                return (function0 == null || (creationExtras = (CreationExtras) function0.invoke()) == null) ? $this$viewModels_u24default$iv.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AppModule appModule_delegate$lambda$0(MainActivity this$0) {
        Application application = this$0.getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type ai.nous.aurora.AuroraApplication");
        return ((AuroraApplication) application).getAppModule();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AppModule getAppModule() {
        return (AppModule) this.appModule.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifPermission$lambda$1(boolean it) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ViewModelProvider.Factory viewModel_delegate$lambda$2(MainActivity this$0) {
        return new MainViewModelFactory(this$0.getAppModule().getCredentialStore(), this$0.getAppModule().getGatewayClient(), this$0.getAppModule().getSessionRepository(), this$0.getAppModule().getSettingsRepository(), this$0.getAppModule().getNotifications());
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (maybeLaunchAssist(getIntent())) {
            return;
        }
        EdgeToEdge.enable(this, SystemBarStyle.INSTANCE.dark(0), SystemBarStyle.INSTANCE.dark(ViewCompat.MEASURED_STATE_MASK));
        observeWindowState();
        if (Build.VERSION.SDK_INT >= 33) {
            this.notifPermission.launch("android.permission.POST_NOTIFICATIONS");
        }
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-232562567, true, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.MainActivity.onCreate.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final MainViewModel.UiState invoke$lambda$0(State<MainViewModel.UiState> state) {
                Object thisObj$iv = state.getValue();
                return (MainViewModel.UiState) thisObj$iv;
            }

            public final void invoke(Composer $composer, int $changed) {
                Object value$iv;
                ComposerKt.sourceInformation($composer, "C61@2534L29,62@2637L29,65@2759L387,65@2738L408,75@3185L4488,75@3160L4513:MainActivity.kt#l0z6fd");
                if (($changed & 3) != 2 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-232562567, $changed, -1, "ai.nous.aurora.MainActivity.onCreate.<anonymous> (MainActivity.kt:61)");
                    }
                    State state$delegate = FlowExtKt.collectAsStateWithLifecycle(MainActivity.this.getViewModel().getUiState(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer, 0, 7);
                    State skinWire$delegate = FlowExtKt.collectAsStateWithLifecycle(MainActivity.this.getAppModule().getCredentialStore().getAppearanceSkinFlow(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer, 0, 7);
                    AuroraSkin skin = AuroraSkin.INSTANCE.fromWireValue(invoke$lambda$1(skinWire$delegate));
                    Unit unit = Unit.INSTANCE;
                    $composer.startReplaceGroup(-1213662882);
                    ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                    boolean invalid$iv = $composer.changedInstance(MainActivity.this);
                    MainActivity mainActivity = MainActivity.this;
                    Object it$iv = $composer.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = new MainActivity$onCreate$1$1$1(mainActivity, null);
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    $composer.endReplaceGroup();
                    EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv, $composer, 6);
                    AuroraThemeKt.AuroraTheme(skin, ComposableLambdaKt.rememberComposableLambda(841288630, true, new AnonymousClass2(MainActivity.this, skin, state$delegate), $composer, 54), $composer, 48, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }

            private static final String invoke$lambda$1(State<String> state) {
                Object thisObj$iv = state.getValue();
                return (String) thisObj$iv;
            }

            /* JADX INFO: renamed from: ai.nous.aurora.MainActivity$onCreate$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: MainActivity.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
                final /* synthetic */ AuroraSkin $skin;
                final /* synthetic */ State<MainViewModel.UiState> $state$delegate;
                final /* synthetic */ MainActivity this$0;

                AnonymousClass2(MainActivity mainActivity, AuroraSkin auroraSkin, State<MainViewModel.UiState> state) {
                    this.this$0 = mainActivity;
                    this.$skin = auroraSkin;
                    this.$state$delegate = state;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer, int $changed) {
                    Object value$iv$iv;
                    Object value$iv;
                    Object value$iv2;
                    Object value$iv3;
                    Object value$iv4;
                    Object value$iv5;
                    Object value$iv6;
                    Object value$iv7;
                    Object value$iv8;
                    Object value$iv9;
                    Object value$iv10;
                    Object value$iv11;
                    Object value$iv12;
                    Object value$iv13;
                    MainActivity$onCreate$1$2$2$1 value$iv14;
                    MainActivity$onCreate$1$2$3$1 value$iv15;
                    Object value$iv16;
                    ComposerKt.sourceInformation($composer, "C:MainActivity.kt#l0z6fd");
                    if (($changed & 3) == 2 && $composer.getSkipping()) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(841288630, $changed, -1, "ai.nous.aurora.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:76)");
                    }
                    if (C01801.invoke$lambda$0(this.$state$delegate).getMode() == MainViewModel.UiMode.Connect) {
                        $composer.startReplaceGroup(-569466629);
                        ComposerKt.sourceInformation($composer, "80@3406L33,81@3486L25,82@3553L26,83@3630L27,78@3302L445");
                        MainViewModel.UiState uiStateInvoke$lambda$0 = C01801.invoke$lambda$0(this.$state$delegate);
                        Object viewModel = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819481509);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv = $composer.changedInstance(viewModel);
                        Object it$iv = $composer.rememberedValue();
                        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv13 = new MainActivity$onCreate$1$2$1$1(viewModel);
                            $composer.updateRememberedValue(value$iv13);
                        } else {
                            value$iv13 = it$iv;
                        }
                        $composer.endReplaceGroup();
                        Function1 function1 = (Function1) ((KFunction) value$iv13);
                        Object viewModel2 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819478957);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv2 = $composer.changedInstance(viewModel2);
                        Object it$iv2 = $composer.rememberedValue();
                        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                            value$iv14 = new MainActivity$onCreate$1$2$2$1(viewModel2);
                            $composer.updateRememberedValue(value$iv14);
                        } else {
                            value$iv14 = it$iv2;
                        }
                        $composer.endReplaceGroup();
                        Function1 function2 = (Function1) ((KFunction) value$iv14);
                        Object viewModel3 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819476812);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv3 = $composer.changedInstance(viewModel3);
                        Object it$iv3 = $composer.rememberedValue();
                        if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv15 = new MainActivity$onCreate$1$2$3$1(viewModel3);
                            $composer.updateRememberedValue(value$iv15);
                        } else {
                            value$iv15 = it$iv3;
                        }
                        $composer.endReplaceGroup();
                        Function0 function0 = (Function0) ((KFunction) value$iv15);
                        Object viewModel4 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819474347);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv4 = $composer.changedInstance(viewModel4);
                        Object it$iv4 = $composer.rememberedValue();
                        if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                            value$iv16 = new MainActivity$onCreate$1$2$4$1(viewModel4);
                            $composer.updateRememberedValue(value$iv16);
                        } else {
                            value$iv16 = it$iv4;
                        }
                        $composer.endReplaceGroup();
                        PairingScreenKt.PairingScreen(uiStateInvoke$lambda$0, function1, function2, function0, (Function0) ((KFunction) value$iv16), SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0);
                        $composer.endReplaceGroup();
                    } else if (C01801.invoke$lambda$0(this.$state$delegate).getDisplayMode() == AuroraDisplayMode.RAZR_COVER) {
                        $composer.startReplaceGroup(-568901964);
                        ComposerKt.sourceInformation($composer, "90@3971L28,91@4041L254,88@3869L516");
                        MainViewModel.UiState uiStateInvoke$lambda$1 = C01801.invoke$lambda$0(this.$state$delegate);
                        Object viewModel5 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819463434);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv5 = $composer.changedInstance(viewModel5);
                        Object it$iv5 = $composer.rememberedValue();
                        if (invalid$iv5 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                            value$iv11 = new MainActivity$onCreate$1$2$5$1(viewModel5);
                            $composer.updateRememberedValue(value$iv11);
                        } else {
                            value$iv11 = it$iv5;
                        }
                        $composer.endReplaceGroup();
                        Function0 function3 = (Function0) ((KFunction) value$iv11);
                        $composer.startReplaceGroup(-1819460968);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv6 = $composer.changedInstance(this.this$0);
                        final MainActivity mainActivity = this.this$0;
                        Object it$iv6 = $composer.rememberedValue();
                        if (invalid$iv6 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                            value$iv12 = new Function0() { // from class: ai.nous.aurora.MainActivity$onCreate$1$2$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivity.C01801.AnonymousClass2.invoke$lambda$6$lambda$5(mainActivity);
                                }
                            };
                            $composer.updateRememberedValue(value$iv12);
                        } else {
                            value$iv12 = it$iv6;
                        }
                        $composer.endReplaceGroup();
                        RazrCoverShellKt.RazrCoverShell(uiStateInvoke$lambda$1, function3, (Function0) value$iv12, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), $composer, 3072, 0);
                        $composer.endReplaceGroup();
                    } else {
                        $composer.startReplaceGroup(-568231837);
                        ComposerKt.sourceInformation($composer, "100@4499L24,109@4941L24,110@5005L24,111@5068L15,112@5128L21,113@5196L29,114@5270L24,115@5340L22,116@5407L28,117@5477L26,118@5548L21,119@5616L26,120@5683L25,121@5757L28,122@5834L25,123@5911L28,124@5994L31,125@6067L24,126@6137L22,127@6207L32,128@6286L14,129@6347L23,131@6499L27,132@6574L26,133@6642L18,134@6709L22,135@6782L27,136@6862L32,137@6945L30,138@7024L28,141@7282L28,142@7360L26,143@7430L23,144@7503L26,130@6420L31,139@7102L38,140@7191L39,104@4687L216,101@4548L3071");
                        ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart($composer, -954367824, "CC(remember):Effects.kt#9igjgp");
                        Object it$iv$iv = $composer.rememberedValue();
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
                            $composer.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        final CoroutineScope scope = wrapper$iv.getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        MainViewModel.UiState uiStateInvoke$lambda$2 = C01801.invoke$lambda$0(this.$state$delegate);
                        Object viewModel6 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819432398);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv7 = $composer.changedInstance(viewModel6);
                        Object it$iv7 = $composer.rememberedValue();
                        if (invalid$iv7 || it$iv7 == Composer.INSTANCE.getEmpty()) {
                            value$iv = new MainActivity$onCreate$1$2$7$1(viewModel6);
                            $composer.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv7;
                        }
                        KFunction kFunction = (KFunction) value$iv;
                        $composer.endReplaceGroup();
                        Object viewModel7 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819430350);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv8 = $composer.changedInstance(viewModel7);
                        Object it$iv8 = $composer.rememberedValue();
                        if (invalid$iv8 || it$iv8 == Composer.INSTANCE.getEmpty()) {
                            value$iv2 = new MainActivity$onCreate$1$2$8$1(viewModel7);
                            $composer.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = it$iv8;
                        }
                        KFunction kFunction2 = (KFunction) value$iv2;
                        $composer.endReplaceGroup();
                        Object viewModel8 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819428343);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv9 = $composer.changedInstance(viewModel8);
                        Object it$iv9 = $composer.rememberedValue();
                        if (invalid$iv9 || it$iv9 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = new MainActivity$onCreate$1$2$9$1(viewModel8);
                            $composer.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv9;
                        }
                        KFunction kFunction3 = (KFunction) value$iv3;
                        $composer.endReplaceGroup();
                        Object viewModel9 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819426417);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv10 = $composer.changedInstance(viewModel9);
                        Object it$iv10 = $composer.rememberedValue();
                        if (invalid$iv10 || it$iv10 == Composer.INSTANCE.getEmpty()) {
                            value$iv4 = new MainActivity$onCreate$1$2$10$1(viewModel9);
                            $composer.updateRememberedValue(value$iv4);
                        } else {
                            value$iv4 = it$iv10;
                        }
                        KFunction kFunction4 = (KFunction) value$iv4;
                        $composer.endReplaceGroup();
                        Object viewModel10 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819424233);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv11 = $composer.changedInstance(viewModel10);
                        Object it$iv11 = $composer.rememberedValue();
                        if (invalid$iv11 || it$iv11 == Composer.INSTANCE.getEmpty()) {
                            value$iv5 = new MainActivity$onCreate$1$2$11$1(viewModel10);
                            $composer.updateRememberedValue(value$iv5);
                        } else {
                            value$iv5 = it$iv11;
                        }
                        KFunction kFunction5 = (KFunction) value$iv5;
                        $composer.endReplaceGroup();
                        Object viewModel11 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819421870);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv12 = $composer.changedInstance(viewModel11);
                        Object it$iv12 = $composer.rememberedValue();
                        if (invalid$iv12 || it$iv12 == Composer.INSTANCE.getEmpty()) {
                            value$iv6 = new MainActivity$onCreate$1$2$12$1(viewModel11);
                            $composer.updateRememberedValue(value$iv6);
                        } else {
                            value$iv6 = it$iv12;
                        }
                        KFunction kFunction6 = (KFunction) value$iv6;
                        $composer.endReplaceGroup();
                        Object viewModel12 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819419632);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv13 = $composer.changedInstance(viewModel12);
                        Object it$iv13 = $composer.rememberedValue();
                        if (invalid$iv13 || it$iv13 == Composer.INSTANCE.getEmpty()) {
                            value$iv7 = new MainActivity$onCreate$1$2$13$1(viewModel12);
                            $composer.updateRememberedValue(value$iv7);
                        } else {
                            value$iv7 = it$iv13;
                        }
                        KFunction kFunction7 = (KFunction) value$iv7;
                        $composer.endReplaceGroup();
                        Object viewModel13 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819417482);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv14 = $composer.changedInstance(viewModel13);
                        Object it$iv14 = $composer.rememberedValue();
                        if (invalid$iv14 || it$iv14 == Composer.INSTANCE.getEmpty()) {
                            value$iv8 = new MainActivity$onCreate$1$2$14$1(viewModel13);
                            $composer.updateRememberedValue(value$iv8);
                        } else {
                            value$iv8 = it$iv14;
                        }
                        KFunction kFunction8 = (KFunction) value$iv8;
                        $composer.endReplaceGroup();
                        Object viewModel14 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819415244);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv15 = $composer.changedInstance(viewModel14);
                        MainActivity$onCreate$1$2$15$1 value$iv17 = $composer.rememberedValue();
                        if (invalid$iv15 || value$iv17 == Composer.INSTANCE.getEmpty()) {
                            value$iv17 = new MainActivity$onCreate$1$2$15$1(viewModel14);
                            $composer.updateRememberedValue(value$iv17);
                        }
                        KFunction kFunction9 = (KFunction) value$iv17;
                        $composer.endReplaceGroup();
                        Object viewModel15 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819412977);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv16 = $composer.changedInstance(viewModel15);
                        Object it$iv15 = $composer.rememberedValue();
                        if (invalid$iv16 || it$iv15 == Composer.INSTANCE.getEmpty()) {
                            value$iv9 = new MainActivity$onCreate$1$2$16$1(viewModel15);
                            $composer.updateRememberedValue(value$iv9);
                        } else {
                            value$iv9 = it$iv15;
                        }
                        KFunction kFunction10 = (KFunction) value$iv9;
                        $composer.endReplaceGroup();
                        Object viewModel16 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819410796);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv17 = $composer.changedInstance(viewModel16);
                        MainActivity$onCreate$1$2$17$1 value$iv18 = $composer.rememberedValue();
                        if (invalid$iv17 || value$iv18 == Composer.INSTANCE.getEmpty()) {
                            value$iv18 = new MainActivity$onCreate$1$2$17$1(viewModel16);
                            $composer.updateRememberedValue(value$iv18);
                        }
                        KFunction kFunction11 = (KFunction) value$iv18;
                        $composer.endReplaceGroup();
                        Object viewModel17 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819408653);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv18 = $composer.changedInstance(viewModel17);
                        MainActivity$onCreate$1$2$18$1 value$iv19 = $composer.rememberedValue();
                        if (invalid$iv18 || value$iv19 == Composer.INSTANCE.getEmpty()) {
                            value$iv19 = new MainActivity$onCreate$1$2$18$1(viewModel17);
                            $composer.updateRememberedValue(value$iv19);
                        }
                        KFunction kFunction12 = (KFunction) value$iv19;
                        $composer.endReplaceGroup();
                        Object viewModel18 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819406282);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv19 = $composer.changedInstance(viewModel18);
                        MainActivity$onCreate$1$2$19$1 value$iv20 = $composer.rememberedValue();
                        if (invalid$iv19 || value$iv20 == Composer.INSTANCE.getEmpty()) {
                            value$iv20 = new MainActivity$onCreate$1$2$19$1(viewModel18);
                            $composer.updateRememberedValue(value$iv20);
                        }
                        KFunction kFunction13 = (KFunction) value$iv20;
                        $composer.endReplaceGroup();
                        Object viewModel19 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819403821);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv20 = $composer.changedInstance(viewModel19);
                        MainActivity$onCreate$1$2$20$1 value$iv21 = $composer.rememberedValue();
                        if (invalid$iv20 || value$iv21 == Composer.INSTANCE.getEmpty()) {
                            value$iv21 = new MainActivity$onCreate$1$2$20$1(viewModel19);
                            $composer.updateRememberedValue(value$iv21);
                        }
                        KFunction kFunction14 = (KFunction) value$iv21;
                        $composer.endReplaceGroup();
                        Object viewModel20 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819401354);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv21 = $composer.changedInstance(viewModel20);
                        MainActivity$onCreate$1$2$21$1 value$iv22 = $composer.rememberedValue();
                        if (invalid$iv21 || value$iv22 == Composer.INSTANCE.getEmpty()) {
                            value$iv22 = new MainActivity$onCreate$1$2$21$1(viewModel20);
                            $composer.updateRememberedValue(value$iv22);
                        }
                        KFunction kFunction15 = (KFunction) value$iv22;
                        $composer.endReplaceGroup();
                        Object viewModel21 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819398695);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv22 = $composer.changedInstance(viewModel21);
                        MainActivity$onCreate$1$2$22$1 value$iv23 = $composer.rememberedValue();
                        if (invalid$iv22 || value$iv23 == Composer.INSTANCE.getEmpty()) {
                            value$iv23 = new MainActivity$onCreate$1$2$22$1(viewModel21);
                            $composer.updateRememberedValue(value$iv23);
                        }
                        KFunction kFunction16 = (KFunction) value$iv23;
                        $composer.endReplaceGroup();
                        Object viewModel22 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819396366);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv23 = $composer.changedInstance(viewModel22);
                        MainActivity$onCreate$1$2$23$1 value$iv24 = $composer.rememberedValue();
                        if (invalid$iv23 || value$iv24 == Composer.INSTANCE.getEmpty()) {
                            value$iv24 = new MainActivity$onCreate$1$2$23$1(viewModel22);
                            $composer.updateRememberedValue(value$iv24);
                        }
                        KFunction kFunction17 = (KFunction) value$iv24;
                        $composer.endReplaceGroup();
                        Object viewModel23 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819394128);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv24 = $composer.changedInstance(viewModel23);
                        MainActivity$onCreate$1$2$24$1 value$iv25 = $composer.rememberedValue();
                        if (invalid$iv24 || value$iv25 == Composer.INSTANCE.getEmpty()) {
                            value$iv25 = new MainActivity$onCreate$1$2$24$1(viewModel23);
                            $composer.updateRememberedValue(value$iv25);
                        }
                        KFunction kFunction18 = (KFunction) value$iv25;
                        $composer.endReplaceGroup();
                        Object viewModel24 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819391878);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv25 = $composer.changedInstance(viewModel24);
                        MainActivity$onCreate$1$2$25$1 value$iv26 = $composer.rememberedValue();
                        if (invalid$iv25 || value$iv26 == Composer.INSTANCE.getEmpty()) {
                            value$iv26 = new MainActivity$onCreate$1$2$25$1(viewModel24);
                            $composer.updateRememberedValue(value$iv26);
                        }
                        KFunction kFunction19 = (KFunction) value$iv26;
                        $composer.endReplaceGroup();
                        Object obj = this.this$0;
                        $composer.startReplaceGroup(-1819389368);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv26 = $composer.changedInstance(obj);
                        MainActivity$onCreate$1$2$26$1 value$iv27 = $composer.rememberedValue();
                        if (invalid$iv26 || value$iv27 == Composer.INSTANCE.getEmpty()) {
                            value$iv27 = new MainActivity$onCreate$1$2$26$1(obj);
                            $composer.updateRememberedValue(value$iv27);
                        }
                        KFunction kFunction20 = (KFunction) value$iv27;
                        $composer.endReplaceGroup();
                        Object viewModel25 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819387407);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv27 = $composer.changedInstance(viewModel25);
                        MainActivity$onCreate$1$2$27$1 value$iv28 = $composer.rememberedValue();
                        if (invalid$iv27 || value$iv28 == Composer.INSTANCE.getEmpty()) {
                            value$iv28 = new MainActivity$onCreate$1$2$27$1(viewModel25);
                            $composer.updateRememberedValue(value$iv28);
                        }
                        KFunction kFunction21 = (KFunction) value$iv28;
                        $composer.endReplaceGroup();
                        Object viewModel26 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819382539);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv28 = $composer.changedInstance(viewModel26);
                        MainActivity$onCreate$1$2$28$1 value$iv29 = $composer.rememberedValue();
                        if (invalid$iv28 || value$iv29 == Composer.INSTANCE.getEmpty()) {
                            value$iv29 = new MainActivity$onCreate$1$2$28$1(viewModel26);
                            $composer.updateRememberedValue(value$iv29);
                        }
                        KFunction kFunction22 = (KFunction) value$iv29;
                        $composer.endReplaceGroup();
                        Object viewModel27 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819380140);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv29 = $composer.changedInstance(viewModel27);
                        MainActivity$onCreate$1$2$29$1 value$iv30 = $composer.rememberedValue();
                        if (invalid$iv29 || value$iv30 == Composer.INSTANCE.getEmpty()) {
                            value$iv30 = new MainActivity$onCreate$1$2$29$1(viewModel27);
                            $composer.updateRememberedValue(value$iv30);
                        }
                        KFunction kFunction23 = (KFunction) value$iv30;
                        $composer.endReplaceGroup();
                        Object viewModel28 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819377972);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv30 = $composer.changedInstance(viewModel28);
                        MainActivity$onCreate$1$2$30$1 value$iv31 = $composer.rememberedValue();
                        if (invalid$iv30 || value$iv31 == Composer.INSTANCE.getEmpty()) {
                            value$iv31 = new MainActivity$onCreate$1$2$30$1(viewModel28);
                            $composer.updateRememberedValue(value$iv31);
                        }
                        KFunction kFunction24 = (KFunction) value$iv31;
                        $composer.endReplaceGroup();
                        Object viewModel29 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819375824);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv31 = $composer.changedInstance(viewModel29);
                        MainActivity$onCreate$1$2$31$1 value$iv32 = $composer.rememberedValue();
                        if (invalid$iv31 || value$iv32 == Composer.INSTANCE.getEmpty()) {
                            value$iv32 = new MainActivity$onCreate$1$2$31$1(viewModel29);
                            $composer.updateRememberedValue(value$iv32);
                        }
                        KFunction kFunction25 = (KFunction) value$iv32;
                        $composer.endReplaceGroup();
                        Object viewModel30 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819373483);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv32 = $composer.changedInstance(viewModel30);
                        MainActivity$onCreate$1$2$32$1 value$iv33 = $composer.rememberedValue();
                        if (invalid$iv32 || value$iv33 == Composer.INSTANCE.getEmpty()) {
                            value$iv33 = new MainActivity$onCreate$1$2$32$1(viewModel30);
                            $composer.updateRememberedValue(value$iv33);
                        }
                        KFunction kFunction26 = (KFunction) value$iv33;
                        $composer.endReplaceGroup();
                        Object viewModel31 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819370918);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv33 = $composer.changedInstance(viewModel31);
                        MainActivity$onCreate$1$2$33$1 value$iv34 = $composer.rememberedValue();
                        if (invalid$iv33 || value$iv34 == Composer.INSTANCE.getEmpty()) {
                            value$iv34 = new MainActivity$onCreate$1$2$33$1(viewModel31);
                            $composer.updateRememberedValue(value$iv34);
                        }
                        KFunction kFunction27 = (KFunction) value$iv34;
                        $composer.endReplaceGroup();
                        Object viewModel32 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819368264);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv34 = $composer.changedInstance(viewModel32);
                        MainActivity$onCreate$1$2$34$1 value$iv35 = $composer.rememberedValue();
                        if (invalid$iv34 || value$iv35 == Composer.INSTANCE.getEmpty()) {
                            value$iv35 = new MainActivity$onCreate$1$2$34$1(viewModel32);
                            $composer.updateRememberedValue(value$iv35);
                        }
                        KFunction kFunction28 = (KFunction) value$iv35;
                        $composer.endReplaceGroup();
                        Object viewModel33 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819365738);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv35 = $composer.changedInstance(viewModel33);
                        MainActivity$onCreate$1$2$35$1 value$iv36 = $composer.rememberedValue();
                        if (invalid$iv35 || value$iv36 == Composer.INSTANCE.getEmpty()) {
                            value$iv36 = new MainActivity$onCreate$1$2$35$1(viewModel33);
                            $composer.updateRememberedValue(value$iv36);
                        }
                        KFunction kFunction29 = (KFunction) value$iv36;
                        $composer.endReplaceGroup();
                        Object viewModel34 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819357482);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv36 = $composer.changedInstance(viewModel34);
                        MainActivity$onCreate$1$2$36$1 value$iv37 = $composer.rememberedValue();
                        if (invalid$iv36 || value$iv37 == Composer.INSTANCE.getEmpty()) {
                            value$iv37 = new MainActivity$onCreate$1$2$36$1(viewModel34);
                            $composer.updateRememberedValue(value$iv37);
                        }
                        KFunction kFunction30 = (KFunction) value$iv37;
                        $composer.endReplaceGroup();
                        Object viewModel35 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819354988);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv37 = $composer.changedInstance(viewModel35);
                        MainActivity$onCreate$1$2$37$1 value$iv38 = $composer.rememberedValue();
                        if (invalid$iv37 || value$iv38 == Composer.INSTANCE.getEmpty()) {
                            value$iv38 = new MainActivity$onCreate$1$2$37$1(viewModel35);
                            $composer.updateRememberedValue(value$iv38);
                        }
                        KFunction kFunction31 = (KFunction) value$iv38;
                        $composer.endReplaceGroup();
                        Object viewModel36 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819352751);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv38 = $composer.changedInstance(viewModel36);
                        MainActivity$onCreate$1$2$38$1 value$iv39 = $composer.rememberedValue();
                        if (invalid$iv38 || value$iv39 == Composer.INSTANCE.getEmpty()) {
                            value$iv39 = new MainActivity$onCreate$1$2$38$1(viewModel36);
                            $composer.updateRememberedValue(value$iv39);
                        }
                        KFunction kFunction32 = (KFunction) value$iv39;
                        $composer.endReplaceGroup();
                        Object viewModel37 = this.this$0.getViewModel();
                        $composer.startReplaceGroup(-1819350412);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv39 = $composer.changedInstance(viewModel37);
                        MainActivity$onCreate$1$2$39$1 value$iv40 = $composer.rememberedValue();
                        if (invalid$iv39 || value$iv40 == Composer.INSTANCE.getEmpty()) {
                            value$iv40 = new MainActivity$onCreate$1$2$39$1(viewModel37);
                            $composer.updateRememberedValue(value$iv40);
                        }
                        $composer.endReplaceGroup();
                        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Function1 function4 = (Function1) kFunction;
                        Function1 function5 = (Function1) kFunction2;
                        Function0 function6 = (Function0) kFunction3;
                        Function0 function7 = (Function0) kFunction4;
                        Function1 function8 = (Function1) kFunction5;
                        Function0 function9 = (Function0) kFunction6;
                        Function1 function10 = (Function1) kFunction7;
                        Function0 function11 = (Function0) kFunction8;
                        Function0 function12 = (Function0) kFunction9;
                        Function0 function13 = (Function0) ((KFunction) value$iv40);
                        Function0 function14 = (Function0) kFunction10;
                        Function1 function15 = (Function1) kFunction11;
                        Function1 function16 = (Function1) kFunction12;
                        Function1 function17 = (Function1) kFunction13;
                        Function1 function18 = (Function1) kFunction14;
                        Function0 function19 = (Function0) kFunction15;
                        Function1 function20 = (Function1) kFunction16;
                        Function0 function21 = (Function0) kFunction17;
                        Function0 function22 = (Function0) kFunction18;
                        Function0 function23 = (Function0) kFunction19;
                        Function0 function24 = (Function0) kFunction20;
                        Function0 function25 = (Function0) kFunction21;
                        $composer.startReplaceGroup(-1819385063);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv40 = $composer.changedInstance(this.this$0);
                        final MainActivity mainActivity2 = this.this$0;
                        Object it$iv16 = $composer.rememberedValue();
                        if (invalid$iv40 || it$iv16 == Composer.INSTANCE.getEmpty()) {
                            value$iv10 = new Function0() { // from class: ai.nous.aurora.MainActivity$onCreate$1$2$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivity.C01801.AnonymousClass2.invoke$lambda$41$lambda$40(mainActivity2);
                                }
                            };
                            $composer.updateRememberedValue(value$iv10);
                        } else {
                            value$iv10 = it$iv16;
                        }
                        Function0 function26 = (Function0) value$iv10;
                        $composer.endReplaceGroup();
                        Function2 function27 = (Function2) kFunction22;
                        Function3 function28 = (Function3) kFunction23;
                        Function1 function29 = (Function1) kFunction24;
                        Function1 function30 = (Function1) kFunction25;
                        Function0 function31 = (Function0) kFunction26;
                        Function1 function32 = (Function1) kFunction27;
                        Function1 function33 = (Function1) kFunction28;
                        Function1 function34 = (Function1) kFunction29;
                        $composer.startReplaceGroup(-1819363232);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv41 = $composer.changedInstance(this.this$0);
                        final MainActivity mainActivity3 = this.this$0;
                        Object value$iv41 = $composer.rememberedValue();
                        if (invalid$iv41 || value$iv41 == Composer.INSTANCE.getEmpty()) {
                            value$iv41 = new Function0() { // from class: ai.nous.aurora.MainActivity$onCreate$1$2$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivity.C01801.AnonymousClass2.invoke$lambda$43$lambda$42(mainActivity3);
                                }
                            };
                            $composer.updateRememberedValue(value$iv41);
                        }
                        Function0 function35 = (Function0) value$iv41;
                        $composer.endReplaceGroup();
                        $composer.startReplaceGroup(-1819360383);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv42 = $composer.changedInstance(this.this$0);
                        final MainActivity mainActivity4 = this.this$0;
                        Object value$iv42 = $composer.rememberedValue();
                        if (invalid$iv42 || value$iv42 == Composer.INSTANCE.getEmpty()) {
                            value$iv42 = new Function0() { // from class: ai.nous.aurora.MainActivity$onCreate$1$2$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivity.C01801.AnonymousClass2.invoke$lambda$45$lambda$44(mainActivity4);
                                }
                            };
                            $composer.updateRememberedValue(value$iv42);
                        }
                        Function0 function36 = (Function0) value$iv42;
                        $composer.endReplaceGroup();
                        Function1 function37 = (Function1) kFunction30;
                        Function1 function38 = (Function1) kFunction31;
                        Function1 function39 = (Function1) kFunction32;
                        AuroraSkin auroraSkin = this.$skin;
                        $composer.startReplaceGroup(-1819440334);
                        ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
                        boolean invalid$iv43 = $composer.changedInstance(scope) | $composer.changedInstance(this.this$0);
                        final MainActivity mainActivity5 = this.this$0;
                        Object value$iv43 = $composer.rememberedValue();
                        if (invalid$iv43 || value$iv43 == Composer.INSTANCE.getEmpty()) {
                            value$iv43 = new Function1() { // from class: ai.nous.aurora.MainActivity$onCreate$1$2$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return MainActivity.C01801.AnonymousClass2.invoke$lambda$47$lambda$46(scope, mainActivity5, (AuroraSkin) obj2);
                                }
                            };
                            $composer.updateRememberedValue(value$iv43);
                        }
                        $composer.endReplaceGroup();
                        InnerShellKt.InnerShell(uiStateInvoke$lambda$2, function4, function5, function6, function7, function8, function9, function10, function11, function12, function13, function14, function15, function16, function17, function18, function19, function20, function21, function22, function23, function24, function25, function26, function27, function28, function29, function30, function31, function32, function33, function34, function35, function36, function37, function38, function39, auroraSkin, (Function1) value$iv43, modifierFillMaxSize$default, $composer, 0, 0, 0, 805306368, 0, 0);
                        $composer.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$6$lambda$5(MainActivity this$0) {
                    this$0.getViewModel().requestInnerFromCover();
                    this$0.getViewModel().createSession();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$47$lambda$46(CoroutineScope $scope, MainActivity this$0, AuroraSkin next) {
                    Intrinsics.checkNotNullParameter(next, "next");
                    BuildersKt__Builders_commonKt.launch$default($scope, null, null, new MainActivity$onCreate$1$2$43$1$1(this$0, next, null), 3, null);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$41$lambda$40(MainActivity this$0) {
                    MainViewModel.refreshSettings$default(this$0.getViewModel(), false, false, 3, null);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$43$lambda$42(MainActivity this$0) {
                    this$0.getViewModel().setModelPickerOpen(true);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$45$lambda$44(MainActivity this$0) {
                    this$0.getViewModel().setModelPickerOpen(false);
                    return Unit.INSTANCE;
                }
            }
        }), 1, null);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        getViewModel().setAppInForeground(true);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        maybeLaunchAssist(intent);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0050  */
    private final boolean maybeLaunchAssist(Intent intent) {
        boolean assist;
        if (intent == null) {
            return false;
        }
        String action = intent.getAction();
        Uri data = intent.getData();
        if (Intrinsics.areEqual(action, "android.intent.action.ASSIST") || Intrinsics.areEqual(action, "android.intent.action.VOICE_COMMAND") || Intrinsics.areEqual(action, "android.intent.action.WEB_SEARCH")) {
            assist = true;
        } else {
            if (Intrinsics.areEqual(data != null ? data.getScheme() : null, "aurora") && (Intrinsics.areEqual(data.getHost(), "assistant") || Intrinsics.areEqual(data.getHost(), "assist"))) {
                assist = true;
            } else {
                assist = false;
            }
        }
        if (!assist) {
            return false;
        }
        Intent $this$maybeLaunchAssist_u24lambda_u243 = new Intent(this, (Class<?>) AssistActivity.class);
        $this$maybeLaunchAssist_u24lambda_u243.addFlags(335544320);
        $this$maybeLaunchAssist_u24lambda_u243.putExtras(intent);
        $this$maybeLaunchAssist_u24lambda_u243.setAction(action);
        $this$maybeLaunchAssist_u24lambda_u243.setData(data);
        startActivity($this$maybeLaunchAssist_u24lambda_u243);
        finish();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchAssist() {
        Intent $this$launchAssist_u24lambda_u244 = new Intent(this, (Class<?>) AssistActivity.class);
        $this$launchAssist_u24lambda_u244.addFlags(335544320);
        startActivity($this$launchAssist_u24lambda_u244);
    }

    @Override // android.app.Activity
    protected void onStop() {
        getViewModel().setAppInForeground(false);
        super.onStop();
    }

    /* JADX INFO: renamed from: ai.nous.aurora.MainActivity$observeWindowState$1, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.MainActivity$observeWindowState$1", f = "MainActivity.kt", i = {}, l = {ComposerKt.compositionLocalMapKey}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainActivity.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: ai.nous.aurora.MainActivity$observeWindowState$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: MainActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "ai.nous.aurora.MainActivity$observeWindowState$1$1", f = "MainActivity.kt", i = {}, l = {205}, m = "invokeSuspend", n = {}, s = {})
        static final class C00021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ MainActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00021(MainActivity mainActivity, Continuation<? super C00021> continuation) {
                super(2, continuation);
                this.this$0 = mainActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00021(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        Flow<WindowLayoutInfo> flowWindowLayoutInfo = WindowInfoTracker.INSTANCE.getOrCreate(this.this$0).windowLayoutInfo((Activity) this.this$0);
                        final MainActivity mainActivity = this.this$0;
                        this.label = 1;
                        if (flowWindowLayoutInfo.collect(new FlowCollector() { // from class: ai.nous.aurora.MainActivity.observeWindowState.1.1.1
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                                return emit((WindowLayoutInfo) value, (Continuation<? super Unit>) $completion);
                            }

                            public final Object emit(WindowLayoutInfo info, Continuation<? super Unit> continuation) {
                                RazrLayoutClassifier razrLayoutClassifier = RazrLayoutClassifier.INSTANCE;
                                Configuration configuration = mainActivity.getResources().getConfiguration();
                                Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
                                AuroraDisplayMode mode = razrLayoutClassifier.classify(configuration, info);
                                mainActivity.getViewModel().onDisplayMode(mode);
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (RepeatOnLifecycleKt.repeatOnLifecycle(MainActivity.this, Lifecycle.State.STARTED, new C00021(MainActivity.this, null), this) == coroutine_suspended) {
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

    private final void observeWindowState() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(null), 3, null);
    }
}
