package androidx.compose.foundation.text.input.internal;

import android.view.KeyEvent;
import androidx.compose.foundation.text.DeadKeyCombiner;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyEventHelpers_androidKt;
import androidx.compose.foundation.text.KeyMapping;
import androidx.compose.foundation.text.KeyMapping_androidKt;
import androidx.compose.foundation.text.StringHelpers_jvmKt;
import androidx.compose.foundation.text.TextFieldKeyInput_androidKt;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldPreparedSelection;
import androidx.compose.foundation.text.input.internal.selection.TextFieldPreparedSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldKeyEventHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JP\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J:\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J:\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\n2\u0017\u0010$\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00170%¢\u0006\u0002\b'H\u0082\bJ\f\u0010(\u001a\u00020)*\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006*"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldKeyEventHandler;", "", "()V", "deadKeyCombiner", "Landroidx/compose/foundation/text/DeadKeyCombiner;", "keyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "preparedSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldPreparedSelectionState;", "onKeyEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/key/KeyEvent;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "editable", "singleLine", "onSubmit", "Lkotlin/Function0;", "", "onKeyEvent-6ptp14s", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;ZZLkotlin/jvm/functions/Function0;)Z", "onPreKeyEvent", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "onPreKeyEvent-MyFupTE", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/focus/FocusManager;Landroidx/compose/ui/platform/SoftwareKeyboardController;)Z", "preparedSelectionContext", "state", "isFromSoftKeyboard", "block", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldPreparedSelection;", "Lkotlin/ExtensionFunctionType;", "getVisibleTextLayoutHeight", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class TextFieldKeyEventHandler {
    public static final int $stable = 8;
    private final TextFieldPreparedSelectionState preparedSelectionState = new TextFieldPreparedSelectionState();
    private final DeadKeyCombiner deadKeyCombiner = new DeadKeyCombiner();
    private final KeyMapping keyMapping = KeyMapping_androidKt.getPlatformDefaultKeyMapping();

    /* JADX INFO: compiled from: TextFieldKeyEventHandler.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KeyCommand.values().length];
            try {
                iArr[KeyCommand.COPY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[KeyCommand.PASTE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[KeyCommand.CUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[KeyCommand.LEFT_CHAR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[KeyCommand.RIGHT_CHAR.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[KeyCommand.LEFT_WORD.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[KeyCommand.RIGHT_WORD.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[KeyCommand.PREV_PARAGRAPH.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[KeyCommand.NEXT_PARAGRAPH.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[KeyCommand.UP.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[KeyCommand.DOWN.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[KeyCommand.PAGE_UP.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[KeyCommand.PAGE_DOWN.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[KeyCommand.LINE_START.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[KeyCommand.LINE_END.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[KeyCommand.LINE_LEFT.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[KeyCommand.LINE_RIGHT.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[KeyCommand.HOME.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[KeyCommand.END.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[KeyCommand.DELETE_PREV_CHAR.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[KeyCommand.DELETE_NEXT_CHAR.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[KeyCommand.DELETE_PREV_WORD.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[KeyCommand.DELETE_NEXT_WORD.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[KeyCommand.DELETE_FROM_LINE_START.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[KeyCommand.DELETE_TO_LINE_END.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[KeyCommand.NEW_LINE.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[KeyCommand.TAB.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[KeyCommand.SELECT_ALL.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[KeyCommand.SELECT_LEFT_CHAR.ordinal()] = 29;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[KeyCommand.SELECT_RIGHT_CHAR.ordinal()] = 30;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[KeyCommand.SELECT_LEFT_WORD.ordinal()] = 31;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[KeyCommand.SELECT_RIGHT_WORD.ordinal()] = 32;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[KeyCommand.SELECT_PREV_PARAGRAPH.ordinal()] = 33;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[KeyCommand.SELECT_NEXT_PARAGRAPH.ordinal()] = 34;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_START.ordinal()] = 35;
            } catch (NoSuchFieldError e35) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_END.ordinal()] = 36;
            } catch (NoSuchFieldError e36) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_LEFT.ordinal()] = 37;
            } catch (NoSuchFieldError e37) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_RIGHT.ordinal()] = 38;
            } catch (NoSuchFieldError e38) {
            }
            try {
                iArr[KeyCommand.SELECT_UP.ordinal()] = 39;
            } catch (NoSuchFieldError e39) {
            }
            try {
                iArr[KeyCommand.SELECT_DOWN.ordinal()] = 40;
            } catch (NoSuchFieldError e40) {
            }
            try {
                iArr[KeyCommand.SELECT_PAGE_UP.ordinal()] = 41;
            } catch (NoSuchFieldError e41) {
            }
            try {
                iArr[KeyCommand.SELECT_PAGE_DOWN.ordinal()] = 42;
            } catch (NoSuchFieldError e42) {
            }
            try {
                iArr[KeyCommand.SELECT_HOME.ordinal()] = 43;
            } catch (NoSuchFieldError e43) {
            }
            try {
                iArr[KeyCommand.SELECT_END.ordinal()] = 44;
            } catch (NoSuchFieldError e44) {
            }
            try {
                iArr[KeyCommand.DESELECT.ordinal()] = 45;
            } catch (NoSuchFieldError e45) {
            }
            try {
                iArr[KeyCommand.UNDO.ordinal()] = 46;
            } catch (NoSuchFieldError e46) {
            }
            try {
                iArr[KeyCommand.REDO.ordinal()] = 47;
            } catch (NoSuchFieldError e47) {
            }
            try {
                iArr[KeyCommand.CHARACTER_PALETTE.ordinal()] = 48;
            } catch (NoSuchFieldError e48) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: onPreKeyEvent-MyFupTE */
    public boolean mo1487onPreKeyEventMyFupTE(KeyEvent event, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, FocusManager focusManager, SoftwareKeyboardController keyboardController) {
        long selection = textFieldState.getVisualText().getSelection();
        if (!TextRange.m6473getCollapsedimpl(selection) && KeyEventHelpers_androidKt.m1349cancelsTextSelectionZmokQxo(event)) {
            textFieldSelectionState.deselect();
            return true;
        }
        return false;
    }

    /* JADX INFO: renamed from: onKeyEvent-6ptp14s */
    public boolean mo1486onKeyEvent6ptp14s(KeyEvent event, TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, boolean editable, boolean singleLine, Function0<Unit> onSubmit) {
        boolean consumed;
        Integer codePoint;
        if (!KeyEventType.m5592equalsimpl0(KeyEvent_androidKt.m5600getTypeZmokQxo(event), KeyEventType.INSTANCE.m5596getKeyDownCS__XNY())) {
            return false;
        }
        if (TextFieldKeyInput_androidKt.m1432isTypedEventZmokQxo(event) && (codePoint = this.deadKeyCombiner.m1348consumeZmokQxo(event)) != null) {
            String text = StringHelpers_jvmKt.appendCodePointX(new StringBuilder(2), codePoint.intValue()).toString();
            if (!editable) {
                return false;
            }
            boolean restartImeIfContentChanges$iv = !TextFieldKeyEventHandler_androidKt.m1558isFromSoftKeyboardZmokQxo(event);
            TextFieldState $this$iv$iv = textFieldState.textFieldState;
            InputTransformation inputTransformation$iv$iv = textFieldState.inputTransformation;
            TextFieldEditUndoBehavior undoBehavior$iv$iv = TextFieldEditUndoBehavior.MergeIfPossible;
            $this$iv$iv.getMainBuffer().getChangeTracker().clearChanges();
            EditingBuffer $this$onKeyEvent_6ptp14s_u24lambda_u240 = $this$iv$iv.getMainBuffer();
            $this$onKeyEvent_6ptp14s_u24lambda_u240.commitComposition();
            EditCommandKt.commitText($this$onKeyEvent_6ptp14s_u24lambda_u240, text, 1);
            $this$iv$iv.commitEditAsUser(inputTransformation$iv$iv, restartImeIfContentChanges$iv, undoBehavior$iv$iv);
            this.preparedSelectionState.resetCachedX();
            return true;
        }
        KeyCommand command = this.keyMapping.mo1350mapZmokQxo(event);
        if (command == null || (command.getEditsText() && !editable)) {
            return false;
        }
        boolean isFromSoftKeyboard$iv = TextFieldKeyEventHandler_androidKt.m1558isFromSoftKeyboardZmokQxo(event);
        TextLayoutResult layoutResult$iv = textLayoutState.getLayoutResult();
        float visibleTextLayoutHeight$iv = getVisibleTextLayoutHeight(textLayoutState);
        TextFieldPreparedSelection preparedSelection$iv = new TextFieldPreparedSelection(textFieldState, layoutResult$iv, isFromSoftKeyboard$iv, visibleTextLayoutHeight$iv, this.preparedSelectionState);
        TextRange textRangeM6467boximpl = null;
        switch (WhenMappings.$EnumSwitchMapping$0[command.ordinal()]) {
            case 1:
                textFieldSelectionState.copy(false);
                consumed = true;
                break;
            case 2:
                textFieldSelectionState.paste();
                consumed = true;
                break;
            case 3:
                textFieldSelectionState.cut();
                consumed = true;
                break;
            case 4:
                preparedSelection$iv.collapseLeftOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldKeyEventHandler$onKeyEvent$2$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                        invoke2(textFieldPreparedSelection);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextFieldPreparedSelection $this$collapseLeftOr) {
                        $this$collapseLeftOr.moveCursorLeft();
                    }
                });
                consumed = true;
                break;
            case 5:
                preparedSelection$iv.collapseRightOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldKeyEventHandler$onKeyEvent$2$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                        invoke2(textFieldPreparedSelection);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextFieldPreparedSelection $this$collapseRightOr) {
                        $this$collapseRightOr.moveCursorRight();
                    }
                });
                consumed = true;
                break;
            case 6:
                preparedSelection$iv.moveCursorLeftByWord();
                consumed = true;
                break;
            case 7:
                preparedSelection$iv.moveCursorRightByWord();
                consumed = true;
                break;
            case 8:
                preparedSelection$iv.moveCursorPrevByParagraph();
                consumed = true;
                break;
            case 9:
                preparedSelection$iv.moveCursorNextByParagraph();
                consumed = true;
                break;
            case 10:
                preparedSelection$iv.moveCursorUpByLine();
                consumed = true;
                break;
            case 11:
                preparedSelection$iv.moveCursorDownByLine();
                consumed = true;
                break;
            case 12:
                preparedSelection$iv.moveCursorUpByPage();
                consumed = true;
                break;
            case 13:
                preparedSelection$iv.moveCursorDownByPage();
                consumed = true;
                break;
            case 14:
                preparedSelection$iv.moveCursorToLineStart();
                consumed = true;
                break;
            case 15:
                preparedSelection$iv.moveCursorToLineEnd();
                consumed = true;
                break;
            case 16:
                preparedSelection$iv.moveCursorToLineLeftSide();
                consumed = true;
                break;
            case 17:
                preparedSelection$iv.moveCursorToLineRightSide();
                consumed = true;
                break;
            case 18:
                preparedSelection$iv.moveCursorToHome();
                consumed = true;
                break;
            case 19:
                preparedSelection$iv.moveCursorToEnd();
                consumed = true;
                break;
            case 20:
                if (TextRange.m6473getCollapsedimpl(preparedSelection$iv.getSelection())) {
                    Integer numValueOf = Integer.valueOf(preparedSelection$iv.getPrecedingCharacterIndex());
                    int it = numValueOf.intValue();
                    if (!(it != -1)) {
                        numValueOf = null;
                    }
                    if (numValueOf != null) {
                        int it2 = numValueOf.intValue();
                        textRangeM6467boximpl = TextRange.m6467boximpl(TextRangeKt.TextRange(it2, TextRange.m6474getEndimpl(preparedSelection$iv.getSelection())));
                    }
                    if (textRangeM6467boximpl != null) {
                        long it$iv = textRangeM6467boximpl.getPackedValue();
                        TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", it$iv, null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                    }
                } else {
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", preparedSelection$iv.getSelection(), null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                }
                consumed = true;
                break;
            case 21:
                if (TextRange.m6473getCollapsedimpl(preparedSelection$iv.getSelection())) {
                    Integer numValueOf2 = Integer.valueOf(preparedSelection$iv.getNextCharacterIndex());
                    int it3 = numValueOf2.intValue();
                    if (!(it3 != -1)) {
                        numValueOf2 = null;
                    }
                    if (numValueOf2 != null) {
                        int it4 = numValueOf2.intValue();
                        textRangeM6467boximpl = TextRange.m6467boximpl(TextRangeKt.TextRange(TextRange.m6479getStartimpl(preparedSelection$iv.getSelection()), it4));
                    }
                    if (textRangeM6467boximpl != null) {
                        long it$iv2 = textRangeM6467boximpl.getPackedValue();
                        TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", it$iv2, null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                    }
                } else {
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", preparedSelection$iv.getSelection(), null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                }
                consumed = true;
                break;
            case 22:
                if (TextRange.m6473getCollapsedimpl(preparedSelection$iv.getSelection())) {
                    long it$iv3 = TextRangeKt.TextRange(preparedSelection$iv.getPreviousWordOffset(), TextRange.m6474getEndimpl(preparedSelection$iv.getSelection()));
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", it$iv3, null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                } else {
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", preparedSelection$iv.getSelection(), null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                }
                consumed = true;
                break;
            case 23:
                if (TextRange.m6473getCollapsedimpl(preparedSelection$iv.getSelection())) {
                    long it$iv4 = TextRangeKt.TextRange(TextRange.m6479getStartimpl(preparedSelection$iv.getSelection()), preparedSelection$iv.getNextWordOffset());
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", it$iv4, null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                } else {
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", preparedSelection$iv.getSelection(), null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                }
                consumed = true;
                break;
            case 24:
                if (TextRange.m6473getCollapsedimpl(preparedSelection$iv.getSelection())) {
                    long it$iv5 = TextRangeKt.TextRange(preparedSelection$iv.getLineStartByOffset(), TextRange.m6474getEndimpl(preparedSelection$iv.getSelection()));
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", it$iv5, null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                } else {
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", preparedSelection$iv.getSelection(), null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                }
                consumed = true;
                break;
            case 25:
                if (TextRange.m6473getCollapsedimpl(preparedSelection$iv.getSelection())) {
                    long it$iv6 = TextRangeKt.TextRange(TextRange.m6479getStartimpl(preparedSelection$iv.getSelection()), preparedSelection$iv.getLineEndByOffset());
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", it$iv6, null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                } else {
                    TransformedTextFieldState.m1579replaceTextM8tDOmk$default(preparedSelection$iv.state, "", preparedSelection$iv.getSelection(), null, !preparedSelection$iv.isFromSoftKeyboard, 4, null);
                }
                consumed = true;
                break;
            case 26:
                if (singleLine) {
                    onSubmit.invoke();
                } else {
                    TextFieldState $this$iv$iv2 = textFieldState.textFieldState;
                    InputTransformation inputTransformation$iv$iv2 = textFieldState.inputTransformation;
                    TextFieldEditUndoBehavior undoBehavior$iv$iv2 = TextFieldEditUndoBehavior.MergeIfPossible;
                    $this$iv$iv2.getMainBuffer().getChangeTracker().clearChanges();
                    EditingBuffer $this$onKeyEvent_6ptp14s_u24lambda_u2413_u24lambda_u2411 = $this$iv$iv2.getMainBuffer();
                    $this$onKeyEvent_6ptp14s_u24lambda_u2413_u24lambda_u2411.commitComposition();
                    EditCommandKt.commitText($this$onKeyEvent_6ptp14s_u24lambda_u2413_u24lambda_u2411, "\n", 1);
                    $this$iv$iv2.commitEditAsUser(inputTransformation$iv$iv2, true, undoBehavior$iv$iv2);
                }
                consumed = true;
                break;
            case 27:
                if (!singleLine) {
                    TextFieldState $this$iv$iv3 = textFieldState.textFieldState;
                    InputTransformation inputTransformation$iv$iv3 = textFieldState.inputTransformation;
                    TextFieldEditUndoBehavior undoBehavior$iv$iv3 = TextFieldEditUndoBehavior.MergeIfPossible;
                    $this$iv$iv3.getMainBuffer().getChangeTracker().clearChanges();
                    EditingBuffer $this$onKeyEvent_6ptp14s_u24lambda_u2413_u24lambda_u2412 = $this$iv$iv3.getMainBuffer();
                    $this$onKeyEvent_6ptp14s_u24lambda_u2413_u24lambda_u2412.commitComposition();
                    EditCommandKt.commitText($this$onKeyEvent_6ptp14s_u24lambda_u2413_u24lambda_u2412, "\t", 1);
                    $this$iv$iv3.commitEditAsUser(inputTransformation$iv$iv3, true, undoBehavior$iv$iv3);
                    consumed = true;
                } else {
                    consumed = false;
                }
                break;
            case MotionEventCompat.AXIS_RELATIVE_Y /* 28 */:
                preparedSelection$iv.selectAll();
                consumed = true;
                break;
            case 29:
                preparedSelection$iv.moveCursorLeft().selectMovement();
                consumed = true;
                break;
            case 30:
                preparedSelection$iv.moveCursorRight().selectMovement();
                consumed = true;
                break;
            case 31:
                preparedSelection$iv.moveCursorLeftByWord().selectMovement();
                consumed = true;
                break;
            case 32:
                preparedSelection$iv.moveCursorRightByWord().selectMovement();
                consumed = true;
                break;
            case 33:
                preparedSelection$iv.moveCursorPrevByParagraph().selectMovement();
                consumed = true;
                break;
            case 34:
                preparedSelection$iv.moveCursorNextByParagraph().selectMovement();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                preparedSelection$iv.moveCursorToLineStart().selectMovement();
                consumed = true;
                break;
            case 36:
                preparedSelection$iv.moveCursorToLineEnd().selectMovement();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_6 /* 37 */:
                preparedSelection$iv.moveCursorToLineLeftSide().selectMovement();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                preparedSelection$iv.moveCursorToLineRightSide().selectMovement();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_8 /* 39 */:
                preparedSelection$iv.moveCursorUpByLine().selectMovement();
                consumed = true;
                break;
            case 40:
                preparedSelection$iv.moveCursorDownByLine().selectMovement();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_10 /* 41 */:
                preparedSelection$iv.moveCursorUpByPage().selectMovement();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                preparedSelection$iv.moveCursorDownByPage().selectMovement();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                preparedSelection$iv.moveCursorToHome().selectMovement();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
                preparedSelection$iv.moveCursorToEnd().selectMovement();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_14 /* 45 */:
                preparedSelection$iv.deselect();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                textFieldState.undo();
                consumed = true;
                break;
            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                textFieldState.redo();
                consumed = true;
                break;
            case 48:
                KeyEventHelpers_androidKt.showCharacterPalette();
                consumed = true;
                break;
            default:
                consumed = true;
                break;
        }
        if (!TextRange.m6472equalsimpl0(preparedSelection$iv.getSelection(), preparedSelection$iv.getInitialValue().getSelection())) {
            textFieldState.m1586selectCharsIn5zctL8(preparedSelection$iv.getSelection());
        }
        return consumed;
    }

    private final void preparedSelectionContext(TransformedTextFieldState state, TextLayoutState textLayoutState, boolean isFromSoftKeyboard, Function1<? super TextFieldPreparedSelection, Unit> block) {
        TextLayoutResult layoutResult = textLayoutState.getLayoutResult();
        float visibleTextLayoutHeight = getVisibleTextLayoutHeight(textLayoutState);
        TextFieldPreparedSelection preparedSelection = new TextFieldPreparedSelection(state, layoutResult, isFromSoftKeyboard, visibleTextLayoutHeight, this.preparedSelectionState);
        block.invoke(preparedSelection);
        if (!TextRange.m6472equalsimpl0(preparedSelection.getSelection(), preparedSelection.getInitialValue().getSelection())) {
            state.m1586selectCharsIn5zctL8(preparedSelection.getSelection());
        }
    }

    private final float getVisibleTextLayoutHeight(TextLayoutState $this$getVisibleTextLayoutHeight) {
        LayoutCoordinates textLayoutCoordinates = $this$getVisibleTextLayoutHeight.getTextLayoutNodeCoordinates();
        if (textLayoutCoordinates != null) {
            Rect rectLocalBoundingBoxOf$default = null;
            if (!textLayoutCoordinates.isAttached()) {
                textLayoutCoordinates = null;
            }
            if (textLayoutCoordinates != null) {
                LayoutCoordinates it = $this$getVisibleTextLayoutHeight.getDecoratorNodeCoordinates();
                if (it != null) {
                    if (!it.isAttached()) {
                        it = null;
                    }
                    if (it != null) {
                        rectLocalBoundingBoxOf$default = LayoutCoordinates.localBoundingBoxOf$default(it, textLayoutCoordinates, false, 2, null);
                    }
                }
                if (rectLocalBoundingBoxOf$default != null) {
                    return Size.m4359getHeightimpl(rectLocalBoundingBoxOf$default.m4326getSizeNHjbRc());
                }
            }
        }
        return Float.NaN;
    }
}
