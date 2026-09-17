package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.InputTransformationKt;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.TextObfuscationMode;
import androidx.compose.foundation.text.input.internal.CodepointTransformation;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BasicSecureTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÊ\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u001328\b\u0002\u0010\u0014\u001a2\u0012\u0004\u0012\u00020\u0016\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0017¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0015¢\u0006\u0002\b\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a \u0010(\u001a\u00020\u00052\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00050\u0017¢\u0006\u0002\b*H\u0003¢\u0006\u0002\u0010+\u001a\u001a\u0010,\u001a\u0004\u0018\u00010\r*\u0004\u0018\u00010\r2\b\u0010-\u001a\u0004\u0018\u00010\rH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006."}, d2 = {"DefaultObfuscationCharacter", "", "LAST_TYPED_CHARACTER_REVEAL_DURATION_MILLIS", "", "BasicSecureTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "Lkotlin/ExtensionFunctionType;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "decorator", "Landroidx/compose/foundation/text/input/TextFieldDecorator;", "textObfuscationMode", "Landroidx/compose/foundation/text/input/TextObfuscationMode;", "textObfuscationCharacter", "BasicSecureTextField-Jb9bMDk", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/TextFieldDecorator;ICLandroidx/compose/runtime/Composer;III)V", "DisableCutCopy", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "then", "next", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BasicSecureTextFieldKt {
    private static final char DefaultObfuscationCharacter = 8226;
    private static final long LAST_TYPED_CHARACTER_REVEAL_DURATION_MILLIS = 1500;

    /* JADX WARN: Code duplicated, block: B:235:0x03ba  */
    /* JADX WARN: Code duplicated, block: B:236:0x03bf  */
    /* JADX WARN: Code duplicated, block: B:239:0x03ff  */
    /* JADX INFO: renamed from: BasicSecureTextField-Jb9bMDk, reason: not valid java name */
    public static final void m1322BasicSecureTextFieldJb9bMDk(final TextFieldState state, Modifier modifier, boolean enabled, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function2, MutableInteractionSource interactionSource, Brush cursorBrush, TextFieldDecorator decorator, int textObfuscationMode, char textObfuscationCharacter, Composer $composer, final int $changed, final int $changed1, final int i) {
        KeyboardOptions keyboardOptions2;
        KeyboardActionHandler onKeyboardAction2;
        int $dirty1;
        Object value$iv;
        Modifier.Companion focusChangeModifier;
        TextFieldDecorator decorator2;
        char textObfuscationCharacter2;
        Brush cursorBrush2;
        int textObfuscationMode2;
        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function3;
        Modifier modifier2;
        MutableInteractionSource interactionSource2;
        boolean enabled2;
        InputTransformation inputTransformation2;
        TextStyle textStyle2;
        Composer $composer2 = $composer.startRestartGroup(1399310985);
        ComposerKt.sourceInformation($composer2, "C(BasicSecureTextField)P(9,6,2,3,12,5,7,8,4!2,11:c#foundation.text.input.TextObfuscationMode)131@7694L46,132@7777L60,133@7884L130,133@7842L172,144@8392L129,144@8353L168,150@8557L384,176@9335L825,176@9320L840:BasicSecureTextField.kt#423gt5");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(enabled) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(inputTransformation) ? 2048 : 1024;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(textStyle) ? 16384 : 8192;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changed(keyboardOptions) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(onKeyboardAction) ? 1048576 : 524288;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer2.changed(cursorBrush) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i11 = i & 1024;
        if (i11 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty2 |= ($changed1 & 8) == 0 ? $composer2.changed(decorator) : $composer2.changedInstance(decorator) ? 4 : 2;
        }
        int i12 = i & 2048;
        if (i12 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty2 |= $composer2.changed(textObfuscationMode) ? 32 : 16;
        }
        int i13 = i & 4096;
        if (i13 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty2 |= $composer2.changed(textObfuscationCharacter) ? 256 : 128;
        }
        if (($dirty & 306783379) == 306783378 && ($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            enabled2 = enabled;
            inputTransformation2 = inputTransformation;
            textStyle2 = textStyle;
            keyboardOptions2 = keyboardOptions;
            onKeyboardAction2 = onKeyboardAction;
            function3 = function2;
            interactionSource2 = interactionSource;
            cursorBrush2 = cursorBrush;
            decorator2 = decorator;
            textObfuscationMode2 = textObfuscationMode;
            textObfuscationCharacter2 = textObfuscationCharacter;
            $dirty1 = $dirty2;
        } else {
            Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
            boolean enabled3 = i3 != 0 ? true : enabled;
            InputTransformation inputTransformation3 = i4 != 0 ? null : inputTransformation;
            TextStyle textStyle3 = i5 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle;
            keyboardOptions2 = i6 != 0 ? KeyboardOptions.INSTANCE.getSecureTextField$foundation_release() : keyboardOptions;
            onKeyboardAction2 = i7 != 0 ? null : onKeyboardAction;
            Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function4 = i8 != 0 ? null : function2;
            MutableInteractionSource interactionSource3 = i9 != 0 ? null : interactionSource;
            SolidColor cursorBrush3 = i10 != 0 ? new SolidColor(Color.INSTANCE.m4560getBlack0d7_KjU(), null) : cursorBrush;
            TextFieldDecorator decorator3 = i11 != 0 ? null : decorator;
            int textObfuscationMode3 = i12 != 0 ? TextObfuscationMode.INSTANCE.m1483getRevealLastTypedvTwcZD0() : textObfuscationMode;
            char textObfuscationCharacter3 = i13 != 0 ? (char) 8226 : textObfuscationCharacter;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1399310985, $dirty, $dirty2, "androidx.compose.foundation.text.BasicSecureTextField (BasicSecureTextField.kt:130)");
            }
            final State obfuscationMaskState = SnapshotStateKt.rememberUpdatedState(Character.valueOf(textObfuscationCharacter3), $composer2, ($dirty2 >> 6) & 14);
            ComposerKt.sourceInformationMarkerStart($composer2, 120982059, "CC(remember):BasicSecureTextField.kt#9igjgp");
            Object value$iv2 = $composer2.rememberedValue();
            char textObfuscationCharacter4 = textObfuscationCharacter3;
            if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new SecureTextFieldController(obfuscationMaskState);
                $composer2.updateRememberedValue(value$iv2);
            }
            final SecureTextFieldController secureTextFieldController = (SecureTextFieldController) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 120985553, "CC(remember):BasicSecureTextField.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(secureTextFieldController);
            final TextFieldDecorator decorator4 = decorator3;
            BasicSecureTextFieldKt$BasicSecureTextField$1$1 value$iv3 = $composer2.rememberedValue();
            if (invalid$iv || value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = new BasicSecureTextFieldKt$BasicSecureTextField$1$1(secureTextFieldController, null);
                $composer2.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(secureTextFieldController, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv3, $composer2, 0);
            final boolean revealLastTypedEnabled = TextObfuscationMode.m1478equalsimpl0(textObfuscationMode3, TextObfuscationMode.INSTANCE.m1483getRevealLastTypedvTwcZD0());
            Boolean boolValueOf = Boolean.valueOf(revealLastTypedEnabled);
            ComposerKt.sourceInformationMarkerStart($composer2, 121001808, "CC(remember):BasicSecureTextField.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changed(revealLastTypedEnabled) | $composer2.changedInstance(secureTextFieldController);
            final Brush cursorBrush4 = cursorBrush3;
            BasicSecureTextFieldKt$BasicSecureTextField$2$1 value$iv4 = $composer2.rememberedValue();
            if (invalid$iv2 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new BasicSecureTextFieldKt$BasicSecureTextField$2$1(revealLastTypedEnabled, secureTextFieldController, null);
                $composer2.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 121007343, "CC(remember):BasicSecureTextField.kt#9igjgp");
            boolean invalid$iv3 = ($dirty2 & 112) == 32;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv3) {
                $dirty1 = $dirty2;
            } else {
                $dirty1 = $dirty2;
                if (it$iv != Composer.INSTANCE.getEmpty()) {
                    value$iv = it$iv;
                }
                final CodepointTransformation codepointTransformation = (CodepointTransformation) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier3, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.password($this$semantics);
                        SemanticsPropertiesKt.copyText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                return false;
                            }
                        }, 1, null);
                        SemanticsPropertiesKt.cutText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1.2
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                return false;
                            }
                        }, 1, null);
                    }
                });
                if (revealLastTypedEnabled) {
                    focusChangeModifier = secureTextFieldController.getFocusChangeModifier();
                } else {
                    focusChangeModifier = Modifier.INSTANCE;
                }
                final Modifier secureTextFieldModifier = modifierSemantics.then(focusChangeModifier);
                final boolean z = enabled3;
                final InputTransformation inputTransformation4 = inputTransformation3;
                final TextStyle textStyle4 = textStyle3;
                final KeyboardOptions keyboardOptions3 = keyboardOptions2;
                final KeyboardActionHandler keyboardActionHandler = onKeyboardAction2;
                final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function5 = function4;
                final MutableInteractionSource mutableInteractionSource = interactionSource3;
                DisableCutCopy(ComposableLambdaKt.rememberComposableLambda(2023988909, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed2) {
                        InputTransformation inputTransformationThen;
                        ComposerKt.sourceInformation($composer3, "C177@9345L809:BasicSecureTextField.kt#423gt5");
                        if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2023988909, $changed2, -1, "androidx.compose.foundation.text.BasicSecureTextField.<anonymous> (BasicSecureTextField.kt:177)");
                            }
                            TextFieldState textFieldState = state;
                            Modifier modifier4 = secureTextFieldModifier;
                            boolean z2 = z;
                            if (revealLastTypedEnabled) {
                                inputTransformationThen = BasicSecureTextFieldKt.then(inputTransformation4, secureTextFieldController.getPasswordInputTransformation());
                            } else {
                                inputTransformationThen = inputTransformation4;
                            }
                            BasicTextFieldKt.BasicTextField(textFieldState, modifier4, z2, false, inputTransformationThen, textStyle4, keyboardOptions3, keyboardActionHandler, TextFieldLineLimits.SingleLine.INSTANCE, function5, mutableInteractionSource, cursorBrush4, codepointTransformation, null, decorator4, null, true, $composer3, 100666368, 1572864, 40960);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }, $composer2, 54), $composer2, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                decorator2 = decorator4;
                textObfuscationCharacter2 = textObfuscationCharacter4;
                cursorBrush2 = cursorBrush4;
                textObfuscationMode2 = textObfuscationMode3;
                function3 = function4;
                modifier2 = modifier3;
                interactionSource2 = interactionSource3;
                enabled2 = enabled3;
                inputTransformation2 = inputTransformation3;
                textStyle2 = textStyle3;
            }
            if (TextObfuscationMode.m1478equalsimpl0(textObfuscationMode3, TextObfuscationMode.INSTANCE.m1483getRevealLastTypedvTwcZD0())) {
                value$iv = secureTextFieldController.getCodepointTransformation();
            } else {
                value$iv = TextObfuscationMode.m1478equalsimpl0(textObfuscationMode3, TextObfuscationMode.INSTANCE.m1482getHiddenvTwcZD0()) ? new CodepointTransformation() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda0
                    @Override // androidx.compose.foundation.text.input.internal.CodepointTransformation
                    public final int transform(int i14, int i15) {
                        return BasicSecureTextFieldKt.BasicSecureTextField_Jb9bMDk$lambda$4$lambda$3(obfuscationMaskState, i14, i15);
                    }
                } : null;
            }
            $composer2.updateRememberedValue(value$iv);
            final CodepointTransformation codepointTransformation2 = (CodepointTransformation) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierSemantics2 = SemanticsModifierKt.semantics(modifier3, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.password($this$semantics);
                    SemanticsPropertiesKt.copyText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return false;
                        }
                    }, 1, null);
                    SemanticsPropertiesKt.cutText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1.2
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return false;
                        }
                    }, 1, null);
                }
            });
            if (revealLastTypedEnabled) {
                focusChangeModifier = secureTextFieldController.getFocusChangeModifier();
            } else {
                focusChangeModifier = Modifier.INSTANCE;
            }
            final Modifier secureTextFieldModifier2 = modifierSemantics2.then(focusChangeModifier);
            final boolean z2 = enabled3;
            final InputTransformation inputTransformation5 = inputTransformation3;
            final TextStyle textStyle5 = textStyle3;
            final KeyboardOptions keyboardOptions4 = keyboardOptions2;
            final KeyboardActionHandler keyboardActionHandler2 = onKeyboardAction2;
            final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function6 = function4;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource3;
            DisableCutCopy(ComposableLambdaKt.rememberComposableLambda(2023988909, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    InputTransformation inputTransformationThen;
                    ComposerKt.sourceInformation($composer3, "C177@9345L809:BasicSecureTextField.kt#423gt5");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2023988909, $changed2, -1, "androidx.compose.foundation.text.BasicSecureTextField.<anonymous> (BasicSecureTextField.kt:177)");
                        }
                        TextFieldState textFieldState = state;
                        Modifier modifier4 = secureTextFieldModifier2;
                        boolean z3 = z2;
                        if (revealLastTypedEnabled) {
                            inputTransformationThen = BasicSecureTextFieldKt.then(inputTransformation5, secureTextFieldController.getPasswordInputTransformation());
                        } else {
                            inputTransformationThen = inputTransformation5;
                        }
                        BasicTextFieldKt.BasicTextField(textFieldState, modifier4, z3, false, inputTransformationThen, textStyle5, keyboardOptions4, keyboardActionHandler2, TextFieldLineLimits.SingleLine.INSTANCE, function6, mutableInteractionSource2, cursorBrush4, codepointTransformation2, null, decorator4, null, true, $composer3, 100666368, 1572864, 40960);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            decorator2 = decorator4;
            textObfuscationCharacter2 = textObfuscationCharacter4;
            cursorBrush2 = cursorBrush4;
            textObfuscationMode2 = textObfuscationMode3;
            function3 = function4;
            modifier2 = modifier3;
            interactionSource2 = interactionSource3;
            enabled2 = enabled3;
            inputTransformation2 = inputTransformation3;
            textStyle2 = textStyle3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final boolean z3 = enabled2;
            final InputTransformation inputTransformation6 = inputTransformation2;
            final TextStyle textStyle6 = textStyle2;
            final KeyboardOptions keyboardOptions5 = keyboardOptions2;
            final KeyboardActionHandler keyboardActionHandler3 = onKeyboardAction2;
            final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function7 = function3;
            final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
            final Brush brush = cursorBrush2;
            final TextFieldDecorator textFieldDecorator = decorator2;
            final int i14 = textObfuscationMode2;
            final char c = textObfuscationCharacter2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i15) {
                    BasicSecureTextFieldKt.m1322BasicSecureTextFieldJb9bMDk(state, modifier4, z3, inputTransformation6, textStyle6, keyboardOptions5, keyboardActionHandler3, function7, mutableInteractionSource3, brush, textFieldDecorator, i14, c, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int BasicSecureTextField_Jb9bMDk$lambda$4$lambda$3(State $obfuscationMaskState, int i, int i2) {
        return ((Character) $obfuscationMaskState.getValue()).charValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final InputTransformation then(InputTransformation $this$then, InputTransformation next) {
        if ($this$then == null) {
            return next;
        }
        return next == null ? $this$then : InputTransformationKt.then($this$then, next);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DisableCutCopy(final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(-1085555050);
        ComposerKt.sourceInformation($composer2, "C(DisableCutCopy)311@13911L7,312@13949L680,331@14706L361,331@14634L433:BasicSecureTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($dirty & 3) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1085555050, $dirty, -1, "androidx.compose.foundation.text.DisableCutCopy (BasicSecureTextField.kt:310)");
            }
            ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localTextToolbar);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final TextToolbar currentToolbar = (TextToolbar) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer2, -1202776994, "CC(remember):BasicSecureTextField.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(currentToolbar);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new TextToolbar() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1
                    private final /* synthetic */ TextToolbar $$delegate_0;

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public TextToolbarStatus getStatus() {
                        return this.$$delegate_0.getStatus();
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void hide() {
                        this.$$delegate_0.hide();
                    }

                    {
                        this.$$delegate_0 = this.$currentToolbar;
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void showMenu(Rect rect, Function0<Unit> onCopyRequested, Function0<Unit> onPasteRequested, Function0<Unit> onCutRequested, Function0<Unit> onSelectAllRequested) {
                        this.$currentToolbar.showMenu(rect, null, onPasteRequested, null, onSelectAllRequested);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1 copyDisabledToolbar = (BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalTextToolbar().provides(copyDisabledToolbar), ComposableLambdaKt.rememberComposableLambda(-1448819882, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt.DisableCutCopy.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer3, "C332@14716L345:BasicSecureTextField.kt#423gt5");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1448819882, $changed2, -1, "androidx.compose.foundation.text.DisableCutCopy.<anonymous> (BasicSecureTextField.kt:332)");
                        }
                        Modifier modifier$iv = KeyInputModifierKt.onPreviewKeyEvent(Modifier.INSTANCE, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt.DisableCutCopy.1.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                                return m1323invokeZmokQxo(keyEvent.m5588unboximpl());
                            }

                            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                            public final Boolean m1323invokeZmokQxo(android.view.KeyEvent keyEvent) {
                                KeyCommand command = KeyMapping_androidKt.getPlatformDefaultKeyMapping().mo1350mapZmokQxo(keyEvent);
                                return Boolean.valueOf(command == KeyCommand.COPY || command == KeyCommand.CUT);
                            }
                        });
                        Function2<Composer, Integer, Unit> function3 = function2;
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function0 = constructor;
                            $composer3.createNode(function0);
                        } else {
                            function0 = constructor;
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer3);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i2 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1177942586, "C338@15042L9:BasicSecureTextField.kt#423gt5");
                        function3.invoke($composer3, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt.DisableCutCopy.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    BasicSecureTextFieldKt.DisableCutCopy(function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
