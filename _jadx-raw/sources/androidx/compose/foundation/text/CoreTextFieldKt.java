package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.relocation.BringIntoViewRequesterKt;
import androidx.compose.foundation.text.handwriting.StylusHandwritingKt;
import androidx.compose.foundation.text.input.internal.LegacyAdaptingPlatformTextInputModifierNodeKt;
import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter;
import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter_androidKt;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.foundation.text.selection.SelectionHandleAnchor;
import androidx.compose.foundation.text.selection.SelectionHandleInfo;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteAllCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CoreTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aî\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001323\b\u0002\u0010\u001d\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001fH\u0001¢\u0006\u0002\u0010#\u001a0\u0010$\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0011\u0010'\u001a\r\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001fH\u0003¢\u0006\u0002\u0010(\u001a\u001d\u0010)\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0006\u0010*\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010+\u001a\u0015\u0010,\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\u0010-\u001a\u0010\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u000200H\u0002\u001a \u00101\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00102\u001a\u000203H\u0002\u001a0\u00104\u001a\u00020\u00012\u0006\u00105\u001a\u0002062\u0006\u0010/\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00102\u001a\u000203H\u0002\u001a \u00107\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0013H\u0002\u001a2\u0010;\u001a\u00020\u0001*\u00020<2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\r2\u0006\u00102\u001a\u000203H\u0080@¢\u0006\u0002\u0010@\u001a\u001c\u0010A\u001a\u00020\u0007*\u00020\u00072\u0006\u0010/\u001a\u0002002\u0006\u0010%\u001a\u00020&H\u0002¨\u0006B²\u0006\n\u0010C\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"CoreTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "softWrap", "", "maxLines", "", "minLines", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "enabled", "readOnly", "decorationBox", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "innerTextField", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;ZIILandroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/text/KeyboardActions;ZZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "CoreTextFieldRootBox", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "content", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SelectionToolbarAndHandles", "show", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;ZLandroidx/compose/runtime/Composer;I)V", "TextFieldCursorHandle", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "endInputSession", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "notifyFocusedRect", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "startInputSession", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "tapToFocus", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "allowKeyboard", "bringSelectionEndIntoView", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "textLayoutResult", "(Landroidx/compose/foundation/relocation/BringIntoViewRequester;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/input/OffsetMapping;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "previewKeyEventToDeselectOnBack", "foundation_release", "writeable"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CoreTextFieldKt {
    /* JADX WARN: Code duplicated, block: B:273:0x0517  */
    /* JADX WARN: Code duplicated, block: B:274:0x0519  */
    /* JADX WARN: Code duplicated, block: B:277:0x0525  */
    /* JADX WARN: Code duplicated, block: B:278:0x0527  */
    /* JADX WARN: Code duplicated, block: B:288:0x055c  */
    /* JADX WARN: Code duplicated, block: B:291:0x0571  */
    /* JADX WARN: Code duplicated, block: B:296:0x05aa  */
    /* JADX WARN: Code duplicated, block: B:300:0x05b9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:304:0x0656  */
    /* JADX WARN: Code duplicated, block: B:305:0x066d  */
    /* JADX WARN: Code duplicated, block: B:308:0x06a7  */
    /* JADX WARN: Code duplicated, block: B:309:0x06b5  */
    /* JADX WARN: Code duplicated, block: B:312:0x075d  */
    /* JADX WARN: Code duplicated, block: B:313:0x077a  */
    /* JADX WARN: Code duplicated, block: B:316:0x07a7  */
    /* JADX WARN: Code duplicated, block: B:317:0x07b2  */
    /* JADX WARN: Code duplicated, block: B:320:0x07d5  */
    /* JADX WARN: Code duplicated, block: B:321:0x07d7  */
    /* JADX WARN: Code duplicated, block: B:324:0x07e2  */
    /* JADX WARN: Code duplicated, block: B:325:0x07e4  */
    /* JADX WARN: Code duplicated, block: B:328:0x07f0  */
    /* JADX WARN: Code duplicated, block: B:329:0x07f2  */
    /* JADX WARN: Code duplicated, block: B:332:0x07fc  */
    /* JADX WARN: Code duplicated, block: B:335:0x0805  */
    /* JADX WARN: Code duplicated, block: B:338:0x080d A[PHI: r12
  0x080d: PHI (r12v42 'imeOptions' androidx.compose.ui.text.input.ImeOptions) = 
  (r12v38 'imeOptions' androidx.compose.ui.text.input.ImeOptions)
  (r12v43 'imeOptions' androidx.compose.ui.text.input.ImeOptions)
 binds: [B:337:0x080b, B:333:0x0802] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:339:0x080f  */
    /* JADX WARN: Code duplicated, block: B:342:0x0831  */
    /* JADX WARN: Code duplicated, block: B:346:0x083e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:350:0x0873 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:352:0x0877  */
    /* JADX WARN: Code duplicated, block: B:355:0x08ad  */
    /* JADX WARN: Code duplicated, block: B:357:0x08b3  */
    /* JADX WARN: Code duplicated, block: B:363:0x08c9  */
    /* JADX WARN: Code duplicated, block: B:367:0x08d6 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:371:0x091b  */
    /* JADX WARN: Code duplicated, block: B:375:0x0929 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:379:0x0958  */
    /* JADX WARN: Code duplicated, block: B:380:0x095a  */
    /* JADX WARN: Code duplicated, block: B:383:0x0962  */
    /* JADX WARN: Code duplicated, block: B:384:0x0964  */
    /* JADX WARN: Code duplicated, block: B:387:0x097a  */
    /* JADX WARN: Code duplicated, block: B:391:0x0987 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:395:0x09db  */
    /* JADX WARN: Code duplicated, block: B:396:0x09dd  */
    /* JADX WARN: Code duplicated, block: B:399:0x09f0  */
    /* JADX WARN: Code duplicated, block: B:403:0x0a01 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:407:0x0a35  */
    /* JADX WARN: Code duplicated, block: B:408:0x0a37  */
    /* JADX WARN: Code duplicated, block: B:411:0x0a4c  */
    /* JADX WARN: Code duplicated, block: B:412:0x0a4e  */
    /* JADX WARN: Code duplicated, block: B:415:0x0a61  */
    /* JADX WARN: Code duplicated, block: B:419:0x0a6e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:423:0x0ab2  */
    /* JADX WARN: Code duplicated, block: B:424:0x0ab4  */
    /* JADX WARN: Code duplicated, block: B:427:0x0abf  */
    /* JADX WARN: Code duplicated, block: B:428:0x0ac1  */
    /* JADX WARN: Code duplicated, block: B:431:0x0ad0  */
    /* JADX WARN: Code duplicated, block: B:432:0x0ad2  */
    /* JADX WARN: Code duplicated, block: B:435:0x0aeb  */
    /* JADX WARN: Code duplicated, block: B:437:0x0af1  */
    /* JADX WARN: Code duplicated, block: B:443:0x0b07  */
    /* JADX WARN: Code duplicated, block: B:447:0x0b14 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:451:0x0b49 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:457:0x0b59  */
    /* JADX WARN: Code duplicated, block: B:460:0x0b84  */
    /* JADX WARN: Code duplicated, block: B:464:0x0b91 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:468:0x0bc3  */
    /* JADX WARN: Code duplicated, block: B:469:0x0bc5  */
    /* JADX WARN: Code duplicated, block: B:472:0x0bcf  */
    /* JADX WARN: Code duplicated, block: B:474:0x0bd5  */
    /* JADX WARN: Code duplicated, block: B:480:0x0beb  */
    /* JADX WARN: Code duplicated, block: B:484:0x0bfc A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:488:0x0c32  */
    /* JADX WARN: Code duplicated, block: B:489:0x0c35  */
    /* JADX WARN: Code duplicated, block: B:492:0x0c95  */
    /* JADX WARN: Code duplicated, block: B:494:0x0c9b  */
    /* JADX WARN: Code duplicated, block: B:500:0x0cb4  */
    /* JADX WARN: Code duplicated, block: B:504:0x0cbf  */
    /* JADX WARN: Code duplicated, block: B:507:0x0d1f  */
    /* JADX WARN: Code duplicated, block: B:516:0x0d36  */
    /* JADX WARN: Code duplicated, block: B:517:0x0d3f  */
    /* JADX WARN: Code duplicated, block: B:520:0x0dcb  */
    public static final void CoreTextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, TextStyle textStyle, VisualTransformation visualTransformation, Function1<? super TextLayoutResult, Unit> function2, MutableInteractionSource interactionSource, Brush cursorBrush, boolean softWrap, int maxLines, int minLines, ImeOptions imeOptions, KeyboardActions keyboardActions, boolean enabled, boolean readOnly, Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        ImeOptions imeOptions2;
        VisualTransformation visualTransformation2;
        Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3M1344getLambda1$foundation_release;
        Brush cursorBrush2;
        boolean softWrap2;
        int minLines2;
        KeyboardActions keyboardActions2;
        TextStyle textStyle2;
        boolean enabled2;
        boolean readOnly2;
        Function1<? super TextLayoutResult, Unit> function4;
        MutableInteractionSource interactionSource2;
        Modifier modifier2;
        int $dirty1;
        int maxLines2;
        ImeOptions imeOptions3;
        Object value$iv;
        Object value$iv2;
        WindowInfo windowInfo;
        Object value$iv3;
        boolean z;
        boolean z2;
        boolean invalid$iv;
        TransformedText transformed;
        TextRange composition;
        ImeOptions imeOptions4;
        MutableInteractionSource interactionSource3;
        TransformedText transformedTextM1419applyCompositionDecoration72CqOWE;
        Object value$iv4;
        boolean invalid$iv2;
        Object value$iv5;
        final LegacyTextFieldState state;
        Object it$iv;
        Object value$iv6;
        UndoManager undoManager;
        Object it$iv2;
        Object value$iv7;
        final TextFieldSelectionManager manager;
        Object value$iv$iv;
        Object it$iv3;
        Object value$iv8;
        boolean z3;
        boolean z4;
        boolean z5;
        final ImeOptions imeOptions5;
        boolean z6;
        boolean invalid$iv3;
        Object value$iv9;
        boolean z7;
        boolean invalid$iv4;
        Object value$iv10;
        boolean invalid$iv5;
        Object value$iv11;
        boolean z8;
        boolean z9;
        boolean invalid$iv6;
        Object value$iv12;
        boolean z10;
        boolean invalid$iv7;
        Object value$iv13;
        boolean z11;
        final WindowInfo windowInfo2;
        boolean z12;
        boolean invalid$iv8;
        Object value$iv14;
        boolean z13;
        boolean z14;
        boolean z15;
        boolean invalid$iv9;
        Object value$iv15;
        boolean z16;
        boolean invalid$iv10;
        Object value$iv16;
        boolean z17;
        boolean invalid$iv11;
        Object value$iv17;
        boolean z18;
        boolean showHandleAndMagnifier;
        final ImeOptions imeOptions6;
        boolean enabled3;
        boolean invalid$iv12;
        Object value$iv18;
        Modifier.Companion companionTextFieldMagnifier;
        final int maxLines3;
        boolean enabled4;
        ImeOptions imeOptions7;
        MutableInteractionSource interactionSource4;
        VisualTransformation visualTransformation3;
        Modifier modifier3;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-958708118);
        ComposerKt.sourceInformation($composer3, "C(CoreTextField)P(14,10,8,13,15,9,4!1,12,6,7,3,5,2,11)221@12329L29,222@12399L58,223@12503L72,228@12633L7,229@12694L7,230@12762L7,231@12827L7,232@12872L7,233@12941L7,241@13250L42,238@13157L135,244@13333L269,257@13907L21,258@13945L403,287@14747L26,290@14833L51,296@15136L7,297@15187L7,298@15244L7,303@15385L24,304@15443L37,311@15672L1487,352@17266L42,353@17334L970,353@17313L991,379@18375L28,380@18467L907,408@19636L516,425@20215L1473,462@21825L6646,616@28696L60,616@28670L86,620@28791L458,620@28762L487,647@29739L1132,694@31923L4765,694@31870L4818:CoreTextField.kt#423gt5");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(textStyle) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 131072 : 65536;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(interactionSource) ? 1048576 : 524288;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(cursorBrush) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(softWrap) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changed(maxLines) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty2 |= $composer3.changed(minLines) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty2 |= ((i & 2048) == 0 && $composer3.changed(imeOptions)) ? 32 : 16;
        }
        int i11 = i & 4096;
        if (i11 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty2 |= $composer3.changed(keyboardActions) ? 256 : 128;
        }
        int i12 = i & 8192;
        if (i12 != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        int i13 = i & 16384;
        if (i13 != 0) {
            $dirty2 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty2 |= $composer3.changed(readOnly) ? 16384 : 8192;
        }
        int i14 = i & 32768;
        if (i14 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        if (($dirty & 306783379) == 306783378 && (74899 & $dirty2) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            textStyle2 = textStyle;
            visualTransformation3 = visualTransformation;
            function4 = function2;
            interactionSource4 = interactionSource;
            cursorBrush2 = cursorBrush;
            softWrap2 = softWrap;
            maxLines3 = maxLines;
            minLines2 = minLines;
            imeOptions7 = imeOptions;
            keyboardActions2 = keyboardActions;
            enabled4 = enabled;
            readOnly2 = readOnly;
            function3M1344getLambda1$foundation_release = function3;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                TextStyle textStyle3 = i3 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle;
                VisualTransformation visualTransformation4 = i4 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                AnonymousClass1 anonymousClass1 = i5 != 0 ? new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult it) {
                    }
                } : function2;
                MutableInteractionSource interactionSource5 = i6 != 0 ? null : interactionSource;
                SolidColor cursorBrush3 = i7 != 0 ? new SolidColor(Color.INSTANCE.m4570getUnspecified0d7_KjU(), null) : cursorBrush;
                boolean softWrap3 = i8 != 0 ? true : softWrap;
                int maxLines4 = i9 != 0 ? Integer.MAX_VALUE : maxLines;
                int minLines3 = i10 != 0 ? 1 : minLines;
                if ((i & 2048) != 0) {
                    imeOptions2 = ImeOptions.INSTANCE.getDefault();
                    $dirty2 &= -113;
                } else {
                    imeOptions2 = imeOptions;
                }
                KeyboardActions keyboardActions3 = i11 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                boolean enabled5 = i12 != 0 ? true : enabled;
                boolean readOnly3 = i13 != 0 ? false : readOnly;
                if (i14 != 0) {
                    visualTransformation2 = visualTransformation4;
                    cursorBrush2 = cursorBrush3;
                    softWrap2 = softWrap3;
                    minLines2 = minLines3;
                    keyboardActions2 = keyboardActions3;
                    textStyle2 = textStyle3;
                    enabled2 = enabled5;
                    readOnly2 = readOnly3;
                    function3M1344getLambda1$foundation_release = ComposableSingletons$CoreTextFieldKt.INSTANCE.m1344getLambda1$foundation_release();
                    function4 = anonymousClass1;
                    interactionSource2 = interactionSource5;
                    modifier2 = modifier4;
                    $dirty1 = $dirty2;
                    maxLines2 = maxLines4;
                    imeOptions3 = imeOptions2;
                } else {
                    visualTransformation2 = visualTransformation4;
                    function3M1344getLambda1$foundation_release = function3;
                    cursorBrush2 = cursorBrush3;
                    softWrap2 = softWrap3;
                    minLines2 = minLines3;
                    keyboardActions2 = keyboardActions3;
                    textStyle2 = textStyle3;
                    enabled2 = enabled5;
                    readOnly2 = readOnly3;
                    function4 = anonymousClass1;
                    interactionSource2 = interactionSource5;
                    modifier2 = modifier4;
                    $dirty1 = $dirty2;
                    maxLines2 = maxLines4;
                    imeOptions3 = imeOptions2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2048) != 0) {
                    $dirty2 &= -113;
                }
                modifier2 = modifier;
                textStyle2 = textStyle;
                visualTransformation2 = visualTransformation;
                function4 = function2;
                interactionSource2 = interactionSource;
                cursorBrush2 = cursorBrush;
                softWrap2 = softWrap;
                maxLines2 = maxLines;
                minLines2 = minLines;
                imeOptions3 = imeOptions;
                keyboardActions2 = keyboardActions;
                enabled2 = enabled;
                readOnly2 = readOnly;
                function3M1344getLambda1$foundation_release = function3;
                $dirty1 = $dirty2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-958708118, $dirty, $dirty1, "androidx.compose.foundation.text.CoreTextField (CoreTextField.kt:220)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1705356604, "CC(remember):CoreTextField.kt#9igjgp");
            Object it$iv4 = $composer3.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv = new FocusRequester();
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv4;
            }
            final FocusRequester focusRequester = (FocusRequester) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1705354335, "CC(remember):CoreTextField.kt#9igjgp");
            Object it$iv5 = $composer3.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = LegacyPlatformTextInputServiceAdapter_androidKt.createLegacyPlatformTextInputServiceAdapter();
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv5;
            }
            final LegacyPlatformTextInputServiceAdapter legacyTextInputServiceAdapter = (LegacyPlatformTextInputServiceAdapter) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1705350993, "CC(remember):CoreTextField.kt#9igjgp");
            Object value$iv19 = $composer3.rememberedValue();
            if (value$iv19 == Composer.INSTANCE.getEmpty()) {
                value$iv19 = new TextInputService(legacyTextInputServiceAdapter);
                $composer3.updateRememberedValue(value$iv19);
            }
            final TextInputService textInputService = (TextInputService) value$iv19;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) objConsume;
            ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localFontFamilyResolver);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FontFamily.Resolver fontFamilyResolver = (FontFamily.Resolver) objConsume2;
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long selectionBackgroundColor = ((SelectionColors) objConsume3).getSelectionBackgroundColor();
            ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer3.consume(localFocusManager);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FocusManager focusManager = (FocusManager) objConsume4;
            ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer3.consume(localWindowInfo);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            WindowInfo windowInfo3 = (WindowInfo) objConsume5;
            ProvidableCompositionLocal<SoftwareKeyboardController> localSoftwareKeyboardController = CompositionLocalsKt.getLocalSoftwareKeyboardController();
            Modifier modifier5 = modifier2;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localSoftwareKeyboardController);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SoftwareKeyboardController keyboardController = (SoftwareKeyboardController) objConsume6;
            boolean singleLine = maxLines2 == 1 && !softWrap2 && imeOptions3.getSingleLine();
            final Orientation orientation = singleLine ? Orientation.Horizontal : Orientation.Vertical;
            Object[] objArr = {orientation};
            Saver<TextFieldScrollerPosition, Object> saver = TextFieldScrollerPosition.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart($composer3, -1705327119, "CC(remember):CoreTextField.kt#9igjgp");
            boolean invalid$iv13 = $composer3.changed(orientation);
            int maxLines5 = maxLines2;
            Object it$iv6 = $composer3.rememberedValue();
            if (invalid$iv13) {
                windowInfo = windowInfo3;
            } else {
                windowInfo = windowInfo3;
                if (it$iv6 != Composer.INSTANCE.getEmpty()) {
                    value$iv3 = it$iv6;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final TextFieldScrollerPosition scrollerPosition = (TextFieldScrollerPosition) RememberSaveableKt.m4120rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv3, $composer3, 0, 4);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705324236, "CC(remember):CoreTextField.kt#9igjgp");
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                if (($dirty & 57344) == 16384) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                invalid$iv = z | z2;
                Object it$iv7 = $composer3.rememberedValue();
                if (!invalid$iv || it$iv7 == Composer.INSTANCE.getEmpty()) {
                    transformed = ValidatingOffsetMappingKt.filterWithValidation(visualTransformation2, value.getText());
                    composition = value.getComposition();
                    if (composition != null) {
                        interactionSource3 = interactionSource2;
                        long it = composition.getPackedValue();
                        imeOptions4 = imeOptions3;
                        transformedTextM1419applyCompositionDecoration72CqOWE = TextFieldDelegate.INSTANCE.m1419applyCompositionDecoration72CqOWE(it, transformed);
                        if (transformedTextM1419applyCompositionDecoration72CqOWE == null) {
                        }
                        value$iv4 = transformedTextM1419applyCompositionDecoration72CqOWE;
                        $composer3.updateRememberedValue(value$iv4);
                    } else {
                        imeOptions4 = imeOptions3;
                        interactionSource3 = interactionSource2;
                    }
                    transformedTextM1419applyCompositionDecoration72CqOWE = transformed;
                    value$iv4 = transformedTextM1419applyCompositionDecoration72CqOWE;
                    $composer3.updateRememberedValue(value$iv4);
                } else {
                    imeOptions4 = imeOptions3;
                    value$iv4 = it$iv7;
                    interactionSource3 = interactionSource2;
                }
                final TransformedText transformedText = (TransformedText) value$iv4;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                AnnotatedString visualText = transformedText.getText();
                final OffsetMapping offsetMapping = transformedText.getOffsetMapping();
                RecomposeScope scope = ComposablesKt.getCurrentRecomposeScope($composer3, 0);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705304518, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv2 = $composer3.changed(keyboardController);
                Object it$iv8 = $composer3.rememberedValue();
                if (!invalid$iv2 || it$iv8 == Composer.INSTANCE.getEmpty()) {
                    value$iv5 = new LegacyTextFieldState(new TextDelegate(visualText, textStyle2, 0, 0, softWrap2, 0, density, fontFamilyResolver, null, AnimationConstants.DefaultDurationMillis, null), scope, keyboardController);
                    $composer3.updateRememberedValue(value$iv5);
                } else {
                    value$iv5 = it$iv8;
                }
                state = (LegacyTextFieldState) value$iv5;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                state.m1375updatefnh65Uc(value.getText(), visualText, textStyle2, softWrap2, density, fontFamilyResolver, function1, keyboardActions2, focusManager, selectionBackgroundColor);
                state.getProcessor().reset(value, state.getInputSession());
                ComposerKt.sourceInformationMarkerStart($composer3, -1705279231, "CC(remember):CoreTextField.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv6 = new UndoManager(0, 1, null);
                    $composer3.updateRememberedValue(value$iv6);
                } else {
                    value$iv6 = it$iv;
                }
                undoManager = (UndoManager) value$iv6;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                UndoManager.snapshotIfNeeded$default(undoManager, value, 0L, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705276454, "CC(remember):CoreTextField.kt#9igjgp");
                it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv7 = new TextFieldSelectionManager(undoManager);
                    $composer3.updateRememberedValue(value$iv7);
                } else {
                    value$iv7 = it$iv2;
                }
                manager = (TextFieldSelectionManager) value$iv7;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                manager.setOffsetMapping$foundation_release(offsetMapping);
                manager.setVisualTransformation$foundation_release(visualTransformation2);
                manager.setOnValueChange$foundation_release(state.getOnValueChange());
                manager.setState$foundation_release(state);
                manager.setValue$foundation_release(value);
                ProvidableCompositionLocal<ClipboardManager> localClipboardManager = CompositionLocalsKt.getLocalClipboardManager();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume7 = $composer3.consume(localClipboardManager);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                manager.setClipboardManager$foundation_release((ClipboardManager) objConsume7);
                ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume8 = $composer3.consume(localTextToolbar);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                manager.setTextToolbar((TextToolbar) objConsume8);
                ProvidableCompositionLocal<HapticFeedback> localHapticFeedback = CompositionLocalsKt.getLocalHapticFeedback();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume9 = $composer3.consume(localHapticFeedback);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                manager.setHapticFeedBack((HapticFeedback) objConsume9);
                manager.setFocusRequester(focusRequester);
                manager.setEditable(!readOnly2);
                manager.setEnabled(enabled2);
                ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
                value$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                    $composer3.updateRememberedValue(value$iv$iv);
                }
                CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705256948, "CC(remember):CoreTextField.kt#9igjgp");
                it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv8 = BringIntoViewRequesterKt.BringIntoViewRequester();
                    $composer3.updateRememberedValue(value$iv8);
                } else {
                    value$iv8 = it$iv3;
                }
                final BringIntoViewRequester bringIntoViewRequester = (BringIntoViewRequester) value$iv8;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705248170, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance = $composer3.changedInstance(state);
                final VisualTransformation visualTransformation5 = visualTransformation2;
                if (($dirty1 & 7168) == 2048) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z19 = zChangedInstance | z3;
                if (($dirty1 & 57344) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance2 = z19 | z4 | $composer3.changedInstance(textInputService);
                if (($dirty & 14) == 4) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean z20 = zChangedInstance2 | z5;
                if ((($dirty1 & 112) ^ 48) > 32) {
                    imeOptions5 = imeOptions4;
                    if ($composer3.changed(imeOptions5)) {
                        z6 = true;
                    }
                    invalid$iv3 = z20 | z6 | $composer3.changedInstance(offsetMapping) | $composer3.changedInstance(coroutineScope) | $composer3.changedInstance(bringIntoViewRequester) | $composer3.changedInstance(manager);
                    Object it$iv9 = $composer3.rememberedValue();
                    if (!invalid$iv3 || it$iv9 == Composer.INSTANCE.getEmpty()) {
                        final boolean z21 = enabled2;
                        final boolean z22 = readOnly2;
                        final ImeOptions imeOptions8 = imeOptions5;
                        value$iv9 = new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$focusModifier$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState it2) {
                                TextLayoutResultProxy layoutResult;
                                if (state.getHasFocus() == it2.isFocused()) {
                                    return;
                                }
                                state.setHasFocus(it2.isFocused());
                                if (!state.getHasFocus() || !z21 || z22) {
                                    CoreTextFieldKt.endInputSession(state);
                                } else {
                                    CoreTextFieldKt.startInputSession(textInputService, state, value, imeOptions8, offsetMapping);
                                }
                                if (it2.isFocused() && (layoutResult = state.getLayoutResult()) != null) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1$1(bringIntoViewRequester, value, state, layoutResult, offsetMapping, null), 3, null);
                                }
                                if (!it2.isFocused()) {
                                    TextFieldSelectionManager.m1748deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                                }
                            }
                        };
                        $composer3.updateRememberedValue(value$iv9);
                    } else {
                        value$iv9 = it$iv9;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    MutableInteractionSource interactionSource6 = interactionSource3;
                    Modifier focusModifier = TextFieldGestureModifiersKt.textFieldFocusModifier(companion, enabled2, focusRequester, interactionSource6, (Function1) value$iv9);
                    if (enabled2 || readOnly2) {
                        z7 = false;
                    } else {
                        z7 = true;
                    }
                    State writeable$delegate = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(z7), $composer3, 0);
                    Unit unit = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705195503, "CC(remember):CoreTextField.kt#9igjgp");
                    invalid$iv4 = $composer3.changed(writeable$delegate) | $composer3.changedInstance(state) | $composer3.changedInstance(textInputService) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                    Object it$iv10 = $composer3.rememberedValue();
                    if (!invalid$iv4 || it$iv10 == Composer.INSTANCE.getEmpty()) {
                        value$iv10 = new CoreTextFieldKt$CoreTextField$2$1(state, writeable$delegate, textInputService, manager, imeOptions5, null);
                        $composer3.updateRememberedValue(value$iv10);
                    } else {
                        value$iv10 = it$iv10;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv10, $composer3, 6);
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705163133, "CC(remember):CoreTextField.kt#9igjgp");
                    invalid$iv5 = $composer3.changedInstance(state);
                    value$iv11 = $composer3.rememberedValue();
                    if (!invalid$iv5 || value$iv11 == Composer.INSTANCE.getEmpty()) {
                        value$iv11 = (Function1) new Function1<Boolean, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$1$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean it2) {
                                state.setInTouchMode(it2);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv11);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierUpdateSelectionTouchMode = SelectionGesturesKt.updateSelectionTouchMode(companion2, (Function1) value$iv11);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705159310, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance3 = $composer3.changedInstance(state);
                    if (($dirty1 & 57344) == 16384) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean z23 = zChangedInstance3 | z8;
                    if (($dirty1 & 7168) == 2048) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    invalid$iv6 = z23 | z9 | $composer3.changedInstance(offsetMapping) | $composer3.changedInstance(manager);
                    Object it$iv11 = $composer3.rememberedValue();
                    if (!invalid$iv6 || it$iv11 == Composer.INSTANCE.getEmpty()) {
                        final boolean z24 = readOnly2;
                        final boolean z25 = enabled2;
                        value$iv12 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                                m1345invokek4lQ0M(offset.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                            public final void m1345invokek4lQ0M(long offset) {
                                CoreTextFieldKt.tapToFocus(state, focusRequester, !z24);
                                if (state.getHasFocus() && z25) {
                                    if (state.getHandleState() != HandleState.Selection) {
                                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                                        if (layoutResult != null) {
                                            LegacyTextFieldState legacyTextFieldState = state;
                                            TextFieldDelegate.INSTANCE.m1422setCursorOffsetULxng0E$foundation_release(offset, layoutResult, legacyTextFieldState.getProcessor(), offsetMapping, legacyTextFieldState.getOnValueChange());
                                            if (legacyTextFieldState.getTextDelegate().getText().length() > 0) {
                                                legacyTextFieldState.setHandleState(HandleState.Cursor);
                                                return;
                                            }
                                            return;
                                        }
                                        return;
                                    }
                                    manager.m1752deselect_kEHs6E$foundation_release(Offset.m4282boximpl(offset));
                                }
                            }
                        };
                        $composer3.updateRememberedValue(value$iv12);
                    } else {
                        value$iv12 = it$iv11;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(modifierUpdateSelectionTouchMode, interactionSource6, enabled2, (Function1) value$iv12), manager.getMouseSelectionObserver(), manager.getTouchSelectionObserver()), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
                    Modifier.Companion companion3 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705122293, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance4 = $composer3.changedInstance(state);
                    if (($dirty & 14) == 4) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    invalid$iv7 = zChangedInstance4 | z10 | $composer3.changedInstance(offsetMapping);
                    Object it$iv12 = $composer3.rememberedValue();
                    if (!invalid$iv7 || it$iv12 == Composer.INSTANCE.getEmpty()) {
                        value$iv13 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$drawModifier$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                                invoke2(drawScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DrawScope $this$drawBehind) {
                                TextLayoutResultProxy layoutResult = state.getLayoutResult();
                                if (layoutResult != null) {
                                    TextFieldValue textFieldValue = value;
                                    LegacyTextFieldState legacyTextFieldState = state;
                                    OffsetMapping offsetMapping2 = offsetMapping;
                                    Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
                                    TextFieldDelegate.INSTANCE.m1420drawQ1vqE60$foundation_release(canvas, textFieldValue, legacyTextFieldState.m1370getSelectionPreviewHighlightRanged9O1mEE(), legacyTextFieldState.m1367getDeletionPreviewHighlightRanged9O1mEE(), offsetMapping2, layoutResult.getValue(), legacyTextFieldState.getHighlightPaint(), legacyTextFieldState.getSelectionBackgroundColor());
                                }
                            }
                        };
                        $composer3.updateRememberedValue(value$iv13);
                    } else {
                        value$iv13 = it$iv12;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final Modifier drawModifier = DrawModifierKt.drawBehind(companion3, (Function1) value$iv13);
                    Modifier.Companion companion4 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705102808, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance5 = $composer3.changedInstance(state);
                    if (($dirty1 & 7168) == 2048) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    windowInfo2 = windowInfo;
                    boolean zChanged = zChangedInstance5 | z11 | $composer3.changed(windowInfo2) | $composer3.changedInstance(manager);
                    if (($dirty & 14) == 4) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                    invalid$iv8 = zChanged | z12 | $composer3.changedInstance(offsetMapping);
                    Object it$iv13 = $composer3.rememberedValue();
                    if (!invalid$iv8 || it$iv13 == Composer.INSTANCE.getEmpty()) {
                        final boolean z26 = enabled2;
                        value$iv14 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$onPositionedModifier$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                                invoke2(layoutCoordinates);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(LayoutCoordinates it2) {
                                state.setLayoutCoordinates(it2);
                                TextLayoutResultProxy layoutResult = state.getLayoutResult();
                                if (layoutResult != null) {
                                    layoutResult.setInnerTextFieldCoordinates(it2);
                                }
                                if (z26) {
                                    if (state.getHandleState() == HandleState.Selection) {
                                        if (state.getShowFloatingToolbar() && windowInfo2.isWindowFocused()) {
                                            manager.showSelectionToolbar$foundation_release();
                                        } else {
                                            manager.hideSelectionToolbar$foundation_release();
                                        }
                                        state.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                                        state.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, false));
                                        state.setShowCursorHandle(TextRange.m6473getCollapsedimpl(value.getSelection()));
                                    } else if (state.getHandleState() == HandleState.Cursor) {
                                        state.setShowCursorHandle(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                                    }
                                    CoreTextFieldKt.notifyFocusedRect(state, value, offsetMapping);
                                    TextLayoutResultProxy layoutResult2 = state.getLayoutResult();
                                    if (layoutResult2 != null) {
                                        LegacyTextFieldState legacyTextFieldState = state;
                                        TextFieldValue textFieldValue = value;
                                        OffsetMapping offsetMapping2 = offsetMapping;
                                        TextInputSession inputSession = legacyTextFieldState.getInputSession();
                                        if (inputSession != null && legacyTextFieldState.getHasFocus()) {
                                            TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation_release(inputSession, textFieldValue, offsetMapping2, layoutResult2);
                                        }
                                    }
                                }
                            }
                        };
                        $composer3.updateRememberedValue(value$iv14);
                    } else {
                        value$iv14 = it$iv13;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final Modifier onPositionedModifier = OnGloballyPositionedModifierKt.onGloballyPositioned(companion4, (Function1) value$iv14);
                    final boolean isPassword = visualTransformation5 instanceof PasswordVisualTransformation;
                    Modifier.Companion companion5 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705046115, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance6 = $composer3.changedInstance(transformedText);
                    if (($dirty & 14) == 4) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    boolean z27 = zChangedInstance6 | z13;
                    if (($dirty1 & 7168) == 2048) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean zChanged2 = z14 | z27 | $composer3.changed(isPassword);
                    if ((57344 & $dirty1) == 16384) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    invalid$iv9 = zChanged2 | z15 | $composer3.changedInstance(state) | $composer3.changedInstance(offsetMapping) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                    Object it$iv14 = $composer3.rememberedValue();
                    if (!invalid$iv9 || it$iv14 == Composer.INSTANCE.getEmpty()) {
                        final boolean z28 = enabled2;
                        final boolean z29 = readOnly2;
                        final ImeOptions imeOptions9 = imeOptions5;
                        value$iv15 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(final SemanticsPropertyReceiver $this$semantics) {
                                SemanticsPropertiesKt.setEditableText($this$semantics, transformedText.getText());
                                SemanticsPropertiesKt.m6309setTextSelectionRangeFDrldGo($this$semantics, value.getSelection());
                                if (!z28) {
                                    SemanticsPropertiesKt.disabled($this$semantics);
                                }
                                if (isPassword) {
                                    SemanticsPropertiesKt.password($this$semantics);
                                }
                                boolean editable = z28 && !z29;
                                SemanticsPropertiesKt.setEditable($this$semantics, editable);
                                final LegacyTextFieldState legacyTextFieldState = state;
                                SemanticsPropertiesKt.getTextLayoutResult$default($this$semantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(List<TextLayoutResult> list) {
                                        boolean z30;
                                        if (legacyTextFieldState.getLayoutResult() != null) {
                                            TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                                            Intrinsics.checkNotNull(layoutResult);
                                            list.add(layoutResult.getValue());
                                            z30 = true;
                                        } else {
                                            z30 = false;
                                        }
                                        return Boolean.valueOf(z30);
                                    }
                                }, 1, null);
                                if (editable) {
                                    final LegacyTextFieldState legacyTextFieldState2 = state;
                                    SemanticsPropertiesKt.setText$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.2
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public final Boolean invoke(AnnotatedString text) {
                                            Unit unit2;
                                            TextInputSession session = legacyTextFieldState2.getInputSession();
                                            if (session != null) {
                                                LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState2;
                                                TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new DeleteAllCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState3.getProcessor(), legacyTextFieldState3.getOnValueChange(), session);
                                                unit2 = Unit.INSTANCE;
                                            } else {
                                                unit2 = null;
                                            }
                                            if (unit2 == null) {
                                                SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                                legacyTextFieldState2.getOnValueChange().invoke(new TextFieldValue(text.getText(), TextRangeKt.TextRange(text.getText().length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                            }
                                            return true;
                                        }
                                    }, 1, null);
                                    final boolean z30 = z29;
                                    final boolean z31 = z28;
                                    final LegacyTextFieldState legacyTextFieldState3 = state;
                                    final TextFieldValue textFieldValue = value;
                                    SemanticsPropertiesKt.insertTextAtCursor$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.3
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public final Boolean invoke(AnnotatedString text) {
                                            Unit unit2;
                                            if (z30 || !z31) {
                                                return false;
                                            }
                                            TextInputSession session = legacyTextFieldState3.getInputSession();
                                            if (session != null) {
                                                LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                                TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new FinishComposingTextCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState4.getProcessor(), legacyTextFieldState4.getOnValueChange(), session);
                                                unit2 = Unit.INSTANCE;
                                            } else {
                                                unit2 = null;
                                            }
                                            if (unit2 == null) {
                                                SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                                TextFieldValue textFieldValue2 = textFieldValue;
                                                LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState3;
                                                String newText = StringsKt.replaceRange((CharSequence) textFieldValue2.getText(), TextRange.m6479getStartimpl(textFieldValue2.getSelection()), TextRange.m6474getEndimpl(textFieldValue2.getSelection()), (CharSequence) text).toString();
                                                long newCursor = TextRangeKt.TextRange(TextRange.m6479getStartimpl(textFieldValue2.getSelection()) + text.length());
                                                legacyTextFieldState5.getOnValueChange().invoke(new TextFieldValue(newText, newCursor, (TextRange) null, 4, (DefaultConstructorMarker) null));
                                            }
                                            return true;
                                        }
                                    }, 1, null);
                                }
                                final OffsetMapping offsetMapping2 = offsetMapping;
                                final boolean z32 = z28;
                                final TextFieldValue textFieldValue2 = value;
                                final TextFieldSelectionManager textFieldSelectionManager = manager;
                                final LegacyTextFieldState legacyTextFieldState4 = state;
                                SemanticsPropertiesKt.setSelection$default($this$semantics, null, new Function3<Integer, Integer, Boolean, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.4
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Integer num2, Boolean bool) {
                                        return invoke(num.intValue(), num2.intValue(), bool.booleanValue());
                                    }

                                    public final Boolean invoke(int selectionStart, int selectionEnd, boolean relativeToOriginalText) {
                                        int start;
                                        int end;
                                        if (relativeToOriginalText) {
                                            start = selectionStart;
                                        } else {
                                            start = offsetMapping2.transformedToOriginal(selectionStart);
                                        }
                                        if (relativeToOriginalText) {
                                            end = selectionEnd;
                                        } else {
                                            end = offsetMapping2.transformedToOriginal(selectionEnd);
                                        }
                                        boolean z33 = false;
                                        if (z32 && (start != TextRange.m6479getStartimpl(textFieldValue2.getSelection()) || end != TextRange.m6474getEndimpl(textFieldValue2.getSelection()))) {
                                            if (Math.min(start, end) >= 0 && Math.max(start, end) <= textFieldValue2.getText().length()) {
                                                if (!relativeToOriginalText && start != end) {
                                                    TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager, false, 1, null);
                                                } else {
                                                    textFieldSelectionManager.exitSelectionMode$foundation_release();
                                                }
                                                legacyTextFieldState4.getOnValueChange().invoke(new TextFieldValue(textFieldValue2.getText(), TextRangeKt.TextRange(start, end), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                                z33 = true;
                                            } else {
                                                textFieldSelectionManager.exitSelectionMode$foundation_release();
                                            }
                                        }
                                        return Boolean.valueOf(z33);
                                    }
                                }, 1, null);
                                int imeAction = imeOptions9.getImeAction();
                                final LegacyTextFieldState legacyTextFieldState5 = state;
                                final ImeOptions imeOptions10 = imeOptions9;
                                SemanticsPropertiesKt.m6304onImeAction9UiTYpY$default($this$semantics, imeAction, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.5
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        legacyTextFieldState5.getOnImeActionPerformed().invoke(ImeAction.m6641boximpl(imeOptions10.getImeAction()));
                                        return true;
                                    }
                                }, 2, null);
                                final LegacyTextFieldState legacyTextFieldState6 = state;
                                final FocusRequester focusRequester2 = focusRequester;
                                final boolean z33 = z29;
                                SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.6
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        CoreTextFieldKt.tapToFocus(legacyTextFieldState6, focusRequester2, !z33);
                                        return true;
                                    }
                                }, 1, null);
                                final TextFieldSelectionManager textFieldSelectionManager2 = manager;
                                SemanticsPropertiesKt.onLongClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.7
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager2, false, 1, null);
                                        return true;
                                    }
                                }, 1, null);
                                if (!TextRange.m6473getCollapsedimpl(value.getSelection()) && !isPassword) {
                                    final TextFieldSelectionManager textFieldSelectionManager3 = manager;
                                    SemanticsPropertiesKt.copyText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.8
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            TextFieldSelectionManager.copy$foundation_release$default(textFieldSelectionManager3, false, 1, null);
                                            return true;
                                        }
                                    }, 1, null);
                                    if (z28 && !z29) {
                                        final TextFieldSelectionManager textFieldSelectionManager4 = manager;
                                        SemanticsPropertiesKt.cutText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.9
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                textFieldSelectionManager4.cut$foundation_release();
                                                return true;
                                            }
                                        }, 1, null);
                                    }
                                }
                                if (z28 && !z29) {
                                    final TextFieldSelectionManager textFieldSelectionManager5 = manager;
                                    SemanticsPropertiesKt.pasteText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.10
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            textFieldSelectionManager5.paste$foundation_release();
                                            return true;
                                        }
                                    }, 1, null);
                                }
                            }
                        };
                        $composer3.updateRememberedValue(value$iv15);
                    } else {
                        value$iv15 = it$iv14;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier semanticsModifier = SemanticsModifierKt.semantics(companion5, true, (Function1) value$iv15);
                    if (enabled2 || readOnly2 || !windowInfo2.isWindowFocused() || state.hasHighlight()) {
                        z16 = false;
                    } else {
                        z16 = true;
                    }
                    boolean showCursor = z16;
                    final Modifier cursorModifier = TextFieldCursorKt.cursor(Modifier.INSTANCE, state, value, offsetMapping, cursorBrush2, showCursor);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1704832829, "CC(remember):CoreTextField.kt#9igjgp");
                    invalid$iv10 = $composer3.changedInstance(manager);
                    Object it$iv15 = $composer3.rememberedValue();
                    if (!invalid$iv10 || it$iv15 == Composer.INSTANCE.getEmpty()) {
                        value$iv16 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                                final TextFieldSelectionManager textFieldSelectionManager = manager;
                                return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public void dispose() {
                                        textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                                    }
                                };
                            }
                        };
                        $composer3.updateRememberedValue(value$iv16);
                    } else {
                        value$iv16 = it$iv15;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    EffectsKt.DisposableEffect(manager, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv16, $composer3, 0);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1704829391, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance7 = $composer3.changedInstance(state) | $composer3.changedInstance(textInputService);
                    if (($dirty & 14) == 4) {
                        z17 = true;
                    } else {
                        z17 = false;
                    }
                    invalid$iv11 = zChangedInstance7 | z17 | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                    Object it$iv16 = $composer3.rememberedValue();
                    if (!invalid$iv11 || it$iv16 == Composer.INSTANCE.getEmpty()) {
                        value$iv17 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                                if (state.getHasFocus()) {
                                    state.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation_release(textInputService, value, state.getProcessor(), imeOptions5, state.getOnValueChange(), state.getOnImeActionPerformed()));
                                }
                                return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public void dispose() {
                                    }
                                };
                            }
                        };
                        $composer3.updateRememberedValue(value$iv17);
                    } else {
                        value$iv17 = it$iv16;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    EffectsKt.DisposableEffect(imeOptions5, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv17, $composer3, ($dirty1 >> 3) & 14);
                    Modifier.Companion companion6 = Modifier.INSTANCE;
                    Function1<TextFieldValue, Unit> onValueChange = state.getOnValueChange();
                    boolean z30 = !readOnly2;
                    if (maxLines5 == 1) {
                        z18 = true;
                    } else {
                        z18 = false;
                    }
                    showHandleAndMagnifier = false;
                    imeOptions6 = imeOptions5;
                    boolean z31 = z18;
                    int $dirty3 = $dirty1;
                    enabled3 = enabled2;
                    Modifier textKeyInputModifier = TextFieldKeyInputKt.m1429textFieldKeyInput2WJ9YEU(companion6, state, manager, value, onValueChange, z30, z31, offsetMapping, undoManager, imeOptions5.getImeAction());
                    Modifier.Companion companion7 = Modifier.INSTANCE;
                    boolean zCoreTextField$lambda$11 = CoreTextField$lambda$11(writeable$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1704798381, "CC(remember):CoreTextField.kt#9igjgp");
                    invalid$iv12 = $composer3.changedInstance(state) | (((($dirty3 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions6)) || ($dirty3 & 48) == 32) | $composer3.changedInstance(legacyTextInputServiceAdapter);
                    Object it$iv17 = $composer3.rememberedValue();
                    if (!invalid$iv12 || it$iv17 == Composer.INSTANCE.getEmpty()) {
                        value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                if (!state.getHasFocus()) {
                                    focusRequester.requestFocus();
                                }
                                if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                                    legacyTextInputServiceAdapter.startStylusHandwriting();
                                }
                                return true;
                            }
                        };
                        $composer3.updateRememberedValue(value$iv18);
                    } else {
                        value$iv18 = it$iv17;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier stylusHandwritingModifier = StylusHandwritingKt.stylusHandwriting(companion7, zCoreTextField$lambda$11, (Function0) value$iv18);
                    Modifier decorationBoxModifier = OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier5, legacyTextInputServiceAdapter, state, manager).then(stylusHandwritingModifier).then(focusModifier), state, focusManager), state, manager).then(textKeyInputModifier), scrollerPosition, interactionSource6, enabled3).then(r15).then(semanticsModifier), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                            invoke2(layoutCoordinates);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(LayoutCoordinates it2) {
                            TextLayoutResultProxy layoutResult = state.getLayoutResult();
                            if (layoutResult == null) {
                                return;
                            }
                            layoutResult.setDecorationBoxCoordinates(it2);
                        }
                    });
                    if (enabled3 && state.getHasFocus() && state.isInTouchMode() && windowInfo2.isWindowFocused()) {
                        showHandleAndMagnifier = true;
                    }
                    if (showHandleAndMagnifier) {
                        companionTextFieldMagnifier = TextFieldSelectionManager_androidKt.textFieldMagnifier(Modifier.INSTANCE, manager);
                    } else {
                        companionTextFieldMagnifier = Modifier.INSTANCE;
                    }
                    maxLines3 = maxLines5;
                    final Modifier magnifierModifier = companionTextFieldMagnifier;
                    final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function5 = function3M1344getLambda1$foundation_release;
                    final TextStyle textStyle4 = textStyle2;
                    final int i15 = minLines2;
                    enabled4 = enabled3;
                    imeOptions7 = imeOptions6;
                    interactionSource4 = interactionSource6;
                    visualTransformation3 = visualTransformation5;
                    modifier3 = modifier5;
                    final boolean z32 = showHandleAndMagnifier;
                    final boolean z33 = readOnly2;
                    final Function1<? super TextLayoutResult, Unit> function6 = function4;
                    Function2<Composer, Integer, Unit> function7 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5
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

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C695@31947L4735,695@31933L4749:CoreTextField.kt#423gt5");
                            if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-374338080, $changed2, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:695)");
                                }
                                Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> function8 = function5;
                                final LegacyTextFieldState legacyTextFieldState = state;
                                final TextStyle textStyle5 = textStyle4;
                                final int i16 = i15;
                                final int i17 = maxLines3;
                                final TextFieldScrollerPosition textFieldScrollerPosition = scrollerPosition;
                                final TextFieldValue textFieldValue = value;
                                final VisualTransformation visualTransformation6 = visualTransformation5;
                                final Modifier modifier6 = cursorModifier;
                                final Modifier modifier7 = drawModifier;
                                final Modifier modifier8 = onPositionedModifier;
                                final Modifier modifier9 = magnifierModifier;
                                final BringIntoViewRequester bringIntoViewRequester2 = bringIntoViewRequester;
                                final TextFieldSelectionManager textFieldSelectionManager = manager;
                                final boolean z34 = z32;
                                final boolean z35 = z33;
                                final Function1<TextLayoutResult, Unit> function9 = function6;
                                final OffsetMapping offsetMapping2 = offsetMapping;
                                final Density density2 = density;
                                function8.invoke(ComposableLambdaKt.rememberComposableLambda(2032502107, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1
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

                                    public final void invoke(Composer $composer5, int $changed3) {
                                        Object value$iv20;
                                        ComposerKt.sourceInformation($composer5, "C711@32801L22,720@33160L3512,720@33124L3548:CoreTextField.kt#423gt5");
                                        if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(2032502107, $changed3, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:698)");
                                            }
                                            Modifier modifierHeightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m1067heightInVpY3zN4$default(Modifier.INSTANCE, legacyTextFieldState.m1368getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), textStyle5, i16, i17);
                                            TextFieldScrollerPosition textFieldScrollerPosition2 = textFieldScrollerPosition;
                                            TextFieldValue textFieldValue2 = textFieldValue;
                                            VisualTransformation visualTransformation7 = visualTransformation6;
                                            ComposerKt.sourceInformationMarkerStart($composer5, 713189947, "CC(remember):CoreTextField.kt#9igjgp");
                                            boolean invalid$iv14 = $composer5.changedInstance(legacyTextFieldState);
                                            final LegacyTextFieldState legacyTextFieldState2 = legacyTextFieldState;
                                            Object it$iv18 = $composer5.rememberedValue();
                                            if (invalid$iv14 || it$iv18 == Composer.INSTANCE.getEmpty()) {
                                                value$iv20 = new Function0<TextLayoutResultProxy>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$coreTextFieldModifier$1$1
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final TextLayoutResultProxy invoke() {
                                                        return legacyTextFieldState2.getLayoutResult();
                                                    }
                                                };
                                                $composer5.updateRememberedValue(value$iv20);
                                            } else {
                                                value$iv20 = it$iv18;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd($composer5);
                                            Modifier coreTextFieldModifier = BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScrollKt.textFieldScroll(modifierHeightInLines, textFieldScrollerPosition2, textFieldValue2, visualTransformation7, (Function0) value$iv20).then(modifier6).then(modifier7), textStyle5).then(modifier8).then(modifier9), bringIntoViewRequester2);
                                            final TextFieldSelectionManager textFieldSelectionManager2 = textFieldSelectionManager;
                                            final LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState;
                                            final boolean z36 = z34;
                                            final boolean z37 = z35;
                                            final Function1<TextLayoutResult, Unit> function10 = function9;
                                            final TextFieldValue textFieldValue3 = textFieldValue;
                                            final OffsetMapping offsetMapping3 = offsetMapping2;
                                            final Density density3 = density2;
                                            final int i18 = i17;
                                            SimpleLayoutKt.SimpleLayout(coreTextFieldModifier, ComposableLambdaKt.rememberComposableLambda(-363167407, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1
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

                                                /* JADX WARN: Code duplicated, block: B:35:0x0131  */
                                                public final void invoke(Composer $composer6, int $changed4) {
                                                    boolean z38;
                                                    ComposerKt.sourceInformation($composer6, "C721@33178L2880,776@36076L322:CoreTextField.kt#423gt5");
                                                    if (($changed4 & 3) != 2 || !$composer6.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-363167407, $changed4, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous>.<anonymous> (CoreTextField.kt:721)");
                                                        }
                                                        final LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                                        final Function1<TextLayoutResult, Unit> function11 = function10;
                                                        final TextFieldValue textFieldValue4 = textFieldValue3;
                                                        final OffsetMapping offsetMapping4 = offsetMapping3;
                                                        final Density density4 = density3;
                                                        final int i19 = i18;
                                                        MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1.2
                                                            @Override // androidx.compose.ui.layout.MeasurePolicy
                                                            /* JADX INFO: renamed from: measure-3p2s80s */
                                                            public MeasureResult mo391measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
                                                                Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                                                                LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState4;
                                                                Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                                                                Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                                                                Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                                                                try {
                                                                    TextLayoutResultProxy prevProxy = legacyTextFieldState5.getLayoutResult();
                                                                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                    TextLayoutResult prevResult = prevProxy != null ? prevProxy.getValue() : null;
                                                                    Triple<Integer, Integer, TextLayoutResult> tripleM1421layout_EkL_Y$foundation_release = TextFieldDelegate.INSTANCE.m1421layout_EkL_Y$foundation_release(legacyTextFieldState4.getTextDelegate(), constraints, $this$measure_u2d3p2s80s.getLayoutDirection(), prevResult);
                                                                    int width = tripleM1421layout_EkL_Y$foundation_release.component1().intValue();
                                                                    int height = tripleM1421layout_EkL_Y$foundation_release.component2().intValue();
                                                                    TextLayoutResult result = tripleM1421layout_EkL_Y$foundation_release.component3();
                                                                    if (!Intrinsics.areEqual(prevResult, result)) {
                                                                        legacyTextFieldState4.setLayoutResult(new TextLayoutResultProxy(result, null, prevProxy != null ? prevProxy.getDecorationBoxCoordinates() : null, 2, null));
                                                                        function11.invoke(result);
                                                                        CoreTextFieldKt.notifyFocusedRect(legacyTextFieldState4, textFieldValue4, offsetMapping4);
                                                                    }
                                                                    LegacyTextFieldState legacyTextFieldState6 = legacyTextFieldState4;
                                                                    Density $this$measure_3p2s80s_u24lambda_u241 = density4;
                                                                    legacyTextFieldState6.m1372setMinHeightForSingleLineField0680j_4($this$measure_3p2s80s_u24lambda_u241.mo723toDpu2uoSUM(i19 == 1 ? TextDelegateKt.ceilToIntPx(result.getLineBottom(0)) : 0));
                                                                    HorizontalAlignmentLine firstBaseline = AlignmentLineKt.getFirstBaseline();
                                                                    float $this$fastRoundToInt$iv = result.getFirstBaseline();
                                                                    HorizontalAlignmentLine lastBaseline = AlignmentLineKt.getLastBaseline();
                                                                    float $this$fastRoundToInt$iv2 = result.getLastBaseline();
                                                                    return $this$measure_u2d3p2s80s.layout(width, height, MapsKt.mapOf(TuplesKt.to(firstBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv))), TuplesKt.to(lastBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv2)))), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1$2$measure$2
                                                                        @Override // kotlin.jvm.functions.Function1
                                                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                            invoke2(placementScope);
                                                                            return Unit.INSTANCE;
                                                                        }

                                                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                        public final void invoke2(Placeable.PlacementScope $this$layout) {
                                                                        }
                                                                    });
                                                                } catch (Throwable th) {
                                                                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                    throw th;
                                                                }
                                                            }

                                                            @Override // androidx.compose.ui.layout.MeasurePolicy
                                                            public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
                                                                legacyTextFieldState4.getTextDelegate().layoutIntrinsics($this$maxIntrinsicWidth.getLayoutDirection());
                                                                return legacyTextFieldState4.getTextDelegate().getMaxIntrinsicWidth();
                                                            }
                                                        };
                                                        ComposerKt.sourceInformationMarkerStart($composer6, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                                        Modifier modifier$iv = Modifier.INSTANCE;
                                                        int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                        CompositionLocalMap localMap$iv = $composer6.getCurrentCompositionLocalMap();
                                                        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer6, modifier$iv);
                                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                        int $changed$iv$iv = ((0 << 6) & 896) | 6;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                        if (!($composer6.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        $composer6.startReusableNode();
                                                        if ($composer6.getInserting()) {
                                                            $composer6.createNode(constructor);
                                                        } else {
                                                            $composer6.useNode();
                                                        }
                                                        Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer6);
                                                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                                                            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                                                            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                                                        }
                                                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                                        int i20 = ($changed$iv$iv >> 6) & 14;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, -1674249312, "C:CoreTextField.kt#423gt5");
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        $composer6.endNode();
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        TextFieldSelectionManager textFieldSelectionManager3 = textFieldSelectionManager2;
                                                        if (legacyTextFieldState3.getHandleState() == HandleState.None || legacyTextFieldState3.getLayoutCoordinates() == null) {
                                                            z38 = false;
                                                        } else {
                                                            LayoutCoordinates layoutCoordinates = legacyTextFieldState3.getLayoutCoordinates();
                                                            Intrinsics.checkNotNull(layoutCoordinates);
                                                            if (layoutCoordinates.isAttached() && z36) {
                                                                z38 = true;
                                                            } else {
                                                                z38 = false;
                                                            }
                                                        }
                                                        CoreTextFieldKt.SelectionToolbarAndHandles(textFieldSelectionManager3, z38, $composer6, 0);
                                                        if (legacyTextFieldState3.getHandleState() == HandleState.Cursor && !z37 && z36) {
                                                            $composer6.startReplaceGroup(-2032274);
                                                            ComposerKt.sourceInformation($composer6, "789@36600L40");
                                                            CoreTextFieldKt.TextFieldCursorHandle(textFieldSelectionManager2, $composer6, 0);
                                                            $composer6.endReplaceGroup();
                                                        } else {
                                                            $composer6.startReplaceGroup(-1955394);
                                                            $composer6.endReplaceGroup();
                                                        }
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    $composer6.skipToGroupEnd();
                                                }
                                            }, $composer5, 54), $composer5, 48, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer5.skipToGroupEnd();
                                    }
                                }, $composer4, 54), $composer4, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    };
                    $composer2 = $composer3;
                    CoreTextFieldRootBox(decorationBoxModifier, manager, ComposableLambdaKt.rememberComposableLambda(-374338080, true, function7, $composer2, 54), $composer2, 384);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    imeOptions5 = imeOptions4;
                }
                if (($dirty1 & 48) == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                invalid$iv3 = z20 | z6 | $composer3.changedInstance(offsetMapping) | $composer3.changedInstance(coroutineScope) | $composer3.changedInstance(bringIntoViewRequester) | $composer3.changedInstance(manager);
                Object it$iv18 = $composer3.rememberedValue();
                if (invalid$iv3) {
                }
                final boolean z210 = enabled2;
                final boolean z211 = readOnly2;
                final ImeOptions imeOptions10 = imeOptions5;
                value$iv9 = new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$focusModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                        invoke2(focusState);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FocusState it2) {
                        TextLayoutResultProxy layoutResult;
                        if (state.getHasFocus() == it2.isFocused()) {
                            return;
                        }
                        state.setHasFocus(it2.isFocused());
                        if (!state.getHasFocus() || !z210 || z211) {
                            CoreTextFieldKt.endInputSession(state);
                        } else {
                            CoreTextFieldKt.startInputSession(textInputService, state, value, imeOptions10, offsetMapping);
                        }
                        if (it2.isFocused() && (layoutResult = state.getLayoutResult()) != null) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1$1(bringIntoViewRequester, value, state, layoutResult, offsetMapping, null), 3, null);
                        }
                        if (!it2.isFocused()) {
                            TextFieldSelectionManager.m1748deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv9);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                MutableInteractionSource interactionSource7 = interactionSource3;
                Modifier focusModifier2 = TextFieldGestureModifiersKt.textFieldFocusModifier(companion, enabled2, focusRequester, interactionSource7, (Function1) value$iv9);
                if (enabled2) {
                    z7 = false;
                } else {
                    z7 = false;
                }
                State writeable$delegate2 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(z7), $composer3, 0);
                Unit unit2 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705195503, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv4 = $composer3.changed(writeable$delegate2) | $composer3.changedInstance(state) | $composer3.changedInstance(textInputService) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                Object it$iv19 = $composer3.rememberedValue();
                if (invalid$iv4) {
                }
                value$iv10 = new CoreTextFieldKt$CoreTextField$2$1(state, writeable$delegate2, textInputService, manager, imeOptions5, null);
                $composer3.updateRememberedValue(value$iv10);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(unit2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv10, $composer3, 6);
                Modifier.Companion companion8 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705163133, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv5 = $composer3.changedInstance(state);
                value$iv11 = $composer3.rememberedValue();
                if (!invalid$iv5) {
                }
                value$iv11 = (Function1) new Function1<Boolean, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean it2) {
                        state.setInTouchMode(it2);
                    }
                };
                $composer3.updateRememberedValue(value$iv11);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierUpdateSelectionTouchMode2 = SelectionGesturesKt.updateSelectionTouchMode(companion8, (Function1) value$iv11);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705159310, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance8 = $composer3.changedInstance(state);
                if (($dirty1 & 57344) == 16384) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean z212 = zChangedInstance8 | z8;
                if (($dirty1 & 7168) == 2048) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                invalid$iv6 = z212 | z9 | $composer3.changedInstance(offsetMapping) | $composer3.changedInstance(manager);
                Object it$iv110 = $composer3.rememberedValue();
                if (invalid$iv6) {
                }
                final boolean z213 = readOnly2;
                final boolean z214 = enabled2;
                value$iv12 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m1345invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m1345invokek4lQ0M(long offset) {
                        CoreTextFieldKt.tapToFocus(state, focusRequester, !z213);
                        if (state.getHasFocus() && z214) {
                            if (state.getHandleState() != HandleState.Selection) {
                                TextLayoutResultProxy layoutResult = state.getLayoutResult();
                                if (layoutResult != null) {
                                    LegacyTextFieldState legacyTextFieldState = state;
                                    TextFieldDelegate.INSTANCE.m1422setCursorOffsetULxng0E$foundation_release(offset, layoutResult, legacyTextFieldState.getProcessor(), offsetMapping, legacyTextFieldState.getOnValueChange());
                                    if (legacyTextFieldState.getTextDelegate().getText().length() > 0) {
                                        legacyTextFieldState.setHandleState(HandleState.Cursor);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            manager.m1752deselect_kEHs6E$foundation_release(Offset.m4282boximpl(offset));
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv12);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(modifierUpdateSelectionTouchMode2, interactionSource7, enabled2, (Function1) value$iv12), manager.getMouseSelectionObserver(), manager.getTouchSelectionObserver()), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
                Modifier.Companion companion9 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705122293, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance9 = $composer3.changedInstance(state);
                if (($dirty & 14) == 4) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                invalid$iv7 = zChangedInstance9 | z10 | $composer3.changedInstance(offsetMapping);
                Object it$iv111 = $composer3.rememberedValue();
                if (invalid$iv7) {
                }
                value$iv13 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$drawModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$drawBehind) {
                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                        if (layoutResult != null) {
                            TextFieldValue textFieldValue = value;
                            LegacyTextFieldState legacyTextFieldState = state;
                            OffsetMapping offsetMapping2 = offsetMapping;
                            Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
                            TextFieldDelegate.INSTANCE.m1420drawQ1vqE60$foundation_release(canvas, textFieldValue, legacyTextFieldState.m1370getSelectionPreviewHighlightRanged9O1mEE(), legacyTextFieldState.m1367getDeletionPreviewHighlightRanged9O1mEE(), offsetMapping2, layoutResult.getValue(), legacyTextFieldState.getHighlightPaint(), legacyTextFieldState.getSelectionBackgroundColor());
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv13);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Modifier drawModifier2 = DrawModifierKt.drawBehind(companion9, (Function1) value$iv13);
                Modifier.Companion companion10 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705102808, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance10 = $composer3.changedInstance(state);
                if (($dirty1 & 7168) == 2048) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                windowInfo2 = windowInfo;
                boolean zChanged3 = zChangedInstance10 | z11 | $composer3.changed(windowInfo2) | $composer3.changedInstance(manager);
                if (($dirty & 14) == 4) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                invalid$iv8 = zChanged3 | z12 | $composer3.changedInstance(offsetMapping);
                Object it$iv112 = $composer3.rememberedValue();
                if (invalid$iv8) {
                }
                final boolean z215 = enabled2;
                value$iv14 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$onPositionedModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates it2) {
                        state.setLayoutCoordinates(it2);
                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                        if (layoutResult != null) {
                            layoutResult.setInnerTextFieldCoordinates(it2);
                        }
                        if (z215) {
                            if (state.getHandleState() == HandleState.Selection) {
                                if (state.getShowFloatingToolbar() && windowInfo2.isWindowFocused()) {
                                    manager.showSelectionToolbar$foundation_release();
                                } else {
                                    manager.hideSelectionToolbar$foundation_release();
                                }
                                state.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                                state.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, false));
                                state.setShowCursorHandle(TextRange.m6473getCollapsedimpl(value.getSelection()));
                            } else if (state.getHandleState() == HandleState.Cursor) {
                                state.setShowCursorHandle(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                            }
                            CoreTextFieldKt.notifyFocusedRect(state, value, offsetMapping);
                            TextLayoutResultProxy layoutResult2 = state.getLayoutResult();
                            if (layoutResult2 != null) {
                                LegacyTextFieldState legacyTextFieldState = state;
                                TextFieldValue textFieldValue = value;
                                OffsetMapping offsetMapping2 = offsetMapping;
                                TextInputSession inputSession = legacyTextFieldState.getInputSession();
                                if (inputSession != null && legacyTextFieldState.getHasFocus()) {
                                    TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation_release(inputSession, textFieldValue, offsetMapping2, layoutResult2);
                                }
                            }
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv14);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Modifier onPositionedModifier2 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion10, (Function1) value$iv14);
                final boolean isPassword2 = visualTransformation5 instanceof PasswordVisualTransformation;
                Modifier.Companion companion11 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705046115, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance11 = $composer3.changedInstance(transformedText);
                if (($dirty & 14) == 4) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                boolean z216 = zChangedInstance11 | z13;
                if (($dirty1 & 7168) == 2048) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean zChanged4 = z14 | z216 | $composer3.changed(isPassword2);
                if ((57344 & $dirty1) == 16384) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                invalid$iv9 = zChanged4 | z15 | $composer3.changedInstance(state) | $composer3.changedInstance(offsetMapping) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                Object it$iv113 = $composer3.rememberedValue();
                if (invalid$iv9) {
                }
                final boolean z217 = enabled2;
                final boolean z218 = readOnly2;
                final ImeOptions imeOptions11 = imeOptions5;
                value$iv15 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setEditableText($this$semantics, transformedText.getText());
                        SemanticsPropertiesKt.m6309setTextSelectionRangeFDrldGo($this$semantics, value.getSelection());
                        if (!z217) {
                            SemanticsPropertiesKt.disabled($this$semantics);
                        }
                        if (isPassword2) {
                            SemanticsPropertiesKt.password($this$semantics);
                        }
                        boolean editable = z217 && !z218;
                        SemanticsPropertiesKt.setEditable($this$semantics, editable);
                        final LegacyTextFieldState legacyTextFieldState = state;
                        SemanticsPropertiesKt.getTextLayoutResult$default($this$semantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(List<TextLayoutResult> list) {
                                boolean z34;
                                if (legacyTextFieldState.getLayoutResult() != null) {
                                    TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                                    Intrinsics.checkNotNull(layoutResult);
                                    list.add(layoutResult.getValue());
                                    z34 = true;
                                } else {
                                    z34 = false;
                                }
                                return Boolean.valueOf(z34);
                            }
                        }, 1, null);
                        if (editable) {
                            final LegacyTextFieldState legacyTextFieldState2 = state;
                            SemanticsPropertiesKt.setText$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(AnnotatedString text) {
                                    Unit unit3;
                                    TextInputSession session = legacyTextFieldState2.getInputSession();
                                    if (session != null) {
                                        LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState2;
                                        TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new DeleteAllCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState3.getProcessor(), legacyTextFieldState3.getOnValueChange(), session);
                                        unit3 = Unit.INSTANCE;
                                    } else {
                                        unit3 = null;
                                    }
                                    if (unit3 == null) {
                                        SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                        legacyTextFieldState2.getOnValueChange().invoke(new TextFieldValue(text.getText(), TextRangeKt.TextRange(text.getText().length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                    }
                                    return true;
                                }
                            }, 1, null);
                            final boolean z34 = z218;
                            final boolean z35 = z217;
                            final LegacyTextFieldState legacyTextFieldState3 = state;
                            final TextFieldValue textFieldValue = value;
                            SemanticsPropertiesKt.insertTextAtCursor$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(AnnotatedString text) {
                                    Unit unit3;
                                    if (z34 || !z35) {
                                        return false;
                                    }
                                    TextInputSession session = legacyTextFieldState3.getInputSession();
                                    if (session != null) {
                                        LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                        TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new FinishComposingTextCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState4.getProcessor(), legacyTextFieldState4.getOnValueChange(), session);
                                        unit3 = Unit.INSTANCE;
                                    } else {
                                        unit3 = null;
                                    }
                                    if (unit3 == null) {
                                        SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                        TextFieldValue textFieldValue2 = textFieldValue;
                                        LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState3;
                                        String newText = StringsKt.replaceRange((CharSequence) textFieldValue2.getText(), TextRange.m6479getStartimpl(textFieldValue2.getSelection()), TextRange.m6474getEndimpl(textFieldValue2.getSelection()), (CharSequence) text).toString();
                                        long newCursor = TextRangeKt.TextRange(TextRange.m6479getStartimpl(textFieldValue2.getSelection()) + text.length());
                                        legacyTextFieldState5.getOnValueChange().invoke(new TextFieldValue(newText, newCursor, (TextRange) null, 4, (DefaultConstructorMarker) null));
                                    }
                                    return true;
                                }
                            }, 1, null);
                        }
                        final OffsetMapping offsetMapping2 = offsetMapping;
                        final boolean z36 = z217;
                        final TextFieldValue textFieldValue2 = value;
                        final TextFieldSelectionManager textFieldSelectionManager = manager;
                        final LegacyTextFieldState legacyTextFieldState4 = state;
                        SemanticsPropertiesKt.setSelection$default($this$semantics, null, new Function3<Integer, Integer, Boolean, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Integer num2, Boolean bool) {
                                return invoke(num.intValue(), num2.intValue(), bool.booleanValue());
                            }

                            public final Boolean invoke(int selectionStart, int selectionEnd, boolean relativeToOriginalText) {
                                int start;
                                int end;
                                if (relativeToOriginalText) {
                                    start = selectionStart;
                                } else {
                                    start = offsetMapping2.transformedToOriginal(selectionStart);
                                }
                                if (relativeToOriginalText) {
                                    end = selectionEnd;
                                } else {
                                    end = offsetMapping2.transformedToOriginal(selectionEnd);
                                }
                                boolean z37 = false;
                                if (z36 && (start != TextRange.m6479getStartimpl(textFieldValue2.getSelection()) || end != TextRange.m6474getEndimpl(textFieldValue2.getSelection()))) {
                                    if (Math.min(start, end) >= 0 && Math.max(start, end) <= textFieldValue2.getText().length()) {
                                        if (!relativeToOriginalText && start != end) {
                                            TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager, false, 1, null);
                                        } else {
                                            textFieldSelectionManager.exitSelectionMode$foundation_release();
                                        }
                                        legacyTextFieldState4.getOnValueChange().invoke(new TextFieldValue(textFieldValue2.getText(), TextRangeKt.TextRange(start, end), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                        z37 = true;
                                    } else {
                                        textFieldSelectionManager.exitSelectionMode$foundation_release();
                                    }
                                }
                                return Boolean.valueOf(z37);
                            }
                        }, 1, null);
                        int imeAction = imeOptions11.getImeAction();
                        final LegacyTextFieldState legacyTextFieldState5 = state;
                        final ImeOptions imeOptions12 = imeOptions11;
                        SemanticsPropertiesKt.m6304onImeAction9UiTYpY$default($this$semantics, imeAction, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                legacyTextFieldState5.getOnImeActionPerformed().invoke(ImeAction.m6641boximpl(imeOptions12.getImeAction()));
                                return true;
                            }
                        }, 2, null);
                        final LegacyTextFieldState legacyTextFieldState6 = state;
                        final FocusRequester focusRequester2 = focusRequester;
                        final boolean z37 = z218;
                        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                CoreTextFieldKt.tapToFocus(legacyTextFieldState6, focusRequester2, !z37);
                                return true;
                            }
                        }, 1, null);
                        final TextFieldSelectionManager textFieldSelectionManager2 = manager;
                        SemanticsPropertiesKt.onLongClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.7
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager2, false, 1, null);
                                return true;
                            }
                        }, 1, null);
                        if (!TextRange.m6473getCollapsedimpl(value.getSelection()) && !isPassword2) {
                            final TextFieldSelectionManager textFieldSelectionManager3 = manager;
                            SemanticsPropertiesKt.copyText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.8
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    TextFieldSelectionManager.copy$foundation_release$default(textFieldSelectionManager3, false, 1, null);
                                    return true;
                                }
                            }, 1, null);
                            if (z217 && !z218) {
                                final TextFieldSelectionManager textFieldSelectionManager4 = manager;
                                SemanticsPropertiesKt.cutText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.9
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        textFieldSelectionManager4.cut$foundation_release();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        }
                        if (z217 && !z218) {
                            final TextFieldSelectionManager textFieldSelectionManager5 = manager;
                            SemanticsPropertiesKt.pasteText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.10
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    textFieldSelectionManager5.paste$foundation_release();
                                    return true;
                                }
                            }, 1, null);
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv15);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier semanticsModifier2 = SemanticsModifierKt.semantics(companion11, true, (Function1) value$iv15);
                if (enabled2) {
                    z16 = false;
                } else {
                    z16 = false;
                }
                boolean showCursor2 = z16;
                final Modifier cursorModifier2 = TextFieldCursorKt.cursor(Modifier.INSTANCE, state, value, offsetMapping, cursorBrush2, showCursor2);
                ComposerKt.sourceInformationMarkerStart($composer3, -1704832829, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv10 = $composer3.changedInstance(manager);
                Object it$iv114 = $composer3.rememberedValue();
                if (invalid$iv10) {
                }
                value$iv16 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        final TextFieldSelectionManager textFieldSelectionManager = manager;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                            }
                        };
                    }
                };
                $composer3.updateRememberedValue(value$iv16);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.DisposableEffect(manager, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv16, $composer3, 0);
                ComposerKt.sourceInformationMarkerStart($composer3, -1704829391, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance12 = $composer3.changedInstance(state) | $composer3.changedInstance(textInputService);
                if (($dirty & 14) == 4) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                invalid$iv11 = zChangedInstance12 | z17 | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                Object it$iv115 = $composer3.rememberedValue();
                if (invalid$iv11) {
                }
                value$iv17 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        if (state.getHasFocus()) {
                            state.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation_release(textInputService, value, state.getProcessor(), imeOptions5, state.getOnValueChange(), state.getOnImeActionPerformed()));
                        }
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                            }
                        };
                    }
                };
                $composer3.updateRememberedValue(value$iv17);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.DisposableEffect(imeOptions5, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv17, $composer3, ($dirty1 >> 3) & 14);
                Modifier.Companion companion12 = Modifier.INSTANCE;
                Function1<TextFieldValue, Unit> onValueChange2 = state.getOnValueChange();
                boolean z34 = !readOnly2;
                if (maxLines5 == 1) {
                    z18 = true;
                } else {
                    z18 = false;
                }
                showHandleAndMagnifier = false;
                imeOptions6 = imeOptions5;
                boolean z35 = z18;
                int $dirty4 = $dirty1;
                enabled3 = enabled2;
                Modifier textKeyInputModifier2 = TextFieldKeyInputKt.m1429textFieldKeyInput2WJ9YEU(companion12, state, manager, value, onValueChange2, z34, z35, offsetMapping, undoManager, imeOptions5.getImeAction());
                Modifier.Companion companion13 = Modifier.INSTANCE;
                boolean zCoreTextField$lambda$12 = CoreTextField$lambda$11(writeable$delegate2);
                ComposerKt.sourceInformationMarkerStart($composer3, -1704798381, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv12 = $composer3.changedInstance(state) | (((($dirty4 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions6)) || ($dirty4 & 48) == 32) | $composer3.changedInstance(legacyTextInputServiceAdapter);
                Object it$iv116 = $composer3.rememberedValue();
                if (invalid$iv12) {
                    value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            if (!state.getHasFocus()) {
                                focusRequester.requestFocus();
                            }
                            if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                                legacyTextInputServiceAdapter.startStylusHandwriting();
                            }
                            return true;
                        }
                    };
                    $composer3.updateRememberedValue(value$iv18);
                } else {
                    value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            if (!state.getHasFocus()) {
                                focusRequester.requestFocus();
                            }
                            if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                                legacyTextInputServiceAdapter.startStylusHandwriting();
                            }
                            return true;
                        }
                    };
                    $composer3.updateRememberedValue(value$iv18);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier stylusHandwritingModifier2 = StylusHandwritingKt.stylusHandwriting(companion13, zCoreTextField$lambda$12, (Function0) value$iv18);
                Modifier decorationBoxModifier2 = OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier5, legacyTextInputServiceAdapter, state, manager).then(stylusHandwritingModifier2).then(focusModifier2), state, focusManager), state, manager).then(textKeyInputModifier2), scrollerPosition, interactionSource7, enabled3).then(r15).then(semanticsModifier2), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates it2) {
                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                        if (layoutResult == null) {
                            return;
                        }
                        layoutResult.setDecorationBoxCoordinates(it2);
                    }
                });
                if (enabled3) {
                    showHandleAndMagnifier = true;
                }
                if (showHandleAndMagnifier) {
                    companionTextFieldMagnifier = TextFieldSelectionManager_androidKt.textFieldMagnifier(Modifier.INSTANCE, manager);
                } else {
                    companionTextFieldMagnifier = Modifier.INSTANCE;
                }
                maxLines3 = maxLines5;
                final Modifier magnifierModifier2 = companionTextFieldMagnifier;
                final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function8 = function3M1344getLambda1$foundation_release;
                final TextStyle textStyle5 = textStyle2;
                final int i16 = minLines2;
                enabled4 = enabled3;
                imeOptions7 = imeOptions6;
                interactionSource4 = interactionSource7;
                visualTransformation3 = visualTransformation5;
                modifier3 = modifier5;
                final boolean z36 = showHandleAndMagnifier;
                final boolean z37 = readOnly2;
                final Function1<? super TextLayoutResult, Unit> function9 = function4;
                Function2<Composer, Integer, Unit> function10 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5
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

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C695@31947L4735,695@31933L4749:CoreTextField.kt#423gt5");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-374338080, $changed2, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:695)");
                            }
                            Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> function11 = function8;
                            final LegacyTextFieldState legacyTextFieldState = state;
                            final TextStyle textStyle6 = textStyle5;
                            final int i17 = i16;
                            final int i18 = maxLines3;
                            final TextFieldScrollerPosition textFieldScrollerPosition = scrollerPosition;
                            final TextFieldValue textFieldValue = value;
                            final VisualTransformation visualTransformation6 = visualTransformation5;
                            final Modifier modifier6 = cursorModifier2;
                            final Modifier modifier7 = drawModifier2;
                            final Modifier modifier8 = onPositionedModifier2;
                            final Modifier modifier9 = magnifierModifier2;
                            final BringIntoViewRequester bringIntoViewRequester2 = bringIntoViewRequester;
                            final TextFieldSelectionManager textFieldSelectionManager = manager;
                            final boolean z38 = z36;
                            final boolean z39 = z37;
                            final Function1<? super TextLayoutResult, Unit> function12 = function9;
                            final OffsetMapping offsetMapping2 = offsetMapping;
                            final Density density2 = density;
                            function11.invoke(ComposableLambdaKt.rememberComposableLambda(2032502107, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1
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

                                public final void invoke(Composer $composer5, int $changed3) {
                                    Object value$iv20;
                                    ComposerKt.sourceInformation($composer5, "C711@32801L22,720@33160L3512,720@33124L3548:CoreTextField.kt#423gt5");
                                    if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2032502107, $changed3, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:698)");
                                        }
                                        Modifier modifierHeightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m1067heightInVpY3zN4$default(Modifier.INSTANCE, legacyTextFieldState.m1368getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), textStyle6, i17, i18);
                                        TextFieldScrollerPosition textFieldScrollerPosition2 = textFieldScrollerPosition;
                                        TextFieldValue textFieldValue2 = textFieldValue;
                                        VisualTransformation visualTransformation7 = visualTransformation6;
                                        ComposerKt.sourceInformationMarkerStart($composer5, 713189947, "CC(remember):CoreTextField.kt#9igjgp");
                                        boolean invalid$iv14 = $composer5.changedInstance(legacyTextFieldState);
                                        final LegacyTextFieldState legacyTextFieldState2 = legacyTextFieldState;
                                        Object it$iv117 = $composer5.rememberedValue();
                                        if (invalid$iv14 || it$iv117 == Composer.INSTANCE.getEmpty()) {
                                            value$iv20 = new Function0<TextLayoutResultProxy>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$coreTextFieldModifier$1$1
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final TextLayoutResultProxy invoke() {
                                                    return legacyTextFieldState2.getLayoutResult();
                                                }
                                            };
                                            $composer5.updateRememberedValue(value$iv20);
                                        } else {
                                            value$iv20 = it$iv117;
                                        }
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        Modifier coreTextFieldModifier = BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScrollKt.textFieldScroll(modifierHeightInLines, textFieldScrollerPosition2, textFieldValue2, visualTransformation7, (Function0) value$iv20).then(modifier6).then(modifier7), textStyle6).then(modifier8).then(modifier9), bringIntoViewRequester2);
                                        final TextFieldSelectionManager textFieldSelectionManager2 = textFieldSelectionManager;
                                        final LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState;
                                        final boolean z310 = z38;
                                        final boolean z311 = z39;
                                        final Function1<? super TextLayoutResult, Unit> function13 = function12;
                                        final TextFieldValue textFieldValue3 = textFieldValue;
                                        final OffsetMapping offsetMapping3 = offsetMapping2;
                                        final Density density3 = density2;
                                        final int i19 = i18;
                                        SimpleLayoutKt.SimpleLayout(coreTextFieldModifier, ComposableLambdaKt.rememberComposableLambda(-363167407, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1
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

                                            /* JADX WARN: Code duplicated, block: B:35:0x0131  */
                                            public final void invoke(Composer $composer6, int $changed4) {
                                                boolean z312;
                                                ComposerKt.sourceInformation($composer6, "C721@33178L2880,776@36076L322:CoreTextField.kt#423gt5");
                                                if (($changed4 & 3) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-363167407, $changed4, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous>.<anonymous> (CoreTextField.kt:721)");
                                                    }
                                                    final LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                                    final Function1<? super TextLayoutResult, Unit> function14 = function13;
                                                    final TextFieldValue textFieldValue4 = textFieldValue3;
                                                    final OffsetMapping offsetMapping4 = offsetMapping3;
                                                    final Density density4 = density3;
                                                    final int i110 = i19;
                                                    MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1.2
                                                        @Override // androidx.compose.ui.layout.MeasurePolicy
                                                        /* JADX INFO: renamed from: measure-3p2s80s */
                                                        public MeasureResult mo391measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
                                                            Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                                                            LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState4;
                                                            Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                                                            Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                                                            Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                                                            try {
                                                                TextLayoutResultProxy prevProxy = legacyTextFieldState5.getLayoutResult();
                                                                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                TextLayoutResult prevResult = prevProxy != null ? prevProxy.getValue() : null;
                                                                Triple<Integer, Integer, TextLayoutResult> tripleM1421layout_EkL_Y$foundation_release = TextFieldDelegate.INSTANCE.m1421layout_EkL_Y$foundation_release(legacyTextFieldState4.getTextDelegate(), constraints, $this$measure_u2d3p2s80s.getLayoutDirection(), prevResult);
                                                                int width = tripleM1421layout_EkL_Y$foundation_release.component1().intValue();
                                                                int height = tripleM1421layout_EkL_Y$foundation_release.component2().intValue();
                                                                TextLayoutResult result = tripleM1421layout_EkL_Y$foundation_release.component3();
                                                                if (!Intrinsics.areEqual(prevResult, result)) {
                                                                    legacyTextFieldState4.setLayoutResult(new TextLayoutResultProxy(result, null, prevProxy != null ? prevProxy.getDecorationBoxCoordinates() : null, 2, null));
                                                                    function14.invoke(result);
                                                                    CoreTextFieldKt.notifyFocusedRect(legacyTextFieldState4, textFieldValue4, offsetMapping4);
                                                                }
                                                                LegacyTextFieldState legacyTextFieldState6 = legacyTextFieldState4;
                                                                Density $this$measure_3p2s80s_u24lambda_u241 = density4;
                                                                legacyTextFieldState6.m1372setMinHeightForSingleLineField0680j_4($this$measure_3p2s80s_u24lambda_u241.mo723toDpu2uoSUM(i110 == 1 ? TextDelegateKt.ceilToIntPx(result.getLineBottom(0)) : 0));
                                                                HorizontalAlignmentLine firstBaseline = AlignmentLineKt.getFirstBaseline();
                                                                float $this$fastRoundToInt$iv = result.getFirstBaseline();
                                                                HorizontalAlignmentLine lastBaseline = AlignmentLineKt.getLastBaseline();
                                                                float $this$fastRoundToInt$iv2 = result.getLastBaseline();
                                                                return $this$measure_u2d3p2s80s.layout(width, height, MapsKt.mapOf(TuplesKt.to(firstBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv))), TuplesKt.to(lastBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv2)))), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1$2$measure$2
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                        invoke2(placementScope);
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                    public final void invoke2(Placeable.PlacementScope $this$layout) {
                                                                    }
                                                                });
                                                            } catch (Throwable th) {
                                                                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                throw th;
                                                            }
                                                        }

                                                        @Override // androidx.compose.ui.layout.MeasurePolicy
                                                        public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
                                                            legacyTextFieldState4.getTextDelegate().layoutIntrinsics($this$maxIntrinsicWidth.getLayoutDirection());
                                                            return legacyTextFieldState4.getTextDelegate().getMaxIntrinsicWidth();
                                                        }
                                                    };
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                                    Modifier modifier$iv = Modifier.INSTANCE;
                                                    int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                    CompositionLocalMap localMap$iv = $composer6.getCurrentCompositionLocalMap();
                                                    Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer6, modifier$iv);
                                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                    int $changed$iv$iv = ((0 << 6) & 896) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                    if (!($composer6.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    $composer6.startReusableNode();
                                                    if ($composer6.getInserting()) {
                                                        $composer6.createNode(constructor);
                                                    } else {
                                                        $composer6.useNode();
                                                    }
                                                    Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer6);
                                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                    if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                                                        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                                                        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                                                    }
                                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                                    int i20 = ($changed$iv$iv >> 6) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1674249312, "C:CoreTextField.kt#423gt5");
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    $composer6.endNode();
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    TextFieldSelectionManager textFieldSelectionManager3 = textFieldSelectionManager2;
                                                    if (legacyTextFieldState3.getHandleState() == HandleState.None || legacyTextFieldState3.getLayoutCoordinates() == null) {
                                                        z312 = false;
                                                    } else {
                                                        LayoutCoordinates layoutCoordinates = legacyTextFieldState3.getLayoutCoordinates();
                                                        Intrinsics.checkNotNull(layoutCoordinates);
                                                        if (layoutCoordinates.isAttached() && z310) {
                                                            z312 = true;
                                                        } else {
                                                            z312 = false;
                                                        }
                                                    }
                                                    CoreTextFieldKt.SelectionToolbarAndHandles(textFieldSelectionManager3, z312, $composer6, 0);
                                                    if (legacyTextFieldState3.getHandleState() == HandleState.Cursor && !z311 && z310) {
                                                        $composer6.startReplaceGroup(-2032274);
                                                        ComposerKt.sourceInformation($composer6, "789@36600L40");
                                                        CoreTextFieldKt.TextFieldCursorHandle(textFieldSelectionManager2, $composer6, 0);
                                                        $composer6.endReplaceGroup();
                                                    } else {
                                                        $composer6.startReplaceGroup(-1955394);
                                                        $composer6.endReplaceGroup();
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer6.skipToGroupEnd();
                                            }
                                        }, $composer5, 54), $composer5, 48, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer5.skipToGroupEnd();
                                }
                            }, $composer4, 54), $composer4, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                };
                $composer2 = $composer3;
                CoreTextFieldRootBox(decorationBoxModifier2, manager, ComposableLambdaKt.rememberComposableLambda(-374338080, true, function10, $composer2, 54), $composer2, 384);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv3 = (Function0) new Function0<TextFieldScrollerPosition>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$scrollerPosition$1$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TextFieldScrollerPosition invoke() {
                    return new TextFieldScrollerPosition(orientation, 0.0f, 2, null);
                }
            };
            $composer3.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final TextFieldScrollerPosition scrollerPosition2 = (TextFieldScrollerPosition) RememberSaveableKt.m4120rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv3, $composer3, 0, 4);
            ComposerKt.sourceInformationMarkerStart($composer3, -1705324236, "CC(remember):CoreTextField.kt#9igjgp");
            if (($dirty & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            if (($dirty & 57344) == 16384) {
                z2 = true;
            } else {
                z2 = false;
            }
            invalid$iv = z | z2;
            Object it$iv20 = $composer3.rememberedValue();
            if (invalid$iv) {
            }
            transformed = ValidatingOffsetMappingKt.filterWithValidation(visualTransformation2, value.getText());
            composition = value.getComposition();
            if (composition != null) {
                interactionSource3 = interactionSource2;
                long it2 = composition.getPackedValue();
                imeOptions4 = imeOptions3;
                transformedTextM1419applyCompositionDecoration72CqOWE = TextFieldDelegate.INSTANCE.m1419applyCompositionDecoration72CqOWE(it2, transformed);
                if (transformedTextM1419applyCompositionDecoration72CqOWE == null) {
                }
                value$iv4 = transformedTextM1419applyCompositionDecoration72CqOWE;
                $composer3.updateRememberedValue(value$iv4);
                final TransformedText transformedText2 = (TransformedText) value$iv4;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                AnnotatedString visualText2 = transformedText2.getText();
                final OffsetMapping offsetMapping2 = transformedText2.getOffsetMapping();
                RecomposeScope scope2 = ComposablesKt.getCurrentRecomposeScope($composer3, 0);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705304518, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv2 = $composer3.changed(keyboardController);
                Object it$iv21 = $composer3.rememberedValue();
                if (invalid$iv2) {
                }
                value$iv5 = new LegacyTextFieldState(new TextDelegate(visualText2, textStyle2, 0, 0, softWrap2, 0, density, fontFamilyResolver, null, AnimationConstants.DefaultDurationMillis, null), scope2, keyboardController);
                $composer3.updateRememberedValue(value$iv5);
                state = (LegacyTextFieldState) value$iv5;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                state.m1375updatefnh65Uc(value.getText(), visualText2, textStyle2, softWrap2, density, fontFamilyResolver, function1, keyboardActions2, focusManager, selectionBackgroundColor);
                state.getProcessor().reset(value, state.getInputSession());
                ComposerKt.sourceInformationMarkerStart($composer3, -1705279231, "CC(remember):CoreTextField.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv6 = new UndoManager(0, 1, null);
                    $composer3.updateRememberedValue(value$iv6);
                } else {
                    value$iv6 = it$iv;
                }
                undoManager = (UndoManager) value$iv6;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                UndoManager.snapshotIfNeeded$default(undoManager, value, 0L, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705276454, "CC(remember):CoreTextField.kt#9igjgp");
                it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv7 = new TextFieldSelectionManager(undoManager);
                    $composer3.updateRememberedValue(value$iv7);
                } else {
                    value$iv7 = it$iv2;
                }
                manager = (TextFieldSelectionManager) value$iv7;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                manager.setOffsetMapping$foundation_release(offsetMapping2);
                manager.setVisualTransformation$foundation_release(visualTransformation2);
                manager.setOnValueChange$foundation_release(state.getOnValueChange());
                manager.setState$foundation_release(state);
                manager.setValue$foundation_release(value);
                ProvidableCompositionLocal<ClipboardManager> localClipboardManager2 = CompositionLocalsKt.getLocalClipboardManager();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume10 = $composer3.consume(localClipboardManager2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                manager.setClipboardManager$foundation_release((ClipboardManager) objConsume10);
                ProvidableCompositionLocal<TextToolbar> localTextToolbar2 = CompositionLocalsKt.getLocalTextToolbar();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume11 = $composer3.consume(localTextToolbar2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                manager.setTextToolbar((TextToolbar) objConsume11);
                ProvidableCompositionLocal<HapticFeedback> localHapticFeedback2 = CompositionLocalsKt.getLocalHapticFeedback();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume12 = $composer3.consume(localHapticFeedback2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                manager.setHapticFeedBack((HapticFeedback) objConsume12);
                manager.setFocusRequester(focusRequester);
                manager.setEditable(!readOnly2);
                manager.setEnabled(enabled2);
                ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
                value$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                    $composer3.updateRememberedValue(value$iv$iv);
                }
                CompositionScopedCoroutineScopeCanceller wrapper$iv2 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final CoroutineScope coroutineScope2 = wrapper$iv2.getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705256948, "CC(remember):CoreTextField.kt#9igjgp");
                it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv8 = BringIntoViewRequesterKt.BringIntoViewRequester();
                    $composer3.updateRememberedValue(value$iv8);
                } else {
                    value$iv8 = it$iv3;
                }
                final BringIntoViewRequester bringIntoViewRequester2 = (BringIntoViewRequester) value$iv8;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier.Companion companion14 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705248170, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance13 = $composer3.changedInstance(state);
                final VisualTransformation visualTransformation6 = visualTransformation2;
                if (($dirty1 & 7168) == 2048) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z110 = zChangedInstance13 | z3;
                if (($dirty1 & 57344) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean zChangedInstance14 = z110 | z4 | $composer3.changedInstance(textInputService);
                if (($dirty & 14) == 4) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean z219 = zChangedInstance14 | z5;
                if ((($dirty1 & 112) ^ 48) > 32) {
                    imeOptions5 = imeOptions4;
                    if ($composer3.changed(imeOptions5)) {
                        z6 = true;
                    }
                    invalid$iv3 = z219 | z6 | $composer3.changedInstance(offsetMapping2) | $composer3.changedInstance(coroutineScope2) | $composer3.changedInstance(bringIntoViewRequester2) | $composer3.changedInstance(manager);
                    Object it$iv117 = $composer3.rememberedValue();
                    if (invalid$iv3) {
                    }
                    final boolean z2110 = enabled2;
                    final boolean z2111 = readOnly2;
                    final ImeOptions imeOptions12 = imeOptions5;
                    value$iv9 = new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$focusModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState it3) {
                            TextLayoutResultProxy layoutResult;
                            if (state.getHasFocus() == it3.isFocused()) {
                                return;
                            }
                            state.setHasFocus(it3.isFocused());
                            if (!state.getHasFocus() || !z2110 || z2111) {
                                CoreTextFieldKt.endInputSession(state);
                            } else {
                                CoreTextFieldKt.startInputSession(textInputService, state, value, imeOptions12, offsetMapping2);
                            }
                            if (it3.isFocused() && (layoutResult = state.getLayoutResult()) != null) {
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1$1(bringIntoViewRequester2, value, state, layoutResult, offsetMapping2, null), 3, null);
                            }
                            if (!it3.isFocused()) {
                                TextFieldSelectionManager.m1748deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                            }
                        }
                    };
                    $composer3.updateRememberedValue(value$iv9);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    MutableInteractionSource interactionSource8 = interactionSource3;
                    Modifier focusModifier3 = TextFieldGestureModifiersKt.textFieldFocusModifier(companion14, enabled2, focusRequester, interactionSource8, (Function1) value$iv9);
                    if (enabled2) {
                        z7 = false;
                    } else {
                        z7 = false;
                    }
                    State writeable$delegate3 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(z7), $composer3, 0);
                    Unit unit3 = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705195503, "CC(remember):CoreTextField.kt#9igjgp");
                    invalid$iv4 = $composer3.changed(writeable$delegate3) | $composer3.changedInstance(state) | $composer3.changedInstance(textInputService) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                    Object it$iv118 = $composer3.rememberedValue();
                    if (invalid$iv4) {
                    }
                    value$iv10 = new CoreTextFieldKt$CoreTextField$2$1(state, writeable$delegate3, textInputService, manager, imeOptions5, null);
                    $composer3.updateRememberedValue(value$iv10);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    EffectsKt.LaunchedEffect(unit3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv10, $composer3, 6);
                    Modifier.Companion companion15 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705163133, "CC(remember):CoreTextField.kt#9igjgp");
                    invalid$iv5 = $composer3.changedInstance(state);
                    value$iv11 = $composer3.rememberedValue();
                    if (!invalid$iv5) {
                    }
                    value$iv11 = (Function1) new Function1<Boolean, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$1$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                            invoke(bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean it3) {
                            state.setInTouchMode(it3);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv11);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierUpdateSelectionTouchMode3 = SelectionGesturesKt.updateSelectionTouchMode(companion15, (Function1) value$iv11);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705159310, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance15 = $composer3.changedInstance(state);
                    if (($dirty1 & 57344) == 16384) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean z2112 = zChangedInstance15 | z8;
                    if (($dirty1 & 7168) == 2048) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    invalid$iv6 = z2112 | z9 | $composer3.changedInstance(offsetMapping2) | $composer3.changedInstance(manager);
                    Object it$iv119 = $composer3.rememberedValue();
                    if (invalid$iv6) {
                    }
                    final boolean z2113 = readOnly2;
                    final boolean z2114 = enabled2;
                    value$iv12 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                            m1345invokek4lQ0M(offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                        public final void m1345invokek4lQ0M(long offset) {
                            CoreTextFieldKt.tapToFocus(state, focusRequester, !z2113);
                            if (state.getHasFocus() && z2114) {
                                if (state.getHandleState() != HandleState.Selection) {
                                    TextLayoutResultProxy layoutResult = state.getLayoutResult();
                                    if (layoutResult != null) {
                                        LegacyTextFieldState legacyTextFieldState = state;
                                        TextFieldDelegate.INSTANCE.m1422setCursorOffsetULxng0E$foundation_release(offset, layoutResult, legacyTextFieldState.getProcessor(), offsetMapping2, legacyTextFieldState.getOnValueChange());
                                        if (legacyTextFieldState.getTextDelegate().getText().length() > 0) {
                                            legacyTextFieldState.setHandleState(HandleState.Cursor);
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                }
                                manager.m1752deselect_kEHs6E$foundation_release(Offset.m4282boximpl(offset));
                            }
                        }
                    };
                    $composer3.updateRememberedValue(value$iv12);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(modifierUpdateSelectionTouchMode3, interactionSource8, enabled2, (Function1) value$iv12), manager.getMouseSelectionObserver(), manager.getTouchSelectionObserver()), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
                    Modifier.Companion companion16 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705122293, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance16 = $composer3.changedInstance(state);
                    if (($dirty & 14) == 4) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    invalid$iv7 = zChangedInstance16 | z10 | $composer3.changedInstance(offsetMapping2);
                    Object it$iv1110 = $composer3.rememberedValue();
                    if (invalid$iv7) {
                    }
                    value$iv13 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$drawModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                            invoke2(drawScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DrawScope $this$drawBehind) {
                            TextLayoutResultProxy layoutResult = state.getLayoutResult();
                            if (layoutResult != null) {
                                TextFieldValue textFieldValue = value;
                                LegacyTextFieldState legacyTextFieldState = state;
                                OffsetMapping offsetMapping3 = offsetMapping2;
                                Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
                                TextFieldDelegate.INSTANCE.m1420drawQ1vqE60$foundation_release(canvas, textFieldValue, legacyTextFieldState.m1370getSelectionPreviewHighlightRanged9O1mEE(), legacyTextFieldState.m1367getDeletionPreviewHighlightRanged9O1mEE(), offsetMapping3, layoutResult.getValue(), legacyTextFieldState.getHighlightPaint(), legacyTextFieldState.getSelectionBackgroundColor());
                            }
                        }
                    };
                    $composer3.updateRememberedValue(value$iv13);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final Modifier drawModifier3 = DrawModifierKt.drawBehind(companion16, (Function1) value$iv13);
                    Modifier.Companion companion17 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705102808, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance17 = $composer3.changedInstance(state);
                    if (($dirty1 & 7168) == 2048) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    windowInfo2 = windowInfo;
                    boolean zChanged5 = zChangedInstance17 | z11 | $composer3.changed(windowInfo2) | $composer3.changedInstance(manager);
                    if (($dirty & 14) == 4) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                    invalid$iv8 = zChanged5 | z12 | $composer3.changedInstance(offsetMapping2);
                    Object it$iv1111 = $composer3.rememberedValue();
                    if (invalid$iv8) {
                    }
                    final boolean z2115 = enabled2;
                    value$iv14 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$onPositionedModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                            invoke2(layoutCoordinates);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(LayoutCoordinates it3) {
                            state.setLayoutCoordinates(it3);
                            TextLayoutResultProxy layoutResult = state.getLayoutResult();
                            if (layoutResult != null) {
                                layoutResult.setInnerTextFieldCoordinates(it3);
                            }
                            if (z2115) {
                                if (state.getHandleState() == HandleState.Selection) {
                                    if (state.getShowFloatingToolbar() && windowInfo2.isWindowFocused()) {
                                        manager.showSelectionToolbar$foundation_release();
                                    } else {
                                        manager.hideSelectionToolbar$foundation_release();
                                    }
                                    state.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                                    state.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, false));
                                    state.setShowCursorHandle(TextRange.m6473getCollapsedimpl(value.getSelection()));
                                } else if (state.getHandleState() == HandleState.Cursor) {
                                    state.setShowCursorHandle(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                                }
                                CoreTextFieldKt.notifyFocusedRect(state, value, offsetMapping2);
                                TextLayoutResultProxy layoutResult2 = state.getLayoutResult();
                                if (layoutResult2 != null) {
                                    LegacyTextFieldState legacyTextFieldState = state;
                                    TextFieldValue textFieldValue = value;
                                    OffsetMapping offsetMapping3 = offsetMapping2;
                                    TextInputSession inputSession = legacyTextFieldState.getInputSession();
                                    if (inputSession != null && legacyTextFieldState.getHasFocus()) {
                                        TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation_release(inputSession, textFieldValue, offsetMapping3, layoutResult2);
                                    }
                                }
                            }
                        }
                    };
                    $composer3.updateRememberedValue(value$iv14);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final Modifier onPositionedModifier3 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion17, (Function1) value$iv14);
                    final boolean isPassword3 = visualTransformation6 instanceof PasswordVisualTransformation;
                    Modifier.Companion companion18 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1705046115, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance18 = $composer3.changedInstance(transformedText2);
                    if (($dirty & 14) == 4) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    boolean z2116 = zChangedInstance18 | z13;
                    if (($dirty1 & 7168) == 2048) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean zChanged6 = z14 | z2116 | $composer3.changed(isPassword3);
                    if ((57344 & $dirty1) == 16384) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    invalid$iv9 = zChanged6 | z15 | $composer3.changedInstance(state) | $composer3.changedInstance(offsetMapping2) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                    Object it$iv1112 = $composer3.rememberedValue();
                    if (invalid$iv9) {
                    }
                    final boolean z2117 = enabled2;
                    final boolean z2118 = readOnly2;
                    final ImeOptions imeOptions13 = imeOptions5;
                    value$iv15 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(final SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.setEditableText($this$semantics, transformedText2.getText());
                            SemanticsPropertiesKt.m6309setTextSelectionRangeFDrldGo($this$semantics, value.getSelection());
                            if (!z2117) {
                                SemanticsPropertiesKt.disabled($this$semantics);
                            }
                            if (isPassword3) {
                                SemanticsPropertiesKt.password($this$semantics);
                            }
                            boolean editable = z2117 && !z2118;
                            SemanticsPropertiesKt.setEditable($this$semantics, editable);
                            final LegacyTextFieldState legacyTextFieldState = state;
                            SemanticsPropertiesKt.getTextLayoutResult$default($this$semantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(List<TextLayoutResult> list) {
                                    boolean z38;
                                    if (legacyTextFieldState.getLayoutResult() != null) {
                                        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                                        Intrinsics.checkNotNull(layoutResult);
                                        list.add(layoutResult.getValue());
                                        z38 = true;
                                    } else {
                                        z38 = false;
                                    }
                                    return Boolean.valueOf(z38);
                                }
                            }, 1, null);
                            if (editable) {
                                final LegacyTextFieldState legacyTextFieldState2 = state;
                                SemanticsPropertiesKt.setText$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(AnnotatedString text) {
                                        Unit unit4;
                                        TextInputSession session = legacyTextFieldState2.getInputSession();
                                        if (session != null) {
                                            LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState2;
                                            TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new DeleteAllCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState3.getProcessor(), legacyTextFieldState3.getOnValueChange(), session);
                                            unit4 = Unit.INSTANCE;
                                        } else {
                                            unit4 = null;
                                        }
                                        if (unit4 == null) {
                                            SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                            legacyTextFieldState2.getOnValueChange().invoke(new TextFieldValue(text.getText(), TextRangeKt.TextRange(text.getText().length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                        }
                                        return true;
                                    }
                                }, 1, null);
                                final boolean z38 = z2118;
                                final boolean z39 = z2117;
                                final LegacyTextFieldState legacyTextFieldState3 = state;
                                final TextFieldValue textFieldValue = value;
                                SemanticsPropertiesKt.insertTextAtCursor$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(AnnotatedString text) {
                                        Unit unit4;
                                        if (z38 || !z39) {
                                            return false;
                                        }
                                        TextInputSession session = legacyTextFieldState3.getInputSession();
                                        if (session != null) {
                                            LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                            TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new FinishComposingTextCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState4.getProcessor(), legacyTextFieldState4.getOnValueChange(), session);
                                            unit4 = Unit.INSTANCE;
                                        } else {
                                            unit4 = null;
                                        }
                                        if (unit4 == null) {
                                            SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                            TextFieldValue textFieldValue2 = textFieldValue;
                                            LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState3;
                                            String newText = StringsKt.replaceRange((CharSequence) textFieldValue2.getText(), TextRange.m6479getStartimpl(textFieldValue2.getSelection()), TextRange.m6474getEndimpl(textFieldValue2.getSelection()), (CharSequence) text).toString();
                                            long newCursor = TextRangeKt.TextRange(TextRange.m6479getStartimpl(textFieldValue2.getSelection()) + text.length());
                                            legacyTextFieldState5.getOnValueChange().invoke(new TextFieldValue(newText, newCursor, (TextRange) null, 4, (DefaultConstructorMarker) null));
                                        }
                                        return true;
                                    }
                                }, 1, null);
                            }
                            final OffsetMapping offsetMapping3 = offsetMapping2;
                            final boolean z310 = z2117;
                            final TextFieldValue textFieldValue2 = value;
                            final TextFieldSelectionManager textFieldSelectionManager = manager;
                            final LegacyTextFieldState legacyTextFieldState4 = state;
                            SemanticsPropertiesKt.setSelection$default($this$semantics, null, new Function3<Integer, Integer, Boolean, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Integer num2, Boolean bool) {
                                    return invoke(num.intValue(), num2.intValue(), bool.booleanValue());
                                }

                                public final Boolean invoke(int selectionStart, int selectionEnd, boolean relativeToOriginalText) {
                                    int start;
                                    int end;
                                    if (relativeToOriginalText) {
                                        start = selectionStart;
                                    } else {
                                        start = offsetMapping3.transformedToOriginal(selectionStart);
                                    }
                                    if (relativeToOriginalText) {
                                        end = selectionEnd;
                                    } else {
                                        end = offsetMapping3.transformedToOriginal(selectionEnd);
                                    }
                                    boolean z311 = false;
                                    if (z310 && (start != TextRange.m6479getStartimpl(textFieldValue2.getSelection()) || end != TextRange.m6474getEndimpl(textFieldValue2.getSelection()))) {
                                        if (Math.min(start, end) >= 0 && Math.max(start, end) <= textFieldValue2.getText().length()) {
                                            if (!relativeToOriginalText && start != end) {
                                                TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager, false, 1, null);
                                            } else {
                                                textFieldSelectionManager.exitSelectionMode$foundation_release();
                                            }
                                            legacyTextFieldState4.getOnValueChange().invoke(new TextFieldValue(textFieldValue2.getText(), TextRangeKt.TextRange(start, end), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                            z311 = true;
                                        } else {
                                            textFieldSelectionManager.exitSelectionMode$foundation_release();
                                        }
                                    }
                                    return Boolean.valueOf(z311);
                                }
                            }, 1, null);
                            int imeAction = imeOptions13.getImeAction();
                            final LegacyTextFieldState legacyTextFieldState5 = state;
                            final ImeOptions imeOptions14 = imeOptions13;
                            SemanticsPropertiesKt.m6304onImeAction9UiTYpY$default($this$semantics, imeAction, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.5
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    legacyTextFieldState5.getOnImeActionPerformed().invoke(ImeAction.m6641boximpl(imeOptions14.getImeAction()));
                                    return true;
                                }
                            }, 2, null);
                            final LegacyTextFieldState legacyTextFieldState6 = state;
                            final FocusRequester focusRequester2 = focusRequester;
                            final boolean z311 = z2118;
                            SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.6
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    CoreTextFieldKt.tapToFocus(legacyTextFieldState6, focusRequester2, !z311);
                                    return true;
                                }
                            }, 1, null);
                            final TextFieldSelectionManager textFieldSelectionManager2 = manager;
                            SemanticsPropertiesKt.onLongClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.7
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager2, false, 1, null);
                                    return true;
                                }
                            }, 1, null);
                            if (!TextRange.m6473getCollapsedimpl(value.getSelection()) && !isPassword3) {
                                final TextFieldSelectionManager textFieldSelectionManager3 = manager;
                                SemanticsPropertiesKt.copyText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.8
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        TextFieldSelectionManager.copy$foundation_release$default(textFieldSelectionManager3, false, 1, null);
                                        return true;
                                    }
                                }, 1, null);
                                if (z2117 && !z2118) {
                                    final TextFieldSelectionManager textFieldSelectionManager4 = manager;
                                    SemanticsPropertiesKt.cutText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.9
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            textFieldSelectionManager4.cut$foundation_release();
                                            return true;
                                        }
                                    }, 1, null);
                                }
                            }
                            if (z2117 && !z2118) {
                                final TextFieldSelectionManager textFieldSelectionManager5 = manager;
                                SemanticsPropertiesKt.pasteText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.10
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        textFieldSelectionManager5.paste$foundation_release();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        }
                    };
                    $composer3.updateRememberedValue(value$iv15);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier semanticsModifier3 = SemanticsModifierKt.semantics(companion18, true, (Function1) value$iv15);
                    if (enabled2) {
                        z16 = false;
                    } else {
                        z16 = false;
                    }
                    boolean showCursor3 = z16;
                    final Modifier cursorModifier3 = TextFieldCursorKt.cursor(Modifier.INSTANCE, state, value, offsetMapping2, cursorBrush2, showCursor3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1704832829, "CC(remember):CoreTextField.kt#9igjgp");
                    invalid$iv10 = $composer3.changedInstance(manager);
                    Object it$iv1113 = $composer3.rememberedValue();
                    if (invalid$iv10) {
                    }
                    value$iv16 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                            final TextFieldSelectionManager textFieldSelectionManager = manager;
                            return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                                }
                            };
                        }
                    };
                    $composer3.updateRememberedValue(value$iv16);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    EffectsKt.DisposableEffect(manager, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv16, $composer3, 0);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1704829391, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean zChangedInstance19 = $composer3.changedInstance(state) | $composer3.changedInstance(textInputService);
                    if (($dirty & 14) == 4) {
                        z17 = true;
                    } else {
                        z17 = false;
                    }
                    invalid$iv11 = zChangedInstance19 | z17 | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                    Object it$iv1114 = $composer3.rememberedValue();
                    if (invalid$iv11) {
                    }
                    value$iv17 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                            if (state.getHasFocus()) {
                                state.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation_release(textInputService, value, state.getProcessor(), imeOptions5, state.getOnValueChange(), state.getOnImeActionPerformed()));
                            }
                            return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                }
                            };
                        }
                    };
                    $composer3.updateRememberedValue(value$iv17);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    EffectsKt.DisposableEffect(imeOptions5, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv17, $composer3, ($dirty1 >> 3) & 14);
                    Modifier.Companion companion19 = Modifier.INSTANCE;
                    Function1<TextFieldValue, Unit> onValueChange3 = state.getOnValueChange();
                    boolean z38 = !readOnly2;
                    if (maxLines5 == 1) {
                        z18 = true;
                    } else {
                        z18 = false;
                    }
                    showHandleAndMagnifier = false;
                    imeOptions6 = imeOptions5;
                    boolean z39 = z18;
                    int $dirty5 = $dirty1;
                    enabled3 = enabled2;
                    Modifier textKeyInputModifier3 = TextFieldKeyInputKt.m1429textFieldKeyInput2WJ9YEU(companion19, state, manager, value, onValueChange3, z38, z39, offsetMapping2, undoManager, imeOptions5.getImeAction());
                    Modifier.Companion companion110 = Modifier.INSTANCE;
                    boolean zCoreTextField$lambda$13 = CoreTextField$lambda$11(writeable$delegate3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1704798381, "CC(remember):CoreTextField.kt#9igjgp");
                    invalid$iv12 = $composer3.changedInstance(state) | (((($dirty5 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions6)) || ($dirty5 & 48) == 32) | $composer3.changedInstance(legacyTextInputServiceAdapter);
                    Object it$iv1115 = $composer3.rememberedValue();
                    if (invalid$iv12) {
                        value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                if (!state.getHasFocus()) {
                                    focusRequester.requestFocus();
                                }
                                if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                                    legacyTextInputServiceAdapter.startStylusHandwriting();
                                }
                                return true;
                            }
                        };
                        $composer3.updateRememberedValue(value$iv18);
                    } else {
                        value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                if (!state.getHasFocus()) {
                                    focusRequester.requestFocus();
                                }
                                if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                                    legacyTextInputServiceAdapter.startStylusHandwriting();
                                }
                                return true;
                            }
                        };
                        $composer3.updateRememberedValue(value$iv18);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier stylusHandwritingModifier3 = StylusHandwritingKt.stylusHandwriting(companion110, zCoreTextField$lambda$13, (Function0) value$iv18);
                    Modifier decorationBoxModifier3 = OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier5, legacyTextInputServiceAdapter, state, manager).then(stylusHandwritingModifier3).then(focusModifier3), state, focusManager), state, manager).then(textKeyInputModifier3), scrollerPosition2, interactionSource8, enabled3).then(r15).then(semanticsModifier3), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                            invoke2(layoutCoordinates);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(LayoutCoordinates it3) {
                            TextLayoutResultProxy layoutResult = state.getLayoutResult();
                            if (layoutResult == null) {
                                return;
                            }
                            layoutResult.setDecorationBoxCoordinates(it3);
                        }
                    });
                    if (enabled3) {
                        showHandleAndMagnifier = true;
                    }
                    if (showHandleAndMagnifier) {
                        companionTextFieldMagnifier = TextFieldSelectionManager_androidKt.textFieldMagnifier(Modifier.INSTANCE, manager);
                    } else {
                        companionTextFieldMagnifier = Modifier.INSTANCE;
                    }
                    maxLines3 = maxLines5;
                    final Modifier magnifierModifier3 = companionTextFieldMagnifier;
                    final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function11 = function3M1344getLambda1$foundation_release;
                    final TextStyle textStyle6 = textStyle2;
                    final int i17 = minLines2;
                    enabled4 = enabled3;
                    imeOptions7 = imeOptions6;
                    interactionSource4 = interactionSource8;
                    visualTransformation3 = visualTransformation6;
                    modifier3 = modifier5;
                    final boolean z310 = showHandleAndMagnifier;
                    final boolean z311 = readOnly2;
                    final Function1<? super TextLayoutResult, Unit> function12 = function4;
                    Function2<Composer, Integer, Unit> function13 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5
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

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C695@31947L4735,695@31933L4749:CoreTextField.kt#423gt5");
                            if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-374338080, $changed2, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:695)");
                                }
                                Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> function14 = function11;
                                final LegacyTextFieldState legacyTextFieldState = state;
                                final TextStyle textStyle7 = textStyle6;
                                final int i18 = i17;
                                final int i19 = maxLines3;
                                final TextFieldScrollerPosition textFieldScrollerPosition = scrollerPosition2;
                                final TextFieldValue textFieldValue = value;
                                final VisualTransformation visualTransformation7 = visualTransformation6;
                                final Modifier modifier6 = cursorModifier3;
                                final Modifier modifier7 = drawModifier3;
                                final Modifier modifier8 = onPositionedModifier3;
                                final Modifier modifier9 = magnifierModifier3;
                                final BringIntoViewRequester bringIntoViewRequester3 = bringIntoViewRequester2;
                                final TextFieldSelectionManager textFieldSelectionManager = manager;
                                final boolean z312 = z310;
                                final boolean z313 = z311;
                                final Function1<? super TextLayoutResult, Unit> function15 = function12;
                                final OffsetMapping offsetMapping3 = offsetMapping2;
                                final Density density2 = density;
                                function14.invoke(ComposableLambdaKt.rememberComposableLambda(2032502107, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1
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

                                    public final void invoke(Composer $composer5, int $changed3) {
                                        Object value$iv20;
                                        ComposerKt.sourceInformation($composer5, "C711@32801L22,720@33160L3512,720@33124L3548:CoreTextField.kt#423gt5");
                                        if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(2032502107, $changed3, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:698)");
                                            }
                                            Modifier modifierHeightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m1067heightInVpY3zN4$default(Modifier.INSTANCE, legacyTextFieldState.m1368getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), textStyle7, i18, i19);
                                            TextFieldScrollerPosition textFieldScrollerPosition2 = textFieldScrollerPosition;
                                            TextFieldValue textFieldValue2 = textFieldValue;
                                            VisualTransformation visualTransformation8 = visualTransformation7;
                                            ComposerKt.sourceInformationMarkerStart($composer5, 713189947, "CC(remember):CoreTextField.kt#9igjgp");
                                            boolean invalid$iv14 = $composer5.changedInstance(legacyTextFieldState);
                                            final LegacyTextFieldState legacyTextFieldState2 = legacyTextFieldState;
                                            Object it$iv1116 = $composer5.rememberedValue();
                                            if (invalid$iv14 || it$iv1116 == Composer.INSTANCE.getEmpty()) {
                                                value$iv20 = new Function0<TextLayoutResultProxy>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$coreTextFieldModifier$1$1
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final TextLayoutResultProxy invoke() {
                                                        return legacyTextFieldState2.getLayoutResult();
                                                    }
                                                };
                                                $composer5.updateRememberedValue(value$iv20);
                                            } else {
                                                value$iv20 = it$iv1116;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd($composer5);
                                            Modifier coreTextFieldModifier = BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScrollKt.textFieldScroll(modifierHeightInLines, textFieldScrollerPosition2, textFieldValue2, visualTransformation8, (Function0) value$iv20).then(modifier6).then(modifier7), textStyle7).then(modifier8).then(modifier9), bringIntoViewRequester3);
                                            final TextFieldSelectionManager textFieldSelectionManager2 = textFieldSelectionManager;
                                            final LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState;
                                            final boolean z314 = z312;
                                            final boolean z315 = z313;
                                            final Function1<? super TextLayoutResult, Unit> function16 = function15;
                                            final TextFieldValue textFieldValue3 = textFieldValue;
                                            final OffsetMapping offsetMapping4 = offsetMapping3;
                                            final Density density3 = density2;
                                            final int i110 = i19;
                                            SimpleLayoutKt.SimpleLayout(coreTextFieldModifier, ComposableLambdaKt.rememberComposableLambda(-363167407, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1
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

                                                /* JADX WARN: Code duplicated, block: B:35:0x0131  */
                                                public final void invoke(Composer $composer6, int $changed4) {
                                                    boolean z316;
                                                    ComposerKt.sourceInformation($composer6, "C721@33178L2880,776@36076L322:CoreTextField.kt#423gt5");
                                                    if (($changed4 & 3) != 2 || !$composer6.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-363167407, $changed4, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous>.<anonymous> (CoreTextField.kt:721)");
                                                        }
                                                        final LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                                        final Function1<? super TextLayoutResult, Unit> function17 = function16;
                                                        final TextFieldValue textFieldValue4 = textFieldValue3;
                                                        final OffsetMapping offsetMapping5 = offsetMapping4;
                                                        final Density density4 = density3;
                                                        final int i111 = i110;
                                                        MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1.2
                                                            @Override // androidx.compose.ui.layout.MeasurePolicy
                                                            /* JADX INFO: renamed from: measure-3p2s80s */
                                                            public MeasureResult mo391measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
                                                                Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                                                                LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState4;
                                                                Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                                                                Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                                                                Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                                                                try {
                                                                    TextLayoutResultProxy prevProxy = legacyTextFieldState5.getLayoutResult();
                                                                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                    TextLayoutResult prevResult = prevProxy != null ? prevProxy.getValue() : null;
                                                                    Triple<Integer, Integer, TextLayoutResult> tripleM1421layout_EkL_Y$foundation_release = TextFieldDelegate.INSTANCE.m1421layout_EkL_Y$foundation_release(legacyTextFieldState4.getTextDelegate(), constraints, $this$measure_u2d3p2s80s.getLayoutDirection(), prevResult);
                                                                    int width = tripleM1421layout_EkL_Y$foundation_release.component1().intValue();
                                                                    int height = tripleM1421layout_EkL_Y$foundation_release.component2().intValue();
                                                                    TextLayoutResult result = tripleM1421layout_EkL_Y$foundation_release.component3();
                                                                    if (!Intrinsics.areEqual(prevResult, result)) {
                                                                        legacyTextFieldState4.setLayoutResult(new TextLayoutResultProxy(result, null, prevProxy != null ? prevProxy.getDecorationBoxCoordinates() : null, 2, null));
                                                                        function17.invoke(result);
                                                                        CoreTextFieldKt.notifyFocusedRect(legacyTextFieldState4, textFieldValue4, offsetMapping5);
                                                                    }
                                                                    LegacyTextFieldState legacyTextFieldState6 = legacyTextFieldState4;
                                                                    Density $this$measure_3p2s80s_u24lambda_u241 = density4;
                                                                    legacyTextFieldState6.m1372setMinHeightForSingleLineField0680j_4($this$measure_3p2s80s_u24lambda_u241.mo723toDpu2uoSUM(i111 == 1 ? TextDelegateKt.ceilToIntPx(result.getLineBottom(0)) : 0));
                                                                    HorizontalAlignmentLine firstBaseline = AlignmentLineKt.getFirstBaseline();
                                                                    float $this$fastRoundToInt$iv = result.getFirstBaseline();
                                                                    HorizontalAlignmentLine lastBaseline = AlignmentLineKt.getLastBaseline();
                                                                    float $this$fastRoundToInt$iv2 = result.getLastBaseline();
                                                                    return $this$measure_u2d3p2s80s.layout(width, height, MapsKt.mapOf(TuplesKt.to(firstBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv))), TuplesKt.to(lastBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv2)))), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1$2$measure$2
                                                                        @Override // kotlin.jvm.functions.Function1
                                                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                            invoke2(placementScope);
                                                                            return Unit.INSTANCE;
                                                                        }

                                                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                        public final void invoke2(Placeable.PlacementScope $this$layout) {
                                                                        }
                                                                    });
                                                                } catch (Throwable th) {
                                                                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                    throw th;
                                                                }
                                                            }

                                                            @Override // androidx.compose.ui.layout.MeasurePolicy
                                                            public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
                                                                legacyTextFieldState4.getTextDelegate().layoutIntrinsics($this$maxIntrinsicWidth.getLayoutDirection());
                                                                return legacyTextFieldState4.getTextDelegate().getMaxIntrinsicWidth();
                                                            }
                                                        };
                                                        ComposerKt.sourceInformationMarkerStart($composer6, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                                        Modifier modifier$iv = Modifier.INSTANCE;
                                                        int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                        CompositionLocalMap localMap$iv = $composer6.getCurrentCompositionLocalMap();
                                                        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer6, modifier$iv);
                                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                        int $changed$iv$iv = ((0 << 6) & 896) | 6;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                        if (!($composer6.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        $composer6.startReusableNode();
                                                        if ($composer6.getInserting()) {
                                                            $composer6.createNode(constructor);
                                                        } else {
                                                            $composer6.useNode();
                                                        }
                                                        Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer6);
                                                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                                                            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                                                            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                                                        }
                                                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                                        int i20 = ($changed$iv$iv >> 6) & 14;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, -1674249312, "C:CoreTextField.kt#423gt5");
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        $composer6.endNode();
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        TextFieldSelectionManager textFieldSelectionManager3 = textFieldSelectionManager2;
                                                        if (legacyTextFieldState3.getHandleState() == HandleState.None || legacyTextFieldState3.getLayoutCoordinates() == null) {
                                                            z316 = false;
                                                        } else {
                                                            LayoutCoordinates layoutCoordinates = legacyTextFieldState3.getLayoutCoordinates();
                                                            Intrinsics.checkNotNull(layoutCoordinates);
                                                            if (layoutCoordinates.isAttached() && z314) {
                                                                z316 = true;
                                                            } else {
                                                                z316 = false;
                                                            }
                                                        }
                                                        CoreTextFieldKt.SelectionToolbarAndHandles(textFieldSelectionManager3, z316, $composer6, 0);
                                                        if (legacyTextFieldState3.getHandleState() == HandleState.Cursor && !z315 && z314) {
                                                            $composer6.startReplaceGroup(-2032274);
                                                            ComposerKt.sourceInformation($composer6, "789@36600L40");
                                                            CoreTextFieldKt.TextFieldCursorHandle(textFieldSelectionManager2, $composer6, 0);
                                                            $composer6.endReplaceGroup();
                                                        } else {
                                                            $composer6.startReplaceGroup(-1955394);
                                                            $composer6.endReplaceGroup();
                                                        }
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    $composer6.skipToGroupEnd();
                                                }
                                            }, $composer5, 54), $composer5, 48, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer5.skipToGroupEnd();
                                    }
                                }, $composer4, 54), $composer4, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    };
                    $composer2 = $composer3;
                    CoreTextFieldRootBox(decorationBoxModifier3, manager, ComposableLambdaKt.rememberComposableLambda(-374338080, true, function13, $composer2, 54), $composer2, 384);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    imeOptions5 = imeOptions4;
                }
                if (($dirty1 & 48) == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                invalid$iv3 = z219 | z6 | $composer3.changedInstance(offsetMapping2) | $composer3.changedInstance(coroutineScope2) | $composer3.changedInstance(bringIntoViewRequester2) | $composer3.changedInstance(manager);
                Object it$iv1116 = $composer3.rememberedValue();
                if (invalid$iv3) {
                }
                final boolean z2119 = enabled2;
                final boolean z21110 = readOnly2;
                final ImeOptions imeOptions14 = imeOptions5;
                value$iv9 = new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$focusModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                        invoke2(focusState);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FocusState it3) {
                        TextLayoutResultProxy layoutResult;
                        if (state.getHasFocus() == it3.isFocused()) {
                            return;
                        }
                        state.setHasFocus(it3.isFocused());
                        if (!state.getHasFocus() || !z2119 || z21110) {
                            CoreTextFieldKt.endInputSession(state);
                        } else {
                            CoreTextFieldKt.startInputSession(textInputService, state, value, imeOptions14, offsetMapping2);
                        }
                        if (it3.isFocused() && (layoutResult = state.getLayoutResult()) != null) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1$1(bringIntoViewRequester2, value, state, layoutResult, offsetMapping2, null), 3, null);
                        }
                        if (!it3.isFocused()) {
                            TextFieldSelectionManager.m1748deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv9);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                MutableInteractionSource interactionSource9 = interactionSource3;
                Modifier focusModifier4 = TextFieldGestureModifiersKt.textFieldFocusModifier(companion14, enabled2, focusRequester, interactionSource9, (Function1) value$iv9);
                if (enabled2) {
                    z7 = false;
                } else {
                    z7 = false;
                }
                State writeable$delegate4 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(z7), $composer3, 0);
                Unit unit4 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705195503, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv4 = $composer3.changed(writeable$delegate4) | $composer3.changedInstance(state) | $composer3.changedInstance(textInputService) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                Object it$iv1117 = $composer3.rememberedValue();
                if (invalid$iv4) {
                }
                value$iv10 = new CoreTextFieldKt$CoreTextField$2$1(state, writeable$delegate4, textInputService, manager, imeOptions5, null);
                $composer3.updateRememberedValue(value$iv10);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(unit4, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv10, $composer3, 6);
                Modifier.Companion companion111 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705163133, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv5 = $composer3.changedInstance(state);
                value$iv11 = $composer3.rememberedValue();
                if (!invalid$iv5) {
                }
                value$iv11 = (Function1) new Function1<Boolean, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean it3) {
                        state.setInTouchMode(it3);
                    }
                };
                $composer3.updateRememberedValue(value$iv11);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierUpdateSelectionTouchMode4 = SelectionGesturesKt.updateSelectionTouchMode(companion111, (Function1) value$iv11);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705159310, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance110 = $composer3.changedInstance(state);
                if (($dirty1 & 57344) == 16384) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean z21111 = zChangedInstance110 | z8;
                if (($dirty1 & 7168) == 2048) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                invalid$iv6 = z21111 | z9 | $composer3.changedInstance(offsetMapping2) | $composer3.changedInstance(manager);
                Object it$iv1118 = $composer3.rememberedValue();
                if (invalid$iv6) {
                }
                final boolean z21112 = readOnly2;
                final boolean z21113 = enabled2;
                value$iv12 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m1345invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m1345invokek4lQ0M(long offset) {
                        CoreTextFieldKt.tapToFocus(state, focusRequester, !z21112);
                        if (state.getHasFocus() && z21113) {
                            if (state.getHandleState() != HandleState.Selection) {
                                TextLayoutResultProxy layoutResult = state.getLayoutResult();
                                if (layoutResult != null) {
                                    LegacyTextFieldState legacyTextFieldState = state;
                                    TextFieldDelegate.INSTANCE.m1422setCursorOffsetULxng0E$foundation_release(offset, layoutResult, legacyTextFieldState.getProcessor(), offsetMapping2, legacyTextFieldState.getOnValueChange());
                                    if (legacyTextFieldState.getTextDelegate().getText().length() > 0) {
                                        legacyTextFieldState.setHandleState(HandleState.Cursor);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            manager.m1752deselect_kEHs6E$foundation_release(Offset.m4282boximpl(offset));
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv12);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(modifierUpdateSelectionTouchMode4, interactionSource9, enabled2, (Function1) value$iv12), manager.getMouseSelectionObserver(), manager.getTouchSelectionObserver()), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
                Modifier.Companion companion112 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705122293, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance111 = $composer3.changedInstance(state);
                if (($dirty & 14) == 4) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                invalid$iv7 = zChangedInstance111 | z10 | $composer3.changedInstance(offsetMapping2);
                Object it$iv1119 = $composer3.rememberedValue();
                if (invalid$iv7) {
                }
                value$iv13 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$drawModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$drawBehind) {
                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                        if (layoutResult != null) {
                            TextFieldValue textFieldValue = value;
                            LegacyTextFieldState legacyTextFieldState = state;
                            OffsetMapping offsetMapping3 = offsetMapping2;
                            Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
                            TextFieldDelegate.INSTANCE.m1420drawQ1vqE60$foundation_release(canvas, textFieldValue, legacyTextFieldState.m1370getSelectionPreviewHighlightRanged9O1mEE(), legacyTextFieldState.m1367getDeletionPreviewHighlightRanged9O1mEE(), offsetMapping3, layoutResult.getValue(), legacyTextFieldState.getHighlightPaint(), legacyTextFieldState.getSelectionBackgroundColor());
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv13);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Modifier drawModifier4 = DrawModifierKt.drawBehind(companion112, (Function1) value$iv13);
                Modifier.Companion companion113 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705102808, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance112 = $composer3.changedInstance(state);
                if (($dirty1 & 7168) == 2048) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                windowInfo2 = windowInfo;
                boolean zChanged7 = zChangedInstance112 | z11 | $composer3.changed(windowInfo2) | $composer3.changedInstance(manager);
                if (($dirty & 14) == 4) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                invalid$iv8 = zChanged7 | z12 | $composer3.changedInstance(offsetMapping2);
                Object it$iv11110 = $composer3.rememberedValue();
                if (invalid$iv8) {
                }
                final boolean z21114 = enabled2;
                value$iv14 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$onPositionedModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates it3) {
                        state.setLayoutCoordinates(it3);
                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                        if (layoutResult != null) {
                            layoutResult.setInnerTextFieldCoordinates(it3);
                        }
                        if (z21114) {
                            if (state.getHandleState() == HandleState.Selection) {
                                if (state.getShowFloatingToolbar() && windowInfo2.isWindowFocused()) {
                                    manager.showSelectionToolbar$foundation_release();
                                } else {
                                    manager.hideSelectionToolbar$foundation_release();
                                }
                                state.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                                state.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, false));
                                state.setShowCursorHandle(TextRange.m6473getCollapsedimpl(value.getSelection()));
                            } else if (state.getHandleState() == HandleState.Cursor) {
                                state.setShowCursorHandle(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                            }
                            CoreTextFieldKt.notifyFocusedRect(state, value, offsetMapping2);
                            TextLayoutResultProxy layoutResult2 = state.getLayoutResult();
                            if (layoutResult2 != null) {
                                LegacyTextFieldState legacyTextFieldState = state;
                                TextFieldValue textFieldValue = value;
                                OffsetMapping offsetMapping3 = offsetMapping2;
                                TextInputSession inputSession = legacyTextFieldState.getInputSession();
                                if (inputSession != null && legacyTextFieldState.getHasFocus()) {
                                    TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation_release(inputSession, textFieldValue, offsetMapping3, layoutResult2);
                                }
                            }
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv14);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Modifier onPositionedModifier4 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion113, (Function1) value$iv14);
                final boolean isPassword4 = visualTransformation6 instanceof PasswordVisualTransformation;
                Modifier.Companion companion114 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705046115, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance113 = $composer3.changedInstance(transformedText2);
                if (($dirty & 14) == 4) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                boolean z21115 = zChangedInstance113 | z13;
                if (($dirty1 & 7168) == 2048) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean zChanged8 = z14 | z21115 | $composer3.changed(isPassword4);
                if ((57344 & $dirty1) == 16384) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                invalid$iv9 = zChanged8 | z15 | $composer3.changedInstance(state) | $composer3.changedInstance(offsetMapping2) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                Object it$iv11111 = $composer3.rememberedValue();
                if (invalid$iv9) {
                }
                final boolean z21116 = enabled2;
                final boolean z21117 = readOnly2;
                final ImeOptions imeOptions15 = imeOptions5;
                value$iv15 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setEditableText($this$semantics, transformedText2.getText());
                        SemanticsPropertiesKt.m6309setTextSelectionRangeFDrldGo($this$semantics, value.getSelection());
                        if (!z21116) {
                            SemanticsPropertiesKt.disabled($this$semantics);
                        }
                        if (isPassword4) {
                            SemanticsPropertiesKt.password($this$semantics);
                        }
                        boolean editable = z21116 && !z21117;
                        SemanticsPropertiesKt.setEditable($this$semantics, editable);
                        final LegacyTextFieldState legacyTextFieldState = state;
                        SemanticsPropertiesKt.getTextLayoutResult$default($this$semantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(List<TextLayoutResult> list) {
                                boolean z312;
                                if (legacyTextFieldState.getLayoutResult() != null) {
                                    TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                                    Intrinsics.checkNotNull(layoutResult);
                                    list.add(layoutResult.getValue());
                                    z312 = true;
                                } else {
                                    z312 = false;
                                }
                                return Boolean.valueOf(z312);
                            }
                        }, 1, null);
                        if (editable) {
                            final LegacyTextFieldState legacyTextFieldState2 = state;
                            SemanticsPropertiesKt.setText$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(AnnotatedString text) {
                                    Unit unit5;
                                    TextInputSession session = legacyTextFieldState2.getInputSession();
                                    if (session != null) {
                                        LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState2;
                                        TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new DeleteAllCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState3.getProcessor(), legacyTextFieldState3.getOnValueChange(), session);
                                        unit5 = Unit.INSTANCE;
                                    } else {
                                        unit5 = null;
                                    }
                                    if (unit5 == null) {
                                        SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                        legacyTextFieldState2.getOnValueChange().invoke(new TextFieldValue(text.getText(), TextRangeKt.TextRange(text.getText().length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                    }
                                    return true;
                                }
                            }, 1, null);
                            final boolean z312 = z21117;
                            final boolean z313 = z21116;
                            final LegacyTextFieldState legacyTextFieldState3 = state;
                            final TextFieldValue textFieldValue = value;
                            SemanticsPropertiesKt.insertTextAtCursor$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(AnnotatedString text) {
                                    Unit unit5;
                                    if (z312 || !z313) {
                                        return false;
                                    }
                                    TextInputSession session = legacyTextFieldState3.getInputSession();
                                    if (session != null) {
                                        LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                        TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new FinishComposingTextCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState4.getProcessor(), legacyTextFieldState4.getOnValueChange(), session);
                                        unit5 = Unit.INSTANCE;
                                    } else {
                                        unit5 = null;
                                    }
                                    if (unit5 == null) {
                                        SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                        TextFieldValue textFieldValue2 = textFieldValue;
                                        LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState3;
                                        String newText = StringsKt.replaceRange((CharSequence) textFieldValue2.getText(), TextRange.m6479getStartimpl(textFieldValue2.getSelection()), TextRange.m6474getEndimpl(textFieldValue2.getSelection()), (CharSequence) text).toString();
                                        long newCursor = TextRangeKt.TextRange(TextRange.m6479getStartimpl(textFieldValue2.getSelection()) + text.length());
                                        legacyTextFieldState5.getOnValueChange().invoke(new TextFieldValue(newText, newCursor, (TextRange) null, 4, (DefaultConstructorMarker) null));
                                    }
                                    return true;
                                }
                            }, 1, null);
                        }
                        final OffsetMapping offsetMapping3 = offsetMapping2;
                        final boolean z314 = z21116;
                        final TextFieldValue textFieldValue2 = value;
                        final TextFieldSelectionManager textFieldSelectionManager = manager;
                        final LegacyTextFieldState legacyTextFieldState4 = state;
                        SemanticsPropertiesKt.setSelection$default($this$semantics, null, new Function3<Integer, Integer, Boolean, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Integer num2, Boolean bool) {
                                return invoke(num.intValue(), num2.intValue(), bool.booleanValue());
                            }

                            public final Boolean invoke(int selectionStart, int selectionEnd, boolean relativeToOriginalText) {
                                int start;
                                int end;
                                if (relativeToOriginalText) {
                                    start = selectionStart;
                                } else {
                                    start = offsetMapping3.transformedToOriginal(selectionStart);
                                }
                                if (relativeToOriginalText) {
                                    end = selectionEnd;
                                } else {
                                    end = offsetMapping3.transformedToOriginal(selectionEnd);
                                }
                                boolean z315 = false;
                                if (z314 && (start != TextRange.m6479getStartimpl(textFieldValue2.getSelection()) || end != TextRange.m6474getEndimpl(textFieldValue2.getSelection()))) {
                                    if (Math.min(start, end) >= 0 && Math.max(start, end) <= textFieldValue2.getText().length()) {
                                        if (!relativeToOriginalText && start != end) {
                                            TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager, false, 1, null);
                                        } else {
                                            textFieldSelectionManager.exitSelectionMode$foundation_release();
                                        }
                                        legacyTextFieldState4.getOnValueChange().invoke(new TextFieldValue(textFieldValue2.getText(), TextRangeKt.TextRange(start, end), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                        z315 = true;
                                    } else {
                                        textFieldSelectionManager.exitSelectionMode$foundation_release();
                                    }
                                }
                                return Boolean.valueOf(z315);
                            }
                        }, 1, null);
                        int imeAction = imeOptions15.getImeAction();
                        final LegacyTextFieldState legacyTextFieldState5 = state;
                        final ImeOptions imeOptions16 = imeOptions15;
                        SemanticsPropertiesKt.m6304onImeAction9UiTYpY$default($this$semantics, imeAction, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                legacyTextFieldState5.getOnImeActionPerformed().invoke(ImeAction.m6641boximpl(imeOptions16.getImeAction()));
                                return true;
                            }
                        }, 2, null);
                        final LegacyTextFieldState legacyTextFieldState6 = state;
                        final FocusRequester focusRequester2 = focusRequester;
                        final boolean z315 = z21117;
                        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                CoreTextFieldKt.tapToFocus(legacyTextFieldState6, focusRequester2, !z315);
                                return true;
                            }
                        }, 1, null);
                        final TextFieldSelectionManager textFieldSelectionManager2 = manager;
                        SemanticsPropertiesKt.onLongClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.7
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager2, false, 1, null);
                                return true;
                            }
                        }, 1, null);
                        if (!TextRange.m6473getCollapsedimpl(value.getSelection()) && !isPassword4) {
                            final TextFieldSelectionManager textFieldSelectionManager3 = manager;
                            SemanticsPropertiesKt.copyText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.8
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    TextFieldSelectionManager.copy$foundation_release$default(textFieldSelectionManager3, false, 1, null);
                                    return true;
                                }
                            }, 1, null);
                            if (z21116 && !z21117) {
                                final TextFieldSelectionManager textFieldSelectionManager4 = manager;
                                SemanticsPropertiesKt.cutText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.9
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        textFieldSelectionManager4.cut$foundation_release();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        }
                        if (z21116 && !z21117) {
                            final TextFieldSelectionManager textFieldSelectionManager5 = manager;
                            SemanticsPropertiesKt.pasteText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.10
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    textFieldSelectionManager5.paste$foundation_release();
                                    return true;
                                }
                            }, 1, null);
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv15);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier semanticsModifier4 = SemanticsModifierKt.semantics(companion114, true, (Function1) value$iv15);
                if (enabled2) {
                    z16 = false;
                } else {
                    z16 = false;
                }
                boolean showCursor4 = z16;
                final Modifier cursorModifier4 = TextFieldCursorKt.cursor(Modifier.INSTANCE, state, value, offsetMapping2, cursorBrush2, showCursor4);
                ComposerKt.sourceInformationMarkerStart($composer3, -1704832829, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv10 = $composer3.changedInstance(manager);
                Object it$iv11112 = $composer3.rememberedValue();
                if (invalid$iv10) {
                }
                value$iv16 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        final TextFieldSelectionManager textFieldSelectionManager = manager;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                            }
                        };
                    }
                };
                $composer3.updateRememberedValue(value$iv16);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.DisposableEffect(manager, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv16, $composer3, 0);
                ComposerKt.sourceInformationMarkerStart($composer3, -1704829391, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance114 = $composer3.changedInstance(state) | $composer3.changedInstance(textInputService);
                if (($dirty & 14) == 4) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                invalid$iv11 = zChangedInstance114 | z17 | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                Object it$iv11113 = $composer3.rememberedValue();
                if (invalid$iv11) {
                }
                value$iv17 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        if (state.getHasFocus()) {
                            state.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation_release(textInputService, value, state.getProcessor(), imeOptions5, state.getOnValueChange(), state.getOnImeActionPerformed()));
                        }
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                            }
                        };
                    }
                };
                $composer3.updateRememberedValue(value$iv17);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.DisposableEffect(imeOptions5, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv17, $composer3, ($dirty1 >> 3) & 14);
                Modifier.Companion companion115 = Modifier.INSTANCE;
                Function1<TextFieldValue, Unit> onValueChange4 = state.getOnValueChange();
                boolean z312 = !readOnly2;
                if (maxLines5 == 1) {
                    z18 = true;
                } else {
                    z18 = false;
                }
                showHandleAndMagnifier = false;
                imeOptions6 = imeOptions5;
                boolean z313 = z18;
                int $dirty6 = $dirty1;
                enabled3 = enabled2;
                Modifier textKeyInputModifier4 = TextFieldKeyInputKt.m1429textFieldKeyInput2WJ9YEU(companion115, state, manager, value, onValueChange4, z312, z313, offsetMapping2, undoManager, imeOptions5.getImeAction());
                Modifier.Companion companion116 = Modifier.INSTANCE;
                boolean zCoreTextField$lambda$14 = CoreTextField$lambda$11(writeable$delegate4);
                ComposerKt.sourceInformationMarkerStart($composer3, -1704798381, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv12 = $composer3.changedInstance(state) | (((($dirty6 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions6)) || ($dirty6 & 48) == 32) | $composer3.changedInstance(legacyTextInputServiceAdapter);
                Object it$iv11114 = $composer3.rememberedValue();
                if (invalid$iv12) {
                    value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            if (!state.getHasFocus()) {
                                focusRequester.requestFocus();
                            }
                            if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                                legacyTextInputServiceAdapter.startStylusHandwriting();
                            }
                            return true;
                        }
                    };
                    $composer3.updateRememberedValue(value$iv18);
                } else {
                    value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            if (!state.getHasFocus()) {
                                focusRequester.requestFocus();
                            }
                            if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                                legacyTextInputServiceAdapter.startStylusHandwriting();
                            }
                            return true;
                        }
                    };
                    $composer3.updateRememberedValue(value$iv18);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier stylusHandwritingModifier4 = StylusHandwritingKt.stylusHandwriting(companion116, zCoreTextField$lambda$14, (Function0) value$iv18);
                Modifier decorationBoxModifier4 = OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier5, legacyTextInputServiceAdapter, state, manager).then(stylusHandwritingModifier4).then(focusModifier4), state, focusManager), state, manager).then(textKeyInputModifier4), scrollerPosition2, interactionSource9, enabled3).then(r15).then(semanticsModifier4), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates it3) {
                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                        if (layoutResult == null) {
                            return;
                        }
                        layoutResult.setDecorationBoxCoordinates(it3);
                    }
                });
                if (enabled3) {
                    showHandleAndMagnifier = true;
                }
                if (showHandleAndMagnifier) {
                    companionTextFieldMagnifier = TextFieldSelectionManager_androidKt.textFieldMagnifier(Modifier.INSTANCE, manager);
                } else {
                    companionTextFieldMagnifier = Modifier.INSTANCE;
                }
                maxLines3 = maxLines5;
                final Modifier magnifierModifier4 = companionTextFieldMagnifier;
                final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function14 = function3M1344getLambda1$foundation_release;
                final TextStyle textStyle7 = textStyle2;
                final int i18 = minLines2;
                enabled4 = enabled3;
                imeOptions7 = imeOptions6;
                interactionSource4 = interactionSource9;
                visualTransformation3 = visualTransformation6;
                modifier3 = modifier5;
                final boolean z314 = showHandleAndMagnifier;
                final boolean z315 = readOnly2;
                final Function1<? super TextLayoutResult, Unit> function15 = function4;
                Function2<Composer, Integer, Unit> function16 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5
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

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C695@31947L4735,695@31933L4749:CoreTextField.kt#423gt5");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-374338080, $changed2, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:695)");
                            }
                            Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> function17 = function14;
                            final LegacyTextFieldState legacyTextFieldState = state;
                            final TextStyle textStyle8 = textStyle7;
                            final int i19 = i18;
                            final int i110 = maxLines3;
                            final TextFieldScrollerPosition textFieldScrollerPosition = scrollerPosition2;
                            final TextFieldValue textFieldValue = value;
                            final VisualTransformation visualTransformation7 = visualTransformation6;
                            final Modifier modifier6 = cursorModifier4;
                            final Modifier modifier7 = drawModifier4;
                            final Modifier modifier8 = onPositionedModifier4;
                            final Modifier modifier9 = magnifierModifier4;
                            final BringIntoViewRequester bringIntoViewRequester3 = bringIntoViewRequester2;
                            final TextFieldSelectionManager textFieldSelectionManager = manager;
                            final boolean z316 = z314;
                            final boolean z317 = z315;
                            final Function1<? super TextLayoutResult, Unit> function18 = function15;
                            final OffsetMapping offsetMapping3 = offsetMapping2;
                            final Density density2 = density;
                            function17.invoke(ComposableLambdaKt.rememberComposableLambda(2032502107, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1
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

                                public final void invoke(Composer $composer5, int $changed3) {
                                    Object value$iv20;
                                    ComposerKt.sourceInformation($composer5, "C711@32801L22,720@33160L3512,720@33124L3548:CoreTextField.kt#423gt5");
                                    if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2032502107, $changed3, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:698)");
                                        }
                                        Modifier modifierHeightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m1067heightInVpY3zN4$default(Modifier.INSTANCE, legacyTextFieldState.m1368getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), textStyle8, i19, i110);
                                        TextFieldScrollerPosition textFieldScrollerPosition2 = textFieldScrollerPosition;
                                        TextFieldValue textFieldValue2 = textFieldValue;
                                        VisualTransformation visualTransformation8 = visualTransformation7;
                                        ComposerKt.sourceInformationMarkerStart($composer5, 713189947, "CC(remember):CoreTextField.kt#9igjgp");
                                        boolean invalid$iv14 = $composer5.changedInstance(legacyTextFieldState);
                                        final LegacyTextFieldState legacyTextFieldState2 = legacyTextFieldState;
                                        Object it$iv11115 = $composer5.rememberedValue();
                                        if (invalid$iv14 || it$iv11115 == Composer.INSTANCE.getEmpty()) {
                                            value$iv20 = new Function0<TextLayoutResultProxy>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$coreTextFieldModifier$1$1
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final TextLayoutResultProxy invoke() {
                                                    return legacyTextFieldState2.getLayoutResult();
                                                }
                                            };
                                            $composer5.updateRememberedValue(value$iv20);
                                        } else {
                                            value$iv20 = it$iv11115;
                                        }
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        Modifier coreTextFieldModifier = BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScrollKt.textFieldScroll(modifierHeightInLines, textFieldScrollerPosition2, textFieldValue2, visualTransformation8, (Function0) value$iv20).then(modifier6).then(modifier7), textStyle8).then(modifier8).then(modifier9), bringIntoViewRequester3);
                                        final TextFieldSelectionManager textFieldSelectionManager2 = textFieldSelectionManager;
                                        final LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState;
                                        final boolean z318 = z316;
                                        final boolean z319 = z317;
                                        final Function1<? super TextLayoutResult, Unit> function19 = function18;
                                        final TextFieldValue textFieldValue3 = textFieldValue;
                                        final OffsetMapping offsetMapping4 = offsetMapping3;
                                        final Density density3 = density2;
                                        final int i111 = i110;
                                        SimpleLayoutKt.SimpleLayout(coreTextFieldModifier, ComposableLambdaKt.rememberComposableLambda(-363167407, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1
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

                                            /* JADX WARN: Code duplicated, block: B:35:0x0131  */
                                            public final void invoke(Composer $composer6, int $changed4) {
                                                boolean z3110;
                                                ComposerKt.sourceInformation($composer6, "C721@33178L2880,776@36076L322:CoreTextField.kt#423gt5");
                                                if (($changed4 & 3) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-363167407, $changed4, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous>.<anonymous> (CoreTextField.kt:721)");
                                                    }
                                                    final LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                                    final Function1<? super TextLayoutResult, Unit> function110 = function19;
                                                    final TextFieldValue textFieldValue4 = textFieldValue3;
                                                    final OffsetMapping offsetMapping5 = offsetMapping4;
                                                    final Density density4 = density3;
                                                    final int i112 = i111;
                                                    MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1.2
                                                        @Override // androidx.compose.ui.layout.MeasurePolicy
                                                        /* JADX INFO: renamed from: measure-3p2s80s */
                                                        public MeasureResult mo391measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
                                                            Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                                                            LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState4;
                                                            Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                                                            Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                                                            Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                                                            try {
                                                                TextLayoutResultProxy prevProxy = legacyTextFieldState5.getLayoutResult();
                                                                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                TextLayoutResult prevResult = prevProxy != null ? prevProxy.getValue() : null;
                                                                Triple<Integer, Integer, TextLayoutResult> tripleM1421layout_EkL_Y$foundation_release = TextFieldDelegate.INSTANCE.m1421layout_EkL_Y$foundation_release(legacyTextFieldState4.getTextDelegate(), constraints, $this$measure_u2d3p2s80s.getLayoutDirection(), prevResult);
                                                                int width = tripleM1421layout_EkL_Y$foundation_release.component1().intValue();
                                                                int height = tripleM1421layout_EkL_Y$foundation_release.component2().intValue();
                                                                TextLayoutResult result = tripleM1421layout_EkL_Y$foundation_release.component3();
                                                                if (!Intrinsics.areEqual(prevResult, result)) {
                                                                    legacyTextFieldState4.setLayoutResult(new TextLayoutResultProxy(result, null, prevProxy != null ? prevProxy.getDecorationBoxCoordinates() : null, 2, null));
                                                                    function110.invoke(result);
                                                                    CoreTextFieldKt.notifyFocusedRect(legacyTextFieldState4, textFieldValue4, offsetMapping5);
                                                                }
                                                                LegacyTextFieldState legacyTextFieldState6 = legacyTextFieldState4;
                                                                Density $this$measure_3p2s80s_u24lambda_u241 = density4;
                                                                legacyTextFieldState6.m1372setMinHeightForSingleLineField0680j_4($this$measure_3p2s80s_u24lambda_u241.mo723toDpu2uoSUM(i112 == 1 ? TextDelegateKt.ceilToIntPx(result.getLineBottom(0)) : 0));
                                                                HorizontalAlignmentLine firstBaseline = AlignmentLineKt.getFirstBaseline();
                                                                float $this$fastRoundToInt$iv = result.getFirstBaseline();
                                                                HorizontalAlignmentLine lastBaseline = AlignmentLineKt.getLastBaseline();
                                                                float $this$fastRoundToInt$iv2 = result.getLastBaseline();
                                                                return $this$measure_u2d3p2s80s.layout(width, height, MapsKt.mapOf(TuplesKt.to(firstBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv))), TuplesKt.to(lastBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv2)))), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1$2$measure$2
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                        invoke2(placementScope);
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                    public final void invoke2(Placeable.PlacementScope $this$layout) {
                                                                    }
                                                                });
                                                            } catch (Throwable th) {
                                                                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                throw th;
                                                            }
                                                        }

                                                        @Override // androidx.compose.ui.layout.MeasurePolicy
                                                        public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
                                                            legacyTextFieldState4.getTextDelegate().layoutIntrinsics($this$maxIntrinsicWidth.getLayoutDirection());
                                                            return legacyTextFieldState4.getTextDelegate().getMaxIntrinsicWidth();
                                                        }
                                                    };
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                                    Modifier modifier$iv = Modifier.INSTANCE;
                                                    int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                    CompositionLocalMap localMap$iv = $composer6.getCurrentCompositionLocalMap();
                                                    Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer6, modifier$iv);
                                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                    int $changed$iv$iv = ((0 << 6) & 896) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                    if (!($composer6.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    $composer6.startReusableNode();
                                                    if ($composer6.getInserting()) {
                                                        $composer6.createNode(constructor);
                                                    } else {
                                                        $composer6.useNode();
                                                    }
                                                    Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer6);
                                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                    if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                                                        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                                                        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                                                    }
                                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                                    int i20 = ($changed$iv$iv >> 6) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1674249312, "C:CoreTextField.kt#423gt5");
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    $composer6.endNode();
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    TextFieldSelectionManager textFieldSelectionManager3 = textFieldSelectionManager2;
                                                    if (legacyTextFieldState3.getHandleState() == HandleState.None || legacyTextFieldState3.getLayoutCoordinates() == null) {
                                                        z3110 = false;
                                                    } else {
                                                        LayoutCoordinates layoutCoordinates = legacyTextFieldState3.getLayoutCoordinates();
                                                        Intrinsics.checkNotNull(layoutCoordinates);
                                                        if (layoutCoordinates.isAttached() && z318) {
                                                            z3110 = true;
                                                        } else {
                                                            z3110 = false;
                                                        }
                                                    }
                                                    CoreTextFieldKt.SelectionToolbarAndHandles(textFieldSelectionManager3, z3110, $composer6, 0);
                                                    if (legacyTextFieldState3.getHandleState() == HandleState.Cursor && !z319 && z318) {
                                                        $composer6.startReplaceGroup(-2032274);
                                                        ComposerKt.sourceInformation($composer6, "789@36600L40");
                                                        CoreTextFieldKt.TextFieldCursorHandle(textFieldSelectionManager2, $composer6, 0);
                                                        $composer6.endReplaceGroup();
                                                    } else {
                                                        $composer6.startReplaceGroup(-1955394);
                                                        $composer6.endReplaceGroup();
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer6.skipToGroupEnd();
                                            }
                                        }, $composer5, 54), $composer5, 48, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer5.skipToGroupEnd();
                                }
                            }, $composer4, 54), $composer4, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                };
                $composer2 = $composer3;
                CoreTextFieldRootBox(decorationBoxModifier4, manager, ComposableLambdaKt.rememberComposableLambda(-374338080, true, function16, $composer2, 54), $composer2, 384);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                imeOptions4 = imeOptions3;
                interactionSource3 = interactionSource2;
            }
            transformedTextM1419applyCompositionDecoration72CqOWE = transformed;
            value$iv4 = transformedTextM1419applyCompositionDecoration72CqOWE;
            $composer3.updateRememberedValue(value$iv4);
            final TransformedText transformedText3 = (TransformedText) value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            AnnotatedString visualText3 = transformedText3.getText();
            final OffsetMapping offsetMapping3 = transformedText3.getOffsetMapping();
            RecomposeScope scope3 = ComposablesKt.getCurrentRecomposeScope($composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, -1705304518, "CC(remember):CoreTextField.kt#9igjgp");
            invalid$iv2 = $composer3.changed(keyboardController);
            Object it$iv22 = $composer3.rememberedValue();
            if (invalid$iv2) {
            }
            value$iv5 = new LegacyTextFieldState(new TextDelegate(visualText3, textStyle2, 0, 0, softWrap2, 0, density, fontFamilyResolver, null, AnimationConstants.DefaultDurationMillis, null), scope3, keyboardController);
            $composer3.updateRememberedValue(value$iv5);
            state = (LegacyTextFieldState) value$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            state.m1375updatefnh65Uc(value.getText(), visualText3, textStyle2, softWrap2, density, fontFamilyResolver, function1, keyboardActions2, focusManager, selectionBackgroundColor);
            state.getProcessor().reset(value, state.getInputSession());
            ComposerKt.sourceInformationMarkerStart($composer3, -1705279231, "CC(remember):CoreTextField.kt#9igjgp");
            it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv6 = new UndoManager(0, 1, null);
                $composer3.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv;
            }
            undoManager = (UndoManager) value$iv6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            UndoManager.snapshotIfNeeded$default(undoManager, value, 0L, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -1705276454, "CC(remember):CoreTextField.kt#9igjgp");
            it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = new TextFieldSelectionManager(undoManager);
                $composer3.updateRememberedValue(value$iv7);
            } else {
                value$iv7 = it$iv2;
            }
            manager = (TextFieldSelectionManager) value$iv7;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            manager.setOffsetMapping$foundation_release(offsetMapping3);
            manager.setVisualTransformation$foundation_release(visualTransformation2);
            manager.setOnValueChange$foundation_release(state.getOnValueChange());
            manager.setState$foundation_release(state);
            manager.setValue$foundation_release(value);
            ProvidableCompositionLocal<ClipboardManager> localClipboardManager3 = CompositionLocalsKt.getLocalClipboardManager();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume13 = $composer3.consume(localClipboardManager3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            manager.setClipboardManager$foundation_release((ClipboardManager) objConsume13);
            ProvidableCompositionLocal<TextToolbar> localTextToolbar3 = CompositionLocalsKt.getLocalTextToolbar();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume14 = $composer3.consume(localTextToolbar3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            manager.setTextToolbar((TextToolbar) objConsume14);
            ProvidableCompositionLocal<HapticFeedback> localHapticFeedback3 = CompositionLocalsKt.getLocalHapticFeedback();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume15 = $composer3.consume(localHapticFeedback3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            manager.setHapticFeedBack((HapticFeedback) objConsume15);
            manager.setFocusRequester(focusRequester);
            manager.setEditable(!readOnly2);
            manager.setEnabled(enabled2);
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
            value$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv);
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv3 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final CoroutineScope coroutineScope3 = wrapper$iv3.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1705256948, "CC(remember):CoreTextField.kt#9igjgp");
            it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv8 = BringIntoViewRequesterKt.BringIntoViewRequester();
                $composer3.updateRememberedValue(value$iv8);
            } else {
                value$iv8 = it$iv3;
            }
            final BringIntoViewRequester bringIntoViewRequester3 = (BringIntoViewRequester) value$iv8;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier.Companion companion117 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1705248170, "CC(remember):CoreTextField.kt#9igjgp");
            boolean zChangedInstance115 = $composer3.changedInstance(state);
            final VisualTransformation visualTransformation7 = visualTransformation2;
            if (($dirty1 & 7168) == 2048) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z111 = zChangedInstance115 | z3;
            if (($dirty1 & 57344) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean zChangedInstance116 = z111 | z4 | $composer3.changedInstance(textInputService);
            if (($dirty & 14) == 4) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean z2120 = zChangedInstance116 | z5;
            if ((($dirty1 & 112) ^ 48) > 32) {
                imeOptions5 = imeOptions4;
                if ($composer3.changed(imeOptions5)) {
                    z6 = true;
                }
                invalid$iv3 = z2120 | z6 | $composer3.changedInstance(offsetMapping3) | $composer3.changedInstance(coroutineScope3) | $composer3.changedInstance(bringIntoViewRequester3) | $composer3.changedInstance(manager);
                Object it$iv11115 = $composer3.rememberedValue();
                if (invalid$iv3) {
                }
                final boolean z21118 = enabled2;
                final boolean z21119 = readOnly2;
                final ImeOptions imeOptions16 = imeOptions5;
                value$iv9 = new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$focusModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                        invoke2(focusState);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FocusState it3) {
                        TextLayoutResultProxy layoutResult;
                        if (state.getHasFocus() == it3.isFocused()) {
                            return;
                        }
                        state.setHasFocus(it3.isFocused());
                        if (!state.getHasFocus() || !z21118 || z21119) {
                            CoreTextFieldKt.endInputSession(state);
                        } else {
                            CoreTextFieldKt.startInputSession(textInputService, state, value, imeOptions16, offsetMapping3);
                        }
                        if (it3.isFocused() && (layoutResult = state.getLayoutResult()) != null) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1$1(bringIntoViewRequester3, value, state, layoutResult, offsetMapping3, null), 3, null);
                        }
                        if (!it3.isFocused()) {
                            TextFieldSelectionManager.m1748deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv9);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                MutableInteractionSource interactionSource10 = interactionSource3;
                Modifier focusModifier5 = TextFieldGestureModifiersKt.textFieldFocusModifier(companion117, enabled2, focusRequester, interactionSource10, (Function1) value$iv9);
                if (enabled2) {
                    z7 = false;
                } else {
                    z7 = false;
                }
                State writeable$delegate5 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(z7), $composer3, 0);
                Unit unit5 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705195503, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv4 = $composer3.changed(writeable$delegate5) | $composer3.changedInstance(state) | $composer3.changedInstance(textInputService) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                Object it$iv11116 = $composer3.rememberedValue();
                if (invalid$iv4) {
                }
                value$iv10 = new CoreTextFieldKt$CoreTextField$2$1(state, writeable$delegate5, textInputService, manager, imeOptions5, null);
                $composer3.updateRememberedValue(value$iv10);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(unit5, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv10, $composer3, 6);
                Modifier.Companion companion118 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705163133, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv5 = $composer3.changedInstance(state);
                value$iv11 = $composer3.rememberedValue();
                if (!invalid$iv5) {
                }
                value$iv11 = (Function1) new Function1<Boolean, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean it3) {
                        state.setInTouchMode(it3);
                    }
                };
                $composer3.updateRememberedValue(value$iv11);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierUpdateSelectionTouchMode5 = SelectionGesturesKt.updateSelectionTouchMode(companion118, (Function1) value$iv11);
                ComposerKt.sourceInformationMarkerStart($composer3, -1705159310, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance117 = $composer3.changedInstance(state);
                if (($dirty1 & 57344) == 16384) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean z211110 = zChangedInstance117 | z8;
                if (($dirty1 & 7168) == 2048) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                invalid$iv6 = z211110 | z9 | $composer3.changedInstance(offsetMapping3) | $composer3.changedInstance(manager);
                Object it$iv11117 = $composer3.rememberedValue();
                if (invalid$iv6) {
                }
                final boolean z211111 = readOnly2;
                final boolean z211112 = enabled2;
                value$iv12 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m1345invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m1345invokek4lQ0M(long offset) {
                        CoreTextFieldKt.tapToFocus(state, focusRequester, !z211111);
                        if (state.getHasFocus() && z211112) {
                            if (state.getHandleState() != HandleState.Selection) {
                                TextLayoutResultProxy layoutResult = state.getLayoutResult();
                                if (layoutResult != null) {
                                    LegacyTextFieldState legacyTextFieldState = state;
                                    TextFieldDelegate.INSTANCE.m1422setCursorOffsetULxng0E$foundation_release(offset, layoutResult, legacyTextFieldState.getProcessor(), offsetMapping3, legacyTextFieldState.getOnValueChange());
                                    if (legacyTextFieldState.getTextDelegate().getText().length() > 0) {
                                        legacyTextFieldState.setHandleState(HandleState.Cursor);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            manager.m1752deselect_kEHs6E$foundation_release(Offset.m4282boximpl(offset));
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv12);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(modifierUpdateSelectionTouchMode5, interactionSource10, enabled2, (Function1) value$iv12), manager.getMouseSelectionObserver(), manager.getTouchSelectionObserver()), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
                Modifier.Companion companion119 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705122293, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance118 = $composer3.changedInstance(state);
                if (($dirty & 14) == 4) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                invalid$iv7 = zChangedInstance118 | z10 | $composer3.changedInstance(offsetMapping3);
                Object it$iv11118 = $composer3.rememberedValue();
                if (invalid$iv7) {
                }
                value$iv13 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$drawModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$drawBehind) {
                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                        if (layoutResult != null) {
                            TextFieldValue textFieldValue = value;
                            LegacyTextFieldState legacyTextFieldState = state;
                            OffsetMapping offsetMapping4 = offsetMapping3;
                            Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
                            TextFieldDelegate.INSTANCE.m1420drawQ1vqE60$foundation_release(canvas, textFieldValue, legacyTextFieldState.m1370getSelectionPreviewHighlightRanged9O1mEE(), legacyTextFieldState.m1367getDeletionPreviewHighlightRanged9O1mEE(), offsetMapping4, layoutResult.getValue(), legacyTextFieldState.getHighlightPaint(), legacyTextFieldState.getSelectionBackgroundColor());
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv13);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Modifier drawModifier5 = DrawModifierKt.drawBehind(companion119, (Function1) value$iv13);
                Modifier.Companion companion1110 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705102808, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance119 = $composer3.changedInstance(state);
                if (($dirty1 & 7168) == 2048) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                windowInfo2 = windowInfo;
                boolean zChanged9 = zChangedInstance119 | z11 | $composer3.changed(windowInfo2) | $composer3.changedInstance(manager);
                if (($dirty & 14) == 4) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                invalid$iv8 = zChanged9 | z12 | $composer3.changedInstance(offsetMapping3);
                Object it$iv11119 = $composer3.rememberedValue();
                if (invalid$iv8) {
                }
                final boolean z211113 = enabled2;
                value$iv14 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$onPositionedModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates it3) {
                        state.setLayoutCoordinates(it3);
                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                        if (layoutResult != null) {
                            layoutResult.setInnerTextFieldCoordinates(it3);
                        }
                        if (z211113) {
                            if (state.getHandleState() == HandleState.Selection) {
                                if (state.getShowFloatingToolbar() && windowInfo2.isWindowFocused()) {
                                    manager.showSelectionToolbar$foundation_release();
                                } else {
                                    manager.hideSelectionToolbar$foundation_release();
                                }
                                state.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                                state.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, false));
                                state.setShowCursorHandle(TextRange.m6473getCollapsedimpl(value.getSelection()));
                            } else if (state.getHandleState() == HandleState.Cursor) {
                                state.setShowCursorHandle(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                            }
                            CoreTextFieldKt.notifyFocusedRect(state, value, offsetMapping3);
                            TextLayoutResultProxy layoutResult2 = state.getLayoutResult();
                            if (layoutResult2 != null) {
                                LegacyTextFieldState legacyTextFieldState = state;
                                TextFieldValue textFieldValue = value;
                                OffsetMapping offsetMapping4 = offsetMapping3;
                                TextInputSession inputSession = legacyTextFieldState.getInputSession();
                                if (inputSession != null && legacyTextFieldState.getHasFocus()) {
                                    TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation_release(inputSession, textFieldValue, offsetMapping4, layoutResult2);
                                }
                            }
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv14);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Modifier onPositionedModifier5 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion1110, (Function1) value$iv14);
                final boolean isPassword5 = visualTransformation7 instanceof PasswordVisualTransformation;
                Modifier.Companion companion1111 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1705046115, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance1110 = $composer3.changedInstance(transformedText3);
                if (($dirty & 14) == 4) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                boolean z211114 = zChangedInstance1110 | z13;
                if (($dirty1 & 7168) == 2048) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean zChanged10 = z14 | z211114 | $composer3.changed(isPassword5);
                if ((57344 & $dirty1) == 16384) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                invalid$iv9 = zChanged10 | z15 | $composer3.changedInstance(state) | $composer3.changedInstance(offsetMapping3) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                Object it$iv111110 = $composer3.rememberedValue();
                if (invalid$iv9) {
                }
                final boolean z211115 = enabled2;
                final boolean z211116 = readOnly2;
                final ImeOptions imeOptions17 = imeOptions5;
                value$iv15 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setEditableText($this$semantics, transformedText3.getText());
                        SemanticsPropertiesKt.m6309setTextSelectionRangeFDrldGo($this$semantics, value.getSelection());
                        if (!z211115) {
                            SemanticsPropertiesKt.disabled($this$semantics);
                        }
                        if (isPassword5) {
                            SemanticsPropertiesKt.password($this$semantics);
                        }
                        boolean editable = z211115 && !z211116;
                        SemanticsPropertiesKt.setEditable($this$semantics, editable);
                        final LegacyTextFieldState legacyTextFieldState = state;
                        SemanticsPropertiesKt.getTextLayoutResult$default($this$semantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(List<TextLayoutResult> list) {
                                boolean z316;
                                if (legacyTextFieldState.getLayoutResult() != null) {
                                    TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                                    Intrinsics.checkNotNull(layoutResult);
                                    list.add(layoutResult.getValue());
                                    z316 = true;
                                } else {
                                    z316 = false;
                                }
                                return Boolean.valueOf(z316);
                            }
                        }, 1, null);
                        if (editable) {
                            final LegacyTextFieldState legacyTextFieldState2 = state;
                            SemanticsPropertiesKt.setText$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(AnnotatedString text) {
                                    Unit unit6;
                                    TextInputSession session = legacyTextFieldState2.getInputSession();
                                    if (session != null) {
                                        LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState2;
                                        TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new DeleteAllCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState3.getProcessor(), legacyTextFieldState3.getOnValueChange(), session);
                                        unit6 = Unit.INSTANCE;
                                    } else {
                                        unit6 = null;
                                    }
                                    if (unit6 == null) {
                                        SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                        legacyTextFieldState2.getOnValueChange().invoke(new TextFieldValue(text.getText(), TextRangeKt.TextRange(text.getText().length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                    }
                                    return true;
                                }
                            }, 1, null);
                            final boolean z316 = z211116;
                            final boolean z317 = z211115;
                            final LegacyTextFieldState legacyTextFieldState3 = state;
                            final TextFieldValue textFieldValue = value;
                            SemanticsPropertiesKt.insertTextAtCursor$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(AnnotatedString text) {
                                    Unit unit6;
                                    if (z316 || !z317) {
                                        return false;
                                    }
                                    TextInputSession session = legacyTextFieldState3.getInputSession();
                                    if (session != null) {
                                        LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                        TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new FinishComposingTextCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState4.getProcessor(), legacyTextFieldState4.getOnValueChange(), session);
                                        unit6 = Unit.INSTANCE;
                                    } else {
                                        unit6 = null;
                                    }
                                    if (unit6 == null) {
                                        SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                        TextFieldValue textFieldValue2 = textFieldValue;
                                        LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState3;
                                        String newText = StringsKt.replaceRange((CharSequence) textFieldValue2.getText(), TextRange.m6479getStartimpl(textFieldValue2.getSelection()), TextRange.m6474getEndimpl(textFieldValue2.getSelection()), (CharSequence) text).toString();
                                        long newCursor = TextRangeKt.TextRange(TextRange.m6479getStartimpl(textFieldValue2.getSelection()) + text.length());
                                        legacyTextFieldState5.getOnValueChange().invoke(new TextFieldValue(newText, newCursor, (TextRange) null, 4, (DefaultConstructorMarker) null));
                                    }
                                    return true;
                                }
                            }, 1, null);
                        }
                        final OffsetMapping offsetMapping4 = offsetMapping3;
                        final boolean z318 = z211115;
                        final TextFieldValue textFieldValue2 = value;
                        final TextFieldSelectionManager textFieldSelectionManager = manager;
                        final LegacyTextFieldState legacyTextFieldState4 = state;
                        SemanticsPropertiesKt.setSelection$default($this$semantics, null, new Function3<Integer, Integer, Boolean, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Integer num2, Boolean bool) {
                                return invoke(num.intValue(), num2.intValue(), bool.booleanValue());
                            }

                            public final Boolean invoke(int selectionStart, int selectionEnd, boolean relativeToOriginalText) {
                                int start;
                                int end;
                                if (relativeToOriginalText) {
                                    start = selectionStart;
                                } else {
                                    start = offsetMapping4.transformedToOriginal(selectionStart);
                                }
                                if (relativeToOriginalText) {
                                    end = selectionEnd;
                                } else {
                                    end = offsetMapping4.transformedToOriginal(selectionEnd);
                                }
                                boolean z319 = false;
                                if (z318 && (start != TextRange.m6479getStartimpl(textFieldValue2.getSelection()) || end != TextRange.m6474getEndimpl(textFieldValue2.getSelection()))) {
                                    if (Math.min(start, end) >= 0 && Math.max(start, end) <= textFieldValue2.getText().length()) {
                                        if (!relativeToOriginalText && start != end) {
                                            TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager, false, 1, null);
                                        } else {
                                            textFieldSelectionManager.exitSelectionMode$foundation_release();
                                        }
                                        legacyTextFieldState4.getOnValueChange().invoke(new TextFieldValue(textFieldValue2.getText(), TextRangeKt.TextRange(start, end), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                        z319 = true;
                                    } else {
                                        textFieldSelectionManager.exitSelectionMode$foundation_release();
                                    }
                                }
                                return Boolean.valueOf(z319);
                            }
                        }, 1, null);
                        int imeAction = imeOptions17.getImeAction();
                        final LegacyTextFieldState legacyTextFieldState5 = state;
                        final ImeOptions imeOptions18 = imeOptions17;
                        SemanticsPropertiesKt.m6304onImeAction9UiTYpY$default($this$semantics, imeAction, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                legacyTextFieldState5.getOnImeActionPerformed().invoke(ImeAction.m6641boximpl(imeOptions18.getImeAction()));
                                return true;
                            }
                        }, 2, null);
                        final LegacyTextFieldState legacyTextFieldState6 = state;
                        final FocusRequester focusRequester2 = focusRequester;
                        final boolean z319 = z211116;
                        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                CoreTextFieldKt.tapToFocus(legacyTextFieldState6, focusRequester2, !z319);
                                return true;
                            }
                        }, 1, null);
                        final TextFieldSelectionManager textFieldSelectionManager2 = manager;
                        SemanticsPropertiesKt.onLongClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.7
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager2, false, 1, null);
                                return true;
                            }
                        }, 1, null);
                        if (!TextRange.m6473getCollapsedimpl(value.getSelection()) && !isPassword5) {
                            final TextFieldSelectionManager textFieldSelectionManager3 = manager;
                            SemanticsPropertiesKt.copyText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.8
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    TextFieldSelectionManager.copy$foundation_release$default(textFieldSelectionManager3, false, 1, null);
                                    return true;
                                }
                            }, 1, null);
                            if (z211115 && !z211116) {
                                final TextFieldSelectionManager textFieldSelectionManager4 = manager;
                                SemanticsPropertiesKt.cutText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.9
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        textFieldSelectionManager4.cut$foundation_release();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        }
                        if (z211115 && !z211116) {
                            final TextFieldSelectionManager textFieldSelectionManager5 = manager;
                            SemanticsPropertiesKt.pasteText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.10
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    textFieldSelectionManager5.paste$foundation_release();
                                    return true;
                                }
                            }, 1, null);
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv15);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier semanticsModifier5 = SemanticsModifierKt.semantics(companion1111, true, (Function1) value$iv15);
                if (enabled2) {
                    z16 = false;
                } else {
                    z16 = false;
                }
                boolean showCursor5 = z16;
                final Modifier cursorModifier5 = TextFieldCursorKt.cursor(Modifier.INSTANCE, state, value, offsetMapping3, cursorBrush2, showCursor5);
                ComposerKt.sourceInformationMarkerStart($composer3, -1704832829, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv10 = $composer3.changedInstance(manager);
                Object it$iv111111 = $composer3.rememberedValue();
                if (invalid$iv10) {
                }
                value$iv16 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        final TextFieldSelectionManager textFieldSelectionManager = manager;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                            }
                        };
                    }
                };
                $composer3.updateRememberedValue(value$iv16);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.DisposableEffect(manager, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv16, $composer3, 0);
                ComposerKt.sourceInformationMarkerStart($composer3, -1704829391, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChangedInstance1111 = $composer3.changedInstance(state) | $composer3.changedInstance(textInputService);
                if (($dirty & 14) == 4) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                invalid$iv11 = zChangedInstance1111 | z17 | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
                Object it$iv111112 = $composer3.rememberedValue();
                if (invalid$iv11) {
                }
                value$iv17 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        if (state.getHasFocus()) {
                            state.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation_release(textInputService, value, state.getProcessor(), imeOptions5, state.getOnValueChange(), state.getOnImeActionPerformed()));
                        }
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                            }
                        };
                    }
                };
                $composer3.updateRememberedValue(value$iv17);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.DisposableEffect(imeOptions5, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv17, $composer3, ($dirty1 >> 3) & 14);
                Modifier.Companion companion1112 = Modifier.INSTANCE;
                Function1<TextFieldValue, Unit> onValueChange5 = state.getOnValueChange();
                boolean z316 = !readOnly2;
                if (maxLines5 == 1) {
                    z18 = true;
                } else {
                    z18 = false;
                }
                showHandleAndMagnifier = false;
                imeOptions6 = imeOptions5;
                boolean z317 = z18;
                int $dirty7 = $dirty1;
                enabled3 = enabled2;
                Modifier textKeyInputModifier5 = TextFieldKeyInputKt.m1429textFieldKeyInput2WJ9YEU(companion1112, state, manager, value, onValueChange5, z316, z317, offsetMapping3, undoManager, imeOptions5.getImeAction());
                Modifier.Companion companion1113 = Modifier.INSTANCE;
                boolean zCoreTextField$lambda$15 = CoreTextField$lambda$11(writeable$delegate5);
                ComposerKt.sourceInformationMarkerStart($composer3, -1704798381, "CC(remember):CoreTextField.kt#9igjgp");
                invalid$iv12 = $composer3.changedInstance(state) | (((($dirty7 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions6)) || ($dirty7 & 48) == 32) | $composer3.changedInstance(legacyTextInputServiceAdapter);
                Object it$iv111113 = $composer3.rememberedValue();
                if (invalid$iv12) {
                    value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            if (!state.getHasFocus()) {
                                focusRequester.requestFocus();
                            }
                            if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                                legacyTextInputServiceAdapter.startStylusHandwriting();
                            }
                            return true;
                        }
                    };
                    $composer3.updateRememberedValue(value$iv18);
                } else {
                    value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            if (!state.getHasFocus()) {
                                focusRequester.requestFocus();
                            }
                            if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                                legacyTextInputServiceAdapter.startStylusHandwriting();
                            }
                            return true;
                        }
                    };
                    $composer3.updateRememberedValue(value$iv18);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier stylusHandwritingModifier5 = StylusHandwritingKt.stylusHandwriting(companion1113, zCoreTextField$lambda$15, (Function0) value$iv18);
                Modifier decorationBoxModifier5 = OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier5, legacyTextInputServiceAdapter, state, manager).then(stylusHandwritingModifier5).then(focusModifier5), state, focusManager), state, manager).then(textKeyInputModifier5), scrollerPosition2, interactionSource10, enabled3).then(r15).then(semanticsModifier5), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates it3) {
                        TextLayoutResultProxy layoutResult = state.getLayoutResult();
                        if (layoutResult == null) {
                            return;
                        }
                        layoutResult.setDecorationBoxCoordinates(it3);
                    }
                });
                if (enabled3) {
                    showHandleAndMagnifier = true;
                }
                if (showHandleAndMagnifier) {
                    companionTextFieldMagnifier = TextFieldSelectionManager_androidKt.textFieldMagnifier(Modifier.INSTANCE, manager);
                } else {
                    companionTextFieldMagnifier = Modifier.INSTANCE;
                }
                maxLines3 = maxLines5;
                final Modifier magnifierModifier5 = companionTextFieldMagnifier;
                final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function17 = function3M1344getLambda1$foundation_release;
                final TextStyle textStyle8 = textStyle2;
                final int i19 = minLines2;
                enabled4 = enabled3;
                imeOptions7 = imeOptions6;
                interactionSource4 = interactionSource10;
                visualTransformation3 = visualTransformation7;
                modifier3 = modifier5;
                final boolean z318 = showHandleAndMagnifier;
                final boolean z319 = readOnly2;
                final Function1<? super TextLayoutResult, Unit> function18 = function4;
                Function2<Composer, Integer, Unit> function19 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5
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

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C695@31947L4735,695@31933L4749:CoreTextField.kt#423gt5");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-374338080, $changed2, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:695)");
                            }
                            Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> function110 = function17;
                            final LegacyTextFieldState legacyTextFieldState = state;
                            final TextStyle textStyle9 = textStyle8;
                            final int i110 = i19;
                            final int i111 = maxLines3;
                            final TextFieldScrollerPosition textFieldScrollerPosition = scrollerPosition2;
                            final TextFieldValue textFieldValue = value;
                            final VisualTransformation visualTransformation8 = visualTransformation7;
                            final Modifier modifier6 = cursorModifier5;
                            final Modifier modifier7 = drawModifier5;
                            final Modifier modifier8 = onPositionedModifier5;
                            final Modifier modifier9 = magnifierModifier5;
                            final BringIntoViewRequester bringIntoViewRequester4 = bringIntoViewRequester3;
                            final TextFieldSelectionManager textFieldSelectionManager = manager;
                            final boolean z3110 = z318;
                            final boolean z3111 = z319;
                            final Function1<? super TextLayoutResult, Unit> function111 = function18;
                            final OffsetMapping offsetMapping4 = offsetMapping3;
                            final Density density2 = density;
                            function110.invoke(ComposableLambdaKt.rememberComposableLambda(2032502107, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1
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

                                public final void invoke(Composer $composer5, int $changed3) {
                                    Object value$iv20;
                                    ComposerKt.sourceInformation($composer5, "C711@32801L22,720@33160L3512,720@33124L3548:CoreTextField.kt#423gt5");
                                    if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2032502107, $changed3, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:698)");
                                        }
                                        Modifier modifierHeightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m1067heightInVpY3zN4$default(Modifier.INSTANCE, legacyTextFieldState.m1368getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), textStyle9, i110, i111);
                                        TextFieldScrollerPosition textFieldScrollerPosition2 = textFieldScrollerPosition;
                                        TextFieldValue textFieldValue2 = textFieldValue;
                                        VisualTransformation visualTransformation9 = visualTransformation8;
                                        ComposerKt.sourceInformationMarkerStart($composer5, 713189947, "CC(remember):CoreTextField.kt#9igjgp");
                                        boolean invalid$iv14 = $composer5.changedInstance(legacyTextFieldState);
                                        final LegacyTextFieldState legacyTextFieldState2 = legacyTextFieldState;
                                        Object it$iv111114 = $composer5.rememberedValue();
                                        if (invalid$iv14 || it$iv111114 == Composer.INSTANCE.getEmpty()) {
                                            value$iv20 = new Function0<TextLayoutResultProxy>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$coreTextFieldModifier$1$1
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final TextLayoutResultProxy invoke() {
                                                    return legacyTextFieldState2.getLayoutResult();
                                                }
                                            };
                                            $composer5.updateRememberedValue(value$iv20);
                                        } else {
                                            value$iv20 = it$iv111114;
                                        }
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        Modifier coreTextFieldModifier = BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScrollKt.textFieldScroll(modifierHeightInLines, textFieldScrollerPosition2, textFieldValue2, visualTransformation9, (Function0) value$iv20).then(modifier6).then(modifier7), textStyle9).then(modifier8).then(modifier9), bringIntoViewRequester4);
                                        final TextFieldSelectionManager textFieldSelectionManager2 = textFieldSelectionManager;
                                        final LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState;
                                        final boolean z3112 = z3110;
                                        final boolean z3113 = z3111;
                                        final Function1<? super TextLayoutResult, Unit> function112 = function111;
                                        final TextFieldValue textFieldValue3 = textFieldValue;
                                        final OffsetMapping offsetMapping5 = offsetMapping4;
                                        final Density density3 = density2;
                                        final int i112 = i111;
                                        SimpleLayoutKt.SimpleLayout(coreTextFieldModifier, ComposableLambdaKt.rememberComposableLambda(-363167407, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1
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

                                            /* JADX WARN: Code duplicated, block: B:35:0x0131  */
                                            public final void invoke(Composer $composer6, int $changed4) {
                                                boolean z3114;
                                                ComposerKt.sourceInformation($composer6, "C721@33178L2880,776@36076L322:CoreTextField.kt#423gt5");
                                                if (($changed4 & 3) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-363167407, $changed4, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous>.<anonymous> (CoreTextField.kt:721)");
                                                    }
                                                    final LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                                    final Function1<? super TextLayoutResult, Unit> function113 = function112;
                                                    final TextFieldValue textFieldValue4 = textFieldValue3;
                                                    final OffsetMapping offsetMapping6 = offsetMapping5;
                                                    final Density density4 = density3;
                                                    final int i113 = i112;
                                                    MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1.2
                                                        @Override // androidx.compose.ui.layout.MeasurePolicy
                                                        /* JADX INFO: renamed from: measure-3p2s80s */
                                                        public MeasureResult mo391measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
                                                            Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                                                            LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState4;
                                                            Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                                                            Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                                                            Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                                                            try {
                                                                TextLayoutResultProxy prevProxy = legacyTextFieldState5.getLayoutResult();
                                                                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                TextLayoutResult prevResult = prevProxy != null ? prevProxy.getValue() : null;
                                                                Triple<Integer, Integer, TextLayoutResult> tripleM1421layout_EkL_Y$foundation_release = TextFieldDelegate.INSTANCE.m1421layout_EkL_Y$foundation_release(legacyTextFieldState4.getTextDelegate(), constraints, $this$measure_u2d3p2s80s.getLayoutDirection(), prevResult);
                                                                int width = tripleM1421layout_EkL_Y$foundation_release.component1().intValue();
                                                                int height = tripleM1421layout_EkL_Y$foundation_release.component2().intValue();
                                                                TextLayoutResult result = tripleM1421layout_EkL_Y$foundation_release.component3();
                                                                if (!Intrinsics.areEqual(prevResult, result)) {
                                                                    legacyTextFieldState4.setLayoutResult(new TextLayoutResultProxy(result, null, prevProxy != null ? prevProxy.getDecorationBoxCoordinates() : null, 2, null));
                                                                    function113.invoke(result);
                                                                    CoreTextFieldKt.notifyFocusedRect(legacyTextFieldState4, textFieldValue4, offsetMapping6);
                                                                }
                                                                LegacyTextFieldState legacyTextFieldState6 = legacyTextFieldState4;
                                                                Density $this$measure_3p2s80s_u24lambda_u241 = density4;
                                                                legacyTextFieldState6.m1372setMinHeightForSingleLineField0680j_4($this$measure_3p2s80s_u24lambda_u241.mo723toDpu2uoSUM(i113 == 1 ? TextDelegateKt.ceilToIntPx(result.getLineBottom(0)) : 0));
                                                                HorizontalAlignmentLine firstBaseline = AlignmentLineKt.getFirstBaseline();
                                                                float $this$fastRoundToInt$iv = result.getFirstBaseline();
                                                                HorizontalAlignmentLine lastBaseline = AlignmentLineKt.getLastBaseline();
                                                                float $this$fastRoundToInt$iv2 = result.getLastBaseline();
                                                                return $this$measure_u2d3p2s80s.layout(width, height, MapsKt.mapOf(TuplesKt.to(firstBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv))), TuplesKt.to(lastBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv2)))), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1$2$measure$2
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                        invoke2(placementScope);
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                    public final void invoke2(Placeable.PlacementScope $this$layout) {
                                                                    }
                                                                });
                                                            } catch (Throwable th) {
                                                                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                                throw th;
                                                            }
                                                        }

                                                        @Override // androidx.compose.ui.layout.MeasurePolicy
                                                        public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
                                                            legacyTextFieldState4.getTextDelegate().layoutIntrinsics($this$maxIntrinsicWidth.getLayoutDirection());
                                                            return legacyTextFieldState4.getTextDelegate().getMaxIntrinsicWidth();
                                                        }
                                                    };
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                                    Modifier modifier$iv = Modifier.INSTANCE;
                                                    int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                    CompositionLocalMap localMap$iv = $composer6.getCurrentCompositionLocalMap();
                                                    Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer6, modifier$iv);
                                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                    int $changed$iv$iv = ((0 << 6) & 896) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                    if (!($composer6.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    $composer6.startReusableNode();
                                                    if ($composer6.getInserting()) {
                                                        $composer6.createNode(constructor);
                                                    } else {
                                                        $composer6.useNode();
                                                    }
                                                    Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer6);
                                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                    if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                                                        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                                                        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                                                    }
                                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                                    int i20 = ($changed$iv$iv >> 6) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1674249312, "C:CoreTextField.kt#423gt5");
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    $composer6.endNode();
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    TextFieldSelectionManager textFieldSelectionManager3 = textFieldSelectionManager2;
                                                    if (legacyTextFieldState3.getHandleState() == HandleState.None || legacyTextFieldState3.getLayoutCoordinates() == null) {
                                                        z3114 = false;
                                                    } else {
                                                        LayoutCoordinates layoutCoordinates = legacyTextFieldState3.getLayoutCoordinates();
                                                        Intrinsics.checkNotNull(layoutCoordinates);
                                                        if (layoutCoordinates.isAttached() && z3112) {
                                                            z3114 = true;
                                                        } else {
                                                            z3114 = false;
                                                        }
                                                    }
                                                    CoreTextFieldKt.SelectionToolbarAndHandles(textFieldSelectionManager3, z3114, $composer6, 0);
                                                    if (legacyTextFieldState3.getHandleState() == HandleState.Cursor && !z3113 && z3112) {
                                                        $composer6.startReplaceGroup(-2032274);
                                                        ComposerKt.sourceInformation($composer6, "789@36600L40");
                                                        CoreTextFieldKt.TextFieldCursorHandle(textFieldSelectionManager2, $composer6, 0);
                                                        $composer6.endReplaceGroup();
                                                    } else {
                                                        $composer6.startReplaceGroup(-1955394);
                                                        $composer6.endReplaceGroup();
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer6.skipToGroupEnd();
                                            }
                                        }, $composer5, 54), $composer5, 48, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer5.skipToGroupEnd();
                                }
                            }, $composer4, 54), $composer4, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                };
                $composer2 = $composer3;
                CoreTextFieldRootBox(decorationBoxModifier5, manager, ComposableLambdaKt.rememberComposableLambda(-374338080, true, function19, $composer2, 54), $composer2, 384);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                imeOptions5 = imeOptions4;
            }
            if (($dirty1 & 48) == 32) {
                z6 = true;
            } else {
                z6 = false;
            }
            invalid$iv3 = z2120 | z6 | $composer3.changedInstance(offsetMapping3) | $composer3.changedInstance(coroutineScope3) | $composer3.changedInstance(bringIntoViewRequester3) | $composer3.changedInstance(manager);
            Object it$iv111114 = $composer3.rememberedValue();
            if (invalid$iv3) {
            }
            final boolean z211117 = enabled2;
            final boolean z211118 = readOnly2;
            final ImeOptions imeOptions18 = imeOptions5;
            value$iv9 = new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$focusModifier$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                    invoke2(focusState);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(FocusState it3) {
                    TextLayoutResultProxy layoutResult;
                    if (state.getHasFocus() == it3.isFocused()) {
                        return;
                    }
                    state.setHasFocus(it3.isFocused());
                    if (!state.getHasFocus() || !z211117 || z211118) {
                        CoreTextFieldKt.endInputSession(state);
                    } else {
                        CoreTextFieldKt.startInputSession(textInputService, state, value, imeOptions18, offsetMapping3);
                    }
                    if (it3.isFocused() && (layoutResult = state.getLayoutResult()) != null) {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1$1(bringIntoViewRequester3, value, state, layoutResult, offsetMapping3, null), 3, null);
                    }
                    if (!it3.isFocused()) {
                        TextFieldSelectionManager.m1748deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                    }
                }
            };
            $composer3.updateRememberedValue(value$iv9);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MutableInteractionSource interactionSource11 = interactionSource3;
            Modifier focusModifier6 = TextFieldGestureModifiersKt.textFieldFocusModifier(companion117, enabled2, focusRequester, interactionSource11, (Function1) value$iv9);
            if (enabled2) {
                z7 = false;
            } else {
                z7 = false;
            }
            State writeable$delegate6 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(z7), $composer3, 0);
            Unit unit6 = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1705195503, "CC(remember):CoreTextField.kt#9igjgp");
            invalid$iv4 = $composer3.changed(writeable$delegate6) | $composer3.changedInstance(state) | $composer3.changedInstance(textInputService) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
            Object it$iv111115 = $composer3.rememberedValue();
            if (invalid$iv4) {
            }
            value$iv10 = new CoreTextFieldKt$CoreTextField$2$1(state, writeable$delegate6, textInputService, manager, imeOptions5, null);
            $composer3.updateRememberedValue(value$iv10);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(unit6, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv10, $composer3, 6);
            Modifier.Companion companion1114 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1705163133, "CC(remember):CoreTextField.kt#9igjgp");
            invalid$iv5 = $composer3.changedInstance(state);
            value$iv11 = $composer3.rememberedValue();
            if (!invalid$iv5) {
            }
            value$iv11 = (Function1) new Function1<Boolean, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean it3) {
                    state.setInTouchMode(it3);
                }
            };
            $composer3.updateRememberedValue(value$iv11);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierUpdateSelectionTouchMode6 = SelectionGesturesKt.updateSelectionTouchMode(companion1114, (Function1) value$iv11);
            ComposerKt.sourceInformationMarkerStart($composer3, -1705159310, "CC(remember):CoreTextField.kt#9igjgp");
            boolean zChangedInstance1112 = $composer3.changedInstance(state);
            if (($dirty1 & 57344) == 16384) {
                z8 = true;
            } else {
                z8 = false;
            }
            boolean z211119 = zChangedInstance1112 | z8;
            if (($dirty1 & 7168) == 2048) {
                z9 = true;
            } else {
                z9 = false;
            }
            invalid$iv6 = z211119 | z9 | $composer3.changedInstance(offsetMapping3) | $composer3.changedInstance(manager);
            Object it$iv111116 = $composer3.rememberedValue();
            if (invalid$iv6) {
            }
            final boolean z2111110 = readOnly2;
            final boolean z2111111 = enabled2;
            value$iv12 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m1345invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m1345invokek4lQ0M(long offset) {
                    CoreTextFieldKt.tapToFocus(state, focusRequester, !z2111110);
                    if (state.getHasFocus() && z2111111) {
                        if (state.getHandleState() != HandleState.Selection) {
                            TextLayoutResultProxy layoutResult = state.getLayoutResult();
                            if (layoutResult != null) {
                                LegacyTextFieldState legacyTextFieldState = state;
                                TextFieldDelegate.INSTANCE.m1422setCursorOffsetULxng0E$foundation_release(offset, layoutResult, legacyTextFieldState.getProcessor(), offsetMapping3, legacyTextFieldState.getOnValueChange());
                                if (legacyTextFieldState.getTextDelegate().getText().length() > 0) {
                                    legacyTextFieldState.setHandleState(HandleState.Cursor);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        manager.m1752deselect_kEHs6E$foundation_release(Offset.m4282boximpl(offset));
                    }
                }
            };
            $composer3.updateRememberedValue(value$iv12);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(modifierUpdateSelectionTouchMode6, interactionSource11, enabled2, (Function1) value$iv12), manager.getMouseSelectionObserver(), manager.getTouchSelectionObserver()), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
            Modifier.Companion companion1115 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1705122293, "CC(remember):CoreTextField.kt#9igjgp");
            boolean zChangedInstance1113 = $composer3.changedInstance(state);
            if (($dirty & 14) == 4) {
                z10 = true;
            } else {
                z10 = false;
            }
            invalid$iv7 = zChangedInstance1113 | z10 | $composer3.changedInstance(offsetMapping3);
            Object it$iv111117 = $composer3.rememberedValue();
            if (invalid$iv7) {
            }
            value$iv13 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$drawModifier$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope $this$drawBehind) {
                    TextLayoutResultProxy layoutResult = state.getLayoutResult();
                    if (layoutResult != null) {
                        TextFieldValue textFieldValue = value;
                        LegacyTextFieldState legacyTextFieldState = state;
                        OffsetMapping offsetMapping4 = offsetMapping3;
                        Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
                        TextFieldDelegate.INSTANCE.m1420drawQ1vqE60$foundation_release(canvas, textFieldValue, legacyTextFieldState.m1370getSelectionPreviewHighlightRanged9O1mEE(), legacyTextFieldState.m1367getDeletionPreviewHighlightRanged9O1mEE(), offsetMapping4, layoutResult.getValue(), legacyTextFieldState.getHighlightPaint(), legacyTextFieldState.getSelectionBackgroundColor());
                    }
                }
            };
            $composer3.updateRememberedValue(value$iv13);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Modifier drawModifier6 = DrawModifierKt.drawBehind(companion1115, (Function1) value$iv13);
            Modifier.Companion companion1116 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1705102808, "CC(remember):CoreTextField.kt#9igjgp");
            boolean zChangedInstance1114 = $composer3.changedInstance(state);
            if (($dirty1 & 7168) == 2048) {
                z11 = true;
            } else {
                z11 = false;
            }
            windowInfo2 = windowInfo;
            boolean zChanged11 = zChangedInstance1114 | z11 | $composer3.changed(windowInfo2) | $composer3.changedInstance(manager);
            if (($dirty & 14) == 4) {
                z12 = true;
            } else {
                z12 = false;
            }
            invalid$iv8 = zChanged11 | z12 | $composer3.changedInstance(offsetMapping3);
            Object it$iv111118 = $composer3.rememberedValue();
            if (invalid$iv8) {
            }
            final boolean z2111112 = enabled2;
            value$iv14 = new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$onPositionedModifier$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                    invoke2(layoutCoordinates);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LayoutCoordinates it3) {
                    state.setLayoutCoordinates(it3);
                    TextLayoutResultProxy layoutResult = state.getLayoutResult();
                    if (layoutResult != null) {
                        layoutResult.setInnerTextFieldCoordinates(it3);
                    }
                    if (z2111112) {
                        if (state.getHandleState() == HandleState.Selection) {
                            if (state.getShowFloatingToolbar() && windowInfo2.isWindowFocused()) {
                                manager.showSelectionToolbar$foundation_release();
                            } else {
                                manager.hideSelectionToolbar$foundation_release();
                            }
                            state.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                            state.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, false));
                            state.setShowCursorHandle(TextRange.m6473getCollapsedimpl(value.getSelection()));
                        } else if (state.getHandleState() == HandleState.Cursor) {
                            state.setShowCursorHandle(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                        }
                        CoreTextFieldKt.notifyFocusedRect(state, value, offsetMapping3);
                        TextLayoutResultProxy layoutResult2 = state.getLayoutResult();
                        if (layoutResult2 != null) {
                            LegacyTextFieldState legacyTextFieldState = state;
                            TextFieldValue textFieldValue = value;
                            OffsetMapping offsetMapping4 = offsetMapping3;
                            TextInputSession inputSession = legacyTextFieldState.getInputSession();
                            if (inputSession != null && legacyTextFieldState.getHasFocus()) {
                                TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation_release(inputSession, textFieldValue, offsetMapping4, layoutResult2);
                            }
                        }
                    }
                }
            };
            $composer3.updateRememberedValue(value$iv14);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Modifier onPositionedModifier6 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion1116, (Function1) value$iv14);
            final boolean isPassword6 = visualTransformation7 instanceof PasswordVisualTransformation;
            Modifier.Companion companion1117 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1705046115, "CC(remember):CoreTextField.kt#9igjgp");
            boolean zChangedInstance1115 = $composer3.changedInstance(transformedText3);
            if (($dirty & 14) == 4) {
                z13 = true;
            } else {
                z13 = false;
            }
            boolean z2111113 = zChangedInstance1115 | z13;
            if (($dirty1 & 7168) == 2048) {
                z14 = true;
            } else {
                z14 = false;
            }
            boolean zChanged12 = z14 | z2111113 | $composer3.changed(isPassword6);
            if ((57344 & $dirty1) == 16384) {
                z15 = true;
            } else {
                z15 = false;
            }
            invalid$iv9 = zChanged12 | z15 | $composer3.changedInstance(state) | $composer3.changedInstance(offsetMapping3) | $composer3.changedInstance(manager) | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
            Object it$iv111119 = $composer3.rememberedValue();
            if (invalid$iv9) {
            }
            final boolean z2111114 = enabled2;
            final boolean z2111115 = readOnly2;
            final ImeOptions imeOptions19 = imeOptions5;
            value$iv15 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setEditableText($this$semantics, transformedText3.getText());
                    SemanticsPropertiesKt.m6309setTextSelectionRangeFDrldGo($this$semantics, value.getSelection());
                    if (!z2111114) {
                        SemanticsPropertiesKt.disabled($this$semantics);
                    }
                    if (isPassword6) {
                        SemanticsPropertiesKt.password($this$semantics);
                    }
                    boolean editable = z2111114 && !z2111115;
                    SemanticsPropertiesKt.setEditable($this$semantics, editable);
                    final LegacyTextFieldState legacyTextFieldState = state;
                    SemanticsPropertiesKt.getTextLayoutResult$default($this$semantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(List<TextLayoutResult> list) {
                            boolean z3110;
                            if (legacyTextFieldState.getLayoutResult() != null) {
                                TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                                Intrinsics.checkNotNull(layoutResult);
                                list.add(layoutResult.getValue());
                                z3110 = true;
                            } else {
                                z3110 = false;
                            }
                            return Boolean.valueOf(z3110);
                        }
                    }, 1, null);
                    if (editable) {
                        final LegacyTextFieldState legacyTextFieldState2 = state;
                        SemanticsPropertiesKt.setText$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(AnnotatedString text) {
                                Unit unit7;
                                TextInputSession session = legacyTextFieldState2.getInputSession();
                                if (session != null) {
                                    LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState2;
                                    TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new DeleteAllCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState3.getProcessor(), legacyTextFieldState3.getOnValueChange(), session);
                                    unit7 = Unit.INSTANCE;
                                } else {
                                    unit7 = null;
                                }
                                if (unit7 == null) {
                                    SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                    legacyTextFieldState2.getOnValueChange().invoke(new TextFieldValue(text.getText(), TextRangeKt.TextRange(text.getText().length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                }
                                return true;
                            }
                        }, 1, null);
                        final boolean z3110 = z2111115;
                        final boolean z3111 = z2111114;
                        final LegacyTextFieldState legacyTextFieldState3 = state;
                        final TextFieldValue textFieldValue = value;
                        SemanticsPropertiesKt.insertTextAtCursor$default($this$semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(AnnotatedString text) {
                                Unit unit7;
                                if (z3110 || !z3111) {
                                    return false;
                                }
                                TextInputSession session = legacyTextFieldState3.getInputSession();
                                if (session != null) {
                                    LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                    TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new FinishComposingTextCommand(), new CommitTextCommand(text, 1)}), legacyTextFieldState4.getProcessor(), legacyTextFieldState4.getOnValueChange(), session);
                                    unit7 = Unit.INSTANCE;
                                } else {
                                    unit7 = null;
                                }
                                if (unit7 == null) {
                                    SemanticsPropertyReceiver semanticsPropertyReceiver = $this$semantics;
                                    TextFieldValue textFieldValue2 = textFieldValue;
                                    LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState3;
                                    String newText = StringsKt.replaceRange((CharSequence) textFieldValue2.getText(), TextRange.m6479getStartimpl(textFieldValue2.getSelection()), TextRange.m6474getEndimpl(textFieldValue2.getSelection()), (CharSequence) text).toString();
                                    long newCursor = TextRangeKt.TextRange(TextRange.m6479getStartimpl(textFieldValue2.getSelection()) + text.length());
                                    legacyTextFieldState5.getOnValueChange().invoke(new TextFieldValue(newText, newCursor, (TextRange) null, 4, (DefaultConstructorMarker) null));
                                }
                                return true;
                            }
                        }, 1, null);
                    }
                    final OffsetMapping offsetMapping4 = offsetMapping3;
                    final boolean z3112 = z2111114;
                    final TextFieldValue textFieldValue2 = value;
                    final TextFieldSelectionManager textFieldSelectionManager = manager;
                    final LegacyTextFieldState legacyTextFieldState4 = state;
                    SemanticsPropertiesKt.setSelection$default($this$semantics, null, new Function3<Integer, Integer, Boolean, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Integer num2, Boolean bool) {
                            return invoke(num.intValue(), num2.intValue(), bool.booleanValue());
                        }

                        public final Boolean invoke(int selectionStart, int selectionEnd, boolean relativeToOriginalText) {
                            int start;
                            int end;
                            if (relativeToOriginalText) {
                                start = selectionStart;
                            } else {
                                start = offsetMapping4.transformedToOriginal(selectionStart);
                            }
                            if (relativeToOriginalText) {
                                end = selectionEnd;
                            } else {
                                end = offsetMapping4.transformedToOriginal(selectionEnd);
                            }
                            boolean z3113 = false;
                            if (z3112 && (start != TextRange.m6479getStartimpl(textFieldValue2.getSelection()) || end != TextRange.m6474getEndimpl(textFieldValue2.getSelection()))) {
                                if (Math.min(start, end) >= 0 && Math.max(start, end) <= textFieldValue2.getText().length()) {
                                    if (!relativeToOriginalText && start != end) {
                                        TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager, false, 1, null);
                                    } else {
                                        textFieldSelectionManager.exitSelectionMode$foundation_release();
                                    }
                                    legacyTextFieldState4.getOnValueChange().invoke(new TextFieldValue(textFieldValue2.getText(), TextRangeKt.TextRange(start, end), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                    z3113 = true;
                                } else {
                                    textFieldSelectionManager.exitSelectionMode$foundation_release();
                                }
                            }
                            return Boolean.valueOf(z3113);
                        }
                    }, 1, null);
                    int imeAction = imeOptions19.getImeAction();
                    final LegacyTextFieldState legacyTextFieldState5 = state;
                    final ImeOptions imeOptions110 = imeOptions19;
                    SemanticsPropertiesKt.m6304onImeAction9UiTYpY$default($this$semantics, imeAction, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            legacyTextFieldState5.getOnImeActionPerformed().invoke(ImeAction.m6641boximpl(imeOptions110.getImeAction()));
                            return true;
                        }
                    }, 2, null);
                    final LegacyTextFieldState legacyTextFieldState6 = state;
                    final FocusRequester focusRequester2 = focusRequester;
                    final boolean z3113 = z2111115;
                    SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            CoreTextFieldKt.tapToFocus(legacyTextFieldState6, focusRequester2, !z3113);
                            return true;
                        }
                    }, 1, null);
                    final TextFieldSelectionManager textFieldSelectionManager2 = manager;
                    SemanticsPropertiesKt.onLongClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.7
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            TextFieldSelectionManager.enterSelectionMode$foundation_release$default(textFieldSelectionManager2, false, 1, null);
                            return true;
                        }
                    }, 1, null);
                    if (!TextRange.m6473getCollapsedimpl(value.getSelection()) && !isPassword6) {
                        final TextFieldSelectionManager textFieldSelectionManager3 = manager;
                        SemanticsPropertiesKt.copyText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.8
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                TextFieldSelectionManager.copy$foundation_release$default(textFieldSelectionManager3, false, 1, null);
                                return true;
                            }
                        }, 1, null);
                        if (z2111114 && !z2111115) {
                            final TextFieldSelectionManager textFieldSelectionManager4 = manager;
                            SemanticsPropertiesKt.cutText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.9
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    textFieldSelectionManager4.cut$foundation_release();
                                    return true;
                                }
                            }, 1, null);
                        }
                    }
                    if (z2111114 && !z2111115) {
                        final TextFieldSelectionManager textFieldSelectionManager5 = manager;
                        SemanticsPropertiesKt.pasteText$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1.10
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                textFieldSelectionManager5.paste$foundation_release();
                                return true;
                            }
                        }, 1, null);
                    }
                }
            };
            $composer3.updateRememberedValue(value$iv15);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier semanticsModifier6 = SemanticsModifierKt.semantics(companion1117, true, (Function1) value$iv15);
            if (enabled2) {
                z16 = false;
            } else {
                z16 = false;
            }
            boolean showCursor6 = z16;
            final Modifier cursorModifier6 = TextFieldCursorKt.cursor(Modifier.INSTANCE, state, value, offsetMapping3, cursorBrush2, showCursor6);
            ComposerKt.sourceInformationMarkerStart($composer3, -1704832829, "CC(remember):CoreTextField.kt#9igjgp");
            invalid$iv10 = $composer3.changedInstance(manager);
            Object it$iv1111110 = $composer3.rememberedValue();
            if (invalid$iv10) {
            }
            value$iv16 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                    final TextFieldSelectionManager textFieldSelectionManager = manager;
                    return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                        }
                    };
                }
            };
            $composer3.updateRememberedValue(value$iv16);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.DisposableEffect(manager, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv16, $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, -1704829391, "CC(remember):CoreTextField.kt#9igjgp");
            boolean zChangedInstance1116 = $composer3.changedInstance(state) | $composer3.changedInstance(textInputService);
            if (($dirty & 14) == 4) {
                z17 = true;
            } else {
                z17 = false;
            }
            invalid$iv11 = zChangedInstance1116 | z17 | (((($dirty1 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions5)) || ($dirty1 & 48) == 32);
            Object it$iv1111111 = $composer3.rememberedValue();
            if (invalid$iv11) {
            }
            value$iv17 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                    if (state.getHasFocus()) {
                        state.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation_release(textInputService, value, state.getProcessor(), imeOptions5, state.getOnValueChange(), state.getOnImeActionPerformed()));
                    }
                    return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                        }
                    };
                }
            };
            $composer3.updateRememberedValue(value$iv17);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.DisposableEffect(imeOptions5, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv17, $composer3, ($dirty1 >> 3) & 14);
            Modifier.Companion companion1118 = Modifier.INSTANCE;
            Function1<TextFieldValue, Unit> onValueChange6 = state.getOnValueChange();
            boolean z3110 = !readOnly2;
            if (maxLines5 == 1) {
                z18 = true;
            } else {
                z18 = false;
            }
            showHandleAndMagnifier = false;
            imeOptions6 = imeOptions5;
            boolean z3111 = z18;
            int $dirty8 = $dirty1;
            enabled3 = enabled2;
            Modifier textKeyInputModifier6 = TextFieldKeyInputKt.m1429textFieldKeyInput2WJ9YEU(companion1118, state, manager, value, onValueChange6, z3110, z3111, offsetMapping3, undoManager, imeOptions5.getImeAction());
            Modifier.Companion companion1119 = Modifier.INSTANCE;
            boolean zCoreTextField$lambda$16 = CoreTextField$lambda$11(writeable$delegate6);
            ComposerKt.sourceInformationMarkerStart($composer3, -1704798381, "CC(remember):CoreTextField.kt#9igjgp");
            invalid$iv12 = $composer3.changedInstance(state) | (((($dirty8 & 112) ^ 48) <= 32 && $composer3.changed(imeOptions6)) || ($dirty8 & 48) == 32) | $composer3.changedInstance(legacyTextInputServiceAdapter);
            Object it$iv1111112 = $composer3.rememberedValue();
            if (invalid$iv12) {
                value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        if (!state.getHasFocus()) {
                            focusRequester.requestFocus();
                        }
                        if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                            legacyTextInputServiceAdapter.startStylusHandwriting();
                        }
                        return true;
                    }
                };
                $composer3.updateRememberedValue(value$iv18);
            } else {
                value$iv18 = new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$stylusHandwritingModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        if (!state.getHasFocus()) {
                            focusRequester.requestFocus();
                        }
                        if (!KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6716getPasswordPjHm6EE()) && !KeyboardType.m6697equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m6715getNumberPasswordPjHm6EE())) {
                            legacyTextInputServiceAdapter.startStylusHandwriting();
                        }
                        return true;
                    }
                };
                $composer3.updateRememberedValue(value$iv18);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier stylusHandwritingModifier6 = StylusHandwritingKt.stylusHandwriting(companion1119, zCoreTextField$lambda$16, (Function0) value$iv18);
            Modifier decorationBoxModifier6 = OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier5, legacyTextInputServiceAdapter, state, manager).then(stylusHandwritingModifier6).then(focusModifier6), state, focusManager), state, manager).then(textKeyInputModifier6), scrollerPosition2, interactionSource11, enabled3).then(r15).then(semanticsModifier6), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                    invoke2(layoutCoordinates);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LayoutCoordinates it3) {
                    TextLayoutResultProxy layoutResult = state.getLayoutResult();
                    if (layoutResult == null) {
                        return;
                    }
                    layoutResult.setDecorationBoxCoordinates(it3);
                }
            });
            if (enabled3) {
                showHandleAndMagnifier = true;
            }
            if (showHandleAndMagnifier) {
                companionTextFieldMagnifier = TextFieldSelectionManager_androidKt.textFieldMagnifier(Modifier.INSTANCE, manager);
            } else {
                companionTextFieldMagnifier = Modifier.INSTANCE;
            }
            maxLines3 = maxLines5;
            final Modifier magnifierModifier6 = companionTextFieldMagnifier;
            final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function110 = function3M1344getLambda1$foundation_release;
            final TextStyle textStyle9 = textStyle2;
            final int i110 = minLines2;
            enabled4 = enabled3;
            imeOptions7 = imeOptions6;
            interactionSource4 = interactionSource11;
            visualTransformation3 = visualTransformation7;
            modifier3 = modifier5;
            final boolean z3112 = showHandleAndMagnifier;
            final boolean z3113 = readOnly2;
            final Function1<? super TextLayoutResult, Unit> function111 = function4;
            Function2<Composer, Integer, Unit> function112 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5
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

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C695@31947L4735,695@31933L4749:CoreTextField.kt#423gt5");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-374338080, $changed2, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:695)");
                        }
                        Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> function113 = function110;
                        final LegacyTextFieldState legacyTextFieldState = state;
                        final TextStyle textStyle10 = textStyle9;
                        final int i111 = i110;
                        final int i112 = maxLines3;
                        final TextFieldScrollerPosition textFieldScrollerPosition = scrollerPosition2;
                        final TextFieldValue textFieldValue = value;
                        final VisualTransformation visualTransformation8 = visualTransformation7;
                        final Modifier modifier6 = cursorModifier6;
                        final Modifier modifier7 = drawModifier6;
                        final Modifier modifier8 = onPositionedModifier6;
                        final Modifier modifier9 = magnifierModifier6;
                        final BringIntoViewRequester bringIntoViewRequester4 = bringIntoViewRequester3;
                        final TextFieldSelectionManager textFieldSelectionManager = manager;
                        final boolean z3114 = z3112;
                        final boolean z3115 = z3113;
                        final Function1<? super TextLayoutResult, Unit> function114 = function111;
                        final OffsetMapping offsetMapping4 = offsetMapping3;
                        final Density density2 = density;
                        function113.invoke(ComposableLambdaKt.rememberComposableLambda(2032502107, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1
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

                            public final void invoke(Composer $composer5, int $changed3) {
                                Object value$iv20;
                                ComposerKt.sourceInformation($composer5, "C711@32801L22,720@33160L3512,720@33124L3548:CoreTextField.kt#423gt5");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2032502107, $changed3, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:698)");
                                    }
                                    Modifier modifierHeightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m1067heightInVpY3zN4$default(Modifier.INSTANCE, legacyTextFieldState.m1368getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), textStyle10, i111, i112);
                                    TextFieldScrollerPosition textFieldScrollerPosition2 = textFieldScrollerPosition;
                                    TextFieldValue textFieldValue2 = textFieldValue;
                                    VisualTransformation visualTransformation9 = visualTransformation8;
                                    ComposerKt.sourceInformationMarkerStart($composer5, 713189947, "CC(remember):CoreTextField.kt#9igjgp");
                                    boolean invalid$iv14 = $composer5.changedInstance(legacyTextFieldState);
                                    final LegacyTextFieldState legacyTextFieldState2 = legacyTextFieldState;
                                    Object it$iv1111113 = $composer5.rememberedValue();
                                    if (invalid$iv14 || it$iv1111113 == Composer.INSTANCE.getEmpty()) {
                                        value$iv20 = new Function0<TextLayoutResultProxy>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$coreTextFieldModifier$1$1
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final TextLayoutResultProxy invoke() {
                                                return legacyTextFieldState2.getLayoutResult();
                                            }
                                        };
                                        $composer5.updateRememberedValue(value$iv20);
                                    } else {
                                        value$iv20 = it$iv1111113;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    Modifier coreTextFieldModifier = BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScrollKt.textFieldScroll(modifierHeightInLines, textFieldScrollerPosition2, textFieldValue2, visualTransformation9, (Function0) value$iv20).then(modifier6).then(modifier7), textStyle10).then(modifier8).then(modifier9), bringIntoViewRequester4);
                                    final TextFieldSelectionManager textFieldSelectionManager2 = textFieldSelectionManager;
                                    final LegacyTextFieldState legacyTextFieldState3 = legacyTextFieldState;
                                    final boolean z3116 = z3114;
                                    final boolean z3117 = z3115;
                                    final Function1<? super TextLayoutResult, Unit> function115 = function114;
                                    final TextFieldValue textFieldValue3 = textFieldValue;
                                    final OffsetMapping offsetMapping5 = offsetMapping4;
                                    final Density density3 = density2;
                                    final int i113 = i112;
                                    SimpleLayoutKt.SimpleLayout(coreTextFieldModifier, ComposableLambdaKt.rememberComposableLambda(-363167407, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1
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

                                        /* JADX WARN: Code duplicated, block: B:35:0x0131  */
                                        public final void invoke(Composer $composer6, int $changed4) {
                                            boolean z3118;
                                            ComposerKt.sourceInformation($composer6, "C721@33178L2880,776@36076L322:CoreTextField.kt#423gt5");
                                            if (($changed4 & 3) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-363167407, $changed4, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous>.<anonymous> (CoreTextField.kt:721)");
                                                }
                                                final LegacyTextFieldState legacyTextFieldState4 = legacyTextFieldState3;
                                                final Function1<? super TextLayoutResult, Unit> function116 = function115;
                                                final TextFieldValue textFieldValue4 = textFieldValue3;
                                                final OffsetMapping offsetMapping6 = offsetMapping5;
                                                final Density density4 = density3;
                                                final int i114 = i113;
                                                MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1.2
                                                    @Override // androidx.compose.ui.layout.MeasurePolicy
                                                    /* JADX INFO: renamed from: measure-3p2s80s */
                                                    public MeasureResult mo391measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
                                                        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                                                        LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState4;
                                                        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                                                        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                                                        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                                                        try {
                                                            TextLayoutResultProxy prevProxy = legacyTextFieldState5.getLayoutResult();
                                                            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                            TextLayoutResult prevResult = prevProxy != null ? prevProxy.getValue() : null;
                                                            Triple<Integer, Integer, TextLayoutResult> tripleM1421layout_EkL_Y$foundation_release = TextFieldDelegate.INSTANCE.m1421layout_EkL_Y$foundation_release(legacyTextFieldState4.getTextDelegate(), constraints, $this$measure_u2d3p2s80s.getLayoutDirection(), prevResult);
                                                            int width = tripleM1421layout_EkL_Y$foundation_release.component1().intValue();
                                                            int height = tripleM1421layout_EkL_Y$foundation_release.component2().intValue();
                                                            TextLayoutResult result = tripleM1421layout_EkL_Y$foundation_release.component3();
                                                            if (!Intrinsics.areEqual(prevResult, result)) {
                                                                legacyTextFieldState4.setLayoutResult(new TextLayoutResultProxy(result, null, prevProxy != null ? prevProxy.getDecorationBoxCoordinates() : null, 2, null));
                                                                function116.invoke(result);
                                                                CoreTextFieldKt.notifyFocusedRect(legacyTextFieldState4, textFieldValue4, offsetMapping6);
                                                            }
                                                            LegacyTextFieldState legacyTextFieldState6 = legacyTextFieldState4;
                                                            Density $this$measure_3p2s80s_u24lambda_u241 = density4;
                                                            legacyTextFieldState6.m1372setMinHeightForSingleLineField0680j_4($this$measure_3p2s80s_u24lambda_u241.mo723toDpu2uoSUM(i114 == 1 ? TextDelegateKt.ceilToIntPx(result.getLineBottom(0)) : 0));
                                                            HorizontalAlignmentLine firstBaseline = AlignmentLineKt.getFirstBaseline();
                                                            float $this$fastRoundToInt$iv = result.getFirstBaseline();
                                                            HorizontalAlignmentLine lastBaseline = AlignmentLineKt.getLastBaseline();
                                                            float $this$fastRoundToInt$iv2 = result.getLastBaseline();
                                                            return $this$measure_u2d3p2s80s.layout(width, height, MapsKt.mapOf(TuplesKt.to(firstBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv))), TuplesKt.to(lastBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv2)))), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1$2$measure$2
                                                                @Override // kotlin.jvm.functions.Function1
                                                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                    invoke2(placementScope);
                                                                    return Unit.INSTANCE;
                                                                }

                                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                public final void invoke2(Placeable.PlacementScope $this$layout) {
                                                                }
                                                            });
                                                        } catch (Throwable th) {
                                                            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                                            throw th;
                                                        }
                                                    }

                                                    @Override // androidx.compose.ui.layout.MeasurePolicy
                                                    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
                                                        legacyTextFieldState4.getTextDelegate().layoutIntrinsics($this$maxIntrinsicWidth.getLayoutDirection());
                                                        return legacyTextFieldState4.getTextDelegate().getMaxIntrinsicWidth();
                                                    }
                                                };
                                                ComposerKt.sourceInformationMarkerStart($composer6, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                                Modifier modifier$iv = Modifier.INSTANCE;
                                                int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                CompositionLocalMap localMap$iv = $composer6.getCurrentCompositionLocalMap();
                                                Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer6, modifier$iv);
                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                int $changed$iv$iv = ((0 << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                if (!($composer6.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer6.startReusableNode();
                                                if ($composer6.getInserting()) {
                                                    $composer6.createNode(constructor);
                                                } else {
                                                    $composer6.useNode();
                                                }
                                                Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer6);
                                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                                                    $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                                                    $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                                                }
                                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i20 = ($changed$iv$iv >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -1674249312, "C:CoreTextField.kt#423gt5");
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                $composer6.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                TextFieldSelectionManager textFieldSelectionManager3 = textFieldSelectionManager2;
                                                if (legacyTextFieldState3.getHandleState() == HandleState.None || legacyTextFieldState3.getLayoutCoordinates() == null) {
                                                    z3118 = false;
                                                } else {
                                                    LayoutCoordinates layoutCoordinates = legacyTextFieldState3.getLayoutCoordinates();
                                                    Intrinsics.checkNotNull(layoutCoordinates);
                                                    if (layoutCoordinates.isAttached() && z3116) {
                                                        z3118 = true;
                                                    } else {
                                                        z3118 = false;
                                                    }
                                                }
                                                CoreTextFieldKt.SelectionToolbarAndHandles(textFieldSelectionManager3, z3118, $composer6, 0);
                                                if (legacyTextFieldState3.getHandleState() == HandleState.Cursor && !z3117 && z3116) {
                                                    $composer6.startReplaceGroup(-2032274);
                                                    ComposerKt.sourceInformation($composer6, "789@36600L40");
                                                    CoreTextFieldKt.TextFieldCursorHandle(textFieldSelectionManager2, $composer6, 0);
                                                    $composer6.endReplaceGroup();
                                                } else {
                                                    $composer6.startReplaceGroup(-1955394);
                                                    $composer6.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    }, $composer5, 54), $composer5, 48, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }, $composer4, 54), $composer4, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            };
            $composer2 = $composer3;
            CoreTextFieldRootBox(decorationBoxModifier6, manager, ComposableLambdaKt.rememberComposableLambda(-374338080, true, function112, $composer2, 54), $composer2, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            final TextStyle textStyle10 = textStyle2;
            final VisualTransformation visualTransformation8 = visualTransformation3;
            final Function1<? super TextLayoutResult, Unit> function20 = function4;
            final MutableInteractionSource mutableInteractionSource = interactionSource4;
            final Brush brush = cursorBrush2;
            final boolean z40 = softWrap2;
            final int i20 = maxLines3;
            final int i21 = minLines2;
            final ImeOptions imeOptions20 = imeOptions7;
            final KeyboardActions keyboardActions4 = keyboardActions2;
            final boolean z41 = enabled4;
            final boolean z42 = readOnly2;
            final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function21 = function3M1344getLambda1$foundation_release;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.6
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

                public final void invoke(Composer composer, int i22) {
                    CoreTextFieldKt.CoreTextField(value, function1, modifier6, textStyle10, visualTransformation8, function20, mutableInteractionSource, brush, z40, i20, i21, imeOptions20, keyboardActions4, z41, z42, function21, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CoreTextField$lambda$11(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CoreTextFieldRootBox(final Modifier modifier, final TextFieldSelectionManager manager, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(-20551815);
        ComposerKt.sourceInformation($composer2, "C(CoreTextFieldRootBox)P(2,1)802@36846L95:CoreTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-20551815, $dirty, -1, "androidx.compose.foundation.text.CoreTextFieldRootBox (CoreTextField.kt:801)");
            }
            int $changed$iv = ($dirty & 14) | 384;
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1183915871, "C803@36902L33:CoreTextField.kt#423gt5");
            ContextMenu_androidKt.ContextMenuArea(manager, function2, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextFieldRootBox.2
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

                public final void invoke(Composer composer, int i3) {
                    CoreTextFieldKt.CoreTextFieldRootBox(modifier, manager, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final Modifier previewKeyEventToDeselectOnBack(Modifier $this$previewKeyEventToDeselectOnBack, final LegacyTextFieldState state, final TextFieldSelectionManager manager) {
        return KeyInputModifierKt.onPreviewKeyEvent($this$previewKeyEventToDeselectOnBack, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.previewKeyEventToDeselectOnBack.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m1347invokeZmokQxo(keyEvent.m5588unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m1347invokeZmokQxo(android.view.KeyEvent keyEvent) {
                boolean z;
                if (state.getHandleState() == HandleState.Selection && KeyEventHelpers_androidKt.m1349cancelsTextSelectionZmokQxo(keyEvent)) {
                    z = true;
                    TextFieldSelectionManager.m1748deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tapToFocus(LegacyTextFieldState state, FocusRequester focusRequester, boolean allowKeyboard) {
        SoftwareKeyboardController keyboardController;
        if (!state.getHasFocus()) {
            focusRequester.requestFocus();
        } else {
            if (!allowKeyboard || (keyboardController = state.getKeyboardController()) == null) {
                return;
            }
            keyboardController.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startInputSession(TextInputService textInputService, LegacyTextFieldState state, TextFieldValue value, ImeOptions imeOptions, OffsetMapping offsetMapping) {
        state.setInputSession(TextFieldDelegate.INSTANCE.onFocus$foundation_release(textInputService, value, state.getProcessor(), imeOptions, state.getOnValueChange(), state.getOnImeActionPerformed()));
        notifyFocusedRect(state, value, offsetMapping);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void endInputSession(LegacyTextFieldState state) {
        TextInputSession session = state.getInputSession();
        if (session != null) {
            TextFieldDelegate.INSTANCE.onBlur$foundation_release(session, state.getProcessor(), state.getOnValueChange());
        }
        state.setInputSession(null);
    }

    public static final Object bringSelectionEndIntoView(BringIntoViewRequester $this$bringSelectionEndIntoView, TextFieldValue value, TextDelegate textDelegate, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, Continuation<? super Unit> continuation) {
        Rect selectionEndBounds;
        int selectionEndInTransformed = offsetMapping.originalToTransformed(TextRange.m6476getMaximpl(value.getSelection()));
        if (selectionEndInTransformed < textLayoutResult.getLayoutInput().getText().length()) {
            selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed);
        } else if (selectionEndInTransformed != 0) {
            selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed - 1);
        } else {
            long defaultSize = TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null);
            selectionEndBounds = new Rect(0.0f, 0.0f, 1.0f, IntSize.m7185getHeightimpl(defaultSize));
        }
        Object objBringIntoView = $this$bringSelectionEndIntoView.bringIntoView(selectionEndBounds, continuation);
        return objBringIntoView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBringIntoView : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SelectionToolbarAndHandles(final TextFieldSelectionManager manager, final boolean show, Composer $composer, final int $changed) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        Composer $composer2 = $composer.startRestartGroup(626339208);
        ComposerKt.sourceInformation($composer2, "C(SelectionToolbarAndHandles):CoreTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(show) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(626339208, $dirty, -1, "androidx.compose.foundation.text.SelectionToolbarAndHandles (CoreTextField.kt:1146)");
            }
            if (show) {
                $composer2.startReplaceGroup(-1286242594);
                ComposerKt.sourceInformation($composer2, "");
                LegacyTextFieldState state = manager.getState();
                TextLayoutResult textLayoutResult = null;
                if (state != null && (layoutResult = state.getLayoutResult()) != null && (value = layoutResult.getValue()) != null) {
                    LegacyTextFieldState state2 = manager.getState();
                    if (!(state2 != null ? state2.getIsLayoutResultStale() : true)) {
                        textLayoutResult = value;
                    }
                }
                if (textLayoutResult == null) {
                    $composer2.startReplaceGroup(-1285984396);
                } else {
                    $composer2.startReplaceGroup(-1285984395);
                    ComposerKt.sourceInformation($composer2, "");
                    TextLayoutResult it = textLayoutResult;
                    if (!TextRange.m6473getCollapsedimpl(manager.getValue$foundation_release().getSelection())) {
                        $composer2.startReplaceGroup(-1680616096);
                        ComposerKt.sourceInformation($composer2, "");
                        int startOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m6479getStartimpl(manager.getValue$foundation_release().getSelection()));
                        int endOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m6474getEndimpl(manager.getValue$foundation_release().getSelection()));
                        ResolvedTextDirection startDirection = it.getBidiRunDirection(startOffset);
                        ResolvedTextDirection endDirection = it.getBidiRunDirection(Math.max(endOffset - 1, 0));
                        LegacyTextFieldState state3 = manager.getState();
                        if (state3 != null && state3.getShowSelectionHandleStart()) {
                            $composer2.startReplaceGroup(-1680216289);
                            ComposerKt.sourceInformation($composer2, "1158@51449L203");
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, startDirection, manager, $composer2, (($dirty << 6) & 896) | 6);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-1679975078);
                            $composer2.endReplaceGroup();
                        }
                        LegacyTextFieldState state4 = manager.getState();
                        if (state4 != null && state4.getShowSelectionHandleEnd()) {
                            $composer2.startReplaceGroup(-1679895904);
                            ComposerKt.sourceInformation($composer2, "1165@51772L202");
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, endDirection, manager, $composer2, (($dirty << 6) & 896) | 6);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-1679655654);
                            $composer2.endReplaceGroup();
                        }
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(-1679637798);
                        $composer2.endReplaceGroup();
                    }
                    LegacyTextFieldState textFieldState = manager.getState();
                    if (textFieldState != null) {
                        if (manager.isTextChanged$foundation_release()) {
                            textFieldState.setShowFloatingToolbar(false);
                        }
                        if (textFieldState.getHasFocus()) {
                            if (textFieldState.getShowFloatingToolbar()) {
                                manager.showSelectionToolbar$foundation_release();
                            } else {
                                manager.hideSelectionToolbar$foundation_release();
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
                $composer2.endReplaceGroup();
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(651305535);
                $composer2.endReplaceGroup();
                manager.hideSelectionToolbar$foundation_release();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.SelectionToolbarAndHandles.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    CoreTextFieldKt.SelectionToolbarAndHandles(manager, show, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0068  */
    /* JADX WARN: Code duplicated, block: B:64:0x0182  */
    public static final void TextFieldCursorHandle(final TextFieldSelectionManager manager, Composer $composer, final int $changed) {
        boolean z;
        Object value$iv;
        OffsetProvider value$iv2;
        CoreTextFieldKt$TextFieldCursorHandle$2$1 value$iv3;
        Object value$iv4;
        Composer $composer2 = $composer.startRestartGroup(-1436003720);
        ComposerKt.sourceInformation($composer2, "C(TextFieldCursorHandle):CoreTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1436003720, $dirty2, -1, "androidx.compose.foundation.text.TextFieldCursorHandle (CoreTextField.kt:1189)");
            }
            LegacyTextFieldState state = manager.getState();
            if (state != null && state.getShowCursorHandle()) {
                AnnotatedString transformedText$foundation_release = manager.getTransformedText$foundation_release();
                if (transformedText$foundation_release == null) {
                    z = false;
                } else {
                    if (transformedText$foundation_release.length() > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    $composer2.startReplaceGroup(-285446808);
                    ComposerKt.sourceInformation($composer2, "1191@52866L50,1192@52979L7,1194@53039L12,1196@53125L601,1208@53754L309,1193@52996L1077");
                    ComposerKt.sourceInformationMarkerStart($composer2, -424850346, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean invalid$iv = $composer2.changed(manager);
                    Object it$iv = $composer2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = manager.cursorDragObserver$foundation_release();
                        $composer2.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    TextDragObserver observer = (TextDragObserver) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    final long position = manager.m1754getCursorPositiontuRUvjQ$foundation_release((Density) objConsume);
                    ComposerKt.sourceInformationMarkerStart($composer2, -424844848, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean invalid$iv2 = $composer2.changed(position);
                    Object it$iv2 = $composer2.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1
                            @Override // androidx.compose.foundation.text.selection.OffsetProvider
                            /* JADX INFO: renamed from: provide-F1C5BW0 */
                            public final long mo1324provideF1C5BW0() {
                                return position;
                            }
                        };
                        $composer2.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv2;
                    }
                    OffsetProvider offsetProvider = (OffsetProvider) value$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, -424841507, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean invalid$iv3 = $composer2.changedInstance(observer) | $composer2.changedInstance(manager);
                    Object it$iv3 = $composer2.rememberedValue();
                    if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = new CoreTextFieldKt$TextFieldCursorHandle$2$1(observer, manager, null);
                        $composer2.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, observer, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv3);
                    ComposerKt.sourceInformationMarkerStart($composer2, -424821671, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean invalid$iv4 = $composer2.changed(position);
                    Object it$iv4 = $composer2.rememberedValue();
                    if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                        value$iv4 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                $this$semantics.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(Handle.Cursor, position, SelectionHandleAnchor.Middle, true, null));
                            }
                        };
                        $composer2.updateRememberedValue(value$iv4);
                    } else {
                        value$iv4 = it$iv4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    AndroidCursorHandle_androidKt.m1320CursorHandleUSBMPiE(offsetProvider, SemanticsModifierKt.semantics$default(modifierPointerInput, false, (Function1) value$iv4, 1, null), 0L, $composer2, 0, 4);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(-284257090);
                    $composer2.endReplaceGroup();
                }
            } else {
                $composer2.startReplaceGroup(-284257090);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.TextFieldCursorHandle.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    CoreTextFieldKt.TextFieldCursorHandle(manager, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyFocusedRect(LegacyTextFieldState state, TextFieldValue value, OffsetMapping offsetMapping) {
        TextInputSession inputSession;
        LayoutCoordinates layoutCoordinates;
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            TextLayoutResultProxy layoutResult = state.getLayoutResult();
            if (layoutResult != null && (inputSession = state.getInputSession()) != null && (layoutCoordinates = state.getLayoutCoordinates()) != null) {
                TextFieldDelegate.INSTANCE.notifyFocusedRect$foundation_release(value, state.getTextDelegate(), layoutResult.getValue(), layoutCoordinates, inputSession, state.getHasFocus(), offsetMapping);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
    }
}
