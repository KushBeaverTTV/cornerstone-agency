package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.HandleState;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextDelegate;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursorKt;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.UndoManager;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextFieldValueKt;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\r\u0010p\u001a\u00020FH\u0000¢\u0006\u0002\bqJ\u0018\u0010r\u001a\u00020F2\u0006\u0010s\u001a\u00020\fø\u0001\u0000¢\u0006\u0004\bt\u0010uJ\u0017\u0010v\u001a\u00020F2\b\b\u0002\u0010w\u001a\u00020!H\u0000¢\u0006\u0002\bxJ\"\u0010y\u001a\u00020C2\u0006\u0010z\u001a\u00020_2\u0006\u0010{\u001a\u00020|H\u0002ø\u0001\u0000¢\u0006\u0004\b}\u0010~J\u000e\u0010\u007f\u001a\u00020[H\u0000¢\u0006\u0003\b\u0080\u0001J\u000f\u0010\u0081\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u0082\u0001J\u001e\u0010\u0083\u0001\u001a\u00020F2\n\b\u0002\u0010s\u001a\u0004\u0018\u00010\fH\u0000ø\u0001\u0000¢\u0006\u0003\b\u0084\u0001J\u001a\u0010\u0085\u0001\u001a\u00020F2\t\b\u0002\u0010\u0086\u0001\u001a\u00020!H\u0000¢\u0006\u0003\b\u0087\u0001J\u000f\u0010\u0088\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u0089\u0001J\n\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0002J\"\u0010\u008c\u0001\u001a\u00020\f2\b\u0010\u008d\u0001\u001a\u00030\u008e\u0001H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J!\u0010\u0091\u0001\u001a\u00020\f2\u0007\u0010\u0092\u0001\u001a\u00020!H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J\u0018\u0010\u0095\u0001\u001a\u00020[2\u0007\u0010\u0092\u0001\u001a\u00020!H\u0000¢\u0006\u0003\b\u0096\u0001J\u000f\u0010\u0097\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u0098\u0001J\u000f\u0010\u0099\u0001\u001a\u00020!H\u0000¢\u0006\u0003\b\u009a\u0001J\u000f\u0010\u009b\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u009c\u0001J\u000f\u0010\u009d\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u009e\u0001J\u001d\u0010\u009f\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020|H\u0000ø\u0001\u0000¢\u0006\u0005\b¡\u0001\u0010uJ\u0013\u0010¢\u0001\u001a\u00020F2\b\u0010£\u0001\u001a\u00030¤\u0001H\u0002J\u001d\u0010¥\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020|H\u0000ø\u0001\u0000¢\u0006\u0005\b¦\u0001\u0010uJ\u000f\u0010§\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b¨\u0001J\u0012\u0010©\u0001\u001a\u00020F2\u0007\u0010ª\u0001\u001a\u00020!H\u0002JK\u0010«\u0001\u001a\u00020|2\u0006\u0010d\u001a\u00020C2\u0007\u0010¬\u0001\u001a\u00020\f2\u0007\u0010\u00ad\u0001\u001a\u00020!2\u0007\u0010\u0092\u0001\u001a\u00020!2\b\u0010®\u0001\u001a\u00030¯\u00012\u0007\u0010°\u0001\u001a\u00020!H\u0002ø\u0001\u0000¢\u0006\u0006\b±\u0001\u0010²\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR5\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f8F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\fX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\fX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0018R/\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u000b\u001a\u0004\u0018\u00010\u001a8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b \u0010\u0013\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR+\u0010\"\u001a\u00020!2\u0006\u0010\u000b\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0013\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R+\u0010(\u001a\u00020!2\u0006\u0010\u000b\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010\u0013\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u00102\u001a\u0004\u0018\u000103X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0014\u00108\u001a\u000209X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020=X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u000e\u0010B\u001a\u00020CX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010D\u001a\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020F0EX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u000e\u0010K\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010N\u001a\u0004\u0018\u00010OX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001c\u0010T\u001a\u0004\u0018\u00010UX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u0014\u0010Z\u001a\u00020[X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010]R\u0016\u0010^\u001a\u0004\u0018\u00010_8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bb\u0010cR+\u0010d\u001a\u00020C2\u0006\u0010\u000b\u001a\u00020C8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bi\u0010\u0013\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\u001a\u0010j\u001a\u00020kX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010o\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006³\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "", "undoManager", "Landroidx/compose/foundation/text/UndoManager;", "(Landroidx/compose/foundation/text/UndoManager;)V", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "getClipboardManager$foundation_release", "()Landroidx/compose/ui/platform/ClipboardManager;", "setClipboardManager$foundation_release", "(Landroidx/compose/ui/platform/ClipboardManager;)V", "<set-?>", "Landroidx/compose/ui/geometry/Offset;", "currentDragPosition", "getCurrentDragPosition-_m7T9-E", "()Landroidx/compose/ui/geometry/Offset;", "setCurrentDragPosition-_kEHs6E", "(Landroidx/compose/ui/geometry/Offset;)V", "currentDragPosition$delegate", "Landroidx/compose/runtime/MutableState;", "dragBeginOffsetInText", "", "Ljava/lang/Integer;", "dragBeginPosition", "J", "dragTotalDistance", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "", "editable", "getEditable", "()Z", "setEditable", "(Z)V", "editable$delegate", "enabled", "getEnabled", "setEnabled", "enabled$delegate", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "getMouseSelectionObserver$foundation_release", "()Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "getOffsetMapping$foundation_release", "()Landroidx/compose/ui/text/input/OffsetMapping;", "setOffsetMapping$foundation_release", "(Landroidx/compose/ui/text/input/OffsetMapping;)V", "oldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "", "getOnValueChange$foundation_release", "()Lkotlin/jvm/functions/Function1;", "setOnValueChange$foundation_release", "(Lkotlin/jvm/functions/Function1;)V", "previousRawDragOffset", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "getState$foundation_release", "()Landroidx/compose/foundation/text/LegacyTextFieldState;", "setState$foundation_release", "(Landroidx/compose/foundation/text/LegacyTextFieldState;)V", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "touchSelectionObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "getTouchSelectionObserver$foundation_release", "()Landroidx/compose/foundation/text/TextDragObserver;", "transformedText", "Landroidx/compose/ui/text/AnnotatedString;", "getTransformedText$foundation_release", "()Landroidx/compose/ui/text/AnnotatedString;", "getUndoManager", "()Landroidx/compose/foundation/text/UndoManager;", "value", "getValue$foundation_release", "()Landroidx/compose/ui/text/input/TextFieldValue;", "setValue$foundation_release", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "value$delegate", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "getVisualTransformation$foundation_release", "()Landroidx/compose/ui/text/input/VisualTransformation;", "setVisualTransformation$foundation_release", "(Landroidx/compose/ui/text/input/VisualTransformation;)V", "clearPreviewHighlight", "clearPreviewHighlight$foundation_release", "contextMenuOpenAdjustment", "position", "contextMenuOpenAdjustment-k-4lQ0M", "(J)V", "copy", "cancelSelection", "copy$foundation_release", "createTextFieldValue", "annotatedString", "selection", "Landroidx/compose/ui/text/TextRange;", "createTextFieldValue-FDrldGo", "(Landroidx/compose/ui/text/AnnotatedString;J)Landroidx/compose/ui/text/input/TextFieldValue;", "cursorDragObserver", "cursorDragObserver$foundation_release", "cut", "cut$foundation_release", "deselect", "deselect-_kEHs6E$foundation_release", "enterSelectionMode", "showFloatingToolbar", "enterSelectionMode$foundation_release", "exitSelectionMode", "exitSelectionMode$foundation_release", "getContentRect", "Landroidx/compose/ui/geometry/Rect;", "getCursorPosition", "density", "Landroidx/compose/ui/unit/Density;", "getCursorPosition-tuRUvjQ$foundation_release", "(Landroidx/compose/ui/unit/Density;)J", "getHandlePosition", "isStartHandle", "getHandlePosition-tuRUvjQ$foundation_release", "(Z)J", "handleDragObserver", "handleDragObserver$foundation_release", "hideSelectionToolbar", "hideSelectionToolbar$foundation_release", "isTextChanged", "isTextChanged$foundation_release", "paste", "paste$foundation_release", "selectAll", "selectAll$foundation_release", "setDeletionPreviewHighlight", "range", "setDeletionPreviewHighlight-5zc-tL8$foundation_release", "setHandleState", "handleState", "Landroidx/compose/foundation/text/HandleState;", "setSelectionPreviewHighlight", "setSelectionPreviewHighlight-5zc-tL8$foundation_release", "showSelectionToolbar", "showSelectionToolbar$foundation_release", "updateFloatingToolbar", "show", "updateSelection", "currentPosition", "isStartOfSelection", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "isTouchBasedSelection", "updateSelection-8UEBfa8", "(Landroidx/compose/ui/text/input/TextFieldValue;JZZLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSelectionManager {
    public static final int $stable = 8;
    private ClipboardManager clipboardManager;

    /* JADX INFO: renamed from: currentDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState currentDragPosition;
    private Integer dragBeginOffsetInText;
    private long dragBeginPosition;
    private long dragTotalDistance;

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle;

    /* JADX INFO: renamed from: editable$delegate, reason: from kotlin metadata */
    private final MutableState editable;

    /* JADX INFO: renamed from: enabled$delegate, reason: from kotlin metadata */
    private final MutableState enabled;
    private FocusRequester focusRequester;
    private HapticFeedback hapticFeedBack;
    private final MouseSelectionObserver mouseSelectionObserver;
    private OffsetMapping offsetMapping;
    private TextFieldValue oldValue;
    private Function1<? super TextFieldValue, Unit> onValueChange;
    private int previousRawDragOffset;
    private SelectionLayout previousSelectionLayout;
    private LegacyTextFieldState state;
    private TextToolbar textToolbar;
    private final TextDragObserver touchSelectionObserver;
    private final UndoManager undoManager;

    /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;
    private VisualTransformation visualTransformation;

    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldSelectionManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public TextFieldSelectionManager(UndoManager undoManager) {
        this.undoManager = undoManager;
        this.offsetMapping = ValidatingOffsetMappingKt.getValidatingEmptyOffsetMappingIdentity();
        this.onValueChange = new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$onValueChange$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                invoke2(textFieldValue);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TextFieldValue it) {
            }
        };
        this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null), null, 2, null);
        this.visualTransformation = VisualTransformation.INSTANCE.getNone();
        this.editable = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        this.enabled = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        this.dragBeginPosition = Offset.INSTANCE.m4309getZeroF1C5BW0();
        this.dragTotalDistance = Offset.INSTANCE.m4309getZeroF1C5BW0();
        this.draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.currentDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.previousRawDragOffset = -1;
        this.oldValue = new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null);
        this.touchSelectionObserver = new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1411onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo1413onStartk4lQ0M(long startPoint) {
                TextLayoutResultProxy layoutResult;
                TextLayoutResultProxy layoutResult2;
                if (this.this$0.getEnabled() && this.this$0.getDraggingHandle() == null) {
                    this.this$0.setDraggingHandle(Handle.SelectionEnd);
                    this.this$0.previousRawDragOffset = -1;
                    this.this$0.hideSelectionToolbar$foundation_release();
                    LegacyTextFieldState state = this.this$0.getState();
                    if (!((state == null || (layoutResult2 = state.getLayoutResult()) == null || !layoutResult2.m1445isPositionOnTextk4lQ0M(startPoint)) ? false : true)) {
                        LegacyTextFieldState state2 = this.this$0.getState();
                        if (state2 != null && (layoutResult = state2.getLayoutResult()) != null) {
                            TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                            int transformedOffset = TextLayoutResultProxy.m1443getOffsetForPosition3MmeM6k$default(layoutResult, startPoint, false, 2, null);
                            int offset = textFieldSelectionManager.getOffsetMapping().transformedToOriginal(transformedOffset);
                            TextFieldValue newValue = textFieldSelectionManager.m1747createTextFieldValueFDrldGo(textFieldSelectionManager.getValue$foundation_release().getText(), TextRangeKt.TextRange(offset, offset));
                            textFieldSelectionManager.enterSelectionMode$foundation_release(false);
                            HapticFeedback hapticFeedBack = textFieldSelectionManager.getHapticFeedBack();
                            if (hapticFeedBack != null) {
                                hapticFeedBack.mo5262performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5271getTextHandleMove5zf0vsI());
                            }
                            textFieldSelectionManager.getOnValueChange$foundation_release().invoke(newValue);
                        }
                    } else {
                        if (this.this$0.getValue$foundation_release().getText().length() == 0) {
                            return;
                        }
                        this.this$0.enterSelectionMode$foundation_release(false);
                        long adjustedStartSelection = this.this$0.m1750updateSelection8UEBfa8(TextFieldValue.m6721copy3r_uNRQ$default(this.this$0.getValue$foundation_release(), (AnnotatedString) null, TextRange.INSTANCE.m6484getZerod9O1mEE(), (TextRange) null, 5, (Object) null), startPoint, true, false, SelectionAdjustment.INSTANCE.getWord(), true);
                        this.this$0.dragBeginOffsetInText = Integer.valueOf(TextRange.m6479getStartimpl(adjustedStartSelection));
                    }
                    this.this$0.setHandleState(HandleState.None);
                    this.this$0.dragBeginPosition = startPoint;
                    this.this$0.m1749setCurrentDragPosition_kEHs6E(Offset.m4282boximpl(this.this$0.dragBeginPosition));
                    this.this$0.dragTotalDistance = Offset.INSTANCE.m4309getZeroF1C5BW0();
                }
            }

            /* JADX WARN: Code duplicated, block: B:23:0x00c5  */
            /* JADX WARN: Code duplicated, block: B:25:0x00cb  */
            /* JADX WARN: Code duplicated, block: B:26:0x00d0  */
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo1412onDragk4lQ0M(long delta) {
                TextLayoutResultProxy layoutResult;
                Integer num;
                int iM1444getOffsetForPosition3MmeM6k;
                long jM1750updateSelection8UEBfa8;
                SelectionAdjustment adjustment;
                if (this.this$0.getEnabled()) {
                    if (this.this$0.getValue$foundation_release().getText().length() == 0) {
                        return;
                    }
                    TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                    textFieldSelectionManager.dragTotalDistance = Offset.m4298plusMKHz9U(textFieldSelectionManager.dragTotalDistance, delta);
                    LegacyTextFieldState state = this.this$0.getState();
                    if (state != null && (layoutResult = state.getLayoutResult()) != null) {
                        TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                        textFieldSelectionManager2.m1749setCurrentDragPosition_kEHs6E(Offset.m4282boximpl(Offset.m4298plusMKHz9U(textFieldSelectionManager2.dragBeginPosition, textFieldSelectionManager2.dragTotalDistance)));
                        if (textFieldSelectionManager2.dragBeginOffsetInText != null) {
                            num = textFieldSelectionManager2.dragBeginOffsetInText;
                            if (num != null) {
                                iM1444getOffsetForPosition3MmeM6k = num.intValue();
                            } else {
                                iM1444getOffsetForPosition3MmeM6k = layoutResult.m1444getOffsetForPosition3MmeM6k(textFieldSelectionManager2.dragBeginPosition, false);
                            }
                            int startOffset = iM1444getOffsetForPosition3MmeM6k;
                            Offset offsetM1753getCurrentDragPosition_m7T9E = textFieldSelectionManager2.m1753getCurrentDragPosition_m7T9E();
                            Intrinsics.checkNotNull(offsetM1753getCurrentDragPosition_m7T9E);
                            int endOffset = layoutResult.m1444getOffsetForPosition3MmeM6k(offsetM1753getCurrentDragPosition_m7T9E.getPackedValue(), false);
                            if (textFieldSelectionManager2.dragBeginOffsetInText != null) {
                            }
                            TextFieldValue value$foundation_release = textFieldSelectionManager2.getValue$foundation_release();
                            Offset offsetM1753getCurrentDragPosition_m7T9E2 = textFieldSelectionManager2.m1753getCurrentDragPosition_m7T9E();
                            Intrinsics.checkNotNull(offsetM1753getCurrentDragPosition_m7T9E2);
                            jM1750updateSelection8UEBfa8 = textFieldSelectionManager2.m1750updateSelection8UEBfa8(value$foundation_release, offsetM1753getCurrentDragPosition_m7T9E2.getPackedValue(), false, false, SelectionAdjustment.INSTANCE.getWord(), true);
                        } else {
                            Offset offsetM1753getCurrentDragPosition_m7T9E3 = textFieldSelectionManager2.m1753getCurrentDragPosition_m7T9E();
                            Intrinsics.checkNotNull(offsetM1753getCurrentDragPosition_m7T9E3);
                            if (layoutResult.m1445isPositionOnTextk4lQ0M(offsetM1753getCurrentDragPosition_m7T9E3.getPackedValue())) {
                                num = textFieldSelectionManager2.dragBeginOffsetInText;
                                if (num != null) {
                                    iM1444getOffsetForPosition3MmeM6k = num.intValue();
                                } else {
                                    iM1444getOffsetForPosition3MmeM6k = layoutResult.m1444getOffsetForPosition3MmeM6k(textFieldSelectionManager2.dragBeginPosition, false);
                                }
                                int startOffset2 = iM1444getOffsetForPosition3MmeM6k;
                                Offset offsetM1753getCurrentDragPosition_m7T9E4 = textFieldSelectionManager2.m1753getCurrentDragPosition_m7T9E();
                                Intrinsics.checkNotNull(offsetM1753getCurrentDragPosition_m7T9E4);
                                int endOffset2 = layoutResult.m1444getOffsetForPosition3MmeM6k(offsetM1753getCurrentDragPosition_m7T9E4.getPackedValue(), false);
                                if (textFieldSelectionManager2.dragBeginOffsetInText != null && startOffset2 == endOffset2) {
                                    return;
                                }
                                TextFieldValue value$foundation_release2 = textFieldSelectionManager2.getValue$foundation_release();
                                Offset offsetM1753getCurrentDragPosition_m7T9E5 = textFieldSelectionManager2.m1753getCurrentDragPosition_m7T9E();
                                Intrinsics.checkNotNull(offsetM1753getCurrentDragPosition_m7T9E5);
                                jM1750updateSelection8UEBfa8 = textFieldSelectionManager2.m1750updateSelection8UEBfa8(value$foundation_release2, offsetM1753getCurrentDragPosition_m7T9E5.getPackedValue(), false, false, SelectionAdjustment.INSTANCE.getWord(), true);
                            } else {
                                int startOffset3 = textFieldSelectionManager2.getOffsetMapping().transformedToOriginal(TextLayoutResultProxy.m1443getOffsetForPosition3MmeM6k$default(layoutResult, textFieldSelectionManager2.dragBeginPosition, false, 2, null));
                                OffsetMapping offsetMapping = textFieldSelectionManager2.getOffsetMapping();
                                Offset offsetM1753getCurrentDragPosition_m7T9E6 = textFieldSelectionManager2.m1753getCurrentDragPosition_m7T9E();
                                Intrinsics.checkNotNull(offsetM1753getCurrentDragPosition_m7T9E6);
                                int endOffset3 = offsetMapping.transformedToOriginal(TextLayoutResultProxy.m1443getOffsetForPosition3MmeM6k$default(layoutResult, offsetM1753getCurrentDragPosition_m7T9E6.getPackedValue(), false, 2, null));
                                if (startOffset3 == endOffset3) {
                                    adjustment = SelectionAdjustment.INSTANCE.getNone();
                                } else {
                                    adjustment = SelectionAdjustment.INSTANCE.getWord();
                                }
                                TextFieldValue value$foundation_release3 = textFieldSelectionManager2.getValue$foundation_release();
                                Offset offsetM1753getCurrentDragPosition_m7T9E7 = textFieldSelectionManager2.m1753getCurrentDragPosition_m7T9E();
                                Intrinsics.checkNotNull(offsetM1753getCurrentDragPosition_m7T9E7);
                                jM1750updateSelection8UEBfa8 = textFieldSelectionManager2.m1750updateSelection8UEBfa8(value$foundation_release3, offsetM1753getCurrentDragPosition_m7T9E7.getPackedValue(), false, false, adjustment, true);
                            }
                        }
                        TextRange.m6467boximpl(jM1750updateSelection8UEBfa8);
                    }
                    this.this$0.updateFloatingToolbar(false);
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                onEnd();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                onEnd();
            }

            private final void onEnd() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1749setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
                this.this$0.dragBeginOffsetInText = null;
                boolean collapsed = TextRange.m6473getCollapsedimpl(this.this$0.getValue$foundation_release().getSelection());
                this.this$0.setHandleState(collapsed ? HandleState.Cursor : HandleState.Selection);
                LegacyTextFieldState state = this.this$0.getState();
                if (state != null) {
                    state.setShowSelectionHandleStart(!collapsed && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this.this$0, true));
                }
                LegacyTextFieldState state2 = this.this$0.getState();
                if (state2 != null) {
                    state2.setShowSelectionHandleEnd(!collapsed && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this.this$0, false));
                }
                LegacyTextFieldState state3 = this.this$0.getState();
                if (state3 != null) {
                    state3.setShowCursorHandle(collapsed && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this.this$0, true));
                }
            }
        };
        this.mouseSelectionObserver = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$mouseSelectionObserver$1
            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtend-k-4lQ0M */
            public boolean mo1626onExtendk4lQ0M(long downPosition) {
                LegacyTextFieldState state = this.this$0.getState();
                if (state == null || state.getLayoutResult() == null || !this.this$0.getEnabled()) {
                    return false;
                }
                this.this$0.previousRawDragOffset = -1;
                updateMouseSelection(this.this$0.getValue$foundation_release(), downPosition, false, SelectionAdjustment.INSTANCE.getNone());
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M */
            public boolean mo1627onExtendDragk4lQ0M(long dragPosition) {
                LegacyTextFieldState state;
                if (this.this$0.getEnabled()) {
                    if ((this.this$0.getValue$foundation_release().getText().length() == 0) || (state = this.this$0.getState()) == null || state.getLayoutResult() == null) {
                        return false;
                    }
                    updateMouseSelection(this.this$0.getValue$foundation_release(), dragPosition, false, SelectionAdjustment.INSTANCE.getNone());
                    return true;
                }
                return false;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public boolean mo1628onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
                LegacyTextFieldState state;
                if (this.this$0.getEnabled()) {
                    if ((this.this$0.getValue$foundation_release().getText().length() == 0) || (state = this.this$0.getState()) == null || state.getLayoutResult() == null) {
                        return false;
                    }
                    FocusRequester focusRequester = this.this$0.getFocusRequester();
                    if (focusRequester != null) {
                        focusRequester.requestFocus();
                    }
                    this.this$0.dragBeginPosition = downPosition;
                    this.this$0.previousRawDragOffset = -1;
                    TextFieldSelectionManager.enterSelectionMode$foundation_release$default(this.this$0, false, 1, null);
                    updateMouseSelection(this.this$0.getValue$foundation_release(), this.this$0.dragBeginPosition, true, adjustment);
                    return true;
                }
                return false;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onDrag-3MmeM6k */
            public boolean mo1625onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                LegacyTextFieldState state;
                if (this.this$0.getEnabled()) {
                    if ((this.this$0.getValue$foundation_release().getText().length() == 0) || (state = this.this$0.getState()) == null || state.getLayoutResult() == null) {
                        return false;
                    }
                    updateMouseSelection(this.this$0.getValue$foundation_release(), dragPosition, false, adjustment);
                    return true;
                }
                return false;
            }

            public final void updateMouseSelection(TextFieldValue value, long currentPosition, boolean isStartOfSelection, SelectionAdjustment adjustment) {
                long newSelection = this.this$0.m1750updateSelection8UEBfa8(value, currentPosition, isStartOfSelection, false, adjustment, false);
                this.this$0.setHandleState(TextRange.m6473getCollapsedimpl(newSelection) ? HandleState.Cursor : HandleState.Selection);
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            public void onDragDone() {
            }
        };
    }

    public /* synthetic */ TextFieldSelectionManager(UndoManager undoManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : undoManager);
    }

    public final UndoManager getUndoManager() {
        return this.undoManager;
    }

    /* JADX INFO: renamed from: getOffsetMapping$foundation_release, reason: from getter */
    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final void setOffsetMapping$foundation_release(OffsetMapping offsetMapping) {
        this.offsetMapping = offsetMapping;
    }

    public final Function1<TextFieldValue, Unit> getOnValueChange$foundation_release() {
        return this.onValueChange;
    }

    public final void setOnValueChange$foundation_release(Function1<? super TextFieldValue, Unit> function1) {
        this.onValueChange = function1;
    }

    /* JADX INFO: renamed from: getState$foundation_release, reason: from getter */
    public final LegacyTextFieldState getState() {
        return this.state;
    }

    public final void setState$foundation_release(LegacyTextFieldState legacyTextFieldState) {
        this.state = legacyTextFieldState;
    }

    public final TextFieldValue getValue$foundation_release() {
        State $this$getValue$iv = this.value;
        return (TextFieldValue) $this$getValue$iv.getValue();
    }

    public final void setValue$foundation_release(TextFieldValue textFieldValue) {
        MutableState $this$setValue$iv = this.value;
        $this$setValue$iv.setValue(textFieldValue);
    }

    public final AnnotatedString getTransformedText$foundation_release() {
        TextDelegate textDelegate;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (textDelegate = legacyTextFieldState.getTextDelegate()) == null) {
            return null;
        }
        return textDelegate.getText();
    }

    /* JADX INFO: renamed from: getVisualTransformation$foundation_release, reason: from getter */
    public final VisualTransformation getVisualTransformation() {
        return this.visualTransformation;
    }

    public final void setVisualTransformation$foundation_release(VisualTransformation visualTransformation) {
        this.visualTransformation = visualTransformation;
    }

    /* JADX INFO: renamed from: getClipboardManager$foundation_release, reason: from getter */
    public final ClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    public final void setClipboardManager$foundation_release(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public final boolean getEditable() {
        State $this$getValue$iv = this.editable;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setEditable(boolean z) {
        MutableState $this$setValue$iv = this.editable;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean getEnabled() {
        State $this$getValue$iv = this.enabled;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setEnabled(boolean z) {
        MutableState $this$setValue$iv = this.enabled;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDraggingHandle(Handle handle) {
        MutableState $this$setValue$iv = this.draggingHandle;
        $this$setValue$iv.setValue(handle);
    }

    public final Handle getDraggingHandle() {
        State $this$getValue$iv = this.draggingHandle;
        return (Handle) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCurrentDragPosition-_kEHs6E, reason: not valid java name */
    public final void m1749setCurrentDragPosition_kEHs6E(Offset offset) {
        MutableState $this$setValue$iv = this.currentDragPosition;
        $this$setValue$iv.setValue(offset);
    }

    /* JADX INFO: renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m1753getCurrentDragPosition_m7T9E() {
        State $this$getValue$iv = this.currentDragPosition;
        return (Offset) $this$getValue$iv.getValue();
    }

    /* JADX INFO: renamed from: getTouchSelectionObserver$foundation_release, reason: from getter */
    public final TextDragObserver getTouchSelectionObserver() {
        return this.touchSelectionObserver;
    }

    /* JADX INFO: renamed from: getMouseSelectionObserver$foundation_release, reason: from getter */
    public final MouseSelectionObserver getMouseSelectionObserver() {
        return this.mouseSelectionObserver;
    }

    public final TextDragObserver handleDragObserver$foundation_release(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$handleDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1411onDownk4lQ0M(long point) {
                TextLayoutResultProxy layoutResult;
                this.this$0.setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
                long handleCoordinates = SelectionHandlesKt.m1696getAdjustedCoordinatesk4lQ0M(this.this$0.m1755getHandlePositiontuRUvjQ$foundation_release(isStartHandle));
                LegacyTextFieldState state = this.this$0.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return;
                }
                long translatedPosition = layoutResult.m1447translateInnerToDecorationCoordinatesMKHz9U$foundation_release(handleCoordinates);
                this.this$0.dragBeginPosition = translatedPosition;
                this.this$0.m1749setCurrentDragPosition_kEHs6E(Offset.m4282boximpl(translatedPosition));
                this.this$0.dragTotalDistance = Offset.INSTANCE.m4309getZeroF1C5BW0();
                this.this$0.previousRawDragOffset = -1;
                LegacyTextFieldState state2 = this.this$0.getState();
                if (state2 != null) {
                    state2.setInTouchMode(true);
                }
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1749setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo1413onStartk4lQ0M(long startPoint) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo1412onDragk4lQ0M(long delta) {
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.dragTotalDistance = Offset.m4298plusMKHz9U(textFieldSelectionManager.dragTotalDistance, delta);
                this.this$0.m1749setCurrentDragPosition_kEHs6E(Offset.m4282boximpl(Offset.m4298plusMKHz9U(this.this$0.dragBeginPosition, this.this$0.dragTotalDistance)));
                TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                TextFieldValue value$foundation_release = this.this$0.getValue$foundation_release();
                Offset offsetM1753getCurrentDragPosition_m7T9E = this.this$0.m1753getCurrentDragPosition_m7T9E();
                Intrinsics.checkNotNull(offsetM1753getCurrentDragPosition_m7T9E);
                textFieldSelectionManager2.m1750updateSelection8UEBfa8(value$foundation_release, offsetM1753getCurrentDragPosition_m7T9E.getPackedValue(), false, isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), true);
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1749setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }
        };
    }

    public final TextDragObserver cursorDragObserver$foundation_release() {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$cursorDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1411onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1749setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo1413onStartk4lQ0M(long startPoint) {
                TextLayoutResultProxy layoutResult;
                long handleCoordinates = SelectionHandlesKt.m1696getAdjustedCoordinatesk4lQ0M(this.this$0.m1755getHandlePositiontuRUvjQ$foundation_release(true));
                LegacyTextFieldState state = this.this$0.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return;
                }
                long translatedPosition = layoutResult.m1447translateInnerToDecorationCoordinatesMKHz9U$foundation_release(handleCoordinates);
                this.this$0.dragBeginPosition = translatedPosition;
                this.this$0.m1749setCurrentDragPosition_kEHs6E(Offset.m4282boximpl(translatedPosition));
                this.this$0.dragTotalDistance = Offset.INSTANCE.m4309getZeroF1C5BW0();
                this.this$0.setDraggingHandle(Handle.Cursor);
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo1412onDragk4lQ0M(long delta) {
                TextLayoutResultProxy layoutResult;
                HapticFeedback hapticFeedBack;
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.dragTotalDistance = Offset.m4298plusMKHz9U(textFieldSelectionManager.dragTotalDistance, delta);
                LegacyTextFieldState state = this.this$0.getState();
                if (state != null && (layoutResult = state.getLayoutResult()) != null) {
                    TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                    textFieldSelectionManager2.m1749setCurrentDragPosition_kEHs6E(Offset.m4282boximpl(Offset.m4298plusMKHz9U(textFieldSelectionManager2.dragBeginPosition, textFieldSelectionManager2.dragTotalDistance)));
                    OffsetMapping offsetMapping = textFieldSelectionManager2.getOffsetMapping();
                    Offset offsetM1753getCurrentDragPosition_m7T9E = textFieldSelectionManager2.m1753getCurrentDragPosition_m7T9E();
                    Intrinsics.checkNotNull(offsetM1753getCurrentDragPosition_m7T9E);
                    int offset = offsetMapping.transformedToOriginal(TextLayoutResultProxy.m1443getOffsetForPosition3MmeM6k$default(layoutResult, offsetM1753getCurrentDragPosition_m7T9E.getPackedValue(), false, 2, null));
                    long newSelection = TextRangeKt.TextRange(offset, offset);
                    if (TextRange.m6472equalsimpl0(newSelection, textFieldSelectionManager2.getValue$foundation_release().getSelection())) {
                        return;
                    }
                    LegacyTextFieldState state2 = textFieldSelectionManager2.getState();
                    boolean z = false;
                    if (state2 != null && !state2.isInTouchMode()) {
                        z = true;
                    }
                    if (!z && (hapticFeedBack = textFieldSelectionManager2.getHapticFeedBack()) != null) {
                        hapticFeedBack.mo5262performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5271getTextHandleMove5zf0vsI());
                    }
                    textFieldSelectionManager2.getOnValueChange$foundation_release().invoke(textFieldSelectionManager2.m1747createTextFieldValueFDrldGo(textFieldSelectionManager2.getValue$foundation_release().getText(), newSelection));
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1749setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }
        };
    }

    public static /* synthetic */ void enterSelectionMode$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.enterSelectionMode$foundation_release(z);
    }

    public final void enterSelectionMode$foundation_release(boolean showFloatingToolbar) {
        FocusRequester focusRequester;
        LegacyTextFieldState legacyTextFieldState = this.state;
        boolean z = false;
        if (legacyTextFieldState != null && !legacyTextFieldState.getHasFocus()) {
            z = true;
        }
        if (z && (focusRequester = this.focusRequester) != null) {
            focusRequester.requestFocus();
        }
        this.oldValue = getValue$foundation_release();
        updateFloatingToolbar(showFloatingToolbar);
        setHandleState(HandleState.Selection);
    }

    public final void exitSelectionMode$foundation_release() {
        updateFloatingToolbar(false);
        setHandleState(HandleState.None);
    }

    /* JADX INFO: renamed from: deselect-_kEHs6E$foundation_release$default, reason: not valid java name */
    public static /* synthetic */ void m1748deselect_kEHs6E$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, Offset offset, int i, Object obj) {
        if ((i & 1) != 0) {
            offset = null;
        }
        textFieldSelectionManager.m1752deselect_kEHs6E$foundation_release(offset);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x006c  */
    /* JADX INFO: renamed from: deselect-_kEHs6E$foundation_release, reason: not valid java name */
    public final void m1752deselect_kEHs6E$foundation_release(Offset position) {
        HandleState selectionMode;
        int newCursorOffset;
        if (!TextRange.m6473getCollapsedimpl(getValue$foundation_release().getSelection())) {
            LegacyTextFieldState legacyTextFieldState = this.state;
            TextLayoutResultProxy layoutResult = legacyTextFieldState != null ? legacyTextFieldState.getLayoutResult() : null;
            if (position != null && layoutResult != null) {
                newCursorOffset = this.offsetMapping.transformedToOriginal(TextLayoutResultProxy.m1443getOffsetForPosition3MmeM6k$default(layoutResult, position.getPackedValue(), false, 2, null));
            } else {
                newCursorOffset = TextRange.m6476getMaximpl(getValue$foundation_release().getSelection());
            }
            TextFieldValue newValue = TextFieldValue.m6721copy3r_uNRQ$default(getValue$foundation_release(), (AnnotatedString) null, TextRangeKt.TextRange(newCursorOffset), (TextRange) null, 5, (Object) null);
            this.onValueChange.invoke(newValue);
        }
        if (position == null) {
            selectionMode = HandleState.None;
        } else {
            if (getValue$foundation_release().getText().length() > 0) {
                selectionMode = HandleState.Cursor;
            } else {
                selectionMode = HandleState.None;
            }
        }
        setHandleState(selectionMode);
        updateFloatingToolbar(false);
    }

    /* JADX INFO: renamed from: setSelectionPreviewHighlight-5zc-tL8$foundation_release, reason: not valid java name */
    public final void m1757setSelectionPreviewHighlight5zctL8$foundation_release(long range) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m1374setSelectionPreviewHighlightRange5zctL8(range);
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.m1371setDeletionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m6484getZerod9O1mEE());
        }
        if (!TextRange.m6473getCollapsedimpl(range)) {
            exitSelectionMode$foundation_release();
        }
    }

    /* JADX INFO: renamed from: setDeletionPreviewHighlight-5zc-tL8$foundation_release, reason: not valid java name */
    public final void m1756setDeletionPreviewHighlight5zctL8$foundation_release(long range) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m1371setDeletionPreviewHighlightRange5zctL8(range);
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.m1374setSelectionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m6484getZerod9O1mEE());
        }
        if (!TextRange.m6473getCollapsedimpl(range)) {
            exitSelectionMode$foundation_release();
        }
    }

    public final void clearPreviewHighlight$foundation_release() {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m1371setDeletionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m6484getZerod9O1mEE());
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 == null) {
            return;
        }
        legacyTextFieldState2.m1374setSelectionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m6484getZerod9O1mEE());
    }

    public static /* synthetic */ void copy$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.copy$foundation_release(z);
    }

    public final void copy$foundation_release(boolean cancelSelection) {
        if (TextRange.m6473getCollapsedimpl(getValue$foundation_release().getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        if (cancelSelection) {
            int newCursorOffset = TextRange.m6476getMaximpl(getValue$foundation_release().getSelection());
            TextFieldValue newValue = m1747createTextFieldValueFDrldGo(getValue$foundation_release().getText(), TextRangeKt.TextRange(newCursorOffset, newCursorOffset));
            this.onValueChange.invoke(newValue);
            setHandleState(HandleState.None);
        }
    }

    public final void paste$foundation_release() {
        AnnotatedString text;
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (text = clipboardManager.getText()) == null) {
            return;
        }
        AnnotatedString newText = TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()).plus(text).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()));
        int newCursorOffset = TextRange.m6477getMinimpl(getValue$foundation_release().getSelection()) + text.length();
        TextFieldValue newValue = m1747createTextFieldValueFDrldGo(newText, TextRangeKt.TextRange(newCursorOffset, newCursorOffset));
        this.onValueChange.invoke(newValue);
        setHandleState(HandleState.None);
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
    }

    public final void cut$foundation_release() {
        if (TextRange.m6473getCollapsedimpl(getValue$foundation_release().getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        AnnotatedString newText = TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()));
        int newCursorOffset = TextRange.m6477getMinimpl(getValue$foundation_release().getSelection());
        TextFieldValue newValue = m1747createTextFieldValueFDrldGo(newText, TextRangeKt.TextRange(newCursorOffset, newCursorOffset));
        this.onValueChange.invoke(newValue);
        setHandleState(HandleState.None);
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
    }

    public final void selectAll$foundation_release() {
        TextFieldValue newValue = m1747createTextFieldValueFDrldGo(getValue$foundation_release().getText(), TextRangeKt.TextRange(0, getValue$foundation_release().getText().length()));
        this.onValueChange.invoke(newValue);
        this.oldValue = TextFieldValue.m6721copy3r_uNRQ$default(this.oldValue, (AnnotatedString) null, newValue.getSelection(), (TextRange) null, 5, (Object) null);
        enterSelectionMode$foundation_release(true);
    }

    /* JADX INFO: renamed from: getHandlePosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m1755getHandlePositiontuRUvjQ$foundation_release(boolean isStartHandle) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult textLayoutResult;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (layoutResult = legacyTextFieldState.getLayoutResult()) == null || (textLayoutResult = layoutResult.getValue()) == null) {
            return Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
        }
        AnnotatedString transformedText = getTransformedText$foundation_release();
        if (transformedText == null) {
            return Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
        }
        String layoutInputText = textLayoutResult.getLayoutInput().getText().getText();
        if (!Intrinsics.areEqual(transformedText.getText(), layoutInputText)) {
            return Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
        }
        long selection = getValue$foundation_release().getSelection();
        int offset = isStartHandle ? TextRange.m6479getStartimpl(selection) : TextRange.m6474getEndimpl(selection);
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(textLayoutResult, this.offsetMapping.originalToTransformed(offset), isStartHandle, TextRange.m6478getReversedimpl(getValue$foundation_release().getSelection()));
    }

    /* JADX INFO: renamed from: getCursorPosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m1754getCursorPositiontuRUvjQ$foundation_release(Density density) {
        int offset = this.offsetMapping.originalToTransformed(TextRange.m6479getStartimpl(getValue$foundation_release().getSelection()));
        LegacyTextFieldState legacyTextFieldState = this.state;
        TextLayoutResultProxy layoutResult = legacyTextFieldState != null ? legacyTextFieldState.getLayoutResult() : null;
        Intrinsics.checkNotNull(layoutResult);
        TextLayoutResult layoutResult2 = layoutResult.getValue();
        Rect cursorRect = layoutResult2.getCursorRect(RangesKt.coerceIn(offset, 0, layoutResult2.getLayoutInput().getText().length()));
        float x = cursorRect.getLeft() + (density.mo726toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness()) / 2);
        return OffsetKt.Offset(x, cursorRect.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFloatingToolbar(boolean show) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.setShowFloatingToolbar(show);
        }
        if (show) {
            showSelectionToolbar$foundation_release();
        } else {
            hideSelectionToolbar$foundation_release();
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0077  */
    public final void showSelectionToolbar$foundation_release() {
        Function0<Unit> function0;
        if (getEnabled()) {
            LegacyTextFieldState legacyTextFieldState = this.state;
            if ((legacyTextFieldState == null || legacyTextFieldState.isInTouchMode()) ? false : true) {
                return;
            }
            boolean isPassword = this.visualTransformation instanceof PasswordVisualTransformation;
            Function0<Unit> function1 = (TextRange.m6473getCollapsedimpl(getValue$foundation_release().getSelection()) || isPassword) ? null : new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$copy$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    TextFieldSelectionManager.copy$foundation_release$default(this.this$0, false, 1, null);
                    this.this$0.hideSelectionToolbar$foundation_release();
                }
            };
            Function0<Unit> function2 = (TextRange.m6473getCollapsedimpl(getValue$foundation_release().getSelection()) || !getEditable() || isPassword) ? null : new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$cut$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.cut$foundation_release();
                    this.this$0.hideSelectionToolbar$foundation_release();
                }
            };
            if (getEditable()) {
                ClipboardManager clipboardManager = this.clipboardManager;
                if (clipboardManager != null && clipboardManager.hasText()) {
                    function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$paste$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            this.this$0.paste$foundation_release();
                            this.this$0.hideSelectionToolbar$foundation_release();
                        }
                    };
                } else {
                    function0 = null;
                }
            } else {
                function0 = null;
            }
            Function0<Unit> function3 = TextRange.m6475getLengthimpl(getValue$foundation_release().getSelection()) != getValue$foundation_release().getText().length() ? new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$selectAll$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.selectAll$foundation_release();
                }
            } : null;
            TextToolbar textToolbar = this.textToolbar;
            if (textToolbar != null) {
                textToolbar.showMenu(getContentRect(), function1, function0, function2, function3);
            }
        }
    }

    public final void hideSelectionToolbar$foundation_release() {
        TextToolbar textToolbar;
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    /* JADX INFO: renamed from: contextMenuOpenAdjustment-k-4lQ0M, reason: not valid java name */
    public final void m1751contextMenuOpenAdjustmentk4lQ0M(long position) {
        TextLayoutResultProxy layoutResult;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null && (layoutResult = legacyTextFieldState.getLayoutResult()) != null) {
            int offset = TextLayoutResultProxy.m1443getOffsetForPosition3MmeM6k$default(layoutResult, position, false, 2, null);
            if (!TextRange.m6470containsimpl(getValue$foundation_release().getSelection(), offset)) {
                this.previousRawDragOffset = -1;
                m1750updateSelection8UEBfa8(getValue$foundation_release(), position, true, false, SelectionAdjustment.INSTANCE.getWord(), false);
            }
        }
    }

    public final boolean isTextChanged$foundation_release() {
        return !Intrinsics.areEqual(this.oldValue.getText(), getValue$foundation_release().getText());
    }

    private final Rect getContentRect() {
        float startTop;
        LayoutCoordinates layoutCoordinates;
        TextLayoutResult value;
        Rect cursorRect;
        LayoutCoordinates layoutCoordinates2;
        TextLayoutResult value2;
        Rect cursorRect2;
        LayoutCoordinates layoutCoordinates3;
        LayoutCoordinates layoutCoordinates4;
        LegacyTextFieldState it = this.state;
        if (it != null) {
            if (it.getIsLayoutResultStale()) {
                it = null;
            }
            if (it != null) {
                int transformedStart = this.offsetMapping.originalToTransformed(TextRange.m6479getStartimpl(getValue$foundation_release().getSelection()));
                int transformedEnd = this.offsetMapping.originalToTransformed(TextRange.m6474getEndimpl(getValue$foundation_release().getSelection()));
                LegacyTextFieldState legacyTextFieldState = this.state;
                long startOffset = (legacyTextFieldState == null || (layoutCoordinates4 = legacyTextFieldState.getLayoutCoordinates()) == null) ? Offset.INSTANCE.m4309getZeroF1C5BW0() : layoutCoordinates4.mo5881localToRootMKHz9U(m1755getHandlePositiontuRUvjQ$foundation_release(true));
                LegacyTextFieldState legacyTextFieldState2 = this.state;
                long endOffset = (legacyTextFieldState2 == null || (layoutCoordinates3 = legacyTextFieldState2.getLayoutCoordinates()) == null) ? Offset.INSTANCE.m4309getZeroF1C5BW0() : layoutCoordinates3.mo5881localToRootMKHz9U(m1755getHandlePositiontuRUvjQ$foundation_release(false));
                LegacyTextFieldState legacyTextFieldState3 = this.state;
                float endTop = 0.0f;
                if (legacyTextFieldState3 == null || (layoutCoordinates2 = legacyTextFieldState3.getLayoutCoordinates()) == null) {
                    startTop = 0.0f;
                } else {
                    TextLayoutResultProxy layoutResult = it.getLayoutResult();
                    startTop = Offset.m4294getYimpl(layoutCoordinates2.mo5881localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult == null || (value2 = layoutResult.getValue()) == null || (cursorRect2 = value2.getCursorRect(transformedStart)) == null) ? 0.0f : cursorRect2.getTop())));
                }
                LegacyTextFieldState legacyTextFieldState4 = this.state;
                if (legacyTextFieldState4 != null && (layoutCoordinates = legacyTextFieldState4.getLayoutCoordinates()) != null) {
                    TextLayoutResultProxy layoutResult2 = it.getLayoutResult();
                    endTop = Offset.m4294getYimpl(layoutCoordinates.mo5881localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult2 == null || (value = layoutResult2.getValue()) == null || (cursorRect = value.getCursorRect(transformedEnd)) == null) ? 0.0f : cursorRect.getTop())));
                }
                float left = Math.min(Offset.m4293getXimpl(startOffset), Offset.m4293getXimpl(endOffset));
                float right = Math.max(Offset.m4293getXimpl(startOffset), Offset.m4293getXimpl(endOffset));
                float top = Math.min(startTop, endTop);
                float bottom = Math.max(Offset.m4294getYimpl(startOffset), Offset.m4294getYimpl(endOffset)) + (Dp.m7016constructorimpl(25) * it.getTextDelegate().getDensity().getDensity());
                return new Rect(left, top, right, bottom);
            }
        }
        return Rect.INSTANCE.getZero();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:47:0x010e  */
    /* JADX WARN: Code duplicated, block: B:49:0x0111  */
    /* JADX WARN: Code duplicated, block: B:51:0x011d  */
    /* JADX WARN: Code duplicated, block: B:52:0x011f  */
    /* JADX WARN: Code duplicated, block: B:61:0x0144  */
    /* JADX WARN: Code duplicated, block: B:65:0x0154  */
    /* JADX WARN: Code duplicated, block: B:68:0x015b  */
    /* JADX WARN: Code duplicated, block: B:69:0x015e  */
    /* JADX WARN: Code duplicated, block: B:80:0x017a  */
    /* JADX WARN: Code duplicated, block: B:82:0x0180  */
    /* JADX WARN: Code duplicated, block: B:84:0x0187  */
    /* JADX WARN: Code duplicated, block: B:85:0x0189  */
    /* JADX WARN: Code duplicated, block: B:90:0x0192  */
    /* JADX WARN: Code duplicated, block: B:92:0x0198  */
    /* JADX WARN: Code duplicated, block: B:95:0x01a0  */
    /* JADX WARN: Instruction removed from duplicated block: B:92:0x0198, please report this as an issue */
    /* JADX INFO: renamed from: updateSelection-8UEBfa8, reason: not valid java name */
    public final long m1750updateSelection8UEBfa8(TextFieldValue value, long currentPosition, boolean isStartOfSelection, boolean isStartHandle, SelectionAdjustment adjustment, boolean isTouchBasedSelection) {
        TextLayoutResultProxy layoutResult;
        int rawStartHandleOffset;
        int rawEndHandleOffset;
        int rawPreviousHandleOffset;
        boolean bothSelectionsCollapsed;
        LegacyTextFieldState legacyTextFieldState;
        LegacyTextFieldState legacyTextFieldState2;
        LegacyTextFieldState legacyTextFieldState3;
        LegacyTextFieldState legacyTextFieldState4;
        boolean z;
        boolean z2;
        boolean z3;
        HapticFeedback hapticFeedback;
        LegacyTextFieldState legacyTextFieldState5 = this.state;
        if (legacyTextFieldState5 == null || (layoutResult = legacyTextFieldState5.getLayoutResult()) == null) {
            return TextRange.INSTANCE.m6484getZerod9O1mEE();
        }
        long previousTransformedSelection = TextRangeKt.TextRange(this.offsetMapping.originalToTransformed(TextRange.m6479getStartimpl(value.getSelection())), this.offsetMapping.originalToTransformed(TextRange.m6474getEndimpl(value.getSelection())));
        int currentOffset = layoutResult.m1444getOffsetForPosition3MmeM6k(currentPosition, false);
        if (isStartHandle || isStartOfSelection) {
            rawStartHandleOffset = currentOffset;
        } else {
            rawStartHandleOffset = TextRange.m6479getStartimpl(previousTransformedSelection);
        }
        if (!isStartHandle || isStartOfSelection) {
            rawEndHandleOffset = currentOffset;
        } else {
            rawEndHandleOffset = TextRange.m6474getEndimpl(previousTransformedSelection);
        }
        SelectionLayout previousSelectionLayout = this.previousSelectionLayout;
        if (isStartOfSelection || previousSelectionLayout == null || this.previousRawDragOffset == -1) {
            rawPreviousHandleOffset = -1;
        } else {
            rawPreviousHandleOffset = this.previousRawDragOffset;
        }
        SelectionLayout selectionLayout = SelectionLayoutKt.m1699getTextFieldSelectionLayoutRcvTLA(layoutResult.getValue(), rawStartHandleOffset, rawEndHandleOffset, rawPreviousHandleOffset, previousTransformedSelection, isStartOfSelection, isStartHandle);
        if (!selectionLayout.shouldRecomputeSelection(previousSelectionLayout)) {
            return value.getSelection();
        }
        this.previousSelectionLayout = selectionLayout;
        this.previousRawDragOffset = currentOffset;
        Selection newTransformedSelection = adjustment.adjust(selectionLayout);
        long newSelection = TextRangeKt.TextRange(this.offsetMapping.transformedToOriginal(newTransformedSelection.getStart().getOffset()), this.offsetMapping.transformedToOriginal(newTransformedSelection.getEnd().getOffset()));
        if (TextRange.m6472equalsimpl0(newSelection, value.getSelection())) {
            return value.getSelection();
        }
        if (TextRange.m6478getReversedimpl(newSelection) != TextRange.m6478getReversedimpl(value.getSelection())) {
            int iM6474getEndimpl = TextRange.m6474getEndimpl(newSelection);
            int rawPreviousHandleOffset2 = TextRange.m6479getStartimpl(newSelection);
            long $this$updateSelection_8UEBfa8_u24lambda_u244 = TextRangeKt.TextRange(iM6474getEndimpl, rawPreviousHandleOffset2);
            boolean onlyChangeIsReversed = TextRange.m6472equalsimpl0($this$updateSelection_8UEBfa8_u24lambda_u244, value.getSelection());
            if (TextRange.m6473getCollapsedimpl(newSelection) || !TextRange.m6473getCollapsedimpl(value.getSelection())) {
                bothSelectionsCollapsed = false;
            } else {
                bothSelectionsCollapsed = true;
            }
            if (isTouchBasedSelection) {
                if (value.getText().length() > 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3 && !onlyChangeIsReversed && !bothSelectionsCollapsed && (hapticFeedback = this.hapticFeedBack) != null) {
                    hapticFeedback.mo5262performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5271getTextHandleMove5zf0vsI());
                }
            }
            TextFieldValue newValue = m1747createTextFieldValueFDrldGo(value.getText(), newSelection);
            this.onValueChange.invoke(newValue);
            if (!isTouchBasedSelection) {
                updateFloatingToolbar(!TextRange.m6473getCollapsedimpl(newSelection));
            }
            legacyTextFieldState = this.state;
            if (legacyTextFieldState != null) {
                legacyTextFieldState.setInTouchMode(isTouchBasedSelection);
            }
            legacyTextFieldState2 = this.state;
            if (legacyTextFieldState2 != null) {
                boolean onlyChangeIsReversed2 = TextRange.m6473getCollapsedimpl(newSelection) && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, true);
                legacyTextFieldState2.setShowSelectionHandleStart(onlyChangeIsReversed2);
            }
            legacyTextFieldState3 = this.state;
            if (legacyTextFieldState3 != null) {
                if (TextRange.m6473getCollapsedimpl(newSelection)) {
                    z2 = false;
                } else {
                    z2 = false;
                    if (TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, false)) {
                        z2 = true;
                    }
                }
                legacyTextFieldState3.setShowSelectionHandleEnd(z2);
            }
            legacyTextFieldState4 = this.state;
            if (legacyTextFieldState4 != null) {
                if (TextRange.m6473getCollapsedimpl(newSelection)) {
                    z = TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, true);
                }
                legacyTextFieldState4.setShowCursorHandle(z);
            }
            return newSelection;
        }
        if (TextRange.m6473getCollapsedimpl(newSelection)) {
            bothSelectionsCollapsed = false;
        } else {
            bothSelectionsCollapsed = false;
        }
        if (isTouchBasedSelection) {
            if (value.getText().length() > 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                hapticFeedback.mo5262performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5271getTextHandleMove5zf0vsI());
            }
        }
        TextFieldValue newValue2 = m1747createTextFieldValueFDrldGo(value.getText(), newSelection);
        this.onValueChange.invoke(newValue2);
        if (!isTouchBasedSelection) {
            updateFloatingToolbar(!TextRange.m6473getCollapsedimpl(newSelection));
        }
        legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.setInTouchMode(isTouchBasedSelection);
        }
        legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            if (TextRange.m6473getCollapsedimpl(newSelection)) {
            }
            legacyTextFieldState2.setShowSelectionHandleStart(onlyChangeIsReversed2);
        }
        legacyTextFieldState3 = this.state;
        if (legacyTextFieldState3 != null) {
            if (TextRange.m6473getCollapsedimpl(newSelection)) {
                z2 = false;
                if (TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, false)) {
                    z2 = true;
                }
            } else {
                z2 = false;
            }
            legacyTextFieldState3.setShowSelectionHandleEnd(z2);
        }
        legacyTextFieldState4 = this.state;
        if (legacyTextFieldState4 != null) {
            if (TextRange.m6473getCollapsedimpl(newSelection)) {
                if (TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, true)) {
                }
            }
            legacyTextFieldState4.setShowCursorHandle(z);
        }
        return newSelection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setHandleState(HandleState handleState) {
        LegacyTextFieldState it = this.state;
        if (it != null) {
            if (it.getHandleState() == handleState) {
                it = null;
            }
            if (it != null) {
                it.setHandleState(handleState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createTextFieldValue-FDrldGo, reason: not valid java name */
    public final TextFieldValue m1747createTextFieldValueFDrldGo(AnnotatedString annotatedString, long selection) {
        return new TextFieldValue(annotatedString, selection, (TextRange) null, 4, (DefaultConstructorMarker) null);
    }
}
