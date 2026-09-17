package androidx.compose.foundation.text.input.internal;

import android.os.Build;
import android.os.CancellationSignal;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.PreviewableHandwritingGesture;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.ui.platform.PlatformTextInputMethodRequest;
import androidx.compose.ui.platform.PlatformTextInputSession;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0002\u001al\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\n\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0081@¢\u0006\u0002\u0010\"\u001ah\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\n\u0018\u00010\u001a2\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!H\u0080@¢\u0006\u0002\u0010#\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00058\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\"\u000e\u0010\b\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"ALL_MIME_TYPES", "", "", "[Ljava/lang/String;", "TIA_DEBUG", "", "getTIA_DEBUG$annotations", "()V", "TIA_TAG", "logDebug", "", "tag", "content", "Lkotlin/Function0;", "platformSpecificTextInputSession", "", "Landroidx/compose/ui/platform/PlatformTextInputSession;", "state", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "layoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "receiveContentConfiguration", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "onImeAction", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/ImeAction;", "composeImm", "Landroidx/compose/foundation/text/input/internal/ComposeInputMethodManager;", "stylusHandwritingTrigger", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "(Landroidx/compose/ui/platform/PlatformTextInputSession;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/text/input/internal/ComposeInputMethodManager;Lkotlinx/coroutines/flow/MutableSharedFlow;Landroidx/compose/ui/platform/ViewConfiguration;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/compose/ui/platform/PlatformTextInputSession;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;Lkotlin/jvm/functions/Function1;Lkotlinx/coroutines/flow/MutableSharedFlow;Landroidx/compose/ui/platform/ViewConfiguration;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidTextInputSession_androidKt {
    private static final String[] ALL_MIME_TYPES = {"*/*", "image/*", "video/*"};
    public static final boolean TIA_DEBUG = false;
    private static final String TIA_TAG = "AndroidTextInputSession";

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$1, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt", f = "AndroidTextInputSession.android.kt", i = {}, l = {59}, m = "platformSpecificTextInputSession", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidTextInputSession_androidKt.platformSpecificTextInputSession(null, null, null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt", f = "AndroidTextInputSession.android.kt", i = {}, l = {82}, m = "platformSpecificTextInputSession", n = {}, s = {})
    static final class AnonymousClass2 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidTextInputSession_androidKt.platformSpecificTextInputSession(null, null, null, null, null, null, null, null, null, this);
        }
    }

    public static /* synthetic */ void getTIA_DEBUG$annotations() {
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object platformSpecificTextInputSession(PlatformTextInputSession $this$platformSpecificTextInputSession, TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, ImeOptions imeOptions, ReceiveContentConfiguration receiveContentConfiguration, Function1<? super ImeAction, Unit> function1, MutableSharedFlow<Unit> mutableSharedFlow, ViewConfiguration viewConfiguration, Continuation<?> continuation) {
        AnonymousClass1 anonymousClass1;
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
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ComposeInputMethodManager ComposeInputMethodManager = ComposeInputMethodManager_androidKt.ComposeInputMethodManager($this$platformSpecificTextInputSession.getView());
                anonymousClass2.label = 1;
                if (platformSpecificTextInputSession($this$platformSpecificTextInputSession, transformedTextFieldState, textLayoutState, imeOptions, receiveContentConfiguration, function1, ComposeInputMethodManager, mutableSharedFlow, viewConfiguration, anonymousClass2) == coroutine_suspended) {
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

    public static /* synthetic */ Object platformSpecificTextInputSession$default(PlatformTextInputSession platformTextInputSession, TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, ImeOptions imeOptions, ReceiveContentConfiguration receiveContentConfiguration, Function1 function1, MutableSharedFlow mutableSharedFlow, ViewConfiguration viewConfiguration, Continuation continuation, int i, Object obj) {
        MutableSharedFlow mutableSharedFlow2;
        ViewConfiguration viewConfiguration2;
        if ((i & 32) == 0) {
            mutableSharedFlow2 = mutableSharedFlow;
        } else {
            mutableSharedFlow2 = null;
        }
        if ((i & 64) == 0) {
            viewConfiguration2 = viewConfiguration;
        } else {
            viewConfiguration2 = null;
        }
        return platformSpecificTextInputSession(platformTextInputSession, transformedTextFieldState, textLayoutState, imeOptions, receiveContentConfiguration, function1, mutableSharedFlow2, viewConfiguration2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object platformSpecificTextInputSession(PlatformTextInputSession $this$platformSpecificTextInputSession, TransformedTextFieldState state, TextLayoutState layoutState, ImeOptions imeOptions, ReceiveContentConfiguration receiveContentConfiguration, Function1<? super ImeAction, Unit> function1, ComposeInputMethodManager composeImm, MutableSharedFlow<Unit> mutableSharedFlow, ViewConfiguration viewConfiguration, Continuation<?> continuation) {
        AnonymousClass2 anonymousClass2;
        if (continuation instanceof AnonymousClass2) {
            anonymousClass2 = (AnonymousClass2) continuation;
            if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                anonymousClass2.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass2 = new AnonymousClass2(continuation);
            }
        } else {
            anonymousClass2 = new AnonymousClass2(continuation);
        }
        AnonymousClass2 anonymousClass3 = anonymousClass2;
        Object $result = anonymousClass3.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass3.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                AnonymousClass3 anonymousClass4 = new AnonymousClass3(mutableSharedFlow, state, layoutState, composeImm, $this$platformSpecificTextInputSession, imeOptions, receiveContentConfiguration, function1, viewConfiguration, null);
                anonymousClass3.label = 1;
                if (CoroutineScopeKt.coroutineScope(anonymousClass4, anonymousClass3) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3", f = "AndroidTextInputSession.android.kt", i = {}, l = {129}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<?>, Object> {
        final /* synthetic */ ComposeInputMethodManager $composeImm;
        final /* synthetic */ ImeOptions $imeOptions;
        final /* synthetic */ TextLayoutState $layoutState;
        final /* synthetic */ Function1<ImeAction, Unit> $onImeAction;
        final /* synthetic */ ReceiveContentConfiguration $receiveContentConfiguration;
        final /* synthetic */ TransformedTextFieldState $state;
        final /* synthetic */ MutableSharedFlow<Unit> $stylusHandwritingTrigger;
        final /* synthetic */ PlatformTextInputSession $this_platformSpecificTextInputSession;
        final /* synthetic */ ViewConfiguration $viewConfiguration;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(MutableSharedFlow<Unit> mutableSharedFlow, TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, ComposeInputMethodManager composeInputMethodManager, PlatformTextInputSession platformTextInputSession, ImeOptions imeOptions, ReceiveContentConfiguration receiveContentConfiguration, Function1<? super ImeAction, Unit> function1, ViewConfiguration viewConfiguration, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$stylusHandwritingTrigger = mutableSharedFlow;
            this.$state = transformedTextFieldState;
            this.$layoutState = textLayoutState;
            this.$composeImm = composeInputMethodManager;
            this.$this_platformSpecificTextInputSession = platformTextInputSession;
            this.$imeOptions = imeOptions;
            this.$receiveContentConfiguration = receiveContentConfiguration;
            this.$onImeAction = function1;
            this.$viewConfiguration = viewConfiguration;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$stylusHandwritingTrigger, this.$state, this.$layoutState, this.$composeImm, this.$this_platformSpecificTextInputSession, this.$imeOptions, this.$receiveContentConfiguration, this.$onImeAction, this.$viewConfiguration, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<?> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$1", f = "AndroidTextInputSession.android.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ComposeInputMethodManager $composeImm;
            final /* synthetic */ TransformedTextFieldState $state;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TransformedTextFieldState transformedTextFieldState, ComposeInputMethodManager composeInputMethodManager, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$state = transformedTextFieldState;
                this.$composeImm = composeInputMethodManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$state, this.$composeImm, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        TransformedTextFieldState transformedTextFieldState = this.$state;
                        final ComposeInputMethodManager composeInputMethodManager = this.$composeImm;
                        this.label = 1;
                        if (transformedTextFieldState.collectImeNotifications(new TextFieldState.NotifyImeListener() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$1$$ExternalSyntheticLambda0
                            @Override // androidx.compose.foundation.text.input.TextFieldState.NotifyImeListener
                            public final void onChange(TextFieldCharSequence textFieldCharSequence, TextFieldCharSequence textFieldCharSequence2, boolean z) {
                                AndroidTextInputSession_androidKt.AnonymousClass3.AnonymousClass1.invokeSuspend$lambda$0(composeInputMethodManager, textFieldCharSequence, textFieldCharSequence2, z);
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

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invokeSuspend$lambda$0(ComposeInputMethodManager $composeImm, TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartImeIfContentChanges) {
                long oldSelection = oldValue.getSelection();
                long newSelection = newValue.getSelection();
                TextRange oldComposition = oldValue.getComposition();
                TextRange newComposition = newValue.getComposition();
                if (restartImeIfContentChanges && oldValue.getComposition() != null && !oldValue.contentEquals(newValue)) {
                    $composeImm.restartInput();
                } else if (!TextRange.m6472equalsimpl0(oldSelection, newSelection) || !Intrinsics.areEqual(oldComposition, newComposition)) {
                    $composeImm.updateSelection(TextRange.m6477getMinimpl(newSelection), TextRange.m6476getMaximpl(newSelection), newComposition != null ? TextRange.m6477getMinimpl(newComposition.getPackedValue()) : -1, newComposition != null ? TextRange.m6476getMaximpl(newComposition.getPackedValue()) : -1);
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(this.$state, this.$composeImm, null), 1, null);
                    MutableSharedFlow<Unit> mutableSharedFlow = this.$stylusHandwritingTrigger;
                    if (mutableSharedFlow != null) {
                        BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1(mutableSharedFlow, this.$composeImm, null), 3, null);
                    }
                    final CursorAnchorInfoController cursorUpdatesController = new CursorAnchorInfoController(this.$state, this.$layoutState, this.$composeImm, $this$coroutineScope);
                    PlatformTextInputSession platformTextInputSession = this.$this_platformSpecificTextInputSession;
                    final TransformedTextFieldState transformedTextFieldState = this.$state;
                    final ImeOptions imeOptions = this.$imeOptions;
                    final ReceiveContentConfiguration receiveContentConfiguration = this.$receiveContentConfiguration;
                    final ComposeInputMethodManager composeInputMethodManager = this.$composeImm;
                    final Function1<ImeAction, Unit> function1 = this.$onImeAction;
                    final TextLayoutState textLayoutState = this.$layoutState;
                    final ViewConfiguration viewConfiguration = this.$viewConfiguration;
                    this.label = 1;
                    if (platformTextInputSession.startInputMethod(new PlatformTextInputMethodRequest() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$$ExternalSyntheticLambda0
                        @Override // androidx.compose.ui.platform.PlatformTextInputMethodRequest
                        public final InputConnection createInputConnection(EditorInfo editorInfo) {
                            return AndroidTextInputSession_androidKt.AnonymousClass3.invokeSuspend$lambda$2(transformedTextFieldState, imeOptions, receiveContentConfiguration, composeInputMethodManager, function1, cursorUpdatesController, textLayoutState, viewConfiguration, editorInfo);
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

        /* JADX INFO: Access modifiers changed from: private */
        public static final InputConnection invokeSuspend$lambda$2(final TransformedTextFieldState $state, ImeOptions $imeOptions, final ReceiveContentConfiguration $receiveContentConfiguration, final ComposeInputMethodManager $composeImm, final Function1 $onImeAction, final CursorAnchorInfoController $cursorUpdatesController, final TextLayoutState $layoutState, final ViewConfiguration $viewConfiguration, EditorInfo outAttrs) {
            AndroidTextInputSession_androidKt.logDebug$default(null, new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$3$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "createInputConnection(value=\"" + ((Object) $state.getVisualText()) + "\")";
                }
            }, 1, null);
            TextInputSession textInputSession = new TextInputSession() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$3$textInputSession$1
                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public TextFieldCharSequence getText() {
                    return $state.getVisualText();
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public void requestEdit(Function1<? super EditingBuffer, Unit> block) {
                    TransformedTextFieldState this_$iv = $state;
                    TextFieldState $this$iv$iv = this_$iv.textFieldState;
                    InputTransformation inputTransformation$iv$iv = this_$iv.inputTransformation;
                    TextFieldEditUndoBehavior undoBehavior$iv$iv = TextFieldEditUndoBehavior.MergeIfPossible;
                    $this$iv$iv.getMainBuffer().getChangeTracker().clearChanges();
                    block.invoke($this$iv$iv.getMainBuffer());
                    $this$iv$iv.commitEditAsUser(inputTransformation$iv$iv, false, undoBehavior$iv$iv);
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public void sendKeyEvent(KeyEvent keyEvent) {
                    $composeImm.sendKeyEvent(keyEvent);
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                /* JADX INFO: renamed from: onImeAction-KlQnJC8, reason: not valid java name */
                public void mo1489onImeActionKlQnJC8(int imeAction) {
                    Function1<ImeAction, Unit> function1 = $onImeAction;
                    if (function1 != null) {
                        function1.invoke(ImeAction.m6641boximpl(imeAction));
                    }
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public boolean onCommitContent(TransferableContent transferableContent) {
                    ReceiveContentConfiguration receiveContentConfiguration = $receiveContentConfiguration;
                    if (receiveContentConfiguration != null) {
                        return receiveContentConfiguration.onCommitContent(transferableContent);
                    }
                    return false;
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public void requestCursorUpdates(int cursorUpdateMode) {
                    $cursorUpdatesController.requestUpdates(cursorUpdateMode);
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public int performHandwritingGesture(HandwritingGesture gesture) {
                    if (Build.VERSION.SDK_INT >= 34) {
                        return HandwritingGestureApi34.INSTANCE.performHandwritingGesture$foundation_release($state, gesture, $layoutState, $viewConfiguration);
                    }
                    return 2;
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public boolean previewHandwritingGesture(PreviewableHandwritingGesture gesture, CancellationSignal cancellationSignal) {
                    if (Build.VERSION.SDK_INT >= 34) {
                        return HandwritingGestureApi34.INSTANCE.previewHandwritingGesture$foundation_release($state, gesture, $layoutState, cancellationSignal);
                    }
                    return false;
                }
            };
            EditorInfo_androidKt.m1498updatepLxbY9I(outAttrs, $state.getVisualText(), $state.getVisualText().getSelection(), $imeOptions, $receiveContentConfiguration != null ? AndroidTextInputSession_androidKt.ALL_MIME_TYPES : null);
            return new StatelessInputConnection(textInputSession, outAttrs);
        }
    }

    static /* synthetic */ void logDebug$default(String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = TIA_TAG;
        }
        logDebug(str, function0);
    }

    private static final void logDebug(String tag, Function0<String> function0) {
    }
}
