package ai.nous.aurora.presentation.voice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.LocalActivityResultRegistryOwner;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VoiceDictation.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001aQ\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u00042\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u0004H\u0007¢\u0006\u0002\u0010\t¨\u0006\n²\u0006\f\u0010\u000b\u001a\u0004\u0018\u00010\fX\u008a\u008e\u0002²\u0006\n\u0010\r\u001a\u00020\u0007X\u008a\u008e\u0002"}, d2 = {"rememberDictationLauncher", "Lkotlin/Function0;", "", "onResult", "Lkotlin/Function1;", "", "onListening", "", "onError", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Lkotlin/jvm/functions/Function0;", "app_debug", "recognizer", "Landroid/speech/SpeechRecognizer;", "active"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class VoiceDictationKt {
    /* JADX WARN: Code duplicated, block: B:107:0x02a7 A[PHI: r12
  0x02a7: PHI (r12v4 kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit>) = 
  (r12v2 kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit>)
  (r12v5 kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit>)
 binds: [B:106:0x02a5, B:102:0x029c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:117:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:118:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:125:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:128:0x0318  */
    public static final Function0<Unit> rememberDictationLauncher(final Function1<? super String, Unit> onResult, final Function1<? super Boolean, Unit> onListening, Function1<? super String, Unit> function1, Composer $composer, int $changed, int i) {
        final Function1<? super String, Unit> function2;
        Object value$iv;
        boolean z;
        Object value$iv2;
        Object value$iv3;
        Function1<? super String, Unit> function3;
        boolean z2;
        boolean z3;
        boolean invalid$iv;
        Object value$iv4;
        Object value$iv5;
        Object value$iv6;
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(onListening, "onListening");
        $composer.startReplaceGroup(-1241758761);
        ComposerKt.sourceInformation($composer, "C(rememberDictationLauncher)P(2,1)30@1157L2,32@1208L7,33@1273L7,34@1303L52,35@1374L34,37@1437L269,37@1414L292,129@4856L100,127@4762L194,133@4969L380:VoiceDictation.kt#k9vj1");
        if ((i & 4) != 0) {
            $composer.startReplaceGroup(-294757495);
            ComposerKt.sourceInformation($composer, "CC(remember):VoiceDictation.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv6 = new Function1() { // from class: ai.nous.aurora.presentation.voice.VoiceDictationKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return VoiceDictationKt.rememberDictationLauncher$lambda$1$lambda$0((String) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv;
            }
            $composer.endReplaceGroup();
            function2 = (Function1) value$iv6;
        } else {
            function2 = function1;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1241758761, $changed, -1, "ai.nous.aurora.presentation.voice.rememberDictationLauncher (VoiceDictation.kt:31)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Context context = (Context) objConsume;
        ActivityResultRegistryOwner registryOwner = LocalActivityResultRegistryOwner.INSTANCE.getCurrent($composer, LocalActivityResultRegistryOwner.$stable);
        $composer.startReplaceGroup(-294752773);
        ComposerKt.sourceInformation($composer, "CC(remember):VoiceDictation.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.Companion.getEmpty()) {
            value$iv = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4(null, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv2;
        }
        final MutableState recognizer$delegate = (MutableState) value$iv;
        $composer.endReplaceGroup();
        $composer.startReplaceGroup(-294750519);
        ComposerKt.sourceInformation($composer, "CC(remember):VoiceDictation.kt#9igjgp");
        Object it$iv3 = $composer.rememberedValue();
        if (it$iv3 == Composer.Companion.getEmpty()) {
            z = false;
            value$iv2 = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4(false, null, 2, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            z = false;
            value$iv2 = it$iv3;
        }
        final MutableState active$delegate = (MutableState) value$iv2;
        $composer.endReplaceGroup();
        Unit unit = Unit.INSTANCE;
        $composer.startReplaceGroup(-294748268);
        ComposerKt.sourceInformation($composer, "CC(remember):VoiceDictation.kt#9igjgp");
        boolean invalid$iv2 = (((($changed & 112) ^ 48) <= 32 || !$composer.changed(onListening)) && ($changed & 48) != 32) ? z : true;
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv4 == Composer.Companion.getEmpty()) {
            value$iv3 = new Function1() { // from class: ai.nous.aurora.presentation.voice.VoiceDictationKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VoiceDictationKt.rememberDictationLauncher$lambda$10$lambda$9(onListening, recognizer$delegate, active$delegate, (DisposableEffectScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv3);
        } else {
            value$iv3 = it$iv4;
        }
        $composer.endReplaceGroup();
        EffectsKt.DisposableEffect(unit, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv3, $composer, 6);
        $composer.startReplaceGroup(-294646942);
        ComposerKt.sourceInformation($composer, "122@4652L73");
        if (registryOwner == null) {
            $composer.startReplaceGroup(-294645584);
            ComposerKt.sourceInformation($composer, "CC(remember):VoiceDictation.kt#9igjgp");
            boolean invalid$iv3 = (((($changed & 896) ^ 384) <= 256 || !$composer.changed(function2)) && ($changed & 384) != 256) ? z : true;
            Object it$iv5 = $composer.rememberedValue();
            if (invalid$iv3 || it$iv5 == Composer.Companion.getEmpty()) {
                value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.voice.VoiceDictationKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return VoiceDictationKt.rememberDictationLauncher$lambda$14$lambda$13(function2);
                    }
                };
                $composer.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = it$iv5;
            }
            Function0<Unit> function0 = (Function0) value$iv5;
            $composer.endReplaceGroup();
            $composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return function0;
        }
        $composer.endReplaceGroup();
        ActivityResultContracts.RequestPermission requestPermission = new ActivityResultContracts.RequestPermission();
        $composer.startReplaceGroup(-294639029);
        ComposerKt.sourceInformation($composer, "CC(remember):VoiceDictation.kt#9igjgp");
        boolean invalid$iv4 = $composer.changedInstance(context) | ((((($changed & 896) ^ 384) <= 256 || !$composer.changed(function2)) && ($changed & 384) != 256) ? z : true) | ((((($changed & 112) ^ 48) <= 32 || !$composer.changed(onListening)) && ($changed & 48) != 32) ? z : true) | ((((($changed & 14) ^ 6) <= 4 || !$composer.changed(onResult)) && ($changed & 6) != 4) ? z : true);
        Object value$iv7 = $composer.rememberedValue();
        if (invalid$iv4 || value$iv7 == Composer.Companion.getEmpty()) {
            final Function1<? super String, Unit> function4 = function2;
            value$iv7 = new Function1() { // from class: ai.nous.aurora.presentation.voice.VoiceDictationKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VoiceDictationKt.rememberDictationLauncher$lambda$16$lambda$15(function4, context, onListening, recognizer$delegate, active$delegate, onResult, ((Boolean) obj).booleanValue());
                }
            };
            $composer.updateRememberedValue(value$iv7);
        }
        $composer.endReplaceGroup();
        final ManagedActivityResultLauncher permissionLauncher = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission, (Function1) value$iv7, $composer, 0);
        $composer.startReplaceGroup(-294635133);
        ComposerKt.sourceInformation($composer, "CC(remember):VoiceDictation.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance(context) | ((((($changed & 112) ^ 48) <= 32 || !$composer.changed(onListening)) && ($changed & 48) != 32) ? z : true);
        if ((($changed & 896) ^ 384) > 256) {
            function3 = function2;
            if ($composer.changed(function3)) {
                z2 = true;
            }
            boolean z4 = zChangedInstance | z2;
            if ((($changed & 14) ^ 6) <= 4 && $composer.changed(onResult)) {
                z3 = true;
            } else if (($changed & 6) == 4) {
                z3 = true;
            } else {
                z3 = z;
            }
            invalid$iv = z4 | z3 | $composer.changedInstance(permissionLauncher);
            value$iv4 = $composer.rememberedValue();
            if (invalid$iv || value$iv4 == Composer.Companion.getEmpty()) {
                final Function1<? super String, Unit> function5 = function3;
                value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.voice.VoiceDictationKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return VoiceDictationKt.rememberDictationLauncher$lambda$18$lambda$17(context, permissionLauncher, active$delegate, onListening, recognizer$delegate, function5, onResult);
                    }
                };
                $composer.updateRememberedValue(value$iv4);
            }
            Function0<Unit> function6 = (Function0) value$iv4;
            $composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return function6;
        }
        function3 = function2;
        if (($changed & 384) == 256) {
            z2 = true;
        } else {
            z2 = z;
        }
        boolean z5 = zChangedInstance | z2;
        if ((($changed & 14) ^ 6) <= 4) {
        }
        if (($changed & 6) == 4) {
            z3 = true;
        } else {
            z3 = z;
        }
        invalid$iv = z5 | z3 | $composer.changedInstance(permissionLauncher);
        value$iv4 = $composer.rememberedValue();
        if (invalid$iv) {
            final Function1 function7 = function3;
            value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.voice.VoiceDictationKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return VoiceDictationKt.rememberDictationLauncher$lambda$18$lambda$17(context, permissionLauncher, active$delegate, onListening, recognizer$delegate, function7, onResult);
                }
            };
            $composer.updateRememberedValue(value$iv4);
        } else {
            final Function1 function8 = function3;
            value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.voice.VoiceDictationKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return VoiceDictationKt.rememberDictationLauncher$lambda$18$lambda$17(context, permissionLauncher, active$delegate, onListening, recognizer$delegate, function8, onResult);
                }
            };
            $composer.updateRememberedValue(value$iv4);
        }
        Function0<Unit> function9 = (Function0) value$iv4;
        $composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return function9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberDictationLauncher$lambda$1$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SpeechRecognizer rememberDictationLauncher$lambda$3(MutableState<SpeechRecognizer> mutableState) {
        MutableState<SpeechRecognizer> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    private static final boolean rememberDictationLauncher$lambda$6(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void rememberDictationLauncher$lambda$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult rememberDictationLauncher$lambda$10$lambda$9(final Function1 $onListening, final MutableState $recognizer$delegate, final MutableState $active$delegate, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        return new DisposableEffectResult() { // from class: ai.nous.aurora.presentation.voice.VoiceDictationKt$rememberDictationLauncher$lambda$10$lambda$9$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                try {
                    SpeechRecognizer speechRecognizerRememberDictationLauncher$lambda$3 = VoiceDictationKt.rememberDictationLauncher$lambda$3($recognizer$delegate);
                    if (speechRecognizerRememberDictationLauncher$lambda$3 != null) {
                        speechRecognizerRememberDictationLauncher$lambda$3.cancel();
                    }
                    SpeechRecognizer speechRecognizerRememberDictationLauncher$lambda$4 = VoiceDictationKt.rememberDictationLauncher$lambda$3($recognizer$delegate);
                    if (speechRecognizerRememberDictationLauncher$lambda$4 != null) {
                        speechRecognizerRememberDictationLauncher$lambda$4.destroy();
                    }
                } catch (Exception e) {
                }
                $recognizer$delegate.setValue(null);
                VoiceDictationKt.rememberDictationLauncher$lambda$7($active$delegate, false);
                $onListening.invoke(false);
            }
        };
    }

    private static final void rememberDictationLauncher$stop(Function1<? super Boolean, Unit> function1, MutableState<SpeechRecognizer> mutableState, MutableState<Boolean> mutableState2) {
        try {
            SpeechRecognizer speechRecognizerRememberDictationLauncher$lambda$3 = rememberDictationLauncher$lambda$3(mutableState);
            if (speechRecognizerRememberDictationLauncher$lambda$3 != null) {
                speechRecognizerRememberDictationLauncher$lambda$3.stopListening();
            }
        } catch (Exception e) {
        }
        rememberDictationLauncher$lambda$7(mutableState2, false);
        function1.invoke(false);
    }

    private static final void rememberDictationLauncher$startInternal(Context context, final Function1<? super String, Unit> function1, final Function1<? super Boolean, Unit> function2, MutableState<SpeechRecognizer> mutableState, final MutableState<Boolean> mutableState2, final Function1<? super String, Unit> function3) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            function1.invoke("Speech recognition unavailable on this device");
            return;
        }
        if (rememberDictationLauncher$lambda$3(mutableState) == null) {
            SpeechRecognizer sr = SpeechRecognizer.createSpeechRecognizer(context);
            sr.setRecognitionListener(new RecognitionListener() { // from class: ai.nous.aurora.presentation.voice.VoiceDictationKt$rememberDictationLauncher$startInternal$1$1
                @Override // android.speech.RecognitionListener
                public void onReadyForSpeech(Bundle params) {
                    VoiceDictationKt.rememberDictationLauncher$lambda$7(mutableState2, true);
                    function2.invoke(true);
                }

                @Override // android.speech.RecognitionListener
                public void onBeginningOfSpeech() {
                }

                @Override // android.speech.RecognitionListener
                public void onRmsChanged(float rmsdB) {
                }

                @Override // android.speech.RecognitionListener
                public void onBufferReceived(byte[] buffer) {
                }

                @Override // android.speech.RecognitionListener
                public void onPartialResults(Bundle partialResults) {
                }

                @Override // android.speech.RecognitionListener
                public void onEvent(int eventType, Bundle params) {
                }

                @Override // android.speech.RecognitionListener
                public void onEndOfSpeech() {
                    VoiceDictationKt.rememberDictationLauncher$lambda$7(mutableState2, false);
                    function2.invoke(false);
                }

                @Override // android.speech.RecognitionListener
                public void onError(int error) {
                    VoiceDictationKt.rememberDictationLauncher$lambda$7(mutableState2, false);
                    function2.invoke(false);
                    if (error != 5 && error != 7) {
                        function1.invoke("Dictation error (" + error + ")");
                    }
                }

                @Override // android.speech.RecognitionListener
                public void onResults(Bundle results) {
                    String str;
                    VoiceDictationKt.rememberDictationLauncher$lambda$7(mutableState2, false);
                    function2.invoke(false);
                    String best = null;
                    ArrayList<String> stringArrayList = results != null ? results.getStringArrayList("results_recognition") : null;
                    if (stringArrayList != null && (str = (String) CollectionsKt.firstOrNull((List) stringArrayList)) != null) {
                        best = StringsKt.trim((CharSequence) str).toString();
                    }
                    if (best == null) {
                        best = "";
                    }
                    if (best.length() > 0) {
                        function3.invoke(best);
                    }
                }
            });
            mutableState.setValue(sr);
        }
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.LANGUAGE", Locale.getDefault());
        intent.putExtra("android.speech.extra.PARTIAL_RESULTS", false);
        intent.putExtra("android.speech.extra.MAX_RESULTS", 1);
        try {
            rememberDictationLauncher$lambda$7(mutableState2, true);
            function2.invoke(true);
            SpeechRecognizer speechRecognizerRememberDictationLauncher$lambda$3 = rememberDictationLauncher$lambda$3(mutableState);
            if (speechRecognizerRememberDictationLauncher$lambda$3 != null) {
                speechRecognizerRememberDictationLauncher$lambda$3.startListening(intent);
            }
        } catch (Throwable t) {
            rememberDictationLauncher$lambda$7(mutableState2, false);
            function2.invoke(false);
            String message = t.getMessage();
            if (message == null) {
                message = "Could not start dictation";
            }
            function1.invoke(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberDictationLauncher$lambda$14$lambda$13(Function1 $onError) {
        $onError.invoke("Microphone unavailable in this context");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberDictationLauncher$lambda$16$lambda$15(Function1 $onError, Context $context, Function1 $onListening, MutableState $recognizer$delegate, MutableState $active$delegate, Function1 $onResult, boolean granted) {
        if (granted) {
            rememberDictationLauncher$startInternal($context, $onError, $onListening, $recognizer$delegate, $active$delegate, $onResult);
        } else {
            $onError.invoke("Microphone permission denied");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberDictationLauncher$lambda$18$lambda$17(Context $context, ManagedActivityResultLauncher $permissionLauncher, MutableState $active$delegate, Function1 $onListening, MutableState $recognizer$delegate, Function1 $onError, Function1 $onResult) {
        if (rememberDictationLauncher$lambda$6($active$delegate)) {
            rememberDictationLauncher$stop($onListening, $recognizer$delegate, $active$delegate);
        } else {
            boolean granted = ContextCompat.checkSelfPermission($context, "android.permission.RECORD_AUDIO") == 0;
            if (granted) {
                rememberDictationLauncher$startInternal($context, $onError, $onListening, $recognizer$delegate, $active$delegate, $onResult);
            } else {
                $permissionLauncher.launch("android.permission.RECORD_AUDIO");
            }
        }
        return Unit.INSTANCE;
    }
}
