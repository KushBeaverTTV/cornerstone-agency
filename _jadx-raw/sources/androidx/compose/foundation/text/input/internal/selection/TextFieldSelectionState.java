package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.TransferableContent_androidKt;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursorKt;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text.input.internal.IndexTransformationType;
import androidx.compose.foundation.text.input.internal.MathUtilsKt;
import androidx.compose.foundation.text.input.internal.SelectionWedgeAffinity;
import androidx.compose.foundation.text.input.internal.TextLayoutState;
import androidx.compose.foundation.text.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.WedgeAffinity;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SelectionLayout;
import androidx.compose.foundation.text.selection.SelectionLayoutKt;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text.selection.TextSelectionDelegateKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.platform.ClipEntry;
import androidx.compose.ui.platform.ClipMetadata;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: TextFieldSelectionState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001:\u0006«\u0001¬\u0001\u00ad\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\u0006\u0010W\u001a\u00020\tJ\u0006\u0010X\u001a\u00020\tJ\u0006\u0010Y\u001a\u00020\tJ\u0006\u0010Z\u001a\u00020\tJ\u0006\u0010[\u001a\u00020\\J\u0010\u0010]\u001a\u00020\\2\b\b\u0002\u0010^\u001a\u00020\tJ\u0006\u0010_\u001a\u00020\\J\u0006\u0010`\u001a\u00020\\J\u0006\u0010a\u001a\u00020\\J\b\u0010b\u001a\u00020cH\u0002J\u0015\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020\tH\u0000¢\u0006\u0002\bgJ\u0006\u0010h\u001a\u00020cJ\u001d\u0010i\u001a\u00020\u00112\u0006\u0010j\u001a\u00020\tH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bk\u0010lJ\u001d\u0010m\u001a\u00020e2\u0006\u0010j\u001a\u00020\t2\u0006\u0010f\u001a\u00020\tH\u0000¢\u0006\u0002\bnJ<\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u0002032\u0006\u0010r\u001a\u0002032\b\u0010s\u001a\u0004\u0018\u00010p2\u0006\u0010j\u001a\u00020\t2\u0006\u0010t\u001a\u00020uH\u0002ø\u0001\u0000¢\u0006\u0004\bv\u0010wJ\b\u0010x\u001a\u00020\\H\u0002J\b\u0010y\u001a\u00020\tH\u0002J\b\u0010z\u001a\u00020\\H\u0002J1\u0010{\u001a\n\u0012\u0004\u0012\u00020\\\u0018\u00010<2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010|\u001a\u00020P2\u000e\b\u0004\u0010}\u001a\b\u0012\u0004\u0012\u00020\\0<H\u0082\bJ\u000e\u0010~\u001a\u00020\\H\u0086@¢\u0006\u0002\u0010\u007fJ\u000f\u0010\u0080\u0001\u001a\u00020\\H\u0082@¢\u0006\u0002\u0010\u007fJ\u000f\u0010\u0081\u0001\u001a\u00020\\H\u0082@¢\u0006\u0002\u0010\u007fJ\u0007\u0010\u0082\u0001\u001a\u00020\\J\t\u0010\u0083\u0001\u001a\u00020\\H\u0002J\u001e\u0010\u0084\u0001\u001a\u00020\t2\u0007\u0010\u0085\u0001\u001a\u00020\u0011H\u0002ø\u0001\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0007\u0010\u0088\u0001\u001a\u00020\\J\u0012\u0010\u0089\u0001\u001a\u00020\\2\u0007\u0010\u008a\u0001\u001a\u00020cH\u0002J?\u0010\u008b\u0001\u001a\u00020\\2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020O2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tJ%\u0010\u008c\u0001\u001a\u00020\\2\u0007\u0010\u008d\u0001\u001a\u00020\u001d2\u0007\u0010\u008e\u0001\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001JZ\u0010\u0091\u0001\u001a\u00020p2\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\u0007\u0010\u0094\u0001\u001a\u0002032\u0007\u0010\u0095\u0001\u001a\u0002032\u0006\u0010j\u001a\u00020\t2\u0006\u0010t\u001a\u00020u2\t\b\u0002\u0010\u0096\u0001\u001a\u00020\t2\t\b\u0002\u0010\u0097\u0001\u001a\u00020\tH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\u000f\u0010\u009a\u0001\u001a\u00020\\2\u0006\u0010Q\u001a\u00020PJ\u0015\u0010\u009b\u0001\u001a\u00020\\*\u00030\u009c\u0001H\u0086@¢\u0006\u0003\u0010\u009d\u0001J\u0015\u0010\u009e\u0001\u001a\u00020\\*\u00030\u009c\u0001H\u0082@¢\u0006\u0003\u0010\u009d\u0001J\u001d\u0010\u009f\u0001\u001a\u00020\\*\u00030\u009c\u00012\u0006\u0010j\u001a\u00020\tH\u0082@¢\u0006\u0003\u0010 \u0001J?\u0010¡\u0001\u001a\u00020\\*\u00030\u009c\u00012\n\u0010¢\u0001\u001a\u0005\u0018\u00010£\u00012\r\u0010¤\u0001\u001a\b\u0012\u0004\u0012\u00020\\0<2\r\u0010¥\u0001\u001a\b\u0012\u0004\u0012\u00020\\0<H\u0086@¢\u0006\u0003\u0010¦\u0001J\u0015\u0010§\u0001\u001a\u00020\\*\u00030\u009c\u0001H\u0086@¢\u0006\u0003\u0010\u009d\u0001J\u001d\u0010¨\u0001\u001a\u00020\\*\u00030\u009c\u00012\u0006\u0010j\u001a\u00020\tH\u0086@¢\u0006\u0003\u0010 \u0001J$\u0010©\u0001\u001a\u00020\\*\u00030\u009c\u00012\r\u0010¤\u0001\u001a\b\u0012\u0004\u0012\u00020\\0<H\u0086@¢\u0006\u0003\u0010ª\u0001R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00158F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR/\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0014\u001a\u0004\u0018\u00010\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u001c\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010'\u001a\u00020\u00118Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b(\u0010\u0013R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010&\"\u0004\b+\u0010,R+\u0010-\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010\u001c\u001a\u0004\b-\u0010&\"\u0004\b.\u0010,R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R1\u00106\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00118B@BX\u0082\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b:\u0010\u001c\u001a\u0004\b7\u0010\u0013\"\u0004\b8\u00109R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010;\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010=\u0018\u00010<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR+\u0010B\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010\u001c\u001a\u0004\bC\u0010&\"\u0004\bD\u0010,R1\u0010F\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00118B@BX\u0082\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bI\u0010\u001c\u001a\u0004\bG\u0010\u0013\"\u0004\bH\u00109R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010J\u001a\u0004\u0018\u00010K8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u0004\u0018\u00010OX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010Q\u001a\u00020P2\u0006\u0010\u0014\u001a\u00020P8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bV\u0010\u001c\u001a\u0004\bR\u0010S\"\u0004\bT\u0010U\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006®\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "density", "Landroidx/compose/ui/unit/Density;", "enabled", "", "readOnly", "isFocused", "isPassword", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/unit/Density;ZZZZ)V", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "currentTextLayoutPositionInWindow", "Landroidx/compose/ui/geometry/Offset;", "getCurrentTextLayoutPositionInWindow-F1C5BW0", "()J", "<set-?>", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "directDragGestureInitiator", "getDirectDragGestureInitiator", "()Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "setDirectDragGestureInitiator", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;)V", "directDragGestureInitiator$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "editable", "getEditable", "()Z", "handleDragPosition", "getHandleDragPosition-F1C5BW0", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setFocused", "(Z)V", "isInTouchMode", "setInTouchMode", "isInTouchMode$delegate", "pressInteraction", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "previousRawDragOffset", "", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "rawHandleDragPosition", "getRawHandleDragPosition-F1C5BW0", "setRawHandleDragPosition-k-4lQ0M", "(J)V", "rawHandleDragPosition$delegate", "receiveContentConfiguration", "Lkotlin/Function0;", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "getReceiveContentConfiguration", "()Lkotlin/jvm/functions/Function0;", "setReceiveContentConfiguration", "(Lkotlin/jvm/functions/Function0;)V", "showCursorHandle", "getShowCursorHandle", "setShowCursorHandle", "showCursorHandle$delegate", "startTextLayoutPositionInWindow", "getStartTextLayoutPositionInWindow-F1C5BW0", "setStartTextLayoutPositionInWindow-k-4lQ0M", "startTextLayoutPositionInWindow$delegate", "textLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getTextLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "textToolbarState", "getTextToolbarState", "()Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "setTextToolbarState", "(Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;)V", "textToolbarState$delegate", "canCopy", "canCut", "canPaste", "canSelectAll", "clearHandleDragging", "", "copy", "cancelSelection", "cut", "deselect", "dispose", "getContentRect", "Landroidx/compose/ui/geometry/Rect;", "getCursorHandleState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "includePosition", "getCursorHandleState$foundation_release", "getCursorRect", "getHandlePosition", "isStartHandle", "getHandlePosition-tuRUvjQ", "(Z)J", "getSelectionHandleState", "getSelectionHandleState$foundation_release", "getTextFieldSelection", "Landroidx/compose/ui/text/TextRange;", "rawStartOffset", "rawEndOffset", "previousSelection", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "getTextFieldSelection-qeG_v_k", "(IILandroidx/compose/ui/text/TextRange;ZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)J", "hideTextToolbar", "isCursorHandleInVisibleBounds", "markStartContentVisibleOffset", "menuItem", "desiredState", "operation", "observeChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeTextChanges", "observeTextToolbarVisibility", "paste", "pasteAsPlainText", "placeCursorAtNearestOffset", "offset", "placeCursorAtNearestOffset-k-4lQ0M", "(J)Z", "selectAll", "showTextToolbar", "contentRect", "update", "updateHandleDragging", "handle", "position", "updateHandleDragging-Uv8p0NA", "(Landroidx/compose/foundation/text/Handle;J)V", "updateSelection", "textFieldCharSequence", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "startOffset", "endOffset", "allowPreviousSelectionCollapsed", "isStartOfSelection", "updateSelection-SsL-Rf8", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;IIZLandroidx/compose/foundation/text/selection/SelectionAdjustment;ZZ)J", "updateTextToolbarState", "cursorHandleGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectCursorHandleDragGestures", "detectSelectionHandleDragGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTextFieldTapGestures", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "requestFocus", "showKeyboard", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTouchMode", "selectionHandleGestures", "textFieldSelectionGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "InputType", "TextFieldMouseSelectionObserver", "TextFieldTextDragObserver", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSelectionState {
    public static final int $stable = 8;
    private ClipboardManager clipboardManager;
    private Density density;
    private boolean enabled;
    private HapticFeedback hapticFeedBack;
    private boolean isFocused;
    private boolean isPassword;
    private PressInteraction.Press pressInteraction;
    private SelectionLayout previousSelectionLayout;
    private boolean readOnly;
    private Function0<? extends ReceiveContentConfiguration> receiveContentConfiguration;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private TextToolbar textToolbar;

    /* JADX INFO: renamed from: isInTouchMode$delegate, reason: from kotlin metadata */
    private final MutableState isInTouchMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* JADX INFO: renamed from: startTextLayoutPositionInWindow$delegate, reason: from kotlin metadata */
    private final MutableState startTextLayoutPositionInWindow = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m4282boximpl(Offset.INSTANCE.m4308getUnspecifiedF1C5BW0()), null, 2, null);

    /* JADX INFO: renamed from: rawHandleDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState rawHandleDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m4282boximpl(Offset.INSTANCE.m4308getUnspecifiedF1C5BW0()), null, 2, null);

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: directDragGestureInitiator$delegate, reason: from kotlin metadata */
    private final MutableState directDragGestureInitiator = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(InputType.None, null, 2, null);

    /* JADX INFO: renamed from: showCursorHandle$delegate, reason: from kotlin metadata */
    private final MutableState showCursorHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: textToolbarState$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TextToolbarState.None, null, 2, null);
    private int previousRawDragOffset = -1;

    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "", "(Ljava/lang/String;I)V", "None", "Touch", "Mouse", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum InputType {
        None,
        Touch,
        Mouse
    }

    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IndexTransformationType.values().length];
            try {
                iArr[IndexTransformationType.Untransformed.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[IndexTransformationType.Deletion.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[IndexTransformationType.Insertion.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[IndexTransformationType.Replacement.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0, 0, 0}, l = {614}, m = "detectCursorHandleDragGestures", n = {"this", "cursorDragStart", "cursorDragDelta"}, s = {"L$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectCursorHandleDragGestures(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0, 0, 0, 0}, l = {980}, m = "detectSelectionHandleDragGestures", n = {"this", "dragBeginPosition", "dragTotalDistance", "handle"}, s = {"L$0", "L$1", "L$2", "L$3"})
    static final class C04261 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C04261(Continuation<? super C04261> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectSelectionHandleDragGestures(null, false, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0}, l = {423}, m = "observeChanges", n = {"this"}, s = {"L$0"})
    static final class C04351 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04351(Continuation<? super C04351> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.observeChanges(this);
        }
    }

    public TextFieldSelectionState(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, Density density, boolean enabled, boolean readOnly, boolean isFocused, boolean isPassword) {
        this.textFieldState = textFieldState;
        this.textLayoutState = textLayoutState;
        this.density = density;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.isFocused = isFocused;
        this.isPassword = isPassword;
    }

    /* JADX INFO: renamed from: isFocused, reason: from getter */
    public final boolean getIsFocused() {
        return this.isFocused;
    }

    public final void setFocused(boolean z) {
        this.isFocused = z;
    }

    public final boolean isInTouchMode() {
        State $this$getValue$iv = this.isInTouchMode;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setInTouchMode(boolean z) {
        MutableState $this$setValue$iv = this.isInTouchMode;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final Function0<ReceiveContentConfiguration> getReceiveContentConfiguration() {
        return this.receiveContentConfiguration;
    }

    public final void setReceiveContentConfiguration(Function0<? extends ReceiveContentConfiguration> function0) {
        this.receiveContentConfiguration = function0;
    }

    /* JADX INFO: renamed from: getStartTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1615getStartTextLayoutPositionInWindowF1C5BW0() {
        State $this$getValue$iv = this.startTextLayoutPositionInWindow;
        return ((Offset) $this$getValue$iv.getValue()).getPackedValue();
    }

    /* JADX INFO: renamed from: setStartTextLayoutPositionInWindow-k-4lQ0M, reason: not valid java name */
    private final void m1619setStartTextLayoutPositionInWindowk4lQ0M(long j) {
        MutableState $this$setValue$iv = this.startTextLayoutPositionInWindow;
        $this$setValue$iv.setValue(Offset.m4282boximpl(j));
    }

    /* JADX INFO: renamed from: getCurrentTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1612getCurrentTextLayoutPositionInWindowF1C5BW0() {
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        return textLayoutCoordinates != null ? LayoutCoordinatesKt.positionInWindow(textLayoutCoordinates) : Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: getRawHandleDragPosition-F1C5BW0, reason: not valid java name */
    private final long m1614getRawHandleDragPositionF1C5BW0() {
        State $this$getValue$iv = this.rawHandleDragPosition;
        return ((Offset) $this$getValue$iv.getValue()).getPackedValue();
    }

    /* JADX INFO: renamed from: setRawHandleDragPosition-k-4lQ0M, reason: not valid java name */
    private final void m1618setRawHandleDragPositionk4lQ0M(long j) {
        MutableState $this$setValue$iv = this.rawHandleDragPosition;
        $this$setValue$iv.setValue(Offset.m4282boximpl(j));
    }

    /* JADX INFO: renamed from: getHandleDragPosition-F1C5BW0, reason: not valid java name */
    public final long m1622getHandleDragPositionF1C5BW0() {
        if (OffsetKt.m4314isUnspecifiedk4lQ0M(m1614getRawHandleDragPositionF1C5BW0())) {
            return Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
        }
        if (OffsetKt.m4314isUnspecifiedk4lQ0M(m1615getStartTextLayoutPositionInWindowF1C5BW0())) {
            return TextLayoutStateKt.m1574fromDecorationToTextLayoutUv8p0NA(this.textLayoutState, m1614getRawHandleDragPositionF1C5BW0());
        }
        return Offset.m4298plusMKHz9U(m1614getRawHandleDragPositionF1C5BW0(), Offset.m4297minusMKHz9U(m1615getStartTextLayoutPositionInWindowF1C5BW0(), m1612getCurrentTextLayoutPositionInWindowF1C5BW0()));
    }

    public final Handle getDraggingHandle() {
        State $this$getValue$iv = this.draggingHandle;
        return (Handle) $this$getValue$iv.getValue();
    }

    public final void setDraggingHandle(Handle handle) {
        MutableState $this$setValue$iv = this.draggingHandle;
        $this$setValue$iv.setValue(handle);
    }

    public final InputType getDirectDragGestureInitiator() {
        State $this$getValue$iv = this.directDragGestureInitiator;
        return (InputType) $this$getValue$iv.getValue();
    }

    public final void setDirectDragGestureInitiator(InputType inputType) {
        MutableState $this$setValue$iv = this.directDragGestureInitiator;
        $this$setValue$iv.setValue(inputType);
    }

    private final boolean getShowCursorHandle() {
        State $this$getValue$iv = this.showCursorHandle;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowCursorHandle(boolean z) {
        MutableState $this$setValue$iv = this.showCursorHandle;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextToolbarState getTextToolbarState() {
        State $this$getValue$iv = this.textToolbarState;
        return (TextToolbarState) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextToolbarState(TextToolbarState textToolbarState) {
        MutableState $this$setValue$iv = this.textToolbarState;
        $this$setValue$iv.setValue(textToolbarState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getTextLayoutCoordinates() {
        LayoutCoordinates it = this.textLayoutState.getTextLayoutNodeCoordinates();
        if (it == null || !it.isAttached()) {
            return null;
        }
        return it;
    }

    private final boolean getEditable() {
        return this.enabled && !this.readOnly;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0048  */
    public final TextFieldHandleState getCursorHandleState$foundation_release(boolean includePosition) {
        TextFieldCharSequence text = this.textFieldState.getVisualText();
        boolean showCursorHandle = getShowCursorHandle();
        boolean visible = true;
        boolean notBeingDragged = getDirectDragGestureInitiator() == InputType.None;
        Handle draggingHandle = getDraggingHandle();
        if (showCursorHandle && notBeingDragged && TextRange.m6473getCollapsedimpl(text.getSelection()) && text.shouldShowSelection()) {
            if (!(text.length() > 0) || (draggingHandle != Handle.Cursor && !isCursorHandleInVisibleBounds())) {
                visible = false;
            }
        } else {
            visible = false;
        }
        if (visible) {
            return new TextFieldHandleState(true, includePosition ? getCursorRect().m4320getBottomCenterF1C5BW0() : Offset.INSTANCE.m4308getUnspecifiedF1C5BW0(), ResolvedTextDirection.Ltr, false, null);
        }
        return TextFieldHandleState.INSTANCE.getHidden();
    }

    private final boolean isCursorHandleInVisibleBounds() {
        Rect rectVisibleBounds;
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            long position = getCursorRect().m4320getBottomCenterF1C5BW0();
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            if (textLayoutCoordinates == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) {
                return false;
            }
            return SelectionManagerKt.m1733containsInclusiveUv8p0NA(rectVisibleBounds, position);
        } catch (Throwable th) {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            throw th;
        }
    }

    public final Rect getCursorRect() {
        float cursorCenterX;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Rect.INSTANCE.getZero();
        }
        TextFieldCharSequence value = this.textFieldState.getVisualText();
        if (!TextRange.m6473getCollapsedimpl(value.getSelection())) {
            return Rect.INSTANCE.getZero();
        }
        Rect cursorRect = layoutResult.getCursorRect(TextRange.m6479getStartimpl(value.getSelection()));
        Density $this$getCursorRect_u24lambda_u242 = this.density;
        float cursorWidth = $this$getCursorRect_u24lambda_u242.mo726toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
        if (layoutResult.getLayoutInput().getLayoutDirection() == LayoutDirection.Ltr) {
            cursorCenterX = cursorRect.getLeft() + (cursorWidth / 2);
        } else {
            cursorCenterX = cursorRect.getRight() - (cursorWidth / 2);
        }
        float f = 2;
        float coercedCursorCenterX = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(cursorCenterX, IntSize.m7186getWidthimpl(layoutResult.getSize()) - (cursorWidth / f)), cursorWidth / f);
        return new Rect(coercedCursorCenterX - (cursorWidth / f), cursorRect.getTop(), (cursorWidth / f) + coercedCursorCenterX, cursorRect.getBottom());
    }

    public final void update(HapticFeedback hapticFeedBack, ClipboardManager clipboardManager, TextToolbar textToolbar, Density density, boolean enabled, boolean readOnly, boolean isPassword) {
        if (!enabled) {
            hideTextToolbar();
        }
        this.hapticFeedBack = hapticFeedBack;
        this.clipboardManager = clipboardManager;
        this.textToolbar = textToolbar;
        this.density = density;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.isPassword = isPassword;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(PointerInputScope pointerInputScope, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_cursorHandleGestures = pointerInputScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = TextFieldSelectionState.this.new AnonymousClass2(this.$this_cursorHandleGestures, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {368}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$this_cursorHandleGestures, continuation);
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
                        this.label = 1;
                        if (this.this$0.detectTouchMode(this.$this_cursorHandleGestures, this) == coroutine_suspended) {
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
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1, null);
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00612(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1, null);
                    return BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass3(this.$this_cursorHandleGestures, TextFieldSelectionState.this, null), 1, null);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {371}, m = "invokeSuspend", n = {}, s = {})
        static final class C00612 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00612(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super C00612> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00612(this.this$0, this.$this_cursorHandleGestures, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00612) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (this.this$0.detectCursorHandleDragGestures(this.$this_cursorHandleGestures, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3", f = "TextFieldSelectionState.kt", i = {}, l = {374}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$this_cursorHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.$this_cursorHandleGestures, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        PointerInputScope pointerInputScope = this.$this_cursorHandleGestures;
                        final TextFieldSelectionState textFieldSelectionState = this.this$0;
                        Function1<Offset, Unit> function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.cursorHandleGestures.2.3.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                                m1629invokek4lQ0M(offset.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                            public final void m1629invokek4lQ0M(long it) {
                                TextToolbarState textToolbarState;
                                TextFieldSelectionState textFieldSelectionState2 = textFieldSelectionState;
                                if (textFieldSelectionState.getTextToolbarState() == TextToolbarState.Cursor) {
                                    textToolbarState = TextToolbarState.None;
                                } else {
                                    textToolbarState = TextToolbarState.Cursor;
                                }
                                textFieldSelectionState2.setTextToolbarState(textToolbarState);
                            }
                        };
                        this.label = 1;
                        if (TapGestureDetectorKt.detectTapGestures(pointerInputScope, (7 & 1) != 0 ? null : null, (7 & 2) != 0 ? null : null, (7 & 4) != 0 ? TapGestureDetectorKt.NoPressGesture : null, (7 & 8) != 0 ? null : function1, this) == coroutine_suspended) {
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

    public final Object cursorHandleGestures(PointerInputScope $this$cursorHandleGestures, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2($this$cursorHandleGestures, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04422 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ boolean $isStartHandle;
        final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04422(PointerInputScope pointerInputScope, boolean z, Continuation<? super C04422> continuation) {
            super(2, continuation);
            this.$this_selectionHandleGestures = pointerInputScope;
            this.$isStartHandle = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04422 c04422 = TextFieldSelectionState.this.new C04422(this.$this_selectionHandleGestures, this.$isStartHandle, continuation);
            c04422.L$0 = obj;
            return c04422;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C04422) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {391}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$this_selectionHandleGestures, continuation);
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
                        this.label = 1;
                        if (this.this$0.detectTouchMode(this.$this_selectionHandleGestures, this) == coroutine_suspended) {
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
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(TextFieldSelectionState.this, this.$this_selectionHandleGestures, null), 1, null);
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00632(this.$this_selectionHandleGestures, TextFieldSelectionState.this, this.$isStartHandle, null), 1, null);
                    return BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass3(TextFieldSelectionState.this, this.$this_selectionHandleGestures, this.$isStartHandle, null), 1, null);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {394}, m = "invokeSuspend", n = {}, s = {})
        static final class C00632 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00632(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, boolean z, Continuation<? super C00632> continuation) {
                super(2, continuation);
                this.$this_selectionHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
                this.$isStartHandle = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00632(this.$this_selectionHandleGestures, this.this$0, this.$isStartHandle, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00632) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        PointerInputScope pointerInputScope = this.$this_selectionHandleGestures;
                        final TextFieldSelectionState textFieldSelectionState = this.this$0;
                        final boolean z = this.$isStartHandle;
                        TapOnPosition tapOnPosition = new TapOnPosition() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.selectionHandleGestures.2.2.1
                            @Override // androidx.compose.foundation.text.input.internal.selection.TapOnPosition
                            /* JADX INFO: renamed from: onEvent-k-4lQ0M */
                            public final void mo1593onEventk4lQ0M(long it) {
                                Handle handle;
                                textFieldSelectionState.markStartContentVisibleOffset();
                                TextFieldSelectionState textFieldSelectionState2 = textFieldSelectionState;
                                if (z) {
                                    handle = Handle.SelectionStart;
                                } else {
                                    handle = Handle.SelectionEnd;
                                }
                                textFieldSelectionState2.m1623updateHandleDraggingUv8p0NA(handle, SelectionHandlesKt.m1696getAdjustedCoordinatesk4lQ0M(textFieldSelectionState.m1613getHandlePositiontuRUvjQ(z)));
                            }
                        };
                        final TextFieldSelectionState textFieldSelectionState2 = this.this$0;
                        this.label = 1;
                        if (PressDownGestureKt.detectPressDownGesture(pointerInputScope, tapOnPosition, new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.selectionHandleGestures.2.2.2
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
                                textFieldSelectionState2.clearHandleDragging();
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$3", f = "TextFieldSelectionState.kt", i = {}, l = {412}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, boolean z, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
                this.$isStartHandle = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.this$0, this.$this_selectionHandleGestures, this.$isStartHandle, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (this.this$0.detectSelectionHandleDragGestures(this.$this_selectionHandleGestures, this.$isStartHandle, this) == coroutine_suspended) {
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

    public final Object selectionHandleGestures(PointerInputScope $this$selectionHandleGestures, boolean isStartHandle, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C04422($this$selectionHandleGestures, isStartHandle, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x005e  */
    /* JADX WARN: Code duplicated, block: B:31:0x0074  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object observeChanges(Continuation<? super Unit> continuation) throws Throwable {
        C04351 c04351;
        Throwable th;
        TextFieldSelectionState textFieldSelectionState;
        if (continuation instanceof C04351) {
            c04351 = (C04351) continuation;
            if ((c04351.label & Integer.MIN_VALUE) != 0) {
                c04351.label -= Integer.MIN_VALUE;
            } else {
                c04351 = new C04351(continuation);
            }
        } else {
            c04351 = new C04351(continuation);
        }
        C04351 c04352 = c04351;
        Object $result = c04352.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04352.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    C04362 c04362 = new C04362(null);
                    c04352.L$0 = this;
                    c04352.label = 1;
                    if (CoroutineScopeKt.coroutineScope(c04362, c04352) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    textFieldSelectionState = this;
                    textFieldSelectionState.setShowCursorHandle(false);
                    if (textFieldSelectionState.getTextToolbarState() != TextToolbarState.None) {
                        textFieldSelectionState.hideTextToolbar();
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    textFieldSelectionState = this;
                    textFieldSelectionState.setShowCursorHandle(false);
                    if (textFieldSelectionState.getTextToolbarState() != TextToolbarState.None) {
                        textFieldSelectionState.hideTextToolbar();
                    }
                    throw th;
                }
            case 1:
                textFieldSelectionState = (TextFieldSelectionState) c04352.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    textFieldSelectionState.setShowCursorHandle(false);
                    if (textFieldSelectionState.getTextToolbarState() != TextToolbarState.None) {
                        textFieldSelectionState.hideTextToolbar();
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    textFieldSelectionState.setShowCursorHandle(false);
                    if (textFieldSelectionState.getTextToolbarState() != TextToolbarState.None) {
                        textFieldSelectionState.hideTextToolbar();
                    }
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04362 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04362(Continuation<? super C04362> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04362 c04362 = TextFieldSelectionState.this.new C04362(continuation);
            c04362.L$0 = obj;
            return c04362;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C04362) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {424}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
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
                        this.label = 1;
                        if (this.this$0.observeTextChanges(this) == coroutine_suspended) {
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
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new AnonymousClass1(TextFieldSelectionState.this, null), 3, null);
                    return BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new C00622(TextFieldSelectionState.this, null), 3, null);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {425}, m = "invokeSuspend", n = {}, s = {})
        static final class C00622 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00622(TextFieldSelectionState textFieldSelectionState, Continuation<? super C00622> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00622(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00622) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (this.this$0.observeTextToolbarVisibility(this) == coroutine_suspended) {
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

    public final void updateTextToolbarState(TextToolbarState textToolbarState) {
        setTextToolbarState(textToolbarState);
    }

    public final void dispose() {
        hideTextToolbar();
        this.textToolbar = null;
        this.clipboardManager = null;
        this.hapticFeedBack = null;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTouchMode$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTouchMode$2", f = "TextFieldSelectionState.kt", i = {0}, l = {455}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
    static final class C04332 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04332(Continuation<? super C04332> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04332 c04332 = TextFieldSelectionState.this.new C04332(continuation);
            c04332.L$0 = obj;
            return c04332;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C04332) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0038 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:11:0x0039  */
        /* JADX WARN: Code duplicated, block: B:14:0x0049  */
        /* JADX WARN: Code duplicated, block: B:15:0x004b  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0039 -> B:12:0x003f). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:15:0x004b
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 1
                switch(r1) {
                    case 0: goto L20;
                    case 1: goto L13;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L13:
                r1 = r8
                java.lang.Object r3 = r1.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
                kotlin.ResultKt.throwOnFailure(r9)
                r4 = r3
                r3 = r1
                r1 = r0
                r0 = r9
                goto L3f
            L20:
                kotlin.ResultKt.throwOnFailure(r9)
                r1 = r8
                java.lang.Object r3 = r1.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
            L28:
                androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r1.L$0 = r3
                r1.label = r2
                java.lang.Object r4 = r3.awaitPointerEvent(r4, r5)
                if (r4 != r0) goto L39
                return r0
            L39:
                r7 = r0
                r0 = r9
                r9 = r4
                r4 = r3
                r3 = r1
                r1 = r7
            L3f:
                androidx.compose.ui.input.pointer.PointerEvent r9 = (androidx.compose.ui.input.pointer.PointerEvent) r9
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r5 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                boolean r6 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r9)
                if (r6 != 0) goto L4b
                r9 = r2
                goto L4c
            L4b:
                r9 = 0
            L4c:
                r5.setInTouchMode(r9)
                r9 = r0
                r0 = r1
                r1 = r3
                r3 = r4
                goto L28
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.C04332.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object detectTouchMode(PointerInputScope $this$detectTouchMode, Continuation<? super Unit> continuation) {
        Object objAwaitPointerEventScope = $this$detectTouchMode.awaitPointerEventScope(new C04332(null), continuation);
        return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
    }

    public final Object detectTextFieldTapGestures(PointerInputScope $this$detectTextFieldTapGestures, MutableInteractionSource interactionSource, final Function0<Unit> function0, final Function0<Unit> function1, Continuation<? super Unit> continuation) {
        Object objDetectTapAndPress = TapGestureDetectorKt.detectTapAndPress($this$detectTextFieldTapGestures, new C04312(interactionSource, this, null), new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectTextFieldTapGestures.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                m1635invokek4lQ0M(offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m1635invokek4lQ0M(long offset) {
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectTextFieldTapGestures.3.1
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "onTapTextField";
                    }
                });
                function0.invoke();
                if (this.enabled && this.getIsFocused()) {
                    if (!this.readOnly) {
                        function1.invoke();
                        if (this.textFieldState.getVisualText().length() > 0) {
                            this.setShowCursorHandle(true);
                        }
                    }
                    this.updateTextToolbarState(TextToolbarState.None);
                    long coercedOffset = this.textLayoutState.m1567coercedInVisibleBoundsOfInputTextMKHz9U$foundation_release(offset);
                    this.m1617placeCursorAtNearestOffsetk4lQ0M(TextLayoutStateKt.m1574fromDecorationToTextLayoutUv8p0NA(this.textLayoutState, coercedOffset));
                }
            }
        }, continuation);
        return objDetectTapAndPress == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapAndPress : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "offset", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {491}, m = "invokeSuspend", n = {}, s = {})
    static final class C04312 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04312(MutableInteractionSource mutableInteractionSource, TextFieldSelectionState textFieldSelectionState, Continuation<? super C04312> continuation) {
            super(3, continuation);
            this.$interactionSource = mutableInteractionSource;
            this.this$0 = textFieldSelectionState;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
            return m1634invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
        }

        /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m1634invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
            C04312 c04312 = new C04312(this.$interactionSource, this.this$0, continuation);
            c04312.L$0 = pressGestureScope;
            c04312.J$0 = j;
            return c04312.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            C04312 c04312;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PressGestureScope $this$detectTapAndPress = (PressGestureScope) this.L$0;
                    long offset = this.J$0;
                    MutableInteractionSource interactionSource = this.$interactionSource;
                    if (interactionSource != null) {
                        TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = new TextFieldSelectionState$detectTextFieldTapGestures$2$1$1($this$detectTapAndPress, this.this$0, offset, interactionSource, null);
                        this.label = 1;
                        if (CoroutineScopeKt.coroutineScope(textFieldSelectionState$detectTextFieldTapGestures$2$1$1, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c04312 = this;
                    }
                    return Unit.INSTANCE;
                case 1:
                    c04312 = this;
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeCursorAtNearestOffset-k-4lQ0M, reason: not valid java name */
    public final boolean m1617placeCursorAtNearestOffsetk4lQ0M(long offset) {
        int index;
        IndexTransformationType type$iv;
        int iM6479getStartimpl;
        SelectionWedgeAffinity selectionWedgeAffinity;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null || (index = layoutResult.m6451getOffsetForPositionk4lQ0M(offset)) == -1) {
            return false;
        }
        SelectionWedgeAffinity selectionWedgeAffinity2 = null;
        TransformedTextFieldState $this$getIndexTransformationType$iv = this.textFieldState;
        long untransformed$iv = $this$getIndexTransformationType$iv.m1581mapFromTransformedjx7JFs(index);
        long retransformed$iv = $this$getIndexTransformationType$iv.m1584mapToTransformedGEjPoXI(untransformed$iv);
        if (TextRange.m6473getCollapsedimpl(untransformed$iv) && TextRange.m6473getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Untransformed;
        } else if (!TextRange.m6473getCollapsedimpl(untransformed$iv) && !TextRange.m6473getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Replacement;
        } else if (TextRange.m6473getCollapsedimpl(untransformed$iv) && !TextRange.m6473getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Insertion;
        } else {
            type$iv = IndexTransformationType.Deletion;
        }
        IndexTransformationType type = type$iv;
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                iM6479getStartimpl = TextRange.m6479getStartimpl(untransformed$iv);
                break;
            case 2:
                iM6479getStartimpl = TextRange.m6479getStartimpl(untransformed$iv);
                break;
            case 3:
                Rect wedgeStartCursorRect = layoutResult.getCursorRect(TextRange.m6479getStartimpl(retransformed$iv));
                Rect wedgeEndCursorRect = layoutResult.getCursorRect(TextRange.m6474getEndimpl(retransformed$iv));
                if (MathUtilsKt.m1529findClosestRect9KIMszo(offset, wedgeStartCursorRect, wedgeEndCursorRect) < 0) {
                    selectionWedgeAffinity = new SelectionWedgeAffinity(WedgeAffinity.Start);
                } else {
                    selectionWedgeAffinity = new SelectionWedgeAffinity(WedgeAffinity.End);
                }
                selectionWedgeAffinity2 = selectionWedgeAffinity;
                iM6479getStartimpl = TextRange.m6479getStartimpl(untransformed$iv);
                break;
            case 4:
                Rect wedgeStartCursorRect2 = layoutResult.getCursorRect(TextRange.m6479getStartimpl(retransformed$iv));
                Rect wedgeEndCursorRect2 = layoutResult.getCursorRect(TextRange.m6474getEndimpl(retransformed$iv));
                if (MathUtilsKt.m1529findClosestRect9KIMszo(offset, wedgeStartCursorRect2, wedgeEndCursorRect2) < 0) {
                    iM6479getStartimpl = TextRange.m6479getStartimpl(untransformed$iv);
                } else {
                    iM6479getStartimpl = TextRange.m6474getEndimpl(untransformed$iv);
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        int untransformedCursor = iM6479getStartimpl;
        long untransformedCursorRange = TextRangeKt.TextRange(untransformedCursor);
        if (TextRange.m6472equalsimpl0(untransformedCursorRange, this.textFieldState.getUntransformedText().getSelection()) && (selectionWedgeAffinity2 == null || Intrinsics.areEqual(selectionWedgeAffinity2, this.textFieldState.getSelectionWedgeAffinity()))) {
            return false;
        }
        this.textFieldState.m1587selectUntransformedCharsIn5zctL8(untransformedCursorRange);
        if (selectionWedgeAffinity2 != null) {
            SelectionWedgeAffinity it = selectionWedgeAffinity2;
            this.textFieldState.setSelectionWedgeAffinity(it);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object detectCursorHandleDragGestures(PointerInputScope $this$detectCursorHandleDragGestures, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Ref.LongRef cursorDragStart;
        TextFieldSelectionState textFieldSelectionState;
        Ref.LongRef cursorDragStart2;
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
                final Ref.LongRef cursorDragStart3 = new Ref.LongRef();
                cursorDragStart3.element = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
                final Ref.LongRef cursorDragDelta = new Ref.LongRef();
                cursorDragDelta.element = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
                try {
                    Function1<Offset, Unit> function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectCursorHandleDragGestures.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                            m1630invokek4lQ0M(offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                        public final void m1630invokek4lQ0M(long it) {
                            cursorDragStart3.element = SelectionHandlesKt.m1696getAdjustedCoordinatesk4lQ0M(this.getCursorRect().m4320getBottomCenterF1C5BW0());
                            cursorDragDelta.element = Offset.INSTANCE.m4309getZeroF1C5BW0();
                            this.setInTouchMode(true);
                            this.markStartContentVisibleOffset();
                            this.m1623updateHandleDraggingUv8p0NA(Handle.Cursor, cursorDragStart3.element);
                        }
                    };
                    Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectCursorHandleDragGestures.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TextFieldSelectionState.detectCursorHandleDragGestures$onDragStop(cursorDragStart3, cursorDragDelta, this);
                        }
                    };
                    Function0<Unit> function2 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectCursorHandleDragGestures.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TextFieldSelectionState.detectCursorHandleDragGestures$onDragStop(cursorDragStart3, cursorDragDelta, this);
                        }
                    };
                    Function2<PointerInputChange, Offset, Unit> function3 = new Function2<PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectCursorHandleDragGestures.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                            m1631invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m1631invokeUv8p0NA(PointerInputChange change, long dragAmount) {
                            cursorDragDelta.element = Offset.m4298plusMKHz9U(cursorDragDelta.element, dragAmount);
                            this.m1623updateHandleDraggingUv8p0NA(Handle.Cursor, Offset.m4298plusMKHz9U(cursorDragStart3.element, cursorDragDelta.element));
                            if (this.m1617placeCursorAtNearestOffsetk4lQ0M(this.m1622getHandleDragPositionF1C5BW0())) {
                                change.consume();
                                HapticFeedback hapticFeedback = this.hapticFeedBack;
                                if (hapticFeedback != null) {
                                    hapticFeedback.mo5262performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5271getTextHandleMove5zf0vsI());
                                }
                            }
                        }
                    };
                    anonymousClass2.L$0 = this;
                    anonymousClass2.L$1 = cursorDragStart3;
                    anonymousClass2.L$2 = cursorDragDelta;
                    anonymousClass2.label = 1;
                    if (DragGestureDetectorKt.detectDragGestures($this$detectCursorHandleDragGestures, function1, function0, function2, function3, anonymousClass2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    cursorDragStart = cursorDragStart3;
                    textFieldSelectionState = this;
                    cursorDragStart2 = cursorDragDelta;
                    detectCursorHandleDragGestures$onDragStop(cursorDragStart, cursorDragStart2, textFieldSelectionState);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    cursorDragStart = cursorDragStart3;
                    textFieldSelectionState = this;
                    cursorDragStart2 = cursorDragDelta;
                    detectCursorHandleDragGestures$onDragStop(cursorDragStart, cursorDragStart2, textFieldSelectionState);
                    throw th;
                }
            case 1:
                cursorDragStart2 = (Ref.LongRef) anonymousClass2.L$2;
                cursorDragStart = (Ref.LongRef) anonymousClass2.L$1;
                textFieldSelectionState = (TextFieldSelectionState) anonymousClass2.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    detectCursorHandleDragGestures$onDragStop(cursorDragStart, cursorDragStart2, textFieldSelectionState);
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    detectCursorHandleDragGestures$onDragStop(cursorDragStart, cursorDragStart2, textFieldSelectionState);
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectCursorHandleDragGestures$onDragStop(Ref.LongRef cursorDragStart, Ref.LongRef cursorDragDelta, TextFieldSelectionState this$0) {
        if (OffsetKt.m4312isSpecifiedk4lQ0M(cursorDragStart.element)) {
            cursorDragStart.element = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
            cursorDragDelta.element = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
            this$0.clearHandleDragging();
        }
    }

    public final Object textFieldSelectionGestures(PointerInputScope $this$textFieldSelectionGestures, Function0<Unit> function0, Continuation<? super Unit> continuation) {
        Object objSelectionGesturePointerInputBtf2 = SelectionGesturesKt.selectionGesturePointerInputBtf2($this$textFieldSelectionGestures, new TextFieldMouseSelectionObserver(function0), new TextFieldTextDragObserver(function0), continuation);
        return objSelectionGesturePointerInputBtf2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSelectionGesturePointerInputBtf2 : Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\"\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0004H\u0016J\u001a\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0016J\"\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0011J*\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u00020\tX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldMouseSelectionObserver;", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "requestFocus", "Lkotlin/Function0;", "", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "onDrag", "", "dragPosition", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "onDrag-3MmeM6k", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "onDragDone", "onExtend", "downPosition", "onExtend-k-4lQ0M", "(J)Z", "onExtendDrag", "onExtendDrag-k-4lQ0M", "onStart", "onStart-3MmeM6k", "updateSelection", "Landroidx/compose/ui/text/TextRange;", "isStartOfSelection", "updateSelection-r1Wruf4", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class TextFieldMouseSelectionObserver implements MouseSelectionObserver {
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
        private final Function0<Unit> requestFocus;

        public TextFieldMouseSelectionObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onStart-3MmeM6k, reason: not valid java name */
        public boolean mo1628onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
            if (TextFieldSelectionState.this.enabled) {
                if (!(TextFieldSelectionState.this.textFieldState.getVisualText().length() == 0)) {
                    TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onStart$1
                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Mouse.onStart";
                        }
                    });
                    TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Mouse);
                    this.requestFocus.invoke();
                    TextFieldSelectionState.this.previousRawDragOffset = -1;
                    this.dragBeginOffsetInText = -1;
                    this.dragBeginPosition = downPosition;
                    long newSelection = m1624updateSelectionr1Wruf4(downPosition, adjustment, true);
                    this.dragBeginOffsetInText = TextRange.m6479getStartimpl(newSelection);
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onDrag-3MmeM6k, reason: not valid java name */
        public boolean mo1625onDrag3MmeM6k(final long dragPosition, SelectionAdjustment adjustment) {
            if (TextFieldSelectionState.this.enabled) {
                if (!(TextFieldSelectionState.this.textFieldState.getVisualText().length() == 0)) {
                    TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onDrag$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Mouse.onDrag " + ((Object) Offset.m4301toStringimpl(dragPosition));
                        }
                    });
                    m1624updateSelectionr1Wruf4(dragPosition, adjustment, false);
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: renamed from: updateSelection-r1Wruf4, reason: not valid java name */
        private final long m1624updateSelectionr1Wruf4(long dragPosition, SelectionAdjustment adjustment, boolean isStartOfSelection) {
            Integer numValueOf = Integer.valueOf(this.dragBeginOffsetInText);
            int it = numValueOf.intValue();
            if (!(it >= 0)) {
                numValueOf = null;
            }
            int startOffset = numValueOf != null ? numValueOf.intValue() : TextFieldSelectionState.this.textLayoutState.m1569getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
            int endOffset = TextFieldSelectionState.this.textLayoutState.m1569getOffsetForPosition3MmeM6k(dragPosition, false);
            long newSelection = TextFieldSelectionState.this.m1620updateSelectionSsLRf8(TextFieldSelectionState.this.textFieldState.getVisualText(), startOffset, endOffset, false, adjustment, false, isStartOfSelection);
            if (this.dragBeginOffsetInText == -1 && !TextRange.m6473getCollapsedimpl(newSelection)) {
                this.dragBeginOffsetInText = TextRange.m6479getStartimpl(newSelection);
            }
            if (TextRange.m6478getReversedimpl(newSelection)) {
                newSelection = TextFieldSelectionStateKt.m1637reverse5zctL8(newSelection);
            }
            TextFieldSelectionState.this.textFieldState.m1586selectCharsIn5zctL8(newSelection);
            TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
            return newSelection;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        public void onDragDone() {
            TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onDragDone$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Mouse.onDragDone";
                }
            });
            TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onExtend-k-4lQ0M, reason: not valid java name */
        public boolean mo1626onExtendk4lQ0M(long downPosition) {
            TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onExtend$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Mouse.onExtend";
                }
            });
            return true;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
        public boolean mo1627onExtendDragk4lQ0M(long dragPosition) {
            TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onExtendDrag$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Mouse.onExtendDrag";
                }
            });
            return true;
        }
    }

    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\u001a\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0012J\b\u0010\u0016\u001a\u00020\u0004H\u0002J\u001a\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0012J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\b\u0010\u001b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\u00020\u000bX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\fR\u0016\u0010\r\u001a\u00020\u000bX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\fR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldTextDragObserver;", "Landroidx/compose/foundation/text/TextDragObserver;", "requestFocus", "Lkotlin/Function0;", "", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "actingHandle", "Landroidx/compose/foundation/text/Handle;", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "dragTotalDistance", "onCancel", "onDown", "point", "onDown-k-4lQ0M", "(J)V", "onDrag", "delta", "onDrag-k-4lQ0M", "onDragStop", "onStart", "startPoint", "onStart-k-4lQ0M", "onStop", "onUp", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class TextFieldTextDragObserver implements TextDragObserver {
        private final Function0<Unit> requestFocus;
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
        private long dragTotalDistance = Offset.INSTANCE.m4309getZeroF1C5BW0();
        private Handle actingHandle = Handle.SelectionEnd;

        public TextFieldTextDragObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
        }

        private final void onDragStop() {
            if (OffsetKt.m4312isSpecifiedk4lQ0M(this.dragBeginPosition)) {
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldTextDragObserver$onDragStop$1
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "Touch.onDragStop";
                    }
                });
                TextFieldSelectionState.this.clearHandleDragging();
                this.dragBeginOffsetInText = -1;
                this.dragBeginPosition = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
                this.dragTotalDistance = Offset.INSTANCE.m4309getZeroF1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
                this.requestFocus.invoke();
            }
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* JADX INFO: renamed from: onDown-k-4lQ0M */
        public void mo1411onDownk4lQ0M(long point) {
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onUp() {
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onStop() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onCancel() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* JADX INFO: renamed from: onStart-k-4lQ0M */
        public void mo1413onStartk4lQ0M(final long startPoint) {
            if (TextFieldSelectionState.this.enabled) {
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldTextDragObserver$onStart$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "Touch.onDragStart after longPress at " + ((Object) Offset.m4301toStringimpl(startPoint));
                    }
                });
                TextFieldSelectionState.this.m1623updateHandleDraggingUv8p0NA(this.actingHandle, startPoint);
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Touch);
                this.dragBeginPosition = startPoint;
                this.dragTotalDistance = Offset.INSTANCE.m4309getZeroF1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                if (!TextFieldSelectionState.this.textLayoutState.m1570isPositionOnTextk4lQ0M(startPoint)) {
                    int offset = TextLayoutState.m1566getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, startPoint, false, 2, null);
                    HapticFeedback hapticFeedback = TextFieldSelectionState.this.hapticFeedBack;
                    if (hapticFeedback != null) {
                        hapticFeedback.mo5262performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5271getTextHandleMove5zf0vsI());
                    }
                    TextFieldSelectionState.this.textFieldState.placeCursorBeforeCharAt(offset);
                    TextFieldSelectionState.this.setShowCursorHandle(true);
                    TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Cursor);
                    return;
                }
                if (TextFieldSelectionState.this.textFieldState.getVisualText().length() == 0) {
                    return;
                }
                int offset2 = TextLayoutState.m1566getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, startPoint, false, 2, null);
                long newSelection = TextFieldSelectionState.m1621updateSelectionSsLRf8$default(TextFieldSelectionState.this, new TextFieldCharSequence(TextFieldSelectionState.this.textFieldState.getVisualText(), TextRange.INSTANCE.m6484getZerod9O1mEE(), null, null, 12, null), offset2, offset2, false, SelectionAdjustment.INSTANCE.getWord(), false, false, 96, null);
                TextFieldSelectionState.this.textFieldState.m1586selectCharsIn5zctL8(newSelection);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                this.dragBeginOffsetInText = TextRange.m6479getStartimpl(newSelection);
            }
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* JADX INFO: renamed from: onDrag-k-4lQ0M */
        public void mo1412onDragk4lQ0M(long delta) {
            int startOffset;
            int endOffset;
            SelectionAdjustment adjustment;
            Handle handle;
            if (TextFieldSelectionState.this.enabled) {
                if (TextFieldSelectionState.this.textFieldState.getVisualText().length() == 0) {
                    return;
                }
                this.dragTotalDistance = Offset.m4298plusMKHz9U(this.dragTotalDistance, delta);
                final long currentDragPosition = Offset.m4298plusMKHz9U(this.dragBeginPosition, this.dragTotalDistance);
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldTextDragObserver$onDrag$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "Touch.onDrag at " + ((Object) Offset.m4301toStringimpl(currentDragPosition));
                    }
                });
                if (this.dragBeginOffsetInText < 0 && !TextFieldSelectionState.this.textLayoutState.m1570isPositionOnTextk4lQ0M(currentDragPosition)) {
                    startOffset = TextLayoutState.m1566getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, this.dragBeginPosition, false, 2, null);
                    endOffset = TextLayoutState.m1566getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, currentDragPosition, false, 2, null);
                    if (startOffset == endOffset) {
                        adjustment = SelectionAdjustment.INSTANCE.getNone();
                    } else {
                        adjustment = SelectionAdjustment.INSTANCE.getWord();
                    }
                } else {
                    Integer numValueOf = Integer.valueOf(this.dragBeginOffsetInText);
                    int it = numValueOf.intValue();
                    if (!(it >= 0)) {
                        numValueOf = null;
                    }
                    startOffset = numValueOf != null ? numValueOf.intValue() : TextFieldSelectionState.this.textLayoutState.m1569getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
                    endOffset = TextFieldSelectionState.this.textLayoutState.m1569getOffsetForPosition3MmeM6k(currentDragPosition, false);
                    if (this.dragBeginOffsetInText < 0 && startOffset == endOffset) {
                        return;
                    }
                    adjustment = SelectionAdjustment.INSTANCE.getWord();
                    TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                }
                long prevSelection = TextFieldSelectionState.this.textFieldState.getVisualText().getSelection();
                long newSelection = TextFieldSelectionState.m1621updateSelectionSsLRf8$default(TextFieldSelectionState.this, TextFieldSelectionState.this.textFieldState.getVisualText(), startOffset, endOffset, false, adjustment, false, false, 64, null);
                if (this.dragBeginOffsetInText == -1 && !TextRange.m6473getCollapsedimpl(newSelection)) {
                    this.dragBeginOffsetInText = TextRange.m6479getStartimpl(newSelection);
                }
                if (TextRange.m6478getReversedimpl(newSelection)) {
                    newSelection = TextFieldSelectionStateKt.m1637reverse5zctL8(newSelection);
                }
                if (!TextRange.m6472equalsimpl0(newSelection, prevSelection)) {
                    if (TextRange.m6479getStartimpl(newSelection) != TextRange.m6479getStartimpl(prevSelection) && TextRange.m6474getEndimpl(newSelection) == TextRange.m6474getEndimpl(prevSelection)) {
                        handle = Handle.SelectionStart;
                    } else if (TextRange.m6479getStartimpl(newSelection) != TextRange.m6479getStartimpl(prevSelection) || TextRange.m6474getEndimpl(newSelection) == TextRange.m6474getEndimpl(prevSelection)) {
                        float newMiddle = (TextRange.m6479getStartimpl(newSelection) + TextRange.m6474getEndimpl(newSelection)) / 2.0f;
                        float prevMiddle = (TextRange.m6479getStartimpl(prevSelection) + TextRange.m6474getEndimpl(prevSelection)) / 2.0f;
                        if (newMiddle > prevMiddle) {
                            handle = Handle.SelectionEnd;
                        } else {
                            handle = Handle.SelectionStart;
                        }
                    } else {
                        handle = Handle.SelectionEnd;
                    }
                    this.actingHandle = handle;
                }
                if (TextRange.m6473getCollapsedimpl(prevSelection) || !TextRange.m6473getCollapsedimpl(newSelection)) {
                    TextFieldSelectionState.this.textFieldState.m1586selectCharsIn5zctL8(newSelection);
                }
                TextFieldSelectionState.this.m1623updateHandleDraggingUv8p0NA(this.actingHandle, currentDragPosition);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:38:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:47:0x0113  */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    public final Object detectSelectionHandleDragGestures(PointerInputScope pointerInputScope, boolean isStartHandle, Continuation<? super Unit> continuation) throws Throwable {
        C04261 c04261;
        final Handle handle;
        TextFieldSelectionState textFieldSelectionState;
        Ref.LongRef dragTotalDistance;
        Ref.LongRef dragBeginPosition;
        if (continuation instanceof C04261) {
            c04261 = (C04261) continuation;
            if ((c04261.label & Integer.MIN_VALUE) != 0) {
                c04261.label -= Integer.MIN_VALUE;
            } else {
                c04261 = new C04261(continuation);
            }
        } else {
            c04261 = new C04261(continuation);
        }
        Object $result = c04261.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04261.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final Ref.LongRef dragBeginPosition2 = new Ref.LongRef();
                dragBeginPosition2.element = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
                final Ref.LongRef dragTotalDistance2 = new Ref.LongRef();
                dragTotalDistance2.element = Offset.INSTANCE.m4309getZeroF1C5BW0();
                Handle handle2 = isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd;
                try {
                    final boolean z = isStartHandle;
                    final Handle handle3 = handle2;
                    Function1<Offset, Unit> function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                            m1632invokek4lQ0M(offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                        public final void m1632invokek4lQ0M(long it) {
                            dragBeginPosition2.element = SelectionHandlesKt.m1696getAdjustedCoordinatesk4lQ0M(this.m1613getHandlePositiontuRUvjQ(z));
                            this.m1623updateHandleDraggingUv8p0NA(handle3, dragBeginPosition2.element);
                            dragTotalDistance2.element = Offset.INSTANCE.m4309getZeroF1C5BW0();
                            this.previousRawDragOffset = -1;
                        }
                    };
                    Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TextFieldSelectionState.detectSelectionHandleDragGestures$onDragStop$5(dragBeginPosition2, this, dragTotalDistance2);
                        }
                    };
                    Function0<Unit> function2 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TextFieldSelectionState.detectSelectionHandleDragGestures$onDragStop$5(dragBeginPosition2, this, dragTotalDistance2);
                        }
                    };
                    final boolean z2 = isStartHandle;
                    final Handle handle4 = handle2;
                    Function2<PointerInputChange, Offset, Unit> function3 = new Function2<PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                            m1633invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                        public final void m1633invokeUv8p0NA(PointerInputChange pointerInputChange, long delta) {
                            int startOffset;
                            int endOffset;
                            dragTotalDistance2.element = Offset.m4298plusMKHz9U(dragTotalDistance2.element, delta);
                            TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
                            if (layoutResult == null) {
                                return;
                            }
                            this.m1623updateHandleDraggingUv8p0NA(handle4, Offset.m4298plusMKHz9U(dragBeginPosition2.element, dragTotalDistance2.element));
                            if (!z2) {
                                startOffset = TextRange.m6479getStartimpl(this.textFieldState.getVisualText().getSelection());
                            } else {
                                startOffset = layoutResult.m6451getOffsetForPositionk4lQ0M(this.m1622getHandleDragPositionF1C5BW0());
                            }
                            if (z2) {
                                endOffset = TextRange.m6474getEndimpl(this.textFieldState.getVisualText().getSelection());
                            } else {
                                endOffset = layoutResult.m6451getOffsetForPositionk4lQ0M(this.m1622getHandleDragPositionF1C5BW0());
                            }
                            long prevSelection = this.textFieldState.getVisualText().getSelection();
                            long newSelection = TextFieldSelectionState.m1621updateSelectionSsLRf8$default(this, this.textFieldState.getVisualText(), startOffset, endOffset, z2, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), false, false, 96, null);
                            if (TextRange.m6473getCollapsedimpl(prevSelection) || !TextRange.m6473getCollapsedimpl(newSelection)) {
                                this.textFieldState.m1586selectCharsIn5zctL8(newSelection);
                            }
                        }
                    };
                    c04261.L$0 = this;
                    c04261.L$1 = dragBeginPosition2;
                    c04261.L$2 = dragTotalDistance2;
                    c04261.L$3 = handle2;
                    c04261.label = 1;
                    Handle handle5 = handle2;
                    try {
                        if (DragGestureDetectorKt.detectDragGestures(pointerInputScope, function1, function0, function2, function3, c04261) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        handle = handle5;
                        textFieldSelectionState = this;
                        dragTotalDistance = dragTotalDistance2;
                        dragBeginPosition = dragBeginPosition2;
                        TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures.6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return "Selection Handle drag cancelled for draggingHandle: " + TextFieldSelectionState.this.getDraggingHandle() + " definedOn: " + handle;
                            }
                        });
                        if (textFieldSelectionState.getDraggingHandle() == handle) {
                            detectSelectionHandleDragGestures$onDragStop$5(dragBeginPosition, textFieldSelectionState, dragTotalDistance);
                        }
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        handle = handle5;
                        textFieldSelectionState = this;
                        dragTotalDistance = dragTotalDistance2;
                        dragBeginPosition = dragBeginPosition2;
                        TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures.6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return "Selection Handle drag cancelled for draggingHandle: " + TextFieldSelectionState.this.getDraggingHandle() + " definedOn: " + handle;
                            }
                        });
                        if (textFieldSelectionState.getDraggingHandle() == handle) {
                            detectSelectionHandleDragGestures$onDragStop$5(dragBeginPosition, textFieldSelectionState, dragTotalDistance);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    handle = handle2;
                    textFieldSelectionState = this;
                    dragTotalDistance = dragTotalDistance2;
                    dragBeginPosition = dragBeginPosition2;
                }
                break;
            case 1:
                handle = (Handle) c04261.L$3;
                dragTotalDistance = (Ref.LongRef) c04261.L$2;
                dragBeginPosition = (Ref.LongRef) c04261.L$1;
                textFieldSelectionState = (TextFieldSelectionState) c04261.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Selection Handle drag cancelled for draggingHandle: " + TextFieldSelectionState.this.getDraggingHandle() + " definedOn: " + handle;
                        }
                    });
                    if (textFieldSelectionState.getDraggingHandle() == handle) {
                        detectSelectionHandleDragGestures$onDragStop$5(dragBeginPosition, textFieldSelectionState, dragTotalDistance);
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Selection Handle drag cancelled for draggingHandle: " + TextFieldSelectionState.this.getDraggingHandle() + " definedOn: " + handle;
                        }
                    });
                    if (textFieldSelectionState.getDraggingHandle() == handle) {
                        detectSelectionHandleDragGestures$onDragStop$5(dragBeginPosition, textFieldSelectionState, dragTotalDistance);
                    }
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectSelectionHandleDragGestures$onDragStop$5(Ref.LongRef dragBeginPosition, TextFieldSelectionState this$0, Ref.LongRef dragTotalDistance) {
        if (OffsetKt.m4312isSpecifiedk4lQ0M(dragBeginPosition.element)) {
            this$0.clearHandleDragging();
            dragBeginPosition.element = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
            dragTotalDistance.element = Offset.INSTANCE.m4309getZeroF1C5BW0();
            this$0.previousRawDragOffset = -1;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextChanges$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* synthetic */ class C04383 extends FunctionReferenceImpl implements Function2<TextFieldCharSequence, CharSequence, Boolean> {
        public static final C04383 INSTANCE = new C04383();

        C04383() {
            super(2, TextFieldCharSequence.class, "contentEquals", "contentEquals(Ljava/lang/CharSequence;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(TextFieldCharSequence p0, CharSequence p1) {
            return Boolean.valueOf(p0.contentEquals(p1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextChanges(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.drop(FlowKt.distinctUntilChanged(SnapshotStateKt.snapshotFlow(new Function0<TextFieldCharSequence>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.observeTextChanges.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextFieldCharSequence invoke() {
                return TextFieldSelectionState.this.textFieldState.getVisualText();
            }
        }), C04383.INSTANCE), 1).collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.observeTextChanges.4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((TextFieldCharSequence) value, (Continuation<? super Unit>) $completion);
            }

            public final Object emit(TextFieldCharSequence it, Continuation<? super Unit> continuation2) {
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.None);
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextToolbarVisibility(Continuation<? super Unit> continuation) {
        Object objCollect = SnapshotStateKt.snapshotFlow(new Function0<Rect>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.observeTextToolbarVisibility.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Rect invoke() {
                Rect visibleBounds;
                boolean isCollapsed = TextRange.m6473getCollapsedimpl(TextFieldSelectionState.this.textFieldState.getVisualText().getSelection());
                boolean textToolbarStateVisible = (isCollapsed && TextFieldSelectionState.this.getTextToolbarState() == TextToolbarState.Cursor) || (!isCollapsed && TextFieldSelectionState.this.getTextToolbarState() == TextToolbarState.Selection);
                boolean textToolbarVisible = textToolbarStateVisible && TextFieldSelectionState.this.getDraggingHandle() == null && TextFieldSelectionState.this.isInTouchMode();
                if (textToolbarVisible) {
                    LayoutCoordinates textLayoutCoordinates = TextFieldSelectionState.this.getTextLayoutCoordinates();
                    Rect visibleBounds2 = textLayoutCoordinates != null ? SelectionManagerKt.visibleBounds(textLayoutCoordinates) : null;
                    if (visibleBounds2 != null) {
                        LayoutCoordinates textLayoutCoordinates2 = TextFieldSelectionState.this.getTextLayoutCoordinates();
                        Offset visibleBoundsTopLeftInRoot = textLayoutCoordinates2 != null ? Offset.m4282boximpl(textLayoutCoordinates2.mo5881localToRootMKHz9U(visibleBounds2.m4328getTopLeftF1C5BW0())) : null;
                        Intrinsics.checkNotNull(visibleBoundsTopLeftInRoot);
                        Rect visibleBoundsInRoot = RectKt.m4333Recttz77jQw(visibleBoundsTopLeftInRoot.getPackedValue(), visibleBounds2.m4326getSizeNHjbRc());
                        Rect it = TextFieldSelectionState.this.getContentRect();
                        Rect rect = visibleBoundsInRoot.overlaps(it) ? it : null;
                        if (rect == null || (visibleBounds = rect.intersect(visibleBoundsInRoot)) == null) {
                            return Rect.INSTANCE.getZero();
                        }
                        return visibleBounds;
                    }
                    return Rect.INSTANCE.getZero();
                }
                return Rect.INSTANCE.getZero();
            }
        }).collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.observeTextToolbarVisibility.3
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((Rect) value, (Continuation<? super Unit>) $completion);
            }

            public final Object emit(Rect rect, Continuation<? super Unit> continuation2) {
                if (Intrinsics.areEqual(rect, Rect.INSTANCE.getZero())) {
                    TextFieldSelectionState.this.hideTextToolbar();
                } else {
                    TextFieldSelectionState.this.showTextToolbar(rect);
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getContentRect() {
        float startTop;
        Rect cursorRect;
        Rect cursorRect2;
        TextFieldCharSequence text = this.textFieldState.getVisualText();
        if (TextRange.m6473getCollapsedimpl(text.getSelection())) {
            Rect cursorRect3 = getCursorRect();
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            long topLeft = textLayoutCoordinates != null ? textLayoutCoordinates.mo5881localToRootMKHz9U(cursorRect3.m4328getTopLeftF1C5BW0()) : Offset.INSTANCE.m4309getZeroF1C5BW0();
            return RectKt.m4333Recttz77jQw(topLeft, cursorRect3.m4326getSizeNHjbRc());
        }
        LayoutCoordinates textLayoutCoordinates2 = getTextLayoutCoordinates();
        long startOffset = textLayoutCoordinates2 != null ? textLayoutCoordinates2.mo5881localToRootMKHz9U(m1613getHandlePositiontuRUvjQ(true)) : Offset.INSTANCE.m4309getZeroF1C5BW0();
        LayoutCoordinates textLayoutCoordinates3 = getTextLayoutCoordinates();
        long endOffset = textLayoutCoordinates3 != null ? textLayoutCoordinates3.mo5881localToRootMKHz9U(m1613getHandlePositiontuRUvjQ(false)) : Offset.INSTANCE.m4309getZeroF1C5BW0();
        LayoutCoordinates textLayoutCoordinates4 = getTextLayoutCoordinates();
        float endTop = 0.0f;
        if (textLayoutCoordinates4 != null) {
            TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
            startTop = Offset.m4294getYimpl(textLayoutCoordinates4.mo5881localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult == null || (cursorRect2 = layoutResult.getCursorRect(TextRange.m6479getStartimpl(text.getSelection()))) == null) ? 0.0f : cursorRect2.getTop())));
        } else {
            startTop = 0.0f;
        }
        LayoutCoordinates textLayoutCoordinates5 = getTextLayoutCoordinates();
        if (textLayoutCoordinates5 != null) {
            TextLayoutResult layoutResult2 = this.textLayoutState.getLayoutResult();
            endTop = Offset.m4294getYimpl(textLayoutCoordinates5.mo5881localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult2 == null || (cursorRect = layoutResult2.getCursorRect(TextRange.m6474getEndimpl(text.getSelection()))) == null) ? 0.0f : cursorRect.getTop())));
        }
        return new Rect(Math.min(Offset.m4293getXimpl(startOffset), Offset.m4293getXimpl(endOffset)), Math.min(startTop, endTop), Math.max(Offset.m4293getXimpl(startOffset), Offset.m4293getXimpl(endOffset)), Math.max(Offset.m4294getYimpl(startOffset), Offset.m4294getYimpl(endOffset)));
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0059  */
    public final TextFieldHandleState getSelectionHandleState$foundation_release(boolean isStartHandle, boolean includePosition) {
        boolean visible;
        long coercedPosition;
        Rect it;
        Rect rectVisibleBounds;
        Handle handle = isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextFieldHandleState.INSTANCE.getHidden();
        }
        long selection = this.textFieldState.getVisualText().getSelection();
        if (TextRange.m6473getCollapsedimpl(selection)) {
            return TextFieldHandleState.INSTANCE.getHidden();
        }
        long position = m1613getHandlePositiontuRUvjQ(isStartHandle);
        if (getDirectDragGestureInitiator() != InputType.None) {
            visible = false;
        } else {
            if (getDraggingHandle() != handle) {
                LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
                if (!((textLayoutCoordinates == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) ? false : SelectionManagerKt.m1733containsInclusiveUv8p0NA(rectVisibleBounds, position))) {
                    visible = false;
                }
            }
            visible = true;
        }
        if (visible && this.textFieldState.getVisualText().shouldShowSelection()) {
            int directionOffset = isStartHandle ? TextRange.m6479getStartimpl(selection) : Math.max(TextRange.m6474getEndimpl(selection) - 1, 0);
            ResolvedTextDirection direction = layoutResult.getBidiRunDirection(directionOffset);
            boolean handlesCrossed = TextRange.m6478getReversedimpl(selection);
            if (includePosition) {
                LayoutCoordinates textLayoutCoordinates2 = getTextLayoutCoordinates();
                coercedPosition = (textLayoutCoordinates2 == null || (it = SelectionManagerKt.visibleBounds(textLayoutCoordinates2)) == null) ? position : TextLayoutStateKt.m1573coerceIn3MmeM6k(position, it);
            } else {
                coercedPosition = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
            }
            return new TextFieldHandleState(true, coercedPosition, direction, handlesCrossed, null);
        }
        return TextFieldHandleState.INSTANCE.getHidden();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getHandlePosition-tuRUvjQ, reason: not valid java name */
    public final long m1613getHandlePositiontuRUvjQ(boolean isStartHandle) {
        int offset;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Offset.INSTANCE.m4309getZeroF1C5BW0();
        }
        long selection = this.textFieldState.getVisualText().getSelection();
        if (isStartHandle) {
            offset = TextRange.m6479getStartimpl(selection);
        } else {
            offset = TextRange.m6474getEndimpl(selection);
        }
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(layoutResult, offset, isStartHandle, TextRange.m6478getReversedimpl(selection));
    }

    /* JADX INFO: renamed from: updateHandleDragging-Uv8p0NA, reason: not valid java name */
    public final void m1623updateHandleDraggingUv8p0NA(Handle handle, long position) {
        setDraggingHandle(handle);
        m1618setRawHandleDragPositionk4lQ0M(position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markStartContentVisibleOffset() {
        m1619setStartTextLayoutPositionInWindowk4lQ0M(m1612getCurrentTextLayoutPositionInWindowF1C5BW0());
    }

    public final void clearHandleDragging() {
        setDraggingHandle(null);
        m1618setRawHandleDragPositionk4lQ0M(Offset.INSTANCE.m4308getUnspecifiedF1C5BW0());
        m1619setStartTextLayoutPositionInWindowk4lQ0M(Offset.INSTANCE.m4308getUnspecifiedF1C5BW0());
    }

    public final boolean canCut() {
        return (TextRange.m6473getCollapsedimpl(this.textFieldState.getVisualText().getSelection()) || !getEditable() || this.isPassword) ? false : true;
    }

    public final void cut() {
        TextFieldCharSequence text = this.textFieldState.getVisualText();
        if (TextRange.m6473getCollapsedimpl(text.getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(text).toString(), null, null, 6, null));
        }
        this.textFieldState.deleteSelectedText();
    }

    public final boolean canCopy() {
        return (TextRange.m6473getCollapsedimpl(this.textFieldState.getVisualText().getSelection()) || this.isPassword) ? false : true;
    }

    public static /* synthetic */ void copy$default(TextFieldSelectionState textFieldSelectionState, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionState.copy(z);
    }

    public final void copy(boolean cancelSelection) {
        TextFieldCharSequence text = this.textFieldState.getVisualText();
        if (TextRange.m6473getCollapsedimpl(text.getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(text).toString(), null, null, 6, null));
        }
        if (cancelSelection) {
            this.textFieldState.collapseSelectionToMax();
        }
    }

    public final boolean canPaste() {
        if (!getEditable()) {
            return false;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null && clipboardManager.hasText()) {
            return true;
        }
        Function0<? extends ReceiveContentConfiguration> function0 = this.receiveContentConfiguration;
        if ((function0 != null ? function0.invoke() : null) == null) {
            return false;
        }
        ClipboardManager clipboardManager2 = this.clipboardManager;
        return (clipboardManager2 != null ? clipboardManager2.getClip() : null) != null;
    }

    public final void paste() {
        ReceiveContentConfiguration receiveContentConfiguration;
        ClipEntry clipEntry;
        ClipEntry clipEntry2;
        String clipboardText;
        Function0<? extends ReceiveContentConfiguration> function0 = this.receiveContentConfiguration;
        if (function0 == null || (receiveContentConfiguration = function0.invoke()) == null) {
            pasteAsPlainText();
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (clipEntry = clipboardManager.getClip()) == null) {
            pasteAsPlainText();
            return;
        }
        ClipMetadata clipMetadata = clipEntry.getClipMetadata();
        TransferableContent remaining = receiveContentConfiguration.getReceiveContentListener().onReceive(new TransferableContent(clipEntry, clipMetadata, TransferableContent.Source.INSTANCE.m689getClipboardkB6V9T0(), null, 8, null));
        if (remaining != null && (clipEntry2 = remaining.getClipEntry()) != null && (clipboardText = TransferableContent_androidKt.readPlainText(clipEntry2)) != null) {
            TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, clipboardText, false, TextFieldEditUndoBehavior.NeverMerge, 2, null);
        }
    }

    private final void pasteAsPlainText() {
        AnnotatedString text;
        String clipboardText;
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (text = clipboardManager.getText()) == null || (clipboardText = text.getText()) == null) {
            return;
        }
        TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, clipboardText, false, TextFieldEditUndoBehavior.NeverMerge, 2, null);
    }

    public final boolean canSelectAll() {
        return TextRange.m6475getLengthimpl(this.textFieldState.getVisualText().getSelection()) != this.textFieldState.getVisualText().length();
    }

    public final void selectAll() {
        this.textFieldState.selectAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTextToolbar(Rect contentRect) {
        Function0<Unit> function0;
        Function0<Unit> function1;
        Function0<Unit> function2;
        TextToolbar textToolbar = this.textToolbar;
        if (textToolbar != null) {
            boolean enabled$iv = canCopy();
            final TextToolbarState desiredState$iv = TextToolbarState.None;
            Function0<Unit> function3 = null;
            if (!enabled$iv) {
                function0 = null;
            } else {
                function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$showTextToolbar$$inlined$menuItem$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TextFieldSelectionState.copy$default(this, false, 1, null);
                        this.this$0$inline_fun.updateTextToolbarState(desiredState$iv);
                    }
                };
            }
            boolean enabled$iv2 = canPaste();
            final TextToolbarState desiredState$iv2 = TextToolbarState.None;
            if (!enabled$iv2) {
                function1 = null;
            } else {
                function1 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$showTextToolbar$$inlined$menuItem$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        this.paste();
                        this.this$0$inline_fun.updateTextToolbarState(desiredState$iv2);
                    }
                };
            }
            boolean enabled$iv3 = canCut();
            final TextToolbarState desiredState$iv3 = TextToolbarState.None;
            if (!enabled$iv3) {
                function2 = null;
            } else {
                function2 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$showTextToolbar$$inlined$menuItem$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        this.cut();
                        this.this$0$inline_fun.updateTextToolbarState(desiredState$iv3);
                    }
                };
            }
            boolean enabled$iv4 = canSelectAll();
            final TextToolbarState desiredState$iv4 = TextToolbarState.Selection;
            if (enabled$iv4) {
                function3 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$showTextToolbar$$inlined$menuItem$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        this.selectAll();
                        this.this$0$inline_fun.updateTextToolbarState(desiredState$iv4);
                    }
                };
            }
            textToolbar.showMenu(contentRect, function0, function1, function2, function3);
        }
    }

    private final Function0<Unit> menuItem(boolean enabled, final TextToolbarState desiredState, final Function0<Unit> operation) {
        if (enabled) {
            return new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.menuItem.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    operation.invoke();
                    this.updateTextToolbarState(desiredState);
                }
            };
        }
        return null;
    }

    public final void deselect() {
        if (!TextRange.m6473getCollapsedimpl(this.textFieldState.getVisualText().getSelection())) {
            this.textFieldState.collapseSelectionToEnd();
        }
        setShowCursorHandle(false);
        updateTextToolbarState(TextToolbarState.None);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideTextToolbar() {
        TextToolbar textToolbar;
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    /* JADX INFO: renamed from: updateSelection-SsL-Rf8$default, reason: not valid java name */
    static /* synthetic */ long m1621updateSelectionSsLRf8$default(TextFieldSelectionState textFieldSelectionState, TextFieldCharSequence textFieldCharSequence, int i, int i2, boolean z, SelectionAdjustment selectionAdjustment, boolean z2, boolean z3, int i3, Object obj) {
        boolean z4;
        boolean z5;
        if ((i3 & 32) == 0) {
            z4 = z2;
        } else {
            z4 = false;
        }
        if ((i3 & 64) == 0) {
            z5 = z3;
        } else {
            z5 = false;
        }
        return textFieldSelectionState.m1620updateSelectionSsLRf8(textFieldCharSequence, i, i2, z, selectionAdjustment, z4, z5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x0063  */
    /* JADX INFO: renamed from: updateSelection-SsL-Rf8, reason: not valid java name */
    public final long m1620updateSelectionSsLRf8(TextFieldCharSequence textFieldCharSequence, int startOffset, int endOffset, boolean isStartHandle, SelectionAdjustment adjustment, boolean allowPreviousSelectionCollapsed, boolean isStartOfSelection) {
        boolean z;
        TextRange textRangeM6467boximpl = TextRange.m6467boximpl(textFieldCharSequence.getSelection());
        long it = textRangeM6467boximpl.getPackedValue();
        if (!(!isStartOfSelection && (allowPreviousSelectionCollapsed || !TextRange.m6473getCollapsedimpl(it)))) {
            textRangeM6467boximpl = null;
        }
        long newSelection = m1616getTextFieldSelectionqeG_v_k(startOffset, endOffset, textRangeM6467boximpl, isStartHandle, adjustment);
        if (TextRange.m6472equalsimpl0(newSelection, textFieldCharSequence.getSelection())) {
            return newSelection;
        }
        if (TextRange.m6478getReversedimpl(newSelection) != TextRange.m6478getReversedimpl(textFieldCharSequence.getSelection())) {
            long $this$updateSelection_SsL_Rf8_u24lambda_u2413 = TextRangeKt.TextRange(TextRange.m6474getEndimpl(newSelection), TextRange.m6479getStartimpl(newSelection));
            z = TextRange.m6472equalsimpl0($this$updateSelection_SsL_Rf8_u24lambda_u2413, textFieldCharSequence.getSelection());
        }
        boolean onlyChangeIsReversed = z;
        if (isInTouchMode() && !onlyChangeIsReversed) {
            HapticFeedback hapticFeedback = this.hapticFeedBack;
            if (hapticFeedback != null) {
                hapticFeedback.mo5262performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5271getTextHandleMove5zf0vsI());
            }
        }
        return newSelection;
    }

    /* JADX INFO: renamed from: getTextFieldSelection-qeG_v_k, reason: not valid java name */
    private final long m1616getTextFieldSelectionqeG_v_k(int rawStartOffset, int rawEndOffset, TextRange previousSelection, boolean isStartHandle, SelectionAdjustment adjustment) {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextRange.INSTANCE.m6484getZerod9O1mEE();
        }
        if (previousSelection == null && Intrinsics.areEqual(adjustment, SelectionAdjustment.INSTANCE.getCharacter())) {
            return TextRangeKt.TextRange(rawStartOffset, rawEndOffset);
        }
        SelectionLayout selectionLayout = SelectionLayoutKt.m1699getTextFieldSelectionLayoutRcvTLA(layoutResult, rawStartOffset, rawEndOffset, this.previousRawDragOffset, previousSelection != null ? previousSelection.getPackedValue() : TextRange.INSTANCE.m6484getZerod9O1mEE(), previousSelection == null, isStartHandle);
        if (previousSelection != null && !selectionLayout.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return previousSelection.getPackedValue();
        }
        long result = adjustment.adjust(selectionLayout).m1687toTextRanged9O1mEE();
        this.previousSelectionLayout = selectionLayout;
        this.previousRawDragOffset = isStartHandle ? rawStartOffset : rawEndOffset;
        return result;
    }
}
